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
 * This interface is an implementation of {@link IModelVisitor} whose default strategy consists in transmitting the visit() call to the super class visit method.
 * <p>On root metaclasses, the visitor tries to delegate the parent metaclass metamodel visitor if available.If not available, <code>null</code> is returned.
 * 
 */
@objid ("f3891086-541e-4b45-aa3f-564ccb1cb308")
public interface IDefaultModelVisitor extends IModelVisitor {
    /**
     * Get the visitor to delegate to when a {@link IInfrastructureVisitor} is needed.
     * <p>If null is returned the caller will return null.
     * @return the {@link IInfrastructureVisitor} visitor or <i>null</i>.
     */
    @objid ("503a1260-0fcb-4ef3-a7cb-c26895fdd078")
    IInfrastructureVisitor getInfrastructureVisitor();

    @objid ("59bf9cfb-18cf-4b0e-b303-ae4ef7db1a95")
    @Override
    default Object visitAbstractPseudoState(AbstractPseudoState obj) {
        return visitStateVertex(obj);
        
    }

    @objid ("4e25637c-a9ef-45a5-bd1a-171f7581a35e")
    @Override
    default Object visitAbstraction(Abstraction obj) {
        IInfrastructureVisitor v = getInfrastructureVisitor();
        if (v != null)
          return v.visitDependency(obj);
        else
          return null;
        
    }

    @objid ("991591b7-6c1f-46f5-a7ed-53818e9b04c6")
    @Override
    default Object visitAcceptCallEventAction(AcceptCallEventAction obj) {
        return visitActivityAction(obj);
        
    }

    @objid ("b0444789-55e2-4f4b-91a1-2b8ab66276cd")
    @Override
    default Object visitAcceptChangeEventAction(AcceptChangeEventAction obj) {
        return visitActivityAction(obj);
        
    }

    @objid ("6d261cdd-5aa2-4b36-b225-e6224ad2e12b")
    @Override
    default Object visitAcceptSignalAction(AcceptSignalAction obj) {
        return visitActivityAction(obj);
        
    }

    @objid ("66c1a26b-6c00-48c7-98eb-309767ef3135")
    @Override
    default Object visitAcceptTimeEventAction(AcceptTimeEventAction obj) {
        return visitActivityAction(obj);
        
    }

    @objid ("c543af80-1670-47c5-8dc1-f80ba7d17480")
    @Override
    default Object visitActivity(Activity obj) {
        return visitBehavior(obj);
        
    }

    @objid ("ec642e07-7f7f-4c35-b38e-2355699ef8e8")
    @Override
    default Object visitActivityAction(ActivityAction obj) {
        return visitActivityNode(obj);
        
    }

    @objid ("cd5fcd90-865e-46a4-b6f6-075a9c7f4729")
    @Override
    default Object visitActivityDiagram(ActivityDiagram obj) {
        return visitBehaviorDiagram(obj);
        
    }

