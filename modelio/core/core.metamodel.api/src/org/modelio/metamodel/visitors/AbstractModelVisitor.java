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
package org.modelio.metamodel.visitors;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.activities.BpmnActivity;
import org.modelio.metamodel.bpmn.activities.BpmnAdHocSubProcess;
import org.modelio.metamodel.bpmn.activities.BpmnBusinessRuleTask;
import org.modelio.metamodel.bpmn.activities.BpmnCallActivity;
import org.modelio.metamodel.bpmn.activities.BpmnComplexBehaviorDefinition;
import org.modelio.metamodel.bpmn.activities.BpmnLoopCharacteristics;
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
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnProcessCollaborationDiagram;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnProcessDesignDiagram;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnSubProcessDiagram;
import org.modelio.metamodel.bpmn.bpmnService.BpmnEndPoint;
import org.modelio.metamodel.bpmn.bpmnService.BpmnInterface;
import org.modelio.metamodel.bpmn.bpmnService.BpmnOperation;
import org.modelio.metamodel.bpmn.events.BpmnBoundaryEvent;
import org.modelio.metamodel.bpmn.events.BpmnCancelEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnCatchEvent;
import org.modelio.metamodel.bpmn.events.BpmnCompensateEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnConditionalEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnEndEvent;
import org.modelio.metamodel.bpmn.events.BpmnErrorEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnEscalationEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnEvent;
import org.modelio.metamodel.bpmn.events.BpmnEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnImplicitThrowEvent;
import org.modelio.metamodel.bpmn.events.BpmnIntermediateCatchEvent;
import org.modelio.metamodel.bpmn.events.BpmnIntermediateThrowEvent;
import org.modelio.metamodel.bpmn.events.BpmnLinkEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnMessageEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnSignalEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnStartEvent;
import org.modelio.metamodel.bpmn.events.BpmnTerminateEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnThrowEvent;
import org.modelio.metamodel.bpmn.events.BpmnTimerEventDefinition;
import org.modelio.metamodel.bpmn.flows.BpmnMessage;
import org.modelio.metamodel.bpmn.flows.BpmnMessageFlow;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.gateways.BpmnComplexGateway;
import org.modelio.metamodel.bpmn.gateways.BpmnEventBasedGateway;
import org.modelio.metamodel.bpmn.gateways.BpmnExclusiveGateway;
import org.modelio.metamodel.bpmn.gateways.BpmnGateway;
import org.modelio.metamodel.bpmn.gateways.BpmnInclusiveGateway;
import org.modelio.metamodel.bpmn.gateways.BpmnParallelGateway;
import org.modelio.metamodel.bpmn.objects.BpmnDataAssociation;
import org.modelio.metamodel.bpmn.objects.BpmnDataInput;
import org.modelio.metamodel.bpmn.objects.BpmnDataObject;
import org.modelio.metamodel.bpmn.objects.BpmnDataOutput;
import org.modelio.metamodel.bpmn.objects.BpmnDataState;
import org.modelio.metamodel.bpmn.objects.BpmnDataStore;
import org.modelio.metamodel.bpmn.objects.BpmnItemAwareElement;
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
import org.modelio.metamodel.bpmn.rootElements.BpmnArtifact;
import org.modelio.metamodel.bpmn.rootElements.BpmnAssociation;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowNode;
import org.modelio.metamodel.bpmn.rootElements.BpmnGroup;
import org.modelio.metamodel.bpmn.rootElements.BpmnSharedDefinitions;
import org.modelio.metamodel.bpmn.rootElements.BpmnSharedElement;
import org.modelio.metamodel.diagrams.ActivityDiagram;
import org.modelio.metamodel.diagrams.BehaviorDiagram;
import org.modelio.metamodel.diagrams.ClassDiagram;
import org.modelio.metamodel.diagrams.CommunicationDiagram;
import org.modelio.metamodel.diagrams.CompositeStructureDiagram;
import org.modelio.metamodel.diagrams.DeploymentDiagram;
import org.modelio.metamodel.diagrams.ObjectDiagram;
import org.modelio.metamodel.diagrams.SequenceDiagram;
import org.modelio.metamodel.diagrams.StateMachineDiagram;
import org.modelio.metamodel.diagrams.StaticDiagram;
import org.modelio.metamodel.diagrams.UseCaseDiagram;
import org.modelio.metamodel.mda.Project;
import org.modelio.metamodel.uml.behavior.activityModel.AcceptCallEventAction;
import org.modelio.metamodel.uml.behavior.activityModel.AcceptChangeEventAction;
import org.modelio.metamodel.uml.behavior.activityModel.AcceptSignalAction;
import org.modelio.metamodel.uml.behavior.activityModel.AcceptTimeEventAction;
import org.modelio.metamodel.uml.behavior.activityModel.Activity;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityAction;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityEdge;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityFinalNode;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityGroup;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityNode;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityParameterNode;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityPartition;
import org.modelio.metamodel.uml.behavior.activityModel.CallAction;
import org.modelio.metamodel.uml.behavior.activityModel.CallBehaviorAction;
import org.modelio.metamodel.uml.behavior.activityModel.CallOperationAction;
import org.modelio.metamodel.uml.behavior.activityModel.CentralBufferNode;
import org.modelio.metamodel.uml.behavior.activityModel.Clause;
import org.modelio.metamodel.uml.behavior.activityModel.ConditionalNode;
import org.modelio.metamodel.uml.behavior.activityModel.ControlFlow;
import org.modelio.metamodel.uml.behavior.activityModel.ControlNode;
import org.modelio.metamodel.uml.behavior.activityModel.DataStoreNode;
import org.modelio.metamodel.uml.behavior.activityModel.DecisionMergeNode;
import org.modelio.metamodel.uml.behavior.activityModel.ExceptionHandler;
import org.modelio.metamodel.uml.behavior.activityModel.ExpansionNode;
import org.modelio.metamodel.uml.behavior.activityModel.ExpansionRegion;
import org.modelio.metamodel.uml.behavior.activityModel.FinalNode;
import org.modelio.metamodel.uml.behavior.activityModel.FlowFinalNode;
import org.modelio.metamodel.uml.behavior.activityModel.ForkJoinNode;
import org.modelio.metamodel.uml.behavior.activityModel.InitialNode;
import org.modelio.metamodel.uml.behavior.activityModel.InputPin;
import org.modelio.metamodel.uml.behavior.activityModel.InstanceNode;
import org.modelio.metamodel.uml.behavior.activityModel.InterruptibleActivityRegion;
import org.modelio.metamodel.uml.behavior.activityModel.LoopNode;
import org.modelio.metamodel.uml.behavior.activityModel.MessageFlow;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectFlow;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectNode;
import org.modelio.metamodel.uml.behavior.activityModel.OpaqueAction;
import org.modelio.metamodel.uml.behavior.activityModel.OutputPin;
import org.modelio.metamodel.uml.behavior.activityModel.Pin;
import org.modelio.metamodel.uml.behavior.activityModel.SendSignalAction;
import org.modelio.metamodel.uml.behavior.activityModel.StructuredActivityNode;
import org.modelio.metamodel.uml.behavior.activityModel.ValuePin;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
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
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionFragment;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionOperand;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionUse;
import org.modelio.metamodel.uml.behavior.interactionModel.Lifeline;
import org.modelio.metamodel.uml.behavior.interactionModel.Message;
import org.modelio.metamodel.uml.behavior.interactionModel.MessageEnd;
import org.modelio.metamodel.uml.behavior.interactionModel.OccurrenceSpecification;
import org.modelio.metamodel.uml.behavior.interactionModel.PartDecomposition;
import org.modelio.metamodel.uml.behavior.interactionModel.StateInvariant;
import org.modelio.metamodel.uml.behavior.interactionModel.TerminateSpecification;
import org.modelio.metamodel.uml.behavior.stateMachineModel.AbstractPseudoState;
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
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateVertex;
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
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.infrastructure.Substitution;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.infrastructure.Usage;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.AttributeLink;
import org.modelio.metamodel.uml.statik.BehavioralFeature;
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
import org.modelio.metamodel.uml.statik.Feature;
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
import org.modelio.metamodel.uml.statik.StructuralFeature;
import org.modelio.metamodel.uml.statik.TemplateBinding;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.metamodel.uml.statik.TemplateParameterSubstitution;

