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
package org.modelio.xmi.impl;

import java.io.File;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.modelio.api.module.IModule;
import org.modelio.gproject.project.AbstractGProject;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.platform.core.navigate.IModelioNavigationService;
import org.modelio.vcore.session.api.transactions.ITransaction;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.xmi.api.ExportConfiguration;
import org.modelio.xmi.api.FormatExport;
import org.modelio.xmi.api.IXMIService;
import org.modelio.xmi.api.ImportConfiguration;
import org.modelio.xmi.generation.ExportServices;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.gui.report.ReportManager;
import org.modelio.xmi.gui.report.ReportModel;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.reverse.ImportServices;
import org.modelio.xmi.reverse.ReverseProperties;
import org.modelio.xmi.util.AbortProcessException;
import org.modelio.xmi.util.XMIFileUtils;

/**
 * This class implements the services provided by XMI
 * 
 * @author ebrosse
 */
@objid ("f00a8a73-a896-45bb-8fd1-659d9d7ffd45")
public class XMIService implements IXMIService {
    @objid ("41ca83f5-53ac-4f40-8b67-024028290836")
    private ImportServices importService = null;

    @objid ("3b12b4f2-ddf6-4520-b9f6-7185b058b9a3")
    private ExportServices exportService = null;

    @objid ("d6992648-03fe-45e2-8516-e6ac2ac98026")
    private void cancelProcess(Shell shell) {
        if (shell != null && !System.getProperty("os.name").equals("Linux")) {
            shell.dispose();
        }
        
        Xmi.LOG.error(Xmi.PLUGIN_ID, Xmi.I18N.getString("info.import.result_failed"));
        
    }

    @objid ("c172d488-4dab-4ea0-b7db-e65a4ef210c1")
    private void exportModel(final ExportConfiguration configuration) {
        Shell shell = null;
        String xmiFilePath = configuration.getXmiFile().getAbsolutePath();
        
        GenerationProperties genProp = GenerationProperties.getInstance();
        genProp.setReportModel(ReportManager.getNewReport());
        genProp.setRootElements(configuration.getEntryPoint());
        genProp.setFilePath(configuration.getXmiFile());
        
        try {
        
            genProp.setRootElements(configuration.getEntryPoint());
            genProp.setRoundtripEnabled(configuration.exportedAnotation());
        
            this.exportService.createEcoreModel(null);
        
            if (!configuration.getVersionExport().equals(FormatExport.EMF300)) {
                XMIFileUtils.changeToUML(xmiFilePath);
            }
        
            Xmi.LOG.error(Xmi.I18N.getString("info.export.result_done"));
        
        } catch (AbortProcessException e) {
            cancelProcess(shell);
        } catch (Exception e) {
            cancelProcess(shell);
            Xmi.LOG.error(e);
        } finally {
            ReportModel reportModel = genProp.getReportModel();
            if (!reportModel.isEmpty()) {
                ReportManager.writeReport(reportModel, genProp.getLogFilePath());
            }
        }
        
    }

    @objid ("3bef3178-045c-4eb1-84ab-13ac13fa4462")
    @Override
    public void exportXMIFile(final ExportConfiguration configuration, IProgressMonitor monitor, IMModelServices mmService, MMetamodel metamodel, IModelioNavigationService mns) throws Exception {
        if (configuration.getEntryPoint() == null) {
            Xmi.LOG.error(Xmi.PLUGIN_ID, Xmi.I18N.getString("error.nullGivenParameter"));
        } else {
            if (configuration.getXmiFile() != null) {
                initExportService(mmService, metamodel, mns);
                exportModel(configuration);
            } else {
                Xmi.LOG.error(Xmi.PLUGIN_ID, Xmi.I18N.getString("error.invalidFilePath"));
            }
        }
        
    }

    @objid ("708692ba-0a7b-4ac7-8376-015c89fa18b9")
    private void importModel(File xmiFile, MObject owner) {
        Shell shell = Display.getDefault().getActiveShell();
        ReverseProperties revProp = ReverseProperties.getInstance();
        revProp.setReportModel(ReportManager.getNewReport());
        
        try (ITransaction t = AbstractGProject.getProject(owner).getSession().getTransactionSupport().createTransaction("Import")) {
        
            Resource resource = this.importService.getResource(xmiFile);
        
            if (resource != null) {
                this.importService.importEcoreModel(resource, null, shell);
            }
            t.commit();
        
        } catch (AbortProcessException e) {
            // cancelProcess(shell);
        } catch (Exception e) {
            // cancelProcess(shell);
            Xmi.LOG.error(Xmi.PLUGIN_ID, e);
        } finally {
            ReportModel reportModel = revProp.getReportModel();
            if (!reportModel.isEmpty()) {
                ReportManager.writeReport(reportModel, revProp.getLogFilePath());
            }
        }
        
    }

    @objid ("52d1a467-d5d9-4bff-ad95-1813be619061")
    private void initExportService(IMModelServices mmService, MMetamodel metamodel, IModelioNavigationService mns) {
        GenerationProperties genProp = GenerationProperties.getInstance();
        genProp.initialize(mmService, metamodel, mns);
        
        if (this.exportService == null) {
            this.exportService = new ExportServices(null);
        }
        
    }

