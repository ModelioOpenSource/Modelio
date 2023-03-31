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
package org.modelio.audit.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Inject;
import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.services.statusreporter.StatusReporter;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.jface.preference.IPreferenceStore;
import org.modelio.audit.engine.AuditEngine;
import org.modelio.audit.engine.AuditJobRunner;
import org.modelio.audit.engine.core.IAuditExecutionPlan;
import org.modelio.audit.engine.core.IAuditMonitor;
import org.modelio.audit.plugin.Audit;
import org.modelio.audit.preferences.AuditModelController;
import org.modelio.audit.preferences.AuditPreferencePage;
import org.modelio.audit.preferences.model.AuditConfigurationModel;
import org.modelio.audit.service.IAuditService;
import org.modelio.gproject.core.IGModelFragment;
import org.modelio.gproject.core.IGProject;
import org.modelio.gproject.data.project.GProjectPartDescriptor.GProjectPartType;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.platform.core.events.ModelioEventTopics;
import org.modelio.platform.project.services.IProjectService;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.SmClass;

/**
 * Audit service implementation.
 */
@objid ("7ddbb117-45fb-11e2-9b4d-bc305ba4815c")
@SuppressWarnings ("restriction")
@Creatable
public class AuditService implements IAuditService {
    @objid ("7c5545c3-1b66-4212-8dc8-742cd4f8d9b1")
    private static final String START_JOB_ID = "audit.job.start";

    @objid ("7ddbb11a-45fb-11e2-9b4d-bc305ba4815c")
    private final AuditEngine auditEngine;

    @objid ("7ddbb11b-45fb-11e2-9b4d-bc305ba4815c")
    private IGProject openedProject;

    @objid ("4fd2d0ed-357f-45c7-b763-e7d91f4f80e5")
    private AuditModelController modelController;

    @objid ("599c07f0-15cf-421f-9d46-13e12822a4b3")
    private final Geometry geometry;

    /**
     * Thread map for audit jobs. The key is an audit job identifier. The value is an AuditJobRunner thread.
     */
    @objid ("8cbe1403-afe5-4135-a683-2a64bc832a10")
    private final Map<String, Thread> auditJobsMap;

    @objid ("c5c5a1af-0e3d-4d9c-a583-dd7349b4bbef")
    @Inject
    private StatusReporter errReporter;

    @objid ("7ddbb11c-45fb-11e2-9b4d-bc305ba4815c")
    @Override
    public void setConfigurationFile(final File confFile) {
        this.geometry.setConfigurationFile(confFile);
        restart();
        
    }

    @objid ("7ddbb120-45fb-11e2-9b4d-bc305ba4815c")
    @Override
    public void restart() {
        try {
            this.modelController = createModelController();
        
            // Generate the plan
            final IAuditExecutionPlan auditPlan = this.modelController.createPlan();
        
            // Change the plan
            this.auditEngine.setPlan(auditPlan);
        } catch (final FileSystemException e) {
            this.errReporter.show(StatusReporter.ERROR, FileUtils.getLocalizedMessage(e), e);
        } catch (final IOException e) {
            Audit.LOG.error(e);
            this.errReporter.show(StatusReporter.ERROR, e.getLocalizedMessage(), e);
        }
        
    }

    @objid ("7ddbb123-45fb-11e2-9b4d-bc305ba4815c")
    @Override
    public void checkElement(final MObject element, final String jobId) {
        this.auditEngine.checkElement(element, jobId);
    }

    /**
     * Return File descriptor of the local configuration file for the current project.
     * <p>
     * An opened modeling session is assumed to be available.
     * @return File : Configuration file
     */
    @objid ("7ddbb127-45fb-11e2-9b4d-bc305ba4815c")
    @Override
    public File getConfigurationFile() {
        return this.geometry.getConfigurationFile();
    }

    /**
     * This method is called while a project is opening.<br/>
     * <ol>
     * <li>Keep a reference to the modeling session and model services.
     * <li>
     * </ol>
     */
    @objid ("7ddbb12d-45fb-11e2-9b4d-bc305ba4815c")
    @Inject
    @Optional
    void onProjectOpening(@UIEventTopic (ModelioEventTopics.PROJECT_OPENING) final IGProject project, IProjectService projectService) {
        this.openedProject = project;
        this.geometry.initForProject(project);
        
        try {
            // Create the controller
            this.modelController = createModelController();
        
            // Generate the plan
            final IAuditExecutionPlan auditPlan = this.modelController.createPlan();
        
            // Init the audit engine, set the plan and start
            this.auditEngine.setPlan(auditPlan);
            this.auditEngine.start(this.openedProject.getSession());
        
            IPreferenceStore prefs = projectService.getProjectPreferences(Audit.PLUGIN_ID);
            Boolean runAtStartup = prefs.getBoolean(AuditPreferencePage.RUN_AT_STARTUP);
            if (runAtStartup) {
                boolean valid = analyseAllModel(project);
                if (!valid) {
                    prefs.setValue(AuditPreferencePage.RUN_AT_STARTUP, false);
                }
            }
        
        } catch (final FileSystemException e) {
            this.errReporter.show(StatusReporter.ERROR, FileUtils.getLocalizedMessage(e), e);
        } catch (final IOException e) {
            Audit.LOG.error(e);
            this.errReporter.show(StatusReporter.ERROR, e.getLocalizedMessage(), e);
        }
        
    }

