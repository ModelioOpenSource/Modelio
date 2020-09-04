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
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: PortChecker checkIsBehaviorType
 */
@objid ("eb4f27a0-32da-4bdb-ad89-9de28b56016b")
public class R2570 extends AbstractUmlRule {
    @objid ("0f4e5c8c-8cfb-44e9-9ea5-716fe7a20d44")
    private static final String RULEID = "R2570";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("11e0b27f-e3bc-40ab-984d-735e2ec73deb")
    private CheckR2570 checkerInstance = null;

    @objid ("37effe00-89a4-460f-99f7-9235cfbfb2b8")
    @Override
    public String getRuleId() {
        return R2570.RULEID;
    }

    @objid ("4033d89d-87ed-4fc6-b459-d6d38c45dd5f")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(Port.MQNAME, this, AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("9abe721a-4a95-46bd-bc8c-7fdb3c00c96e")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("40f2f8d1-4094-407c-9ed2-1ddc7bdb15df")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("d33a49ae-5dbf-4390-bf71-aeb4792099a6")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2570
     */
    @objid ("026096b6-e1ec-414f-9f17-7cbc9882153c")
    public R2570() {
        this.checkerInstance = new CheckR2570(this);
    }

    @objid ("48436a58-863e-49a6-9591-05bcbdd181f4")
    private static class CheckR2570 extends AbstractControl {
        @objid ("f02e1778-1c50-45b1-9b0b-6fd003014a05")
        public CheckR2570(IRule rule) {
            super(rule);
        }

        @objid ("e6f76e4a-068d-4564-b443-b518fdcc268f")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof Port) {
                diagnostic.addEntry(checkR2570((Port) element));
            }
            return diagnostic;
        }

        @objid ("8bb885fb-7721-40e5-8a5e-2c3ac3fe3b50")
        private IAuditEntry checkR2570(final Port port) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(), AuditSeverity.AuditSuccess, port, null);
            
            if (port.isIsBehavior()) {
                if (!(port.getBase() == null || port.getBase().equals(port.getInternalOwner()))) {
            
                    // Rule Failed
            
                    auditEntry.setSeverity(this.rule.getSeverity());
                    List<Object> linkedObjects = new ArrayList<>();
                    linkedObjects.add(port);
                    linkedObjects.add(port.getBase());
                    auditEntry.setLinkedInfos(linkedObjects);
                }
            }
            return auditEntry;
        }

    }

}
