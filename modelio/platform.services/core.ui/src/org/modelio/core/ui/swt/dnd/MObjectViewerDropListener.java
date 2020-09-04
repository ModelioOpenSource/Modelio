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

package org.modelio.core.ui.swt.dnd;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerDropAdapter;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.swt.widgets.Link;
import org.modelio.api.ui.dnd.ModelElementTransfer;
import org.modelio.core.ui.plugin.CoreUi;
import org.modelio.metamodel.uml.infrastructure.AbstractProject;
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.metamodel.uml.statik.ClassAssociation;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.session.api.model.IModel;
import org.modelio.vcore.session.api.transactions.ITransaction;
import org.modelio.vcore.session.api.transactions.ITransactionSupport;
import org.modelio.vcore.session.impl.CoreSession;
import org.modelio.vcore.smkernel.IllegalModelManipulationException;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MExpert;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;
import org.modelio.vcore.smkernel.mapi.MStatus;

/**
 * Drop listener implementation for a viewer containing MObjects.
 * <p>
 * Handles 'Copy' and 'Move' operations only.
 * </p>
 * @see ModelElementTransfer
 */
@objid ("6f1a74cc-f75d-4e6c-9d92-05c7d75cbd7b")
public class MObjectViewerDropListener extends ViewerDropAdapter {
    /**
     * Constructor initializing an instance of MObjectViewerDropListener.
     * 
     * @param viewer the viewer were elements are dropped
     */
    @objid ("c328ed80-63d2-44d6-a3ea-cbc8a71fd5e4")
    public MObjectViewerDropListener(Viewer viewer) {
        super(viewer);
        this.setFeedbackEnabled(false);
    }

    /**
     * After a drop has been stated as valid, copy or move the dragged elements under the target.
     */
    @objid ("cf6575af-9344-4771-8185-859cc389a182")
    @Override
    public boolean performDrop(Object data) {
        MObject target = (MObject) getCurrentTarget();
        
        // Convert the transfer data to MRefs.
        MRef[] refs = (MRef[]) data;
        if (refs != null) {
            // Find model elements in the session from their refs
            List<MObject> dropedElements = new ArrayList<>();
            for (int i = 0; i < refs.length; i++) {
                dropedElements.add(CoreSession.getSession(target).getModel().findByRef(refs[i], IModel.NODELETED));
            }
        
            boolean isMove = getCurrentOperation() == DND.DROP_MOVE;
            if (isValidDrop(target, dropedElements, isMove)) {
                return performDrop(target, dropedElements, isMove);
            }
        }
        return false;
    }

    /**
     * Indicates whether or not the currently transfered elements can be dropped on the target.
     * <p>
     * Metamodel rules are checked, as well as manipulation rights for both the target and dropped elements.
     * </p>
     * 
     * @param target the targeted element, must be a MObject.
     * @param operation the d&d operation, must be {@link DND#DROP_COPY} or {@link DND#DROP_MOVE}.
     * @param transferType the contents of data being dropped.
     * @return <code>true</code> if all the drop parameters are valid.
     */
    @objid ("68ec59ae-47c4-4fdb-aa0a-61f80ddf897d")
    @Override
    public boolean validateDrop(Object target, int operation, TransferData transferType) {
        // Is the target a model element?
        if (!(target instanceof MObject)) {
            return false;
        }
        
        // We only handle moves and copies.
        if (operation != DND.DROP_MOVE && operation != DND.DROP_COPY) {
            return false;
        }
        
        // The transfer type must be supported by ModelElementTransfer.
        ModelElementTransfer elementTransfer = ModelElementTransfer.getInstance();
        if (!elementTransfer.isSupportedType(transferType)) {
            return false;
        }
        
        // Convert the transfer data to MRefs.
        MRef[] refs = (MRef[]) elementTransfer.nativeToJava(transferType);
        if (refs != null) {
            // Find model elements in the session from their refs
            List<MObject> dropedElements = new ArrayList<>();
            for (int i = 0; i < refs.length; i++) {
                dropedElements.add(CoreSession.getSession((MObject) target).getModel().findByRef(refs[i], IModel.NODELETED));
            }
        
            return isValidDrop((MObject) target, dropedElements, operation == DND.DROP_MOVE);
        } else {
            // On linux, the event data is not filled until the 'drop'. Try getting the selection from LocalSelectionTransfer.
            return isValidDrop((MObject) target, getLocalDraggedElements(), operation == DND.DROP_MOVE);
        }
    }

