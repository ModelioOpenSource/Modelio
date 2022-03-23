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
package org.modelio.diagram.elements.common.abstractdiagram;

import java.util.Deque;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.metamodel.InfrastructureMetamodel;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.Document;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.visitors.DefaultInfrastructureVisitor;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Drop policy extension that deals with Infrastructure elements.
 */
@objid ("a7335059-6042-4f5d-a3d5-143cb8ec837a")
public class InfraDiagramElementDropEditPolicyExtension extends AbstractDiagramElementDropEditPolicyExtension {
    @objid ("d81e8c60-14ae-4f4f-833d-477d3524ef58")
    public  InfraDiagramElementDropEditPolicyExtension() {
        super();
    }

    @objid ("7e11d6e0-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Command getUnmaskCommandFor(DiagramElementDropEditPolicy dropPolicy, final MObject droppedElement, final Point dropLocation) {
        if (droppedElement.getMClass().getOrigin().getName().equals(InfrastructureMetamodel.NAME)) {
            final Command cmd = (Command) droppedElement.accept(new InfraVisitorImpl(dropLocation, dropPolicy));
            return cmd != null ? cmd : super.getUnmaskCommandFor(dropPolicy, droppedElement, dropLocation);
        }
        return null;
    }

    @objid ("7e11d6c6-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean isToBeAddedToHierarchy(IGmDiagram context, Deque<MObject> hierarchy, MObject candidate) {
        return false;
    }

    @objid ("1fd299c3-38b1-4e16-ac3d-324cef8480a7")
    private static class InfraVisitorImpl extends DefaultInfrastructureVisitor {
        @objid ("41a0338c-a9de-4520-8a22-8d6e141fed5c")
        private Point dropLocation;

        @objid ("2fda1a94-c53a-4704-b452-a28aa51f3567")
        private DiagramElementDropEditPolicy dropPolicy;

        @objid ("c7949d9c-f734-4f27-8b03-c61a0569bb9d")
        public  InfraVisitorImpl(Point dropLocation, DiagramElementDropEditPolicy dropPolicy) {
            this.dropLocation = dropLocation;
            this.dropPolicy = dropPolicy;
            
        }

        @objid ("7e143900-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public Object visitDependency(final Dependency theDependency) {
            if (theDependency.isStereotyped("ModelerModule", "related_diagram")) {
                return new UnmaskLinkedNodeCommand(theDependency, (AbstractDiagramEditPart) this.dropPolicy.getHost(), new Rectangle(this.dropLocation, new Dimension(-1, -1)), this.dropLocation);
            } else {
                return visitModelElement(theDependency);
            }
            
        }

        @objid ("7e143922-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public Object visitDocument(final Document theDocument) {
            return new UnmaskDocumentCommand(theDocument, (AbstractDiagramEditPart) this.dropPolicy.getHost(), new Rectangle(this.dropLocation, new Dimension(-1, -1)), this.dropLocation);
        }

        @objid ("7e14390d-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public Object visitNote(final Note theNote) {
            return new UnmaskNoteCommand(theNote, (AbstractDiagramEditPart) this.dropPolicy.getHost(), new Rectangle(this.dropLocation, new Dimension(-1, -1)), this.dropLocation);
        }

    }

}
