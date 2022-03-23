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
package org.modelio.metamodel.impl.expert.standard.meta.impl;

import java.util.HashSet;
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
import org.modelio.metamodel.diagrams.DiagramSet;
import org.modelio.metamodel.diagrams.ObjectDiagram;
import org.modelio.metamodel.diagrams.SequenceDiagram;
import org.modelio.metamodel.diagrams.StateMachineDiagram;
import org.modelio.metamodel.diagrams.StaticDiagram;
import org.modelio.metamodel.diagrams.UseCaseDiagram;
import org.modelio.metamodel.impl.expert.standard.meta.IMetaExpert;
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
import org.modelio.metamodel.uml.behavior.activityModel.ObjectNode;
import org.modelio.metamodel.uml.behavior.activityModel.OpaqueAction;
import org.modelio.metamodel.uml.behavior.activityModel.OutputPin;
import org.modelio.metamodel.uml.behavior.activityModel.SendSignalAction;
import org.modelio.metamodel.uml.behavior.activityModel.StructuredActivityNode;
import org.modelio.metamodel.uml.behavior.activityModel.ValuePin;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Event;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationChannel;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationInteraction;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationMessage;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationNode;
import org.modelio.metamodel.uml.behavior.interactionModel.CombinedFragment;
import org.modelio.metamodel.uml.behavior.interactionModel.ExecutionOccurenceSpecification;
import org.modelio.metamodel.uml.behavior.interactionModel.ExecutionSpecification;
import org.modelio.metamodel.uml.behavior.interactionModel.Gate;
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
import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.metamodel.uml.infrastructure.Usage;
import org.modelio.metamodel.uml.infrastructure.properties.DynamicPropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.EnumeratedPropertyType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyEnumerationLitteral;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyType;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.AttributeLink;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Binding;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.metamodel.uml.statik.CollaborationUse;
import org.modelio.metamodel.uml.statik.Component;
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
import org.modelio.metamodel.uml.statik.LinkEnd;
import org.modelio.metamodel.uml.statik.Manifestation;
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
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

/**
 * Default creation expert that uses metamodel rules to answer.
 */
@objid ("002ff54e-e14c-1097-bcec-001ec947cd2a")
public class DefaultMetaExpert implements IMetaExpert {
    @objid ("006c2a78-e60a-1097-bcec-001ec947cd2a")
    private static final MetamodelRules RULES = new MetamodelRules();

    @objid ("0016513e-e4d5-1097-bcec-001ec947cd2a")
    @Override
    public boolean canCompose(final MObject owner, final MObject composed, final String dep) {
        return canCompose(owner.getMClass(), composed.getMClass(), dep);
    }

    @objid ("0016f1a2-e4d5-1097-bcec-001ec947cd2a")
    @Override
    public boolean canCompose(final MObject owner, final MClass composed, final String dep) {
        return canCompose(owner.getMClass(), composed, dep);
    }

    @objid ("001745ee-e4d5-1097-bcec-001ec947cd2a")
    @Override
    public boolean canCompose(final MClass owner, final MClass composed, String dep) {
        SmClass smClass = (SmClass) owner;
        SmDependency smDep = smClass.getDependencyDef(dep);
        if (smDep != null) {
            return (smDep.isComposition() || smDep.isSharedComposition()) && composed.hasBase(smDep.getTarget())
                    && DefaultMetaExpert.RULES.canCreate(composed, owner);
        } else {
            return DefaultMetaExpert.RULES.canCreate(composed, owner);
        }
        
    }

    @objid ("0017ba88-e4d5-1097-bcec-001ec947cd2a")
    @Override
    public boolean canDep(MObject source, MClass target, String dep) {
        return canDep(source.getMClass(), target, dep);
    }

    @objid ("0017f570-e4d5-1097-bcec-001ec947cd2a")
    @Override
    public boolean canDep(MClass source, MClass target, String dep) {
        MDependency mDep = source.getDependency(dep);
        return (mDep != null && ((SmClass) mDep.getTarget()).hasBase(target));
    }

    @objid ("00182c84-e4d5-1097-bcec-001ec947cd2a")
    @Override
    public boolean canDep(MObject source, MObject target, String dep) {
        return canDep(source.getMClass(), target.getMClass(), dep);
    }

    /**
     * This class contains the metamodel knowledge of the creation expert.
     * <p>
     * A CreationExpert tool can answer the following questions:
     * <ol>
     * <li>can I create an object X under an Y object ?</li>
     * <li>can I create a link D from an X to an Y</li>
     * <li>can I start a link D from an X</li>
     * </ol>
     * where X, Y and D are metaclass names.
     * </p>
     * <p>
     * Example:<br/>
     * <tt>CreationExpert.canLink(IAssociation.class, IClass.class, IClass.class)</tt> => returns true
     * </p>
     */
    @objid ("006c17b8-e60a-1097-bcec-001ec947cd2a")
    static class MetamodelRules {
        @objid ("006c21cc-e60a-1097-bcec-001ec947cd2a")
        private final HashSet<String> rules = new HashSet<>();

        /**
         * Returns whether the childMetaclass can be created as a child of the parentMetaclass.
         * @param childMetaclass the child
         * @param parentMetaclass the parent
         * @return true if the creation is possible, false otherwise.
         */
        @objid ("006c3acc-e60a-1097-bcec-001ec947cd2a")
        public boolean canCreate(MClass childMetaclass, MClass parentMetaclass) {
            return this.rules.contains(parentMetaclass.getQualifiedName() + childMetaclass.getQualifiedName());
        }

        @objid ("006c417a-e60a-1097-bcec-001ec947cd2a")
         MetamodelRules() {
            registerUmlNodes();
            registerBpmnNodes();
            
        }

