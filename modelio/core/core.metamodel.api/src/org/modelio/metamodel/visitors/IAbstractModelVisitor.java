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
 * 
 */
@objid ("6b4da49c-5452-428c-b2d8-2406b179c380")
public interface IAbstractModelVisitor extends IModelVisitor {
    @objid ("5ebb85cf-c245-44c2-b4ba-bd3159b0416f")
    @Override
    default Object visitAbstractPseudoState(AbstractPseudoState obj) {
        	return null;
    }

    @objid ("7d7b86ee-4ca2-4c44-9f41-06125b0ab916")
    @Override
    default Object visitAbstraction(Abstraction obj) {
        	return null;
    }

    @objid ("6653b0a0-898e-41bb-89c1-c487257ef3fe")
    @Override
    default Object visitAcceptCallEventAction(AcceptCallEventAction obj) {
        	return null;
    }

    @objid ("f21ec12b-d2e0-4afc-8b4b-11286a553526")
    @Override
    default Object visitAcceptChangeEventAction(AcceptChangeEventAction obj) {
        	return null;
    }

    @objid ("0e81d49a-aad4-4392-a6f6-768a0a20518e")
    @Override
    default Object visitAcceptSignalAction(AcceptSignalAction obj) {
        	return null;
    }

    @objid ("fe29b900-405e-4a16-9836-02b789ed7b65")
    @Override
    default Object visitAcceptTimeEventAction(AcceptTimeEventAction obj) {
        	return null;
    }

    @objid ("361f30ad-3580-4e94-af6a-fad5101ce330")
    @Override
    default Object visitActivity(Activity obj) {
        	return null;
    }

    @objid ("31f5d36a-05e6-472b-b328-1f9fdc3bd126")
    @Override
    default Object visitActivityAction(ActivityAction obj) {
        	return null;
    }

    @objid ("5ff25e28-ddd8-4fba-bd33-128d54fad3c8")
    @Override
    default Object visitActivityDiagram(ActivityDiagram obj) {
        	return null;
    }

    @objid ("72a47527-5662-440f-afbc-a624a14766ca")
    @Override
    default Object visitActivityEdge(ActivityEdge obj) {
        	return null;
    }

    @objid ("8de70f21-a33c-4124-b5cc-68b4dced0136")
    @Override
    default Object visitActivityFinalNode(ActivityFinalNode obj) {
        	return null;
    }

    @objid ("caf43b06-5dc0-4b42-b55f-496f1ab91692")
    @Override
    default Object visitActivityGroup(ActivityGroup obj) {
        	return null;
    }

    @objid ("a9ce9b4e-1f8e-40c2-86dc-4131cbf33c12")
    @Override
    default Object visitActivityNode(ActivityNode obj) {
        	return null;
    }

    @objid ("c54ce4e8-39fb-40d9-8453-126f85bfd40b")
    @Override
    default Object visitActivityParameterNode(ActivityParameterNode obj) {
        	return null;
    }

    @objid ("b8c0b97d-3910-4b47-a5c3-0c7ae4241518")
    @Override
    default Object visitActivityPartition(ActivityPartition obj) {
        	return null;
    }

    @objid ("434c3b84-7956-421e-9dd7-4aca4cf28f3e")
    @Override
    default Object visitActor(Actor obj) {
        	return null;
    }

    @objid ("e6304249-33d3-408b-9128-0f3597cebc5b")
    @Override
    default Object visitArtifact(Artifact obj) {
        	return null;
    }

    @objid ("d6aa0ec9-1428-4f7c-871e-bcb8e23f7311")
    @Override
    default Object visitAssociation(Association obj) {
        	return null;
    }

    @objid ("f9079003-2352-4af7-95ed-8d603bded3a7")
    @Override
    default Object visitAssociationEnd(AssociationEnd obj) {
        	return null;
    }

    @objid ("4dcda675-6c0a-45e9-926c-0ffd2f387773")
    @Override
    default Object visitAttribute(Attribute obj) {
        	return null;
    }

    @objid ("88c426f9-80bd-40f0-956a-e4afc2aec4fb")
    @Override
    default Object visitAttributeLink(AttributeLink obj) {
        	return null;
    }

    @objid ("b718e563-f71a-4737-b94c-89ad2eb4f0c8")
    @Override
    default Object visitBehavior(Behavior obj) {
        	return null;
    }

    @objid ("4ead324a-ce1a-4505-9047-86f1208f2b2a")
    @Override
    default Object visitBehaviorDiagram(BehaviorDiagram obj) {
        	return null;
    }

    @objid ("dddea03e-6a0f-4fbc-b9d3-4a2fbb5b6ef5")
    @Override
    default Object visitBehaviorParameter(BehaviorParameter obj) {
        	return null;
    }

    @objid ("83064d27-9ca9-4d4b-a5f2-cf194457d33b")
    @Override
    default Object visitBehavioralFeature(BehavioralFeature obj) {
        	return null;
    }

    @objid ("3b7cab28-158a-4044-b528-14da13fe6bd1")
    @Override
    default Object visitBindableInstance(BindableInstance obj) {
        	return null;
    }

    @objid ("03c628b3-513c-4791-a40b-87314be68d85")
    @Override
    default Object visitBinding(Binding obj) {
        	return null;
    }

    @objid ("966d568c-f0ef-43dd-ae7c-bc2d6b184ac7")
    @Override
    default Object visitBpmnActivity(BpmnActivity obj) {
        	return null;
    }

    @objid ("3a8fa03a-23f6-430b-acde-84b7e35434cc")
    @Override
    default Object visitBpmnAdHocSubProcess(BpmnAdHocSubProcess obj) {
        	return null;
    }

    @objid ("e2cd6eac-8dc6-4ce9-90b4-1d10773f0396")
    @Override
    default Object visitBpmnArtifact(BpmnArtifact obj) {
        	return null;
    }

