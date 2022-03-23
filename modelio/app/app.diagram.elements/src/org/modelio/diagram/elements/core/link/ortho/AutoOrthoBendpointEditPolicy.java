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
package org.modelio.diagram.elements.core.link.ortho;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Bendpoint;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editpolicies.SelectionHandlesEditPolicy;
import org.eclipse.gef.handles.BendpointMoveHandle;
import org.eclipse.gef.handles.ConnectionHandle;
import org.eclipse.gef.requests.BendpointRequest;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.modelio.diagram.elements.common.abstractdiagram.GmAbstractDiagram;
import org.modelio.diagram.elements.core.figures.geometry.Direction;
import org.modelio.diagram.elements.core.figures.geometry.Orientation;
import org.modelio.diagram.elements.core.figures.routers.AutoOrthogonalRouter;
import org.modelio.diagram.elements.core.figures.routers.ConnectionState;
import org.modelio.diagram.elements.core.figures.routers.OrthogonalRectifierRouter;
import org.modelio.diagram.elements.core.link.GmPath;
import org.modelio.diagram.elements.core.link.IAnchorModelProvider;
import org.modelio.diagram.elements.core.link.MPoint;
import org.modelio.diagram.elements.core.link.path.BendPointUtils;
import org.modelio.diagram.elements.core.link.path.ConnectionPolicyUtils;
import org.modelio.diagram.elements.core.link.path.ILinkPathEditor;
import org.modelio.diagram.elements.core.link.path.ILinkPathEditorFactory;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.model.IGmLinkObject;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.model.IGmPath;
import org.modelio.diagram.elements.core.policies.DefaultResizeTracker.FeedbackChangeBoundsRequest;
import org.modelio.diagram.elements.core.policies.SelectionHandlesBuilder;
import org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId;

/**
 * Auto Orthogonal router bendpoints edit policy.
 * 
 * @since 5.0.2
 */
@objid ("aa94dcab-566e-4bc6-83b5-2e70b6151b76")
public class AutoOrthoBendpointEditPolicy extends SelectionHandlesEditPolicy implements PropertyChangeListener {
    @objid ("eab9c7cd-b72a-4ab7-ad90-ffe2d9e71844")
    private static final double CONSTANT_FACTOR = 1000.0;

    @objid ("abd6d2aa-b77f-4002-8795-b0cb416bae23")
    private static final int SNAP_DIST = 7;

    @objid ("1e49b10f-73fa-48bb-a119-7065ca745ee4")
    private boolean isActive;

    @objid ("0027e003-f3f6-494c-9503-737f3749880e")
    private static final Point TMP1 = new PrecisionPoint();

    @objid ("6c7d57b6-b4e9-40fd-a22e-03710ca69f74")
    private static final Point TMP2 = new PrecisionPoint();

    @objid ("ec29bec3-c261-4fb9-a26c-c23e62a1db5d")
    private ConnectionState originalState;

    @objid ("6019625a-5818-4396-8d1f-3b68acbefbc4")
    private Command moveCommand;

    @objid ("9f40c72d-be94-4dc3-8d9d-8503e3e17be1")
    private ConnectionState frozenOriginalState;

    /**
     * <code>activate()</code> is extended to add a listener to the <code>Connection</code> figure.
     */
    @objid ("5a1b5cc9-675d-4a82-87fb-0db0b48ef68c")
    @Override
    public void activate() {
        assert isValidConstraint() : getConnection().getRoutingConstraint();
        this.isActive = true;
        
        super.activate();
        getConnection().addPropertyChangeListener(Connection.PROPERTY_POINTS, this);
        ((IGmObject) getHost().getModel()).getDiagram().addPropertyChangeListener(this);
        
    }

    @objid ("d4cbcffc-36ca-4ef5-91b7-fdf6c23ea4c9")
    private boolean isValidConstraint() {
        final Object routingConstraint = getConnection().getRoutingConstraint();
        if (routingConstraint == null) {
            return true;
        }
        
        if (!(routingConstraint instanceof List)) {
            return false;
        }
        
        List<?> l = (List<?>) routingConstraint;
        if (l.isEmpty()) {
            return true;
        }
        return l.get(0) instanceof MPoint;
    }

