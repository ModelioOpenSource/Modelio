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

/**
 * A picking provider is a GUI part that allows the user to select
 * an element by a single click .
 * 
 * The provider then calls setElement() on the PickingService that will
 * forward the call to the IPickingClient that opened the picking session.
 */
@objid ("f61174c6-7584-11e0-8651-001ec947cd2a")
public interface IPickingProvider {
    /**
     * Begin a picking session.
     * 
     * The provider must then call setElement() on the PickingService
     * when a model element is clicked with the mouse.
     */
    @objid ("33b8aa50-7585-11e0-8651-001ec947cd2a")
    void enterPickingMode(IPickingSession session);

    /**
     * Ends the picking session.
     * 
     * The provider must returns to its original behavior.
     */
    @objid ("33b8aa4f-7585-11e0-8651-001ec947cd2a")
    void leavePickingMode(IPickingSession session);

}
