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

package org.modelio.uml.activitydiagram.editor.elements.factories;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.group.GmGroup;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.factory.IGmNodeFactory;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IPersistent;
import org.modelio.diagram.persistence.IPersistentMigrator;
import org.modelio.metamodel.uml.behavior.activityModel.AcceptCallEventAction;
import org.modelio.metamodel.uml.behavior.activityModel.AcceptChangeEventAction;
import org.modelio.metamodel.uml.behavior.activityModel.AcceptSignalAction;
import org.modelio.metamodel.uml.behavior.activityModel.AcceptTimeEventAction;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityFinalNode;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityPartition;
import org.modelio.metamodel.uml.behavior.activityModel.CallBehaviorAction;
import org.modelio.metamodel.uml.behavior.activityModel.CallOperationAction;
import org.modelio.metamodel.uml.behavior.activityModel.CentralBufferNode;
import org.modelio.metamodel.uml.behavior.activityModel.Clause;
import org.modelio.metamodel.uml.behavior.activityModel.ConditionalNode;
import org.modelio.metamodel.uml.behavior.activityModel.DataStoreNode;
import org.modelio.metamodel.uml.behavior.activityModel.DecisionMergeNode;
import org.modelio.metamodel.uml.behavior.activityModel.ExpansionNode;
import org.modelio.metamodel.uml.behavior.activityModel.ExpansionRegion;
import org.modelio.metamodel.uml.behavior.activityModel.FlowFinalNode;
import org.modelio.metamodel.uml.behavior.activityModel.ForkJoinNode;
import org.modelio.metamodel.uml.behavior.activityModel.InitialNode;
import org.modelio.metamodel.uml.behavior.activityModel.InputPin;
import org.modelio.metamodel.uml.behavior.activityModel.InstanceNode;
import org.modelio.metamodel.uml.behavior.activityModel.InterruptibleActivityRegion;
import org.modelio.metamodel.uml.behavior.activityModel.LoopNode;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectNode;
import org.modelio.metamodel.uml.behavior.activityModel.OpaqueAction;
import org.modelio.metamodel.uml.behavior.activityModel.OutputPin;
import org.modelio.metamodel.uml.behavior.activityModel.SendSignalAction;
import org.modelio.metamodel.uml.behavior.activityModel.StructuredActivityNode;
import org.modelio.metamodel.uml.behavior.activityModel.ValuePin;
import org.modelio.metamodel.visitors.DefaultModelVisitor;
import org.modelio.uml.activitydiagram.editor.elements.acceptsignal.GmAcceptSignal;
import org.modelio.uml.activitydiagram.editor.elements.action.GmAction;
import org.modelio.uml.activitydiagram.editor.elements.activitydiagram.GmActivityDiagram;
import org.modelio.uml.activitydiagram.editor.elements.activityfinal.GmActivityFinal;
import org.modelio.uml.activitydiagram.editor.elements.callbehavior.GmCallBehavior;
import org.modelio.uml.activitydiagram.editor.elements.callevent.GmCallEvent;
import org.modelio.uml.activitydiagram.editor.elements.calloperation.GmCallOperation;
import org.modelio.uml.activitydiagram.editor.elements.centralbuffer.GmCentralBuffer;
import org.modelio.uml.activitydiagram.editor.elements.changeevent.GmChangeEvent;
import org.modelio.uml.activitydiagram.editor.elements.clause.GmClause;
import org.modelio.uml.activitydiagram.editor.elements.conditional.GmConditional;
import org.modelio.uml.activitydiagram.editor.elements.datastore.GmDataStore;
import org.modelio.uml.activitydiagram.editor.elements.decisionmerge.GmDecisionMerge;
import org.modelio.uml.activitydiagram.editor.elements.expansionnode.GmExpansionNode;
import org.modelio.uml.activitydiagram.editor.elements.expansionregion.GmExpansionRegion;
import org.modelio.uml.activitydiagram.editor.elements.flowfinal.GmFlowFinal;
import org.modelio.uml.activitydiagram.editor.elements.forkjoin.GmForkJoin;
import org.modelio.uml.activitydiagram.editor.elements.initial.GmInitial;
import org.modelio.uml.activitydiagram.editor.elements.inputpin.GmInputPin;
import org.modelio.uml.activitydiagram.editor.elements.interruptible.GmInterruptible;
import org.modelio.uml.activitydiagram.editor.elements.loopnode.GmLoopNode;
import org.modelio.uml.activitydiagram.editor.elements.objectnode.GmObjectNode;
import org.modelio.uml.activitydiagram.editor.elements.outputpin.GmOutputPin;
import org.modelio.uml.activitydiagram.editor.elements.partition.GmPartition;
import org.modelio.uml.activitydiagram.editor.elements.partitioncontainer.GmDiagramPartitionContainer;
import org.modelio.uml.activitydiagram.editor.elements.partitioncontainer.GmPartitionContainer;
import org.modelio.uml.activitydiagram.editor.elements.sendsignal.GmSendSignal;
import org.modelio.uml.activitydiagram.editor.elements.structuredactivity.GmStructuredActivity;
import org.modelio.uml.activitydiagram.editor.elements.timeevent.GmTimeEvent;
import org.modelio.uml.activitydiagram.editor.elements.valuepin.GmValuePin;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Activity diagram specific implementation of {@link IGmNodeFactory}.
 * <p>
 * This particular implementation:
 * <ul>
 * <li>does not support cascading</li>
 * <li>only processes activity diagram specific elements</li>
 * </ul>
 */