        /**
         * Parenting rules are defined as X.Y which means that an 'Y' instance can be created under an 'X' instance. Note that
         * parenting rules do not take the metamodel inheritance tree into account
         */
        @objid ("006c4ab2-e60a-1097-bcec-001ec947cd2a")
        private void registerUmlNodes() {
            // AcceptCallEventAction
            addRule(AcceptCallEventAction.MQNAME, Constraint.MQNAME);
            addRule(AcceptCallEventAction.MQNAME, OutputPin.MQNAME);
            
            // AcceptChangeEventAction
            addRule(AcceptChangeEventAction.MQNAME, Constraint.MQNAME);
            addRule(AcceptChangeEventAction.MQNAME, OutputPin.MQNAME);
            
            // AcceptSignalAction
            addRule(AcceptSignalAction.MQNAME, Constraint.MQNAME);
            addRule(AcceptSignalAction.MQNAME, OutputPin.MQNAME);
            
            // AcceptTimeEventAction
            addRule(AcceptTimeEventAction.MQNAME, Constraint.MQNAME);
            addRule(AcceptTimeEventAction.MQNAME, OutputPin.MQNAME);
            
            // Activity
            addRule(Activity.MQNAME, AcceptCallEventAction.MQNAME);
            addRule(Activity.MQNAME, AcceptChangeEventAction.MQNAME);
            addRule(Activity.MQNAME, AcceptSignalAction.MQNAME);
            addRule(Activity.MQNAME, AcceptTimeEventAction.MQNAME);
            addRule(Activity.MQNAME, ActivityFinalNode.MQNAME);
            addRule(Activity.MQNAME, ActivityParameterNode.MQNAME);
            addRule(Activity.MQNAME, ActivityPartition.MQNAME);
            addRule(Activity.MQNAME, CallBehaviorAction.MQNAME);
            addRule(Activity.MQNAME, CallOperationAction.MQNAME);
            addRule(Activity.MQNAME, CentralBufferNode.MQNAME);
            addRule(Activity.MQNAME, ConditionalNode.MQNAME);
            addRule(Activity.MQNAME, Constraint.MQNAME);
            addRule(Activity.MQNAME, DataStoreNode.MQNAME);
            addRule(Activity.MQNAME, DecisionMergeNode.MQNAME);
            addRule(Activity.MQNAME, FlowFinalNode.MQNAME);
            addRule(Activity.MQNAME, FlowFinalNode.MQNAME);
            addRule(Activity.MQNAME, ForkJoinNode.MQNAME);
            addRule(Activity.MQNAME, InitialNode.MQNAME);
            addRule(Activity.MQNAME, InstanceNode.MQNAME);
            addRule(Activity.MQNAME, InterruptibleActivityRegion.MQNAME);
            addRule(Activity.MQNAME, LoopNode.MQNAME);
            addRule(Activity.MQNAME, OpaqueAction.MQNAME);
            addRule(Activity.MQNAME, SendSignalAction.MQNAME);
            addRule(Activity.MQNAME, StructuredActivityNode.MQNAME);
            addRule(Activity.MQNAME, ExpansionRegion.MQNAME);
            addRule(Activity.MQNAME, ActivityDiagram.MQNAME);
            
            // ActivityFinalNode
            addRule(ActivityFinalNode.MQNAME, Constraint.MQNAME);
            
            // ActivityParameterNode
            addRule(ActivityParameterNode.MQNAME, Constraint.MQNAME);
            
            // ActivityPartition
            addRule(ActivityPartition.MQNAME, AcceptCallEventAction.MQNAME);
            addRule(ActivityPartition.MQNAME, AcceptChangeEventAction.MQNAME);
            addRule(ActivityPartition.MQNAME, AcceptSignalAction.MQNAME);
            addRule(ActivityPartition.MQNAME, AcceptTimeEventAction.MQNAME);
            addRule(ActivityPartition.MQNAME, ActivityFinalNode.MQNAME);
            addRule(ActivityPartition.MQNAME, ActivityPartition.MQNAME);
            addRule(ActivityPartition.MQNAME, CallBehaviorAction.MQNAME);
            addRule(ActivityPartition.MQNAME, CallOperationAction.MQNAME);
            addRule(ActivityPartition.MQNAME, CentralBufferNode.MQNAME);
            addRule(ActivityPartition.MQNAME, ConditionalNode.MQNAME);
            addRule(ActivityPartition.MQNAME, Constraint.MQNAME);
            addRule(ActivityPartition.MQNAME, DataStoreNode.MQNAME);
            addRule(ActivityPartition.MQNAME, DecisionMergeNode.MQNAME);
            addRule(ActivityPartition.MQNAME, FlowFinalNode.MQNAME);
            addRule(ActivityPartition.MQNAME, FlowFinalNode.MQNAME);
            addRule(ActivityPartition.MQNAME, ForkJoinNode.MQNAME);
            addRule(ActivityPartition.MQNAME, InitialNode.MQNAME);
            addRule(ActivityPartition.MQNAME, InstanceNode.MQNAME);
            addRule(ActivityPartition.MQNAME, InterruptibleActivityRegion.MQNAME);
            addRule(ActivityPartition.MQNAME, LoopNode.MQNAME);
            addRule(ActivityPartition.MQNAME, OpaqueAction.MQNAME);
            addRule(ActivityPartition.MQNAME, SendSignalAction.MQNAME);
            addRule(ActivityPartition.MQNAME, StructuredActivityNode.MQNAME);
            addRule(ActivityPartition.MQNAME, ExpansionRegion.MQNAME);
            
            // Actor
            addRule(Actor.MQNAME, Activity.MQNAME);
            addRule(Actor.MQNAME, AssociationEnd.MQNAME);
            addRule(Actor.MQNAME, Attribute.MQNAME);
            addRule(Actor.MQNAME, BindableInstance.MQNAME);
            addRule(Actor.MQNAME, Collaboration.MQNAME);
            addRule(Actor.MQNAME, CollaborationUse.MQNAME);
            addRule(Actor.MQNAME, CommunicationInteraction.MQNAME);
            addRule(Actor.MQNAME, Constraint.MQNAME);
            addRule(Actor.MQNAME, DataFlow.MQNAME);
            addRule(Actor.MQNAME, ElementImport.MQNAME);
            addRule(Actor.MQNAME, Generalization.MQNAME);
            addRule(Actor.MQNAME, Instance.MQNAME);
            addRule(Actor.MQNAME, Interaction.MQNAME);
            addRule(Actor.MQNAME, Operation.MQNAME);
            addRule(Actor.MQNAME, PackageImport.MQNAME);
            
            // Artifact
            addRule(Artifact.MQNAME, Artifact.MQNAME);
            addRule(Artifact.MQNAME, Attribute.MQNAME);
            addRule(Artifact.MQNAME, BindableInstance.MQNAME);
            addRule(Artifact.MQNAME, Constraint.MQNAME);
            addRule(Artifact.MQNAME, DataFlow.MQNAME);
            addRule(Artifact.MQNAME, ElementImport.MQNAME);
            addRule(Artifact.MQNAME, Generalization.MQNAME);
            addRule(Artifact.MQNAME, Instance.MQNAME);
            addRule(Artifact.MQNAME, Manifestation.MQNAME);
            addRule(Artifact.MQNAME, Operation.MQNAME);
            addRule(Artifact.MQNAME, PackageImport.MQNAME);
            addRule(Artifact.MQNAME, Port.MQNAME);
            addRule(Artifact.MQNAME, TemplateBinding.MQNAME);
            addRule(Artifact.MQNAME, TemplateParameter.MQNAME);
            addRule(Artifact.MQNAME, Usage.MQNAME);
            addRule(Artifact.MQNAME, DeploymentDiagram.MQNAME);
            addRule(Artifact.MQNAME, ObjectDiagram.MQNAME);
            
            // AssociationEnd
            addRule(AssociationEnd.MQNAME, AssociationEnd.MQNAME);
            addRule(AssociationEnd.MQNAME, Attribute.MQNAME);
            addRule(AssociationEnd.MQNAME, Constraint.MQNAME);
            addRule(AssociationEnd.MQNAME, Usage.MQNAME);
            
            // Attribute
            addRule(Attribute.MQNAME, Constraint.MQNAME);
            addRule(Attribute.MQNAME, Usage.MQNAME);
            
            // AttributeLink
            addRule(AttributeLink.MQNAME, Constraint.MQNAME);
            addRule(AttributeLink.MQNAME, Usage.MQNAME);
            
            // BindableInstance
            addRule(BindableInstance.MQNAME, AttributeLink.MQNAME);
            addRule(BindableInstance.MQNAME, BindableInstance.MQNAME);
            addRule(BindableInstance.MQNAME, Constraint.MQNAME);
            addRule(BindableInstance.MQNAME, Port.MQNAME);
            addRule(BindableInstance.MQNAME, Usage.MQNAME);
            addRule(BindableInstance.MQNAME, ObjectDiagram.MQNAME);
            
            // Binding
            addRule(Binding.MQNAME, Constraint.MQNAME);
            
            // CallBehaviorAction
            addRule(CallBehaviorAction.MQNAME, Constraint.MQNAME);
            addRule(CallBehaviorAction.MQNAME, InputPin.MQNAME);
            addRule(CallBehaviorAction.MQNAME, ValuePin.MQNAME);
            addRule(CallBehaviorAction.MQNAME, OutputPin.MQNAME);
            
            // CallOperationAction
            addRule(CallOperationAction.MQNAME, Constraint.MQNAME);
            addRule(CallOperationAction.MQNAME, InputPin.MQNAME);
            addRule(CallOperationAction.MQNAME, ValuePin.MQNAME);
            addRule(CallOperationAction.MQNAME, OutputPin.MQNAME);
            
            // CentralBufferNode
            addRule(CentralBufferNode.MQNAME, Constraint.MQNAME);
            
            // ChoicePseudoState
            addRule(ChoicePseudoState.MQNAME, Constraint.MQNAME);
            
            // Class
            addRule(Class.MQNAME, Activity.MQNAME);
            addRule(Class.MQNAME, Actor.MQNAME);
            addRule(Class.MQNAME, AssociationEnd.MQNAME);
            addRule(Class.MQNAME, Attribute.MQNAME);
            addRule(Class.MQNAME, BindableInstance.MQNAME);
            addRule(Class.MQNAME, Class.MQNAME);
            addRule(Class.MQNAME, Collaboration.MQNAME);
            addRule(Class.MQNAME, CollaborationUse.MQNAME);
            addRule(Class.MQNAME, CommunicationInteraction.MQNAME);
            addRule(Class.MQNAME, Component.MQNAME);
            addRule(Class.MQNAME, Constraint.MQNAME);
            addRule(Class.MQNAME, DataFlow.MQNAME);
            addRule(Class.MQNAME, DataType.MQNAME);
            addRule(Class.MQNAME, ElementImport.MQNAME);
            addRule(Class.MQNAME, Enumeration.MQNAME);
            addRule(Class.MQNAME, Generalization.MQNAME);
            addRule(Class.MQNAME, InformationItem.MQNAME);
            addRule(Class.MQNAME, Instance.MQNAME);
            addRule(Class.MQNAME, Interaction.MQNAME);
            addRule(Class.MQNAME, Interface.MQNAME);
            addRule(Class.MQNAME, InterfaceRealization.MQNAME);
            addRule(Class.MQNAME, Operation.MQNAME);
            addRule(Class.MQNAME, PackageImport.MQNAME);
            addRule(Class.MQNAME, Port.MQNAME);
            addRule(Class.MQNAME, Signal.MQNAME);
            addRule(Class.MQNAME, StateMachine.MQNAME);
            addRule(Class.MQNAME, TemplateBinding.MQNAME);
            addRule(Class.MQNAME, TemplateParameter.MQNAME);
            addRule(Class.MQNAME, Usage.MQNAME);
            addRule(Class.MQNAME, UseCase.MQNAME);
            addRule(Class.MQNAME, BpmnSharedDefinitions.MQNAME);
            addRule(Class.MQNAME, BpmnProcess.MQNAME);
            addRule(Class.MQNAME, BpmnCollaboration.MQNAME);
            addRule(Class.MQNAME, ClassDiagram.MQNAME);
            addRule(Class.MQNAME, StaticDiagram.MQNAME);
            addRule(Class.MQNAME, CompositeStructureDiagram.MQNAME);
            addRule(Class.MQNAME, DeploymentDiagram.MQNAME);
            addRule(Class.MQNAME, ObjectDiagram.MQNAME);
            addRule(Class.MQNAME, UseCaseDiagram.MQNAME);
            
            // Clause
            addRule(Clause.MQNAME, AcceptCallEventAction.MQNAME);
            addRule(Clause.MQNAME, AcceptChangeEventAction.MQNAME);
            addRule(Clause.MQNAME, AcceptSignalAction.MQNAME);
            addRule(Clause.MQNAME, AcceptTimeEventAction.MQNAME);
            addRule(Clause.MQNAME, ActivityFinalNode.MQNAME);
            addRule(Clause.MQNAME, CallBehaviorAction.MQNAME);
            addRule(Clause.MQNAME, CallOperationAction.MQNAME);
            addRule(Clause.MQNAME, CentralBufferNode.MQNAME);
            addRule(Clause.MQNAME, ConditionalNode.MQNAME);
            addRule(Clause.MQNAME, Constraint.MQNAME);
            addRule(Clause.MQNAME, DataStoreNode.MQNAME);
            addRule(Clause.MQNAME, DecisionMergeNode.MQNAME);
            addRule(Clause.MQNAME, FlowFinalNode.MQNAME);
            addRule(Clause.MQNAME, FlowFinalNode.MQNAME);
            addRule(Clause.MQNAME, ForkJoinNode.MQNAME);
            addRule(Clause.MQNAME, InitialNode.MQNAME);
            addRule(Clause.MQNAME, InstanceNode.MQNAME);
            addRule(Clause.MQNAME, InterruptibleActivityRegion.MQNAME);
            addRule(Clause.MQNAME, LoopNode.MQNAME);
            addRule(Clause.MQNAME, OpaqueAction.MQNAME);
            addRule(Clause.MQNAME, SendSignalAction.MQNAME);
            addRule(Clause.MQNAME, StructuredActivityNode.MQNAME);
            addRule(Clause.MQNAME, ExpansionRegion.MQNAME);
            
            // Collaboration
            addRule(Collaboration.MQNAME, Activity.MQNAME);
            addRule(Collaboration.MQNAME, BindableInstance.MQNAME);
            addRule(Collaboration.MQNAME, CollaborationUse.MQNAME);
            addRule(Collaboration.MQNAME, CommunicationInteraction.MQNAME);
            addRule(Collaboration.MQNAME, Constraint.MQNAME);
            addRule(Collaboration.MQNAME, ElementImport.MQNAME);
            addRule(Collaboration.MQNAME, Generalization.MQNAME);
            addRule(Collaboration.MQNAME, InformationItem.MQNAME);
            addRule(Collaboration.MQNAME, Interaction.MQNAME);
            addRule(Collaboration.MQNAME, InterfaceRealization.MQNAME);
            addRule(Collaboration.MQNAME, PackageImport.MQNAME);
            addRule(Collaboration.MQNAME, TemplateBinding.MQNAME);
            addRule(Collaboration.MQNAME, TemplateParameter.MQNAME);
            addRule(Collaboration.MQNAME, Usage.MQNAME);
            addRule(Collaboration.MQNAME, BpmnSharedDefinitions.MQNAME);
            addRule(Collaboration.MQNAME, BpmnProcess.MQNAME);
            addRule(Collaboration.MQNAME, BpmnCollaboration.MQNAME);
            addRule(Collaboration.MQNAME, CompositeStructureDiagram.MQNAME);
            addRule(Collaboration.MQNAME, ObjectDiagram.MQNAME);
            
            // CollaborationUse
            addRule(CollaborationUse.MQNAME, Constraint.MQNAME);
            addRule(CollaborationUse.MQNAME, Usage.MQNAME);
            
            // CombinedFragment
            addRule(CombinedFragment.MQNAME, Constraint.MQNAME);
            addRule(CombinedFragment.MQNAME, InteractionOperand.MQNAME);
            
            // CommunicationInteraction
            addRule(CommunicationInteraction.MQNAME, CommunicationNode.MQNAME);
            addRule(CommunicationInteraction.MQNAME, Constraint.MQNAME);
            addRule(CommunicationInteraction.MQNAME, CommunicationDiagram.MQNAME);
            
            // CommunicationChannel
            addRule(CommunicationChannel.MQNAME, Constraint.MQNAME);
            addRule(CommunicationChannel.MQNAME, CommunicationMessage.MQNAME);
            
            // CommunicationMessage
            addRule(CommunicationMessage.MQNAME, Constraint.MQNAME);
            
            // CommunicationNode
            addRule(CommunicationNode.MQNAME, Constraint.MQNAME);
            
            // Component
            addRule(Component.MQNAME, Activity.MQNAME);
            addRule(Component.MQNAME, Actor.MQNAME);
            addRule(Component.MQNAME, Artifact.MQNAME);
            addRule(Component.MQNAME, Attribute.MQNAME);
            addRule(Component.MQNAME, BindableInstance.MQNAME);
            addRule(Component.MQNAME, Class.MQNAME);
            addRule(Component.MQNAME, Collaboration.MQNAME);
            addRule(Component.MQNAME, CollaborationUse.MQNAME);
            addRule(Component.MQNAME, CommunicationInteraction.MQNAME);
            addRule(Component.MQNAME, Component.MQNAME);
            addRule(Component.MQNAME, Constraint.MQNAME);
            addRule(Component.MQNAME, DataFlow.MQNAME);
            addRule(Component.MQNAME, DataType.MQNAME);
            addRule(Component.MQNAME, ElementImport.MQNAME);
            addRule(Component.MQNAME, Enumeration.MQNAME);
            addRule(Component.MQNAME, Generalization.MQNAME);
            addRule(Component.MQNAME, InformationItem.MQNAME);
            addRule(Component.MQNAME, Instance.MQNAME);
            addRule(Component.MQNAME, Interaction.MQNAME);
            addRule(Component.MQNAME, Interface.MQNAME);
            addRule(Component.MQNAME, InterfaceRealization.MQNAME);
            addRule(Component.MQNAME, Node.MQNAME);
            addRule(Component.MQNAME, Operation.MQNAME);
            addRule(Component.MQNAME, Package.MQNAME);
            addRule(Component.MQNAME, PackageImport.MQNAME);
            addRule(Component.MQNAME, Port.MQNAME);
            addRule(Component.MQNAME, Signal.MQNAME);
            addRule(Component.MQNAME, StateMachine.MQNAME);
            addRule(Component.MQNAME, TemplateBinding.MQNAME);
            addRule(Component.MQNAME, TemplateParameter.MQNAME);
            addRule(Component.MQNAME, Usage.MQNAME);
            addRule(Component.MQNAME, UseCase.MQNAME);
            addRule(Component.MQNAME, BpmnSharedDefinitions.MQNAME);
            addRule(Component.MQNAME, BpmnProcess.MQNAME);
            addRule(Component.MQNAME, BpmnCollaboration.MQNAME);
            addRule(Component.MQNAME, ClassDiagram.MQNAME);
            addRule(Component.MQNAME, StaticDiagram.MQNAME);
            addRule(Component.MQNAME, DeploymentDiagram.MQNAME);
            addRule(Component.MQNAME, ObjectDiagram.MQNAME);
            addRule(Component.MQNAME, UseCaseDiagram.MQNAME);
            
            // ConditionalNode
            addRule(ConditionalNode.MQNAME, Clause.MQNAME);
            addRule(ConditionalNode.MQNAME, Constraint.MQNAME);
            addRule(ConditionalNode.MQNAME, InputPin.MQNAME);
            addRule(ConditionalNode.MQNAME, OutputPin.MQNAME);
            addRule(ConditionalNode.MQNAME, ValuePin.MQNAME);
            
            // ConnectionPointReference
            addRule(ConnectionPointReference.MQNAME, Constraint.MQNAME);
            
            // ConnectorEnd
            addRule(ConnectorEnd.MQNAME, ConnectorEnd.MQNAME);
            addRule(ConnectorEnd.MQNAME, Constraint.MQNAME);
            
            // Constraint
            addRule(Constraint.MQNAME, Constraint.MQNAME);
            
            // ControlFlow
            addRule(ControlFlow.MQNAME, Constraint.MQNAME);
            addRule(ControlFlow.MQNAME, InformationFlow.MQNAME);
            
            // DataFlow
            addRule(DataFlow.MQNAME, Constraint.MQNAME);
            addRule(DataFlow.MQNAME, Usage.MQNAME);
            
            // DataStoreNode
            addRule(DataStoreNode.MQNAME, Constraint.MQNAME);
            
            // DataType
            addRule(DataType.MQNAME, AssociationEnd.MQNAME);
            addRule(DataType.MQNAME, Attribute.MQNAME);
            addRule(DataType.MQNAME, BindableInstance.MQNAME);
            addRule(DataType.MQNAME, Constraint.MQNAME);
            addRule(DataType.MQNAME, ElementImport.MQNAME);
            addRule(DataType.MQNAME, Generalization.MQNAME);
            addRule(DataType.MQNAME, Instance.MQNAME);
            addRule(DataType.MQNAME, Operation.MQNAME);
            addRule(DataType.MQNAME, PackageImport.MQNAME);
            addRule(DataType.MQNAME, TemplateBinding.MQNAME);
            addRule(DataType.MQNAME, TemplateParameter.MQNAME);
            addRule(DataType.MQNAME, Usage.MQNAME);
            
            // DecisionMergeNode
            addRule(DecisionMergeNode.MQNAME, Constraint.MQNAME);
            
            // DeepHistoryPseudoState
            addRule(DeepHistoryPseudoState.MQNAME, Constraint.MQNAME);
            
            // Dependency
            addRule(Dependency.MQNAME, Constraint.MQNAME);
            addRule(Dependency.MQNAME, InformationFlow.MQNAME);
            
            // DiagramSet
            addRule(DiagramSet.MQNAME, DiagramSet.MQNAME);
            
            // ElementImport
            addRule(ElementImport.MQNAME, Constraint.MQNAME);
            
            // ElementRealization
            addRule(ElementRealization.MQNAME, Constraint.MQNAME);
            
            // EntryPointPseudoState
            addRule(EntryPointPseudoState.MQNAME, Constraint.MQNAME);
            
            // EnumeratedPropertyType
            addRule(EnumeratedPropertyType.MQNAME, Constraint.MQNAME);
            
            // Enumeration
            addRule(Enumeration.MQNAME, AssociationEnd.MQNAME);
            addRule(Enumeration.MQNAME, Attribute.MQNAME);
            addRule(Enumeration.MQNAME, Class.MQNAME);
            addRule(Enumeration.MQNAME, Constraint.MQNAME);
            addRule(Enumeration.MQNAME, DataType.MQNAME);
            addRule(Enumeration.MQNAME, ElementImport.MQNAME);
            addRule(Enumeration.MQNAME, Enumeration.MQNAME);
            addRule(Enumeration.MQNAME, EnumerationLiteral.MQNAME);
            addRule(Enumeration.MQNAME, Interface.MQNAME);
            addRule(Enumeration.MQNAME, InterfaceRealization.MQNAME);
            addRule(Enumeration.MQNAME, Operation.MQNAME);
            addRule(Enumeration.MQNAME, PackageImport.MQNAME);
            addRule(Enumeration.MQNAME, TemplateBinding.MQNAME);
            addRule(Enumeration.MQNAME, TemplateParameter.MQNAME);
            addRule(Enumeration.MQNAME, Usage.MQNAME);
            
            // EnumerationLiteral
            addRule(EnumerationLiteral.MQNAME, Constraint.MQNAME);
            addRule(EnumerationLiteral.MQNAME, Usage.MQNAME);
            
            // Event
            addRule(Event.MQNAME, Constraint.MQNAME);
            addRule(Event.MQNAME, Usage.MQNAME);
            
            // ExpansionRegion
            addRule(ExpansionRegion.MQNAME, AcceptCallEventAction.MQNAME);
            addRule(ExpansionRegion.MQNAME, AcceptChangeEventAction.MQNAME);
            addRule(ExpansionRegion.MQNAME, AcceptSignalAction.MQNAME);
            addRule(ExpansionRegion.MQNAME, AcceptTimeEventAction.MQNAME);
            addRule(ExpansionRegion.MQNAME, ActivityFinalNode.MQNAME);
            addRule(ExpansionRegion.MQNAME, CallBehaviorAction.MQNAME);
            addRule(ExpansionRegion.MQNAME, CallOperationAction.MQNAME);
            addRule(ExpansionRegion.MQNAME, CentralBufferNode.MQNAME);
            addRule(ExpansionRegion.MQNAME, ConditionalNode.MQNAME);
            addRule(ExpansionRegion.MQNAME, Constraint.MQNAME);
            addRule(ExpansionRegion.MQNAME, DataStoreNode.MQNAME);
            addRule(ExpansionRegion.MQNAME, DecisionMergeNode.MQNAME);
            addRule(ExpansionRegion.MQNAME, FlowFinalNode.MQNAME);
            addRule(ExpansionRegion.MQNAME, FlowFinalNode.MQNAME);
            addRule(ExpansionRegion.MQNAME, ForkJoinNode.MQNAME);
            addRule(ExpansionRegion.MQNAME, InitialNode.MQNAME);
            addRule(ExpansionRegion.MQNAME, InstanceNode.MQNAME);
            addRule(ExpansionRegion.MQNAME, InterruptibleActivityRegion.MQNAME);
            addRule(ExpansionRegion.MQNAME, LoopNode.MQNAME);
            addRule(ExpansionRegion.MQNAME, OpaqueAction.MQNAME);
            addRule(ExpansionRegion.MQNAME, SendSignalAction.MQNAME);
            addRule(ExpansionRegion.MQNAME, StructuredActivityNode.MQNAME);
            addRule(ExpansionRegion.MQNAME, ExpansionNode.MQNAME);
            addRule(ExpansionRegion.MQNAME, ExpansionRegion.MQNAME);
            
            // ExpansionNode
            addRule(ExpansionNode.MQNAME, Constraint.MQNAME);
            
            // ExecutionOccurenceSpecification
            addRule(ExecutionOccurenceSpecification.MQNAME, Constraint.MQNAME);
            
            // ExecutionSpecification
            addRule(ExecutionSpecification.MQNAME, Constraint.MQNAME);
            
            // ExitPointPseudoState
            addRule(ExitPointPseudoState.MQNAME, Constraint.MQNAME);
            
            // ExtensionPoint
            addRule(ExtensionPoint.MQNAME, Constraint.MQNAME);
            
            // FinalState
            addRule(FinalState.MQNAME, Constraint.MQNAME);
            
            // FlowFinalNode
            addRule(FlowFinalNode.MQNAME, Constraint.MQNAME);
            
            // ForkJoinNode
            addRule(ForkJoinNode.MQNAME, Constraint.MQNAME);
            
            // ForkPseudoState
            addRule(ForkPseudoState.MQNAME, Constraint.MQNAME);
            
            // Gate
            addRule(Gate.MQNAME, Constraint.MQNAME);
            
            // Generalization
            addRule(Generalization.MQNAME, Constraint.MQNAME);
            
            // InformationItem
            addRule(InformationItem.MQNAME, Constraint.MQNAME);
            
            // InitialNode
            addRule(InitialNode.MQNAME, Constraint.MQNAME);
            
            // InitialPseudoState
            addRule(InitialPseudoState.MQNAME, Constraint.MQNAME);
            
            // InputPin
            addRule(InputPin.MQNAME, Constraint.MQNAME);
            
            // Instance
            addRule(Instance.MQNAME, AttributeLink.MQNAME);
            addRule(Instance.MQNAME, BindableInstance.MQNAME);
            addRule(Instance.MQNAME, Constraint.MQNAME);
            addRule(Instance.MQNAME, LinkEnd.MQNAME);
            addRule(Instance.MQNAME, Port.MQNAME);
            addRule(Instance.MQNAME, Usage.MQNAME);
            addRule(Instance.MQNAME, ObjectDiagram.MQNAME);
            
            // InstanceNode
            addRule(InstanceNode.MQNAME, Constraint.MQNAME);
            
            // Interaction
            addRule(Interaction.MQNAME, CombinedFragment.MQNAME);
            addRule(Interaction.MQNAME, Constraint.MQNAME);
            addRule(Interaction.MQNAME, Gate.MQNAME);
            addRule(Interaction.MQNAME, InteractionUse.MQNAME);
            addRule(Interaction.MQNAME, Lifeline.MQNAME);
            addRule(Interaction.MQNAME, PartDecomposition.MQNAME);
            addRule(Interaction.MQNAME, SequenceDiagram.MQNAME);
            
            // InteractionOperand
            addRule(InteractionOperand.MQNAME, CombinedFragment.MQNAME);
            addRule(InteractionOperand.MQNAME, Constraint.MQNAME);
            addRule(InteractionOperand.MQNAME, InteractionUse.MQNAME);
            
            // InteractionUse
            addRule(InteractionUse.MQNAME, Constraint.MQNAME);
            addRule(InteractionUse.MQNAME, Gate.MQNAME);
            
            // Interface
            addRule(Interface.MQNAME, Activity.MQNAME);
            addRule(Interface.MQNAME, AssociationEnd.MQNAME);
            addRule(Interface.MQNAME, Attribute.MQNAME);
            addRule(Interface.MQNAME, Class.MQNAME);
            addRule(Interface.MQNAME, Collaboration.MQNAME);
            addRule(Interface.MQNAME, CommunicationInteraction.MQNAME);
            addRule(Interface.MQNAME, Constraint.MQNAME);
            addRule(Interface.MQNAME, DataFlow.MQNAME);
            addRule(Interface.MQNAME, DataType.MQNAME);
            addRule(Interface.MQNAME, ElementImport.MQNAME);
            addRule(Interface.MQNAME, Enumeration.MQNAME);
            addRule(Interface.MQNAME, Generalization.MQNAME);
            addRule(Interface.MQNAME, InformationItem.MQNAME);
            addRule(Interface.MQNAME, Instance.MQNAME);
            addRule(Interface.MQNAME, Interaction.MQNAME);
            addRule(Interface.MQNAME, Interface.MQNAME);
            addRule(Interface.MQNAME, Operation.MQNAME);
            addRule(Interface.MQNAME, PackageImport.MQNAME);
            addRule(Interface.MQNAME, Signal.MQNAME);
            addRule(Interface.MQNAME, StateMachine.MQNAME);
            addRule(Interface.MQNAME, TemplateBinding.MQNAME);
            addRule(Interface.MQNAME, TemplateParameter.MQNAME);
            addRule(Interface.MQNAME, Usage.MQNAME);
            addRule(Interface.MQNAME, UseCase.MQNAME);
            addRule(Interface.MQNAME, BpmnSharedDefinitions.MQNAME);
            addRule(Interface.MQNAME, BpmnProcess.MQNAME);
            addRule(Interface.MQNAME, BpmnCollaboration.MQNAME);
            addRule(Interface.MQNAME, ClassDiagram.MQNAME);
            addRule(Interface.MQNAME, StaticDiagram.MQNAME);
            addRule(Interface.MQNAME, UseCaseDiagram.MQNAME);
            
            // InterfaceRealization
            addRule(InterfaceRealization.MQNAME, Constraint.MQNAME);
            
            // InternalTransition
            addRule(InternalTransition.MQNAME, Constraint.MQNAME);
            addRule(InternalTransition.MQNAME, Usage.MQNAME);
            
            // InterruptibleActivityRegion
            addRule(InterruptibleActivityRegion.MQNAME, AcceptCallEventAction.MQNAME);
            addRule(InterruptibleActivityRegion.MQNAME, AcceptChangeEventAction.MQNAME);
            addRule(InterruptibleActivityRegion.MQNAME, AcceptSignalAction.MQNAME);
            addRule(InterruptibleActivityRegion.MQNAME, AcceptTimeEventAction.MQNAME);
            addRule(InterruptibleActivityRegion.MQNAME, ActivityFinalNode.MQNAME);
            addRule(InterruptibleActivityRegion.MQNAME, CallBehaviorAction.MQNAME);
            addRule(InterruptibleActivityRegion.MQNAME, CallOperationAction.MQNAME);
            addRule(InterruptibleActivityRegion.MQNAME, CentralBufferNode.MQNAME);
            addRule(InterruptibleActivityRegion.MQNAME, ConditionalNode.MQNAME);
            addRule(InterruptibleActivityRegion.MQNAME, Constraint.MQNAME);
            addRule(InterruptibleActivityRegion.MQNAME, DataStoreNode.MQNAME);
            addRule(InterruptibleActivityRegion.MQNAME, DecisionMergeNode.MQNAME);
            addRule(InterruptibleActivityRegion.MQNAME, FlowFinalNode.MQNAME);
            addRule(InterruptibleActivityRegion.MQNAME, FlowFinalNode.MQNAME);
            addRule(InterruptibleActivityRegion.MQNAME, ForkJoinNode.MQNAME);
            addRule(InterruptibleActivityRegion.MQNAME, InitialNode.MQNAME);
            addRule(InterruptibleActivityRegion.MQNAME, InstanceNode.MQNAME);
            addRule(InterruptibleActivityRegion.MQNAME, InterruptibleActivityRegion.MQNAME);
            addRule(InterruptibleActivityRegion.MQNAME, LoopNode.MQNAME);
            addRule(InterruptibleActivityRegion.MQNAME, OpaqueAction.MQNAME);
            addRule(InterruptibleActivityRegion.MQNAME, SendSignalAction.MQNAME);
            addRule(InterruptibleActivityRegion.MQNAME, StructuredActivityNode.MQNAME);
            addRule(InterruptibleActivityRegion.MQNAME, ExpansionRegion.MQNAME);
            
            // JoinPseudoState
            addRule(JoinPseudoState.MQNAME, Constraint.MQNAME);
            
            // JunctionPseudoState
            addRule(JunctionPseudoState.MQNAME, Constraint.MQNAME);
            
            // Lifeline
            addRule(Lifeline.MQNAME, Constraint.MQNAME);
            addRule(Lifeline.MQNAME, ExecutionOccurenceSpecification.MQNAME);
            addRule(Lifeline.MQNAME, ExecutionSpecification.MQNAME);
            addRule(Lifeline.MQNAME, PartDecomposition.MQNAME);
            addRule(Lifeline.MQNAME, StateInvariant.MQNAME);
            
            // LinkEnd
            addRule(LinkEnd.MQNAME, Constraint.MQNAME);
            addRule(LinkEnd.MQNAME, LinkEnd.MQNAME);
            
            // LoopNode
            addRule(LoopNode.MQNAME, AcceptCallEventAction.MQNAME);
            addRule(LoopNode.MQNAME, AcceptChangeEventAction.MQNAME);
            addRule(LoopNode.MQNAME, AcceptSignalAction.MQNAME);
            addRule(LoopNode.MQNAME, AcceptTimeEventAction.MQNAME);
            addRule(LoopNode.MQNAME, ActivityFinalNode.MQNAME);
            addRule(LoopNode.MQNAME, CallBehaviorAction.MQNAME);
            addRule(LoopNode.MQNAME, CallOperationAction.MQNAME);
            addRule(LoopNode.MQNAME, CentralBufferNode.MQNAME);
            addRule(LoopNode.MQNAME, ConditionalNode.MQNAME);
            addRule(LoopNode.MQNAME, Constraint.MQNAME);
            addRule(LoopNode.MQNAME, DataStoreNode.MQNAME);
            addRule(LoopNode.MQNAME, DecisionMergeNode.MQNAME);
            addRule(LoopNode.MQNAME, FlowFinalNode.MQNAME);
            addRule(LoopNode.MQNAME, FlowFinalNode.MQNAME);
            addRule(LoopNode.MQNAME, ForkJoinNode.MQNAME);
            addRule(LoopNode.MQNAME, InitialNode.MQNAME);
            addRule(LoopNode.MQNAME, InputPin.MQNAME);
            addRule(LoopNode.MQNAME, ValuePin.MQNAME);
            addRule(LoopNode.MQNAME, InstanceNode.MQNAME);
            addRule(LoopNode.MQNAME, InterruptibleActivityRegion.MQNAME);
            addRule(LoopNode.MQNAME, LoopNode.MQNAME);
            addRule(LoopNode.MQNAME, OpaqueAction.MQNAME);
            addRule(LoopNode.MQNAME, OutputPin.MQNAME);
            addRule(LoopNode.MQNAME, SendSignalAction.MQNAME);
            addRule(LoopNode.MQNAME, StructuredActivityNode.MQNAME);
            addRule(LoopNode.MQNAME, ExpansionRegion.MQNAME);
            
            // Manifestation
            addRule(Manifestation.MQNAME, Constraint.MQNAME);
            
            // Message
            addRule(Message.MQNAME, Constraint.MQNAME);
            addRule(Message.MQNAME, InformationFlow.MQNAME);
            
            // MessageFlow
            addRule(MessageFlow.MQNAME, InformationFlow.MQNAME);
            
            // ModelElement
            addRule(ModelElement.MQNAME, Constraint.MQNAME);
            addRule(ModelElement.MQNAME, ElementRealization.MQNAME);
            
            // Node
            addRule(Node.MQNAME, AssociationEnd.MQNAME);
            addRule(Node.MQNAME, Attribute.MQNAME);
            addRule(Node.MQNAME, BindableInstance.MQNAME);
            addRule(Node.MQNAME, CollaborationUse.MQNAME);
            addRule(Node.MQNAME, CommunicationInteraction.MQNAME);
            addRule(Node.MQNAME, Constraint.MQNAME);
            addRule(Node.MQNAME, DataFlow.MQNAME);
            addRule(Node.MQNAME, ElementImport.MQNAME);
            addRule(Node.MQNAME, Generalization.MQNAME);
            addRule(Node.MQNAME, InformationItem.MQNAME);
            addRule(Node.MQNAME, Instance.MQNAME);
            addRule(Node.MQNAME, Node.MQNAME);
            addRule(Node.MQNAME, Operation.MQNAME);
            addRule(Node.MQNAME, PackageImport.MQNAME);
            addRule(Node.MQNAME, Port.MQNAME);
            addRule(Node.MQNAME, StateMachine.MQNAME);
            addRule(Node.MQNAME, TemplateBinding.MQNAME);
            addRule(Node.MQNAME, TemplateParameter.MQNAME);
            addRule(Node.MQNAME, Usage.MQNAME);
            addRule(Node.MQNAME, CompositeStructureDiagram.MQNAME);
            addRule(Node.MQNAME, DeploymentDiagram.MQNAME);
            addRule(Node.MQNAME, ObjectDiagram.MQNAME);
            
            // Note
            addRule(Note.MQNAME, Constraint.MQNAME);
            
            // ObjectFlow
            addRule(ObjectFlow.MQNAME, Constraint.MQNAME);
            addRule(ObjectFlow.MQNAME, InformationFlow.MQNAME);
            
            // ObjectNode
            addRule(ObjectNode.MQNAME, Constraint.MQNAME);
            
            // OpaqueAction
            addRule(OpaqueAction.MQNAME, Constraint.MQNAME);
            addRule(OpaqueAction.MQNAME, InputPin.MQNAME);
            addRule(OpaqueAction.MQNAME, ValuePin.MQNAME);
            addRule(OpaqueAction.MQNAME, OutputPin.MQNAME);
            
            // Operation
            addRule(Operation.MQNAME, Activity.MQNAME);
            addRule(Operation.MQNAME, Collaboration.MQNAME);
            addRule(Operation.MQNAME, CollaborationUse.MQNAME);
            addRule(Operation.MQNAME, CommunicationInteraction.MQNAME);
            addRule(Operation.MQNAME, Constraint.MQNAME);
            addRule(Operation.MQNAME, ElementImport.MQNAME);
            addRule(Operation.MQNAME, Interaction.MQNAME);
            addRule(Operation.MQNAME, PackageImport.MQNAME);
            addRule(Operation.MQNAME, Parameter.MQNAME);
            addRule(Operation.MQNAME, RaisedException.MQNAME);
            addRule(Operation.MQNAME, StateMachine.MQNAME);
            addRule(Operation.MQNAME, TemplateBinding.MQNAME);
            addRule(Operation.MQNAME, TemplateParameter.MQNAME);
            addRule(Operation.MQNAME, Usage.MQNAME);
            addRule(Operation.MQNAME, BpmnSharedDefinitions.MQNAME);
            addRule(Operation.MQNAME, BpmnProcess.MQNAME);
            addRule(Operation.MQNAME, BpmnCollaboration.MQNAME);
            
            // OutputPin
            addRule(OutputPin.MQNAME, Constraint.MQNAME);
            
            // Package
            addRule(Package.MQNAME, Activity.MQNAME);
            addRule(Package.MQNAME, Actor.MQNAME);
            addRule(Package.MQNAME, Artifact.MQNAME);
            addRule(Package.MQNAME, Class.MQNAME);
            addRule(Package.MQNAME, Collaboration.MQNAME);
            addRule(Package.MQNAME, CommunicationInteraction.MQNAME);
            addRule(Package.MQNAME, Component.MQNAME);
            addRule(Package.MQNAME, Constraint.MQNAME);
            addRule(Package.MQNAME, DataFlow.MQNAME);
            addRule(Package.MQNAME, DataType.MQNAME);
            addRule(Package.MQNAME, ElementImport.MQNAME);
            addRule(Package.MQNAME, Enumeration.MQNAME);
            addRule(Package.MQNAME, InformationItem.MQNAME);
            addRule(Package.MQNAME, Instance.MQNAME);
            addRule(Package.MQNAME, Interaction.MQNAME);
            addRule(Package.MQNAME, Interface.MQNAME);
            addRule(Package.MQNAME, Node.MQNAME);
            addRule(Package.MQNAME, Package.MQNAME);
            addRule(Package.MQNAME, PackageImport.MQNAME);
            addRule(Package.MQNAME, PackageMerge.MQNAME);
            addRule(Package.MQNAME, Signal.MQNAME);
            addRule(Package.MQNAME, StateMachine.MQNAME);
            addRule(Package.MQNAME, TemplateBinding.MQNAME);
            addRule(Package.MQNAME, TemplateParameter.MQNAME);
            addRule(Package.MQNAME, Usage.MQNAME);
            addRule(Package.MQNAME, UseCase.MQNAME);
            addRule(Package.MQNAME, BpmnSharedDefinitions.MQNAME);
            addRule(Package.MQNAME, BpmnProcess.MQNAME);
            addRule(Package.MQNAME, BpmnCollaboration.MQNAME);
            addRule(Package.MQNAME, ClassDiagram.MQNAME);
            addRule(Package.MQNAME, StaticDiagram.MQNAME);
            addRule(Package.MQNAME, DeploymentDiagram.MQNAME);
            addRule(Package.MQNAME, ObjectDiagram.MQNAME);
            addRule(Package.MQNAME, UseCaseDiagram.MQNAME);
            
            // PackageImport
            addRule(PackageImport.MQNAME, Constraint.MQNAME);
            
            // PackageMerge
            addRule(PackageMerge.MQNAME, Constraint.MQNAME);
            
            // Parameter
            addRule(Parameter.MQNAME, Constraint.MQNAME);
            addRule(Parameter.MQNAME, Usage.MQNAME);
            
            // Port
            addRule(Port.MQNAME, ConnectorEnd.MQNAME);
            addRule(Port.MQNAME, Constraint.MQNAME);
            addRule(Port.MQNAME, ProvidedInterface.MQNAME);
            addRule(Port.MQNAME, RequiredInterface.MQNAME);
            addRule(Port.MQNAME, Usage.MQNAME);
            
            // Project
            addRule(Project.MQNAME, Constraint.MQNAME);
            addRule(Project.MQNAME, Package.MQNAME);
            addRule(Project.MQNAME, StaticDiagram.MQNAME);
            
            // PropertyEnumerationLitteral
            addRule(PropertyEnumerationLitteral.MQNAME, Constraint.MQNAME);
            
            // PropertyDefinition
            addRule(PropertyDefinition.MQNAME, Constraint.MQNAME);
            
            // DynamicPropertyDefinition
            addRule(DynamicPropertyDefinition.MQNAME, Constraint.MQNAME);
            
            // PropertyTableDefinition
            addRule(PropertyTableDefinition.MQNAME, Constraint.MQNAME);
            
            // PropertyType
            addRule(PropertyType.MQNAME, Constraint.MQNAME);
            
            // ProvidedInterface
            addRule(ProvidedInterface.MQNAME, Constraint.MQNAME);
            
            // RaisedException
            addRule(RaisedException.MQNAME, Constraint.MQNAME);
            
            // Region
            addRule(Region.MQNAME, ChoicePseudoState.MQNAME);
            addRule(Region.MQNAME, Constraint.MQNAME);
            addRule(Region.MQNAME, DeepHistoryPseudoState.MQNAME);
            addRule(Region.MQNAME, FinalState.MQNAME);
            addRule(Region.MQNAME, ForkPseudoState.MQNAME);
            addRule(Region.MQNAME, InitialPseudoState.MQNAME);
            addRule(Region.MQNAME, JoinPseudoState.MQNAME);
            addRule(Region.MQNAME, JunctionPseudoState.MQNAME);
            addRule(Region.MQNAME, ShallowHistoryPseudoState.MQNAME);
            addRule(Region.MQNAME, State.MQNAME);
            addRule(Region.MQNAME, TerminatePseudoState.MQNAME);
            addRule(Region.MQNAME, Transition.MQNAME);
            addRule(Region.MQNAME, Usage.MQNAME);
            
            // RequiredInterface
            addRule(RequiredInterface.MQNAME, Constraint.MQNAME);
            
            // SendSignalAction
            addRule(SendSignalAction.MQNAME, Constraint.MQNAME);
            addRule(SendSignalAction.MQNAME, InputPin.MQNAME);
            addRule(SendSignalAction.MQNAME, ValuePin.MQNAME);
            
            // ShallowHistoryPseudoState
            addRule(ShallowHistoryPseudoState.MQNAME, Constraint.MQNAME);
            
            // Signal
            addRule(Signal.MQNAME, AssociationEnd.MQNAME);
            addRule(Signal.MQNAME, Attribute.MQNAME);
            addRule(Signal.MQNAME, Collaboration.MQNAME);
            addRule(Signal.MQNAME, CollaborationUse.MQNAME);
            addRule(Signal.MQNAME, CommunicationInteraction.MQNAME);
            addRule(Signal.MQNAME, Constraint.MQNAME);
            addRule(Signal.MQNAME, DataType.MQNAME);
            addRule(Signal.MQNAME, ElementImport.MQNAME);
            addRule(Signal.MQNAME, Enumeration.MQNAME);
            addRule(Signal.MQNAME, Generalization.MQNAME);
            addRule(Signal.MQNAME, Operation.MQNAME);
            addRule(Signal.MQNAME, PackageImport.MQNAME);
            addRule(Signal.MQNAME, Port.MQNAME);
            addRule(Signal.MQNAME, StateMachine.MQNAME);
            addRule(Signal.MQNAME, TemplateBinding.MQNAME);
            addRule(Signal.MQNAME, TemplateParameter.MQNAME);
            addRule(Signal.MQNAME, Usage.MQNAME);
            
            // State
            addRule(State.MQNAME, ConnectionPointReference.MQNAME);
            addRule(State.MQNAME, Constraint.MQNAME);
            addRule(State.MQNAME, EntryPointPseudoState.MQNAME);
            addRule(State.MQNAME, ExitPointPseudoState.MQNAME);
            addRule(State.MQNAME, InternalTransition.MQNAME);
            addRule(State.MQNAME, Region.MQNAME);
            addRule(State.MQNAME, Transition.MQNAME);
            addRule(State.MQNAME, Usage.MQNAME);
            
            // StateInvariant
            addRule(StateInvariant.MQNAME, Constraint.MQNAME);
            
            // StateMachine
            addRule(StateMachine.MQNAME, EntryPointPseudoState.MQNAME);
            addRule(StateMachine.MQNAME, ExitPointPseudoState.MQNAME);
            addRule(StateMachine.MQNAME, Region.MQNAME);
            addRule(StateMachine.MQNAME, StateMachineDiagram.MQNAME);
            addRule(StateMachine.MQNAME, Event.MQNAME);
            
            // StaticDiagram
            addRule(StaticDiagram.MQNAME, Constraint.MQNAME);
            
            // Stereotype
            addRule(Stereotype.MQNAME, Constraint.MQNAME);
            
            // StructuredActivityNode
            addRule(StructuredActivityNode.MQNAME, AcceptCallEventAction.MQNAME);
            addRule(StructuredActivityNode.MQNAME, AcceptChangeEventAction.MQNAME);
            addRule(StructuredActivityNode.MQNAME, AcceptSignalAction.MQNAME);
            addRule(StructuredActivityNode.MQNAME, AcceptTimeEventAction.MQNAME);
            addRule(StructuredActivityNode.MQNAME, ActivityFinalNode.MQNAME);
            addRule(StructuredActivityNode.MQNAME, CallBehaviorAction.MQNAME);
            addRule(StructuredActivityNode.MQNAME, CallOperationAction.MQNAME);
            addRule(StructuredActivityNode.MQNAME, CentralBufferNode.MQNAME);
            addRule(StructuredActivityNode.MQNAME, ConditionalNode.MQNAME);
            addRule(StructuredActivityNode.MQNAME, Constraint.MQNAME);
            addRule(StructuredActivityNode.MQNAME, DataStoreNode.MQNAME);
            addRule(StructuredActivityNode.MQNAME, DecisionMergeNode.MQNAME);
            addRule(StructuredActivityNode.MQNAME, FlowFinalNode.MQNAME);
            addRule(StructuredActivityNode.MQNAME, FlowFinalNode.MQNAME);
            addRule(StructuredActivityNode.MQNAME, ForkJoinNode.MQNAME);
            addRule(StructuredActivityNode.MQNAME, InitialNode.MQNAME);
            addRule(StructuredActivityNode.MQNAME, InputPin.MQNAME);
            addRule(StructuredActivityNode.MQNAME, ValuePin.MQNAME);
            addRule(StructuredActivityNode.MQNAME, InstanceNode.MQNAME);
            addRule(StructuredActivityNode.MQNAME, InterruptibleActivityRegion.MQNAME);
            addRule(StructuredActivityNode.MQNAME, LoopNode.MQNAME);
            addRule(StructuredActivityNode.MQNAME, OpaqueAction.MQNAME);
            addRule(StructuredActivityNode.MQNAME, OutputPin.MQNAME);
            addRule(StructuredActivityNode.MQNAME, SendSignalAction.MQNAME);
            addRule(StructuredActivityNode.MQNAME, StructuredActivityNode.MQNAME);
            addRule(StructuredActivityNode.MQNAME, ExpansionRegion.MQNAME);
            
            // TaggedValue
            addRule(TaggedValue.MQNAME, Constraint.MQNAME);
            
            // TemplateBinding
            addRule(TemplateBinding.MQNAME, Constraint.MQNAME);
            addRule(TemplateBinding.MQNAME, TemplateParameterSubstitution.MQNAME);
            addRule(TemplateBinding.MQNAME, Usage.MQNAME);
            
            // TemplateParameter
            addRule(TemplateParameter.MQNAME, Artifact.MQNAME);
            addRule(TemplateParameter.MQNAME, Attribute.MQNAME);
            addRule(TemplateParameter.MQNAME, BindableInstance.MQNAME);
            addRule(TemplateParameter.MQNAME, Class.MQNAME);
            addRule(TemplateParameter.MQNAME, Collaboration.MQNAME);
            addRule(TemplateParameter.MQNAME, Component.MQNAME);
            addRule(TemplateParameter.MQNAME, Constraint.MQNAME);
            addRule(TemplateParameter.MQNAME, DataType.MQNAME);
            addRule(TemplateParameter.MQNAME, Enumeration.MQNAME);
            addRule(TemplateParameter.MQNAME, Instance.MQNAME);
            addRule(TemplateParameter.MQNAME, Interface.MQNAME);
            addRule(TemplateParameter.MQNAME, Node.MQNAME);
            addRule(TemplateParameter.MQNAME, Operation.MQNAME);
            addRule(TemplateParameter.MQNAME, Package.MQNAME);
            addRule(TemplateParameter.MQNAME, Port.MQNAME);
            addRule(TemplateParameter.MQNAME, Signal.MQNAME);
            addRule(TemplateParameter.MQNAME, StateMachine.MQNAME);
            addRule(TemplateParameter.MQNAME, Usage.MQNAME);
            
            // TemplateParameterSubstitution
            addRule(TemplateParameterSubstitution.MQNAME, Constraint.MQNAME);
            
            // TerminatePseudoState
            addRule(TerminatePseudoState.MQNAME, Constraint.MQNAME);
            
            // Transition
            addRule(Transition.MQNAME, Constraint.MQNAME);
            addRule(Transition.MQNAME, Usage.MQNAME);
            
            // Usage
            addRule(Usage.MQNAME, Constraint.MQNAME);
            
            // UseCase
            addRule(UseCase.MQNAME, Activity.MQNAME);
            addRule(UseCase.MQNAME, AssociationEnd.MQNAME);
            addRule(UseCase.MQNAME, Attribute.MQNAME);
            addRule(UseCase.MQNAME, BindableInstance.MQNAME);
            addRule(UseCase.MQNAME, Collaboration.MQNAME);
            addRule(UseCase.MQNAME, CollaborationUse.MQNAME);
            addRule(UseCase.MQNAME, CommunicationInteraction.MQNAME);
            addRule(UseCase.MQNAME, Constraint.MQNAME);
            addRule(UseCase.MQNAME, DataFlow.MQNAME);
            addRule(UseCase.MQNAME, ElementImport.MQNAME);
            addRule(UseCase.MQNAME, ExtensionPoint.MQNAME);
            addRule(UseCase.MQNAME, Generalization.MQNAME);
            addRule(UseCase.MQNAME, Interaction.MQNAME);
            addRule(UseCase.MQNAME, Operation.MQNAME);
            addRule(UseCase.MQNAME, PackageImport.MQNAME);
            addRule(UseCase.MQNAME, StateMachine.MQNAME);
            addRule(UseCase.MQNAME, TemplateBinding.MQNAME);
            addRule(UseCase.MQNAME, TemplateParameter.MQNAME);
            addRule(UseCase.MQNAME, Usage.MQNAME);
            addRule(UseCase.MQNAME, UseCaseDependency.MQNAME);
            addRule(UseCase.MQNAME, UseCaseDiagram.MQNAME);
            
            // UseCaseDependency
            addRule(UseCaseDependency.MQNAME, Constraint.MQNAME);
            
            // ValuePin
            addRule(ValuePin.MQNAME, Constraint.MQNAME);
            
        }

