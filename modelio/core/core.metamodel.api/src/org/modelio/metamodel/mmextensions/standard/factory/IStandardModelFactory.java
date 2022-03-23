/* 
 * Copyright 2013-2020 Modeliosoft
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
package org.modelio.metamodel.mmextensions.standard.factory;

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
import org.modelio.metamodel.diagrams.ObjectDiagram;
import org.modelio.metamodel.diagrams.SequenceDiagram;
import org.modelio.metamodel.diagrams.StateMachineDiagram;
import org.modelio.metamodel.diagrams.StaticDiagram;
import org.modelio.metamodel.diagrams.UseCaseDiagram;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.mda.Project;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.mmextensions.infrastructure.IInfrastructureModelFactory;
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
import org.modelio.metamodel.uml.behavior.interactionModel.TerminateSpecification;
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
import org.modelio.metamodel.uml.infrastructure.Abstraction;
import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.metamodel.uml.infrastructure.ExternElement;
import org.modelio.metamodel.uml.infrastructure.MethodologicalLink;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.Substitution;
import org.modelio.metamodel.uml.infrastructure.Usage;
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
import org.modelio.metamodel.uml.statik.ComponentRealization;
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
import org.modelio.vcore.session.api.repository.IRepository;

/**
 * Factory that creates the UML model elements.
 * <p>
 * New UML model elements can easily be created using <i>createXxxxx()</i> methods where Xxxxx is the name of the metaclass for
 * which an instance has to be created. At least one method <i>createXxxxx()</i> exists for UML model Element. i.e.:
 * {@linkplain #createClass()}. This method only create an instance of the metaclass. The instance will have to be attached to the
 * model and eventually named to be valid for the current transaction.
 * </p>
 * 
 * <p>
 * This factory is also providing convenience methods that allow to directly create a valid instance for the most frequently used
 * metaclasses. i.e.:<br>
 * {@linkplain #createClass(String name, NameSpace owner)},<br>
 * {@linkplain #createClass(String name, NameSpace owner, Stereotype stereotype)} .<br>
 * These methods ensure the validity of the created element.
 * </p>
 */
@objid ("1d41ecd6-2116-4b79-aff7-9e19e37f4957")
public interface IStandardModelFactory extends IInfrastructureModelFactory {
    /**
     * @return a new {@link Abstraction}.
     */
    @objid ("0d3b4d66-380c-4e7a-88a5-b2823f2d0ad8")
    Abstraction createAbstraction();

    /**
     * @return a new {@link AcceptCallEventAction}.
     */
    @objid ("cc75c8de-669e-494b-940f-01609acaa000")
    AcceptCallEventAction createAcceptCallEventAction();

    /**
     * @return a new {@link AcceptChangeEventAction}.
     */
    @objid ("8afe219e-7521-4497-a13c-da67c9fa099a")
    AcceptChangeEventAction createAcceptChangeEventAction();

    /**
     * @return a new {@link AcceptSignalAction}.
     */
    @objid ("26a7fc49-eb23-4b8f-b02b-9e21240ee080")
    AcceptSignalAction createAcceptSignalAction();

    /**
     * @return a new {@link AcceptTimeEventAction}.
     */
    @objid ("6cabd9c7-f5a9-4365-b61b-c0acc9dd6f85")
    AcceptTimeEventAction createAcceptTimeEventAction();

    /**
     * @return a new .
     */
    @objid ("77af9de5-daaa-4c52-9c81-91c961b6b76e")
    Activity createActivity();

    /**
     * @return a new .
     */
    @objid ("74317136-437b-40aa-bf27-81a9d16650b8")
    ActivityDiagram createActivityDiagram();

    /**
     * @param name the name of the ActivityDiagram to be created.
     * @param owner the ModelElement that will contain the ActivityDiagram.
     * @return An ActivityDiagram representing the diagram in the Model.
     */
    @objid ("b4430148-6664-4e9a-8ffc-6e0e296f1bed")
    ActivityDiagram createActivityDiagram(String name, ModelElement owner);

    /**
     * @return a new {@link ActivityFinalNode}.
     */
    @objid ("dc93afba-2bbd-4115-b8e0-ea8a29df2ccd")
    ActivityFinalNode createActivityFinalNode();

    /**
     * @return a new {@link ActivityParameterNode}.
     */
    @objid ("6c9b1556-80e8-4d3f-9137-d05cd675518b")
    ActivityParameterNode createActivityParameterNode();

    /**
     * @return a new {@link ActivityPartition}.
     */
    @objid ("8091c6d2-2f67-4e63-97c1-2aa5685c2345")
    ActivityPartition createActivityPartition();

    /**
     * @return a new {@link Actor}.
     */
    @objid ("08d6eb6a-406e-435c-b01a-ce2b364c9cc9")
    Actor createActor();

    /**
     * @param name The name of the Actor to be created.
     * @param owner The NameSpace that will contain the Actor.
     * @return An Actor representing the Actor in the Model.
     */
    @objid ("10d23c1c-b4db-4985-8f3f-1a38df8da6d8")
    Actor createActor(String name, NameSpace owner);

    /**
     * @param name The name of the Actor to be created.
     * @param owner The NameSpace that will contain the Actor.
     * @param stereotype the Stereotype that will be referred by the Actor.
     * @return An Actor representing the Actor in the Model.
     */
    @objid ("0ecebefe-d896-49f2-940d-6f783306794d")
    Actor createActor(String name, NameSpace owner, Stereotype stereotype);

    /**
     * @param name The name of the Actor to be created.
     * @param owner The NameSpace that will contain the Actor.
     * @param moduleName name of the {@link ModuleComponent module} owning the stereotype.
     * @param stereotypeName the name of the Stereotype that will be referred by the Actor.
     * @return An Actor representing the Actor in the Model.
     * @throws ExtensionNotFoundException if the stereotype is not found
     */
    @objid ("7aa04948-7e05-444c-a75c-27342c54d0d7")
    Actor createActor(String name, NameSpace owner, String moduleName, String stereotypeName) throws ExtensionNotFoundException;

    /**
     * Create a binary aggregation.
     * 
     * This method create a binary aggregation between two classifiers and give a name to the destination role. The created
     * aggregation has a navigable role.
     * @param source the source Classifier of the aggregation.
     * @param destination the destination Classifier of the aggregation.
     * @param destinationRoleName the Name of the destination role.
     * @return The link going from the source to the destination.
     */
    @objid ("7dc3277d-9291-4131-ab34-25e3b7cbaa13")
    Association createAggregation(Classifier source, Classifier destination, String destinationRoleName);

    /**
     * Creates a new {@link Artifact}.
     * @return a new {@link Artifact}.
     */
    @objid ("52df4bf7-4f56-42a0-9edb-04b53ca87b13")
    Artifact createArtifact();

    /**
     * @param name The name of the Artifact to be created.
     * @param owner The NameSpace that will contain the Artifact.
     * @return An Artifact representing the Artifact in the Model.
     */
    @objid ("34471768-c91f-4379-b898-908b81359e7a")
    Artifact createArtifact(String name, NameSpace owner);

    /**
     * @param name The name of the Artifact to be created.
     * @param owner The NameSpace that will contain the Artifact.
     * @param stereotype the Stereotype that will be referred by the Artifact.
     * @return An Artifact representing the Artifact in the Model.
     */
    @objid ("c9de004c-93cc-4eee-b8a2-2f4257180cf9")
    Artifact createArtifact(String name, NameSpace owner, Stereotype stereotype);

    @objid ("11dc4b1f-8cac-4ce7-a98a-916d62145169")
    Artifact createArtifact(String name, NameSpace owner, String moduleName, String stereotypeName) throws ExtensionNotFoundException;

    /**
     * Create an new {@link Association} without any {@link AssociationEnd role}.
     * @return a new Association.
     */
    @objid ("4b4b0d9d-057a-4110-b35b-f7fa8d40b5fd")
    Association createAssociation();

    /**
     * Create a binary association.
     * 
     * This method create a binary association between two classifiers and give a name to the destination role. The created
     * association has a navigable role.
     * @param source the source Classifier of the Association.
     * @param destination the destination Classifier of the Association.
     * @param destinationRoleName the Name of the destination role.
     * @return The link going from the source to the destination.
     */
    @objid ("f9eaee01-b61c-4f49-9699-e340fac99ae8")
    Association createAssociation(Classifier source, Classifier destination, String destinationRoleName);

    /**
     * Create an new association role.
     * @return a new {@link AssociationEnd}.
     */
    @objid ("fef657f1-b890-4669-83be-b221392845f5")
    AssociationEnd createAssociationEnd();

    /**
     * Creates an new {@link Attribute}
     * @return a new attribute.
     */
    @objid ("5b344e17-f4c7-430e-8bce-25596c4c7445")
    Attribute createAttribute();

    /**
     * @param name the name of the Attribute to be created.
     * @param type the GeneralClass that will type the Atrribute.
     * @param owner the Classifier that will contain the Attribute
     * @return An Attribute representing the Attribute in the Model.
     */
    @objid ("b26ec4c5-36a7-42c5-a00a-61fd91c4275d")
    Attribute createAttribute(String name, GeneralClass type, Classifier owner);

    /**
     * @param name the name of the Attribute to be created.
     * @param type the GeneralClass that will type the Attribute.
     * @param owner the Classifier that will contain the Attribute.
     * @param stereotype the name of the Stereotype that will be referred by the Attribute.
     * @return An Attribute representing the Attribute in the Model.
     */
    @objid ("3e8d7257-a8a8-4d8e-ba2b-a09c8683abf1")
    Attribute createAttribute(String name, GeneralClass type, Classifier owner, Stereotype stereotype);

    @objid ("a3322413-7e99-43dc-b098-2043a47afb6c")
    Attribute createAttribute(String name, GeneralClass type, Classifier owner, String moduleName, String stereotypeName) throws ExtensionNotFoundException;

