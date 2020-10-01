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
import org.eclipse.nebula.widgets.nattable.NatTable;

/**
 * This interface is designed to represent a cell content in the data layer of a {@link NatTable}.
 * <p>
 * It wraps an {@link Object} with a few services to be used by the cell's editor and renderer.
 * </p>
 */
@objid ("eccfd302-cb80-4fec-a22f-d69b1782ffe1")
public interface INatValue extends Cloneable {
    /**
     * @return whether or not <code>null</code> is a valid value to be set.
     */
    @objid ("13b19c47-992c-4922-8e8f-d9a50c314ff2")
    boolean acceptNullValue();

    /**
     * Gets the data associated with the cell.
     * 
     * @return the cell's underlying value. Might be <code>null</code> as long as {@link #acceptNullValue()} is set to <code>true</code>.
     */
    @objid ("d1bdab27-fa30-480c-a5df-afc5e598fb91")
    Object getValue();

    /**
     * Sets the data associated with the cell.
     * Arbitrary objects may be put in this field.
     * 
     * @param value the new cell's value.
     * @throws java.security.InvalidParameterException if value is <code>null</code> and {@link #acceptNullValue()} is set to <code>false</code>.
     */
    @objid ("1b035b15-0e7a-423a-9366-6b24109b454f")
    void setValue(Object value) throws InvalidParameterException;

    /**
     * Get the argument value if it is a {@link INatValue}.
     * 
     * @param valueOrNatValue may be a {@link INatValue} or anything else.
     * @return the {@link INatValue#getValue()} or the argument if it is not a {@link INatValue}.
     */
    @objid ("8e212dbe-1331-4eab-8a1c-fa484b912217")
    static Object getValue(Object valueOrNatValue) {
        if (valueOrNatValue instanceof INatValue) {
            return ((INatValue)valueOrNatValue).getValue();
        } else {
            return valueOrNatValue;
        }
    }

}
