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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.AncestorListener;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.ConnectionLocator;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Polygon;
import org.eclipse.draw2d.XYAnchor;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionDimension;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.editpolicies.FeedbackHelper;
import org.eclipse.gef.editpolicies.SelectionHandlesEditPolicy;
import org.eclipse.gef.handles.ConnectionHandle;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.metamodel.uml.behavior.interactionModel.ExecutionOccurenceSpecification;
import org.modelio.metamodel.uml.behavior.interactionModel.ExecutionSpecification;
import org.modelio.metamodel.uml.behavior.interactionModel.Message;
import org.modelio.metamodel.uml.behavior.interactionModel.MessageEnd;
import org.modelio.metamodel.uml.behavior.interactionModel.MessageSort;
import org.modelio.uml.sequencediagram.editor.elements.modelmanipulation.ManipulationHelper;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * This policy is in charge of handling the vertical translation of a message.
 * 
 * @author fpoyer
 */
@objid ("d965312a-55b6-11e2-877f-002564c97630")
public class MessageTranslationEditPolicy extends SelectionHandlesEditPolicy {
    @objid ("d965312d-55b6-11e2-877f-002564c97630")
    private ConnectionFocus focus;

    @objid ("6b97c28d-1316-4332-8639-e6db294bde60")
    private FeedbackHelper feedbackHelper;

    @objid ("050c68c3-b68f-4f70-9b54-f47ce993d26a")
    private XYAnchor dummySourceAnchor = new XYAnchor(new Point(10, 10));

    @objid ("ede6b9d8-9f52-4468-b568-be4a136ca80d")
    private XYAnchor dummyTargetAnchor = new XYAnchor(new Point(10, 10));

    @objid ("a1802c5c-63d3-44f9-82a6-62f3c7f78c9b")
    private ConnectionAnchor originalSourceAnchor;

    @objid ("d0f48e1d-6aae-4bfa-baa9-a63fb0bf56db")
    private ConnectionAnchor originalTargetAnchor;

    @objid ("78ac62fc-9bcb-49b9-b88b-3d5cd85fe210")
    private ManipulationHelper manipHelper;

    @objid ("d966b79d-55b6-11e2-877f-002564c97630")
    @Override
    public void eraseSourceFeedback(final Request request) {
        if (RequestConstants.REQ_RECONNECT_TARGET.equals(request.getType()) || RequestConstants.REQ_RECONNECT_SOURCE.equals(request.getType())) {
            eraseConnectionMoveFeedback((ReconnectRequest) request);
        } else if (RequestConstants.REQ_MOVE.equals(request.getType())) {
            eraseChangeBoundsFeedback((ChangeBoundsRequest) request);
        }
    }

    @objid ("d966b7a2-55b6-11e2-877f-002564c97630")
    @Override
    public Command getCommand(final Request request) {
        Object type = request.getType();
        
        if (RequestConstants.REQ_MOVE.equals(type)) {
            // Before returning a command, check that the movement can atually be applied.
            // Compute predicates, update variables and check predicates
            computePredicatesForHost();
            updateVariablesFromRequest((ChangeBoundsRequest) request);
            if (this.manipHelper.checkAllPredicates()) {
                // Ignore request to move along the X axis
                ((ChangeBoundsRequest) request).getMoveDelta().x = 0;
                GmMessage gmMessage = (GmMessage) getHost().getModel();
                MoveMessageCommand moveMessageCommand = new MoveMessageCommand();
                moveMessageCommand.setDiagram(gmMessage.getDiagram());
                moveMessageCommand.setMessage(gmMessage.getRelatedElement());
                moveMessageCommand.setSourceTimeDelta(((ChangeBoundsRequest) request).getMoveDelta().y);
                moveMessageCommand.setTargetTimeDelta(((ChangeBoundsRequest) request).getMoveDelta().y);
                return moveMessageCommand;
            } else {
                return UnexecutableCommand.INSTANCE;
            }
        }
        return null;
    }

