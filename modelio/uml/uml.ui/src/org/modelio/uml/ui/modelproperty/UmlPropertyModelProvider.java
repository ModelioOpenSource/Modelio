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

package org.modelio.uml.ui.modelproperty;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.nattable.viewer.model.INatTableViewerContext;
import org.modelio.core.ui.nattable.viewer.model.IPropertyModel;
import org.modelio.core.ui.nattable.viewer.model.IPropertyModelProvider;
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
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnProcessCollaborationDiagram;
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
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowNode;
import org.modelio.metamodel.bpmn.rootElements.BpmnGroup;
import org.modelio.metamodel.bpmn.rootElements.BpmnSharedDefinitions;
import org.modelio.metamodel.bpmn.rootElements.BpmnSharedElement;
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
import org.modelio.metamodel.mda.Project;
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
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionUse;
import org.modelio.metamodel.uml.behavior.interactionModel.Lifeline;
import org.modelio.metamodel.uml.behavior.interactionModel.Message;
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
import org.modelio.metamodel.uml.statik.Generalization;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.InterfaceRealization;
import org.modelio.metamodel.uml.statik.Link;
import org.modelio.metamodel.uml.statik.LinkEnd;
import org.modelio.metamodel.uml.statik.Manifestation;
import org.modelio.metamodel.uml.statik.NaryAssociation;
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
import org.modelio.metamodel.visitors.DefaultModelVisitor;
import org.modelio.uml.ui.modelproperty.bpmn.BpmnActivityPropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.BpmnAdHocSubProcessPropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.BpmnArtifactPropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.BpmnBoundaryEventPropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.BpmnBusinessRuleTaskPropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.BpmnCallActivityPropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.BpmnCatchEventPropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.BpmnCollaborationPropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.BpmnComplexBehaviorDefinitionPropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.BpmnComplexGatewayPropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.BpmnDataAssociationPropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.BpmnDataInputPropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.BpmnDataObjectPropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.BpmnDataOutputPropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.BpmnDataStatePropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.BpmnDataStorePropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.BpmnEndEventPropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.BpmnEndPointPropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.BpmnEventBasedGatewayPropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.BpmnEventDefinitionPropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.BpmnEventPropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.BpmnExclusiveGatewayPropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.BpmnFlowElementPropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.BpmnFlowNodePropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.BpmnGatewayPropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.BpmnGroupPropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.BpmnInclusiveGatewayPropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.BpmnInterfacePropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.BpmnIntermediateCatchEventPropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.BpmnIntermediateThrowEventPropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.BpmnItemAwareElementPropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.BpmnItemDefinitionPropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.BpmnLanePropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.BpmnLaneSetPropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.BpmnLoopCharacteristicsPropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.BpmnManualTaskPropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.BpmnMessageFlowPropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.BpmnMessagePropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.BpmnMultiInstanceLoopCharacteristicsPropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.BpmnOperationPropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.BpmnParallelGatewayPropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.BpmnParticipantPropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.BpmnProcessCollaborationDiagramPropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.BpmnProcessPropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.BpmnReceiveTaskPropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.BpmnResourceParameterBindingPropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.BpmnResourceParameterPropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.BpmnResourcePropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.BpmnResourceRolePropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.BpmnScriptTaskPropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.BpmnSendTaskPropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.BpmnSequenceFlowDataAssociationPropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.BpmnSequenceFlowPropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.BpmnServiceTaskPropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.BpmnSharedDefinitionsPropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.BpmnSharedElementPropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.BpmnStandardLoopCharacteristicsPropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.BpmnStartEventPropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.BpmnSubProcessDiagramPropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.BpmnSubProcessPropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.BpmnTaskPropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.BpmnThrowEventPropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.BpmnTransactionPropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.BpmnUserTaskPropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.delegated.BpmnCancelEventDefinitionPropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.delegated.BpmnCompensateEventDefinitionPropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.delegated.BpmnConditionalEventDefinitionPropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.delegated.BpmnErrorEventDefinitionPropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.delegated.BpmnEscalationEventDefinitionPropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.delegated.BpmnLinkEventDefinitionPropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.delegated.BpmnMessageEventDefinitionPropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.delegated.BpmnSignalEventDefinitionPropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.delegated.BpmnTerminateEventDefinitionPropertyModel;
import org.modelio.uml.ui.modelproperty.bpmn.delegated.BpmnTimerEventDefinitionPropertyModel;
import org.modelio.uml.ui.modelproperty.uml.*;
import org.modelio.uml.ui.modelproperty.uml.templateparameter.TemplateParameterPropertyModel;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Default property model provider with Uml elements for the property view.
 */
@objid ("cf4c5926-08f7-49c3-a928-b3f97c46d65f")
public class UmlPropertyModelProvider implements IPropertyModelProvider {
    @objid ("ff790497-7c30-40a6-8158-caf9e6bdf2c6")
    @Override
    public IPropertyModel<?> getPropertyModel(MObject element, INatTableViewerContext context) {
        return (IPropertyModel<?>) element.accept(new UmlPropertyModelVisitor(context));
    }

    @objid ("0e7a813a-ac01-4e2e-9a41-46bff9572bf1")
    public static class UmlPropertyModelVisitor extends DefaultModelVisitor {
        @objid ("84ca4d45-3ae3-42af-b17c-def86924e478")
        private INatTableViewerContext context;

        /**
         * C'tor
         */
        @objid ("10a9f218-9a14-4e4b-b528-22adb1e4022a")
        public UmlPropertyModelVisitor(INatTableViewerContext context) {
            this.context = context;
        }

        @objid ("0c73d558-90b1-481b-a4ac-a8090ddcc45c")
        @Override
        public Object visitAbstraction(Abstraction theAbstraction) {
            return new AbstractionPropertyModel(theAbstraction);
        }

        @objid ("efd3dc41-ff49-4f46-8f6e-2499bb9fd971")
        @Override
        public Object visitAcceptCallEventAction(AcceptCallEventAction theAcceptCallEventAction) {
            return new AcceptCallEventActionPropertyModel(theAcceptCallEventAction);
        }

        @objid ("34f193aa-78b5-487e-a0a7-cc15d7b84774")
        @Override
        public Object visitAcceptChangeEventAction(AcceptChangeEventAction theAcceptChangeEventAction) {
            return new AcceptChangeEventActionPropertyModel(theAcceptChangeEventAction);
        }

        @objid ("93e2eb00-a944-4842-92d1-ca978b8e53f7")
        @Override
        public Object visitAcceptSignalAction(AcceptSignalAction theAcceptSignalAction) {
            return new AcceptSignalActionPropertyModel(theAcceptSignalAction);
        }

        @objid ("993bf3df-653c-411c-9571-6084be4103ad")
        @Override
        public Object visitAcceptTimeEventAction(AcceptTimeEventAction theAcceptTimeEventAction) {
            return new AcceptTimeEventActionPropertyModel(theAcceptTimeEventAction);
        }

        @objid ("2468de10-3b0f-4d65-ad85-d2dfdb64f931")
        @Override
        public Object visitActivity(Activity theActivity) {
            return new ActivityPropertyModel(theActivity);
        }

        @objid ("f202427b-b3cb-49b1-9a5b-99020a4b54ea")
        @Override
        public Object visitActivityDiagram(ActivityDiagram theActivityDiagram) {
            return new ActivityDiagramPropertyModel(theActivityDiagram);
        }

        @objid ("606778aa-9acc-4cb2-b777-7142b213896f")
        @Override
        public Object visitActivityFinalNode(ActivityFinalNode theActivityFinalNode) {
            return new ActivityFinalNodePropertyModel(theActivityFinalNode);
        }

