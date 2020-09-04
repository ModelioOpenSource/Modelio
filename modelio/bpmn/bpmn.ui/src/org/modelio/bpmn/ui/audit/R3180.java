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
import org.modelio.metamodel.bpmn.flows.BpmnMessageFlow;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowNode;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * R3180 Severity : error Description : A FlowElement cannot have a SequenceFlow or a MessageFlow towards itself.
 */
@objid ("049871db-ceac-42a3-a8af-7619f7fd27e6")
public class R3180 extends AbstractBpmnRule {
    @objid ("8ba0aa6c-b831-4cbc-b353-13f1a0bd753a")
    private static final String RULEID = "R3180";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("73562a36-a497-4326-b0b3-be1e7cdc04d5")
    private CheckR3180 checkerInstance = null;

    @objid ("42d553b7-d76a-4223-9800-9ca0b10c88c2")
    @Override
    public void autoRegister(final BpmnAuditPlan plan) {
        plan.registerRule(BpmnSequenceFlow.MNAME, this, AuditTrigger.CREATE | AuditTrigger.MOVE | AuditTrigger.UPDATE);
        plan.registerRule(BpmnMessageFlow.MNAME, this, AuditTrigger.CREATE | AuditTrigger.MOVE | AuditTrigger.UPDATE);
    }

    @objid ("48400f3d-b572-4a00-aeda-39bfd0494ec7")
    @Override
    public String getRuleId() {
        return R3180.RULEID;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("717a5582-4a9a-48a9-bfbf-3cf337dee61b")
    @Override
    public IControl getUpdateControl(final MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("80817c1a-cfcb-4c5b-8b31-0b8ef9e9affb")
    @Override
    public IControl getMoveControl(final IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("befcb19e-db21-4457-b25e-f4566c63e87d")
    @Override
    public IControl getCreationControl(final MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R3180
     */
    @objid ("ff7bc7ec-b2c7-4503-93ea-0db6f28e1215")
    public R3180() {
        this.checkerInstance = new CheckR3180(this);
    }

    /**
     * Actual checker for R3180: Checks that an ActivityParameterNode doesn't have both incoming and outgoing edges at the same time.
     */
    @objid ("0c54fc63-77ff-4700-8b7d-3d7c39a6c07d")
    public static class CheckR3180 extends AbstractControl {
        /**
         * C'tor.
         * 
         * @param rule the rule to check.
         */
        @objid ("75657d18-384b-489f-9234-13aec786c35e")
        public CheckR3180(final IRule rule) {
            super(rule);
        }

        @objid ("f9e0c0f7-cdb2-48f7-bc32-038794c2a4da")
        @Override
        public IDiagnosticCollector doRun(final IDiagnosticCollector diagnostic, final MObject element) {
            if (element instanceof BpmnSequenceFlow) {
                diagnostic.addEntry(checkR3180((BpmnSequenceFlow) element));
            } else if (element instanceof BpmnMessageFlow) {
                diagnostic.addEntry(checkR3180((BpmnMessageFlow) element));
            } else {
                BpmnUi.LOG.warning("R3180: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("cf1a005f-3543-4b85-b963-0591773e5d0f")
        private IAuditEntry checkR3180(final BpmnSequenceFlow seqFlow) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    seqFlow,
                    null);
            
            final BpmnFlowNode sourceRef = seqFlow.getSourceRef();
            if (sourceRef != null && sourceRef.equals(seqFlow.getTargetRef())) {
                // Rule failed
                auditEntry.setSeverity(this.rule.getSeverity());
                ArrayList<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(sourceRef.getMClass().getName());
                linkedObjects.add(sourceRef);
                linkedObjects.add(seqFlow.getMClass().getName());
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

        @objid ("da46be36-6310-475e-944c-1b4982c6e3f6")
        private IAuditEntry checkR3180(final BpmnMessageFlow msgFlow) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    msgFlow,
                    null);
            
            final BpmnBaseElement sourceRef = msgFlow.getSourceRef();
            if (sourceRef != null && sourceRef.equals(msgFlow.getTargetRef())) {
                // Rule failed
                auditEntry.setSeverity(this.rule.getSeverity());
                ArrayList<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(sourceRef.getMClass().getName());
                linkedObjects.add(sourceRef);
                linkedObjects.add(msgFlow.getMClass().getName());
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

    }

}
