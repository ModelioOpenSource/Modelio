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
import org.modelio.metamodel.bpmn.flows.BpmnMessageFlow;
import org.modelio.metamodel.bpmn.gateways.BpmnGateway;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * R3160 Severity : error Description : A MessageFlow cannot have a Gateway as its source or target.
 */
@objid ("4c5edc85-c3cb-4462-ba45-8c9b827f5905")
public class R3160 extends AbstractBpmnRule {
    @objid ("6ed8a260-7273-4098-adda-bd0603d3b753")
    private static final String RULEID = "R3160";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("f8f803a5-0100-4836-9fed-23d6b1a28c1a")
    private CheckR3160 checkerInstance = null;

    @objid ("2737a067-4ee5-4142-bbc5-54ab75200c95")
    @Override
    public void autoRegister(final BpmnAuditPlan plan) {
        plan.registerRule(BpmnMessageFlow.MQNAME, this, AuditTrigger.CREATE |
                AuditTrigger.MOVE |
                AuditTrigger.UPDATE);
    }

    @objid ("47111467-10e6-4a0d-986e-d8d616e03b3e")
    @Override
    public String getRuleId() {
        return R3160.RULEID;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("cc56144b-9d68-42d3-80b6-54f23c46675f")
    @Override
    public IControl getUpdateControl(final MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("b917e36d-c80b-435d-9919-403abff6b7a6")
    @Override
    public IControl getMoveControl(final IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("6033086c-47da-41c8-9bf3-2d83ee51a9bd")
    @Override
    public IControl getCreationControl(final MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R3160
     */
    @objid ("064b0385-03f6-4f44-9cf0-ffcc18a656bc")
    public R3160() {
        this.checkerInstance = new CheckR3160(this);
    }

    /**
     * Actual checker for R3160: Checks that an ActivityParameterNode doesn't have both incoming and outgoing edges at the same time.
     */
    @objid ("9c65fe00-4982-4476-b687-622a4b5b0e7c")
    private static class CheckR3160 extends AbstractControl {
        /**
         * C'tor.
         * @param rule the rule to check.
         */
        @objid ("74fe6899-5adf-4aae-97d3-18e5970d00c1")
        public CheckR3160(final IRule rule) {
            super(rule);
        }

        @objid ("5da0e174-6347-4ac4-8577-673a673254af")
        @Override
        public IDiagnosticCollector doRun(final IDiagnosticCollector diagnostic, final MObject element) {
            if (element instanceof BpmnMessageFlow) {
                diagnostic.addEntry(checkR1050((BpmnMessageFlow) element));
            } else {
                BpmnUi.LOG.warning("R3160: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("3d6ca0a3-31e6-4372-a552-3b3fdcfc235b")
        private IAuditEntry checkR1050(final BpmnMessageFlow messageFlow) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    messageFlow,
                    null);
            
            if (messageFlow.getSourceRef() instanceof BpmnGateway ||
                    messageFlow.getTargetRef() instanceof BpmnGateway) {
            
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(messageFlow);
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

    }

}
