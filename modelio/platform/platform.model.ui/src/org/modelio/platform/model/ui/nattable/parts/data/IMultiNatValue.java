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

package org.modelio.platform.model.ui.nattable.parts.data;

import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Defines an {@link INatValue} which associated Object value has a cardinality of <i>0..*</i>,
 * and is stored as a {@link Collection}.
 */
@objid ("00681ec4-0ba0-4407-aa44-cb9faf381696")
public interface IMultiNatValue extends INatValue {
    @objid ("06dbdf7c-e6a0-4bbe-ac02-d46ab34f23df")
    @Override
    Collection<?> getValue();

}
