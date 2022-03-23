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
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Metamodel descriptor.
 * 
 * @author cma
 */
@objid ("f48de3af-a114-46ab-9e89-13307d843abe")
public class MetamodelDescriptor implements Serializable {
    /**
     * Metamodel descriptor format version.
     * <p>
     * Increment it when modifying the descriptors.
     */
    @objid ("cfa80844-107d-48c6-b34a-d031bd10e13c")
    public static final long serialVersionUID = 1;

    @objid ("ea429064-a0e7-4984-93fa-32531f08b116")
    private final Map<String, MetamodelFragmentDescriptor> fragments = new TreeMap<> ();

    @objid ("e17f67f2-d13a-4758-973e-3c202db7a0f0")
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.fragments == null) ? 0 : this.fragments.hashCode());
        return result;
    }

    @objid ("457eb828-ad7c-4708-b811-070b1a97824e")
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
        MetamodelDescriptor other = (MetamodelDescriptor) obj;
        if (this.fragments == null) {
            if (other.fragments != null) {
                return false;
            }
        } else if (!this.fragments.equals(other.fragments)) {
            return false;
        }
        return true;
    }

    @objid ("7a030655-c865-460e-a9bc-d4643ad0e4da")
    @Override
    public String toString() {
        return
                        "MetamodelDescriptor[" +
                        "fragments=" + this.fragments.values()
                        .stream()
                        .map(f -> f.getName()+" v"+f.getVersion())
                        .collect(Collectors.joining(", "))+
                        "]";
    }

    /**
     * Add a metamodel fragment.
     * @param fd a metamodel fragment.
     */
    @objid ("43667447-0251-4f42-9ee5-f14bb537faa1")
    public void addFragment(MetamodelFragmentDescriptor fd) {
        MetamodelFragmentDescriptor prev = getFragments().putIfAbsent(fd.getName(), fd);
        if (prev != null) {
            throw new IllegalArgumentException(prev+" already registered.");
        }
        
    }

    /**
     * @return the metamodel fragment descriptors.
     */
    @objid ("342827a1-c6e1-4aaa-ac0c-2ca023a99c27")
    public Map<String, MetamodelFragmentDescriptor> getFragments() {
        return this.fragments;
    }

}
