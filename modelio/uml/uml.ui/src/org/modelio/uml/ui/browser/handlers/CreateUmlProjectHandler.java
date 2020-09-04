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

package org.modelio.uml.ui.browser.handlers;

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
import org.modelio.metamodel.diagrams.DiagramSet;
import org.modelio.metamodel.mda.Project;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.model.api.IElementNamerService;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.transactions.ITransaction;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Specific handler for UML project creation.
 */
@objid ("58af3867-9549-4fb0-a37f-fd225ce74825")
public class CreateUmlProjectHandler {
    /**
     * Creates a UML project in the selected editable fragment.
     * 
     * @param selection the current platform selection.
     * @param projectService the project service, to get session and metamodel from.
     * @param selectionService the selection service, to update the platform selection with.
     */
    @objid ("d3295a68-b094-4100-a6b7-449dd2a000dc")
    @Execute
    public final void execute(@Named (IServiceConstants.ACTIVE_SELECTION) final IStructuredSelection selection, IProjectService projectService, IModelioNavigationService selectionService) {
        IProjectFragment fragment = SelectionHelper.getFirst(selection, IProjectFragment.class);
        
        final ICoreSession session = projectService.getSession();
        try (ITransaction t = session.getTransactionSupport().createTransaction("Create UML Project")) {
            MTools mTools = MTools.get(session);
            IElementNamerService namer = mTools.getNamer();
            IStandardModelFactory modelFactory = mTools.getModelFactory(IStandardModelFactory.class);
        
            Project newProject = modelFactory.createProject(fragment.getRepository());
            newProject.setName(namer.getUniqueName(namer.getBaseName(newProject), newProject));
        
            for (Package root : newProject.getModel()) {
                root.setName(namer.getUniqueName(namer.getBaseName(root), root));
            }
        
            DiagramSet diagramRoot = newProject.getDiagramRoot();
            diagramRoot.setName(namer.getBaseName(diagramRoot));
        
            t.commit();
        
            selectionService.fireNavigate(newProject);
        } catch (Exception e) {
            UmlUi.LOG.error("CreateUmlProjectHandler: \n\tCannot create an UML Project");
            UmlUi.LOG.error(e);
        }
    }

    /**
     * @param selection the current platform selection.
     * @param projectService the project service, to get session and metamodel from.
     * @return <code>true</code> if the project can be created, <code>false</code> otherwise.
     */
    @objid ("c73e0239-d003-446a-9e00-e7509b3feff7")
    @CanExecute
    public boolean canExecute(@Named (IServiceConstants.ACTIVE_SELECTION) final IStructuredSelection selection, IProjectService projectService) {
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
        
        for (MObject umlProject : fragment.getRepository().findByClass(projectService.getSession().getMetamodel().getMClass(Project.class), true)) {
            if (umlProject.isValid()) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return true if the fragment is read only.
     */
    @objid ("0c090460-494d-4641-8241-d9b00d7cd143")
    private boolean isReadonly(IProjectFragment fragment) {
        String s = fragment.getProperties().getValue(IProjectFragment.PROP_READ_ONLY);
        return s != null && Boolean.parseBoolean(s);
    }

}
