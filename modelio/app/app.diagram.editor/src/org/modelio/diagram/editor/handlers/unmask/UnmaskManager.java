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
package org.modelio.diagram.editor.handlers.unmask;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.handles.HandleBounds;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;
import org.modelio.metamodel.uml.infrastructure.Document;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.Generalization;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.InterfaceRealization;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This classes deals with unmasking model elements from a selected model element.<br>
 * The main part is about links such as generalizations, realizations and associations.<br>
 * A basic layout in line or column is used.
 */
@objid ("6658fde0-33f7-11e2-95fe-001ec947c8cc")
public class UnmaskManager {
    @objid ("6658fde2-33f7-11e2-95fe-001ec947c8cc")
    private static final int VERTICAL_OFFSET = 120;

    @objid ("665b5f15-33f7-11e2-95fe-001ec947c8cc")
    private static final int HORIZONTAL_OFFSET = 200;

    /**
     * Unmask child generalizations and realizations.<br>
     * The represented element must be at least an NameSpace.
     * @param viewer the viewer to unmask the elements on.
     * @param gmModel the gmModel representing the model element to work from.
     */
    @objid ("665b5f17-33f7-11e2-95fe-001ec947c8cc")
    public void unmaskChildLink(final EditPartViewer viewer, final GmModel gmModel) {
        MObject elt = gmModel.getRepresentedElement();
        if (elt != null) {
            List<MObject> toUnmask = new ArrayList<>();
        
            // Get all child generalizations
            if (elt instanceof NameSpace) {
                NameSpace ns = (NameSpace) elt;
        
                for (Generalization link : ns.getSpecialization()) {
                    toUnmask.add(link);
                }
            }
        
            // Get all child realizations
            if (elt instanceof Interface) {
                Interface itf = (Interface) elt;
        
                for (InterfaceRealization link : itf.getImplementedLink()) {
                    toUnmask.add(link);
                }
            }
        
            // Layout all links in a line
            RelativeRectangle bounds = getBounds(viewer, gmModel);
            RelativePoint initialPosition = new RelativePoint(
                    bounds.ref,
                    bounds.bounds.x +
                    (bounds.bounds.width / 2) -
                    (UnmaskManager.HORIZONTAL_OFFSET * (toUnmask.size() - 1) / 2), bounds.bounds.y +
                            bounds.bounds.height +
                            UnmaskManager.VERTICAL_OFFSET);
        
            layoutHorizontal(initialPosition, viewer, toUnmask);
        
        }
        
    }

    /**
     * Unmask all constraints in a column, next to the given element, on the right.<br>
     * The represented element must be an ModelElement.
     * @param viewer the viewer to unmask the elements on.
     * @param gmModel the gmModel representing the model element to work from.
     */
    @objid ("665b5f1e-33f7-11e2-95fe-001ec947c8cc")
    public void unmaskConstraints(final EditPartViewer viewer, final GmModel gmModel) {
        MObject elt = gmModel.getRepresentedElement();
        if (elt instanceof ModelElement) {
            List<MObject> toUnmask = new ArrayList<>();
        
            // Get all constraints
            if (elt instanceof UmlModelElement) {
                UmlModelElement modelElement = (UmlModelElement) elt;
                toUnmask.addAll(modelElement.getConstraintDefinition());
            }
        
            // Layout all constraints in column
            RelativeRectangle bounds = getBounds(viewer, gmModel);
            RelativePoint initialPosition = new RelativePoint(bounds.ref, bounds.bounds.x + bounds.bounds.width + UnmaskManager.HORIZONTAL_OFFSET / 2, bounds.bounds.y);
        
            layoutVertical(initialPosition, viewer, toUnmask);
        }
        
    }

    /**
     * Unmask all non structuring links around the element.
     * @param viewer The viewer to unmask elements into.
     * @param gmModel the gmModel representing the model element to work from.
     * @param unmaskNewNodes indicates if new nodes could be unmasked.
     */
    @objid ("665b5f25-33f7-11e2-95fe-001ec947c8cc")
    public void unmaskNonStructuringLinks(final EditPartViewer viewer, final GmModel gmModel, final boolean unmaskNewNodes) {
        MObject elt = gmModel.getRepresentedElement();
        if (elt != null) {
            IGmDiagram diagram = (IGmDiagram) viewer.getRootEditPart().getContents().getModel();
        
            Set<MObject> unmaskedElements = unmaskNewNodes ? null : getUnmaskedElements(diagram);
        
            LinkPositionSet linkPositionSet = new LinkPositionSet(elt, false, unmaskedElements);
        
            RelativeRectangle bounds = getBounds(viewer, gmModel);
        
            unmaskLinkPositionSet(viewer, linkPositionSet, bounds);
        }
        
    }

