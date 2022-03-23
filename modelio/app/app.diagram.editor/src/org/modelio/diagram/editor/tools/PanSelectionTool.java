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
package org.modelio.diagram.editor.tools;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.RangeModel;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.tools.SelectionTool;
import org.eclipse.swt.graphics.Cursor;
import org.modelio.diagram.elements.common.abstractdiagram.AbstractDiagramFigure;
import org.modelio.diagram.elements.common.root.ScalableFreeformRootEditPart2;
import org.modelio.platform.ui.gef.SharedCursors2;

/**
 * A subclass of the SelectionTool that allows panning.<br/>
 * Spanning begins by Middle-button + small move (DRAG_THRESHOLD)
 */
@objid ("66a7aa76-33f7-11e2-95fe-001ec947c8cc")
public class PanSelectionTool extends SelectionTool {
    /**
     * The state to indicate that the right button is down but no significative (> 5 pixels) drag has been initiated.
     */
    @objid ("66aa0cae-33f7-11e2-95fe-001ec947c8cc")
    protected static final int PAN = SelectionTool.MAX_STATE << 1;

    /**
     * The state to indicate that a pan is in progress.
     */
    @objid ("66aa0cb1-33f7-11e2-95fe-001ec947c8cc")
    protected static final int PAN_IN_PROGRESS = PAN << 1;

    /**
     * Max state
     */
    @objid ("66aa0cb4-33f7-11e2-95fe-001ec947c8cc")
    @SuppressWarnings("hiding")
    protected static final int MAX_STATE = PAN_IN_PROGRESS;

    @objid ("66aa0cb8-33f7-11e2-95fe-001ec947c8cc")
    private static final int DRAG_BUTTON = 2;

    @objid ("66aa0cba-33f7-11e2-95fe-001ec947c8cc")
    private static final int DRAG_THRESHOLD = 2;

    @objid ("66aa0cbc-33f7-11e2-95fe-001ec947c8cc")
    protected static final int WORKAREA_RESIZE_INCREMENT = 40;

    @objid ("66aa0cbe-33f7-11e2-95fe-001ec947c8cc")
    private static final int RIGHT_BUTTON = 3;

    @objid ("66a7aa78-33f7-11e2-95fe-001ec947c8cc")
    private Point viewLocation;

