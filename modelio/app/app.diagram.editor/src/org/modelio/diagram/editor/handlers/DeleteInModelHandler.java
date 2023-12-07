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
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Named;
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
import org.modelio.diagram.elements.common.abstractdiagram.AbstractDiagramEditPart;
import org.modelio.diagram.elements.core.commands.DeleteInModelCommand;
import org.modelio.diagram.elements.core.model.IGmModelRelated;
import org.modelio.diagram.elements.drawings.core.IGmDrawing;
import org.modelio.diagram.elements.umlcommon.diagramview.DiagramViewEditPart;
import org.modelio.diagram.elements.umlcommon.diagramview.GmDiagramView;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.platform.model.ui.swt.SelectionHelper;
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
        if (selected.isEmpty())
            return false;
        
        final Command buildCommand = buildCommand(selected);
        return buildCommand.canExecute();
    }

    @objid ("4042b31f-5c31-4215-ab55-b47fb5ed9c2a")
    private List<GraphicalEditPart> getSelectedEditParts(ISelection selection) {
        List<GraphicalEditPart> editParts = SelectionHelper.toList(selection, GraphicalEditPart.class);
        List<GraphicalEditPart> selected = new ArrayList<>(editParts.size());
        
        for (GraphicalEditPart ep : editParts) {
            if (!(ep instanceof DiagramViewEditPart) && ep instanceof AbstractDiagramEditPart)
                continue;
            if (!(ep instanceof DiagramViewEditPart) && ep.getAdapter(AbstractDiagram.class) != null)
                continue;
            selected.add(ep);
        }
        return selected;
    }

    @objid ("efc3ca81-adf5-4130-a7b6-80f811f2a8e3")
    private Command buildCommand(List<GraphicalEditPart> selected) {
        CompoundCommand compound = new CompoundCommand("Delete");
        
        // Get the model elements to delete or to mask
        final List<GraphicalEditPart> toMask = new ArrayList<>(selected.size());
        final Collection<MObject> toDelete = new ArrayList<>(selected.size());
        for (final GraphicalEditPart editPart : selected) {
            final Object model = editPart.getModel();
            if (model instanceof IGmModelRelated) {
                final IGmModelRelated gmModel = (IGmModelRelated) model;
                if (!gmModel.getDiagram().isUserEditable()) {
                    return UnexecutableCommand.INSTANCE;
                }
        
                final MObject el = gmModel.getRelatedElement();
                if (el == null || gmModel instanceof GmDiagramView) {
                    // - mask diagram views, don't delete them
                    // - or This is probably a ghost, we need to mask it
                    toMask.add(editPart);
                } else if (!toDelete.contains(el)) {
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
