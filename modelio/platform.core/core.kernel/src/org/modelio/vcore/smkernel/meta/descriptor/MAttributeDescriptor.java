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

package org.modelio.vcore.smkernel.meta.descriptor;

import java.io.Serializable;
import com.modeliosoft.modelio.javadesigner.annotations.mdl;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Metamodel attribute descriptor.
 * @author cma
 * @since 3.6
 */
@objid ("6ed5dfcd-a248-490b-a762-d71c7cfa64b8")
public class MAttributeDescriptor implements Serializable {
    @mdl.prop
    @objid ("e53bd4f1-cc0d-490d-bf82-48ae4a7da34c")
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

    @objid ("96104fec-e06c-4b8f-8124-1877fbfadc1a")
    private static final long serialVersionUID = 1L;

    @mdl.prop
    @objid ("87193928-1a9c-4f2c-8911-015fb21f10e6")
    private String enumType;

    @mdl.propgetter
    public String getEnumType() {
        // Automatically generated method. Please do not modify this code.
        return this.enumType;
    }

    @mdl.propsetter
    public void setEnumType(String value) {
        // Automatically generated method. Please do not modify this code.
        this.enumType = value;
    }

    @mdl.prop
    @objid ("ddbc9732-898c-4958-8ac3-7f56b6f4a152")
    private Class<?> type;

    @mdl.propgetter
    public Class<?> getType() {
        // Automatically generated method. Please do not modify this code.
        return this.type;
    }

    @mdl.propsetter
    public void setType(Class<?> value) {
        // Automatically generated method. Please do not modify this code.
        this.type = value;
    }

    @objid ("15a8c72e-a94b-4ea9-ab51-0b0983496eb6")
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.enumType == null) ? 0 : this.enumType.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.type == null) ? 0 : this.type.hashCode());
        return result;
    }

    @objid ("ed2ec09d-2af5-42d4-9116-02334bc4b67a")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        MAttributeDescriptor other = (MAttributeDescriptor) obj;
        if (this.enumType == null) {
            if (other.enumType != null) {
                return false;
            }
        } else if (!this.enumType.equals(other.enumType)) {
            return false;
        }
        if (this.name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!this.name.equals(other.name)) {
            return false;
        }
        if (this.type == null) {
            if (other.type != null) {
                return false;
            }
        } else if (!this.type.equals(other.type)) {
            return false;
        }
        return true;
    }

    @objid ("ae1d9a32-6c2a-4c12-a191-36a6e1527ce4")
    @Override
    public String toString() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return
                super.toString() +
                "\n MAttributeDescriptor = [" +
                "name=" + (this.name != null ? "\"" + this.name + "\"" : null) +
                ", enumType=" + (this.enumType != null ? "\"" + this.enumType + "\"" : null) +
                ", type=" + this.type +
                "]";
    }

}
