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
package org.modelio.metamodel.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.bpmn.activities.BpmnActivitySmClass;
import org.modelio.metamodel.impl.bpmn.activities.BpmnAdHocSubProcessSmClass;
import org.modelio.metamodel.impl.bpmn.activities.BpmnBusinessRuleTaskSmClass;
import org.modelio.metamodel.impl.bpmn.activities.BpmnCallActivitySmClass;
import org.modelio.metamodel.impl.bpmn.activities.BpmnComplexBehaviorDefinitionSmClass;
import org.modelio.metamodel.impl.bpmn.activities.BpmnLoopCharacteristicsSmClass;
import org.modelio.metamodel.impl.bpmn.activities.BpmnManualTaskSmClass;
import org.modelio.metamodel.impl.bpmn.activities.BpmnMultiInstanceLoopCharacteristicsSmClass;
import org.modelio.metamodel.impl.bpmn.activities.BpmnReceiveTaskSmClass;
import org.modelio.metamodel.impl.bpmn.activities.BpmnScriptTaskSmClass;
import org.modelio.metamodel.impl.bpmn.activities.BpmnSendTaskSmClass;
import org.modelio.metamodel.impl.bpmn.activities.BpmnServiceTaskSmClass;
import org.modelio.metamodel.impl.bpmn.activities.BpmnStandardLoopCharacteristicsSmClass;
import org.modelio.metamodel.impl.bpmn.activities.BpmnSubProcessSmClass;
import org.modelio.metamodel.impl.bpmn.activities.BpmnTaskSmClass;
import org.modelio.metamodel.impl.bpmn.activities.BpmnTransactionSmClass;
import org.modelio.metamodel.impl.bpmn.activities.BpmnUserTaskSmClass;
import org.modelio.metamodel.impl.bpmn.bpmnDiagrams.BpmnCollaborationDiagramSmClass;
import org.modelio.metamodel.impl.bpmn.bpmnDiagrams.BpmnProcessCollaborationDiagramSmClass;
import org.modelio.metamodel.impl.bpmn.bpmnDiagrams.BpmnProcessDesignDiagramSmClass;
import org.modelio.metamodel.impl.bpmn.bpmnDiagrams.BpmnSubProcessDiagramSmClass;
import org.modelio.metamodel.impl.bpmn.bpmnService.BpmnEndPointSmClass;
import org.modelio.metamodel.impl.bpmn.bpmnService.BpmnInterfaceSmClass;
import org.modelio.metamodel.impl.bpmn.bpmnService.BpmnOperationSmClass;
import org.modelio.metamodel.impl.bpmn.events.BpmnBoundaryEventSmClass;
import org.modelio.metamodel.impl.bpmn.events.BpmnCancelEventDefinitionSmClass;
import org.modelio.metamodel.impl.bpmn.events.BpmnCatchEventSmClass;
import org.modelio.metamodel.impl.bpmn.events.BpmnCompensateEventDefinitionSmClass;
import org.modelio.metamodel.impl.bpmn.events.BpmnConditionalEventDefinitionSmClass;
import org.modelio.metamodel.impl.bpmn.events.BpmnEndEventSmClass;
import org.modelio.metamodel.impl.bpmn.events.BpmnErrorEventDefinitionSmClass;
import org.modelio.metamodel.impl.bpmn.events.BpmnEscalationEventDefinitionSmClass;
import org.modelio.metamodel.impl.bpmn.events.BpmnEventDefinitionSmClass;
import org.modelio.metamodel.impl.bpmn.events.BpmnEventSmClass;
import org.modelio.metamodel.impl.bpmn.events.BpmnImplicitThrowEventSmClass;
import org.modelio.metamodel.impl.bpmn.events.BpmnIntermediateCatchEventSmClass;
import org.modelio.metamodel.impl.bpmn.events.BpmnIntermediateThrowEventSmClass;
import org.modelio.metamodel.impl.bpmn.events.BpmnLinkEventDefinitionSmClass;
import org.modelio.metamodel.impl.bpmn.events.BpmnMessageEventDefinitionSmClass;
import org.modelio.metamodel.impl.bpmn.events.BpmnSignalEventDefinitionSmClass;
import org.modelio.metamodel.impl.bpmn.events.BpmnStartEventSmClass;
import org.modelio.metamodel.impl.bpmn.events.BpmnTerminateEventDefinitionSmClass;
import org.modelio.metamodel.impl.bpmn.events.BpmnThrowEventSmClass;
import org.modelio.metamodel.impl.bpmn.events.BpmnTimerEventDefinitionSmClass;
import org.modelio.metamodel.impl.bpmn.flows.BpmnMessageFlowSmClass;
import org.modelio.metamodel.impl.bpmn.flows.BpmnMessageSmClass;
import org.modelio.metamodel.impl.bpmn.flows.BpmnSequenceFlowSmClass;
import org.modelio.metamodel.impl.bpmn.gateways.BpmnComplexGatewaySmClass;
import org.modelio.metamodel.impl.bpmn.gateways.BpmnEventBasedGatewaySmClass;
import org.modelio.metamodel.impl.bpmn.gateways.BpmnExclusiveGatewaySmClass;
import org.modelio.metamodel.impl.bpmn.gateways.BpmnGatewaySmClass;
import org.modelio.metamodel.impl.bpmn.gateways.BpmnInclusiveGatewaySmClass;
import org.modelio.metamodel.impl.bpmn.gateways.BpmnParallelGatewaySmClass;
import org.modelio.metamodel.impl.bpmn.objects.BpmnDataAssociationSmClass;
import org.modelio.metamodel.impl.bpmn.objects.BpmnDataInputSmClass;
import org.modelio.metamodel.impl.bpmn.objects.BpmnDataObjectSmClass;
import org.modelio.metamodel.impl.bpmn.objects.BpmnDataOutputSmClass;
import org.modelio.metamodel.impl.bpmn.objects.BpmnDataStateSmClass;
import org.modelio.metamodel.impl.bpmn.objects.BpmnDataStoreSmClass;
import org.modelio.metamodel.impl.bpmn.objects.BpmnItemAwareElementSmClass;
import org.modelio.metamodel.impl.bpmn.objects.BpmnItemDefinitionSmClass;
import org.modelio.metamodel.impl.bpmn.objects.BpmnSequenceFlowDataAssociationSmClass;
import org.modelio.metamodel.impl.bpmn.processCollaboration.BpmnCollaborationSmClass;
import org.modelio.metamodel.impl.bpmn.processCollaboration.BpmnLaneSetSmClass;
import org.modelio.metamodel.impl.bpmn.processCollaboration.BpmnLaneSmClass;
import org.modelio.metamodel.impl.bpmn.processCollaboration.BpmnParticipantSmClass;
import org.modelio.metamodel.impl.bpmn.processCollaboration.BpmnProcessSmClass;
import org.modelio.metamodel.impl.bpmn.resources.BpmnResourceParameterBindingSmClass;
import org.modelio.metamodel.impl.bpmn.resources.BpmnResourceParameterSmClass;
import org.modelio.metamodel.impl.bpmn.resources.BpmnResourceRoleSmClass;
import org.modelio.metamodel.impl.bpmn.resources.BpmnResourceSmClass;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnArtifactSmClass;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnAssociationSmClass;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnBaseElementSmClass;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnFlowElementSmClass;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnFlowNodeSmClass;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnGroupSmClass;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnSharedDefinitionsSmClass;
import org.modelio.metamodel.impl.bpmn.rootElements.BpmnSharedElementSmClass;
import org.modelio.metamodel.impl.control.AttributeTypeChecker;
import org.modelio.metamodel.impl.control.BindableInstanceInternalOwnerChecker;
import org.modelio.metamodel.impl.control.CollaborationUseNRepresentedChecker;
import org.modelio.metamodel.impl.control.ConnectorEndRepresentedFeatureChecker;
import org.modelio.metamodel.impl.control.ExternDocumentSubjectChecker;
import org.modelio.metamodel.impl.control.InstanceOwnerChecker;
import org.modelio.metamodel.impl.control.InterfaceRealizationImplementerChecker;
import org.modelio.metamodel.impl.control.ModelTreeOwnerChecker;
import org.modelio.metamodel.impl.control.NoteSubjectChecker;
import org.modelio.metamodel.impl.control.ParameterTypeChecker;
import org.modelio.metamodel.impl.control.RaisedExceptionThrownTypeChecker;
import org.modelio.metamodel.impl.control.StereotypeExtendedElementChecker;
import org.modelio.metamodel.impl.control.TaggedValueAnnotedChecker;
import org.modelio.metamodel.impl.control.TemplateBindingBoundElementChecker;
import org.modelio.metamodel.impl.control.TemplateParameterParametrizedChecker;
import org.modelio.metamodel.impl.diagrams.ActivityDiagramSmClass;
import org.modelio.metamodel.impl.diagrams.BehaviorDiagramSmClass;
import org.modelio.metamodel.impl.diagrams.ClassDiagramSmClass;
import org.modelio.metamodel.impl.diagrams.CommunicationDiagramSmClass;
import org.modelio.metamodel.impl.diagrams.CompositeStructureDiagramSmClass;
import org.modelio.metamodel.impl.diagrams.DeploymentDiagramSmClass;
import org.modelio.metamodel.impl.diagrams.ObjectDiagramSmClass;
import org.modelio.metamodel.impl.diagrams.SequenceDiagramSmClass;
import org.modelio.metamodel.impl.diagrams.StateMachineDiagramSmClass;
import org.modelio.metamodel.impl.diagrams.StaticDiagramSmClass;
import org.modelio.metamodel.impl.diagrams.UseCaseDiagramSmClass;
import org.modelio.metamodel.impl.expert.StandardMetamodelExpert;
import org.modelio.metamodel.impl.mda.ProjectSmClass;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.StandardCheckerFactory;
import org.modelio.metamodel.impl.uml.behavior.activityModel.AcceptCallEventActionSmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.AcceptChangeEventActionSmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.AcceptSignalActionSmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.AcceptTimeEventActionSmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ActivityActionSmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ActivityEdgeSmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ActivityFinalNodeSmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ActivityGroupSmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ActivityNodeSmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ActivityParameterNodeSmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ActivityPartitionSmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ActivitySmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.CallActionSmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.CallBehaviorActionSmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.CallOperationActionSmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.CentralBufferNodeSmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ClauseSmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ConditionalNodeSmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ControlFlowSmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ControlNodeSmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.DataStoreNodeSmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.DecisionMergeNodeSmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ExceptionHandlerSmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ExpansionNodeSmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ExpansionRegionSmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.FinalNodeSmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.FlowFinalNodeSmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ForkJoinNodeSmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.InitialNodeSmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.InputPinSmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.InstanceNodeSmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.InterruptibleActivityRegionSmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.LoopNodeSmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.MessageFlowSmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ObjectFlowSmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ObjectNodeSmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.OpaqueActionSmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.OutputPinSmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.PinSmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.SendSignalActionSmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.StructuredActivityNodeSmClass;
import org.modelio.metamodel.impl.uml.behavior.activityModel.ValuePinSmClass;
import org.modelio.metamodel.impl.uml.behavior.commonBehaviors.BehaviorParameterSmClass;
import org.modelio.metamodel.impl.uml.behavior.commonBehaviors.BehaviorSmClass;
import org.modelio.metamodel.impl.uml.behavior.commonBehaviors.EventSmClass;
import org.modelio.metamodel.impl.uml.behavior.commonBehaviors.OpaqueBehaviorSmClass;
import org.modelio.metamodel.impl.uml.behavior.commonBehaviors.SignalSmClass;
import org.modelio.metamodel.impl.uml.behavior.communicationModel.CommunicationChannelSmClass;
import org.modelio.metamodel.impl.uml.behavior.communicationModel.CommunicationInteractionSmClass;
import org.modelio.metamodel.impl.uml.behavior.communicationModel.CommunicationMessageSmClass;
import org.modelio.metamodel.impl.uml.behavior.communicationModel.CommunicationNodeSmClass;
import org.modelio.metamodel.impl.uml.behavior.interactionModel.CombinedFragmentSmClass;
import org.modelio.metamodel.impl.uml.behavior.interactionModel.DurationConstraintSmClass;
import org.modelio.metamodel.impl.uml.behavior.interactionModel.ExecutionOccurenceSpecificationSmClass;
import org.modelio.metamodel.impl.uml.behavior.interactionModel.ExecutionSpecificationSmClass;
import org.modelio.metamodel.impl.uml.behavior.interactionModel.GateSmClass;
import org.modelio.metamodel.impl.uml.behavior.interactionModel.GeneralOrderingSmClass;
import org.modelio.metamodel.impl.uml.behavior.interactionModel.InteractionFragmentSmClass;
import org.modelio.metamodel.impl.uml.behavior.interactionModel.InteractionOperandSmClass;
import org.modelio.metamodel.impl.uml.behavior.interactionModel.InteractionSmClass;
import org.modelio.metamodel.impl.uml.behavior.interactionModel.InteractionUseSmClass;
import org.modelio.metamodel.impl.uml.behavior.interactionModel.LifelineSmClass;
import org.modelio.metamodel.impl.uml.behavior.interactionModel.MessageEndSmClass;
import org.modelio.metamodel.impl.uml.behavior.interactionModel.MessageSmClass;
import org.modelio.metamodel.impl.uml.behavior.interactionModel.OccurrenceSpecificationSmClass;
import org.modelio.metamodel.impl.uml.behavior.interactionModel.PartDecompositionSmClass;
import org.modelio.metamodel.impl.uml.behavior.interactionModel.StateInvariantSmClass;
import org.modelio.metamodel.impl.uml.behavior.interactionModel.TerminateSpecificationSmClass;
import org.modelio.metamodel.impl.uml.behavior.stateMachineModel.AbstractPseudoStateSmClass;
import org.modelio.metamodel.impl.uml.behavior.stateMachineModel.ChoicePseudoStateSmClass;
import org.modelio.metamodel.impl.uml.behavior.stateMachineModel.ConnectionPointReferenceSmClass;
import org.modelio.metamodel.impl.uml.behavior.stateMachineModel.DeepHistoryPseudoStateSmClass;
import org.modelio.metamodel.impl.uml.behavior.stateMachineModel.EntryPointPseudoStateSmClass;
import org.modelio.metamodel.impl.uml.behavior.stateMachineModel.ExitPointPseudoStateSmClass;
import org.modelio.metamodel.impl.uml.behavior.stateMachineModel.FinalStateSmClass;
import org.modelio.metamodel.impl.uml.behavior.stateMachineModel.ForkPseudoStateSmClass;
import org.modelio.metamodel.impl.uml.behavior.stateMachineModel.InitialPseudoStateSmClass;
import org.modelio.metamodel.impl.uml.behavior.stateMachineModel.InternalTransitionSmClass;
import org.modelio.metamodel.impl.uml.behavior.stateMachineModel.JoinPseudoStateSmClass;
import org.modelio.metamodel.impl.uml.behavior.stateMachineModel.JunctionPseudoStateSmClass;
import org.modelio.metamodel.impl.uml.behavior.stateMachineModel.RegionSmClass;
import org.modelio.metamodel.impl.uml.behavior.stateMachineModel.ShallowHistoryPseudoStateSmClass;
import org.modelio.metamodel.impl.uml.behavior.stateMachineModel.StateMachineSmClass;
import org.modelio.metamodel.impl.uml.behavior.stateMachineModel.StateSmClass;
import org.modelio.metamodel.impl.uml.behavior.stateMachineModel.StateVertexSmClass;
import org.modelio.metamodel.impl.uml.behavior.stateMachineModel.TerminatePseudoStateSmClass;
import org.modelio.metamodel.impl.uml.behavior.stateMachineModel.TransitionSmClass;
import org.modelio.metamodel.impl.uml.behavior.usecaseModel.ActorSmClass;
import org.modelio.metamodel.impl.uml.behavior.usecaseModel.ExtensionPointSmClass;
import org.modelio.metamodel.impl.uml.behavior.usecaseModel.UseCaseDependencySmClass;
import org.modelio.metamodel.impl.uml.behavior.usecaseModel.UseCaseSmClass;
import org.modelio.metamodel.impl.uml.informationFlow.DataFlowSmClass;
import org.modelio.metamodel.impl.uml.informationFlow.InformationFlowSmClass;
import org.modelio.metamodel.impl.uml.informationFlow.InformationItemSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.AbstractionSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.ConstraintSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.ModelTreeSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.SubstitutionSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementSmClass;
import org.modelio.metamodel.impl.uml.infrastructure.UsageSmClass;
import org.modelio.metamodel.impl.uml.statik.ArtifactSmClass;
import org.modelio.metamodel.impl.uml.statik.AssociationEndSmClass;
import org.modelio.metamodel.impl.uml.statik.AssociationSmClass;
import org.modelio.metamodel.impl.uml.statik.AttributeLinkSmClass;
import org.modelio.metamodel.impl.uml.statik.AttributeSmClass;
import org.modelio.metamodel.impl.uml.statik.BehavioralFeatureSmClass;
import org.modelio.metamodel.impl.uml.statik.BindableInstanceSmClass;
import org.modelio.metamodel.impl.uml.statik.BindingSmClass;
import org.modelio.metamodel.impl.uml.statik.ClassAssociationSmClass;
import org.modelio.metamodel.impl.uml.statik.ClassSmClass;
import org.modelio.metamodel.impl.uml.statik.ClassifierSmClass;
import org.modelio.metamodel.impl.uml.statik.CollaborationSmClass;
import org.modelio.metamodel.impl.uml.statik.CollaborationUseSmClass;
import org.modelio.metamodel.impl.uml.statik.ComponentRealizationSmClass;
import org.modelio.metamodel.impl.uml.statik.ComponentSmClass;
import org.modelio.metamodel.impl.uml.statik.ConnectorEndSmClass;
import org.modelio.metamodel.impl.uml.statik.ConnectorSmClass;
import org.modelio.metamodel.impl.uml.statik.DataTypeSmClass;
import org.modelio.metamodel.impl.uml.statik.ElementImportSmClass;
import org.modelio.metamodel.impl.uml.statik.ElementRealizationSmClass;
import org.modelio.metamodel.impl.uml.statik.EnumerationLiteralSmClass;
import org.modelio.metamodel.impl.uml.statik.EnumerationSmClass;
import org.modelio.metamodel.impl.uml.statik.FeatureSmClass;
import org.modelio.metamodel.impl.uml.statik.GeneralClassSmClass;
import org.modelio.metamodel.impl.uml.statik.GeneralizationSmClass;
import org.modelio.metamodel.impl.uml.statik.InstanceSmClass;
import org.modelio.metamodel.impl.uml.statik.InterfaceRealizationSmClass;
import org.modelio.metamodel.impl.uml.statik.InterfaceSmClass;
import org.modelio.metamodel.impl.uml.statik.LinkEndSmClass;
import org.modelio.metamodel.impl.uml.statik.LinkSmClass;
import org.modelio.metamodel.impl.uml.statik.ManifestationSmClass;
import org.modelio.metamodel.impl.uml.statik.NameSpaceSmClass;
import org.modelio.metamodel.impl.uml.statik.NaryAssociationEndSmClass;
import org.modelio.metamodel.impl.uml.statik.NaryAssociationSmClass;
import org.modelio.metamodel.impl.uml.statik.NaryConnectorEndSmClass;
import org.modelio.metamodel.impl.uml.statik.NaryConnectorSmClass;
import org.modelio.metamodel.impl.uml.statik.NaryLinkEndSmClass;
import org.modelio.metamodel.impl.uml.statik.NaryLinkSmClass;
import org.modelio.metamodel.impl.uml.statik.NodeSmClass;
import org.modelio.metamodel.impl.uml.statik.OperationSmClass;
import org.modelio.metamodel.impl.uml.statik.PackageImportSmClass;
import org.modelio.metamodel.impl.uml.statik.PackageMergeSmClass;
import org.modelio.metamodel.impl.uml.statik.PackageSmClass;
import org.modelio.metamodel.impl.uml.statik.ParameterSmClass;
import org.modelio.metamodel.impl.uml.statik.PortSmClass;
import org.modelio.metamodel.impl.uml.statik.ProvidedInterfaceSmClass;
import org.modelio.metamodel.impl.uml.statik.RaisedExceptionSmClass;
import org.modelio.metamodel.impl.uml.statik.RequiredInterfaceSmClass;
import org.modelio.metamodel.impl.uml.statik.StructuralFeatureSmClass;
import org.modelio.metamodel.impl.uml.statik.TemplateBindingSmClass;
import org.modelio.metamodel.impl.uml.statik.TemplateParameterSmClass;
import org.modelio.metamodel.impl.uml.statik.TemplateParameterSubstitutionSmClass;
import org.modelio.vbasic.version.Version;
import org.modelio.vbasic.version.VersionedItem;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.mapi.MExpert;
import org.modelio.vcore.smkernel.mapi.MMetamodelFragment;
import org.modelio.vcore.smkernel.mapi.modelshield.spi.ICheckerFactory;
import org.modelio.vcore.smkernel.meta.AbstractMetamodelFragment;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependencyTypeChecker;
import org.modelio.vcore.smkernel.meta.SmMetamodel;

