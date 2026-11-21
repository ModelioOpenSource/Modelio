# Run inside Modelio with -create <fresh-project> -batch <this-file>.
# This is Jython 2.7 code, using the bindings supplied by Modelio's batch runner.
from java.lang import System
import json

assert System.getProperty('java.specification.version') == '27'
assert modelingSession is not None

model = modelingSession.getModel()
roots = list(model.getModelRoots())
project = next(root for root in roots if root.getMClass().getName() == 'Project')
root_package = project.getModel().get(0)

transaction = modelingSession.createTransaction('Java 27 smoke test')
try:
    package = model.createPackage('Jdk27SmokePackage', root_package)
    element = model.createClass('Jdk27SmokeClass', package)
    diagram = model.createClassDiagram('Jdk27SmokeDiagram', package, None)
    transaction.commit()
finally:
    transaction.close()

coreSession.save(None)
print('JDK27_SCRIPT_OK ' + json.dumps({
    'java': str(System.getProperty('java.version')),
    'package': str(package.getUuid()),
    'class': str(element.getUuid()),
    'diagram': str(diagram.getUuid())
}))
