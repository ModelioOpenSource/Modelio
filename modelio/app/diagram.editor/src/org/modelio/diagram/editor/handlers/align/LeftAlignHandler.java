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

package org.modelio.diagram.editor.handlers.align;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.ChangeBoundsRequest;

@objid ("65b2185a-33f7-11e2-95fe-001ec947c8cc")
public class LeftAlignHandler extends AbstractAlignHandler {
    @objid ("65b2185b-33f7-11e2-95fe-001ec947c8cc")
    @Override
    protected void align(GraphicalEditPart primarySelection, List<GraphicalEditPart> otherSelections) {
        IFigure primaryFigure = primarySelection.getFigure();
        Rectangle primaryBounds = getEffectiveBounds(primaryFigure);
        primaryFigure.translateToAbsolute(primaryBounds);
        
        CompoundCommand compound = new CompoundCommand("Align left");
        for (GraphicalEditPart editPart : otherSelections) {
            IFigure figure = editPart.getFigure();
            Rectangle bounds = getEffectiveBounds(figure);
            figure.translateToAbsolute(bounds);
            Point moveDelta = new Point(primaryBounds.x - bounds.x, 0);
            ChangeBoundsRequest req = new ChangeBoundsRequest(RequestConstants.REQ_MOVE);
            req.setEditParts(editPart);
            req.setMoveDelta(moveDelta);
            req.setSizeDelta(new Dimension(0, 0));
            compound.add(editPart.getCommand(req));
        }
        if (compound.canExecute()) {
            primarySelection.getViewer().getEditDomain().getCommandStack().execute(compound);
        }
    }

}
