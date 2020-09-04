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
import org.modelio.metamodel.uml.behavior.stateMachineModel.ForkPseudoState;
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
@objid ("78d36a6c-451e-4e71-aad2-220963c4e8d1")
public class R2950 extends AbstractUmlRule {
    @objid ("f17a30cf-f3bd-4846-bc05-6aa5d1efd40b")
    private static final String RULEID = "R2950";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("9355ebd5-ec2d-402c-a1cd-1a74ab6a940b")
    private CheckR2950 checkerInstance = null;

    @objid ("10ec3749-319f-496c-82a0-0135d0282f1b")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(Transition.MQNAME, this, AuditTrigger.CREATE |
                AuditTrigger.MOVE |
                AuditTrigger.UPDATE);
        plan.registerRule(State.MQNAME, this, AuditTrigger.MOVE);
    }

    @objid ("95e78ff5-e5e8-4e0d-950b-68c0f09263df")
    @Override
    public String getRuleId() {
        return R2950.RULEID;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("355b1d92-689c-4390-ae55-5a32fe2ac621")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("baf4c122-ad63-4d61-a323-19ae1da524a6")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("ccdfaaf5-9ea8-4f15-8675-a39bd1690eeb")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R2950
     */
    @objid ("6430d1af-1cc6-45d8-b1e4-58ce070e0c3b")
    public R2950() {
        this.checkerInstance = new CheckR2950(this);
    }

    @objid ("9c244664-1575-4b58-be8e-17b9aa2ca030")
    private static class CheckR2950 extends AbstractControl {
        @objid ("7eca7052-ce79-4419-8789-61eaf61e5950")
        public CheckR2950(IRule rule) {
            super(rule);
        }

        @objid ("9c6ec9e3-7b42-40d2-8142-a1b89554c1e5")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof Transition) {
            
                // A transition was either created or moved
                // We need to check if its source is a fork state
            
                StateVertex source = ((Transition) element).getSource();
                if (source instanceof ForkPseudoState) {
                    diagnostic.addEntry(checkR2950((ForkPseudoState) source));
                }
            
            } else if (element instanceof State) {
            
                // A state was moved
                // We need to check if the source of one of its incoming transitions if a fork state
            
                for (Transition transition : ((State) element).getIncoming()) {
                    StateVertex source = transition.getSource();
                    if (source instanceof ForkPseudoState) {
                        diagnostic.addEntry(checkR2950((ForkPseudoState) source));
                    }
            
                }
            } else {
                UmlUi.LOG.warning("R2950: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        /**
         * This rules check that all targeted state of a ForkPseudoState are all contained in the same State and are in different regions.
         * @param fork The ForkPseudoState to check.
         * @return The audit entry.
         */
        @objid ("14878e80-338c-446c-b638-ab0d1140e4c1")
        private IAuditEntry checkR2950(ForkPseudoState fork) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    fork,
                    null);
            
            // Fetching all targeted states
            List<State> targetedStates = fetchTargetStates(fork);
            
            // Fetching all ancestors for each target state
            Map<State, List<State>> ancestorsMap = fetchAncestors(targetedStates);
            
            // Checking there is at least one common ancestor (intersection of ancestors)
            List<State> ancestorIntersection = fetchCommonAncestors(ancestorsMap);
            
            if (ancestorIntersection.isEmpty()) {
            
                // There was no common ancestors between two states
                // Rule failed
            
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(fork);
                auditEntry.setLinkedInfos(linkedObjects);
            
                return auditEntry;
            }
            
            State commonAncestor = ancestorIntersection.get(0);
            
            List<Region> targetedRegions = new ArrayList<>();
            
            // Checking that all targeted states are in different regions
            for (State state : targetedStates) {
            
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
                    linkedObjects.add(fork);
                    auditEntry.setLinkedInfos(linkedObjects);
            
                } else {
                    targetedRegions.add(region);
                }
            }
            return auditEntry;
        }

        /**
         * Fetches all the target states of a ForkPseudoState
         * @param fork The ForkPseudoState.
         * @return The list of target State.
         */
        @objid ("0e747cf1-7b19-4d1b-85b4-851f165d7ee5")
        private List<State> fetchTargetStates(final ForkPseudoState fork) {
            List<State> targetedStates = new ArrayList<>();
            
            for (Transition transition : fork.getOutGoing()) {
                StateVertex vertex = transition.getTarget();
                if (vertex instanceof State) {
                    targetedStates.add((State) vertex);
                }
            }
            return targetedStates;
        }

        /**
         * Fetches all the ancestors for each state.
         * @param sourceStates The states from which to get the ancestors.
         * @return A map containing all the ancestors of each state.
         */
        @objid ("57687cdb-dd09-441b-bd48-68a895f3081a")
        private Map<State, List<State>> fetchAncestors(final List<State> targetedStates) {
            Map<State, List<State>> ancestorsMap = new HashMap<>();
            
            for (State state : targetedStates) {
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
        @objid ("da514789-3620-437a-b31c-ad5fc743df8c")
        private List<State> fetchCommonAncestors(final Map<State, List<State>> ancestorsMap) {
            List<State> ancestorIntersection = new ArrayList<>();
            
            for (State state : ancestorsMap.keySet()) {
                if (ancestorIntersection.isEmpty()) {
                    ancestorIntersection.addAll(ancestorsMap.get(state));
                } else {
                    List<State> compatibleAncestors = new ArrayList<>();
            
                    for (State ancestor : ancestorsMap.get(state)) {
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
