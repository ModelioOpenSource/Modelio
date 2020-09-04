/* 
 * Copyright 2013-2019 Modeliosoft
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package org.modelio.api.modelio.editor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.diagram.ContributorCategory;
import org.modelio.api.module.contributor.IWizardContributor;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.uml.infrastructure.Document;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.matrix.MatrixDefinition;
import org.modelio.metamodel.uml.statik.Artifact;

/**
 * This interface defines services to open, close and select Modelio editors.
 * <p>
 * A text editor is bound to a model element and a file, and must have one of those types:<br>
 * <li>"com.modeliosoft.modelio.edition.TXTEditorID" - Standard text editor</li>
 * <li>"com.modeliosoft.modelio.edition.MDDEditorID" - Model Driven editor, edition is only available between markers.</li>
 * <li>"com.modeliosoft.modelio.edition.RTEditorID" - Round Trip editor, only annotations bounds to Modelio can't be edited.</li>
 * <p>
 * Each editor can be set to read-only mode.
 * 
 * @see IMDAEditorListener
 */
@objid ("71f2e307-6c24-11e0-b589-002564c97630")
public interface IEditionService {
    /**
     * Set the focus on a specific editor.
     * 
     * @param editor the editor to focus.
     */
    @objid ("ab3f9e59-6c24-11e0-b589-002564c97630")
    void activateEditor(final IMDATextEditor editor);

    /**
     * Close the given text editor.
     * 
     * @param editor The text editor to close.
     */
    @objid ("ab3f7747-6c24-11e0-b589-002564c97630")
    void closeEditor(final IMDATextEditor editor);

    /**
     * Create a document content for the given document.
     * <p>
     * A default content is found by 2 ways:
     * <ol>
     * <li>Look for a document named "default" under the {@link ExternDocumentType} with the same MIME type.
     * <li>Look in <i>.projectdata/admin/documents</i> for a file named:
     * <ol>
     * <li><code>role_name.mime_type.dat</code> like file
     * <li><code>role_name.dat</code> like file.
     * </ol>
     * All invalid characters for a file name in the document type and MIME type
     * are replaced by '_' when looked for the file.
     * <li>If everything failed then an empty content valid for the MIME type is created.
     * </ol>
     * 
     * @param doc The document to initialize
     * @return <code>true</code> if the file was created, false if no default content could be found.
     * @throws java.io.IOException in case of error trying to create the file.
     * @since 2.1
     */
    @objid ("a4084ac9-0ecc-11e2-96c4-002564c97630")
    boolean createDocumentContent(final Document doc) throws IOException;

    /**
     * Get a copy of the file for a given external document.
     * 
     * @param doc an external document.
     * @param listener A listener fired if the original note is modified externally, by SVN for example. <code>null</code> means no edition.
     * @return its file.
     * @throws java.io.IOException in case of failure.
     * @since 3.1
     */
    @objid ("513826ce-ccdb-487e-829d-2417eb0a7d99")
    Path editRichNote(Document doc, IExternDocumentChangeListener listener) throws IOException;

    /**
     * Indicates which mime type are supported in the current instance of Modelio.
     * 
     * @return the supported mime type list.
     * @since 2.1
     */
    @objid ("a46397bc-1c25-11e1-a04f-002564c97630")
    List<String> getSupportedMimeTypes();

    @objid ("e8d36cf5-e2ae-423d-afc0-e230e2f614e8")
    String html2text(String s);

    /**
     * Open a new text editor, making the correspondence between a model element and a file.
     * Use default UTF-8 charset.
     * 
     * @param modelElement the model element to edit.
     * @param file the file to display in the editor.
     * @param editorTypeID the type of the editor to open.
     * @param readonly <code>true</code> if the editor is in read only mode.
     * @return the opened editor.
     * @deprecated use {@link #openEditor(ModelElement, File, EditorType, boolean, String, String, String)} instead.
     */
    @objid ("a4078774-0ecc-11e2-96c4-002564c97630")
    @Deprecated
    IMDATextEditor openEditor(final ModelElement modelElement, final File file, final EditorType editorTypeID, final boolean readonly);