    @objid ("058fd5e2-980d-4df3-8ee0-9b5ec59844c9")
    @Override
    default Object visitBpmnAssociation(BpmnAssociation obj) {
        	return null;
    }

    @objid ("679ab675-221d-456a-b138-982a7af00f12")
    @Override
    default Object visitBpmnBaseElement(BpmnBaseElement obj) {
        	return null;
    }

    @objid ("5d4f6351-a3af-4be0-860a-7ed782b8f75f")
    @Override
    default Object visitBpmnBoundaryEvent(BpmnBoundaryEvent obj) {
        	return null;
    }

    @objid ("81686058-4243-4717-ad2a-e4606b5859ea")
    @Override
    default Object visitBpmnBusinessRuleTask(BpmnBusinessRuleTask obj) {
        	return null;
    }

    @objid ("c76de1b4-36f3-4bb8-9337-05509a870b46")
    @Override
    default Object visitBpmnCallActivity(BpmnCallActivity obj) {
        	return null;
    }

    @objid ("34385eb8-335f-4ea8-a309-08ab0e466cf4")
    @Override
    default Object visitBpmnCancelEventDefinition(BpmnCancelEventDefinition obj) {
        	return null;
    }

    @objid ("1209b7d0-d3e5-4b89-82a0-8d71d6e603c6")
    @Override
    default Object visitBpmnCatchEvent(BpmnCatchEvent obj) {
        	return null;
    }

    @objid ("5b37d29b-2db4-4688-acd2-53ed06196c79")
    @Override
    default Object visitBpmnCollaboration(BpmnCollaboration obj) {
        	return null;
    }

    @objid ("71af4d1c-a5f1-4b1a-bc33-64475c67f6e5")
    @Override
    default Object visitBpmnCollaborationDiagram(BpmnCollaborationDiagram obj) {
        	return null;
    }

    @objid ("3e0839ac-470f-41ad-bd3b-d64c401e580b")
    @Override
    default Object visitBpmnCompensateEventDefinition(BpmnCompensateEventDefinition obj) {
        	return null;
    }

    @objid ("d2925282-903b-49fe-b582-d01b998a4be3")
    @Override
    default Object visitBpmnComplexBehaviorDefinition(BpmnComplexBehaviorDefinition obj) {
        	return null;
    }

    @objid ("2ff1ba07-f139-4663-86c8-d9141fb2234e")
    @Override
    default Object visitBpmnComplexGateway(BpmnComplexGateway obj) {
        	return null;
    }

    @objid ("8edf94fa-397d-4d29-a233-dfc2585e85b6")
    @Override
    default Object visitBpmnConditionalEventDefinition(BpmnConditionalEventDefinition obj) {
        	return null;
    }

    @objid ("67602716-2865-4da4-b40b-a47ffa1acec7")
    @Override
    default Object visitBpmnDataAssociation(BpmnDataAssociation obj) {
        	return null;
    }

    @objid ("b4ad2be9-d6c4-461e-938f-c06a26e7a483")
    @Override
    default Object visitBpmnDataInput(BpmnDataInput obj) {
        	return null;
    }

    @objid ("8d6feb29-f8c2-4672-9e40-fede079c4fa0")
    @Override
    default Object visitBpmnDataObject(BpmnDataObject obj) {
        	return null;
    }

    @objid ("dc8bde9c-dc91-487a-bbdb-b3f2ecd36414")
    @Override
    default Object visitBpmnDataOutput(BpmnDataOutput obj) {
        	return null;
    }

    @objid ("202c9372-b6b4-40a4-87f3-3cafc44cb1d0")
    @Override
    default Object visitBpmnDataState(BpmnDataState obj) {
        	return null;
    }

    @objid ("1e166525-1192-4456-bba5-7c91330c42ad")
    @Override
    default Object visitBpmnDataStore(BpmnDataStore obj) {
        	return null;
    }

    @objid ("c1aac140-908b-4101-b859-89e13ff7dd7a")
    @Override
    default Object visitBpmnEndEvent(BpmnEndEvent obj) {
        	return null;
    }

    @objid ("7907fe5f-a0b9-4e6c-9cfb-f3dde2923b53")
    @Override
    default Object visitBpmnEndPoint(BpmnEndPoint obj) {
        	return null;
    }

    @objid ("773194f1-a3be-4bac-8492-ec446c4d9cc5")
    @Override
    default Object visitBpmnErrorEventDefinition(BpmnErrorEventDefinition obj) {
        	return null;
    }

    @objid ("f7f5d849-d94a-4a1a-8f0b-dddf8a18ffc7")
    @Override
    default Object visitBpmnEscalationEventDefinition(BpmnEscalationEventDefinition obj) {
        	return null;
    }

    @objid ("58c56a87-0b9d-493a-940a-f414855f7376")
    @Override
    default Object visitBpmnEvent(BpmnEvent obj) {
        	return null;
    }

    @objid ("a62874ed-8788-49f4-b92c-746af330f989")
    @Override
    default Object visitBpmnEventBasedGateway(BpmnEventBasedGateway obj) {
        	return null;
    }

    @objid ("131cf383-3164-4f4b-a7eb-0bd6b8a77d10")
    @Override
    default Object visitBpmnEventDefinition(BpmnEventDefinition obj) {
        	return null;
    }

    @objid ("c25b3a53-3d7a-4a6e-847a-8d94718dc338")
    @Override
    default Object visitBpmnExclusiveGateway(BpmnExclusiveGateway obj) {
        	return null;
    }

    @objid ("ec300adb-bf48-46b0-a304-ef19cf9d1c55")
    @Override
    default Object visitBpmnFlowElement(BpmnFlowElement obj) {
        	return null;
    }