    /**
     * @return a new AttributeLink.
     */
    @objid ("de49ee97-2059-471c-b011-3232891ba6d9")
    AttributeLink createAttributeLink();

    /**
     * @return a new BehaviorParameter.
     */
    @objid ("b12b61cc-6af8-4a32-a771-06335d16c8e2")
    BehaviorParameter createBehaviorParameter();

    /**
     * @return a new BindableInstance.
     */
    @objid ("4cf8ca7e-1c92-424a-b724-1c289593d299")
    BindableInstance createBindableInstance();

    /**
     * @return a new Binding.
     */
    @objid ("81f9c855-d9bc-4ae5-8a3c-d5198bf6924d")
    Binding createBinding();

    /**
     * @return a new BpmnActivity.
     */
    @objid ("66b8ff56-6dff-4276-961d-da9fc3407539")
    BpmnActivity createBpmnActivity();

    /**
     * @return a new BpmnAdHocSubProcess.
     */
    @objid ("9c48cd3d-3c50-41fd-9cdb-ab5828eed074")
    BpmnAdHocSubProcess createBpmnAdHocSubProcess();

    /**
     * @return a new BpmnAssociation.
     */
    @objid ("d6d59eca-33df-4749-8c99-8c9dbce31c37")
    BpmnAssociation createBpmnAssociation();

    /**
     * @return a new BpmnBoundaryEvent.
     */
    @objid ("5937bb93-84bc-437b-a455-7fc24304988d")
    BpmnBoundaryEvent createBpmnBoundaryEvent();

    /**
     * @return a new BpmnBusinessRuleTask.
     */
    @objid ("13120a01-21ca-4f75-a49f-1537e21f1367")
    BpmnBusinessRuleTask createBpmnBusinessRuleTask();

    /**
     * @return a new BpmnCallActivity.
     */
    @objid ("ac7f9fdf-e5bb-4166-ad9f-7270f1dbb5fb")
    BpmnCallActivity createBpmnCallActivity();

    /**
     * @return a new BpmnCancelEventDefinition.
     */
    @objid ("e195fbf3-e40d-454b-866c-f32335583ba6")
    BpmnCancelEventDefinition createBpmnCancelEventDefinition();

    /**
     * @return a new BpmnCollaboration.
     */
    @objid ("3e052b4d-2fd0-4b94-9ae1-93c8a06759b6")
    BpmnCollaboration createBpmnCollaboration();

    /**
     * @return a new {@link BpmnCollaborationDiagram}.
     */
    @objid ("0f21d3e9-7b7b-4124-882f-73a5093f5b1e")
    BpmnCollaborationDiagram createBpmnCollaborationDiagram();

    /**
     * @return a new BpmnCompensateEventDefinition.
     */
    @objid ("eb9951df-b8ca-4d42-95e6-6c3cf7247343")
    BpmnCompensateEventDefinition createBpmnCompensateEventDefinition();

    /**
     * @return a new BpmnComplexBehaviorDefinition.
     */
    @objid ("6b87ef66-d33d-44b0-ba14-36964983cc18")
    BpmnComplexBehaviorDefinition createBpmnComplexBehaviorDefinition();

    /**
     * @return a new BpmnComplexGateway.
     */
    @objid ("dff17a70-4606-4c9d-8fc8-565b4db442de")
    BpmnComplexGateway createBpmnComplexGateway();

    /**
     * @return a new BpmnConditionalEventDefinition.
     */
    @objid ("83405679-c50a-40c2-a150-6dd3802208bf")
    BpmnConditionalEventDefinition createBpmnConditionalEventDefinition();

    /**
     * @return a new BpmnDataAssociation.
     */
    @objid ("f6196e9e-56c1-4268-93f3-684a6dee5c8a")
    BpmnDataAssociation createBpmnDataAssociation();

    /**
     * @return a new BpmnDataInput.
     */
    @objid ("0fbeaaaf-4b6d-47b4-8b61-e0e241e4d06e")
    BpmnDataInput createBpmnDataInput();

    /**
     * @return a new BpmnDataObject.
     */
    @objid ("5dd5c038-6e62-487a-b683-92116a0da3e6")
    BpmnDataObject createBpmnDataObject();

    /**
     * @return a new BpmnDataOutput.
     */
    @objid ("e3c19cdd-43f4-4ae9-8003-a40600224ece")
    BpmnDataOutput createBpmnDataOutput();

    /**
     * @return a new BpmnDataState.
     */
    @objid ("2edcddb1-5706-4cd1-9d28-1b1b50060af1")
    BpmnDataState createBpmnDataState();

    /**
     * @return a new BpmnDataStore.
     */
    @objid ("b9ffc5ba-5cb9-4d38-aa62-c607f95e9ca6")
    BpmnDataStore createBpmnDataStore();

    /**
     * @return a new BpmnEndEvent.
     */
    @objid ("21c0b7f3-1490-4f93-b246-50a9ba931d33")
    BpmnEndEvent createBpmnEndEvent();

    /**
     * @return a new BpmnEndPoint.
     */
    @objid ("b936eb47-77a5-458d-9fb2-ba1340ac3b98")
    BpmnEndPoint createBpmnEndPoint();

    /**
     * @return a new BpmnErrorEventDefinition.
     */
    @objid ("94a00679-4199-471e-81b4-e0d02a40a7df")
    BpmnErrorEventDefinition createBpmnErrorEventDefinition();

    /**
     * @return a new BpmnEscalationEventDefinition.
     */
    @objid ("b1d7c72f-0493-4270-a15c-2840018d7092")
    BpmnEscalationEventDefinition createBpmnEscalationEventDefinition();

    /**
     * @return a new BpmnEventBasedGateway.
     */
    @objid ("8ea43718-2787-473f-85e7-5102df6b7bd7")
    BpmnEventBasedGateway createBpmnEventBasedGateway();

    /**
     * @return a new BpmnExclusiveGateway.
     */
    @objid ("e4158ae3-6ada-48c6-85fc-70a26c09a8c0")
    BpmnExclusiveGateway createBpmnExclusiveGateway();

    /**
     * @return a new BpmnGroup.
     */
    @objid ("cd49d57f-0091-4033-a1cb-b737547e9a7b")
    BpmnGroup createBpmnGroup();

    /**
     * @return a new BpmnImplicitThrowEvent.
     */
    @objid ("f68d5383-05a0-42b5-a574-693d24e8ffb9")
    BpmnImplicitThrowEvent createBpmnImplicitThrowEvent();

    /**
     * @return a new BpmnInclusiveGateway.
     */
    @objid ("14759f3a-a2a8-4f71-aadc-b14f84634d3c")
    BpmnInclusiveGateway createBpmnInclusiveGateway();

    /**
     * @return a new BpmnInterface.
     */
    @objid ("b4ea198c-c2f0-4454-88d5-7e43745850f6")
    BpmnInterface createBpmnInterface();

    /**
     * @return a new BpmnIntermediateCatchEvent.
     */
    @objid ("4abc0d7b-517f-41b7-beff-8b09fe0d2fba")
    BpmnIntermediateCatchEvent createBpmnIntermediateCatchEvent();

    /**
     * @return a new BpmnIntermediateThrowEvent.
     */
    @objid ("4ce7fb9d-2a61-485b-9b17-4e2da2a2ebf5")
    BpmnIntermediateThrowEvent createBpmnIntermediateThrowEvent();

    /**
     * @return a new {@link BpmnItemDefinition} .
     */
    @objid ("1ec8f2a5-c4c9-4c7c-b420-fc68782fc1cd")
    BpmnItemDefinition createBpmnItemDefinition();

    /**
     * @return a new BpmnLane.
     */
    @objid ("e6bebf13-b15b-4dbe-afb7-7569960bead3")
    BpmnLane createBpmnLane();

    /**
     * @return a new BpmnLaneSet.
     */
    @objid ("7389f606-d22d-4bdd-a95d-cb513b9b19d9")
    BpmnLaneSet createBpmnLaneSet();

    /**
     * @return a new BpmnLinkEventDefinition.
     */
    @objid ("30e264d9-7a1f-4679-8784-5e807af157ee")
    BpmnLinkEventDefinition createBpmnLinkEventDefinition();

    /**
     * @return a new {@link BpmnManualTask}.
     */
    @objid ("ac880452-c888-4393-9636-27f653d4dd59")
    BpmnManualTask createBpmnManualTask();

    /**
     * @return a new {@link BpmnMessage}.
     */
    @objid ("7e74a77a-1d0f-426f-89cf-4d1fa9e0272b")
    BpmnMessage createBpmnMessage();

    /**
     * @return a new {@link BpmnMessageEventDefinition} .
     */
    @objid ("fcb37e3a-d470-426e-ba3d-3f664be941ed")
    BpmnMessageEventDefinition createBpmnMessageEventDefinition();

    /**
     * @return a new {@link BpmnMessageFlow}.
     */
    @objid ("08f82500-480c-48d4-b4bb-4ab03285660f")
    BpmnMessageFlow createBpmnMessageFlow();

    /**
     * @return a new {@link BpmnMultiInstanceLoopCharacteristics}.
     */
    @objid ("ff874747-5fb7-4e00-ae58-17bee25683c0")
    BpmnMultiInstanceLoopCharacteristics createBpmnMultiInstanceLoopCharacteristics();

    /**
     * @return a new {@link BpmnOperation}.
     */
    @objid ("a2ec43a8-cd45-478f-9e58-c8f0ca58cef2")
    BpmnOperation createBpmnOperation();

    /**
     * @return a new {@link BpmnParallelGateway} .
     */
    @objid ("e45d4251-9d8d-4991-915a-b21cdad23e28")
    BpmnParallelGateway createBpmnParallelGateway();

