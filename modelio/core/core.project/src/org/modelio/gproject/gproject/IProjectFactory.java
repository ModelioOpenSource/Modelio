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

import java.io.IOException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.data.project.ProjectDescriptor;
import org.modelio.vbasic.auth.IAuthData;
import org.modelio.vbasic.progress.IModelioProgress;

/**
 * Interface that all project factories must implement.
 */
@objid ("953ae04e-0e30-11e2-8e4b-001ec947ccaf")
public interface IProjectFactory {
    /**
     * Instantiate a {@link GProject} from a descriptor.
     * <p>
     * The implementation should just create an instance of GProject and return.
     * 
     * @param projectDescriptor a project descriptor
     * @return the built GProject.
     * @throws java.lang.IllegalArgumentException if the descriptor is not supported.
     */
    @objid ("a09786ea-0e30-11e2-8e4b-001ec947ccaf")
    GProject createProject(final ProjectDescriptor projectDescriptor) throws IllegalArgumentException;

    /**
     * Tells whether the given project descriptor is supported.
     * 
     * @param projectDescriptor a project descriptor
     * @return <code>true</code> if the factory can create a project with it, else <code>false</code>.
     */
    @objid ("da6973ea-0e3b-11e2-8e4b-001ec947ccaf")
    boolean supports(ProjectDescriptor projectDescriptor);

    /**
     * Get the remote project descriptor.
     * <p>
     * Should return <code>null</code> for local projects.
     * 
     * @param projectDescriptor the local descriptor of the project.
     * @param authData authentication data.
     * @param monitor the progress monitor to use for reporting progress to the user. It is the caller's responsibility to call
     * <code>done()</code> on the given monitor. Accepts <code>null</code>, indicating that no progress should be
     * reported and that the operation cannot be cancelled.
     * @return the remote project descriptor
     * @throws java.io.IOException in case of failure.
     * @throws org.modelio.gproject.gproject.GProjectAuthenticationException in case of authentication failure.
     */
    @objid ("233b8efa-43c8-4b50-9d02-26935dae97d4")
    ProjectDescriptor getRemoteDescriptor(ProjectDescriptor projectDescriptor, IAuthData authData, IModelioProgress monitor) throws IOException, GProjectAuthenticationException;

}
