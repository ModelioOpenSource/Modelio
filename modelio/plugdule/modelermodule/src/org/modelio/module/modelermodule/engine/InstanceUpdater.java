/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package org.modelio.module.modelermodule.engine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.interactionModel.Interaction;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionFragment;
import org.modelio.metamodel.uml.behavior.interactionModel.Lifeline;
import org.modelio.metamodel.uml.behavior.interactionModel.Message;
import org.modelio.metamodel.uml.behavior.interactionModel.MessageEnd;
import org.modelio.metamodel.uml.behavior.interactionModel.MessageSort;
import org.modelio.metamodel.uml.behavior.stateMachineModel.InternalTransition;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Region;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateVertex;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Transition;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagParameter;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.AttributeLink;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Node;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.metamodel.uml.statik.ProvidedInterface;
import org.modelio.metamodel.uml.statik.RequiredInterface;
import org.modelio.module.modelermodule.api.ModelerModuleException;
import org.modelio.module.modelermodule.gui.AttributeCreationWizard;
import org.modelio.module.modelermodule.gui.ClassifierCreationWizard;
import org.modelio.module.modelermodule.gui.ConfirmDialog;
import org.modelio.module.modelermodule.gui.InputDialog;
import org.modelio.module.modelermodule.gui.ShellHelper;
import org.modelio.module.modelermodule.i18n.I18nMessageService;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;

/**
 * Service class managing model transformations and synchronizations between Instances and Classifiers. Also includes State and Sequence related services, as they also involve Instance creations or updates.
 */
@objid ("b409e15d-91ea-4f68-83b1-dff376bfdc5e")
public class InstanceUpdater {
    /**
     * Create an attribute from an attribute link. If the class doesn't exists, it is also created.
     * 
     * @param session The modeling session
     * @param current The attribute link to create a new attribute from.
     * @return <code>true</code> when an attribute is created.
     * @throws org.modelio.module.modelermodule.api.ModelerModuleException when the attribute already exists, or when the base of the instance isn't a classifier.
     */
    @objid ("cd4fcf6a-9ff0-48ce-8f1b-3d0a4bd3953e")
    public boolean createAttribute(final IModelingSession session, final AttributeLink current) throws ModelerModuleException {
        String aName = current.getName();
        Attribute att = current.getBase();
        if (att != null) {
            throw new ModelerModuleException(
                    I18nMessageService.getString("module.error.createAttribute.existingBase", aName));
        } else {
            // Get base classifier
            Instance inst = current.getAttributed();
            if (inst.getBase() == null) {
                // No base classifier, create it.
                if (createClassifierWithOptions(session, inst, false) == null) {
                    return false;
                }
            }
        
            // Is the base valid?
            if (inst.getBase() instanceof Classifier) {
                Classifier base = (Classifier) inst.getBase();
        
                String attName = InputDialog.showInputDialog(ShellHelper.findActiveShell(),
                        I18nMessageService.getString("module.gui.process.attributeName"),
                        I18nMessageService.getString("module.gui.process.chooseName"),
                        aName);
        
                if (attName == null || attName.equals("")) {
                    return false;
                }
        
                // Does the attribute already exists?
                for (Attribute attribute : base.getOwnedAttribute()) {
                    if (attribute.getName().equals(attName)) {
                        throw new ModelerModuleException(
                                I18nMessageService.getString("module.error.createAttribute.existing",
                                        aName));
                    }
                }
                att = session.getModel().createAttribute(attName, session.getModel().getUmlTypes().getUNDEFINED(), base);
                current.setBase(att);
            } else {
                throw new ModelerModuleException(
                        I18nMessageService.getString("module.error.createAttribute.baseType",
                                aName));
            }
        
            return true;
        }
    }

    /**
     * Create a classifier from an instance. Includes creation of: - ports from the instance ports. - attributes from attribute links. - operations from incoming messages.
     * 
     * @param session The modeling session
     * @param current The instance to create the classifier from.
     * @return <code>true</code> when a new classifier is created.
     * @throws org.modelio.module.modelermodule.api.ModelerModuleException When an error happens during creation.
     */
    @objid ("80f415a5-c0da-43fc-af4f-aa01cc281358")
    public boolean createClassifier(final IModelingSession session, final Instance current) throws ModelerModuleException {
        return createClassifierWithOptions(session, current, true) != null;
    }

