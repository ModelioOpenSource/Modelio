/* 
 * Copyright 2013-2018 Modeliosoft
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

import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.modelio.model.browser.view.BrowserView;
import org.modelio.model.browser.view.panel.ModelBrowserPanelProvider;

/**
 * Handler class for the "open browser on element" command.
 */
@objid ("b073bbfb-4a9d-11e2-a4d3-002564c97630")
public class OpenNewBrowserHandler {
    @objid ("4b22b0e5-e5a5-4b04-9060-a3293f496c99")
    private static int cpt = 1;

    @objid ("b38c7812-4a9d-11e2-a4d3-002564c97630")
    @Execute
    public void execute(MApplication application, EPartService partService, EModelService modelService, @Named(IServiceConstants.ACTIVE_SELECTION) final IStructuredSelection selection) {
        // Open with one selected element only
        if (selection.size() != 1) {
            return;
        }
        MPart part = (MPart) modelService.find(BrowserView.PART_ID, application);
        
        // Duplicate part
        MPart newPart = (MPart) EcoreUtil.copy((EObject) part);
        
        // Add a number to the clone's id, to differentiate it from the initial part
        newPart.setElementId(part.getElementId() + "." + OpenNewBrowserHandler.cpt);
        OpenNewBrowserHandler.cpt++;
        
        // Set the same parent as the old part
        newPart.setParent(part.getCurSharedRef().getParent());
        
        // Set it visible
        newPart.setToBeRendered(true);
        
        // Add the new part on top of the stack
        newPart = partService.showPart(newPart, PartState.ACTIVATE);
        
        // Switch the root element
        BrowserView browserView = (BrowserView) newPart.getObject();
        if (browserView != null) {
            ((ModelBrowserPanelProvider) browserView.getContributedPanel()).setLocalRoots(selection.toList());
        }
    }

}
