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
import org.modelio.ui.plugin.UI;

@objid ("2672e72f-3b5a-4018-b1c1-df18156ec872")
public class RemoveAnnotationAction extends Action {
    @objid ("80e921ea-a797-4350-8cd3-40c0bee564a5")
    private NotesPanelController controller;

    @objid ("78063f3f-b8a9-444c-822a-9173a6698d06")
    public RemoveAnnotationAction(NotesPanelController controller) {
        this.controller = controller;
        this.setText(EditionNotes.I18N.getString("RemoveAnnotation.label"));
        this.setImageDescriptor(UI.getImageDescriptor("icons/delete.png"));
    }

    @objid ("221a8cbd-f7d1-4a7c-a774-761a8df7381d")
    @Override
    public void run() {
        this.controller.onRemoveAnnotation();
    }

    @objid ("6ee50027-3e53-4f0b-8a7e-8a5c463441d3")
    @Override
    public boolean isEnabled() {
        return this.controller.canRemoveAnnotation();
    }

}
