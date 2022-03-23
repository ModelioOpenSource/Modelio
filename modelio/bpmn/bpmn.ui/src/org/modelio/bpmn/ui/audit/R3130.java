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
import org.modelio.metamodel.bpmn.events.BpmnEndEvent;
import org.modelio.metamodel.bpmn.events.BpmnIntermediateCatchEvent;
import org.modelio.metamodel.bpmn.events.BpmnIntermediateThrowEvent;
import org.modelio.metamodel.bpmn.events.BpmnStartEvent;
import org.modelio.metamodel.bpmn.flows.BpmnMessageFlow;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * R3130 Severity : error Description : A MessageFlow cannot target a StartEvent or an IntermediateCatchEvent, or have an EndEvent or an IntermediateCatchEvent as its source.
 */
@objid ("61e0c7b8-cb1e-4b1a-bf5f-2a1cc5729654")
public class R3130 extends AbstractBpmnRule {
    @objid ("cf8b961f-f457-4479-b745-3fadef45fea9")
    private static final String RULEID = "R3130";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("91aa6ded-eaa3-4bd0-9f24-08fde07d084c")
    private CheckR3130 checkerInstance = null;

    @objid ("72f64cf5-941a-456b-aa80-316866cb249d")
    @Override
    public void autoRegister(final BpmnAuditPlan plan) {
        plan.registerRule(BpmnMessageFlow.MQNAME, this, AuditTrigger.CREATE |
                AuditTrigger.MOVE |
                AuditTrigger.UPDATE);
        
    }

    @objid ("aae76e59-d89b-4fe6-82fd-6632bfdb283b")
    @Override
    public String getRuleId() {
        return R3130.RULEID;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("00f02f5a-66eb-430e-9d70-965954416436")
    @Override
    public IControl getUpdateControl(final MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("dcb34961-9801-49b1-80a0-8a7365c7b40e")
    @Override
    public IControl getMoveControl(final IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("e728bf91-f742-4371-b031-781dba09317e")
    @Override
    public IControl getCreationControl(final MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R3130
     */
    @objid ("edf004ee-929a-4e4e-84ef-1a38826100d4")
    public  R3130() {
        this.checkerInstance = new CheckR3130(this);
    }

    /**
     * Actual checker for R3130: Checks that an ActivityParameterNode doesn't have both incoming and outgoing edges at the same time.
     */
    @objid ("28cf3010-73df-42d0-a087-980cff50719d")
    private static class CheckR3130 extends AbstractControl {
        /**
         * C'tor.
         * @param rule the rule to check.
         */
        @objid ("b3ac7bbb-3d4e-4efa-b77f-da051addf451")
        public  CheckR3130(final IRule rule) {
            super(rule);
        }

        @objid ("cf34bb8f-6bb7-42bc-9057-7de51d4a5776")
        @Override
        public IDiagnosticCollector doRun(final IDiagnosticCollector diagnostic, final MObject element) {
            if (element instanceof BpmnMessageFlow) {
                diagnostic.addEntry(checkR1050((BpmnMessageFlow) element));
            } else {
                BpmnUi.LOG.warning("R3130: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("16d9a5ea-7e95-4a67-a47e-4f527d0feef9")
        private IAuditEntry checkR1050(final BpmnMessageFlow messageFlow) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    messageFlow,
                    null);
            
            BpmnBaseElement source = messageFlow.getSourceRef();
            BpmnBaseElement target = messageFlow.getTargetRef();
            
            if (target instanceof BpmnEndEvent ||
                    source instanceof BpmnIntermediateCatchEvent ||
                    source instanceof BpmnStartEvent ||
                    target instanceof BpmnIntermediateThrowEvent) {
            
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(messageFlow);
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

    }

}
