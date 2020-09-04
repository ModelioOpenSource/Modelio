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

package org.modelio.model.browser.view.handlers.move;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.modelio.app.project.core.services.IProjectService;
import org.modelio.metamodel.bpmn.activities.BpmnActivity;
import org.modelio.metamodel.bpmn.events.BpmnBoundaryEvent;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.model.browser.view.plugin.BrowserViewActivator;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.transactions.ITransaction;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Pastes the elements from the clipboard.
 */
@objid ("2545b6e6-43a4-11e2-b513-002564c97630")
public class MoveElementDownHandler {
    @objid ("2545b6e8-43a4-11e2-b513-002564c97630")
    @Inject
    protected IProjectService projectService;

    /**
     * Available only when the selection contains only one modifiable element.
     * @param selection the current modelio selection.
     * @return true if the handler can be executed.
     */
    @objid ("2545b6ea-43a4-11e2-b513-002564c97630")
    @CanExecute
    public final boolean canExecute(@Named(IServiceConstants.ACTIVE_SELECTION) final Object selection) {
        // Sanity checks
        if (this.projectService.getSession() == null) {
            return false;
        }
        
        // Must have at least an element
        List<SmObjectImpl> toClone = MoveElementDownHandler.getSelectedElements(selection);
        
        SmObjectImpl dest = MoveElementDownHandler.getPasteTarget(toClone);
        if (dest == null) {
            return false;
        }
        
        List<? extends MObject> listToReorder = getListToMove(toClone.get(0), dest);
        
        // Check the elements to clone can be added to dest
        for (SmObjectImpl moved : toClone) {
        
            if (MoveElementDownHandler.computeNewIndex(moved, listToReorder) == -1) {
                return false;
            }
        
            if (moved.equals(listToReorder.get(listToReorder.size() - 1))) {
                return false;
            }
        
            if (!MTools.getAuthTool().canAdd(dest, moved.getMClass())) {
                return false;
            }
        }
        return true;
    }

    @objid ("2545b6f3-43a4-11e2-b513-002564c97630")
    private static SmObjectImpl getPasteTarget(List<SmObjectImpl> toClone) {
        SmObjectImpl ret = null;
        for (SmObjectImpl obj : toClone) {
        
            SmObjectImpl compositionOwner = null;
            // Specific Treatment for BPMN Objects in Lanes
            if (obj instanceof BpmnBoundaryEvent) {
                BpmnBoundaryEvent boundary = (BpmnBoundaryEvent) obj;
                compositionOwner = (SmObjectImpl) boundary.getAttachedToRef();
            } else if (obj instanceof BpmnFlowElement) {
                BpmnFlowElement flowElement = (BpmnFlowElement) obj;
                if (flowElement.getLane().size() > 0) {
                    compositionOwner = (SmObjectImpl) flowElement.getLane().get(0);
                } else {
                    compositionOwner = obj.getCompositionOwner();
                }
            } else {
                // All elements to clone must have the same parent
                compositionOwner = obj.getCompositionOwner();
            }
        
            if (ret != null && ret != compositionOwner) {
                return null;
            } else {
                ret = compositionOwner;
            }
        }
        return ret;
    }

    @objid ("2545b6fa-43a4-11e2-b513-002564c97630")
    private static List<SmObjectImpl> getSelectedElements(final Object selection) {
        List<SmObjectImpl> selectedElements = new ArrayList<>();
        if (selection instanceof SmObjectImpl) {
            selectedElements.add((SmObjectImpl) selection);
        } else if (selection instanceof IStructuredSelection
                && ((IStructuredSelection) selection).size() >= 1) {
            Object[] elements = ((IStructuredSelection) selection).toArray();
            for (Object element : elements) {
                if (element instanceof SmObjectImpl) {
                    selectedElements.add((SmObjectImpl) element);
                } else if (element instanceof IAdaptable) {
                    final SmObjectImpl adapter = ((IAdaptable) element)
                            .getAdapter(SmObjectImpl.class);
                    if (adapter != null) {
                        selectedElements.add(adapter);
                    }
                }
            }
        }
        return selectedElements;
    }

