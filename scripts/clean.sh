#!/bin/bash
set -euo pipefail

repo_dir="$(cd "$(dirname "$0")/.." && pwd)"
dry_run=false
runtime=false
for arg in "$@"; do
    case "$arg" in
        --dry-run) dry_run=true ;;
        --runtime-cache) runtime=true ;;
        -h|--help)
            echo 'Uso: scripts/clean.sh [--dry-run] [--runtime-cache]'
            echo 'Elimina target/ de módulos Maven y .cache/modelio (dependencias y temporales).'
            echo '--runtime-cache incluye la caché OSGi de Modelio 6.2 del usuario.'
            exit 0 ;;
        *) echo "Opción desconocida: $arg" >&2; exit 2 ;;
    esac
done
cd "$repo_dir"
git rev-parse --is-inside-work-tree >/dev/null
if ! "$dry_run" && pgrep -x modelio >/dev/null; then
    echo 'Cierra Modelio antes de limpiar.' >&2
    exit 1
fi

remove_generated() {
    local path="$1"
    [[ -e "$path" || -L "$path" ]] || return 0
    # Some target directories in this repository contain versioned files.
    if [[ -n "$(git ls-files -- "$path/")" ]]; then
        echo "Conservado (contiene archivos versionados): $path"
        return 0
    fi
    du -sh "$path"
    if ! "$dry_run"; then
        rm -rf -- "$path"
    fi
}

# Do not traverse workspaces, bundled libraries, symlinks or arbitrary user folders.
while IFS= read -r -d '' path; do
    if [[ -f "${path%/target}/pom.xml" ]]; then
        remove_generated "$path"
    fi
done < <(find modelio modelio-lib maven features products doc \
    -type d -name target -prune -print0)
remove_generated .cache/modelio
if "$runtime"; then
    cache="$HOME/.modelio/6.2/opensource-cache/conf/org.eclipse.osgi"
    if [[ -e "$cache" || -L "$cache" ]]; then
        du -sh "$cache"
        if ! "$dry_run"; then rm -rf -- "$cache"; fi
    fi
fi
if "$dry_run"; then
    echo 'Vista previa: no se eliminó ningún archivo.'
else
    echo 'Limpieza terminada. Puedes ejecutar scripts/run.sh para reconstruir y arrancar.'
fi
