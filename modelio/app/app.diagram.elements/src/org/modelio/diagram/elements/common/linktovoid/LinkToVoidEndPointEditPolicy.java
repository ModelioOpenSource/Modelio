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

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ConnectionLocator;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy;
import org.eclipse.gef.handles.ConnectionEndpointHandle;
import org.eclipse.gef.requests.ReconnectRequest;
import org.modelio.diagram.elements.core.policies.SelectionHandlesBuilder;

/**
 * A selection handle policy for placing handles at the target end of a {@link LinkToVoidEditPart}. All
 * LinkToVoidEditParts should have one of these, even if the ends of the connection aren't draggable, because this is
 * the primary SelectionEditPolicy for showing focus.
 * <P>
 * A connection can receive focus but not selection by pressing <code>Control+/</code> on the keyboard.
 * 
 * @since 2.0
 */
@objid ("7ed092c4-1dec-11e2-8cad-001ec947c8cc")
public class LinkToVoidEndPointEditPolicy extends ConnectionEndpointEditPolicy {
    @objid ("7ed092c8-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void eraseSourceFeedback(final Request request) {
        if (REQ_RECONNECT_SOURCE.equals(request.getType()) ||
            REQ_RECONNECT_TARGET.equals(request.getType()) ||
            LinkToVoidConstants.REQ_LINKTOVOID_RECONNECT_SOURCE.equals(request.getType()) ||
            LinkToVoidConstants.REQ_LINKTOVOID_RECONNECT_TARGET.equals(request.getType())) {
            eraseConnectionMoveFeedback((ReconnectRequest) request);
        }
        
    }

    @objid ("7ed092cf-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void showSourceFeedback(final Request request) {
        if (REQ_RECONNECT_SOURCE.equals(request.getType()) ||
            REQ_RECONNECT_TARGET.equals(request.getType()) ||
            LinkToVoidConstants.REQ_LINKTOVOID_RECONNECT_SOURCE.equals(request.getType()) ||
            LinkToVoidConstants.REQ_LINKTOVOID_RECONNECT_TARGET.equals(request.getType())) {
            showConnectionMoveFeedback((ReconnectRequest) request);
        }
        
    }

    @objid ("7ed092d6-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected List createSelectionHandles() {
        List<ConnectionEndpointHandle> list = new ArrayList<>();
        
        list.add(new ConnectionEndpointHandle((ConnectionEditPart) getHost(), ConnectionLocator.SOURCE));
        list.add(new LinkToVoidEndPointHandle((ConnectionEditPart) getHost(), ConnectionLocator.TARGET));
        
        SelectionHandlesBuilder.disableHandlesIfReadOnly(getHost(), list);
        return list;
    }

}