/**
 * This class is an implementation of {@link IModelVisitor} whose visit() methods simply return <code>null</code>.
 */
@objid ("00106c06-4f2c-1032-829a-001ec947cd2a")
public class AbstractModelVisitor implements IModelVisitor {
    @objid ("ef0cfe8f-c7d8-4018-96b2-807ec06113c7")
    @Override
    public Object visitAbstractPseudoState(AbstractPseudoState obj) {
        	return null;
    }

    @objid ("d7200f6d-09c5-471e-bded-1fe96cd6f18a")
    @Override
    public Object visitAbstraction(Abstraction obj) {
        	return null;
    }

    @objid ("7717dd4e-0f09-4c21-9901-83fedb65322d")
    @Override
    public Object visitAcceptCallEventAction(AcceptCallEventAction obj) {
        	return null;
    }

    @objid ("bca276eb-d3f7-4685-b9e5-aea62d62bb94")
    @Override
    public Object visitAcceptChangeEventAction(AcceptChangeEventAction obj) {
        	return null;
    }

    @objid ("9c31e699-3f81-4110-a79a-51a663de65e3")
    @Override
    public Object visitAcceptSignalAction(AcceptSignalAction obj) {
        	return null;
    }

    @objid ("f12ced41-773f-4a78-8cc3-28461f347e1e")
    @Override
    public Object visitAcceptTimeEventAction(AcceptTimeEventAction obj) {
        	return null;
    }

    @objid ("78366810-90d2-42f9-ae66-011e0d23f088")
    @Override
    public Object visitActivity(Activity obj) {
        	return null;
    }

    @objid ("39ea9293-b5bd-4097-99c5-514e10a8851f")
    @Override
    public Object visitActivityAction(ActivityAction obj) {
        	return null;
    }

    @objid ("80651eb7-f380-4468-a6c9-ff47efbd9840")
    @Override
    public Object visitActivityDiagram(ActivityDiagram obj) {
        	return null;
    }

    @objid ("ba92f64d-c236-4cbb-9a22-25671e35bf22")
    @Override
    public Object visitActivityEdge(ActivityEdge obj) {
        	return null;
    }

    @objid ("b0a0cee7-1776-40ce-be52-ea950a8dadb4")
    @Override
    public Object visitActivityFinalNode(ActivityFinalNode obj) {
        	return null;
    }

    @objid ("05314fee-5c6b-4dfb-9eff-d4b2071c49e3")
    @Override
    public Object visitActivityGroup(ActivityGroup obj) {
        	return null;
    }

    @objid ("839d7126-5379-45b2-9d5e-d6e202be571a")
    @Override
    public Object visitActivityNode(ActivityNode obj) {
        	return null;
    }

    @objid ("12ce173c-f966-4d90-aa73-bc7416d2f945")
    @Override
    public Object visitActivityParameterNode(ActivityParameterNode obj) {
        	return null;
    }

    @objid ("9273e4e1-ae1f-4cb4-9eec-be62f1696954")
    @Override
    public Object visitActivityPartition(ActivityPartition obj) {
        	return null;
    }

    @objid ("6fba62df-1667-4bf6-8635-d412c1f31c4e")
    @Override
    public Object visitActor(Actor obj) {
        	return null;
    }

    @objid ("f3feb9a6-89ba-4709-b4b0-9d0932816c9e")
    @Override
    public Object visitArtifact(Artifact obj) {
        	return null;
    }

    @objid ("a94434e3-d208-4972-a940-2298f1e10ae6")
    @Override
    public Object visitAssociation(Association obj) {
        	return null;
    }

    @objid ("5e254993-f9e3-49c7-9389-83e4c0e218f2")
    @Override
    public Object visitAssociationEnd(AssociationEnd obj) {
        	return null;
    }

    @objid ("84b93338-ddd8-4168-956a-276437c2a8c2")
    @Override
    public Object visitAttribute(Attribute obj) {
        	return null;
    }

    @objid ("4ad8e666-07d6-4ba3-8cef-640bc1e38536")
    @Override
    public Object visitAttributeLink(AttributeLink obj) {
        	return null;
    }

    @objid ("03ca11fd-787e-4ae9-9ed2-e2abc4727a98")
    @Override
    public Object visitBehavior(Behavior obj) {
        	return null;
    }

    @objid ("8fc175e4-b7ee-4197-8bab-64e5dd9d6fc2")
    @Override
    public Object visitBehaviorDiagram(BehaviorDiagram obj) {
        	return null;
    }

    @objid ("5372c75f-d5b0-41ac-b26f-08ad73d3a0bf")
    @Override
    public Object visitBehaviorParameter(BehaviorParameter obj) {
        	return null;
    }

    @objid ("03ded753-cb91-4f2b-9d10-b42c88ec8de5")
    @Override
    public Object visitBehavioralFeature(BehavioralFeature obj) {
        	return null;
    }

    @objid ("40da173e-d5fb-4606-a087-66ac848e16f3")
    @Override
    public Object visitBindableInstance(BindableInstance obj) {
        	return null;
    }

    @objid ("a3dafafe-b79d-447f-aaa4-ce7b00f71f07")
    @Override
    public Object visitBinding(Binding obj) {
        	return null;
    }

