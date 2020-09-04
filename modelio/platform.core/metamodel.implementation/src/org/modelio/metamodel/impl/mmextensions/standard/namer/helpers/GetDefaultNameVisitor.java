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

package org.modelio.metamodel.impl.mmextensions.standard.namer.helpers;

import java.util.List;
import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.activities.BpmnAdHocSubProcess;
import org.modelio.metamodel.bpmn.activities.BpmnBusinessRuleTask;
import org.modelio.metamodel.bpmn.activities.BpmnCallActivity;
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
import org.modelio.metamodel.bpmn.events.BpmnCompensateEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnConditionalEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnEndEvent;
import org.modelio.metamodel.bpmn.events.BpmnErrorEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnEscalationEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnIntermediateCatchEvent;
import org.modelio.metamodel.bpmn.events.BpmnIntermediateThrowEvent;
import org.modelio.metamodel.bpmn.events.BpmnLinkEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnMessageEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnSignalEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnStartEvent;
import org.modelio.metamodel.bpmn.events.BpmnTerminateEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnTimerEventDefinition;
import org.modelio.metamodel.bpmn.flows.BpmnMessage;
import org.modelio.metamodel.bpmn.flows.BpmnMessageFlow;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.gateways.BpmnComplexGateway;
import org.modelio.metamodel.bpmn.gateways.BpmnEventBasedGateway;
import org.modelio.metamodel.bpmn.gateways.BpmnExclusiveGateway;
import org.modelio.metamodel.bpmn.gateways.BpmnInclusiveGateway;
import org.modelio.metamodel.bpmn.gateways.BpmnParallelGateway;
import org.modelio.metamodel.bpmn.objects.BpmnDataInput;
import org.modelio.metamodel.bpmn.objects.BpmnDataObject;
import org.modelio.metamodel.bpmn.objects.BpmnDataOutput;
import org.modelio.metamodel.bpmn.objects.BpmnDataState;
import org.modelio.metamodel.bpmn.objects.BpmnDataStore;
import org.modelio.metamodel.bpmn.objects.BpmnItemDefinition;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnCollaboration;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnParticipant;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.resources.BpmnResource;
import org.modelio.metamodel.bpmn.resources.BpmnResourceParameter;
import org.modelio.metamodel.bpmn.resources.BpmnResourceParameterBinding;
import org.modelio.metamodel.bpmn.resources.BpmnResourceRole;
import org.modelio.metamodel.bpmn.rootElements.BpmnGroup;
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
import org.modelio.metamodel.uml.behavior.activityModel.ActivityFinalNode;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityParameterNode;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityPartition;
import org.modelio.metamodel.uml.behavior.activityModel.CallBehaviorAction;
import org.modelio.metamodel.uml.behavior.activityModel.CallOperationAction;
import org.modelio.metamodel.uml.behavior.activityModel.CentralBufferNode;
import org.modelio.metamodel.uml.behavior.activityModel.Clause;
import org.modelio.metamodel.uml.behavior.activityModel.ConditionalNode;
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
import org.modelio.metamodel.uml.behavior.activityModel.OpaqueAction;
import org.modelio.metamodel.uml.behavior.activityModel.OutputPin;
import org.modelio.metamodel.uml.behavior.activityModel.SendSignalAction;
import org.modelio.metamodel.uml.behavior.activityModel.StructuredActivityNode;
import org.modelio.metamodel.uml.behavior.activityModel.ValuePin;
import org.modelio.metamodel.uml.behavior.commonBehaviors.BehaviorParameter;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationInteraction;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationMessage;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationNode;
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
import org.modelio.metamodel.uml.behavior.stateMachineModel.KindOfStateMachine;
import org.modelio.metamodel.uml.behavior.stateMachineModel.ShallowHistoryPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.metamodel.uml.behavior.stateMachineModel.TerminatePseudoState;
import org.modelio.metamodel.uml.behavior.usecaseModel.Actor;
import org.modelio.metamodel.uml.behavior.usecaseModel.ExtensionPoint;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.informationFlow.InformationItem;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Substitution;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.metamodel.uml.statik.CollaborationUse;
import org.modelio.metamodel.uml.statik.ComponentRealization;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.metamodel.uml.statik.EnumerationLiteral;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.Link;
import org.modelio.metamodel.uml.statik.LinkEnd;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.metamodel.visitors.DefaultModelVisitor;
import org.modelio.vbasic.i18n.MessageBundle;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("916ec94d-3e83-4377-8a92-abd5dc0fb912")
public class GetDefaultNameVisitor extends DefaultModelVisitor {
    @objid ("a195602d-fb57-4aa4-977c-89231dc74ef4")
    private static final MessageBundle I18N = new MessageBundle(ResourceBundle.getBundle("standard_default_name"));

    @objid ("0f12a474-80de-4ab4-9a14-3f2c8b6fb193")
    public GetDefaultNameVisitor() {
        super();
    }

    @objid ("b7c9ba03-c50e-4423-a522-4528fd70c3b2")
    public String getDefaultName(MObject element) {
        String name = (String) element.accept(this);
        
        if (name == null) {
            name = "";
        }
        return name;
    }

