/* 
 * Copyright 2013-2018 Modeliosoft
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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Interface that rich note editors must provide.
 * <p>
 * The editor must unregister this interface when closing, by using
 * {@link IEditionService#unregisterListener(IExternDocumentChangeListener)}
 * </p>
 */
@objid ("7240af74-5409-4ccf-93c8-dac6e484e420")
public interface IExternDocumentChangeListener {
    /**
     * Called when the original content or the model object has been externally deleted.
     * <p>
     * The edited file is still here and won't be touched.
     * @param model the deleted model object
     */
    @objid ("1909fa58-5ca4-463f-acc9-dfa3ab6bc3a3")
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
    @objid ("51b2df11-f620-4dbb-ad22-13efa5c905d3")
    void onOriginalModified(MObject model);

}
