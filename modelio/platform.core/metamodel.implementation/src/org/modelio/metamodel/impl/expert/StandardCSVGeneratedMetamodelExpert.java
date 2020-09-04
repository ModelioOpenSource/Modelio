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

package org.modelio.metamodel.impl.expert;

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
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.utils.metamodel.experts.CompositeMetamodelExpert;

/**
 * This is an automatically generated metamodel expert.
 * <p>
 * All modifications will be overwritten. If you need to modify it, subclass this class instead.
 * <p>
 * You need to call {@link #register()} after instantiation to initialize the expert from the metamodel.
 */
@objid ("584d4183-2654-45b0-9e4e-6c0c5b54c35b")
public class StandardCSVGeneratedMetamodelExpert extends CompositeMetamodelExpert {
    /**
     * Constructor.
     * <p>
     * You need to call {@link #register()} next.
     * @param mm The metamodel.
     */
    @objid ("6af6247c-0090-4e35-b61e-f2159ab54a90")
    public StandardCSVGeneratedMetamodelExpert(MMetamodel mm) {
        super(mm);
    }

    /**
     * Initializes this expert.
     */
    @objid ("750cdafb-e00e-4420-a3f8-512c410cf388")
    public void register() {
        registerLinkExpertForBpmnMessageFlow();
        registerLinkExpertForBpmnSequenceFlow();
        registerLinkExpertForActivityEdge();
        registerLinkExpertForCommunicationChannel();
        registerLinkExpertForMessage();
        registerLinkExpertForTransition();
        registerLinkExpertForUseCaseDependency();
        registerLinkExpertForDataFlow();
        registerLinkExpertForInformationFlow();
        registerLinkExpertForAbstraction();
        registerLinkExpertForSubstitution();
        registerLinkExpertForUsage();
        registerLinkExpertForAssociation();
        registerLinkExpertForAssociationEnd();
        registerLinkExpertForElementImport();
        registerLinkExpertForGeneralization();
        registerLinkExpertForInterfaceRealization();
        registerLinkExpertForLink();
        registerLinkExpertForLinkEnd();
        registerLinkExpertForManifestation();
        registerLinkExpertForPackageImport();
        registerLinkExpertForPackageMerge();
        registerLinkExpertForRaisedException();
        registerLinkExpertForTemplateBinding();
        registerLinkExpertForControlFlow();
        registerLinkExpertForMessageFlow();
        registerLinkExpertForObjectFlow();
        registerLinkExpertForElementRealization();
        registerLinkExpertForConnector();
        registerLinkExpertForConnectorEnd();
        registerMetaExpertForProject();
        registerMetaExpertForTemplateParameterSubstitution();
        registerMetaExpertForTemplateParameter();
        registerMetaExpertForTemplateBinding();
        registerMetaExpertForRequiredInterface();
        registerMetaExpertForRaisedException();
        registerMetaExpertForProvidedInterface();
        registerMetaExpertForPort();
        registerMetaExpertForPackageMerge();
        registerMetaExpertForPackageImport();
        registerMetaExpertForPackage();
        registerMetaExpertForOperation();
        registerMetaExpertForNode();
        registerMetaExpertForNaryConnectorEnd();
        registerMetaExpertForNaryLinkEnd();
        registerMetaExpertForNaryConnector();
        registerMetaExpertForNaryLink();
        registerMetaExpertForNaryAssociationEnd();
        registerMetaExpertForNaryAssociation();
        registerMetaExpertForManifestation();
        registerMetaExpertForInterfaceRealization();
        registerMetaExpertForInterface();
        registerMetaExpertForGeneralization();
        registerMetaExpertForEnumerationLiteral();
        registerMetaExpertForEnumeration();
        registerMetaExpertForElementRealization();
        registerMetaExpertForElementImport();
        registerMetaExpertForDataType();
        registerMetaExpertForConnectorEnd();
        registerMetaExpertForLinkEnd();
        registerMetaExpertForConnector();
        registerMetaExpertForLink();
        registerMetaExpertForComponentRealization();
        registerMetaExpertForComponent();
        registerMetaExpertForCollaborationUse();
        registerMetaExpertForCollaboration();
        registerMetaExpertForClassAssociation();
        registerMetaExpertForClass();
        registerMetaExpertForBinding();
        registerMetaExpertForBindableInstance();
        registerMetaExpertForInstance();
        registerMetaExpertForBehavioralFeature();
        registerMetaExpertForAttributeLink();
        registerMetaExpertForAttribute();
        registerMetaExpertForAssociationEnd();
        registerMetaExpertForStructuralFeature();
        registerMetaExpertForFeature();
        registerMetaExpertForAssociation();
        registerMetaExpertForArtifact();
        registerMetaExpertForUsage();
        registerMetaExpertForSubstitution();
        registerMetaExpertForAbstraction();
        registerMetaExpertForInformationItem();
        registerMetaExpertForInformationFlow();
        registerMetaExpertForDataFlow();
        registerMetaExpertForUseCaseDependency();
        registerMetaExpertForUseCase();
        registerMetaExpertForExtensionPoint();
        registerMetaExpertForActor();
        registerMetaExpertForTerminatePseudoState();
        registerMetaExpertForStateMachine();
        registerMetaExpertForShallowHistoryPseudoState();
        registerMetaExpertForRegion();
        registerMetaExpertForJunctionPseudoState();
        registerMetaExpertForJoinPseudoState();
        registerMetaExpertForInternalTransition();
        registerMetaExpertForTransition();
        registerMetaExpertForInitialPseudoState();
        registerMetaExpertForForkPseudoState();
        registerMetaExpertForFinalState();
        registerMetaExpertForState();
        registerMetaExpertForExitPointPseudoState();
        registerMetaExpertForEntryPointPseudoState();
        registerMetaExpertForDeepHistoryPseudoState();
        registerMetaExpertForConnectionPointReference();
        registerMetaExpertForChoicePseudoState();
        registerMetaExpertForAbstractPseudoState();
        registerMetaExpertForStateVertex();
        registerMetaExpertForTerminateSpecification();
        registerMetaExpertForStateInvariant();
        registerMetaExpertForPartDecomposition();
        registerMetaExpertForMessage();
        registerMetaExpertForLifeline();
        registerMetaExpertForInteractionUse();
        registerMetaExpertForInteractionOperand();
        registerMetaExpertForInteraction();
        registerMetaExpertForGeneralOrdering();
        registerMetaExpertForGate();
        registerMetaExpertForExecutionSpecification();
        registerMetaExpertForExecutionOccurenceSpecification();
        registerMetaExpertForMessageEnd();
        registerMetaExpertForOccurrenceSpecification();
        registerMetaExpertForDurationConstraint();
        registerMetaExpertForConstraint();
        registerMetaExpertForCombinedFragment();
        registerMetaExpertForInteractionFragment();
        registerMetaExpertForCommunicationNode();
        registerMetaExpertForCommunicationMessage();
        registerMetaExpertForCommunicationInteraction();
        registerMetaExpertForCommunicationChannel();
        registerMetaExpertForSignal();
        registerMetaExpertForGeneralClass();
        registerMetaExpertForClassifier();
        registerMetaExpertForNameSpace();
        registerMetaExpertForModelTree();
        registerMetaExpertForOpaqueBehavior();
        registerMetaExpertForEvent();
        registerMetaExpertForBehaviorParameter();
        registerMetaExpertForParameter();
        registerMetaExpertForValuePin();
        registerMetaExpertForSendSignalAction();
        registerMetaExpertForOutputPin();
        registerMetaExpertForOpaqueAction();
        registerMetaExpertForObjectFlow();
        registerMetaExpertForMessageFlow();
        registerMetaExpertForLoopNode();
        registerMetaExpertForInterruptibleActivityRegion();
        registerMetaExpertForInstanceNode();
        registerMetaExpertForInputPin();
        registerMetaExpertForPin();
        registerMetaExpertForInitialNode();
        registerMetaExpertForForkJoinNode();
        registerMetaExpertForFlowFinalNode();
        registerMetaExpertForExpansionRegion();
        registerMetaExpertForExpansionNode();
        registerMetaExpertForExceptionHandler();
        registerMetaExpertForDecisionMergeNode();
        registerMetaExpertForDataStoreNode();
        registerMetaExpertForControlFlow();
        registerMetaExpertForConditionalNode();
        registerMetaExpertForStructuredActivityNode();
        registerMetaExpertForClause();
        registerMetaExpertForCentralBufferNode();
        registerMetaExpertForCallOperationAction();
        registerMetaExpertForCallBehaviorAction();
        registerMetaExpertForCallAction();
        registerMetaExpertForActivityPartition();
        registerMetaExpertForActivityParameterNode();
        registerMetaExpertForObjectNode();
        registerMetaExpertForActivityGroup();
        registerMetaExpertForActivityFinalNode();
        registerMetaExpertForFinalNode();
        registerMetaExpertForControlNode();
        registerMetaExpertForActivityEdge();
        registerMetaExpertForActivity();
        registerMetaExpertForAcceptTimeEventAction();
        registerMetaExpertForAcceptSignalAction();
        registerMetaExpertForAcceptChangeEventAction();
        registerMetaExpertForAcceptCallEventAction();
        registerMetaExpertForActivityAction();
        registerMetaExpertForActivityNode();
        registerMetaExpertForUseCaseDiagram();
        registerMetaExpertForStateMachineDiagram();
        registerMetaExpertForSequenceDiagram();
        registerMetaExpertForObjectDiagram();
        registerMetaExpertForDeploymentDiagram();
        registerMetaExpertForCompositeStructureDiagram();
        registerMetaExpertForCommunicationDiagram();
        registerMetaExpertForClassDiagram();
        registerMetaExpertForStaticDiagram();
        registerMetaExpertForActivityDiagram();
        registerMetaExpertForBpmnSharedDefinitions();
        registerMetaExpertForBpmnGroup();
        registerMetaExpertForBpmnAssociation();
        registerMetaExpertForBpmnArtifact();
        registerMetaExpertForBpmnResourceRole();
        registerMetaExpertForBpmnResourceParameterBinding();
        registerMetaExpertForBpmnResourceParameter();
        registerMetaExpertForBpmnResource();
        registerMetaExpertForBpmnProcess();
        registerMetaExpertForBpmnParticipant();
        registerMetaExpertForBpmnLaneSet();
        registerMetaExpertForBpmnLane();
        registerMetaExpertForBpmnCollaboration();
        registerMetaExpertForBehavior();
        registerMetaExpertForUmlModelElement();
        registerMetaExpertForBpmnSequenceFlowDataAssociation();
        registerMetaExpertForBpmnItemDefinition();
        registerMetaExpertForBpmnDataStore();
        registerMetaExpertForBpmnDataState();
        registerMetaExpertForBpmnDataOutput();
        registerMetaExpertForBpmnDataObject();
        registerMetaExpertForBpmnDataInput();
        registerMetaExpertForBpmnItemAwareElement();
        registerMetaExpertForBpmnDataAssociation();
        registerMetaExpertForBpmnParallelGateway();
        registerMetaExpertForBpmnInclusiveGateway();
        registerMetaExpertForBpmnExclusiveGateway();
        registerMetaExpertForBpmnEventBasedGateway();
        registerMetaExpertForBpmnComplexGateway();
        registerMetaExpertForBpmnGateway();
        registerMetaExpertForBpmnSequenceFlow();
        registerMetaExpertForBpmnMessageFlow();
        registerMetaExpertForBpmnMessage();
        registerMetaExpertForBpmnTimerEventDefinition();
        registerMetaExpertForBpmnTerminateEventDefinition();
        registerMetaExpertForBpmnStartEvent();
        registerMetaExpertForBpmnSignalEventDefinition();
        registerMetaExpertForBpmnMessageEventDefinition();
        registerMetaExpertForBpmnLinkEventDefinition();
        registerMetaExpertForBpmnIntermediateThrowEvent();
        registerMetaExpertForBpmnIntermediateCatchEvent();
        registerMetaExpertForBpmnImplicitThrowEvent();
        registerMetaExpertForBpmnEscalationEventDefinition();
        registerMetaExpertForBpmnErrorEventDefinition();
        registerMetaExpertForBpmnEndEvent();
        registerMetaExpertForBpmnThrowEvent();
        registerMetaExpertForBpmnConditionalEventDefinition();
        registerMetaExpertForBpmnCompensateEventDefinition();
        registerMetaExpertForBpmnCancelEventDefinition();
        registerMetaExpertForBpmnEventDefinition();
        registerMetaExpertForBpmnBoundaryEvent();
        registerMetaExpertForBpmnCatchEvent();
        registerMetaExpertForBpmnEvent();
        registerMetaExpertForBpmnOperation();
        registerMetaExpertForBpmnInterface();
        registerMetaExpertForBpmnEndPoint();
        registerMetaExpertForBpmnSharedElement();
        registerMetaExpertForBpmnCollaborationDiagram();
        registerMetaExpertForBpmnProcessDesignDiagram();
        registerMetaExpertForBpmnSubProcessDiagram();
        registerMetaExpertForBpmnProcessCollaborationDiagram();
        registerMetaExpertForBehaviorDiagram();
        registerMetaExpertForBpmnUserTask();
        registerMetaExpertForBpmnTransaction();
        registerMetaExpertForBpmnStandardLoopCharacteristics();
        registerMetaExpertForBpmnServiceTask();
        registerMetaExpertForBpmnSendTask();
        registerMetaExpertForBpmnScriptTask();
        registerMetaExpertForBpmnReceiveTask();
        registerMetaExpertForBpmnMultiInstanceLoopCharacteristics();
        registerMetaExpertForBpmnManualTask();
        registerMetaExpertForBpmnLoopCharacteristics();
        registerMetaExpertForBpmnComplexBehaviorDefinition();
        registerMetaExpertForBpmnCallActivity();
        registerMetaExpertForBpmnBusinessRuleTask();
        registerMetaExpertForBpmnTask();
        registerMetaExpertForBpmnAdHocSubProcess();
        registerMetaExpertForBpmnSubProcess();
        registerMetaExpertForBpmnActivity();
        registerMetaExpertForBpmnFlowNode();
        registerMetaExpertForBpmnFlowElement();
        registerMetaExpertForBpmnBaseElement();
    }

    @objid ("350c71c9-ec9d-46d1-96d0-4dfd2a22342c")
    protected void registerLinkExpertForBpmnMessageFlow() {
        // Standard.BpmnMessageFlow
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(BpmnMessageFlow.class);
        
        // Standard.BpmnMessageFlow sources and target dependencies
        this.ruleLinkExpert.addSourceDep(BpmnMessageFlow.class, "SourceRef");
        this.ruleLinkExpert.addTargetDep(BpmnMessageFlow.class, "TargetRef");
        
        
        // Standard.BpmnMessageFlow rules:
    }

    @objid ("1222dba5-663f-474f-9a46-45c1439d8705")
    protected void registerLinkExpertForBpmnSequenceFlow() {
        // Standard.BpmnSequenceFlow
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(BpmnSequenceFlow.class);
        
        // Standard.BpmnSequenceFlow sources and target dependencies
        this.ruleLinkExpert.addSourceDep(BpmnSequenceFlow.class, "SourceRef");
        this.ruleLinkExpert.addTargetDep(BpmnSequenceFlow.class, "TargetRef");
        
        
        // Standard.BpmnSequenceFlow rules:
    }

    @objid ("5348524c-d2b8-40df-ae86-859b154696d5")
    protected void registerLinkExpertForActivityEdge() {
        // Standard.ActivityEdge is abstract
        
        this.ruleLinkExpert.addLinkMetaclass(ActivityEdge.class);
    }

    @objid ("99e03216-934d-4138-b700-17f1b6fd07a3")
    protected void registerLinkExpertForCommunicationChannel() {
        // Standard.CommunicationChannel
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(CommunicationChannel.class);
        
        // Standard.CommunicationChannel sources and target dependencies
        this.ruleLinkExpert.addTargetDep(CommunicationChannel.class, "End");
        this.ruleLinkExpert.addSourceDep(CommunicationChannel.class, "Start");
        
        
        // Standard.CommunicationChannel rules:
    }

    @objid ("afb0280f-9a41-4c88-9ee9-58dd244a1ae1")
    protected void registerLinkExpertForMessage() {
        // Standard.Message
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(Message.class);
        
        // Standard.Message sources and target dependencies
        this.ruleLinkExpert.addTargetDep(Message.class, "ReceiveEvent");
        this.ruleLinkExpert.addSourceDep(Message.class, "SendEvent");
        
        
        // Standard.Message rules:
    }

    @objid ("4e529caf-bc0c-41b5-b16d-3f842c35e523")
    protected void registerLinkExpertForTransition() {
        // Standard.Transition
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(Transition.class);
        
        // Standard.Transition sources and target dependencies
        this.ruleLinkExpert.addSourceDep(Transition.class, "Source");
        this.ruleLinkExpert.addTargetDep(Transition.class, "Target");
        
        
        // Standard.Transition rules:
    }

    @objid ("adb1e689-b04e-4f82-a0bc-b1cb78830c11")
    protected void registerLinkExpertForUseCaseDependency() {
        // Standard.UseCaseDependency
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(UseCaseDependency.class);
        
        // Standard.UseCaseDependency sources and target dependencies
        this.ruleLinkExpert.addSourceDep(UseCaseDependency.class, "Origin");
        this.ruleLinkExpert.addTargetDep(UseCaseDependency.class, "Target");
        
        
        // Standard.UseCaseDependency rules:
    }

    @objid ("04a46798-21f5-476b-9ebd-fde8b1111f27")
    protected void registerLinkExpertForDataFlow() {
        // Standard.DataFlow
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(DataFlow.class);
        
        // Standard.DataFlow sources and target dependencies
        this.ruleLinkExpert.addTargetDep(DataFlow.class, "Destination");
        this.ruleLinkExpert.addSourceDep(DataFlow.class, "Origin");
        
        
        // Standard.DataFlow rules:
    }

    @objid ("46c006fb-12e2-4349-b336-414af2d6785e")
    protected void registerLinkExpertForInformationFlow() {
        // Standard.InformationFlow
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(InformationFlow.class);
        
        // Standard.InformationFlow sources and target dependencies
        this.ruleLinkExpert.addSourceDep(InformationFlow.class, "InformationSource");
        this.ruleLinkExpert.addTargetDep(InformationFlow.class, "InformationTarget");
        
        
        // Standard.InformationFlow rules:
    }

    @objid ("38810226-9817-4cc4-a128-d28741654412")
    protected void registerLinkExpertForAbstraction() {
        // Standard.Abstraction
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(Abstraction.class);
        
        // Standard.Abstraction sources and target dependencies
        this.ruleLinkExpert.addTargetDep(Abstraction.class, "DependsOn");
        this.ruleLinkExpert.addSourceDep(Abstraction.class, "Impacted");
        
        
        // Standard.Abstraction rules:
    }

    @objid ("f1a2ea3a-376f-45f8-9fe2-76d06cf6d803")
    protected void registerLinkExpertForSubstitution() {
        // Standard.Substitution
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(Substitution.class);
        
        // Standard.Substitution sources and target dependencies
        this.ruleLinkExpert.addTargetDep(Substitution.class, "Contract");
        this.ruleLinkExpert.addSourceDep(Substitution.class, "SubstitutingClassifier");
        
        
        // Standard.Substitution rules:
    }

    @objid ("c17844ff-8295-40da-9746-da571270d8ed")
    protected void registerLinkExpertForUsage() {
        // Standard.Usage
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(Usage.class);
        
        // Standard.Usage sources and target dependencies
        this.ruleLinkExpert.addTargetDep(Usage.class, "DependsOn");
        this.ruleLinkExpert.addSourceDep(Usage.class, "Impacted");
        
        
        // Standard.Usage rules:
    }

    @objid ("7737d20f-68d1-4b63-9a27-b7d84c3c744f")
    protected void registerLinkExpertForAssociation() {
        // Standard.Association
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(Association.class);
        
        // Standard.Association sources and target dependencies
        
        
        // Standard.Association rules:
    }

    @objid ("3181e7c4-9137-4e75-b07a-02328c51431d")
    protected void registerLinkExpertForAssociationEnd() {
        // Standard.AssociationEnd
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(AssociationEnd.class);
        
        // Standard.AssociationEnd sources and target dependencies
        this.ruleLinkExpert.addSourceDep(AssociationEnd.class, "Source");
        this.ruleLinkExpert.addTargetDep(AssociationEnd.class, "Target");
        
        
        // Standard.AssociationEnd rules:
    }

    @objid ("e08a2a27-d75a-4bc4-b9d0-d73b4569630e")
    protected void registerLinkExpertForElementImport() {
        // Standard.ElementImport
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(ElementImport.class);
        
        // Standard.ElementImport sources and target dependencies
        this.ruleLinkExpert.addTargetDep(ElementImport.class, "ImportedElement");
        this.ruleLinkExpert.addSourceDep(ElementImport.class, "ImportingNameSpace");
        this.ruleLinkExpert.addSourceDep(ElementImport.class, "ImportingOperation");
        
        
        // Standard.ElementImport rules:
    }

    @objid ("bbb93efd-2767-455d-9197-6d4e972f5b0d")
    protected void registerLinkExpertForGeneralization() {
        // Standard.Generalization
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(Generalization.class);
        
        // Standard.Generalization sources and target dependencies
        this.ruleLinkExpert.addSourceDep(Generalization.class, "SubType");
        this.ruleLinkExpert.addTargetDep(Generalization.class, "SuperType");
        
        
        // Standard.Generalization rules:
    }

    @objid ("2f7467b0-a16f-4e29-9898-a366e85b50f4")
    protected void registerLinkExpertForInterfaceRealization() {
        // Standard.InterfaceRealization
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(InterfaceRealization.class);
        
        // Standard.InterfaceRealization sources and target dependencies
        this.ruleLinkExpert.addTargetDep(InterfaceRealization.class, "Implemented");
        this.ruleLinkExpert.addSourceDep(InterfaceRealization.class, "Implementer");
        
        
        // Standard.InterfaceRealization rules:
    }

    @objid ("306c1752-629b-419a-9d05-5bfa3344ac41")
    protected void registerLinkExpertForLink() {
        // Standard.Link
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(Link.class);
        
        // Standard.Link sources and target dependencies
        
        
        // Standard.Link rules:
    }

    @objid ("88e3f7ae-7071-4e66-8439-f84090952270")
    protected void registerLinkExpertForLinkEnd() {
        // Standard.LinkEnd
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(LinkEnd.class);
        
        // Standard.LinkEnd sources and target dependencies
        this.ruleLinkExpert.addSourceDep(LinkEnd.class, "Source");
        this.ruleLinkExpert.addTargetDep(LinkEnd.class, "Target");
        
        
        // Standard.LinkEnd rules:
    }

    @objid ("0476b105-1ac7-4487-94c8-b7e88e4aa245")
    protected void registerLinkExpertForManifestation() {
        // Standard.Manifestation
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(Manifestation.class);
        
        // Standard.Manifestation sources and target dependencies
        this.ruleLinkExpert.addSourceDep(Manifestation.class, "Owner");
        this.ruleLinkExpert.addTargetDep(Manifestation.class, "UtilizedElement");
        
        
        // Standard.Manifestation rules:
    }

    @objid ("698579b2-b94e-4218-9c3f-62c3ae3bc288")
    protected void registerLinkExpertForPackageImport() {
        // Standard.PackageImport
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(PackageImport.class);
        
        // Standard.PackageImport sources and target dependencies
        this.ruleLinkExpert.addTargetDep(PackageImport.class, "ImportedPackage");
        this.ruleLinkExpert.addSourceDep(PackageImport.class, "ImportingNameSpace");
        this.ruleLinkExpert.addSourceDep(PackageImport.class, "ImportingOperation");
        
        
        // Standard.PackageImport rules:
    }

    @objid ("5f5c411d-8939-4c5d-acfe-38b96a9e9cd9")
    protected void registerLinkExpertForPackageMerge() {
        // Standard.PackageMerge
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(PackageMerge.class);
        
        // Standard.PackageMerge sources and target dependencies
        this.ruleLinkExpert.addTargetDep(PackageMerge.class, "MergedPackage");
        this.ruleLinkExpert.addSourceDep(PackageMerge.class, "ReceivingPackage");
        
        
        // Standard.PackageMerge rules:
    }

    @objid ("824bf648-b5c6-40ed-8ef4-eee7ee985b23")
    protected void registerLinkExpertForRaisedException() {
        // Standard.RaisedException
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(RaisedException.class);
        
        // Standard.RaisedException sources and target dependencies
        this.ruleLinkExpert.addSourceDep(RaisedException.class, "Thrower");
        this.ruleLinkExpert.addTargetDep(RaisedException.class, "ThrownType");
        
        
        // Standard.RaisedException rules:
    }

    @objid ("8f1169ca-1260-451f-94e3-21cdbf034d96")
    protected void registerLinkExpertForTemplateBinding() {
        // Standard.TemplateBinding
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(TemplateBinding.class);
        
        // Standard.TemplateBinding sources and target dependencies
        this.ruleLinkExpert.addSourceDep(TemplateBinding.class, "BoundElement");
        this.ruleLinkExpert.addSourceDep(TemplateBinding.class, "BoundOperation");
        this.ruleLinkExpert.addTargetDep(TemplateBinding.class, "InstanciatedTemplate");
        this.ruleLinkExpert.addTargetDep(TemplateBinding.class, "InstanciatedTemplateOperation");
        
        
        // Standard.TemplateBinding rules:
    }

    @objid ("c540a0b6-d951-478c-8195-877d47c4f991")
    protected void registerLinkExpertForControlFlow() {
        // Standard.ControlFlow
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(ControlFlow.class);
        
        // Standard.ControlFlow sources and target dependencies
        this.ruleLinkExpert.addSourceDep(ControlFlow.class, "Source");
        this.ruleLinkExpert.addTargetDep(ControlFlow.class, "Target");
        
        
        // Standard.ControlFlow rules:
    }

    @objid ("30aa177a-5a39-40a3-bdfe-fce9fb8f054a")
    protected void registerLinkExpertForMessageFlow() {
        // Standard.MessageFlow
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(MessageFlow.class);
        
        // Standard.MessageFlow sources and target dependencies
        this.ruleLinkExpert.addSourceDep(MessageFlow.class, "Source");
        this.ruleLinkExpert.addSourceDep(MessageFlow.class, "SourcePartition");
        this.ruleLinkExpert.addTargetDep(MessageFlow.class, "Target");
        this.ruleLinkExpert.addTargetDep(MessageFlow.class, "TargetPartition");
        
        
        // Standard.MessageFlow rules:
    }

