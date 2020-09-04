package org.modelio.module.modelermodule.engine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagParameter;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.ElementImport;
import org.modelio.metamodel.uml.statik.Feature;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.InterfaceRealization;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.uml.statik.PackageImport;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.metamodel.uml.statik.PassingMode;
import org.modelio.metamodel.uml.statik.RaisedException;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MMetamodel;

/**
 * Service class used to synchronize operations between Interfaces and their implementing Classifiers.
 */
@objid ("5744f9e5-d9ec-4471-9ac8-84f6a1d3c588")
public class InterfaceImplementer {
    /**
     * For all classifiers implementing this interface, synchronize all operation signatures. Missing operations are created.
     * @param session the current modeling session.
     * @param theInterface the interface to update operations from.
     * @return <code>true</code> if the model has been modified.
     */
    @objid ("24faa311-73e8-43d7-aa4d-3408067079ad")
    public boolean updateImplementingClassifiers(final IModelingSession session, final Interface theInterface) {
        boolean result = false;
        
        for (InterfaceRealization theRealization : theInterface.getImplementedLink()) {
            NameSpace theNameSpace = theRealization.getImplementer();
            if (theNameSpace != null && theNameSpace instanceof Classifier) {
                boolean newResult = implementInterface(session, (Classifier) theNameSpace, theInterface);
                result = result || newResult;
            }
        }
        return result;
    }

    /**
     * Create Operations in the Class from those defined in the implemented Interfaces.
     * @param session the current modeling session.
     * @param futureOperationOwner The Class to create the Operations in.
     * @return <code>true</code> if the model has been modified.
     */
    @objid ("8e61049e-bce3-438c-8c39-78789be624d9")
    public boolean implementInterfaces(final IModelingSession session, final Classifier futureOperationOwner) {
        boolean ret = true;
        // Take all Interfaces
        for (Iterator<InterfaceRealization> iter = futureOperationOwner.getRealized().iterator(); iter.hasNext();) {
            Interface itf = iter.next().getImplemented();
        
            ret = ret && implementInterface(session, futureOperationOwner, itf);
        }
        return ret;
    }

