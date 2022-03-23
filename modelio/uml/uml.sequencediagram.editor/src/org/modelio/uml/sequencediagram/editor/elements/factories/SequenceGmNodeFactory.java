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
package org.modelio.uml.sequencediagram.editor.elements.factories;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.group.GmGroup;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.factory.IGmNodeFactory;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IPersistent;
import org.modelio.diagram.persistence.IPersistentMigrator;
import org.modelio.metamodel.uml.behavior.interactionModel.CombinedFragment;
import org.modelio.metamodel.uml.behavior.interactionModel.ExecutionOccurenceSpecification;
import org.modelio.metamodel.uml.behavior.interactionModel.ExecutionSpecification;
import org.modelio.metamodel.uml.behavior.interactionModel.Gate;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionOperand;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionUse;
import org.modelio.metamodel.uml.behavior.interactionModel.Lifeline;
import org.modelio.metamodel.uml.behavior.interactionModel.StateInvariant;
import org.modelio.metamodel.visitors.DefaultModelVisitor;
import org.modelio.uml.sequencediagram.editor.elements.combinedfragment.GmCombinedFragment;
import org.modelio.uml.sequencediagram.editor.elements.executionoccurencespecification.GmExecutionOccurenceSpecification;
import org.modelio.uml.sequencediagram.editor.elements.executionspecification.GmExecutionSpecification;
import org.modelio.uml.sequencediagram.editor.elements.gate.GmGate;
import org.modelio.uml.sequencediagram.editor.elements.interactionoperand.GmInteractionOperand;
import org.modelio.uml.sequencediagram.editor.elements.interactionuse.GmInteractionUse;
import org.modelio.uml.sequencediagram.editor.elements.interactionuse.gate.GmGateOnInteractionUse;
import org.modelio.uml.sequencediagram.editor.elements.lifeline.GmLifeline;
import org.modelio.uml.sequencediagram.editor.elements.stateinvariant.GmStateInvariant;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Sequence diagram specific implementation of {@link IGmNodeFactory}.
 * <p>
 * This particular implementation:
 * <ul>
 * <li>does not support cascading</li>
 * <li>only processes sequence diagram specific elements</li>
 * </ul>
 */
@objid ("d9884974-55b6-11e2-877f-002564c97630")
public final class SequenceGmNodeFactory implements IGmNodeFactory {
    @objid ("d988497a-55b6-11e2-877f-002564c97630")
    @Override
    public GmNodeModel create(IGmDiagram diagram, GmCompositeNode parent, MObject newElement, Object initialLayoutData) {
        if (parent instanceof GmGroup) {
            return null;
        }
        // else Use the node factory visitor
        final NodeFactoryVisitor v = new NodeFactoryVisitor(diagram, initialLayoutData);
        
        final GmNodeModel child = (GmNodeModel) newElement.accept(v);
        
        if (child != null) {
            if (newElement instanceof InteractionOperand) {
                // the order is important
                CombinedFragment ownerFragment = ((InteractionOperand) newElement).getOwnerFragment();
                int idx = ownerFragment.getOperand().indexOf(newElement);
                assert (idx >= 0) : String.format("%s not found in %s (index=%d)", newElement, ownerFragment, idx);
        
                parent.addChild(child, idx);
            } else {
                parent.addChild(child);
            }
        }
        return child;
    }

