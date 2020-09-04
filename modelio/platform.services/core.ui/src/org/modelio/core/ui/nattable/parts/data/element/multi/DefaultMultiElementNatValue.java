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

package org.modelio.core.ui.nattable.parts.data.element.multi;

import java.util.ArrayList;
import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.nattable.parts.data.DefaultNatValue;
import org.modelio.vcore.session.api.model.IMObjectFilter;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Default implementation of {@link IMultiElementNatValue}.
 */
@objid ("11ee2d90-ac9d-4eea-a769-655ac9fdcdc0")
public class DefaultMultiElementNatValue extends DefaultNatValue implements IMultiElementNatValue {
    @objid ("639bfd92-a569-4a5e-8e43-8d3e9ddeed95")
    protected Collection<Class<? extends MObject>> allowedClasses = null;

    @objid ("0403b728-c3c8-47f7-a70c-c626faaa6bdc")
    protected IMObjectFilter elementFilter = null;

    /**
     * Creates a new instance.
     * 
     * @param value the wrapped value.
     * @param acceptNullValue whether or not <code>null</code> is a valid value for this field.
     * @param allowedClasses list of the accepted metaclasses for the value.
     */
    @objid ("a693409e-98ed-4d56-8925-d170a4e1ad9b")
    public DefaultMultiElementNatValue(Collection<MObject> value, boolean acceptNullValue, Collection<Class<? extends MObject>> allowedClasses) {
        super(new ArrayList<>(value), acceptNullValue);
        this.allowedClasses = allowedClasses;
    }

    /**
     * Copy constructor, creating a new instance with the same configuration as the other.
     * 
     * @param anotherInstance the instance to copy.
     */
    @objid ("e9fde442-d4b8-4a48-8b75-7e5dfd4a2566")
    public DefaultMultiElementNatValue(DefaultMultiElementNatValue anotherInstance) {
        super(anotherInstance);
    }

    @objid ("6fe35160-ec28-4a29-97dc-8854fad33882")
    @Override
    public Collection<Class<? extends MObject>> getAllowedClasses() {
        return this.allowedClasses;
    }

    @objid ("af8ccb0b-f90f-42c4-a348-35a586c49137")
    @Override
    public IMObjectFilter getElementFilter() {
        return this.elementFilter;
    }

    @objid ("a9d7ce60-c1bf-4527-851b-ac81be7807f3")
    @SuppressWarnings("unchecked")
    @Override
    public Collection<MObject> getValue() {
        return (Collection<MObject>) super.getValue();
    }

    @objid ("cd1f35d4-f85e-4401-8ca9-bff933a7db97")
    @Override
    public void setElementFilter(IMObjectFilter elementFilter) {
        this.elementFilter = elementFilter;
    }

    @objid ("9ecb4b04-59d1-4e72-a63a-b020dcc15132")
    @Override
    public void setValue(Object value) {
        if (value != null && !(value instanceof Collection)) {
            throw new IllegalArgumentException("Value must be a MObject Collection.");
        }
        super.setValue(value);
    }

}