    @objid ("5c6f2ce8-9f5d-4ddb-b4d3-13829fe6adf0")
    @Override
    default Object visitBpmnFlowNode(BpmnFlowNode obj) {
        	return null;
    }

    @objid ("8f0c0093-972c-45cc-8efa-b3eb8a5c4e20")
    @Override
    default Object visitBpmnGateway(BpmnGateway obj) {
        	return null;
    }

    @objid ("c993ff00-054f-4a18-8fa2-19ff1a04879b")
    @Override
    default Object visitBpmnGroup(BpmnGroup obj) {
        	return null;
    }

    @objid ("9f8f653a-a0ba-458e-96b0-f8efab1354bc")
    @Override
    default Object visitBpmnImplicitThrowEvent(BpmnImplicitThrowEvent obj) {
        	return null;
    }

    @objid ("c52cc0c7-e89a-404e-9b58-f3a9296fcd50")
    @Override
    default Object visitBpmnInclusiveGateway(BpmnInclusiveGateway obj) {
        	return null;
    }

    @objid ("83bf26dd-d628-49a3-97f1-8d18e9441da7")
    @Override
    default Object visitBpmnInterface(BpmnInterface obj) {
        	return null;
    }

    @objid ("1856f4bf-7e24-4242-a23e-4a31e0ce1b3f")
    @Override
    default Object visitBpmnIntermediateCatchEvent(BpmnIntermediateCatchEvent obj) {
        	return null;
    }

    @objid ("2cd2cb5f-1232-449e-b4b1-91685e86bc7b")
    @Override
    default Object visitBpmnIntermediateThrowEvent(BpmnIntermediateThrowEvent obj) {
        	return null;
    }

    @objid ("6717f1c8-07ff-4f78-8bd2-643ce52064a2")
    @Override
    default Object visitBpmnItemAwareElement(BpmnItemAwareElement obj) {
        	return null;
    }

    @objid ("a2bd7f0d-0700-4c0f-b832-f9a7b529a429")
    @Override
    default Object visitBpmnItemDefinition(BpmnItemDefinition obj) {
        	return null;
    }

    @objid ("7783e541-8b17-4e6e-9626-8a7b6ed217b7")
    @Override
    default Object visitBpmnLane(BpmnLane obj) {
        	return null;
    }

    @objid ("0bf3b165-fae9-43f0-b49e-5db95ecc3ee4")
    @Override
    default Object visitBpmnLaneSet(BpmnLaneSet obj) {
        	return null;
    }

    @objid ("08972a23-2575-4829-ae99-ef5bdb5b6c56")
    @Override
    default Object visitBpmnLinkEventDefinition(BpmnLinkEventDefinition obj) {
        	return null;
    }

    @objid ("2dc41286-4dc3-4b07-b055-d9a5b239f97b")
    @Override
    default Object visitBpmnLoopCharacteristics(BpmnLoopCharacteristics obj) {
        	return null;
    }

    @objid ("8f3bf872-10b8-44ff-8514-a708236e00e8")
    @Override
    default Object visitBpmnManualTask(BpmnManualTask obj) {
        	return null;
    }

    @objid ("6269bab4-6bb7-4dfa-9a53-81539d490c18")
    @Override
    default Object visitBpmnMessage(BpmnMessage obj) {
        	return null;
    }

    @objid ("b76131e3-9e90-448b-be96-26703680317a")
    @Override
    default Object visitBpmnMessageEventDefinition(BpmnMessageEventDefinition obj) {
        	return null;
    }

    @objid ("ab59d377-cc82-4dd2-86cd-19bbb7ad7510")
    @Override
    default Object visitBpmnMessageFlow(BpmnMessageFlow obj) {
        	return null;
    }

    @objid ("66bc1321-2f2b-4576-92e8-54dbe12b1bb9")
    @Override
    default Object visitBpmnMultiInstanceLoopCharacteristics(BpmnMultiInstanceLoopCharacteristics obj) {
        	return null;
    }

    @objid ("636e2663-4c0c-42f0-b5ea-5bf6d3854374")
    @Override
    default Object visitBpmnOperation(BpmnOperation obj) {
        	return null;
    }

    @objid ("21e39d08-0829-4226-9404-c6740cdfc4fc")
    @Override
    default Object visitBpmnParallelGateway(BpmnParallelGateway obj) {
        	return null;
    }

    @objid ("ed5fbf3a-dd19-44ae-b1db-5fb916a7dcbe")
    @Override
    default Object visitBpmnParticipant(BpmnParticipant obj) {
        	return null;
    }

    @objid ("37ddd854-9c0c-4006-b358-c3cf970b1ae2")
    @Override
    default Object visitBpmnProcess(BpmnProcess obj) {
        	return null;
    }

    @objid ("5a9c6d50-f1fe-476f-a237-ae45abff8859")
    @Override
    default Object visitBpmnProcessCollaborationDiagram(BpmnProcessCollaborationDiagram obj) {
        	return null;
    }

    @objid ("bf68f0f9-9009-487f-9c9c-3ac59d5738ad")
    @Override
    default Object visitBpmnProcessDesignDiagram(BpmnProcessDesignDiagram obj) {
        	return null;
    }

    @objid ("ff929ddc-9a27-4705-bf7f-e6dc263ab657")
    @Override
    default Object visitBpmnReceiveTask(BpmnReceiveTask obj) {
        	return null;
    }

    @objid ("fd834d1b-6964-41d1-b4b4-f568adaa1b2d")
    @Override
    default Object visitBpmnResource(BpmnResource obj) {
        	return null;
    }

    @objid ("3374238d-a624-4be2-8ddd-2538df496152")
    @Override
    default Object visitBpmnResourceParameter(BpmnResourceParameter obj) {
        	return null;
    }

