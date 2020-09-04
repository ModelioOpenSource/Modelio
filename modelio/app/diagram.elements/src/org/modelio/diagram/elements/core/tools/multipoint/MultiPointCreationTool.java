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

package org.modelio.diagram.elements.core.tools.multipoint;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartListener;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.SharedCursors;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.gef.tools.AbstractTool;
import org.eclipse.gef.tools.TargetingTool;
import org.eclipse.swt.graphics.Cursor;

/**
 * A tool that works like a Connection creation tool, only it can accept more than 2 extremities and then 1
 * "central point"
 * 
 * @author fpoyer
 */
@objid ("80f28d5a-1dec-11e2-8cad-001ec947c8cc")
public class MultiPointCreationTool extends TargetingTool {
    /**
     * The state which indicates that the connection creation has begun. This means that the source of the connection
     * has been identified, and the user is still to determine the target.
     */
    @objid ("80f28d5e-1dec-11e2-8cad-001ec947c8cc")
    protected static final int STATE_MULTIPOINT_STARTED = AbstractTool.MAX_STATE << 1;

    /**
     * The max state.
     */
    @objid ("80f28d62-1dec-11e2-8cad-001ec947c8cc")
    @SuppressWarnings("hiding")
    protected static final int MAX_STATE = STATE_MULTIPOINT_STARTED;

    @objid ("80f28d66-1dec-11e2-8cad-001ec947c8cc")
    private static final int FLAG_SOURCE_FEEDBACK = TargetingTool.MAX_FLAG << 1;

    /**
     * The max flag.
     */
    @objid ("80f28d68-1dec-11e2-8cad-001ec947c8cc")
    @SuppressWarnings("hiding")
    protected static final int MAX_FLAG = FLAG_SOURCE_FEEDBACK;

    @objid ("63927e81-1e83-11e2-8cad-001ec947c8cc")
    private List<EditPart> connectionSources = new ArrayList<>();

    @objid ("63927e84-1e83-11e2-8cad-001ec947c8cc")
    private CreationFactory factory;

    @objid ("63927e85-1e83-11e2-8cad-001ec947c8cc")
    private EditPartViewer viewer;

    @objid ("63927e86-1e83-11e2-8cad-001ec947c8cc")
    private org.eclipse.gef.EditPartListener.Stub deactivationListener = new EditPartListener.Stub() {
        @Override
        public void partDeactivated(EditPart editpart) {
            handleSourceDeactivated();
        }
    };

    /**
     * The default constructor
     */
    @objid ("80f4efa0-1dec-11e2-8cad-001ec947c8cc")
    public MultiPointCreationTool() {
        setDefaultCursor(SharedCursors.CURSOR_PLUG);
        setDisabledCursor(SharedCursors.CURSOR_PLUG_NOT);
    }

    /**
     * Constructs a new MultiPointCreationTool with the given creation factory.
     * 
     * @param factory the creation factory
     */
    @objid ("80f751f5-1dec-11e2-8cad-001ec947c8cc")
    public MultiPointCreationTool(final CreationFactory factory) {
        this();
        setFactory(factory);
    }

    @objid ("80f751fc-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void deactivate() {
        eraseSourceFeedback();
        clearConnectionSources();
        super.deactivate();
        setState(STATE_TERMINAL);
        this.viewer = null;
    }

    /**
     * Sets the creation factory used in the request.
     * 
     * @param factory the factory
     */
    @objid ("80f751ff-1dec-11e2-8cad-001ec947c8cc")
    public void setFactory(final CreationFactory factory) {
        this.factory = factory;
    }

    /**
     * Add to the list of the source editparts for the creation
     * 
     * @param source the additionnal source editpart node
     */
    @objid ("80f75206-1dec-11e2-8cad-001ec947c8cc")
    protected void addConnectionSource(final EditPart source) {
        if (source != null) {
            source.addEditPartListener(this.deactivationListener);
            this.connectionSources.add(source);
        }
    }

    @objid ("80f7520d-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Cursor calculateCursor() {
        // TODO handle the 4 states: invalid, valid start, valid middle, valid end.
        if (isInState(STATE_INITIAL)) {
            if (getCurrentCommand() != null) {
                return getDefaultCursor();
            }
        }
        return super.calculateCursor();
    }

    @objid ("80f75212-1dec-11e2-8cad-001ec947c8cc")
    protected void clearConnectionSources() {
        for (EditPart connectionSource : this.connectionSources) {
            connectionSource.removeEditPartListener(this.deactivationListener);
        }
        this.connectionSources.clear();
    }

    @objid ("80f75214-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Request createTargetRequest() {
        CreateMultiPointRequest req = new CreateMultiPointRequest();
        req.setFactory(getFactory());
        return req;
    }

