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
package org.modelio.bpmn.ui.audit;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.audit.engine.core.AbstractControl;
import org.modelio.audit.engine.core.AbstractRule;
import org.modelio.audit.engine.core.AuditEntry;
import org.modelio.audit.engine.core.IAuditEntry;
import org.modelio.audit.engine.core.IAuditExecutionPlan.AuditTrigger;
import org.modelio.audit.engine.core.IControl;
import org.modelio.audit.engine.core.IDiagnosticCollector;
import org.modelio.audit.engine.core.IRule;
import org.modelio.audit.service.AuditSeverity;
import org.modelio.bpmn.ui.plugin.BpmnUi;
import org.modelio.metamodel.bpmn.activities.BpmnActivity;
import org.modelio.metamodel.bpmn.activities.BpmnAdHocSubProcess;
import org.modelio.metamodel.bpmn.activities.BpmnBusinessRuleTask;
import org.modelio.metamodel.bpmn.activities.BpmnCallActivity;
import org.modelio.metamodel.bpmn.activities.BpmnManualTask;
import org.modelio.metamodel.bpmn.activities.BpmnReceiveTask;
import org.modelio.metamodel.bpmn.activities.BpmnScriptTask;
import org.modelio.metamodel.bpmn.activities.BpmnSendTask;
import org.modelio.metamodel.bpmn.activities.BpmnServiceTask;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.activities.BpmnTask;
import org.modelio.metamodel.bpmn.activities.BpmnTransaction;
import org.modelio.metamodel.bpmn.activities.BpmnUserTask;
import org.modelio.metamodel.bpmn.events.BpmnBoundaryEvent;
import org.modelio.metamodel.bpmn.events.BpmnEndEvent;
import org.modelio.metamodel.bpmn.events.BpmnEvent;
import org.modelio.metamodel.bpmn.events.BpmnImplicitThrowEvent;
import org.modelio.metamodel.bpmn.events.BpmnIntermediateCatchEvent;
import org.modelio.metamodel.bpmn.events.BpmnIntermediateThrowEvent;
import org.modelio.metamodel.bpmn.events.BpmnLinkEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnSignalEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnStartEvent;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.gateways.BpmnComplexGateway;
import org.modelio.metamodel.bpmn.gateways.BpmnEventBasedGateway;
import org.modelio.metamodel.bpmn.gateways.BpmnExclusiveGateway;
import org.modelio.metamodel.bpmn.gateways.BpmnInclusiveGateway;
import org.modelio.metamodel.bpmn.gateways.BpmnParallelGateway;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowNode;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * R3080 Severity: error Description : All FlowNodes should be part of a sequence starting with a StartEvent and finishing with an EndEvent.
 */
@objid ("8222a9d2-338e-40d1-bf53-c69974357b5e")
public class R3080 extends AbstractBpmnRule {
    @objid ("9d9e8c58-10df-4c1b-a46c-20cbbff574f4")
    private static final String RULEID = "R3080";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("91b9afc0-51a6-4177-933a-c4f2a4ed97a9")
    private CheckR3080 checkerInstance = null;

