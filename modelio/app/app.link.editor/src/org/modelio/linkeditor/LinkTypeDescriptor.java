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
package org.modelio.linkeditor;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.vcore.smkernel.mapi.MClass;

/**
 * Represents a link type by its MClass possibly completed by a stereotype.
 */
@objid ("979c8c31-3356-4201-a0b3-0baaf7251782")
public class LinkTypeDescriptor implements Comparable<LinkTypeDescriptor> {
    @objid ("379f50c8-d029-4ce7-a316-8fa90476da07")
    private final MClass mClass;

    @objid ("3ded20c7-008f-42b4-b033-7aac1883d1e4")
    private final Stereotype stereotype;

    @objid ("56a225db-91c6-48d6-a960-b4fc0e38d53f")
    public  LinkTypeDescriptor(MClass mClass, Stereotype stereotype) {
        this.mClass = mClass;
        this.stereotype = stereotype;
        
    }

    @objid ("951a6b36-1681-4b5c-b6f7-cfaed216ba40")
    @Override
    public int compareTo(LinkTypeDescriptor o) {
        int mcCompare = this.mClass.getQualifiedName().compareTo(o.mClass.getQualifiedName());
        if (mcCompare == 0) {
            String s1 = this.stereotype != null ? this.stereotype.getName() : "";
            String s2 = o.stereotype != null ? o.stereotype.getName() : "";
            return s1.compareTo(s2);
        } else {
            return mcCompare;
        }
        
    }

    @objid ("6be54d76-7031-4414-b7c5-836b344f68a6")
    public MClass getMClass() {
        return this.mClass;
    }

    @objid ("235e79cb-be6f-49a1-bc64-a7e9ffa44955")
    public Stereotype getStereotype() {
        return this.stereotype;
    }

    @objid ("65c10aa7-2a5c-4895-bb6f-d682b110b59b")
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.mClass == null) ? 0 : this.mClass.hashCode());
        result = prime * result + ((this.stereotype == null) ? 0 : this.stereotype.hashCode());
        return result;
    }

    @objid ("f88feb2d-33ff-4757-b35b-16e9fbb5b0b5")
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
        LinkTypeDescriptor other = (LinkTypeDescriptor) obj;
        if (this.mClass == null) {
            if (other.mClass != null) {
                return false;
            }
        } else if (!this.mClass.equals(other.mClass)) {
            return false;
        }
        if (this.stereotype == null) {
            if (other.stereotype != null) {
                return false;
            }
        } else if (!this.stereotype.equals(other.stereotype)) {
            return false;
        }
        return true;
    }

    @objid ("21db0a4a-482b-484f-9014-ab82802ddcf1")
    @Override
    public String toString() {
        return "LinkTypeDescriptor [mClass=" + this.mClass + ", stereotype=" + this.stereotype + "]";
    }

}
