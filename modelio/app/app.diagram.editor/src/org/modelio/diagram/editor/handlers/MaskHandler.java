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
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Named;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.modelio.diagram.elements.common.abstractdiagram.AbstractDiagramEditPart;

/**
 * Mask element from diagram handler.
 */
@objid ("65b21862-33f7-11e2-95fe-001ec947c8cc")
public class MaskHandler {
    @objid ("65b21863-33f7-11e2-95fe-001ec947c8cc")
    @Execute
    public void execute(@Named (IServiceConstants.ACTIVE_SELECTION) ISelection selection) {
        List<GraphicalEditPart> selected = new ArrayList<>();
        if (selection instanceof IStructuredSelection) {
            for (Object selectedObject : ((IStructuredSelection) selection).toList()) {
                if (selectedObject instanceof GraphicalEditPart) {
                    selected.add((GraphicalEditPart) selectedObject);
                }
            }
        }
        if (selected.isEmpty()) {
            return;
        }
        
        Command compound = buildGefCommand(selected);
        
        if (compound.canExecute()) {
            selected.get(0).getViewer().getEditDomain().getCommandStack().execute(compound);
        }
        
    }

    @objid ("7a804046-5e25-11e2-a8be-00137282c51b")
    @CanExecute
    public boolean canExecute(@Named (IServiceConstants.ACTIVE_SELECTION) ISelection selection) {
        List<GraphicalEditPart> selected = new ArrayList<>();
        if (selection instanceof IStructuredSelection) {
            for (Object selectedObject : ((IStructuredSelection) selection).toList()) {
                if (selectedObject instanceof GraphicalEditPart && !(selectedObject instanceof AbstractDiagramEditPart) && !(selectedObject.getClass().getSimpleName().equals("CompositionLinkEditPart"))) {
                    selected.add((GraphicalEditPart) selectedObject);
                }
            }
        }
        
        Command compound = buildGefCommand(selected);
        return compound.canExecute();
    }

    @objid ("d0296de7-2d80-4b76-8a43-d795fcbfee42")
    private Command buildGefCommand(List<GraphicalEditPart> selected) {
        GroupRequest deleteReq = new GroupRequest(RequestConstants.REQ_DELETE);
        deleteReq.setEditParts(selected);
        
        CompoundCommand compound = new CompoundCommand("Mask");
        for (EditPart editPart : selected) {
            Command cmd = editPart.getCommand(deleteReq);
            if (cmd != null) {
                compound.add(cmd);
            }
        }
        return compound.unwrap();
    }

}
