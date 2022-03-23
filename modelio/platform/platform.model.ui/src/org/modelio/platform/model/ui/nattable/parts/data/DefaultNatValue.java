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
package org.modelio.platform.model.ui.nattable.parts.data;

import java.security.InvalidParameterException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Abstract implementation of {@link INatValue}.
 * <p>
 * Stores 'acceptNullValue' and 'value' fields for subclasses.
 * </p>
 */
@objid ("9454af25-895a-43f5-910f-c48875a58fe2")
public abstract class DefaultNatValue implements INatValue {
    @objid ("3988a20a-d773-430b-a9cf-a658a2064d33")
    private boolean acceptNullValue = false;

    @objid ("b7b2880e-77d5-43f9-b582-742219f12754")
    private Object value;

    /**
     * Creates a new instance.
     * @param value the wrapped value.
     * @param acceptNullValue whether or not <code>null</code> is a valid value for this field.
     */
    @objid ("b3c646a5-262d-4e75-abf2-8bfec6c0844b")
    public  DefaultNatValue(Object value, boolean acceptNullValue) {
        this.acceptNullValue = acceptNullValue;
        this.value = value;
        
    }

    @objid ("1746db22-8b25-4db8-bf98-85a384675fbe")
    @Override
    public boolean acceptNullValue() {
        return this.acceptNullValue;
    }

    @objid ("3f578b6b-21a5-43ef-a557-7f91ae47c8b2")
    @Override
    public Object getValue() {
        return this.value;
    }

    @objid ("10a47c52-c644-48c8-9e0f-52e595b47f9b")
    @Override
    public void setValue(Object value) {
        if (value == null && !this.acceptNullValue) {
            throw new InvalidParameterException("Value can't be null.");
        }
        this.value = value;
        
    }

    /**
     * Copy constructor, creating a new instance with the same configuration as the other.
     * @param anotherInstance the instance to copy.
     */
    @objid ("7219d3d6-6a0e-42f8-aab3-ef313d0b7ae1")
    protected  DefaultNatValue(DefaultNatValue anotherInstance) {
        this.acceptNullValue = anotherInstance.acceptNullValue;
        this.value = anotherInstance.value;
        
    }

    @objid ("a1683d8e-2fe1-45dc-9a9b-2ea9a8657dcd")
    @Override
    public String toString() {
        return String.format("%s [value=%s]", getClass().getSimpleName(), getValue());
    }

}
