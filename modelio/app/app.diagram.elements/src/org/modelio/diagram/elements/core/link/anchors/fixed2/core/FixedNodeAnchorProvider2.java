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
package org.modelio.diagram.elements.core.link.anchors.fixed2.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.EllipseAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.XYAnchor;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.AccessibleAnchorProvider;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.modelio.diagram.elements.core.figures.anchors.DelegateAnchor;
import org.modelio.diagram.elements.core.figures.anchors.FixedAnchor;
import org.modelio.diagram.elements.core.figures.anchors.SatelliteAnchor;
import org.modelio.diagram.elements.core.link.CreateBendedConnectionRequest;
import org.modelio.diagram.elements.core.link.GmAbstractLinkAnchor;
import org.modelio.diagram.elements.core.link.RoutingModeGetter;
import org.modelio.diagram.elements.core.link.anchors.AbstractNodeAnchorProvider;
import org.modelio.diagram.elements.core.link.anchors.AnchorRefHelper;
import org.modelio.diagram.elements.core.link.anchors.GmBorderAnchor;
import org.modelio.diagram.elements.core.link.anchors.GmFixedAnchor;
import org.modelio.diagram.elements.core.link.anchors.GmNodeAnchor;
import org.modelio.diagram.elements.core.link.anchors.GmRaySlidableAnchor;
import org.modelio.diagram.elements.core.link.anchors.GmSourceSatelliteAnchor;
import org.modelio.diagram.elements.core.link.anchors.GmXYAnchor;
import org.modelio.diagram.elements.core.link.anchors.RectangleNodeAnchorProvider;
import org.modelio.diagram.elements.core.model.IGmLinkObject;
import org.modelio.diagram.elements.core.model.IGmPath;
import org.modelio.diagram.elements.core.requests.CreateLinkConstants;
import org.modelio.diagram.elements.plugin.DiagramElements;
import org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId;

/**
 * {@link IFixedNodeAnchorProvider} implementation that delegates to a {@link IFigureAnchorsAbstractFactory}.
 * <p>
 * 
 * @author cmarin
 * @since 5.3.1
 */
@objid ("69af4f1f-7ab0-4003-862c-42f85227f63c")
public class FixedNodeAnchorProvider2 extends AbstractNodeAnchorProvider implements IFixedNodeAnchorProvider {
    @objid ("dc5def5a-79a0-4caf-a948-f0c30391374a")
    private IFigureAnchorsAbstractFactory factory;

    @objid ("919fcc54-afc8-46ed-a588-334bbdf75818")
    public  FixedNodeAnchorProvider2(IFigureAnchorsAbstractFactory factory) {
        super();
        this.factory = factory;
        
    }

    /**
     * protected only constructor.
     */
    @objid ("91f80757-957d-4c66-931b-668f367454f6")
    protected  FixedNodeAnchorProvider2() {
        
    }

    @objid ("63a52780-8044-47af-ba36-6ab6fc2300fc")
    @Override
    public IFigureAnchorsFactory getAnchorFactoryFor(GraphicalEditPart anEditPart, IFigure aFigure) {
        return this.factory.getAnchorFactoryFor(anEditPart, aFigure);
    }

    /**
     * Create a serializable anchor model from the given anchor.
     * @param anchor a figure anchor
     * @return an anchor model.
     */
    @objid ("76e07bf3-a20b-438e-8d27-bfe20d0bdf6b")
    @Override
    public Object createAnchorModel(final ConnectionAnchor anchor) {
        if (anchor instanceof ChopboxAnchor) {
            return null;
        } else if (anchor instanceof EllipseAnchor) {
            return null;
        } else if (anchor instanceof FixedAnchor) {
            FixedAnchor fa = (FixedAnchor) anchor;
            return new GmFixedAnchor(fa.getLocator().getAlgorithm(), fa.getFace(), fa.getRank(), fa.getTotalOnFace());
        } else if (anchor instanceof XYAnchor) {
            XYAnchor xy = (XYAnchor) anchor;
            return new GmXYAnchor(xy.getReferencePoint());
        } else if (anchor instanceof DelegateAnchor) {
            DelegateAnchor a = (DelegateAnchor) anchor;
            return createAnchorModel(a.getDelegate());
        } else if (anchor instanceof SatelliteAnchor) {
            SatelliteAnchor sa = (SatelliteAnchor) anchor;
            GmSourceSatelliteAnchor ret = new GmSourceSatelliteAnchor();
            ret.setLocation(sa.getDistance());
            return ret;
        } else {
            // Compatibility with <= 5.0.0 diagrams
            return RectangleNodeAnchorProvider.get().createAnchorModel(anchor);
        }
        
    }

