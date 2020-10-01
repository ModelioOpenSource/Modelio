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

package org.modelio.xmi.util;

import java.util.HashMap;
import java.util.List;
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
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.ActivityDiagram;
import org.modelio.metamodel.diagrams.BehaviorDiagram;
import org.modelio.metamodel.diagrams.ClassDiagram;
import org.modelio.metamodel.diagrams.CommunicationDiagram;
import org.modelio.metamodel.diagrams.CompositeStructureDiagram;
import org.modelio.metamodel.diagrams.DeploymentDiagram;
import org.modelio.metamodel.diagrams.DiagramSet;
import org.modelio.metamodel.diagrams.GraphDiagram;
import org.modelio.metamodel.diagrams.ObjectDiagram;
import org.modelio.metamodel.diagrams.SequenceDiagram;
import org.modelio.metamodel.diagrams.StateMachineDiagram;
import org.modelio.metamodel.diagrams.StaticDiagram;
import org.modelio.metamodel.diagrams.UseCaseDiagram;
import org.modelio.metamodel.impact.ImpactDiagram;
import org.modelio.metamodel.impact.ImpactLink;
import org.modelio.metamodel.impact.ImpactModel;
import org.modelio.metamodel.impact.ImpactProject;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.mda.ModuleParameter;
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
import org.modelio.metamodel.uml.infrastructure.AbstractProject;
import org.modelio.metamodel.uml.infrastructure.AbstractResource;
import org.modelio.metamodel.uml.infrastructure.Abstraction;
import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.Document;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ExternElement;
import org.modelio.metamodel.uml.infrastructure.ExternProcessor;
import org.modelio.metamodel.uml.infrastructure.MetaclassReference;
import org.modelio.metamodel.uml.infrastructure.MethodologicalLink;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.infrastructure.Resource;
import org.modelio.metamodel.uml.infrastructure.ResourceType;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.Substitution;
import org.modelio.metamodel.uml.infrastructure.TagParameter;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.infrastructure.Usage;
import org.modelio.metamodel.uml.infrastructure.matrix.MatrixDefinition;
import org.modelio.metamodel.uml.infrastructure.matrix.MatrixValueDefinition;
import org.modelio.metamodel.uml.infrastructure.matrix.QueryDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.DynamicPropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.EnumeratedPropertyType;
import org.modelio.metamodel.uml.infrastructure.properties.LocalPropertyTable;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyEnumerationLitteral;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTable;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyType;
import org.modelio.metamodel.uml.infrastructure.properties.TypedPropertyTable;
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
import org.modelio.metamodel.visitors.DefaultModelVisitor;
import org.modelio.metamodel.visitors.IDefaultInfrastructureVisitor;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This class checks if a given element is in the export scope
 * 
 * @author ebrosse
 */
@objid ("c2865693-321e-4c26-90e7-8bebf3be9c6e")
public class ScopeChecker {
    @objid ("73476a35-3820-42dd-b30b-f504b55292a3")
    private final HashMap<MObject, Boolean> scopeMap;

    @objid ("dd6c53e1-3663-4643-b046-54d58cfed6c3")
    private Boolean theResult;

    @objid ("02b28fc1-2e8c-4075-9fef-9d2ea0320908")
    private final List<ModelElement> localRoot;

    @objid ("5451ace7-a0e6-4531-b6eb-6d9a38e941f1")
    private final ScopeSelector selector;

    @objid ("6298ca39-0c93-4608-80fb-aaf2f6d1057c")
    public ScopeChecker(List<ModelElement> localRoots) {
        this.localRoot = localRoots;
        this.scopeMap = new HashMap<>();
        this.selector = new ScopeSelector();
    }

    @objid ("ec15003f-db01-4ed9-a67f-c9f5ce9c2630")
    public boolean contains(MObject eltToTest) {
        if (eltToTest == null) {
            setTheResult(false);
        } else {
            setTheResult(this.scopeMap.get(eltToTest));
            if (getTheResult() == null) {
                if (!ModelUtils.mustBeExported(eltToTest)) {
                    setTheResult(false);
                } else {
                    this.selector.launchVisit(eltToTest);
                }
                this.scopeMap.put(eltToTest, getTheResult());
            }
        }
        return getTheResult();
    }

    @objid ("ab05839d-2ada-4e85-97b8-1f183387ba1f")
    public Boolean getTheResult() {
        return this.theResult;
    }

    @objid ("227ad76c-5ab4-49f3-856a-4d82103d4533")
    public void setTheResult(Boolean theResult) {
        this.theResult = theResult;
    }

    @objid ("0fbc91e8-7e3c-4438-a5d8-2776eee9f31a")
    private class ScopeSelector extends DefaultModelVisitor implements IDefaultInfrastructureVisitor {
        @objid ("92d2a917-299e-4a25-8b81-8c53878a20af")
        public ScopeSelector() {
            this.infrastructureVisitor = this;
        }

        @objid ("cae5f082-156b-4451-9e30-c2ba84cdd31e")
        public void launchVisit(MObject eltToTest) {
            eltToTest.accept(this);
        }

        @objid ("c7b4840e-58f5-4b44-a2fb-eb3af0ca611f")
        @Override
        public Object visitAbstractPseudoState(AbstractPseudoState eltToTest) {
            contains(eltToTest.getCompositionOwner());
            return null;
        }

        @objid ("39a17f65-e07f-422c-8e70-727dfce937c0")
        @Override
        public Object visitAbstractResource(AbstractResource obj) {
            return null;
        }

        @objid ("0b967dd9-3b84-426d-b10c-6c3200f0d24c")
        @Override
        public Object visitActivityDiagram(ActivityDiagram eltToTest) {
            setTheResult(false);
            return null;
        }

        @objid ("56792fec-1976-47ce-9334-bf3088214478")
        @Override
        public Object visitActivityEdge(ActivityEdge eltToTest) {
            if (contains(eltToTest.getSource())) {
                contains(eltToTest.getTarget());
            }
            return null;
        }

        @objid ("a71c1733-21e1-4d39-b18f-33e5943a466e")
        @Override
        public Object visitActivityGroup(ActivityGroup eltToTest) {
            contains(eltToTest.getCompositionOwner());
            return null;
        }

        @objid ("c7214b50-3068-4662-b998-de3ac3345a59")
        @Override
        public Object visitActivityNode(ActivityNode eltToTest) {
            contains(eltToTest.getCompositionOwner());
            return null;
        }

        @objid ("682c4ccd-8e60-4974-839a-ef3a03753c17")
        @Override
        public Object visitActivityPartition(ActivityPartition eltToTest) {
            contains(eltToTest.getCompositionOwner());
            return null;
        }

        @objid ("4f37699b-c102-46ad-ab13-933223fbb0d3")
        @Override
        public Object visitAssociation(Association eltToTest) {
            for (AssociationEnd assocEnd : eltToTest.getEnd()) {
                if (!contains(assocEnd.getOwner())) {
                    break;
                }
            }
            return null;
        }

        @objid ("3c45f98a-7f5e-42e9-8555-0377d4a454b7")
        @Override
        public Object visitAssociationEnd(AssociationEnd eltToTest) {
            contains(eltToTest.getCompositionOwner());
            return null;
        }

        @objid ("a9fe5d01-8651-40f4-bf17-9d11277156c7")
        @Override
        public Object visitAttribute(Attribute eltToTest) {
            contains(eltToTest.getCompositionOwner());
            return null;
        }

        @objid ("a8f2653e-24a1-476e-9f9c-e0b75d6c3d02")
        @Override
        public Object visitAttributeLink(AttributeLink eltToTest) {
            contains(eltToTest.getCompositionOwner());
            return null;
        }

        @objid ("510a512e-9662-47dc-9705-da62ef36ad74")
        @Override
        public Object visitBehavior(Behavior eltToTest) {
            contains(eltToTest.getCompositionOwner());
            return null;
        }

