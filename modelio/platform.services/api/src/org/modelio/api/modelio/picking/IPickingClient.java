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

package org.modelio.api.modelio.picking;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Interface that represent a picking client. <p>
 * A picking client is a GUI field that allows the user to edit a model element type or a link destination.<p>
 * 
 * When the user clicks on the field, the IPickingClient: <br>
 * - calls beginPicking(...) on the IPickingService <br>
 * - answer to calls to acceptElement() <br>
 * - receive a call to setElement() <br>
 * - calls endPicking() on the IPickingService <br>
 * - returns true from setElement() call <p>
 * 
 * The picking client may end the picking session if its underlying field looses
 * the edit focus.
 */
@objid ("fde428ce-7584-11e0-8651-001ec947cd2a")
public interface IPickingClient {
    /**
     * Called when another picking client calls beginPicking() on the picking service.
     * The receiver then has to return to its initial state.
     */
    @objid ("33b8aa52-7585-11e0-8651-001ec947cd2a")
    void pickingAborted();

    /**
     * Returns whether the field accepts the element as a value.
     * 
     * @param target the new value candidate.
     * @return true if the new value is valid, false in the other case.
     */
    @objid ("a3b87b0e-0ecc-11e2-96c4-002564c97630")
    boolean acceptElement(MObject target);

    /**
     * Set the field value to the given element and end the picking session
     * if the given element is valid.
     * 
     * @param target The new represented element in the field
     * @return true if the new value is valid, false in the other case.
     */
    @objid ("a3b87b12-0ecc-11e2-96c4-002564c97630")
    boolean setElement(MObject target);

}