    @objid ("f7fc4e30-0604-416b-bbdb-e88bd64be765")
    @Override
    default Object visitBpmnResourceParameterBinding(BpmnResourceParameterBinding obj) {
        	return null;
    }

    @objid ("318a5855-cd71-4eea-9246-a9c2a06e8697")
    @Override
    default Object visitBpmnResourceRole(BpmnResourceRole obj) {
        	return null;
    }

    @objid ("d787f7e8-0369-4b89-a1fb-24bab4d6609e")
    @Override
    default Object visitBpmnScriptTask(BpmnScriptTask obj) {
        	return null;
    }

    @objid ("af48fcfc-2a57-482c-b4f2-ab1e8ab1e4d5")
    @Override
    default Object visitBpmnSendTask(BpmnSendTask obj) {
        	return null;
    }

    @objid ("b0fd9fba-626d-4096-b5f0-3dd4d3c7613a")
    @Override
    default Object visitBpmnSequenceFlow(BpmnSequenceFlow obj) {
        	return null;
    }

    @objid ("5ede21d7-0aac-4835-b471-09bf41c68156")
    @Override
    default Object visitBpmnSequenceFlowDataAssociation(BpmnSequenceFlowDataAssociation obj) {
        	return null;
    }

    @objid ("9d698dec-061b-4f56-9361-53b0ecaefbf8")
    @Override
    default Object visitBpmnServiceTask(BpmnServiceTask obj) {
        	return null;
    }

    @objid ("0e855695-aa3d-4cf1-b432-872903e74090")
    @Override
    default Object visitBpmnSharedDefinitions(BpmnSharedDefinitions obj) {
        	return null;
    }

    @objid ("a270dcc3-761b-41f4-a507-b4432ba98093")
    @Override
    default Object visitBpmnSharedElement(BpmnSharedElement obj) {
        	return null;
    }

    @objid ("aea143a3-d4cd-4e64-8835-2102907ac8dc")
    @Override
    default Object visitBpmnSignalEventDefinition(BpmnSignalEventDefinition obj) {
        	return null;
    }

    @objid ("03b67e02-9f1d-4f85-811d-7a85d600d51d")
    @Override
    default Object visitBpmnStandardLoopCharacteristics(BpmnStandardLoopCharacteristics obj) {
        	return null;
    }

    @objid ("226c8f73-a625-4e09-822f-f14455e568c1")
    @Override
    default Object visitBpmnStartEvent(BpmnStartEvent obj) {
        	return null;
    }

    @objid ("609fd43a-033c-4425-a353-07a0a614b64e")
    @Override
    default Object visitBpmnSubProcess(BpmnSubProcess obj) {
        	return null;
    }

    @objid ("9ae70d46-7829-48ee-8bde-b67063f26f36")
    @Override
    default Object visitBpmnSubProcessDiagram(BpmnSubProcessDiagram obj) {
        	return null;
    }

    @objid ("dd32feed-ac4a-482f-943c-2a0d879b22e6")
    @Override
    default Object visitBpmnTask(BpmnTask obj) {
        	return null;
    }

    @objid ("11aab72e-2b44-4a9f-b670-f9817584ca0a")
    @Override
    default Object visitBpmnTerminateEventDefinition(BpmnTerminateEventDefinition obj) {
        	return null;
    }

    @objid ("c59b5919-a7ab-4aae-ac2e-1e2497760c36")
    @Override
    default Object visitBpmnThrowEvent(BpmnThrowEvent obj) {
        	return null;
    }

    @objid ("e361ca1b-10e4-4c6d-9af6-90ad9cb1e0f6")
    @Override
    default Object visitBpmnTimerEventDefinition(BpmnTimerEventDefinition obj) {
        	return null;
    }

    @objid ("00c98e45-2f77-4c39-9dfd-bc1cb8283fae")
    @Override
    default Object visitBpmnTransaction(BpmnTransaction obj) {
        	return null;
    }

    @objid ("d27eca5d-83c3-4ea4-9d03-c5c31c5d0ad9")
    @Override
    default Object visitBpmnUserTask(BpmnUserTask obj) {
        	return null;
    }

    @objid ("ef804ab9-cb7a-41d0-b1f9-2ba5f2287f70")
    @Override
    default Object visitCallAction(CallAction obj) {
        	return null;
    }

    @objid ("da9e10bc-5e82-40b1-9434-0450aa08ec1e")
    @Override
    default Object visitCallBehaviorAction(CallBehaviorAction obj) {
        	return null;
    }

    @objid ("4097686d-fb1d-493e-924c-5c7a10b54696")
    @Override
    default Object visitCallOperationAction(CallOperationAction obj) {
        	return null;
    }

    @objid ("9af5e849-d340-4b53-bcaf-531c56b01519")
    @Override
    default Object visitCentralBufferNode(CentralBufferNode obj) {
        	return null;
    }

    @objid ("4331e24c-8be4-4f6a-9d1e-fd571150d8ef")
    @Override
    default Object visitChoicePseudoState(ChoicePseudoState obj) {
        	return null;
    }

    @objid ("f67d0f77-005f-4203-bbeb-e22fec2c441d")
    @Override
    default Object visitClass(Class obj) {
        	return null;
    }

    @objid ("06336d53-3e44-44f8-b27a-e02a58ad3e2e")
    @Override
    default Object visitClassAssociation(ClassAssociation obj) {
        	return null;
    }

    @objid ("40f43c72-14ba-41b8-a083-343d1ec6d9dd")
    @Override
    default Object visitClassDiagram(ClassDiagram obj) {
        	return null;
    }

    @objid ("cbd0264b-3426-4525-b656-8fbedb4c41d8")
    @Override
    default Object visitClassifier(Classifier obj) {
        	return null;
    }

    @objid ("95240173-d26f-488a-8221-bb10fab6792c")
    @Override
    default Object visitClause(Clause obj) {
        	return null;
    }

