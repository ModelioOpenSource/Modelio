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

import java.security.InvalidParameterException;
import java.util.Date;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.platform.model.ui.nattable.parts.data.DefaultNatValue;

/**
 * Default implementation of {@link IJavaEnumNatValue}.
 */
@objid ("3562bed9-45e6-4e62-9ae7-41fe7c46eec6")
public class DefaultJavaEnumNatValue extends DefaultNatValue implements IJavaEnumNatValue {
    @objid ("9c598ccd-0893-434a-9fe8-c8fe1ba7deac")
    private Class<? extends Enum<?>> theEnumeration;

    /**
     * Creates a new instance.
     * 
     * @param value the wrapped value.
     * @param theEnumeration enumeration to choose values from.
     */
    @objid ("1ef5749a-d94d-4e62-b426-a5601baab1a8")
    public DefaultJavaEnumNatValue(Enum<?> value, Class<? extends Enum<?>> theEnumeration) {
        super(value, false);
        this.theEnumeration = theEnumeration;
    }

    /**
     * Copy constructor, creating a new instance with the same configuration as the other.
     * 
     * @param anotherInstance the instance to copy.
     */
    @objid ("383ebc17-e396-41c5-b820-bb6a9f9c2518")
    public DefaultJavaEnumNatValue(DefaultJavaEnumNatValue anotherInstance) {
        super(anotherInstance);
        this.theEnumeration = anotherInstance.theEnumeration;
    }

    @objid ("5ada8fc4-16c4-494c-b584-543b05dacaf5")
    @Override
    public Class<? extends Enum<?>> getEnumeration() {
        return this.theEnumeration;
    }

    @objid ("205d84b3-0ece-432d-b729-4fb27492c693")
    @Override
    public Enum<?> getValue() {
        return (Enum<?>) super.getValue();
    }

    @objid ("199c7659-277e-4a64-9b6b-4d55683644ce")
    @Override
    public void setValue(Object value) {
        if (value != null && !(value instanceof Date)) {
            throw new InvalidParameterException("Value must be an Enumeration.");
        }
        super.setValue(value);
    }

}
