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
import org.modelio.metamodel.uml.behavior.activityModel.InstanceNode;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectNodeOrderingKind;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: ActivityModelChecker checkR61057 error
 */
@objid ("0c3f8718-2d36-49a4-874a-ca7158b49a34")
public class R1350 extends AbstractUmlRule {
    @objid ("af87c759-c92a-4d08-8e03-bf58d97a95fc")
    private static final String RULEID = "R1350";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("86f17b69-88b0-4072-ab70-a61973b5c9f2")
    private CheckR1350 checkerInstance = null;

    @objid ("c8c1c94f-1c74-4bdb-aa65-42682e21ec48")
    @Override
    public String getRuleId() {
        return R1350.RULEID;
    }

    @objid ("7aea4b68-4a61-4728-8b38-1b616ae59fde")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(InstanceNode.MQNAME, this,
                AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("eb62a934-580c-4fe3-b383-da35075fb599")
    @Override
    public IControl getCreationControl(MObject element) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("383fad83-17c4-440b-80f8-9e204e9b2b83")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("9c94eabd-72de-4ee3-b45f-04518800aa18")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1350
     */
    @objid ("0cde10a6-380e-4c04-a9ee-5112838874fe")
    public R1350() {
        this.checkerInstance = new CheckR1350(this);
    }

    @objid ("12bfa406-ddf6-47a9-8e2d-206b060b6e14")
    private static class CheckR1350 extends AbstractControl {
        @objid ("18ca6e14-af5e-41e7-a98c-55ab19282d29")
        public CheckR1350(IRule rule) {
            super(rule);
        }

        @objid ("3083756e-5b04-4609-834a-bf743dce6c3e")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof InstanceNode) {
                diagnostic.addEntry(checkR1350((InstanceNode) element));
            } else {
                UmlUi.LOG.warning("R1350: unsupported element type '%s'",
                        element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("95311c4d-c5a8-4b8c-bdec-7eff6fd72bff")
        private IAuditEntry checkR1350(InstanceNode objectNnode) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess, objectNnode, null);
            
            boolean isOrdered = objectNnode.getOrdering() == ObjectNodeOrderingKind.ORDERED;
            boolean hasSelectionBehavior = !objectNnode.getSelectionBehavior().isEmpty();
            
            if ((isOrdered && !hasSelectionBehavior) || (!isOrdered && hasSelectionBehavior)) {
            
                // Rule failed
            
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(objectNnode);
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

    }

}