    /**
     * Cut the currently selected elements.
     * @param selection the current modelio selection.
     * @param currentDisplay the display Modelio runs into.
     */
    @objid ("2545b702-43a4-11e2-b513-002564c97630")
    @Execute
    public final void execute(@Named(IServiceConstants.ACTIVE_SELECTION) final Object selection, Display currentDisplay) {
        ICoreSession session = this.projectService.getSession();
        
        // Sanity checks
        if (session == null) {
            return;
        }
        
        final List<SmObjectImpl> selectedElements = MoveElementDownHandler.getSelectedElements(selection);
        SmObjectImpl targetElement = MoveElementDownHandler.getPasteTarget(selectedElements);
        if (targetElement == null) {
            return;
        }
        
        // No elements to move
        if (selectedElements.isEmpty()) {
            return;
        }
        
        try (ITransaction transaction = session.getTransactionSupport()
                .createTransaction("Move element down")) {
            int nbToMove = 0;
        
            // We first move down the Last selected element of the list; This
            // way the positions of other
            // selected elements are not affected by the move of the current
            // element.
            for (int i = selectedElements.size() - 1; i >= 0; --i) {
                SmObjectImpl element = selectedElements.get(i);
        
                List listToReorder = getListToMove(element, targetElement);
        
                // Retrieve the new index of the element
                int index = MoveElementDownHandler.computeNewIndex(element, listToReorder);
        
                if (index == -1) {
                    // Invalid case, just exit
                    transaction.rollback();
                    return;
                }
        
                // Move the element in the list
                nbToMove++;
                listToReorder.remove(element);
                listToReorder.add(index, element);
        
            }
        
            if (nbToMove > 0) {
                transaction.commit();
            } else {
                transaction.rollback();
            }
        } catch (Exception e) {
            // Should catch InvalidModelManipulationException to display a popup
            // box, but it
            // is not a RuntimeException.
            MoveElementDownHandler.reportException(e);
        }
    }

    @objid ("25481845-43a4-11e2-b513-002564c97630")
    private static int computeNewIndex(SmObjectImpl element, List<? extends MObject> listToReorder) {
        int index = listToReorder.indexOf(element) + 1;
        
        // Specific Treatment for BPMN Objects
        if (element instanceof BpmnFlowElement) {
            while (index < listToReorder.size() && !(listToReorder.get(index) instanceof BpmnFlowElement)) {
                index++;
            }
        
        } else {
            // Iterate until we find an element of the same metaclass or until we
            // find the end of the list.
            while (index < listToReorder.size()
                    && listToReorder.get(index).getClass() != element.getClass()) {
                index++;
            }
        }
        
        // If that would move outside of the list, that means element is already
        // the last one.
        if (index >= listToReorder.size()) {
            return -1;
        }
        return index;
    }

    @objid ("2548184d-43a4-11e2-b513-002564c97630")
    static void reportException(Exception e) {
        // Show an error box
        String title = BrowserViewActivator.I18N.getMessage("CannotPasteClipboard");
        
        MessageDialog.openError(null, title, e.getLocalizedMessage());
        
        BrowserViewActivator.LOG.error(e);
    }

    @objid ("c5c9f34f-9753-4816-8905-d9a1c93b0b3b")
    private List<? extends MObject> getListToMove(SmObjectImpl toMove, SmObjectImpl dest) {
        // Specific Treatment for BPMN Objects in Lanes
        if (toMove instanceof BpmnFlowElement && dest instanceof BpmnLane) {
            return ((BpmnLane) dest).getFlowElementRef();
        } else if (toMove instanceof BpmnBoundaryEvent) {
            return ((BpmnActivity) dest).getBoundaryEventRef();
        }
        SmDepVal relation = toMove.getCompositionRelation();
        return relation != null ? dest.mGet(relation.dep.getSymetric()) : Collections.emptyList();
    }

}
