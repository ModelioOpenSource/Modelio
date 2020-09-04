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

package org.modelio.app.project.core.services.openproject;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.app.project.core.plugin.AppProjectCore;
import org.modelio.app.project.core.services.IProjectService;
import org.modelio.gproject.fragment.IProjectFragment;
import org.modelio.gproject.gproject.GProject;
import org.modelio.gproject.gproject.GProjectEvent;
import org.modelio.gproject.gproject.GProjectEventType;
import org.modelio.gproject.gproject.IProjectMonitor;

/**
 * Project monitor used while the project is being open.
 */
@objid ("8e4eb233-ec8b-453e-a4d2-7240f3ce34ca")
final class ProjectOpeningMonitor implements IProjectMonitor {
    @objid ("894d519d-faee-486d-a538-0c95a4d7a32f")
    private final IProjectMonitor cascaded;

    @objid ("ddbae82f-7ad7-43e3-8fdb-928aecd3de12")
    private final IProjectService projectService;

    @objid ("7ff991c3-3f29-4db2-b310-da80de5d0bdc")
    ProjectOpeningMonitor(IProjectService projectService, IProjectMonitor cascaded) {
        this.projectService = projectService;
        this.cascaded = cascaded;
    }

    @objid ("dce14247-dd23-4bb3-adbb-4b9fd9137959")
    @Override
    public void handleProjectEvent(GProjectEvent ev) {
        switch (ev.type) {
        case FRAGMENT_STATE_CHANGED:
        case FRAGMENT_DOWN:
            // defer
            break;
        case WARNING:
            // Log immediately
            if (ev.throwable != null) {
                AppProjectCore.LOG.warning(ev.throwable);
            } else if (ev.message != null) {
                if (ev.fragment != null) {
                    AppProjectCore.LOG.warning("%s : %s", ev.fragment.getId(), ev.message);
                } else {
                    AppProjectCore.LOG.warning(ev.message);
                }
            }
            break;
        default:
            // Log immediately
            if (ev.message != null) {
                AppProjectCore.LOG.info(ev.message);
            }
            if (ev.throwable != null) {
                AppProjectCore.LOG.info(ev.throwable);
            }
            break;
        
        }
    }

    /**
     * Rebuild fragments state change events that were ignored.
     */
    @objid ("47a39b72-faaa-40e0-8587-c07eaeb900b4")
    public void processDefered() {
        final GProject openedProject = this.projectService.getOpenedProject();
        
        for (IProjectFragment f : openedProject.getFragments()) {
            GProjectEvent ev;
            switch (f.getState()) {
            case DOWN:
                ev = new GProjectEvent(GProjectEventType.FRAGMENT_DOWN, null, f, f.getDownError());
                this.cascaded.handleProjectEvent(ev);
                break;
            case UP_FULL:
            case UP_LIGHT:
                ev = new GProjectEvent(GProjectEventType.FRAGMENT_STATE_CHANGED, null, f, f.getDownError());
                this.cascaded.handleProjectEvent(ev);
                //this.modelioEventService.postAsyncEvent(this.projectService, ModelioEvent.FRAGMENT_UP, f);
                break;
            case INITIAL:
            case MOUNTING:
            default:
                break;
        
            }
        }
    }

}
