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

package org.modelio.api.modelio.model;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
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
import org.modelio.metamodel.uml.infrastructure.ExternElement;
import org.modelio.metamodel.uml.infrastructure.ExternProcessor;
import org.modelio.metamodel.uml.infrastructure.MethodologicalLink;
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
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * The IUmlModel provide navigation and modification in the UML model represented in Modelio.
 * <p>
 * To navigate the UML model use the {@linkplain #getModelRoots()} method. this method give access to the root Package of the
 * model. The whole model can be navigate through the associations that links the model elements to each other.
 * </p>
 * <p>
 * The IUmlModel is also the factory which create the UML model elements.
 * </p>
 * <p>
 * Any model modification must be done in a transaction. It is the MDAC developer responsibility to ensure that the
 * newly created metaclass is valid before commiting a transaction.
 * </p>
 * <p>
 * New UML model elements can easily be created using <i>createXxxxx()</i> methods where Xxxxx is the name of the
 * metaclass for which an instance has to be created. At least one method <i>createXxxxx()</i> exists for UML model
 * Element. i.e.: {@linkplain #createClass()}. This method only create an instance of the metaclass. The instance will
 * have to be attached to the model and eventually named to be valid for the current transaction.
 * </p>
 * <p>
 * The factory provide some convenience methods that allow to directly create a valid instance for the most frequently
 * used metaclasses. i.e.: {@linkplain #createClass(String name, NameSpace owner)},
 * {@linkplain #createClass(String name, NameSpace owner, Stereotype stereotype)}. These methods ensure the validity of the
 * created element.
 * </p>
 */
@objid ("b343a657-644e-11e0-b650-001ec947cd2a")
public interface IUmlModel {
    /**
     * Create an Abstraction.
     * The created Abstraction has no composition owner. In order to build a valid model, a composition owner must be
     * defined. The returned object is an Abstraction representing the Abstraction in the Model.
     * 
     * @return An Abstraction representing the Abstraction in the Model.
     */
    @objid ("f7f2af5f-644e-11e0-b650-001ec947cd2a")
    Abstraction createAbstraction();

    /**
     * Create an AcceptCallEventAction.
     * The created AcceptCallEventAction has no composition owner. In order to build a valid model, a composition owner
     * must be defined. The returned object is an IAcceptCallEventAction representing the AcceptCallEventAction in the
     * Model.
     * 
     * @return An AcceptCallEventAction representing the AcceptCallEventAction in the Model.
     */
    @objid ("f7f2af5e-644e-11e0-b650-001ec947cd2a")
    AcceptCallEventAction createAcceptCallEventAction();

    /**
     * Create an AcceptChangeEventAction.
     * The created AcceptChangeEventAction has no composition owner. In order to build a valid model, a composition
     * owner must be defined. The returned object is an IAcceptChangeEventAction representing the
     * AcceptChangeEventAction in the Model.
     * 
     * @return An AcceptChangeEventAction representing the AcceptChangeEventAction in the Model.
     */
    @objid ("f7f2af5d-644e-11e0-b650-001ec947cd2a")
    AcceptChangeEventAction createAcceptChangeEventAction();

    /**
     * Create an AcceptSignalAction.
     * The created AcceptSignalAction has no composition owner. In order to build a valid model, a composition owner
     * must be defined. The returned object is an IAcceptSignalAction representing the AcceptSignalAction in the Model.
     * 
     * @return An AcceptSignalAction representing the AcceptSignalAction in the Model.
     */
    @objid ("f7f2af5c-644e-11e0-b650-001ec947cd2a")
    AcceptSignalAction createAcceptSignalAction();

    /**
     * Create an AcceptTimeEventAction.
     * The created AcceptTimeEventAction has no composition owner. In order to build a valid model, a composition owner
     * must be defined. The returned object is an IAcceptTimeEventAction representing the AcceptTimeEventAction in the
     * Model.
     * 
     * @return An AcceptTimeEventAction representing the AcceptTimeEventAction in the Model.
     */
    @objid ("f7f2af5b-644e-11e0-b650-001ec947cd2a")
    AcceptTimeEventAction createAcceptTimeEventAction();

    /**
     * Create an Activity.
     * The created Activity has no composition owner. In order to build a valid model, a composition owner must be
     * defined. The returned object is an IActivity representing the Abstraction in the Model.
     * 
     * @return An Activity representing the Activity in the Model.
     */
    @objid ("f7f2af5a-644e-11e0-b650-001ec947cd2a")
    Activity createActivity();

    /**
     * Create an ActivityDiagram.
     * The returned object is an IActivityDiagram owned by 'owner' and named by 'name'
     * 
     * @param name the name of the ActivityDiagram to be created.
     * @param owner the ModelElement that will contain the ActivityDiagram.
     * @return An ActivityDiagram representing the diagram in the Model.
     */
    @objid ("f7f2af59-644e-11e0-b650-001ec947cd2a")
    ActivityDiagram createActivityDiagram(String name, ModelElement owner);

    /**
     * Create an ActivityFinalNode.
     * The created ActivityFinalNode has no composition owner. In order to build a valid model, a composition owner must
     * be defined. The returned object is an IActivityFinalNode representing the ActivityFinalNode in the Model.
     * 
     * @return An ActivityFinalNode representing the ActivityFinalNode in the Model.
     */
    @objid ("f7f2af58-644e-11e0-b650-001ec947cd2a")
    ActivityFinalNode createActivityFinalNode();

    /**
     * Create an ActivityParameterNode.
     * The created ActivityParameterNode has no composition owner. In order to build a valid model, a composition owner
     * must be defined. The returned object is an IActivityParameterNode representing the ActivityParameterNode in the
     * Model.
     * 
     * @return An ActivityParameterNode representing the ActivityParameterNode in the Model.
     */
    @objid ("f7f2af56-644e-11e0-b650-001ec947cd2a")
    ActivityParameterNode createActivityParameterNode();

    /**
     * Create an ActivityPartition.
     * The created ActivityPartition has no composition owner. In order to build a valid model, a composition owner must
     * be defined. The returned object is an IActivityPartition representing the ActivityPartition in the Model.
     * 
     * @return An ActivityPartition representing the ActivityPartition in the Model.
     */
    @objid ("f7f2af55-644e-11e0-b650-001ec947cd2a")
    ActivityPartition createActivityPartition();

    /**
     * Create an Actor.
     * The created Actor has no composition owner. In order to build a valid model, a composition owner must be defined.
     * The returned object is an IActor representing the Abstraction in the Model.
     * 
     * @return An Actor representing the Actor in the Model.
     */
    @objid ("f7f2af54-644e-11e0-b650-001ec947cd2a")
    Actor createActor();

    /**
     * Create an Actor.
     * The created Actor is valid for the current transaction. (It as a name and an owner.)
     * 
     * @param name The name of the Actor to be created.
     * @param owner The NameSpace that will contain the Actor.
     * @return An Actor representing the Actor in the Model.
     */
    @objid ("f7f2af53-644e-11e0-b650-001ec947cd2a")
    Actor createActor(String name, NameSpace owner);

    /**
     * Create an Actor with the desired name and stereotype.
     * <p>
     * <p>
     * The created Actor is valid for the current transaction. (It as a name and an owner.)
     * <p>
     * <p>
     * The created Actor is stereotyped by the <code>stereotype</code> argument. The
     * <code>StereotypeNotFoundException</code> is thrown if the stereotype is not found.
     * 
     * @param name The name of the Actor to be created.
     * @param owner The NameSpace that will contain the Actor.
     * @param stereotype the Stereotype that will be refered by the Actor.
     * @return An Actor representing the Actor in the Model.
     */
    @objid ("f7f2af52-644e-11e0-b650-001ec947cd2a")
    Actor createActor(String name, NameSpace owner, Stereotype stereotype);

    /**
     * Create an Actor with the desired name and stereotype.
     * <p>
     * <p>
     * The created Actor is valid for the current transaction. (It as a name and an owner.)
     * <p>
     * <p>
     * The created Actor is stereotyped by the <code>stereotype</code> argument. The
     * <code>StereotypeNotFoundException</code> is thrown if the stereotype is not found.
     * 
     * @param name The name of the Actor to be created.
     * @param owner The NameSpace that will contain the Actor.
     * @param moduleName the module owning the Stereotype that will be refered by the Actor.
     * @param stereotypeName the Stereotype that will be refered by the Actor.
     * @return An Actor representing the Actor in the Model.
     * @throws org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException if no stereotype matching the given name and the element metaclass is found
     */
    @objid ("ae17a569-020f-4958-9b56-5c1e5423a03f")
    Actor createActor(String name, NameSpace owner, String moduleName, String stereotypeName) throws ExtensionNotFoundException;

    /**
     * Create an Artifact.
     * The created Artifact has no composition owner. In order to build a valid model, a composition owner must be
     * defined. The returned object is an Artifact representing the Abstraction in the Model.
     * 
     * @return An Artifact representing the Artifact in the Model.
     */
    @objid ("f7f2af51-644e-11e0-b650-001ec947cd2a")
    Artifact createArtifact();

    /**
     * Create an Artifact.
     * The created Artifact is valid for the current transaction. (It as a name and an owner.)
     * 
     * @param name The name of the Artifact to be created.
     * @param owner The NameSpace that will contain the Artifact.
     * @return An Artifact representing the Artifact in the Model.
     */
    @objid ("f7f2af50-644e-11e0-b650-001ec947cd2a")
    Artifact createArtifact(String name, NameSpace owner);

    /**
     * Create an Artifact.
     * The created Artifact has no composition owner. In order to build a valid model, a composition owner must be
     * defined. The returned object is an Artifact representing the Abstraction in the Model.
     * The created Artifact is stereotyped by the <code>stereotype</code> argument. The
     * <code>StereotypeNotFoundException</code> is thrown if the stereotype is not found.
     * 
     * @param name Name of the Artifact
     * @param owner The Owner of the Artifact
     * @param stereotype the stereotype to add on the created Artifact or null if none.
     * @return An Artifact representing the Artifact in the Model.
     */
    @objid ("f7f2af4f-644e-11e0-b650-001ec947cd2a")
    Artifact createArtifact(String name, NameSpace owner, Stereotype stereotype);

    /**
     * Create an Artifact.
     * The created Artifact has no composition owner. In order to build a valid model, a composition owner must be
     * defined. The returned object is an Artifact representing the Abstraction in the Model.
     * The created Artifact is stereotyped by the <code>stereotype</code> argument. The
     * <code>StereotypeNotFoundException</code> is thrown if the stereotype is not found.
     * 
     * @param name Name of the Artifact
     * @param owner The Owner of the Artifact
     * @param moduleName the module owning the Stereotype that will be refered by the Artifact.
     * @param stereotypeName the stereotype to add on the created Artifact or null if none.
     * @return An Artifact representing the Artifact in the Model.
     * @throws org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException if no type matching the given name and the element metaclass is found
     */
    @objid ("f3f1567d-b273-473e-860d-41f12314eae5")
    Artifact createArtifact(String name, NameSpace owner, String moduleName, String stereotypeName) throws ExtensionNotFoundException;

    /**
     * Create a binary association.
     * This method create a binary association between two classifiers and give a name to the destination role. The
     * created association has a navigable role.
     * 
     * @param source the source Classifier of the Association.
     * @param destination the destination Classifier of the Association.
     * @param destinationRole the Name of the destination role.
     * @return An Association representing the Association in the Model.
     */
    @objid ("f7f2af4d-644e-11e0-b650-001ec947cd2a")
    Association createAssociation(Classifier source, Classifier destination, String destinationRole);

    /**
     * Create a binary association.
     * 
     * @return An Association representing the Association in the Model.
     */
    @objid ("813fe811-baec-4136-a206-6da97a28a677")
    Association createAssociation();

    /**
     * Create an AssociationEnd.
     * The created AssociationEnd has no composition owner. In order to build a valid model, a composition owner must be
     * defined. The returned object is an AssociationEnd representing the Abstraction in the Model.
     * 
     * @return An AssociationEnd representing the AssociationEnd in the Model.
     */
    @objid ("f7f2af4c-644e-11e0-b650-001ec947cd2a")
    AssociationEnd createAssociationEnd();

    /**
     * Create an Attribute.
     * The created Attribute has no composition owner. In order to build a valid model, a composition owner must be
     * defined. The returned object is an Attribute representing the Abstraction in the Model.
     * 
     * @return An Attribute representing the Attribute in the Model.
     */
    @objid ("f7f2af4b-644e-11e0-b650-001ec947cd2a")
    Attribute createAttribute();

    /**
     * Create an Attribute.
     * The Attribute is created on owner and has a name, a type.
     * 
     * @param name the name of the Attribute to be created.
     * @param type the GeneralClass that will type the Attribute.
     * @param owner the Classifier that will contain the Attribute
     * @return An Attribute representing the Attribute in the Model.
     */
    @objid ("f7f04d25-644e-11e0-b650-001ec947cd2a")
    Attribute createAttribute(String name, GeneralClass type, Classifier owner);

    /**
     * Create a stereotyped Attribute.
     * The Attribute is created on owner and has a name, a type and a stereotype.
     * 
     * @param name the name of the Attribute to be created.
     * @param type the GeneralClass that will type the Atrribute.
     * @param owner the Classifier that will contain the Attribute.
     * @param stereotype the name of the Stereotype that will be refered by the Attribute.
     * @return An Attribute representing the Attribute in the Model.
     */
    @objid ("f7f2af4a-644e-11e0-b650-001ec947cd2a")
    Attribute createAttribute(String name, GeneralClass type, Classifier owner, Stereotype stereotype);

    /**
     * Create a stereotyped Attribute.
     * The Attribute is created on owner and has a name, a type and a stereotype.
     * 
     * @param name the name of the Attribute to be created.
     * @param type the GeneralClass that will type the Atrribute.
     * @param owner the Classifier that will contain the Attribute.
     * @param moduleName the module owning the Stereotype that will be refered by the Attribute.
     * @param stereotypeName the name of the Stereotype that will be refered by the Attribute.
     * @return An Attribute representing the Attribute in the Model.
     * @throws org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException if no stereotype matching the given name and the element metaclass is found
     */
    @objid ("17860a00-43ab-4c62-8083-b412c76b53ca")
    Attribute createAttribute(String name, GeneralClass type, Classifier owner, String moduleName, String stereotypeName) throws ExtensionNotFoundException;

    /**
     * Create an AttributeLink.
     * The created AttributeLink has no composition owner. In order to build a valid model, a composition owner must be
     * defined. The returned object is an AttributeLink representing the Abstraction in the Model.
     * 
     * @return An AttributeLink representing the AttributeLink in the Model.
     */
    @objid ("f7f2af49-644e-11e0-b650-001ec947cd2a")
    AttributeLink createAttributeLink();

    /**
     * Create a BehaviorParameter.
     * The created BehaviorParameter has no composition owner. In order to build a valid model, a composition owner must
     * be defined. The returned object is an BehaviorParameter representing the BehaviorParameter in the Model.
     * 
     * @return An BehaviorParameter representing the BehaviorParameter in the Model.
     */
    @objid ("f7f2af48-644e-11e0-b650-001ec947cd2a")
    BehaviorParameter createBehaviorParameter();

    /**
     * Create a BindableInstance.
     * The created BindableInstance has no composition owner. In order to build a valid model, a composition owner must
     * be defined. The returned object is an BindableInstance representing the Abstraction in the Model.
     * 
     * @return An BindableInstance representing the BindableInstance in the Model.
     */
    @objid ("f7f2af47-644e-11e0-b650-001ec947cd2a")
    BindableInstance createBindableInstance();

    /**
     * Create a Binding.
     * The created Binding has no composition owner. In order to build a valid model, a composition owner must be
     * defined. The returned object is an Binding representing the Abstraction in the Model.
     * 
     * @return An Binding representing the Binding in the Model.
     */
    @objid ("f7f2af46-644e-11e0-b650-001ec947cd2a")
    Binding createBinding();

    /**
     * Create a BpmnActivity.
     * 
     * @return An BpmnActivity representing the BpmnActivity in the Model.
     */
    @objid ("5ff23978-e396-11e0-889f-002564c97630")
    BpmnActivity createBpmnActivity();

    /**
     * Create a BpmnAdHocSubProcess.
     * 
     * @return An BpmnAdHocSubProcess representing the BpmnAdHocSubProcess in the Model.
     */
    @objid ("5ff28799-e396-11e0-889f-002564c97630")
    BpmnAdHocSubProcess createBpmnAdHocSubProcess();

    /**
     * Create a BpmnAssociation.
     * 
     * @return An BpmnAssociation representing the BpmnAssociation in the Model
     */
    @objid ("6008a7fc-e396-11e0-889f-002564c97630")
    BpmnAssociation createBpmnAssociation();

    /**
     * Create a BpmnBoundaryEvent.
     * 
     * @return An BpmnBoundaryEvent representing the BpmnBoundaryEvent in the Model
     */
    @objid ("5ff8c941-e396-11e0-889f-002564c97630")
    BpmnBoundaryEvent createBpmnBoundaryEvent();

    /**
     * Create a BpmnBusinessRuleTask.
     * 
     * @return An BpmnBusinessRuleTask representing the BpmnBusinessRuleTask in the Model.
     */
    @objid ("5ff2d5ba-e396-11e0-889f-002564c97630")
    BpmnBusinessRuleTask createBpmnBusinessRuleTask();

    /**
     * Create a BpmnCallActivity.
     * 
     * @return An BpmnCallActivity representing the BpmnCallActivity in the Model.
     */
    @objid ("5ff323db-e396-11e0-889f-002564c97630")
    BpmnCallActivity createBpmnCallActivity();

    /**
     * Create a BpmnCancelEventDefinition.
     * 
     * @return An BpmnCancelEventDefinition representing the BpmnCancelEventDefinition in the Model
     */
    @objid ("5ff8f051-e396-11e0-889f-002564c97630")
    BpmnCancelEventDefinition createBpmnCancelEventDefinition();

    /**
     * Create a BpmnCollaboration.
     * 
     * @return An BpmnCollaboration representing the BpmnCollaboration in the Model
     */
    @objid ("60054c90-e396-11e0-889f-002564c97630")
    BpmnCollaboration createBpmnCollaboration();

    /**
     * Create a BpmnCollaborationDiagram.
     * 
     * @return An BpmnCollaborationDiagram representing the BpmnCollaborationDiagram in the Model
     */
    @objid ("5ff7429b-e396-11e0-889f-002564c97630")
    BpmnCollaborationDiagram createBpmnCollaborationDiagram();

    /**
     * Create a BpmnCompensateEventDefinition.
     * 
     * @return An BpmnCompensateEventDefinition representing the BpmnCompensateEventDefinition in the Model
     */
    @objid ("5ff98c94-e396-11e0-889f-002564c97630")
    BpmnCompensateEventDefinition createBpmnCompensateEventDefinition();

    /**
     * Create a BpmnComplexBehaviorDefinition.
     * 
     * @return An BpmnComplexBehaviorDefinition representing the BpmnComplexBehaviorDefinition in the Model.
     */
    @objid ("5ff371fd-e396-11e0-889f-002564c97630")
    BpmnComplexBehaviorDefinition createBpmnComplexBehaviorDefinition();

    /**
     * Create a BpmnComplexGateway.
     * 
     * @return An BpmnComplexGateway representing the BpmnComplexGateway in the Model
     */
    @objid ("6000436d-e396-11e0-889f-002564c97630")
    BpmnComplexGateway createBpmnComplexGateway();

    /**
     * Create a BpmnConditionalEventDefinition.
     * 
     * @return An BpmnConditionalEventDefinition representing the BpmnConditionalEventDefinition in the Model
     */
    @objid ("5ffa01c5-e396-11e0-889f-002564c97630")
    BpmnConditionalEventDefinition createBpmnConditionalEventDefinition();

    /**
     * Create a BpmnDataAssociation.
     * 
     * @return An BpmnDataAssociation representing the BpmnDataAssociation in the Model
     */
    @objid ("6002db86-e396-11e0-889f-002564c97630")
    BpmnDataAssociation createBpmnDataAssociation();

    /**
     * Create a BpmnDataInput.
     * 
     * @return An BpmnDataInput representing the BpmnDataInput in the Model
     */
    @objid ("6004140b-e396-11e0-889f-002564c97630")
    BpmnDataInput createBpmnDataInput();

    /**
     * Create a BpmnDataObject.
     * 
     * @return An BpmnDataObject representing the BpmnDataObject in the Model
     */
    @objid ("60023f44-e396-11e0-889f-002564c97630")
    BpmnDataObject createBpmnDataObject();

    /**
     * Create a BpmnDataOutput.
     * 
     * @return An BpmnDataOutput representing the BpmnDataOutput in the Model
     */
    @objid ("60039ed9-e396-11e0-889f-002564c97630")
    BpmnDataOutput createBpmnDataOutput();

    /**
     * Create a BpmnDataState.
     * 
     * @return An BpmnDataState representing the BpmnDataState in the Model
     */
    @objid ("6004fe6e-e396-11e0-889f-002564c97630")
    BpmnDataState createBpmnDataState();

    /**
     * Create a BpmnDataStore.
     * 
     * @return An BpmnDataStore representing the BpmnDataStore in the Model
     */
    @objid ("60028d65-e396-11e0-889f-002564c97630")
    BpmnDataStore createBpmnDataStore();

    /**
     * Create a BpmnEndEvent.
     * 
     * @return An BpmnEndEvent representing the BpmnEndEvent in the Model
     */
    @objid ("5ffa4fe6-e396-11e0-889f-002564c97630")
    BpmnEndEvent createBpmnEndEvent();

    /**
     * Create a BpmnEndPoint.
     * 
     * @return An BpmnEndPoint representing the BpmnEndPoint in the Model
     */
    @objid ("5ff87b20-e396-11e0-889f-002564c97630")
    BpmnEndPoint createBpmnEndPoint();

    /**
     * Create a BpmnErrorEventDefinition.
     * 
     * @return An BpmnErrorEventDefinition representing the BpmnErrorEventDefinition in the Model
     */
    @objid ("5ffa9e08-e396-11e0-889f-002564c97630")
    BpmnErrorEventDefinition createBpmnErrorEventDefinition();

    /**
     * Create a BpmnEscalationEventDefinition.
     * 
     * @return An BpmnEscalationEventDefinition representing the BpmnEscalationEventDefinition in the Model
     */
    @objid ("5ffaec29-e396-11e0-889f-002564c97630")
    BpmnEscalationEventDefinition createBpmnEscalationEventDefinition();

    /**
     * Create a BpmnEventBasedGateway.
     * 
     * @return An BpmnEventBasedGateway representing the BpmnEventBasedGateway in the Model
     */
    @objid ("6000918e-e396-11e0-889f-002564c97630")
    BpmnEventBasedGateway createBpmnEventBasedGateway();

    /**
     * Create a BpmnExclusiveGateway.
     * 
     * @return An BpmnExclusiveGateway representing the BpmnExclusiveGateway in the Model
     */
    @objid ("6000dfaf-e396-11e0-889f-002564c97630")
    BpmnExclusiveGateway createBpmnExclusiveGateway();

    /**
     * Create a BpmnGroup.
     * 
     * @return An BpmnGroup representing the BpmnGroup in the Model
     */
    @objid ("600a7cc3-e396-11e0-889f-002564c97630")
    BpmnGroup createBpmnGroup();

    /**
     * Create a BpmnImplicitThrowEvent.
     * 
     * @return An BpmnImplicitThrowEvent representing the BpmnImplicitThrowEvent in the Model
     */
    @objid ("5ffbd68c-e396-11e0-889f-002564c97630")
    BpmnImplicitThrowEvent createBpmnImplicitThrowEvent();

    /**
     * Create a BpmnInclusiveGateway.
     * 
     * @return An BpmnInclusiveGateway representing the BpmnInclusiveGateway in the Model
     */
    @objid ("60017bf1-e396-11e0-889f-002564c97630")
    BpmnInclusiveGateway createBpmnInclusiveGateway();

    /**
     * Create a BpmnInterface.
     * 
     * @return An BpmnInterface representing the BpmnInterface in the Model
     */
    @objid ("5ff82cfe-e396-11e0-889f-002564c97630")
    BpmnInterface createBpmnInterface();

    /**
     * Create a BpmnIntermediateCatchEvent.
     * 
     * @return An BpmnIntermediateCatchEvent representing the BpmnIntermediateCatchEvent in the Model
     */
    @objid ("5ffc24ad-e396-11e0-889f-002564c97630")
    BpmnIntermediateCatchEvent createBpmnIntermediateCatchEvent();

    /**
     * Create a BpmnIntermediateThrowEvent.
     * 
     * @return An BpmnIntermediateThrowEvent representing the BpmnIntermediateThrowEvent in the Model
     */
    @objid ("5ffc72ce-e396-11e0-889f-002564c97630")
    BpmnIntermediateThrowEvent createBpmnIntermediateThrowEvent();

    /**
     * Create a BpmnItemDefinition.
     * 
     * @return An BpmnItemDefinition representing the BpmnItemDefinition in the Model
     */
    @objid ("6004b04d-e396-11e0-889f-002564c97630")
    BpmnItemDefinition createBpmnItemDefinition();

    /**
     * Create a BpmnLane.
     * 
     * @return An BpmnLane representing the BpmnLane in the Model
     */
    @objid ("6005c1c1-e396-11e0-889f-002564c97630")
    BpmnLane createBpmnLane();

    /**
     * Create a BpmnLaneSet.
     * 
     * @return An BpmnLaneSet representing the BpmnLaneSet in the Model
     */
    @objid ("60060fe2-e396-11e0-889f-002564c97630")
    BpmnLaneSet createBpmnLaneSet();

    /**
     * Create a BpmnLinkEventDefinition.
     * 
     * @return An BpmnLinkEventDefinition representing the BpmnLinkEventDefinition in the Model
     */
    @objid ("5ffcc0f0-e396-11e0-889f-002564c97630")
    BpmnLinkEventDefinition createBpmnLinkEventDefinition();

    /**
     * Create a BpmnManualTask.
     * 
     * @return An BpmnManualTask representing the BpmnManualTask in the Model.
     */
    @objid ("5ff3e72e-e396-11e0-889f-002564c97630")
    BpmnManualTask createBpmnManualTask();

    /**
     * Create a BpmnMessage.
     * 
     * @return An BpmnMessage representing the BpmnMessage in the Model
     */
    @objid ("5fff31f9-e396-11e0-889f-002564c97630")
    BpmnMessage createBpmnMessage();

    /**
     * Create a BpmnMessageEventDefinition.
     * 
     * @return An BpmnMessageEventDefinition representing the BpmnMessageEventDefinition in the Model
     */
    @objid ("5ffd3621-e396-11e0-889f-002564c97630")
    BpmnMessageEventDefinition createBpmnMessageEventDefinition();

    /**
     * Create a BpmnMessageFlow.
     * 
     * @return An BpmnMessageFlow representing the BpmnMessageFlow in the Model
     */
    @objid ("5fff801a-e396-11e0-889f-002564c97630")
    BpmnMessageFlow createBpmnMessageFlow();

    /**
     * Create a BpmnMultiInstanceLoopCharacteristics.
     * 
     * @return An BpmnMultiInstanceLoopCharacteristics representing the BpmnMultiInstanceLoopCharacteristics in the Model.
     */
    @objid ("5ff4354f-e396-11e0-889f-002564c97630")
    BpmnMultiInstanceLoopCharacteristics createBpmnMultiInstanceLoopCharacteristics();

    /**
     * Create a BpmnOperation.
     * 
     * @return An BpmnOperation representing the BpmnOperation in the Model
     */
    @objid ("5ff7dedd-e396-11e0-889f-002564c97630")
    BpmnOperation createBpmnOperation();

    /**
     * Create a BpmnParallelGateway.
     * 
     * @return An BpmnParallelGateway representing the BpmnParallelGateway in the Model
     */
    @objid ("6001ca12-e396-11e0-889f-002564c97630")
    BpmnParallelGateway createBpmnParallelGateway();

    /**
     * Create a BpmnParticipant.
     * 
     * @return An BpmnParticipant representing the BpmnParticipant in the Model
     */
    @objid ("60065e04-e396-11e0-889f-002564c97630")
    BpmnParticipant createBpmnParticipant();

    /**
     * Create a BpmnProcess.
     * 
     * @return An BpmnProcess representing the BpmnProcess in the Model
     */
    @objid ("6006ac25-e396-11e0-889f-002564c97630")
    BpmnProcess createBpmnProcess();

    /**
     * Create a BpmnProcessCollaborationDiagram.
     * 
     * @return An BpmnProcessCollaborationDiagram representing the BpmnProcessCollaborationDiagram in the Model
     */
    @objid ("abaaa145-325f-4068-95ac-72687ab036b0")
    BpmnProcessDesignDiagram createBpmnProcessDesignDiagram();

    /**
     * Create a BpmnReceiveTask.
     * 
     * @return An BpmnReceiveTask representing the BpmnReceiveTask in the Model
     */
    @objid ("5ff48371-e396-11e0-889f-002564c97630")
    BpmnReceiveTask createBpmnReceiveTask();

    /**
     * Create a BpmnResource.
     * 
     * @return An BpmnResource representing the BpmnResource in the Model
     */
    @objid ("60072156-e396-11e0-889f-002564c97630")
    BpmnResource createBpmnResource();

    /**
     * Create a BpmnResourceParameter.
     * 
     * @return An BpmnResourceParameter representing the BpmnResourceParameter in the Model
     */
    @objid ("60076f78-e396-11e0-889f-002564c97630")
    BpmnResourceParameter createBpmnResourceParameter();

    /**
     * Create a BpmnResourceParameterBinding.
     * 
     * @return An BpmnResourceParameterBinding representing the BpmnResourceParameterBinding in the Model
     */
    @objid ("600859db-e396-11e0-889f-002564c97630")
    BpmnResourceParameterBinding createBpmnResourceParameterBinding();

    /**
     * Create a BpmnResourceRole.
     * 
     * @return An BpmnResourceRole representing the BpmnResourceRole in the Model
     */
    @objid ("6007e4a9-e396-11e0-889f-002564c97630")
    BpmnResourceRole createBpmnResourceRole();

    /**
     * Create a BpmnScriptTask.
     * 
     * @return An BpmnScriptTask representing the BpmnScriptTask in the Model
     */
    @objid ("5ff4d192-e396-11e0-889f-002564c97630")
    BpmnScriptTask createBpmnScriptTask();

    /**
     * Create a BpmnSendTask.
     * 
     * @return An BpmnSendTask representing the BpmnSendTask in the Model
     */
    @objid ("5ff546c3-e396-11e0-889f-002564c97630")
    BpmnSendTask createBpmnSendTask();

    /**
     * Create a BpmnSequenceFlow.
     * 
     * @return An BpmnSequenceFlow representing the BpmnSequenceFlow in the Model
     */
    @objid ("5fffce3b-e396-11e0-889f-002564c97630")
    BpmnSequenceFlow createBpmnSequenceFlow();

    /**
     * Create a BpmnSequenceFlowDataAssociation.
     * 
     * @return An BpmnSequenceFlowDataAssociation representing the BpmnSequenceFlowDataAssociation in the Model
     */
    @objid ("600350b8-e396-11e0-889f-002564c97630")
    BpmnSequenceFlowDataAssociation createBpmnSequenceFlowDataAssociation();

    /**
     * Create a BpmnServiceTask.
     * 
     * @return An BpmnServiceTask representing the BpmnServiceTask in the Model
     */
    @objid ("5ff56dd4-e396-11e0-889f-002564c97630")
    BpmnServiceTask createBpmnServiceTask();

    /**
     * Create a BpmnSharedDefinitions.
     * 
     * @return An BpmnSharedDefinitions representing the BpmnSharedDefinitions in the Model
     * @since 3.7
     */
    @objid ("600b4016-e396-11e0-889f-002564c97630")
    BpmnSharedDefinitions createBpmnSharedDefinitions();

    /**
     * Create a BpmnSignalEventDefinition.
     * 
     * @return An BpmnSignalEventDefinition representing the BpmnSignalEventDefinition in the Model
     */
    @objid ("5ffd8442-e396-11e0-889f-002564c97630")
    BpmnSignalEventDefinition createBpmnSignalEventDefinition();

    /**
     * Create a BpmnStandardLoopCharacteristics.
     * 
     * @return An BpmnStandardLoopCharacteristics representing the BpmnStandardLoopCharacteristics in the Model
     */
    @objid ("5ff5bbf5-e396-11e0-889f-002564c97630")
    BpmnStandardLoopCharacteristics createBpmnStandardLoopCharacteristics();

    /**
     * Create a BpmnStartEvent.
     * 
     * @return An BpmnStartEvent representing the BpmnStartEvent in the Model
     */
    @objid ("5ffdd264-e396-11e0-889f-002564c97630")
    BpmnStartEvent createBpmnStartEvent();

    /**
     * Create a BpmnSubProcess.
     * 
     * @return An BpmnSubProcess representing the BpmnSubProcess in the Model
     */
    @objid ("5ff60a16-e396-11e0-889f-002564c97630")
    BpmnSubProcess createBpmnSubProcess();

    /**
     * Create a BpmnSubProcessDiagram.
     * 
     * @return An BpmnSubProcessDiagram representing the BpmnSubProcessDiagram in the Model
     */
    @objid ("5ff790bc-e396-11e0-889f-002564c97630")
    BpmnSubProcessDiagram createBpmnSubProcessDiagram();

    /**
     * Create a BpmnTask.
     * 
     * @return An BpmnTask representing the BpmnTask in the Model
     */
    @objid ("5ff65838-e396-11e0-889f-002564c97630")
    BpmnTask createBpmnTask();

    /**
     * Create a BpmnTerminateEventDefinition.
     * 
     * @return An BpmnTerminateEventDefinition representing the BpmnTerminateEventDefinition in the Model
     */
    @objid ("5ffe2085-e396-11e0-889f-002564c97630")
    BpmnTerminateEventDefinition createBpmnTerminateEventDefinition();

    /**
     * Create a BpmnTimerEventDefinition.
     * 
     * @return An BpmnTimerEventDefinition representing the BpmnTimerEventDefinition in the Model
     */
    @objid ("5ffee3d8-e396-11e0-889f-002564c97630")
    BpmnTimerEventDefinition createBpmnTimerEventDefinition();

    /**
     * Create a BpmnTransaction.
     * 
     * @return An BpmnTransaction representing the BpmnTransaction in the Model
     */
    @objid ("5ff6a659-e396-11e0-889f-002564c97630")
    BpmnTransaction createBpmnTransaction();

    /**
     * Create a BpmnUserTask.
     * 
     * @return An BpmnUserTask representing the BpmnUserTask in the Model
     */
    @objid ("5ff6f47a-e396-11e0-889f-002564c97630")
    BpmnUserTask createBpmnUserTask();

    /**
     * Create a CallBehaviorAction.
     * The created CallBehaviorAction has no composition owner. In order to build a valid model, a composition owner
     * must be defined. The returned object is an CallBehaviorAction representing the CallBehaviorAction in the Model.
     * 
     * @return An CallBehaviorAction representing the CallBehaviorAction in the Model.
     */
    @objid ("f7f2af45-644e-11e0-b650-001ec947cd2a")
    CallBehaviorAction createCallBehaviorAction();

    /**
     * Create a CallOperationAction.
     * The created CallOperationAction has no composition owner. In order to build a valid model, a composition owner
     * must be defined. The returned object is an CallOperationAction representing the CallOperationAction in the
     * Model.
     * 
     * @return An CallOperationAction representing the CallOperationAction in the Model.
     */
    @objid ("f7f2af44-644e-11e0-b650-001ec947cd2a")
    CallOperationAction createCallOperationAction();

    /**
     * Create a CentralBufferNode.
     * The created CentralBufferNode has no composition owner. In order to build a valid model, a composition owner must
     * be defined. The returned object is an CentralBufferNode representing the CentralBufferNode in the Model.
     * 
     * @return An CentralBufferNode representing the CentralBufferNode in the Model.
     */
    @objid ("f7f04d3c-644e-11e0-b650-001ec947cd2a")
    CentralBufferNode createCentralBufferNode();

    /**
     * Create a ChoicePseudoState.
     * The created ChoicePseudoState has no composition owner. In order to build a valid model, a composition owner must
     * be defined. The returned object is an ChoicePseudoState representing the ChoicePseudoState in the Model.
     * 
     * @return An ChoicePseudoState representing the ChoicePseudoState in the Model.
     */
    @objid ("f7f2af43-644e-11e0-b650-001ec947cd2a")
    ChoicePseudoState createChoicePseudoState();

    /**
     * Create a Class.
     * The created Class has no composition owner. In order to build a valid model, a composition owner must be defined.
     * The returned object is an Class representing the Abstraction in the Model.
     * 
     * @return An Class representing the Class in the Model.
     */
    @objid ("f7f2af42-644e-11e0-b650-001ec947cd2a")
    Class createClass();

    /**
     * Create a class.
     * The class is created on owner and has a name.
     * 
     * @param name the name of the Class to create.
     * @param owner the NameSpace that will contain the Class.
     * @return An Class representing the Class in the Model.
     */
    @objid ("f7f2af41-644e-11e0-b650-001ec947cd2a")
    Class createClass(String name, NameSpace owner);

    /**
     * Create a stereotyped class.
     * The class is created on owner, has a name and owns a Stereotype.
     * 
     * @param name the name of the Class to create.
     * @param owner the NameSpace that will contain the Class.
     * @param stereotype the Stereotype to refer on the class.
     * @return An Class representing the Class in the Model.
     */
    @objid ("f7f04d3b-644e-11e0-b650-001ec947cd2a")
    Class createClass(String name, NameSpace owner, Stereotype stereotype);

    /**
     * Create a stereotyped class.
     * The class is created on owner, has a name and owns a Stereotype.
     * 
     * @param name the name of the Class to create.
     * @param owner the NameSpace that will contain the Class.
     * @param moduleName the module owning the Stereotype that will be refered by the Class.
     * @param stereotypeName the Stereotype to refer on the class.
     * @return An Class representing the Class in the Model.
     * @throws org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException if no stereotype matching the given name and the element metaclass is found
     */
    @objid ("ce9ed345-fb5e-40f3-872d-71ce569b82ee")
    Class createClass(String name, NameSpace owner, String moduleName, String stereotypeName) throws ExtensionNotFoundException;

    /**
     * Create a ClassAssociation.
     * The created ClassAssociation has no composition owner. In order to build a valid model, a composition owner must
     * be defined. The returned object is an ClassAssociation representing the Abstraction in the Model.
     * 
     * @return An ClassAssociation representing the ClassAssociation in the Model.
     */
    @objid ("f7f2af40-644e-11e0-b650-001ec947cd2a")
    ClassAssociation createClassAssociation();

    /**
     * Create a ClassDiagram.
     * The returned object is an ClassDiagram owned by <code>owner</code> named by <code>name</code> and sterotyped by
     * <code>stereotype</code>
     * 
     * @param name the name of the Class Diagram to create.
     * @param owner the element on which the Class Diagram will be created.
     * @param stereotype the name of the stereotype that will be used to type the Class Diagram
     * @return An ClassDiagram representing the diagram in the Model.
     */
    @objid ("ffcd3fac-87a7-11e0-bfd2-002564c97630")
    ClassDiagram createClassDiagram(final String name, final ModelElement owner, final Stereotype stereotype);

    /**
     * Create a Clause.
     * The created Clause has no composition owner. In order to build a valid model, a composition owner must be
     * defined. The returned object is an Clause representing the Clause in the Model.
     * 
     * @return An Clause representing the Clause in the Model.
     */
    @objid ("f7f2af3f-644e-11e0-b650-001ec947cd2a")
    Clause createClause();

    /**
     * Create a Collaboration.
     * The created Collaboration has no composition owner. In order to build a valid model, a composition owner must be
     * defined. The returned object is a Collaboration representing the Collaboration in the Model.
     * 
     * @return A Collaboration representing the Collaboration in the Model.
     */
    @objid ("f7f2af3e-644e-11e0-b650-001ec947cd2a")
    Collaboration createCollaboration();

    /**
     * Create a CollaborationUse.
     * The created CollaborationUse has no composition owner. In order to build a valid model, a composition owner must
     * be defined. The returned object is a CollaborationUse representing the Abstraction in the Model.
     * 
     * @return A CollaborationUse representing the CollaborationUse in the Model.
     */
    @objid ("f7f2af3d-644e-11e0-b650-001ec947cd2a")
    CollaborationUse createCollaborationUse();

    /**
     * Create a CombinedFragment.
     * The created CombinedFragment has no composition owner. In order to build a valid model, a composition owner must
     * be defined. The returned object is a CombinedFragment representing the CombinedFragment in the Model.
     * 
     * @return A CombinedFragment representing the CombinedFragment in the Model.
     */
    @objid ("f7f2af3c-644e-11e0-b650-001ec947cd2a")
    CombinedFragment createCombinedFragment();

    /**
     * Create a CombinedFragment.
     * Create a CombinedFragment with An interactionOperator. In order to build a valid model, the CombinedFragment must
     * be inserted in the model.
     * 
     * @param operator the operator of the CombinedFragment to create.
     * @return A CombinedFragment representing the CombinedFragment in the Model.
     */
    @objid ("f7f2af3b-644e-11e0-b650-001ec947cd2a")
    CombinedFragment createCombinedFragment(InteractionOperator operator);

    /**
     * Create a CommunicationChannel.
     * The created element has no composition owner. In order to build a valid model, a composition owner must be
     * defined.
     * 
     * @return A CommunicationChannel in the Model.
     */
    @objid ("ed6e89f5-e92f-437c-b2bf-447320fa02cf")
    CommunicationChannel createCommunicationChannel();

    /**
     * Create a CommunicationDiagram.
     * The returned object is a CommunicationDiagram owned by <code>owner</code> named by <code>name</code> and sterotyped by
     * <code>stereotype</code>
     * 
     * @param name the name of the Communication Diagram to create.
     * @param owner the element on which the Communication Diagram will be created.
     * @param stereotype the name of the stereotype that will be used to type the Communication Diagram
     * @return A CommunicationDiagram representing the diagram in the Model.
     */
    @objid ("ffcdb4d9-87a7-11e0-bfd2-002564c97630")
    CommunicationDiagram createCommunicationDiagram(final String name, final ModelElement owner, final Stereotype stereotype);

    /**
     * Create a communication interaction.
     * The created CommunicationInteraction has no composition owner. In order to build a valid model, a composition owner must be
     * defined.
     * 
     * @return A CommunicationInteraction representing the CommunicationInteraction in the Model.
     * @since 3.7
     */
    @objid ("5591ea2c-71aa-4c14-aade-d092bf1de6a1")
    CommunicationInteraction createCommunicationInteraction();

    /**
     * Create a CommunicationMessage.
     * The created element has no composition owner. In order to build a valid model, a composition owner must be
     * defined.
     * 
     * @return A CommunicationMessage in the Model.
     */
    @objid ("ef4c6f2d-c7d3-413e-9171-ef9be4543088")
    CommunicationMessage createCommunicationMessage();

    /**
     * Create a CommunicationNode.
     * The created element has no composition owner. In order to build a valid model, a composition owner must be
     * defined.
     * 
     * @return A CommunicationNode in the Model.
     */
    @objid ("978e5148-9952-449a-86eb-7e341bd4b437")
    CommunicationNode createCommunicationNode();

    /**
     * Create a Component.
     * The created Component has no composition owner. In order to build a valid model, a composition owner must be
     * defined. The returned object is a Component representing the Abstraction in the Model.
     * 
     * @return A Component representing the Component in the Model.
     */
    @objid ("f7f2af3a-644e-11e0-b650-001ec947cd2a")
    Component createComponent();

    /**
     * Create a Component.
     * The component is created on owner and has a name.
     * 
     * @param name the name of the Component to create.
     * @param owner the NameSpace that will contain the Component.
     * @return A Component representing the Component in the Model.
     */
    @objid ("f7f2af39-644e-11e0-b650-001ec947cd2a")
    Component createComponent(String name, NameSpace owner);

    /**
     * Create a stereotyped Component.
     * The Component is created on owner, has a name and owns a Stereotype.
     * 
     * @param name the name of the Component to create.
     * @param owner the NameSpace that will contain the Component.
     * @param stereotype the Stereotype to refer on the Component.
     * @return A Component representing the Component in the Model.
     */
    @objid ("f7f2af38-644e-11e0-b650-001ec947cd2a")
    Component createComponent(String name, NameSpace owner, Stereotype stereotype);

    /**
     * Create a stereotyped Component.
     * The Component is created on owner, has a name and owns a Stereotype.
     * 
     * @param name the name of the Component to create.
     * @param owner the NameSpace that will contain the Component.
     * @param moduleName the module owning the Stereotype that will be refered by the Actor.
     * @param stereotypeName the Stereotype that will be refered by the Actor.
     * @return A Component representing the Component in the Model.
     */
    @objid ("05e7fd4c-cdd4-4722-8fc6-268187b9f99f")
    Component createComponent(String name, NameSpace owner, String moduleName, String stereotypeName) throws ExtensionNotFoundException;

    /**
     * Create a CompositeStructureDiagram.
     * The returned object is a CompositeStructureDiagram owned by <code>owner</code> named by <code>name</code> and sterotyped by
     * <code>stereotype</code>
     * @since 2.1.1
     * 
     * @param name the name of the CompositeStructure Diagram to create.
     * @param owner the element on which the Class Diagram will be created.
     * @param stereotype the name of the stereotype that will be used to type the Class Diagram
     * @return A CompositeStructureDiagram representing the diagram in the Model.
     */
    @objid ("0c995aa6-5968-11e1-8a5c-002564c97630")
    CompositeStructureDiagram createCompositeStructureDiagram(final String name, final ModelElement owner, final Stereotype stereotype);

    /**
     * Create a ConditionalNode.
     * The created ConditionalNode has no composition owner. In order to build a valid model, a composition owner must
     * be defined. The returned object is a ConditionalNode representing the ConditionalNode in the Model.
     * 
     * @return A ConditionalNode representing the ConditionalNode in the Model.
     */
    @objid ("f7f2af37-644e-11e0-b650-001ec947cd2a")
    ConditionalNode createConditionalNode();

    /**
     * Create a ConnectionPointReference.
     * The created ConnectionPointReference has no composition owner. In order to build a valid model, a composition
     * owner must be defined. The returned object is a ConnectionPointReference representing the
     * ConnectionPointReference in the Model.
     * 
     * @return A ConnectionPointReference representing the ConnectionPointReference in the Model.
     */
    @objid ("f7f2af36-644e-11e0-b650-001ec947cd2a")
    ConnectionPointReference createConnectionPointReference();

    /**
     * Create a connector between two bindable instances.
     * This method create a binary connector between two instances and give a name to the destination role. The created
     * connector has a navigable role.
     * 
     * @param source the source instance of the Connector.
     * @param destination the destination instance of the Connector.
     * @param destinationRole the Name of the destination role.
     * @return A ConnectorEnd representing the Connector in the Model.
     */
    @objid ("ad0de2a3-1217-11e2-bfed-002564c97630")
    Connector createConnector(BindableInstance source, BindableInstance destination, String destinationRole);

    /**
     * Create a Connector.
     * The created element has no composition owner. In order to build a valid model, a composition owner must be
     * defined.
     * 
     * @return A Connector in the Model.
     */
    @objid ("0608d8e9-f410-42fd-948c-5f7c3187c6c0")
    Connector createConnector();

    /**
     * Create a ConnectorEnd.
     * The created ConnectorEnd has no composition owner. In order to build a valid model, a composition owner must be
     * defined. The returned object is a ConnectorEnd representing the ConnectorEnd in the Model.
     * 
     * @return A ConnectorEnd representing the ConnectorEnd in the Model.
     */
    @objid ("f7f2af33-644e-11e0-b650-001ec947cd2a")
    ConnectorEnd createConnectorEnd();

    /**
     * Create a Constraint.
     * The created Constraint has no composition owner. In order to build a valid model, a composition owner must be
     * defined. The returned object is a Constraint representing the Constraint in the Model.
     * 
     * @return A Constraint representing the Constraint in the Model.
     */
    @objid ("f7f2af32-644e-11e0-b650-001ec947cd2a")
    Constraint createConstraint();

    /**
     * Create a ControlFlow.
     * The created ControlFlow has no composition owner. In order to build a valid model, a composition owner must be
     * defined. The returned object is a ControlFlow representing the ControlFlow in the Model.
     * 
     * @return A ControlFlow representing the ControlFlow in the Model.
     */
    @objid ("f7f2af31-644e-11e0-b650-001ec947cd2a")
    ControlFlow createControlFlow();

    /**
     * Create a DataFlow.
     * The created DataFlow has no composition owner. In order to build a valid model, a composition owner must be
     * defined. The returned object is an DataFlow representing the DataFlow in the Model.
     * 
     * @return An DataFlow representing the DataFlow in the Model.
     */
    @objid ("f7f2af30-644e-11e0-b650-001ec947cd2a")
    DataFlow createDataFlow();

    /**
     * Create a DataStoreNode.
     * The created DataStoreNode has no composition owner. In order to build a valid model, a composition owner must be
     * defined. The returned object is an DataStoreNode representing the DataStoreNode in the Model.
     * 
     * @return An DataStoreNode representing the DataStoreNode in the Model.
     */
    @objid ("f7f2af2f-644e-11e0-b650-001ec947cd2a")
    DataStoreNode createDataStoreNode();

    /**
     * Create a DataType.
     * The created DataType has no composition owner. In order to build a valid model, a composition owner must be
     * defined. The returned object is an DataType representing the Abstraction in the Model.
     * 
     * @return An DataType representing the DataType in the Model.
     */
    @objid ("f7f2af2e-644e-11e0-b650-001ec947cd2a")
    DataType createDataType();

    /**
     * Create a DataType.
     * The created DataType has a composition owner and a name.
     * 
     * @param name the name of the DataType to create.
     * @param owner the NameSpace that will contain the DataType.
     * @return An DataType representing the DataType in the Model.
     */
    @objid ("f7f2af2d-644e-11e0-b650-001ec947cd2a")
    DataType createDataType(String name, NameSpace owner);

    /**
     * Create a DataType.
     * The created DataType has a composition owner, a name and refers a Stereotype.
     * 
     * @param name the name of the DataType to create.
     * @param owner the NameSpace that will contain the DataType.
     * @param stereotype the stereotype that will extend the DataType.
     * @return An DataType representing the DataType in the Model.
     */
    @objid ("f7f2af2c-644e-11e0-b650-001ec947cd2a")
    DataType createDataType(String name, NameSpace owner, Stereotype stereotype);

    @objid ("0b410214-af9c-4d33-a28d-1240bef3a3b5")
    DataType createDataType(String name, NameSpace owner, String moduleName, String stereotypeName) throws ExtensionNotFoundException;

    /**
     * Create a DecisionMergeNode.
     * The created DecisionMergeNode has no composition owner. In order to build a valid model, a composition owner must
     * be defined. The returned object is an DecisionMergeNode representing the DecisionMergeNode in the Model.
     * 
     * @return An DecisionMergeNode representing the DecisionMergeNode in the Model.
     */
    @objid ("f7f2af2b-644e-11e0-b650-001ec947cd2a")
    DecisionMergeNode createDecisionMergeNode();

    /**
     * Create a DeepHistoryPseudoState.
     * The created DeepHistoryPseudoState has no composition owner. In order to build a valid model, a composition owner
     * must be defined. The returned object is an DeepHistoryPseudoState representing the DeepHistoryPseudoState in the
     * Model.
     * 
     * @return An DeepHistoryPseudoState representing the DeepHistoryPseudoState in the Model.
     */
    @objid ("f7f2af2a-644e-11e0-b650-001ec947cd2a")
    DeepHistoryPseudoState createDeepHistoryPseudoState();

    /**
     * Create a Dependency.
     * The created Dependency has no composition owner. In order to build a valid model, a composition owner must be
     * defined. The returned object is an Dependency representing the Abstraction in the Model.
     * 
     * @return An Dependency representing the Dependency in the Model.
     */
    @objid ("f7f2af29-644e-11e0-b650-001ec947cd2a")
    Dependency createDependency();

    /**
     * Create a Dependency between two ModelElements.<br>
     * 
     * @param source the origin of the dependency.
     * @param destination the destination of the dependency.
     * @param stereotype the Stereotype that extends the dependency.
     * @return An Dependency representing the Dependency in the Model.
     */
    @objid ("f7f2af28-644e-11e0-b650-001ec947cd2a")
    Dependency createDependency(ModelElement source, ModelElement destination, Stereotype stereotype);

    @objid ("e3d1a098-a654-4231-9366-09287d202a2c")
    Dependency createDependency(ModelElement source, ModelElement destination, String moduleName, String stereotypeName) throws ExtensionNotFoundException;

    /**
     * Create a DeploymentDiagram.
     * The returned object is an DeploymentDiagram owned by <code>owner</code> named by <code>name</code> and sterotyped by
     * <code>stereotype</code>
     * 
     * @param name the name of the Deployment Diagram to create.
     * @param owner the element on which the Deployment Diagram will be created.
     * @param stereotype the name of the stereotype that will be used to type the Deployment Diagram
     * @return An DeploymentDiagram representing the diagram in the Model.
     */
    @objid ("ffce02f7-87a7-11e0-bfd2-002564c97630")
    DeploymentDiagram createDeploymentDiagram(final String name, final ModelElement owner, final Stereotype stereotype);

    /**
     * Create a DiagramSet.
     * The created DiagramSet has no composition owner. In order to build a valid model, a composition owner must be defined. The returned object is an DiagramSet representing the DiagramSet in the Model.
     * 
     * @return An UseCase representing the UseCase in the Model.
     * @since 2.1.1
     */
    @objid ("4ce4b01f-4d97-11e1-9298-002564c97630")
    DiagramSet createDiagramSet();

    /**
     * Create a non initialized {@link Document}.
     * <p>
     * Call {@link Document#createEmbeddedResource(String)} or {@link Document#createExternalResource(String)} to initialize the Document content.
     * The created Document has no composition owner. In order to build a valid model, a composition owner must be defined.
     * The returned object is a Document representing the Document in the Model.
     * 
     * @return An ExternDocument representing the Document in the Model.
     * @since 3.7
     */
    @objid ("457825a3-75c7-4e62-b442-fba3b72be866")
    Document createDocument();

    /**
     * Create a DurationConstraint.
     * The created DurationConstraint has no composition owner. In order to build a valid model, a composition owner
     * must be defined. The returned object is an DurationConstraint representing the DurationConstraint in the Model.
     * 
     * @return An DurationConstraint representing the DurationConstraint in the Model.
     */
    @objid ("f7f2af27-644e-11e0-b650-001ec947cd2a")
    DurationConstraint createDurationConstraint();

    /**
     * Create an element from its metaclass name, and attach it to a parent using a specific relation.
     * 
     * @param metaclassName the metaclass name.
     * @param parentElement the composition owned of the new element.
     * @param relation the name of the relation between the new element and its owner.
     * @return An element representing this metaclass in the Model.
     */
    @objid ("052d02e3-7242-11e0-89ea-002564c97630")
    MObject createElement(final String metaclassName, final Element parentElement, final String relation);

    /**
     * Create an element from its metaclass name.
     * 
     * @param metaclassName the metaclass name.
     * @return An element representing this metaclass in the Model.
     */
    @objid ("052e89a3-7242-11e0-89ea-002564c97630")
    MObject createElement(final String metaclassName);

    /**
     * Create an ElementImport.
     * The created ElementImport has no composition owner. In order to build a valid model, a composition owner must be
     * defined. The returned object is an ElementImport representing the Abstraction in the Model.
     * 
     * @return An ElementImport representing the ElementImport in the Model.
     */
    @objid ("f7f2af26-644e-11e0-b650-001ec947cd2a")
    ElementImport createElementImport();

    /**
     * Create an ElementImport between two NameSpaces.
     * 
     * @param source origin of the ElementImport
     * @param destination destination of the ElementImport
     * @return An ElementImport representing the ElementImport in the Model.
     */
    @objid ("f7f2af25-644e-11e0-b650-001ec947cd2a")
    ElementImport createElementImport(NameSpace source, NameSpace destination);

    /**
     * Create an ElementImport between an Operation and a NameSpace.
     * 
     * @param source origin of the ElementImport
     * @param destination destination of the ElementImport
     * @return An ElementImport representing the ElementImport in the Model.
     */
    @objid ("f7f2af24-644e-11e0-b650-001ec947cd2a")
    ElementImport createElementImport(Operation source, NameSpace destination);

    /**
     * Create an ElementRealization.
     * The created ElementRealization has no composition owner. In order to build a valid model, a composition owner
     * must be defined. The returned object is an ElementRealization representing the Abstraction in the Model.
     * 
     * @return An ElementRealization representing the ElementRealization in the Model.
     */
    @objid ("f7f2af23-644e-11e0-b650-001ec947cd2a")
    ElementRealization createElementRealization();

    /**
     * Creates an embedded document.
     * @since 3.7
     * 
     * @param type the {@link ResourceType document role} of the document.
     * @param owner the composition owner of the rich note.
     * @param mimeType the MIME type of the document.
     * @return A {@link Document} representing the document in the model.
     * @throws java.io.IOException in case of failure creating the file.
     */
    @objid ("686b9bac-cc2d-4068-8b49-12ee8cb6c784")
    Document createEmbeddedDocument(ResourceType type, ModelElement owner, String mimeType) throws IOException;

    /**
     * Creates an embedded document from an existing file.
     * <p>
     * The file content will be copied and embedded into the Modelio model. The passed file itself will never be used after this operation.
     * <p>
     * @since 3.7
     * 
     * @param moduleName the name of the module owning the {@link ResourceType document role}.
     * @param documentRole the name of the {@link ResourceType document role} played by the document.
     * @param owner the composition owner of the document.
     * @param mimeType the MIME type of the document.
     * @return An {@link Document} representing the document in the model.
     * @throws org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException if no type matching the given name and the element metaclass is found
     * @throws java.io.IOException in case of failure creating the file.
     */
    @objid ("7d9b8743-55ae-4f2d-84a8-1f7d22def4d4")
    Document createEmbeddedDocument(String moduleName, String documentRole, ModelElement owner, String mimeType, Path initialContent) throws ExtensionNotFoundException, IOException;

    /**
     * Creates an empty embedded document.
     * <p>
     * Call {@link Document#createEmbeddedResource(String)} to initialize the {@link Document} content.
     * @since 3.7
     * 
     * @param moduleName the name of the module owning the {@link ResourceType document role}.
     * @param documentRole the {@link ResourceType document role} played by the document.
     * @param owner the composition owner of the document.
     * @param mimeType the MIME type of the document.
     * @return An {@link Document} representing the document in the model.
     * @throws org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException if no {@link ResourceType document role} matching the given name and the element metaclass is found
     * @throws java.io.IOException in case of failure creating the file.
     */
    @objid ("a138e126-6ff0-4799-bb55-6421df85044b")
    Document createEmbeddedDocument(String moduleName, String documentRole, ModelElement owner, String mimeType) throws ExtensionNotFoundException, IOException;

    /**
     * Create a EntryPointPseudoState.
     * The created EntryPointPseudoState has no composition owner. In order to build a valid model, a composition owner
     * must be defined. The returned object is an EntryPointPseudoState representing the EntryPointPseudoState in the
     * Model.
     * 
     * @return An EntryPointPseudoState representing the EntryPointPseudoState in the Model.
     */
    @objid ("f7f04d3e-644e-11e0-b650-001ec947cd2a")
    EntryPointPseudoState createEntryPointPseudoState();

    @objid ("9761d3a7-348d-11e2-b056-002564c97630")
    EnumeratedPropertyType createEnumeratedPropertyType();

    /**
     * Create an Enumeration.
     * The created Enumeration has no composition owner. In order to build a valid model, a composition owner must be
     * defined. The returned object is an Enumeration representing the Abstraction in the Model.
     * 
     * @return An Enumeration representing the Enumeration in the Model.
     */
    @objid ("f7f2af22-644e-11e0-b650-001ec947cd2a")
    Enumeration createEnumeration();

    /**
     * Create an Enumeration.
     * 
     * @param name the name of the Enumeration to create.
     * @param owner the NameSpace that will contain the Enumeration
     * @return An Enumeration representing the Enumeration in the Model.
     */
    @objid ("f7f2af21-644e-11e0-b650-001ec947cd2a")
    Enumeration createEnumeration(String name, NameSpace owner);

    /**
     * Create an stereotyped Enumeration.
     * 
     * @param name the name of the Enumeration to create.
     * @param owner the NameSpace that will contain the Enumeration.
     * @param stereotype tthe Stereotype that will extend the Enumeration.
     * @return An Enumeration representing the Enumeration in the Model.
     */
    @objid ("f7f2af20-644e-11e0-b650-001ec947cd2a")
    Enumeration createEnumeration(String name, NameSpace owner, Stereotype stereotype);

    @objid ("cf1a65f8-5c95-49fd-b874-c87340bbc400")
    Enumeration createEnumeration(String name, NameSpace owner, String moduleName, String stereotypeName) throws ExtensionNotFoundException;

    /**
     * Create an EnumerationLiteral.
     * The created EnumerationLiteral has no composition owner. In order to build a valid model, a composition owner
     * must be defined. The returned object is an EnumerationLiteral representing the Abstraction in the Model.
     * 
     * @return An EnumerationLiteral representing the EnumerationLiteral in the Model.
     */
    @objid ("f7f2af1f-644e-11e0-b650-001ec947cd2a")
    EnumerationLiteral createEnumerationLiteral();

    /**
     * Create an EnumerationLiteral.
     * 
     * @param name the name of the EnumerationLiteral to create.
     * @param owner the Enumeration that will contain the EnumerationLiteral
     * @return An Enumeration representing the EnumerationLiteral in the Model.
     */
    @objid ("f7f2af1e-644e-11e0-b650-001ec947cd2a")
    EnumerationLiteral createEnumerationLiteral(String name, Enumeration owner);

    /**
     * Create an stereotyped EnumerationLiteral.
     * 
     * @param name the name of the EnumerationLiteral to create.
     * @param owner the Enumeration that will contain the EnumerationLiteral.
     * @param stereotype the Stereotype that will extend the EnumerationLiteral.
     * @return An Enumeration representing the Enumeration in the Model.
     */
    @objid ("f7f2af1d-644e-11e0-b650-001ec947cd2a")
    EnumerationLiteral createEnumerationLiteral(String name, Enumeration owner, Stereotype stereotype);

    @objid ("325a2b78-1608-417d-b112-40195429603e")
    EnumerationLiteral createEnumerationLiteral(String name, Enumeration owner, String moduleName, String stereotypeName) throws ExtensionNotFoundException;

    /**
     * Create an Event.
     * The created Event has no composition owner. In order to build a valid model, a composition owner must be defined.
     * The returned object is an Event representing the Abstraction in the Model.
     * 
     * @return An Event representing the Event in the Model.
     */
    @objid ("f7f2af1c-644e-11e0-b650-001ec947cd2a")
    Event createEvent();

    /**
     * Create an ExceptionHandler.
     * 
     * @return An ExceptionHandler representing the ExceptionHandler in the Model.
     */
    @objid ("5ff21267-e396-11e0-889f-002564c97630")
    ExceptionHandler createExceptionHandler();

    /**
     * Create a ExecutionOccurenceSpecification.
     * The created ExecutionOccurenceSpecification has no composition owner. In order to build a valid model, a
     * composition owner must be defined. The returned object is an ExecutionOccurenceSpecification representing the
     * ExecutionOccurenceSpecification in the Model.
     * 
     * @return An ExecutionOccurenceSpecification representing the ExecutionOccurenceSpecification in the Model.
     */
    @objid ("f7f04d22-644e-11e0-b650-001ec947cd2a")
    ExecutionOccurenceSpecification createExecutionOccurenceSpecification();

    /**
     * Create a ExecutionSpecification.
     * The created ExecutionOccurenceSpecification has no composition owner. In order to build a valid model, a
     * composition owner must be defined. The returned object is an ExecutionSpecification representing the
     * ExecutionSpecification in the Model.
     * 
     * @return An ExecutionOccurenceSpecification representing the ExecutionOccurenceSpecification in the Model.
     */
    @objid ("f7f2af1b-644e-11e0-b650-001ec947cd2a")
    ExecutionSpecification createExecutionSpecification();

    /**
     * Create a ExitPointPseudoState.
     * The created ExitPointPseudoState has no composition owner. In order to build a valid model, a composition owner
     * must be defined. The returned object is an ExitPointPseudoState representing the ExitPointPseudoState in the
     * Model.
     * 
     * @return An ObExitpointPseudoState representing the ExitpointPseudoState in the Model.
     */
    @objid ("f7f2af1a-644e-11e0-b650-001ec947cd2a")
    ExitPointPseudoState createExitPointPseudoState();

    /**
     * Create an ExpansionNode.
     * 
     * @return An ExpansionNode representing the ExpansionNode in the Model.
     */
    @objid ("5ff17625-e396-11e0-889f-002564c97630")
    ExpansionNode createExpansionNode();

    /**
     * Create an ExpansionRegion.
     * 
     * @return An ExpansionRegion representing the ExpansionRegion in the Model.
     */
    @objid ("5ff1c446-e396-11e0-889f-002564c97630")
    ExpansionRegion createExpansionRegion();

    /**
     * Create an extend UseCaseDependency between two UseCases.
     * 
     * @param source the origin of the UseCaseDependency
     * @param destination the destination of the UseCaseDependency
     * @return An UseCaseDependency representing the UseCaseDependency in the Model.
     * @throws org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException if no stereotype matching the given name and the element metaclass is found
     */
    @objid ("f7f2af19-644e-11e0-b650-001ec947cd2a")
    UseCaseDependency createExtendUseCaseDependency(UseCase source, UseCase destination) throws ExtensionNotFoundException;

    /**
     * Create a ExtensionPoint.
     * The created ExtensionPoint has no composition owner. In order to build a valid model, a composition owner must be
     * defined. The returned object is an ExtensionPoint representing the ExtensionPoint in the Model.
     * 
     * @return An ExtensionPoint representing the ExtensionPoint in the Model.
     */
    @objid ("f7f2af18-644e-11e0-b650-001ec947cd2a")
    ExtensionPoint createExtensionPoint();

    /**
     * Create a non initialized {@link Document}.
     * <p>
     * Call {@link Document#createEmbeddedResource(String)} to initialize the Document content.
     * The created Document has no composition owner. In order to build a valid model, a composition owner must be defined.
     * The returned object is a Document representing the Document in the Model.
     * 
     * @return An ExternDocument representing the Document in the Model.
     * @since 2.1
     * @deprecated Since 3.7, Renamed {@link #createDocument()}
     */
    @objid ("12750649-1fe7-11e1-938e-002564c97630")
    @Deprecated
    Document createExternDocument();

    /**
     * Creates an empty embedded document.
     * <p>
     * Call {@link Document#createEmbeddedResource(String)} to initialize the document content.
     * @since 2.1
     * 
     * @param moduleName the name of the module owning the note type.
     * @param documentRole the role played by the document.
     * @param owner the composition owner of the document.
     * @param mimeType the MIME type of the document.
     * @return An {@link Document} representing the document in the model.
     * @throws java.io.IOException in case of failure creating the file.
     * @throws org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException if no type matching the given name and the element metaclass is found
     * @deprecated Since 3.7, Renamed {@link #createEmbeddedDocument(String, String, ModelElement, String)}
     */
    @objid ("1275546b-1fe7-11e1-938e-002564c97630")
    @Deprecated
    Document createExternDocument(String moduleName, final String documentRole, final ModelElement owner, final String mimeType) throws IOException, ExtensionNotFoundException;

    /**
     * Creates an empty embedded document.
     * <p>
     * Call {@link Document#createEmbeddedResource(String)} to initialize the document content.
     * @since 2.1
     * 
     * @param role {@link ResourceType document role} played by the document.
     * @param owner the composition owner of the document.
     * @param mimeType the MIME type of the document.
     * @return A {@link Document} representing the document in the model.
     * @throws java.io.IOException in case of failure creating the file.
     * @deprecated Since 3.7, Use {@link #createEmbeddedDocument(String, String, ModelElement, String, Path)}.
     */
    @objid ("66cff0ff-3cbd-47f8-9a42-81b1c8fc2171")
    @Deprecated
    Document createExternDocument(final ResourceType role, final ModelElement owner, final String mimeType) throws IOException;

    /**
     * @deprecated Since 3.7, Use {@link #createEmbeddedDocument(String, String, ModelElement, String, Path)}.
     */
    @objid ("c741d8fa-976a-4b07-82be-732a16638891")
    @Deprecated
    Document createExternDocument(String moduleName, String documentRole, ModelElement owner, String mimeType, Path initialContent) throws IOException, ExtensionNotFoundException;

    /**
     * Create a ExternProcessor.
     * 
     * @return a new ExternProcessor instance.
     * @since 3.1.0
     */
    @objid ("05ddfe9c-6760-4ece-a4d8-0e73622df6ea")
    ExternProcessor createExternProcessor();

    /**
     * Create an ExternProcessor, bound to the implementation class from the specified module.
     * 
     * @param implementationClassName the complete name of the implementation class.
     * @param moduleName the module implementing the class. <code>null</code> for standard implementations defined by Modelio itself.
     * @return a new ExternProcessor instance.
     * @since 3.1.0
     */
    @objid ("8bf08602-4114-4b49-9f88-648e8d0f79fe")
    ExternProcessor createExternProcessor(String implementationClassName, String moduleName);

    /**
     * Create a FinalState.
     * The created FinalState has no composition owner. In order to build a valid model, a composition owner must be
     * defined. The returned object is an FinalState representing the FinalState in the Model.
     * 
     * @return An FinalState representing the FinalState in the Model.
     */
    @objid ("f7f2af17-644e-11e0-b650-001ec947cd2a")
    FinalState createFinalState();

    /**
     * Create a FlowFinalNode.
     * The created FlowFinalNode has no composition owner. In order to build a valid model, a composition owner must be
     * defined. The returned object is an FlowFinalNode representing the FlowFinalNode in the Model.
     * 
     * @return An FlowFinalNode representing the FlowFinalNode in the Model.
     */
    @objid ("f7f2af16-644e-11e0-b650-001ec947cd2a")
    FlowFinalNode createFlowFinalNode();

    /**
     * Create a ForkJoinNode.
     * The created ForkJoinNode has no composition owner. In order to build a valid model, a composition owner must be
     * defined. The returned object is an ForkJoinNode representing the ForkJoinNode in the Model.
     * 
     * @return An ForkJoinNode representing the ForkJoinNode in the Model.
     */
    @objid ("f7f2af15-644e-11e0-b650-001ec947cd2a")
    ForkJoinNode createForkJoinNode();

    /**
     * Create a ForkPseudoState.
     * The created ForkPseudoState has no composition owner. In order to build a valid model, a composition owner must
     * be defined. The returned object is an ForkPseudoState representing the ForkPseudoState in the Model.
     * 
     * @return An ForkPseudoState representing the ForkPseudoState in the Model.
     */
    @objid ("f7f04d32-644e-11e0-b650-001ec947cd2a")
    ForkPseudoState createForkPseudoState();

    /**
     * Create a Gate.
     * The created Gate has no composition owner. In order to build a valid model, a composition owner must be defined.
     * The returned object is an Gate representing the Gate in the Model.
     * 
     * @return An Gate representing the Gate in the Model.
     */
    @objid ("f7f2af14-644e-11e0-b650-001ec947cd2a")
    Gate createGate();

    /**
     * Create a Gate.
     * The Gate is created with a name. In order to build a valid model, the Gate must be inserted in the model. must be
     * defined.
     * 
     * @param name The name of the gate.
     * @return An Gate representing the Gate in the Model.
     */
    @objid ("f7f2af13-644e-11e0-b650-001ec947cd2a")
    Gate createGate(String name);

    /**
     * Create a GeneralOrdering.
     * The created GeneralOrdering has no composition owner. In order to build a valid model, a composition owner must
     * be defined. The returned object is an GeneralOrdering representing the GeneralOrdering in the Model.
     * 
     * @return An GeneralOrdering representing the GeneralOrdering in the Model.
     */
    @objid ("f7f04d31-644e-11e0-b650-001ec947cd2a")
    GeneralOrdering createGeneralOrdering();

    /**
     * Create a Generalization.
     * The created Generalization has no composition owner. In order to build a valid model, a composition owner must be
     * defined. The returned object is an Generalization representing the Abstraction in the Model.
     * 
     * @return An Generalization representing the Generalization in the Model.
     */
    @objid ("f7f04d30-644e-11e0-b650-001ec947cd2a")
    Generalization createGeneralization();

    /**
     * Create a Generalization between two NameSpaces.
     * 
     * @param source the child element of the Generaliartion
     * @param destination the parent element of the Generaliartion
     * @return An Generalization representing the Generalization in the Model.
     */
    @objid ("f7f2af12-644e-11e0-b650-001ec947cd2a")
    Generalization createGeneralization(NameSpace source, NameSpace destination);

    /**
     * Create an OParameter.
     * 
     * @param name the name of the Parameter to create.
     * @param type the type of the Parameter to create.
     * @param owner the Operation that will contain the Parameter.
     * @return An Parameter representing the Parameter in the Model.
     */
    @objid ("f7f2af11-644e-11e0-b650-001ec947cd2a")
    Parameter createIOParameter(String name, GeneralClass type, Operation owner);

    /**
     * Create a stereotyped IOParameter.
     * 
     * @param name the name of the Parameter to create.
     * @param type the type of the Parameter to create.
     * @param owner the Operation that will contain the Parameter.
     * @param stereotype the stereotype that will extend the Parameter.
     * @return An Parameter representing the Parameter in the Model.
     */
    @objid ("f7f2af10-644e-11e0-b650-001ec947cd2a")
    Parameter createIOParameter(String name, GeneralClass type, Operation owner, Stereotype stereotype);

    @objid ("6e796f98-3862-4de1-ba46-0ee11af64a89")
    Parameter createIOParameter(String name, GeneralClass type, Operation owner, String moduleName, String stereotypeName) throws ExtensionNotFoundException;

    /**
     * Create an nclude UseCaseDependency between two NameSpaces.
     * 
     * @param source the origin element of the UseCaseDependency
     * @param destination the destination element of the UseCaseDependency
     * @return An UseCaseDependency representing the UseCaseDependency in the Model.
     * @throws org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException if there is no stereotype matching the name and the metaclass
     */
    @objid ("f7f2af0f-644e-11e0-b650-001ec947cd2a")
    UseCaseDependency createIncludeUseCaseDependency(UseCase source, UseCase destination) throws ExtensionNotFoundException;

    /**
     * Create a InformationFlow.
     * The created InformationFlow has no composition owner. In order to build a valid model, a composition owner must
     * be defined. The returned object is an InformationFlow representing the InformationFlow in the Model.
     * 
     * @return An InformationFlow representing the InformationFlow in the Model.
     */
    @objid ("f7f2af0e-644e-11e0-b650-001ec947cd2a")
    InformationFlow createInformationFlow();

    /**
     * Create a InformationItem.
     * The created InformationItem has no composition owner. In order to build a valid model, a composition owner must
     * be defined. The returned object is an InformationItem representing the InformationItem in the Model.
     * 
     * @return An InformationItem representing the InformationItem in the Model.
     */
    @objid ("f7f2af0d-644e-11e0-b650-001ec947cd2a")
    InformationItem createInformationItem();

    /**
     * Create a InitialNode.
     * The created InitialNode has no composition owner. In order to build a valid model, a composition owner must be
     * defined. The returned object is an InitialNode representing the InitialNode in the Model.
     * 
     * @return An InitialNode representing the InitialNode in the Model.
     */
    @objid ("f7f2af0c-644e-11e0-b650-001ec947cd2a")
    InitialNode createInitialNode();

    /**
     * Create a InitialPseudoState.
     * The created InitialPseudoState has no composition owner. In order to build a valid model, a composition owner
     * must be defined. The returned object is an InitialPseudoState representing the InitialPseudoState in the Model.
     * 
     * @return An InitialPseudoState representing the InitialPseudoState in the Model.
     */
    @objid ("f7f2af0b-644e-11e0-b650-001ec947cd2a")
    InitialPseudoState createInitialPseudoState();

    /**
     * Create a InputPin.
     * The created InputPin has no composition owner. In order to build a valid model, a composition owner must be
     * defined. The returned object is an InputPin representing the InputPin in the Model.
     * 
     * @return An InputPin representing the InputPin in the Model.
     */
    @objid ("f7f2af0a-644e-11e0-b650-001ec947cd2a")
    InputPin createInputPin();

    /**
     * Create an instance.
     * The created Instance has no composition owner. In order to build a valid model, a composition owner must be
     * defined. The returned object is an instance representing the Instance in the Model.
     * 
     * @return An instance representing the Instance in the Model.
     */
    @objid ("f7f2af09-644e-11e0-b650-001ec947cd2a")
    Instance createInstance();

    /**
     * Create an instance.
     * The Instance is created with the specified name and owner Package.
     * 
     * @param name the name of the Instance to create.
     * @param owner the Package that will conbtain the Port.
     * @return An instance representing the Instance in the Model.
     */
    @objid ("f7f2af08-644e-11e0-b650-001ec947cd2a")
    Instance createInstance(String name, Package owner);

    /**
     * Create an instanceNode.
     * The created InstanceNode has no composition owner. In order to build a valid model, a composition owner must be
     * defined. The returned object is an InstanceNode representing the InstanceNode in the Model.
     * 
     * @return An InstanceNode representing the InstanceNode in the Model.
     */
    @objid ("f7f04d3f-644e-11e0-b650-001ec947cd2a")
    InstanceNode createInstanceNode();

    /**
     * Create interaction.
     * The created Interaction has no composition owner. In order to build a valid model, a composition owner must be
     * defined. The returned object is an Interaction representing the Abstraction in the Model.
     * 
     * @return An Interaction representing the Interaction in the Model.
     */
    @objid ("f7f2af07-644e-11e0-b650-001ec947cd2a")
    Interaction createInteraction();

    /**
     * Create an interactionOperand.
     * The created InteractionOperand has no composition owner. In order to build a valid model, a composition owner
     * must be defined. The returned object is an InteractionOperand representing the InteractionOperand in the Model.
     * 
     * @return An InteractionOperand representing the InteractionOperand in the Model.
     */
    @objid ("f7f2af06-644e-11e0-b650-001ec947cd2a")
    InteractionOperand createInteractionOperand();

    /**
     * Create an interactionOperand.
     * The created InteractionOperand has a guard condition. In order to build a valid model, the InteractionOperand
     * must be inserted in the model.
     * 
     * @param guard the guard condition of the InteractionOperand to create.
     * @return An InteractionOperand representing the InteractionOperand in the Model.
     */
    @objid ("f7f2af05-644e-11e0-b650-001ec947cd2a")
    InteractionOperand createInteractionOperand(String guard);

    /**
     * Create an interactionUse.
     * The created InteractionUse has no composition owner. In order to build a valid model, a composition owner must be
     * defined. The returned object is an InteractionUse representing the InteractionUse in the Model.
     * 
     * @return An InteractionUse representing the InteractionUse in the Model.
     */
    @objid ("f7f2af04-644e-11e0-b650-001ec947cd2a")
    InteractionUse createInteractionUse();

    /**
     * Create an interactionUse.
     * The created InteractionUse refer An interaction. In order to build a valid model, the InteractionUse must be
     * inserted in the model.
     * 
     * @param refered the referred Interaction.
     * @return An InteractionUse representing the InteractionUse in the Model.
     */
    @objid ("f7f2af03-644e-11e0-b650-001ec947cd2a")
    InteractionUse createInteractionUse(Interaction refered);

    /**
     * Create an interface.
     * The created Interface has no composition owner. In order to build a valid model, a composition owner must be
     * defined. The returned object is an interface representing the Abstraction in the Model.
     * 
     * @return An interface representing the Interface in the Model.
     */
    @objid ("f7f2af02-644e-11e0-b650-001ec947cd2a")
    Interface createInterface();

    /**
     * Create an interface.
     * 
     * @param name the name of the Interface to create.
     * @param owner the NameSpace that will contain the Interface.
     * @return An interface representing the Interface in the Model.
     */
    @objid ("f7f2af01-644e-11e0-b650-001ec947cd2a")
    Interface createInterface(String name, NameSpace owner);

    /**
     * Create a stereotyped Interface.
     * 
     * @param name the name of the Interface to create.
     * @param owner the NameSpace that will contain the Interface.
     * @param stereotype the stereotype that will extend the Interface.
     * @return An interface representing the Interface in the Model.
     */
    @objid ("f7f04d44-644e-11e0-b650-001ec947cd2a")
    Interface createInterface(String name, NameSpace owner, Stereotype stereotype);

    @objid ("a1ad4d62-c52e-4112-b205-442cabdd8da0")
    Interface createInterface(String name, NameSpace owner, String moduleName, String stereotypeName) throws ExtensionNotFoundException;

    /**
     * Create an interfaceRealization.
     * The created InterfaceRealization has no composition owner. In order to build a valid model, a composition owner
     * must be defined. The returned object is an InterfaceRealization representing the Abstraction in the Model.
     * 
     * @return An InterfaceRealization representing the InterfaceRealization in the Model.
     */
    @objid ("f7f2af00-644e-11e0-b650-001ec947cd2a")
    InterfaceRealization createInterfaceRealization();

    /**
     * Create an interfaceRealization.
     * 
     * @param source the origin of the InterfaceRealization.
     * @param destination the destination of the InterfaceRealization
     * @return An InterfaceRealization representing the InterfaceRealization in the Model.
     */
    @objid ("f7f2aeff-644e-11e0-b650-001ec947cd2a")
    InterfaceRealization createInterfaceRealization(NameSpace source, Interface destination);

    /**
     * Create an internalTransition.
     * The created InternalTransition has no composition owner. In order to build a valid model, a composition owner
     * must be defined. The returned object is an InternalTransition representing the Abstraction in the Model.
     * 
     * @return An InternalTransition representing the InternalTransition in the Model.
     */
    @objid ("f7f2aefe-644e-11e0-b650-001ec947cd2a")
    InternalTransition createInternalTransition();

    /**
     * Create an interruptibleActivityRegion.
     * The created InterruptibleActivityRegion has no composition owner. In order to build a valid model, a composition
     * owner must be defined. The returned object is an InterruptibleActivityRegion representing the
     * InterruptibleActivityRegion in the Model.
     * 
     * @return An InterruptibleActivityRegion representing the InterruptibleActivityRegion in the Model.
     */
    @objid ("f7f2aefd-644e-11e0-b650-001ec947cd2a")
    InterruptibleActivityRegion createInterruptibleActivityRegion();

    /**
     * Create an JoinPseudoState.
     * The created JoinPseudoState has no composition owner. In order to build a valid model, a composition owner must
     * be defined. The returned object is an JoinPseudoState representing the JoinPseudoState in the Model.
     * 
     * @return An JoinPseudoState representing the JoinPseudoState in the Model.
     */
    @objid ("f7f2aefc-644e-11e0-b650-001ec947cd2a")
    JoinPseudoState createJoinPseudoState();

    /**
     * Create an JunctionPseudoState.
     * The created JunctionPseudoState has no composition owner. In order to build a valid model, a composition owner
     * must be defined. The returned object is an JunctionPseudoState representing the JunctionPseudoState in the
     * Model.
     * 
     * @return An JunctionPseudoState representing the JunctionPseudoState in the Model.
     */
    @objid ("f7f2aefb-644e-11e0-b650-001ec947cd2a")
    JunctionPseudoState createJunctionPseudoState();

    /**
     * Create a Lifeline.
     * The created Lifeline has no composition owner. In order to build a valid model, a composition owner must be
     * defined. The returned object is an Lifeline representing the Lifeline in the Model.
     * 
     * @return An Lifeline representing the Lifeline in the Model.
     */
    @objid ("f7f2aefa-644e-11e0-b650-001ec947cd2a")
    Lifeline createLifeline();

    /**
     * Create a Lifeline.
     * The Lifeline is created with a <code>name</code> in the <code>owner</code> Interaction.
     * 
     * @param name the name of the Lifeline to create.
     * @param owner the owner Interaction of the Lifeline to create.
     * @return An Lifeline representing the Lifeline in the Model.
     */
    @objid ("f7f2aef9-644e-11e0-b650-001ec947cd2a")
    Lifeline createLifeline(String name, Interaction owner);

    /**
     * Create a Lifeline.
     * The Lifeline is created with a <code>name</code> in the <code>owner</code> Interaction. The
     * <code>represented</code> instance is set on the Lifeline.
     * 
     * @param name the name of the Lifeline to create.
     * @param owner the owner Interaction of the Lifeline to create.
     * @param represented the Instance that will be represented by the Lifeline.
     * @return An Lifeline representing the Lifeline in the Model.
     */
    @objid ("f7f2aef8-644e-11e0-b650-001ec947cd2a")
    Lifeline createLifeline(String name, Interaction owner, Instance represented);

    /**
     * Create a Link between two instances.
     * This method create a binary Link between two instances and give a name to the destination role. The created Link
     * has a navigable role.
     * 
     * @param source the source instance of the Link.
     * @param destination the destination instance of the Link.
     * @param destinationRole the Name of the destination role.
     * @return An Link representing the Link in the Model.
     */
    @objid ("f7f2aef6-644e-11e0-b650-001ec947cd2a")
    Link createLink(Instance source, Instance destination, String destinationRole);

    @objid ("ca53f680-aca8-42db-aa69-00b853b49aa3")
    Link createLink();

    /**
     * Create a LinkEnd.
     * The created LinkEnd has no composition owner. In order to build a valid model, a composition owner must be
     * defined. The returned object is an LinkEnd representing the Abstraction in the Model.
     * 
     * @return An LinkEnd representing the LinkEnd in the Model.
     */
    @objid ("f7f04d35-644e-11e0-b650-001ec947cd2a")
    LinkEnd createLinkEnd();

    @objid ("9761d3a9-348d-11e2-b056-002564c97630")
    LocalPropertyTable createLocalPropertyTable();

    /**
     * Create a LoopNode.
     * The created LoopNode has no composition owner. In order to build a valid model, a composition owner must be
     * defined. The returned object is an LoopNode representing the LoopNode in the Model.
     * 
     * @return An LoopNode representing the LoopNode in the Model.
     */
    @objid ("f7f2aef3-644e-11e0-b650-001ec947cd2a")
    LoopNode createLoopNode();

    /**
     * Create a Manifestation.
     * The created Manifestation has no composition owner. In order to build a valid model, a composition owner must be
     * defined. The returned object is an Manifestation representing the Manifestation in the Model.
     * 
     * @return An Manifestation representing the Manifestation in the Model.
     */
    @objid ("f7f2aef2-644e-11e0-b650-001ec947cd2a")
    Manifestation createManifestation();

    /**
     * Create a MatrixDefinition.
     * 
     * @return a new MatrixDefinition instance.
     * @since 3.1.0
     */
    @objid ("fbd0148b-4efb-4099-8a47-ad403f5136f1")
    MatrixDefinition createMatrixDefinition();

    /**
     * Create a MatrixDefinition with all its contents.
     * 
     * @param name the name of the matrix to create.
     * @param lineQuery the QueryDefinition defining the line axis. Mandatory.
     * @param colQuery the QueryDefinition defining the column axis. If <code>null</code>, the lineQuery will also be used for columns.
     * @param depthQuery the QueryDefinition defining the depth axis. Might be <code>null</code>.
     * @param valueDefinition the MatrixValueDefinition defining the content of the matrix's cells.
     * @return a new MatrixDefinition instance.
     * @since 3.1.0
     */
    @objid ("fd063fd1-6cc1-4207-8aef-53bda4611e6d")
    MatrixDefinition createMatrixDefinition(String name, QueryDefinition lineQuery, QueryDefinition colQuery, QueryDefinition depthQuery, MatrixValueDefinition valueDefinition);

    /**
     * Create a MatrixValueDefinition.
     * 
     * @return a new MatrixValueDefinition instance.
     * @since 3.1.0
     */
    @objid ("36fd4032-b66b-4dd9-97c0-e41cf35c35ac")
    MatrixValueDefinition createMatrixValueDefinition();

    /**
     * Create a MatrixValueDefinition implemented by a java class.
     * <p>
     * The created MatrixValueDefinition owns an ExternProcessor, bound to the implementation class from the specified module.
     * </p>
     * 
     * @param implementationClassName the complete name of the implementation class. Ex: <code>com.modeliosoft.modelio.matrix.model.contentaccessor.JythonMatrixContentAccessor</code>
     * @param moduleName the module implementing the IMatrixContentAccessor interface. <code>null</code> for standard implementations defined by Modelio itself.
     * @return a new MatrixValueDefinition instance.
     * @since 3.1.0
     */
    @objid ("b159506a-b6b3-48c6-aae1-3653e5595358")
    MatrixValueDefinition createMatrixValueDefinition(String implementationClassName, String moduleName);

    /**
     * Create a Message.
     * The created Message has no composition owner. In order to build a valid model, a composition owner must be
     * defined. The returned object is an Message representing the Message in the Model.
     * 
     * @return An Message representing the Message in the Model.
     */
    @objid ("f7f2aef1-644e-11e0-b650-001ec947cd2a")
    Message createMessage();

    /**
     * Create a Message.
     * The Message is created with a type. In order to build a valid model, the Message must be inserted in the model.
     * 
     * @param sort the type of message to create.
     * @return An Message representing the Message in the Model.
     */
    @objid ("f7f2aef0-644e-11e0-b650-001ec947cd2a")
    Message createMessage(MessageSort sort);

    /**
     * Create a Message.
     * The Message is created with a type and invoke an operation. In order to build a valid model, the Message must be
     * inserted in the model.
     * 
     * @param sort the type of message to create.
     * @param invoked the operation that is invoked by the Message to create.
     * @return An Message representing the Message in the Model.
     */
    @objid ("f7f2aeef-644e-11e0-b650-001ec947cd2a")
    Message createMessage(MessageSort sort, Operation invoked);

    /**
     * Create a Message.
     * The Message is created with a <code>name</code> and a type. In order to build a valid model, the Message must be
     * inserted in the model.
     * 
     * @param name the name of the message to create.
     * @param sort the type of message to create.
     * @return An Message representing the Message in the Model.
     */
    @objid ("f7f2aeee-644e-11e0-b650-001ec947cd2a")
    Message createMessage(String name, MessageSort sort);

    /**
     * Create a MessageFlow.
     * The created MessageFlow has no composition owner. In order to build a valid model, a composition owner must be
     * defined. The returned object is an MessageFlow representing the MessageFlow in the Model.
     * 
     * @return An MessageFlow representing the MessageFlow in the Model.
     */
    @objid ("f7f2aeed-644e-11e0-b650-001ec947cd2a")
    MessageFlow createMessageFlow();

    @objid ("e957a868-f99e-4d53-92a9-561156fe8f15")
    NaryAssociation createNaryAssociation();

    @objid ("1061ed44-e894-4af1-ac7f-ac8aa6b47653")
    NaryAssociation createNaryAssociation(List<Classifier> ends);

    @objid ("c5ef9df6-9dd0-4c3e-b07a-b5f4e1bf037c")
    NaryAssociationEnd createNaryAssociationEnd();

    @objid ("4099071e-6b7a-43db-ab0a-788c1ba622da")
    NaryConnector createNaryConnector();

    @objid ("6871393c-16ac-42af-9c2e-6b233cf101ab")
    NaryConnector createNaryConnector(List<BindableInstance> ends);

    @objid ("4192f61a-9875-4da0-8e40-da06c15b293c")
    NaryConnectorEnd createNaryConnectorEnd();

    @objid ("67fb9a8a-527b-44e5-8ff6-0615aba429aa")
    NaryLink createNaryLink();

    @objid ("32605af5-f874-48fc-9577-901bfe343cea")
    NaryLink createNaryLink(List<Instance> source);

    @objid ("41106a00-7db7-4767-9405-77ed66209d85")
    NaryLinkEnd createNaryLinkEnd();

    /**
     * Create a Node.
     * The created Node has no composition owner. In order to build a valid model, a composition owner must be defined.
     * The returned object is an Node representing the Abstraction in the Model.
     * 
     * @return An Node representing the Node in the Model.
     */
    @objid ("f7f2aeeb-644e-11e0-b650-001ec947cd2a")
    Node createNode();

    /**
     * Create a Note.
     * The created Note has no composition owner. In order to build a valid model, a composition owner must be defined.
     * The returned object is an Note representing the Abstraction in the Model.
     * 
     * @return An Note representing the Note in the Model.
     */
    @objid ("f7f2aeea-644e-11e0-b650-001ec947cd2a")
    Note createNote();

    /**
     * Create a Note.
     * 
     * @param moduleName the name of the module owning the note type.
     * @param noteType the name of the NoteType.
     * @param owner the composition owner of the Note.
     * @param content the text of the Note.
     * @return An Note representing the Note in the Model.
     * @throws org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException if no type matching the given name and the element metaclass is found
     */
    @objid ("f7f2aee9-644e-11e0-b650-001ec947cd2a")
    Note createNote(String moduleName, String noteType, ModelElement owner, String content) throws ExtensionNotFoundException;

    /**
     * Create a Note.
     * 
     * @param noteType the type of the Note.
     * @param owner the composition owner of the Note.
     * @param content the text of the Note.
     * @return An Note representing the Note in the Model.
     */
    @objid ("e4e59454-7f92-4293-9438-986176a842ad")
    Note createNote(NoteType noteType, ModelElement owner, String content);

    /**
     * Create a ObjectDiagram.
     * The returned object is an ObjectDiagram owned by <code>owner</code> named by <code>name</code> and sterotyped by
     * <code>stereotype</code>
     * 
     * @param name the name of the Object Diagram to create.
     * @param owner the element on which the Object Diagram will be created.
     * @param stereotype the name of the stereotype that will be used to type the Object Diagram
     * @return An ObjectDiagram representing the diagram in the Model.
     */
    @objid ("ffcf3b6f-87a7-11e0-bfd2-002564c97630")
    ObjectDiagram createObjectDiagram(final String name, final ModelElement owner, final Stereotype stereotype);

    /**
     * Create a ObjectFlow.
     * The created ObjectFlow has no composition owner. In order to build a valid model, a composition owner must be
     * defined. The returned object is an ObjectFlow representing the ObjectFlow in the Model.
     * 
     * @return An ObjectFlow representing the ObjectFlow in the Model.
     */
    @objid ("f7f2aee8-644e-11e0-b650-001ec947cd2a")
    ObjectFlow createObjectFlow();

    /**
     * Create a OpaqueAction.
     * The created OpaqueAction has no composition owner. In order to build a valid model, a composition owner must be
     * defined. The returned object is an OpaqueAction representing the OpaqueAction in the Model.
     * 
     * @return An OpaqueAction representing the OpaqueAction in the Model.
     */
    @objid ("f7f2aee7-644e-11e0-b650-001ec947cd2a")
    OpaqueAction createOpaqueAction();

    /**
     * Create a OpaqueBehavior.
     * The created OpaqueBehavior has no composition owner. In order to build a valid model, a composition owner must be
     * defined. The returned object is an OpaqueBehavior representing the OpaqueBehavior in the Model.
     * 
     * @return An OpaqueBehavior representing the OpaqueBehavior in the Model.
     */
    @objid ("f7f04d2c-644e-11e0-b650-001ec947cd2a")
    OpaqueBehavior createOpaqueBehavior();

    /**
     * Create an Operation.
     * The created Operation has no composition owner. In order to build a valid model, a composition owner must be
     * defined. The returned object is an Operation representing the Abstraction in the Model.
     * 
     * @return An Operation representing the Operation in the Model.
     */
    @objid ("f7f2aee6-644e-11e0-b650-001ec947cd2a")
    Operation createOperation();

    /**
     * Create an Operation.
     * 
     * @param name the name of the Operation to create.
     * @param owner the Classifier that will contain the Operation.
     * @return An Operation representing the Operation in the Model.
     */
    @objid ("f7f2aee5-644e-11e0-b650-001ec947cd2a")
    Operation createOperation(String name, Classifier owner);

    /**
     * Create a stereotyped Operation.
     * 
     * @param name the name of the Operation to create.
     * @param owner the Classifier that will contain the Operation.
     * @param stereotype the Stereotype that will extend the Operation.
     * @return An Operation representing the Operation in the Model.
     */
    @objid ("f7f2aee4-644e-11e0-b650-001ec947cd2a")
    Operation createOperation(String name, Classifier owner, Stereotype stereotype);

    @objid ("b0311e1c-8609-4558-b9b3-9d6836d90d3c")
    Operation createOperation(String name, Classifier owner, String moduleName, String stereotypeName) throws ExtensionNotFoundException;

    /**
     * Create a OutputPin.
     * The created OutputPin has no composition owner. In order to build a valid model, a composition owner must be
     * defined. The returned object is an OutputPin representing the OutputPin in the Model.
     * 
     * @return An OutputPin representing the OutputPin in the Model.
     */
    @objid ("f7f2aee3-644e-11e0-b650-001ec947cd2a")
    OutputPin createOutputPin();

    /**
     * Create a Package.
     * The created Package has no composition owner. In order to build a valid model, a composition owner must be
     * defined. The returned object is an Package representing the Abstraction in the Model.
     * 
     * @return An Package representing the Package in the Model.
     */
    @objid ("f7f2aee2-644e-11e0-b650-001ec947cd2a")
    Package createPackage();

    /**
     * Create a Package.
     * 
     * @param name the name of the Package to create.
     * @param owner the NameSpace that will contain the Package.
     * @return An Package representing the Package in the Model.
     */
    @objid ("f7f2aee1-644e-11e0-b650-001ec947cd2a")
    Package createPackage(String name, NameSpace owner);

    /**
     * Create a stereotyped Package.
     * 
     * @param name the name of the Package to create.
     * @param owner the NameSpace that will contain the Package.
     * @param stereotype the Stereotype will extend the Package.
     * @return An Package representing the Package in the Model.
     */
    @objid ("f7f2aee0-644e-11e0-b650-001ec947cd2a")
    Package createPackage(String name, NameSpace owner, Stereotype stereotype);

    @objid ("44818770-e84b-4587-93df-a139bd12a63a")
    Package createPackage(String name, NameSpace owner, String moduleName, String stereotypeName) throws ExtensionNotFoundException;

    /**
     * Create a PackageImport.
     * The created PackageImport has no composition owner. In order to build a valid model, a composition owner must be
     * defined. The returned object is an PackageImport representing the Abstraction in the Model.
     * 
     * @return An PackageImport representing the PackageImport in the Model.
     */
    @objid ("f7f2aedf-644e-11e0-b650-001ec947cd2a")
    PackageImport createPackageImport();

    /**
     * Create a PackageImport between a NameSpace and a Package.
     * 
     * @param source origin of the ElementImport
     * @param destination destination of the ElementImport
     * @return An ElementImport representing the ElementImport in the Model.
     */
    @objid ("f7f2aede-644e-11e0-b650-001ec947cd2a")
    PackageImport createPackageImport(NameSpace source, Package destination);

    /**
     * Create a PackageImport between an Operation and a Package.
     * 
     * @param source origin of the ElementImport
     * @param destination destination of the ElementImport
     * @return An ElementImport representing the ElementImport in the Model.
     */
    @objid ("f7f2aedd-644e-11e0-b650-001ec947cd2a")
    PackageImport createPackageImport(Operation source, Package destination);

    /**
     * Create a PackageMerge.
     * The created PackageMerge has no composition owner. In order to build a valid model, a composition owner must be
     * defined. The returned object is an PackageMerge representing the Abstraction in the Model.
     * 
     * @return An PackageMerge representing the PackageMerge in the Model.
     */
    @objid ("f7f2aedc-644e-11e0-b650-001ec947cd2a")
    PackageMerge createPackageMerge();

    /**
     * Create a Parameter.
     * The created Parameter has no composition owner. In order to build a valid model, a composition owner must be
     * defined. The returned object is an Parameter representing the Abstraction in the Model.
     * 
     * @return An Parameter representing the Parameter in the Model.
     */
    @objid ("f7f2aedb-644e-11e0-b650-001ec947cd2a")
    Parameter createParameter();

    /**
     * Create a PartDecomposition.
     * The created PartDecomposition has no composition owner. In order to build a valid model, a composition owner must
     * be defined. The returned object is an PartDecomposition representing the PartDecomposition in the Model.
     * 
     * @return An PartDecomposition representing the PartDecomposition in the Model.
     */
    @objid ("f7f2aeda-644e-11e0-b650-001ec947cd2a")
    PartDecomposition createPartDecomposition();

    /**
     * Create an PartDecomposition.
     * The created PartDecomposition refer An interaction. In order to build a valid model, the PartDecomposition must
     * be inserted in the model.
     * 
     * @param refered the refered Interaction.
     * @return An PartDecomposition representing the PartDecomposition in the Model.
     */
    @objid ("f7f2aed9-644e-11e0-b650-001ec947cd2a")
    PartDecomposition createPartDecomposition(Interaction refered);

    /**
     * Create a Port.
     * The created Port has no composition owner. In order to build a valid model, a composition owner must be defined.
     * The returned object is an Port representing the Abstraction in the Model.
     * 
     * @return An Port representing the Port in the Model.
     */
    @objid ("f7f2aed8-644e-11e0-b650-001ec947cd2a")
    Port createPort();

    /**
     * Create a Port.
     * The Port is created with the specified name and owner Instance.
     * 
     * @param name The name of the Port to create.
     * @param owner The Instance that will contain the Port.
     * @return An Port representing the Port in the Model.
     */
    @objid ("f7f04d2d-644e-11e0-b650-001ec947cd2a")
    Port createPort(String name, Instance owner);

    /**
     * Create a Port.
     * The port is Created with the specified name and owner Classifier.
     * 
     * @param name The name of the Port to create.
     * @param owner The Classifier that will contain the Port.
     * @return An Port representing the Port in the Model.
     */
    @objid ("f7f2aed7-644e-11e0-b650-001ec947cd2a")
    Port createPort(String name, Classifier owner);

    @objid ("9761d3ab-348d-11e2-b056-002564c97630")
    PropertyDefinition createPropertyDefinition();

    @objid ("9761d3ad-348d-11e2-b056-002564c97630")
    PropertyEnumerationLitteral createPropertyEnumerationLitteral();

    @objid ("9761d3af-348d-11e2-b056-002564c97630")
    PropertyTable createPropertyTable();

    @objid ("9764350a-348d-11e2-b056-002564c97630")
    PropertyTableDefinition createPropertyTableDefinition();

    @objid ("9764350c-348d-11e2-b056-002564c97630")
    PropertyType createPropertyType();

    /**
     * Create a ProvidedInterface.
     * The created ProvidedInterface has no composition owner. In order to build a valid model, a composition owner must
     * be defined. The returned object is an ProvidedInterface representing the Abstraction in the Model.
     * 
     * @return An ProvidedInterface representing the ProvidedInterface in the Model.
     */
    @objid ("f7f2aed6-644e-11e0-b650-001ec947cd2a")
    ProvidedInterface createProvidedInterface();

    /**
     * Create a ProvidedInterface.
     * 
     * @param owner the Port that contains the ProvidedInterface.
     * @param interfaces the provided Interfaces
     * @return An ProvidedInterface representing the ProvidedInterface in the Model.
     */
    @objid ("f7f2aed5-644e-11e0-b650-001ec947cd2a")
    ProvidedInterface createProvidedInterface(Port owner, List<Interface> interfaces);

    /**
     * Create a QueryDefinition.
     * 
     * @return a new QueryDefinition instance.
     * @since 3.1.0
     */
    @objid ("ceae4218-11f6-486d-98fc-b186b6d6c7ae")
    QueryDefinition createQueryDefinition();

    /**
     * Create a QueryDefinition implemented by a java class.
     * <p>
     * The created QueryDefinition owns an ExternProcessor, bound to the implementation class from the specified module.
     * </p>
     * 
     * @param implementationClassName the complete name of the implementation class. Ex: <code>com.modeliosoft.modelio.matrix.model.queries.AllInstancesQuery</code>
     * @param moduleName the module implementing the IQuery interface. <code>null</code> for standard implementations defined by Modelio itself.
     * @return a new QueryDefinition instance.
     * @since 3.1.0
     */
    @objid ("f7d7f612-d28c-420b-97bc-9968de5a43ed")
    QueryDefinition createQueryDefinition(String implementationClassName, String moduleName);

    /**
     * Create a RaisedException.
     * The created RaisedException has no composition owner. In order to build a valid model, a composition owner must
     * be defined. The returned object is an RaisedException representing the Abstraction in the Model.
     * 
     * @return An RaisedException representing the RaisedException in the Model.
     */
    @objid ("f7f2aed3-644e-11e0-b650-001ec947cd2a")
    RaisedException createRaisedException();

    /**
     * Create a Region.
     * The created Region has no composition owner. In order to build a valid model, a composition owner must be
     * defined. The returned object is an Region representing the Abstraction in the Model.
     * 
     * @return An RequiredInterface representing the RequiredInterface in the Model.
     */
    @objid ("f7f2aed2-644e-11e0-b650-001ec947cd2a")
    Region createRegion();

    /**
     * Create a RequiredInterface.
     * The created RequiredInterface has no composition owner. In order to build a valid model, a composition owner must
     * be defined. The returned object is an RequiredInterface representing the Abstraction in the Model.
     * 
     * @return An RequiredInterface representing the RequiredInterface in the Model.
     */
    @objid ("f7f2aed1-644e-11e0-b650-001ec947cd2a")
    RequiredInterface createRequiredInterface();

    /**
     * Create a RequiredInterface.
     * 
     * @param owner the Port that contains the RequiredInterface.
     * @param interfaces the required Interfaces
     * @return An RequiredInterface representing the RequiredInterface in the Model.
     */
    @objid ("f7f2aed0-644e-11e0-b650-001ec947cd2a")
    RequiredInterface createRequiredInterface(Port owner, List<Interface> interfaces);

    /**
     * Create an ReturnParameter.
     * 
     * @param name the name of the Parameter to create.
     * @param type the type of the Parameter to create.
     * @param owner the Operation that will contain the Parameter.
     * @return An Parameter representing the Parameter in the Model.
     */
    @objid ("f7f2aecf-644e-11e0-b650-001ec947cd2a")
    Parameter createReturnParameter(String name, GeneralClass type, Operation owner);

    /**
     * Create a stereotyped ReturnParameter.
     * 
     * @param name the name of the Parameter to create.
     * @param type the type of the Parameter to create.
     * @param owner the Operation that will contain the Parameter.
     * @param stereotype the stereotype that will extend the Parameter.
     * @return An Parameter representing the Parameter in the Model.
     */
    @objid ("f7f2aece-644e-11e0-b650-001ec947cd2a")
    Parameter createReturnParameter(String name, GeneralClass type, Operation owner, Stereotype stereotype);

    @objid ("6520e604-3da5-4fb9-8d1f-b259228f8503")
    Parameter createReturnParameter(String name, GeneralClass type, Operation owner, String moduleName, String stereotypeName) throws ExtensionNotFoundException;

    /**
     * Create a SendSignalAction.
     * The created SendSignalAction has no composition owner. In order to build a valid model, a composition owner must
     * be defined. The returned object is an SendSignalAction representing the SendSignalAction in the Model.
     * 
     * @return An SendSignalAction representing the SendSignalAction in the Model.
     */
    @objid ("f7f2aecd-644e-11e0-b650-001ec947cd2a")
    SendSignalAction createSendSignalAction();

    /**
     * Create a SequenceDiagram.
     * The created SequenceDiagram has no composition owner. In order to build a valid model, a composition owner must
     * be defined. The returned object is an SequenceDiagram representing the SequenceDiagram in the Model.
     * 
     * @return An SequenceDiagram representing the SequenceDiagram in the Model.
     */
    @objid ("f7f2aecc-644e-11e0-b650-001ec947cd2a")
    SequenceDiagram createSequenceDiagram();

    /**
     * Create a ShallowHistoryPseudoState.
     * The created ShallowHistoryPseudoState has no composition owner. In order to build a valid model, a composition
     * owner must be defined. The returned object is an ShallowHistoryPseudoState representing the
     * ShallowHistoryPseudoState in the Model.
     * 
     * @return An ShallowHistoryPseudoState representing the ShallowHistoryPseudoState in the Model.
     */
    @objid ("f7f2aecb-644e-11e0-b650-001ec947cd2a")
    ShallowHistoryPseudoState createShallowHistoryPseudoState();

    /**
     * Create a Signal.
     * The created Signal has no composition owner. In order to build a valid model, a composition owner must be
     * defined. The returned object is an Signal representing the Abstraction in the Model.
     * 
     * @return An Signal representing the Signal in the Model.
     */
    @objid ("f7f2aeca-644e-11e0-b650-001ec947cd2a")
    Signal createSignal();

    /**
     * Create a State.
     * The created State has no composition owner. In order to build a valid model, a composition owner must be defined.
     * The returned object is an State representing the Abstraction in the Model.
     * 
     * @return An State representing the State in the Model.
     */
    @objid ("f7f2aec9-644e-11e0-b650-001ec947cd2a")
    State createState();

    /**
     * Create a StateInvariant.
     * The created StateInvariant has no composition owner. In order to build a valid model, a composition owner must be
     * defined. The returned object is an StateInvariant representing the StateInvariant in the Model.
     * 
     * @return An StateInvariant representing the StateInvariant in the Model.
     */
    @objid ("f7f2aec8-644e-11e0-b650-001ec947cd2a")
    StateInvariant createStateInvariant();

    /**
     * Create a StateInvariant.
     * Create a StateInvariant with a body. In order to build a valid model, the StateInvariant must be inserted in the
     * model.
     * 
     * @param body the body of the StateInvariant to create.
     * @return An StateInvariant representing the StateInvariant in the Model.
     */
    @objid ("f7f2aec7-644e-11e0-b650-001ec947cd2a")
    StateInvariant createStateInvariant(String body);

    /**
     * Create a StateMachine.
     * The created StateMachine has no composition owner. In order to build a valid model, a composition owner must be
     * defined. The returned object is an StateMachine representing the Abstraction in the Model.
     * 
     * @return An StateMachine representing the StateMachine in the Model.
     */
    @objid ("f7f2aec6-644e-11e0-b650-001ec947cd2a")
    StateMachine createStateMachine();

    /**
     * Create a StateMachineDiagram.
     * The returned object is an StateMachineDiagram owned by <code>owner</code> named by <code>name</code> and sterotyped by
     * <code>stereotype</code>
     * 
     * @param name the name of the StateMachine Diagram to create.
     * @param owner the element on which the StateMachine Diagram will be created.
     * @param stereotype the name of the stereotype that will be used to type the StateMachine Diagram
     * @return An StateMachineDiagram representing the diagram in the Model.
     */
    @objid ("ffcec642-87a7-11e0-bfd2-002564c97630")
    StateMachineDiagram createStateMachineDiagram(final String name, final ModelElement owner, final Stereotype stereotype);

    /**
     * Create a StaticDiagram.
     * The returned object is an StaticDiagram owned by <code>owner</code> named by <code>name</code> and sterotyped by
     * <code>stereotype</code>
     * 
     * @param name the name of the Static Diagram to create.
     * @param owner the element on which the Static Diagram will be created.
     * @param stereotype the name of the stereotype that will be used to type the Static Diagram
     * @return An StaticDiagram representing the diagram in the Model.
     */
    @objid ("f7f2aec5-644e-11e0-b650-001ec947cd2a")
    StaticDiagram createStaticDiagram(String name, ModelElement owner, Stereotype stereotype);

    @objid ("d94088ca-438f-4acd-8a3d-2dea9ad0436e")
    StaticDiagram createStaticDiagram(String name, ModelElement owner, String moduleName, String stereotypeName) throws ExtensionNotFoundException;

    /**
     * Create a StructuredActivityNode.
     * The created StructuredActivityNode has no composition owner. In order to build a valid model, a composition owner
     * must be defined. The returned object is an StructuredActivityNode representing the StructuredActivityNode in the
     * Model.
     * 
     * @return An StructuredActivityNode representing the StructuredActivityNode in the Model.
     */
    @objid ("f7f2aec4-644e-11e0-b650-001ec947cd2a")
    StructuredActivityNode createStructuredActivityNode();

    /**
     * Create a Substitution.
     * The created Substitution has no composition owner. In order to build a valid model, a composition owner must be
     * defined. The returned object is an Substitution representing the Abstraction in the Model.
     * 
     * @return An Substitution representing the Substitution in the Model.
     */
    @objid ("f7f2aec3-644e-11e0-b650-001ec947cd2a")
    Substitution createSubstitution();

    /**
     * Create a TagParameter.
     * The created TagParameter has no composition owner. In order to build a valid model, a composition owner must be
     * defined. The returned object is an TagParameter representing the Abstraction in the Model.
     * 
     * @return An TagParameter representing the TagParameter in the Model.
     */
    @objid ("f7f2aec2-644e-11e0-b650-001ec947cd2a")
    TagParameter createTagParameter();

    /**
     * Create a TagParameter using a parameter as initial value.
     * 
     * @param value the value of the tag parameter.
     * @param owner the owner tagged value of the tag parameter.
     * @return An TagParameter representing the TagParameter in the Model.
     */
    @objid ("f7f2aec1-644e-11e0-b650-001ec947cd2a")
    TagParameter createTagParameter(String value, TaggedValue owner);

    /**
     * Create a TaggedValue.
     * The created TaggedValue has no composition owner. In order to build a valid model, a composition owner must be
     * defined. The returned object is an TaggedValue representing the Abstraction in the Model.
     * 
     * @return An TaggedValue representing the TaggedValue in the Model.
     */
    @objid ("f7f2aec0-644e-11e0-b650-001ec947cd2a")
    TaggedValue createTaggedValue();

    /**
     * Create a TaggedValue.
     * 
     * @param moduleName the name of the module owning the note type.
     * @param tagType the name of the TagType.
     * @param owner the ModelElement that contains the Taggedvalue.
     * @return An TaggedValue representing the TaggedValue in the Model.
     * @throws org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException if no tag type matching the given name and the element metaclass is found
     */
    @objid ("f7f2aebf-644e-11e0-b650-001ec947cd2a")
    TaggedValue createTaggedValue(String moduleName, String tagType, ModelElement owner) throws ExtensionNotFoundException;

    /**
     * Create a TaggedValue.
     * 
     * @param tagType the type of the TaggedValue.
     * @param owner the ModelElement that contains the Taggedvalue.
     * @return An TaggedValue representing the TaggedValue in the Model.
     */
    @objid ("3a6526a0-a6f8-4b86-a385-7f56ce19b216")
    TaggedValue createTaggedValue(TagType tagType, ModelElement owner);

    /**
     * Create a TemplateBinding.
     * The created TemplateBinding has no composition owner. In order to build a valid model, a composition owner must
     * be defined. The returned object is an TemplateBinding representing the Abstraction in the Model.
     * 
     * @return An TemplateBinding representing the TemplateBinding in the Model.
     */
    @objid ("f7f2aebe-644e-11e0-b650-001ec947cd2a")
    TemplateBinding createTemplateBinding();

    /**
     * Create a TemplateParameter.
     * The created TemplateParameter has no composition owner. In order to build a valid model, a composition owner must
     * be defined. The returned object is an TemplateParameter representing the Abstraction in the Model.
     * 
     * @return An TemplateParameter representing the TemplateParameter in the Model.
     */
    @objid ("f7f2aebd-644e-11e0-b650-001ec947cd2a")
    TemplateParameter createTemplateParameter();

    /**
     * Create a TemplateParameterSubstitution.
     * The created TemplateParameterSubstitution has no composition owner. In order to build a valid model, a
     * composition owner must be defined. The returned object is an TemplateParameterSubstitution representing the
     * Abstraction in the Model.
     * 
     * @return An TemplateParameterSubstitution representing the TemplateParameterSubstitution in the Model.
     */
    @objid ("f7f2aebc-644e-11e0-b650-001ec947cd2a")
    TemplateParameterSubstitution createTemplateParameterSubstitution();

    /**
     * Create a TerminatePseudoState.
     * The created TerminatePseudoState has no composition owner. In order to build a valid model, a composition owner
     * must be defined. The returned object is an TerminatePseudoState representing the TerminatePseudoState in the
     * Model.
     * 
     * @return An TerminatePseudoState representing the TerminatePseudoState in the Model.
     */
    @objid ("f7f2aebb-644e-11e0-b650-001ec947cd2a")
    TerminatePseudoState createTerminatePseudoState();

    /**
     * Create a Transition.
     * The created Transition has no composition owner. In order to build a valid model, a composition owner must be
     * defined. The returned object is an Transition representing the Abstraction in the Model.
     * 
     * @return An Transition representing the Transition in the Model.
     */
    @objid ("f7f2aeba-644e-11e0-b650-001ec947cd2a")
    Transition createTransition();

    @objid ("9764350e-348d-11e2-b056-002564c97630")
    TypedPropertyTable createTypedPropertyTable();

    /**
     * Create a Usage.
     * The created Usage has no composition owner. In order to build a valid model, a composition owner must be defined.
     * The returned object is an Usage representing the Abstraction in the Model.
     * 
     * @return An Usage representing the Usage in the Model.
     */
    @objid ("f7f2aeb9-644e-11e0-b650-001ec947cd2a")
    Usage createUsage();

    /**
     * Create a Usage between two ModelElement.
     * 
     * @param source the origin of the Usage.
     * @param destination the destination of the Usage.
     * @return An Usage representing the Usage in the Model.
     */
    @objid ("f7f2aeb8-644e-11e0-b650-001ec947cd2a")
    Usage createUsage(ModelElement source, ModelElement destination);

    /**
     * Create a UseCase.
     * The created UseCase has no composition owner. In order to build a valid model, a composition owner must be
     * defined. The returned object is an UseCase representing the Abstraction in the Model.
     * 
     * @return An UseCase representing the UseCase in the Model.
     */
    @objid ("f7f2aeb7-644e-11e0-b650-001ec947cd2a")
    UseCase createUseCase();

    /**
     * Create a UseCase.
     * 
     * @param name the name of the UseCase to create.
     * @param owner the NameSpace that will contain the Usecase.
     * @return An UseCase representing the UseCase in the Model.
     */
    @objid ("f7f2aeb6-644e-11e0-b650-001ec947cd2a")
    UseCase createUseCase(String name, NameSpace owner);

    /**
     * Create a stereotyped UseCase.
     * 
     * @param name the name of the UseCase to create.
     * @param owner the NameSpace that will contain the Usecase.
     * @param stereotype the Stereotype that will extend the UseCase.
     * @return An UseCase representing the UseCase in the Model.
     */
    @objid ("f7f2aeb5-644e-11e0-b650-001ec947cd2a")
    UseCase createUseCase(String name, NameSpace owner, Stereotype stereotype);

    @objid ("8f677b9e-3b82-4e5f-8c36-b755e0b303a5")
    UseCase createUseCase(String name, NameSpace owner, String moduleName, String stereotypeName) throws ExtensionNotFoundException;

    /**
     * Create a UseCaseDependency.
     * The created UseCaseDependency has no composition owner. In order to build a valid model, a composition owner must
     * be defined. The returned object is an UseCaseDependency representing the Abstraction in the Model.
     * 
     * @return An UseCaseDependency representing the UseCaseDependency in the Model.
     */
    @objid ("f7f2aeb4-644e-11e0-b650-001ec947cd2a")
    UseCaseDependency createUseCaseDependency();

    /**
     * Create a UseCaseDiagram.
     * The returned object is an UseCaseDiagram owned by <code>owner</code> named by <code>name</code> and sterotyped by
     * <code>stereotype</code>
     * 
     * @param name the name of the UseCase Diagram to create.
     * @param owner the element on which the UseCase Diagram will be created.
     * @param stereotype the name of the stereotype that will be used to type the UseCase Diagram
     * @return An UseCaseDiagram representing the diagram in the Model.
     */
    @objid ("ffce7824-87a7-11e0-bfd2-002564c97630")
    UseCaseDiagram createUseCaseDiagram(final String name, final ModelElement owner, final Stereotype stereotype);

    /**
     * Create a ValuePin.
     * The created ValuePin has no composition owner. In order to build a valid model, a composition owner must be
     * defined. The returned object is an ValuePin representing the ValuePin in the Model.
     * 
     * @return An ValuePin representing the ValuePin in the Model.
     * @since 2.1.1
     */
    @objid ("91b13b12-63aa-11e1-89ef-002564c97630")
    ValuePin createValuePin();

    @objid ("4b3fbc4f-4bd7-479b-b75b-0dbcbc4d3580")
    IDefaultNameService getDefaultNameService();

    /**
     * Get all the roots of the libraries.
     * They are mostly instances of {@link Component} or {@link AbstractProject}.
     * 
     * @return The library root elements.
     */
    @objid ("214e558c-3572-11e2-b290-002564c97630")
    List<MObject> getLibraryRoots();

    /**
     * Get all the roots of the editable model.
     * They are mostly instances of {@link AbstractProject}.
     * 
     * @return The model root elements.
     */
    @objid ("f7f2aeb2-644e-11e0-b650-001ec947cd2a")
    List<MObject> getModelRoots();

    /**
     * Get the roots of the object in parameter.
     * They are mostly instances of {@link AbstractProject}.
     * 
     * @param context context The fragment context
     * @return The model root elements.
     */
    @objid ("3421a7c5-0483-4b33-8dd3-0adcd39b9543")
    MObject getRoot(MObject context);

    /**
     * Get access to the UML types
     * 
     * @return an object that allow access to UML types
     */
    @objid ("f7f2aeb1-644e-11e0-b650-001ec947cd2a")
    IUMLTypes getUmlTypes();

    /**
     * Create an ExternElement.
     * The created ExternElement has no composition owner. In order to build a valid model, a composition owner must be defined.
     * The returned object is an ExternElement representing the Abstraction in the Model.
     * 
     * @return An ExternElement representing the ExternElement in the Model.
     */
    @objid ("aacf1c58-e18a-48c6-8976-d19cecf2b04c")
    ExternElement createExternElement();

    /**
     * Create a stereotyped externElement.
     * The externElement is created on owner, has a name and owns a Stereotype.
     * 
     * @param name the name of the ExternElement to create.
     * @param owner the MethodologicalLink that will contain the ExternElement.
     * @param stereotype the Stereotype to refer on the externElement.
     * @return An ExternElement representing the ExternElement in the Model.
     */
    @objid ("e881c782-5686-4295-92bc-282e05f007e3")
    ExternElement createExternElement(String name, MethodologicalLink owner, Stereotype stereotype);

    /**
     * Create a stereotyped externElement.
     * The externElement is created on owner, has a name and owns a Stereotype.
     * 
     * @param name the name of the ExternElement to create.
     * @param owner the MethodologicalLink that will contain the ExternElement.
     * @param moduleName the module owning the Stereotype that will be refered by the ExternElement.
     * @param stereotypeName the Stereotype to refer on the externElement.
     * @return An ExternElement representing the ExternElement in the Mode.
     * @throws org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException if no stereotype matching the given name and the element metaclass is found
     */
    @objid ("75a24828-4ea4-4dc6-87a7-caefb8c230c5")
    ExternElement createExternElement(String name, MethodologicalLink owner, String moduleName, String stereotypeName) throws ExtensionNotFoundException;

    /**
     * Create a MethodologicalLink.
     * 
     * @return An MethodologicalLink representing the MethodologicalLink in the Model.
     */
    @objid ("81d606a1-6b93-4d49-86a0-92a5cacebe2b")
    MethodologicalLink createMethodologicalLink();

    /**
     * Create a stereotyped MethodologicalLink between two ModelElements.<br>
     * 
     * @param source the origin of the methodologicalLink.
     * @param destination the destination of the methodologicalLink.
     * @param stereotype the Stereotype that extends the methodologicalLink.
     * @return An MethodologicalLink representing the MethodologicalLink in the Model.
     */
    @objid ("824f2349-418f-4af0-af68-4e0966f0b006")
    MethodologicalLink createMethodologicalLink(ModelElement source, ModelElement destination, Stereotype stereotype);

    @objid ("25e432ff-90d7-4b69-bb39-2781cbea6575")
    MethodologicalLink createMethodologicalLink(ModelElement source, ModelElement destination, String moduleName, String stereotypeName) throws ExtensionNotFoundException;

}
