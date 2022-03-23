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
package org.modelio.platform.model.ui.nattable.parts.data.number._unsigned;

import java.security.InvalidParameterException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.platform.model.ui.nattable.parts.data.DefaultNatValue;

/**
 * Default implementation of {@link IUnsignedNatValue}.
 */
@objid ("854b0c53-da97-4e8c-a2f1-95e56ba09054")
public class DefaultUnsignedNatValue extends DefaultNatValue implements IUnsignedNatValue {
    /**
     * Creates a new instance.
     * @param value the wrapped value.
     */
    @objid ("9b6d05bd-6fba-4faa-9f99-e575c7e284b1")
    public  DefaultUnsignedNatValue(Integer value) {
        super(value, false);
    }

    /**
     * Copy constructor, creating a new instance with the same configuration as the other.
     * @param anotherInstance the instance to copy.
     */
    @objid ("d107bad3-bfa8-4ce4-ab4d-23d746b88367")
    public  DefaultUnsignedNatValue(DefaultUnsignedNatValue anotherInstance) {
        super(anotherInstance);
    }

    @objid ("fea6fd8a-47e2-47a1-b22b-ad8474ff024f")
    @Override
    public Integer getValue() {
        return (Integer) super.getValue();
    }

    @objid ("885ee91c-bda0-4436-b39b-dc34c2d5d13b")
    @Override
    public void setValue(Object value) {
        if (value != null && !(value instanceof Integer)) {
            throw new InvalidParameterException("Value must be an Integer.");
        } else if (value != null && ((Integer)value) < 0) {
            throw new InvalidParameterException("Value must be a positive Integer.");
        }
        super.setValue(value);
        
    }

}
