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
import org.modelio.metamodel.uml.behavior.activityModel.ObjectFlow;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: ActivityModelChecker checkR61054 error
 */
@objid ("5ad4ecf8-4a6c-4346-9d42-61aea1bd7da6")
public class R1320 extends AbstractUmlRule {
    @objid ("ff045681-73d2-4ab5-938a-ecf28c992d25")
    private static final String RULEID = "R1320";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("e09f98d9-75d2-4a2c-b164-674ccc8bc008")
    private CheckR1320 checkerInstance = null;

    @objid ("9887125f-d5f3-4d3a-b20b-48b5126e9937")
    @Override
    public String getRuleId() {
        return R1320.RULEID;
    }

    @objid ("1eb93540-125f-474b-a0f6-3e8283b3112c")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(ObjectFlow.MQNAME, this, AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("8b993a93-20de-45b6-ad0e-537a35b6b3f5")
    @Override
    public IControl getCreationControl(MObject element) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("5df0e56d-b4c5-4942-9c18-2026e59da094")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("d49d710d-7b02-418e-9904-1868e7c546a5")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1320
     */
    @objid ("2778cb92-29e3-4618-98cf-3eba378a738f")
    public  R1320() {
        this.checkerInstance = new CheckR1320(this);
    }

    @objid ("c09a7391-0532-4caa-b516-50f655ce31b6")
    private static class CheckR1320 extends AbstractControl {
        @objid ("b7998a65-8192-4b1e-a67b-d047b6962b72")
        public  CheckR1320(IRule rule) {
            super(rule);
        }

        @objid ("7fcdeb55-cb13-48df-997c-3ac017d876b4")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof ObjectFlow) {
                diagnostic.addEntry(checkR1320((ObjectFlow) element));
            } else {
                UmlUi.LOG.warning("R1320: unsupported element type '%s'",
                        element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("f6d66553-51c8-43f0-98b4-aa2d60448579")
        private IAuditEntry checkR1320(ObjectFlow objectFlow) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess, objectFlow, null);
            
            if (objectFlow.isIsMultiCast() && objectFlow.isIsMultiReceive()) {
            
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
