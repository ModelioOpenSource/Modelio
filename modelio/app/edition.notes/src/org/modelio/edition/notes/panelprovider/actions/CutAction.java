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

package org.modelio.edition.notes.panelprovider.actions;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.action.Action;
import org.modelio.edition.notes.panelprovider.NotesPanelController;
import org.modelio.edition.notes.plugin.EditionNotes;
import org.modelio.ui.plugin.UI;

@objid ("4cccbcce-5ef3-4ccf-b338-fc314e81b46c")
public class CutAction extends Action {
    @objid ("47a63699-2921-4992-abf5-aa0ce1549831")
    private NotesPanelController controller;

    @objid ("b633d031-1163-443b-a400-f8f1d514b53e")
    public CutAction(NotesPanelController controller) {
        this.controller = controller;
        this.setText(EditionNotes.I18N.getString("CutAnnotation.label"));
        this.setImageDescriptor(UI.getImageDescriptor("icons/cut.png"));
    }

    @objid ("3d9036aa-fd2c-4db4-91d3-fdce2161cef7")
    @Override
    public void run() {
        this.controller.onCut();
    }

    @objid ("6eae8abe-1079-479f-b995-e7c14bd5d619")
    @Override
    public boolean isEnabled() {
        return this.controller.canCut();
    }

}