    @objid ("3bc9aac0-9fcd-48d5-b6e0-4cbe03c6ecc7")
    protected void registerLinkExpertForObjectFlow() {
        // Standard.ObjectFlow
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(ObjectFlow.class);
        
        // Standard.ObjectFlow sources and target dependencies
        this.ruleLinkExpert.addSourceDep(ObjectFlow.class, "Source");
        this.ruleLinkExpert.addTargetDep(ObjectFlow.class, "Target");
        
        
        // Standard.ObjectFlow rules:
    }

    @objid ("51180f25-6fb3-483c-a85b-331eec466622")
    protected void registerLinkExpertForElementRealization() {
        // Standard.ElementRealization
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(ElementRealization.class);
        
        // Standard.ElementRealization sources and target dependencies
        this.ruleLinkExpert.addTargetDep(ElementRealization.class, "DependsOn");
        this.ruleLinkExpert.addSourceDep(ElementRealization.class, "Impacted");
        
        
        // Standard.ElementRealization rules:
    }

    @objid ("2104997c-1fef-4eec-9e7d-d87624f69a64")
    protected void registerLinkExpertForConnector() {
        // Standard.Connector
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(Connector.class);
        
        // Standard.Connector sources and target dependencies
        
        
        // Standard.Connector rules:
    }

    @objid ("fe87938d-3767-40fb-a9a1-9728218b6108")
    protected void registerLinkExpertForConnectorEnd() {
        // Standard.ConnectorEnd
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(ConnectorEnd.class);
        
        // Standard.ConnectorEnd sources and target dependencies
        this.ruleLinkExpert.addSourceDep(ConnectorEnd.class, "Source");
        this.ruleLinkExpert.addTargetDep(ConnectorEnd.class, "Target");
        
        
        // Standard.ConnectorEnd rules:
    }

    @objid ("8f7f10b3-a75b-4ac4-896e-d57558e953dd")
    protected void registerMetaExpertForProject() {
        // Standard.Project
        // -----------
        
        // no constraint on Project.Model : Package from Standard.Project to Standard.Package
        this.ruleMetaExpert.addDependencyRule(Project.class, null, "Model");
    }

    @objid ("15f96b0f-cc17-408e-a678-6a0e1cfc8c11")
    protected void registerMetaExpertForTemplateParameterSubstitution() {
        // Standard.TemplateParameterSubstitution
        // -----------
        
        // no constraint on TemplateParameterSubstitution.Actual : UmlModelElement from Standard.TemplateParameterSubstitution to Standard.UmlModelElement
        this.ruleMetaExpert.addDependencyRule(TemplateParameterSubstitution.class, null, "Actual");
        
        // no constraint on TemplateParameterSubstitution.FormalParameter : TemplateParameter from Standard.TemplateParameterSubstitution to Standard.TemplateParameter
        this.ruleMetaExpert.addDependencyRule(TemplateParameterSubstitution.class, null, "FormalParameter");
    }

    @objid ("3f081138-fe7e-4d0b-b060-8d025cd710a4")
    protected void registerMetaExpertForTemplateParameter() {
        // Standard.TemplateParameter
        // -----------
        
        // no constraint on TemplateParameter.Type : UmlModelElement from Standard.TemplateParameter to Standard.UmlModelElement
        this.ruleMetaExpert.addDependencyRule(TemplateParameter.class, null, "Type");
        
        // no constraint on TemplateParameter.OwnedParameterElement : UmlModelElement from Standard.TemplateParameter to Standard.UmlModelElement
        this.ruleMetaExpert.addDependencyRule(TemplateParameter.class, null, "OwnedParameterElement");
        
        // no constraint on TemplateParameter.DefaultType : UmlModelElement from Standard.TemplateParameter to Standard.UmlModelElement
        this.ruleMetaExpert.addDependencyRule(TemplateParameter.class, null, "DefaultType");
    }

    @objid ("375b9c77-9d32-4519-ad38-fae28670ee40")
    protected void registerMetaExpertForTemplateBinding() {
        // Standard.TemplateBinding
        // -----------
        
        // no constraint on TemplateBinding.ParameterSubstitution : TemplateParameterSubstitution from Standard.TemplateBinding to Standard.TemplateParameterSubstitution
        this.ruleMetaExpert.addDependencyRule(TemplateBinding.class, null, "ParameterSubstitution");
        
        // no constraint on TemplateBinding.InstanciatedTemplateOperation : Operation from Standard.TemplateBinding to Standard.Operation
        this.ruleMetaExpert.addDependencyRule(TemplateBinding.class, null, "InstanciatedTemplateOperation");
        
        // no constraint on TemplateBinding.InstanciatedTemplate : NameSpace from Standard.TemplateBinding to Standard.NameSpace
        this.ruleMetaExpert.addDependencyRule(TemplateBinding.class, null, "InstanciatedTemplate");
    }

    @objid ("ade260b2-087d-4a42-8d7a-135883ef89f2")
    protected void registerMetaExpertForRequiredInterface() {
        // Standard.RequiredInterface
        // -----------
        
        // no constraint on RequiredInterface.RequiredElement : Interface from Standard.RequiredInterface to Standard.Interface
        this.ruleMetaExpert.addDependencyRule(RequiredInterface.class, null, "RequiredElement");
    }

    @objid ("4b52a5cb-42a2-47dd-988d-3918f06f97a4")
    protected void registerMetaExpertForRaisedException() {
        // Standard.RaisedException
        // -----------
        
        // no constraint on RaisedException.ThrownType : Classifier from Standard.RaisedException to Standard.Classifier
        this.ruleMetaExpert.addDependencyRule(RaisedException.class, null, "ThrownType");
    }

    @objid ("9df573c5-33ec-4873-ab63-e10943ecd184")
    protected void registerMetaExpertForProvidedInterface() {
        // Standard.ProvidedInterface
        // -----------
        
        // no constraint on ProvidedInterface.ProvidedElement : Interface from Standard.ProvidedInterface to Standard.Interface
        this.ruleMetaExpert.addDependencyRule(ProvidedInterface.class, null, "ProvidedElement");
    }

    @objid ("ced73f35-91c8-407c-a3b7-e2d9d13eb57d")
    protected void registerMetaExpertForPort() {
        // Standard.Port
        // -----------
        
        // no constraint on Port.Provided : ProvidedInterface from Standard.Port to Standard.ProvidedInterface
        this.ruleMetaExpert.addDependencyRule(Port.class, null, "Provided");
        
        // no constraint on Port.Required : RequiredInterface from Standard.Port to Standard.RequiredInterface
        this.ruleMetaExpert.addDependencyRule(Port.class, null, "Required");
    }

    @objid ("0a926d65-a46c-46d0-b091-2a8a40e38677")
    protected void registerMetaExpertForPackageMerge() {
        // Standard.PackageMerge
        // -----------
        
        // no constraint on PackageMerge.MergedPackage : Package from Standard.PackageMerge to Standard.Package
        this.ruleMetaExpert.addDependencyRule(PackageMerge.class, null, "MergedPackage");
    }

    @objid ("10e9e30a-330f-45dc-9bba-9dc701b504fe")
    protected void registerMetaExpertForPackageImport() {
        // Standard.PackageImport
        // -----------
        
        // no constraint on PackageImport.ImportedPackage : Package from Standard.PackageImport to Standard.Package
        this.ruleMetaExpert.addDependencyRule(PackageImport.class, null, "ImportedPackage");
    }

    @objid ("9ffb2f2e-ca39-4bc1-9ed5-4a79c59bdef1")
    protected void registerMetaExpertForPackage() {
        // Standard.Package
        // -----------
        
        // no constraint on Package.Merge : PackageMerge from Standard.Package to Standard.PackageMerge
        this.ruleMetaExpert.addDependencyRule(Package.class, null, "Merge");
    }

    @objid ("d904b7d3-629b-4f42-84d9-74237ec7f237")
    protected void registerMetaExpertForOperation() {
        // Standard.Operation
        // -----------
        
        // no constraint on Operation.OwnedImport : ElementImport from Standard.Operation to Standard.ElementImport
        this.ruleMetaExpert.addDependencyRule(Operation.class, null, "OwnedImport");
        
        // no constraint on Operation.Thrown : RaisedException from Standard.Operation to Standard.RaisedException
        this.ruleMetaExpert.addDependencyRule(Operation.class, null, "Thrown");
        
        // no constraint on Operation.Example : Collaboration from Standard.Operation to Standard.Collaboration
        this.ruleMetaExpert.addDependencyRule(Operation.class, null, "Example");
        
        // no constraint on Operation.OwnedBehavior : Behavior from Standard.Operation to Standard.Behavior
        this.ruleMetaExpert.addDependencyRule(Operation.class, null, "OwnedBehavior");
        
        // no constraint on Operation.IO : Parameter from Standard.Operation to Standard.Parameter
        this.ruleMetaExpert.addDependencyRule(Operation.class, null, "IO");
        
        // no constraint on Operation.TemplateInstanciation : TemplateBinding from Standard.Operation to Standard.TemplateBinding
        this.ruleMetaExpert.addDependencyRule(Operation.class, null, "TemplateInstanciation");
        
        // no constraint on Operation.OwnedPackageImport : PackageImport from Standard.Operation to Standard.PackageImport
        this.ruleMetaExpert.addDependencyRule(Operation.class, null, "OwnedPackageImport");
        
        // no constraint on Operation.Return : Parameter from Standard.Operation to Standard.Parameter
        this.ruleMetaExpert.addDependencyRule(Operation.class, null, "Return");
        
        // no constraint on Operation.Template : TemplateParameter from Standard.Operation to Standard.TemplateParameter
        this.ruleMetaExpert.addDependencyRule(Operation.class, null, "Template");
        
        // no constraint on Operation.OwnedCollaborationUse : CollaborationUse from Standard.Operation to Standard.CollaborationUse
        this.ruleMetaExpert.addDependencyRule(Operation.class, null, "OwnedCollaborationUse");
        
        // no constraint on Operation.Redefines : Operation from Standard.Operation to Standard.Operation
        this.ruleMetaExpert.addDependencyRule(Operation.class, null, "Redefines");
    }

    @objid ("60f70a30-2b0b-4bab-91cf-66d05491d02e")
    protected void registerMetaExpertForNode() {
        // Standard.Node
        // -----------
        
        // no constraint on Node.Resident : Artifact from Standard.Node to Standard.Artifact
        this.ruleMetaExpert.addDependencyRule(Node.class, null, "Resident");
    }

    @objid ("32d30fb3-86b6-46dc-b85d-2714adaad879")
    protected void registerMetaExpertForNaryConnectorEnd() {
        // Standard.NaryConnectorEnd
        // -----------
    }

    @objid ("62842df9-9de9-4784-a9fd-3869a9ffd414")
    protected void registerMetaExpertForNaryLinkEnd() {
        // Standard.NaryLinkEnd
        // -----------
        
        // no constraint on NaryLinkEnd.NaryLink : NaryLink from Standard.NaryLinkEnd to Standard.NaryLink
        this.ruleMetaExpert.addDependencyRule(NaryConnectorEnd.class, null, "NaryLink");
        this.ruleMetaExpert.addDependencyRule(NaryLinkEnd.class, null, "NaryLink");
        
        // no constraint on NaryLinkEnd.Consumer : RequiredInterface from Standard.NaryLinkEnd to Standard.RequiredInterface
        this.ruleMetaExpert.addDependencyRule(NaryConnectorEnd.class, null, "Consumer");
        this.ruleMetaExpert.addDependencyRule(NaryLinkEnd.class, null, "Consumer");
        
        // no constraint on NaryLinkEnd.Provider : ProvidedInterface from Standard.NaryLinkEnd to Standard.ProvidedInterface
        this.ruleMetaExpert.addDependencyRule(NaryConnectorEnd.class, null, "Provider");
        this.ruleMetaExpert.addDependencyRule(NaryLinkEnd.class, null, "Provider");
    }

    @objid ("df69b55c-604b-46bc-bfc8-a587ae4f09ca")
    protected void registerMetaExpertForNaryConnector() {
        // Standard.NaryConnector
        // -----------
        
        // no constraint on NaryConnector.RepresentedFeature : UmlModelElement from Standard.NaryConnector to Standard.UmlModelElement
        this.ruleMetaExpert.addDependencyRule(NaryConnector.class, null, "RepresentedFeature");
    }

    @objid ("8443acb6-e540-4e43-b586-62d951d76fcf")
    protected void registerMetaExpertForNaryLink() {
        // Standard.NaryLink
        // -----------
        
        // no constraint on NaryLink.NaryLinkEnd : NaryLinkEnd from Standard.NaryLink to Standard.NaryLinkEnd
        this.ruleMetaExpert.addDependencyRule(NaryConnector.class, null, "NaryLinkEnd");
        this.ruleMetaExpert.addDependencyRule(NaryLink.class, null, "NaryLinkEnd");
        
        // no constraint on NaryLink.Model : NaryAssociation from Standard.NaryLink to Standard.NaryAssociation
        this.ruleMetaExpert.addDependencyRule(NaryConnector.class, null, "Model");
        this.ruleMetaExpert.addDependencyRule(NaryLink.class, null, "Model");
    }

    @objid ("ccd92c99-2a2e-42f1-9751-f849bfa48023")
    protected void registerMetaExpertForNaryAssociationEnd() {
        // Standard.NaryAssociationEnd
        // -----------
        
        // no constraint on NaryAssociationEnd.NaryAssociation : NaryAssociation from Standard.NaryAssociationEnd to Standard.NaryAssociation
        this.ruleMetaExpert.addDependencyRule(NaryAssociationEnd.class, null, "NaryAssociation");
    }

    @objid ("2b1de829-452c-4a8f-ad3e-c1f6fe630806")
    protected void registerMetaExpertForNaryAssociation() {
        // Standard.NaryAssociation
        // -----------
        
        // no constraint on NaryAssociation.NaryEnd : NaryAssociationEnd from Standard.NaryAssociation to Standard.NaryAssociationEnd
        this.ruleMetaExpert.addDependencyRule(NaryAssociation.class, null, "NaryEnd");
        
        // no constraint on NaryAssociation.LinkToClass : ClassAssociation from Standard.NaryAssociation to Standard.ClassAssociation
        this.ruleMetaExpert.addDependencyRule(NaryAssociation.class, null, "LinkToClass");
    }

    @objid ("958c4486-d9ae-4a46-a6d1-9e9709f8e595")
    protected void registerMetaExpertForManifestation() {
        // Standard.Manifestation
        // -----------
        
        // no constraint on Manifestation.UtilizedElement : UmlModelElement from Standard.Manifestation to Standard.UmlModelElement
        this.ruleMetaExpert.addDependencyRule(Manifestation.class, null, "UtilizedElement");
    }

    @objid ("46b657b9-8ead-4c96-8ff0-d2814deeed9c")
    protected void registerMetaExpertForInterfaceRealization() {
        // Standard.InterfaceRealization
        // -----------
        
        // no constraint on InterfaceRealization.Implemented : Interface from Standard.InterfaceRealization to Standard.Interface
        this.ruleMetaExpert.addDependencyRule(InterfaceRealization.class, null, "Implemented");
    }

    @objid ("db6040d2-1e7a-4afd-b2df-d5c2f05330a3")
    protected void registerMetaExpertForInterface() {
        // Standard.Interface
        // -----------
    }

    @objid ("3da18146-4453-4984-9dc9-cd6516319c0b")
    protected void registerMetaExpertForGeneralization() {
        // Standard.Generalization
        // -----------
        
        // no constraint on Generalization.SuperType : NameSpace from Standard.Generalization to Standard.NameSpace
        this.ruleMetaExpert.addDependencyRule(Generalization.class, null, "SuperType");
    }

    @objid ("ef11675d-1e98-4c4e-a0ed-16d426978b1c")
    protected void registerMetaExpertForEnumerationLiteral() {
        // Standard.EnumerationLiteral
        // -----------
    }

    @objid ("74ea1d01-5f97-4cf2-bcc9-c90686bf751f")
    protected void registerMetaExpertForEnumeration() {
        // Standard.Enumeration
        // -----------
        
        // no constraint on Enumeration.Value : EnumerationLiteral from Standard.Enumeration to Standard.EnumerationLiteral
        this.ruleMetaExpert.addDependencyRule(Enumeration.class, null, "Value");
    }

    @objid ("996867a0-606a-4eba-9596-8c9c3e6a59cc")
    protected void registerMetaExpertForElementRealization() {
        // Standard.ElementRealization
        // -----------
    }

    @objid ("c889a01d-4970-4e09-86db-2b65a1ae3daf")
    protected void registerMetaExpertForElementImport() {
        // Standard.ElementImport
        // -----------
        
        // no constraint on ElementImport.ImportedElement : NameSpace from Standard.ElementImport to Standard.NameSpace
        this.ruleMetaExpert.addDependencyRule(ElementImport.class, null, "ImportedElement");
    }

    @objid ("3e641f9d-e8e2-4e0a-9cbd-5bff85c8d0d8")
    protected void registerMetaExpertForDataType() {
        // Standard.DataType
        // -----------
    }

    @objid ("f56ab84f-a6bb-4a56-9284-edd2162f7911")
    protected void registerMetaExpertForConnectorEnd() {
        // Standard.ConnectorEnd
        // -----------
        
        // no constraint on ConnectorEnd.RepresentedFeature : UmlModelElement from Standard.ConnectorEnd to Standard.UmlModelElement
        this.ruleMetaExpert.addDependencyRule(ConnectorEnd.class, null, "RepresentedFeature");
    }

    @objid ("d11d8105-8972-4152-843b-3cdfeeda6872")
    protected void registerMetaExpertForLinkEnd() {
        // Standard.LinkEnd
        // -----------
        
        // no constraint on LinkEnd.Link : Link from Standard.LinkEnd to Standard.Link
        this.ruleMetaExpert.addDependencyRule(ConnectorEnd.class, null, "Link");
        this.ruleMetaExpert.addDependencyRule(LinkEnd.class, null, "Link");
        
        // no constraint on LinkEnd.Target : Instance from Standard.LinkEnd to Standard.Instance
        this.ruleMetaExpert.addDependencyRule(ConnectorEnd.class, null, "Target");
        this.ruleMetaExpert.addDependencyRule(LinkEnd.class, null, "Target");
        
        // no constraint on LinkEnd.Model : AssociationEnd from Standard.LinkEnd to Standard.AssociationEnd
        this.ruleMetaExpert.addDependencyRule(ConnectorEnd.class, null, "Model");
        this.ruleMetaExpert.addDependencyRule(LinkEnd.class, null, "Model");
        
        // no constraint on LinkEnd.Consumer : RequiredInterface from Standard.LinkEnd to Standard.RequiredInterface
        this.ruleMetaExpert.addDependencyRule(ConnectorEnd.class, null, "Consumer");
        this.ruleMetaExpert.addDependencyRule(LinkEnd.class, null, "Consumer");
        
        // no constraint on LinkEnd.Opposite : LinkEnd from Standard.LinkEnd to Standard.LinkEnd
        this.ruleMetaExpert.addDependencyRule(ConnectorEnd.class, null, "Opposite");
        this.ruleMetaExpert.addDependencyRule(LinkEnd.class, null, "Opposite");
        
        // no constraint on LinkEnd.Source : Instance from Standard.LinkEnd to Standard.Instance
        this.ruleMetaExpert.addDependencyRule(ConnectorEnd.class, null, "Source");
        this.ruleMetaExpert.addDependencyRule(LinkEnd.class, null, "Source");
        
        // no constraint on LinkEnd.Provider : ProvidedInterface from Standard.LinkEnd to Standard.ProvidedInterface
        this.ruleMetaExpert.addDependencyRule(ConnectorEnd.class, null, "Provider");
        this.ruleMetaExpert.addDependencyRule(LinkEnd.class, null, "Provider");
    }

    @objid ("be31efc3-9c49-4251-a596-28a7289378ab")
    protected void registerMetaExpertForConnector() {
        // Standard.Connector
        // -----------
    }

    @objid ("b7bcf3c7-37c8-4301-a969-e39ceccab62d")
    protected void registerMetaExpertForLink() {
        // Standard.Link
        // -----------
        
        // no constraint on Link.Model : Association from Standard.Link to Standard.Association
        this.ruleMetaExpert.addDependencyRule(Connector.class, null, "Model");
        this.ruleMetaExpert.addDependencyRule(Link.class, null, "Model");
    }

    @objid ("e50810a7-dbf9-45e4-876d-d9bec573f05f")
    protected void registerMetaExpertForComponentRealization() {
        // Standard.ComponentRealization
        // -----------
        
        // no constraint on ComponentRealization.RealizingClassifier : Classifier from Standard.ComponentRealization to Standard.Classifier
        this.ruleMetaExpert.addDependencyRule(ComponentRealization.class, null, "RealizingClassifier");
    }

    @objid ("2bb67618-8310-4873-bb18-004c0db5c6df")
    protected void registerMetaExpertForComponent() {
        // Standard.Component
        // -----------
        
        // no constraint on Component.Realization : ComponentRealization from Standard.Component to Standard.ComponentRealization
        this.ruleMetaExpert.addDependencyRule(Component.class, null, "Realization");
    }

    @objid ("637a6abf-3a4e-4a54-a573-247bd8f37096")
    protected void registerMetaExpertForCollaborationUse() {
        // Standard.CollaborationUse
        // -----------
        
        // no constraint on CollaborationUse.Type : Collaboration from Standard.CollaborationUse to Standard.Collaboration
        this.ruleMetaExpert.addDependencyRule(CollaborationUse.class, null, "Type");
        
        // no constraint on CollaborationUse.RoleBinding : Binding from Standard.CollaborationUse to Standard.Binding
        this.ruleMetaExpert.addDependencyRule(CollaborationUse.class, null, "RoleBinding");
    }

    @objid ("dc601058-e746-463e-8b34-e9e333c23514")
    protected void registerMetaExpertForCollaboration() {
        // Standard.Collaboration
        // -----------
    }

    @objid ("8a3248f1-87db-41da-910f-1d6923e22c97")
    protected void registerMetaExpertForClassAssociation() {
        // Standard.ClassAssociation
        // -----------
        
        // no constraint on ClassAssociation.ClassPart : Class from Standard.ClassAssociation to Standard.Class
        this.ruleMetaExpert.addDependencyRule(ClassAssociation.class, null, "ClassPart");
    }

    @objid ("09e9462a-471b-4cb8-843d-063ee18cb065")
    protected void registerMetaExpertForClass() {
        // Standard.Class
        // -----------
    }

    @objid ("e1647114-3d01-4e91-b6a2-b3ec7793b6ca")
    protected void registerMetaExpertForBinding() {
        // Standard.Binding
        // -----------
        
        // no constraint on Binding.ConnectorEndRole : ConnectorEnd from Standard.Binding to Standard.ConnectorEnd
        this.ruleMetaExpert.addDependencyRule(Binding.class, null, "ConnectorEndRole");
        
        // no constraint on Binding.ConnectorRole : NaryConnector from Standard.Binding to Standard.NaryConnector
        this.ruleMetaExpert.addDependencyRule(Binding.class, null, "ConnectorRole");
        
        // no constraint on Binding.Role : BindableInstance from Standard.Binding to Standard.BindableInstance
        this.ruleMetaExpert.addDependencyRule(Binding.class, null, "Role");
        
        // no constraint on Binding.RepresentedFeature : UmlModelElement from Standard.Binding to Standard.UmlModelElement
        this.ruleMetaExpert.addDependencyRule(Binding.class, null, "RepresentedFeature");
    }

    @objid ("31e5d2b0-c8d6-4412-abf9-deafc576a642")
    protected void registerMetaExpertForBindableInstance() {
        // Standard.BindableInstance
        // -----------
        
        // no constraint on BindableInstance.RepresentedFeature : UmlModelElement from Standard.BindableInstance to Standard.UmlModelElement
        this.ruleMetaExpert.addDependencyRule(BindableInstance.class, null, "RepresentedFeature");
        this.ruleMetaExpert.addDependencyRule(Port.class, null, "RepresentedFeature");
    }

    @objid ("235af248-e0ad-4909-82bb-12233f10b30c")
    protected void registerMetaExpertForInstance() {
        // Standard.Instance
        // -----------
        
        // no constraint on Instance.OwnedEnd : LinkEnd from Standard.Instance to Standard.LinkEnd
        this.ruleMetaExpert.addDependencyRule(BindableInstance.class, null, "OwnedEnd");
        this.ruleMetaExpert.addDependencyRule(Instance.class, null, "OwnedEnd");
        this.ruleMetaExpert.addDependencyRule(Port.class, null, "OwnedEnd");
        
        // no constraint on Instance.Base : NameSpace from Standard.Instance to Standard.NameSpace
        this.ruleMetaExpert.addDependencyRule(BindableInstance.class, null, "Base");
        this.ruleMetaExpert.addDependencyRule(Instance.class, null, "Base");
        this.ruleMetaExpert.addDependencyRule(Port.class, null, "Base");
        
        // no constraint on Instance.OwnedNaryEnd : NaryLinkEnd from Standard.Instance to Standard.NaryLinkEnd
        this.ruleMetaExpert.addDependencyRule(BindableInstance.class, null, "OwnedNaryEnd");
        this.ruleMetaExpert.addDependencyRule(Instance.class, null, "OwnedNaryEnd");
        this.ruleMetaExpert.addDependencyRule(Port.class, null, "OwnedNaryEnd");
        
        // no constraint on Instance.Slot : AttributeLink from Standard.Instance to Standard.AttributeLink
        this.ruleMetaExpert.addDependencyRule(BindableInstance.class, null, "Slot");
        this.ruleMetaExpert.addDependencyRule(Instance.class, null, "Slot");
        this.ruleMetaExpert.addDependencyRule(Port.class, null, "Slot");
        
        // no constraint on Instance.Part : BindableInstance from Standard.Instance to Standard.BindableInstance
        this.ruleMetaExpert.addDependencyRule(BindableInstance.class, null, "Part");
        this.ruleMetaExpert.addDependencyRule(Instance.class, null, "Part");
        this.ruleMetaExpert.addDependencyRule(Port.class, null, "Part");
    }

    @objid ("88f55bd0-d017-48bd-9aaa-5b6303b634da")
    protected void registerMetaExpertForBehavioralFeature() {
        // Standard.BehavioralFeature
        // -----------
    }