        @objid ("006c472e-e60a-1097-bcec-001ec947cd2a")
        private void registerBpmnNodes() {
            // BpmnCollaboration
            addRule(BpmnCollaboration.MQNAME, BpmnMessageFlow.MQNAME);
            addRule(BpmnCollaboration.MQNAME, BpmnArtifact.MQNAME);
            addRule(BpmnCollaboration.MQNAME, BpmnAssociation.MQNAME);
            addRule(BpmnCollaboration.MQNAME, BpmnGroup.MQNAME);
            addRule(BpmnCollaboration.MQNAME, BpmnParticipant.MQNAME);
            addRule(BpmnCollaboration.MQNAME, BpmnCollaborationDiagram.MQNAME);
            
            // BpmnLane
            addRule(BpmnLane.MQNAME, BpmnLaneSet.MQNAME);
            
            // BpmnLaneSet
            addRule(BpmnLaneSet.MQNAME, BpmnLane.MQNAME);
            
            // BpmnProcess
            addRule(BpmnProcess.MQNAME, BpmnLaneSet.MQNAME);
            addRule(BpmnProcess.MQNAME, BpmnResourceRole.MQNAME);
            addRule(BpmnProcess.MQNAME, BpmnArtifact.MQNAME);
            addRule(BpmnProcess.MQNAME, BpmnAssociation.MQNAME);
            addRule(BpmnProcess.MQNAME, BpmnGroup.MQNAME);
            addRule(BpmnProcess.MQNAME, BpmnFlowElement.MQNAME);
            addRule(BpmnProcess.MQNAME, BpmnFlowNode.MQNAME);
            addRule(BpmnProcess.MQNAME, BpmnActivity.MQNAME);
            addRule(BpmnProcess.MQNAME, BpmnCallActivity.MQNAME);
            addRule(BpmnProcess.MQNAME, BpmnSubProcess.MQNAME);
            addRule(BpmnProcess.MQNAME, BpmnAdHocSubProcess.MQNAME);
            addRule(BpmnProcess.MQNAME, BpmnTransaction.MQNAME);
            addRule(BpmnProcess.MQNAME, BpmnTask.MQNAME);
            addRule(BpmnProcess.MQNAME, BpmnManualTask.MQNAME);
            addRule(BpmnProcess.MQNAME, BpmnServiceTask.MQNAME);
            addRule(BpmnProcess.MQNAME, BpmnSendTask.MQNAME);
            addRule(BpmnProcess.MQNAME, BpmnBusinessRuleTask.MQNAME);
            addRule(BpmnProcess.MQNAME, BpmnUserTask.MQNAME);
            addRule(BpmnProcess.MQNAME, BpmnReceiveTask.MQNAME);
            addRule(BpmnProcess.MQNAME, BpmnScriptTask.MQNAME);
            addRule(BpmnProcess.MQNAME, BpmnEvent.MQNAME);
            addRule(BpmnProcess.MQNAME, BpmnCatchEvent.MQNAME);
            addRule(BpmnProcess.MQNAME, BpmnBoundaryEvent.MQNAME);
            addRule(BpmnProcess.MQNAME, BpmnIntermediateCatchEvent.MQNAME);
            addRule(BpmnProcess.MQNAME, BpmnStartEvent.MQNAME);
            addRule(BpmnProcess.MQNAME, BpmnThrowEvent.MQNAME);
            addRule(BpmnProcess.MQNAME, BpmnEndEvent.MQNAME);
            addRule(BpmnProcess.MQNAME, BpmnIntermediateThrowEvent.MQNAME);
            addRule(BpmnProcess.MQNAME, BpmnImplicitThrowEvent.MQNAME);
            addRule(BpmnProcess.MQNAME, BpmnGateway.MQNAME);
            addRule(BpmnProcess.MQNAME, BpmnComplexGateway.MQNAME);
            addRule(BpmnProcess.MQNAME, BpmnEventBasedGateway.MQNAME);
            addRule(BpmnProcess.MQNAME, BpmnExclusiveGateway.MQNAME);
            addRule(BpmnProcess.MQNAME, BpmnInclusiveGateway.MQNAME);
            addRule(BpmnProcess.MQNAME, BpmnParallelGateway.MQNAME);
            addRule(BpmnProcess.MQNAME, BpmnItemAwareElement.MQNAME);
            addRule(BpmnProcess.MQNAME, BpmnDataOutput.MQNAME);
            addRule(BpmnProcess.MQNAME, BpmnDataStore.MQNAME);
            addRule(BpmnProcess.MQNAME, BpmnDataObject.MQNAME);
            addRule(BpmnProcess.MQNAME, BpmnDataInput.MQNAME);
            addRule(BpmnProcess.MQNAME, BpmnSequenceFlow.MQNAME);
            addRule(BpmnProcess.MQNAME, BpmnProcessDesignDiagram.MQNAME);
            addRule(BpmnProcess.MQNAME, BpmnCollaboration.MQNAME);
            
            // BpmnFlowNode
            addRule(BpmnFlowNode.MQNAME, BpmnResourceRole.MQNAME);
            
            // BpmnActivity
            addRule(BpmnActivity.MQNAME, BpmnResourceRole.MQNAME);
            
            // BpmnCallActivity
            addRule(BpmnCallActivity.MQNAME, BpmnResourceRole.MQNAME);
            
            // BpmnSubProcess
            addRule(BpmnSubProcess.MQNAME, BpmnResourceRole.MQNAME);
            addRule(BpmnSubProcess.MQNAME, BpmnSubProcessDiagram.MQNAME);
            
            // BpmnAdHocSubProcess
            addRule(BpmnAdHocSubProcess.MQNAME, BpmnResourceRole.MQNAME);
            addRule(BpmnAdHocSubProcess.MQNAME, BpmnSubProcessDiagram.MQNAME);
            
            // BpmnTransaction
            addRule(BpmnTransaction.MQNAME, BpmnResourceRole.MQNAME);
            addRule(BpmnTransaction.MQNAME, BpmnSubProcessDiagram.MQNAME);
            
            // BpmnTask
            addRule(BpmnTask.MQNAME, BpmnResourceRole.MQNAME);
            
            // BpmnManualTask
            addRule(BpmnManualTask.MQNAME, BpmnResourceRole.MQNAME);
            
            // BpmnServiceTask
            addRule(BpmnServiceTask.MQNAME, BpmnResourceRole.MQNAME);
            
            // BpmnSendTask
            addRule(BpmnSendTask.MQNAME, BpmnResourceRole.MQNAME);
            
            // BpmnBusinessRuleTask
            addRule(BpmnBusinessRuleTask.MQNAME, BpmnResourceRole.MQNAME);
            
            // BpmnUserTask
            addRule(BpmnUserTask.MQNAME, BpmnResourceRole.MQNAME);
            
            // BpmnReceiveTask
            addRule(BpmnReceiveTask.MQNAME, BpmnResourceRole.MQNAME);
            
            // BpmnScriptTask
            addRule(BpmnScriptTask.MQNAME, BpmnResourceRole.MQNAME);
            
            // BpmnEvent
            addRule(BpmnEvent.MQNAME, BpmnResourceRole.MQNAME);
            
            // BpmnCatchEvent
            addRule(BpmnCatchEvent.MQNAME, BpmnResourceRole.MQNAME);
            
            // BpmnBoundaryEvent
            addRule(BpmnBoundaryEvent.MQNAME, BpmnResourceRole.MQNAME);
            
            // BpmnIntermediateCatchEvent
            addRule(BpmnIntermediateCatchEvent.MQNAME, BpmnResourceRole.MQNAME);
            
            // BpmnStartEvent
            addRule(BpmnStartEvent.MQNAME, BpmnResourceRole.MQNAME);
            
            // BpmnThrowEvent
            addRule(BpmnThrowEvent.MQNAME, BpmnResourceRole.MQNAME);
            
            // BpmnEndEvent
            addRule(BpmnEndEvent.MQNAME, BpmnResourceRole.MQNAME);
            
            // BpmnIntermediateThrowEvent
            addRule(BpmnIntermediateThrowEvent.MQNAME, BpmnResourceRole.MQNAME);
            
            // BpmnImplicitThrowEvent
            addRule(BpmnImplicitThrowEvent.MQNAME, BpmnResourceRole.MQNAME);
            
            // BpmnGateway
            addRule(BpmnGateway.MQNAME, BpmnResourceRole.MQNAME);
            
            // BpmnComplexGateway
            addRule(BpmnComplexGateway.MQNAME, BpmnResourceRole.MQNAME);
            
            // BpmnEventBasedGateway
            addRule(BpmnEventBasedGateway.MQNAME, BpmnResourceRole.MQNAME);
            
            // BpmnExclusiveGateway
            addRule(BpmnExclusiveGateway.MQNAME, BpmnResourceRole.MQNAME);
            
            // BpmnInclusiveGateway
            addRule(BpmnInclusiveGateway.MQNAME, BpmnResourceRole.MQNAME);
            
            // BpmnParallelGateway
            addRule(BpmnParallelGateway.MQNAME, BpmnResourceRole.MQNAME);
            
            // BpmnBehavior
            addRule(BpmnSharedDefinitions.MQNAME, BpmnSharedElement.MQNAME);
            addRule(BpmnSharedDefinitions.MQNAME, BpmnMessage.MQNAME);
            addRule(BpmnSharedDefinitions.MQNAME, BpmnEndPoint.MQNAME);
            addRule(BpmnSharedDefinitions.MQNAME, BpmnItemDefinition.MQNAME);
            addRule(BpmnSharedDefinitions.MQNAME, BpmnResource.MQNAME);
            addRule(BpmnSharedDefinitions.MQNAME, BpmnInterface.MQNAME);
            
            // BpmnSequenceFlow
            addRule(BpmnSequenceFlow.MQNAME, BpmnSequenceFlowDataAssociation.MQNAME);
            
            // BpmnActivity
            addRule(BpmnActivity.MQNAME, BpmnDataInput.MQNAME);
            
            // BpmnCallActivity
            addRule(BpmnCallActivity.MQNAME, BpmnDataInput.MQNAME);
            
            // BpmnSubProcess
            addRule(BpmnSubProcess.MQNAME, BpmnDataInput.MQNAME);
            
            // BpmnAdHocSubProcess
            addRule(BpmnAdHocSubProcess.MQNAME, BpmnDataInput.MQNAME);
            
            // BpmnTransaction
            addRule(BpmnTransaction.MQNAME, BpmnDataInput.MQNAME);
            
            // BpmnTask
            addRule(BpmnTask.MQNAME, BpmnDataInput.MQNAME);
            
            // BpmnManualTask
            addRule(BpmnManualTask.MQNAME, BpmnDataInput.MQNAME);
            
            // BpmnServiceTask
            addRule(BpmnServiceTask.MQNAME, BpmnDataInput.MQNAME);
            
            // BpmnSendTask
            addRule(BpmnSendTask.MQNAME, BpmnDataInput.MQNAME);
            
            // BpmnBusinessRuleTask
            addRule(BpmnBusinessRuleTask.MQNAME, BpmnDataInput.MQNAME);
            
            // BpmnUserTask
            addRule(BpmnUserTask.MQNAME, BpmnDataInput.MQNAME);
            
            // BpmnReceiveTask
            addRule(BpmnReceiveTask.MQNAME, BpmnDataInput.MQNAME);
            
            // BpmnScriptTask
            addRule(BpmnScriptTask.MQNAME, BpmnDataInput.MQNAME);
            
            // BpmnActivity
            addRule(BpmnActivity.MQNAME, BpmnDataOutput.MQNAME);
            
            // BpmnCallActivity
            addRule(BpmnCallActivity.MQNAME, BpmnDataOutput.MQNAME);
            
            // BpmnSubProcess
            addRule(BpmnSubProcess.MQNAME, BpmnDataOutput.MQNAME);
            
            // BpmnAdHocSubProcess
            addRule(BpmnAdHocSubProcess.MQNAME, BpmnDataOutput.MQNAME);
            
            // BpmnTransaction
            addRule(BpmnTransaction.MQNAME, BpmnDataOutput.MQNAME);
            
            // BpmnTask
            addRule(BpmnTask.MQNAME, BpmnDataOutput.MQNAME);
            
            // BpmnManualTask
            addRule(BpmnManualTask.MQNAME, BpmnDataOutput.MQNAME);
            
            // BpmnServiceTask
            addRule(BpmnServiceTask.MQNAME, BpmnDataOutput.MQNAME);
            
            // BpmnSendTask
            addRule(BpmnSendTask.MQNAME, BpmnDataOutput.MQNAME);
            
            // BpmnBusinessRuleTask
            addRule(BpmnBusinessRuleTask.MQNAME, BpmnDataOutput.MQNAME);
            
            // BpmnUserTask
            addRule(BpmnUserTask.MQNAME, BpmnDataOutput.MQNAME);
            
            // BpmnReceiveTask
            addRule(BpmnReceiveTask.MQNAME, BpmnDataOutput.MQNAME);
            
            // BpmnScriptTask
            addRule(BpmnScriptTask.MQNAME, BpmnDataOutput.MQNAME);
            
            // BpmnActivity
            addRule(BpmnActivity.MQNAME, BpmnDataAssociation.MQNAME);
            
            // BpmnCallActivity
            addRule(BpmnCallActivity.MQNAME, BpmnDataAssociation.MQNAME);
            
            // BpmnSubProcess
            addRule(BpmnSubProcess.MQNAME, BpmnDataAssociation.MQNAME);
            
            // BpmnAdHocSubProcess
            addRule(BpmnAdHocSubProcess.MQNAME, BpmnDataAssociation.MQNAME);
            
            // BpmnTransaction
            addRule(BpmnTransaction.MQNAME, BpmnDataAssociation.MQNAME);
            
            // BpmnTask
            addRule(BpmnTask.MQNAME, BpmnDataAssociation.MQNAME);
            
            // BpmnManualTask
            addRule(BpmnManualTask.MQNAME, BpmnDataAssociation.MQNAME);
            
            // BpmnServiceTask
            addRule(BpmnServiceTask.MQNAME, BpmnDataAssociation.MQNAME);
            
            // BpmnSendTask
            addRule(BpmnSendTask.MQNAME, BpmnDataAssociation.MQNAME);
            
            // BpmnBusinessRuleTask
            addRule(BpmnBusinessRuleTask.MQNAME, BpmnDataAssociation.MQNAME);
            
            // BpmnUserTask
            addRule(BpmnUserTask.MQNAME, BpmnDataAssociation.MQNAME);
            
            // BpmnReceiveTask
            addRule(BpmnReceiveTask.MQNAME, BpmnDataAssociation.MQNAME);
            
            // BpmnScriptTask
            addRule(BpmnScriptTask.MQNAME, BpmnDataAssociation.MQNAME);
            
            // BpmnActivity
            addRule(BpmnActivity.MQNAME, BpmnDataAssociation.MQNAME);
            
            // BpmnCallActivity
            addRule(BpmnCallActivity.MQNAME, BpmnDataAssociation.MQNAME);
            
            // BpmnSubProcess
            addRule(BpmnSubProcess.MQNAME, BpmnDataAssociation.MQNAME);
            
            // BpmnAdHocSubProcess
            addRule(BpmnAdHocSubProcess.MQNAME, BpmnDataAssociation.MQNAME);
            
            // BpmnTransaction
            addRule(BpmnTransaction.MQNAME, BpmnDataAssociation.MQNAME);
            
            // BpmnTask
            addRule(BpmnTask.MQNAME, BpmnDataAssociation.MQNAME);
            
            // BpmnManualTask
            addRule(BpmnManualTask.MQNAME, BpmnDataAssociation.MQNAME);
            
            // BpmnServiceTask
            addRule(BpmnServiceTask.MQNAME, BpmnDataAssociation.MQNAME);
            
            // BpmnSendTask
            addRule(BpmnSendTask.MQNAME, BpmnDataAssociation.MQNAME);
            
            // BpmnBusinessRuleTask
            addRule(BpmnBusinessRuleTask.MQNAME, BpmnDataAssociation.MQNAME);
            
            // BpmnUserTask
            addRule(BpmnUserTask.MQNAME, BpmnDataAssociation.MQNAME);
            
            // BpmnReceiveTask
            addRule(BpmnReceiveTask.MQNAME, BpmnDataAssociation.MQNAME);
            
            // BpmnScriptTask
            addRule(BpmnScriptTask.MQNAME, BpmnDataAssociation.MQNAME);
            
            // BpmnActivity
            addRule(BpmnActivity.MQNAME, BpmnLoopCharacteristics.MQNAME);
            addRule(BpmnActivity.MQNAME, BpmnStandardLoopCharacteristics.MQNAME);
            addRule(BpmnActivity.MQNAME, BpmnMultiInstanceLoopCharacteristics.MQNAME);
            
            // BpmnCallActivity
            addRule(BpmnCallActivity.MQNAME, BpmnLoopCharacteristics.MQNAME);
            addRule(BpmnCallActivity.MQNAME, BpmnStandardLoopCharacteristics.MQNAME);
            addRule(BpmnCallActivity.MQNAME, BpmnMultiInstanceLoopCharacteristics.MQNAME);
            
            // BpmnSubProcess
            addRule(BpmnSubProcess.MQNAME, BpmnLoopCharacteristics.MQNAME);
            addRule(BpmnSubProcess.MQNAME, BpmnStandardLoopCharacteristics.MQNAME);
            addRule(BpmnSubProcess.MQNAME, BpmnMultiInstanceLoopCharacteristics.MQNAME);
            
            // BpmnAdHocSubProcess
            addRule(BpmnAdHocSubProcess.MQNAME, BpmnLoopCharacteristics.MQNAME);
            addRule(BpmnAdHocSubProcess.MQNAME, BpmnStandardLoopCharacteristics.MQNAME);
            addRule(BpmnAdHocSubProcess.MQNAME, BpmnMultiInstanceLoopCharacteristics.MQNAME);
            
            // BpmnTransaction
            addRule(BpmnTransaction.MQNAME, BpmnLoopCharacteristics.MQNAME);
            addRule(BpmnTransaction.MQNAME, BpmnStandardLoopCharacteristics.MQNAME);
            addRule(BpmnTransaction.MQNAME, BpmnMultiInstanceLoopCharacteristics.MQNAME);
            
            // BpmnTask
            addRule(BpmnTask.MQNAME, BpmnLoopCharacteristics.MQNAME);
            addRule(BpmnTask.MQNAME, BpmnStandardLoopCharacteristics.MQNAME);
            addRule(BpmnTask.MQNAME, BpmnMultiInstanceLoopCharacteristics.MQNAME);
            
            // BpmnManualTask
            addRule(BpmnManualTask.MQNAME, BpmnLoopCharacteristics.MQNAME);
            addRule(BpmnManualTask.MQNAME, BpmnStandardLoopCharacteristics.MQNAME);
            addRule(BpmnManualTask.MQNAME, BpmnMultiInstanceLoopCharacteristics.MQNAME);
            
            // BpmnServiceTask
            addRule(BpmnServiceTask.MQNAME, BpmnLoopCharacteristics.MQNAME);
            addRule(BpmnServiceTask.MQNAME, BpmnStandardLoopCharacteristics.MQNAME);
            addRule(BpmnServiceTask.MQNAME, BpmnMultiInstanceLoopCharacteristics.MQNAME);
            
            // BpmnSendTask
            addRule(BpmnSendTask.MQNAME, BpmnLoopCharacteristics.MQNAME);
            addRule(BpmnSendTask.MQNAME, BpmnStandardLoopCharacteristics.MQNAME);
            addRule(BpmnSendTask.MQNAME, BpmnMultiInstanceLoopCharacteristics.MQNAME);
            
            // BpmnBusinessRuleTask
            addRule(BpmnBusinessRuleTask.MQNAME, BpmnLoopCharacteristics.MQNAME);
            addRule(BpmnBusinessRuleTask.MQNAME, BpmnStandardLoopCharacteristics.MQNAME);
            addRule(BpmnBusinessRuleTask.MQNAME, BpmnMultiInstanceLoopCharacteristics.MQNAME);
            
            // BpmnUserTask
            addRule(BpmnUserTask.MQNAME, BpmnLoopCharacteristics.MQNAME);
            addRule(BpmnUserTask.MQNAME, BpmnStandardLoopCharacteristics.MQNAME);
            addRule(BpmnUserTask.MQNAME, BpmnMultiInstanceLoopCharacteristics.MQNAME);
            
            // BpmnReceiveTask
            addRule(BpmnReceiveTask.MQNAME, BpmnLoopCharacteristics.MQNAME);
            addRule(BpmnReceiveTask.MQNAME, BpmnStandardLoopCharacteristics.MQNAME);
            addRule(BpmnReceiveTask.MQNAME, BpmnMultiInstanceLoopCharacteristics.MQNAME);
            
            // BpmnScriptTask
            addRule(BpmnScriptTask.MQNAME, BpmnLoopCharacteristics.MQNAME);
            addRule(BpmnScriptTask.MQNAME, BpmnStandardLoopCharacteristics.MQNAME);
            addRule(BpmnScriptTask.MQNAME, BpmnMultiInstanceLoopCharacteristics.MQNAME);
            
            // BpmnComplexBehaviorDefinition
            addRule(BpmnComplexBehaviorDefinition.MQNAME, BpmnImplicitThrowEvent.MQNAME);
            
            // BpmnMultiInstanceLoopCharacteristics
            addRule(BpmnMultiInstanceLoopCharacteristics.MQNAME, BpmnDataInput.MQNAME);
            
            // BpmnMultiInstanceLoopCharacteristics
            addRule(BpmnMultiInstanceLoopCharacteristics.MQNAME, BpmnDataOutput.MQNAME);
            
            // BpmnMultiInstanceLoopCharacteristics
            addRule(BpmnMultiInstanceLoopCharacteristics.MQNAME, BpmnComplexBehaviorDefinition.MQNAME);
            
            // BpmnSubProcess
            addRule(BpmnSubProcess.MQNAME, BpmnFlowElement.MQNAME);
            addRule(BpmnSubProcess.MQNAME, BpmnFlowNode.MQNAME);
            addRule(BpmnSubProcess.MQNAME, BpmnActivity.MQNAME);
            addRule(BpmnSubProcess.MQNAME, BpmnCallActivity.MQNAME);
            addRule(BpmnSubProcess.MQNAME, BpmnSubProcess.MQNAME);
            addRule(BpmnSubProcess.MQNAME, BpmnAdHocSubProcess.MQNAME);
            addRule(BpmnSubProcess.MQNAME, BpmnTransaction.MQNAME);
            addRule(BpmnSubProcess.MQNAME, BpmnTask.MQNAME);
            addRule(BpmnSubProcess.MQNAME, BpmnManualTask.MQNAME);
            addRule(BpmnSubProcess.MQNAME, BpmnServiceTask.MQNAME);
            addRule(BpmnSubProcess.MQNAME, BpmnSendTask.MQNAME);
            addRule(BpmnSubProcess.MQNAME, BpmnBusinessRuleTask.MQNAME);
            addRule(BpmnSubProcess.MQNAME, BpmnUserTask.MQNAME);
            addRule(BpmnSubProcess.MQNAME, BpmnReceiveTask.MQNAME);
            addRule(BpmnSubProcess.MQNAME, BpmnScriptTask.MQNAME);
            addRule(BpmnSubProcess.MQNAME, BpmnEvent.MQNAME);
            addRule(BpmnSubProcess.MQNAME, BpmnCatchEvent.MQNAME);
            addRule(BpmnSubProcess.MQNAME, BpmnBoundaryEvent.MQNAME);
            addRule(BpmnSubProcess.MQNAME, BpmnIntermediateCatchEvent.MQNAME);
            addRule(BpmnSubProcess.MQNAME, BpmnStartEvent.MQNAME);
            addRule(BpmnSubProcess.MQNAME, BpmnThrowEvent.MQNAME);
            addRule(BpmnSubProcess.MQNAME, BpmnEndEvent.MQNAME);
            addRule(BpmnSubProcess.MQNAME, BpmnIntermediateThrowEvent.MQNAME);
            addRule(BpmnSubProcess.MQNAME, BpmnImplicitThrowEvent.MQNAME);
            addRule(BpmnSubProcess.MQNAME, BpmnGateway.MQNAME);
            addRule(BpmnSubProcess.MQNAME, BpmnComplexGateway.MQNAME);
            addRule(BpmnSubProcess.MQNAME, BpmnEventBasedGateway.MQNAME);
            addRule(BpmnSubProcess.MQNAME, BpmnExclusiveGateway.MQNAME);
            addRule(BpmnSubProcess.MQNAME, BpmnInclusiveGateway.MQNAME);
            addRule(BpmnSubProcess.MQNAME, BpmnParallelGateway.MQNAME);
            addRule(BpmnSubProcess.MQNAME, BpmnItemAwareElement.MQNAME);
            addRule(BpmnSubProcess.MQNAME, BpmnDataOutput.MQNAME);
            addRule(BpmnSubProcess.MQNAME, BpmnDataStore.MQNAME);
            addRule(BpmnSubProcess.MQNAME, BpmnDataObject.MQNAME);
            addRule(BpmnSubProcess.MQNAME, BpmnDataInput.MQNAME);
            addRule(BpmnSubProcess.MQNAME, BpmnSequenceFlow.MQNAME);
            
            // BpmnAdHocSubProcess
            addRule(BpmnAdHocSubProcess.MQNAME, BpmnFlowElement.MQNAME);
            addRule(BpmnAdHocSubProcess.MQNAME, BpmnFlowNode.MQNAME);
            addRule(BpmnAdHocSubProcess.MQNAME, BpmnActivity.MQNAME);
            addRule(BpmnAdHocSubProcess.MQNAME, BpmnCallActivity.MQNAME);
            addRule(BpmnAdHocSubProcess.MQNAME, BpmnSubProcess.MQNAME);
            addRule(BpmnAdHocSubProcess.MQNAME, BpmnAdHocSubProcess.MQNAME);
            addRule(BpmnAdHocSubProcess.MQNAME, BpmnTransaction.MQNAME);
            addRule(BpmnAdHocSubProcess.MQNAME, BpmnTask.MQNAME);
            addRule(BpmnAdHocSubProcess.MQNAME, BpmnManualTask.MQNAME);
            addRule(BpmnAdHocSubProcess.MQNAME, BpmnServiceTask.MQNAME);
            addRule(BpmnAdHocSubProcess.MQNAME, BpmnSendTask.MQNAME);
            addRule(BpmnAdHocSubProcess.MQNAME, BpmnBusinessRuleTask.MQNAME);
            addRule(BpmnAdHocSubProcess.MQNAME, BpmnUserTask.MQNAME);
            addRule(BpmnAdHocSubProcess.MQNAME, BpmnReceiveTask.MQNAME);
            addRule(BpmnAdHocSubProcess.MQNAME, BpmnScriptTask.MQNAME);
            addRule(BpmnAdHocSubProcess.MQNAME, BpmnEvent.MQNAME);
            addRule(BpmnAdHocSubProcess.MQNAME, BpmnCatchEvent.MQNAME);
            addRule(BpmnAdHocSubProcess.MQNAME, BpmnBoundaryEvent.MQNAME);
            addRule(BpmnAdHocSubProcess.MQNAME, BpmnIntermediateCatchEvent.MQNAME);
            addRule(BpmnAdHocSubProcess.MQNAME, BpmnStartEvent.MQNAME);
            addRule(BpmnAdHocSubProcess.MQNAME, BpmnThrowEvent.MQNAME);
            addRule(BpmnAdHocSubProcess.MQNAME, BpmnEndEvent.MQNAME);
            addRule(BpmnAdHocSubProcess.MQNAME, BpmnIntermediateThrowEvent.MQNAME);
            addRule(BpmnAdHocSubProcess.MQNAME, BpmnImplicitThrowEvent.MQNAME);
            addRule(BpmnAdHocSubProcess.MQNAME, BpmnGateway.MQNAME);
            addRule(BpmnAdHocSubProcess.MQNAME, BpmnComplexGateway.MQNAME);
            addRule(BpmnAdHocSubProcess.MQNAME, BpmnEventBasedGateway.MQNAME);
            addRule(BpmnAdHocSubProcess.MQNAME, BpmnExclusiveGateway.MQNAME);
            addRule(BpmnAdHocSubProcess.MQNAME, BpmnInclusiveGateway.MQNAME);
            addRule(BpmnAdHocSubProcess.MQNAME, BpmnParallelGateway.MQNAME);
            addRule(BpmnAdHocSubProcess.MQNAME, BpmnItemAwareElement.MQNAME);
            addRule(BpmnAdHocSubProcess.MQNAME, BpmnDataOutput.MQNAME);
            addRule(BpmnAdHocSubProcess.MQNAME, BpmnDataStore.MQNAME);
            addRule(BpmnAdHocSubProcess.MQNAME, BpmnDataObject.MQNAME);
            addRule(BpmnAdHocSubProcess.MQNAME, BpmnDataInput.MQNAME);
            addRule(BpmnAdHocSubProcess.MQNAME, BpmnSequenceFlow.MQNAME);
            
            // BpmnTransaction
            addRule(BpmnTransaction.MQNAME, BpmnFlowElement.MQNAME);
            addRule(BpmnTransaction.MQNAME, BpmnFlowNode.MQNAME);
            addRule(BpmnTransaction.MQNAME, BpmnActivity.MQNAME);
            addRule(BpmnTransaction.MQNAME, BpmnCallActivity.MQNAME);
            addRule(BpmnTransaction.MQNAME, BpmnSubProcess.MQNAME);
            addRule(BpmnTransaction.MQNAME, BpmnAdHocSubProcess.MQNAME);
            addRule(BpmnTransaction.MQNAME, BpmnTransaction.MQNAME);
            addRule(BpmnTransaction.MQNAME, BpmnTask.MQNAME);
            addRule(BpmnTransaction.MQNAME, BpmnManualTask.MQNAME);
            addRule(BpmnTransaction.MQNAME, BpmnServiceTask.MQNAME);
            addRule(BpmnTransaction.MQNAME, BpmnSendTask.MQNAME);
            addRule(BpmnTransaction.MQNAME, BpmnBusinessRuleTask.MQNAME);
            addRule(BpmnTransaction.MQNAME, BpmnUserTask.MQNAME);
            addRule(BpmnTransaction.MQNAME, BpmnReceiveTask.MQNAME);
            addRule(BpmnTransaction.MQNAME, BpmnScriptTask.MQNAME);
            addRule(BpmnTransaction.MQNAME, BpmnEvent.MQNAME);
            addRule(BpmnTransaction.MQNAME, BpmnCatchEvent.MQNAME);
            addRule(BpmnTransaction.MQNAME, BpmnBoundaryEvent.MQNAME);
            addRule(BpmnTransaction.MQNAME, BpmnIntermediateCatchEvent.MQNAME);
            addRule(BpmnTransaction.MQNAME, BpmnStartEvent.MQNAME);
            addRule(BpmnTransaction.MQNAME, BpmnThrowEvent.MQNAME);
            addRule(BpmnTransaction.MQNAME, BpmnEndEvent.MQNAME);
            addRule(BpmnTransaction.MQNAME, BpmnIntermediateThrowEvent.MQNAME);
            addRule(BpmnTransaction.MQNAME, BpmnImplicitThrowEvent.MQNAME);
            addRule(BpmnTransaction.MQNAME, BpmnGateway.MQNAME);
            addRule(BpmnTransaction.MQNAME, BpmnComplexGateway.MQNAME);
            addRule(BpmnTransaction.MQNAME, BpmnEventBasedGateway.MQNAME);
            addRule(BpmnTransaction.MQNAME, BpmnExclusiveGateway.MQNAME);
            addRule(BpmnTransaction.MQNAME, BpmnInclusiveGateway.MQNAME);
            addRule(BpmnTransaction.MQNAME, BpmnParallelGateway.MQNAME);
            addRule(BpmnTransaction.MQNAME, BpmnItemAwareElement.MQNAME);
            addRule(BpmnTransaction.MQNAME, BpmnDataOutput.MQNAME);
            addRule(BpmnTransaction.MQNAME, BpmnDataStore.MQNAME);
            addRule(BpmnTransaction.MQNAME, BpmnDataObject.MQNAME);
            addRule(BpmnTransaction.MQNAME, BpmnDataInput.MQNAME);
            addRule(BpmnTransaction.MQNAME, BpmnSequenceFlow.MQNAME);
            
            // BpmnSubProcess
            addRule(BpmnSubProcess.MQNAME, BpmnArtifact.MQNAME);
            addRule(BpmnSubProcess.MQNAME, BpmnAssociation.MQNAME);
            addRule(BpmnSubProcess.MQNAME, BpmnGroup.MQNAME);
            
            // BpmnAdHocSubProcess
            addRule(BpmnAdHocSubProcess.MQNAME, BpmnArtifact.MQNAME);
            addRule(BpmnAdHocSubProcess.MQNAME, BpmnAssociation.MQNAME);
            addRule(BpmnAdHocSubProcess.MQNAME, BpmnGroup.MQNAME);
            
            // BpmnTransaction
            addRule(BpmnTransaction.MQNAME, BpmnArtifact.MQNAME);
            addRule(BpmnTransaction.MQNAME, BpmnAssociation.MQNAME);
            addRule(BpmnTransaction.MQNAME, BpmnGroup.MQNAME);
            
            // BpmnCatchEvent
            addRule(BpmnCatchEvent.MQNAME, BpmnDataOutput.MQNAME);
            
            // BpmnBoundaryEvent
            addRule(BpmnBoundaryEvent.MQNAME, BpmnDataOutput.MQNAME);
            
            // BpmnIntermediateCatchEvent
            addRule(BpmnIntermediateCatchEvent.MQNAME, BpmnDataOutput.MQNAME);
            
            // BpmnStartEvent
            addRule(BpmnStartEvent.MQNAME, BpmnDataOutput.MQNAME);
            
            // BpmnCatchEvent
            addRule(BpmnCatchEvent.MQNAME, BpmnDataAssociation.MQNAME);
            
            // BpmnBoundaryEvent
            addRule(BpmnBoundaryEvent.MQNAME, BpmnDataAssociation.MQNAME);
            
            // BpmnIntermediateCatchEvent
            addRule(BpmnIntermediateCatchEvent.MQNAME, BpmnDataAssociation.MQNAME);
            
            // BpmnStartEvent
            addRule(BpmnStartEvent.MQNAME, BpmnDataAssociation.MQNAME);
            
            // BpmnEvent
            addRule(BpmnEvent.MQNAME, BpmnEventDefinition.MQNAME);
            addRule(BpmnEvent.MQNAME, BpmnCancelEventDefinition.MQNAME);
            addRule(BpmnEvent.MQNAME, BpmnEscalationEventDefinition.MQNAME);
            addRule(BpmnEvent.MQNAME, BpmnConditionalEventDefinition.MQNAME);
            addRule(BpmnEvent.MQNAME, BpmnCompensateEventDefinition.MQNAME);
            addRule(BpmnEvent.MQNAME, BpmnErrorEventDefinition.MQNAME);
            addRule(BpmnEvent.MQNAME, BpmnSignalEventDefinition.MQNAME);
            addRule(BpmnEvent.MQNAME, BpmnLinkEventDefinition.MQNAME);
            addRule(BpmnEvent.MQNAME, BpmnTerminateEventDefinition.MQNAME);
            addRule(BpmnEvent.MQNAME, BpmnMessageEventDefinition.MQNAME);
            addRule(BpmnEvent.MQNAME, BpmnTimerEventDefinition.MQNAME);
            
            // BpmnCatchEvent
            addRule(BpmnCatchEvent.MQNAME, BpmnEventDefinition.MQNAME);
            addRule(BpmnCatchEvent.MQNAME, BpmnCancelEventDefinition.MQNAME);
            addRule(BpmnCatchEvent.MQNAME, BpmnEscalationEventDefinition.MQNAME);
            addRule(BpmnCatchEvent.MQNAME, BpmnConditionalEventDefinition.MQNAME);
            addRule(BpmnCatchEvent.MQNAME, BpmnCompensateEventDefinition.MQNAME);
            addRule(BpmnCatchEvent.MQNAME, BpmnErrorEventDefinition.MQNAME);
            addRule(BpmnCatchEvent.MQNAME, BpmnSignalEventDefinition.MQNAME);
            addRule(BpmnCatchEvent.MQNAME, BpmnLinkEventDefinition.MQNAME);
            addRule(BpmnCatchEvent.MQNAME, BpmnTerminateEventDefinition.MQNAME);
            addRule(BpmnCatchEvent.MQNAME, BpmnMessageEventDefinition.MQNAME);
            addRule(BpmnCatchEvent.MQNAME, BpmnTimerEventDefinition.MQNAME);
            
            // BpmnBoundaryEvent
            addRule(BpmnBoundaryEvent.MQNAME, BpmnEventDefinition.MQNAME);
            addRule(BpmnBoundaryEvent.MQNAME, BpmnCancelEventDefinition.MQNAME);
            addRule(BpmnBoundaryEvent.MQNAME, BpmnEscalationEventDefinition.MQNAME);
            addRule(BpmnBoundaryEvent.MQNAME, BpmnConditionalEventDefinition.MQNAME);
            addRule(BpmnBoundaryEvent.MQNAME, BpmnCompensateEventDefinition.MQNAME);
            addRule(BpmnBoundaryEvent.MQNAME, BpmnErrorEventDefinition.MQNAME);
            addRule(BpmnBoundaryEvent.MQNAME, BpmnSignalEventDefinition.MQNAME);
            addRule(BpmnBoundaryEvent.MQNAME, BpmnLinkEventDefinition.MQNAME);
            addRule(BpmnBoundaryEvent.MQNAME, BpmnTerminateEventDefinition.MQNAME);
            addRule(BpmnBoundaryEvent.MQNAME, BpmnMessageEventDefinition.MQNAME);
            addRule(BpmnBoundaryEvent.MQNAME, BpmnTimerEventDefinition.MQNAME);
            
            // BpmnIntermediateCatchEvent
            addRule(BpmnIntermediateCatchEvent.MQNAME, BpmnEventDefinition.MQNAME);
            addRule(BpmnIntermediateCatchEvent.MQNAME, BpmnCancelEventDefinition.MQNAME);
            addRule(BpmnIntermediateCatchEvent.MQNAME, BpmnEscalationEventDefinition.MQNAME);
            addRule(BpmnIntermediateCatchEvent.MQNAME, BpmnConditionalEventDefinition.MQNAME);
            addRule(BpmnIntermediateCatchEvent.MQNAME, BpmnCompensateEventDefinition.MQNAME);
            addRule(BpmnIntermediateCatchEvent.MQNAME, BpmnErrorEventDefinition.MQNAME);
            addRule(BpmnIntermediateCatchEvent.MQNAME, BpmnSignalEventDefinition.MQNAME);
            addRule(BpmnIntermediateCatchEvent.MQNAME, BpmnLinkEventDefinition.MQNAME);
            addRule(BpmnIntermediateCatchEvent.MQNAME, BpmnTerminateEventDefinition.MQNAME);
            addRule(BpmnIntermediateCatchEvent.MQNAME, BpmnMessageEventDefinition.MQNAME);
            addRule(BpmnIntermediateCatchEvent.MQNAME, BpmnTimerEventDefinition.MQNAME);
            
            // BpmnStartEvent
            addRule(BpmnStartEvent.MQNAME, BpmnEventDefinition.MQNAME);
            addRule(BpmnStartEvent.MQNAME, BpmnCancelEventDefinition.MQNAME);
            addRule(BpmnStartEvent.MQNAME, BpmnEscalationEventDefinition.MQNAME);
            addRule(BpmnStartEvent.MQNAME, BpmnConditionalEventDefinition.MQNAME);
            addRule(BpmnStartEvent.MQNAME, BpmnCompensateEventDefinition.MQNAME);
            addRule(BpmnStartEvent.MQNAME, BpmnErrorEventDefinition.MQNAME);
            addRule(BpmnStartEvent.MQNAME, BpmnSignalEventDefinition.MQNAME);
            addRule(BpmnStartEvent.MQNAME, BpmnLinkEventDefinition.MQNAME);
            addRule(BpmnStartEvent.MQNAME, BpmnTerminateEventDefinition.MQNAME);
            addRule(BpmnStartEvent.MQNAME, BpmnMessageEventDefinition.MQNAME);
            addRule(BpmnStartEvent.MQNAME, BpmnTimerEventDefinition.MQNAME);
            
            // BpmnThrowEvent
            addRule(BpmnThrowEvent.MQNAME, BpmnEventDefinition.MQNAME);
            addRule(BpmnThrowEvent.MQNAME, BpmnCancelEventDefinition.MQNAME);
            addRule(BpmnThrowEvent.MQNAME, BpmnEscalationEventDefinition.MQNAME);
            addRule(BpmnThrowEvent.MQNAME, BpmnConditionalEventDefinition.MQNAME);
            addRule(BpmnThrowEvent.MQNAME, BpmnCompensateEventDefinition.MQNAME);
            addRule(BpmnThrowEvent.MQNAME, BpmnErrorEventDefinition.MQNAME);
            addRule(BpmnThrowEvent.MQNAME, BpmnSignalEventDefinition.MQNAME);
            addRule(BpmnThrowEvent.MQNAME, BpmnLinkEventDefinition.MQNAME);
            addRule(BpmnThrowEvent.MQNAME, BpmnTerminateEventDefinition.MQNAME);
            addRule(BpmnThrowEvent.MQNAME, BpmnMessageEventDefinition.MQNAME);
            addRule(BpmnThrowEvent.MQNAME, BpmnTimerEventDefinition.MQNAME);
            
            // BpmnEndEvent
            addRule(BpmnEndEvent.MQNAME, BpmnEventDefinition.MQNAME);
            addRule(BpmnEndEvent.MQNAME, BpmnCancelEventDefinition.MQNAME);
            addRule(BpmnEndEvent.MQNAME, BpmnEscalationEventDefinition.MQNAME);
            addRule(BpmnEndEvent.MQNAME, BpmnConditionalEventDefinition.MQNAME);
            addRule(BpmnEndEvent.MQNAME, BpmnCompensateEventDefinition.MQNAME);
            addRule(BpmnEndEvent.MQNAME, BpmnErrorEventDefinition.MQNAME);
            addRule(BpmnEndEvent.MQNAME, BpmnSignalEventDefinition.MQNAME);
            addRule(BpmnEndEvent.MQNAME, BpmnLinkEventDefinition.MQNAME);
            addRule(BpmnEndEvent.MQNAME, BpmnTerminateEventDefinition.MQNAME);
            addRule(BpmnEndEvent.MQNAME, BpmnMessageEventDefinition.MQNAME);
            addRule(BpmnEndEvent.MQNAME, BpmnTimerEventDefinition.MQNAME);
            
            // BpmnIntermediateThrowEvent
            addRule(BpmnIntermediateThrowEvent.MQNAME, BpmnEventDefinition.MQNAME);
            addRule(BpmnIntermediateThrowEvent.MQNAME, BpmnCancelEventDefinition.MQNAME);
            addRule(BpmnIntermediateThrowEvent.MQNAME, BpmnEscalationEventDefinition.MQNAME);
            addRule(BpmnIntermediateThrowEvent.MQNAME, BpmnConditionalEventDefinition.MQNAME);
            addRule(BpmnIntermediateThrowEvent.MQNAME, BpmnCompensateEventDefinition.MQNAME);
            addRule(BpmnIntermediateThrowEvent.MQNAME, BpmnErrorEventDefinition.MQNAME);
            addRule(BpmnIntermediateThrowEvent.MQNAME, BpmnSignalEventDefinition.MQNAME);
            addRule(BpmnIntermediateThrowEvent.MQNAME, BpmnLinkEventDefinition.MQNAME);
            addRule(BpmnIntermediateThrowEvent.MQNAME, BpmnTerminateEventDefinition.MQNAME);
            addRule(BpmnIntermediateThrowEvent.MQNAME, BpmnMessageEventDefinition.MQNAME);
            addRule(BpmnIntermediateThrowEvent.MQNAME, BpmnTimerEventDefinition.MQNAME);
            
            // BpmnImplicitThrowEvent
            addRule(BpmnImplicitThrowEvent.MQNAME, BpmnEventDefinition.MQNAME);
            addRule(BpmnImplicitThrowEvent.MQNAME, BpmnCancelEventDefinition.MQNAME);
            addRule(BpmnImplicitThrowEvent.MQNAME, BpmnEscalationEventDefinition.MQNAME);
            addRule(BpmnImplicitThrowEvent.MQNAME, BpmnConditionalEventDefinition.MQNAME);
            addRule(BpmnImplicitThrowEvent.MQNAME, BpmnCompensateEventDefinition.MQNAME);
            addRule(BpmnImplicitThrowEvent.MQNAME, BpmnErrorEventDefinition.MQNAME);
            addRule(BpmnImplicitThrowEvent.MQNAME, BpmnSignalEventDefinition.MQNAME);
            addRule(BpmnImplicitThrowEvent.MQNAME, BpmnLinkEventDefinition.MQNAME);
            addRule(BpmnImplicitThrowEvent.MQNAME, BpmnTerminateEventDefinition.MQNAME);
            addRule(BpmnImplicitThrowEvent.MQNAME, BpmnMessageEventDefinition.MQNAME);
            addRule(BpmnImplicitThrowEvent.MQNAME, BpmnTimerEventDefinition.MQNAME);
            
            // BpmnThrowEvent
            addRule(BpmnThrowEvent.MQNAME, BpmnDataInput.MQNAME);
            
            // BpmnEndEvent
            addRule(BpmnEndEvent.MQNAME, BpmnDataInput.MQNAME);
            
            // BpmnIntermediateThrowEvent
            addRule(BpmnIntermediateThrowEvent.MQNAME, BpmnDataInput.MQNAME);
            
            // BpmnImplicitThrowEvent
            addRule(BpmnImplicitThrowEvent.MQNAME, BpmnDataInput.MQNAME);
            
            // BpmnThrowEvent
            addRule(BpmnThrowEvent.MQNAME, BpmnDataAssociation.MQNAME);
            
            // BpmnEndEvent
            addRule(BpmnEndEvent.MQNAME, BpmnDataAssociation.MQNAME);
            
            // BpmnIntermediateThrowEvent
            addRule(BpmnIntermediateThrowEvent.MQNAME, BpmnDataAssociation.MQNAME);
            
            // BpmnImplicitThrowEvent
            addRule(BpmnImplicitThrowEvent.MQNAME, BpmnDataAssociation.MQNAME);
            
            // BpmnItemAwareElement
            addRule(BpmnItemAwareElement.MQNAME, BpmnDataState.MQNAME);
            
            // BpmnDataOutput
            addRule(BpmnDataOutput.MQNAME, BpmnDataState.MQNAME);
            
            // BpmnDataStore
            addRule(BpmnDataStore.MQNAME, BpmnDataState.MQNAME);
            
            // BpmnDataObject
            addRule(BpmnDataObject.MQNAME, BpmnDataState.MQNAME);
            
            // BpmnDataInput
            addRule(BpmnDataInput.MQNAME, BpmnDataState.MQNAME);
            
            // BpmnResource
            addRule(BpmnResource.MQNAME, BpmnResourceParameter.MQNAME);
            
            // BpmnResourceRole
            addRule(BpmnResourceRole.MQNAME, BpmnResourceParameterBinding.MQNAME);
            
            // BpmnInterface
            addRule(BpmnInterface.MQNAME, BpmnOperation.MQNAME);
            
        }

        @objid ("006c246a-e60a-1097-bcec-001ec947cd2a")
        private void addRule(String mcD, String mcX) {
            this.rules.add(mcD + mcX);
        }

    }

}
