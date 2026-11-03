#!/bin/bash
set -euo pipefail

# Run after building with -Ppackage.all, with Modelio closed.
repo_dir="$(cd "$(dirname "$0")/.." && pwd)"
source_app="$repo_dir/products/opensource/target/products/org.modelio.product/macosx/cocoa/aarch64/Modelio 6.2.app"
applications_dir="$HOME/Applications"
destination="$applications_dir/Modelio 6.2.app"
jdk="$(/usr/libexec/java_home -v 27)"

if [[ ! -x "$source_app/Contents/MacOS/modelio" ]]; then
    echo 'Build the macOS product before installing it (see BUILDING.md).' >&2
    exit 1
fi
if pgrep -x modelio >/dev/null; then
    echo 'Quit Modelio before installing or updating it.' >&2
    exit 1
fi

mkdir -p "$applications_dir"
staging_dir="$(mktemp -d "$applications_dir/.modelio-install.XXXXXX")"
staged_app="$staging_dir/Modelio 6.2.app"
ditto "$source_app" "$staged_app"

# Use Homebrew's stable opt link so patch updates do not invalidate the path.
if [[ -d /opt/homebrew/opt/openjdk/libexec/openjdk.jdk/Contents/Home ]]; then
    jdk=/opt/homebrew/opt/openjdk/libexec/openjdk.jdk/Contents/Home
fi
ini="$staged_app/Contents/Eclipse/modelio.ini"
awk -v java="$jdk/bin/java" '
    skip { skip = 0; next }
    $0 == "-vm" { skip = 1; next }
    $0 == "-vmargs" { print "-vm"; print java }
    { print }
' "$ini" > "$ini.tmp"
mv "$ini.tmp" "$ini"

if [[ -e "$destination" ]]; then
    mkdir -p "$HOME/.Trash"
    previous="$HOME/.Trash/Modelio 6.2-$(date +%Y%m%d-%H%M%S)-$$.app"
    mv "$destination" "$previous"
    echo "Previous application moved to: $previous"
fi
mv "$staged_app" "$destination"
rmdir "$staging_dir"
/System/Library/Frameworks/CoreServices.framework/Frameworks/LaunchServices.framework/Support/lsregister -f "$destination"
echo "Installed: $destination"
echo 'To uninstall, quit Modelio and move this application to the Trash.'
echo 'Projects and user preferences are kept separately and are not removed.'
