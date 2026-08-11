{ writeShellApplication, docker, xorg, container, }:

let version = "5.4.1";
in writeShellApplication {
  name = "modelio-container-run";
  runtimeInputs = [ docker xorg.xhost ];
  text = ''
    echo "Loading Modelio container image..."
    docker load < ${container}

    # Allow the container to connect to the host X server
    xhost +local:docker 2>/dev/null || true

    echo "Starting Modelio ${version} in Docker..."
    exec docker run --rm \
      -e DISPLAY="''${DISPLAY:-:0}" \
      -e XAUTHORITY=/tmp/.Xauthority \
      -v "''${XAUTHORITY:-$HOME/.Xauthority}:/tmp/.Xauthority:ro" \
      -v /tmp/.X11-unix:/tmp/.X11-unix \
      -v "''${HOME}/.modelio:/root/.modelio" \
      -v "''${PWD}:/workspace" \
      --network=host \
      modelio:${version}
  '';
}
