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

package org.modelio.diagram.elements.core.tools;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.AccessibleAnchorProvider;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.gef.tools.AbstractConnectionCreationTool;
import org.eclipse.gef.tools.AbstractTool;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.widgets.Event;
import org.modelio.diagram.elements.core.link.CreateBendedConnectionRequest;
import org.modelio.diagram.elements.core.link.ModelioLinkCreationContext;
import org.modelio.diagram.elements.core.link.createhandle.UserChoiceLinkCreationFactory;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.requests.CreateLinkConstants;
import org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.ui.gef.SharedCursors2;

/**
 * Link creation tool for Modelio connections.
 * <p>
 * This tool will support bend points at creation and orthogonal path.
 */
@objid ("80df7a72-1dec-11e2-8cad-001ec947c8cc")
public class BendedConnectionCreationTool extends AbstractConnectionCreationTool {
    @objid ("8f156902-1e83-11e2-8cad-001ec947c8cc")
    private ConnectionRouterId primaryRoutingMode = null;

    @objid ("8f156904-1e83-11e2-8cad-001ec947c8cc")
    private ConnectionRouterId currentRoutingMode = null;

    /**
     * Default Constructor.
     */
    @objid ("80df7a78-1dec-11e2-8cad-001ec947c8cc")
    public BendedConnectionCreationTool() {
        setUnloadWhenFinished(false);
    }

    /**
     * Constructs a new ConnectionCreationTool with the given factory.
     * 
     * @param factory the creation factory
     */
    @objid ("80df7a7b-1dec-11e2-8cad-001ec947c8cc")
    public BendedConnectionCreationTool(CreationFactory factory) {
        setFactory(factory);
        setUnloadWhenFinished(false);
    }

    /**
     * Scrolling can happen either in the {@link AbstractTool#STATE_INITIAL initial} state or once the source of the connection has been {@link AbstractConnectionCreationTool#STATE_CONNECTION_STARTED identified}.
     * @see org.eclipse.gef.Tool#mouseWheelScrolled(org.eclipse.swt.widgets.Event, org.eclipse.gef.EditPartViewer)
     */
    @objid ("80df7a81-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void mouseWheelScrolled(Event event, EditPartViewer viewer) {
        if (isInState(AbstractTool.STATE_INITIAL | AbstractConnectionCreationTool.STATE_CONNECTION_STARTED)) {
            performViewerMouseWheel(event, viewer);
        }
    }

    /**
     * Redefined to initialize the default and alternate routing mode.
     */
    @objid ("80e1dcce-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setFactory(final CreationFactory factory) {
        super.setFactory(factory);
    }

    @objid ("80e1dcd6-1dec-11e2-8cad-001ec947c8cc")
    boolean acceptConnectionFinish(KeyEvent event) {
        return isInState(AbstractTool.STATE_ACCESSIBLE_DRAG_IN_PROGRESS) && event.character == 13;
    }

    @objid ("80e1dcdb-1dec-11e2-8cad-001ec947c8cc")
    boolean acceptConnectionStart(KeyEvent event) {
        return isInState(AbstractTool.STATE_INITIAL) && event.character == 13;
    }