/**
 * <h2>Metamodel changes history since first&nbsp;UML2 implementation</h2>
 * 
 * <p><strong>Modelio 3.8.00&nbsp;- 2.2.02&nbsp;</strong><em>(by&nbsp;chm)</em></p><p>- BpmnCallActivity.CalledOperation: Operation<br />
 * - BpmnCallActivity.CalledBehavior: Behavior<br />
 * - BpmnDataInput.RepresentedParameter: Parameter<br />
 * - BpmnDataOutput.RepresentedParameter: Parameter<br />
 * - BpmnDataState.UmlState: State<br />
 * - BpmnInterface.ImplementationRef: GeneralClass<br />
 * - BpmnItemAwareElement.Type: GeneralClass<br />
 * - BpmnItemAwareElement.InState: State<br />
 * - BpmnItemAwareElement.RepresentedAssociationEnd: AssociationEnd<br />
 * - BpmnItemAwareElement.RepresentedAttribute: Attribute<br />
 * - BpmnItemAwareElement.RepresentedInstance: Instance<br />
 * - BpmnItemDefinition.StructureRef: GeneralClass<br />
 * - BpmnLane.PartitionElement: UmlModelElement<br />
 * - BpmnMessage.Type: GeneralClass<br />
 * - BpmnMessage.InState: State<br />
 * - BpmnOperation.ImplementationRef: Operation<br />
 * - BpmnParticipant.PackageRef: Package<br />
 * - BpmnParticipant.Type: Classifier<br />
 * - BpmnReceiveTask.CalledOperation: Operation<br />
 * - BpmnSendTask.CalledOperation: Operation<br />
 * - BpmnServiceTask.CalledOperation: Operation<br />
 * <em>Note: BPMN -&gt; UML dependencies replaced with MethodologicalLinks.</em></p><p><strong>Modelio 3.7.01&nbsp;- 2.2.01&nbsp;</strong><em>(by&nbsp;cma)</em></p><p>+ Message.Sequence : String : sequence number for messages, to be updated with MessageSequencer facility.</p><p><em>Note: previous Modelio version leak InteractionFragments and Messages when deleting Lifelines.</em></p><p><strong>Modelio Unicorn&nbsp;(3.7)&nbsp;- 2.2.0&nbsp;</strong><em>(by&nbsp;cma)</em></p><p><em>BPMN metamodel changes to remove &quot;BpmnBehavior&quot; that&nbsp;users (PHD) don&#39;t want to see.</em></p><p>-&nbsp;BpmnBehavior (existing instances are transmutted to&nbsp;BpmnSharedDefinitions if not empty)</p><p>+ BpmnSharedDefinitions</p><p># RootElement renamed BpmnSharedElement</p><p># BpmnProcess ^ Behavior (from UML) instead of BpmnBehavior</p><p># BpmnCollaboration&nbsp;^ Behavior (from UML)&nbsp;instead of BpmnBehavior</p><p># BpmnMessage ^ SharedElement (was BpmnBaseElement)</p><p>- BpmnProcess.Caller : &nbsp;BpmnCallActivity,&nbsp;&nbsp;to avoid clash with <em>Behavior.Caller : CallBehaviorAction&nbsp;</em>.&nbsp;Use inherited&nbsp;<em>Behavior.BpmnCaller :&nbsp;BpmnCallActivity </em>instead.</p><p>- BpmnCallActivity.CalledProcess : BpmnProcess, use <em>BpmnCallActivity.CalledBehavior</em></p><p>+ BpmnProcess.DefinitionalCollaboration : BpmnCollaboration[0..1]<br />
 * &nbsp; &nbsp; &nbsp;opposite : BpmnCollaboration.DefinedProcess : BpmnProcess[0..1]</p><p><strong>Modelio Unicorn&nbsp;(3.7)&nbsp;- 2.1.0&nbsp;</strong><em>(by&nbsp;cma)</em></p><p># BpmnProcess.Participant : 0..1 BpmnParticipant : cardinality change to &#39;*&#39;</p><p># BpmnProcess.LaneSet : * BpmnLaneSet&nbsp;: cardinality change to &#39;0..1&#39;</p><p>BPMN metamodel changes to better conform to the official spec.</p><p>&quot;Pools&quot; were wrongly interpreted as a BpmnLaneSet containing one BpmnLane. They are changed to a BpmnParticipant referencing one BpmnProcess. Existing BpmnProcess containing many &quot;Pools&quot; must be splitted, and a BpmnCollaboration must reference all these process with BpmnParticipants.</p><p><strong>Modelio Toutatis (3.6.1)&nbsp;- 2.0.0&nbsp;</strong><em>(by&nbsp;cma)</em></p><p>Infrastructure and Analyst metamodel have been split away to separate metamodels.</p>
 * 
 * <ul>
 * 	<li>+&nbsp;UmlModelElement deriving from&nbsp;Infrastructure.ModelElement . This is the new parent metaclass for elements deriving from&nbsp;Infrastructure.ModelElement in this metamodel fragment.</li>
 * 	<li>Project now derives from Infrastructure.AbstractProject</li>
 * </ul>
 * 
 * <p><strong>Modelio Toutatis (3.6)&nbsp;- 1.1.2&nbsp;</strong><em>(by&nbsp;chm)</em></p><p>- NamespaceUse</p><p><strong>Modelio Toutatis (3.6)&nbsp;- 1.1.1&nbsp;</strong><em>(by&nbsp;chm)</em></p><p>+DynamicPropertyDefinition&nbsp;&nbsp;<em>a PropertyDefinition that is not stored in the model itself, but rather dynamically calculated.</em></p><p>+AbstractProject : regroup all composition tree root metaclasses</p><p><em>#Project.Model : cardinality changed from 1 to *</em></p><p><em><strong>Modelio Shenlong (3.5) - 1.1.0 </strong><em>(by phv)</em></em></p><p><em>+TestContainer <em>an AnalystContainer specialization for test management </em></em></p><p><em>+Test <em>an AnalystItem specialization for test management</em></em></p><p><strong>Modelio 3.4 - 9025 </strong><em>(by&nbsp;phv)</em></p><p>+RiskContainer &nbsp;<em>an AnalystContainer specialization for risk&nbsp;management&nbsp;</em></p><p>+Risk <em>an AnalystItem specialization for risk management</em></p>
 * 
 * <hr />
 * <p><strong><strong>Modelio 3.3 - 9024: 28/10/2014</strong></strong></p><p>-ModuleParameter.Type<br />
 * #ModuleParameter.AssociatedType -&gt; ModuleParameter.Type</p><p>#ModuleParameter.SetName -&gt; ModuleParameter.GroupName<br />
 * +ModuleParameter.EnumType : Enumeration<br />
 * +ModuleParameter.DefaultValue : String<br />
 * -ModuleParameterType.TYPE_PARAM_FILE_OPEN<br />
 * -ModuleParameterType.TYPE_PARAM_FILE_SAVE<br />
 * +ModuleParameterType.TYPE_PARAM_INTEGER</p>
 * 
 * <hr />
 * <p><strong>Modelio 3.3 - 9023: 23/10/2014</strong></p><p>&nbsp;#Note</p><p>&nbsp;+ MimeType: string</p><p>&nbsp;</p><p><strong>Modelio 3.2 - 9022: 27/08/2014</strong></p><p>&nbsp;# BusinessRule, Goal, Requirement, Term, GenericAnalystElement are now CMS nodes</p><p>&nbsp;+ ComponentRealization</p><p>&nbsp;- BusinessRule.Version : this attribute is already on AnalystElement</p><p>&nbsp;- Goal.Version : this attribute is already on AnalystElement</p><p>&nbsp;</p><p><strong>Modelio 3.2 - 9021: 15/01/2014 :</strong></p><p>&nbsp;+ NoteType.MimeType : String</p><p>&nbsp;</p><p><strong>Modelio 3.1 - 9020: 28/11/2013 :</strong></p><p>&nbsp;Added generic analyst element that must be stereotyped, and possibility</p><p>&nbsp;to archive versions of analyst elements without using SVN.</p><p>&nbsp;+ GenericAnalystElement (must be stereotyped)</p><p>&nbsp;+ GenericAnalystContainer</p><p>&nbsp;+ Requirement.ArchivedRequirementVersion : Requirement [*]</p><p>&nbsp;+ Requirement.LastRequirementVersion : Requirement [0..1]</p><p>&nbsp;+ Goal.ArchivedGoalVersion : Goal [*]</p><p>&nbsp;+ Goal.LastGoalVersion :&nbsp; [0..1]</p><p>&nbsp;+ BusinessRule.ArchivedRuleVersion : BusinessRule [*]</p><p>&nbsp;+ BusinessRule.LastRuleVersion : BusinessRule [0..1]</p><p>&nbsp;+ Term.ArchivedTermVersion : Term [*]</p><p>&nbsp;+ Term.LastTermVersion : Term [0..1]</p><p>&nbsp;+ AnalystElement.Version : integer</p><p>&nbsp;+ Goal.Version : integer</p><p>&nbsp;+ BusinessRule.Version : integer</p><p>&nbsp;</p><p><strong>Modelio 3.1 - 9019: 08/11/2013 :</strong></p><p>&nbsp;+ QueryDefinition#UsingAdditions</p><p>&nbsp;</p><p><strong>Modelio 3.1 - 9018: 11/10/2013 :</strong></p><p>&nbsp;Added matrixes metamodel</p><p>&nbsp;+ MatrixDefinition</p><p>&nbsp;+ MatrixValueDefinition</p><p>&nbsp;+ QueryDefinition</p><p>&nbsp;+ ExternProcessor</p><p>&nbsp;</p><p><strong>Modelio Phoenix 3.0 - 9017: 04/09/2013</strong></p><p>&nbsp;- Signal.BrowseBase</p><p>&nbsp;</p><p><strong>Modelio Phoenix 3.0 - 9016: 17/07/2013</strong></p><p>&nbsp;# LinkEnd.Opposite: is now shared composition</p><p>&nbsp;# LinkEnd.OppositeOwner: is no longer a shared composition</p><p>&nbsp;</p><p><strong>Modelio Phoenix - 9015: 14/06/2013</strong></p><p>&nbsp;#Stereotype.Label -&gt; Stereotype.LabelKey</p><p>&nbsp;#NoteType.Label -&gt; NoteType.LabelKey</p><p>&nbsp;#TagType.Label -&gt; TagType.LabelKey</p><p>&nbsp;#ExternDocumentType.Label -&gt; ExternDocumentType.LabelKey</p><p>&nbsp;- LocalNote</p><p>&nbsp;- LocalTaggedValue</p><p>&nbsp;</p><p><strong>Modelio Phoenix - 9014: 28/05/2013:</strong></p><p>&nbsp;- AnalystContainer.Type</p><p>&nbsp;</p><p><strong>Modelio Phoenix - 9013: 22/04/2013:</strong></p><p>&nbsp;- AnalystContainer.DefaultSet</p><p>&nbsp;# AnalystPropertyTable.Owner -&gt; AnalystPropertyTable.AnalystOwner</p><p>&nbsp;</p><p><strong>Modelio Phoenix - 9012: 12/04/2013:</strong></p><p>&nbsp;- AnalystProject.Represented</p><p>&nbsp;- Project.RootRequirement</p><p>&nbsp;# AnalystProject.DictionaryRoot set to *</p><p>&nbsp;# AnalystProject.RequirementRoot set to *</p><p>&nbsp;# AnalystProject.GoalRoot set to *</p><p>&nbsp;# AnalystProject.BusinessRuleRoot set to *</p><p>&nbsp;# Stereotype.Child is an Association instead of an Aggregation</p><p>&nbsp;</p><p><strong>Modelio Phoenix - 9011: 13/03/2013:</strong></p><p>&nbsp;+ Connector</p><p>&nbsp;</p><p><strong>Modelio Phoenix - 9010: 5/03/2013:</strong></p><p>&nbsp;#PropertyBaseType enumaration: added literals: Text, Unsigned, RichText, Date, Time, Element</p><p>&nbsp;</p><p><strong>Modelio Phoenix - 9009: 18/02/2013:</strong></p><p>&nbsp;# metamodel.uml.analyst : metamodel.analyst</p><p>&nbsp;+ AnalystElement</p><p>&nbsp;+ AnalystContainer</p><p>&nbsp;+ AnalystItem</p><p>&nbsp;+ BusinessRule</p><p>&nbsp;+ BusinessRuleContainer</p><p>&nbsp;+ Goal</p><p>&nbsp;+ GoalContainer</p><p>&nbsp;- Project.Installed</p><p>&nbsp;- RequirementElement</p><p>&nbsp;- DictionnaryElement</p><p>&nbsp;- Term.Definition</p><p>&nbsp;# RequirementElement.Text =&gt; AnalystItem.Definition</p><p>&nbsp;- Dictionary.Text</p><p>&nbsp;</p><p><strong>Modelio Phoenix - 9008: 31/01/2013:</strong></p><p>&nbsp;+ Association</p><p>&nbsp;+ CommunicationChannel</p><p>&nbsp;+ Link</p><p>&nbsp;+ AssociationEnd.Association</p><p>&nbsp;# ClassAssociation.AssociationPart: Association</p><p>&nbsp;# InformationFlow.RealizingLink: Link</p><p>&nbsp;# InformationFlow.Channel: Association</p><p>&nbsp;# InformationFlow.RealizingCommunication: CommunicationChannel</p><p>&nbsp;- NaryLink.Provider</p><p>&nbsp;- NaryLink.Consumer</p><p>&nbsp;+ NaryLinkEnd.Provider</p><p>&nbsp;+ NaryLinkEnd.Consumer</p><p>&nbsp;</p><p><strong>Modelio Phoenix - 9007: 21/11/2012:</strong></p><p>&nbsp; # AssociationEnd.Changeable -&gt; AssociationEnd.IsChangeable</p><p>&nbsp;</p><p><strong>Modelio Phoenix - 9006: 12/11/2012</strong></p><p>&nbsp;- Nary links:</p><p>&nbsp; + NaryLinkEnd</p><p>&nbsp; + NaryConnectorEnd</p><p>&nbsp; + Instance.OwnedNaryEnd</p><p>&nbsp; + ProvidedInterface.NaryConsumer</p><p>&nbsp; + RequiredInterface.NaryProvider</p><p>&nbsp; + NaryLink.NaryConsumer</p><p>&nbsp; + NaryLink.NaryProvider</p><p>&nbsp; + NaryLink.Sent</p><p>&nbsp; + NaryLink.RealizedInformationFlow</p><p>&nbsp; + CommunicationMessage.NaryChannel</p><p>&nbsp; # NaryLinkEnd.NaryLinkEnd</p><p>&nbsp; - LinkEnd.NaryLinkEnd</p><p>&nbsp; # Interaction is a CMS node</p><p>&nbsp;</p><p><strong>Modelio Phoenix - 9005: 07/11/2012</strong></p><p>&nbsp;- Property tables metamodel:</p><p>&nbsp;+ created infrastructure.properties package</p><p>&nbsp;+ PropertyTable ^ Element</p><p>&nbsp;+ PropertyBaseType enumeration (Boolean, Enumerate, Float, Integer, String)</p><p>&nbsp;+ PropertyDefinition.BaseType : PropertyBaseType</p><p>&nbsp;+ TypedPropertyTable ^ PropertyTable</p><p>&nbsp;+ LocalPropertyTable ^ PropertyTable: will replace LocalTaggedValue and LocalNote</p><p>&nbsp;+ ModuleComponent.DefinedPropertyType : PropertyType</p><p>&nbsp;+ Stereotype.DefinedTable : PropertyTableDefinition</p><p>&nbsp;+ MetaclassReference.DefinedTable : PropertyTableDefinition</p><p>&nbsp;- PropertyValue</p><p>&nbsp;# Property --&gt; PropertyDefinition</p><p>&nbsp;# PropertySet --&gt; PropertyTableDefinition</p><p>&nbsp;# PropertyValueSet -- &gt;AnalystPropertyTable , inherits from TypedPropertyTable</p><p>&nbsp;# AnalystProject.Properties --&gt; AnalystProject.PropertyRoot</p><p>&nbsp;# Moved :</p><p>&nbsp;&nbsp; - PropertyType :</p><p>&nbsp;&nbsp; - PropertyEnumerationLitteral :</p><p>&nbsp;&nbsp; - PropertyDefinition :</p><p>&nbsp;&nbsp; - EnumeratedPropertyType :</p><p>&nbsp;&nbsp; - PropertyTableDefinition :</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; from : reference.org.modelio.metamodel.uml.requirements</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; to : reference.org.modelio.metamodel.uml.infrastructure.properties</p><p>&nbsp;</p><p><strong>Modelio Phoenix - 9004: 19/10/2012</strong></p><p>&nbsp;+ Navigability eumeration</p><p>&nbsp;# AssociationEnd.Navigable -&gt; AssociationEnd.Navigability</p><p>&nbsp;# LinkEnd.IsNavigable -&gt; LinkEnd.Navigability</p><p>&nbsp;</p><p><strong>&nbsp;Modelio Phoenix - 9003: 18/10/2012</strong></p><p>&nbsp; - Stereotype.BaseClass -&gt; Class [0..1]</p><p>&nbsp; - MetaclassReference.Referenced -&gt; Class [0..1]</p><p>&nbsp; # AssociationEnd.Owner -&gt; AssociationEnd.Source</p><p>&nbsp; # LinkEnd.Linked -&gt; LinkEnd.Target</p><p>&nbsp; # LinkEnd.Instance -&gt; LinkEnd.Source</p><p>&nbsp; # LinkEnd.OppositeLinkEnd -&gt; LinkEnd.Opposite</p><p>&nbsp; # Instance.Conection -&gt; Instance.OwnedEnd</p><p>&nbsp; # Instance.TargetingLinkEnd -&gt; TargetingEnd</p><p>&nbsp;</p><p><strong>Modelio Phoenix - 9002: 18/09/2012</strong></p><p>&nbsp; # Module renamed ModuleComponent</p><p>&nbsp; # SoModuleState renamed ModuleState</p><p>&nbsp; # ConfigParam renamed ModuleParameter</p><p>&nbsp; # ConfigParamType renamed ModuleParameterType</p><p>&nbsp; - SoVisibilityfilter</p><p>&nbsp;</p><p><strong>Modelio Phoenix - 9001: 09/2012</strong></p><p>&nbsp; - DiagramContainer</p><p>&nbsp; # Project (diagramRoot is now a DiagramSet)</p><p>&nbsp; # ModelElement (model elements are now the owners of diagrams)</p><p>&nbsp;</p><p><strong>Modelio Phoenix - 9000 :&nbsp; 02/2012</strong></p><p>&nbsp; - Association</p><p>&nbsp; - Link</p><p>&nbsp; - CommunicationChannel</p><p>&nbsp; + AssociationEnd.opposite -&gt; AssociationEnd [0..1]</p><p>&nbsp; + AssociationEnd.target -&gt; Classifier [0..1]</p><p>&nbsp; - AssociationEnd.Connection -&gt; Association</p><p>&nbsp; + StruturalFeature</p><p>&nbsp; + BehavioralFeature</p><p>&nbsp; + NaryAssociationEnd</p><p>&nbsp; + NaryAssociation</p><p>&nbsp; # AssociationEnd (=&gt; redesigned)</p><p>&nbsp; # LinkEnd (=&gt; redesigned)</p><p>&nbsp;</p><p><strong>Modelio 2.2 - 8020 :&nbsp; 24/04/2012</strong></p><p>&nbsp; + Port.PortDirection : PortOrientation [1..1]</p><p>&nbsp; + PortOrientation enumeration { PortNone, PortIn, PortOut, PortInOut}</p><p>&nbsp;</p><p><strong>Modelio 2.1 - 8008 :&nbsp; 02/11/2011</strong></p><p>&nbsp; + ExternDocumentType ( copy of NoteType + Icon : string [0..1] + Image : string [0..1] )</p><p>&nbsp; + ExternDocument.Type : ExternDocumentType [1..1]</p><p>&nbsp;</p><p><strong>Modelio 2.1 - 8007 : &gt; 25/10/2011</strong></p><p>&nbsp; - LinkTextType enumeration</p><p>&nbsp;</p><p>&nbsp; - NoteType.Tools : string</p><p>&nbsp; - NoteType.Format : string</p><p>&nbsp; - Stereotype.SmallIcon : string</p><p>&nbsp; - Stereotype.dynamicMetaClass : boolean</p><p>&nbsp;</p><p>&nbsp; + ExternDocument ^ ModelElement</p><p>&nbsp; + CompositeStructureDiagram ^ StaticDiagram</p><p>&nbsp;</p><p>&nbsp; + ModelElement.Document : ExternDocument[*]</p><p>&nbsp; + Constraint.Language : string</p><p>&nbsp; + Port.IsConjugated : boolean</p><p>&nbsp; + Parameter.IsOrdered : boolean</p><p>&nbsp; + Parameter.IsUnique : boolean</p><p>&nbsp;</p><p>&nbsp; + BpmnItemAwareElement.Type : GeneralClass</p><p>&nbsp; + BpmnItemAwareElement.InState : State</p><p>&nbsp; + BpmnMessage.Type : GeneralClass</p><p>&nbsp; + BpmnMessage.InState : State</p><p>&nbsp; + BpmnServiceTask.CalledOperation : Operation</p><p>&nbsp; + BpmnSendTask.CalledOperation : Operation</p><p>&nbsp; + BpmnReceiveTask.CalledOperation : Operation</p><p>&nbsp;</p><p>&nbsp; # BehaviorParameter.Effect, IsException, IsStream ==&gt; moved on Parameter</p><p>&nbsp;</p><p>&nbsp; JNI glue : trying to support blob attributes into string attributes.</p><p>&nbsp;</p><p><strong>Modelio 2.0 - 8006 : &nbsp;&nbsp;29/04/2011</strong></p><p>&nbsp;+ metamodel.newDiagramsModel.ClassDiagram</p><p>&nbsp;+ metamodel.newDiagramsModel.CommunicationDiagram</p><p>&nbsp;+ metamodel.newDiagramsModel.DeploymentDiagram</p><p>&nbsp;+ metamodel.newDiagramsModel.ObjectDiagram</p><p>&nbsp;+ metamodel.newDiagramsModel.StateMachineDiagram</p><p>&nbsp;+ metamodel.newDiagramsModel.UseCaseDiagram</p><p>&nbsp;- metamodel.uml.behaviorModel.stateMachineModel.PseudoState</p><p>&nbsp;- metamodel.uml.behaviorModel.stateMachineModel.UML2PseudoState</p><p>&nbsp;</p><p>&nbsp;JONI API modifications:</p><p>&nbsp; All IXxxx metamodel java interfaces have been moved into a hierarchy of packages according to their diagram types</p><p>&nbsp;</p><p><strong>Modelio 2.0 - 8005 : </strong></p><p>+ StateMachine.EntryPoint : EntryPointPseudoState [*]</p><p>+ StateMachine.ExitPoint : ExitPointPseudoState [*]</p><p>&nbsp;</p><p><strong>Modelio 2.0 - 8004 : </strong></p><p>- InternalProduct</p><p>- ExternalProduct</p><p>+ DiagramContainer</p><p>+ DiagramSet</p><p>+ Project.DiagramRoot [1]: DiagramContainer</p><p>+ AbstractDiagram is now owned by the Diagramcontainer</p><p>+ AbstractDiagram.Owner [1] : DiagramContainer</p><p>+ AbstractDiagram.ReferencingSet[*] :DiagramSet</p><p># AbstractDiagram derives from ModelElement instead of InternalProduct</p><p>&nbsp;</p><p><strong>Modelio 2.0 - 8003 : </strong></p><p>+ Added BPMN metamodel</p><p>- BPMNEvent</p><p>- Artifact.Obsolete_Resident : ModelElement</p><p>&nbsp;</p><p><strong>Modelio 2.0 - 8002 : </strong></p><p># ValuePin inherits from InputPin instead of Pin</p><p>&nbsp;</p><p><strong>Modelio 2.0 - 8001 : </strong></p><p>+ ExpansionNode</p><p>+ ExpansionRegion</p><p>+ ExceptionHandler</p><p>+ ValuePin</p><p>&nbsp;</p><p><strong>Modelio 1.2 - 7008 : </strong></p><p>No change, just to force modules to update.</p><p>&nbsp;</p><p><strong>Modelio 1.1.1 &amp; 1.2 - 7007 :</strong></p><p>+ PropertyContainer ^ ModelElement, CMS node</p><p># RequirementProject --&gt; AnalystProject</p><p># RequirementProject.DefinedPropertySet --&gt; PropertyContainer.DefinedPropertySet</p><p># RequirementProject.DefinedPropartyType --&gt; PropertyContainer.DefinedPropertyType</p><p>+ AnalystProject.Properties : PropertyContainer</p><p>+ AnalystProject.RootGoal : RequirementContainer</p><p>+ AnalystProject.RootBusinessRule : RequirementContainer</p><p>&nbsp;</p><p><strong>Modelio 1.1 - 7006 : </strong></p><p>+ NoteType.Label : String</p><p>+ TagType.Label : string</p><p>&nbsp;</p><p><strong>Modelio 1.1 - 7005 :</strong></p><p>deleted the SO model and generation product model</p><p>- SoPointOfView, SoTextType, SoSpecDirective, SoStereotype, SoClass, ...</p><p>- SoElement, SoConfiguration, SoProject, SoSite, So....&nbsp;&nbsp;&nbsp;&nbsp; only SoBase remains</p><p>- Document</p><p>-&nbsp;MpGenProduct, ReservedClass, Reserved**</p><p># SoModule ---&gt; Module</p><p># PointOfView ---&gt; Profile</p><p># Stereotype ^ ModelElement ---&gt; Stereotype ^ ModelElement</p><p># SoBase.project : SoProject ---&gt; SoBase.project : Project</p><p># Stereotype.Context : PointOfView ---&gt; Stereotype.Owner : Profile</p><p>- Stereotype.StereotypeConstraint : Constraint</p><p>- Stereotype.RequiredNote : NoteType</p><p>- Stereotype.RequiredTag : TagType</p><p>+ Stereotype.DefinedTagType : TagType [*]</p><p>+ Stereotype.DefinedNoteType : NoteType [*]</p><p>- TagType.BaseClass:String ---&gt; TagType.OwnerProfile[0..1] + TagType.OwnerReference : MetaclassReference[0..1]</p><p>- TagType.Context : PointOfView</p><p># TagType.Attributed : Stereotype ---&gt; TagType.OwnerStereotype[0..1] + TagType.OwnerReference : MetaclassReference[0..1]</p><p>- NoteType.BaseClass : String ---&gt; NoteType.OwnerProfile[0..1] + NoteType.OwnerReference : MetaclassReference[0..1]</p><p>- NoteType.Context : PointOfView</p><p># NoteType.Attributed : Stereotype ---&gt; NoteType.OwnerStereotype[0..1] + NoteType.OwnerReference : MetaclassReference[0..1]</p><p>- Module.Name ---&gt; ModelElement.Name</p><p>- Module.moduleIhmName : string</p><p>- Module.moduleDescr : string</p><p>- Module.SofteamModule : boolean</p><p>- Module.hideParents : boolean</p><p>- Module.userData : string</p><p>- Module.moduleContext : string</p><p># Module.modularViewPoint :SoPointOfView --&gt; Module.OwnedProfile : Profile [*]</p><p>- Project.Selected : PointOfView</p><p>+Project.Installed : Module</p><p>+MetaclassReference</p><p>+MetaclassReference.DefinedNoteType : NoteType [*]</p><p>+MetaclassReference.DefinedTagType : TagType [*]</p><p>+Profile.OwnedReference : MetaclassReference[*]</p><p>&nbsp;</p><p><strong>Modelio 1.0 - 7004 :</strong></p><p>SoModule state</p><p>+ SoModule.State : ModuleState (enum)</p><p>- SoModule.TargetReceiverBase : string</p><p>&nbsp;</p><p><strong>Hades - 7003 :</strong></p><p>State machine changes:</p><p>+ Region</p><p>- StateMachine.TopStates : State</p><p>- StateMachine.TopPseudoStates : AbstractPseudoState</p><p># StateMachine.Top : State --&gt; StateMachine.Top : Region</p><p># State.Sub : State ---&gt; State.??? : Region</p><p>- State.pseudoParent : StateMachine</p><p># State.Represented ---&gt; Region.Represented : StateMachine</p><p>- State.Part : AbstractPseudoState : merged with Sub : State into Region.Sub : StateVertex</p><p>+ State.EntryPoint : EntryPointPseudoState</p><p>+ State.ExistPoint : ExitPointPseudoState</p><p>&nbsp;</p><p><strong>Hades - 7002 : 25/07/2008 :</strong></p><p>State machine model changes + addons on Stereotype</p><p>+ new &#39;FinalState&#39; metaclass</p><p>+ new &#39;Stereotype.Label: String&#39; attribute</p><p>+ new &#39;SoStereotype.Label: String&#39; attribute</p><p>- deleted &#39;Condition&#39; metaclass : associations to condition have been replaced by String attributes</p><p># &#39;Transition.Guard : Condition&#39; association replaced by &#39;Transition.Guard : String&#39; attribute</p><p># &#39;Transition.Post: Condition&#39; association replaced by &#39;Transition.PostCondition: String&#39; attribute</p><p># &#39;ConnectionPointReference.Entry&#39; cardinality changed from &#39;*&#39; to &#39;0..1&#39;</p><p># &#39;ConnectionPointReference.Exit&#39; cardinality changed from &#39;*&#39; to &#39;0..1&#39;</p><p># &#39;EntryPointPseudoState.Connection&#39; cardinality changed from &#39;0..1&#39; to &#39;*&#39;</p><p># &#39;ExitPointPseudoState.Connection&#39; cardinality changed from &#39;0..1&#39; to &#39;*&#39;</p><p># &#39;StateKind.FinalState&#39; enumeration value renamed &#39;StateKind.OldFinalState&#39; to avoid name collisions</p><p>&nbsp;</p><p><strong>Hades - 7001 :</strong></p><p>Identifiers are now 128 bits UUID, no more site number, no more project counter, no more duplicate identifier :-)))</p><p># SsObject.MSiteIdent : SmInt64 ---&gt; SsObject.MSiteIdent : TUUID</p><p>- SsObject.RootIdent : SmInt64</p><p>- SmIdentifier</p><p>- SeObjectNum</p><p>- *.SiteIdentifier : SmIdentifier</p><p>&nbsp;</p><p><strong>6.1&nbsp;&nbsp;&nbsp; - 6208: 14/03/2008 : </strong></p><p># InformationFlow.RealizingCommunicationMessage : CommunicationMessage ----&gt; InformationFlow.RealizingCommunicationChannel : CommunicationChannel</p><p>&nbsp;</p><p><strong>6.1&nbsp;&nbsp;&nbsp; - 6207: 13/12/2007 :</strong></p><p>Separate metamodel for communication diagrams:</p><p>- Message.Channel</p><p>- Message.SequenceNumber</p><p>+ CommunicationInteraction</p><p>+ CommunicationNode</p><p>+ CommunicationChannel</p><p>+ CommunicationMessage</p><p>&nbsp;</p><p><strong>6.1&nbsp;&nbsp;&nbsp; - 6206: 11/12/2007 : </strong></p><p># Message.Channel : Connector ---&gt; Message.Channel : Link</p><p>&nbsp;</p><p><strong>6.1&nbsp;&nbsp;&nbsp; - 6205: ~??/09/2007 :</strong></p><p>VERSION ROLLBACKED TO 6.1 thanks to &quot;Comit&eacute; de Pilotage Produit&quot;</p><p>&nbsp;</p><p><strong>6.2&nbsp;&nbsp;&nbsp; - 6205: 11/06/2007 :</strong></p><p>+ Added SequenceDiagram</p><p>+ InteractionFragment.LineNumber</p><p>+ InteractionUse.EndLineNumber</p><p>+ InteractionOperand.EndLineNumber</p><p>+ StateInvariant.EndLineNumber</p><p>- ActivityGroup.ContainedNode : ActivityNode</p><p>+ Partition.ContainedNode : ActivityNode(composition)</p><p>+ ActivityDiagram.IsVertical : boolean = false</p><p># ActivityNode.ContainerGroup : ActivityGroup[*] ---&gt; ActivityNode.OwnerPartition : Partition [0..1]</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><p><strong>6.2&nbsp;&nbsp;&nbsp; - 6205: 11/06/2007 :</strong></p><p>VERSION OFFICIALLY CHANGED TO 6.2.00</p><p>TOOL_VERSION is no more defined in obasic but in the makefile</p><p>&nbsp;</p><p><strong>6.1&nbsp;&nbsp;&nbsp; - 6204: 16/05/2007 :</strong></p><p># Clause was abstract, made it concrete (no dictionary change)</p><p>&nbsp;</p><p><strong>6.1&nbsp;&nbsp;&nbsp; - 6204: 10/05/2007 :</strong></p><p># ForkNode &amp; JoinNode merged into ForkJoinNode</p><p># DecisionNode &amp; MergeNode merged into DecisionMergeNode</p><p>&nbsp;</p><p><strong>6.1&nbsp;&nbsp;&nbsp; - 6203: 09/05/2007 :</strong></p><p>+ InputPin</p><p>+ OutputPin</p><p># Pin.Value moved on InputPin</p><p># Pin.IsSelf moved on InputPin</p><p># Pin.Inputing : ActivityAction moved on InputPin</p><p># Pin.Outputing : ActivityAction moved on OutputPin</p><p>- ActivityParameterNode.LinkedParameter : BehaviorParameter , was duplicate of ObjectNode.RepresentedRealParameter:BehaviorParameter</p><p>&nbsp;</p><p><strong>6.1&nbsp;&nbsp;&nbsp; - 6202: 06/04/2007 :</strong></p><p>+ Clause</p><p>+ Clause.Body : ActivityNode</p><p>+ ConditionalNode.OwnedClause</p><p>- ConditionalNode.Test</p><p>+ ObjectNode.RepresentedAttribute</p><p>+ ObjectNode.RepresentedRole</p><p>- InstanceNode.Object</p><p>+ Behavior.OwnedCollaboration : Collaboration</p><p>&nbsp;</p><p><strong>6.1&nbsp;&nbsp;&nbsp; - 6201: 02/04/2007 :</strong></p><p>- AcceptEventAction</p><p>+ AcceptChangeEventAction</p><p>+ AcceptTimeEventAction</p><p>+ AcceptSignalAction</p><p>+ AcceptCallEventAction</p><p>&nbsp;</p><p><strong>6.1 RIO - 6200: 29/03/2007 :</strong></p><p>First time MM version is changed for RIO although MM changes have already been commited.</p><p>Official tool version has not been yet changed.</p><p>+ Activity, sequence, information and state machine model</p><p>&nbsp;</p><p><strong>6.1&nbsp;&nbsp;&nbsp; - 6104: 29/08/2006 :</strong></p><p>BindableInstance.RepresentedFeature cardinality changed from 0..* to 0..1</p><p>&nbsp;</p><p><strong>6.1&nbsp;&nbsp;&nbsp; - 6103: 28/08/2006 : </strong></p><p>added SoModule.DependsOn --&gt; SoModule.Impacted dependency</p><p>&nbsp;</p><p><strong>6.1&nbsp;&nbsp;&nbsp; - 6102: 16/06/2006 :</strong></p><p>added SoModule.javaClassName attribute</p><p>&nbsp;</p><p><strong>6.1&nbsp;&nbsp;&nbsp; - 6101: 06/06/2006 :</strong></p><p>Remove Communication (replaced by Association &amp; AssociationEnd), XSLT migration necessary</p><p>&nbsp;</p><p><strong>6.1&nbsp;&nbsp;&nbsp; - 6100: 17/05/2006 :</strong></p><p>Remove SoMetaProject (and some others in the Soxxx model (smOutil) )</p><p>&nbsp;</p><p><strong>6.0&nbsp;&nbsp;&nbsp; - 612 : 18/01/2006 :</strong></p><p>RequiredInterface.Required --&gt; RequiredInterface.RequiredElement ( was unusable with J)</p><p>ProvidedInterface.Provided --&gt; ProvidedInterface.ProvidedElement</p><p>&nbsp;</p><p><strong>6.0&nbsp;&nbsp;&nbsp; - 611 : 23/11/2005:</strong></p><p>Port didn&#39;t inherit from BindableInstance</p><p>&nbsp;</p><p><strong>6.0&nbsp;&nbsp;&nbsp; - 610 : 18/11/2005:</strong></p><p>+BindableInstance,</p><p>Instance contiennent des BindableInstance,</p><p>Port herite de BindableInstance,</p><p>NameSpaceUse heritera de ModelElement</p><p>Classifier.InternalStructure-&gt;BindableInstance</p><p>&nbsp;</p><p><strong>6.0&nbsp;&nbsp;&nbsp; - 609 : 08/11/2005:</strong></p><p>+NamespaceUse (blue link), +Usage</p><p>&nbsp;</p><p><strong>6.0&nbsp;&nbsp;&nbsp; - 608 : 28/09/2005:</strong></p><p>+StaticDiagram.pdeProperties</p><p>&nbsp;</p><p><strong>6.0&nbsp;&nbsp;&nbsp; - 607 :</strong></p><p>?????????</p><p>&nbsp;</p><p><strong>6.0&nbsp;&nbsp;&nbsp; - 606 :</strong></p><p>Classifier.InternalStructure : Collaboration</p><p>---&gt; Classifier.InternalStructure : Instance (15/09/2005)</p><p>&nbsp;</p><p><strong>6.0&nbsp;&nbsp;&nbsp; - 605 :</strong></p><p>added links from LinkEnd to RequiredInterface &amp;ProvidedInterface (15/09/2005)</p><p>&nbsp;</p><p><strong>5.5.01 - 604 :</strong></p><p>added multi stereotype support</p><p>&nbsp;</p><p><strong>5.5.01 - 603 :</strong></p><p>renamed SoBase::filename to SoBase::stamp</p><p>&nbsp;</p><p><strong>5.5.01 - 602 :</strong></p><p>SoStereotype &amp; Stereotype attributes added (J metaclasses for stereotypes)</p><p>&nbsp;</p><p><strong>5.5.01 - 601 :</strong></p><p>destruction Note.Annotation-&gt;TaggedValue &amp; Note.LocalAnnotation obsoletes (27/05/2005)</p><p>&nbsp;</p><p><strong>5.5.01 - 600 :</strong></p><p>ajout des classes&nbsp; &quot;RaisedException&quot;, &quot;RequiredInterface&quot;, &quot;ProvidedInterface&quot;.</p><p>&quot;Note&quot; promu &quot;ModelElement&quot;.</p><p>&nbsp;</p><p><strong>5.5.01 - 1&nbsp;&nbsp; :</strong></p><p>1er metamodele UML2</p>
 */
