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

import java.io.IOException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.modelio.api.module.lifecycle.ModuleException;
import org.modelio.app.project.core.plugin.AppProjectCore;
import org.modelio.gproject.data.project.ModuleDescriptor;
import org.modelio.gproject.gproject.GProject;
import org.modelio.gproject.gproject.TodoActionsRunner;
import org.modelio.gproject.module.GModule;
import org.modelio.gproject.module.IModuleHandle;
import org.modelio.mda.infra.service.IModuleManagementService;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vbasic.progress.SubProgress;
import org.modelio.vcore.smkernel.AccessDeniedException;

/**
 * Post open to-do action runner.
 */
@objid ("d3eae071-6ef7-449c-af6c-54085b8f4dbb")
class TodoRunner extends TodoActionsRunner {
    @objid ("c3d552da-b123-48c4-9b85-1cdef7b1221e")
    private final IModuleManagementService moduleService;

    /**
     * Initialize the project configurer.
     * @param project the project to synchronize
     * @param moduleService the module service used to install and remove modules.
     */
    @objid ("2a248da2-da07-4866-9ead-3f7bbba0bf30")
    public TodoRunner(GProject project, IModuleManagementService moduleService) {
        super(project);
        this.moduleService = moduleService;
    }

    @objid ("b0e7c795-84a6-48d4-ac84-eca1f5817df2")
    @Override
    protected void installModule(ModuleDescriptor fd, IModelioProgress monitor) throws IOException {
        try {
            SubProgress mon = SubProgress.convert(monitor, 3);
            mon.subTask(AppProjectCore.I18N.getMessage("ProjectSynchro.installModule",
                    fd.getName(), fd.getVersion(), fd.getArchiveLocation()));
        
            // Install the module as if the user asked for it
            IModuleHandle rtModuleHandle = getModuleHandle(mon.newChild(1), fd);
            this.moduleService.installModule(mon.newChild(1), getProject(), rtModuleHandle , fd.getArchiveLocation());
            
            // Overwrite default module parameters with the server parameters
            GModule gModule = getModule(fd.getName()); 
            reconfigureModule(gModule, fd, mon);
        
        } catch (ModuleException e) {
            throw new IOException(e.getLocalizedMessage(), e);
        }
    }

    @objid ("d0837faa-1407-4209-8b2d-887a9d07aed3")
    @Override
    protected void removeModule(GModule m, IModelioProgress mon) throws IOException {
        mon.subTask(AppProjectCore.I18N.getMessage("ProjectSynchro.removeModule",
                m.getName(), m.getVersion()));
        try {
            this.moduleService.removeModule(m);
        } catch (ModuleException e) {
            throw new IOException(e.getLocalizedMessage(), e);
        }
    }

    @objid ("42b5e0d1-c942-4ccf-a1c1-b5979f91863b")
    @Override
    protected void upgradeModule(GModule m, ModuleDescriptor fd, IModelioProgress monitor) throws IOException {
        monitor.subTask(AppProjectCore.I18N.getMessage("ProjectSynchro.updateModule",
                m.getName(), m.getVersion(), fd.getVersion()));
        
        try  {
            // Install the module as if the user asked for it
            // The module may change some parameters on upgrade, they won't be lost.
            SubProgress mon = SubProgress.convert(monitor, 3);
            IModuleHandle rtModuleHandle = getModuleHandle(mon.newChild(1), fd);
            this.moduleService.installModule(mon.newChild(1), getProject(), rtModuleHandle , fd.getArchiveLocation());
        
        } catch (ModuleException e) {
            throw new IOException(e.getLocalizedMessage(), e);
        } finally {
            // TODO is it the right place ?
            // Overwrite default module parameters with the server parameters
            // Note: 'm' is invalid after installModule(...)
            GModule newGModule = getModule(fd.getName());
            reconfigureModule(newGModule, fd, monitor);
        
        }
    }

    /**
     * Report failures to user
     * @param shell a SWT shell
     */
    @objid ("457984e9-466e-40de-8cdd-1f9598bab209")
    public void reportFailures(Shell shell) {
        // TODO make a better dialog
        final String sb = getFailuresReport();
        
        // Get a shell
        final String title = AppProjectCore.I18N.getMessage("ProjectService.ProjectSynchroProblems.title", getProject().getName());
        
        Display d = shell != null ? shell.getDisplay() : Display.getDefault();
        d.asyncExec(
                () -> MessageDialog.openWarning(shell, title, sb));
    }

    /**
     * Concatenates failures in a string builder.
     * @return the failures
     */
    @objid ("4214d867-d139-479e-b085-958a0d1be488")
    public String getFailuresReport() {
        final StringBuilder sb = new StringBuilder();
        
        sb.append(AppProjectCore.I18N.getMessage("ProjectService.ProjectSynchroProblems.message", getProject().getName()));
        
        for (Failure f : getFailures()) {
            sb.append(" - ")
            .append(f.getAction().getLocalizedLabel())
            .append(":\n   ");
        
            Throwable cause = f.getError();
            if (cause instanceof IOException) {
                sb.append(indented(FileUtils.getLocalizedMessage((IOException) cause)));
            } else if (cause instanceof AccessDeniedException) {
                sb.append(indented(cause.getLocalizedMessage()));
            } else if (cause instanceof RuntimeException) {
                sb.append(indented(cause.toString()));
            } else {
                sb.append(indented(cause.getLocalizedMessage()));
            }
            sb.append("\n\n");
        }
        return sb.toString();
    }

    @objid ("5a31613c-bad2-4def-a285-6260152f1e15")
    private static String indented(String str) {
        return str.replace("\n", "\n\t");
    }

}
