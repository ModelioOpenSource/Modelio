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
@objid ("67382fa1-2a08-4074-9f1b-2e45cdf21f89")
public class StandardGeneratedMetamodelExpert extends CompositeMetamodelExpert {
    /**
     * Constructor.
     * <p>
     * You need to call {@link #register()} next.
     * @param mm The metamodel.
     */
    @objid ("53f30271-45b0-4441-8481-366df8985c1b")
    public StandardGeneratedMetamodelExpert(MMetamodel mm) {
        super(mm);
    }

    /**
     * Initializes this expert.
     */
    @objid ("7d1ecd2d-d6bb-4f54-8fa9-ca79902a8479")
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

    @objid ("e934d06d-a129-4b96-a31f-33df2ade61b4")
    protected void registerLinkExpertForBpmnMessageFlow() {
        // Standard.BpmnMessageFlow
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(BpmnMessageFlow.class);
        
        // Standard.BpmnMessageFlow sources and target dependencies
        this.ruleLinkExpert.addSourceDep(BpmnMessageFlow.class, "SourceRef");
        this.ruleLinkExpert.addTargetDep(BpmnMessageFlow.class, "TargetRef");
        
        
        // Standard.BpmnMessageFlow rules: all allowed.
        
        this.ruleLinkExpert.addRule(BpmnMessageFlow.class, null, null);
    }

    @objid ("87d266ba-2015-47f0-8b86-1ad45583c679")
    protected void registerLinkExpertForBpmnSequenceFlow() {
        // Standard.BpmnSequenceFlow
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(BpmnSequenceFlow.class);
        
        // Standard.BpmnSequenceFlow sources and target dependencies
        this.ruleLinkExpert.addSourceDep(BpmnSequenceFlow.class, "SourceRef");
        this.ruleLinkExpert.addTargetDep(BpmnSequenceFlow.class, "TargetRef");
        
        
        // Standard.BpmnSequenceFlow rules: all allowed.
        
        this.ruleLinkExpert.addRule(BpmnSequenceFlow.class, null, null);
    }

    @objid ("c63da531-763d-4a28-a8cf-5b6d5911a04b")
    protected void registerLinkExpertForActivityEdge() {
        // Standard.ActivityEdge is abstract
        
        this.ruleLinkExpert.addLinkMetaclass(ActivityEdge.class);
    }

    @objid ("222eeb3c-ce15-4d5f-8c9d-e7a9bed4c0b4")
    protected void registerLinkExpertForCommunicationChannel() {
        // Standard.CommunicationChannel
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(CommunicationChannel.class);
        
        // Standard.CommunicationChannel sources and target dependencies
        this.ruleLinkExpert.addTargetDep(CommunicationChannel.class, "End");
        this.ruleLinkExpert.addSourceDep(CommunicationChannel.class, "Start");
        
        
        // Standard.CommunicationChannel rules: all allowed.
        
        this.ruleLinkExpert.addRule(CommunicationChannel.class, null, null);
    }

    @objid ("912b6cfe-59d8-4bad-8231-57e5f99792ae")
    protected void registerLinkExpertForMessage() {
        // Standard.Message
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(Message.class);
        
        // Standard.Message sources and target dependencies
        this.ruleLinkExpert.addTargetDep(Message.class, "ReceiveEvent");
        this.ruleLinkExpert.addSourceDep(Message.class, "SendEvent");
        
        
        // Standard.Message rules: all allowed.
        
        this.ruleLinkExpert.addRule(Message.class, null, null);
    }

    @objid ("136f69b6-d8f1-42b6-a0ae-b66430e34a56")
    protected void registerLinkExpertForTransition() {
        // Standard.Transition
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(Transition.class);
        
        // Standard.Transition sources and target dependencies
        this.ruleLinkExpert.addSourceDep(Transition.class, "Source");
        this.ruleLinkExpert.addTargetDep(Transition.class, "Target");
        
        
        // Standard.Transition rules: all allowed.
        
        this.ruleLinkExpert.addRule(Transition.class, null, null);
    }

    @objid ("5fca80d9-8dcd-4fd6-b2ea-0bfd4b0be731")
    protected void registerLinkExpertForUseCaseDependency() {
        // Standard.UseCaseDependency
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(UseCaseDependency.class);
        
        // Standard.UseCaseDependency sources and target dependencies
        this.ruleLinkExpert.addSourceDep(UseCaseDependency.class, "Origin");
        this.ruleLinkExpert.addTargetDep(UseCaseDependency.class, "Target");
        
        
        // Standard.UseCaseDependency rules: all allowed.
        
        this.ruleLinkExpert.addRule(UseCaseDependency.class, null, null);
    }

    @objid ("5925117a-37b9-40c2-aa78-99bf6ee3f473")
    protected void registerLinkExpertForDataFlow() {
        // Standard.DataFlow
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(DataFlow.class);
        
        // Standard.DataFlow sources and target dependencies
        this.ruleLinkExpert.addTargetDep(DataFlow.class, "Destination");
        this.ruleLinkExpert.addSourceDep(DataFlow.class, "Origin");
        
        
        // Standard.DataFlow rules: all allowed.
        
        this.ruleLinkExpert.addRule(DataFlow.class, null, null);
    }

    @objid ("53097f1c-933b-4a4e-8223-83dd100cd123")
    protected void registerLinkExpertForInformationFlow() {
        // Standard.InformationFlow
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(InformationFlow.class);
        
        // Standard.InformationFlow sources and target dependencies
        this.ruleLinkExpert.addSourceDep(InformationFlow.class, "InformationSource");
        this.ruleLinkExpert.addTargetDep(InformationFlow.class, "InformationTarget");
        
        
        // Standard.InformationFlow rules: all allowed.
        
        this.ruleLinkExpert.addRule(InformationFlow.class, null, null);
    }

    @objid ("d9845ee0-f082-490e-9c72-5db7db5e0c0e")
    protected void registerLinkExpertForAbstraction() {
        // Standard.Abstraction
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(Abstraction.class);
        
        // Standard.Abstraction sources and target dependencies
        this.ruleLinkExpert.addTargetDep(Abstraction.class, "DependsOn");
        this.ruleLinkExpert.addSourceDep(Abstraction.class, "Impacted");
        
        
        // Standard.Abstraction rules: all allowed.
        
        this.ruleLinkExpert.addRule(Abstraction.class, null, null);
    }

    @objid ("22d39f49-c899-49e3-a542-594c1700c0f3")
    protected void registerLinkExpertForSubstitution() {
        // Standard.Substitution
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(Substitution.class);
        
        // Standard.Substitution sources and target dependencies
        this.ruleLinkExpert.addTargetDep(Substitution.class, "Contract");
        this.ruleLinkExpert.addSourceDep(Substitution.class, "SubstitutingClassifier");
        
        
        // Standard.Substitution rules: all allowed.
        
        this.ruleLinkExpert.addRule(Substitution.class, null, null);
    }

    @objid ("5ae2baca-9cc1-4914-90d9-772543f1400b")
    protected void registerLinkExpertForUsage() {
        // Standard.Usage
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(Usage.class);
        
        // Standard.Usage sources and target dependencies
        this.ruleLinkExpert.addTargetDep(Usage.class, "DependsOn");
        this.ruleLinkExpert.addSourceDep(Usage.class, "Impacted");
        
        
        // Standard.Usage rules: all allowed.
        
        this.ruleLinkExpert.addRule(Usage.class, null, null);
    }

    @objid ("4d3e5ffc-2c98-4c00-b031-16ab6f3765e3")
    protected void registerLinkExpertForAssociation() {
        // Standard.Association
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(Association.class);
        
        // Standard.Association sources and target dependencies
        
        
        // Standard.Association rules: all allowed.
        
        this.ruleLinkExpert.addRule(Association.class, null, null);
    }

    @objid ("5e076aa1-7b9c-4a73-96c7-3d13ed0fac10")
    protected void registerLinkExpertForAssociationEnd() {
        // Standard.AssociationEnd
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(AssociationEnd.class);
        
        // Standard.AssociationEnd sources and target dependencies
        this.ruleLinkExpert.addSourceDep(AssociationEnd.class, "Source");
        this.ruleLinkExpert.addTargetDep(AssociationEnd.class, "Target");
        
        
        // Standard.AssociationEnd rules: all allowed.
        
        this.ruleLinkExpert.addRule(AssociationEnd.class, null, null);
    }

    @objid ("13b399ca-2953-421b-aaff-f17cf86a44d5")
    protected void registerLinkExpertForElementImport() {
        // Standard.ElementImport
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(ElementImport.class);
        
        // Standard.ElementImport sources and target dependencies
        this.ruleLinkExpert.addTargetDep(ElementImport.class, "ImportedElement");
        this.ruleLinkExpert.addSourceDep(ElementImport.class, "ImportingNameSpace");
        this.ruleLinkExpert.addSourceDep(ElementImport.class, "ImportingOperation");
        
        
        // Standard.ElementImport rules: all allowed.
        
        this.ruleLinkExpert.addRule(ElementImport.class, null, null);
    }

    @objid ("fbb0b597-5f5b-4ca3-8774-899ba9111386")
    protected void registerLinkExpertForGeneralization() {
        // Standard.Generalization
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(Generalization.class);
        
        // Standard.Generalization sources and target dependencies
        this.ruleLinkExpert.addSourceDep(Generalization.class, "SubType");
        this.ruleLinkExpert.addTargetDep(Generalization.class, "SuperType");
        
        
        // Standard.Generalization rules: all allowed.
        
        this.ruleLinkExpert.addRule(Generalization.class, null, null);
    }

    @objid ("284a6ff7-27bb-4912-9960-ffb424ed71df")
    protected void registerLinkExpertForInterfaceRealization() {
        // Standard.InterfaceRealization
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(InterfaceRealization.class);
        
        // Standard.InterfaceRealization sources and target dependencies
        this.ruleLinkExpert.addTargetDep(InterfaceRealization.class, "Implemented");
        this.ruleLinkExpert.addSourceDep(InterfaceRealization.class, "Implementer");
        
        
        // Standard.InterfaceRealization rules: all allowed.
        
        this.ruleLinkExpert.addRule(InterfaceRealization.class, null, null);
    }

    @objid ("15a76ec1-eeb6-4166-8844-6b013ba2d323")
    protected void registerLinkExpertForLink() {
        // Standard.Link
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(Link.class);
        
        // Standard.Link sources and target dependencies
        
        
        // Standard.Link rules: all allowed.
        
        this.ruleLinkExpert.addRule(Link.class, null, null);
    }

    @objid ("1b5e7bc1-66e9-4d7f-9625-8aa1ec8a1d03")
    protected void registerLinkExpertForLinkEnd() {
        // Standard.LinkEnd
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(LinkEnd.class);
        
        // Standard.LinkEnd sources and target dependencies
        this.ruleLinkExpert.addSourceDep(LinkEnd.class, "Source");
        this.ruleLinkExpert.addTargetDep(LinkEnd.class, "Target");
        
        
        // Standard.LinkEnd rules: all allowed.
        
        this.ruleLinkExpert.addRule(LinkEnd.class, null, null);
    }

