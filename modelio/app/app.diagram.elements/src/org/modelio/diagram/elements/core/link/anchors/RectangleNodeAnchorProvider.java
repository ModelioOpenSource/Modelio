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
package org.modelio.diagram.elements.core.link.anchors;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.XYAnchor;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.modelio.diagram.elements.core.figures.anchors.BorderAnchor;
import org.modelio.diagram.elements.core.figures.anchors.FixedAnchor;
import org.modelio.diagram.elements.core.figures.anchors.NodeAnchor;
import org.modelio.diagram.elements.core.figures.anchors.PointAnchor;
import org.modelio.diagram.elements.core.figures.anchors.RaySlidableAnchor;
import org.modelio.diagram.elements.core.figures.anchors.SatelliteAnchor;
import org.modelio.diagram.elements.core.figures.geometry.GeomUtils;
import org.modelio.diagram.elements.core.link.CreateBendedConnectionRequest;
import org.modelio.diagram.elements.core.link.GmAbstractLinkAnchor;
import org.modelio.diagram.elements.core.link.RoutingModeGetter;
import org.modelio.diagram.elements.core.link.anchors.fixed.VariableFixedAnchorProvider;
import org.modelio.diagram.elements.core.model.IGmLinkObject;
import org.modelio.diagram.elements.core.model.IGmPath;
import org.modelio.diagram.elements.core.requests.CreateLinkConstants;
import org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId;

/**
 * Provides connection anchors for rectangular nodes.
 * <p>
 * <b>Usage:</b><br>
 * <code>
 * srcAnchor = RectangleNodeAnchorProvider.get(connEditPart).getSourceConnectionAnchor(srcNodeEditPart);
 * targetAnchor = RectangleNodeAnchorProvider.get(connEditPart).getSourceConnectionAnchor(targetNodeEditPart);
 * </code>
 * <p>
 * The returned provider should be used then discarded. It is not intended to be cached.
 * 
 * @author cmarin
 */
@objid ("80a17d88-1dec-11e2-8cad-001ec947c8cc")
public class RectangleNodeAnchorProvider extends AbstractNodeAnchorProvider {
    @objid ("6431258a-1c3a-4a8e-a6a3-25c518a7bfa0")
    protected final boolean useSlidable;

    @objid ("80a17d8a-1dec-11e2-8cad-001ec947c8cc")
    private static final RectangleNodeAnchorProvider SLIDABLE_PROVIDER = new RectangleNodeAnchorProvider(true);

    @objid ("19e0e577-4048-426d-8a9e-f702544f22a0")
    private static final RectangleNodeAnchorProvider NONSLIDABLE_PROVIDER = new RectangleNodeAnchorProvider(false);

    @objid ("d150403f-9107-42c7-a874-dd93ec8d8fde")
    private VariableFixedAnchorProvider fixedFallbackProvider = new VariableFixedAnchorProvider();

    /**
     * Factory to get a anchor provider .
     * <p>
     * This is the only way to get an anchor provider. The returned provider should be used then discarded. It is not intended to be cached.
     * @return an anchor provider.
     * @deprecated use {@link #getSlidable()} instead.
     */
    @objid ("80a3dfaa-1dec-11e2-8cad-001ec947c8cc")
    @Deprecated
    public static RectangleNodeAnchorProvider get() {
        return getSlidable();
    }

    /**
     * Factory to get a anchor provider using slidable anchors.
     * <p>
     * This is the only way to get an anchor provider. The returned provider should be used then discarded. It is not intended to be cached.
     * @return an anchor provider.
     */
    @objid ("9682f579-715c-4d80-bfe1-d1c7fe6def8a")
    public static RectangleNodeAnchorProvider getSlidable() {
        return SLIDABLE_PROVIDER;
    }

    /**
     * Factory to get a anchor provider <b>not</b> using slidable anchors.
     * <p>
     * This is the only way to get an anchor provider. The returned provider should be used then discarded. It is not intended to be cached.
     * @return an anchor provider.
     */
    @objid ("aaf56525-359c-49a4-8d78-960739813798")
    public static RectangleNodeAnchorProvider getNonSlidable() {
        return NONSLIDABLE_PROVIDER;
    }

