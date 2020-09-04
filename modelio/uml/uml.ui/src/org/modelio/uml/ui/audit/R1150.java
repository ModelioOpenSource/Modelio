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
import org.modelio.metamodel.uml.behavior.activityModel.ActivityAction;
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
@objid ("8e74af2f-4868-4657-bfea-ed3732e924f5")
public class R1150 extends AbstractUmlRule {
    @objid ("214e729a-11fc-44ff-bbb6-4e522d6dcf33")
    private static final String RULEID = "R1150";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("8a9699c3-29e9-40a9-97ed-4324aaa34839")
    private CheckR1150 checkerInstance = null;

    @objid ("b0f19a08-3ab4-4d93-984e-03ef2bf24cef")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(CallOperationAction.MQNAME, this,
                AuditTrigger.UPDATE);
        plan.registerRule(SendSignalAction.MQNAME, this,
                AuditTrigger.UPDATE);
        plan.registerRule(InputPin.MQNAME, this, AuditTrigger.UPDATE
                | AuditTrigger.MOVE);
    }

    @objid ("9f8e55e2-4b87-4adf-b81e-54aa8eb0fb02")
    @Override
    public String getRuleId() {
        return R1150.RULEID;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("1decee58-586c-458c-8842-12e021617225")
    @Override
    public IControl getCreationControl(MObject element) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("7ea12882-5ac3-4a29-824b-9813f3cbd040")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("4e710a36-aaa4-495a-9f2f-25eeb3955dd0")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1150
     */
    @objid ("c6b11aa2-b9c9-4fea-a42c-17a44cd70c5a")
    public R1150() {
        this.checkerInstance = new CheckR1150(this);
    }

    @objid ("67aa6ea7-cdc6-474c-8dad-21657f1c735e")
    private static class CheckR1150 extends AbstractControl {
        @objid ("e70ababe-2545-4d2b-85f9-123e2a6de0da")
        public CheckR1150(IRule rule) {
            super(rule);
        }

        @objid ("fe242e53-b9fb-4550-8ba4-2473430cab07")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof CallOperationAction
                    || element instanceof SendSignalAction) {
                diagnostic.addEntry(checkR1150((ActivityAction) element));
            } else if (element instanceof InputPin) {
                IAuditEntry auditEntry = checkR1150((InputPin) element);
                if (auditEntry != null) {
                    diagnostic.addEntry(auditEntry);
                }
            } else {
                UmlUi.LOG.warning("R1150: unsupported element type '%s'",
                        element.getMClass().getName());
            }
            return diagnostic;
        }

        /**
         * If an InputPin is updated and is on a CallOperationAction, its self attribute potentially change so we need to check the rule on this CallOperationAction.
         * 
         * @param inputPin The updated pin.
         * @return An audit entry if the pin belongs to a CallOperationAction or a SendSignalAction, null otherwise
         */
        @objid ("bce940cf-3242-4877-ad50-6fb9172ff06e")
        private IAuditEntry checkR1150(InputPin inputPin) {
            MObject pinOwner = inputPin.getCompositionOwner();
            if (pinOwner instanceof CallOperationAction
                    || pinOwner instanceof SendSignalAction) {
                return checkR1150((ActivityAction) pinOwner);
            }
            return null;
        }

        @objid ("a79af7c7-d444-4736-a441-6262f13ad986")
        private IAuditEntry checkR1150(ActivityAction action) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess, action, null);
            
            List<InputPin> targetPins = new ArrayList<>();
            
            for (InputPin pin : action.getInput()) {
                if (pin.isIsSelf()) {
                    targetPins.add(pin);
                }
            }
            
            if (targetPins.size() > 1) {
            
                // Rule failed
            
                List<Object> linkedObjects = new ArrayList<>();
                auditEntry.setSeverity(this.rule.getSeverity());
                linkedObjects.add(action);
                for (InputPin pin : targetPins) {
                    linkedObjects.add(pin);
                }
                auditEntry.setLinkedInfos(linkedObjects);
            
            }
            return auditEntry;
        }

    }

}
