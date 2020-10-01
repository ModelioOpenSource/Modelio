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

package org.modelio.vcore.smkernel.meta.mof;

import com.modeliosoft.modelio.javadesigner.annotations.mdl;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.ISmObjectData;
import org.modelio.vcore.smkernel.SmObjectSmClass;
import org.modelio.vcore.smkernel.meta.SmAttribute;

/**
 * MOF SmAttribute implementation.
 */
@objid ("85f40d95-6072-4eb0-b236-9ff2c6f56664")
public class MofSmAttribute extends SmAttribute {
    @mdl.prop
    @objid ("b16d479e-3614-4db8-9c52-d16674c43679")
    private boolean temporary;

    @mdl.propgetter
    public boolean isTemporary() {
        // Automatically generated method. Please do not modify this code.
        return this.temporary;
    }

    @mdl.propsetter
    public void setTemporary(boolean value) {
        // Automatically generated method. Please do not modify this code.
        this.temporary = value;
    }

    /**
     * Create a String MOF attribute.
     * 
     * @param owner the metaclass
     * @param name the name
     */
    @objid ("2d9754ae-3ba7-4ff7-82c8-9beaf523c809")
    public MofSmAttribute(MofSmClass owner, String name) {
        if (name.equals("status") && owner.getName().equals(SmObjectSmClass.MNAME)) {
            // special case for SmObject.status
            init(name, owner, Long.class);
        } else {
            init(name, owner, String.class);
        }
    }

    @objid ("d9200568-5bae-480b-8699-6176236ae17b")
    @Override
    public Object getValue(ISmObjectData data) {
        return ((MofSmObjectData)data).content.get(getName());
    }

    @objid ("9555fbd5-fca5-4856-9f13-daddd14cf2ae")
    @Override
    public void setValue(ISmObjectData data, Object value) {
        ((MofSmObjectData)data).content.put(getName(), value);
    }

    /**
     * Copy constructor
     * 
     * @param owner the owner MOF metaclass
     * @param original the attribute to copy
     */
    @objid ("7882bb3a-83df-409f-b1ef-a47000427d8a")
    public MofSmAttribute(MofSmClass owner, SmAttribute original) {
        if (original.getName().equals("status") && original.getOwner().getName().equals(SmObjectSmClass.MNAME)) {
            // We need the SmObject.status attribute to keep its original type
            init(original.getName(), owner, original.getType(), original.getDirectives());
        } else {
            // All other MOF attributes are Strings.
            init(original.getName(), owner, String.class, original.getDirectives());
        }
    }

}
