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
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.XYAnchor;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.modelio.diagram.elements.core.figures.anchors.BorderAnchor;
import org.modelio.diagram.elements.core.figures.anchors.DelegateAnchor;
import org.modelio.diagram.elements.core.figures.anchors.NodeAnchor;
import org.modelio.diagram.elements.core.figures.anchors.RaySlidableAnchor;
import org.modelio.diagram.elements.core.figures.anchors.SatelliteAnchor;
import org.modelio.diagram.elements.core.link.CreateBendedConnectionRequest;

/**
 * Base implementation of {@link INodeAnchorProvider}.
 * 
 * @since 5.0.2
 */
@objid ("66f7b578-b76b-4355-b2a2-9ddc3909906b")
public abstract class AbstractNodeAnchorProvider implements INodeAnchorProvider {
    /**
     * Create a serializable anchor model from the given anchor.
     * @param anchor a figure anchor
     * @return an anchor model.
     */
    @objid ("7f8a3d2f-2b2d-4900-80e6-94f2668277db")
    @Override
    public Object createAnchorModel(final ConnectionAnchor anchor) {
        if (anchor instanceof BorderAnchor) {
            BorderAnchor a = (BorderAnchor) anchor;
            return new GmBorderAnchor(a.getBorder(), a.getOffset());
        } else if (anchor instanceof ChopboxAnchor) {
            return null;
        } else if (anchor instanceof RaySlidableAnchor) {
            RaySlidableAnchor a = (RaySlidableAnchor) anchor;
            Point anchorPoint = a.getReferencePoint().getCopy();
            Point figureLocation = a.getOwner().getBounds().getTopLeft();
            a.getOwner().translateToAbsolute(figureLocation);
        
            Dimension difference = anchorPoint.getDifference(figureLocation);
            a.getOwner().translateToRelative(difference);
        
            return new GmRaySlidableAnchor(difference);
        } else if (anchor instanceof NodeAnchor) {
            NodeAnchor a = (NodeAnchor) anchor;
            Point anchorPoint = a.getReferencePoint().getCopy();
            Point figureLocation = a.getOwner().getBounds().getTopLeft();
            a.getOwner().translateToAbsolute(figureLocation);
        
            Dimension difference = anchorPoint.getDifference(figureLocation);
            a.getOwner().translateToRelative(difference);
        
            return new GmNodeAnchor(difference);
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
            throw new IllegalArgumentException(anchor + " not handled");
        }
        
    }

    /**
     * Returns the <i>source</i> <code>ConnectionAnchor</code> for the specified Request on the given node. The returned
     * ConnectionAnchor is used only when displaying <i>feedback</i>. The Request is usually a
     * {@link org.eclipse.gef.requests.LocationRequest}, which provides the current mouse location.
     * <p>
     * The default implementation dispatch the request to one of these methods:
     * <ul>
     * <li> {@link #getAnchorForCreateBendedConnectionRequest(GraphicalEditPart, CreateBendedConnectionRequest, boolean)}
     * <li> {@link #getAnchorForReconnectRequest(GraphicalEditPart, ReconnectRequest, boolean)}
     * <li> {@link #getAnchorForCreateRequest(GraphicalEditPart, CreateRequest, boolean)}
     * </ul>
     * Sub classes should implement these 3 methods instead before redefining this one.
     * @param nodeEditPart The rectangular node to anchor from
     * @param request a Request describing the current interaction
     * @return the ConnectionAnchor to use during feedback
     */
    @objid ("22f6e2c9-1d6d-4870-b53a-8593b749bc43")
    @Override
    public ConnectionAnchor getSourceConnectionAnchor(final GraphicalEditPart nodeEditPart, final Request request) {
        if (request instanceof CreateBendedConnectionRequest) {
            CreateBendedConnectionRequest req = (CreateBendedConnectionRequest) request;
            return getAnchorForCreateBendedConnectionRequest(nodeEditPart, req, true);
        } else if (request instanceof ReconnectRequest) {
            return getAnchorForReconnectRequest(nodeEditPart, (ReconnectRequest) request, true);
        } else if (request instanceof CreateRequest) {
            return getAnchorForCreateRequest(nodeEditPart, (CreateRequest) request, true);
        } else {
            throw new IllegalArgumentException(request + " not handled.");
        }
        
    }