    /**
     * Unmask all notes in a column, next to the given element, on the left.<br>
     * The represented element must be an ModelElement.
     * @param viewer the viewer to unmask the elements on.
     * @param gmModel the gmModel representing the model element to work from.
     */
    @objid ("665b5f2e-33f7-11e2-95fe-001ec947c8cc")
    public void unmaskNotes(final EditPartViewer viewer, final GmModel gmModel) {
        MObject elt = gmModel.getRepresentedElement();
        if (elt instanceof ModelElement) {
            List<MObject> toUnmask = new ArrayList<>();
        
            // Get all notes
            ModelElement modelElement = (ModelElement) elt;
            toUnmask.addAll(modelElement.getDescriptor());
            toUnmask.addAll(modelElement.getAttached(Document.class));
        
            // Layout all notes in column
            RelativeRectangle bounds = getBounds(viewer, gmModel);
            RelativePoint initialPosition = new RelativePoint(bounds.ref, bounds.bounds.x - UnmaskManager.HORIZONTAL_OFFSET, bounds.bounds.y);
        
            layoutVertical(initialPosition, viewer, toUnmask);
        }
        
    }

    /**
     * Unmask parent generalizations and realizations.<br>
     * The represented element must be an NameSpace.
     * @param viewer the viewer to unmask the elements on.
     * @param gmModel the gmModel representing the model element to work from.
     */
    @objid ("665b5f35-33f7-11e2-95fe-001ec947c8cc")
    public void unmaskParentLink(final EditPartViewer viewer, final GmModel gmModel) {
        MObject elt = gmModel.getRepresentedElement();
        if (elt instanceof NameSpace) {
            List<MObject> toUnmask = new ArrayList<>();
        
            // Get all parent generalizations
            NameSpace ns = (NameSpace) elt;
            for (Generalization link : ns.getParent()) {
                toUnmask.add(link);
            }
        
            // Get all parent realizations
            for (InterfaceRealization link : ns.getRealized()) {
                toUnmask.add(link);
            }
        
            // Layout all links in a line
            RelativeRectangle bounds = getBounds(viewer, gmModel);
            RelativePoint initialPosition = new RelativePoint(
                    bounds.ref,
                    bounds.bounds.x + (bounds.bounds.width / 2)
                    - (UnmaskManager.HORIZONTAL_OFFSET * (toUnmask.size() - 1) / 2)
                    - UnmaskManager.HORIZONTAL_OFFSET / 2,
                    bounds.bounds.y - UnmaskManager.VERTICAL_OFFSET * 2);
        
            layoutHorizontal(initialPosition, viewer, toUnmask);
        }
        
    }

    /**
     * Unmask all structuring links around the element.
     * @param viewer The viewer to unmask elements into.
     * @param gmModel the gmModel representing the model element to work from.
     * @param unmaskNewNodes indicates if new nodes could be unmasked.
     */
    @objid ("665b5f3c-33f7-11e2-95fe-001ec947c8cc")
    public void unmaskStructuringLinks(final EditPartViewer viewer, final GmModel gmModel, final boolean unmaskNewNodes) {
        MObject elt = gmModel.getRepresentedElement();
        if (elt != null) {
            IGmDiagram diagram = (IGmDiagram) viewer.getRootEditPart().getContents().getModel();
        
            Set<MObject> unmaskedElements = unmaskNewNodes ? null : getUnmaskedElements(diagram);
        
            LinkPositionSet linkPositionSet = new LinkPositionSet(elt, true, unmaskedElements);
        
            RelativeRectangle bounds = getBounds(viewer, gmModel);
        
            unmaskLinkPositionSet(viewer, linkPositionSet, bounds);
        }
        
    }