    /**
     * Returns the <code>ConnectionAnchor</code> for the specified <i>isSourceAnchor</i> rectangular node. The NodeEditPart is the {@link ConnectionEditPart#getSource() isSourceAnchor} EditPart for the provider connection.
     * <P>
     * The anchor may be a function of the connection's model, the node's model, a combination of both, or it may not depend on anything all.
     * @param nodeEditPart The rectangular node to anchor from
     * @param connEditpart The connection to anchor from.
     * @return the ConnectionAnchor for the given rectangular EditPart
     */
    @objid ("80a3dfba-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public ConnectionAnchor getSourceConnectionAnchor(final GraphicalEditPart nodeEditPart, final ConnectionEditPart connEditpart) {
        State state = new State(connEditpart);
        if (state.gmSourceAnchor == null) {
            return new ChopboxAnchor(nodeEditPart.getFigure());
        } else {
            ConnectionAnchor ret = updateConnectionAnchor(state, state.gmSourceAnchor, nodeEditPart, true);
            return ret;
        }
        
    }

    /**
     * Returns the <code>ConnectionAnchor</code> for the specified <i>target</i> rectangular node. The NodeEditPart is the {@link ConnectionEditPart#getTarget() isSourceAnchor} EditPart for the provider connection.
     * <P>
     * The anchor may be a function of the connection's model, the node's model, a combination of both, or it may not depend on anything all.
     * @param nodeEditPart The rectangular node to anchor to
     * @param connEditpart The connection to anchor to.
     * @return the ConnectionAnchor for the given rectangular EditPart
     */
    @objid ("80a3dfe4-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public ConnectionAnchor getTargetConnectionAnchor(final GraphicalEditPart nodeEditPart, final ConnectionEditPart connEditpart) {
        State state = new State(connEditpart);
        
        if (state.gmTargetAnchor == null) {
            return new ChopboxAnchor(nodeEditPart.getFigure());
        } else {
            ConnectionAnchor ret = updateConnectionAnchor(state, state.gmTargetAnchor, nodeEditPart, false);
            return ret;
        }
        
    }

    /**
     * Update an existing connection anchor from a given anchor model and a connection routing mode.
     * <p>
     * May return the same connection anchor or another one. In the last case the returned anchor must be used and the other should be discarded.
     * @param gmLinkAnchor The anchor model.
     * @param nodeEditPart The rectangular node to anchor to
     * @param isSourceAnchor <code>true</code> if the anchor is a source anchor, <code>false</code> if it is a target anchor
     * @return the updated draw2d anchor, or a new one.
     */
    @objid ("80a3dff2-1dec-11e2-8cad-001ec947c8cc")
    protected ConnectionAnchor updateConnectionAnchor(State state, final Object gmLinkAnchor, final GraphicalEditPart nodeEditPart, final boolean isSourceAnchor) {
        final IFigure fig = nodeEditPart.getFigure();
        if (gmLinkAnchor instanceof GmRaySlidableAnchor) {
            GmRaySlidableAnchor a = (GmRaySlidableAnchor) gmLinkAnchor;
            if (needSlidableAnchor(state.getFigureRoutingMode(), state.raked)) {
                return new RaySlidableAnchor(fig, a.getDifference());
            } else {
                return new NodeAnchor(fig, a.getDifference());
            }
        } else if (gmLinkAnchor instanceof GmNodeAnchor) {
            GmNodeAnchor a = (GmNodeAnchor) gmLinkAnchor;
            if (needSlidableAnchor(state.getFigureRoutingMode(), state.raked)) {
                return new RaySlidableAnchor(fig, a.getDifference());
            } else {
                return new NodeAnchor(fig, a.getDifference());
            }
        } else if (gmLinkAnchor instanceof GmBorderAnchor) {
            GmBorderAnchor a = (GmBorderAnchor) gmLinkAnchor;
            return new BorderAnchor(fig, a.getBorder(), a.getOffset());
        } else if (gmLinkAnchor instanceof GmPointAnchor) {
            GmPointAnchor pa = (GmPointAnchor) gmLinkAnchor;
            Dimension d = pa.getLocation();
            Point p = new Point(d.width, d.height);
            return new PointAnchor(fig, p);
        } else if (gmLinkAnchor instanceof GmXYAnchor) {
            final GmXYAnchor pa = (GmXYAnchor) gmLinkAnchor;
            final Point p = pa.getReferencePoint();
            return new XYAnchor(p);
        } else if (gmLinkAnchor instanceof GmSourceSatelliteAnchor) {
            GmSourceSatelliteAnchor sa = (GmSourceSatelliteAnchor) gmLinkAnchor;
            IFigure srcFig;
            if (isSourceAnchor) {
                srcFig = ((GraphicalEditPart) state.connectionEditPart.getTarget()).getFigure();
            } else {
                srcFig = ((GraphicalEditPart) state.connectionEditPart.getSource()).getFigure();
            }
            return new SatelliteAnchor(srcFig, sa.getLocation());
        } else if (gmLinkAnchor instanceof GmAbstractLinkAnchor) {
            final GmAbstractLinkAnchor a = (GmAbstractLinkAnchor) gmLinkAnchor;
            if (needSlidableAnchor(state.getFigureRoutingMode(), state.raked)) {
                return new RaySlidableAnchor(fig, a.getLocation());
            } else {
                return new NodeAnchor(fig, a.getLocation());
            }
        } else if (gmLinkAnchor instanceof GmFixedAnchor) {
            // This should happen only for ghost nodes
            return this.fixedFallbackProvider.createFromModel(fig, (GmFixedAnchor) gmLinkAnchor);
        } else {
            throw new UnsupportedOperationException(gmLinkAnchor.toString());
        }
        
    }