    @objid ("20b6436b-bd18-4bb7-bfea-c3e94724baa9")
    private void initImportService(IMModelServices mmService, MMetamodel metamodel, IModelioNavigationService mns) {
        ReverseProperties.getInstance().initialize(mmService, metamodel, mns);
        if (this.importService == null) {
            this.importService = new ImportServices();
        }
        
    }

    @objid ("2aee3059-41b4-4fb3-b908-cb359d33485d")
    @Override
    public void importXMIModel(final ImportConfiguration configuration, IProgressMonitor monitor, IMModelServices mmService, MMetamodel metamodel, IModelioNavigationService mns) {
        if (configuration.getXmiFile() != null
                && configuration.getXmiFile().isFile()) {
            initImportService(mmService, metamodel, mns);
            ReverseProperties.getInstance().setFilePath(configuration.getXmiFile());
            ReverseProperties.getInstance().setRootElement((Package) configuration.getOwner());
            importModel(configuration.getXmiFile(), configuration.getOwner());
        } else {
            Xmi.LOG.error(Xmi.PLUGIN_ID, Xmi.I18N.getString("error.invalidFilePath"));
        }
        
    }

    @objid ("b3a3e446-0248-4c94-a593-9ca97b3ec5a4")
    @Override
    public void importXMIProfile(final ImportConfiguration configuration, IProgressMonitor monitor, IMModelServices mmService, MMetamodel metamodel, IModelioNavigationService mns) {
        if (configuration.getOwner() != null && configuration.getOwner() instanceof IModule) {
            if (configuration.getXmiFile() != null && configuration.getXmiFile().isFile()) {
                initImportService(mmService, metamodel, mns);
                ReverseProperties.getInstance().setFilePath(configuration.getXmiFile());
                importProfile(configuration.getXmiFile(), configuration.getOwner());
            } else {
                Xmi.LOG.error(Xmi.PLUGIN_ID, Xmi.I18N.getString("error.invalidFilePath"));
            }
        } else {
            Xmi.LOG.error(Xmi.PLUGIN_ID, Xmi.I18N.getString("error.invalidOwner"));
        
        }
        
    }

    @objid ("9794308c-aca2-4f51-9bfb-8ae030ae70d8")
    @Override
    public void exportXMIProfile(final ExportConfiguration configuration, IProgressMonitor monitor, IMModelServices mmService, MMetamodel metamodel, IModelioNavigationService mns) throws Exception {
        if (configuration.getEntryPoint() == null || !(configuration.getEntryPoint() instanceof Profile)) {
            Xmi.LOG.error(Xmi.PLUGIN_ID, Xmi.I18N.getString("error.nullGivenParameter"));
        } else {
            if (configuration.getXmiFile() != null) {
                initExportService(mmService, metamodel, mns);
                exportProfile(configuration);
            } else {
                Xmi.LOG.error(Xmi.PLUGIN_ID, Xmi.I18N.getString("error.invalidFilePath"));
            }
        }
        
    }

    @objid ("bb172e86-1bb7-4d28-8da3-0ee4eb8b0169")
    private void exportProfile(final ExportConfiguration configuration) {
        Shell shell = null;
        String xmiFilePath = configuration.getXmiFile().getAbsolutePath();
        
        GenerationProperties genProp = GenerationProperties.getInstance();
        genProp.setReportModel(ReportManager.getNewReport());
        
        genProp.setRootElements(configuration.getEntryPoint());
        genProp.setFilePath(configuration.getXmiFile());
        
        try {
        
            genProp.setRootElements(configuration.getEntryPoint());
            genProp.setRoundtripEnabled(configuration.exportedAnotation());
        
            this.exportService.createEcoreProfile(null);
        
            if (!configuration.getVersionExport().equals(FormatExport.EMF300)) {
                XMIFileUtils.changeToUML(xmiFilePath);
            }
        
            Xmi.LOG.error(Xmi.PLUGIN_ID, Xmi.I18N.getString("info.export.result_done"));
        
        } catch (AbortProcessException e) {
            cancelProcess(shell);
        } catch (Exception e) {
            cancelProcess(shell);
            Xmi.LOG.error(e);
        } finally {
            ReportModel reportModel = genProp.getReportModel();
            if (!reportModel.isEmpty()) {
                ReportManager.writeReport(reportModel, genProp.getLogFilePath());
            }
        }
        
    }

    @objid ("249d893d-451a-491a-9cbf-64a3dbe2ed94")
    private boolean importProfile(final File xmiFile, final MObject module) {
        boolean error = false;
        
        Shell shell = Display.getDefault().getActiveShell();
        
        ReverseProperties revProp = ReverseProperties.getInstance();
        revProp.setReportModel(ReportManager.getNewReport());
        
        try (ITransaction t = AbstractGProject.getProject(module).getSession().getTransactionSupport().createTransaction("Import")) {
        
            Resource resource = this.importService.getResource(xmiFile);
        
            if (resource != null) {
                error = this.importService.importEcoreProfile(resource, null, shell);
            }
            t.commit();
        } catch (AbortProcessException e) {
            // cancelProcess(shell);
        } catch (Exception e) {
            error = true;
            // cancelProcess(shell);
            Xmi.LOG.error(e);
        } finally {
            ReportModel reportModel = revProp.getReportModel();
            if (!reportModel.isEmpty()) {
                ReportManager.writeReport(reportModel, revProp.getLogFilePath());
            }
        }
        return error;
    }

}