    @objid ("de7763e6-317c-4da8-a466-e843b9dd3270")
    @Override
    public Object visitBpmnActivity(BpmnActivity obj) {
        	return null;
    }

    @objid ("f2ed3e08-b4d4-48ea-9eae-6e65e7102468")
    @Override
    public Object visitBpmnAdHocSubProcess(BpmnAdHocSubProcess obj) {
        	return null;
    }

    @objid ("0113865d-1de3-4f83-8500-5426ae9734b0")
    @Override
    public Object visitBpmnArtifact(BpmnArtifact obj) {
        	return null;
    }

    @objid ("021376aa-7983-48e0-9e7f-17249732f5c6")
    @Override
    public Object visitBpmnAssociation(BpmnAssociation obj) {
        	return null;
    }

    @objid ("d46dc346-0828-46f3-a375-99d2cb3a1f61")
    @Override
    public Object visitBpmnBaseElement(BpmnBaseElement obj) {
        	return null;
    }

    @objid ("4bbfd294-6c31-4bbe-b770-ef4eeaf57e3e")
    @Override
    public Object visitBpmnBoundaryEvent(BpmnBoundaryEvent obj) {
        	return null;
    }

    @objid ("4b2cbf0c-ee22-4cda-b85a-6d343585e268")
    @Override
    public Object visitBpmnBusinessRuleTask(BpmnBusinessRuleTask obj) {
        	return null;
    }

    @objid ("d8ad322a-55cf-4631-af1f-cc451e916f0e")
    @Override
    public Object visitBpmnCallActivity(BpmnCallActivity obj) {
        	return null;
    }

    @objid ("efffe839-d6e7-4533-a185-8c2d99d9259b")
    @Override
    public Object visitBpmnCancelEventDefinition(BpmnCancelEventDefinition obj) {
        	return null;
    }

    @objid ("60639735-78ee-407e-866a-9cb40efcf794")
    @Override
    public Object visitBpmnCatchEvent(BpmnCatchEvent obj) {
        	return null;
    }

    @objid ("c403a9a4-8044-46c3-8e9f-5eca7eaeb90a")
    @Override
    public Object visitBpmnCollaboration(BpmnCollaboration obj) {
        	return null;
    }

    @objid ("360edde9-8bd5-46ac-897c-e50d167b56a7")
    @Override
    public Object visitBpmnCollaborationDiagram(BpmnCollaborationDiagram obj) {
        	return null;
    }

    @objid ("74554a74-8904-4050-af8e-26957048020c")
    @Override
    public Object visitBpmnCompensateEventDefinition(BpmnCompensateEventDefinition obj) {
        	return null;
    }

    @objid ("693110c7-0904-4d27-bba3-4c4afc6cfe81")
    @Override
    public Object visitBpmnComplexBehaviorDefinition(BpmnComplexBehaviorDefinition obj) {
        	return null;
    }

    @objid ("e88168d7-a3a0-4446-96c5-f64fb41d8263")
    @Override
    public Object visitBpmnComplexGateway(BpmnComplexGateway obj) {
        	return null;
    }

    @objid ("90929c7f-74d4-434c-9d8a-0b1fbafa1eb0")
    @Override
    public Object visitBpmnConditionalEventDefinition(BpmnConditionalEventDefinition obj) {
        	return null;
    }

    @objid ("338e4c44-bfbb-440a-8951-e427c6b09d1d")
    @Override
    public Object visitBpmnDataAssociation(BpmnDataAssociation obj) {
        	return null;
    }

    @objid ("d1ccbb64-9d4b-466f-a6e4-189d80ac65a4")
    @Override
    public Object visitBpmnDataInput(BpmnDataInput obj) {
        	return null;
    }

    @objid ("fab789ea-6622-4b9b-8157-ed68e4dbe550")
    @Override
    public Object visitBpmnDataObject(BpmnDataObject obj) {
        	return null;
    }

    @objid ("85ba8c9c-f290-41c6-8887-67f95006dd3a")
    @Override
    public Object visitBpmnDataOutput(BpmnDataOutput obj) {
        	return null;
    }

    @objid ("76f69506-f4f0-4cf3-b62d-1acdefdbb00c")
    @Override
    public Object visitBpmnDataState(BpmnDataState obj) {
        	return null;
    }

    @objid ("c7104a32-557a-48a5-b21a-5937b6925cfe")
    @Override
    public Object visitBpmnDataStore(BpmnDataStore obj) {
        	return null;
    }

    @objid ("fe7aa374-bcb9-4954-b2dd-b428c61d9c1e")
    @Override
    public Object visitBpmnEndEvent(BpmnEndEvent obj) {
        	return null;
    }

    @objid ("fb1e2f69-8187-432b-95a5-111cafc7dcdf")
    @Override
    public Object visitBpmnEndPoint(BpmnEndPoint obj) {
        	return null;
    }

    @objid ("4e99347f-cf67-4919-a1ba-5e0f17513b23")
    @Override
    public Object visitBpmnErrorEventDefinition(BpmnErrorEventDefinition obj) {
        	return null;
    }

    @objid ("e5a1065a-2afa-4cba-824e-1f1cf73e12cb")
    @Override
    public Object visitBpmnEscalationEventDefinition(BpmnEscalationEventDefinition obj) {
        	return null;
    }

    @objid ("c95beea1-3c27-4c3b-bddf-b939c5665306")
    @Override
    public Object visitBpmnEvent(BpmnEvent obj) {
        	return null;
    }

    @objid ("650ca659-595c-4385-aaad-5ad9be072831")
    @Override
    public Object visitBpmnEventBasedGateway(BpmnEventBasedGateway obj) {
        	return null;
    }

    @objid ("8682539f-d242-4053-ba97-c5d0d2274040")
    @Override
    public Object visitBpmnEventDefinition(BpmnEventDefinition obj) {
        	return null;
    }

    @objid ("6f20e278-44e5-4233-bc21-c2fbe7fdced7")
    @Override
    public Object visitBpmnExclusiveGateway(BpmnExclusiveGateway obj) {
        	return null;
    }

    @objid ("ee91c27f-0a7c-4a44-95a6-b5808fb2f8aa")
    @Override
    public Object visitBpmnFlowElement(BpmnFlowElement obj) {
        	return null;
    }

    @objid ("7525326b-5f04-4a7b-b08b-f65fa5c1f2d5")
    @Override
    public Object visitBpmnFlowNode(BpmnFlowNode obj) {
        	return null;
    }

    @objid ("673b0815-0c48-4b52-a93e-1ccc52ed1a96")
    @Override
    public Object visitBpmnGateway(BpmnGateway obj) {
        	return null;
    }

    @objid ("7965b57c-e726-49bf-ae25-7f253257bdb4")
    @Override
    public Object visitBpmnGroup(BpmnGroup obj) {
        	return null;
    }

