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

import java.util.Arrays;
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
import org.modelio.metamodel.bpmn.events.BpmnEndEvent;
import org.modelio.metamodel.bpmn.events.BpmnIntermediateCatchEvent;
import org.modelio.metamodel.bpmn.events.BpmnIntermediateThrowEvent;
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
 * R3100 Severity : error Description : A SequcneFlow in a SubProcess must have its origin and target in the same SubProcess.
 */
@objid ("fe02a401-2abd-4746-9acc-8b2bde2cadbf")
public class R3100 extends AbstractBpmnRule {
    @objid ("7d5cf09c-6057-4e78-ae41-d3e735e19adc")
    private static final String RULEID = "R3100";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("17746a91-b62a-47dc-92f8-861f23b409ae")
    private final CheckR3100 checkerInstance;

    @objid ("5c72ae3c-225d-49fe-9a13-147765b1ba8f")
    @Override
    public void autoRegister(final BpmnAuditPlan plan) {
        plan.registerRule(BpmnSequenceFlow.MQNAME, this, AuditTrigger.CREATE |
                AuditTrigger.MOVE |
                AuditTrigger.UPDATE);
        
        // BpmnFlowNode.Activity.Activity
        plan.registerRule(BpmnCallActivity.MQNAME, this, AuditTrigger.MOVE);
        
        // BpmnFlowNode.Activity.Task
        plan.registerRule(BpmnTask.MQNAME, this, AuditTrigger.MOVE);
        plan.registerRule(BpmnSendTask.MQNAME, this, AuditTrigger.MOVE);
        plan.registerRule(BpmnReceiveTask.MQNAME, this, AuditTrigger.MOVE);
        plan.registerRule(BpmnServiceTask.MQNAME, this, AuditTrigger.MOVE);
        plan.registerRule(BpmnUserTask.MQNAME, this, AuditTrigger.MOVE);
        plan.registerRule(BpmnManualTask.MQNAME, this, AuditTrigger.MOVE);
        plan.registerRule(BpmnScriptTask.MQNAME, this, AuditTrigger.MOVE);
        plan.registerRule(BpmnBusinessRuleTask.MQNAME, this, AuditTrigger.MOVE);
        
        // BpmnFlowNode.Activity.SubProcess
        plan.registerRule(BpmnSubProcess.MQNAME, this, AuditTrigger.MOVE);
        plan.registerRule(BpmnAdHocSubProcess.MQNAME, this, AuditTrigger.MOVE);
        plan.registerRule(BpmnTransaction.MQNAME, this, AuditTrigger.MOVE);
        
        // BpmnFlowNode.Event.CatchEvent
        // Except BoundaryEvent, which are not concerned by the rule.
        // Except ImplicitThrowEvent which is not implemented by Modelio.
        plan.registerRule(BpmnStartEvent.MQNAME, this, AuditTrigger.MOVE);
        plan.registerRule(BpmnIntermediateCatchEvent.MQNAME, this, AuditTrigger.MOVE);
        
        // BpmnFlowNode.Event.ThrowEvent
        plan.registerRule(BpmnEndEvent.MQNAME, this, AuditTrigger.MOVE);
        plan.registerRule(BpmnIntermediateThrowEvent.MQNAME, this, AuditTrigger.MOVE);
        
        // BpmnFlowNode.Gateway
        plan.registerRule(BpmnParallelGateway.MQNAME, this, AuditTrigger.MOVE);
        plan.registerRule(BpmnEventBasedGateway.MQNAME, this, AuditTrigger.MOVE);
        plan.registerRule(BpmnComplexGateway.MQNAME, this, AuditTrigger.MOVE);
        plan.registerRule(BpmnInclusiveGateway.MQNAME, this, AuditTrigger.MOVE);
        plan.registerRule(BpmnExclusiveGateway.MQNAME, this, AuditTrigger.MOVE);
    }

    @objid ("05d73611-e31d-4be4-a182-7f5d1c65e478")
    @Override
    public String getRuleId() {
        return R3100.RULEID;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("3eb07e15-8cda-4ddd-b098-0e671ea4f206")
    @Override
    public IControl getUpdateControl(final MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("14d921c1-df71-438a-861d-bb4e62c502ed")
    @Override
    public IControl getMoveControl(final IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("cf5ded0b-0be9-486d-a037-5dbabc5dda66")
    @Override
    public IControl getCreationControl(final MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R3100
     */
    @objid ("f4fbdb20-ae19-48db-b239-540c067276b8")
    public R3100() {
        this.checkerInstance = new CheckR3100(this);
    }

    /**
     * Actual checker for R3100: Checks that an ActivityParameterNode doesn't have both incoming and outgoing edges at the same time.
     */
    @objid ("28b90542-1983-44f8-b9cf-486874180db9")
    private static class CheckR3100 extends AbstractControl {
        /**
         * C'tor.
         * 
         * @param rule the rule to check.
         */
        @objid ("79336ce6-040a-42c8-b01a-194f7865142a")
        public CheckR3100(final IRule rule) {
            super(rule);
        }

        @objid ("c245fa4d-2e20-4318-9f9e-5f86aa3cbf2b")
        @Override
        public IDiagnosticCollector doRun(final IDiagnosticCollector diagnostic, final MObject element) {
            if (element instanceof BpmnSequenceFlow) {
                diagnostic.addEntry(checkR3100((BpmnSequenceFlow) element));
            } else if (element instanceof BpmnFlowNode) {
                for (BpmnSequenceFlow incomingFlow : ((BpmnFlowNode) element).getIncoming()) {
                    diagnostic.addEntry(checkR3100(incomingFlow));
                }
                for (BpmnSequenceFlow outgoingFlow : ((BpmnFlowNode) element).getOutgoing()) {
                    diagnostic.addEntry(checkR3100(outgoingFlow));
                }
            } else {
                BpmnUi.LOG.warning("R3100: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("3f2d374f-413b-4362-bbe2-14e3a4593ddb")
        private IAuditEntry checkR3100(final BpmnSequenceFlow seqFlow) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    seqFlow,
                    null);
            
            BpmnFlowNode sourceRef = seqFlow.getSourceRef();
            BpmnFlowNode targetRef = seqFlow.getTargetRef();
            if (sourceRef == null || targetRef == null) {
                return auditEntry;
            }
            
            BpmnSubProcess sourceSubPorcess = sourceRef.getSubProcess();
            BpmnSubProcess targetSubPorcess = targetRef.getSubProcess();
            
            if (sourceSubPorcess != null &&
                    targetSubPorcess != null &&
                    !sourceSubPorcess.equals(targetSubPorcess)) {
            
                // Rule failed
            
                auditEntry.setSeverity(this.rule.getSeverity());
                auditEntry.setLinkedInfos(Arrays.asList(seqFlow, sourceRef, targetRef, sourceSubPorcess, targetSubPorcess));
            }
            return auditEntry;
        }

    }

}
