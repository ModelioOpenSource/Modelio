# AGENTS.md

Guía para agentes y personas que continúen el trabajo en este repositorio: cómo
compilar y probar Modelio 6.2 con JDK 27 en macOS, Linux y Windows, qué se
descubrió al hacerlo y qué queda pendiente.

Complementa a [BUILDING.md](BUILDING.md), que describe el flujo oficial en macOS
Apple Silicon. Si algo de aquí contradice a BUILDING.md, verifica en el código
cuál está vigente y actualiza ambos.

## Estado actual (noviembre 2026)

| Plataforma | Compila | Probado | Notas |
|---|---|---|---|
| macOS aarch64 | Sí, según BUILDING.md | `scripts/run.sh` / `install-macos.sh` | Re-validar tras los cambios de esta guía |
| Windows x86_64 | Sí | Instalado y ejecutado con JDK 27.0.1 | Documentación compila con JDK 27 desde asciidoctor 2.2.6 |
| Linux x86_64 | Sin probar | Sin probar | Perfil `platform.linux` existe |

Cambios ya fusionados en `master`:

- **PR #5**: `dev-platform/rcp-target/rcp.target` usa `${env_var:ECLIPSE_WS}/dev-platform/...`
  en lugar de `${project_loc:/...}` o rutas fijas `C:/...`.
- **PR #6**: la ventana *Help → About* muestra `Java: <java.runtime.version> (<java.vendor>)`
  (`modelio/app/app.ui/src/org/modelio/app/ui/about/AboutDialog.java`, clave `$About.Java`
  en `modelio/app/app.ui.ext_org/res/appui-about*.properties`).

## Requisitos comunes

- **JDK 27** para ejecutar Maven y Modelio. El código se compila con nivel de
  lenguaje 21; el producto **no incluye JRE** (`include="false"` en
  `products/opensource/modelio-os.product`), así que necesita un JDK instalado.
- **Maven 3.9.9+**. `.mvn/maven.config` fija `-Dmaven.resolver.validation=MILD`
  (necesario con Maven 3.10, ver BUILDING.md).
- **Tycho 5.0.4** (`maven/modelio-parent/pom.xml`).
- Variable **`ECLIPSE_WS`** = raíz absoluta del repositorio. La usan:
  - `rcp.target` → `${env_var:ECLIPSE_WS}` (Tycho **y** Eclipse PDE).
  - `maven/modelio-parent/pom.xml` → `modelio.ws.path=${env.ECLIPSE_WS}` para los
    repositorios p2 `file://${modelio.ws.path}/dev-platform/...`.
  - En Eclipse IDE hay que definirla **antes** de abrir el IDE; si no, la target
    platform no resuelve.

## Desarrollar y ejecutar desde Eclipse

> **Agentes: avisa siempre al usuario** de estos requisitos antes de que abra
> Eclipse o cuando reporte errores de target platform, proyectos que faltan o un
> `.project` en la raíz del repo. No los apliques en silencio: díselos.

1. **Workspace de Eclipse fuera del repositorio**, en una carpeta hermana:
   - Repo: `C:/Proyectos/Modelio` → workspace: `C:/Proyectos/Modelio-ws`
     (en Mac/Linux, `~/Proyectos/Modelio` → `~/Proyectos/Modelio-ws`).
   - **No** uses la raíz del repo como workspace ni importes la raíz como un único
     proyecto: deja `.metadata` y un `.project` raíz dentro de git (ya ocurrió).
2. **Define `ECLIPSE_WS` con la raíz del repo antes de abrir Eclipse.** El IDE solo
   lee variables de entorno al arrancar:
   - Windows: variable de usuario `ECLIPSE_WS=C:/Proyectos/Modelio` y reinicia
     Eclipse.
   - macOS: las apps lanzadas desde Finder no heredan la shell; usa
     `launchctl setenv ECLIPSE_WS "$HOME/Proyectos/Modelio"` o abre Eclipse desde
     una terminal con la variable exportada.
   - Linux: expórtala en el perfil de sesión o lanza Eclipse desde la terminal.
3. Ejecuta Eclipse con **JDK 27** y declara un JRE "JavaSE-21" o superior en
   *Preferences → Java → Installed JREs* (el código compila a nivel 21).
4. *File → Import → Existing Projects into Workspace*, raíz = carpeta del repo,
   con **"Search for nested projects"** activado y **sin** "Copy projects into
   workspace". Como mínimo importa `dev-platform/rcp-target`,
   `dev-platform/test-resources`, `dev-platform/pack-resources` y los proyectos de
   `modelio/` que vayas a tocar. No importes un proyecto para la raíz del repo.
