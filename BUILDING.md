# Building and running with JDK 27

## Requirements

- An ARM64 JDK 27 for macOS Apple Silicon.
- Maven 3.9.9 or newer (including Homebrew Maven 3.10).
- Internet access for Maven dependencies. The Eclipse target platform is in
  `dev-platform/rcp-target` in this checkout.

The build uses Tycho 5.0.4 and the JDK running Maven, without requiring a separate
Java 21 toolchain. The source and bytecode level remains Java 21; using JDK 27
does not require changing the application's language level.

For Homebrew's unversioned OpenJDK installation:

```sh
export JAVA_HOME="$(brew --prefix openjdk)/libexec/openjdk.jdk/Contents/Home"
export PATH="$JAVA_HOME/bin:$PATH"
mvn --version
```

Check that Maven reports Java 27 and architecture `aarch64`.

## Build the macOS application

Run from the repository root:

```sh
export ECLIPSE_WS="$PWD"
mvn -B -ntp -f maven/aggregators/opensource/pom.xml -Ppackage.all clean verify
```

Use this aggregator rather than the legacy root `pom.xml`. In this branch,
`package.all` selects macOS/Cocoa/aarch64. Build products and archives are written
under `products/opensource/target/products`.

Maven 3.10's stricter artifact validation conflicts with the classifiers Tycho
generates for nested JARs. `.mvn/maven.config` selects `MILD` validation as the
workaround described in [Tycho issue #6399](https://github.com/eclipse-tycho/tycho/issues/6399).

## Run

### Install, update or uninstall on macOS

After building, quit Modelio and run:

```sh
bash scripts/install-macos.sh
```

This installs an independent copy in `~/Applications/Modelio 6.2.app`, registers
it with macOS, and configures the installed app to use JDK 27. Open it from
Finder's **Go > Home > Applications**. Later builds do not modify this installed
copy. To update, rebuild and run the installer again; the previous app is moved
to the Trash before the replacement is installed.

To uninstall, quit Modelio and move `~/Applications/Modelio 6.2.app` to the
Trash. Projects (by default `~/modelio/workspace`) and preferences/caches
(`~/.modelio/6.2`) are separate and are retained. The JDK is shared with other
applications and is retained as well.

### Run directly from the build directory

The product does not bundle a JRE. Supply the installed JDK explicitly with the
Eclipse launcher's `-vm` option, before any `-vmargs` options. Use a separate
workspace when testing an upgrade.

```sh
app="products/opensource/target/products/org.modelio.product/macosx/cocoa/aarch64/Modelio 6.2.app"
mkdir -p /tmp/modelio-jdk27-projects
"$app/Contents/MacOS/modelio" -vm "$JAVA_HOME/bin/java" \
  -data /tmp/modelio-jdk27-eclipse -workspace /tmp/modelio-jdk27-projects \
  -consoleLog
```

`-workspace` selects Modelio's project directory; `-data` selects the Eclipse
instance directory. They serve different purposes.

The integrated MCP server starts with the application on loopback port 7341,
at `/mcp`. Set `MODELIO_MCP_PORT` to change the port, or to `0` to disable it.

## Runtime smoke checks

Use a fresh project name. This creates and saves a package, a UML class and a
class diagram through Modelio's Jython engine, asserting that Java 27 is running:

```sh
"$app/Contents/MacOS/modelio" -vm "$JAVA_HOME/bin/java" \
  -workspace /tmp/modelio-jdk27-projects -create Jdk27Validation \
  -batch "$PWD/tests/runtime/jdk27-smoke.py" -consoleLog
```

The batch process should exit with code 0 and print `JDK27_SCRIPT_OK`. Reopen the
project with `-open Jdk27Validation` instead of `-create` and `-batch`, then run
the read-only MCP checks using Python 3:

```sh
python3 tests/runtime/mcp-smoke.py --project Jdk27Validation
```

These checks exercise Jython, model transactions, persistence, MCP search and
diagram reads. They are smoke checks, rather than a complete application test
suite. Eclipse's bundled OSGi framework still emits a deprecated `sun.misc.Unsafe`
warning on Java 27. Batch mode also logs existing UI dependency-injection
messages while creating and closing the workbench; check the exit code and
smoke marker to determine whether the script completed.
