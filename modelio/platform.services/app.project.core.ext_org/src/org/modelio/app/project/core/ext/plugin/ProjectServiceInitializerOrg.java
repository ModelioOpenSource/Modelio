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

package org.modelio.app.project.core.ext.plugin;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;
import org.modelio.app.core.project.ICurrentProjectService;
import org.modelio.app.project.core.creation.IProjectCreationData;
import org.modelio.app.project.core.creation.IProjectCreator;
import org.modelio.app.project.core.creation.ProjectCreator;
import org.modelio.app.project.core.services.FragmentsMigrator;
import org.modelio.app.project.core.services.IProjectService;
import org.modelio.app.project.core.services.ProjectService;
import org.modelio.app.project.core.services.closeproject.IProjectCloser;
import org.modelio.app.project.core.services.closeproject.ProjectCloser;
import org.modelio.app.project.core.services.createproject.IProjectCreator2;
import org.modelio.app.project.core.services.createproject.IProjectCreatorFactory;
import org.modelio.app.project.core.services.createproject.ProjectCreator2;
import org.modelio.app.project.core.services.openproject.IProjectOpener;
import org.modelio.app.project.core.services.openproject.OpenProjectService;
import org.modelio.app.project.core.services.workspace.IWorkspaceService;
import org.modelio.app.project.core.services.workspace.WorkspaceService;
import org.modelio.gproject.gproject.GProjectConfigurer;

/**
 * E4 model processor to add an {@link IProjectService} to the context.
 */
@objid ("0072e9ee-dcda-103c-9961-001ec947cd2a")
public class ProjectServiceInitializerOrg {
    /**
     * Called by E4.
     * 
     * Create, initialize the IProjectService instance and add it to the context.
     * 
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
