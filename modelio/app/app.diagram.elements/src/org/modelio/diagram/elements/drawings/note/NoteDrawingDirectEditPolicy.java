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
package org.modelio.diagram.elements.drawings.note;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;

/**
 * {@link DirectEditPolicy} that modifies the {@link GmNoteDrawing} text.
 */
@objid ("3f74d89a-7a8b-4224-ac98-49e10a118f82")
class NoteDrawingDirectEditPolicy extends DirectEditPolicy {
    @objid ("4a285277-0329-4a4c-a14c-21173b2d7443")
    @Override
    protected Command getDirectEditCommand(DirectEditRequest request) {
        String text = (String) request.getCellEditor().getValue();
        GmNoteDrawing gm = (GmNoteDrawing) getHost().getModel();
        
        if (text!=null && gm!=null)
            return new EditCommand(gm, text);
        return null;
    }

    @objid ("6c618d51-3de9-4f11-8fa5-8f64a694352b")
    @Override
    protected void showCurrentEditValue(DirectEditRequest request) {
        // TODO Auto-generated method stub
    }

    @objid ("89ffc26a-e92f-4b03-b420-d2d7a540c739")
    private class EditCommand extends Command {
        @objid ("ed1059db-b92e-4251-8dcc-9d388a679a2e")
        private String text;

        @objid ("c028c95a-10e5-4de3-b887-aba8ca0f4330")
        private GmNoteDrawing gm;

        @objid ("20c727a3-880a-4040-ab5b-4a4b00e2b604")
        public  EditCommand(GmNoteDrawing gm, String text) {
            this.gm = gm;
            this.text = text;
            
        }

        @objid ("3e881e28-e7af-4e71-a42a-dbb43389c2aa")
        @Override
        public void execute() {
            this.gm.setLabel(this.text);
        }

    }

}
