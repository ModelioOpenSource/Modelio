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
 * Metamodel dependency descriptor.
 * 
 * @author cma
 * @since 3.6
 */
@objid ("ae5aea12-6236-40b9-952a-14067c4daf00")
public class MDependencyDescriptor implements Serializable {
    @mdl.prop
    @objid ("13f4bd6d-24fa-4ecb-ad90-d81f7cdfab18")
    private MAggregation aggregation;

    @mdl.propgetter
    public MAggregation getAggregation() {
        // Automatically generated method. Please do not modify this code.
        return this.aggregation;
    }

    @mdl.propsetter
    public void setAggregation(MAggregation value) {
        // Automatically generated method. Please do not modify this code.
        this.aggregation = value;
    }

    @mdl.prop
    @objid ("51598580-e4ea-4ecf-8631-3a92fe3b3634")
    private boolean cascadeDelete;

    @mdl.propgetter
    public boolean isCascadeDelete() {
        // Automatically generated method. Please do not modify this code.
        return this.cascadeDelete;
    }

    @mdl.propsetter
    public void setCascadeDelete(boolean value) {
        // Automatically generated method. Please do not modify this code.
        this.cascadeDelete = value;
    }

    @mdl.prop
    @objid ("98cfcb64-0c3c-4972-b4db-29f4eda713aa")
    private int max;

    @mdl.propgetter
    public int getMax() {
        // Automatically generated method. Please do not modify this code.
        return this.max;
    }

    @mdl.propsetter
    public void setMax(int value) {
        // Automatically generated method. Please do not modify this code.
        this.max = value;
    }

    @mdl.prop
    @objid ("d08b23ea-48d0-488d-aede-c934c2c07a2f")
    private int min;

    @mdl.propgetter
    public int getMin() {
        // Automatically generated method. Please do not modify this code.
        return this.min;
    }

    @mdl.propsetter
    public void setMin(int value) {
        // Automatically generated method. Please do not modify this code.
        this.min = value;
    }

    @mdl.prop
    @objid ("aa596655-3d02-4d9c-ad48-35151a17e14b")
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

    @mdl.prop
    @objid ("48755031-4ae1-4ef7-bbd0-d1e098fca194")
    private boolean navigate;

    @mdl.propgetter
    public boolean isNavigate() {
        // Automatically generated method. Please do not modify this code.
        return this.navigate;
    }

    @mdl.propsetter
    public void setNavigate(boolean value) {
        // Automatically generated method. Please do not modify this code.
        this.navigate = value;
    }

    @mdl.prop
    @objid ("b1577d28-aba3-4303-bc75-20bf959ef1cc")
    private String oppositeName;

    @mdl.propgetter
    public String getOppositeName() {
        // Automatically generated method. Please do not modify this code.
        return this.oppositeName;
    }

    @mdl.propsetter
    public void setOppositeName(String value) {
        // Automatically generated method. Please do not modify this code.
        this.oppositeName = value;
    }

    @mdl.prop
    @objid ("56c99848-563a-49c0-8640-82f09b732f49")
    private boolean weakReference;

    @mdl.propgetter
    public boolean isWeakReference() {
        // Automatically generated method. Please do not modify this code.
        return this.weakReference;
    }

    @mdl.propsetter
    public void setWeakReference(boolean value) {
        // Automatically generated method. Please do not modify this code.
        this.weakReference = value;
    }

    @objid ("5870a6b8-85af-4040-8ff4-7dda771e8761")
    private static final long serialVersionUID = 1L;

    @mdl.prop
    @objid ("212b79d4-b8dc-470a-87ba-a3f0456f6315")
    private MClassRef target;

    @mdl.propgetter
    public MClassRef getTarget() {
        // Automatically generated method. Please do not modify this code.
        return this.target;
    }

    @mdl.propsetter
    public void setTarget(MClassRef value) {
        // Automatically generated method. Please do not modify this code.
        this.target = value;
    }

    @objid ("5910466a-5af6-423e-aedf-66571b32e161")
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.aggregation == null) ? 0 : this.aggregation.hashCode());
        result = prime * result + (this.cascadeDelete ? 1231 : 1237);
        result = prime * result + this.max;
        result = prime * result + this.min;
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + (this.navigate ? 1231 : 1237);
        result = prime * result + ((this.oppositeName == null) ? 0 : this.oppositeName.hashCode());
        result = prime * result + ((this.target == null) ? 0 : this.target.hashCode());
        result = prime * result + (this.weakReference ? 1231 : 1237);
        return result;
    }

    @objid ("8fb01325-f065-44c3-adac-e28e20cf850c")
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
        MDependencyDescriptor other = (MDependencyDescriptor) obj;
        if (this.aggregation != other.aggregation) {
            return false;
        }
        if (this.cascadeDelete != other.cascadeDelete) {
            return false;
        }
        if (this.max != other.max) {
            return false;
        }
        if (this.min != other.min) {
            return false;
        }
        if (this.name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!this.name.equals(other.name)) {
            return false;
        }
        if (this.navigate != other.navigate) {
            return false;
        }
        if (this.oppositeName == null) {
            if (other.oppositeName != null) {
                return false;
            }
        } else if (!this.oppositeName.equals(other.oppositeName)) {
            return false;
        }
        if (this.target == null) {
            if (other.target != null) {
                return false;
            }
        } else if (!this.target.equals(other.target)) {
            return false;
        }
        if (this.weakReference != other.weakReference) {
            return false;
        }
        return true;
    }

    @objid ("d94caf05-4431-4fef-a0cf-0e79c44a0c09")
    @Override
    public String toString() {
        return
                        "MDependencyDescriptor['" +
                        this.name  +
                        "': '" + this.target.getQualifiedName() +
                        "' [" + this.min +
                        ".. " + this.max +
                        "] aggregation=" + this.aggregation +
                        "]";
    }

}
