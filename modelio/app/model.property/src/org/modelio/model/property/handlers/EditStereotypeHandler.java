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

import javax.inject.Inject;
import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.modelio.app.project.core.services.IProjectService;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.model.property.stereotype.creator.StereotypeEditor;

@objid ("255b6ee1-ea13-4c00-9417-60e99c2c4a66")
public class EditStereotypeHandler {
    @objid ("ecbea9a7-3763-42bc-a5cf-a2c0c6352dc9")
    @Inject
    private IProjectService projectService;

    /**
     * (non-Javadoc)
     * @see org.eclipse.core.commands.AbstractHandler#isEnabled()
     */
    @objid ("da0edc7c-562d-4811-920d-109a2715a5bc")
    @CanExecute
    public final boolean canExecute(@Named(IServiceConstants.ACTIVE_SELECTION) final IStructuredSelection selection) {
        if (this.projectService.getSession() == null || selection == null) {
            return false;
        }
        Stereotype selectedStereotype = getSelectedStereotype(selection);
        if (selectedStereotype == null || !selectedStereotype.getStatus().isModifiable()) {
            return false;
        }
        return true;
    }

    /**
     * (non-Javadoc)
     * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
     */
    @objid ("fd1a6ee5-8772-4772-9df0-2092d6433cc8")
    @Execute
    public Object execute(@Named(IServiceConstants.ACTIVE_SELECTION) final IStructuredSelection selection, IMModelServices mmServices) {
        StereotypeEditor stereotypeEditor = new StereotypeEditor(this.projectService, mmServices);
        stereotypeEditor.edit(getSelectedStereotype(selection));
        return null;
    }

    @objid ("c9da2e14-79de-4ec1-85a3-18d537c69990")
    private Stereotype getSelectedStereotype(IStructuredSelection selection) {
        if (selection.size() == 1) {
            Object selectedObject = selection.getFirstElement();
            if (selectedObject instanceof Stereotype) {
                return (Stereotype) selectedObject;
            }
        }
        return null;
    }

}
