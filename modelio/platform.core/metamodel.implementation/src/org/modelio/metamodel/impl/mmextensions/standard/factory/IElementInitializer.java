/* 
 * Copyright 2013-2019 Modeliosoft
 * 
 * This file is part of Modelio.
 * 
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Modelio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package org.modelio.metamodel.impl.mmextensions.standard.factory;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Interface that defines classes that are able to initialize a newly created element.
 * <p>
 * Implementers might also do some additional initializations based on a given property map.
 */
@objid ("82ff8174-7ad5-43d1-9447-e7d37035edf8")
interface IElementInitializer {
    /**
     * Initialize the given element.
     * @param modelFactory
     * the model creation factory.
     * 
     * @param element The element to initialize.
     */
    @objid ("3d379920-fe53-4c13-8e13-3fd8d81ecd4d")
    void initialize(MObject element);

    @objid ("430e26d9-521f-4c04-b65d-8d329142c15e")
    void setDefaultValue(String key, Object value);

}
