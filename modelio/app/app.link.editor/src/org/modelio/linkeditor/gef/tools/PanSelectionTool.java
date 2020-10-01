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

package org.modelio.linkeditor.gef.tools;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.Handle;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.requests.SelectionRequest;
import org.eclipse.gef.tools.AbstractTool;
import org.eclipse.gef.tools.SelectionTool;
import org.eclipse.swt.graphics.Cursor;
import org.modelio.platform.core.navigate.IModelioNavigationService;
import org.modelio.platform.ui.gef.SharedCursors2;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * A subclass of the SelectionTool that allows panning.<br/>
 * Spanning begins by Middle-button + small move (DRAG_THRESHOLD)
 */
@objid ("1bb75762-5e33-11e2-b81d-002564c97630")
public class PanSelectionTool extends SelectionTool {
    /**
     * The state to indicate that the right button is down but no significative (> 5 pixels) drag has been initiated.
     */
    @objid ("1bb75766-5e33-11e2-b81d-002564c97630")
    protected static final int PAN = SelectionTool.MAX_STATE << 1;

    /**
     * The state to indicate that a pan is in progress.
     */
    @objid ("1bb75769-5e33-11e2-b81d-002564c97630")
    protected static final int PAN_IN_PROGRESS = PanSelectionTool.PAN << 1;

    /**
     * Max state
     */
    @objid ("1bb7576c-5e33-11e2-b81d-002564c97630")
    @SuppressWarnings("hiding")
    protected static final int MAX_STATE = PanSelectionTool.PAN_IN_PROGRESS;

    @objid ("1bb75770-5e33-11e2-b81d-002564c97630")
    private static final int DRAG_BUTTON = 2;

    @objid ("1bb75772-5e33-11e2-b81d-002564c97630")
    private static final int DRAG_THRESHOLD = 2;

    @objid ("1bb75774-5e33-11e2-b81d-002564c97630")
    protected static final int WORKAREA_RESIZE_INCREMENT = 40;

    @objid ("a980f84d-66f3-4e88-a7ed-eca6c7c85bbb")
    private Point viewLocation;

    @objid ("c726ed4e-d28a-409b-a0d3-1366fd4de15a")
    private IModelioNavigationService navigationService;

