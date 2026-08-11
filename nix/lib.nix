{ jdk11 }: {
  version = "5.4.1";

  # Tycho needs toolchain entries for both JavaSE-1.8 and JavaSE-11 BREE declarations
  setupToolchains = ''
    mkdir -p $HOME/.m2
    cat > $HOME/.m2/toolchains.xml <<XML
    <?xml version="1.0" encoding="UTF-8"?>
    <toolchains xmlns="http://maven.apache.org/TOOLCHAINS/1.1.0"
      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xsi:schemaLocation="http://maven.apache.org/TOOLCHAINS/1.1.0
        http://maven.apache.org/xsd/toolchains-1.1.0.xsd">
      <toolchain>
        <type>jdk</type>
        <provides>
          <version>1.8</version>
          <vendor>adoptOpenJDK</vendor>
          <id>JavaSE-1.8</id>
        </provides>
        <configuration>
          <jdkHome>${jdk11}</jdkHome>
        </configuration>
      </toolchain>
      <toolchain>
        <type>jdk</type>
        <provides>
          <version>11</version>
          <vendor>adoptOpenJDK</vendor>
          <id>JavaSE-11</id>
        </provides>
        <configuration>
          <jdkHome>${jdk11}</jdkHome>
        </configuration>
      </toolchain>
    </toolchains>
    XML
    export JAVA_HOME="${jdk11}"
  '';

  # ~26 directories in build.properties bin.includes aren't tracked by git
  fixBuildProperties = ''
    find . -name build.properties -exec sh -c '
      dir=$(dirname "$1")
      grep -A100 "^bin\.includes" "$1" | \
        sed "/^[a-zA-Z]/{ /^bin\.includes/!q; }" | \
        tr "," "\n" | sed "s/.*=//; s/\\\\//; s/^[[:space:]]*//; s/[[:space:]]*$//" | \
        grep "/" | grep -v "^\." | while read -r entry; do
          entry="''${entry%/}"
          [ ! -e "$dir/$entry" ] && mkdir -p "$dir/$entry"
        done
    ' _ {} \;
  '';

  # Native libraries needed at runtime (GTK, X11, WebKit, etc.)
  runtimeLibs = { gtk3, glib, xorg, freetype, fontconfig, zlib, libsecret
    , webkitgtk_4_1, libGL, alsa-lib, stdenv, }: [
      gtk3
      glib
      xorg.libX11
      xorg.libXtst
      xorg.libXrender
      xorg.libXi
      xorg.libXext
      xorg.libXxf86vm
      freetype
      fontconfig
      zlib
      libsecret
      webkitgtk_4_1
      libGL
      alsa-lib
      stdenv.cc.cc.lib
    ];
}
