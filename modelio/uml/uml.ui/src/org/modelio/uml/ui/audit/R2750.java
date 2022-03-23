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
import org.modelio.metamodel.uml.behavior.commonBehaviors.Event;
import org.modelio.metamodel.uml.behavior.stateMachineModel.ForkPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.JoinPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateVertex;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Transition;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: TransitionChecker checkR63000 warning
 */
@objid ("9fca1a29-6834-4d9a-a0fe-70561d0a3df3")
public class R2750 extends AbstractUmlRule {
    @objid ("591e2ef4-c97a-4ee5-8432-6e685efbaee8")
    private static final String RULEID = "R2750";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("a5cda7cf-2ec5-4670-962b-bd5af316a55f")
    private CheckR2750 checkerInstance = null;

    @objid ("92354154-00d0-4cea-adf5-6eadbe79dde7")
    @Override
    public String getRuleId() {
        return R2750.RULEID;
    }

    @objid ("c7928d84-79ff-434d-9388-d120fff48729")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(Transition.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE | AuditTrigger.MOVE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("fb41df30-0a1a-41b2-98d5-7b05b59d8a5f")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("3e1362a8-82c4-46ae-ac5d-2dad31ffaa24")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("004385f0-242e-472b-b013-cbe35917f72d")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2750
     */
    @objid ("8175346d-73ff-48ca-8494-a6870f0faaef")
    public  R2750() {
        this.checkerInstance = new CheckR2750(this);
    }

    @objid ("542dd43d-e97a-442f-85cc-aa609de9eaf2")
    private static class CheckR2750 extends AbstractControl {
        @objid ("57725c5c-72e2-4c2e-9fc8-a292c4b9c624")
        public  CheckR2750(IRule rule) {
            super(rule);
        }

        @objid ("4080dac0-7638-4aa2-af9d-c18f45b2ae87")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof Transition) {
                diagnostic.addEntry(checkR2750((Transition) element));
            } else {
                UmlUi.LOG.warning("R2750: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("f40f6734-e46a-4cd4-9d03-200724fce4a2")
        private IAuditEntry checkR2750(Transition transition) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(), AuditSeverity.AuditSuccess, transition, null);
            
            StateVertex source = transition.getSource();
            
            if (source instanceof ForkPseudoState || source instanceof JoinPseudoState) {
                String guard = transition.getGuard();
                Event event = transition.getTrigger();
            
                if (!guard.isEmpty() || event != null) {
                    auditEntry.setSeverity(this.rule.getSeverity());
                    List<Object> linkedObjects = new ArrayList<>();
                    linkedObjects.add(transition);
                    linkedObjects.add(source);
                    linkedObjects.add(transition.getTarget());
                    auditEntry.setLinkedInfos(linkedObjects);
                }
            }
            return auditEntry;
        }

    }

}
