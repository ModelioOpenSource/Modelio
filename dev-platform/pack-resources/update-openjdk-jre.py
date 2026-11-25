#!/usr/bin/env python3
"""Regenerate the vendored Java runtime p2 repository (dev-platform/pack-resources/openjdk-jre).

The Modelio product bundles a Java runtime through the feature `net.adoptium.temurin.jre.feature`
(installMode="root"): one p2 binary artifact per platform, unzipped into the installation as `jre/`.
This script rebuilds that repository for a given Eclipse Temurin 21 release, for every platform the
product supports, so the bundled Java can follow the quarterly Java security updates.

For each platform it downloads the official Temurin JDK archive from GitHub (adoptium/temurin21-binaries),
checks it against the SHA-256 published with the release, and keeps the same content as before:
the JDK without jmods/, include/, man/, demo/ and lib/src.zip (bin, conf, legal, lib, release).
On macOS the JDK bundle's Contents/Home is used, so the layout is `jre/bin/java` on every platform.

Usage:
    python3 update-openjdk-jre.py <temurin release, e.g. 21.0.13.1+2> [download cache dir]

Then build as usual; the product picks the new feature version. Needs Python 3.8+ and network access
to github.com only (downloads with curl).
"""
import gzip
import hashlib
import io
import os
import re
import shutil
import stat
import subprocess
import sys
import tarfile
import tempfile
import time
import zipfile
from pathlib import Path

FEATURE = "net.adoptium.temurin.jre.feature"
REPO = Path(__file__).resolve().parent / "openjdk-jre"

# p2 configuration -> (Temurin asset platform, archive type, executable files that need chmod 755)
PLATFORMS = {
    "gtk.linux.x86_64": ("x64_linux", "tar.gz", ("so",)),
    "win32.win32.x86_64": ("x64_windows", "zip", ()),
    "cocoa.macosx.aarch64": ("aarch64_mac", "tar.gz", ("dylib",)),
    "cocoa.macosx.x86_64": ("x64_mac", "tar.gz", ("dylib",)),
}
DROP_DIRS = ("jmods", "include", "man", "demo", "sample")
DROP_FILES = ("lib/src.zip",)


def filt(config):
    ws, os_, arch = config.split(".")
    return f"(&amp;(osgi.arch={arch})(osgi.os={os_})(osgi.ws={ws}))"


def download(url, dest):
    """Download with curl (uses the system certificate store, follows GitHub redirects)."""
    if not dest.exists():
        print("download", url)
        tmp = dest.with_name(dest.name + ".part")
        subprocess.run(["curl", "-fsSL", "--retry", "3", "-o", str(tmp), url], check=True)
        tmp.rename(dest)
    return dest


def fetch(release, asset_platform, kind, cache):
    tag = f"jdk-{release}"
    name = f"OpenJDK21U-jdk_{asset_platform}_hotspot_{release.replace('+', '_')}.{kind}"
    base = f"https://github.com/adoptium/temurin21-binaries/releases/download/{tag.replace('+', '%2B')}/{name}"
    archive = download(base, cache / name)
    expected = download(base + ".sha256.txt", cache / (name + ".sha256.txt")).read_text().split()[0]
    got = hashlib.sha256(archive.read_bytes()).hexdigest()
    if got != expected:
        archive.unlink()
        sys.exit(f"SHA-256 mismatch for {name}: {got} != {expected}")
    print("verified", name, got)
    return archive


