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

package org.modelio.diagram.editor.bpmn.elements.factories;

import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.bpmn.elements.bpmndataassociation.GmBpmnDataAssociation;
import org.modelio.diagram.editor.bpmn.elements.bpmnmessage.GmBpmnMessageLink;
import org.modelio.diagram.editor.bpmn.elements.bpmnmessageflow.GmBpmnMessageFlow;
import org.modelio.diagram.editor.bpmn.elements.bpmnsequenceflow.GmBpmnSequenceFlow;
import org.modelio.diagram.editor.bpmn.elements.diagrams.processcollaboration.GmBpmnProcessCollaborationDiagram;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.factory.IGmLinkFactory;
import org.modelio.diagram.persistence.IPersistent;
import org.modelio.diagram.persistence.IPersistentMigrator;
import org.modelio.metamodel.bpmn.flows.BpmnMessage;
import org.modelio.metamodel.bpmn.flows.BpmnMessageFlow;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.objects.BpmnDataAssociation;
import org.modelio.metamodel.visitors.DefaultModelVisitor;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Bpmn diagram specific implementation of {@link IGmLinkFactory}.
 * <p>
 * This particular implementation:
 * <ul>
 * <li>does not support cascading</li>
 * <li>only processes BPMN diagram specific elements</li>
 * </ul>
 * </p>
 */
@objid ("620b9a3a-55b6-11e2-877f-002564c97630")
public class BpmnGmLinkFactory implements IGmLinkFactory {
    /**
     * Creates the link factory for a diagram.
     */
    @objid ("620b9a40-55b6-11e2-877f-002564c97630")
    public BpmnGmLinkFactory() {
    }

    @objid ("620b9a43-55b6-11e2-877f-002564c97630")
    @Override
    public GmLink create(IGmDiagram diagram, MObject linkElement) {
        if (isInWorkflow(diagram, linkElement)) {
            return (GmLink) linkElement.accept(new ImplVisitor(diagram));
        } else {
            return null;
        }
    }

    @objid ("aec2b316-5acd-4591-8288-30e0e633bc5e")
    @Override
    public Class<? extends IPersistent> resolveClass(String namespace) {
        try {
            if (namespace.startsWith("org.modelio.diagram.editor.bpmn")) {
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

    @objid ("2eeb7d59-b159-4f74-bc8e-a5c2a335dbc6")
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

    @objid ("8d49e589-6b7b-40a4-8cd7-12cdf67783d3")
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
     * @return <code>true</code> if the element belongs to the current workflow.
     */
    @objid ("e7e508eb-e3d0-4a31-9cad-c870370ac835")
    private boolean isInWorkflow(IGmDiagram diagram, MObject elt) {
        if (elt == null) {
            return false;
        } else if (elt instanceof BpmnMessageFlow && diagram instanceof GmBpmnProcessCollaborationDiagram) {
            return true;
        } else if (Objects.equals(diagram.getRelatedElement().getOrigin(), elt)) {
            return true;
        } else {
            return isInWorkflow(diagram, elt.getCompositionOwner());
        }
    }

    /**
     * visitor class for the implementation of the links.
     */
    @objid ("620d20da-55b6-11e2-877f-002564c97630")
    private class ImplVisitor extends DefaultModelVisitor {
        @objid ("728c4cab-55c1-11e2-9337-002564c97630")
        private IGmDiagram diagram;

        @objid ("620d20e1-55b6-11e2-877f-002564c97630")
        public ImplVisitor(IGmDiagram diagram) {
            this.diagram = diagram;
        }

        @objid ("620d20ee-55b6-11e2-877f-002564c97630")
        @Override
        public Object visitBpmnSequenceFlow(BpmnSequenceFlow element) {
            final GmBpmnSequenceFlow gm_object = new GmBpmnSequenceFlow(this.diagram,
                    element,
                    new MRef(element));
            return gm_object;
        }

        @objid ("620d20f6-55b6-11e2-877f-002564c97630")
        @Override
        public Object visitBpmnMessageFlow(BpmnMessageFlow element) {
            // Use the top diagram as the flow's parent, for persistence purpose.
            IGmDiagram topDiagram = IGmDiagram.getRoot(this.diagram);
            if (!(topDiagram instanceof GmBpmnProcessCollaborationDiagram)) {
                return null;
            }
            
            final GmBpmnMessageFlow gm_object = new GmBpmnMessageFlow(topDiagram,
                    element,
                    new MRef(element));
            return gm_object;
        }

        @objid ("620d20fe-55b6-11e2-877f-002564c97630")
        @Override
        public Object visitBpmnDataAssociation(BpmnDataAssociation element) {
            final GmBpmnDataAssociation gm_object = new GmBpmnDataAssociation(this.diagram,
                    element,
                    new MRef(element));
            return gm_object;
        }

        @objid ("620d2106-55b6-11e2-877f-002564c97630")
        @Override
        public Object visitBpmnMessage(final BpmnMessage theNote) throws ClassCastException {
            final GmBpmnMessageLink link = new GmBpmnMessageLink(this.diagram, new MRef(theNote));
            return link;
        }

    }

}
