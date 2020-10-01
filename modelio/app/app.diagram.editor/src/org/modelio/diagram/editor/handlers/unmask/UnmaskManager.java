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
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.handles.HandleBounds;
import org.modelio.diagram.elements.common.root.ScalableFreeformRootEditPart2;
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
     * 
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
            Rectangle bounds = getBounds(viewer, gmModel);
            Point initialPosition = new Point(bounds.x +
                    (bounds.width / 2) -
                    (UnmaskManager.HORIZONTAL_OFFSET * (toUnmask.size() - 1) / 2), bounds.y +
                            bounds.height +
                            UnmaskManager.VERTICAL_OFFSET);
            ((ScalableFreeformRootEditPart2) viewer.getRootEditPart()).getFigure()
                    .translateToParent(initialPosition);
        
            layoutHorizontal(initialPosition, viewer, toUnmask);
        
        }
    }

    /**
     * Unmask all constraints in a column, next to the given element, on the right.<br>
     * The represented element must be an ModelElement.
     * 
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
            Rectangle bounds = getBounds(viewer, gmModel);
            Point initialPosition = new Point(bounds.x + bounds.width + UnmaskManager.HORIZONTAL_OFFSET / 2, bounds.y);
            ((ScalableFreeformRootEditPart2) viewer.getRootEditPart()).getFigure()
                    .translateToParent(initialPosition);
        
            layoutVertical(initialPosition, viewer, toUnmask);
        }
    }

    /**
     * Unmask all non structuring links around the element.
     * 
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
        
            Rectangle bounds = getBounds(viewer, gmModel);
        
            unmaskLinkPositionSet(viewer, linkPositionSet, bounds);
        }
    }

    /**
     * Unmask all notes in a column, next to the given element, on the left.<br>
     * The represented element must be an ModelElement.
     * 
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
            Rectangle bounds = getBounds(viewer, gmModel);
            Point initialPosition = new Point(bounds.x - UnmaskManager.HORIZONTAL_OFFSET, bounds.y);
            ((ScalableFreeformRootEditPart2) viewer.getRootEditPart()).getFigure()
                    .translateToParent(initialPosition);
        
            layoutVertical(initialPosition, viewer, toUnmask);
        }
    }

    /**
     * Unmask parent generalizations and realizations.<br>
     * The represented element must be an NameSpace.
     * 
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
            Rectangle bounds = getBounds(viewer, gmModel);
            Point initialPosition = new Point(bounds.x +
                    (bounds.width / 2) -
                    (UnmaskManager.HORIZONTAL_OFFSET * (toUnmask.size() - 1) / 2) -
                    UnmaskManager.HORIZONTAL_OFFSET /
                            2,
                    bounds.y - UnmaskManager.VERTICAL_OFFSET * 2);
            ((ScalableFreeformRootEditPart2) viewer.getRootEditPart()).getFigure()
                    .translateToParent(initialPosition);
        
            layoutHorizontal(initialPosition, viewer, toUnmask);
        }
    }

    /**
     * Unmask all structuring links around the element.
     * 
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
        
            Rectangle bounds = getBounds(viewer, gmModel);
        
            unmaskLinkPositionSet(viewer, linkPositionSet, bounds);
        }
    }

    /**
     * Return the element location and size as a Rectangle.
     * 
     * @return the element bounds.
     */
    @objid ("665b5f45-33f7-11e2-95fe-001ec947c8cc")
    private Rectangle getBounds(final EditPartViewer viewer, final GmModel gmModel) {
        GraphicalEditPart p = (GraphicalEditPart) viewer.getEditPartRegistry().get(gmModel);
        if (p.getFigure() instanceof HandleBounds) {
            return ((HandleBounds) p.getFigure()).getHandleBounds().getCopy();
        } else {
            return p.getFigure().getBounds().getCopy();
        }
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
     * 
     * @param initialPosition The upper left corner of the unmask zone.
     * @param viewer The viewer to unmask elements into.
     * @param toUnmask List of all elements to unmask.
     * @return the unmasked element line's bounds.
     */
    @objid ("665dc174-33f7-11e2-95fe-001ec947c8cc")
    private Rectangle layoutHorizontal(final Point initialPosition, final EditPartViewer viewer, final Collection<MObject> toUnmask) {
        Point unmaskPosition = new Point(initialPosition);
        
        for (MObject link : toUnmask) {
            unmask(viewer, link, unmaskPosition.x, unmaskPosition.y);
            unmaskPosition.x += UnmaskManager.HORIZONTAL_OFFSET;
        }
        return new Rectangle(initialPosition.x,
                        initialPosition.y,
                        UnmaskManager.HORIZONTAL_OFFSET * toUnmask.size(),
                        UnmaskManager.VERTICAL_OFFSET);
    }

    /**
     * Unmask all elements in a column.
     * 
     * @param initialPosition The upper left corner of the unmask zone.
     * @param viewer The viewer to unmask elements into.
     * @param toUnmask List of all elements to unmask.
     * @return the unmasked element column's bounds.
     */
    @objid ("665dc181-33f7-11e2-95fe-001ec947c8cc")
    private Rectangle layoutVertical(final Point initialPosition, final EditPartViewer viewer, final Collection<MObject> toUnmask) {
        Point unmaskPosition = new Point(initialPosition);
        
        for (MObject link : toUnmask) {
            unmask(viewer, link, unmaskPosition.x, unmaskPosition.y);
            unmaskPosition.y += UnmaskManager.VERTICAL_OFFSET;
        }
        return new Rectangle(initialPosition.x, initialPosition.y, UnmaskManager.HORIZONTAL_OFFSET, UnmaskManager.VERTICAL_OFFSET *
                        toUnmask.size());
    }

    /**
     * Unmask an element in this viewer at the given coordinates.<br>
     * Uses a ModelElementDropRequest, to emulate a standard drag & drop of the element.
     * 
     * @param viewer the viewer to unmask the element on.
     * @param element the element to unmask.
     * @param x the x coordinate for the unmasking location.
     * @param y the y coordinate for the unmasking location.
     */
    @objid ("665dc18e-33f7-11e2-95fe-001ec947c8cc")
    private void unmask(final EditPartViewer viewer, final MObject element, final int x, final int y) {
        Point dropLocation = new Point(x, y);
        
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
    private void unmaskLinkPositionSet(final EditPartViewer viewer, final LinkPositionSet linkPositionSet, final Rectangle bounds) {
        // Layout top line
        Point topInitialPosition = new Point(bounds.x +
                (bounds.width / 2) -
                (UnmaskManager.HORIZONTAL_OFFSET *
                        (linkPositionSet.getBottomLinks().size() - 1) / 2)
                -
                UnmaskManager.HORIZONTAL_OFFSET /
                        2,
                bounds.y + bounds.height - UnmaskManager.VERTICAL_OFFSET * 2);
        ((ScalableFreeformRootEditPart2) viewer.getRootEditPart()).getFigure()
                .translateToParent(topInitialPosition);
        Rectangle topBounds = layoutHorizontal(topInitialPosition, viewer, linkPositionSet.getTopLinks());
        
        // Layout bottom line
        Point bottomInitialPosition = new Point(bounds.x +
                (bounds.width / 2) -
                (UnmaskManager.HORIZONTAL_OFFSET *
                        (linkPositionSet.getBottomLinks().size() - 1) / 2),
                bounds.y + bounds.height + UnmaskManager.VERTICAL_OFFSET);
        ((ScalableFreeformRootEditPart2) viewer.getRootEditPart()).getFigure()
                .translateToParent(bottomInitialPosition);
        Rectangle bottomBounds = layoutHorizontal(bottomInitialPosition,
                viewer,
                linkPositionSet.getBottomLinks());
        
        // Layout right column
        Point rightInitialPosition = new Point(Math.max(topBounds.x + topBounds.width, bottomBounds.x +
                bottomBounds.width),
                bounds.y);
        ((ScalableFreeformRootEditPart2) viewer.getRootEditPart()).getFigure()
                .translateToParent(rightInitialPosition);
        layoutVertical(rightInitialPosition, viewer, linkPositionSet.getRightLinks());
        
        // Layout left column
        Point leftInitialPosition = new Point(Math.min(topBounds.x, bottomBounds.x) - UnmaskManager.HORIZONTAL_OFFSET,
                bounds.y);
        ((ScalableFreeformRootEditPart2) viewer.getRootEditPart()).getFigure()
                .translateToParent(leftInitialPosition);
        layoutVertical(leftInitialPosition, viewer, linkPositionSet.getLeftLinks());
    }

}
