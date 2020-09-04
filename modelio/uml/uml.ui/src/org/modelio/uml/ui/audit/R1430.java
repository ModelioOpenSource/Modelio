/* 
 * Copyright 2013-2019 Modeliosoft
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
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: AssociationChecker checkMultiplicity error
 */
@objid ("b85863ba-f5be-4082-b12d-233bd722f1ff")
public class R1430 extends AbstractUmlRule {
    @objid ("8e169e41-a871-4ade-9ecc-1dd62066d26c")
    private static final String RULEID = "R1430";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(IElement)
     * @see AbstractRule#getUpdateControl(IElement)
     * @see AbstractRule#getMoveControl(IElementMovedEvent)
     */
    @objid ("2f3615bb-94de-4e00-86ba-2fd9e28f8ab0")
    private CheckR1430 checkerInstance = null;

    @objid ("ae430a1e-522b-4ace-970a-df11a01f4765")
    @Override
    public String getRuleId() {
        return R1430.RULEID;
    }

    @objid ("372ea264-4a74-4f68-95d1-bfb30c486b57")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(AssociationEnd.MQNAME, this, AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("cb9cca15-ba1f-4f45-b4bb-b491bf5a3178")
    @Override
    public IControl getCreationControl(MObject element) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("98a9933d-51a4-40db-b177-72255270c7f0")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("d47ce564-d03f-406c-a43f-42dacaab3485")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1430
     */
    @objid ("835f1a71-ea6b-48f1-92c3-c788753e9605")
    public R1430() {
        this.checkerInstance = new CheckR1430(this);
    }

    @objid ("e8f3d0e7-f849-4927-a8d1-03646a59333b")
    private static class CheckR1430 extends AbstractControl {
        @objid ("868758ba-9cc6-4fb7-b77b-c92de26619c3")
        public CheckR1430(IRule rule) {
            super(rule);
        }

        @objid ("fa6b3d3e-3cf7-4ae3-88c3-d34c2f44ff87")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof AssociationEnd) {
                diagnostic.addEntry(checkR1430((AssociationEnd) element));
            } else {
                UmlUi.LOG.warning("R1410: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("ca5c63a9-95f0-463d-ba1c-861a1be99cf5")
        private IAuditEntry checkR1430(final AssociationEnd assocEnd) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    assocEnd,
                    null);
            
            String min = assocEnd.getMultiplicityMin();
            String max = assocEnd.getMultiplicityMax();
            
            if (min.isEmpty() || max.isEmpty()) {
                return auditEntry;
            }
            
            // Guard in case the min or max multiplicity is a text
            try {
                if (min.equals("*") || (!max.equals("*") && !(Integer.parseInt(min) <= Integer.parseInt(max)))) {
            
                    // Rule failed
            
                    auditEntry.setSeverity(this.rule.getSeverity());
                    List<Object> linkedObjects = new ArrayList<>();
                    linkedObjects.add(assocEnd);
                    linkedObjects.add(assocEnd.getSource() != null ? assocEnd.getSource() : assocEnd.getOpposite().getTarget());
                    auditEntry.setLinkedInfos(linkedObjects);
                }
            } catch (@SuppressWarnings("unused") NumberFormatException e) {
                // Do nothing, rule is valid
            }
            return auditEntry;
        }

    }

}