    @objid ("1afabd97-d25f-4dcf-8f7f-6e5b27751d4c")
    protected void registerMetaExpertForAttributeLink() {
        // Standard.AttributeLink
        // -----------
        
        // no constraint on AttributeLink.Base : Attribute from Standard.AttributeLink to Standard.Attribute
        this.ruleMetaExpert.addDependencyRule(AttributeLink.class, null, "Base");
    }

    @objid ("746d01f8-c3c9-4bbe-a3a5-f637ad1dd429")
    protected void registerMetaExpertForAttribute() {
        // Standard.Attribute
        // -----------
        
        // no constraint on Attribute.Type : GeneralClass from Standard.Attribute to Standard.GeneralClass
        this.ruleMetaExpert.addDependencyRule(Attribute.class, null, "Type");
    }

    @objid ("44ab2fec-b0e9-4b96-b3d2-db5a7d95707f")
    protected void registerMetaExpertForAssociationEnd() {
        // Standard.AssociationEnd
        // -----------
        
        // no constraint on AssociationEnd.Target : Classifier from Standard.AssociationEnd to Standard.Classifier
        this.ruleMetaExpert.addDependencyRule(AssociationEnd.class, null, "Target");
        
        // no constraint on AssociationEnd.Source : Classifier from Standard.AssociationEnd to Standard.Classifier
        this.ruleMetaExpert.addDependencyRule(AssociationEnd.class, null, "Source");
        
        // no constraint on AssociationEnd.Qualifier : Attribute from Standard.AssociationEnd to Standard.Attribute
        this.ruleMetaExpert.addDependencyRule(AssociationEnd.class, null, "Qualifier");
        
        // no constraint on AssociationEnd.Opposite : AssociationEnd from Standard.AssociationEnd to Standard.AssociationEnd
        this.ruleMetaExpert.addDependencyRule(AssociationEnd.class, null, "Opposite");
        
        // no constraint on AssociationEnd.Association : Association from Standard.AssociationEnd to Standard.Association
        this.ruleMetaExpert.addDependencyRule(AssociationEnd.class, null, "Association");
    }

    @objid ("89811e21-2808-4e99-838e-52c74f8fa8fc")
    protected void registerMetaExpertForStructuralFeature() {
        // Standard.StructuralFeature
        // -----------
    }

    @objid ("6978bc26-f495-47e9-986d-83064bf38bf7")
    protected void registerMetaExpertForFeature() {
        // Standard.Feature is abstract
        
        // -----------
    }

    @objid ("41bac1f8-8403-4427-b615-6684ea8b1a51")
    protected void registerMetaExpertForAssociation() {
        // Standard.Association
        // -----------
        
        // no constraint on Association.LinkToClass : ClassAssociation from Standard.Association to Standard.ClassAssociation
        this.ruleMetaExpert.addDependencyRule(Association.class, null, "LinkToClass");
    }

    @objid ("8c979517-bde9-4371-b9d8-b2b70ffe4053")
    protected void registerMetaExpertForArtifact() {
        // Standard.Artifact
        // -----------
        
        // no constraint on Artifact.Utilized : Manifestation from Standard.Artifact to Standard.Manifestation
        this.ruleMetaExpert.addDependencyRule(Artifact.class, null, "Utilized");
    }

    @objid ("a7d5ac0b-5072-43ef-9bd4-baf237fd66b6")
    protected void registerMetaExpertForUsage() {
        // Standard.Usage
        // -----------
    }

    @objid ("a9eb59a7-850e-439d-a553-90a0d042b6b7")
    protected void registerMetaExpertForSubstitution() {
        // Standard.Substitution
        // -----------
        
        // no constraint on Substitution.Contract : Classifier from Standard.Substitution to Standard.Classifier
        this.ruleMetaExpert.addDependencyRule(Substitution.class, null, "Contract");
    }

    @objid ("40732411-7df4-4a70-9ef8-61a3b550daba")
    protected void registerMetaExpertForAbstraction() {
        // Standard.Abstraction
        // -----------
    }

    @objid ("9d95ed17-2363-4954-a8ef-e12138a4c0fc")
    protected void registerMetaExpertForInformationItem() {
        // Standard.InformationItem
        // -----------
        
        // no constraint on InformationItem.Represented : Classifier from Standard.InformationItem to Standard.Classifier
        this.ruleMetaExpert.addDependencyRule(InformationItem.class, null, "Represented");
    }

    @objid ("89869feb-13f2-41db-88ee-d0908fa1786d")
    protected void registerMetaExpertForInformationFlow() {
        // Standard.InformationFlow
        // -----------
        
        // no constraint on InformationFlow.InformationSource : UmlModelElement from Standard.InformationFlow to Standard.UmlModelElement
        this.ruleMetaExpert.addDependencyRule(InformationFlow.class, null, "InformationSource");
        
        // no constraint on InformationFlow.InformationTarget : UmlModelElement from Standard.InformationFlow to Standard.UmlModelElement
        this.ruleMetaExpert.addDependencyRule(InformationFlow.class, null, "InformationTarget");
        
        // no constraint on InformationFlow.RealizingActivityEdge : ActivityEdge from Standard.InformationFlow to Standard.ActivityEdge
        this.ruleMetaExpert.addDependencyRule(InformationFlow.class, null, "RealizingActivityEdge");
        
        // no constraint on InformationFlow.RealizingCommunicationMessage : CommunicationMessage from Standard.InformationFlow to Standard.CommunicationMessage
        this.ruleMetaExpert.addDependencyRule(InformationFlow.class, null, "RealizingCommunicationMessage");
        
        // no constraint on InformationFlow.RealizingFeature : StructuralFeature from Standard.InformationFlow to Standard.StructuralFeature
        this.ruleMetaExpert.addDependencyRule(InformationFlow.class, null, "RealizingFeature");
        
        // no constraint on InformationFlow.RealizingLink : LinkEnd from Standard.InformationFlow to Standard.LinkEnd
        this.ruleMetaExpert.addDependencyRule(InformationFlow.class, null, "RealizingLink");
        
        // no constraint on InformationFlow.RealizingMessage : Message from Standard.InformationFlow to Standard.Message
        this.ruleMetaExpert.addDependencyRule(InformationFlow.class, null, "RealizingMessage");
        
        // no constraint on InformationFlow.RealizingNaryLink : NaryLink from Standard.InformationFlow to Standard.NaryLink
        this.ruleMetaExpert.addDependencyRule(InformationFlow.class, null, "RealizingNaryLink");
        
        // no constraint on InformationFlow.Conveyed : Classifier from Standard.InformationFlow to Standard.Classifier
        this.ruleMetaExpert.addDependencyRule(InformationFlow.class, null, "Conveyed");
        
        // no constraint on InformationFlow.Channel : AssociationEnd from Standard.InformationFlow to Standard.AssociationEnd
        this.ruleMetaExpert.addDependencyRule(InformationFlow.class, null, "Channel");
    }

    @objid ("cd497e00-c46f-4655-a33a-c70e29540c44")
    protected void registerMetaExpertForDataFlow() {
        // Standard.DataFlow
        // -----------
        
        // no constraint on DataFlow.Destination : NameSpace from Standard.DataFlow to Standard.NameSpace
        this.ruleMetaExpert.addDependencyRule(DataFlow.class, null, "Destination");
        
        // no constraint on DataFlow.SModel : Signal from Standard.DataFlow to Standard.Signal
        this.ruleMetaExpert.addDependencyRule(DataFlow.class, null, "SModel");
    }

    @objid ("e5de7c06-f01d-43b4-8da6-176ffadf5067")
    protected void registerMetaExpertForUseCaseDependency() {
        // Standard.UseCaseDependency
        // -----------
        
        // no constraint on UseCaseDependency.ExtensionLocation : ExtensionPoint from Standard.UseCaseDependency to Standard.ExtensionPoint
        this.ruleMetaExpert.addDependencyRule(UseCaseDependency.class, null, "ExtensionLocation");
        
        // no constraint on UseCaseDependency.Target : UseCase from Standard.UseCaseDependency to Standard.UseCase
        this.ruleMetaExpert.addDependencyRule(UseCaseDependency.class, null, "Target");
    }

    @objid ("363ee3ef-75f6-4fea-8ed8-c2369974a54c")
    protected void registerMetaExpertForUseCase() {
        // Standard.UseCase
        // -----------
        
        // no constraint on UseCase.Used : UseCaseDependency from Standard.UseCase to Standard.UseCaseDependency
        this.ruleMetaExpert.addDependencyRule(UseCase.class, null, "Used");
        
        // no constraint on UseCase.OwnedExtension : ExtensionPoint from Standard.UseCase to Standard.ExtensionPoint
        this.ruleMetaExpert.addDependencyRule(UseCase.class, null, "OwnedExtension");
    }

    @objid ("2816854b-74ee-4c58-be05-8e64a8c127be")
    protected void registerMetaExpertForExtensionPoint() {
        // Standard.ExtensionPoint
        // -----------
    }

    @objid ("2df24786-194d-429e-bb25-cec0b739511f")
    protected void registerMetaExpertForActor() {
        // Standard.Actor
        // -----------
    }

    @objid ("1f96ae08-6b09-49b4-a3c0-2cd3433aebfa")
    protected void registerMetaExpertForTerminatePseudoState() {
        // Standard.TerminatePseudoState
        // -----------
    }

    @objid ("984d02d5-945d-4507-bb0f-e007634cdd60")
    protected void registerMetaExpertForStateMachine() {
        // Standard.StateMachine
        // -----------
        
        // no constraint on StateMachine.Top : Region from Standard.StateMachine to Standard.Region
        this.ruleMetaExpert.addDependencyRule(StateMachine.class, null, "Top");
        
        // no constraint on StateMachine.EntryPoint : EntryPointPseudoState from Standard.StateMachine to Standard.EntryPointPseudoState
        this.ruleMetaExpert.addDependencyRule(StateMachine.class, null, "EntryPoint");
        
        // no constraint on StateMachine.ExitPoint : ExitPointPseudoState from Standard.StateMachine to Standard.ExitPointPseudoState
        this.ruleMetaExpert.addDependencyRule(StateMachine.class, null, "ExitPoint");
    }

    @objid ("3eb9521e-8552-470a-90be-b23d46b24043")
    protected void registerMetaExpertForShallowHistoryPseudoState() {
        // Standard.ShallowHistoryPseudoState
        // -----------
    }

    @objid ("45962915-a129-44c7-91aa-4977a9b8187f")
    protected void registerMetaExpertForRegion() {
        // Standard.Region
        // -----------
        
        // no constraint on Region.Sub : StateVertex from Standard.Region to Standard.StateVertex
        this.ruleMetaExpert.addDependencyRule(Region.class, null, "Sub");
    }

    @objid ("e91f46c8-f122-48f2-b01a-889ab3db9754")
    protected void registerMetaExpertForJunctionPseudoState() {
        // Standard.JunctionPseudoState
        // -----------
    }

    @objid ("d1ead0fb-73a5-49ff-b4e0-cc0760562c8d")
    protected void registerMetaExpertForJoinPseudoState() {
        // Standard.JoinPseudoState
        // -----------
    }

    @objid ("04867c93-8dfc-46ac-b045-9074ed8cb984")
    protected void registerMetaExpertForInternalTransition() {
        // Standard.InternalTransition
        // -----------
    }

    @objid ("d8d634fe-0e0c-44fc-806a-96fcd66c0646")
    protected void registerMetaExpertForTransition() {
        // Standard.Transition
        // -----------
        
        // no constraint on Transition.Processed : Operation from Standard.Transition to Standard.Operation
        this.ruleMetaExpert.addDependencyRule(InternalTransition.class, null, "Processed");
        this.ruleMetaExpert.addDependencyRule(Transition.class, null, "Processed");
        
        // no constraint on Transition.Trigger : Event from Standard.Transition to Standard.Event
        this.ruleMetaExpert.addDependencyRule(InternalTransition.class, null, "Trigger");
        this.ruleMetaExpert.addDependencyRule(Transition.class, null, "Trigger");
        
        // no constraint on Transition.BehaviorEffect : Behavior from Standard.Transition to Standard.Behavior
        this.ruleMetaExpert.addDependencyRule(InternalTransition.class, null, "BehaviorEffect");
        this.ruleMetaExpert.addDependencyRule(Transition.class, null, "BehaviorEffect");
        
        // no constraint on Transition.Target : StateVertex from Standard.Transition to Standard.StateVertex
        this.ruleMetaExpert.addDependencyRule(InternalTransition.class, null, "Target");
        this.ruleMetaExpert.addDependencyRule(Transition.class, null, "Target");
        
        // no constraint on Transition.Effects : Signal from Standard.Transition to Standard.Signal
        this.ruleMetaExpert.addDependencyRule(InternalTransition.class, null, "Effects");
        this.ruleMetaExpert.addDependencyRule(Transition.class, null, "Effects");
    }

    @objid ("bd14ee23-d68a-4c6d-b50a-a5d792fafb49")
    protected void registerMetaExpertForInitialPseudoState() {
        // Standard.InitialPseudoState
        // -----------
    }

    @objid ("a0f4470e-a500-4135-8f5d-2321e852d94f")
    protected void registerMetaExpertForForkPseudoState() {
        // Standard.ForkPseudoState
        // -----------
    }

    @objid ("867043d1-df23-4e48-abbf-d0d94796e7ae")
    protected void registerMetaExpertForFinalState() {
        // Standard.FinalState
        // -----------
    }

    @objid ("d670367c-73b0-4793-9ac4-518fe35cb47a")
    protected void registerMetaExpertForState() {
        // Standard.State
        // -----------
        
        // no constraint on State.ExitPoint : ExitPointPseudoState from Standard.State to Standard.ExitPointPseudoState
        this.ruleMetaExpert.addDependencyRule(FinalState.class, null, "ExitPoint");
        this.ruleMetaExpert.addDependencyRule(State.class, null, "ExitPoint");
        
        // no constraint on State.Deffered : Event from Standard.State to Standard.Event
        this.ruleMetaExpert.addDependencyRule(FinalState.class, null, "Deffered");
        this.ruleMetaExpert.addDependencyRule(State.class, null, "Deffered");
        
        // no constraint on State.Internal : InternalTransition from Standard.State to Standard.InternalTransition
        this.ruleMetaExpert.addDependencyRule(FinalState.class, null, "Internal");
        this.ruleMetaExpert.addDependencyRule(State.class, null, "Internal");
        
        // no constraint on State.EntryPoint : EntryPointPseudoState from Standard.State to Standard.EntryPointPseudoState
        this.ruleMetaExpert.addDependencyRule(FinalState.class, null, "EntryPoint");
        this.ruleMetaExpert.addDependencyRule(State.class, null, "EntryPoint");
        
        // no constraint on State.OwnedRegion : Region from Standard.State to Standard.Region
        this.ruleMetaExpert.addDependencyRule(FinalState.class, null, "OwnedRegion");
        this.ruleMetaExpert.addDependencyRule(State.class, null, "OwnedRegion");
        
        // no constraint on State.Connection : ConnectionPointReference from Standard.State to Standard.ConnectionPointReference
        this.ruleMetaExpert.addDependencyRule(FinalState.class, null, "Connection");
        this.ruleMetaExpert.addDependencyRule(State.class, null, "Connection");
        
        // no constraint on State.SubMachine : StateMachine from Standard.State to Standard.StateMachine
        this.ruleMetaExpert.addDependencyRule(FinalState.class, null, "SubMachine");
        this.ruleMetaExpert.addDependencyRule(State.class, null, "SubMachine");
    }

    @objid ("8b26d8a4-7577-4429-8705-b82266bd084c")
    protected void registerMetaExpertForExitPointPseudoState() {
        // Standard.ExitPointPseudoState
        // -----------
    }

    @objid ("149c6abf-32bf-495c-a5a7-0bb69cb97897")
    protected void registerMetaExpertForEntryPointPseudoState() {
        // Standard.EntryPointPseudoState
        // -----------
    }

    @objid ("f697d54e-6f89-4c50-aed7-2852c4cba11d")
    protected void registerMetaExpertForDeepHistoryPseudoState() {
        // Standard.DeepHistoryPseudoState
        // -----------
    }

    @objid ("290e1d9d-231b-4d80-8568-528416920478")
    protected void registerMetaExpertForConnectionPointReference() {
        // Standard.ConnectionPointReference
        // -----------
        
        // no constraint on ConnectionPointReference.Exit : ExitPointPseudoState from Standard.ConnectionPointReference to Standard.ExitPointPseudoState
        this.ruleMetaExpert.addDependencyRule(ConnectionPointReference.class, null, "Exit");
        
        // no constraint on ConnectionPointReference.Entry : EntryPointPseudoState from Standard.ConnectionPointReference to Standard.EntryPointPseudoState
        this.ruleMetaExpert.addDependencyRule(ConnectionPointReference.class, null, "Entry");
    }

    @objid ("c800fb08-4c8a-4163-81cd-e1588e79f0ef")
    protected void registerMetaExpertForChoicePseudoState() {
        // Standard.ChoicePseudoState
        // -----------
    }

    @objid ("63cc9629-8a49-4eee-b411-68877b46994a")
    protected void registerMetaExpertForAbstractPseudoState() {
        // Standard.AbstractPseudoState is abstract
        
        // -----------
    }

    @objid ("a43db67c-c58c-4ee1-b9f5-6564354f2e73")
    protected void registerMetaExpertForStateVertex() {
        // Standard.StateVertex is abstract
        
        // -----------
        
        // no constraint on StateVertex.OutGoing : Transition from Standard.StateVertex to Standard.Transition
        this.ruleMetaExpert.addDependencyRule(AbstractPseudoState.class, null, "OutGoing");
        this.ruleMetaExpert.addDependencyRule(ChoicePseudoState.class, null, "OutGoing");
        this.ruleMetaExpert.addDependencyRule(ConnectionPointReference.class, null, "OutGoing");
        this.ruleMetaExpert.addDependencyRule(DeepHistoryPseudoState.class, null, "OutGoing");
        this.ruleMetaExpert.addDependencyRule(EntryPointPseudoState.class, null, "OutGoing");
        this.ruleMetaExpert.addDependencyRule(ExitPointPseudoState.class, null, "OutGoing");
        this.ruleMetaExpert.addDependencyRule(FinalState.class, null, "OutGoing");
        this.ruleMetaExpert.addDependencyRule(ForkPseudoState.class, null, "OutGoing");
        this.ruleMetaExpert.addDependencyRule(InitialPseudoState.class, null, "OutGoing");
        this.ruleMetaExpert.addDependencyRule(JoinPseudoState.class, null, "OutGoing");
        this.ruleMetaExpert.addDependencyRule(JunctionPseudoState.class, null, "OutGoing");
        this.ruleMetaExpert.addDependencyRule(ShallowHistoryPseudoState.class, null, "OutGoing");
        this.ruleMetaExpert.addDependencyRule(State.class, null, "OutGoing");
        this.ruleMetaExpert.addDependencyRule(StateVertex.class, null, "OutGoing");
        this.ruleMetaExpert.addDependencyRule(TerminatePseudoState.class, null, "OutGoing");
    }

    @objid ("19a6426a-f940-46d1-a329-aced78c2ea47")
    protected void registerMetaExpertForTerminateSpecification() {
        // Standard.TerminateSpecification
        // -----------
    }

    @objid ("4c23ebfe-08fa-446a-9fe7-d94d74fa9002")
    protected void registerMetaExpertForStateInvariant() {
        // Standard.StateInvariant
        // -----------
    }

    @objid ("c811f75c-dd33-428f-be82-b29290232ef4")
    protected void registerMetaExpertForPartDecomposition() {
        // Standard.PartDecomposition
        // -----------
    }

    @objid ("3d1f16f1-ce21-45ba-a0b7-1b49634acefb")
    protected void registerMetaExpertForMessage() {
        // Standard.Message
        // -----------
        
        // no constraint on Message.SignalSignature : Signal from Standard.Message to Standard.Signal
        this.ruleMetaExpert.addDependencyRule(Message.class, null, "SignalSignature");
        
        // no constraint on Message.ReceiveEvent : MessageEnd from Standard.Message to Standard.MessageEnd
        this.ruleMetaExpert.addDependencyRule(Message.class, null, "ReceiveEvent");
        
        // no constraint on Message.Invoked : Operation from Standard.Message to Standard.Operation
        this.ruleMetaExpert.addDependencyRule(Message.class, null, "Invoked");
    }

    @objid ("43dadb1d-c9ff-4106-8faa-bce7f24be13d")
    protected void registerMetaExpertForLifeline() {
        // Standard.Lifeline
        // -----------
        
        // no constraint on Lifeline.DecomposedAs : PartDecomposition from Standard.Lifeline to Standard.PartDecomposition
        this.ruleMetaExpert.addDependencyRule(Lifeline.class, null, "DecomposedAs");
        
        // no constraint on Lifeline.Represented : Instance from Standard.Lifeline to Standard.Instance
        this.ruleMetaExpert.addDependencyRule(Lifeline.class, null, "Represented");
    }

    @objid ("05e6ada2-dc4a-4c97-bcd6-d34ea5de4b35")
    protected void registerMetaExpertForInteractionUse() {
        // Standard.InteractionUse
        // -----------
        
        // no constraint on InteractionUse.ActualGate : Gate from Standard.InteractionUse to Standard.Gate
        this.ruleMetaExpert.addDependencyRule(InteractionUse.class, null, "ActualGate");
        this.ruleMetaExpert.addDependencyRule(PartDecomposition.class, null, "ActualGate");
        
        // no constraint on InteractionUse.RefersTo : Interaction from Standard.InteractionUse to Standard.Interaction
        this.ruleMetaExpert.addDependencyRule(InteractionUse.class, null, "RefersTo");
        this.ruleMetaExpert.addDependencyRule(PartDecomposition.class, null, "RefersTo");
    }

    @objid ("8f9381dd-9520-40b8-9727-60f6d2c8bb75")
    protected void registerMetaExpertForInteractionOperand() {
        // Standard.InteractionOperand
        // -----------
        
        // no constraint on InteractionOperand.Fragment : InteractionFragment from Standard.InteractionOperand to Standard.InteractionFragment
        this.ruleMetaExpert.addDependencyRule(InteractionOperand.class, null, "Fragment");
    }

    @objid ("ce88b1e0-dbfd-4ea3-9a1f-007325c56c6f")
    protected void registerMetaExpertForInteraction() {
        // Standard.Interaction
        // -----------
        
        // no constraint on Interaction.FormalGate : Gate from Standard.Interaction to Standard.Gate
        this.ruleMetaExpert.addDependencyRule(Interaction.class, null, "FormalGate");
        
        // no constraint on Interaction.Fragment : InteractionFragment from Standard.Interaction to Standard.InteractionFragment
        this.ruleMetaExpert.addDependencyRule(Interaction.class, null, "Fragment");
        
        // no constraint on Interaction.OwnedLine : Lifeline from Standard.Interaction to Standard.Lifeline
        this.ruleMetaExpert.addDependencyRule(Interaction.class, null, "OwnedLine");
    }

    @objid ("11e5b566-0ffc-4133-9763-b7abb751e365")
    protected void registerMetaExpertForGeneralOrdering() {
        // Standard.GeneralOrdering
        // -----------
        
        // no constraint on GeneralOrdering.After : OccurrenceSpecification from Standard.GeneralOrdering to Standard.OccurrenceSpecification
        this.ruleMetaExpert.addDependencyRule(GeneralOrdering.class, null, "After");
    }

    @objid ("d2dc7141-838c-4881-b277-f2d25240368e")
    protected void registerMetaExpertForGate() {
        // Standard.Gate
        // -----------
        
        // no constraint on Gate.Formal : Gate from Standard.Gate to Standard.Gate
        this.ruleMetaExpert.addDependencyRule(Gate.class, null, "Formal");
    }

    @objid ("61c269f2-18d8-41df-84e1-1c68c0aed486")
    protected void registerMetaExpertForExecutionSpecification() {
        // Standard.ExecutionSpecification
        // -----------
        
        // no constraint on ExecutionSpecification.Finish : ExecutionOccurenceSpecification from Standard.ExecutionSpecification to Standard.ExecutionOccurenceSpecification
        this.ruleMetaExpert.addDependencyRule(ExecutionSpecification.class, null, "Finish");
    }

    @objid ("ecf90b3f-8b07-4860-99e3-c64573db4faa")
    protected void registerMetaExpertForExecutionOccurenceSpecification() {
        // Standard.ExecutionOccurenceSpecification
        // -----------
        
        // no constraint on ExecutionOccurenceSpecification.Started : ExecutionSpecification from Standard.ExecutionOccurenceSpecification to Standard.ExecutionSpecification
        this.ruleMetaExpert.addDependencyRule(ExecutionOccurenceSpecification.class, null, "Started");
        this.ruleMetaExpert.addDependencyRule(TerminateSpecification.class, null, "Started");
    }

    @objid ("73456418-454f-4ed4-a9c2-c267a06b03ee")
    protected void registerMetaExpertForMessageEnd() {
        // Standard.MessageEnd is abstract
        
        // -----------
        
        // no constraint on MessageEnd.SentMessage : Message from Standard.MessageEnd to Standard.Message
        this.ruleMetaExpert.addDependencyRule(ExecutionOccurenceSpecification.class, null, "SentMessage");
        this.ruleMetaExpert.addDependencyRule(Gate.class, null, "SentMessage");
        this.ruleMetaExpert.addDependencyRule(MessageEnd.class, null, "SentMessage");
        this.ruleMetaExpert.addDependencyRule(TerminateSpecification.class, null, "SentMessage");
    }

    @objid ("6005e7f5-2fcb-49fb-915c-8d6931b56c83")
    protected void registerMetaExpertForOccurrenceSpecification() {
        // Standard.OccurrenceSpecification is abstract
        
        // -----------
        
        // no constraint on OccurrenceSpecification.ToAfter : GeneralOrdering from Standard.OccurrenceSpecification to Standard.GeneralOrdering
        this.ruleMetaExpert.addDependencyRule(ExecutionOccurenceSpecification.class, null, "ToAfter");
        this.ruleMetaExpert.addDependencyRule(Gate.class, null, "ToAfter");
        this.ruleMetaExpert.addDependencyRule(MessageEnd.class, null, "ToAfter");
        this.ruleMetaExpert.addDependencyRule(OccurrenceSpecification.class, null, "ToAfter");
        this.ruleMetaExpert.addDependencyRule(StateInvariant.class, null, "ToAfter");
        this.ruleMetaExpert.addDependencyRule(TerminateSpecification.class, null, "ToAfter");
    }