    @objid ("80e1dce0-1dec-11e2-8cad-001ec947c8cc")
    boolean navigateNextAnchor(int direction) {
        EditPart focus = getCurrentViewer().getFocusEditPart();
        AccessibleAnchorProvider provider;
        provider = focus.getAdapter(AccessibleAnchorProvider.class);
        if (provider == null) {
            return false;
        }
        
        List<?> list;
        if (isInState(AbstractTool.STATE_ACCESSIBLE_DRAG_IN_PROGRESS)) {
            list = provider.getTargetAnchorLocations();
        } else {
            list = provider.getSourceAnchorLocations();
        }
        
        Point start = getLocation();
        int distance = Integer.MAX_VALUE;
        Point next = null;
        for (int i = 0; i < list.size(); i++) {
            Point p = (Point) list.get(i);
            if (p.equals(start) || (direction != 0 && (start.getPosition(p) != direction))) {
                continue;
            }
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

    /**
     * Redefined to display forbidden cursor on initial node when the command is not executable.
     */
    @objid ("80e1dce5-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Cursor calculateCursor() {
        if (isInState(AbstractTool.STATE_TERMINAL)) {
            return null;
        }
        
        if (isInState(AbstractConnectionCreationTool.STATE_CONNECTION_STARTED)) {
            final Command command = getCurrentCommand();
            if (command == null || !command.canExecute()) {
                return getDisabledCursor();
            }
        
            AbstractTool.Input input = getCurrentInput();
            if (!input.isControlKeyDown() || getTargetRequest().getType() == CreateLinkConstants.REQ_CONNECTION_ADD_BENDPOINT) {
                return SharedCursors2.CURSOR_LINK_BENDPOINT;
            }
        
            if (getFactory() instanceof UserChoiceLinkCreationFactory) {
                return SharedCursors2.CURSOR_LINK_END_MENU;
            }
        
            return getDefaultCursor();
        }
        
        if (isInState(AbstractTool.STATE_INITIAL)) {
            return getDefaultCursor();
        }
        return null;
    }

    @objid ("80e1dceb-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Request createTargetRequest() {
        CreateBendedConnectionRequest req = new CreateBendedConnectionRequest();
        req.setFactory(getFactory());
        req.setType(RequestConstants.REQ_CONNECTION_START);
        req.getExtendedData().put(CreateLinkConstants.PROP_USER_INTERACTION, Boolean.TRUE);
        return req;
    }

    @objid ("80e1dcf8-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected String getCommandName() {
        if (isInState(AbstractConnectionCreationTool.STATE_CONNECTION_STARTED | AbstractTool.STATE_ACCESSIBLE_DRAG_IN_PROGRESS)) {
            return RequestConstants.REQ_CONNECTION_END;
        } else {
            return RequestConstants.REQ_CONNECTION_START;
        }
    }

    /**
     * Get the current routing mode.
     * <p>
     * The routing mode is lazily initialized here.
     * 
     * @return the the current routing mode.
     */
    @objid ("80e1dcfc-1dec-11e2-8cad-001ec947c8cc")
    protected ConnectionRouterId getCurrentRoutingMode() {
        if (this.currentRoutingMode == null) {
            this.currentRoutingMode = getPrimaryRoutingMode();
        }
        return this.currentRoutingMode;
    }

    @objid ("80e1dd01-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected CreateBendedConnectionRequest getTargetRequest() {
        return (CreateBendedConnectionRequest) super.getTargetRequest();
    }

    /**
     * Process "Mouse down" events. The left (button 1) and right (button 3) buttons are used.
     * 
     * <ol>
     * <li>STATE_INITIAL: NO connection has been started yet</li>
     * <ul>
     * <li>left button click => starts a new connection</li>
     * <li>right button click => no specific operation for this tool</li>
     * </ul>
     * <li>STATE_CONNECTION_STARTED: a connection has already been started, and is being drawn</li>
     * <ul>
     * <li>left button click => terminates the connection or add a bend point depending on the requests type</li>
     * <li>CTRL-left click => add a bend point</li>
     * <li>right button click => terminates the connection using a special REQ_CONNECTION_CREATE_LINK_CHOOSENODE request that can be processed by specific policies to create both a node and a link. Note that the tool removes the viewer menu in order to
     * avoid the right click to pop it up which is unexpected here. The menu is restored when the interaction ends.</li>
     * </ul>
     * 
     * 
     * </ol>
     * 
     * @param button the button that was pressed
     * @return <code>true</code> if the button down was processed
     */
    @objid ("80e1dd06-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected boolean handleButtonDown(int button) {
        AbstractTool.Input input = getCurrentInput();
        
        if (isInState(AbstractConnectionCreationTool.STATE_CONNECTION_STARTED)) {
            if (button == 1) {
                CreateBendedConnectionRequest r = getTargetRequest();
                if (r.getType() == CreateLinkConstants.REQ_CONNECTION_ADD_BENDPOINT) {
                    if (handleCreateIntermediatePoint()) {
                        return true;
                    }
                }
                if (handleCreateConnection()) {
                    setState(AbstractTool.STATE_TERMINAL);
                    return true;
                }
            }
        }
        
        if (isInState(AbstractTool.STATE_INITIAL) && button == 1) {
            // Call inherited behavior if nothing was already done
            super.handleButtonDown(button);
            if (getCurrentCommand() != null) {
                getTargetRequest().getData().setSrcPoint(getLocation());
            }
        } else {
            // Call inherited behavior if nothing was already done
            super.handleButtonDown(button);
        }
        
        if (isInState(AbstractConnectionCreationTool.STATE_CONNECTION_STARTED)) {
            // Fake a drag to cause feedback to be displayed immediately on mouse down.
            handleDrag();
        }
        return true;
    }

    /**
     * Cleans up feedback and resets the tool when focus is lost.
     * 
     * @return <code>true</code> if this focus lost event was processed
     */
    @objid ("80e1dd0d-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected boolean handleFocusLost() {
        if (isInState(AbstractConnectionCreationTool.STATE_CONNECTION_STARTED | AbstractTool.STATE_ACCESSIBLE_DRAG_IN_PROGRESS)) {
            eraseSourceFeedback();
            eraseTargetFeedback();
            setState(AbstractTool.STATE_INVALID);
            handleFinished();
        }
        return super.handleFocusLost();
    }

    /**
     * Processes the arrow keys (to move the cursor to nearby anchor locations) and the enter key (to start or complete a connections).
     * 
     * @param event the key event
     * @return <code>true</code> if this key down event was processed
     */
    @objid ("80e1dd13-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected boolean handleKeyDown(KeyEvent event) {
        if (acceptArrowKey(event)) {
            int direction = 0;
            switch (event.keyCode) {
            case SWT.ARROW_DOWN:
                direction = PositionConstants.SOUTH;
                break;
            case SWT.ARROW_UP:
                direction = PositionConstants.NORTH;
                break;
            case SWT.ARROW_RIGHT:
                direction = isCurrentViewerMirrored2() ? PositionConstants.WEST : PositionConstants.EAST;
                break;
            case SWT.ARROW_LEFT:
                direction = isCurrentViewerMirrored2() ? PositionConstants.EAST : PositionConstants.WEST;
                break;
            }
        
            boolean consumed = false;
            if (direction != 0 && event.stateMask == 0) {
                consumed = navigateNextAnchor(direction);
            }
            if (!consumed) {
                event.stateMask |= SWT.CONTROL;
                event.stateMask &= ~SWT.SHIFT;
                if (getCurrentViewer().getKeyHandler().keyPressed(event)) {
                    navigateNextAnchor(0);
                    updateTargetRequest();
                    updateTargetUnderMouse();
                    Command command = getCommand();
                    if (command != null) {
                        setCurrentCommand(command);
                    }
                    return true;
                }
            }
        }
        
        if (event.character == '/' || event.character == '\\') {
            event.stateMask |= SWT.CONTROL;
            if (getCurrentViewer().getKeyHandler().keyPressed(event)) {
                navigateNextAnchor(0);
                return true;
            }
        }
        
        if (acceptConnectionStart(event)) {
            Command command = getCommand();
            if (command != null && command.canExecute()) {
                updateTargetUnderMouse();
                setConnectionSource(getTargetEditPart());
                ((CreateConnectionRequest) getTargetRequest()).setSourceEditPart(getTargetEditPart());
                setState(AbstractTool.STATE_ACCESSIBLE_DRAG_IN_PROGRESS);
                placeMouseInViewer(getLocation().getTranslated(6, 6));
            }
            return true;
        }
        
        if (acceptConnectionFinish(event)) {
            Command command = getCommand();
            if (command != null && command.canExecute()) {
                setState(AbstractTool.STATE_INITIAL);
                placeMouseInViewer(getLocation().getTranslated(6, 6));
                eraseSourceFeedback();
                eraseTargetFeedback();
                setCurrentCommand(command);
                executeCurrentCommand();
            }
            return true;
        }
        
        if (acceptSwapRoutingMode(event)) {
            swapRoutingMode();
        
            updateTargetRequest();
            showSourceFeedback();
        
            return true;
        }
        return super.handleKeyDown(event);
    }

    @objid ("80e1dd1a-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected boolean handleKeyUp(final KeyEvent e) {
        if (acceptSwapRoutingMode(e)) {
            swapRoutingMode();
        
            updateTargetRequest();
            showSourceFeedback();
        
            return true;
        }
        return super.handleKeyUp(e);
    }

    /**
     * @see org.eclipse.gef.tools.TargetingTool#updateTargetRequest()
     */
    @objid ("80e1dd21-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void updateTargetRequest() {
        CreateBendedConnectionRequest request = getTargetRequest();
        request.setLocation(getLocation());
        request.getData().setLastPoint(new Point(getLocation()));
        request.getData().setRoutingMode(getCurrentRoutingMode());
    }

    @objid ("80e1dd25-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected boolean updateTargetUnderMouse() {
        if (!isTargetLocked()) {
            String requestType = getCommandName();
        
            EditPart editPart = findTargetUnderMouse(requestType);
        
            if (editPart == null && isInState(AbstractConnectionCreationTool.STATE_CONNECTION_STARTED)) {
                // If the target cannot end the link, ask him to add a bendpoint
                requestType = CreateLinkConstants.REQ_CONNECTION_ADD_BENDPOINT;
                editPart = findTargetUnderMouse(requestType);
            }
        
            return updateTargetEditPart(editPart, requestType);
        
        } else {
            return false;
        }
    }

    @objid ("80e43f26-1dec-11e2-8cad-001ec947c8cc")
    private boolean acceptSwapRoutingMode(final KeyEvent event) {
        return event.keyCode == SWT.SHIFT;
    }

    /**
     * Get the alternate connection routing mode that is activated when pressing &lt;shift>.
     * 
     * @return the alternate connection routing mode.
     */
    @objid ("80e43f2c-1dec-11e2-8cad-001ec947c8cc")
    private ConnectionRouterId getAlternateRoutingMode() {
        switch (this.primaryRoutingMode) {
        case BENDPOINT:
        case DIRECT:
            return ConnectionRouterId.ORTHOGONAL;
        case ORTHOGONAL:
        default:
            return ConnectionRouterId.BENDPOINT;
        }
    }

    /**
     * Get the primary routing mode.
     * <p>
     * The primary routing mode is lazily initialized from the routing mode style key.
     * 
     * @return the primary routing mode.
     */
    @objid ("80e43f30-1dec-11e2-8cad-001ec947c8cc")
    private ConnectionRouterId getPrimaryRoutingMode() {
        if (getFactory() instanceof ModelioLinkCreationContext) {
            ModelioLinkCreationContext ctx = (ModelioLinkCreationContext) getFactory();
            IGmObject gmDiagram = (IGmObject) getCurrentViewer().getContents().getModel();
            final StyleKey routingModeKey = ctx.getDefaultRoutingModeKey();
            if (routingModeKey != null) {
                this.primaryRoutingMode = gmDiagram.getDisplayedStyle().getProperty(routingModeKey);
            }
        }
        
        if (this.primaryRoutingMode == null) {
            this.primaryRoutingMode = ConnectionRouterId.ORTHOGONAL;
        }
        return this.primaryRoutingMode;
    }

    /**
     * Find the target editpart and returns it.
     * <p>
     * The target is searched by using the target conditional and the target request temporarily modified to the given request type.
     * 
     * @param requestType The request type to try.
     * @return the edit part that can handle the request under the mouse.
     */
    @objid ("80e43f35-1dec-11e2-8cad-001ec947c8cc")
    protected EditPart findTargetUnderMouse(Object requestType) {
        CreateBendedConnectionRequest targetRequest = getTargetRequest();
        
        Object savedType = targetRequest.getType();
        targetRequest.setType(requestType);
        
        EditPart editPart = getCurrentViewer().findObjectAtExcluding(getLocation(),
                getExclusionSet(),
                getTargetingConditional());
        if (editPart != null) {
            editPart = editPart.getTargetEditPart(targetRequest);
        }
        
        targetRequest.setType(savedType);
        return editPart;
    }

    /**
     * Method that is called when the gesture to create an intermediate point has been received. Returns <code>true</code> to indicate that the point creation succeeded.
     * 
     * @return <code>true</code> if the connection point was performed
     */
    @objid ("80e43f3c-1dec-11e2-8cad-001ec947c8cc")
    private boolean handleCreateIntermediatePoint() {
        Command endCommand = getCommand();
        if (endCommand != null) {
            final CreateBendedConnectionRequest r = getTargetRequest();
            final Point newPoint = r.getLocation();
        
            r.getData().getPath().add(newPoint);
            setCurrentCommand(endCommand);
            showSourceFeedback();
            return true;
        }
        return false;
    }

    @objid ("80e43f41-1dec-11e2-8cad-001ec947c8cc")
    private boolean isCurrentViewerMirrored2() {
        return (getCurrentViewer().getControl().getStyle() & SWT.MIRRORED) != 0;
    }

    @objid ("80e43f45-1dec-11e2-8cad-001ec947c8cc")
    private void swapRoutingMode() {
        if (this.currentRoutingMode == getPrimaryRoutingMode()) {
            this.currentRoutingMode = getAlternateRoutingMode();
        } else {
            this.currentRoutingMode = getPrimaryRoutingMode();
        }
    }

    /**
     * Same as {@link org.eclipse.gef.tools.TargetingTool#setTargetEditPart(EditPart) setTargetEditPart(EditPart)} but returns whether a change was done or not.
     * @param requestType the new request
     * 
     * @param editPart The new edit part, may be null
     * @return true if the edit part was changed, false if it is still the same.
     */
    @objid ("80e43f47-1dec-11e2-8cad-001ec947c8cc")
    protected final boolean updateTargetEditPart(EditPart editPart, Object newRequestType) {
        EditPart oldTargetEditPart = getTargetEditPart();
        boolean changed = oldTargetEditPart != editPart;
        
        setTargetEditPart(editPart);
        
        CreateBendedConnectionRequest targetRequest = getTargetRequest();
        
        if (targetRequest.getType() != newRequestType) {
            eraseTargetFeedback();
            // don't call eraseSourceFeedback();
        
            targetRequest.setType(newRequestType);
        
            showSourceFeedback();
            showTargetFeedback();
        }
        return changed;
    }

    @objid ("80e43f4f-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected boolean handleCreateConnection() {
        super.handleCreateConnection();
        EditPartViewer viewer = getCurrentViewer();
        selectAddedObject(viewer);
        return true;
    }

    /**
     * Add the newly created object to the viewer's selected objects.
     */
    @objid ("80e43f54-1dec-11e2-8cad-001ec947c8cc")
    protected void selectAddedObject(EditPartViewer viewer) {
        final List<Object> models = getTargetRequest().getCreatedObjectsToSelect();
        if (models == null || models.isEmpty() || viewer == null) {
            return;
        }
        
        viewer.flush();
        viewer.deselectAll();
        for (Object model : models) {
            EditPart editpart = (EditPart) viewer.getEditPartRegistry().get(model);
            if (editpart != null && editpart.isSelectable()) {
                // Force the new object to get positioned in the viewer.
                viewer.appendSelection(editpart);
            }
        }
    }

}