        @objid ("04156172-b1a9-417e-8bb8-e5d784c4f236")
        @Override
        public Object visitActivityParameterNode(ActivityParameterNode theActivityParameterNode) {
            return new ActivityParameterNodePropertyModel(theActivityParameterNode);
        }

        @objid ("b22bad33-36d4-4466-96b3-75924fedba9c")
        @Override
        public Object visitActivityPartition(ActivityPartition theActivityPartition) {
            return new ActivityPartitionPropertyModel(theActivityPartition);
        }

        @objid ("00af284d-4fc0-447f-a330-74a9c0d9a4f0")
        @Override
        public Object visitActor(Actor theActor) {
            return new ActorPropertyModel(theActor);
        }

        @objid ("1679da6b-5f69-4112-a7be-73916903ed04")
        @Override
        public Object visitArtifact(Artifact theArtifact) {
            return new ArtifactPropertyModel(theArtifact);
        }

        @objid ("46bae7bb-caf4-4efe-b9b6-8c18be9e7609")
        @Override
        public Object visitAssociation(Association theAssociation) {
            return new AssociationPropertyModel(theAssociation);
        }

        @objid ("09905125-2c61-4e41-8bed-90c4d93a62b5")
        @Override
        public Object visitAssociationEnd(AssociationEnd theAssociationEnd) {
            return new AssociationEnd2PropertyModel(theAssociationEnd);
        }

        @objid ("d9a5e1b7-9544-4911-8d70-faf22441aa11")
        @Override
        public Object visitAttribute(Attribute theAttribute) {
            return new AttributePropertyModel(theAttribute);
        }

        @objid ("bb398f45-dc1c-46af-b53e-508298bab4ce")
        @Override
        public Object visitAttributeLink(AttributeLink theAttributeLink) {
            return new AttributeLinkPropertyModel(theAttributeLink);
        }

        @objid ("660be41f-269a-4a60-b335-0a67db0e61ac")
        @Override
        public Object visitBehaviorParameter(BehaviorParameter theBehaviorParameter) {
            return new BehaviorParameterPropertyModel(theBehaviorParameter);
        }

        @objid ("6a2c6f26-7632-412e-9a99-54bf73aadd3e")
        @Override
        public Object visitBindableInstance(BindableInstance theBindableInstance) {
            return new BindableInstancePropertyModel(theBindableInstance);
        }

        @objid ("c39b6d2c-9b6a-481f-9210-22fa10b7f941")
        @Override
        public Object visitBinding(Binding theBinding) {
            return new BindingPropertyModel(theBinding);
        }

        @objid ("b4742ee2-b6d2-432a-9aca-47b8efc84df3")
        @Override
        public Object visitBpmnActivity(BpmnActivity theBpmnActivity) {
            return new BpmnActivityPropertyModel(theBpmnActivity);
        }

        @objid ("29deb6de-0e01-4fd4-8294-95b90d526f4c")
        @Override
        public Object visitBpmnAdHocSubProcess(BpmnAdHocSubProcess theBpmnAdHocSubProcess) {
            return new BpmnAdHocSubProcessPropertyModel(theBpmnAdHocSubProcess, this.context.getModelService());
        }

        @objid ("84cc5d14-1e50-48cb-b51b-e1caa588b484")
        @Override
        public Object visitBpmnArtifact(BpmnArtifact theBpmnArtifact) {
            return new BpmnArtifactPropertyModel(theBpmnArtifact);
        }

        @objid ("34b0f871-e50f-41d8-bb1d-28781dd14427")
        @Override
        public Object visitBpmnSharedDefinitions(BpmnSharedDefinitions theBpmnSharedDefinitions) {
            return new BpmnSharedDefinitionsPropertyModel(theBpmnSharedDefinitions);
        }

        @objid ("6e37ebef-7ce7-441e-99d1-34090cf84275")
        @Override
        public Object visitBpmnBoundaryEvent(BpmnBoundaryEvent theBpmnBoundaryEvent) {
            return new BpmnBoundaryEventPropertyModel(theBpmnBoundaryEvent, this.context.getModelService(), this);
        }

        @objid ("edf3f958-1068-49fd-9818-1f0e9ac58af9")
        @Override
        public Object visitBpmnBusinessRuleTask(BpmnBusinessRuleTask theBpmnBusinessRuleTask) {
            return new BpmnBusinessRuleTaskPropertyModel(theBpmnBusinessRuleTask, this.context.getModelService());
        }

        @objid ("c7b2a75b-bd20-4b23-86cc-27ed75e35431")
        @Override
        public Object visitBpmnCallActivity(BpmnCallActivity theBpmnCallActivity) {
            return new BpmnCallActivityPropertyModel(theBpmnCallActivity, this.context.getModelService(), this.context.getMdaExpert());
        }

        @objid ("52b8859f-dbb7-4d9a-92f5-a8c125a576dd")
        @Override
        public Object visitBpmnCancelEventDefinition(BpmnCancelEventDefinition theBpmnCancelEventDefinition) {
            return new BpmnCancelEventDefinitionPropertyModel(theBpmnCancelEventDefinition);
        }

        @objid ("ca461336-db46-4e7a-9145-855667969f24")
        @Override
        public Object visitBpmnCatchEvent(BpmnCatchEvent theBpmnCatchEvent) {
            return new BpmnCatchEventPropertyModel(theBpmnCatchEvent);
        }

        @objid ("79dc308d-5d8f-4985-ab1f-117bfadcacf1")
        @Override
        public Object visitBpmnCollaboration(BpmnCollaboration theBpmnCollaboration) {
            return new BpmnCollaborationPropertyModel(theBpmnCollaboration);
        }

        @objid ("315889fb-2646-46f2-94c0-2df6fbeef30a")
        @Override
        public Object visitBpmnCompensateEventDefinition(BpmnCompensateEventDefinition theBpmnCompensateEventDefinition) {
            return new BpmnCompensateEventDefinitionPropertyModel(theBpmnCompensateEventDefinition);
        }

        @objid ("eb7bd4d4-e9c1-4708-80cd-3cb64c5cb644")
        @Override
        public Object visitBpmnComplexBehaviorDefinition(BpmnComplexBehaviorDefinition theBpmnComplexBehaviorDefinition) {
            return new BpmnComplexBehaviorDefinitionPropertyModel(theBpmnComplexBehaviorDefinition);
        }

        @objid ("1f99a490-62d3-4961-b8b0-6f05f70018b9")
        @Override
        public Object visitBpmnComplexGateway(BpmnComplexGateway theBpmnComplexGateway) {
            return new BpmnComplexGatewayPropertyModel(theBpmnComplexGateway);
        }

        @objid ("b5be011d-9576-446e-8919-098bc10740e6")
        @Override
        public Object visitBpmnConditionalEventDefinition(BpmnConditionalEventDefinition theBpmnConditionalEventDefinition) {
            return new BpmnConditionalEventDefinitionPropertyModel(theBpmnConditionalEventDefinition);
        }

        @objid ("c2942875-b249-4899-b96b-8bd7afe1aee1")
        @Override
        public Object visitBpmnDataAssociation(BpmnDataAssociation theBpmnDataAssociation) {
            return new BpmnDataAssociationPropertyModel(theBpmnDataAssociation);
        }

        @objid ("ff0bd9bb-5920-4b87-82c3-0d9fc57d92b0")
        @Override
        public Object visitBpmnDataInput(BpmnDataInput theBpmnDataInput) {
            return new BpmnDataInputPropertyModel(theBpmnDataInput, this.context.getMdaExpert());
        }

