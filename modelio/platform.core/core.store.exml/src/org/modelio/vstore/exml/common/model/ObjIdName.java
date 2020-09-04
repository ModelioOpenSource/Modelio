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

package org.modelio.vstore.exml.common.model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MRef;
import org.modelio.vcore.smkernel.meta.SmClass;

/**
 * Represents an object identifier with its name.
 */
@objid ("9c386308-57d3-4b77-b8e6-2566e8559155")
public final class ObjIdName {
    /**
     * Object name.
     */
    @objid ("5e6ab092-042e-4efb-9d43-75c77e9021e5")
    public final String name;

    /**
     * Object identifier.
     */
    @objid ("0f2c33ca-23f4-4371-b31a-0d88b285e03c")
    public final String id;

    /**
     * Object metaclass
     */
    @objid ("0516c1c5-cf7c-42c9-af88-58759cfff24f")
    public final SmClass classof;

    /**
     * Constructor.
     * @param classof the metaclass
     * @param name the object name
     * @param id the object identifier.
     */
    @objid ("f0ec2175-1cc6-4e6e-8f66-9c0f784716df")
    public ObjIdName(SmClass classof, final String name, final String id) {
        this.classof = classof;
        this.name = name;
        this.id = id;
    }

    /**
     * Indicates whether some other ObjId is "equal to" this one.
     * @param other another ObjId
     * @return <code>true</code> if they are equal else <code>false</code>
     */
    @objid ("53e19342-80ed-45d8-9a4b-85fd966b90d9")
    public boolean equals(final ObjIdName other) {
        return (other != null && this.id.equals(other.id) && this.classof == other.classof);
    }

    @objid ("91686835-ff71-4218-bf09-81dad829b1ce")
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        ObjIdName other = (ObjIdName) obj;
        if (this.classof == null) {
            if (other.classof != null) {
                return false;
            }
        } else if (!this.classof.equals(other.classof)) {
            return false;
        }
        
        if (this.id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!this.id.equals(other.id)) {
            return false;
        }
        return true;
    }

    @objid ("bf0f6011-32af-4b9f-b314-223754eccda9")
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        // Use only the metaclass name to compute hash code so that changes to the metamodel version
        // on the metamodel fragment don't change the hash code.
        result = prime * result + ((this.classof == null) ? 0 : this.classof.getName().hashCode());
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        return result;
    }

    @objid ("26427348-dcb4-4042-a25b-e1742b231f72")
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.name != null) {
            sb.append('\'');
            sb.append(this.name);
            sb.append("' ");
        }
        sb.append("{");
        sb.append(this.id);
        sb.append("} ");
        sb.append(this.classof.getName());
        sb.append(" ObjId");
        return sb.toString();
    }

    /**
     * Constructor from a {@link SmObjectImpl}.
     * <p>
     * Warning : this access the object SmObjectData which may invoke restoration from swap
     * or the repository. Don't use this constructor in repository loading.
     * @param object a model object.
     * @return an initialized ObjId.
     * @deprecated this access the object SmObjectData which may invoke restoration from swap
     * or the repository. Don't use this constructor in repository loading.
     */
    @objid ("dd1bdbc1-90cf-4c36-9487-fc456626bedc")
    @Deprecated
    public static ObjIdName withName(SmObjectImpl object) {
        String name;
        if (object.getRepositoryObject().isAttLoaded(object, null)) {
            name = object.getName();
        } else {
            name = null;
        }
        return new ObjIdName(object.getClassOf(), name, object.getUuid());
    }

    /**
     * Convert to MRef.
     * @return a MRef.
     */
    @objid ("fc91e0f2-26eb-45f7-904a-a194c4c1f1c2")
    public MRef toMRef() {
        return new MRef(this.classof.getQualifiedName(), this.id, this.name);
    }

    /**
     * Convert to {@link ObjId}.
     * @return an {@link ObjId}
     */
    @objid ("2217dac8-c73d-4077-9e28-ba0d162222cb")
    public ObjId toObjId() {
        return new ObjId(this.classof, this.id);
    }

}
