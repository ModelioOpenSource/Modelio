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

package org.modelio.editors.richnote.microsoft.plugin;

import java.io.IOException;
import java.nio.file.Path;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.ole.win32.OLE;
import org.modelio.editors.richnote.api.RichNoteFormat;
import org.modelio.editors.richnote.editor.AbstractRichNoteEditorProvider;
import org.modelio.editors.richnote.editor.IRichNoteFileRepository;
import org.modelio.editors.richnote.microsoft.editor.MicrosoftEditor;
import org.modelio.metamodel.uml.infrastructure.Document;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Editor provider for Microsoft Office.
 */
@objid ("3c7349d2-01c2-4929-bb0a-822413f4c3de")
public class MicrosoftEditorProvider extends AbstractRichNoteEditorProvider {
    @objid ("86a1e843-6cd7-4633-96e8-57a80a3144ab")
    private Boolean usable = null;

    @objid ("69476c49-ec11-48a3-aa62-2f19a98d3e45")
    @Override
    public final void createEmptyFile(MObject target, RichNoteFormat format, IRichNoteFileRepository richRepository) throws IOException {
        assert (isUsable()) : this;
        
        final Document richNote = (Document) target;
        final Path notePath = richRepository.getNewRichNotePath(richNote, format);
        
        MicrosoftEditor.createEmptyFile(notePath.toFile(), format);
        richRepository.saveRichNote(richNote, notePath);
    }

    @objid ("8ca256af-8d53-4674-a5c1-059f3538d229")
    @Override
    public boolean isUsable() {
        if (this.usable == null) {
            this.usable = computeUsable();
        }
        return this.usable;
    }

    @objid ("9a07c825-8214-49af-818e-0556c596ab4b")
    @Override
    public String getEditorId(final MObject target) {
        return MicrosoftEditor.EDITOR_ID;
    }

    @objid ("cebb2182-3905-48df-a099-1f670a2333e2")
    protected boolean computeUsable() {
        try {
            Class.forName("org.eclipse.swt.ole.win32.OLE");
        } catch (ClassNotFoundException e1) {
            return false;
        }
        
        String wordProgId = OLE.findProgramID("doc");
        return wordProgId != null && !wordProgId.isEmpty();
    }

}
