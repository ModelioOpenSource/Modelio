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
import org.modelio.metamodel.uml.behavior.activityModel.CallOperationAction;
import org.modelio.metamodel.uml.behavior.activityModel.InputPin;
import org.modelio.metamodel.uml.behavior.activityModel.SendSignalAction;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This rule is not inherited from previous C++ audit.
 */
@objid ("9ed43563-e0d6-4a4e-a131-d689a8e95da2")
public class R1160 extends AbstractUmlRule {
    @objid ("f96ce15b-34a7-4fc5-9c26-5aec72759889")
    private static final String RULEID = "R1160";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("c028bdf3-c545-45be-a1ad-a8fe1ced4889")
    private CheckR1160 checkerInstance = null;

    @objid ("f110d7ef-cf76-40b0-9b23-2c0f5b2ebf4a")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(InputPin.MQNAME, this, AuditTrigger.UPDATE
                | AuditTrigger.MOVE);
    }

    @objid ("13cde649-c3ce-4175-ae51-8a4a4ff7ad77")
    @Override
    public String getRuleId() {
        return R1160.RULEID;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("b4b4aa76-8226-4c50-80de-0e9d3e7f2753")
    @Override
    public IControl getCreationControl(MObject element) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("82ef6fe7-7221-4bf5-897d-89a7d6ec3d2f")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("4a2fd085-8145-4405-b676-c05992225c13")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1160
     */
    @objid ("2e16c829-d47a-4b35-a056-cb8597cb3108")
    public R1160() {
        this.checkerInstance = new CheckR1160(this);
    }

    @objid ("d2fc367a-6682-4d8b-a167-3dbc5b3ac1a3")
    private static class CheckR1160 extends AbstractControl {
        @objid ("425a4339-8376-43fc-bf45-5c9201a9fd97")
        public CheckR1160(IRule rule) {
            super(rule);
        }

        @objid ("b91f3ac2-799f-4c0c-8c88-59b49c417b80")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof InputPin) {
                diagnostic.addEntry(checkR1160((InputPin) element));
            } else {
                UmlUi.LOG.warning("R1160: unsupported element type '%s'",
                        element.getMClass().getName());
            }
            return diagnostic;
        }

        /**
         * If an InputPin is updated its self attribute potentially change so we need to check if it belongs to an CallOperationAction or a SendSignalAction.
         * 
         * @param inputPin The updated pin.
         * @return An audit entry for the pin.
         */
        @objid ("117c1c22-c3bb-406c-bb66-799b1bba8cca")
        private IAuditEntry checkR1160(InputPin inputPin) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess, inputPin, null);
            
            MObject owner = inputPin.getCompositionOwner();
            
            if (!inputPin.isIsSelf()) {
                return auditEntry;
            }
            
            if (owner instanceof CallOperationAction
                    || owner instanceof SendSignalAction) {
                return auditEntry;
            }
            
            // At this point the rule failed.
            
            List<Object> linkedObjects = new ArrayList<>();
            auditEntry.setSeverity(this.rule.getSeverity());
            linkedObjects.add(inputPin);
            auditEntry.setLinkedInfos(linkedObjects);
            return auditEntry;
        }

    }

}
