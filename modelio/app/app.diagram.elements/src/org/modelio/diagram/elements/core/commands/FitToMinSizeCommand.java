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
package org.modelio.diagram.elements.core.commands;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.handles.HandleBounds;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.modelio.diagram.elements.common.portcontainer.GmPortContainer;
import org.modelio.diagram.elements.core.node.GmNodeModel;

/**
 * Command that tries to expand or shrink the node to fit to its minimum size.
 * <p>
 * The node will try to shrink itself if its minimum size is smaller.
 * <p>
 * The minimum size is based on {@link IFigure#getMinimumSize()}.
 * 
 * @author cmarin
 */
@objid ("7f4303a8-1dec-11e2-8cad-001ec947c8cc")
public class FitToMinSizeCommand extends Command {
    @objid ("670e2dfd-1e83-11e2-8cad-001ec947c8cc")
    private GraphicalEditPart editPart;

    /**
     * Initialize the command.
     * @param editPart The graphic edit part to resize.
     */
    @objid ("7f4303af-1dec-11e2-8cad-001ec947c8cc")
    public  FitToMinSizeCommand(final GraphicalEditPart editPart) {
        this.editPart = editPart;
    }

    @objid ("7f4303b6-1dec-11e2-8cad-001ec947c8cc")
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
    @objid ("7f4303b9-1dec-11e2-8cad-001ec947c8cc")
    private Command getResizeCommand() {
        final IFigure fig = this.editPart.getFigure();
        
        //fig.getUpdateManager().performValidation();
        
        Dimension oldSize = getEffectiveBounds(fig).getSize();
        Dimension newSize = getMinimumSize(fig).getCopy();
        fig.translateToAbsolute(oldSize);
        fig.translateToAbsolute(newSize);
        if (oldSize.equals(newSize)) {
            return NoopCommand.INSTANCE;
        }
        
        final ChangeBoundsRequest req = new ChangeBoundsRequest(RequestConstants.REQ_RESIZE);
        req.setEditParts(this.editPart);
        req.setSizeDelta(newSize.getCopy().shrink(oldSize));
        return this.editPart.getCommand(req);
    }

    @objid ("7f4303c0-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean canExecute() {
        final Command resizeCommand = getResizeCommand();
        return (resizeCommand == null || resizeCommand.canExecute());
    }

    @objid ("7f4303c5-1dec-11e2-8cad-001ec947c8cc")
    private Dimension getMinimumSize(final IFigure figure) {
        if (this.editPart.getModel() instanceof GmPortContainer) {
            // For PortContainers, we must take the main node minimum size to avoid side effects during the setSize
            GmPortContainer gpc = (GmPortContainer) this.editPart.getModel();
            GmNodeModel mainNode = gpc.getMainNode();
        
            if (mainNode != null) {
                GraphicalEditPart mainNodeEditPart = (GraphicalEditPart) this.editPart.getViewer()
                                                                                      .getEditPartRegistry()
                                                                                      .get(mainNode);
        
                if (mainNodeEditPart != null) {
                    IFigure mainFig = mainNodeEditPart.getFigure();
                    return mainFig.getMinimumSize();
                }
            }
        }
        return figure.getMinimumSize();
    }

    @objid ("7f4303cf-1dec-11e2-8cad-001ec947c8cc")
    protected Rectangle getEffectiveBounds(final IFigure figure) {
        return (figure instanceof HandleBounds) ? ((HandleBounds) figure).getHandleBounds().getCopy()
                                                                : figure.getBounds().getCopy();
    }

}