    @objid ("a8872363-9ffc-4e51-9974-366e2961af84")
    @Override
    public Object visitBpmnImplicitThrowEvent(BpmnImplicitThrowEvent obj) {
        	return null;
    }

    @objid ("e7172107-de3d-4ae9-a370-62e3b83f4632")
    @Override
    public Object visitBpmnInclusiveGateway(BpmnInclusiveGateway obj) {
        	return null;
    }

    @objid ("ba2fa6e3-36f8-4cbc-af86-f11f151ae367")
    @Override
    public Object visitBpmnInterface(BpmnInterface obj) {
        	return null;
    }

    @objid ("7e4d2736-c333-4318-8180-aa73862e0881")
    @Override
    public Object visitBpmnIntermediateCatchEvent(BpmnIntermediateCatchEvent obj) {
        	return null;
    }

    @objid ("f12daca3-5234-4871-ac30-bf75fcdce762")
    @Override
    public Object visitBpmnIntermediateThrowEvent(BpmnIntermediateThrowEvent obj) {
        	return null;
    }

    @objid ("046c405a-ed1b-4677-ad47-70aa7b40b858")
    @Override
    public Object visitBpmnItemAwareElement(BpmnItemAwareElement obj) {
        	return null;
    }

    @objid ("b8bb451c-92d1-46f5-b77f-0a57063a1296")
    @Override
    public Object visitBpmnItemDefinition(BpmnItemDefinition obj) {
        	return null;
    }

    @objid ("307e4d99-839d-4ed9-a9f6-c14356090b42")
    @Override
    public Object visitBpmnLane(BpmnLane obj) {
        	return null;
    }

    @objid ("fd2c6e73-fe73-4706-a176-548fa713898a")
    @Override
    public Object visitBpmnLaneSet(BpmnLaneSet obj) {
        	return null;
    }

    @objid ("a91c9453-ef05-4c91-b1ce-1582e32bd68a")
    @Override
    public Object visitBpmnLinkEventDefinition(BpmnLinkEventDefinition obj) {
        	return null;
    }

    @objid ("bb64b266-2bec-45e0-a32d-0649e4eb35d3")
    @Override
    public Object visitBpmnLoopCharacteristics(BpmnLoopCharacteristics obj) {
        	return null;
    }

    @objid ("25c63580-e506-49e2-b45a-f6a1768f9cf3")
    @Override
    public Object visitBpmnManualTask(BpmnManualTask obj) {
        	return null;
    }

    @objid ("7fb7a06f-a0cf-4c87-860c-74c20a4738a2")
    @Override
    public Object visitBpmnMessage(BpmnMessage obj) {
        	return null;
    }

    @objid ("75a88b24-e103-4bfc-9ee9-ec77f99e320d")
    @Override
    public Object visitBpmnMessageEventDefinition(BpmnMessageEventDefinition obj) {
        	return null;
    }

    @objid ("b7e698a0-c67b-4b06-98ad-689837c68d3f")
    @Override
    public Object visitBpmnMessageFlow(BpmnMessageFlow obj) {
        	return null;
    }

    @objid ("e9a527c7-6018-4368-bb46-7a5356e3ac20")
    @Override
    public Object visitBpmnMultiInstanceLoopCharacteristics(BpmnMultiInstanceLoopCharacteristics obj) {
        	return null;
    }

    @objid ("0939cca0-9575-447f-8979-71b504ba67ff")
    @Override
    public Object visitBpmnOperation(BpmnOperation obj) {
        	return null;
    }

    @objid ("f8793429-1cf2-43f2-ad0d-6dff5c1bf778")
    @Override
    public Object visitBpmnParallelGateway(BpmnParallelGateway obj) {
        	return null;
    }

    @objid ("29e43e96-b3f4-4e97-a2bb-bd74f40105cb")
    @Override
    public Object visitBpmnParticipant(BpmnParticipant obj) {
        	return null;
    }

    @objid ("8a9dc5d3-b487-4fe4-8c99-f21470545064")
    @Override
    public Object visitBpmnProcess(BpmnProcess obj) {
        	return null;
    }

    @objid ("9c805991-af32-4b3a-9a4b-bfaa052bf8de")
    @Override
    public Object visitBpmnProcessCollaborationDiagram(BpmnProcessCollaborationDiagram obj) {
        	return null;
    }

    @objid ("cdb9de5d-e9a1-4e8d-972b-b3d6b79a5f22")
    @Override
    public Object visitBpmnProcessDesignDiagram(BpmnProcessDesignDiagram obj) {
        	return null;
    }

    @objid ("82100224-ed05-4a1d-9491-2ef0a8337fa3")
    @Override
    public Object visitBpmnReceiveTask(BpmnReceiveTask obj) {
        	return null;
    }

    @objid ("f1bbb7d7-3504-4b60-9eb1-9e7e5881baa2")
    @Override
    public Object visitBpmnResource(BpmnResource obj) {
        	return null;
    }

    @objid ("3c8d8ec5-8b91-4d53-875c-1a3f2f3e0cbc")
    @Override
    public Object visitBpmnResourceParameter(BpmnResourceParameter obj) {
        	return null;
    }

    @objid ("8009e1be-dab4-474e-b237-2c83c30944b1")
    @Override
    public Object visitBpmnResourceParameterBinding(BpmnResourceParameterBinding obj) {
        	return null;
    }

    @objid ("6269dff0-7ae4-4fdb-aeda-1823d6e620f3")
    @Override
    public Object visitBpmnResourceRole(BpmnResourceRole obj) {
        	return null;
    }

    @objid ("91f5781e-ca5a-40b2-b1c5-a166b4e5339d")
    @Override
    public Object visitBpmnScriptTask(BpmnScriptTask obj) {
        	return null;
    }

    @objid ("1c98c3b3-fcc7-4dc2-ac7e-ed451b3a0ceb")
    @Override
    public Object visitBpmnSendTask(BpmnSendTask obj) {
        	return null;
    }

    @objid ("4b7015d5-25fa-48e2-8482-1bb9448a5ad4")
    @Override
    public Object visitBpmnSequenceFlow(BpmnSequenceFlow obj) {
        	return null;
    }

    @objid ("9584cb11-0889-43c5-8441-fa011642a5c5")
    @Override
    public Object visitBpmnSequenceFlowDataAssociation(BpmnSequenceFlowDataAssociation obj) {
        	return null;
    }

    @objid ("54d13e97-ea1d-4fd9-9c6b-53b3b459f9db")
    @Override
    public Object visitBpmnServiceTask(BpmnServiceTask obj) {
        	return null;
    }

    @objid ("7f2b6fc2-62e5-4fda-bbc5-9360e2cb3afd")
    @Override
    public Object visitBpmnSharedDefinitions(BpmnSharedDefinitions obj) {
        	return null;
    }

