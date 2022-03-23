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
 * Copies selected elements into the clipboard.
 */
@objid ("28109be5-70e7-4767-9f6e-1de540602928")
public class CopyAnnotationHandler {
    @objid ("34878d76-645c-4962-84d2-59325c79d360")
    boolean ctrlFlag;

    @objid ("72df8357-313e-4e84-a22e-95ab20fa4246")
    @Inject
    protected IProjectService projectService;

    /**
     * Available only when the selected elements is not empty.
     * @param part the current active part.
     * @return true if the handler can be executed.
     */
    @objid ("a57c1fa7-6084-449a-afa3-e04145bd6302")
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
        return controller.canCopy();
    }

    /**
     * Copy the currently selected elements.
     * @param part the current active part.
     * @param currentDisplay the display Modelio runs into.
     */
    @objid ("45a51303-bfe0-4176-a4d7-adcd1c0ffb0a")
    @Execute
    public final void execute(final MPart part, Display currentDisplay) {
        NotesPanelProvider notesPanel = ((NotesView) part.getObject()).getNotesPanel();
        NotesPanelController controller = notesPanel.getController();
        controller.onCopy();
        
    }

}
