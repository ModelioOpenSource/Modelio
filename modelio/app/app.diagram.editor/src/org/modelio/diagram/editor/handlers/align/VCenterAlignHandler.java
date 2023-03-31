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
package org.modelio.diagram.editor.handlers.align;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.modelio.diagram.elements.core.helpers.RequestHelper;

@objid ("66602400-33f7-11e2-95fe-001ec947c8cc")
public class VCenterAlignHandler extends AbstractAlignHandler {
    @objid ("66602401-33f7-11e2-95fe-001ec947c8cc")
    @Override
    protected void align(GraphicalEditPart primarySelection, List<GraphicalEditPart> otherSelections) {
        IFigure primaryFigure = primarySelection.getFigure();
        PrecisionRectangle primaryBounds = getEffectiveBounds(primaryFigure);
        primaryFigure.translateToAbsolute(primaryBounds);
        
        ChangeBoundsRequest parentRequest = new ChangeBoundsRequest(RequestConstants.REQ_MOVE);
        parentRequest.setEditParts(otherSelections);
        
        CompoundCommand compound = new CompoundCommand("Align vertical center");
        for (GraphicalEditPart editPart : otherSelections) {
            IFigure figure = editPart.getFigure();
            PrecisionRectangle bounds = getEffectiveBounds(figure);
            figure.translateToAbsolute(bounds);
        
            Point moveDelta = new PrecisionPoint(0,
                    primaryBounds.preciseY() +
                    primaryBounds.preciseHeight() / 2 -
                    (bounds.preciseY() + bounds.preciseHeight() / 2));
        
            ChangeBoundsRequest req = new ChangeBoundsRequest(RequestConstants.REQ_MOVE);
            req.setEditParts(editPart);
            req.setMoveDelta(moveDelta);
            RequestHelper.addSharedEditParts(req, parentRequest);
        
            compound.add(editPart.getCommand(req));
        }
        if (compound.canExecute()) {
            primarySelection.getViewer().getEditDomain().getCommandStack().execute(compound);
        }
        
    }

}
