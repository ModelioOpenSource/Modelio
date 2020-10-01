/* 
 * Copyright 2013-2020 Modeliosoft
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

package org.modelio.metamodel.impl.mmextensions.infrastructure.factory;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Interface that defines classes that are able to initialize a newly created element.
 * <p>
 * Implementers might also do some additional initializations based on a given property map.
 */
@objid ("472967f6-69ad-47e5-ae5c-5b093dc31cc5")
interface IInfrastructureElementInitializer {
    /**
     * Initialize the given element.
     * @param modelFactory
     * the model creation factory.
     * 
     * @param element The element to initialize.
     */
    @objid ("6f6d5c56-2dc3-475f-b4ee-dc128827142c")
    void initialize(MObject element);

    @objid ("f46f2e56-5bdf-47c0-a132-7fb684c23a92")
    void setDefaultValue(String key, Object value);

}
