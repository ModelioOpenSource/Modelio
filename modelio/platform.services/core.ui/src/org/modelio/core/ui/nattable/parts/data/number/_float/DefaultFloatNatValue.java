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

package org.modelio.core.ui.nattable.parts.data.number._float;

import java.security.InvalidParameterException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.nattable.parts.data.DefaultNatValue;

/**
 * Default implementation of {@link IFloatNatValue}.
 */
@objid ("31994490-60ae-4c34-9457-17a66c609665")
public class DefaultFloatNatValue extends DefaultNatValue implements IFloatNatValue {
    /**
     * Creates a new instance.
     * 
     * @param value the wrapped value.
     */
    @objid ("59a997cf-7dfc-4963-a8cf-784c68143a15")
    public DefaultFloatNatValue(Float value) {
        super(value, false);
    }

    /**
     * Copy constructor, creating a new instance with the same configuration as the other.
     * 
     * @param anotherInstance the instance to copy.
     */
    @objid ("6c7d114f-0e50-4c70-8c63-be031f4ff4aa")
    public DefaultFloatNatValue(DefaultFloatNatValue anotherInstance) {
        super(anotherInstance);
    }

    @objid ("c0ee6e1d-3835-4958-a372-027f33cfc2c0")
    @Override
    public Float getValue() {
        return (Float) super.getValue();
    }

    @objid ("26efdc7e-7926-4147-8ec0-319159efc389")
    @Override
    public void setValue(Object value) {
        if (value != null && !(value instanceof Float)) {
            throw new InvalidParameterException("Value must be a Float.");
        }
        super.setValue(value);
    }

}
