/* 
 * Copyright 2013-2018 Modeliosoft
 * 
 * This file is part of Modelio.
 * 
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Modelio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package org.modelio.api.impl.model;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IDefaultNameService;
import org.modelio.api.modelio.model.IUMLTypes;
import org.modelio.api.modelio.model.IUmlModel;
import org.modelio.gproject.fragment.IProjectFragment;
import org.modelio.gproject.gproject.GProject;
import org.modelio.metamodel.bpmn.activities.BpmnActivity;
import org.modelio.metamodel.bpmn.activities.BpmnAdHocSubProcess;
import org.modelio.metamodel.bpmn.activities.BpmnBusinessRuleTask;
import org.modelio.metamodel.bpmn.activities.BpmnCallActivity;
import org.modelio.metamodel.bpmn.activities.BpmnComplexBehaviorDefinition;
import org.modelio.metamodel.bpmn.activities.BpmnManualTask;
import org.modelio.metamodel.bpmn.activities.BpmnMultiInstanceLoopCharacteristics;
import org.modelio.metamodel.bpmn.activities.BpmnReceiveTask;
import org.modelio.metamodel.bpmn.activities.BpmnScriptTask;
import org.modelio.metamodel.bpmn.activities.BpmnSendTask;
import org.modelio.metamodel.bpmn.activities.BpmnServiceTask;
import org.modelio.metamodel.bpmn.activities.BpmnStandardLoopCharacteristics;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.activities.BpmnTask;
import org.modelio.metamodel.bpmn.activities.BpmnTransaction;
import org.modelio.metamodel.bpmn.activities.BpmnUserTask;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnCollaborationDiagram;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnProcessDesignDiagram;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnSubProcessDiagram;
import org.modelio.metamodel.bpmn.bpmnService.BpmnEndPoint;
import org.modelio.metamodel.bpmn.bpmnService.BpmnInterface;
import org.modelio.metamodel.bpmn.bpmnService.BpmnOperation;
import org.modelio.metamodel.bpmn.events.BpmnBoundaryEvent;
import org.modelio.metamodel.bpmn.events.BpmnCancelEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnCompensateEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnConditionalEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnEndEvent;
import org.modelio.metamodel.bpmn.events.BpmnErrorEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnEscalationEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnImplicitThrowEvent;
import org.modelio.metamodel.bpmn.events.BpmnIntermediateCatchEvent;
import org.modelio.metamodel.bpmn.events.BpmnIntermediateThrowEvent;
import org.modelio.metamodel.bpmn.events.BpmnLinkEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnMessageEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnSignalEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnStartEvent;
import org.modelio.metamodel.bpmn.events.BpmnTerminateEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnTimerEventDefinition;
import org.modelio.metamodel.bpmn.flows.BpmnMessage;
import org.modelio.metamodel.bpmn.flows.BpmnMessageFlow;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.gateways.BpmnComplexGateway;
import org.modelio.metamodel.bpmn.gateways.BpmnEventBasedGateway;
import org.modelio.metamodel.bpmn.gateways.BpmnExclusiveGateway;
import org.modelio.metamodel.bpmn.gateways.BpmnInclusiveGateway;
import org.modelio.metamodel.bpmn.gateways.BpmnParallelGateway;
import org.modelio.metamodel.bpmn.objects.BpmnDataAssociation;
import org.modelio.metamodel.bpmn.objects.BpmnDataInput;
import org.modelio.metamodel.bpmn.objects.BpmnDataObject;
import org.modelio.metamodel.bpmn.objects.BpmnDataOutput;
import org.modelio.metamodel.bpmn.objects.BpmnDataState;
import org.modelio.metamodel.bpmn.objects.BpmnDataStore;
import org.modelio.metamodel.bpmn.objects.BpmnItemDefinition;
import org.modelio.metamodel.bpmn.objects.BpmnSequenceFlowDataAssociation;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnCollaboration;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLaneSet;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnParticipant;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.resources.BpmnResource;
import org.modelio.metamodel.bpmn.resources.BpmnResourceParameter;
import org.modelio.metamodel.bpmn.resources.BpmnResourceParameterBinding;
import org.modelio.metamodel.bpmn.resources.BpmnResourceRole;
import org.modelio.metamodel.bpmn.rootElements.BpmnAssociation;
import org.modelio.metamodel.bpmn.rootElements.BpmnGroup;
import org.modelio.metamodel.bpmn.rootElements.BpmnSharedDefinitions;
import org.modelio.metamodel.diagrams.ActivityDiagram;
import org.modelio.metamodel.diagrams.ClassDiagram;
import org.modelio.metamodel.diagrams.CommunicationDiagram;
import org.modelio.metamodel.diagrams.CompositeStructureDiagram;
import org.modelio.metamodel.diagrams.DeploymentDiagram;
import org.modelio.metamodel.diagrams.DiagramSet;
import org.modelio.metamodel.diagrams.ObjectDiagram;
import org.modelio.metamodel.diagrams.SequenceDiagram;
import org.modelio.metamodel.diagrams.StateMachineDiagram;
import org.modelio.metamodel.diagrams.StaticDiagram;
import org.modelio.metamodel.diagrams.UseCaseDiagram;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.behavior.activityModel.AcceptCallEventAction;
import org.modelio.metamodel.uml.behavior.activityModel.AcceptChangeEventAction;
import org.modelio.metamodel.uml.behavior.activityModel.AcceptSignalAction;
import org.modelio.metamodel.uml.behavior.activityModel.AcceptTimeEventAction;
import org.modelio.metamodel.uml.behavior.activityModel.Activity;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityFinalNode;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityParameterNode;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityPartition;
import org.modelio.metamodel.uml.behavior.activityModel.CallBehaviorAction;
import org.modelio.metamodel.uml.behavior.activityModel.CallOperationAction;
import org.modelio.metamodel.uml.behavior.activityModel.CentralBufferNode;
import org.modelio.metamodel.uml.behavior.activityModel.Clause;
import org.modelio.metamodel.uml.behavior.activityModel.ConditionalNode;
import org.modelio.metamodel.uml.behavior.activityModel.ControlFlow;
import org.modelio.metamodel.uml.behavior.activityModel.DataStoreNode;
import org.modelio.metamodel.uml.behavior.activityModel.DecisionMergeNode;
import org.modelio.metamodel.uml.behavior.activityModel.ExceptionHandler;
import org.modelio.metamodel.uml.behavior.activityModel.ExpansionNode;
import org.modelio.metamodel.uml.behavior.activityModel.ExpansionRegion;
import org.modelio.metamodel.uml.behavior.activityModel.FlowFinalNode;
import org.modelio.metamodel.uml.behavior.activityModel.ForkJoinNode;
import org.modelio.metamodel.uml.behavior.activityModel.InitialNode;
import org.modelio.metamodel.uml.behavior.activityModel.InputPin;
import org.modelio.metamodel.uml.behavior.activityModel.InstanceNode;
import org.modelio.metamodel.uml.behavior.activityModel.InterruptibleActivityRegion;
import org.modelio.metamodel.uml.behavior.activityModel.LoopNode;
import org.modelio.metamodel.uml.behavior.activityModel.MessageFlow;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectFlow;
import org.modelio.metamodel.uml.behavior.activityModel.OpaqueAction;
import org.modelio.metamodel.uml.behavior.activityModel.OutputPin;
import org.modelio.metamodel.uml.behavior.activityModel.SendSignalAction;
import org.modelio.metamodel.uml.behavior.activityModel.StructuredActivityNode;
import org.modelio.metamodel.uml.behavior.activityModel.ValuePin;
import org.modelio.metamodel.uml.behavior.commonBehaviors.BehaviorParameter;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Event;
import org.modelio.metamodel.uml.behavior.commonBehaviors.OpaqueBehavior;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationChannel;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationInteraction;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationMessage;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationNode;
import org.modelio.metamodel.uml.behavior.interactionModel.CombinedFragment;
import org.modelio.metamodel.uml.behavior.interactionModel.DurationConstraint;
import org.modelio.metamodel.uml.behavior.interactionModel.ExecutionOccurenceSpecification;
import org.modelio.metamodel.uml.behavior.interactionModel.ExecutionSpecification;
import org.modelio.metamodel.uml.behavior.interactionModel.Gate;
import org.modelio.metamodel.uml.behavior.interactionModel.GeneralOrdering;
import org.modelio.metamodel.uml.behavior.interactionModel.Interaction;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionOperand;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionOperator;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionUse;
import org.modelio.metamodel.uml.behavior.interactionModel.Lifeline;
import org.modelio.metamodel.uml.behavior.interactionModel.Message;
import org.modelio.metamodel.uml.behavior.interactionModel.MessageSort;
import org.modelio.metamodel.uml.behavior.interactionModel.PartDecomposition;
import org.modelio.metamodel.uml.behavior.interactionModel.StateInvariant;
import org.modelio.metamodel.uml.behavior.stateMachineModel.ChoicePseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.ConnectionPointReference;
import org.modelio.metamodel.uml.behavior.stateMachineModel.DeepHistoryPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.EntryPointPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.ExitPointPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.FinalState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.ForkPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.InitialPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.InternalTransition;
import org.modelio.metamodel.uml.behavior.stateMachineModel.JoinPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.JunctionPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Region;
import org.modelio.metamodel.uml.behavior.stateMachineModel.ShallowHistoryPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.metamodel.uml.behavior.stateMachineModel.TerminatePseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Transition;
import org.modelio.metamodel.uml.behavior.usecaseModel.Actor;
import org.modelio.metamodel.uml.behavior.usecaseModel.ExtensionPoint;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCaseDependency;
import org.modelio.metamodel.uml.informationFlow.DataFlow;
import org.modelio.metamodel.uml.informationFlow.InformationFlow;
import org.modelio.metamodel.uml.informationFlow.InformationItem;
import org.modelio.metamodel.uml.infrastructure.AbstractProject;
import org.modelio.metamodel.uml.infrastructure.Abstraction;
import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.Document;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ExternProcessor;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.infrastructure.ResourceType;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.Substitution;
import org.modelio.metamodel.uml.infrastructure.TagParameter;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.metamodel.uml.infrastructure.Usage;
import org.modelio.metamodel.uml.infrastructure.matrix.MatrixDefinition;
import org.modelio.metamodel.uml.infrastructure.matrix.MatrixValueDefinition;
import org.modelio.metamodel.uml.infrastructure.matrix.QueryDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.EnumeratedPropertyType;
import org.modelio.metamodel.uml.infrastructure.properties.LocalPropertyTable;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyEnumerationLitteral;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTable;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyType;
import org.modelio.metamodel.uml.infrastructure.properties.TypedPropertyTable;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.AttributeLink;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Binding;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.ClassAssociation;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.metamodel.uml.statik.CollaborationUse;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.Connector;
import org.modelio.metamodel.uml.statik.ConnectorEnd;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.metamodel.uml.statik.ElementImport;
import org.modelio.metamodel.uml.statik.ElementRealization;
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.metamodel.uml.statik.EnumerationLiteral;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.Generalization;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.InterfaceRealization;
import org.modelio.metamodel.uml.statik.Link;
import org.modelio.metamodel.uml.statik.LinkEnd;
import org.modelio.metamodel.uml.statik.Manifestation;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.NaryAssociation;
import org.modelio.metamodel.uml.statik.NaryAssociationEnd;
import org.modelio.metamodel.uml.statik.NaryConnector;
import org.modelio.metamodel.uml.statik.NaryConnectorEnd;
import org.modelio.metamodel.uml.statik.NaryLink;
import org.modelio.metamodel.uml.statik.NaryLinkEnd;
import org.modelio.metamodel.uml.statik.Node;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.uml.statik.PackageImport;
import org.modelio.metamodel.uml.statik.PackageMerge;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.metamodel.uml.statik.ProvidedInterface;
import org.modelio.metamodel.uml.statik.RaisedException;
import org.modelio.metamodel.uml.statik.RequiredInterface;
import org.modelio.metamodel.uml.statik.TemplateBinding;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.metamodel.uml.statik.TemplateParameterSubstitution;
import org.modelio.vcore.session.api.model.IModel;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * The UMLModel provide navigation and modification in the UML model represented in Modelio.
 * <p>
 * To navigate the UML model use the {@linkplain #getModelRoots()} method. this method give access to the root Package of the model. The whole model can be navigate through the associations that links the model elements to each other.
 * </p>
 * <p>
 * The UMLModel is also the factory which create the UML model elements.
 * </p>
 * <p>
 * Any model modification must be done in a transaction. It is the MDAC developer responsibility to ensure that the newly created metaclass is valid before commiting a transaction.
 * </p>
 * <p>
 * New UML model elements can easily be created using <i>createXxxxx()</i> methods where Xxxxx is the name of the metaclass for which an instance has to be created. At least one method <i>createXxxxx()</i> exists for UML model Element. i.e.:
 * {@linkplain #createClass()}. This method only create an instance of the metaclass. The instance will have to be attached to the model and eventually named to be valid for the current transaction.
 * </p>
 * <p>
 * The factory provide some convenience methods that allow to directly create a valid instance for the most frequently used metaclasses. i.e.: {@linkplain #createClass(String name, NameSpace owner)},
 * {@linkplain #createClass(String name, NameSpace owner, Stereotype stereotype)} . These methods ensure the validity of the created element.
 * </p>
 */
@objid ("6caafdc5-72e0-41b5-9b35-86642b3f4eab")
public class UMLModel implements IUmlModel {
    @objid ("0ddc6593-7322-4631-91aa-24f2f509bd92")
    private IModel model;

    @objid ("62ddf749-4c47-47b8-b50f-3be9763e86af")
    private IMModelServices modelService;

    @objid ("0ea1948c-0851-4396-bdf2-a3c88b0198f7")
    private GProject openedProject;

    @objid ("087f5657-bec3-4dbd-ab3f-498128b038b7")
    private IUMLTypes umlTypes;

    /**
     * Create a new instance of UMLModel.
     * @param openedProject the opened project.
     * @param modelService the model object factory.
     * @param model the model access
     */
    @objid ("d4fb7a4d-87e9-4a59-8d69-ff16c3c345f1")
    public UMLModel(final GProject openedProject, final IMModelServices modelService, final IModel model) {
        this.model = model;
        this.modelService = modelService;
        this.openedProject = openedProject;
        this.umlTypes = new UMLTypes(this.model);
    }

