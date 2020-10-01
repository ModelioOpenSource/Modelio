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

package org.modelio.editors.richnote.libreoffice.editor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.widgets.Composite;
import org.modelio.editors.richnote.api.RichNoteFormat;
import org.modelio.editors.richnote.editor.IRichNoteFileRepository;
import org.modelio.metamodel.uml.infrastructure.Document;

/**
 * Viewer for an edited document.
 * <p>
 * This is the bridge interface to the 'edition.extern.libreoffice.runtime' plugin.
 */
@objid ("1d3604ea-c8e6-4738-ae0f-0bcf6296751c")
public interface IEditedDocumentViewer {
    /**
     * Open in the viewer the given document.
     * 
     * @param stream the stream containing the document
     * @param modifiable true to open in read/write, false for read only.
     * @throws java.io.IOException in case of failure.
     */
    @objid ("0ea607b1-2006-4825-92c9-e626ac1ce7e1")
    void openDocument(final InputStream stream, final boolean modifiable) throws IOException;

    /**
     * Save the content to the given stream.
     * 
     * @param stream where the document will be saved.
     * @throws java.io.IOException in case of error saving the document.
     */
    @objid ("86784f34-5141-4f5f-b3bb-8cd0fac7e594")
    void saveDocument(final OutputStream stream) throws IOException;

    /**
     * Convert the document to simple text format.
     * 
     * @return the document as text.
     * @throws java.io.IOException in case of failure.
     */
    @objid ("bcf0213a-8924-4b82-96f1-4ab0e8ad753a")
    String getAsText() throws IOException;

    /**
     * get the document as HTML format.
     * 
     * @return the document as HTML.
     * @throws java.io.IOException in case of failure.
     */
    @objid ("35cb9e05-0171-4fd2-abe7-28c30d74a2f2")
    String getAsHtml() throws IOException;

    /**
     * Creates the SWT controls for this viewer.
     * <p>
     * For implementors this is a multi-step process:
     * <ol>
     * <li>Create one or more controls within the parent.</li>
     * <li>Set the parent layout as needed.</li>
     * <li>Register any global actions with the site's <code>IActionBars</code>.</li>
     * <li>Register any context menus with the site.</li>
     * <li>Register a selection provider with the site, to make it available to
     * the workbench's <code>ISelectionService</code> (optional). </li>
     * </ol>
     * </p>
     * 
     * @param parent the parent control
     */
    @objid ("31603a13-b202-4e8e-b758-862130f3d3fc")
    void createPartControl(final Composite parent);

    /**
     * Close the viewer.
     */
    @objid ("a346e37f-1419-4712-b98f-eca23f06bd01")
    void close();

    /**
     * @return true if the document is modified.
     */
    @objid ("2d9d313a-41a1-4ffd-a7f7-cef4afaac67c")
    boolean isDirty();

    /**
     * Open in the viewer the given document.
     * 
     * @param file the document
     * @param modifiable true to open in read/write, false for read only.
     * @throws java.io.IOException in case of failure.
     */
    @objid ("54fcf08a-f854-4827-b34a-f7a46c5437a2")
    void openDocument(final Path file, final boolean modifiable) throws IOException;

    /**
     * Create an empty file for the given document.
     * 
     * @param doc the document to initialize.
     * @param format the format of the new file
     * @throws java.io.IOException in case of failure.
     */
    @objid ("6d07062e-df57-4f71-92c2-74d2ad237971")
    void createDocument(final Document doc, RichNoteFormat format) throws IOException;

    /**
     * Save the currently edited file.
     * 
     * @throws java.io.IOException in case of failure.
     */
    @objid ("d641b5ad-5d3f-4e9f-baac-7144df0b663d")
    void saveDocument() throws IOException;

    /**
     * Set the keyboard focus on the editor.
     */
    @objid ("02e41b36-f963-4036-ada2-26f74c260f9e")
    void setFocus();

    /**
     * Close the current document and be ready to open another.
     * 
     * @throws java.io.IOException in case of failure.
     */
    @objid ("05fdc8c3-2342-4ba4-985f-bd7e2567bf1a")
    void closeDocument() throws IOException;

    /**
     * Tells whether the viewer is disposed or usable.
     * 
     * @return <code>true</code> if the viewer is disposed, <code>false</code> if it is usable.
     * @throws java.lang.IllegalStateException if the viewer is not yet initialized.
     * {@linkplain #createPartControl(Composite)} must be called first.
     */
    @objid ("bedcf84c-edcd-48a2-9bd1-135d145ebd84")
    boolean isDisposed() throws IllegalStateException;

    /**
     * Initialize the file manager to use to load and save rich note files.
     * 
     * @param aFileManager the file manager to use
     */
    @objid ("40e44ca5-eb7b-48e2-b15c-fde22cc3743c")
    void setFileManager(IRichNoteFileRepository aFileManager);

}
