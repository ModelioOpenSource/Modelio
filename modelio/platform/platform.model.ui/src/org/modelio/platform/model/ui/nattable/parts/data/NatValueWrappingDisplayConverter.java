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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.nebula.widgets.nattable.data.convert.DisplayConverter;
import org.eclipse.nebula.widgets.nattable.data.convert.IDisplayConverter;

/**
 * Data type converter for any editor working with an {@link INatValue}.
 * <p>
 * Assumes that the data value is stored as a {@link INatValue}.
 * </p>
 * <p>
 * This editor delegates the conversion to a wrapped converter after unwrapping the value from the {@link INatValue}.
 * </p>
 */
@objid ("831af93b-0b10-4d4b-81a2-05ca066eb691")
public class NatValueWrappingDisplayConverter extends DisplayConverter {
    @objid ("55e74184-db3e-46b9-8b3b-7f2e0a52dff5")
    private final IDisplayConverter wrappedConverter;

    /**
     * Build a new converter.
     * 
     * @param wrappedConverter the converter to delegate the conversion to after unwrapping the value from the {@link INatValue}.
     */
    @objid ("fbc30964-de4b-4ac3-b951-4484e62a35fc")
    public NatValueWrappingDisplayConverter(IDisplayConverter wrappedConverter) {
        super();
        this.wrappedConverter = wrappedConverter;
    }

    /**
     * Transform a canonical object of type <i>INatValue</i> to a string.
     */
    @objid ("406aa2cd-fff4-420e-8ffe-15bac692a78c")
    @Override
    public Object canonicalToDisplayValue(Object canonicalValue) {
        return this.wrappedConverter.canonicalToDisplayValue(INatValue.getValue(canonicalValue));
    }

    @objid ("dc8a646b-9db3-4bee-96e2-9f112782f5b1")
    @Override
    public Object displayToCanonicalValue(Object displayValue) {
        return this.wrappedConverter.displayToCanonicalValue(displayValue);
    }

}
