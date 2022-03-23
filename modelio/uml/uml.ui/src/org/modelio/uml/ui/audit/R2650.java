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
import org.modelio.metamodel.uml.behavior.stateMachineModel.KindOfStateMachine;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: StateMachineChecker checkR63009 error
 */
@objid ("3141aeb1-9971-4a1c-940f-860bb8cc39e7")
public class R2650 extends AbstractUmlRule {
    @objid ("3f5f7081-1816-4076-a1c6-150df5e41ba7")
    private static final String RULEID = "R2650";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("f42bddd8-7373-4d61-9a0f-69d5eddc90a3")
    private CheckR2650 checkerInstance = null;

    @objid ("6906e3e4-60bc-4b2e-9d79-7c1424026483")
    @Override
    public String getRuleId() {
        return R2650.RULEID;
    }

    @objid ("97d606b5-0f5c-4f7f-8c70-af7282fcceb5")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(StateMachine.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.MOVE | AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("f4a19023-c698-40ec-9a55-eb54362114ce")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("03daa894-8ced-4dbc-814d-617840b4909f")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("c82b18e1-30ae-4057-b4e1-9468556c9d92")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2650
     */
    @objid ("2c334c45-6c60-4c8f-b57f-3055590aa662")
    public  R2650() {
        this.checkerInstance = new CheckR2650(this);
    }

    @objid ("27fabacc-b4db-4493-a0ad-db4a61e372ff")
    private static class CheckR2650 extends AbstractControl {
        @objid ("3222e5a9-4459-4caf-b8fa-598265bb66e5")
        public  CheckR2650(IRule rule) {
            super(rule);
        }

        @objid ("b08abeba-ab6c-47e0-aadb-f353daf9d22d")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof StateMachine) {
                diagnostic.addEntry(checkR2650((StateMachine) element));
            } else {
                UmlUi.LOG.warning("R2650: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("18099336-3115-4be3-8e97-a584878ce493")
        private IAuditEntry checkR2650(StateMachine stateMachine) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(), AuditSeverity.AuditSuccess, stateMachine, null);
            
            if (stateMachine.getKind().equals(KindOfStateMachine.PROTOCOL)) {
            
                MObject owner = stateMachine.getCompositionOwner();
            
                if (!(owner instanceof Classifier)) {
                    auditEntry.setSeverity(this.rule.getSeverity());
                    List<Object> linkedObjects = new ArrayList<>();
                    linkedObjects.add(stateMachine);
                    linkedObjects.add(owner);
                    auditEntry.setLinkedInfos(linkedObjects);
                }
            }
            return auditEntry;
        }

    }

}
