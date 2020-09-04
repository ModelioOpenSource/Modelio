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

package org.modelio.uml.ui.audit;

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
import org.modelio.metamodel.uml.behavior.activityModel.InputPin;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: ActivityModelChecker checkR61062 error
 */
@objid ("11be94a0-442e-40c4-bc9d-8bd48311a391")
public class R1390 extends AbstractUmlRule {
    @objid ("0adcf7a5-d8cb-4bfb-889c-bc733930e8c0")
    private static final String RULEID = "R1390";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("7031c896-3cdd-4e4c-af24-b85f373a92f4")
    private CheckR1390 checkerInstance = null;

    @objid ("b299c3f0-0d65-4d64-aa21-395eab7c3da8")
    @Override
    public String getRuleId() {
        return R1390.RULEID;
    }

    @objid ("7af21256-f770-4060-8080-32e8a4db3b7d")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(Attribute.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(InputPin.MQNAME, this, AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("578dd0b4-6dd5-46a6-817b-43201d1c4fbd")
    @Override
    public IControl getCreationControl(MObject element) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("c68dfe68-d4ba-4359-8e35-0135c0db74b8")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("2c73a2c7-cea2-4c85-aeba-cf834dd42672")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1390
     */
    @objid ("eef9020c-d759-4f6a-bfe4-011339b9f5fc")
    public R1390() {
        this.checkerInstance = new CheckR1390(this);
    }

    @objid ("daea0ae0-3c49-4223-94e5-e12ea997d087")
    private static class CheckR1390 extends AbstractControl {
        @objid ("ba3c5701-fc33-4c2f-8ff5-6d60d51cc6cc")
        public CheckR1390(IRule rule) {
            super(rule);
        }

        @objid ("1c97d515-50df-4096-b5a8-3ec25dfe4b32")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof InputPin) {
                diagnostic.addEntry(checkR1390((InputPin) element));
            } else if (element instanceof Attribute) {
                diagnostic.addEntries(checkR1390((Attribute) element));
            } else {
                UmlUi.LOG.warning("R1390: unsupported element type '%s'",
                        element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("dc7ea5ef-cf73-4eed-90a3-2d77a747a68b")
        private IAuditEntry checkR1390(InputPin pin) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess, pin, null);
            
            Attribute attribute = null;
            if ((attribute = pin.getRepresentedAttribute()) == null) {
                return auditEntry;
            }
            
            if (attribute.getMultiplicityMax().equals(pin.getUpperBound())) {
                return auditEntry;
            }
            
            // Rule failed
            
            auditEntry.setSeverity(this.rule.getSeverity());
            List<Object> linkedObjects = new ArrayList<>();
            linkedObjects.add(pin);
            linkedObjects.add(attribute);
            auditEntry.setLinkedInfos(linkedObjects);
            return auditEntry;
        }

        /**
         * If an Attribute is updated and this Attribute is represented in an InputPin, we need to check the rule on this InputPin.
         * 
         * @param attribute The updated Attribute.
         * @return A list of audit entry for each InputPin representing the Attribute.
         */
        @objid ("d9cbf380-23c4-4a82-8a56-325fcaaaa938")
        private List<IAuditEntry> checkR1390(Attribute attribute) {
            List<IAuditEntry> auditEntries = new ArrayList<>();
            for (InputPin pin : attribute
                    .getRepresentingObjectNode(InputPin.class)) {
                auditEntries.add(checkR1390(pin));
            }
            return auditEntries;
        }

    }

}
