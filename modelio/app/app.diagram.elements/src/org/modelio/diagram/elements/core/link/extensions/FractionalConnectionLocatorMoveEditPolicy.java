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
package org.modelio.diagram.elements.core.link.extensions;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.AbstractConnectionAnchor;
import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.tools.DragEditPartsTracker;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.policies.SelectionHandlesBuilder;

/**
 * Edit policy that allows connection extension labels to be moved.
 * <p>
 * Can handle any figure that use a FractionalConnectionLocator as layout constraint.
 */
@objid ("7fff5d8b-1dec-11e2-8cad-001ec947c8cc")
public class FractionalConnectionLocatorMoveEditPolicy extends ResizableEditPolicy {
    @objid ("673b7aab-1e83-11e2-8cad-001ec947c8cc")
    private PolylineConnection focuslink = null;

    @objid ("7fff5d92-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean understandsRequest(Request request) {
        if (REQ_RESIZE.equals(request.getType())) {
            return true;
        }
        if (REQ_MOVE.equals(request.getType())) {
            return isDragAllowed();
        }
        return false;
    }

    @objid ("7fff5d9a-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getMoveCommand(ChangeBoundsRequest request) {
        final IFigure extension = ((GraphicalEditPart) getHost()).getFigure();
        final Connection connection = (Connection) extension.getParent();
        final Point moveDelta0 = request.getMoveDelta();
        
        final FractionalConnectionLocator oldLoc = (FractionalConnectionLocator) connection.getLayoutManager()
                .getConstraint(extension);
        
        final ChangeExtensionLocationCommand cmd = new ChangeExtensionLocationCommand();
        
        final Point figLocation = oldLoc.getLocation(extension);
        extension.translateToAbsolute(figLocation);
        figLocation.translate(moveDelta0);
        connection.translateToRelative(figLocation);
        
        final FractionalConnectionLocator newLoc = FractionalConnectionLocator.createFromXyPoint(connection,
                oldLoc.getFraction(),
                figLocation,
                oldLoc.isTowardTarget());
        
        final GmNodeModel gmExtension = (GmNodeModel) getHost().getModel();
        final IGmLocator oldGmLoc = gmExtension.getParentLink().getLayoutContraint(gmExtension);
        final GmFractionalConnectionLocator newconstraint = new GmFractionalConnectionLocator();
        
        newconstraint.setFraction(oldLoc.getFraction());
        newconstraint.setTowardTarget(oldLoc.isTowardTarget());
        newconstraint.setUDistance(newLoc.getUDistance());
        newconstraint.setVDistance(newLoc.getVDistance());
        
        newconstraint.setWidthConstraint(oldGmLoc.getWidthConstraint());
        newconstraint.setHeightConstraint(oldGmLoc.getHeightConstraint());
        
        cmd.setConstraint(newconstraint);
        
        cmd.setModel(gmExtension);
        return cmd;
    }

    @objid ("7fff5da4-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void showSelection() {
        super.showSelection();
        
        final IFigure extension = ((GraphicalEditPart) getHost()).getFigure();
        final Connection connection = (Connection) extension.getParent();
        final FractionalConnectionLocator loc = (FractionalConnectionLocator) connection.getLayoutManager()
                .getConstraint(extension);
        
        this.focuslink = new PolylineConnection();
        this.focuslink.removeAllPoints();
        ConnectionAnchor srcAnchor = new ChopboxAnchor(extension);
        ConnectionAnchor targetAnchor = new LocatorAnchor(connection, loc);
        this.focuslink.setSourceAnchor(srcAnchor);
        this.focuslink.setTargetAnchor(targetAnchor);
        this.focuslink.setLineStyle(org.eclipse.swt.SWT.LINE_DOT);
        
        addFeedback(this.focuslink);
        
    }

    @objid ("7fff5da7-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void hideSelection() {
        super.hideSelection();
        if (this.focuslink != null) {
            removeFeedback(this.focuslink);
            this.focuslink = null;
        }
        
    }

    /**
     * Redefined to set feedback figure size as at least minimum size.
     */
    @objid ("f447b74d-8f99-49e5-b951-5ab7ee8c2846")
    @Override
    protected void showChangeBoundsFeedback(ChangeBoundsRequest request) {
        if (REQ_RESIZE.equals(request.getType())) {
            // It is a resize
            IFigure feedback = getDragSourceFeedbackFigure();
            IFigure hostFigure = getHostFigure();
            ChangeExtensionSizeCommand.showFeedback(request, feedback, hostFigure);
        
        } else {
            super.showChangeBoundsFeedback(request);
        }
        
    }

    /**
     * Default constructor.
     */
    @objid ("9210a3c6-209a-436c-af87-cadc7ab53da3")
    public  FractionalConnectionLocatorMoveEditPolicy() {
        // setResizeDirections(PositionConstants.EAST_WEST);
    }

    /**
     * Redefined to ask the moved edit part its preferred move drag tracker.
     */
    @objid ("0af3b9a2-6069-4d18-ba89-6d67d5ef002d")
    @Override
    protected DragEditPartsTracker getDragTracker() {
        DragTracker dt = getHost().getDragTracker(new ChangeBoundsRequest(REQ_MOVE));
        
        if (dt instanceof DragEditPartsTracker) {
            return (DragEditPartsTracker) dt;
        }
        return super.getDragTracker();
    }

    @objid ("cef7c36a-5626-4a7e-b13d-3f73d5c162b8")
    @Override
    protected List createSelectionHandles() {
        return new SelectionHandlesBuilder((GraphicalEditPart) getHost())
                .withDragAllowed(isDragAllowed())
                .withResizeDirections(getResizeDirections())
                .withMoveDragTracker(getDragTracker())
                .addResizeableHandles()
                .getHandles();
        
    }

    /**
     * Anchor whose location is given by a provided Locator.
     */
    @objid ("7fff5daa-1dec-11e2-8cad-001ec947c8cc")
    private static class LocatorAnchor extends AbstractConnectionAnchor {
        @objid ("7fff5daf-1dec-11e2-8cad-001ec947c8cc")
        private FractionalConnectionLocator loc;

        @objid ("7fff5db0-1dec-11e2-8cad-001ec947c8cc")
        public  LocatorAnchor(final IFigure owner, final FractionalConnectionLocator loc) {
            super(owner);
            this.loc = loc;
            
        }

        @objid ("7fff5db8-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public Point getLocation(final Point reference) {
            return this.loc.getReferencePoint();
        }

        @objid ("7fff5dc3-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public Point getReferencePoint() {
            return this.loc.getReferencePoint();
        }

    }

}
