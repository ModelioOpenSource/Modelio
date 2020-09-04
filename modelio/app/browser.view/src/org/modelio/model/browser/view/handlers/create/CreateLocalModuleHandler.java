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

package org.modelio.model.browser.view.handlers.create;

import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.modelio.app.core.navigate.IModelioNavigationService;
import org.modelio.app.project.core.services.IProjectService;
import org.modelio.core.ui.swt.SelectionHelper;
import org.modelio.gproject.fragment.IProjectFragment;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.mmextensions.infrastructure.IInfrastructureModelFactory;
import org.modelio.model.browser.view.plugin.BrowserViewActivator;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.transactions.ITransaction;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Specific handler for LocalModule project creation.
 */
@objid ("844035c1-8101-480d-a5fc-4eb2d9273f8c")
public class CreateLocalModuleHandler {
    /**
     * Creates a local ModuleComponent in the selected editable fragment.
     * 
     * @param selection the current platform selection.
     * @param projectService the project service, to get session and metamodel from.
     * @param selectionService the selection service, to update the platform selection with.
     */
    @objid ("e32980c1-0d2b-4c46-be99-5527119d1678")
    @Execute
    public final void execute(@Named (IServiceConstants.ACTIVE_SELECTION) final IStructuredSelection selection, IProjectService projectService, IModelioNavigationService selectionService) {
        IProjectFragment fragment = SelectionHelper.getFirst(selection, IProjectFragment.class);
        
        final ICoreSession session = projectService.getSession();
        try (ITransaction t = session.getTransactionSupport().createTransaction("create Local Module")) {
            IInfrastructureModelFactory modelFactory = MTools.get(session).getModelFactory(IInfrastructureModelFactory.class);
        
            ModuleComponent localModule = modelFactory.createModuleProject(fragment.getRepository());
            localModule.setName("LocalModule");
        
            t.commit();
        
            selectionService.fireNavigate(localModule);
        } catch (Exception e) {
            BrowserViewActivator.LOG.error("CreateLocalModuleHandler: \n\tCannot create a Local Module");
            BrowserViewActivator.LOG.error(e);
        }
    }

    /**
     * @param selection the current platform selection.
     * @param projectService the project service, to get session and metamodel from.
     * @return <code>true</code> if the project can be created, <code>false</code> otherwise.
     */
    @objid ("91945961-1f4a-499e-9e45-e102516d0a7d")
    @CanExecute
    public boolean canExecute(@Named (IServiceConstants.ACTIVE_SELECTION) final IStructuredSelection selection, IProjectService projectService) {
        // Sanity checks
        if (projectService.getSession() == null) {
            return false;
        }
        
        if (selection.size() != 1) {
            return false;
        }
        
        // Fragment checks
        IProjectFragment fragment = SelectionHelper.getFirst(selection, IProjectFragment.class);
        if (isReadonly(fragment)) {
            return false;
        }
        
        for (MObject impactProject : fragment.getRepository().findByClass(projectService.getSession().getMetamodel().getMClass(ModuleComponent.class), true)) {
            if (impactProject.isValid()) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return true if the fragment is read only.
     */
    @objid ("bb0ad448-1c59-4d7b-9c8d-4216cabad51e")
    private boolean isReadonly(IProjectFragment fragment) {
        String s = fragment.getProperties().getValue(IProjectFragment.PROP_READ_ONLY);
        return s != null && Boolean.parseBoolean(s);
    }

}
