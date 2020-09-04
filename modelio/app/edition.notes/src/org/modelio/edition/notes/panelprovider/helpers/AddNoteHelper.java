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

package org.modelio.edition.notes.panelprovider.helpers;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.widgets.Shell;
import org.modelio.core.ui.dialogs.elementChooser.ElementChooserDlg;
import org.modelio.edition.notes.noteChooser.NoteChooserDriver;
import org.modelio.edition.notes.plugin.EditionNotes;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.mmextensions.infrastructure.IInfrastructureModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.vcore.session.api.ICoreSession;

@objid ("5642646f-9400-4836-a7f0-9aefa13b727f")
public class AddNoteHelper extends AbstractHelper {
    @objid ("688106b2-79cc-4b6d-9e28-59a64f842581")
    private ICoreSession session;

    @objid ("d6d3af77-4aae-4f2a-a702-c5912dceed90")
    private IMModelServices modelServices;

    @objid ("c1bebdb1-aa25-4fea-a178-188bce23f211")
    public AddNoteHelper(ICoreSession session, IMModelServices modelServices) {
        this.session = session;
        this.modelServices = modelServices;
    }

    @objid ("4f71f6b8-9659-4643-80cb-671da420d540")
    public static boolean canExecute(ModelElement element) {
        return (element != null) && (element.isModifiable());
    }

    @objid ("ffc9a9dc-00e0-4c48-83ee-89ac080caffc")
    private Note createNoteFromUserChoice(final Shell parentShell, final ModelElement element) {
        NoteChooserDriver driver = new NoteChooserDriver(this.session, this.modelServices);
        ElementChooserDlg dialog = new ElementChooserDlg(parentShell, driver, element);
        // Don't return from open() until window closes
        dialog.setBlockOnOpen(true);
        // Open the main window
        dialog.open();
        return driver.getCreatedNote();
    }

    @objid ("e86feefd-07b0-4b06-aafa-532279d3e2fe")
    private Note createNoteFromType(final ModelElement element, final String moduleName, final String ownerName, String noteTypeName) {
        try {
            IInfrastructureModelFactory factory = this.modelServices.getModelFactory().getFactory(IInfrastructureModelFactory.class);
            return factory.createNote(moduleName, ownerName, noteTypeName, element, EditionNotes.I18N.getString("EnterNoteBody"));
        } catch (ExtensionNotFoundException e) {
            EditionNotes.LOG.warning(e);
            return null;
        }
    }

    @objid ("4ee48b5a-c60f-4c43-976a-026a4820f783")
    public Note execute(Shell parentShell, ModelElement element, String moduleName, String ownerName, String noteTypeName) {
        if (moduleName == null || ownerName == null || noteTypeName == null) {
            return createNoteFromUserChoice(parentShell, element);
        } else {
            return createNoteFromType(element, moduleName, ownerName, noteTypeName);
        }
    }

}
