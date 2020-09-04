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

package org.modelio.app.ui.welcome;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.MessageFormat;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.modelio.app.project.core.services.IProjectService;
import org.modelio.app.project.core.services.ProjectImporter;
import org.modelio.app.ui.plugin.AppUi;
import org.modelio.core.rcp.uiservice.IModelioUiService;
import org.modelio.ui.progress.IModelioProgressService;
import org.modelio.vbasic.files.FileUtils;

@objid ("0c453fd3-c67f-4179-a78a-de8d344225c0")
public class WelcomeViewController {
    @objid ("c5d860cc-e2d8-4489-ad9e-df90d52ec4eb")
    private IProjectService projectService;

    @objid ("8325c616-f361-4564-8ad9-039072e4af58")
    private IModelioProgressService progressService;

    @objid ("c4da4066-3bfb-4526-a46b-82c08b8fbdbf")
    private WelcomeView welcomeView;

    @objid ("fcbb72f9-e7f1-496a-88e3-463f9d42dd58")
    private IModelioUiService modelioUiService;

    /**
     * @param uri
     * the URI of the example zip file
     */
    @objid ("57b94a3c-c95c-4746-9b27-135ae16d8cac")
    public void installExample(URI zipUri) {
        final Display display = this.welcomeView.getControl().getDisplay();
        final Shell shell = this.welcomeView.getControl().getShell();
        
        // Install example in workspace
        try {
            this.progressService.run(AppUi.I18N.getString("InstallExample.title"), true, false, monitor -> {
        
                AppUi.LOG.debug("Installing example: %s\n", zipUri.toASCIIString());
        
                SubMonitor progress = SubMonitor.convert(monitor,
                        AppUi.I18N.getString("InstallExample.task"), 100);
        
                // Download zip file
                progress.subTask(AppUi.I18N.getMessage("InstallExample.downloading", zipUri.toASCIIString()));
                Path localZip;
                try {
                    localZip = downloadZipFile(zipUri);
                } catch (IOException e) {
                    AppUi.LOG.error(e);
                    display.asyncExec(() -> MessageDialog.openError(
                            shell,
                            AppUi.I18N.getString("InstallExample.importing"),
                            FileUtils.getLocalizedMessage(e)));
                    return;
                }
                progress.worked(20);
                progress.subTask(AppUi.I18N.getString("InstallExample.importing"));
        
                // Import the archive
                new ProjectImporter(this.projectService, shell)
                        .importProject(localZip, progress.newChild(78));
        
                progress.subTask(AppUi.I18N.getString("InstallExample.cleaningup"));
                cleanTempZipFile(localZip);
                progress.done();
            });
        } catch (InvocationTargetException e) {
            AppUi.LOG.error(e);
            MessageDialog.openError(
                    shell,
                    AppUi.I18N.getString("InstallExample.importing"),
                    MessageFormat.format("Unexpected failure : {0}", e.getCause().toString()));
        } catch (InterruptedException e) {
            // ignore
        } finally {
            // Switch to workspace perspective
            this.modelioUiService.switchToWorkspace();
        }
    }

    @objid ("a83b1307-f034-45d2-9388-20a7e861ef95")
    public WelcomeViewController(WelcomeView welcomeView, IModelioUiService pm, IProjectService projectService, IModelioProgressService progressService) {
        this.modelioUiService = pm;
        this.welcomeView = welcomeView;
        this.progressService = progressService;
        this.projectService = projectService;
    }

    @objid ("5426cd53-03ad-43f5-aeaf-9d42480b9716")
    private void cleanTempZipFile(Path zipFile) {
        try {
            FileUtils.delete(zipFile.toFile());
            FileUtils.delete(zipFile.getParent());
        } catch (IOException e) {
            AppUi.LOG.warning(FileUtils.getLocalizedMessage(e));
            AppUi.LOG.debug(e);
        }
    }

    @objid ("2e1b5761-f845-4115-a525-11c26825ecfc")
    private Path downloadZipFile(URI zipUri) throws IOException {
        String zipName = Paths.get(zipUri.getPath()).getFileName().toString();
        Path tempDir = Files.createTempDirectory("zip");
        Path tempFile = tempDir.resolve(zipName);
        
        try (InputStream in = zipUri.toURL().openStream()) {
            Files.copy(in, tempFile, StandardCopyOption.REPLACE_EXISTING);
        }
        return tempFile;
    }

}
