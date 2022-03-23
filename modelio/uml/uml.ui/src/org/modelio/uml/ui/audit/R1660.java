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
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: EnumerationChecker checkElementary
 */
@objid ("81c318fa-8b24-4a70-9174-e03bf73521d2")
public class R1660 extends AbstractUmlRule {
    @objid ("858c7a2a-6049-40ea-9c4a-3863ae1640bf")
    private static final String RULEID = "R1660";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("dc53af5d-d9d1-452f-b17e-6d90e57ec36f")
    private CheckR1660 checkerInstance = null;

    @objid ("da43b1eb-9976-41de-a09e-537067b4fb76")
    @Override
    public String getRuleId() {
        return R1660.RULEID;
    }

    @objid ("e7823221-82de-4c7d-912f-8e305caf43c3")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(Enumeration.MQNAME, this, AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("b013647b-cbce-4967-8902-3008968bafb4")
    @Override
    public IControl getCreationControl(MObject element) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("2b380cd3-c33a-48f1-83a3-66b1bb6e2120")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("21e4896c-40be-48ae-943f-b70e6003e1fa")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1660
     */
    @objid ("f48b4e87-3015-49d3-bcc5-35b0225b8ac1")
    public  R1660() {
        this.checkerInstance = new CheckR1660(this);
    }

    @objid ("1d595a24-1a5e-4e46-9e5e-6382f95123ec")
    private static class CheckR1660 extends AbstractControl {
        @objid ("29de7a4d-c837-43d4-b3ed-9f170921df7b")
        public  CheckR1660(IRule rule) {
            super(rule);
        }

        @objid ("f6f464a8-a7b1-4765-9ac1-656db4c69b83")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof Enumeration) {
                diagnostic.addEntry(checkR1660((Enumeration) element));
            } else {
                UmlUi.LOG.warning("R1660: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("f05e90c2-bf9b-42fb-8218-713742992d4d")
        private IAuditEntry checkR1660(final Enumeration enumeration) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    enumeration,
                    null);
            
            if (!enumeration.isIsElementary()) {
            
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
