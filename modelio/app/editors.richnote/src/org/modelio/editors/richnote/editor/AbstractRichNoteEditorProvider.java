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

package org.modelio.editors.richnote.editor;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.editors.richnote.api.IRichNoteDiffMerger;
import org.modelio.editors.richnote.api.IRichNoteEditorProvider;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Manages the open editors.
 * <p>
 * Can open close an editor and create empty documents.
 * @author cmarin
 */
@objid ("cee2c78e-0762-46cc-8295-fadf32f1cbe1")
public abstract class AbstractRichNoteEditorProvider implements IRichNoteEditorProvider {
    /**
     * Get the editor ID for the given element.
     * <p>
     * It is assumed that the editor provider supports the given element.
     * In the other case the provider is allowed to throw an {@link IllegalArgumentException}.
     * @param target the element to edit.
     * @return an editor ID.
     */
    @objid ("243a7f0b-894c-472c-b35e-75a0fd0de06a")
    @Override
    public abstract String getEditorId(final MObject target);

    @objid ("2c91b75a-160c-4055-acff-ec4787abf0a6")
    @Override
    public IRichNoteDiffMerger getDiffMerge() {
        return null;
    }

}