    /**
     * @return a new {@link BpmnParticipant}.
     */
    @objid ("9a871927-5bb6-4d2f-9fd5-123b45add504")
    BpmnParticipant createBpmnParticipant();

    /**
     * @return a new {@link BpmnProcess}.
     */
    @objid ("a1039882-1c93-4837-a147-ffce855b5a39")
    BpmnProcess createBpmnProcess();

    /**
     * @return a new {@link BpmnProcessDesignDiagram}.
     */
    @objid ("8447f99c-5aa1-4eaf-818e-c179b3053f18")
    BpmnProcessDesignDiagram createBpmnProcessDesignDiagram();

    /**
     * @return a new {@link BpmnReceiveTask}.
     */
    @objid ("ab566dbc-0189-471c-8082-43f98d5d82e3")
    BpmnReceiveTask createBpmnReceiveTask();

    /**
     * @return a new {@link BpmnResource}.
     */
    @objid ("d3c0d667-bb0e-4bfd-baa3-81eee26e5f64")
    BpmnResource createBpmnResource();

    /**
     * @return a new {@link BpmnResourceParameter}.
     */
    @objid ("b508d618-426a-4512-8b87-09b2b35b1958")
    BpmnResourceParameter createBpmnResourceParameter();

    /**
     * @return a new {@link BpmnResourceParameterBinding}.
     */
    @objid ("4bd9c714-1912-4448-b1e3-2f7dbfdd9250")
    BpmnResourceParameterBinding createBpmnResourceParameterBinding();

    /**
     * @return a new {@link BpmnResourceRole}.
     */
    @objid ("bef9253b-fc98-4c00-8a3d-41fd54186d27")
    BpmnResourceRole createBpmnResourceRole();

    /**
     * @return a new {@link BpmnScriptTask}.
     */
    @objid ("088d146b-254e-4931-9923-8474094b6cc4")
    BpmnScriptTask createBpmnScriptTask();

    /**
     * @return a new {@link BpmnSendTask}.
     */
    @objid ("29c8d75c-ce3f-441c-81c9-805269c43457")
    BpmnSendTask createBpmnSendTask();

    /**
     * @return a new {@link BpmnSequenceFlow}.
     */
    @objid ("401b4c78-f86b-4b25-a4ee-02b896cdc0c3")
    BpmnSequenceFlow createBpmnSequenceFlow();

    /**
     * @return a new {@link BpmnSequenceFlowDataAssociation}.
     */
    @objid ("e6208307-db2c-4842-9c5b-9112bbce5caf")
    BpmnSequenceFlowDataAssociation createBpmnSequenceFlowDataAssociation();

    /**
     * @return a new {@link BpmnServiceTask}.
     */
    @objid ("fa9ed6bf-8fe0-4347-82a3-ec13377422cb")
    BpmnServiceTask createBpmnServiceTask();

    /**
     * @return a new BpmnSharedDefinitions.
     */
    @objid ("de24ce48-a0f1-445f-9680-940c1e7c7688")
    BpmnSharedDefinitions createBpmnSharedDefinitions();

    /**
     * @return a new {@link BpmnSignalEventDefinition}.
     */
    @objid ("aae6314b-cd8f-4ecc-a42b-52697b5d0c46")
    BpmnSignalEventDefinition createBpmnSignalEventDefinition();

    /**
     * @return a new {@link BpmnStandardLoopCharacteristics}.
     */
    @objid ("4bf518b4-7e91-46d8-a694-815e32f09545")
    BpmnStandardLoopCharacteristics createBpmnStandardLoopCharacteristics();

    /**
     * @return a new {@link BpmnStartEvent}.
     */
    @objid ("6d80118d-d265-4029-bafa-1eeaae9b48ff")
    BpmnStartEvent createBpmnStartEvent();

    /**
     * @return a new {@link BpmnSubProcess}.
     */
    @objid ("7ba0da0f-5727-4baf-92bd-ac5d7f6efac6")
    BpmnSubProcess createBpmnSubProcess();

    /**
     * @return a new {@link BpmnSubProcessDiagram}.
     */
    @objid ("a13e2a49-ea97-4876-9447-41032518ef55")
    BpmnSubProcessDiagram createBpmnSubProcessDiagram();

    /**
     * @return a new {@link BpmnTask}.
     */
    @objid ("12f748cc-27aa-41d0-9af6-2f9dc716594f")
    BpmnTask createBpmnTask();

    /**
     * @return a new {@link BpmnTerminateEventDefinition}.
     */
    @objid ("e9bbb21f-2bb6-4fbf-aa47-71e697cf4951")
    BpmnTerminateEventDefinition createBpmnTerminateEventDefinition();

    /**
     * @return a new {@link BpmnTimerEventDefinition}.
     */
    @objid ("00ff191a-415c-47ed-8f2a-93dc8e39638e")
    BpmnTimerEventDefinition createBpmnTimerEventDefinition();

    /**
     * @return a new {@link BpmnTransaction}.
     */
    @objid ("759f5f37-f3f2-413c-a02e-6b28fd40c540")
    BpmnTransaction createBpmnTransaction();

    /**
     * @return a new {@link BpmnUserTask}.
     */
    @objid ("2916672b-19cd-4ce7-83d4-5b154acedb81")
    BpmnUserTask createBpmnUserTask();

    /**
     * @return a new {@link CallBehaviorAction}.
     */
    @objid ("39151299-7b4f-4d36-ac63-1e5ff5c9fd91")
    CallBehaviorAction createCallBehaviorAction();

    /**
     * @return a new {@link CallOperationAction}.
     */
    @objid ("08d84ca6-430a-4501-8a56-77dd9f530c63")
    CallOperationAction createCallOperationAction();

    /**
     * @return a new {@link CentralBufferNode}.
     */
    @objid ("6e722bf4-9971-4619-9cf6-2bc5d3cc7ecb")
    CentralBufferNode createCentralBufferNode();

    /**
     * @return a {@link ChoicePseudoState}
     */
    @objid ("437fabfb-0df9-4cbe-8226-a95020e663c1")
    ChoicePseudoState createChoicePseudoState();

    /**
     * Creates an empty, unnamed {@link Class UML class}.
     * @return a new Class.
     */
    @objid ("2cf6bb7d-ff45-4cd7-94e5-39392096c1d4")
    Class createClass();

    /**
     * @param name the name of the Class to create.
     * @param owner the NameSpace that will contain the Class.
     * @return An Class representing the Class in the Model.
     */
    @objid ("8f5d89d6-6034-4312-bef5-64ab9e342130")
    Class createClass(String name, NameSpace owner);

    /**
     * @param name the name of the Class to create.
     * @param owner the NameSpace that will contain the Class.
     * @param stereotype the Stereotype to refer on the class.
     * @return An Class representing the Class in the Model.
     */
    @objid ("dfd7a8df-7ba2-4a79-bcbd-9fc25484e95b")
    Class createClass(String name, NameSpace owner, Stereotype stereotype);

    @objid ("6d0e925d-4418-4577-ae0c-6f3f541cf235")
    Class createClass(String name, NameSpace owner, String moduleName, String stereotypeName) throws ExtensionNotFoundException;

    /**
     * Create a {@link ClassAssociation}
     * @return a Class association.
     */
    @objid ("7d4b1edf-e39e-4381-885f-088180cf8622")
    ClassAssociation createClassAssociation();

    /**
     * Create a Class Diagram.
     * @return a Class diagram
     */
    @objid ("c4a87ecb-90d9-4ea0-951b-fa51b31194ca")
    ClassDiagram createClassDiagram();

    /**
     * Create a Class Diagram.
     * @param name the name of the Class diagram to be created.
     * @param contextElement the element representing the Object Diagram 'background' for element creation in the diagram. null is not allowed.
     * @param stereotype the Stereotype that will be referred by the diagram.
     * @return An Class Diagram representing the diagram in the Model.
     */
    @objid ("67161de4-adc7-43b0-a5b4-7e7833fab3a7")
    ClassDiagram createClassDiagram(final String name, final ModelElement contextElement, final Stereotype stereotype);

    /**
     * Creates a {@link ConditionalNode} {@link Clause}.
     * @return a new {@link Clause}.
     */
    @objid ("9025c368-4c1b-4977-a10d-a4283a9dadbf")
    Clause createClause();

    /**
     * @return a new {@link Collaboration}
     */
    @objid ("6496fe5b-33ff-4cfa-8df2-5f4cfbc0a7ed")
    Collaboration createCollaboration();

    /**
     * @return a new {@link CollaborationUse}
     */
    @objid ("9a427312-68cc-4c7a-9f12-17c3356094c3")
    CollaborationUse createCollaborationUse();

    /**
     * @return a {@link CombinedFragment}
     */
    @objid ("fc8f80d9-a925-474c-8f33-a47379b6f027")
    CombinedFragment createCombinedFragment();

    /**
     * @param operator the operator of the CombinedFragment to create.
     * @return An CombinedFragment representing the CombinedFragment in the Model.
     */
    @objid ("c7949f69-28ad-473d-91ba-8ae7ae9b44c9")
    CombinedFragment createCombinedFragment(InteractionOperator operator);

    /**
     * @return a new {@link CommunicationChannel }.
     */
    @objid ("1c2861a7-5537-44fe-89be-e8fe43f5d8c5")
    CommunicationChannel createCommunicationChannel();

    /**
     * @return a new {@link CommunicationDiagram} .
     */
    @objid ("30294325-35d2-4a68-be0f-214d55199757")
    CommunicationDiagram createCommunicationDiagram();

    /**
     * Create a Communication Diagram.
     * 
     * The returned object is a CommunicationDiagram stereotyped <<communication>> named by 'name'
     * @param name the name of the Communication diagram to be created.
     * @param contextElement the element representing the Object Diagram 'background' for element creation in the diagram. null is not allowed.
     * @return An CommunicationDiagram representing the diagram in the Model.
     */
    @objid ("79866c8d-5d2b-4a7b-bd09-1bad4c69a59c")
    CommunicationDiagram createCommunicationDiagram(String name, ModelElement contextElement);

