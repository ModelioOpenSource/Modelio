{ lib, stdenv, maven, jdk11, modelio-src, }:

let shared = import ./lib.nix { inherit jdk11; };
in stdenv.mkDerivation {
  pname = "modelio-maven-deps";
  inherit (shared) version;
  src = modelio-src;

  nativeBuildInputs = [ maven jdk11 ];

  buildPhase = ''
    runHook preBuild

    export HOME=$(mktemp -d)

    ${shared.setupToolchains}
    ${shared.fixBuildProperties}

    mvn install \
      -f AGGREGATOR/pom.xml \
      -t $HOME/.m2/toolchains.xml \
      -Dmaven.repo.local=$out/.m2/repository \
      -DskipTests \
      -Dtycho.localArtifacts=ignore \
      -Dproject.build.outputTimestamp=1980-01-01T00:00:02Z \
      --batch-mode

    mvn package \
      -f products/pom.xml \
      -Pproduct.org,platform.linux \
      -t $HOME/.m2/toolchains.xml \
      -Dmaven.repo.local=$out/.m2/repository \
      -DskipTests \
      -Dproject.build.outputTimestamp=1980-01-01T00:00:02Z \
      --batch-mode

    runHook postBuild
  '';

  installPhase = ''
    runHook preInstall

    # Remove non-deterministic files (timestamps, repo metadata)
    find $out -type f \( \
      -name \*.lastUpdated \
      -o -name resolver-status.properties \
      -o -name _remote.repositories \
      -o -name "maven-metadata-*.xml" \) \
      -delete

    runHook postInstall
  '';

  dontFixup = true;
  outputHashAlgo = "sha256";
  outputHashMode = "recursive";
  outputHash = "sha256-yGzIWw8O8kiUuubkJnTRsrArz/q6FAXaYOn0rHVsqB4=";

  impureEnvVars = lib.fetchers.proxyImpureEnvVars;
}
