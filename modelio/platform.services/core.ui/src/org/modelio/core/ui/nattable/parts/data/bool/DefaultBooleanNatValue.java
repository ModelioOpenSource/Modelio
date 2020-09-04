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

package org.modelio.core.ui.nattable.parts.data.bool;

import java.security.InvalidParameterException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.nattable.parts.data.DefaultNatValue;

/**
 * Default implementation of {@link IBooleanNatValue}.
 */
@objid ("c9222de9-2516-4304-b317-7ffbd206b818")
public class DefaultBooleanNatValue extends DefaultNatValue implements IBooleanNatValue {
    /**
     * Creates a new instance.
     * 
     * @param value the wrapped value.
     */
    @objid ("cd621802-969c-476a-806f-b815ed642d6b")
    public DefaultBooleanNatValue(Boolean value) {
        super(value, false);
    }

    /**
     * Copy constructor, creating a new instance with the same configuration as the other.
     * 
     * @param anotherInstance the instance to copy.
     */
    @objid ("de31b96a-d98d-4589-b883-2bc5c4db6147")
    public DefaultBooleanNatValue(DefaultBooleanNatValue anotherInstance) {
        super(anotherInstance);
    }

    @objid ("8680fc45-9a60-4b61-8e1a-e7be18b6fddb")
    @Override
    public Boolean getValue() {
        return (Boolean) super.getValue();
    }

    @objid ("3b3075db-f349-44b0-a4f3-66b22eadd087")
    @Override
    public void setValue(Object value) {
        if (value != null && !(value instanceof Boolean)) {
            throw new InvalidParameterException("Value must be a Boolean.");
        }
        super.setValue(value);
    }

}
