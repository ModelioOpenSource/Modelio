/* 
 * Copyright 2013-2019 Modeliosoft
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

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.mdl;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * <p>Descriptor for a link metaclass.</p>
 */
@objid ("bde61dce-9809-46f6-9f81-dea9464e25ed")
public class MLinkMetaclassDescriptor extends MClassDescriptor {
    /**
     * <p>Name of the dependencies that represent the link sources.</p>
     */
    @mdl.prop
    @objid ("185e9a96-7319-4027-91c5-e486713e70e9")
    private final List<String> sourceDepencencies = new ArrayList<> ();

    @mdl.propgetter
    public List<String> getSourceDepencencies() {
        // Automatically generated method. Please do not modify this code.
        return this.sourceDepencencies;
    }

    /**
     * <p>Name of the dependencies that represent the link targets.</p>
     */
    @mdl.prop
    @objid ("e97a30bf-3482-4f52-8d3b-04a96c8631e6")
    private final List<String> targetDepencencies = new ArrayList<> ();

    @mdl.propgetter
    public List<String> getTargetDepencencies() {
        // Automatically generated method. Please do not modify this code.
        return this.targetDepencencies;
    }

    @objid ("e1493f1a-4025-4173-86df-11219d39a609")
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((this.sourceDepencencies == null) ? 0 : this.sourceDepencencies.hashCode());
        result = prime * result + ((this.targetDepencencies == null) ? 0 : this.targetDepencencies.hashCode());
        return result;
    }

    @objid ("f3e2375a-71c3-4567-b544-230be3c07faf")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        MLinkMetaclassDescriptor other = (MLinkMetaclassDescriptor) obj;
        if (this.sourceDepencencies == null) {
            if (other.sourceDepencencies != null) {
                return false;
            }
        } else if (!this.sourceDepencencies.equals(other.sourceDepencencies)) {
            return false;
        }
        if (this.targetDepencencies == null) {
            if (other.targetDepencencies != null) {
                return false;
            }
        } else if (!this.targetDepencencies.equals(other.targetDepencencies)) {
            return false;
        }
        return true;
    }

}
