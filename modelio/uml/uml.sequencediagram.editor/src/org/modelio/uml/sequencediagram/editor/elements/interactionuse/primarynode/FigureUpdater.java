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
package org.modelio.uml.sequencediagram.editor.elements.interactionuse.primarynode;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutListener.Stub;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionUse;

@objid ("d9239425-55b6-11e2-877f-002564c97630")
class FigureUpdater extends Stub {
    @objid ("3a7eb701-aad7-4349-b789-2c29a3a42556")
    private GraphicalEditPart editPart;

    @objid ("d9239427-55b6-11e2-877f-002564c97630")
    public  FigureUpdater(final GraphicalEditPart editPart) {
        super();
        this.editPart = editPart;
        
    }

    @objid ("d9251abb-55b6-11e2-877f-002564c97630")
    @Override
    public void postLayout(final IFigure container) {
        container.removeLayoutListener(this);
        // TODO read informations from model and if needed ask for a resize.
        
        int top = container.getBounds().y;
        int bottom = container.getBounds().bottom();
        
        InteractionUse interactionUse = (InteractionUse) ((GmModel) this.editPart.getModel()).getRelatedElement();
        int startLineNumber = interactionUse.getLineNumber();
        int endLineNumber = interactionUse.getEndLineNumber();
        
        if (top != startLineNumber || bottom != endLineNumber) {
            // Request a resize to match the model!
            ChangeBoundsRequest request = new ChangeBoundsRequest(RequestConstants.REQ_RESIZE);
            request.setEditParts(this.editPart.getParent());
            request.getMoveDelta().setY(startLineNumber - top);
            request.getSizeDelta().setHeight((endLineNumber - startLineNumber) - (bottom - top));
            Command command = this.editPart.getParent().getCommand(request);
            if (command != null && command.canExecute()) {
                command.execute();
            }
        
        }
        
    }

}
