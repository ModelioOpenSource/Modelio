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

package org.modelio.uml.sequencediagram.editor.elements.message;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.Cursors;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.gef.AccessibleAnchorProvider;
import org.eclipse.gef.AutoexposeHelper;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.Tool;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.tools.AbstractTool;
import org.eclipse.gef.tools.SelectEditPartTracker;
import org.eclipse.gef.tools.ToolUtilities;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.Cursor;

/**
 * Drag tracker specific to the movement (vertical translation) of Message in Sequence Diagrams.
 * 
 * @author fpoyer
 */
@objid ("d95a82d9-55b6-11e2-877f-002564c97630")
public class MessageDragTracker extends SelectEditPartTracker {
    @objid ("d95c093e-55b6-11e2-877f-002564c97630")
    private static final int FLAG_SOURCE_FEEBBACK = SelectEditPartTracker.MAX_FLAG << 1;

    /**
     * The max flag
     */
    @objid ("d95c0940-55b6-11e2-877f-002564c97630")
    @SuppressWarnings ("hiding")
    protected static final int MAX_FLAG = FLAG_SOURCE_FEEBBACK;

    @objid ("d95c0944-55b6-11e2-877f-002564c97630")
    private String commandName;

    @objid ("3088a4c8-c825-4d23-a0b9-79b624ab3f66")
    private List<Object> exclusionSet;

    @objid ("b159dba4-36ec-4a2d-baae-036944b05ab3")
    private PrecisionPoint sourceRelativeStartPoint;

    @objid ("fd511055-0fe7-4f31-ba7b-8bbe80f8470d")
    private ConnectionEditPart connectionEditPart;

    /**
     * Constructs a new ConnectionEndpointTracker for the given ConnectionEditPart.
     * 
     * @param cep the ConnectionEditPart
     */
    @objid ("d95c0945-55b6-11e2-877f-002564c97630")
    public MessageDragTracker(final ConnectionEditPart cep) {
        super(cep);
        setConnectionEditPart(cep);
        setDisabledCursor(Cursors.NO);
    }

    /**
     * Erases source and target feedback and executes the current command.
     * @see DragTracker#commitDrag()
     */
    @objid ("d95c094a-55b6-11e2-877f-002564c97630")
    @Override
    public void commitDrag() {
        eraseSourceFeedback();
        eraseTargetFeedback();
        executeCurrentCommand();
    }

    /**
     * Erases feedback and sets the viewer's focus to <code>null</code>. This will remove any focus rectangles that were painted to show the new target or source edit part.
     * @see Tool#deactivate()
     */
    @objid ("d95c094e-55b6-11e2-877f-002564c97630")
    @Override
    public void deactivate() {
        eraseSourceFeedback();
        getCurrentViewer().setFocus(null);
        super.deactivate();
    }

    /**
     * Sets the command name.
     * 
     * @param newCommandName the new command name
     */
    @objid ("d95c0952-55b6-11e2-877f-002564c97630")
    public void setCommandName(final String newCommandName) {
        this.commandName = newCommandName;
    }

    /**
     * Sets the connection edit part that is being reconnected.
     * 
     * @param cep the connection edit part
     */
    @objid ("d95c0957-55b6-11e2-877f-002564c97630")
    public void setConnectionEditPart(final ConnectionEditPart cep) {
        this.connectionEditPart = cep;
    }

    /**
     * Returns a custom "plug" cursor if this tool is in the initial, drag or accessible drag state. Otherwise defers to <code>super</code>.
     * 
     * @return the cursor
     */
    @objid ("d95c0962-55b6-11e2-877f-002564c97630")
    @Override
    protected Cursor calculateCursor() {
        if (isInState(STATE_INITIAL | STATE_DRAG | STATE_ACCESSIBLE_DRAG))
            return getDefaultCursor();
        return super.calculateCursor();
    }

    /**
     * Returns a List of top-level edit parts excluding dependants (by calling {@link ToolUtilities#getSelectionWithoutDependants(EditPartViewer)} .
     * @see org.eclipse.gef.tools.AbstractTool#createOperationSet()
     */
    @objid ("d95c0968-55b6-11e2-877f-002564c97630")
    @Override
    protected List<?> createOperationSet() {
        if (getCurrentViewer() != null) {
            List<?> list = ToolUtilities.getSelectionWithoutDependants(getCurrentViewer());
            return list;
        }
        return new ArrayList<>();
    }

