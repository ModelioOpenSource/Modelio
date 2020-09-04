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
import org.modelio.metamodel.uml.behavior.usecaseModel.Actor;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * New rule to check Association with same source and target
 */
@objid ("751b6155-f110-435d-a049-cf14ee05e5b5")
public class R2890 extends AbstractUmlRule {
    @objid ("8180f61a-fa51-45c2-bc45-3b684962a71a")
    private static final String RULEID = "R2890";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(IElement)
     * @see AbstractRule#getUpdateControl(IElement)
     * @see AbstractRule#getMoveControl(IElementMovedEvent)
     */
    @objid ("9c2c12e1-fb08-4c4b-952e-49fe658499dc")
    private CheckR2890 checkerInstance = null;

    @objid ("48e35f8b-30a9-4e44-8da1-8af1422753fd")
    @Override
    public String getRuleId() {
        return R2890.RULEID;
    }

    @objid ("e59979d9-4051-4aa6-98ba-4a7099835f7a")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        // plan actor && usecase
        plan.registerRule(AssociationEnd.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE | AuditTrigger.MOVE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("7c3e6c35-b7a5-4c7e-b08b-76b4dc0b40aa")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("5ca61a37-8492-44bb-8609-c296a94b523c")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("8675213c-36b2-4421-8bcf-2c99933907c7")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2890
     */
    @objid ("52b19bf5-822f-4994-9c89-01445ed765c5")
    public R2890() {
        this.checkerInstance = new CheckR2890(this);
    }

    @objid ("7fdf840c-1d3f-4d61-8977-08b994d5c5e6")
    private static class CheckR2890 extends AbstractControl {
        @objid ("861ce989-134b-4743-bf2b-78387d2ae92a")
        public CheckR2890(IRule rule) {
            super(rule);
        }

        @objid ("d31bb0b4-881c-43cb-be4f-a92cb6cf3efd")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof AssociationEnd) {
                if (((AssociationEnd) element).getAssociation() != null) {
                    diagnostic.addEntry(checkR2890(((AssociationEnd) element).getAssociation()));
                } else {
                    UmlUi.LOG.warning("R2890: unsupported element type '%s'", element.getMClass().getName());
                }
            }
            return diagnostic;
        }

        @objid ("d3193209-4fdc-487f-8c07-5de4f5f9c98b")
        private IAuditEntry checkR2890(Association association) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(), AuditSeverity.AuditSuccess, association, null);
            
            List<AssociationEnd> assocEnds = association.getEnd();
            
            if (assocEnds.size() != 2) {
                return auditEntry;
            }
            
            Classifier owner1 = assocEnds.get(0).getSource() != null ? assocEnds.get(0).getSource() : assocEnds.get(0).getOpposite().getTarget();
            Classifier owner2 = assocEnds.get(1).getSource() != null ? assocEnds.get(1).getSource() : assocEnds.get(1).getOpposite().getTarget();
            
            if (owner1 != null && owner2 != null
                    && ((owner1 instanceof UseCase && owner2 instanceof UseCase)
                            || (owner1 instanceof Actor && owner2 instanceof Actor))
                    && owner1.equals(owner2)) {
            
                // Rule failed
            
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(association);
                linkedObjects.add(owner1);
                linkedObjects.add(owner2);
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

    }

}
