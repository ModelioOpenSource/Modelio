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
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.Feature;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: ClassChecker checkIsAbstractIsClass error
 */
@objid ("4036fc16-bb09-432c-9d9b-0245e8abfc7f")
public class R1580 extends AbstractUmlRule {
    @objid ("3ef62765-b79d-4510-812d-5dcd3f0d7e69")
    private static final String RULEID = "R1580";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(IElement)
     * @see AbstractRule#getUpdateControl(IElement)
     * @see AbstractRule#getMoveControl(IElementMovedEvent)
     */
    @objid ("57dfd56b-fc02-42f1-95ae-83f7c5d1c8cf")
    private CheckR1580 checkerInstance = null;

    @objid ("c2bfa0d0-f2d6-43da-8090-b09438af7c9c")
    @Override
    public String getRuleId() {
        return R1580.RULEID;
    }

    @objid ("d21b1ce9-e05f-4664-b74d-2036c345bc13")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(Attribute.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
        plan.registerRule(AssociationEnd.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
        plan.registerRule(Operation.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
        
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("65e73406-ef7d-4e8f-bb40-ffdb5889253e")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("6d43b3e7-2d5a-4d71-95aa-b32bbc5eb8b7")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("a40f82bf-45ae-4a37-9e3c-7867ef5bfd17")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1580
     */
    @objid ("c17540d1-e3e2-4814-9429-f69ea627be35")
    public  R1580() {
        this.checkerInstance = new CheckR1580(this);
    }

    @objid ("50e9dfb4-e92f-43ac-aa24-6e53e53fe18a")
    private static class CheckR1580 extends AbstractControl {
        @objid ("cd4e446d-663a-45ea-8017-ffb15791f788")
        public  CheckR1580(IRule rule) {
            super(rule);
        }

        @objid ("fd216759-9e6c-463a-b523-145f75e6acc8")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            diagnostic.addEntry(checkR1580(element));
            return diagnostic;
        }

        @objid ("96cb5e1b-2576-462e-868c-684b035dd461")
        private IAuditEntry checkR1580(MObject element) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(), AuditSeverity.AuditSuccess, element, null);
            Feature feature = (Feature) element;
            if (feature.isIsAbstract() && feature.isIsClass()) {
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(feature);
                linkedObjects.add(feature.getMClass().getName());
                auditEntry.setLinkedInfos(linkedObjects);
                auditEntry.setSeverity(this.rule.getSeverity());
            }
            return auditEntry;
        }

    }

}
