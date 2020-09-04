/* 
 * Copyright 2013-2019 Modeliosoft
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
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
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
 * Can handle any figure that use a ConnectionEndpointLocator as layout constraint.
 */
@objid ("7ff5d416-1dec-11e2-8cad-001ec947c8cc")
public class ConnectionEndpoinLocatorMoveEditPolicy extends ResizableEditPolicy {
    @objid ("7ff5d41a-1dec-11e2-8cad-001ec947c8cc")
    private static LocatorFactory f = LocatorFactory.getInstance();

    @objid ("663ec167-1e83-11e2-8cad-001ec947c8cc")
    private PolylineConnection focuslink = null;

    @objid ("7ff5d41e-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean understandsRequest(Request request) {
        if (REQ_RESIZE.equals(request.getType())) {
            return true;
        } else if (REQ_MOVE.equals(request.getType())) {
            return isDragAllowed();
        } else {
            return false;
        }
    }

    @objid ("7ff5d426-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getMoveCommand(ChangeBoundsRequest request) {
        final IFigure extension = ((GraphicalEditPart) getHost()).getFigure();
        final Connection connection = (Connection) extension.getParent();
        final Point moveDelta0 = request.getMoveDelta();
        final Dimension moveDelta = new Dimension(moveDelta0.x, moveDelta0.y);
        
        final SidedConnectionEndpointLocator newLoc = f.getLocator(connection,
                                                                   extension,
                                                                   moveDelta,
                                                                   request.getLocation());
        
        final ChangeExtensionLocationCommand cmd = new ChangeExtensionLocationCommand();
        
        final GmNodeModel gmExtension = (GmNodeModel) getHost().getModel();
        final IGmLocator oldGmLoc = gmExtension.getParentLink().getLayoutContraint(gmExtension);
        
        GmConnectionEndpoinLocator newconstraint = new GmConnectionEndpoinLocator();
        newconstraint.setEnd(newLoc.isEnd());
        newconstraint.setUDistance(newLoc.getUDistance());
        newconstraint.setVDistance(newLoc.getVDistance());
        newconstraint.setWidthConstraint(oldGmLoc.getWidthConstraint());
        newconstraint.setHeightConstraint(oldGmLoc.getHeightConstraint());
        
        cmd.setConstraint(newconstraint);
        cmd.setModel((GmNodeModel) getHost().getModel());
        return cmd;
    }

    @objid ("7ff8365e-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void showSelection() {
        super.showSelection();
        
        final IFigure extension = ((GraphicalEditPart) getHost()).getFigure();
        final Connection connection = (Connection) extension.getParent();
        final SidedConnectionEndpointLocator loc = (SidedConnectionEndpointLocator) connection.getLayoutManager()
                                                                                              .getConstraint(extension);
        
        this.focuslink = new PolylineConnection();
        ConnectionAnchor srcAnchor = new ChopboxAnchor(extension);
        ConnectionAnchor targetAnchor = new LocatorAnchor(connection, loc);
        this.focuslink.setSourceAnchor(srcAnchor);
        this.focuslink.setTargetAnchor(targetAnchor);
        this.focuslink.setLineStyle(org.eclipse.swt.SWT.LINE_DOT);
        addFeedback(this.focuslink);
    }

    @objid ("7ff83661-1dec-11e2-8cad-001ec947c8cc")
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
    @objid ("59045252-c37f-466c-b6c0-2befef935adb")
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
    @objid ("eda7f155-66ed-4657-938f-4b5195de4cdd")
    public ConnectionEndpoinLocatorMoveEditPolicy() {
        setResizeDirections(PositionConstants.EAST_WEST);
    }

    /**
     * Redefined to ask the moved edit part its preferred move drag tracker.
     */
    @objid ("65984cb3-f49a-4cea-8030-3c989eb8b63e")
    @Override
    protected DragEditPartsTracker getDragTracker() {
        DragTracker dt = getHost().getDragTracker(new ChangeBoundsRequest(REQ_MOVE));
        
        if (dt instanceof DragEditPartsTracker) {
            return (DragEditPartsTracker) dt;
        }
        return super.getDragTracker();
    }

    @objid ("5283f5c2-d470-45dc-8aa3-64a354ed29b9")
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
    @objid ("7ff83664-1dec-11e2-8cad-001ec947c8cc")
    private static class LocatorAnchor extends AbstractConnectionAnchor {
        @objid ("7ff83669-1dec-11e2-8cad-001ec947c8cc")
        private FractionalConnectionLocator loc;

        @objid ("7ff8366a-1dec-11e2-8cad-001ec947c8cc")
        public LocatorAnchor(final IFigure owner, final SidedConnectionEndpointLocator loc) {
            super(owner);
            if (loc.isEnd()) {
                this.loc = new FractionalConnectionLocator((Connection) owner, 0.9, false);
            } else {
                this.loc = new FractionalConnectionLocator((Connection) owner, 0.1, false);
            }
        }

        @objid ("7ff83672-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public Point getLocation(final Point reference) {
            return this.loc.getReferencePoint();
        }

        @objid ("7ff8367d-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public Point getReferencePoint() {
            return this.loc.getReferencePoint();
        }

    }

}