    /**
     * @see org.eclipse.gef.tools.AbstractTool#getDebugName()
     */
    @objid ("66aa0cc0-33f7-11e2-95fe-001ec947c8cc")
    @Override
    protected String getDebugName() {
        return "Panning Tool"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.tools.AbstractTool#getDebugNameForState(int)
     */
    @objid ("66aa0cc6-33f7-11e2-95fe-001ec947c8cc")
    @Override
    protected String getDebugNameForState(int state) {
        if (state == PAN)
            return "Pan Initial"; //$NON-NLS-1$
        else if (state == PAN_IN_PROGRESS)
            return "Pan In Progress"; //$NON-NLS-1$
        return super.getDebugNameForState(state);
    }

    /**
     * Returns the cursor used under normal conditions.
     * @see #setDefaultCursor(Cursor)
     * @return the default cursor
     */
    @objid ("66aa0ccd-33f7-11e2-95fe-001ec947c8cc")
    @Override
    protected Cursor getDefaultCursor() {
        if (this.isInState(PAN_IN_PROGRESS))
            return SharedCursors2.CURSOR_GRAB_HAND;
        return super.getDefaultCursor();
    }

    /**
     * @see org.eclipse.gef.tools.SelectionTool#handleButtonDown(int)
     */
    @objid ("66aa0cd3-33f7-11e2-95fe-001ec947c8cc")
    @Override
    protected boolean handleButtonDown(int which) {
        if (which == DRAG_BUTTON &&
            this.getCurrentViewer().getControl() instanceof FigureCanvas &&
            this.stateTransition(STATE_INITIAL, PAN)) {
            this.viewLocation = ((FigureCanvas) this.getCurrentViewer().getControl()).getViewport()
                                                                                     .getViewLocation();
            this.refreshCursor();
            return true;
        } else
            return super.handleButtonDown(which);
        
    }

    /**
     * @see org.eclipse.gef.tools.SelectionTool#handleButtonUp(int)
     */
    @objid ("66aa0cd9-33f7-11e2-95fe-001ec947c8cc")
    @Override
    protected boolean handleButtonUp(int which) {
        if (which == DRAG_BUTTON && this.stateTransition(PAN_IN_PROGRESS, STATE_INITIAL)) {
            this.refreshCursor();
            return true;
        }
        if (which == RIGHT_BUTTON) {
            ScalableFreeformRootEditPart2 rootEditPart = (ScalableFreeformRootEditPart2) this.getCurrentViewer()
                                                                                             .getRootEditPart();
            rootEditPart.setCreationLocationTip(this.getLocation());
        }
        return super.handleButtonUp(which);
    }

    /**
     * @see org.eclipse.gef.tools.AbstractTool#handleDrag()
     */
    @objid ("66aa0ce0-33f7-11e2-95fe-001ec947c8cc")
    @Override
    protected boolean handleDrag() {
        // State PAN = rightbutton hold down, no signitificative drag yet (DRAG_THRESHOLD)
        if (this.isInState(PAN) && this.getCurrentViewer().getControl() instanceof FigureCanvas) {
        
            if (Math.abs(this.getDragMoveDelta().width) > DRAG_THRESHOLD ||
                Math.abs(this.getDragMoveDelta().height) > DRAG_THRESHOLD) {
                if (this.stateTransition(PAN, PAN_IN_PROGRESS)) {
                    this.refreshCursor();
                    return true;
                }
            }
            return super.handleDrag();
        
        }
        
        // State PAN_IN_PROGRESS = rightbutton hold down, already dragged of a significative amount (DRAG_THRESHOLD)
        // dragging is in progress
        if (this.isInState(PAN_IN_PROGRESS) && this.getCurrentViewer().getControl() instanceof FigureCanvas) {
        
            GraphicalViewer v = (GraphicalViewer) this.getCurrentViewer();
            ScalableFreeformRootEditPart2 rootEditPart = (ScalableFreeformRootEditPart2) v.getRootEditPart();
            AbstractDiagramFigure diagramFigure = (AbstractDiagramFigure) ((GraphicalEditPart) rootEditPart.getContents()).getFigure();
        
            FigureCanvas canvas = (FigureCanvas) this.getCurrentViewer().getControl();
            this.adjustWorkarea(diagramFigure, canvas);
            canvas.scrollTo(this.viewLocation.x - this.getDragMoveDelta().width,
                            this.viewLocation.y - this.getDragMoveDelta().height);
            this.viewLocation = canvas.getViewport().getViewLocation();
            this.setStartLocation(this.getLocation());
        
            return true;
        } else {
            return super.handleDrag();
        }
        
    }

    /**
     * @see org.eclipse.gef.tools.SelectionTool#handleFocusLost()
     */
    @objid ("66aa0ce5-33f7-11e2-95fe-001ec947c8cc")
    @Override
    protected boolean handleFocusLost() {
        // Abort dragging
        if (this.isInState(PAN | PAN_IN_PROGRESS)) {
            this.setState(STATE_INITIAL);
            this.refreshCursor();
            return true;
        }
        return super.handleFocusLost();
    }

    @objid ("66aa0ceb-33f7-11e2-95fe-001ec947c8cc")
    private void adjustWorkarea(AbstractDiagramFigure diagramFigure, FigureCanvas canvas) {
        Rectangle r = diagramFigure.getFreeformExtent().getCopy();
        
        int newX = this.viewLocation.x - this.getDragMoveDelta().width;
        RangeModel hRange = canvas.getViewport().getHorizontalRangeModel();
        if (newX < hRange.getMinimum()) {
            r.x = r.x - WORKAREA_RESIZE_INCREMENT;
            r.width += WORKAREA_RESIZE_INCREMENT;
        } else if (newX + hRange.getExtent() > hRange.getMaximum()) {
            r.width += WORKAREA_RESIZE_INCREMENT;
        }
        
        int newY = this.viewLocation.y - this.getDragMoveDelta().height;
        RangeModel vRange = canvas.getViewport().getVerticalRangeModel();
        if (newY < vRange.getMinimum()) {
            r.y = r.y - WORKAREA_RESIZE_INCREMENT;
            r.height += WORKAREA_RESIZE_INCREMENT;
        } else if (newY + vRange.getExtent() > vRange.getMaximum()) {
            r.height += WORKAREA_RESIZE_INCREMENT;
        }
        
        diagramFigure.setWorkArea(r);
        
    }

}
