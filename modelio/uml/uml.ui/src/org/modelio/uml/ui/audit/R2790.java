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
import org.modelio.metamodel.uml.behavior.stateMachineModel.Region;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateVertex;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Transition;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: TransitionChecker checkR63005 error
 */
@objid ("01c12a50-ce03-4570-90c8-def4e79bc568")
public class R2790 extends AbstractUmlRule {
    @objid ("ccfa2932-48f8-4b78-a8ee-94911778d6c0")
    private static final String RULEID = "R2790";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("3e4d015d-be63-4358-b4f0-d8fa5a412994")
    private CheckR2790 checkerInstance = null;

    @objid ("ea5a79d4-c540-4305-9379-7d20bd92d9f2")
    @Override
    public String getRuleId() {
        return R2790.RULEID;
    }

    @objid ("00a1f4b2-3b97-45c3-8981-8ae3743826e1")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(Transition.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE | AuditTrigger.MOVE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("75bc8ed8-335e-4103-940c-49a05fe152a2")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("4fb91f0a-0ce5-479e-ab3d-39861d72a3a2")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("84ab4468-68cc-47c7-ab3e-a73194626221")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2790
     */
    @objid ("74bab438-7417-4c2a-b4fc-1739df41ad2b")
    public R2790() {
        this.checkerInstance = new CheckR2790(this);
    }

    @objid ("7e4b8a52-f452-4305-92ea-71b9ee26c900")
    private static class CheckR2790 extends AbstractControl {
        @objid ("14ee2b1f-1360-4052-99f1-feb732718004")
        public CheckR2790(IRule rule) {
            super(rule);
        }

        @objid ("06ee1011-5795-4269-8a3d-3d7fd9fe9e28")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof Transition) {
                diagnostic.addEntry(checkR2790((Transition) element));
            } else {
                UmlUi.LOG.warning("R2790: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("141da8d3-6700-4684-9785-c98cf802d65e")
        private IAuditEntry checkR2790(Transition transition) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(), AuditSeverity.AuditSuccess, transition, null);
            
            StateVertex source = transition.getSource();
            StateVertex target = transition.getTarget();
            Region sourceRegion = source.getParent();
            Region targetRegion = target.getParent();
            
            if (sourceRegion != null && targetRegion != null && !sourceRegion.equals(targetRegion)) {
                State sourceState = sourceRegion.getParent();
                State targetState = targetRegion.getParent();
            
                if (sourceState != null && sourceState.equals(targetState)) {
                    auditEntry.setSeverity(this.rule.getSeverity());
                    List<Object> linkedObjects = new ArrayList<>();
                    linkedObjects.add(transition);
                    linkedObjects.add(sourceRegion);
                    linkedObjects.add(targetRegion);
                    linkedObjects.add(sourceState);
                    auditEntry.setLinkedInfos(linkedObjects);
                }
            }
            return auditEntry;
        }

    }

}
