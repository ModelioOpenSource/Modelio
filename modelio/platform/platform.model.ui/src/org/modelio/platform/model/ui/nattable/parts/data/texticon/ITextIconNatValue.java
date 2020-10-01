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

package org.modelio.platform.model.ui.nattable.parts.data.texticon;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.platform.model.ui.nattable.parts.data.INatValue;
import org.modelio.platform.model.ui.nattable.parts.data.ISingleNatValue;

/**
 * Defines an {@link INatValue} wrapping a single {@link String} instance that might contain more than one line.
 */
@objid ("65fbaf22-67e8-414b-892d-f290fdfb9b72")
public interface ITextIconNatValue extends ISingleNatValue {
    @objid ("78f81ad0-580c-45e3-b46d-0f88b4e78c8e")
    @Override
    TextIcon getValue();

}
