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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.menu.MItem;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.modelio.app.project.core.services.IProjectService;
import org.modelio.model.browser.view.BrowserView;
import org.modelio.model.browser.view.panel.ModelBrowserPanelProvider;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("175ac7bf-452e-11e2-aeb7-002564c97630")
public class SelectAsRootHandler {
    @objid ("53fd34c3-999c-4d11-a942-3556e7adc453")
    private static final String DIRECTTOOLITEM_SELECTASROOT = "org.modelio.model.browser.view.part.directtoolitem.selectasroot";

    @objid ("1c1d85bf-452e-11e2-aeb7-002564c97630")
    @Inject
    protected IProjectService projectService;

    @objid ("1c2e2f58-452e-11e2-aeb7-002564c97630")
    @CanExecute
    public final boolean canExecute(MPart part, @Named(IServiceConstants.ACTIVE_SELECTION) final Object selection) {
        // Sanity checks
        if (this.projectService.getSession() == null) {
            return false;
        }
        
        if (part == null || !(part.getObject() instanceof BrowserView)) {
            return false;
        }
        
        // Must have at least an element
        List<MObject> selectedElements = SelectAsRootHandler.getSelectedElements(selection);
        return selectedElements.size() > 0;
    }

    /**
     * Set the selected notes as the roots for the BrowserView's tree.
     * 
     * @param part a {@link BrowserView} part.
     * @param selection the current application selection.
     */
    @objid ("1c355376-452e-11e2-aeb7-002564c97630")
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Execute
    public static final void execute(MPart part, @Named(IServiceConstants.ACTIVE_SELECTION) final Object selection, EModelService s) {
        MItem button = (MItem) s.find(SelectAsRootHandler.DIRECTTOOLITEM_SELECTASROOT, part.getToolbar());
        assert (part.getObject() instanceof BrowserView) : "Handler used on a part other than BrowserView!";
        BrowserView view = (BrowserView) part.getObject();
        List selectedElements = SelectAsRootHandler.getSelectedElements(selection);
        if (button != null && button.isSelected()) {
            ((ModelBrowserPanelProvider) view.getContributedPanel()).setLocalRoots(selectedElements);
        } else {
            ((ModelBrowserPanelProvider) view.getContributedPanel()).setLocalRoots(Collections.emptyList());
        }
    }

    @objid ("1c35537f-452e-11e2-aeb7-002564c97630")
    private static List<MObject> getSelectedElements(final Object selection) {
        List<MObject> selectedElements = new ArrayList<>();
        if (selection instanceof MObject) {
            selectedElements.add((MObject) selection);
        } else if (selection instanceof IStructuredSelection && ((IStructuredSelection) selection).size() >= 1) {
            Object[] elements = ((IStructuredSelection) selection).toArray();
            for (Object element : elements) {
                if (element instanceof MObject) {
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

}
