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
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.modelio.gproject.gproject.GProject;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.mmextensions.standard.services.MModelServices;
import org.modelio.platform.core.events.ModelioEvent;
import org.modelio.platform.mda.infra.service.IModuleManagementService;
import org.modelio.platform.project.modelshield.ModelShieldController;
import org.modelio.platform.project.plugin.AppProjectCore;
import org.modelio.platform.project.services.openproject.IProjectServiceAccess;

@objid ("cba703c4-d840-4639-9132-8eac008f1e32")
public class ProjectCloser implements IProjectCloser {
    @objid ("267b406f-994c-42f2-9abb-695583438b49")
    private IProjectServiceAccess projectServiceAccess;

    @objid ("9aade60b-0081-47b4-b02a-c78e9d4828a4")
    @Override
    public void closeProject(final GProject projectToClose, final boolean sendSyncEvents) throws IllegalStateException {
        if (this.projectServiceAccess.isOpeningEventSent()) {
            // Plenty event listeners are not prepared to receive a
            // PROJECT_CLOSING if PROJECT_OPENING
            // has never been sent.
            this.projectServiceAccess.postSyncEvent(ModelioEvent.PROJECT_CLOSING, projectToClose);
        }
        
        // Save and close the state preferences once the CLOSING events have been fired and processed by listeners
        this.projectServiceAccess.closeAppStatePreferenceStore();
        
        // FIXME use the current monitor...
        final IModuleManagementService moduleService = this.projectServiceAccess.getEclipseContext().get(IModuleManagementService.class);
        if (moduleService != null) {
            moduleService.stopAllModules(projectToClose);
        }
        
        // End ModelShield
        try {
            ModelShieldController.onProjectClosing(projectToClose);
        } catch (final RuntimeException e) {
            AppProjectCore.LOG.debug(e);
        }
        
        projectToClose.close();
        this.projectServiceAccess.setOpenedProject(null);
        
        // preferences store
        this.projectServiceAccess.setProjectPreferenceStore(null);
        
        // Invalidate the current model services instance before removing it
        // so that if some reference have been kept on it by @&#!%*!&
        // programmers, the error will be detected.
        final IMModelServices s = this.projectServiceAccess.getEclipseContext().get(IMModelServices.class);
        if (s != null) {
            ((MModelServices) s).invalidateProject(null);
            this.projectServiceAccess.getEclipseContext().remove(IMModelServices.class);
        }
        
        if (this.projectServiceAccess.isOpeningEventSent()) {
            if (sendSyncEvents) {
                this.projectServiceAccess.postSyncEvent(ModelioEvent.PROJECT_CLOSED, null);
            } else {
                this.projectServiceAccess.postAsyncEvent(ModelioEvent.PROJECT_CLOSED, null);
            }
        }
        
        this.projectServiceAccess.setOpeningEventSent(false);
    }

    @objid ("0f4795fc-8ecb-462f-8920-5978cba5c5d8")
    @Override
    public final void closeProject(final GProject projectToClose) throws IllegalStateException {
        closeProject(projectToClose, false);
    }

    @objid ("5a52b8de-f2ac-4a80-a4d7-8fd83ad5f3a3")
    @Override
    public void configure(IProjectServiceAccess projectServiceAccess) {
        this.projectServiceAccess = projectServiceAccess;
    }

    @objid ("abe299a3-16ae-4337-875e-6996e5e14986")
    protected final IEclipseContext getContext() {
        return this.projectServiceAccess.getEclipseContext();
    }

}
