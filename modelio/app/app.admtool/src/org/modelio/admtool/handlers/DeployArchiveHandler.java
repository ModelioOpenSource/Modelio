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

package org.modelio.admtool.handlers;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.modelio.admtool.plugin.AdmTool;
import org.modelio.api.module.lifecycle.ModuleException;
import org.modelio.gproject.data.project.ProjectType;
import org.modelio.gproject.gproject.GProject;
import org.modelio.gproject.module.IModuleStore;
import org.modelio.platform.mda.infra.service.IModuleManagementService;
import org.modelio.platform.project.services.IProjectService;
import org.modelio.platform.ui.progress.IModelioProgressService;
import org.modelio.platform.ui.progress.ModelioProgressAdapter;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vbasic.progress.SubProgress;

/**
 * Handler to deploy an archive direclty in the project
 * 
 * @author cmarin
 */
@objid ("e762e859-1cf3-4d59-a25a-1a11c87718e7")
public class DeployArchiveHandler {
    @objid ("92b3a153-caf2-4be4-a075-c8b6a860c6ad")
    @Execute
    void execute(@Named (IServiceConstants.ACTIVE_SHELL) Shell shell, IModuleManagementService moduleSvc, IProjectService projectservice, IModelioProgressService progressService, IModuleStore catalog) {
        FileDialog dlg = new FileDialog(shell, SWT.OPEN);
        dlg.setFilterNames(new String[] { AdmTool.I18N.getString("DeployArchiveHandler.MDAComponents") }); //$NON-NLS-1$
        dlg.setFilterExtensions(new String[] { "*.jmdac" }); //$NON-NLS-1$
        
        dlg.open();
        
        String[] modules = dlg.getFileNames();
        ArrayList<String> paths = new ArrayList<>(modules.length);
        File parentPath = new File(dlg.getFilterPath());
        for (String m : modules) {
            paths.add(new File(parentPath, m).toString());
        }
        
        final GProject openedProject = projectservice.getOpenedProject();
        
        DeployModule runnable = new DeployModule(paths, catalog, moduleSvc, openedProject);
        
        try {
            progressService.run(AdmTool.I18N.getString("DeployArchiveHandler.AddModulesProgressTitle"), true, false, runnable);
        
        } catch (InvocationTargetException e) {
            AdmTool.LOG.error(e);
            MessageDialog.openError(shell,
                    AdmTool.I18N.getString("DeployArchiveHandler.AddModulesErrorTitle"),
                    e.getCause().getLocalizedMessage());
        } catch (InterruptedException e) {
            // ignore
        }
    }

    /**
     * Forbid this command for server projects.
     * 
     * @param projectservice the project service
     * @return true if the command is available.
     */
    @objid ("ad3ae4d3-5f2b-4c2f-bd57-c909c41769ad")
    @CanExecute
    boolean canExecute(IProjectService projectservice) {
        GProject openedProject = projectservice.getOpenedProject();
        
        // Forbid this command for server projects.
        return openedProject != null && openedProject.getType() == ProjectType.LOCAL;
    }

    /**
     * Deploy module runner
     */
    @objid ("02ac6fca-1e7e-4ce7-a22b-aa14be51546c")
    class DeployModule implements IRunnableWithProgress {
        @objid ("a6ad2b7a-9a31-4a98-bcc6-e9e412911ebe")
        private List<String> modules;

        @objid ("70f3efab-1d92-41ab-b3b8-60a3d75e27ef")
        private IModuleStore catalog;

        @objid ("aca8b113-1c17-4dc5-a726-be7fbe0d1e2e")
         IModuleManagementService moduleSvc;

        @objid ("38cb5ba8-70d7-4780-ab1c-75c9f2fdb479")
         GProject project;

        @objid ("1917ce35-b4c0-47c9-b864-fe035509a140")
        private StringBuilder report = new StringBuilder();

        @objid ("0d33a12f-affa-44c3-b873-d746e8aa3828")
        DeployModule(List<String> paths, IModuleStore catalog, IModuleManagementService moduleSvc, GProject project) {
            this.modules = paths;
            this.catalog = catalog;
            this.moduleSvc = moduleSvc;
            this.project = project;
        }

        @objid ("33e251e7-ac9a-4c1e-93d1-260139896671")
        @Override
        public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
            SubProgress mon = ModelioProgressAdapter.convert(monitor, AdmTool.I18N.getString("DeployArchiveHandler.AddModulesProgressTitle"), this.modules.size() * 3);
            int i = 0;
            for (String module : this.modules) {
                try {
                    Path mdacFile = Paths.get(module);
            
                    // Keys {0}:counter {1}:sum of modules {2}:module file name
                    monitor.subTask(AdmTool.I18N.getMessage("DeployArchiveHandler.AddModulesProgressSubTask", String.valueOf(i + 1), String.valueOf(this.modules.size()), mdacFile.getFileName()));
                    this.catalog.installModuleArchive(mdacFile, mon.newChild(2));
            
                    this.moduleSvc.installModule(null, this.project, mdacFile);
                    mon.worked(1);
            
                } catch (IOException e) {
                    this.report
                            .append(AdmTool.I18N.getMessage("DeployArchiveHandler.AddToCatalogFailed", module, FileUtils.getLocalizedMessage(e)))
                            .append("\n\n");
            
                    AdmTool.LOG.error(e);
                } catch (ModuleException e) {
                    this.report
                            .append(AdmTool.I18N.getMessage("DeployArchiveHandler.DeployModuleFailed", module, e.getLocalizedMessage()))
                            .append("\n\n");
            
                    AdmTool.LOG.error(e);
                }
                i++;
            }
            monitor.done();
            
            if (this.report.length() > 0) {
                this.report.insert(0, AdmTool.I18N.getMessage("DeployArchiveHandler.SomeFailed"));
                throw new InvocationTargetException(new IOException(this.report.toString()));
            }
        }

    }

}
