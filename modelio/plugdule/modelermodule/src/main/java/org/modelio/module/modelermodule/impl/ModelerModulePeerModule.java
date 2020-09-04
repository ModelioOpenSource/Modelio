package org.modelio.module.modelermodule.impl;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.api.module.context.configuration.IModuleAPIConfiguration;
import org.modelio.metamodel.uml.behavior.interactionModel.Lifeline;
import org.modelio.metamodel.uml.behavior.interactionModel.Message;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Transition;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.metamodel.uml.statik.AttributeLink;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.IModelerModuleStereotypes;
import org.modelio.module.modelermodule.api.ModelerModuleException;
import org.modelio.module.modelermodule.engine.InstanceUpdater;
import org.modelio.module.modelermodule.engine.InterfaceImplementer;
import org.modelio.module.modelermodule.engine.StateUpdater;
import org.modelio.vbasic.version.Version;

/**
 * Implementation of Module services
 * <br>When a module is built using the MDA Modeler tool, a public interface is generated and accessible for the other module developments.
 * <br>The main class that allows developpers to get specific module services has to implement the current interface.
 * <br>Each mda component brings a specific interface that inherit from this one and gives all the desired module services.
 */
@objid ("5be97239-5a0f-4638-bf87-7b6da12d40f6")
public class ModelerModulePeerModule implements IModelerModulePeerModule {
    @objid ("1b72348d-c13b-4f4d-b946-0888c7212984")
    private ModelerModuleModule module;

    @objid ("b1580f35-4163-4432-9c8a-1386318b9baa")
    private IModuleAPIConfiguration peerConfiguration;

    @objid ("a4d64196-cd60-4470-866b-7b1525d6666a")
    public ModelerModulePeerModule(final ModelerModuleModule statModuleModule, final IModuleAPIConfiguration peerConfiguration) {
        super();
        this.module = statModuleModule;
        this.peerConfiguration = peerConfiguration;
    }

    /**
     * @see org.modelio.api.module.IPeerModule#getConfiguration()
     */
    @objid ("b7135dfc-f383-4e90-b3ed-a505a522fab4")
    @Override
    public IModuleAPIConfiguration getConfiguration() {
        return this.peerConfiguration;
    }

    /**
     * @see org.modelio.api.module.IPeerModule#getDescription()
     */
    @objid ("ae4fed1b-a982-4d01-a1f6-4ef9c0d72c6e")
    @Override
    public String getDescription() {
        return this.module.getDescription();
    }

    /**
     * @see org.modelio.api.module.IPeerModule#getName()
     */
    @objid ("ae244e3a-f8af-475e-93b3-dbb4d613dbff")
    @Override
    public String getName() {
        return this.module.getName();
    }

    /**
     * @see org.modelio.api.module.IPeerModule#getVersion()
     */
    @objid ("9f021401-3424-4289-8b6f-5470c0903b1a")
    @Override
    public Version getVersion() {
        return this.module.getVersion();
    }

    /**
     * Create an attribute from an attribute link. If the class doesn't exists, it is also created.
     * @param attr The attribute link to create a new attribute from.
     * @return <code>true</code> when an attribute is created.
     * @throws org.modelio.module.modelermodule.api.ModelerModuleException when the attribute already exists, or when the base of the instance isn't a classifier.
     */
    @objid ("d9e8531d-fd67-4fde-9d3c-18b7a752355c")
    @Override
    public boolean createAttribute(final AttributeLink attr) throws ModelerModuleException {
        IModelingSession session = this.module.getModuleContext().getModelingSession();
        
        boolean result = false;
        
        try (ITransaction transaction = session.createTransaction("CreateAttribute")) {
            InstanceUpdater p = new InstanceUpdater();
            result = p.createAttribute(session, attr);
            transaction.commit();
        }
        return result;
    }

    /**
     * Create a classifier from an instance.
     * Includes creation of:
     * - ports from the instance ports.
     * - attributes from attribute links.
     * - operations from incoming messages.
     * @param inst The instance to create the classifier from.
     * @return <code>true</code> when a new classifier is created.
     * @throws org.modelio.module.modelermodule.api.ModelerModuleException When an error happens during creation.
     */
    @objid ("77c2dfaa-d10e-4d96-adf7-95c209abcd39")
    @Override
    public boolean createClassifier(final Instance inst) throws ModelerModuleException {
        IModelingSession session = this.module.getModuleContext().getModelingSession();
        
        boolean result = false;
        
        try (ITransaction transaction = session.createTransaction("CreateClassifierByLifeline")) {
        
            InstanceUpdater p = new InstanceUpdater();
            result = p.createClassifier(session, inst);
        
            transaction.commit();
        }
        return result;
    }

