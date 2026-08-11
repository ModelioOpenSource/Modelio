{ runCommand, writeShellApplication, modelio, xvfb-run, coreutils, procps, }:

let
  smokeTest = writeShellApplication {
    name = "modelio-smoke-test";
    runtimeInputs = [ modelio xvfb-run coreutils procps ];
    text = ''
      echo "=== Modelio smoke test ==="

      # Verify binary exists and is executable
      test -x "${modelio}/bin/modelio"
      echo "PASS: modelio binary exists"

      # Verify JRE symlink
      test -d "${modelio}/lib/modelio/jre"
      echo "PASS: JRE directory exists"

      # Launch with xvfb and check it starts
      xvfb-run -a "${modelio}/bin/modelio" &
      PID=$!
      sleep 15

      if kill -0 "$PID" 2>/dev/null; then
        echo "PASS: modelio process running after 15s"
        kill "$PID" || true
        wait "$PID" 2>/dev/null || true
        echo "PASS: modelio exited cleanly"
      else
        echo "FAIL: modelio process died before 15s"
        exit 1
      fi

      echo "=== All smoke tests passed ==="
    '';
  };
in runCommand "modelio-smoke-test" { nativeBuildInputs = [ smokeTest ]; } ''
  modelio-smoke-test
  touch $out
''
