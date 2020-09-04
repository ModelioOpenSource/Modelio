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

package org.modelio.edition.notes.panelprovider.actions;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.action.Action;
import org.modelio.edition.notes.panelprovider.NotesPanelController;
import org.modelio.edition.notes.plugin.EditionNotes;

@objid ("ea21c81e-4336-447e-875b-7e27ba4815e4")
public class JyExecAction extends Action {
    @objid ("68f26282-f79c-4277-ae7d-171641e6131d")
    private NotesPanelController controller;

    @objid ("ac11cbf8-8d54-45f5-ac67-1466e50350d9")
    public JyExecAction(NotesPanelController controller) {
        this.controller = controller;
        this.setText(EditionNotes.I18N.getString("JyExec.label"));
        this.setImageDescriptor(EditionNotes.getImageDescriptor("icons/jyexec.png"));
    }

    @objid ("2092fea8-4749-4f08-8d17-86cac957ef47")
    @Override
    public void run() {
        this.controller.onJyExec();
    }

    @objid ("a35070b0-c8e7-4d4f-90c7-31ee2777cf98")
    @Override
    public boolean isEnabled() {
        return this.controller.canJyExec();
    }

}