        @objid ("44b32e92-2621-4ded-9e48-5a93946e9c84")
        @Override
        public Object visitBpmnDataObject(BpmnDataObject theBpmnDataObject) {
            return new BpmnDataObjectPropertyModel(theBpmnDataObject, this.context.getMdaExpert());
        }

        @objid ("eed11dd8-562b-4e37-a058-c1893271ec04")
        @Override
        public Object visitBpmnDataOutput(BpmnDataOutput theBpmnDataOutput) {
            return new BpmnDataOutputPropertyModel(theBpmnDataOutput, this.context.getMdaExpert());
        }

        @objid ("72fe01b5-2532-4964-aac0-6632fc1fb41b")
        @Override
        public Object visitBpmnDataState(BpmnDataState theBpmnDataState) {
            return new BpmnDataStatePropertyModel(theBpmnDataState, this.context.getMdaExpert());
        }

        @objid ("65579630-d6b9-402d-9c2a-324e6c1efd79")
        @Override
        public Object visitBpmnDataStore(BpmnDataStore theBpmnDataStore) {
            return new BpmnDataStorePropertyModel(theBpmnDataStore, this.context.getMdaExpert());
        }

        @objid ("ab353d2e-d2da-4932-ad39-d3cb5a644099")
        @Override
        public Object visitBpmnEndEvent(BpmnEndEvent theBpmnEndEvent) {
            return new BpmnEndEventPropertyModel(theBpmnEndEvent, this.context.getModelService(), this);
        }

        @objid ("a94d176e-62ad-4af3-8843-cba55d293b7b")
        @Override
        public Object visitBpmnEndPoint(BpmnEndPoint theBpmnEndPoint) {
            return new BpmnEndPointPropertyModel(theBpmnEndPoint);
        }

        @objid ("d247b5c7-4e87-46f3-bcd2-0ac23dfbe04f")
        @Override
        public Object visitBpmnErrorEventDefinition(BpmnErrorEventDefinition theBpmnErrorEventDefinition) {
            return new BpmnErrorEventDefinitionPropertyModel(theBpmnErrorEventDefinition);
        }

        @objid ("fce21cdd-d079-45ec-b1ee-4a26ad415293")
        @Override
        public Object visitBpmnEscalationEventDefinition(BpmnEscalationEventDefinition theBpmnEscalationEventDefinition) {
            return new BpmnEscalationEventDefinitionPropertyModel(theBpmnEscalationEventDefinition);
        }

        @objid ("6b0546dd-ed22-4699-82e7-d1cdad77cbce")
        @Override
        public Object visitBpmnEvent(BpmnEvent theBpmnEvent) {
            return new BpmnEventPropertyModel(theBpmnEvent);
        }

        @objid ("c35204fc-dd8c-4845-bcf8-2d28aea5b8e3")
        @Override
        public Object visitBpmnEventBasedGateway(BpmnEventBasedGateway theBpmnEventBasedGateway) {
            return new BpmnEventBasedGatewayPropertyModel(theBpmnEventBasedGateway);
        }

        @objid ("21b0e10b-16ed-4437-9f67-aebc01fc8ade")
        @Override
        public Object visitBpmnEventDefinition(BpmnEventDefinition theBpmnEventDefinition) {
            return new BpmnEventDefinitionPropertyModel(theBpmnEventDefinition);
        }

        @objid ("0ea372da-5985-4f2f-94b4-177600a4ede8")
        @Override
        public Object visitBpmnExclusiveGateway(BpmnExclusiveGateway theBpmnExclusiveGateway) {
            return new BpmnExclusiveGatewayPropertyModel(theBpmnExclusiveGateway);
        }

        @objid ("17807605-365b-40f9-9abd-e1fc5140934a")
        @Override
        public Object visitBpmnFlowElement(BpmnFlowElement theBpmnFlowElement) {
            return new BpmnFlowElementPropertyModel(theBpmnFlowElement);
        }

        @objid ("a9761ff3-3f67-4349-a505-493b2eb4fc3a")
        @Override
        public Object visitBpmnFlowNode(BpmnFlowNode theBpmnFlowNode) {
            return new BpmnFlowNodePropertyModel(theBpmnFlowNode);
        }

        @objid ("f4c0db2f-a677-4e6d-9839-9775006ef611")
        @Override
        public Object visitBpmnGateway(BpmnGateway theBpmnGateway) {
            return new BpmnGatewayPropertyModel(theBpmnGateway);
        }

        @objid ("702ac17b-2d53-4830-bfa5-ce9e82579ca7")
        @Override
        public Object visitBpmnGroup(BpmnGroup theBpmnGroup) {
            return new BpmnGroupPropertyModel(theBpmnGroup);
        }

        @objid ("4b380401-0964-4ca2-bb58-52eb829056e5")
        @Override
        public Object visitBpmnInclusiveGateway(BpmnInclusiveGateway theBpmnInclusiveGateway) {
            return new BpmnInclusiveGatewayPropertyModel(theBpmnInclusiveGateway);
        }

        @objid ("a9112ce2-56e0-43ff-838c-1f8fbcacf064")
        @Override
        public Object visitBpmnInterface(BpmnInterface theBpmnInterface) {
            return new BpmnInterfacePropertyModel(theBpmnInterface, this.context.getMdaExpert());
        }

        @objid ("1d0fdc91-a217-44d4-92ac-945238fb4722")
        @Override
        public Object visitBpmnIntermediateCatchEvent(BpmnIntermediateCatchEvent theBpmnDataObject) {
            return new BpmnIntermediateCatchEventPropertyModel(theBpmnDataObject, this.context.getModelService(), this);
        }

        @objid ("865d26c0-eac9-4932-bdc8-093cee377071")
        @Override
        public Object visitBpmnIntermediateThrowEvent(BpmnIntermediateThrowEvent theBpmnIntermediateThrowEvent) {
            return new BpmnIntermediateThrowEventPropertyModel(theBpmnIntermediateThrowEvent, this.context.getModelService(), this);
        }

        @objid ("68e41cd3-c2ee-4389-b521-99cb5c3a6e63")
        @Override
        public Object visitBpmnItemAwareElement(BpmnItemAwareElement theBpmnItemAwareElement) {
            return new BpmnItemAwareElementPropertyModel(theBpmnItemAwareElement);
        }

        @objid ("e1ac52e6-1872-47ae-8a36-c5282b0f8260")
        @Override
        public Object visitBpmnItemDefinition(BpmnItemDefinition theBpmnItemDefinition) {
            return new BpmnItemDefinitionPropertyModel(theBpmnItemDefinition, this.context.getMdaExpert());
        }

        @objid ("ad178557-3e3d-4166-bd8f-a6ecf7ae2f5b")
        @Override
        public Object visitBpmnLane(BpmnLane theBpmnLane) {
            return new BpmnLanePropertyModel(theBpmnLane, this.context.getMdaExpert());
        }

        @objid ("a7db2625-d90b-4beb-8746-148c96c16b47")
        @Override
        public Object visitBpmnLaneSet(BpmnLaneSet theBpmnLaneSet) {
            return new BpmnLaneSetPropertyModel(theBpmnLaneSet);
        }

        @objid ("2258656a-0994-4a18-aa5e-3330c8f34681")
        @Override
        public Object visitBpmnLinkEventDefinition(BpmnLinkEventDefinition theBpmnLinkEventDefinition) {
            return new BpmnLinkEventDefinitionPropertyModel(theBpmnLinkEventDefinition);
        }

        @objid ("0e948a6d-d6f4-4052-aea0-e85c7237707a")
        @Override
        public Object visitBpmnLoopCharacteristics(BpmnLoopCharacteristics theBpmnLoopCharacteristics) {
            return new BpmnLoopCharacteristicsPropertyModel(theBpmnLoopCharacteristics);
        }