    @objid ("e1e61392-0c66-4ee9-9009-698bc7e5a1ba")
    protected void registerMetaExpertForDurationConstraint() {
        // Standard.DurationConstraint
        // -----------
    }

    @objid ("5984dc6b-68de-4bad-8453-3fde1dbeb54c")
    protected void registerMetaExpertForConstraint() {
        // Standard.Constraint
        // -----------
        
        // no constraint on Constraint.ConstrainedElement : UmlModelElement from Standard.Constraint to Standard.UmlModelElement
        this.ruleMetaExpert.addDependencyRule(Constraint.class, null, "ConstrainedElement");
        this.ruleMetaExpert.addDependencyRule(DurationConstraint.class, null, "ConstrainedElement");
    }

    @objid ("719f9fd5-ee21-4472-9ad9-fc57b37194e9")
    protected void registerMetaExpertForCombinedFragment() {
        // Standard.CombinedFragment
        // -----------
        
        // no constraint on CombinedFragment.Operand : InteractionOperand from Standard.CombinedFragment to Standard.InteractionOperand
        this.ruleMetaExpert.addDependencyRule(CombinedFragment.class, null, "Operand");
        
        // no constraint on CombinedFragment.FragmentGate : Gate from Standard.CombinedFragment to Standard.Gate
        this.ruleMetaExpert.addDependencyRule(CombinedFragment.class, null, "FragmentGate");
    }

    @objid ("e1c6541d-bc25-4746-9f8f-15636e832fa1")
    protected void registerMetaExpertForInteractionFragment() {
        // Standard.InteractionFragment is abstract
        
        // -----------
        
        // no constraint on InteractionFragment.Covered : Lifeline from Standard.InteractionFragment to Standard.Lifeline
        this.ruleMetaExpert.addDependencyRule(CombinedFragment.class, null, "Covered");
        this.ruleMetaExpert.addDependencyRule(ExecutionOccurenceSpecification.class, null, "Covered");
        this.ruleMetaExpert.addDependencyRule(ExecutionSpecification.class, null, "Covered");
        this.ruleMetaExpert.addDependencyRule(Gate.class, null, "Covered");
        this.ruleMetaExpert.addDependencyRule(InteractionFragment.class, null, "Covered");
        this.ruleMetaExpert.addDependencyRule(InteractionOperand.class, null, "Covered");
        this.ruleMetaExpert.addDependencyRule(InteractionUse.class, null, "Covered");
        this.ruleMetaExpert.addDependencyRule(MessageEnd.class, null, "Covered");
        this.ruleMetaExpert.addDependencyRule(OccurrenceSpecification.class, null, "Covered");
        this.ruleMetaExpert.addDependencyRule(PartDecomposition.class, null, "Covered");
        this.ruleMetaExpert.addDependencyRule(StateInvariant.class, null, "Covered");
        this.ruleMetaExpert.addDependencyRule(TerminateSpecification.class, null, "Covered");
    }

    @objid ("ef29632f-38a8-4d82-a598-362df7145592")
    protected void registerMetaExpertForCommunicationNode() {
        // Standard.CommunicationNode
        // -----------
        
        // no constraint on CommunicationNode.Represented : Instance from Standard.CommunicationNode to Standard.Instance
        this.ruleMetaExpert.addDependencyRule(CommunicationNode.class, null, "Represented");
        
        // no constraint on CommunicationNode.Started : CommunicationChannel from Standard.CommunicationNode to Standard.CommunicationChannel
        this.ruleMetaExpert.addDependencyRule(CommunicationNode.class, null, "Started");
    }

    @objid ("299f23a5-6346-429e-ae6a-4cbc4546b269")
    protected void registerMetaExpertForCommunicationMessage() {
        // Standard.CommunicationMessage
        // -----------
        
        // no constraint on CommunicationMessage.Invoked : Operation from Standard.CommunicationMessage to Standard.Operation
        this.ruleMetaExpert.addDependencyRule(CommunicationMessage.class, null, "Invoked");
        
        // no constraint on CommunicationMessage.SignalSignature : Signal from Standard.CommunicationMessage to Standard.Signal
        this.ruleMetaExpert.addDependencyRule(CommunicationMessage.class, null, "SignalSignature");
    }

    @objid ("d05f400e-9344-4866-ab6e-1b21609a501e")
    protected void registerMetaExpertForCommunicationInteraction() {
        // Standard.CommunicationInteraction
        // -----------
        
        // no constraint on CommunicationInteraction.Owned : CommunicationNode from Standard.CommunicationInteraction to Standard.CommunicationNode
        this.ruleMetaExpert.addDependencyRule(CommunicationInteraction.class, null, "Owned");
    }

    @objid ("f629760f-8a67-49cc-920b-ed77d7d70fc2")
    protected void registerMetaExpertForCommunicationChannel() {
        // Standard.CommunicationChannel
        // -----------
        
        // no constraint on CommunicationChannel.StartToEndMessage : CommunicationMessage from Standard.CommunicationChannel to Standard.CommunicationMessage
        this.ruleMetaExpert.addDependencyRule(CommunicationChannel.class, null, "StartToEndMessage");
        
        // no constraint on CommunicationChannel.Channel : Link from Standard.CommunicationChannel to Standard.Link
        this.ruleMetaExpert.addDependencyRule(CommunicationChannel.class, null, "Channel");
        
        // no constraint on CommunicationChannel.NaryChannel : NaryLink from Standard.CommunicationChannel to Standard.NaryLink
        this.ruleMetaExpert.addDependencyRule(CommunicationChannel.class, null, "NaryChannel");
        
        // no constraint on CommunicationChannel.EndToStartMessage : CommunicationMessage from Standard.CommunicationChannel to Standard.CommunicationMessage
        this.ruleMetaExpert.addDependencyRule(CommunicationChannel.class, null, "EndToStartMessage");
        
        // no constraint on CommunicationChannel.End : CommunicationNode from Standard.CommunicationChannel to Standard.CommunicationNode
        this.ruleMetaExpert.addDependencyRule(CommunicationChannel.class, null, "End");
    }

    @objid ("75f63324-8916-4ae2-9c50-bf35f30dcadf")
    protected void registerMetaExpertForSignal() {
        // Standard.Signal
        // -----------
        
        // no constraint on Signal.PBase : Parameter from Standard.Signal to Standard.Parameter
        this.ruleMetaExpert.addDependencyRule(Signal.class, null, "PBase");
        
        // no constraint on Signal.OBase : Operation from Standard.Signal to Standard.Operation
        this.ruleMetaExpert.addDependencyRule(Signal.class, null, "OBase");
        
        // no constraint on Signal.Base : GeneralClass from Standard.Signal to Standard.GeneralClass
        this.ruleMetaExpert.addDependencyRule(Signal.class, null, "Base");
    }

    @objid ("812d4c2a-3ec0-4b83-bbbf-a1b55dcfcc73")
    protected void registerMetaExpertForGeneralClass() {
        // Standard.GeneralClass is abstract
        
        // -----------
    }

    @objid ("6a8928b3-ea6e-4c74-ab69-daf93842e656")
    protected void registerMetaExpertForClassifier() {
        // Standard.Classifier is abstract
        
        // -----------
        
        // no constraint on Classifier.OwnedOperation : Operation from Standard.Classifier to Standard.Operation
        this.ruleMetaExpert.addDependencyRule(Actor.class, null, "OwnedOperation");
        this.ruleMetaExpert.addDependencyRule(Artifact.class, null, "OwnedOperation");
        this.ruleMetaExpert.addDependencyRule(Class.class, null, "OwnedOperation");
        this.ruleMetaExpert.addDependencyRule(Classifier.class, null, "OwnedOperation");
        this.ruleMetaExpert.addDependencyRule(Component.class, null, "OwnedOperation");
        this.ruleMetaExpert.addDependencyRule(DataType.class, null, "OwnedOperation");
        this.ruleMetaExpert.addDependencyRule(Enumeration.class, null, "OwnedOperation");
        this.ruleMetaExpert.addDependencyRule(GeneralClass.class, null, "OwnedOperation");
        this.ruleMetaExpert.addDependencyRule(InformationItem.class, null, "OwnedOperation");
        this.ruleMetaExpert.addDependencyRule(Interface.class, null, "OwnedOperation");
        this.ruleMetaExpert.addDependencyRule(Node.class, null, "OwnedOperation");
        this.ruleMetaExpert.addDependencyRule(Signal.class, null, "OwnedOperation");
        this.ruleMetaExpert.addDependencyRule(TemplateParameter.class, null, "OwnedOperation");
        this.ruleMetaExpert.addDependencyRule(UseCase.class, null, "OwnedOperation");
        
        // no constraint on Classifier.Substitued : Substitution from Standard.Classifier to Standard.Substitution
        this.ruleMetaExpert.addDependencyRule(Actor.class, null, "Substitued");
        this.ruleMetaExpert.addDependencyRule(Artifact.class, null, "Substitued");
        this.ruleMetaExpert.addDependencyRule(Class.class, null, "Substitued");
        this.ruleMetaExpert.addDependencyRule(Classifier.class, null, "Substitued");
        this.ruleMetaExpert.addDependencyRule(Component.class, null, "Substitued");
        this.ruleMetaExpert.addDependencyRule(DataType.class, null, "Substitued");
        this.ruleMetaExpert.addDependencyRule(Enumeration.class, null, "Substitued");
        this.ruleMetaExpert.addDependencyRule(GeneralClass.class, null, "Substitued");
        this.ruleMetaExpert.addDependencyRule(InformationItem.class, null, "Substitued");
        this.ruleMetaExpert.addDependencyRule(Interface.class, null, "Substitued");
        this.ruleMetaExpert.addDependencyRule(Node.class, null, "Substitued");
        this.ruleMetaExpert.addDependencyRule(Signal.class, null, "Substitued");
        this.ruleMetaExpert.addDependencyRule(TemplateParameter.class, null, "Substitued");
        this.ruleMetaExpert.addDependencyRule(UseCase.class, null, "Substitued");
        
        // no constraint on Classifier.OwnedAttribute : Attribute from Standard.Classifier to Standard.Attribute
        this.ruleMetaExpert.addDependencyRule(Actor.class, null, "OwnedAttribute");
        this.ruleMetaExpert.addDependencyRule(Artifact.class, null, "OwnedAttribute");
        this.ruleMetaExpert.addDependencyRule(Class.class, null, "OwnedAttribute");
        this.ruleMetaExpert.addDependencyRule(Classifier.class, null, "OwnedAttribute");
        this.ruleMetaExpert.addDependencyRule(Component.class, null, "OwnedAttribute");
        this.ruleMetaExpert.addDependencyRule(DataType.class, null, "OwnedAttribute");
        this.ruleMetaExpert.addDependencyRule(Enumeration.class, null, "OwnedAttribute");
        this.ruleMetaExpert.addDependencyRule(GeneralClass.class, null, "OwnedAttribute");
        this.ruleMetaExpert.addDependencyRule(InformationItem.class, null, "OwnedAttribute");
        this.ruleMetaExpert.addDependencyRule(Interface.class, null, "OwnedAttribute");
        this.ruleMetaExpert.addDependencyRule(Node.class, null, "OwnedAttribute");
        this.ruleMetaExpert.addDependencyRule(Signal.class, null, "OwnedAttribute");
        this.ruleMetaExpert.addDependencyRule(TemplateParameter.class, null, "OwnedAttribute");
        this.ruleMetaExpert.addDependencyRule(UseCase.class, null, "OwnedAttribute");
        
        // no constraint on Classifier.OwnedNaryEnd : NaryAssociationEnd from Standard.Classifier to Standard.NaryAssociationEnd
        this.ruleMetaExpert.addDependencyRule(Actor.class, null, "OwnedNaryEnd");
        this.ruleMetaExpert.addDependencyRule(Artifact.class, null, "OwnedNaryEnd");
        this.ruleMetaExpert.addDependencyRule(Class.class, null, "OwnedNaryEnd");
        this.ruleMetaExpert.addDependencyRule(Classifier.class, null, "OwnedNaryEnd");
        this.ruleMetaExpert.addDependencyRule(Component.class, null, "OwnedNaryEnd");
        this.ruleMetaExpert.addDependencyRule(DataType.class, null, "OwnedNaryEnd");
        this.ruleMetaExpert.addDependencyRule(Enumeration.class, null, "OwnedNaryEnd");
        this.ruleMetaExpert.addDependencyRule(GeneralClass.class, null, "OwnedNaryEnd");
        this.ruleMetaExpert.addDependencyRule(InformationItem.class, null, "OwnedNaryEnd");
        this.ruleMetaExpert.addDependencyRule(Interface.class, null, "OwnedNaryEnd");
        this.ruleMetaExpert.addDependencyRule(Node.class, null, "OwnedNaryEnd");
        this.ruleMetaExpert.addDependencyRule(Signal.class, null, "OwnedNaryEnd");
        this.ruleMetaExpert.addDependencyRule(TemplateParameter.class, null, "OwnedNaryEnd");
        this.ruleMetaExpert.addDependencyRule(UseCase.class, null, "OwnedNaryEnd");
        
        // no constraint on Classifier.OwnedEnd : AssociationEnd from Standard.Classifier to Standard.AssociationEnd
        this.ruleMetaExpert.addDependencyRule(Actor.class, null, "OwnedEnd");
        this.ruleMetaExpert.addDependencyRule(Artifact.class, null, "OwnedEnd");
        this.ruleMetaExpert.addDependencyRule(Class.class, null, "OwnedEnd");
        this.ruleMetaExpert.addDependencyRule(Classifier.class, null, "OwnedEnd");
        this.ruleMetaExpert.addDependencyRule(Component.class, null, "OwnedEnd");
        this.ruleMetaExpert.addDependencyRule(DataType.class, null, "OwnedEnd");
        this.ruleMetaExpert.addDependencyRule(Enumeration.class, null, "OwnedEnd");
        this.ruleMetaExpert.addDependencyRule(GeneralClass.class, null, "OwnedEnd");
        this.ruleMetaExpert.addDependencyRule(InformationItem.class, null, "OwnedEnd");
        this.ruleMetaExpert.addDependencyRule(Interface.class, null, "OwnedEnd");
        this.ruleMetaExpert.addDependencyRule(Node.class, null, "OwnedEnd");
        this.ruleMetaExpert.addDependencyRule(Signal.class, null, "OwnedEnd");
        this.ruleMetaExpert.addDependencyRule(TemplateParameter.class, null, "OwnedEnd");
        this.ruleMetaExpert.addDependencyRule(UseCase.class, null, "OwnedEnd");
        
        // no constraint on Classifier.InternalStructure : BindableInstance from Standard.Classifier to Standard.BindableInstance
        this.ruleMetaExpert.addDependencyRule(Actor.class, null, "InternalStructure");
        this.ruleMetaExpert.addDependencyRule(Artifact.class, null, "InternalStructure");
        this.ruleMetaExpert.addDependencyRule(Class.class, null, "InternalStructure");
        this.ruleMetaExpert.addDependencyRule(Classifier.class, null, "InternalStructure");
        this.ruleMetaExpert.addDependencyRule(Component.class, null, "InternalStructure");
        this.ruleMetaExpert.addDependencyRule(DataType.class, null, "InternalStructure");
        this.ruleMetaExpert.addDependencyRule(Enumeration.class, null, "InternalStructure");
        this.ruleMetaExpert.addDependencyRule(GeneralClass.class, null, "InternalStructure");
        this.ruleMetaExpert.addDependencyRule(InformationItem.class, null, "InternalStructure");
        this.ruleMetaExpert.addDependencyRule(Interface.class, null, "InternalStructure");
        this.ruleMetaExpert.addDependencyRule(Node.class, null, "InternalStructure");
        this.ruleMetaExpert.addDependencyRule(Signal.class, null, "InternalStructure");
        this.ruleMetaExpert.addDependencyRule(TemplateParameter.class, null, "InternalStructure");
        this.ruleMetaExpert.addDependencyRule(UseCase.class, null, "InternalStructure");
    }

