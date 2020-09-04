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

package org.modelio.model.browser.view.handlers;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.modelio.app.project.core.services.IProjectService;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.model.browser.view.BrowserView;
import org.modelio.model.browser.view.IElementNameEditor;
import org.modelio.ui.panel.IPanelProvider;
import org.modelio.vcore.session.api.transactions.ITransaction;

/**
 * Basic "rename element" handler.
 * Available only when the selected element is modifiable.
 */
@objid ("1b968bdb-9e98-4129-9417-b7b36c920677")
public class RenameElementHandler {
    @objid ("3c9fc408-9252-4d95-9c1c-981274601990")
    @Inject
    protected IProjectService projectService;

    /**
     * Available only when the selected elements are modifiable.
     * 
     * @param selection the current modelio selection.
     * @return true if the handler can be executed.
     */
    @objid ("27fbffa8-8fba-405e-953e-b5f821dd00b7")
    @CanExecute
    public final boolean canExecute(@Named(IServiceConstants.ACTIVE_SELECTION) final Object selection) {
        // Sanity checks
        if (this.projectService.getSession() == null) {
            return false;
        }
        
        // Must select an element only
        List<Element> selectedElements = getSelectedElements(selection);
        if (selectedElements.size() != 1) {
            return false;
        }
        
        for (Element element : selectedElements) {
            if (!element.getStatus().isModifiable()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Rename the currently selected elements.
     * 
     * @param selection the current modelio selection.
     */
    @objid ("6c44a544-68f6-41d9-b438-b35b21f158df")
    @Execute
    public final void execute(@Named(IServiceConstants.ACTIVE_SELECTION) final Object selection, @Named(IServiceConstants.ACTIVE_PART) final MPart part) {
        if (! (part.getObject() instanceof BrowserView)) {
            return;
        }
        
        
        // Sanity checks
        if (this.projectService.getSession() == null) {
            return;
        }
        
        // Must have at least an element
        List<Element> selectedElements = getSelectedElements(selection);
        if (selectedElements.isEmpty()) {
            return;
        }
        
        IPanelProvider browserPanel = ((BrowserView) part.getObject()).getContributedPanel();
        if (browserPanel instanceof IElementNameEditor) {
            try (ITransaction t = this.projectService.getSession().getTransactionSupport().createTransaction("Rename element")) {
                Element editedElement = selectedElements.get(0);
        
                ((IElementNameEditor) browserPanel).edit(editedElement);
        
                t.commit();
            }
        }
    }

    @objid ("230e0de8-f50c-4903-a5be-d0b2482114e9")
    private static List<Element> getSelectedElements(final Object selection) {
        List<Element> selectedElements = new ArrayList<>();
        if (selection instanceof Element) {
            selectedElements.add((Element) selection);
        } else if (selection instanceof IStructuredSelection && ((IStructuredSelection) selection).size() >= 1) {
            Object[] elements = ((IStructuredSelection) selection).toArray();
            for (Object element : elements) {
                if (element instanceof Element) {
                    selectedElements.add((Element) element);
                } else if (element instanceof IAdaptable) {
                    final Element adapter = ((IAdaptable) element).getAdapter(Element.class);
                    if (adapter != null) {
                        selectedElements.add(adapter);
                    }
                }
            }
        }
        return selectedElements;
    }

}