    @objid ("d966b7a9-55b6-11e2-877f-002564c97630")
    @Override
    public void showSourceFeedback(final Request request) {
        if (RequestConstants.REQ_RECONNECT_SOURCE.equals(request.getType()) || RequestConstants.REQ_RECONNECT_TARGET.equals(request.getType())) {
            showConnectionMoveFeedback((ReconnectRequest) request);
        } else if (RequestConstants.REQ_MOVE.equals(request.getType())) {
            showChangeBoundsFeedback((ChangeBoundsRequest) request);
        }
    }

    /**
     * @see org.eclipse.gef.editpolicies.SelectionHandlesEditPolicy#createSelectionHandles()
     */
    @objid ("d966b7ae-55b6-11e2-877f-002564c97630")
    @Override
    protected List<ConnectionHandle> createSelectionHandles() {
        List<ConnectionHandle> list = new ArrayList<>();
        list.add(new MessageHandle((ConnectionEditPart) getHost(), ConnectionLocator.TARGET));
        list.add(new MessageHandle((ConnectionEditPart) getHost(), ConnectionLocator.SOURCE));
        list.add(new MessageHandle((ConnectionEditPart) getHost(), ConnectionLocator.MIDDLE));
        return list;
    }

    /**
     * Erases connection move feedback. This method is called when a ReconnectRequest is received.
     * 
     * @param request the reconnect request.
     */
    @objid ("d966b7b6-55b6-11e2-877f-002564c97630")
    protected void eraseConnectionMoveFeedback(final ReconnectRequest request) {
        if (this.originalSourceAnchor != null) {
            getConnection().setSourceAnchor(this.originalSourceAnchor);
        }
        if (this.originalTargetAnchor != null) {
            getConnection().setTargetAnchor(this.originalTargetAnchor);
        }
        this.originalSourceAnchor = null;
        this.originalTargetAnchor = null;
        this.feedbackHelper = null;
    }

    /**
     * Convenience method for obtaining the host's <code>Connection</code> figure.
     * 
     * @return the Connection figure
     */
    @objid ("d966b7bb-55b6-11e2-877f-002564c97630")
    protected Connection getConnection() {
        return (Connection) ((GraphicalEditPart) getHost()).getFigure();
    }

    /**
     * Lazily creates and returns the feedback helper for the given request. The helper will be configured as either moving the source or target end of the connection.
     * 
     * @param request the reconnect request
     * @return the feedback helper
     */
    @objid ("d966b7c0-55b6-11e2-877f-002564c97630")
    protected FeedbackHelper getFeedbackHelper(final ReconnectRequest request) {
        if (this.feedbackHelper == null) {
            this.feedbackHelper = new FeedbackHelper();
            this.feedbackHelper.setConnection(getConnection());
            this.feedbackHelper.setMovingStartAnchor(request.isMovingStartAnchor());
        }
        return this.feedbackHelper;
    }

    /**
     * Hides the focus indicator. The focus indicator is a dotted outline around the connection.
     * @see #showFocus()
     * @see org.eclipse.gef.editpolicies.SelectionEditPolicy#hideFocus()
     */
    @objid ("d966b7c7-55b6-11e2-877f-002564c97630")
    @Override
    protected void hideFocus() {
        if (this.focus != null) {
            removeFeedback(this.focus);
            this.focus = null;
        }
    }

    /**
     * Shows or updates connection move feedback. Called whenever a show feedback request is received for reconnection.
     * 
     * @param request the reconnect request
     */
    @objid ("d966b7cb-55b6-11e2-877f-002564c97630")
    protected void showConnectionMoveFeedback(final ReconnectRequest request) {
        NodeEditPart node = null;
        if (request.getTarget() instanceof NodeEditPart) {
            node = (NodeEditPart) request.getTarget();
        }
        if (this.originalSourceAnchor == null) {
            if (request.isMovingStartAnchor()) {
                this.originalSourceAnchor = getConnection().getSourceAnchor();
            } else {
                this.originalSourceAnchor = getConnection().getTargetAnchor();
            }
        }
        ConnectionAnchor anchor = null;
        if (node != null) {
            if (request.isMovingStartAnchor()) {
                anchor = node.getSourceConnectionAnchor(request);
            } else {
                anchor = node.getTargetConnectionAnchor(request);
            }
        }
        FeedbackHelper helper = getFeedbackHelper(request);
        helper.update(anchor, request.getLocation());
    }

