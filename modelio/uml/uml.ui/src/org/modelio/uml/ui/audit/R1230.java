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
import org.modelio.metamodel.uml.behavior.activityModel.ActivityEdge;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityNode;
import org.modelio.metamodel.uml.behavior.activityModel.ControlFlow;
import org.modelio.metamodel.uml.behavior.activityModel.InitialNode;
import org.modelio.metamodel.uml.behavior.activityModel.MessageFlow;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectFlow;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: ActivityModelChecker checkR61043 error
 */
@objid ("e9c398a3-983a-49bb-8992-3eb897d8aedf")
public class R1230 extends AbstractUmlRule {
    @objid ("26632a1f-7820-409e-990d-8a6b51bd33dd")
    private static final String RULEID = "R1230";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("9d3881a7-3f24-4c65-bfe2-49d29edd588f")
    private CheckR1230 checkerInstance = null;

    @objid ("67662dcc-3a52-4428-95c8-11238f004289")
    @Override
    public String getRuleId() {
        return R1230.RULEID;
    }

    @objid ("176e3967-900d-41ca-98f4-878d8f5e1e9e")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan
                .registerRule(InitialNode.MQNAME, this,
                        AuditTrigger.UPDATE);
        
        plan.registerRule(ObjectFlow.MQNAME, this, AuditTrigger.CREATE
                | AuditTrigger.MOVE);
        plan.registerRule(ControlFlow.MQNAME, this, AuditTrigger.CREATE
                | AuditTrigger.MOVE);
        plan.registerRule(MessageFlow.MQNAME, this, AuditTrigger.CREATE
                | AuditTrigger.MOVE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("3a2aa954-0f15-4ff6-8b05-3c608c306736")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("c0c16634-30cf-457c-ad26-277c7a2e1c34")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("d3f0ba2c-2524-449e-a211-ef92a55eda01")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1230
     */
    @objid ("cbe69896-1d77-4a95-93a5-328dd68312fe")
    public R1230() {
        this.checkerInstance = new CheckR1230(this);
    }

    @objid ("30ed3ca6-281e-4a9a-9623-d9c427f625ea")
    private static class CheckR1230 extends AbstractControl {
        @objid ("5d05dbd5-1afc-4769-a1bf-9c03c79c0d12")
        public CheckR1230(IRule rule) {
            super(rule);
        }

        @objid ("2206f3dd-07c9-4f0a-85d7-b4a06013ad8a")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof InitialNode) {
                diagnostic.addEntry(checkR1230((InitialNode) element));
            } else if (element instanceof ActivityEdge) {
                IAuditEntry auditEntry = checkR1230((ActivityEdge) element);
                if (auditEntry != null) {
                    diagnostic.addEntry(auditEntry);
                }
            } else {
                UmlUi.LOG.warning("R1230: unsupported element type '%s'",
                        element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("15072720-879d-4386-8b55-aafbb30b1c38")
        private IAuditEntry checkR1230(InitialNode initialNode) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess, initialNode, null);
            
            for (ActivityEdge edge : initialNode.getOutgoing()) {
                if (!(edge instanceof ControlFlow)) {
            
                    // Rule failed
            
                    auditEntry.setSeverity(this.rule.getSeverity());
                    List<Object> linkedObjects = new ArrayList<>();
                    linkedObjects.add(initialNode);
                    auditEntry.setLinkedInfos(linkedObjects);
            
                    break;
                }
            }
            return auditEntry;
        }

        @objid ("c5915829-e58e-4c0d-bc0d-2c471a539368")
        private IAuditEntry checkR1230(ActivityEdge activityEdge) {
            ActivityNode activityNode = activityEdge.getSource();
            
            if (activityNode instanceof InitialNode) {
                AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                        AuditSeverity.AuditSuccess, activityNode, null);
                if (activityEdge instanceof ControlFlow) {
                    return auditEntry;
                } else {
            
                    // Rule failed
            
                    auditEntry.setSeverity(this.rule.getSeverity());
                    List<Object> linkedObjects = new ArrayList<>();
                    linkedObjects.add(activityNode);
                    auditEntry.setLinkedInfos(linkedObjects);
            
                    return auditEntry;
                }
            }
            return null;
        }

    }

}
