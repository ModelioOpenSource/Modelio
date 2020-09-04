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

@objid ("824a24ef-9277-46bd-a336-d344a0a57f9d")
public class HtmlConvertAction extends Action {
    @objid ("f5f7ac51-515c-4698-9b31-fbe33736ad56")
    private NotesPanelController controller;

    @objid ("16d613da-1695-42ee-94c3-9e6f063fb617")
    public HtmlConvertAction(NotesPanelController controller) {
        this.controller = controller;
        this.setText(EditionNotes.I18N.getString("HtmlType.label"));
        this.setImageDescriptor(EditionNotes.getImageDescriptor("icons/html.png"));
    }

    @objid ("6688138d-c5ed-40f5-a0f0-0718f2bfb332")
    @Override
    public void run() {
        this.controller.onHtmlConvert();
    }

    @objid ("09ae675d-493c-4b29-a731-756389bf775e")
    @Override
    public boolean isEnabled() {
        return this.controller.canHtmlConvert();
    }

}
