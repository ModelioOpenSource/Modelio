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

package org.modelio.model.browser.view.handlers;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.modelio.app.project.core.services.IProjectService;
import org.modelio.metamodel.diagrams.DiagramSet;
import org.modelio.metamodel.uml.infrastructure.AbstractProject;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.metamodel.uml.statik.Link;
import org.modelio.model.browser.view.plugin.BrowserViewActivator;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.session.api.transactions.ITransaction;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Basic "delete element" handler.
 * Available only when the selected elements are modifiable.
 */
@objid ("96ed3002-13a8-11e2-8060-002564c97630")
public class DeleteElementHandler {
    @objid ("9b72925a-13a8-11e2-8060-002564c97630")
    @Inject
    protected IProjectService projectService;

    /**
     * Available only when the selected elements are modifiable.
     * 
     * @param selection the current modelio selection.
     * @return true if the handler can be executed.
     */
    @objid ("9b72e083-13a8-11e2-8060-002564c97630")
    @CanExecute
    public final boolean canExecute(@Named(IServiceConstants.ACTIVE_SELECTION) final Object selection) {
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
            if (! canDeleteElement(element)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Delete the currently selected elements.
     * 
     * @param selection the current modelio selection.
     */
    @objid ("9b744038-13a8-11e2-8060-002564c97630")
    @Execute
    public final void execute(@Named(IServiceConstants.ACTIVE_SELECTION) final Object selection) {
        // Sanity checks
        if (this.projectService.getSession() == null) {
            return;
        }
        
        // Must have at least an element
        List<MObject> selectedElements = getSelectedElements(selection);
        if (selectedElements.isEmpty()) {
            return;
        }
        
        
        String transactionName = MessageFormat.format("Delete {0,choice,0#nothing|1#\"{1}\" {2}|1<{0} elements}."
                , selectedElements.size()
                , selectedElements.get(0).getName()
                , selectedElements.get(0).getMClass().getName());
        
        BrowserViewActivator.LOG.debug(transactionName);
        
        try (ITransaction t = this.projectService.getSession().getTransactionSupport().createTransaction(transactionName)) {
            for (MObject element : selectedElements) {
                if (canDeleteElement(element)) {
                    element.delete();
                }
            }
            t.commit();
        }
    }

    @objid ("9b748e5e-13a8-11e2-8060-002564c97630")
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

    @objid ("cef72ab7-4ba3-4245-9a60-cd41168a1896")
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
