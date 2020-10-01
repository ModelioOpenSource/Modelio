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

import java.net.URI;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.data.project.AuthDescriptor;
import org.modelio.gproject.data.project.DefinitionScope;
import org.modelio.gproject.data.project.FragmentDescriptor;
import org.modelio.gproject.data.project.GAuthConf;
import org.modelio.gproject.data.project.GProperties;
import org.modelio.gproject.data.project.ModuleDescriptor;
import org.modelio.gproject.data.project.ProjectDescriptor;
import org.modelio.gproject.data.project.ResourceDescriptor;
import org.modelio.gproject.fragment.IProjectFragment;
import org.modelio.gproject.module.GModule;
import org.modelio.vbasic.auth.NoneAuthData;
import org.modelio.vbasic.version.Version;
import org.modelio.version.ModelioVersion;

/**
 * Serialize a {@link GProject}, a {@link IProjectFragment} or a project
 * descriptor in a XML file.
 */
@objid ("d24b1ab1-99bb-11e1-ac83-001ec947ccaf")
public class ProjectWriter {
    @objid ("99dbb2d6-a711-40d0-b776-aba72903b0a1")
    private GProject project;

    /**
     * Instantiate the writer.
     * 
     * @param aProject the related project.
     */
    @objid ("eed3ae02-e758-4d5c-98e4-d68425e669c8")
    public ProjectWriter(final GProject aProject) {
        this.project = aProject;
    }

    /**
     * Instantiate the writer.
     * @deprecated Use {@link #ProjectWriter(GProject)} instead
     */
    @objid ("60702d4e-101b-4823-94fd-a15a1b669f87")
    @Deprecated
    public ProjectWriter() {
        super();
    }

    /**
     * Get the descriptor of a fragment.
     * 
     * @param projectFragment a project fragment
     * @return its descriptor
     */
    @objid ("eed9bfb9-9a71-11e1-ac83-001ec947ccaf")
    public FragmentDescriptor writeFragmentDescriptor(final IProjectFragment projectFragment) {
        FragmentDescriptor fd = new FragmentDescriptor();
        
        fd.setId(projectFragment.getId());
        fd.setType(projectFragment.getType());
        
        URI uri = getRelativeUri(projectFragment.getUri());
        fd.setUri(uri);
        fd.setScope(projectFragment.getScope());
        
        fd.setProperties(new GProperties(projectFragment.getProperties()));
        fd.setAuthDescriptor(writeAuth(projectFragment.getAuthConfiguration()));
        return fd;
    }

    /**
     * Get the descriptor of a module.
     * 
     * @param m a module
     * @return its descriptor.
     */
    @objid ("aa89539b-ec75-11e1-912e-001ec947ccaf")
    public ModuleDescriptor writeModuleDescriptor(final GModule m) {
        ModuleDescriptor d = new ModuleDescriptor();
        d.setName(m.getName());
        d.setScope(m.getScope());
        d.setActivated(m.isActivated());
        
        d.setVersion(m.getVersion());
        if (m.getOriginalArchiveUri() != null) {
            d.setArchiveLocation(getRelativeUri(m.getOriginalArchiveUri()));
        }
        
        d.setParameters(new GProperties(m.getParameters()));
        d.setAuthDescriptor(writeAuth(m.getAuthData()));
        return d;
    }

    /**
     * Write the project configuration to a descriptor.
     * 
     * @return the descriptor of the project.
     */
    @objid ("eed9bfb6-9a71-11e1-ac83-001ec947ccaf")
    public ProjectDescriptor writeProject() {
        if (this.project == null) {
            throw new IllegalStateException();
        }
        
        // Get expected Modelio version to write.
        Version modelioVersion = this.project.getExpectedModelioVersion();
        if (modelioVersion == null) {
            modelioVersion = ModelioVersion.VERSION;
        }
        
        ProjectDescriptor out = new ProjectDescriptor();
        out.setName(this.project.getName());
        out.setPath(this.project.getProjectFileStructure().getProjectPath());
        out.setType(this.project.getType().name());
        out.setModelioVersion(modelioVersion);
        
        if (this.project.getRemoteLocation() != null) {
            out.setRemoteLocation(this.project.getRemoteLocation().toString());
        }
        
        // Write properties
        
        out.setProperties(new GProperties(this.project.getProperties()));
        out.setTodo(this.project.getTodo());
        
        out.setAuthDescriptor(writeAuth(this.project.getAuthConfiguration()));
        
        // Write project fragments
        for (IProjectFragment fragment : this.project.getOwnFragments()) {
            out.getFragments().add(writeFragmentDescriptor(fragment));
        }
        
        // Write modules
        for (GModule m : this.project.getModules()) {
            out.getModules().add(writeModuleDescriptor(m));
        }
        
        // Write shared resources
        for (ResourceDescriptor r : this.project.getSharedResources()) {
            ResourceDescriptor rd = new ResourceDescriptor();
            rd.setId(r.getId());
            rd.setTargetLocation(r.getTargetLocation());
            rd.setTimestamp(r.getTimestamp());
        
            out.getSharedResources().add(rd);
        }
        return out;
    }

    @objid ("b46c8a50-0baa-11e2-bed6-001ec947ccaf")
    private URI getRelativeUri(URI uri) {
        if (uri == null) {
            return null;
        } else {
            return this.project.getProjectFileStructure().getProjectPath().toUri().relativize(uri);
        }
    }

    @objid ("2edd7fb7-8fbf-417d-9063-d46f55c20ab8")
    private AuthDescriptor writeAuth(GAuthConf auth) {
        if (auth == null) {
            return new AuthDescriptor(new NoneAuthData(), DefinitionScope.LOCAL);
        } else {
            return new AuthDescriptor(auth.getAuthData(), auth.getScope());
        }
    }

}
