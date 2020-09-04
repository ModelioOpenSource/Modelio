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

package org.modelio.diagram.elements.core.node;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.ConnectionRouter;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.XYAnchor;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.modelio.diagram.elements.core.figures.anchors.BorderAnchor;
import org.modelio.diagram.elements.core.figures.anchors.DelegateAnchor;
import org.modelio.diagram.elements.core.figures.anchors.NodeAnchor;
import org.modelio.diagram.elements.core.figures.anchors.PointAnchor;
import org.modelio.diagram.elements.core.figures.anchors.RaySlidableAnchor;
import org.modelio.diagram.elements.core.figures.anchors.SatelliteAnchor;
import org.modelio.diagram.elements.core.figures.routers.OrthogonalRouter;
import org.modelio.diagram.elements.core.figures.routers.RakeRouter;
import org.modelio.diagram.elements.core.link.CreateBendedConnectionRequest;
import org.modelio.diagram.elements.core.link.GmAbstractLinkAnchor;
import org.modelio.diagram.elements.core.link.anchors.AnchorRegistry;
import org.modelio.diagram.elements.core.link.anchors.GmBorderAnchor;
import org.modelio.diagram.elements.core.link.anchors.GmNodeAnchor;
import org.modelio.diagram.elements.core.link.anchors.GmPointAnchor;
import org.modelio.diagram.elements.core.link.anchors.GmRaySlidableAnchor;
import org.modelio.diagram.elements.core.link.anchors.GmSourceSatelliteAnchor;
import org.modelio.diagram.elements.core.link.anchors.GmXYAnchor;
import org.modelio.diagram.elements.core.model.IGmLinkObject;
import org.modelio.diagram.elements.core.model.IGmPath;
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
public class RectangleNodeAnchorProvider {
    @objid ("80a3dfa9-1dec-11e2-8cad-001ec947c8cc")
    private boolean raked;

    @objid ("c62ef822-9809-4d80-85c3-7e8f49d813ea")
    private ConnectionEditPart connectionEditPart;

    @objid ("80a17d8a-1dec-11e2-8cad-001ec947c8cc")
    private static RectangleNodeAnchorProvider singleton = new RectangleNodeAnchorProvider();

    @objid ("80a17d8b-1dec-11e2-8cad-001ec947c8cc")
    private IGmLinkObject gmLink;

    @objid ("80a17d8c-1dec-11e2-8cad-001ec947c8cc")
    private Object gmSourceAnchor;

    @objid ("80a17d8d-1dec-11e2-8cad-001ec947c8cc")
    private Object gmTargetAnchor;

    /**
     * Factory to get a anchor provider .
     * <p>
     * This is the only way to get an anchor provider. The returned provider should be used then discarded. It is not
     * intended to be cached.
     * @return an anchor provider.
     */
    @objid ("80a3dfaa-1dec-11e2-8cad-001ec947c8cc")
    public static RectangleNodeAnchorProvider get() {
        return singleton;
    }

    /**
     * Private only constructor.
     */
    @objid ("80a3dfaf-1dec-11e2-8cad-001ec947c8cc")
    private RectangleNodeAnchorProvider() {
    }

