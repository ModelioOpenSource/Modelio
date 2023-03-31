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
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IProgressMonitor;
import org.modelio.platform.project.creation.IProjectCreationData;
import org.modelio.platform.project.creation.IProjectCreatorDelegate;
import org.modelio.platform.project.services.IProjectService;
import org.modelio.platform.project.services.openproject.IProjectServiceAccess;

/**
 * Interface for the service that creates a project.
 */
@objid ("5d20e41f-3e05-41aa-9c6c-5a2d95d6074d")
public interface IProjectCreator {
    /**
     * Creates a new project in the current workspace. The nature and the properties of the project to create are passed in the <code>dataModel</code> argument.
     * @param projectCreator the delegated project creator, typically null. If null a project creator will be guessed from 'data' and used to create the project.
     * @param data the nature, characteristics and properties of the project to create.
     * @param monitor a progress monitor, null accepted.
     * @throws IOException in case of failure
     */
    @objid ("8e2f8b3f-a57d-4898-b25d-1ad77925152d")
    void createProject(IProjectCreatorDelegate projectCreator, IProjectCreationData data, IProgressMonitor monitor) throws IOException;

    /**
     * Equivalent to calling {@link IProjectService#createProject(IProjectCreator, IProjectCreationData, IProgressMonitor)} with projectCreator being null
     * @param data the nature, characteristics and properties of the project to create. This will determine wich project creator to use.
     * @param monitor a progress monitor, null accepted.
     */
    @objid ("f2253cd7-31ec-421e-bbcc-2b51d1d5e5c1")
    void createProject(IProjectCreationData data, IProgressMonitor monitor) throws IOException;

    @objid ("708c7f99-24db-4439-b328-54a2163a2efe")
    void configure(IProjectServiceAccess service);
}

