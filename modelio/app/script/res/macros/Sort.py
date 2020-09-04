#
# Sort  4.0
#  This macro sorts the model elements under a Classifier or a Package.
#  Classes, interfaces, component, use cases are all classifiers.
#
# Author:  Modeliosoft
#
# Applicable on: Classifier, ModelTree, RequirementContainer, Dictionary, ArchimateFolder
#
# Version history:
# 1.0   3rd Sept 2009       - creation
# 1.01  7th Sept 2009       - enable the macro on Classifiers
# 1.02  4th March 2011      - fix associations sort to sort only modifiable ones.
# 1.03  1st April 2011      - forces refresh in the explorer
# 1.04  20th May 2011       - enable the macro on Dictionary and RequirementContainers.
# 1.04  20th May 2011       - enable the macro on multi selection.
# 2.0   09th September 2011 - update for Modelio 2.0
# 2.01  27th Avril 2012     - added sort of diagrams
# 3.0  10th June 2013       - update for Modelio 3.0
# 4.0  12th Sept 2016       - update for Modelio 3.6, enable the macro on ArchimateFolders

from org.modelio.metamodel.uml.statik import VisibilityMode
from org.modelio.metamodel.uml.infrastructure import ModelElement
from org.modelio.metamodel.uml.infrastructure import ModelTree
from org.modelio.metamodel.uml.statik import Classifier

visibilityOrderingTable = [VisibilityMode.PUBLIC, VisibilityMode.PACKAGEVISIBILITY, VisibilityMode.PROTECTED, VisibilityMode.PRIVATE, VisibilityMode.VISIBILITYUNDEFINED]

def isModifiable(o):
    return o.getStatus().isModifiable()

def attributeCompare(a, b):
    return cmp(a.getName(), b.getName())

def operationCompare(a, b):
    nameA = a.getName()
    nameB = b.getName()
    visA = a.getVisibility()
    visB = b.getVisibility()
    if ( visibilityCompare(visA, visB) == 0 ):
        return  cmp(nameA, nameB)
    else:
        return visibilityCompare(visA, visB)

def visibilityCompare(x, y):
    return cmp(visibilityOrderingTable.index(x), visibilityOrderingTable.index(y))

def sortAttributes(clazz):
    attributes = []
    for att in clazz.getOwnedAttribute():
        attributes.append(att)
    clazz.getOwnedAttribute().clear()
    attributes.sort(attributeCompare)
    for att in attributes:
        clazz.getOwnedAttribute().add(att)

def sortOperations(clazz):
    operations = []
    for op in clazz.getOwnedOperation():
        operations.append(op)
    clazz.getOwnedOperation().clear()
    
    operations.sort(operationCompare)
    
    for op in operations:
        clazz.getOwnedOperation().add(op)

def sortAssociationEnds(clazz):
    oldRoles = []
    newRoles = []
    for role in clazz.getOwnedEnd():
        if isModifiable(role):
            oldRoles.append(role)
            newRoles.append(role)
            
    newRoles.sort(attributeCompare)
    
    if oldRoles != newRoles:
        for role in oldRoles:
            clazz.getOwnedEnd().remove(role)
        
        for role in newRoles:
            clazz.getOwnedEnd().add(role)
    return

def getSortKey(el):
    if (el.getStatus().isRamc()):
        return '1'+el.getName()
    else:
        return '0'+el.getName()

def sortModelTree(modeltree):
    ownedElements = []
    for e in modeltree.getOwnedElement():
        ownedElements.append(e)
    modeltree.getOwnedElement().clear()
    ownedElements.sort(lambda x, y: cmp(getSortKey(x), getSortKey(y)))
    for e in ownedElements:
        modeltree.getOwnedElement().add(e)

def sortRequirementContainer(container):
    ownedElements = []
    for e in container.getOwnedRequirement():
        ownedElements.append(e)
    container.getOwnedRequirement().clear()
    ownedElements.sort(lambda x, y: cmp(x.getName(), y.getName()))
    for e in ownedElements:
        container.getOwnedRequirement().add(e)
    
    ownedContainers = []
    for e in container.getOwnedContainer():
        ownedContainers.append(e)
    container.getOwnedContainer().clear()
    ownedContainers.sort(lambda x, y: cmp(x.getName(), y.getName()))
    for e in ownedContainers:
        container.getOwnedContainer().add(e)

def sortDictionary(dictionary):
    ownedElements = []
    for e in dictionary.getOwnedTerm():
        ownedElements.append(e)
    dictionary.getOwnedTerm().clear()
    ownedElements.sort(lambda x, y: cmp(x.getName(), y.getName()))
    for e in ownedElements:
        dictionary.getOwnedTerm().add(e)
    
    ownedContainers = []
    for e in dictionary.getOwnedDictionary():
        ownedContainers.append(e)
    dictionary.getOwnedDictionary().clear()
    ownedContainers.sort(lambda x, y: cmp(x.getName(), y.getName()))
    for e in ownedContainers:
        dictionary.getOwnedDictionary().add(e)

def sortFolder(folder):
    folders = []
    for e in folder.getFolder():
        folders.append(e)
    folder.getFolder().clear()
    folders.sort(lambda x, y: cmp(x.getName(), y.getName()))
    for e in folders:
        folder.getFolder().add(e)
        
    contents = []
    for e in folder.getContent():
        contents.append(e)
    folder.getContent().clear()
    contents.sort(lambda x, y: cmp(x.getName(), y.getName()))
    for e in contents:
        folder.getContent().add(e)

def sortDiagrams(modelelement):
    ownedDiagrams = []
    for d in modelelement.getProduct():
        ownedDiagrams.append(d)
    modelelement.getProduct().clear()
    ownedDiagrams.sort(lambda x, y: cmp(x.getName(), y.getName()))
    for d in ownedDiagrams:
        modelelement.getProduct().add(d)

#
# The macro execution starts here
#
if (selectedElements.size > 0):
    for element in selectedElements:
        status = element.getStatus()
        if (status.isModifiable()):
            sorted = False
            metaclass = element.getMClass().getQualifiedName()
            if (isinstance(element, ModelElement)):
                sortDiagrams(element)
                sorted = True
            
            if (isinstance(element, ModelTree)):                
                sortModelTree(element)
                sorted = True
            
            if (isinstance(element, Classifier)):                
                sortAttributes(element)
                sortOperations(element)
                sortAssociationEnds(element)
                sorted = True
            
            # Analyst metamodel might be missing, use the metaclass name...
            if (metaclass == "Analyst.RequirementContainer"):
                sortRequirementContainer(element)
                sorted = True
            
            if (metaclass == "Analyst.Dictionary"):
                sortDictionary(element)
                sorted = True
            
            # Archimate metamodel might be missing, use the metaclass name...
            if metaclass.startswith("Archimate") and metaclass.endswith("Folder"):
                sortFolder(element)
                sorted = True
            
            if (not sorted):
                print "ERROR: The selected element cannot be sorted."
        else:
            print "ERROR: The selected element is not modifiable!"
