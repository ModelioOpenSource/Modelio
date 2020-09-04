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

package org.modelio.diagram.editor.sequence.elements.interactionuse.gate;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutListener.Stub;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.metamodel.uml.behavior.interactionModel.Gate;

@objid ("d912cb5f-55b6-11e2-877f-002564c97630")
class FigureUpdater extends Stub {
    @objid ("1ba46be0-1423-4c0a-90e3-7071888ea114")
    private GraphicalEditPart editPart;

    @objid ("d912cb61-55b6-11e2-877f-002564c97630")
    public FigureUpdater(final GraphicalEditPart editPart) {
        super();
        this.editPart = editPart;
    }

    @objid ("d912cb65-55b6-11e2-877f-002564c97630")
    @Override
    public void postLayout(final IFigure container) {
        container.removeLayoutListener(this);
        // TODO read informations from model and if needed ask for a resize.
        
        int top = container.getBounds().getCenter().y;
        Gate gate = (Gate) ((GmModel) this.editPart.getModel()).getRelatedElement();
        int startLineNumber = gate.getLineNumber();
        
        if (top != startLineNumber) {
            // Request a resize to match the model!
            ChangeBoundsRequest request = new ChangeBoundsRequest(RequestConstants.REQ_MOVE);
            request.setCenteredResize(false);
            request.setConstrainedMove(false);
            request.setConstrainedResize(false);
            request.setEditParts(this.editPart.getParent());
            request.setMoveDelta(new Point(0, startLineNumber - top));
            request.setSizeDelta(new Dimension(0, 0));
            Command command = this.editPart.getParent().getCommand(request);
            if (command != null && command.canExecute()) {
                command.execute();
            }
        
        }
    }

}