    /**
     * Returns the <i>target</i> <code>ConnectionAnchor</code> for the specified Request on the given node.
     * <p>
     * The returned ConnectionAnchor is used only when displaying <i>feedback</i>. The Request is usually a
     * {@link org.eclipse.gef.requests.LocationRequest} subclass, which provides the current mouse location.
     * <p>
     * The default implementation dispatch the request to one of these methods:
     * <ul>
     * <li> {@link #getAnchorForCreateBendedConnectionRequest(GraphicalEditPart, CreateBendedConnectionRequest, boolean)}
     * <li> {@link #getAnchorForReconnectRequest(GraphicalEditPart, ReconnectRequest, boolean)}
     * <li> {@link #getAnchorForCreateRequest(GraphicalEditPart, CreateRequest, boolean)}
     * </ul>
     * Sub classes should implement these 3 methods instead before redefining this one.
     * @param nodeEditPart The rectangular node to anchor from
     * @param request a Request describing the current interaction
     * @return the ConnectionAnchor to use during feedback
     */
    @objid ("b150c56f-1ffb-4ecd-8256-47e36399d3b4")
    @Override
    public ConnectionAnchor getTargetConnectionAnchor(final GraphicalEditPart nodeEditPart, final Request request) {
        if (request instanceof CreateBendedConnectionRequest) {
            CreateBendedConnectionRequest req = (CreateBendedConnectionRequest) request;
            return getAnchorForCreateBendedConnectionRequest(nodeEditPart, req, false);
        
        } else if (request instanceof ReconnectRequest) {
            return getAnchorForReconnectRequest(nodeEditPart, (ReconnectRequest) request, false);
        } else if (request instanceof CreateRequest) {
            return getAnchorForCreateRequest(nodeEditPart, (CreateRequest) request, false);
        } else {
            throw new IllegalArgumentException(request + " not handled.");
        }
        
    }

    /**
     * Returns the <code>ConnectionAnchor</code> for the specified {@link CreateBendedConnectionRequest}.
     * <p>
     * The returned ConnectionAnchor is used only when displaying <i>feedback</i>.
     * @param nodeEditPart the node on which an anchor is requested
     * @param request a Request describing the current interaction
     * @param isSourceAnchor Whether an anchor is needed for a source or a target side.
     * @return the ConnectionAnchor to use during feedback
     * @throws IllegalArgumentException if the request has no location.
     */
    @objid ("d914f84d-c3d5-42d2-979f-568d0248feec")
    protected abstract ConnectionAnchor getAnchorForCreateBendedConnectionRequest(final GraphicalEditPart nodeEditPart, CreateBendedConnectionRequest request, boolean isSourceAnchor) throws IllegalArgumentException;

    /**
     * Returns the <code>ConnectionAnchor</code> for the specified {@link ReconnectRequest}.
     * <p>
     * The returned ConnectionAnchor is used only when displaying <i>feedback</i>.
     * @param nodeEditPart the node on which an anchor is requested
     * @param request a Request describing the current interaction
     * @param source Whether an anchor is needed for a source or a target side.
     * @return the ConnectionAnchor to use during feedback
     * @throws IllegalArgumentException if the request has no location.
     */
    @objid ("c9f8d2e1-4d1f-4768-8418-7b14e112b050")
    protected abstract ConnectionAnchor getAnchorForReconnectRequest(final GraphicalEditPart nodeEditPart, final ReconnectRequest request, final boolean source) throws IllegalArgumentException;

    /**
     * Returns the <code>ConnectionAnchor</code> for the specified {@link CreateRequest}.
     * <p>
     * The returned ConnectionAnchor is used only when displaying <i>feedback</i>.
     * @param nodeEditPart the node on which an anchor is requested
     * @param request a Request describing the current interaction
     * @param source Whether an anchor is needed for a source or a target side.
     * @return the ConnectionAnchor to use during feedback
     * @throws IllegalArgumentException if the request has no location.
     */
    @objid ("cf16cedd-9b4a-4d63-9274-89c824775d19")
    protected abstract ConnectionAnchor getAnchorForCreateRequest(final GraphicalEditPart nodeEditPart, final CreateRequest request, final boolean source) throws IllegalArgumentException;

}
