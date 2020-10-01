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

package org.modelio.diagram.elements.drawings.text;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;

/**
 * {@link DirectEditPolicy} that modifies the {@link GmTextDrawing} text.
 */
@objid ("7ee3f712-d6ff-4966-915b-a7c7fb4a0634")
class TextDrawingDirectEditPolicy extends DirectEditPolicy {
    @objid ("c42db7bb-92c8-4c7a-958c-6768695715b8")
    @Override
    protected Command getDirectEditCommand(DirectEditRequest request) {
        String text = (String) request.getCellEditor().getValue();
        GmTextDrawing gm = (GmTextDrawing) getHost().getModel();
        
        if (text!=null && gm!=null)
            return new EditCommand(gm, text);
        return null;
    }

    @objid ("3840a230-bf21-4b82-98b0-37f12bb8fe78")
    @Override
    protected void showCurrentEditValue(DirectEditRequest request) {
        // TODO Auto-generated method stub
    }

    @objid ("c2fbf300-e8be-4d60-93c6-f51c6f210769")
    private class EditCommand extends Command {
        @objid ("950b5d78-313c-4834-9556-cfb0574a8750")
        private String text;

        @objid ("f7696ebb-52e6-4bb4-9783-7bcba1301c0f")
        private GmTextDrawing gm;

        @objid ("6ade4d41-95bc-480d-bb7f-7830fade35a1")
        public EditCommand(GmTextDrawing gm, String text) {
            this.gm = gm;
            this.text = text;
        }

        @objid ("dc264fdc-c643-41c7-bb32-4801471701fc")
        @Override
        public void execute() {
            this.gm.setLabel(this.text);
        }

    }

}
