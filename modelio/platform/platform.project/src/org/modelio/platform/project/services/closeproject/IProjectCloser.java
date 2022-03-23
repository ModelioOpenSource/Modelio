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
package org.modelio.platform.project.services.closeproject;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.gproject.GProject;
import org.modelio.platform.core.events.ModelioEvent;
import org.modelio.platform.project.services.openproject.IProjectServiceAccess;

@objid ("5fbbd26b-d143-4e8e-9dc5-94772554752b")
public interface IProjectCloser {
    /**
     * Closes the project currently opened in the application.
     * @param project The project to close. Must be equal to the currently opened project.
     * @param sendSyncEvents whether the {@link ModelioEvent#PROJECT_CLOSED} should be sent synchronously or asynchronously.
     * @throws IllegalArgumentException If <code>project</code> is null or different from the currently opened project.
     * @throws IllegalStateException If no project is currently opened.
     */
    @objid ("0083087e-acc2-103b-a520-001ec947cd2a")
    void closeProject(final GProject project, boolean sendSyncEvents) throws IllegalArgumentException, IllegalStateException;

    /**
     * Calls {@link #closeProject(GProject, false)}
     * @param project The project to close. Must be equal to the currently opened project.
     * @throws IllegalArgumentException If <code>project</code> is null or different from the currently opened project.
     * @throws IllegalStateException If no project is currently opened.
     */
    @objid ("7159706d-b1e2-4a5b-ab06-9bad395e0fbc")
    void closeProject(final GProject project) throws IllegalArgumentException, IllegalStateException;

    @objid ("ffe83a87-147d-4b50-8787-85b0bf41cd12")
    void configure(IProjectServiceAccess service);

}
