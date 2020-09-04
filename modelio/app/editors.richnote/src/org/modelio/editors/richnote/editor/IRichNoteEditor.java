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
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Interface that rich note editors must provide to
 * {@link IRichNoteFileRepository#openRichNote(org.modelio.metamodel.uml.infrastructure.ExternDocument, IRichNoteEditor) IRichNoteFileManager.getRichNoteFile(...)}.
 * <p>
 * 
 * The editor must unregister this interface when closing, by using
 * {@link IRichNoteFileRepository#removeEditor(IRichNoteEditor)}.
 */
@objid ("47c13e07-60c5-4055-bbb4-7eb882f8de99")
public interface IRichNoteEditor {
    /**
     * Called when the original content or the model object has been externally deleted.
     * <p>
     * The edited file is still here and won't be touched.
     * @param model the deleted model object
     */
    @objid ("91867cd4-0ca1-49ce-9cd7-61655049c3a1")
    void onOriginalDeleted(MObject model);

    /**
     * Called when the original content has been externally modified.
     * <p>
     * The edited file is still here and won't be touched.
     * <p>
     * The implementation may choose to <ul>
     * <li> keep its version,
     * <li> reload the file by calling {@link IRichNoteFileRepository#openRichNote(org.modelio.metamodel.uml.infrastructure.ExternDocument, IRichNoteEditor) IRichNoteFileManager.getRichNoteFile(...)},
     * <li> close the editor,
     * <li> run a diff/merge,
     * <li> ask the user for what to do,
     * <li> ...
     * </ul>
     * @param model the deleted model object
     */
    @objid ("d0798fc7-a575-4ae6-9324-d9434750d39c")
    void onOriginalModified(MObject model);

    /**
     * The implementation must close any not E4 GUI, cease immediately to use the rich note
     * and release any other resources it has allocated.
     * <p>
     * It does not need to close the Eclipse 4 MPart.
     * Used to close rich note editors that are not based on the Eclipse 4 model.
     */
    @objid ("33dc467d-8925-4dce-80b3-99a67f1be6e7")
    void disposeResources();

    /**
     * Get the Eclipse 4 part used to edit the rich note.
     * @return the part
     */
    @objid ("8cfc32ce-ff62-4551-8884-4d1b2a16d613")
    MPart getMPart();

}