    @objid ("c9fd3964-4b77-4ee6-a236-f38df8eb6bd1")
    @Override
    public Object visitBpmnSharedElement(BpmnSharedElement obj) {
        	return null;
    }

    @objid ("822f4672-e070-4725-a18b-ab31e8b80a3d")
    @Override
    public Object visitBpmnSignalEventDefinition(BpmnSignalEventDefinition obj) {
        	return null;
    }

    @objid ("86549c05-d977-4801-832c-905b958ec2be")
    @Override
    public Object visitBpmnStandardLoopCharacteristics(BpmnStandardLoopCharacteristics obj) {
        	return null;
    }

    @objid ("a124051d-fff8-409f-b08e-391cd3652057")
    @Override
    public Object visitBpmnStartEvent(BpmnStartEvent obj) {
        	return null;
    }

    @objid ("c34ffa0b-6582-4974-acbb-a75168321232")
    @Override
    public Object visitBpmnSubProcess(BpmnSubProcess obj) {
        	return null;
    }

    @objid ("e7331794-c0ff-4d26-b868-31a714788093")
    @Override
    public Object visitBpmnSubProcessDiagram(BpmnSubProcessDiagram obj) {
        	return null;
    }

    @objid ("14ca2cbe-7918-4718-8249-1e0bf5933e5b")
    @Override
    public Object visitBpmnTask(BpmnTask obj) {
        	return null;
    }

    @objid ("1536d79f-0d59-45c9-ab41-ca2d61ba57ee")
    @Override
    public Object visitBpmnTerminateEventDefinition(BpmnTerminateEventDefinition obj) {
        	return null;
    }

    @objid ("9e3072be-5008-4aaa-b459-473582797bc5")
    @Override
    public Object visitBpmnThrowEvent(BpmnThrowEvent obj) {
        	return null;
    }

    @objid ("67da0d9d-35bf-4c46-aa3c-de15e772694b")
    @Override
    public Object visitBpmnTimerEventDefinition(BpmnTimerEventDefinition obj) {
        	return null;
    }

    @objid ("1b27fd0c-0ff7-4077-9c67-dce7ac68f1ee")
    @Override
    public Object visitBpmnTransaction(BpmnTransaction obj) {
        	return null;
    }

    @objid ("1b73b467-29cb-452f-b8ec-7bcbdbfd3b92")
    @Override
    public Object visitBpmnUserTask(BpmnUserTask obj) {
        	return null;
    }

    @objid ("4b8c0f91-c552-45ec-aa52-3b163f70e4d6")
    @Override
    public Object visitCallAction(CallAction obj) {
        	return null;
    }

    @objid ("19a56449-3d6a-4fcb-81da-ac83bf070636")
    @Override
    public Object visitCallBehaviorAction(CallBehaviorAction obj) {
        	return null;
    }

    @objid ("f852f7d5-e66d-41e2-80c8-c67e31b1a98f")
    @Override
    public Object visitCallOperationAction(CallOperationAction obj) {
        	return null;
    }

    @objid ("b566b670-a635-4d61-a157-33f5de914e09")
    @Override
    public Object visitCentralBufferNode(CentralBufferNode obj) {
        	return null;
    }

    @objid ("dd2311af-a7b3-41f0-ac5f-ee8d557caebf")
    @Override
    public Object visitChoicePseudoState(ChoicePseudoState obj) {
        	return null;
    }

    @objid ("149e8974-ac11-46d5-8f34-f8a839f45e05")
    @Override
    public Object visitClass(Class obj) {
        	return null;
    }

    @objid ("f349d2e6-7f49-4d3a-833e-48099be73392")
    @Override
    public Object visitClassAssociation(ClassAssociation obj) {
        	return null;
    }

    @objid ("01d9f74f-2237-47d5-bb0c-aa8b66ce9eca")
    @Override
    public Object visitClassDiagram(ClassDiagram obj) {
        	return null;
    }

    @objid ("3ef3aed7-0c75-4038-b282-69212c956c89")
    @Override
    public Object visitClassifier(Classifier obj) {
        	return null;
    }

    @objid ("9a56a69f-3da4-470a-95a7-e0953573c331")
    @Override
    public Object visitClause(Clause obj) {
        	return null;
    }

    @objid ("95fbb8ed-9a80-4e1b-b127-b7d2ea2a56b4")
    @Override
    public Object visitCollaboration(Collaboration obj) {
        	return null;
    }

    @objid ("b6eec3d2-77eb-4073-8256-fd78c9a519bb")
    @Override
    public Object visitCollaborationUse(CollaborationUse obj) {
        	return null;
    }

    @objid ("009c499b-0897-446a-ae81-776de6c94a29")
    @Override
    public Object visitCombinedFragment(CombinedFragment obj) {
        	return null;
    }

    @objid ("3c5708b5-41f8-455c-be0a-946d5ff107a8")
    @Override
    public Object visitCommunicationChannel(CommunicationChannel obj) {
        	return null;
    }

    @objid ("3c3ebc4c-ccdb-4b27-b78e-64a1a06f5f0c")
    @Override
    public Object visitCommunicationDiagram(CommunicationDiagram obj) {
        	return null;
    }

    @objid ("d6210f19-8a70-4400-931a-4271d76a2010")
    @Override
    public Object visitCommunicationInteraction(CommunicationInteraction obj) {
        	return null;
    }

    @objid ("21c65074-d68d-43fd-970d-3e82ae8da6cd")
    @Override
    public Object visitCommunicationMessage(CommunicationMessage obj) {
        	return null;
    }

    @objid ("9bce58a2-4674-4b16-bc96-4ae87ecd60a9")
    @Override
    public Object visitCommunicationNode(CommunicationNode obj) {
        	return null;
    }

    @objid ("fe85037f-791c-4183-bd0b-802d7e3dc64d")
    @Override
    public Object visitComponent(Component obj) {
        	return null;
    }

    @objid ("e9ea3f8c-3202-4f60-9cb5-8705fb6d33c3")
    @Override
    public Object visitComponentRealization(ComponentRealization obj) {
        	return null;
    }

    @objid ("dec2a0ab-fec7-4aeb-bb60-ec2a50de2054")
    @Override
    public Object visitCompositeStructureDiagram(CompositeStructureDiagram obj) {
        	return null;
    }

    @objid ("f9247c90-7746-4360-a9b1-4da95bce806d")
    @Override
    public Object visitConditionalNode(ConditionalNode obj) {
        	return null;
    }

    @objid ("9cbf5e7d-c198-46db-9778-49856960631d")
    @Override
    public Object visitConnectionPointReference(ConnectionPointReference obj) {
        	return null;
    }

    @objid ("72e1b256-a7db-43aa-92c8-5d3e6047901b")
    @Override
    public Object visitConnector(Connector obj) {
        	return null;
    }

