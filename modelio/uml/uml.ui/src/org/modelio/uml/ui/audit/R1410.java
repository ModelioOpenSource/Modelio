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
import org.modelio.metamodel.uml.statik.AggregationKind;
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: AssociationChecker checkAggregation error
 */
@objid ("b9f2a219-64db-4ce6-b7fb-4c6619c91381")
public class R1410 extends AbstractUmlRule {
    @objid ("69638b25-ff4e-4c5c-bc88-45d6beee8e1f")
    private static final String RULEID = "R1410";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(IElement)
     * @see AbstractRule#getUpdateControl(IElement)
     * @see AbstractRule#getMoveControl(IElementMovedEvent)
     */
    @objid ("76fc8f4d-d8ec-45bc-9ddb-ce2be725a0b9")
    private CheckR1410 checkerInstance = null;

    @objid ("b6d8b6e4-2fcf-4227-9cc1-5b0fad023cec")
    @Override
    public String getRuleId() {
        return R1410.RULEID;
    }

    @objid ("1f279ffc-f624-4cfb-b2ec-5466b2b4726e")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(AssociationEnd.MQNAME, this, AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("6e86dbfc-0600-4d65-8811-e3c756af350a")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("e49ad2ed-bfa0-4a7e-84db-cb914f2c2cbc")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("82ad4632-499b-4715-aa60-38bba7d3cdee")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1410
     */
    @objid ("7b6a0f1b-2717-4bfb-bae6-8cc12aac4554")
    public R1410() {
        this.checkerInstance = new CheckR1410(this);
    }

    @objid ("bd453698-3c36-42d9-a9ad-e8c45633bc46")
    private static class CheckR1410 extends AbstractControl {
        @objid ("c5cd3883-4a6a-4766-b40a-6c1033b76d29")
        public CheckR1410(IRule rule) {
            super(rule);
        }

        @objid ("c108fc8d-ac62-4280-9147-f08e190999a6")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof AssociationEnd && ((AssociationEnd) element).getAssociation() != null) {
                diagnostic.addEntry(checkR1410(((AssociationEnd) element).getAssociation()));
            } else {
                UmlUi.LOG.warning("R1410: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("eb94c137-560d-4d73-b127-f01903bba227")
        private IAuditEntry checkR1410(final Association association) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(), AuditSeverity.AuditSuccess, association, null);
            
            int nbComposition = 0;
            for (AssociationEnd end : association.getEnd()) {
                if (end.getAggregation().equals(AggregationKind.KINDISAGGREGATION)
                        || end.getAggregation().equals(AggregationKind.KINDISCOMPOSITION)) {
                    nbComposition++;
                }
            }
            
            if (nbComposition > 1) {
                // Rule failed
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(association);
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

    }

}
