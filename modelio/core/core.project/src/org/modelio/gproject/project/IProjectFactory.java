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
package org.modelio.gproject.project;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.core.IGProject;
import org.modelio.gproject.data.project.GProjectDescriptor;

/**
 * Interface that all project factories must implement.
 */
@objid ("953ae04e-0e30-11e2-8e4b-001ec947ccaf")
public interface IProjectFactory {
    /**
     * Instantiate a {@link IGProject} from a descriptor.
     * <p>
     * The implementation should just create an instance of IGProject and return.
     * @param projectDescriptor a project descriptor
     * @return the built IGProject.
     * @throws IllegalArgumentException if the descriptor is not supported.
     */
    @objid ("a09786ea-0e30-11e2-8e4b-001ec947ccaf")
    IGProject createProject(final GProjectDescriptor projectDescriptor) throws IllegalArgumentException;

    /**
     * Tells whether the given project descriptor is supported.
     * @param projectDescriptor a project descriptor
     * @return <code>true</code> if the factory can create a project with it, else <code>false</code>.
     */
    @objid ("da6973ea-0e3b-11e2-8e4b-001ec947ccaf")
    boolean supports(GProjectDescriptor projectDescriptor);
}