    @objid ("23ca67f7-12b4-42b9-bdb5-207d8f6b5698")
    protected void registerLinkExpertForManifestation() {
        // Standard.Manifestation
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(Manifestation.class);
        
        // Standard.Manifestation sources and target dependencies
        this.ruleLinkExpert.addSourceDep(Manifestation.class, "Owner");
        this.ruleLinkExpert.addTargetDep(Manifestation.class, "UtilizedElement");
        
        
        // Standard.Manifestation rules: all allowed.
        
        this.ruleLinkExpert.addRule(Manifestation.class, null, null);
    }

    @objid ("01cb39e9-3d37-469d-9a61-ea32876aa9a7")
    protected void registerLinkExpertForPackageImport() {
        // Standard.PackageImport
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(PackageImport.class);
        
        // Standard.PackageImport sources and target dependencies
        this.ruleLinkExpert.addTargetDep(PackageImport.class, "ImportedPackage");
        this.ruleLinkExpert.addSourceDep(PackageImport.class, "ImportingNameSpace");
        this.ruleLinkExpert.addSourceDep(PackageImport.class, "ImportingOperation");
        
        
        // Standard.PackageImport rules: all allowed.
        
        this.ruleLinkExpert.addRule(PackageImport.class, null, null);
    }

    @objid ("4f2b1e55-bdae-4713-9954-1919a9546624")
    protected void registerLinkExpertForPackageMerge() {
        // Standard.PackageMerge
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(PackageMerge.class);
        
        // Standard.PackageMerge sources and target dependencies
        this.ruleLinkExpert.addTargetDep(PackageMerge.class, "MergedPackage");
        this.ruleLinkExpert.addSourceDep(PackageMerge.class, "ReceivingPackage");
        
        
        // Standard.PackageMerge rules: all allowed.
        
        this.ruleLinkExpert.addRule(PackageMerge.class, null, null);
    }

    @objid ("79ad1a0c-2ab8-4bda-931c-f2ad4369e3f9")
    protected void registerLinkExpertForRaisedException() {
        // Standard.RaisedException
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(RaisedException.class);
        
        // Standard.RaisedException sources and target dependencies
        this.ruleLinkExpert.addSourceDep(RaisedException.class, "Thrower");
        this.ruleLinkExpert.addTargetDep(RaisedException.class, "ThrownType");
        
        
        // Standard.RaisedException rules: all allowed.
        
        this.ruleLinkExpert.addRule(RaisedException.class, null, null);
    }

    @objid ("fcdbc216-adf7-47cd-9b5d-8bd55abd087c")
    protected void registerLinkExpertForTemplateBinding() {
        // Standard.TemplateBinding
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(TemplateBinding.class);
        
        // Standard.TemplateBinding sources and target dependencies
        this.ruleLinkExpert.addSourceDep(TemplateBinding.class, "BoundElement");
        this.ruleLinkExpert.addSourceDep(TemplateBinding.class, "BoundOperation");
        this.ruleLinkExpert.addTargetDep(TemplateBinding.class, "InstanciatedTemplate");
        this.ruleLinkExpert.addTargetDep(TemplateBinding.class, "InstanciatedTemplateOperation");
        
        
        // Standard.TemplateBinding rules: all allowed.
        
        this.ruleLinkExpert.addRule(TemplateBinding.class, null, null);
    }

    @objid ("57c2c828-a5d8-45bb-8207-0542f9c1529e")
    protected void registerLinkExpertForControlFlow() {
        // Standard.ControlFlow
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(ControlFlow.class);
        
        // Standard.ControlFlow sources and target dependencies
        this.ruleLinkExpert.addSourceDep(ControlFlow.class, "Source");
        this.ruleLinkExpert.addTargetDep(ControlFlow.class, "Target");
        
        
        // Standard.ControlFlow rules: all allowed.
        
        this.ruleLinkExpert.addRule(ControlFlow.class, null, null);
    }

    @objid ("daa33a4f-f434-4e54-9250-3562810660e8")
    protected void registerLinkExpertForMessageFlow() {
        // Standard.MessageFlow
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(MessageFlow.class);
        
        // Standard.MessageFlow sources and target dependencies
        this.ruleLinkExpert.addSourceDep(MessageFlow.class, "Source");
        this.ruleLinkExpert.addSourceDep(MessageFlow.class, "SourcePartition");
        this.ruleLinkExpert.addTargetDep(MessageFlow.class, "Target");
        this.ruleLinkExpert.addTargetDep(MessageFlow.class, "TargetPartition");
        
        
        // Standard.MessageFlow rules: all allowed.
        
        this.ruleLinkExpert.addRule(MessageFlow.class, null, null);
    }

    @objid ("5f93ff9d-d966-4bad-ab40-50f6f6e1e57b")
    protected void registerLinkExpertForObjectFlow() {
        // Standard.ObjectFlow
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(ObjectFlow.class);
        
        // Standard.ObjectFlow sources and target dependencies
        this.ruleLinkExpert.addSourceDep(ObjectFlow.class, "Source");
        this.ruleLinkExpert.addTargetDep(ObjectFlow.class, "Target");
        
        
        // Standard.ObjectFlow rules: all allowed.
        
        this.ruleLinkExpert.addRule(ObjectFlow.class, null, null);
    }

    @objid ("41177bd9-e8b5-44d5-adf7-64264344c3a4")
    protected void registerLinkExpertForElementRealization() {
        // Standard.ElementRealization
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(ElementRealization.class);
        
        // Standard.ElementRealization sources and target dependencies
        this.ruleLinkExpert.addTargetDep(ElementRealization.class, "DependsOn");
        this.ruleLinkExpert.addSourceDep(ElementRealization.class, "Impacted");
        
        
        // Standard.ElementRealization rules: all allowed.
        
        this.ruleLinkExpert.addRule(ElementRealization.class, null, null);
    }

    @objid ("27f4684d-bbb0-4673-b9d9-3c641b3c35a3")
    protected void registerLinkExpertForConnector() {
        // Standard.Connector
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(Connector.class);
        
        // Standard.Connector sources and target dependencies
        
        
        // Standard.Connector rules: all allowed.
        
        this.ruleLinkExpert.addRule(Connector.class, null, null);
    }

    @objid ("cf1199a5-093b-428a-a5e2-06ee1e8eae3f")
    protected void registerLinkExpertForConnectorEnd() {
        // Standard.ConnectorEnd
        // -----------
        
        this.ruleLinkExpert.addLinkMetaclass(ConnectorEnd.class);
        
        // Standard.ConnectorEnd sources and target dependencies
        this.ruleLinkExpert.addSourceDep(ConnectorEnd.class, "Source");
        this.ruleLinkExpert.addTargetDep(ConnectorEnd.class, "Target");
        
        
        // Standard.ConnectorEnd rules: all allowed.
        
        this.ruleLinkExpert.addRule(ConnectorEnd.class, null, null);
    }

    @objid ("fbcd8b4c-6eb8-4ffb-8967-5a2f05c10b15")
    protected void registerMetaExpertForProject() {
        // Standard.Project
        // -----------
        
        // no constraint on Project.Model : Package from Standard.Project to Standard.Package
        this.ruleMetaExpert.addDependencyRule(Project.class, null, "Model");
    }

    @objid ("ca8feddd-93d8-4fdf-a261-feac1373e30b")
    protected void registerMetaExpertForTemplateParameterSubstitution() {
        // Standard.TemplateParameterSubstitution
        // -----------
        
        // no constraint on TemplateParameterSubstitution.Actual : UmlModelElement from Standard.TemplateParameterSubstitution to Standard.UmlModelElement
        this.ruleMetaExpert.addDependencyRule(TemplateParameterSubstitution.class, null, "Actual");
        
        // no constraint on TemplateParameterSubstitution.FormalParameter : TemplateParameter from Standard.TemplateParameterSubstitution to Standard.TemplateParameter
        this.ruleMetaExpert.addDependencyRule(TemplateParameterSubstitution.class, null, "FormalParameter");
    }

    @objid ("25245b07-189b-4eb0-9c6a-18144ca6172b")
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

    @objid ("72ab78bc-b5fc-4afb-bfd5-14474b874247")
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

    @objid ("c888945e-2b4d-457d-bddd-b555179893b9")
    protected void registerMetaExpertForRequiredInterface() {
        // Standard.RequiredInterface
        // -----------
        
        // no constraint on RequiredInterface.RequiredElement : Interface from Standard.RequiredInterface to Standard.Interface
        this.ruleMetaExpert.addDependencyRule(RequiredInterface.class, null, "RequiredElement");
    }

    @objid ("da988c0f-65f5-477e-a1a0-7c2d0150380a")
    protected void registerMetaExpertForRaisedException() {
        // Standard.RaisedException
        // -----------
        
        // no constraint on RaisedException.ThrownType : Classifier from Standard.RaisedException to Standard.Classifier
        this.ruleMetaExpert.addDependencyRule(RaisedException.class, null, "ThrownType");
    }

    @objid ("944a23be-6aff-409c-b5bb-3a294ac6b860")
    protected void registerMetaExpertForProvidedInterface() {
        // Standard.ProvidedInterface
        // -----------
        
        // no constraint on ProvidedInterface.ProvidedElement : Interface from Standard.ProvidedInterface to Standard.Interface
        this.ruleMetaExpert.addDependencyRule(ProvidedInterface.class, null, "ProvidedElement");
    }

    @objid ("87d05eb7-0e64-4fbb-aa34-079a53e709c6")
    protected void registerMetaExpertForPort() {
        // Standard.Port
        // -----------
        
        // no constraint on Port.Provided : ProvidedInterface from Standard.Port to Standard.ProvidedInterface
        this.ruleMetaExpert.addDependencyRule(Port.class, null, "Provided");
        
        // no constraint on Port.Required : RequiredInterface from Standard.Port to Standard.RequiredInterface
        this.ruleMetaExpert.addDependencyRule(Port.class, null, "Required");
    }

    @objid ("a0b331b6-671c-4795-befb-7b2e341e665e")
    protected void registerMetaExpertForPackageMerge() {
        // Standard.PackageMerge
        // -----------
        
        // no constraint on PackageMerge.MergedPackage : Package from Standard.PackageMerge to Standard.Package
        this.ruleMetaExpert.addDependencyRule(PackageMerge.class, null, "MergedPackage");
    }

    @objid ("9e038779-01e2-410d-a987-36d8d4aff259")
    protected void registerMetaExpertForPackageImport() {
        // Standard.PackageImport
        // -----------
        
        // no constraint on PackageImport.ImportedPackage : Package from Standard.PackageImport to Standard.Package
        this.ruleMetaExpert.addDependencyRule(PackageImport.class, null, "ImportedPackage");
    }

    @objid ("f45e1911-2629-45e2-bbdc-26800f1f8e6b")
    protected void registerMetaExpertForPackage() {
        // Standard.Package
        // -----------
        
        // no constraint on Package.Merge : PackageMerge from Standard.Package to Standard.PackageMerge
        this.ruleMetaExpert.addDependencyRule(Package.class, null, "Merge");
    }

    @objid ("6c703694-52af-4513-9230-aca6cb199a33")
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

    @objid ("14a7a097-15e5-4f95-8e51-526ddcdf6f05")
    protected void registerMetaExpertForNode() {
        // Standard.Node
        // -----------
        
        // no constraint on Node.Resident : Artifact from Standard.Node to Standard.Artifact
        this.ruleMetaExpert.addDependencyRule(Node.class, null, "Resident");
    }

