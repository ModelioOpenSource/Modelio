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
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: EnumerationChecker checkAbstract
 */
@objid ("7c0abd3a-93f6-4ff4-a692-293a0c985503")
public class R1650 extends AbstractUmlRule {
    @objid ("92ade3d7-b344-4b4d-be6d-fecad6b2291f")
    private static final String RULEID = "R1650";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("a3462e3d-b7be-442e-8369-89128bf8909b")
    private CheckR1650 checkerInstance = null;

    @objid ("cf2c5bfe-737a-4812-b3a3-a22bdd140ddc")
    @Override
    public String getRuleId() {
        return R1650.RULEID;
    }

    @objid ("6c998116-4b36-4b5f-a693-a59fe19ab3ac")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(Enumeration.MQNAME, this, AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("ef4879d5-eb16-42ed-bfeb-be08547a7a74")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("9155f3be-26ef-430f-afa0-40eb455fe495")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("16fa453c-f6a7-4f8f-950a-f59caa4b9ff9")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1650
     */
    @objid ("f4c4f4d2-63c7-49ca-b242-f6bcc2f8a87f")
    public R1650() {
        this.checkerInstance = new CheckR1650(this);
    }

    @objid ("584a60d3-da61-4ab9-b2ea-460c0d90564e")
    private static class CheckR1650 extends AbstractControl {
        @objid ("4c986a68-a563-401f-8f35-86317ba8f110")
        public CheckR1650(IRule rule) {
            super(rule);
        }

        @objid ("e3201339-196c-49b2-b80b-7d4dc5ae1012")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof Enumeration) {
                diagnostic.addEntry(checkR1650((Enumeration) element));
            } else {
                UmlUi.LOG.warning("R1650: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("10fc5c4b-2644-4f85-923c-015739a2da64")
        private IAuditEntry checkR1650(final Enumeration enumeration) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(), AuditSeverity.AuditSuccess, enumeration, null);
            
            if (enumeration.isIsAbstract()) {
            
                // Rule failed
            
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(enumeration);
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

    }

}
