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

import java.beans.PropertyChangeEvent;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.requests.ReconnectRequest;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.link.LinkEditPart;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Links to void have a special end point edit policy that allow the end point to be moved anywhere.
 */
@objid ("7ece3070-1dec-11e2-8cad-001ec947c8cc")
public class LinkToVoidEditPart extends LinkEditPart {
    @objid ("7ece3072-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public DragTracker getDragTracker(final Request req) {
        return new LinkToVoidEditPartTracker(this);
    }

    @objid ("7ece307d-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        
        installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE, new LinkToVoidEndPointEditPolicy());
    }

    @objid ("7ece3080-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(GmLink.PROP_SOURCE_EL)) {
            // If notified that the source element changed.
            // Build a reconnection request, that will be passed to edit
            // parts that might accept it.
            final Connection fig = getConnectionFigure();
            final Point loc = fig.getPoints().getFirstPoint();
            fig.translateToAbsolute(loc);
        
            final ReconnectRequest request = new ReconnectRequest(LinkToVoidConstants.REQ_LINKTOVOID_RECONNECT_SOURCE);
            request.setConnectionEditPart(this);
            request.setLocation(loc);
        
            swapEnd((MObject) evt.getNewValue(), request);
        } else if (evt.getPropertyName().equals(GmLink.PROP_TARGET_EL)) {
            // If notified that the target changed.
            // Build a reconnection request, that will be passed to edit
            // parts that might accept it.
            final Connection fig = getConnectionFigure();
            final Point loc = fig.getPoints().getLastPoint();
            fig.translateToAbsolute(loc);
        
            final ReconnectRequest request = new ReconnectRequest(LinkToVoidConstants.REQ_LINKTOVOID_RECONNECT_TARGET);
            request.setConnectionEditPart(this);
            request.setLocation(loc);
        
            swapEnd((MObject) evt.getNewValue(), request);
        
        } else {
            super.propertyChange(evt);
        }
    }

}
