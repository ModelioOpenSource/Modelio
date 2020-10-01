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

package org.modelio.vcore.smkernel.meta.fake;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmClass;

/**
 * Fake SmAttribute implementation.
 */
@objid ("08cda3da-dfc8-428b-bcac-d1d93ef50c84")
public class FakeSmAttribute extends SmAttribute {
    @objid ("342d25a7-73ae-4840-902a-f8855f41fa4d")
    private final Object defaultValue;

    /**
     * Create a String fake attribute.
     * 
     * @param owner the metaclass
     * @param name the name
     */
    @objid ("447be8b6-cec9-4e67-8b2d-d645af0e39da")
    public FakeSmAttribute(SmClass owner, String name) {
        init(name, owner, String.class);
        this.defaultValue = getDefaultValue(String.class);
    }

    @objid ("4c2529a0-b512-4566-bd19-cb1369bc572a")
    @Override
    public Object getValue(ISmObjectData data) {
        return cast(data).content.getOrDefault(this, this.defaultValue);
    }

    @objid ("8ae9e4f2-bae5-4df7-87f3-d2b752f6a749")
    @Override
    public void setValue(ISmObjectData object, Object value) {
        cast(object).content.put(this, value);
    }

    /**
     * Fluent version of cast operator to make code more readable.
     * 
     * @param data the data to cast to {@link FakeSmObjectData}
     * @return the casted data.
     */
    @objid ("b5dc7e96-6d7f-4dd6-a7b5-ee3f516229a0")
    private static final FakeSmObjectData cast(ISmObjectData data) {
        return (FakeSmObjectData) data;
    }

    /**
     * Create a fake copy of a SmAttribute.
     * 
     * @param owner the metaclass that will own this attribute.
     * @param orig the SmAttribute to copy.
     */
    @objid ("87076ce4-5e06-4e29-b943-55f78be1e676")
    public FakeSmAttribute(SmClass owner, SmAttribute orig) {
        init(orig.getName(), owner, orig.getType(), orig.getDirectives());
        this.defaultValue = getDefaultValue(orig.getType());
    }

    @objid ("9093fe2d-309f-4e16-a93d-702649830dac")
    private static Object getDefaultValue(Class<?> type) {
        if (type == String.class) {
            return "";
        } else if (type == long.class || type == Long.class) {
            return 0L;
        } else if (type == int.class || type == Integer.class) {
            return 0;
        } else if (type == short.class || type == Short.class) {
            return (short) 0;
        } else if (type == float.class || type == Float.class) {
            return 0.0f;
        } else if (type == boolean.class || type == Boolean.class) {
            return Boolean.FALSE;
        } else if (type == double.class || type == Double.class) {
            return (double) 0.0d;
        } else if (type.isEnum()) {
            return type.getEnumConstants()[0];
        }
        throw new IllegalArgumentException(String.format("Unsupported type: %s",type));
    }

}
