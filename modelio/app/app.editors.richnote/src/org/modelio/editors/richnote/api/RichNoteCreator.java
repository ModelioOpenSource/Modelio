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
package org.modelio.editors.richnote.api;

import java.io.IOException;
import java.net.UnknownServiceException;
import java.nio.file.Files;
import java.nio.file.Path;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.modelio.editors.richnote.editor.IRichNoteEditor;
import org.modelio.editors.richnote.editor.IRichNoteFileRepository;
import org.modelio.editors.richnote.management.RichNotesSession;
import org.modelio.editors.richnote.plugin.EditorsRichNote;
import org.modelio.gproject.core.IGProject;
import org.modelio.gproject.project.AbstractGProject;
import org.modelio.metamodel.uml.infrastructure.AbstractResource;
import org.modelio.metamodel.uml.infrastructure.Document;
import org.modelio.metamodel.uml.infrastructure.ResourceType;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Service to creates the default rich notes content.
 * <p>
 * A default content is found by 2 ways:
 * <ol>
 * <li>Look for a document named "default" under the {@link ExternDocumentType} with the same MIME type.
 * <li>Look in <i>$(project)/data/admin/richnotes</i> for a file named:
 * <ol>
 * <li><code>role_name.mime_type.dat</code> like file
 * <li><code>role_name.dat</code> like file.
 * </ol>
 * All invalid characters for a file name in the document type and MIME type
 * are replaced by '_' when looked for the file.
 * </ol>
 */
@objid ("435ab4e2-5dca-411b-85f9-1b44bc3fcf91")
public class RichNoteCreator {
    @objid ("a8fa57d0-80f0-408b-a310-1872f0a8c99d")
    private static final String RICHNOTES_SUBDIR = "richnotes";

    /**
     * No instance.
     */
    @objid ("dac062b7-e7eb-43ff-a7f9-8f69d71786c6")
    private  RichNoteCreator() {
        
    }

    /**
     * Create the default document content for the given document.
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
     * </ol>
     * @param docToInitialize The document to initialize
     * @throws IOException in case of error trying to create the file.
     * @throws UnknownServiceException if no default content could be found.
     */
    @objid ("a5e5a6cb-270b-4f58-92bb-7d55192f0b15")
    public static void createRichNote(final Document docToInitialize) throws IOException, UnknownServiceException {
        // Look for document format, its editor and ask him to create an empty file.
        RichNoteFormat format = RichNoteFormatRegistry.getInstance().getFormat(docToInitialize);
        if (format == null) {
            throw new UnknownServiceException("No rich note format found for " + docToInitialize);
        }
        
        // Use the first declared extension
        final String extension = format.getFileExtensions().iterator().next();
        IRichNoteFileRepository fileRepo = RichNotesSession.get(docToInitialize).getFileRepository();
        
        // First to create from template
        Path templatePath = getTemplate(docToInitialize, extension);
        if (templatePath != null) {
            fileRepo.saveRichNote(docToInitialize, templatePath);
            return;
        }
        
        IRichNoteEditorProvider editor = format.getEditorProvider();
        if (editor == null) {
            throw new UnknownServiceException("No editor to support " + format.getLabel() + " files.");
        }
        
        if (!editor.isUsable()) {
            throw new UnknownServiceException(editor.getClass().getSimpleName() + " editor is not supported in this computer");
        }
        
        editor.createEmptyFile(docToInitialize, format, fileRepo);
        
    }

