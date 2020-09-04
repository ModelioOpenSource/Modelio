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

package org.modelio.editors.richnote.gui.handler;

import java.io.IOException;
import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.modelio.app.core.activation.IActivationService;
import org.modelio.core.ui.swt.SelectionHelper;
import org.modelio.editors.richnote.helper.AddEmbeddedDocumentHelper;
import org.modelio.editors.richnote.plugin.EditorsRichNote;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.Document;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vbasic.files.FileUtils;

/**
 * Handler for 'Add document' button.
 * <p>
 * Most of the work is done by {@link AddDocumentHelper}...
 * </p>
 */
@objid ("9cfe3a9b-afdb-4151-81e7-63c1c66305a9")
public class AddDocumentHandler {
    @objid ("216ef0d3-738b-4c29-929e-60f694cb1d25")
    @CanExecute
    final boolean canExecute(@Named(IServiceConstants.ACTIVE_SELECTION) IStructuredSelection selection) {
        // Must have and only one selected element
        return selection.size() == 1 && AddEmbeddedDocumentHelper.canExecute(SelectionHelper.getFirst(selection, ModelElement.class));
    }

    @objid ("90c10778-0498-49c4-8fa1-5077685e59c9")
    @Execute
    void execute(@Optional @Named(IServiceConstants.ACTIVE_SHELL) Shell parentShell, @Optional @Named(IServiceConstants.ACTIVE_SELECTION) IStructuredSelection selection, @Optional IActivationService activationService, @Optional IMModelServices modelServices) {
        ModelElement currentElement = SelectionHelper.getFirst(selection, ModelElement.class);
        
        try {
            Document richNote = AddEmbeddedDocumentHelper.execute(parentShell, currentElement, modelServices);
            if (richNote != null && activationService != null) {
                activationService.activateMObject(richNote);
            }
        } catch (IllegalArgumentException | IOException e) {
            reportException(e, parentShell);
        }
    }

    /**
     * Show a warning dialog box.
     */
    @objid ("12d55105-8a58-4328-83c8-791d69caf19f")
    void reportException(final Exception e, Shell parentShell) {
        EditorsRichNote.LOG.error(e);
        
        String title = EditorsRichNote.I18N.getMessage("CannotInitializeRichNoteContent");
        String message = e instanceof IOException ? FileUtils.getLocalizedMessage((IOException) e) : e.getLocalizedMessage();
        MessageDialog.openWarning(parentShell, title, message);
    }

}
