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
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: AssociationChecker checkR36 error
 */
@objid ("fd0d421b-0b94-426d-a43e-1d70db57f7f3")
public class R1470 extends AbstractUmlRule {
    @objid ("98331598-e3ea-4458-9aa8-46a555269f06")
    private static final String RULEID = "R1470";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(IElement)
     * @see AbstractRule#getUpdateControl(IElement)
     * @see AbstractRule#getMoveControl(IElementMovedEvent)
     */
    @objid ("015c4e7d-1aaf-4ae9-9c0f-3a020bef0495")
    private CheckR1470 checkerInstance = null;

    @objid ("0af1bfce-1395-4a2e-8da4-c0a33b01376c")
    @Override
    public String getRuleId() {
        return R1470.RULEID;
    }

    @objid ("e08a9f1a-e242-4799-adb8-e7813898342c")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        // This is necessary in case of a delete or move event, which trigger an update on the old parent
        plan.registerRule(AssociationEnd.MQNAME, this, AuditTrigger.UPDATE);
        
        plan.registerRule(Attribute.MQNAME, this, AuditTrigger.MOVE | AuditTrigger.UPDATE);
        
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("3090b616-6316-44b2-a493-115e88bb95f5")
    @Override
    public IControl getCreationControl(MObject element) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("08e272d2-124e-4b8c-95da-09a492384fb2")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("8633a0de-f6e4-4c36-b47f-b09a4ed3e77f")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1470
     */
    @objid ("bba27e37-2246-49f1-9434-4a65d4d636ad")
    public  R1470() {
        this.checkerInstance = new CheckR1470(this);
    }

    @objid ("e296dffe-712f-4c05-9feb-9ca2db6b5c1e")
    private static class CheckR1470 extends AbstractControl {
        @objid ("b913db58-faa0-417f-96db-61a978f3fdc6")
        public  CheckR1470(IRule rule) {
            super(rule);
        }

        @objid ("92d8fb1f-4cb8-4870-be2b-422ca9ab4bb8")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof AssociationEnd) {
                diagnostic.addEntry(checkR1470((AssociationEnd) element));
            } else if (element instanceof Attribute) {
                MObject owner = ((Attribute) element).getCompositionOwner();
                if (owner instanceof AssociationEnd) {
                    diagnostic.addEntry(checkR1470((AssociationEnd) owner));
                }
            } else {
                UmlUi.LOG.warning("R1470: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("41550f38-68c8-41b2-bff5-8085b4c3e3d6")
        private IAuditEntry checkR1470(final AssociationEnd assocEnd) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    assocEnd,
                    null);
            
            List<String> qualifierNames = new ArrayList<>();
            
            for (Attribute qualifier : assocEnd.getQualifier()) {
                String name = qualifier.getName();
                if (qualifierNames.contains(name)) {
            
                    // Rule failed
            
                    auditEntry.setSeverity(this.rule.getSeverity());
                    List<Object> linkedObjects = new ArrayList<>();
                    linkedObjects.add(assocEnd);
                    linkedObjects.add(assocEnd.getSource() != null ? assocEnd.getSource() : assocEnd.getOpposite().getTarget());
                    linkedObjects.add(name);
                    auditEntry.setLinkedInfos(linkedObjects);
            
                } else {
                    qualifierNames.add(name);
                }
            }
            return auditEntry;
        }

    }

}