@objid ("fb2732d1-2d05-4208-8d82-1b793d28be42")
public final class StandardMetamodelFragment extends AbstractMetamodelFragment {
    @objid ("84065cd5-d1fe-47d9-a9f0-db97ff3362cb")
    private static final StandardMetamodelFragment INSTANCE = new StandardMetamodelFragment();

    @objid ("353f05a5-8b70-4d3c-8be0-7dfba0aef8f3")
    public static ISmMetamodelFragment getInstance() {
        return INSTANCE;
    }

    @objid ("19d61fe8-0d00-4d02-9bc6-1ba5b1eb4026")
    @Override
    public final MExpert createMExpert(SmMetamodel mm) {
        return new StandardMetamodelExpert(mm);
    }

    @objid ("b3b4085a-941c-4ca0-886b-ccc9de3e2d24")
    @Override
    public Collection<VersionedItem<MMetamodelFragment>> getNeededFragments() {
        Collection<VersionedItem<MMetamodelFragment>> ret = new ArrayList<>(super.getNeededFragments());
        
        ret.add(new VersionedItem<MMetamodelFragment>("Infrastructure", new Version("2.1.02")));
        
        return ret;
        
    }

    @objid ("cb510a81-e1bf-408d-ab82-0f8055cd6de4")
    public  StandardMetamodelFragment() {
        	super("Standard", new Version("2.3.00"), "Modeliosoft", "3.8.00");
        
    }

