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

package org.modelio.diagram.editor.communication.elements.factories;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.communication.elements.communicationmessage.GmCommunicationMessageLabel;
import org.modelio.diagram.editor.communication.elements.communicationnode.GmCommunicationNode;
import org.modelio.diagram.editor.statik.elements.namespacelabel.GmNameSpaceLabel;
import org.modelio.diagram.elements.common.group.GmGroup;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.factory.IGmNodeFactory;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IPersistent;
import org.modelio.diagram.persistence.IPersistentMigrator;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationMessage;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationNode;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.metamodel.visitors.DefaultModelVisitor;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Communication diagram specific implementation of {@link IGmNodeFactory}.
 * <p>
 * This particular implementation:
 * <ul>
 * <li>does not support cascading</li>
 * <li>only processes communication diagram specific elements</li>
 * </ul>
 */
@objid ("7a313e7b-55b6-11e2-877f-002564c97630")
public final class CommunicationGmNodeFactory implements IGmNodeFactory {
    @objid ("7a32c4df-55b6-11e2-877f-002564c97630")
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

    @objid ("7a32c503-55b6-11e2-877f-002564c97630")
    @Override
    public Class<? extends IPersistent> resolveClass(String namespace) {
        try {
            if (namespace.startsWith("org.modelio.diagram.editor.communication")) {
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

    @objid ("7c6e6626-5662-459c-86c6-c778b3b7a8cb")
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

    @objid ("9a003ed8-4118-407e-9a75-53aa146fbdd9")
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
    @objid ("7a344b81-55b6-11e2-877f-002564c97630")
    private class GmLabelFactoryVisitor extends DefaultModelVisitor {
        @objid ("9c9ba9f9-55c1-11e2-9337-002564c97630")
        private IGmDiagram diagram;

        @objid ("7a344b85-55b6-11e2-877f-002564c97630")
        private Object initialLayoutData;

        @objid ("7a344b89-55b6-11e2-877f-002564c97630")
        public GmLabelFactoryVisitor(IGmDiagram diagram, Object initialLayoutData) {
            this.diagram = diagram;
            this.initialLayoutData = initialLayoutData;
        }

        @objid ("8f8aa6e8-f15f-457e-8d05-6e632d771537")
        @Override
        public Object visitArtifact(Artifact theArtifact) {
            final GmNameSpaceLabel node = new GmNameSpaceLabel(this.diagram, theArtifact, new MRef(theArtifact));
            node.setLayoutData(this.initialLayoutData);
            return node;
        }

        @objid ("191a3277-5a8c-4e07-a450-cfa2bbdeee1b")
        @Override
        public Object visitCommunicationMessage(final CommunicationMessage theCommunicationMessage) {
            final GmCommunicationMessageLabel node = new GmCommunicationMessageLabel(this.diagram, theCommunicationMessage, new MRef(theCommunicationMessage));
            node.setLayoutData(this.initialLayoutData);
            return node;
        }

    }

    /**
     * Factory visitor that creates standard GmNodes.
     */
    @objid ("7a32c50b-55b6-11e2-877f-002564c97630")
    private class NodeFactoryVisitor extends DefaultModelVisitor {
        @objid ("9c9b0db9-55c1-11e2-9337-002564c97630")
        private IGmDiagram diagram;

        @objid ("7a32c50f-55b6-11e2-877f-002564c97630")
        private Object initialLayoutData;

        @objid ("7a32c513-55b6-11e2-877f-002564c97630")
        public NodeFactoryVisitor(IGmDiagram diagram, Object initialLayoutData) {
            this.diagram = diagram;
            this.initialLayoutData = initialLayoutData;
        }

        @objid ("06a713e8-59a5-11e2-80d8-00137282c51b")
        @Override
        public Object visitCommunicationNode(CommunicationNode theCommunicationNode) {
            GmNodeModel node = new GmCommunicationNode(this.diagram,
                    theCommunicationNode,
                    new MRef(theCommunicationNode));
            node.setLayoutData(this.initialLayoutData);
            return node;
        }

    }

}