    @objid ("5fb51bb0-a8d9-482a-9f36-519b05a0e005")
    @Override
    default Object visitActivityEdge(ActivityEdge obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("5f2d4aff-5518-4738-896a-924788625716")
    @Override
    default Object visitActivityFinalNode(ActivityFinalNode obj) {
        return visitFinalNode(obj);
        
    }

    @objid ("1f3a5143-5db9-4928-aa49-23053dc2c5f2")
    @Override
    default Object visitActivityGroup(ActivityGroup obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("8a43c6ec-9164-4e19-a580-5c1051ad04f5")
    @Override
    default Object visitActivityNode(ActivityNode obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("6384de7e-58f5-4924-9bfc-61daf7ccc19d")
    @Override
    default Object visitActivityParameterNode(ActivityParameterNode obj) {
        return visitObjectNode(obj);
        
    }

    @objid ("d5289f21-a612-4c5d-a704-5f653343bdff")
    @Override
    default Object visitActivityPartition(ActivityPartition obj) {
        return visitActivityGroup(obj);
        
    }

    @objid ("9c565d77-ac03-4c7f-b2bd-1ad8f6a82a75")
    @Override
    default Object visitActor(Actor obj) {
        return visitGeneralClass(obj);
        
    }

    @objid ("5c94a7aa-3031-4d7f-94b6-8a5cf29e2ea7")
    @Override
    default Object visitArtifact(Artifact obj) {
        return visitClassifier(obj);
        
    }

    @objid ("091a016d-19e4-4169-9eb2-067e49dcc14d")
    @Override
    default Object visitAssociation(Association obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("fad3c004-a91e-461f-b958-4cfb3d3409c5")
    @Override
    default Object visitAssociationEnd(AssociationEnd obj) {
        return visitStructuralFeature(obj);
        
    }

    @objid ("0c528490-b414-4f58-b66a-06d2ea27fc1b")
    @Override
    default Object visitAttribute(Attribute obj) {
        return visitStructuralFeature(obj);
        
    }

    @objid ("da24f3c6-e636-4f3c-96d3-acc125710cff")
    @Override
    default Object visitAttributeLink(AttributeLink obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("395d793b-0d80-4755-a5b3-7eab303813e1")
    @Override
    default Object visitBehavior(Behavior obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("4318190c-f52f-40f3-8e59-1fc81099df06")
    @Override
    default Object visitBehaviorDiagram(BehaviorDiagram obj) {
        IInfrastructureVisitor v = getInfrastructureVisitor();
        if (v != null)
          return v.visitAbstractDiagram(obj);
        else
          return null;
        
    }

    @objid ("6ff10c08-59ba-46af-9bcf-dcf388b76c24")
    @Override
    default Object visitBehaviorParameter(BehaviorParameter obj) {
        return visitParameter(obj);
        
    }

    @objid ("297638ce-5ed6-4643-8258-e2a66469032e")
    @Override
    default Object visitBehavioralFeature(BehavioralFeature obj) {
        return visitFeature(obj);
        
    }

    @objid ("ac897780-44ac-4284-8adc-40dc9cd64196")
    @Override
    default Object visitBindableInstance(BindableInstance obj) {
        return visitInstance(obj);
        
    }

    @objid ("39023373-bb5c-4d02-a776-9be146787aa5")
    @Override
    default Object visitBinding(Binding obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("3f68a403-a937-4ba8-b6f9-49916f08156c")
    @Override
    default Object visitBpmnActivity(BpmnActivity obj) {
        return visitBpmnFlowNode(obj);
        
    }

    @objid ("c4ab5e5e-88b0-49ff-a93a-1b7544e750e4")
    @Override
    default Object visitBpmnAdHocSubProcess(BpmnAdHocSubProcess obj) {
        return visitBpmnSubProcess(obj);
        
    }

    @objid ("2c23bbb4-a996-4ff2-974d-2ec1bc7d9cbc")
    @Override
    default Object visitBpmnArtifact(BpmnArtifact obj) {
        return visitBpmnBaseElement(obj);
        
    }

    @objid ("49b93727-68c0-4bac-a5b3-dd26d1805fb2")
    @Override
    default Object visitBpmnAssociation(BpmnAssociation obj) {
        return visitBpmnArtifact(obj);
        
    }

    @objid ("fd57e9e2-0b77-4f44-8400-074f9ccf546e")
    @Override
    default Object visitBpmnBaseElement(BpmnBaseElement obj) {
        IInfrastructureVisitor v = getInfrastructureVisitor();
        if (v != null)
          return v.visitModelElement(obj);
        else
          return null;
        
    }

    @objid ("6c370c6d-2845-42d4-8fb6-43448a5fd6b5")
    @Override
    default Object visitBpmnBoundaryEvent(BpmnBoundaryEvent obj) {
        return visitBpmnCatchEvent(obj);
        
    }

    @objid ("d4948142-b56f-404c-9814-c989d3642ace")
    @Override
    default Object visitBpmnBusinessRuleTask(BpmnBusinessRuleTask obj) {
        return visitBpmnTask(obj);
        
    }

    @objid ("0fa8372a-c32c-4c41-a226-9fb19c946f7e")
    @Override
    default Object visitBpmnCallActivity(BpmnCallActivity obj) {
        return visitBpmnActivity(obj);
        
    }

    @objid ("93078e39-ba40-43c5-9bf3-ac760c6102ec")
    @Override
    default Object visitBpmnCancelEventDefinition(BpmnCancelEventDefinition obj) {
        return visitBpmnEventDefinition(obj);
        
    }

    @objid ("39e933e7-6e98-4588-811f-c71ddb107c46")
    @Override
    default Object visitBpmnCatchEvent(BpmnCatchEvent obj) {
        return visitBpmnEvent(obj);
        
    }

    @objid ("da18ed26-8b84-4cca-a2df-ad4c826f6011")
    @Override
    default Object visitBpmnCollaboration(BpmnCollaboration obj) {
        return visitBehavior(obj);
        
    }

    @objid ("078cf46f-69ee-4235-b06d-cc595749a4e8")
    @Override
    default Object visitBpmnCollaborationDiagram(BpmnCollaborationDiagram obj) {
        return visitBpmnProcessCollaborationDiagram(obj);
        
    }

    @objid ("f5193ce1-93f2-44c3-ba14-f3d001803f18")
    @Override
    default Object visitBpmnCompensateEventDefinition(BpmnCompensateEventDefinition obj) {
        return visitBpmnEventDefinition(obj);
        
    }

    @objid ("06ab0229-a4f7-4596-8e06-c75be679f50c")
    @Override
    default Object visitBpmnComplexBehaviorDefinition(BpmnComplexBehaviorDefinition obj) {
        return visitBpmnBaseElement(obj);
        
    }

    @objid ("9633163a-4381-4124-bdf7-1613a814c8b7")
    @Override
    default Object visitBpmnComplexGateway(BpmnComplexGateway obj) {
        return visitBpmnGateway(obj);
        
    }

    @objid ("e43e1175-074e-484f-88e9-5212cb87c11b")
    @Override
    default Object visitBpmnConditionalEventDefinition(BpmnConditionalEventDefinition obj) {
        return visitBpmnEventDefinition(obj);
        
    }

    @objid ("584604ad-a941-40de-973f-d8b73b6baeb9")
    @Override
    default Object visitBpmnDataAssociation(BpmnDataAssociation obj) {
        return visitBpmnBaseElement(obj);
        
    }

    @objid ("5cd5ed26-038f-4cd3-a2e4-3387cd592d82")
    @Override
    default Object visitBpmnDataInput(BpmnDataInput obj) {
        return visitBpmnItemAwareElement(obj);
        
    }

    @objid ("0b36253e-5d93-4022-8906-48fe9707a865")
    @Override
    default Object visitBpmnDataObject(BpmnDataObject obj) {
        return visitBpmnItemAwareElement(obj);
        
    }

    @objid ("6d2fa887-7cf3-4984-9c03-d8a0d8a58504")
    @Override
    default Object visitBpmnDataOutput(BpmnDataOutput obj) {
        return visitBpmnItemAwareElement(obj);
        
    }

    @objid ("d3b44a23-44d4-4fe0-8eba-04f02c13d0e1")
    @Override
    default Object visitBpmnDataState(BpmnDataState obj) {
        return visitBpmnBaseElement(obj);
        
    }

    @objid ("ea882eac-f172-4f79-b51e-2d6148376030")
    @Override
    default Object visitBpmnDataStore(BpmnDataStore obj) {
        return visitBpmnItemAwareElement(obj);
        
    }

    @objid ("c230c882-b6fb-4473-96f9-e273fe526b1b")
    @Override
    default Object visitBpmnEndEvent(BpmnEndEvent obj) {
        return visitBpmnThrowEvent(obj);
        
    }

    @objid ("04189bda-a1c7-4e2f-87f3-3edc9e8e2efa")
    @Override
    default Object visitBpmnEndPoint(BpmnEndPoint obj) {
        return visitBpmnSharedElement(obj);
        
    }

    @objid ("038b7859-7da3-43af-9d21-0718919d653f")
    @Override
    default Object visitBpmnErrorEventDefinition(BpmnErrorEventDefinition obj) {
        return visitBpmnEventDefinition(obj);
        
    }

    @objid ("d70604f9-e8a6-4e2c-9163-3eefd0ea3859")
    @Override
    default Object visitBpmnEscalationEventDefinition(BpmnEscalationEventDefinition obj) {
        return visitBpmnEventDefinition(obj);
        
    }

    @objid ("ffa132de-62d8-4216-b02f-4cd8b59e209e")
    @Override
    default Object visitBpmnEvent(BpmnEvent obj) {
        return visitBpmnFlowNode(obj);
        
    }

    @objid ("45eae5e4-0058-4005-9918-52fd8b9b4fd1")
    @Override
    default Object visitBpmnEventBasedGateway(BpmnEventBasedGateway obj) {
        return visitBpmnGateway(obj);
        
    }

    @objid ("37beeded-9ec4-499c-bb70-375e82015d1d")
    @Override
    default Object visitBpmnEventDefinition(BpmnEventDefinition obj) {
        return visitBpmnBaseElement(obj);
        
    }

    @objid ("4b988cdf-4cb9-4c5d-896a-d731c6dc678e")
    @Override
    default Object visitBpmnExclusiveGateway(BpmnExclusiveGateway obj) {
        return visitBpmnGateway(obj);
        
    }

    @objid ("54a78037-70be-46d6-934d-3a897a453fca")
    @Override
    default Object visitBpmnFlowElement(BpmnFlowElement obj) {
        return visitBpmnBaseElement(obj);
        
    }

    @objid ("190a98dd-ebd7-44f1-9830-402d83ff7763")
    @Override
    default Object visitBpmnFlowNode(BpmnFlowNode obj) {
        return visitBpmnFlowElement(obj);
        
    }

    @objid ("454b97e2-389a-4354-a328-40b86ccbd7a3")
    @Override
    default Object visitBpmnGateway(BpmnGateway obj) {
        return visitBpmnFlowNode(obj);
        
    }

    @objid ("e78d0d50-0816-4c60-a721-3f29bdda08c1")
    @Override
    default Object visitBpmnGroup(BpmnGroup obj) {
        return visitBpmnArtifact(obj);
        
    }

    @objid ("3d9d5033-b41d-4ef8-91a3-612dfa4f358e")
    @Override
    default Object visitBpmnImplicitThrowEvent(BpmnImplicitThrowEvent obj) {
        return visitBpmnThrowEvent(obj);
        
    }

    @objid ("c9f34905-6e63-4328-aa20-abed4314142e")
    @Override
    default Object visitBpmnInclusiveGateway(BpmnInclusiveGateway obj) {
        return visitBpmnGateway(obj);
        
    }

    @objid ("0c6b123e-04f2-4d84-99b2-b78e0bc143b0")
    @Override
    default Object visitBpmnInterface(BpmnInterface obj) {
        return visitBpmnSharedElement(obj);
        
    }

    @objid ("42a3c695-5537-4e37-8164-beb6e1eb4055")
    @Override
    default Object visitBpmnIntermediateCatchEvent(BpmnIntermediateCatchEvent obj) {
        return visitBpmnCatchEvent(obj);
        
    }

    @objid ("6cfe9609-c5b7-445b-9cbf-a3e62d78f52f")
    @Override
    default Object visitBpmnIntermediateThrowEvent(BpmnIntermediateThrowEvent obj) {
        return visitBpmnThrowEvent(obj);
        
    }

    @objid ("c3e3340d-1673-4db6-bce5-3dadca89d98d")
    @Override
    default Object visitBpmnItemAwareElement(BpmnItemAwareElement obj) {
        return visitBpmnFlowElement(obj);
        
    }

    @objid ("cd9d02d3-fda2-4678-8684-ddee0d61141b")
    @Override
    default Object visitBpmnItemDefinition(BpmnItemDefinition obj) {
        return visitBpmnSharedElement(obj);
        
    }

    @objid ("17311410-3498-4570-9fc9-03b4846ad2da")
    @Override
    default Object visitBpmnLane(BpmnLane obj) {
        return visitBpmnBaseElement(obj);
        
    }

    @objid ("4f9ca332-69cf-4e5f-be59-ab69edaf1824")
    @Override
    default Object visitBpmnLaneSet(BpmnLaneSet obj) {
        return visitBpmnBaseElement(obj);
        
    }

    @objid ("996e278d-dcf6-45e9-9fa3-e4506c51f169")
    @Override
    default Object visitBpmnLinkEventDefinition(BpmnLinkEventDefinition obj) {
        return visitBpmnEventDefinition(obj);
        
    }

    @objid ("a8a5bbb7-e9cb-4bdd-bfb6-3aeeb62777ea")
    @Override
    default Object visitBpmnLoopCharacteristics(BpmnLoopCharacteristics obj) {
        return visitBpmnBaseElement(obj);
        
    }

    @objid ("f314fecd-21b0-4fc6-8346-6dad91230874")
    @Override
    default Object visitBpmnManualTask(BpmnManualTask obj) {
        return visitBpmnTask(obj);
        
    }

    @objid ("b01dd9d0-610c-4008-a1dc-44cb5d5a1809")
    @Override
    default Object visitBpmnMessage(BpmnMessage obj) {
        return visitBpmnSharedElement(obj);
        
    }

    @objid ("6fd039aa-ebf2-4ae3-8811-e5976e9eb2cc")
    @Override
    default Object visitBpmnMessageEventDefinition(BpmnMessageEventDefinition obj) {
        return visitBpmnEventDefinition(obj);
        
    }

    @objid ("5e114e61-e8e2-4587-ab81-7d68f49b8a83")
    @Override
    default Object visitBpmnMessageFlow(BpmnMessageFlow obj) {
        return visitBpmnBaseElement(obj);
        
    }

    @objid ("1b8e9aac-ee76-4a02-b479-3b3046f338a6")
    @Override
    default Object visitBpmnMultiInstanceLoopCharacteristics(BpmnMultiInstanceLoopCharacteristics obj) {
        return visitBpmnLoopCharacteristics(obj);
        
    }

    @objid ("a0718018-0da5-4590-bd3f-256ba7e21e14")
    @Override
    default Object visitBpmnOperation(BpmnOperation obj) {
        return visitBpmnBaseElement(obj);
        
    }

    @objid ("c4a97335-c187-4cce-822a-afaeca1316fb")
    @Override
    default Object visitBpmnParallelGateway(BpmnParallelGateway obj) {
        return visitBpmnGateway(obj);
        
    }

    @objid ("0f9ec923-db36-4783-b9d9-a2c7bcbadc1f")
    @Override
    default Object visitBpmnParticipant(BpmnParticipant obj) {
        return visitBpmnBaseElement(obj);
        
    }

    @objid ("0e6d52c4-7579-4cc6-85b6-5d43ff246271")
    @Override
    default Object visitBpmnProcess(BpmnProcess obj) {
        return visitBehavior(obj);
        
    }

    @objid ("9a374bb8-d086-41f6-852c-b3f3129b70f3")
    @Override
    default Object visitBpmnProcessCollaborationDiagram(BpmnProcessCollaborationDiagram obj) {
        return visitBehaviorDiagram(obj);
        
    }

    @objid ("b19d6f7e-28dc-4da2-8064-00ac6d3cb0ea")
    @Override
    default Object visitBpmnProcessDesignDiagram(BpmnProcessDesignDiagram obj) {
        return visitBpmnProcessCollaborationDiagram(obj);
        
    }

    @objid ("1bfb93e3-e073-477b-ba94-1c8a9b832a7b")
    @Override
    default Object visitBpmnReceiveTask(BpmnReceiveTask obj) {
        return visitBpmnTask(obj);
        
    }

    @objid ("dc892aee-51f9-40d7-bc53-499663e760e9")
    @Override
    default Object visitBpmnResource(BpmnResource obj) {
        return visitBpmnSharedElement(obj);
        
    }

    @objid ("a99908e7-7bf2-4986-b9d6-87d983999da8")
    @Override
    default Object visitBpmnResourceParameter(BpmnResourceParameter obj) {
        return visitBpmnBaseElement(obj);
        
    }

    @objid ("fe107f6a-8ba6-427e-bf4e-7724a0c5dd07")
    @Override
    default Object visitBpmnResourceParameterBinding(BpmnResourceParameterBinding obj) {
        return visitBpmnBaseElement(obj);
        
    }

    @objid ("01343f6a-2189-4e98-bec1-4b5f8f57a32a")
    @Override
    default Object visitBpmnResourceRole(BpmnResourceRole obj) {
        return visitBpmnBaseElement(obj);
        
    }

    @objid ("f6f5f9ca-dda0-48b3-b047-96f94b43df4b")
    @Override
    default Object visitBpmnScriptTask(BpmnScriptTask obj) {
        return visitBpmnTask(obj);
        
    }

    @objid ("a25391f5-78c9-412d-9733-da593e601aef")
    @Override
    default Object visitBpmnSendTask(BpmnSendTask obj) {
        return visitBpmnTask(obj);
        
    }

    @objid ("e9483683-4210-44f8-bbf0-fb0d01aaa0ed")
    @Override
    default Object visitBpmnSequenceFlow(BpmnSequenceFlow obj) {
        return visitBpmnFlowElement(obj);
        
    }

    @objid ("cb8c11a6-cbfb-49b5-9528-ca3a7ed60f03")
    @Override
    default Object visitBpmnSequenceFlowDataAssociation(BpmnSequenceFlowDataAssociation obj) {
        return visitBpmnBaseElement(obj);
        
    }

    @objid ("a4a6e3e8-b652-4cba-92b5-9f10e994fd67")
    @Override
    default Object visitBpmnServiceTask(BpmnServiceTask obj) {
        return visitBpmnTask(obj);
        
    }

    @objid ("8db95ed1-ef45-4f6c-af09-bbc198d7d00f")
    @Override
    default Object visitBpmnSharedDefinitions(BpmnSharedDefinitions obj) {
        return visitBehavior(obj);
        
    }

    @objid ("adacc2ee-eb9c-4ca6-bb0b-53acbd511055")
    @Override
    default Object visitBpmnSharedElement(BpmnSharedElement obj) {
        return visitBpmnBaseElement(obj);
        
    }

    @objid ("349569c6-0420-4219-85b1-9147467380e6")
    @Override
    default Object visitBpmnSignalEventDefinition(BpmnSignalEventDefinition obj) {
        return visitBpmnEventDefinition(obj);
        
    }

    @objid ("99237737-69e3-48fb-94c7-b14aad56fc03")
    @Override
    default Object visitBpmnStandardLoopCharacteristics(BpmnStandardLoopCharacteristics obj) {
        return visitBpmnLoopCharacteristics(obj);
        
    }

    @objid ("2df6c914-c43e-44d0-bc10-431afbb3f000")
    @Override
    default Object visitBpmnStartEvent(BpmnStartEvent obj) {
        return visitBpmnCatchEvent(obj);
        
    }

    @objid ("e6ce1b6b-e9dd-4320-92e6-22aa79ff70cd")
    @Override
    default Object visitBpmnSubProcess(BpmnSubProcess obj) {
        return visitBpmnActivity(obj);
        
    }

    @objid ("4951f9e6-5564-41dd-97bd-1e644d5c1061")
    @Override
    default Object visitBpmnSubProcessDiagram(BpmnSubProcessDiagram obj) {
        return visitBehaviorDiagram(obj);
        
    }

    @objid ("a24f8457-0d17-4fd6-937c-f764c645c3f9")
    @Override
    default Object visitBpmnTask(BpmnTask obj) {
        return visitBpmnActivity(obj);
        
    }

    @objid ("41cd7603-b97b-4bf3-9b7c-f4ccdcb0a8b3")
    @Override
    default Object visitBpmnTerminateEventDefinition(BpmnTerminateEventDefinition obj) {
        return visitBpmnEventDefinition(obj);
        
    }

    @objid ("f9260e12-e3c1-4c93-b807-7aad32aff5ff")
    @Override
    default Object visitBpmnThrowEvent(BpmnThrowEvent obj) {
        return visitBpmnEvent(obj);
        
    }

    @objid ("ee2219ff-77b6-4ebd-9c66-d0a37172bcf7")
    @Override
    default Object visitBpmnTimerEventDefinition(BpmnTimerEventDefinition obj) {
        return visitBpmnEventDefinition(obj);
        
    }

    @objid ("a68bbbdf-2750-4dde-9ebe-43b50927e976")
    @Override
    default Object visitBpmnTransaction(BpmnTransaction obj) {
        return visitBpmnSubProcess(obj);
        
    }

    @objid ("3898d083-c739-4b6f-99be-90553b541eb2")
    @Override
    default Object visitBpmnUserTask(BpmnUserTask obj) {
        return visitBpmnTask(obj);
        
    }

    @objid ("b3c83ec5-1946-43a9-82b6-c7485d8b793d")
    @Override
    default Object visitCallAction(CallAction obj) {
        return visitActivityAction(obj);
        
    }

    @objid ("72041f4e-fd2e-4b7d-b84c-2254fe95c198")
    @Override
    default Object visitCallBehaviorAction(CallBehaviorAction obj) {
        return visitCallAction(obj);
        
    }

    @objid ("cfab557d-2ae4-477e-83b9-b569bb21171f")
    @Override
    default Object visitCallOperationAction(CallOperationAction obj) {
        return visitCallAction(obj);
        
    }

    @objid ("21e664ea-fd42-4840-905a-b0f8a42e36d3")
    @Override
    default Object visitCentralBufferNode(CentralBufferNode obj) {
        return visitObjectNode(obj);
        
    }

    @objid ("a770b3f1-bd77-4566-b601-5445b5069f0a")
    @Override
    default Object visitChoicePseudoState(ChoicePseudoState obj) {
        return visitAbstractPseudoState(obj);
        
    }

    @objid ("6249b5da-3872-420b-bcd3-b917ba2e37c5")
    @Override
    default Object visitClass(Class obj) {
        return visitGeneralClass(obj);
        
    }

    @objid ("1148db58-0af5-4447-bb4d-36e53c171615")
    @Override
    default Object visitClassAssociation(ClassAssociation obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("8f0a60bf-0356-49c6-98a4-29890631f036")
    @Override
    default Object visitClassDiagram(ClassDiagram obj) {
        return visitStaticDiagram(obj);
        
    }

    @objid ("dcceff77-0c2c-4170-8ca2-fcfc2ce56a18")
    @Override
    default Object visitClassifier(Classifier obj) {
        return visitNameSpace(obj);
        
    }

    @objid ("2bbd1ccd-c04f-4b1a-9bea-dd16608eb480")
    @Override
    default Object visitClause(Clause obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("15ede574-e055-4b3b-bb66-419a381fe256")
    @Override
    default Object visitCollaboration(Collaboration obj) {
        return visitNameSpace(obj);
        
    }

    @objid ("bc777f5c-112c-458e-8b7a-9ce6c3f86495")
    @Override
    default Object visitCollaborationUse(CollaborationUse obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("80ba147b-6d8e-498a-b17c-aeebf86ff5b6")
    @Override
    default Object visitCombinedFragment(CombinedFragment obj) {
        return visitInteractionFragment(obj);
        
    }

    @objid ("fd89df00-1a40-493e-a9af-5dba2818770e")
    @Override
    default Object visitCommunicationChannel(CommunicationChannel obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("2db1dbed-177f-4b84-b0b3-ca6f530ae34b")
    @Override
    default Object visitCommunicationDiagram(CommunicationDiagram obj) {
        return visitBehaviorDiagram(obj);
        
    }

    @objid ("7912d313-ce59-4a47-82f8-e8bf30191c57")
    @Override
    default Object visitCommunicationInteraction(CommunicationInteraction obj) {
        return visitBehavior(obj);
        
    }

    @objid ("3a2f69c1-a7d7-48fe-8660-3346acf0edbd")
    @Override
    default Object visitCommunicationMessage(CommunicationMessage obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("9c5d3242-614f-4b7f-837a-9a93d453d5fa")
    @Override
    default Object visitCommunicationNode(CommunicationNode obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("d98ef5fd-c5c9-400c-8a5b-2dca298e324a")
    @Override
    default Object visitComponent(Component obj) {
        return visitClass(obj);
        
    }

    @objid ("ed34e91f-6977-40be-8113-04b24c04f40f")
    @Override
    default Object visitComponentRealization(ComponentRealization obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("377a348e-d4be-4fde-878d-016874c172e5")
    @Override
    default Object visitCompositeStructureDiagram(CompositeStructureDiagram obj) {
        return visitStaticDiagram(obj);
        
    }

    @objid ("79db36c7-8daf-479e-b1c2-a973d06063b2")
    @Override
    default Object visitConditionalNode(ConditionalNode obj) {
        return visitStructuredActivityNode(obj);
        
    }

    @objid ("f6467cb0-5fbe-4cdd-8c33-9ca87f9c7928")
    @Override
    default Object visitConnectionPointReference(ConnectionPointReference obj) {
        return visitStateVertex(obj);
        
    }

    @objid ("8fc9b73d-aa59-4b81-91f2-74c4b3ce21b1")
    @Override
    default Object visitConnector(Connector obj) {
        return visitLink(obj);
        
    }

    @objid ("5cf6ceac-38e1-4244-9737-4b2b9acfd0a9")
    @Override
    default Object visitConnectorEnd(ConnectorEnd obj) {
        return visitLinkEnd(obj);
        
    }

    @objid ("91e363b5-77e4-4cad-bf04-a1aaadaa49de")
    @Override
    default Object visitConstraint(Constraint obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("dc859637-4182-4bf0-8a32-c4698a7698b8")
    @Override
    default Object visitControlFlow(ControlFlow obj) {
        return visitActivityEdge(obj);
        
    }

    @objid ("0a4a8969-e107-4d0f-8216-9d2c652c14aa")
    @Override
    default Object visitControlNode(ControlNode obj) {
        return visitActivityNode(obj);
        
    }

    @objid ("602878fc-fdf1-41b4-8784-96a3d72af457")
    @Override
    default Object visitDataFlow(DataFlow obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("20734a67-23a6-457f-adc1-36b3f0847938")
    @Override
    default Object visitDataStoreNode(DataStoreNode obj) {
        return visitCentralBufferNode(obj);
        
    }

    @objid ("f3a59b3e-fb4d-4895-b7b3-cc1e69c93df7")
    @Override
    default Object visitDataType(DataType obj) {
        return visitGeneralClass(obj);
        
    }

    @objid ("e01a965a-9677-4bce-9dad-b3ef808807a1")
    @Override
    default Object visitDecisionMergeNode(DecisionMergeNode obj) {
        return visitControlNode(obj);
        
    }

    @objid ("a71bd0d9-04d6-4e45-89c4-59b809f71389")
    @Override
    default Object visitDeepHistoryPseudoState(DeepHistoryPseudoState obj) {
        return visitAbstractPseudoState(obj);
        
    }

    @objid ("b51676ee-0eef-434e-8d79-08651ee2c933")
    @Override
    default Object visitDeploymentDiagram(DeploymentDiagram obj) {
        return visitStaticDiagram(obj);
        
    }

    @objid ("cc1563f9-ff2b-4d38-b3a9-f754be798cef")
    @Override
    default Object visitDurationConstraint(DurationConstraint obj) {
        return visitConstraint(obj);
        
    }

    @objid ("aa2f23e3-8d7b-49f4-8005-b27ec8db4d7e")
    @Override
    default Object visitElementImport(ElementImport obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("a4d20a58-9505-4fd8-979e-9693e1730735")
    @Override
    default Object visitElementRealization(ElementRealization obj) {
        return visitAbstraction(obj);
        
    }

    @objid ("3f50614f-7c42-4032-987c-e438af77a8f3")
    @Override
    default Object visitEntryPointPseudoState(EntryPointPseudoState obj) {
        return visitAbstractPseudoState(obj);
        
    }

    @objid ("6b12cc76-2988-4636-8de8-3a8036d4239a")
    @Override
    default Object visitEnumeration(Enumeration obj) {
        return visitGeneralClass(obj);
        
    }

    @objid ("94f211d2-b55e-4fd9-b931-73dc4c13347f")
    @Override
    default Object visitEnumerationLiteral(EnumerationLiteral obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("bc5c72ed-7bb7-4866-b293-ad5f57cd5eab")
    @Override
    default Object visitEvent(Event obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("ab9070ba-3da2-43ad-8a4c-502d55a9bd31")
    @Override
    default Object visitExceptionHandler(ExceptionHandler obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("48b0cff2-75ed-46c8-9b54-e9fd62fd6b74")
    @Override
    default Object visitExecutionOccurenceSpecification(ExecutionOccurenceSpecification obj) {
        return visitMessageEnd(obj);
        
    }

    @objid ("a7d3d57e-ffd2-4802-9d05-97d952d1b8b1")
    @Override
    default Object visitExecutionSpecification(ExecutionSpecification obj) {
        return visitInteractionFragment(obj);
        
    }

    @objid ("4e56bd13-2ff0-4245-b457-4bfdb4f4457d")
    @Override
    default Object visitExitPointPseudoState(ExitPointPseudoState obj) {
        return visitAbstractPseudoState(obj);
        
    }

    @objid ("30cd14ee-3bf6-4479-83ea-4994b204a2d8")
    @Override
    default Object visitExpansionNode(ExpansionNode obj) {
        return visitObjectNode(obj);
        
    }

    @objid ("b06b0762-4d9e-4f0e-9f0c-441c96552a3a")
    @Override
    default Object visitExpansionRegion(ExpansionRegion obj) {
        return visitStructuredActivityNode(obj);
        
    }

    @objid ("ac952a46-90d3-4a89-a535-ec488d81a87c")
    @Override
    default Object visitExtensionPoint(ExtensionPoint obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("928eba44-ef90-4d16-ad13-a2996cd673cb")
    @Override
    default Object visitFeature(Feature obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("da25bfbb-80a7-48ff-bfbd-a27563fa2eb4")
    @Override
    default Object visitFinalNode(FinalNode obj) {
        return visitControlNode(obj);
        
    }

    @objid ("278d78a2-81a4-40af-a7d1-d4f3acff32f8")
    @Override
    default Object visitFinalState(FinalState obj) {
        return visitState(obj);
        
    }

    @objid ("6aa23ebd-cd97-4542-8855-dd74dde1177a")
    @Override
    default Object visitFlowFinalNode(FlowFinalNode obj) {
        return visitFinalNode(obj);
        
    }

    @objid ("9f665a47-2465-46ff-b0d6-c026349b2ae8")
    @Override
    default Object visitForkJoinNode(ForkJoinNode obj) {
        return visitControlNode(obj);
        
    }

    @objid ("d0e33085-0e61-43b3-b28d-21e11bfc2b77")
    @Override
    default Object visitForkPseudoState(ForkPseudoState obj) {
        return visitAbstractPseudoState(obj);
        
    }

    @objid ("30ffe71f-bde7-4b19-a7b3-16f45841a785")
    @Override
    default Object visitGate(Gate obj) {
        return visitMessageEnd(obj);
        
    }

    @objid ("6f9ab2a9-1392-497c-b67e-8acca943f5f7")
    @Override
    default Object visitGeneralClass(GeneralClass obj) {
        return visitClassifier(obj);
        
    }

    @objid ("97c6ecfc-3bdc-46d3-943a-fd86105b2bf5")
    @Override
    default Object visitGeneralOrdering(GeneralOrdering obj) {
        IInfrastructureVisitor v = getInfrastructureVisitor();
        if (v != null)
          return v.visitElement(obj);
        else
          return null;
        
    }

    @objid ("d674f5c3-1676-4d83-bc35-2ea0238e15c2")
    @Override
    default Object visitGeneralization(Generalization obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("351432b3-8d56-4532-8464-5ae0f7ac3153")
    @Override
    default Object visitInformationFlow(InformationFlow obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("8e59959e-f25f-47cd-98f9-159ef22c13c5")
    @Override
    default Object visitInformationItem(InformationItem obj) {
        return visitClassifier(obj);
        
    }

    @objid ("c8da65ba-3819-45bb-b663-566119f92ca2")
    @Override
    default Object visitInitialNode(InitialNode obj) {
        return visitControlNode(obj);
        
    }

    @objid ("68bfe827-0305-4ef1-bbb1-5ea5ebc6d676")
    @Override
    default Object visitInitialPseudoState(InitialPseudoState obj) {
        return visitAbstractPseudoState(obj);
        
    }

    @objid ("648e9256-9fd8-46bf-94ac-82e218d2b020")
    @Override
    default Object visitInputPin(InputPin obj) {
        return visitPin(obj);
        
    }

    @objid ("6603d4ca-eaaf-423b-8959-9693e42e8b28")
    @Override
    default Object visitInstance(Instance obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("251b53e7-0c55-40d2-97de-41d5125bd46b")
    @Override
    default Object visitInstanceNode(InstanceNode obj) {
        return visitObjectNode(obj);
        
    }

    @objid ("df31fe41-8e2b-452e-b499-446b27a445cc")
    @Override
    default Object visitInteraction(Interaction obj) {
        return visitBehavior(obj);
        
    }

    @objid ("a8d6b310-90a4-4173-b775-5ea170d93dba")
    @Override
    default Object visitInteractionFragment(InteractionFragment obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("619c0f77-944b-4f8a-b3fc-3ea4fba14cf4")
    @Override
    default Object visitInteractionOperand(InteractionOperand obj) {
        return visitInteractionFragment(obj);
        
    }

    @objid ("1703013f-78e9-4ff3-bf43-3d062326f042")
    @Override
    default Object visitInteractionUse(InteractionUse obj) {
        return visitInteractionFragment(obj);
        
    }

    @objid ("dad2d21a-d5de-4db7-b4ea-12f6b1ff14b0")
    @Override
    default Object visitInterface(Interface obj) {
        return visitGeneralClass(obj);
        
    }

    @objid ("cbebc8a4-4942-4b74-9268-5dd5b40f88dd")
    @Override
    default Object visitInterfaceRealization(InterfaceRealization obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("945aef9e-b17b-46c3-bea8-5d9b6c53ab86")
    @Override
    default Object visitInternalTransition(InternalTransition obj) {
        return visitTransition(obj);
        
    }

    @objid ("6fa39672-0a84-455a-b9a7-4eef4ccbfd4e")
    @Override
    default Object visitInterruptibleActivityRegion(InterruptibleActivityRegion obj) {
        return visitActivityGroup(obj);
        
    }

    @objid ("7281b8dd-a9ef-46f1-8fcd-ab0f6e8858f4")
    @Override
    default Object visitJoinPseudoState(JoinPseudoState obj) {
        return visitAbstractPseudoState(obj);
        
    }

    @objid ("e3e7db27-9b8d-41fd-a20c-3ba81a468bf6")
    @Override
    default Object visitJunctionPseudoState(JunctionPseudoState obj) {
        return visitAbstractPseudoState(obj);
        
    }

    @objid ("bbba42cf-e696-4826-97b7-8cf6b06e4c16")
    @Override
    default Object visitLifeline(Lifeline obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("a2242fae-63bd-4fb3-9a10-9d2de14e4903")
    @Override
    default Object visitLink(Link obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("b753a0ee-7570-4132-b0d4-d5857137ba58")
    @Override
    default Object visitLinkEnd(LinkEnd obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("85043b88-e14f-4516-a7d0-53ce8173bac5")
    @Override
    default Object visitLoopNode(LoopNode obj) {
        return visitStructuredActivityNode(obj);
        
    }

    @objid ("0c30b138-0019-490f-a509-49320305e2a1")
    @Override
    default Object visitManifestation(Manifestation obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("2f4f4ab3-f6d1-4d10-a7f0-776c0b256343")
    @Override
    default Object visitMessage(Message obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("fd7817ff-c2b1-4f2d-a179-70f812f7a73d")
    @Override
    default Object visitMessageEnd(MessageEnd obj) {
        return visitOccurrenceSpecification(obj);
        
    }

    @objid ("a44a775b-bea6-4312-91ff-8129a8d405d2")
    @Override
    default Object visitMessageFlow(MessageFlow obj) {
        return visitActivityEdge(obj);
        
    }

    @objid ("1bb7115e-3d5c-481f-8c39-1d0538e8af3d")
    @Override
    default Object visitModelTree(ModelTree obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("40de81cc-203f-49ed-a226-07cba9b15418")
    @Override
    default Object visitNameSpace(NameSpace obj) {
        return visitModelTree(obj);
        
    }

    @objid ("2dc3817e-02ea-4929-8ee1-6096fe215037")
    @Override
    default Object visitNaryAssociation(NaryAssociation obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("a2318788-a10e-40cb-b5a1-4583995a6931")
    @Override
    default Object visitNaryAssociationEnd(NaryAssociationEnd obj) {
        return visitStructuralFeature(obj);
        
    }

    @objid ("8174a4c0-022a-4ea3-bc0b-67b4afc678bd")
    @Override
    default Object visitNaryConnector(NaryConnector obj) {
        return visitNaryLink(obj);
        
    }

    @objid ("f17b3767-f951-49db-b0e3-8290df9d68e2")
    @Override
    default Object visitNaryConnectorEnd(NaryConnectorEnd obj) {
        return visitNaryLinkEnd(obj);
        
    }

    @objid ("5113117a-8c95-4244-8af6-da337e6cdb73")
    @Override
    default Object visitNaryLink(NaryLink obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("c9915c61-60c4-4c83-9c46-98f60a60804c")
    @Override
    default Object visitNaryLinkEnd(NaryLinkEnd obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("6be482e6-e766-44e7-94d4-3f236cd09600")
    @Override
    default Object visitNode(Node obj) {
        return visitClassifier(obj);
        
    }

    @objid ("ad067809-687a-4e1d-8f69-512d8c684f90")
    @Override
    default Object visitObjectDiagram(ObjectDiagram obj) {
        return visitStaticDiagram(obj);
        
    }

    @objid ("f537325d-d6f3-487b-9a79-62375bfde67d")
    @Override
    default Object visitObjectFlow(ObjectFlow obj) {
        return visitActivityEdge(obj);
        
    }

    @objid ("beafaf77-3f4e-44c8-81a5-f9a2d4f9b6ec")
    @Override
    default Object visitObjectNode(ObjectNode obj) {
        return visitActivityNode(obj);
        
    }

    @objid ("6eda2087-019e-44fd-917c-063163052a0a")
    @Override
    default Object visitOccurrenceSpecification(OccurrenceSpecification obj) {
        return visitInteractionFragment(obj);
        
    }

    @objid ("8a866d56-c38d-40a8-9c36-c8c5bc3dc246")
    @Override
    default Object visitOpaqueAction(OpaqueAction obj) {
        return visitActivityAction(obj);
        
    }

    @objid ("0b285850-4c0d-46bb-84f1-a2db75b07622")
    @Override
    default Object visitOpaqueBehavior(OpaqueBehavior obj) {
        return visitBehavior(obj);
        
    }

    @objid ("cd2949ca-3cc7-4781-99f9-58d77794dbf5")
    @Override
    default Object visitOperation(Operation obj) {
        return visitBehavioralFeature(obj);
        
    }

    @objid ("d0cda06f-fa5b-4605-adda-80b6304a8aef")
    @Override
    default Object visitOutputPin(OutputPin obj) {
        return visitPin(obj);
        
    }

    @objid ("f12a0c4c-6ce0-402c-8a59-f14a8799f0ab")
    @Override
    default Object visitPackage(Package obj) {
        return visitNameSpace(obj);
        
    }

    @objid ("a6701e14-343d-46a5-a55e-1163e9de7f7b")
    @Override
    default Object visitPackageImport(PackageImport obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("25b2f612-ac35-4745-a88b-1daaa5d40bf0")
    @Override
    default Object visitPackageMerge(PackageMerge obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("dbff2db8-ab7e-4c69-ac9f-a134f9444d81")
    @Override
    default Object visitParameter(Parameter obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("e475d8da-267b-4276-809c-bb8826b1aa1b")
    @Override
    default Object visitPartDecomposition(PartDecomposition obj) {
        return visitInteractionUse(obj);
        
    }

    @objid ("d67044a4-18c5-4b8c-8a88-6d9631d6a878")
    @Override
    default Object visitPin(Pin obj) {
        return visitObjectNode(obj);
        
    }

    @objid ("76a3a2ee-756e-434c-a050-39f196d3347e")
    @Override
    default Object visitPort(Port obj) {
        return visitBindableInstance(obj);
        
    }

    @objid ("e8b1dd04-aeb9-444e-aa45-a65e0f2edae6")
    @Override
    default Object visitProject(Project obj) {
        IInfrastructureVisitor v = getInfrastructureVisitor();
        if (v != null)
          return v.visitAbstractProject(obj);
        else
          return null;
        
    }

    @objid ("e021486a-9050-4bb7-a3c9-fe66a1c69d94")
    @Override
    default Object visitProvidedInterface(ProvidedInterface obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("6f5a3517-2546-4361-a364-412c1468489f")
    @Override
    default Object visitRaisedException(RaisedException obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("276c0776-e6fa-43ce-bbf4-c26caa631e28")
    @Override
    default Object visitRegion(Region obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("2321feca-324c-4a91-bec1-874990b1eb87")
    @Override
    default Object visitRequiredInterface(RequiredInterface obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("2a4ca45e-c368-4e3d-8ede-907ffdf2569d")
    @Override
    default Object visitSendSignalAction(SendSignalAction obj) {
        return visitActivityAction(obj);
        
    }

    @objid ("0f20afd0-0e56-42cb-9f07-b90228b9f981")
    @Override
    default Object visitSequenceDiagram(SequenceDiagram obj) {
        return visitBehaviorDiagram(obj);
        
    }

    @objid ("4015c8d5-f5bb-4296-a9fa-d201b23a6c23")
    @Override
    default Object visitShallowHistoryPseudoState(ShallowHistoryPseudoState obj) {
        return visitAbstractPseudoState(obj);
        
    }

    @objid ("770699bd-a970-462c-a727-e3be3e3c5b8a")
    @Override
    default Object visitSignal(Signal obj) {
        return visitGeneralClass(obj);
        
    }

    @objid ("fd526408-ef25-4987-b206-551669c92996")
    @Override
    default Object visitState(State obj) {
        return visitStateVertex(obj);
        
    }

    @objid ("cad1e0c0-7bfe-4797-8c8a-a357875fc89f")
    @Override
    default Object visitStateInvariant(StateInvariant obj) {
        return visitOccurrenceSpecification(obj);
        
    }

    @objid ("a259152d-525e-4327-83ea-c31391da65e0")
    @Override
    default Object visitStateMachine(StateMachine obj) {
        return visitBehavior(obj);
        
    }

    @objid ("e3fd2c29-f6bc-4673-b75a-b49bfd71e947")
    @Override
    default Object visitStateMachineDiagram(StateMachineDiagram obj) {
        return visitBehaviorDiagram(obj);
        
    }

    @objid ("667ec18d-3ac9-4a3e-9d3e-de707d77d913")
    @Override
    default Object visitStateVertex(StateVertex obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("ff0364dc-15a4-4960-b16c-07ec0c64e530")
    @Override
    default Object visitStaticDiagram(StaticDiagram obj) {
        IInfrastructureVisitor v = getInfrastructureVisitor();
        if (v != null)
          return v.visitAbstractDiagram(obj);
        else
          return null;
        
    }

    @objid ("2093c54d-84c4-4d36-9886-28e625335606")
    @Override
    default Object visitStructuralFeature(StructuralFeature obj) {
        return visitFeature(obj);
        
    }

    @objid ("71351626-0a1c-4511-92a6-74f50b25e618")
    @Override
    default Object visitStructuredActivityNode(StructuredActivityNode obj) {
        return visitActivityAction(obj);
        
    }

    @objid ("ddf87abf-33ab-4080-b8fe-04a6c9cfbc42")
    @Override
    default Object visitSubstitution(Substitution obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("2be00144-57c3-4049-a684-0c17bab1cf82")
    @Override
    default Object visitTemplateBinding(TemplateBinding obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("a1f119b4-3774-4b2c-bfe7-622e70ccdf28")
    @Override
    default Object visitTemplateParameter(TemplateParameter obj) {
        return visitGeneralClass(obj);
        
    }

    @objid ("30ced484-e7fe-463c-9766-e51d3db76710")
    @Override
    default Object visitTemplateParameterSubstitution(TemplateParameterSubstitution obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("d8c29b7f-4d6d-4501-b00d-340656d650f9")
    @Override
    default Object visitTerminatePseudoState(TerminatePseudoState obj) {
        return visitAbstractPseudoState(obj);
        
    }

    @objid ("2a56e751-f565-4344-a237-ffe95ec33fa8")
    @Override
    default Object visitTerminateSpecification(TerminateSpecification obj) {
        return visitExecutionOccurenceSpecification(obj);
        
    }

    @objid ("f5f5e625-25e1-4862-bc35-a69c3a730989")
    @Override
    default Object visitTransition(Transition obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("91014d16-aace-4d97-93bc-5344f75f30ae")
    @Override
    default Object visitUmlModelElement(UmlModelElement obj) {
        IInfrastructureVisitor v = getInfrastructureVisitor();
        if (v != null)
          return v.visitModelElement(obj);
        else
          return null;
        
    }

    @objid ("1e1eeb5b-c28c-4a3f-b4c0-2157a6677097")
    @Override
    default Object visitUsage(Usage obj) {
        IInfrastructureVisitor v = getInfrastructureVisitor();
        if (v != null)
          return v.visitDependency(obj);
        else
          return null;
        
    }

    @objid ("1d0d3816-2586-4948-aaa8-e6f43ebc7283")
    @Override
    default Object visitUseCase(UseCase obj) {
        return visitGeneralClass(obj);
        
    }

    @objid ("a2973169-2351-44b9-b457-8f177b8416a6")
    @Override
    default Object visitUseCaseDependency(UseCaseDependency obj) {
        return visitUmlModelElement(obj);
        
    }

    @objid ("2dd41b77-e053-4e7c-ac83-152f2f6e808f")
    @Override
    default Object visitUseCaseDiagram(UseCaseDiagram obj) {
        return visitStaticDiagram(obj);
        
    }

    @objid ("95a31d9b-cc5e-412e-81e0-66f7ee3d5ac0")
    @Override
    default Object visitValuePin(ValuePin obj) {
        return visitInputPin(obj);
        
    }
}