    @objid ("f8368519-c913-4088-9605-e0fd0698762a")
    @Override
    public Object visitConnectorEnd(ConnectorEnd obj) {
        	return null;
    }

    @objid ("a289611a-cd60-46bb-877d-fb5b02bb3ec4")
    @Override
    public Object visitConstraint(Constraint obj) {
        	return null;
    }

    @objid ("9fd8b82c-6f7d-4290-825f-fb68896b024c")
    @Override
    public Object visitControlFlow(ControlFlow obj) {
        	return null;
    }

    @objid ("7fd72628-2a8a-44e8-a8db-69ffc0016710")
    @Override
    public Object visitControlNode(ControlNode obj) {
        	return null;
    }

    @objid ("80ec5fed-bade-43ee-be8c-311fe30075e6")
    @Override
    public Object visitDataFlow(DataFlow obj) {
        	return null;
    }

    @objid ("b146af5c-fbb6-4fed-b519-2bcb356345c1")
    @Override
    public Object visitDataStoreNode(DataStoreNode obj) {
        	return null;
    }

    @objid ("c43bd6be-b035-4a35-b440-70cd618b51f5")
    @Override
    public Object visitDataType(DataType obj) {
        	return null;
    }

    @objid ("69964060-6a9a-4897-8edc-a876a399ea99")
    @Override
    public Object visitDecisionMergeNode(DecisionMergeNode obj) {
        	return null;
    }

    @objid ("d82b1b19-6fe0-423a-bedc-245cb0a2fd74")
    @Override
    public Object visitDeepHistoryPseudoState(DeepHistoryPseudoState obj) {
        	return null;
    }

    @objid ("89a00b13-dd49-45eb-acb5-975b7571fd23")
    @Override
    public Object visitDeploymentDiagram(DeploymentDiagram obj) {
        	return null;
    }

    @objid ("70496f4b-f014-45e0-ac5b-f8f8f265a686")
    @Override
    public Object visitDurationConstraint(DurationConstraint obj) {
        	return null;
    }

    @objid ("72f10d6f-a955-4550-bb50-b91913f952e8")
    @Override
    public Object visitElementImport(ElementImport obj) {
        	return null;
    }

    @objid ("2618cc56-4e97-4a0f-a967-59754a1cc7aa")
    @Override
    public Object visitElementRealization(ElementRealization obj) {
        	return null;
    }

    @objid ("c313167c-425e-45bd-8d5e-6a667f3508a6")
    @Override
    public Object visitEntryPointPseudoState(EntryPointPseudoState obj) {
        	return null;
    }

    @objid ("8a890558-4b98-4a65-b2e0-832fd232e224")
    @Override
    public Object visitEnumeration(Enumeration obj) {
        	return null;
    }

    @objid ("e6b92d29-9742-4f8d-b4ea-cf17d9505b52")
    @Override
    public Object visitEnumerationLiteral(EnumerationLiteral obj) {
        	return null;
    }

    @objid ("a79cb522-7701-45b0-b3af-f3d84d9726ff")
    @Override
    public Object visitEvent(Event obj) {
        	return null;
    }

    @objid ("7ebb3107-5763-496b-9d01-456df6473cd0")
    @Override
    public Object visitExceptionHandler(ExceptionHandler obj) {
        	return null;
    }

    @objid ("b466b78e-63d0-4e67-aea9-a2ea6ffd4cb7")
    @Override
    public Object visitExecutionOccurenceSpecification(ExecutionOccurenceSpecification obj) {
        	return null;
    }

    @objid ("521e9450-cd8b-4049-b567-f130646dda95")
    @Override
    public Object visitExecutionSpecification(ExecutionSpecification obj) {
        	return null;
    }

    @objid ("2023ba9a-bec9-42ac-8e6e-780cda6c9540")
    @Override
    public Object visitExitPointPseudoState(ExitPointPseudoState obj) {
        	return null;
    }

    @objid ("4ffbc6cf-4f6a-43c7-901e-458f7714bec1")
    @Override
    public Object visitExpansionNode(ExpansionNode obj) {
        	return null;
    }

    @objid ("8568f866-f84a-4bce-87f3-82d9ff7adbc5")
    @Override
    public Object visitExpansionRegion(ExpansionRegion obj) {
        	return null;
    }

    @objid ("fa118155-0108-4d3c-8d8d-67cd28008ae8")
    @Override
    public Object visitExtensionPoint(ExtensionPoint obj) {
        	return null;
    }

    @objid ("f75daa85-d51e-40ca-bbd1-2746d669cdef")
    @Override
    public Object visitFeature(Feature obj) {
        	return null;
    }

    @objid ("4d584715-0038-4e7d-913d-fcfaf6601e01")
    @Override
    public Object visitFinalNode(FinalNode obj) {
        	return null;
    }

    @objid ("16b2b585-1f0c-43cd-9114-d5b16c2cdff7")
    @Override
    public Object visitFinalState(FinalState obj) {
        	return null;
    }

    @objid ("1ebdb635-d417-40af-8cdd-b89a485fdc41")
    @Override
    public Object visitFlowFinalNode(FlowFinalNode obj) {
        	return null;
    }

    @objid ("f63efd38-1d4f-4aef-9584-2b2479fcdd3b")
    @Override
    public Object visitForkJoinNode(ForkJoinNode obj) {
        	return null;
    }

    @objid ("2de8f48c-8f93-4dff-9b86-c0a8f0c272c2")
    @Override
    public Object visitForkPseudoState(ForkPseudoState obj) {
        	return null;
    }

    @objid ("357fa3b4-f299-4fa2-9d27-51b052d86dde")
    @Override
    public Object visitGate(Gate obj) {
        	return null;
    }

    @objid ("99cf8351-9958-4fe6-8d71-d35abefcfaed")
    @Override
    public Object visitGeneralClass(GeneralClass obj) {
        	return null;
    }

    @objid ("80e32e76-c4e2-4d94-9460-635872e07a7e")
    @Override
    public Object visitGeneralOrdering(GeneralOrdering obj) {
        	return null;
    }

    @objid ("0c841e27-813c-43b0-8914-178fae64c10f")
    @Override
    public Object visitGeneralization(Generalization obj) {
        	return null;
    }

    @objid ("6782947c-9b75-4519-8fea-8e66f4479023")
    @Override
    public Object visitInformationFlow(InformationFlow obj) {
        	return null;
    }

    @objid ("da1b2cc8-4993-4dea-b0a8-7ee986c9e439")
    @Override
    public Object visitInformationItem(InformationItem obj) {
        	return null;
    }

    @objid ("190d0673-d107-43cf-beaa-6288fcf62d81")
    @Override
    public Object visitInitialNode(InitialNode obj) {
        	return null;
    }

