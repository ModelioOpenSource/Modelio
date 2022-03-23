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
import org.modelio.metamodel.uml.behavior.usecaseModel.Actor;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.NaryAssociationEnd;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: AssociationChecker checkBiAssociation error
 */
@objid ("ede6ac53-33a4-4397-ba65-d41c64896286")
public class R1420 extends AbstractUmlRule {
    @objid ("7b91f336-ac19-4eac-a5d2-19231b530cd0")
    private static final String RULEID = "R1420";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("77dec6ce-25e7-4da3-a640-1f200e78a73c")
    private CheckR1420 checkerInstance = null;

    @objid ("5eb752e1-699a-44e2-8eba-9405659e84d2")
    @Override
    public String getRuleId() {
        return R1420.RULEID;
    }

    @objid ("fab43d62-9316-4407-b34c-8b32d083e35e")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(NaryAssociationEnd.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.MOVE | AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("4e1e4691-ffb7-412d-91fc-a44f4ca4d0f9")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("126adc48-c7f5-4d51-ae77-4378ace11f2a")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("1eb4806b-5099-475a-a1a5-5bae8fa9dbaf")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1420
     */
    @objid ("56997879-0cd6-488a-b004-dfbaaa564ac1")
    public  R1420() {
        this.checkerInstance = new CheckR1420(this);
    }

    @objid ("f9227ce0-1bf5-478a-b602-7efd84024383")
    private static class CheckR1420 extends AbstractControl {
        @objid ("386a0635-699d-4550-8adc-3ce65700af8c")
        public  CheckR1420(IRule rule) {
            super(rule);
        }

        @objid ("a7b82aa4-6b15-4f90-9c3b-3a2c2c7476db")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof NaryAssociationEnd) {
                diagnostic.addEntry(checkR1420((NaryAssociationEnd) element));
            } else {
                UmlUi.LOG.warning("R1420: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("6417f37d-ce0f-4f64-9b17-5a9d497559b3")
        private IAuditEntry checkR1420(final NaryAssociationEnd assocEnd) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(), AuditSeverity.AuditSuccess, assocEnd, null);
            
            Classifier owner = assocEnd.getOwner();
            
            if (owner instanceof Actor || owner instanceof UseCase) {
            
                // Rule failed
            
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(owner);
                linkedObjects.add(assocEnd);
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

    }

}