    /**
     * Create Operations in the Class from those defined in the Interface.
     * @param futureOperationOwner The Class to create the Operations in.
     * @param itf The Interface containing the Operations to redefine.
     * @return <code>true</code> if the model has been modified.
     */
    @objid ("fef0b56b-a046-4e45-a600-2013450ceac1")
    private boolean implementInterface(final IModelingSession session, final Classifier futureOperationOwner, final Interface itf) {
        try {
            // Take all Operations
            for (Feature element : itf.getOwnedOperation()) {
                Operation interfacefOperation = (Operation) element;
        
                // Check if an existing operation matches
                Operation implementingOperation = getImplementingOperation(interfacefOperation,
                        futureOperationOwner);
                if (implementingOperation == null) {
                    // Create a new Operation implementing the interface operation
                    implementingOperation = session.getModel()
                            .createOperation(interfacefOperation.getName(),
                                    futureOperationOwner);
                    implementingOperation.setRedefines(interfacefOperation);
                    // Update all content for a new operation
                    copyOperationContent(session, interfacefOperation, implementingOperation);
                } else {
                    // Update all content for an existing operation
                    copyOperationContent(session, interfacefOperation, implementingOperation);
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Delete Operations in a Classifier from those defined in the implemented Interfaces.
     * @param current The Classifier to delete the Operations from.
     * @return <code>true</code> if the model has been modified.
     */
    @objid ("a027ed05-3139-441d-a8ec-a2bc76ce8635")
    public boolean unImplementInterfaces(final Classifier current) {
        boolean res = false;
        // Take all Operations
        for (Operation op : new ArrayList<>(current.getOwnedOperation())) {
            // Take all Interfaces
            for (Iterator<InterfaceRealization> iter = current.getRealized().iterator(); iter.hasNext();) {
                Interface itf = iter.next().getImplemented();
                // Check if an existing operation matches
                Operation superOperation = getSuperOperation(op, itf);
                if (superOperation != null) {
                    op.delete(); // delete it from model
                    res = true;
                    break;
                }
            }
        }
        return res;
    }

    @objid ("8eec06fd-634a-41b5-b0e9-ff088bd07310")
    private boolean contentEquals(final Operation operationToFind, final Operation op) {
        boolean found = true;
        
        // Check Operation name
        if (operationToFind.getName().equals(op.getName())) {
            // Check return Parameter
            if (contentEquals(operationToFind.getReturn(), op.getReturn())) {
                // Check IO Parameters count
                if (operationToFind.getIO().size() == op.getIO().size()) {
                    // Check each parameter content
                    Iterator<Parameter> iterator = op.getIO().iterator();
                    for (Iterator<Parameter> iterator2 = operationToFind.getIO().iterator(); iterator2.hasNext() &&
                            found;) {
                        Parameter oldParam = iterator.next();
                        Parameter newParam = iterator2.next();
        
                        if (!contentEquals(newParam, oldParam)) {
                            found = false;
                        }
                    }
        
                    // Check exceptions
                    if (operationToFind.getThrown().size() == op.getThrown().size()) {
                        Iterator<RaisedException> itOldExcept = op.getThrown().iterator();
                        for (Iterator<RaisedException> itNewExcept = operationToFind.getThrown().iterator(); itNewExcept.hasNext() &&
                                found;) {
                            RaisedException oldExcept = itOldExcept.next();
                            RaisedException newExcept = itNewExcept.next();
        
                            if (!newExcept.getThrownType().equals(oldExcept.getThrownType())) {
                                found = false;
                            }
                        }
        
                        // If everything is identical, return true
                        if (found) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Checks if a specific Operation can be found into this Classifier.
     */
    @objid ("5b602fa0-19b7-49ad-be5d-fa4cbc054b50")
    private Operation getImplementingOperation(final Operation operationToMatch, final Classifier potentialParent) {
        String operationToMatchName = operationToMatch.getName();
        
        // Check all redefine links
        for (Operation op : potentialParent.getOwnedOperation()) {
            if (operationToMatch.equals(op.getRedefines())) {
                return op;
            }
        }
        
        // If no redefine link is found, check signatures
        for (Operation op : potentialParent.getOwnedOperation()) {
            String opName = op.getName();
        
            if (operationToMatchName.equals(opName)) {
                if (contentEquals(operationToMatch, op)) {
                    // Add the redefine link
                    op.setRedefines(operationToMatch);
        
                    return op;
                }
            }
        }
        return null;
    }

    /**
     * Checks if a specific Operation can be found into this Interface.
     */
    @objid ("12774039-279b-4a9e-a47a-a5ae7fcf306d")
    private Operation getSuperOperation(final Operation operationToMatch, final Interface potentialParent) {
        // Check the redefine links
        for (Operation op : potentialParent.getOwnedOperation()) {
            if (op.equals(operationToMatch.getRedefines())) {
                return op;
            }
        }
        
        // Check deeper to find a matching operation
        for (Operation op : potentialParent.getOwnedOperation()) {
            if (contentEquals(op, operationToMatch)) {
                return op;
            }
        }
        return null;
    }

    /**
     * Return true if the two parameters have the same type.
     */
    @objid ("ac9aa2c4-6502-423b-a181-eb07844c9e87")
    private boolean contentEquals(final Parameter p1, final Parameter p2) {
        return ((p1 == null && p2 == null) // both null
                || (p1 != null && p1.getType() == null && p2 != null && p2.getType() == null) // null type
                || (p1 != null && p1.getType() != null && p1.getType().equals(p2.getType()))); // same type
    }

    /**
     * Report all the content of the original Operation in the new Operation.
     * @param originalOperation The operation to copy.
     * @param newOperation The owner of the Operation to create.
     */
    @objid ("f6612749-009b-400c-879e-af84e62493f3")
    private void copyOperationContent(final IModelingSession session, final Operation originalOperation, final Operation newOperation) {
        newOperation.setName(originalOperation.getName());
        newOperation.setIsClass(originalOperation.isIsClass());
        newOperation.setConcurrency(originalOperation.isConcurrency());
        copyExceptions(session, originalOperation, newOperation);
        newOperation.setFinal(originalOperation.isFinal());
        copyImports(session, originalOperation, newOperation);
        copyNotes(session, originalOperation, newOperation);
        
        // Update parameters
        List<Parameter> originalParameters = originalOperation.getIO();
        for (Parameter theParameter : new ArrayList<>(newOperation.getIO())) {
            theParameter.delete();
        }
        
        for (Parameter oldParam : originalParameters) {
            Parameter newParam = session.getModel().createIOParameter("", null, newOperation);
            copyParameterContent(session, oldParam, newParam);
        }
        
        newOperation.setPassing(originalOperation.getPassing());
        
        if (originalOperation.getReturn() != null) {
            Parameter returnParam = session.getModel().createReturnParameter("", null, newOperation);
            copyParameterContent(session, originalOperation.getReturn(), returnParam);
        } else {
            Parameter returnParam = newOperation.getReturn();
            if (returnParam != null) {
                returnParam.delete();
            }
        }
        
        copyStereotypes(session, originalOperation, newOperation);
        copyTaggedValues(session, originalOperation, newOperation);
        newOperation.setVisibility(originalOperation.getVisibility());
    }

    @objid ("e769b115-c345-4cf5-a50c-7bef6465db7e")
    private void copyExceptions(final IModelingSession session, final Operation originalOperation, final Operation newOperation) {
        // Update parameters
        List<RaisedException> originalExceptions = originalOperation.getThrown();
        for (RaisedException theParameter : new ArrayList<>(newOperation.getThrown())) {
            theParameter.delete();
        }
        
        for (RaisedException oldException : originalExceptions) {
            RaisedException newException = session.getModel().createRaisedException();
            newException.setThrownType(oldException.getThrownType());
            newException.setThrower(newOperation);
        
            copyNotes(session, oldException, newException);
            copyStereotypes(session, oldException, newException);
            copyTaggedValues(session, oldException, newException);
        }
    }

    @objid ("173a91b3-258d-4170-a139-1c7e1cfa5ef5")
    private void copyStereotypes(final IModelingSession session, final ModelElement originalElement, final ModelElement newElement) {
        MMetamodel metamodel = originalElement.getMClass().getMetamodel();
        for (Stereotype stereo : originalElement.getExtension()) {
            String n = stereo.getName();
            // Find the stereotype
            Stereotype s = session.getMetamodelExtensions().getStereotype(n, metamodel.getMClass(Operation.class));
            if (s != null) {
                // Add the stereotype if it doesn't exists
                if (!newElement.getExtension().contains(s)) {
                    newElement.getExtension().add(s);
                }
            }
        }
    }

    @objid ("3b0cebbb-0013-45cc-93b7-9f66e5568bfe")
    private void copyTaggedValues(final IModelingSession session, final ModelElement originalElement, final ModelElement newElement) {
        for (TaggedValue oldTag : originalElement.getTag()) {
            try {
                TagType oldTagType = oldTag.getDefinition();
                if (oldTagType != null) {
                    String oldTagTypeName = oldTagType.getName();
        
                    boolean exists = false;
                    // Check if the tag already exists
                    for (TaggedValue newTag : newElement.getTag()) {
                        TagType newTagType = newTag.getDefinition();
                        if (newTagType != null) {
                            String newTagTypeName = newTagType.getName();
        
                            // If it exists, update its content
                            if (oldTagTypeName.equals(newTagTypeName)) {
                                for (TagParameter param : new ArrayList<>(newTag.getActual())) {
                                    param.delete();
                                }
                            }
        
                            newTag.setQualifier(oldTag.getQualifier());
        
                            for (TagParameter param : oldTag.getActual()) {
                                session.getModel().createTagParameter(param.getValue(), newTag);
                            }
        
                            exists = true;
                            break;
                        }
                    }
        
                    // If it doesn't exists, create it
                    if (!exists) {
                        TaggedValue newTag = session.getModel().createTaggedValue("ModelerModule", oldTagTypeName, newElement);
                        newTag.setQualifier(oldTag.getQualifier());
        
                        for (TagParameter param : oldTag.getActual()) {
                            session.getModel().createTagParameter(param.getValue(), newTag);
                        }
                    }
                }
            } catch (ExtensionNotFoundException e) {
                // The tag comes from the model itself, no exception is possible
                ModelerModuleModule.getInstance().getModuleContext().getLogService().error(e);
            }
        }
    }

    @objid ("a279b90f-452d-4884-956a-e5035d885454")
    private void copyNotes(final IModelingSession session, final ModelElement originalElement, final ModelElement newElement) {
        for (Note oldNote : originalElement.getDescriptor()) {
            try {
                NoteType oldNoteType = oldNote.getModel();
                if (oldNoteType != null) {
                    String oldNoteTypeName = oldNoteType.getName();
        
                    boolean exists = false;
        
                    // Check if the note already exists on the target
                    for (Note newNote : newElement.getDescriptor()) {
                        NoteType newNoteType = newNote.getModel();
                        if (newNoteType != null) {
                            String newNoteTypeName = newNoteType.getName();
        
                            // If the note is found, update its content
                            if (oldNoteTypeName.equals(newNoteTypeName)) {
                                newNote.setContent(oldNote.getContent());
                                exists = true;
                                break;
                            }
                        }
                    }
        
                    // Create the note if it doesn't exists
                    if (!exists) {
                        session.getModel().createNote("ModelerModule", oldNoteTypeName, newElement, oldNote.getContent());
                    }
                }
            } catch (ExtensionNotFoundException e) {
                // The note comes from the model itself, no exception is possible
                ModelerModuleModule.getInstance().getModuleContext().getLogService().error(e);
            }
        }
    }

    @objid ("75fa3147-95a7-45fa-a7a2-ebbc73e10e93")
    private void copyParameterContent(final IModelingSession session, final Parameter originalParameter, final Parameter newParameter) {
        boolean isReturnParameter = (originalParameter.getReturned() != null);
        
        newParameter.setMultiplicityMin(originalParameter.getMultiplicityMin());
        newParameter.setMultiplicityMax(originalParameter.getMultiplicityMax());
        newParameter.setTypeConstraint(originalParameter.getTypeConstraint());
        newParameter.setType(originalParameter.getType());
        
        copyNotes(session, originalParameter, newParameter);
        copyStereotypes(session, originalParameter, newParameter);
        copyTaggedValues(session, originalParameter, newParameter);
        
        if (!isReturnParameter) {
            newParameter.setName(originalParameter.getName());
            newParameter.setDefaultValue(originalParameter.getDefaultValue());
            newParameter.setParameterPassing(originalParameter.getParameterPassing());
        } else {
            newParameter.setParameterPassing(PassingMode.OUT);
        }
    }

    @objid ("d8f79e7a-c89b-4d90-bbf3-b6cbad37ae3e")
    private void copyImports(final IModelingSession session, final Operation originalOperation, final Operation newOperation) {
        // ElementImports
        for (ElementImport originalImport : originalOperation.getOwnedImport()) {
            boolean exists = false;
            for (ElementImport newImport : newOperation.getOwnedImport()) {
                // Check imported Element
                Element newImportedElement = newImport.getImportedElement();
                Element oldImportedElement = originalImport.getImportedElement();
                if (newImportedElement != null &&
                        oldImportedElement != null &&
                        newImportedElement.equals(oldImportedElement)) {
                    exists = true;
                    break;
                }
            }
        
            if (!exists) {
                ElementImport newImport = session.getModel().createElementImport();
                newImport.setName(originalImport.getName());
                newImport.setVisibility(originalImport.getVisibility());
                newImport.setImportingOperation(newOperation);
                newImport.setImportedElement(originalImport.getImportedElement());
        
                copyNotes(session, originalImport, newImport);
                copyStereotypes(session, originalImport, newImport);
                copyTaggedValues(session, originalImport, newImport);
            }
        }
        
        // PackageImports
        for (PackageImport originalImport : originalOperation.getOwnedPackageImport()) {
            boolean exists = false;
            for (PackageImport newImport : newOperation.getOwnedPackageImport()) {
                // Check imported Package
                Package newImportedPackage = newImport.getImportedPackage();
                Package oldImportedPackage = originalImport.getImportedPackage();
                if (newImportedPackage != null &&
                        oldImportedPackage != null &&
                        newImportedPackage.equals(oldImportedPackage)) {
                    exists = true;
                    break;
                }
            }
        
            if (!exists) {
                PackageImport newImport = session.getModel().createPackageImport();
                newImport.setName(originalImport.getName());
                newImport.setVisibility(originalImport.getVisibility());
                newImport.setImportingOperation(newOperation);
                newImport.setImportedPackage(originalImport.getImportedPackage());
        
                copyNotes(session, originalImport, newImport);
                copyStereotypes(session, originalImport, newImport);
                copyTaggedValues(session, originalImport, newImport);
            }
        }
    }

    /**
     * Checks all operations to remove redefine links bound to interfaces no more implemented.
     * @param operationsOwner The classifier owning the operations.
     * @return <code>true</code> if the model has been modified.
     */
    @objid ("903fb552-3977-489d-ba68-62dc53b3af71")
    public boolean updateRedefineLinks(final Classifier operationsOwner) {
        boolean hasDoneWork = false;
        
        ArrayList<Interface> implementedInterfaces = new ArrayList<>();
        // Take all Interfaces
        for (Iterator<InterfaceRealization> iter = operationsOwner.getRealized().iterator(); iter.hasNext();) {
            Interface itf = iter.next().getImplemented();
            implementedInterfaces.add(itf);
        }
        
        // Take all Operations
        for (Operation operation : operationsOwner.getOwnedOperation()) {
            Operation redefinedOperation = operation.getRedefines();
            if (redefinedOperation != null) {
                Classifier redefinedOperationOwner = redefinedOperation.getOwner();
                if ((redefinedOperationOwner instanceof Interface) && !implementedInterfaces.contains(redefinedOperationOwner)) {
                    // Remove only the redefine link
                    operation.setRedefines(null);
                    hasDoneWork = true;
                }
            }
        }
        return hasDoneWork;
    }

}
