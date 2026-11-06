#!/usr/bin/env bash
# build-modelio.sh -- command-line build of Modelio (open-source edition) and,
# optionally, of the modules found in a sibling ExtensionsForModelio checkout.
#
# This is a scripted version of the procedure of the wiki page
# "Package_modelio" (the Maven/Tycho build).  The "Compile_modelio" wiki page
# describes the older Eclipse-IDE workflow, which is only needed to develop or
# debug Modelio inside Eclipse.  See BUILDING.asciidoc for details.
#
# Usage: ./build-modelio.sh [options]
#
#   -p, --platform P   linux | win | mac | all   (default: native platform)
#                        all   = official release packaging (linux + win)
#                        linux = linux.gtk.x86_64 only
#                        win   = win32.win32.x86_64 only
#                        mac   = macosx.cocoa.aarch64 (Apple Silicon), see
#                                BUILDING.asciidoc -- not an upstream target
#   -m, --modules      also build the modules of ../ExtensionsForModelio
#                        (JavaDesigner, SysMLArchitect, TogafArchitect)
#   -M, --modules-only build only the modules (no Modelio build)
#       --extensions D path of the ExtensionsForModelio checkout
#                        (default: ../ExtensionsForModelio)
#   -o, --offline      pass -o to maven (everything already downloaded)
#   -n, --no-clean     don't run the 'clean' phase
#   -h, --help         show this help
#
# Environment:
#   JAVA_HOME    a JDK 21 (default: /usr/libexec/java_home -v 21 on macOS)
#   MVN          maven command (default: mvn; Maven >= 3.9 required by Tycho 4)
#
# Results:
#   products/opensource/target/products/*.tar.gz|*.zip   Modelio distributions
#   products/opensource/target/repository                 p2 repository
#   <Module>/target/<Module>_<version>.jmdac              modules (add them
#                                                          to the catalog with
#                                                          Configuration >
#                                                          Modules catalog)
#   build-logs/*.log                                      maven logs

set -euo pipefail

here="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
platform=""
build_modelio=1
build_modules=0
extensions="$(cd "$here/.." && pwd)/ExtensionsForModelio"
mvn_opts=(-B -Dmaven.test.skip=true)
phases=(clean install)

usage() { sed -n '2,/^$/{s/^# \{0,1\}//;p;}' "$0"; }

while [ $# -gt 0 ]; do
    case "$1" in
        -p|--platform)     platform="$2"; shift ;;
        -m|--modules)      build_modules=1 ;;
        -M|--modules-only) build_modules=1; build_modelio=0 ;;
        --extensions)      extensions="$2"; shift ;;
        -o|--offline)      mvn_opts+=(-o) ;;
        -n|--no-clean)     phases=(install) ;;
        -h|--help)         usage; exit 0 ;;
        *) echo "Unknown option: $1" >&2; usage >&2; exit 2 ;;
    esac
    shift
done

if [ -z "$platform" ]; then
    case "$(uname -s)" in
        Darwin) platform=mac ;;
        Linux)  platform=linux ;;
        *)      platform=win ;;
    esac
fi

case "$platform" in
    all)   profiles="package.all" ;;
    linux) profiles="product.org,platform.linux" ;;
    win)   profiles="product.org,platform.win" ;;
    mac)   profiles="platform.mac" ;;
    *) echo "Unknown platform: $platform" >&2; exit 2 ;;
esac

# --- toolchain --------------------------------------------------------------

if [ -z "${JAVA_HOME:-}" ] && [ -x /usr/libexec/java_home ]; then
    JAVA_HOME="$(/usr/libexec/java_home -v 21 2>/dev/null || true)"
fi
if [ -z "${JAVA_HOME:-}" ]; then
    echo "Please set JAVA_HOME to a JDK 21." >&2; exit 1
fi
export JAVA_HOME
export PATH="$JAVA_HOME/bin:$PATH"
MVN="${MVN:-mvn}"

java_major="$("$JAVA_HOME/bin/java" -XshowSettings:properties -version 2>&1 \
              | sed -n 's/^ *java.specification.version = //p')"
if [ "$java_major" != 21 ]; then
    echo "JAVA_HOME=$JAVA_HOME is Java $java_major; Modelio 6.2 needs Java 21." >&2
    exit 1
fi
mvn_version="$("$MVN" -v 2>/dev/null | sed -n 's/^Apache Maven \([0-9.]*\).*/\1/p')"
case "$mvn_version" in
    3.9.*|3.1[0-9].*|[4-9].*) ;;
    *) echo "Maven >= 3.9 is required (found '${mvn_version:-none}')." >&2; exit 1 ;;
esac

logs="$here/build-logs"
mkdir -p "$logs"

echo "JAVA_HOME = $JAVA_HOME"
echo "Maven     = $mvn_version"

# --- Modelio ----------------------------------------------------------------

if [ "$build_modelio" = 1 ]; then
    export ECLIPSE_WS="$here"          # used by maven/modelio-parent/pom.xml
    log="$logs/modelio-$platform.log"
    echo "Building Modelio (profiles: $profiles), log in $log ..."
    start=$SECONDS
    ( cd "$here/maven/aggregators/opensource" &&
      "$MVN" "${mvn_opts[@]}" -P "$profiles" "${phases[@]}" ) > "$log" 2>&1 || {
        grep -E '^\[ERROR\]' "$log" | head -40 >&2
        echo "Modelio build FAILED, see $log" >&2; exit 1; }
    echo "Modelio built in $(( (SECONDS-start)/60 )) min:"
    ls -1 "$here/products/opensource/target/products/"*.{tar.gz,zip} 2>/dev/null || true
fi

# --- Modules (ExtensionsForModelio) -----------------------------------------
#
# The modules are plain Maven projects: they compile against the MDAKit API
# and use the modelio-maven-plugin, both downloaded from
# https://repository.modelio.org, so they don't need the Modelio build above.

if [ "$build_modules" = 1 ]; then
    if [ ! -d "$extensions" ]; then
        echo "No ExtensionsForModelio checkout at $extensions" >&2; exit 1
    fi
    for agg in "$extensions"/*/aggregator/pom.xml; do
        mod="$(basename "$(dirname "$(dirname "$agg")")")"
        log="$logs/module-$mod.log"
        echo "Building module $mod, log in $log ..."
        ( cd "$(dirname "$agg")" &&
          "$MVN" "${mvn_opts[@]}" "${phases[@]}" ) > "$log" 2>&1 || {
            grep -E '^\[ERROR\]' "$log" | head -40 >&2
            echo "Module $mod FAILED, see $log" >&2; exit 1; }
        ls -1 "$extensions/$mod/target/"*.jmdac 2>/dev/null || true
    done
fi
