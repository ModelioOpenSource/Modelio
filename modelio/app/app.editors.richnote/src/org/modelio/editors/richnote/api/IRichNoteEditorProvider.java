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
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.editors.richnote.editor.IRichNoteFileRepository;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * External editor provider interface.
 */
@objid ("a4b2e694-42f2-462d-a62e-fbd83f2543d0")
public interface IRichNoteEditorProvider {
    /**
     * Create an empty document for the given element.
     * @param target a model element
     * @param format the new rich note format
     * @throws IOException in case of failure
     */
    @objid ("8a73631c-08b9-43db-89c4-26c76bbec2aa")
    void createEmptyFile(final MObject target, RichNoteFormat format, IRichNoteFileRepository richRepository) throws IOException;

    /**
     * Get the document comparison service.
     * <p>
     * The provider may return <code>null</code> if document comparison is not supported.<br/>
     * @return the diff/merge service if comparison is supported, <code>null</code> if none is available.
     */
    @objid ("fe0e3253-ffb4-43ec-addc-21a0bacc20bc")
    IRichNoteDiffMerger getDiffMerge();

    /**
     * Tells whether the editor provider is available for usage.
     * <p>
     * The provider may answer <code>false</code> if a required software is not installed.<br/>
     * eg: The LibreOffice editor provider can't work if LibreOffice is not installed.
     * @return <code>true</code> if the editor provider is usable, <code>false</code> if it must be forgotten.
     */
    @objid ("bfa0f51a-ea8a-445d-b53d-c71413ee0d66")
    boolean isUsable();

    /**
     * Get the Eclipse editor ID to use to open a new editor.
     * @param target the edited element.
     * @return the Eclipse editor ID
     */
    @objid ("bf29bd6d-a368-4b5a-9f1f-3eef2159ad83")
    String getEditorId(final MObject target);
}