    /**
     * Tells whether 'child' can be owned by 'parent'.
     * 
     * @param parent The future parent element
     * @param child a child element
     * @return true only if parent can contain the child.
     */
    @objid ("f7230fe2-2f94-43d8-8566-601c3f397fab")
    private boolean canBeParentOf(final MObject parent, final MObject child) {
        MExpert mExpert = parent.getMClass().getMetamodel().getMExpert();
        return mExpert.canCompose(parent, child, null) || mExpert.canSource(child, parent);
    }

    @objid ("4a13f923-01bd-49ce-813e-c00b98ddc0fa")
    private boolean copyElements(final List<MObject> elementsToCopy, final MObject targetElement) {
        List<MObject> copyResult = null;
        try {
            copyResult = MTools.getModelTool().copyElements(elementsToCopy, targetElement);
        
            // Select the result
            getViewer().setSelection(new StructuredSelection(copyResult));
        
            return true;
        } catch (final Exception e) {
            // Show an error box
            MessageDialog.openError(null, CoreUi.I18N.getMessage("CopyFailed"), e.getLocalizedMessage());
            return false;
        }
    }

    /**
     * Get the elements dragged from the same instance of Modelio. Uses {@link LocalSelectionTransfer}.
     * @see LocalSelectionTransfer
     * 
     * @return the dragged elements.
     */
    @objid ("b1e30427-6c6e-47fe-ba24-d7e166b09c11")
    private List<MObject> getLocalDraggedElements() {
        List<MObject> selectedElements = new ArrayList<>();
        
        ISelection selection = LocalSelectionTransfer.getTransfer().getSelection();
        
        if (selection instanceof IStructuredSelection) {
            IStructuredSelection structuredSelection = (IStructuredSelection) selection;
            for (Iterator<?> i = structuredSelection.iterator(); i.hasNext();) {
                Object o = i.next();
                if (o instanceof IAdaptable) {
                    IAdaptable adapter = (IAdaptable) o;
                    MObject element = adapter.getAdapter(MObject.class);
                    if (element != null) {
                        selectedElements.add(element);
                    }
                } else if (o instanceof MObject) {
                    selectedElements.add((MObject) o);
                }
            }
        }
        return selectedElements;
    }

    /**
     * Tells whether 'element' is owned by 'parentCandidate'.
     * 
     * @param parentCandidate The parent element to check
     * @param element an element
     * @return true only if parentCandidate owns element.
     */
    @objid ("3c3fdf0b-df43-47a4-9076-e2a631540e3f")
    private boolean isParentOf(MObject parentCandidate, MObject element) {
        final MObject parent = element.getCompositionOwner();
        
        if (parent == null) {
            return false;
        }
        
        if (parentCandidate.equals(parent)) {
            return true;
        }
        return isParentOf(parentCandidate, parent);
    }

