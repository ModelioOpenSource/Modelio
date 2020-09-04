/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.app.project.core.services.openproject;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.preference.IPersistentPreferenceStore;
import org.modelio.app.preferences.GProjectPreferenceStore;
import org.modelio.app.project.core.services.IProjectService;
import org.modelio.gproject.gproject.GProject;

/**
 * Accessors on internals of {@link IProjectService} implementation.
 * 
 * @author cmarin
 * @since 3.5
 */
@objid ("f5df3653-f60b-43c2-a312-db0bb058e3e4")
public interface IProjectServiceAccess {
    @objid ("1d7416bb-38e4-4be8-a971-3e8048b07eb1")
    void setOpeningEventSent(boolean b);

    @objid ("72f7ad18-4247-43ee-9d03-5ae523860c38")
    void setProjectPreferenceStore(GProjectPreferenceStore prefsStore);

    @objid ("7c564460-6399-4c50-88dd-2168de61b9b8")
    IProjectService getProjectService();

    @objid ("6e8f265a-6a0d-4497-8643-693792c89d1c")
    void setOpenedProject(GProject project);

    @objid ("1caaaf15-8a0a-49d1-ad14-bff922d1a6d4")
    void setStatePreferenceStore(IPersistentPreferenceStore stateStore);

}
