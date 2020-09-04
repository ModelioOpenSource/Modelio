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

package org.modelio.core.ui.nattable.parts.data.string.multi;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.nattable.parts.data.DefaultNatValue;

/**
 * Default implementation of {@link IMultiStringNatValue}.
 */
@objid ("4d2191f1-ebfe-4124-aa7a-9e31d238dbc6")
public class DefaultMultiStringNatValue extends DefaultNatValue implements IMultiStringNatValue {
    /**
     * Creates a new instance.
     * @param value the wrapped value.
     * @param acceptNullValue whether or not <code>null</code> is a valid value for this field.
     */
    @objid ("aee1d8fe-6fb4-498b-9551-b40e0622e233")
    public DefaultMultiStringNatValue(List<String> value, boolean acceptNullValue) {
        super(new ArrayList<>(value), acceptNullValue);
    }

    /**
     * Copy constructor, creating a new instance with the same configuration as the other.
     * @param anotherInstance the instance to copy.
     */
    @objid ("dbfed6dc-5129-45de-a34c-df2b74478bc3")
    public DefaultMultiStringNatValue(DefaultMultiStringNatValue anotherInstance) {
        super(anotherInstance);
    }

    @objid ("e5abfea0-a5ce-476a-8b0c-00113d02db32")
    @SuppressWarnings("unchecked")
    @Override
    public List<String> getValue() {
        return (List<String>) super.getValue();
    }

    @objid ("ef7ea0a1-ea33-4a14-a904-1c2472d2c8b7")
    @Override
    public void setValue(Object value) {
        if (value != null && !(value instanceof List)) {
            throw new InvalidParameterException("Value must be a String List.");
        }
        super.setValue(value);
    }

}