    @objid ("d966b7d0-55b6-11e2-877f-002564c97630")
    @Override
    protected void showFocus() {
        if (this.focus == null) {
            this.focus = new ConnectionFocus();
            addFeedback(this.focus);
        }
    }

    /**
     * Shows or updates feedback for a change bounds request.
     * 
     * @param originalRequest the request
     */
    @objid ("d966b7d3-55b6-11e2-877f-002564c97630")
    protected void showChangeBoundsFeedback(final ChangeBoundsRequest originalRequest) {
        // Copy the original request, nullifying the x move delta.
        ChangeBoundsRequest request = new ChangeBoundsRequest(originalRequest.getType());
        request.setCenteredResize(originalRequest.isCenteredResize());
        request.setConstrainedMove(originalRequest.isConstrainedMove());
        request.setConstrainedResize(originalRequest.isConstrainedResize());
        request.setEditParts(originalRequest.getEditParts());
        request.setExtendedData(originalRequest.getExtendedData());
        request.setLocation(originalRequest.getLocation().getCopy());
        request.setMoveDelta(new Point(0, originalRequest.getMoveDelta().y));
        request.setResizeDirection(originalRequest.getResizeDirection());
        request.setSizeDelta(originalRequest.getSizeDelta().getCopy());
        // TODO: transmit the request to (if they exist): start and end blue square/gate and started execution(s).
        GmMessage messageModel = (GmMessage) getHost().getModel();
        Message message = messageModel.getRelatedElement();
        MessageEnd sourceEnd = message.getSendEvent();
        Collection<GmModel> sourceEndModels = messageModel.getDiagram().getAllGMRelatedTo(new MRef(sourceEnd));
        for (GmModel sourceEndModel : sourceEndModels) {
            if (((GmNodeModel) sourceEndModel).isVisible()) {
                getEditPart(sourceEndModel).showSourceFeedback(request);
            }
        }
        MessageEnd targetEnd = message.getReceiveEvent();
        Collection<GmModel> targetEndModels = messageModel.getDiagram().getAllGMRelatedTo(new MRef(targetEnd));
        for (GmModel targetEndModel : targetEndModels) {
            if (((GmNodeModel) targetEndModel).isVisible()) {
                getEditPart(targetEndModel).showSourceFeedback(request);
            }
        }
        if (this.originalSourceAnchor == null) {
            this.originalSourceAnchor = getConnection().getSourceAnchor();
        }
        if (this.originalTargetAnchor == null) {
            this.originalTargetAnchor = getConnection().getTargetAnchor();
        }
        Point p = this.originalSourceAnchor.getReferencePoint().getCopy();
        p.translate(0, request.getMoveDelta().y);
        this.dummySourceAnchor.setLocation(p);
        p = this.originalTargetAnchor.getReferencePoint().getCopy();
        p.translate(0, request.getMoveDelta().y);
        this.dummyTargetAnchor.setLocation(p);
        getConnection().setSourceAnchor(this.dummySourceAnchor);
        getConnection().setTargetAnchor(this.dummyTargetAnchor);
        
        computePredicatesForHost();
        updateVariablesFromRequest(request);
        this.manipHelper.showFeedBack(getFeedbackLayer());
    }

    @objid ("d9683e39-55b6-11e2-877f-002564c97630")
    private void eraseChangeBoundsFeedback(final ChangeBoundsRequest request) {
        // TODO: transmit the request to (if they exist): start and end blue square/gate and started execution(s).
        GmMessage messageModel = (GmMessage) getHost().getModel();
        Message message = messageModel.getRelatedElement();
        MessageEnd sourceEnd = message.getSendEvent();
        Collection<GmModel> sourceEndModels = messageModel.getDiagram().getAllGMRelatedTo(new MRef(sourceEnd));
        for (GmModel sourceEndModel : sourceEndModels) {
            if (((GmNodeModel) sourceEndModel).isVisible()) {
                getEditPart(sourceEndModel).eraseSourceFeedback(request);
            }
        }
        MessageEnd targetEnd = message.getReceiveEvent();
        Collection<GmModel> targetEndModels = messageModel.getDiagram().getAllGMRelatedTo(new MRef(targetEnd));
        for (GmModel targetEndModel : targetEndModels) {
            if (((GmNodeModel) targetEndModel).isVisible()) {
                getEditPart(targetEndModel).eraseSourceFeedback(request);
            }
        }
        
        if (this.originalSourceAnchor != null) {
            getConnection().setSourceAnchor(this.originalSourceAnchor);
        }
        if (this.originalTargetAnchor != null) {
            getConnection().setTargetAnchor(this.originalTargetAnchor);
        }
        this.originalSourceAnchor = null;
        this.originalTargetAnchor = null;
        
        IFigure fbLayer = getFeedbackLayer();
        this.manipHelper.eraseFeedback(fbLayer);
    }