    @objid ("d810486f-de95-4087-9e12-9b19e4051d4f")
    public ConnectionAnchor createFromModel(GraphicalEditPart nodeEditPart, GmFixedAnchor gmLinkAnchor) {
        return this.factory.getAnchorFactoryFor(nodeEditPart, nodeEditPart.getFigure()).createFromModel( gmLinkAnchor);
    }

    /**
     * <p>
     * Get an implementation of {@link AccessibleAnchorProvider} for the given node figure.
     * </p>
     * @param nodeFig a node figure
     * @return an implementation of {@link AccessibleAnchorProvider}
     */
    @objid ("5e867bc4-e8b8-4c53-8361-c4982fc1a033")
    public AccessibleAnchorProvider getAccessibleAnchorProvider(GraphicalEditPart nodeEditPart) {
        return new AccessibleAnchorProvider() {
            @objid ("6ec0672d-aeaf-4655-987d-4150507d600b")
            @Override
            public List getSourceAnchorLocations() {
                return getTargetAnchorLocations();
            }
        
            @objid ("1bd01c33-3d6d-4108-ae55-84b7242c9220")
            @Override
            public List getTargetAnchorLocations() {
                Collection<ConnectionAnchor> allAnchors = factory.getAnchorFactoryFor(nodeEditPart, nodeEditPart.getFigure()).getAllAnchors( ConnectionRouterId.ORTHOGONAL, null);
                List<Point> ret = new ArrayList<>(allAnchors.size());
        
                for (ConnectionAnchor a : allAnchors) {
                    ret.add(a.getReferencePoint());
                }
                return ret;
            }
        };
        
    }

    /**
     * Returns the <code>ConnectionAnchor</code> for the specified <i>source</i> rectangular node. The NodeEditPart is the {@link ConnectionEditPart#getSource() source} EditPart for the provider connection.
     * <P>
     * The anchor may be a function of the connection's model, the node's model, a combination of both, or it may not depend on anything all.
     * @param nodeEditPart The rectangular node to anchor from
     * @param connEditpart The connection to anchor from.
     * @return the ConnectionAnchor for the given rectangular EditPart
     */
    @objid ("eef82d0a-6447-4c6e-8eef-60f6f18c599c")
    @Override
    public ConnectionAnchor getSourceConnectionAnchor(final GraphicalEditPart nodeEditPart, final ConnectionEditPart connEditpart) {
        State state = new State(connEditpart);
        if (state.gmSourceAnchor == null) {
            return new ChopboxAnchor(nodeEditPart.getFigure());
        } else {
            ConnectionAnchor ret = updateConnectionAnchor(state, nodeEditPart, true);
            return ret;
        }
        
    }

    @objid ("41bc7146-206e-4b2f-9edb-cd2dbc4cfb7f")
    @Override
    public ConnectionAnchor getSourceConnectionAnchor(GraphicalEditPart nodeEditPart, Request request) {
        Boolean needSlidableAnchor = (Boolean) request.getExtendedData().get(CreateLinkConstants.PROP_NEED_SLIDABLE_ANCHOR);
        
        if (Boolean.TRUE.equals(needSlidableAnchor)) {
            return RectangleNodeAnchorProvider.getSlidable().getSourceConnectionAnchor(nodeEditPart, request);
        } else {
            return super.getSourceConnectionAnchor(nodeEditPart, request);
        }
        
    }

