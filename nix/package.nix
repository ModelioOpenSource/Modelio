{ lib, stdenv, jdk, maven, jdk11, makeWrapper, wrapGAppsHook3, autoPatchelfHook
, gtk3, glib, xorg, freetype, fontconfig, zlib, libsecret, webkitgtk_4_1, libGL
, alsa-lib, copyDesktopItems, makeDesktopItem, modelio-src, mvnDeps, }:

let
  shared = import ./lib.nix { inherit jdk11; };

  libs = shared.runtimeLibs {
    inherit gtk3 glib xorg freetype fontconfig zlib libsecret webkitgtk_4_1
      libGL alsa-lib stdenv;
  };

  src = lib.cleanSourceWith {
    src = ./..;
    filter = path: type:
      let relPath = lib.removePrefix (toString ./..) path;
      in !(lib.hasPrefix "/nix" relPath) && !(lib.hasPrefix "/flake" relPath)
      && !(lib.hasPrefix "/.git" relPath) && !(lib.hasSuffix ".nix" relPath)
      && !(lib.hasSuffix "flake.lock" relPath);
  };
in stdenv.mkDerivation {
  pname = "modelio";
  inherit (shared) version;
  inherit src;

  nativeBuildInputs = [
    maven
    jdk11
    makeWrapper
    wrapGAppsHook3
    autoPatchelfHook
    copyDesktopItems
  ];

  buildInputs = libs;

  dontWrapGApps = true;

  buildPhase = ''
    runHook preBuild

    export HOME=$(mktemp -d)

    ${shared.setupToolchains}
    ${shared.fixBuildProperties}

    # Copy cached deps to writable location (Tycho needs to create .tycholock files)
    cp -r ${mvnDeps}/.m2/repository $HOME/.m2/repository
    chmod -R u+w $HOME/.m2/repository

    mvn install \
      -f AGGREGATOR/pom.xml \
      -t $HOME/.m2/toolchains.xml \
      -Dmaven.repo.local=$HOME/.m2/repository \
      --offline \
      -DskipTests \
      -Dtycho.localArtifacts=ignore \
      --batch-mode

    mvn package \
      -f products/pom.xml \
      -Pproduct.org,platform.linux \
      -t $HOME/.m2/toolchains.xml \
      -Dmaven.repo.local=$HOME/.m2/repository \
      --offline \
      -DskipTests \
      --batch-mode

    runHook postBuild
  '';

  installPhase = ''
    runHook preInstall

    tarball=$(find products/target/products -name '*linux*tar.gz' | head -1)
    if [ -z "$tarball" ]; then
      echo "ERROR: No linux tarball found in products/target/products/"
      find products/target/ -name '*.tar.gz' || true
      exit 1
    fi

    mkdir -p $out/lib
    tar xf "$tarball" -C $out/lib
    mv "$out/lib/Modelio ${shared.version}" $out/lib/modelio

    # Replace bundled JRE with Nix JDK
    rm -rf $out/lib/modelio/jre
    ln -s ${jdk} $out/lib/modelio/jre

    mkdir -p $out/bin
    makeWrapper $out/lib/modelio/modelio $out/bin/modelio \
      "''${gappsWrapperArgs[@]}" \
      --set JAVA_HOME "${jdk}" \
      --prefix LD_LIBRARY_PATH : "${lib.makeLibraryPath libs}" \
      --add-flags "-Xms1024m" \
      --add-flags "-Xmx4096m" \
      --add-flags "-Dpython.console.encoding=UTF-8" \
      --add-flags "-Dorg.eclipse.swt.internal.webkitgtk.ignoretlserrors=true"

    install -Dm644 products/icons/modelio.xpm $out/share/pixmaps/modelio.xpm

    runHook postInstall
  '';

  desktopItems = [
    (makeDesktopItem {
      name = "modelio";
      desktopName = "Modelio";
      exec = "modelio";
      icon = "modelio";
      comment = "Open-source UML/BPMN modeling tool";
      categories = [ "Development" "IDE" ];
    })
  ];

  meta = {
    description = "Open-source modeling environment (UML, BPMN, ArchiMate)";
    homepage = "https://www.modelio.org";
    license = lib.licenses.asl20;
    platforms = [ "x86_64-linux" ];
    mainProgram = "modelio";
  };
}
