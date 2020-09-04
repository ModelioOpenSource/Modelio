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

package org.modelio.diagram.editor.deployment.elements.factories;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.deployment.elements.artifact.GmArtifact;
import org.modelio.diagram.editor.deployment.elements.node.GmNode;
import org.modelio.diagram.elements.common.group.GmGroup;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.factory.IGmNodeFactory;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IPersistent;
import org.modelio.diagram.persistence.IPersistentMigrator;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.metamodel.uml.statik.Node;
import org.modelio.metamodel.visitors.DefaultModelVisitor;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Deployment diagram specific implementation of {@link IGmNodeFactory}.
 * <p>
 * This particular implementation:
 * <ul>
 * <li>does not support cascading</li>
 * <li>only processes deployment diagram specific elements</li>
 * </ul>
 */
@objid ("972cad3a-55b6-11e2-877f-002564c97630")
public final class DeploymentGmNodeFactory implements IGmNodeFactory {
    @objid ("972cad48-55b6-11e2-877f-002564c97630")
    @Override
    public GmNodeModel create(IGmDiagram diagram, GmCompositeNode parent, MObject newElement, Object initialLayoutData) {
        if (parent instanceof GmGroup) {
            // Use the label factory visitor
            final GmLabelFactoryVisitor v = new GmLabelFactoryVisitor(diagram, initialLayoutData);
        
            final GmNodeModel child = (GmNodeModel) newElement.accept(v);
            if (child != null) {
                parent.addChild(child);
            }
            return child;
        }
        // else Use the node factory visitor
        final NodeFactoryVisitor v = new NodeFactoryVisitor(diagram, initialLayoutData);
        
        final GmNodeModel child = (GmNodeModel) newElement.accept(v);
        if (child != null) {
            parent.addChild(child);
        }
        return child;
    }

    @objid ("972cad6c-55b6-11e2-877f-002564c97630")
    @Override
    public Class<? extends IPersistent> resolveClass(String namespace) {
        try {
            if (namespace.startsWith("org.modelio.diagram.editor.deployment")) {
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

    @objid ("6af9fcc7-5b67-45ef-a4f5-8db78a1e5ebe")
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

    @objid ("e2398df0-6a6c-4d0b-ba95-7144fe4e2298")
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
     * Factory visitor that creates only instances of GmLabels or derived classes.
     */
    @objid ("972e33e9-55b6-11e2-877f-002564c97630")
    private class GmLabelFactoryVisitor extends DefaultModelVisitor {
        @objid ("1cc6ca69-55c2-11e2-9337-002564c97630")
        private IGmDiagram diagram;

        @objid ("972e33ed-55b6-11e2-877f-002564c97630")
        private Object initialLayoutData;

        @objid ("972e33f1-55b6-11e2-877f-002564c97630")
        public GmLabelFactoryVisitor(IGmDiagram diagram, Object initialLayoutData) {
            this.diagram = diagram;
            this.initialLayoutData = initialLayoutData;
        }

    }

    /**
     * Factory visitor that creates standard GmNodes.
     */
    @objid ("972cad74-55b6-11e2-877f-002564c97630")
    private class NodeFactoryVisitor extends DefaultModelVisitor {
        @objid ("1cc543cb-55c2-11e2-9337-002564c97630")
        private IGmDiagram diagram;

        @objid ("972cad78-55b6-11e2-877f-002564c97630")
        private Object initialLayoutData;

        @objid ("972cad7c-55b6-11e2-877f-002564c97630")
        public NodeFactoryVisitor(IGmDiagram diagram, Object initialLayoutData) {
            this.diagram = diagram;
            this.initialLayoutData = initialLayoutData;
        }

        @objid ("972e33d9-55b6-11e2-877f-002564c97630")
        @Override
        public Object visitArtifact(Artifact theArtifact) {
            GmArtifact actor = new GmArtifact(this.diagram, theArtifact, new MRef(theArtifact));
            actor.setLayoutData(this.initialLayoutData);
            return actor;
        }

        @objid ("972e33e1-55b6-11e2-877f-002564c97630")
        @Override
        public Object visitNode(Node theNode) {
            GmNode node = new GmNode(this.diagram, theNode, new MRef(theNode));
            node.setLayoutData(this.initialLayoutData);
            return node;
        }

    }

}
