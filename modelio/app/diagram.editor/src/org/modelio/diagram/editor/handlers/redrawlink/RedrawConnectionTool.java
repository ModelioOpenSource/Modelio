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

package org.modelio.diagram.editor.handlers.redrawlink;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.AccessibleAnchorProvider;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.tools.AbstractConnectionCreationTool;
import org.eclipse.gef.tools.AbstractTool;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.widgets.Event;
import org.modelio.diagram.elements.core.link.CreateBendedConnectionRequest;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.link.LinkEditPart;
import org.modelio.diagram.elements.core.link.ModelioLinkCreationContext;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.requests.CreateLinkConstants;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * Redraw Link tool for Modelio connections.
 * <p>
 * This tool will support bend points and orthogonal path.
 * 
 * @author fpoyer
 */
@objid ("65be03e1-33f7-11e2-95fe-001ec947c8cc")
public class RedrawConnectionTool extends AbstractConnectionCreationTool {
    @objid ("eac4e4f6-3896-11e2-95fe-001ec947c8cc")
    private ConnectionRouterId primaryRoutingMode = null;

    @objid ("eac9a9ab-3896-11e2-95fe-001ec947c8cc")
    private ConnectionRouterId currentRoutingMode = null;

    @objid ("464476c7-0617-400f-8262-a299fb9c0244")
    private EditPart targetEditPart;

    @objid ("65c0663a-33f7-11e2-95fe-001ec947c8cc")
    private GmLink originalGmLink;

    @objid ("190400fc-b161-48f1-9f82-cd119d839646")
    private Point sourcePoint;

    @objid ("8a655cdb-abfd-4ab7-b1f7-b94d4f08c0b1")
    private IGmDiagram originalDiagram;

    /**
     * Constructs a new RedrawConnectionTool to redraw the given {@link LinkEditPart}
     * 
     * @param linkEditPart the edit part of the link to redraw.
     */
    @objid ("65c0663d-33f7-11e2-95fe-001ec947c8cc")
    public RedrawConnectionTool(final LinkEditPart linkEditPart) {
        super(new ModelioLinkCreationContext(linkEditPart.getModel().getRelatedElement()));
        // CreationFactory factory, final Connection oldConnection, final EditPart targetEditPart
        setUnloadWhenFinished(true);
        
        IFigure ownerFigure = ((Connection) linkEditPart.getFigure()).getSourceAnchor().getOwner();
        Point ownerCenter = ownerFigure.getBounds().getCenter();
        ownerFigure.translateToAbsolute(ownerCenter);
        this.sourcePoint = ownerCenter;
        this.targetEditPart = linkEditPart.getTarget();
        
        this.originalGmLink = linkEditPart.getModel();
        this.originalDiagram = this.originalGmLink.getDiagram();
        this.originalGmLink.delete();
    }

    /**
     * Scrolling can happen either in the {@link AbstractTool#STATE_INITIAL initial} state or once the source of the connection has been {@link AbstractConnectionCreationTool#STATE_CONNECTION_STARTED identified}.
     * @see org.eclipse.gef.Tool#mouseWheelScrolled(org.eclipse.swt.widgets.Event, org.eclipse.gef.EditPartViewer)
     */
    @objid ("65c06642-33f7-11e2-95fe-001ec947c8cc")
    @Override
    public void mouseWheelScrolled(Event event, EditPartViewer viewer) {
        if (isInState(AbstractTool.STATE_INITIAL | AbstractConnectionCreationTool.STATE_CONNECTION_STARTED)) {
            performViewerMouseWheel(event, viewer);
        }
    }

    @objid ("65c06648-33f7-11e2-95fe-001ec947c8cc")
    boolean acceptConnectionFinish(KeyEvent event) {
        return isInState(AbstractTool.STATE_ACCESSIBLE_DRAG_IN_PROGRESS) && event.character == 13;
    }

    @objid ("65c0664d-33f7-11e2-95fe-001ec947c8cc")
    boolean acceptConnectionStart(KeyEvent event) {
        return isInState(AbstractTool.STATE_INITIAL) && event.character == 13;
    }

    @objid ("65c06652-33f7-11e2-95fe-001ec947c8cc")
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
    @objid ("65c06657-33f7-11e2-95fe-001ec947c8cc")
    @Override
    protected Cursor calculateCursor() {
        if (isInState(AbstractTool.STATE_TERMINAL)) {
            return null;
        }
        
        final Command command = getCurrentCommand();
        if (command == null || !command.canExecute()) {
            return getDisabledCursor();
        }
        return getDefaultCursor();
    }

