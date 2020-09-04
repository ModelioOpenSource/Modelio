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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.Generalization;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: ActivityModelChecker checkR61050 warning
 */
@objid ("57c0e82f-8d92-4706-9906-d53f9d67200b")
public class R1290 extends AbstractUmlRule {
    @objid ("706fc4bf-8848-474e-9048-871f735de9cf")
    private static final String RULEID = "R1290";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(Element)
     * @see AbstractRule#getUpdateControl(Element)
     * @see AbstractRule#getMoveControl(ElementMovedEvent)
     */
    @objid ("4d3b3064-94cb-4681-853f-b8a5d767535d")
    private CheckR1290 checkerInstance = null;

    @objid ("d8feb7e5-32fe-4e7a-ab59-ed471539c1a0")
    @Override
    public String getRuleId() {
        return R1290.RULEID;
    }

    @objid ("dccd31c9-d7a5-48cc-aaad-6b5ab5056dc0")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(InstanceNode.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(ObjectFlow.MQNAME, this, AuditTrigger.CREATE | AuditTrigger.MOVE | AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("e37d696d-1f93-45a6-be19-7e43285e2101")
    @Override
    public IControl getCreationControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("c8cabb70-a810-4a45-a446-85f6e15de043")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return this.checkerInstance;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("e1a71a45-9858-46c8-a515-f403e767e4b6")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1290
     */
    @objid ("0456e323-dd1a-41d1-8ebd-64573fad1b47")
    public R1290() {
        this.checkerInstance = new CheckR1290(this);
    }

    @objid ("cb695c59-9c2b-427d-9ebe-986b6e40fdb4")
    private static class CheckR1290 extends AbstractControl {
        @objid ("7ddc2e71-d3f1-4cce-8eab-0ea17d613b8e")
        public CheckR1290(IRule rule) {
            super(rule);
        }

        @objid ("8c02aabe-c765-4501-bd8d-1edc6b88111e")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof InstanceNode) {
                diagnostic.addEntries(checkR1290((InstanceNode) element));
            } else if (element instanceof ObjectFlow) {
                diagnostic.addEntry(checkR1290((ObjectFlow) element));
            } else {
                UmlUi.LOG.warning("R1290: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("c11561a1-d354-4f95-9726-c5c77b10f449")
        private IAuditEntry checkR1290(ObjectFlow objectFlow) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(), AuditSeverity.AuditSuccess, objectFlow, null);
            
            ActivityNode sourceNode = objectFlow.getSource();
            ActivityNode targetNode = objectFlow.getTarget();
            
            // If one of the end of the ObjectFlow is not an ObjectNode, rule
            // does not apply.
            if (!(sourceNode instanceof InstanceNode) || !(targetNode instanceof InstanceNode)) {
                return auditEntry;
            }
            
            List<GeneralClass> sourceClasses = new ArrayList<>();
            List<GeneralClass> targetClasses = new ArrayList<>();
            
            // Look for the first ObjecNodes, which is not a ControlType, on
            // each path in the source and target directions, and stores its
            // Type in a list.
            findSourceClasses(sourceNode, sourceClasses, new ArrayList<ActivityNode>());
            findTargetClasses(targetNode, targetClasses, new ArrayList<ActivityNode>());
            
            // No ObjectNode, which is not a ControlType, was found in one of
            // the direction.
            if (sourceClasses.isEmpty() || targetClasses.isEmpty()) {
                return auditEntry;
            }
            
            try {
            
                // Checking the types of the found Classes.
                KindOfType result = computeKindOfType(sourceClasses, targetClasses);
            
                // Types are NULL and NOTNULL, the rule failed.
                if (result == KindOfType.MIX) {
                    throw new Exception();
                } else if (result == KindOfType.NULL) {
                    return auditEntry;
                }
            
                List<GeneralClass> sourceSuperClasses = findSuperTypesIntersection(sourceClasses);
            
                // No intersection was found in the types of the source nodes.
                if (sourceSuperClasses.isEmpty()) {
                    throw new Exception();
                }
            
                // Check if all target nodes are types or super types of the
                // types of the source nodes.
                for (GeneralClass targetClass : targetClasses) {
                    if (!sourceSuperClasses.contains(targetClass)) {
                        throw new Exception();
                    }
                }
            } catch (@SuppressWarnings("unused") Exception e) {
                // Rule failed
            
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(objectFlow);
                auditEntry.setLinkedInfos(linkedObjects);
            }
            return auditEntry;
        }

        @objid ("3ca5c317-7e29-4217-bdb6-c85d24cd17f1")
        private List<IAuditEntry> checkR1290(InstanceNode objectNode) {
            List<IAuditEntry> auditEntries = new ArrayList<>();
            
            // The ObjectNode is a control, rule does not apply.
            if (objectNode.isIsControlType()) {
                return auditEntries;
            } else {
            
                List<ObjectFlow> sourceObjectFlows = new ArrayList<>();
                List<ObjectFlow> targetObjectFlows = new ArrayList<>();
            
                // Fetches all the object flows on the path between our object
                // node and other object nodes.
                findSourceFlows(objectNode, sourceObjectFlows, new ArrayList<ObjectFlow>());
                findTargetFlows(objectNode, targetObjectFlows, new ArrayList<ObjectFlow>());
            
                // Check the rule on all the found flows.
                for (ObjectFlow sourceObjectFlow : sourceObjectFlows) {
                    auditEntries.add(checkR1290(sourceObjectFlow));
                }
                for (ObjectFlow targetObjectFlow : targetObjectFlows) {
                    auditEntries.add(checkR1290(targetObjectFlow));
                }
            }
            return auditEntries;
        }

        /**
         * Find if there are types that are super types of all the given classes.
         * 
         * @param sourceClasses The source Classes.
         * @return The list of the super Classes or an empty list is no such Classes are found.
         */
        @objid ("49f4bb31-4277-49fd-9dc3-84e982b08744")
        private List<GeneralClass> findSuperTypesIntersection(List<GeneralClass> sourceClasses) {
            List<GeneralClass> intersection = new ArrayList<>();
            
            for (GeneralClass genClass : sourceClasses) {
            
                Set<GeneralClass> srcTypes = new HashSet<>();
                getAllSuperTypes(genClass, srcTypes);
            
                // If the intersection list is empty, we add all the super types
                // of the first node.
                if (intersection.isEmpty()) {
                    intersection.addAll(srcTypes);
                } else {
            
                    List<GeneralClass> tmpIntersection = new ArrayList<>();
            
                    for (GeneralClass srcGenClass : srcTypes) {
                        if (intersection.contains(srcGenClass)) {
                            tmpIntersection.add(srcGenClass);
                        }
                    }
            
                    intersection = tmpIntersection;
            
                    // No intersection was found between two types
                    if (intersection.isEmpty()) {
                        return intersection;
                    }
                }
            }
            return intersection;
        }

        /**
         * Check the type of source and target classes and return it.
         * 
         * @param sourceClasses The source Classes
         * @param targetClasses The target Classes
         * @return The KinfOfType of source and target Classes.
         */
        @objid ("21403ed8-3450-498b-8990-8fc05e88824a")
        private KindOfType computeKindOfType(List<GeneralClass> sourceClasses, List<GeneralClass> targetClasses) {
            // Checking both source and target nodes in the same pass.
            List<GeneralClass> allNodes = new ArrayList<>();
            allNodes.addAll(sourceClasses);
            allNodes.addAll(targetClasses);
            
            KindOfType kindOfType = null;
            
            for (GeneralClass genClass : allNodes) {
                if (genClass == null) {
                    if (kindOfType == null) {
                        kindOfType = KindOfType.NULL;
                    } else if (kindOfType == KindOfType.NOTNULL) {
                        return KindOfType.MIX;
                    }
                } else {
                    if (kindOfType == null) {
                        kindOfType = KindOfType.NOTNULL;
                    } else if (kindOfType == KindOfType.NULL) {
                        return KindOfType.MIX;
                    }
                }
            }
            return kindOfType;
        }

        /**
         * Gets All the super types of a type.
         * 
         * @param type The class to start from.
         * @param superTypes The list of found super types.
         */
        @objid ("c7855407-5bbf-47ff-8f72-d57b3bb58c0e")
        private void getAllSuperTypes(GeneralClass type, Set<GeneralClass> superTypes) {
            // If the type is already added, we don't need to check its super
            // types.
            if (superTypes.add(type)) {
                // Gets all generalisations from the current type.
                for (Generalization generalization : type.getParent()) {
            
                    NameSpace superObject = generalization.getSuperType();
            
                    // If the super object is a class, we look for its super
                    // classes
                    if (superObject instanceof GeneralClass) {
                        GeneralClass genClass = (GeneralClass) superObject;
                        getAllSuperTypes(genClass, superTypes);
                    }
                }
            }
        }

        /**
         * Finds all the object nodes that are connected to the given object node, except for object nodes that are controls.
         * 
         * @param node The ObjectNode to start from.
         * @param sourceClasses The list of found Classes
         * @param visitedNodes A list of visited nodes to avoid cycling
         */
        @objid ("c008ee06-e0ad-42e8-a442-ebfa0f2dafec")
        private void findSourceClasses(ActivityNode node, List<GeneralClass> sourceClasses, List<ActivityNode> visitedNodes) {
            if (visitedNodes.contains(node)) {
                return;
            }
            
            visitedNodes.add(node);
            
            // If the node is an action, there is no need to go further.
            if (node instanceof ActivityAction) {
                return;
            } else if (node instanceof InstanceNode && !((InstanceNode) node).isIsControlType()) {
                sourceClasses.add(((InstanceNode) node).getType());
            } else {
                for (ActivityEdge objectFlow : node.getIncoming(ObjectFlow.class)) {
                    ActivityNode sourceNode = objectFlow.getSource();
                    if (!visitedNodes.contains(sourceNode)) {
                        findSourceClasses(sourceNode, sourceClasses, visitedNodes);
                    }
                }
            }
        }

        /**
         * Finds all the object nodes that are connected to the given object node, except for object nodes that are controls.
         * 
         * @param node The ObjectNode to start from.
         * @param targetClasses The list of found Classes
         * @param visitedNodes A list of visited nodes to avoid cycling
         */
        @objid ("136e32f6-d0ab-469a-bf37-48c403cd6855")
        private void findTargetClasses(ActivityNode node, List<GeneralClass> targetClasses, List<ActivityNode> visitedNodes) {
            if (visitedNodes.contains(node)) {
                return;
            }
            
            visitedNodes.add(node);
            
            // If the node is an action, there is no need to go further.
            if (node instanceof ActivityAction) {
                return;
            } else if (node instanceof InstanceNode && !((InstanceNode) node).isIsControlType()) {
                targetClasses.add(((InstanceNode) node).getType());
            } else {
                for (ActivityEdge objectFlow : node.getOutgoing(ObjectFlow.class)) {
                    ActivityNode targetNode = objectFlow.getTarget();
                    if (!visitedNodes.contains(targetNode)) {
                        findTargetClasses(targetNode, targetClasses, visitedNodes);
                    }
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
        @objid ("64464227-1e67-422d-ae89-ac4d5dcbe43c")
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
                } else if (sourceNode instanceof InstanceNode && !((InstanceNode) sourceNode).isIsControlType()) {
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
        @objid ("b5f378a2-601c-45a6-b736-a35c033bcadd")
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
                } else if (targetNode instanceof InstanceNode && !((InstanceNode) targetNode).isIsControlType()) {
                    objectFlows.add(outgoingObjectFlow);
                } else {
                    objectFlows.add(outgoingObjectFlow);
                    findTargetFlows(targetNode, objectFlows, visitedFlows);
                }
            }
        }

    }

    @objid ("4f7f81af-2575-42ae-8cd6-a9630b0b976c")
    private enum KindOfType {
        MIX,
        NULL,
        NOTNULL;
    }

}
