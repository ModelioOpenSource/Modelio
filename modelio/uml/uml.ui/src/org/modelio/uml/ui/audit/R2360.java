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
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: OperationChecker checkR136
 */
@objid ("32cd4089-4dfd-4591-80dc-8a04158f298d")
public class R2360 extends AbstractUmlRule {
    @objid ("bf76b598-0c14-40f0-a2a3-3e87136765b6")
    private static final String RULEID = "R2360";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("7bb29a18-d5a0-4e07-962f-aa3414f14f7c")
    private CheckR2360 checkerInstance = null;

    @objid ("e4c481ed-ee1d-4d73-a00f-7d1bdd294792")
    @Override
    public String getRuleId() {
        return R2360.RULEID;
    }

    @objid ("614f3a4b-3593-4b79-82a9-949784a9bc8d")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(Operation.MQNAME, this, AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("0c776d1b-e5c2-465b-ae23-58ed8b534043")
    @Override
    public IControl getCreationControl(MObject element) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("a85ff7b3-6a36-4d0e-b846-888b470040b6")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("439dce1d-5f98-4421-97d4-f54b07a2a25c")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2360
     */
    @objid ("5ffb3bf8-c3cc-41f5-9258-9001efa7d064")
    public R2360() {
        this.checkerInstance = new CheckR2360(this);
    }

    @objid ("573fbf60-a375-48a1-9d0a-f1b679aa5660")
    private static class CheckR2360 extends AbstractControl {
        @objid ("e66f3bd8-e2b0-455d-ab58-7c178a9e9e65")
        public CheckR2360(IRule rule) {
            super(rule);
        }

        @objid ("25fdbedb-c490-41aa-aa63-0e5abeed526f")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof Operation) {
                // An Operation was modified
                // We need to check it, but also any operation that redefines it
                Operation operation = (Operation) element;
                diagnostic.addEntry(checkR2360(operation));
                for (Operation redefinition : operation.getRedefinition()) {
                    diagnostic.addEntry(checkR2360(redefinition));
                }
            } else {
                UmlUi.LOG.warning("R2360: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("10577016-054a-405b-97e4-a1db783b2179")
        private IAuditEntry checkR2360(final Operation operation) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    operation,
                    null);
            
            Operation redefined = operation.getRedefines();
            
            if (redefined != null) {
            
                int operationVisibility = getVisibilityNumber(operation.getVisibility());
                int redefinedVisibility = getVisibilityNumber(redefined.getVisibility());
            
                if (operationVisibility != 0 &&
                        redefinedVisibility != 0 &&
                        redefinedVisibility < operationVisibility) {
            
                    // Rule failed: cannot reduce visibility by redefinition
            
                    auditEntry.setSeverity(this.rule.getSeverity());
                    List<Object> linkedObjects = new ArrayList<>();
                    linkedObjects.add(operation);
                    auditEntry.setLinkedInfos(linkedObjects);
                }
            }
            return auditEntry;
        }

        @objid ("224770ca-5242-4372-91f4-33b2e4fab775")
        private int getVisibilityNumber(final VisibilityMode val) {
            switch (val) {
            case PUBLIC:
                return 4;
            case PACKAGEVISIBILITY:
                return 3;
            case PROTECTED:
                return 2;
            case PRIVATE:
                return 1;
            default:
                return 0;
            }
        }

    }

}
