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

package org.modelio.api.modelio.navigation;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Service that can "navigate" a model element.<p>
 * Navigating a class may for example: <br>
 * - show and select the element in the browser <br>
 * - show and select the element in the opened diagram <br>
 * 
 * The service works by first registering navigation listeners. <br>
 * Then, calling {@link #fireNavigate(Element)} will call each listener .
 * @see INavigationListener
 */
@objid ("dcd98198-e6e1-11dd-9e73-0014222a9f79")
public interface INavigationService {
    /**
     * Register a navigation listener.
     * 
     * @param listener a navigation listener.
     */
    @objid ("dcd9819a-e6e1-11dd-9e73-0014222a9f79")
    void addNavigationListener(INavigationListener listener);

    /**
     * Remove a navigation listener.
     * 
     * @param listener a navigation listener.
     */
    @objid ("dcd9819b-e6e1-11dd-9e73-0014222a9f79")
    void removeNavigationListener(INavigationListener listener);

    /**
     * Fire all navigation listeners.
     * 
     * @param target The navigated element.
     */
    @objid ("a3d26c58-0ecc-11e2-96c4-002564c97630")
    void fireNavigate(MObject target);

    /**
     * Fire all navigation listeners.
     * 
     * @param targets The navigated elements.
     */
    @objid ("5923c631-4f5d-40de-b325-e2fe1958d76c")
    void fireNavigate(List<MObject> targets);

}
