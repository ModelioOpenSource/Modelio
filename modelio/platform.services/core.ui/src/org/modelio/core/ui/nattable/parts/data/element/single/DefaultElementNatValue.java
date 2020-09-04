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

package org.modelio.core.ui.nattable.parts.data.element.single;

import java.security.InvalidParameterException;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.nattable.parts.data.DefaultNatValue;
import org.modelio.vcore.session.api.model.IMObjectFilter;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Default implementation of {@link IElementNatValue}.
 */
@objid ("6d52dc57-3e04-4aa5-be2c-300415440e9a")
public class DefaultElementNatValue extends DefaultNatValue implements IElementNatValue {
    @objid ("b5ec6d57-19a8-4ee8-ab5f-5eebde2a4370")
    protected List<Class<? extends MObject>> allowedClasses = null;

    @objid ("b59024ce-d2ed-4208-9c68-8b3d9af26bc4")
    protected IMObjectFilter elementFilter = null;

    /**
     * Creates a new instance.
     * @param value the wrapped value.
     * @param acceptNullValue whether or not <code>null</code> is a valid value for this field.
     * @param allowedClasses list of the accepted metaclasses for the value.
     */
    @objid ("fe6f7807-500b-4fa8-8ae4-d65b9361a018")
    public DefaultElementNatValue(MObject value, boolean acceptNullValue, List<Class<? extends MObject>> allowedClasses) {
        super(value, acceptNullValue);
        this.allowedClasses = allowedClasses;
    }

    /**
     * Copy constructor, creating a new instance with the same configuration as the other.
     * @param anotherInstance the instance to copy.
     */
    @objid ("62969e17-d736-4c14-9826-0bfd8ef6be7f")
    public DefaultElementNatValue(DefaultElementNatValue anotherInstance) {
        super(anotherInstance);
        this.allowedClasses = anotherInstance.allowedClasses;
        this.elementFilter = anotherInstance.elementFilter;
    }

    @objid ("29dd2ee2-d268-4013-8f04-a825f26fca9a")
    @Override
    public List<Class<? extends MObject>> getAllowedClasses() {
        return this.allowedClasses;
    }

    @objid ("31137711-be23-437c-b7ff-29d61f4ec5f3")
    @Override
    public IMObjectFilter getElementFilter() {
        return this.elementFilter;
    }

    @objid ("e83f9085-7c34-4271-baad-4242cd5a57d8")
    @Override
    public MObject getValue() {
        return (MObject) super.getValue();
    }

    @objid ("3d601675-076a-41d7-a6eb-b0a1a67217f1")
    @Override
    public void setElementFilter(IMObjectFilter elementFilter) {
        this.elementFilter = elementFilter;
    }

    @objid ("8ea83567-d707-4b05-a798-640c69131624")
    @Override
    public void setValue(Object value) {
        if (value != null && !(value instanceof MObject)) {
            throw new InvalidParameterException("Value must be a MObject.");
        }
        super.setValue(value);
    }

    @objid ("a5360e66-343d-4e07-856b-6f1527466396")
    @Override
    public String toString() {
        return String.format("%s [value=%s]", getClass().getSimpleName(), getValue());
    }

}