    /**
     * Returns the <code>ConnectionAnchor</code> for the specified <i>target</i> rectangular node. The NodeEditPart is the {@link ConnectionEditPart#getTarget() source} EditPart for the provider connection.
     * <P>
     * The anchor may be a function of the connection's model, the node's model, a combination of both, or it may not depend on anything all.
     * @param nodeEditPart The rectangular node to anchor to
     * @param connEditpart The connection to anchor to.
     * @return the ConnectionAnchor for the given rectangular EditPart
     */
    @objid ("fe6f7f3b-0514-4adf-8227-d788cdc0286c")
    @Override
    public ConnectionAnchor getTargetConnectionAnchor(final GraphicalEditPart nodeEditPart, final ConnectionEditPart connEditpart) {
        State state = new State(connEditpart);
        
        if (state.gmTargetAnchor == null) {
            return new ChopboxAnchor(nodeEditPart.getFigure());
        } else {
            ConnectionAnchor ret = updateConnectionAnchor(state, nodeEditPart, false);
            return ret;
        }
        
    }

    @objid ("850d925b-b945-4673-8068-317f7da466e0")
    @Override
    public ConnectionAnchor getTargetConnectionAnchor(GraphicalEditPart nodeEditPart, Request request) {
        Boolean needSlidableAnchor = (Boolean) request.getExtendedData().get(CreateLinkConstants.PROP_NEED_SLIDABLE_ANCHOR);
        
        if (Boolean.TRUE.equals(needSlidableAnchor)) {
            return RectangleNodeAnchorProvider.getSlidable().getTargetConnectionAnchor(nodeEditPart, request);
        } else {
            return super.getTargetConnectionAnchor(nodeEditPart, request);
        }
        
    }

    @objid ("e0bb580e-6751-4473-b3c3-0b7cd9cfe20b")
    public void onFigureMoved(IFigure figure) {
        // TODO Auto-generated method stub
    }

    @objid ("9643ca06-dbce-4ae8-9859-f65785fd0ba5")
    @Override
    protected ConnectionAnchor getAnchorForCreateBendedConnectionRequest(GraphicalEditPart nodeEditPart, CreateBendedConnectionRequest req, boolean isSourceAnchor) {
        IFigure figure = nodeEditPart.getFigure();
        Rectangle figureBounds = figure.getBounds().getCopy();
        figure.translateToAbsolute(figureBounds);
        Point figCenter = figureBounds.getCenter();
        
        if (isSourceAnchor) {
            Point srcPoint = findGoodAnchorRef(
                    req.getData().getSrcPoint(),
                    req.getData().getPath(),
                    true,
                    req.getData().getLastPoint(),
                    req.getLocation(),
                    figCenter);
        
            if (srcPoint == null) {
                srcPoint = figureBounds.getRight();
            }
            return this.factory.getAnchorFactoryFor(nodeEditPart, figure).getNearest(srcPoint, req.getData().getRoutingMode(), null, isSourceAnchor);
        } else {
            // Flag put by CreateRakeLinkEditPolicy.createPathModel(...)
            // this.raked = request.getExtendedData().get("rake") == Boolean.TRUE;
            Point targetPoint = findGoodAnchorRef(
                    req.getLocation(),
                    req.getData().getPath(),
                    false,
                    req.getData().getSrcPoint(),
                    null,
                    figCenter);
            if (targetPoint == null) {
                targetPoint = figureBounds.getLeft();
            }
            return this.factory.getAnchorFactoryFor(nodeEditPart, figure).getNearest( targetPoint, req.getData().getRoutingMode(), null, isSourceAnchor);
        }
        
    }

    /**
     * Returns the <code>ConnectionAnchor</code> for the specified Request. The returned ConnectionAnchor is used only when displaying <i>feedback</i>.
     * @param request a Request describing the current interaction
     * @param source Whether an anchor is needed for a source or a target side.
     * @return the ConnectionAnchor to use during feedback
     * @throws IllegalArgumentException if the request has no location.
     */
    @objid ("f4135530-d530-4e2c-b4c6-84d2aadecdfe")
    @Override
    protected ConnectionAnchor getAnchorForCreateRequest(final GraphicalEditPart nodeEditPart, final CreateRequest request, final boolean source) throws IllegalArgumentException {
        final Point p = request.getLocation();
        return this.factory.getAnchorFactoryFor(nodeEditPart, nodeEditPart.getFigure()).getNearest( p, ConnectionRouterId.ORTHOGONAL, null, source);
    }

