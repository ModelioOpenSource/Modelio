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
import org.modelio.metamodel.uml.behavior.stateMachineModel.Region;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: StateChecker checkNameUnicity
 */
@objid ("12d747d6-ae10-4633-96dc-bbff57e20fd0")
public class R2600 extends AbstractUmlRule {
    @objid ("a68107c1-a730-4bbb-a6c3-327fd0b03f11")
    private static final String RULEID = "R2600";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("6164928c-1d78-4af9-8bfb-3e063cc4cdd1")
    private CheckR2600 checkerInstance = null;

    @objid ("49eec2dc-421c-4155-b410-98696cf5dcc2")
    @Override
    public String getRuleId() {
        return R2600.RULEID;
    }

    @objid ("a97e9ee3-27a1-4f7f-b9f3-3a9520b8e5e0")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(StateMachine.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Region.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(State.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE | AuditTrigger.MOVE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("3d6eb1d6-0086-449a-a2c0-71bd3938106b")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("ce6e8d52-6957-4075-aa15-f03732b0e0c7")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("36816c5b-9b64-4c55-b7df-194a71467b75")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2600
     */
    @objid ("870681d9-9274-410e-a739-f3c5d2a50eb3")
    public R2600() {
        this.checkerInstance = new CheckR2600(this);
    }

    @objid ("9d5531dd-af96-493e-bf8c-48e0bacada48")
    private static class CheckR2600 extends AbstractControl {
        @objid ("56670eb1-36b9-45f2-a015-f22d36c0830d")
        public CheckR2600(IRule rule) {
            super(rule);
        }

        @objid ("89433493-0d3e-4625-8a32-cddf592d4a1e")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof StateMachine) {
                // A state machine received an update because a state was either created, moved or deleted
                diagnostic.addEntry(checkR2600((StateMachine) element));
            } else if (element instanceof State) {
                // The state was updated, and potentially its name was.
                // Checking the enclosing state, either a state machine or a state
                Region region = ((State) element).getParent();
                if (region.getParent() != null) {
                    diagnostic.addEntry(checkR2600(region.getParent()));
                } else {
                    MObject owner = region.getCompositionOwner();
                    if (owner instanceof StateMachine) {
                        diagnostic.addEntry(checkR2600((StateMachine) owner));
                    }
                }
                // Or the state received an update because a state was either created, moved or deleted
                diagnostic.addEntry(checkR2600((State) element));
            } else if (element instanceof Region) {
                // A region received an update because a state was either created, moved or deleted
                Region region = (Region) element;
                if (region.getParent() != null) {
                    diagnostic.addEntry(checkR2600(region.getParent()));
                } else {
                    MObject owner = region.getCompositionOwner();
                    if (owner instanceof StateMachine) {
                        diagnostic.addEntry(checkR2600((StateMachine) owner));
                    }
                }
            } else {
                UmlUi.LOG.warning("R2600: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("381363a0-beac-4af7-af03-92120862af2c")
        private IAuditEntry checkR2600(State state) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(), AuditSeverity.AuditSuccess, state, null);
            
            List<String> foundStateNames = new ArrayList<>();
            List<State> duplicateNamedStates = new ArrayList<>();
            
            for (Region foundRegion : state.getOwnedRegion()) {
                for (State foundState : foundRegion.getSub(State.class)) {
                    String foundStateName = foundState.getName();
                    if (foundStateNames.contains(foundStateName)) {
                        duplicateNamedStates.add(foundState);
                    } else {
                        foundStateNames.add(foundStateName);
                    }
                }
            }
            
            if (!duplicateNamedStates.isEmpty()) {
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(state);
                linkedObjects.add(duplicateNamedStates.get(0).getName());
                linkedObjects.addAll(duplicateNamedStates);
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

        @objid ("7e66efda-ba63-4f30-90a3-ae56e3ec5365")
        private IAuditEntry checkR2600(StateMachine stateMachine) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(), AuditSeverity.AuditSuccess, stateMachine, null);
            
            List<String> foundStateNames = new ArrayList<>();
            List<State> duplicateNamedStates = new ArrayList<>();
            
            for (State foundState : stateMachine.getTop().getSub(State.class)) {
                String foundStateName = foundState.getName();
                if (foundStateNames.contains(foundStateName)) {
                    duplicateNamedStates.add(foundState);
                } else {
                    foundStateNames.add(foundStateName);
                }
            }
            
            if (!duplicateNamedStates.isEmpty()) {
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(stateMachine);
                linkedObjects.add(duplicateNamedStates.get(0).getName());
                linkedObjects.addAll(duplicateNamedStates);
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

    }

}