    /**
     * Update the internal structure of a class. Updates all parts from their base classifiers, and allows creation of all missing bases. It is also possible to reference an existing classifier.
     * 
     * @param session The modeling session
     * @param current The class to update.
     * @return <code>true</code> if the class has been modified.
     * @throws org.modelio.module.modelermodule.api.ModelerModuleException When an error happens during the update.
     */
    @objid ("906638b8-0d60-4829-b13c-035b357132d7")
    public boolean updateInternalStructure(final IModelingSession session, final Class current) throws ModelerModuleException {
        for (BindableInstance inst : new ArrayList<>(current.getInternalStructure())) {
            if (!(inst instanceof Port)) {
                Classifier instanciedClass = null;
        
                // Is the part linked to a classifier?
                if (inst.getBase() != null) {
                    if (inst.getBase() instanceof Classifier) {
                        instanciedClass = (Classifier) inst.getBase();
                    } else {
                        throw new ModelerModuleException(
                                I18nMessageService.getString("module.error.createInternalStructure.baseType", inst.getName()));
                    }
                } else {
                    // Bound the part to a new or existing classifier
                    ConfirmDialog.Values res = ConfirmDialog.showConfirmDialog(ShellHelper.findActiveShell(),
                            I18nMessageService.getString("module.gui.process.updateFromClass",
                                    inst.getName()),
                            I18nMessageService.getString("module.gui.process.choose"));
                    if (res == ConfirmDialog.Values.YES_OPTION) {
                        updatePartFromInstanciedClassifier(session, inst);
                        instanciedClass = (Classifier) inst.getBase();
                    } else if (res == ConfirmDialog.Values.NO_OPTION) {
                        instanciedClass = createClassifierWithOptions(session, inst, true);
                    }
                }
        
                // Update the part from its base class
                if (inst.getBase() != null) {
                    updatePartFromInstanciedClassifier(session, inst, instanciedClass);
                } else {
                    throw new ModelerModuleException(
                            I18nMessageService.getString("module.error.createInternalStructure.nothing",
                                    inst.getName()));
                }
            }
        }
        return false;
    }

    /**
     * Create an operation from a message. An Instance might be created in the process, or a Classifier.
     * @see InstanceUpdater#createInstanceAndClassifier(IModelingSession, Lifeline)
     * 
     * @param session The modeling session
     * @param current The message to create the operation from.
     * @return <code>true</code> if a new operation is created.
     * @throws org.modelio.module.modelermodule.api.ModelerModuleException When an error happens during the creation.
     */
    @objid ("095908e4-edfa-4c57-9533-448fc5a11633")
    public boolean createOperation(final IModelingSession session, final Message current) throws ModelerModuleException {
        Operation op = current.getInvoked();
        
        // Does the Operation already exist?
        if (op != null) {
            throw new ModelerModuleException(
                    I18nMessageService.getString("module.error.createOperation.existing", op.getName()));
        } else {
            List<Lifeline> covered = current.getReceiveEvent().getCovered();
            if (covered.size() != 1) {
                return false;
            }
        
            Lifeline lifeline = covered.get(0);
            Instance occurence = lifeline.getRepresented();
        
            if (occurence == null) {
                // Try to create the instance
                createInstanceAndClassifier(session, lifeline);
                occurence = lifeline.getRepresented();
        
                if (occurence == null) {
                    // No instance has been created, rollback & quit
                    return false;
                } else {
                    // Instance fully created, no need to continue
                    return true;
                }
            }
        
            Classifier base = null;
        
            // Get the base classifier
            if (occurence.getBase() == null) {
                // No classifier, create one
                if (createClassifierWithOptions(session, occurence, false) == null) {
                    return false;
                } else {
                    if (occurence.getBase() instanceof Classifier) {
                        base = (Classifier) occurence.getBase();
                    } else {
                        throw new ModelerModuleException(
                                I18nMessageService.getString("module.error.createOperation.baseType", occurence.getName()));
                    }
                }
            } else {
                // Check base type
                if (occurence.getBase() instanceof Classifier) {
                    base = (Classifier) occurence.getBase();
                } else {
                    throw new ModelerModuleException(
                            I18nMessageService.getString("module.error.createOperation.baseType", occurence.getName()));
                }
            }
        
            // Create new operation
            if (current.getInvoked() == null) {
                String opName = InputDialog.showInputDialog(ShellHelper.findActiveShell(),
                        I18nMessageService.getString("module.gui.process.operationName"),
                        I18nMessageService.getString("module.gui.process.chooseName"),
                        current.getName());
                if (opName != null) {
                    for (Operation tmp : base.getOwnedOperation()) {
                        if (opName.equals(tmp.getName())) {
                            op = tmp;
                            break;
                        }
                    }
        
                    if (op == null) {
                        op = session.getModel().createOperation(opName, base);
                    }
        
                    current.setInvoked(op);
                }
            }
        }
        return true;
    }