    /**
     * Create a classifier from a lifeline.
     * Includes creation of:
     * - an instance represented by the lifeline.
     * - ports from the instance ports.
     * - attributes from attribute links.
     * - operations from incoming messages.
     * @param ll The lifeline to create the classifier from.
     * @return <code>true</code> when a new classifier is created.
     * @throws org.modelio.module.modelermodule.api.ModelerModuleException When an error happens during creation.
     */
    @objid ("55fd1660-19f4-402c-aaef-dfdc189f51a7")
    @Override
    public boolean createClassifierByLifeline(final Lifeline ll) throws ModelerModuleException {
        IModelingSession session = this.module.getModuleContext().getModelingSession();
        
        boolean result = false;
        try (ITransaction transaction = session.createTransaction("CreateClassifierByLifeline")) {
            InstanceUpdater p = new InstanceUpdater();
            Instance inst = ll.getRepresented();
        
            if (inst != null) {
                result = p.createClassifier(session, inst);
            } else {
                result = p.createInstanceAndClassifier(session, ll);
            }            
            transaction.commit();
        }
        return result;
    }

    /**
     * Create an operation from a message.
     * An Instance might be created in the process, or a Classifier.
     * @param message The message to create the operation from.
     * @return <code>true</code> if a new operation is created.
     * @throws org.modelio.module.modelermodule.api.ModelerModuleException When an error happens during the creation.
     */
    @objid ("37254160-5f95-4cf4-8535-a8b446b10eb5")
    @Override
    public boolean createOperationFromMessage(final Message message) throws ModelerModuleException {
        IModelingSession session = this.module.getModuleContext().getModelingSession();
        
        boolean result = false;
        
        try (ITransaction transaction = session.createTransaction("CreateOperationFromMessage")) {
            InstanceUpdater p = new InstanceUpdater();
            result = p.createOperation(session, message);
        
            transaction.commit();
        }
        return result;
    }

    /**
     * Create an operation from a transition.
     * An Instance might be created in the process, or a Classifier.
     * @param transition The transition to create the operation from.
     * @return <code>true</code> if a new transition is created.
     * @throws org.modelio.module.modelermodule.api.ModelerModuleException When an error happens during the creation.
     */
    @objid ("094fa721-d9e9-48ec-b205-5464b63b9126")
    @Override
    public boolean createOperationFromTransition(final Transition transition) throws ModelerModuleException {
        IModelingSession session = this.module.getModuleContext().getModelingSession();
        
        boolean result = false;
        
        try (ITransaction transaction = session.createTransaction("CreateOperationFromTransition")) {
            InstanceUpdater p = new InstanceUpdater();
            result = p.createOperation(session, transition);
        
            transaction.commit();
        }
        return result;
    }

    /**
     * Create a sub state machine from a state having entry and exit points.
     * @param state the state to create the sub state machine from.
     * @return the create sub state machine.
     */
    @objid ("b03d862b-a329-43e4-9473-19911bf9b9c7")
    @Override
    public StateMachine createSubStateMachineFromCompositeState(final State state) {
        IModelingSession session = this.module.getModuleContext().getModelingSession();
        
        StateMachine result = null;
        try (ITransaction transaction = session.createTransaction("CreateStateMachineFromState")) {
            StateUpdater stateWizard = new StateUpdater();
            result = stateWizard.createSubStateMachineFromCompositeState(session, state);
        
            transaction.commit();
        }
        return result;
    }

    /**
     * Create Operations in those Classifiers from those defined in their implemented Interfaces.
     * @param classifiers The Classifiers to create the Operations in.
     */
    @objid ("e9eb9c44-826e-4f3f-8385-5159592284f3")
    @Override
    public void implementInterfaces(final List<Classifier> classifiers) {
        InterfaceImplementer interfaceManager = new InterfaceImplementer();
        IModelingSession session = this.module.getModuleContext().getModelingSession();
        
        try (ITransaction transaction = session.createTransaction("Update class from interfaces")) {
            boolean hasDoneWork = false;
            for (Element theElement : classifiers) {
                Classifier theClassifier = (Classifier) theElement;
        
                boolean newResult = interfaceManager.implementInterfaces(session, theClassifier);
                hasDoneWork = hasDoneWork || newResult;
            }
        
            if (hasDoneWork) {
                transaction.commit();
            } else {
                transaction.rollback();
            }
        }
    }

    /**
     * Delete Operations in Classifiers from those defined in their implemented Interfaces.
     * @param classifiers The Classifiers to remove the Operations from.
     */
    @objid ("0b6b5453-4f0b-45f5-9de1-69e2821fad8a")
    @Override
    public void unimplementInterfaces(final List<Classifier> classifiers) {
        InterfaceImplementer interfaceManager = new InterfaceImplementer();
        IModelingSession session = this.module.getModuleContext().getModelingSession();
        
        try (ITransaction transaction = session.createTransaction("Unimplement Interfaces in class")) {
            boolean hasDoneWork = false;
            for (Classifier theClassifier : classifiers) {
                boolean newResult = interfaceManager.unImplementInterfaces(theClassifier);
                hasDoneWork = hasDoneWork || newResult;
            }
        
            if (hasDoneWork) {
                transaction.commit();
            } else {
                transaction.rollback();
            }
        }
    }

