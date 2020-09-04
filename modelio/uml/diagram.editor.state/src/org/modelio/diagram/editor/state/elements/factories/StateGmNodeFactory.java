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

package org.modelio.diagram.editor.state.elements.factories;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.state.elements.choice.GmChoice;
import org.modelio.diagram.editor.state.elements.connectionpoint.GmConnectionPoint;
import org.modelio.diagram.editor.state.elements.deephistory.GmDeepHistory;
import org.modelio.diagram.editor.state.elements.entry.GmEntry;
import org.modelio.diagram.editor.state.elements.exit.GmExitPoint;
import org.modelio.diagram.editor.state.elements.finalstate.GmFinalState;
import org.modelio.diagram.editor.state.elements.fork.GmForkState;
import org.modelio.diagram.editor.state.elements.initialstate.GmInitialState;
import org.modelio.diagram.editor.state.elements.internaltransition.GmInternalTransition;
import org.modelio.diagram.editor.state.elements.join.GmJoin;
import org.modelio.diagram.editor.state.elements.junction.GmJunction;
import org.modelio.diagram.editor.state.elements.region.GmRegion;
import org.modelio.diagram.editor.state.elements.shallowhistory.GmShallowHistory;
import org.modelio.diagram.editor.state.elements.state.GmState;
import org.modelio.diagram.editor.state.elements.terminal.GmTerminal;
import org.modelio.diagram.elements.common.group.GmGroup;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.factory.IGmNodeFactory;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IPersistent;
import org.modelio.diagram.persistence.IPersistentMigrator;
import org.modelio.metamodel.uml.behavior.stateMachineModel.ChoicePseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.ConnectionPointReference;
import org.modelio.metamodel.uml.behavior.stateMachineModel.DeepHistoryPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.EntryPointPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.ExitPointPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.FinalState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.ForkPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.InitialPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.InternalTransition;
import org.modelio.metamodel.uml.behavior.stateMachineModel.JoinPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.JunctionPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Region;
import org.modelio.metamodel.uml.behavior.stateMachineModel.ShallowHistoryPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.behavior.stateMachineModel.TerminatePseudoState;
import org.modelio.metamodel.visitors.DefaultModelVisitor;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * State diagram specific implementation of {@link IGmNodeFactory}.
 * <p>
 * This particular implementation:
 * <ul>
 * <li>does not support cascading</li>
 * <li>only processes state diagram specific elements</li>
 * </ul>
 */
@objid ("f53c4461-55b6-11e2-877f-002564c97630")
public class StateGmNodeFactory implements IGmNodeFactory {
    @objid ("f53c4467-55b6-11e2-877f-002564c97630")
    @Override
    public GmNodeModel create(IGmDiagram diagram, GmCompositeNode parent, MObject newElement, Object initialLayoutData) {
        if (parent instanceof GmGroup) {
            // Use the group element factory visitor
            final GroupElementFactoryVisitor v = new GroupElementFactoryVisitor(diagram, initialLayoutData);
        
            final GmNodeModel child = (GmNodeModel) newElement.accept(v);
            if (child != null) {
                parent.addChild(child);
            }
            return child;
        } else {
            // Use the node factory visitor
            final NodeFactoryVisitor v = new NodeFactoryVisitor(diagram, initialLayoutData);
        
            final GmNodeModel child = (GmNodeModel) newElement.accept(v);
            if (child != null) {
                parent.addChild(child);
            }
            return child;
        }
    }

    @objid ("f53c4477-55b6-11e2-877f-002564c97630")
    @Override
    public Class<? extends IPersistent> resolveClass(String namespace) {
        try {
            if (namespace.startsWith("org.modelio.diagram.editor.state")) {
                Class<?> clazz = Class.forName(namespace);
                if (clazz != null) {
                    return clazz.asSubclass(IPersistent.class);
                }
            }
        } catch (@SuppressWarnings ("unused") ClassNotFoundException | ClassCastException e) {
            // Class not found, return null
        }
        return null;
    }

    @objid ("09b06394-f96c-4e06-a452-9dac42e9442f")
    @Override
    public Class<? extends IPersistentMigrator> resolveMigratorClass(String classNamespace) {
        try {
            Class<?> clazz = Class.forName(classNamespace);
            if (clazz != null) {
                return clazz.asSubclass(IPersistentMigrator.class);
            }
        } catch (@SuppressWarnings ("unused") ClassNotFoundException | ClassCastException e) {
            // Class not found, return null
        }
        return null;
    }