def jdk_home_entries(archive, kind):
    """Yield (relative path inside the JDK home, bytes, is_executable) for the kept files."""
    def keep(rel):
        top = rel.split("/", 1)[0]
        return rel and top not in DROP_DIRS and rel not in DROP_FILES

    if kind == "zip":
        with zipfile.ZipFile(archive) as z:
            for info in z.infolist():
                if info.is_dir():
                    continue
                rel = info.filename.split("/", 1)[1] if "/" in info.filename else ""
                if keep(rel):
                    yield rel, z.read(info), False
        return
    # decompress once into memory: symlinks point backwards in the archive (random access)
    with tarfile.open(fileobj=io.BytesIO(gzip.decompress(Path(archive).read_bytes()))) as t:
        members = {m.name: m for m in t.getmembers()}
        for m in t.getmembers():
            if not (m.isfile() or m.issym()):
                continue
            parts = m.name.split("/")
            # Linux: jdk-x/<home>; macOS: jdk-x/Contents/Home/<home>
            if len(parts) > 3 and parts[1] == "Contents" and parts[2] == "Home":
                rel = "/".join(parts[3:])
            elif len(parts) > 1 and parts[1] != "Contents":
                rel = "/".join(parts[1:])
            else:
                continue  # macOS bundle files outside Contents/Home (Info.plist, MacOS/, _CodeSignature)
            if not keep(rel):
                continue
            src = m
            while src.issym():  # store links as regular files (zip/p2 unzip have no symlinks)
                target = os.path.normpath(os.path.join(os.path.dirname(src.name), src.linkname))
                src = members[target]
            yield rel, t.extractfile(src).read(), bool(src.mode & stat.S_IXUSR)


def build_payload(archive, kind, out):
    names, executables = [], []
    with zipfile.ZipFile(out, "w", zipfile.ZIP_DEFLATED, compresslevel=6) as z:
        for rel, data, exe in sorted(jdk_home_entries(archive, kind)):
            info = zipfile.ZipInfo("jre/" + rel, date_time=(2026, 1, 1, 0, 0, 0))
            info.external_attr = (0o755 if exe else 0o644) << 16
            info.compress_type = zipfile.ZIP_DEFLATED
            z.writestr(info, data)
            names.append("jre/" + rel)
    return names


def chmod_targets(names, libsuffixes):
    if not libsuffixes:
        return []
    out = []
    for n in names:
        if (n.startswith("jre/bin/") or n in ("jre/lib/jexec", "jre/lib/jspawnhelper")
                or any(n.endswith("." + s) for s in libsuffixes)):
            out.append(n)
    return sorted(out)


def root_unit(config, version, chmods):
    iu = f"{FEATURE}_root.{config}"
    ins = "".join(
        f"""
        <instructions size='1'>
          <instruction key='install'>
            chmod(targetDir:${{installFolder}}, targetFile:{f}, permissions:755);
          </instruction>
        </instructions>""" for f in chmods)
    return f"""
    <unit id='{iu}' version='{version}'>
      <provides size='1'>
        <provided namespace='org.eclipse.equinox.p2.iu' name='{iu}' version='{version}'/>
      </provides>
      <filter>
        {filt(config)}
      </filter>
      <artifacts size='1'>
        <artifact classifier='binary' id='{iu}' version='{version}'/>
      </artifacts>
      <touchpoint id='org.eclipse.equinox.p2.native' version='1.0.0'/>
      <touchpointData size='{1 + len(chmods)}'>
        <instructions size='2'>
          <instruction key='uninstall'>
            cleanupzip(source:@artifact, target:${{installFolder}});
          </instruction>
          <instruction key='install'>
            unzip(source:@artifact, target:${{installFolder}});
          </instruction>
        </instructions>{ins}
      </touchpointData>
    </unit>"""


LICENSE = """
      <licenses size='1'>
        <license uri='https://www.eclipse.org/legal/epl-2.0/' url='https://www.eclipse.org/legal/epl-2.0/'>
          https://www.eclipse.org/legal/epl-2.0/
        </license>
      </licenses>
      <copyright uri='https://www.eclipse.org/legal/copyright.php' url='https://www.eclipse.org/legal/copyright.php'>
        https://www.eclipse.org/legal/copyright.php
      </copyright>"""

PROPS = """
        <property name='org.eclipse.equinox.p2.name' value='jre21'/>
        <property name='org.eclipse.equinox.p2.description' value='https://adoptium.net/temurin/'/>
        <property name='org.eclipse.equinox.p2.description.url' value='https://adoptium.net/temurin/'/>
        <property name='org.eclipse.equinox.p2.provider' value='Eclipse Adoptium'/>"""