    @objid ("81a8a2f3-29e6-41da-a19b-295a9146edcb")
    protected void registerMetaExpertForNaryConnectorEnd() {
        // Standard.NaryConnectorEnd
        // -----------
    }

    @objid ("0fe0e15e-8c09-4043-a582-1ef929cd6a19")
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

    @objid ("96f0ad33-0877-4173-8ed5-3a9fa38a11e0")
    protected void registerMetaExpertForNaryConnector() {
        // Standard.NaryConnector
        // -----------
        
        // no constraint on NaryConnector.RepresentedFeature : UmlModelElement from Standard.NaryConnector to Standard.UmlModelElement
        this.ruleMetaExpert.addDependencyRule(NaryConnector.class, null, "RepresentedFeature");
    }

    @objid ("9ab72217-c2aa-4f24-94cb-d34aabccf553")
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

    @objid ("6943d510-884b-4358-a497-2f4314de6417")
    protected void registerMetaExpertForNaryAssociationEnd() {
        // Standard.NaryAssociationEnd
        // -----------
        
        // no constraint on NaryAssociationEnd.NaryAssociation : NaryAssociation from Standard.NaryAssociationEnd to Standard.NaryAssociation
        this.ruleMetaExpert.addDependencyRule(NaryAssociationEnd.class, null, "NaryAssociation");
    }

    @objid ("ade1eef5-fe5c-4ccc-b051-8b5ba841f44d")
    protected void registerMetaExpertForNaryAssociation() {
        // Standard.NaryAssociation
        // -----------
        
        // no constraint on NaryAssociation.NaryEnd : NaryAssociationEnd from Standard.NaryAssociation to Standard.NaryAssociationEnd
        this.ruleMetaExpert.addDependencyRule(NaryAssociation.class, null, "NaryEnd");
        
        // no constraint on NaryAssociation.LinkToClass : ClassAssociation from Standard.NaryAssociation to Standard.ClassAssociation
        this.ruleMetaExpert.addDependencyRule(NaryAssociation.class, null, "LinkToClass");
    }

    @objid ("9f561a8a-8937-4480-ba81-b593d378054d")
    protected void registerMetaExpertForManifestation() {
        // Standard.Manifestation
        // -----------
        
        // no constraint on Manifestation.UtilizedElement : UmlModelElement from Standard.Manifestation to Standard.UmlModelElement
        this.ruleMetaExpert.addDependencyRule(Manifestation.class, null, "UtilizedElement");
    }

    @objid ("f589b2dd-5a4f-4b00-a042-ee08ce23077e")
    protected void registerMetaExpertForInterfaceRealization() {
        // Standard.InterfaceRealization
        // -----------
        
        // no constraint on InterfaceRealization.Implemented : Interface from Standard.InterfaceRealization to Standard.Interface
        this.ruleMetaExpert.addDependencyRule(InterfaceRealization.class, null, "Implemented");
    }

    @objid ("d925a4df-8d56-4dab-910a-d2a0428f6ce0")
    protected void registerMetaExpertForInterface() {
        // Standard.Interface
        // -----------
    }

    @objid ("c0d0b6f8-2ae3-4bad-a489-dfebc6e1846e")
    protected void registerMetaExpertForGeneralization() {
        // Standard.Generalization
        // -----------
        
        // no constraint on Generalization.SuperType : NameSpace from Standard.Generalization to Standard.NameSpace
        this.ruleMetaExpert.addDependencyRule(Generalization.class, null, "SuperType");
    }

    @objid ("c974f65e-891c-4598-9412-24d91eee6ac0")
    protected void registerMetaExpertForEnumerationLiteral() {
        // Standard.EnumerationLiteral
        // -----------
    }

    @objid ("e6c5f8da-2347-48e9-b845-d1549128c83d")
    protected void registerMetaExpertForEnumeration() {
        // Standard.Enumeration
        // -----------
        
        // no constraint on Enumeration.Value : EnumerationLiteral from Standard.Enumeration to Standard.EnumerationLiteral
        this.ruleMetaExpert.addDependencyRule(Enumeration.class, null, "Value");
    }

    @objid ("7e2aec19-e03f-43c3-9064-4e9df780fc4e")
    protected void registerMetaExpertForElementRealization() {
        // Standard.ElementRealization
        // -----------
    }

    @objid ("d27968ab-0c55-42c6-ab09-312f9a7295d6")
    protected void registerMetaExpertForElementImport() {
        // Standard.ElementImport
        // -----------
        
        // no constraint on ElementImport.ImportedElement : NameSpace from Standard.ElementImport to Standard.NameSpace
        this.ruleMetaExpert.addDependencyRule(ElementImport.class, null, "ImportedElement");
    }

    @objid ("35c39a05-a5bf-488f-b352-ae41d422c735")
    protected void registerMetaExpertForDataType() {
        // Standard.DataType
        // -----------
    }

    @objid ("7d213bca-4612-4bf7-8415-5645602d2588")
    protected void registerMetaExpertForConnectorEnd() {
        // Standard.ConnectorEnd
        // -----------
        
        // no constraint on ConnectorEnd.RepresentedFeature : UmlModelElement from Standard.ConnectorEnd to Standard.UmlModelElement
        this.ruleMetaExpert.addDependencyRule(ConnectorEnd.class, null, "RepresentedFeature");
    }

    @objid ("cda091f6-6804-4ce3-b8a9-18f7e0ea94e0")
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

    @objid ("ec325774-a3e5-4fa6-a45e-f400a0751ed2")
    protected void registerMetaExpertForConnector() {
        // Standard.Connector
        // -----------
    }

    @objid ("4fcac0c4-589f-4966-8530-4bffe3eae7c2")
    protected void registerMetaExpertForLink() {
        // Standard.Link
        // -----------
        
        // no constraint on Link.Model : Association from Standard.Link to Standard.Association
        this.ruleMetaExpert.addDependencyRule(Connector.class, null, "Model");
        this.ruleMetaExpert.addDependencyRule(Link.class, null, "Model");
    }

    @objid ("3d9aeece-c399-4f59-95bb-ce1fd95003df")
    protected void registerMetaExpertForComponentRealization() {
        // Standard.ComponentRealization
        // -----------
        
        // no constraint on ComponentRealization.RealizingClassifier : Classifier from Standard.ComponentRealization to Standard.Classifier
        this.ruleMetaExpert.addDependencyRule(ComponentRealization.class, null, "RealizingClassifier");
    }

    @objid ("5af766f2-ed9d-4d2b-b29a-5b2a6087dbfb")
    protected void registerMetaExpertForComponent() {
        // Standard.Component
        // -----------
        
        // no constraint on Component.Realization : ComponentRealization from Standard.Component to Standard.ComponentRealization
        this.ruleMetaExpert.addDependencyRule(Component.class, null, "Realization");
    }

    @objid ("0717561a-01ff-413d-9a77-03ff68536c0b")
    protected void registerMetaExpertForCollaborationUse() {
        // Standard.CollaborationUse
        // -----------
        
        // no constraint on CollaborationUse.Type : Collaboration from Standard.CollaborationUse to Standard.Collaboration
        this.ruleMetaExpert.addDependencyRule(CollaborationUse.class, null, "Type");
        
        // no constraint on CollaborationUse.RoleBinding : Binding from Standard.CollaborationUse to Standard.Binding
        this.ruleMetaExpert.addDependencyRule(CollaborationUse.class, null, "RoleBinding");
    }

    @objid ("8ce3ff4f-fe05-49ca-be14-e96ed0a40646")
    protected void registerMetaExpertForCollaboration() {
        // Standard.Collaboration
        // -----------
    }

    @objid ("7bb723b8-2fb5-4c41-86fe-c350832a7272")
    protected void registerMetaExpertForClassAssociation() {
        // Standard.ClassAssociation
        // -----------
        
        // no constraint on ClassAssociation.ClassPart : Class from Standard.ClassAssociation to Standard.Class
        this.ruleMetaExpert.addDependencyRule(ClassAssociation.class, null, "ClassPart");
    }

    @objid ("8d548c91-0516-45a6-8c4e-bca4affad2bf")
    protected void registerMetaExpertForClass() {
        // Standard.Class
        // -----------
    }

    @objid ("ac5d1257-e613-454a-b1a5-06a67ee571c2")
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

    @objid ("fc19161d-c6cd-4541-9c8b-15dd801d6509")
    protected void registerMetaExpertForBindableInstance() {
        // Standard.BindableInstance
        // -----------
        
        // no constraint on BindableInstance.RepresentedFeature : UmlModelElement from Standard.BindableInstance to Standard.UmlModelElement
        this.ruleMetaExpert.addDependencyRule(BindableInstance.class, null, "RepresentedFeature");
        this.ruleMetaExpert.addDependencyRule(Port.class, null, "RepresentedFeature");
    }

    @objid ("a669024d-8517-49fb-a37d-2ee0487ca782")
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

    @objid ("1a0e7fdb-4148-46ef-bac0-e65a5830c840")
    protected void registerMetaExpertForBehavioralFeature() {
        // Standard.BehavioralFeature
        // -----------
    }

    @objid ("582e2192-b42b-4e2d-b599-4f2a56353f55")
    protected void registerMetaExpertForAttributeLink() {
        // Standard.AttributeLink
        // -----------
        
        // no constraint on AttributeLink.Base : Attribute from Standard.AttributeLink to Standard.Attribute
        this.ruleMetaExpert.addDependencyRule(AttributeLink.class, null, "Base");
    }

    @objid ("bdb3e89a-4418-40ad-bac1-0aa3de637ac6")
    protected void registerMetaExpertForAttribute() {
        // Standard.Attribute
        // -----------
        
        // no constraint on Attribute.Type : GeneralClass from Standard.Attribute to Standard.GeneralClass
        this.ruleMetaExpert.addDependencyRule(Attribute.class, null, "Type");
    }

    @objid ("b91fbbf4-eabd-40a1-b483-fdd13a65b238")
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

    @objid ("2c33bfe8-cbab-4b53-bd2f-bf3a9fd9ce94")
    protected void registerMetaExpertForStructuralFeature() {
        // Standard.StructuralFeature
        // -----------
    }

    @objid ("ef658b8e-2ed9-43f8-90a4-df42a97ba623")
    protected void registerMetaExpertForFeature() {
        // Standard.Feature is abstract
        
        // -----------
    }

    @objid ("8e5b3abc-8628-4e01-a825-d9f770e7778d")
    protected void registerMetaExpertForAssociation() {
        // Standard.Association
        // -----------
        
        // no constraint on Association.LinkToClass : ClassAssociation from Standard.Association to Standard.ClassAssociation
        this.ruleMetaExpert.addDependencyRule(Association.class, null, "LinkToClass");
    }

    @objid ("ac6be7c8-72cd-4f70-b6d6-8e57ca2ef0a8")
    protected void registerMetaExpertForArtifact() {
        // Standard.Artifact
        // -----------
        
        // no constraint on Artifact.Utilized : Manifestation from Standard.Artifact to Standard.Manifestation
        this.ruleMetaExpert.addDependencyRule(Artifact.class, null, "Utilized");
    }

    @objid ("8c437115-0bbb-4109-8232-7e02f561b3d3")
    protected void registerMetaExpertForUsage() {
        // Standard.Usage
        // -----------
    }

