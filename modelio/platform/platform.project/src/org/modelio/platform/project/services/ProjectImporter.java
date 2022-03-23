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
package org.modelio.platform.project.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.modelio.platform.project.plugin.AppProjectCore;
import org.modelio.platform.ui.progress.ModelioProgressAdapter;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vbasic.files.Unzipper;
import org.modelio.vbasic.progress.IModelioProgress;

/**
 * Service that imports a project archive into the workspace.
 * @author cma
 * @since 3.7
 */
@objid ("c477837f-1e54-4165-b7bb-79cbca44a828")
public class ProjectImporter {
    @objid ("6e74ce03-107f-45b4-8fc8-bab299e1b820")
    private final IProjectService projSvc;

    @objid ("d6d809f7-f16e-4836-99b5-e163fa274283")
    private final Shell parentShell;

    @objid ("0f418b95-3ebf-4100-8783-0961d619c8f4")
    public  ProjectImporter(IProjectService projSvc, Shell parentShell) {
        this.projSvc = Objects.requireNonNull(projSvc);
        this.parentShell = Objects.requireNonNull(parentShell);
        
    }

    /**
     * Import the archive.
     * @param the name of the created project. <code>null</code> when an error occurs.
     * @param archiveFile the archive file.
     * @param progress a progress monitor.
     */
    @objid ("f58550c6-86a3-44b8-8a86-8a96f6f8591a")
    public String importProject(Path archiveFile, IProgressMonitor progress) {
        final Unzipper unzipper = new Unzipper();
        final IModelioProgress monitor = ModelioProgressAdapter.convert(progress, 10);
        final Display display = this.parentShell.getDisplay();
        
        monitor.beginTask(AppProjectCore.I18N.getMessage("ImportingProject", "..."), 10);
        
        ArchiveEntry[] projectConf;
        try {
            projectConf = unzipper.findEntry(archiveFile.toFile(), "^[^/]+/project\\.conf$");
        } catch (IOException e) {
            reportIOException(archiveFile.toString(), e);
            return null;
        }
        monitor.worked(1);
        
        if (projectConf.length == 1) {
            // Checks for an already existing project
            final String projectName = projectConf[0].getName().substring(0, projectConf[0].getName().indexOf("/"));
            if (this.projSvc.getWorkspace().resolve(projectName).toFile().exists()) {
                if (! CompletableFuture.supplyAsync(
                        () -> MessageDialog.openQuestion(this.parentShell,
                                AppProjectCore.I18N.getString("CannotImportExistingProjectTitle"),
                                AppProjectCore.I18N.getMessage("CannotImportExistingProjectMsg", projectName))
                        , display::syncExec).join() ) {
                    // Fast exit
                    return null;
                }
            }
            String progressMessage = AppProjectCore.I18N.getMessage("ImportingProject", projectName);
            monitor.subTask(progressMessage);
            monitor.worked(1);
        
            unzipper.setProgressLabelPrefix(progressMessage);
            try {
                unzipper.unzip(archiveFile, this.projSvc.getWorkspace(), monitor);
        
                AppProjectCore.LOG.info("Imported archive '%s' %d bytes.", archiveFile, getFileSize(archiveFile));
        
                this.projSvc.refreshWorkspace(projectName);
            } catch (final IOException e) {
                reportIOException(projectName, e);
            }
        
            return projectName;
        } else {
            display.syncExec(()
                    -> MessageDialog.openInformation(this.parentShell,
                            AppProjectCore.I18N.getString("InvalidProjectArchiveTitle"),
                            AppProjectCore.I18N.getMessage("InvalidProjectArchiveMsg", archiveFile.toString()))
                    );
            return null;
        }
        
    }

    @objid ("aa138a29-c435-4f86-8922-7a2c1ae24321")
    private static long getFileSize(Path archiveFile) {
        try {
            return Files.size(archiveFile);
        } catch (IOException e) {
            AppProjectCore.LOG.debug(e);
            return -1;
        }
        
    }

    @objid ("0855ec03-eb0e-4d75-b86c-df54ee1525d3")
    private void reportIOException(String projectName, IOException e) {
        AppProjectCore.LOG.error(e);
        this.parentShell.getDisplay().asyncExec(()
                -> MessageDialog.openError(this.parentShell,
                        AppProjectCore.I18N.getMessage("ImportingProject", projectName),
                        FileUtils.getLocalizedMessage(e)));
        
    }

}
