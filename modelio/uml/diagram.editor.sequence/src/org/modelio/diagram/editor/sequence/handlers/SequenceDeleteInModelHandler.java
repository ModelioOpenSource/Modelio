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

package org.modelio.diagram.editor.sequence.handlers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
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
import org.eclipse.swt.widgets.Shell;
import org.modelio.core.ui.swt.SelectionHelper;
import org.modelio.diagram.elements.core.commands.DeleteInModelCommand;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.drawings.core.IGmDrawing;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionFragment;
import org.modelio.metamodel.uml.behavior.interactionModel.Lifeline;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Handler for the delete model element command in Sequence diagrams.
 * <p>
 * Deletes the model element represented by the selected diagram elements. Masks graphic elements that does not represent a model element.
 * </p>
 * <p>
 * InteractionFragments with no lifeline anymore are deleted too.
 * </p>
 */
@objid ("e6a64118-0c34-415a-9ea3-00c7afbbc608")
public class SequenceDeleteInModelHandler {
    /**
     * @return <code>false</code> if the command is to be greyed, <code>true</code> otherwise.
     */
    @objid ("3d0bf0df-898d-45e9-ae50-a08ea3c1cc16")
    @CanExecute
    public boolean canExecute(@Named (IServiceConstants.ACTIVE_SELECTION) ISelection selection, @Named (IServiceConstants.ACTIVE_SHELL) final Shell shell) {
        List<GraphicalEditPart> selected = getSelectedEditParts(selection);
        
        Command cmd = buildCommand(selected);
        return !selected.isEmpty() && cmd.canExecute();
    }

    @objid ("c2b07f0e-c026-4c45-8f53-4cb62f7b962c")
    @Execute
    public void execute(@Named (IServiceConstants.ACTIVE_SELECTION) ISelection selection, @Named (IServiceConstants.ACTIVE_SHELL) final Shell shell) {
        List<GraphicalEditPart> selected = getSelectedEditParts(selection);
        Command cmd = buildCommand(selected);
        
        // Execute the delete and mask commands
        if (cmd != null && cmd.canExecute()) {
            EditDomain editDomain = selected.get(0).getViewer().getEditDomain();
            editDomain.getCommandStack().execute(cmd);
        }
    }

    @objid ("b05db157-b4a5-43c6-a93f-af21849b24f9")
    private List<GraphicalEditPart> getSelectedEditParts(ISelection selection) {
        return SelectionHelper.toList(selection, GraphicalEditPart.class);
    }

    @objid ("1439c316-e737-40a7-8f8a-f9bcca5a26a2")
    private Command buildCommand(List<GraphicalEditPart> selected) {
        CompoundCommand compound = new CompoundCommand("Delete");
        
        // Get the model elements to delete or to mask
        
        // Collect elements and edit parts to masking or delete
        final Collection<MObject> toDelete = collectElementsToDelete(selected);
        if (toDelete == null) {
            return UnexecutableCommand.INSTANCE;
        }
        
        final List<GraphicalEditPart> toMask = collectEditpartsToMask(selected);
        
        DeleteInModelCommand deleteCommand = deleteInModel(toDelete);
        if (deleteCommand != null && deleteCommand.canExecute()) {
            // Store the command in the compound
            compound.add(deleteCommand);
        }
        
        // Mask all edit parts without an element
        if (!toMask.isEmpty()) {
            GroupRequest deleteReq = new GroupRequest(RequestConstants.REQ_DELETE);
            deleteReq.setEditParts(toMask);
        
            for (EditPart editPart : toMask) {
                Command cmd = editPart.getCommand(deleteReq);
                if (cmd != null && cmd.canExecute()) {
                    // Store the command in the compound
                    compound.add(cmd);
                }
            }
        }
        return compound.unwrap();
    }

    @objid ("4197ba13-04c5-4256-826b-420df90542c4")
    private DeleteInModelCommand deleteInModel(final Collection<MObject> toDelete) {
        // Build the delete command
        final DeleteInModelCommand cmd = new DeleteInModelCommand();
        
        for (MObject flow : toDelete) {
            cmd.addElementToDelete(flow);
        }
        return cmd;
    }

    /**
     * Collect the edit parts to mask by analyzing the selected edit parts.
     * 
     * @param selected the selected edit parts to analyze
     */
    @objid ("86b35685-00c6-4711-96cb-01d173617870")
    private List<GraphicalEditPart> collectEditpartsToMask(List<GraphicalEditPart> selected) {
        List<GraphicalEditPart> toMask = new ArrayList<>();
        
        for (final GraphicalEditPart editPart : selected) {
            final Object model = editPart.getModel();
            if (model instanceof GmModel) {
                final MObject el = ((GmModel) model).getRelatedElement();
                if (el == null) {
                    // This is probably a ghost, worth to mask it
                    toMask.add(editPart);
                }
            } else if (model instanceof IGmDrawing) {
                toMask.add(editPart);
            }
        }
        return toMask;
    }

    /**
     * Collect the element to delete by analyzing the selected edit parts.
     * 
     * @param selected the selected edit parts to analyze
     * @return <code>null</code> to indicate that the delete operation is not possible due to read only element(s). Otherwise the effective of the elements to delete is returned.
     */
    @objid ("dc937006-cf21-4a02-be7c-3aa689887525")
    private Collection<MObject> collectElementsToDelete(List<GraphicalEditPart> selected) {
        Collection<MObject> toDelete = new HashSet<>();
        
        for (final GraphicalEditPart editPart : selected) {
            final Object model = editPart.getModel();
            if (model instanceof GmModel) {
                final GmModel gmModel = (GmModel) model;
                if (!gmModel.getDiagram().isUserEditable()) {
                    // Abort, at least one element cannot be deleted
                    return null;
                }
        
                final MObject el = gmModel.getRelatedElement();
                if (el != null && !toDelete.contains(el)) {
                    toDelete.add(el);
                }
            } 
        }
        
        for (MObject eltToDelete : new ArrayList<>(toDelete)) {
        
            if (eltToDelete instanceof Lifeline) {
                Lifeline lifeline = (Lifeline) eltToDelete;
                for (InteractionFragment f : lifeline.getCoveredBy()) {
                    if (f.getCovered()
                            .stream()
                            .allMatch(lf -> toDelete.contains(lf))) {
                        toDelete.add(f);
                    }
                }
            }
        }
        return toDelete;
    }

}