    /**
     * Return the element location and size as a Rectangle.
     * @return the element bounds.
     */
    @objid ("665b5f45-33f7-11e2-95fe-001ec947c8cc")
    private RelativeRectangle getBounds(final EditPartViewer viewer, final GmModel gmModel) {
        GraphicalEditPart p = (GraphicalEditPart) viewer.getEditPartRegistry().get(gmModel);
        Rectangle bounds;
        IFigure figure = p.getFigure();
        
        if (figure instanceof HandleBounds) {
            bounds = ((HandleBounds) figure).getHandleBounds().getCopy();
        } else {
            bounds = figure.getBounds().getCopy();
        }
        return new RelativeRectangle(bounds, figure);
    }

    /**
     * @param diagram The diagram to check.
     * @return Returns the list of all MObject unmasked in this diagram.
     */
    @objid ("665b5f4d-33f7-11e2-95fe-001ec947c8cc")
    private Set<MObject> getUnmaskedElements(final IGmDiagram diagram) {
        Set<MObject> ret = new HashSet<>();
        for (GmModel gm : diagram.getAllModels()) {
            MObject representedElement = gm.getRepresentedElement();
            if (representedElement != null) {
                ret.add(representedElement);
            }
        }
        return ret;
    }

    /**
     * Unmasks all elements in a line.
     * @param initialPosition The upper left corner of the unmask zone.
     * @param viewer The viewer to unmask elements into.
     * @param toUnmask List of all elements to unmask.
     * @return the unmasked element line's bounds.
     */
    @objid ("665dc174-33f7-11e2-95fe-001ec947c8cc")
    private RelativeRectangle layoutHorizontal(final RelativePoint initialPosition, final EditPartViewer viewer, final Collection<MObject> toUnmask) {
        RelativePoint unmaskPosition = new RelativePoint(initialPosition);
        
        for (MObject link : toUnmask) {
            unmask(viewer, link, unmaskPosition);
            unmaskPosition.point.x += UnmaskManager.HORIZONTAL_OFFSET;
        }
        return new RelativeRectangle(initialPosition.ref,
                initialPosition.point.x,
                        initialPosition.point.y,
                        UnmaskManager.HORIZONTAL_OFFSET * toUnmask.size(),
                        UnmaskManager.VERTICAL_OFFSET);
        
    }

    /**
     * Unmask all elements in a column.
     * @param initialPosition The upper left corner of the unmask zone.
     * @param viewer The viewer to unmask elements into.
     * @param toUnmask List of all elements to unmask.
     * @return the unmasked element column's bounds.
     */
    @objid ("665dc181-33f7-11e2-95fe-001ec947c8cc")
    private RelativeRectangle layoutVertical(final RelativePoint initialPosition, final EditPartViewer viewer, final Collection<MObject> toUnmask) {
        RelativePoint unmaskPosition = new RelativePoint(initialPosition);
        
        for (MObject link : toUnmask) {
            unmask(viewer, link, unmaskPosition);
            unmaskPosition.point.y += UnmaskManager.VERTICAL_OFFSET;
        }
        return new RelativeRectangle(
                initialPosition.ref,
                initialPosition.point.x,
                initialPosition.point.y,
                UnmaskManager.HORIZONTAL_OFFSET,
                UnmaskManager.VERTICAL_OFFSET * toUnmask.size());
        
    }

    /**
     * Unmask an element in this viewer at the given coordinates.<br>
     * Uses a ModelElementDropRequest, to emulate a standard drag & drop of the element.
     * @param x the x coordinate for the unmasking location.
     * @param y the y coordinate for the unmasking location.
     * @param viewer the viewer to unmask the element on.
     * @param element the element to unmask.
     */
    @objid ("665dc18e-33f7-11e2-95fe-001ec947c8cc")
    private void unmask(final EditPartViewer viewer, final MObject element, final RelativePoint pos) {
        Point dropLocation = pos.point.getCopy();
        pos.ref.translateToAbsolute(dropLocation);
        
        final ModelElementDropRequest req = new ModelElementDropRequest();
        req.setDroppedElements(new MObject[] { element });
        req.setLocation(dropLocation);
        
        EditPart targetEditPart = viewer.findObjectAtExcluding(dropLocation,
                Collections.EMPTY_LIST,
                new EditPartViewer.Conditional() {
                    @Override
                    public boolean evaluate(EditPart editpart) {
                        return editpart.getTargetEditPart(req) != null;
                    }
                });
        
        targetEditPart = targetEditPart.getTargetEditPart(req);
        if (targetEditPart != null) {
        
            Command com = targetEditPart.getCommand(req);
            if (com != null && com.canExecute()) {
                targetEditPart.getViewer().getEditDomain().getCommandStack().execute(com);
            }
        }
        
    }

