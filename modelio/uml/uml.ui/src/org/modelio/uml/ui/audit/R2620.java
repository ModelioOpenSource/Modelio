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
import org.modelio.metamodel.uml.behavior.stateMachineModel.EntryPointPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.ExitPointPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: StateChecker checkR63015 error
 */
@objid ("2ab1a041-5d84-44bf-bd9e-15dd2257990d")
public class R2620 extends AbstractUmlRule {
    @objid ("a154acac-4700-4a1e-8f6a-bb17bc2d323d")
    private static final String RULEID = "R2620";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("1eee0855-73f1-412a-9271-2f083879f1d5")
    private CheckR2620 checkerInstance = null;

    @objid ("0ae57adb-d913-4e18-af52-da5f098b16ac")
    @Override
    public String getRuleId() {
        return R2620.RULEID;
    }

    @objid ("96a06bf8-1cee-4f8d-991c-2ed3b2e45b99")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(State.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(EntryPointPseudoState.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.MOVE);
        plan.registerRule(ExitPointPseudoState.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.MOVE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("8fb304f5-e70b-4ace-86b9-6ba1a590da5d")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("79b8f4be-ebd2-40fd-9e4a-2d09c857ead6")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("52ec4a9c-dd6d-45aa-8efc-0de0568c47b8")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2620
     */
    @objid ("0fb4b96b-b681-4a01-b6d1-60e7841c0682")
    public R2620() {
        this.checkerInstance = new CheckR2620(this);
    }

    @objid ("2fd7ff98-a8dd-4c8b-8bcd-6fe0017eaead")
    private static class CheckR2620 extends AbstractControl {
        @objid ("1600cb84-430d-45a6-af25-18f4cf30bfa7")
        public CheckR2620(IRule rule) {
            super(rule);
        }

        @objid ("8733d3d9-8b42-4fe5-a827-dbea5e7d796d")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof State) {
                diagnostic.addEntry(checkR2620((State) element));
            } else if (element instanceof EntryPointPseudoState || element instanceof ExitPointPseudoState) {
                MObject owner = element.getCompositionOwner();
                if (owner != null && owner instanceof State) {
                    diagnostic.addEntry(checkR2620((State) owner));
                }
            } else {
                UmlUi.LOG.warning("R2620: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("32b5b4e4-5443-44ad-883d-aec9f4727377")
        private IAuditEntry checkR2620(State state) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(), AuditSeverity.AuditSuccess, state, null);
            
            if (state.getSubMachine() == null) {
                return auditEntry;
            }
            
            List<EntryPointPseudoState> entries = state.getEntryPoint();
            List<ExitPointPseudoState> exits = state.getExitPoint();
            
            if (!entries.isEmpty() || !exits.isEmpty()) {
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(state);
                linkedObjects.addAll(entries);
                linkedObjects.addAll(exits);
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

    }

}
