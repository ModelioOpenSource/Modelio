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

package org.modelio.vstore.exml.common.model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MRef;
import org.modelio.vcore.smkernel.meta.SmClass;

/**
 * Represents an object identifier.
 * <p>
 * Differences with MRef are the metaclass is a reference to MClass and there is no 'name'.
 */
@objid ("fd26ba09-5986-11e1-991a-001ec947ccaf")
public final class ObjId implements Comparable<ObjId> {
    /**
     * Object identifier.
     */
    @objid ("0198f1cb-34b1-4d4f-94b5-5cd0936c6ef9")
    public final String id;

    /**
     * Object metaclass
     */
    @objid ("fd21f565-5986-11e1-991a-001ec947ccaf")
    public final SmClass classof;

    /**
     * Constructor from a {@link SmObjectImpl}.
     * <p>
     * 
     * @param object a model object.
     */
    @objid ("fd24575e-5986-11e1-991a-001ec947ccaf")
    public ObjId(SmObjectImpl object) {
        this.id = object.getUuid();
        this.classof = object.getClassOf();
    }

    /**
     * Constructor.
     * 
     * @param classof the metaclass
     * @param id the object identifier.
     */
    @objid ("fd24574d-5986-11e1-991a-001ec947ccaf")
    public ObjId(SmClass classof, final String id) {
        this.classof = classof;
        this.id = id;
    }

    /**
     * Indicates whether some other ObjId is "equal to" this one.
     * 
     * @param other another ObjId
     * @return <code>true</code> if they are equal else <code>false</code>
     */
    @objid ("fd21f729-5986-11e1-991a-001ec947ccaf")
    public boolean equals(final ObjId other) {
        return (other != null && this.id.equals(other.id) && this.classof == other.classof);
    }

    @objid ("d5bf8482-6231-11e1-b31a-001ec947ccaf")
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
        
        ObjId other = (ObjId) obj;
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

    @objid ("d5bf847d-6231-11e1-b31a-001ec947ccaf")
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

    @objid ("d503c743-7f1a-11e1-ba70-001ec947ccaf")
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder()
        .append('{')
        .append(this.id)
        .append("} ")
        .append(this.classof.getName())
        .append(" ObjId");
        return sb.toString();
    }

    /**
     * Convert to MRef.
     * <p>
     * The MRef will have null pointer as name.
     * 
     * @return a MRef.
     */
    @objid ("86d71357-8714-4194-859d-f3b32df5d31f")
    public MRef toMRef() {
        return new MRef(this.classof.getQualifiedName(), this.id, null);
    }

    /**
     * Compares the identifier, then the metaclass qualified name.
     */
    @objid ("fce4ee2a-008c-420f-9afb-fe713fb07098")
    @Override
    public int compareTo(ObjId o) {
        int c = this.id.compareTo(o.id);
        if (c == 0) {
            return this.classof.getQualifiedName().compareTo(o.classof.getQualifiedName());
        }
        return c;
    }

}