def content_xml(version, roots, ts):
    reqs = "".join(f"""
        <required namespace='org.eclipse.equinox.p2.iu' name='{FEATURE}_root.{c}' range='[{version},{version}]'>
          <filter>
            {filt(c)}
          </filter>
        </required>""" for c in roots)
    units = [f"""
    <unit id='{FEATURE}.feature.group' version='{version}' singleton='false'>
      <update id='{FEATURE}.feature.group' range='[0.0.0,{version})' severity='0'/>
      <properties size='5'>{PROPS}
        <property name='org.eclipse.equinox.p2.type.group' value='true'/>
      </properties>
      <provides size='1'>
        <provided namespace='org.eclipse.equinox.p2.iu' name='{FEATURE}.feature.group' version='{version}'/>
      </provides>
      <requires size='{1 + len(roots)}'>
        <required namespace='org.eclipse.equinox.p2.iu' name='{FEATURE}.feature.jar' range='[{version},{version}]'>
          <filter>
            (org.eclipse.update.install.features=true)
          </filter>
        </required>{reqs}
      </requires>
      <touchpoint id='null' version='0.0.0'/>{LICENSE}
    </unit>""", f"""
    <unit id='{FEATURE}.feature.jar' version='{version}'>
      <properties size='4'>{PROPS}
      </properties>
      <provides size='3'>
        <provided namespace='org.eclipse.equinox.p2.iu' name='{FEATURE}.feature.jar' version='{version}'/>
        <provided namespace='org.eclipse.equinox.p2.eclipse.type' name='feature' version='1.0.0'/>
        <provided namespace='org.eclipse.update.feature' name='{FEATURE}' version='{version}'/>
      </provides>
      <filter>
        (org.eclipse.update.install.features=true)
      </filter>
      <artifacts size='1'>
        <artifact classifier='org.eclipse.update.feature' id='{FEATURE}' version='{version}'/>
      </artifacts>
      <touchpoint id='org.eclipse.equinox.p2.osgi' version='1.0.0'/>
      <touchpointData size='1'>
        <instructions size='1'>
          <instruction key='zipped'>
            true
          </instruction>
        </instructions>
      </touchpointData>{LICENSE}
    </unit>"""] + [root_unit(c, version, chm) for c, chm in roots.items()]
    return f"""<?xml version='1.0' encoding='UTF-8'?>
<?metadataRepository version='1.2.0'?>
<repository name='Exported Repository' type='org.eclipse.equinox.internal.p2.metadata.repository.LocalMetadataRepository' version='1'>
  <properties size='2'>
    <property name='p2.timestamp' value='{ts}'/>
    <property name='p2.compressed' value='true'/>
  </properties>
  <units size='{len(units)}'>{''.join(units)}
  </units>
</repository>
"""


def artifacts_xml(version, sizes, feature_size, ts):
    arts = "".join(f"""
    <artifact classifier='binary' id='{FEATURE}_root.{c}' version='{version}'>
      <properties size='2'>
        <property name='download.size' value='{s}'/>
        <property name='download.checksum.sha-256' value='{h}'/>
      </properties>
    </artifact>""" for c, (s, h) in sizes.items())
    return f"""<?xml version='1.0' encoding='UTF-8'?>
<?artifactRepository version='1.1.0'?>
<repository name='Exported Repository' type='org.eclipse.equinox.p2.artifact.repository.simpleRepository' version='1'>
  <properties size='2'>
    <property name='p2.timestamp' value='{ts}'/>
    <property name='p2.compressed' value='true'/>
  </properties>
  <mappings size='3'>
    <rule filter='(&amp; (classifier=osgi.bundle))' output='${{repoUrl}}/plugins/${{id}}_${{version}}.jar'/>
    <rule filter='(&amp; (classifier=binary))' output='${{repoUrl}}/binary/${{id}}_${{version}}'/>
    <rule filter='(&amp; (classifier=org.eclipse.update.feature))' output='${{repoUrl}}/features/${{id}}_${{version}}.jar'/>
  </mappings>
  <artifacts size='{len(sizes) + 1}'>
    <artifact classifier='org.eclipse.update.feature' id='{FEATURE}' version='{version}'>
      <properties size='2'>
        <property name='download.contentType' value='application/zip'/>
        <property name='download.size' value='{feature_size}'/>
      </properties>
    </artifact>{arts}
  </artifacts>
</repository>
"""