    @objid ("d9683e3d-55b6-11e2-877f-002564c97630")
    @Override
    public boolean understandsRequest(final Request request) {
        if (RequestConstants.REQ_MOVE.equals(request.getType())) {
            return true;
        } else {
            return false;
        }
    }

    @objid ("d9683e43-55b6-11e2-877f-002564c97630")
    private void computePredicatesForHost() {
        // Move and/or resizing an ExecutionSpecification, is really like move the ExecutionOccurrenceSpecification at each end.
        Message message = ((GmMessage) getHost().getModel()).getRelatedElement();
        this.manipHelper.computePredicatesForHost(message);
    }

    @objid ("d9683e45-55b6-11e2-877f-002564c97630")
    private void updateVariablesFromRequest(final ChangeBoundsRequest request) {
        if (request.getEditParts() == null) {
            return;
        }
        for (Object obj : request.getEditParts()) {
            if (obj instanceof GraphicalEditPart) {
                GraphicalEditPart editPart = (GraphicalEditPart) obj;
                IFigure figure = editPart.getFigure();
                GmModel model = (GmModel) editPart.getModel();
        
                Dimension moveDelta = new PrecisionDimension(request.getMoveDelta().x, request.getMoveDelta().y);
                figure.translateToRelative(moveDelta);
        
                MObject relatedElement = model.getRelatedElement();
                if (relatedElement instanceof MessageEnd) {
                    updateVariablesForMessageEnd(
                            (MessageEnd) relatedElement,
                            moveDelta.height);
                } else if (relatedElement instanceof ExecutionSpecification) {
                    Dimension sizeDelta = new PrecisionDimension(request.getSizeDelta());
                    figure.translateToRelative(sizeDelta);
        
                    updateVariablesForExecutionSpecification(
                            (ExecutionSpecification) relatedElement,
                            moveDelta.height,
                            sizeDelta.height);
                } else if (relatedElement instanceof Message) {
                    updateVariablesForMessage(moveDelta.height, (Message) relatedElement);
                }
        
            }
        }
    }

    @objid ("d9683e5a-55b6-11e2-877f-002564c97630")
    private void updateVariablesForMessage(final int shift, final Message message) {
        MessageEnd msgSrcEvent = message.getSendEvent();
        MessageEnd msgTargetEvent = message.getReceiveEvent();
        
        // Shift Send and Receive event
        updateVariablesForMessageEnd(msgSrcEvent, shift);
        updateVariablesForMessageEnd(msgTargetEvent, shift);
        
        // If the moved message starts some execution specification, they will be moved too.
        if (msgSrcEvent instanceof ExecutionOccurenceSpecification) {
            ExecutionOccurenceSpecification eosSendEvent = (ExecutionOccurenceSpecification) msgSrcEvent;
            if (eosSendEvent.getStarted() != null) {
             // If the moved message starts some execution
                updateVariablesForExecutionSpecification(eosSendEvent.getStarted(),
                        shift,
                        0);
            }
        }
        if (msgTargetEvent instanceof ExecutionOccurenceSpecification) {
            ExecutionOccurenceSpecification eosRecEvent = (ExecutionOccurenceSpecification) msgTargetEvent;
            if (eosRecEvent.getStarted() != null) {
                // the moved message end starts an execution rectangle
                updateVariablesForExecutionSpecification(eosRecEvent.getStarted(),
                        shift,
                        0);
                MessageEnd otherEnd = eosRecEvent.getStarted().getFinish();
                if (otherEnd.getSentMessage() != null &&
                        otherEnd.getSentMessage().getSortOfMessage() == MessageSort.RETURNMESSAGE) {
                    updateVariablesForMessage(shift, otherEnd.getSentMessage());
                }
            }
        }
    }

