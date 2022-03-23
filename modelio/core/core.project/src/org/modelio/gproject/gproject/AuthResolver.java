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
package org.modelio.gproject.gproject;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.data.project.AuthDescriptor;
import org.modelio.gproject.data.project.FragmentDescriptor;
import org.modelio.gproject.data.project.GAuthConf;
import org.modelio.gproject.data.project.InheritedAuthData;
import org.modelio.gproject.data.project.ModuleDescriptor;
import org.modelio.gproject.data.project.ProjectDescriptor;
import org.modelio.gproject.fragment.IProjectFragment;
import org.modelio.gproject.module.GModule;
import org.modelio.vbasic.auth.IAuthData;

/**
 * Utility class to get the real authentication data to use for
 * a project fragment or module.
 * 
 * Fragments or module authentication data may be inherited from the project authentication data.
 * in this case resolve(...) methods return the project authentication data.
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
    public  AuthResolver(GProject project) {
        this.projectAuth = project.getAuthConfiguration().getAuthData();
    }

    /**
     * initialize the resolver from a project descriptor.
     * @param projDesc the project descriptor.
     */
    @objid ("a7913fab-cd75-459a-9732-ddf12b3ee788")
    public  AuthResolver(ProjectDescriptor projDesc) {
        this.projectAuth = projDesc.getAuthDescriptor().getData();
    }

    /**
     * Get the real  authentication data to use for the given fragment.
     * @param f the fragment
     * @return the authentication data to use.
     */
    @objid ("3844cfe7-852d-4e5b-9158-63f22656e6b0")
    public IAuthData resolve(FragmentDescriptor f) {
        final AuthDescriptor authDescriptor = f.getAuthDescriptor();
        if (authDescriptor != null)
            return resolve(authDescriptor.getData());
        else
            return null;
        
    }

    /**
     * Get the real authentication data to use for the given fragment.
     * @param f the fragment
     * @return the authentication data to use.
     */
    @objid ("f60b5054-a4fb-4d96-91c6-268f6ce4c27a")
    public IAuthData resolve(ModuleDescriptor f) {
        final AuthDescriptor authDescriptor = f.getAuthDescriptor();
        if (authDescriptor != null)
            return resolve(authDescriptor.getData());
        else
            return null;
        
    }

    /**
     * Get the real authentication data to use for the given fragment.
     * @param f the fragment
     * @return the authentication data to use.
     */
    @objid ("a7085c8d-e661-483c-85d4-ee902e53cddf")
    public IAuthData resolve(IProjectFragment f) {
        final GAuthConf authDescriptor = f.getAuthConfiguration();
        if (authDescriptor != null)
            return resolve(authDescriptor.getAuthData());
        else
            return null;
        
    }

    /**
     * Get the real  authentication data to use for the given fragment.
     * @param f the fragment
     * @return the authentication data to use.
     */
    @objid ("99ca5e5f-de7e-4889-bb5d-1a8650bf5787")
    public IAuthData resolve(GModule f) {
        final GAuthConf authDescriptor = f.getAuthData();
        if (authDescriptor != null)
            return resolve(authDescriptor.getAuthData());
        else
            return null;
        
    }

    /**
     * Get the real authentication data to use a given authentication data.
     * <p>
     * Returns the given data unless it is an {@link InheritedAuthData}.
     * @param d the authentication data.
     * @return the authentication data to use.
     */
    @objid ("1dd36eeb-006c-43c2-86d2-93f821dde2b3")
    public IAuthData resolve(IAuthData d) {
        if (d == null)
            return null;
        else if (InheritedAuthData.matches(d))
            return this.projectAuth;
        else
            return d;
        
    }

}
