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
 * Command that tries to expand or shrink the node to fit to its content.
 * <p>
 * The node will try to shrink itself if the content is smaller.
 * <p>
 * The ideal size is based on {@link IFigure#getPreferredSize()}.
 * 
 * @author cmarin
 */
@objid ("7f40a172-1dec-11e2-8cad-001ec947c8cc")
public class FitToContentCommand extends Command {
    @objid ("018c89f4-fa89-475e-a30b-a70f520fd837")
    private boolean fitHorizontal;

    @objid ("4f334dec-43b2-480b-82c9-4a4ff47998a1")
    private boolean fitVertical;

    @objid ("642b1503-1e83-11e2-8cad-001ec947c8cc")
    private GraphicalEditPart editPart;

    /**
     * Initialize the command.
     * @param fitHorizontal
     * @param fitVertical
     * @param editPart The graphic edit part to resize.
     */
    @objid ("7f40a179-1dec-11e2-8cad-001ec947c8cc")
    public FitToContentCommand(final GraphicalEditPart editPart, boolean fitHorizontal, boolean fitVertical) {
        this.editPart = editPart;
        this.fitHorizontal = fitHorizontal;
        this.fitVertical = fitVertical;
    }

    @objid ("7f40a180-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void execute() {
        final Command resizeCommand = getResizeCommand();
        
        if (resizeCommand != null && resizeCommand.canExecute()) {
            resizeCommand.execute();
        }
    }

    /**
     * Ask for the command that will resize the node to its preferred size.
     * @return The resize command. May return <tt>null</tt>.
     */
    @objid ("7f40a183-1dec-11e2-8cad-001ec947c8cc")
    private Command getResizeCommand() {
        final IFigure fig = this.editPart.getFigure();
        
        //fig.getUpdateManager().performValidation();
        
        final Dimension oldSize = fig.getSize();
        final Dimension newSize = fig.getPreferredSize().getCopy();
        
        if (!this.fitHorizontal) {
            newSize.setWidth(oldSize.width());
        }
        if (!this.fitVertical) {
            newSize.setHeight(oldSize.height());
        }
        
        if (oldSize.equals(newSize)) {
            return NoopCommand.INSTANCE;
        }
        
        Dimension diff = newSize.getShrinked(oldSize);
        fig.translateToAbsolute(diff); // request coords are in absolute coordinates
        
        final ChangeBoundsRequest req = new ChangeBoundsRequest(RequestConstants.REQ_RESIZE_CHILDREN);
        req.setEditParts(this.editPart);
        req.setSizeDelta(diff);
        return this.editPart.getParent().getCommand(req);
    }

    @objid ("7f40a18a-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean canExecute() {
        final Command resizeCommand = getResizeCommand();
        return (resizeCommand == null || resizeCommand.canExecute());
    }

}
