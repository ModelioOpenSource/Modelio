{ mkShell, maven, jdk11, gtk3, glib, xorg, freetype, fontconfig, zlib, libsecret
, }:

let shared = import ./lib.nix { inherit jdk11; };
in mkShell {
  packages = [ maven jdk11 ];

  buildInputs = [
    gtk3
    glib
    xorg.libX11
    xorg.libXtst
    xorg.libXrender
    freetype
    fontconfig
    zlib
    libsecret
  ];

  shellHook = ''
    ${shared.setupToolchains}

    echo "Modelio dev shell"
    echo "  JAVA_HOME=$JAVA_HOME"
    echo "  java:  $(java -version 2>&1 | head -1)"
    echo "  maven: $(mvn --version 2>&1 | head -1)"
    echo ""
    echo "Build steps:"
    echo "  1. mvn clean install -f AGGREGATOR/pom.xml"
    echo "  2. mvn package -f products/pom.xml -Pproduct.org,platform.linux"
    echo "  Output: products/target/products/*linux*.tar.gz"
  '';
}