    @objid ("f0164871-ea50-4c23-ba20-14e8c4d376aa")
    @Override
    public Object createAnchorModel(ConnectionAnchor anchor) {
        if (anchor instanceof FixedAnchor) {
            // This should happen only for ghost nodes
            return this.fixedFallbackProvider.createAnchorModel(anchor);
        } else {
            return super.createAnchorModel(anchor);
        }
        
    }

    @objid ("9192eb84-7e11-41f3-87cb-71016fe438eb")
    @Override
    protected ConnectionAnchor getAnchorForCreateBendedConnectionRequest(GraphicalEditPart nodeEditPart, CreateBendedConnectionRequest request, boolean isSourceAnchor) {
        IFigure fig = nodeEditPart.getFigure();
        Rectangle figBounds = fig.getBounds().getCopy();
        fig.translateToAbsolute(figBounds);
        final Point figLocation = figBounds.getTopLeft();
        
        final Dimension offset;
        boolean raked = false;
        Point requestLocation = request.getLocation();
        if (isSourceAnchor) {
            offset = request.getData().getSrcPoint().getDifference(figLocation);
        } else {
            // Flag put by CreateRakeLinkEditPolicy.createPathModel(...)
            raked = request.getExtendedData().get("rake") == Boolean.TRUE;
        
            offset = requestLocation.getDifference(figLocation);
        }
        
        fig.translateToRelative(offset);
        
        if (needSlidableAnchor(request.getData().getRoutingMode(), raked)) {
            // For Orthogonal and Rake routers, return "sliding" anchors.
            return new RaySlidableAnchor(fig, offset);
        } else {
            return createBorderAnchor(fig, offset, isSourceAnchor);
        }
        
    }

    /**
     * Returns the <code>ConnectionAnchor</code> for the specified Request. The returned ConnectionAnchor is used only when displaying <i>feedback</i>.
     * @param request a Request describing the current interaction
     * @param isSourceAnchor Whether an anchor is needed for a isSourceAnchor or a target side.
     * @return the ConnectionAnchor to use during feedback
     * @throws IllegalArgumentException if the request has no location.
     */
    @objid ("80a6421d-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected ConnectionAnchor getAnchorForCreateRequest(GraphicalEditPart nodeEditPart, CreateRequest request, boolean isSourceAnchor) throws IllegalArgumentException {
        IFigure fig = nodeEditPart.getFigure();
        Rectangle figBounds = fig.getBounds().getCopy();
        fig.translateToAbsolute(figBounds);
        final Point figLocation = figBounds.getTopLeft();
        
        Point requestLocation = request.getLocation();
        final Dimension offset = requestLocation.getDifference(figLocation);
        fig.translateToRelative(offset);
        
        if (isSourceAnchor) {
            return new RaySlidableAnchor(fig, offset);
        } else {
            return createBorderAnchor(fig, offset, isSourceAnchor);
        }
        
    }

    @objid ("ee726c27-d0e2-4a7d-9321-41ad98fa97dc")
    private ConnectionAnchor createBorderAnchor(IFigure fig, Dimension offset, boolean isSourceAnchor) {
        Rectangle figBounds = fig.getBounds();
        
        Point p = figBounds.getTopLeft();
        p.translate(offset);
        
        switch (GeomUtils.getDirection(p, figBounds)) {
        case EAST:
            return new BorderAnchor(fig, PositionConstants.EAST, offset.height);
        case WEST:
            return new BorderAnchor(fig, PositionConstants.WEST, offset.height);
        case NORTH:
            return new BorderAnchor(fig, PositionConstants.NORTH, offset.width);
        case SOUTH:
            return new BorderAnchor(fig, PositionConstants.SOUTH, offset.width);
        default:
            if (isSourceAnchor) {
                return new BorderAnchor(fig, PositionConstants.EAST, offset.height);
            } else {
                return new BorderAnchor(fig, PositionConstants.WEST, offset.height);
            }
        }
        
    }