    @objid ("65c0665d-33f7-11e2-95fe-001ec947c8cc")
    @Override
    protected Request createTargetRequest() {
        CreateBendedConnectionRequest req = new CreateBendedConnectionRequest();
        req.setFactory(getFactory());
        req.setType(RequestConstants.REQ_CONNECTION_START);
        return req;
    }

    @objid ("65c06662-33f7-11e2-95fe-001ec947c8cc")
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
    @objid ("65c06666-33f7-11e2-95fe-001ec947c8cc")
    protected ConnectionRouterId getCurrentRoutingMode() {
        if (this.currentRoutingMode == null) {
            StyleKey routerKey = this.originalGmLink.getStyleKey(MetaKey.CONNECTIONROUTER);
            if (routerKey != null) {
                this.currentRoutingMode = this.originalGmLink.getDisplayedStyle().getProperty(routerKey);
            }
        }
        if (this.currentRoutingMode == null) {
            this.currentRoutingMode = getPrimaryRoutingMode();
        }
        return this.currentRoutingMode;
    }

    @objid ("65c0666b-33f7-11e2-95fe-001ec947c8cc")
    @Override
    protected CreateBendedConnectionRequest getTargetRequest() {
        return (CreateBendedConnectionRequest) super.getTargetRequest();
    }

