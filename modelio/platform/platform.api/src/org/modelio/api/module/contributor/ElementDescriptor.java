/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package org.modelio.api.module.contributor;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.vcore.smkernel.mapi.MClass;

/**
 * Represents an element type by its MClass possibly completed with a stereotype.
 */
@objid ("9bfc6004-a5bd-44ae-8c11-55ce9e7100fd")
public class ElementDescriptor implements Comparable<ElementDescriptor> {
    @objid ("45ee6f6c-1d48-4532-8ce7-617020e0d1d9")
    private final MClass mClass;

    @objid ("6bb6a456-dfe6-4da8-9247-9500a3feb052")
    private final Stereotype stereotype;

    @objid ("b260b1f0-ff16-4093-9670-f08aa31e1b4c")
    public  ElementDescriptor(MClass mClass, Stereotype stereotype) {
        this.mClass = mClass;
        this.stereotype = stereotype;
        
    }

    @objid ("d933c24c-c055-40d0-b50c-ce5ed342b569")
    @Override
    public int compareTo(ElementDescriptor o) {
        int mcCompare = this.mClass.getQualifiedName().compareTo(o.mClass.getQualifiedName());
        if (mcCompare == 0) {
            String s1 = this.stereotype != null ? this.stereotype.getName() : "";
            String s2 = o.stereotype != null ? o.stereotype.getName() : "";
            return s1.compareTo(s2);
        } else {
            return mcCompare;
        }
        
    }

    @objid ("08fededf-ee18-4ac3-86ba-a36e7d870e00")
    public MClass getMClass() {
        return this.mClass;
    }

    @objid ("97e6b237-7d6d-47e3-85d6-130290a79c87")
    public Stereotype getStereotype() {
        return this.stereotype;
    }

    @objid ("abb84462-7b97-4f57-a051-ec61421022a6")
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.mClass == null) ? 0 : this.mClass.hashCode());
        result = prime * result + ((this.stereotype == null) ? 0 : this.stereotype.hashCode());
        return result;
    }

    @objid ("8d3d7771-7d4f-4169-8ff3-5706d86bfd4c")
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
        ElementDescriptor other = (ElementDescriptor) obj;
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

}
