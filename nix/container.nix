{ dockerTools, modelio, bash, coreutils, cacert, }:

dockerTools.buildLayeredImage {
  name = "modelio";
  tag = modelio.version;

  contents = [ modelio bash coreutils cacert ];

  config = {
    Cmd = [ "${modelio}/bin/modelio" ];
    Env =
      [ "DISPLAY=:99" "SSL_CERT_FILE=${cacert}/etc/ssl/certs/ca-bundle.crt" ];
    Volumes = {
      "/workspace" = { };
      "/root/.modelio" = { };
    };
  };
}