    /**
     * Create an operation from a transition. An Instance might be created in the process, or a Classifier.
     * @see InstanceUpdater#createInstanceAndClassifier(IModelingSession, Lifeline)
     * 
     * @param session The modeling session
     * @param current The transition to create the operation from.
     * @return <code>true</code> if a new transition is created.
     * @throws org.modelio.module.modelermodule.api.ModelerModuleException When an error happens during the creation.
     */
    @objid ("21fe25af-cd84-4264-a7c6-69a9f5a71616")
    public boolean createOperation(final IModelingSession session, final Transition current) throws ModelerModuleException {
        Operation op = current.getProcessed();
        
        if (op != null) {
            throw new ModelerModuleException(
                    I18nMessageService.getString("module.error.createOperationFromTransition.existing", op.getName()));
        } else {
            NameSpace ret = getStateContainer(current);
        
            Classifier base;
            if (ret instanceof Classifier) {
                base = (Classifier) ret;
            } else {
                String className = InputDialog.showInputDialog(ShellHelper.findActiveShell(),
                        I18nMessageService.getString("module.gui.process.className"),
                        I18nMessageService.getString("module.gui.process.chooseExistingName"),
                        "Class");
                if (className != null && className.length() > 0) {
                    base = session.getModel().createClass();
                    base.setOwner(ret);
                    base.setName(className);
                } else {
                    base = null;
                }
            }
        
            // Create new operation
            if (base != null) {
                String opName = InputDialog.showInputDialog(ShellHelper.findActiveShell(),
                        I18nMessageService.getString("module.gui.process.operationName"),
                        I18nMessageService.getString("module.gui.process.chooseName"),
                        current.getName());
                if (opName != null) {
                    for (Operation tmp : base.getOwnedOperation()) {
                        if (opName.equals(tmp.getName())) {
                            op = tmp;
                            break;
                        }
                    }
        
                    if (op == null) {
                        op = session.getModel().createOperation(opName, base);
                    }
        
                    current.setProcessed(op);
                    current.setEffect("");
        
                    return true;
                } else {
                    throw new ModelerModuleException(
                            I18nMessageService.getString("module.error.createOperationFromTransition.noOperationPossible"));
                }
            }
        }
        return false;
    }

    /**
     * Update a part contents from its base classifier. Allows creation of a new classifier if no base exists, or referencing an existing classifier.
     * 
     * @param session The modeling session.
     * @param current the instance to update.
     * @return <code>true</code> if the instance have been modified.
     * @throws org.modelio.module.modelermodule.api.ModelerModuleException when an error occurs during the update.
     */
    @objid ("72bd3577-c749-433b-95de-8f38ffb457c6")
    public boolean updatePartFromInstanciedClassifier(final IModelingSession session, final Instance current) throws ModelerModuleException {
        Classifier base;
        
        if (current.getBase() != null) {
            if (current.getBase() instanceof Classifier) {
                base = (Classifier) current.getBase();
                updatePartFromInstanciedClassifier(session, current, base);
            } else {
                throw new ModelerModuleException(
                        I18nMessageService.getString("module.error.updatePartFromInstanciedClass.baseType", current.getName()));
            }
        } else {
            // Choose a class name
            String className = InputDialog.showInputDialog(ShellHelper.findActiveShell(),
                    I18nMessageService.getString("module.gui.process.className"),
                    I18nMessageService.getString("module.gui.process.chooseExistingName"),
                    current.getName());
            if (className == null || className.equals("")) {
                return false;
            }
        
            // Get the package container
            Package container = getContainerPackage(current);
            if (container == null) {
                throw new ModelerModuleException(
                        I18nMessageService.getString("module.error.updatePartFromInstanciedClass.rolePackage", current.getName()));
            }
        
            List<ModelTree> cl = new ArrayList<>();
            for (ModelTree mt : container.getOwnedElement()) {
                if (className.equals(mt.getName()) && mt instanceof Classifier) {
                    cl.add(mt);
                }
            }
            if (cl.size() == 1) {
                Classifier newClass = (Classifier) cl.get(0);
                current.setBase(newClass);
        
                // Do the update
                updatePartFromInstanciedClassifier(session, current, newClass);
            } else {
                throw new ModelerModuleException(
                        I18nMessageService.getString("module.error.updatePartFromInstanciedClass.find", className));
            }
        }
        return true;
    }

    /**
     * Create a classifier from a lifeline. Includes creation of: - an instance represented by the lifeline. - ports from the instance ports. - attributes from attribute links. - operations from incoming messages.
     * 
     * @param session The modeling session
     * @param current The lifeline to create the classifier from.
     * @return <code>true</code> when a new classifier is created.
     * @throws org.modelio.module.modelermodule.api.ModelerModuleException When an error happens during creation.
     */
    @objid ("5690a2dd-7f3c-4983-a66a-7cdefbe6cd12")
    public boolean createInstanceAndClassifier(final IModelingSession session, final Lifeline current) throws ModelerModuleException {
        // Does the lifeline represent an instance?
        if (current.getRepresented() != null) {
            throw new ModelerModuleException(
                    I18nMessageService.getString("module.error.createInstanceAndClassifier.represented",
                            current.getRepresented().getName()));
        }
        
        // Choose a name for the instance
        String instName = InputDialog.showInputDialog(ShellHelper.findActiveShell(),
                I18nMessageService.getString("module.gui.process.instanceName"),
                I18nMessageService.getString("module.gui.process.chooseName"),
                current.getName());
        if (instName == null || instName.equals("")) {
            return false;
        }
        
        // Get local collaboration
        final Interaction owner = current.getOwner();
        Collaboration localCollaboration = getLocalCollaboration(owner);
        if (localCollaboration == null) {
            // Create the local collaboration
            localCollaboration = session.getModel().createCollaboration();
            localCollaboration.setName("locals");
            owner.getOwnedCollaboration().add(localCollaboration);
        }
        
        // Is the instance already existing ?
        List<Instance> cl = new ArrayList<>();
        for (Instance instance : localCollaboration.getDeclared()) {
            if (instName.equals(instance.getName())) {
                cl.add(instance);
            }
        }
        
        if (cl.size() > 0) {
            throw new ModelerModuleException(
                    I18nMessageService.getString("module.error.createInstanceAndClassifier.existing",
                            instName));
        } else {
            // Create new instance
            BindableInstance newInst = session.getModel().createBindableInstance();
            newInst.setName(instName);
            newInst.setOwner(localCollaboration);
            current.setRepresented(newInst);
        
            return createClassifier(session, newInst);
        }
    }