    @objid ("dded89a2-cd59-442d-a497-3408e1193b68")
    @Override
    public List<SmClass> createMetaclasses() {
        	final List<SmClass> metaclasses = new ArrayList<>(274);
        	metaclasses.add(new BpmnBaseElementSmClass(this));
        	metaclasses.add(new BpmnComplexBehaviorDefinitionSmClass(this));
        	metaclasses.add(new BpmnLoopCharacteristicsSmClass(this));
        	metaclasses.add(new BpmnMultiInstanceLoopCharacteristicsSmClass(this));
        	metaclasses.add(new BpmnStandardLoopCharacteristicsSmClass(this));
        	metaclasses.add(new BpmnOperationSmClass(this));
        	metaclasses.add(new BpmnEventDefinitionSmClass(this));
        	metaclasses.add(new BpmnCancelEventDefinitionSmClass(this));
        	metaclasses.add(new BpmnCompensateEventDefinitionSmClass(this));
        	metaclasses.add(new BpmnConditionalEventDefinitionSmClass(this));
        	metaclasses.add(new BpmnErrorEventDefinitionSmClass(this));
        	metaclasses.add(new BpmnEscalationEventDefinitionSmClass(this));
        	metaclasses.add(new BpmnLinkEventDefinitionSmClass(this));
        	metaclasses.add(new BpmnMessageEventDefinitionSmClass(this));
        	metaclasses.add(new BpmnSignalEventDefinitionSmClass(this));
        	metaclasses.add(new BpmnTerminateEventDefinitionSmClass(this));
        	metaclasses.add(new BpmnTimerEventDefinitionSmClass(this));
        	metaclasses.add(new BpmnMessageFlowSmClass(this));
        	metaclasses.add(new BpmnDataAssociationSmClass(this));
        	metaclasses.add(new BpmnDataStateSmClass(this));
        	metaclasses.add(new BpmnSequenceFlowDataAssociationSmClass(this));
        	metaclasses.add(new BpmnLaneSmClass(this));
        	metaclasses.add(new BpmnLaneSetSmClass(this));
        	metaclasses.add(new BpmnParticipantSmClass(this));
        	metaclasses.add(new BpmnResourceParameterSmClass(this));
        	metaclasses.add(new BpmnResourceParameterBindingSmClass(this));
        	metaclasses.add(new BpmnResourceRoleSmClass(this));
        	metaclasses.add(new BpmnArtifactSmClass(this));
        	metaclasses.add(new BpmnAssociationSmClass(this));
        	metaclasses.add(new BpmnGroupSmClass(this));
        	metaclasses.add(new BpmnFlowElementSmClass(this));
        	metaclasses.add(new BpmnSequenceFlowSmClass(this));
        	metaclasses.add(new BpmnItemAwareElementSmClass(this));
        	metaclasses.add(new BpmnDataInputSmClass(this));
        	metaclasses.add(new BpmnDataObjectSmClass(this));
        	metaclasses.add(new BpmnDataOutputSmClass(this));
        	metaclasses.add(new BpmnDataStoreSmClass(this));
        	metaclasses.add(new BpmnFlowNodeSmClass(this));
        	metaclasses.add(new BpmnActivitySmClass(this));
        	metaclasses.add(new BpmnCallActivitySmClass(this));
        	metaclasses.add(new BpmnSubProcessSmClass(this));
        	metaclasses.add(new BpmnAdHocSubProcessSmClass(this));
        	metaclasses.add(new BpmnTransactionSmClass(this));
        	metaclasses.add(new BpmnTaskSmClass(this));
        	metaclasses.add(new BpmnBusinessRuleTaskSmClass(this));
        	metaclasses.add(new BpmnManualTaskSmClass(this));
        	metaclasses.add(new BpmnReceiveTaskSmClass(this));
        	metaclasses.add(new BpmnScriptTaskSmClass(this));
        	metaclasses.add(new BpmnSendTaskSmClass(this));
        	metaclasses.add(new BpmnServiceTaskSmClass(this));
        	metaclasses.add(new BpmnUserTaskSmClass(this));
        	metaclasses.add(new BpmnEventSmClass(this));
        	metaclasses.add(new BpmnCatchEventSmClass(this));
        	metaclasses.add(new BpmnBoundaryEventSmClass(this));
        	metaclasses.add(new BpmnIntermediateCatchEventSmClass(this));
        	metaclasses.add(new BpmnStartEventSmClass(this));
        	metaclasses.add(new BpmnThrowEventSmClass(this));
        	metaclasses.add(new BpmnEndEventSmClass(this));
        	metaclasses.add(new BpmnImplicitThrowEventSmClass(this));
        	metaclasses.add(new BpmnIntermediateThrowEventSmClass(this));
        	metaclasses.add(new BpmnGatewaySmClass(this));
        	metaclasses.add(new BpmnComplexGatewaySmClass(this));
        	metaclasses.add(new BpmnEventBasedGatewaySmClass(this));
        	metaclasses.add(new BpmnExclusiveGatewaySmClass(this));
        	metaclasses.add(new BpmnInclusiveGatewaySmClass(this));
        	metaclasses.add(new BpmnParallelGatewaySmClass(this));
        	metaclasses.add(new BpmnSharedElementSmClass(this));
        	metaclasses.add(new BpmnEndPointSmClass(this));
        	metaclasses.add(new BpmnInterfaceSmClass(this));
        	metaclasses.add(new BpmnMessageSmClass(this));
        	metaclasses.add(new BpmnItemDefinitionSmClass(this));
        	metaclasses.add(new BpmnResourceSmClass(this));
        	metaclasses.add(new BehaviorDiagramSmClass(this));
        	metaclasses.add(new BpmnProcessCollaborationDiagramSmClass(this));
        	metaclasses.add(new BpmnProcessDesignDiagramSmClass(this));
        	metaclasses.add(new BpmnCollaborationDiagramSmClass(this));
        	metaclasses.add(new BpmnSubProcessDiagramSmClass(this));
        	metaclasses.add(new ActivityDiagramSmClass(this));
        	metaclasses.add(new CommunicationDiagramSmClass(this));
        	metaclasses.add(new SequenceDiagramSmClass(this));
        	metaclasses.add(new StateMachineDiagramSmClass(this));
        	metaclasses.add(new StaticDiagramSmClass(this));
        	metaclasses.add(new ClassDiagramSmClass(this));
        	metaclasses.add(new CompositeStructureDiagramSmClass(this));
        	metaclasses.add(new DeploymentDiagramSmClass(this));
        	metaclasses.add(new ObjectDiagramSmClass(this));
        	metaclasses.add(new UseCaseDiagramSmClass(this));
        	metaclasses.add(new GeneralOrderingSmClass(this));
        	metaclasses.add(new AbstractionSmClass(this));
        	metaclasses.add(new ElementRealizationSmClass(this));
        	metaclasses.add(new UmlModelElementSmClass(this));
        	metaclasses.add(new ActivityEdgeSmClass(this));
        	metaclasses.add(new ControlFlowSmClass(this));
        	metaclasses.add(new MessageFlowSmClass(this));
        	metaclasses.add(new ObjectFlowSmClass(this));
        	metaclasses.add(new ActivityGroupSmClass(this));
        	metaclasses.add(new ActivityPartitionSmClass(this));
        	metaclasses.add(new InterruptibleActivityRegionSmClass(this));
        	metaclasses.add(new ActivityNodeSmClass(this));
        	metaclasses.add(new ActivityActionSmClass(this));
        	metaclasses.add(new AcceptCallEventActionSmClass(this));
        	metaclasses.add(new AcceptChangeEventActionSmClass(this));
        	metaclasses.add(new AcceptSignalActionSmClass(this));
        	metaclasses.add(new AcceptTimeEventActionSmClass(this));
        	metaclasses.add(new CallActionSmClass(this));
        	metaclasses.add(new CallBehaviorActionSmClass(this));
        	metaclasses.add(new CallOperationActionSmClass(this));
        	metaclasses.add(new OpaqueActionSmClass(this));
        	metaclasses.add(new SendSignalActionSmClass(this));
        	metaclasses.add(new StructuredActivityNodeSmClass(this));
        	metaclasses.add(new ConditionalNodeSmClass(this));
        	metaclasses.add(new ExpansionRegionSmClass(this));
        	metaclasses.add(new LoopNodeSmClass(this));
        	metaclasses.add(new ControlNodeSmClass(this));
        	metaclasses.add(new DecisionMergeNodeSmClass(this));
        	metaclasses.add(new FinalNodeSmClass(this));
        	metaclasses.add(new ActivityFinalNodeSmClass(this));
        	metaclasses.add(new FlowFinalNodeSmClass(this));
        	metaclasses.add(new ForkJoinNodeSmClass(this));
        	metaclasses.add(new InitialNodeSmClass(this));
        	metaclasses.add(new ObjectNodeSmClass(this));
        	metaclasses.add(new ActivityParameterNodeSmClass(this));
        	metaclasses.add(new CentralBufferNodeSmClass(this));
        	metaclasses.add(new DataStoreNodeSmClass(this));
        	metaclasses.add(new ExpansionNodeSmClass(this));
        	metaclasses.add(new InstanceNodeSmClass(this));
        	metaclasses.add(new PinSmClass(this));
        	metaclasses.add(new InputPinSmClass(this));
        	metaclasses.add(new ValuePinSmClass(this));
        	metaclasses.add(new OutputPinSmClass(this));
        	metaclasses.add(new ClauseSmClass(this));
        	metaclasses.add(new ExceptionHandlerSmClass(this));
        	metaclasses.add(new BehaviorSmClass(this));
        	metaclasses.add(new BpmnCollaborationSmClass(this));
        	metaclasses.add(new BpmnProcessSmClass(this));
        	metaclasses.add(new BpmnSharedDefinitionsSmClass(this));
        	metaclasses.add(new ActivitySmClass(this));
        	metaclasses.add(new OpaqueBehaviorSmClass(this));
        	metaclasses.add(new CommunicationInteractionSmClass(this));
        	metaclasses.add(new InteractionSmClass(this));
        	metaclasses.add(new StateMachineSmClass(this));
        	metaclasses.add(new EventSmClass(this));
        	metaclasses.add(new CommunicationChannelSmClass(this));
        	metaclasses.add(new CommunicationMessageSmClass(this));
        	metaclasses.add(new CommunicationNodeSmClass(this));
        	metaclasses.add(new InteractionFragmentSmClass(this));
        	metaclasses.add(new CombinedFragmentSmClass(this));
        	metaclasses.add(new ExecutionSpecificationSmClass(this));
        	metaclasses.add(new InteractionOperandSmClass(this));
        	metaclasses.add(new InteractionUseSmClass(this));
        	metaclasses.add(new PartDecompositionSmClass(this));
        	metaclasses.add(new OccurrenceSpecificationSmClass(this));
        	metaclasses.add(new MessageEndSmClass(this));
        	metaclasses.add(new ExecutionOccurenceSpecificationSmClass(this));
        	metaclasses.add(new TerminateSpecificationSmClass(this));
        	metaclasses.add(new GateSmClass(this));
        	metaclasses.add(new StateInvariantSmClass(this));
        	metaclasses.add(new LifelineSmClass(this));
        	metaclasses.add(new MessageSmClass(this));
        	metaclasses.add(new RegionSmClass(this));
        	metaclasses.add(new StateVertexSmClass(this));
        	metaclasses.add(new AbstractPseudoStateSmClass(this));
        	metaclasses.add(new ChoicePseudoStateSmClass(this));
        	metaclasses.add(new DeepHistoryPseudoStateSmClass(this));
        	metaclasses.add(new EntryPointPseudoStateSmClass(this));
        	metaclasses.add(new ExitPointPseudoStateSmClass(this));
        	metaclasses.add(new ForkPseudoStateSmClass(this));
        	metaclasses.add(new InitialPseudoStateSmClass(this));
        	metaclasses.add(new JoinPseudoStateSmClass(this));
        	metaclasses.add(new JunctionPseudoStateSmClass(this));
        	metaclasses.add(new ShallowHistoryPseudoStateSmClass(this));
        	metaclasses.add(new TerminatePseudoStateSmClass(this));
        	metaclasses.add(new ConnectionPointReferenceSmClass(this));
        	metaclasses.add(new StateSmClass(this));
        	metaclasses.add(new FinalStateSmClass(this));
        	metaclasses.add(new TransitionSmClass(this));
        	metaclasses.add(new InternalTransitionSmClass(this));
        	metaclasses.add(new ExtensionPointSmClass(this));
        	metaclasses.add(new UseCaseDependencySmClass(this));
        	metaclasses.add(new DataFlowSmClass(this));
        	metaclasses.add(new InformationFlowSmClass(this));
        	metaclasses.add(new ConstraintSmClass(this));
        	metaclasses.add(new DurationConstraintSmClass(this));
        	metaclasses.add(new ModelTreeSmClass(this));
        	metaclasses.add(new NameSpaceSmClass(this));
        	metaclasses.add(new ClassifierSmClass(this));
        	metaclasses.add(new InformationItemSmClass(this));
        	metaclasses.add(new ArtifactSmClass(this));
        	metaclasses.add(new GeneralClassSmClass(this));
        	metaclasses.add(new SignalSmClass(this));
        	metaclasses.add(new ActorSmClass(this));
        	metaclasses.add(new UseCaseSmClass(this));
        	metaclasses.add(new ClassSmClass(this));
        	metaclasses.add(new ComponentSmClass(this));
        	metaclasses.add(new DataTypeSmClass(this));
        	metaclasses.add(new EnumerationSmClass(this));
        	metaclasses.add(new InterfaceSmClass(this));
        	metaclasses.add(new TemplateParameterSmClass(this));
        	metaclasses.add(new NodeSmClass(this));
        	metaclasses.add(new CollaborationSmClass(this));
        	metaclasses.add(new PackageSmClass(this));
        	metaclasses.add(new SubstitutionSmClass(this));
        	metaclasses.add(new AssociationSmClass(this));
        	metaclasses.add(new AttributeLinkSmClass(this));
        	metaclasses.add(new BindingSmClass(this));
        	metaclasses.add(new ClassAssociationSmClass(this));
        	metaclasses.add(new CollaborationUseSmClass(this));
        	metaclasses.add(new ComponentRealizationSmClass(this));
        	metaclasses.add(new ElementImportSmClass(this));
        	metaclasses.add(new EnumerationLiteralSmClass(this));
        	metaclasses.add(new FeatureSmClass(this));
        	metaclasses.add(new BehavioralFeatureSmClass(this));
        	metaclasses.add(new OperationSmClass(this));
        	metaclasses.add(new StructuralFeatureSmClass(this));
        	metaclasses.add(new AssociationEndSmClass(this));
        	metaclasses.add(new AttributeSmClass(this));
        	metaclasses.add(new NaryAssociationEndSmClass(this));
        	metaclasses.add(new GeneralizationSmClass(this));
        	metaclasses.add(new InstanceSmClass(this));
        	metaclasses.add(new BindableInstanceSmClass(this));
        	metaclasses.add(new PortSmClass(this));
        	metaclasses.add(new InterfaceRealizationSmClass(this));
        	metaclasses.add(new LinkSmClass(this));
        	metaclasses.add(new ConnectorSmClass(this));
        	metaclasses.add(new LinkEndSmClass(this));
        	metaclasses.add(new ConnectorEndSmClass(this));
        	metaclasses.add(new ManifestationSmClass(this));
        	metaclasses.add(new NaryAssociationSmClass(this));
        	metaclasses.add(new NaryLinkSmClass(this));
        	metaclasses.add(new NaryConnectorSmClass(this));
        	metaclasses.add(new NaryLinkEndSmClass(this));
        	metaclasses.add(new NaryConnectorEndSmClass(this));
        	metaclasses.add(new PackageImportSmClass(this));
        	metaclasses.add(new PackageMergeSmClass(this));
        	metaclasses.add(new ParameterSmClass(this));
        	metaclasses.add(new BehaviorParameterSmClass(this));
        	metaclasses.add(new ProvidedInterfaceSmClass(this));
        	metaclasses.add(new RaisedExceptionSmClass(this));
        	metaclasses.add(new RequiredInterfaceSmClass(this));
        	metaclasses.add(new TemplateBindingSmClass(this));
        	metaclasses.add(new TemplateParameterSubstitutionSmClass(this));
        	metaclasses.add(new UsageSmClass(this));
        	metaclasses.add(new ProjectSmClass(this));
        
        	 return metaclasses;
        
    }