    /**
     * Creates the target request, a {@link ChangeBoundsRequest}.
     * 
     * @return the target request
     */
    @objid ("d95c0970-55b6-11e2-877f-002564c97630")
    @Override
    protected Request createTargetRequest() {
        return new ChangeBoundsRequest(getCommandName());
    }

    /**
     * Erases the source feedback.
     */
    @objid ("d95d8fdb-55b6-11e2-877f-002564c97630")
    protected void eraseSourceFeedback() {
        if (!getFlag(FLAG_SOURCE_FEEBBACK))
            return;
        setFlag(FLAG_SOURCE_FEEBBACK, false);
        Iterator<?> iter = getOperationSet().iterator();
        Request request = getTargetRequest();
        request.setType(REQ_MOVE);
        while (iter.hasNext()) {
            EditPart editPart = (EditPart) iter.next();
            editPart.eraseSourceFeedback(getTargetRequest());
        }
    }

    /**
     * Asks each edit part in the {@link AbstractTool#getOperationSet() operation set} to contribute to a {@link CompoundCommand} after first setting the request type to {@link RequestConstants#REQ_MOVE}.
     * @see org.eclipse.gef.tools.AbstractTool#getCommand()
     */
    @objid ("d95d8fde-55b6-11e2-877f-002564c97630")
    @Override
    protected Command getCommand() {
        CompoundCommand command = new CompoundCommand();
        command.setDebugLabel("MessageDragTracker");//$NON-NLS-1$
        
        Iterator<?> iter = getOperationSet().iterator();
        
        Request request = getTargetRequest();
        
        request.setType(REQ_MOVE);
        while (iter.hasNext()) {
            EditPart editPart = (EditPart) iter.next();
            command.add(editPart.getCommand(request));
        }
        return command.unwrap();
    }

    /**
     * @see AbstractTool#getCommandName()
     */
    @objid ("d95d8fe4-55b6-11e2-877f-002564c97630")
    @Override
    protected String getCommandName() {
        return this.commandName;
    }

    /**
     * Returns the ConnectionEditPart's figure.
     * 
     * @return the connection
     */
    @objid ("d95d8fea-55b6-11e2-877f-002564c97630")
    protected Connection getConnection() {
        return (Connection) getConnectionEditPart().getFigure();
    }

    /**
     * Returns the ConnectionEditPart.
     * 
     * @return the ConnectionEditPart
     */
    @objid ("d95d8fef-55b6-11e2-877f-002564c97630")
    protected ConnectionEditPart getConnectionEditPart() {
        return this.connectionEditPart;
    }

    @objid ("d95d8ff4-55b6-11e2-877f-002564c97630")
    @Override
    protected String getDebugName() {
        return "MessageDragTracker"; //$NON-NLS-1$
    }

    @objid ("d95d8ff9-55b6-11e2-877f-002564c97630")
    @Override
    protected Collection<?> getExclusionSet() {
        if (this.exclusionSet == null) {
            this.exclusionSet = new ArrayList<>();
            this.exclusionSet.add(getConnection());
        }
        return this.exclusionSet;
    }

    /**
     * If currently in the drag-in-progress state, it goes into the terminal state erases feedback and executes the current command.
     * @see org.eclipse.gef.tools.AbstractTool#handleButtonUp(int)
     */
    @objid ("d95d9000-55b6-11e2-877f-002564c97630")
    @Override
    protected boolean handleButtonUp(final int button) {
        if (stateTransition(STATE_DRAG_IN_PROGRESS, STATE_TERMINAL)) {
            eraseSourceFeedback();
            eraseTargetFeedback();
            executeCurrentCommand();
        }
        return true;
    }