    /**
     * Create a Communication Diagram.
     * @param name the name of the Communication diagram to be created.
     * @param contextElement the element representing the Object Diagram 'background' for element creation in the diagram. null is not allowed.
     * @param stereotype the Stereotype that will be referred by the diagram.
     * @return A CommunicationDiagram representing the diagram in the Model.
     */
    @objid ("1401bacc-e320-4183-93d4-0fb37a7c7e8b")
    CommunicationDiagram createCommunicationDiagram(final String name, final ModelElement contextElement, final Stereotype stereotype);

    /**
     * @return a new {@link CommunicationInteraction}.
     */
    @objid ("435cac0c-d4ed-42cd-b3a8-f178e8a36c1c")
    CommunicationInteraction createCommunicationInteraction();

    /**
     * @return a new {@link CommunicationMessage}.
     */
    @objid ("e65c2e35-5c6c-485f-88af-122d4caabd7d")
    CommunicationMessage createCommunicationMessage();

    /**
     * @return a new {@link CommunicationNode}.
     */
    @objid ("f7d03d77-73ee-416d-8f0b-38df93e3f1f3")
    CommunicationNode createCommunicationNode();

    /**
     * @return a new {@link Component}.
     */
    @objid ("6b7d62cc-7c29-402d-b752-3ed676c05b3e")
    Component createComponent();

    /**
     * @param name the name of the Component to create.
     * @param owner the NameSpace that will contain the Component.
     * @return An Component representing the Component in the Model.
     */
    @objid ("8049e87e-6fa5-4590-8aed-a99df2f5b42e")
    Component createComponent(String name, NameSpace owner);

    /**
     * @param name the name of the Component to create.
     * @param owner the NameSpace that will contain the Component.
     * @param stereotype the Stereotype to refer on the Component.
     * @return A Component representing the Component in the Model.
     */
    @objid ("269b4e6c-7b7c-4ad6-b7da-a1da493c7124")
    Component createComponent(String name, NameSpace owner, Stereotype stereotype);

    @objid ("1f249131-d57e-46d1-8aea-dbd57c5cd6e5")
    Component createComponent(String name, NameSpace owner, String moduleName, String stereotypeName) throws ExtensionNotFoundException;

    /**
     * Create an empty Component realization link.
     * @return a ComponentRealization.
     */
    @objid ("1ac0bdc7-00a1-4f1c-ad0b-2c433bbc6efc")
    ComponentRealization createComponentRealization();

    /**
     * Create a {@link ComponentRealization} from a {@link Classifier} to a {@link Component} realizing the Component.
     * <p>
     * Note : a ComponentRelaization is owned by the target {@link Component}.
     * @param source the origin of the ComponentRealization.
     * @param destination the destination of the ComponentRealization
     * @return An ComponentRealization representing the ComponentRealization in the Model.
     */
    @objid ("885827dd-7550-44c5-8c4b-666425ae4011")
    ComponentRealization createComponentRealization(Classifier source, Component destination);

    /**
     * @return a new {@link CompositeStructureDiagram}.
     */
    @objid ("be8e5940-9c8b-4073-b07c-b68f314c9822")
    CompositeStructureDiagram createCompositeStructureDiagram();

    /**
     * Create a CompositeStructure Diagram.
     * @param name the name of the CompositeStructure diagram to be created.
     * @param contextElement the element representing the CompositeStructure Diagram 'background' for element creation in the diagram. null is
     * not allowed.
     * @param stereotype the Stereotype that will be referred by the diagram.
     * @return An CompositeStructureDiagram representing the diagram in the Model.
     */
    @objid ("897a0acf-0134-40ae-900c-d16baecb7ebd")
    CompositeStructureDiagram createCompositeStructureDiagram(final String name, final ModelElement contextElement, final Stereotype stereotype);

    /**
     * Create a binary composition.
     * 
     * This method create a binary composition between two classifiers and give a name to the destination role. The created
     * composition has a navigable role.
     * @param source the source Classifier of the composition.
     * @param destination the destination Classifier of the composition.
     * @param destinationRoleName the Name of the destination role.
     * @return The Association the link going from the source to the destination.
     */
    @objid ("3a39eade-67e4-48c8-906b-ac08c6d32ae9")
    Association createComposition(Classifier source, Classifier destination, String destinationRoleName);

    /**
     * @return a new .
     */
    @objid ("e0228d6d-5aaf-4a46-be01-2343dad61f12")
    ConditionalNode createConditionalNode();

    /**
     * @return a new .
     */
    @objid ("6d65b90f-1543-414a-ba82-f8891feade3a")
    ConnectionPointReference createConnectionPointReference();

    /**
     * Creates a defined {@link Connector}.
     * @param source the source {@link BindableInstance part}
     * @param destination the destination {@link BindableInstance part}
     * @param destinationRoleName the target connector role name.
     * @return a new Connector.
     */
    @objid ("a5366e87-6964-4d9b-8612-3bb528230058")
    Connector createConnector(BindableInstance source, BindableInstance destination, String destinationRoleName);

    /**
     * @return a new {@link Connector}.
     */
    @objid ("1d1c2636-24cf-473b-82bc-00dfda3222fd")
    Connector createConnector();

    /**
     * @return a new {@link ConnectorEnd}.
     */
    @objid ("8e56c7a7-cc97-406e-8d96-19bc1568106e")
    ConnectorEnd createConnectorEnd();

    /**
     * @return a new {@link Constraint}.
     */
    @objid ("07a75aab-f9a8-43a8-8e8d-23c97e4491f0")
    Constraint createConstraint();

    /**
     * @return a new {@link ControlFlow}.
     */
    @objid ("7150ac0d-911f-4c31-8184-2e4bfaca58ce")
    ControlFlow createControlFlow();

    /**
     * @return a new {@link DataFlow}.
     */
    @objid ("6fe6fb2f-541e-4270-8e2c-40094fbad9df")
    DataFlow createDataFlow();

    /**
     * @return a new {@link DataStoreNode}.
     */
    @objid ("ef44cf5e-719c-48eb-8caf-2a49409b984d")
    DataStoreNode createDataStoreNode();

    /**
     * Creates a new {@link DataType data type}
     * @return a new {@link DataType}.
     */
    @objid ("745ed4d4-0838-4c23-97e3-b6c8f297cc69")
    DataType createDataType();

    /**
     * Creates a new {@link DataType data type}
     * @param name the name of the DataType to create.
     * @param owner the NameSpace that will contain the DataType.
     * @return An DataType representing the DataType in the Model.
     */
    @objid ("76d5ae1d-14b5-479d-9a25-a5994f5b6010")
    DataType createDataType(String name, NameSpace owner);

    /**
     * Creates a new {@link DataType data type}
     * @param name the name of the DataType to create.
     * @param owner the NameSpace that will contain the DataType.
     * @param stereotype the stereotype that will extend the DataType.
     * @return An DataType representing the DataType in the Model.
     */
    @objid ("272624ac-c8e4-4610-aaef-3d2b7c7a6e53")
    DataType createDataType(String name, NameSpace owner, Stereotype stereotype);

    @objid ("9226a29a-8c5b-4d19-a76c-7386a7850708")
    DataType createDataType(String name, NameSpace owner, String moduleName, String stereotypeName) throws ExtensionNotFoundException;

    /**
     * @return a new {@link DecisionMergeNode} .
     */
    @objid ("2554e707-9f0b-4777-8aab-9042c632e063")
    DecisionMergeNode createDecisionMergeNode();

    /**
     * @return a new {@link DeepHistoryPseudoState}.
     */
    @objid ("09ef7b22-c627-4b3e-b829-a9f393c6b480")
    DeepHistoryPseudoState createDeepHistoryPseudoState();

    /**
     * Creates a new {@link DeploymentDiagram deployment diagram}.
     * @return a new {@link DeploymentDiagram}.
     */
    @objid ("e7489d79-611e-4e95-bce3-27a2c277c4a1")
    DeploymentDiagram createDeploymentDiagram();

    /**
     * Create a Deployment Diagram.
     * 
     * The returned object is a DeploymentDiagram stereotyped <<deployment>> named by 'name'
     * @param name the name of the Deployment diagram to be created.
     * @param contextElement the element representing the Deployment Diagram 'background' for element creation in the diagram. null is not
     * allowed.
     * @return A DeploymentDiagram representing the diagram in the Model.
     */
    @objid ("221b88d9-daee-4f44-a05a-0f840c3eb294")
    DeploymentDiagram createDeploymentDiagram(String name, ModelElement contextElement);

    /**
     * Create a Deployment Diagram.
     * @param name the name of the Deployment diagram to be created.
     * @param contextElement the element representing the Deployment Diagram 'background' for element creation in the diagram. null is not
     * allowed.
     * @param stereotype the Stereotype that will be referred by the diagram.
     * @return A DeploymentDiagram representing the diagram in the Model.
     */
    @objid ("06a4ed8f-8296-4e44-b934-e352f7f4b882")
    DeploymentDiagram createDeploymentDiagram(final String name, final ModelElement contextElement, final Stereotype stereotype);

    /**
     * @return a new {@link DurationConstraint}.
     */
    @objid ("07268314-8164-4634-aaa8-e1b762d93080")
    DurationConstraint createDurationConstraint();

    /**
     * @return a new {@link ElementImport}.
     */
    @objid ("44b662b0-c4e1-4aef-83e4-f4a41e0ea8a3")
    ElementImport createElementImport();

    /**
     * @param source origin of the ElementImport
     * @param destination destination of the ElementImport
     * @return An ElementImport representing the ElementImport in the Model.
     */
    @objid ("e8ec5095-7db5-4cb1-8f10-fbd05b776750")
    ElementImport createElementImport(NameSpace source, NameSpace destination);

