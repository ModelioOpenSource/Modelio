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
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: AttributeChecker checkTypeIsElementary
 */
@objid ("ed835f52-796e-4d32-b796-5571f3640172")
public class R1480 extends AbstractUmlRule {
    @objid ("8e321dc4-360c-412a-933c-4e1668b0a2d6")
    private static final String RULEID = "R1480";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("92592b22-925d-4bb2-b698-811a19bce48f")
    private CheckR1480 checkerInstance = null;

    @objid ("ebad6333-68e9-447b-a217-eb628bb7760a")
    @Override
    public String getRuleId() {
        return R1480.RULEID;
    }

    @objid ("e1690987-a057-4626-bd18-2beb21760d8d")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(Attribute.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE);
        plan.registerRule(Class.MQNAME, this, AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("40944c0c-f294-41b0-9d0e-c99f517289a9")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("97777a1c-7c2d-4708-94c9-a7098f4eb38e")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("52194451-b0a0-404d-88dc-72e7161c290d")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1480
     */
    @objid ("2de488da-1e45-43bf-ae98-741c67af5842")
    public R1480() {
        this.checkerInstance = new CheckR1480(this);
    }

    @objid ("e8731082-af28-4cee-ba75-71d62187a6f2")
    private static class CheckR1480 extends AbstractControl {
        @objid ("1c6dd426-cf57-49b5-8319-6887cf116502")
        public CheckR1480(IRule rule) {
            super(rule);
        }

        @objid ("ff1a5989-2b16-4e9a-9a29-ac0c0341adf7")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof Attribute) {
                diagnostic.addEntry(checkR1480((Attribute) element));
            } else if (element instanceof Class) {
                Class clazz = (Class) element;
                for (Attribute att : clazz.getObject()) {
                    diagnostic.addEntry(checkR1480(att));
                }
            }
            return diagnostic;
        }

        @objid ("b49afbc4-d10f-4260-9ff9-fa1b506adf63")
        private IAuditEntry checkR1480(final Attribute attribute) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(), AuditSeverity.AuditSuccess, attribute, null);
            
            if (attribute.getQualified() != null) {
                return auditEntry;
            }
            
            GeneralClass type = attribute.getType();
            
            if (type != null && !type.isIsElementary()) {
                // failed
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(attribute);
                linkedObjects.add(type);
                linkedObjects.add(type.getMClass().getName());
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

    }

}