@objid ("2a8f324a-55b6-11e2-877f-002564c97630")
public class ActivityGmNodeFactory implements IGmNodeFactory {
    @objid ("2a8f3250-55b6-11e2-877f-002564c97630")
    @Override
    public GmNodeModel create(IGmDiagram diagram, GmCompositeNode parent, MObject newElement, Object initialLayoutData) {
        if (parent instanceof GmGroup) {
            // Use the group element factory visitor
            final GroupElementFactoryVisitor v = new GroupElementFactoryVisitor(diagram);
        
            final GmNodeModel child = (GmNodeModel) newElement.accept(v);
            if (child != null) {
                parent.addChild(child);
            }
            return child;
        } else {
            // Use the node factory visitor
            final NodeFactoryVisitor v = new NodeFactoryVisitor(diagram, parent, initialLayoutData);
        
            final GmNodeModel child = (GmNodeModel) newElement.accept(v);
            if (child != null) {
                parent.addChild(child);
            }
            return child;
        }
    }

    @objid ("2a90b8d1-55b6-11e2-877f-002564c97630")
    @Override
    public Class<? extends IPersistent> resolveClass(String namespace) {
        try {
            String fixedNamespace = migrateNamespace(namespace);
            if (fixedNamespace.startsWith("org.modelio.uml.activitydiagram.editor")) {
                Class<?> clazz = Class.forName(fixedNamespace);
                if (clazz != null) {
                    return clazz.asSubclass(IPersistent.class);
                }
            }
        } catch (@SuppressWarnings ("unused") ClassNotFoundException | ClassCastException e) {
            // Class not found, return null
        }
        return null;
    }

    @objid ("1c0ccb34-7e1b-4712-befd-808f06ee3352")
    @Override
    public Class<? extends IPersistentMigrator> resolveMigratorClass(String classNamespace) {
        try {
            String fixedNamespace = migrateNamespace(classNamespace);
            Class<?> clazz = Class.forName(fixedNamespace);
            if (clazz != null) {
                return clazz.asSubclass(IPersistentMigrator.class);
            }
        } catch (@SuppressWarnings ("unused") ClassNotFoundException | ClassCastException e) {
            // Class not found, return null
        }
        return null;
    }

    @objid ("7383afdf-3a93-457f-90c2-080d6008d7f0")
    @Override
    public Class<? extends Enum<?>> resolveEnumClass(String enumNamespace) {
        try {
            String fixedNamespace = migrateNamespace(enumNamespace);
            Class<?> clazz = Class.forName(fixedNamespace);
            if (clazz != null && clazz.isEnum()) {
                return (Class<? extends Enum<?>>) clazz;
            }
        } catch (@SuppressWarnings ("unused") ClassNotFoundException | ClassCastException e) {
            // Enum not found, return null
        }
        return null;
    }

    @objid ("4fd5af45-7030-4462-9626-23f8e987d31e")
    @Override
    public String migrateNamespace(String namespace) {
        if (namespace.startsWith("org.modelio.diagram.editor.activity")) {
            return namespace.replaceAll("org.modelio.diagram.editor.activity", "org.modelio.uml.activitydiagram.editor");
        }
        return namespace;
    }

    /**
     * Factory visitor that creates instances to put into {@link GmGroup}.
     * 
     * @author cmarin
     */
    @objid ("2a96d350-55b6-11e2-877f-002564c97630")
    private class GroupElementFactoryVisitor extends DefaultModelVisitor {
        @objid ("d2197869-55c0-11e2-9337-002564c97630")
        private IGmDiagram diagram;