    /**
     * Create a serializable anchor model from the given anchor.
     * @param anchor a figure anchor
     * @return an anchor model.
     */
    @objid ("80a3dfb2-1dec-11e2-8cad-001ec947c8cc")
    public Object createAnchorModel(final ConnectionAnchor anchor) {
        Object gmAnchor = AnchorRegistry.getGmAnchor(anchor);
        if (anchor instanceof BorderAnchor) {
            BorderAnchor a = (BorderAnchor) anchor;
            if (gmAnchor instanceof GmBorderAnchor) {
                GmBorderAnchor gmBorder = (GmBorderAnchor) gmAnchor;
                gmBorder.setBorder(a.getBorder());
                gmBorder.setOffset(a.getOffset());
                return gmBorder;
            } else {
                return new GmBorderAnchor(a.getBorder(), a.getOffset());
            }
        } else if (anchor instanceof ChopboxAnchor) {
            return null;
        } else if (anchor instanceof RaySlidableAnchor) {
            RaySlidableAnchor a = (RaySlidableAnchor) anchor;
            Point anchorPoint = a.getReferencePoint().getCopy();
            Point figureLocation = a.getOwner().getBounds().getTopLeft();
            a.getOwner().translateToAbsolute(figureLocation);
        
            Dimension difference = anchorPoint.getDifference(figureLocation);
            a.getOwner().translateToRelative(difference);
        
            if (gmAnchor instanceof GmRaySlidableAnchor) {
                GmRaySlidableAnchor gmRay = (GmRaySlidableAnchor) gmAnchor;
                gmRay.setDifference(difference);
                return gmRay;
            } else {
                return new GmRaySlidableAnchor(difference);
            }
        } else if (anchor instanceof NodeAnchor) {
            NodeAnchor a = (NodeAnchor) anchor;
            Point anchorPoint = a.getReferencePoint().getCopy();
            Point figureLocation = a.getOwner().getBounds().getTopLeft();
            a.getOwner().translateToAbsolute(figureLocation);
        
            Dimension difference = anchorPoint.getDifference(figureLocation);
            a.getOwner().translateToRelative(difference);
        
            if (gmAnchor instanceof GmNodeAnchor) {
                GmNodeAnchor gmNodeAnchor = (GmNodeAnchor) gmAnchor;
                gmNodeAnchor.setDifference(difference);
                return gmNodeAnchor;
            } else {
                return new GmNodeAnchor(difference);
            }
        } else if (anchor instanceof XYAnchor) {
            XYAnchor xy = (XYAnchor) anchor;
            if (gmAnchor instanceof GmXYAnchor) {
                GmXYAnchor gmXy = (GmXYAnchor) gmAnchor;
                gmXy.setReferencePoint(xy.getReferencePoint());
                return gmXy;
            } else {
                return new GmXYAnchor(xy.getReferencePoint());
            }
        } else if (anchor instanceof DelegateAnchor) {
            DelegateAnchor a = (DelegateAnchor) anchor;
            return createAnchorModel(a.getDelegate());
        } else if (anchor instanceof SatelliteAnchor) {
            SatelliteAnchor sa = (SatelliteAnchor) anchor;
            if (gmAnchor instanceof GmSourceSatelliteAnchor) {
                GmSourceSatelliteAnchor ret = (GmSourceSatelliteAnchor) gmAnchor;
                ret.setLocation(sa.getDistance());
                return ret;
            } else {
                GmSourceSatelliteAnchor ret = new GmSourceSatelliteAnchor();
                ret.setLocation(sa.getDistance());
                return ret;
            }
        } else {
            throw new IllegalArgumentException(anchor + " not handled");
        }
    }

    /**
     * Returns the <code>ConnectionAnchor</code> for the specified <i>source</i> rectangular node. The NodeEditPart is
     * the {@link ConnectionEditPart#getSource() source} EditPart for the provider connection.
     * <P>
     * The anchor may be a function of the connection's model, the node's model, a combination of both, or it may not
     * depend on anything all.
     * @param nodeEditPart The rectangular node to anchor from
     * @param connEditpart The connection to anchor from.
     * @return the ConnectionAnchor for the given rectangular EditPart
     */
    @objid ("80a3dfba-1dec-11e2-8cad-001ec947c8cc")
    public ConnectionAnchor getSourceConnectionAnchor(final GraphicalEditPart nodeEditPart, final ConnectionEditPart connEditpart) {
        init(connEditpart);
        if (this.gmSourceAnchor == null) {
            return new ChopboxAnchor(nodeEditPart.getFigure());
        } else {
            ConnectionAnchor old = AnchorRegistry.getFigAnchor(this.gmSourceAnchor);
            ConnectionAnchor ret = updateConnectionAnchor(old, this.gmSourceAnchor, nodeEditPart, false);
            if (old != ret)
                AnchorRegistry.put(this.gmSourceAnchor, ret);
            return ret;
        }
    }

