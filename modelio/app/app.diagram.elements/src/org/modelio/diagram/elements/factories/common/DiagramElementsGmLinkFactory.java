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

package org.modelio.diagram.elements.factories.common;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.factory.IGmLinkFactory;
import org.modelio.diagram.elements.plugin.DiagramElements;
import org.modelio.diagram.elements.umlcommon.dependency.GmDependency;
import org.modelio.diagram.elements.umlcommon.diagramholder.GmDiagramHolderLink;
import org.modelio.diagram.elements.umlcommon.externdocument.GmExternDocumentLink;
import org.modelio.diagram.elements.umlcommon.namespaceuse.GmNamespaceUse;
import org.modelio.diagram.elements.umlcommon.note.GmNoteLink;
import org.modelio.diagram.persistence.IPersistent;
import org.modelio.diagram.persistence.IPersistentMigrator;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.impact.ImpactLink;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.Document;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.visitors.DefaultInfrastructureVisitor;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Implementation of {@link IGmLinkFactory} in charge of {@link GmLink} elements brought by the {@link DiagramElements} plugin.
 */
@objid ("ee3ac073-b672-485d-84e3-29ec02e6ba06")
public class DiagramElementsGmLinkFactory implements IGmLinkFactory {
    @objid ("49f3007e-d03a-4ad4-a730-17a4932ee4c0")
    @Override
    public GmLink create(IGmDiagram diagram, MObject linkElement) {
        return (GmLink) linkElement.accept(new ImplVisitor(diagram));
    }

    @objid ("2fc51a40-fa48-431e-90ea-376b5b0305f3")
    @Override
    public Class<? extends IPersistent> resolveClass(String namespace) {
        // Look for elements belonging to the DiagramElements plugin
        try {
            if (namespace.startsWith("org.modelio.diagram.elements")) {
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

    @objid ("ac5a57b8-a241-4cd6-9a22-f234fb3da592")
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

    @objid ("29e93ec8-0e02-45d8-aacd-07b3cbb0ee76")
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
     * visitor class for the implementation of the links.
     */
    @objid ("0aaae7af-c6dc-4921-9ebd-cf960aa0d803")
    private class ImplVisitor extends DefaultInfrastructureVisitor {
        @objid ("96ba6266-700d-406d-942c-6e5179e61f7b")
        private IGmDiagram diagram;

        @objid ("f0a2b81a-9fd8-49eb-9602-a7066f783fa1")
        public ImplVisitor(IGmDiagram diagram) {
            this.diagram = diagram;
        }

        @objid ("e929b3b4-e05f-476b-a07e-28e29459d935")
        @Override
        public Object visitDependency(final Dependency theDep) {
            if (theDep.isStereotyped("ModelerModule", "related_diagram")) {
                if (theDep.getDependsOn() == null || theDep.getDependsOn() instanceof AbstractDiagram) {
                    // It is a related diagram link
                    return new GmDiagramHolderLink(this.diagram, new MRef(theDep), theDep);
                }
            }
            
            // It is a standard dependency link
            return new GmDependency(this.diagram, theDep, new MRef(theDep));
        }

        @objid ("50d16a69-6e5c-4cbf-8bf0-52e6b9f7a9f2")
        @Override
        public Object visitDocument(final Document theDocument) throws ClassCastException {
            final GmExternDocumentLink link = new GmExternDocumentLink(this.diagram, new MRef(theDocument));
            return link;
        }

        @objid ("dc2b8879-6daa-4e6a-b54f-cca10b3b8c6a")
        @Override
        public Object visitElement(Element theElement) {
            return null;
        }

        @objid ("86bc9b48-b772-49c2-a48f-7e41a463c4ba")
        @Override
        public Object visitImpactLink(final ImpactLink theImpactLink) {
            return new GmNamespaceUse(this.diagram, theImpactLink, new MRef(theImpactLink));
        }

        /**
         * Creates a GmNoteLink between the annotated element and the note.
         * <p>
         * The link destination must be a GmNote.
         * 
         * @throws java.lang.ClassCastException if the destination node is not a GmNote.
         */
        @objid ("a645174b-6914-4878-b980-6b7fbaf81542")
        @Override
        public Object visitNote(Note theNote) throws ClassCastException {
            final GmNoteLink link = new GmNoteLink(this.diagram, new MRef(theNote));
            return link;
        }

    }

}
