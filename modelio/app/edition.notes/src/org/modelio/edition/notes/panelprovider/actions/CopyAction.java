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

@objid ("90b8543a-412d-4649-a97a-99be4a8f0ae5")
public class CopyAction extends Action {
    @objid ("62217e58-094d-4090-83a5-faa3dca4409c")
    private NotesPanelController controller;

    @objid ("eea52e5b-bda0-4041-9c40-1a9a854cd48c")
    public CopyAction(NotesPanelController controller) {
        this.controller = controller;
        this.setText(EditionNotes.I18N.getString("CopyAnnotation.label"));
        this.setImageDescriptor(UI.getImageDescriptor("icons/copy.png"));
    }

    @objid ("072b6dd9-f5b5-4919-a936-c7ec4aacd9bf")
    @Override
    public void run() {
        this.controller.onCopy();
    }

    @objid ("59ab44b0-e074-4304-9062-5546c1621977")
    @Override
    public boolean isEnabled() {
        return this.controller.canCopy();
    }

}
