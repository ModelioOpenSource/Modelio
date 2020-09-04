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

package org.modelio.api.ui.text;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Interface allowing to filter IElements through its {@link #accept(IElement) method}.
 */
@objid ("5bf0d9e0-911c-11e0-9de7-002564c97630")
public interface IElementFilter {
    /**
     * This method checks if an element is accepted.
     * @param element the model element to check.
     * @return <code>true</code> if the element is accepted.
     */
    @objid ("5bf0d9e1-911c-11e0-9de7-002564c97630")
    boolean accept(final MObject element);

}
