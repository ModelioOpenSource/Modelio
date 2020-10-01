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

package org.modelio.gproject.data.project;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Project fragment descriptor.
 */
@objid ("599d0ffc-985a-11e1-ac83-001ec947ccaf")
public class FragmentDescriptor implements Serializable {
    @objid ("eedc21ef-9a71-11e1-ac83-001ec947ccaf")
    private FragmentType type;

    @objid ("0619d012-3019-11e2-8f81-001ec947ccaf")
    private DefinitionScope scope;

    @objid ("eedc21ed-9a71-11e1-ac83-001ec947ccaf")
    private String id;

    /**
     * Class format version
     */
    @objid ("139bd3dd-9a85-11e1-ac83-001ec947ccaf")
    public static final long serialVersionUID = 1L;

    @objid ("eedc21ee-9a71-11e1-ac83-001ec947ccaf")
    private URI uri;

    @objid ("f47b4b60-aa5a-11e1-8392-001ec947ccaf")
    private GProperties properties = new GProperties();

    @objid ("59ce182b-77c6-4e0f-a08e-5610b4f9ec5c")
    private AuthDescriptor auth;

    /**
     * @return the fragment identifier.
     */
    @objid ("eedc21f0-9a71-11e1-ac83-001ec947ccaf")
    public String getId() {
        return this.id;
    }

    /**
     * @param id the fragment identifier.
     */
    @objid ("eedc21f4-9a71-11e1-ac83-001ec947ccaf")
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * @return the fragment URI.
     */
    @objid ("eedc21f7-9a71-11e1-ac83-001ec947ccaf")
    public URI getUri() {
        return this.uri;
    }

    /**
     * @param uri the fragment URI as a string
     * @throws java.net.URISyntaxException if the string is not a valid URI.
     */
    @objid ("eedc21fb-9a71-11e1-ac83-001ec947ccaf")
    public void setUri(final String uri) throws URISyntaxException {
        if (uri==null || uri.isEmpty())
            this.uri = null;
        else
            this.uri = new URI(uri);
    }

    /**
     * @return the fragment type.
     */
    @objid ("eedc21fe-9a71-11e1-ac83-001ec947ccaf")
    public FragmentType getType() {
        return this.type;
    }

    /**
     * @param type the fragment type.
     */
    @objid ("eedc2202-9a71-11e1-ac83-001ec947ccaf")
    public void setType(final FragmentType type) {
        this.type = type;
    }

    /**
     * @return the fragment properties.
     */
    @objid ("f47b4b64-aa5a-11e1-8392-001ec947ccaf")
    public GProperties getProperties() {
        return this.properties;
    }

    /**
     * Set the fragment properties.
     * 
     * @param properties the fragment properties.
     */
    @objid ("f47b4b68-aa5a-11e1-8392-001ec947ccaf")
    public void setProperties(final GProperties properties) {
        this.properties = properties;
    }

    /**
     * @param uri the fragment URI
     */
    @objid ("49be8d27-ab3f-11e1-8392-001ec947ccaf")
    public void setUri(final URI uri) {
        this.uri = uri;
    }

    @objid ("e616f779-d02c-11e1-a8eb-001ec947ccaf")
    @Override
    public String toString() {
        return this.id + " " + (this.type==null? "<no type>" : this.type) + " @" + this.uri;
    }

    /**
     * Copy constructor
     * 
     * @param fd the descriptor to copy.
     */
    @objid ("6b4a75e3-115c-11e2-8a4f-001ec947ccaf")
    public FragmentDescriptor(FragmentDescriptor fd) {
        this.id =fd.id;
        this.uri = fd.uri;
        this.type = fd.type;
        this.properties = new GProperties(fd.properties);
        this.scope = fd.scope;
        if (fd.auth != null)
            this.auth = new AuthDescriptor(fd.auth.getData(), fd.auth.getScope());
    }

    /**
     * Create an empty fragment.
     */
    @objid ("6b4a75e7-115c-11e2-8a4f-001ec947ccaf")
    public FragmentDescriptor() {
        // nothing
    }

    /**
     * Get the definition scope.
     * <p>
     * The fragment may be defined locally or on a server.
     * 
     * @return the definition scope.
     */
    @objid ("630dc62c-3004-11e2-8f81-001ec947ccaf")
    public DefinitionScope getScope() {
        return this.scope;
    }

    /**
     * Set the definition scope.
     * 
     * @param scope the definition scope.
     */
    @objid ("630dc631-3004-11e2-8f81-001ec947ccaf")
    public void setScope(DefinitionScope scope) {
        this.scope = scope;
    }

    /**
     * Tells whether this descriptor is a complete fragment descriptor or only
     * a reference to a fragment with local level properties.
     * 
     * @return <code>true</code> if the descriptor is complete enough to instantiate a fragment.
     */
    @objid ("0460ce59-3019-11e2-8f81-001ec947ccaf")
    public boolean isValid() {
        return this.id != null && ! this.id.isEmpty() &&
                this.scope != null &&
                this.type != null;
    }

    @objid ("e8877ef3-806d-43fc-ac89-c8b0e1ab71da")
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.properties == null) ? 0 : this.properties.hashCode());
        result = prime * result + ((this.scope == null) ? 0 : this.scope.hashCode());
        result = prime * result + ((this.type == null) ? 0 : this.type.hashCode());
        result = prime * result + ((this.uri == null) ? 0 : this.uri.hashCode());
        result = prime * result + ((this.auth == null) ? 0 : this.auth.hashCode());
        return result;
    }

    @objid ("35624057-6ed9-4da7-b30c-97c3cd11b9ac")
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        
        FragmentDescriptor other = (FragmentDescriptor) obj;
        
        if (this.id == null) {
            if (other.id != null)
                return false;
        } else if (!this.id.equals(other.id))
            return false;
        
        if (this.properties == null) {
            if (other.properties != null)
                return false;
        } else if (!this.properties.equals(other.properties))
            return false;
        
        if (this.scope != other.scope)
            return false;
        
        if (this.type != other.type)
            return false;
        
        if (this.uri == null) {
            if (other.uri != null)
                return false;
        } else if (!this.uri.equals(other.uri))
            return false;
        
        if (this.auth == null) {
            if (other.auth != null)
                return false;
        } else if (!this.auth.equals(other.auth))
            return false;
        return true;
    }

    /**
     * Get the authentication data descriptor.
     * 
     * @return the authentication data descriptor.
     */
    @objid ("00238e3d-503a-4efa-b1e0-f5349c670cc4")
    public AuthDescriptor getAuthDescriptor() {
        return this.auth;
    }

    /**
     * set the authentication descriptor.
     * 
     * @param auth the authentication descriptor
     */
    @objid ("75dd71ac-5ac1-4463-920e-9371a68a63ba")
    public void setAuthDescriptor(AuthDescriptor auth) {
        this.auth = auth;
    }

}