    @objid ("b7918469-2054-4284-b790-928a1680f95f")
    @Override
    default Object visitCollaboration(Collaboration obj) {
        	return null;
    }

    @objid ("4b078aa5-6817-4c1f-828b-2ed0f83977e9")
    @Override
    default Object visitCollaborationUse(CollaborationUse obj) {
        	return null;
    }

    @objid ("9212179e-5d74-43bd-bc37-770398ce0ac1")
    @Override
    default Object visitCombinedFragment(CombinedFragment obj) {
        	return null;
    }

    @objid ("ebe603a6-4a71-4cfb-a966-86464cf0e815")
    @Override
    default Object visitCommunicationChannel(CommunicationChannel obj) {
        	return null;
    }

    @objid ("3d6c02a6-8bdd-4334-9ddb-de173c8c13a3")
    @Override
    default Object visitCommunicationDiagram(CommunicationDiagram obj) {
        	return null;
    }

    @objid ("a2890f06-09f1-425e-8acf-bc9ce84c88dc")
    @Override
    default Object visitCommunicationInteraction(CommunicationInteraction obj) {
        	return null;
    }

    @objid ("f5cf1ea9-4ffe-473e-a9b7-f3593bdd6b86")
    @Override
    default Object visitCommunicationMessage(CommunicationMessage obj) {
        	return null;
    }

    @objid ("88039f5a-eff4-4d96-8e78-1951e233c65c")
    @Override
    default Object visitCommunicationNode(CommunicationNode obj) {
        	return null;
    }

    @objid ("0f51bf14-7740-41c4-b32a-00c6665d1410")
    @Override
    default Object visitComponent(Component obj) {
        	return null;
    }

    @objid ("70f6bb01-7f2d-4bed-bcff-31e4b41aaf75")
    @Override
    default Object visitComponentRealization(ComponentRealization obj) {
        	return null;
    }

    @objid ("0bf13011-aa4d-4554-9f6e-39915a1acce5")
    @Override
    default Object visitCompositeStructureDiagram(CompositeStructureDiagram obj) {
        	return null;
    }

    @objid ("1ea48f1a-8256-4d49-8689-15a833afed8b")
    @Override
    default Object visitConditionalNode(ConditionalNode obj) {
        	return null;
    }

    @objid ("238a0286-1788-4a3d-8996-f9a6f7a29f38")
    @Override
    default Object visitConnectionPointReference(ConnectionPointReference obj) {
        	return null;
    }

    @objid ("c39cdba3-9366-4d5c-b71b-a06d950c52c9")
    @Override
    default Object visitConnector(Connector obj) {
        	return null;
    }

    @objid ("fd43778b-b483-4630-a85e-dd67d0946adf")
    @Override
    default Object visitConnectorEnd(ConnectorEnd obj) {
        	return null;
    }

    @objid ("2ed5e9e4-560f-42a8-bbe2-40658d3f0d72")
    @Override
    default Object visitConstraint(Constraint obj) {
        	return null;
    }

    @objid ("bfbf9117-0253-4a29-be9d-6a013378fe92")
    @Override
    default Object visitControlFlow(ControlFlow obj) {
        	return null;
    }

    @objid ("7a38797a-3753-4178-b396-deb206582765")
    @Override
    default Object visitControlNode(ControlNode obj) {
        	return null;
    }

    @objid ("41325040-b55a-4ad8-86e9-1915d824b8a9")
    @Override
    default Object visitDataFlow(DataFlow obj) {
        	return null;
    }

    @objid ("01cd65d6-13f9-4ded-ac2c-a8bfa2c401f6")
    @Override
    default Object visitDataStoreNode(DataStoreNode obj) {
        	return null;
    }

    @objid ("496e44d5-fa18-443a-9625-9612ffc144cb")
    @Override
    default Object visitDataType(DataType obj) {
        	return null;
    }

    @objid ("92b7d846-ec27-46e5-b1fe-81e67a748fb9")
    @Override
    default Object visitDecisionMergeNode(DecisionMergeNode obj) {
        	return null;
    }

    @objid ("7626179b-e22a-4327-9027-5121c64ffcbb")
    @Override
    default Object visitDeepHistoryPseudoState(DeepHistoryPseudoState obj) {
        	return null;
    }

    @objid ("a12afe55-b9a9-4354-af62-3cdd84094277")
    @Override
    default Object visitDeploymentDiagram(DeploymentDiagram obj) {
        	return null;
    }

    @objid ("4228dfaf-7fa9-4cb0-b6fe-ede5e866d2e3")
    @Override
    default Object visitDurationConstraint(DurationConstraint obj) {
        	return null;
    }

    @objid ("fbe7b9fa-cc54-4d5d-8c90-21a468d6d73a")
    @Override
    default Object visitElementImport(ElementImport obj) {
        	return null;
    }

    @objid ("caa4e9f8-a19f-48e4-a51c-d59a9e58bd75")
    @Override
    default Object visitElementRealization(ElementRealization obj) {
        	return null;
    }

    @objid ("4b38e2d1-2220-4ea3-934e-aa79b299b026")
    @Override
    default Object visitEntryPointPseudoState(EntryPointPseudoState obj) {
        	return null;
    }

    @objid ("b06715a9-a2cf-47ba-a14d-240533322404")
    @Override
    default Object visitEnumeration(Enumeration obj) {
        	return null;
    }

    @objid ("b054c90e-e550-47ff-a51d-ea38a9677a0c")
    @Override
    default Object visitEnumerationLiteral(EnumerationLiteral obj) {
        	return null;
    }

    @objid ("d11aec30-084e-428c-b56e-18d86a0e07c0")
    @Override
    default Object visitEvent(Event obj) {
        	return null;
    }