    /**
     * Update variables for execution rectangle.
     * 
     * @param executionSpecification the execution rectangle
     * @param shift the vertical move delta
     * @param sizeDelta the vertical size delta
     */
    @objid ("d9683e62-55b6-11e2-877f-002564c97630")
    private void updateVariablesForExecutionSpecification(final ExecutionSpecification executionSpecification, final int shift, final int sizeDelta) {
        // Start with the Execution itself.
        this.manipHelper.updateVariable(executionSpecification, executionSpecification.getLineNumber() + shift);
        
        // Now the Execution start.
        updateVariablesForMessageEnd(executionSpecification.getStart(), shift);
        // And finally the Execution end.
        updateVariablesForMessageEnd(executionSpecification.getFinish(), shift + sizeDelta);
    }

    @objid ("d9683e6c-55b6-11e2-877f-002564c97630")
    private void updateVariablesForMessageEnd(final MessageEnd messageEnd, final int shift) {
        this.manipHelper.updateVariable(messageEnd, messageEnd.getLineNumber() + shift);
    }

    @objid ("dac6be03-2c18-4622-a8ab-3dc261cd930a")
    @Override
    public void activate() {
        super.activate();
        
        this.manipHelper = new ManipulationHelper((GraphicalEditPart) getHost());
    }

    @objid ("6da2e559-729d-44c7-86ef-ac5c901f32fd")
    protected GraphicalEditPart getEditPart(MObject obj) {
        GmMessage messageModel = (GmMessage) getHost().getModel();
        Collection<GmModel> sourceEndModels = messageModel.getDiagram().getAllGMRelatedTo(new MRef(obj));
        for (GmModel sourceEndModel : sourceEndModels) {
            if (((GmNodeModel) sourceEndModel).isVisible()) {
                return getEditPart(sourceEndModel);
            }
        }
        return null;
    }

    @objid ("9e9f8d33-4c03-449a-9ed7-ef22bb6d6c4b")
    protected GraphicalEditPart getEditPart(GmModel model) {
        return (GraphicalEditPart) getHost().getViewer().getEditPartRegistry().get(model);
    }

    @objid ("d9683e74-55b6-11e2-877f-002564c97630")
    class ConnectionFocus extends Polygon implements PropertyChangeListener {
        @objid ("13b57fe5-b6ce-474c-ae28-ca6403a99118")
        public AncestorListener ancestorListener = new AncestorListener.Stub() {
            @Override
            public void ancestorMoved(IFigure ancestor) {
                revalidate();
            }
        };

        @objid ("d9683e76-55b6-11e2-877f-002564c97630")
        @Override
        public void addNotify() {
            super.addNotify();
            getConnection().addPropertyChangeListener(Connection.PROPERTY_POINTS, this);
            getConnection().addAncestorListener(this.ancestorListener);
        }

        @objid ("d9683e79-55b6-11e2-877f-002564c97630")
        @Override
        public void propertyChange(final PropertyChangeEvent evt) {
            revalidate();
        }

        @objid ("d969c4dc-55b6-11e2-877f-002564c97630")
        @Override
        public void removeNotify() {
            getConnection().removePropertyChangeListener(Connection.PROPERTY_POINTS, this);
            getConnection().removeAncestorListener(this.ancestorListener);
            super.removeNotify();
        }

        @objid ("d969c4df-55b6-11e2-877f-002564c97630")
        @Override
        public void validate() {
            if (isValid()) {
                return;
            }
            PointList points = getConnection().getPoints().getCopy();
            getConnection().translateToAbsolute(points);
            // points = StrokePointList.strokeList(points, 5);
            translateToRelative(points);
            setPoints(points);
        }

        @objid ("d969c4e2-55b6-11e2-877f-002564c97630")
        ConnectionFocus() {
            setFill(true);
            setForegroundColor(ColorConstants.green);
            setBackgroundColor(ColorConstants.red);
            setXOR(true);
            setOutline(true);
        }

        @objid ("d969c4e4-55b6-11e2-877f-002564c97630")
        @Override
        protected void outlineShape(final Graphics g) {
            g.setLineDash(new int[] { 1, 1 });
            super.outlineShape(g);
        }

    }

}