    /**
     * @param source origin of the ElementImport
     * @param destination destination of the ElementImport
     * @return An ElementImport representing the ElementImport in the Model.
     */
    @objid ("e301575b-c0f4-41a6-b3d0-a477c7fa30b4")
    ElementImport createElementImport(Operation source, NameSpace destination);

    /**
     * @return a new {@link ElementRealization}.
     */
    @objid ("2335f4ef-0e12-4005-add1-e76567e9441e")
    ElementRealization createElementRealization();

    /**
     * @return a new {@link EntryPointPseudoState}.
     */
    @objid ("7ca1e8d1-9b16-4ce8-8d95-b88250a0b2d1")
    EntryPointPseudoState createEntryPointPseudoState();

    /**
     * @return a new {@link Enumeration}.
     */
    @objid ("d22ed52d-05a5-4994-8b13-e1ec1254cb31")
    Enumeration createEnumeration();

    /**
     * @param name the name of the Enumeration to create.
     * @param owner the NameSpace that will contain the Enumeration
     * @return An Enumeration representing the Enumeration in the Model.
     */
    @objid ("5cd8edcb-34dd-48de-84ef-c544646a2c20")
    Enumeration createEnumeration(String name, NameSpace owner);

    /**
     * @param name the name of the Enumeration to create.
     * @param owner the NameSpace that will contain the Enumeration.
     * @param stereotype tthe Stereotype that will extend the Enumeration.
     * @return An Enumeration representing the Enumeration in the Model.
     */
    @objid ("31015dc2-b82e-4020-903a-da2e6c589888")
    Enumeration createEnumeration(String name, NameSpace owner, Stereotype stereotype);

    @objid ("636f8ae0-6dd7-4cd8-bb95-17bbb192a614")
    Enumeration createEnumeration(String name, NameSpace owner, String moduleName, String stereotypeName) throws ExtensionNotFoundException;

    /**
     * @return a new {@link EnumerationLiteral }.
     */
    @objid ("e97d9257-ddea-402e-8480-ef213f098c78")
    EnumerationLiteral createEnumerationLiteral();

    /**
     * @param name the name of the EnumerationLiteral to create.
     * @param owner the Enumeration that will contain the EnumerationLiteral
     * @return An Enumeration representing the EnumerationLiteral in the Model.
     */
    @objid ("573f32f9-8332-452b-a682-ad1b9b1b74f2")
    EnumerationLiteral createEnumerationLiteral(String name, Enumeration owner);

    /**
     * @param name the name of the EnumerationLiteral to create.
     * @param owner the Enumeration that will contain the EnumerationLiteral.
     * @param stereotype the Stereotype that will extend the EnumerationLiteral.
     * @return An Enumeration representing the Enumeration in the Model.
     */
    @objid ("e2a8b0b5-345d-49bb-98c1-6f068f99ad4f")
    EnumerationLiteral createEnumerationLiteral(String name, Enumeration owner, Stereotype stereotype);

    @objid ("cd69d34b-47d4-4426-a3e6-cb30baf476e9")
    EnumerationLiteral createEnumerationLiteral(String name, Enumeration owner, String moduleName, String stereotypeName) throws ExtensionNotFoundException;

    /**
     * @return a new {@link Event}.
     */
    @objid ("2e0237f0-7ad1-4de5-a203-0d6d69c57e14")
    Event createEvent();

    /**
     * @return a new {@link ExceptionHandler}.
     */
    @objid ("27af03f5-3e88-4163-8a7d-f4fcca43b61a")
    ExceptionHandler createExceptionHandler();

    /**
     * @return a new {@link ExecutionOccurenceSpecification}.
     */
    @objid ("d4c43a91-0571-47b7-b0f6-d8bb3d2f148a")
    ExecutionOccurenceSpecification createExecutionOccurenceSpecification();

    /**
     * @return a new {@link ExecutionSpecification}.
     */
    @objid ("be1c60b4-a7f9-41e8-be74-7cf698e202e8")
    ExecutionSpecification createExecutionSpecification();

    /**
     * @return a new {@link ExitPointPseudoState}.
     */
    @objid ("d5448e74-cb01-439b-8b45-c501023c7ca2")
    ExitPointPseudoState createExitPointPseudoState();

    /**
     * @return a new {@link ExpansionNode}.
     */
    @objid ("3509071e-e0e0-4573-a8af-2bf464790281")
    ExpansionNode createExpansionNode();

    /**
     * @return a new {@link ExpansionRegion}.
     */
    @objid ("793f9650-1c1a-4705-9a2e-fa66b00346ab")
    ExpansionRegion createExpansionRegion();

    /**
     * Creates an "extends" stereotyped {@link UseCaseDependency}.
     * @param source the origin of the UseCaseDependency
     * @param destination the destination of the UseCaseDependency
     * @return An UseCaseDependency representing the UseCaseDependency in the Model.
     * @throws ExtensionNotFoundException in the unlikely case where the "Extends" stereotype is missing.
     */
    @objid ("b7c20727-93c4-47bd-983b-8a549d51f7fd")
    UseCaseDependency createExtendUseCaseDependency(UseCase source, UseCase destination) throws ExtensionNotFoundException;

    /**
     * Creates a use case extension point.
     * @return a new {@link ExtensionPoint}.
     */
    @objid ("75e6cbc7-67d3-4c45-8f6e-55a2700fe8ec")
    ExtensionPoint createExtensionPoint();

    /**
     * @return a new {@link FinalState}.
     */
    @objid ("06a72cf3-11fd-4988-8768-f63f24f029b6")
    FinalState createFinalState();

    /**
     * @return a new {@link FlowFinalNode}.
     */
    @objid ("ac127e50-c8e0-46af-bfe0-3c3fe9576a97")
    FlowFinalNode createFlowFinalNode();

    /**
     * @return a new {@link ForkJoinNode}.
     */
    @objid ("e49530e7-504b-441e-bdc7-35ee8b06f17b")
    ForkJoinNode createForkJoinNode();

    /**
     * @return a new {@link ForkPseudoState }.
     */
    @objid ("4a85fc19-9ed7-4a77-ba4d-0a8e40d436b6")
    ForkPseudoState createForkPseudoState();

    /**
     * @return a new {@link Gate}.
     */
    @objid ("1bbf76dc-05ba-4544-bcbb-3f2821ba2b46")
    Gate createGate();

    /**
     * @param name the name of the Gate.
     * @return An Gate representing the Gate in the Model.
     */
    @objid ("42c378b8-09c0-4527-9d55-58db076f3108")
    Gate createGate(String name);

    /**
     * @return a new {@link GeneralOrdering}.
     */
    @objid ("a79c1e07-fba3-47a8-b9b1-3c7d40717874")
    GeneralOrdering createGeneralOrdering();

    /**
     * Creates an inheritance link.
     * @return a new {@link Generalization}.
     */
    @objid ("8498f9f4-33bc-4e63-ba3c-cc6c7c871fca")
    Generalization createGeneralization();

    /**
     * Creates an inheritance link.
     * @param source the child element of the Generalization
     * @param destination the parent element of the Generalization
     * @return An Generalization representing the Generalization in the Model.
     */
    @objid ("43c16be4-39ca-4d69-8f22-3b39bc9f1474")
    Generalization createGeneralization(NameSpace source, NameSpace destination);

    /**
     * Creates an {@link Operation operation} {@link Parameter}.
     * @param name the name of the Parameter to create.
     * @param type the type of the Parameter to create.
     * @param owner the Operation that will contain the Parameter.
     * @return An Parameter representing the Parameter in the Model.
     */
    @objid ("9a14f618-4200-4249-b70f-e287697b817b")
    Parameter createIOParameter(String name, GeneralClass type, Operation owner);

    /**
     * Creates an {@link Operation operation} {@link Parameter}.
     * @param name the name of the Parameter to create.
     * @param type the type of the Parameter to create.
     * @param owner the Operation that will contain the Parameter.
     * @param stereotype the stereotype that will extend the Parameter.
     * @return An Parameter representing the Parameter in the Model.
     */
    @objid ("8123f684-8fc6-40aa-8f7d-b716e236ff12")
    Parameter createIOParameter(String name, GeneralClass type, Operation owner, Stereotype stereotype);

    @objid ("ba12021e-0590-4ed9-af6b-4c1d02dc30a7")
    Parameter createIOParameter(String name, GeneralClass type, Operation owner, String moduleName, String stereotypeName) throws ExtensionNotFoundException;

    /**
     * @param source the origin element of the UseCaseDependency
     * @param destination the destination element of the UseCaseDependency
     * @return An UseCaseDependency representing the UseCaseDependency in the Model.
     * @throws ExtensionNotFoundException in the unlikely case where the "include" stereotype is missing.
     */
    @objid ("4b3d1256-7bb2-48de-826c-cfcf6e3d8e09")
    UseCaseDependency createIncludeUseCaseDependency(UseCase source, UseCase destination) throws ExtensionNotFoundException;

    /**
     * @return a new {@link InformationFlow}.
     */
    @objid ("0c75a98e-f8c1-491d-a586-4bed4fdf9909")
    InformationFlow createInformationFlow();

    /**
     * @return a new {@link InformationItem}.
     */
    @objid ("f7318465-9c95-497f-9135-7106d53c857e")
    InformationItem createInformationItem();

    /**
     * @return a new {@link InitialNode}.
     */
    @objid ("42fb1d73-debc-4ba4-8aff-52dc19aefdba")
    InitialNode createInitialNode();

    /**
     * @return a new {@link InitialPseudoState}.
     */
    @objid ("d0a6f607-355c-4819-bdbb-7d79cc29be91")
    InitialPseudoState createInitialPseudoState();