    /**
     * @see org.eclipse.gef.tools.AbstractTool#getDebugName()
     */
    @objid ("1bb75779-5e33-11e2-b81d-002564c97630")
    @Override
    protected String getDebugName() {
        return "Panning Tool"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.tools.AbstractTool#getDebugNameForState(int)
     */
    @objid ("1bb7577f-5e33-11e2-b81d-002564c97630")
    @Override
    protected String getDebugNameForState(int state) {
        if (state == PanSelectionTool.PAN)
            return "Pan Initial"; //$NON-NLS-1$
        else if (state == PanSelectionTool.PAN_IN_PROGRESS)
            return "Pan In Progress"; //$NON-NLS-1$
        return super.getDebugNameForState(state);
    }

    /**
     * Returns the cursor used under normal conditions.
     * @see #setDefaultCursor(Cursor)
     * 
     * @return the default cursor
     */
    @objid ("1bb75786-5e33-11e2-b81d-002564c97630")
    @Override
    protected Cursor getDefaultCursor() {
        if (this.isInState(PanSelectionTool.PAN_IN_PROGRESS))
            return SharedCursors2.CURSOR_GRAB_HAND;
        return super.getDefaultCursor();
    }

    /**
     * @see org.eclipse.gef.tools.SelectionTool#handleButtonDown(int)
     */
    @objid ("1bb7578c-5e33-11e2-b81d-002564c97630")
    @Override
    protected boolean handleButtonDown(int which) {
        if (which == PanSelectionTool.DRAG_BUTTON && this.getCurrentViewer().getControl() instanceof FigureCanvas
                && this.stateTransition(AbstractTool.STATE_INITIAL, PanSelectionTool.PAN)) {
            this.viewLocation = ((FigureCanvas) this.getCurrentViewer().getControl()).getViewport().getViewLocation();
            this.refreshCursor();
            return true;
        } else if (this.getCurrentInput().isControlKeyDown()) {
            EditPartViewer viewer = this.getCurrentViewer();
            Point p = this.getLocation();
        
            if (this.getDragTracker() != null)
                this.getDragTracker().deactivate();
        
            if (viewer instanceof GraphicalViewer) {
                Handle handle = ((GraphicalViewer) viewer).findHandleAt(p);
                if (handle != null) {
                    this.setDragTracker(handle.getDragTracker());
                    return true;
                }
            }
            this.updateTargetRequest();
            ((SelectionRequest) this.getTargetRequest()).setLastButtonPressed(which);
            this.updateTargetUnderMouse();
            EditPart editpart = this.getTargetEditPart();
            if (editpart != null) {
                MObject element = editpart.getAdapter(MObject.class);
                if (element != null) {
                    if (this.navigationService != null) {
                        this.navigationService.fireNavigate(element);
                    }
                    return true;
                }
            }
            return false;
        } else
            return super.handleButtonDown(which);
    }

    /**
     * @see org.eclipse.gef.tools.SelectionTool#handleButtonUp(int)
     */
    @objid ("1bb75792-5e33-11e2-b81d-002564c97630")
    @Override
    protected boolean handleButtonUp(int which) {
        if (which == PanSelectionTool.DRAG_BUTTON && this.stateTransition(PanSelectionTool.PAN_IN_PROGRESS, AbstractTool.STATE_INITIAL)) {
            this.refreshCursor();
            return true;
        } else {
            return super.handleButtonUp(which);
        }
    }

    /**
     * @see org.eclipse.gef.tools.AbstractTool#handleDrag()
     */
    @objid ("1bb9b8c3-5e33-11e2-b81d-002564c97630")
    @Override
    protected boolean handleDrag() {
        // State PAN = rightbutton hold down, no signitificative drag yet
        // (DRAG_THRESHOLD)
        if (this.isInState(PanSelectionTool.PAN) && this.getCurrentViewer().getControl() instanceof FigureCanvas) {
        
            if (Math.abs(this.getDragMoveDelta().width) > PanSelectionTool.DRAG_THRESHOLD
                    || Math.abs(this.getDragMoveDelta().height) > PanSelectionTool.DRAG_THRESHOLD) {
                if (this.stateTransition(PanSelectionTool.PAN, PanSelectionTool.PAN_IN_PROGRESS)) {
                    this.refreshCursor();
                    return true;
                }
            }
            return super.handleDrag();
        
        }
        
        // State PAN_IN_PROGRESS = rightbutton hold down, already dragged of a
        // significative amount (DRAG_THRESHOLD)
        // dragging is in progress
        if (this.isInState(PanSelectionTool.PAN_IN_PROGRESS) && this.getCurrentViewer().getControl() instanceof FigureCanvas) {
        
            GraphicalViewer v = (GraphicalViewer) this.getCurrentViewer();
        
            ScalableFreeformRootEditPart rootEditPart = (ScalableFreeformRootEditPart) v.getRootEditPart();
            FreeformLayer diagramFigure = (FreeformLayer) ((GraphicalEditPart) rootEditPart.getContents()).getFigure();
        
            FigureCanvas canvas = (FigureCanvas) this.getCurrentViewer().getControl();
        
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
    @objid ("1bb9b8c8-5e33-11e2-b81d-002564c97630")
    @Override
    protected boolean handleFocusLost() {
        // Abort dragging
        if (this.isInState(PanSelectionTool.PAN | PanSelectionTool.PAN_IN_PROGRESS)) {
            this.setState(AbstractTool.STATE_INITIAL);
            this.refreshCursor();
            return true;
        }
        return super.handleFocusLost();
    }

    @objid ("cfd08ae1-1be6-4bdd-b882-5ca78c02a142")
    public PanSelectionTool(IModelioNavigationService navigationService) {
        this.navigationService = navigationService;
    }

}