        @objid ("2a96d357-55b6-11e2-877f-002564c97630")
        public GroupElementFactoryVisitor(IGmDiagram diagram) {
            this.diagram = diagram;
        }

        @objid ("00db7284-58db-11e2-8539-00137282c51b")
        @Override
        public Object visitClause(Clause theClause) {
            GmClause clause = new GmClause(this.diagram, theClause, new MRef(theClause));
            return clause;
        }

    }

    /**
     * Factory visitor that creates standard GmNodes.
     */
    @objid ("2a90b8db-55b6-11e2-877f-002564c97630")
    private class NodeFactoryVisitor extends DefaultModelVisitor {
        @objid ("d2135dea-55c0-11e2-9337-002564c97630")
        private IGmDiagram diagram;

        @objid ("2a90b8df-55b6-11e2-877f-002564c97630")
        private Object initialLayoutData;

        @objid ("d2135deb-55c0-11e2-9337-002564c97630")
        private GmCompositeNode parent;

        @objid ("2a90b8e6-55b6-11e2-877f-002564c97630")
        public NodeFactoryVisitor(IGmDiagram diagram, GmCompositeNode parent, Object initialLayoutData) {
            this.diagram = diagram;
            this.parent = parent;
            this.initialLayoutData = initialLayoutData;
        }

        @objid ("2bdc02bc-597f-11e2-8539-00137282c51b")
        @Override
        public Object visitAcceptCallEventAction(AcceptCallEventAction theAcceptCallEventAction) {
            GmCallEvent callEvent = new GmCallEvent(this.diagram,
                    theAcceptCallEventAction,
                    new MRef(theAcceptCallEventAction));
            callEvent.setLayoutData(this.initialLayoutData);
            return callEvent;
        }

        @objid ("2bdc02c2-597f-11e2-8539-00137282c51b")
        @Override
        public Object visitAcceptChangeEventAction(AcceptChangeEventAction theAcceptChangeEventAction) {
            final GmChangeEvent changeEvent = new GmChangeEvent(this.diagram,
                    theAcceptChangeEventAction,
                    new MRef(theAcceptChangeEventAction));
            changeEvent.setLayoutData(this.initialLayoutData);
            return changeEvent;
        }

        @objid ("2bdc02c8-597f-11e2-8539-00137282c51b")
        @Override
        public Object visitAcceptSignalAction(AcceptSignalAction theAcceptSignalAction) {
            final GmAcceptSignal acceptSignal = new GmAcceptSignal(this.diagram,
                    theAcceptSignalAction,
                    new MRef(theAcceptSignalAction));
            acceptSignal.setLayoutData(this.initialLayoutData);
            return acceptSignal;
        }

        @objid ("2bdc02ce-597f-11e2-8539-00137282c51b")
        @Override
        public Object visitAcceptTimeEventAction(AcceptTimeEventAction theAcceptTimeEventAction) {
            final GmTimeEvent timeEvent = new GmTimeEvent(this.diagram,
                    theAcceptTimeEventAction,
                    new MRef(theAcceptTimeEventAction));
            timeEvent.setLayoutData(this.initialLayoutData);
            return timeEvent;
        }

        @objid ("00d91041-58db-11e2-8539-00137282c51b")
        @Override
        public Object visitActivityFinalNode(ActivityFinalNode theActivityFinalNode) {
            final GmActivityFinal activityFinal = new GmActivityFinal(this.diagram,
                    theActivityFinalNode,
                    new MRef(theActivityFinalNode));
            activityFinal.setLayoutData(this.initialLayoutData);
            return activityFinal;
        }

        @objid ("2bde6514-597f-11e2-8539-00137282c51b")
        @Override
        public Object visitActivityPartition(ActivityPartition theActivityPartition) {
            if (this.parent instanceof GmActivityDiagram) {
                // Create the partition container
                GmDiagramPartitionContainer partitionContainer = new GmDiagramPartitionContainer(this.diagram,
                        new MRef(this.diagram.getRepresentedElement()));
                partitionContainer.setLayoutData(this.initialLayoutData);
                // Create the partition and add it to the partition container.
                GmPartition partition = new GmPartition(this.diagram,
                        theActivityPartition,
                        new MRef(theActivityPartition));
                partitionContainer.addChild(partition);
                // Return the partition container.
                return partitionContainer;
            } else if (this.parent instanceof GmPartitionContainer) {
                GmPartition partition = new GmPartition(this.diagram,
                        theActivityPartition,
                        new MRef(theActivityPartition));
                partition.setLayoutData(this.initialLayoutData);
                return partition;
            }
            // Error case
            throw new IllegalArgumentException("Unhandled type of parent node while trying to create a Partition. Parent node must be GmaActivityDiagram or GmPartitionContainer. Given parent node is of type: " +
                    this.parent.getClass().getName());
        }

