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
package org.modelio.model.property.handlers;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Named;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.model.property.stereotype.chooser.StereotypeChooserDriver;
import org.modelio.platform.model.ui.dialogs.elementChooser.ElementChooserDlg;
import org.modelio.platform.project.services.IProjectService;
import org.modelio.vcore.session.api.ICoreSession;

/**
 * Handler for the 'Add stereotype' button.
 */
@objid ("cd69186a-575d-480e-8596-069190919332")
public class AddStereotypeHandler {
    @objid ("197a602d-0c2d-4a7c-811d-76ce740d7b10")
    @Execute
    public void execute(Shell parentShell, IProjectService projectService, IMModelServices modelServices, @Named(IServiceConstants.ACTIVE_SELECTION) final IStructuredSelection selection) {
        ICoreSession session = projectService.getSession();
        createStereotype(parentShell, getSelectedElement(selection), session, modelServices);
        
    }

    @objid ("f829db0f-b563-4d08-93bf-65df0d2a0eec")
    @CanExecute
    public final boolean canExecute(@Named(IServiceConstants.ACTIVE_SELECTION) final IStructuredSelection selection) {
        ModelElement element = getSelectedElement(selection);
        return element != null && element.getStatus().isModifiable();
    }

    @objid ("32afb041-5795-416b-b5ae-35d7c43aab36")
    private ModelElement getSelectedElement(IStructuredSelection selection) {
        if (selection != null && selection.size() == 1) {
            Object first = selection.getFirstElement();
            if (first instanceof ModelElement) {
                return (ModelElement) first;
            } else if (first instanceof IAdaptable) {
                return ((IAdaptable) first).getAdapter(ModelElement.class);
            }
        }
        return null;
    }

    @objid ("376b9825-940b-4d2e-b4a6-92d33c71cd7a")
    private Stereotype createStereotype(final Shell parentShell, final ModelElement parent, ICoreSession session, IMModelServices modelServices) {
        StereotypeChooserDriver driver = new StereotypeChooserDriver(session, modelServices, null);
        ElementChooserDlg dialog = new ElementChooserDlg(parentShell, driver, parent);
        
        // Don't return from open() until window closes
        dialog.setBlockOnOpen(true);
        
        // Open the main window
        dialog.open();
        return driver.getCreatedStereotype();
    }

}