    @objid ("8170f29b-547c-4c0c-99e5-3ac1d9d3b93e")
    @Override
    public Class<? extends Enum<?>> resolveEnumClass(String enumNamespace) {
        try {
            Class<?> clazz = Class.forName(enumNamespace);
            if (clazz != null && clazz.isEnum()) {
                return (Class<? extends Enum<?>>) clazz;
            }
        } catch (@SuppressWarnings ("unused") ClassNotFoundException | ClassCastException e) {
            // Enum not found, return null
        }
        return null;
    }

    /**
     * Factory visitor that creates instances to put into {@link GmGroup}.
     */
    @objid ("f540d82e-55b6-11e2-877f-002564c97630")
    private class GroupElementFactoryVisitor extends DefaultModelVisitor {
        @objid ("816945b9-55c2-11e2-9337-002564c97630")
        private IGmDiagram diagram;

        @objid ("f540d835-55b6-11e2-877f-002564c97630")
        private Object initialLayoutData;

        @objid ("f540d836-55b6-11e2-877f-002564c97630")
        public GroupElementFactoryVisitor(IGmDiagram diagram, Object initialLayoutData) {
            this.diagram = diagram;
            this.initialLayoutData = initialLayoutData;
        }

        @objid ("198972d2-0438-4c2c-b56a-c5ca14908819")
        @Override
        public Object visitInternalTransition(InternalTransition theInternalTransition) {
            final GmInternalTransition region = new GmInternalTransition(this.diagram,
                    theInternalTransition,
                    new MRef(theInternalTransition));
            region.setLayoutData(this.initialLayoutData);
            return region;
        }

        @objid ("494d5692-df32-4fcb-a79e-fa2368e131cd")
        @Override
        public Object visitRegion(Region theRegion) {
            final GmRegion node = new GmRegion(this.diagram, theRegion, new MRef(theRegion));
            node.setLayoutData(this.initialLayoutData);
            return node;
        }

    }

    /**
     * Factory visitor that creates standard GmNodes.
     */
    @objid ("f53dcae9-55b6-11e2-877f-002564c97630")
    private class NodeFactoryVisitor extends DefaultModelVisitor {
        @objid ("81661169-55c2-11e2-9337-002564c97630")
        private IGmDiagram diagram;

        @objid ("f53dcaf0-55b6-11e2-877f-002564c97630")
        private Object initialLayoutData;

        @objid ("f53dcaf1-55b6-11e2-877f-002564c97630")
        public NodeFactoryVisitor(IGmDiagram diagram, Object initialLayoutData) {
            this.diagram = diagram;
            this.initialLayoutData = initialLayoutData;
        }

        @objid ("f53dcaf7-55b6-11e2-877f-002564c97630")
        @Override
        public Object visitChoicePseudoState(ChoicePseudoState theChoicePseudoState) {
            final GmChoice choice = new GmChoice(this.diagram,
                    theChoicePseudoState,
                    new MRef(theChoicePseudoState));
            choice.setLayoutData(this.initialLayoutData);
            return choice;
        }

        @objid ("f53dcaff-55b6-11e2-877f-002564c97630")
        @Override
        public Object visitConnectionPointReference(ConnectionPointReference theConnectionPointReference) {
            final GmConnectionPoint connectionPoint = new GmConnectionPoint(this.diagram,
                    theConnectionPointReference,
                    new MRef(theConnectionPointReference));
            connectionPoint.setLayoutData(this.initialLayoutData);
            return connectionPoint;
        }

        @objid ("f53dcb07-55b6-11e2-877f-002564c97630")
        @Override
        public Object visitDeepHistoryPseudoState(DeepHistoryPseudoState theDeepHistoryPseudoState) {
            final GmDeepHistory deepHistory = new GmDeepHistory(this.diagram,
                    theDeepHistoryPseudoState,
                    new MRef(theDeepHistoryPseudoState));
            deepHistory.setLayoutData(this.initialLayoutData);
            return deepHistory;
        }

