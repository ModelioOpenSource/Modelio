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
import org.modelio.metamodel.uml.behavior.activityModel.ActivityAction;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectFlow;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: ActivityModelChecker checkR61049 error
 */
@objid ("fa653876-3fbf-4ecc-92ca-dc28b56679d8")
public class R1280 extends AbstractUmlRule {
    @objid ("ccd9e236-d02c-4ad0-8a1d-bf983e4886f2")
    private static final String RULEID = "R1280";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("29e956b3-9118-405a-9f78-19cfb752dea7")
    private CheckR1280 checkerInstance = null;

    @objid ("f7fd8121-b12f-4c1b-b68e-75505f0df2e1")
    @Override
    public String getRuleId() {
        return R1280.RULEID;
    }

    @objid ("b5336014-5bf2-4bec-ba68-26b0e46c1e20")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(ObjectFlow.MQNAME, this, AuditTrigger.CREATE
                | AuditTrigger.MOVE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("c118d32b-0f0c-49c7-bb8e-4f3a82c36e1f")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("2546d1d2-8a04-4618-9c85-70e68a7fef7e")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("2c3b32a9-4c6d-4855-99f9-57ee603a546d")
    @Override
    public IControl getUpdateControl(MObject element) {
        return null;
    }

    /**
     * Default constructor for R1280
     */
    @objid ("8006281a-81dd-4dd7-b72d-0ce0cede2a08")
    public R1280() {
        this.checkerInstance = new CheckR1280(this);
    }

    @objid ("b40f83b7-9dd2-4bc5-8a5c-cf6e14ccd39b")
    private static class CheckR1280 extends AbstractControl {
        @objid ("95d5e293-9ea7-472a-b8fd-a469707b09bf")
        public CheckR1280(IRule rule) {
            super(rule);
        }

        @objid ("b1a35973-0baa-4297-8b55-0964047f8728")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof ObjectFlow) {
                diagnostic.addEntry(checkR1280((ObjectFlow) element));
            } else {
                UmlUi.LOG.warning("R1280: unsupported element type '%s'",
                        element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("195c2f29-deba-419a-bf79-61a41056d3b2")
        private IAuditEntry checkR1280(ObjectFlow objectFlow) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess, objectFlow, null);
            
            if ((objectFlow.getSource() instanceof ActivityAction) && (objectFlow.getTarget() instanceof ActivityAction)) {
                // Rule failed
            
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(objectFlow);
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

    }

}
