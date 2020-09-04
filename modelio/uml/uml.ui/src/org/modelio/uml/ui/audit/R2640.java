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
import org.modelio.metamodel.uml.behavior.stateMachineModel.KindOfStateMachine;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: StateMachineChecker checkR63008 error
 */
@objid ("a03686df-06fc-4f2e-a621-9989558bdd62")
public class R2640 extends AbstractUmlRule {
    @objid ("ef16880e-671f-4133-bb7a-124042b0e68b")
    private static final String RULEID = "R2640";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("61f87235-94f7-4529-bb48-52e6ae5476c4")
    private CheckR2640 checkerInstance = null;

    @objid ("4fd6a4f5-b624-49c4-866e-f24d69e39a2b")
    @Override
    public String getRuleId() {
        return R2640.RULEID;
    }

    @objid ("57055bbd-753b-4030-bc3a-3498ad8d6938")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(StateMachine.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.MOVE | AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("d48995d5-f310-4b79-8ecf-f4bbe14fffc2")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("fdc77366-cd4b-4ff3-bfbd-d0a19d6dbc8e")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("e2582e7b-a70b-4c90-87dc-d7ee97a14597")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2640
     */
    @objid ("b5b901bb-1d67-4217-b843-599561b8dbb2")
    public R2640() {
        this.checkerInstance = new CheckR2640(this);
    }

    @objid ("1a6a7b77-8977-47bf-adb6-595d6347f599")
    private static class CheckR2640 extends AbstractControl {
        @objid ("2b3052d0-f947-47a3-851b-fe0009683136")
        public CheckR2640(IRule rule) {
            super(rule);
        }

        @objid ("a37af918-4b7d-48a9-ba0f-6ec6a984976c")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof StateMachine) {
                diagnostic.addEntry(checkR2640((StateMachine) element));
            } else {
                UmlUi.LOG.warning("R2640: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("449dc951-8bff-4ceb-aaf3-3002a23958c6")
        private IAuditEntry checkR2640(StateMachine stateMachine) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(), AuditSeverity.AuditSuccess, stateMachine, null);
            
            MObject owner = stateMachine.getCompositionOwner();
            
            if (owner instanceof Interface && stateMachine.getKind() != KindOfStateMachine.PROTOCOL) {
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(stateMachine);
                linkedObjects.add(owner);
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

    }

}