    @objid ("1d1cafb0-f330-4cb2-8790-478cefc37a3e")
    @Override
    public Object visitAcceptCallEventAction(AcceptCallEventAction theAcceptCallEventAction) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.AcceptCallEventAction");
    }

    @objid ("6e3d6af2-9929-4b93-b471-5e27a7c9b042")
    @Override
    public Object visitAcceptChangeEventAction(AcceptChangeEventAction theAcceptChangeEventAction) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.AcceptChangeEventAction");
    }

    @objid ("1e62c059-0dbd-4eb9-affd-69b48afa7dcd")
    @Override
    public Object visitAcceptSignalAction(AcceptSignalAction theAcceptSignalAction) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.AcceptSignalAction");
    }

    @objid ("cccb452d-ebf2-4fef-9964-ea9fa22ba21c")
    @Override
    public Object visitAcceptTimeEventAction(AcceptTimeEventAction theAcceptTimeEventAction) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.AcceptTimeEventAction");
    }

    @objid ("1201982b-7c34-418c-b790-65e3455b7860")
    @Override
    public Object visitActivityDiagram(ActivityDiagram theActivityDiagram) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.ActivityDiagram", theActivityDiagram.getOrigin().getName());
    }

    @objid ("f15381b6-09dd-4e4c-a9a4-7bb6aa5a7a14")
    @Override
    public Object visitActivityFinalNode(ActivityFinalNode theActivityFinalNode) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.ActivityFinalNode");
    }

    @objid ("3e337800-c53e-4c78-af08-1bef1252777f")
    @Override
    public Object visitActivityParameterNode(ActivityParameterNode obj) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.ActivityParameterNode");
    }

    @objid ("eeca97d0-c9d5-4a7a-9795-b9524376ea71")
    @Override
    public Object visitActivityPartition(ActivityPartition theActivityPartition) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.ActivityPartition");
    }

    @objid ("d001ca40-b5cf-4dff-af6b-b3a87bcea349")
    @Override
    public Object visitAssociation(Association theAssociation) {
        return "";
    }

    @objid ("8997e60a-9e51-42f7-bc92-fd609f70e899")
    @Override
    public Object visitAssociationEnd(AssociationEnd theAssociationEnd) {
        final Classifier oppositeClass = theAssociationEnd.getTarget();
        
        // No default name for communication links
        if (oppositeClass instanceof Actor || oppositeClass instanceof UseCase) {
            MObject owner = theAssociationEnd.getCompositionOwner();
            if (owner instanceof Actor || owner instanceof UseCase) {
                return "";
            }
        }
        
        if (theAssociationEnd.isNavigable() && oppositeClass != null) {
            String roleName = oppositeClass.getName();
            if (roleName.length() > 0) {
                roleName = roleName.substring(0, 1).toLowerCase() + roleName.substring(1);
            } else {
                roleName = "";
            }
            return roleName;
        } else {
            return "";
        }
    }

    @objid ("d4f0f9d9-54df-4a7d-b453-1a2408c567a5")
    @Override
    public Object visitBehaviorDiagram(BehaviorDiagram theBehaviorDiagram) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.BehaviorDiagram", theBehaviorDiagram.getOrigin().getName());
    }

    @objid ("f363c42c-4afa-428d-9e75-ab8be6801262")
    @Override
    public Object visitBehaviorParameter(BehaviorParameter theBehaviorParameter) {
        if (theBehaviorParameter.getReturned() != null) {
            return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.BehaviorParameter.Returned");
        } else {
            return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.BehaviorParameter");
        }
    }

    @objid ("389f23d0-4048-46c9-914f-db01ef552034")
    @Override
    public Object visitBindableInstance(BindableInstance theBindableInstance) {
        BindableInstance bindableInstance = theBindableInstance;
        final NameSpace owner = bindableInstance.getOwner();
        if (owner instanceof Collaboration && ((Collaboration) owner).getBRepresented() == null) {
            return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.BindableInstance.Collaboration");
        } else {
            return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.BindableInstance");
        }
    }

    @objid ("876f127a-558a-42ab-8871-01daff0dc4d0")
    @Override
    public Object visitBpmnAdHocSubProcess(BpmnAdHocSubProcess theBpmnAdHocSubProcess) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.Bpmn.AdHocSubProcess");
    }

    @objid ("cb7f3f9f-18fd-4ed7-b527-d13d1c17e743")
    @Override
    public Object visitBpmnBoundaryEvent(BpmnBoundaryEvent theBpmnBoundaryEvent) {
        List<BpmnEventDefinition> definitions = theBpmnBoundaryEvent.getEventDefinitions();
        if (definitions.size() == 1) {
            BpmnEventDefinition def = definitions.get(0);
            String type = def.getMClass().getName().replace("EventDefinition", "").replace("Bpmn", "");
            return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.Bpmn.BoundaryEvent." + type);
        } else {
            return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.Bpmn.BoundaryEvent");
        }
    }

    @objid ("dd600b1a-8b09-48c1-bec3-c945fe11bd20")
    @Override
    public Object visitBpmnBusinessRuleTask(BpmnBusinessRuleTask theBpmnBusinessRuleTask) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.Bpmn.BusinessRuleTask");
    }

    @objid ("5660acc4-78bd-40e3-9608-e1f2d301a54d")
    @Override
    public Object visitBpmnCallActivity(BpmnCallActivity theBpmnCallActivity) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.Bpmn.CallActivity");
    }

    @objid ("1e60dc52-3b1a-4c2f-98e7-67f35ddbecc4")
    @Override
    public Object visitBpmnCancelEventDefinition(final BpmnCancelEventDefinition theBpmnUserTask) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.Bpmn.BpmnCancelEventDefinition");
    }

    @objid ("bae61fe7-688d-47a6-b55f-f5f877ae73df")
    @Override
    public Object visitBpmnCollaboration(final BpmnCollaboration theBpmnCollaboration) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.Bpmn.BpmnCollaboration");
    }

    @objid ("eb879425-faf5-4970-954d-336db08d84fe")
    @Override
    public Object visitBpmnCollaborationDiagram(BpmnCollaborationDiagram theBpmnCollaborationDiagram) {
        ModelElement collab = theBpmnCollaborationDiagram.getOrigin();
        if (collab instanceof BpmnCollaboration) {
            BpmnProcess process = ((BpmnCollaboration) collab).getDefinedProcess();
            if (process != null) {
                return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.BpmnCollaborationDiagram", process.getName());
            }
        }
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.BpmnCollaborationDiagram", "");
    }

    @objid ("f1a35a7d-f059-4f09-ba41-147ac6ab8606")
    @Override
    public Object visitBpmnCompensateEventDefinition(final BpmnCompensateEventDefinition theBpmnUserTask) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.Bpmn.BpmnLinkEventDefinition");
    }

    @objid ("1ce12137-b54e-40f3-ad0c-5283c7c412fa")
    @Override
    public Object visitBpmnComplexGateway(BpmnComplexGateway theBpmnComplexGateway) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.Bpmn.ComplexGateway");
    }

    @objid ("82e1dd5f-df3a-4f83-97f2-180a18608337")
    @Override
    public Object visitBpmnConditionalEventDefinition(final BpmnConditionalEventDefinition theBpmnUserTask) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.Bpmn.BpmnConditionalEventDefinition");
    }

    @objid ("b150297c-8f65-4960-b915-d9e160f35f1d")
    @Override
    public Object visitBpmnDataInput(final BpmnDataInput theBpmnDataInput) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.Bpmn.BpmnDataInput");
    }

    @objid ("aba155d0-20b7-4328-9512-3cdbd07bb97f")
    @Override
    public Object visitBpmnDataObject(final BpmnDataObject theBpmnDataObject) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.Bpmn.BpmnDataObject");
    }

    @objid ("bbf059e6-9475-42a3-bcf2-9f25e66db3f3")
    @Override
    public Object visitBpmnDataOutput(final BpmnDataOutput theBpmnDataOutput) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.Bpmn.BpmnDataOutput");
    }

    @objid ("a0ece27f-4272-4e66-a181-61b6db0b2760")
    @Override
    public Object visitBpmnDataState(BpmnDataState obj) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.Bpmn.BpmnDataState");
    }

    @objid ("bb023a02-f9e0-4637-8436-0649973e290b")
    @Override
    public Object visitBpmnDataStore(final BpmnDataStore theBpmnDataStore) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.Bpmn.BpmnDataStore");
    }

    @objid ("6de0e6a4-7786-4ee1-9c38-8aa363205961")
    @Override
    public Object visitBpmnEndEvent(BpmnEndEvent theBpmnEndEvent) {
        List<BpmnEventDefinition> definitions = theBpmnEndEvent.getEventDefinitions();
        if (definitions.size() == 1) {
            BpmnEventDefinition def = definitions.get(0);
            String type = def.getMClass().getName().replace("EventDefinition", "").replace("Bpmn", "");
            return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.Bpmn.EndEvent." + type);
        } else {
            return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.Bpmn.EndEvent");
        }
    }

    @objid ("c59f7ac0-e4fd-4124-8d1a-953405abef92")
    @Override
    public Object visitBpmnEndPoint(final BpmnEndPoint theBpmnEndPoint) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.Bpmn.BpmnEndPoint");
    }

    @objid ("4b7ef71c-b609-48cb-9ebc-a24fc9056632")
    @Override
    public Object visitBpmnErrorEventDefinition(final BpmnErrorEventDefinition theBpmnUserTask) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.Bpmn.BpmnErrorEventDefinition");
    }

    @objid ("a4212804-829e-41eb-a849-98087b05fc64")
    @Override
    public Object visitBpmnEscalationEventDefinition(final BpmnEscalationEventDefinition theBpmnUserTask) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.Bpmn.BpmnEscalationEventDefinition");
    }

    @objid ("9ee5b0d9-ef88-4644-a6d6-7d2a678e452f")
    @Override
    public Object visitBpmnEventBasedGateway(BpmnEventBasedGateway theBpmnEventBasedGateway) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.Bpmn.EventBasedGateway");
    }

    @objid ("ef5b66f0-1c51-4312-a977-4880a6be03d4")
    @Override
    public Object visitBpmnExclusiveGateway(BpmnExclusiveGateway theBpmnExclusiveGateway) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.Bpmn.ExclusiveGateway");
    }

    @objid ("b3fcc33c-a408-4bb7-a4b7-d5cc5abea12d")
    @Override
    public Object visitBpmnGroup(final BpmnGroup theBpmnGroup) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.Bpmn.BpmnGroup");
    }

    @objid ("78d24976-df61-4038-86a3-9ed2cdfcbaa9")
    @Override
    public Object visitBpmnInclusiveGateway(BpmnInclusiveGateway theBpmnInclusiveGateway) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.Bpmn.InclusiveGateway");
    }

    @objid ("e4232602-97aa-41bd-8336-251386ead6e9")
    @Override
    public Object visitBpmnInterface(final BpmnInterface theBpmnInterface) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.Bpmn.BpmnInterface");
    }

    @objid ("2e26ae9b-2c00-475b-bf1b-8db40ef967c8")
    @Override
    public Object visitBpmnIntermediateCatchEvent(BpmnIntermediateCatchEvent theBpmnIntermediateCatchEvent) {
        List<BpmnEventDefinition> definitions = theBpmnIntermediateCatchEvent.getEventDefinitions();
        if (definitions.size() == 1) {
            BpmnEventDefinition def = definitions.get(0);
            String type = def.getMClass().getName().replace("EventDefinition", "").replace("Bpmn", "");
            return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.Bpmn.IntermediateCatchEvent." + type);
        } else {
            return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.Bpmn.IntermediateCatchEvent");
        }
    }

    @objid ("26607990-6902-4d8f-a35e-79aa40b0fe0d")
    @Override
    public Object visitBpmnIntermediateThrowEvent(BpmnIntermediateThrowEvent theBpmnIntermediateThrowEvent) {
        List<BpmnEventDefinition> definitions = theBpmnIntermediateThrowEvent.getEventDefinitions();
        if (definitions.size() == 1) {
            BpmnEventDefinition def = definitions.get(0);
            String type = def.getMClass().getName().replace("EventDefinition", "").replace("Bpmn", "");
            return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.Bpmn.IntermediateThrowEvent." + type);
        } else {
            return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.Bpmn.IntermediateThrowEvent");
        }
    }

    @objid ("a7023d82-fc99-4c39-9d78-179ce01233bf")
    @Override
    public Object visitBpmnItemDefinition(final BpmnItemDefinition theBpmnItemDefinition) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.Bpmn.BpmnItemDefinition");
    }

    @objid ("87670786-263d-48c1-b01b-c9b96e794b41")
    @Override
    public Object visitBpmnLane(final BpmnLane theBpmnLane) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.Bpmn.BpmnLane");
    }

    @objid ("2d66dd6b-5b32-4b05-afa2-17a30494ec14")
    @Override
    public Object visitBpmnLinkEventDefinition(final BpmnLinkEventDefinition theBpmnUserTask) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.Bpmn.BpmnLinkEventDefinition");
    }

    @objid ("4f642df9-4b23-4109-a148-748b658a3d5f")
    @Override
    public Object visitBpmnManualTask(BpmnManualTask theBpmnManualTask) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.Bpmn.ManualTask");
    }

    @objid ("8bc5bb8f-8d14-4a39-8a3a-2b04e6b44b9b")
    @Override
    public Object visitBpmnMessage(final BpmnMessage theBpmnMessage) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.Bpmn.BpmnMessage");
    }

    @objid ("3122e39d-5ecf-4ee7-97e7-bf3a25c038e5")
    @Override
    public Object visitBpmnMessageEventDefinition(BpmnMessageEventDefinition theBpmnUserTask) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.Bpmn.BpmnMessageEventDefinition");
    }

    @objid ("d518f290-3e6c-48bd-be79-eaa8fdbddcea")
    @Override
    public Object visitBpmnMessageFlow(final BpmnMessageFlow theBpmnMessageFlow) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.Bpmn.BpmnMessageFlow");
    }

    @objid ("964fb487-d1dd-45d7-b0e2-e877c43a65ef")
    @Override
    public Object visitBpmnMultiInstanceLoopCharacteristics(BpmnMultiInstanceLoopCharacteristics obj) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.Bpmn.MultiInstanceLoopCharacteristics");
    }

    @objid ("ae5b5305-dafb-4065-9a8c-04e6a763ee97")
    @Override
    public Object visitBpmnOperation(final BpmnOperation theBpmnOperation) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.Bpmn.BpmnOperation");
    }

    @objid ("0bb4ed45-f338-4e7b-b39b-f652eabc7558")
    @Override
    public Object visitBpmnParallelGateway(BpmnParallelGateway theBpmnParallelGateway) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.Bpmn.ParallelGateway");
    }

    @objid ("45ac02df-f8cd-4b26-a6fc-399cdacb506d")
    @Override
    public Object visitBpmnParticipant(final BpmnParticipant theBpmnParticipant) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.Bpmn.BpmnParticipant");
    }

    @objid ("3ea21b1b-c9a9-4dd5-a6b5-914bc9ad72eb")
    @Override
    public Object visitBpmnProcess(BpmnProcess theBpmnProcess) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.Bpmn.Process");
    }

    @objid ("47b2a243-061d-4f1d-ad9b-ce25018a726c")
    @Override
    public Object visitBpmnProcessDesignDiagram(BpmnProcessDesignDiagram theBpmnProcessDesignDiagram) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.BpmnProcessDesignDiagram", theBpmnProcessDesignDiagram.getOrigin().getName());
    }

    @objid ("4e783f7c-fe49-4d99-b055-2e9d5e84f355")
    @Override
    public Object visitBpmnReceiveTask(BpmnReceiveTask theBpmnReceiveTask) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.Bpmn.ReceiveTask");
    }

    @objid ("e5d16753-0c17-47d7-92f6-bbde3c0a42f9")
    @Override
    public Object visitBpmnResource(final BpmnResource theBpmnResource) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.Bpmn.BpmnResource");
    }

    @objid ("380d9138-1607-4cae-abb0-cdcfc8e7f1fa")
    @Override
    public Object visitBpmnResourceParameter(final BpmnResourceParameter theBpmnResourceParameter) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.Bpmn.BpmnResourceParameter");
    }

    @objid ("ebc7adce-8f52-408e-a61b-2c2a45815ae2")
    @Override
    public Object visitBpmnResourceParameterBinding(final BpmnResourceParameterBinding theBpmnResourceParameterBinding) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.Bpmn.BpmnResourceParameterBinding");
    }

    @objid ("7b9dbcde-14ef-4903-b5b4-6bfabde67492")
    @Override
    public Object visitBpmnResourceRole(final BpmnResourceRole theBpmnResourceRole) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.Bpmn.BpmnResourceRole");
    }

    @objid ("e85d4d9e-8447-4d24-bf9e-4d213ac0c720")
    @Override
    public Object visitBpmnScriptTask(BpmnScriptTask theBpmnScriptTask) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.Bpmn.ScriptTask");
    }

    @objid ("d79c609e-449c-4b38-9269-e6469f28c76a")
    @Override
    public Object visitBpmnSendTask(BpmnSendTask theBpmnSendTask) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.Bpmn.SendTask");
    }

    @objid ("a714402e-2c7c-4d57-8fb2-a87be3c4f91b")
    @Override
    public Object visitBpmnSequenceFlow(final BpmnSequenceFlow theBpmnSequenceFlow) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.Bpmn.BpmnSequenceFlow");
    }

    @objid ("7af82367-5c5d-4a40-aadc-ffc03f0acb26")
    @Override
    public Object visitBpmnServiceTask(BpmnServiceTask theBpmnServiceTask) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.Bpmn.ServiceTask");
    }

    @objid ("9312b3c3-42d0-402b-a336-3b496a86d8d6")
    @Override
    public Object visitBpmnSignalEventDefinition(final BpmnSignalEventDefinition theBpmnUserTask) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.Bpmn.BpmnSignalEventDefinition");
    }

    @objid ("41c66859-72eb-40c2-b3d0-d3c817aa4241")
    @Override
    public Object visitBpmnStandardLoopCharacteristics(BpmnStandardLoopCharacteristics obj) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.Bpmn.StandardLoopCharacteristics");
    }

    @objid ("10b4fd95-7028-461f-84b4-fd511cd320bb")
    @Override
    public Object visitBpmnStartEvent(BpmnStartEvent theBpmnStartEvent) {
        List<BpmnEventDefinition> definitions = theBpmnStartEvent.getEventDefinitions();
        if (definitions.size() == 1) {
            BpmnEventDefinition def = definitions.get(0);
            String type = def.getMClass().getName().replace("EventDefinition", "").replace("Bpmn", "");
            return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.Bpmn.StartEvent." + type);
        } else {
            return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.Bpmn.StartEvent");
        }
    }

    @objid ("9f0b58ae-7f10-436f-abe1-1fa88209d7fb")
    @Override
    public Object visitBpmnSubProcess(BpmnSubProcess theBpmnSubProcess) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.Bpmn.SubProcess");
    }

    @objid ("fd8654eb-428f-4749-b882-c02bf206343a")
    @Override
    public Object visitBpmnSubProcessDiagram(final BpmnSubProcessDiagram theBpmnSubProcessDiagram) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.BpmnSubProcessDiagram", theBpmnSubProcessDiagram.getOrigin().getName());
    }

    @objid ("8f731f45-1b01-4afe-acaf-b6978ba5d2d8")
    @Override
    public Object visitBpmnTask(BpmnTask theTask) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.Bpmn.Task");
    }

    @objid ("71a982ab-4bb9-46d8-b483-8cf06820fac2")
    @Override
    public Object visitBpmnTerminateEventDefinition(final BpmnTerminateEventDefinition theBpmnUserTask) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.Bpmn.BpmnTerminateEventDefinition");
    }

    @objid ("58ef052f-f980-49ff-8252-14c377da532c")
    @Override
    public Object visitBpmnTimerEventDefinition(final BpmnTimerEventDefinition theBpmnUserTask) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.Bpmn.BpmnTimerEventDefinition");
    }

    @objid ("c417856d-0b11-4b89-9d6b-405caf15be96")
    @Override
    public Object visitBpmnTransaction(BpmnTransaction theBpmnTransaction) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.Bpmn.Transaction");
    }

    @objid ("70290749-bd33-41a4-9205-47734cca533c")
    @Override
    public Object visitBpmnUserTask(final BpmnUserTask theBpmnUserTask) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.Bpmn.UserTask");
    }

    @objid ("f3cf2e75-4ac8-40b2-9e13-2e25f9c0c4b6")
    @Override
    public Object visitCallBehaviorAction(CallBehaviorAction theCallBehaviorAction) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.CallBehaviorAction");
    }

    @objid ("8db4cded-38b9-47da-99ab-47fe0ff1dd69")
    @Override
    public Object visitCallOperationAction(CallOperationAction theCallOperationAction) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.CallOperationAction");
    }

    @objid ("565c19d6-d120-4edb-a402-58df301472d3")
    @Override
    public Object visitCentralBufferNode(CentralBufferNode theCentralBufferNode) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.CentralBufferNode");
    }

    @objid ("b2d9d4ff-05bb-4e7e-8b92-aa2b6795f5b8")
    @Override
    public Object visitChoicePseudoState(ChoicePseudoState theChoicePseudoState) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.ChoicePseudoState");
    }

    @objid ("fe9024cc-f2ab-4022-95d3-f7996f4c9580")
    @Override
    public Object visitClassDiagram(ClassDiagram theClassDiagram) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.ClassDiagram", theClassDiagram.getOrigin().getName());
    }

    @objid ("f1ee44d4-d274-4252-9a7b-93aa1b187a9b")
    @Override
    public Object visitClause(Clause theClause) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.Clause");
    }

    @objid ("5cd46cc9-08fd-402e-a038-cfa62d2f272f")
    @Override
    public Object visitCollaborationUse(CollaborationUse obj) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.CollaborationUse");
    }

    @objid ("560f7ede-ae1c-43eb-aa4d-5011d11c7752")
    @Override
    public Object visitCommunicationDiagram(CommunicationDiagram theCommunicationDiagram) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.CommunicationDiagram", theCommunicationDiagram.getOrigin().getName());
    }

    @objid ("74824f4b-9341-4588-b75c-5c86b184f37d")
    @Override
    public Object visitCommunicationInteraction(CommunicationInteraction theCommunicationInteraction) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.CommunicationInteraction");
    }

    @objid ("249b4e58-ac1d-4a69-86ac-e3542126e994")
    @Override
    public Object visitCommunicationMessage(CommunicationMessage theCommunicationMessage) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.CommunicationMessage");
    }

    @objid ("e30c4cd7-0e30-421d-8f6a-6509ff10a5d5")
    @Override
    public Object visitCommunicationNode(CommunicationNode obj) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.CommunicationNode");
    }

    @objid ("d0c4ec7d-686e-4e18-8976-cb8be88820a6")
    @Override
    public Object visitComponentRealization(ComponentRealization obj) {
        return "";
    }

    @objid ("730b2b5d-4075-43a0-949b-62d1147507a9")
    @Override
    public Object visitCompositeStructureDiagram(CompositeStructureDiagram theCompositeStructureDiagram) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.CompositeStructureDiagram", theCompositeStructureDiagram.getOrigin().getName());
    }

    @objid ("e58e4711-e524-4e56-acd7-ecaea6f5bf26")
    @Override
    public Object visitConditionalNode(ConditionalNode theConditionalNode) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.ConditionalNode");
    }

    @objid ("4c4409a7-e179-49b8-88e1-89526b1ff03d")
    @Override
    public Object visitConnectionPointReference(ConnectionPointReference theConnectionPointReference) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.ConnectionPointReference");
    }

    @objid ("6785f6b4-c665-4544-aec1-a4fbe204d3ed")
    @Override
    public Object visitDataStoreNode(DataStoreNode theDataStoreNode) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.DataStoreNode");
    }

    @objid ("099e2583-926c-4a8b-bd64-f50dbe63bcb5")
    @Override
    public Object visitDataType(DataType obj) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.DataType");
    }

    @objid ("47fca444-1422-47a4-b0cd-1fa78767925a")
    @Override
    public Object visitDecisionMergeNode(DecisionMergeNode theDecisionMergeNode) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.DecisionMergeNode");
    }

    @objid ("4db3f102-c144-42d8-94f5-0287be8c74d3")
    @Override
    public Object visitDeepHistoryPseudoState(DeepHistoryPseudoState theDeepHistoryPseudoState) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.DeepHistoryPseudoState");
    }

    @objid ("44a6100e-624c-478e-afdf-3e5892c5d039")
    @Override
    public Object visitDeploymentDiagram(DeploymentDiagram theDeploymentDiagram) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.DeploymentDiagram", theDeploymentDiagram.getOrigin().getName());
    }

    @objid ("7ba36d91-b7f3-431d-86c8-840a6538ed3b")
    @Override
    public Object visitEntryPointPseudoState(EntryPointPseudoState theEntryPointPseudoState) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.EntryPointPseudoState");
    }

    @objid ("a9acacad-aa4a-4cae-85d6-9e368f90437b")
    @Override
    public Object visitEnumerationLiteral(EnumerationLiteral obj) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.EnumerationLiteral");
    }

    @objid ("9b2834b5-946b-41d9-b49f-8f95e89e073d")
    @Override
    public Object visitExitPointPseudoState(ExitPointPseudoState theExitPointPseudoState) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.ExitPointPseudoState");
    }

    @objid ("2025dbda-6f68-4b23-9710-d98c8e740064")
    @Override
    public Object visitExpansionNode(ExpansionNode theExpansionNode) {
        if (theExpansionNode.getRegionAsInput() != null) {
            return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.ExpansionNode.Input");
        } else if (theExpansionNode.getRegionAsOutput() != null) {
            return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.ExpansionNode.Output");
        } else {
            return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.ExpansionNode");
        }
    }

    @objid ("ff9eef3e-e603-44b3-ba1c-7761965ee484")
    @Override
    public Object visitExpansionRegion(ExpansionRegion theExpansionRegion) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.ExpansionRegion");
    }

    @objid ("a3cd6905-d18e-4fc8-bc7e-b1abb5182fce")
    @Override
    public Object visitExtensionPoint(ExtensionPoint obj) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.ExtensionPoint");
    }

    @objid ("a3d0586d-e50b-4bd8-8fe1-17e57319fbf9")
    @Override
    public Object visitFinalState(FinalState obj) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.FinalState");
    }

    @objid ("62ebcd41-45ed-4147-9629-11237d774955")
    @Override
    public Object visitFlowFinalNode(FlowFinalNode theFlowFinalNode) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.FlowFinalNode");
    }

    @objid ("68cca311-c1a5-4c4e-ad74-f05c9cd6b6f1")
    @Override
    public Object visitForkJoinNode(ForkJoinNode theForkJoinNode) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.ForkJoinNode");
    }

    @objid ("97e7d20a-c708-4aba-819c-7c521608bc71")
    @Override
    public Object visitForkPseudoState(ForkPseudoState theForkPseudoState) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.ForkPseudoState");
    }

    @objid ("b4e0648c-4b22-4b6c-82ed-3167cea0c906")
    @Override
    public Object visitInformationItem(InformationItem obj) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.InformationItem");
    }

    @objid ("7ec7ffa1-7605-4201-b153-2e9b135c5960")
    @Override
    public Object visitInitialNode(InitialNode theInitialNode) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.InitialNode");
    }

    @objid ("28ea07f6-652c-4cf1-bc37-5b9ffaa413c6")
    @Override
    public Object visitInitialPseudoState(InitialPseudoState theInitialPseudoState) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.InitialPseudoState");
    }

    @objid ("55c5c744-e3bb-44e9-8dd7-7c20e39cba08")
    @Override
    public Object visitInputPin(InputPin theInputPin) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.InputPin");
    }

    @objid ("14f893e4-5272-427e-8e94-ed3eb6185ef5")
    @Override
    public Object visitInstanceNode(InstanceNode theInstanceNode) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.InstanceNode");
    }

    @objid ("322a93ee-1935-41c3-bbd3-1eb576b157fc")
    @Override
    public Object visitInternalTransition(InternalTransition theInternalTransition) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.InternalTransition");
    }

    @objid ("2fef5897-db07-4c0b-aeba-a97bbe83b875")
    @Override
    public Object visitInterruptibleActivityRegion(InterruptibleActivityRegion obj) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.InterruptibleActivityRegion");
    }

    @objid ("7c1cc456-543d-4643-897f-9d0b42a6f6ab")
    @Override
    public Object visitJoinPseudoState(JoinPseudoState theJoinPseudoState) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.JoinPseudoState");
    }

    @objid ("fe1138e3-7048-482b-9ea6-8838ce5d1ab9")
    @Override
    public Object visitJunctionPseudoState(JunctionPseudoState theJunctionPseudoState) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.JunctionPseudoState");
    }

    @objid ("8ff057d9-fadf-415b-b1f9-86bfdfebaa98")
    @Override
    public Object visitLink(Link theLink) {
        return "";
    }

    @objid ("49d745b1-f8ec-418c-b981-bdd24decaebb")
    @Override
    public Object visitLinkEnd(LinkEnd theLinkEnd) {
        final Instance oppositeClass = theLinkEnd.getTarget();
        if (theLinkEnd.isNavigable() && oppositeClass != null) {
            String roleName = oppositeClass.getName();
            if (roleName.length() > 0) {
                roleName = roleName.substring(0, 1).toLowerCase() + roleName.substring(1);
            } else {
                roleName = "";
            }
            return roleName;
        } else {
            return "";
        }
    }

    @objid ("661d31df-3820-4abf-85f1-1d8328b45382")
    @Override
    public Object visitLoopNode(LoopNode theLoopNode) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.LoopNode");
    }

    @objid ("0935b478-b240-419a-bd86-e133799ddeb7")
    @Override
    public Object visitObjectDiagram(ObjectDiagram theObjectDiagram) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.ObjectDiagram", theObjectDiagram.getOrigin().getName());
    }

    @objid ("36cf0f91-3815-45eb-8481-2c698963faf2")
    @Override
    public Object visitOpaqueAction(OpaqueAction theOpaqueAction) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.OpaqueAction");
    }

    @objid ("32667529-a65f-4610-b862-4f66d2bb5e90")
    @Override
    public Object visitOperation(Operation theOperation) {
        if (theOperation.isStereotyped("ModelerModule", "create")) {
            return theOperation.getOwner().getName();
        } else if (theOperation.isStereotyped("ModelerModule", "destroy")) {
            return "~" + theOperation.getOwner().getName();
        }
        return super.visitOperation(theOperation);
    }

    @objid ("d89ae931-4d08-4a06-846b-990edc6dd196")
    @Override
    public Object visitOutputPin(OutputPin theOutputPin) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.OutputPin");
    }

    @objid ("1cb07d97-fc45-4dca-b062-a4231fd583c7")
    @Override
    public Object visitParameter(Parameter theParameter) {
        if (theParameter.getReturned() != null) {
            return "";
        } else {
            return theParameter.getMClass().getName();
        }
    }

    @objid ("ae55b7e3-82fd-4e9f-b1e5-9c2c6c966694")
    @Override
    public Object visitPort(Port thePort) {
        return thePort.getMClass().getName();
    }

    @objid ("f33993a4-550f-4429-b2bd-a511347dfaf1")
    @Override
    public Object visitSendSignalAction(SendSignalAction theSendSignalAction) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.SendSignalAction");
    }

    @objid ("3f7408af-88c7-4167-870a-23df0cb5ec5f")
    @Override
    public Object visitSequenceDiagram(SequenceDiagram theSequenceDiagram) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.SequenceDiagram", theSequenceDiagram.getOrigin().getName());
    }

    @objid ("aa0411bf-5457-4db8-9a16-5f5a29cd898b")
    @Override
    public Object visitShallowHistoryPseudoState(ShallowHistoryPseudoState theShallowHistoryPseudoState) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.ShallowHistoryPseudoState");
    }

    @objid ("ebe4aceb-df72-4356-99e9-b20d7e32f61c")
    @Override
    public Object visitStateMachine(StateMachine theStateMachine) {
        if (theStateMachine.getKind() == KindOfStateMachine.PROTOCOL) {
            return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.StateMachine.Protocol");
        } else {
            return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.StateMachine");
        }
    }

    @objid ("06eb71e3-786f-4eb0-b0ce-beb8a81b01b1")
    @Override
    public Object visitStateMachineDiagram(StateMachineDiagram theStateMachineDiagram) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.StateMachineDiagram", theStateMachineDiagram.getOrigin().getName());
    }

    @objid ("fc6874a4-9495-486f-b82c-219177a6c732")
    @Override
    public Object visitStaticDiagram(StaticDiagram theStaticDiagram) {
        if (theStaticDiagram.getExtension().size() > 0) {
            final String stereotype = theStaticDiagram.getExtension().get(0).getName();
            return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.Diagram." + stereotype, theStaticDiagram.getOrigin()
                    .getName());
        } else {
            return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.Diagram.IStatic", theStaticDiagram.getOrigin().getName());
        }
    }

    @objid ("fa52e02e-0eb0-439a-868d-34a005947a3e")
    @Override
    public Object visitStructuredActivityNode(StructuredActivityNode theStructuredActivityNode) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.StructuredActivityNode");
    }

    @objid ("f726a95d-10ef-448a-bc4f-64d57a15c5ae")
    @Override
    public Object visitSubstitution(Substitution obj) {
        return "";
    }

    @objid ("f8089d21-4dc6-4635-a3b9-8afda48a119a")
    @Override
    public Object visitTemplateParameter(TemplateParameter theTemplateParameter) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.TemplateParameter");
    }

    @objid ("fb7528f3-8f05-4faf-b82d-c30cc6fb7ffb")
    @Override
    public Object visitTerminatePseudoState(TerminatePseudoState theTerminatePseudoState) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.TerminatePseudoState");
    }

    @objid ("b22b7690-cd59-4c07-912c-94d7106a86bb")
    @Override
    public Object visitUmlModelElement(UmlModelElement theModelElement) {
        return theModelElement.getMClass().getName();
    }

    @objid ("99232f76-1da3-4248-a94a-8f6e8a65c7e5")
    @Override
    public Object visitUseCase(UseCase obj) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.UseCase");
    }

    @objid ("6e0e28f2-5b3a-4ae0-9641-8586ec1c67fe")
    @Override
    public Object visitUseCaseDiagram(UseCaseDiagram theUseCaseDiagram) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.UseCaseDiagram", theUseCaseDiagram.getOrigin().getName());
    }

    @objid ("5083b605-1d8f-46bc-a9c1-37fa2ae4b30d")
    @Override
    public Object visitValuePin(ValuePin theValuePin) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.ValuePin");
    }

    @objid ("6669837f-6bc5-45e2-b085-a766ecfd76eb")
    @Override
    public Object visitPackage(Package thePackage) {
        if (thePackage.getRepresented() != null) {
            return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.Package.Root");
        }
        return super.visitPackage(thePackage);
    }

    @objid ("223ae995-252f-4841-affc-94c4448254a5")
    @Override
    public Object visitProject(Project theProject) {
        return GetDefaultNameVisitor.I18N.getMessage("$DefaultName.Project");
    }

}