    @objid ("82337aa4-bb05-4deb-bc03-4c1568408c15")
    protected void registerMetaExpertForNameSpace() {
        // Standard.NameSpace is abstract
        
        // -----------
        
        // no constraint on NameSpace.Parent : Generalization from Standard.NameSpace to Standard.Generalization
        this.ruleMetaExpert.addDependencyRule(Actor.class, null, "Parent");
        this.ruleMetaExpert.addDependencyRule(Artifact.class, null, "Parent");
        this.ruleMetaExpert.addDependencyRule(Class.class, null, "Parent");
        this.ruleMetaExpert.addDependencyRule(Classifier.class, null, "Parent");
        this.ruleMetaExpert.addDependencyRule(Collaboration.class, null, "Parent");
        this.ruleMetaExpert.addDependencyRule(Component.class, null, "Parent");
        this.ruleMetaExpert.addDependencyRule(DataType.class, null, "Parent");
        this.ruleMetaExpert.addDependencyRule(Enumeration.class, null, "Parent");
        this.ruleMetaExpert.addDependencyRule(GeneralClass.class, null, "Parent");
        this.ruleMetaExpert.addDependencyRule(InformationItem.class, null, "Parent");
        this.ruleMetaExpert.addDependencyRule(Interface.class, null, "Parent");
        this.ruleMetaExpert.addDependencyRule(NameSpace.class, null, "Parent");
        this.ruleMetaExpert.addDependencyRule(Node.class, null, "Parent");
        this.ruleMetaExpert.addDependencyRule(Package.class, null, "Parent");
        this.ruleMetaExpert.addDependencyRule(Signal.class, null, "Parent");
        this.ruleMetaExpert.addDependencyRule(TemplateParameter.class, null, "Parent");
        this.ruleMetaExpert.addDependencyRule(UseCase.class, null, "Parent");
        
        // no constraint on NameSpace.TemplateInstanciation : TemplateBinding from Standard.NameSpace to Standard.TemplateBinding
        this.ruleMetaExpert.addDependencyRule(Actor.class, null, "TemplateInstanciation");
        this.ruleMetaExpert.addDependencyRule(Artifact.class, null, "TemplateInstanciation");
        this.ruleMetaExpert.addDependencyRule(Class.class, null, "TemplateInstanciation");
        this.ruleMetaExpert.addDependencyRule(Classifier.class, null, "TemplateInstanciation");
        this.ruleMetaExpert.addDependencyRule(Collaboration.class, null, "TemplateInstanciation");
        this.ruleMetaExpert.addDependencyRule(Component.class, null, "TemplateInstanciation");
        this.ruleMetaExpert.addDependencyRule(DataType.class, null, "TemplateInstanciation");
        this.ruleMetaExpert.addDependencyRule(Enumeration.class, null, "TemplateInstanciation");
        this.ruleMetaExpert.addDependencyRule(GeneralClass.class, null, "TemplateInstanciation");
        this.ruleMetaExpert.addDependencyRule(InformationItem.class, null, "TemplateInstanciation");
        this.ruleMetaExpert.addDependencyRule(Interface.class, null, "TemplateInstanciation");
        this.ruleMetaExpert.addDependencyRule(NameSpace.class, null, "TemplateInstanciation");
        this.ruleMetaExpert.addDependencyRule(Node.class, null, "TemplateInstanciation");
        this.ruleMetaExpert.addDependencyRule(Package.class, null, "TemplateInstanciation");
        this.ruleMetaExpert.addDependencyRule(Signal.class, null, "TemplateInstanciation");
        this.ruleMetaExpert.addDependencyRule(TemplateParameter.class, null, "TemplateInstanciation");
        this.ruleMetaExpert.addDependencyRule(UseCase.class, null, "TemplateInstanciation");
        
        // no constraint on NameSpace.OwnedBehavior : Behavior from Standard.NameSpace to Standard.Behavior
        this.ruleMetaExpert.addDependencyRule(Actor.class, null, "OwnedBehavior");
        this.ruleMetaExpert.addDependencyRule(Artifact.class, null, "OwnedBehavior");
        this.ruleMetaExpert.addDependencyRule(Class.class, null, "OwnedBehavior");
        this.ruleMetaExpert.addDependencyRule(Classifier.class, null, "OwnedBehavior");
        this.ruleMetaExpert.addDependencyRule(Collaboration.class, null, "OwnedBehavior");
        this.ruleMetaExpert.addDependencyRule(Component.class, null, "OwnedBehavior");
        this.ruleMetaExpert.addDependencyRule(DataType.class, null, "OwnedBehavior");
        this.ruleMetaExpert.addDependencyRule(Enumeration.class, null, "OwnedBehavior");
        this.ruleMetaExpert.addDependencyRule(GeneralClass.class, null, "OwnedBehavior");
        this.ruleMetaExpert.addDependencyRule(InformationItem.class, null, "OwnedBehavior");
        this.ruleMetaExpert.addDependencyRule(Interface.class, null, "OwnedBehavior");
        this.ruleMetaExpert.addDependencyRule(NameSpace.class, null, "OwnedBehavior");
        this.ruleMetaExpert.addDependencyRule(Node.class, null, "OwnedBehavior");
        this.ruleMetaExpert.addDependencyRule(Package.class, null, "OwnedBehavior");
        this.ruleMetaExpert.addDependencyRule(Signal.class, null, "OwnedBehavior");
        this.ruleMetaExpert.addDependencyRule(TemplateParameter.class, null, "OwnedBehavior");
        this.ruleMetaExpert.addDependencyRule(UseCase.class, null, "OwnedBehavior");
        
        // no constraint on NameSpace.OwnedInformationFlow : InformationFlow from Standard.NameSpace to Standard.InformationFlow
        this.ruleMetaExpert.addDependencyRule(Actor.class, null, "OwnedInformationFlow");
        this.ruleMetaExpert.addDependencyRule(Artifact.class, null, "OwnedInformationFlow");
        this.ruleMetaExpert.addDependencyRule(Class.class, null, "OwnedInformationFlow");
        this.ruleMetaExpert.addDependencyRule(Classifier.class, null, "OwnedInformationFlow");
        this.ruleMetaExpert.addDependencyRule(Collaboration.class, null, "OwnedInformationFlow");
        this.ruleMetaExpert.addDependencyRule(Component.class, null, "OwnedInformationFlow");
        this.ruleMetaExpert.addDependencyRule(DataType.class, null, "OwnedInformationFlow");
        this.ruleMetaExpert.addDependencyRule(Enumeration.class, null, "OwnedInformationFlow");
        this.ruleMetaExpert.addDependencyRule(GeneralClass.class, null, "OwnedInformationFlow");
        this.ruleMetaExpert.addDependencyRule(InformationItem.class, null, "OwnedInformationFlow");
        this.ruleMetaExpert.addDependencyRule(Interface.class, null, "OwnedInformationFlow");
        this.ruleMetaExpert.addDependencyRule(NameSpace.class, null, "OwnedInformationFlow");
        this.ruleMetaExpert.addDependencyRule(Node.class, null, "OwnedInformationFlow");
        this.ruleMetaExpert.addDependencyRule(Package.class, null, "OwnedInformationFlow");
        this.ruleMetaExpert.addDependencyRule(Signal.class, null, "OwnedInformationFlow");
        this.ruleMetaExpert.addDependencyRule(TemplateParameter.class, null, "OwnedInformationFlow");
        this.ruleMetaExpert.addDependencyRule(UseCase.class, null, "OwnedInformationFlow");
        
        // no constraint on NameSpace.Sent : DataFlow from Standard.NameSpace to Standard.DataFlow
        this.ruleMetaExpert.addDependencyRule(Actor.class, null, "Sent");
        this.ruleMetaExpert.addDependencyRule(Artifact.class, null, "Sent");
        this.ruleMetaExpert.addDependencyRule(Class.class, null, "Sent");
        this.ruleMetaExpert.addDependencyRule(Classifier.class, null, "Sent");
        this.ruleMetaExpert.addDependencyRule(Collaboration.class, null, "Sent");
        this.ruleMetaExpert.addDependencyRule(Component.class, null, "Sent");
        this.ruleMetaExpert.addDependencyRule(DataType.class, null, "Sent");
        this.ruleMetaExpert.addDependencyRule(Enumeration.class, null, "Sent");
        this.ruleMetaExpert.addDependencyRule(GeneralClass.class, null, "Sent");
        this.ruleMetaExpert.addDependencyRule(InformationItem.class, null, "Sent");
        this.ruleMetaExpert.addDependencyRule(Interface.class, null, "Sent");
        this.ruleMetaExpert.addDependencyRule(NameSpace.class, null, "Sent");
        this.ruleMetaExpert.addDependencyRule(Node.class, null, "Sent");
        this.ruleMetaExpert.addDependencyRule(Package.class, null, "Sent");
        this.ruleMetaExpert.addDependencyRule(Signal.class, null, "Sent");
        this.ruleMetaExpert.addDependencyRule(TemplateParameter.class, null, "Sent");
        this.ruleMetaExpert.addDependencyRule(UseCase.class, null, "Sent");
        
        // no constraint on NameSpace.OwnedDataFlow : DataFlow from Standard.NameSpace to Standard.DataFlow
        this.ruleMetaExpert.addDependencyRule(Actor.class, null, "OwnedDataFlow");
        this.ruleMetaExpert.addDependencyRule(Artifact.class, null, "OwnedDataFlow");
        this.ruleMetaExpert.addDependencyRule(Class.class, null, "OwnedDataFlow");
        this.ruleMetaExpert.addDependencyRule(Classifier.class, null, "OwnedDataFlow");
        this.ruleMetaExpert.addDependencyRule(Collaboration.class, null, "OwnedDataFlow");
        this.ruleMetaExpert.addDependencyRule(Component.class, null, "OwnedDataFlow");
        this.ruleMetaExpert.addDependencyRule(DataType.class, null, "OwnedDataFlow");
        this.ruleMetaExpert.addDependencyRule(Enumeration.class, null, "OwnedDataFlow");
        this.ruleMetaExpert.addDependencyRule(GeneralClass.class, null, "OwnedDataFlow");
        this.ruleMetaExpert.addDependencyRule(InformationItem.class, null, "OwnedDataFlow");
        this.ruleMetaExpert.addDependencyRule(Interface.class, null, "OwnedDataFlow");
        this.ruleMetaExpert.addDependencyRule(NameSpace.class, null, "OwnedDataFlow");
        this.ruleMetaExpert.addDependencyRule(Node.class, null, "OwnedDataFlow");
        this.ruleMetaExpert.addDependencyRule(Package.class, null, "OwnedDataFlow");
        this.ruleMetaExpert.addDependencyRule(Signal.class, null, "OwnedDataFlow");
        this.ruleMetaExpert.addDependencyRule(TemplateParameter.class, null, "OwnedDataFlow");
        this.ruleMetaExpert.addDependencyRule(UseCase.class, null, "OwnedDataFlow");
        
        // no constraint on NameSpace.OwnedCollaborationUse : CollaborationUse from Standard.NameSpace to Standard.CollaborationUse
        this.ruleMetaExpert.addDependencyRule(Actor.class, null, "OwnedCollaborationUse");
        this.ruleMetaExpert.addDependencyRule(Artifact.class, null, "OwnedCollaborationUse");
        this.ruleMetaExpert.addDependencyRule(Class.class, null, "OwnedCollaborationUse");
        this.ruleMetaExpert.addDependencyRule(Classifier.class, null, "OwnedCollaborationUse");
        this.ruleMetaExpert.addDependencyRule(Collaboration.class, null, "OwnedCollaborationUse");
        this.ruleMetaExpert.addDependencyRule(Component.class, null, "OwnedCollaborationUse");
        this.ruleMetaExpert.addDependencyRule(DataType.class, null, "OwnedCollaborationUse");
        this.ruleMetaExpert.addDependencyRule(Enumeration.class, null, "OwnedCollaborationUse");
        this.ruleMetaExpert.addDependencyRule(GeneralClass.class, null, "OwnedCollaborationUse");
        this.ruleMetaExpert.addDependencyRule(InformationItem.class, null, "OwnedCollaborationUse");
        this.ruleMetaExpert.addDependencyRule(Interface.class, null, "OwnedCollaborationUse");
        this.ruleMetaExpert.addDependencyRule(NameSpace.class, null, "OwnedCollaborationUse");
        this.ruleMetaExpert.addDependencyRule(Node.class, null, "OwnedCollaborationUse");
        this.ruleMetaExpert.addDependencyRule(Package.class, null, "OwnedCollaborationUse");
        this.ruleMetaExpert.addDependencyRule(Signal.class, null, "OwnedCollaborationUse");
        this.ruleMetaExpert.addDependencyRule(TemplateParameter.class, null, "OwnedCollaborationUse");
        this.ruleMetaExpert.addDependencyRule(UseCase.class, null, "OwnedCollaborationUse");
        
        // no constraint on NameSpace.OwnedPackageImport : PackageImport from Standard.NameSpace to Standard.PackageImport
        this.ruleMetaExpert.addDependencyRule(Actor.class, null, "OwnedPackageImport");
        this.ruleMetaExpert.addDependencyRule(Artifact.class, null, "OwnedPackageImport");
        this.ruleMetaExpert.addDependencyRule(Class.class, null, "OwnedPackageImport");
        this.ruleMetaExpert.addDependencyRule(Classifier.class, null, "OwnedPackageImport");
        this.ruleMetaExpert.addDependencyRule(Collaboration.class, null, "OwnedPackageImport");
        this.ruleMetaExpert.addDependencyRule(Component.class, null, "OwnedPackageImport");
        this.ruleMetaExpert.addDependencyRule(DataType.class, null, "OwnedPackageImport");
        this.ruleMetaExpert.addDependencyRule(Enumeration.class, null, "OwnedPackageImport");
        this.ruleMetaExpert.addDependencyRule(GeneralClass.class, null, "OwnedPackageImport");
        this.ruleMetaExpert.addDependencyRule(InformationItem.class, null, "OwnedPackageImport");
        this.ruleMetaExpert.addDependencyRule(Interface.class, null, "OwnedPackageImport");
        this.ruleMetaExpert.addDependencyRule(NameSpace.class, null, "OwnedPackageImport");
        this.ruleMetaExpert.addDependencyRule(Node.class, null, "OwnedPackageImport");
        this.ruleMetaExpert.addDependencyRule(Package.class, null, "OwnedPackageImport");
        this.ruleMetaExpert.addDependencyRule(Signal.class, null, "OwnedPackageImport");
        this.ruleMetaExpert.addDependencyRule(TemplateParameter.class, null, "OwnedPackageImport");
        this.ruleMetaExpert.addDependencyRule(UseCase.class, null, "OwnedPackageImport");
        
        // no constraint on NameSpace.Template : TemplateParameter from Standard.NameSpace to Standard.TemplateParameter
        this.ruleMetaExpert.addDependencyRule(Actor.class, null, "Template");
        this.ruleMetaExpert.addDependencyRule(Artifact.class, null, "Template");
        this.ruleMetaExpert.addDependencyRule(Class.class, null, "Template");
        this.ruleMetaExpert.addDependencyRule(Classifier.class, null, "Template");
        this.ruleMetaExpert.addDependencyRule(Collaboration.class, null, "Template");
        this.ruleMetaExpert.addDependencyRule(Component.class, null, "Template");
        this.ruleMetaExpert.addDependencyRule(DataType.class, null, "Template");
        this.ruleMetaExpert.addDependencyRule(Enumeration.class, null, "Template");
        this.ruleMetaExpert.addDependencyRule(GeneralClass.class, null, "Template");
        this.ruleMetaExpert.addDependencyRule(InformationItem.class, null, "Template");
        this.ruleMetaExpert.addDependencyRule(Interface.class, null, "Template");
        this.ruleMetaExpert.addDependencyRule(NameSpace.class, null, "Template");
        this.ruleMetaExpert.addDependencyRule(Node.class, null, "Template");
        this.ruleMetaExpert.addDependencyRule(Package.class, null, "Template");
        this.ruleMetaExpert.addDependencyRule(Signal.class, null, "Template");
        this.ruleMetaExpert.addDependencyRule(TemplateParameter.class, null, "Template");
        this.ruleMetaExpert.addDependencyRule(UseCase.class, null, "Template");
        
        // no constraint on NameSpace.Realized : InterfaceRealization from Standard.NameSpace to Standard.InterfaceRealization
        this.ruleMetaExpert.addDependencyRule(Actor.class, null, "Realized");
        this.ruleMetaExpert.addDependencyRule(Artifact.class, null, "Realized");
        this.ruleMetaExpert.addDependencyRule(Class.class, null, "Realized");
        this.ruleMetaExpert.addDependencyRule(Classifier.class, null, "Realized");
        this.ruleMetaExpert.addDependencyRule(Collaboration.class, null, "Realized");
        this.ruleMetaExpert.addDependencyRule(Component.class, null, "Realized");
        this.ruleMetaExpert.addDependencyRule(DataType.class, null, "Realized");
        this.ruleMetaExpert.addDependencyRule(Enumeration.class, null, "Realized");
        this.ruleMetaExpert.addDependencyRule(GeneralClass.class, null, "Realized");
        this.ruleMetaExpert.addDependencyRule(InformationItem.class, null, "Realized");
        this.ruleMetaExpert.addDependencyRule(Interface.class, null, "Realized");
        this.ruleMetaExpert.addDependencyRule(NameSpace.class, null, "Realized");
        this.ruleMetaExpert.addDependencyRule(Node.class, null, "Realized");
        this.ruleMetaExpert.addDependencyRule(Package.class, null, "Realized");
        this.ruleMetaExpert.addDependencyRule(Signal.class, null, "Realized");
        this.ruleMetaExpert.addDependencyRule(TemplateParameter.class, null, "Realized");
        this.ruleMetaExpert.addDependencyRule(UseCase.class, null, "Realized");
        
        // no constraint on NameSpace.Declared : Instance from Standard.NameSpace to Standard.Instance
        this.ruleMetaExpert.addDependencyRule(Actor.class, null, "Declared");
        this.ruleMetaExpert.addDependencyRule(Artifact.class, null, "Declared");
        this.ruleMetaExpert.addDependencyRule(Class.class, null, "Declared");
        this.ruleMetaExpert.addDependencyRule(Classifier.class, null, "Declared");
        this.ruleMetaExpert.addDependencyRule(Collaboration.class, null, "Declared");
        this.ruleMetaExpert.addDependencyRule(Component.class, null, "Declared");
        this.ruleMetaExpert.addDependencyRule(DataType.class, null, "Declared");
        this.ruleMetaExpert.addDependencyRule(Enumeration.class, null, "Declared");
        this.ruleMetaExpert.addDependencyRule(GeneralClass.class, null, "Declared");
        this.ruleMetaExpert.addDependencyRule(InformationItem.class, null, "Declared");
        this.ruleMetaExpert.addDependencyRule(Interface.class, null, "Declared");
        this.ruleMetaExpert.addDependencyRule(NameSpace.class, null, "Declared");
        this.ruleMetaExpert.addDependencyRule(Node.class, null, "Declared");
        this.ruleMetaExpert.addDependencyRule(Package.class, null, "Declared");
        this.ruleMetaExpert.addDependencyRule(Signal.class, null, "Declared");
        this.ruleMetaExpert.addDependencyRule(TemplateParameter.class, null, "Declared");
        this.ruleMetaExpert.addDependencyRule(UseCase.class, null, "Declared");
        
        // no constraint on NameSpace.OwnedImport : ElementImport from Standard.NameSpace to Standard.ElementImport
        this.ruleMetaExpert.addDependencyRule(Actor.class, null, "OwnedImport");
        this.ruleMetaExpert.addDependencyRule(Artifact.class, null, "OwnedImport");
        this.ruleMetaExpert.addDependencyRule(Class.class, null, "OwnedImport");
        this.ruleMetaExpert.addDependencyRule(Classifier.class, null, "OwnedImport");
        this.ruleMetaExpert.addDependencyRule(Collaboration.class, null, "OwnedImport");
        this.ruleMetaExpert.addDependencyRule(Component.class, null, "OwnedImport");
        this.ruleMetaExpert.addDependencyRule(DataType.class, null, "OwnedImport");
        this.ruleMetaExpert.addDependencyRule(Enumeration.class, null, "OwnedImport");
        this.ruleMetaExpert.addDependencyRule(GeneralClass.class, null, "OwnedImport");
        this.ruleMetaExpert.addDependencyRule(InformationItem.class, null, "OwnedImport");
        this.ruleMetaExpert.addDependencyRule(Interface.class, null, "OwnedImport");
        this.ruleMetaExpert.addDependencyRule(NameSpace.class, null, "OwnedImport");
        this.ruleMetaExpert.addDependencyRule(Node.class, null, "OwnedImport");
        this.ruleMetaExpert.addDependencyRule(Package.class, null, "OwnedImport");
        this.ruleMetaExpert.addDependencyRule(Signal.class, null, "OwnedImport");
        this.ruleMetaExpert.addDependencyRule(TemplateParameter.class, null, "OwnedImport");
        this.ruleMetaExpert.addDependencyRule(UseCase.class, null, "OwnedImport");
    }

    @objid ("979c9ade-fc8b-4970-a90a-c1eff613cde1")
    protected void registerMetaExpertForModelTree() {
        // Standard.ModelTree is abstract
        
        // -----------
        
        // no constraint on ModelTree.OwnedElement : ModelTree from Standard.ModelTree to Standard.ModelTree
        this.ruleMetaExpert.addDependencyRule(Actor.class, null, "OwnedElement");
        this.ruleMetaExpert.addDependencyRule(Artifact.class, null, "OwnedElement");
        this.ruleMetaExpert.addDependencyRule(Class.class, null, "OwnedElement");
        this.ruleMetaExpert.addDependencyRule(Classifier.class, null, "OwnedElement");
        this.ruleMetaExpert.addDependencyRule(Collaboration.class, null, "OwnedElement");
        this.ruleMetaExpert.addDependencyRule(Component.class, null, "OwnedElement");
        this.ruleMetaExpert.addDependencyRule(DataType.class, null, "OwnedElement");
        this.ruleMetaExpert.addDependencyRule(Enumeration.class, null, "OwnedElement");
        this.ruleMetaExpert.addDependencyRule(GeneralClass.class, null, "OwnedElement");
        this.ruleMetaExpert.addDependencyRule(InformationItem.class, null, "OwnedElement");
        this.ruleMetaExpert.addDependencyRule(Interface.class, null, "OwnedElement");
        this.ruleMetaExpert.addDependencyRule(ModelTree.class, null, "OwnedElement");
        this.ruleMetaExpert.addDependencyRule(NameSpace.class, null, "OwnedElement");
        this.ruleMetaExpert.addDependencyRule(Node.class, null, "OwnedElement");
        this.ruleMetaExpert.addDependencyRule(Package.class, null, "OwnedElement");
        this.ruleMetaExpert.addDependencyRule(Signal.class, null, "OwnedElement");
        this.ruleMetaExpert.addDependencyRule(TemplateParameter.class, null, "OwnedElement");
        this.ruleMetaExpert.addDependencyRule(UseCase.class, null, "OwnedElement");
    }

    @objid ("f57d8ff6-e620-479d-8c90-c987256967bf")
    protected void registerMetaExpertForOpaqueBehavior() {
        // Standard.OpaqueBehavior
        // -----------
    }

    @objid ("e1929d82-421d-4591-ba42-048ccf841c45")
    protected void registerMetaExpertForEvent() {
        // Standard.Event
        // -----------
        
        // no constraint on Event.Model : Signal from Standard.Event to Standard.Signal
        this.ruleMetaExpert.addDependencyRule(Event.class, null, "Model");
        
        // no constraint on Event.Called : Operation from Standard.Event to Standard.Operation
        this.ruleMetaExpert.addDependencyRule(Event.class, null, "Called");
    }

    @objid ("569f0580-b687-4119-a17b-74bebd8c93d5")
    protected void registerMetaExpertForBehaviorParameter() {
        // Standard.BehaviorParameter
        // -----------
        
        // no constraint on BehaviorParameter.Mapped : Parameter from Standard.BehaviorParameter to Standard.Parameter
        this.ruleMetaExpert.addDependencyRule(BehaviorParameter.class, null, "Mapped");
    }

    @objid ("ecd4a508-3663-4189-b362-23eb4fc1445b")
    protected void registerMetaExpertForParameter() {
        // Standard.Parameter
        // -----------
        
        // no constraint on Parameter.Type : GeneralClass from Standard.Parameter to Standard.GeneralClass
        this.ruleMetaExpert.addDependencyRule(BehaviorParameter.class, null, "Type");
        this.ruleMetaExpert.addDependencyRule(Parameter.class, null, "Type");
    }

    @objid ("b8a44269-fb2f-4bd7-914f-48c5bf4e8254")
    protected void registerMetaExpertForValuePin() {
        // Standard.ValuePin
        // -----------
    }

    @objid ("a2d06f25-d004-4f07-978f-cab0725c9d16")
    protected void registerMetaExpertForSendSignalAction() {
        // Standard.SendSignalAction
        // -----------
        
        // no constraint on SendSignalAction.Sent : Signal from Standard.SendSignalAction to Standard.Signal
        this.ruleMetaExpert.addDependencyRule(SendSignalAction.class, null, "Sent");
    }

    @objid ("b524bb39-d37a-486b-8120-2c94b67cbfae")
    protected void registerMetaExpertForOutputPin() {
        // Standard.OutputPin
        // -----------
    }

    @objid ("60b38ca6-60e5-4c25-af52-6ab978a7258b")
    protected void registerMetaExpertForOpaqueAction() {
        // Standard.OpaqueAction
        // -----------
    }

    @objid ("c76042a8-a1e4-4dc4-91fe-5cc21050a243")
    protected void registerMetaExpertForObjectFlow() {
        // Standard.ObjectFlow
        // -----------
    }

    @objid ("af58e253-638d-4d5c-b439-09089c8bf2e2")
    protected void registerMetaExpertForMessageFlow() {
        // Standard.MessageFlow
        // -----------
        
        // no constraint on MessageFlow.TargetPartition : ActivityPartition from Standard.MessageFlow to Standard.ActivityPartition
        this.ruleMetaExpert.addDependencyRule(MessageFlow.class, null, "TargetPartition");
    }

    @objid ("9d16f091-8324-4249-90a6-4fd6cfddc987")
    protected void registerMetaExpertForLoopNode() {
        // Standard.LoopNode
        // -----------
    }

    @objid ("f7da63fc-dee8-444d-abc4-88791b52d5e9")
    protected void registerMetaExpertForInterruptibleActivityRegion() {
        // Standard.InterruptibleActivityRegion
        // -----------
        
        // no constraint on InterruptibleActivityRegion.InterruptingEdge : ActivityEdge from Standard.InterruptibleActivityRegion to Standard.ActivityEdge
        this.ruleMetaExpert.addDependencyRule(InterruptibleActivityRegion.class, null, "InterruptingEdge");
    }

    @objid ("911c7528-e4a2-4b86-83e5-971821e6b846")
    protected void registerMetaExpertForInstanceNode() {
        // Standard.InstanceNode
        // -----------
    }

    @objid ("60aa7614-2c98-407f-8f79-d8527fbf1a37")
    protected void registerMetaExpertForInputPin() {
        // Standard.InputPin
        // -----------
    }

    @objid ("7b6b18d2-4005-402f-837b-82ed7e773df7")
    protected void registerMetaExpertForPin() {
        // Standard.Pin is abstract
        
        // -----------
        
        // no constraint on Pin.Matched : Parameter from Standard.Pin to Standard.Parameter
        this.ruleMetaExpert.addDependencyRule(InputPin.class, null, "Matched");
        this.ruleMetaExpert.addDependencyRule(OutputPin.class, null, "Matched");
        this.ruleMetaExpert.addDependencyRule(Pin.class, null, "Matched");
        this.ruleMetaExpert.addDependencyRule(ValuePin.class, null, "Matched");
    }

    @objid ("4b94f4fa-0f77-4be8-9c43-b9b263b6036d")
    protected void registerMetaExpertForInitialNode() {
        // Standard.InitialNode
        // -----------
    }

    @objid ("04e4aefb-199b-42a2-a366-528a09eafd69")
    protected void registerMetaExpertForForkJoinNode() {
        // Standard.ForkJoinNode
        // -----------
    }

    @objid ("abde501d-6930-4639-8b8f-5e7942100655")
    protected void registerMetaExpertForFlowFinalNode() {
        // Standard.FlowFinalNode
        // -----------
    }

    @objid ("b4f431b3-8afe-47ce-8870-144a4c9baccf")
    protected void registerMetaExpertForExpansionRegion() {
        // Standard.ExpansionRegion
        // -----------
        
        // no constraint on ExpansionRegion.OutputElement : ExpansionNode from Standard.ExpansionRegion to Standard.ExpansionNode
        this.ruleMetaExpert.addDependencyRule(ExpansionRegion.class, null, "OutputElement");
        
        // no constraint on ExpansionRegion.InputElement : ExpansionNode from Standard.ExpansionRegion to Standard.ExpansionNode
        this.ruleMetaExpert.addDependencyRule(ExpansionRegion.class, null, "InputElement");
    }

    @objid ("b7c28c9b-738f-4eb0-bfe5-b7781ca670a8")
    protected void registerMetaExpertForExpansionNode() {
        // Standard.ExpansionNode
        // -----------
    }

    @objid ("95a68e83-3aa7-4904-bd2a-9d933e366c79")
    protected void registerMetaExpertForExceptionHandler() {
        // Standard.ExceptionHandler
        // -----------
        
        // no constraint on ExceptionHandler.ExceptionInput : InputPin from Standard.ExceptionHandler to Standard.InputPin
        this.ruleMetaExpert.addDependencyRule(ExceptionHandler.class, null, "ExceptionInput");
        
        // no constraint on ExceptionHandler.ExceptionType : GeneralClass from Standard.ExceptionHandler to Standard.GeneralClass
        this.ruleMetaExpert.addDependencyRule(ExceptionHandler.class, null, "ExceptionType");
    }

    @objid ("4e65d66d-265c-430b-aeb4-6e59e2a91074")
    protected void registerMetaExpertForDecisionMergeNode() {
        // Standard.DecisionMergeNode
        // -----------
    }

    @objid ("b69fc041-72c6-44d3-bdde-cdd554453ed5")
    protected void registerMetaExpertForDataStoreNode() {
        // Standard.DataStoreNode
        // -----------
    }

    @objid ("456a70ce-5b62-40f6-b52e-bba57a509e28")
    protected void registerMetaExpertForControlFlow() {
        // Standard.ControlFlow
        // -----------
    }

    @objid ("45ff19a4-80a7-4a54-804f-a1f80d2fd1da")
    protected void registerMetaExpertForConditionalNode() {
        // Standard.ConditionalNode
        // -----------
        
        // no constraint on ConditionalNode.OwnedClause : Clause from Standard.ConditionalNode to Standard.Clause
        this.ruleMetaExpert.addDependencyRule(ConditionalNode.class, null, "OwnedClause");
    }

    @objid ("51a63b08-c967-4396-bb41-f49b1b900ce9")
    protected void registerMetaExpertForStructuredActivityNode() {
        // Standard.StructuredActivityNode
        // -----------
        
        // no constraint on StructuredActivityNode.Body : ActivityNode from Standard.StructuredActivityNode to Standard.ActivityNode
        this.ruleMetaExpert.addDependencyRule(ConditionalNode.class, null, "Body");
        this.ruleMetaExpert.addDependencyRule(ExpansionRegion.class, null, "Body");
        this.ruleMetaExpert.addDependencyRule(LoopNode.class, null, "Body");
        this.ruleMetaExpert.addDependencyRule(StructuredActivityNode.class, null, "Body");
    }

    @objid ("b0615691-cb72-45c5-82ce-22b3264f374c")
    protected void registerMetaExpertForClause() {
        // Standard.Clause
        // -----------
        
        // no constraint on Clause.Body : ActivityNode from Standard.Clause to Standard.ActivityNode
        this.ruleMetaExpert.addDependencyRule(Clause.class, null, "Body");
    }

    @objid ("b51172d2-6d4c-4ebc-89cf-74034a726809")
    protected void registerMetaExpertForCentralBufferNode() {
        // Standard.CentralBufferNode
        // -----------
    }

    @objid ("c47a5812-2f2a-4d30-8e3c-a24bbcb6fe1f")
    protected void registerMetaExpertForCallOperationAction() {
        // Standard.CallOperationAction
        // -----------
        
        // no constraint on CallOperationAction.Called : Operation from Standard.CallOperationAction to Standard.Operation
        this.ruleMetaExpert.addDependencyRule(CallOperationAction.class, null, "Called");
    }

    @objid ("a1dc8e62-dc51-47f3-be94-06776b29af04")
    protected void registerMetaExpertForCallBehaviorAction() {
        // Standard.CallBehaviorAction
        // -----------
        
        // no constraint on CallBehaviorAction.Called : Behavior from Standard.CallBehaviorAction to Standard.Behavior
        this.ruleMetaExpert.addDependencyRule(CallBehaviorAction.class, null, "Called");
    }

    @objid ("e1407538-a572-4fab-a15b-0065da487a92")
    protected void registerMetaExpertForCallAction() {
        // Standard.CallAction is abstract
        
        // -----------
    }

    @objid ("b6862c43-2730-43e9-8b48-92bfba9e3e47")
    protected void registerMetaExpertForActivityPartition() {
        // Standard.ActivityPartition
        // -----------
        
        // no constraint on ActivityPartition.Represented : UmlModelElement from Standard.ActivityPartition to Standard.UmlModelElement
        this.ruleMetaExpert.addDependencyRule(ActivityPartition.class, null, "Represented");
        
        // no constraint on ActivityPartition.ContainedNode : ActivityNode from Standard.ActivityPartition to Standard.ActivityNode
        this.ruleMetaExpert.addDependencyRule(ActivityPartition.class, null, "ContainedNode");
        
        // no constraint on ActivityPartition.Outgoing : MessageFlow from Standard.ActivityPartition to Standard.MessageFlow
        this.ruleMetaExpert.addDependencyRule(ActivityPartition.class, null, "Outgoing");
        
        // no constraint on ActivityPartition.SubPartition : ActivityPartition from Standard.ActivityPartition to Standard.ActivityPartition
        this.ruleMetaExpert.addDependencyRule(ActivityPartition.class, null, "SubPartition");
    }

    @objid ("952cd88c-4146-4390-9f9c-2b65defcbbb5")
    protected void registerMetaExpertForActivityParameterNode() {
        // Standard.ActivityParameterNode
        // -----------
    }