    @objid ("4400598f-897a-46ee-81dc-090848003053")
    @Override
    public Object visitInitialPseudoState(InitialPseudoState obj) {
        	return null;
    }

    @objid ("e699bfc7-3192-4594-b56d-8bcffaf7d22e")
    @Override
    public Object visitInputPin(InputPin obj) {
        	return null;
    }

    @objid ("a578c540-0eb9-4723-bf5c-cca565bf67fd")
    @Override
    public Object visitInstance(Instance obj) {
        	return null;
    }

    @objid ("115a69ce-0086-4769-9662-e2ebff2dccc9")
    @Override
    public Object visitInstanceNode(InstanceNode obj) {
        	return null;
    }

    @objid ("7406a92f-d55b-4f15-add6-f615751a1df5")
    @Override
    public Object visitInteraction(Interaction obj) {
        	return null;
    }

    @objid ("537f3951-f822-4031-be15-313882094784")
    @Override
    public Object visitInteractionFragment(InteractionFragment obj) {
        	return null;
    }

    @objid ("e8b09ce6-539f-43bd-bf77-b0ac2a8eae50")
    @Override
    public Object visitInteractionOperand(InteractionOperand obj) {
        	return null;
    }

    @objid ("c1a052c2-5d3b-4e15-99aa-adbc26dd36b5")
    @Override
    public Object visitInteractionUse(InteractionUse obj) {
        	return null;
    }

    @objid ("1546c54e-e17e-4581-afb1-99630b3ac078")
    @Override
    public Object visitInterface(Interface obj) {
        	return null;
    }

    @objid ("72c372eb-c031-490e-a1b2-3fb73de65914")
    @Override
    public Object visitInterfaceRealization(InterfaceRealization obj) {
        	return null;
    }

    @objid ("92243faf-f279-4618-a14a-5aaa7a69f694")
    @Override
    public Object visitInternalTransition(InternalTransition obj) {
        	return null;
    }

    @objid ("d2e204b0-fc23-4cd8-bc3e-f68a91dd60da")
    @Override
    public Object visitInterruptibleActivityRegion(InterruptibleActivityRegion obj) {
        	return null;
    }

    @objid ("d20e55c2-b4eb-40d4-85c2-06f3176b6ae2")
    @Override
    public Object visitJoinPseudoState(JoinPseudoState obj) {
        	return null;
    }

    @objid ("fd3afd15-f525-4222-9e74-13e320d666e6")
    @Override
    public Object visitJunctionPseudoState(JunctionPseudoState obj) {
        	return null;
    }

    @objid ("eec61d12-5fe7-4b81-b529-19437a0a7d4b")
    @Override
    public Object visitLifeline(Lifeline obj) {
        	return null;
    }

    @objid ("e56b862d-8ce3-41e2-b9d4-76a0112d990f")
    @Override
    public Object visitLink(Link obj) {
        	return null;
    }

    @objid ("3e98c2d4-6aa4-444a-8627-7de9388ee6aa")
    @Override
    public Object visitLinkEnd(LinkEnd obj) {
        	return null;
    }

    @objid ("a9c8e4b9-48f5-4e80-a324-9171a1e7eb57")
    @Override
    public Object visitLoopNode(LoopNode obj) {
        	return null;
    }

    @objid ("63950b53-18a3-436c-b0c2-d39a3f7f99e9")
    @Override
    public Object visitManifestation(Manifestation obj) {
        	return null;
    }

    @objid ("e99abea5-9baa-4d1e-b24f-f54f57bee7b4")
    @Override
    public Object visitMessage(Message obj) {
        	return null;
    }

    @objid ("7a0e56b1-8c8a-4004-992f-5b118fb8f5f2")
    @Override
    public Object visitMessageEnd(MessageEnd obj) {
        	return null;
    }

    @objid ("86237a10-df67-41dc-89a6-5147a7598726")
    @Override
    public Object visitMessageFlow(MessageFlow obj) {
        	return null;
    }

    @objid ("7b65849b-dac2-47a7-9fcc-87d9cb67aff2")
    @Override
    public Object visitModelTree(ModelTree obj) {
        	return null;
    }

    @objid ("3c03c6d9-de30-45be-b25c-70cece5b1bb4")
    @Override
    public Object visitNameSpace(NameSpace obj) {
        	return null;
    }

    @objid ("0aa2a7d9-c7b1-4393-a8c7-cabbf788b46c")
    @Override
    public Object visitNaryAssociation(NaryAssociation obj) {
        	return null;
    }

    @objid ("643ae6c8-981a-44af-ac21-f5178c026d31")
    @Override
    public Object visitNaryAssociationEnd(NaryAssociationEnd obj) {
        	return null;
    }

    @objid ("ef9f46e2-c30b-400d-bf1c-0b7009b85932")
    @Override
    public Object visitNaryConnector(NaryConnector obj) {
        	return null;
    }

    @objid ("99d69d37-61b2-4716-9562-d506b7137582")
    @Override
    public Object visitNaryConnectorEnd(NaryConnectorEnd obj) {
        	return null;
    }

    @objid ("7f57952e-c7c2-4c48-94a0-b73238517d85")
    @Override
    public Object visitNaryLink(NaryLink obj) {
        	return null;
    }

    @objid ("975481c5-18c2-482e-8b34-5094a4c1ae51")
    @Override
    public Object visitNaryLinkEnd(NaryLinkEnd obj) {
        	return null;
    }

    @objid ("4e559d8b-a754-4cc1-b929-1bb69c4bbff8")
    @Override
    public Object visitNode(Node obj) {
        	return null;
    }

    @objid ("28560b13-f4eb-4eaa-8741-17da5ac98935")
    @Override
    public Object visitObjectDiagram(ObjectDiagram obj) {
        	return null;
    }

    @objid ("57af6c14-5ae8-4ff8-9048-532ad0d926ae")
    @Override
    public Object visitObjectFlow(ObjectFlow obj) {
        	return null;
    }

    @objid ("eccc382e-7410-4125-a4ba-11cf84b9dea0")
    @Override
    public Object visitObjectNode(ObjectNode obj) {
        	return null;
    }

    @objid ("8c31d021-e96b-40b7-a009-7fcbfdb0d825")
    @Override
    public Object visitOccurrenceSpecification(OccurrenceSpecification obj) {
        	return null;
    }

    @objid ("24a7e2ab-aff4-4fff-be0f-d2091554be48")
    @Override
    public Object visitOpaqueAction(OpaqueAction obj) {
        	return null;
    }

    @objid ("f5ad96d1-15d6-4fee-b2f1-0cb228d10981")
    @Override
    public Object visitOpaqueBehavior(OpaqueBehavior obj) {
        	return null;
    }

    @objid ("2f4e2d44-f36e-494d-aed4-1dea9cd630a5")
    @Override
    public Object visitOperation(Operation obj) {
        	return null;
    }