    /**
     * Create all the model checker classes.
     * @param metamodel the metamodel
     * @return the live model checkers.
     */
    @objid ("29a5037a-c9f3-43c2-9f0e-3289b3c4b115")
    @Override
    public final Collection<SmDependencyTypeChecker> createDependencyCheckers(SmMetamodel metamodel) {
        // This code is automatically generated from all checker classes found in
        //   'modelio.platform.core.modelio.platform.core.metamodel.standard.standard_implementation.mm.impl.org.modelio.metamodel.impl.control' package.
        final List<SmDependencyTypeChecker> checkers = new ArrayList<>(+15);
        
        checkers.add(new AttributeTypeChecker(metamodel));
        checkers.add(new BindableInstanceInternalOwnerChecker(metamodel));
        checkers.add(new CollaborationUseNRepresentedChecker(metamodel));
        checkers.add(new ConnectorEndRepresentedFeatureChecker(metamodel));
        checkers.add(new ExternDocumentSubjectChecker(metamodel));
        checkers.add(new InstanceOwnerChecker(metamodel));
        checkers.add(new InterfaceRealizationImplementerChecker(metamodel));
        checkers.add(new ModelTreeOwnerChecker(metamodel));
        checkers.add(new NoteSubjectChecker(metamodel));
        checkers.add(new ParameterTypeChecker(metamodel));
        checkers.add(new RaisedExceptionThrownTypeChecker(metamodel));
        checkers.add(new StereotypeExtendedElementChecker(metamodel));
        checkers.add(new TaggedValueAnnotedChecker(metamodel));
        checkers.add(new TemplateBindingBoundElementChecker(metamodel));
        checkers.add(new TemplateParameterParametrizedChecker(metamodel));
        
        return checkers;
        
    }

    @objid ("53b6f1b4-0f64-4229-acf6-859363d75672")
    @Override
    public final boolean isExtension() {
        return false;
        
    }

    /**
     * Get the model shield checkers factory.
     * @param metamodel the metamodel
     * @return the model shield checkers factory.
     */
    @objid ("a99daf52-be46-4d6b-86cf-b83fb15d8a72")
    @Override
    public ICheckerFactory getModelShieldCheckers() {
        return new StandardCheckerFactory();
        
    }

}