    @objid ("1c94a70d-b574-443f-bd71-cdbb99b42c34")
    @Override
    default Object visitExceptionHandler(ExceptionHandler obj) {
        	return null;
    }

    @objid ("f2de41d8-c5b1-4fcc-a62c-cf4b3589febe")
    @Override
    default Object visitExecutionOccurenceSpecification(ExecutionOccurenceSpecification obj) {
        	return null;
    }

    @objid ("1f10259e-a3b1-4ee3-8355-40a545fc5607")
    @Override
    default Object visitExecutionSpecification(ExecutionSpecification obj) {
        	return null;
    }

    @objid ("24290d8b-201b-401a-9371-feaffbd2a430")
    @Override
    default Object visitExitPointPseudoState(ExitPointPseudoState obj) {
        	return null;
    }

    @objid ("5ff1c3d3-4f0a-4bd8-ac28-96eb927099ef")
    @Override
    default Object visitExpansionNode(ExpansionNode obj) {
        	return null;
    }

    @objid ("da40172e-8486-4915-ad86-a225e7597441")
    @Override
    default Object visitExpansionRegion(ExpansionRegion obj) {
        	return null;
    }

    @objid ("4f24f16f-4bff-4ef7-afa0-3005d8771cb5")
    @Override
    default Object visitExtensionPoint(ExtensionPoint obj) {
        	return null;
    }

    @objid ("18d911c4-4dde-405b-b23f-21b2a11b8765")
    @Override
    default Object visitFeature(Feature obj) {
        	return null;
    }

    @objid ("92bf2e7e-61a0-43ed-a17d-ced44aa4adc3")
    @Override
    default Object visitFinalNode(FinalNode obj) {
        	return null;
    }

    @objid ("a86acee3-54c1-43fe-9bd8-742b0da07b35")
    @Override
    default Object visitFinalState(FinalState obj) {
        	return null;
    }

    @objid ("cba5c66c-eca6-4b15-a32f-7868998daa0a")
    @Override
    default Object visitFlowFinalNode(FlowFinalNode obj) {
        	return null;
    }

    @objid ("5bd0ba36-9e6e-49d7-a7c2-e9d3da5a7cad")
    @Override
    default Object visitForkJoinNode(ForkJoinNode obj) {
        	return null;
    }

    @objid ("6b40f1f6-a242-4e39-87c7-a02a0ea8c249")
    @Override
    default Object visitForkPseudoState(ForkPseudoState obj) {
        	return null;
    }

    @objid ("6aa02fb1-ef83-44d3-afd9-ed90b96b6d9d")
    @Override
    default Object visitGate(Gate obj) {
        	return null;
    }

    @objid ("b01406bb-062f-4e3e-a79c-c56038d25aee")
    @Override
    default Object visitGeneralClass(GeneralClass obj) {
        	return null;
    }

    @objid ("d41a90a6-803e-4830-aad8-813bb4d5d621")
    @Override
    default Object visitGeneralOrdering(GeneralOrdering obj) {
        	return null;
    }

    @objid ("f28b00bc-acb4-452c-97a4-f9668a282087")
    @Override
    default Object visitGeneralization(Generalization obj) {
        	return null;
    }

    @objid ("37895137-a9e8-4e73-8d82-0d67b76d1fdd")
    @Override
    default Object visitInformationFlow(InformationFlow obj) {
        	return null;
    }

    @objid ("e9028cdd-073b-468d-b4f3-88222d20d404")
    @Override
    default Object visitInformationItem(InformationItem obj) {
        	return null;
    }

    @objid ("06826be2-3d81-4ed8-925f-d0f903692425")
    @Override
    default Object visitInitialNode(InitialNode obj) {
        	return null;
    }

    @objid ("d74c5c62-9bbf-4e7b-8b03-74400950e92a")
    @Override
    default Object visitInitialPseudoState(InitialPseudoState obj) {
        	return null;
    }

    @objid ("94db8427-f3fd-4b26-9c65-241873c8b46e")
    @Override
    default Object visitInputPin(InputPin obj) {
        	return null;
    }

    @objid ("cacd78a4-b68d-41c4-9143-694507b683ec")
    @Override
    default Object visitInstance(Instance obj) {
        	return null;
    }

    @objid ("c3eb46aa-f783-48e5-ac3a-91cc7f60796a")
    @Override
    default Object visitInstanceNode(InstanceNode obj) {
        	return null;
    }

    @objid ("f13971f5-8cd4-4d07-8ffa-b30dcec083a1")
    @Override
    default Object visitInteraction(Interaction obj) {
        	return null;
    }

    @objid ("2285eaa6-a33e-4c25-9cbc-ee0574ad38f7")
    @Override
    default Object visitInteractionFragment(InteractionFragment obj) {
        	return null;
    }

    @objid ("d9851624-cb46-4649-88e9-4eeb4889f659")
    @Override
    default Object visitInteractionOperand(InteractionOperand obj) {
        	return null;
    }

    @objid ("9aa2e545-dc64-4bdb-aeda-4815ad20ec42")
    @Override
    default Object visitInteractionUse(InteractionUse obj) {
        	return null;
    }

    @objid ("1bb53b82-92d6-41d8-aa99-4ee57d3c73b5")
    @Override
    default Object visitInterface(Interface obj) {
        	return null;
    }

    @objid ("0b5e5d71-1fcc-41cc-8e27-aa7cb6505981")
    @Override
    default Object visitInterfaceRealization(InterfaceRealization obj) {
        	return null;
    }

    @objid ("4f174aa2-edf3-406e-bbfd-38fc359d3b42")
    @Override
    default Object visitInternalTransition(InternalTransition obj) {
        	return null;
    }

    @objid ("3233fd23-27b9-446b-bdb9-33c5dd2c0a2b")
    @Override
    default Object visitInterruptibleActivityRegion(InterruptibleActivityRegion obj) {
        	return null;
    }