        @objid ("41deba36-58d0-11e2-8539-00137282c51b")
        @Override
        public Object visitCallBehaviorAction(CallBehaviorAction theCallBehaviorAction) {
            GmCallBehavior callBehavior = new GmCallBehavior(this.diagram,
                    theCallBehaviorAction,
                    new MRef(theCallBehaviorAction));
            callBehavior.setLayoutData(this.initialLayoutData);
            return callBehavior;
        }

        @objid ("41e11c94-58d0-11e2-8539-00137282c51b")
        @Override
        public Object visitCallOperationAction(CallOperationAction theCallOperationAction) {
            GmCallOperation callOperation = new GmCallOperation(this.diagram,
                    theCallOperationAction,
                    new MRef(theCallOperationAction));
            callOperation.setLayoutData(this.initialLayoutData);
            return callOperation;
        }

        @objid ("2bde6519-597f-11e2-8539-00137282c51b")
        @Override
        public Object visitCentralBufferNode(CentralBufferNode theCentralBufferNode) {
            GmCentralBuffer centralBuffer = new GmCentralBuffer(this.diagram,
                    theCentralBufferNode,
                    new MRef(theCentralBufferNode));
            centralBuffer.setLayoutData(this.initialLayoutData);
            return centralBuffer;
        }

        @objid ("2bd9a065-597f-11e2-8539-00137282c51b")
        @Override
        public Object visitClause(Clause theClause) {
            final GmClause clause = new GmClause(this.diagram, theClause, new MRef(theClause));
            clause.setLayoutData(this.initialLayoutData);
            return clause;
        }

        @objid ("2b37005d-58d2-11e2-8539-00137282c51b")
        @Override
        public Object visitConditionalNode(ConditionalNode theConditionalNode) {
            final GmConditional conditional = new GmConditional(this.diagram,
                    theConditionalNode,
                    new MRef(theConditionalNode));
            conditional.setLayoutData(this.initialLayoutData);
            return conditional;
        }

        @objid ("2bdc02b6-597f-11e2-8539-00137282c51b")
        @Override
        public Object visitDataStoreNode(DataStoreNode theDataStoreNode) {
            GmDataStore dataStore = new GmDataStore(this.diagram,
                    theDataStoreNode,
                    new MRef(theDataStoreNode));
            dataStore.setLayoutData(this.initialLayoutData);
            return dataStore;
        }

        @objid ("00d9103a-58db-11e2-8539-00137282c51b")
        @Override
        public Object visitDecisionMergeNode(DecisionMergeNode theDecisionMergeNode) {
            final GmDecisionMerge decisionMerge = new GmDecisionMerge(this.diagram,
                    theDecisionMergeNode,
                    new MRef(theDecisionMergeNode));
            decisionMerge.setLayoutData(this.initialLayoutData);
            return decisionMerge;
        }

        @objid ("2bd4dbb4-597f-11e2-8539-00137282c51b")
        @Override
        public Object visitExpansionNode(ExpansionNode theExpansionNode) {
            GmExpansionNode expansionNode = new GmExpansionNode(this.diagram,
                    theExpansionNode,
                    new MRef(theExpansionNode));
            expansionNode.setLayoutData(this.initialLayoutData);
            return expansionNode;
        }

        @objid ("00d6adcc-58db-11e2-8539-00137282c51b")
        @Override
        public Object visitExpansionRegion(ExpansionRegion theExpansionRegion) {
            final GmExpansionRegion expansionRegion = new GmExpansionRegion(this.diagram,
                    theExpansionRegion,
                    new MRef(theExpansionRegion));
            expansionRegion.setLayoutData(this.initialLayoutData);
            return expansionRegion;
        }

        @objid ("00d91027-58db-11e2-8539-00137282c51b")
        @Override
        public Object visitFlowFinalNode(FlowFinalNode theFlowFinalNode) {
            final GmFlowFinal flowfinal = new GmFlowFinal(this.diagram,
                    theFlowFinalNode,
                    new MRef(theFlowFinalNode));
            flowfinal.setLayoutData(this.initialLayoutData);
            return flowfinal;
        }

