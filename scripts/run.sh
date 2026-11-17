#!/bin/bash
set -euo pipefail

repo_dir="$(cd "$(dirname "$0")/.." && pwd)"
rebuild=false
case "${1:-}" in
    --rebuild) rebuild=true; shift ;;
    -h|--help)
        echo 'Uso: scripts/run.sh [--rebuild] [--] [argumentos de Modelio]'
        echo 'Requiere macOS Apple Silicon y JDK 27; Maven 3.9.9+ para reconstruir.'
        echo 'Descarga dependencias faltantes en .cache/modelio/maven y arranca Modelio.'
        exit 0 ;;
esac
if [[ "${1:-}" == -- ]]; then shift; fi
if [[ "$(uname -s)" != Darwin || "$(uname -m)" != arm64 ]]; then
    echo 'Este producto está configurado para macOS Apple Silicon (ARM64).' >&2
    exit 1
fi
if [[ -z "${JAVA_HOME:-}" ]]; then
    JAVA_HOME="$(/usr/libexec/java_home -v 27 2>/dev/null || true)"
    if [[ -z "$JAVA_HOME" && -x /opt/homebrew/opt/openjdk/libexec/openjdk.jdk/Contents/Home/bin/java ]]; then
        JAVA_HOME=/opt/homebrew/opt/openjdk/libexec/openjdk.jdk/Contents/Home
    fi
fi
if [[ ! -x "${JAVA_HOME:-}/bin/java" || ! -x "${JAVA_HOME:-}/bin/javac" ]]; then
    echo 'Instala un JDK 27 y configura JAVA_HOME (consulta BUILDING.md).' >&2
    exit 1
fi
java_settings="$("$JAVA_HOME/bin/java" -XshowSettings:properties -version 2>&1)"
if ! grep -Eq 'java.specification.version = 27$' <<< "$java_settings" || \
   ! grep -Eq 'os.arch = aarch64$' <<< "$java_settings"; then
    echo 'JAVA_HOME debe apuntar a un JDK 27 ARM64 (consulta BUILDING.md).' >&2
    exit 1
fi
export JAVA_HOME
export PATH="$JAVA_HOME/bin:$PATH"
export ECLIPSE_WS="$repo_dir"
cd "$repo_dir"
app="$repo_dir/products/opensource/target/products/org.modelio.product/macosx/cocoa/aarch64/Modelio 6.2.app"
launcher="$app/Contents/MacOS/modelio"
stamp="$repo_dir/products/opensource/target/.run-build-complete"
if "$rebuild" || [[ ! -x "$launcher" || ! -f "$stamp" ]]; then
    if pgrep -x modelio >/dev/null; then
        echo 'Cierra Modelio antes de reconstruir.' >&2
        exit 1
    fi
    if ! command -v mvn >/dev/null; then
        echo 'Instala Maven 3.9.9 o posterior (consulta BUILDING.md).' >&2
        exit 1
    fi
    rm -f "$stamp"
    mkdir -p "$repo_dir/.cache/modelio/maven" "$repo_dir/.cache/modelio/tmp"
    echo 'Compilando Modelio; Maven descargará las dependencias faltantes...'
    mvn -B -ntp -U "-Dmaven.repo.local=$repo_dir/.cache/modelio/maven" \
        "-Djava.io.tmpdir=$repo_dir/.cache/modelio/tmp" \
        -f maven/aggregators/opensource/pom.xml -Ppackage.all clean verify
    if [[ ! -x "$launcher" ]]; then
        echo "La compilación no generó el ejecutable esperado: $launcher" >&2
        exit 1
    fi
    touch "$stamp"
fi
exec "$launcher" -vm "$JAVA_HOME/bin/java" -clean "$@"
