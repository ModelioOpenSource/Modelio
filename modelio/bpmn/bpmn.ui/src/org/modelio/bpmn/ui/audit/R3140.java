/* 
 * Copyright 2013-2019 Modeliosoft
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
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.gateways.BpmnEventBasedGateway;
import org.modelio.metamodel.bpmn.gateways.BpmnParallelGateway;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowNode;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * R3140 Severity : warning Description : All outgoing SequenceFlow from an EventBasedGateway or a ParallelGateway must have its default and guard properties empty.
 */
@objid ("893bec1f-a6b6-45e2-adcc-fab04073b32b")
public class R3140 extends AbstractBpmnRule {
    @objid ("12ccbd95-3633-49ea-a6b6-b3a74293f545")
    private static final String RULEID = "R3140";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("77efb041-346f-4f75-8739-30053242fedd")
    private CheckR3140 checkerInstance = null;

    @objid ("6f5514d4-d1f2-4c2a-854f-2e3d6ab6df0d")
    @Override
    public void autoRegister(final BpmnAuditPlan plan) {
        plan.registerRule(BpmnSequenceFlow.MQNAME, this, AuditTrigger.CREATE |
                AuditTrigger.MOVE |
                AuditTrigger.UPDATE);
        plan.registerRule(BpmnFlowElement.MQNAME, this, AuditTrigger.UPDATE);
    }

    @objid ("e51c1e65-abdb-4903-932a-a8f130df46e9")
    @Override
    public String getRuleId() {
        return R3140.RULEID;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("366ef981-3859-4f11-a869-5552f9f9eecc")
    @Override
    public IControl getUpdateControl(final MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("ffd2e5b4-7161-431e-b6e5-1900256112fb")
    @Override
    public IControl getMoveControl(final IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("2a8dacb3-b303-439f-9b5a-ce78a48682ea")
    @Override
    public IControl getCreationControl(final MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R3140
     */
    @objid ("56c46cc4-558b-42f8-be2d-2c5d5bf85cf4")
    public R3140() {
        this.checkerInstance = new CheckR3140(this);
    }

    /**
     * Actual checker for R3140: Checks that an ActivityParameterNode doesn't have both incoming and outgoing edges at the same time.
     */
    @objid ("8694f98a-0d28-4f64-9d59-e4b04e8d3723")
    private static class CheckR3140 extends AbstractControl {
        /**
         * C'tor.
         * 
         * @param rule the rule to check.
         */
        @objid ("2b19ef6c-96b9-4bc4-aa47-dd473f0a8294")
        public CheckR3140(final IRule rule) {
            super(rule);
        }

        @objid ("4a26701f-22e7-4608-945f-532f0e7136b5")
        @Override
        public IDiagnosticCollector doRun(final IDiagnosticCollector diagnostic, final MObject element) {
            if (element instanceof BpmnSequenceFlow) {
                diagnostic.addEntry(checkR1050((BpmnSequenceFlow) element));
            } else {
                BpmnUi.LOG.warning("R3140: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("76cd08e2-86c6-49cd-8cb6-276f920fac0e")
        private IAuditEntry checkR1050(final BpmnSequenceFlow seqFlow) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    seqFlow,
                    null);
            
            BpmnFlowNode source = seqFlow.getSourceRef();
            
            if (source instanceof BpmnEventBasedGateway || source instanceof BpmnParallelGateway) {
                if (!seqFlow.getConditionExpression().isEmpty()) {
            
                    // Rule failed
            
                    auditEntry.setSeverity(this.rule.getSeverity());
                    List<Object> linkedObjects = new ArrayList<>();
                    linkedObjects.add(seqFlow);
                    auditEntry.setLinkedInfos(linkedObjects);
            
                }
            }
            return auditEntry;
        }

    }

}