    @objid ("5a9f6f29-972a-4a20-a879-358846436c45")
    protected void registerMetaExpertForSubstitution() {
        // Standard.Substitution
        // -----------
        
        // no constraint on Substitution.Contract : Classifier from Standard.Substitution to Standard.Classifier
        this.ruleMetaExpert.addDependencyRule(Substitution.class, null, "Contract");
    }

    @objid ("62832981-d76d-4861-846a-7ade9ba36777")
    protected void registerMetaExpertForAbstraction() {
        // Standard.Abstraction
        // -----------
    }

    @objid ("55fc7f5d-d811-4c5a-b86c-1ed388c496b1")
    protected void registerMetaExpertForInformationItem() {
        // Standard.InformationItem
        // -----------
        
        // no constraint on InformationItem.Represented : Classifier from Standard.InformationItem to Standard.Classifier
        this.ruleMetaExpert.addDependencyRule(InformationItem.class, null, "Represented");
    }

    @objid ("c6c89e45-a863-491c-9575-092b1f247e35")
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

    @objid ("e0d5bd3f-d39c-420e-aa1b-5a7b4928a468")
    protected void registerMetaExpertForDataFlow() {
        // Standard.DataFlow
        // -----------
        
        // no constraint on DataFlow.Destination : NameSpace from Standard.DataFlow to Standard.NameSpace
        this.ruleMetaExpert.addDependencyRule(DataFlow.class, null, "Destination");
        
        // no constraint on DataFlow.SModel : Signal from Standard.DataFlow to Standard.Signal
        this.ruleMetaExpert.addDependencyRule(DataFlow.class, null, "SModel");
    }

    @objid ("0b055ca9-538f-4acb-ac63-a0167c6eac19")
    protected void registerMetaExpertForUseCaseDependency() {
        // Standard.UseCaseDependency
        // -----------
        
        // no constraint on UseCaseDependency.ExtensionLocation : ExtensionPoint from Standard.UseCaseDependency to Standard.ExtensionPoint
        this.ruleMetaExpert.addDependencyRule(UseCaseDependency.class, null, "ExtensionLocation");
        
        // no constraint on UseCaseDependency.Target : UseCase from Standard.UseCaseDependency to Standard.UseCase
        this.ruleMetaExpert.addDependencyRule(UseCaseDependency.class, null, "Target");
    }

    @objid ("0cf9e448-6bea-468c-8a5c-3faa3da538dc")
    protected void registerMetaExpertForUseCase() {
        // Standard.UseCase
        // -----------
        
        // no constraint on UseCase.Used : UseCaseDependency from Standard.UseCase to Standard.UseCaseDependency
        this.ruleMetaExpert.addDependencyRule(UseCase.class, null, "Used");
        
        // no constraint on UseCase.OwnedExtension : ExtensionPoint from Standard.UseCase to Standard.ExtensionPoint
        this.ruleMetaExpert.addDependencyRule(UseCase.class, null, "OwnedExtension");
    }

    @objid ("0ad380a2-524c-42b7-92f5-f0dac85e2ecd")
    protected void registerMetaExpertForExtensionPoint() {
        // Standard.ExtensionPoint
        // -----------
    }

    @objid ("f33fc8fe-73eb-4297-b15c-6730a85af05f")
    protected void registerMetaExpertForActor() {
        // Standard.Actor
        // -----------
    }

    @objid ("e233bf78-ac03-4bac-a75a-21e801ed364a")
    protected void registerMetaExpertForTerminatePseudoState() {
        // Standard.TerminatePseudoState
        // -----------
    }

    @objid ("ff5ce88e-a035-4795-8450-ceaca73fdf42")
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

    @objid ("3c179c78-eb1f-4813-8c35-52d9c012b65b")
    protected void registerMetaExpertForShallowHistoryPseudoState() {
        // Standard.ShallowHistoryPseudoState
        // -----------
    }

    @objid ("64e05300-03cc-4128-bc14-8d45e1c4e31b")
    protected void registerMetaExpertForRegion() {
        // Standard.Region
        // -----------
        
        // no constraint on Region.Sub : StateVertex from Standard.Region to Standard.StateVertex
        this.ruleMetaExpert.addDependencyRule(Region.class, null, "Sub");
    }

    @objid ("23561d88-9215-487e-b2b0-8ac2b4027dbe")
    protected void registerMetaExpertForJunctionPseudoState() {
        // Standard.JunctionPseudoState
        // -----------
    }

    @objid ("388bb96f-a101-4072-80b3-0d0728d64b79")
    protected void registerMetaExpertForJoinPseudoState() {
        // Standard.JoinPseudoState
        // -----------
    }

    @objid ("0dec8f1d-eb43-48a1-ba35-4f7a15063a19")
    protected void registerMetaExpertForInternalTransition() {
        // Standard.InternalTransition
        // -----------
    }

    @objid ("91985c63-5148-4e3e-8c9f-e06db6492fff")
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

    @objid ("fe886f9f-901f-4bbd-b8d3-bd705f84b002")
    protected void registerMetaExpertForInitialPseudoState() {
        // Standard.InitialPseudoState
        // -----------
    }

    @objid ("713f92a0-826d-4f58-9bba-1aeea09bb317")
    protected void registerMetaExpertForForkPseudoState() {
        // Standard.ForkPseudoState
        // -----------
    }

    @objid ("4b3d1639-82ba-426f-9bff-e439a81550cc")
    protected void registerMetaExpertForFinalState() {
        // Standard.FinalState
        // -----------
    }

    @objid ("791cc59b-1ab1-4d2e-8596-7dd010753dc9")
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

    @objid ("9c3a5fda-f294-4f75-8afd-048a0593d0a6")
    protected void registerMetaExpertForExitPointPseudoState() {
        // Standard.ExitPointPseudoState
        // -----------
    }

    @objid ("f9b802af-6d0a-4ff7-adfd-08a8358a5576")
    protected void registerMetaExpertForEntryPointPseudoState() {
        // Standard.EntryPointPseudoState
        // -----------
    }

    @objid ("d0ddc759-6bd2-40f4-be4a-4791da9ba0dd")
    protected void registerMetaExpertForDeepHistoryPseudoState() {
        // Standard.DeepHistoryPseudoState
        // -----------
    }

    @objid ("a2dfbf3e-e2fb-4f9f-bd7b-081aeec63f87")
    protected void registerMetaExpertForConnectionPointReference() {
        // Standard.ConnectionPointReference
        // -----------
        
        // no constraint on ConnectionPointReference.Exit : ExitPointPseudoState from Standard.ConnectionPointReference to Standard.ExitPointPseudoState
        this.ruleMetaExpert.addDependencyRule(ConnectionPointReference.class, null, "Exit");
        
        // no constraint on ConnectionPointReference.Entry : EntryPointPseudoState from Standard.ConnectionPointReference to Standard.EntryPointPseudoState
        this.ruleMetaExpert.addDependencyRule(ConnectionPointReference.class, null, "Entry");
    }

    @objid ("a36ae648-de90-4137-919d-e23fd23873bb")
    protected void registerMetaExpertForChoicePseudoState() {
        // Standard.ChoicePseudoState
        // -----------
    }

    @objid ("33c052d4-46f3-4a04-8252-817dc99bc0d4")
    protected void registerMetaExpertForAbstractPseudoState() {
        // Standard.AbstractPseudoState is abstract
        
        // -----------
    }

    @objid ("0b7a23d4-9da6-4ba7-a525-689e1b35ec5e")
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

    @objid ("4014539e-a001-4fdb-92d5-382c77107ab0")
    protected void registerMetaExpertForTerminateSpecification() {
        // Standard.TerminateSpecification
        // -----------
    }

    @objid ("55cbb5d2-72c9-4a45-bd36-4a2cd78b4a50")
    protected void registerMetaExpertForStateInvariant() {
        // Standard.StateInvariant
        // -----------
    }

    @objid ("eaf84547-b564-4620-a02f-18cbb74874ba")
    protected void registerMetaExpertForPartDecomposition() {
        // Standard.PartDecomposition
        // -----------
    }

    @objid ("0cd39734-971b-40a0-9d94-6ecfb6a19372")
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

    @objid ("3c860731-5266-4145-bf20-f6433df9d632")
    protected void registerMetaExpertForLifeline() {
        // Standard.Lifeline
        // -----------
        
        // no constraint on Lifeline.DecomposedAs : PartDecomposition from Standard.Lifeline to Standard.PartDecomposition
        this.ruleMetaExpert.addDependencyRule(Lifeline.class, null, "DecomposedAs");
        
        // no constraint on Lifeline.Represented : Instance from Standard.Lifeline to Standard.Instance
        this.ruleMetaExpert.addDependencyRule(Lifeline.class, null, "Represented");
    }

    @objid ("0c249f76-cdfa-40f6-adfc-f9c179fa352e")
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

    @objid ("ed26897f-a5e1-402d-856b-7b2ecda9f821")
    protected void registerMetaExpertForInteractionOperand() {
        // Standard.InteractionOperand
        // -----------
        
        // no constraint on InteractionOperand.Fragment : InteractionFragment from Standard.InteractionOperand to Standard.InteractionFragment
        this.ruleMetaExpert.addDependencyRule(InteractionOperand.class, null, "Fragment");
    }

    @objid ("b3fc76e1-7935-45cf-8487-cdf616746895")
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

    @objid ("ecfcb703-4105-40dd-851a-70133340af1a")
    protected void registerMetaExpertForGeneralOrdering() {
        // Standard.GeneralOrdering
        // -----------
        
        // no constraint on GeneralOrdering.After : OccurrenceSpecification from Standard.GeneralOrdering to Standard.OccurrenceSpecification
        this.ruleMetaExpert.addDependencyRule(GeneralOrdering.class, null, "After");
    }

    @objid ("32911075-0065-4baf-9951-aaecaf6c0eb0")
    protected void registerMetaExpertForGate() {
        // Standard.Gate
        // -----------
        
        // no constraint on Gate.Formal : Gate from Standard.Gate to Standard.Gate
        this.ruleMetaExpert.addDependencyRule(Gate.class, null, "Formal");
    }

    @objid ("934ac9d8-7fc1-4ca9-aedd-a6b9e8aa9520")
    protected void registerMetaExpertForExecutionSpecification() {
        // Standard.ExecutionSpecification
        // -----------
        
        // no constraint on ExecutionSpecification.Finish : ExecutionOccurenceSpecification from Standard.ExecutionSpecification to Standard.ExecutionOccurenceSpecification
        this.ruleMetaExpert.addDependencyRule(ExecutionSpecification.class, null, "Finish");
    }

    @objid ("43acd097-bddc-4346-9acb-975b1477b831")
    protected void registerMetaExpertForExecutionOccurenceSpecification() {
        // Standard.ExecutionOccurenceSpecification
        // -----------
        
        // no constraint on ExecutionOccurenceSpecification.Started : ExecutionSpecification from Standard.ExecutionOccurenceSpecification to Standard.ExecutionSpecification
        this.ruleMetaExpert.addDependencyRule(ExecutionOccurenceSpecification.class, null, "Started");
        this.ruleMetaExpert.addDependencyRule(TerminateSpecification.class, null, "Started");
    }

