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
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: ClassChecker checkPrimitiveCollaboration
 */
@objid ("5403ed99-4b78-4b9f-a6e5-bc42dbfd4ab0")
public class R1600 extends AbstractUmlRule {
    @objid ("f05e0dfc-704a-4eb1-8205-15d535d8525c")
    private static final String RULEID = "R1600";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("2b5ff7f1-c1f9-4cfa-9f96-683e55ef7790")
    private CheckR1600 checkerInstance = null;

    @objid ("faa1c7bd-b3d9-4614-9cce-0ab70c85f259")
    @Override
    public String getRuleId() {
        return R1600.RULEID;
    }

    @objid ("d93236ef-8506-44df-a92d-9eaac3445bb2")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(Class.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
        plan.registerRule(Collaboration.MQNAME, this, AuditTrigger.CREATE |
                AuditTrigger.MOVE |
                AuditTrigger.UPDATE);
        
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("eefd7eea-6341-41be-8d73-a3be32c1241b")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("545e2db5-a2e4-4ae9-8441-a0fbd9966445")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("da45fb28-4d00-4466-9e82-7508aa7cae89")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1600
     */
    @objid ("0ac3f771-5f6f-4c21-9f07-a452da48866d")
    public  R1600() {
        this.checkerInstance = new CheckR1600(this);
    }

    @objid ("49d1edbf-1894-4d3e-9202-4b17c52ba0c5")
    private static class CheckR1600 extends AbstractControl {
        @objid ("03d561ef-f6c8-43bd-a171-7713ca7bcfba")
        public  CheckR1600(IRule rule) {
            super(rule);
        }

        @objid ("b9be444f-555a-40cd-ab2f-2c2a7e5ba560")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof Class) {
                diagnostic.addEntry(checkR1600((Class) element));
            } else if (element instanceof Collaboration) {
                Collaboration collaboration = (Collaboration) element;
                MObject owner = collaboration.getCompositionOwner();
                if (owner instanceof Class) {
                    diagnostic.addEntry(checkR1600((Class) owner));
                }
            } else {
                UmlUi.LOG.warning("R1600: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("0b6dccee-c696-43de-bf22-6fad77729fd7")
        private IAuditEntry checkR1600(final Class clazz) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    clazz,
                    null);
            if (clazz.isIsElementary()) {
                for (ModelTree elt : clazz.getOwnedElement()) {
                    if (elt instanceof Collaboration) {
            
                        // Rule failed
            
                        auditEntry.setSeverity(this.rule.getSeverity());
                        List<Object> linkedObjects = new ArrayList<>();
                        linkedObjects.add(clazz);
                        auditEntry.setLinkedInfos(linkedObjects);
                    }
                }
            }
            return auditEntry;
        }

    }

}
