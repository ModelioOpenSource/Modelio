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
package org.modelio.diagram.elements.core.link.path;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.HashSet;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.ConnectionRouter;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.editparts.LayerManager;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.modelio.diagram.elements.core.figures.RoundedLinkFigure;
import org.modelio.diagram.elements.core.link.ConnectionRoutingServices;
import org.modelio.diagram.elements.core.link.CreateBendedConnectionRequest;
import org.modelio.diagram.elements.core.link.GmPath;
import org.modelio.diagram.elements.core.link.IAnchorModelProvider;
import org.modelio.diagram.elements.core.model.IGmPath;
import org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId;

/**
 * Utilities needed by link edit policies to build a {@link IGmPath} from connection creation requests .
 * 
 * @author cma
 * @since 3.7
 */
@objid ("27a1bf4f-5048-4f42-ae29-eba703b8754f")
public class ConnectionPolicyUtils {
    /**
     * Build a {@link IGmPath} from a request and a Connection figure.
     * @param req a connection creation request.
     * @param tmpConnection a temporary Connection figure. This figure will be setup to reflect the request result and is used to compute the result path.
     * @return the built path model.
     */
    @objid ("07f85ee8-4d72-4e49-a810-bc2369bf0b25")
    public static IGmPath createPathModel(final CreateConnectionRequest req, Connection tmpConnection) {
        setupConnection(req, tmpConnection);
        
        GmPath ret = new GmPath();
        
        // Router defaults to DIRECT unless determined otherwise below
        ret.setRouterKind(ConnectionRouterId.DIRECT);
        
        // Getting a hold on the model of both anchors
        NodeEditPart sourceEditPart = (NodeEditPart) req.getSourceEditPart();
        ret.setSourceAnchor(getAnchorModel(sourceEditPart, tmpConnection.getSourceAnchor()));
        
        NodeEditPart targetPart = (NodeEditPart) req.getTargetEditPart();
        ret.setTargetAnchor(getAnchorModel(targetPart, tmpConnection.getTargetAnchor()));
        
        // If the request specifies so, extract more data
        if (req instanceof CreateBendedConnectionRequest) {
            CreateBendedConnectionRequest request = (CreateBendedConnectionRequest) req;
        
            // Set the real router
            ConnectionRouterId routerId = request.getData().getRoutingMode();
            ret.setRouterKind(routerId);
        
            // Compute the path constraint
            IConnectionHelper connPath = getRoutingServices(targetPart).getConnectionHelperFactory().createFromRawData(request, tmpConnection);
        
            // Convert it to serializable model.
            ret.setPathData(connPath.getModelPathData());
        
            // Note : the temp connection is still in the layer
        }
        return ret;
    }

    /**
     * Get the anchor model for the given anchor.
     * @param editpart a node edit part.
     * @param anchor a draw2d anchor
     * @return the anchor model, may be <code>null</code>
     */
    @objid ("99b1da79-7881-41ad-89a2-2d6413c15d1b")
    public static Object getAnchorModel(final NodeEditPart editpart, final ConnectionAnchor anchor) {
        if (editpart instanceof IAnchorModelProvider) {
            return ((IAnchorModelProvider) editpart).createAnchorModel(anchor);
        } else {
            return null; // TODO handle non IAnchorModelProvider
        }
        
    }

    /**
     * Get the connection layer for an edit part.
     * @param ep an edit part
     * @return the the connection layer to use.
     */
    @objid ("c1f0b31b-b5f5-45de-8868-112ad62aea9c")
    public static final IFigure getConnectionLayer(EditPart ep) {
        return LayerManager.Helper.find(ep).getLayer(LayerConstants.CONNECTION_LAYER);
    }

    /**
     * Get a common edit part owning the source and target edit part of the request.
     * <p>
     * If the source and target are in the same diagram, return the root edit part. In the other case, return the common edit part in both roots ownership chain.
     * @param req a connection creation request.
     * @return the common edit part.
     */
    @objid ("d45ad7f1-d17c-48c1-812c-51e00c6f0909")
    public static final EditPart getCommonParent(CreateConnectionRequest req) {
        EditPart sourceRoot = req.getSourceEditPart().getRoot();
        EditPart targetRoot = req.getTargetEditPart().getRoot();
        
        if (sourceRoot == targetRoot) {
            return sourceRoot;
        }
        
        // Look for common root edit part
        return getCommonParent(sourceRoot, targetRoot);
    }

