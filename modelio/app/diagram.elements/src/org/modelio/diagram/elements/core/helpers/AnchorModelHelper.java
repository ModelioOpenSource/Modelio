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

package org.modelio.diagram.elements.core.helpers;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.modelio.diagram.elements.core.link.IAnchorModelProvider;

/**
 * Helper class to get anchor models from connection requests.
 * 
 * @author cmarin
 */
@objid ("7fd210b8-1dec-11e2-8cad-001ec947c8cc")
public class AnchorModelHelper {
    /**
     * Get a source anchor model for the given request.
     * @param sourceEditPart The source node edit part.
     * @param req a create connection request.
     * @return the anchor model.
     */
    @objid ("7fd210ba-1dec-11e2-8cad-001ec947c8cc")
    public static Object getSourceAnchorModel(final EditPart sourceEditPart, final CreateConnectionRequest req) {
        if (sourceEditPart instanceof NodeEditPart) {
            NodeEditPart sourceNodePart = (NodeEditPart) sourceEditPart;
            ConnectionAnchor srcAnchor = sourceNodePart.getSourceConnectionAnchor(req);
            return getAnchorModel(sourceNodePart, srcAnchor);
        } else {
            return null;
        }
    }

    /**
     * Get a target anchor model for the given request.
     * @param req a create connection request.
     * @return the anchor model.
     */
    @objid ("7fd210c6-1dec-11e2-8cad-001ec947c8cc")
    public static Object getTargetAnchorModel(final CreateConnectionRequest req) {
        NodeEditPart targetPart = (NodeEditPart) req.getTargetEditPart();
        ConnectionAnchor targetAnchor = targetPart.getTargetConnectionAnchor(req);
        return getAnchorModel(targetPart, targetAnchor);
    }

    /**
     * Get the anchor model for the given anchor.
     * @param editpart a node edit part.
     * @param anchor a draw2d anchor
     * @return the anchor model, may be <code>null</code>
     */
    @objid ("7fd210cf-1dec-11e2-8cad-001ec947c8cc")
    private static Object getAnchorModel(final NodeEditPart editpart, final ConnectionAnchor anchor) {
        if (editpart instanceof IAnchorModelProvider) {
            return (((IAnchorModelProvider) editpart).createAnchorModel(anchor));
        } else {
            return (null); // TODO handle non IAnchorModelProvider
        }
    }

    /**
     * Get a target anchor model for the given reconnection request.
     * @param req a create connection request.
     * @return the anchor model.
     */
    @objid ("7fd210db-1dec-11e2-8cad-001ec947c8cc")
    public static Object getTargetAnchorModel(final ReconnectRequest req) {
        NodeEditPart targetPart = (NodeEditPart) req.getTarget();
        ConnectionAnchor targetAnchor = targetPart.getTargetConnectionAnchor(req);
        return getAnchorModel(targetPart, targetAnchor);
    }

    /**
     * Get a target anchor model for the given reconnection request.
     * @param req a create connection request.
     * @return the anchor model.
     */
    @objid ("7fd210e4-1dec-11e2-8cad-001ec947c8cc")
    public static Object getSourceAnchorModel(final ReconnectRequest req) {
        NodeEditPart targetPart = (NodeEditPart) req.getTarget();
        ConnectionAnchor targetAnchor = targetPart.getSourceConnectionAnchor(req);
        return getAnchorModel(targetPart, targetAnchor);
    }

}
