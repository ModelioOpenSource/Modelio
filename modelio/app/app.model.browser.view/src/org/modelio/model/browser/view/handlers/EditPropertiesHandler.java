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

import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.platform.core.activation.IActivationService;
import org.modelio.platform.model.ui.swt.SelectionHelper;

/**
 * Handler for the "Properties..." command.
 * 
 * @author phv
 */
@objid ("16801af5-a67f-45ee-8d8e-1d6fadd0a22a")
public class EditPropertiesHandler {
    @objid ("d3b919fb-519d-4f83-84f4-f87f3a82ebda")
    @Execute
    public void execute(@Named(IServiceConstants.ACTIVE_SELECTION) final IStructuredSelection selection, IActivationService activationService) {
        final Element e = SelectionHelper.getFirst(selection, Element.class);
        
        if (e!=null) {
            activationService.editProperties(e);
        }
    }

    @objid ("ed0592e3-2967-4e71-9442-026f2ae34faa")
    @CanExecute
    public boolean canExecute(@Named(IServiceConstants.ACTIVE_SELECTION) ISelection selection) {
        return SelectionHelper.size(selection) == 1;
    }

}