    /**
     * Update a lifeline's represented instance contents from its base classifier. Allows creation of the instance, and of a new classifier if no base exists, or referencing an existing classifier.
     * 
     * @param session The modeling session
     * @param current The lifeline to update the classifier from.
     * @throws org.modelio.module.modelermodule.api.ModelerModuleException When an error happens during the update.
     */
    @objid ("1ad46ef3-115f-4bab-b864-ae38a068c356")
    public void updateInstanceAndClassifier(final IModelingSession session, final Lifeline current) throws ModelerModuleException {
        // Does the lifeline represent an instance?
        if (current.getRepresented() != null) {
            throw new ModelerModuleException(
                    I18nMessageService.getString("module.error.createInstanceAndClassifier.represented", current.getRepresented().getName()));
        }
        
        // Choose a name for the instance
        String instName = InputDialog.showInputDialog(ShellHelper.findActiveShell(),
                I18nMessageService.getString("module.gui.process.instanceName"),
                I18nMessageService.getString("module.gui.process.chooseName"),
                current.getName());
        if (instName == null || instName.equals("")) {
            return;
        }
        
        // Get local collaboration
        final Interaction owner = current.getOwner();
        Collaboration localCollaboration = getLocalCollaboration(owner);
        if (localCollaboration == null) {
            // Create the local collaboration
            localCollaboration = session.getModel().createCollaboration();
            localCollaboration.setName("locals");
            owner.getOwnedCollaboration().add(localCollaboration);
        }
        
        // Is the instance already existing ?
        List<Instance> cl = new ArrayList<>();
        for (Instance instance : localCollaboration.getDeclared()) {
            if (instName.equals(instance.getName())) {
                cl.add(instance);
            }
        }
        if (cl.size() > 0) {
            throw new ModelerModuleException(
                    I18nMessageService.getString("module.error.createInstanceAndClassifier.existing", instName));
        } else {
            // Create new instance
            BindableInstance newInst = session.getModel().createBindableInstance();
            newInst.setName(instName);
            newInst.setOwner(localCollaboration);
            current.setRepresented(newInst);
        
            updatePartFromInstanciedClassifier(session, newInst);
        }
    }

    /**
     * Update the contents of the body according to the classifier.
     * 
     * @param inst The instance to update.
     * @param instanciedClass The classifier used for update.
     */
    @objid ("dcbcf50d-02cc-434f-ae20-20e741957116")
    private void updatePartFromInstanciedClassifier(final IModelingSession session, final Instance inst, final Classifier instanciedClass) {
        if (!(inst instanceof Port)) {
        
            // Each port on the class must be reported on the part
            for (Port sourcePort : new ArrayList<>(instanciedClass.getInternalStructure(Port.class))) {
                Port targetPort = null;
                for (Port port : new ArrayList<>(inst.getPart(Port.class))) {
                    if (port.getRepresentedFeature() != null
                            && port.getRepresentedFeature().equals(sourcePort)) {
                        if (targetPort != null) {
                            port.delete();
                        } else {
                            targetPort = port;
                        }
                    }
                }
        
                if (targetPort == null) {
                    // Create a port on the instance, from the class port.
                    targetPort = (Port) ModelerModuleModule.getInstance().getModuleContext().getModelioServices().getModelManipulationService().clone(sourcePort);
        
                    // Change the owner
                    targetPort.setInternalOwner(null);
                    inst.getPart().add(targetPort);
        
                    // Set the represented property
                    targetPort.setRepresentedFeature(sourcePort);
                } else {
                    // Update port from the referenced one.
                    updatePortFromPort(session, targetPort, sourcePort);
                }
        
            }
        
            // Delete existing ports not representing a port from the instanced class
            for (Port p : new ArrayList<>(inst.getPart(Port.class))) {
                if (p.getRepresentedFeature() == null
                        || !p.getRepresentedFeature().getCompositionOwner().equals(instanciedClass)) {
                    p.delete();
                }
            }
        
            // Launch attribute creation wizard
            if (instanciedClass.getOwnedAttribute().size() > 0) {
                AttributeCreationWizard window = new AttributeCreationWizard(ShellHelper.findActiveShell(), instanciedClass, inst);
                window.open();
            } else {
                for (Iterator<AttributeLink> iter = new ArrayList<>(inst.getSlot()).iterator(); iter.hasNext();) {
                    iter.next().delete();
                }
            }
        }
    }