    @objid ("d988498b-55b6-11e2-877f-002564c97630")
    @Override
    public Class<? extends IPersistent> resolveClass(String namespace) {
        try {
            String fixedNamespace = migrateNamespace(namespace);
            if (fixedNamespace.startsWith("org.modelio.uml.sequencediagram.editor")) {
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

    @objid ("8ea3b20b-9675-4396-8577-c7db471048ac")
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

    @objid ("2e518fe2-3020-4508-9150-5b67645013b1")
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

    @objid ("efa68f89-9f67-441e-b58e-a87f62ade5a8")
    @Override
    public String migrateNamespace(String namespace) {
        if (namespace.startsWith("org.modelio.diagram.editor.sequence")) {
            return namespace.replaceAll("org.modelio.diagram.editor.sequence", "org.modelio.uml.sequencediagram.editor");
        }
        return namespace;
    }

    /**
     * Factory visitor that creates standard GmNodes.
     */
    @objid ("d989d001-55b6-11e2-877f-002564c97630")
    private class NodeFactoryVisitor extends DefaultModelVisitor {
        @objid ("4fe556ab-55c2-11e2-9337-002564c97630")
        private IGmDiagram diagram;

        @objid ("7063a87e-21c8-41c4-aeff-03c9cd315b64")
        private Object initialLayoutData;

        @objid ("d989d009-55b6-11e2-877f-002564c97630")
        public  NodeFactoryVisitor(IGmDiagram diagram, Object initialLayoutData) {
            this.diagram = diagram;
            this.initialLayoutData = initialLayoutData;
            
        }

        @objid ("d98b569c-55b6-11e2-877f-002564c97630")
        @Override
        public Object visitCombinedFragment(final CombinedFragment theCombinedFragment) {
            final GmCombinedFragment node = new GmCombinedFragment(this.diagram,
                    theCombinedFragment,
                    new MRef(theCombinedFragment));
            node.setLayoutData(this.initialLayoutData);
            return node;
        }

        @objid ("d989d028-55b6-11e2-877f-002564c97630")
        @Override
        public Object visitExecutionOccurenceSpecification(final ExecutionOccurenceSpecification theExecutionOccurenceSpecification) {
            final GmExecutionOccurenceSpecification node = new GmExecutionOccurenceSpecification(this.diagram,
                    theExecutionOccurenceSpecification,
                    new MRef(theExecutionOccurenceSpecification));
            node.setLayoutData(this.initialLayoutData);
            return node;
        }

        @objid ("d989d00f-55b6-11e2-877f-002564c97630")
        @Override
        public Object visitExecutionSpecification(ExecutionSpecification theExecutionSpecification) {
            final GmExecutionSpecification node = new GmExecutionSpecification(this.diagram,
                    theExecutionSpecification,
                    new MRef(theExecutionSpecification));
            node.setLayoutData(this.initialLayoutData);
            return node;
        }

        @objid ("d989d031-55b6-11e2-877f-002564c97630")
        @Override
        public Object visitGate(final Gate theGate) {
            if (theGate.getOwnerUse() != null) {
                final GmGateOnInteractionUse node = new GmGateOnInteractionUse(this.diagram,
                        theGate,
                        new MRef(theGate));
                node.setLayoutData(this.initialLayoutData);
                return node;
            } else {
                final GmGate node = new GmGate(this.diagram, theGate, new MRef(theGate));
                node.setLayoutData(this.initialLayoutData);
                return node;
            }
            
        }

        @objid ("d98b56a5-55b6-11e2-877f-002564c97630")
        @Override
        public Object visitInteractionOperand(final InteractionOperand theInteractionOperand) {
            final GmInteractionOperand node = new GmInteractionOperand(this.diagram,
                    theInteractionOperand,
                    new MRef(theInteractionOperand));
            node.setLayoutData(this.initialLayoutData);
            return node;
        }

        @objid ("d989d039-55b6-11e2-877f-002564c97630")
        @Override
        public Object visitInteractionUse(final InteractionUse theInteractionUse) {
            final GmInteractionUse node = new GmInteractionUse(this.diagram,
                    theInteractionUse,
                    new MRef(theInteractionUse));
            node.setLayoutData(this.initialLayoutData);
            return node;
        }

        @objid ("d989d017-55b6-11e2-877f-002564c97630")
        @Override
        public Object visitLifeline(Lifeline theLifeline) {
            final GmLifeline node = new GmLifeline(this.diagram, theLifeline, new MRef(theLifeline));
            node.setLayoutData(this.initialLayoutData);
            return node;
        }

        @objid ("d98b56ae-55b6-11e2-877f-002564c97630")
        @Override
        public Object visitStateInvariant(final StateInvariant theStateInvariant) {
            final GmStateInvariant node = new GmStateInvariant(this.diagram,
                    theStateInvariant,
                    new MRef(theStateInvariant));
            node.setLayoutData(this.initialLayoutData);
            return node;
        }

    }

}
