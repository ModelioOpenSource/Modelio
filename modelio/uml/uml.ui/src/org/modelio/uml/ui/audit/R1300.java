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
import org.modelio.metamodel.uml.behavior.activityModel.ActivityAction;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityEdge;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityNode;
import org.modelio.metamodel.uml.behavior.activityModel.InstanceNode;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectFlow;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectNode;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: ActivityModelChecker checkR61051 warning
 */
@objid ("c0d37322-ce47-4a0c-b846-0327eb1e43b4")
public class R1300 extends AbstractUmlRule {
    @objid ("aa7b0ded-7f0c-4aa4-b383-9345f538f55c")
    private static final String RULEID = "R1300";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("259f2cf6-d248-4798-a28a-6f5e89ad1ad5")
    private CheckR1300 checkerInstance = null;

    @objid ("6a3ed13e-07ad-4ee3-9055-bb6809e77235")
    @Override
    public String getRuleId() {
        return R1300.RULEID;
    }

    @objid ("df78bbe5-a2c0-49e0-9d1b-c6bf89b971bf")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(InstanceNode.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(ObjectFlow.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.MOVE | AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("383377c3-8535-44c6-9fea-d5e66bd19f6b")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("7f976b96-eb8a-4dc2-ba4a-6cd2a2d6222e")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("f488ef63-79ae-419b-bf52-6584f5153926")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1300
     */
    @objid ("aa29be11-dadf-4ac5-a4a2-d2661812a6ca")
    public R1300() {
        this.checkerInstance = new CheckR1300(this);
    }

    @objid ("c7808566-c4a6-4d3d-b61a-050459fcc51e")
    private static class CheckR1300 extends AbstractControl {
        @objid ("04de4f90-932a-4a0c-8f78-55c17981604d")
        public CheckR1300(IRule rule) {
            super(rule);
        }

        @objid ("8c2844b1-417c-47d4-85a5-f8b6de540187")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof InstanceNode) {
                diagnostic.addEntries(checkR1300((InstanceNode) element));
            } else if (element instanceof ObjectFlow) {
                diagnostic.addEntry(checkR1300((ObjectFlow) element));
            } else {
                UmlUi.LOG.warning("R1300: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("76482f1c-bb9a-41b8-86cf-1347efc1fe4c")
        private IAuditEntry checkR1300(ObjectFlow objectFlow) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(), AuditSeverity.AuditSuccess, objectFlow, null);
            
            ActivityNode sourceNode = objectFlow.getSource();
            ActivityNode targetNode = objectFlow.getTarget();
            
            // If one of the end of the ObjectFlow is not an ObjectNode, rule
            // does not apply.
            if (!(sourceNode instanceof ObjectNode) || !(targetNode instanceof ObjectNode)) {
                return auditEntry;
            }
            
            // Look for the first ObjecNodes, which is not a control type, on
            // each path in the source and target directions, and stores its
            // Type in a list.
            
            String sourceBound = findSourceUpperBound((ObjectNode) sourceNode, "", new ArrayList<ObjectNode>());
            String targetBound = findTargetUpperBound((ObjectNode) targetNode, "", new ArrayList<ObjectNode>());
            
            // If one of these returned null, upper bounds are inconsistent, rule failed
            if (sourceBound != null && targetBound != null) {
            
                // If one of the result is the empty string, it means an OjectNode was not found in one of the direction, in which case the rule does not apply.
                // If the two returned value are the same, the rule is a success.
                if (sourceBound.equals("") || targetBound.equals("") || sourceBound.equals(targetBound)) {
                    return auditEntry;
                }
            }
            
            // Rule failed
            
            auditEntry.setSeverity(this.rule.getSeverity());
            List<Object> linkedObjects = new ArrayList<>();
            linkedObjects.add(objectFlow);
            auditEntry.setLinkedInfos(linkedObjects);
            return auditEntry;
        }

        /**
         * If an object node is modified, its upper bound is potentially modified, so we need to check all the upstream and downstream flows connecting to ObjectNodes. An object node can also be updated if a flow is moved or deleted, potentially creating or removing paths between object nodes, so we need to check both upstream and downstream path to update concerned flows.
         * 
         * @param objectNode The object node that was updated.
         * @return A list of audit entries of all concerned flows.
         */
        @objid ("9e532853-33c7-4917-bb19-885ab2df1aee")
        private List<IAuditEntry> checkR1300(InstanceNode objectNode) {
            List<IAuditEntry> auditEntries = new ArrayList<>();
            
            // The ObjectNode is a control, rule does not apply.
            if (objectNode.isIsControlType()) {
                return auditEntries;
            }
            
            // Checking upstream flows.
            List<ObjectFlow> sourceObjectFlows = new ArrayList<>();
            findSourceFlows(objectNode, sourceObjectFlows, new ArrayList<ObjectFlow>());
            for (ObjectFlow sourceObjectFlow : sourceObjectFlows) {
                auditEntries.add(checkR1300(sourceObjectFlow));
            }
            
            // Checking downstream flows.
            List<ObjectFlow> targetObjectFlows = new ArrayList<>();
            findTargetFlows(objectNode, targetObjectFlows, new ArrayList<ObjectFlow>());
            for (ObjectFlow targetObjectFlow : targetObjectFlows) {
                auditEntries.add(checkR1300(targetObjectFlow));
            }
            return auditEntries;
        }

        /**
         * Finds all the ObjectFlows connecting the given ObjectNode to another ObjectNode, except for ObjectNodes that are controls.
         * 
         * @param node The Object node to search from
         * @param objectFlows The list of found ObjectFlow
         * @param visitedFlows The list of visited ObjectFlows
         */
        @objid ("0e21b8e9-96cb-46fa-aac5-b0b6268cd572")
        private void findSourceFlows(ActivityNode node, List<ObjectFlow> objectFlows, List<ObjectFlow> visitedFlows) {
            // Check in all incoming directions from the current node
            for (ObjectFlow incomingObjectFlow : node.getIncoming(ObjectFlow.class)) {
            
                if (visitedFlows.contains(incomingObjectFlow)) {
                    continue;
                }
            
                visitedFlows.add(incomingObjectFlow);
                ActivityNode sourceNode = incomingObjectFlow.getSource();
            
                // If the node is an action, there is no need to go further.
                if (sourceNode instanceof ActivityAction) {
                    return;
                } else if (sourceNode instanceof ObjectNode && !((ObjectNode) sourceNode).isIsControlType()) {
                    objectFlows.add(incomingObjectFlow);
                } else {
                    objectFlows.add(incomingObjectFlow);
                    findSourceFlows(sourceNode, objectFlows, visitedFlows);
                }
            }
        }

        /**
         * Finds all the ObjectFlows connecting the given ObjectNode to another ObjectNode, except for ObjectNodes that are controls.
         * 
         * @param node The Object node to search from
         * @param objectFlows The list of found ObjectFlow
         * @param visitedFlows The list of visited ObjectFlows
         */
        @objid ("b2e5941d-b65c-4104-97ff-9798e79c55db")
        private void findTargetFlows(ActivityNode node, List<ObjectFlow> objectFlows, List<ObjectFlow> visitedFlows) {
            // Check in all incoming directions from the current node
            for (ObjectFlow outgoingObjectFlow : node.getOutgoing(ObjectFlow.class)) {
            
                if (visitedFlows.contains(outgoingObjectFlow)) {
                    continue;
                }
            
                visitedFlows.add(outgoingObjectFlow);
                ActivityNode targetNode = outgoingObjectFlow.getTarget();
            
                // If the node is an action, there is no need to go further.
                if (targetNode instanceof ActivityAction) {
                    return;
                } else if (targetNode instanceof ObjectNode && !((ObjectNode) targetNode).isIsControlType()) {
                    objectFlows.add(outgoingObjectFlow);
                } else {
                    objectFlows.add(outgoingObjectFlow);
                    findTargetFlows(targetNode, objectFlows, visitedFlows);
                }
            }
        }

        @objid ("2f0ea0cb-e4c6-4a1c-98e0-d8b3f0c903a7")
        private String findTargetUpperBound(ObjectNode objectNode, String upperBound, ArrayList<ObjectNode> visitedNodes) {
            if (visitedNodes.contains(objectNode)) {
                return upperBound;
            }
            
            visitedNodes.add(objectNode);
            
            // An ObjectNode that is not a ControlType was found
            if (!objectNode.isIsControlType()) {
                // If upperBound is empty, we initialise it to the found value.
                if (upperBound.equals("")) {
                    return objectNode.getUpperBound();
                } else if (!upperBound.equals(objectNode.getUpperBound())) {
                    return null;
                }
            } else {
                // The ObjectNode is a ControlType, we look for ObjectNodes in its downstream paths
                for (ActivityEdge objectFlow : objectNode.getOutgoing(ObjectFlow.class)) {
                    ActivityNode targetNode = objectFlow.getTarget();
                    if (targetNode instanceof ObjectNode && !visitedNodes.contains(targetNode)) {
                        return findTargetUpperBound((ObjectNode) targetNode, upperBound, visitedNodes);
                    }
                }
            }
            return upperBound;
        }

        @objid ("3503e24b-e343-4baf-978b-f5931aea5806")
        private String findSourceUpperBound(ObjectNode objectNode, String upperBound, ArrayList<ObjectNode> visitedNodes) {
            if (visitedNodes.contains(objectNode)) {
                return upperBound;
            }
            
            visitedNodes.add(objectNode);
            
            // An ObjectNode that is not a ControlType was found
            if (!objectNode.isIsControlType()) {
                // If upperBound is empty, we initialise it to the found value.
                if (upperBound.equals("")) {
                    return objectNode.getUpperBound();
                } else if (!upperBound.equals(objectNode.getUpperBound())) {
                    return null;
                }
            } else {
                // The ObjectNode is a ControlType, we look for ObjectNodes in its upstream paths
                for (ActivityEdge objectFlow : objectNode.getIncoming(ObjectFlow.class)) {
                    ActivityNode sourceNode = objectFlow.getSource();
                    if (sourceNode instanceof ObjectNode && !visitedNodes.contains(sourceNode)) {
                        return findSourceUpperBound((ObjectNode) sourceNode, upperBound, visitedNodes);
                    }
                }
            }
            return upperBound;
        }

    }

}