    /**
     * Create the default document content for the given document.
     * <p>
     * A default content is found by 2 ways:
     * <ol>
     * <li>Look for a document named "default" under the {@link ResourceType} with the same MIME type.
     * <li>Look in <i>$project/data/.config/richnotes</i> for a file named:
     * <ol>
     * <li><code>role_name.mime_type.dat</code> like file
     * <li><code>role_name.dat</code> like file.
     * </ol>
     * All invalid characters for a file name in the document type and MIME type
     * are replaced by '_' when looked for the file.
     * </ol>
     * @param docToInitialize The document to initialize
     * @param extension the file extension
     * @return <code>true</code> if the file was created, false if no default content could be found.
     * @throws IOException in case of error trying to create the file.
     */
    @objid ("2d42e648-4d15-4799-afb6-714f4dfb1fd6")
    private static Path getTemplate(final AbstractResource docToInitialize, final String extension) throws IOException {
        Path defPath;
        IGProject gproject = AbstractGProject.getProject(docToInitialize);
        
        AbstractResource defDoc = getDefaultDocument(docToInitialize);
        if (defDoc != null) {
            defPath = extractDefaultRichNote(defDoc);
        } else {
            Path dir = gproject.getPfs().getProjectDataConfigPath().resolve(RichNoteCreator.RICHNOTES_SUBDIR);
        
            defPath = lookForDefaultFile(dir, docToInitialize, extension);
        }
        
        if (defPath != null) {
            return defPath;
        }
        return null;
    }

    @objid ("3a155ecd-d4c0-47ad-bd51-865f5f9ee867")
    private static AbstractResource getDefaultDocument(final AbstractResource docToInitialize) {
        ResourceType docType = docToInitialize.getType();
        
        for (AbstractResource defDoc : docType.getTypedResource()) {
            if (defDoc.getMimeType().equals(docToInitialize.getMimeType()) &&
                    defDoc.getName().equals("default")) {
                return defDoc;
            }
        
        }
        return null;
    }

    /**
     * Look in the directory for a file named:
     * <ol>
     * <li><code>role-name.mime-type.dat</code> like file
     * <li><code>role-name.dat</code> like file.
     * </ol>
     * All invalid characters for a file name in the document type and MIME type
     * are replaced by '_' when looked for the file.
     * @param dir the directory to search
     * @param docToInitialize the document to initialize
     * @param extension the file extension
     * @return the found file or <code>null</code>.
     */
    @objid ("d9cbfd5b-9d92-4593-a46e-4614704df91e")
    private static Path lookForDefaultFile(final Path dir, final AbstractResource docToInitialize, final String extension) {
        String mimeName = sanitizeFilename(docToInitialize.getMimeType());
        String roleName = sanitizeFilename(docToInitialize.getType().getName());
        Path f = dir.resolve(roleName + "." + mimeName + "." + extension);
        if (Files.isRegularFile(f)) {
            return f;
        }
        
        f = dir.resolve(roleName + "." + extension);
        if (Files.isRegularFile(f)) {
            return f;
        }
        return null;
    }

    /**
     * Replace illegal characters in a filename with "_".<br>
     * Illegal characters : * : \ / * ? | < >
     * @param name the file name to sanitize
     * @return the valid file name
     */
    @objid ("6dfcdee9-8d9c-40da-80b9-33040466e9d4")
    private static String sanitizeFilename(final String name) {
        return name.replaceAll("[:\\\\/*?|<>]", "_");
    }

    @objid ("13ca9b5d-def8-4b59-a010-f4827ad80798")
    private static Path extractDefaultRichNote(AbstractResource doc) throws IOException {
        RichNotesSession rsess = RichNotesSession.get(doc);
        IRichNoteEditor editor = new IRichNoteEditor() {
        
            @Override
            public void onOriginalModified(MObject model) {
                EditorsRichNote.LOG.error("Unexpected modification of " + model + " template.");
            }
        
            @Override
            public void onOriginalDeleted(MObject model) {
                EditorsRichNote.LOG.error("Unexpected delete of " + model + " template.");
            }
        
            @Override
            public MPart getMPart() {
                return null;
            }
        
            @Override
            public void disposeResources() {
                // nothing
            }
        };
        
        Path ret = rsess.getFileRepository().openRichNote(doc, editor);
        rsess.getFileRepository().removeEditor(editor);
        return ret;
    }

}
