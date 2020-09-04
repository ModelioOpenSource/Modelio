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

package org.modelio.core.ui.nattable.parts.data.number.time;

import java.security.InvalidParameterException;
import java.util.Date;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.nattable.parts.data.DefaultNatValue;

/**
 * Default implementation of {@link ITimeNatValue}.
 */
@objid ("e70807a1-0126-4935-a9ae-cc63aadccdc8")
public class DefaultTimeNatValue extends DefaultNatValue implements ITimeNatValue {
    /**
     * Creates a new instance.
     * 
     * @param value the wrapped value.
     */
    @objid ("3743fb84-c46b-4621-ad08-b5b88b837352")
    public DefaultTimeNatValue(Date value) {
        super(value, false);
    }

    /**
     * Copy constructor, creating a new instance with the same configuration as the other.
     * 
     * @param anotherInstance the instance to copy.
     */
    @objid ("970e85e8-70b4-4e4a-b3bf-b88686cdd026")
    public DefaultTimeNatValue(DefaultTimeNatValue anotherInstance) {
        super(anotherInstance);
    }

    @objid ("f2e279d5-3203-446a-be1e-d04ffa55f722")
    @Override
    public Date getValue() {
        return (Date) super.getValue();
    }

    @objid ("8fcf55cc-5da8-4031-a9c2-d11862e4748f")
    @Override
    public void setValue(Object value) {
        if (value != null && !(value instanceof Date)) {
            throw new InvalidParameterException("Value must be a Date.");
        }
        super.setValue(value);
    }

}