    @objid ("665dc199-33f7-11e2-95fe-001ec947c8cc")
    private void unmaskLinkPositionSet(final EditPartViewer viewer, final LinkPositionSet linkPositionSet, final RelativeRectangle relBounds) {
        Rectangle bounds = relBounds.bounds;
        
        int baseHorizontaloffset = UnmaskManager.HORIZONTAL_OFFSET * (linkPositionSet.getBottomLinks().size() - 1) / 2;
        
        // Layout top line
        RelativePoint topInitialPosition = new RelativePoint(
                relBounds.ref,
                bounds.x + (bounds.width / 2) - baseHorizontaloffset - UnmaskManager.HORIZONTAL_OFFSET / 2,
                bounds.y + bounds.height - UnmaskManager.VERTICAL_OFFSET * 2);
        
        RelativeRectangle topBounds = layoutHorizontal(topInitialPosition, viewer, linkPositionSet.getTopLinks());
        
        // Layout bottom line
        RelativePoint bottomInitialPosition = new RelativePoint(
                relBounds.ref,
                bounds.x + (bounds.width / 2) - baseHorizontaloffset,
                bounds.y + bounds.height + UnmaskManager.VERTICAL_OFFSET);
        
        RelativeRectangle bottomBounds = layoutHorizontal(bottomInitialPosition,
                viewer,
                linkPositionSet.getBottomLinks());
        
        // Layout right column
        RelativePoint rightInitialPosition = new RelativePoint(
                relBounds.ref,
                Math.max(topBounds.bounds.x + topBounds.bounds.width, bottomBounds.bounds.x + bottomBounds.bounds.width),
                bounds.y);
        layoutVertical(rightInitialPosition, viewer, linkPositionSet.getRightLinks());
        
        // Layout left column
        RelativePoint leftInitialPosition = new RelativePoint(
                relBounds.ref,
                Math.min(topBounds.bounds.x, bottomBounds.bounds.x) - UnmaskManager.HORIZONTAL_OFFSET,
                bounds.y);
        layoutVertical(leftInitialPosition, viewer, linkPositionSet.getLeftLinks());
        
    }

    /**
     * Describe a Point and the figure its coordinate are relative to.
     * @since 5.1
     */
    @objid ("c57d4060-e032-467b-8267-3e55e1db13b3")
    private static class RelativePoint {
        @objid ("cb7cca1c-4258-431a-9130-951c388140d0")
        public final IFigure ref;

        @objid ("8e63e7b1-44b1-47f5-95de-e87e2c329c52")
        public final Point point;

        @objid ("dee9f0f4-d7d6-4dc8-85a9-382ef5b40820")
        public  RelativePoint(IFigure ref, int x, int y) {
            this.point = new Point(x,y);
            this.ref = ref;
            
        }

        @objid ("e5ddd742-1987-4c60-996d-9cdbcf24542b")
        public  RelativePoint(RelativePoint other) {
            this.ref = other.ref;
            this.point = other.point.getCopy();
            
        }

    }

    /**
     * Describe a rectangle and the figure its coordinate are relative to.
     * @since 5.1
     */
    @objid ("0783898c-cc61-42ea-964c-65b4f816b965")
    private static class RelativeRectangle {
        @objid ("c1744c6c-4936-48c3-a501-b525b587c064")
        public final Rectangle bounds;

        @objid ("953b54a8-20c7-44dd-a91b-dc6dba9f08c2")
        public final IFigure ref;

        @objid ("7b083d77-35e5-47c2-a8d2-c2641713a4e0")
        public  RelativeRectangle(Rectangle bounds, IFigure ref) {
            this.bounds = bounds.getCopy();
            this.ref = ref;
            
        }

        @objid ("3f261a3a-b628-492c-93fc-1f5a7112f8fa")
        public  RelativeRectangle(IFigure ref, int x, int y, int w, int h) {
            this.ref = ref;
            this.bounds = new Rectangle(x, y, w, h);
            
        }

    }

}