    /**
     * Returns the <i>source</i> <code>ConnectionAnchor</code> for the specified Request on the given node. The returned
     * ConnectionAnchor is used only when displaying <i>feedback</i>. The Request is usually a
     * {@link org.eclipse.gef.requests.LocationRequest}, which provides the current mouse location.
     * @param nodeEditPart The rectangular node to anchor from
     * @param request a Request describing the current interaction
     * @return the ConnectionAnchor to use during feedback
     */
    @objid ("80a3dfc8-1dec-11e2-8cad-001ec947c8cc")
    public ConnectionAnchor getSourceConnectionAnchor(final GraphicalEditPart nodeEditPart, final Request request) {
        if (request instanceof CreateBendedConnectionRequest) {
            IFigure fig = nodeEditPart.getFigure();
            final Point figLocation = fig.getBounds().getTopLeft();
            fig.translateToAbsolute(figLocation);
        
            CreateBendedConnectionRequest req = (CreateBendedConnectionRequest) request;
            if (needSlidableAnchor(req.getData().getRoutingMode())) {
                // For Orthogonal and Rake routers, return "sliding" anchors.
                final Dimension r = req.getData().getSrcPoint().getDifference(figLocation);
                fig.translateToRelative(r);
                return new RaySlidableAnchor(fig, r);
            } else {
                // Default: return a NodeAnchor.
                final Dimension r = req.getData().getSrcPoint().getDifference(figLocation);
                fig.translateToRelative(r);
                return new NodeAnchor(fig, r);
        
            }
        } else if (request instanceof ReconnectRequest) {
            return getConnectionAnchor(nodeEditPart, (ReconnectRequest) request, true);
        } else if (request instanceof CreateRequest) {
            return getConnectionAnchor(nodeEditPart, (CreateRequest) request, true);
        } else {
            throw new IllegalArgumentException(request + " not handled.");
        
        }
    }

    /**
     * Returns the <i>target</i> <code>ConnectionAnchor</code> for the specified Request on the given node. The returned
     * ConnectionAnchor is used only when displaying <i>feedback</i>. The Request is usually a
     * {@link org.eclipse.gef.requests.LocationRequest}, which provides the current mouse location.
     * @param nodeEditPart The rectangular node to anchor from
     * @param request a Request describing the current interaction
     * @return the ConnectionAnchor to use during feedback
     */
    @objid ("80a3dfd6-1dec-11e2-8cad-001ec947c8cc")
    public ConnectionAnchor getTargetConnectionAnchor(final GraphicalEditPart nodeEditPart, final Request request) {
        if (request instanceof CreateBendedConnectionRequest) {
            // Flag put by CreateRakeLinkEditPolicy.createPathModel(...)
            this.raked = request.getExtendedData().get("rake") == Boolean.TRUE;
            
            IFigure fig = nodeEditPart.getFigure();
            final Point figLocation = fig.getBounds().getTopLeft();
            fig.translateToAbsolute(figLocation);
        
            CreateBendedConnectionRequest req = (CreateBendedConnectionRequest) request;
            if (needSlidableAnchor(req.getData().getRoutingMode())) {
                // For Orthogonal and Rake routers, return "sliding" anchors.
                final Dimension r = req.getLocation().getDifference(figLocation);
                fig.translateToRelative(r);
                return new RaySlidableAnchor(fig, r);
            } else {
                // Default: return a NodeAnchor.
                final Dimension r = req.getLocation().getDifference(figLocation);
                fig.translateToRelative(r);
                return new NodeAnchor(fig, r);
            }
        
        } else if (request instanceof ReconnectRequest) {
            return getConnectionAnchor(nodeEditPart, (ReconnectRequest) request, false);
        } else if (request instanceof CreateRequest) {
            return getConnectionAnchor(nodeEditPart, (CreateRequest) request, false);
        } else {
            throw new IllegalArgumentException(request + " not handled.");
        }
    }