    /**
     * Updates the request and the mouse target, asks to show feedback, and gets the current command.
     * 
     * @return <code>true</code>
     */
    @objid ("d95d9008-55b6-11e2-877f-002564c97630")
    @Override
    protected boolean handleDragInProgress() {
        updateTargetRequest();
        updateTargetUnderMouse();
        showSourceFeedback();
        showTargetFeedback();
        setCurrentCommand(getCommand());
        return true;
    }

    @objid ("d95d900e-55b6-11e2-877f-002564c97630")
    @Override
    protected boolean handleDragStarted() {
        stateTransition(STATE_DRAG, STATE_DRAG_IN_PROGRESS);
        return false;
    }

    @objid ("d95d9013-55b6-11e2-877f-002564c97630")
    @Override
    protected boolean handleHover() {
        if (isInState(STATE_DRAG_IN_PROGRESS | STATE_ACCESSIBLE_DRAG_IN_PROGRESS))
            updateAutoexposeHelper();
        return true;
    }

    /**
     * Processes the arrow keys (to choose a different source or target edit part) and forwardslash and backslash keys (to try to connect to another connection).
     * @see org.eclipse.gef.tools.AbstractTool#handleKeyDown(org.eclipse.swt.events.KeyEvent)
     */
    @objid ("d95f167d-55b6-11e2-877f-002564c97630")
    @Override
    protected boolean handleKeyDown(final KeyEvent e) {
        if (acceptArrowKey(e)) {
            if (stateTransition(STATE_INITIAL, STATE_ACCESSIBLE_DRAG_IN_PROGRESS)) {
                // When the drag first starts, set the focus Part to be one end
                // of the connection
                getCurrentViewer().setFocus(getConnectionEditPart().getSource());
                getCurrentViewer().reveal(getConnectionEditPart().getSource());
            }
            int direction = 0;
            switch (e.keyCode) {
            case SWT.ARROW_DOWN:
                direction = PositionConstants.SOUTH;
                break;
            case SWT.ARROW_UP:
                direction = PositionConstants.NORTH;
                break;
            case SWT.ARROW_RIGHT:
                direction = (getCurrentViewer().getControl().getStyle() & SWT.MIRRORED) != 0
                        ? PositionConstants.WEST : PositionConstants.EAST;
                break;
            case SWT.ARROW_LEFT:
                direction = (getCurrentViewer().getControl().getStyle() & SWT.MIRRORED) != 0
                        ? PositionConstants.EAST : PositionConstants.WEST;
                break;
            default:
                break;
            }
        
            boolean consumed = false;
            if (direction != 0 && e.stateMask == 0)
                consumed = navigateNextAnchor(direction);
            if (!consumed) {
                e.stateMask |= SWT.CONTROL;
                e.stateMask &= ~SWT.SHIFT;
                if (getCurrentViewer().getKeyHandler().keyPressed(e)) {
                    navigateNextAnchor(0);
                    return true;
                }
            }
        }
        if (e.character == '/' || e.character == '\\') {
            e.stateMask |= SWT.CONTROL;
            if (getCurrentViewer().getKeyHandler().keyPressed(e)) {
                // Do not try to connect to the same connection being dragged.
                if (getCurrentViewer().getFocusEditPart() != getConnectionEditPart())
                    navigateNextAnchor(0);
                return true;
            }
        }
        return false;
    }

    /**
     * If auto scroll (also called auto expose) is being performed, the start location moves during the scroll. This method updates that location.
     */
    @objid ("d95f1685-55b6-11e2-877f-002564c97630")
    protected void repairStartLocation() {
        if (this.sourceRelativeStartPoint == null)
            return;
        IFigure figure = ((GraphicalEditPart) getConnectionEditPart()).getFigure();
        PrecisionPoint newStart = (PrecisionPoint) this.sourceRelativeStartPoint.getCopy();
        figure.translateToAbsolute(newStart);
        // Point delta = new Point(newStart.x - getStartLocation().x, newStart.y
        // - getStartLocation().y);
        setStartLocation(newStart);
        // sourceRectangle and compoundSrcRect need to be updated as well when
        // auto-scrolling
        // TODO Following is used for snap (see DragEditPartsTracker)
        // if (sourceRectangle != null)
        // sourceRectangle.translate(delta);
        // if (compoundSrcRect != null)
        // compoundSrcRect.translate(delta);
    }