    @objid ("e8f76e41-72d6-4e05-b81c-c4e6c5332087")
    protected void registerMetaExpertForMessageEnd() {
        // Standard.MessageEnd is abstract
        
        // -----------
        
        // no constraint on MessageEnd.SentMessage : Message from Standard.MessageEnd to Standard.Message
        this.ruleMetaExpert.addDependencyRule(ExecutionOccurenceSpecification.class, null, "SentMessage");
        this.ruleMetaExpert.addDependencyRule(Gate.class, null, "SentMessage");
        this.ruleMetaExpert.addDependencyRule(MessageEnd.class, null, "SentMessage");
        this.ruleMetaExpert.addDependencyRule(TerminateSpecification.class, null, "SentMessage");
    }

    @objid ("1a8bf0db-5602-4e71-8144-83191e415c3b")
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

    @objid ("8eb0cdaf-9cc1-406e-be0b-59b4910a0637")
    protected void registerMetaExpertForDurationConstraint() {
        // Standard.DurationConstraint
        // -----------
    }

    @objid ("1cbc2755-7fb8-40e0-bfcd-7c070759525f")
    protected void registerMetaExpertForConstraint() {
        // Standard.Constraint
        // -----------
        
        // no constraint on Constraint.ConstrainedElement : UmlModelElement from Standard.Constraint to Standard.UmlModelElement
        this.ruleMetaExpert.addDependencyRule(Constraint.class, null, "ConstrainedElement");
        this.ruleMetaExpert.addDependencyRule(DurationConstraint.class, null, "ConstrainedElement");
    }

    @objid ("730db1b3-fc91-4b7b-86da-c80f8c5f65d2")
    protected void registerMetaExpertForCombinedFragment() {
        // Standard.CombinedFragment
        // -----------
        
        // no constraint on CombinedFragment.Operand : InteractionOperand from Standard.CombinedFragment to Standard.InteractionOperand
        this.ruleMetaExpert.addDependencyRule(CombinedFragment.class, null, "Operand");
        
        // no constraint on CombinedFragment.FragmentGate : Gate from Standard.CombinedFragment to Standard.Gate
        this.ruleMetaExpert.addDependencyRule(CombinedFragment.class, null, "FragmentGate");
    }

    @objid ("92e69bb3-740d-41f8-9217-f30237c98545")
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

    @objid ("00d89774-b77d-452b-b603-5d022264cfcb")
    protected void registerMetaExpertForCommunicationNode() {
        // Standard.CommunicationNode
        // -----------
        
        // no constraint on CommunicationNode.Represented : Instance from Standard.CommunicationNode to Standard.Instance
        this.ruleMetaExpert.addDependencyRule(CommunicationNode.class, null, "Represented");
        
        // no constraint on CommunicationNode.Started : CommunicationChannel from Standard.CommunicationNode to Standard.CommunicationChannel
        this.ruleMetaExpert.addDependencyRule(CommunicationNode.class, null, "Started");
    }

    @objid ("bd357d73-e7c8-489f-9d86-a52b87e2349d")
    protected void registerMetaExpertForCommunicationMessage() {
        // Standard.CommunicationMessage
        // -----------
        
        // no constraint on CommunicationMessage.Invoked : Operation from Standard.CommunicationMessage to Standard.Operation
        this.ruleMetaExpert.addDependencyRule(CommunicationMessage.class, null, "Invoked");
        
        // no constraint on CommunicationMessage.SignalSignature : Signal from Standard.CommunicationMessage to Standard.Signal
        this.ruleMetaExpert.addDependencyRule(CommunicationMessage.class, null, "SignalSignature");
    }

    @objid ("d2c7929c-0d19-451a-8a11-c6ada498191c")
    protected void registerMetaExpertForCommunicationInteraction() {
        // Standard.CommunicationInteraction
        // -----------
        
        // no constraint on CommunicationInteraction.Owned : CommunicationNode from Standard.CommunicationInteraction to Standard.CommunicationNode
        this.ruleMetaExpert.addDependencyRule(CommunicationInteraction.class, null, "Owned");
    }

    @objid ("3cca46ac-2f11-47dd-9c7d-d467ed8b52ea")
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

    @objid ("2201a494-c6f0-4338-bea5-e823863ccc51")
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

    @objid ("43880751-9b37-4293-84fa-bc7efb07640b")
    protected void registerMetaExpertForGeneralClass() {
        // Standard.GeneralClass is abstract
        
        // -----------
    }

    @objid ("2afc9abc-61ef-446d-9724-16084ae9c5b9")
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

    @objid ("1aa23345-4ade-406d-926e-064d057dec7a")
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

    @objid ("6c7575e6-e938-4742-9d2f-9b9a86931a74")
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

    @objid ("c6009664-a74d-4308-8d48-f0323ff94428")
    protected void registerMetaExpertForOpaqueBehavior() {
        // Standard.OpaqueBehavior
        // -----------
    }

    @objid ("ebc4af74-3861-4880-8e38-1066484fa616")
    protected void registerMetaExpertForEvent() {
        // Standard.Event
        // -----------
        
        // no constraint on Event.Model : Signal from Standard.Event to Standard.Signal
        this.ruleMetaExpert.addDependencyRule(Event.class, null, "Model");
        
        // no constraint on Event.Called : Operation from Standard.Event to Standard.Operation
        this.ruleMetaExpert.addDependencyRule(Event.class, null, "Called");
    }

    @objid ("7974898d-efb6-4c92-9057-7ae53bf87a6c")
    protected void registerMetaExpertForBehaviorParameter() {
        // Standard.BehaviorParameter
        // -----------
        
        // no constraint on BehaviorParameter.Mapped : Parameter from Standard.BehaviorParameter to Standard.Parameter
        this.ruleMetaExpert.addDependencyRule(BehaviorParameter.class, null, "Mapped");
    }

    @objid ("fcbf7aa2-45aa-45e1-aecd-a81eaae4316f")
    protected void registerMetaExpertForParameter() {
        // Standard.Parameter
        // -----------
        
        // no constraint on Parameter.Type : GeneralClass from Standard.Parameter to Standard.GeneralClass
        this.ruleMetaExpert.addDependencyRule(BehaviorParameter.class, null, "Type");
        this.ruleMetaExpert.addDependencyRule(Parameter.class, null, "Type");
    }

    @objid ("7012822c-506b-48fc-b4a8-19fab078280e")
    protected void registerMetaExpertForValuePin() {
        // Standard.ValuePin
        // -----------
    }

    @objid ("e7a24c09-96d9-4722-955c-cf63ce8df7b7")
    protected void registerMetaExpertForSendSignalAction() {
        // Standard.SendSignalAction
        // -----------
        
        // no constraint on SendSignalAction.Sent : Signal from Standard.SendSignalAction to Standard.Signal
        this.ruleMetaExpert.addDependencyRule(SendSignalAction.class, null, "Sent");
    }

    @objid ("5c819eac-75db-4ca4-b30f-8b800dacd84f")
    protected void registerMetaExpertForOutputPin() {
        // Standard.OutputPin
        // -----------
    }

    @objid ("0c1d8002-1eec-4cdc-9afd-f84502cc3ab7")
    protected void registerMetaExpertForOpaqueAction() {
        // Standard.OpaqueAction
        // -----------
    }

    @objid ("4e8dec1b-51a5-4d65-8313-6ae3ae5d02ac")
    protected void registerMetaExpertForObjectFlow() {
        // Standard.ObjectFlow
        // -----------
    }

    @objid ("81856a51-27bf-48af-bdc0-dde9146b3710")
    protected void registerMetaExpertForMessageFlow() {
        // Standard.MessageFlow
        // -----------
        
        // no constraint on MessageFlow.TargetPartition : ActivityPartition from Standard.MessageFlow to Standard.ActivityPartition
        this.ruleMetaExpert.addDependencyRule(MessageFlow.class, null, "TargetPartition");
    }

    @objid ("24160e86-aead-45b8-b488-0188b13094ef")
    protected void registerMetaExpertForLoopNode() {
        // Standard.LoopNode
        // -----------
    }

    @objid ("80650cc1-d601-41db-ac1a-d5d840c5a265")
    protected void registerMetaExpertForInterruptibleActivityRegion() {
        // Standard.InterruptibleActivityRegion
        // -----------
        
        // no constraint on InterruptibleActivityRegion.InterruptingEdge : ActivityEdge from Standard.InterruptibleActivityRegion to Standard.ActivityEdge
        this.ruleMetaExpert.addDependencyRule(InterruptibleActivityRegion.class, null, "InterruptingEdge");
    }

    @objid ("d88b4e9a-a1e4-4b50-a23e-d54de969e062")
    protected void registerMetaExpertForInstanceNode() {
        // Standard.InstanceNode
        // -----------
    }

    @objid ("a262df17-e345-49ad-a4eb-a3b932cd2765")
    protected void registerMetaExpertForInputPin() {
        // Standard.InputPin
        // -----------
    }

    @objid ("7dc33847-323d-422b-b778-a4812d627dd9")
    protected void registerMetaExpertForPin() {
        // Standard.Pin is abstract
        
        // -----------
        
        // no constraint on Pin.Matched : Parameter from Standard.Pin to Standard.Parameter
        this.ruleMetaExpert.addDependencyRule(InputPin.class, null, "Matched");
        this.ruleMetaExpert.addDependencyRule(OutputPin.class, null, "Matched");
        this.ruleMetaExpert.addDependencyRule(Pin.class, null, "Matched");
        this.ruleMetaExpert.addDependencyRule(ValuePin.class, null, "Matched");
    }

    @objid ("3e7ccd1d-770d-476f-854b-73c9ada537c8")
    protected void registerMetaExpertForInitialNode() {
        // Standard.InitialNode
        // -----------
    }

    @objid ("61a68bc3-9284-433a-b925-3ea384460bb8")
    protected void registerMetaExpertForForkJoinNode() {
        // Standard.ForkJoinNode
        // -----------
    }

    @objid ("939ce04d-92e7-4827-800f-bee38677080e")
    protected void registerMetaExpertForFlowFinalNode() {
        // Standard.FlowFinalNode
        // -----------
    }

    @objid ("c19e4d60-ae55-4311-a23e-e5999034417e")
    protected void registerMetaExpertForExpansionRegion() {
        // Standard.ExpansionRegion
        // -----------
        
        // no constraint on ExpansionRegion.OutputElement : ExpansionNode from Standard.ExpansionRegion to Standard.ExpansionNode
        this.ruleMetaExpert.addDependencyRule(ExpansionRegion.class, null, "OutputElement");
        
        // no constraint on ExpansionRegion.InputElement : ExpansionNode from Standard.ExpansionRegion to Standard.ExpansionNode
        this.ruleMetaExpert.addDependencyRule(ExpansionRegion.class, null, "InputElement");
    }

    @objid ("c8b26fe9-009c-4c14-8d04-be3be0dd70f9")
    protected void registerMetaExpertForExpansionNode() {
        // Standard.ExpansionNode
        // -----------
    }

    @objid ("a82fbead-811e-4d27-80b5-c4aa7e635c82")
    protected void registerMetaExpertForExceptionHandler() {
        // Standard.ExceptionHandler
        // -----------
        
        // no constraint on ExceptionHandler.ExceptionInput : InputPin from Standard.ExceptionHandler to Standard.InputPin
        this.ruleMetaExpert.addDependencyRule(ExceptionHandler.class, null, "ExceptionInput");
        
        // no constraint on ExceptionHandler.ExceptionType : GeneralClass from Standard.ExceptionHandler to Standard.GeneralClass
        this.ruleMetaExpert.addDependencyRule(ExceptionHandler.class, null, "ExceptionType");
    }

