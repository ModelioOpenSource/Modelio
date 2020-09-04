/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.core.ui.nattable.parts.data.number._integer;

import java.security.InvalidParameterException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.nattable.parts.data.DefaultNatValue;

/**
 * Default implementation of {@link IIntegerNatValue}.
 */
@objid ("c848380c-4936-42cb-b50f-048df96c560d")
public class DefaultIntegerNatValue extends DefaultNatValue implements IIntegerNatValue {
    /**
     * Creates a new instance.
     * @param value the wrapped value.
     */
    @objid ("1b7c0c25-c209-4922-b546-943666495661")
    public DefaultIntegerNatValue(Integer value) {
        super(value, false);
    }

    /**
     * Copy constructor, creating a new instance with the same configuration as the other.
     * @param anotherInstance the instance to copy.
     */
    @objid ("d40ebc1d-1ed7-4951-9d6a-2cf5910a8a12")
    public DefaultIntegerNatValue(DefaultIntegerNatValue anotherInstance) {
        super(anotherInstance);
    }

    @objid ("05c168ea-73ed-4834-98fd-b1a951dfa527")
    @Override
    public Integer getValue() {
        return (Integer) super.getValue();
    }

    @objid ("dd599670-ad96-478e-b5b4-c81b5fa38baa")
    @Override
    public void setValue(Object value) {
        if (value != null && !(value instanceof Integer)) {
            throw new InvalidParameterException("Value must be a Integer.");
        }
        super.setValue(value);
    }

}
