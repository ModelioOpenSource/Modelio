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
package org.modelio.platform.model.ui.nattable.parts.data.text;

import java.security.InvalidParameterException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.platform.model.ui.nattable.parts.data.DefaultNatValue;

/**
 * Default implementation of {@link ITextNatValue}.
 */
@objid ("86fe65c6-e838-4165-b20f-c806b29cc66f")
public class DefaultTextNatValue extends DefaultNatValue implements ITextNatValue {
    /**
     * Creates a new instance.
     * @param value the wrapped value.
     * @param acceptNullValue whether or not <code>null</code> is a valid value for this field.
     */
    @objid ("dd50d4c5-f5cd-4b5e-9424-c7940aeec7c1")
    public  DefaultTextNatValue(String value, boolean acceptNullValue) {
        super(value, acceptNullValue);
    }

    /**
     * Copy constructor, creating a new instance with the same configuration as the other.
     * @param anotherInstance the instance to copy.
     */
    @objid ("67523413-3486-4ba5-a453-2ffc1e6e397e")
    public  DefaultTextNatValue(DefaultTextNatValue anotherInstance) {
        super(anotherInstance);
    }

    @objid ("64f40343-8cb5-4ae6-b556-4916e0bbdfa7")
    @Override
    public String getValue() {
        return (String) super.getValue();
    }

    @objid ("05cf8c67-4a04-4b47-9ba9-6f3d14f2cbdd")
    @Override
    public void setValue(Object value) {
        if (value != null && !(value instanceof String)) {
            throw new InvalidParameterException("Value must be a String.");
        }
        super.setValue(value);
        
    }

}
