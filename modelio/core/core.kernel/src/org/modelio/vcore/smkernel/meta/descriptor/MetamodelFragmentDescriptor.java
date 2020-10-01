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
import org.modelio.vbasic.version.Version;
import org.modelio.vbasic.version.VersionedItem;

/**
 * Metamodel fragment descriptor.
 * 
 * @author cma
 * @since 3.6
 */
@objid ("fa0000bd-2e3c-4a87-bca7-6700e496ccaf")
public class MetamodelFragmentDescriptor implements Serializable {
    @mdl.prop
    @objid ("84c22663-4d2a-4b12-a559-0acd68d19592")
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
    @objid ("e6d61adc-4dd5-478d-a85d-03d4bb70b6c4")
    private String provider;

    @mdl.propgetter
    public String getProvider() {
        // Automatically generated method. Please do not modify this code.
        return this.provider;
    }

    @mdl.propsetter
    public void setProvider(String value) {
        // Automatically generated method. Please do not modify this code.
        this.provider = value;
    }

    @mdl.prop
    @objid ("c5d20a52-c016-44ae-861f-38ee802f0609")
    private String providerVersion;

    @mdl.propgetter
    public String getProviderVersion() {
        // Automatically generated method. Please do not modify this code.
        return this.providerVersion;
    }

    @mdl.propsetter
    public void setProviderVersion(String value) {
        // Automatically generated method. Please do not modify this code.
        this.providerVersion = value;
    }

    @mdl.prop
    @objid ("90c126ad-6759-4b3f-8cb0-dafb8e7cddfc")
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

    @objid ("80965e44-a6ac-442e-965e-90bd7e4b3284")
    private static final long serialVersionUID = 1L;

    @mdl.prop
    @objid ("e41f8995-14d5-44ff-a322-089e399f5571")
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
    @objid ("8cdb08de-df5b-41dc-94e5-f7c5adf9fc5c")
    private final List<MClassDescriptor> metaclasses = new ArrayList<> ();

    @mdl.propgetter
    public List<MClassDescriptor> getMetaclasses() {
        // Automatically generated method. Please do not modify this code.
        return this.metaclasses;
    }

    @mdl.prop
    @objid ("87f350bb-707c-4056-9abd-fb20cd81157c")
    private final List<VersionedItem> dependencies = new ArrayList<> ();

    @mdl.propgetter
    public List<VersionedItem> getDependencies() {
        // Automatically generated method. Please do not modify this code.
        return this.dependencies;
    }

    @mdl.prop
    @objid ("6c982c46-4fd0-4b19-990f-fc7aaee5bfeb")
    private final List<MEnumDescriptor> enumerations = new ArrayList<> ();

    @mdl.propgetter
    public List<MEnumDescriptor> getEnumerations() {
        // Automatically generated method. Please do not modify this code.
        return this.enumerations;
    }

    @objid ("e25baa79-1e38-4b80-b6a3-c1da1247d97f")
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.dependencies == null) ? 0 : this.dependencies.hashCode());
        result = prime * result + ((this.metaclasses == null) ? 0 : this.metaclasses.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.provider == null) ? 0 : this.provider.hashCode());
        result = prime * result + ((this.providerVersion == null) ? 0 : this.providerVersion.hashCode());
        result = prime * result + ((this.version == null) ? 0 : this.version.hashCode());
        return result;
    }

    @objid ("2cb106e7-26f9-4d85-9733-af38e1f3b25b")
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
        MetamodelFragmentDescriptor other = (MetamodelFragmentDescriptor) obj;
        if (this.dependencies == null) {
            if (other.dependencies != null) {
                return false;
            }
        } else if (!this.dependencies.equals(other.dependencies)) {
            return false;
        }
        if (this.metaclasses == null) {
            if (other.metaclasses != null) {
                return false;
            }
        } else if (!this.metaclasses.equals(other.metaclasses)) {
            return false;
        }
        if (this.name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!this.name.equals(other.name)) {
            return false;
        }
        if (this.provider == null) {
            if (other.provider != null) {
                return false;
            }
        } else if (!this.provider.equals(other.provider)) {
            return false;
        }
        if (this.providerVersion == null) {
            if (other.providerVersion != null) {
                return false;
            }
        } else if (!this.providerVersion.equals(other.providerVersion)) {
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

    @objid ("353acf50-68fb-43e7-a802-5d07561b765c")
    @Override
    public String toString() {
        return
                        "MetamodelFragmentDescriptor[" +
                        "'" + this.name +
                        "' v" + this.version +
                        ", fake=" + this.fake +
                        ", provider=" + (this.provider != null ? "\"" + this.provider + "\"" : null) +
                        ", providerVersion=" + (this.providerVersion != null ? "\"" + this.providerVersion + "\"" : null) +
                        "]";
    }

}