    @objid ("f62ab7d3-3564-4422-aef6-82eecd99cd30")
    protected void registerMetaExpertForDecisionMergeNode() {
        // Standard.DecisionMergeNode
        // -----------
    }

    @objid ("d85f6087-a236-42ae-a442-ae66e6ce61db")
    protected void registerMetaExpertForDataStoreNode() {
        // Standard.DataStoreNode
        // -----------
    }

    @objid ("e9ad6c5e-2af0-4082-b041-2236a96e5d11")
    protected void registerMetaExpertForControlFlow() {
        // Standard.ControlFlow
        // -----------
    }

    @objid ("87b77ea3-8882-4bdf-ac4c-620546c81ee7")
    protected void registerMetaExpertForConditionalNode() {
        // Standard.ConditionalNode
        // -----------
        
        // no constraint on ConditionalNode.OwnedClause : Clause from Standard.ConditionalNode to Standard.Clause
        this.ruleMetaExpert.addDependencyRule(ConditionalNode.class, null, "OwnedClause");
    }

    @objid ("0a97394f-0772-4712-b0a6-90a5398ae51c")
    protected void registerMetaExpertForStructuredActivityNode() {
        // Standard.StructuredActivityNode
        // -----------
        
        // no constraint on StructuredActivityNode.Body : ActivityNode from Standard.StructuredActivityNode to Standard.ActivityNode
        this.ruleMetaExpert.addDependencyRule(ConditionalNode.class, null, "Body");
        this.ruleMetaExpert.addDependencyRule(ExpansionRegion.class, null, "Body");
        this.ruleMetaExpert.addDependencyRule(LoopNode.class, null, "Body");
        this.ruleMetaExpert.addDependencyRule(StructuredActivityNode.class, null, "Body");
    }

    @objid ("14930aed-74c7-4708-a41f-2055be988e3a")
    protected void registerMetaExpertForClause() {
        // Standard.Clause
        // -----------
        
        // no constraint on Clause.Body : ActivityNode from Standard.Clause to Standard.ActivityNode
        this.ruleMetaExpert.addDependencyRule(Clause.class, null, "Body");
    }

    @objid ("7a882ca1-ea0e-41ca-8881-29cba5f4f10b")
    protected void registerMetaExpertForCentralBufferNode() {
        // Standard.CentralBufferNode
        // -----------
    }

    @objid ("4eaa63bc-5348-4f28-864c-579c8e67c726")
    protected void registerMetaExpertForCallOperationAction() {
        // Standard.CallOperationAction
        // -----------
        
        // no constraint on CallOperationAction.Called : Operation from Standard.CallOperationAction to Standard.Operation
        this.ruleMetaExpert.addDependencyRule(CallOperationAction.class, null, "Called");
    }

    @objid ("641129ee-551d-46a8-88ab-a188f5d18e0b")
    protected void registerMetaExpertForCallBehaviorAction() {
        // Standard.CallBehaviorAction
        // -----------
        
        // no constraint on CallBehaviorAction.Called : Behavior from Standard.CallBehaviorAction to Standard.Behavior
        this.ruleMetaExpert.addDependencyRule(CallBehaviorAction.class, null, "Called");
    }

    @objid ("31469617-85e6-46be-889c-3b4a7f13228b")
    protected void registerMetaExpertForCallAction() {
        // Standard.CallAction is abstract
        
        // -----------
    }

    @objid ("f1ce42a9-3319-4f08-a789-18f6df39d25c")
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

    @objid ("6198a05e-53b1-4169-b28c-c9bfcd055323")
    protected void registerMetaExpertForActivityParameterNode() {
        // Standard.ActivityParameterNode
        // -----------
    }

    @objid ("a54f16b0-b0ab-4e8b-b71f-76633f25d6c0")
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

    @objid ("c86ddfc0-281a-4676-ad35-c55cec72ca33")
    protected void registerMetaExpertForActivityGroup() {
        // Standard.ActivityGroup is abstract
        
        // -----------
    }

    @objid ("6c2aebb7-df94-42ca-ac35-282cb0bc755e")
    protected void registerMetaExpertForActivityFinalNode() {
        // Standard.ActivityFinalNode
        // -----------
    }

    @objid ("7d5d8893-cc33-418d-80d8-e5d5930a31d8")
    protected void registerMetaExpertForFinalNode() {
        // Standard.FinalNode is abstract
        
        // -----------
    }

    @objid ("0658c14d-807c-4b25-8796-07a0622d17ff")
    protected void registerMetaExpertForControlNode() {
        // Standard.ControlNode is abstract
        
        // -----------
    }

    @objid ("e94fa207-6b75-4c15-afed-8f3a4ebdd321")
    protected void registerMetaExpertForActivityEdge() {
        // Standard.ActivityEdge is abstract
        
        // -----------
        
        // no constraint on ActivityEdge.Target : ActivityNode from Standard.ActivityEdge to Standard.ActivityNode
        this.ruleMetaExpert.addDependencyRule(ActivityEdge.class, null, "Target");
        this.ruleMetaExpert.addDependencyRule(ControlFlow.class, null, "Target");
        this.ruleMetaExpert.addDependencyRule(MessageFlow.class, null, "Target");
        this.ruleMetaExpert.addDependencyRule(ObjectFlow.class, null, "Target");
    }

    @objid ("7a1e2302-e428-4110-aa0a-e482bc0da685")
    protected void registerMetaExpertForActivity() {
        // Standard.Activity
        // -----------
        
        // no constraint on Activity.OwnedGroup : ActivityGroup from Standard.Activity to Standard.ActivityGroup
        this.ruleMetaExpert.addDependencyRule(Activity.class, null, "OwnedGroup");
        
        // no constraint on Activity.OwnedNode : ActivityNode from Standard.Activity to Standard.ActivityNode
        this.ruleMetaExpert.addDependencyRule(Activity.class, null, "OwnedNode");
    }

    @objid ("822427e6-04f4-4c91-8cb2-d76c3f967ee2")
    protected void registerMetaExpertForAcceptTimeEventAction() {
        // Standard.AcceptTimeEventAction
        // -----------
    }

    @objid ("27c6254a-359e-44da-81af-57a04720fa79")
    protected void registerMetaExpertForAcceptSignalAction() {
        // Standard.AcceptSignalAction
        // -----------
        
        // no constraint on AcceptSignalAction.Accepted : Signal from Standard.AcceptSignalAction to Standard.Signal
        this.ruleMetaExpert.addDependencyRule(AcceptSignalAction.class, null, "Accepted");
    }

    @objid ("2bd14a1e-fee9-4faf-820b-a39af93a244f")
    protected void registerMetaExpertForAcceptChangeEventAction() {
        // Standard.AcceptChangeEventAction
        // -----------
    }

    @objid ("9f2a05a8-8ebc-478b-8de3-2ff83f470a3c")
    protected void registerMetaExpertForAcceptCallEventAction() {
        // Standard.AcceptCallEventAction
        // -----------
        
        // no constraint on AcceptCallEventAction.Called : Operation from Standard.AcceptCallEventAction to Standard.Operation
        this.ruleMetaExpert.addDependencyRule(AcceptCallEventAction.class, null, "Called");
    }

    @objid ("292fc965-9d79-4973-897f-ae592a3185de")
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

    @objid ("40c27503-0c24-4f45-98f1-c42840461464")
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

    @objid ("0dd7a3a7-82e2-40a7-a434-0981da07ea63")
    protected void registerMetaExpertForUseCaseDiagram() {
        // Standard.UseCaseDiagram
        // -----------
    }

    @objid ("2e2c9632-a16d-44e7-862b-57468ef12f2f")
    protected void registerMetaExpertForStateMachineDiagram() {
        // Standard.StateMachineDiagram
        // -----------
    }

    @objid ("1ec355d1-3cc1-4fbc-a1e7-70e812500a24")
    protected void registerMetaExpertForSequenceDiagram() {
        // Standard.SequenceDiagram
        // -----------
    }

    @objid ("8ea9175b-ae0f-42dc-8eca-95f2b9c3441f")
    protected void registerMetaExpertForObjectDiagram() {
        // Standard.ObjectDiagram
        // -----------
    }

    @objid ("f54cbac5-668c-40f1-bb35-9a46b2f171b5")
    protected void registerMetaExpertForDeploymentDiagram() {
        // Standard.DeploymentDiagram
        // -----------
    }

    @objid ("9c4a3090-7ae8-41f6-a03b-4f774f894d3c")
    protected void registerMetaExpertForCompositeStructureDiagram() {
        // Standard.CompositeStructureDiagram
        // -----------
    }

    @objid ("50a2afa1-609e-48dd-aa5e-62dbf01d6fd0")
    protected void registerMetaExpertForCommunicationDiagram() {
        // Standard.CommunicationDiagram
        // -----------
    }

    @objid ("1d33b30e-cfe8-407f-b591-38b340bc3e17")
    protected void registerMetaExpertForClassDiagram() {
        // Standard.ClassDiagram
        // -----------
    }

    @objid ("de04c209-ff59-4c7e-9be4-01dfbe395416")
    protected void registerMetaExpertForStaticDiagram() {
        // Standard.StaticDiagram
        // -----------
    }

    @objid ("9489d828-150a-464a-a151-0f056b9f592e")
    protected void registerMetaExpertForActivityDiagram() {
        // Standard.ActivityDiagram
        // -----------
    }

    @objid ("f8072bb3-2dc6-4a72-beff-5e86ab5433b6")
    protected void registerMetaExpertForBpmnSharedDefinitions() {
        // Standard.BpmnSharedDefinitions
        // -----------
        
        // no constraint on BpmnSharedDefinitions.RootElement : BpmnSharedElement from Standard.BpmnSharedDefinitions to Standard.BpmnSharedElement
        this.ruleMetaExpert.addDependencyRule(BpmnSharedDefinitions.class, null, "RootElement");
    }

    @objid ("28a13595-e0b7-4780-bf2d-aeec34c447bd")
    protected void registerMetaExpertForBpmnGroup() {
        // Standard.BpmnGroup
        // -----------
        
        // no constraint on BpmnGroup.Categorized : BpmnFlowElement from Standard.BpmnGroup to Standard.BpmnFlowElement
        this.ruleMetaExpert.addDependencyRule(BpmnGroup.class, null, "Categorized");
    }

    @objid ("ad7c054d-940d-474c-9b6c-82db3d40c56f")
    protected void registerMetaExpertForBpmnAssociation() {
        // Standard.BpmnAssociation
        // -----------
        
        // no constraint on BpmnAssociation.TargetRef : BpmnBaseElement from Standard.BpmnAssociation to Standard.BpmnBaseElement
        this.ruleMetaExpert.addDependencyRule(BpmnAssociation.class, null, "TargetRef");
        
        // no constraint on BpmnAssociation.SourceRef : BpmnBaseElement from Standard.BpmnAssociation to Standard.BpmnBaseElement
        this.ruleMetaExpert.addDependencyRule(BpmnAssociation.class, null, "SourceRef");
    }

    @objid ("d0bc1503-561f-4bc5-b1f6-60f1cc74dc72")
    protected void registerMetaExpertForBpmnArtifact() {
        // Standard.BpmnArtifact is abstract
        
        // -----------
    }