    /**
     * Create a classifier from an instance. May include creation of: - ports from ports on the instance - attributes from attribute links - operations from incoming messages
     * 
     * @param current The instance to create a classifier from.
     * @param createContent Whether or not to update the class contents.
     * @return The created classifier.
     */
    @objid ("918d6298-2a1f-4bc2-b667-2ffeb7e046c3")
    private Classifier createClassifierWithOptions(final IModelingSession session, final Instance current, final boolean createContent) throws ModelerModuleException {
        Classifier newClass = null;
        
        // Does the instance already have a base?
        if (current.getBase() != null) {
            throw new ModelerModuleException(
                    I18nMessageService.getString("module.error.createClassWithOptions.baseType",
                            current.getBase().getName()));
        }
        
        // Choose a name for the new classifier
        ClassifierCreationWizard diag = new ClassifierCreationWizard(ShellHelper.findActiveShell(), current);
        diag.open();
        
        String className = diag.getChosenName();
        String toCreate = diag.getChosenClass();
        
        if (className == null || className.equals("")) {
            return null;
        }
        
        // Get package container
        Package container = getContainerPackage(current);
        if (container == null) {
            throw new ModelerModuleException(
                    I18nMessageService.getString("module.error.createClassWithOptions.container",
                            current.getName()));
        }
        
        // Does the classifier already exist?
        List<ModelTree> cl = new ArrayList<>();
        for (ModelTree element : container.getOwnedElement(getClassifierTypeFromName(toCreate))) {
            if (className.equals(element.getName())) {
                cl.add(element);
            }
        }
        if (cl.size() > 0) {
            throw new ModelerModuleException(
                    I18nMessageService.getString("module.error.createClassWithOptions.existing",
                            className));
        } else {
            // Create the classifier
            newClass = createClassifier(session, toCreate, className, container);
            current.setBase(newClass);
        
            // Do we have to create the content?
            if (createContent) {
                // Create operations from messages
                for (Iterator<Lifeline> iter = current.getRepresentedLifeLine().iterator(); iter.hasNext();) {
                    for (Iterator<InteractionFragment> iter2 = iter.next().getCoveredBy().iterator(); iter2.hasNext();) {
                        InteractionFragment inter = iter2.next();
                        if (inter instanceof MessageEnd) {
                            Message msg = ((MessageEnd) inter).getReceivedMessage();
                            if (msg != null) {
                                MessageSort sort = msg.getSortOfMessage();
        
                                if (sort != MessageSort.RETURNMESSAGE &&
                                        sort != MessageSort.ASYNCCALL &&
                                        sort != MessageSort.ASYNCSIGNAL) {
                                    createOperation(session, msg);
                                }
                            }
                        }
                    }
                }
        
                // Create attributes from attribute links
                for (Iterator<AttributeLink> iter = current.getSlot().iterator(); iter.hasNext();) {
                    createAttribute(session, iter.next());
                }
        
                // All ports on the part must be reported on the class
                for (Port sourcePort : current.getPart(Port.class)) {
                    Port port = createPortFromPort(session, newClass, sourcePort);
                    completeRequiredProvided(session, port, sourcePort);
                }
            }
        }
        return newClass;
    }

    /**
     * Get the closest package owning this instance. Follows composition in the model.
     * 
     * @param current the instance to get the corresponding parent package from.
     */
    @objid ("784c9c55-ebb7-460d-b44a-c2cee811f74d")
    private Package getContainerPackage(final Instance current) {
        ModelTree container;
        
        if (current == null) {
            return null;
        }
        
        container = current.getOwner();
        if (container != null) {
            while (!(container instanceof Package)) {
                if (container instanceof Collaboration) {
                    container = getContainerPackage((Collaboration) container);
                } else {
                    container = getContainerPackage(container);
                }
            }
        } else {
            if (current instanceof BindableInstance) {
                container = ((BindableInstance) current).getInternalOwner();
        
                if (!(container instanceof Package)) {
                    container = getContainerPackage(container);
                }
            } else {
                return null;
            }
        }
        return (Package) container;
    }

    /**
     * Get the closest package owning this collaboration. Follows composition in the model.
     */
    @objid ("b050a789-f395-4259-87ec-4401f8a20a4e")
    private Package getContainerPackage(final Collaboration current) {
        if (current == null) {
            return null;
        }
        
        // In Modelio, a Collaboration belongs to a UseCase, a Class, a
        // Package, a Collaboration or an Operation.
        
        ModelTree container = current.getOwner();
        
        if (container == null) {
            if (current.getBRepresented() != null) {
                container = current.getBRepresented().getOwner();
            } else if (current.getORepresented() != null) {
                container = current.getORepresented().getOwner();
            } else {
                // container = current.get
            }
        }
        
        if (!(container instanceof Package)) {
            container = getContainerPackage(container);
        }
        return (Package) container;
    }

