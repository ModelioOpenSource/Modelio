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

package org.modelio.core.ui.nattable.parts.data.string.single;

import java.security.InvalidParameterException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.nattable.parts.data.DefaultNatValue;

/**
 * Default implementation of {@link IStringNatValue}.
 */
@objid ("8a62ae37-15a0-42db-96aa-df819e4d0c1d")
public class DefaultStringNatValue extends DefaultNatValue implements IStringNatValue {
    /**
     * Creates a new instance.
     * 
     * @param value the wrapped value.
     * @param acceptNullValue whether or not <code>null</code> is a valid value for this field.
     */
    @objid ("2fd86c43-f248-42c7-bfb9-917add4d0c49")
    public DefaultStringNatValue(String value, boolean acceptNullValue) {
        super(value, acceptNullValue);
    }

    /**
     * Copy constructor, creating a new instance with the same configuration as the other.
     * 
     * @param anotherInstance the instance to copy.
     */
    @objid ("3da4e4d6-372a-43f4-b11c-d0beb03c2eeb")
    public DefaultStringNatValue(DefaultStringNatValue anotherInstance) {
        super(anotherInstance);
    }

    @objid ("7121f560-2df9-49a0-b667-7e750d0e8cfd")
    @Override
    public String getValue() {
        return (String) super.getValue();
    }

    @objid ("e565cf84-4fcb-464c-b0ed-dd8a951267a5")
    @Override
    public void setValue(Object value) {
        if (value != null && !(value instanceof String)) {
            throw new InvalidParameterException("Value must be a String.");
        }
        super.setValue(value);
    }

}
