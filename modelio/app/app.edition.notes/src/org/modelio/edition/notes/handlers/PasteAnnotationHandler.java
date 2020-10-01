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

package org.modelio.edition.notes.handlers;

import javax.inject.Inject;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.swt.widgets.Display;
import org.modelio.edition.notes.panelprovider.NotesPanelController;
import org.modelio.edition.notes.panelprovider.NotesPanelProvider;
import org.modelio.edition.notes.view.NotesView;
import org.modelio.platform.project.services.IProjectService;

/**
 * Pastes the annotation(s) from the clipboard.
 */
@objid ("6a94ce26-686e-452d-8025-ca16be0183f9")
public class PasteAnnotationHandler {
    @objid ("72af845a-3fd8-4159-80f0-a10086bb8081")
    @Inject
    protected IProjectService projectService;

    /**
     * Available only when the selection contains only one modifiable element.
     * 
     * @param part the E4 part
     * @param currentDisplay the SWT display
     * @return true if the handler can be executed.
     */
    @objid ("604025a2-ef00-4d64-b9a9-4e97bb7ee6b7")
    @CanExecute
    public final boolean canExecute(final MPart part, Display currentDisplay) {
        if (part == null || !(part.getObject() instanceof NotesView)) {
            return false;
        }
        
        NotesPanelProvider notesPanel = ((NotesView) part.getObject()).getNotesPanel();
        if (notesPanel == null) {
            return false;
        }
        
        // Check focus
        if (!notesPanel.getTreeViewer().getControl().isFocusControl()) {
            return false;
        }
        
        NotesPanelController controller = notesPanel.getController();
        return controller.canPaste();
    }

    /**
     * Cut the currently selected elements.
     * 
     * @param part the current E4 view
     * @param currentDisplay the display Modelio runs into.
     */
    @objid ("be2f6280-9d4f-4e3e-b24f-877ff3dec5e2")
    @Execute
    public final void execute(final MPart part, Display currentDisplay) {
        NotesPanelProvider notesPanel = ((NotesView) part.getObject()).getNotesPanel();
        NotesPanelController controller = notesPanel.getController();
        controller.onPaste();
    }

}