    /**
     * Returns the <code>ConnectionAnchor</code> for the specified Request. The returned ConnectionAnchor is used only when displaying <i>feedback</i>. The Request is usually a {@link org.eclipse.gef.requests.LocationRequest} , which provides the current
     * mouse location.
     * @param request a Request describing the current interaction
     * @param source Whether an anchor is needed for a source or a target side.
     * @return the ConnectionAnchor to use during feedback
     * @throws IllegalArgumentException if the request has no location.
     */
    @objid ("39273a73-5bd5-43a2-ab88-3073648d6a66")
    @Override
    protected ConnectionAnchor getAnchorForReconnectRequest(final GraphicalEditPart nodeEditPart, final ReconnectRequest request, final boolean source) throws IllegalArgumentException {
        final Point requestLocation = request.getLocation();
        if (requestLocation == null) {
            throw new IllegalArgumentException(request + " has no location.");
        }
        
        State state = new State(request.getConnectionEditPart());
        
        final EditPart oldNodeEditPart = source ? state.connectionEditPart.getSource()
                : state.connectionEditPart.getTarget();
        
        IFigure nodeFigure = (IFigure) request.getExtendedData().get(CreateLinkConstants.PROP_RECONNECT_ON_FIGURE);
        if (nodeFigure == null) {
            nodeFigure = nodeEditPart.getFigure();
        }
        
        if (oldNodeEditPart == nodeEditPart) {
            // The connection stayed on the same source/target.
            // The request may tell to stay on same face.
            // Try to return the same previous instance to spare listeners firing & changes
        
            Connection connFig = state.getConnection();
            ConnectionAnchor refAnchor = (ConnectionAnchor) request.getExtendedData().get(CreateLinkConstants.PROP_RECONNECT_ON_SAME_FACE);
            ConnectionAnchor previous;
        
            if (refAnchor != null) {
                previous = refAnchor;
            } else if (source) {
                previous = connFig.getSourceAnchor();
            } else {
                previous = connFig.getTargetAnchor();
            }
        
            Integer face = refAnchor instanceof FixedAnchor ? ((FixedAnchor) refAnchor).getFace() : null;
        
            ConnectionAnchor nearest = this.factory.getAnchorFactoryFor(nodeEditPart, nodeFigure).getNearest( requestLocation, state.getFigureRoutingMode(), face, source);
            if (nearest == null) {
                nearest = this.factory.getAnchorFactoryFor(nodeEditPart, nodeFigure).getNearest( requestLocation, state.getFigureRoutingMode(), null, source);
            }
        
            if (nearest == null) {
                return previous;
            } else if (previous != null && previous.equals(nearest)) {
                // return same previous instance to spare listeners firing & changes
                return previous;
            } else {
                return nearest;
            }
        } else {
            ConnectionRouterId routerId = state.getFigureRoutingMode();
            return this.factory.getAnchorFactoryFor(nodeEditPart, nodeFigure).getNearest( requestLocation, routerId, null, source);
        }
        
    }

    @objid ("9c2b7126-d4fe-4877-8c89-58d3592fce3a")
    private static Point findGoodAnchorRef(Point candidate1, List<Point> candidates2, boolean first, Point candidate3, Point candidate4, Point forbidden) {
        if (AnchorRefHelper.isGoodAnchorRef(candidate1, forbidden))
            return candidate1;
        
        Point found = AnchorRefHelper.findGoodAnchorRef(candidates2, first, 1, forbidden);
        if (found != null)
            return found;
        
        if (AnchorRefHelper.isGoodAnchorRef(candidate3, forbidden))
            return candidate3;
        
        if (AnchorRefHelper.isGoodAnchorRef(candidate4, forbidden))
            return candidate4;
        return null;
    }

    @objid ("bce295fe-3bb0-47e4-99a1-2fdd2ed850d7")
    private boolean isRaked(final IGmPath p) {
        return p.getSourceRake() != null || p.getTargetRake() != null;
    }

