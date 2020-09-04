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

package org.modelio.app.project.core.services;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map.Entry;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.Platform;
import org.eclipse.osgi.service.datalocation.Location;
import org.modelio.api.modelio.Modelio;
import org.modelio.app.project.core.creation.IProjectCreationData;
import org.modelio.app.project.core.creation.ProjectCreationDataModel;
import org.modelio.app.project.core.plugin.AppProjectCore;
import org.modelio.gproject.gproject.GProject;
import org.modelio.gproject.gproject.GProjectAuthenticationException;
import org.modelio.script.engine.core.engine.IScriptRunner;
import org.modelio.script.engine.core.engine.ScriptRunnerFactory;
import org.modelio.ui.progress.IModelioProgressService;
import org.modelio.vbasic.files.FileUtils;

/**
 * Contrary to its name this class handles all command line options and run batch script if specified.
 * <p>
 * If {@link CommandLineData#isBatch() batch mode}, {@link #run()} will never return and exit the VM instead.<br>
 * In the other case and without failures the method will return normally.
 */
@objid ("00470784-8d2e-10b4-9941-001ec947cd2a")
class BatchRunner implements Runnable {
    @objid ("00471044-8d2e-10b4-9941-001ec947cd2a")
    private final CommandLineData batchData;

    @objid ("0047219c-8d2e-10b4-9941-001ec947cd2a")
    private final IProjectService projectService;

    @objid ("2f214a1a-afcb-4bee-9932-daf5185a3f98")
    private final IModelioProgressService progressService;

    @objid ("004730a6-8d2e-10b4-9941-001ec947cd2a")
    public BatchRunner(IModelioProgressService progressService, IProjectService projectService, CommandLineData batchData) {
        this.progressService = progressService;
        this.projectService = projectService;
        this.batchData = batchData;
    }

    @objid ("00474a32-8d2e-10b4-9941-001ec947cd2a")
    @Override
    public void run() {
        AppProjectCore.LOG.debug("Running batch data : %s", this.batchData);
        try {
            // Change the workspace if required
            initWorkspace();
        
            // Handle -create option and -template if defined
            final String projectName = this.batchData.getProjectName();
            if (this.batchData.isCreate() && projectName != null) {
                createProjectFromBatchData(projectName);
            }
        
            // Check option parameters
            // The project must exist in the current workspace
            GProject openedProject = openProject(projectName);
        
            // If a project was opened and if there is a script to launch
            // run in batch mode
            if (openedProject != null && this.batchData.getScript() != null) {
                int retcode = runScript(openedProject);
        
                System.exit(retcode);
            }
            
            // No script, return and the caller will run with GUI.
            return;
        } catch (RuntimeException | Error t) {
            AppProjectCore.LOG.error("Unexpected %s:", t);
            AppProjectCore.LOG.error(t);
            throw t;
        } finally {
            if (this.batchData.isBatch()) {
                // Ensure Modelio always exit in batch mode
                System.exit(-1);
            }
        }
    }

    /**
     * Creates a new project in the current workspace. The nature and the properties of the project to create are passed in the <code>dataModel</code> argument.
     * @param delegateProjectCreator
     * the delegated project creator, can be null. If null a standard default project creator will be used
     * @param data the nature, characteristics and properties of the project to create.
     */
    @objid ("f36e1685-dff9-4ea3-8349-308ec607c070")
    private void createProject(final IProjectCreationData data) {
        Objects.requireNonNull(data);
        
        // Provide a progress monitor as parameter if possible
        if (this.progressService != null) {
            try {
                final String title = AppProjectCore.I18N.getString("CreateProject.ProgressDialog.title");
        
                this.progressService.run(title, false, false, monitor -> {
                    try {
                        this.projectService.createProject(data, monitor);
                    } catch (IOException e) {
                        AppProjectCore.LOG.error(FileUtils.getLocalizedMessage(e));
                        AppProjectCore.LOG.error(e);
                    }
                });
            } catch (InvocationTargetException | InterruptedException e) {
                AppProjectCore.LOG.error(e);
            }
        } else {
            // No progress service available
            try {
                this.projectService.createProject(data, null);
            } catch (IOException e) {
                AppProjectCore.LOG.error(FileUtils.getLocalizedMessage(e));
                AppProjectCore.LOG.error(e);
            }
        }
    }

    @objid ("f55a0aa2-25f6-4817-b782-f2b467359069")
    private Path getTemplate(String templateArg) {
        Path templatePath = null;
        
        if (templateArg == null || templateArg.isEmpty()) {
            return null;
        }
        
        // First try to interpret the argument as a full path
        templatePath = Paths.get(templateArg);
        
        if (Files.exists(templatePath) && Files.isRegularFile(templatePath) && Files.isReadable(templatePath)) {
            return templatePath;
        }
        
        // Otherwise try to use the argument as a filename to be looked up in the Modelio install templates directory
        Location location = Platform.getInstallLocation();
        try {
            String tpl = ".template";
            URI resolvedURI = new URI(location.getURL().getProtocol(), location.getURL().getPath(), null);
            if (templateArg.endsWith(tpl)) {                
                templatePath = Paths.get(resolvedURI).resolve("templates").resolve(templateArg);
            }
            else {
                templatePath = Paths.get(resolvedURI).resolve("templates").resolve(templateArg + tpl);
            }            
        
            if (Files.exists(templatePath) && Files.isRegularFile(templatePath) && Files.isReadable(templatePath)) {
                return templatePath;
            }
        } catch (URISyntaxException e) {
            AppProjectCore.LOG.error("Invalid template name");
            AppProjectCore.LOG.error(e);
        }
        
        // At this stage the given argument could not be interpreted as a template file descriptor
        return null;
    }