        @objid ("00d9102e-58db-11e2-8539-00137282c51b")
        @Override
        public Object visitForkJoinNode(ForkJoinNode theForkJoinNode) {
            final GmForkJoin forkJoin = new GmForkJoin(this.diagram,
                    theForkJoinNode,
                    new MRef(theForkJoinNode));
            forkJoin.setLayoutData(this.initialLayoutData);
            return forkJoin;
        }

        @objid ("00d91034-58db-11e2-8539-00137282c51b")
        @Override
        public Object visitInitialNode(InitialNode theInitialNode) {
            final GmInitial timeEvent = new GmInitial(this.diagram, theInitialNode, new MRef(theInitialNode));
            timeEvent.setLayoutData(this.initialLayoutData);
            return timeEvent;
        }

        @objid ("2bd9a05f-597f-11e2-8539-00137282c51b")
        @Override
        public Object visitInputPin(InputPin theInputPin) {
            GmInputPin inputPin = new GmInputPin(this.diagram, theInputPin, new MRef(theInputPin));
            inputPin.setLayoutData(this.initialLayoutData);
            return inputPin;
        }

        @objid ("2bd73e0a-597f-11e2-8539-00137282c51b")
        @Override
        public Object visitInstanceNode(InstanceNode theInstanceNode) {
            GmObjectNode instanceNode = new GmObjectNode(this.diagram,
                    theInstanceNode,
                    new MRef(theInstanceNode));
            instanceNode.setLayoutData(this.initialLayoutData);
            return instanceNode;
        }

        @objid ("00d6adda-58db-11e2-8539-00137282c51b")
        @Override
        public Object visitInterruptibleActivityRegion(InterruptibleActivityRegion theInterruptibleActivityRegion) {
            GmInterruptible interruptibleRegion = new GmInterruptible(this.diagram,
                    theInterruptibleActivityRegion,
                    new MRef(theInterruptibleActivityRegion));
            interruptibleRegion.setLayoutData(this.initialLayoutData);
            return interruptibleRegion;
        }

        @objid ("00d6ade0-58db-11e2-8539-00137282c51b")
        @Override
        public Object visitLoopNode(LoopNode theLoopNode) {
            final GmLoopNode loopNode = new GmLoopNode(this.diagram, theLoopNode, new MRef(theLoopNode));
            loopNode.setLayoutData(this.initialLayoutData);
            return loopNode;
        }

        @objid ("2bd4dba8-597f-11e2-8539-00137282c51b")
        @Override
        public Object visitObjectNode(ObjectNode theObjectNode) {
            final GmObjectNode instanceNode = new GmObjectNode(this.diagram,
                    theObjectNode,
                    new MRef(theObjectNode));
            instanceNode.setLayoutData(this.initialLayoutData);
            return instanceNode;
        }

        @objid ("f90583e4-58ca-11e2-8539-00137282c51b")
        @Override
        public Object visitOpaqueAction(OpaqueAction theOpaqueAction) {
            final GmAction opaqueAction = new GmAction(this.diagram,
                    theOpaqueAction,
                    new MRef(theOpaqueAction));
            opaqueAction.setLayoutData(this.initialLayoutData);
            return opaqueAction;
        }

        @objid ("2bd73e04-597f-11e2-8539-00137282c51b")
        @Override
        public Object visitOutputPin(OutputPin theOutputPin) {
            GmOutputPin outputPin = new GmOutputPin(this.diagram, theOutputPin, new MRef(theOutputPin));
            outputPin.setLayoutData(this.initialLayoutData);
            return outputPin;
        }

        @objid ("2bd4dbba-597f-11e2-8539-00137282c51b")
        @Override
        public Object visitSendSignalAction(SendSignalAction theSendSignalAction) {
            GmSendSignal sendSignal = new GmSendSignal(this.diagram,
                    theSendSignalAction,
                    new MRef(theSendSignalAction));
            sendSignal.setLayoutData(this.initialLayoutData);
            return sendSignal;
        }

        @objid ("00d6add3-58db-11e2-8539-00137282c51b")
        @Override
        public Object visitStructuredActivityNode(StructuredActivityNode theStructuredActivityNode) {
            final GmStructuredActivity structured = new GmStructuredActivity(this.diagram,
                    theStructuredActivityNode,
                    new MRef(theStructuredActivityNode));
            structured.setLayoutData(this.initialLayoutData);
            return structured;
        }

        @objid ("2bd4dbae-597f-11e2-8539-00137282c51b")
        @Override
        public Object visitValuePin(ValuePin theValuePin) {
            GmValuePin valuePin = new GmValuePin(this.diagram, theValuePin, new MRef(theValuePin));
            valuePin.setLayoutData(this.initialLayoutData);
            return valuePin;
        }

    }

}
