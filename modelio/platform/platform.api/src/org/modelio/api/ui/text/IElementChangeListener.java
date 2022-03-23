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
package org.modelio.api.ui.text;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Interface for listeners on TextWrapperForIElement.
 */
@objid ("5bf03d9a-911c-11e0-9de7-002564c97630")
public interface IElementChangeListener {
    /**
     * Called when the selected element changes.
     * @param oldElement previously selected element. May be null.
     * @param newElement newly selected element. May be null.
     */
    @objid ("d9658cc8-5b07-11e2-9c97-002564c97630")
    void selectedElementChanged(final MObject oldElement, final MObject newElement);

}