    /**
     * Open a new text editor, making the correspondence between a model element and a file.
     * 
     * @param modelElement the model element to edit.
     * @param file the file to display in the editor.
     * @param editorTypeID the type of the editor to open.
     * @param readonly <code>true</code> if the editor is in read only mode.
     * @param charsetName The name of a supported {@link java.nio.charset.Charset charset}. If <i>null</i> it will be the UTF-8 charset.
     * @return the opened editor.
     * @since 3.3.0
     * @deprecated use {@link #openEditor(ModelElement, File, EditorType, boolean, String, String, String)} instead.
     */
    @objid ("a407d596-0ecc-11e2-96c4-002564c97630")
    @Deprecated
    IMDATextEditor openEditor(final ModelElement modelElement, final File file, final EditorType editorTypeID, final boolean readonly, String charsetName);

    /**
     * Open a diagram editor from an {@link AbstractDiagram}.
     * 
     * @param diagram the diagram to edit.
     * @since 2.1
     */
    @objid ("a407fca7-0ecc-11e2-96c4-002564c97630")
    void openEditor(final AbstractDiagram diagram);

    /**
     * Open an editor from an artifact.
     * <p>
     * The file declared by the artifact must be part of the supported mime type list.
     * 
     * @param artifact the artifact to edit.
     * @since 2.1
     */
    @objid ("a40823b8-0ecc-11e2-96c4-002564c97630")
    void openEditor(final Artifact artifact);

    /**
     * Open an editor from an extern document.
     * <p>
     * The type of the document's file must be part of the supported mime type list.
     * 
     * @param document the extern document to edit.
     * @since 2.1
     */
    @objid ("01f9c416-fad2-49e5-89a0-d2bb8321b001")
    void openEditor(final Document document);

    /**
     * Open a new text editor, making the correspondence between a model element and a file.
     * 
     * @param modelElement the model element to edit.
     * @param file the file to display in the editor.
     * @param editorTypeID the type of the editor to open.
     * @param readonly <code>true</code> if the editor is in read only mode.
     * @param charsetName The name of a supported {@link java.nio.charset.Charset charset}. If <i>null</i> it will be the UTF-8 charset.
     * @param askedTitle the tab title. If <i>null</i> it will be the model element name.
     * @param askedTooltip the tab tooltip. If <i>null</i> it will be the file absolute path.
     * @return the opened editor.
     * @since 3.6.1
     */
    @objid ("2dcf59d9-ed07-4d43-9186-4628174496fe")
    IMDATextEditor openEditor(final ModelElement modelElement, final File file, final EditorType editorTypeID, final boolean readonly, String charsetName, String askedTitle, String askedTooltip);

    /**
     * Open an editor from a matrix.
     * <p>
     * The type of the document's file must be part of the supported mime type list.
     * 
     * @param document the matrix to edit.
     * @since 3.8.0 Valkyrie
     */
    @objid ("ad32d13f-6a16-4b65-9239-b9f5da8760bc")
    void openEditor(final MatrixDefinition document);

    /**
     * Register creation wizard contributor
     * 
     * @param category of the diagram contributor
     * @since 3.4
     * @param contributor the diagram contributor
     */
    @objid ("f910f910-27b2-4606-819a-ddc374b91819")
    void registerDiagramContributor(ContributorCategory category, IWizardContributor contributor);

    /**
     * Save the external document.
     * 
     * @param doc the external document model object.
     * @param fileToSave the external document content
     * @throws java.io.IOException in case of failure.
     * @since 3.1
     */
    @objid ("a9cccc45-56c4-4cf9-9961-104104bff393")
    void saveRichNote(Document doc, Path fileToSave) throws IOException;

    /**
     * Set a document content for the given document.
     * 
     * @param doc The document to update.
     * @param content the content to set.
     * @throws java.io.IOException in case of error trying to set the file.
     * @since 3.1
     */
    @objid ("bc8aec62-33c9-458d-b57f-41b09ea08346")
    void setDocumentContent(Document doc, Path content) throws IOException;

    /**
     * Unregister creation wizard contributor
     * 
     * @param category of the diagram contributor
     * @since 3.4
     * @param contributor the diagram contributor
     */
    @objid ("8f349def-4bbb-4dd2-8836-0fd20f159bc0")
    void unregisterDiagramContributor(ContributorCategory category, IWizardContributor contributor);

    /**
     * To call when an listener is not used anymore.
     * 
     * @param editor the listener to unregister.
     * @since 3.1
     */
    @objid ("97d3bf10-fafd-4ea6-9c37-7787852c9daf")
    void unregisterListener(IExternDocumentChangeListener editor);

}
