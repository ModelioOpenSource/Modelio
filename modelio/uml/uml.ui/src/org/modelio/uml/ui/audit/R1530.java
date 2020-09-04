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
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * New rule to check the nameless condition of an element for some metaclasses that don't really require a name, like Association.
 */
@objid ("aea69409-f17d-4bfd-93a2-dc75de00a9fe")
public class R1530 extends AbstractUmlRule {
    @objid ("6d160027-d259-40c7-a4e2-a1e5eb582d47")
    private static final String RULEID = "R1530";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(IElement)
     * @see AbstractRule#getUpdateControl(IElement)
     * @see AbstractRule#getMoveControl(IElementMovedEvent)
     */
    @objid ("1114328b-bb42-4dfc-8b72-ee6f860203f6")
    private CheckR1530 checkerInstance = null;

    @objid ("32c99562-fc09-46d8-b9ce-a7900537c835")
    @Override
    public String getRuleId() {
        return R1530.RULEID;
    }

    @objid ("262985d3-404b-49c7-809e-efa5ce9448c3")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(Association.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Port.MQNAME, this, AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("5f805b93-0e98-47fc-bcf2-560c849d4594")
    @Override
    public IControl getCreationControl(MObject element) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("36949988-139f-4e58-8daf-f681c5920c6d")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("04287c08-72e3-4e3a-8c16-31b02e06d433")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1530
     */
    @objid ("307112e5-8b57-421a-88bb-1243d1584f39")
    public R1530() {
        this.checkerInstance = new CheckR1530(this);
    }

    @objid ("60d6fc5e-416e-4f08-a6b7-f37fd550b9c6")
    private static class CheckR1530 extends AbstractControl {
        @objid ("8a8ee153-257d-4855-b604-1baa5eccb87b")
        public CheckR1530(IRule rule) {
            super(rule);
        }

        @objid ("f315e54a-630f-49ec-8037-bd72b65be7f1")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            diagnostic.addEntry(checkR1530(element));
            return diagnostic;
        }

        @objid ("1134774e-1125-4e4b-b5d8-498f74380296")
        private IAuditEntry checkR1530(MObject element) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(), AuditSeverity.AuditSuccess, element, null);
            
            if (element.getName().equals("")) {
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(element.getMClass().getName());
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

    }

}
