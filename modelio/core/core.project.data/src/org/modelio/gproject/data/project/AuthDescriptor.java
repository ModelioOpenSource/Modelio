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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.auth.AuthData;
import org.modelio.vbasic.auth.IAuthData;
import org.modelio.vbasic.auth.NoneAuthData;

/**
 * Descriptor to serialize an {@link AuthData}.
 */
@objid ("129b5661-18c5-413e-817c-7b3b899c3402")
public class AuthDescriptor {
    /**
     * Definition scope, should never be <code>null</code>.
     */
    @objid ("6eb0e7e5-7bad-4717-bd1b-5c80a12418ac")
    private DefinitionScope scope;

    /**
     * To be used as authentication scheme to tell authentication must be asked to the user.
     */
    @objid ("bce96002-7ab6-4650-8e94-7854214495a9")
    public static final String AUTH_TYPE_ASK = "AUTH_TYPE_ASK";

    /**
     * Authentication data either computed from own datas or
     * given to the constructor.
     * <p>
     * This is a dirty hack that allows us to use descriptors
     * that keep
     */
    @objid ("3643bc08-d0dd-43a6-b7b8-85eb16d53b50")
    private IAuthData data;

    /**
     * initialize an empty descriptor.
     */
    @objid ("0ecb04fd-9323-4185-b529-906177335efd")
    public  AuthDescriptor() {
        this.scope = DefinitionScope.LOCAL;
    }

    /**
     * Serialize an authentication data in a new descriptor.
     * <p>
     * Only data given by {@link IAuthData#serialize()} are kept when writing this descriptor.
     * <p>
     * The given authentication data is retained with all its data and
     * will be returned by {@link #getData()}.
     * @param authData the data to serialize.
     * @param scope definition scope
     */
    @objid ("30873c26-3b9a-4b01-9baa-4a2dc716a686")
    public  AuthDescriptor(IAuthData authData, DefinitionScope scope) {
        this.data = authData;
        this.scope = scope;
        
    }

    /**
     * Get the authentication data.
     * @return the data
     */
    @objid ("7eec202e-f3e9-47ec-bd08-b8a1b1644d69")
    public IAuthData getData() {
        return this.data;
    }

    /**
     * @return the definition scope
     */
    @objid ("eb9bdafb-c718-4010-9e55-78328f04cbb8")
    public DefinitionScope getScope() {
        return this.scope;
    }

    /**
     * Get the authentication scheme.
     * <p>
     * Returns <code>null</code> if no authentication is defined.
     * @return the authentication type.
     */
    @objid ("b4709ef9-4fdc-4951-9aed-7675d5abf270")
    public String getScheme() {
        return this.data == null ? null : this.data.getSchemeId();
    }

    /**
     * @param scope the definition scope
     */
    @objid ("27a931e6-fce1-48ef-8fb8-702e28c72960")
    public void setScope(DefinitionScope scope) {
        this.scope = scope;
    }

    @objid ("cde06a65-345f-41e5-a7a2-58c722c8e1b1")
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.data == null) ? 0 : this.data.hashCode());
        result = prime * result + ((this.scope == null) ? 0 : this.scope.hashCode());
        return result;
    }

    @objid ("a67c7a39-1266-4eb3-a425-7c3104a163a8")
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        
        AuthDescriptor other = (AuthDescriptor) obj;
        if (this.data == null) {
            if (other.data != null)
                return false;
        } else if (!this.data.equals(other.data))
            return false;
        
        if (this.scope != other.scope)
            return false;
        return true;
    }

    /**
     * Set the authentication properties.
     * @param newData the authentication properties.
     */
    @objid ("d7dcaabc-7738-4afa-ad3c-236ad46aab0c")
    public void setData(IAuthData newData) {
        this.data = newData;
    }

    @objid ("95142bf3-3f5d-4410-9dbd-d4b65a82fa6d")
    @Override
    public String toString() {
        if (isDefined())
            return "auth: "+ this.data.getSchemeId()+ " ("+this.scope+")= "+ this.data;
        else
            return "auth: <undefined>";
        
    }

    /**
     * Tells whether authentication is specified.
     * <p>
     * The authentication is specified if a scheme identifier is set, event to {@link NoneAuthData}
     * @return true if there is
     */
    @objid ("0a0957d7-aa24-4a3d-9952-4986f26576a8")
    public boolean isDefined() {
        return getScheme() != null && !getScheme().isEmpty();
    }

}
