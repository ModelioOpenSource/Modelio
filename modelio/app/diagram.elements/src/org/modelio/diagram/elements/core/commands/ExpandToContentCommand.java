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

package org.modelio.diagram.elements.core.commands;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;

/**
 * Commands that expands the node to fit to its content.
 * <p>
 * It won't shrink the node if the content is smaller.
 * <p>
 * The ideal size is based on {@link IFigure#getPreferredSize()}.
 * 
 * @author cmarin
 */
@objid ("7f40a14d-1dec-11e2-8cad-001ec947c8cc")
public class ExpandToContentCommand extends Command {
    @objid ("56a38ef3-2021-4dc6-a5c6-7c8f1b810c35")
    private GraphicalEditPart editPart;

    /**
     * Initialize the expand command.
     * @param editPart The graphic edit part to expand.
     */
    @objid ("7f40a154-1dec-11e2-8cad-001ec947c8cc")
    public ExpandToContentCommand(final GraphicalEditPart editPart) {
        this.editPart = editPart;
    }

    @objid ("7f40a15b-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void execute() {
        final Command resizeCommand = getResizeCommand();
        
        if (resizeCommand != null && resizeCommand.canExecute()) {
            resizeCommand.execute();
        }
    }

    /**
     * Ask for the command that will expand the node to its preferred size.
     * @return The resize command. May return <tt>null</tt>.
     */
    @objid ("7f40a15e-1dec-11e2-8cad-001ec947c8cc")
    private Command getResizeCommand() {
        final IFigure fig = this.editPart.getFigure();
        
        //fig.getUpdateManager().performValidation();
        
        final Dimension oldSize = fig.getSize();
        final Dimension newSize = fig.getPreferredSize();
        final Dimension diff = newSize.getShrinked(oldSize);
        
        if (diff.width() < 0 ) {
            diff.setWidth(0);
        }
        if (diff.height() < 0 ) {
            diff.setHeight(0);
        }
        
        if (diff.equals(0, 0)) {
            return NoopCommand.INSTANCE;
        }
        
        // Send directly a request to the parent
        fig.translateToAbsolute(diff); // request coords are in absolute coordinates
        final ChangeBoundsRequest req = new ChangeBoundsRequest(RequestConstants.REQ_RESIZE_CHILDREN);
        req.setEditParts(this.editPart);
        req.setSizeDelta(diff);
        return this.editPart.getParent().getCommand(req);
    }

    @objid ("7f40a165-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean canExecute() {
        final Command resizeCommand = getResizeCommand();
        return (resizeCommand != null && resizeCommand.canExecute());
    }

}