5. Abre `dev-platform/rcp-target/rcp.target` y pulsa **Set as Active Target
   Platform**. Si falla al resolver rutas `${env_var:ECLIPSE_WS}/...`, la variable
   no llegó a Eclipse (paso 2).
6. Lanza el producto desde `products/opensource/modelio-os.product` (*Launch an
   Eclipse application*) o con una configuración de ejecución. Añade
   `-consoleLog` y usa un `-workspace` de Modelio de pruebas separado.

## Compilar y probar por plataforma

Siempre usa el agregador `maven/aggregators/opensource/pom.xml`, no el `pom.xml`
raíz. El producto queda en
`products/opensource/target/products/org.modelio.product/<os>/<ws>/<arch>/`.

### macOS (Apple Silicon)

Sigue [BUILDING.md](BUILDING.md):

```sh
./scripts/run.sh --rebuild          # compila con -Ppackage.all y ejecuta
bash scripts/install-macos.sh       # instala en ~/Applications/Modelio 6.2.app
```

- `-Ppackage.all` en `products/opensource/pom.xml` **solo** genera
  `macosx/cocoa/aarch64` (a pesar de su nombre y del comentario "all").
- `run.sh` usa un repositorio Maven propio en `.cache/modelio/maven`, aislado de
  `~/.m2`. Esto evita el problema de bundles antiguos descrito más abajo.
- No hay perfil `platform.mac` aunque el comentario del pom lo mencione.

Para validar tras los cambios fusionados:

1. `./scripts/clean.sh && ./scripts/run.sh --rebuild` debe compilar sin errores
   (confirma que `${env_var:ECLIPSE_WS}` resuelve; `run.sh` exporta `ECLIPSE_WS`).
2. Revisar si `org.modelio.documentation.modeliomodeler` compila con JDK 27 en Mac
   (ver tareas pendientes).
3. Abrir *Modelio → About* y comprobar la línea `Java: 27...`.
4. Ejecutar los smoke tests de BUILDING.md (`jdk27-smoke.py`, `mcp-smoke.py`).

### Linux (x86_64)

No se ha probado. Comando esperado (equivalente al de Windows):

```sh
export JAVA_HOME=/ruta/al/jdk-27
export PATH="$JAVA_HOME/bin:$PATH"
export ECLIPSE_WS="$PWD"
mvn -B -ntp -f maven/aggregators/opensource/pom.xml -Pproduct.org,platform.linux clean verify
```

En Linux `file://${modelio.ws.path}` con una ruta `/home/...` produce
`file:///home/...`, válido; no debería necesitar el ajuste de Windows.

Ejecutar desde el directorio del producto, indicando el JDK:

```sh
app="products/opensource/target/products/org.modelio.product/linux/gtk/x86_64/Modelio 6.2"
"$app/modelio" -vm "$JAVA_HOME/bin/java" -consoleLog
```

Validar: arranque, *Help → About* (línea Java), servidor MCP en
`http://127.0.0.1:7341/mcp`, y los smoke tests de BUILDING.md.

### Windows (x86_64)

Probado el 2026-11-17 con Temurin 27.0.1 y Maven 3.9.16. Diferencias respecto a
macOS/Linux:

1. Perfiles: `-Pproduct.org,platform.win` (`package.all` solo hace Mac). En
   PowerShell **pon el argumento entre comillas** (`'-Pproduct.org,platform.win'`),
   o PowerShell lo parte en el punto.
2. `file://${modelio.ws.path}` con `C:/...` genera `file://C:/...` (URI inválida:
   *"URI has an authority component"*). Pasar
   `-Dmodelio.ws.path=/C:/ruta/al/repo` → `file:///C:/...`. `ECLIPSE_WS` se queda
   como `C:/ruta/al/repo` (lo usa `rcp.target`).
3. La documentación compila con JDK 27 desde que `asciidoctor.version` es 2.2.6
   (antes 1.5.7.1 obligaba a una segunda pasada con JDK 21).
4. **Usar `install`, no `verify`**. Con `-rf` los módulos anteriores salen del
   reactor y Tycho los toma de `~/.m2`; si allí hay una versión anterior, el
   producto se arma con bundles viejos (pasó con `org.modelio.app.ui`).
   Alternativa: un repositorio local dedicado con `-Dmaven.repo.local=...`, como
   hace `run.sh`.

