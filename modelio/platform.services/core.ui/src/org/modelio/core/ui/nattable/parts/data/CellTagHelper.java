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

package org.modelio.core.ui.nattable.parts.data;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.nattable.parts.data.INatValue;

/**
 * Simple helper class computing a unique tag from an {@link INatValue} class.
 */
@objid ("684514af-5420-484a-8029-d8a7b3ea8fcd")
public class CellTagHelper {
    @objid ("88fddd40-5656-437a-b5a8-5a4569ffe934")
    public static String getTypeTag(Class<? extends INatValue> baseType) {
        return getTypeTag(baseType, "");
    }

    @objid ("ff206117-8f18-40c3-9be5-9da48a6ed44c")
    public static String getTypeTag(Class<? extends INatValue> baseType, String suffix) {
        if (!baseType.isInterface()) {
            for (Class<?> itf : baseType.getInterfaces()) {
                return itf.getName() + ".tag." + suffix;
            }
        }
        return baseType.getName() + ".tag." + suffix;
    }

}
