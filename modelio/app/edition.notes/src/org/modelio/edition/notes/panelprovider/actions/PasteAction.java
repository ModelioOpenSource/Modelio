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

@objid ("13b18cb1-0783-4be0-96cf-ecd1394637cb")
public class PasteAction extends Action {
    @objid ("b0085a00-9c6e-4916-a5f0-022388ee499c")
    private NotesPanelController controller;

    @objid ("55e5d749-9c1b-467c-a63f-1be8c19f1a1f")
    public PasteAction(NotesPanelController controller) {
        this.controller = controller;
        this.setText(EditionNotes.I18N.getString("PasteAnnotation.label"));
        this.setImageDescriptor(UI.getImageDescriptor("icons/paste.png"));
    }

    @objid ("57b4d1e3-34e3-4b78-9436-26a1bfd5b191")
    @Override
    public void run() {
        this.controller.onPaste();
    }

    @objid ("4f6068f7-64c2-42f9-8403-1ebb730940e1")
    @Override
    public boolean isEnabled() {
        return this.controller.canPaste();
    }

}
