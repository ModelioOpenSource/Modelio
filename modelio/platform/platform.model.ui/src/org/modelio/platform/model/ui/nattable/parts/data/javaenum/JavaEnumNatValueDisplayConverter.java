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

package org.modelio.platform.model.ui.nattable.parts.data.javaenum;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.nebula.widgets.nattable.data.convert.DisplayConverter;
import org.modelio.platform.model.ui.nattable.parts.data.bool.IBooleanNatValue;

/**
 * Data type converter for an {@link Enum} editor.
 * <p>
 * Assumes that the data value is stored as a {@link IBooleanNatValue}.
 * </p>
 */
@objid ("dda4b810-fd7a-4315-944c-23d6ca6eb7b6")
public class JavaEnumNatValueDisplayConverter extends DisplayConverter {
    @objid ("786eb01e-b503-46ea-9da7-b50fcc6b1707")
    private final Class<?> enumType;

    /**
     * Default constructor for this converter.
     * 
     * @param enumType the enum type to be converted.
     */
    @objid ("f855ddd0-d4d8-4741-b5a5-a3da6e47c165")
    public JavaEnumNatValueDisplayConverter(Class<?> enumType) {
        this.enumType = enumType;
    }

    @objid ("b1853544-7e36-451b-9f38-b6da8e85b049")
    @Override
    public Object canonicalToDisplayValue(Object obj) {
        if (obj == null) {
            return "undefined";
        }
        
        final Enum<?> l;
        if (obj instanceof Enum) {
            l = (Enum<?>) obj;
        } else {
            return obj;
        }
        return l.name();
    }

    @objid ("400f5074-c9aa-4133-a110-aea9d1853689")
    @Override
    public Object displayToCanonicalValue(Object displayedValue) {
        for (final Object l : this.enumType.getEnumConstants()) {
            if (l.toString().equals(displayedValue)) {
                return l;
            }
        }
        return this.enumType.getEnumConstants()[0];
    }

}