    /**
     * <code>deactivate()</code> is extended to remove the property change listener on the <code>Connection</code> figure.
     */
    @objid ("34707d9a-1477-48dc-964e-1e1d7a74cb0f")
    @Override
    public void deactivate() {
        getConnection().removePropertyChangeListener(Connection.PROPERTY_POINTS, this);
        ((IGmObject) getHost().getModel()).getDiagram().removePropertyChangeListener(this);
        super.deactivate();
        this.isActive = false;
        
    }

    /**
     * Redefined to protect against reentrance in headless mode.
     */
    @objid ("97e12322-29ce-436d-a921-3dba369a4eb4")
    @Override
    protected void addSelectionHandles() {
        // protect against reentrance in headless mode :
        // super.addSelectionHandles() adds figures that triggers revalidation that triggers connection layout
        
        // Protect against buggy call in deactivated state related to rakes.
        if (!this.isActive) {
            return;
        }
        
        assert isValidConstraint() : getConnection().getRoutingConstraint();
        
        Connection connection = getConnection();
        connection.removePropertyChangeListener(Connection.PROPERTY_POINTS, this);
        
        super.addSelectionHandles();
        
        connection.addPropertyChangeListener(Connection.PROPERTY_POINTS, this);
        
    }

    @objid ("0a3602ca-d855-41d6-98ff-c079d9cac900")
    @Override
    public void eraseSourceFeedback(final Request request) {
        Object type = request.getType();
        if (FeedbackChangeBoundsRequest.REQ_TYPE.equals(type)) {
            restoreOriginalConstraint();
        } else if (ConnectionSegmentTracker.REQ_MOVE_SEGMENT.equals(type)
                || RequestConstants.REQ_MOVE_BENDPOINT.equals(type)) {
            eraseConnectionFeedback((BendpointRequest) request);
        } else if (RequestConstants.REQ_MOVE.equals(type) || RequestConstants.REQ_ADD.equals(type) || RequestConstants.REQ_RESIZE.equals(type)) {
            eraseChangeBoundsFeedback((ChangeBoundsRequest) request);
        }
        if (ConnectionSegmentTracker.REQ_MOVE_SEGMENT.equals(type)) {
            eraseMoveSegmentFeedback((BendpointRequest) request);
        }
        
    }

    /**
     * Factors the Request into either a MOVE, a DELETE, or a CREATE of a bendpoint or a segment.
     */
    @objid ("52d3ea46-8dd1-44e8-857a-22278c6ca397")
    @Override
    public Command getCommand(final Request request) {
        Object type = request.getType();
        if (ConnectionSegmentTracker.REQ_MOVE_SEGMENT.equals(type)) {
            return getMoveSegmentCommand((BendpointRequest) request);
        } else if (RequestConstants.REQ_MOVE_BENDPOINT.equals(type)) {
            return getMoveBendpointCommand((BendpointRequest) request);
        } else if (RequestConstants.REQ_MOVE.equals(type) || RequestConstants.REQ_ADD.equals(type) || RequestConstants.REQ_RESIZE.equals(type)) {
            return getMoveCommand((ChangeBoundsRequest) request);
        } else if (FeedbackChangeBoundsRequest.REQ_TYPE.equals(type)) {
            FeedbackChangeBoundsRequest fbreq = (FeedbackChangeBoundsRequest) request;
            return getMoveCommand(fbreq.getLinkedRequest());
        }
        return null;
    }

    /**
     * @return the host edit part as a ConnectionEditPart.
     */
    @objid ("8eaedf2d-b7a5-4912-be66-76ed28863623")
    @Override
    public ConnectionEditPart getHost() {
        return (ConnectionEditPart) super.getHost();
    }

