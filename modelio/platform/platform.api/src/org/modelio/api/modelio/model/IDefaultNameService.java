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
package org.modelio.api.modelio.model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.ModelElement;

/**
 * Help class used when an element needs a "default" name.
 * <p>
 * Default is understood as "the name that it would have taken if you had created it through the UI in the most
 * usual way (no smart interaction/wizard/etc)" .
 * <p>
 * The 4 main methods are:
 * <ul>
 * <li>setDefaultName( IModelElement element ) and setDefaultName( IAbstractDiagram diagram ): the most simple ones,
 * where you just request a name for the passed element or diagram.<br>
 * The name returned will follow the following construction: "base_name" + "postfix_counter". <br>
 * The base name is usually the metaclass name or something like that. <br>
 * The postfix counter will be an integer computed to make sure that the name returned is not already used by
 * another element of the same type in the same parent element.
 * <li>setDefaultName( IModelElement element, String prefix ): here you additionally give a prefix to be used
 * (default value: "copy of").
 * <li>setdefaultNameUsingBaseName( IModelElement element, String baseName ): here you give the base name to be used
 * (in the default version, the metaclass name or something close is usually used).
 * </ul>
 */
@objid ("89f96195-6c28-11e0-b589-002564c97630")
public interface IDefaultNameService {
    /**
     * Set an element default name. The name is composed of an invariant radical
     * (mostly based on the metaclass of the element) optionally followed by an
     * integer counter to distinguish from other elements of the same type in
     * the same container.
     * @param element an element
     */
    @objid ("a4306d43-0ecc-11e2-96c4-002564c97630")
    void setDefaultName(ModelElement element);

    /**
     * Set an element default name. The name is composed of the given baseName
     * as radical, optionally followed by an integer counter to distinguish from
     * other elements of the same type in the same container.
     * @param element an element
     * @param baseName the radical to use.
     */
    @objid ("a4309451-0ecc-11e2-96c4-002564c97630")
    void setDefaultName(ModelElement element, String baseName);
}

