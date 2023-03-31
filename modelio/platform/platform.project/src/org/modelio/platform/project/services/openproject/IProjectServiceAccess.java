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
package org.modelio.platform.project.services.openproject;

import java.nio.file.Path;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.modelio.gproject.core.IGProject;
import org.modelio.platform.core.events.ModelioEvent;
import org.modelio.platform.preferences.GProjectPreferenceStore;
import org.modelio.platform.project.services.IProjectService;

/**
 * Accessors on internals of {@link IProjectService} implementation.
 * 
 * 
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
    void setOpenedProject(IGProject project);

    @objid ("bd0f7774-c894-4aae-9bc4-77ffa59d98ca")
    void postSyncEvent(final ModelioEvent topic, final Object data);

    @objid ("718b3d35-2e7b-4005-81cb-ac4bba2aaeae")
    void postAsyncEvent(final ModelioEvent topic, final Object data);

    @objid ("1661b9bb-80ac-4eb6-bad1-4ebaf006fffb")
    IEclipseContext getEclipseContext();

    @objid ("439970dd-b9e0-4d35-868f-b8f0e94e9923")
    void closeAppStatePreferenceStore();

    @objid ("12e5146d-ee31-410c-abb7-05b27ac53933")
    void openAppStatePreferenceStore(IGProject project);

    @objid ("3b528df6-cfb0-4878-b972-eb83660b3d85")
    boolean isOpeningEventSent();

    @objid ("e07718cf-61a3-4eae-9170-b0befd94f93d")
    Path getWorkspace();
}