    /**
     * For all classifiers implementing those interfaces, synchronize all operation signatures.
     * Missing operations are created.
     * @param interfaces the interfaces to update operations from.
     */
    @objid ("53d5c32d-d61b-4851-bc60-e712b6444f70")
    @Override
    public void updateClassesFromInterface(final List<Interface> interfaces) {
        InterfaceImplementer interfaceManager = new InterfaceImplementer();
        IModelingSession session = this.module.getModuleContext().getModelingSession();
        
        try (ITransaction transaction = session.createTransaction("Update classes from interface")) {
            boolean hasDoneWork = false;
            for ( Interface theInterface : interfaces) {
        
                boolean newResult = interfaceManager.updateImplementingClassifiers(session, theInterface);
                hasDoneWork = hasDoneWork || newResult;
            }
        
            if (hasDoneWork) {
                transaction.commit();
            } else {
                transaction.rollback();
            }
        }
    }

    /**
     * Update a part contents from its base classifier.
     * Allows creation of a new classifier if no base exists, or referencing an existing classifier.
     * @param inst the instance to update.
     * @throws org.modelio.module.modelermodule.api.ModelerModuleException when an error occurs during the update.
     */
    @objid ("c4b42f06-70fb-433c-a6a9-6d1a2e7ce86d")
    @Override
    public void updateInstanceFromClassifier(final Instance inst) throws ModelerModuleException {
        IModelingSession session = this.module.getModuleContext().getModelingSession();
        
        try (ITransaction transaction = session.createTransaction("UpdateFromClassifier")) {
            InstanceUpdater p = new InstanceUpdater();
            p.updatePartFromInstanciedClassifier(session, inst);
        
            transaction.commit();
        }
    }

    /**
     * Update a lifeline's represented instance contents from its base classifier.
     * Allows creation of the instance, and of a new classifier if no base exists, or referencing an existing classifier.
     * @param ll the lifeline to update.
     * @throws org.modelio.module.modelermodule.api.ModelerModuleException when an error occurs during the update.
     */
    @objid ("a2596171-fa75-4f84-a7b7-e2504db13885")
    @Override
    public void updateFromClassifierByLifeline(final Lifeline ll) throws ModelerModuleException {
        IModelingSession session = this.module.getModuleContext().getModelingSession();
        
        try (ITransaction transaction = session.createTransaction("UpdateFromClassifierByLifeline")) {
        
            InstanceUpdater p = new InstanceUpdater();
            Instance inst = ll.getRepresented();
        
            if (inst != null) {
                p.updatePartFromInstanciedClassifier(session, inst);
            } else {
                p.updateInstanceAndClassifier(session, ll);
            }
        
            transaction.commit();
        }
    }

    /**
     * Update the internal structure of a class.
     * Updates all parts from their base classifiers, and allows creation of all missing bases.
     * It is also possible to reference an existing classifier.
     * @param classToUpdate The class to update.
     * @throws org.modelio.module.modelermodule.api.ModelerModuleException When an error happens during the update.
     */
    @objid ("1b03f204-c85b-4c20-9d6d-817a5364deff")
    @Override
    public void updateInternalStructure(final Class classToUpdate) throws ModelerModuleException {
        IModelingSession session = this.module.getModuleContext().getModelingSession();
        
        try (ITransaction transaction = session.createTransaction("UpdateInternalStructure")) {
            InstanceUpdater p = new InstanceUpdater();
            p.updateInternalStructure(session, classToUpdate);
        
            transaction.commit();
        }
    }

    /**
     * Updates a state machine from a sub state machine. (entry, exit points)
     * @param state the state to update.
     */
    @objid ("d5c469b0-6f73-4336-b366-0da6363234bd")
    @Override
    public void updateStateFromStateMachine(final State state) throws ModelerModuleException {
        IModelingSession session = this.module.getModuleContext().getModelingSession();
        
        try (ITransaction transaction = session.createTransaction("UpdateStateFromStateMachine")) {
            StateUpdater updater = new StateUpdater();
            updater.updateStateFromStateMachine(session, state);
        
            transaction.commit();
        }
    }

    @objid ("260c5256-6d64-41b1-b7c0-1aa461202cb0")
    @Override
    public Path computePath(final Artifact fileArtifact) {
        if (!fileArtifact.isStereotyped(MODULE_NAME, IModelerModuleStereotypes.FILE)) {
            throw new InvalidParameterException("Artifact must have the " + IModelerModuleStereotypes.FILE + " stereotype.");
        }
        
        List<String> ownerPaths = new ArrayList<>();
        ModelTree owner = fileArtifact.getOwner();
        while (owner instanceof Artifact && owner.isStereotyped(MODULE_NAME, IModelerModuleStereotypes.DIRECTORY)) {
            ownerPaths.add(((Artifact)owner).getFileName());
            owner = owner.getOwner();
        }
        ownerPaths.add(fileArtifact.getFileName());
        
        String first = ownerPaths.remove(0);
        return Paths.get(first, ownerPaths.toArray(new String[0]));
    }

}