        @objid ("bf0f3973-2c53-41a1-a6bf-f21379914a1c")
        @Override
        public Object visitBehaviorDiagram(BehaviorDiagram eltToTest) {
            setTheResult(false);
            return null;
        }

        @objid ("f92c8c65-53ed-4622-9abe-e22b90d02f35")
        @Override
        public Object visitBindableInstance(BindableInstance eltToTest) {
            contains(eltToTest.getCompositionOwner());
            return null;
        }

        @objid ("50363bc8-d56d-47a3-a510-cfc60b4ebdf2")
        @Override
        public Object visitBinding(Binding eltToTest) {
            contains(eltToTest.getCompositionOwner());
            return null;
        }

        @objid ("4fd9d361-11ad-4273-93ed-3cf51f941344")
        @Override
        public Object visitClassAssociation(ClassAssociation eltToTest) {
            if (contains(eltToTest.getAssociationPart())) {
                contains(eltToTest.getClassPart());
            }
            return null;
        }

        @objid ("e3eea8fa-54de-49f6-9ad6-8aaa4ea5ae1a")
        @Override
        public Object visitClause(Clause eltToTest) {
            contains(eltToTest.getCompositionOwner());
            return null;
        }

        @objid ("75e2e0f6-7939-4a4f-8703-6ceb27d7dc63")
        @Override
        public Object visitCollaborationUse(CollaborationUse eltToTest) {
            contains(eltToTest.getCompositionOwner());
            return null;
        }

        @objid ("4efe1825-da52-4d45-a449-d05edbc154f5")
        @Override
        public Object visitComponentRealization(ComponentRealization eltToTest) {
            contains(eltToTest.getCompositionOwner());
            return null;
        }

        @objid ("c4a54dba-7b92-44eb-bcc2-0ecb2db885ec")
        @Override
        public Object visitConnectionPointReference(ConnectionPointReference eltToTest) {
            contains(eltToTest.getCompositionOwner());
            return null;
        }

        @objid ("71d90d2a-368e-4f96-bf3e-2e2276c1887e")
        @Override
        public Object visitConnector(Connector eltToTest) {
            for (LinkEnd linkEnd : eltToTest.getLinkEnd()) {
                if (!contains(linkEnd.getOwner())) {
                    break;
                }
            }
            return null;
        }

        @objid ("3747bce3-9be6-495c-8302-3d9074d61785")
        @Override
        public Object visitConnectorEnd(ConnectorEnd eltToTest) {
            contains(eltToTest.getOwner());
            
            if ((getTheResult()) && (eltToTest.getOpposite() != null)) {
                contains(eltToTest.getOpposite().getOwner());
            }
            return null;
        }

        @objid ("3369a4bd-089d-469e-87c8-6b580fd1721d")
        @Override
        public Object visitConstraint(Constraint eltToTest) {
            for (ModelElement constrainedElement : eltToTest
                    .getConstrainedElement()) {
                if (!contains(constrainedElement)) {
                    break;
                }
            }
            return null;
        }

        @objid ("e4c1fab8-a492-4042-a8c9-4f7a634911e1")
        @Override
        public Object visitDataFlow(DataFlow eltToTest) {
            if (contains(eltToTest.getDestination())) {
                contains(eltToTest.getOrigin());
            }
            return null;
        }

        @objid ("861e253d-e385-4e8b-afe9-124ac4fb2627")
        @Override
        public Object visitDataType(final DataType eltToTest) {
            if (ModelioPrimitiveTypeMapper.isPredefinedType(eltToTest)) {
                setTheResult(true);
            } else {
                contains(eltToTest.getCompositionOwner());
            }
            return null;
        }

        @objid ("24c929a9-76eb-403c-aa5c-6a3afffc91df")
        @Override
        public Object visitDependency(final Dependency eltToTest) {
            if (contains(eltToTest.getDependsOn())) {
                contains(eltToTest.getImpacted());
            }
            return null;
        }

        @objid ("6fb5d369-bdae-4831-a08b-c0bcd543241f")
        @Override
        public Object visitDiagramSet(DiagramSet eltToTest) {
            return visitModelElement(eltToTest);
        }

        @objid ("b06d0f4c-a9d3-4b12-a691-1a06f3c81c03")
        @Override
        public Object visitDocument(Document eltToTest) {
            return visitModelElement(eltToTest);
        }

        @objid ("99e66bf6-031d-4ece-8e85-199ea226bc84")
        @Override
        public Object visitDynamicPropertyDefinition(DynamicPropertyDefinition eltToTest) {
            return visitModelElement(eltToTest);
        }

        @objid ("83bb744c-db83-4bc3-a5a7-a70a1b5260e6")
        @Override
        public Object visitElement(Element eltToTest) {
            throw new NotFoundException("Element of type "
                    + eltToTest.getClass()
                    + " has no implementation done in scope filter.");
        }

        @objid ("ab6fba2a-8cbf-47c1-821a-af05b6436e48")
        @Override
        public Object visitElementImport(ElementImport eltToTest) {
            contains(eltToTest.getImportedElement());
            return null;
        }

        @objid ("a67de59e-eeb5-4159-9e4c-cfe5c2fcc5bf")
        @Override
        public Object visitElementRealization(ElementRealization eltToTest) {
            contains(eltToTest.getImpacted());
            return null;
        }

        @objid ("5e24ac74-0db2-4e85-9cf1-2904dcf94899")
        @Override
        public Object visitEnumeratedPropertyType(EnumeratedPropertyType eltToTest) {
            return visitModelElement(eltToTest);
        }

        @objid ("41a9f2db-0837-425c-926d-dcbb9b595ca7")
        @Override
        public Object visitEnumerationLiteral(EnumerationLiteral eltToTest) {
            contains(eltToTest.getCompositionOwner());
            return null;
        }

        @objid ("a6fcc478-ee38-4c65-bb1c-77326a08e03e")
        @Override
        public Object visitEvent(Event eltToTest) {
            contains(eltToTest.getCompositionOwner());
            return null;
        }

        @objid ("2cda6705-c376-4fd2-aa95-12b33f845d0e")
        @Override
        public Object visitExceptionHandler(ExceptionHandler eltToTest) {
            contains(eltToTest.getCompositionOwner());
            return null;
        }

        @objid ("dc4849e3-0f8d-4486-ba28-14daf8e78e01")
        @Override
        public Object visitExpansionNode(ExpansionNode eltToTest) {
            contains(eltToTest.getCompositionOwner());
            return null;
        }

        @objid ("e585081f-8804-4ec7-a539-e3d5e8364bad")
        @Override
        public Object visitExpansionRegion(ExpansionRegion eltToTest) {
            contains(eltToTest.getCompositionOwner());
            return null;
        }

        @objid ("a50be1f5-42ca-4394-b935-b5f27edc67eb")
        @Override
        public Object visitExtensionPoint(ExtensionPoint eltToTest) {
            contains(eltToTest.getCompositionOwner());
            return null;
        }

        @objid ("a5ad6d6f-85c1-4db4-8a9e-cda01eedde96")
        @Override
        public Object visitExternProcessor(ExternProcessor eltToTest) {
            return visitModelElement(eltToTest);
        }

        @objid ("e1271735-fc5a-4a5e-b13f-3d103ee4dccf")
        @Override
        public Object visitFeature(Feature eltToTest) {
            contains(eltToTest.getCompositionOwner());
            return null;
        }

        @objid ("aabfd2a1-b487-46fd-87b2-af3828e91e6c")
        @Override
        public Object visitFinalState(FinalState eltToTest) {
            contains(eltToTest.getParent());
            return null;
        }

        @objid ("daec559d-f3af-4d18-8134-396f130d6226")
        @Override
        public Object visitGeneralization(Generalization eltToTest) {
            if (contains(eltToTest.getSubType())) {
                contains(eltToTest.getSuperType());
            }
            return null;
        }