    @objid ("80f7521b-1dec-11e2-8cad-001ec947c8cc")
    protected void eraseSourceFeedback() {
        if (!isShowingSourceFeedback()) {
            return;
        }
        setFlag(FLAG_SOURCE_FEEDBACK, false);
        for (EditPart connectionSource : this.connectionSources) {
            connectionSource.eraseSourceFeedback(getSourceRequest());
        }
    }

    @objid ("80f7521d-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected String getCommandName() {
        assert (false) : "this method should never get called!";
        return null;
    }

    @objid ("80f75222-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected String getDebugName() {
        return "MultiPoint Creation Tool"; //$NON-NLS-1$
    }

    @objid ("80f75227-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected String getDebugNameForState(final int s) {
        // TODO: more states than this.
        if (s == STATE_MULTIPOINT_STARTED || s == STATE_ACCESSIBLE_DRAG_IN_PROGRESS)
         {
            return "Multi Point Started";//$NON-NLS-1$
        }
        return super.getDebugNameForState(s);
    }

    /**
     * Returns the creation factory that will be used with the create connection request.
     * 
     * @return the creation factory
     */
    @objid ("80f7522e-1dec-11e2-8cad-001ec947c8cc")
    protected CreationFactory getFactory() {
        return this.factory;
    }

    /**
     * Returns the request sent to the each source node. Each source node receives the same request, the only difference
     * is that at that time the request will be typed as {@link RequestConstants#REQ_CONNECTION_START}.
     * 
     * @return the request used with the source node editpart
     */
    @objid ("80f75235-1dec-11e2-8cad-001ec947c8cc")
    protected Request getSourceRequest() {
        return getTargetRequest();
    }

    @objid ("80f7523c-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected CreateMultiPointRequest getTargetRequest() {
        return (CreateMultiPointRequest) super.getTargetRequest();
    }

    /**
     * When the button is first pressed, the source node and its command contribution are determined and locked in.
     * After that time, the tool will be looking for additional sources or the target node to complete the connection
     * @see org.eclipse.gef.tools.AbstractTool#handleButtonDown(int)
     * 
     * @param button which button is pressed
     * @return <code>true</code> if the button down was processed
     */
    @objid ("80f9b450-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected boolean handleButtonDown(final int button) {
        final Object reqType = getTargetRequest().getType();
        // If the request is in final state and user click, try to complete creation
        if (button == 1 && CreateMultiPointRequest.REQ_MULTIPOINT_LAST.equals(reqType)) {
            //stateTransition(STATE_MULTIPOINT_STARTED, STATE_TERMINAL): not true, may also happen from initial state!
            setState(STATE_TERMINAL);
            return handleCreation();
        }
        
        if (isInState(STATE_INITIAL | STATE_MULTIPOINT_STARTED) && button == 1) {
            // Update location and set type to either REQ_MULTIPOINT_FIRST or REQ_MULTIPOINT_ADDITIONAL
            updateTargetRequest();
            // Look up a target edit part. If none found, switch request type to REQ_MULTIPOINT_FINAL and look up again
            updateTargetUnderMouse();
            if (getTargetEditPart() != null) {
                Command command = getCommand();
                if (command != null) {
                    if (CreateMultiPointRequest.REQ_MULTIPOINT_FIRST.equals(reqType) ||
                        CreateMultiPointRequest.REQ_MULTIPOINT_ADDITIONAL.equals(reqType)) {
                        // Add first and additional targets to source list, but NOT final!
                        addConnectionSource(getTargetEditPart());
                        getTargetRequest().addSourceEditPart(getTargetEditPart());
                    }
        
                    getTargetRequest().addStartCommand(command);
                    setState(STATE_MULTIPOINT_STARTED);
                    setCurrentCommand(command);
                    this.viewer = getCurrentViewer();
                }
            }
        }
        
        if (isInState(STATE_INITIAL) && button != 1) {
            setState(STATE_INVALID);
            handleInvalidInput();
        }
        if (isInState(STATE_MULTIPOINT_STARTED)) {
            // Fake a drag to cause feedback to be displayed immediately on mouse down.
            handleDrag();
        }
        return true;
    }

    /**
     * Unloads or resets the tool if the state is in the terminal or invalid state.
     */
    @objid ("80f9b458-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected boolean handleButtonUp(final int button) {
        if (isInState(STATE_TERMINAL | STATE_INVALID)) {
            handleFinished();
        }
        return true;
    }

    @objid ("80f9b460-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected boolean handleCommandStackChanged() {
        if (!isInState(STATE_INITIAL)) {
            if (getCurrentInput().isMouseButtonDown(1)) {
                setState(STATE_INVALID);
            } else {
                setState(STATE_INITIAL);
            }
            handleInvalidInput();
            return true;
        }
        return false;
    }

    /**
     * Method that is called when the gesture to create the connection has been received. Subclasses may extend or
     * override this method to do additional creation setup, such as prompting the user to choose an option about the
     * connection being created. Returns <code>true</code> to indicate that the connection creation succeeded.
     * 
     * @return <code>true</code> if the connection creation was performed
     */
    @objid ("80f9b465-1dec-11e2-8cad-001ec947c8cc")
    protected boolean handleCreation() {
        eraseSourceFeedback();
        Command endCommand = getCommand();
        setCurrentCommand(endCommand);
        executeCurrentCommand();
        return true;
    }