    @objid ("d8fe3ebc-8b46-4d41-9390-95a5046c8dc7")
    @Override
    public Object visitOutputPin(OutputPin obj) {
        	return null;
    }

    @objid ("309042cc-0422-4a16-9386-2f95dfc3b2c0")
    @Override
    public Object visitPackage(Package obj) {
        	return null;
    }

    @objid ("04195168-ab8e-4a74-a216-58d8530c46fe")
    @Override
    public Object visitPackageImport(PackageImport obj) {
        	return null;
    }

    @objid ("9d18aa74-1e65-4319-8f19-dacc58d4ac11")
    @Override
    public Object visitPackageMerge(PackageMerge obj) {
        	return null;
    }

    @objid ("def35080-9e30-4132-8f82-01a2df6364a4")
    @Override
    public Object visitParameter(Parameter obj) {
        	return null;
    }

    @objid ("ff27a46e-b648-4e06-91cb-eb585504534b")
    @Override
    public Object visitPartDecomposition(PartDecomposition obj) {
        	return null;
    }

    @objid ("7868c2c8-43ac-4c4d-b8e9-52e809e36e02")
    @Override
    public Object visitPin(Pin obj) {
        	return null;
    }

    @objid ("c24920e1-1dfe-46d0-922b-71104bc2328f")
    @Override
    public Object visitPort(Port obj) {
        	return null;
    }

    @objid ("45cf4650-56c0-49d2-8e6a-c3632a1c4f5e")
    @Override
    public Object visitProject(Project obj) {
        	return null;
    }

    @objid ("8dfc0908-49eb-448f-b136-df0460875c49")
    @Override
    public Object visitProvidedInterface(ProvidedInterface obj) {
        	return null;
    }

    @objid ("4a2ffd99-d134-4a88-bded-a79d6e1b03d6")
    @Override
    public Object visitRaisedException(RaisedException obj) {
        	return null;
    }

    @objid ("50a1ab1f-bfed-4b56-983f-42532e40b648")
    @Override
    public Object visitRegion(Region obj) {
        	return null;
    }

    @objid ("f1946e89-d82a-4ee6-a6d7-506cb5d178a3")
    @Override
    public Object visitRequiredInterface(RequiredInterface obj) {
        	return null;
    }

    @objid ("b800b6bb-54ac-4391-949c-09264640a78a")
    @Override
    public Object visitSendSignalAction(SendSignalAction obj) {
        	return null;
    }

    @objid ("bc6e2cc8-a2c8-4289-8023-e2259a948c27")
    @Override
    public Object visitSequenceDiagram(SequenceDiagram obj) {
        	return null;
    }

    @objid ("d133b6d2-549e-499d-bd2d-e718325a125e")
    @Override
    public Object visitShallowHistoryPseudoState(ShallowHistoryPseudoState obj) {
        	return null;
    }

    @objid ("80674512-21fb-4789-a16f-364bb7572334")
    @Override
    public Object visitSignal(Signal obj) {
        	return null;
    }

    @objid ("5103e2ef-96c5-445d-96ed-697beef27578")
    @Override
    public Object visitState(State obj) {
        	return null;
    }

    @objid ("81c29dca-8c9a-4fd8-b998-98ae24b57eae")
    @Override
    public Object visitStateInvariant(StateInvariant obj) {
        	return null;
    }

    @objid ("a25e0dbe-f64d-4e3b-ba9d-5b8aa8dbe631")
    @Override
    public Object visitStateMachine(StateMachine obj) {
        	return null;
    }

    @objid ("7c2ffe99-eb16-458c-8d2a-4cb51229bba1")
    @Override
    public Object visitStateMachineDiagram(StateMachineDiagram obj) {
        	return null;
    }

    @objid ("97042e0f-f471-4d71-8efc-9175b74b0b1c")
    @Override
    public Object visitStateVertex(StateVertex obj) {
        	return null;
    }

    @objid ("132faa1e-004a-4c4c-bbce-20086aed6a61")
    @Override
    public Object visitStaticDiagram(StaticDiagram obj) {
        	return null;
    }

    @objid ("ef6453ce-3b9b-4e1f-b3ed-54ba22b866f0")
    @Override
    public Object visitStructuralFeature(StructuralFeature obj) {
        	return null;
    }

    @objid ("ebbad80e-3768-49f2-8e05-2fa46206e7ad")
    @Override
    public Object visitStructuredActivityNode(StructuredActivityNode obj) {
        	return null;
    }

    @objid ("ccedc931-fc42-4a9e-9620-059f85b737c4")
    @Override
    public Object visitSubstitution(Substitution obj) {
        	return null;
    }

    @objid ("b510e2ab-b988-477d-a9b4-f8876820dc39")
    @Override
    public Object visitTemplateBinding(TemplateBinding obj) {
        	return null;
    }

    @objid ("4ee2aa15-2d48-4b76-812a-d14b025d3295")
    @Override
    public Object visitTemplateParameter(TemplateParameter obj) {
        	return null;
    }

    @objid ("660ccda5-8f49-4e44-8c44-964f114fb78c")
    @Override
    public Object visitTemplateParameterSubstitution(TemplateParameterSubstitution obj) {
        	return null;
    }

    @objid ("e67c8948-d428-490c-bee4-97e27ae88fd5")
    @Override
    public Object visitTerminatePseudoState(TerminatePseudoState obj) {
        	return null;
    }

    @objid ("fee76840-e68e-411c-9793-b8b60f414375")
    @Override
    public Object visitTerminateSpecification(TerminateSpecification obj) {
        	return null;
    }

    @objid ("a1ef60c2-4b7d-497d-ac85-625c6e172ce5")
    @Override
    public Object visitTransition(Transition obj) {
        	return null;
    }

    @objid ("2762957b-8c3b-4f9c-998b-8e40f1f300e6")
    @Override
    public Object visitUmlModelElement(UmlModelElement obj) {
        	return null;
    }

    @objid ("6d8ae240-b0c1-464d-b145-755ddb41e7ba")
    @Override
    public Object visitUsage(Usage obj) {
        	return null;
    }

    @objid ("3bfe655f-1463-4de8-8dca-089a23ea6211")
    @Override
    public Object visitUseCase(UseCase obj) {
        	return null;
    }

    @objid ("683ef920-0145-4909-b4e2-65c966a39014")
    @Override
    public Object visitUseCaseDependency(UseCaseDependency obj) {
        	return null;
    }

    @objid ("68d79ecd-c9f3-446a-b9ad-ae819dc1259e")
    @Override
    public Object visitUseCaseDiagram(UseCaseDiagram obj) {
        	return null;
    }

    @objid ("fd7beacd-39ac-454f-8f72-b604dc7216cd")
    @Override
    public Object visitValuePin(ValuePin obj) {
        	return null;
    }

}
