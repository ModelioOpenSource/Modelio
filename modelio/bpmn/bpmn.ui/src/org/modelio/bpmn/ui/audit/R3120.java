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
import java.util.Optional;
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
import org.modelio.metamodel.bpmn.events.BpmnEventDefinition;
import org.modelio.metamodel.bpmn.events.BpmnIntermediateCatchEvent;
import org.modelio.metamodel.bpmn.events.BpmnIntermediateThrowEvent;
import org.modelio.metamodel.bpmn.events.BpmnLinkEventDefinition;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * R3120 Severity : warning Description : A Bpmn Link Event must be linked to another Bpmn Link Event.
 */
@objid ("f8f21177-719f-423d-a39c-55992086189d")
public class R3120 extends AbstractBpmnRule {
    @objid ("665b4309-f996-473b-abf8-d4fda3d4b182")
    private static final String RULEID = "R3120";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("4a1d941b-7828-4a3e-bc5a-2ad3cac068fe")
    private CheckR3120 checkerInstance = null;

    @objid ("7251d57a-4452-46f9-8a06-3fa24d0553e3")
    @Override
    public void autoRegister(final BpmnAuditPlan plan) {
        plan.registerRule(BpmnLinkEventDefinition.MQNAME, this, AuditTrigger.CREATE |
                AuditTrigger.MOVE |
                AuditTrigger.UPDATE);
        plan.registerRule(BpmnIntermediateThrowEvent.MQNAME, this, AuditTrigger.CREATE |
                AuditTrigger.MOVE |
                AuditTrigger.UPDATE);
        plan.registerRule(BpmnIntermediateCatchEvent.MQNAME, this, AuditTrigger.CREATE |
                AuditTrigger.MOVE |
                AuditTrigger.UPDATE);
        
    }

    @objid ("ee050416-30ec-4321-bfd3-5315d5d89ca8")
    @Override
    public String getRuleId() {
        return R3120.RULEID;
    }

    @objid ("466fbd28-0a3f-4aa3-9bc4-aa9a1dd329e1")
    @Override
    public IControl getUpdateControl(final MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status
     * or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("4b685876-f377-488c-b69e-47592852d647")
    @Override
    public IControl getMoveControl(final IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status
     * or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("b65c9c0d-d02e-4005-9a48-147ba3e4766c")
    @Override
    public IControl getCreationControl(final MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R3120
     */
    @objid ("96b05e4d-2413-420f-ab26-a74383e5c830")
    public  R3120() {
        this.checkerInstance = new CheckR3120(this);
    }

    /**
     * Actual checker for R3120: Checks that an BpmnLinkEventDefinition have a defined Target BpmnLinkEventDefinition.
     */
    @objid ("43fcafa6-9e5d-42d4-83f1-966ab75e6b9b")
    private static class CheckR3120 extends AbstractControl {
        /**
         * C'tor.
         * @param rule the rule to check.
         */
        @objid ("1fc469e9-402a-4a7d-9179-d23030fa53f1")
        public  CheckR3120(final IRule rule) {
            super(rule);
        }

        @objid ("7e9d5b98-9c24-4e4d-b74b-e69188d49dd8")
        @Override
        public IDiagnosticCollector doRun(final IDiagnosticCollector diagnostic, final MObject element) {
            if (element instanceof BpmnLinkEventDefinition) {
                diagnostic.addEntry(checkR3120((BpmnLinkEventDefinition) element));
            } else if (element instanceof BpmnIntermediateThrowEvent) {
            
                Optional<BpmnEventDefinition> def = ((BpmnIntermediateThrowEvent) element).getEventDefinitions().stream().filter(t -> t instanceof BpmnLinkEventDefinition).findFirst();
                if (def.isPresent()) {
                    diagnostic.addEntry(checkR3120((BpmnLinkEventDefinition) def.get()));
                }
            } else if (element instanceof BpmnIntermediateCatchEvent) {
                Optional<BpmnEventDefinition> def = ((BpmnIntermediateCatchEvent) element).getEventDefinitions().stream().filter(t -> t instanceof BpmnLinkEventDefinition).findFirst();
                if (def.isPresent()) {
                    diagnostic.addEntry(checkR3120((BpmnLinkEventDefinition) def.get()));
                }
            } else {
                BpmnUi.LOG.warning("R3120: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("64dcbf08-5bc0-44c0-b6f5-f7aef57f135e")
        private IAuditEntry checkR3120(final BpmnLinkEventDefinition linkEvent) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    linkEvent,
                    null);
            
            if ((linkEvent.getCompositionOwner() instanceof  BpmnIntermediateCatchEvent  && linkEvent.getTarget() == null) ||
                (linkEvent.getCompositionOwner() instanceof  BpmnIntermediateThrowEvent && linkEvent.getSource().isEmpty())) {
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(linkEvent.getCompositionOwner());
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

    }

}
