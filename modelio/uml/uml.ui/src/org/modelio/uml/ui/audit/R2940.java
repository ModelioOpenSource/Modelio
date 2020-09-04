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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.modelio.metamodel.uml.behavior.stateMachineModel.Region;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateVertex;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Transition;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * New Rule for checking that all states outgoing from a fork are concurrent.
 */
@objid ("717bbf55-f159-4bb8-b164-3b8e550fff79")
public class R2940 extends AbstractUmlRule {
    @objid ("a0d4ef51-0073-4eec-ae00-287ab469c141")
    private static final String RULEID = "R2940";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("4bda2230-b49f-433a-8868-1e54550288e8")
    private CheckR2940 checkerInstance = null;

    @objid ("beb160ba-eed0-4be6-899d-0b0f62bfdf2d")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(Transition.MQNAME, this, AuditTrigger.CREATE |
                AuditTrigger.MOVE |
                AuditTrigger.UPDATE);
        plan.registerRule(State.MQNAME, this, AuditTrigger.MOVE);
    }

    @objid ("22fd5573-9b57-4780-8c9d-9dcc5cbf5848")
    @Override
    public String getRuleId() {
        return R2940.RULEID;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("eb6d7e63-cf2b-4fe9-9e11-f0bbd5a9fef7")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("6cfb1bc4-5e98-4874-bf4e-d4dc8f64a8e6")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("5486b69e-22ed-4eba-b7ff-883671c734c0")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2940
     */
    @objid ("f8aab443-013c-482c-bd07-1453bbd9f773")
    public R2940() {
        this.checkerInstance = new CheckR2940(this);
    }

    @objid ("6c2e0582-ee7d-4781-b03c-58a05907a42a")
    private static class CheckR2940 extends AbstractControl {
        @objid ("f024ccb6-7e3a-4cd7-9f51-46fcbfa649bb")
        public CheckR2940(IRule rule) {
            super(rule);
        }

        @objid ("c634cc02-54a3-4493-9447-c11b44e21859")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof Transition) {
            
                // A transition was either created or moved
                // We need to check if its source is a fork state
            
                StateVertex target = ((Transition) element).getTarget();
                if (target instanceof JoinPseudoState) {
                    diagnostic.addEntry(checkR2940((JoinPseudoState) target));
                }
            
            } else if (element instanceof State) {
            
                // A state was moved
                // We need to check if the target of one of its outgoing transitions if a join state
            
                for (Transition transition : ((State) element).getOutGoing()) {
                    StateVertex target = transition.getTarget();
                    if (target instanceof JoinPseudoState) {
                        diagnostic.addEntry(checkR2940((JoinPseudoState) target));
                    }
            
                }
            } else {
                UmlUi.LOG.warning("R2940: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        /**
         * This rules check that all origin state of a JoinPseudoState are all contained in the same State and are in different regions.
         * @param join The JoinPseudoState to check.
         * @return The audit entry.
         */
        @objid ("2c1d74ee-dc27-40d1-a966-eff36ed0ccc3")
        private IAuditEntry checkR2940(JoinPseudoState join) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    join,
                    null);
            
            // Fetching all origin states
            List<State> sourceStates = fetchOriginStates(join);
            
            // Fetching all ancestors for each origin state
            Map<State, List<State>> ancestorsMap = fetchAncestors(sourceStates);
            
            // Checking there is at least one common ancestor (intersection of ancestors)
            List<State> ancestorIntersection = fetchCommonAncestors(ancestorsMap);
            
            if (ancestorIntersection.isEmpty()) {
            
                // There was no common ancestors between two states
                // Rule failed
            
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(join);
                auditEntry.setLinkedInfos(linkedObjects);
            
                return auditEntry;
            }
            
            State commonAncestor = ancestorIntersection.get(0);
            
            List<Region> targetedRegions = new ArrayList<>();
            
            // Checking that all targeted states are in different regions
            for (State state : sourceStates) {
            
                Region region = state.getParent();
                State parent = region.getParent();
            
                while (!parent.equals(commonAncestor)) {
                    region = parent.getParent();
                    parent = region.getParent();
                }
            
                if (targetedRegions.contains(region)) {
            
                    // Two of the targeted states are in the same region
                    // Rule failed
            
                    auditEntry.setSeverity(this.rule.getSeverity());
                    List<Object> linkedObjects = new ArrayList<>();
                    linkedObjects.add(join);
                    auditEntry.setLinkedInfos(linkedObjects);
            
                } else {
                    targetedRegions.add(region);
                }
            }
            return auditEntry;
        }

        /**
         * Fetches all the origin states of a JoinPseudoState
         * @param join The JoinPseudoState.
         * @return The list of origin State.
         */
        @objid ("9ec142be-f5c3-43e5-a0aa-2eb1a05c8f20")
        private List<State> fetchOriginStates(final JoinPseudoState join) {
            List<State> sourceStates = new ArrayList<>();
            
            for (Transition transition : join.getIncoming()) {
                StateVertex vertex = transition.getSource();
                if (vertex instanceof State) {
                    sourceStates.add((State) vertex);
                }
            }
            return sourceStates;
        }

        /**
         * Fetches all the ancestors for each state.
         * @param sourceStates The states from which to get the ancestors.
         * @return A map containing all the ancestors of each state.
         */
        @objid ("691e1918-1ea8-41bf-a16a-379f6e047d1d")
        private Map<State, List<State>> fetchAncestors(final List<State> sourceStates) {
            Map<State, List<State>> ancestorsMap = new HashMap<>();
            
            for (State state : sourceStates) {
                List<State> ancestors = new ArrayList<>();
                Region region = state.getParent();
                while (region != null) {
                    State parentState = region.getParent();
                    if (parentState != null) {
                        ancestors.add(parentState);
                        region = parentState.getParent();
                    } else {
                        region = null;
                    }
                }
                ancestorsMap.put(state, ancestors);
            }
            return ancestorsMap;
        }

        /**
         * Tries to fetch the common ancestors to all the states. If none exists, it returns an empty list.
         * @param ancestorsMap The map of the state and their ancestors.
         * @return The list of common ancestors.
         */
        @objid ("1b55ed71-5153-4063-bdde-432b760f8570")
        private List<State> fetchCommonAncestors(final Map<State, List<State>> ancestorsMap) {
            List<State> ancestorIntersection = new ArrayList<>();
            
            for (List<State> states : ancestorsMap.values()) {
                if (ancestorIntersection.isEmpty()) {
                    ancestorIntersection.addAll(states);
                } else {
                    List<State> compatibleAncestors = new ArrayList<>();
            
                    for (State ancestor : states) {
                        if (ancestorIntersection.contains(ancestor)) {
                            compatibleAncestors.add(ancestor);
                        }
                    }
                    if (compatibleAncestors.isEmpty()) {
                        return compatibleAncestors;
                    } else {
                        ancestorIntersection = compatibleAncestors;
                    }
                }
            }
            return ancestorIntersection;
        }

    }

}
