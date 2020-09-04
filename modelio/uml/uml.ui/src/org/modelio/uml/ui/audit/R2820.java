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
import org.modelio.metamodel.uml.behavior.stateMachineModel.InitialPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateVertex;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Transition;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: TransitionChecker checkR63012 error
 */
@objid ("c28a5c41-9a69-449f-960c-ec6d2061306b")
public class R2820 extends AbstractUmlRule {
    @objid ("e17a4d5b-82ae-45c9-9175-ba7915deb303")
    private static final String RULEID = "R2820";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("7de6c714-9ee1-4c05-8b73-4458051e7cf9")
    private CheckR2820 checkerInstance = null;

    @objid ("4587b054-e488-4adb-ae1e-d002880bda7e")
    @Override
    public String getRuleId() {
        return R2820.RULEID;
    }

    @objid ("e23f06f8-4bba-4821-a57c-53afc9613a40")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(Transition.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE | AuditTrigger.MOVE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("42f8efb5-89b9-43c7-a324-7f35d51269f7")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("f8e3b313-46fe-4eb8-b2be-e09b2ffdbfe4")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("c9bd66a7-e616-4f9b-9ca7-3e4fd57579dd")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2820
     */
    @objid ("614a9d05-f3b3-46d7-b295-53c6c59b63ec")
    public R2820() {
        this.checkerInstance = new CheckR2820(this);
    }

    @objid ("ae26d682-905d-4cef-80e3-dd967a85e2c4")
    private static class CheckR2820 extends AbstractControl {
        @objid ("f7bf91b3-58e3-4d0f-b344-3adaf5de8d31")
        public CheckR2820(IRule rule) {
            super(rule);
        }

        @objid ("6a3f3c30-6221-452c-adc1-d583711a9f97")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof Transition) {
                diagnostic.addEntry(checkR2820((Transition) element));
            } else {
                UmlUi.LOG.warning("R2820: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("3bfa9a1f-6481-483d-99c4-41937da3ffbb")
        private IAuditEntry checkR2820(Transition transition) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(), AuditSeverity.AuditSuccess, transition, null);
            
            StateVertex target = transition.getTarget();
            
            if (target instanceof InitialPseudoState) {
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(transition);
                linkedObjects.add(target);
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

    }

}