        @objid ("307d30f5-9961-4aac-adbb-49e563b63200")
        @Override
        public Object visitImpactDiagram(ImpactDiagram obj) {
            return visitAbstractDiagram(obj);
        }

        @objid ("b5ffb815-b713-4e36-a421-37c6ac9ccc7f")
        @Override
        public Object visitImpactLink(ImpactLink obj) {
            return visitModelElement(obj);
        }

        @objid ("618f432f-1f32-442b-a055-97bc327f4335")
        @Override
        public Object visitImpactModel(ImpactModel obj) {
            return visitModelElement(obj);
        }

        @objid ("02ffb98a-fdf2-4481-a068-9e9849e86bd4")
        @Override
        public Object visitImpactProject(ImpactProject obj) {
            return visitAbstractProject(obj);
        }

        @objid ("d47af5b0-5a9a-4622-a272-b399e7218140")
        @Override
        public Object visitInformationFlow(InformationFlow eltToTest) {
            contains(eltToTest.getOwner());
            return null;
        }

        @objid ("caff9179-9fad-4f7e-9ded-4fb131d7c3bd")
        @Override
        public Object visitInformationItem(InformationItem eltToTest) {
            contains(eltToTest.getOwner());
            return null;
        }

        @objid ("2473d57b-ef6d-44bf-8ed4-ab7147904f8f")
        @Override
        public Object visitInstance(Instance eltToTest) {
            contains(eltToTest.getCompositionOwner());
            return null;
        }

        @objid ("b3acec2c-8c81-469e-bf73-7a16b08f694d")
        @Override
        public Object visitInteractionFragment(InteractionFragment eltToTest) {
            contains(eltToTest.getCompositionOwner());
            return null;
        }

        @objid ("a8d49ddc-4dc2-4d3f-8683-a36fb1e29ec4")
        @Override
        public Object visitInterfaceRealization(InterfaceRealization eltToTest) {
            if (contains(eltToTest.getImplemented())) {
                contains(eltToTest.getImplementer());
            }
            return null;
        }

        @objid ("9f2595b4-7d7d-4b10-b48b-81b70969160d")
        @Override
        public Object visitInternalTransition(InternalTransition eltToTest) {
            contains(eltToTest.getCompositionOwner());
            return null;
        }

        @objid ("3f063a0b-56fc-4899-8733-9d963f94b57b")
        @Override
        public Object visitLifeline(Lifeline eltToTest) {
            contains(eltToTest.getCompositionOwner());
            return null;
        }

        @objid ("9ebfc2ba-7f61-4725-b966-48f62c5a8198")
        @Override
        public Object visitLink(Link eltToTest) {
            for (LinkEnd linkEnd : eltToTest.getLinkEnd()) {
                if (!contains(linkEnd.getOwner())) {
                    break;
                }
            }
            return null;
        }

        @objid ("ff9120e4-68c2-4e6c-a813-830bd3574729")
        @Override
        public Object visitLinkEnd(LinkEnd eltToTest) {
            contains(eltToTest.getOwner());
            
            if (getTheResult()) {
                if (!contains(eltToTest.getOpposite().getOwner())) {
                    return null;
                }
            }
            return null;
        }

        @objid ("07026384-72fc-49f9-9bf4-ee3084257bb2")
        @Override
        public Object visitLocalPropertyTable(LocalPropertyTable eltToTest) {
            return visitElement(eltToTest);
        }

        @objid ("15b20f15-1ca4-463a-9807-f811d37076a1")
        @Override
        public Object visitManifestation(Manifestation eltToTest) {
            if (contains(eltToTest.getUtilizedElement())) {
                contains(eltToTest.getOwner());
            }
            return null;
        }

        @objid ("5a6352d3-0b4e-481a-a7d1-5e2ead1872ba")
        @Override
        public Object visitMatrixDefinition(MatrixDefinition eltToTest) {
            return visitModelElement(eltToTest);
        }

        @objid ("1c55e55c-aea1-4c25-8dff-ca98b8357ba9")
        @Override
        public Object visitMatrixValueDefinition(MatrixValueDefinition eltToTest) {
            return visitElement(eltToTest);
        }

        @objid ("a11118e4-2531-481b-a7d4-457c2bb86806")
        @Override
        public Object visitMessage(Message eltToTest) {
            contains(eltToTest.getCompositionOwner());
            return null;
        }

        @objid ("d06c351f-0080-4e2c-b8a3-19e29e660bf6")
        @Override
        public Object visitMetaclassReference(MetaclassReference eltToTest) {
            return visitElement(eltToTest);
        }

        @objid ("951c78b6-c128-48ef-a614-a3ecf884bc74")
        @Override
        public Object visitModelElement(final ModelElement eltToTest) {
            contains(eltToTest.getCompositionOwner());
            visitElement(eltToTest);
            return null;
        }

        @objid ("e37142c6-821d-4724-81c5-462d50fe222d")
        @Override
        public Object visitModelTree(ModelTree eltToTest) {
            if (eltToTest == null) {
                setTheResult(false);
            } else {
                for (ModelElement currentRoot : ScopeChecker.this.localRoot) {
                    if (currentRoot.equals(eltToTest)) {
                        setTheResult(true);
                        return null;
                    }
                }
                contains(eltToTest.getCompositionOwner());
            }
            return null;
        }

        @objid ("ffbbc3db-b8a2-4963-af71-cfb08083821e")
        @Override
        public Object visitNaryAssociation(NaryAssociation eltToTest) {
            for (NaryAssociationEnd assocEnd : eltToTest.getNaryEnd()) {
                if (!contains(assocEnd.getOwner())) {
                    break;
                }
            }
            return null;
        }

        @objid ("0a5c1c21-f445-4748-ac7b-d7b52237a570")
        @Override
        public Object visitNaryAssociationEnd(NaryAssociationEnd eltToTest) {
            contains(eltToTest.getCompositionOwner());
            return null;
        }

        @objid ("d392c08e-2f28-4cfe-b8f0-509b1d7f9570")
        @Override
        public Object visitNaryConnector(NaryConnector eltToTest) {
            for (NaryLinkEnd assocEnd : eltToTest.getNaryLinkEnd()) {
                if (!contains(assocEnd.getCompositionOwner())) {
                    break;
                }
            }
            return null;
        }

        @objid ("acd482de-7ac9-4192-9296-f921cd797a69")
        @Override
        public Object visitNaryConnectorEnd(NaryConnectorEnd eltToTest) {
            contains(eltToTest.getCompositionOwner());
            return null;
        }

        @objid ("939d318a-3b11-45af-b8bd-9b4a06a45368")
        @Override
        public Object visitNaryLink(NaryLink eltToTest) {
            for (NaryLinkEnd assocEnd : eltToTest.getNaryLinkEnd()) {
                if (!contains(assocEnd.getCompositionOwner())) {
                    break;
                }
            }
            return null;
        }

        @objid ("ab74f7a3-1c11-4481-9796-2214a74ac9cf")
        @Override
        public Object visitNaryLinkEnd(NaryLinkEnd eltToTest) {
            contains(eltToTest.getCompositionOwner());
            return null;
        }

        @objid ("dbed6c3b-1dd6-4681-818a-df69e47f2592")
        @Override
        public Object visitNote(Note eltToTest) {
            contains(eltToTest.getCompositionOwner());
            return null;
        }

        @objid ("fc0395f4-a04f-4ef5-b951-bccb4b6886a9")
        @Override
        public Object visitNoteType(NoteType eltToTest) {
            contains(eltToTest.getCompositionOwner());
            return null;
        }

        @objid ("c16ed6c0-a80e-46be-9c3d-bc757ba19827")
        @Override
        public Object visitOperation(Operation eltToTest) {
            contains(eltToTest.getCompositionOwner());
            return null;
        }

        @objid ("feb93109-c02a-49b7-b94d-3d3fd9bf816a")
        @Override
        public Object visitPackageImport(PackageImport eltToTest) {
            if (contains(eltToTest.getImportedPackage())) {
                contains(eltToTest.getCompositionOwner());
            }
            return null;
        }