        @objid ("bbe6efed-f401-44dc-9f81-c89e6f4b10f2")
        @Override
        public Object visitBpmnManualTask(BpmnManualTask theBpmnManualTask) {
            return new BpmnManualTaskPropertyModel(theBpmnManualTask, this.context.getModelService());
        }

        @objid ("62d247b3-4d04-4302-a523-c99a8ecd1ef6")
        @Override
        public Object visitBpmnMessage(BpmnMessage theBpmnMessage) {
            return new BpmnMessagePropertyModel(theBpmnMessage, this.context.getMdaExpert());
        }

        @objid ("8da57845-83ac-4d97-91e2-1cf1b2c165b5")
        @Override
        public Object visitBpmnMessageEventDefinition(BpmnMessageEventDefinition theBpmnMessageEventDefinition) {
            return new BpmnMessageEventDefinitionPropertyModel(theBpmnMessageEventDefinition);
        }

        @objid ("c65b97c6-3b8c-48d9-9543-84052ec460f8")
        @Override
        public Object visitBpmnMessageFlow(BpmnMessageFlow theBpmnMessageFlow) {
            return new BpmnMessageFlowPropertyModel(theBpmnMessageFlow);
        }

        @objid ("eaacc0b0-2c6d-4b47-9e64-9f8ea9f11724")
        @Override
        public Object visitBpmnMultiInstanceLoopCharacteristics(BpmnMultiInstanceLoopCharacteristics theBpmnMultiInstanceLoopCharacteristics) {
            return new BpmnMultiInstanceLoopCharacteristicsPropertyModel(theBpmnMultiInstanceLoopCharacteristics);
        }

        @objid ("745dd7eb-bb10-4d29-bb78-ecd60827beb6")
        @Override
        public Object visitBpmnOperation(BpmnOperation theBpmnOperation) {
            return new BpmnOperationPropertyModel(theBpmnOperation, this.context.getMdaExpert());
        }

        @objid ("9d63b787-e4e4-49d3-8c31-be046056079a")
        @Override
        public Object visitBpmnParallelGateway(BpmnParallelGateway theBpmnParallelGateway) {
            return new BpmnParallelGatewayPropertyModel(theBpmnParallelGateway);
        }

        @objid ("f01ffd7b-9245-4a09-ad1d-bf09d1682175")
        @Override
        public Object visitBpmnParticipant(BpmnParticipant theBpmnParticipant) {
            return new BpmnParticipantPropertyModel(theBpmnParticipant, this.context.getMdaExpert());
        }

        @objid ("f61a883c-e810-4b77-bcae-d10462b4d69c")
        @Override
        public Object visitBpmnProcess(BpmnProcess theBpmnProcess) {
            return new BpmnProcessPropertyModel(theBpmnProcess, this.context.getMdaExpert());
        }

        @objid ("5bdbd917-f829-4980-b51f-612506b02322")
        @Override
        public Object visitBpmnProcessCollaborationDiagram(BpmnProcessCollaborationDiagram theBpmnProcessCollaborationDiagram) {
            return new BpmnProcessCollaborationDiagramPropertyModel(theBpmnProcessCollaborationDiagram);
        }

        @objid ("713af400-e76f-4a94-b0f9-060ed39b8688")
        @Override
        public Object visitBpmnReceiveTask(BpmnReceiveTask theBpmnReceiveTask) {
            return new BpmnReceiveTaskPropertyModel(theBpmnReceiveTask, this.context.getModelService(), this.context.getMdaExpert());
        }

        @objid ("f843555d-aca6-4f9f-b4a6-fad267d85d99")
        @Override
        public Object visitBpmnResource(BpmnResource theBpmnResource) {
            return new BpmnResourcePropertyModel(theBpmnResource);
        }

        @objid ("1fad184a-1a7b-423b-af78-39a48cb32871")
        @Override
        public Object visitBpmnResourceParameter(BpmnResourceParameter theBpmnResourceParameter) {
            return new BpmnResourceParameterPropertyModel(theBpmnResourceParameter);
        }

        @objid ("358d823c-d06e-43f4-8784-3d3ab689f35f")
        @Override
        public Object visitBpmnResourceParameterBinding(BpmnResourceParameterBinding theBpmnResourceParameterBinding) {
            return new BpmnResourceParameterBindingPropertyModel(theBpmnResourceParameterBinding);
        }

        @objid ("3a155406-28f3-4787-8ca9-150f3b927de5")
        @Override
        public Object visitBpmnResourceRole(BpmnResourceRole theBpmnResourceRole) {
            return new BpmnResourceRolePropertyModel(theBpmnResourceRole);
        }

        @objid ("ff399432-566c-4ba1-a618-1ab19c8af94f")
        @Override
        public Object visitBpmnSharedElement(BpmnSharedElement theBpmnSharedElement) {
            return new BpmnSharedElementPropertyModel(theBpmnSharedElement);
        }

        @objid ("2f930178-80d4-408d-9912-9cad73133e3e")
        @Override
        public Object visitBpmnScriptTask(BpmnScriptTask theBpmnScriptTask) {
            return new BpmnScriptTaskPropertyModel(theBpmnScriptTask, this.context.getModelService());
        }

        @objid ("3c6018be-83f7-444d-9624-ce57d809a313")
        @Override
        public Object visitBpmnSendTask(BpmnSendTask theBpmnSendTask) {
            return new BpmnSendTaskPropertyModel(theBpmnSendTask, this.context.getModelService(), this.context.getMdaExpert());
        }

        @objid ("a1280f62-0289-461b-a529-ae49672e93d3")
        @Override
        public Object visitBpmnSequenceFlow(BpmnSequenceFlow theBpmnSequenceFlow) {
            return new BpmnSequenceFlowPropertyModel(theBpmnSequenceFlow, this.context.getModelService());
        }

        @objid ("4824d7bb-dafb-4602-8c25-08d8c1f5cfeb")
        @Override
        public Object visitBpmnSequenceFlowDataAssociation(BpmnSequenceFlowDataAssociation theBpmnSequenceFlowDataAssociation) {
            return new BpmnSequenceFlowDataAssociationPropertyModel(theBpmnSequenceFlowDataAssociation);
        }

        @objid ("4ccf792f-5e89-426d-b19c-f509700617e4")
        @Override
        public Object visitBpmnServiceTask(BpmnServiceTask theBpmnServiceTask) {
            return new BpmnServiceTaskPropertyModel(theBpmnServiceTask, this.context.getModelService(), this.context.getMdaExpert());
        }

        @objid ("ca87c57b-b4b6-46c5-8804-38fa9f002113")
        @Override
        public Object visitBpmnSignalEventDefinition(BpmnSignalEventDefinition theBpmnSignalEventDefinition) {
            return new BpmnSignalEventDefinitionPropertyModel(theBpmnSignalEventDefinition);
        }

        @objid ("fd4db0c4-b3bc-4935-b881-88483d6ca558")
        @Override
        public Object visitBpmnStandardLoopCharacteristics(final BpmnStandardLoopCharacteristics theBpmnTerminateEventDefinition) {
            return new BpmnStandardLoopCharacteristicsPropertyModel(theBpmnTerminateEventDefinition);
        }

        @objid ("283613cd-0a9a-4452-bf64-f384b683af5a")
        @Override
        public Object visitBpmnStartEvent(BpmnStartEvent theBpmnStartEvent) {
            return new BpmnStartEventPropertyModel(theBpmnStartEvent, this.context.getModelService(), this);
        }

        @objid ("1819bf96-29cd-4b5a-953e-3d3d8ed3ad06")
        @Override
        public Object visitBpmnSubProcess(BpmnSubProcess theBpmnSubProcess) {
            return new BpmnSubProcessPropertyModel(theBpmnSubProcess, this.context.getModelService());
        }