    /**
     * Returns the <code>ConnectionAnchor</code> for the specified <i>target</i> rectangular node. The NodeEditPart is
     * the {@link ConnectionEditPart#getTarget() source} EditPart for the provider connection.
     * <P>
     * The anchor may be a function of the connection's model, the node's model, a combination of both, or it may not
     * depend on anything all.
     * @param nodeEditPart The rectangular node to anchor to
     * @param connEditpart The connection to anchor to.
     * @return the ConnectionAnchor for the given rectangular EditPart
     */
    @objid ("80a3dfe4-1dec-11e2-8cad-001ec947c8cc")
    public ConnectionAnchor getTargetConnectionAnchor(final GraphicalEditPart nodeEditPart, final ConnectionEditPart connEditpart) {
        init(connEditpart);
        
        if (this.gmTargetAnchor == null) {
            return new ChopboxAnchor(nodeEditPart.getFigure());
        } else {
            ConnectionAnchor old = AnchorRegistry.getFigAnchor(this.gmTargetAnchor);
            ConnectionAnchor ret = updateConnectionAnchor(old, this.gmTargetAnchor, nodeEditPart, true);
        
            if (ret != old)
                AnchorRegistry.put(this.gmTargetAnchor, ret);
            return ret;
        }
    }

    /**
     * Update an existing connection anchor from a given anchor model and a connection routing mode.
     * <p>
     * May return the same connection anchor or another one. In the last case the returned anchor must be used and the
     * other should be discarded.
     * @param anchor the draw2d anchor to update
     * @param gmLinkAnchor The anchor model.
     * @param nodeEditPart The rectangular node to anchor to
     * @param isTargetAnchor <code>true</code> if the anchor is a target anchor, <code>false</code> if it is a source anchor
     * @return the updated draw2d anchor, or a new one.
     */
    @objid ("80a3dff2-1dec-11e2-8cad-001ec947c8cc")
    public ConnectionAnchor updateConnectionAnchor(final ConnectionAnchor anchor, final Object gmLinkAnchor, final GraphicalEditPart nodeEditPart, final boolean isTargetAnchor) {
        final IFigure nodeFigure = nodeEditPart.getFigure();
        if (gmLinkAnchor instanceof GmRaySlidableAnchor) {
            GmRaySlidableAnchor a = (GmRaySlidableAnchor) gmLinkAnchor;
            return updateConnectionAnchor(anchor, a.getDifference(), nodeFigure);
        } else if (gmLinkAnchor instanceof GmNodeAnchor) {
            GmNodeAnchor a = (GmNodeAnchor) gmLinkAnchor;
            return updateConnectionAnchor(anchor, a.getDifference(), nodeFigure);
        } else if (gmLinkAnchor instanceof GmBorderAnchor) {
            GmBorderAnchor a = (GmBorderAnchor) gmLinkAnchor;
            if (anchor instanceof BorderAnchor && anchor.getOwner() == nodeFigure) {
                BorderAnchor ret = (BorderAnchor) anchor;
                ret.setBorder(a.getBorder());
                ret.setOffset(a.getOffset());
                return ret;
            } else {
                return new BorderAnchor(nodeFigure, a.getBorder(), a.getOffset());
            }
        } else if (gmLinkAnchor instanceof GmPointAnchor) {
            GmPointAnchor pa = (GmPointAnchor) gmLinkAnchor;
            Dimension d = pa.getLocation();
            Point p = new Point(d.width, d.height);
            if (anchor instanceof PointAnchor && anchor.getOwner() == nodeFigure) {
                PointAnchor ret = (PointAnchor) anchor;
                ret.setLocation(p);
                return ret;
            } else {
                return new PointAnchor(nodeFigure, p);
            }
        } else if (gmLinkAnchor instanceof GmXYAnchor) {
            final GmXYAnchor pa = (GmXYAnchor) gmLinkAnchor;
            final Point p = pa.getReferencePoint();
            if (anchor instanceof XYAnchor) {
                final XYAnchor ret = (XYAnchor) anchor;
                ret.setLocation(p);
                return ret;
            } else {
                return new XYAnchor(p);
            }
        } else if (gmLinkAnchor instanceof GmSourceSatelliteAnchor) {
            GmSourceSatelliteAnchor sa = (GmSourceSatelliteAnchor) gmLinkAnchor;
            IFigure srcFig;
            if (isTargetAnchor)
                srcFig = ((GraphicalEditPart) this.connectionEditPart.getSource()).getFigure();
            else
                srcFig = ((GraphicalEditPart) this.connectionEditPart.getTarget()).getFigure();
        
            return new SatelliteAnchor(srcFig, sa.getLocation());
        } else {
            final GmAbstractLinkAnchor a = (GmAbstractLinkAnchor) gmLinkAnchor;
            return updateConnectionAnchor(anchor, a.getLocation(), nodeFigure);
        }
    }

