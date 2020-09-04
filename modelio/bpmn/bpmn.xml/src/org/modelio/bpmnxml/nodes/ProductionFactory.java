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

package org.modelio.bpmnxml.nodes;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.bpmnxml.nodes.finaliser.CollaborationFinaliser;
import org.modelio.bpmnxml.nodes.finaliser.DiagramFinaliser;
import org.modelio.bpmnxml.nodes.finaliser.ProcessFinaliser;
import org.modelio.bpmnxml.nodes.production.activities.AdHocSubProcessNode;
import org.modelio.bpmnxml.nodes.production.activities.BusinessTaskNode;
import org.modelio.bpmnxml.nodes.production.activities.CallActivityNode;
import org.modelio.bpmnxml.nodes.production.activities.ManualTaskNode;
import org.modelio.bpmnxml.nodes.production.activities.ReceiveTaskNode;
import org.modelio.bpmnxml.nodes.production.activities.RuleTaskNode;
import org.modelio.bpmnxml.nodes.production.activities.ScriptTaskNode;
import org.modelio.bpmnxml.nodes.production.activities.SendTaskNode;
import org.modelio.bpmnxml.nodes.production.activities.ServiceTaskNode;
import org.modelio.bpmnxml.nodes.production.activities.SubProcessNode;
import org.modelio.bpmnxml.nodes.production.activities.TaskNode;
import org.modelio.bpmnxml.nodes.production.activities.TransactionNode;
import org.modelio.bpmnxml.nodes.production.activities.UserTaskNode;
import org.modelio.bpmnxml.nodes.production.datas.DataInputNode;
import org.modelio.bpmnxml.nodes.production.datas.DataObjectNode;
import org.modelio.bpmnxml.nodes.production.datas.DataOutputNode;
import org.modelio.bpmnxml.nodes.production.datas.DataStoreNode;
import org.modelio.bpmnxml.nodes.production.datas.MessageNode;
import org.modelio.bpmnxml.nodes.production.eventdefinitions.CancelEventDefinitionNode;
import org.modelio.bpmnxml.nodes.production.eventdefinitions.CompensateEventDefinitionNode;
import org.modelio.bpmnxml.nodes.production.eventdefinitions.ConditionalEventDefinitionNode;
import org.modelio.bpmnxml.nodes.production.eventdefinitions.ErrorEventDefinitionNode;
import org.modelio.bpmnxml.nodes.production.eventdefinitions.EscalationEventDefinitionNode;
import org.modelio.bpmnxml.nodes.production.eventdefinitions.LinkEventDefinitionNode;
import org.modelio.bpmnxml.nodes.production.eventdefinitions.MessageEventDefinitionNode;
import org.modelio.bpmnxml.nodes.production.eventdefinitions.SignalEventDefinitionNode;
import org.modelio.bpmnxml.nodes.production.eventdefinitions.TerminateEventDefinitionNode;
import org.modelio.bpmnxml.nodes.production.eventdefinitions.TimerEventDefinitionNode;
import org.modelio.bpmnxml.nodes.production.events.BoundaryEventNode;
import org.modelio.bpmnxml.nodes.production.events.EndEventNode;
import org.modelio.bpmnxml.nodes.production.events.ImplicitThrowEventNode;
import org.modelio.bpmnxml.nodes.production.events.IntermediateCatchEventNode;
import org.modelio.bpmnxml.nodes.production.events.IntermediateThrowEventNode;
import org.modelio.bpmnxml.nodes.production.events.StartEventNode;
import org.modelio.bpmnxml.nodes.production.flows.DataAssociationNode;
import org.modelio.bpmnxml.nodes.production.flows.MessageFlowNode;
import org.modelio.bpmnxml.nodes.production.flows.SequenceFlowNode;
import org.modelio.bpmnxml.nodes.production.gateways.ComplexGatewayNode;
import org.modelio.bpmnxml.nodes.production.gateways.EventBasedGatewayNode;
import org.modelio.bpmnxml.nodes.production.gateways.ExclusiveGatewayNode;
import org.modelio.bpmnxml.nodes.production.gateways.InclusiveGatewayNode;
import org.modelio.bpmnxml.nodes.production.gateways.ParallelGatewayNode;
import org.modelio.bpmnxml.nodes.production.looptypes.MultiInstanceLoopNode;
import org.modelio.bpmnxml.nodes.production.looptypes.StandardLoopNode;
import org.modelio.bpmnxml.nodes.production.process.AnnotationNode;
import org.modelio.bpmnxml.nodes.production.process.AssociationNode;
import org.modelio.bpmnxml.nodes.production.process.BehaviorDiagramNode;
import org.modelio.bpmnxml.nodes.production.process.CollaborationNode;
import org.modelio.bpmnxml.nodes.production.process.LaneNode;
import org.modelio.bpmnxml.nodes.production.process.LaneSetNode;
import org.modelio.bpmnxml.nodes.production.process.ParticipantNode;
import org.modelio.bpmnxml.nodes.production.process.ProcessNode;
import org.modelio.bpmnxml.plugin.BPMNXml;
import org.modelio.metamodel.bpmn.objects.BpmnDataInput;
import org.modelio.metamodel.bpmn.objects.BpmnDataOutput;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;

