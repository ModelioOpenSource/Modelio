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
package org.modelio.api.modelio.picking;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("31aac3a4-3a9b-4bd6-9ac1-0452c9b065b9")
public interface IPickingSession {
    /**
     * Ask the picking client whether it accepts the given element. <br>
     * This method is called by a picking provider when the mouse is moved on a model element.
     * @param target The model element
     * @return true if the element is accepted, false in the other case
     */
    @objid ("a3eef5c3-0ecc-11e2-96c4-002564c97630")
    boolean hoverElement(final MObject target);

    /**
     * Tell the picking client to pick the given element. <br>
     * This method is called by a picking provider when the user click on a model element. <br>
     * The picking client may either accept the element and close the picking session, or refuse it.
     * @param target The model element
     */
    @objid ("a3ef1cd5-0ecc-11e2-96c4-002564c97630")
    void selectElement(final MObject target);
}