        @objid ("b0633769-eba9-4791-8761-4bd5a42b0914")
        @Override
        public Object visitBpmnSubProcessDiagram(BpmnSubProcessDiagram theBpmnSubProcessDiagram) {
            return new BpmnSubProcessDiagramPropertyModel(theBpmnSubProcessDiagram);
        }

        @objid ("5416d295-3912-4fb2-a75d-74e870c859c0")
        @Override
        public Object visitBpmnTask(BpmnTask theBpmnDataObject) {
            return new BpmnTaskPropertyModel(theBpmnDataObject, this.context.getModelService());
        }

        @objid ("d95a771b-08bb-47b6-b3a1-308b52a522e3")
        @Override
        public Object visitBpmnTerminateEventDefinition(BpmnTerminateEventDefinition theBpmnTerminateEventDefinition) {
            return new BpmnTerminateEventDefinitionPropertyModel(theBpmnTerminateEventDefinition);
        }

        @objid ("fd392da5-19ba-4503-8635-82cba06219d1")
        @Override
        public Object visitBpmnThrowEvent(BpmnThrowEvent theBpmnThrowEvent) {
            return new BpmnThrowEventPropertyModel(theBpmnThrowEvent);
        }

        @objid ("fc375d68-bffb-456f-87c1-c2ddc784ee09")
        @Override
        public Object visitBpmnTimerEventDefinition(BpmnTimerEventDefinition theBpmnTimerEventDefinition) {
            return new BpmnTimerEventDefinitionPropertyModel(theBpmnTimerEventDefinition);
        }

        @objid ("8896977c-d87f-44d1-b78b-8b735c6f9422")
        @Override
        public Object visitBpmnTransaction(BpmnTransaction theBpmnTransaction) {
            return new BpmnTransactionPropertyModel(theBpmnTransaction, this.context.getModelService());
        }

        @objid ("050b2350-1a06-45b9-a639-a9cfef6ab4f8")
        @Override
        public Object visitBpmnUserTask(BpmnUserTask theBpmnUserTask) {
            return new BpmnUserTaskPropertyModel(theBpmnUserTask, this.context.getModelService());
        }

        @objid ("f70cc3e0-7ae5-4482-828c-faecbf7a8785")
        @Override
        public Object visitCallBehaviorAction(CallBehaviorAction theCallBehaviorAction) {
            return new CallBehaviorActionPropertyModel(theCallBehaviorAction);
        }

        @objid ("96b056a0-935a-4fa9-82de-b05c407e9004")
        @Override
        public Object visitCallOperationAction(CallOperationAction theCallOperationAction) {
            return new CallOperationActionPropertyModel(theCallOperationAction);
        }

        @objid ("2564c992-7ea5-4bfe-be7c-02700262c57c")
        @Override
        public Object visitCentralBufferNode(CentralBufferNode theCentralBufferNode) {
            return new CentralBufferNodePropertyModel(theCentralBufferNode);
        }

        @objid ("ff99991a-27ab-42c4-b507-7a326dd70a62")
        @Override
        public Object visitChoicePseudoState(ChoicePseudoState theChoicePseudoState) {
            return new ChoicePseudoStatePropertyModel(theChoicePseudoState);
        }

        @objid ("e89f96eb-14fc-4066-9932-3a4cfa5f6f6d")
        @Override
        public Object visitClass(Class theClass) {
            return new ClassPropertyModel(theClass);
        }

        @objid ("6f2cd6e3-46e7-44c2-9ee8-eca77b00d578")
        @Override
        public Object visitClassAssociation(ClassAssociation theClassAssociation) {
            return new ClassAssociationPropertyModel(theClassAssociation);
        }

        @objid ("88913a76-95eb-488a-8c5e-0553f377d9c5")
        @Override
        public Object visitClassDiagram(final ClassDiagram theClassDiagram) {
            return new ClassDiagramPropertyModel(theClassDiagram);
        }

        @objid ("2d6cc1b3-8c68-422d-a6be-b7ad780d5c4b")
        @Override
        public Object visitClause(Clause theClause) {
            return new ClausePropertyModel(theClause);
        }

        @objid ("bed61923-71b4-4eb9-8f4c-c6e9b0e107f0")
        @Override
        public Object visitCollaboration(Collaboration theCollaboration) {
            return new CollaborationPropertyModel(theCollaboration);
        }

        @objid ("f9a1fd6d-e50a-45f5-a9fd-4bad8150eea9")
        @Override
        public Object visitCollaborationUse(CollaborationUse theCollaborationUse) {
            return new CollaborationUsePropertyModel(theCollaborationUse);
        }

        @objid ("ad7a4c04-e6b6-4912-a9e0-9be04b830268")
        @Override
        public Object visitCombinedFragment(CombinedFragment theCombinedFragment) {
            return new CombinedFragmentPropertyModel(theCombinedFragment);
        }

        @objid ("5af801ab-ebb7-4225-b317-568ab4b9984b")
        @Override
        public Object visitCommunicationChannel(CommunicationChannel theCommunicationChannel) {
            return new CommunicationChannelPropertyModel(theCommunicationChannel);
        }

        @objid ("a9520bf1-f616-471e-afcd-22d1950d1c92")
        @Override
        public Object visitCommunicationDiagram(final CommunicationDiagram theCommunicationDiagram) {
            return new CommunicationDiagramPropertyModel(theCommunicationDiagram);
        }

        @objid ("14f6e6ee-f034-4ab1-a254-7dabf03b3e14")
        @Override
        public Object visitCommunicationInteraction(CommunicationInteraction theCommunicationInteraction) {
            return new CommunicationInteractionPropertyModel(theCommunicationInteraction);
        }

        @objid ("176f9de0-634a-4ad1-bb01-7cf4fea7fd5b")
        @Override
        public Object visitCommunicationMessage(CommunicationMessage theCommunicationMessage) {
            return new CommunicationMessagePropertyModel(theCommunicationMessage);
        }

        @objid ("79a56f3f-88b5-465a-aa35-da66cc93c38b")
        @Override
        public Object visitCommunicationNode(CommunicationNode theCommunicationNode) {
            return new CommunicationNodePropertyModel(theCommunicationNode);
        }

        @objid ("e49ab2b6-f868-47dc-9ee0-cb252d0aa6e7")
        @Override
        public Object visitComponent(Component theComponent) {
            return new ComponentPropertyModel(theComponent);
        }

        @objid ("713cba7e-b08b-4f37-adcb-b6336a9bc3c6")
        @Override
        public Object visitComponentRealization(ComponentRealization obj) {
            return new ComponentRealizationPropertyModel(obj);
        }

        @objid ("4eec53e3-d566-444d-a059-3eab0ca80ddd")
        @Override
        public Object visitCompositeStructureDiagram(final CompositeStructureDiagram theCompositeStructureDiagram) {
            return new CompositeStructureDiagramPropertyModel(theCompositeStructureDiagram);
        }

        @objid ("b25d6fb1-e2f4-4cdd-bbf6-ec16a080494d")
        @Override
        public Object visitConditionalNode(ConditionalNode theConditionalNode) {
            return new ConditionalNodePropertyModel(theConditionalNode);
        }

        @objid ("d03368ce-4c6f-4fdc-a9f3-2c72bd4c55ba")
        @Override
        public Object visitConnectionPointReference(ConnectionPointReference theConnectionPointReference) {
            return new ConnectionPointReferencePropertyModel(theConnectionPointReference);
        }

        @objid ("141f523a-3233-4237-90b0-4ede977db626")
        @Override
        public Object visitConnector(Connector theConnector) {
            return new ConnectorPropertyModel(theConnector);
        }

