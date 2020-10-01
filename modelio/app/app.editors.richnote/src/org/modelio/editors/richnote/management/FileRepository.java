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

package org.modelio.editors.richnote.management;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.editors.richnote.api.RichNoteFormat;
import org.modelio.editors.richnote.editor.IRichNoteEditor;
import org.modelio.editors.richnote.editor.IRichNoteFileRepository;
import org.modelio.editors.richnote.helper.RichNoteFilesGeometry;
import org.modelio.gproject.gproject.GProject;
import org.modelio.metamodel.uml.infrastructure.AbstractResource;
import org.modelio.metamodel.uml.infrastructure.IResourceHandle;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.transactions.ITransaction;

/**
 * Rich notes files extracted from blobs to a directory in the project runtime directory.
 * <p>
 * Provide services to extract a rich note from blobs to the runtime directory back and forth.
 */
@objid ("a2b9bd65-bc58-4b0c-9909-32b5b1d7336e")
class FileRepository implements IRichNoteFileRepository {
    @objid ("6467e67d-30e4-42f3-a4f9-9523f5cbdc68")
    private ICoreSession session;

    @objid ("aee2cebb-0a19-498c-8d99-602f8d5d2683")
    private RichNoteFilesGeometry geometry;

    @objid ("998817c1-6228-44a8-a038-7ec537e421ba")
    private EditorsRegistry editorsRegistry;

    /**
     * Initialize
     * 
     * @param project the project to handle
     * @param editorsRegistry the editors registry.
     */
    @objid ("4d46a9b7-81d1-4a24-8a24-83173f1d32d5")
    public FileRepository(GProject project, EditorsRegistry editorsRegistry) {
        this.session = project.getSession();
        this.geometry = new RichNoteFilesGeometry(project);
        this.editorsRegistry = editorsRegistry;
    }

    @objid ("e3371f5e-234c-41b9-8014-34b334958e08")
    @Override
    public Path openRichNote(AbstractResource doc, IRichNoteEditor editor) throws IOException {
        if (editor != null) {
            this.editorsRegistry.addEditor(doc, editor);
        }
        
        IResourceHandle handle = doc.getHandle();
        if (handle == null) {
            return null;
        }
        
        Path targetDir = this.geometry.getEditedFilesdirectory();
        Files.createDirectories(targetDir);
        
        Path extracted = handle.extractInto(targetDir);
        return extracted;
    }

    @objid ("ca00b217-6317-4039-8cd3-a5989c33de10")
    @Override
    public Path getArtifactFile(Artifact art) {
        return this.geometry.getPath(art);
    }

    @objid ("04bf795f-596e-42ab-a56d-b76b100b2765")
    @Override
    public Path getNewRichNotePath(AbstractResource doc, RichNoteFormat format) {
        return this.geometry.getDefaultPath(doc, format.getFileExtensions().iterator().next());
    }

    @objid ("1f8eae59-a8f1-409d-b764-37b7dc9c363e")
    @Override
    public void saveRichNote(AbstractResource doc, Path fileToSave) throws IOException {
        String name = doc.getName();
        try (ITransaction t = this.session.getTransactionSupport().createTransaction("touch " + name)) {
            IResourceHandle handle = doc.getHandle();
            if (handle == null) {
                // Create the resource itself
                handle = doc.createEmbeddedResource(fileToSave.getFileName().toString());
            } else {
                // Touch the model element so that it is included in SVN commit
                doc.setName("");
                doc.setName(name);
            }
        
            try (OutputStream os = handle.write()) {
                Files.copy(fileToSave, os);
            }
        
            t.disableUndo();
            t.commit();
        }
    }

    @objid ("75e7a7c9-a8dd-4cd1-89cf-54a392892f43")
    @Override
    public void removeEditor(IRichNoteEditor editor) {
        this.editorsRegistry.removeEditor(editor);
    }

    @objid ("4bdae04b-d4d3-4f3b-8b19-0396a5c482d4")
    @Override
    public void initRichNoteFromFile(AbstractResource doc, Path fileToSave) throws IOException {
        try (OutputStream blobStream = doc.createEmbeddedResource(fileToSave.getFileName().toString()).write();) {
            Files.copy(fileToSave, blobStream);
        }
    }

}
