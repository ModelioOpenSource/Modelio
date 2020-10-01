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

package org.modelio.bpmn.diagram.editor.handlers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
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
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.modelio.bpmn.diagram.editor.plugin.DiagramEditorBpmn;
import org.modelio.diagram.elements.common.abstractdiagram.AbstractDiagramEditPart;
import org.modelio.diagram.elements.core.commands.DeleteInModelCommand;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.drawings.core.IGmDrawing;
import org.modelio.metamodel.bpmn.objects.BpmnDataAssociation;
import org.modelio.metamodel.bpmn.objects.BpmnItemAwareElement;
import org.modelio.metamodel.bpmn.objects.BpmnSequenceFlowDataAssociation;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLaneSet;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;
import org.modelio.platform.model.ui.swt.labelprovider.UniversalLabelProvider;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Handler for the delete model element command in BPMN diagrams.
 * <p>
 * Deletes the model element represented by the selected diagram elements. Masks graphic elements that does not represent a model element.
 * </p>
 * <p>
 * BpmnLaneSets with no remaining BpmnLanes are also deleted.
 * </p>
 * <p>
 * Elements linked to message flows trigger a confirmation dialog before the delete itself.
 * </p>
 */
@objid ("b523b0ce-c76d-4bdd-9f4b-db58624936b4")
public class BpmnDeleteInModelHandler {
    /**
     * @return <code>false</code> if the command is to be greyed, <code>true</code> otherwise.
     */
    @objid ("64457b74-76e3-40d5-beaa-832589d8f9fd")
    @CanExecute
    public boolean canExecute(@Named (IServiceConstants.ACTIVE_SELECTION) ISelection selection, @Named (IServiceConstants.ACTIVE_SHELL) final Shell shell) {
        List<GraphicalEditPart> selected = getSelectedEditParts(selection);
        
        Command cmd = buildCommand(shell, selected, false);
        return !selected.isEmpty() && cmd.canExecute();
    }

    @objid ("105a8a83-d0d1-4d08-9d27-41af47a1dae6")
    @Execute
    public void execute(@Named (IServiceConstants.ACTIVE_SELECTION) ISelection selection, @Named (IServiceConstants.ACTIVE_SHELL) final Shell shell) {
        List<GraphicalEditPart> selected = getSelectedEditParts(selection);
        Command cmd = buildCommand(shell, selected, true);
        
        // Execute the delete and mask commands
        if (cmd != null && cmd.canExecute()) {
            EditDomain editDomain = selected.get(0).getViewer().getEditDomain();
            editDomain.getCommandStack().execute(cmd);
        }
    }

    @objid ("06d9d953-6299-4dea-95ec-ff39f8093bd4")
    private List<GraphicalEditPart> getSelectedEditParts(ISelection selection) {
        List<GraphicalEditPart> selected = new ArrayList<>();
        
        if (selection instanceof IStructuredSelection) {
            for (Object selectedObject : ((IStructuredSelection) selection).toList()) {
                if (selectedObject instanceof GraphicalEditPart && !(selectedObject instanceof AbstractDiagramEditPart)) {
                    selected.add((GraphicalEditPart) selectedObject);
                }
            }
        }
        return selected;
    }