    @objid ("ce47181c-6380-45d7-836b-edf536a179bc")
    protected void registerMetaExpertForObjectNode() {
        // Standard.ObjectNode is abstract
        
        // -----------
        
        // no constraint on ObjectNode.Represented : Instance from Standard.ObjectNode to Standard.Instance
        this.ruleMetaExpert.addDependencyRule(ActivityParameterNode.class, null, "Represented");
        this.ruleMetaExpert.addDependencyRule(CentralBufferNode.class, null, "Represented");
        this.ruleMetaExpert.addDependencyRule(DataStoreNode.class, null, "Represented");
        this.ruleMetaExpert.addDependencyRule(ExpansionNode.class, null, "Represented");
        this.ruleMetaExpert.addDependencyRule(InputPin.class, null, "Represented");
        this.ruleMetaExpert.addDependencyRule(InstanceNode.class, null, "Represented");
        this.ruleMetaExpert.addDependencyRule(ObjectNode.class, null, "Represented");
        this.ruleMetaExpert.addDependencyRule(OutputPin.class, null, "Represented");
        this.ruleMetaExpert.addDependencyRule(Pin.class, null, "Represented");
        this.ruleMetaExpert.addDependencyRule(ValuePin.class, null, "Represented");
        
        // no constraint on ObjectNode.RepresentedRealParameter : BehaviorParameter from Standard.ObjectNode to Standard.BehaviorParameter
        this.ruleMetaExpert.addDependencyRule(ActivityParameterNode.class, null, "RepresentedRealParameter");
        this.ruleMetaExpert.addDependencyRule(CentralBufferNode.class, null, "RepresentedRealParameter");
        this.ruleMetaExpert.addDependencyRule(DataStoreNode.class, null, "RepresentedRealParameter");
        this.ruleMetaExpert.addDependencyRule(ExpansionNode.class, null, "RepresentedRealParameter");
        this.ruleMetaExpert.addDependencyRule(InputPin.class, null, "RepresentedRealParameter");
        this.ruleMetaExpert.addDependencyRule(InstanceNode.class, null, "RepresentedRealParameter");
        this.ruleMetaExpert.addDependencyRule(ObjectNode.class, null, "RepresentedRealParameter");
        this.ruleMetaExpert.addDependencyRule(OutputPin.class, null, "RepresentedRealParameter");
        this.ruleMetaExpert.addDependencyRule(Pin.class, null, "RepresentedRealParameter");
        this.ruleMetaExpert.addDependencyRule(ValuePin.class, null, "RepresentedRealParameter");
        
        // no constraint on ObjectNode.Type : GeneralClass from Standard.ObjectNode to Standard.GeneralClass
        this.ruleMetaExpert.addDependencyRule(ActivityParameterNode.class, null, "Type");
        this.ruleMetaExpert.addDependencyRule(CentralBufferNode.class, null, "Type");
        this.ruleMetaExpert.addDependencyRule(DataStoreNode.class, null, "Type");
        this.ruleMetaExpert.addDependencyRule(ExpansionNode.class, null, "Type");
        this.ruleMetaExpert.addDependencyRule(InputPin.class, null, "Type");
        this.ruleMetaExpert.addDependencyRule(InstanceNode.class, null, "Type");
        this.ruleMetaExpert.addDependencyRule(ObjectNode.class, null, "Type");
        this.ruleMetaExpert.addDependencyRule(OutputPin.class, null, "Type");
        this.ruleMetaExpert.addDependencyRule(Pin.class, null, "Type");
        this.ruleMetaExpert.addDependencyRule(ValuePin.class, null, "Type");
        
        // no constraint on ObjectNode.RepresentedRole : AssociationEnd from Standard.ObjectNode to Standard.AssociationEnd
        this.ruleMetaExpert.addDependencyRule(ActivityParameterNode.class, null, "RepresentedRole");
        this.ruleMetaExpert.addDependencyRule(CentralBufferNode.class, null, "RepresentedRole");
        this.ruleMetaExpert.addDependencyRule(DataStoreNode.class, null, "RepresentedRole");
        this.ruleMetaExpert.addDependencyRule(ExpansionNode.class, null, "RepresentedRole");
        this.ruleMetaExpert.addDependencyRule(InputPin.class, null, "RepresentedRole");
        this.ruleMetaExpert.addDependencyRule(InstanceNode.class, null, "RepresentedRole");
        this.ruleMetaExpert.addDependencyRule(ObjectNode.class, null, "RepresentedRole");
        this.ruleMetaExpert.addDependencyRule(OutputPin.class, null, "RepresentedRole");
        this.ruleMetaExpert.addDependencyRule(Pin.class, null, "RepresentedRole");
        this.ruleMetaExpert.addDependencyRule(ValuePin.class, null, "RepresentedRole");
        
        // no constraint on ObjectNode.RepresentedAttribute : Attribute from Standard.ObjectNode to Standard.Attribute
        this.ruleMetaExpert.addDependencyRule(ActivityParameterNode.class, null, "RepresentedAttribute");
        this.ruleMetaExpert.addDependencyRule(CentralBufferNode.class, null, "RepresentedAttribute");
        this.ruleMetaExpert.addDependencyRule(DataStoreNode.class, null, "RepresentedAttribute");
        this.ruleMetaExpert.addDependencyRule(ExpansionNode.class, null, "RepresentedAttribute");
        this.ruleMetaExpert.addDependencyRule(InputPin.class, null, "RepresentedAttribute");
        this.ruleMetaExpert.addDependencyRule(InstanceNode.class, null, "RepresentedAttribute");
        this.ruleMetaExpert.addDependencyRule(ObjectNode.class, null, "RepresentedAttribute");
        this.ruleMetaExpert.addDependencyRule(OutputPin.class, null, "RepresentedAttribute");
        this.ruleMetaExpert.addDependencyRule(Pin.class, null, "RepresentedAttribute");
        this.ruleMetaExpert.addDependencyRule(ValuePin.class, null, "RepresentedAttribute");
        
        // no constraint on ObjectNode.InState : State from Standard.ObjectNode to Standard.State
        this.ruleMetaExpert.addDependencyRule(ActivityParameterNode.class, null, "InState");
        this.ruleMetaExpert.addDependencyRule(CentralBufferNode.class, null, "InState");
        this.ruleMetaExpert.addDependencyRule(DataStoreNode.class, null, "InState");
        this.ruleMetaExpert.addDependencyRule(ExpansionNode.class, null, "InState");
        this.ruleMetaExpert.addDependencyRule(InputPin.class, null, "InState");
        this.ruleMetaExpert.addDependencyRule(InstanceNode.class, null, "InState");
        this.ruleMetaExpert.addDependencyRule(ObjectNode.class, null, "InState");
        this.ruleMetaExpert.addDependencyRule(OutputPin.class, null, "InState");
        this.ruleMetaExpert.addDependencyRule(Pin.class, null, "InState");
        this.ruleMetaExpert.addDependencyRule(ValuePin.class, null, "InState");
    }

    @objid ("a3f8ef30-a352-4e19-a733-6b371d6916b8")
    protected void registerMetaExpertForActivityGroup() {
        // Standard.ActivityGroup is abstract
        
        // -----------
    }

    @objid ("5109f1c5-060c-41c1-9ce5-c22d66e4431d")
    protected void registerMetaExpertForActivityFinalNode() {
        // Standard.ActivityFinalNode
        // -----------
    }

    @objid ("65ec41b4-666d-4520-b4de-457922809c6d")
    protected void registerMetaExpertForFinalNode() {
        // Standard.FinalNode is abstract
        
        // -----------
    }

    @objid ("4c0e0dba-7f91-43ea-8e61-ddb3af74f9df")
    protected void registerMetaExpertForControlNode() {
        // Standard.ControlNode is abstract
        
        // -----------
    }

    @objid ("0451e5e0-3dfd-4deb-96fe-2513851e7ff6")
    protected void registerMetaExpertForActivityEdge() {
        // Standard.ActivityEdge is abstract
        
        // -----------
        
        // no constraint on ActivityEdge.Target : ActivityNode from Standard.ActivityEdge to Standard.ActivityNode
        this.ruleMetaExpert.addDependencyRule(ActivityEdge.class, null, "Target");
        this.ruleMetaExpert.addDependencyRule(ControlFlow.class, null, "Target");
        this.ruleMetaExpert.addDependencyRule(MessageFlow.class, null, "Target");
        this.ruleMetaExpert.addDependencyRule(ObjectFlow.class, null, "Target");
    }

    @objid ("ae017834-1358-408e-9bc2-04db5a827a40")
    protected void registerMetaExpertForActivity() {
        // Standard.Activity
        // -----------
        
        // no constraint on Activity.OwnedGroup : ActivityGroup from Standard.Activity to Standard.ActivityGroup
        this.ruleMetaExpert.addDependencyRule(Activity.class, null, "OwnedGroup");
        
        // no constraint on Activity.OwnedNode : ActivityNode from Standard.Activity to Standard.ActivityNode
        this.ruleMetaExpert.addDependencyRule(Activity.class, null, "OwnedNode");
    }

    @objid ("7dcf2a09-1004-424f-bfe5-bfb27c0b12c6")
    protected void registerMetaExpertForAcceptTimeEventAction() {
        // Standard.AcceptTimeEventAction
        // -----------
    }

    @objid ("c9714c36-6b90-40ba-a4b4-ef46d2bd54ad")
    protected void registerMetaExpertForAcceptSignalAction() {
        // Standard.AcceptSignalAction
        // -----------
        
        // no constraint on AcceptSignalAction.Accepted : Signal from Standard.AcceptSignalAction to Standard.Signal
        this.ruleMetaExpert.addDependencyRule(AcceptSignalAction.class, null, "Accepted");
    }

    @objid ("fb79e097-285a-4803-9012-fcd45cbaa0de")
    protected void registerMetaExpertForAcceptChangeEventAction() {
        // Standard.AcceptChangeEventAction
        // -----------
    }

    @objid ("11872acd-0e07-4461-b4d9-b8eac854cfd9")
    protected void registerMetaExpertForAcceptCallEventAction() {
        // Standard.AcceptCallEventAction
        // -----------
        
        // no constraint on AcceptCallEventAction.Called : Operation from Standard.AcceptCallEventAction to Standard.Operation
        this.ruleMetaExpert.addDependencyRule(AcceptCallEventAction.class, null, "Called");
    }

    @objid ("45f4057c-efc3-4acb-89c2-c3aa24595592")
    protected void registerMetaExpertForActivityAction() {
        // Standard.ActivityAction is abstract
        
        // -----------
        
        // no constraint on ActivityAction.Output : OutputPin from Standard.ActivityAction to Standard.OutputPin
        this.ruleMetaExpert.addDependencyRule(AcceptCallEventAction.class, null, "Output");
        this.ruleMetaExpert.addDependencyRule(AcceptChangeEventAction.class, null, "Output");
        this.ruleMetaExpert.addDependencyRule(AcceptSignalAction.class, null, "Output");
        this.ruleMetaExpert.addDependencyRule(AcceptTimeEventAction.class, null, "Output");
        this.ruleMetaExpert.addDependencyRule(ActivityAction.class, null, "Output");
        this.ruleMetaExpert.addDependencyRule(CallAction.class, null, "Output");
        this.ruleMetaExpert.addDependencyRule(CallBehaviorAction.class, null, "Output");
        this.ruleMetaExpert.addDependencyRule(CallOperationAction.class, null, "Output");
        this.ruleMetaExpert.addDependencyRule(ConditionalNode.class, null, "Output");
        this.ruleMetaExpert.addDependencyRule(ExpansionRegion.class, null, "Output");
        this.ruleMetaExpert.addDependencyRule(LoopNode.class, null, "Output");
        this.ruleMetaExpert.addDependencyRule(OpaqueAction.class, null, "Output");
        this.ruleMetaExpert.addDependencyRule(SendSignalAction.class, null, "Output");
        this.ruleMetaExpert.addDependencyRule(StructuredActivityNode.class, null, "Output");
        
        // no constraint on ActivityAction.Input : InputPin from Standard.ActivityAction to Standard.InputPin
        this.ruleMetaExpert.addDependencyRule(AcceptCallEventAction.class, null, "Input");
        this.ruleMetaExpert.addDependencyRule(AcceptChangeEventAction.class, null, "Input");
        this.ruleMetaExpert.addDependencyRule(AcceptSignalAction.class, null, "Input");
        this.ruleMetaExpert.addDependencyRule(AcceptTimeEventAction.class, null, "Input");
        this.ruleMetaExpert.addDependencyRule(ActivityAction.class, null, "Input");
        this.ruleMetaExpert.addDependencyRule(CallAction.class, null, "Input");
        this.ruleMetaExpert.addDependencyRule(CallBehaviorAction.class, null, "Input");
        this.ruleMetaExpert.addDependencyRule(CallOperationAction.class, null, "Input");
        this.ruleMetaExpert.addDependencyRule(ConditionalNode.class, null, "Input");
        this.ruleMetaExpert.addDependencyRule(ExpansionRegion.class, null, "Input");
        this.ruleMetaExpert.addDependencyRule(LoopNode.class, null, "Input");
        this.ruleMetaExpert.addDependencyRule(OpaqueAction.class, null, "Input");
        this.ruleMetaExpert.addDependencyRule(SendSignalAction.class, null, "Input");
        this.ruleMetaExpert.addDependencyRule(StructuredActivityNode.class, null, "Input");
        
        // no constraint on ActivityAction.Handler : ExceptionHandler from Standard.ActivityAction to Standard.ExceptionHandler
        this.ruleMetaExpert.addDependencyRule(AcceptCallEventAction.class, null, "Handler");
        this.ruleMetaExpert.addDependencyRule(AcceptChangeEventAction.class, null, "Handler");
        this.ruleMetaExpert.addDependencyRule(AcceptSignalAction.class, null, "Handler");
        this.ruleMetaExpert.addDependencyRule(AcceptTimeEventAction.class, null, "Handler");
        this.ruleMetaExpert.addDependencyRule(ActivityAction.class, null, "Handler");
        this.ruleMetaExpert.addDependencyRule(CallAction.class, null, "Handler");
        this.ruleMetaExpert.addDependencyRule(CallBehaviorAction.class, null, "Handler");
        this.ruleMetaExpert.addDependencyRule(CallOperationAction.class, null, "Handler");
        this.ruleMetaExpert.addDependencyRule(ConditionalNode.class, null, "Handler");
        this.ruleMetaExpert.addDependencyRule(ExpansionRegion.class, null, "Handler");
        this.ruleMetaExpert.addDependencyRule(LoopNode.class, null, "Handler");
        this.ruleMetaExpert.addDependencyRule(OpaqueAction.class, null, "Handler");
        this.ruleMetaExpert.addDependencyRule(SendSignalAction.class, null, "Handler");
        this.ruleMetaExpert.addDependencyRule(StructuredActivityNode.class, null, "Handler");
    }

    @objid ("692b8121-78b4-4d25-83eb-c27ec88a234e")
    protected void registerMetaExpertForActivityNode() {
        // Standard.ActivityNode is abstract
        
        // -----------
        
        // no constraint on ActivityNode.Outgoing : ActivityEdge from Standard.ActivityNode to Standard.ActivityEdge
        this.ruleMetaExpert.addDependencyRule(AcceptCallEventAction.class, null, "Outgoing");
        this.ruleMetaExpert.addDependencyRule(AcceptChangeEventAction.class, null, "Outgoing");
        this.ruleMetaExpert.addDependencyRule(AcceptSignalAction.class, null, "Outgoing");
        this.ruleMetaExpert.addDependencyRule(AcceptTimeEventAction.class, null, "Outgoing");
        this.ruleMetaExpert.addDependencyRule(ActivityAction.class, null, "Outgoing");
        this.ruleMetaExpert.addDependencyRule(ActivityFinalNode.class, null, "Outgoing");
        this.ruleMetaExpert.addDependencyRule(ActivityNode.class, null, "Outgoing");
        this.ruleMetaExpert.addDependencyRule(ActivityParameterNode.class, null, "Outgoing");
        this.ruleMetaExpert.addDependencyRule(CallAction.class, null, "Outgoing");
        this.ruleMetaExpert.addDependencyRule(CallBehaviorAction.class, null, "Outgoing");
        this.ruleMetaExpert.addDependencyRule(CallOperationAction.class, null, "Outgoing");
        this.ruleMetaExpert.addDependencyRule(CentralBufferNode.class, null, "Outgoing");
        this.ruleMetaExpert.addDependencyRule(ConditionalNode.class, null, "Outgoing");
        this.ruleMetaExpert.addDependencyRule(ControlNode.class, null, "Outgoing");
        this.ruleMetaExpert.addDependencyRule(DataStoreNode.class, null, "Outgoing");
        this.ruleMetaExpert.addDependencyRule(DecisionMergeNode.class, null, "Outgoing");
        this.ruleMetaExpert.addDependencyRule(ExpansionNode.class, null, "Outgoing");
        this.ruleMetaExpert.addDependencyRule(ExpansionRegion.class, null, "Outgoing");
        this.ruleMetaExpert.addDependencyRule(FinalNode.class, null, "Outgoing");
        this.ruleMetaExpert.addDependencyRule(FlowFinalNode.class, null, "Outgoing");
        this.ruleMetaExpert.addDependencyRule(ForkJoinNode.class, null, "Outgoing");
        this.ruleMetaExpert.addDependencyRule(InitialNode.class, null, "Outgoing");
        this.ruleMetaExpert.addDependencyRule(InputPin.class, null, "Outgoing");
        this.ruleMetaExpert.addDependencyRule(InstanceNode.class, null, "Outgoing");
        this.ruleMetaExpert.addDependencyRule(LoopNode.class, null, "Outgoing");
        this.ruleMetaExpert.addDependencyRule(ObjectNode.class, null, "Outgoing");
        this.ruleMetaExpert.addDependencyRule(OpaqueAction.class, null, "Outgoing");
        this.ruleMetaExpert.addDependencyRule(OutputPin.class, null, "Outgoing");
        this.ruleMetaExpert.addDependencyRule(Pin.class, null, "Outgoing");
        this.ruleMetaExpert.addDependencyRule(SendSignalAction.class, null, "Outgoing");
        this.ruleMetaExpert.addDependencyRule(StructuredActivityNode.class, null, "Outgoing");
        this.ruleMetaExpert.addDependencyRule(ValuePin.class, null, "Outgoing");
    }

    @objid ("3ab42e7f-6172-40d2-9e48-48dd7072d26e")
    protected void registerMetaExpertForUseCaseDiagram() {
        // Standard.UseCaseDiagram
        // -----------
    }

    @objid ("2fdf7565-8d26-4f6f-ad35-1f36a1d30349")
    protected void registerMetaExpertForStateMachineDiagram() {
        // Standard.StateMachineDiagram
        // -----------
    }

    @objid ("b186948c-3dc5-4ff4-8882-7a2e1530833d")
    protected void registerMetaExpertForSequenceDiagram() {
        // Standard.SequenceDiagram
        // -----------
    }

    @objid ("059c70b5-2abe-4378-8ec9-4fb0a5bb820c")
    protected void registerMetaExpertForObjectDiagram() {
        // Standard.ObjectDiagram
        // -----------
    }

    @objid ("98b436f1-b8df-401e-ba19-2085171b818f")
    protected void registerMetaExpertForDeploymentDiagram() {
        // Standard.DeploymentDiagram
        // -----------
    }

    @objid ("975f20d4-13e6-4dfd-a1cb-d82daebe07c2")
    protected void registerMetaExpertForCompositeStructureDiagram() {
        // Standard.CompositeStructureDiagram
        // -----------
    }

    @objid ("998e254e-8cf5-4799-993c-1621bc4afba2")
    protected void registerMetaExpertForCommunicationDiagram() {
        // Standard.CommunicationDiagram
        // -----------
    }

    @objid ("02014494-bb4e-4313-94d7-ca3ec4da9755")
    protected void registerMetaExpertForClassDiagram() {
        // Standard.ClassDiagram
        // -----------
    }

    @objid ("b706d137-4fc2-4b2b-b454-9227451d04e6")
    protected void registerMetaExpertForStaticDiagram() {
        // Standard.StaticDiagram
        // -----------
    }

    @objid ("6d3182d7-05f8-4299-8d92-b0a3b63701fc")
    protected void registerMetaExpertForActivityDiagram() {
        // Standard.ActivityDiagram
        // -----------
    }

    @objid ("f878b702-79ca-4434-b0fb-5364403cb1a3")
    protected void registerMetaExpertForBpmnSharedDefinitions() {
        // Standard.BpmnSharedDefinitions
        // -----------
        
        // no constraint on BpmnSharedDefinitions.RootElement : BpmnSharedElement from Standard.BpmnSharedDefinitions to Standard.BpmnSharedElement
        this.ruleMetaExpert.addDependencyRule(BpmnSharedDefinitions.class, null, "RootElement");
    }

    @objid ("6f66f16a-702f-4a7d-bdb5-efa6e755c9d8")
    protected void registerMetaExpertForBpmnGroup() {
        // Standard.BpmnGroup
        // -----------
        
        // no constraint on BpmnGroup.Categorized : BpmnFlowElement from Standard.BpmnGroup to Standard.BpmnFlowElement
        this.ruleMetaExpert.addDependencyRule(BpmnGroup.class, null, "Categorized");
    }

    @objid ("bfc7545c-8b32-4db8-a743-3a4227eed5e9")
    protected void registerMetaExpertForBpmnAssociation() {
        // Standard.BpmnAssociation
        // -----------
        
        // no constraint on BpmnAssociation.TargetRef : BpmnBaseElement from Standard.BpmnAssociation to Standard.BpmnBaseElement
        this.ruleMetaExpert.addDependencyRule(BpmnAssociation.class, null, "TargetRef");
        
        // no constraint on BpmnAssociation.SourceRef : BpmnBaseElement from Standard.BpmnAssociation to Standard.BpmnBaseElement
        this.ruleMetaExpert.addDependencyRule(BpmnAssociation.class, null, "SourceRef");
    }

    @objid ("0b5887cd-e202-4365-b836-1c27770cca71")
    protected void registerMetaExpertForBpmnArtifact() {
        // Standard.BpmnArtifact is abstract
        
        // -----------
    }

    @objid ("245fa2fb-2fa8-45ac-a5e5-bd1747732442")
    protected void registerMetaExpertForBpmnResourceRole() {
        // Standard.BpmnResourceRole
        // -----------
        
        // no constraint on BpmnResourceRole.ResourceRef : BpmnResource from Standard.BpmnResourceRole to Standard.BpmnResource
        this.ruleMetaExpert.addDependencyRule(BpmnResourceRole.class, null, "ResourceRef");
        
        // no constraint on BpmnResourceRole.ResourceParameterBinding : BpmnResourceParameterBinding from Standard.BpmnResourceRole to Standard.BpmnResourceParameterBinding
        this.ruleMetaExpert.addDependencyRule(BpmnResourceRole.class, null, "ResourceParameterBinding");
    }

    @objid ("ae2340f3-e8c8-4f30-a946-c584ff0e93bb")
    protected void registerMetaExpertForBpmnResourceParameterBinding() {
        // Standard.BpmnResourceParameterBinding
        // -----------
        
        // no constraint on BpmnResourceParameterBinding.ParameterRef : BpmnResourceParameter from Standard.BpmnResourceParameterBinding to Standard.BpmnResourceParameter
        this.ruleMetaExpert.addDependencyRule(BpmnResourceParameterBinding.class, null, "ParameterRef");
    }

    @objid ("8b75dcff-b1ef-4a96-8301-24dfb5b6eb7b")
    protected void registerMetaExpertForBpmnResourceParameter() {
        // Standard.BpmnResourceParameter
        // -----------
        
        // no constraint on BpmnResourceParameter.Type : BpmnItemDefinition from Standard.BpmnResourceParameter to Standard.BpmnItemDefinition
        this.ruleMetaExpert.addDependencyRule(BpmnResourceParameter.class, null, "Type");
    }

    @objid ("216ed0c9-f416-4d98-9b6a-d6caccf5f73e")
    protected void registerMetaExpertForBpmnResource() {
        // Standard.BpmnResource
        // -----------
        
        // no constraint on BpmnResource.Parameter : BpmnResourceParameter from Standard.BpmnResource to Standard.BpmnResourceParameter
        this.ruleMetaExpert.addDependencyRule(BpmnResource.class, null, "Parameter");
    }

    @objid ("bcfd04bc-9bf3-4722-9b76-8a65aef500b3")
    protected void registerMetaExpertForBpmnProcess() {
        // Standard.BpmnProcess
        // -----------
        
        // no constraint on BpmnProcess.Supports : BpmnProcess from Standard.BpmnProcess to Standard.BpmnProcess
        this.ruleMetaExpert.addDependencyRule(BpmnProcess.class, null, "Supports");
        
        // no constraint on BpmnProcess.Artifact : BpmnArtifact from Standard.BpmnProcess to Standard.BpmnArtifact
        this.ruleMetaExpert.addDependencyRule(BpmnProcess.class, null, "Artifact");
        
        // no constraint on BpmnProcess.LaneSet : BpmnLaneSet from Standard.BpmnProcess to Standard.BpmnLaneSet
        this.ruleMetaExpert.addDependencyRule(BpmnProcess.class, null, "LaneSet");
        
        // no constraint on BpmnProcess.FlowElement : BpmnFlowElement from Standard.BpmnProcess to Standard.BpmnFlowElement
        this.ruleMetaExpert.addDependencyRule(BpmnProcess.class, null, "FlowElement");
        
        // no constraint on BpmnProcess.Resource : BpmnResourceRole from Standard.BpmnProcess to Standard.BpmnResourceRole
        this.ruleMetaExpert.addDependencyRule(BpmnProcess.class, null, "Resource");
        
        // no constraint on BpmnProcess.DefinitionalCollaboration : BpmnCollaboration from Standard.BpmnProcess to Standard.BpmnCollaboration
        this.ruleMetaExpert.addDependencyRule(BpmnProcess.class, null, "DefinitionalCollaboration");
    }

    @objid ("9f6b315c-d2f6-4b12-a3eb-1af4b46feee8")
    protected void registerMetaExpertForBpmnParticipant() {
        // Standard.BpmnParticipant
        // -----------
        
        // no constraint on BpmnParticipant.Process : BpmnProcess from Standard.BpmnParticipant to Standard.BpmnProcess
        this.ruleMetaExpert.addDependencyRule(BpmnParticipant.class, null, "Process");
        
        // no constraint on BpmnParticipant.EndPointRefs : BpmnEndPoint from Standard.BpmnParticipant to Standard.BpmnEndPoint
        this.ruleMetaExpert.addDependencyRule(BpmnParticipant.class, null, "EndPointRefs");
        
        // no constraint on BpmnParticipant.InterfaceRefs : BpmnInterface from Standard.BpmnParticipant to Standard.BpmnInterface
        this.ruleMetaExpert.addDependencyRule(BpmnParticipant.class, null, "InterfaceRefs");
    }

    @objid ("5680c31b-eb27-4824-af3d-cd001670a93d")
    protected void registerMetaExpertForBpmnLaneSet() {
        // Standard.BpmnLaneSet
        // -----------
        
        // no constraint on BpmnLaneSet.Lane : BpmnLane from Standard.BpmnLaneSet to Standard.BpmnLane
        this.ruleMetaExpert.addDependencyRule(BpmnLaneSet.class, null, "Lane");
    }

    @objid ("4c2341c2-dcc3-4c5a-a6e8-7152bfd5c663")
    protected void registerMetaExpertForBpmnLane() {
        // Standard.BpmnLane
        // -----------
        
        // no constraint on BpmnLane.ChildLaneSet : BpmnLaneSet from Standard.BpmnLane to Standard.BpmnLaneSet
        this.ruleMetaExpert.addDependencyRule(BpmnLane.class, null, "ChildLaneSet");
        
        // no constraint on BpmnLane.FlowElementRef : BpmnFlowElement from Standard.BpmnLane to Standard.BpmnFlowElement
        this.ruleMetaExpert.addDependencyRule(BpmnLane.class, null, "FlowElementRef");
        
        // no constraint on BpmnLane.BpmnPartitionElementRef : BpmnBaseElement from Standard.BpmnLane to Standard.BpmnBaseElement
        this.ruleMetaExpert.addDependencyRule(BpmnLane.class, null, "BpmnPartitionElementRef");
    }