        @objid ("f53dcb17-55b6-11e2-877f-002564c97630")
        @Override
        public Object visitEntryPointPseudoState(EntryPointPseudoState theEntryPointPseudoState) {
            final GmEntry entry = new GmEntry(this.diagram,
                    theEntryPointPseudoState,
                    new MRef(theEntryPointPseudoState));
            entry.setLayoutData(this.initialLayoutData);
            return entry;
        }

        @objid ("f53f517b-55b6-11e2-877f-002564c97630")
        @Override
        public Object visitExitPointPseudoState(ExitPointPseudoState theExitPointPseudoState) {
            final GmExitPoint exit = new GmExitPoint(this.diagram,
                    theExitPointPseudoState,
                    new MRef(theExitPointPseudoState));
            exit.setLayoutData(this.initialLayoutData);
            return exit;
        }

        @objid ("f53f5183-55b6-11e2-877f-002564c97630")
        @Override
        public Object visitFinalState(FinalState theFinalState) {
            final GmFinalState finalState = new GmFinalState(this.diagram,
                    theFinalState,
                    new MRef(theFinalState));
            finalState.setLayoutData(this.initialLayoutData);
            return finalState;
        }

        @objid ("f53f518b-55b6-11e2-877f-002564c97630")
        @Override
        public Object visitForkPseudoState(ForkPseudoState theForkPseudoState) {
            final GmForkState fork = new GmForkState(this.diagram,
                    theForkPseudoState,
                    new MRef(theForkPseudoState));
            fork.setLayoutData(this.initialLayoutData);
            return fork;
        }

        @objid ("f53f5193-55b6-11e2-877f-002564c97630")
        @Override
        public Object visitInitialPseudoState(InitialPseudoState theInitialPseudoState) {
            final GmInitialState initial = new GmInitialState(this.diagram,
                    theInitialPseudoState,
                    new MRef(theInitialPseudoState));
            initial.setLayoutData(this.initialLayoutData);
            return initial;
        }

        @objid ("f53f519b-55b6-11e2-877f-002564c97630")
        @Override
        public Object visitJoinPseudoState(JoinPseudoState theJoinPseudoState) {
            final GmJoin join = new GmJoin(this.diagram, theJoinPseudoState, new MRef(theJoinPseudoState));
            join.setLayoutData(this.initialLayoutData);
            return join;
        }

        @objid ("f53f51a3-55b6-11e2-877f-002564c97630")
        @Override
        public Object visitJunctionPseudoState(JunctionPseudoState theJunctionPseudoState) {
            final GmJunction junction = new GmJunction(this.diagram,
                    theJunctionPseudoState,
                    new MRef(theJunctionPseudoState));
            junction.setLayoutData(this.initialLayoutData);
            return junction;
        }

        @objid ("f53f51ab-55b6-11e2-877f-002564c97630")
        @Override
        public Object visitRegion(Region theRegion) {
            final GmRegion region = new GmRegion(this.diagram, theRegion, new MRef(theRegion));
            region.setLayoutData(this.initialLayoutData);
            return region;
        }

        @objid ("f53f51b3-55b6-11e2-877f-002564c97630")
        @Override
        public Object visitShallowHistoryPseudoState(ShallowHistoryPseudoState theShallowHistoryPseudoState) {
            final GmShallowHistory shallowHistory = new GmShallowHistory(this.diagram,
                    theShallowHistoryPseudoState,
                    new MRef(theShallowHistoryPseudoState));
            shallowHistory.setLayoutData(this.initialLayoutData);
            return shallowHistory;
        }

        @objid ("f53f51bb-55b6-11e2-877f-002564c97630")
        @Override
        public Object visitState(State theState) {
            final GmState state = new GmState(this.diagram, theState, new MRef(theState));
            state.setLayoutData(this.initialLayoutData);
            return state;
        }

        @objid ("f540d81d-55b6-11e2-877f-002564c97630")
        @Override
        public Object visitTerminatePseudoState(TerminatePseudoState theTerminatePseudoState) {
            final GmTerminal terminal = new GmTerminal(this.diagram,
                    theTerminatePseudoState,
                    new MRef(theTerminatePseudoState));
            terminal.setLayoutData(this.initialLayoutData);
            return terminal;
        }

    }

}