    @objid ("d95f1688-55b6-11e2-877f-002564c97630")
    @Override
    protected void setAutoexposeHelper(final AutoexposeHelper helper) {
        super.setAutoexposeHelper(helper);
        if (helper != null &&
                this.sourceRelativeStartPoint == null &&
                isInState(STATE_DRAG_IN_PROGRESS | STATE_ACCESSIBLE_DRAG_IN_PROGRESS)) {
            IFigure figure = ((GraphicalEditPart) getConnectionEditPart()).getFigure();
            this.sourceRelativeStartPoint = new PrecisionPoint(getStartLocation());
            figure.translateToRelative(this.sourceRelativeStartPoint);
        }
    }

    /**
     * Asks the ConnectionEditPart to show source feedback.
     */
    @objid ("d95f168d-55b6-11e2-877f-002564c97630")
    protected void showSourceFeedback() {
        Iterator<?> iter = getOperationSet().iterator();
        Request request = getTargetRequest();
        request.setType(REQ_MOVE);
        while (iter.hasNext()) {
            EditPart editPart = (EditPart) iter.next();
            editPart.showSourceFeedback(getTargetRequest());
        }
        setFlag(FLAG_SOURCE_FEEBBACK, true);
    }

    /**
     * Calls {@link #repairStartLocation()} in case auto scroll is being performed. Updates the request with the current {@link AbstractTool#getOperationSet() operation set}, move delta, location and type.
     * @see org.eclipse.gef.tools.TargetingTool#updateTargetRequest()
     */
    @objid ("d95f1690-55b6-11e2-877f-002564c97630")
    @Override
    protected void updateTargetRequest() {
        repairStartLocation();
        ChangeBoundsRequest request = (ChangeBoundsRequest) getTargetRequest();
        request.setEditParts(getOperationSet());
        Dimension delta = getDragMoveDelta();
        
        // constrains the move to dx=0, dy=0, or dx=dy if shift is depressed
        if (getCurrentInput().isShiftKeyDown()) {
            request.setConstrainedMove(true);
            float ratio = 0;
        
            if (delta.width != 0)
                ratio = (float) delta.height / (float) delta.width;
        
            ratio = Math.abs(ratio);
            if (ratio > 0.5 && ratio < 1.5) {
                if (Math.abs(delta.height) > Math.abs(delta.width)) {
                    if (delta.height > 0)
                        delta.height = Math.abs(delta.width);
                    else
                        delta.height = -Math.abs(delta.width);
                } else {
                    if (delta.width > 0)
                        delta.width = Math.abs(delta.height);
                    else
                        delta.width = -Math.abs(delta.height);
                }
            } else {
                if (Math.abs(delta.width) > Math.abs(delta.height))
                    delta.height = 0;
                else
                    delta.width = 0;
            }
        } else
            request.setConstrainedMove(false);
        
        Point moveDelta = new Point(delta.width, delta.height);
        request.getExtendedData().clear();
        request.setMoveDelta(moveDelta);
        
        // TODO : snap?
        // snapPoint(request);
        
        request.setLocation(getLocation());
        request.setType(getCommandName());
    }

    @objid ("7752f87d-2f24-4f88-b4ec-fc87fd1e67d9")
    boolean navigateNextAnchor(final int direction) {
        EditPart focus = getCurrentViewer().getFocusEditPart();
        AccessibleAnchorProvider provider;
        provider = focus.getAdapter(AccessibleAnchorProvider.class);
        if (provider == null)
            return false;
        
        List<?> list = provider.getSourceAnchorLocations();
        
        Point start = getLocation();
        int distance = Integer.MAX_VALUE;
        Point next = null;
        for (int i = 0; i < list.size(); i++) {
            Point p = (Point) list.get(i);
            if (p.equals(start) || (direction != 0 && (start.getPosition(p) != direction)))
                continue;
            int d = p.getDistanceOrthogonal(start);
            if (d < distance) {
                distance = d;
                next = p;
            }
        }
        
        if (next != null) {
            placeMouseInViewer(next);
            return true;
        }
        return false;
    }

}
