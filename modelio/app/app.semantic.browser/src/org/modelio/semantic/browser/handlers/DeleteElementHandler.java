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
package org.modelio.semantic.browser.handlers;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Inject;
import javax.inject.Named;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.modelio.metamodel.diagrams.DiagramSet;
import org.modelio.metamodel.uml.infrastructure.AbstractProject;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.metamodel.uml.statik.Link;
import org.modelio.platform.project.services.IProjectService;
import org.modelio.semantic.browser.plugin.SemanticBrowser;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.session.api.transactions.ITransaction;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Basic "delete element" handler for the Semantic browser.
 * <p>
 * Available only when the selected elements are modifiable.
 * </p>
 */
@objid ("0b91d137-4a9a-4e68-b789-724577784510")
public class DeleteElementHandler {
    @objid ("1861b3d1-a660-4ab2-9893-c481c2b4f70d")
    @Inject
    protected IProjectService projectService;

    /**
     * Available only when the selected elements are modifiable.
     * @param selection the current modelio selection.
     * @return true if the handler can be executed.
     */
    @objid ("d032fdba-e2da-4a63-bfcd-17504d029f7e")
    @CanExecute
    public final boolean canExecute(@Named (IServiceConstants.ACTIVE_SELECTION) final Object selection) {
        // Sanity checks
        if (this.projectService.getSession() == null) {
            return false;
        }
        
        // Must have at least an element
        List<MObject> selectedElements = getSelectedElements(selection);
        if (selectedElements.isEmpty()) {
            return false;
        }
        for (MObject element : selectedElements) {
            if (!canDeleteElement(element)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Delete the currently selected elements.
     * @param selection the current modelio selection.
     */
    @objid ("c3ddb311-e92d-4aac-9751-72bfb30e3ef4")
    @Execute
    public final void execute(@Named (IServiceConstants.ACTIVE_SELECTION) final Object selection) {
        // Sanity checks
        if (this.projectService.getSession() == null) {
            return;
        }
        
        // Must have at least an element
        List<MObject> selectedElements = getSelectedElements(selection);
        if (selectedElements.isEmpty()) {
            return;
        }
        
        String transactionName = MessageFormat.format("Delete {0,choice,0#nothing|1#\"{1}\" {2}|1<{0} elements}.", selectedElements.size(), selectedElements.get(0).getName(), selectedElements.get(0).getMClass().getName());
        
        SemanticBrowser.LOG.debug(transactionName);
        
        try (ITransaction t = this.projectService.getSession().getTransactionSupport().createTransaction(transactionName)) {
            for (MObject element : selectedElements) {
                if (canDeleteElement(element)) {
                    element.delete();
                }
            }
            t.commit();
        }
        
    }

    @objid ("a49fc5ab-4428-4722-8c75-40d2256743e3")
    private static List<MObject> getSelectedElements(final Object selection) {
        List<MObject> selectedElements = new ArrayList<>();
        if (selection instanceof Element) {
            selectedElements.add((Element) selection);
        } else if (selection instanceof IStructuredSelection && ((IStructuredSelection) selection).size() >= 1) {
            Object[] elements = ((IStructuredSelection) selection).toArray();
            for (Object element : elements) {
                if (element instanceof Association || element instanceof Link) {
                    selectedElements.add(((MObject) element).getCompositionOwner());
                } else if (element instanceof MObject) {
                    selectedElements.add((MObject) element);
                } else if (element instanceof IAdaptable) {
                    final MObject adapter = ((IAdaptable) element).getAdapter(MObject.class);
                    if (adapter != null) {
                        selectedElements.add(adapter);
                    }
                }
            }
        }
        return selectedElements;
    }

    @objid ("2ca060aa-6b27-407f-ba8f-b7b5ee1c0a73")
    private boolean canDeleteElement(MObject element) {
        MObject owner = element.getCompositionOwner();
        // cannot delete root diagram set
        if (element instanceof DiagramSet) {
            return false;
        }
        
        if (element instanceof AbstractProject && element.getStatus().isCmsManaged()) {
            // SVN managed Sub project cannot be deleted with this command:
            // their deletion is not undoable and is immediately SVN committed.
            return false;
        }
        
        if (owner != null && !MTools.getAuthTool().canRemoveFrom(element, owner)) {
            return false;
        }
        return true;
    }

}