    @objid ("7d8a7449-6938-4b50-aaf8-5f5e5663d32a")
    @Override
    default Object visitJoinPseudoState(JoinPseudoState obj) {
        	return null;
    }

    @objid ("9ec5bc95-7ba7-450c-ac26-d9acb1a15812")
    @Override
    default Object visitJunctionPseudoState(JunctionPseudoState obj) {
        	return null;
    }

    @objid ("94f659f9-45b2-43d1-b496-151f5997f95a")
    @Override
    default Object visitLifeline(Lifeline obj) {
        	return null;
    }

    @objid ("c8b3b3fb-8bd4-4ab1-bab8-a57986758932")
    @Override
    default Object visitLink(Link obj) {
        	return null;
    }

    @objid ("04a322ea-8678-4c54-a0f6-108866649404")
    @Override
    default Object visitLinkEnd(LinkEnd obj) {
        	return null;
    }

    @objid ("80bcf25d-1060-4a53-b608-68ed5642f448")
    @Override
    default Object visitLoopNode(LoopNode obj) {
        	return null;
    }

    @objid ("f9b2e19b-1026-41c2-ab6d-14423e5fe52d")
    @Override
    default Object visitManifestation(Manifestation obj) {
        	return null;
    }

    @objid ("e1a084a0-27c4-4156-88df-d61cbadb092b")
    @Override
    default Object visitMessage(Message obj) {
        	return null;
    }

    @objid ("b03adbe8-4b9e-4170-b19f-77fdcba0aa14")
    @Override
    default Object visitMessageEnd(MessageEnd obj) {
        	return null;
    }

    @objid ("3bac40c4-326e-4548-abcf-10949adaec15")
    @Override
    default Object visitMessageFlow(MessageFlow obj) {
        	return null;
    }

    @objid ("5d37b12b-c859-4aa9-999d-76eff5747862")
    @Override
    default Object visitModelTree(ModelTree obj) {
        	return null;
    }

    @objid ("a1578257-a865-4958-adaf-64f5d0f85631")
    @Override
    default Object visitNameSpace(NameSpace obj) {
        	return null;
    }

    @objid ("69f12da1-5eaf-4d1a-b498-a0b9f38a1523")
    @Override
    default Object visitNaryAssociation(NaryAssociation obj) {
        	return null;
    }

    @objid ("3011c244-acb1-4897-a0b1-b34e969fe91b")
    @Override
    default Object visitNaryAssociationEnd(NaryAssociationEnd obj) {
        	return null;
    }

    @objid ("763a7a17-00ce-48e1-b564-7f18f37be3ed")
    @Override
    default Object visitNaryConnector(NaryConnector obj) {
        	return null;
    }

    @objid ("ff8c5eb9-7ad1-4b88-955a-7fbd126de2e7")
    @Override
    default Object visitNaryConnectorEnd(NaryConnectorEnd obj) {
        	return null;
    }

    @objid ("9eb8b226-869c-4f8f-833c-ec84d5559f87")
    @Override
    default Object visitNaryLink(NaryLink obj) {
        	return null;
    }

    @objid ("31ada104-0cc8-4bef-ab94-84384700b8c9")
    @Override
    default Object visitNaryLinkEnd(NaryLinkEnd obj) {
        	return null;
    }

    @objid ("37ffb9b1-ad24-4287-9a65-e8f60ea1048e")
    @Override
    default Object visitNode(Node obj) {
        	return null;
    }

    @objid ("7efe6375-d9aa-464e-a558-3e911aeee621")
    @Override
    default Object visitObjectDiagram(ObjectDiagram obj) {
        	return null;
    }

    @objid ("f015740d-75b6-4991-bcd7-552f02417229")
    @Override
    default Object visitObjectFlow(ObjectFlow obj) {
        	return null;
    }

    @objid ("59adf986-bbe2-4596-a933-a03a52f693cc")
    @Override
    default Object visitObjectNode(ObjectNode obj) {
        	return null;
    }

    @objid ("1fef4a64-2fd5-4daa-bdc7-eec32e2ca9a2")
    @Override
    default Object visitOccurrenceSpecification(OccurrenceSpecification obj) {
        	return null;
    }

    @objid ("18657529-5a57-4575-a684-9eea744f4989")
    @Override
    default Object visitOpaqueAction(OpaqueAction obj) {
        	return null;
    }

    @objid ("60dd3ce5-96a8-492b-9ea5-4df7389020e4")
    @Override
    default Object visitOpaqueBehavior(OpaqueBehavior obj) {
        	return null;
    }

    @objid ("374421e0-31f2-4819-9e70-2f62d06b9c5c")
    @Override
    default Object visitOperation(Operation obj) {
        	return null;
    }

    @objid ("51e880a1-f36e-400a-b785-394573f7eb47")
    @Override
    default Object visitOutputPin(OutputPin obj) {
        	return null;
    }

    @objid ("32b6d625-599d-46d5-8802-029706646cc8")
    @Override
    default Object visitPackage(Package obj) {
        	return null;
    }

    @objid ("f930fce4-f738-4b4f-9c4e-8eebcd3635db")
    @Override
    default Object visitPackageImport(PackageImport obj) {
        	return null;
    }

    @objid ("b1fd7d08-5e9d-45f5-a696-fccae142ee92")
    @Override
    default Object visitPackageMerge(PackageMerge obj) {
        	return null;
    }

    @objid ("2b31cd80-02b5-4d1f-8220-8f5a28f1feb9")
    @Override
    default Object visitParameter(Parameter obj) {
        	return null;
    }

    @objid ("a8f96e41-adc3-4b47-9ec6-bd26c5ac988d")
    @Override
    default Object visitPartDecomposition(PartDecomposition obj) {
        	return null;
    }

