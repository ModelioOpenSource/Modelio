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

package org.modelio.diagram.browser.handlers;

import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.modelio.app.core.activation.IActivationService;
import org.modelio.core.ui.swt.SelectionHelper;
import org.modelio.metamodel.uml.infrastructure.Element;

/**
 * Handler for the "select in explorer" menu item.
 */
@objid ("5d7f6f7e-8ee7-4904-833b-23c3ea5f7c88")
public class EditPropertiesHandler {
    @objid ("2cfe5f36-6fe9-49c3-b800-e4e117b0a258")
    @Execute
    public void execute(@Named(IServiceConstants.ACTIVE_SELECTION) final IStructuredSelection selection, IActivationService activationService) {
        final Element e = SelectionHelper.getFirst(selection, Element.class);
        
        if (e != null) {
            activationService.editProperties(e);
        }
    }

    @objid ("ede875a0-10d3-4c31-9a0e-358ca017cbb0")
    @CanExecute
    public boolean canExecute(@Named(IServiceConstants.ACTIVE_SELECTION) ISelection selection) {
        return SelectionHelper.size(selection) == 1;
    }

}