    /**
     * Checks whether or not this drop configuration is valid, using different parameters:
     * <ul>
     * <li>The model manipulation rules are not violated.</li>
     * <li>We do not drop an element onto itself or a child</li>
     * <li>The drop target can be modified</li>
     * <li>For a "move", the dragged elements and their respective parents must be modifiable.</li>
     * </ul>
     * 
     * @param targetElement the targeted element.
     * @param elementsToDrop the elements to drop.
     * @param isMove <code>true</code> if the current drop is a move operation, <code>false</code> if it is a copy.
     * @return <code>true</code> if all the drop parameters are valid.
     */
    @objid ("a1985e67-bd81-414e-b78a-810d219ae7f0")
    private boolean isValidDrop(MObject targetElement, List<MObject> elementsToDrop, boolean isMove) {
        if (targetElement == null || elementsToDrop.isEmpty()) {
            return false;
        }
        
        if (isMove) {
            for (MObject element : elementsToDrop) {
                if (element.equals(targetElement) || isParentOf(element, targetElement)) {
                    // Cannot cut/paste an element onto itself or a child
                    return false;
                }
            }
        }
        
        // Check status and metaclasses
        boolean selectionAreFreeCmsNode = true;
        for (final MObject element : elementsToDrop) {
            final MStatus status = element.getStatus();
            final boolean isFreeCmsNode = element.getMClass().isCmsNode() && !status.isCmsManaged();
            if (selectionAreFreeCmsNode && !isFreeCmsNode) {
                selectionAreFreeCmsNode = false;
            }
        
            MDependency dep = null;
        
            if (element instanceof Association || element instanceof Link) {
                // assoc and links cannot be D&D'ed
                return false;
            } else if (element instanceof Parameter) {
                // for a return parameter, check that the target is an detail with no existing return parameter
                final Parameter parameter = (Parameter) element;
                if (targetElement instanceof Operation && parameter.getReturned() != null) {
                    final Operation targetOperation = (Operation) targetElement;
                    if (targetOperation.getReturn() != null) {
                        return false;
                    }
                }
            } else if (element instanceof ClassAssociation) {
                // for a class associationn check we are not targeting an already link object
                if (targetElement instanceof Association) {
                    final Association targetAssociation = (Association) targetElement;
                    if (targetAssociation.getLinkToClass() != null) {
                        return false;
                    }
                }
            } else if (element instanceof AbstractProject) {
                // cannot D&D projects
                return false;
            } else {
                // 'ordinary' model element, treat the D&D as a composition owner change
                dep = targetElement.getMClass().getMetamodel().getMExpert().getDefaultCompositionDep(targetElement, element);
                if (dep == null) {
                    return false;
                }
                if (!canBeParentOf(targetElement, element)) {
                    return false;
                }
        
            }
        
            // If the current DND detail is a "move", make sure that the dragged elements are all modifiable and that
            // their respective parent are also modifiable.
            if (isMove) {
                if (!status.isModifiable()) {
                    return false;
                }
        
                final MObject parentElement = element.getCompositionOwner();
                if (parentElement == null) {
                    return false;
                }
        
                if (!parentElement.getStatus().isModifiable() && !isFreeCmsNode) {
                    return false;
                }
            }
        }
        
        // Make sure the drop target can be modified
        final MStatus targetStatus = targetElement.getStatus();
        if (targetStatus.isRamc() || (!targetStatus.isModifiable() && !selectionAreFreeCmsNode)) {
            return false;
        }
        
        // Accept drop
        return true;
    }

    @objid ("3b38336f-ff70-478d-a9ae-1159b20740ff")
    private boolean moveElements(final List<MObject> elementsToMove, final MObject targetElement) {
        try {
            MTools.getModelTool().moveElements(elementsToMove, targetElement, null);
            // Select the result
            getViewer().setSelection(new StructuredSelection(elementsToMove));
        
            return true;
        } catch (final Exception e) {
            // Show an error box
            MessageDialog.openError(null, CoreUi.I18N.getMessage("MovingFailed"), e.getLocalizedMessage());
            return false;
        }
    }

    /**
     * After a drop has been stated as valid, copy or move the dragged elements under the target.
     * 
     * @param targetElement the targeted element.
     * @param elementsToDrop the elements to drop.
     * @param isMove <code>true</code> if the current drop is a move operation, <code>false</code> if it is a copy.
     * @return <code>true</code> if all the drop parameters are valid.
     */
    @objid ("929de76c-80aa-49ab-a961-5efc439c3a29")
    private boolean performDrop(MObject targetElement, List<MObject> elementsToDrop, boolean isMove) {
        if (targetElement == null || elementsToDrop.isEmpty()) {
            return false;
        }
        
        // Perform the action
        ITransactionSupport transactionManager = CoreSession.getSession(targetElement).getTransactionSupport();
        
        try (ITransaction transaction = transactionManager.createTransaction((isMove) ? "Cut" : "Copy")) {
            // elements
            boolean ret;
            if (isMove) {
                ret = moveElements(elementsToDrop, targetElement);
            } else {
                ret = copyElements(elementsToDrop, targetElement);
            }
        
            // check results
            if (ret) {
                transaction.commit();
                return true;
            } else {
                transaction.rollback();
                return false;
            }
        } catch (IllegalModelManipulationException e) {
            // Ignore model shield error
            return false;
        }
    }

}