    @objid ("1d04a1c7-e62a-439f-8fd0-473c993de7f1")
    protected void registerMetaExpertForBpmnCollaboration() {
        // Standard.BpmnCollaboration
        // -----------
        
        // no constraint on BpmnCollaboration.Artifact : BpmnArtifact from Standard.BpmnCollaboration to Standard.BpmnArtifact
        this.ruleMetaExpert.addDependencyRule(BpmnCollaboration.class, null, "Artifact");
        
        // no constraint on BpmnCollaboration.MessageFlow : BpmnMessageFlow from Standard.BpmnCollaboration to Standard.BpmnMessageFlow
        this.ruleMetaExpert.addDependencyRule(BpmnCollaboration.class, null, "MessageFlow");
        
        // no constraint on BpmnCollaboration.Participants : BpmnParticipant from Standard.BpmnCollaboration to Standard.BpmnParticipant
        this.ruleMetaExpert.addDependencyRule(BpmnCollaboration.class, null, "Participants");
        
        // no constraint on BpmnCollaboration.Messages : BpmnMessage from Standard.BpmnCollaboration to Standard.BpmnMessage
        this.ruleMetaExpert.addDependencyRule(BpmnCollaboration.class, null, "Messages");
    }

    @objid ("130c72dc-9789-4450-9826-3d9b56809cec")
    protected void registerMetaExpertForBehavior() {
        // Standard.Behavior is abstract
        
        // -----------
        
        // no constraint on Behavior.Parameter : BehaviorParameter from Standard.Behavior to Standard.BehaviorParameter
        this.ruleMetaExpert.addDependencyRule(Activity.class, null, "Parameter");
        this.ruleMetaExpert.addDependencyRule(Behavior.class, null, "Parameter");
        this.ruleMetaExpert.addDependencyRule(BpmnCollaboration.class, null, "Parameter");
        this.ruleMetaExpert.addDependencyRule(BpmnProcess.class, null, "Parameter");
        this.ruleMetaExpert.addDependencyRule(BpmnSharedDefinitions.class, null, "Parameter");
        this.ruleMetaExpert.addDependencyRule(CommunicationInteraction.class, null, "Parameter");
        this.ruleMetaExpert.addDependencyRule(Interaction.class, null, "Parameter");
        this.ruleMetaExpert.addDependencyRule(OpaqueBehavior.class, null, "Parameter");
        this.ruleMetaExpert.addDependencyRule(StateMachine.class, null, "Parameter");
        
        // no constraint on Behavior.OwnedCollaboration : Collaboration from Standard.Behavior to Standard.Collaboration
        this.ruleMetaExpert.addDependencyRule(Activity.class, null, "OwnedCollaboration");
        this.ruleMetaExpert.addDependencyRule(Behavior.class, null, "OwnedCollaboration");
        this.ruleMetaExpert.addDependencyRule(BpmnCollaboration.class, null, "OwnedCollaboration");
        this.ruleMetaExpert.addDependencyRule(BpmnProcess.class, null, "OwnedCollaboration");
        this.ruleMetaExpert.addDependencyRule(BpmnSharedDefinitions.class, null, "OwnedCollaboration");
        this.ruleMetaExpert.addDependencyRule(CommunicationInteraction.class, null, "OwnedCollaboration");
        this.ruleMetaExpert.addDependencyRule(Interaction.class, null, "OwnedCollaboration");
        this.ruleMetaExpert.addDependencyRule(OpaqueBehavior.class, null, "OwnedCollaboration");
        this.ruleMetaExpert.addDependencyRule(StateMachine.class, null, "OwnedCollaboration");
        
        // no constraint on Behavior.EComponent : Event from Standard.Behavior to Standard.Event
        this.ruleMetaExpert.addDependencyRule(Activity.class, null, "EComponent");
        this.ruleMetaExpert.addDependencyRule(Behavior.class, null, "EComponent");
        this.ruleMetaExpert.addDependencyRule(BpmnCollaboration.class, null, "EComponent");
        this.ruleMetaExpert.addDependencyRule(BpmnProcess.class, null, "EComponent");
        this.ruleMetaExpert.addDependencyRule(BpmnSharedDefinitions.class, null, "EComponent");
        this.ruleMetaExpert.addDependencyRule(CommunicationInteraction.class, null, "EComponent");
        this.ruleMetaExpert.addDependencyRule(Interaction.class, null, "EComponent");
        this.ruleMetaExpert.addDependencyRule(OpaqueBehavior.class, null, "EComponent");
        this.ruleMetaExpert.addDependencyRule(StateMachine.class, null, "EComponent");
    }