    @objid ("bdb30aa1-54e1-408e-af05-e486ffae7dcb")
    protected void registerMetaExpertForBpmnResourceRole() {
        // Standard.BpmnResourceRole
        // -----------
        
        // no constraint on BpmnResourceRole.ResourceRef : BpmnResource from Standard.BpmnResourceRole to Standard.BpmnResource
        this.ruleMetaExpert.addDependencyRule(BpmnResourceRole.class, null, "ResourceRef");
        
        // no constraint on BpmnResourceRole.ResourceParameterBinding : BpmnResourceParameterBinding from Standard.BpmnResourceRole to Standard.BpmnResourceParameterBinding
        this.ruleMetaExpert.addDependencyRule(BpmnResourceRole.class, null, "ResourceParameterBinding");
    }

    @objid ("0ba144ff-ca6f-4392-86cf-1c394654c5fe")
    protected void registerMetaExpertForBpmnResourceParameterBinding() {
        // Standard.BpmnResourceParameterBinding
        // -----------
        
        // no constraint on BpmnResourceParameterBinding.ParameterRef : BpmnResourceParameter from Standard.BpmnResourceParameterBinding to Standard.BpmnResourceParameter
        this.ruleMetaExpert.addDependencyRule(BpmnResourceParameterBinding.class, null, "ParameterRef");
    }

    @objid ("8ebdc46b-4276-40f8-bf75-a5dc46e3fa8f")
    protected void registerMetaExpertForBpmnResourceParameter() {
        // Standard.BpmnResourceParameter
        // -----------
        
        // no constraint on BpmnResourceParameter.Type : BpmnItemDefinition from Standard.BpmnResourceParameter to Standard.BpmnItemDefinition
        this.ruleMetaExpert.addDependencyRule(BpmnResourceParameter.class, null, "Type");
    }

    @objid ("a7261e77-7f3e-4b61-9e88-265c31ea3b8d")
    protected void registerMetaExpertForBpmnResource() {
        // Standard.BpmnResource
        // -----------
        
        // no constraint on BpmnResource.Parameter : BpmnResourceParameter from Standard.BpmnResource to Standard.BpmnResourceParameter
        this.ruleMetaExpert.addDependencyRule(BpmnResource.class, null, "Parameter");
    }

    @objid ("38456a2e-b847-4a53-b1d8-9d2f21927fce")
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

    @objid ("62925edf-973a-4baf-93a4-cba78554c5a2")
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

    @objid ("db060139-e6c9-4c73-8411-e796a5480cf3")
    protected void registerMetaExpertForBpmnLaneSet() {
        // Standard.BpmnLaneSet
        // -----------
        
        // no constraint on BpmnLaneSet.Lane : BpmnLane from Standard.BpmnLaneSet to Standard.BpmnLane
        this.ruleMetaExpert.addDependencyRule(BpmnLaneSet.class, null, "Lane");
    }

    @objid ("dca49daf-3416-4ac2-81e2-63ee137e2b80")
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

    @objid ("fe297611-6c6b-46ca-bf18-69ccfe31bba1")
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

    @objid ("4978646c-e76b-425b-8c5b-44d493882342")
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

    @objid ("93c6f0c7-1912-442e-8c92-17afef046887")
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

    @objid ("d4fcf504-fc62-4e5c-88b7-82d322b9eb02")
    protected void registerMetaExpertForBpmnSequenceFlowDataAssociation() {
        // Standard.BpmnSequenceFlowDataAssociation
        // -----------
        
        // no constraint on BpmnSequenceFlowDataAssociation.Connected : BpmnSequenceFlow from Standard.BpmnSequenceFlowDataAssociation to Standard.BpmnSequenceFlow
        this.ruleMetaExpert.addDependencyRule(BpmnSequenceFlowDataAssociation.class, null, "Connected");
        
        // no constraint on BpmnSequenceFlowDataAssociation.DataAssociation : BpmnDataAssociation from Standard.BpmnSequenceFlowDataAssociation to Standard.BpmnDataAssociation
        this.ruleMetaExpert.addDependencyRule(BpmnSequenceFlowDataAssociation.class, null, "DataAssociation");
    }

    @objid ("18b221f7-af59-4225-9b84-554844696949")
    protected void registerMetaExpertForBpmnItemDefinition() {
        // Standard.BpmnItemDefinition
        // -----------
    }

    @objid ("372e750a-0387-4c3f-9f23-98142d321e9d")
    protected void registerMetaExpertForBpmnDataStore() {
        // Standard.BpmnDataStore
        // -----------
    }

    @objid ("1d50ee41-7b08-4375-b5f8-306c23761586")
    protected void registerMetaExpertForBpmnDataState() {
        // Standard.BpmnDataState
        // -----------
    }

    @objid ("a1a5457b-aafb-420b-99cd-c65e847276bf")
    protected void registerMetaExpertForBpmnDataOutput() {
        // Standard.BpmnDataOutput
        // -----------
    }

    @objid ("1b6ffcb7-e27e-4064-925c-67fa0e4d3163")
    protected void registerMetaExpertForBpmnDataObject() {
        // Standard.BpmnDataObject
        // -----------
    }

    @objid ("ba756b18-0e57-4fbd-801d-e6b1894c5958")
    protected void registerMetaExpertForBpmnDataInput() {
        // Standard.BpmnDataInput
        // -----------
    }

    @objid ("b220145c-8869-4dec-ae52-7c426a178d4c")
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

    @objid ("6b920a14-fa15-4138-b3c2-162ea8af9c1e")
    protected void registerMetaExpertForBpmnDataAssociation() {
        // Standard.BpmnDataAssociation
        // -----------
        
        // no constraint on BpmnDataAssociation.SourceRef : BpmnItemAwareElement from Standard.BpmnDataAssociation to Standard.BpmnItemAwareElement
        this.ruleMetaExpert.addDependencyRule(BpmnDataAssociation.class, null, "SourceRef");
        
        // no constraint on BpmnDataAssociation.TargetRef : BpmnItemAwareElement from Standard.BpmnDataAssociation to Standard.BpmnItemAwareElement
        this.ruleMetaExpert.addDependencyRule(BpmnDataAssociation.class, null, "TargetRef");
    }

    @objid ("0f6abd19-3997-477e-8ba1-5ef662a654f8")
    protected void registerMetaExpertForBpmnParallelGateway() {
        // Standard.BpmnParallelGateway
        // -----------
    }

    @objid ("a7d78928-b914-4a22-9586-0b3489327ef3")
    protected void registerMetaExpertForBpmnInclusiveGateway() {
        // Standard.BpmnInclusiveGateway
        // -----------
        
        // no constraint on BpmnInclusiveGateway.DefaultFlow : BpmnSequenceFlow from Standard.BpmnInclusiveGateway to Standard.BpmnSequenceFlow
        this.ruleMetaExpert.addDependencyRule(BpmnInclusiveGateway.class, null, "DefaultFlow");
    }

    @objid ("6ac865cc-b020-4b62-b0bc-d64fcca4c77c")
    protected void registerMetaExpertForBpmnExclusiveGateway() {
        // Standard.BpmnExclusiveGateway
        // -----------
        
        // no constraint on BpmnExclusiveGateway.DefaultFlow : BpmnSequenceFlow from Standard.BpmnExclusiveGateway to Standard.BpmnSequenceFlow
        this.ruleMetaExpert.addDependencyRule(BpmnExclusiveGateway.class, null, "DefaultFlow");
    }

    @objid ("2997d1fa-ebd1-496b-8839-2b843f3851eb")
    protected void registerMetaExpertForBpmnEventBasedGateway() {
        // Standard.BpmnEventBasedGateway
        // -----------
    }

    @objid ("3f872782-beac-4ba4-b95c-f21cf728ef96")
    protected void registerMetaExpertForBpmnComplexGateway() {
        // Standard.BpmnComplexGateway
        // -----------
        
        // no constraint on BpmnComplexGateway.DefaultFlow : BpmnSequenceFlow from Standard.BpmnComplexGateway to Standard.BpmnSequenceFlow
        this.ruleMetaExpert.addDependencyRule(BpmnComplexGateway.class, null, "DefaultFlow");
    }

    @objid ("36d11569-c785-422d-b053-dc608e944ab3")
    protected void registerMetaExpertForBpmnGateway() {
        // Standard.BpmnGateway is abstract
        
        // -----------
    }

    @objid ("3d0521c8-4950-4b5f-8f5a-3f87b554251c")
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

    @objid ("16de1863-3f67-4219-86b1-fe90fef1a475")
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

    @objid ("6cb5adb8-aaf6-47ab-9a52-15b4a9738eb4")
    protected void registerMetaExpertForBpmnMessage() {
        // Standard.BpmnMessage
        // -----------
        
        // no constraint on BpmnMessage.ItemRef : BpmnItemDefinition from Standard.BpmnMessage to Standard.BpmnItemDefinition
        this.ruleMetaExpert.addDependencyRule(BpmnMessage.class, null, "ItemRef");
    }

    @objid ("0d0dd0cc-a0b9-4853-9192-aa868c5b9f7e")
    protected void registerMetaExpertForBpmnTimerEventDefinition() {
        // Standard.BpmnTimerEventDefinition
        // -----------
    }

    @objid ("9b6df302-6a44-4c19-80da-1847035383b4")
    protected void registerMetaExpertForBpmnTerminateEventDefinition() {
        // Standard.BpmnTerminateEventDefinition
        // -----------
    }

    @objid ("d127a290-b2e4-4d1d-869b-bdc848ad0786")
    protected void registerMetaExpertForBpmnStartEvent() {
        // Standard.BpmnStartEvent
        // -----------
    }

    @objid ("3c539a58-e443-4c0d-b5d5-081a93392ec9")
    protected void registerMetaExpertForBpmnSignalEventDefinition() {
        // Standard.BpmnSignalEventDefinition
        // -----------
    }

    @objid ("c7f06733-c67b-4a2d-a5d6-1a21c317b05e")
    protected void registerMetaExpertForBpmnMessageEventDefinition() {
        // Standard.BpmnMessageEventDefinition
        // -----------
        
        // no constraint on BpmnMessageEventDefinition.MessageRef : BpmnMessage from Standard.BpmnMessageEventDefinition to Standard.BpmnMessage
        this.ruleMetaExpert.addDependencyRule(BpmnMessageEventDefinition.class, null, "MessageRef");
        
        // no constraint on BpmnMessageEventDefinition.OperationRef : BpmnOperation from Standard.BpmnMessageEventDefinition to Standard.BpmnOperation
        this.ruleMetaExpert.addDependencyRule(BpmnMessageEventDefinition.class, null, "OperationRef");
    }

    @objid ("0bfcdd2c-6956-4813-9ad6-da2373b0b986")
    protected void registerMetaExpertForBpmnLinkEventDefinition() {
        // Standard.BpmnLinkEventDefinition
        // -----------
        
        // no constraint on BpmnLinkEventDefinition.Target : BpmnLinkEventDefinition from Standard.BpmnLinkEventDefinition to Standard.BpmnLinkEventDefinition
        this.ruleMetaExpert.addDependencyRule(BpmnLinkEventDefinition.class, null, "Target");
    }

    @objid ("40f7981b-87b9-4ac2-b4c3-e4a868ce669e")
    protected void registerMetaExpertForBpmnIntermediateThrowEvent() {
        // Standard.BpmnIntermediateThrowEvent
        // -----------
    }

