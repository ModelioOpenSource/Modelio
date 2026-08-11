# Modelio – open-source UML/BPMN modeling tool
#
# Usage:
#   nix build          Build Modelio
#   nix run            Build and launch Modelio
#   nix develop        Enter dev shell (Maven + JDK 11 + native libs)
#   nix build .#container        Build OCI container image
#   nix run .#container-run      Load and run container with Docker
#   nix flake check              Run smoke tests
#
# See ./nix/README.md for full documentation.
{
  description = "Modelio – open-source UML/BPMN modeling tool";

  inputs = {
    nixpkgs.url = "github:NixOS/nixpkgs/nixos-25.11";
    flake-utils.url = "github:numtide/flake-utils";

    # Pinned Modelio source for maven dependency fetching.
    # This ensures the maven deps FOD only changes when you explicitly
    # run: nix flake update modelio-src
    modelio-src = {
      url =
        "github:randomizedcoder/Modelio/53eb60381eac57b313ea862208b3d38652bf59b9";
      flake = false;
    };
  };

  outputs = { nixpkgs, flake-utils, modelio-src, ... }:
    flake-utils.lib.eachSystem [ "x86_64-linux" ] (system:
      let
        pkgs = nixpkgs.legacyPackages.${system};
        mvnDeps =
          pkgs.callPackage ./nix/maven-deps.nix { inherit modelio-src; };
        modelio =
          pkgs.callPackage ./nix/package.nix { inherit modelio-src mvnDeps; };
        container = pkgs.callPackage ./nix/container.nix { inherit modelio; };
      in {
        packages = {
          default = modelio;
          inherit modelio;
          inherit container;
          container-run =
            pkgs.callPackage ./nix/container-run.nix { inherit container; };
        };

        devShells.default = pkgs.callPackage ./nix/shell.nix { };

        checks.default = pkgs.callPackage ./nix/test.nix { inherit modelio; };

        formatter = pkgs.nixfmt-classic;
      });
}
