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
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: OperationChecker checkStateMachineNames
 */
@objid ("b36cb3ec-b5be-4ae2-b7bc-a1f6ee4d6eb8")
public class R2430 extends AbstractUmlRule {
    @objid ("8a33382c-292e-4f80-9e13-e3dda7dbfa2d")
    private static final String RULEID = "R2430";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("c77c7272-07cc-4d73-965c-cb73bb59252f")
    private CheckR2430 checkerInstance = null;

    @objid ("c1d651bc-c51e-4d00-95e7-57843c0a5d4d")
    @Override
    public String getRuleId() {
        return R2430.RULEID;
    }

    @objid ("b041418f-8b20-439b-a46e-9ba2c3bb6a0f")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        // This cover the case of moving or deleting a StateMachine, which triggers an UPDATE on the old parent.
        plan.registerRule(Operation.MQNAME, this, AuditTrigger.UPDATE);
        
        // This cover the case of [creating|moving] a StateMachine [under|to] a new parent.
        // It also covers the case of renaming a StateMachine.
        plan.registerRule(StateMachine.MQNAME, this, AuditTrigger.CREATE |
                AuditTrigger.MOVE |
                AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("8311aa01-0d69-41b6-bd90-7ef5ef0eabf8")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("38f39894-c305-414d-a984-3703a64648fc")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("c1e8cff8-c6c2-4321-929e-1535438b1196")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2430
     */
    @objid ("f19fdfdb-928b-4f16-992b-21e2ddbc6c19")
    public R2430() {
        this.checkerInstance = new CheckR2430(this);
    }

    @objid ("e5443d50-af9b-4a26-8a28-31492f108ccc")
    private static class CheckR2430 extends AbstractControl {
        @objid ("38bacc04-29a3-4419-aee0-4883952e5d71")
        public CheckR2430(IRule rule) {
            super(rule);
        }

        @objid ("a8b260b4-0f4f-4f1e-a801-3346c60f05c1")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof Operation) {
                diagnostic.addEntry(checkR2430((Operation) element));
            } else if (element instanceof StateMachine) {
                StateMachine stateMachine = (StateMachine) element;
                MObject owner = stateMachine.getCompositionOwner();
                if (owner instanceof Operation) {
                    diagnostic.addEntry(checkR2430((Operation) owner));
                }
            } else {
                UmlUi.LOG.warning("R2430: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("67e49552-464c-441f-b670-a2daaf539286")
        private IAuditEntry checkR2430(final Operation operation) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    operation,
                    null);
            
            if (operation.getOwnedBehavior().size() > 1) {
            
                List<String> smNames = new ArrayList<>();
            
                for (StateMachine sm : operation.getOwnedBehavior(StateMachine.class)) {
                    String name = sm.getName();
            
                    if (smNames.contains(name)) {
            
                        // Rule failed
            
                        auditEntry.setSeverity(this.rule.getSeverity());
                        List<Object> linkedObjects = new ArrayList<>();
                        linkedObjects.add(operation);
                        linkedObjects.add(name);
                        auditEntry.setLinkedInfos(linkedObjects);
                    } else {
                        smNames.add(name);
                    }
                }
            }
            return auditEntry;
        }

    }

}