    /**
     * Update all requires and provided interfaces on a port from another one.
     * 
     * @param current the port to update.
     * @param sourcePort the port being used as a reference.
     */
    @objid ("280535bb-cbd0-46b3-93de-075f33394d0f")
    private void completeRequiredProvided(final IModelingSession session, final Port current, final Port sourcePort) {
        ProvidedInterface pi = null;
        RequiredInterface ri = null;
        
        // Update all Provided Interfaces
        for (Iterator<ProvidedInterface> iter = sourcePort.getProvided().iterator(); iter.hasNext();) {
            boolean destPortFound = false;
            ProvidedInterface cur = iter.next();
        
            if (current.getProvided().contains(cur)) {
                destPortFound = true;
            }
        
            if (!destPortFound) {
                pi = session.getModel().createProvidedInterface();
                current.getProvided().add(pi);
                for (Iterator<Interface> iter2 = cur.getProvidedElement().iterator(); iter2.hasNext();) {
                    pi.getProvidedElement().add(iter2.next());
                }
            }
        }
        
        // Update all Required Interfaces
        for (Iterator<RequiredInterface> iter = sourcePort.getRequired().iterator(); iter.hasNext();) {
            boolean destPortFound = false;
            RequiredInterface cur = iter.next();
        
            if (current.getRequired().contains(cur)) {
                destPortFound = true;
            }
        
            if (!destPortFound) {
                ri = session.getModel().createRequiredInterface();
                current.getRequired().add(ri);
                for (Iterator<Interface> iter2 = cur.getRequiredElement().iterator(); iter2.hasNext();) {
                    ri.getRequiredElement().add(iter2.next());
                }
            }
        }
    }

    /**
     * Get the closest namespace owning a transition.
     */
    @objid ("241e65d8-e613-429c-be18-468d536f2629")
    private NameSpace getStateContainer(final Transition current) {
        NameSpace o = null;
        
        if (current instanceof InternalTransition) {
            o = getStateContainer((InternalTransition) current);
        } else {
            o = getStateContainer(current.getSource());
            if (o == null) {
                o = getStateContainer(current.getTarget());
            }
        }
        return o;
    }

    /**
     * Get the closest namespace owning a state.
     */
    @objid ("c2abcc21-98ed-43d7-9eeb-fe92b5efb1ff")
    private NameSpace getStateContainer(final StateVertex current) {
        NameSpace o = null;
        
        if (current instanceof State) {
            Region rep = ((State) current).getParent();
            if (rep != null) {
                StateMachine stateMachine = rep.getRepresented();
                if (stateMachine != null) {
                    o = stateMachine.getOwner();
                } else {
                    o = getStateContainer(rep.getParent());
                }
            }
        }
        return o;
    }

    /**
     * Get the closest namespace owning an internal transitio n.
     */
    @objid ("329205b7-f585-4daf-b475-fb173a74e56a")
    private NameSpace getStateContainer(final InternalTransition current) {
        return getStateContainer(current.getSComposed());
    }

    /**
     * Create a new port on a classifier from an existing one.
     * 
     * @param current the classifier to create the port on.
     * @param sourcePort the port being used as a reference.
     */
    @objid ("092096ee-d789-4b3e-ab0d-86b88b94de3e")
    private Port createPortFromPort(final IModelingSession session, final Classifier current, final Port sourcePort) {
        Port targetPort = session.getModel().createPort();
        updatePortFromPort(session, targetPort, sourcePort);
        return targetPort;
    }

    /**
     * Update a port from an existing one.
     * 
     * @param session the modeling session.
     * @param targetPort the port to update;
     * @param sourcePort the port being used as a reference.
     */
    @objid ("ac91e649-0398-47d5-89a0-214101a53957")
    private void updatePortFromPort(final IModelingSession session, final Port targetPort, final Port sourcePort) {
        targetPort.setName(sourcePort.getName());
        targetPort.setIsService(sourcePort.isIsService());
        targetPort.setIsBehavior(sourcePort.isIsBehavior());
        targetPort.setRepresentedFeature(sourcePort);
        targetPort.setDirection(sourcePort.getDirection());
        targetPort.setMultiplicityMin(sourcePort.getMultiplicityMin());
        targetPort.setMultiplicityMax(sourcePort.getMultiplicityMax());
        targetPort.setBase(sourcePort.getBase());
        
        updateNotes(session, sourcePort, targetPort);
        updateStereotypes(sourcePort, targetPort);
        updateTaggedValues(session, sourcePort, targetPort);
        updateProvidedIntefaces(session, sourcePort, targetPort);
        updateRequiredIntefaces(session, sourcePort, targetPort);
    }

