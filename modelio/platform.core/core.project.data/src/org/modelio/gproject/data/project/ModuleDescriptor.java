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

package org.modelio.gproject.data.project;

import java.io.Serializable;
import java.net.URI;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.version.Version;

/**
 * Module descriptor.
 */
@objid ("84d1655c-f34b-11e1-9173-001ec947ccaf")
public final class ModuleDescriptor implements Serializable {
    @objid ("0620f6df-3019-11e2-8f81-001ec947ccaf")
    private DefinitionScope scope;

    @objid ("84d1657b-f34b-11e1-9173-001ec947ccaf")
    private String name;

    /**
     * Class format version
     */
    @objid ("84d1657d-f34b-11e1-9173-001ec947ccaf")
    public static final long serialVersionUID = 1L;

    /**
     * A module is activated unless specified.
     */
    @objid ("cbb1cede-6ed8-4337-afa6-012d20dc8f61")
    private boolean activated = true;

    @objid ("84d16577-f34b-11e1-9173-001ec947ccaf")
    private GProperties parameters = new GProperties();

    @objid ("f87f027d-f369-11e1-9173-001ec947ccaf")
    private Version version;

    @objid ("6824523d-0d5c-11e2-9d8a-001ec947ccaf")
    private URI archiveLocation;

    @objid ("4aa23f14-50a6-4219-b1f5-83da597c00fa")
    private AuthDescriptor auth;

    /**
     * @return the module name.
     */
    @objid ("84d16551-f34b-11e1-9173-001ec947ccaf")
    public String getName() {
        return this.name;
    }

    /**
     * @param name the module name.
     */
    @objid ("84d16552-f34b-11e1-9173-001ec947ccaf")
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @return the module parameters.
     */
    @objid ("84d16557-f34b-11e1-9173-001ec947ccaf")
    public GProperties getParameters() {
        return this.parameters;
    }

    /**
     * @param gProperties the module parameters.
     */
    @objid ("84d16558-f34b-11e1-9173-001ec947ccaf")
    public void setParameters(final GProperties gProperties) {
        this.parameters = gProperties;
    }

    @objid ("84d1655a-f34b-11e1-9173-001ec947ccaf")
    @Override
    public String toString() {
        return this.name + " V" + this.version + " @" + this.archiveLocation;
    }

    /**
     * @return the module version.
     */
    @objid ("f87f0284-f369-11e1-9173-001ec947ccaf")
    public Version getVersion() {
        return this.version;
    }

    /**
     * @param version the module version.
     */
    @objid ("f87f0288-f369-11e1-9173-001ec947ccaf")
    public void setVersion(Version version) {
        this.version = version;
    }

    /**
     * @return the archive location
     */
    @objid ("f87f028b-f369-11e1-9173-001ec947ccaf")
    public URI getArchiveLocation() {
        return this.archiveLocation;
    }

    /**
     * @param uri the archive location
     */
    @objid ("f87f028f-f369-11e1-9173-001ec947ccaf")
    public void setArchiveLocation(URI uri) {
        this.archiveLocation = uri;
    }

    /**
     * Copy descriptor.
     * 
     * @param md the descriptor to copy.
     */
    @objid ("6b3e8a2e-115c-11e2-8a4f-001ec947ccaf")
    public ModuleDescriptor(ModuleDescriptor md) {
        this.name = md.name;
        this.version = md.version;
        this.archiveLocation = md.archiveLocation;
        this.parameters = new GProperties(md.getParameters());
        this.scope = md.scope;
        this.activated = md.activated;
        
        if (md.auth != null) {
            this.auth = new AuthDescriptor(md.auth.getData(), md.auth.getScope());
        }
        else {
            this.auth = new AuthDescriptor(null, DefinitionScope.LOCAL); // should not happen
        }
    }

    /**
     * Package private default constructor.
     */
    @objid ("6b3e8a32-115c-11e2-8a4f-001ec947ccaf")
    public ModuleDescriptor() {
        // nothing
    }

    /**
     * Get the definition scope.
     * <p>
     * The module may be defined locally or on a server.
     * 
     * @return the definition scope.
     */
    @objid ("6389bb8d-3004-11e2-8f81-001ec947ccaf")
    public DefinitionScope getScope() {
        return this.scope;
    }

    /**
     * Set the definition scope.
     * 
     * @param scope the definition scope.
     */
    @objid ("6389bb92-3004-11e2-8f81-001ec947ccaf")
    public void setScope(DefinitionScope scope) {
        this.scope = scope;
    }

    /**
     * @return <code>true</code> if the descriptor is complete enough to instantiate a fragment.
     */
    @objid ("046a5760-3019-11e2-8f81-001ec947ccaf")
    public boolean isValid() {
        return this.name != null && ! this.name.isEmpty() &&
                        this.scope != null &&
                        this.version != null ;
    }

    @objid ("33ed38cc-e3d8-4963-96c8-7f3a2e6878cb")
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.archiveLocation == null) ? 0 : this.archiveLocation.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.parameters == null) ? 0 : this.parameters.hashCode());
        result = prime * result + ((this.scope == null) ? 0 : this.scope.hashCode());
        result = prime * result + ((this.version == null) ? 0 : this.version.hashCode());
        result = prime * result + ((this.auth == null) ? 0 : this.auth.hashCode());
        return result;
    }

    @objid ("0e137450-7710-4568-bf08-9b59ab494b73")
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
        
        ModuleDescriptor other = (ModuleDescriptor) obj;
        
        if (this.archiveLocation == null) {
            if (other.archiveLocation != null) {
                return false;
            }
        } else if (!this.archiveLocation.equals(other.archiveLocation)) {
            return false;
        }
        
        if (this.name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!this.name.equals(other.name)) {
            return false;
        }
        
        if (this.parameters == null) {
            if (other.parameters != null) {
                return false;
            }
        } else if (!this.parameters.equals(other.parameters)) {
            return false;
        }
        
        if (this.scope != other.scope) {
            return false;
        }
        
        if (this.version == null) {
            if (other.version != null) {
                return false;
            }
        } else if (!this.version.equals(other.version)) {
            return false;
        }
        
        if (this.auth == null) {
            if (other.auth != null) {
                return false;
            }
        } else if (!this.auth.equals(other.auth)) {
            return false;
        }
        return true;
    }

    /**
     * Get the authentication data descriptor.
     * <p>
     * Never return <i><code>null</code></i> .
     * 
     * @return the authentication data descriptor.
     */
    @objid ("09d6655e-b593-4e55-9bd0-031c4c5e2905")
    public AuthDescriptor getAuthDescriptor() {
        return this.auth;
    }

    /**
     * Set the authentication descriptor.
     * 
     * @param auth the authentication descriptor
     */
    @objid ("c48a676c-e88f-4de6-bf15-a365fdd4d4e1")
    public void setAuthDescriptor(AuthDescriptor auth) {
        this.auth = auth;
    }

    /**
     * @return <i>true</i> if the module is activated else <i>false</i>.
     */
    @objid ("0cdac1ad-f4bf-426f-9bbf-7c35459c78dd")
    public boolean isActivated() {
        return this.activated;
    }

    /**
     * Set whether the module is activated.
     * 
     * @param activated whether the module is activated.
     */
    @objid ("e67e65e5-c4f4-4237-b973-670b49e9a33f")
    public void setActivated(boolean activated) {
        this.activated = activated;
    }

}