    /**
     * Returns the <code>ConnectionAnchor</code> for the specified Request. The returned ConnectionAnchor is used only
     * when displaying <i>feedback</i>. The Request is usually a {@link org.eclipse.gef.requests.LocationRequest} ,
     * which provides the current mouse location.
     * @param request a Request describing the current interaction
     * @param source Whether an anchor is needed for a source or a target side.
     * @return the ConnectionAnchor to use during feedback
     * @throws java.lang.IllegalArgumentException if the request has no location.
     */
    @objid ("80a6420d-1dec-11e2-8cad-001ec947c8cc")
    protected ConnectionAnchor getConnectionAnchor(final GraphicalEditPart nodeEditPart, final ReconnectRequest request, final boolean source) throws IllegalArgumentException {
        IFigure fig = nodeEditPart.getFigure();
        
        final Point figLocation = fig.getBounds().getTopLeft();
        fig.translateToAbsolute(figLocation);
        
        final Point requestLocation = request.getLocation();
        if (requestLocation == null) {
            throw new IllegalArgumentException(request + " has no location.");
        }
        
        init(request.getConnectionEditPart());
        
        final Object gmLinkAnchor = source ? this.gmSourceAnchor : this.gmTargetAnchor;
        final EditPart oldNodeEditPart = source ? this.connectionEditPart.getSource()
                : this.connectionEditPart.getTarget();
        
        final Dimension r = requestLocation.getDifference(figLocation);
        fig.translateToRelative(r);
        
        ConnectionRouterId router = getFigureRoutingMode();//this.gmLink.getPath().getRouterKind();
        
        if (oldNodeEditPart == nodeEditPart) {
            // The connection stayed on the same source/target, edit the existing anchor.
            ConnectionAnchor ret = AnchorRegistry.getFigAnchor(gmLinkAnchor);
            if (needSlidableAnchor(router)) {
                if (ret instanceof RaySlidableAnchor && ret.getOwner() == fig) {
                    RaySlidableAnchor nodeAnchor = (RaySlidableAnchor) ret;
                    nodeAnchor.setReference(r);
                    return nodeAnchor;
                } else {
                    ret = new RaySlidableAnchor(fig, r);
                    AnchorRegistry.put(gmLinkAnchor, ret);
                    return ret;
                }
            } else {
                if (ret != null && ret.getClass() == NodeAnchor.class && ret.getOwner() == fig) {
                    NodeAnchor nodeAnchor = (NodeAnchor) ret;
                    nodeAnchor.setReference(r);
                    return nodeAnchor;
                } else {
                    ret = new NodeAnchor(fig, r);
                    AnchorRegistry.put(gmLinkAnchor, ret);
                    return ret;
                }
            }
        } else {
            // The connection changed source / target, return a new anchor
            if (needSlidableAnchor(router)) {
                return new RaySlidableAnchor(fig, r);
            } else {
                return new NodeAnchor(fig, r);
            }
        }
    }

    /**
     * Returns the <code>ConnectionAnchor</code> for the specified Request. The returned ConnectionAnchor is used only
     * when displaying <i>feedback</i>.
     * @param request a Request describing the current interaction
     * @param source Whether an anchor is needed for a source or a target side.
     * @return the ConnectionAnchor to use during feedback
     * @throws java.lang.IllegalArgumentException if the request has no location.
     */
    @objid ("80a6421d-1dec-11e2-8cad-001ec947c8cc")
    protected ConnectionAnchor getConnectionAnchor(final GraphicalEditPart nodeEditPart, final CreateRequest request, final boolean source) throws IllegalArgumentException {
        IFigure fig = nodeEditPart.getFigure();
        
        final Point figLocation = fig.getBounds().getTopLeft();
        fig.translateToAbsolute(figLocation);
        
        final Point p = request.getLocation();
        final Dimension r = p.getDifference(figLocation);
        fig.translateToRelative(r);
        
        if (source)
            return new RaySlidableAnchor(fig, r);
        else
            return new NodeAnchor(fig, r);
    }

