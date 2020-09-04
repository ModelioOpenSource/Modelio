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

package org.modelio.core.ui.nattable.parts.data.number.date;

import java.security.InvalidParameterException;
import java.util.Date;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.nattable.parts.data.DefaultNatValue;

/**
 * Default implementation of {@link IDateNatValue}.
 */
@objid ("a2f0f051-d702-4f65-8373-e277fe99129a")
public class DefaultDateNatValue extends DefaultNatValue implements IDateNatValue {
    /**
     * Creates a new instance.
     * 
     * @param value the wrapped value.
     */
    @objid ("4aa07473-bd53-414c-b6fd-11677ff2c58b")
    public DefaultDateNatValue(Date value) {
        super(value, false);
    }

    /**
     * Copy constructor, creating a new instance with the same configuration as the other.
     * 
     * @param anotherInstance the instance to copy.
     */
    @objid ("cee72ea3-b28d-4ca4-8d5f-1174efad4d9c")
    public DefaultDateNatValue(DefaultDateNatValue anotherInstance) {
        super(anotherInstance);
    }

    @objid ("2ebb02ba-0279-4135-ba49-8826c0e85f0d")
    @Override
    public Date getValue() {
        return (Date) super.getValue();
    }

    @objid ("8837b4a4-201b-46e4-b3f5-7bdf2bde55ff")
    @Override
    public void setValue(Object value) {
        if (value != null && !(value instanceof Date)) {
            throw new InvalidParameterException("Value must be a Date.");
        }
        super.setValue(value);
    }

}
