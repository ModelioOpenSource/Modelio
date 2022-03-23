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
package org.modelio.app.model.imp.handler;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Inject;
import javax.inject.Named;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.progress.IProgressService;
import org.modelio.app.model.imp.impl.ModelImportDataModel;
import org.modelio.app.model.imp.impl.ModelImporter;
import org.modelio.app.model.imp.impl.ui.ImportModelDialog;
import org.modelio.app.model.imp.plugin.AppModelImportOrg;
import org.modelio.gproject.fragment.IProjectFragment;
import org.modelio.gproject.gproject.GProject;
import org.modelio.gproject.gproject.GProjectEnvironment;
import org.modelio.gproject.gproject.IGProjectEnv;
import org.modelio.platform.core.ModelioEnv;
import org.modelio.platform.core.navigate.IModelioNavigationService;
import org.modelio.platform.model.ui.swt.SelectionHelper;
import org.modelio.platform.project.services.IProjectService;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MStatus;

/**
 * Handler for the Import command.
 * <p>
 * The command is enabled if all conditions are met:
 * <ul>
 * <li>Only one fragment must be selected
 * <li>One fragment or some model objects must be selected.
 * <li>the selection may contain only one element of each metaclass.
 * <li>all elements must be in the same fragment
 * </ul>
 */
@objid ("a46138fa-c556-4235-82e6-b406b92fc359")
public class ModelImportHandler {
    @objid ("ea307279-3a00-4f16-84a2-e965f87579dc")
    @Inject
    ModelioEnv modelioEnv;

    @objid ("8269f820-4e0c-4406-9a27-dd7757ce9197")
    @Execute
    public void execute(IProjectService projectService, @Named(IServiceConstants.ACTIVE_SELECTION) final IStructuredSelection selection, @Named(IServiceConstants.ACTIVE_SHELL) Shell activeShell, IProgressService progressService, IModelioNavigationService navigator) {
        GProject openedProject = projectService.getOpenedProject();
        
        GProjectEnvironment projectEnv = new GProjectEnvironment()
                .setModulesCache(openedProject.getModuleCache())
                .addMetamodelExtensions(openedProject.getMetamodelExtensions())
                .setRamcCache(modelioEnv.getRamcCachePath());
        
        try (ModelImportDataModel dataModel = new ModelImportDataModel()) {
            if (promptUser(activeShell, projectEnv, dataModel) && dataModel.getElementsToImport().size() > 0) {
                // Import the model...
                ModelImporter importer = new ModelImporter(projectService.getSession(), selection, dataModel);
                progressService.run(true, false, importer);
        
                navigator.fireNavigate(importer.getDoneCopies());
            }
        
        } catch (@SuppressWarnings("unused") InterruptedException e) {
            // Nothing specific to do.
        } catch (Exception e) {
            AppModelImportOrg.LOG.error(e);
            // Nothing specific to do: transaction will be rolled back in the finally block.
        }
        
    }

    /**
     * The command is enabled if all conditions are met:
     * <ul>
     * <li>Only one fragment must be selected
     * <li>One fragment or some model objects must be selected.
     * <li>the selection may contain only one element of each metaclass.
     * <li>all elements must be in the same fragment
     * </ul>
     * @param projectService the project service
     * @param selection the Eclipse selection
     * @return whether the command is available.
     */
    @objid ("1fc787a6-0741-4477-ba75-b336379b5e8a")
    @CanExecute
    public boolean isEnabled(IProjectService projectService, @Named(IServiceConstants.ACTIVE_SELECTION) final IStructuredSelection selection) {
        final GProject openedProject = projectService.getOpenedProject();
        if (openedProject == null) {
            return false;
        }
        
        List<MObject> selObjs = SelectionHelper.toList(selection, MObject.class);
        List<IProjectFragment> selFrags = SelectionHelper.toList(selection, IProjectFragment.class);
        
        // - Only one fragment must be selected
        // - One fragment or some model objects must be selected.
        if (selFrags.size() != 1 && selObjs.isEmpty()) {
            return false;
        }
        
        // - the fragment must be editable.
        if (!selFrags.isEmpty()) {
            for (MObject obj : selFrags.get(0).getRoots()) {
                if (!ModelImportHandler.isEditable(obj)) {
                    return false;
                }
            }
        }
        
        // - the selection may contain only one element of each metaclass.
        // - all elements must be in the same fragment
        // - the fragment must be editable.
        if (!selObjs.isEmpty()) {
            IProjectFragment f = openedProject.getFragment(selObjs.get(0));
            for (MObject obj : selObjs) {
                // - all elements must be in the same fragment
                if (openedProject.getFragment(obj) != f) {
                    return false;
                }
        
                // - the fragment must be editable.
                if (!ModelImportHandler.isEditable(obj)) {
                    return false;
                }
        
                // - the selection may contain only one element of each metaclass.
                for (MObject obj2 : selObjs) {
                    if (obj != obj2 && obj.getMClass() == obj2.getMClass()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @objid ("eda08f72-e5c8-475a-9c13-003ec39fc7e0")
    private boolean promptUser(Shell parentShell, IGProjectEnv projectEnv, ModelImportDataModel dataModel) {
        ImportModelDialog dialog = new ImportModelDialog(parentShell, dataModel, projectEnv);
        
        int code = dialog.open();
        
        if (code == Window.OK) {
            return true;
        } else {
            return false;
        }
        
    }

    @objid ("ab9b20d6-d733-4c27-970d-ed0e9c7e8fb0")
    private static boolean isEditable(MObject obj) {
        MStatus status = obj.getStatus();
        return status.isModifiable() || status.isCmsManaged();
    }

}
