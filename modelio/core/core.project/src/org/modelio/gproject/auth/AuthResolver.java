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
package org.modelio.gproject.auth;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.data.project.auth.AuthDescriptor;
import org.modelio.gproject.data.project.auth.InheritedAuthData;
import org.modelio.vbasic.auth.IAuthData;

/**
 * Utility class to get the real authentication data to use for a project fragment or module.
 * 
 * Fragments or module authentication data may be inherited from the project authentication data. in this case resolve(...) methods return the project authentication data.
 */
@objid ("a87c8968-5e12-413d-b08b-04190d448592")
public class AuthResolver {
    @objid ("73aacd8f-8d8a-429e-996e-bdcc9551c948")
    private final IAuthData projectAuth;

    /**
     * initialize the resolver from a project
     * @param project the project
     */
    @objid ("1f3c1fa7-c6bf-4636-afdc-e21f55f5ddf9")
    public  AuthResolver(IAuthData projectAuth) {
        this.projectAuth = projectAuth;
    }

    /**
     * Get the real authentication data to use for the given fragment.
     * @param f the fragment
     * @return the authentication data to use.
     */
    @objid ("3844cfe7-852d-4e5b-9158-63f22656e6b0")
    public IAuthData resolve(AuthDescriptor authDescriptor) {
        if (authDescriptor != null) {
            IAuthData authData = authDescriptor.getData();
            if (authData == null)
                return null;
            else if (InheritedAuthData.matches(authData))
                return this.projectAuth;
            else
                return authData;
        } else {
            return null;
        }
        
    }

}
