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
import org.modelio.metamodel.visitors.IInfrastructureVisitor;

/**
 * This class is an implementation of {@link IModelVisitor} whose default strategy consists in transmitting the visit call to the super class visit method.
 * <p>On root metaclasses, the visitor tries to delegate to the configured delegation visitor if available.If not available, <code>null</code> is returned.
 * 
 */
@objid ("00280eec-4f2c-1032-829a-001ec947cd2a")
public class DefaultModelVisitor implements IModelVisitor {
    @objid ("e4bd4ff3-b7eb-49f1-849b-8a1682a33c88")
    protected IInfrastructureVisitor infrastructureVisitor = null;

    @objid ("b5ecf2e8-87de-4e80-bf09-b3daf19d1ab9")
    public  DefaultModelVisitor() {
        super();
    }

    @objid ("0128ddd0-0e56-4e35-a88c-2d21ac17ecf8")
    public  DefaultModelVisitor(IInfrastructureVisitor infrastructureVisitor) {
        super();
        this.infrastructureVisitor = infrastructureVisitor;
    }

    @objid ("741b1660-c9f8-41fb-9c03-8b6a7e5f1c96")
    @Override
    public Object visitAbstractPseudoState(AbstractPseudoState obj) {
        return visitStateVertex(obj);
        
    }

    @objid ("eafdece0-2359-412b-823d-b328e8bc9c82")
    @Override
    public Object visitAbstraction(Abstraction obj) {
        return this.infrastructureVisitor != null ? this.infrastructureVisitor.visitDependency(obj) : null;
        
    }

    @objid ("2ac33e8b-79c4-4d5f-80e5-6f64fe28f835")
    @Override
    public Object visitAcceptCallEventAction(AcceptCallEventAction obj) {
        return visitActivityAction(obj);
        
    }

    @objid ("d8a2d6cf-df8f-4072-8ce6-048579152ba7")
    @Override
    public Object visitAcceptChangeEventAction(AcceptChangeEventAction obj) {
        return visitActivityAction(obj);
        
    }

    @objid ("2a6d0c84-37c0-473a-909d-ac76524c1967")
    @Override
    public Object visitAcceptSignalAction(AcceptSignalAction obj) {
        return visitActivityAction(obj);
        
    }

    @objid ("cca3109e-dacb-427c-bfd3-570be00d0844")
    @Override
    public Object visitAcceptTimeEventAction(AcceptTimeEventAction obj) {
        return visitActivityAction(obj);
        
    }

    @objid ("0a0bacfc-30ab-4b87-a2c5-cc8161cf5221")
    @Override
    public Object visitActivity(Activity obj) {
        return visitBehavior(obj);
        
    }

    @objid ("5be5a158-8f06-47a8-a4b7-72f531c29c5c")
    @Override
    public Object visitActivityAction(ActivityAction obj) {
        return visitActivityNode(obj);
        
    }

    @objid ("5e872d8f-3b89-49dd-b7ac-a8f0a5ded6e2")
    @Override
    public Object visitActivityDiagram(ActivityDiagram obj) {
        return visitBehaviorDiagram(obj);
        
    }

