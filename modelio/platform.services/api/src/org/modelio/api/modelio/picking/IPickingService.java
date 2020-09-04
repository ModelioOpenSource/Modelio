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

/**
 * Element picking services for picking providers. <p>
 * 
 * This service allows edition of GUI fields representing the type of an attribute, parameter...
 * by clicking on the new type in: <ul>
 * <li> the browser,
 * <li> a diagram
 * <li> or any GUI part implementing the {@link IPickingProvider} interface.
 * </ul>
 * The GUI field typically implements the {@link IPickingClient} interface. <br>
 * The browser and the diagrams implement the {@link IPickingProvider} interface. <p>
 * 
 * When the user clicks on an picking enabled field : <ul>
 * <li> the IPickingClient calls {@linkplain IPickingService#startPickingSession(IPickingClient)}.
 * <li> the IPickingService calls {@linkplain IPickingProvider#enterPickingMode()} on all {@link IPickingProvider}.
 * </ul>
 * 
 * Then, when the user moves the mouse on an element in the the browser or any {@link IPickingProvider}: <ul>
 * <li> the IPickingProvider calls {@linkplain IPickingService#hoverElement(Element)} on the {@link IPickingService}, that forwards it to the client
 * <li> the IPickingClient answer to calls to {@linkplain IPickingClient#acceptElement(Element)}.
 * </ul>
 * 
 * When the user clicks on an element in the the browser or any IPickingProvider: <ul>
 * <li> the IPickingProvider calls {@linkplain IPickingService#selectElement(Element)} on the {@link IPickingService}, that forwards it to the client<br>
 * <li> the IPickingClient receive a call to {@linkplain IPickingClient#setElement(Element)} <br>
 * <li> the IPickingClient calls {@linkplain IPickingService#endPickingSession(IPickingClient)} on the {@link IPickingService} <br>
 * <li> the IPickingClientService forwards {@linkplain IPickingProvider#leavePickingMode()} on all {@link IPickingProvider} <br>
 * <li> the IPickingClient returns true from {@linkplain IPickingClient#setElement(Element)}  call </ul>
 * @see IPickingProvider
 * @see IPickingClient
 */
@objid ("4bc6a23e-6bf5-11e0-a371-001ec947cd2a")
public interface IPickingService {
    /**
     * Registers an element picking provider . <p>
     * 
     * The provider will be called each time a picking session is
     * opened or closed by a IPickingClient.
     * 
     * @param pickingProvider The provider to add.
     */
    @objid ("8953f5ea-7585-11e0-8651-001ec947cd2a")
    void registerPickingProvider(final IPickingProvider pickingProvider);

    /**
     * Remove an element picking provider.
     * 
     * @param pickingProvider The provider to remove.
     */
    @objid ("89565844-7585-11e0-8651-001ec947cd2a")
    void unregisterPickingProvider(final IPickingProvider pickingProvider);

    /**
     * Begin a picking session. <p>
     * 
     * @param client The client that begins the picking session.
     */
    @objid ("8956584c-7585-11e0-8651-001ec947cd2a")
    IPickingSession startPickingSession(final IPickingClient client);

    /**
     * Abort the picking. <p>
     * Calling this method cause the call of the pickingAborted on all picking clients.
     * @param client The client to end picking on.
     */
    @objid ("8956584f-7585-11e0-8651-001ec947cd2a")
    void endPickingSession(final IPickingSession session);

}