    @objid ("f2c483c9-155f-481c-b97c-aa1ac5100b88")
    @Override
    public void autoRegister(final BpmnAuditPlan plan) {
        plan.registerRule(BpmnSequenceFlow.MQNAME, this, AuditTrigger.CREATE |
                AuditTrigger.MOVE |
                AuditTrigger.UPDATE);
        
        // BpmnFlowNode.Activity.Activity
        plan.registerRule(BpmnCallActivity.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
        
        // BpmnFlowNode.Activity.Task
        plan.registerRule(BpmnTask.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
        plan.registerRule(BpmnSendTask.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
        plan.registerRule(BpmnReceiveTask.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
        plan.registerRule(BpmnServiceTask.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
        plan.registerRule(BpmnUserTask.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
        plan.registerRule(BpmnManualTask.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
        plan.registerRule(BpmnScriptTask.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
        plan.registerRule(BpmnBusinessRuleTask.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
        
        // BpmnFlowNode.Activity.SubProcess
        plan.registerRule(BpmnSubProcess.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
        plan.registerRule(BpmnAdHocSubProcess.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
        plan.registerRule(BpmnTransaction.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
        
        // BpmnFlowNode.Event.CatchEvent
        // Except BoundaryEvent, which are not concerned by the rule.
        // Except ImplicitThrowEvent which is not implemented by Modelio.
        plan.registerRule(BpmnStartEvent.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
        plan.registerRule(BpmnIntermediateCatchEvent.MQNAME, this, AuditTrigger.CREATE |
                AuditTrigger.UPDATE);
        
        // BpmnFlowNode.Event.ThrowEvent
        plan.registerRule(BpmnEndEvent.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
        plan.registerRule(BpmnIntermediateThrowEvent.MQNAME, this, AuditTrigger.CREATE |
                AuditTrigger.UPDATE);
        
        // BpmnFlowNode.Gateway
        plan.registerRule(BpmnParallelGateway.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
        plan.registerRule(BpmnEventBasedGateway.MQNAME, this, AuditTrigger.CREATE |
                AuditTrigger.UPDATE);
        plan.registerRule(BpmnComplexGateway.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
        plan.registerRule(BpmnInclusiveGateway.MQNAME, this, AuditTrigger.CREATE |
                AuditTrigger.UPDATE);
        plan.registerRule(BpmnExclusiveGateway.MQNAME, this, AuditTrigger.CREATE |
                AuditTrigger.UPDATE);
        
    }

    @objid ("bea4424a-cde0-407f-a544-726108fab43e")
    @Override
    public String getRuleId() {
        return R3080.RULEID;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status
     * or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("91c295a6-0f6b-4eb4-8874-961c43703c8b")
    @Override
    public IControl getUpdateControl(final MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status
     * or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("204ef00f-29fc-4e17-b0ca-62b25e3360c5")
    @Override
    public IControl getMoveControl(final IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status
     * or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("52ab9e94-366b-4a9a-966f-686d4fbbd8fc")
    @Override
    public IControl getCreationControl(final MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R3080
     */
    @objid ("e604d614-2480-4dd0-98bb-6889117eb535")
    public  R3080() {
        this.checkerInstance = new CheckR3080(this);
    }

    /**
     * Actual checker for R3080: Checks that an ActivityParameterNode doesn't have both incoming and outgoing edges at the same time.
     */
    @objid ("da6a6ed0-7e34-4027-9e20-c440a9ddadca")
    private static class CheckR3080 extends AbstractControl {
        /**
         * C'tor.
         * @param rule the rule to check.
         */
        @objid ("7fed0c0b-a930-41bd-9cfb-e176948b54cd")
        public  CheckR3080(final IRule rule) {
            super(rule);
        }

        @objid ("725acd26-1efe-41ea-960f-05e9639f6720")
        @Override
        public IDiagnosticCollector doRun(final IDiagnosticCollector diagnostic, final MObject element) {
            if (element instanceof BpmnFlowNode) {
                diagnostic.addEntry(checkR3080((BpmnFlowNode) element));
            } else if (element instanceof BpmnSequenceFlow) {
                BpmnFlowNode sourceRef = ((BpmnSequenceFlow) element).getSourceRef();
                if (sourceRef != null) {
                    diagnostic.addEntry(checkR3080(sourceRef));
                }
            
                BpmnFlowNode targetRef = ((BpmnSequenceFlow) element).getTargetRef();
                if (targetRef != null) {
                    diagnostic.addEntry(checkR3080(targetRef));
                }
            } else {
                BpmnUi.LOG.warning("R3080: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("6ba75569-1ae9-4d1b-9060-48ede6f650fd")
        private IAuditEntry checkR3080(final BpmnFlowNode flowNode) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    flowNode,
                    null);
            
            if (flowNode instanceof BpmnBoundaryEvent || flowNode instanceof BpmnImplicitThrowEvent || flowNode.isTriggeredByEvent()) {
                return auditEntry;
            }
            
            int incoming = flowNode.getIncoming().size();
            int outgoing = flowNode.getOutgoing().size();
            
            if ((flowNode instanceof BpmnActivity && !((BpmnActivity) flowNode).getBoundaryEventRef().isEmpty()) ||
                    (flowNode instanceof BpmnStartEvent && outgoing > 0) ||
                    (flowNode instanceof BpmnEndEvent && incoming > 0) ||
                    (incoming > 0 && outgoing > 0)) {
                return auditEntry;
            }
            
            // Filter Events of type BpmnLinkEventDefinition
            if (flowNode instanceof BpmnEvent && ((BpmnEvent) flowNode).getEventDefinitions().size() > 0 && ((BpmnEvent) flowNode).getEventDefinitions().stream().filter(s -> s instanceof BpmnLinkEventDefinition).count() > 0) {
                return auditEntry;
            }
            
            // Filter SubProcesses starting with a Signal
            if (flowNode instanceof BpmnSubProcess) {
                for (BpmnStartEvent startEvent : ((BpmnSubProcess) flowNode).getFlowElement(BpmnStartEvent.class)) {
                    if (!startEvent.getEventDefinitions(BpmnSignalEventDefinition.class).isEmpty()) {
                        return auditEntry;
                    }
                }
            }
            
            // At this point the rule failed
            
            auditEntry.setSeverity(this.rule.getSeverity());
            List<Object> linkedObjects = new ArrayList<>();
            linkedObjects.add(flowNode);
            auditEntry.setLinkedInfos(linkedObjects);
            return auditEntry;
        }

    }

}
