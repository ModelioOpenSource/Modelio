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
 * Listener for the navigation service.
 * @see INavigationService
 */
@objid ("c814c59c-e6e1-11dd-9e73-0014222a9f79")
public interface INavigationListener {
    /**
     * Implementation should display and select the element in its view.
     * 
     * @param target The navigated element.
     */
    @objid ("a41d0bd1-0ecc-11e2-96c4-002564c97630")
    void navigateTo(MObject target);

    /**
     * Implementation should display and select the elements in its view.
     * 
     * @param targets List of the navigated elements.
     */
    @objid ("5f16e38a-fcd2-4d5e-866b-b6f87a0a1a0f")
    void navigateTo(List<MObject> targets);

}
