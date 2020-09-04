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

package org.modelio.diagram.elements.common.portcontainer;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.modelio.diagram.elements.core.helpers.RequestHelper;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;

/**
 * Specific Command that make the port container try to resize itself so that it contains all children. This has to be
 * done at the very last moment, since some children might change their mind between the moment the command is emitted
 * and the moment it is actually executed (think ExpansionNode which is moved from a vertical border to an horizontal
 * border).
 */
@objid ("92bce932-7383-449d-933f-ce2738a07283")
class LastMinuteContainerAutoResizeCommand extends Command {
    @objid ("12caebc4-73c2-41e3-8a38-2f99a8912db1")
    private PortContainerEditPart host;

    @objid ("84186a9b-281d-466a-b601-c5be9f21617e")
    private Rectangle previousTrimmedBounds;

    /**
     * C'tor.
     * @param host the host on which to apply the autoresize.
     */
    @objid ("02137fbd-ff50-47f1-825b-0a68aa02375b")
    public LastMinuteContainerAutoResizeCommand(PortContainerEditPart host) {
        this.host = host;
        this.previousTrimmedBounds = host.getTrimmedBounds().getCopy();
        host.getFigure().translateToAbsolute(this.previousTrimmedBounds);
    }

    @objid ("05b522c7-e557-45f6-a793-acfaf8fc14b5")
    private PortContainerEditPart getHost() {
        return this.host;
    }

    @objid ("1bae9a3f-b2cd-41a1-8770-5bcd37f7f9cb")
    private PortContainerFigure getHostFigure() {
        return this.host.getPortContainerFigure();
    }

    @objid ("1c8ce6d0-837c-4a6f-922f-6538007ccc6c")
    @Override
    public void execute() {
        PortContainerFigure containerFigure = getHostFigure();
        
        // 1 - container may need to be layouted itself
        containerFigure.getUpdateManager().performValidation();
        
        // 2 - given the child new constraint, compute the "updated" bounds of
        // the container, in absolute coordinates
        PortContainerLayout portContainerLayout = containerFigure.getPortContainerLayout();
        Rectangle updatedContainerBounds = portContainerLayout.getPreferredBounds(containerFigure);
        
        // 3 - Compute the difference between old and updated bounds,
        // and request a resize of container to its parent.
        // Note: let's just hope the parent does accept RESIZE_CHILDREN requests... :/
        ChangeBoundsRequest req = new ChangeBoundsRequest(RequestConstants.REQ_RESIZE_CHILDREN);
        req.setEditParts(getHost());
        RequestHelper.setDeltas(req, containerFigure, updatedContainerBounds);
        
        // In case parent is a port container itself, it might need to know the modification of the main node bounds
        PortResizeHelper.putMainNodeBounds(getHost(), req);
        
        // Compute new trimmed bounds.
        // Note: the cached may be obsolete, the below ones are directly usable.
        Rectangle trimmedBounds2 = getHost().computeTrimmedBounds(null, null);
        getHost().getFigure().translateToAbsolute(trimmedBounds2);
        req.getExtendedData().put(AbstractNodeEditPart.REQPROP_TRIMMED_BOUNDS, trimmedBounds2);
        req.getExtendedData().put(AbstractNodeEditPart.REQPROP_OLD_TRIMMED_BOUNDS, this.previousTrimmedBounds);
        
        
        // Ask parent for a command
        Command resizeContainerCommand = getHost().getParent().getCommand(req);
        
        // 4 - If container is about to be translated (moveDelta != (0,0)), all
        // children need to be translated oppositely so they stay visually at
        // the same place.
        Command translateChildrenCommand = null;
        if (!req.getMoveDelta().equals(0, 0)) {
            translateChildrenCommand = new TranslateChildrenCommand(getHost(), req.getMoveDelta().getNegated());
        }
        
        // 5 - Run all commands
        if (resizeContainerCommand != null && resizeContainerCommand.canExecute()) {
            resizeContainerCommand.execute();
            if (translateChildrenCommand != null && translateChildrenCommand.canExecute()) {
                translateChildrenCommand.execute();
            }
        }
    }

}
