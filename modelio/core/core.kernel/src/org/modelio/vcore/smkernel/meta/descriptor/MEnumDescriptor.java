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

package org.modelio.vcore.smkernel.meta.descriptor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.mdl;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Metamodel enumeration descriptor.
 * @author cma
 * @since 3.6
 */
@objid ("90afe5fc-ba42-450f-aa2b-21b56ac5df1e")
public class MEnumDescriptor implements Serializable {
    @mdl.prop
    @objid ("45b4ae73-27a8-4191-abc4-8dc9e90d8d42")
    private final List<String> values = new ArrayList<> ();

    @mdl.propgetter
    public List<String> getValues() {
        // Automatically generated method. Please do not modify this code.
        return this.values;
    }

    @mdl.prop
    @objid ("94c63b4e-6c2d-41a1-9b67-1bc2a892c626")
    private String name;

    @mdl.propgetter
    public String getName() {
        // Automatically generated method. Please do not modify this code.
        return this.name;
    }

    @mdl.propsetter
    public void setName(String value) {
        // Automatically generated method. Please do not modify this code.
        this.name = value;
    }

    @objid ("e391eced-5522-4589-b101-111cf88f26ae")
    private static final long serialVersionUID = 1L;

    @objid ("81a690a9-9e4f-4f51-b8bf-8b2591ee5d9d")
    @Override
    public boolean equals(Object obj) {
        // Automatically generated method. Please delete this comment before entering specific code.
        if (obj==null) {
            return false;
        }
        
        if (this == obj) {
            return true;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        MEnumDescriptor other = (MEnumDescriptor) obj;
        if (this.values == null ? other.values != null :
           !this.values.equals(other.values)) {
            return false;
        }
        if (this.name == null ? other.name != null :
           !this.name.equals(other.name)) {
            return false;
        }
        return true;
    }

    @objid ("67f81100-fc78-47c5-9cce-cd7091b0e3e3")
    @Override
    public int hashCode() {
        // Automatically generated method. Please delete this comment before entering specific code.
        int result = super.hashCode();
        result = 31 * result + (this.values != null ? this.values.hashCode() : 0);
        result = 31 * result + (this.name != null ? this.name.hashCode() : 0);
        return result;
    }

    @objid ("6008bc7f-9846-48de-b48d-862369b36279")
    @Override
    public String toString() {
        return "MEnumDescriptor = [" +
                                                " name=" + (this.name != null ? "\"" + this.name + "\"" : null) +
                                                "]";
    }

}
