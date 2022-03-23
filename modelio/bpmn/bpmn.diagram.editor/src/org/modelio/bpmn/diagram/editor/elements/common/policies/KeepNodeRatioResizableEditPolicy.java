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
package org.modelio.bpmn.diagram.editor.elements.common.policies;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.modelio.diagram.elements.core.helpers.RequestHelper;
import org.modelio.diagram.elements.core.policies.DefaultNodeResizableEditPolicy;

/**
 * A specialization of {@link DefaultNodeResizableEditPolicy} keeping the width/height the same when resizing the node.
 */
@objid ("cddfd94c-49aa-41bc-9674-e67c8677712c")
public class KeepNodeRatioResizableEditPolicy extends DefaultNodeResizableEditPolicy {
    @objid ("c618e880-1613-4e02-b9e7-dbf773048d56")
    @Override
    protected Command getResizeCommand(ChangeBoundsRequest request) {
        ChangeBoundsRequest modifiedReq = new ChangeBoundsRequest(RequestConstants.REQ_RESIZE_CHILDREN);
        updateModifiedRequest(modifiedReq, request);
        return getHost().getParent().getCommand(modifiedReq);
    }

    @objid ("cc695d98-6e70-4638-ad43-1a3b82479267")
    @Override
    protected void showChangeBoundsFeedback(ChangeBoundsRequest request) {
        if (REQ_RESIZE.equals(request.getType())) {
            ChangeBoundsRequest modifiedReq = new ChangeBoundsRequest(request.getType());
            updateModifiedRequest(modifiedReq, request);
            super.showChangeBoundsFeedback(modifiedReq);
        } else {
            super.showChangeBoundsFeedback(request);
        }
        
    }

    /**
     * Update the modified request from the original request so that the node respect aspect ratio.
     * @param modifiedReq the modified request
     * @param fromRequest the original request
     */
    @objid ("d9e53c5a-4ef4-451d-81e2-9ee46a554c94")
    protected ChangeBoundsRequest updateModifiedRequest(ChangeBoundsRequest modifiedReq, ChangeBoundsRequest fromRequest) {
        modifiedReq.setEditParts(getHost());
        modifiedReq.setMoveDelta(fromRequest.getMoveDelta());
        
        int dimension = 0;
        int x = fromRequest.getSizeDelta().height;
        int y = fromRequest.getSizeDelta().width;
        
        if (x >= 0 && y >= 0) {
            if (x > y) {
                dimension = x;
            } else {
                dimension = y;
            }
        } else {
            if (x < y) {
                dimension = x;
            } else {
                dimension = y;
            }
        }
        
        modifiedReq.setSizeDelta(new Dimension(dimension, dimension));
        modifiedReq.setLocation(fromRequest.getLocation());
        modifiedReq.setResizeDirection(fromRequest.getResizeDirection());
        modifiedReq.setExtendedData(fromRequest.getExtendedData());
        RequestHelper.addSharedEditParts(modifiedReq, fromRequest);
        return modifiedReq;
    }

}
