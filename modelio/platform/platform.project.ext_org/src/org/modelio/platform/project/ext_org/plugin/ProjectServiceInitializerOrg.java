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
package org.modelio.platform.project.ext_org.plugin;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;
import org.modelio.gproject.gproject.GProjectConfigurer;
import org.modelio.platform.core.project.ICurrentProjectService;
import org.modelio.platform.project.creation.IProjectCreationData;
import org.modelio.platform.project.creation.IProjectCreator;
import org.modelio.platform.project.creation.ProjectCreator;
import org.modelio.platform.project.services.FragmentsMigrator;
import org.modelio.platform.project.services.IProjectService;
import org.modelio.platform.project.services.ProjectService;
import org.modelio.platform.project.services.closeproject.IProjectCloser;
import org.modelio.platform.project.services.closeproject.ProjectCloser;
import org.modelio.platform.project.services.createproject.IProjectCreator2;
import org.modelio.platform.project.services.createproject.IProjectCreatorFactory;
import org.modelio.platform.project.services.createproject.ProjectCreator2;
import org.modelio.platform.project.services.openproject.IProjectOpener;
import org.modelio.platform.project.services.openproject.OpenProjectService;
import org.modelio.platform.project.services.workspace.IWorkspaceService;
import org.modelio.platform.project.services.workspace.WorkspaceService;

/**
 * E4 model processor to add an {@link IProjectService} to the context.
 */
@objid ("0072e9ee-dcda-103c-9961-001ec947cd2a")
public class ProjectServiceInitializerOrg {
    /**
     * Called by E4.
     * 
     * Create, initialize the IProjectService instance and add it to the context.
     * @param context the Eclipse context
     */
    @objid ("00022664-dcdb-103c-9961-001ec947cd2a")
    @Execute
    public static void initialize(IEclipseContext context) {
        IProjectCreatorFactory creatorFactory = new IProjectCreatorFactory() {
            @Override
            public IProjectCreator getProjectCreator(IProjectCreationData data) {
                return new ProjectCreator();
            }
        };
        
        IProjectCreator2 projectCreator = new ProjectCreator2(creatorFactory);
        GProjectConfigurer synchronizer = new GProjectConfigurer();
        IProjectOpener projectOpener = new OpenProjectService(synchronizer);
        IProjectCloser projectCloser = new ProjectCloser();
        IWorkspaceService workspaceService = new WorkspaceService();
        
        ProjectService projectService = new ProjectService(
                context,
                projectCreator,
                projectOpener,
                synchronizer,
                projectCloser,
                workspaceService,
                (eclipseContext, project, withConfirmation) -> new FragmentsMigrator(eclipseContext, project, withConfirmation));
        context.set(IProjectService.class, projectService);
        context.set(ICurrentProjectService.class, projectService);
        
    }

}