    @objid ("156b7ddf-2daf-4aad-8071-a2579340ce56")
    @Override
    default Object visitPin(Pin obj) {
        	return null;
    }

    @objid ("5fb628eb-80c4-418e-aa1c-5b9c537df445")
    @Override
    default Object visitPort(Port obj) {
        	return null;
    }

    @objid ("028523dd-72ed-44b7-a963-b1166b5bf713")
    @Override
    default Object visitProject(Project obj) {
        	return null;
    }

    @objid ("9b675063-7a1b-4bee-b0d5-f792be45f953")
    @Override
    default Object visitProvidedInterface(ProvidedInterface obj) {
        	return null;
    }

    @objid ("04d662c1-35a8-4993-a2ee-0ba8d806486a")
    @Override
    default Object visitRaisedException(RaisedException obj) {
        	return null;
    }

    @objid ("e4614cea-e6a1-4841-bebb-9abaa41446b5")
    @Override
    default Object visitRegion(Region obj) {
        	return null;
    }

    @objid ("6e1c8a92-4f59-43ba-9caf-056dbb0bd343")
    @Override
    default Object visitRequiredInterface(RequiredInterface obj) {
        	return null;
    }

    @objid ("71018584-5dc5-4e65-8d0b-319e8b66fb8f")
    @Override
    default Object visitSendSignalAction(SendSignalAction obj) {
        	return null;
    }

    @objid ("4a606cfc-136a-4b9a-afcb-ff817a2c17ce")
    @Override
    default Object visitSequenceDiagram(SequenceDiagram obj) {
        	return null;
    }

    @objid ("593dc115-a2e4-4cd8-bcff-0b88dec443b8")
    @Override
    default Object visitShallowHistoryPseudoState(ShallowHistoryPseudoState obj) {
        	return null;
    }

    @objid ("1d8134f0-1983-4000-a781-744f68c9d846")
    @Override
    default Object visitSignal(Signal obj) {
        	return null;
    }

    @objid ("a98cfdb2-b47b-4001-8cf9-ba3cfef7e96a")
    @Override
    default Object visitState(State obj) {
        	return null;
    }

    @objid ("9b6607b9-2ed3-456d-828e-217ab7d4b784")
    @Override
    default Object visitStateInvariant(StateInvariant obj) {
        	return null;
    }

    @objid ("ba765b2f-6528-4d70-96b3-0e05d6c4baa6")
    @Override
    default Object visitStateMachine(StateMachine obj) {
        	return null;
    }

    @objid ("d322a5c2-3900-45da-93a3-8d486a932d59")
    @Override
    default Object visitStateMachineDiagram(StateMachineDiagram obj) {
        	return null;
    }

    @objid ("1cfa79c2-6e51-477e-be15-fbb964ca3977")
    @Override
    default Object visitStateVertex(StateVertex obj) {
        	return null;
    }

    @objid ("0808f1ae-c593-439a-ad9c-4966c280a523")
    @Override
    default Object visitStaticDiagram(StaticDiagram obj) {
        	return null;
    }

    @objid ("59751cd0-7133-4611-9b9c-68e24d9384ce")
    @Override
    default Object visitStructuralFeature(StructuralFeature obj) {
        	return null;
    }

    @objid ("e24c0f57-1679-4ea5-ae61-4846d134a920")
    @Override
    default Object visitStructuredActivityNode(StructuredActivityNode obj) {
        	return null;
    }

    @objid ("d7084282-c115-48de-82be-8e8dd0b7f907")
    @Override
    default Object visitSubstitution(Substitution obj) {
        	return null;
    }

    @objid ("03085076-bf63-464b-8fee-271fe80a659c")
    @Override
    default Object visitTemplateBinding(TemplateBinding obj) {
        	return null;
    }

    @objid ("5f8d7c81-15a8-42ea-a8bc-fee28a3bdb13")
    @Override
    default Object visitTemplateParameter(TemplateParameter obj) {
        	return null;
    }

    @objid ("d1f9839f-bf20-40ee-9de5-9726241ed835")
    @Override
    default Object visitTemplateParameterSubstitution(TemplateParameterSubstitution obj) {
        	return null;
    }

    @objid ("cc79aafb-c0c5-427f-bb0a-7eddf925741a")
    @Override
    default Object visitTerminatePseudoState(TerminatePseudoState obj) {
        	return null;
    }

    @objid ("12b9b2bd-85ff-42fd-8d48-aec4d5ddd4ef")
    @Override
    default Object visitTerminateSpecification(TerminateSpecification obj) {
        	return null;
    }

    @objid ("c2c5b1e3-4bf5-49de-9b92-8f771cd155fe")
    @Override
    default Object visitTransition(Transition obj) {
        	return null;
    }

    @objid ("0719598a-da44-45f3-9d38-8d6bf0c44bc8")
    @Override
    default Object visitUmlModelElement(UmlModelElement obj) {
        	return null;
    }

    @objid ("de74f223-713b-4ea7-bb30-8004c6a7d1bc")
    @Override
    default Object visitUsage(Usage obj) {
        	return null;
    }

    @objid ("63ca153f-4e49-4780-aec3-e8f5caf0f349")
    @Override
    default Object visitUseCase(UseCase obj) {
        	return null;
    }

    @objid ("e660f0ac-a999-4689-afc8-a76a4bfa359a")
    @Override
    default Object visitUseCaseDependency(UseCaseDependency obj) {
        	return null;
    }

    @objid ("edcff567-9f9b-4644-a70d-822d33fa75c4")
    @Override
    default Object visitUseCaseDiagram(UseCaseDiagram obj) {
        	return null;
    }

    @objid ("1622772e-4a37-40cf-8d69-3b3250d91f87")
    @Override
    default Object visitValuePin(ValuePin obj) {
        	return null;
    }
}