        @objid ("3eb6e9ea-d085-43df-a7dc-54fc2041756e")
        @Override
        public Object visitPackageMerge(PackageMerge eltToTest) {
            if (contains(eltToTest.getMergedPackage())) {
                contains(eltToTest.getReceivingPackage());
            }
            return null;
        }

        @objid ("4f7cbb80-5c4c-459f-9071-42aa8c33a671")
        @Override
        public Object visitParameter(Parameter eltToTest) {
            contains(eltToTest.getCompositionOwner());
            return null;
        }

        @objid ("dd90eb06-ede6-4ce7-b33f-4433f07ac167")
        @Override
        public Object visitPort(Port eltToTest) {
            contains(eltToTest.getCompositionOwner());
            return null;
        }

        @objid ("3279fc42-3857-4406-a928-f8607f372a88")
        @Override
        public Object visitProfile(Profile eltToTest) {
            if (ScopeChecker.this.localRoot.get(0) instanceof Profile) {
                for (ModelElement root : ScopeChecker.this.localRoot) {
                    if (eltToTest.getName().contains(root.getName())
                            && (eltToTest.getCompositionOwner().equals(root.getCompositionOwner()))) {
                        setTheResult(true);
                    }
                }
            } else {
                setTheResult(true);
            }
            return null;
        }

        @objid ("46265bdb-ccee-4ce7-801e-74f7612972cf")
        @Override
        public Object visitProject(final Project eltToTest) {
            setTheResult(ScopeChecker.this.localRoot.contains(eltToTest));
            return null;
        }

        @objid ("17a240cd-af35-4a79-86ae-03e26d64f702")
        @Override
        public Object visitProvidedInterface(ProvidedInterface eltToTest) {
            contains(eltToTest.getCompositionOwner());
            return null;
        }

        @objid ("2d7dfc10-0752-44aa-9276-bcca39717f39")
        @Override
        public Object visitRaisedException(RaisedException eltToTest) {
            if (contains(eltToTest.getThrownType())) {
                contains(eltToTest.getCompositionOwner());
            }
            return null;
        }

        @objid ("c53af3f5-a528-4ce8-87dc-8bbc0e1616de")
        @Override
        public Object visitRegion(Region eltToTest) {
            if (eltToTest.getParent() != null) {
                contains(eltToTest.getParent());
            } else {
                contains(eltToTest.getRepresented());
            }
            return null;
        }

        @objid ("976e7154-1b06-44e8-a475-d46a47380664")
        @Override
        public Object visitRequiredInterface(RequiredInterface eltToTest) {
            contains(eltToTest.getCompositionOwner());
            return null;
        }

        @objid ("b84374fb-6c5a-4900-8bd9-1fd8f6f4ece1")
        @Override
        public Object visitResource(Resource obj) {
            setTheResult(false);
            return null;
        }

        @objid ("b26ba5e7-ffd8-4ee9-9c01-03e01c80440d")
        @Override
        public Object visitState(State eltToTest) {
            contains(eltToTest.getParent());
            return null;
        }

        @objid ("8cf6f46b-692c-4ef4-a113-96a1a7cb8781")
        @Override
        public Object visitStateVertex(final StateVertex eltToTest) {
            contains(eltToTest.getCompositionOwner());
            return null;
        }

        @objid ("81e5bf5d-747a-4091-8798-1b92af989ea6")
        @Override
        public Object visitStaticDiagram(StaticDiagram eltToTest) {
            setTheResult(false);
            return null;
        }

        @objid ("9bfbbd62-8399-4e09-ba9f-2c001a338743")
        @Override
        public Object visitStereotype(Stereotype eltToTest) {
            contains(eltToTest.getCompositionOwner());
            return null;
        }

        @objid ("2141fad9-d3b1-4432-bdef-e16699b79461")
        @Override
        public Object visitSubstitution(Substitution eltToTest) {
            if (contains(eltToTest.getContract())) {
                contains(eltToTest.getSubstitutingClassifier());
            }
            return null;
        }

        @objid ("edf7c276-be89-4c10-b6d9-11a3c18a0cfb")
        @Override
        public Object visitTaggedValue(TaggedValue eltToTest) {
            contains(eltToTest.getAnnoted());
            return null;
        }

        @objid ("938a716a-8366-4888-84da-1c0be1c3aebc")
        @Override
        public Object visitTemplateBinding(TemplateBinding eltToTest) {
            contains(eltToTest.getCompositionOwner());
            return null;
        }

        @objid ("d716fdbc-692e-45e4-9d50-8c95dc4753af")
        @Override
        public Object visitTemplateParameter(TemplateParameter eltToTest) {
            contains(eltToTest.getCompositionOwner());
            return null;
        }

        @objid ("53cad87b-0c7a-43b0-b9ea-3e7971985b5c")
        @Override
        public Object visitTemplateParameterSubstitution(TemplateParameterSubstitution eltToTest) {
            contains(eltToTest.getCompositionOwner());
            return null;
        }

        @objid ("84740e4a-9ec1-4e2c-bacb-903633a8167f")
        @Override
        public Object visitTransition(Transition eltToTest) {
            contains(eltToTest.getCompositionOwner());
            return null;
        }

        @objid ("dd361a20-b654-4ab8-8117-b6549af403b9")
        @Override
        public Object visitUseCaseDependency(UseCaseDependency eltToTest) {
            if (contains(eltToTest.getTarget())) {
                contains(eltToTest.getOrigin());
            }
            return null;
        }

        @objid ("4875bad6-dd56-4f95-a4b5-878c4cb31fe2")
        @Override
        public Object visitTagType(TagType eltToTest) {
            contains(eltToTest.getCompositionOwner());
            return null;
        }