    @objid ("80f9b46a-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected boolean handleDrag() {
        if (isInState(STATE_MULTIPOINT_STARTED)) {
            return handleMove();
        }
        return false;
    }

    @objid ("80f9b46f-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected boolean handleDragInProgress() {
        if (isInState(STATE_ACCESSIBLE_DRAG_IN_PROGRESS)) {
            return handleMove();
        }
        return false;
    }

    @objid ("80f9b474-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected boolean handleEnteredEditPart() {
        showTargetFeedback();
        return true;
    }

    @objid ("80f9b479-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected boolean handleFocusLost() {
        if (isInState(STATE_MULTIPOINT_STARTED)) {
            eraseSourceFeedback();
            eraseTargetFeedback();
            setState(STATE_INVALID);
            handleFinished();
        }
        return super.handleFocusLost();
    }

    @objid ("80f9b47e-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected boolean handleHover() {
        if (isInState(STATE_MULTIPOINT_STARTED)) {
            updateAutoexposeHelper();
        }
        return true;
    }

    @objid ("80f9b483-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected boolean handleInvalidInput() {
        eraseSourceFeedback();
        clearConnectionSources();
        return super.handleInvalidInput();
    }

    @objid ("80f9b488-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected boolean handleMove() {
        if (isInState(STATE_MULTIPOINT_STARTED) && this.viewer != getCurrentViewer()) {
            return false;
        }
        if (isInState(STATE_INITIAL | STATE_MULTIPOINT_STARTED | STATE_ACCESSIBLE_DRAG_IN_PROGRESS)) {
            updateTargetRequest();
            updateTargetUnderMouse();
            showSourceFeedback();
            showTargetFeedback();
            setCurrentCommand(getCommand());
        }
        return true;
    }

    /**
     * Called if the source editpart is deactivated for some reason during the creation process. For example, the user
     * performs an Undo while in the middle of creating a connection, which undoes a prior command which created the
     * source.
     */
    @objid ("80f9b48d-1dec-11e2-8cad-001ec947c8cc")
    protected void handleSourceDeactivated() {
        setState(STATE_INVALID);
        handleInvalidInput();
        handleFinished();
    }

    /**
     * Returns <code>true</code> if feedback is being shown.
     * 
     * @return <code>true</code> if showing source feedback
     */
    @objid ("80f9b490-1dec-11e2-8cad-001ec947c8cc")
    protected boolean isShowingSourceFeedback() {
        return getFlag(FLAG_SOURCE_FEEDBACK);
    }

    /**
     * Sends a show feedback request to the source editpart and sets the feedback flag.
     */
    @objid ("80f9b495-1dec-11e2-8cad-001ec947c8cc")
    protected void showSourceFeedback() {
        for (EditPart connectionSource : this.connectionSources) {
            connectionSource.showSourceFeedback(getSourceRequest());
        }
        setFlag(FLAG_SOURCE_FEEDBACK, true);
    }

    @objid ("80f9b498-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void updateTargetRequest() {
        CreateMultiPointRequest request = getTargetRequest();
        if (isInState(STATE_INITIAL)) {
            request.setType(CreateMultiPointRequest.REQ_MULTIPOINT_FIRST);
        } else {
            request.setType(CreateMultiPointRequest.REQ_MULTIPOINT_ADDITIONAL);
        }
        request.setLocation(getLocation());
    }

    /**
     * Overridden because the request has to be presented a second time as REQ_MULTIPOINT_LAST type if not accepted in
     * its current type.
     */
    @objid ("80f9b49b-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected boolean updateTargetUnderMouse() {
        if (!isTargetLocked()) {
            EditPart editPart = getCurrentViewer().findObjectAtExcluding(getLocation(),
                                                                         getExclusionSet(),
                                                                         getTargetingConditional());
            if (editPart == null || editPart.getTargetEditPart(getTargetRequest()) == null) {
                // We didn't find a candidate accepting the request in its current state (either REQ_MULTIPOINT_FIRST
                // or REQ_MULTIPOINT_ADDITIONAL), so let's try to find one that will accept REQ_MULTIPOINT_FINAL.
                getTargetRequest().setType(CreateMultiPointRequest.REQ_MULTIPOINT_LAST);
                editPart = getCurrentViewer().findObjectAtExcluding(getLocation(),
                                                                    getExclusionSet(),
                                                                    getTargetingConditional());
            }
            if (editPart != null) {
                editPart = editPart.getTargetEditPart(getTargetRequest());
            }
            boolean changed = getTargetEditPart() != editPart;
            setTargetEditPart(editPart);
            return changed;
        } else {
            return false;
        }
    }

}
