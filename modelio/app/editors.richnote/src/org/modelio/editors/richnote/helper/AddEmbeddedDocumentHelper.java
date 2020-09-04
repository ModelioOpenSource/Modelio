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

package org.modelio.editors.richnote.helper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Shell;
import org.modelio.editors.richnote.gui.creation.CreateEmbeddedDocumentDialog;
import org.modelio.editors.richnote.gui.creation.RichNoteDescriptor;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.Document;
import org.modelio.metamodel.uml.infrastructure.ModelElement;

@objid ("7c250ae9-57fd-48ad-af9c-42e5bef8beb8")
public class AddEmbeddedDocumentHelper {
    @objid ("cdf64b3c-f142-4a1c-a4f9-7b6b918f4a45")
    public static boolean canExecute(ModelElement element) {
        return (element != null && element.getStatus().isModifiable());
    }

    /**
     * Create a document  by opening the document creation wizard with default values.
     */
    @objid ("3dc1e283-0556-4b60-a560-1bdf9966e8da")
    public static Document execute(Shell parentShell, ModelElement element, IMModelServices modelServices) throws IOException, IllegalArgumentException {
        RichNoteDescriptor richNoteDescriptor = new RichNoteDescriptor(element);
        return AddEmbeddedDocumentHelper.execute(parentShell, modelServices, richNoteDescriptor);
    }

    /**
     * Create a document by opening the document creation wizard with preconfigured values.
     */
    @objid ("8935e185-baf7-4f52-9f6e-0d2d7b3b4464")
    public static Document execute(Shell parentShell, IMModelServices modelServices, RichNoteDescriptor richNoteDescriptor) throws IOException {
        CreateEmbeddedDocumentDialog dialog = new CreateEmbeddedDocumentDialog(parentShell, richNoteDescriptor);
        
        // Don't return from open() until window closes
        dialog.setBlockOnOpen(true);
        
        // Open the dialog window
        int ret = dialog.open();
        if (ret == IDialogConstants.OK_ID) {
            switch (richNoteDescriptor.getCreationMode()) {
            case EMBEDDED:
                return AddEmbeddedDocumentHelper.createEmbeddedDocument(richNoteDescriptor, modelServices.getModelFactory().getFactory(IStandardModelFactory.class));
            case IMPORT:
                return AddEmbeddedDocumentHelper.createImportedDocument(richNoteDescriptor, modelServices.getModelFactory().getFactory(IStandardModelFactory.class));
            default:
                return null;
            }
        }
        return null;
    }

    @objid ("feb963bf-b5f3-4561-adbc-1ca58b6bafa7")
    private static Document createDocumentReference(final RichNoteDescriptor model, final IStandardModelFactory factory) throws InvalidPathException {
        URI uri;
        try {
            uri = new URI(model.getPath());
            if (uri.getScheme() == null) {
                // The uri has no scheme, assume it's a local file to avoid errors when creating the document...
                uri = new URI("file://" + model.getPath());
            }
        } catch (URISyntaxException e) {
            uri = Paths.get(model.getPath()).toUri();
        }
        
        Document doc = factory.resourceBuilder()
                .withOwner(model.getTargetElement())
                .withRole(model.getDocumentType())
                .withName(model.getName())
                .withURI(uri)
                .createDocumentReference();
        doc.setAbstract(model.getAbstract());
        return doc;
    }

    @objid ("d71e5b89-20d9-4c0c-9644-18fc40e376a1")
    private static Document createEmbeddedDocument(final RichNoteDescriptor model, final IStandardModelFactory factory) throws IOException {
        Document doc = factory.resourceBuilder()
                .withOwner(model.getTargetElement())
                .withMimeType(model.getChosenMimeType().getMimeType())
                .withRole(model.getDocumentType())
                .withName(model.getName())
                .createEmbeddedDocument();
        doc.setAbstract(model.getAbstract());
        return doc;
    }

    @objid ("f0a9be92-1abb-4192-8435-a66ab4d41922")
    private static Document createImportedDocument(final RichNoteDescriptor model, final IStandardModelFactory factory) throws IOException {
        Document doc = factory.resourceBuilder()
                .withOwner(model.getTargetElement())
                .withMimeType(model.getChosenMimeType().getMimeType())
                .withRole(model.getDocumentType())
                .withName(model.getName())
                .withFile(Paths.get(model.getPath()))
                .createEmbeddedDocument();
        doc.setAbstract(model.getAbstract());
        return doc;
    }

}