    @objid ("1fab3d7d-fbfd-4cfe-9e91-592379fa1319")
    private boolean equals(final ProvidedInterface pi1, final ProvidedInterface pi2) {
        List<Interface> providedElements1 = pi1.getProvidedElement();
        List<Interface> providedElements2 = pi2.getProvidedElement();
        return providedElements1.size() == providedElements2.size() && providedElements1.containsAll(providedElements2);
    }

    @objid ("a6643406-098c-40e0-9848-f171be2b927a")
    private boolean equals(final RequiredInterface ri1, final RequiredInterface ri2) {
        List<Interface> requiredElements1 = ri1.getRequiredElement();
        List<Interface> requiredElements2 = ri2.getRequiredElement();
        return requiredElements1.size() == requiredElements2.size() && requiredElements1.containsAll(requiredElements2);
    }

    @objid ("2512e3ad-1e6e-435a-9b5e-c213858c8c7b")
    private void updateProvidedIntefaces(final IModelingSession session, final Port sourcePort, final Port targetPort) {
        ArrayList<ProvidedInterface> piSources = new ArrayList<>();
        piSources.addAll(sourcePort.getProvided());
        
        for (ProvidedInterface piTarget : new ArrayList<>(targetPort.getProvided())) {
            // test if target provided interface already exist on source port
            boolean notExist = true;
            ProvidedInterface matchPI = null;
        
            for (ProvidedInterface piSource : piSources) {
                if (equals(piTarget, piSource)) {
                    notExist = false;
                    matchPI = piSource;
                    break;
                }
            }
        
            if (notExist) {
                // delete none existing provided interface
                piTarget.delete();
            } else {
                // remove the matched ProvidedInterface
                piSources.remove(matchPI);
            }
        }
        
        ArrayList<ProvidedInterface> piTargets = new ArrayList<>();
        piSources.addAll(sourcePort.getProvided());
        
        for (ProvidedInterface piSource : sourcePort.getProvided()) {
            // test if target provided interface already exist on source port
            boolean notExist = true;
            ProvidedInterface matchPI = null;
        
            for (ProvidedInterface piTarget : piTargets) {
                if (equals(piTarget, piSource)) {
                    notExist = false;
                    matchPI = piTarget;
                    break;
                }
            }
        
            if (notExist) {
                // create none existing provided interface
                session.getModel().createProvidedInterface(targetPort, piSource.getProvidedElement());
            } else {
                piTargets.remove(matchPI);
            }
        }
    }

    @objid ("1e82eb09-dc14-436c-abe5-bc0a6f2276f4")
    private void updateRequiredIntefaces(final IModelingSession session, final Port sourcePort, final Port targetPort) {
        ArrayList<RequiredInterface> riSources = new ArrayList<>();
        riSources.addAll(sourcePort.getRequired());
        
        for (RequiredInterface riTarget : new ArrayList<>(targetPort.getRequired())) {
            // test if target required interface already exist on source port
            boolean notExist = true;
            RequiredInterface matchedRI = null;
        
            for (RequiredInterface riSource : riSources) {
                if (equals(riTarget, riSource)) {
                    notExist = false;
                    matchedRI = riSource;
                    break;
                }
            }
        
            if (notExist) {
                // delete none existing required interface
                riTarget.delete();
            } else {
                riSources.remove(matchedRI);
            }
        }
        
        ArrayList<RequiredInterface> riTargets = new ArrayList<>();
        riTargets.addAll(targetPort.getRequired());
        
        for (RequiredInterface riSource : new ArrayList<>(sourcePort.getRequired())) {
            // test if target provided interface already exist on source port
            boolean notExist = true;
            RequiredInterface matchedRI = null;
        
            for (RequiredInterface riTarget : riTargets) {
                if (equals(riTarget, riSource)) {
                    notExist = false;
                    matchedRI = riTarget;
                    break;
                }
            }
        
            if (notExist) {
                // create none existing provided interface
                session.getModel().createRequiredInterface(targetPort, riSource.getRequiredElement());
            } else {
                riTargets.remove(matchedRI);
            }
        }
    }

    @objid ("ee6a77ad-739a-46e5-8979-7076f5527640")
    private void updateTaggedValues(final IModelingSession session, final Port sourcePort, final Port targetPort) {
        for (TaggedValue tag : new ArrayList<>(targetPort.getTag())) {
            tag.delete();
        }
        
        createTaggedValuesFromTaggedValues(session, sourcePort, targetPort);
    }

    @objid ("2306f908-e78b-4ffd-b554-fe9ebdca62de")
    private void updateStereotypes(final Port sourcePort, final Port targetPort) {
        for (Stereotype ster : new ArrayList<>(targetPort.getExtension())) {
            targetPort.getExtension().remove(ster);
        }
        
        createStereotypesFromStereotypes(sourcePort, targetPort);
    }

