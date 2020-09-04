/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.app.ramcs.handlers;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
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
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.progress.IProgressService;
import org.modelio.api.module.IModule;
import org.modelio.app.project.core.services.IProjectService;
import org.modelio.app.ramcs.edition.EditRamcDialog;
import org.modelio.app.ramcs.edition.RamcModel;
import org.modelio.app.ramcs.edition.ViewRamcDialog;
import org.modelio.app.ramcs.plugin.AppRamcs;
import org.modelio.gproject.gproject.GProject;
import org.modelio.gproject.ramc.core.packaging.IModelComponentContributor;
import org.modelio.gproject.ramc.core.packaging.RamcPackager;
import org.modelio.mda.infra.service.IModuleService;
import org.modelio.mda.infra.service.IRTModule;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.module.modelermodule.api.default_.standard.artifact.ModelComponentArchive;
import org.modelio.ui.progress.ModelioProgressAdapter;
import org.modelio.vcore.session.api.ICoreSession;

@objid ("14d86a9c-b9f8-447c-acb5-559c2409425f")
public class PackageRamcHandler {
    @objid ("f897b984-52e5-4ac1-a279-9309ddd4a2c6")
    private static final String RAMCFILEEXTENSION = ".ramc";

    @objid ("ca4fd202-417b-4b44-8e12-d01a6b4531b1")
    @Execute
    public void execute(@Named (IServiceConstants.ACTIVE_SHELL) final Shell shell, @Named (IServiceConstants.ACTIVE_SELECTION) final IStructuredSelection selection, IProjectService projectService, IModuleService moduleService, IProgressService progressService) {
        if (selection.size() == 1 && selection.getFirstElement() instanceof Artifact) {
        
            Artifact ramc = (Artifact) selection.getFirstElement();
            if (!ramc.isStereotyped(ModelComponentArchive.MdaTypes.STEREOTYPE_ELT)) {
                return;
            }
        
            ICoreSession session = projectService.getSession();
            if (session == null) {
                return;
            }
        
            RamcModel model = new RamcModel(projectService.getOpenedProject().getProjectFileStructure().getProjectPath(), ramc);
            List<IModule> contributorCandidates = new ArrayList<>();
            for (IRTModule m : moduleService.getStartedModules()) {
                if (m.getIModule() != null && m.getIModule().getModelComponentContributor(model) != null) {
                    contributorCandidates.add(m.getIModule());
                }
            }
            model.setContributorCandidates(contributorCandidates);
        
            if (ramc.isModifiable() || ramc.getStatus().isCmsManaged()) {
                EditRamcDialog dialog = new EditRamcDialog(shell, model);
        
                switch (dialog.open()) {
                case IDialogConstants.FINISH_ID: // Package
                    // Contributors to RAMC packaging
                    List<IModelComponentContributor> contributors = new ArrayList<>();
                    for (IRTModule m : moduleService.getStartedModules()) {
                        if (m.getIModule() != null) {
                            IModelComponentContributor contributor = m.getIModule().getModelComponentContributor(model);
                            if (contributor != null && model.getContributingModules().containsKey(m.getName())) {
                                contributors.add(contributor);
                            }
                        }
                    }
                    doPackageRamc(projectService.getOpenedProject(), model, shell, contributors, progressService);
                    break;
                default:// Cancel
                    break;
                }
        
            } else {
                new ViewRamcDialog(shell, model).open();
            }
        }
    }

    @objid ("b56ca33d-98d1-4c99-b7cd-5d55495d62bb")
    @CanExecute
    public boolean canExecute(@Named (IServiceConstants.ACTIVE_SELECTION) final IStructuredSelection selection) {
        if (selection.size() == 1 && selection.getFirstElement() instanceof Artifact) {
            return ((Artifact) selection.getFirstElement()).isStereotyped(ModelComponentArchive.MdaTypes.STEREOTYPE_ELT);
        } else {
            return false;
        }
    }

    @objid ("28c6061b-073e-4337-86d8-178f3550b2d7")
    private void doPackageRamc(final GProject gproject, final RamcModel model, Shell shell, final List<IModelComponentContributor> contributors, IProgressService progressService) {
        final Path archivePath = promptUser(gproject, model, shell);
        
        if (archivePath == null) {
            return; // the user cancelled the operation
        }
        
        final boolean fork = true; // run the task in a separate thread
        final boolean cancelable = true; // the task can be cancelled, the
        // button
        // cancel is available
        
        try {
            progressService.run(fork, cancelable, new IRunnableWithProgress() {
        
                @Override
                public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                    try {
                        ModelioProgressAdapter adaptedMonitor = new ModelioProgressAdapter(monitor);
                        RamcPackager packager = new RamcPackager(gproject, model.getArtifact(), archivePath, contributors);
                        packager.run(adaptedMonitor);
                    } catch (IOException e) {
                        AppRamcs.LOG.warning(e);
                    }
                }
        
            });
        } catch (InvocationTargetException | InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    @objid ("951b1841-6663-4273-a472-78aeb274c344")
    private String makeDefaultName(RamcModel model) {
        String normalizedName = model.getName().replace(" ", "_");
        String normalizedVersion = model.getVersion().getMajorVersion() + "." + model.getVersion().getMinorVersion() + "."
                + model.getVersion().getBuildVersion();
        return normalizedName + "_" + normalizedVersion + PackageRamcHandler.RAMCFILEEXTENSION;
    }

    @objid ("743247fa-e5b3-4f8f-9313-38d437306982")
    private Path promptUser(final GProject gproject, final RamcModel model, Shell shell) {
        // Create and configure the file chooser dialog
        String defaultName = makeDefaultName(model);
        final FileDialog fileChooser = new FileDialog(shell, SWT.SAVE | SWT.SINGLE);
        fileChooser.setFilterPath(gproject.getProjectFileStructure().getProjectPath().toString());
        fileChooser.setFileName(defaultName);
        
        while (true) {
            String filename = fileChooser.open();
            if (filename == null) {
                // User cancelled operation
                return null;
            }
        
            Path choosenPath = Paths.get(filename);
            if (Files.exists(choosenPath)) {
                boolean overwrite = MessageDialog.openQuestion(shell,
                        AppRamcs.I18N.getString("EditRamcDialog.ConfirmOverwrite.title"),
                        AppRamcs.I18N.getMessage("EditRamcDialog.ConfirmOverwrite.message", choosenPath.toString()));
                if (overwrite) {
                    try {
                        Files.delete(choosenPath);
                        return choosenPath;
                    } catch (IOException e) {
                        // TODO inform the user that the file cannot be overwritten
                        AppRamcs.LOG.error(e);
                    }
                }
            } else {
                return choosenPath;
            }
        }
    }

}
