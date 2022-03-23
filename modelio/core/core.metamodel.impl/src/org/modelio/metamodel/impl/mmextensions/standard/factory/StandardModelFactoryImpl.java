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
package org.modelio.metamodel.impl.mmextensions.standard.factory;

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
import org.modelio.metamodel.impl.mmextensions.infrastructure.factory.InfrastructureModelFactoryImpl;
import org.modelio.metamodel.mda.Project;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
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
import org.modelio.metamodel.uml.statik.AggregationKind;
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
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.repository.IRepository;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * High level model element creation factory for Standard metamodel fragment.
 */
@objid ("f255145e-9b6d-4199-a69a-9ef874e43cac")
public class StandardModelFactoryImpl extends InfrastructureModelFactoryImpl implements IStandardModelFactory {
    /**
     * The model element initializer used by the factory.
     */
    @objid ("7d4b156f-307b-4844-85a2-81d072c85dca")
    private final IElementInitializer elementInitializer;

    @objid ("2f86cfde-cddc-42a6-9bdd-72fbbf25a59f")
    public  StandardModelFactoryImpl(ICoreSession session) {
        super(session);
        
        this.elementInitializer = new ElementInitializer(this);
        
    }

    @objid ("e45f8be1-f906-43f9-a86e-dfb9d6309208")
    @Override
    public Abstraction createAbstraction() {
        Abstraction newElement = this.genericFactory.create(Abstraction.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("1be4619b-09e4-4688-9a42-6b1f6bd74b09")
    @Override
    public AcceptCallEventAction createAcceptCallEventAction() {
        AcceptCallEventAction newElement = this.genericFactory.create(AcceptCallEventAction.class,
                this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("08daa119-86dd-4fca-ae94-35292ee1b35d")
    @Override
    public AcceptChangeEventAction createAcceptChangeEventAction() {
        AcceptChangeEventAction newElement = this.genericFactory.create(AcceptChangeEventAction.class,
                this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("8a8cbd9d-028f-4f2d-9bff-26d4a0cf3b68")
    @Override
    public AcceptSignalAction createAcceptSignalAction() {
        AcceptSignalAction newElement = this.genericFactory.create(AcceptSignalAction.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("a739b94f-f463-447d-a139-dcb9036dd8e2")
    @Override
    public AcceptTimeEventAction createAcceptTimeEventAction() {
        AcceptTimeEventAction newElement = this.genericFactory.create(AcceptTimeEventAction.class,
                this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("daac83d6-9272-4505-9082-3ea6853ce5da")
    @Override
    public Activity createActivity() {
        Activity newElement = this.genericFactory.create(Activity.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("be157e2f-1678-451d-816f-ee061e417d58")
    @Override
    public ActivityDiagram createActivityDiagram() {
        ActivityDiagram newElement = this.genericFactory.create(ActivityDiagram.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("cdb5a68c-f3f1-41a7-8eeb-e6e84847447f")
    @Override
    public ActivityDiagram createActivityDiagram(String name, ModelElement contextElement) {
        ActivityDiagram newElement = this.genericFactory.create(ActivityDiagram.class, contextElement);
        newElement.setName(name);
        newElement.setOrigin(contextElement);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("78adb4fb-7b1f-4be6-9a75-79f706d15c0a")
    @Override
    public ActivityFinalNode createActivityFinalNode() {
        ActivityFinalNode newElement = this.genericFactory.create(ActivityFinalNode.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("09a61a3f-3df3-4b7e-af6d-a11866f28f38")
    @Override
    public ActivityParameterNode createActivityParameterNode() {
        ActivityParameterNode newElement = this.genericFactory.create(ActivityParameterNode.class,
                this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("a38192c9-03b3-4d0f-9f5e-57e22075f86b")
    @Override
    public ActivityPartition createActivityPartition() {
        ActivityPartition newElement = this.genericFactory.create(ActivityPartition.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("1186ad8a-00ae-4f73-8957-f2c51019146c")
    @Override
    public Actor createActor() {
        Actor newElement = this.genericFactory.create(Actor.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("30bf5e26-9c0c-4f02-ae3d-d4ca6a24bdd4")
    @Override
    public Actor createActor(String name, NameSpace owner) {
        Actor newElement = this.genericFactory.create(Actor.class, owner);
        newElement.setName(name);
        newElement.setOwner(owner);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("6efe2489-8dd3-4fdf-af56-cfcd47067dfc")
    @Override
    public Actor createActor(String name, NameSpace owner, Stereotype stereotype) {
        Actor newElement = this.genericFactory.create(Actor.class, owner);
        newElement.setName(name);
        newElement.setOwner(owner);
        if (stereotype != null) {
            newElement.getExtension().add(stereotype);
        }
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("0dca8764-fe51-4fb4-8830-90f9699284c8")
    @Override
    public Actor createActor(String name, NameSpace owner, String moduleName, String stereotypeName) throws ExtensionNotFoundException {
        return createActor(name, owner, resolveStereotype(moduleName, stereotypeName, this.metamodel.getMClass(Actor.class)));
    }

    @objid ("42c914ae-7c80-47ea-ab22-e5b4a01cec02")
    @Override
    public Association createAggregation(Classifier source, Classifier destination, String roleName) {
        AssociationEnd sourceRole = createAssociationEnd();
        sourceRole.setSource(source);
        sourceRole.setTarget(destination);
        sourceRole.setAggregation(AggregationKind.KINDISAGGREGATION);
        sourceRole.setName(roleName);
        
        AssociationEnd destinationRole = createAssociationEnd();
        
        // Opposite relation must be set for both ends
        destinationRole.setOpposite(sourceRole);
        sourceRole.setOpposite(destinationRole);
        
        // Create the association itself
        Association newAssoc = createAssociation();
        destinationRole.setAssociation(newAssoc);
        sourceRole.setAssociation(newAssoc);
        return newAssoc;
    }

    @objid ("07cab6b8-8289-4e90-a4b2-174118b0238c")
    @Override
    public Artifact createArtifact() {
        Artifact newElement = this.genericFactory.create(Artifact.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("6f128ca0-a68e-4928-90ec-6c1bae991d97")
    @Override
    public Artifact createArtifact(String name, NameSpace owner) {
        Artifact newElement = this.genericFactory.create(Artifact.class, this.scratchRepository);
        newElement.setName(name);
        newElement.setOwner(owner);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("a7a22562-fc2e-4a2d-8b78-e102ac3180a4")
    @Override
    public Artifact createArtifact(String name, NameSpace owner, Stereotype stereotype) {
        Artifact newElement = this.genericFactory.create(Artifact.class, owner);
        newElement.setName(name);
        newElement.setOwner(owner);
        if (stereotype != null) {
            newElement.getExtension().add(stereotype);
        }
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("ffb90d07-3c2c-4ba6-8aa4-528e1f3b2d68")
    @Override
    public Artifact createArtifact(String name, NameSpace owner, String moduleName, String stereotypeName) throws ExtensionNotFoundException {
        return createArtifact(name, owner, resolveStereotype(moduleName, stereotypeName, this.metamodel.getMClass(Artifact.class)));
    }

    @objid ("6f179107-8a5d-4fa7-9248-78891079705e")
    @Override
    public Association createAssociation() {
        Association newElement = this.genericFactory.create(Association.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("b21e44e0-3b08-44fe-b3a8-7d2c505e5c7e")
    @Override
    public Association createAssociation(Classifier source, Classifier destination, String roleName) {
        AssociationEnd sourceRole = createAssociationEnd();
        sourceRole.setSource(source);
        sourceRole.setTarget(destination);
        sourceRole.setAggregation(AggregationKind.KINDISASSOCIATION);
        sourceRole.setName(roleName);
        
        AssociationEnd destinationRole = createAssociationEnd();
        
        // Opposite relation must be set for both ends
        destinationRole.setOpposite(sourceRole);
        sourceRole.setOpposite(destinationRole);
        
        // Create the association itself
        Association newAssoc = createAssociation();
        destinationRole.setAssociation(newAssoc);
        sourceRole.setAssociation(newAssoc);
        return newAssoc;
    }

    @objid ("0e508e41-eede-41ed-a6de-d8023cbaf687")
    @Override
    public AssociationEnd createAssociationEnd() {
        AssociationEnd newElement = this.genericFactory.create(AssociationEnd.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("3852adcb-7135-47cb-a920-cc3744b20b7a")
    @Override
    public Attribute createAttribute() {
        Attribute newElement = this.genericFactory.create(Attribute.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("b706e0ca-ebad-45e0-acb7-3d301be2887e")
    @Override
    public Attribute createAttribute(String name, GeneralClass type, Classifier owner) {
        Attribute newElement = this.genericFactory.create(Attribute.class, owner);
        newElement.setName(name);
        newElement.setOwner(owner);
        this.elementInitializer.initialize(newElement);
        newElement.setType(type);
        return newElement;
    }

    @objid ("ab1bdfdd-744a-4fed-aa24-1ed91416a3eb")
    @Override
    public Attribute createAttribute(String name, GeneralClass type, Classifier owner, Stereotype stereotype) {
        Attribute newElement = this.genericFactory.create(Attribute.class, owner);
        newElement.setName(name);
        newElement.setOwner(owner);
        if (stereotype != null) {
            newElement.getExtension().add(stereotype);
        }
        this.elementInitializer.initialize(newElement);
        newElement.setType(type);
        return newElement;
    }

    @objid ("97a37f51-8c66-4f08-a227-4b2b0339b647")
    @Override
    public Attribute createAttribute(String name, GeneralClass type, Classifier owner, String moduleName, String stereotypeName) throws ExtensionNotFoundException {
        return createAttribute(name, type, owner, resolveStereotype(moduleName, stereotypeName, this.metamodel.getMClass(Attribute.class)));
    }

    @objid ("00d0b6d8-57f8-4375-8fce-ae126e8f1415")
    @Override
    public AttributeLink createAttributeLink() {
        AttributeLink newElement = this.genericFactory.create(AttributeLink.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("9c684cb4-6f6e-43fb-ac57-d89eff714785")
    @Override
    public BehaviorParameter createBehaviorParameter() {
        BehaviorParameter newElement = this.genericFactory.create(BehaviorParameter.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("15d597d5-deff-4a04-88d9-8e0a17fc1c1a")
    @Override
    public BindableInstance createBindableInstance() {
        BindableInstance newElement = this.genericFactory.create(BindableInstance.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("d9159205-bdad-4070-a2a9-abd904a5c7f1")
    @Override
    public Binding createBinding() {
        Binding newElement = this.genericFactory.create(Binding.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("af85c549-7b82-405b-9c12-185c62ab2567")
    @Override
    public BpmnActivity createBpmnActivity() {
        BpmnActivity newElement = this.genericFactory.create(BpmnActivity.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("ccd4feb2-9f1f-4052-b722-15ce240377ca")
    @Override
    public BpmnAdHocSubProcess createBpmnAdHocSubProcess() {
        BpmnAdHocSubProcess newElement = this.genericFactory.create(BpmnAdHocSubProcess.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("0bc60aa3-cb47-4a92-b2d4-9caede4abcc1")
    @Override
    public BpmnAssociation createBpmnAssociation() {
        BpmnAssociation newElement = this.genericFactory.create(BpmnAssociation.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("3d9c7501-f415-4c81-a7a1-2b3135bd450f")
    @Override
    public BpmnBoundaryEvent createBpmnBoundaryEvent() {
        BpmnBoundaryEvent newElement = this.genericFactory.create(BpmnBoundaryEvent.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("d44f28df-d16c-4b97-a226-0c31bfe74792")
    @Override
    public BpmnBusinessRuleTask createBpmnBusinessRuleTask() {
        BpmnBusinessRuleTask newElement = this.genericFactory.create(BpmnBusinessRuleTask.class,
                this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("b8057d0d-3681-4002-bbcd-147b71995b44")
    @Override
    public BpmnCallActivity createBpmnCallActivity() {
        BpmnCallActivity newElement = this.genericFactory.create(BpmnCallActivity.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("1122be30-b9de-4f01-a6d3-1c48d7ebce63")
    @Override
    public BpmnCancelEventDefinition createBpmnCancelEventDefinition() {
        BpmnCancelEventDefinition newElement = this.genericFactory.create(BpmnCancelEventDefinition.class,
                this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("5ad4790d-cdc1-400d-be41-932784235aa4")
    @Override
    public BpmnCollaboration createBpmnCollaboration() {
        BpmnCollaboration newElement = this.genericFactory.create(BpmnCollaboration.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("52fc823c-ee74-48f8-acc9-f74fe300e8fa")
    @Override
    public BpmnCollaborationDiagram createBpmnCollaborationDiagram() {
        BpmnCollaborationDiagram newElement = this.genericFactory.create(BpmnCollaborationDiagram.class,
                this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("7c55276f-9034-426e-8d36-f6f0586ae717")
    @Override
    public BpmnCompensateEventDefinition createBpmnCompensateEventDefinition() {
        BpmnCompensateEventDefinition newElement = this.genericFactory.create(BpmnCompensateEventDefinition.class,
                this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("d370bc27-de10-4803-b791-6eac7ad682ea")
    @Override
    public BpmnComplexBehaviorDefinition createBpmnComplexBehaviorDefinition() {
        BpmnComplexBehaviorDefinition newElement = this.genericFactory.create(BpmnComplexBehaviorDefinition.class,
                this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("02208fe7-7360-44ed-8485-9f805fa170cf")
    @Override
    public BpmnComplexGateway createBpmnComplexGateway() {
        BpmnComplexGateway newElement = this.genericFactory.create(BpmnComplexGateway.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("6b2a1d2b-35fe-44e5-86f1-f48488648641")
    @Override
    public BpmnConditionalEventDefinition createBpmnConditionalEventDefinition() {
        BpmnConditionalEventDefinition newElement = this.genericFactory.create(BpmnConditionalEventDefinition.class,
                this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("39c8c599-c3f0-4742-89b7-5bd692f68d02")
    @Override
    public BpmnDataAssociation createBpmnDataAssociation() {
        BpmnDataAssociation newElement = this.genericFactory.create(BpmnDataAssociation.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("362606d5-2f20-44a5-86ba-a1279da87267")
    @Override
    public BpmnDataInput createBpmnDataInput() {
        BpmnDataInput newElement = this.genericFactory.create(BpmnDataInput.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("a45a685e-2603-4493-af3e-a210103726d6")
    @Override
    public BpmnDataObject createBpmnDataObject() {
        BpmnDataObject newElement = this.genericFactory.create(BpmnDataObject.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("44ca3413-639c-4b93-8abc-08dbdcfafeb7")
    @Override
    public BpmnDataOutput createBpmnDataOutput() {
        BpmnDataOutput newElement = this.genericFactory.create(BpmnDataOutput.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("0355a500-705c-4354-99b9-883864f7ff14")
    @Override
    public BpmnDataState createBpmnDataState() {
        BpmnDataState newElement = this.genericFactory.create(BpmnDataState.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("3dcede7f-9332-4322-8cf2-905bfa4a41f5")
    @Override
    public BpmnDataStore createBpmnDataStore() {
        BpmnDataStore newElement = this.genericFactory.create(BpmnDataStore.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("126b20d3-ce17-4cf8-9b36-c7af359302c9")
    @Override
    public BpmnEndEvent createBpmnEndEvent() {
        BpmnEndEvent newElement = this.genericFactory.create(BpmnEndEvent.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("bebcc2f7-3919-42a3-8522-79f1c599c19f")
    @Override
    public BpmnEndPoint createBpmnEndPoint() {
        BpmnEndPoint newElement = this.genericFactory.create(BpmnEndPoint.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("e00d4ebb-6ba4-430d-afaa-50f85f8b3076")
    @Override
    public BpmnErrorEventDefinition createBpmnErrorEventDefinition() {
        BpmnErrorEventDefinition newElement = this.genericFactory.create(BpmnErrorEventDefinition.class,
                this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("c2fd45d4-5b8f-4530-a8dc-1043d44a36ff")
    @Override
    public BpmnEscalationEventDefinition createBpmnEscalationEventDefinition() {
        BpmnEscalationEventDefinition newElement = this.genericFactory.create(BpmnEscalationEventDefinition.class,
                this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("eb25ab1e-587f-4ec6-91dc-536e3bd400c9")
    @Override
    public BpmnEventBasedGateway createBpmnEventBasedGateway() {
        BpmnEventBasedGateway newElement = this.genericFactory.create(BpmnEventBasedGateway.class,
                this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("e06f4cf5-abac-445c-a472-bc4b59326d12")
    @Override
    public BpmnExclusiveGateway createBpmnExclusiveGateway() {
        BpmnExclusiveGateway newElement = this.genericFactory.create(BpmnExclusiveGateway.class,
                this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("c7735af2-859f-4284-9cf0-026c14a680da")
    @Override
    public BpmnGroup createBpmnGroup() {
        BpmnGroup newElement = this.genericFactory.create(BpmnGroup.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("823db442-441c-4d19-a208-a8a35849b924")
    @Override
    public BpmnImplicitThrowEvent createBpmnImplicitThrowEvent() {
        BpmnImplicitThrowEvent newElement = this.genericFactory.create(BpmnImplicitThrowEvent.class,
                this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("f1fb012a-89fd-4eb3-a2ab-fe56c795240c")
    @Override
    public BpmnInclusiveGateway createBpmnInclusiveGateway() {
        BpmnInclusiveGateway newElement = this.genericFactory.create(BpmnInclusiveGateway.class,
                this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("a76b3012-91ad-4871-9f3f-69a752678977")
    @Override
    public BpmnInterface createBpmnInterface() {
        BpmnInterface newElement = this.genericFactory.create(BpmnInterface.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("fb4040f2-f5a6-4859-9b5c-8e3ed0d2de32")
    @Override
    public BpmnIntermediateCatchEvent createBpmnIntermediateCatchEvent() {
        BpmnIntermediateCatchEvent newElement = this.genericFactory.create(BpmnIntermediateCatchEvent.class,
                this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("9e37be34-90d3-4547-a474-5eaa905af540")
    @Override
    public BpmnIntermediateThrowEvent createBpmnIntermediateThrowEvent() {
        BpmnIntermediateThrowEvent newElement = this.genericFactory.create(BpmnIntermediateThrowEvent.class,
                this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("7dbe3f03-2b1c-489b-8818-9ac12e5af88e")
    @Override
    public BpmnItemDefinition createBpmnItemDefinition() {
        BpmnItemDefinition newElement = this.genericFactory.create(BpmnItemDefinition.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("de607c52-ce73-470e-91eb-04f44448d525")
    @Override
    public BpmnLane createBpmnLane() {
        BpmnLane newElement = this.genericFactory.create(BpmnLane.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("43b6038a-f5d4-4ee5-a029-570b955e5525")
    @Override
    public BpmnLaneSet createBpmnLaneSet() {
        BpmnLaneSet newElement = this.genericFactory.create(BpmnLaneSet.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("b9e2015d-9e5c-48c9-b830-cc0badb99bb8")
    @Override
    public BpmnLinkEventDefinition createBpmnLinkEventDefinition() {
        BpmnLinkEventDefinition newElement = this.genericFactory.create(BpmnLinkEventDefinition.class,
                this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("f105e06e-63dd-41e5-a5bd-86020c4aa1f0")
    @Override
    public BpmnManualTask createBpmnManualTask() {
        BpmnManualTask newElement = this.genericFactory.create(BpmnManualTask.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("13a991b0-b7d3-4291-973e-8ccf0544b612")
    @Override
    public BpmnMessage createBpmnMessage() {
        BpmnMessage newElement = this.genericFactory.create(BpmnMessage.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("f348c1d3-7565-4028-8746-4056e81b321a")
    @Override
    public BpmnMessageEventDefinition createBpmnMessageEventDefinition() {
        BpmnMessageEventDefinition newElement = this.genericFactory.create(BpmnMessageEventDefinition.class,
                this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("57e5f2c5-0a19-4991-a55a-7dd915ceded0")
    @Override
    public BpmnMessageFlow createBpmnMessageFlow() {
        BpmnMessageFlow newElement = this.genericFactory.create(BpmnMessageFlow.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("ecc3f6cb-a256-47af-a431-efa89e806e8e")
    @Override
    public BpmnMultiInstanceLoopCharacteristics createBpmnMultiInstanceLoopCharacteristics() {
        BpmnMultiInstanceLoopCharacteristics newElement = this.genericFactory
                .create(BpmnMultiInstanceLoopCharacteristics.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("387bda9d-8bf3-439e-8449-a21637063b93")
    @Override
    public BpmnOperation createBpmnOperation() {
        BpmnOperation newElement = this.genericFactory.create(BpmnOperation.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("12deb5f9-af4d-46ea-9cb8-2ced63e784a0")
    @Override
    public BpmnParallelGateway createBpmnParallelGateway() {
        BpmnParallelGateway newElement = this.genericFactory.create(BpmnParallelGateway.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("3a0ff230-53f3-4416-a9e1-834345d223aa")
    @Override
    public BpmnParticipant createBpmnParticipant() {
        BpmnParticipant newElement = this.genericFactory.create(BpmnParticipant.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("d567c0ef-159f-4cc3-a590-0082a4f06a1b")
    @Override
    public BpmnProcess createBpmnProcess() {
        BpmnProcess newElement = this.genericFactory.create(BpmnProcess.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("21600c8b-75d2-427e-b814-24f841365788")
    @Override
    public BpmnProcessDesignDiagram createBpmnProcessDesignDiagram() {
        BpmnProcessDesignDiagram newElement = this.genericFactory.create(BpmnProcessDesignDiagram.class,
                this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("0952f66f-bb54-4e46-b622-79fb25608ae8")
    @Override
    public BpmnReceiveTask createBpmnReceiveTask() {
        BpmnReceiveTask newElement = this.genericFactory.create(BpmnReceiveTask.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("71caec26-38e7-49b1-87f4-9b92244be6a1")
    @Override
    public BpmnResource createBpmnResource() {
        BpmnResource newElement = this.genericFactory.create(BpmnResource.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("638c4515-dcb4-4425-8517-4c3b232dcdc0")
    @Override
    public BpmnResourceParameter createBpmnResourceParameter() {
        BpmnResourceParameter newElement = this.genericFactory.create(BpmnResourceParameter.class,
                this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("edd4fe5f-3545-4422-ad7d-9d5a4e0fcea4")
    @Override
    public BpmnResourceParameterBinding createBpmnResourceParameterBinding() {
        BpmnResourceParameterBinding newElement = this.genericFactory.create(BpmnResourceParameterBinding.class,
                this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("1f1896fc-efe1-4899-95ab-328d0728f87f")
    @Override
    public BpmnResourceRole createBpmnResourceRole() {
        BpmnResourceRole newElement = this.genericFactory.create(BpmnResourceRole.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("c9b10d67-7445-4cd7-bf5c-dbad68e2bcdc")
    @Override
    public BpmnScriptTask createBpmnScriptTask() {
        BpmnScriptTask newElement = this.genericFactory.create(BpmnScriptTask.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("0cb8e4ab-b4c3-4cb1-b849-0c4cc45b6bdf")
    @Override
    public BpmnSendTask createBpmnSendTask() {
        BpmnSendTask newElement = this.genericFactory.create(BpmnSendTask.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("28ce6e44-3bea-4101-a301-f575a94264a9")
    @Override
    public BpmnSequenceFlow createBpmnSequenceFlow() {
        BpmnSequenceFlow newElement = this.genericFactory.create(BpmnSequenceFlow.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("ef2fe348-7146-4afd-b22a-d630584d2076")
    @Override
    public BpmnSequenceFlowDataAssociation createBpmnSequenceFlowDataAssociation() {
        BpmnSequenceFlowDataAssociation newElement = this.genericFactory.create(BpmnSequenceFlowDataAssociation.class,
                this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("dcbdb6b4-d4b3-4abb-92b9-c66cec4c5172")
    @Override
    public BpmnServiceTask createBpmnServiceTask() {
        BpmnServiceTask newElement = this.genericFactory.create(BpmnServiceTask.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("fa4620d2-8965-47e0-bf98-81f5bf959b50")
    @Override
    public BpmnSharedDefinitions createBpmnSharedDefinitions() {
        BpmnSharedDefinitions newElement = this.genericFactory.create(BpmnSharedDefinitions.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("983e1ea9-e192-4567-8cf1-6291d113d859")
    @Override
    public BpmnSignalEventDefinition createBpmnSignalEventDefinition() {
        BpmnSignalEventDefinition newElement = this.genericFactory.create(BpmnSignalEventDefinition.class,
                this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("92e6c5bc-1297-4327-80b6-33dd9db5e80e")
    @Override
    public BpmnStandardLoopCharacteristics createBpmnStandardLoopCharacteristics() {
        BpmnStandardLoopCharacteristics newElement = this.genericFactory.create(BpmnStandardLoopCharacteristics.class,
                this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("b02c0902-401e-4d30-9ac6-a6e3c03c0c47")
    @Override
    public BpmnStartEvent createBpmnStartEvent() {
        BpmnStartEvent newElement = this.genericFactory.create(BpmnStartEvent.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("89fc13c6-d0f5-4b0d-a590-90862a6daffa")
    @Override
    public BpmnSubProcess createBpmnSubProcess() {
        BpmnSubProcess newElement = this.genericFactory.create(BpmnSubProcess.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("8f881c2b-a64f-4b9c-87b4-25d07f1feca2")
    @Override
    public BpmnSubProcessDiagram createBpmnSubProcessDiagram() {
        BpmnSubProcessDiagram newElement = this.genericFactory.create(BpmnSubProcessDiagram.class,
                this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("504c244c-df1a-4243-916b-0d7ef7ecadbe")
    @Override
    public BpmnTask createBpmnTask() {
        BpmnTask newElement = this.genericFactory.create(BpmnTask.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("5c3489fd-4576-45cd-a411-2eb5a844972e")
    @Override
    public BpmnTerminateEventDefinition createBpmnTerminateEventDefinition() {
        BpmnTerminateEventDefinition newElement = this.genericFactory.create(BpmnTerminateEventDefinition.class,
                this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("9e456c0a-7b71-4ed1-86df-365bc44c988a")
    @Override
    public BpmnTimerEventDefinition createBpmnTimerEventDefinition() {
        BpmnTimerEventDefinition newElement = this.genericFactory.create(BpmnTimerEventDefinition.class,
                this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("a03105e0-f705-435a-a46c-994484285fad")
    @Override
    public BpmnTransaction createBpmnTransaction() {
        BpmnTransaction newElement = this.genericFactory.create(BpmnTransaction.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("37966c9a-5a2d-4a9b-94f3-34fff55227b5")
    @Override
    public BpmnUserTask createBpmnUserTask() {
        BpmnUserTask newElement = this.genericFactory.create(BpmnUserTask.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("193becc0-7efb-467a-9e71-2010eeac9bf3")
    @Override
    public CallBehaviorAction createCallBehaviorAction() {
        CallBehaviorAction newElement = this.genericFactory.create(CallBehaviorAction.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("4c337c31-7117-4e37-9103-d08e2ff6a259")
    @Override
    public CallOperationAction createCallOperationAction() {
        CallOperationAction newElement = this.genericFactory.create(CallOperationAction.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("a728e560-4354-481a-8428-ab7bf944b118")
    @Override
    public CentralBufferNode createCentralBufferNode() {
        CentralBufferNode newElement = this.genericFactory.create(CentralBufferNode.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("57a6d6b4-18f1-497a-9665-a54f8dec07a0")
    @Override
    public ChoicePseudoState createChoicePseudoState() {
        ChoicePseudoState newElement = this.genericFactory.create(ChoicePseudoState.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("76373e48-81dd-4fe0-8b86-d08ada65e8a5")
    @Override
    public Class createClass() {
        Class newElement = this.genericFactory.create(Class.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("56395cda-2703-407b-bd52-776081ecb222")
    @Override
    public Class createClass(String name, NameSpace owner) {
        Class newElement = this.genericFactory.create(Class.class, owner);
        newElement.setName(name);
        newElement.setOwner(owner);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("3a21476f-229c-46d6-8311-bd15bc2c33c0")
    @Override
    public Class createClass(String name, NameSpace owner, Stereotype stereotype) {
        Class newElement = this.genericFactory.create(Class.class, owner);
        newElement.setName(name);
        newElement.setOwner(owner);
        if (stereotype != null) {
            newElement.getExtension().add(stereotype);
        }
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("5d0af5a4-d33b-4b11-8e8c-5de2fda94ef8")
    @Override
    public Class createClass(String name, NameSpace owner, String moduleName, String stereotypeName) throws ExtensionNotFoundException {
        return createClass(name, owner, resolveStereotype(moduleName, stereotypeName, this.metamodel.getMClass(Class.class)));
    }

    @objid ("a9f4bbad-63fd-49a9-a41b-9e59fad1c769")
    @Override
    public ClassAssociation createClassAssociation() {
        ClassAssociation newElement = this.genericFactory.create(ClassAssociation.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("b285c8e7-b722-48b2-8e09-63f7bb02aceb")
    @Override
    public ClassDiagram createClassDiagram() {
        ClassDiagram newElement = this.genericFactory.create(ClassDiagram.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("10e46e8b-5b6b-4df2-a030-d46ba7630302")
    @Override
    public ClassDiagram createClassDiagram(final String name, final ModelElement diagramContext, final Stereotype stereotype) {
        ClassDiagram newElement = this.genericFactory.create(ClassDiagram.class, diagramContext);
        newElement.setName(name);
        newElement.setOrigin(diagramContext);
        if (stereotype != null) {
            newElement.getExtension().add(stereotype);
        }
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("ce1b2c86-8a2a-4929-956f-23d59d96c7af")
    @Override
    public Clause createClause() {
        Clause newElement = this.genericFactory.create(Clause.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("dd7db0f6-a2f7-4c38-8b28-07aaf3ea2a4d")
    @Override
    public Collaboration createCollaboration() {
        Collaboration newElement = this.genericFactory.create(Collaboration.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("674a6163-b1f6-4ffd-b574-d1f6cf1f9850")
    @Override
    public CollaborationUse createCollaborationUse() {
        CollaborationUse newElement = this.genericFactory.create(CollaborationUse.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("3f36f819-d310-42ea-b6ea-a50c0940407d")
    @Override
    public CombinedFragment createCombinedFragment() {
        CombinedFragment newElement = this.genericFactory.create(CombinedFragment.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("a379df05-c1f3-4804-9718-6c98b9bc93e4")
    @Override
    public CombinedFragment createCombinedFragment(InteractionOperator operator) {
        CombinedFragment newElement = this.genericFactory.create(CombinedFragment.class, this.scratchRepository);
        newElement.setOperator(operator);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("f8eb1232-dc89-4d99-8dd0-8b1580909b3c")
    @Override
    public CommunicationChannel createCommunicationChannel() {
        CommunicationChannel newElement = this.genericFactory.create(CommunicationChannel.class,
                this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("e24de720-5bbd-4879-8b05-3ea29bb366a9")
    @Override
    public CommunicationDiagram createCommunicationDiagram() {
        CommunicationDiagram newElement = this.genericFactory.create(CommunicationDiagram.class,
                this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("46a9414c-abdb-44c7-a59c-613e31eb1cbb")
    @Override
    public CommunicationDiagram createCommunicationDiagram(String name, ModelElement diagramContext) {
        CommunicationDiagram newElement = this.genericFactory.create(CommunicationDiagram.class, diagramContext);
        newElement.setName(name);
        newElement.setOrigin(diagramContext);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("009b2c85-fa34-4997-bcdc-790ecb7f0404")
    @Override
    public CommunicationDiagram createCommunicationDiagram(final String name, final ModelElement diagramContext, final Stereotype stereotype) {
        CommunicationDiagram newElement = this.genericFactory.create(CommunicationDiagram.class, diagramContext);
        newElement.setName(name);
        newElement.setOrigin(diagramContext);
        if (stereotype != null) {
            newElement.getExtension().add(stereotype);
        }
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("8eaa382e-23af-4d6e-9ad9-791e66c56ae6")
    @Override
    public CommunicationInteraction createCommunicationInteraction() {
        CommunicationInteraction newElement = this.genericFactory.create(CommunicationInteraction.class,
                this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("f801f924-7ad7-4289-b327-814c0e7c0bd3")
    @Override
    public CommunicationMessage createCommunicationMessage() {
        CommunicationMessage newElement = this.genericFactory.create(CommunicationMessage.class,
                this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("d22cbe48-bf45-4c4b-b7d5-f8de9cc93469")
    @Override
    public CommunicationNode createCommunicationNode() {
        CommunicationNode newElement = this.genericFactory.create(CommunicationNode.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("60096452-e988-4865-92c0-cec5c472c11c")
    @Override
    public Component createComponent() {
        Component newElement = this.genericFactory.create(Component.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("9d1188a8-e183-4010-8523-1edc1cae60b1")
    @Override
    public Component createComponent(String name, NameSpace owner) {
        Component newElement = this.genericFactory.create(Component.class, owner);
        newElement.setName(name);
        newElement.setOwner(owner);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("110de1f3-3286-4ac7-b763-7ea1a6108306")
    @Override
    public Component createComponent(String name, NameSpace owner, Stereotype stereotype) {
        Component newElement = this.genericFactory.create(Component.class, owner);
        newElement.setName(name);
        newElement.setOwner(owner);
        if (stereotype != null) {
            newElement.getExtension().add(stereotype);
        }
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("56b3b772-d1a2-4a85-b816-ba4a0633d3eb")
    @Override
    public Component createComponent(String name, NameSpace owner, String moduleName, String stereotypeName) throws ExtensionNotFoundException {
        return createComponent(name, owner, resolveStereotype(moduleName, stereotypeName, this.metamodel.getMClass(Component.class)));
    }

    @objid ("e17ca827-d01f-423e-880a-a292d4ca39c7")
    @Override
    public ComponentRealization createComponentRealization() {
        ComponentRealization newElement = this.genericFactory.create(ComponentRealization.class,
                this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("aad14085-0adf-43f4-a613-bfcda428df25")
    @Override
    public ComponentRealization createComponentRealization(Classifier source, Component destination) {
        ComponentRealization newElement = this.genericFactory.create(ComponentRealization.class,
                this.scratchRepository);
        newElement.setRealizingClassifier(source);
        newElement.setAbstraction(destination);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("09b34f6e-896c-4d1f-b4f9-4bcbcae24f0d")
    @Override
    public CompositeStructureDiagram createCompositeStructureDiagram() {
        CompositeStructureDiagram newElement = this.genericFactory.create(CompositeStructureDiagram.class,
                this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("941fbcab-6424-4863-b057-2fb3deaac8ce")
    @Override
    public CompositeStructureDiagram createCompositeStructureDiagram(final String name, final ModelElement diagramContext, final Stereotype stereotype) {
        CompositeStructureDiagram newElement = this.genericFactory.create(CompositeStructureDiagram.class,
                diagramContext);
        newElement.setName(name);
        newElement.setOrigin(diagramContext);
        if (stereotype != null) {
            newElement.getExtension().add(stereotype);
        }
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("503eca61-6ffe-4b21-9f44-dc1625ca0ea6")
    @Override
    public Association createComposition(Classifier source, Classifier destination, String roleName) {
        AssociationEnd sourceRole = createAssociationEnd();
        sourceRole.setSource(source);
        sourceRole.setTarget(destination);
        sourceRole.setAggregation(AggregationKind.KINDISCOMPOSITION);
        sourceRole.setName(roleName);
        
        AssociationEnd destinationRole = createAssociationEnd();
        
        // Opposite relation must be set for both ends
        destinationRole.setOpposite(sourceRole);
        sourceRole.setOpposite(destinationRole);
        
        // Create the association itself
        Association newAssoc = createAssociation();
        destinationRole.setAssociation(newAssoc);
        sourceRole.setAssociation(newAssoc);
        return newAssoc;
    }

    @objid ("33901087-9967-4368-8c3a-d170077cd645")
    @Override
    public ConditionalNode createConditionalNode() {
        ConditionalNode newElement = this.genericFactory.create(ConditionalNode.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("0817bbb4-09ba-47c6-b702-afdcc6e8cc75")
    @Override
    public ConnectionPointReference createConnectionPointReference() {
        ConnectionPointReference newElement = this.genericFactory.create(ConnectionPointReference.class,
                this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("02d6bf44-99c7-457a-abe0-b42a82140ddb")
    @Override
    public Connector createConnector(BindableInstance source, BindableInstance destination, String destinationRoleName) {
        ConnectorEnd sourceRole = createConnectorEnd();
        sourceRole.setSource(source);
        sourceRole.setTarget(destination);
        
        ConnectorEnd destinationRole = createConnectorEnd();
        destinationRole.setName(destinationRoleName);
        
        // Opposite relation must be set for both ends
        destinationRole.setOpposite(sourceRole);
        sourceRole.setOpposite(destinationRole);
        
        // Create the link itself
        Connector newConnector = createConnector();
        destinationRole.setLink(newConnector);
        sourceRole.setLink(newConnector);
        return newConnector;
    }

    @objid ("ecb3ec08-a67b-43b3-9b34-29756e3c0d46")
    @Override
    public Connector createConnector() {
        Connector newElement = this.genericFactory.create(Connector.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("c257a9be-009b-4206-a688-0f5ba87179b9")
    @Override
    public ConnectorEnd createConnectorEnd() {
        ConnectorEnd newElement = this.genericFactory.create(ConnectorEnd.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("a9943ca7-6cf9-4145-a4d7-39f93affa7a8")
    @Override
    public Constraint createConstraint() {
        Constraint newElement = this.genericFactory.create(Constraint.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("00cd1cfd-1dc9-4d71-84a0-2cefdc7b79dd")
    @Override
    public ControlFlow createControlFlow() {
        ControlFlow newElement = this.genericFactory.create(ControlFlow.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("965d38dd-6578-4b02-88c0-04367724afac")
    @Override
    public DataFlow createDataFlow() {
        DataFlow newElement = this.genericFactory.create(DataFlow.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("fdb6b83b-b018-444c-b31b-e471c1689433")
    @Override
    public DataStoreNode createDataStoreNode() {
        DataStoreNode newElement = this.genericFactory.create(DataStoreNode.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("69bd8391-4b28-458a-ac51-f27ee82245c0")
    @Override
    public DataType createDataType() {
        DataType newElement = this.genericFactory.create(DataType.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("c0246f98-8d41-4851-8bc0-aa12911661a3")
    @Override
    public DataType createDataType(String name, NameSpace owner) {
        DataType newElement = this.genericFactory.create(DataType.class, this.scratchRepository);
        newElement.setName(name);
        newElement.setOwner(owner);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("85a6d46f-3f30-4da3-ad8f-3ee1d8e85260")
    @Override
    public DataType createDataType(String name, NameSpace owner, Stereotype stereotype) {
        DataType newElement = this.genericFactory.create(DataType.class, owner);
        newElement.setName(name);
        newElement.setOwner(owner);
        if (stereotype != null) {
            newElement.getExtension().add(stereotype);
        }
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("681f17bd-47a8-4d82-b0af-982f2ff95e92")
    @Override
    public DataType createDataType(String name, NameSpace owner, String moduleName, String stereotypeName) throws ExtensionNotFoundException {
        return createDataType(name, owner, resolveStereotype(moduleName, stereotypeName, this.metamodel.getMClass(DataType.class)));
    }

    @objid ("722f7798-5840-4afa-93c2-5d45b0eca5de")
    @Override
    public DecisionMergeNode createDecisionMergeNode() {
        DecisionMergeNode newElement = this.genericFactory.create(DecisionMergeNode.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("acf53764-9c6e-40d5-aa80-f7cd7ab48b08")
    @Override
    public DeepHistoryPseudoState createDeepHistoryPseudoState() {
        DeepHistoryPseudoState newElement = this.genericFactory.create(DeepHistoryPseudoState.class,
                this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("00a5a2b1-34af-4337-81e9-ec48e351b849")
    @Override
    public DeploymentDiagram createDeploymentDiagram() {
        DeploymentDiagram newElement = this.genericFactory.create(DeploymentDiagram.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("75676ae4-4e7f-4a64-aabe-211749222ba8")
    @Override
    public DeploymentDiagram createDeploymentDiagram(String name, ModelElement diagramContext) {
        DeploymentDiagram newElement = this.genericFactory.create(DeploymentDiagram.class, this.scratchRepository);
        newElement.setName(name);
        newElement.setOrigin(diagramContext);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("93dd4992-5e34-4a0d-8bb8-09a953180908")
    @Override
    public DeploymentDiagram createDeploymentDiagram(final String name, final ModelElement diagramContext, final Stereotype stereotype) {
        DeploymentDiagram newElement = this.genericFactory.create(DeploymentDiagram.class, diagramContext);
        newElement.setName(name);
        newElement.setOrigin(diagramContext);
        if (stereotype != null) {
            newElement.getExtension().add(stereotype);
        }
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("74075d69-7eb9-49e1-a11f-c9ef033424e3")
    @Override
    public DurationConstraint createDurationConstraint() {
        DurationConstraint newElement = this.genericFactory.create(DurationConstraint.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("f57f7b8e-3806-40d3-8dd3-ecb63246aab5")
    @Override
    public <T extends MObject> T createElement(java.lang.Class<T> metaclass) {
        T newElement = this.genericFactory.create(metaclass, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("3b8facca-d68f-45ae-abf1-b051f089e52c")
    @Override
    public MObject createElement(String metaclassName) {
        MObject newElement = this.genericFactory.create(metaclassName, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("1fa4265b-aa53-4bdc-a997-73ad4ca22ba0")
    @Override
    public MObject createElement(MClass metaclass) {
        MObject newElement = this.genericFactory.create(metaclass, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("c58e4b27-2418-45b8-8632-701e2104eca9")
    @Override
    public MObject createElement(MClass metaclass, MObject owner, MDependency dependency) {
        MObject newElement = this.genericFactory.create(metaclass, owner, dependency);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("d3c7f044-5a20-4e24-8501-49f51b4087ed")
    @Override
    public MObject createElement(String metaclassName, MObject owner, String dependencyName) {
        // TODO for all createElement generic methods, process errors (unknown
        // dep unknown metaclass, null objects ... )
        MObject newElement = this.genericFactory.create(metaclassName, owner, dependencyName);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("fd0df3aa-b516-49af-88f3-221a246236ef")
    @Override
    public <T extends MObject> T createElement(java.lang.Class<T> metaclass, MObject owner, String dependencyName) {
        T newElement = this.genericFactory.create(metaclass, owner, dependencyName);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("ca6b830a-b606-4af6-b3e0-a2c4cb28c198")
    @Override
    public ElementImport createElementImport() {
        ElementImport newElement = this.genericFactory.create(ElementImport.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("ec5bfc50-dd6f-447a-8336-00a1131db1c2")
    @Override
    public ElementImport createElementImport(NameSpace source, NameSpace destination) {
        ElementImport newElement = this.genericFactory.create(ElementImport.class, source);
        newElement.setImportingNameSpace(source);
        newElement.setImportedElement(destination);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("c8858587-76cb-4e6c-afef-ab8df616d7ac")
    @Override
    public ElementImport createElementImport(Operation source, NameSpace destination) {
        ElementImport newElement = this.genericFactory.create(ElementImport.class, source);
        newElement.setImportingOperation(source);
        newElement.setImportedElement(destination);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("b9381503-f393-47ff-8a10-42ce45e48546")
    @Override
    public ElementRealization createElementRealization() {
        ElementRealization newElement = this.genericFactory.create(ElementRealization.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("c7fb9dce-4577-4db3-b6f0-4f452715c1b3")
    @Override
    public EntryPointPseudoState createEntryPointPseudoState() {
        EntryPointPseudoState newElement = this.genericFactory.create(EntryPointPseudoState.class,
                this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("48722f57-031b-4c1f-92b4-3455efee18f9")
    @Override
    public Enumeration createEnumeration() {
        Enumeration newElement = this.genericFactory.create(Enumeration.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("1381f772-54ef-4714-a315-84bda8593915")
    @Override
    public Enumeration createEnumeration(String name, NameSpace owner) {
        Enumeration newElement = this.genericFactory.create(Enumeration.class, owner);
        newElement.setName(name);
        newElement.setOwner(owner);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("e0f113c4-58b7-4eb8-b53c-e14013e5a5ef")
    @Override
    public Enumeration createEnumeration(String name, NameSpace owner, Stereotype stereotype) {
        Enumeration newElement = this.genericFactory.create(Enumeration.class, owner);
        newElement.setName(name);
        newElement.setOwner(owner);
        if (stereotype != null) {
            newElement.getExtension().add(stereotype);
        }
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("b1063a8e-3115-47a3-9f9b-e9a8a255efbd")
    @Override
    public Enumeration createEnumeration(String name, NameSpace owner, String moduleName, String stereotypeName) throws ExtensionNotFoundException {
        return createEnumeration(name, owner, resolveStereotype(moduleName, stereotypeName, this.metamodel.getMClass(Enumeration.class)));
    }

    @objid ("0933ad89-28e1-43ba-889e-c0b6ab730940")
    @Override
    public EnumerationLiteral createEnumerationLiteral() {
        EnumerationLiteral newElement = this.genericFactory.create(EnumerationLiteral.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("6915e422-be40-4760-9dbb-10a2c6af0179")
    @Override
    public EnumerationLiteral createEnumerationLiteral(String name, Enumeration owner) {
        EnumerationLiteral newElement = this.genericFactory.create(EnumerationLiteral.class, owner);
        newElement.setName(name);
        newElement.setValuated(owner);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("a8eed0af-5dbc-4211-a9f1-49193fb0cbda")
    @Override
    public EnumerationLiteral createEnumerationLiteral(String name, Enumeration owner, Stereotype stereotype) {
        EnumerationLiteral newElement = this.genericFactory.create(EnumerationLiteral.class, owner);
        newElement.setName(name);
        newElement.setValuated(owner);
        if (stereotype != null) {
            newElement.getExtension().add(stereotype);
        }
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("80673d17-8b5f-448f-9235-0afd64b1ec1c")
    @Override
    public EnumerationLiteral createEnumerationLiteral(String name, Enumeration owner, String moduleName, String stereotypeName) throws ExtensionNotFoundException {
        return createEnumerationLiteral(name, owner, resolveStereotype(moduleName, stereotypeName, this.metamodel.getMClass(EnumerationLiteral.class)));
    }

    @objid ("c720ce0f-f2c7-48a1-b754-5b04b936f8c3")
    @Override
    public Event createEvent() {
        Event newElement = this.genericFactory.create(Event.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("78006b28-13d1-4660-89f3-6f9e050ce232")
    @Override
    public ExceptionHandler createExceptionHandler() {
        ExceptionHandler newElement = this.genericFactory.create(ExceptionHandler.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("e8cdc5dd-b918-4fc1-9cb1-69ebcd7e4ccb")
    @Override
    public ExecutionOccurenceSpecification createExecutionOccurenceSpecification() {
        ExecutionOccurenceSpecification newElement = this.genericFactory.create(ExecutionOccurenceSpecification.class,
                this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("04448dfd-0b82-4510-bd68-6c6c1d55ccaa")
    @Override
    public ExecutionSpecification createExecutionSpecification() {
        ExecutionSpecification newElement = this.genericFactory.create(ExecutionSpecification.class,
                this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("95a5fff8-55ae-4599-80a7-d5b4a71f5b94")
    @Override
    public ExitPointPseudoState createExitPointPseudoState() {
        ExitPointPseudoState newElement = this.genericFactory.create(ExitPointPseudoState.class,
                this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("07383157-5e08-48ef-bdc7-3e6889048b41")
    @Override
    public ExpansionNode createExpansionNode() {
        ExpansionNode newElement = this.genericFactory.create(ExpansionNode.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("9f9abe24-eb38-49e3-95a8-c4b7b16f4eab")
    @Override
    public ExpansionRegion createExpansionRegion() {
        ExpansionRegion newElement = this.genericFactory.create(ExpansionRegion.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("479af3b6-8815-4f5c-9159-376daef6eb40")
    @Override
    public UseCaseDependency createExtendUseCaseDependency(UseCase source, UseCase destination) throws ExtensionNotFoundException {
        Stereotype stereotype = resolveStereotype("ModelerModule", "extend", this.metamodel.getMClass(UseCaseDependency.class));
        
        UseCaseDependency newElement = this.genericFactory.create(UseCaseDependency.class, source);
        newElement.setOrigin(source);
        newElement.setTarget(destination);
        newElement.getExtension().add(stereotype);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("85323acb-41f8-4478-a640-8f3296554ebe")
    @Override
    public ExtensionPoint createExtensionPoint() {
        ExtensionPoint newElement = this.genericFactory.create(ExtensionPoint.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("6b0372ae-9c76-454d-9ed6-3826a233bf54")
    @Override
    public FinalState createFinalState() {
        FinalState newElement = this.genericFactory.create(FinalState.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("87263574-5eb0-4a9f-8d27-7300009c5451")
    @Override
    public FlowFinalNode createFlowFinalNode() {
        FlowFinalNode newElement = this.genericFactory.create(FlowFinalNode.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("6bb538c3-06f5-4c47-8996-dd983124053d")
    @Override
    public ForkJoinNode createForkJoinNode() {
        ForkJoinNode newElement = this.genericFactory.create(ForkJoinNode.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("4761c63b-f76b-4fcf-9c36-96f3099dd929")
    @Override
    public ForkPseudoState createForkPseudoState() {
        ForkPseudoState newElement = this.genericFactory.create(ForkPseudoState.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("283d883b-754a-416f-a2de-003846b3dd3b")
    @Override
    public Gate createGate() {
        Gate newElement = this.genericFactory.create(Gate.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("766602fa-ef8d-4733-8991-63a8834e514b")
    @Override
    public Gate createGate(String name) {
        Gate newElement = this.genericFactory.create(Gate.class, this.scratchRepository);
        newElement.setName(name);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("1eec6fb4-e9ce-4921-ae6b-1f2ff2fe2e1c")
    @Override
    public GeneralOrdering createGeneralOrdering() {
        GeneralOrdering newElement = this.genericFactory.create(GeneralOrdering.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("b1f49dd3-4279-4f58-985f-2996576be3e0")
    @Override
    public Generalization createGeneralization() {
        Generalization newElement = this.genericFactory.create(Generalization.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("b996019b-4b16-4865-a4d2-662b709aed87")
    @Override
    public Generalization createGeneralization(NameSpace source, NameSpace destination) {
        Generalization newElement = this.genericFactory.create(Generalization.class, source);
        newElement.setSubType(source);
        newElement.setSuperType(destination);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("1ca77225-3af5-41db-b1df-e2ca87521f4c")
    @Override
    public Parameter createIOParameter(String name, GeneralClass type, Operation owner) {
        Parameter newElement = this.genericFactory.create(Parameter.class, owner);
        newElement.setName(name);
        newElement.setComposed(owner);
        this.elementInitializer.initialize(newElement);
        newElement.setType(type);
        return newElement;
    }

    @objid ("bd32e1ca-8033-4f2b-ac63-4a9b7832b67b")
    @Override
    public Parameter createIOParameter(String name, GeneralClass type, Operation owner, Stereotype stereotype) {
        Parameter newElement = this.genericFactory.create(Parameter.class, owner);
        newElement.setName(name);
        newElement.setComposed(owner);
        if (stereotype != null) {
            newElement.getExtension().add(stereotype);
        }
        this.elementInitializer.initialize(newElement);
        newElement.setType(type);
        return newElement;
    }

    @objid ("863e4df2-a6eb-44c9-8865-8af1b7b2545f")
    @Override
    public Parameter createIOParameter(String name, GeneralClass type, Operation owner, String moduleName, String stereotypeName) throws ExtensionNotFoundException {
        return createIOParameter(name, type, owner, resolveStereotype(moduleName, stereotypeName, this.metamodel.getMClass(Parameter.class)));
    }

    @objid ("45856a45-57cc-4d43-8f54-3ac4eaf2085d")
    @Override
    public UseCaseDependency createIncludeUseCaseDependency(UseCase source, UseCase destination) throws ExtensionNotFoundException {
        Stereotype stereotype = resolveStereotype("ModelerModule", "include", this.metamodel.getMClass(UseCaseDependency.class));
        
        UseCaseDependency newElement = this.genericFactory.create(UseCaseDependency.class, source);
        newElement.setOrigin(source);
        newElement.setTarget(destination);
        newElement.getExtension().add(stereotype);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("1da069ed-1688-4d1b-86cb-b8f28662bd89")
    @Override
    public InformationFlow createInformationFlow() {
        InformationFlow newElement = this.genericFactory.create(InformationFlow.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("7d21ba30-1379-49f2-9a3e-64c530a4a322")
    @Override
    public InformationItem createInformationItem() {
        InformationItem newElement = this.genericFactory.create(InformationItem.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("5d1523b6-57a2-4073-b7d0-1060d94afef1")
    @Override
    public InitialNode createInitialNode() {
        InitialNode newElement = this.genericFactory.create(InitialNode.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("293f5a17-6343-47d2-9100-33a022fdc6fa")
    @Override
    public InitialPseudoState createInitialPseudoState() {
        InitialPseudoState newElement = this.genericFactory.create(InitialPseudoState.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("27729588-871a-4775-84cc-6caa29643090")
    @Override
    public InputPin createInputPin() {
        InputPin newElement = this.genericFactory.create(InputPin.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("7b7a2188-7ce5-4c84-b5bb-dd0892292cbc")
    @Override
    public Instance createInstance() {
        Instance newElement = this.genericFactory.create(Instance.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("077ab4f2-e219-4c81-9235-01f7e9069809")
    @Override
    public Instance createInstance(String name, Package owner) {
        Instance newElement = this.genericFactory.create(Instance.class, this.scratchRepository);
        newElement.setName(name);
        newElement.setOwner(owner);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("b12336ec-683f-4911-b0c9-764b0ecd609c")
    @Override
    public InstanceNode createInstanceNode() {
        InstanceNode newElement = this.genericFactory.create(InstanceNode.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("6d1d22fc-f547-41aa-929c-789a6753a2fc")
    @Override
    public Interaction createInteraction() {
        Interaction newElement = this.genericFactory.create(Interaction.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("281a6f66-3cef-434a-be66-5d6b39438acd")
    @Override
    public InteractionOperand createInteractionOperand() {
        InteractionOperand newElement = this.genericFactory.create(InteractionOperand.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("e6dca5b0-ea5b-4cdf-837c-9ca42a87be47")
    @Override
    public InteractionOperand createInteractionOperand(String guard) {
        InteractionOperand newElement = this.genericFactory.create(InteractionOperand.class, this.scratchRepository);
        newElement.setGuard(guard);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("26197606-a65e-48a4-a869-b2bdafc4c35c")
    @Override
    public InteractionUse createInteractionUse() {
        InteractionUse newElement = this.genericFactory.create(InteractionUse.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("6238df02-4a1a-4444-a0a8-44d6728d8c37")
    @Override
    public InteractionUse createInteractionUse(Interaction refered) {
        InteractionUse newElement = this.genericFactory.create(InteractionUse.class, this.scratchRepository);
        newElement.setRefersTo(refered);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("29aa1b2c-fb87-4bda-801d-1ce68c131d0c")
    @Override
    public Interface createInterface() {
        Interface newElement = this.genericFactory.create(Interface.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("6a34e92b-4996-4070-955a-42465c115c44")
    @Override
    public Interface createInterface(String name, NameSpace owner) {
        Interface newElement = this.genericFactory.create(Interface.class, owner);
        newElement.setName(name);
        newElement.setOwner(owner);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("89244a62-0acc-44c1-bf8c-ca4db9a5c110")
    @Override
    public Interface createInterface(String name, NameSpace owner, Stereotype stereotype) {
        Interface newElement = this.genericFactory.create(Interface.class, owner);
        newElement.setName(name);
        newElement.setOwner(owner);
        if (stereotype != null) {
            newElement.getExtension().add(stereotype);
        }
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("461e0905-c616-4f83-99d8-397395004631")
    @Override
    public Interface createInterface(String name, NameSpace owner, String moduleName, String stereotypeName) throws ExtensionNotFoundException {
        return createInterface(name, owner, resolveStereotype(moduleName, stereotypeName, this.metamodel.getMClass(Interface.class)));
    }

    @objid ("91f6202c-b28d-47d2-b666-3229178b7165")
    @Override
    public InterfaceRealization createInterfaceRealization() {
        InterfaceRealization newElement = this.genericFactory.create(InterfaceRealization.class,
                this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("e37ff9bf-090e-446e-b221-492fcded5307")
    @Override
    public InterfaceRealization createInterfaceRealization(NameSpace source, Interface destination) {
        InterfaceRealization newElement = this.genericFactory.create(InterfaceRealization.class, source);
        newElement.setImplementer(source);
        newElement.setImplemented(destination);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("24a0bdb2-9f97-4bb3-93e3-642d6a727dca")
    @Override
    public InternalTransition createInternalTransition() {
        InternalTransition newElement = this.genericFactory.create(InternalTransition.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("186603cc-78e8-48c7-a12f-5b8e6999a5ad")
    @Override
    public InterruptibleActivityRegion createInterruptibleActivityRegion() {
        InterruptibleActivityRegion newElement = this.genericFactory.create(InterruptibleActivityRegion.class,
                this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("1971cc70-1ade-47a9-aa30-86ee24061e2d")
    @Override
    public JoinPseudoState createJoinPseudoState() {
        JoinPseudoState newElement = this.genericFactory.create(JoinPseudoState.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("db002992-028f-4fe9-8630-77ebfd7031cd")
    @Override
    public JunctionPseudoState createJunctionPseudoState() {
        JunctionPseudoState newElement = this.genericFactory.create(JunctionPseudoState.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("b9bbf277-51d7-487a-8697-aed87cf7d67b")
    @Override
    public Lifeline createLifeline() {
        Lifeline newElement = this.genericFactory.create(Lifeline.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("9dadc46f-406f-4a15-ac88-13ffe793afd4")
    @Override
    public Lifeline createLifeline(String name, Interaction owner) {
        Lifeline newElement = this.genericFactory.create(Lifeline.class, owner);
        newElement.setName(name);
        newElement.setOwner(owner);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("99e02651-cc2f-4658-bdec-4c56fe0b77ef")
    @Override
    public Lifeline createLifeline(String name, Interaction owner, Instance represented) {
        Lifeline newElement = this.genericFactory.create(Lifeline.class, owner);
        newElement.setName(name);
        newElement.setOwner(owner);
        this.elementInitializer.initialize(newElement);
        newElement.setRepresented(represented);
        return newElement;
    }

    @objid ("0fbd666f-1e9b-42cb-8fed-c799a5d5cfc0")
    @Override
    public Link createLink(Instance source, Instance destination, String destinationRoleName) {
        LinkEnd sourceRole = createLinkEnd();
        sourceRole.setSource(source);
        sourceRole.setTarget(destination);
        
        LinkEnd destinationRole = createLinkEnd();
        destinationRole.setName(destinationRoleName);
        
        // Opposite relation must be set for both ends
        destinationRole.setOpposite(sourceRole);
        sourceRole.setOpposite(destinationRole);
        
        // Create the link itself
        Link newLink = createLink();
        destinationRole.setLink(newLink);
        sourceRole.setLink(newLink);
        return newLink;
    }

    @objid ("294833d8-1ac5-4964-82b2-0116c9bdcda4")
    @Override
    public Link createLink() {
        Link newElement = this.genericFactory.create(Link.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("bbf34d3f-470c-4874-a813-fce95f8dc6c3")
    @Override
    public LinkEnd createLinkEnd() {
        final LinkEnd newElement = this.genericFactory.create(LinkEnd.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("621aadc8-3f1b-40d6-b11e-f829fec42862")
    @Override
    public LoopNode createLoopNode() {
        LoopNode newElement = this.genericFactory.create(LoopNode.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("b60f7a01-b47b-44f7-983c-a796fc6862a3")
    @Override
    public Manifestation createManifestation() {
        Manifestation newElement = this.genericFactory.create(Manifestation.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("8568a4bd-7ba4-4455-9db5-5e7f50c1c842")
    @Override
    public Message createMessage() {
        Message newElement = this.genericFactory.create(Message.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("39dce3ba-ff08-4d98-a84a-a94562824726")
    @Override
    public Message createMessage(MessageSort sort) {
        Message newElement = this.genericFactory.create(Message.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        newElement.setSortOfMessage(sort);
        return newElement;
    }

    @objid ("a4999198-5d79-467c-99dd-121069bcd619")
    @Override
    public Message createMessage(MessageSort sort, Operation invoked) {
        Message newElement = this.genericFactory.create(Message.class, this.scratchRepository);
        newElement.setInvoked(invoked);
        this.elementInitializer.initialize(newElement);
        newElement.setSortOfMessage(sort);
        return newElement;
    }

    @objid ("16ae13ca-ee48-4c2f-bd5c-fe242540f84b")
    @Override
    public Message createMessage(String name, MessageSort sort) {
        Message newElement = this.genericFactory.create(Message.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        newElement.setSortOfMessage(sort);
        return newElement;
    }

    @objid ("36497262-d1fb-47f6-8ecc-10b77f2b6fea")
    @Override
    public MessageFlow createMessageFlow() {
        MessageFlow newElement = this.genericFactory.create(MessageFlow.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("d12539cf-c86e-4e8c-bce3-badf96a63d91")
    @Override
    public NaryAssociation createNaryAssociation() {
        NaryAssociation newElement = this.genericFactory.create(NaryAssociation.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("13f74d10-ad1b-439b-a26f-d88b6e7b7218")
    @Override
    public NaryAssociation createNaryAssociation(List<Classifier> ends) {
        NaryAssociation newElement = this.genericFactory.create(NaryAssociation.class, this.scratchRepository);
        for (Classifier end : ends) {
            NaryAssociationEnd newEnd = createNaryAssociationEnd();
            newEnd.setOwner(end);
            newElement.getNaryEnd().add(newEnd);
        }
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("5e0dfcd1-7b8c-401e-bcd4-f87696f142f9")
    @Override
    public NaryAssociationEnd createNaryAssociationEnd() {
        NaryAssociationEnd newElement = this.genericFactory.create(NaryAssociationEnd.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("61dcc68d-43a1-4630-85ea-d7ea4ad42def")
    @Override
    public NaryConnector createNaryConnector() {
        NaryConnector newElement = this.genericFactory.create(NaryConnector.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("01660183-782c-49d2-840a-8abb25ea0365")
    @Override
    public NaryConnector createNaryConnector(List<BindableInstance> ends) {
        NaryConnector newElement = this.genericFactory.create(NaryConnector.class, this.scratchRepository);
        
        for (BindableInstance end : ends) {
            NaryConnectorEnd newEnd = createNaryConnectorEnd();
            newEnd.setSource(end);
            newElement.getNaryLinkEnd().add(newEnd);
        }
        
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("eb459333-6c5d-4d6b-9d5c-19d183a8f1f9")
    @Override
    public NaryConnectorEnd createNaryConnectorEnd() {
        NaryConnectorEnd newElement = this.genericFactory.create(NaryConnectorEnd.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("3d48e235-1e8c-423c-b534-992173670033")
    @Override
    public NaryLink createNaryLink() {
        final NaryLink newElement = this.genericFactory.create(NaryLink.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("0b187af9-2bf6-4623-9398-86e5859d9fc2")
    @Override
    public NaryLink createNaryLink(List<Instance> ends) {
        final NaryLink newElement = this.genericFactory.create(NaryLink.class, this.scratchRepository);
        
        for (Instance end : ends) {
            NaryLinkEnd newEnd = createNaryLinkEnd();
            newEnd.setSource(end);
            newElement.getNaryLinkEnd().add(newEnd);
        }
        
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("b113251c-b233-4156-828e-df5e4a4ffd9b")
    @Override
    public NaryLinkEnd createNaryLinkEnd() {
        final NaryLinkEnd newElement = this.genericFactory.create(NaryLinkEnd.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("03e77b96-26c9-4139-b95a-429a0ea2b7db")
    @Override
    public Node createNode() {
        Node newElement = this.genericFactory.create(Node.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("1713783b-ff6e-4e36-a6d9-7d435188790d")
    @Override
    public ObjectDiagram createObjectDiagram() {
        ObjectDiagram newElement = this.genericFactory.create(ObjectDiagram.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("660370ae-d039-449e-ada1-1572bc22d94b")
    @Override
    public ObjectDiagram createObjectDiagram(String name, ModelElement diagramContext) {
        ObjectDiagram newElement = this.genericFactory.create(ObjectDiagram.class, diagramContext);
        newElement.setName(name);
        newElement.setOrigin(diagramContext);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("360a27f1-f9c8-47bd-81f3-0f4603f75179")
    @Override
    public ObjectDiagram createObjectDiagram(final String name, final ModelElement diagramContext, final Stereotype stereotype) {
        ObjectDiagram newElement = this.genericFactory.create(ObjectDiagram.class, diagramContext);
        newElement.setName(name);
        newElement.setOrigin(diagramContext);
        if (stereotype != null) {
            newElement.getExtension().add(stereotype);
        }
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("4f2fe903-4fd3-44e3-8d19-50672a6614a7")
    @Override
    public ObjectFlow createObjectFlow() {
        ObjectFlow newElement = this.genericFactory.create(ObjectFlow.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("a4c6cf9c-d7f3-454d-a6e6-7f8a9632645f")
    @Override
    public OpaqueAction createOpaqueAction() {
        OpaqueAction newElement = this.genericFactory.create(OpaqueAction.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("3ef740e8-c8a6-4b63-8b94-421309ac58fb")
    @Override
    public OpaqueBehavior createOpaqueBehavior() {
        OpaqueBehavior newElement = this.genericFactory.create(OpaqueBehavior.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("78eaf94a-40f8-4fa3-8bea-f98fee2edade")
    @Override
    public Operation createOperation() {
        Operation newElement = this.genericFactory.create(Operation.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("61dc45f9-3f54-4edb-8219-554545e4b905")
    @Override
    public Operation createOperation(String name, Classifier owner) {
        Operation newElement = this.genericFactory.create(Operation.class, owner);
        newElement.setName(name);
        newElement.setOwner(owner);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("3657c32f-8f89-4b15-8168-370a3b7f85be")
    @Override
    public Operation createOperation(String name, Classifier owner, Stereotype stereotype) {
        Operation newElement = this.genericFactory.create(Operation.class, owner);
        newElement.setName(name);
        newElement.setOwner(owner);
        if (stereotype != null) {
            newElement.getExtension().add(stereotype);
        }
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("b21166e8-ce50-4c64-84cf-a0fdcc18bb57")
    @Override
    public Operation createOperation(String name, Classifier owner, String moduleName, String stereotypeName) throws ExtensionNotFoundException {
        return createOperation(name, owner, resolveStereotype(moduleName, stereotypeName, this.metamodel.getMClass(Operation.class)));
    }

    @objid ("cd807d5b-242b-4a34-8818-679f67cb35dc")
    @Override
    public OutputPin createOutputPin() {
        OutputPin newElement = this.genericFactory.create(OutputPin.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("5835771c-07d8-4eb0-8974-a670452595e3")
    @Override
    public Package createPackage() {
        Package newElement = this.genericFactory.create(Package.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("2bea769c-20cf-4a9e-bd35-7aede926a7ab")
    @Override
    public Package createPackage(String name, NameSpace owner) {
        Package newElement = this.genericFactory.create(Package.class, owner);
        newElement.setName(name);
        newElement.setOwner(owner);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("a82d2f79-390f-45d5-9fd9-5e9e89ad71bc")
    @Override
    public Package createPackage(String name, NameSpace owner, Stereotype stereotype) {
        Package newElement = this.genericFactory.create(Package.class, owner);
        newElement.setName(name);
        newElement.setOwner(owner);
        if (stereotype != null) {
            newElement.getExtension().add(stereotype);
        }
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("a62f35fa-f7d1-499d-a9b9-f9f42eb7122b")
    @Override
    public Package createPackage(String name, NameSpace owner, String moduleName, String stereotypeName) throws ExtensionNotFoundException {
        return createPackage(name, owner, resolveStereotype(moduleName, stereotypeName, this.metamodel.getMClass(Package.class)));
    }

    @objid ("de0c1408-0fcc-489d-ad71-8c6f610d9fd7")
    @Override
    public PackageImport createPackageImport() {
        PackageImport newElement = this.genericFactory.create(PackageImport.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("6dfe6cb4-df70-41b8-81ae-25776a177927")
    @Override
    public PackageImport createPackageImport(NameSpace source, Package destination) {
        PackageImport newElement = this.genericFactory.create(PackageImport.class, source);
        newElement.setImportingNameSpace(source);
        newElement.setImportedPackage(destination);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("750769ba-5036-420e-84fc-77ad142d4c6b")
    @Override
    public PackageImport createPackageImport(Operation source, Package destination) {
        PackageImport newElement = this.genericFactory.create(PackageImport.class, source);
        newElement.setImportingOperation(source);
        newElement.setImportedPackage(destination);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("cb178441-7a71-492f-b5a2-e99b178e6206")
    @Override
    public PackageMerge createPackageMerge() {
        PackageMerge newElement = this.genericFactory.create(PackageMerge.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("946a8a11-7b04-46ee-b337-abc34b0c1486")
    @Override
    public Parameter createParameter() {
        Parameter newElement = this.genericFactory.create(Parameter.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("46ebb158-834f-456a-a605-fbbc5095c3b1")
    @Override
    public PartDecomposition createPartDecomposition() {
        PartDecomposition newElement = this.genericFactory.create(PartDecomposition.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("3563058a-514a-4202-a297-bea64afe8538")
    @Override
    public PartDecomposition createPartDecomposition(Interaction refered) {
        PartDecomposition newElement = this.genericFactory.create(PartDecomposition.class, this.scratchRepository);
        newElement.setRefersTo(refered);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("aee04a62-308c-4e53-9e5e-48acb28778fa")
    @Override
    public Port createPort() {
        Port newElement = this.genericFactory.create(Port.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("a8ebee35-1bbd-47bd-87c1-6ec353e7b504")
    @Override
    public Port createPort(String name, Instance owner) {
        Port newElement = this.genericFactory.create(Port.class, owner);
        newElement.setName(name);
        newElement.setCluster(owner);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("9fea0d4d-53f7-4f62-ad39-eb9d6afd9c40")
    @Override
    public Port createPort(String name, Classifier owner) {
        Port newElement = this.genericFactory.create(Port.class, owner);
        newElement.setName(name);
        newElement.setInternalOwner(owner);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("82343fd3-a8d1-48dd-a4b1-97731d02a8a0")
    @Override
    public Project createProject(IRepository repository) {
        Project newElement = this.genericFactory.create(Project.class, repository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("d271b99d-bcd7-48e2-a7b4-6e4bc0c6a863")
    @Override
    public ProvidedInterface createProvidedInterface() {
        ProvidedInterface newElement = this.genericFactory.create(ProvidedInterface.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("f90a07d4-bae0-4848-9d07-b1750b0a2499")
    @Override
    public ProvidedInterface createProvidedInterface(Port owner, List<Interface> interfaces) {
        ProvidedInterface newElement = this.genericFactory.create(ProvidedInterface.class, owner);
        newElement.setProviding(owner);
        for (Interface interface1 : interfaces) {
            newElement.getProvidedElement().add(interface1);
        }
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("f009b3dd-5d4b-4659-ba44-3f8816913dc5")
    @Override
    public RaisedException createRaisedException() {
        RaisedException newElement = this.genericFactory.create(RaisedException.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("9b6b7b54-9a12-4322-b589-971399ac8b93")
    @Override
    public Region createRegion() {
        Region newElement = this.genericFactory.create(Region.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("1445b04a-382b-4717-959a-df712d370b24")
    @Override
    public RequiredInterface createRequiredInterface() {
        RequiredInterface newElement = this.genericFactory.create(RequiredInterface.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("e3f6719f-2352-44ca-b038-8b1fb0d6a284")
    @Override
    public RequiredInterface createRequiredInterface(Port owner, List<Interface> interfaces) {
        RequiredInterface newElement = this.genericFactory.create(RequiredInterface.class, owner);
        newElement.setRequiring(owner);
        this.elementInitializer.initialize(newElement);
        for (Interface interface1 : interfaces) {
            newElement.getRequiredElement().add(interface1);
        }
        return newElement;
    }

    @objid ("f313d2f9-607a-4cce-8949-c98589f05a5f")
    @Override
    public Parameter createReturnParameter(String name, GeneralClass type, Operation owner) {
        Parameter newElement = this.genericFactory.create(Parameter.class, owner);
        newElement.setReturned(owner);
        newElement.setName(name);
        this.elementInitializer.initialize(newElement);
        newElement.setType(type);
        return newElement;
    }

    @objid ("9fe06181-e8c5-43a0-a513-2a20a3f32ab3")
    @Override
    public Parameter createReturnParameter(String name, GeneralClass type, Operation owner, Stereotype stereotype) {
        Parameter newElement = this.genericFactory.create(Parameter.class, owner);
        newElement.setName(name);
        newElement.setReturned(owner);
        if (stereotype != null) {
            newElement.getExtension().add(stereotype);
        }
        this.elementInitializer.initialize(newElement);
        newElement.setType(type);
        return newElement;
    }

    @objid ("0ccdd41f-b11f-4c67-b5e3-3827041be582")
    @Override
    public Parameter createReturnParameter(String name, GeneralClass type, Operation owner, String moduleName, String stereotypeName) throws ExtensionNotFoundException {
        return createReturnParameter(name, type, owner, resolveStereotype(moduleName, stereotypeName, this.metamodel.getMClass(Parameter.class)));
    }

    @objid ("9687efe3-2014-41ef-9374-508ad4a31540")
    @Override
    public SendSignalAction createSendSignalAction() {
        SendSignalAction newElement = this.genericFactory.create(SendSignalAction.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("52e7880d-38ce-45bc-afe2-15f743279383")
    @Override
    public SequenceDiagram createSequenceDiagram() {
        SequenceDiagram newElement = this.genericFactory.create(SequenceDiagram.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("9630d035-ca3a-4c09-8d7f-c7b979c75a8c")
    @Override
    public SequenceDiagram createSequenceDiagram(String name, ModelElement contextElement) {
        SequenceDiagram newElement = this.genericFactory.create(SequenceDiagram.class, contextElement);
        newElement.setName(name);
        newElement.setOrigin(contextElement);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("42612691-2b58-4a09-9305-0772d1e49924")
    @Override
    public ShallowHistoryPseudoState createShallowHistoryPseudoState() {
        ShallowHistoryPseudoState newElement = this.genericFactory.create(ShallowHistoryPseudoState.class,
                this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("b64e3a1a-0d2f-465d-b4a2-ece7c084abdb")
    @Override
    public Signal createSignal() {
        Signal newElement = this.genericFactory.create(Signal.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("825fa77f-b969-4bdd-bbdc-29ae55620f30")
    @Override
    public State createState() {
        State newElement = this.genericFactory.create(State.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("94448f4b-bfd2-4d6e-bba2-c3160a09eb7e")
    @Override
    public StateInvariant createStateInvariant() {
        StateInvariant newElement = this.genericFactory.create(StateInvariant.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("5a1f4c72-97de-4494-99c8-64d300ddd512")
    @Override
    public StateInvariant createStateInvariant(String body) {
        StateInvariant newElement = this.genericFactory.create(StateInvariant.class, this.scratchRepository);
        newElement.setBody(body);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("2061c98b-3e3d-45ea-8775-527be1ac6b60")
    @Override
    public StateMachine createStateMachine() {
        StateMachine newElement = this.genericFactory.create(StateMachine.class, this.scratchRepository);
        Region region = createRegion();
        newElement.setTop(region);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("e67a8bb2-1a2f-4a41-8ade-c935ceffab9e")
    @Override
    public StateMachineDiagram createStateMachineDiagram() {
        StateMachineDiagram newElement = this.genericFactory.create(StateMachineDiagram.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("c2d76bff-3fd9-45a7-a748-d03bbc382d08")
    @Override
    public StateMachineDiagram createStateMachineDiagram(final String name, final ModelElement diagramContext, final Stereotype stereotype) {
        StateMachineDiagram newElement = this.genericFactory.create(StateMachineDiagram.class, diagramContext);
        newElement.setName(name);
        newElement.setOrigin(diagramContext);
        attachStereotype(newElement, stereotype);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("a86301db-7305-400e-bdce-cb1cb611dee8")
    @Override
    public StaticDiagram createStaticDiagram() {
        StaticDiagram newElement = this.genericFactory.create(StaticDiagram.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("23f04611-e24a-4f76-98f7-b481bf32c273")
    @Override
    public StaticDiagram createStaticDiagram(String name, ModelElement contextElement, Stereotype stereotype) {
        StaticDiagram newElement = this.genericFactory.create(StaticDiagram.class, contextElement);
        newElement.setName(name);
        newElement.setOrigin(contextElement);
        attachStereotype(newElement, stereotype);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("6eeadc3e-f13d-4239-9ecb-b0785b6d1893")
    @Override
    public StaticDiagram createStaticDiagram(String name, ModelElement owner, String moduleName, String stereotypeName) throws ExtensionNotFoundException {
        return createStaticDiagram(name, owner, resolveStereotype(moduleName, stereotypeName, this.metamodel.getMClass(StaticDiagram.class)));
    }

    @objid ("77640bc3-ea84-411b-a698-3097913c6224")
    @Override
    public StructuredActivityNode createStructuredActivityNode() {
        StructuredActivityNode newElement = this.genericFactory.create(StructuredActivityNode.class,
                this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("89d3127b-9959-447f-9d32-3d40aec1688c")
    @Override
    public Substitution createSubstitution() {
        Substitution newElement = this.genericFactory.create(Substitution.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("ccd4e66d-8302-4ebe-899f-bff83d9695d6")
    @Override
    public TemplateBinding createTemplateBinding() {
        TemplateBinding newElement = this.genericFactory.create(TemplateBinding.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("91657627-c701-43d5-94b0-dbdcc77809da")
    @Override
    public TemplateParameter createTemplateParameter() {
        TemplateParameter newElement = this.genericFactory.create(TemplateParameter.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("d854802f-1837-45c8-9ef0-0cbc715ce056")
    @Override
    public TemplateParameterSubstitution createTemplateParameterSubstitution() {
        TemplateParameterSubstitution newElement = this.genericFactory.create(TemplateParameterSubstitution.class,
                this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("11dd0c50-ba31-432c-bdfe-010b08474948")
    @Override
    public TerminatePseudoState createTerminatePseudoState() {
        TerminatePseudoState newElement = this.genericFactory.create(TerminatePseudoState.class,
                this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("a0597116-fab0-4b3f-87ee-d64c1fe90b16")
    @Override
    public TerminateSpecification createTerminateSpecification() {
        TerminateSpecification newElement = this.genericFactory.create(TerminateSpecification.class,
                this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("b9540d34-c684-4a44-8537-b31af680901a")
    @Override
    public Transition createTransition() {
        Transition newElement = this.genericFactory.create(Transition.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("b061bfea-8ee2-445b-98b5-9db2d020295f")
    @Override
    public Usage createUsage() {
        Usage newElement = this.genericFactory.create(Usage.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("21e07d71-37c2-4a55-9a15-940ebab4e58d")
    @Override
    public Usage createUsage(ModelElement source, ModelElement destination) {
        Usage newElement = this.genericFactory.create(Usage.class, source);
        newElement.setImpacted(source);
        newElement.setDependsOn(destination);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("7e9e0841-65ca-4c3e-9c7b-0538bd81675e")
    @Override
    public UseCase createUseCase() {
        UseCase newElement = this.genericFactory.create(UseCase.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("ed475b83-f901-4fb2-bb31-618024b0dd4c")
    @Override
    public UseCase createUseCase(String name, NameSpace owner) {
        UseCase newElement = this.genericFactory.create(UseCase.class, this.scratchRepository);
        newElement.setName(name);
        newElement.setOwner(owner);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("1bf70d92-502f-474e-a1c6-955aafa1c76a")
    @Override
    public UseCase createUseCase(String name, NameSpace owner, Stereotype stereotype) {
        UseCase newElement = this.genericFactory.create(UseCase.class, owner);
        newElement.setName(name);
        newElement.setOwner(owner);
        attachStereotype(newElement, stereotype);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("7769a321-3c24-4904-9475-2518fb477d84")
    @Override
    public UseCase createUseCase(String name, NameSpace owner, String moduleName, String stereotypeName) throws ExtensionNotFoundException {
        return createUseCase(name, owner, resolveStereotype(moduleName, stereotypeName, this.metamodel.getMClass(UseCase.class)));
    }

    @objid ("45ead6b6-f8a8-42dc-8b81-bbae0b6d739b")
    @Override
    public UseCaseDependency createUseCaseDependency() {
        UseCaseDependency newElement = this.genericFactory.create(UseCaseDependency.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("b526505c-898f-4a72-8fb8-59c320dc5219")
    @Override
    public UseCaseDiagram createUseCaseDiagram() {
        UseCaseDiagram newElement = this.genericFactory.create(UseCaseDiagram.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("ff33ad43-6d8a-41aa-ac3b-89315956ceda")
    @Override
    public UseCaseDiagram createUseCaseDiagram(String name, ModelElement diagramContext) {
        UseCaseDiagram newElement = this.genericFactory.create(UseCaseDiagram.class, diagramContext);
        newElement.setName(name);
        newElement.setOrigin(diagramContext);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("b0341a12-f5e9-44d5-91ae-776eac715886")
    @Override
    public UseCaseDiagram createUseCaseDiagram(final String name, final ModelElement diagramContext, final Stereotype stereotype) {
        UseCaseDiagram newElement = this.genericFactory.create(UseCaseDiagram.class, diagramContext);
        newElement.setName(name);
        newElement.setOrigin(diagramContext);
        attachStereotype(newElement, stereotype);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("ed01b640-392c-42c0-a802-69fb2a9b5f3e")
    @Override
    public ValuePin createValuePin() {
        ValuePin newElement = this.genericFactory.create(ValuePin.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("ab64d3c5-50e5-4da7-a0c7-ca3a6d79e133")
    @Override
    public void setDefaultValue(String key, Object value) {
        super.setDefaultValue(key, value);
        this.elementInitializer.setDefaultValue(key, value);
        
    }

    @objid ("10aabf63-49bb-40e9-8eb6-d23791e02c5d")
    @Override
    public ExternElement createExternElement() {
        ExternElement newElement = this.genericFactory.create(ExternElement.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("1c9ec929-8a6a-4e8b-b0a2-b707f9f70fdb")
    @Override
    public MethodologicalLink createMethodologicalLink() {
        MethodologicalLink newElement = this.genericFactory.create(MethodologicalLink.class, this.scratchRepository);
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("9d3dba20-8de2-4233-a6bf-f22c56975b27")
    @Override
    public ExternElement createExternElement(String name, MethodologicalLink owner, Stereotype stereotype) {
        ExternElement newElement = this.genericFactory.create(ExternElement.class, owner);
        newElement.setName(name);
        newElement.setOwner(owner);
        if (stereotype != null) {
            newElement.getExtension().add(stereotype);
        }
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("abf5d489-ece1-4622-8fd4-a715fa171b67")
    @Override
    public ExternElement createExternElement(String name, MethodologicalLink owner, String moduleName, String stereotypeName) throws ExtensionNotFoundException {
        return createExternElement(name, owner, resolveStereotype(moduleName, stereotypeName, this.metamodel.getMClass(Class.class)));
    }

    @objid ("71109710-73f5-4fc9-a406-9ceb3b6501c4")
    @Override
    public MethodologicalLink createMethodologicalLink(ModelElement source, ModelElement destination, Stereotype stereotype) {
        MethodologicalLink newElement = this.genericFactory.create(MethodologicalLink.class, source);
        newElement.setImpacted(source);
        newElement.setDependsOn(destination);
        if (stereotype != null) {
            newElement.getExtension().add(stereotype);
        }
        this.elementInitializer.initialize(newElement);
        return newElement;
    }

    @objid ("6a7204dd-8b53-452d-8f9c-93e470bb7847")
    @Override
    public MethodologicalLink createMethodologicalLink(ModelElement source, ModelElement destination, String moduleName, String stereotypeName) throws ExtensionNotFoundException {
        return createMethodologicalLink(source, destination, resolveStereotype(moduleName, stereotypeName, this.metamodel.getMClass(MethodologicalLink.class)));
    }

}