```powershell
$env:ECLIPSE_WS = 'C:/Proyectos/Modelio'
$common = '-B','-ntp','-f','maven/aggregators/opensource/pom.xml','-Pproduct.org,platform.win','-Dmodelio.ws.path=/C:/Proyectos/Modelio'

$env:JAVA_HOME = 'C:\Program Files\Eclipse Adoptium\jdk-27.0.1.0-hotspot'
mvn @common clean install
```

Instalación local usada: copiar el producto a
`%LOCALAPPDATA%\Programs\Modelio 6.2` y añadir en `modelio.ini`, antes de
`-vmargs`:

```
-vm
C:\Program Files\Eclipse Adoptium\jdk-27.0.1.0-hotspot\bin\javaw.exe
```

Cierra Modelio por completo antes de reemplazarlo: la JVM (`javaw.exe`) puede
seguir viva tras cerrar `modelio.exe` y bloquea los DLL del launcher.

## Hallazgos

- **`${project_loc:...}` no funciona en Maven/Tycho**: solo lo resuelve el IDE.
  Fue la causa de que alguien sustituyera las rutas por `C:/Proyectos/...`, lo que
  rompía el resto de plataformas. Solución fusionada: `${env_var:ECLIPSE_WS}`.
- **No subir rutas absolutas** ni archivos de entorno local: `.project` en la
  raíz, `modelio-lib/modelio-maven-plugin/target/` (está versionado y genera
  conflictos de merge en `.class`; candidato a `.gitignore` + `git rm --cached`).
- **No eliminar entornos ajenos** en `products/opensource/pom.xml` ni en
  `maven/modelio-parent/pom.xml` al añadir Mac: añadir, no sustituir.
- **JRE embebido retirado a propósito** con la migración a JDK 27
  (`feature/upgrade-jdk27`): no restaurar `net.adoptium.temurin.jre.feature`
  (Temurin 21) del `.product`.
- **asciidoctor-maven-plugin 1.5.7.1 + JRuby 9.1.17.0 fallaba con JDK 27 en
  Windows** (resuelto migrando a 2.2.6, AsciidoctorJ 2.5 / JRuby 9.4; se quitó
  `<compact>` de los 27 poms porque ya no existe): `ClassCastException ... ValueLayouts$OfIntImpl cannot be cast to
  ValueLayout$OfLong` (FFI; en Windows `long` es de 32 bits) o
  `ExceptionInInitializerError`. Forzar `jruby-complete` 9.2.21.0 o 9.4.12.0 vía
  `pluginManagement` **no sirve**: asciidoctorj 1.5.7 llama a
  `ThreadContext.getBacktrace...`, que ya no existe (`NoSuchMethodError`).
- **La fase de la documentación está fija** (`<phase>generate-resources</phase>`)
  en cada `doc/plugins/*/*/pom.xml`; la propiedad `phase.generate-documentation`
  de `doc/parent/pom.xml` no la desactiva.
- El servidor MCP integrado escucha en `127.0.0.1:7341/mcp`
  (`MODELIO_MCP_PORT` para cambiarlo; `0` lo desactiva). Es una forma rápida de
  comprobar que la aplicación arrancó.

## Tareas pendientes

1. **Documentación con asciidoctor 2.2.6**: compila con JDK 27 en Windows.
   Pendiente: comparar el HTML con el generado por 1.5.7.1 y confirmar en
   macOS/Linux. Asciidoctor 2.x avisa "stylesheet does not exist" por cada
   página (inofensivo: `linkcss` enlaza `./modeliosoft.css`, que copia antrun).
2. **Validar macOS** con los cambios de `rcp.target` y About (pasos arriba).
3. **Probar Linux** de punta a punta y documentar el resultado aquí.
4. **`package.all`**: decidir si debe incluir Linux y Windows además de Mac, o
   renombrarlo; hoy solo produce Mac.
5. **`modelio.ws.path` en Windows**: hacerlo portable en `maven/modelio-parent/pom.xml`
   (p. ej. derivar una URL `file:` válida o una propiedad por perfil de SO) para no
   tener que pasar `-Dmodelio.ws.path=/C:/...`.
6. **`modelio-lib/modelio-maven-plugin/target/`**: sacarlo del control de versiones
   y añadirlo a `.gitignore`.
7. Las etiquetas `$About.*` vienen de la herramienta Pollux; añadir `$About.Java`
   en su fuente para que no se pierda si se regeneran los `.properties`.