    /**
     * Tells whether a slidable anchor is needed.
     * <p>
     * A slidable anchor is needed for orthogonal mode, except for rake mode.
     * @param router the router id.
     * @return <code>true</code> if a slidable anchor is needed, else <code>false</code>.
     */
    @objid ("80a6422d-1dec-11e2-8cad-001ec947c8cc")
    protected boolean needSlidableAnchor(final ConnectionRouterId router) {
        return (router == ConnectionRouterId.ORTHOGONAL && !this.raked);
    }

    /**
     * Update an existing connection anchor from a position relative to the node location.
     * <p>
     * May return the same connection anchor or another one. In the last case the returned anchor must be used and the
     * other should be discarded.
     * @param anchor the draw2d anchor to update or discard
     * @param pos reference position relative to the node figure location
     * @param nodeFigure the node figure to anchor to
     * @return the updated draw2d anchor, or a new one.
     */
    @objid ("80a64234-1dec-11e2-8cad-001ec947c8cc")
    protected ConnectionAnchor updateConnectionAnchor(final ConnectionAnchor anchor, final Dimension pos, final IFigure nodeFigure) {
        // return RaySlidableAnchor for orthogonal mode except if raked.
        // return NodeAnchor for rake and all other routing modes.
        final ConnectionRouterId routerKind = this.raked ? ConnectionRouterId.BENDPOINT
                : this.gmLink.getPath().getRouterKind();
        
        if (needSlidableAnchor(routerKind)) {
            final IFigure fig = nodeFigure;
            if (anchor instanceof RaySlidableAnchor && anchor.getOwner() == nodeFigure) {
                RaySlidableAnchor ret = (RaySlidableAnchor) anchor;
                ret.setReference(pos);
                return ret;
            } else {
                return new RaySlidableAnchor(fig, pos);
            }
        } else {
            if (anchor instanceof NodeAnchor && anchor.getOwner() == nodeFigure) {
                NodeAnchor ret = (NodeAnchor) anchor;
                ret.setReference(pos);
                return ret;
            } else {
                return new NodeAnchor(nodeFigure, pos);
            }
        
        }
    }

    /**
     * Get the routing mode from the connection figure.
     * @return the connection routing mode
     */
    @objid ("80a64246-1dec-11e2-8cad-001ec947c8cc")
    private ConnectionRouterId getFigureRoutingMode() {
        Connection conn = (Connection) this.connectionEditPart.getFigure();
        ConnectionRouter router = conn.getConnectionRouter();
        if (router instanceof OrthogonalRouter)
            return ConnectionRouterId.ORTHOGONAL;
        else if (router instanceof BendpointConnectionRouter)
            return ConnectionRouterId.BENDPOINT;
        else if (router == null || router instanceof ConnectionRouter.NullConnectionRouter)
            return ConnectionRouterId.DIRECT;
        else if (router instanceof RakeRouter)
            return ConnectionRouterId.ORTHOGONAL;
        else
            throw new IllegalArgumentException(router + " not handled.");
    }

    @objid ("80a6424a-1dec-11e2-8cad-001ec947c8cc")
    private void init(final ConnectionEditPart aConnectionEditPart) {
        this.connectionEditPart = aConnectionEditPart;
        this.gmLink = (IGmLinkObject) aConnectionEditPart.getModel();
        
        final IGmPath path = this.gmLink.getPath();
        this.gmSourceAnchor = path.getSourceAnchor();
        this.gmTargetAnchor = path.getTargetAnchor();
        this.raked = isRaked(path);
    }

    @objid ("80a64250-1dec-11e2-8cad-001ec947c8cc")
    private boolean isRaked(final IGmPath p) {
        return p.getSourceRake() != null || p.getTargetRake() != null;
    }

}
