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
package org.modelio.diagram.elements.core.link;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionRouter;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.modelio.diagram.elements.core.figures.routers.AutoOrthogonalRouter;
import org.modelio.diagram.elements.core.figures.routers.OrthogonalRouter;
import org.modelio.diagram.elements.core.figures.routers.RakeRouter;
import org.modelio.diagram.elements.core.helpers.RequestHelper;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.plugin.DiagramElements;
import org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId;

/**
 * Services to extract the connection routing mode from an edit part , a figure, a request ...
 */
@objid ("b4a05426-c723-4017-8b0b-b56e3be5153a")
public class RoutingModeGetter {
    /**
     * Get the routing mode from a connection edit part.
     * @param ep an edit part
     * @return the connection routing mode
     */
    @objid ("d1cbdebd-6ce8-4ad3-95cf-8e076f3c90b1")
    public static ConnectionRouterId fromEditPart(ConnectionEditPart ep) {
        if (ep.getModel() instanceof IGmLink) {
            IGmLink m = (IGmLink) ep.getModel();
            return m.getPath().getRouterKind();
        }
        return fromFigure((Connection) ep.getFigure());
    }

    /**
     * Get the routing mode a reconnect request.
     * @param req a GEF request
     * @return the connection routing mode
     */
    @objid ("a463fc68-37a6-4ce6-8731-684a4c655c1c")
    public static ConnectionRouterId fromBendedConnectionRequest(CreateBendedConnectionRequest req) {
        return req.getData().getRoutingMode();
    }

    /**
     * Get the routing mode a reconnect request.
     * @param req a GEF request
     * @return the connection routing mode
     */
    @objid ("4984da04-9f21-443c-8c45-7391dbaf11df")
    public static ConnectionRouterId fromReconnectRequest(ReconnectRequest req) {
        return fromEditPart(req.getConnectionEditPart());
    }

    /**
     * Guess the routing mode from any request.
     * @param req a GEF request
     * @return the connection routing mode
     */
    @objid ("1aa57d98-3609-4c36-a47b-f80fd21900a2")
    public static ConnectionRouterId fromRequest(Request req) {
        if (req instanceof ReconnectRequest) {
            return fromReconnectRequest((ReconnectRequest) req);
        } else if (req instanceof CreateBendedConnectionRequest) {
            return fromBendedConnectionRequest((CreateBendedConnectionRequest) req);
        } else if (req instanceof CreateConnectionRequest || req instanceof ChangeBoundsRequest) {
            return ConnectionRouterId.ORTHOGONAL;
        } else {
            // default case
        
            // debug trace
            if (DiagramElements.LOG.isDebugEnabled())
                DiagramElements.LOG.debug(new IllegalArgumentException(RequestHelper.toString(req)));
        
            return ConnectionRouterId.ORTHOGONAL;
        }
        
    }

    /**
     * Get the routing mode from the connection figure.
     * <p>
     * @param conn the connection figure
     * @return the connection routing mode
     * @deprecated does not work with new auto orthogonal routing
     */
    @objid ("ad17a6c1-968e-4ea2-9e95-9c2843914e6a")
    @Deprecated
    private static ConnectionRouterId fromFigure(Connection conn) {
        ConnectionRouter router = conn.getConnectionRouter();
        if (router instanceof RakeRouter || router instanceof OrthogonalRouter || router instanceof AutoOrthogonalRouter) {
            return ConnectionRouterId.ORTHOGONAL;
        } else if (router instanceof BendpointConnectionRouter) {
            return ConnectionRouterId.BENDPOINT;
        } else if (router == null || router instanceof ConnectionRouter.NullConnectionRouter) {
            return ConnectionRouterId.DIRECT;
        } else {
            throw new IllegalArgumentException(router + " not handled.");
        }
        
    }

}
