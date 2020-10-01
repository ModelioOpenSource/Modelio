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
import java.util.Collection;
import java.util.List;
import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.modelio.diagram.elements.common.abstractdiagram.AbstractDiagramEditPart;
import org.modelio.diagram.elements.core.commands.DeleteInModelCommand;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.drawings.core.IGmDrawing;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Handler for the delete model element command.
 * <p>
 * Deletes the model element represented by the selected diagram elements.
 * Masks graphic elements that does not represent a model element.
 */
@objid ("65afb5f6-33f7-11e2-95fe-001ec947c8cc")
public class DeleteInModelHandler {
    @objid ("65afb5f8-33f7-11e2-95fe-001ec947c8cc")
    @Execute
    public void execute(@Named (IServiceConstants.ACTIVE_SELECTION) ISelection selection) {
        List<GraphicalEditPart> selected = getSelectedEditParts(selection);
        
        if (selected.isEmpty()) {
            return;
        }
        
        Command compound = buildCommand(selected);
        
        // Execute the delete and mask commands
        if (compound.canExecute()) {
            EditDomain editDomain = selected.get(0).getViewer().getEditDomain();
            editDomain.getCommandStack().execute(compound);
        }
    }

    @objid ("7a33f504-5e25-11e2-a8be-00137282c51b")
    @CanExecute
    public boolean canExecute(@Named (IServiceConstants.ACTIVE_SELECTION) ISelection selection) {
        List<GraphicalEditPart> selected = getSelectedEditParts(selection);
        
        final Command buildCommand = buildCommand(selected);
        return !selected.isEmpty() && buildCommand.canExecute();
    }

    @objid ("4042b31f-5c31-4215-ab55-b47fb5ed9c2a")
    private List<GraphicalEditPart> getSelectedEditParts(ISelection selection) {
        List<GraphicalEditPart> selected = new ArrayList<>();
        
        if (selection instanceof IStructuredSelection) {
            for (Object selectedObject : ((IStructuredSelection) selection).toList()) {
                if (selectedObject instanceof GraphicalEditPart && !(selectedObject instanceof AbstractDiagramEditPart)) {
                    Object model = ((GraphicalEditPart) selectedObject).getModel();
                    if (model instanceof GmModel && ((GmModel) model).getRelatedElement() instanceof AbstractDiagram) {
                        continue;
                    }
                    selected.add((GraphicalEditPart) selectedObject);
                }
            }
        }
        return selected;
    }

    @objid ("efc3ca81-adf5-4130-a7b6-80f811f2a8e3")
    private Command buildCommand(List<GraphicalEditPart> selected) {
        CompoundCommand compound = new CompoundCommand("Delete");
        
        // Get the model elements to delete or to mask
        final List<GraphicalEditPart> toMask = new ArrayList<>();
        final Collection<MObject> toDelete = new ArrayList<>();
        for (final GraphicalEditPart editPart : selected) {
            final Object model = editPart.getModel();
            if (model instanceof GmModel) {
                final GmModel gmModel = (GmModel) model;
                if (!gmModel.getDiagram().isUserEditable()) {
                    return UnexecutableCommand.INSTANCE;
                }
        
                final MObject el = gmModel.getRelatedElement();
                if (el == null) {
                    // This is probably a ghost, we need to mask it
                    toMask.add(editPart);
                }
                if (el != null && !toDelete.contains(el)) {
                    toDelete.add(el);
                }
            } else if (model instanceof IGmDrawing) {
                toMask.add(editPart);
            }
        }
        
        if (!toDelete.isEmpty()) {
            // Build the delete command
            final DeleteInModelCommand cmd = new DeleteInModelCommand();
            for (MObject el : toDelete) {
                cmd.addElementToDelete(el);
            }
        
            // Store the command in the compound
            if (cmd.canExecute()) {
                compound.add(cmd);
            }
        }
        
        // Mask all edit parts without an element
        if (!toMask.isEmpty()) {
            GroupRequest deleteReq = new GroupRequest(RequestConstants.REQ_DELETE);
            deleteReq.setEditParts(toMask);
        
            for (EditPart editPart : toMask) {
                Command cmd2 = editPart.getCommand(deleteReq);
                if (cmd2 != null) {
                    // Store the command in the compound
                    compound.add(cmd2);
                }
            }
        }
        return compound.unwrap();
    }

}