    /**
     * @return a new {@link InputPin}.
     */
    @objid ("a420223b-6adf-41ea-9659-acf3b064fb92")
    InputPin createInputPin();

    /**
     * @return a new {@link Instance}.
     */
    @objid ("23a1a3e6-40e8-47ee-a926-9e06ec7b09d9")
    Instance createInstance();

    /**
     * @param name the name of the Instance to create.
     * @param owner the Package that will conbtain the Port.
     * @return an Instance representing the Instance in the Model.
     */
    @objid ("2d0044ed-5341-4a72-b0cf-73982d152903")
    Instance createInstance(String name, Package owner);

    /**
     * @return a new {@link InstanceNode}.
     */
    @objid ("cbd02705-cec1-4e52-affa-95b8a60b79bd")
    InstanceNode createInstanceNode();

    /**
     * @return a new {@link Interaction}.
     */
    @objid ("613c8f8d-46d7-4848-8888-65d02a83f7c9")
    Interaction createInteraction();

    /**
     * @return a new {@link InteractionOperand}.
     */
    @objid ("b7e8af80-53c4-4cc4-9da3-6bb5e264e319")
    InteractionOperand createInteractionOperand();

    /**
     * @param guard the guard condition of the InteractionOperand to create.
     * @return An InteractionOperand representing the InteractionOperand in the Model.
     */
    @objid ("dd5d7f1a-84e8-48a1-97a6-0d85c76abaa0")
    InteractionOperand createInteractionOperand(String guard);

    /**
     * @return a new {@link InteractionUse}.
     */
    @objid ("1fe3fa61-d7e4-44b7-875e-f504ec971493")
    InteractionUse createInteractionUse();

    /**
     * @param referred the referred Interaction.
     * @return An InteractionUse representing the InteractionUse in the Model.
     */
    @objid ("a6a69062-d98b-4670-a42b-35c3b49cb715")
    InteractionUse createInteractionUse(Interaction referred);

    /**
     * @return a new {@link Interface}.
     */
    @objid ("013a28b3-a055-47da-8b3a-f2846977287e")
    Interface createInterface();

    /**
     * @param name the name of the Interface to create.
     * @param owner the NameSpace that will contain the Interface.
     * @return An Interface representing the Interface in the Model.
     */
    @objid ("167df904-720d-4886-913a-54e40c99f150")
    Interface createInterface(String name, NameSpace owner);

    /**
     * @param name the name of the Interface to create.
     * @param owner the NameSpace that will contain the Interface.
     * @param stereotype the stereotype that will extend the Interface.
     * @return An Interface representing the Interface in the Model.
     */
    @objid ("dc1416b3-ded6-4a99-b584-5b90450679da")
    Interface createInterface(String name, NameSpace owner, Stereotype stereotype);

    @objid ("1f8c9df1-9cce-494c-ac4c-6fae97297030")
    Interface createInterface(String name, NameSpace owner, String moduleName, String stereotypeName) throws ExtensionNotFoundException;

    /**
     * @return a new {@link InterfaceRealization }.
     */
    @objid ("5835fb4c-fb6c-47cd-983d-14d0bda04784")
    InterfaceRealization createInterfaceRealization();

    /**
     * @param source the origin of the InterfaceRealization.
     * @param destination the destination of the InterfaceRealization
     * @return An InterfaceRealization representing the InterfaceRealization in the Model.
     */
    @objid ("ab9fa63d-e708-48e9-a31d-91623ebcb2f9")
    InterfaceRealization createInterfaceRealization(NameSpace source, Interface destination);

    /**
     * @return a new {@link InternalTransition}.
     */
    @objid ("9543465d-2f3f-4ea0-baca-39864411cb1e")
    InternalTransition createInternalTransition();

    /**
     * @return a new {@link InterruptibleActivityRegion}.
     */
    @objid ("b96992ef-95bd-4cee-9608-9092c08a8aa7")
    InterruptibleActivityRegion createInterruptibleActivityRegion();

    /**
     * @return a new {@link JoinPseudoState}.
     */
    @objid ("2cee4e62-cee5-4e59-9ee0-d9e4374264ad")
    JoinPseudoState createJoinPseudoState();

    /**
     * @return a new {@link JunctionPseudoState}.
     */
    @objid ("cd1a180f-f335-48db-be28-2f8ea6aa9a05")
    JunctionPseudoState createJunctionPseudoState();

    /**
     * @return a new {@link Lifeline }.
     */
    @objid ("c76a6ee3-e899-4297-93c7-b4248e911e4d")
    Lifeline createLifeline();

    /**
     * @param name the name of the Lifeline to create.
     * @param owner the owner Interaction of the Lifeline to create.
     * @return An Lifeline representing the Lifeline in the Model.
     */
    @objid ("f8f648c2-5316-4ef4-ab6e-c9934b1f10c7")
    Lifeline createLifeline(String name, Interaction owner);

    /**
     * @param name the name of the Lifeline to create.
     * @param owner the owner Interaction of the Lifeline to create.
     * @param represented the Instance that will be represented by the Lifeline.
     * @return An Lifeline representing the Lifeline in the Model.
     */
    @objid ("d6e3d572-a7fc-46f4-a94b-7b7ed200bba5")
    Lifeline createLifeline(String name, Interaction owner, Instance represented);

    /**
     * Creates an {@link Link instance link}.
     * @param source the source instance
     * @param destination the target instance
     * @param destinationRoleName the name of the role towards the target.
     * @return the new Link.
     */
    @objid ("c28d30d6-c4c9-4560-b387-48b06f86630b")
    Link createLink(Instance source, Instance destination, String destinationRoleName);

    /**
     * @return a new {@link Link}.
     */
    @objid ("dbb78429-b981-42ea-9bd6-e4f0023e946b")
    Link createLink();

    /**
     * @return a new {@link LinkEnd}.
     */
    @objid ("e8b54249-2ad3-449c-a144-6c465c030dd5")
    LinkEnd createLinkEnd();

    /**
     * @return a new {@link LoopNode}.
     */
    @objid ("ec68bbfb-ec5c-4468-8052-0f00a5b4c61e")
    LoopNode createLoopNode();

    /**
     * Creates a new {@link Artifact artifact} {@link Manifestation}.
     * @return a new {@link Manifestation}.
     */
    @objid ("11f6fd20-9e55-4684-8b3a-caa70acb0659")
    Manifestation createManifestation();

    /**
     * @return a new {@link Message}.
     */
    @objid ("e9baef7f-7795-469a-bd14-3cbcbd16d629")
    Message createMessage();

    /**
     * @param sort the type of message to create.
     * @return An Message representing the Message in the Model.
     */
    @objid ("c2fa46fb-4bd3-482c-8da1-62bd3e215d02")
    Message createMessage(MessageSort sort);

    /**
     * @param sort the type of message to create.
     * @param invoked the operation that is invoked by the Message to create.
     * @return An Message representing the Message in the Model.
     */
    @objid ("2883f17a-32c3-4c9f-943e-0a1319f336f2")
    Message createMessage(MessageSort sort, Operation invoked);

    /**
     * @param name the name of the message to create.
     * @param sort the type of message to create.
     * @return An Message representing the Message in the Model.
     */
    @objid ("3bf3f666-d352-40ae-a290-1cc43e143f01")
    Message createMessage(String name, MessageSort sort);

    /**
     * @return a new {@link MessageFlow}.
     */
    @objid ("f185b272-4a82-407e-80fe-fb8068a05b72")
    MessageFlow createMessageFlow();

    /**
     * @return a new {@link NaryAssociation}.
     */
    @objid ("7b2b5126-cfb3-47a2-bee6-515795e52ea2")
    NaryAssociation createNaryAssociation();

    /**
     * Create a nary association.
     * 
     * This method create a nary association between classifiers. The created association has no navigable role.
     * @param ends the Classifiers linked by the nary association.
     * @return A NaryAssociation representing the NaryAssociation in the Model.
     */
    @objid ("cb37f9d5-6ec3-40bb-a016-056dd1fbe938")
    NaryAssociation createNaryAssociation(List<Classifier> ends);

    /**
     * @return a new {@link NaryAssociationEnd}.
     */
    @objid ("22508063-7783-4fb3-9c79-bf88347f9264")
    NaryAssociationEnd createNaryAssociationEnd();

    /**
     * @return a new {@link NaryConnector}.
     */
    @objid ("ad0002fa-5676-4d5c-9545-19d154768eb1")
    NaryConnector createNaryConnector();

    /**
     * Create a nary connector.
     * 
     * This method create a nary connector between bindable instances.
     * @param ends the BindableInstances bound by the NaryConnector.
     * @return A NaryConnector representing the NaryConnector in the Model.
     */
    @objid ("6497cf0f-53d0-4b9e-8ecf-376173823d42")
    NaryConnector createNaryConnector(List<BindableInstance> ends);

    /**
     * @return a new {@link NaryConnectorEnd}.
     */
    @objid ("5d3b1578-cc53-46f5-bdca-9269e3d24655")
    NaryConnectorEnd createNaryConnectorEnd();

    /**
     * @return a new {@link NaryLink}.
     */
    @objid ("2db99635-cec8-4d8e-94df-345716c41d51")
    NaryLink createNaryLink();

    /**
     * Create a nary link.
     * 
     * This method create a nary link between instances.
     * @param ends the Instances bound by the NaryLink.
     * @return A NaryLink representing the NaryLink in the Model.
     */
    @objid ("ed490c83-485e-4104-bb64-b0315f14c985")
    NaryLink createNaryLink(List<Instance> ends);

    /**
     * @return a new {@link NaryLinkEnd }.
     */
    @objid ("18ebfe4f-18a1-44b3-bc99-9af4907747d3")
    NaryLinkEnd createNaryLinkEnd();

