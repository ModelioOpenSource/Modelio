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
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: OperationChecker checkR287 error
 */
@objid ("bcfb7e91-3688-45fe-ad4f-aae826ee671e")
public class R2410 extends AbstractUmlRule {
    @objid ("8c3d833f-b547-4aad-8913-e7b0a85bb610")
    private static final String RULEID = "R2410";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("f8764e80-289c-4c5c-bda8-cb66bc7185c2")
    private CheckR2410 checkerInstance = null;

    @objid ("1d973ad0-50ce-4249-a56d-cdbd38e1463f")
    @Override
    public String getRuleId() {
        return R2410.RULEID;
    }

    @objid ("d8b6d498-c1e2-4ab5-802d-68c118b91b20")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(Operation.MQNAME, this, AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("8daaaa5f-f66b-477f-9870-8878e7cb8050")
    @Override
    public IControl getCreationControl(MObject element) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("066d1cd5-741d-4bcf-9b9c-069900c6ac62")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("e67996c3-3cdb-485a-88ed-50d0ce7556db")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2410
     */
    @objid ("768e58e0-c65d-4023-a721-950539809397")
    public  R2410() {
        this.checkerInstance = new CheckR2410(this);
    }

    @objid ("f115e534-9e4b-4d35-b5a6-034a2706b04c")
    private static class CheckR2410 extends AbstractControl {
        @objid ("2dd6bbfe-68d8-4c9e-8ab6-390292b32686")
        public  CheckR2410(IRule rule) {
            super(rule);
        }

        @objid ("4ff11247-a857-47ec-af70-06b921717994")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof Operation) {
                diagnostic.addEntry(checkR2410((Operation) element));
            }
            return diagnostic;
        }

        @objid ("abaf32ff-1840-47da-98c5-6a3eb5a9fd52")
        private IAuditEntry checkR2410(final Operation operation) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(), AuditSeverity.AuditSuccess, operation, null);
            
            boolean failed = false;
            
            if (operation.isStereotyped("ModelerModule", "create") && operation.isStereotyped("ModelerModule", "destroy")) {
                // Rule failed
                failed = true;
            }
            if (failed) {
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(operation);
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

    }

}
