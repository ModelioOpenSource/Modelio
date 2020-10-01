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

package org.modelio.diagram.elements.common.linktovoid;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.requests.ReconnectRequest;
import org.modelio.diagram.elements.core.figures.anchors.SatelliteAnchor;
import org.modelio.diagram.elements.core.link.SelectConnectionEditPartTracker;

/**
 * Edit part tracker for {@link LinkToVoidEditPart}.
 * <p>
 * Produces {@link LinkToVoidConstants#REQ_LINKTOVOID_RECONNECT_SOURCE} and
 * {@link LinkToVoidConstants#REQ_LINKTOVOID_RECONNECT_TARGET} requests.
 * 
 * @author cmarin
 */
@objid ("7ece308c-1dec-11e2-8cad-001ec947c8cc")
public class LinkToVoidEditPartTracker extends SelectConnectionEditPartTracker {
    /**
     * Initialize the tracker.
     * 
     * @param owner Connection edit part that creates and owns the tracker object
     */
    @objid ("7ece308e-1dec-11e2-8cad-001ec947c8cc")
    public LinkToVoidEditPartTracker(final ConnectionEditPart owner) {
        super(owner);
    }

    @objid ("7ece3095-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void handleButtonDownOnSource(final int button) {
        super.handleButtonDownOnSource(button);
        
        if (getConnection().getSourceAnchor() instanceof SatelliteAnchor)
            setType(LinkToVoidConstants.REQ_LINKTOVOID_RECONNECT_SOURCE);
    }

    @objid ("7ece309a-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void handleButtonDownOnTarget(final int button) {
        super.handleButtonDownOnTarget(button);
        
        //if (getConnection().getTargetAnchor() instanceof SatelliteAnchor)
        setType(LinkToVoidConstants.REQ_LINKTOVOID_RECONNECT_TARGET);
    }

    @objid ("7ece309f-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Request createSourceRequest() {
        final String type = getType();
        if (type.equals(LinkToVoidConstants.REQ_LINKTOVOID_RECONNECT_SOURCE) ||
            type.equals(LinkToVoidConstants.REQ_LINKTOVOID_RECONNECT_TARGET)) {
        
            final ReconnectRequest req = new LinkToVoidReconnectRequest();
            req.setConnectionEditPart(getConnectionEditPart());
            req.setType(type);
        
            return req;
        } else {
            return super.createSourceRequest();
        }
    }

    /**
     * A Request to reconnect a connection.
     * <p>
     * Adapted to handle links without target.
     */
    @objid ("7ece30a5-1dec-11e2-8cad-001ec947c8cc")
    private static class LinkToVoidReconnectRequest extends ReconnectRequest {
        @objid ("7ece30aa-1dec-11e2-8cad-001ec947c8cc")
        public LinkToVoidReconnectRequest() {
        }

        @objid ("7ece30ac-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public boolean isMovingStartAnchor() {
            return LinkToVoidConstants.REQ_LINKTOVOID_RECONNECT_SOURCE.equals(getType());
        }

    }

}