    /**
     * Update an existing connection anchor from a given anchor model and a connection routing mode.
     * <p>
     * May return the same connection anchor or another one. In the last case the returned anchor must be used and the other should be discarded.
     * @param nodeEditPart The rectangular node to anchor to
     * @param isSourceAnchor <code>true</code> if the anchor is a source anchor, <code>false</code> if it is a target anchor
     * @return the updated draw2d anchor, or a new one.
     */
    @objid ("e6ce38aa-2b51-4004-9f5a-8c5346a56fd8")
    private ConnectionAnchor updateConnectionAnchor(final State state, final GraphicalEditPart nodeEditPart, final boolean isSourceAnchor) {
        final Object gmLinkAnchor = !isSourceAnchor ? state.gmTargetAnchor : state.gmSourceAnchor;
        
        final IFigure nodeFigure = nodeEditPart.getFigure();
        if (gmLinkAnchor instanceof GmRaySlidableAnchor || gmLinkAnchor instanceof GmNodeAnchor  || gmLinkAnchor instanceof GmBorderAnchor) {
            // Compatibility with <= 5.0.0 diagrams
            if (isSourceAnchor) {
                return RectangleNodeAnchorProvider.getSlidable().getSourceConnectionAnchor(nodeEditPart, state.connectionEditPart);
            } else {
                return RectangleNodeAnchorProvider.getSlidable().getTargetConnectionAnchor(nodeEditPart, state.connectionEditPart);
            }
        } else if (gmLinkAnchor instanceof GmFixedAnchor) {
            GmFixedAnchor a = (GmFixedAnchor) gmLinkAnchor;
            return this.factory.getAnchorFactoryFor(nodeEditPart, nodeFigure).createFromModel( a);
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
            // should not happen, convert unsupported GM to supported anchor
            DiagramElements.LOG.debug(new IllegalArgumentException(String.format("Unsupported anchor <%s> - on %s - for %s connection", gmLinkAnchor, nodeEditPart, state.connectionEditPart)));
            GmAbstractLinkAnchor a = (GmAbstractLinkAnchor) gmLinkAnchor;
            Point pos = nodeFigure.getBounds().getTopLeft().translate(a.getLocation());
            nodeFigure.translateToAbsolute(pos);
            return this.factory.getAnchorFactoryFor(nodeEditPart, nodeFigure).getNearest( pos, state.getFigureRoutingMode(), null, isSourceAnchor);
        } else {
            // should not happen, ask for anchor to node center
            DiagramElements.LOG.debug(new IllegalArgumentException(String.format("Unknown anchor <%s> - on %s - for %s connection", gmLinkAnchor, nodeEditPart, state.connectionEditPart)));
            Point pos = nodeFigure.getBounds().getCenter();
            nodeFigure.translateToAbsolute(pos);
            return this.factory.getAnchorFactoryFor(nodeEditPart, nodeFigure).getNearest(pos, state.getFigureRoutingMode(), null, isSourceAnchor);
        
        }
        
    }

    /**
     * Some computations and quick references about the current ConnectionEditPart.
     */
    @objid ("e00063c8-3876-478e-8a81-40ddbdd4c4c1")
    private static class State {
        @objid ("90c02cff-09fe-43dc-9574-ba39afbd99b2")
        final ConnectionEditPart connectionEditPart;

        @objid ("37020fc0-d2bf-4679-b8b2-8e288eaa99cd")
        final IGmLinkObject gmLink;

        @objid ("566ac1ac-cd13-4ce5-b38b-921ffffe8aa7")
        final Object gmSourceAnchor;

        @objid ("57a2a729-8bd1-4139-a705-325e3b321304")
        final Object gmTargetAnchor;

        @objid ("86ecc521-49b4-4df0-883f-5374226242f4")
        public  State(final ConnectionEditPart aConnectionEditPart) {
            this.connectionEditPart = aConnectionEditPart;
            this.gmLink = (IGmLinkObject) aConnectionEditPart.getModel();
            
            final IGmPath path = this.gmLink.getPath();
            this.gmSourceAnchor = path.getSourceAnchor();
            this.gmTargetAnchor = path.getTargetAnchor();
            // this.raked = isRaked(path);
            
        }

        @objid ("40414b57-77e1-4a3a-b26f-18f747647713")
        public Connection getConnection() {
            return (Connection) this.connectionEditPart.getFigure();
        }

        /**
         * Get the routing mode from the connection figure.
         * @return the connection routing mode
         */
        @objid ("987da4dc-703b-4a86-91d8-22c767116220")
        public ConnectionRouterId getFigureRoutingMode() {
            return RoutingModeGetter.fromEditPart(this.connectionEditPart);
        }

    }

}