    /**
     * @return a new {@link Node}.
     */
    @objid ("7d24047c-7fea-487f-88d9-a46cf205c40e")
    Node createNode();

    /**
     * @return a new {@link ObjectDiagram}.
     */
    @objid ("1c0dba9f-311f-4946-82e8-01a805811b36")
    ObjectDiagram createObjectDiagram();

    /**
     * Create an Object Diagram.
     * 
     * The returned object is an ObjectDiagram stereotyped <<object>> named by 'name'
     * @param name the name of the Object diagram to be created.
     * @param contextElement the element representing the Object Diagram 'background' for element creation in the diagram. null is not allowed.
     * @return An ObjectDiagram representing the diagram in the Model.
     */
    @objid ("eb166a55-7808-43f4-8d53-03e8c954817f")
    ObjectDiagram createObjectDiagram(String name, ModelElement contextElement);

    /**
     * Create an Object Diagram.
     * @param name the name of the Object diagram to be created.
     * @param contextElement the element representing the Object Diagram 'background' for element creation in the diagram. null is not allowed.
     * @param stereotype the Stereotype that will be referred by the diagram.
     * @return An ObjectDiagram representing the diagram in the Model.
     */
    @objid ("a62d9cd8-9ecf-4eb0-91c1-77aa11ec0200")
    ObjectDiagram createObjectDiagram(final String name, final ModelElement contextElement, final Stereotype stereotype);

    /**
     * @return a new {@link ObjectFlow}.
     */
    @objid ("e2b40281-acc7-4f1a-8a72-c3b4487d7ddc")
    ObjectFlow createObjectFlow();

    /**
     * @return a new {@link OpaqueAction}.
     */
    @objid ("0f568bc6-59e7-4201-b52b-03c29c7f4a09")
    OpaqueAction createOpaqueAction();

    /**
     * @return a new {@link OpaqueBehavior}.
     */
    @objid ("bda0905b-99d2-4a89-9a0a-5e545c3d9b48")
    OpaqueBehavior createOpaqueBehavior();

    /**
     * @return a new {@link Operation}.
     */
    @objid ("40bf183f-53fc-4b1c-bba7-4861320859bb")
    Operation createOperation();

    /**
     * @param name the name of the Operation to create.
     * @param owner the Classifier that will contain the Operation.
     * @return An Operation representing the Operation in the Model.
     */
    @objid ("e1a8ba9d-ca53-4e4d-9601-7fb59dd44ed9")
    Operation createOperation(String name, Classifier owner);

    /**
     * @param name the name of the Operation to create.
     * @param owner the Classifier that will contain the Operation.
     * @param stereotype the Stereotype that will extend the Operation.
     * @return An Operation representing the Operation in the Model.
     */
    @objid ("a1e8119e-54b6-4367-9033-0b8fbdd6d872")
    Operation createOperation(String name, Classifier owner, Stereotype stereotype);

    @objid ("dba82ed1-139e-4c3a-a2b6-513e6cef00e0")
    Operation createOperation(String name, Classifier owner, String moduleName, String stereotypeName) throws ExtensionNotFoundException;

    /**
     * @return a new {@link OutputPin}.
     */
    @objid ("77230be0-b8a1-432d-904e-2e1d9fcb8dd1")
    OutputPin createOutputPin();

    /**
     * Creates an {@link Package UML package}.
     * @return a new {@link Package}.
     */
    @objid ("82aba8cf-365a-46d3-937a-c2ceae9ba205")
    Package createPackage();

    /**
     * Creates an {@link Package UML package}.
     * @param name the name of the Package to create.
     * @param owner the NameSpace that will contain the Package.
     * @return An Package representing the Package in the Model.
     */
    @objid ("887269da-55b2-4e03-b91c-9a84427a693b")
    Package createPackage(String name, NameSpace owner);

    /**
     * Creates an {@link Package UML package}.
     * @param name the name of the Package to create.
     * @param owner the NameSpace that will contain the Package.
     * @param stereotype the Stereotype will extend the Package.
     * @return An Package representing the Package in the Model.
     */
    @objid ("bc89b662-3573-4d13-a13c-3d0aa6c6e853")
    Package createPackage(String name, NameSpace owner, Stereotype stereotype);

    @objid ("940c2deb-4c44-424d-bb39-c42c79c65709")
    Package createPackage(String name, NameSpace owner, String moduleName, String stereotypeName) throws ExtensionNotFoundException;

    /**
     * @return a new {@link PackageImport}.
     */
    @objid ("214edb0f-ff67-469b-a522-dace9ddad9ed")
    PackageImport createPackageImport();

    /**
     * @param source origin of the ElementImport
     * @param destination destination of the ElementImport
     * @return An {@link ElementImport} representing the ElementImport in the Model.
     */
    @objid ("c0b7bae2-7468-4ecc-bee3-28072e38ce54")
    PackageImport createPackageImport(NameSpace source, Package destination);

    /**
     * @param source origin of the PackageImport
     * @param destination destination of the PackageImport
     * @return A PackageImport representing the PackageImport in the Model.
     */
    @objid ("862ede5c-b3c4-40bf-a0bf-74db504e3ad5")
    PackageImport createPackageImport(Operation source, Package destination);

    /**
     * @return a new {@link PackageMerge}.
     */
    @objid ("c3530275-b92e-4339-9746-3eda5f1196d4")
    PackageMerge createPackageMerge();

    /**
     * @return a new {@link Parameter}.
     */
    @objid ("049be362-2d75-4d43-bd78-f75e6f2288a7")
    Parameter createParameter();

    /**
     * @return a new {@link PartDecomposition }.
     */
    @objid ("eb1da56d-bb4c-4d76-b614-b940ff738501")
    PartDecomposition createPartDecomposition();

    /**
     * @param referred the referred Interaction.
     * @return An PartDecomposition representing the PartDecomposition in the Model.
     */
    @objid ("afd2e419-0cab-43ef-86eb-23157d9feb58")
    PartDecomposition createPartDecomposition(Interaction referred);

    /**
     * @return a new {@link Port}.
     */
    @objid ("4a9acd4f-aa8e-4f16-b973-9b00f5176a3f")
    Port createPort();

    /**
     * @param name The name of the Port to create.
     * @param owner The Instance that will contain the Port.
     * @return An Port representing the Port in the Model.
     */
    @objid ("237aef5f-e59d-4fd8-afa3-f414b2644197")
    Port createPort(String name, Instance owner);

    /**
     * @param name The name of the Port to create.
     * @param owner The Classifier that will contain the Port.
     * @return An Port representing the Port in the Model.
     */
    @objid ("600a6999-e112-4934-96e0-f1ae6a87f467")
    Port createPort(String name, Classifier owner);

    /**
     * Creates a {@link Project}.
     * @param repository the repository where the model object will be stored.
     * @return a new {@link Project}.
     */
    @objid ("0df9439d-a55e-4c3b-ac78-440799ec34f4")
    Project createProject(IRepository repository);

    /**
     * @return a new {@link ProvidedInterface}.
     */
    @objid ("6bf6a455-7375-4095-b2eb-7007f990f438")
    ProvidedInterface createProvidedInterface();

    /**
     * @param owner the Port that contains the ProvidedInterface.
     * @param interfaces the provided Interfaces
     * @return An ProvidedInterface representing the ProvidedInterface in the Model.
     */
    @objid ("ff7321b3-d24a-4e94-a041-8d7959246d57")
    ProvidedInterface createProvidedInterface(Port owner, List<Interface> interfaces);

    /**
     * @return a new {@link RaisedException}.
     */
    @objid ("486c9f67-c3e8-47c1-8c8f-0c6ff8057a0d")
    RaisedException createRaisedException();

    /**
     * @return a new {@link Region }.
     */
    @objid ("117a70ad-24df-461b-a9e5-8aa14a8fa5f1")
    Region createRegion();

    /**
     * @return a new {@link RequiredInterface}.
     */
    @objid ("3d7009b2-feaf-4863-b5ae-606302e162c3")
    RequiredInterface createRequiredInterface();

    /**
     * @param owner the Port that contains the RequiredInterface.
     * @param interfaces the required Interfaces
     * @return An RequiredInterface representing the RequiredInterface in the Model.
     */
    @objid ("a8f30148-67ac-400f-8b5e-89609bdb3610")
    RequiredInterface createRequiredInterface(Port owner, List<Interface> interfaces);

    /**
     * @param name the name of the Parameter to create.
     * @param type the type of the Parameter to create.
     * @param owner the Operation that will contain the Parameter.
     * @return An Parameter representing the Parameter in the Model.
     */
    @objid ("5e2d03a1-79d9-48c9-a076-eeb6ca906f43")
    Parameter createReturnParameter(String name, GeneralClass type, Operation owner);

    /**
     * @param name the name of the Parameter to create.
     * @param type the type of the Parameter to create.
     * @param owner the Operation that will contain the Parameter.
     * @param stereotype the stereotype that will extend the Parameter.
     * @return An Parameter representing the Parameter in the Model.
     */
    @objid ("24d8f4ab-5e4b-46de-bbf2-52b7c01d102a")
    Parameter createReturnParameter(String name, GeneralClass type, Operation owner, Stereotype stereotype);

    @objid ("5bbb4d33-6148-4c3b-8fb4-ebedc15c58f8")
    Parameter createReturnParameter(String name, GeneralClass type, Operation owner, String moduleName, String stereotypeName) throws ExtensionNotFoundException;

    /**
     * @return a new {@link SendSignalAction}.
     */
    @objid ("4f22a7bd-3e0b-4ca0-87c1-3887f2fa2eb4")
    SendSignalAction createSendSignalAction();

    /**
     * Creates a new.
     * @return a new {@link SequenceDiagram}.
     */
    @objid ("05d3eccf-fb39-46c7-b8fb-5c7d1825bf90")
    SequenceDiagram createSequenceDiagram();

