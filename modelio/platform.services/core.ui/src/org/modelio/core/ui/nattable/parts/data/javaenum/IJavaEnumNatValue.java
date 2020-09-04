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

package org.modelio.core.ui.nattable.parts.data.javaenum;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.nattable.parts.data.INatValue;
import org.modelio.core.ui.nattable.parts.data.ISingleNatValue;

/**
 * Defines an {@link INatValue} wrapping a single {@link Enum} instance.
 */
@objid ("4719f68e-eab7-4155-985d-473112c67eac")
public interface IJavaEnumNatValue extends ISingleNatValue {
    /**
     * @return the enumeration used to choose the values from.
     */
    @objid ("b62a5bae-778a-42e1-81c3-79e5432a94ae")
    Class<? extends Enum<?>> getEnumeration();

    @objid ("55a97aea-e84a-40c6-9933-152d8019cf6f")
    @Override
    Enum<?> getValue();

}