    @objid ("dfad7c41-809b-4e15-87b0-e640b53b675b")
    private void createProjectFromBatchData(final String projectName) {
        // Check option parameters
        // The project must not exist in the current workspace
        if (Files.exists(this.projectService.getWorkspace().resolve(projectName))) {
            AppProjectCore.LOG.error("Aborting: The '%s' project already exists in the workspace.", projectName);
            System.exit(-1);
        }
        
        String templateArg = this.batchData.getTemplate();
        Path templatePath = getTemplate(templateArg);
        
        if (templateArg != null && templatePath == null) {
            AppProjectCore.LOG.error("Aborting: The '%s' template file does not exist or is not accessible.", templateArg);
            System.exit(-1);
        }
        
        ProjectCreationDataModel pm = new ProjectCreationDataModel(this.projectService.getWorkspace());
        pm.setProjectName(projectName);
        pm.setTemplate(templatePath);
        
        createProject(pm);
    }

    /**
     * Change workspace if specified in batch data.
     */
    @objid ("b68f3865-bee2-4379-86a5-081e4d27617a")
    private void initWorkspace() {
        if (this.batchData.getWorkspace() != null) {
            Path wkdir = Paths.get(this.batchData.getWorkspace());
            try {
                wkdir = wkdir.toRealPath();
        
                Files.createDirectories(wkdir);
            } catch (final IOException e) {
                AppProjectCore.LOG.error("Invalid workspace directory '%s' : %s", this.batchData.getWorkspace(), FileUtils.getLocalizedMessage(e));
                AppProjectCore.LOG.debug(e);
            }
        
            if (!Files.isDirectory(wkdir)) {
                AppProjectCore.LOG.error("Invalid workspace directory: '%s', it does not exist.", this.batchData.getWorkspace());
                System.exit(-1);
            }
        
            if (!Files.isWritable(wkdir)) {
                AppProjectCore.LOG.error("Workspace directory '%s' is read only.", this.batchData.getWorkspace());
                System.exit(-1);
            }
        
            this.projectService.changeWorkspace(wkdir);
        }
    }

    @objid ("27271532-33cf-4a61-94c1-318ef3a2c210")
    private GProject openProject(final String projectName) {
        GProject openedProject = this.projectService.getOpenedProject();
        
        if (projectName != null) {
            assert (openedProject == null);
            try {
                this.projectService.openProject(projectName, null, null);
            } catch (GProjectAuthenticationException e) {
                AppProjectCore.LOG.error(e);
            } catch (IOException e) {
                AppProjectCore.LOG.error("The '%s' project could not be opened in the workspace: %s", projectName, FileUtils.getLocalizedMessage(e));
                AppProjectCore.LOG.error(e);
                System.exit(-1);
            } catch (InterruptedException e) {
                AppProjectCore.LOG.error("The '%s' project opening has been cancelled.", projectName);
                AppProjectCore.LOG.error(e);
                System.exit(-1);
            }
        
            openedProject = this.projectService.getOpenedProject();
            if (openedProject == null) {
                AppProjectCore.LOG.error("The '%s' project could not be opened in the workspace.", projectName);
                System.exit(-1);
            }
        }
        return openedProject;
    }

    /**
     * Run the specified script.
     * @param openedProject the project
     * @return the return code.
     */
    @objid ("1f274c5b-8457-410a-a016-2f24529b7ef6")
    private int runScript(GProject openedProject) {
        assert (this.batchData.getScript() != null); 
        
        // The script file must be accessible
        File scriptFile = new File(this.batchData.getScript());
        if (!(scriptFile.exists() && scriptFile.canRead() && scriptFile.isFile())) {
            AppProjectCore.LOG.error("The Script file: '%s' was not found or not accessible.", scriptFile.getAbsolutePath());
            System.exit(-1);
        }
        
        
        AppProjectCore.LOG.info("Running script: %s", scriptFile);
        int retcode = 0;
        try {
            if (scriptFile.getName().endsWith(".py")) {
                final IScriptRunner scriptRunner = ScriptRunnerFactory.getInstance().getScriptRunner("jython");
                scriptRunner.addClassLoader(scriptRunner.getEngine().getClass().getClassLoader());
                scriptRunner.bind("coreSession", openedProject.getSession());
                scriptRunner.bind("modelingSession", Modelio.getInstance().getModelingSession());
                scriptRunner.bind("parameters", this.batchData.getBatchParameters());
              
                for (Entry<String, String> p : this.batchData.getBatchParameters().entrySet()) {
                    scriptRunner.bind(p.getKey(), p.getValue());
                }
              
                scriptRunner.runFile(scriptFile.toPath(), null, null);
            } else if (scriptFile.getName().endsWith(".jar")) {
                // Run .jar main class as a E4 processor
                new JarRunner().runJarFile(openedProject, scriptFile, this.batchData.getBatchParameters());
            } else {
                AppProjectCore.LOG.error("The script must a Python script (.py), aborting...");
            }
        } catch (Throwable e) {
            AppProjectCore.LOG.error("Unexpected exception:");
            AppProjectCore.LOG.error(e);
            retcode = -1;
        } finally {
            this.projectService.closeProject(openedProject);
        }
        return retcode;
    }

}