    /**
     * If the connections is already started, the second button down will call {@link AbstractConnectionCreationTool#handleCreateConnection()}. Otherwise, it attempts to start the connection.
     * 
     * @param button the button that was pressed
     * @return <code>true</code> if the button down was processed
     */
    @objid ("65c06670-33f7-11e2-95fe-001ec947c8cc")
    @Override
    protected boolean handleButtonDown(int button) {
        if (button == 1 && isInState(AbstractConnectionCreationTool.STATE_CONNECTION_STARTED)) {
            if (getTargetRequest().getType() == CreateLinkConstants.REQ_CONNECTION_ADD_BENDPOINT) {
                if (handleCreateIntermediatePoint()) {
                    return true;
                }
            }
        
            if (handleCreateConnection()) {
                setState(AbstractTool.STATE_TERMINAL);
                return true;
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
    @objid ("65c06677-33f7-11e2-95fe-001ec947c8cc")
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
    @objid ("65c0667d-33f7-11e2-95fe-001ec947c8cc")
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

    @objid ("65c2c895-33f7-11e2-95fe-001ec947c8cc")
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
    @objid ("65c2c89c-33f7-11e2-95fe-001ec947c8cc")
    @Override
    protected void updateTargetRequest() {
        CreateBendedConnectionRequest request = getTargetRequest();
        request.setLocation(getLocation());
        request.getData().setLastPoint(new Point(getLocation()));
        request.getData().setRoutingMode(getCurrentRoutingMode());
    }

    @objid ("65c2c8a0-33f7-11e2-95fe-001ec947c8cc")
    @Override
    protected boolean updateTargetUnderMouse() {
        if (!isTargetLocked()) {
            getTargetRequest().setType(getCommandName());
            EditPart editPart = getTargetUnderMouse();
        
            if (editPart == null && isInState(AbstractConnectionCreationTool.STATE_CONNECTION_STARTED)) {
                // If the target cannot end the link, ask him to add a bendpoint
                getTargetRequest().setType(CreateLinkConstants.REQ_CONNECTION_ADD_BENDPOINT);
                editPart = getTargetUnderMouse();
            }
        
            return updateTargetEditPart(editPart);
        
        } else {
            return false;
        }
    }

    @objid ("65c2c8a4-33f7-11e2-95fe-001ec947c8cc")
    private boolean acceptSwapRoutingMode(final KeyEvent event) {
        return event.keyCode == SWT.SHIFT;
    }

    /**
     * Get the alternate connection routing mode that is activated when pressing &lt;shift>.
     * 
     * @return the alternate connection routing mode.
     */
    @objid ("65c2c8aa-33f7-11e2-95fe-001ec947c8cc")
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
    @objid ("65c2c8ae-33f7-11e2-95fe-001ec947c8cc")
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
            this.primaryRoutingMode = ConnectionRouterId.BENDPOINT;
        }
        return this.primaryRoutingMode;
    }

    /**
     * Find the target editpart and returns it. The target is searched by using the target conditional and the target request.
     * 
     * @return the edit part that can handle the request under the mouse.
     */
    @objid ("65c2c8b3-33f7-11e2-95fe-001ec947c8cc")
    private EditPart getTargetUnderMouse() {
        EditPart editPart = getCurrentViewer().findObjectAtExcluding(getLocation(),
                getExclusionSet(),
                getTargetingConditional());
        if (editPart != null) {
            editPart = editPart.getTargetEditPart(getTargetRequest());
        }
        return editPart;
    }

    /**
     * Method that is called when the gesture to create an intermediate point has been received. Returns <code>true</code> to indicate that the point creation succeeded.
     * 
     * @return <code>true</code> if the connection point was performed
     */
    @objid ("65c2c8b8-33f7-11e2-95fe-001ec947c8cc")
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

    @objid ("65c2c8bd-33f7-11e2-95fe-001ec947c8cc")
    private boolean isCurrentViewerMirrored2() {
        return (getCurrentViewer().getControl().getStyle() & SWT.MIRRORED) != 0;
    }

    @objid ("65c2c8c1-33f7-11e2-95fe-001ec947c8cc")
    private void swapRoutingMode() {
        if (this.currentRoutingMode == getPrimaryRoutingMode()) {
            this.currentRoutingMode = getAlternateRoutingMode();
        } else {
            this.currentRoutingMode = getPrimaryRoutingMode();
        }
    }

    /**
     * Same as {@link org.eclipse.gef.tools.TargetingTool#setTargetEditPart(EditPart) setTargetEditPart(EditPart)} but returns whether a change was done or not.
     * 
     * @param editPart The new edit part, may be null
     * @return true if the edit part was changed, false if it is still the same.
     */
    @objid ("65c2c8c3-33f7-11e2-95fe-001ec947c8cc")
    private boolean updateTargetEditPart(EditPart editPart) {
        boolean changed = getTargetEditPart() != editPart;
        setTargetEditPart(editPart);
        return changed;
    }

    /**
     * Hacked to simulate a first click at source point at the first possible time.
     */
    @objid ("65c2c8c9-33f7-11e2-95fe-001ec947c8cc")
    @Override
    public void setViewer(final EditPartViewer viewer) {
        EditPartViewer oldViewer = getCurrentViewer();
        super.setViewer(viewer);
        if (viewer != null && !viewer.equals(oldViewer)) {
            getCurrentInput().setMouseLocation(this.sourcePoint.x, this.sourcePoint.y);
            handleButtonDown(1);
            handleButtonUp(1);
        }
    }

    /**
     * Hacked to force target edit part to show some feedback so that user know where to go.
     */
    @objid ("65c2c8cf-33f7-11e2-95fe-001ec947c8cc")
    @Override
    protected void showTargetFeedback() {
        super.showTargetFeedback();
        if (this.targetEditPart != null &&
                (CreateLinkConstants.REQ_CONNECTION_ADD_BENDPOINT.equals(getTargetRequest().getType()) || RequestConstants.REQ_CONNECTION_END.equals(getTargetRequest().getType()))) {
            Object type = getTargetRequest().getType();
            getTargetRequest().setType(RequestConstants.REQ_CONNECTION_END);
            EditPart tmpEP = getTargetRequest().getTargetEditPart();
            getTargetRequest().setTargetEditPart(this.targetEditPart);
            this.targetEditPart.showTargetFeedback(getTargetRequest());
            getTargetRequest().setTargetEditPart(tmpEP);
            getTargetRequest().setType(type);
        }
    }

    /**
     * Hacked to erase the additional feedback shown on target edit part.
     * @see #showTargetFeedback()
     */
    @objid ("65c2c8d3-33f7-11e2-95fe-001ec947c8cc")
    @Override
    protected void eraseTargetFeedback() {
        super.eraseTargetFeedback();
        if (this.targetEditPart != null) {
            this.targetEditPart.eraseTargetFeedback(getTargetRequest());
        }
    }

    @objid ("65c2c8d7-33f7-11e2-95fe-001ec947c8cc")
    @Override
    protected boolean handleCreateConnection() {
        Command command = getCommand();
        boolean handled = command != null && command.canExecute();
        super.handleCreateConnection();
        return handled;
    }

    @objid ("65c2c8dc-33f7-11e2-95fe-001ec947c8cc")
    @Override
    public void deactivate() {
        boolean mustReload = false;
        if (!isInState(AbstractTool.STATE_TERMINAL)) {
            mustReload = true;
        }
        super.deactivate();
        if (mustReload) {
            this.originalDiagram.load();
        }
    }

}
