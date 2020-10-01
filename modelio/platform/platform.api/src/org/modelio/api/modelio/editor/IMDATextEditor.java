/* 
 * Copyright 2013-2020 Modeliosoft
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
 * Interface defining a text editor in Modelio.
 * A text editor is bound to a model element and a file, and must have one of those types:<br>
 * <li>com.modeliosoft.modelio.edition.MDDEditorID</li>
 * <li>com.modeliosoft.modelio.edition.RTEditorID</li>
 * <li>com.modeliosoft.modelio.edition.TXTEditorID</li>
 * <p>
 * Each editor can be set to read-only mode.
 */
@objid ("01f410a4-0000-08d1-0000-000000000000")
public interface IMDATextEditor {
    /**
     * Get the file opened in the editor.
     * 
     * @return a file.
     */
    @objid ("01f410a4-0000-08f1-0000-000000000000")
    File getFile();

    /**
     * Save the content of the editor.<br>
     * Triggers {@linkplain IMDAEditorListener#documentSaved(IMDATextEditor, ModelElement, File)} on the current listener.
     */
    @objid ("01f41744-0000-0019-0000-000000000000")
    void save();

    /**
     * Reload the content of the editor from the corresponding file.
     */
    @objid ("01f41744-0000-001a-0000-000000000000")
    void reload();

    /**
     * Indicates whether of not the editor's content has been modified.<br>
     * It matches the presence of the little '*' in the editor's header.
     * 
     * @return <code>true</code> if the content of the editor has been modified.
     */
    @objid ("01f41744-0000-001b-0000-000000000000")
    boolean isDirty();

    /**
     * Change the editor's editon mode.<br>
     * A read only editor can't be modified.
     * 
     * @param readOnly <code>true</code> if the editor is read only.
     */
    @objid ("01f41be4-0000-00e1-0000-000000000000")
    void setReadonlyMode(boolean readOnly);

    /**
     * Set the listener who will be notified when a 'save' or 'close' event occurs.
     * @see IMDAEditorListener
     * 
     * @param listener The listener to attach to the editor.
     */
    @objid ("01f41be4-0000-00e3-0000-000000000000")
    void setListener(IMDAEditorListener listener);

    /**
     * Get the editor's type.
     * 
     * @return the editor's type.
     */
    @objid ("1520aecb-f9d3-11dd-baf4-001ec947cd2a")
    EditorType getType();

    /**
     * Get the element corresponding to this editor.
     * 
     * @return a model element.
     */
    @objid ("a42bb231-0ecc-11e2-96c4-002564c97630")
    ModelElement getElement();

    /**
     * Change current charset on the editor.
     * @since 3.3.0
     */
    @objid ("d4172b78-8618-4854-9917-463151cb2140")
    void setCharsetName(String charsetName);

}
