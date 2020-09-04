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

package org.modelio.edition.notes.handlers;

import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.modelio.edition.notes.panelprovider.NotesPanelController;
import org.modelio.edition.notes.panelprovider.NotesPanelProvider;
import org.modelio.edition.notes.view.NotesView;

/**
 * Delete annotation(s) from the selected element.
 */
@objid ("26dec2d1-186f-11e2-bc4e-002564c97630")
public class RemoveAnnotationHandler {
    @objid ("2130107d-a7a1-41f2-bbb9-aab3bbe0d33c")
    @Execute
    protected void execute(@Named(IServiceConstants.ACTIVE_PART) final MPart part) {
        NotesPanelProvider notesPanel = ((NotesView) part.getObject()).getNotesPanel();
        NotesPanelController controller = notesPanel.getController();
        controller.onRemoveAnnotation();
    }

    @objid ("ad5afe5d-bda6-46d7-9bd3-06a7167357e7")
    @CanExecute
    protected boolean canExecute(@Named(IServiceConstants.ACTIVE_PART) final MPart part) {
        if (part == null || !(part.getObject() instanceof NotesView)) {
            return false;
        }
        
        NotesPanelProvider notesPanel = ((NotesView) part.getObject()).getNotesPanel();
        NotesPanelController controller = notesPanel.getController();
        return controller.canRemoveAnnotation();
    }

}
