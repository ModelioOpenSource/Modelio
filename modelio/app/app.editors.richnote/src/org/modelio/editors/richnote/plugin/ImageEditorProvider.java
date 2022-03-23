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
package org.modelio.editors.richnote.plugin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.editors.richnote.api.IRichNoteDiffMerger;
import org.modelio.editors.richnote.api.RichNoteFormat;
import org.modelio.editors.richnote.editor.AbstractRichNoteEditorProvider;
import org.modelio.editors.richnote.editor.IRichNoteFileRepository;
import org.modelio.metamodel.uml.infrastructure.Document;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rich note editor provider for images.
 */
@objid ("f615d001-755a-4b0c-9f9f-eca0758ee729")
public class ImageEditorProvider extends AbstractRichNoteEditorProvider {
    @objid ("71af1cb8-6335-450d-a4b3-5107d5e52c02")
    @Override
    public void createEmptyFile(final MObject target, RichNoteFormat format, IRichNoteFileRepository richNoteRepository) throws IOException {
        assert (isUsable()) : this;
        
        final Document richNote = (Document) target;
        final Path newPath = richNoteRepository.getNewRichNotePath(richNote, format);
        
        if (!Files.isDirectory(newPath.getParent())) {
            Files.createDirectories(newPath.getParent());
        }
        
        Files.createFile(newPath);
        
        richNoteRepository.saveRichNote(richNote, newPath);
        
    }

    @objid ("6123c9ce-0d38-447a-85b8-f615566da4e4")
    @Override
    public IRichNoteDiffMerger getDiffMerge() {
        return null;
    }

    @objid ("73ba8666-a91e-49fc-bd4d-f9cbd66c4162")
    @Override
    public String getEditorId(final MObject target) {
        return null;
    }

    @objid ("8c23b5d0-ab3e-4b3c-be0a-bbc98321dbea")
    @Override
    public boolean isUsable() {
        return true;
    }

}