    @objid ("9f87de41-5cb2-47d0-812f-8b5b5004ffe5")
    protected void registerMetaExpertForUmlModelElement() {
        // Standard.UmlModelElement is abstract
        
        // -----------
        
        // no constraint on UmlModelElement.ConstraintDefinition : Constraint from Standard.UmlModelElement to Standard.Constraint
        this.ruleMetaExpert.addDependencyRule(AbstractPseudoState.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(AcceptCallEventAction.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(AcceptChangeEventAction.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(AcceptSignalAction.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(AcceptTimeEventAction.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(Activity.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(ActivityAction.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(ActivityEdge.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(ActivityFinalNode.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(ActivityGroup.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(ActivityNode.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(ActivityParameterNode.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(ActivityPartition.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(Actor.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(Artifact.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(Association.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(AssociationEnd.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(Attribute.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(AttributeLink.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(Behavior.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(BehaviorParameter.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(BehavioralFeature.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(BindableInstance.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(Binding.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(BpmnCollaboration.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(BpmnProcess.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(BpmnSharedDefinitions.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(CallAction.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(CallBehaviorAction.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(CallOperationAction.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(CentralBufferNode.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(ChoicePseudoState.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(Class.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(ClassAssociation.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(Classifier.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(Clause.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(Collaboration.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(CollaborationUse.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(CombinedFragment.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(CommunicationChannel.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(CommunicationInteraction.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(CommunicationMessage.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(CommunicationNode.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(Component.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(ComponentRealization.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(ConditionalNode.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(ConnectionPointReference.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(Connector.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(ConnectorEnd.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(Constraint.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(ControlFlow.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(ControlNode.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(DataFlow.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(DataStoreNode.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(DataType.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(DecisionMergeNode.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(DeepHistoryPseudoState.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(DurationConstraint.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(ElementImport.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(EntryPointPseudoState.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(Enumeration.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(EnumerationLiteral.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(Event.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(ExceptionHandler.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(ExecutionOccurenceSpecification.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(ExecutionSpecification.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(ExitPointPseudoState.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(ExpansionNode.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(ExpansionRegion.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(ExtensionPoint.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(Feature.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(FinalNode.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(FinalState.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(FlowFinalNode.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(ForkJoinNode.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(ForkPseudoState.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(Gate.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(GeneralClass.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(Generalization.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(InformationFlow.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(InformationItem.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(InitialNode.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(InitialPseudoState.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(InputPin.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(Instance.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(InstanceNode.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(Interaction.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(InteractionFragment.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(InteractionOperand.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(InteractionUse.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(Interface.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(InterfaceRealization.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(InternalTransition.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(InterruptibleActivityRegion.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(JoinPseudoState.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(JunctionPseudoState.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(Lifeline.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(Link.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(LinkEnd.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(LoopNode.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(Manifestation.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(Message.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(MessageEnd.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(MessageFlow.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(ModelTree.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(NameSpace.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(NaryAssociation.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(NaryAssociationEnd.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(NaryConnector.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(NaryConnectorEnd.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(NaryLink.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(NaryLinkEnd.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(Node.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(ObjectFlow.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(ObjectNode.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(OccurrenceSpecification.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(OpaqueAction.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(OpaqueBehavior.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(Operation.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(OutputPin.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(Package.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(PackageImport.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(PackageMerge.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(Parameter.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(PartDecomposition.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(Pin.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(Port.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(ProvidedInterface.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(RaisedException.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(Region.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(RequiredInterface.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(SendSignalAction.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(ShallowHistoryPseudoState.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(Signal.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(State.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(StateInvariant.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(StateMachine.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(StateVertex.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(StructuralFeature.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(StructuredActivityNode.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(Substitution.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(TemplateBinding.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(TemplateParameter.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(TemplateParameterSubstitution.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(TerminatePseudoState.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(TerminateSpecification.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(Transition.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(UmlModelElement.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(UseCase.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(UseCaseDependency.class, null, "ConstraintDefinition");
        this.ruleMetaExpert.addDependencyRule(ValuePin.class, null, "ConstraintDefinition");
    }

    @objid ("220bec63-01f6-4bbf-ad00-65ec515a7c55")
    protected void registerMetaExpertForBpmnSequenceFlowDataAssociation() {
        // Standard.BpmnSequenceFlowDataAssociation
        // -----------
        
        // no constraint on BpmnSequenceFlowDataAssociation.Connected : BpmnSequenceFlow from Standard.BpmnSequenceFlowDataAssociation to Standard.BpmnSequenceFlow
        this.ruleMetaExpert.addDependencyRule(BpmnSequenceFlowDataAssociation.class, null, "Connected");
        
        // no constraint on BpmnSequenceFlowDataAssociation.DataAssociation : BpmnDataAssociation from Standard.BpmnSequenceFlowDataAssociation to Standard.BpmnDataAssociation
        this.ruleMetaExpert.addDependencyRule(BpmnSequenceFlowDataAssociation.class, null, "DataAssociation");
    }

    @objid ("20624a20-9eb6-4555-a26f-858241fdc1c2")
    protected void registerMetaExpertForBpmnItemDefinition() {
        // Standard.BpmnItemDefinition
        // -----------
    }

    @objid ("cef4ef39-6e1e-44ed-9c01-c9f42413863a")
    protected void registerMetaExpertForBpmnDataStore() {
        // Standard.BpmnDataStore
        // -----------
    }

    @objid ("d1ac0f56-dec6-47a0-ad4c-bcb4b2c42f58")
    protected void registerMetaExpertForBpmnDataState() {
        // Standard.BpmnDataState
        // -----------
    }

    @objid ("9d5ce853-1097-4cab-b73f-e1fdd2634fe6")
    protected void registerMetaExpertForBpmnDataOutput() {
        // Standard.BpmnDataOutput
        // -----------
    }

    @objid ("8aa72331-b659-4deb-b14b-36474da6caba")
    protected void registerMetaExpertForBpmnDataObject() {
        // Standard.BpmnDataObject
        // -----------
    }

    @objid ("4254b555-5e89-4efc-bb16-37e66603d331")
    protected void registerMetaExpertForBpmnDataInput() {
        // Standard.BpmnDataInput
        // -----------
    }

    @objid ("5006f998-1a2b-47f8-8a83-70779f9701f8")
    protected void registerMetaExpertForBpmnItemAwareElement() {
        // Standard.BpmnItemAwareElement is abstract
        
        // -----------
        
        // no constraint on BpmnItemAwareElement.ItemSubjectRef : BpmnItemDefinition from Standard.BpmnItemAwareElement to Standard.BpmnItemDefinition
        this.ruleMetaExpert.addDependencyRule(BpmnDataInput.class, null, "ItemSubjectRef");
        this.ruleMetaExpert.addDependencyRule(BpmnDataObject.class, null, "ItemSubjectRef");
        this.ruleMetaExpert.addDependencyRule(BpmnDataOutput.class, null, "ItemSubjectRef");
        this.ruleMetaExpert.addDependencyRule(BpmnDataStore.class, null, "ItemSubjectRef");
        this.ruleMetaExpert.addDependencyRule(BpmnItemAwareElement.class, null, "ItemSubjectRef");
        
        // no constraint on BpmnItemAwareElement.DataState : BpmnDataState from Standard.BpmnItemAwareElement to Standard.BpmnDataState
        this.ruleMetaExpert.addDependencyRule(BpmnDataInput.class, null, "DataState");
        this.ruleMetaExpert.addDependencyRule(BpmnDataObject.class, null, "DataState");
        this.ruleMetaExpert.addDependencyRule(BpmnDataOutput.class, null, "DataState");
        this.ruleMetaExpert.addDependencyRule(BpmnDataStore.class, null, "DataState");
        this.ruleMetaExpert.addDependencyRule(BpmnItemAwareElement.class, null, "DataState");
    }

    @objid ("551f083c-2c4b-4918-9d1d-12cba6a39372")
    protected void registerMetaExpertForBpmnDataAssociation() {
        // Standard.BpmnDataAssociation
        // -----------
        
        // no constraint on BpmnDataAssociation.SourceRef : BpmnItemAwareElement from Standard.BpmnDataAssociation to Standard.BpmnItemAwareElement
        this.ruleMetaExpert.addDependencyRule(BpmnDataAssociation.class, null, "SourceRef");
        
        // no constraint on BpmnDataAssociation.TargetRef : BpmnItemAwareElement from Standard.BpmnDataAssociation to Standard.BpmnItemAwareElement
        this.ruleMetaExpert.addDependencyRule(BpmnDataAssociation.class, null, "TargetRef");
    }

    @objid ("81561af4-b36b-48cb-8a59-d62b735d96c4")
    protected void registerMetaExpertForBpmnParallelGateway() {
        // Standard.BpmnParallelGateway
        // -----------
    }

    @objid ("452aef5e-d82b-43d6-8e1e-f352d5babf7e")
    protected void registerMetaExpertForBpmnInclusiveGateway() {
        // Standard.BpmnInclusiveGateway
        // -----------
        
        // no constraint on BpmnInclusiveGateway.DefaultFlow : BpmnSequenceFlow from Standard.BpmnInclusiveGateway to Standard.BpmnSequenceFlow
        this.ruleMetaExpert.addDependencyRule(BpmnInclusiveGateway.class, null, "DefaultFlow");
    }

    @objid ("2d9d9a45-3415-4b19-93b1-7e99312aea52")
    protected void registerMetaExpertForBpmnExclusiveGateway() {
        // Standard.BpmnExclusiveGateway
        // -----------
        
        // no constraint on BpmnExclusiveGateway.DefaultFlow : BpmnSequenceFlow from Standard.BpmnExclusiveGateway to Standard.BpmnSequenceFlow
        this.ruleMetaExpert.addDependencyRule(BpmnExclusiveGateway.class, null, "DefaultFlow");
    }

    @objid ("8ae9d86a-1c05-4f46-9d63-e6c65ffd8e0f")
    protected void registerMetaExpertForBpmnEventBasedGateway() {
        // Standard.BpmnEventBasedGateway
        // -----------
    }

    @objid ("7dbd6cdc-ed16-4796-9cfc-2d76d6524f63")
    protected void registerMetaExpertForBpmnComplexGateway() {
        // Standard.BpmnComplexGateway
        // -----------
        
        // no constraint on BpmnComplexGateway.DefaultFlow : BpmnSequenceFlow from Standard.BpmnComplexGateway to Standard.BpmnSequenceFlow
        this.ruleMetaExpert.addDependencyRule(BpmnComplexGateway.class, null, "DefaultFlow");
    }

    @objid ("476f223c-324a-4bfb-a294-440b092cba78")
    protected void registerMetaExpertForBpmnGateway() {
        // Standard.BpmnGateway is abstract
        
        // -----------
    }

    @objid ("c81cec58-fb0d-44a5-baec-f653f24abbaf")
    protected void registerMetaExpertForBpmnSequenceFlow() {
        // Standard.BpmnSequenceFlow
        // -----------
        
        // no constraint on BpmnSequenceFlow.SourceRef : BpmnFlowNode from Standard.BpmnSequenceFlow to Standard.BpmnFlowNode
        this.ruleMetaExpert.addDependencyRule(BpmnSequenceFlow.class, null, "SourceRef");
        
        // no constraint on BpmnSequenceFlow.TargetRef : BpmnFlowNode from Standard.BpmnSequenceFlow to Standard.BpmnFlowNode
        this.ruleMetaExpert.addDependencyRule(BpmnSequenceFlow.class, null, "TargetRef");
        
        // no constraint on BpmnSequenceFlow.Connector : BpmnSequenceFlowDataAssociation from Standard.BpmnSequenceFlow to Standard.BpmnSequenceFlowDataAssociation
        this.ruleMetaExpert.addDependencyRule(BpmnSequenceFlow.class, null, "Connector");
    }

    @objid ("3530fc18-1853-471a-a618-75a89bbccfa1")
    protected void registerMetaExpertForBpmnMessageFlow() {
        // Standard.BpmnMessageFlow
        // -----------
        
        // no constraint on BpmnMessageFlow.MessageRef : BpmnMessage from Standard.BpmnMessageFlow to Standard.BpmnMessage
        this.ruleMetaExpert.addDependencyRule(BpmnMessageFlow.class, null, "MessageRef");
        
        // no constraint on BpmnMessageFlow.SourceRef : BpmnBaseElement from Standard.BpmnMessageFlow to Standard.BpmnBaseElement
        this.ruleMetaExpert.addDependencyRule(BpmnMessageFlow.class, null, "SourceRef");
        
        // no constraint on BpmnMessageFlow.TargetRef : BpmnBaseElement from Standard.BpmnMessageFlow to Standard.BpmnBaseElement
        this.ruleMetaExpert.addDependencyRule(BpmnMessageFlow.class, null, "TargetRef");
    }

    @objid ("c9306bc6-0eac-4d44-a4dc-8322168bfe2d")
    protected void registerMetaExpertForBpmnMessage() {
        // Standard.BpmnMessage
        // -----------
        
        // no constraint on BpmnMessage.ItemRef : BpmnItemDefinition from Standard.BpmnMessage to Standard.BpmnItemDefinition
        this.ruleMetaExpert.addDependencyRule(BpmnMessage.class, null, "ItemRef");
    }

    @objid ("a7934a25-c21e-4430-87fa-cf50160efcaf")
    protected void registerMetaExpertForBpmnTimerEventDefinition() {
        // Standard.BpmnTimerEventDefinition
        // -----------
    }

    @objid ("0d2e53cd-1bb0-4897-acc8-c72e577b8577")
    protected void registerMetaExpertForBpmnTerminateEventDefinition() {
        // Standard.BpmnTerminateEventDefinition
        // -----------
    }

    @objid ("8fa71687-e20e-44db-99b4-459ceddf10fc")
    protected void registerMetaExpertForBpmnStartEvent() {
        // Standard.BpmnStartEvent
        // -----------
    }

    @objid ("ac2395f3-5b66-4304-ad3e-8f74f211b4a5")
    protected void registerMetaExpertForBpmnSignalEventDefinition() {
        // Standard.BpmnSignalEventDefinition
        // -----------
    }

    @objid ("449986db-638d-422d-80c7-993f71883729")
    protected void registerMetaExpertForBpmnMessageEventDefinition() {
        // Standard.BpmnMessageEventDefinition
        // -----------
        
        // no constraint on BpmnMessageEventDefinition.MessageRef : BpmnMessage from Standard.BpmnMessageEventDefinition to Standard.BpmnMessage
        this.ruleMetaExpert.addDependencyRule(BpmnMessageEventDefinition.class, null, "MessageRef");
        
        // no constraint on BpmnMessageEventDefinition.OperationRef : BpmnOperation from Standard.BpmnMessageEventDefinition to Standard.BpmnOperation
        this.ruleMetaExpert.addDependencyRule(BpmnMessageEventDefinition.class, null, "OperationRef");
    }

    @objid ("dc9f6436-3509-4cfb-899b-d15ed6205726")
    protected void registerMetaExpertForBpmnLinkEventDefinition() {
        // Standard.BpmnLinkEventDefinition
        // -----------
        
        // no constraint on BpmnLinkEventDefinition.Target : BpmnLinkEventDefinition from Standard.BpmnLinkEventDefinition to Standard.BpmnLinkEventDefinition
        this.ruleMetaExpert.addDependencyRule(BpmnLinkEventDefinition.class, null, "Target");
    }

    @objid ("5668529c-53aa-455a-a7c7-c4c87a8a89fb")
    protected void registerMetaExpertForBpmnIntermediateThrowEvent() {
        // Standard.BpmnIntermediateThrowEvent
        // -----------
    }

    @objid ("b2dad21e-ebcb-4b0a-a298-2d5546854228")
    protected void registerMetaExpertForBpmnIntermediateCatchEvent() {
        // Standard.BpmnIntermediateCatchEvent
        // -----------
    }

    @objid ("b48757b8-4c65-40b0-8c63-cfcaef2300ff")
    protected void registerMetaExpertForBpmnImplicitThrowEvent() {
        // Standard.BpmnImplicitThrowEvent
        // -----------
    }

    @objid ("2076675d-3e06-4827-b4ef-a154d8b26315")
    protected void registerMetaExpertForBpmnEscalationEventDefinition() {
        // Standard.BpmnEscalationEventDefinition
        // -----------
    }

    @objid ("b8a48e4e-cb99-4fb2-a2ee-f3c91b42de67")
    protected void registerMetaExpertForBpmnErrorEventDefinition() {
        // Standard.BpmnErrorEventDefinition
        // -----------
    }

    @objid ("1ad53efc-2129-4acf-82e5-e5237a914ef7")
    protected void registerMetaExpertForBpmnEndEvent() {
        // Standard.BpmnEndEvent
        // -----------
    }

    @objid ("c090ba7a-79be-4fdd-b476-fa1d91813c4d")
    protected void registerMetaExpertForBpmnThrowEvent() {
        // Standard.BpmnThrowEvent is abstract
        
        // -----------
        
        // no constraint on BpmnThrowEvent.DataInputAssociation : BpmnDataAssociation from Standard.BpmnThrowEvent to Standard.BpmnDataAssociation
        this.ruleMetaExpert.addDependencyRule(BpmnEndEvent.class, null, "DataInputAssociation");
        this.ruleMetaExpert.addDependencyRule(BpmnImplicitThrowEvent.class, null, "DataInputAssociation");
        this.ruleMetaExpert.addDependencyRule(BpmnIntermediateThrowEvent.class, null, "DataInputAssociation");
        this.ruleMetaExpert.addDependencyRule(BpmnThrowEvent.class, null, "DataInputAssociation");
        
        // no constraint on BpmnThrowEvent.DataInput : BpmnDataInput from Standard.BpmnThrowEvent to Standard.BpmnDataInput
        this.ruleMetaExpert.addDependencyRule(BpmnEndEvent.class, null, "DataInput");
        this.ruleMetaExpert.addDependencyRule(BpmnImplicitThrowEvent.class, null, "DataInput");
        this.ruleMetaExpert.addDependencyRule(BpmnIntermediateThrowEvent.class, null, "DataInput");
        this.ruleMetaExpert.addDependencyRule(BpmnThrowEvent.class, null, "DataInput");
    }

    @objid ("b408f6df-ba51-4391-9993-f1389e632af5")
    protected void registerMetaExpertForBpmnConditionalEventDefinition() {
        // Standard.BpmnConditionalEventDefinition
        // -----------
    }

    @objid ("d21872de-9296-4550-9cef-272c989d58fa")
    protected void registerMetaExpertForBpmnCompensateEventDefinition() {
        // Standard.BpmnCompensateEventDefinition
        // -----------
        
        // no constraint on BpmnCompensateEventDefinition.ActivityRef : BpmnActivity from Standard.BpmnCompensateEventDefinition to Standard.BpmnActivity
        this.ruleMetaExpert.addDependencyRule(BpmnCompensateEventDefinition.class, null, "ActivityRef");
    }

    @objid ("39b4007c-bd04-49dd-a242-b2b271b7c654")
    protected void registerMetaExpertForBpmnCancelEventDefinition() {
        // Standard.BpmnCancelEventDefinition
        // -----------
    }

    @objid ("0aa7aa0d-cc30-4bf8-8560-3a264688d842")
    protected void registerMetaExpertForBpmnEventDefinition() {
        // Standard.BpmnEventDefinition is abstract
        
        // -----------
    }

    @objid ("f51be05c-9f1d-4659-ac8c-1c4f0a4b63e8")
    protected void registerMetaExpertForBpmnBoundaryEvent() {
        // Standard.BpmnBoundaryEvent
        // -----------
    }

    @objid ("6c20f065-28d8-460b-9701-8f049c6e7e18")
    protected void registerMetaExpertForBpmnCatchEvent() {
        // Standard.BpmnCatchEvent is abstract
        
        // -----------
        
        // no constraint on BpmnCatchEvent.DataOutputAssociation : BpmnDataAssociation from Standard.BpmnCatchEvent to Standard.BpmnDataAssociation
        this.ruleMetaExpert.addDependencyRule(BpmnBoundaryEvent.class, null, "DataOutputAssociation");
        this.ruleMetaExpert.addDependencyRule(BpmnCatchEvent.class, null, "DataOutputAssociation");
        this.ruleMetaExpert.addDependencyRule(BpmnIntermediateCatchEvent.class, null, "DataOutputAssociation");
        this.ruleMetaExpert.addDependencyRule(BpmnStartEvent.class, null, "DataOutputAssociation");
        
        // no constraint on BpmnCatchEvent.DataOutput : BpmnDataOutput from Standard.BpmnCatchEvent to Standard.BpmnDataOutput
        this.ruleMetaExpert.addDependencyRule(BpmnBoundaryEvent.class, null, "DataOutput");
        this.ruleMetaExpert.addDependencyRule(BpmnCatchEvent.class, null, "DataOutput");
        this.ruleMetaExpert.addDependencyRule(BpmnIntermediateCatchEvent.class, null, "DataOutput");
        this.ruleMetaExpert.addDependencyRule(BpmnStartEvent.class, null, "DataOutput");
    }

    @objid ("a6ac8252-f62b-4498-aee6-9addc681f140")
    protected void registerMetaExpertForBpmnEvent() {
        // Standard.BpmnEvent is abstract
        
        // -----------
        
        // no constraint on BpmnEvent.EventDefinitions : BpmnEventDefinition from Standard.BpmnEvent to Standard.BpmnEventDefinition
        this.ruleMetaExpert.addDependencyRule(BpmnBoundaryEvent.class, null, "EventDefinitions");
        this.ruleMetaExpert.addDependencyRule(BpmnCatchEvent.class, null, "EventDefinitions");
        this.ruleMetaExpert.addDependencyRule(BpmnEndEvent.class, null, "EventDefinitions");
        this.ruleMetaExpert.addDependencyRule(BpmnEvent.class, null, "EventDefinitions");
        this.ruleMetaExpert.addDependencyRule(BpmnImplicitThrowEvent.class, null, "EventDefinitions");
        this.ruleMetaExpert.addDependencyRule(BpmnIntermediateCatchEvent.class, null, "EventDefinitions");
        this.ruleMetaExpert.addDependencyRule(BpmnIntermediateThrowEvent.class, null, "EventDefinitions");
        this.ruleMetaExpert.addDependencyRule(BpmnStartEvent.class, null, "EventDefinitions");
        this.ruleMetaExpert.addDependencyRule(BpmnThrowEvent.class, null, "EventDefinitions");
    }

    @objid ("bf6a5b62-d949-498f-9200-b42b1ce93f8b")
    protected void registerMetaExpertForBpmnOperation() {
        // Standard.BpmnOperation
        // -----------
        
        // no constraint on BpmnOperation.InMessageRef : BpmnMessage from Standard.BpmnOperation to Standard.BpmnMessage
        this.ruleMetaExpert.addDependencyRule(BpmnOperation.class, null, "InMessageRef");
        
        // no constraint on BpmnOperation.OutMessageRef : BpmnMessage from Standard.BpmnOperation to Standard.BpmnMessage
        this.ruleMetaExpert.addDependencyRule(BpmnOperation.class, null, "OutMessageRef");
    }

    @objid ("2a09c296-0a0d-4554-a24e-88ec79fb693a")
    protected void registerMetaExpertForBpmnInterface() {
        // Standard.BpmnInterface
        // -----------
        
        // no constraint on BpmnInterface.Operation : BpmnOperation from Standard.BpmnInterface to Standard.BpmnOperation
        this.ruleMetaExpert.addDependencyRule(BpmnInterface.class, null, "Operation");
    }

    @objid ("236fc9a3-0686-4935-8a39-b0d9b2086f8f")
    protected void registerMetaExpertForBpmnEndPoint() {
        // Standard.BpmnEndPoint
        // -----------
    }

    @objid ("b35bf176-5d14-4f14-82ba-95336a270534")
    protected void registerMetaExpertForBpmnSharedElement() {
        // Standard.BpmnSharedElement is abstract
        
        // -----------
    }

    @objid ("fd7052f5-082a-47bd-8e5b-93bf4e6a06eb")
    protected void registerMetaExpertForBpmnCollaborationDiagram() {
        // Standard.BpmnCollaborationDiagram
        // -----------
    }

    @objid ("f4e7b827-cd4d-4681-9aa4-716f07f3b6ba")
    protected void registerMetaExpertForBpmnProcessDesignDiagram() {
        // Standard.BpmnProcessDesignDiagram
        // -----------
    }

    @objid ("3d497e23-eaf2-45a8-9f7a-a4ef31dc40c4")
    protected void registerMetaExpertForBpmnSubProcessDiagram() {
        // Standard.BpmnSubProcessDiagram
        // -----------
    }

    @objid ("998e6f06-d167-4f82-88e0-f28623480e7f")
    protected void registerMetaExpertForBpmnProcessCollaborationDiagram() {
        // Standard.BpmnProcessCollaborationDiagram is abstract
        
        // -----------
    }

    @objid ("57f35e7b-8395-4c9f-8936-0df4d5c177ba")
    protected void registerMetaExpertForBehaviorDiagram() {
        // Standard.BehaviorDiagram is abstract
        
        // -----------
    }

    @objid ("0c6129ae-ccee-403a-8329-0e78ee65ef69")
    protected void registerMetaExpertForBpmnUserTask() {
        // Standard.BpmnUserTask
        // -----------
    }

    @objid ("d2190ce8-c02a-451c-8240-6557026ee098")
    protected void registerMetaExpertForBpmnTransaction() {
        // Standard.BpmnTransaction
        // -----------
    }

    @objid ("85902d63-9d44-4c1b-9802-ae3a1301200d")
    protected void registerMetaExpertForBpmnStandardLoopCharacteristics() {
        // Standard.BpmnStandardLoopCharacteristics
        // -----------
    }

    @objid ("043bf9f8-ebca-4fad-b722-2d15d4ce460d")
    protected void registerMetaExpertForBpmnServiceTask() {
        // Standard.BpmnServiceTask
        // -----------
        
        // no constraint on BpmnServiceTask.OperationRef : BpmnOperation from Standard.BpmnServiceTask to Standard.BpmnOperation
        this.ruleMetaExpert.addDependencyRule(BpmnServiceTask.class, null, "OperationRef");
    }

    @objid ("173c9543-2c39-4f60-a5d8-8b9971a04324")
    protected void registerMetaExpertForBpmnSendTask() {
        // Standard.BpmnSendTask
        // -----------
        
        // no constraint on BpmnSendTask.MessageRef : BpmnMessage from Standard.BpmnSendTask to Standard.BpmnMessage
        this.ruleMetaExpert.addDependencyRule(BpmnSendTask.class, null, "MessageRef");
        
        // no constraint on BpmnSendTask.OperationRef : BpmnOperation from Standard.BpmnSendTask to Standard.BpmnOperation
        this.ruleMetaExpert.addDependencyRule(BpmnSendTask.class, null, "OperationRef");
    }

    @objid ("5a8507ec-f4bc-43d4-adb7-36997492dc2e")
    protected void registerMetaExpertForBpmnScriptTask() {
        // Standard.BpmnScriptTask
        // -----------
    }

    @objid ("653b78f0-40af-4638-bcb7-8c748b5c16c2")
    protected void registerMetaExpertForBpmnReceiveTask() {
        // Standard.BpmnReceiveTask
        // -----------
        
        // no constraint on BpmnReceiveTask.MessageRef : BpmnMessage from Standard.BpmnReceiveTask to Standard.BpmnMessage
        this.ruleMetaExpert.addDependencyRule(BpmnReceiveTask.class, null, "MessageRef");
        
        // no constraint on BpmnReceiveTask.OperationRef : BpmnOperation from Standard.BpmnReceiveTask to Standard.BpmnOperation
        this.ruleMetaExpert.addDependencyRule(BpmnReceiveTask.class, null, "OperationRef");
    }

    @objid ("aa3b1255-d7c7-4ee7-bdf5-c1a5bd6b59b6")
    protected void registerMetaExpertForBpmnMultiInstanceLoopCharacteristics() {
        // Standard.BpmnMultiInstanceLoopCharacteristics
        // -----------
        
        // no constraint on BpmnMultiInstanceLoopCharacteristics.LoopDataInput : BpmnDataInput from Standard.BpmnMultiInstanceLoopCharacteristics to Standard.BpmnDataInput
        this.ruleMetaExpert.addDependencyRule(BpmnMultiInstanceLoopCharacteristics.class, null, "LoopDataInput");
        
        // no constraint on BpmnMultiInstanceLoopCharacteristics.LoopDataOutputRef : BpmnDataOutput from Standard.BpmnMultiInstanceLoopCharacteristics to Standard.BpmnDataOutput
        this.ruleMetaExpert.addDependencyRule(BpmnMultiInstanceLoopCharacteristics.class, null, "LoopDataOutputRef");
        
        // no constraint on BpmnMultiInstanceLoopCharacteristics.CompletionEventRef : BpmnEventDefinition from Standard.BpmnMultiInstanceLoopCharacteristics to Standard.BpmnEventDefinition
        this.ruleMetaExpert.addDependencyRule(BpmnMultiInstanceLoopCharacteristics.class, null, "CompletionEventRef");
        
        // no constraint on BpmnMultiInstanceLoopCharacteristics.ComplexBehaviorDefinition : BpmnComplexBehaviorDefinition from Standard.BpmnMultiInstanceLoopCharacteristics to Standard.BpmnComplexBehaviorDefinition
        this.ruleMetaExpert.addDependencyRule(BpmnMultiInstanceLoopCharacteristics.class, null, "ComplexBehaviorDefinition");
    }

    @objid ("632314be-7edc-408c-a423-d0311c6a4f47")
    protected void registerMetaExpertForBpmnManualTask() {
        // Standard.BpmnManualTask
        // -----------
    }

    @objid ("87e6000f-f63f-4426-8793-e2ffba5d323b")
    protected void registerMetaExpertForBpmnLoopCharacteristics() {
        // Standard.BpmnLoopCharacteristics is abstract
        
        // -----------
    }

    @objid ("15e8f165-5681-4195-9eb9-73da73ba3046")
    protected void registerMetaExpertForBpmnComplexBehaviorDefinition() {
        // Standard.BpmnComplexBehaviorDefinition
        // -----------
        
        // no constraint on BpmnComplexBehaviorDefinition.Event : BpmnImplicitThrowEvent from Standard.BpmnComplexBehaviorDefinition to Standard.BpmnImplicitThrowEvent
        this.ruleMetaExpert.addDependencyRule(BpmnComplexBehaviorDefinition.class, null, "Event");
    }

    @objid ("5aeb8fa1-3c2e-47f6-9bb9-3057f4291700")
    protected void registerMetaExpertForBpmnCallActivity() {
        // Standard.BpmnCallActivity
        // -----------
        
        // no constraint on BpmnCallActivity.CalledGlobalTask : BpmnTask from Standard.BpmnCallActivity to Standard.BpmnTask
        this.ruleMetaExpert.addDependencyRule(BpmnCallActivity.class, null, "CalledGlobalTask");
    }

    @objid ("9720609d-337b-4acc-a110-bde2dc31d7a5")
    protected void registerMetaExpertForBpmnBusinessRuleTask() {
        // Standard.BpmnBusinessRuleTask
        // -----------
    }

    @objid ("96c280c4-bf23-4774-8247-55d41f4de104")
    protected void registerMetaExpertForBpmnTask() {
        // Standard.BpmnTask
        // -----------
    }

    @objid ("a34146df-0efc-482d-977d-3af3dfdad0e1")
    protected void registerMetaExpertForBpmnAdHocSubProcess() {
        // Standard.BpmnAdHocSubProcess
        // -----------
    }

    @objid ("f13a6dd4-e863-427e-9668-b675817c4a82")
    protected void registerMetaExpertForBpmnSubProcess() {
        // Standard.BpmnSubProcess
        // -----------
        
        // no constraint on BpmnSubProcess.Artifact : BpmnArtifact from Standard.BpmnSubProcess to Standard.BpmnArtifact
        this.ruleMetaExpert.addDependencyRule(BpmnAdHocSubProcess.class, null, "Artifact");
        this.ruleMetaExpert.addDependencyRule(BpmnSubProcess.class, null, "Artifact");
        this.ruleMetaExpert.addDependencyRule(BpmnTransaction.class, null, "Artifact");
        
        // no constraint on BpmnSubProcess.FlowElement : BpmnFlowElement from Standard.BpmnSubProcess to Standard.BpmnFlowElement
        this.ruleMetaExpert.addDependencyRule(BpmnAdHocSubProcess.class, null, "FlowElement");
        this.ruleMetaExpert.addDependencyRule(BpmnSubProcess.class, null, "FlowElement");
        this.ruleMetaExpert.addDependencyRule(BpmnTransaction.class, null, "FlowElement");
        
        // no constraint on BpmnSubProcess.LaneSet : BpmnLaneSet from Standard.BpmnSubProcess to Standard.BpmnLaneSet
        this.ruleMetaExpert.addDependencyRule(BpmnAdHocSubProcess.class, null, "LaneSet");
        this.ruleMetaExpert.addDependencyRule(BpmnSubProcess.class, null, "LaneSet");
        this.ruleMetaExpert.addDependencyRule(BpmnTransaction.class, null, "LaneSet");
    }

    @objid ("921ca57e-0954-4f50-9732-f9ecaf47dfe7")
    protected void registerMetaExpertForBpmnActivity() {
        // Standard.BpmnActivity
        // -----------
        
        // no constraint on BpmnActivity.InputSpecification : BpmnDataInput from Standard.BpmnActivity to Standard.BpmnDataInput
        this.ruleMetaExpert.addDependencyRule(BpmnActivity.class, null, "InputSpecification");
        this.ruleMetaExpert.addDependencyRule(BpmnAdHocSubProcess.class, null, "InputSpecification");
        this.ruleMetaExpert.addDependencyRule(BpmnBusinessRuleTask.class, null, "InputSpecification");
        this.ruleMetaExpert.addDependencyRule(BpmnCallActivity.class, null, "InputSpecification");
        this.ruleMetaExpert.addDependencyRule(BpmnManualTask.class, null, "InputSpecification");
        this.ruleMetaExpert.addDependencyRule(BpmnReceiveTask.class, null, "InputSpecification");
        this.ruleMetaExpert.addDependencyRule(BpmnScriptTask.class, null, "InputSpecification");
        this.ruleMetaExpert.addDependencyRule(BpmnSendTask.class, null, "InputSpecification");
        this.ruleMetaExpert.addDependencyRule(BpmnServiceTask.class, null, "InputSpecification");
        this.ruleMetaExpert.addDependencyRule(BpmnSubProcess.class, null, "InputSpecification");
        this.ruleMetaExpert.addDependencyRule(BpmnTask.class, null, "InputSpecification");
        this.ruleMetaExpert.addDependencyRule(BpmnTransaction.class, null, "InputSpecification");
        this.ruleMetaExpert.addDependencyRule(BpmnUserTask.class, null, "InputSpecification");
        
        // no constraint on BpmnActivity.DataInputAssociation : BpmnDataAssociation from Standard.BpmnActivity to Standard.BpmnDataAssociation
        this.ruleMetaExpert.addDependencyRule(BpmnActivity.class, null, "DataInputAssociation");
        this.ruleMetaExpert.addDependencyRule(BpmnAdHocSubProcess.class, null, "DataInputAssociation");
        this.ruleMetaExpert.addDependencyRule(BpmnBusinessRuleTask.class, null, "DataInputAssociation");
        this.ruleMetaExpert.addDependencyRule(BpmnCallActivity.class, null, "DataInputAssociation");
        this.ruleMetaExpert.addDependencyRule(BpmnManualTask.class, null, "DataInputAssociation");
        this.ruleMetaExpert.addDependencyRule(BpmnReceiveTask.class, null, "DataInputAssociation");
        this.ruleMetaExpert.addDependencyRule(BpmnScriptTask.class, null, "DataInputAssociation");
        this.ruleMetaExpert.addDependencyRule(BpmnSendTask.class, null, "DataInputAssociation");
        this.ruleMetaExpert.addDependencyRule(BpmnServiceTask.class, null, "DataInputAssociation");
        this.ruleMetaExpert.addDependencyRule(BpmnSubProcess.class, null, "DataInputAssociation");
        this.ruleMetaExpert.addDependencyRule(BpmnTask.class, null, "DataInputAssociation");
        this.ruleMetaExpert.addDependencyRule(BpmnTransaction.class, null, "DataInputAssociation");
        this.ruleMetaExpert.addDependencyRule(BpmnUserTask.class, null, "DataInputAssociation");
        
        // no constraint on BpmnActivity.OutputSpecification : BpmnDataOutput from Standard.BpmnActivity to Standard.BpmnDataOutput
        this.ruleMetaExpert.addDependencyRule(BpmnActivity.class, null, "OutputSpecification");
        this.ruleMetaExpert.addDependencyRule(BpmnAdHocSubProcess.class, null, "OutputSpecification");
        this.ruleMetaExpert.addDependencyRule(BpmnBusinessRuleTask.class, null, "OutputSpecification");
        this.ruleMetaExpert.addDependencyRule(BpmnCallActivity.class, null, "OutputSpecification");
        this.ruleMetaExpert.addDependencyRule(BpmnManualTask.class, null, "OutputSpecification");
        this.ruleMetaExpert.addDependencyRule(BpmnReceiveTask.class, null, "OutputSpecification");
        this.ruleMetaExpert.addDependencyRule(BpmnScriptTask.class, null, "OutputSpecification");
        this.ruleMetaExpert.addDependencyRule(BpmnSendTask.class, null, "OutputSpecification");
        this.ruleMetaExpert.addDependencyRule(BpmnServiceTask.class, null, "OutputSpecification");
        this.ruleMetaExpert.addDependencyRule(BpmnSubProcess.class, null, "OutputSpecification");
        this.ruleMetaExpert.addDependencyRule(BpmnTask.class, null, "OutputSpecification");
        this.ruleMetaExpert.addDependencyRule(BpmnTransaction.class, null, "OutputSpecification");
        this.ruleMetaExpert.addDependencyRule(BpmnUserTask.class, null, "OutputSpecification");
        
        // no constraint on BpmnActivity.LoopCharacteristics : BpmnLoopCharacteristics from Standard.BpmnActivity to Standard.BpmnLoopCharacteristics
        this.ruleMetaExpert.addDependencyRule(BpmnActivity.class, null, "LoopCharacteristics");
        this.ruleMetaExpert.addDependencyRule(BpmnAdHocSubProcess.class, null, "LoopCharacteristics");
        this.ruleMetaExpert.addDependencyRule(BpmnBusinessRuleTask.class, null, "LoopCharacteristics");
        this.ruleMetaExpert.addDependencyRule(BpmnCallActivity.class, null, "LoopCharacteristics");
        this.ruleMetaExpert.addDependencyRule(BpmnManualTask.class, null, "LoopCharacteristics");
        this.ruleMetaExpert.addDependencyRule(BpmnReceiveTask.class, null, "LoopCharacteristics");
        this.ruleMetaExpert.addDependencyRule(BpmnScriptTask.class, null, "LoopCharacteristics");
        this.ruleMetaExpert.addDependencyRule(BpmnSendTask.class, null, "LoopCharacteristics");
        this.ruleMetaExpert.addDependencyRule(BpmnServiceTask.class, null, "LoopCharacteristics");
        this.ruleMetaExpert.addDependencyRule(BpmnSubProcess.class, null, "LoopCharacteristics");
        this.ruleMetaExpert.addDependencyRule(BpmnTask.class, null, "LoopCharacteristics");
        this.ruleMetaExpert.addDependencyRule(BpmnTransaction.class, null, "LoopCharacteristics");
        this.ruleMetaExpert.addDependencyRule(BpmnUserTask.class, null, "LoopCharacteristics");
        
        // no constraint on BpmnActivity.BoundaryEventRef : BpmnBoundaryEvent from Standard.BpmnActivity to Standard.BpmnBoundaryEvent
        this.ruleMetaExpert.addDependencyRule(BpmnActivity.class, null, "BoundaryEventRef");
        this.ruleMetaExpert.addDependencyRule(BpmnAdHocSubProcess.class, null, "BoundaryEventRef");
        this.ruleMetaExpert.addDependencyRule(BpmnBusinessRuleTask.class, null, "BoundaryEventRef");
        this.ruleMetaExpert.addDependencyRule(BpmnCallActivity.class, null, "BoundaryEventRef");
        this.ruleMetaExpert.addDependencyRule(BpmnManualTask.class, null, "BoundaryEventRef");
        this.ruleMetaExpert.addDependencyRule(BpmnReceiveTask.class, null, "BoundaryEventRef");
        this.ruleMetaExpert.addDependencyRule(BpmnScriptTask.class, null, "BoundaryEventRef");
        this.ruleMetaExpert.addDependencyRule(BpmnSendTask.class, null, "BoundaryEventRef");
        this.ruleMetaExpert.addDependencyRule(BpmnServiceTask.class, null, "BoundaryEventRef");
        this.ruleMetaExpert.addDependencyRule(BpmnSubProcess.class, null, "BoundaryEventRef");
        this.ruleMetaExpert.addDependencyRule(BpmnTask.class, null, "BoundaryEventRef");
        this.ruleMetaExpert.addDependencyRule(BpmnTransaction.class, null, "BoundaryEventRef");
        this.ruleMetaExpert.addDependencyRule(BpmnUserTask.class, null, "BoundaryEventRef");
        
        // no constraint on BpmnActivity.DataOutputAssociation : BpmnDataAssociation from Standard.BpmnActivity to Standard.BpmnDataAssociation
        this.ruleMetaExpert.addDependencyRule(BpmnActivity.class, null, "DataOutputAssociation");
        this.ruleMetaExpert.addDependencyRule(BpmnAdHocSubProcess.class, null, "DataOutputAssociation");
        this.ruleMetaExpert.addDependencyRule(BpmnBusinessRuleTask.class, null, "DataOutputAssociation");
        this.ruleMetaExpert.addDependencyRule(BpmnCallActivity.class, null, "DataOutputAssociation");
        this.ruleMetaExpert.addDependencyRule(BpmnManualTask.class, null, "DataOutputAssociation");
        this.ruleMetaExpert.addDependencyRule(BpmnReceiveTask.class, null, "DataOutputAssociation");
        this.ruleMetaExpert.addDependencyRule(BpmnScriptTask.class, null, "DataOutputAssociation");
        this.ruleMetaExpert.addDependencyRule(BpmnSendTask.class, null, "DataOutputAssociation");
        this.ruleMetaExpert.addDependencyRule(BpmnServiceTask.class, null, "DataOutputAssociation");
        this.ruleMetaExpert.addDependencyRule(BpmnSubProcess.class, null, "DataOutputAssociation");
        this.ruleMetaExpert.addDependencyRule(BpmnTask.class, null, "DataOutputAssociation");
        this.ruleMetaExpert.addDependencyRule(BpmnTransaction.class, null, "DataOutputAssociation");
        this.ruleMetaExpert.addDependencyRule(BpmnUserTask.class, null, "DataOutputAssociation");
        
        // no constraint on BpmnActivity.DefaultFlow : BpmnSequenceFlow from Standard.BpmnActivity to Standard.BpmnSequenceFlow
        this.ruleMetaExpert.addDependencyRule(BpmnActivity.class, null, "DefaultFlow");
        this.ruleMetaExpert.addDependencyRule(BpmnAdHocSubProcess.class, null, "DefaultFlow");
        this.ruleMetaExpert.addDependencyRule(BpmnBusinessRuleTask.class, null, "DefaultFlow");
        this.ruleMetaExpert.addDependencyRule(BpmnCallActivity.class, null, "DefaultFlow");
        this.ruleMetaExpert.addDependencyRule(BpmnManualTask.class, null, "DefaultFlow");
        this.ruleMetaExpert.addDependencyRule(BpmnReceiveTask.class, null, "DefaultFlow");
        this.ruleMetaExpert.addDependencyRule(BpmnScriptTask.class, null, "DefaultFlow");
        this.ruleMetaExpert.addDependencyRule(BpmnSendTask.class, null, "DefaultFlow");
        this.ruleMetaExpert.addDependencyRule(BpmnServiceTask.class, null, "DefaultFlow");
        this.ruleMetaExpert.addDependencyRule(BpmnSubProcess.class, null, "DefaultFlow");
        this.ruleMetaExpert.addDependencyRule(BpmnTask.class, null, "DefaultFlow");
        this.ruleMetaExpert.addDependencyRule(BpmnTransaction.class, null, "DefaultFlow");
        this.ruleMetaExpert.addDependencyRule(BpmnUserTask.class, null, "DefaultFlow");
    }

    @objid ("a4f11f6e-0a71-4183-a383-70b3f0d4ec3f")
    protected void registerMetaExpertForBpmnFlowNode() {
        // Standard.BpmnFlowNode is abstract
        
        // -----------
        
        // no constraint on BpmnFlowNode.Resource : BpmnResourceRole from Standard.BpmnFlowNode to Standard.BpmnResourceRole
        this.ruleMetaExpert.addDependencyRule(BpmnActivity.class, null, "Resource");
        this.ruleMetaExpert.addDependencyRule(BpmnAdHocSubProcess.class, null, "Resource");
        this.ruleMetaExpert.addDependencyRule(BpmnBoundaryEvent.class, null, "Resource");
        this.ruleMetaExpert.addDependencyRule(BpmnBusinessRuleTask.class, null, "Resource");
        this.ruleMetaExpert.addDependencyRule(BpmnCallActivity.class, null, "Resource");
        this.ruleMetaExpert.addDependencyRule(BpmnCatchEvent.class, null, "Resource");
        this.ruleMetaExpert.addDependencyRule(BpmnComplexGateway.class, null, "Resource");
        this.ruleMetaExpert.addDependencyRule(BpmnEndEvent.class, null, "Resource");
        this.ruleMetaExpert.addDependencyRule(BpmnEvent.class, null, "Resource");
        this.ruleMetaExpert.addDependencyRule(BpmnEventBasedGateway.class, null, "Resource");
        this.ruleMetaExpert.addDependencyRule(BpmnExclusiveGateway.class, null, "Resource");
        this.ruleMetaExpert.addDependencyRule(BpmnFlowNode.class, null, "Resource");
        this.ruleMetaExpert.addDependencyRule(BpmnGateway.class, null, "Resource");
        this.ruleMetaExpert.addDependencyRule(BpmnImplicitThrowEvent.class, null, "Resource");
        this.ruleMetaExpert.addDependencyRule(BpmnInclusiveGateway.class, null, "Resource");
        this.ruleMetaExpert.addDependencyRule(BpmnIntermediateCatchEvent.class, null, "Resource");
        this.ruleMetaExpert.addDependencyRule(BpmnIntermediateThrowEvent.class, null, "Resource");
        this.ruleMetaExpert.addDependencyRule(BpmnManualTask.class, null, "Resource");
        this.ruleMetaExpert.addDependencyRule(BpmnParallelGateway.class, null, "Resource");
        this.ruleMetaExpert.addDependencyRule(BpmnReceiveTask.class, null, "Resource");
        this.ruleMetaExpert.addDependencyRule(BpmnScriptTask.class, null, "Resource");
        this.ruleMetaExpert.addDependencyRule(BpmnSendTask.class, null, "Resource");
        this.ruleMetaExpert.addDependencyRule(BpmnServiceTask.class, null, "Resource");
        this.ruleMetaExpert.addDependencyRule(BpmnStartEvent.class, null, "Resource");
        this.ruleMetaExpert.addDependencyRule(BpmnSubProcess.class, null, "Resource");
        this.ruleMetaExpert.addDependencyRule(BpmnTask.class, null, "Resource");
        this.ruleMetaExpert.addDependencyRule(BpmnThrowEvent.class, null, "Resource");
        this.ruleMetaExpert.addDependencyRule(BpmnTransaction.class, null, "Resource");
        this.ruleMetaExpert.addDependencyRule(BpmnUserTask.class, null, "Resource");
    }

    @objid ("405f226f-63a7-4090-b1a5-5804dac21e62")
    protected void registerMetaExpertForBpmnFlowElement() {
        // Standard.BpmnFlowElement is abstract
        
        // -----------
    }

    @objid ("7d0911a9-6dfa-4376-a2f8-826bc52b1ad9")
    protected void registerMetaExpertForBpmnBaseElement() {
        // Standard.BpmnBaseElement is abstract
        
        // -----------
    }

}
