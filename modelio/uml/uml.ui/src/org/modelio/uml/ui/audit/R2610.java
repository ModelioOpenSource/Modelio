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
import org.modelio.metamodel.uml.behavior.stateMachineModel.ConnectionPointReference;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: StateChecker checkR63014 error
 */
@objid ("85b25465-36e2-4a7f-a3e8-8025c0f5fb2f")
public class R2610 extends AbstractUmlRule {
    @objid ("9a7fba39-bf9a-4932-b7d8-8cbbec5eb12f")
    private static final String RULEID = "R2610";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("ee99f455-b9e7-4348-b4ae-0a9c8d97fc9a")
    private CheckR2610 checkerInstance = null;

    @objid ("06ac5bec-dcfd-43eb-9ce8-8c3252721975")
    @Override
    public String getRuleId() {
        return R2610.RULEID;
    }

    @objid ("3669c421-4308-4826-88b3-39329d890690")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(State.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(ConnectionPointReference.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.MOVE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("1f6bbf09-40b6-4433-820e-f23af2927fb4")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("b49d11dd-f095-4087-91b0-8bafea2d48c1")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("880bea5f-a807-4070-9b4d-f898dead6521")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2610
     */
    @objid ("4770043a-8e81-440d-872e-17a438d9fdc6")
    public R2610() {
        this.checkerInstance = new CheckR2610(this);
    }

    @objid ("e3272e80-f3e6-4e03-87b6-34273030555f")
    private static class CheckR2610 extends AbstractControl {
        @objid ("b2a010f8-70ce-465e-bed2-7039d98da789")
        public CheckR2610(IRule rule) {
            super(rule);
        }

        @objid ("f9ff06db-28c6-479e-8a1f-fcb67ee67bee")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof State) {
                diagnostic.addEntry(checkR2610((State) element));
            } else if (element instanceof ConnectionPointReference) {
                MObject owner = element.getCompositionOwner();
                if (owner != null && owner instanceof State) {
                    diagnostic.addEntry(checkR2610((State) owner));
                }
            } else {
                UmlUi.LOG.warning("R2610: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("123016c4-85fe-4321-99ec-b3fba491ed21")
        private IAuditEntry checkR2610(State state) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(), AuditSeverity.AuditSuccess, state, null);
            
            if (state.getSubMachine() != null) {
                return auditEntry;
            }
            
            List<ConnectionPointReference> cprs = state.getConnection();
            
            if (!cprs.isEmpty()) {
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(state);
                linkedObjects.addAll(cprs);
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

    }

}
