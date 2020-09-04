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
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Transition;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: TransitionChecker checkR63018 warning
 */
@objid ("c83fb3ff-acd7-4ac7-bf55-3ea1f3c23090")
public class R2840 extends AbstractUmlRule {
    @objid ("b6d1ebfa-4f29-4825-9060-9e4f784f2f51")
    private static final String RULEID = "R2840";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("e10f6903-5fc8-41e7-b350-d2d82fceb0dc")
    private CheckR2840 checkerInstance = null;

    @objid ("3aafa229-3ebf-400a-9b25-e663d7aac77d")
    @Override
    public String getRuleId() {
        return R2840.RULEID;
    }

    @objid ("e316a876-61f3-4002-831d-584deb30a1e6")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(Transition.MQNAME, this, AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("d6aeda45-b8b9-45c1-92c1-4682b496eceb")
    @Override
    public IControl getCreationControl(MObject element) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("eea3fdd3-560f-436a-90f6-4d38b3f3e0bb")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("01d4e290-cea3-4141-8bf1-297801dfc0d7")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2840
     */
    @objid ("8b815fe3-8bcd-4c76-8cbc-6f525bbcc71b")
    public R2840() {
        this.checkerInstance = new CheckR2840(this);
    }

    @objid ("479183f4-e847-4709-b70c-d79847513156")
    private static class CheckR2840 extends AbstractControl {
        @objid ("3bec88ea-531d-4f8e-a35a-3ec227e9c0d4")
        public CheckR2840(IRule rule) {
            super(rule);
        }

        @objid ("5a2899f4-6443-4cde-a725-73292f864706")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof Transition) {
                diagnostic.addEntry(checkR2840((Transition) element));
            } else {
                UmlUi.LOG.warning("R2840: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("e79d2bb2-2092-40c1-bfe5-2d1d855249b4")
        private IAuditEntry checkR2840(Transition transition) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(), AuditSeverity.AuditSuccess, transition, null);
            
            int defined = 0;
            Behavior behavior = null;
            Signal signal = null;
            Operation operation = null;
            
            if ((behavior = transition.getBehaviorEffect()) != null) {
                defined++;
            }
            
            if ((signal = transition.getEffects()) != null) {
                defined++;
            }
            
            if ((operation = transition.getProcessed()) != null) {
                defined++;
            }
            
            if (defined > 1) {
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(transition);
                linkedObjects.add(transition.getSource());
                linkedObjects.add(transition.getTarget());
                if (behavior != null) {
                    linkedObjects.add(behavior);
                }
                if (signal != null) {
                    linkedObjects.add(signal);
                }
                if (operation != null) {
                    linkedObjects.add(operation);
                }
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

    }

}
