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
import org.modelio.metamodel.uml.behavior.activityModel.ActivityEdge;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityNode;
import org.modelio.metamodel.uml.behavior.activityModel.ControlNode;
import org.modelio.metamodel.uml.behavior.activityModel.InstanceNode;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectFlow;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: ActivityModelChecker checkR61052 warning
 */
@objid ("ad0d878c-d8ad-4d7e-be88-b96d5e25bc14")
public class R1310 extends AbstractUmlRule {
    @objid ("daba88ec-9080-4f05-8d88-bdb52247678c")
    private static final String RULEID = "R1310";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("c3b72160-9b15-4e59-bc12-b2ce4822ab1c")
    private CheckR1310 checkerInstance = null;

    @objid ("1b5b952f-76f1-432f-9633-b45faacd2164")
    @Override
    public String getRuleId() {
        return R1310.RULEID;
    }

    @objid ("3a134281-401a-4700-aea0-0ac2c7553b03")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        // In Modelio metamodel, InstanceNodes represent ObjectNodes
        plan.registerRule(InstanceNode.MQNAME, this, AuditTrigger.UPDATE);
        
        plan.registerRule(ControlNode.MQNAME, this, AuditTrigger.UPDATE);
        
        plan.registerRule(ObjectFlow.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.MOVE | AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("d30596ca-a797-4db9-86f2-87c018ef761a")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("a11ece1d-f7de-421c-bce9-3cacc9332eb7")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("5da845e2-64e7-45f7-af7c-ab03c8955b83")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1310
     */
    @objid ("2da9181f-e509-44a6-8fcf-fe59ae0b4dcf")
    public R1310() {
        this.checkerInstance = new CheckR1310(this);
    }

    @objid ("478889ea-42fc-4995-93c8-a14310865055")
    private static class CheckR1310 extends AbstractControl {
        @objid ("df115f1a-a83e-4280-8438-568dfaaadc7e")
        public CheckR1310(IRule rule) {
            super(rule);
        }

        @objid ("fa5d7150-333e-4620-b01f-724051341c4d")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof InstanceNode) {
                diagnostic.addEntries(checkR1310((InstanceNode) element));
            } else if (element instanceof ObjectFlow) {
                diagnostic.addEntries(checkR1310((ObjectFlow) element));
            } else {
                UmlUi.LOG.warning("R1310: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        /**
         * Checks if the given edge respect the rule: All downstream nodes must have an upper bound lower that the flow's weight.
         * 
         * @param edge The edge to check.
         * @return The audit result.
         */
        @objid ("c3d7583d-37f7-4a7c-a14a-0929515b8bb6")
        private IAuditEntry checkR1310(ActivityEdge edge) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(), AuditSeverity.AuditSuccess, edge, null);
            
            // UML Constraint: An edge with constant weight may not target an object node, or lead to an object node downstream with no intervening
            // actions, that has an upper bound less than the weight.
            
            // We considered that the path to check should only be made of ObjectNodes and ObjectFlows, any ControlNode or ConftrolFlow break the flow.
            
            // If the edge has no weight, the rule does not apply.
            if (edge.getWeight() == null) {
                return auditEntry;
            }
            
            // Recursively check all downstream nodes starting with the edge's
            // target node.
            if (!checkAllUpperBounds(Integer.parseInt(edge.getWeight()), edge.getTarget(), new ArrayList<ActivityNode>())) {
            
                // Rule failed
            
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(edge);
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

        /**
         * If a node is modified, its upper bound is potentially modified, so we need to check all the upstream flows' weight, either object or control, targeting it or leading to it. A node can also be updated if a flow is moved or deleted, potentially creating or removing paths between object nodes, so we need to check both upstream and downstream path to updated concerned flows.
         * 
         * @param node The object node to start from.
         * @return A list of audit entries for each concerned flow.
         */
        @objid ("0c75d089-c282-4d9c-8dfb-1783df171371")
        private List<IAuditEntry> checkR1310(ActivityNode node) {
            List<IAuditEntry> auditEntries = new ArrayList<>();
            
            auditEntries.addAll(checkUpstreamPath(node));
            auditEntries.addAll(checkDownstreamPath(node));
            return auditEntries;
        }

        /**
         * If an object flow is created or moved, it potentially connects object nodes together, and therefore creates potentially new paths (in both direction) to check the rule on. If the object flow is updated, we also need to check the rule on it.
         * 
         * @param objectFlow The edge to check.
         * @return A list of audit entries for each impacted edges.
         */
        @objid ("98eb9379-ce64-4536-9344-0c9e488b3666")
        private List<IAuditEntry> checkR1310(ObjectFlow objectFlow) {
            List<IAuditEntry> auditEntries = new ArrayList<>();
            
            // Checking the upstream paths
            ActivityNode sourceNode = objectFlow.getSource();
            if (sourceNode instanceof InstanceNode && ((InstanceNode) sourceNode).isIsControlType()) {
                auditEntries.addAll(checkUpstreamPath(sourceNode));
            }
            
            // Checking the downstream paths.
            ActivityNode targetNode = objectFlow.getTarget();
            if (targetNode instanceof InstanceNode && ((InstanceNode) targetNode).isIsControlType()) {
                auditEntries.addAll(checkDownstreamPath(targetNode));
            }
            
            // Checking the edge itself
            auditEntries.add(checkR1310((ActivityEdge) objectFlow));
            return auditEntries;
        }

        /**
         * Check all upper bounds of the object nodes, that are not control types, downstream of the given node.
         * 
         * @param weight The weight to compare to
         * @param target The starting node
         * @return True if all the nodes have an upper bound equal or inferior to the weight, false otherwise.
         */
        @objid ("c7c0bc2a-6fe0-4e11-b0da-69d7e915092f")
        private boolean checkAllUpperBounds(int weight, ActivityNode target, List<ActivityNode> visitedNodes) {
            if (visitedNodes.contains(target)) {
                return true;
            }
            
            if (target instanceof InstanceNode && !((InstanceNode) target).isIsControlType()) {
            
                visitedNodes.add(target);
            
                if (Integer.parseInt(((InstanceNode) target).getUpperBound()) < weight) {
                    return false;
                } else {
                    for (ActivityEdge edge : target.getOutgoing(ObjectFlow.class)) {
                        if (!checkAllUpperBounds(weight, edge.getTarget(), visitedNodes)) {
                            return false;
                        }
                    }
                }
            }
            return true;
        }

        /**
         * Finds all the flows connecting the given ObjectNode to another ObjectNode, except for ObjectNodes that are controls.
         * @param objectFlows The list of found flows
         * 
         * @param node The Object node to search from
         * @param visitedFlows The list of visited ObjectFlows
         */
        @objid ("719593d4-5857-4e96-84a5-158de5a95a3a")
        private void findSourceFlows(ActivityNode node, List<ActivityEdge> flows, List<ActivityEdge> visitedFlows) {
            // Check in all incoming directions from the current node
            for (ActivityEdge incomingFlow : node.getIncoming(ObjectFlow.class)) {
            
                if (visitedFlows.contains(incomingFlow)) {
                    continue;
                }
            
                visitedFlows.add(incomingFlow);
                ActivityNode sourceNode = incomingFlow.getSource();
            
                // If the node is an object and is not a control type, we add
                // the flow to the found flows
                if (sourceNode instanceof InstanceNode && !((InstanceNode) sourceNode).isIsControlType()) {
                    flows.add(incomingFlow);
                    findSourceFlows(sourceNode, flows, visitedFlows);
                }
            }
        }

        /**
         * Finds all the upstream flows targeting the node until we reach either an action or an object node.
         * @param objectFlows The list of found flows
         * 
         * @param node The Object node to search from
         * @param visitedFlows The list of visited ObjectFlows
         */
        @objid ("ae713ed9-23d2-490e-85fd-aaa9793cef53")
        private void findTargetFlows(ActivityNode node, List<ActivityEdge> flows, List<ActivityEdge> visitedFlows) {
            // Check in all outgoing directions from the current node
            for (ActivityEdge outgoingFlow : node.getOutgoing(ObjectFlow.class)) {
            
                if (visitedFlows.contains(outgoingFlow)) {
                    continue;
                }
            
                visitedFlows.add(outgoingFlow);
                ActivityNode targetNode = outgoingFlow.getTarget();
            
                // If the node is an object and is not a control type, we add
                // the flow to the found flows.
                if (targetNode instanceof InstanceNode && !((InstanceNode) targetNode).isIsControlType()) {
                    flows.add(outgoingFlow);
                    findTargetFlows(targetNode, flows, visitedFlows);
                }
            }
        }

        /**
         * Check all the flows upstream of a given node.
         * 
         * @param node The node to start from.
         * @return A list of audit entries for each flow.
         */
        @objid ("f2fc8589-d1b1-4c1a-b40c-e981aecf43b3")
        private List<IAuditEntry> checkUpstreamPath(ActivityNode node) {
            List<IAuditEntry> auditEntries = new ArrayList<>();
            
            List<ActivityEdge> sourceFlows = new ArrayList<>();
            findSourceFlows(node, sourceFlows, new ArrayList<ActivityEdge>());
            
            for (ActivityEdge sourceFlow : sourceFlows) {
                auditEntries.add(checkR1310(sourceFlow));
            }
            return auditEntries;
        }

        /**
         * Check all the flows downstream of a given node.
         * 
         * @param node The node to start from.
         * @return A list of audit entries for each flow.
         */
        @objid ("ab5ac221-069b-4cd1-bcaf-c9c724c73764")
        private List<IAuditEntry> checkDownstreamPath(ActivityNode node) {
            List<IAuditEntry> auditEntries = new ArrayList<>();
            
            List<ActivityEdge> targetFlows = new ArrayList<>();
            findTargetFlows(node, targetFlows, new ArrayList<ActivityEdge>());
            
            for (ActivityEdge targetFlow : targetFlows) {
                auditEntries.add(checkR1310(targetFlow));
            }
            return auditEntries;
        }

    }

}