@objid ("b6ed78dd-89ff-47a9-8d01-91bd04501ea5")
public class ProductionFactory {
    @objid ("085ff1ff-e8ea-48b1-b424-09446a7e9bff")
    public IProduction getImportProductionNode(Object jaxbElement) {
        switch (jaxbElement.getClass().getSimpleName()) {
        // case "TDefinitions":
        // return new BehaviorNode();
        case "TProcess":
            return new ProcessNode();
        case "TTask":
            return new TaskNode();
        case "TAdHocSubProcess":
            return new AdHocSubProcessNode();
        case "TBusinessRuleTask":
            return new BusinessTaskNode();
        case "TCallActivity":
            return new CallActivityNode();
        case "TManualTask":
            return new ManualTaskNode();
        case "TReceiveTask":
            return new ReceiveTaskNode();
        case "TRuleTask":
            return new RuleTaskNode();
        case "TScriptTask":
            return new ScriptTaskNode();
        case "TSendTask":
            return new SendTaskNode();
        case "TServiceTask":
            return new ServiceTaskNode();
        case "TSubProcess":
            return new SubProcessNode();
        case "TTransaction":
            return new TransactionNode();
        case "TUserTask":
            return new UserTaskNode();
        case "TCancelEventDefinition":
            return new CancelEventDefinitionNode();
        case "TCompensateEventDefinition":
            return new CompensateEventDefinitionNode();
        case "TConditionalEventDefinition":
            return new ConditionalEventDefinitionNode();
        case "TErrorEventDefinition":
            return new ErrorEventDefinitionNode();
        case "TEscalationEventDefinition":
            return new EscalationEventDefinitionNode();
        case "TLinkEventDefinition":
            return new LinkEventDefinitionNode();
        case "TMessageEventDefinition":
            return new MessageEventDefinitionNode();
        case "TSignalEventDefinition":
            return new SignalEventDefinitionNode();
        case "TTerminateEventDefinition":
            return new TerminateEventDefinitionNode();
        case "TTimerEventDefinition":
            return new TimerEventDefinitionNode();
        case "TBoundaryEvent":
            return new BoundaryEventNode();
        case "TImplicitThrowEvent":
            return new ImplicitThrowEventNode();
        case "TIntermediateCatchEvent":
            return new IntermediateCatchEventNode();
        case "TIntermediateThrowEvent":
            return new IntermediateThrowEventNode();
        case "TComplexGateway":
            return new ComplexGatewayNode();
        case "TEventBasedGateway":
            return new EventBasedGatewayNode();
        case "TExclusiveGateway":
            return new ExclusiveGatewayNode();
        case "TInclusiveGateway":
            return new InclusiveGatewayNode();
        case "TParallelGateway":
            return new ParallelGatewayNode();
        case "TMultiInstanceLoop":
            return new MultiInstanceLoopNode();
        case "TStandardLoop":
            return new StandardLoopNode();
        case "TLane":
            return new LaneNode();
        case "TLaneSet":
            return new LaneSetNode();
        case "TStartEvent":
            return new StartEventNode();
        case "TEndEvent":
            return new EndEventNode();
        case "TSequenceFlow":
            return new SequenceFlowNode();
        case "TDataInputAssociation":
            return new DataAssociationNode();
        case "TDataOutputAssociation":
            return new DataAssociationNode();
        case "TDataInput":
            return new DataInputNode();
        case "TDataOutput":
            return new DataOutputNode();
        case "TMessage":
            return new MessageNode();
        case "TStandardLoopCharacteristics":
            return new StandardLoopNode();
        case "TMultiInstanceLoopCharacteristics":
            return new MultiInstanceLoopNode();
        case "TCollaboration":
            return new CollaborationNode();
        case "TDataObjectReference":
            return new DataObjectNode();
        case "TDataStoreReference":
            return new DataStoreNode();
        case "TMessageFlow":
            return new MessageFlowNode();
        case "TAssociation":
            return new AssociationNode();
        case "TTextAnnotation":
            return new AnnotationNode();
        case "TParticipant":
            return new ParticipantNode();
        
        case "BPMNDiagram":
            return new BehaviorDiagramNode();
        
        default:
            BPMNXml.LOG.debug("[Missing Case] " + jaxbElement.getClass().getSimpleName());
            return null;
        }
    }