    @objid ("049cb4c6-c3d8-440d-94b2-25d34fd9940e")
    private Command buildCommand(Shell shell, List<GraphicalEditPart> selected, boolean withConfirmation) {
        CompoundCommand compound = new CompoundCommand("Delete");
        
        // Get the model elements to delete or to mask
        
        // Collect elements and edit parts to masking or delete
        final Collection<MObject> toDelete = collectElementsToDelete(selected);
        if (toDelete == null) {
            return UnexecutableCommand.INSTANCE;
        }
        
        final List<GraphicalEditPart> toMask = collectEditpartsToMask(selected);
        
        DeleteInModelCommand deleteCommand = deleteInModel(toDelete, shell, withConfirmation);
        if (deleteCommand == null) {
            // Abort
            return null;
        } else {
            // Store the command in the compound
            if (deleteCommand.canExecute()) {
                compound.add(deleteCommand);
            }
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

    @objid ("c269933c-98a6-4e17-a183-3b0ec34c9139")
    private DeleteInModelCommand deleteInModel(final Collection<MObject> toDelete, Shell shell, boolean withConfirmation) {
        // Build the delete command
        final DeleteInModelCommand cmd = new DeleteInModelCommand();
        
        Collection<BpmnLane> lanes = new HashSet<>();
        Collection<MObject> hasMessageFlow = new HashSet<>();
        
        for (MObject eltToDelete : toDelete) {
            cmd.addElementToDelete(eltToDelete);
        
            if (eltToDelete instanceof BpmnBaseElement) {
                BpmnBaseElement bpmnElt = (BpmnBaseElement) eltToDelete;
        
                hasMessageFlow.addAll(bpmnElt.getOutgoingFlow());
                hasMessageFlow.addAll(bpmnElt.getIncomingFlow());
        
                if (eltToDelete instanceof BpmnLane) {
                    lanes.add((BpmnLane) eltToDelete);
                }
            }
        }
        
        // Delete BpmnLaneSets with no remaining BpmnLanes
        for (BpmnLane lane : lanes) {
            BpmnLaneSet laneSet = lane.getLaneSet();
            if (lanes.containsAll(laneSet.getLane())) {
                cmd.addElementToDelete(laneSet);
            }
        }
        
        // Confirm message flow destruction
        if (withConfirmation && !hasMessageFlow.isEmpty()) {
            UniversalLabelProvider labelProvider = new UniversalLabelProvider();
            String listStr = hasMessageFlow
                    .stream()
                    .map(t -> " - " + labelProvider.getText(t))
                    .sorted()
                    .collect(Collectors.joining("\n"));
        
            if (!MessageDialog.openQuestion(
                    shell,
                    DiagramEditorBpmn.I18N.getString("BpmnDeleteInModelHandler.confirmdialog.title"),
                    DiagramEditorBpmn.I18N.getMessage("BpmnDeleteInModelHandler.confirmdialog.message", listStr, hasMessageFlow.size()))) {
                // Returning null here will abort the whole delete operation
                return null;
            } else {
                for (MObject flow : hasMessageFlow) {
                    cmd.addElementToDelete(flow);
                }
            }
        }
        return cmd;
    }

    /**
     * Collect the edit parts to mask by analyzing the selected edit parts.
     * 
     * @param selected the selected edit parts to analyze
     */
    @objid ("86540063-d98c-446b-af73-48d5d2c6b709")
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
     * @return the effective elements to delete.
     */
    @objid ("088e635d-812d-4c9d-87af-6349b01c6f33")
    private Collection<MObject> collectElementsToDelete(List<GraphicalEditPart> selected) {
        Set<MObject> toDelete = new HashSet<>();
        
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
                    toDelete.addAll(collectElementsToDelete(el));
                }
            }
        }
        return toDelete;
    }

    /**
     * In some cases, deleting a specific element must also delete others, which are not properly handled at metamodel level.
     * 
     * @param el the element being deleted
     * @return the effective elements to delete.
     */
    @objid ("0f8908f3-e1cc-4729-aeea-f62817088fa7")
    private Collection<MObject> collectElementsToDelete(MObject el) {
        if (el instanceof BpmnSequenceFlowDataAssociation) {
            BpmnSequenceFlowDataAssociation bpmnDataAssociation = (BpmnSequenceFlowDataAssociation) el;
        
            List<MObject> toDelete = new ArrayList<>();
            toDelete.add(bpmnDataAssociation);
            toDelete.addAll(bpmnDataAssociation.getDataAssociation());
        
            return toDelete;
        } else if (el instanceof BpmnItemAwareElement) {
            BpmnItemAwareElement bpmnItemAwareElement = (BpmnItemAwareElement) el;
        
            List<MObject> toDelete = new ArrayList<>();
            toDelete.add(bpmnItemAwareElement);
            for (BpmnDataAssociation bpmnDataAssociation : bpmnItemAwareElement.getSourceOfDataAssociation()) {
                toDelete.addAll(collectElementsToDelete(bpmnDataAssociation));
            }
            for (BpmnDataAssociation bpmnDataAssociation : bpmnItemAwareElement.getTargetOfDataAssociation()) {
                toDelete.addAll(collectElementsToDelete(bpmnDataAssociation));
            }
        
            return toDelete;
        } else if (el instanceof BpmnDataAssociation) {
            BpmnDataAssociation bpmnDataAssociation = (BpmnDataAssociation) el;
        
            List<MObject> toDelete = new ArrayList<>();
            toDelete.add(bpmnDataAssociation);
            toDelete.addAll(bpmnDataAssociation.getVisualShortCut());
        
            return toDelete;
        } else {
            return Arrays.asList(el);
        }
    }

}
