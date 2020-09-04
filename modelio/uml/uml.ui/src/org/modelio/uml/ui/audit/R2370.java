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
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: OperationChecker checkR137
 */
@objid ("b3d91d36-2e36-4ce4-9e30-7c1a9bf8f90e")
public class R2370 extends AbstractUmlRule {
    @objid ("dcf4a0a6-4737-4a64-817d-83684ffc6a23")
    private static final String RULEID = "R2370";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("fe64b15b-34af-4447-b381-bf8d214ece7e")
    private CheckR2370 checkerInstance = null;

    @objid ("df5352ad-08b6-4710-a411-39a2b909e4bc")
    @Override
    public String getRuleId() {
        return R2370.RULEID;
    }

    @objid ("959eb520-933a-4b1b-9832-299139015277")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(Operation.MQNAME, this, AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("5c8e56a8-4f8d-4682-baf9-037f9426adf2")
    @Override
    public IControl getCreationControl(MObject element) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("0a571b6e-e69b-4825-b831-96e566bec20d")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("74e8f65b-eaf8-434d-a5be-0e504153258a")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2370
     */
    @objid ("dfabb793-8e6c-466b-b22d-0cc5cfc99adb")
    public R2370() {
        this.checkerInstance = new CheckR2370(this);
    }

    @objid ("34b9792a-9437-41dc-9052-0f49ce6a5b09")
    private static class CheckR2370 extends AbstractControl {
        @objid ("aa96d69f-bc90-4f49-8f37-0985dc4a4540")
        public CheckR2370(IRule rule) {
            super(rule);
        }

        @objid ("7ed5c580-47a3-4879-b329-9264889e0ff6")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof Operation) {
                diagnostic.addEntry(checkR2370((Operation) element));
            } else {
                UmlUi.LOG.warning("R2370: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("dc219446-ba0c-43ef-af79-ed3c75fe4fec")
        private IAuditEntry checkR2370(final Operation operation) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    operation,
                    null);
            
            if (operation.isIsClass() && !operation.getRedefinition().isEmpty()) {
                // Rule failed
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(operation);
                auditEntry.setLinkedInfos(linkedObjects);
            
            }
            return auditEntry;
        }

    }

}
