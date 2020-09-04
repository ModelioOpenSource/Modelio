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

package org.modelio.diagram.editor.handlers;

import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.jface.viewers.ISelection;
import org.modelio.app.core.activation.IActivationService;
import org.modelio.core.ui.swt.SelectionHelper;
import org.modelio.metamodel.uml.infrastructure.Element;

/**
 * Handler for the "Properties..." command.
 * 
 * @author phv
 */
@objid ("4a0103f9-5416-4b50-b8e2-16c314934055")
public class EditPropertiesHandler {
    @objid ("dbe1cd32-9e2d-4777-b0fe-64228a1507d0")
    @Execute
    public void execute(@Named(IServiceConstants.ACTIVE_SELECTION) ISelection selection, IActivationService activationService) {
        final GraphicalEditPart editPart = SelectionHelper.getFirst(selection, GraphicalEditPart.class);
        
        if (editPart != null) {
            final Element elt = editPart.getAdapter(Element.class);
            if (elt != null) {
                activationService.editProperties(elt);
            }
        }
    }

    @objid ("6c0cce9c-7d6f-4104-9a2c-38e8bfd9d5c8")
    @CanExecute
    public boolean canExecute(@Named(IServiceConstants.ACTIVE_SELECTION) ISelection selection) {
        final GraphicalEditPart editPart = SelectionHelper.getFirst(selection, GraphicalEditPart.class);
        return editPart != null && editPart.getAdapter(Element.class) != null;
    }

}
