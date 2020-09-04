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

package org.modelio.api.ui.dnd;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Interface defining a model element drop client.<br>
 * Used by {@link EditorDropListener}.
 */
@objid ("90dc7023-79da-11dd-ba6f-0014222a9f79")
public interface IEditorDropClient {
    /**
     * Returns whether the field accepts the element as a value.
     * 
     * @param element the new value candidate.
     * @return true if the new value is valid, false in the other case.
     */
    @objid ("e445c203-79db-11dd-ba6f-0014222a9f79")
    boolean acceptDroppedElements(final MObject[] element);

    /**
     * Set the field value to the given element and end the picking session
     * if the given element is valid.
     * 
     * @param dropedElement The new represented elements in the field
     */
    @objid ("e451adfe-79db-11dd-ba6f-0014222a9f79")
    void setDroppedElements(final MObject[] dropedElement);

}
