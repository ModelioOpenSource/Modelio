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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Inject;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.swt.widgets.Display;
import org.modelio.edition.notes.panelprovider.NotesPanelController;
import org.modelio.edition.notes.panelprovider.NotesPanelProvider;
import org.modelio.edition.notes.view.NotesView;
import org.modelio.platform.project.services.IProjectService;

/**
 * Cuts selected annotation(s) into the clipboard.
 */
@objid ("65c0aea4-4ece-408b-bbb4-1cd600ead190")
public class CutAnnotationHandler {
    @objid ("05ac3c5d-b435-4a6c-8db1-981e7adf2451")
    boolean ctrlFlag;

    @objid ("4842c180-e06a-450c-8aef-bc1d47b5145e")
    @Inject
    protected IProjectService projectService;

    /**
     * Available only when the selected elements are modifiable.
     * @param part the current active part.
     * @return true if the handler can be executed.
     */
    @objid ("7ffd9d53-937d-4303-ac08-25ca37564575")
    @CanExecute
    public final boolean canExecute(final MPart part) {
        if (part == null || !(part.getObject() instanceof NotesView)) {
            return false;
        }
        
        // Sanity checks
        if (this.projectService.getSession() == null) {
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
        return controller.canCut();
    }

    /**
     * Cut the currently selected elements.
     * @param part the current active part.
     * @param currentDisplay the display Modelio runs into.
     */
    @objid ("daaeef67-3284-4439-9d48-77bc3f51add8")
    @Execute
    public final void execute(final MPart part, Display currentDisplay) {
        NotesPanelProvider notesPanel = ((NotesView) part.getObject()).getNotesPanel();
        NotesPanelController controller = notesPanel.getController();
        controller.onCut();
        
    }

}