    /**
     * Run Audit on all model fragments
     */
    @objid ("5b9b2a97-e466-4375-a7cc-d897c1968214")
    private boolean analyseAllModel(final IGProject project) {
        long projectSize = 0;
        SmClass melement = project.getSession().getMetamodel().getMClass(ModelElement.class);
        List<MObject> roots = new ArrayList<MObject>();
        for (IGModelFragment iProjectFragment : project.getParts(IGModelFragment.class)) {
            if (iProjectFragment.getType().equals(GProjectPartType.EXMLFRAGMENT) || iProjectFragment.getType().equals(GProjectPartType.SVNFRAGMENT)) {
                roots.addAll(iProjectFragment.getRoots());
                projectSize = projectSize + iProjectFragment.getRepository().findByClass(melement, true).size();
            }
            if (projectSize > AuditPreferencePage.MAX_MODEL_SIZE) {
                return false;
            }
        }
        checkElementTree(roots, START_JOB_ID);
        return true;
    }

    /**
     * Called when a project is closed. On session close un-reference the modeling session and model services.
     */
    @objid ("7dde127a-45fb-11e2-9b4d-bc305ba4815c")
    @Optional
    @Inject
    void onProjectClosing(@UIEventTopic (ModelioEventTopics.PROJECT_CLOSING) final IGProject closedProject) {
        if (closedProject != null) {
            interuptCheck(START_JOB_ID);
        
            // Standard audit
            this.auditEngine.stop(closedProject.getSession());
            this.modelController = null;
            this.openedProject = null;
        }
        
    }

    @objid ("7dde1283-45fb-11e2-9b4d-bc305ba4815c")
    @Override
    public AuditEngine getAuditEngine() {
        return this.auditEngine;
    }

    /**
     * constructor to be called by E4 engine.
     */
    @objid ("b4c3aaa7-f8a1-41cc-9c0b-d8cf049f14c5")
    public  AuditService() {
        this.auditEngine = new AuditEngine();
        this.geometry = new Geometry();
        this.auditJobsMap = new HashMap<>();
        
    }

    @objid ("36b7f133-5e97-483e-aeb0-9965120a1037")
    @Override
    public AuditConfigurationModel getConfigurationModel() {
        return new AuditConfigurationModel(this.modelController.getModel());
    }

    @objid ("4b43952c-f80d-4afe-90d0-a91d9ab991b7")
    @Override
    public void apply(final AuditConfigurationModel auditConfiguration) {
        // Create an audit configurator
        if (this.modelController != null) {
            this.modelController.applyAuditConfiguration(auditConfiguration);
        }
        
        try {
            saveConfiguration(getConfigurationFile());
            restart();
        } catch (final IOException e) {
            Audit.LOG.error(e);
            this.errReporter.show(StatusReporter.ERROR, e.getLocalizedMessage(), e);
        }
        
    }

    @objid ("c8161b2b-1836-4e48-a89b-e2a942be8a69")
    @Override
    public AuditConfigurationModel getFactorySettings() {
        final AuditModelController ret = new AuditModelController();
        try {
            ret.applyAuditConfiguration(this.geometry.getDefaultProjectConfigurationFile());
        } catch (final IOException e) {
            Audit.LOG.error(e);
            this.errReporter.show(StatusReporter.ERROR, e.getLocalizedMessage(), e);
        }
        return ret.getModel();
    }

    @objid ("cb7aaa3a-8f81-4781-a48c-55f70ef1e696")
    private void saveConfiguration(final File file) throws IOException {
        this.modelController.writeConfiguration(file);
    }

    /**
     * Creates a new model controller matching the audit service current configuration.
     * @return a new model controller.
     * @throws IOException in case of I/O error
     */
    @objid ("76e8a01d-9912-44d3-b1d6-5376f260cc8b")
    private AuditModelController createModelController() throws IOException {
        final AuditModelController ret = new AuditModelController();
        final File defaultConf = this.geometry.getDefaultProjectConfigurationFile();
        if (defaultConf != null) {
            ret.addDefaultConf(defaultConf);
        }
        
        final File confFile = getConfigurationFile();
        if (confFile.isFile()) {
            ret.applyAuditConfiguration(confFile);
        }
        return ret;
    }

    /**
     * Get the edited project.
     * @return the edited project.
     */
    @objid ("1a82221b-5501-4a65-89df-42671396dbbe")
    IGProject getProject() {
        assert this.openedProject != null;
        return this.openedProject;
    }

