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
import org.modelio.metamodel.uml.behavior.stateMachineModel.AbstractPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.DeepHistoryPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.ShallowHistoryPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateVertex;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Transition;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: TransitionChecker checkR63007 error
 */
@objid ("6662895b-2bda-4365-a77d-ad010dffad1b")
public class R2810 extends AbstractUmlRule {
    @objid ("6d2d1d8b-13ac-4824-adf9-0af8e36166ed")
    private static final String RULEID = "R2810";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("5c12657c-fbf4-4e19-b2bd-a953d353c427")
    private CheckR2810 checkerInstance = null;

    @objid ("a4f70468-1e2b-4e0c-b37a-07a82a95f8e8")
    @Override
    public String getRuleId() {
        return R2810.RULEID;
    }

    @objid ("c933d6d8-def6-4fe9-ac60-910edf341f41")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(Transition.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.UPDATE | AuditTrigger.MOVE);
        plan.registerRule(DeepHistoryPseudoState.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(ShallowHistoryPseudoState.MQNAME, this, AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("536d0f3c-37c6-47bc-82ea-8bc4c9238012")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("20e48863-7470-46a4-b99e-056e08156340")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("0e42417c-6a35-46e4-b388-a26d5e260b9c")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2810
     */
    @objid ("7262a150-c12d-4d83-ae75-63fbc05b16b4")
    public R2810() {
        this.checkerInstance = new CheckR2810(this);
    }

    @objid ("825b4495-4d94-4e32-8530-20cfe2a2d05e")
    private static class CheckR2810 extends AbstractControl {
        @objid ("d9cd5fa7-86ae-4a84-8804-e3febc90dd13")
        public CheckR2810(IRule rule) {
            super(rule);
        }

        @objid ("28492e7b-2c5a-4b97-ad77-060b05072df4")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof Transition) {
                StateVertex source = ((Transition) element).getSource();
                if (source instanceof DeepHistoryPseudoState || source instanceof ShallowHistoryPseudoState) {
                    diagnostic.addEntry(checkR2810((AbstractPseudoState) source));
                }
            } else if (element instanceof DeepHistoryPseudoState || element instanceof ShallowHistoryPseudoState) {
                diagnostic.addEntry(checkR2810((AbstractPseudoState) element));
            } else {
                UmlUi.LOG.warning("R2810: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("9dd18d2c-09cc-4989-ace6-2610a87f8547")
        private IAuditEntry checkR2810(AbstractPseudoState state) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(), AuditSeverity.AuditSuccess, state, null);
            
            List<Transition> outgoings = state.getOutGoing();
            if (outgoings.size() > 1) {
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(state);
                linkedObjects.addAll(outgoings);
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

    }

}
