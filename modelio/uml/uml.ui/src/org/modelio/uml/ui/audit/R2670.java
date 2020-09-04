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
import org.modelio.metamodel.uml.behavior.stateMachineModel.AbstractPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.DeepHistoryPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.KindOfStateMachine;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Region;
import org.modelio.metamodel.uml.behavior.stateMachineModel.ShallowHistoryPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: StateMachineChecker checkR63017 error
 */
@objid ("7164ad40-910e-4ba8-97e7-943f7cbf464c")
public class R2670 extends AbstractUmlRule {
    @objid ("32689b85-da48-47bf-a346-17ae66a7d2d4")
    private static final String RULEID = "R2670";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("94365659-3fa3-4e75-aed8-8a0895c55c66")
    private CheckR2670 checkerInstance = null;

    @objid ("321c1930-442d-43bc-8d54-eb170d4462eb")
    @Override
    public String getRuleId() {
        return R2670.RULEID;
    }

    @objid ("1cb8e97a-e7cf-4426-adec-2b541f085727")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(StateMachine.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(DeepHistoryPseudoState.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.MOVE);
        plan.registerRule(ShallowHistoryPseudoState.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.MOVE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("082c927a-9eb8-426b-8ff9-a1f03b320ab0")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("a7123286-a0b1-46bd-85d6-dfcee6dbf91e")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("8ab9f31e-65e9-4aba-9145-dfae2de36a73")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2670
     */
    @objid ("fdd7b406-bd85-4f48-bfe8-84a06117548e")
    public R2670() {
        this.checkerInstance = new CheckR2670(this);
    }

    @objid ("6c34eb25-f66a-4fe0-8869-781a39b4bc76")
    private static class CheckR2670 extends AbstractControl {
        @objid ("52ecb4df-78f5-4cd8-b84f-ddfd27884467")
        public CheckR2670(IRule rule) {
            super(rule);
        }

        @objid ("dfd93ef2-c088-40e2-8ec5-cca95cee63e7")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof StateMachine) {
                diagnostic.addEntry(checkR2670((StateMachine) element));
            } else if (element instanceof DeepHistoryPseudoState || element instanceof ShallowHistoryPseudoState) {
                Region region = ((AbstractPseudoState) element).getParent();
                MObject owner = null;
                if (region != null && (owner = region.getCompositionOwner()) != null && owner instanceof StateMachine) {
                    diagnostic.addEntry(checkR2670((StateMachine) owner));
                }
            } else {
                UmlUi.LOG.warning("R2670: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("d5a6d817-91d0-404f-96fe-0a3013f0a299")
        private IAuditEntry checkR2670(StateMachine stateMachine) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(), AuditSeverity.AuditSuccess, stateMachine, null);
            
            if (stateMachine.getKind().equals(KindOfStateMachine.PROTOCOL)) {
            
                Region region = stateMachine.getTop();
            
                List<DeepHistoryPseudoState> deepsHistories = region.getSub(DeepHistoryPseudoState.class);
                List<ShallowHistoryPseudoState> shallowHistories = region.getSub(ShallowHistoryPseudoState.class);
            
                if (!deepsHistories.isEmpty() || !shallowHistories.isEmpty()) {
                    auditEntry.setSeverity(this.rule.getSeverity());
                    List<Object> linkedObjects = new ArrayList<>();
                    linkedObjects.add(stateMachine);
                    linkedObjects.addAll(deepsHistories);
                    linkedObjects.addAll(shallowHistories);
                    auditEntry.setLinkedInfos(linkedObjects);
                }
            }
            return auditEntry;
        }

    }

}
