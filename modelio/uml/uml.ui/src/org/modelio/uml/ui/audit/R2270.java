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
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: OperationChecker checkCollaborationNames
 */
@objid ("6ec38572-2a5b-4ff3-af54-ee9c4b3dae83")
public class R2270 extends AbstractUmlRule {
    @objid ("29d49327-cc63-4199-8387-e767500e930f")
    private static final String RULEID = "R2270";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("ed6e34fb-16b9-462c-ae1a-6715f2adbc54")
    private CheckR2270 checkerInstance = null;

    @objid ("b9ac8fea-2f34-4ee9-9d93-8f8bfb1f2716")
    @Override
    public String getRuleId() {
        return R2270.RULEID;
    }

    @objid ("9ca29cf6-a077-43e2-a0e6-643ca850839e")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        // This cover the case of moving or deleting a Collaboration, which triggers an UPDATE on the old parent.
        plan.registerRule(Operation.MQNAME, this, AuditTrigger.UPDATE);
        
        // This cover the case of [creating|moving] a Collaboration [under|to] a new parent.
        // It also covers the case of renaming a Collaboration.
        plan.registerRule(Collaboration.MQNAME, this, AuditTrigger.CREATE |
                AuditTrigger.MOVE |
                AuditTrigger.UPDATE);
        
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("66ab62de-a1f7-4cec-9e73-dc5842ec6fbf")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("364bf4cf-94c6-4585-9695-d2d3018838ac")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("8e1e9266-11ad-4357-a99f-efbb7d58df29")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2270
     */
    @objid ("5fce23ab-54c9-476a-861e-49f06c3214bc")
    public  R2270() {
        this.checkerInstance = new CheckR2270(this);
    }

    @objid ("a3bf81ac-fba7-4dcd-8b11-02990fd47aea")
    private static class CheckR2270 extends AbstractControl {
        @objid ("75456bb2-56c1-4635-b3df-88e9252da318")
        public  CheckR2270(IRule rule) {
            super(rule);
        }

        @objid ("3702aa61-7d1c-42b3-a1cf-5e45be713e77")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof Operation) {
                diagnostic.addEntry(checkR2270((Operation) element));
            } else if (element instanceof Collaboration) {
                Collaboration collaboration = (Collaboration) element;
                MObject owner = collaboration.getCompositionOwner();
                if (owner instanceof Operation) {
                    diagnostic.addEntry(checkR2270((Operation) owner));
                }
            } else {
                UmlUi.LOG.warning("R2270: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("051675d3-fed7-4953-b7f1-bfcba6561059")
        private IAuditEntry checkR2270(final Operation operation) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    operation,
                    null);
            
            if (operation.getExample().size() > 1) {
            
                List<String> collaboNames = new ArrayList<>();
            
                for (Collaboration collabo : operation.getExample()) {
                    String name = collabo.getName();
            
                    if (collaboNames.contains(name)) {
            
                        // Rule failed
            
                        auditEntry.setSeverity(this.rule.getSeverity());
                        List<Object> linkedObjects = new ArrayList<>();
                        linkedObjects.add(operation);
                        linkedObjects.add(name);
                        auditEntry.setLinkedInfos(linkedObjects);
                    } else {
                        collaboNames.add(name);
                    }
                }
            }
            return auditEntry;
        }

    }

}
