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
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.ModelElement;

/**
 * After opening a IMDATextEditor, a listener can be added to it using the
 * <code>setListener(IMDAEditorListener listener)</code> method.
 * <p>
 * When the specified event occurs, <code>documentSaved(...)</code> or <code>editorClosed(...)</code> will
 * be sent to the instance.
 * </p>
 * @see IMDATextEditor
 */
@objid ("01f410a4-0000-08d2-0000-000000000000")
public interface IMDAEditorListener {
    /**
     * This method is called after an editor is closed.
     * 
     * @param editor the listened editor.
     */
    @objid ("01f41744-0000-0008-0000-000000000000")
    void editorClosed(IMDATextEditor editor);

    /**
     * This method is called after an editor is saved.
     * 
     * @param editor the listened editor.
     * @param modelElement the edited model element.
     * @param file the edited file.
     */
    @objid ("a41d32e2-0ecc-11e2-96c4-002564c97630")
    void documentSaved(IMDATextEditor editor, ModelElement modelElement, File file);

}