    @objid ("b117ad83-6e4b-4c0f-8bad-2387db7ea210")
    private void updateNotes(final IModelingSession session, final Port sourcePort, final Port targetPort) {
        // Delete existing notes
        for (Note note : new ArrayList<>(targetPort.getDescriptor())) {
            note.delete();
        }
        
        createNotesFromNotes(session, sourcePort, targetPort);
    }

    /**
     * Create a classifier in a container.
     * 
     * @param toCreate type of classifier to create: Class, Component, Node or Interface.
     * @param className name of the element to create.
     * @param container the container to create the new classifier into.
     * @return the created classifier.
     */
    @objid ("b85d61a4-11f1-4d8a-8744-e462570fd507")
    private Classifier createClassifier(final IModelingSession session, final String toCreate, final String className, final Package container) {
        if (toCreate.equals(I18nMessageService.getString("module.gui.classifierWizard.class"))) {
            return session.getModel().createClass(className, container);
        } else if (toCreate.equals(I18nMessageService.getString("module.gui.classifierWizard.component"))) {
            return session.getModel().createComponent(className, container);
        } else if (toCreate.equals(I18nMessageService.getString("module.gui.classifierWizard.node"))) {
            Node res = session.getModel().createNode();
            res.setOwner(container);
            res.setName(className);
            return res;
        } else if (toCreate.equals(I18nMessageService.getString("module.gui.classifierWizard.interface"))) {
            return session.getModel().createInterface(className, container);
        }
        return null;
    }

    /**
     * Get a java class from a name.
     * 
     * @param chosenClass a type name: Class, Component, Node or Interface.
     * @return the java class corresponding to the name, or <code>null</code> if it isn't a valid name.
     */
    @objid ("294467d2-a975-480f-9ea8-7e921a60600c")
    private java.lang.Class<? extends ModelTree> getClassifierTypeFromName(final String chosenClass) {
        if (chosenClass.equals(I18nMessageService.getString("module.gui.classifierWizard.class"))) {
            return Class.class;
        } else if (chosenClass.equals(I18nMessageService.getString("module.gui.classifierWizard.component"))) {
            return Component.class;
        } else if (chosenClass.equals(I18nMessageService.getString("module.gui.classifierWizard.node"))) {
            return Node.class;
        } else if (chosenClass.equals(I18nMessageService.getString("module.gui.classifierWizard.interface"))) {
            return Interface.class;
        }
        return null;
    }

    /**
     * Get the closest owner package of a ModelTree.
     */
    @objid ("e6f40101-41b3-4c06-abbc-cbf420966e75")
    private Package getContainerPackage(final ModelTree current) {
        ModelTree container;
        
        if (current == null) {
            return null;
        }
        
        container = current.getOwner();
        while (!(container instanceof Package)) {
            if (container instanceof Collaboration) {
                container = getContainerPackage((Collaboration) container);
            } else {
                container = getContainerPackage(container);
            }
        }
        return (Package) container;
    }

    /**
     * Get the closest owner package of an interaction.
     */
    @objid ("0dde64ef-b13e-4a0b-954d-873dac7f07b1")
    private Collaboration getLocalCollaboration(final Interaction current) {
        for (Collaboration c : current.getOwnedCollaboration()) {
            if (c.getName().equals("locals")) {
                return c;
            }
        }
        return null;
    }

    @objid ("3e799b8b-b812-49ee-a2ab-6ca26756319c")
    private void createStereotypesFromStereotypes(final ModelElement oldElt, final ModelElement newElt) {
        for (Stereotype stereo : oldElt.getExtension()) {
            // Add the stereotype to the element
            newElt.getExtension().add(stereo);
        }
    }

    @objid ("71022310-4d3d-4c8b-b753-ae27450dc986")
    private void createTaggedValuesFromTaggedValues(final IModelingSession session, final ModelElement oldElt, final ModelElement newElt) {
        for (TaggedValue tag : oldElt.getTag()) {
            try {
                String n = tag.getDefinition().getName();
                TaggedValue newTag = session.getModel().createTaggedValue("ModelerModule", n, newElt);
        
                newTag.setQualifier(tag.getQualifier());
        
                for (TagParameter param : tag.getActual()) {
                    session.getModel().createTagParameter(param.getValue(), newTag);
                }
            } catch (ExtensionNotFoundException e) {
                // The tag comes from the model itself, no exception is possible
                ModelerModuleModule.getInstance().getModuleContext().getLogService().error(e);
            }
        }
    }

    @objid ("633e036e-0c38-43e8-8653-a9de9bf21db3")
    private void createNotesFromNotes(final IModelingSession session, final ModelElement oldElt, final ModelElement newElt) {
        for (Note note : oldElt.getDescriptor()) {
            try {
                String n = note.getModel().getName();
                session.getModel().createNote("ModelerModule", n, newElt, note.getContent());
            } catch (ExtensionNotFoundException e) {
                // The note comes from the model itself, no exception is possible
                ModelerModuleModule.getInstance().getModuleContext().getLogService().error(e);
            }
        }
    }

}