        @objid ("9f48ba07-76cd-484d-bf1b-43c9dd99a63d")
        @Override
        public Object visitConnectorEnd(ConnectorEnd theConnectorEnd) {
            return new ConnectorEnd2PropertyModel(theConnectorEnd);
        }

        @objid ("5311616e-1512-405d-af75-d90fe6e2f998")
        @Override
        public Object visitConstraint(Constraint theConstraint) {
            return new ConstraintPropertyModel(theConstraint);
        }

        @objid ("da69c7fb-66ac-4b27-a62e-9daad3c1c466")
        @Override
        public Object visitControlFlow(ControlFlow theControlFlow) {
            return new ControlFlowPropertyModel(theControlFlow);
        }

        @objid ("6633a6f5-4b2a-415b-a584-ba1f2ef1725e")
        @Override
        public Object visitDataFlow(DataFlow theDataFlow) {
            return new DataFlowPropertyModel(theDataFlow);
        }

        @objid ("f20bec95-e316-485e-82c0-95c0429f7d0c")
        @Override
        public Object visitDataStoreNode(DataStoreNode theDataStoreNode) {
            return new DataStoreNodePropertyModel(theDataStoreNode);
        }

        @objid ("dca4be08-5fab-40ec-afbf-274903f5b8a6")
        @Override
        public Object visitDataType(DataType theDataType) {
            return new DataTypePropertyModel(theDataType);
        }

        @objid ("55e90969-c75a-4a8f-8957-246037fc7765")
        @Override
        public Object visitDecisionMergeNode(DecisionMergeNode theDecisionMergeNode) {
            return new DecisionMergeNodePropertyModel(theDecisionMergeNode);
        }

        @objid ("5b007db3-662f-43de-995e-53fabf3fcee0")
        @Override
        public Object visitDeepHistoryPseudoState(DeepHistoryPseudoState theDeepHistoryPseudoState) {
            return new DeepHistoryPseudoStatePropertyModel(theDeepHistoryPseudoState);
        }

        @objid ("c832dcbb-9869-4bfd-bde0-780b0adee945")
        @Override
        public Object visitDeploymentDiagram(final DeploymentDiagram theDeploymentDiagram) {
            return new DeploymentDiagramPropertyModel(theDeploymentDiagram);
        }

        @objid ("c3c8b70f-9d61-4a53-8bf3-e2a69b24bd21")
        @Override
        public Object visitDurationConstraint(DurationConstraint theDurationConstraint) {
            return new DurationConstraintPropertyModel(theDurationConstraint);
        }

        @objid ("2af3d7ea-4891-436f-b386-b45422654c28")
        @Override
        public Object visitElementImport(ElementImport theElementImport) {
            return new ElementImportPropertyModel(theElementImport);
        }

        @objid ("3776c243-763d-4641-8d78-bbc753b46e61")
        @Override
        public Object visitElementRealization(ElementRealization theElementRealization) {
            return new ElementRealizationPropertyModel(theElementRealization);
        }

        @objid ("c1ae55d4-6181-4b09-967a-1c5269e34bbb")
        @Override
        public Object visitEntryPointPseudoState(EntryPointPseudoState theEntryPointPseudoState) {
            return new EntryPointPseudoStatePropertyModel(theEntryPointPseudoState);
        }

        @objid ("05462029-0378-41ff-ac05-efd63d978a9f")
        @Override
        public Object visitEnumeration(Enumeration theEnumeration) {
            return new EnumerationPropertyModel(theEnumeration);
        }

        @objid ("49c049ff-72b9-450d-97e7-f9216b5082c6")
        @Override
        public Object visitEnumerationLiteral(EnumerationLiteral theEnumerationLiteral) {
            return new EnumerationLiteralPropertyModel(theEnumerationLiteral);
        }

        @objid ("3060041a-09b5-4fe4-993f-9c174f473e7b")
        @Override
        public Object visitEvent(Event theEvent) {
            return new EventPropertyModel(theEvent);
        }

        @objid ("d53c9059-6ef4-4849-99a5-68333a517eab")
        @Override
        public Object visitExceptionHandler(ExceptionHandler theExceptionHandler) {
            return new ExceptionHandlerPropertyModel(theExceptionHandler);
        }

        @objid ("372d1875-6581-49a2-ba5f-260f747ba033")
        @Override
        public Object visitExecutionOccurenceSpecification(ExecutionOccurenceSpecification theExecutionOccurenceSpecification) {
            return null;
        }

        @objid ("e66b6c13-6bc3-4abf-a418-c2ea85f796ae")
        @Override
        public Object visitExecutionSpecification(ExecutionSpecification theExecutionSpecification) {
            return null;
        }

        @objid ("ae65fd8a-c07a-46c5-908d-828a0c82493f")
        @Override
        public Object visitExitPointPseudoState(ExitPointPseudoState theExitPointPseudoState) {
            return new ExitPointPseudoStatePropertyModel(theExitPointPseudoState);
        }

        @objid ("ef326cb8-ee98-48a1-8a08-41b49f2ea753")
        @Override
        public Object visitExpansionNode(ExpansionNode theExpansionNode) {
            return new ExpansionNodePropertyModel(theExpansionNode);
        }

        @objid ("a86ea697-d325-4096-b39c-4f98de00673b")
        @Override
        public Object visitExpansionRegion(ExpansionRegion theExpansionRegion) {
            return new ExpansionRegionPropertyModel(theExpansionRegion);
        }

        @objid ("e9d3ae2f-ec44-4a72-8d61-2c3aca42c59a")
        @Override
        public Object visitExtensionPoint(ExtensionPoint theExtensionPoint) {
            return new ExtensionPointPropertyModel(theExtensionPoint);
        }

        @objid ("d38d4ad8-b8da-416e-af54-3f72b7eca528")
        @Override
        public Object visitFinalState(FinalState theFinalState) {
            return new FinalStatePropertyModel(theFinalState);
        }

        @objid ("cde79ccb-6e52-4ce4-a817-6a59d544cd57")
        @Override
        public Object visitFlowFinalNode(FlowFinalNode theFlowFinalNode) {
            return new FlowFinalNodePropertyModel(theFlowFinalNode);
        }

        @objid ("ede18558-2754-42ff-8509-66e06ee3cf17")
        @Override
        public Object visitForkJoinNode(ForkJoinNode theForkJoinNode) {
            return new ForkJoinNodePropertyModel(theForkJoinNode);
        }

        @objid ("acf937dc-433c-404d-b008-0d5181c5bb46")
        @Override
        public Object visitForkPseudoState(ForkPseudoState theForkPseudoState) {
            return new ForkPseudoStatePropertyModel(theForkPseudoState);
        }

        @objid ("2b933d2d-0223-41ee-a46f-6a80609ecb41")
        @Override
        public Object visitGate(Gate theGate) {
            return new GatePropertyModel(theGate);
        }

        @objid ("7c66fa24-6773-4808-a80c-7ec2edaf370e")
        @Override
        public Object visitGeneralOrdering(GeneralOrdering theGeneralOrdering) {
            return new GeneralOrderingPropertyModel(theGeneralOrdering);
        }

        @objid ("49ecc110-ba00-4fac-8081-67b2c192c4e9")
        @Override
        public Object visitGeneralization(Generalization theGeneralization) {
            return new GeneralizationPropertyModel(theGeneralization);
        }

        @objid ("f1d15ac2-ea8a-4b9b-bb8f-d59fbc2302aa")
        @Override
        public Object visitInformationFlow(InformationFlow theInformationFlow) {
            return new InformationFlowPropertyModel(theInformationFlow);
        }

