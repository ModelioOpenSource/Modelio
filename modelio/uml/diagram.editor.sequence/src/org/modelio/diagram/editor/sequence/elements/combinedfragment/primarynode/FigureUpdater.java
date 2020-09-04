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

package org.modelio.diagram.editor.sequence.elements.combinedfragment.primarynode;

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
import org.modelio.metamodel.uml.behavior.interactionModel.CombinedFragment;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionOperand;

@objid ("d8cb13e6-55b6-11e2-877f-002564c97630")
class FigureUpdater extends Stub {
    @objid ("d871a126-fc72-47f5-879d-14941af0ec2d")
    private GraphicalEditPart editPart;

    @objid ("d8cb13e8-55b6-11e2-877f-002564c97630")
    public FigureUpdater(final GraphicalEditPart editPart) {
        super();
        this.editPart = editPart;
    }

    @objid ("d8cb13ec-55b6-11e2-877f-002564c97630")
    @Override
    public void postLayout(final IFigure container) {
        container.removeLayoutListener(this);
        // TODO read informations from model and if needed ask for a resize.
        
        IFigure operandContainer = (IFigure) container.getChildren().get(1);
        int top = operandContainer.getBounds().y;
        int height = operandContainer.getBounds().getSize().height;
        CombinedFragment combinedFragment = (CombinedFragment) ((GmModel) this.editPart.getModel()).getRelatedElement();
        int startLineNumber = combinedFragment.getLineNumber();
        int actualFragmentSize = 0;
        if (combinedFragment.getOperand().size() > 0) {
            for (InteractionOperand operand : combinedFragment.getOperand()) {
                ++actualFragmentSize;
                actualFragmentSize += operand.getEndLineNumber() - operand.getLineNumber();
            }
            --actualFragmentSize;
        }
        if (top != startLineNumber || height != actualFragmentSize) {
            // Request a resize to match the model!
            ChangeBoundsRequest request = new ChangeBoundsRequest(RequestConstants.REQ_RESIZE);
            request.setCenteredResize(false);
            request.setConstrainedMove(false);
            request.setConstrainedResize(false);
            request.setEditParts(this.editPart.getParent());
            request.setMoveDelta(new Point(0, startLineNumber - top));
            request.setSizeDelta(new Dimension(0, actualFragmentSize - height));
            Command command = this.editPart.getParent().getCommand(request);
            if (command != null && command.canExecute()) {
                command.execute();
            }
        
        }
    }

}
