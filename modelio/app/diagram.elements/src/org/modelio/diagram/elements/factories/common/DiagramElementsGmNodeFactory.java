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

package org.modelio.diagram.elements.factories.common;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.group.GmGroup;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.factory.IGmNodeFactory;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.plugin.DiagramElements;
import org.modelio.diagram.elements.umlcommon.diagramview.GmDiagramView;
import org.modelio.diagram.elements.umlcommon.externdocument.GmExternDocument;
import org.modelio.diagram.elements.umlcommon.note.GmNote;
import org.modelio.diagram.persistence.IPersistent;
import org.modelio.diagram.persistence.IPersistentMigrator;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.Document;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.visitors.DefaultInfrastructureVisitor;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Implementation of {@link IGmNodeFactory} in charge of {@link GmNodeModel} elements brought by the {@link DiagramElements} plugin.
 */
@objid ("b8daf760-4118-4e50-8dfd-a6ddc1aab1d7")
public class DiagramElementsGmNodeFactory implements IGmNodeFactory {
    @objid ("8cbe47bd-3208-4f90-8b10-9ab4423b857d")
    @Override
    public GmNodeModel create(IGmDiagram diagram, GmCompositeNode parentNode, MObject elementToUnmask, Object initialLayoutData) {
        // Cascaded factories did not produce anything...
        if (parentNode instanceof GmGroup) {
            throw new UnsupportedOperationException(elementToUnmask.toString() + " not unmaskable as a label.");
        } else {
            // Use the node factory visitor
            final NodeFactoryVisitor v = new NodeFactoryVisitor(diagram, parentNode, initialLayoutData);
        
            final GmNodeModel child = (GmNodeModel) elementToUnmask.accept(v);
            if (child != null) {
                parentNode.addChild(child);
            }
            return child;
        }
    }

    @objid ("599f034d-2e99-407b-8d16-649ccf13097c")
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
        
            // Diagram view migration :
            switch (namespace) {
            case "org.modelio.diagram.editor.activity.elements.activitydiagramview.GmActivityDiagramView":
            case "org.modelio.archimate.diagrams.elements.archimatediagramview.GmArchimateDiagramView":
            case "org.modelio.diagram.editor.bpmn.elements.diagrams.view.GmBpmnDiagramView":
            case "org.modelio.diagram.editor.communication.elements.communicationdiagramview.GmCommunicationDiagramView":
            case "com.modeliosoft.modelio.impact.diagrams.common.elements.impactdiagramview.GmImpactDiagramView":
            case "org.modelio.diagram.editor.sequence.elements.sequencediagramview.GmSequenceDiagramView":
            case "org.modelio.diagram.editor.state.elements.statediagramview.GmStateDiagramView":
            case "org.modelio.diagram.editor.statik.elements.staticdiagramview.GmStaticDiagramView":
            case "org.modelio.archimate.diagrams.elements.viewpointdiagramview.GmViewPointDiagramView":
                return GmDiagramView.class;
            }
        } catch (@SuppressWarnings ("unused") ClassNotFoundException | ClassCastException e) {
            // Class not found, return null
        }
        return null;
    }

    @objid ("5edc673f-c275-4f25-881f-7f389c3f9c79")
    @Override
    public Class<? extends IPersistentMigrator> resolveMigratorClass(String namespace) {
        // Look for elements belonging to the DiagramElements plugin
        try {
            Class<?> clazz = Class.forName(namespace);
            if (clazz != null) {
                return clazz.asSubclass(IPersistentMigrator.class);
            }
        } catch (@SuppressWarnings ("unused") ClassNotFoundException | ClassCastException e) {
            // Class not found, return null
        }
        return null;
    }

    @objid ("d38637dc-fb72-4564-b9de-7a40627f6d56")
    @Override
    public Class<? extends Enum<?>> resolveEnumClass(String namespace) {
        // Look for elements belonging to the DiagramElements plugin
        try {
            Class<?> clazz = Class.forName(namespace);
            if (clazz != null && clazz.isEnum()) {
                return (Class<? extends Enum<?>>) clazz;
            }
        } catch (@SuppressWarnings ("unused") ClassNotFoundException | ClassCastException e) {
            // Class not found, return null
        }
        return null;
    }

    /**
     * Factory visitor that creates standard GmNodes.
     */
    @objid ("d8fe9242-f8f0-49fd-a6d3-c1aae0a75f94")
    private static final class NodeFactoryVisitor extends DefaultInfrastructureVisitor {
        @objid ("f21bd436-318e-4a3c-b65b-24ea704b05b7")
        private IGmDiagram diagram;

        @objid ("85217de8-2220-49f0-bfcf-e95da2de6137")
        private Object initialLayoutData;

        @objid ("f88f83d2-36ab-4493-909c-acd8392d23a4")
        private GmCompositeNode parent;

        /**
         * Creates the visitor.
         * @param diagram The diagram
         * @param parent The parent node
         * @param initialLayoutData The initial layout data.
         */
        @objid ("66a3b242-1b5f-44fc-8c8e-c61d2e087957")
        public NodeFactoryVisitor(IGmDiagram diagram, GmCompositeNode parent, Object initialLayoutData) {
            this.diagram = diagram;
            this.parent = parent;
            this.initialLayoutData = initialLayoutData;
        }

        @objid ("3978b383-6301-4253-95a5-62636b9fdb5b")
        @Override
        public Object visitDependency(final Dependency dep) {
            if (dep.isStereotyped("ModelerModule", "related_diagram")) {
                final ModelElement target = dep.getDependsOn();
                // final ModelElement src = dep.getImpacted();
                if (target == null || target instanceof AbstractDiagram) {
                    // It is a related diagram link
                    GmDiagramView node = new GmDiagramView(this.diagram, (AbstractDiagram) target, new MRef(target));
                    node.setLayoutData(this.initialLayoutData);
            
                    return node;
                }
            }
            
            // Not handled
            return super.visitDependency(dep);
        }

        @objid ("077e675f-c160-4a34-9b0a-0f95b8d78004")
        @Override
        public Object visitDocument(final Document theDocument) {
            final GmExternDocument node = new GmExternDocument(this.diagram, theDocument, new MRef(theDocument));
            node.setLayoutData(this.initialLayoutData);
            return node;
        }

        @objid ("e80b6e9a-f354-4ca3-9453-6f68b33d5d55")
        @Override
        public Object visitNote(Note theNote) {
            final GmNote node = new GmNote(this.diagram, theNote, new MRef(theNote));
            node.setLayoutData(this.initialLayoutData);
            return node;
        }

        @objid ("5733b2a3-a1e6-400d-9b14-18be6de6a6cd")
        @Override
        public Object visitAbstractDiagram(final AbstractDiagram theDiagram) {
            final GmDiagramView node = new GmDiagramView(this.diagram, theDiagram, new MRef(theDiagram));
            node.setLayoutData(this.initialLayoutData);
            return node;
        }

    }

}
