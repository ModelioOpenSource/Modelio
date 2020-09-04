/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.diagram.elements.drawings.core.node;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;

/**
 * {@link DirectEditPolicy} that modifies the {@link GmNodeDrawing} text.
 */
@objid ("b6f3ae11-6b5b-49bc-8253-e90d77a9d65d")
class NodeLabelDirectEditPolicy extends DirectEditPolicy {
    @objid ("21945d66-d6c4-4e03-98a2-42b2c1df1d36")
    @Override
    protected Command getDirectEditCommand(DirectEditRequest request) {
        String text = (String) request.getCellEditor().getValue();
        GmNodeDrawing gm = (GmNodeDrawing) getHost().getModel();
        
        if (text!=null && gm!=null)
            return new EditCommand(gm, text);
        return null;
    }

    @objid ("c319e5f7-8f7e-4568-9ee0-576768a386c0")
    @Override
    protected void showCurrentEditValue(DirectEditRequest request) {
        // ignore
    }

    @objid ("370fb209-7eb6-4078-b501-e5a21b7213ef")
    private class EditCommand extends Command {
        @objid ("6de0c497-37d9-4c54-ab62-d1d5d1a4b99e")
        private String text;

        @objid ("1ec93cae-7068-40f7-ad64-5d886bdcbab0")
        private GmNodeDrawing gm;

        @objid ("4f5ed59a-1ce3-4f40-b005-bc4f77031961")
        public EditCommand(GmNodeDrawing gm, String text) {
            this.gm = gm;
            this.text = text;
        }

        @objid ("437a798a-95b4-4f88-823f-79c5ade08b66")
        @Override
        public void execute() {
            this.gm.setLabel(this.text);
        }

    }

}
