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
package org.modelio.platform.project.services.createproject;

import java.io.IOException;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IProgressMonitor;
import org.modelio.gproject.gproject.GProjectEnvironment;
import org.modelio.gproject.gproject.IGProjectEnv;
import org.modelio.gproject.module.IModuleRTCache;
import org.modelio.platform.core.ModelioEnv;
import org.modelio.platform.core.events.ModelioEvent;
import org.modelio.platform.project.creation.IProjectCreationData;
import org.modelio.platform.project.creation.IProjectCreator;
import org.modelio.platform.project.services.openproject.IProjectServiceAccess;
import org.modelio.platform.ui.progress.ModelioProgressAdapter;
import org.modelio.vbasic.progress.IModelioProgress;

@objid ("af0ed4ca-1bdc-4c6e-b8b2-c4f690b243f6")
public class ProjectCreator2 implements IProjectCreator2 {
    @objid ("888418b0-0b80-4276-aec4-2337d4becb7c")
    private final IProjectCreatorFactory projectCreatorFactory;

    @objid ("3c5b555f-eaad-4ce2-b2c5-d4827ba9e19a")
    private IProjectServiceAccess projectServiceAccess;

    @objid ("4d8d209f-a569-4c32-bee5-54728e4a7bd2")
    @Override
    public void createProject(final IProjectCreator projectCreator, final IProjectCreationData data, final IProgressMonitor monitor) throws IOException {
        Objects.requireNonNull(data);
        if (projectCreator == null) {
            createProject(data, monitor);
        } else {
            doCreateProject(projectCreator, data, monitor);
        }
        
    }

    @objid ("d1563f4d-8dea-4e0b-968a-da2e964f6770")
    @Override
    public void createProject(final IProjectCreationData data, final IProgressMonitor monitor) throws IOException {
        Objects.requireNonNull(data);
        doCreateProject(this.projectCreatorFactory.getProjectCreator(data), data, monitor);
        
    }

    @objid ("8f8c398f-4b25-43c5-8005-76b0eba53c85")
    private void doCreateProject(final IProjectCreator projectCreator, final IProjectCreationData data, final IProgressMonitor monitor) throws IOException {
        Objects.requireNonNull(projectCreator);
        
        final IModelioProgress progress = monitor == null ? null : new ModelioProgressAdapter(monitor);
        projectCreator.createProject(data, getProjectFactoryConfiguration(), progress);
        this.projectServiceAccess.postAsyncEvent(ModelioEvent.WORKSPACE_CONTENTS, this.projectServiceAccess.getWorkspace());
        
    }

    @objid ("267289b1-6d1a-4059-bb11-5121861c787a")
    public  ProjectCreator2(IProjectCreatorFactory projectCreatorFactory) {
        this.projectCreatorFactory = projectCreatorFactory;
    }

    @objid ("806d8c19-00fa-4de0-8d44-08c18843bbc9")
    private IGProjectEnv getProjectFactoryConfiguration() {
        final ModelioEnv env = this.projectServiceAccess.getEclipseContext().get(ModelioEnv.class);
        return new GProjectEnvironment().addMetamodelExtensions(env.getActiveMetamodelExtensions())
                .setModulesCache(this.projectServiceAccess.getEclipseContext().get(IModuleRTCache.class))
                .setRamcCache(env.getRamcCachePath());
        
    }

    @objid ("f8a9bc2f-ad71-456c-a67d-8661e693f200")
    @Override
    public void configure(IProjectServiceAccess projectServiceAccess) {
        this.projectServiceAccess = projectServiceAccess;
    }

}