    @objid ("a146c2ab-3d69-4d46-9638-821cac96ecf4")
    @Override
    public void checkElementTree(final List<MObject> selection, final String jobId) {
        final AuditJobRunner checker = new AuditJobRunner(this, selection, jobId);
        final Thread checkerThread = new Thread(checker);
        checkerThread.setPriority(Thread.MIN_PRIORITY);
        checkerThread.setName("CHECKER");
        checkerThread.start();
        this.auditJobsMap.put(jobId, checkerThread);
        
    }

    @objid ("4685e5e3-6ef7-477f-bb73-16e3caccf244")
    @Override
    public void addAuditMonitor(final IAuditMonitor monitor) {
        this.auditEngine.addAuditMonitor(monitor);
    }

    @objid ("40c2eea9-b031-4219-96f9-ffb3567c938f")
    @Override
    public void removeAuditMonitor(final IAuditMonitor monitor) {
        this.auditEngine.addAuditMonitor(monitor);
    }

    @objid ("4b695881-32ea-4ce1-b7ef-fd4ebe5b0272")
    @Override
    public void interuptCheck(final String jobId) {
        final Thread checkerThread = this.auditJobsMap.get(jobId);
        if (checkerThread != null) {
            checkerThread.interrupt();
            this.auditJobsMap.remove(jobId);
        }
        
    }

    /**
     * Ensure the configuration file exists.
     * @throws IOException in case of I/O error making the project configuration file.
     */
    @objid ("703edf07-33d2-40cf-ae9a-9a2d5f447f3d")
    private void ensureConfigurationFile() throws IOException {
        final File curDefault = this.geometry.getDefaultProjectConfigurationFile();
        if (curDefault == null || !curDefault.isFile()) {
            // If no conf exists, write one from the current plan
            final AuditModelController newconf = new AuditModelController();
            newconf.applyAuditConfiguration(getConfigurationModel());
            newconf.writeConfiguration(this.geometry.defaultProjectConfigurationFile);
        }
        
    }

    /**
     * Current configuration files geometry.
     * <p>
     * Gives the configuration files path.
     */
    @objid ("47824921-74a7-4ae6-b1af-7b20c48425eb")
    private static class Geometry {
        /**
         * The currently used configuration file. Might be <code>null</code>.
         */
        @objid ("191c3cd7-81e4-417d-a6ab-2adb77b5748f")
        private File configurationFile;

        /**
         * The default configuration file stored in the opened project.
         */
        @objid ("4bc34c79-271c-43ba-8f29-7b6494ae5c7c")
        private File defaultProjectConfigurationFile;

        @objid ("0702a158-b73f-4865-a585-d47019cbaff3")
        public  Geometry() {
            // nothing
        }

        /**
         * Change the currently used audit configuration file.
         * @param confFile an audit configuration file. Set to <code>null</code> to make the default configuration file used.
         */
        @objid ("ccb78196-44cb-4932-b7b4-fab715a79a85")
        public void setConfigurationFile(final File confFile) {
            this.configurationFile = confFile;
        }

        /**
         * Find the first configuration file available in the following ordered list:
         * <ul>
         * <li>The set configuration file
         * <li>The default project configuration file
         * <li>The set factory settings file
         * <li>The default factory settings file.
         * @return the configuration file to read.
         */
        @objid ("20c85e9a-c398-427c-aead-6d63572434f1")
        public File findFirstConfigurationFile() {
            final File[] ff = new File[] { getConfigurationFile(), getDefaultProjectConfigurationFile() };
            for (final File f : ff) {
                if (f != null && f.isFile()) {
                    return f;
                }
            }
            return null;
        }

        /**
         * Return File descriptor of the currently used audit configuration file.
         * <p>
         * When no specific configuration file is defined, defaults to {@link #defaultProjectConfigurationFile}.
         * </p>
         * @return an audit configuration file.
         */
        @objid ("9a5449ce-008e-4055-aafa-bd2db6416caf")
        public File getConfigurationFile() {
            if (this.configurationFile != null) {
                return this.configurationFile;
            }
            return this.defaultProjectConfigurationFile;
        }

        /**
         * Return File descriptor of the currently used default configuration file.
         * <p>
         * When no specific default configuration file is defined, returns <code>null</code>.
         * </p>
         * @return an audit configuration file. Might be <code>null</code>.
         */
        @objid ("1a4dd68f-a0e7-4c81-914e-bf3bdcf12be0")
        public File getDefaultProjectConfigurationFile() {
            if (this.defaultProjectConfigurationFile != null && this.defaultProjectConfigurationFile.isFile()) {
                return this.defaultProjectConfigurationFile;
            }
            return null;
        }

        @objid ("683e932c-a337-404e-8170-f82f2b0a955d")
        public File initForProject(final IGProject project) {
            return this.defaultProjectConfigurationFile = project.getPfs().getProjectDataPath().resolve(".config/auditconfiguration.properties").toFile();
        }

    }

}