    @objid ("6ca63ed7-9634-463c-8f9b-b12566fad834")
    @Override
    public Object visitActivityEdge(ActivityEdge obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("a97e784b-e74e-47e0-b882-efa73c348279")
    @Override
    public Object visitActivityFinalNode(ActivityFinalNode obj) {
        return visitFinalNode(obj);
        
    }

    @objid ("aa883e28-1f2f-46d5-a507-d97326260f23")
    @Override
    public Object visitActivityGroup(ActivityGroup obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("5b55749e-57b1-4f3b-ab02-e1836eb03472")
    @Override
    public Object visitActivityNode(ActivityNode obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("726c2b29-cede-469b-92bf-98a4b8b57dde")
    @Override
    public Object visitActivityParameterNode(ActivityParameterNode obj) {
        return visitObjectNode(obj);
        
    }

    @objid ("7067872f-f22e-43c0-a760-7043037e74b3")
    @Override
    public Object visitActivityPartition(ActivityPartition obj) {
        return visitActivityGroup(obj);
        
    }

    @objid ("9fdabe42-5cf7-482b-bdae-9eca34f23c42")
    @Override
    public Object visitActor(Actor obj) {
        return visitGeneralClass(obj);
        
    }

    @objid ("563bf729-a3a4-47d1-9d2e-59e153bc525c")
    @Override
    public Object visitArtifact(Artifact obj) {
        return visitClassifier(obj);
        
    }

    @objid ("a70c548b-da5a-450b-8d77-3dacedaace68")
    @Override
    public Object visitAssociation(Association obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("330b4dd5-7f7f-46f6-8fdc-06eea8561bf0")
    @Override
    public Object visitAssociationEnd(AssociationEnd obj) {
        return visitStructuralFeature(obj);
        
    }

    @objid ("81048df7-151d-49ed-a626-09f63e1f6434")
    @Override
    public Object visitAttribute(Attribute obj) {
        return visitStructuralFeature(obj);
        
    }

    @objid ("ab12ae58-b828-4317-ab31-ba2dc3202e0f")
    @Override
    public Object visitAttributeLink(AttributeLink obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("f4729164-dab4-4cc7-8960-3420e127f16b")
    @Override
    public Object visitBehavior(Behavior obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("371c0022-fe20-4a34-b6c4-7bdc04e8a428")
    @Override
    public Object visitBehaviorDiagram(BehaviorDiagram obj) {
        return this.infrastructureVisitor != null ? this.infrastructureVisitor.visitAbstractDiagram(obj) : null;
        
    }

    @objid ("1238eaad-4dca-40d3-9af8-409ab49b4cf1")
    @Override
    public Object visitBehaviorParameter(BehaviorParameter obj) {
        return visitParameter(obj);
        
    }

    @objid ("dcccc818-8bca-497a-970c-0639c4a7b104")
    @Override
    public Object visitBehavioralFeature(BehavioralFeature obj) {
        return visitFeature(obj);
        
    }

    @objid ("a2503d8b-6f13-4c6f-a596-2d08c53ff4d6")
    @Override
    public Object visitBindableInstance(BindableInstance obj) {
        return visitInstance(obj);
        
    }

    @objid ("3a315c2f-1ae9-4ae8-be11-51346f12087c")
    @Override
    public Object visitBinding(Binding obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("5137ac11-1f26-41c1-84ba-8c14b403dd8d")
    @Override
    public Object visitBpmnActivity(BpmnActivity obj) {
        return visitBpmnFlowNode(obj);
        
    }

    @objid ("8f7ef9da-927b-4e8f-a461-0e9454be7418")
    @Override
    public Object visitBpmnAdHocSubProcess(BpmnAdHocSubProcess obj) {
        return visitBpmnSubProcess(obj);
        
    }

    @objid ("aaa835f3-148e-4a59-a517-e545978dd2e1")
    @Override
    public Object visitBpmnArtifact(BpmnArtifact obj) {
        return visitBpmnBaseElement(obj);
        
    }

    @objid ("2ff5a56e-6dfb-4c27-92ed-7696e6699e41")
    @Override
    public Object visitBpmnAssociation(BpmnAssociation obj) {
        return visitBpmnArtifact(obj);
        
    }

    @objid ("f57c41b9-6f09-4e21-a40b-195bcab61423")
    @Override
    public Object visitBpmnBaseElement(BpmnBaseElement obj) {
        return this.infrastructureVisitor != null ? this.infrastructureVisitor.visitModelElement(obj) : null;
        
    }

    @objid ("54c964bf-df08-4d19-9d76-54936976d7d6")
    @Override
    public Object visitBpmnBoundaryEvent(BpmnBoundaryEvent obj) {
        return visitBpmnCatchEvent(obj);
        
    }

    @objid ("5e673e82-7100-41ec-bd53-70f6c650ff92")
    @Override
    public Object visitBpmnBusinessRuleTask(BpmnBusinessRuleTask obj) {
        return visitBpmnTask(obj);
        
    }

    @objid ("c3c6adfe-d15b-4745-bb2f-5599d84ac25b")
    @Override
    public Object visitBpmnCallActivity(BpmnCallActivity obj) {
        return visitBpmnActivity(obj);
        
    }

    @objid ("d0eee8b2-2367-4843-ad84-2fdc62713e38")
    @Override
    public Object visitBpmnCancelEventDefinition(BpmnCancelEventDefinition obj) {
        return visitBpmnEventDefinition(obj);
        
    }

    @objid ("d5e5eb54-3d3f-4eb8-8cc3-21de72e7358b")
    @Override
    public Object visitBpmnCatchEvent(BpmnCatchEvent obj) {
        return visitBpmnEvent(obj);
        
    }

    @objid ("0e8891d4-788a-4183-8202-63476059008a")
    @Override
    public Object visitBpmnCollaboration(BpmnCollaboration obj) {
        return visitBehavior(obj);
        
    }

    @objid ("9cfe094d-9b5f-4268-bada-1dc6ea4b9f45")
    @Override
    public Object visitBpmnCollaborationDiagram(BpmnCollaborationDiagram obj) {
        return visitBpmnProcessCollaborationDiagram(obj);
        
    }

    @objid ("b89cb471-21a5-4b2f-ab01-862df826de6f")
    @Override
    public Object visitBpmnCompensateEventDefinition(BpmnCompensateEventDefinition obj) {
        return visitBpmnEventDefinition(obj);
        
    }

    @objid ("f6a0cc8a-6512-4c80-afc0-95842a68afc4")
    @Override
    public Object visitBpmnComplexBehaviorDefinition(BpmnComplexBehaviorDefinition obj) {
        return visitBpmnBaseElement(obj);
        
    }

    @objid ("18e51cc3-12af-4567-b3cf-ba9d6d9ef86d")
    @Override
    public Object visitBpmnComplexGateway(BpmnComplexGateway obj) {
        return visitBpmnGateway(obj);
        
    }

    @objid ("d3210a59-ed28-4559-b3cf-95f5a65120f1")
    @Override
    public Object visitBpmnConditionalEventDefinition(BpmnConditionalEventDefinition obj) {
        return visitBpmnEventDefinition(obj);
        
    }

    @objid ("4d17fa7a-46ca-4273-adbc-69e51064372d")
    @Override
    public Object visitBpmnDataAssociation(BpmnDataAssociation obj) {
        return visitBpmnBaseElement(obj);
        
    }

    @objid ("d3166ee8-6fe4-4ce8-aaec-46e0c9b582ea")
    @Override
    public Object visitBpmnDataInput(BpmnDataInput obj) {
        return visitBpmnItemAwareElement(obj);
        
    }

    @objid ("93633c91-2b9c-41d2-923d-a963266ac5dc")
    @Override
    public Object visitBpmnDataObject(BpmnDataObject obj) {
        return visitBpmnItemAwareElement(obj);
        
    }

    @objid ("94433cc2-9cea-4502-8be6-12013e363228")
    @Override
    public Object visitBpmnDataOutput(BpmnDataOutput obj) {
        return visitBpmnItemAwareElement(obj);
        
    }

    @objid ("27231615-df92-4fff-95e9-03604e3a8e2d")
    @Override
    public Object visitBpmnDataState(BpmnDataState obj) {
        return visitBpmnBaseElement(obj);
        
    }

    @objid ("1c2f5f8b-0b2b-44ac-b7cd-8fe471a64988")
    @Override
    public Object visitBpmnDataStore(BpmnDataStore obj) {
        return visitBpmnItemAwareElement(obj);
        
    }

    @objid ("b5523895-422e-42d1-8ba2-6e2473511b41")
    @Override
    public Object visitBpmnEndEvent(BpmnEndEvent obj) {
        return visitBpmnThrowEvent(obj);
        
    }

    @objid ("3497b525-b8f8-4e64-8137-305d0bad4874")
    @Override
    public Object visitBpmnEndPoint(BpmnEndPoint obj) {
        return visitBpmnSharedElement(obj);
        
    }

    @objid ("0cfb8ffe-b2ae-4f4a-be54-5ab9de064256")
    @Override
    public Object visitBpmnErrorEventDefinition(BpmnErrorEventDefinition obj) {
        return visitBpmnEventDefinition(obj);
        
    }

    @objid ("bf7ad10c-d05f-4d65-b8f7-2cf2f564e5f3")
    @Override
    public Object visitBpmnEscalationEventDefinition(BpmnEscalationEventDefinition obj) {
        return visitBpmnEventDefinition(obj);
        
    }

    @objid ("efcc3e19-a3b2-452a-b9a3-20adb229e63a")
    @Override
    public Object visitBpmnEvent(BpmnEvent obj) {
        return visitBpmnFlowNode(obj);
        
    }

    @objid ("d8c03fa7-dec4-4d22-b4d2-365a138a1bf4")
    @Override
    public Object visitBpmnEventBasedGateway(BpmnEventBasedGateway obj) {
        return visitBpmnGateway(obj);
        
    }

    @objid ("3ec55893-7dda-42f7-8527-e93f9506e304")
    @Override
    public Object visitBpmnEventDefinition(BpmnEventDefinition obj) {
        return visitBpmnBaseElement(obj);
        
    }

    @objid ("c5c5b6c2-22eb-4414-bfbe-94168d870ab0")
    @Override
    public Object visitBpmnExclusiveGateway(BpmnExclusiveGateway obj) {
        return visitBpmnGateway(obj);
        
    }

    @objid ("2fdb8194-4bbc-4cad-bc13-08de1e5c74c3")
    @Override
    public Object visitBpmnFlowElement(BpmnFlowElement obj) {
        return visitBpmnBaseElement(obj);
        
    }

    @objid ("d4f7e601-414d-4fdf-9515-3666c1249369")
    @Override
    public Object visitBpmnFlowNode(BpmnFlowNode obj) {
        return visitBpmnFlowElement(obj);
        
    }

    @objid ("eb8c1cb3-924a-4bde-be40-b9c8f88adc38")
    @Override
    public Object visitBpmnGateway(BpmnGateway obj) {
        return visitBpmnFlowNode(obj);
        
    }

    @objid ("09769139-d06d-4053-80aa-51d0b5f0c064")
    @Override
    public Object visitBpmnGroup(BpmnGroup obj) {
        return visitBpmnArtifact(obj);
        
    }

    @objid ("55187df6-1ab9-4ddc-bd97-9c4941dcdd9e")
    @Override
    public Object visitBpmnImplicitThrowEvent(BpmnImplicitThrowEvent obj) {
        return visitBpmnThrowEvent(obj);
        
    }

    @objid ("d570a45a-7322-472d-a10a-804309eb2baa")
    @Override
    public Object visitBpmnInclusiveGateway(BpmnInclusiveGateway obj) {
        return visitBpmnGateway(obj);
        
    }

    @objid ("c08bb893-7ece-4a99-8597-57af4ec30871")
    @Override
    public Object visitBpmnInterface(BpmnInterface obj) {
        return visitBpmnSharedElement(obj);
        
    }

    @objid ("2ab824fd-5c71-4010-986c-c21ce05aa26a")
    @Override
    public Object visitBpmnIntermediateCatchEvent(BpmnIntermediateCatchEvent obj) {
        return visitBpmnCatchEvent(obj);
        
    }

    @objid ("24287da1-672a-4255-93c3-b76bdf613281")
    @Override
    public Object visitBpmnIntermediateThrowEvent(BpmnIntermediateThrowEvent obj) {
        return visitBpmnThrowEvent(obj);
        
    }

    @objid ("132c4596-49cc-420e-8579-45b7d6649614")
    @Override
    public Object visitBpmnItemAwareElement(BpmnItemAwareElement obj) {
        return visitBpmnFlowElement(obj);
        
    }

    @objid ("21ea4a79-14cd-4366-a706-c2c0ad0b5d9f")
    @Override
    public Object visitBpmnItemDefinition(BpmnItemDefinition obj) {
        return visitBpmnSharedElement(obj);
        
    }

    @objid ("bd9adb16-ca9b-4d3f-8217-7f46a40a5219")
    @Override
    public Object visitBpmnLane(BpmnLane obj) {
        return visitBpmnBaseElement(obj);
        
    }

    @objid ("429c204c-5ef5-458b-878c-187b9384d8a9")
    @Override
    public Object visitBpmnLaneSet(BpmnLaneSet obj) {
        return visitBpmnBaseElement(obj);
        
    }

    @objid ("adf9d6f9-b42d-430a-9590-74345c5bdc59")
    @Override
    public Object visitBpmnLinkEventDefinition(BpmnLinkEventDefinition obj) {
        return visitBpmnEventDefinition(obj);
        
    }

    @objid ("fb72da7e-c600-4c93-b3ed-32f6ffbde3cb")
    @Override
    public Object visitBpmnLoopCharacteristics(BpmnLoopCharacteristics obj) {
        return visitBpmnBaseElement(obj);
        
    }

    @objid ("e66c46ea-f986-4475-b1df-1dad11366b1a")
    @Override
    public Object visitBpmnManualTask(BpmnManualTask obj) {
        return visitBpmnTask(obj);
        
    }

    @objid ("23178158-e743-4485-a31c-30c048cae4fe")
    @Override
    public Object visitBpmnMessage(BpmnMessage obj) {
        return visitBpmnSharedElement(obj);
        
    }

    @objid ("bf0160c5-03dd-4fe5-8f08-3c956014a9e5")
    @Override
    public Object visitBpmnMessageEventDefinition(BpmnMessageEventDefinition obj) {
        return visitBpmnEventDefinition(obj);
        
    }

    @objid ("3dbb4a0d-5e38-4010-9c57-223592a85c8d")
    @Override
    public Object visitBpmnMessageFlow(BpmnMessageFlow obj) {
        return visitBpmnBaseElement(obj);
        
    }

    @objid ("5b72b05a-aa7a-4e6e-9dfc-19e1c5c532e8")
    @Override
    public Object visitBpmnMultiInstanceLoopCharacteristics(BpmnMultiInstanceLoopCharacteristics obj) {
        return visitBpmnLoopCharacteristics(obj);
        
    }

    @objid ("1c98842d-6cf8-4883-b37b-a85aff6c7b61")
    @Override
    public Object visitBpmnOperation(BpmnOperation obj) {
        return visitBpmnBaseElement(obj);
        
    }

    @objid ("8eca5395-a478-48d7-9861-a521f5e732a0")
    @Override
    public Object visitBpmnParallelGateway(BpmnParallelGateway obj) {
        return visitBpmnGateway(obj);
        
    }

    @objid ("11f004b9-51ce-4470-b61b-fe3b7d66263b")
    @Override
    public Object visitBpmnParticipant(BpmnParticipant obj) {
        return visitBpmnBaseElement(obj);
        
    }

    @objid ("6f1e5873-71df-47f0-8768-59cdf0326a65")
    @Override
    public Object visitBpmnProcess(BpmnProcess obj) {
        return visitBehavior(obj);
        
    }

    @objid ("9f405937-f255-4d6e-b335-25dfd50f24d4")
    @Override
    public Object visitBpmnProcessCollaborationDiagram(BpmnProcessCollaborationDiagram obj) {
        return visitBehaviorDiagram(obj);
        
    }

    @objid ("e900960a-257c-4ac9-bbcb-5b9f00f99900")
    @Override
    public Object visitBpmnProcessDesignDiagram(BpmnProcessDesignDiagram obj) {
        return visitBpmnProcessCollaborationDiagram(obj);
        
    }

    @objid ("4c5bdf79-a3bd-44ff-8288-0e4193422b73")
    @Override
    public Object visitBpmnReceiveTask(BpmnReceiveTask obj) {
        return visitBpmnTask(obj);
        
    }

    @objid ("94e34031-0195-4e88-920b-483464580048")
    @Override
    public Object visitBpmnResource(BpmnResource obj) {
        return visitBpmnSharedElement(obj);
        
    }

    @objid ("9a6c8b55-5a00-4089-bddb-a237c00ae81e")
    @Override
    public Object visitBpmnResourceParameter(BpmnResourceParameter obj) {
        return visitBpmnBaseElement(obj);
        
    }

    @objid ("168858d2-edbb-437c-8751-03341babd506")
    @Override
    public Object visitBpmnResourceParameterBinding(BpmnResourceParameterBinding obj) {
        return visitBpmnBaseElement(obj);
        
    }

    @objid ("58f063b0-ff64-4906-b75c-62dc1e5ff282")
    @Override
    public Object visitBpmnResourceRole(BpmnResourceRole obj) {
        return visitBpmnBaseElement(obj);
        
    }

    @objid ("76e7bb21-8782-4745-94ee-270e33170405")
    @Override
    public Object visitBpmnScriptTask(BpmnScriptTask obj) {
        return visitBpmnTask(obj);
        
    }

    @objid ("0ec13695-96bb-4e4f-a868-7a035ffbcfe3")
    @Override
    public Object visitBpmnSendTask(BpmnSendTask obj) {
        return visitBpmnTask(obj);
        
    }

    @objid ("6db8ea57-31ff-4975-9380-68cf80a1468e")
    @Override
    public Object visitBpmnSequenceFlow(BpmnSequenceFlow obj) {
        return visitBpmnFlowElement(obj);
        
    }

    @objid ("58f81ca6-0a14-4c7f-9b22-74146cb0fd5b")
    @Override
    public Object visitBpmnSequenceFlowDataAssociation(BpmnSequenceFlowDataAssociation obj) {
        return visitBpmnBaseElement(obj);
        
    }

    @objid ("9f52d9a3-f628-44eb-a5b1-1f716d0464da")
    @Override
    public Object visitBpmnServiceTask(BpmnServiceTask obj) {
        return visitBpmnTask(obj);
        
    }

    @objid ("850b5f05-2105-4eb3-968f-575834f3e24e")
    @Override
    public Object visitBpmnSharedDefinitions(BpmnSharedDefinitions obj) {
        return visitBehavior(obj);
        
    }

    @objid ("9f5dff91-00ef-4dd8-ae66-578309d1e85d")
    @Override
    public Object visitBpmnSharedElement(BpmnSharedElement obj) {
        return visitBpmnBaseElement(obj);
        
    }

    @objid ("90bcee23-791d-4635-9d38-118b9cebad89")
    @Override
    public Object visitBpmnSignalEventDefinition(BpmnSignalEventDefinition obj) {
        return visitBpmnEventDefinition(obj);
        
    }

    @objid ("9ed7f41e-d5a7-4c0e-863b-2b0388597806")
    @Override
    public Object visitBpmnStandardLoopCharacteristics(BpmnStandardLoopCharacteristics obj) {
        return visitBpmnLoopCharacteristics(obj);
        
    }

    @objid ("829b0751-a1c6-454e-afd8-978fb9bb2663")
    @Override
    public Object visitBpmnStartEvent(BpmnStartEvent obj) {
        return visitBpmnCatchEvent(obj);
        
    }

    @objid ("55b11d05-5b70-459f-a7c5-33535b9e1262")
    @Override
    public Object visitBpmnSubProcess(BpmnSubProcess obj) {
        return visitBpmnActivity(obj);
        
    }

    @objid ("12aa3a84-9ebf-4d4b-8b6c-0a31e3e27434")
    @Override
    public Object visitBpmnSubProcessDiagram(BpmnSubProcessDiagram obj) {
        return visitBehaviorDiagram(obj);
        
    }

    @objid ("7acead3c-ab5d-4b4f-9403-e7f8163ed6c2")
    @Override
    public Object visitBpmnTask(BpmnTask obj) {
        return visitBpmnActivity(obj);
        
    }

    @objid ("05a59088-24e4-4273-a0de-03252afc55c4")
    @Override
    public Object visitBpmnTerminateEventDefinition(BpmnTerminateEventDefinition obj) {
        return visitBpmnEventDefinition(obj);
        
    }

    @objid ("09f5155b-8fed-4bb8-b357-de9c0a024671")
    @Override
    public Object visitBpmnThrowEvent(BpmnThrowEvent obj) {
        return visitBpmnEvent(obj);
        
    }

    @objid ("24f3fd29-7d4a-46a9-9ef6-6ec4c949ef4e")
    @Override
    public Object visitBpmnTimerEventDefinition(BpmnTimerEventDefinition obj) {
        return visitBpmnEventDefinition(obj);
        
    }

    @objid ("87924593-9264-4b68-84a4-496569cd79c1")
    @Override
    public Object visitBpmnTransaction(BpmnTransaction obj) {
        return visitBpmnSubProcess(obj);
        
    }

    @objid ("6bc2a6a5-fc76-4e45-bf21-73ff8916bd29")
    @Override
    public Object visitBpmnUserTask(BpmnUserTask obj) {
        return visitBpmnTask(obj);
        
    }

    @objid ("7a677c8b-37f5-4144-af5f-76b6bacb3c51")
    @Override
    public Object visitCallAction(CallAction obj) {
        return visitActivityAction(obj);
        
    }

    @objid ("8f0911fd-8a95-446c-9db3-e5a5573ee185")
    @Override
    public Object visitCallBehaviorAction(CallBehaviorAction obj) {
        return visitCallAction(obj);
        
    }

    @objid ("52799a2e-987c-48fc-853e-c2061c5ac558")
    @Override
    public Object visitCallOperationAction(CallOperationAction obj) {
        return visitCallAction(obj);
        
    }

    @objid ("a637ae74-b0d3-47aa-a3b4-96330bc1aec6")
    @Override
    public Object visitCentralBufferNode(CentralBufferNode obj) {
        return visitObjectNode(obj);
        
    }

    @objid ("5c794c2a-6a87-4be5-badd-fea0cec7d853")
    @Override
    public Object visitChoicePseudoState(ChoicePseudoState obj) {
        return visitAbstractPseudoState(obj);
        
    }

    @objid ("7bd9e6cd-f248-4dca-90d1-c3f9663c0397")
    @Override
    public Object visitClass(Class obj) {
        return visitGeneralClass(obj);
        
    }

    @objid ("e46fafa5-9511-49d7-bff9-6f35168f2b8a")
    @Override
    public Object visitClassAssociation(ClassAssociation obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("558e64b5-8c25-4776-959b-31ff5255b8f3")
    @Override
    public Object visitClassDiagram(ClassDiagram obj) {
        return visitStaticDiagram(obj);
        
    }

    @objid ("e2571a38-1072-41ad-b42c-5a1bbdaced61")
    @Override
    public Object visitClassifier(Classifier obj) {
        return visitNameSpace(obj);
        
    }

    @objid ("5dbfa207-608c-445f-bf89-8be92fd6662e")
    @Override
    public Object visitClause(Clause obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("308083c8-188d-4ce2-9539-cb179d437eb1")
    @Override
    public Object visitCollaboration(Collaboration obj) {
        return visitNameSpace(obj);
        
    }

    @objid ("1716e611-a8f5-4275-b0c8-29d6e3ab8aa7")
    @Override
    public Object visitCollaborationUse(CollaborationUse obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("4a819be4-2ccd-4aa0-a590-060fd0eb004f")
    @Override
    public Object visitCombinedFragment(CombinedFragment obj) {
        return visitInteractionFragment(obj);
        
    }

    @objid ("d8efff4a-ea6d-442c-a2d2-ab534c6cb956")
    @Override
    public Object visitCommunicationChannel(CommunicationChannel obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("a30c7620-8fec-4265-a652-306fe96374e6")
    @Override
    public Object visitCommunicationDiagram(CommunicationDiagram obj) {
        return visitBehaviorDiagram(obj);
        
    }

    @objid ("164a331c-1158-4bfc-a01c-2a9ca1ac2103")
    @Override
    public Object visitCommunicationInteraction(CommunicationInteraction obj) {
        return visitBehavior(obj);
        
    }

    @objid ("8a11b85c-020d-4e89-99af-f6e034543237")
    @Override
    public Object visitCommunicationMessage(CommunicationMessage obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("1eee0c45-37c4-42e6-ada1-70a3d0798c65")
    @Override
    public Object visitCommunicationNode(CommunicationNode obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("b5c5ea32-1ff8-467c-8fc8-d77fe358437e")
    @Override
    public Object visitComponent(Component obj) {
        return visitClass(obj);
        
    }

    @objid ("bdd2ca61-649c-42e2-b49e-86f018940c64")
    @Override
    public Object visitComponentRealization(ComponentRealization obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("904d0720-84ed-4974-9f48-e9f03f2c3dd6")
    @Override
    public Object visitCompositeStructureDiagram(CompositeStructureDiagram obj) {
        return visitStaticDiagram(obj);
        
    }

    @objid ("2b706275-47bb-49f2-a3d3-43d084aa4842")
    @Override
    public Object visitConditionalNode(ConditionalNode obj) {
        return visitStructuredActivityNode(obj);
        
    }

    @objid ("dc885143-1695-4a87-a816-7087defece73")
    @Override
    public Object visitConnectionPointReference(ConnectionPointReference obj) {
        return visitStateVertex(obj);
        
    }

    @objid ("078e5016-f6e9-4100-bca0-82926b3a6959")
    @Override
    public Object visitConnector(Connector obj) {
        return visitLink(obj);
        
    }

    @objid ("903915c2-caeb-4d4a-a313-e2798a3414f8")
    @Override
    public Object visitConnectorEnd(ConnectorEnd obj) {
        return visitLinkEnd(obj);
        
    }

    @objid ("69e7eba0-3bd2-4397-840b-ae0acd4c49cb")
    @Override
    public Object visitConstraint(Constraint obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("67180f79-03b8-4071-93c8-fc321498a23b")
    @Override
    public Object visitControlFlow(ControlFlow obj) {
        return visitActivityEdge(obj);
        
    }

    @objid ("b8474824-cc9b-45dc-93d1-566028c28bc2")
    @Override
    public Object visitControlNode(ControlNode obj) {
        return visitActivityNode(obj);
        
    }

    @objid ("575b047d-bf2c-4e4d-807b-a4bac50d06f9")
    @Override
    public Object visitDataFlow(DataFlow obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("698c0ccf-b05f-403e-b1e7-59d9bbfa2983")
    @Override
    public Object visitDataStoreNode(DataStoreNode obj) {
        return visitCentralBufferNode(obj);
        
    }

    @objid ("be83a63e-e6df-42a4-b2c8-ff986371a7ca")
    @Override
    public Object visitDataType(DataType obj) {
        return visitGeneralClass(obj);
        
    }

    @objid ("70fd9cf7-17a6-4e50-9ebb-fc0b4949c962")
    @Override
    public Object visitDecisionMergeNode(DecisionMergeNode obj) {
        return visitControlNode(obj);
        
    }

    @objid ("267552c9-da75-4b81-aad9-8b88c6b78a89")
    @Override
    public Object visitDeepHistoryPseudoState(DeepHistoryPseudoState obj) {
        return visitAbstractPseudoState(obj);
        
    }

    @objid ("0f32bbb3-72cf-46e2-aaf4-d826c54c1a99")
    @Override
    public Object visitDeploymentDiagram(DeploymentDiagram obj) {
        return visitStaticDiagram(obj);
        
    }

    @objid ("3f2524b5-7990-4adb-b230-f5a54c365bc7")
    @Override
    public Object visitDurationConstraint(DurationConstraint obj) {
        return visitConstraint(obj);
        
    }

    @objid ("70e2c35c-4883-43b4-ac6c-3feaafabec65")
    @Override
    public Object visitElementImport(ElementImport obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("82c38273-4e53-4bc8-bb80-b6e3912eba0f")
    @Override
    public Object visitElementRealization(ElementRealization obj) {
        return visitAbstraction(obj);
        
    }

    @objid ("385f4350-e8e4-4efb-808a-00de505dc867")
    @Override
    public Object visitEntryPointPseudoState(EntryPointPseudoState obj) {
        return visitAbstractPseudoState(obj);
        
    }

    @objid ("9252cd0b-d6e1-47a7-8c5a-95cb51fd2b2d")
    @Override
    public Object visitEnumeration(Enumeration obj) {
        return visitGeneralClass(obj);
        
    }

    @objid ("547fe7fb-6e9d-4a23-a9a4-5f47b1c192b2")
    @Override
    public Object visitEnumerationLiteral(EnumerationLiteral obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("e503d12d-48b1-4e9e-85b1-e3232695f55e")
    @Override
    public Object visitEvent(Event obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("7db82561-e580-4818-8af0-0ba58f99a6a4")
    @Override
    public Object visitExceptionHandler(ExceptionHandler obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("9f5cac5e-8d18-4675-9fcd-3e38efc99afe")
    @Override
    public Object visitExecutionOccurenceSpecification(ExecutionOccurenceSpecification obj) {
        return visitMessageEnd(obj);
        
    }

    @objid ("4e20cb63-9c2e-4bce-8fdb-4df9e20d07a0")
    @Override
    public Object visitExecutionSpecification(ExecutionSpecification obj) {
        return visitInteractionFragment(obj);
        
    }

    @objid ("bb78c5de-e820-4cbf-8200-facd8a6da72f")
    @Override
    public Object visitExitPointPseudoState(ExitPointPseudoState obj) {
        return visitAbstractPseudoState(obj);
        
    }

    @objid ("cd5ea62a-18ab-4569-9fb8-93e3cad80912")
    @Override
    public Object visitExpansionNode(ExpansionNode obj) {
        return visitObjectNode(obj);
        
    }

    @objid ("7873a035-3d57-485c-a464-d68b1e1a0eed")
    @Override
    public Object visitExpansionRegion(ExpansionRegion obj) {
        return visitStructuredActivityNode(obj);
        
    }

    @objid ("db8e8cec-9ff5-41e6-973c-9325fd71bd39")
    @Override
    public Object visitExtensionPoint(ExtensionPoint obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("af28b40c-e898-4423-b5ca-1ed72545a554")
    @Override
    public Object visitFeature(Feature obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("9bc74182-7cbd-4cb1-88e0-261d2ea4efb5")
    @Override
    public Object visitFinalNode(FinalNode obj) {
        return visitControlNode(obj);
        
    }

    @objid ("5771c14b-c80a-4a5c-b01d-04c2d333bc38")
    @Override
    public Object visitFinalState(FinalState obj) {
        return visitState(obj);
        
    }

    @objid ("2ce028be-0d27-4be0-a58c-71045e534354")
    @Override
    public Object visitFlowFinalNode(FlowFinalNode obj) {
        return visitFinalNode(obj);
        
    }

    @objid ("e60b49d7-8b0f-46d7-9a69-878884304de4")
    @Override
    public Object visitForkJoinNode(ForkJoinNode obj) {
        return visitControlNode(obj);
        
    }

    @objid ("a788da07-2441-4223-b5e9-d3f763845e17")
    @Override
    public Object visitForkPseudoState(ForkPseudoState obj) {
        return visitAbstractPseudoState(obj);
        
    }

    @objid ("519fe0f9-9d57-4bbf-9158-67e8c8235d99")
    @Override
    public Object visitGate(Gate obj) {
        return visitMessageEnd(obj);
        
    }

    @objid ("0575ffee-4c46-49ba-85a4-c26aa1c4d6db")
    @Override
    public Object visitGeneralClass(GeneralClass obj) {
        return visitClassifier(obj);
        
    }

    @objid ("df640a9c-ab38-4cae-8006-31e3b439a10d")
    @Override
    public Object visitGeneralOrdering(GeneralOrdering obj) {
        return this.infrastructureVisitor != null ? this.infrastructureVisitor.visitElement(obj) : null;
        
    }

    @objid ("daea2a4b-3cd6-4c9b-8cd1-7d0bea9b65ef")
    @Override
    public Object visitGeneralization(Generalization obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("c7452bb6-4052-4a6b-996b-d984f3213588")
    @Override
    public Object visitInformationFlow(InformationFlow obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("b5012515-40dc-43e7-bb91-e71a89d71b1b")
    @Override
    public Object visitInformationItem(InformationItem obj) {
        return visitClassifier(obj);
        
    }

    @objid ("8effc129-54a6-436e-a596-4903925ab661")
    @Override
    public Object visitInitialNode(InitialNode obj) {
        return visitControlNode(obj);
        
    }

    @objid ("3b83ccde-bbab-4d34-a6ff-a7c43f2421c4")
    @Override
    public Object visitInitialPseudoState(InitialPseudoState obj) {
        return visitAbstractPseudoState(obj);
        
    }

    @objid ("5f408a25-7e0e-4d41-8cec-b6c16208108e")
    @Override
    public Object visitInputPin(InputPin obj) {
        return visitPin(obj);
        
    }

    @objid ("8e297aca-94c3-4a78-aad0-4a136f1b58da")
    @Override
    public Object visitInstance(Instance obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("72e6b5cd-ebf4-4fa1-b6e0-4c7253f6ab19")
    @Override
    public Object visitInstanceNode(InstanceNode obj) {
        return visitObjectNode(obj);
        
    }

    @objid ("b7e6542a-896f-408d-9a4c-f8fdbc59fc89")
    @Override
    public Object visitInteraction(Interaction obj) {
        return visitBehavior(obj);
        
    }

    @objid ("d707f422-48a5-40cd-bf40-9cefb5027d1a")
    @Override
    public Object visitInteractionFragment(InteractionFragment obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("e9bd5459-4dc3-45e5-a961-619e3902bacc")
    @Override
    public Object visitInteractionOperand(InteractionOperand obj) {
        return visitInteractionFragment(obj);
        
    }

    @objid ("3a842d2d-f849-46ca-b7d6-3b3d384b2a7f")
    @Override
    public Object visitInteractionUse(InteractionUse obj) {
        return visitInteractionFragment(obj);
        
    }

    @objid ("d48e4716-0d21-4ae0-b0b1-5d22acacaf27")
    @Override
    public Object visitInterface(Interface obj) {
        return visitGeneralClass(obj);
        
    }

    @objid ("3bc68003-da91-4392-a720-1a3a0a730776")
    @Override
    public Object visitInterfaceRealization(InterfaceRealization obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("44e26c70-039b-406a-a8cb-fa8eba10af6d")
    @Override
    public Object visitInternalTransition(InternalTransition obj) {
        return visitTransition(obj);
        
    }

    @objid ("e5f56331-85a0-4fab-8932-cc1379f64039")
    @Override
    public Object visitInterruptibleActivityRegion(InterruptibleActivityRegion obj) {
        return visitActivityGroup(obj);
        
    }

    @objid ("fb39dcbc-9311-4eff-a913-0369bdb05cab")
    @Override
    public Object visitJoinPseudoState(JoinPseudoState obj) {
        return visitAbstractPseudoState(obj);
        
    }

    @objid ("d153a47f-f18f-4235-94b7-0aad99fc1ba0")
    @Override
    public Object visitJunctionPseudoState(JunctionPseudoState obj) {
        return visitAbstractPseudoState(obj);
        
    }

    @objid ("d2de172b-6753-48f5-89d8-f8fb72d7476e")
    @Override
    public Object visitLifeline(Lifeline obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("64a78e66-6dc8-4508-8968-3c7ae40c99e6")
    @Override
    public Object visitLink(Link obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("56868d1e-4030-4d22-81e9-7fcf8d0cd72a")
    @Override
    public Object visitLinkEnd(LinkEnd obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("d96affad-7812-4e79-9a27-016913c82496")
    @Override
    public Object visitLoopNode(LoopNode obj) {
        return visitStructuredActivityNode(obj);
        
    }

    @objid ("d9efc2cd-2678-4cbc-acfc-7edacc41e0e6")
    @Override
    public Object visitManifestation(Manifestation obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("07d59f6d-cd34-4700-972a-b37f9becd2d7")
    @Override
    public Object visitMessage(Message obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("96335192-af07-4634-bfac-83bacd4af45b")
    @Override
    public Object visitMessageEnd(MessageEnd obj) {
        return visitOccurrenceSpecification(obj);
        
    }

    @objid ("5d3fb4c3-cff1-4be3-bcc5-a9cb38c1875c")
    @Override
    public Object visitMessageFlow(MessageFlow obj) {
        return visitActivityEdge(obj);
        
    }

    @objid ("3cf19de5-913c-4085-a9c0-867004ae1da8")
    @Override
    public Object visitModelTree(ModelTree obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("cb183c99-1fbf-42d1-a12a-c80fecd9b208")
    @Override
    public Object visitNameSpace(NameSpace obj) {
        return visitModelTree(obj);
        
    }

    @objid ("d88c279f-bdaf-4b5a-84b1-41f01cde7ee4")
    @Override
    public Object visitNaryAssociation(NaryAssociation obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("30741677-9fe8-4408-8a4a-2e7483e46483")
    @Override
    public Object visitNaryAssociationEnd(NaryAssociationEnd obj) {
        return visitStructuralFeature(obj);
        
    }

    @objid ("b60c7e7c-ed23-4c34-8dd9-99750ce77244")
    @Override
    public Object visitNaryConnector(NaryConnector obj) {
        return visitNaryLink(obj);
        
    }

    @objid ("ffb563a5-70b0-49c0-9c2b-ed15367ba949")
    @Override
    public Object visitNaryConnectorEnd(NaryConnectorEnd obj) {
        return visitNaryLinkEnd(obj);
        
    }

    @objid ("eb411c30-a406-4e84-a70d-e46a6b2c0935")
    @Override
    public Object visitNaryLink(NaryLink obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("4a682e79-3407-4971-a91b-3ffaeb80169f")
    @Override
    public Object visitNaryLinkEnd(NaryLinkEnd obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("8fa89cb1-0113-4e14-8379-41cd40c5e00a")
    @Override
    public Object visitNode(Node obj) {
        return visitClassifier(obj);
        
    }

    @objid ("5c1e09f6-9462-4c2f-b844-f2bd3f7870d1")
    @Override
    public Object visitObjectDiagram(ObjectDiagram obj) {
        return visitStaticDiagram(obj);
        
    }

    @objid ("e1ec0081-dad6-421d-a757-87de0da2299c")
    @Override
    public Object visitObjectFlow(ObjectFlow obj) {
        return visitActivityEdge(obj);
        
    }

    @objid ("1325f925-545b-4a40-802e-ccce96e18c5e")
    @Override
    public Object visitObjectNode(ObjectNode obj) {
        return visitActivityNode(obj);
        
    }

    @objid ("e494e55a-03b2-453f-8506-8e3dad199e7d")
    @Override
    public Object visitOccurrenceSpecification(OccurrenceSpecification obj) {
        return visitInteractionFragment(obj);
        
    }

    @objid ("0951bdce-0542-4aad-a934-0353df18810b")
    @Override
    public Object visitOpaqueAction(OpaqueAction obj) {
        return visitActivityAction(obj);
        
    }

    @objid ("1149e215-bf1b-424c-872b-e40e7db5c0fd")
    @Override
    public Object visitOpaqueBehavior(OpaqueBehavior obj) {
        return visitBehavior(obj);
        
    }

    @objid ("a197dbf0-90d0-45bd-b43c-6b0dd4792ff4")
    @Override
    public Object visitOperation(Operation obj) {
        return visitBehavioralFeature(obj);
        
    }

    @objid ("e7bb11ea-c03d-4459-8aae-e43809f8b9b9")
    @Override
    public Object visitOutputPin(OutputPin obj) {
        return visitPin(obj);
        
    }

    @objid ("81040a3e-2cb2-4f24-9b35-2ccc2a5ddb07")
    @Override
    public Object visitPackage(Package obj) {
        return visitNameSpace(obj);
        
    }

    @objid ("49aa48b8-0961-40c0-b4e2-8a2e3c4199ef")
    @Override
    public Object visitPackageImport(PackageImport obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("9eeea506-8c6e-4d84-8bf2-f1d888d30cdf")
    @Override
    public Object visitPackageMerge(PackageMerge obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("df67f89e-d26c-4e2d-ae35-d04a083e4a54")
    @Override
    public Object visitParameter(Parameter obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("f4e00336-3b6f-4e85-a723-8f407026f3c3")
    @Override
    public Object visitPartDecomposition(PartDecomposition obj) {
        return visitInteractionUse(obj);
        
    }

    @objid ("9b1071ed-f857-4edb-acfe-5c79f8218c69")
    @Override
    public Object visitPin(Pin obj) {
        return visitObjectNode(obj);
        
    }

    @objid ("735af299-0102-476a-9c20-78a4ee800312")
    @Override
    public Object visitPort(Port obj) {
        return visitBindableInstance(obj);
        
    }

    @objid ("2c897808-aeb6-488b-a7b2-9ba587f7437d")
    @Override
    public Object visitProject(Project obj) {
        return this.infrastructureVisitor != null ? this.infrastructureVisitor.visitAbstractProject(obj) : null;
        
    }

    @objid ("82a65707-0ac2-490b-957d-bd376b808a25")
    @Override
    public Object visitProvidedInterface(ProvidedInterface obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("8cc7d178-8ec3-4740-b217-1685ef6e99dd")
    @Override
    public Object visitRaisedException(RaisedException obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("4788aece-10ea-488e-8473-ecef195b62fb")
    @Override
    public Object visitRegion(Region obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("c35ff2f5-60fe-4084-912c-330f32fa06d9")
    @Override
    public Object visitRequiredInterface(RequiredInterface obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("6e50b0de-122e-450b-b1f7-99ffb2ce07ac")
    @Override
    public Object visitSendSignalAction(SendSignalAction obj) {
        return visitActivityAction(obj);
        
    }

    @objid ("71890cf0-5103-41fb-a901-019e1a851fd9")
    @Override
    public Object visitSequenceDiagram(SequenceDiagram obj) {
        return visitBehaviorDiagram(obj);
        
    }

    @objid ("3c07fe68-f127-40f4-b78b-663c892252ee")
    @Override
    public Object visitShallowHistoryPseudoState(ShallowHistoryPseudoState obj) {
        return visitAbstractPseudoState(obj);
        
    }

    @objid ("9c02767e-716d-4169-9f4a-bda5b1783693")
    @Override
    public Object visitSignal(Signal obj) {
        return visitGeneralClass(obj);
        
    }

    @objid ("f950fc04-9703-4360-81f0-169dd8b90c01")
    @Override
    public Object visitState(State obj) {
        return visitStateVertex(obj);
        
    }

    @objid ("f9b0d87f-a541-4890-9c6f-f3b193dcc167")
    @Override
    public Object visitStateInvariant(StateInvariant obj) {
        return visitOccurrenceSpecification(obj);
        
    }

    @objid ("3f12e9ef-a4ec-462e-8eec-de6d5a02f2ca")
    @Override
    public Object visitStateMachine(StateMachine obj) {
        return visitBehavior(obj);
        
    }

    @objid ("64177390-89ec-47f5-9dd5-9e9cb0592290")
    @Override
    public Object visitStateMachineDiagram(StateMachineDiagram obj) {
        return visitBehaviorDiagram(obj);
        
    }

    @objid ("1ab78492-0824-4055-a5c9-18fe5362f283")
    @Override
    public Object visitStateVertex(StateVertex obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("0d3e5989-d72f-4e55-ba59-8b5498db9846")
    @Override
    public Object visitStaticDiagram(StaticDiagram obj) {
        return this.infrastructureVisitor != null ? this.infrastructureVisitor.visitAbstractDiagram(obj) : null;
        
    }

    @objid ("05c35f07-6d22-4efa-9a5b-b07292185f24")
    @Override
    public Object visitStructuralFeature(StructuralFeature obj) {
        return visitFeature(obj);
        
    }

    @objid ("5a523eaa-0a81-413f-9331-e27f1943bf6a")
    @Override
    public Object visitStructuredActivityNode(StructuredActivityNode obj) {
        return visitActivityAction(obj);
        
    }

    @objid ("66a506c6-9c09-47b8-b5c3-f246cc073318")
    @Override
    public Object visitSubstitution(Substitution obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("285af831-067f-4e42-af8b-2d991ae4be81")
    @Override
    public Object visitTemplateBinding(TemplateBinding obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("059b1d91-ba50-44a0-b248-7b612be27baf")
    @Override
    public Object visitTemplateParameter(TemplateParameter obj) {
        return visitGeneralClass(obj);
        
    }

    @objid ("add3db2b-af92-473b-9703-41ce57b5266e")
    @Override
    public Object visitTemplateParameterSubstitution(TemplateParameterSubstitution obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("2731af63-17f2-4027-b6ca-db90700973fa")
    @Override
    public Object visitTerminatePseudoState(TerminatePseudoState obj) {
        return visitAbstractPseudoState(obj);
        
    }

    @objid ("16565946-be81-48fb-a984-4e8bfa0657cd")
    @Override
    public Object visitTerminateSpecification(TerminateSpecification obj) {
        return visitExecutionOccurenceSpecification(obj);
        
    }

    @objid ("0da16e17-6757-4be8-bd99-ec6383834a74")
    @Override
    public Object visitTransition(Transition obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("263abb08-f16a-4c12-97f7-e429e29f2ace")
    @Override
    public Object visitUmlModelElement(UmlModelElement obj) {
        return this.infrastructureVisitor != null ? this.infrastructureVisitor.visitModelElement(obj) : null;
        
    }

    @objid ("bdf3654a-a94f-4787-bcc5-9ea28eba0804")
    @Override
    public Object visitUsage(Usage obj) {
        return this.infrastructureVisitor != null ? this.infrastructureVisitor.visitDependency(obj) : null;
        
    }

    @objid ("9a62d2d8-c81f-4cf7-aa43-46d51484c4d2")
    @Override
    public Object visitUseCase(UseCase obj) {
        return visitGeneralClass(obj);
        
    }

    @objid ("caa183f8-b54e-4d2d-ab97-121c42b570bf")
    @Override
    public Object visitUseCaseDependency(UseCaseDependency obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("e695cd8b-64f6-4892-b807-97e6aa2d21c1")
    @Override
    public Object visitUseCaseDiagram(UseCaseDiagram obj) {
        return visitStaticDiagram(obj);
        
    }

    @objid ("d34667e7-089a-4c0f-b98a-81403be5d51f")
    @Override
    public Object visitValuePin(ValuePin obj) {
        return visitInputPin(obj);
        
    }

}