        @objid ("017b7397-2cac-4e18-9854-0eefa86db5e5")
        @Override
        public Object visitInformationItem(InformationItem theInformationItem) {
            return new InformationItemPropertyModel(theInformationItem);
        }

        @objid ("f8274fe8-e330-4b85-a66d-bbaa02e742d0")
        @Override
        public Object visitInitialNode(InitialNode theInitialNode) {
            return new InitialNodePropertyModel(theInitialNode);
        }

        @objid ("123fb5f4-3cb2-4534-8cf1-c3e18ddd1971")
        @Override
        public Object visitInitialPseudoState(InitialPseudoState theInitialPseudoState) {
            return new InitialPseudoStatePropertyModel(theInitialPseudoState);
        }

        @objid ("61575abf-6de3-4889-a4a2-0f41830cae71")
        @Override
        public Object visitInputPin(InputPin theInputPin) {
            return new InputPinPropertyModel(theInputPin);
        }

        @objid ("cf3f4b9d-0ffa-43e0-b202-d000731ab023")
        @Override
        public Object visitInstance(Instance theInstance) {
            return new InstancePropertyModel(theInstance);
        }

        @objid ("3a1d53a8-993b-4e60-9cf8-95d9bace4fa1")
        @Override
        public Object visitInstanceNode(InstanceNode theInstanceNode) {
            return new InstanceNodePropertyModel(theInstanceNode);
        }

        @objid ("41dbdb15-aca9-4309-8125-4c1d173e4d1a")
        @Override
        public Object visitInteraction(Interaction theInteraction) {
            return new InteractionPropertyModel(theInteraction);
        }

        @objid ("456ff567-8c74-421d-8aca-ec04efbc9d96")
        @Override
        public Object visitInteractionOperand(InteractionOperand theInteractionOperand) {
            return new InteractionOperandPropertyModel(theInteractionOperand);
        }

        @objid ("3362719b-4e22-4be1-b60c-addd9552ea6d")
        @Override
        public Object visitInteractionUse(InteractionUse theInteractionUse) {
            return new InteractionUsePropertyModel(theInteractionUse);
        }

        @objid ("6bbf18c2-882d-4f8b-b72a-ed0301c860df")
        @Override
        public Object visitInterface(Interface theInterface) {
            return new InterfacePropertyModel(theInterface);
        }

        @objid ("c03542a2-cb50-4ae0-a17b-ae81ff3c6d34")
        @Override
        public Object visitInterfaceRealization(InterfaceRealization theInterfaceRealization) {
            return new InterfaceRealizationPropertyModel(theInterfaceRealization);
        }

        @objid ("1df1b587-31fb-4abe-8224-e6617f0f5681")
        @Override
        public Object visitInternalTransition(InternalTransition theInternalTransition) {
            return new InternalTransitionPropertyModel(theInternalTransition);
        }

        @objid ("1ea47f20-4359-43cd-8e41-d0dc344724b0")
        @Override
        public Object visitInterruptibleActivityRegion(InterruptibleActivityRegion theInterruptibleActivityRegion) {
            return new InterruptibleActivityRegionPropertyModel(theInterruptibleActivityRegion);
        }

        @objid ("018f9968-d204-4135-a87c-e625e6192be0")
        @Override
        public Object visitJoinPseudoState(JoinPseudoState theJoinPseudoState) {
            return new JoinPseudoStatePropertyModel(theJoinPseudoState);
        }

        @objid ("048dd483-51a0-43b6-bad9-9c7149029b3e")
        @Override
        public Object visitJunctionPseudoState(JunctionPseudoState theJunctionPseudoState) {
            return new JunctionPseudoStatePropertyModel(theJunctionPseudoState);
        }

        @objid ("4ac89e7d-6afa-4e89-9a85-a4b01370f8c6")
        @Override
        public Object visitLifeline(Lifeline theLifeline) {
            return new LifelinePropertyModel(theLifeline);
        }

        @objid ("b20d7a39-1ee3-4a50-91b0-0802e6482020")
        @Override
        public Object visitLink(Link theLink) {
            return new LinkPropertyModel(theLink);
        }

        @objid ("875be3ec-51a1-47fa-924b-5f63e50d69b6")
        @Override
        public Object visitLinkEnd(LinkEnd theLinkEnd) {
            return new LinkEnd2PropertyModel(theLinkEnd);
        }

        @objid ("2b194f40-7817-4cc3-a71b-5154c9101b36")
        @Override
        public Object visitLoopNode(LoopNode theLoopNode) {
            return new LoopNodePropertyModel(theLoopNode);
        }

        @objid ("39033059-c71e-4ec0-8234-4080f5e42cb3")
        @Override
        public Object visitManifestation(Manifestation theManifestation) {
            return new ManifestationPropertyModel(theManifestation);
        }

        @objid ("5b2a7048-8f0e-4ec3-bd0a-e18b1c37f79f")
        @Override
        public Object visitMessage(Message theMessage) {
            return new MessagePropertyModel(theMessage);
        }

        @objid ("d0723468-4b29-4234-95fe-93005fa51c17")
        @Override
        public Object visitMessageFlow(MessageFlow theMessageFlow) {
            return new MessageFlowPropertyModel(theMessageFlow);
        }

        @objid ("599a96d9-68d3-497d-ad9f-482b6205c36f")
        @Override
        public Object visitNaryAssociation(NaryAssociation obj) {
            return new AssociationEndNPropertyModel(obj.getNaryEnd().get(0));
        }

        @objid ("8562e381-b0a0-4cc9-9926-6414609d34a3")
        @Override
        public Object visitNaryLink(NaryLink obj) {
            return new LinkEndNPropertyModel(obj.getNaryLinkEnd().get(0));
        }

        @objid ("dc8e283d-f3ec-46e9-901a-06584d3ab607")
        @Override
        public Object visitNaryLinkEnd(NaryLinkEnd theNaryLinkEnd) {
            return new LinkEndNPropertyModel(theNaryLinkEnd);
        }

        @objid ("d0a00c81-ba60-4704-93cc-ee8e509427fe")
        @Override
        public Object visitNode(Node theNode) {
            return new NodePropertyModel(theNode);
        }

        @objid ("fcd9ebfe-8b7c-41bf-b391-7ea48d8ae2e6")
        @Override
        public Object visitObjectDiagram(final ObjectDiagram theObjectDiagram) {
            return new ObjectDiagramPropertyModel(theObjectDiagram);
        }

        @objid ("f263fef9-c577-4a95-900d-a9a1a256e668")
        @Override
        public Object visitObjectFlow(ObjectFlow theObjectFlow) {
            return new ObjectFlowPropertyModel(theObjectFlow);
        }

        @objid ("3f86e210-4d97-48f2-86a8-d4a771af5982")
        @Override
        public Object visitOpaqueAction(OpaqueAction theOpaqueAction) {
            return new OpaqueActionPropertyModel(theOpaqueAction);
        }

        @objid ("778b1257-be22-43cf-81b5-a2263081f5cb")
        @Override
        public Object visitOpaqueBehavior(OpaqueBehavior theOpaqueBehavior) {
            return new OpaqueBehaviorPropertyModel(theOpaqueBehavior);
        }

        @objid ("2a37a218-4db4-4f5b-aa84-a146a899899f")
        @Override
        public Object visitOperation(Operation theOperation) {
            return new OperationPropertyModel(theOperation);
        }

        @objid ("7e2f66fb-631c-4e53-9592-f52c7746ad20")
        @Override
        public Object visitOutputPin(OutputPin theOutputPin) {
            return new OutputPinPropertyModel(theOutputPin);
        }

