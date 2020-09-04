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

package org.modelio.diagram.editor.handlers.align;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.handles.HandleBounds;
import org.eclipse.jface.viewers.ISelection;
import org.modelio.core.ui.swt.SelectionHelper;
import org.modelio.diagram.elements.core.model.IGmObject;

/**
 * Base abstract class for all alignment command handlers.
 * 
 * @author pvlaemynck
 */
@objid ("65ad536b-33f7-11e2-95fe-001ec947c8cc")
public abstract class AbstractAlignHandler {
    @objid ("65ad536d-33f7-11e2-95fe-001ec947c8cc")
    @Execute
    public Object execute(@Named(IServiceConstants.ACTIVE_SELECTION) ISelection selection) {
        List<GraphicalEditPart> otherSelections = new ArrayList<>();
        GraphicalEditPart primarySelection = parseAndFilterSelection(selection, otherSelections);
        
        // Align the elements
        if (primarySelection != null && ! otherSelections.isEmpty()) {            
            align(primarySelection, otherSelections);
        }
        return null;
    }

    @objid ("65ad5373-33f7-11e2-95fe-001ec947c8cc")
    protected abstract void align(GraphicalEditPart primarySelection, List<GraphicalEditPart> otherSelections);

    /**
     * This method returns the effective bounds (those seen by the end user) of a figure
     * @param figure the figure which bounds are to be returned.
     * @return a copy of the effective bounds of the figure
     */
    @objid ("65ad5378-33f7-11e2-95fe-001ec947c8cc")
    protected Rectangle getEffectiveBounds(IFigure figure) {
        return (figure instanceof HandleBounds) ? ((HandleBounds) figure).getHandleBounds().getCopy()
                                                                        : figure.getBounds().getCopy();
    }

    /**
     * Filter the selection: when an ancestor is also in selection, remove the child.
     * That is done because any translation/resizing applied to the ancestor will already have an
     * impact on the child.
     * @param primarySelection the primary selection
     * @param otherSelections the secondary selection that will be filtered
     */
    @objid ("65ad537e-33f7-11e2-95fe-001ec947c8cc")
    private GraphicalEditPart filterSelection(final GraphicalEditPart primarySelection, final List<GraphicalEditPart> otherSelections) {
        List<GraphicalEditPart> otherSelectionsCopy = new ArrayList<>(otherSelections);
        if (primarySelection != null) {
            otherSelectionsCopy.add(primarySelection);
        }
        
        for (EditPart editPart : otherSelectionsCopy) {
            boolean isToRemove = ! isUserEditable(editPart);
            if (isToRemove) {
                otherSelections.remove(editPart);
            }
            
            while (editPart != null && !isToRemove) {
                if (otherSelectionsCopy.contains(editPart.getParent())) {
                    otherSelections.remove(editPart);
                    isToRemove = true;
                }
                editPart = editPart.getParent();
            }
        }
        
        if (primarySelection != null && isUserEditable(primarySelection)) {
            return primarySelection;
        } else {
            return null;
        }
    }

    /**
     * Extract and filter the GEF selection from the Eclipse selection
     * @param selection the Eclipse selection
     * @param otherSelections a container for the secondary selection
     * @return the primary selected edit part, or null
     */
    @objid ("0580f994-fb38-476b-a306-e91307782e9a")
    private GraphicalEditPart parseAndFilterSelection(ISelection selection, List<GraphicalEditPart> otherSelections) {
        GraphicalEditPart primarySelection = null;
        for (GraphicalEditPart editPart : SelectionHelper.toList(selection, GraphicalEditPart.class)) {
            if (editPart.getSelected() == EditPart.SELECTED_PRIMARY) {
                primarySelection = editPart;
            } else {
                otherSelections.add(editPart);
            }
        }
        
        primarySelection = filterSelection(primarySelection, otherSelections);
        return primarySelection;
    }

    @objid ("ff1e3fbf-1d7d-4e95-9c63-8b54b43a8fc3")
    @CanExecute
    public boolean canExecute(@Named(IServiceConstants.ACTIVE_SELECTION) ISelection selection) {
        List<GraphicalEditPart> otherSelections = new ArrayList<>();
        GraphicalEditPart primarySelection = parseAndFilterSelection(selection, otherSelections);
        return (primarySelection != null && ! otherSelections.isEmpty());
    }

    @objid ("09d3f62b-5b93-449d-ae58-b247742371f5")
    private boolean isUserEditable(EditPart editPart) {
        Object model = editPart.getModel();
        return (model instanceof IGmObject) && ((IGmObject) model).isUserEditable();
    }

}
