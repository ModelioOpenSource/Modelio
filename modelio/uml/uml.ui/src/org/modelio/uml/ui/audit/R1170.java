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
import org.modelio.metamodel.uml.behavior.activityModel.CallOperationAction;
import org.modelio.metamodel.uml.behavior.activityModel.InputPin;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: ActivityModelChecker checkR61035 error
 */
@objid ("7f5bb729-b24b-4059-ae03-518ed101b6ee")
public class R1170 extends AbstractUmlRule {
    @objid ("09c29dfd-c803-4fc1-926a-c1e0e84cc4b6")
    private static final String RULEID = "R1170";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("e15adaf8-645a-4d53-9bab-2d880ab6b2a7")
    private CheckR1170 checkerInstance = null;

    @objid ("dfc7a824-2dad-458c-a892-c00ad245b75c")
    @Override
    public String getRuleId() {
        return R1170.RULEID;
    }

    @objid ("b52cc63e-6c67-48e4-9857-bdd51eddbdbb")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(CallOperationAction.MQNAME, this,
                AuditTrigger.UPDATE);
        plan.registerRule(InputPin.MQNAME, this, AuditTrigger.UPDATE
                | AuditTrigger.MOVE);
        plan.registerRule(Operation.MQNAME, this, AuditTrigger.MOVE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("abe8b4de-55ca-4acb-843a-cfe70bbad25c")
    @Override
    public IControl getCreationControl(MObject element) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("55361917-6d03-4009-a4b0-9b9131f3bffe")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("b2c56d34-3c56-4c01-8a06-e4df73f970be")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1170
     */
    @objid ("c95e672c-20fa-4b3a-b04d-fac148ac89cb")
    public R1170() {
        this.checkerInstance = new CheckR1170(this);
    }

    @objid ("f9c739b6-2ebe-4688-a739-f6654b31d3af")
    private static class CheckR1170 extends AbstractControl {
        @objid ("7d1fee88-661e-4a2d-89bd-3daae147923d")
        public CheckR1170(IRule rule) {
            super(rule);
        }

        @objid ("bea783a9-c137-42af-8d38-2c8049380305")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof CallOperationAction) {
                diagnostic.addEntry(checkR1170((CallOperationAction) element));
            } else if (element instanceof InputPin) {
                IAuditEntry auditEntry = checkR1170((InputPin) element);
                if (auditEntry != null) {
                    diagnostic.addEntry(auditEntry);
                }
            } else if (element instanceof Operation) {
                diagnostic.addEntries(checkR1170((Operation) element));
            } else {
                UmlUi.LOG.warning("R1170: unsupported element type '%s'",
                        element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("05e17014-9c52-4b4e-817e-b5494d7757ec")
        private IAuditEntry checkR1170(CallOperationAction callOperation) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess, callOperation, null);
            
            Operation operation = callOperation.getCalled();
            
            // The CallOperationAction does not call any Operation, the rule
            // does not apply.
            if (operation == null) {
                return auditEntry;
            }
            
            InputPin targetPin = null;
            
            for (InputPin pin : callOperation.getInput()) {
                if (pin.isIsSelf()) {
                    targetPin = pin;
                }
            }
            
            // The CallOperationAction does not have a target pin, the rule does
            // not apply.
            if (targetPin == null) {
                return auditEntry;
            }
            
            Classifier owningType = operation.getOwner();
            
            if (owningType.equals(targetPin.getType())) {
                return auditEntry;
            }
            
            // At this point the rule failed
            
            List<Object> linkedObjects = new ArrayList<>();
            auditEntry.setSeverity(this.rule.getSeverity());
            linkedObjects.add(callOperation);
            linkedObjects.add(targetPin);
            auditEntry.setLinkedInfos(linkedObjects);
            return auditEntry;
        }

        /**
         * If an InputPin is updated and is on a CallOperationAction, its self attribute potentially change so we need to check the rule on this CallOperationAction.
         * 
         * @param inputPin @return
         */
        @objid ("a37e7eaf-557e-4bdd-80e9-a356bc2f29f3")
        private IAuditEntry checkR1170(InputPin inputPin) {
            MObject pinOwner = inputPin.getCompositionOwner();
            if (pinOwner instanceof CallOperationAction) {
                return checkR1170((CallOperationAction) pinOwner);
            }
            return null;
        }

        /**
         * If an operation is moved, its owner's classifier potentially changes so we need to check the rule on every CallOperationAction that calls this operation.
         * 
         * @param operation The moved operation.
         * @return A list of audit entry for each concerned CallOperationAction.
         */
        @objid ("a9af495a-efdb-4467-a295-943b92a1892a")
        private List<IAuditEntry> checkR1170(Operation operation) {
            List<IAuditEntry> auditEntries = new ArrayList<>();
            for (CallOperationAction callOperation : operation
                    .getCallingAction()) {
                auditEntries.add(checkR1170(callOperation));
            }
            return auditEntries;
        }

    }

}
