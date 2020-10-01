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

package org.modelio.diagram.editor.handlers;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.modelio.platform.core.navigate.IModelioNavigationService;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Handler for the "select in UML explorer" command.
 * 
 * @author fpoyer
 */
@objid ("65c78d7f-33f7-11e2-95fe-001ec947c8cc")
public class SelectInExplorerHandler {
    @objid ("65c78d81-33f7-11e2-95fe-001ec947c8cc")
    @Execute
    public void execute(@Named(IServiceConstants.ACTIVE_SELECTION) ISelection iSelection, IModelioNavigationService navigationService) {
        // navigate to the selected editpart element
        navigationService.fireNavigate(getSelection(iSelection));
    }

    @objid ("7a76b6de-5e25-11e2-a8be-00137282c51b")
    @CanExecute
    public boolean canExecute(@Named(IServiceConstants.ACTIVE_SELECTION) ISelection iSelection) {
        return !getSelection(iSelection).isEmpty();
    }

    @objid ("e9027d17-125d-4b03-b525-5d53500dc9eb")
    public List<MObject> getSelection(ISelection iSelection) {
        List<MObject> selectedElements = new ArrayList<>();
        if (iSelection instanceof IStructuredSelection) {
            List<?> selectionAsList = ((IStructuredSelection) iSelection).toList();
            for (Object selectedObject : selectionAsList) {
                if (selectedObject instanceof GraphicalEditPart) {
                    GraphicalEditPart editPart = (GraphicalEditPart) selectedObject;
                    
                    final MObject elt = editPart.getAdapter(MObject.class);
                    if (elt != null)
                        selectedElements.add(elt);
                }
            }
        }
        return selectedElements;
    }

}
