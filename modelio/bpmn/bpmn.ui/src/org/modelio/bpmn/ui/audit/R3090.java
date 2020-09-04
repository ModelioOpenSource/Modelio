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
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowNode;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * R3090 Severity : error Description : A SequenceFlow cannot have its source or target in different Lane, except for sub-lanes.
 */
@objid ("c87911fc-0e7c-4a07-a9cd-b0c92edec6b6")
public class R3090 extends AbstractBpmnRule {
    @objid ("74a70377-f929-4caf-b116-60be172826dd")
    private static final String RULEID = "R3090";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("6cf60b22-f04e-4ee9-a0de-de37381a10b4")
    private final CheckR3090 checkerInstance;

    @objid ("33924045-e93f-45db-93c4-57a421f546b8")
    @Override
    public void autoRegister(final BpmnAuditPlan plan) {
        plan.registerRule(BpmnSequenceFlow.MQNAME, this, AuditTrigger.CREATE |
                AuditTrigger.MOVE |
                AuditTrigger.UPDATE);
        plan.registerRule(BpmnFlowNode.MQNAME, this, AuditTrigger.MOVE);
    }

    @objid ("f1dc89e2-a4da-4107-91e6-0730b713899a")
    @Override
    public String getRuleId() {
        return R3090.RULEID;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status
     * or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("c0342709-6b09-492b-92e2-abb8d33054a4")
    @Override
    public IControl getUpdateControl(final MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status
     * or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("9173bfca-6145-44e8-b54e-79d32a6a55d1")
    @Override
    public IControl getMoveControl(final IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status
     * or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("0817ebd0-a158-4913-9d75-45cfdd13a1b0")
    @Override
    public IControl getCreationControl(final MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R3090
     */
    @objid ("6dcd8f38-a8bd-4800-931d-eb89f227bee8")
    public R3090() {
        this.checkerInstance = new CheckR3090(this);
    }

    /**
     * Actual checker for R3090: Checks that an ActivityParameterNode doesn't have both incoming and outgoing edges at the same time.
     */
    @objid ("749eab2e-5a27-4866-b0ae-2e31a062ee51")
    private static class CheckR3090 extends AbstractControl {
        /**
         * C'tor.
         * @param rule the rule to check.
         */
        @objid ("62772d4b-735b-44f3-a23c-b3bd4934c89c")
        public CheckR3090(final IRule rule) {
            super(rule);
        }

        @objid ("0ce80121-ebf2-4fe1-85d7-ece2c05bf78b")
        @Override
        public IDiagnosticCollector doRun(final IDiagnosticCollector diagnostic, final MObject element) {
            if (element instanceof BpmnSequenceFlow) {
                diagnostic.addEntry(checkR3090((BpmnSequenceFlow) element));
            } else if (element instanceof BpmnFlowNode) {
                BpmnFlowNode bpmnFlowNode = (BpmnFlowNode) element;
                for (BpmnSequenceFlow incomingFlow : bpmnFlowNode.getIncoming()) {
                    diagnostic.addEntry(checkR3090(incomingFlow));
                }
                for (BpmnSequenceFlow outgoingFlow : bpmnFlowNode.getOutgoing()) {
                    diagnostic.addEntry(checkR3090(outgoingFlow));
                }
            } else {
                BpmnUi.LOG.warning("R3090: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("18f1dc48-b74e-4225-a85a-0314fd1a94fe")
        private IAuditEntry checkR3090(final BpmnSequenceFlow flow) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    flow,
                    null);
            
            BpmnFlowNode sourceRef = flow.getSourceRef();
            BpmnFlowNode targetRef = flow.getTargetRef();
            if (sourceRef == null || targetRef == null) {
                return auditEntry;
            }
            
            BpmnProcess sourceProcess = getProcess(sourceRef);
            BpmnProcess targetProcess = getProcess(targetRef);
            
            if (sourceProcess != null && targetProcess != null && !sourceProcess.equals(targetProcess)) {
                // Rule failed
                auditEntry.setSeverity(this.rule.getSeverity());
                auditEntry.setLinkedInfos(Arrays.asList(flow, sourceRef, targetRef, sourceProcess, targetProcess));
            }
            return auditEntry;
        }

    }

}