    @objid ("5f57657d-d8ce-4d7c-9b3d-7106a40e56b1")
    public IFinaliseNode getImportFinalizationNode(Object jaxbElement) {
        switch (jaxbElement.getClass().getSimpleName()) {
        case "TProcess":
            return new ProcessFinaliser();
        case "TCollaboration":
            return new CollaborationFinaliser();
        case "BPMNDiagram":
            return new DiagramFinaliser();
        default:
            return null;
        }
    }

    @objid ("9afdc315-3cd3-4ce9-80c2-367f80b55d80")
    public IFinaliseNode getExportFinalizationNode(Object jaxbElement) {
        switch (jaxbElement.getClass().getSimpleName()) {
        case "BpmnProcessImpl":
            return new ProcessFinaliser();
        case "BpmnCollaborationImpl":
            return new CollaborationFinaliser();
        case "BpmnCollaborationDiagramImpl":
        case "BpmnProcessDesignDiagramImpl":
        case "BpmnSubProcessDiagramImpl":
            return new DiagramFinaliser();
        default:
            return null;
        }
    }

    @objid ("5dcc2904-7e79-416a-ba9f-627b1d12b3a2")
    public IProduction getExportProductionNode(Object modelioElement) {
        switch (modelioElement.getClass().getSimpleName()) {
        case "BpmnProcessImpl":
            return new ProcessNode();
        case "BpmnTaskImpl":
            return new TaskNode();
        case "BpmnAdHocSubProcessImpl":
            return new AdHocSubProcessNode();
        case "BpmnBusinessRuleTaskImpl":
            return new BusinessTaskNode();
        case "BpmnCallActivityImpl":
            return new CallActivityNode();
        case "BpmnManualTaskImpl":
            return new ManualTaskNode();
        case "BpmnReceiveTaskImpl":
            return new ReceiveTaskNode();
        case "BpmnRuleTaskImpl":
            return new RuleTaskNode();
        case "BpmnScriptTaskImpl":
            return new ScriptTaskNode();
        case "BpmnSendTaskImpl":
            return new SendTaskNode();
        case "BpmnServiceTaskImpl":
            return new ServiceTaskNode();
        case "BpmnSubProcessImpl":
            return new SubProcessNode();
        case "BpmnTransactionImpl":
            return new TransactionNode();
        case "BpmnUserTaskImpl":
            return new UserTaskNode();
        case "BpmnCancelEventDefinitionImpl":
            return new CancelEventDefinitionNode();
        case "BpmnCompensateEventDefinitionImpl":
            return new CompensateEventDefinitionNode();
        case "BpmnConditionalEventDefinitionImpl":
            return new ConditionalEventDefinitionNode();
        case "BpmnErrorEventDefinitionImpl":
            return new ErrorEventDefinitionNode();
        case "BpmnEscalationEventDefinitionImpl":
            return new EscalationEventDefinitionNode();
        case "BpmnLinkEventDefinitionImpl":
            return new LinkEventDefinitionNode();
        case "BpmnMessageEventDefinitionImpl":
            return new MessageEventDefinitionNode();
        case "BpmnSignalEventDefinitionImpl":
            return new SignalEventDefinitionNode();
        case "BpmnTerminateEventDefinitionImpl":
            return new TerminateEventDefinitionNode();
        case "BpmnTimerEventDefinitionImpl":
            return new TimerEventDefinitionNode();
        case "BpmnBoundaryEventImpl":
            return new BoundaryEventNode();
        case "BpmnImplicitThrowEventImpl":
            return new ImplicitThrowEventNode();
        case "BpmnIntermediateCatchEventImpl":
            return new IntermediateCatchEventNode();
        case "BpmnIntermediateThrowEventImpl":
            return new IntermediateThrowEventNode();
        case "BpmnComplexGatewayImpl":
            return new ComplexGatewayNode();
        case "BpmnEventBasedGatewayImpl":
            return new EventBasedGatewayNode();
        case "BpmnExclusiveGatewayImpl":
            return new ExclusiveGatewayNode();
        case "BpmnInclusiveGatewayImpl":
            return new InclusiveGatewayNode();
        case "BpmnParallelGatewayImpl":
            return new ParallelGatewayNode();
        case "BpmnMultiInstanceLoopCharacteristicsImpl":
            return new MultiInstanceLoopNode();
        case "BpmnStandardLoopCharacteristicsImpl":
            return new StandardLoopNode();
        case "BpmnLaneImpl":
            return new LaneNode();
        case "BpmnLaneSetImpl":
            return new LaneSetNode();
        case "BpmnStartEventImpl":
            return new StartEventNode();
        case "BpmnEndEventImpl":
            return new EndEventNode();
        case "BpmnSequenceFlowImpl":
            return new SequenceFlowNode();
        case "BpmnMessage":
            return new MessageNode();
        case "BpmnDataAssociationImpl":
            return new DataAssociationNode();
        case "BpmnDataInputImpl":
            BpmnDataInput din = (BpmnDataInput) modelioElement;
            if (din.getCompositionOwner() instanceof BpmnProcess) {
                return new DataObjectNode();
            }
            return new DataInputNode();
        case "BpmnDataOutputImpl":
            BpmnDataOutput dot = (BpmnDataOutput) modelioElement;
            if (dot.getCompositionOwner() instanceof BpmnProcess) {
                return new DataObjectNode();
            }
            return new DataOutputNode();
        case "BpmnCollaborationImpl":
            return new CollaborationNode();
        case "BpmnDataObjectImpl":
            return new DataObjectNode();
        case "BpmnDataStoreImpl":
            return new DataStoreNode();
        case "BpmnMessageImpl":
            return new MessageNode();
        case "BpmnMessageFlowImpl":
            return new MessageFlowNode();
        case "TAssociation":
            return new AssociationNode();
        case "TTextAnnotation":
            return new AnnotationNode();
        case "BpmnCollaborationDiagramImpl":
        case "BpmnProcessDesignDiagramImpl":
        case "BpmnSubProcessDiagramImpl":
            return new BehaviorDiagramNode();
        case "BpmnParticipantImpl":
            return new ParticipantNode();
        
        default:
            BPMNXml.LOG.debug("[Missing Case] " + modelioElement.getClass().getSimpleName());
            return null;
        }
    }

}
