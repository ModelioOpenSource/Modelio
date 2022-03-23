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
package org.modelio.diagram.elements.core.policies;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.AbsoluteBendpoint;
import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.EllipseAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.handles.MoveHandle;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.swt.SWT;
import org.modelio.diagram.elements.core.figures.EllipseFigure;
import org.modelio.diagram.elements.core.figures.IClonableFigure;
import org.modelio.diagram.elements.core.helpers.RequestHelper;
import org.modelio.diagram.elements.plugin.DiagramElements;

/**
 * A specialization of {@link DefaultNodeResizableEditPolicy} that forbids resize and show only one handle.
 * <p>
 * Moving the node will show a ghost for the node and all links connected to the node.
 * <p>
 * To be used one small figures that are not resizeable.
 */
@objid ("80d85371-1dec-11e2-8cad-001ec947c8cc")
public class SmallNodeNonResizeableEditPolicy extends DefaultNodeResizableEditPolicy {
    @objid ("cbc20e86-0992-43d0-ab82-f3f653cfb190")
    protected List<IFigure> feedbackFigures = new ArrayList<>();

    /**
     * Initialize the policy.
     */
    @objid ("80d85378-1dec-11e2-8cad-001ec947c8cc")
    public  SmallNodeNonResizeableEditPolicy() {
        super();
    }

    /**
     * Redefined to make a copy of the dragged node and all connections going from or to him.
     */
    @objid ("80d8537b-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected IFigure createDragSourceFeedbackFigure() {
        IFigure fig = getHostFigure();
        try {
            final IFigure feed = clone(fig);
            feed.getBounds().setBounds(fig.getBounds());
        
            if (feed instanceof Shape) {
                ((Shape) feed).setLineStyle(SWT.LINE_DASH);
            }
        
            getFeedbackLayer().add(feed);
        
            final GraphicalEditPart host = (GraphicalEditPart) getHost();
            for (Object c : host.getSourceConnections()) {
                final GraphicalEditPart connPart = (GraphicalEditPart) c;
                final Connection origConn = (Connection) connPart.getFigure();
                final Connection connFig = origConn.getClass().newInstance();
                connFig.setSourceAnchor(createFeedbackFigureAnchor(feed));
                connFig.setTargetAnchor(origConn.getTargetAnchor());
        
                connFig.setConnectionRouter(origConn.getConnectionRouter());
                connFig.setRoutingConstraint(cloneRoutingConstraint(origConn));
        
                if (connFig instanceof Shape) {
                    ((Shape) connFig).setLineStyle(SWT.LINE_DASH);
                }
        
                this.feedbackFigures.add(connFig);
                getFeedbackLayer().add(connFig);
        
            }
        
            for (Object c : host.getTargetConnections()) {
                final GraphicalEditPart connPart = (GraphicalEditPart) c;
                final Connection origConn = (Connection) connPart.getFigure();
                final Connection connFig = origConn.getClass().newInstance();
        
                connFig.setSourceAnchor(origConn.getSourceAnchor());
                connFig.setTargetAnchor(createFeedbackFigureAnchor(feed));
        
                connFig.setConnectionRouter(origConn.getConnectionRouter());
                connFig.setRoutingConstraint(cloneRoutingConstraint(origConn));
        
                if (connFig instanceof Shape) {
                    ((Shape) connFig).setLineStyle(SWT.LINE_DASH);
                }
        
                this.feedbackFigures.add(connFig);
                getFeedbackLayer().add(connFig);
            }
        
            return feed;
        
        } catch (InstantiationException | IllegalAccessException e) {
            DiagramElements.LOG.error(e);
            return super.createDragSourceFeedbackFigure();
        }
        
    }

    @objid ("3cec539f-1ca4-46e9-a945-0c751fe94744")
    protected ConnectionAnchor createFeedbackFigureAnchor(IFigure feedbackFigure) {
        if (feedbackFigure instanceof EllipseFigure) {
            return new EllipseAnchor(feedbackFigure);
        } else {
            return new ChopboxAnchor(feedbackFigure);
        }
        
    }

    /**
     * Make best effort to make a deep copy of the routing constraint
     * @param conn a connection with routing constraint
     * @return a clone or the original constraint.
     */
    @objid ("42b4eb1f-4dc5-4a2a-8b1e-ea35be83842a")
    protected Object cloneRoutingConstraint(Connection conn) {
        Object routingConstraint = conn.getRoutingConstraint();
        if (routingConstraint instanceof List) {
            ArrayList<Object> l = new ArrayList<>((List<?>) routingConstraint);
            for (int i = 0; i < l.size(); i++) {
                Object o = l.get(i);
                if (o instanceof AbsoluteBendpoint) {
                    AbsoluteBendpoint s = (AbsoluteBendpoint) o;
                    l.set(i, new AbsoluteBendpoint(s));
                } else if (o instanceof Point) {
                    l.set(i, new Point((Point) o));
                }
            }
            return l;
        }
        return routingConstraint;
    }

    /**
     * Create a single rectangular handle that fits the entire figure.
     */
    @objid ("80d85382-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected List<?> createSelectionHandles() {
        final List<Object> ret = new ArrayList<>(1);
        ret.add(new MoveHandle((GraphicalEditPart) getHost()));
        
        SelectionHandlesBuilder.disableHandlesIfReadOnly(getHost(), ret);
        return ret;
    }

    @objid ("80d8538a-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void eraseChangeBoundsFeedback(final ChangeBoundsRequest request) {
        super.eraseChangeBoundsFeedback(request);
        
        removeFeedbacks();
        
    }

    @objid ("80d85391-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getResizeCommand(final ChangeBoundsRequest request) {
        ChangeBoundsRequest req = new ChangeBoundsRequest(REQ_RESIZE_CHILDREN);
        req.setEditParts(getHost());
        
        req.setMoveDelta(request.getMoveDelta());
        // Ignore actual resize request: node is not really resizeable.
        req.setSizeDelta(computeSizeDelta());
        req.setLocation(request.getLocation());
        req.setExtendedData(request.getExtendedData());
        req.setResizeDirection(request.getResizeDirection());
        RequestHelper.addSharedEditParts(req, request);
        return getHost().getParent().getCommand(req);
    }

    @objid ("80d8539c-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void showChangeBoundsFeedback(final ChangeBoundsRequest request) {
        super.showChangeBoundsFeedback(request);
    }

    /**
     * Compute the delta from the current size to the size fixed by the figure preferred size.
     * @return the delta to the preferred size.
     */
    @objid ("80d853a3-1dec-11e2-8cad-001ec947c8cc")
    private Dimension computeSizeDelta() {
        final IFigure fig = getHostFigure();
        return fig.getPreferredSize().getShrinked(fig.getBounds().getSize());
    }

    @objid ("4ec10b22-75b7-422c-997e-9b1db2002a0d")
    private IFigure clone(IFigure fig) throws InstantiationException, IllegalAccessException {
        if (fig instanceof IClonableFigure) {
            return ((IClonableFigure) fig).getCopy();
        } else {
            return fig.getClass().newInstance();
        }
        
    }

    @objid ("66263d23-6f31-4af7-8696-70b61868352f")
    private void removeFeedbacks() {
        for (IFigure f : this.feedbackFigures) {
            f.getParent().remove(f);
        }
        this.feedbackFigures.clear();
        
    }

    @objid ("9a2c6e27-9288-4cc6-847a-70302477ffe5")
    @Override
    public void deactivate() {
        removeFeedbacks();
        
        super.deactivate();
        
    }

}
