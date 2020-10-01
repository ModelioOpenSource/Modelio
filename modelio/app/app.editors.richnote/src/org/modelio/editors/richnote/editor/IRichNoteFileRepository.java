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

package org.modelio.editors.richnote.editor;

import java.io.IOException;
import java.nio.file.Path;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.editors.richnote.api.RichNoteFormat;
import org.modelio.metamodel.uml.infrastructure.AbstractResource;
import org.modelio.metamodel.uml.statik.Artifact;

/**
 * Manages the files related to rich notes.
 */
@objid ("68676e39-04ad-44a6-be4f-73dfb1ad887b")
public interface IRichNoteFileRepository {
    /**
     * Get the file for a given rich note.
     * <p>
     * Extract it from blob if needed.
     * 
     * @param doc a rich note
     * @param editor The editor willing to edit the note.
     * It will be fired if the original note is modified externally, by SVN for example. <code>null</code> means no edition.
     * @return its file.
     * @throws java.io.IOException in case of failure
     */
    @objid ("841092a8-4e92-4146-b08e-1a5781cfb451")
    Path openRichNote(AbstractResource doc, IRichNoteEditor editor) throws IOException;

    /**
     * Get the file where the firle referred by the artifact can be edited.
     * 
     * @param art an  artifact
     * @return its file path.
     */
    @objid ("96258702-4b18-448f-8e8a-bb29f39fdd90")
    Path getArtifactFile(Artifact art);

    /**
     * Get the path where the given rich note should be created.
     * 
     * @param doc a rich note
     * @param format the rich note format
     * @return the path where the editor should save the rich note.
     */
    @objid ("e5189366-1d8b-45ff-911a-0775ed856714")
    Path getNewRichNotePath(AbstractResource doc, RichNoteFormat format);

    /**
     * Save the rich note.
     * 
     * @param doc the rich note model object.
     * @param fileToSave the rich note content
     * @throws java.io.IOException in case of failure.
     */
    @objid ("817270dd-fa1c-4652-abcc-d787892406cc")
    void saveRichNote(AbstractResource doc, Path fileToSave) throws IOException;

    /**
     * To call when a rich note file is not used anymore, when the editor is closed.
     * 
     * @param editor the editor to remove.
     */
    @objid ("077c1d91-07c2-4144-927c-762e7adb33c2")
    void removeEditor(IRichNoteEditor editor);

    @objid ("be65d41b-e8b1-4cb7-93b7-d5487bbb94ed")
    void initRichNoteFromFile(AbstractResource doc, Path fileToSave) throws IOException;

}
