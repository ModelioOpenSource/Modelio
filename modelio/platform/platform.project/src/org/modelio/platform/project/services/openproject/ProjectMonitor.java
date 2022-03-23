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

import java.nio.file.FileSystemException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.e4.core.services.statusreporter.StatusReporter;
import org.eclipse.swt.widgets.Display;
import org.modelio.gproject.fragment.FragmentState;
import org.modelio.gproject.gproject.GProjectEvent;
import org.modelio.gproject.gproject.GProjectEventType;
import org.modelio.gproject.gproject.IProjectMonitor;
import org.modelio.platform.core.events.ModelioEvent;
import org.modelio.platform.project.plugin.AppProjectCore;
import org.modelio.vbasic.files.FileUtils;

/**
 * ProjectService project monitor
 * <p>
 */
@objid ("6bc806dc-37b3-11e2-82ed-001ec947ccaf")
final class ProjectMonitor implements IProjectMonitor {
    @objid ("e3d5dbff-eba9-4391-9c48-ccebfa35bd10")
    private StatusReporter reporter;

    @objid ("2a2643d9-41dc-447b-a164-4834ef0d812f")
    private IProjectServiceAccess projectServiceAccess;

    @objid ("6bca6931-37b3-11e2-82ed-001ec947ccaf")
     ProjectMonitor(IProjectServiceAccess projectService, StatusReporter reporter) {
        this.projectServiceAccess = projectService;
        this.reporter = reporter;
        
    }

    @objid ("6bca6933-37b3-11e2-82ed-001ec947ccaf")
    @Override
    public void handleProjectEvent(GProjectEvent ev) {
        switch (ev.type) {
        case FRAGMENT_DOWN:
        
            AppProjectCore.LOG.error("'%s' fragment falled DOWN: %s", ev.fragment.getId(), ev.message);
            AppProjectCore.LOG.error(ev.throwable);
        
            // display problem to user
            reportAsStatus(ev);
        
            // post as E4 event
            this.projectServiceAccess.postAsyncEvent(ModelioEvent.FRAGMENT_DOWN, ev.fragment);
        
            break;
        case WARNING:
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
        case FRAGMENT_STATE_CHANGED:
            AppProjectCore.LOG.debug("'%s' fragment state changed to '%s'.", ev.fragment.getId(), ev.fragment.getState().toString());
            if ((ev.fragment.getState() == FragmentState.UP_FULL) || (ev.fragment.getState() == FragmentState.UP_LIGHT)) {
                this.projectServiceAccess.postAsyncEvent(ModelioEvent.FRAGMENT_UP, ev.fragment);
            }
            break;
        default:
            if (ev.message != null) {
                AppProjectCore.LOG.info(ev.message);
            }
            if (ev.throwable != null) {
                AppProjectCore.LOG.info(ev.throwable);
            }
            break;
        
        }
        
    }

    @objid ("ff9cf99a-5c5e-47ba-8ea7-57daa521a97b")
    String getMessage(GProjectEvent ev) {
        if ((ev.message != null) && !ev.message.isEmpty()) {
            return ev.message;
        } else if (ev.throwable == null) {
            return AppProjectCore.I18N.getMessage("ProjectService.noEventMessage");
        } else if (ev.throwable instanceof FileSystemException) {
            return FileUtils.getLocalizedMessage((FileSystemException) ev.throwable);
        } else if (ev.throwable instanceof RuntimeException) {
            return ev.throwable.toString(); // some runtime exceptions have
                                            // no message at all
        } else {
            return ev.throwable.getLocalizedMessage();
        }
        
    }

    @objid ("357af83e-da98-41b2-8095-e74ddcdffa42")
    private void reportAsStatus(final GProjectEvent ev) {
        final StatusReporter statusReporter = getReporter();
        
        if (statusReporter != null) {
            Display.getDefault().asyncExec(() -> {
                IStatus s1;
                if (ev.type == GProjectEventType.FRAGMENT_DOWN) {
                    String message = AppProjectCore.I18N.getMessage("ProjectService.fragmentDown", ev.fragment.getId());
                    s1 = statusReporter.newStatus(StatusReporter.WARNING, message, ev.throwable);
                } else {
                    s1 = statusReporter.newStatus(StatusReporter.WARNING, getMessage(ev), ev.throwable);
                }
                statusReporter.report(s1, StatusReporter.SHOW, ev);
            });
        }
        
    }

    @objid ("6391ece3-5887-4453-b4bb-e6a6d77a9735")
    private StatusReporter getReporter() {
        return this.reporter;
    }

}
