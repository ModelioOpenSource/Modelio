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
package org.modelio.platform.project.creation;

import java.io.IOException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.modelio.gproject.env.IGProjectEnv;
import org.modelio.vbasic.progress.IModelioProgress;

/**
 * Project creator contract deals with physically creating projects: ie providing initial data.
 */
@objid ("0083fd9c-7310-10b7-9941-001ec947cd2a")
public interface IProjectCreatorDelegate {
    /**
     * Called by the project service before creating the project.
     * The default implementation does nothing.
     * @since 5.2
     */
    @objid ("50da1cd5-9d8d-4a26-980f-d4b7f1adf46f")
    default void initialize(IEclipseContext iEclipseContext) throws IOException {
        // Do nothing
    }

    /**
     * @param data the project creation data model.
     * @param monitor a progress monitor, can be null
     * @throws IOException in case of I/O error.
     */
    @objid ("00179d0a-7311-10b7-9941-001ec947cd2a")
    void createProject(IProjectCreationData data, IGProjectEnv configuration, IModelioProgress monitor) throws IOException;

    /**
     * Called by the project service after creating the project.
     * The default implementation does nothing.
     * @since 5.2
     */
    @objid ("9ad0b97c-6c86-4bc0-85ef-cdb5739402e8")
    default void terminate(IEclipseContext iEclipseContext) throws IOException {
        // Do nothing
    }
}

