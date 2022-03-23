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
package org.modelio.platform.core.project;

import java.nio.file.Path;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.preference.IPreferenceStore;
import org.modelio.gproject.gproject.GProject;
import org.modelio.platform.core.IModelioService;
import org.modelio.vcore.session.api.ICoreSession;

/**
 * Modelio application scoped services dealing with the currently opened project
 * <p>
 * <code> ICurrentProjectService</code> has a singleton instance available for injection.
 * @author cma
 * @since Valkyrie 3.8 : extracted from 'IProjectService' in 'app.project.core' to avoid useless plugin dependencies
 * and unwanted access to dangerous methods.
 */
@objid ("69208a29-b6c6-4449-a8b7-b0b3187e591b")
public interface ICurrentProjectService extends IModelioService {
    /**
     * Gets the currently opened project.
     * @return the currently opened project or null if none.
     */
    @objid ("00832174-acc2-103b-a520-001ec947cd2a")
    GProject getOpenedProject();

    /**
     * @param nodeId a preference node identifier.
     * @return the project preference store for the node.
     */
    @objid ("60dd0f06-fe9e-4698-9611-18477c247b19")
    IPreferenceStore getProjectPreferences(String nodeId);

    /**
     * @return the modeling session.
     */
    @objid ("0054641a-bb2f-103c-a520-001ec947cd2a")
    ICoreSession getSession();

    /**
     * The application state preference store can be used to store the application state on project closing and restore the state at project opening. Application state refers to any information about the application state: opened views, view configuration
     * options, current selection ... Do not mix this concept with project preferences.
     * @return the application state preference store.
     */
    @objid ("2f97cf67-46e6-4f46-a18e-903269a31ff2")
    IPreferenceStore getStatePreferences();

    /**
     * Gets the current workspace path.
     * @return the current workspace path or null if none.
     */
    @objid ("008329e4-acc2-103b-a520-001ec947cd2a")
    Path getWorkspace();

    /**
     * Tells whether the session or the project preferences needs to be saved.
     * @return <code>true</code> if the session or the project preferences needs to be saved, <code>false</code> otherwise.
     */
    @objid ("38cdfb9d-8aaf-4b8a-b00c-55319d7ec41c")
    boolean isDirty();

}