    /**
     * Create an Abstraction. The created Abstraction has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IAbstraction representing the Abstraction in the Model.
     * @return An IAbstraction representing the Abstraction in the Model.
     */
    @objid ("b809f030-eb03-48ba-9881-efccdcf1edb9")
    @Override
    public Abstraction createAbstraction() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createAbstraction();
    }

    /**
     * Create an AcceptCallEventAction. The created AcceptCallEventAction has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IAcceptCallEventAction representing the
     * AcceptCallEventAction in the Model.
     * @return An IAcceptCallEventAction representing the AcceptCallEventAction in the Model.
     */
    @objid ("3f02497b-5a82-4fdc-9e4e-630e442db6e9")
    @Override
    public AcceptCallEventAction createAcceptCallEventAction() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createAcceptCallEventAction();
    }

    /**
     * Create an AcceptChangeEventAction. The created AcceptChangeEventAction has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IAcceptChangeEventAction representing the
     * AcceptChangeEventAction in the Model.
     * @return An IAcceptChangeEventAction representing the AcceptChangeEventAction in the Model.
     */
    @objid ("39c6a5c5-3550-4b28-af35-b37fb89d689c")
    @Override
    public AcceptChangeEventAction createAcceptChangeEventAction() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createAcceptChangeEventAction();
    }

    /**
     * Create an AcceptSignalAction. The created AcceptSignalAction has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IAcceptSignalAction representing the AcceptSignalAction in the
     * Model.
     * @return An IAcceptSignalAction representing the AcceptSignalAction in the Model.
     */
    @objid ("c376ab73-fda8-47f6-91a7-9e1957761c7f")
    @Override
    public AcceptSignalAction createAcceptSignalAction() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createAcceptSignalAction();
    }

    /**
     * Create an AcceptTimeEventAction. The created AcceptTimeEventAction has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IAcceptTimeEventAction representing the
     * AcceptTimeEventAction in the Model.
     * @return An IAcceptTimeEventAction representing the AcceptTimeEventAction in the Model.
     */
    @objid ("b3aa44c3-cc82-41c9-add7-14fb9f5cc041")
    @Override
    public AcceptTimeEventAction createAcceptTimeEventAction() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createAcceptTimeEventAction();
    }

    /**
     * Create an Activity. The created Activity has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IActivity representing the Abstraction in the Model.
     * @return An IActivity representing the Activity in the Model.
     */
    @objid ("7a4d0b77-5f66-4b2b-a35a-9428708a37a1")
    @Override
    public Activity createActivity() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createActivity();
    }

    /**
     * Create an ActivityDiagram. The returned object is an IActivityDiagram owned by 'owner' and named by 'name'
     * @param name the name of the ActivityDiagram to be created.
     * @param owner the ModelElement that will contain the ActivityDiagram.
     * @return An IActivityDiagram representing the diagram in the Model.
     */
    @objid ("b2f26a10-e2c0-45f6-90cb-d6ab65cc04b9")
    @Override
    public ActivityDiagram createActivityDiagram(final String name, final ModelElement owner) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createActivityDiagram(name, owner);
    }

    /**
     * Create an ActivityFinalNode. The created ActivityFinalNode has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IActivityFinalNode representing the ActivityFinalNode in the Model.
     * @return An IActivityFinalNode representing the ActivityFinalNode in the Model.
     */
    @objid ("f85c11ce-59f3-4f2b-b064-bd6620dfdb14")
    @Override
    public ActivityFinalNode createActivityFinalNode() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createActivityFinalNode();
    }

    /**
     * Create an ActivityParameterNode. The created ActivityParameterNode has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IActivityParameterNode representing the
     * ActivityParameterNode in the Model.
     * @return An IActivityParameterNode representing the ActivityParameterNode in the Model.
     */
    @objid ("3dbab2a0-f1c5-4f1f-b6dd-e0cd076f9065")
    @Override
    public ActivityParameterNode createActivityParameterNode() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createActivityParameterNode();
    }

    /**
     * Create an ActivityPartition. The created ActivityPartition has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IActivityPartition representing the ActivityPartition in the Model.
     * @return An IActivityPartition representing the ActivityPartition in the Model.
     */
    @objid ("bcf0bcab-7957-4a55-babe-eac4fa21bafa")
    @Override
    public ActivityPartition createActivityPartition() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createActivityPartition();
    }

    /**
     * Create an Actor. The created Actor has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IActor representing the Abstraction in the Model.
     * @return An IActor representing the Actor in the Model.
     */
    @objid ("d1025971-4ee4-468b-b3e4-d711b69f2329")
    @Override
    public Actor createActor() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createActor();
    }

    /**
     * Create an Actor. The created Actor is valid for the current transaction. (It as a name and an owner.)
     * @param name The name of the Actor to be created.
     * @param owner The NameSpace that will contain the Actor.
     * @return An IActor representing the Actor in the Model.
     */
    @objid ("ba7c8315-caf3-4d19-b39c-40a85855d4eb")
    @Override
    public Actor createActor(final String name, final NameSpace owner) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createActor(name, owner);
    }

    /**
     * Create an Actor with the desired name and stereotype.
     * <p>
     * <p>
     * The created Actor is valid for the current transaction. (It as a name and an owner.)
     * <p>
     * <p>
     * The created Actor is stereotyped by the <code>stereotype</code> argument. The <code>ExtensionNotFoundException</code> is thrown if the stereotype is not found.
     * @param name The name of the Actor to be created.
     * @param owner The NameSpace that will contain the Actor.
     * @param stereotype the Stereotype that will be refered by the Actor.
     * @return An IActor representing the Actor in the Model.
     */
    @objid ("28c7948e-cad8-4ffb-84c1-a7f3084c9e6f")
    @Override
    public Actor createActor(final String name, final NameSpace owner, final Stereotype stereotype) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createActor(name, owner, stereotype);
    }

    @objid ("be5cde4f-6405-499d-a2cc-3544576107fd")
    @Override
    public Actor createActor(final String name, final NameSpace owner, final String moduleName, final String stereotypeName) throws ExtensionNotFoundException {
        try {
            return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createActor(name, owner, moduleName, stereotypeName);
        } catch (ExtensionNotFoundException e) {
            throw new ExtensionNotFoundException(e);
        }
    }

    /**
     * Create an Artifact. The created Artifact has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IArtifact representing the Abstraction in the Model.
     * @return An IArtifact representing the Artifact in the Model.
     */
    @objid ("d5b70a03-c289-4621-8c46-bf3eb8db76b5")
    @Override
    public Artifact createArtifact() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createArtifact();
    }

    /**
     * Create an Artifact. The created Artifact is valid for the current transaction. (It as a name and an owner.)
     * @param name The name of the Artifact to be created.
     * @param owner The NameSpace that will contain the Artifact.
     * @return An IArtifact representing the Artifact in the Model.
     */
    @objid ("ee4db1ca-fbcc-494d-83ee-706d034ff52e")
    @Override
    public Artifact createArtifact(final String name, final NameSpace owner) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createArtifact(name, owner);
    }

    /**
     * Create an Artifact. The created Artifact has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IArtifact representing the Abstraction in the Model. The created Artifact is
     * stereotyped by the <code>stereotype</code> argument. The <code>ExtensionNotFoundException</code> is thrown if the stereotype is not found.
     * @param name Name of the Artifact
     * @param owner The Owner of the Artifact
     * @param stereotype the stereotype to add on the created Artifact or null if none.
     * @return An IArtifact representing the Artifact in the Model.
     */
    @objid ("b771de11-70c2-4398-83bd-346832b22e7a")
    @Override
    public Artifact createArtifact(final String name, final NameSpace owner, final Stereotype stereotype) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createArtifact(name, owner, stereotype);
    }

    @objid ("6be76c23-a375-45dc-8059-44399fff8d1d")
    @Override
    public Artifact createArtifact(final String name, final NameSpace owner, final String moduleName, final String stereotypeName) throws ExtensionNotFoundException {
        try {
            return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createArtifact(name, owner, moduleName, stereotypeName);
        } catch (ExtensionNotFoundException e) {
            throw new ExtensionNotFoundException(e);
        }
    }

    /**
     * Create a binary association. This method create a binary association between two classifiers and give a name to the destination role. The created association has a navigable role.
     * @param source the source Classifier of the Association.
     * @param destination the destination Classifier of the Association.
     * @param destinationRole the Name of the destination role.
     * @return An IAssociation representing the Association in the Model.
     */
    @objid ("c89dea81-d537-4612-8b91-34ad8147828e")
    @Override
    public Association createAssociation(final Classifier source, final Classifier destination, final String destinationRole) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createAssociation(source, destination, destinationRole);
    }

    /**
     * Create an Association. The created Association has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an Association representing the Association in the Model.
     * @return An Association representing the Association in the Model.
     */
    @objid ("94e16813-a429-46b9-bf91-1dab5b3c3c75")
    @Override
    public Association createAssociation() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createAssociation();
    }

    /**
     * Create an AssociationEnd. The created AssociationEnd has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an AssociationEnd representing the Abstraction in the Model.
     * @return An AssociationEnd representing the AssociationEnd in the Model.
     */
    @objid ("b5f1e758-6dcc-4266-8a4c-c500036d5351")
    @Override
    public AssociationEnd createAssociationEnd() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createAssociationEnd();
    }

    /**
     * Create an Attribute. The created Attribute has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IAttribute representing the Abstraction in the Model.
     * @return An IAttribute representing the Attribute in the Model.
     */
    @objid ("bda33d76-3966-42ec-a53c-2606631941eb")
    @Override
    public Attribute createAttribute() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createAttribute();
    }

    /**
     * Create an Attribute. The Attribute is created on owner and has a name, a type.
     * @param name the name of the Attribute to be created.
     * @param type the GeneralClass that will type the Atrribute.
     * @param owner the Classifier that will contain the Attribute
     * @return An IAttribute representing the Attribute in the Model.
     */
    @objid ("123e42c1-80b4-4e2e-b292-a48f8b0454d7")
    @Override
    public Attribute createAttribute(final String name, final GeneralClass type, final Classifier owner) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createAttribute(name, type, owner);
    }

    /**
     * Create a stereotyped Attribute. The Attribute is created on owner and has a name, a type and a stereotype.
     * @param name the name of the Attribute to be created.
     * @param type the GeneralClass that will type the Atrribute.
     * @param owner the Classifier that will contain the Attribute.
     * @param stereotype the name of the Stereotype that will be refered by the Attribute.
     * @return An IAttribute representing the Attribute in the Model.
     */
    @objid ("615e2916-841c-48a2-95f4-096135ab40ad")
    @Override
    public Attribute createAttribute(final String name, final GeneralClass type, final Classifier owner, final Stereotype stereotype) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createAttribute(name, type, owner, stereotype);
    }

    @objid ("6d8e43f9-cea0-43e7-88f7-3cedbddbe1f3")
    @Override
    public Attribute createAttribute(final String name, final GeneralClass type, final Classifier owner, final String moduleName, final String stereotypeName) throws ExtensionNotFoundException {
        try {
            return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createAttribute(name, type, owner, moduleName, stereotypeName);
        } catch (ExtensionNotFoundException e) {
            throw new ExtensionNotFoundException(e);
        }
    }

    /**
     * Create an AttributeLink. The created AttributeLink has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IAttributeLink representing the Abstraction in the Model.
     * @return An IAttributeLink representing the AttributeLink in the Model.
     */
    @objid ("07e18f84-501f-4906-82a6-4de97fa022d1")
    @Override
    public AttributeLink createAttributeLink() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createAttributeLink();
    }

    /**
     * Create a BehaviorParameter. The created BehaviorParameter has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IBehaviorParameter representing the BehaviorParameter in the Model.
     * @return An IBehaviorParameter representing the BehaviorParameter in the Model.
     */
    @objid ("d7e2f272-7ac8-48c0-a42a-cd07a636688f")
    @Override
    public BehaviorParameter createBehaviorParameter() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBehaviorParameter();
    }

    /**
     * Create a BindableInstance. The created BindableInstance has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an BindableInstance representing the Abstraction in the Model.
     * @return An BindableInstance representing the BindableInstance in the Model.
     */
    @objid ("b5952b1e-b187-4bd7-afb1-9042991b4535")
    @Override
    public BindableInstance createBindableInstance() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBindableInstance();
    }

    /**
     * Create a Binding. The created Binding has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IBinding representing the Abstraction in the Model.
     * @return An IBinding representing the Binding in the Model.
     */
    @objid ("2194e569-3395-4f76-85f7-08738f939b02")
    @Override
    public Binding createBinding() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBinding();
    }

    @objid ("940e7185-195e-4dba-ba26-acb50ccebf09")
    @Override
    public BpmnActivity createBpmnActivity() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnActivity();
    }

    @objid ("99392a8f-d87a-49c1-af2c-467b8a60cd72")
    @Override
    public BpmnAdHocSubProcess createBpmnAdHocSubProcess() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnAdHocSubProcess();
    }

    @objid ("abbf4b29-cc51-4567-9815-1282e4bf9c56")
    @Override
    public BpmnAssociation createBpmnAssociation() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnAssociation();
    }

    @objid ("80c198f9-69c5-457d-95d8-c62221a74ae3")
    @Override
    public BpmnBoundaryEvent createBpmnBoundaryEvent() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnBoundaryEvent();
    }

    @objid ("6f04cb16-44b7-45c6-ac44-6f6d8bde825b")
    @Override
    public BpmnBusinessRuleTask createBpmnBusinessRuleTask() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnBusinessRuleTask();
    }

    @objid ("a9dc16b9-17ad-4d04-bc11-072831282c7d")
    @Override
    public BpmnCallActivity createBpmnCallActivity() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnCallActivity();
    }

    @objid ("2e12726a-ff59-407f-93c4-ce15c10ff370")
    @Override
    public BpmnCancelEventDefinition createBpmnCancelEventDefinition() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnCancelEventDefinition();
    }

    @objid ("9ed37f33-1fa8-42f8-b41d-4fa77c62720c")
    @Override
    public BpmnCollaboration createBpmnCollaboration() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnCollaboration();
    }

    @objid ("9ddac939-5a1e-4cbe-9135-65f521b2d7db")
    @Override
    public BpmnCollaborationDiagram createBpmnCollaborationDiagram() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnCollaborationDiagram();
    }

    @objid ("b615280b-c8ee-4de8-980f-812b277aa7c5")
    @Override
    public BpmnCompensateEventDefinition createBpmnCompensateEventDefinition() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnCompensateEventDefinition();
    }

    @objid ("af557d61-1740-4b9d-ad14-f4a49dae54f8")
    @Override
    public BpmnComplexBehaviorDefinition createBpmnComplexBehaviorDefinition() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnComplexBehaviorDefinition();
    }

    @objid ("d078b831-5074-4518-8fba-be9e403a1006")
    @Override
    public BpmnComplexGateway createBpmnComplexGateway() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnComplexGateway();
    }

    @objid ("ccca606e-4c60-4725-b266-85b2da21d97b")
    @Override
    public BpmnConditionalEventDefinition createBpmnConditionalEventDefinition() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnConditionalEventDefinition();
    }

    @objid ("9f378ea5-d091-4f47-8aa4-e4882a0dfe56")
    @Override
    public BpmnDataAssociation createBpmnDataAssociation() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnDataAssociation();
    }

    @objid ("ad7f056c-8b14-472c-b3c0-dfec6b9a286a")
    @Override
    public BpmnDataInput createBpmnDataInput() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnDataInput();
    }

    @objid ("2e95676c-676e-4141-9872-7dd083248472")
    @Override
    public BpmnDataObject createBpmnDataObject() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnDataObject();
    }

    @objid ("b596f13d-aecc-41c4-8050-39a0eb4757ce")
    @Override
    public BpmnDataOutput createBpmnDataOutput() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnDataOutput();
    }

    @objid ("101ea253-0bac-4bea-8c8e-6585d3f31c21")
    @Override
    public BpmnDataState createBpmnDataState() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnDataState();
    }

    @objid ("c1e49dbd-90ca-41d9-b222-f873543d37a5")
    @Override
    public BpmnDataStore createBpmnDataStore() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnDataStore();
    }

    @objid ("0246504d-36fd-4356-820c-acbfcc40b8aa")
    @Override
    public BpmnEndEvent createBpmnEndEvent() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnEndEvent();
    }

    @objid ("de8d9746-cf08-48ab-9618-5e5e4360dafc")
    @Override
    public BpmnEndPoint createBpmnEndPoint() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnEndPoint();
    }

    @objid ("aa4468eb-dbfb-4487-af0c-be72bcfb947b")
    @Override
    public BpmnErrorEventDefinition createBpmnErrorEventDefinition() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnErrorEventDefinition();
    }

    @objid ("e275d9d6-68e6-437b-8659-ba8651ddc7f5")
    @Override
    public BpmnEscalationEventDefinition createBpmnEscalationEventDefinition() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnEscalationEventDefinition();
    }

    @objid ("0b1210a1-68d4-4fe8-b016-d1a90bcbd8d7")
    @Override
    public BpmnEventBasedGateway createBpmnEventBasedGateway() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnEventBasedGateway();
    }

    @objid ("1902445d-c836-41e1-8da4-173e61c32b2f")
    @Override
    public BpmnExclusiveGateway createBpmnExclusiveGateway() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnExclusiveGateway();
    }

    @objid ("2bb1d426-be5b-47bf-b9e9-5da3852c80c5")
    @Override
    public BpmnGroup createBpmnGroup() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnGroup();
    }

    @objid ("a5d1c3bb-6b6d-4f6b-b58e-41922c85faa4")
    @Override
    public BpmnImplicitThrowEvent createBpmnImplicitThrowEvent() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnImplicitThrowEvent();
    }

    @objid ("fe2edb37-aa4b-4bcc-b871-321c449ebd0c")
    @Override
    public BpmnInclusiveGateway createBpmnInclusiveGateway() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnInclusiveGateway();
    }

    @objid ("40eaae94-0b3c-48c4-84bf-708dcfc0ccf6")
    @Override
    public BpmnInterface createBpmnInterface() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnInterface();
    }

    @objid ("96777f85-b711-44d1-90d1-6f8553dd9fbf")
    @Override
    public BpmnIntermediateCatchEvent createBpmnIntermediateCatchEvent() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnIntermediateCatchEvent();
    }

    @objid ("e3e5a9e4-e9cb-4476-88bd-92b75a79629f")
    @Override
    public BpmnIntermediateThrowEvent createBpmnIntermediateThrowEvent() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnIntermediateThrowEvent();
    }

    @objid ("24cca37b-477f-45af-b847-7c8b234eca33")
    @Override
    public BpmnItemDefinition createBpmnItemDefinition() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnItemDefinition();
    }

    @objid ("fd6db924-74d2-4195-9c64-500e4562ef04")
    @Override
    public BpmnLane createBpmnLane() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnLane();
    }

    @objid ("66c07dd6-15c1-4520-93d0-23628de43bee")
    @Override
    public BpmnLaneSet createBpmnLaneSet() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnLaneSet();
    }

    @objid ("79f50287-6217-435a-822a-252d590f26a4")
    @Override
    public BpmnLinkEventDefinition createBpmnLinkEventDefinition() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnLinkEventDefinition();
    }

    @objid ("aa96ad71-eab1-4213-8a46-3e8c471fa350")
    @Override
    public BpmnManualTask createBpmnManualTask() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnManualTask();
    }

    @objid ("292963a5-4bce-4da1-93bc-cd53bb92609e")
    @Override
    public BpmnMessage createBpmnMessage() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnMessage();
    }

    @objid ("9619fcb3-045e-40ef-8349-b9e7650c2f31")
    @Override
    public BpmnMessageEventDefinition createBpmnMessageEventDefinition() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnMessageEventDefinition();
    }

    @objid ("4b32acd8-b81e-496a-8e9c-ae0ded26f996")
    @Override
    public BpmnMessageFlow createBpmnMessageFlow() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnMessageFlow();
    }

    @objid ("4ca59a48-78d5-412c-b82a-763fbec903d5")
    @Override
    public BpmnMultiInstanceLoopCharacteristics createBpmnMultiInstanceLoopCharacteristics() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnMultiInstanceLoopCharacteristics();
    }

    @objid ("21a906d8-c1a5-4e77-8b62-156033a715ea")
    @Override
    public BpmnOperation createBpmnOperation() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnOperation();
    }

    @objid ("9c55de46-3c3a-43d4-9246-5329312bc915")
    @Override
    public BpmnParallelGateway createBpmnParallelGateway() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnParallelGateway();
    }

    @objid ("c0460184-1d6b-405a-a414-3ea8e29e440b")
    @Override
    public BpmnParticipant createBpmnParticipant() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnParticipant();
    }

    @objid ("ffff88f7-16e7-4343-b419-6e6702cae37c")
    @Override
    public BpmnProcess createBpmnProcess() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnProcess();
    }

    @objid ("ed3d99e2-fc45-4565-b7b7-5bbc6cc8bb7f")
    @Override
    public BpmnProcessDesignDiagram createBpmnProcessDesignDiagram() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnProcessDesignDiagram();
    }

    @objid ("e99e740c-4aa4-4f8a-8c78-ed89eee9037f")
    @Override
    public BpmnReceiveTask createBpmnReceiveTask() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnReceiveTask();
    }

    @objid ("148270a2-ae30-497c-84c6-95219bf9af70")
    @Override
    public BpmnResource createBpmnResource() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnResource();
    }

    @objid ("7cf1385f-e41c-4f92-8d50-5964853e61df")
    @Override
    public BpmnResourceParameter createBpmnResourceParameter() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnResourceParameter();
    }

    @objid ("726f8e03-80df-4427-8e2e-783f523d14f1")
    @Override
    public BpmnResourceParameterBinding createBpmnResourceParameterBinding() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnResourceParameterBinding();
    }

    @objid ("9764ed36-adc8-448e-99f3-46c10d0d2c26")
    @Override
    public BpmnResourceRole createBpmnResourceRole() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnResourceRole();
    }

    @objid ("58e150a3-d1b4-4e93-8c21-5a6bed007c5b")
    @Override
    public BpmnScriptTask createBpmnScriptTask() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnScriptTask();
    }

    @objid ("9131b8aa-80f5-4f8f-999a-abd050a5069c")
    @Override
    public BpmnSendTask createBpmnSendTask() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnSendTask();
    }

    @objid ("2b8f0931-8096-4fb1-b7b5-a767fa5306e6")
    @Override
    public BpmnSequenceFlow createBpmnSequenceFlow() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnSequenceFlow();
    }

    @objid ("65e4b4ac-ba57-4df5-ad73-b185c44ca380")
    @Override
    public BpmnSequenceFlowDataAssociation createBpmnSequenceFlowDataAssociation() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnSequenceFlowDataAssociation();
    }

    @objid ("e3f1710c-fde5-45b6-9045-b2b0dfa6757b")
    @Override
    public BpmnServiceTask createBpmnServiceTask() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnServiceTask();
    }

    @objid ("fde6de47-ef86-4be0-a508-215ea2e3e80c")
    @Override
    public BpmnSharedDefinitions createBpmnSharedDefinitions() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnSharedDefinitions();
    }

    @objid ("915931b0-c401-4eef-8d5c-7beb5672f490")
    @Override
    public BpmnSignalEventDefinition createBpmnSignalEventDefinition() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnSignalEventDefinition();
    }

    @objid ("6209d9fa-e278-4c39-84e6-f8db48788ca4")
    @Override
    public BpmnStandardLoopCharacteristics createBpmnStandardLoopCharacteristics() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnStandardLoopCharacteristics();
    }

    @objid ("7ed4b744-1ffa-4701-b032-66ee5ddee0c1")
    @Override
    public BpmnStartEvent createBpmnStartEvent() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnStartEvent();
    }

    @objid ("33e1919e-956e-46b9-9495-b96e3fd44a2f")
    @Override
    public BpmnSubProcess createBpmnSubProcess() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnSubProcess();
    }

    @objid ("6e24cfcb-2a8b-4f9d-a330-3b12e2d475dd")
    @Override
    public BpmnSubProcessDiagram createBpmnSubProcessDiagram() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnSubProcessDiagram();
    }

    @objid ("e7a604d8-1013-4197-9560-2b4db5a52109")
    @Override
    public BpmnTask createBpmnTask() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnTask();
    }

    @objid ("196854d8-50e5-4a9e-bfd7-c6ab450fde73")
    @Override
    public BpmnTerminateEventDefinition createBpmnTerminateEventDefinition() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnTerminateEventDefinition();
    }

    @objid ("e31c5338-92ab-418c-81f3-257778dea160")
    @Override
    public BpmnTimerEventDefinition createBpmnTimerEventDefinition() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnTimerEventDefinition();
    }

    @objid ("93ee2466-113f-4b5a-acb7-02c2b0b1cc8e")
    @Override
    public BpmnTransaction createBpmnTransaction() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnTransaction();
    }

    @objid ("fbf0fe26-43ad-4cb7-aecb-2840b88ce427")
    @Override
    public BpmnUserTask createBpmnUserTask() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createBpmnUserTask();
    }

    /**
     * Create a CallBehaviorAction. The created CallBehaviorAction has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an ICallBehaviorAction representing the CallBehaviorAction in the
     * Model.
     * @return An ICallBehaviorAction representing the CallBehaviorAction in the Model.
     */
    @objid ("006a104d-4406-4f55-b5fc-b37d4816c4cd")
    @Override
    public CallBehaviorAction createCallBehaviorAction() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createCallBehaviorAction();
    }

    /**
     * Create a CallOperationAction. The created CallOperationAction has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an ICallOperationAction representing the CallOperationAction in the
     * Model.
     * @return An ICallOperationAction representing the CallOperationAction in the Model.
     */
    @objid ("2f215751-3edf-43d4-94f3-7de5e9d2643c")
    @Override
    public CallOperationAction createCallOperationAction() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createCallOperationAction();
    }

    /**
     * Create a CentralBufferNode. The created CentralBufferNode has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an ICentralBufferNode representing the CentralBufferNode in the Model.
     * @return An ICentralBufferNode representing the CentralBufferNode in the Model.
     */
    @objid ("09e8652b-bcec-4e9a-a756-e97482c0f767")
    @Override
    public CentralBufferNode createCentralBufferNode() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createCentralBufferNode();
    }

    /**
     * Create a ChoicePseudoState. The created ChoicePseudoState has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IChoicePseudoState representing the ChoicePseudoState in the Model.
     * @return An IChoicePseudoState representing the ChoicePseudoState in the Model.
     */
    @objid ("3da62da4-7459-4917-a432-6f7a042ebe0b")
    @Override
    public ChoicePseudoState createChoicePseudoState() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createChoicePseudoState();
    }

    /**
     * Create a Class. The created Class has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IClass representing the Abstraction in the Model.
     * @return An IClass representing the Class in the Model.
     */
    @objid ("baed3667-0f23-4c80-8137-c24f364ac626")
    @Override
    public Class createClass() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createClass();
    }

    /**
     * Create a class. The class is created on owner and has a name.
     * @param name the name of the Class to create.
     * @param owner the NameSpace that will contain the Class.
     * @return An IClass representing the Class in the Model.
     */
    @objid ("2c7da03c-cfa6-4b77-82bf-1d2ba5d71d0a")
    @Override
    public Class createClass(final String name, final NameSpace owner) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createClass(name, owner);
    }

    /**
     * Create a stereotyped class. The class is created on owner, has a name and owns a Stereotype.
     * @param name the name of the Class to create.
     * @param owner the NameSpace that will contain the Class.
     * @param stereotype the Stereotype to refer on the class.
     * @return An IClass representing the Class in the Model.
     */
    @objid ("c5d76cbe-072e-4a2b-807d-fc084d41d307")
    @Override
    public Class createClass(final String name, final NameSpace owner, final Stereotype stereotype) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createClass(name, owner, stereotype);
    }

    @objid ("42a18bc0-00ed-4448-8a4d-90bd2c48b74d")
    @Override
    public Class createClass(final String name, final NameSpace owner, final String moduleName, final String stereotypeName) throws ExtensionNotFoundException {
        try {
            return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createClass(name, owner, moduleName, stereotypeName);
        } catch (ExtensionNotFoundException e) {
            throw new ExtensionNotFoundException(e);
        }
    }

    /**
     * Create a ClassAssociation. The created ClassAssociation has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IClassAssociation representing the Abstraction in the Model.
     * @return An IClassAssociation representing the ClassAssociation in the Model.
     */
    @objid ("54b1d74d-8d1f-4480-8f1d-1e67532d4da9")
    @Override
    public ClassAssociation createClassAssociation() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createClassAssociation();
    }

    /**
     * Create a ClassDiagram. The returned object is an IClassDiagram owned by <code>owner</code> named by <code>name</code> and sterotyped by <code>stereotype</code>
     * @param name the name of the Class Diagram to create.
     * @param owner the element on which the Class Diagram will be created.
     * @param stereotype the name of the stereotype that will be used to type the Class Diagram
     * @return An IClassDiagram representing the diagram in the Model.
     */
    @objid ("9567272f-0a68-45b6-9f5c-8e25d8e10697")
    @Override
    public ClassDiagram createClassDiagram(final String name, final ModelElement owner, final Stereotype stereotype) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createClassDiagram(name, owner, stereotype);
    }

    /**
     * Create a Clause. The created Clause has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IClause representing the Clause in the Model.
     * @return An IClause representing the Clause in the Model.
     */
    @objid ("ee09e334-d7eb-480f-822b-bd737088d48c")
    @Override
    public Clause createClause() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createClause();
    }

    /**
     * Create a Collaboration. The created Collaboration has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an ICollaboration representing the Collaboration in the Model.
     * @return An ICollaboration representing the Collaboration in the Model.
     */
    @objid ("2857f47c-d697-43f1-bda7-98ccf01fa41d")
    @Override
    public Collaboration createCollaboration() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createCollaboration();
    }

    /**
     * Create a CollaborationUse. The created CollaborationUse has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an ICollaborationUse representing the Abstraction in the Model.
     * @return An ICollaborationUse representing the CollaborationUse in the Model.
     */
    @objid ("afc723b5-37ac-4870-84ac-92f81a8b3549")
    @Override
    public CollaborationUse createCollaborationUse() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createCollaborationUse();
    }

    /**
     * Create a CombinedFragment. The created CombinedFragment has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an ICombinedFragment representing the CombinedFragment in the Model.
     * @return An ICombinedFragment representing the CombinedFragment in the Model.
     */
    @objid ("ea9e790b-e84e-4575-87d9-89649b7c3811")
    @Override
    public CombinedFragment createCombinedFragment() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createCombinedFragment();
    }

    /**
     * Create a CombinedFragment. Create a CombinedFragment with an InteractionOperator. In order to build a valid model, the CombinedFragment must be inserted in the model.
     * @param operator the operator of the CombinedFragment to create.
     * @return An ICombinedFragment representing the CombinedFragment in the Model.
     */
    @objid ("1164e437-8e43-4029-a026-7ba11740c656")
    @Override
    public CombinedFragment createCombinedFragment(final InteractionOperator operator) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createCombinedFragment(operator);
    }

    @objid ("582e17e3-cae0-4b70-b8bc-251a14890adc")
    @Override
    public CommunicationChannel createCommunicationChannel() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createCommunicationChannel();
    }

    /**
     * Create a CommunicationDiagram. The returned object is an ICommunicationDiagram owned by <code>owner</code> named by <code>name</code> and sterotyped by <code>stereotype</code>
     * @param name the name of the Communication Diagram to create.
     * @param owner the element on which the Communication Diagram will be created.
     * @param stereotype the name of the stereotype that will be used to type the Communication Diagram
     * @return An ICommunicationDiagram representing the diagram in the Model.
     */
    @objid ("0dc99445-19d0-4f2d-9313-0e5f38e3c90d")
    @Override
    public CommunicationDiagram createCommunicationDiagram(final String name, final ModelElement owner, final Stereotype stereotype) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createCommunicationDiagram(name, owner, stereotype);
    }

    /**
     * Create a CommunicationInteraction. The created CommunicationInteraction has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is a CommunicationInteraction representing the
     * CommunicationInteraction in the Model.
     * @return A CommunicationInteraction representing the CommunicationInteraction in the Model.
     */
    @objid ("7925326d-88df-47b6-8b0a-e789f6966e4b")
    @Override
    public CommunicationInteraction createCommunicationInteraction() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createCommunicationInteraction();
    }

    @objid ("22826c98-0257-4788-9657-83941c3aed7d")
    @Override
    public CommunicationMessage createCommunicationMessage() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createCommunicationMessage();
    }

    @objid ("47b687c5-e3b5-44d2-9dd9-f61dfc6196e1")
    @Override
    public CommunicationNode createCommunicationNode() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createCommunicationNode();
    }

    /**
     * Create a Component. The created Component has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IComponent representing the Abstraction in the Model.
     * @return An IComponent representing the Component in the Model.
     */
    @objid ("66b7b66b-1b43-4726-8bca-ce84bdaf0bed")
    @Override
    public Component createComponent() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createComponent();
    }

    /**
     * Create a Component. The component is created on owner and has a name.
     * @param name the name of the Component to create.
     * @param owner the NameSpace that will contain the Component.
     * @return An IComponent representing the Component in the Model.
     */
    @objid ("0df9f1b0-6539-48a3-b8de-a391fa286fa5")
    @Override
    public Component createComponent(final String name, final NameSpace owner) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createComponent(name, owner);
    }

    /**
     * Create a stereotyped Component. The Component is created on owner, has a name and owns a Stereotype.
     * @param name the name of the Component to create.
     * @param owner the NameSpace that will contain the Component.
     * @param stereotype the Stereotype to refer on the Component.
     * @return An IComponent representing the Component in the Model.
     */
    @objid ("4effce2d-b2f4-4ac8-a340-bbcece749a08")
    @Override
    public Component createComponent(final String name, final NameSpace owner, final Stereotype stereotype) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createComponent(name, owner, stereotype);
    }

    @objid ("c8d78aec-63b7-44f6-b0f0-b1be9a0b9bee")
    @Override
    public Component createComponent(final String name, final NameSpace owner, final String moduleName, final String stereotypeName) throws ExtensionNotFoundException {
        try {
            return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createComponent(name, owner, moduleName, stereotypeName);
        } catch (ExtensionNotFoundException e) {
            throw new ExtensionNotFoundException(e);
        }
    }

    /**
     * Create a CompositeStructureDiagram. The returned object is an ICompositeStructureDiagram owned by <code>owner</code> named by <code>name</code> and sterotyped by <code>stereotype</code>
     * @since 2.1.1
     * @param name the name of the CompositeStructure Diagram to create.
     * @param owner the element on which the Class Diagram will be created.
     * @param stereotype the name of the stereotype that will be used to type the Class Diagram
     * @return An ICompositeStructureDiagram representing the diagram in the Model.
     */
    @objid ("fe6e27e8-4859-426d-9c7a-9eec2b17fc07")
    @Override
    public CompositeStructureDiagram createCompositeStructureDiagram(final String name, final ModelElement owner, final Stereotype stereotype) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createCompositeStructureDiagram(name, owner, stereotype);
    }

    /**
     * Create a ConditionalNode. The created ConditionalNode has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IConditionalNode representing the ConditionalNode in the Model.
     * @return An IConditionalNode representing the ConditionalNode in the Model.
     */
    @objid ("14c8b3a2-dae4-4e5e-ac28-4c2a444f0f7c")
    @Override
    public ConditionalNode createConditionalNode() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createConditionalNode();
    }

    /**
     * Create a ConnectionPointReference. The created ConnectionPointReference has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IConnectionPointReference representing the
     * ConnectionPointReference in the Model.
     * @return An IConnectionPointReference representing the ConnectionPointReference in the Model.
     */
    @objid ("a6caa19c-b653-4732-8c4f-72bbf82f90e5")
    @Override
    public ConnectionPointReference createConnectionPointReference() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createConnectionPointReference();
    }

    @objid ("b765cb84-9e1f-4e23-b431-44c0499a76d6")
    @Override
    public Connector createConnector() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createConnector();
    }

    @objid ("fa938034-b722-408e-a74f-8b4692037ccd")
    @Override
    public Connector createConnector(final BindableInstance source, final BindableInstance destination, final String destinationRole) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createConnector(source, destination, destinationRole);
    }

    /**
     * Create a ConnectorEnd. The created ConnectorEnd has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IConnectorEnd representing the ConnectorEnd in the Model.
     * @return An IConnectorEnd representing the ConnectorEnd in the Model.
     */
    @objid ("0a7a2cf0-c456-466b-b17b-0020fdfd81ab")
    @Override
    public ConnectorEnd createConnectorEnd() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createConnectorEnd();
    }

    /**
     * Create a Constraint. The created Constraint has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IConstraint representing the Constraint in the Model.
     * @return An IConstraint representing the Constraint in the Model.
     */
    @objid ("3481eeb4-beba-4a33-a134-c9c5f0e6e50a")
    @Override
    public Constraint createConstraint() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createConstraint();
    }

    /**
     * Create a ControlFlow. The created ControlFlow has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IControlFlow representing the ControlFlow in the Model.
     * @return An IControlFlow representing the ControlFlow in the Model.
     */
    @objid ("75c5c894-7e02-4f2f-b87c-f6f78050946b")
    @Override
    public ControlFlow createControlFlow() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createControlFlow();
    }

    /**
     * Create a DataFlow. The created DataFlow has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IDataFlow representing the DataFlow in the Model.
     * @return An IDataFlow representing the DataFlow in the Model.
     */
    @objid ("e2a8df71-453d-418a-8885-082884bd02d7")
    @Override
    public DataFlow createDataFlow() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createDataFlow();
    }

    /**
     * Create a DataStoreNode. The created DataStoreNode has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IDataStoreNode representing the DataStoreNode in the Model.
     * @return An IDataStoreNode representing the DataStoreNode in the Model.
     */
    @objid ("060fc6a2-6558-4f9d-9785-95c4adb91944")
    @Override
    public DataStoreNode createDataStoreNode() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createDataStoreNode();
    }

    /**
     * Create a DataType. The created DataType has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IDataType representing the Abstraction in the Model.
     * @return An IDataType representing the DataType in the Model.
     */
    @objid ("8b089d1b-33b8-401c-8770-cc104c6b5ccf")
    @Override
    public DataType createDataType() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createDataType();
    }

    /**
     * Create a DataType. The created DataType has a composition owner and a name.
     * @param name the name of the DataType to create.
     * @param owner the NameSpace that will contain the DataType.
     * @return An IDataType representing the DataType in the Model.
     */
    @objid ("484b1652-902f-4954-8b2d-c422d7c294e7")
    @Override
    public DataType createDataType(final String name, final NameSpace owner) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createDataType(name, owner);
    }

    /**
     * Create a DataType. The created DataType has a composition owner, a name and refers a Stereotype.
     * @param name the name of the DataType to create.
     * @param owner the NameSpace that will contain the DataType.
     * @param stereotype the stereotype that will extend the DataType.
     * @return An IDataType representing the DataType in the Model.
     */
    @objid ("11c195df-d9f2-4c08-bd03-408f7f1b18a5")
    @Override
    public DataType createDataType(final String name, final NameSpace owner, final Stereotype stereotype) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createDataType(name, owner, stereotype);
    }

    @objid ("808845fb-5cb8-4119-949f-63b820c7ac33")
    @Override
    public DataType createDataType(final String name, final NameSpace owner, final String moduleName, final String stereotypeName) throws ExtensionNotFoundException {
        try {
            return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createDataType(name, owner, moduleName, stereotypeName);
        } catch (ExtensionNotFoundException e) {
            throw new ExtensionNotFoundException(e);
        }
    }

    /**
     * Create a DecisionMergeNode. The created DecisionMergeNode has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IDecisionMergeNode representing the DecisionMergeNode in the Model.
     * @return An IDecisionMergeNode representing the DecisionMergeNode in the Model.
     */
    @objid ("3c9283e0-093b-4c5d-8899-50db9e47c37e")
    @Override
    public DecisionMergeNode createDecisionMergeNode() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createDecisionMergeNode();
    }

    /**
     * Create a DeepHistoryPseudoState. The created DeepHistoryPseudoState has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IDeepHistoryPseudoState representing the
     * DeepHistoryPseudoState in the Model.
     * @return An IDeepHistoryPseudoState representing the DeepHistoryPseudoState in the Model.
     */
    @objid ("adaf07f8-d591-43d3-8b4e-29fc0cf3a465")
    @Override
    public DeepHistoryPseudoState createDeepHistoryPseudoState() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createDeepHistoryPseudoState();
    }

    /**
     * Create a Dependency. The created Dependency has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IDependency representing the Abstraction in the Model.
     * @return An IDependency representing the Dependency in the Model.
     */
    @objid ("ebe05e96-7732-4371-ae95-f0558bd01d81")
    @Override
    public Dependency createDependency() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createDependency();
    }

    /**
     * Create a Dependency between two ModelElements.<br>
     * @param source the origin of the dependency.
     * @param destination the destination of the dependency.
     * @param stereotype the Stereotype that extends the d?pendency.
     * @return An IDependency representing the Dependency in the Model.
     */
    @objid ("aa469865-c7ab-47ef-89d3-fe3609a740b1")
    @Override
    public Dependency createDependency(final ModelElement source, final ModelElement destination, final Stereotype stereotype) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createDependency(source, destination, stereotype);
    }

    @objid ("d9a8705f-86a8-49ed-b61d-ce1a99c03bc4")
    @Override
    public Dependency createDependency(final ModelElement source, final ModelElement destination, final String moduleName, final String stereotypeName) throws ExtensionNotFoundException {
        try {
            return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createDependency(source, destination, moduleName, stereotypeName);
        } catch (ExtensionNotFoundException e) {
            throw new ExtensionNotFoundException(e);
        }
    }

    /**
     * Create a DeploymentDiagram. The returned object is an IDeploymentDiagram owned by <code>owner</code> named by <code>name</code> and sterotyped by <code>stereotype</code>
     * @param name the name of the Deployment Diagram to create.
     * @param owner the element on which the Deployment Diagram will be created.
     * @param stereotype the name of the stereotype that will be used to type the Deployment Diagram
     * @return An IDeploymentDiagram representing the diagram in the Model.
     */
    @objid ("42f38ed3-0089-443b-a94e-a1cdba3e35ff")
    @Override
    public DeploymentDiagram createDeploymentDiagram(final String name, final ModelElement owner, final Stereotype stereotype) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createDeploymentDiagram(name, owner, stereotype);
    }

    /**
     * @return An UseCase representing the UseCase in the Model.
     */
    @objid ("7edb28d6-6682-4316-8640-ed7b201d19cd")
    @Override
    public DiagramSet createDiagramSet() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createDiagramSet();
    }

    @objid ("a7d33106-d469-4ec2-b488-9fbaf8323ccd")
    @Override
    public Document createDocument() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createDocument();
    }

    /**
     * Create a DurationConstraint. The created DurationConstraint has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IDurationConstraint representing the DurationConstraint in the
     * Model.
     * @return An IDurationConstraint representing the DurationConstraint in the Model.
     */
    @objid ("ba18826c-b1b7-4771-b12d-682b9ac5a801")
    @Override
    public DurationConstraint createDurationConstraint() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createDurationConstraint();
    }

    @objid ("e0d377d0-957d-47bc-bc7d-19a972e53506")
    @Override
    public MObject createElement(final String targetClass, final Element parentElement, final String dependency) {
        return this.modelService.getModelFactory().createElement(targetClass, parentElement, dependency);
    }

    @objid ("5336cd29-47a4-4a3b-8bcc-998aabdef6c3")
    @Override
    public MObject createElement(final String metaclassName) {
        return this.modelService.getModelFactory().createElement(metaclassName);
    }

    /**
     * Create an ElementImport. The created ElementImport has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an ElementImport representing the Abstraction in the Model.
     * @return An ElementImport representing the ElementImport in the Model.
     */
    @objid ("74f28a44-7dae-4d0e-9e0c-a382206a11ba")
    @Override
    public ElementImport createElementImport() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createElementImport();
    }

    /**
     * Create an ElementImport between two NameSpaces.
     * @param source origin of the ElementImport
     * @param destination destination of the ElementImport
     * @return An ElementImport representing the ElementImport in the Model.
     */
    @objid ("c28af4b8-3882-46a9-b2cf-b6a52d79751e")
    @Override
    public ElementImport createElementImport(final NameSpace source, final NameSpace destination) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createElementImport(source, destination);
    }

    /**
     * Create an ElementImport between an Operation and a NameSpace.
     * @param source origin of the ElementImport
     * @param destination destination of the ElementImport
     * @return An ElementImport representing the ElementImport in the Model.
     */
    @objid ("e3571b45-acee-4e47-8315-4d55d0a363d3")
    @Override
    public ElementImport createElementImport(final Operation source, final NameSpace destination) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createElementImport(source, destination);
    }

    /**
     * Create an ElementRealization. The created ElementRealization has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an ElementRealization representing the Abstraction in the Model.
     * @return An ElementRealization representing the ElementRealization in the Model.
     */
    @objid ("de0ea9ff-6c85-4628-bc84-bfca7ef9d5e1")
    @Override
    public ElementRealization createElementRealization() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createElementRealization();
    }

    @objid ("c1006b9b-9ae5-4c26-afd0-750f682ec0ac")
    @Override
    public Document createEmbeddedDocument(final String moduleName, final String documentRole, final ModelElement owner, final String mimeType) throws ExtensionNotFoundException, IOException {
        final Document doc = this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).resourceBuilder()
                .withMimeType(mimeType)
                .withOwner(owner)
                .withRole(moduleName, ".*", documentRole)
                .createEmbeddedDocument();
        return doc;
    }

    @objid ("755d3d8b-360b-435b-886c-ed28c48f82b1")
    @Override
    public Document createEmbeddedDocument(final ResourceType type, final ModelElement owner, final String mimeType) throws IOException {
        final Document doc = this.modelService.getModelFactory().getFactory(IStandardModelFactory.class)
                .resourceBuilder()
                .withMimeType(mimeType)
                .withOwner(owner)
                .withRole(type)
                .createEmbeddedDocument();
        return doc;
    }

    @objid ("def12b40-3190-4ce6-acd3-7dd2f3b4a5a2")
    @Override
    public Document createEmbeddedDocument(final String moduleName, final String documentRole, final ModelElement owner, final String mimeType, final Path initialContent) throws ExtensionNotFoundException, IOException {
        final Document doc = this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).resourceBuilder()
                .withFile(initialContent)
                .withMimeType(mimeType)
                .withOwner(owner)
                .withName(initialContent.getFileName().toString())
                .withRole(moduleName, ".*", documentRole)
                .createEmbeddedDocument();
        return doc;
    }

    /**
     * Create a EntryPointPseudoState. The created EntryPointPseudoState has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IEntryPointPseudoState representing the EntryPointPseudoState
     * in the Model.
     * @return An IEntryPointPseudoState representing the EntryPointPseudoState in the Model.
     */
    @objid ("815e27c8-c6a2-408c-9b3e-b01bd62f218c")
    @Override
    public EntryPointPseudoState createEntryPointPseudoState() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createEntryPointPseudoState();
    }

    @objid ("dcc3ffbb-d454-414c-bffc-49c6f97b6e64")
    @Override
    public EnumeratedPropertyType createEnumeratedPropertyType() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createEnumeratedPropertyType();
    }

    /**
     * Create an Enumeration. The created Enumeration has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an Enumeration representing the Abstraction in the Model.
     * @return An Enumeration representing the Enumeration in the Model.
     */
    @objid ("b4af8391-96f6-442d-8863-9db07eff20bd")
    @Override
    public Enumeration createEnumeration() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createEnumeration();
    }

    /**
     * Create an Enumeration.
     * @param name the name of the Enumeration to create.
     * @param owner the NameSpace that will contain the Enumeration
     * @return An Enumeration representing the Enumeration in the Model.
     */
    @objid ("eada05c2-57af-4f11-b619-70babf907528")
    @Override
    public Enumeration createEnumeration(final String name, final NameSpace owner) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createEnumeration(name, owner);
    }

    /**
     * Create an stereotyped Enumeration.
     * @param name the name of the Enumeration to create.
     * @param owner the NameSpace that will contain the Enumeration.
     * @param stereotype tthe Stereotype that will extend the Enumeration.
     * @return An Enumeration representing the Enumeration in the Model.
     */
    @objid ("dcbe15b6-0c65-46f0-895e-6ddf44012b2b")
    @Override
    public Enumeration createEnumeration(final String name, final NameSpace owner, final Stereotype stereotype) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createEnumeration(name, owner, stereotype);
    }

    @objid ("0c86ff09-11b6-415b-a23c-d9011325178a")
    @Override
    public Enumeration createEnumeration(final String name, final NameSpace owner, final String moduleName, final String stereotypeName) throws ExtensionNotFoundException {
        try {
            return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createEnumeration(name, owner, moduleName, stereotypeName);
        } catch (ExtensionNotFoundException e) {
            throw new ExtensionNotFoundException(e);
        }
    }

    /**
     * Create an EnumerationLiteral. The created EnumerationLiteral has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an EnumerationLiteral representing the Abstraction in the Model.
     * @return An EnumerationLiteral representing the EnumerationLiteral in the Model.
     */
    @objid ("47c8e071-04c0-4427-957a-73f21eaf0e4a")
    @Override
    public EnumerationLiteral createEnumerationLiteral() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createEnumerationLiteral();
    }

    /**
     * Create an EnumerationLiteral.
     * @param name the name of the EnumerationLiteral to create.
     * @param owner the Enumeration that will contain the EnumerationLiteral
     * @return An Enumeration representing the EnumerationLiteral in the Model.
     */
    @objid ("1b9bf981-0ade-4b50-b3bc-45ddf50cdea5")
    @Override
    public EnumerationLiteral createEnumerationLiteral(final String name, final Enumeration owner) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createEnumerationLiteral(name, owner);
    }

    /**
     * Create an stereotyped EnumerationLiteral.
     * @param name the name of the EnumerationLiteral to create.
     * @param owner the Enumeration that will contain the EnumerationLiteral.
     * @param stereotype the Stereotype that will extend the EnumerationLiteral.
     * @return An Enumeration representing the Enumeration in the Model.
     */
    @objid ("ff00a657-b430-4e5e-a674-4b885ce04118")
    @Override
    public EnumerationLiteral createEnumerationLiteral(final String name, final Enumeration owner, final Stereotype stereotype) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createEnumerationLiteral(name, owner, stereotype);
    }

    @objid ("891c62bd-cf71-4ca0-a7a1-37c387e586db")
    @Override
    public EnumerationLiteral createEnumerationLiteral(final String name, final Enumeration owner, final String moduleName, final String stereotypeName) throws ExtensionNotFoundException {
        try {
            return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createEnumerationLiteral(name, owner, moduleName, stereotypeName);
        } catch (ExtensionNotFoundException e) {
            throw new ExtensionNotFoundException(e);
        }
    }

    /**
     * Create an Event. The created Event has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IEvent representing the Abstraction in the Model.
     * @return An IEvent representing the Event in the Model.
     */
    @objid ("177c2201-ad72-4de3-8a2e-7d506341d9b9")
    @Override
    public Event createEvent() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createEvent();
    }

    @objid ("0fea5d06-760d-4249-96de-3f4e4ba713a0")
    @Override
    public ExceptionHandler createExceptionHandler() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createExceptionHandler();
    }

    /**
     * Create a ExecutionOccurenceSpecification. The created ExecutionOccurenceSpecification has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IExecutionOccurenceSpecification
     * representing the ExecutionOccurenceSpecification in the Model.
     * @return An IExecutionOccurenceSpecification representing the ExecutionOccurenceSpecification in the Model.
     */
    @objid ("bebc8fbe-a443-4f9c-b363-eb79ee06cf21")
    @Override
    public ExecutionOccurenceSpecification createExecutionOccurenceSpecification() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createExecutionOccurenceSpecification();
    }

    /**
     * Create a ExecutionSpecification. The created ExecutionOccurenceSpecification has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IExecutionSpecification representing the
     * ExecutionSpecification in the Model.
     * @return An IExecutionOccurenceSpecification representing the ExecutionOccurenceSpecification in the Model.
     */
    @objid ("cdb362d3-2288-42d6-9212-b2ca014572b8")
    @Override
    public ExecutionSpecification createExecutionSpecification() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createExecutionSpecification();
    }

    /**
     * Create a ExitPointPseudoState. The created ExitPointPseudoState has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IExitPointPseudoState representing the ExitPointPseudoState in
     * the Model.
     * @return An ObExitpointPseudoState representing the ExitpointPseudoState in the Model.
     */
    @objid ("67ccd03c-9993-44e4-9549-8fd98c6b9763")
    @Override
    public ExitPointPseudoState createExitPointPseudoState() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createExitPointPseudoState();
    }

    @objid ("d825e70d-4ba6-4857-894b-9aecc3894629")
    @Override
    public ExpansionNode createExpansionNode() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createExpansionNode();
    }

    @objid ("6c31b7ca-e85f-4fa4-8302-ba6ec7761dd2")
    @Override
    public ExpansionRegion createExpansionRegion() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createExpansionRegion();
    }

    /**
     * Create an extend UseCaseDependency between two UseCases.
     * @param source the origin of the UseCaseDependency
     * @param destination the destination of the UseCaseDependency
     * @return An UseCaseDependency representing the UseCaseDependency in the Model.
     */
    @objid ("e367cd39-9f34-4804-9c8c-1a57af20f376")
    @Override
    public UseCaseDependency createExtendUseCaseDependency(final UseCase source, final UseCase destination) throws ExtensionNotFoundException {
        try {
            return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createExtendUseCaseDependency(source, destination);
        } catch (ExtensionNotFoundException e) {
            throw new ExtensionNotFoundException(e);
        }
    }

    /**
     * Create a ExtensionPoint. The created ExtensionPoint has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IExtensionPoint representing the ExtensionPoint in the Model.
     * @return An IExtensionPoint representing the ExtensionPoint in the Model.
     */
    @objid ("1877f964-f0c1-42ea-b6bd-6ed96258c099")
    @Override
    public ExtensionPoint createExtensionPoint() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createExtensionPoint();
    }

    @objid ("b2566358-67bf-484a-b6ba-823ebd6d0d0c")
    @Override
    @Deprecated
    public Document createExternDocument() {
        return createDocument();
    }

    @objid ("04162f69-8ca8-4dd6-b3d1-457eaec97f62")
    @Override
    @Deprecated
    public Document createExternDocument(final String moduleName, final String documentRole, final ModelElement owner, final String mimeType) throws ExtensionNotFoundException, IOException {
        return createEmbeddedDocument(moduleName, documentRole, owner, mimeType);
    }

    @objid ("09bfb35c-26db-49b0-8c1a-56aa20cad8b1")
    @Override
    @Deprecated
    public Document createExternDocument(final ResourceType type, final ModelElement owner, final String mimeType) throws IOException {
        return createEmbeddedDocument(type, owner, mimeType);
    }

    @objid ("ab63da4f-a60f-4f0d-a880-9e8f6373934e")
    @Override
    @Deprecated
    public Document createExternDocument(final String moduleName, final String documentRole, final ModelElement owner, final String mimeType, final Path initialContent) throws ExtensionNotFoundException, IOException {
        return createEmbeddedDocument(moduleName, documentRole, owner, mimeType, initialContent);
    }

    @objid ("0bb57389-0b12-4906-a6db-59fc1a62acd0")
    @Override
    public ExternProcessor createExternProcessor() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createExternProcessor();
    }

    @objid ("a0eb05da-f108-473c-8bf4-61d20795cdd5")
    @Override
    public ExternProcessor createExternProcessor(final String implementationClassName, final String moduleName) {
        ExternProcessor processor = createExternProcessor();
        processor.setClassName(moduleName != null ? "module:/" + moduleName + "/" + implementationClassName : implementationClassName);
        return processor;
    }

    /**
     * Create a FinalState. The created FinalState has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IFinalState representing the FinalState in the Model.
     * @return An IFinalState representing the FinalState in the Model.
     */
    @objid ("b05cdb3e-63e7-4a87-a803-5aac6b45dc8e")
    @Override
    public FinalState createFinalState() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createFinalState();
    }

    /**
     * Create a FlowFinalNode. The created FlowFinalNode has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IFlowFinalNode representing the FlowFinalNode in the Model.
     * @return An IFlowFinalNode representing the FlowFinalNode in the Model.
     */
    @objid ("555814bf-40ea-43e1-bd93-0465fa072765")
    @Override
    public FlowFinalNode createFlowFinalNode() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createFlowFinalNode();
    }

    /**
     * Create a ForkJoinNode. The created ForkJoinNode has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IForkJoinNode representing the ForkJoinNode in the Model.
     * @return An IForkJoinNode representing the ForkJoinNode in the Model.
     */
    @objid ("fd1aedfc-2473-4855-b7ae-8976adb69b14")
    @Override
    public ForkJoinNode createForkJoinNode() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createForkJoinNode();
    }

    /**
     * Create a ForkPseudoState. The created ForkPseudoState has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IForkPseudoState representing the ForkPseudoState in the Model.
     * @return An IForkPseudoState representing the ForkPseudoState in the Model.
     */
    @objid ("dbdf1b61-c3ff-4ddb-bc5e-138270a2e1e9")
    @Override
    public ForkPseudoState createForkPseudoState() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createForkPseudoState();
    }

    /**
     * Create a Gate. The created Gate has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IGate representing the Gate in the Model.
     * @return An IGate representing the Gate in the Model.
     */
    @objid ("7b0db3c5-9671-40a2-b1c7-3894da84db23")
    @Override
    public Gate createGate() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createGate();
    }

    /**
     * Create a Gate. The Gate is created with a name. In order to build a valid model, the Gate must be inserted in the model. must be defined.
     * @param name The name of the gate.
     * @return An IGate representing the Gate in the Model.
     */
    @objid ("9ece5b8e-5e4a-4d94-a0c4-db28a47d45a8")
    @Override
    public Gate createGate(final String name) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createGate(name);
    }

    /**
     * Create a GeneralOrdering. The created GeneralOrdering has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IGeneralOrdering representing the GeneralOrdering in the Model.
     * @return An IGeneralOrdering representing the GeneralOrdering in the Model.
     */
    @objid ("e9a42f75-902d-4163-bd91-0e9a9528f2f6")
    @Override
    public GeneralOrdering createGeneralOrdering() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createGeneralOrdering();
    }

    /**
     * Create a Generalization. The created Generalization has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IGeneralization representing the Abstraction in the Model.
     * @return An IGeneralization representing the Generalization in the Model.
     */
    @objid ("6360743e-f41c-4322-84c6-a906a544a234")
    @Override
    public Generalization createGeneralization() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createGeneralization();
    }

    /**
     * Create a Generalization between two NameSpaces.
     * @param source the child element of the Generaliartion
     * @param destination the parent element of the Generaliartion
     * @return An IGeneralization representing the Generalization in the Model.
     */
    @objid ("a6642690-3f33-4d2a-8e8a-85259dfbb7ee")
    @Override
    public Generalization createGeneralization(final NameSpace source, final NameSpace destination) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createGeneralization(source, destination);
    }

    /**
     * Create an IOParameter.
     * @param name the name of the Parameter to create.
     * @param type the type of the Parameter to create.
     * @param owner the Operation that will contain the Parameter.
     * @return An IParameter representing the Parameter in the Model.
     */
    @objid ("73ba28fb-be97-4609-ad9a-d2e3a897ee45")
    @Override
    public Parameter createIOParameter(final String name, final GeneralClass type, final Operation owner) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createIOParameter(name, type, owner);
    }

    /**
     * Create a stereotyped IOParameter.
     * @param name the name of the Parameter to create.
     * @param type the type of the Parameter to create.
     * @param owner the Operation that will contain the Parameter.
     * @param stereotype the stereotype that will extend the Parameter.
     * @return An IParameter representing the Parameter in the Model.
     */
    @objid ("8062ea3d-1a7b-4e78-973a-34d05edd5315")
    @Override
    public Parameter createIOParameter(final String name, final GeneralClass type, final Operation owner, final Stereotype stereotype) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createIOParameter(name, type, owner, stereotype);
    }

    @objid ("3db20ad3-b01e-4234-a35d-dd9ea6eead89")
    @Override
    public Parameter createIOParameter(final String name, final GeneralClass type, final Operation owner, final String moduleName, final String stereotypeName) throws ExtensionNotFoundException {
        try {
            return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createIOParameter(name, type, owner, moduleName, stereotypeName);
        } catch (ExtensionNotFoundException e) {
            throw new ExtensionNotFoundException(e);
        }
    }

    /**
     * Create an include UseCaseDependency between two NameSpaces.
     * @param source the origin element of the UseCaseDependency
     * @param destination the destination element of the UseCaseDependency
     * @return An UseCaseDependency representing the UseCaseDependency in the Model.
     */
    @objid ("831aac25-1c80-4958-8391-c8707861cd0c")
    @Override
    public UseCaseDependency createIncludeUseCaseDependency(final UseCase source, final UseCase destination) throws ExtensionNotFoundException {
        try {
            return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createIncludeUseCaseDependency(source, destination);
        } catch (ExtensionNotFoundException e) {
            throw new ExtensionNotFoundException(e);
        }
    }

    /**
     * Create a InformationFlow. The created InformationFlow has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IInformationFlow representing the InformationFlow in the Model.
     * @return An IInformationFlow representing the InformationFlow in the Model.
     */
    @objid ("bf466df3-d6e2-4805-ad43-409fbc50a140")
    @Override
    public InformationFlow createInformationFlow() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createInformationFlow();
    }

    /**
     * Create a InformationItem. The created InformationItem has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IInformationItem representing the InformationItem in the Model.
     * @return An IInformationItem representing the InformationItem in the Model.
     */
    @objid ("b6d6e69a-3f52-4321-bf46-a53058a1fa5c")
    @Override
    public InformationItem createInformationItem() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createInformationItem();
    }

    /**
     * Create a InitialNode. The created InitialNode has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IInitialNode representing the InitialNode in the Model.
     * @return An IInitialNode representing the InitialNode in the Model.
     */
    @objid ("04145221-6862-4d0f-bcce-f5f300891199")
    @Override
    public InitialNode createInitialNode() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createInitialNode();
    }

    /**
     * Create a InitialPseudoState. The created InitialPseudoState has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IInitialPseudoState representing the InitialPseudoState in the
     * Model.
     * @return An IInitialPseudoState representing the InitialPseudoState in the Model.
     */
    @objid ("98c7a2f3-fcb4-4734-bdff-8b242f0a1117")
    @Override
    public InitialPseudoState createInitialPseudoState() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createInitialPseudoState();
    }

    /**
     * Create a InputPin. The created InputPin has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IInputPin representing the InputPin in the Model.
     * @return An IInputPin representing the InputPin in the Model.
     */
    @objid ("bfe94eb0-f475-4cb6-950e-6c88922388d2")
    @Override
    public InputPin createInputPin() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createInputPin();
    }

    /**
     * Create an Instance. The created Instance has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an Instance representing the Instance in the Model.
     * @return An Instance representing the Instance in the Model.
     */
    @objid ("43fdf71a-9e07-4fd6-ae9f-2263c4dbcfc1")
    @Override
    public Instance createInstance() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createInstance();
    }

    /**
     * Create an Instance. The Instance is created with the specified name and owner Package.
     * @param name the name of the Instance to create.
     * @param owner the Package that will conbtain the Port.
     * @return an Instance representing the Instance in the Model.
     */
    @objid ("d11a545f-3ada-44b2-a4bd-48bdacc3e9a7")
    @Override
    public Instance createInstance(final String name, final Package owner) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createInstance(name, owner);
    }

    /**
     * Create an InstanceNode. The created InstanceNode has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an InstanceNode representing the InstanceNode in the Model.
     * @return An InstanceNode representing the InstanceNode in the Model.
     */
    @objid ("9bbea1a1-d266-4ea2-95aa-3548189698f3")
    @Override
    public InstanceNode createInstanceNode() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createInstanceNode();
    }

    /**
     * Create an Interaction. The created Interaction has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an Interaction representing the Interaction in the Model.
     * @return An Interaction representing the Interaction in the Model.
     */
    @objid ("f60aa706-2ca2-4341-b9f3-dc2087db8fc5")
    @Override
    public Interaction createInteraction() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createInteraction();
    }

    /**
     * Create an InteractionOperand. The created InteractionOperand has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an InteractionOperand representing the InteractionOperand in the
     * Model.
     * @return An InteractionOperand representing the InteractionOperand in the Model.
     */
    @objid ("2453f314-1da3-403e-b260-063507145af0")
    @Override
    public InteractionOperand createInteractionOperand() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createInteractionOperand();
    }

    /**
     * Create an InteractionOperand. The created InteractionOperand has a guard condition. In order to build a valid model, the InteractionOperand must be inserted in the model.
     * @param guard the guard condition of the InteractionOperand to create.
     * @return An InteractionOperand representing the InteractionOperand in the Model.
     */
    @objid ("78f63855-aa56-40e1-956f-e8a9bc8482de")
    @Override
    public InteractionOperand createInteractionOperand(final String guard) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createInteractionOperand(guard);
    }

    /**
     * Create an InteractionUse. The created InteractionUse has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an InteractionUse representing the InteractionUse in the Model.
     * @return An InteractionUse representing the InteractionUse in the Model.
     */
    @objid ("2affcbf0-82f2-4a39-93e2-52eea9fca02d")
    @Override
    public InteractionUse createInteractionUse() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createInteractionUse();
    }

    /**
     * Create an InteractionUse. The created InteractionUse refer an Interaction. In order to build a valid model, the InteractionUse must be inserted in the model.
     * @param refered the refered Interaction.
     * @return An InteractionUse representing the InteractionUse in the Model.
     */
    @objid ("c4f54fde-ea11-43aa-a431-5205f0cb07a3")
    @Override
    public InteractionUse createInteractionUse(final Interaction refered) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createInteractionUse(refered);
    }

    /**
     * Create an Interface. The created Interface has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an Interface representing the Abstraction in the Model.
     * @return An Interface representing the Interface in the Model.
     */
    @objid ("950b1e85-b47e-4076-8c7e-075abe389f4f")
    @Override
    public Interface createInterface() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createInterface();
    }

    /**
     * Create an Interface.
     * @param name the name of the Interface to create.
     * @param owner the NameSpace that will contain the Interface.
     * @return An Interface representing the Interface in the Model.
     */
    @objid ("78cf1b74-0d22-46b9-9cda-a7d304216b47")
    @Override
    public Interface createInterface(final String name, final NameSpace owner) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createInterface(name, owner);
    }

    /**
     * Create a stereotyped Interface.
     * @param name the name of the Interface to create.
     * @param owner the NameSpace that will contain the Interface.
     * @param stereotype the stereotype that will extend the Interface.
     * @return An Interface representing the Interface in the Model.
     */
    @objid ("69778a58-64da-475c-bda2-f26fea3804cb")
    @Override
    public Interface createInterface(final String name, final NameSpace owner, final Stereotype stereotype) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createInterface(name, owner, stereotype);
    }

    @objid ("cc9eb24e-5e40-42ef-8e20-46fdbbbd3470")
    @Override
    public Interface createInterface(final String name, final NameSpace owner, final String moduleName, final String stereotypeName) throws ExtensionNotFoundException {
        try {
            return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createInterface(name, owner, moduleName, stereotypeName);
        } catch (ExtensionNotFoundException e) {
            throw new ExtensionNotFoundException(e);
        }
    }

    /**
     * Create an InterfaceRealization. The created InterfaceRealization has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an InterfaceRealization representing the Abstraction in the
     * Model.
     * @return An InterfaceRealization representing the InterfaceRealization in the Model.
     */
    @objid ("dc30464c-51cb-4306-b71f-740a92e93a7b")
    @Override
    public InterfaceRealization createInterfaceRealization() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createInterfaceRealization();
    }

    /**
     * Create an InterfaceRealization.
     * @param source the origin of the InterfaceRealization.
     * @param destination the destination of the InterfaceRealization
     * @return An InterfaceRealization representing the InterfaceRealization in the Model.
     */
    @objid ("6a077a94-f888-4046-b401-afbd291722d1")
    @Override
    public InterfaceRealization createInterfaceRealization(final NameSpace source, final Interface destination) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createInterfaceRealization(source, destination);
    }

    /**
     * Create an InternalTransition. The created InternalTransition has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IInternalTransition representing the Abstraction in the Model.
     * @return An IInternalTransition representing the InternalTransition in the Model.
     */
    @objid ("9cd9b2ad-fc7b-475d-8837-88c000fd315e")
    @Override
    public InternalTransition createInternalTransition() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createInternalTransition();
    }

    /**
     * Create an InterruptibleActivityRegion. The created InterruptibleActivityRegion has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IInterruptibleActivityRegion representing the
     * InterruptibleActivityRegion in the Model.
     * @return An IInterruptibleActivityRegion representing the InterruptibleActivityRegion in the Model.
     */
    @objid ("26c811cf-3813-4f66-84e3-f74ab051ca1c")
    @Override
    public InterruptibleActivityRegion createInterruptibleActivityRegion() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createInterruptibleActivityRegion();
    }

    /**
     * Create an JoinPseudoState. The created JoinPseudoState has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IJoinPseudoState representing the JoinPseudoState in the Model.
     * @return An IJoinPseudoState representing the JoinPseudoState in the Model.
     */
    @objid ("7841f99a-d38f-44a8-be9e-43998c3fc834")
    @Override
    public JoinPseudoState createJoinPseudoState() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createJoinPseudoState();
    }

    /**
     * Create an JunctionPseudoState. The created JunctionPseudoState has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IJunctionPseudoState representing the JunctionPseudoState in the
     * Model.
     * @return An IJunctionPseudoState representing the JunctionPseudoState in the Model.
     */
    @objid ("0f8ee2bd-f340-499e-a5e3-aa7514c057a0")
    @Override
    public JunctionPseudoState createJunctionPseudoState() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createJunctionPseudoState();
    }

    /**
     * Create a Lifeline. The created Lifeline has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an ILifeline representing the Lifeline in the Model.
     * @return An ILifeline representing the Lifeline in the Model.
     */
    @objid ("9013ed66-6868-4d9b-8401-924cfebfedec")
    @Override
    public Lifeline createLifeline() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createLifeline();
    }

    /**
     * Create a Lifeline. The Lifeline is created with a <code>name</code> in the <code>owner</code> Interaction.
     * @param name the name of the Lifeline to create.
     * @param owner the owner Interaction of the Lifeline to create.
     * @return An ILifeline representing the Lifeline in the Model.
     */
    @objid ("ffc00342-3388-40d2-8d44-efc4d6b6f921")
    @Override
    public Lifeline createLifeline(final String name, final Interaction owner) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createLifeline(name, owner);
    }

    /**
     * Create a Lifeline. The Lifeline is created with a <code>name</code> in the <code>owner</code> Interaction. The <code>represented</code> instance is set on the Lifeline.
     * @param name the name of the Lifeline to create.
     * @param owner the owner Interaction of the Lifeline to create.
     * @param represented the Instance that will be represented by the Lifeline.
     * @return An ILifeline representing the Lifeline in the Model.
     */
    @objid ("aac6e431-0c95-4c34-b2a9-6f6a26684202")
    @Override
    public Lifeline createLifeline(final String name, final Interaction owner, final Instance represented) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createLifeline(name, owner, represented);
    }

    @objid ("1b629775-3890-4a15-8f81-27c6a2e1e6e4")
    @Override
    public Link createLink() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createLink();
    }

    @objid ("78aa7dbe-b5e6-49db-adaa-adc4029b2ec2")
    @Override
    public Link createLink(final Instance source, final Instance destination, final String destinationRole) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createLink(source, destination, destinationRole);
    }

    /**
     * Create a LinkEnd. The created LinkEnd has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an ILinkEnd representing the Abstraction in the Model.
     * @return An ILinkEnd representing the LinkEnd in the Model.
     */
    @objid ("c1c866c1-ea73-463a-86d6-f9b39f70eafa")
    @Override
    public LinkEnd createLinkEnd() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createLinkEnd();
    }

    @objid ("dea9760f-80ea-4cba-8b74-cfb76cab6025")
    @Override
    public LocalPropertyTable createLocalPropertyTable() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createLocalPropertyTable();
    }

    /**
     * Create a LoopNode. The created LoopNode has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an ILoopNode representing the LoopNode in the Model.
     * @return An ILoopNode representing the LoopNode in the Model.
     */
    @objid ("3e761365-697a-4317-83e0-840b7ee452f8")
    @Override
    public LoopNode createLoopNode() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createLoopNode();
    }

    /**
     * Create a Manifestation. The created Manifestation has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IManifestation representing the Manifestation in the Model.
     * @return An IManifestation representing the Manifestation in the Model.
     */
    @objid ("1e67fcf4-78e0-424c-b3b3-b57aa9c17850")
    @Override
    public Manifestation createManifestation() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createManifestation();
    }

    @objid ("bd435e1e-f663-41ec-bdf8-62bc74f11adb")
    @Override
    public MatrixDefinition createMatrixDefinition() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createMatrixDefinition();
    }

    @objid ("3df097a6-2565-46c4-af11-c04dbf7e54fc")
    @Override
    public MatrixDefinition createMatrixDefinition(final String name, final QueryDefinition lineQuery, final QueryDefinition colQuery, final QueryDefinition depthQuery, final MatrixValueDefinition valueDefinition) {
        MatrixDefinition matrixDef = createMatrixDefinition();
        matrixDef.setName(name);
        
        matrixDef.setLinesDefinition(lineQuery);
        matrixDef.setColumnsDefinition(colQuery);
        matrixDef.setDepthDefinition(depthQuery);
        matrixDef.setValuesDefinition(valueDefinition);
        return matrixDef;
    }

    @objid ("e1fd87d8-da6a-4dca-b640-5f2283c03a32")
    @Override
    public MatrixValueDefinition createMatrixValueDefinition() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createMatrixValueDefinition();
    }

    @objid ("5126fe16-6bd3-4d79-a157-cda1047826d3")
    @Override
    public MatrixValueDefinition createMatrixValueDefinition(final String implementationClassName, final String moduleName) {
        MatrixValueDefinition valueDef = createMatrixValueDefinition();
        
        ExternProcessor queryProcessor = createExternProcessor(moduleName, implementationClassName);
        valueDef.setProcessor(queryProcessor);
        return valueDef;
    }

    /**
     * Create a Message. The created Message has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IMessage representing the Message in the Model.
     * @return An IMessage representing the Message in the Model.
     */
    @objid ("7dbd3591-10f9-4ac4-8b38-a03d9c876697")
    @Override
    public Message createMessage() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createMessage();
    }

    /**
     * Create a Message. The Message is created with a type. In order to build a valid model, the Message must be inserted in the model.
     * @param sort the type of message to create.
     * @return An IMessage representing the Message in the Model.
     */
    @objid ("a2279023-3b89-4a81-9a2d-8299955aeefe")
    @Override
    public Message createMessage(final MessageSort sort) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createMessage(sort);
    }

    /**
     * Create a Message. The Message is created with a type and invoke an operation. In order to build a valid model, the Message must be inserted in the model.
     * @param sort the type of message to create.
     * @param invoked the operation that is invoked by the Message to create.
     * @return An IMessage representing the Message in the Model.
     */
    @objid ("288e8e58-4a06-4bfd-b131-a4c45a3a8434")
    @Override
    public Message createMessage(final MessageSort sort, final Operation invoked) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createMessage(sort, invoked);
    }

    /**
     * Create a Message. The Message is created with a <code>name</code> and a type. In order to build a valid model, the Message must be inserted in the model.
     * @param name the name of the message to create.
     * @param sort the type of message to create.
     * @return An IMessage representing the Message in the Model.
     */
    @objid ("caa14a4a-9376-4584-a64f-a40b51f0afd4")
    @Override
    public Message createMessage(final String name, final MessageSort sort) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createMessage(name, sort);
    }

    /**
     * Create a MessageFlow. The created MessageFlow has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IMessageFlow representing the MessageFlow in the Model.
     * @return An IMessageFlow representing the MessageFlow in the Model.
     */
    @objid ("19dcfa23-176d-45e8-8c8f-932cf6874a9c")
    @Override
    public MessageFlow createMessageFlow() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createMessageFlow();
    }

    @objid ("f30ad3f7-804f-40b4-8540-3a92da701ecc")
    @Override
    public NaryAssociation createNaryAssociation() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createNaryAssociation();
    }

    @objid ("8aae4856-f13c-4759-be6b-1807c1d87cb4")
    @Override
    public NaryAssociation createNaryAssociation(final List<Classifier> ends) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createNaryAssociation(ends);
    }

    @objid ("6bc72345-cc2f-410e-84c4-b2c45a81d283")
    @Override
    public NaryAssociationEnd createNaryAssociationEnd() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createNaryAssociationEnd();
    }

    @objid ("5ccba362-f27d-487b-917b-9f9c9833b74d")
    @Override
    public NaryConnector createNaryConnector() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createNaryConnector();
    }

    @objid ("1a5cd66a-62bf-4e72-ba8b-ca86b5ba840f")
    @Override
    public NaryConnector createNaryConnector(final List<BindableInstance> ends) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createNaryConnector(ends);
    }

    @objid ("3ffa1c73-b239-4840-b1ec-89e80de26110")
    @Override
    public NaryConnectorEnd createNaryConnectorEnd() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createNaryConnectorEnd();
    }

    @objid ("ffd6e526-ba50-44bb-9e97-f9e5414a1ef0")
    @Override
    public NaryLink createNaryLink() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createNaryLink();
    }

    @objid ("bf41ac12-0509-46f9-bc11-966333353352")
    @Override
    public NaryLink createNaryLink(final List<Instance> source) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createNaryLink(source);
    }

    @objid ("ace6b28d-aabd-4f3e-a4d6-7e8875f1e140")
    @Override
    public NaryLinkEnd createNaryLinkEnd() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createNaryLinkEnd();
    }

    /**
     * Create a Node. The created Node has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an INode representing the Abstraction in the Model.
     * @return An INode representing the Node in the Model.
     */
    @objid ("54232fee-c447-413c-985a-1889413bdb39")
    @Override
    public Node createNode() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createNode();
    }

    /**
     * Create a Note. The created Note has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an INote representing the Abstraction in the Model.
     * @return An INote representing the Note in the Model.
     */
    @objid ("645ba0f8-1b4d-4410-a3ca-cd9721a81a07")
    @Override
    public Note createNote() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createNote();
    }

    /**
     * Create a Note.
     * @param noteType the type of the Note.
     * @param owner the composition owner of the Note.
     * @param content the text of the Note.
     * @return An INote representing the Note in the Model.
     */
    @objid ("5c79e368-5b36-438a-aaca-e9d3a37c160e")
    @Override
    public Note createNote(final String moduleName, final String noteType, final ModelElement owner, final String content) throws ExtensionNotFoundException {
        try {
            return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createNote(moduleName, ".*", noteType, owner, content);
        } catch (ExtensionNotFoundException e) {
            throw new ExtensionNotFoundException(e);
        }
    }

    @objid ("24214696-421f-4613-ae3b-7c12aafc9995")
    @Override
    public Note createNote(final NoteType noteType, final ModelElement owner, final String content) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createNote(noteType, owner, content);
    }

    /**
     * Create a ObjectDiagram. The returned object is an IObjectDiagram owned by <code>owner</code> named by <code>name</code> and sterotyped by <code>stereotype</code>
     * @param name the name of the Object Diagram to create.
     * @param owner the element on which the Object Diagram will be created.
     * @param stereotype the name of the stereotype that will be used to type the Object Diagram
     * @return An IObjectDiagram representing the diagram in the Model.
     */
    @objid ("12ae7cb9-9983-4623-ad60-aae0d72a6b26")
    @Override
    public ObjectDiagram createObjectDiagram(final String name, final ModelElement owner, final Stereotype stereotype) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createObjectDiagram(name, owner, stereotype);
    }

    /**
     * Create a ObjectFlow. The created ObjectFlow has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IObjectFlow representing the ObjectFlow in the Model.
     * @return An IObjectFlow representing the ObjectFlow in the Model.
     */
    @objid ("61f69a24-a09e-4e91-a39d-b2697e5c4a4a")
    @Override
    public ObjectFlow createObjectFlow() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createObjectFlow();
    }

    /**
     * Create a OpaqueAction. The created OpaqueAction has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IOpaqueAction representing the OpaqueAction in the Model.
     * @return An IOpaqueAction representing the OpaqueAction in the Model.
     */
    @objid ("765801c8-2bd6-4437-b181-1c5d57e3cba9")
    @Override
    public OpaqueAction createOpaqueAction() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createOpaqueAction();
    }

    /**
     * Create a OpaqueBehavior. The created OpaqueBehavior has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IOpaqueBehavior representing the OpaqueBehavior in the Model.
     * @return An IOpaqueBehavior representing the OpaqueBehavior in the Model.
     */
    @objid ("fb44a558-46b1-49a9-bdef-702d8b31facb")
    @Override
    public OpaqueBehavior createOpaqueBehavior() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createOpaqueBehavior();
    }

    /**
     * Create an Operation. The created Operation has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an Operation representing the Abstraction in the Model.
     * @return An Operation representing the Operation in the Model.
     */
    @objid ("1f955855-48a5-4241-85f8-7a51c61d70d7")
    @Override
    public Operation createOperation() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createOperation();
    }

    /**
     * Create an Operation.
     * @param name the name of the Operation to create.
     * @param owner the Classifier that will contain the Operation.
     * @return An Operation representing the Operation in the Model.
     */
    @objid ("af70d0ac-c696-4ba5-b720-c5177bdd7164")
    @Override
    public Operation createOperation(final String name, final Classifier owner) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createOperation(name, owner);
    }

    /**
     * Create a stereotyped Operation.
     * @param name the name of the Operation to create.
     * @param owner the Classifier that will contain the Operation.
     * @param stereotype the Stereotype that will extend the Operation.
     * @return An Operation representing the Operation in the Model.
     */
    @objid ("5083f70f-a877-432e-b35d-6660312541ba")
    @Override
    public Operation createOperation(final String name, final Classifier owner, final Stereotype stereotype) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createOperation(name, owner, stereotype);
    }

    @objid ("1a1b32f2-cb9b-47da-8163-4ac91a9de6ba")
    @Override
    public Operation createOperation(final String name, final Classifier owner, final String moduleName, final String stereotypeName) throws ExtensionNotFoundException {
        try {
            return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createOperation(name, owner, moduleName, stereotypeName);
        } catch (ExtensionNotFoundException e) {
            throw new ExtensionNotFoundException(e);
        }
    }

    /**
     * Create a OutputPin. The created OutputPin has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IOutputPin representing the OutputPin in the Model.
     * @return An IOutputPin representing the OutputPin in the Model.
     */
    @objid ("d9920f99-74c5-48f7-a320-a81a2e5fb850")
    @Override
    public OutputPin createOutputPin() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createOutputPin();
    }

    /**
     * Create a Package. The created Package has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an Package representing the Abstraction in the Model.
     * @return An Package representing the Package in the Model.
     */
    @objid ("ac540fc4-f1d0-4f00-b6a5-3395b015cc1a")
    @Override
    public Package createPackage() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createPackage();
    }

    /**
     * Create a Package.
     * @param name the name of the Package to create.
     * @param owner the NameSpace that will contain the Package.
     * @return An Package representing the Package in the Model.
     */
    @objid ("bee25450-3526-4666-9197-a116cb38efd8")
    @Override
    public Package createPackage(final String name, final NameSpace owner) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createPackage(name, owner);
    }

    /**
     * Create a stereotyped Package.
     * @param name the name of the Package to create.
     * @param owner the NameSpace that will contain the Package.
     * @param stereotype the Stereotype will extend the Package.
     * @return An Package representing the Package in the Model.
     */
    @objid ("3f7dd924-319a-436c-8a41-f78fdd162c3c")
    @Override
    public Package createPackage(final String name, final NameSpace owner, final Stereotype stereotype) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createPackage(name, owner, stereotype);
    }

    @objid ("55105b38-a63c-47dc-8e9a-929ed7357cab")
    @Override
    public Package createPackage(final String name, final NameSpace owner, final String moduleName, final String stereotypeName) throws ExtensionNotFoundException {
        try {
            return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createPackage(name, owner, moduleName, stereotypeName);
        } catch (ExtensionNotFoundException e) {
            throw new ExtensionNotFoundException(e);
        }
    }

    /**
     * Create a PackageImport. The created PackageImport has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an PackageImport representing the Abstraction in the Model.
     * @return An PackageImport representing the PackageImport in the Model.
     */
    @objid ("b81ed849-1db5-4303-a779-f92822d6d073")
    @Override
    public PackageImport createPackageImport() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createPackageImport();
    }

    /**
     * Create a PackageImport between a NameSpace and a Package.
     * @param source origin of the ElementImport
     * @param destination destination of the ElementImport
     * @return An ElementImport representing the ElementImport in the Model.
     */
    @objid ("90dbf98a-96a8-4d0b-8832-3dff4c71407f")
    @Override
    public PackageImport createPackageImport(final NameSpace source, final Package destination) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createPackageImport(source, destination);
    }

    /**
     * Create a PackageImport between an Operation and a Package.
     * @param source origin of the ElementImport
     * @param destination destination of the ElementImport
     * @return An ElementImport representing the ElementImport in the Model.
     */
    @objid ("e1edddb5-b1ce-4960-85e7-f7183850ed14")
    @Override
    public PackageImport createPackageImport(final Operation source, final Package destination) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createPackageImport(source, destination);
    }

    /**
     * Create a PackageMerge. The created PackageMerge has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an PackageMerge representing the Abstraction in the Model.
     * @return An PackageMerge representing the PackageMerge in the Model.
     */
    @objid ("80179be7-f1c8-4955-ad64-611b43b6191d")
    @Override
    public PackageMerge createPackageMerge() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createPackageMerge();
    }

    /**
     * Create a Parameter. The created Parameter has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IParameter representing the Abstraction in the Model.
     * @return An IParameter representing the Parameter in the Model.
     */
    @objid ("c4e4c998-e49b-476a-acb4-80f32bc0b9b0")
    @Override
    public Parameter createParameter() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createParameter();
    }

    /**
     * Create a PartDecomposition. The created PartDecomposition has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IPartDecomposition representing the PartDecomposition in the Model.
     * @return An IPartDecomposition representing the PartDecomposition in the Model.
     */
    @objid ("52d072b8-0d41-4647-8b0b-18120a0e8943")
    @Override
    public PartDecomposition createPartDecomposition() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createPartDecomposition();
    }

    /**
     * Create an PartDecomposition. The created PartDecomposition refer an Interaction. In order to build a valid model, the PartDecomposition must be inserted in the model.
     * @param refered the refered Interaction.
     * @return An IPartDecomposition representing the PartDecomposition in the Model.
     */
    @objid ("3bea65df-da61-4d8c-b3a5-7bb371036f7e")
    @Override
    public PartDecomposition createPartDecomposition(final Interaction refered) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createPartDecomposition(refered);
    }

    /**
     * Create a Port. The created Port has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an Port representing the Abstraction in the Model.
     * @return An Port representing the Port in the Model.
     */
    @objid ("dae2500c-1ff9-464a-89bc-f7ed271fc36a")
    @Override
    public Port createPort() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createPort();
    }

    /**
     * Create a Port. The Port is created with the specified name and owner Instance.
     * @param name The name of the Port to create.
     * @param owner The Instance that will contain the Port.
     * @return An Port representing the Port in the Model.
     */
    @objid ("6978a4be-a023-4593-a78c-45135608d90b")
    @Override
    public Port createPort(final String name, final Instance owner) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createPort(name, owner);
    }

    /**
     * Create a Port. The port is Created with the specified name and owner Classifier.
     * @param name The name of the Port to create.
     * @param owner The Classifier that will contain the Port.
     * @return An Port representing the Port in the Model.
     */
    @objid ("f0617404-80fe-40c8-8248-8e8085d02cb5")
    @Override
    public Port createPort(final String name, final Classifier owner) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createPort(name, owner);
    }

    @objid ("a6c9b1cb-54c6-4beb-a788-c36251643da4")
    @Override
    public PropertyDefinition createPropertyDefinition() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createPropertyDefinition();
    }

    @objid ("9c006404-0a49-4da9-8938-b7205a1a5b8e")
    @Override
    public PropertyEnumerationLitteral createPropertyEnumerationLitteral() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createPropertyEnumerationLitteral();
    }

    @objid ("530a05a2-2edd-4054-bd91-e3fddcabc25b")
    @Override
    public PropertyTable createPropertyTable() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createPropertyTable();
    }

    @objid ("9c354772-7c0a-47f3-84be-2f51a689c920")
    @Override
    public PropertyTableDefinition createPropertyTableDefinition() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createPropertyTableDefinition();
    }

    @objid ("a99e71ac-3b2f-4649-9792-d557a283aa4c")
    @Override
    public PropertyType createPropertyType() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createPropertyType();
    }

    /**
     * Create a ProvidedInterface. The created ProvidedInterface has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IProvidedInterface representing the Abstraction in the Model.
     * @return An IProvidedInterface representing the ProvidedInterface in the Model.
     */
    @objid ("5f2e34e7-e6bd-419e-aa77-7802e89cbe30")
    @Override
    public ProvidedInterface createProvidedInterface() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createProvidedInterface();
    }

    /**
     * Create a ProvidedInterface.
     * @param owner the Port that contains the ProvidedInterface.
     * @param interfaces the provided Interfaces
     * @return An IProvidedInterface representing the ProvidedInterface in the Model.
     */
    @objid ("14fe60b5-9a15-4606-8315-22652ef455aa")
    @Override
    public ProvidedInterface createProvidedInterface(final Port owner, final List<Interface> interfaces) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createProvidedInterface(owner, interfaces);
    }

    @objid ("2b538b63-efe1-47e0-be8d-bb7c58c68c64")
    @Override
    public QueryDefinition createQueryDefinition() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createQueryDefinition();
    }

    @objid ("5dbef4ae-a701-4826-8921-65d2091298fd")
    @Override
    public QueryDefinition createQueryDefinition(final String implementationClassName, final String moduleName) {
        QueryDefinition query = createQueryDefinition();
        
        ExternProcessor queryProcessor = createExternProcessor(moduleName, implementationClassName);
        query.setProcessor(queryProcessor);
        return query;
    }

    /**
     * Create a RaisedException. The created RaisedException has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IRaisedException representing the Abstraction in the Model.
     * @return An IRaisedException representing the RaisedException in the Model.
     */
    @objid ("dc67187c-c219-407e-8eac-c7e51cd45f4d")
    @Override
    public RaisedException createRaisedException() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createRaisedException();
    }

    /**
     * Create a Region. The created Region has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IRegion representing the Abstraction in the Model.
     * @return An IRequiredInterface representing the RequiredInterface in the Model.
     */
    @objid ("d3f09d72-2dfa-4eaf-9b43-a7f04cf9561a")
    @Override
    public Region createRegion() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createRegion();
    }

    /**
     * Create a RequiredInterface. The created RequiredInterface has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IRequiredInterface representing the Abstraction in the Model.
     * @return An IRequiredInterface representing the RequiredInterface in the Model.
     */
    @objid ("2cbd7ba6-0a75-4bbe-9fb6-af7a55bff30a")
    @Override
    public RequiredInterface createRequiredInterface() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createRequiredInterface();
    }

    /**
     * Create a RequiredInterface.
     * @param owner the Port that contains the RequiredInterface.
     * @param interfaces the required Interfaces
     * @return An IRequiredInterface representing the RequiredInterface in the Model.
     */
    @objid ("ecf076d4-fa06-497b-b351-71d52070fd8f")
    @Override
    public RequiredInterface createRequiredInterface(final Port owner, final List<Interface> interfaces) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createRequiredInterface(owner, interfaces);
    }

    /**
     * Create an ReturnParameter.
     * @param name the name of the Parameter to create.
     * @param type the type of the Parameter to create.
     * @param owner the Operation that will contain the Parameter.
     * @return An IParameter representing the Parameter in the Model.
     */
    @objid ("861a5238-f660-4f2b-bad9-d94309774fcd")
    @Override
    public Parameter createReturnParameter(final String name, final GeneralClass type, final Operation owner) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createReturnParameter(name, type, owner);
    }

    /**
     * Create a stereotyped ReturnParameter.
     * @param name the name of the Parameter to create.
     * @param type the type of the Parameter to create.
     * @param owner the Operation that will contain the Parameter.
     * @param stereotype the stereotype that will extend the Parameter.
     * @return An IParameter representing the Parameter in the Model.
     */
    @objid ("85561e7d-15fc-4648-a563-22759d1c74e2")
    @Override
    public Parameter createReturnParameter(final String name, final GeneralClass type, final Operation owner, final Stereotype stereotype) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createReturnParameter(name, type, owner, stereotype);
    }

    @objid ("be08f78f-7b26-4550-9ea7-30c4d107fc16")
    @Override
    public Parameter createReturnParameter(final String name, final GeneralClass type, final Operation owner, final String moduleName, final String stereotypeName) throws ExtensionNotFoundException {
        try {
            return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createReturnParameter(name, type, owner, moduleName, stereotypeName);
        } catch (ExtensionNotFoundException e) {
            throw new ExtensionNotFoundException(e);
        }
    }

    /**
     * Create a SendSignalAction. The created SendSignalAction has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an ISendSignalAction representing the SendSignalAction in the Model.
     * @return An ISendSignalAction representing the SendSignalAction in the Model.
     */
    @objid ("84dd4915-8d20-45d6-a05c-0c9dc7cee008")
    @Override
    public SendSignalAction createSendSignalAction() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createSendSignalAction();
    }

    /**
     * Create a SequenceDiagram. The created SequenceDiagram has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an ISequenceDiagram representing the SequenceDiagram in the Model.
     * @return An ISequenceDiagram representing the SequenceDiagram in the Model.
     */
    @objid ("bfcc9a46-64d3-4dfb-ac9f-e0b6bc2e0984")
    @Override
    public SequenceDiagram createSequenceDiagram() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createSequenceDiagram();
    }

    /**
     * Create a ShallowHistoryPseudoState. The created ShallowHistoryPseudoState has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IShallowHistoryPseudoState representing the
     * ShallowHistoryPseudoState in the Model.
     * @return An IShallowHistoryPseudoState representing the ShallowHistoryPseudoState in the Model.
     */
    @objid ("d0e2d73e-1ade-4b13-a428-17323adb93e1")
    @Override
    public ShallowHistoryPseudoState createShallowHistoryPseudoState() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createShallowHistoryPseudoState();
    }

    /**
     * Create a Signal. The created Signal has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an ISignal representing the Abstraction in the Model.
     * @return An ISignal representing the Signal in the Model.
     */
    @objid ("6042d49a-4a28-4e4f-94d2-898111589c4e")
    @Override
    public Signal createSignal() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createSignal();
    }

    /**
     * Create a State. The created State has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IState representing the Abstraction in the Model.
     * @return An IState representing the State in the Model.
     */
    @objid ("1a8cd5b2-98f9-49de-b53e-3771c6ea0661")
    @Override
    public State createState() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createState();
    }

    /**
     * Create a StateInvariant. The created StateInvariant has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IStateInvariant representing the StateInvariant in the Model.
     * @return An IStateInvariant representing the StateInvariant in the Model.
     */
    @objid ("96dd168f-d338-4eab-96b9-188a32bbcd6b")
    @Override
    public StateInvariant createStateInvariant() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createStateInvariant();
    }

    /**
     * Create a StateInvariant. Create a StateInvariant with a body. In order to build a valid model, the StateInvariant must be inserted in the model.
     * @param body the body of the StateInvariant to create.
     * @return An IStateInvariant representing the StateInvariant in the Model.
     */
    @objid ("d37e1e28-6745-45aa-80d2-1edcabedac0e")
    @Override
    public StateInvariant createStateInvariant(final String body) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createStateInvariant(body);
    }

    /**
     * Create a StateMachine. The created StateMachine has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IStateMachine representing the Abstraction in the Model.
     * @return An IStateMachine representing the StateMachine in the Model.
     */
    @objid ("4fc78b10-425d-4e38-baba-5c325873a412")
    @Override
    public StateMachine createStateMachine() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createStateMachine();
    }

    /**
     * Create a StateMachineDiagram. The returned object is an IStateMachineDiagram owned by <code>owner</code> named by <code>name</code> and sterotyped by <code>stereotype</code>
     * @param name the name of the StateMachine Diagram to create.
     * @param owner the element on which the StateMachine Diagram will be created.
     * @param stereotype the name of the stereotype that will be used to type the StateMachine Diagram
     * @return An IStateMachineDiagram representing the diagram in the Model.
     */
    @objid ("d2c7ed8f-5403-4061-9140-b8ea1acaf371")
    @Override
    public StateMachineDiagram createStateMachineDiagram(final String name, final ModelElement owner, final Stereotype stereotype) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createStateMachineDiagram(name, owner, stereotype);
    }

    /**
     * Create a StaticDiagram. The returned object is an IStaticDiagram owned by <code>owner</code> named by <code>name</code> and sterotyped by <code>stereotype</code>
     * @param name the name of the Static Diagram to create.
     * @param owner the element on which the Static Diagram will be created.
     * @param stereotype the name of the stereotype that will be used to type the Static Diagram
     * @return An IStaticDiagram representing the diagram in the Model.
     */
    @objid ("f116cde3-4e97-4d83-93d2-78853727eeed")
    @Override
    public StaticDiagram createStaticDiagram(final String name, final ModelElement owner, final Stereotype stereotype) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createStaticDiagram(name, owner, stereotype);
    }

    @objid ("2a375daa-34e9-4233-9696-2a4a242ad5b9")
    @Override
    public StaticDiagram createStaticDiagram(final String name, final ModelElement owner, final String moduleName, final String stereotypeName) throws ExtensionNotFoundException {
        try {
            return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createStaticDiagram(name, owner, moduleName, stereotypeName);
        } catch (ExtensionNotFoundException e) {
            throw new ExtensionNotFoundException(e);
        }
    }

    /**
     * Create a StructuredActivityNode. The created StructuredActivityNode has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IStructuredActivityNode representing the
     * StructuredActivityNode in the Model.
     * @return An IStructuredActivityNode representing the StructuredActivityNode in the Model.
     */
    @objid ("e3e40be7-5c99-4b7d-bdfb-745f2dfa9d67")
    @Override
    public StructuredActivityNode createStructuredActivityNode() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createStructuredActivityNode();
    }

    /**
     * Create a Substitution. The created Substitution has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an ISubstitution representing the Abstraction in the Model.
     * @return An ISubstitution representing the Substitution in the Model.
     */
    @objid ("de134fc3-37af-4933-a665-3516b7d1bd88")
    @Override
    public Substitution createSubstitution() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createSubstitution();
    }

    /**
     * Create a TagParameter. The created TagParameter has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an TagParameter representing the Abstraction in the Model.
     * @return An TagParameter representing the TagParameter in the Model.
     */
    @objid ("7d47e6bd-5be8-40a5-88c7-3f095a55240c")
    @Override
    public TagParameter createTagParameter() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createTagParameter();
    }

    /**
     * Create a TagParameter using a parameter as initial value.
     * @param value the value of the tag parameter.
     * @param owner the owner tagged value of the tag parameter.
     * @return An TagParameter representing the TagParameter in the Model.
     */
    @objid ("cca10d4f-9707-4f3d-a5c4-66587e4d1415")
    @Override
    public TagParameter createTagParameter(final String value, final TaggedValue owner) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createTagParameter(value, owner);
    }

    /**
     * Create a TaggedValue. The created TaggedValue has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an TaggedValue representing the Abstraction in the Model.
     * @return An TaggedValue representing the TaggedValue in the Model.
     */
    @objid ("f0b810fb-3997-40f7-924f-8c397fe5b26a")
    @Override
    public TaggedValue createTaggedValue() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createTaggedValue();
    }

    @objid ("7ea862b2-4156-4e10-9882-339d3f78bb10")
    @Override
    public TaggedValue createTaggedValue(final TagType tagType, final ModelElement owner) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createTaggedValue(tagType, owner);
    }

    /**
     * Create a TaggedValue.
     * @param tagType the type of the TaggedValue.
     * @param owner the ModelElement that contains the Taggedvalue.
     * @return An TaggedValue representing the TaggedValue in the Model.
     */
    @objid ("9cae2502-580f-4c33-99c5-c77104f32c5b")
    @Override
    public TaggedValue createTaggedValue(final String moduleName, final String tagType, final ModelElement owner) throws ExtensionNotFoundException {
        try {
            return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createTaggedValue(moduleName, ".*", tagType, owner);
        } catch (ExtensionNotFoundException e) {
            throw new ExtensionNotFoundException(e);
        }
    }

    /**
     * Create a TemplateBinding. The created TemplateBinding has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an ITemplateBinding representing the Abstraction in the Model.
     * @return An ITemplateBinding representing the TemplateBinding in the Model.
     */
    @objid ("ff7fddc8-e92f-4b28-ba02-4d9d4e62875d")
    @Override
    public TemplateBinding createTemplateBinding() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createTemplateBinding();
    }

    /**
     * Create a TemplateParameter. The created TemplateParameter has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an ITemplateParameter representing the Abstraction in the Model.
     * @return An ITemplateParameter representing the TemplateParameter in the Model.
     */
    @objid ("76e07202-3310-431a-a684-f28af7793106")
    @Override
    public TemplateParameter createTemplateParameter() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createTemplateParameter();
    }

    /**
     * Create a TemplateParameterSubstitution. The created TemplateParameterSubstitution has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an ITemplateParameterSubstitution representing
     * the Abstraction in the Model.
     * @return An ITemplateParameterSubstitution representing the TemplateParameterSubstitution in the Model.
     */
    @objid ("9fea54cb-70ea-4fd5-99bf-6d7b79131860")
    @Override
    public TemplateParameterSubstitution createTemplateParameterSubstitution() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createTemplateParameterSubstitution();
    }

    /**
     * Create a TerminatePseudoState. The created TerminatePseudoState has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an ITerminatePseudoState representing the TerminatePseudoState in
     * the Model.
     * @return An ITerminatePseudoState representing the TerminatePseudoState in the Model.
     */
    @objid ("11f059ff-de55-4aa1-9b1b-c3769d590916")
    @Override
    public TerminatePseudoState createTerminatePseudoState() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createTerminatePseudoState();
    }

    /**
     * Create a Transition. The created Transition has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an ITransition representing the Abstraction in the Model.
     * @return An ITransition representing the Transition in the Model.
     */
    @objid ("8a6bff65-6cbd-4ff5-9427-9bb578111df0")
    @Override
    public Transition createTransition() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createTransition();
    }

    @objid ("2be09f54-420b-43f5-b9a3-f6ced449b801")
    @Override
    public TypedPropertyTable createTypedPropertyTable() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createTypedPropertyTable();
    }

    /**
     * Create a Usage. The created Usage has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an IUsage representing the Abstraction in the Model.
     * @return An IUsage representing the Usage in the Model.
     */
    @objid ("8b227e3d-736b-414f-8326-f5463afd46e5")
    @Override
    public Usage createUsage() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createUsage();
    }

    /**
     * Create a Usage between two ModelElement.
     * @param source the origin of the Usage.
     * @param destination the destination of the Usage.
     * @return An IUsage representing the Usage in the Model.
     */
    @objid ("2ba70128-ca57-42b0-a51d-8488a6511df9")
    @Override
    public Usage createUsage(final ModelElement source, final ModelElement destination) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createUsage(source, destination);
    }

    /**
     * Create a UseCase. The created UseCase has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an UseCase representing the Abstraction in the Model.
     * @return An UseCase representing the UseCase in the Model.
     */
    @objid ("c2405f87-b982-4a4a-8e7f-4fc710cde6ae")
    @Override
    public UseCase createUseCase() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createUseCase();
    }

    /**
     * Create a UseCase.
     * @param name the name of the UseCase to create.
     * @param owner the NameSpace that will contain the Usecase.
     * @return An UseCase representing the UseCase in the Model.
     */
    @objid ("13ee1f2b-6a23-41b8-bfa1-4a824912cc73")
    @Override
    public UseCase createUseCase(final String name, final NameSpace owner) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createUseCase(name, owner);
    }

    /**
     * Create a stereotyped UseCase.
     * @param name the name of the UseCase to create.
     * @param owner the NameSpace that will contain the Usecase.
     * @param stereotype the Stereotype that will extend the UseCase.
     * @return An UseCase representing the UseCase in the Model.
     */
    @objid ("802c45d3-0bda-4bd6-b38c-000723ca3621")
    @Override
    public UseCase createUseCase(final String name, final NameSpace owner, final Stereotype stereotype) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createUseCase(name, owner, stereotype);
    }

    @objid ("2dcab824-ce24-4916-a875-dde0c5118f07")
    @Override
    public UseCase createUseCase(final String name, final NameSpace owner, final String moduleName, final String stereotypeName) throws ExtensionNotFoundException {
        try {
            return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createUseCase(name, owner, moduleName, stereotypeName);
        } catch (ExtensionNotFoundException e) {
            throw new ExtensionNotFoundException(e);
        }
    }

    /**
     * Create a UseCaseDependency. The created UseCaseDependency has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an UseCaseDependency representing the Abstraction in the Model.
     * @return An UseCaseDependency representing the UseCaseDependency in the Model.
     */
    @objid ("d4747b19-4159-4fae-aa73-9f8dd2c1dac3")
    @Override
    public UseCaseDependency createUseCaseDependency() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createUseCaseDependency();
    }

    /**
     * Create a UseCaseDiagram. The returned object is an UseCaseDiagram owned by <code>owner</code> named by <code>name</code> and sterotyped by <code>stereotype</code>
     * @param name the name of the UseCase Diagram to create.
     * @param owner the element on which the UseCase Diagram will be created.
     * @param stereotype the name of the stereotype that will be used to type the UseCase Diagram
     * @return An UseCaseDiagram representing the diagram in the Model.
     */
    @objid ("46591730-66ea-4d03-a448-9baa22b15ae7")
    @Override
    public UseCaseDiagram createUseCaseDiagram(final String name, final ModelElement owner, final Stereotype stereotype) {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createUseCaseDiagram(name, owner, stereotype);
    }

    @objid ("d3eb46f0-2e06-48c6-b336-7ccaf31d8dc3")
    @Override
    public ValuePin createValuePin() {
        return this.modelService.getModelFactory().getFactory(IStandardModelFactory.class).createValuePin();
    }

    @objid ("b4ec2134-7e27-488e-9db6-6e399ac56d8c")
    @Override
    public IDefaultNameService getDefaultNameService() {
        return new DefaultNameService(this.modelService.getElementNamer());
    }

    /**
     * Get all the roots of the libraries. They are mostly instances of {@link AbstractProject}.
     * @return The library root elements.
     */
    @objid ("a417bb8d-fe24-47cc-95c6-f748f9733031")
    @Override
    public List<MObject> getLibraryRoots() {
        List<MObject> ret = new ArrayList<>();
        for (IProjectFragment fragment : this.openedProject.getFragments()) {
            switch (fragment.getType()) {
            case RAMC:
            case EXML_URL:
                ret.addAll(fragment.getRoots());
                break;
            default:
                // do nothing
                break;
            }
        }
        return ret;
    }

    /**
     * Get all the roots of the editable model. They are mostly instances of {@link AbstractProject}.
     * @return The model root elements.
     */
    @objid ("d88baddf-eb4a-4b32-847d-08cdccd9696a")
    @Override
    public List<MObject> getModelRoots() {
        List<MObject> ret = new ArrayList<>();
        for (IProjectFragment fragment : this.openedProject.getFragments()) {
            switch (fragment.getType()) {
            case EXML:
            case EXML_SVN:
                ret.addAll(fragment.getRoots());
                break;
            default:
                // do nothing
                break;
            }
        }
        return ret;
    }

    @objid ("1791ac07-90d1-43ea-9835-f8d7fc4d5a1c")
    @Override
    public MObject getRoot(final MObject context) {
        MObject contextElement = context;
        while (contextElement.getCompositionOwner() != null) {
            contextElement = contextElement.getCompositionOwner();
        }
        
        for (MObject root : getModelRoots()) {
            if (root.equals(contextElement)) {
                return root;
            }
        }
        
        for (MObject root : getLibraryRoots()) {
            if (root.equals(contextElement)) {
                return root;
            }
        }
        return null;
    }

    /**
     * Get access to the UML types
     * @return an object that allow access to UML types
     */
    @objid ("e200e79c-00f1-42c1-8251-9a4b19ed1dba")
    @Override
    public IUMLTypes getUmlTypes() {
        // Automatically generated method. Please delete this comment before
        // entering specific code.
        return this.umlTypes;
    }

}