    /**
     * Create a {@linkplain SequenceDiagram sequence diagram}.
     * 
     * The returned object is a SequenceDiagram named by 'name'.
     * @param name the name of the SequenceDiagram to be created.
     * @param contextElement the element representing the Static Diagram 'background' for element creation in the diagram. null is not allowed.
     * @return An SequenceDiagram representing the diagram in the Model.
     */
    @objid ("62d3d0bf-4b84-44f1-b090-fd0ca038addc")
    SequenceDiagram createSequenceDiagram(String name, ModelElement contextElement);

    /**
     * @return a new {@link ShallowHistoryPseudoState}.
     */
    @objid ("911514aa-f9b5-4cda-912d-b7ec3c99176e")
    ShallowHistoryPseudoState createShallowHistoryPseudoState();

    /**
     * @return a new {@link Signal}.
     */
    @objid ("654a39ee-c648-4d79-8bc4-dde1985794a2")
    Signal createSignal();

    /**
     * @return a new {@link State}.
     */
    @objid ("38fba0cb-7f40-4cb0-ad6d-39ec41e096a7")
    State createState();

    /**
     * @return a new {@link StateInvariant}.
     */
    @objid ("8afee9d3-ac9e-4e58-8790-8ef832171966")
    StateInvariant createStateInvariant();

    /**
     * @param body the body of the StateInvariant to create.
     * @return An StateInvariant representing the StateInvariant in the Model.
     */
    @objid ("746e1ed2-20c9-4f54-8ec7-b2aec2833188")
    StateInvariant createStateInvariant(String body);

    /**
     * @return a new {@link StateMachine}.
     */
    @objid ("b6e2c474-d61c-4d19-a5ee-7abd0aa59205")
    StateMachine createStateMachine();

    /**
     * @return a new {@link StateMachineDiagram}.
     */
    @objid ("a3e564c7-32be-4491-bc3e-741332e137c8")
    StateMachineDiagram createStateMachineDiagram();

    /**
     * Create a StateMachine Diagram.
     * @param name the name of the StateMachine diagram to be created.
     * @param contextElement the element representing the StateMachine Diagram 'background' for element creation in the diagram. null is not
     * allowed.
     * @param stereotype the Stereotype that will be referred by the diagram.
     * @return A StateMachineDiagram representing the diagram in the Model.
     */
    @objid ("2ba20c9a-267e-4157-9c4e-d72bb1bc5704")
    StateMachineDiagram createStateMachineDiagram(final String name, final ModelElement contextElement, final Stereotype stereotype);

    /**
     * @return a new {@link StaticDiagram}.
     */
    @objid ("bb48de93-670d-4655-9299-3e9054798dc3")
    StaticDiagram createStaticDiagram();

    /**
     * @param name the name of the Static Diagram to create.
     * @param owner the element on which the Static Diagram will be created.
     * @param stereotype the name of the stereotype that will be used to type the Static Diagram
     * @return A StaticDiagram representing the diagram in the Model.
     */
    @objid ("cd1b5ede-fa50-4c3a-bd90-321923b2832c")
    StaticDiagram createStaticDiagram(String name, ModelElement owner, Stereotype stereotype);

    @objid ("20c75ead-8f1d-41cc-bb09-17e5c74f3373")
    StaticDiagram createStaticDiagram(String name, ModelElement owner, String moduleName, String stereotypeName) throws ExtensionNotFoundException;

    /**
     * @return a new {@link StructuredActivityNode}.
     */
    @objid ("11fcdb31-e8f9-43e8-a71f-172c7dacec4c")
    StructuredActivityNode createStructuredActivityNode();

    /**
     * @return a new {@link Substitution}.
     */
    @objid ("bba5df5f-d26b-4bc4-b7cd-10c6dbf729c7")
    Substitution createSubstitution();

    /**
     * @return a new {@link }.
     */
    @objid ("9ecd193d-606c-4788-82af-c182d29cae62")
    TemplateBinding createTemplateBinding();

    /**
     * @return a new {@link }.
     */
    @objid ("69e557f1-8a36-48e3-a980-f88ec8643a55")
    TemplateParameter createTemplateParameter();

    /**
     * @return a new {@link }.
     */
    @objid ("d6a38516-d846-49d4-bfa6-4bd719582608")
    TemplateParameterSubstitution createTemplateParameterSubstitution();

    /**
     * @return a new {@link TerminatePseudoState}.
     */
    @objid ("526b61d7-c69c-49f0-85ff-a7b4921b845b")
    TerminatePseudoState createTerminatePseudoState();

    /**
     * @return a new {@link TerminateSpecification}.
     */
    @objid ("ccbe6c58-321f-4cd2-b2dc-73065b99cb7b")
    TerminateSpecification createTerminateSpecification();

    /**
     * @return a new {@link Transition}.
     */
    @objid ("83a47a6f-7177-4e6e-8cf0-abf007980520")
    Transition createTransition();

    /**
     * @return a new {@link Usage }.
     */
    @objid ("fe780108-1c59-465e-aac2-7a55529e4736")
    Usage createUsage();

    /**
     * @param source the origin of the Usage.
     * @param destination the destination of the Usage.
     * @return An Usage representing the Usage in the Model.
     */
    @objid ("5b1e3b57-a8fe-42bf-ba41-eef3343daf72")
    Usage createUsage(ModelElement source, ModelElement destination);

    /**
     * @return a new {@link UseCase}.
     */
    @objid ("6342f6c0-c4a5-4d9d-8262-9ab651a3605f")
    UseCase createUseCase();

    /**
     * @param name the name of the UseCase to create.
     * @param owner the NameSpace that will contain the Usecase.
     * @return An UseCase representing the UseCase in the Model.
     */
    @objid ("841cb962-b0ca-4ca8-9c79-3d3f88be1c8f")
    UseCase createUseCase(String name, NameSpace owner);

    /**
     * @param name the name of the UseCase to create.
     * @param owner the NameSpace that will contain the Usecase.
     * @param stereotype the Stereotype that will extend the UseCase.
     * @return An UseCase representing the UseCase in the Model.
     */
    @objid ("b8b19d3c-3a66-42f1-9efa-dec37cd41ca8")
    UseCase createUseCase(String name, NameSpace owner, Stereotype stereotype);

    @objid ("c1f89392-06ca-4221-88d0-4f86112440dc")
    UseCase createUseCase(String name, NameSpace owner, String moduleName, String stereotypeName) throws ExtensionNotFoundException;

    /**
     * @return a new {@link UseCaseDependency}.
     */
    @objid ("28356a19-5f14-49ef-9039-e012a1204495")
    UseCaseDependency createUseCaseDependency();

    /**
     * @return a new {@link UseCaseDiagram}.
     */
    @objid ("66876b21-580f-4251-b192-3d805dd941d9")
    UseCaseDiagram createUseCaseDiagram();

    /**
     * Create a UseCase Diagram.
     * 
     * The returned object is a UseCaseDiagram stereotyped &lt;&lt;usecase>> named by 'name'
     * @param diagramName the name of the UseCase diagram to be created.
     * @param contextElement the element representing the UseCase Diagram 'background' for element creation in the diagram. null is not
     * allowed.
     * @return A UseCaseDiagram representing the diagram in the Model.
     */
    @objid ("b576d85c-fe99-42ca-aaf7-1d9373002c7b")
    UseCaseDiagram createUseCaseDiagram(String diagramName, ModelElement contextElement);

    /**
     * Create a UseCase Diagram.
     * @param name the name of the UseCase diagram to be created.
     * @param contextElement the element representing the UseCase Diagram 'background' for element creation in the diagram. null is not
     * allowed.
     * @param stereotype the Stereotype that will be referred by the diagram.
     * @return A UseCase representing the diagram in the Model.
     */
    @objid ("c820fd06-14f9-4097-ae3a-01727afa9e40")
    UseCaseDiagram createUseCaseDiagram(final String name, final ModelElement contextElement, final Stereotype stereotype);

    /**
     * @return a new {@link ValuePin}.
     */
    @objid ("9fa1f464-cabc-413c-b8c7-0fd70d298092")
    ValuePin createValuePin();

    /**
     * Creates an empty, unnamed {@link ExternElement UML class}.
     * @return a new Class.
     */
    @objid ("5648f64b-d273-422f-8dd3-d42589b327fa")
    ExternElement createExternElement();

    /**
     * @param name the name of the ExternElement to create.
     * @param owner the MethodologicalLink that will contain the ExternElement.
     * @param stereotype the Stereotype to refer on the ExternElement.
     * @return An ExternElement representing the ExternElement in the Model.
     */
    @objid ("68e0fe2b-9e7c-4f20-99f1-f4a4e1b05b7b")
    ExternElement createExternElement(String name, MethodologicalLink owner, Stereotype stereotype);

    @objid ("52cf4627-7351-43a1-bbc0-7ede234a4391")
    ExternElement createExternElement(String name, MethodologicalLink owner, String moduleName, String stereotypeName);

    /**
     * Creates {@link MethodologicalLink}.
     * @return a new Class.
     */
    @objid ("0755764b-4d8c-4cf1-ae1e-3cb1883e1fa8")
    MethodologicalLink createMethodologicalLink();

    /**
     * @param source the origin of the MethodologicalLink.
     * @param destination the destination of the MethodologicalLink.
     * @param stereotype the Stereotype that extends the MethodologicalLink.
     * @return An MethodologicalLink representing the MethodologicalLink in the Model.
     */
    @objid ("0392dcaa-4340-4aad-bd36-268301692e01")
    MethodologicalLink createMethodologicalLink(ModelElement source, ModelElement destination, Stereotype stereotype);

    @objid ("935759a9-18cd-4a35-b0e1-57b911dd8891")
    MethodologicalLink createMethodologicalLink(ModelElement source, ModelElement destination, String moduleName, String stereotypeName);

}
