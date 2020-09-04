/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.api.modelio.diagram.style;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * A style handle is a named proxy towards a style definition.
 * Its name is used as an identifier to find it among shared styles.
 * <p>
 * A style provides many properties such has the foreground and background color, the font and some display options.
 * <p>
 * A Style holds a local property map where the property value are fetched from. A Style is also attached to a
 * cascadedStyle that is used as a defaulting mechanism when a property value is not available in the local map.
 */
@objid ("e97cd670-861c-11e0-b2a1-002564c97630")
public interface IStyleHandle {
    /**
     * @return The style name.
     */
    @objid ("e97cd671-861c-11e0-b2a1-002564c97630")
    String getName();

}