    /**
     * If the number of bendpoints changes, handles are updated.
     * @see java.beans.PropertyChangeListener#propertyChange(PropertyChangeEvent)
     */
    @objid ("fac776cd-5b70-4742-a83f-8aad2b2003ee")
    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        if (getHost().getSelected() != EditPart.SELECTED_NONE) {
            addSelectionHandles();
        }
        
        // if (this.originalState == null && Connection.PROPERTY_POINTS.equals(evt.getPropertyName())) {
        if (GmAbstractDiagram.PROP_POSTLOADACTIONS_END.equals(evt.getPropertyName())) {
            // Save the current figure routing constraint in the graphic model,
            // without firing any listener.
            Connection connection = getConnection();
            if (connection.getConnectionRouter() instanceof OrthogonalRectifierRouter) {
                // Do not use ChangeLinkRoutingConstraintCommand, anchors must remain unchanged
                // Edit the GmPath directly to avoid firing listeners
                Object routingConstraint = connection.getRoutingConstraint();
                List<MPoint> modelPoints = routingConstraint == null ? Collections.emptyList() : BendPointUtils.copyConstraint((List<MPoint>) routingConstraint);
                IGmPath path = ((IGmLinkObject) getHost().getModel()).getPath();
                path.setPathData(modelPoints);
            }
        }
        
    }

    @objid ("a8cfda88-4f56-4fa8-aa72-b949b894c5fb")
    @Override
    public void showSourceFeedback(final Request request) {
        Object type = request.getType();
        if (RequestConstants.REQ_MOVE_BENDPOINT.equals(type)) {
            showMoveBendpointFeedback((BendpointRequest) request);
        } else if (ConnectionSegmentTracker.REQ_MOVE_SEGMENT.equals(type)) {
            showMoveSegmentFeedback((BendpointRequest) request);
        } else if (RequestConstants.REQ_MOVE.equals(type) || RequestConstants.REQ_ADD.equals(type) || RequestConstants.REQ_RESIZE.equals(type)) {
            showChangeBoundsFeedback((ChangeBoundsRequest) request);
        } else if (FeedbackChangeBoundsRequest.REQ_TYPE.equals(request.getType())) {
            showResizeEndFeedback((FeedbackChangeBoundsRequest) request);
        }
        
    }

    @objid ("0f97d445-af39-470a-8821-d0ce88abfac1")
    protected void showResizeEndFeedback(FeedbackChangeBoundsRequest feedbackRequest) {
        // Before modifying the connection, save its original constraint and anchors so as to be able to cancel if needed.
        if (this.originalState == null) {
            saveOriginalConstraint();
        }
        
        getPathEditor().from(getHost(), this.originalState)
                .applyChangeBoundsRequest(feedbackRequest.getLinkedRequest(), true)
                .applyStateToConnection();
        
    }

    @objid ("4fb0fbd5-921b-4507-8a22-602759b4d575")
    @Override
    public boolean understandsRequest(final Request request) {
        Object type = request.getType();
        if (FeedbackChangeBoundsRequest.REQ_TYPE.equals(type) || RequestConstants.REQ_MOVE.equals(type)) {
            return true;
        }
        return super.understandsRequest(request);
    }

    /**
     * Creates selection handles for the bendpoints. Explicit (user-defined) bendpoints will have {@link BendpointMoveHandle}s on them. Segments between them will have {@link HorizontalSegmentMoveHandle} or {@link VerticalSegmentMoveHandle} on them.
     */
    @objid ("e955b0cb-edec-467d-a2a7-709bec07f239")
    @Override
    protected List<?> createSelectionHandles() {
        boolean userEditable = ((IGmObject) getHost().getModel()).isUserEditable();
        
        List<ConnectionHandle> list = new ArrayList<>();
        ConnectionEditPart connEP = getHost();
        Connection connection = getConnection();
        PointList points = connection.getPoints();
        
        // First segment move handle
        points.getPoint(TMP1, 0);
        points.getPoint(TMP2, 1);
        Orientation orientation = Direction.getMajor(TMP1, TMP2).orientation();
        if (userEditable) {
            if (orientation == Orientation.HORIZONTAL) {
                list.add(new HorizontalSegmentMoveHandle(connEP, 0));
            } else if (orientation == Orientation.VERTICAL) {
                list.add(new VerticalSegmentMoveHandle(connEP, 0));
            }
        }
        
        @SuppressWarnings ("unchecked")
        List<MPoint> constraints = (List<MPoint>) getConnection().getRoutingConstraint();
        
        for (int i = 1; i < points.size() - 1; i++) {
            int idxC = i - 1;
            if (idxC < constraints.size()) {
                MPoint curPoint = constraints.get(idxC);
                if (curPoint.isFixed()) {
                    list.add(new PinOrthoBpMoveHandle(connEP, i, orientation));
                }
            }
        
            if (userEditable) {
                points.getPoint(TMP1, i);
                points.getPoint(TMP2, i + 1);
                orientation = Direction.getMajor(TMP1, TMP2).orientation();
                if (orientation == Orientation.HORIZONTAL) {
                    list.add(new HorizontalSegmentMoveHandle(connEP, i));
                } else if (orientation == Orientation.VERTICAL) {
                    list.add(new VerticalSegmentMoveHandle(connEP, i));
                }
            }
        }
        
        SelectionHandlesBuilder.disableHandlesIfReadOnly(getHost(), list);
        return list;
    }

    /**
     * @param request the move request
     */
    @objid ("ba72d5e7-937b-4785-9b51-04eb4341dd41")
    protected void eraseChangeBoundsFeedback(final ChangeBoundsRequest request) {
        restoreOriginalConstraint();
    }

    /**
     * Erases all bendpoint feedback.
     * <p>
     * Since the original <code>Connection</code> figure is used for feedback, we just restore the original constraint that was saved before feedback started to show.
     * @param request the BendpointRequest
     */
    @objid ("82874c31-d583-49b8-80db-91d518498500")
    protected void eraseConnectionFeedback(final BendpointRequest request) {
        restoreOriginalConstraint();
    }

    /**
     * Convenience method for obtaining the host's <code>Connection</code> figure.
     * @return the Connection figure
     */
    @objid ("83701102-abce-4914-991d-0529fff35225")
    protected Connection getConnection() {
        return (Connection) getHost().getFigure();
    }

    /**
     * @return the model path as a {@link IGmPath}
     */
    @objid ("42839d13-9708-4203-b1ff-12e47c900483")
    protected IGmPath getModelPath() {
        return ((IGmLinkObject) getHost().getModel()).getPath();
    }

    /**
     * @param request the request to use to build the command.
     */
    @objid ("245fee0f-9e6e-456c-a60a-c6888ff5c6f6")
    protected Command getMoveBendpointCommand(final BendpointRequest request) {
        ConnectionEditPart hostEP = getHost();
        return new ApplyConstraintCommand(hostEP);
    }

    /**
     * Get a command for a move request.
     * <p>
     * The request may involve one or more of: - the source node - the target node - a parent of the source node - a parent of the target node - the connection
     * @return the command that updates the connection
     */
    @objid ("60cef883-73ba-48dc-9010-73e2731e91bc")
    protected Command getMoveCommand(final ChangeBoundsRequest request) {
        ConnectionEditPart host = getHost();
        if (!host.isActive() || host.getSource() == null || host.getTarget() == null) {
            return null;
        }
        
        // Before modifying the connection, save its original constraint and anchors so as to be able to cancel if needed.
        if (this.originalState == null) {
            saveOriginalConstraint();
        }
        
        ILinkPathEditorFactory pathEditor = getPathEditor();
        
        if (this.frozenOriginalState == null) {
            this.frozenOriginalState = pathEditor
                    .from(getHost(), this.originalState)
                    .createFrozenStateCopy()
                    .getState()
                    .immutable();
        }
        
        MoveConnectionCommand moveconnCommand = new MoveConnectionCommand(
                host,
                pathEditor,
                this.frozenOriginalState,
                request);
        
        CompoundCommand cmd = new CompoundCommand(moveconnCommand.getLabel());
        cmd.add(moveconnCommand);
        cmd.add(new Command("Reset original constraint") {
            @Override
            public void execute() {
                // Reset field after execution in case 'restoreOriginalConstraint' is not called (e.g. moving with the arrow keys)
                resetSavedState();
            }
        });
        return cmd;
    }

    @objid ("84d43080-3672-4f4a-bdbd-7541fe938639")
    void resetSavedState() {
        this.moveCommand = null;
        this.originalState = null;
        this.frozenOriginalState = null;
        
    }

    /**
     * @param request the request to use to build the command.
     */
    @objid ("7ed513fb-551e-459b-abc1-bfed0e0a4bda")
    protected Command getMoveSegmentCommand(final BendpointRequest request) {
        ConnectionEditPart hostEP = getHost();
        return new ApplyConstraintCommand(hostEP);
    }

    /**
     * Restores the original constraint that was saved before feedback began to show.
     */
    @objid ("d100c891-f4e1-4d64-b805-b31f042204d5")
    protected void restoreOriginalConstraint() {
        if (this.originalState != null) {
            this.originalState.applyTo(getConnection());
        }
        resetSavedState();
        
    }

    /**
     * Since the original figure is used for feedback, this method saves the original constraint, so that is can be restored when the feedback is erased.
     * <p>
     * It also builds an exhaustive routing constraint (== 1 bend point for each visual point of the connection, inclusive of the first and last).
     */
    @objid ("d7a56b1d-8f81-4da7-9da8-89bfe5ce399b")
    protected void saveOriginalConstraint() {
        this.originalState = getPathEditor().from(getHost()).backupConnection().immutable();
        this.moveCommand = null;
        this.frozenOriginalState = null;
        
    }

    /**
     * Show feedback for connected nodes move/resize request.
     * @param request a move/resize request
     */
    @objid ("9a5de569-ada2-4472-89a3-c9577574c42e")
    protected void showChangeBoundsFeedback(final ChangeBoundsRequest request) {
        // Before modifying the connection, save its original constraint and anchors so as to be able to cancel if needed.
        if (this.originalState == null) {
            saveOriginalConstraint();
        }
        
        getPathEditor().from(getHost(), this.originalState)
                .applyChangeBoundsRequest(request, true)
                .applyStateToConnection();
        
    }

    @objid ("52d30e68-a520-4ba2-ad3f-cf022dd587cd")
    protected void showMoveBendpointFeedback(final BendpointRequest request) {
        // Before modifying the connection, save its original constraint, so as to be able to cancel if needed.
        if (this.originalState == null) {
            saveOriginalConstraint();
        }
        
        int reqIndex = request.getIndex();
        Point reqLocation = request.getLocation();
        
        // start again from original constraint
        this.originalState.applyTo(getConnection());
        
        getPathEditor()
                .from(getHost(), this.originalState)
                .moveBendPoint(reqIndex, reqLocation)
                .applyStateToConnection();
        
    }

    /**
     * @return the link path editor
     */
    @objid ("8ecbb039-11c3-4eb8-abda-abf3799f64e4")
    protected ILinkPathEditorFactory getPathEditor() {
        return getPathEditorFactory(getHost());
    }

    @objid ("00e68be0-9d08-4082-8dc9-b2c5d3076e93")
    protected static ILinkPathEditorFactory getPathEditorFactory(EditPart host) {
        return ConnectionPolicyUtils.getRoutingServices(host).getLinkPathEditor(ConnectionRouterId.ORTHOGONAL);
    }

    @objid ("50514584-1084-4c4a-bc8e-293f9991f474")
    protected void showMoveSegmentFeedback(final BendpointRequest request) {
        // Before modifying the connection, save its original constraint and anchors so as to be able to cancel if needed.
        if (this.originalState == null) {
            saveOriginalConstraint();
        }
        
        int reqIndex = request.getIndex();
        Point reqLocation = request.getLocation();
        
        // start again from original constraint
        getPathEditor()
                .from(getHost(), this.originalState)
                .moveSegment(reqIndex, reqLocation)
                .applyStateToConnection();
        
        if (request.getIndex() == 0) {
            // Update source anchor.
            ConnectionEditPart connectionEditPart = getHost();
            NodeEditPart sourceEditPart = (NodeEditPart) connectionEditPart.getSource();
            ReconnectRequest reconnectRequest = new ReconnectRequest(RequestConstants.REQ_RECONNECT_SOURCE);
            reconnectRequest.setConnectionEditPart(connectionEditPart);
            reconnectRequest.setTargetEditPart(sourceEditPart);
            reconnectRequest.setLocation(reqLocation);
            sourceEditPart.showTargetFeedback(reconnectRequest);
        }
        if (request.getIndex() >= this.originalState.getMPoints().size()) {
            // Update target anchor.
            ConnectionEditPart connectionEditPart = getHost();
            NodeEditPart targetEditPart = (NodeEditPart) connectionEditPart.getTarget();
            ReconnectRequest reconnectRequest = new ReconnectRequest(RequestConstants.REQ_RECONNECT_TARGET);
            reconnectRequest.setConnectionEditPart(connectionEditPart);
            reconnectRequest.setLocation(reqLocation);
            reconnectRequest.setTargetEditPart(targetEditPart);
            targetEditPart.showTargetFeedback(reconnectRequest);
        }
        
    }

    @objid ("34f9d105-e08f-41ab-a0a3-22a3d7bcebc2")
    private void eraseMoveSegmentFeedback(BendpointRequest request) {
        Connection connection = getConnection();
        List<Bendpoint> routingConstraint = (List<Bendpoint>) connection.getRoutingConstraint();
        if (request.getIndex() == 0) {
            ConnectionEditPart connectionEditPart = getHost();
            NodeEditPart sourceEditPart = (NodeEditPart) connectionEditPart.getSource();
            ReconnectRequest reconnectRequest = new ReconnectRequest(RequestConstants.REQ_RECONNECT_SOURCE);
            reconnectRequest.setConnectionEditPart(connectionEditPart);
            reconnectRequest.setTargetEditPart(sourceEditPart);
            sourceEditPart.eraseTargetFeedback(reconnectRequest);
        }
        if (request.getIndex() >= routingConstraint.size()) {
            ConnectionEditPart connectionEditPart = getHost();
            NodeEditPart targetEditPart = (NodeEditPart) connectionEditPart.getTarget();
            ReconnectRequest reconnectRequest = new ReconnectRequest(RequestConstants.REQ_RECONNECT_TARGET);
            reconnectRequest.setConnectionEditPart(connectionEditPart);
            reconnectRequest.setTargetEditPart(targetEditPart);
            targetEditPart.eraseTargetFeedback(reconnectRequest);
        }
        
    }

    @objid ("f8d781d0-c748-4eca-bb2f-3b807036e536")
    private static class ApplyConstraintCommand extends ChangeLinkRoutingConstraintCommand {
        @objid ("13989b16-e74d-429f-9298-03c174990c0c")
        public  ApplyConstraintCommand(ConnectionEditPart connectionEP) {
            super(connectionEP);
        }

        @objid ("b8aec2c9-eb72-4f3b-8a0d-9fdee64af3f9")
        @Override
        protected List<MPoint> getNewBendPoints() {
            List<MPoint> newConstraint = AutoOrthogonalRouter.getCleanedConstraint(this.connection, this.connectionState, true);
            return newConstraint;
        }

    }

    @objid ("c0908cc6-86a7-496a-bd86-41f8ee004b53")
    private static class MoveConnectionCommand extends Command {
        @objid ("e41e553b-0211-4301-b14e-0acbd30fe80e")
        private final WeakReference<ConnectionEditPart> epRef;

        @objid ("79392d4e-0ac6-41a8-8194-c89ef9e6f076")
        private final ILinkPathEditorFactory editor;

        @objid ("a9d11346-5cfb-47db-9702-112cddc7aaa1")
        private final IGmLink gmLink;

        @objid ("f3884d4d-06b4-4845-acf3-e9debe233b8c")
        private final Map<Object, EditPart> editPartRegistry;

        @objid ("f7dc4b08-efb1-44c6-97a6-b6276655d5f8")
        private final ChangeBoundsRequest origRequest;

        @objid ("1a850a40-e6ba-43c5-a0c6-87c0db74c28a")
        private final ConnectionState initialState;

        @objid ("ffab127a-ea79-41e7-be70-5b3ea3b8af5d")
        public  MoveConnectionCommand(ConnectionEditPart ep, ILinkPathEditorFactory editor, ConnectionState initialState, final ChangeBoundsRequest origRequest) {
            super();
            this.initialState = initialState;
            this.epRef = new WeakReference<>(ep);
            this.editor = editor;
            this.origRequest = origRequest;
            this.gmLink = (IGmLink) ep.getModel();
            this.editPartRegistry = ep.getViewer().getEditPartRegistry();
            
        }

        @objid ("a46853f8-83c8-482a-b595-1f48e28e95a9")
        @Override
        public String getLabel() {
            if (this.gmLink.getRelatedElement() != null) {
                return String.format("Layout '%s' connection from %s to %s", getConnectionEditPart(),
                        this.gmLink.getFromElement(),
                        this.gmLink.getToElement());
            } else {
                return String.format("Layout '%s' ghost connection %s", getConnectionEditPart(),
                        this.gmLink.getRepresentedRef());
            }
            
        }

        @objid ("f3611908-6e05-4767-9bb5-4a891c6ce54b")
        public ChangeBoundsRequest getRequest() {
            return this.origRequest;
        }

        @objid ("807c3893-bcad-432a-b12b-5cc15211553b")
        protected ConnectionEditPart getConnectionEditPart() {
            ConnectionEditPart ep = this.epRef.get();
            if (isValidConnectionEditPart(ep)) {
                return ep;
            }
            
            ep = (ConnectionEditPart) this.editPartRegistry.get(this.gmLink);
            if (isValidConnectionEditPart(ep)) {
                return ep;
            }
            return null;
        }

        @objid ("4cabacf7-f553-423e-b57d-a9be5246cde2")
        private static boolean isValidConnectionEditPart(ConnectionEditPart ep) {
            return ep != null
                    && ep.getParent() != null
                    && ep.getSource() != null
                    && ep.getTarget() != null;
            
        }

        @objid ("992a2d67-d37e-4e81-83b9-b10e1ef17891")
        @Override
        public void execute() {
            ConnectionEditPart ep = getConnectionEditPart();
            if (ep == null) {
                return;
            }
            
            Connection conn = (Connection) ep.getFigure();
            
            // Run figure validations to have new node figures bounds.
            ConnectionAnchor tmpAnchor = conn.getSourceAnchor();
            conn.setSourceAnchor(null); // hack to disable validation for now : it makes visible glitches
            conn.getUpdateManager().performValidation();
            conn.setSourceAnchor(tmpAnchor);
            
            // Call the editor to get the new connection state
            ILinkPathEditor editor2 = this.editor.from(ep, this.initialState);
            editor2.applyChangeBoundsRequest(this.origRequest, false);
            
            // apply state to model
            ConnectionState finalState = editor2.getState();
            Object sourceAnchorModel = ((IAnchorModelProvider) ep.getSource()).createAnchorModel(finalState.getSourceAnchor());
            Object targetAnchorModel = ((IAnchorModelProvider) ep.getTarget()).createAnchorModel(finalState.getTargetAnchor());
            
            IGmLinkObject model = (IGmLinkObject) ep.getModel();
            GmPath newPath = new GmPath(model.getPath());
            newPath.setPathData(finalState.getConstraint());
            newPath.setSourceAnchor(sourceAnchorModel);
            newPath.setTargetAnchor(targetAnchorModel);
            model.setLayoutData(newPath);
            
        }

    }

}
