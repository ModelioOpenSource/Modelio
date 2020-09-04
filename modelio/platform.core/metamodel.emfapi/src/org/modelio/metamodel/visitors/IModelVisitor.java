/* 
 * Copyright 2013-2019 Modeliosoft
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
import org.modelio.vcore.smkernel.mapi.MVisitor;

@objid ("000a4862-4f2c-1032-829a-001ec947cd2a")
public interface IModelVisitor extends MVisitor {
    @objid ("cfdec494-a71f-4e68-803b-f02669a0aec7")
    Object visitAbstractPseudoState(AbstractPseudoState obj);

    @objid ("b8144e41-ffda-45c7-a5a0-d1549b93b071")
    Object visitAbstraction(Abstraction obj);

    @objid ("9200c30e-f2e5-4857-9bb8-d56359fe4929")
    Object visitAcceptCallEventAction(AcceptCallEventAction obj);

    @objid ("19c87b13-30b9-4adf-bbb1-27394d896e78")
    Object visitAcceptChangeEventAction(AcceptChangeEventAction obj);

    @objid ("95930bfb-a5df-49ed-9fe6-1687d681b719")
    Object visitAcceptSignalAction(AcceptSignalAction obj);

    @objid ("639434f1-93e2-4f27-aa69-f9bcf86f57d4")
    Object visitAcceptTimeEventAction(AcceptTimeEventAction obj);

    @objid ("e4fa39e3-a5a6-4c22-afeb-7d710884620c")
    Object visitActivity(Activity obj);

    @objid ("dce44de5-9865-44be-81d8-5e81a708c491")
    Object visitActivityAction(ActivityAction obj);

    @objid ("cdc30f51-090f-436e-bb40-da15e221c5de")
    Object visitActivityDiagram(ActivityDiagram obj);

    @objid ("11648455-2400-4dd3-8c08-0084cbb6d51d")
    Object visitActivityEdge(ActivityEdge obj);

    @objid ("b34015c2-083b-4efc-b550-3e7c95671cb8")
    Object visitActivityFinalNode(ActivityFinalNode obj);

    @objid ("b0486ee3-0c50-4c44-a0dc-d0eb6fce4226")
    Object visitActivityGroup(ActivityGroup obj);

    @objid ("be6543a5-7539-41da-8ad5-7de822b0a300")
    Object visitActivityNode(ActivityNode obj);

    @objid ("8073d6b2-893d-490a-8984-572a00f3ea85")
    Object visitActivityParameterNode(ActivityParameterNode obj);

    @objid ("223d86c6-9e2e-43f6-9c98-654cd9345933")
    Object visitActivityPartition(ActivityPartition obj);

    @objid ("49e0518d-8e04-4886-8bd8-59da1f7dda96")
    Object visitActor(Actor obj);

    @objid ("c7da03f0-ea32-41b8-9a51-fc2751d04939")
    Object visitArtifact(Artifact obj);

    @objid ("4edb03d7-9b4b-4bda-ab90-d21329b337c3")
    Object visitAssociation(Association obj);

    @objid ("5b071186-b8e8-4905-bcd9-e3377d827f07")
    Object visitAssociationEnd(AssociationEnd obj);

    @objid ("b4a35e1e-ec62-4ca1-aae2-14c5403c983d")
    Object visitAttribute(Attribute obj);

    @objid ("6442fbb8-899d-4c84-9b6a-a7e8325b18a8")
    Object visitAttributeLink(AttributeLink obj);

    @objid ("b864c981-6420-44b2-b7d5-162f5bd95868")
    Object visitBehavior(Behavior obj);

    @objid ("5b84d39f-8511-4963-bba7-c859546d5dc3")
    Object visitBehaviorDiagram(BehaviorDiagram obj);

    @objid ("d0224822-f792-45f4-ac48-02fa86ef13ca")
    Object visitBehaviorParameter(BehaviorParameter obj);

    @objid ("cbddf4c3-d6fb-4bfa-b8fa-80e11b5474e7")
    Object visitBehavioralFeature(BehavioralFeature obj);

    @objid ("2374ca01-b493-421b-8b8d-bcc953345671")
    Object visitBindableInstance(BindableInstance obj);

    @objid ("a11417ec-e4b7-4768-9307-7776f7e5010c")
    Object visitBinding(Binding obj);

    @objid ("5c1a40ea-f916-4ba3-83df-8012d6b5893b")
    Object visitBpmnActivity(BpmnActivity obj);

    @objid ("fb14b9f9-9740-4943-8a6d-3e27dfeba6dc")
    Object visitBpmnAdHocSubProcess(BpmnAdHocSubProcess obj);

    @objid ("e8708616-557e-4415-96e5-f401955b272c")
    Object visitBpmnArtifact(BpmnArtifact obj);

    @objid ("f2b2344a-1a5e-4649-8ae8-a271752b51a5")
    Object visitBpmnAssociation(BpmnAssociation obj);

    @objid ("9ac8883e-63d7-42fd-be6b-0bb2193b80b6")
    Object visitBpmnBaseElement(BpmnBaseElement obj);

    @objid ("bfbb5058-a806-41fc-9688-793b74b969d7")
    Object visitBpmnBoundaryEvent(BpmnBoundaryEvent obj);

    @objid ("a3370cc2-d002-452b-88e8-4838bfe9240e")
    Object visitBpmnBusinessRuleTask(BpmnBusinessRuleTask obj);

    @objid ("6a0ff588-199b-4630-8b1c-a2003e9190c6")
    Object visitBpmnCallActivity(BpmnCallActivity obj);

    @objid ("66025e38-259e-4e0f-ad47-9c9bd891b40f")
    Object visitBpmnCancelEventDefinition(BpmnCancelEventDefinition obj);

    @objid ("7573968d-5189-408d-ace1-6b17b287fd21")
    Object visitBpmnCatchEvent(BpmnCatchEvent obj);

    @objid ("3ec4924c-e0a9-410e-83bf-fc6344955b6b")
    Object visitBpmnCollaboration(BpmnCollaboration obj);

    @objid ("55b72548-d14f-439f-a60c-e354b0c9f6b7")
    Object visitBpmnCollaborationDiagram(BpmnCollaborationDiagram obj);

    @objid ("81d85e0a-0139-4c18-a6fa-7495e2d3182c")
    Object visitBpmnCompensateEventDefinition(BpmnCompensateEventDefinition obj);

    @objid ("24ee9f6f-660a-4709-bef5-7e1645374fec")
    Object visitBpmnComplexBehaviorDefinition(BpmnComplexBehaviorDefinition obj);

    @objid ("c0bfb81a-0678-49b4-a769-f04c3a156392")
    Object visitBpmnComplexGateway(BpmnComplexGateway obj);

    @objid ("114a954b-28d2-4ce6-a4f4-2471e2c3b92a")
    Object visitBpmnConditionalEventDefinition(BpmnConditionalEventDefinition obj);

    @objid ("dcf026dc-9d5a-47c3-9fb1-35e0a5a5498d")
    Object visitBpmnDataAssociation(BpmnDataAssociation obj);

    @objid ("26754cfd-1c29-4653-b939-62b998f13d1a")
    Object visitBpmnDataInput(BpmnDataInput obj);

    @objid ("b3f40df5-1f1e-4a95-a528-e89fc1d74e10")
    Object visitBpmnDataObject(BpmnDataObject obj);

    @objid ("aafe52af-005e-4a0e-b37d-360b24fd4f74")
    Object visitBpmnDataOutput(BpmnDataOutput obj);

    @objid ("307e26c9-407e-48b6-8933-1f341c7c0cad")
    Object visitBpmnDataState(BpmnDataState obj);

    @objid ("6c4ffb5a-8e20-4050-b7c1-64d6ab542a44")
    Object visitBpmnDataStore(BpmnDataStore obj);

    @objid ("740316a2-80d1-4a6d-9d17-a009ee607561")
    Object visitBpmnEndEvent(BpmnEndEvent obj);

    @objid ("5b8e3833-5b4c-43fb-aa1b-dcae6d21f4ed")
    Object visitBpmnEndPoint(BpmnEndPoint obj);

    @objid ("8011e9b1-91f8-439c-a54d-3204e53c0530")
    Object visitBpmnErrorEventDefinition(BpmnErrorEventDefinition obj);

    @objid ("ab099db9-1611-464f-87cc-56e0b17723ad")
    Object visitBpmnEscalationEventDefinition(BpmnEscalationEventDefinition obj);

    @objid ("9053f36d-04f5-42f3-a509-39349d89b9bd")
    Object visitBpmnEvent(BpmnEvent obj);

    @objid ("5f95d924-d47f-445d-8f73-d110d8b5be25")
    Object visitBpmnEventBasedGateway(BpmnEventBasedGateway obj);

    @objid ("966c72a5-a584-4925-b5d5-f2517ff50f08")
    Object visitBpmnEventDefinition(BpmnEventDefinition obj);

    @objid ("b94cd7aa-1602-41db-9bcd-c5eeeddbbfc3")
    Object visitBpmnExclusiveGateway(BpmnExclusiveGateway obj);

    @objid ("b20f6d5c-21a3-4b63-86bc-d87ff326e635")
    Object visitBpmnFlowElement(BpmnFlowElement obj);

    @objid ("39edc3a8-f51d-4c23-b3f8-eea0a5e84ff6")
    Object visitBpmnFlowNode(BpmnFlowNode obj);

    @objid ("fbfb0153-031b-4596-927f-62941da02c10")
    Object visitBpmnGateway(BpmnGateway obj);

    @objid ("e25a1ad6-ce2e-479a-a189-4e38aac100b5")
    Object visitBpmnGroup(BpmnGroup obj);

    @objid ("eb7a6ae6-1681-4a2d-8641-a635f8c3862d")
    Object visitBpmnImplicitThrowEvent(BpmnImplicitThrowEvent obj);

    @objid ("16200eee-0a24-48c9-a12b-d0d6cf3187b6")
    Object visitBpmnInclusiveGateway(BpmnInclusiveGateway obj);

    @objid ("9dd90331-4fcb-4043-8cf0-f8eb292321cc")
    Object visitBpmnInterface(BpmnInterface obj);

    @objid ("ee804580-cfb5-431f-b467-be9603aa1f00")
    Object visitBpmnIntermediateCatchEvent(BpmnIntermediateCatchEvent obj);

    @objid ("f5d1f688-4fb7-4ba1-a0c2-82383ce5f040")
    Object visitBpmnIntermediateThrowEvent(BpmnIntermediateThrowEvent obj);

    @objid ("138f8868-b5cf-4e38-ad9f-cc1bbaa822c6")
    Object visitBpmnItemAwareElement(BpmnItemAwareElement obj);

    @objid ("9f438e85-8129-4d39-9469-8badaf7543a5")
    Object visitBpmnItemDefinition(BpmnItemDefinition obj);

    @objid ("063f0665-b451-42a4-84bd-df226f1d69a1")
    Object visitBpmnLane(BpmnLane obj);

    @objid ("bd19974f-db08-4cf9-9e40-1655e737da1e")
    Object visitBpmnLaneSet(BpmnLaneSet obj);

    @objid ("b089f5b7-2b9c-4e47-939b-6c4199d80136")
    Object visitBpmnLinkEventDefinition(BpmnLinkEventDefinition obj);

    @objid ("81f05202-62e5-496a-81ab-d59013c0cb93")
    Object visitBpmnLoopCharacteristics(BpmnLoopCharacteristics obj);

    @objid ("7ab4a6fd-94c3-4d35-8d6d-06265206e792")
    Object visitBpmnManualTask(BpmnManualTask obj);

    @objid ("cf86c548-0530-4780-9135-921e2a03bf90")
    Object visitBpmnMessage(BpmnMessage obj);

    @objid ("f14fa350-4ba3-468b-ad5f-d974e841ffb2")
    Object visitBpmnMessageEventDefinition(BpmnMessageEventDefinition obj);

    @objid ("f0c4f04b-4089-4edb-93df-7de8002d5856")
    Object visitBpmnMessageFlow(BpmnMessageFlow obj);

    @objid ("e2f9f143-3dd2-439e-be00-1fcb8f05f217")
    Object visitBpmnMultiInstanceLoopCharacteristics(BpmnMultiInstanceLoopCharacteristics obj);

    @objid ("eb2ff98a-a3a8-46c6-8159-1f5983255635")
    Object visitBpmnOperation(BpmnOperation obj);

    @objid ("81edc8dc-d116-4290-ac66-7313d4a91306")
    Object visitBpmnParallelGateway(BpmnParallelGateway obj);

    @objid ("21bbcefb-2420-4bbc-a1b0-361d3fe4dab5")
    Object visitBpmnParticipant(BpmnParticipant obj);

    @objid ("d501bfd5-7feb-4fe7-99d0-9c38063615f9")
    Object visitBpmnProcess(BpmnProcess obj);

    @objid ("48e025a7-f714-4c65-a694-d2fb0a60d7af")
    Object visitBpmnProcessCollaborationDiagram(BpmnProcessCollaborationDiagram obj);

    @objid ("6168252a-c309-4282-b46b-f89c0e923ac7")
    Object visitBpmnProcessDesignDiagram(BpmnProcessDesignDiagram obj);

    @objid ("82d4a3e5-da19-4f42-9f84-5498931a9521")
    Object visitBpmnReceiveTask(BpmnReceiveTask obj);

    @objid ("df2ee154-1ff9-44ee-b918-5786c33e48b3")
    Object visitBpmnResource(BpmnResource obj);

    @objid ("516f6e65-a41a-4e95-8344-af53b29cd1a8")
    Object visitBpmnResourceParameter(BpmnResourceParameter obj);

    @objid ("b3d799a4-63e0-43bd-8d15-a2bbd4262c79")
    Object visitBpmnResourceParameterBinding(BpmnResourceParameterBinding obj);

    @objid ("6ce9c216-1603-44cc-bfd6-b16d328c868b")
    Object visitBpmnResourceRole(BpmnResourceRole obj);

    @objid ("f8142a23-8b91-4da6-8552-585fd59b3f42")
    Object visitBpmnScriptTask(BpmnScriptTask obj);

    @objid ("630b62f9-b9c8-483d-ab6d-23c850321322")
    Object visitBpmnSendTask(BpmnSendTask obj);

    @objid ("90afb6d4-d8d5-49e4-aa75-90e3d8e97866")
    Object visitBpmnSequenceFlow(BpmnSequenceFlow obj);

    @objid ("1489b233-74c3-4656-9a16-707bd2fa48c2")
    Object visitBpmnSequenceFlowDataAssociation(BpmnSequenceFlowDataAssociation obj);

    @objid ("206329b9-d74e-4d8e-a1e8-79a9386cbc03")
    Object visitBpmnServiceTask(BpmnServiceTask obj);

    @objid ("dc1cfc2f-fa16-49c3-ae81-a79df8de65b3")
    Object visitBpmnSharedDefinitions(BpmnSharedDefinitions obj);

    @objid ("5a81d638-252c-404a-830c-f73ccfe90916")
    Object visitBpmnSharedElement(BpmnSharedElement obj);

    @objid ("0c74dc6d-e20a-4025-8b33-9c9138090e4a")
    Object visitBpmnSignalEventDefinition(BpmnSignalEventDefinition obj);

    @objid ("f81bda2f-ada8-4683-8020-eb3a77470841")
    Object visitBpmnStandardLoopCharacteristics(BpmnStandardLoopCharacteristics obj);

    @objid ("cd905a16-12d1-44eb-bc69-38b6ad8506ab")
    Object visitBpmnStartEvent(BpmnStartEvent obj);

    @objid ("bb224ff5-d421-4415-8fe5-700b84e4b59d")
    Object visitBpmnSubProcess(BpmnSubProcess obj);

    @objid ("a0a4c5ad-a9d2-4cc8-acbd-710f63695721")
    Object visitBpmnSubProcessDiagram(BpmnSubProcessDiagram obj);

    @objid ("525edb69-e9da-4a47-b74d-4ca5b8fda939")
    Object visitBpmnTask(BpmnTask obj);

    @objid ("063a892c-b6fc-4b7f-af45-26ffa98022f8")
    Object visitBpmnTerminateEventDefinition(BpmnTerminateEventDefinition obj);

    @objid ("e1e26d6d-0c8b-41d2-a520-002fe27e97db")
    Object visitBpmnThrowEvent(BpmnThrowEvent obj);

    @objid ("05ef04ac-6744-447b-8f62-67b5d854dc97")
    Object visitBpmnTimerEventDefinition(BpmnTimerEventDefinition obj);

    @objid ("31fc5667-53a3-4197-9bdd-23bc4b2a27cd")
    Object visitBpmnTransaction(BpmnTransaction obj);

    @objid ("c77b7302-88bc-487c-bbcd-19ac01ab13b0")
    Object visitBpmnUserTask(BpmnUserTask obj);

    @objid ("8666d836-2a1a-4c49-8e05-034f85212838")
    Object visitCallAction(CallAction obj);

    @objid ("f99f8654-e0b2-4c52-83e6-ad0526734450")
    Object visitCallBehaviorAction(CallBehaviorAction obj);

    @objid ("1a1deb59-1f29-47d4-a70d-6ad4b9aa4811")
    Object visitCallOperationAction(CallOperationAction obj);

    @objid ("ca4a9da9-0566-41b2-bb58-687faa0cacdb")
    Object visitCentralBufferNode(CentralBufferNode obj);

    @objid ("10ee7a84-68cf-4099-b05f-7478077937b4")
    Object visitChoicePseudoState(ChoicePseudoState obj);

    @objid ("35da494e-55c9-4a2c-a156-c13fe49c7ae6")
    Object visitClass(Class obj);

    @objid ("079ad363-b8da-42e8-a1bf-c9fb8c521746")
    Object visitClassAssociation(ClassAssociation obj);

    @objid ("0289458b-2ff8-49fc-9d62-bea09dd7f570")
    Object visitClassDiagram(ClassDiagram obj);

    @objid ("ce71356e-fbec-4b10-86a8-02f0ddf015fc")
    Object visitClassifier(Classifier obj);

    @objid ("d006e0b5-61f3-4ad0-a48f-77741bd51fde")
    Object visitClause(Clause obj);

    @objid ("520b5343-3e72-459e-981f-d2d5d81fee74")
    Object visitCollaboration(Collaboration obj);

    @objid ("45888c0d-16b4-404d-83f0-095c89957177")
    Object visitCollaborationUse(CollaborationUse obj);

    @objid ("895e291f-bb6d-4973-ba09-833c70960ce0")
    Object visitCombinedFragment(CombinedFragment obj);

    @objid ("d209aece-1c6a-44f3-b6be-6effab49eead")
    Object visitCommunicationChannel(CommunicationChannel obj);

    @objid ("5b64ad45-af4d-4945-bc22-c16a998eabc6")
    Object visitCommunicationDiagram(CommunicationDiagram obj);

    @objid ("90ac5cf5-b0d0-4ae7-b464-8795589bcb8c")
    Object visitCommunicationInteraction(CommunicationInteraction obj);

    @objid ("a6f643f7-b2e1-4b40-86c7-4df618813ab7")
    Object visitCommunicationMessage(CommunicationMessage obj);

    @objid ("037ec3c9-62e1-46aa-9395-cfa886e76897")
    Object visitCommunicationNode(CommunicationNode obj);

    @objid ("60515d2f-ed2e-42f0-8d80-5189074cd982")
    Object visitComponent(Component obj);

    @objid ("a477916c-0adb-4211-8e50-b39e1cb3f2e5")
    Object visitComponentRealization(ComponentRealization obj);

    @objid ("20ba5359-fb9a-4aff-9617-35e9d1f175d6")
    Object visitCompositeStructureDiagram(CompositeStructureDiagram obj);

    @objid ("2c6a1541-c631-420a-bfb0-52a4c51a57d7")
    Object visitConditionalNode(ConditionalNode obj);

    @objid ("4444eb09-abd9-425f-af5f-d90759eb39e9")
    Object visitConnectionPointReference(ConnectionPointReference obj);

    @objid ("92e4d110-1519-41bc-8539-f10b2077ad7f")
    Object visitConnector(Connector obj);

    @objid ("74019e7c-064c-4804-b4a9-8764ba9b6fac")
    Object visitConnectorEnd(ConnectorEnd obj);

    @objid ("dd4f3264-144f-45c9-a052-a0c68a6638e1")
    Object visitConstraint(Constraint obj);

    @objid ("80384bdb-0d71-405a-a153-cad1f220b399")
    Object visitControlFlow(ControlFlow obj);

    @objid ("fac77602-9791-4fd7-a783-279c3280f78c")
    Object visitControlNode(ControlNode obj);

    @objid ("36b2d348-3720-4823-992d-35967c01e1c6")
    Object visitDataFlow(DataFlow obj);

    @objid ("a2bc82d9-2bb4-4bdd-8659-ebf23e404008")
    Object visitDataStoreNode(DataStoreNode obj);

    @objid ("d4e85a6f-94cb-4117-acd3-457ade8f5f15")
    Object visitDataType(DataType obj);

    @objid ("425b1bf2-4501-4b9b-b639-97700ee29ee2")
    Object visitDecisionMergeNode(DecisionMergeNode obj);

    @objid ("2cb5bb74-bff9-4eb5-a7b5-317ffd4dab30")
    Object visitDeepHistoryPseudoState(DeepHistoryPseudoState obj);

    @objid ("5027f6b9-17bf-45fe-8972-daa3430c6dc3")
    Object visitDeploymentDiagram(DeploymentDiagram obj);

    @objid ("7ba3ddb5-06df-42db-a023-6a07906969c1")
    Object visitDurationConstraint(DurationConstraint obj);

    @objid ("7eda6e8f-1b0e-4a19-bda7-23820724665c")
    Object visitElementImport(ElementImport obj);

    @objid ("6da43212-7c1b-45ab-9ca9-abe64d9ff5a0")
    Object visitElementRealization(ElementRealization obj);

    @objid ("45896a54-2dd6-49e2-9855-e174a18fe9e4")
    Object visitEntryPointPseudoState(EntryPointPseudoState obj);

    @objid ("0573e97d-b8c4-447f-8991-cfbeec99dc16")
    Object visitEnumeration(Enumeration obj);

    @objid ("fa694843-2fcf-4148-a741-6d987b4bd9c3")
    Object visitEnumerationLiteral(EnumerationLiteral obj);

    @objid ("7c988fee-193d-4f67-bfcb-3192962d32ed")
    Object visitEvent(Event obj);

    @objid ("35a150e9-eb37-4ea0-ad74-52c6faf26423")
    Object visitExceptionHandler(ExceptionHandler obj);

    @objid ("e856f65a-8a3a-4927-99f9-28704814781e")
    Object visitExecutionOccurenceSpecification(ExecutionOccurenceSpecification obj);

    @objid ("5717ed51-c15e-489a-a9ea-eaeb89a14458")
    Object visitExecutionSpecification(ExecutionSpecification obj);

    @objid ("6d23503b-cfcb-4297-980f-98de77e826d1")
    Object visitExitPointPseudoState(ExitPointPseudoState obj);

    @objid ("aaf645f4-67d8-4f5c-b342-47359ce01314")
    Object visitExpansionNode(ExpansionNode obj);

    @objid ("ab64abf9-c287-4b31-8be4-b1076849454e")
    Object visitExpansionRegion(ExpansionRegion obj);

    @objid ("e75dd157-2e50-42fc-90b4-3ce8e3bc1aeb")
    Object visitExtensionPoint(ExtensionPoint obj);

    @objid ("a1228ac8-a62d-47ae-9c56-a024b4ddfcff")
    Object visitFeature(Feature obj);

    @objid ("3d48c1eb-2daa-49fb-91e2-d9ba679a61ba")
    Object visitFinalNode(FinalNode obj);

    @objid ("fcdacbfe-6f52-4eaa-b1db-d6a054aacfaa")
    Object visitFinalState(FinalState obj);

    @objid ("a239e7ed-7f3d-4eea-a5a6-746d2dc87fce")
    Object visitFlowFinalNode(FlowFinalNode obj);

    @objid ("04fb05c5-659d-40f1-8e77-82a7f22311fa")
    Object visitForkJoinNode(ForkJoinNode obj);

    @objid ("de037b76-6a6e-43de-a66f-e664d724ed4f")
    Object visitForkPseudoState(ForkPseudoState obj);

    @objid ("b2102a3e-8033-45d1-87ad-b39f30ba6923")
    Object visitGate(Gate obj);

    @objid ("80ba06ca-6296-4081-855c-bb374d316de0")
    Object visitGeneralClass(GeneralClass obj);

    @objid ("5691c9e1-a792-49ea-a7f7-68a6faef6ecf")
    Object visitGeneralOrdering(GeneralOrdering obj);

    @objid ("3df61eff-aabb-4db7-a5dc-e124e04e6b15")
    Object visitGeneralization(Generalization obj);

    @objid ("2c46fd48-8331-4045-8890-430cd23ef1b7")
    Object visitInformationFlow(InformationFlow obj);

    @objid ("55662bd8-6d6e-4aed-b6a0-3abba6b770cf")
    Object visitInformationItem(InformationItem obj);

    @objid ("32fe664d-e8ff-41e9-9084-df531fea9b5c")
    Object visitInitialNode(InitialNode obj);

    @objid ("194d0cd8-5690-47ce-ad61-1c9463c6ec48")
    Object visitInitialPseudoState(InitialPseudoState obj);

    @objid ("0b7cb097-becb-4281-91fb-67de69bc9735")
    Object visitInputPin(InputPin obj);

    @objid ("c9285267-62f6-42eb-9202-ed8e3a5b55e2")
    Object visitInstance(Instance obj);

    @objid ("0748d4a6-7383-4055-ba22-d8bf134fe2b3")
    Object visitInstanceNode(InstanceNode obj);

    @objid ("7490118a-5386-4b12-92bf-048003acfa00")
    Object visitInteraction(Interaction obj);

    @objid ("babc3fff-509e-4b28-804f-9941f650c8e4")
    Object visitInteractionFragment(InteractionFragment obj);

    @objid ("7bae8385-c3c1-43bc-88e0-cfc4eb40ab40")
    Object visitInteractionOperand(InteractionOperand obj);

    @objid ("49d5e784-0655-4991-84da-bd0a04a29d93")
    Object visitInteractionUse(InteractionUse obj);

    @objid ("9d1d90e0-6cff-4891-b93e-3c0503f29079")
    Object visitInterface(Interface obj);

    @objid ("949b45b3-473d-4299-97f1-f9385ca03868")
    Object visitInterfaceRealization(InterfaceRealization obj);

    @objid ("53f76548-ccb6-4b38-abd8-5229db7be1b2")
    Object visitInternalTransition(InternalTransition obj);

    @objid ("aed442a5-90b5-41be-9005-c77e9eb2f3ec")
    Object visitInterruptibleActivityRegion(InterruptibleActivityRegion obj);

    @objid ("5352397e-e390-4718-8e98-1f7404b1ec72")
    Object visitJoinPseudoState(JoinPseudoState obj);

    @objid ("781fea89-9bc9-41fb-8559-5cc1cb5d0075")
    Object visitJunctionPseudoState(JunctionPseudoState obj);

    @objid ("b822de50-50a0-49fe-a376-5632a6a210d4")
    Object visitLifeline(Lifeline obj);

    @objid ("f5a44e65-e521-4118-8765-9aeb64e3aa9c")
    Object visitLink(Link obj);

    @objid ("e05c18a2-07a8-4311-ad85-335447b7c89e")
    Object visitLinkEnd(LinkEnd obj);

    @objid ("a94ce82f-9d47-4ab1-bba5-16abc99d3243")
    Object visitLoopNode(LoopNode obj);

    @objid ("d6e8be8c-08ca-4f1f-bb27-b23441b5f8bb")
    Object visitManifestation(Manifestation obj);

    @objid ("a6f33512-93a9-4f51-a85d-5894e9756022")
    Object visitMessage(Message obj);

    @objid ("73493d6e-d87a-4ac0-a06f-06400eab0e44")
    Object visitMessageEnd(MessageEnd obj);

    @objid ("b4bca730-55a9-4e5d-9892-f2bf28a3f416")
    Object visitMessageFlow(MessageFlow obj);

    @objid ("9f0136e1-7830-47a2-b824-60aff1ced8a5")
    Object visitModelTree(ModelTree obj);

    @objid ("0fed457b-7964-4fb0-b581-c63070ce0863")
    Object visitNameSpace(NameSpace obj);

    @objid ("d6b9361f-f3ca-47fe-b822-350e2617da1c")
    Object visitNaryAssociation(NaryAssociation obj);

    @objid ("ce1c65e2-e565-4ad9-9e6b-5975d8771571")
    Object visitNaryAssociationEnd(NaryAssociationEnd obj);

    @objid ("427a049f-5262-4507-b50a-25f3903fcee2")
    Object visitNaryConnector(NaryConnector obj);

    @objid ("005259ae-3dfa-4266-86de-74aa487201f8")
    Object visitNaryConnectorEnd(NaryConnectorEnd obj);

    @objid ("3b17c999-9b80-46de-a3b1-02ff54f36f24")
    Object visitNaryLink(NaryLink obj);

    @objid ("ab198ac9-8fca-4a00-874b-28c06cda20c2")
    Object visitNaryLinkEnd(NaryLinkEnd obj);

    @objid ("c92a6cb6-f83f-4944-aa9e-fdfbc5ae1def")
    Object visitNode(Node obj);

    @objid ("9522f579-8793-4932-afae-ff0c9f5875ab")
    Object visitObjectDiagram(ObjectDiagram obj);

    @objid ("407a5705-7bd3-492b-8093-1d33fc9cd925")
    Object visitObjectFlow(ObjectFlow obj);

    @objid ("7e91dde6-9ddf-4024-a3fb-7119cb424f05")
    Object visitObjectNode(ObjectNode obj);

    @objid ("d83756fe-31fd-47e3-aab6-425b8da4cc8d")
    Object visitOccurrenceSpecification(OccurrenceSpecification obj);

    @objid ("ca303e81-f23c-40c8-b3e8-0f9514e0487f")
    Object visitOpaqueAction(OpaqueAction obj);

    @objid ("11eaf66f-ba37-4443-96c8-8b5cae87bb52")
    Object visitOpaqueBehavior(OpaqueBehavior obj);

    @objid ("9ae55c2d-c7cd-4bd8-b904-b5205645921b")
    Object visitOperation(Operation obj);

    @objid ("0c647ec6-7793-413b-ad5c-c1fc4bf9cfe5")
    Object visitOutputPin(OutputPin obj);

    @objid ("8cbc92d7-a5bf-4829-a7ca-957e6dfc564b")
    Object visitPackage(Package obj);

    @objid ("05eb06fc-53f1-4c90-bdd1-571403f54f2f")
    Object visitPackageImport(PackageImport obj);

    @objid ("f2620178-16e4-4715-aecb-0482973d53a4")
    Object visitPackageMerge(PackageMerge obj);

    @objid ("569fec3f-d7df-4708-a358-af2b47fe2000")
    Object visitParameter(Parameter obj);

    @objid ("9d988e12-0d71-40e9-89af-4bc813180754")
    Object visitPartDecomposition(PartDecomposition obj);

    @objid ("2946b702-645f-49f4-b925-024fc14603ad")
    Object visitPin(Pin obj);

    @objid ("f827af66-ef2f-4ca3-b92e-c604af410870")
    Object visitPort(Port obj);

    @objid ("983119b6-18b0-4c00-af22-cba49b522cc9")
    Object visitProject(Project obj);

    @objid ("d0043c89-f54e-45af-9b18-2f455afd7f74")
    Object visitProvidedInterface(ProvidedInterface obj);

    @objid ("c9400370-0fb5-4e88-8672-bb902546da36")
    Object visitRaisedException(RaisedException obj);

    @objid ("58eb0f8c-d70e-4572-8379-024152e6b0c4")
    Object visitRegion(Region obj);

    @objid ("d102989a-6beb-4766-8b71-6c1f898e28c1")
    Object visitRequiredInterface(RequiredInterface obj);

    @objid ("abf0d20d-bda6-4bb8-a37b-7d050cce6fb8")
    Object visitSendSignalAction(SendSignalAction obj);

    @objid ("f6ca4d2e-3d96-4cd5-81f2-c91ce28b49d5")
    Object visitSequenceDiagram(SequenceDiagram obj);

    @objid ("c65b126e-d109-405d-9ddf-a41b40c92be5")
    Object visitShallowHistoryPseudoState(ShallowHistoryPseudoState obj);

    @objid ("efb51251-1c04-4ac1-b1f2-3b59bb645885")
    Object visitSignal(Signal obj);

    @objid ("e29e147f-6eda-4f46-8b7b-6d2bad2010d7")
    Object visitState(State obj);

    @objid ("d554478a-f92e-4bef-aa34-da21e9830d28")
    Object visitStateInvariant(StateInvariant obj);

    @objid ("653ee30b-e4fa-426e-8266-98340458068c")
    Object visitStateMachine(StateMachine obj);

    @objid ("a3d3e812-f33a-49a6-88e4-d006a7bcdfd9")
    Object visitStateMachineDiagram(StateMachineDiagram obj);

    @objid ("fad82777-d751-4c0c-9113-2e99e3cd4736")
    Object visitStateVertex(StateVertex obj);

    @objid ("71932255-e3d4-4508-a94b-490d1147bc54")
    Object visitStaticDiagram(StaticDiagram obj);

    @objid ("a510a1c0-4f47-4200-a70b-7974395ffa5f")
    Object visitStructuralFeature(StructuralFeature obj);

    @objid ("1e5db8ce-789a-4af0-a205-6333c04020e3")
    Object visitStructuredActivityNode(StructuredActivityNode obj);

    @objid ("85f118e3-9ac3-4142-ac32-1a8f703edee8")
    Object visitSubstitution(Substitution obj);

    @objid ("8f2b8401-22aa-49d4-9ea6-bf7a41108a64")
    Object visitTemplateBinding(TemplateBinding obj);

    @objid ("825f8c6a-9f89-4119-b179-298ed2ab3db8")
    Object visitTemplateParameter(TemplateParameter obj);

    @objid ("7bfc58c9-874a-4812-83be-611271368736")
    Object visitTemplateParameterSubstitution(TemplateParameterSubstitution obj);

    @objid ("c742b3da-348d-4e60-9683-e6a9211320a0")
    Object visitTerminatePseudoState(TerminatePseudoState obj);

    @objid ("8755ab96-3c59-46c1-a1d2-bdc62cb52448")
    Object visitTerminateSpecification(TerminateSpecification obj);

    @objid ("634d228c-831e-4c8a-8bae-83f006f9a435")
    Object visitTransition(Transition obj);

    @objid ("03e49d1a-7f3b-4e56-a0d2-f4aa1eba893a")
    Object visitUmlModelElement(UmlModelElement obj);

    @objid ("21644422-a8d7-44c3-a94d-8c2b1d8cec74")
    Object visitUsage(Usage obj);

    @objid ("afdf8828-389a-4e2f-b36f-18aaa7fe8932")
    Object visitUseCase(UseCase obj);

    @objid ("f4648de2-a278-4842-aa3f-cfb8810ad127")
    Object visitUseCaseDependency(UseCaseDependency obj);

    @objid ("a87962cf-24d8-4415-8ccc-511212cc983c")
    Object visitUseCaseDiagram(UseCaseDiagram obj);

    @objid ("a2ca39c6-ae60-4bcd-8ff4-e8df5f0b02e8")
    Object visitValuePin(ValuePin obj);

}
