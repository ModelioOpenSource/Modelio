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

package org.modelio.diagram.editor.handlers;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.PrecisionDimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.handles.HandleBounds;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.jface.viewers.ISelection;
import org.modelio.core.ui.swt.SelectionHelper;
import org.modelio.diagram.editor.plugin.DiagramEditor;
import org.modelio.diagram.elements.common.abstractdiagram.AbstractDiagramEditPart;
import org.modelio.diagram.elements.common.portcontainer.GmPortContainer;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.node.GmNodeModel;

/**
 * Handler for the autosize command that will reduce a selection of node edit parts to their minimum size.
 * 
 * @author phv
 */
@objid ("65ad5386-33f7-11e2-95fe-001ec947c8cc")
public class AutoSizeHandler {
    /**
     * @param selection the Eclipse selection
     */
    @objid ("65ad5399-33f7-11e2-95fe-001ec947c8cc")
    @Execute
    public void execute(@Named (IServiceConstants.ACTIVE_SELECTION) ISelection selection) {
        // Resize the elements
        execute(parseAndFilterSelection(selection));
    }

    @objid ("65ad538e-33f7-11e2-95fe-001ec947c8cc")
    private ChangeBoundsRequest buildAutoSizeRequest(GraphicalEditPart editPart) {
        // does not apply on diagram
        if (editPart instanceof AbstractDiagramEditPart) {
            return null;
        }
        
        final IFigure fig = editPart.getFigure();
        
        final Dimension oldSize = getEffectiveBounds(fig).getSize();
        final Dimension newSize = getMinimumSize(editPart);
        
        if (oldSize.equals(newSize)) {
            return null;
        }
        
        // request coords must be in absolute coordinates.
        Dimension sizeDelta = new PrecisionDimension(newSize).shrink(oldSize);
        fig.translateToAbsolute(sizeDelta);
        
        final ChangeBoundsRequest req = new ChangeBoundsRequest(RequestConstants.REQ_RESIZE);
        req.setEditParts(editPart);
        req.setSizeDelta(sizeDelta);
        return req;
    }

    /**
     * Filter the selection: when an ancestor is also in selection, remove the child.
     * That is done because any translation/resizing applied to the ancestor will already have an
     * impact on the child.
     * 
     * @param primarySelection the primary selection
     * @param otherSelections the secondary selection that will be filtered
     * @return the selected edit parts, or an empty list
     */
    @objid ("65ad53a0-33f7-11e2-95fe-001ec947c8cc")
    private List<GraphicalEditPart> filterSelection(final GraphicalEditPart primarySelection, final List<GraphicalEditPart> otherSelections) {
        List<GraphicalEditPart> selectedEditParts = new ArrayList<>(otherSelections);
        if (primarySelection != null && isUserEditable(primarySelection)) {
            selectedEditParts.add(primarySelection);
        }
        
        for (EditPart editPart : selectedEditParts) {
            boolean isToRemove = !isUserEditable(editPart);
            if (isToRemove) {
                otherSelections.remove(editPart);
            }
        
            while (editPart != null && !isToRemove) {
                if (selectedEditParts.contains(editPart.getParent())) {
                    otherSelections.remove(editPart);
                    isToRemove = true;
                }
                editPart = editPart.getParent();
            }
        }
        return selectedEditParts;
    }

    @objid ("65ad5393-33f7-11e2-95fe-001ec947c8cc")
    private Dimension getMinimumSize(final GraphicalEditPart editPart) {
        // For PortContainers, we must take the main node minimum size to avoid side effects during the setSize
        if (editPart.getModel() instanceof GmPortContainer) {
            GmPortContainer gpc = (GmPortContainer) editPart.getModel();
            GmNodeModel mainNode = gpc.getMainNode();
        
            if (mainNode != null) {
                GraphicalEditPart mainNodeEditPart = (GraphicalEditPart) editPart.getViewer()
                        .getEditPartRegistry()
                        .get(mainNode);
        
                if (mainNodeEditPart != null) {
                    IFigure mainFig = mainNodeEditPart.getFigure();
                    return mainFig.getMinimumSize();
                }
            }
        }
        return editPart.getFigure().getMinimumSize();
    }

    /**
     * This method returns the effective bounds (those seen by the end user) of a figure.
     * 
     * @param figure the figure which bounds are to be returned.
     * @return the effective bounds of the figure. This object must NOT be modified.
     */
    @objid ("65ad53a8-33f7-11e2-95fe-001ec947c8cc")
    protected Rectangle getEffectiveBounds(final IFigure figure) {
        return (figure instanceof HandleBounds) ? ((HandleBounds) figure).getHandleBounds()
                                : figure.getBounds();
    }

    @objid ("65ad5388-33f7-11e2-95fe-001ec947c8cc")
    protected void execute(List<GraphicalEditPart> selectedEditParts) {
        CompoundCommand compound = new CompoundCommand("Auto size");
        EditPartViewer viewer = null;
        
        for (GraphicalEditPart editPart : selectedEditParts) {
            final ChangeBoundsRequest req = buildAutoSizeRequest(editPart);
            if (req != null) {
                compound.add(editPart.getCommand(req));
            }
        
            if (viewer == null) {
                viewer = editPart.getViewer();
            }
        }
        
        if (viewer != null && compound.canExecute()) {
            viewer.getEditDomain().getCommandStack().execute(compound);
        } else if (viewer == null) {
            DiagramEditor.LOG.warning("AutosizeHandler#align : could not reach a valid EditPartViewer");
        }
    }

    /**
     * Extract and filter the GEF selection from the Eclipse selection
     * 
     * @param selection the Eclipse selection
     * @return the selected edit parts, or an empty list
     */
    @objid ("6fa56b65-35eb-4528-a40e-390248c5b86d")
    private List<GraphicalEditPart> parseAndFilterSelection(ISelection selection) {
        List<GraphicalEditPart> otherSelections = new ArrayList<>();
        GraphicalEditPart primarySelection = null;
        for (GraphicalEditPart editPart : SelectionHelper.toList(selection, GraphicalEditPart.class)) {
            if (editPart.getSelected() == EditPart.SELECTED_PRIMARY) {
                primarySelection = editPart;
            } else {
                otherSelections.add(editPart);
            }
        }
        return filterSelection(primarySelection, otherSelections);
    }

    @objid ("d5f1f781-1e0e-40e4-aeb8-2ef287875b3c")
    private boolean isUserEditable(EditPart editPart) {
        Object model = editPart.getModel();
        return (model instanceof IGmObject) && ((IGmObject) model).isUserEditable();
    }

    @objid ("5cbb3545-d936-464a-8174-f9b43fed2c51")
    @CanExecute
    public boolean canExecute(@Named (IServiceConstants.ACTIVE_SELECTION) ISelection selection) {
        List<GraphicalEditPart> selectedEditParts = parseAndFilterSelection(selection);
        return (!selectedEditParts.isEmpty());
    }

}