    @objid ("c5bb8afd-8a6d-4c27-af25-4a6386e6a499")
    protected void registerMetaExpertForBpmnIntermediateCatchEvent() {
        // Standard.BpmnIntermediateCatchEvent
        // -----------
    }

    @objid ("16577ae8-59c4-482c-b7e8-6ff68484999f")
    protected void registerMetaExpertForBpmnImplicitThrowEvent() {
        // Standard.BpmnImplicitThrowEvent
        // -----------
    }

    @objid ("8bde57da-9404-4989-962d-6d5e4b904ab3")
    protected void registerMetaExpertForBpmnEscalationEventDefinition() {
        // Standard.BpmnEscalationEventDefinition
        // -----------
    }

    @objid ("611c16f6-42d3-458f-ba43-7b354341fb32")
    protected void registerMetaExpertForBpmnErrorEventDefinition() {
        // Standard.BpmnErrorEventDefinition
        // -----------
    }

    @objid ("7785ae4c-cef9-471f-9afa-d7b58b1bf413")
    protected void registerMetaExpertForBpmnEndEvent() {
        // Standard.BpmnEndEvent
        // -----------
    }

    @objid ("3c5cb658-8f9c-4bdc-b593-fa2cea0142e5")
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

    @objid ("8fd1a49f-cb4a-4f72-a979-66e534e4864d")
    protected void registerMetaExpertForBpmnConditionalEventDefinition() {
        // Standard.BpmnConditionalEventDefinition
        // -----------
    }

    @objid ("44ce6955-4363-415a-8715-84fb072fd77b")
    protected void registerMetaExpertForBpmnCompensateEventDefinition() {
        // Standard.BpmnCompensateEventDefinition
        // -----------
        
        // no constraint on BpmnCompensateEventDefinition.ActivityRef : BpmnActivity from Standard.BpmnCompensateEventDefinition to Standard.BpmnActivity
        this.ruleMetaExpert.addDependencyRule(BpmnCompensateEventDefinition.class, null, "ActivityRef");
    }

    @objid ("25c8da9f-c6fe-4cb2-98c5-2b7179e27d83")
    protected void registerMetaExpertForBpmnCancelEventDefinition() {
        // Standard.BpmnCancelEventDefinition
        // -----------
    }

    @objid ("b396415f-179e-4804-9c31-55746458b43f")
    protected void registerMetaExpertForBpmnEventDefinition() {
        // Standard.BpmnEventDefinition is abstract
        
        // -----------
    }

    @objid ("56cd30c6-8b25-403a-aa43-a2dfbf5fc33a")
    protected void registerMetaExpertForBpmnBoundaryEvent() {
        // Standard.BpmnBoundaryEvent
        // -----------
    }

    @objid ("0251a5cc-86b4-4a97-9bf7-a31698a27a7d")
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

    @objid ("08081aed-e22b-49da-b29d-c823e668f95c")
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

    @objid ("63d3c002-3d68-4d83-8dd9-38b1e1f34bc1")
    protected void registerMetaExpertForBpmnOperation() {
        // Standard.BpmnOperation
        // -----------
        
        // no constraint on BpmnOperation.InMessageRef : BpmnMessage from Standard.BpmnOperation to Standard.BpmnMessage
        this.ruleMetaExpert.addDependencyRule(BpmnOperation.class, null, "InMessageRef");
        
        // no constraint on BpmnOperation.OutMessageRef : BpmnMessage from Standard.BpmnOperation to Standard.BpmnMessage
        this.ruleMetaExpert.addDependencyRule(BpmnOperation.class, null, "OutMessageRef");
    }

    @objid ("0c1450d6-2b4b-4530-830d-63d22eda9dff")
    protected void registerMetaExpertForBpmnInterface() {
        // Standard.BpmnInterface
        // -----------
        
        // no constraint on BpmnInterface.Operation : BpmnOperation from Standard.BpmnInterface to Standard.BpmnOperation
        this.ruleMetaExpert.addDependencyRule(BpmnInterface.class, null, "Operation");
    }

    @objid ("7549dc4d-39aa-46b5-8db5-20fd71bae462")
    protected void registerMetaExpertForBpmnEndPoint() {
        // Standard.BpmnEndPoint
        // -----------
    }

    @objid ("ce6494f5-5ced-4e30-8704-7342613796f7")
    protected void registerMetaExpertForBpmnSharedElement() {
        // Standard.BpmnSharedElement is abstract
        
        // -----------
    }

    @objid ("573091f9-7c53-41ee-8ab4-5d8b5544e59a")
    protected void registerMetaExpertForBpmnCollaborationDiagram() {
        // Standard.BpmnCollaborationDiagram
        // -----------
    }

    @objid ("c6e5ef4c-6d56-43d2-a498-730dc18f9613")
    protected void registerMetaExpertForBpmnProcessDesignDiagram() {
        // Standard.BpmnProcessDesignDiagram
        // -----------
    }

    @objid ("aee57a35-23e5-48a5-9765-f438a9474925")
    protected void registerMetaExpertForBpmnSubProcessDiagram() {
        // Standard.BpmnSubProcessDiagram
        // -----------
    }

    @objid ("0529df00-b932-47eb-9fce-d908cfe76f24")
    protected void registerMetaExpertForBpmnProcessCollaborationDiagram() {
        // Standard.BpmnProcessCollaborationDiagram is abstract
        
        // -----------
    }

    @objid ("8f390fd5-6335-4c74-aa94-2a80d12d5e9d")
    protected void registerMetaExpertForBehaviorDiagram() {
        // Standard.BehaviorDiagram is abstract
        
        // -----------
    }

    @objid ("192082c2-fdd2-4e57-834b-cef410c21b29")
    protected void registerMetaExpertForBpmnUserTask() {
        // Standard.BpmnUserTask
        // -----------
    }

    @objid ("4b2f5087-9246-4774-bda1-e924fd19d3d2")
    protected void registerMetaExpertForBpmnTransaction() {
        // Standard.BpmnTransaction
        // -----------
    }

    @objid ("1b801dba-52c8-4f53-8055-9a8acf06d111")
    protected void registerMetaExpertForBpmnStandardLoopCharacteristics() {
        // Standard.BpmnStandardLoopCharacteristics
        // -----------
    }

    @objid ("97e2a147-ef40-46b6-8f38-497c451a3c9d")
    protected void registerMetaExpertForBpmnServiceTask() {
        // Standard.BpmnServiceTask
        // -----------
        
        // no constraint on BpmnServiceTask.OperationRef : BpmnOperation from Standard.BpmnServiceTask to Standard.BpmnOperation
        this.ruleMetaExpert.addDependencyRule(BpmnServiceTask.class, null, "OperationRef");
    }

    @objid ("29b80d04-4027-40d4-88cf-9bd78aa1137d")
    protected void registerMetaExpertForBpmnSendTask() {
        // Standard.BpmnSendTask
        // -----------
        
        // no constraint on BpmnSendTask.MessageRef : BpmnMessage from Standard.BpmnSendTask to Standard.BpmnMessage
        this.ruleMetaExpert.addDependencyRule(BpmnSendTask.class, null, "MessageRef");
        
        // no constraint on BpmnSendTask.OperationRef : BpmnOperation from Standard.BpmnSendTask to Standard.BpmnOperation
        this.ruleMetaExpert.addDependencyRule(BpmnSendTask.class, null, "OperationRef");
    }

    @objid ("b6ffaa29-fa6f-4658-8cc5-89d7d75d9d77")
    protected void registerMetaExpertForBpmnScriptTask() {
        // Standard.BpmnScriptTask
        // -----------
    }

    @objid ("c2d147f4-5b8d-43ac-80ee-4da40d457eed")
    protected void registerMetaExpertForBpmnReceiveTask() {
        // Standard.BpmnReceiveTask
        // -----------
        
        // no constraint on BpmnReceiveTask.MessageRef : BpmnMessage from Standard.BpmnReceiveTask to Standard.BpmnMessage
        this.ruleMetaExpert.addDependencyRule(BpmnReceiveTask.class, null, "MessageRef");
        
        // no constraint on BpmnReceiveTask.OperationRef : BpmnOperation from Standard.BpmnReceiveTask to Standard.BpmnOperation
        this.ruleMetaExpert.addDependencyRule(BpmnReceiveTask.class, null, "OperationRef");
    }

    @objid ("ed756ab1-87e4-4a8d-97d4-0e2e428dc732")
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

    @objid ("66fc5669-4111-4efe-8be6-a4e77ab6c5dc")
    protected void registerMetaExpertForBpmnManualTask() {
        // Standard.BpmnManualTask
        // -----------
    }

    @objid ("c719c087-be14-47db-a796-f4fbd0834459")
    protected void registerMetaExpertForBpmnLoopCharacteristics() {
        // Standard.BpmnLoopCharacteristics is abstract
        
        // -----------
    }

    @objid ("9f962994-d2f4-4690-8600-f072c63fb0cf")
    protected void registerMetaExpertForBpmnComplexBehaviorDefinition() {
        // Standard.BpmnComplexBehaviorDefinition
        // -----------
        
        // no constraint on BpmnComplexBehaviorDefinition.Event : BpmnImplicitThrowEvent from Standard.BpmnComplexBehaviorDefinition to Standard.BpmnImplicitThrowEvent
        this.ruleMetaExpert.addDependencyRule(BpmnComplexBehaviorDefinition.class, null, "Event");
    }

    @objid ("99ed121d-746d-4678-bbb0-554dbd63ea03")
    protected void registerMetaExpertForBpmnCallActivity() {
        // Standard.BpmnCallActivity
        // -----------
        
        // no constraint on BpmnCallActivity.CalledGlobalTask : BpmnTask from Standard.BpmnCallActivity to Standard.BpmnTask
        this.ruleMetaExpert.addDependencyRule(BpmnCallActivity.class, null, "CalledGlobalTask");
    }

    @objid ("808ebe33-cc15-492c-a1f9-da566968f619")
    protected void registerMetaExpertForBpmnBusinessRuleTask() {
        // Standard.BpmnBusinessRuleTask
        // -----------
    }

    @objid ("e3359f02-2ed7-47ee-be5f-ca44ff43090c")
    protected void registerMetaExpertForBpmnTask() {
        // Standard.BpmnTask
        // -----------
    }

    @objid ("003494bd-164a-4056-8b28-92b6aa265338")
    protected void registerMetaExpertForBpmnAdHocSubProcess() {
        // Standard.BpmnAdHocSubProcess
        // -----------
    }

    @objid ("34a0513a-3387-4cd9-9606-5a16d1fa77e1")
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

    @objid ("fdd5dcde-e519-4e31-8500-bd655c7e32a2")
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

    @objid ("1e7808f7-ff09-41f0-8004-52fd5755ecdd")
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

    @objid ("bb9bf1ba-6465-42b0-a743-7723a3278aab")
    protected void registerMetaExpertForBpmnFlowElement() {
        // Standard.BpmnFlowElement is abstract
        
        // -----------
    }

    @objid ("c0b84ab1-352d-4c91-8580-40a9883f7cc5")
    protected void registerMetaExpertForBpmnBaseElement() {
        // Standard.BpmnBaseElement is abstract
        
        // -----------
    }

}