    /**
     * Get the common edit part in the parent composition hierarchy.
     * <p>
     * If the source and target are in the same diagram, return the root edit part. In the other case, return the common edit part in the ownership chain.
     * @param sourceEp an edit part
     * @param targetEp an edit part
     * @return the edit part containing both.
     */
    @objid ("bb40a1ff-cc24-4dac-b630-8c08bd42d811")
    protected static final EditPart getCommonParent(EditPart sourceEp, EditPart targetEp) {
        ArrayDeque<EditPart> sourceStack = new ArrayDeque<>(5);
        ArrayDeque<EditPart> targetStack = new ArrayDeque<>(5);
        
        for (EditPart d = sourceEp; d != null; d = d.getParent()) {
            sourceStack.add(d);
        }
        for (EditPart d = targetEp; d != null; d = d.getParent()) {
            targetStack.add(d);
        }
        
        EditPart a = sourceStack.pollLast();
        EditPart b = targetStack.pollLast();
        EditPart common = null;
        while (a == b) {
            common = a;
            a = sourceStack.pollLast();
            b = targetStack.pollLast();
        }
        return common;
    }

    /**
     * @param ep an edit part
     * @return the connection routers registry.
     */
    @objid ("d93ec40a-b727-4a82-a0ab-d54675166d6f")
    public static ConnectionRoutingServices getRoutingServices(EditPart ep) {
        return (ConnectionRoutingServices) ep.getViewer().getProperty(ConnectionRoutingServices.ID);
    }

    /**
     * Setup the Connection figure to reflects the result of the request.
     * @param req a connection creation request.
     * @param tmpConnection a Connection figure.
     */
    @objid ("0f851dc3-130a-4177-8d73-9af7f0356bdf")
    public static void setupConnection(final CreateConnectionRequest req, Connection tmpConnection) {
        // Getting a hold on the model of both anchors
        NodeEditPart sourceEditPart = (NodeEditPart) req.getSourceEditPart();
        ConnectionAnchor srcAnchor = sourceEditPart.getSourceConnectionAnchor(req);
        
        NodeEditPart targetPart = (NodeEditPart) req.getTargetEditPart();
        ConnectionAnchor targetAnchor = targetPart.getTargetConnectionAnchor(req);
        
        // Set the anchors
        tmpConnection.setSourceAnchor(srcAnchor);
        tmpConnection.setTargetAnchor(targetAnchor);
        
        // Setup the temporary connection to be able to compute the path data
        EditPart commonRoot = getCommonParent(req);
        IFigure connLayer = getConnectionLayer(commonRoot);
        connLayer.add(tmpConnection);
        
        // If the request specifies so, extract more data
        if (req instanceof CreateBendedConnectionRequest) {
            CreateBendedConnectionRequest request = (CreateBendedConnectionRequest) req;
        
            // Set the real router
            ConnectionRouterId routerId = request.getData().getRoutingMode();
            final ConnectionRouter router = getRoutingServices(commonRoot).getCreationRouter(routerId);
            tmpConnection.setConnectionRouter(router);
        }
        
    }

    /**
     * Get the collections of all diagram connections.
     * <p>
     * This collection is used by {@link RoundedLinkFigure} to find connections intersections to draw bridges. The collection is returned by reference to be updated by the connection figures.
     * @return all diagram connections.
     * @since 3.7
     */
    @objid ("e6eefd99-a241-40d9-852b-6f73e29327e8")
    public static final Collection<Connection> getAllDiagramConnectionsCollector(EditPart ep) {
        EditPart rootRoot = ep.getRoot();
        
        @SuppressWarnings ("unchecked")
        Collection<Connection> allDiagramConnections = (Collection<Connection>) rootRoot.getViewer().getProperty("allDiagramConnections");
        if (allDiagramConnections == null) {
            // Create and add the collector to the viewer
            allDiagramConnections = new HashSet<>();
            while (rootRoot.getParent() != null) {
                // 'rootRoot' is still an embedded diagram root
                rootRoot = rootRoot.getParent().getRoot();
            }
            rootRoot.getViewer().setProperty("allDiagramConnections", allDiagramConnections);
        }
        return allDiagramConnections;
    }

}
