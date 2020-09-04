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
import org.modelio.metamodel.uml.statik.AggregationKind;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: AssociationChecker checkR34 error
 */
@objid ("a94238ef-a46a-434a-8636-0fa30834e9b8")
public class R1450 extends AbstractUmlRule {
    @objid ("fc8e932e-fe2d-418a-9088-a19459f08fdd")
    private static final String RULEID = "R1450";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(IElement)
     * @see AbstractRule#getUpdateControl(IElement)
     * @see AbstractRule#getMoveControl(IElementMovedEvent)
     */
    @objid ("3d62eec5-9a18-4a2b-8ab7-6a5c38ec8927")
    private CheckR1450 checkerInstance = null;

    @objid ("f2c7fb26-8129-4dea-83f5-421323faafed")
    @Override
    public String getRuleId() {
        return R1450.RULEID;
    }

    @objid ("48c21cf4-71e4-43ed-8420-5585c1890b7a")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(AssociationEnd.MQNAME, this, AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("8feb36dd-45dd-4ea2-a61f-8a2e2f63ebf3")
    @Override
    public IControl getCreationControl(MObject element) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("e5b2dd38-1d4f-4b0a-b6c8-86089ef5136d")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("beeed1d3-b840-4ffb-a875-0e7fceb7dc49")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1450
     */
    @objid ("65d4246b-a417-4578-87f8-9888f0514f40")
    public R1450() {
        this.checkerInstance = new CheckR1450(this);
    }

    @objid ("47fc2dae-a12b-4e40-856d-8ebcca972eed")
    private static class CheckR1450 extends AbstractControl {
        @objid ("bf82ac7b-67ed-4687-beb9-63e98dff0e79")
        public CheckR1450(IRule rule) {
            super(rule);
        }

        @objid ("23465925-39e0-4f4e-83b4-1a65f321f886")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof AssociationEnd) {
                diagnostic.addEntry(checkR1450(((AssociationEnd) element).getOpposite()));
            } else {
                UmlUi.LOG.warning("R1600: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("fb20bba4-6ec9-4753-8294-19ccb11703df")
        private IAuditEntry checkR1450(final AssociationEnd assocEnd) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(), AuditSeverity.AuditSuccess, assocEnd, null);
            
            if (assocEnd.getAggregation().equals(AggregationKind.KINDISCOMPOSITION) && !assocEnd.getOpposite().getMultiplicityMax().equals("1")) {
            
                // Rule failed
            
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(assocEnd);
                linkedObjects.add(assocEnd.getSource() != null ? assocEnd.getSource() : assocEnd.getOpposite().getTarget());
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

    }

}