    /**
     * Returns the <code>ConnectionAnchor</code> for the specified Request. The returned ConnectionAnchor is used only when displaying <i>feedback</i>. The Request is usually a {@link org.eclipse.gef.requests.LocationRequest} , which provides the current
     * mouse location.
     * @param request a Request describing the current interaction
     * @param isSourceAnchor Whether an anchor is needed for a isSourceAnchor or a target side.
     * @return the ConnectionAnchor to use during feedback
     * @throws IllegalArgumentException if the request has no location.
     */
    @objid ("80a6420d-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected ConnectionAnchor getAnchorForReconnectRequest(GraphicalEditPart nodeEditPart, ReconnectRequest request, boolean isSourceAnchor) throws IllegalArgumentException {
        IFigure fig = (IFigure) request.getExtendedData().get(CreateLinkConstants.PROP_RECONNECT_ON_FIGURE);
        if (fig == null) {
            fig = nodeEditPart.getFigure();
        }
        
        final Point figLocation = fig.getBounds().getTopLeft();
        fig.translateToAbsolute(figLocation);
        
        final Point requestLocation = request.getLocation();
        if (requestLocation == null) {
            throw new IllegalArgumentException(request + " has no location.");
        }
        
        State state = new State(request.getConnectionEditPart());
        
        final Dimension offset = requestLocation.getDifference(figLocation);
        fig.translateToRelative(offset);
        
        // Return a new anchor
        if (needSlidableAnchor(state.getFigureRoutingMode(), state.raked)) {
            RaySlidableAnchor ret = new RaySlidableAnchor(fig, offset);
            return ret;
        } else {
            return createBorderAnchor(fig, offset, isSourceAnchor);
        }
        
    }

    /**
     * Private only constructor.
     */
    @objid ("80a3dfaf-1dec-11e2-8cad-001ec947c8cc")
    private  RectangleNodeAnchorProvider(boolean useSlidable) {
        this.useSlidable = useSlidable;
    }

    /**
     * Tells whether a slidable anchor is needed.
     * <p>
     * A slidable anchor is needed for orthogonal mode, except for rake mode. Tells whether a slidable anchor is needed.
     * </p>
     * @param router the router id.
     * @return <code>true</code> if a slidable anchor is needed, else <code>false</code>.
     */
    @objid ("53ccc5e4-267d-4071-a477-29fcd7fd52cd")
    protected boolean needSlidableAnchor(ConnectionRouterId router, boolean raked) {
        return this.useSlidable && router == ConnectionRouterId.ORTHOGONAL && !raked;
    }

    @objid ("718ca7bc-099f-47ff-9169-b7e15fc7665a")
    protected static class State {
        @objid ("80a3dfa9-1dec-11e2-8cad-001ec947c8cc")
        final boolean raked;

        @objid ("04f9b449-daab-487e-9652-70be0e0ae71a")
        final ConnectionEditPart connectionEditPart;

        @objid ("4ca35d4b-5484-422d-840a-ca54f6bfdc94")
        final IGmLinkObject gmLink;

        @objid ("d2aa36bc-6306-4144-8611-44727d6d0e1f")
        final Object gmSourceAnchor;

        @objid ("7eb8e12f-7d73-405f-a7d6-49844c83d85a")
        final Object gmTargetAnchor;

        @objid ("80a6424a-1dec-11e2-8cad-001ec947c8cc")
        public  State(final ConnectionEditPart aConnectionEditPart) {
            this.connectionEditPart = aConnectionEditPart;
            this.gmLink = (IGmLinkObject) aConnectionEditPart.getModel();
            
            final IGmPath path = this.gmLink.getPath();
            this.gmSourceAnchor = path.getSourceAnchor();
            this.gmTargetAnchor = path.getTargetAnchor();
            this.raked = isRaked(path);
            
        }

        @objid ("eec94b0e-51be-46ad-811d-775ba95ad4aa")
        public Connection getConnection() {
            return (Connection) this.connectionEditPart.getFigure();
        }

        /**
         * Get the routing mode from the connection figure.
         * @return the connection routing mode
         */
        @objid ("e7a1bcae-1d47-47d6-85af-ddcc683dbce2")
        public ConnectionRouterId getFigureRoutingMode() {
            return RoutingModeGetter.fromEditPart(this.connectionEditPart);
        }

        @objid ("80a64250-1dec-11e2-8cad-001ec947c8cc")
        private boolean isRaked(final IGmPath p) {
            return p.getSourceRake() != null || p.getTargetRake() != null;
        }

        @objid ("959a3d2b-9bbb-4434-a635-a0a0cf09febe")
        public ConnectionRouterId getNeededRouter() {
            return this.raked ? ConnectionRouterId.BENDPOINT
                    : this.gmLink.getPath().getRouterKind();
            
        }

    }

}
