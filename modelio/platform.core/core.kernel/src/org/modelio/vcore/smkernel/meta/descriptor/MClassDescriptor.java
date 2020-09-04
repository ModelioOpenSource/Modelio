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
import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.mdl;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.version.Version;

/**
 * Metaclass descriptor.
 * 
 * @author cma
 * @since 3.6
 */
@objid ("a04ba7a0-3a0b-4735-8b55-51f4969dfe39")
public class MClassDescriptor implements Serializable {
    @mdl.prop
    @objid ("cc92dd8b-6228-45d1-8082-3361409939b3")
    private boolean cmsNode;

    @mdl.propgetter
    public boolean isCmsNode() {
        // Automatically generated method. Please do not modify this code.
        return this.cmsNode;
    }

    @mdl.propsetter
    public void setCmsNode(boolean value) {
        // Automatically generated method. Please do not modify this code.
        this.cmsNode = value;
    }

    @mdl.prop
    @objid ("7c99a649-37d2-4625-9272-c14a2f29e704")
    private boolean abstrakt;

    @mdl.propgetter
    public boolean isAbstrakt() {
        // Automatically generated method. Please do not modify this code.
        return this.abstrakt;
    }

    @mdl.propsetter
    public void setAbstrakt(boolean value) {
        // Automatically generated method. Please do not modify this code.
        this.abstrakt = value;
    }

    @mdl.prop
    @objid ("9757f1aa-249e-495d-81c4-0b6f9678f1db")
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
    @objid ("aa6c545d-c25f-496a-9492-1072b9713d51")
    private boolean fake;

    @mdl.propgetter
    public boolean isFake() {
        // Automatically generated method. Please do not modify this code.
        return this.fake;
    }

    @mdl.propsetter
    public void setFake(boolean value) {
        // Automatically generated method. Please do not modify this code.
        this.fake = value;
    }

    @objid ("dc4ac586-8a61-4eee-b191-66fcb0f0c1fd")
    private static final long serialVersionUID = 1L;

    @mdl.prop
    @objid ("fed8b124-a4c7-4fba-8056-c37ce1223827")
    private final List<MDependencyDescriptor> dependencies = new ArrayList<> ();

    @mdl.propgetter
    public List<MDependencyDescriptor> getDependencies() {
        // Automatically generated method. Please do not modify this code.
        return this.dependencies;
    }

    @mdl.prop
    @objid ("846261a3-133b-4172-848a-4b17e3714e3a")
    private final List<MAttributeDescriptor> attributes = new ArrayList<> ();

    @mdl.propgetter
    public List<MAttributeDescriptor> getAttributes() {
        // Automatically generated method. Please do not modify this code.
        return this.attributes;
    }

    @mdl.prop
    @objid ("3f4675b5-f963-4181-bd2a-d96d0ee13dd7")
    private Version version;

    @mdl.propgetter
    public Version getVersion() {
        // Automatically generated method. Please do not modify this code.
        return this.version;
    }

    @mdl.propsetter
    public void setVersion(Version value) {
        // Automatically generated method. Please do not modify this code.
        this.version = value;
    }

    @mdl.prop
    @objid ("ed2a407e-dc88-4b81-9dff-4bd65b7359c5")
    private MClassRef parent;

    @mdl.propgetter
    public MClassRef getParent() {
        // Automatically generated method. Please do not modify this code.
        return this.parent;
    }

    @mdl.propsetter
    public void setParent(MClassRef value) {
        // Automatically generated method. Please do not modify this code.
        this.parent = value;
    }

    @objid ("18086b59-2e4b-4f17-a1d9-429d72067803")
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (this.abstrakt ? 1231 : 1237);
        result = prime * result + ((this.attributes == null) ? 0 : this.attributes.hashCode());
        result = prime * result + (this.cmsNode ? 1231 : 1237);
        result = prime * result + ((this.dependencies == null) ? 0 : this.dependencies.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.parent == null) ? 0 : this.parent.hashCode());
        result = prime * result + ((this.version == null) ? 0 : this.version.hashCode());
        return result;
    }

    @objid ("7f702e0f-1668-4b7f-8b6f-a978a0ffa1f7")
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
        MClassDescriptor other = (MClassDescriptor) obj;
        if (this.abstrakt != other.abstrakt) {
            return false;
        }
        if (this.attributes == null) {
            if (other.attributes != null) {
                return false;
            }
        } else if (!this.attributes.equals(other.attributes)) {
            return false;
        }
        if (this.cmsNode != other.cmsNode) {
            return false;
        }
        if (this.dependencies == null) {
            if (other.dependencies != null) {
                return false;
            }
        } else if (!this.dependencies.equals(other.dependencies)) {
            return false;
        }
        if (this.name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!this.name.equals(other.name)) {
            return false;
        }
        if (this.parent == null) {
            if (other.parent != null) {
                return false;
            }
        } else if (!this.parent.equals(other.parent)) {
            return false;
        }
        if (this.version == null) {
            if (other.version != null) {
                return false;
            }
        } else if (!this.version.equals(other.version)) {
            return false;
        }
        return true;
    }

    @objid ("af674f29-44fb-49e7-a64e-1df9ccf14409")
    @Override
    public String toString() {
        return getClass().getSimpleName()+" = [" +
                                "name=" + (this.name != null ? "\"" + this.name + "\"" : null) +
                                "]";
    }

}