def feature_files(version):
    fx = f"""<?xml version="1.0" encoding="UTF-8"?>
<feature
      id="{FEATURE}"
      label="jre21"
      version="{version}"
      provider-name="Eclipse Adoptium">

   <description url="https://adoptium.net/temurin/">
      https://adoptium.net/temurin/
   </description>

   <copyright url="https://www.eclipse.org/legal/copyright.php">
      https://www.eclipse.org/legal/copyright.php
   </copyright>

   <license url="https://www.eclipse.org/legal/epl-2.0/">
      https://www.eclipse.org/legal/epl-2.0/
   </license>

</feature>
"""
    def pde(c):  # p2 config ws.os.arch -> PDE os.ws.arch
        ws, os_, arch = c.split(".")
        return f"{os_}.{ws}.{arch}"
    bp = "bin.includes = feature.xml,\\\n               build.properties\n" + "".join(
        f"root.{pde(c)} = root/{pde(c)}\n" for c in PLATFORMS)
    return fx, bp


def jar_of(path, files):
    buf = io.BytesIO()
    with zipfile.ZipFile(buf, "w", zipfile.ZIP_DEFLATED) as z:
        for name, data in files.items():
            info = zipfile.ZipInfo(name, date_time=(2026, 1, 1, 0, 0, 0))
            info.compress_type = zipfile.ZIP_DEFLATED
            z.writestr(info, data)
    path.write_bytes(buf.getvalue())


def main():
    if len(sys.argv) < 2 or not re.fullmatch(r"21\.\d+\.\d+(\.\d+)?\+\d+", sys.argv[1]):
        sys.exit(__doc__)
    release = sys.argv[1]
    version = release.split("+")[0]  # p2/OSGi version of the feature, e.g. 21.0.13.1
    cache = Path(sys.argv[2] if len(sys.argv) > 2 else Path(tempfile.gettempdir()) / "openjdk-jre-downloads").resolve()
    cache.mkdir(parents=True, exist_ok=True)
    ts = int(time.time() * 1000)

    archives = {c: fetch(release, asset, kind, cache) for c, (asset, kind, _) in PLATFORMS.items()}
    shutil.rmtree(REPO, ignore_errors=True)
    (REPO / "binary").mkdir(parents=True)
    (REPO / "features").mkdir()
    roots, sizes = {}, {}
    for config, (asset, kind, libs) in PLATFORMS.items():
        archive = archives[config]
        out = REPO / "binary" / f"{FEATURE}_root.{config}_{version}"
        names = build_payload(archive, kind, out)
        roots[config] = chmod_targets(names, libs)
        data = out.read_bytes()
        sizes[config] = (len(data), hashlib.sha256(data).hexdigest())
        print(f"payload {out.name}: {len(names)} files, {len(data)} bytes")

    fx, bp = feature_files(version)
    fdir = REPO / "features" / f"{FEATURE}_{version}"
    fdir.mkdir()
    (fdir / "feature.xml").write_text(fx)
    (fdir / "build.properties").write_text(bp)
    fjar = REPO / "features" / f"{FEATURE}_{version}.jar"
    jar_of(fjar, {"build.properties": bp.encode(), "feature.xml": fx.encode()})

    jar_of(REPO / "content.jar", {"content.xml": content_xml(version, roots, ts).encode()})
    jar_of(REPO / "artifacts.jar", {"artifacts.xml": artifacts_xml(version, sizes, fjar.stat().st_size, ts).encode()})
    print(f"done: {REPO} now provides {FEATURE} {version} for {', '.join(PLATFORMS)}")


if __name__ == "__main__":
    main()
