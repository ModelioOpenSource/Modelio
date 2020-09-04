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
import org.modelio.metamodel.uml.behavior.stateMachineModel.InternalTransition;
import org.modelio.metamodel.uml.behavior.stateMachineModel.KindOfStateMachine;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Region;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateVertex;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: StateMachineChecker checkR63016 error
 */
@objid ("623adccc-5b0c-402a-abc4-331f2b90be65")
public class R2660 extends AbstractUmlRule {
    @objid ("a8859ce5-5866-4b29-b1a9-2063ed509b81")
    private static final String RULEID = "R2660";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("bfe11556-5a70-4615-ae82-e46952f08440")
    private CheckR2660 checkerInstance = null;

    @objid ("cc5ef1e9-0909-420f-bfea-01f6979f5102")
    @Override
    public String getRuleId() {
        return R2660.RULEID;
    }

    @objid ("083a05c4-ad6a-483a-884e-f160cc50418d")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(StateMachine.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(State.MQNAME, this, AuditTrigger.UPDATE | AuditTrigger.MOVE);
        plan.registerRule(InternalTransition.MQNAME, this, AuditTrigger.CREATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("b01268a6-e07d-426d-9bcd-f2a692f2c189")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("d3712d73-420c-4d7d-a6ea-37559a5e24b4")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("25a63dd5-aa8e-4dad-a144-2644bb21cb85")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2660
     */
    @objid ("116499f7-141d-4b77-83fb-00a485bc47ff")
    public R2660() {
        this.checkerInstance = new CheckR2660(this);
    }

    @objid ("04ffc580-d351-4ee2-b1f7-bd1b57a7a8b1")
    private static class CheckR2660 extends AbstractControl {
        @objid ("372171bf-06f7-4f87-8ddd-5d6581568cdb")
        public CheckR2660(IRule rule) {
            super(rule);
        }

        @objid ("1f9ace85-46dd-4315-a07a-9eb886fdb39f")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof StateMachine) {
                for (State state : fetchAllStates((StateMachine) element)) {
                    diagnostic.addEntry(checkR2660(state));
                }
            } else if (element instanceof InternalTransition) {
                StateVertex vertex = ((InternalTransition) element).getSource();
                if (vertex instanceof State) {
                    diagnostic.addEntry(checkR2660((State) vertex));
                }
            } else if (element instanceof State) {
                diagnostic.addEntry(checkR2660((State) element));
            } else {
                UmlUi.LOG.warning("R2660: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("8cbecc58-2882-4624-8585-4941d2d035e1")
        private IAuditEntry checkR2660(final State state) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    state,
                    null);
            
            StateMachine sm = getStateMachine(state);
            
            if (sm.getKind().equals(KindOfStateMachine.PROTOCOL)) {
                if (!state.getInternal().isEmpty()) {
                    auditEntry.setSeverity(this.rule.getSeverity());
                    List<Object> linkedObjects = new ArrayList<>();
                    linkedObjects.add(state);
                    auditEntry.setLinkedInfos(linkedObjects);
                }
            }
            return auditEntry;
        }

        /**
         * This methods recursively fetches the StateMachine containing the state.
         * 
         * @param state The state.
         * @return The StateMachine.
         */
        @objid ("ed0cfb1a-0fc6-419b-a0ae-e8c7ffbd49ed")
        private StateMachine getStateMachine(final State state) {
            Region region = state.getParent();
            StateMachine sm = region.getRepresented();
            if (sm != null) {
                return sm;
            } else {
                return getStateMachine(region.getParent());
            }
        }

        @objid ("376329af-9a7f-4448-ae2e-3aa36b65e523")
        private List<State> fetchAllStates(final StateMachine sm) {
            List<State> states = new ArrayList<>();
            
            for (StateVertex vertex : sm.getTop().getSub()) {
                if (vertex instanceof State) {
                    State state = (State) vertex;
                    states.add(state);
                    states.addAll(fetchAllStates(state));
                }
            }
            return states;
        }

        @objid ("3023e74c-e32e-412c-b230-86506cfec7fe")
        private List<State> fetchAllStates(final State state) {
            List<State> states = new ArrayList<>();
            
            for (Region region : state.getOwnedRegion()) {
                for (StateVertex vertex : region.getSub()) {
                    if (vertex instanceof State) {
                        State st = (State) vertex;
                        states.add(st);
                        states.addAll(fetchAllStates(st));
                    }
                }
            }
            return states;
        }

    }

}
