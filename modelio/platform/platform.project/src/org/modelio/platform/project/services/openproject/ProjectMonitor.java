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

import java.io.IOException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.e4.core.services.statusreporter.StatusReporter;
import org.eclipse.swt.widgets.Display;
import org.modelio.gproject.core.IGModelFragment;
import org.modelio.gproject.core.IGPartState.GPartStateEnum;
import org.modelio.gproject.monitor.GProjectEvent;
import org.modelio.gproject.monitor.GProjectEventType;
import org.modelio.gproject.monitor.IProjectMonitor;
import org.modelio.platform.core.events.ModelioEvent;
import org.modelio.platform.project.plugin.AppProjectCore;
import org.modelio.vbasic.files.FileUtils;

/**
 * An IProjectMonitor implementation provided and used by the ProjectService to monitor project configuration events.
 * <p>
 * This implementation does the following (may vary depending on the GProjectEvent type):
 * <ul>
 * <li>log the event in the Modelio Log</li>
 * <li>call StatusReporter report() method</li>
 * <li>fires Modelio E4 project events {@link ModelioEvent}</li>
 * </ul>
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
        case PART_DOWN: {
            IGModelFragment fragment = (IGModelFragment) ev.subject;
            AppProjectCore.LOG.error("'%s' fragment falled DOWN: %s", fragment.getId(), ev.message);
            AppProjectCore.LOG.error(ev.throwable);
        
            // display problem to user
            reportAsStatus(ev);
        
            // post as E4 event
            this.projectServiceAccess.postAsyncEvent(ModelioEvent.FRAGMENT_DOWN, (ev.subject));
            break;
        }
        case WARNING: {
            if (ev.throwable != null) {
                AppProjectCore.LOG.warning(ev.throwable);
            } else if (ev.message != null) {
                IGModelFragment fragment = ev.subject instanceof IGModelFragment ? (IGModelFragment) ev.subject : null;
                if (fragment != null) {
                    AppProjectCore.LOG.warning("%s : %s", fragment.getId(), ev.message);
                } else {
                    AppProjectCore.LOG.warning(ev.message);
                }
            }
            // display problem to user
            reportAsStatus(ev);
            break;
        }
        case PART_INSTALLED:
        case PART_STATE_CHANGED: {
            GPartStateEnum state = ev.subject instanceof IGModelFragment ? ((IGModelFragment) ev.subject).getState().getValue() : null;
            if ((state == GPartStateEnum.MOUNTED)) {
                this.projectServiceAccess.postAsyncEvent(ModelioEvent.FRAGMENT_UP, ev.subject);
            }
            break;
        }
        case PART_ADDED: {
            // Fire added fragment event
            this.projectServiceAccess.postAsyncEvent(ModelioEvent.FRAGMENT_ADDED, ev.subject);
            break;
        }
        case PART_REMOVED: {
            // Fire removed fragment event
            this.projectServiceAccess.postAsyncEvent(ModelioEvent.FRAGMENT_REMOVED, ev.subject);
            break;
        }
        default: {
            if (ev.message != null) {
                AppProjectCore.LOG.info(ev.message);
            }
            if (ev.throwable != null) {
                AppProjectCore.LOG.info(ev.throwable);
            }
            break;
        }
        }
        
    }

    @objid ("ff9cf99a-5c5e-47ba-8ea7-57daa521a97b")
    String getMessage(GProjectEvent ev) {
        if ((ev.message != null) && !ev.message.isEmpty()) {
            return ev.message;
        } else if (ev.throwable == null) {
            return AppProjectCore.I18N.getMessage("ProjectService.noEventMessage");
        } else if (ev.throwable instanceof IOException) {
            return FileUtils.getLocalizedMessage((IOException) ev.throwable);
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
                if (ev.type == GProjectEventType.PART_DOWN) {
                    String message = AppProjectCore.I18N.getMessage("ProjectService.fragmentDown", ((IGModelFragment) ev.subject).getId());
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