        @objid ("0df15f64-fee6-46aa-9199-ce05a1fbfa4c")
        @Override
        public Object visitPackage(Package thePackage) {
            return new PackagePropertyModel(thePackage);
        }

        @objid ("59e0b217-8cb3-4263-9131-cd5d902f8269")
        @Override
        public Object visitPackageImport(PackageImport thePackageImport) {
            return new PackageImportPropertyModel(thePackageImport);
        }

        @objid ("349a6742-5360-4008-a86c-acee1c5c2545")
        @Override
        public Object visitPackageMerge(PackageMerge thePackageMerge) {
            return new PackageMergePropertyModel(thePackageMerge);
        }

        @objid ("93e6fd31-8124-4aa6-92e3-3c1a31f9ffa9")
        @Override
        public Object visitParameter(Parameter theParameter) {
            if (theParameter.getReturned() != null) {
                return new ReturnParameterPropertyModel(theParameter);
            } else if (theParameter.getComposed() != null) {
                return new IOParameterPropertyModel(theParameter);
            } else {
                return null;
            }
        }

        @objid ("d6b1688f-b71e-4755-9e6a-ed9cea481339")
        @Override
        public Object visitPartDecomposition(PartDecomposition thePartDecomposition) {
            return new PartDecompositionPropertyModel(thePartDecomposition);
        }

        @objid ("b505e657-8f84-41cf-9885-f138a827bbf2")
        @Override
        public Object visitPort(Port thePort) {
            return new PortPropertyModel(thePort);
        }

        @objid ("1f7540f5-4945-455c-8c89-1b1d0e868669")
        @Override
        public Object visitProject(Project theProject) {
            return new ProjectPropertyModel(theProject);
        }

        @objid ("6cfa771a-234f-4f46-acaf-ba8cbc41928b")
        @Override
        public Object visitProvidedInterface(ProvidedInterface theProvidedInterface) {
            return new ProvidedInterfacePropertyModel(theProvidedInterface);
        }

        @objid ("e7237d21-ec39-4905-8c2f-c5f619c3eb6d")
        @Override
        public Object visitRaisedException(RaisedException theRaisedException) {
            return new RaisedExceptionPropertyModel(theRaisedException);
        }

        @objid ("bd0b589d-cb9f-4ff3-a585-b429eb039d49")
        @Override
        public Object visitRequiredInterface(RequiredInterface theRequiredInterface) {
            return new RequiredInterfacePropertyModel(theRequiredInterface);
        }

        @objid ("6cd1b163-2252-4181-a171-74e5eeae13db")
        @Override
        public Object visitSendSignalAction(SendSignalAction theSendSignalAction) {
            return new SendSignalActionPropertyModel(theSendSignalAction);
        }

        @objid ("6d29df98-4199-4ca6-9517-225a5749cb08")
        @Override
        public Object visitSequenceDiagram(SequenceDiagram theSequenceDiagram) {
            return new SequenceDiagramPropertyModel(theSequenceDiagram);
        }

        @objid ("34ffeb29-d81d-41e9-a763-9f19d21518fc")
        @Override
        public Object visitShallowHistoryPseudoState(ShallowHistoryPseudoState theShallowHistoryPseudoState) {
            return new ShallowHistoryPseudoStatePropertyModel(theShallowHistoryPseudoState);
        }

        @objid ("08110f83-b014-4a07-b692-cf2266ed94ab")
        @Override
        public Object visitSignal(Signal theSignal) {
            return new SignalPropertyModel(theSignal);
        }

        @objid ("7bfe1f40-9117-4efc-b489-13cb7af4ba56")
        @Override
        public Object visitState(State theState) {
            return new StatePropertyModel(theState);
        }

        @objid ("f3d28a2b-4df2-4469-909a-150a5efe877e")
        @Override
        public Object visitStateInvariant(StateInvariant theStateInvariant) {
            return new StateInvariantPropertyModel(theStateInvariant);
        }

        @objid ("90a6ab41-4c69-4ff9-9ea6-45113010a5ae")
        @Override
        public Object visitStateMachine(StateMachine theStateMachine) {
            return new StateMachinePropertyModel(theStateMachine);
        }

        @objid ("d3e2d220-7520-4a98-95cc-97aa4e72f519")
        @Override
        public Object visitStateMachineDiagram(final StateMachineDiagram theStateMachineDiagram) {
            return new StateMachineDiagramPropertyModel(theStateMachineDiagram);
        }

        @objid ("97e740cb-95b0-4e59-a68a-db6255855eba")
        @Override
        public Object visitStaticDiagram(StaticDiagram theStaticDiagram) {
            return new StaticDiagramPropertyModel(theStaticDiagram);
        }

        @objid ("6cf85978-9f53-4fcb-a49d-ec718b37fe76")
        @Override
        public Object visitStructuredActivityNode(StructuredActivityNode theStructuredActivityNode) {
            return new StructuredActivityNodePropertyModel(theStructuredActivityNode);
        }

        @objid ("19fecb2f-fa45-4c98-b212-f0e4c6c4da6b")
        @Override
        public Object visitSubstitution(Substitution theSubstitution) {
            return new SubstitutionPropertyModel(theSubstitution);
        }

        @objid ("190c3bd2-75bb-40c1-956b-f9f5fcfe4f6f")
        @Override
        public Object visitTemplateBinding(TemplateBinding theTemplateBinding) {
            return new TemplateBindingPropertyModel(theTemplateBinding, this.context.getModelService());
        }

        @objid ("842b9b05-ffdc-4ca8-bb9f-c3568dd18dce")
        @Override
        public Object visitTemplateParameter(TemplateParameter theTemplateParameter) {
            return new TemplateParameterPropertyModel(theTemplateParameter);
        }

        @objid ("c515d5eb-a7d3-4ce3-880a-cf08153b0a62")
        @Override
        public Object visitTemplateParameterSubstitution(TemplateParameterSubstitution theTemplateParameterSubstitution) {
            return new TemplateParameterSubstitutionPropertyModel(theTemplateParameterSubstitution);
        }

        @objid ("8c3e3020-a58a-466a-b911-96a8089e1a66")
        @Override
        public Object visitTerminatePseudoState(TerminatePseudoState theTerminatePseudoState) {
            return new TerminatePseudoStatePropertyModel(theTerminatePseudoState);
        }

        @objid ("9761eba2-7310-4fe2-96b0-bb868f21d52c")
        @Override
        public Object visitTransition(Transition theTransition) {
            return new TransitionPropertyModel(theTransition);
        }

        @objid ("3186c5f0-5eb3-4b95-a4d4-dca0262e6ab2")
        @Override
        public Object visitUsage(Usage theUsage) {
            return new UsagePropertyModel(theUsage);
        }

        @objid ("4c789bc0-edf3-4dce-ad7e-e6f2183ed505")
        @Override
        public Object visitUseCase(UseCase theUseCase) {
            return new UseCasePropertyModel(theUseCase);
        }

        @objid ("3d8871cd-b730-4fff-9c32-79e97b11908b")
        @Override
        public Object visitUseCaseDependency(UseCaseDependency theUseCaseDependency) {
            return new UseCaseDependencyPropertyModel(theUseCaseDependency);
        }

        @objid ("de9e4fd7-bfd4-4fa6-97db-f0c44ae94a64")
        @Override
        public Object visitUseCaseDiagram(final UseCaseDiagram theUseCaseDiagram) {
            return new UseCaseDiagramPropertyModel(theUseCaseDiagram);
        }

        @objid ("23d4ea5d-cdc3-47d1-9404-32cadb6a48f1")
        @Override
        public Object visitValuePin(ValuePin theValuePin) {
            return new ValuePinPropertyModel(theValuePin);
        }

    }

}
