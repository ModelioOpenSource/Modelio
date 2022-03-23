#
# MakeSingleton  2.0
#
# Description:
#  This macro makes a singleton from an existing class.It can also create a new class if applied on a Package.
#  It actually creates a static getInstance() method, an self-association 'instance' and a private default constructor for the class.
#
# Applicable on: Class, Package
#
# Author:  Modeliosoft
#
# Version history:
# 1.0   25th August 2009   - creation
# 1.1   3rd September 2009 - when applied to a package, create a new class
# 2.0   10th June 2013     - update for Modelio 3.0
# 3.0   23rd November 2020 - Modelio 5.0, use the Java Architect module if Java Designer is missing
#

from org.modelio.metamodel.uml.statik import KindOfAccess
from org.modelio.metamodel.uml.statik import VisibilityMode
from org.modelio.metamodel.uml.statik import AggregationKind
from org.modelio.metamodel.uml.statik import Class
from org.modelio.metamodel.uml.statik import Package

def createClass(package):
	"""Create a class named 'Singleton' under 'package'"""
	clazz = modelingSession.getModel().createClass("Singleton", package)
	return clazz


def makeSingleton(clazz):
	"""Where the real work is done"""
	createInstanceAssociation(clazz)
	createPrivateConstructor(clazz)
	createGetInstanceMethod(clazz)


def createInstanceAssociation(clazz):
	"""Create the 'instance' association """
	# create and configure the source AssociationEnd
	source = modelingSession.getModel().createAssociationEnd()

  # create and configure the target AssociationEnd
	target = modelingSession.getModel().createAssociationEnd()
	target.setIsClass(1)
	target.setVisibility(VisibilityMode.PRIVATE)
	target.setChangeable(KindOfAccess.ACCESNONE)
	target.setAggregation(AggregationKind.KINDISCOMPOSITION)
	target.setMultiplicityMin("0")
	target.setMultiplicityMax("1")
	target.setName("Instance")
	target.setSource(clazz)
	target.setTarget(clazz)

	# create and bind the new association
	assoc  = modelingSession.getModel().createAssociation()
	assoc.setName("")
	source.setAssociation(assoc)
	target.setAssociation(assoc)
	source.setOpposite(target)
	target.setOpposite(source)

def createGetInstanceMethod(clazz):
	"""Create the 'getInstance()' method"""
	# create the operation
	op = modelingSession.getModel().createOperation("getInstance", clazz)
	op.setVisibility(VisibilityMode.PUBLIC)
	op.setIsClass(True)
	# create the return parameter
	returnParameter = modelingSession.getModel().createReturnParameter("", clazz, op)
	# create the code note
	if (Modelio.getInstance().getModuleService().getPeerModule("JavaDesigner")):
	  code = "return (instance == null)? (instance=new " + clazz.getName() + "()) : instance;"
	  note = modelingSession.getModel().createNote("JavaDesigner", "JavaCode", op, code)
	elif (Modelio.getInstance().getModuleService().getPeerModule("JavaArchitect")):
	  code = "return (instance == null)? (instance=new " + clazz.getName() + "()) : instance;"
	  note = modelingSession.getModel().createNote("JavaArchitect", "JavaCode", op, code)

def createPrivateConstructor(clazz):
	"""Create a private default constructor for the class"""
	ctor = modelingSession.getModel().createOperation("constructor", clazz, "ModelerModule", "create")
	ctor.setVisibility(VisibilityMode.PRIVATE)


#
# The macro execution starts here
#
if (selectedElements.size > 0):
	if (isinstance(selectedElements.get(0), Class)):
		makeSingleton(selectedElements.get(0))
	if (isinstance(selectedElements.get(0), Package)):
		clazz = createClass(selectedElements.get(0))
		makeSingleton(clazz)
