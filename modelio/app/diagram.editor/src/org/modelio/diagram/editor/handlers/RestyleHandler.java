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

package org.modelio.diagram.editor.handlers;

import java.util.List;
import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.modelio.diagram.editor.tools.ClonePropertiesSelectionTool;

@objid ("65c52afc-33f7-11e2-95fe-001ec947c8cc")
public class RestyleHandler {
    @objid ("65c52afd-33f7-11e2-95fe-001ec947c8cc")
    @Execute
    public void execute(@Named(IServiceConstants.ACTIVE_SELECTION) ISelection selection) {
        GraphicalEditPart primarySelection = null;
        if (selection instanceof IStructuredSelection) {
            List<?> selectedObjects = ((IStructuredSelection) selection).toList();
            for (Object selectedObject : selectedObjects) {
                if (selectedObject instanceof GraphicalEditPart) {
                    GraphicalEditPart editPart = (GraphicalEditPart) selectedObject;
                    if (editPart.getSelected() == EditPart.SELECTED_PRIMARY) {
                        primarySelection = editPart;
                        break;
                    }
                }
            }
        }
        
        // Configure the edit domain
        // Set the active and default tool
        if (primarySelection != null) {
            primarySelection.getViewer()
            .getEditDomain()
            .setActiveTool(new ClonePropertiesSelectionTool(primarySelection));
        }
    }

    @objid ("ee240366-c24a-487c-803a-e8763efec320")
    @CanExecute
    public boolean canExecute(@Named(IServiceConstants.ACTIVE_SELECTION) ISelection selection) {
        GraphicalEditPart primarySelection = null;
        if (selection instanceof IStructuredSelection) {
            List<?> selectedObjects = ((IStructuredSelection) selection).toList();
            for (Object selectedObject : selectedObjects) {
                if (selectedObject instanceof GraphicalEditPart) {
                    GraphicalEditPart editPart = (GraphicalEditPart) selectedObject;
                    if (editPart.getSelected() == EditPart.SELECTED_PRIMARY) {
                        primarySelection = editPart;
                    } else {
                        // more than one element selected, deactivate the handler
                        return false;
                    }
                }
            }
        }
        return (primarySelection != null);
    }

}
