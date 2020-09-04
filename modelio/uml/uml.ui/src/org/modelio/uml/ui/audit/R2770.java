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
import org.modelio.metamodel.uml.behavior.stateMachineModel.JoinPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateVertex;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Transition;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: TransitionChecker checkR63002 error
 */
@objid ("77d02751-7cc7-4b1f-b940-a162a76b8cc9")
public class R2770 extends AbstractUmlRule {
    @objid ("7a4f3989-6e4a-49b8-b512-322c3e4204f3")
    private static final String RULEID = "R2770";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("fa526ad2-9c08-4291-a291-af93a08d65f8")
    private CheckR2770 checkerInstance = null;

    @objid ("6e12862f-39e7-4ee2-a57a-f4cfd9cfef28")
    @Override
    public String getRuleId() {
        return R2770.RULEID;
    }

    @objid ("89265206-0ae9-451a-b101-30ae53cec8ce")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(Transition.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE | AuditTrigger.MOVE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("393fd18c-3c63-4ff3-869c-58e5b8734ebd")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("9b42584a-7c1f-4ab6-8be7-84d04420e990")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("705a2b72-4043-4fc3-a9a6-5daebe1c85d3")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2770
     */
    @objid ("a710b8b7-888a-445f-9250-3ed21e6ccf9b")
    public R2770() {
        this.checkerInstance = new CheckR2770(this);
    }

    @objid ("55f4610f-6f50-44b2-b567-cede8ae0d660")
    private static class CheckR2770 extends AbstractControl {
        @objid ("ab536543-52dc-4cda-aafe-746f5b338d07")
        public CheckR2770(IRule rule) {
            super(rule);
        }

        @objid ("6d730bc5-9a54-459c-a2d4-a5c2b7fec507")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof Transition) {
                diagnostic.addEntry(checkR2770((Transition) element));
            } else {
                UmlUi.LOG.warning("R2770: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("c80b0d01-cc69-4c4d-b1cd-b2f3907c11f4")
        private IAuditEntry checkR2770(Transition transition) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(), AuditSeverity.AuditSuccess, transition, null);
            
            StateVertex target = transition.getTarget();
            
            if (target instanceof JoinPseudoState) {
                StateVertex source = transition.getSource();
                if (!(source instanceof State)) {
                    auditEntry.setSeverity(this.rule.getSeverity());
                    List<Object> linkedObjects = new ArrayList<>();
                    linkedObjects.add(target);
                    linkedObjects.add(source);
                    auditEntry.setLinkedInfos(linkedObjects);
                }
            }
            return auditEntry;
        }

    }

}
