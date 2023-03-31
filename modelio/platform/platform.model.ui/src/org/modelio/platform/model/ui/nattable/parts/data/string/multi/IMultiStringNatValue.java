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
package org.modelio.platform.model.ui.nattable.parts.data.string.multi;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.platform.model.ui.nattable.parts.data.IMultiNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.INatValue;

/**
 * Defines an {@link INatValue} wrapping a collection of {@link String} instances.
 */
@objid ("bc1d6edb-1f21-4fb4-b729-241635c4a3d6")
public interface IMultiStringNatValue extends IMultiNatValue {
    @objid ("09107103-247e-4fe0-a1bd-7e2147164994")
    @Override
    List<String> getValue();
}

