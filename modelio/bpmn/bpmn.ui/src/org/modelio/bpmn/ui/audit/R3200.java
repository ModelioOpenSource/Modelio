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
 * R3120 Severity : tip Description : A Bpmn Link Event should have the same name as the targeted Bpmn Link Event.
 */
@objid ("2772c29f-b5f0-46fa-9825-30bb297915f6")
public class R3200 extends AbstractBpmnRule {
    @objid ("71110d6f-98d3-4a94-b944-64c542ff7d23")
    private static final String RULEID = "R3200";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("542bf78e-63ae-42c2-ab70-7b75edccdc65")
    private CheckR3200 checkerInstance = null;

    @objid ("48f96cd6-e924-4044-b292-1113e27eae67")
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

    @objid ("f9259918-369b-4635-970f-ac33de3f8ced")
    @Override
    public String getRuleId() {
        return R3200.RULEID;
    }

    @objid ("5c4d81fe-4049-46a8-a83d-6052eedc088e")
    @Override
    public IControl getUpdateControl(final MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status
     * or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("a89b24ea-5d4b-4ef2-83fc-b0dc6f150e5f")
    @Override
    public IControl getMoveControl(final IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status
     * or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("62dc1cb6-6249-42e1-a104-3087066f3d2f")
    @Override
    public IControl getCreationControl(final MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R3200
     */
    @objid ("d0be3038-d00a-47ae-a7eb-724f8b43f691")
    public R3200() {
        this.checkerInstance = new CheckR3200(this);
    }

    /**
     * Actual checker for R3200: Checks that the BpmnLinkEventDefinition owner event have the same name than the name of owner event of the the targeted BpmnLinkEventDefinition .
     */
    @objid ("1c02abd6-7e8a-4c0f-9a6c-e741b7ff409c")
    private static class CheckR3200 extends AbstractControl {
        /**
         * C'tor.
         * 
         * @param rule the rule to check.
         */
        @objid ("aa1f152a-ba97-4f9e-affb-23b1c044fb5a")
        public CheckR3200(final IRule rule) {
            super(rule);
        }

        @objid ("b9fef1b6-9d96-4894-afce-685388344871")
        @Override
        public IDiagnosticCollector doRun(final IDiagnosticCollector diagnostic, final MObject element) {
            if (element instanceof BpmnLinkEventDefinition) {
                diagnostic.addEntry(checkR3120((BpmnLinkEventDefinition) element));
            } else if (element instanceof BpmnIntermediateThrowEvent) {
                Optional<BpmnEventDefinition> def = ((BpmnIntermediateThrowEvent) element).getEventDefinitions().stream().filter(t -> t instanceof BpmnLinkEventDefinition).findFirst();
                if (def.isPresent() && !((BpmnLinkEventDefinition) def.get()).getSource().isEmpty()) {
            
                    for (BpmnLinkEventDefinition sdef : ((BpmnLinkEventDefinition) def.get()).getSource()) {
                        diagnostic.addEntry(checkR3120(sdef));
                    }
                }
            } else if (element instanceof BpmnIntermediateCatchEvent) {
                Optional<BpmnEventDefinition> def = ((BpmnIntermediateCatchEvent) element).getEventDefinitions().stream().filter(t -> t instanceof BpmnLinkEventDefinition).findFirst();
                if (def.isPresent()) {
                    diagnostic.addEntry(checkR3120((BpmnLinkEventDefinition) def.get()));
                }
            
            } else {
                BpmnUi.LOG.warning("R3200: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("1c4450c3-0651-4bd3-8558-2cc8db2086b9")
        private IAuditEntry checkR3120(final BpmnLinkEventDefinition linkEvent) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    linkEvent,
                    null);
            
            if (linkEvent.getTarget() != null) {
                String elem1 = linkEvent.getCompositionOwner().getName();
                String elem2 = linkEvent.getTarget().getCompositionOwner().getName();
            
                if (elem1 != null && !elem1.equals(elem2)) {
                    auditEntry.setSeverity(this.rule.getSeverity());
                    List<Object> linkedObjects = new ArrayList<>();
                    linkedObjects.add(linkEvent.getTarget().getCompositionOwner());
                    linkedObjects.add(elem2);
                    auditEntry.setLinkedInfos(linkedObjects);
                }
            
            }
            return auditEntry;
        }

    }

}