        @objid ("18a4a721-d250-41a9-b8ad-9acb1713423b")
        @Override
        public Object visitAbstractDiagram(AbstractDiagram obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("8902baa6-287a-4a0c-880a-418bb5f656a3")
        @Override
        public Object visitAbstractProject(AbstractProject obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("eab5fc2e-9c3a-4b3f-aa13-7c2741511249")
        @Override
        public Object visitExternElement(ExternElement obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("74e6bbdc-073f-4036-aa38-4842eb8a0fce")
        @Override
        public Object visitGraphDiagram(GraphDiagram obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("4212b46d-aceb-4381-ac97-67fdd97d9a8b")
        @Override
        public Object visitMethodologicalLink(MethodologicalLink obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("27547e83-6bed-4601-8d5c-9e7665f070c3")
        @Override
        public Object visitModuleComponent(ModuleComponent obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("a8a4f535-436f-4db7-b2d5-2ace039168d7")
        @Override
        public Object visitModuleParameter(ModuleParameter obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("b1c1adc4-4af6-48cb-b7e0-33383f8c221f")
        @Override
        public Object visitPropertyDefinition(PropertyDefinition obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("ad3a5170-e8fd-4454-a0d2-e039298af63f")
        @Override
        public Object visitPropertyEnumerationLitteral(PropertyEnumerationLitteral obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("699e1d1d-010d-48e1-ba41-d80d24178b89")
        @Override
        public Object visitPropertyTable(PropertyTable obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("c781ebe4-093b-4e78-878d-378bec5be6d5")
        @Override
        public Object visitPropertyTableDefinition(PropertyTableDefinition obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("0d2ec8bc-ed08-4c45-834c-49321b8d5985")
        @Override
        public Object visitPropertyType(PropertyType obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("2a0ea241-a01e-456b-b6fb-9debbde1dca8")
        @Override
        public Object visitQueryDefinition(QueryDefinition obj) {
            setTheResult(false);
            return null;
        }

        @objid ("93214fda-8040-4f46-a2a9-ed68ed622b4c")
        @Override
        public Object visitResourceType(ResourceType obj) {
            setTheResult(false);
            return null;
        }

        @objid ("c855ccab-a71b-4927-9dff-c660bddd128e")
        @Override
        public Object visitTagParameter(TagParameter obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("3c0f4896-41bf-4189-879c-2449367b787e")
        @Override
        public Object visitTypedPropertyTable(TypedPropertyTable obj) {
            setTheResult(false);
            return null;
        }

        @objid ("018eccd1-46cc-44a7-8fb4-e52a2062b81d")
        @Override
        public Object visitAbstraction(Abstraction obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("666b8420-27cb-4ee6-bac9-e939a5ac9441")
        @Override
        public Object visitAcceptCallEventAction(AcceptCallEventAction obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("b168e988-bedc-422e-bb5d-e51d361fa8af")
        @Override
        public Object visitAcceptChangeEventAction(AcceptChangeEventAction obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("e4f3c351-3a11-4e74-ac1d-ec7cc3e70004")
        @Override
        public Object visitAcceptSignalAction(AcceptSignalAction obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("c6c1dd69-7346-4d89-bebc-b657bd781454")
        @Override
        public Object visitAcceptTimeEventAction(AcceptTimeEventAction obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("43218733-8673-4174-9745-2244ef61d534")
        @Override
        public Object visitActivity(Activity obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("ebbd2868-348c-4635-a675-c5ea758f3b5e")
        @Override
        public Object visitActivityAction(ActivityAction obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("4761c6a5-80ab-4d6a-b245-9efd7126b352")
        @Override
        public Object visitActivityFinalNode(ActivityFinalNode obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("8efe4411-b28f-47af-bdbd-fa6bcabe48c7")
        @Override
        public Object visitActivityParameterNode(ActivityParameterNode obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("4cd71ad8-bf31-413f-a2d0-2adf918af9b4")
        @Override
        public Object visitActor(Actor obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("5c9cea17-e246-491e-8401-4066279704e0")
        @Override
        public Object visitArtifact(Artifact obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("d474bf5b-82ca-4f69-9328-c4921e43bffa")
        @Override
        public Object visitBehaviorParameter(BehaviorParameter obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("08c542bc-4c1a-472a-974d-5a393bc613ea")
        @Override
        public Object visitBehavioralFeature(BehavioralFeature obj) {
            setTheResult(false);
            return null;
        }

        @objid ("76f4f4f7-7de6-4215-a14b-b25762ccf6e2")
        @Override
        public Object visitBpmnActivity(BpmnActivity obj) {
            setTheResult(false);
            return null;
        }

        @objid ("9c8e564d-5044-4ba7-b9f7-f233e52e7075")
        @Override
        public Object visitBpmnAdHocSubProcess(BpmnAdHocSubProcess obj) {
            setTheResult(false);
            return null;
        }

        @objid ("8b6d7f07-77b4-47f5-b916-77d7dfdd542b")
        @Override
        public Object visitBpmnArtifact(BpmnArtifact obj) {
            setTheResult(false);
            return null;
        }

        @objid ("5d9cad1c-9ee2-44fe-883d-bc1c23789ab0")
        @Override
        public Object visitBpmnAssociation(BpmnAssociation obj) {
            setTheResult(false);
            return null;
        }

        @objid ("f944b5e1-05dc-4290-921e-2183cbcc49ca")
        @Override
        public Object visitBpmnBaseElement(BpmnBaseElement obj) {
            setTheResult(false);
            return null;
        }

        @objid ("411f4e5d-43eb-420d-86a0-ab93262cd6c7")
        @Override
        public Object visitBpmnBoundaryEvent(BpmnBoundaryEvent obj) {
            setTheResult(false);
            return null;
        }

        @objid ("01be4909-c40b-4131-9749-9a06c140e639")
        @Override
        public Object visitBpmnBusinessRuleTask(BpmnBusinessRuleTask obj) {
            setTheResult(false);
            return null;
        }

        @objid ("9d2ac686-4a11-496e-9d28-1b3e80fac85d")
        @Override
        public Object visitBpmnCallActivity(BpmnCallActivity obj) {
            setTheResult(false);
            return null;
        }

        @objid ("f1ecc3da-c9ab-40d1-b16a-ffc4373dfdf0")
        @Override
        public Object visitBpmnCancelEventDefinition(BpmnCancelEventDefinition obj) {
            setTheResult(false);
            return null;
        }

        @objid ("fe1a269a-04e6-41bb-bf2a-e91c24b4cfd3")
        @Override
        public Object visitBpmnCatchEvent(BpmnCatchEvent obj) {
            setTheResult(false);
            return null;
        }

        @objid ("ff86211f-1945-483d-83b7-b3be6b3c3b74")
        @Override
        public Object visitBpmnCollaboration(BpmnCollaboration obj) {
            setTheResult(false);
            return null;
        }

        @objid ("a6911f91-c680-4013-ad8e-22fc12439cf8")
        @Override
        public Object visitBpmnCollaborationDiagram(BpmnCollaborationDiagram obj) {
            setTheResult(false);
            return null;
        }

        @objid ("a45d4e04-76c9-4797-8c16-0d23e52ee6fa")
        @Override
        public Object visitBpmnCompensateEventDefinition(BpmnCompensateEventDefinition obj) {
            setTheResult(false);
            return null;
        }

        @objid ("10fd21a5-472d-4141-b2e1-5a3b71b77a40")
        @Override
        public Object visitBpmnComplexBehaviorDefinition(BpmnComplexBehaviorDefinition obj) {
            setTheResult(false);
            return null;
        }

        @objid ("7904035f-0ce2-4c3f-a1c7-c8f8347c86b5")
        @Override
        public Object visitBpmnComplexGateway(BpmnComplexGateway obj) {
            setTheResult(false);
            return null;
        }

        @objid ("1b837b7f-99bb-4278-b02a-454367187429")
        @Override
        public Object visitBpmnConditionalEventDefinition(BpmnConditionalEventDefinition obj) {
            setTheResult(false);
            return null;
        }

        @objid ("d006c22b-9a8d-4cb9-bf82-6dc0beff6688")
        @Override
        public Object visitBpmnDataAssociation(BpmnDataAssociation obj) {
            setTheResult(false);
            return null;
        }

        @objid ("7d026f70-ae62-4fe4-aff1-a7dc622c12b8")
        @Override
        public Object visitBpmnDataInput(BpmnDataInput obj) {
            setTheResult(false);
            return null;
        }

        @objid ("06707b8a-453c-4efd-9bf1-1ea24836c4d4")
        @Override
        public Object visitBpmnDataObject(BpmnDataObject obj) {
            setTheResult(false);
            return null;
        }

        @objid ("e34c3d51-3b0e-45f4-b323-da397aa61b56")
        @Override
        public Object visitBpmnDataOutput(BpmnDataOutput obj) {
            setTheResult(false);
            return null;
        }

        @objid ("dc19f0dc-9402-443e-b15a-1e9dec4ae2b9")
        @Override
        public Object visitBpmnDataState(BpmnDataState obj) {
            setTheResult(false);
            return null;
        }

        @objid ("eb893c27-17d6-436f-9666-4eaa3dcbcdc2")
        @Override
        public Object visitBpmnDataStore(BpmnDataStore obj) {
            setTheResult(false);
            return null;
        }

        @objid ("efd85f5d-b6eb-4140-a327-f1c72b3cd50c")
        @Override
        public Object visitBpmnEndEvent(BpmnEndEvent obj) {
            setTheResult(false);
            return null;
        }

        @objid ("1c896fa6-7f93-4606-b4d1-e1e2cabe0d64")
        @Override
        public Object visitBpmnEndPoint(BpmnEndPoint obj) {
            setTheResult(false);
            return null;
        }

        @objid ("4f3ac069-da09-4ab8-8f5b-7aade7cbbff2")
        @Override
        public Object visitBpmnErrorEventDefinition(BpmnErrorEventDefinition obj) {
            setTheResult(false);
            return null;
        }

        @objid ("a2c1e50e-771a-4424-a4eb-26892dc42871")
        @Override
        public Object visitBpmnEscalationEventDefinition(BpmnEscalationEventDefinition obj) {
            setTheResult(false);
            return null;
        }

        @objid ("cbbeec4f-8e77-450a-bae8-4e926642aa17")
        @Override
        public Object visitBpmnEvent(BpmnEvent obj) {
            setTheResult(false);
            return null;
        }

        @objid ("a7cee663-663b-4805-8dfc-181338bc502b")
        @Override
        public Object visitBpmnEventBasedGateway(BpmnEventBasedGateway obj) {
            setTheResult(false);
            return null;
        }

        @objid ("356bd121-cd65-4191-b9e7-9af8675c44e7")
        @Override
        public Object visitBpmnEventDefinition(BpmnEventDefinition obj) {
            setTheResult(false);
            return null;
        }

        @objid ("c9cc3efb-f510-423f-a2b7-0e43f7438e6f")
        @Override
        public Object visitBpmnExclusiveGateway(BpmnExclusiveGateway obj) {
            setTheResult(false);
            return null;
        }

        @objid ("c831cc14-8f28-48d9-b004-0bc433ab0717")
        @Override
        public Object visitBpmnFlowElement(BpmnFlowElement obj) {
            setTheResult(false);
            return null;
        }

        @objid ("8dda8f26-d337-4002-9b06-a53cb868e550")
        @Override
        public Object visitBpmnFlowNode(BpmnFlowNode obj) {
            setTheResult(false);
            return null;
        }

        @objid ("5ca88cfb-f7c4-4ec1-9357-c6557507f073")
        @Override
        public Object visitBpmnGateway(BpmnGateway obj) {
            setTheResult(false);
            return null;
        }

        @objid ("a90d1387-2275-4b7f-9b61-bde374b37675")
        @Override
        public Object visitBpmnGroup(BpmnGroup obj) {
            setTheResult(false);
            return null;
        }

        @objid ("8abcbd68-2967-4999-a12d-d4afe3b0cd19")
        @Override
        public Object visitBpmnImplicitThrowEvent(BpmnImplicitThrowEvent obj) {
            setTheResult(false);
            return null;
        }

        @objid ("ea927bd2-791b-47c5-9863-ac09c1d05120")
        @Override
        public Object visitBpmnInclusiveGateway(BpmnInclusiveGateway obj) {
            setTheResult(false);
            return null;
        }

        @objid ("c70d4c5a-e6c2-497e-b784-3e5e00e036d6")
        @Override
        public Object visitBpmnInterface(BpmnInterface obj) {
            setTheResult(false);
            return null;
        }

        @objid ("ca3328cd-6412-4798-b91a-98c962ccd5e4")
        @Override
        public Object visitBpmnIntermediateCatchEvent(BpmnIntermediateCatchEvent obj) {
            setTheResult(false);
            return null;
        }

        @objid ("c35d1fb8-d228-42ac-a543-a82977362d9d")
        @Override
        public Object visitBpmnIntermediateThrowEvent(BpmnIntermediateThrowEvent obj) {
            setTheResult(false);
            return null;
        }

        @objid ("ea5a55b8-8212-4cd0-8d64-bd737a31d1bd")
        @Override
        public Object visitBpmnItemAwareElement(BpmnItemAwareElement obj) {
            setTheResult(false);
            return null;
        }

        @objid ("7da59539-bb73-4442-a677-3c01a7fa9ed2")
        @Override
        public Object visitBpmnItemDefinition(BpmnItemDefinition obj) {
            setTheResult(false);
            return null;
        }

        @objid ("6452c333-21d3-404e-b260-3452a075c99c")
        @Override
        public Object visitBpmnLane(BpmnLane obj) {
            setTheResult(false);
            return null;
        }

        @objid ("ec6e4113-f998-4f32-aa30-62035eaa1648")
        @Override
        public Object visitBpmnLaneSet(BpmnLaneSet obj) {
            setTheResult(false);
            return null;
        }

        @objid ("d3043548-aa31-410a-84e7-b5b3595ea5d0")
        @Override
        public Object visitBpmnLinkEventDefinition(BpmnLinkEventDefinition obj) {
            setTheResult(false);
            return null;
        }

        @objid ("89ab0150-1018-4411-bb97-28b0de3064b6")
        @Override
        public Object visitBpmnLoopCharacteristics(BpmnLoopCharacteristics obj) {
            setTheResult(false);
            return null;
        }

        @objid ("c65a4503-f46a-46ef-bdeb-1b15381f10b7")
        @Override
        public Object visitBpmnManualTask(BpmnManualTask obj) {
            setTheResult(false);
            return null;
        }

        @objid ("fc70498b-0e3d-4f68-8176-dce93933139e")
        @Override
        public Object visitBpmnMessage(BpmnMessage obj) {
            setTheResult(false);
            return null;
        }

        @objid ("b3fbdda8-f409-4a89-ae52-094498ac35ea")
        @Override
        public Object visitBpmnMessageEventDefinition(BpmnMessageEventDefinition obj) {
            setTheResult(false);
            return null;
        }

        @objid ("7a807725-f138-4b30-b8ba-0908034fec1c")
        @Override
        public Object visitBpmnMessageFlow(BpmnMessageFlow obj) {
            setTheResult(false);
            return null;
        }

        @objid ("87203211-a90c-4a94-9807-dedb60a0d654")
        @Override
        public Object visitBpmnMultiInstanceLoopCharacteristics(BpmnMultiInstanceLoopCharacteristics obj) {
            setTheResult(false);
            return null;
        }

        @objid ("18c12897-1020-4aa9-94cf-2bb2728a0d27")
        @Override
        public Object visitBpmnOperation(BpmnOperation obj) {
            setTheResult(false);
            return null;
        }

        @objid ("fd779bd2-1b5d-42f4-aa19-260977a04852")
        @Override
        public Object visitBpmnParallelGateway(BpmnParallelGateway obj) {
            setTheResult(false);
            return null;
        }

        @objid ("8d5519f6-9ccf-4098-8dcc-c1649154e083")
        @Override
        public Object visitBpmnParticipant(BpmnParticipant obj) {
            setTheResult(false);
            return null;
        }

        @objid ("6f527b02-d537-4fe6-87a8-3e9a02b7d58e")
        @Override
        public Object visitBpmnProcess(BpmnProcess obj) {
            setTheResult(false);
            return null;
        }

        @objid ("2b2820c0-96a2-4405-9358-d70087caaa87")
        @Override
        public Object visitBpmnProcessCollaborationDiagram(BpmnProcessCollaborationDiagram obj) {
            setTheResult(false);
            return null;
        }

        @objid ("26fb2d95-32c1-4c4a-8eda-50651ca7a5f5")
        @Override
        public Object visitBpmnProcessDesignDiagram(BpmnProcessDesignDiagram obj) {
            setTheResult(false);
            return null;
        }

        @objid ("2553ff7a-76b6-4c3a-b744-d7ac271aac36")
        @Override
        public Object visitBpmnReceiveTask(BpmnReceiveTask obj) {
            setTheResult(false);
            return null;
        }

        @objid ("ec5279ee-6f8e-4b91-bd64-be9f66d7672f")
        @Override
        public Object visitBpmnResource(BpmnResource obj) {
            setTheResult(false);
            return null;
        }

        @objid ("b203e552-1de6-4d43-81ec-faee4085e851")
        @Override
        public Object visitBpmnResourceParameter(BpmnResourceParameter obj) {
            setTheResult(false);
            return null;
        }

        @objid ("e2e0476a-31dc-44ab-98e2-3bb831c56660")
        @Override
        public Object visitBpmnResourceParameterBinding(BpmnResourceParameterBinding obj) {
            setTheResult(false);
            return null;
        }

        @objid ("933f5f11-b4db-41c6-b048-9f1ca5f7e090")
        @Override
        public Object visitBpmnResourceRole(BpmnResourceRole obj) {
            setTheResult(false);
            return null;
        }

        @objid ("974b42dc-9ba5-483e-b2b9-39c9c5315144")
        @Override
        public Object visitBpmnScriptTask(BpmnScriptTask obj) {
            setTheResult(false);
            return null;
        }

        @objid ("a140a06c-d12f-482e-a17d-4f5987ffa398")
        @Override
        public Object visitBpmnSendTask(BpmnSendTask obj) {
            setTheResult(false);
            return null;
        }

        @objid ("03e484dd-e3a4-48cf-b536-ce9beb4f95df")
        @Override
        public Object visitBpmnSequenceFlow(BpmnSequenceFlow obj) {
            setTheResult(false);
            return null;
        }

        @objid ("66c50fa7-ad25-4283-bdee-31c36c446b8f")
        @Override
        public Object visitBpmnSequenceFlowDataAssociation(BpmnSequenceFlowDataAssociation obj) {
            setTheResult(false);
            return null;
        }

        @objid ("d590c5d4-ed54-4132-91f6-7e63616b64b3")
        @Override
        public Object visitBpmnServiceTask(BpmnServiceTask obj) {
            setTheResult(false);
            return null;
        }

        @objid ("b4dd8eb2-baa7-4101-87e5-4fa1cf3afbfe")
        @Override
        public Object visitBpmnSharedDefinitions(BpmnSharedDefinitions obj) {
            setTheResult(false);
            return null;
        }

        @objid ("57548f20-6084-4e00-a954-5a2948b1adea")
        @Override
        public Object visitBpmnSharedElement(BpmnSharedElement obj) {
            setTheResult(false);
            return null;
        }

        @objid ("6516e4e1-9209-42ea-a2a2-922a9eaf34fd")
        @Override
        public Object visitBpmnSignalEventDefinition(BpmnSignalEventDefinition obj) {
            setTheResult(false);
            return null;
        }

        @objid ("75619ba4-8ee9-4166-afc3-53a31095ef57")
        @Override
        public Object visitBpmnStandardLoopCharacteristics(BpmnStandardLoopCharacteristics obj) {
            setTheResult(false);
            return null;
        }

        @objid ("f2d1fe62-4d86-4b76-a397-19f373c36e6c")
        @Override
        public Object visitBpmnStartEvent(BpmnStartEvent obj) {
            setTheResult(false);
            return null;
        }

        @objid ("b35cb63b-ce9e-4926-b8bf-e7b9ca8acae8")
        @Override
        public Object visitBpmnSubProcess(BpmnSubProcess obj) {
            setTheResult(false);
            return null;
        }

        @objid ("a193b58b-fafe-4bbf-a3c7-3ad4be068d7b")
        @Override
        public Object visitBpmnSubProcessDiagram(BpmnSubProcessDiagram obj) {
            setTheResult(false);
            return null;
        }

        @objid ("2b43c7a0-da75-47ed-a603-eaf043d39720")
        @Override
        public Object visitBpmnTask(BpmnTask obj) {
            setTheResult(false);
            return null;
        }

        @objid ("9a03a164-078e-4c58-9687-e51041f3f27b")
        @Override
        public Object visitBpmnTerminateEventDefinition(BpmnTerminateEventDefinition obj) {
            setTheResult(false);
            return null;
        }

        @objid ("27351849-631c-41dc-8385-add05664f756")
        @Override
        public Object visitBpmnThrowEvent(BpmnThrowEvent obj) {
            setTheResult(false);
            return null;
        }

        @objid ("ba6b8716-d1a8-49a9-9998-d6628d056990")
        @Override
        public Object visitBpmnTimerEventDefinition(BpmnTimerEventDefinition obj) {
            setTheResult(false);
            return null;
        }

        @objid ("9fe1eb2d-6afe-4463-bc1d-7fae1f56c99f")
        @Override
        public Object visitBpmnTransaction(BpmnTransaction obj) {
            setTheResult(false);
            return null;
        }

        @objid ("c3543841-d67e-4f53-ae69-76c0e05178e4")
        @Override
        public Object visitBpmnUserTask(BpmnUserTask obj) {
            setTheResult(false);
            return null;
        }

        @objid ("59dcbf28-727c-410c-8a84-c2a444dd3a90")
        @Override
        public Object visitCallAction(CallAction obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("9a424161-61ae-4cc1-a846-4367bce86c53")
        @Override
        public Object visitCallBehaviorAction(CallBehaviorAction obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("d1317025-affc-4c29-b970-26ee2cd954b8")
        @Override
        public Object visitCallOperationAction(CallOperationAction obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("a00714f5-6ecc-4a51-a724-721b87a5ec65")
        @Override
        public Object visitCentralBufferNode(CentralBufferNode obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("393d8b4d-cdcf-489b-8698-f900c8207ba2")
        @Override
        public Object visitChoicePseudoState(ChoicePseudoState obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("c78e8b3a-b3e9-4c8f-98ad-6c04df193ec5")
        @Override
        public Object visitClass(Class obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("fdfa8bc3-ebaa-47c3-ae67-6c6e821d9bde")
        @Override
        public Object visitClassDiagram(ClassDiagram obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("16147946-7c33-4a7c-a432-db012bc201ad")
        @Override
        public Object visitClassifier(Classifier obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("71ebb511-c592-41a0-8f84-d823dd72e4d4")
        @Override
        public Object visitCollaboration(Collaboration obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("3e505f9a-9e7d-41b2-bfa7-1713dc9b2e58")
        @Override
        public Object visitCombinedFragment(CombinedFragment obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("bd429288-8d6c-4972-8ec4-d284a86a0ae1")
        @Override
        public Object visitCommunicationChannel(CommunicationChannel obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("832ecb93-6468-4e29-9480-43410d49a309")
        @Override
        public Object visitCommunicationDiagram(CommunicationDiagram obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("fe0096e6-8eba-4dc4-ac02-a3689b381001")
        @Override
        public Object visitCommunicationInteraction(CommunicationInteraction obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("7a8205ea-bf73-4775-96ea-17f2e1ae98f0")
        @Override
        public Object visitCommunicationMessage(CommunicationMessage obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("c6086189-5484-4bb8-b25f-9fe763f6a798")
        @Override
        public Object visitCommunicationNode(CommunicationNode obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("3e26d86a-14ad-47e5-a968-a36514dd496c")
        @Override
        public Object visitComponent(Component obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("bd923b9a-165a-41a4-8d46-489c994170b8")
        @Override
        public Object visitCompositeStructureDiagram(CompositeStructureDiagram obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("c10ce7e6-7632-4863-a954-901af7b1f864")
        @Override
        public Object visitConditionalNode(ConditionalNode obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("f018d3fe-7b49-48d6-b4f8-980f2714ef57")
        @Override
        public Object visitControlFlow(ControlFlow obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("0828be55-74e2-4853-acee-b8ba9a0aa23f")
        @Override
        public Object visitControlNode(ControlNode obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("a27b75e3-1817-4bea-8b09-f64f4be3e25e")
        @Override
        public Object visitDataStoreNode(DataStoreNode obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("f9e559bf-43bd-4588-862e-1bdf532979ef")
        @Override
        public Object visitDecisionMergeNode(DecisionMergeNode obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("bdcd6252-9b0d-4eed-8dd7-2516c95b40ac")
        @Override
        public Object visitDeepHistoryPseudoState(DeepHistoryPseudoState obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("5ae56e30-f055-4603-99ca-7aa98d3d8700")
        @Override
        public Object visitDeploymentDiagram(DeploymentDiagram obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("2b5dede5-37df-4b8d-8ae1-d19424021232")
        @Override
        public Object visitDurationConstraint(DurationConstraint obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("af213bc2-782c-43c1-bec5-b6e62a097a74")
        @Override
        public Object visitEntryPointPseudoState(EntryPointPseudoState obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("d129d6ca-abdc-45d6-a2a9-fdeae24f5837")
        @Override
        public Object visitEnumeration(Enumeration obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("296d6c0b-4a56-4c5a-9c49-b44822060605")
        @Override
        public Object visitExecutionOccurenceSpecification(ExecutionOccurenceSpecification obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("e03159ef-f687-4693-b7bd-7d9f4d68f5e7")
        @Override
        public Object visitExecutionSpecification(ExecutionSpecification obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("a5bc4823-bee0-4997-bde7-dafe8614c580")
        @Override
        public Object visitExitPointPseudoState(ExitPointPseudoState obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("7dda9197-f05e-4aa0-aba0-2e74360582c2")
        @Override
        public Object visitFinalNode(FinalNode obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("5b67e434-2ff4-48b6-906c-fba7d06f2a06")
        @Override
        public Object visitFlowFinalNode(FlowFinalNode obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("042ce3c2-ae26-4d6e-a595-214290748676")
        @Override
        public Object visitForkJoinNode(ForkJoinNode obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("9f9cee11-c239-46cc-a3d5-287d6730fed8")
        @Override
        public Object visitForkPseudoState(ForkPseudoState obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("c2db44d7-9bed-44b7-8afa-410ba88128b4")
        @Override
        public Object visitGate(Gate obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("a55ff677-7039-4d11-9e19-293278efd3b8")
        @Override
        public Object visitGeneralClass(GeneralClass obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("beee6002-b785-44a5-8ba2-3270da24d0e1")
        @Override
        public Object visitGeneralOrdering(GeneralOrdering obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("a4513971-c867-4403-a264-e5832c27fd9a")
        @Override
        public Object visitInitialNode(InitialNode obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("138576d4-2fd1-4bad-8da6-671675151364")
        @Override
        public Object visitInitialPseudoState(InitialPseudoState obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("eba1ac90-1c1b-419b-bc5c-ba14b7fa859c")
        @Override
        public Object visitInputPin(InputPin obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("e5e5c9a9-6b91-4847-a6ff-66d21dd9686a")
        @Override
        public Object visitInstanceNode(InstanceNode obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("13c523cc-a76e-451d-a0f0-57868880cb92")
        @Override
        public Object visitInteraction(Interaction obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("7979d762-65a5-472e-b96a-07454c70757a")
        @Override
        public Object visitInteractionOperand(InteractionOperand obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("58722a20-91b3-4ee5-ba47-3b03da86c6ab")
        @Override
        public Object visitInteractionUse(InteractionUse obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("f9450238-f325-4120-8451-686e39558038")
        @Override
        public Object visitInterface(Interface obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("c8a947f8-3ab2-4f00-93d6-7fc5ca44e9be")
        @Override
        public Object visitInterruptibleActivityRegion(InterruptibleActivityRegion obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("14bdd988-1210-431a-854e-3f9ce9eef2b9")
        @Override
        public Object visitJoinPseudoState(JoinPseudoState obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("56abe6fa-f78b-49c5-8b4c-3a3e137b1ead")
        @Override
        public Object visitJunctionPseudoState(JunctionPseudoState obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("2dd5fcbf-166b-44cc-9621-b5f27d8c55bb")
        @Override
        public Object visitLoopNode(LoopNode obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("41921514-ce84-41be-8cb4-c7ae0bc1702f")
        @Override
        public Object visitMessageEnd(MessageEnd obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("f42a989a-38af-4ce8-b576-304cae7c9a0a")
        @Override
        public Object visitMessageFlow(MessageFlow obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("922d0c6c-def5-4435-9406-dd306605e028")
        @Override
        public Object visitNameSpace(NameSpace obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("b2f69713-df27-4b49-87e8-571983c27656")
        @Override
        public Object visitNode(Node obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("de3fd494-cee2-404d-9074-875d699d7cbc")
        @Override
        public Object visitObjectDiagram(ObjectDiagram obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("3ebf8910-30a5-44c0-a15f-24b6fba6dc45")
        @Override
        public Object visitObjectFlow(ObjectFlow obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("9380ce3b-9484-443d-9eef-27ab130a6bf2")
        @Override
        public Object visitObjectNode(ObjectNode obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("303fb922-d323-404a-b536-05c62a8177d4")
        @Override
        public Object visitOccurrenceSpecification(OccurrenceSpecification obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("05e5951f-8bef-4f67-86e7-f5fbeb1535a8")
        @Override
        public Object visitOpaqueAction(OpaqueAction obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("9338c06b-299f-4df1-bd01-18b6d0dcdbb4")
        @Override
        public Object visitOpaqueBehavior(OpaqueBehavior obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("75f47242-2ac0-4769-8eb7-497764ba9762")
        @Override
        public Object visitOutputPin(OutputPin obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("3be98740-dd17-47da-a3b3-fdb5b16bcd9d")
        @Override
        public Object visitPackage(Package obj) {
            if (ScopeChecker.this.localRoot.contains(obj)) {
                setTheResult(true);
            }else {
                contains(obj.getCompositionOwner());
            }
            return null;
        }

        @objid ("15f1784a-cd35-4423-87f3-fdb7bc49a6d1")
        @Override
        public Object visitPartDecomposition(PartDecomposition obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("c13cb2f6-45c2-4acb-b85b-b4af958dff7f")
        @Override
        public Object visitPin(Pin obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("cf6270b2-7447-493f-98bd-f1750d543415")
        @Override
        public Object visitSendSignalAction(SendSignalAction obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("292169f1-62b0-4e4e-b62a-a4f53e6a97ea")
        @Override
        public Object visitSequenceDiagram(SequenceDiagram obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("329503eb-d429-4bd1-81e9-d2335b575e9f")
        @Override
        public Object visitShallowHistoryPseudoState(ShallowHistoryPseudoState obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("2a620cca-5f3f-4782-9e0a-be91590740df")
        @Override
        public Object visitSignal(Signal obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("4987655e-1926-4877-b068-98ab08edd2eb")
        @Override
        public Object visitStateInvariant(StateInvariant obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("aa4dc927-e2f6-41eb-a3fd-0819f8537721")
        @Override
        public Object visitStateMachine(StateMachine obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("69fc3c10-a617-48dc-b8cb-28f64e8a31bf")
        @Override
        public Object visitStateMachineDiagram(StateMachineDiagram obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("0a9bc1c3-2878-4fce-a8b0-470b5211ff4d")
        @Override
        public Object visitStructuralFeature(StructuralFeature obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("be432b00-ddea-4819-a5a9-d131e81c9e14")
        @Override
        public Object visitStructuredActivityNode(StructuredActivityNode obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("48caa063-f12f-42f9-889d-674da187e385")
        @Override
        public Object visitTerminatePseudoState(TerminatePseudoState obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("f083a6af-6f2f-4425-bc74-33287aaa428a")
        @Override
        public Object visitTerminateSpecification(TerminateSpecification obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("b7840cc1-3699-4344-b547-c0b9503fe3f9")
        @Override
        public Object visitUmlModelElement(UmlModelElement obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("24f1a7c4-e093-43ff-bc43-0e6681dce399")
        @Override
        public Object visitUsage(Usage obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("2b45fea1-1583-4807-a94d-1ca3d0647aee")
        @Override
        public Object visitUseCase(UseCase obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("599aaf07-c247-4597-82a4-d131b29d4550")
        @Override
        public Object visitUseCaseDiagram(UseCaseDiagram obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

        @objid ("4e30e219-7bf7-4765-9fb9-3da57c8dbc5e")
        @Override
        public Object visitValuePin(ValuePin obj) {
            contains(obj.getCompositionOwner());
            return null;
        }

    }

}
