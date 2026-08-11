# Nix Integration for Modelio

Reproducible builds, development shells, OCI containers, and smoke tests for
Modelio using [Nix flakes](https://nixos.wiki/wiki/Flakes).

## Table of Contents

- [Quick Start](#quick-start)
- [What This Provides](#what-this-provides)
- [How It Works](#how-it-works)
  - [Maven Dependencies (maven-deps.nix)](#maven-dependencies-maven-depsnix)
  - [Source Build (package.nix)](#source-build-packagenix)
  - [Dev Shell (shell.nix)](#dev-shell-shellnix)
  - [Container (container.nix)](#container-containernix)
  - [Smoke Test (test.nix)](#smoke-test-testnix)
- [File Layout](#file-layout)
- [Design Decisions](#design-decisions)
- [Reproducibility Strategy](#reproducibility-strategy)
- [Updating the FOD Hash](#updating-the-fod-hash)

## Quick Start

```bash
# Build and run Modelio
nix run

# Build without running
nix build

# Enter a development shell (Maven + JDK 11 + native libs)
nix develop

# Build an OCI container image
nix build .#container

# Build container and run with Docker (handles X11 forwarding)
nix run .#container-run

# Run smoke tests
nix flake check
```

## What This Provides

| Flake output        | Description |
|---------------------|-------------|
| `packages.default` / `packages.modelio` | Full source build of Modelio 5.4.1 with desktop entry and wrapper script |
| `packages.container` | Minimal OCI container image (layered, with bash and CA certs) |
| `packages.container-run` | Script that loads the container into Docker and launches Modelio with X11 forwarding |
| `devShells.default` | Dev shell with Maven, JDK 11, native libs, and auto-generated `toolchains.xml` |
| `checks.default` | xvfb-based smoke test that verifies the built binary starts correctly |

All outputs target `x86_64-linux` only.

## How It Works

The flake (`flake.nix`) delegates to module files in `nix/`:

```
flake.nix
  ├── nix/lib.nix         → shared constants and shell snippets
  ├── nix/maven-deps.nix  → packages.modelio (FOD dependency cache)
  ├── nix/package.nix     → packages.modelio / packages.default
  ├── nix/shell.nix       → devShells.default
  ├── nix/container.nix     → packages.container
  ├── nix/container-run.nix → packages.container-run
  └── nix/test.nix          → checks.default
```

### Maven Dependencies (`maven-deps.nix`)

A **fixed-output derivation (FOD)** that runs with network access. Executes
`mvn install` on `AGGREGATOR/pom.xml` and `mvn package` on `products/pom.xml`,
caching all Maven, Tycho, and P2 dependencies into `$out/.m2/repository`.
The result is pinned by a SHA-256 content hash (`outputHash`).

Uses pinned source (`modelio-src` flake input) so local edits don't invalidate
the cache. Update the pin with: `nix flake update modelio-src`.

### Source Build (`package.nix`)

Builds fully offline (`--offline`) using the cached repo from `maven-deps.nix`.
Copies the repo to a writable temp directory (Tycho needs to write `.tycholock`
files), then runs the same Maven commands.

The install phase extracts the product tarball from
`products/target/products/`, replaces the bundled JRE with a symlink to the
Nix-provided JDK, and creates a wrapper script that sets:
- `LD_LIBRARY_PATH` for GTK, X11, WebKit, and other native libraries
- `JAVA_HOME` pointing to the runtime JDK
- JVM flags: `-Xms1024m -Xmx4096m`, UTF-8 Python console, WebKit TLS config
- A `.desktop` entry and icon

Shared values (toolchains, build.properties fixup, runtime library list) are
imported from `lib.nix` to avoid duplication.

### Dev Shell (`shell.nix`)

`mkShell` with Maven and JDK 11 on `PATH`, plus native library dependencies in
`buildInputs`. The `shellHook` imports `setupToolchains` from `lib.nix` and
prints build instructions:

```
1. mvn clean install -f AGGREGATOR/pom.xml
2. mvn package -f products/pom.xml -Pproduct.org,platform.linux
   Output: products/target/products/*linux*.tar.gz
```

### Container (`container.nix`)

Uses `dockerTools.buildLayeredImage` to produce an OCI image tagged with the
Modelio version. Contents:

- Modelio package
- `bash`, `coreutils`, CA certificates
- `DISPLAY=:99`, SSL cert path pre-configured

Load and run manually:

```bash
nix build .#container
docker load < result
docker run --rm -e DISPLAY=:0 -v /tmp/.X11-unix:/tmp/.X11-unix modelio:5.4.1
```

Or use the one-shot launcher (handles X11 auth, mounts `~/.modelio` and `$PWD`):

```bash
nix run .#container-run
```

### Smoke Test (`test.nix`)

A `writeShellApplication` wrapped in `runCommand` (so it runs as a Nix check).
The test:

1. Verifies the `modelio` binary exists and is executable
2. Confirms the JRE symlink is in place
3. Launches Modelio under `xvfb-run`, waits 15 seconds, and checks the process
   is still alive
4. Kills the process and confirms clean exit

## File Layout

| File | Purpose |
|------|---------|
| `flake.nix` | Flake entry point — wires outputs to `nix/*.nix` modules |
| `flake.lock` | Pinned input revisions (nixpkgs 24.11, flake-utils) |
| `nix/lib.nix` | Shared constants and shell snippets (version, toolchains, runtime libs) |
| `nix/maven-deps.nix` | Fixed-output derivation — Maven/Tycho dependency cache |
| `nix/package.nix` | Modelio source build (offline, using cached deps) |
| `nix/shell.nix` | Development shell |
| `nix/container.nix` | OCI container image |
| `nix/container-run.nix` | One-shot script to load and run the container with Docker |
| `nix/test.nix` | Smoke test check |
| `nix/README.md` | This file |

## Design Decisions

### 1. JDK 11 for build, latest JDK for runtime

Tycho 2.2.0 (used by Modelio's build) doesn't recognize execution environments
beyond ~JavaSE-16, so the build must use `jdk11`. The installed application
links to the default Nix `jdk` (latest OpenJDK) for runtime — Java is
backward-compatible for running bytecode compiled with older versions.

### 2. Dual toolchain entries (JavaSE-1.8 + JavaSE-11)

Some OSGi bundles in the Modelio source declare
`Bundle-RequiredExecutionEnvironment: JavaSE-1.8`. Tycho's `useJDK=BREE` mode
demands a matching toolchain entry for each referenced execution environment.
Both entries point to `jdk11`, which can compile Java 8 bytecode via
`--release 8`.

### 3. Dynamic missing-directory creation

Approximately 26 directories referenced in `build.properties` `bin.includes`
fields across the source tree are not tracked by git (empty directories). Rather
than maintaining a static list, a `find`+`grep` script at build time parses
every `build.properties` file and creates any missing directories automatically.

### 4. Writable repo copy for Tycho lock files

The FOD output lives in `/nix/store` and is read-only. Tycho creates
`.tycholock` files inside the Maven repository during resolution. The main
derivation copies the cached repo to `$HOME/.m2/repository` and makes it
writable before building.

### 5. `autoPatchelfHook` + `wrapGAppsHook3`

The Eclipse native launcher (`modelio` binary) needs the NixOS dynamic linker
patched in — `autoPatchelfHook` handles this automatically. GTK/GLib environment
variables are handled by `wrapGAppsHook3`, but `dontWrapGApps = true` is set so
that we control wrapper arguments ourselves via `makeWrapper` +
`gappsWrapperArgs`.

### 6. FOD hash maintenance

The `outputHash` in `maven-deps.nix` pins the exact set of fetched dependencies.
When upstream dependencies change, this hash must be updated. See below.

## Reproducibility Strategy

Maven builds are non-deterministic by default — metadata files contain
timestamps and JAR archives embed build dates. The FOD in `maven-deps.nix`
applies four mitigations to ensure the dependency cache hashes reproducibly:

### 1. Delete ephemeral metadata files

Files like `*.lastUpdated`, `resolver-status.properties`, and
`_remote.repositories` contain download timestamps and local path references.
These are removed in the FOD `installPhase`. This matches the pattern used by
nixpkgs' [`build-maven-package.nix`][nixpkgs-maven].

### 2. Delete `maven-metadata-*.xml`

These files contain `<lastUpdated>` timestamp elements that vary between builds.
Removing them follows the approach used by other nixpkgs Maven packages (e.g.,
tuxguitar).

### 3. Set `-Dproject.build.outputTimestamp`

The Maven property `project.build.outputTimestamp=1980-01-01T00:00:02Z` forces
all build outputs (JARs, manifests) to use a fixed timestamp instead of the
current time. This is standard practice in nixpkgs (scenebuilder, nzbhydra2,
verapdf) and is documented in the [Maven reproducible builds guide][maven-repro].

### 4. `dontFixup = true`

Prevents Nix fixup phases (strip, patchelf, etc.) from modifying the FOD output,
which could introduce non-determinism.

### References

- [nixpkgs Java/Maven documentation][nixpkgs-java]
- [nixpkgs `build-maven-package.nix`][nixpkgs-maven] — canonical Maven FOD pattern
- [Maven reproducible builds guide][maven-repro] — documents `project.build.outputTimestamp`
- [Reproducible Builds: JVM][repro-jvm] — JVM-specific reproducibility guidance
- [nixpkgs #278518][nixpkgs-issue] — tracking issue for non-deterministic Java apps

[nixpkgs-java]: https://nixos.org/manual/nixpkgs/stable/#sec-language-java
[nixpkgs-maven]: https://github.com/NixOS/nixpkgs/blob/master/pkgs/by-name/ma/maven/build-maven-package.nix
[maven-repro]: https://maven.apache.org/guides/mini/guide-reproducible-builds.html
[repro-jvm]: https://reproducible-builds.org/docs/jvm/
[nixpkgs-issue]: https://github.com/NixOS/nixpkgs/issues/278518

## Updating the FOD Hash

When Maven dependencies change (e.g., after bumping a version in a `pom.xml`),
the `outputHash` in `nix/maven-deps.nix` must be updated:

1. Set the hash to a placeholder:
   ```nix
   outputHash = lib.fakeHash;
   ```

2. Attempt a build:
   ```bash
   nix build 2>&1
   ```

3. The build will fail with a hash mismatch. Copy the `got:` hash from the
   error message.

4. Replace `lib.fakeHash` with the real hash:
   ```nix
   outputHash = "sha256-XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX=";
   ```

5. Rebuild to confirm:
   ```bash
   nix build
   ```
