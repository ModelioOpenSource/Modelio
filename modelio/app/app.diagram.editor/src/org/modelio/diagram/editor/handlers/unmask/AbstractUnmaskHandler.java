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

package org.modelio.diagram.editor.handlers.unmask;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.handles.HandleBounds;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.platform.project.services.IProjectService;
import org.modelio.vcore.session.api.ICoreSession;

/**
 * Base abstract class for all E4 unmask command handlers.
 */
@objid ("65eb508f-33f7-11e2-95fe-001ec947c8cc")
public abstract class AbstractUnmaskHandler {
    @objid ("65eb5091-33f7-11e2-95fe-001ec947c8cc")
    protected static UnmaskManager unmaskManager = new UnmaskManager();

    /**
     * Execute the handler
     * 
     * @param project project service
     * @param selection eclipse selection
     */
    @objid ("65eb5092-33f7-11e2-95fe-001ec947c8cc")
    @Execute
    public final void execute(IProjectService project, @Named(IServiceConstants.ACTIVE_SELECTION) IStructuredSelection selection) {
        List<GraphicalEditPart> secondarySelection = new ArrayList<>();
        GraphicalEditPart primarySelection = getSelection(selection, secondarySelection);
        
        // Unmask the elements
        unmask(primarySelection, secondarySelection, project.getSession());
    }

    /**
     * This method returns the effective bounds (those seen by the end user) of a figure
     * 
     * @param figure the figure which bounds are to be returned.
     * @return a copy of the effective bounds of the figure
     */
    @objid ("65eb5099-33f7-11e2-95fe-001ec947c8cc")
    protected Rectangle getEffectiveBounds(final IFigure figure) {
        return (figure instanceof HandleBounds) ? ((HandleBounds) figure).getHandleBounds().getCopy()
                                                : figure.getBounds().getCopy();
    }

    /**
     * Get all <i>main</i> edit parts from the Eclipse selection.
     * <p>
     * Separates the primary selection from other selected elements.
     * The primary element is returned and the other are added to <code>secondarySelection</code>.
     * 
     * @param selection the Eclipse selection
     * @param secondarySelection among the Eclipse selection, the secondary selection
     * @return the primary selected edit part main node edit part.
     */
    @objid ("65eb50a0-33f7-11e2-95fe-001ec947c8cc")
    protected GraphicalEditPart getSelection(IStructuredSelection selection, final List<GraphicalEditPart> secondarySelection) {
        GraphicalEditPart primarySelection = null;
        
        List<?> selectedObjects = selection.toList();
        for (Object selectedObject : selectedObjects) {
            if (selectedObject instanceof GraphicalEditPart && 
                    ((EditPart)selectedObject).getModel() instanceof GmModel) {
                GraphicalEditPart editPart = (GraphicalEditPart) selectedObject;
                boolean isPrimary = editPart.getSelected() == EditPart.SELECTED_PRIMARY;
        
                // Only keep 'main' gms
                while (((GmModel)editPart.getModel()).getRepresentedElement() == null) {
                    editPart = (GraphicalEditPart) editPart.getParent();
                }
        
                if (isPrimary) {
                    primarySelection = editPart;
        
                    // Avoid keeping the same edit part more than once
                    secondarySelection.remove(editPart);
                } else {
                    // Avoid keeping the same edit part more than once
                    if (editPart != primarySelection && !secondarySelection.contains(editPart)) {
                        secondarySelection.add(editPart);
                    }
                }
            }
        }
        
        filterSelection(primarySelection, secondarySelection);
        return primarySelection;
    }

    @objid ("65eb50a8-33f7-11e2-95fe-001ec947c8cc")
    protected abstract void unmask(final GraphicalEditPart primarySelection, final List<GraphicalEditPart> secondarySelection, ICoreSession session);

    /**
     * Filter the secondary selection: when an ancestor is also in one of the selections, remove the child
     * from the secondary selection.
     * <p>
     * That is done because any translation/resizing applied to the ancestor will already have an
     * impact on the child.
     * 
     * @param primarySelection the primary selected edit part
     * @param secondarySelection the secondary selection to be filtered
     */
    @objid ("65edb2ed-33f7-11e2-95fe-001ec947c8cc")
    private void filterSelection(final GraphicalEditPart primarySelection, final List<GraphicalEditPart> secondarySelection) {
        // Filter the selection: when an ancestor is also in selection, remove the child.
        // That is done because any translation/resizing applied to the ancestor will already have an
        // impact on the child.
        List<GraphicalEditPart> secondarySelectionCopy = new ArrayList<>(secondarySelection);
        secondarySelectionCopy.add(primarySelection);
        for (EditPart editPart : secondarySelectionCopy) {
            boolean ancestorFound = false;
            while (editPart != null && !ancestorFound) {
                if (secondarySelectionCopy.contains(editPart.getParent())) {
                    secondarySelection.remove(editPart);
                    ancestorFound = true;
                }
                editPart = editPart.getParent();
            }
        }
    }

}
