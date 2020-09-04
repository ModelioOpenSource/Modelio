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

package org.modelio.diagram.elements.core.tools.multipoint;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.ConnectionLayer;
import org.eclipse.draw2d.ConnectionRouter;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editpolicies.FeedbackHelper;
import org.eclipse.gef.editpolicies.GraphicalEditPolicy;
import org.modelio.diagram.elements.core.figures.FigureUtilities2;
import org.modelio.diagram.elements.core.link.ModelioLinkCreationContext;
import org.modelio.diagram.elements.drawings.core.GmDrawing;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Abstract base class for all policies dealing with MultiPoint creation interaction.
 * 
 * @author fpoyer
 */
@objid ("80eb663f-1dec-11e2-8cad-001ec947c8cc")
public abstract class MultiPointCreationEditPolicy extends GraphicalEditPolicy {
    /**
     * <p>
     * This boolean is used to determine the behavior to adopt when receiving a request about a metaclass that is not handled
     * because the CreationExpert does not allow it.
     * </p>
     * <ul>
     * <li>When <code>true</code>, the policy will still return the host in the "getTargetEditPart" method and it will return a non
     * executable command in the getCommand method. This will in effect prevent the tool from proposing the request to the host's
     * parent edit part, meaning the host is "opaque".</li>
     * <li>When <code>false</code> on the other hand, the getTargetEditPart method will return <code>null</code>, giving a chance to
     * the tool to propose the request to the host's parent edit part, meaning the host is "transparent".</li>
     * </ul>
     */
    @objid ("80eb6643-1dec-11e2-8cad-001ec947c8cc")
    private boolean isOpaque = true;

    @objid ("8181447c-1e83-11e2-8cad-001ec947c8cc")
    private static final String HIGHLIGHTKEY = "HIGHLIGHTKEY";

    /**
     * the current FeedbackHelper
     */
    @objid ("6381ce0e-1e83-11e2-8cad-001ec947c8cc")
    protected List<FeedbackHelper> feedbackHelpers = new ArrayList<>();

    /**
     * The connection feedbacks displayed during creates
     */
    @objid ("63843068-1e83-11e2-8cad-001ec947c8cc")
    protected List<Connection> connectionFeedbacks = new ArrayList<>();

    /**
     * No Parameter c'tor: creates an opaque instance of this policy.
     * @see #MultiPointCreationEditPolicy(boolean)
     */
    @objid ("80eb6653-1dec-11e2-8cad-001ec947c8cc")
    public MultiPointCreationEditPolicy() {
        this(true);
    }

    /**
     * <p>
     * C'tor.
     * </p>
     * <p>
     * <string><em>Note about isOpaque effect:</em></strong>
     * <p>
     * This boolean is used to determine the behavior to adopt when receiving a request about a metaclass that is not handled
     * because the CreationExpert does not allow it.
     * </p>
     * <ul>
     * <li>When <code>true</code>, the policy will still return the host in the "getTargetEditPart" method and it will return a non
     * executable command in the getCommand method. This will in effect prevent the tool from proposing the request to the host's
     * parent edit part, meaning the host is "opaque".</li>
     * <li>When <code>false</code> on the other hand, the getTargetEditPart method will return <code>null</code>, giving a chance to
     * the tool to propose the request to the host's parent edit part, meaning the host is "transparent".</li>
     * </ul>
     * </p>
     * @param isOpaque determines the behavior of this policy on request where the creation expert doesn't allow. See Note.
     */
    @objid ("80eb6656-1dec-11e2-8cad-001ec947c8cc")
    public MultiPointCreationEditPolicy(final boolean isOpaque) {
        super();
        this.isOpaque = isOpaque;
    }

    @objid ("80eb665b-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void deactivate() {
        if (!this.connectionFeedbacks.isEmpty()) {
            for (final Connection connectionFeedback : this.connectionFeedbacks) {
                removeFeedback(connectionFeedback);
            }
            this.feedbackHelpers.clear();
            this.connectionFeedbacks.clear();
        }
        super.deactivate();
    }

    /**
     * Calls {@link #eraseCreationFeedback(CreateMultiPointRequest)} when appropriate.
     */
    @objid ("80eb665e-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void eraseSourceFeedback(final Request request) {
        if (CreateMultiPointRequest.REQ_MULTIPOINT_ADDITIONAL.equals(request.getType())
                || CreateMultiPointRequest.REQ_MULTIPOINT_LAST.equals(request.getType())) {
            eraseCreationFeedback((CreateMultiPointRequest) request);
        }
    }

    /**
     * Calls {@link #eraseTargetConnectionFeedback(CreateMultiPointRequest)} when appropriate.
     */
    @objid ("80eb6666-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void eraseTargetFeedback(final Request request) {
        if (CreateMultiPointRequest.REQ_MULTIPOINT_FIRST.equals(request.getType())
                || CreateMultiPointRequest.REQ_MULTIPOINT_ADDITIONAL.equals(request.getType())
                || CreateMultiPointRequest.REQ_MULTIPOINT_LAST.equals(request.getType())) {
            eraseTargetConnectionFeedback((CreateMultiPointRequest) request);
        }
    }

    /**
     * Factors the request into one of three abstract methods. Subclasses must implement these methods.
     */
    @objid ("80eb666e-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Command getCommand(final Request request) {
        if (CreateMultiPointRequest.REQ_MULTIPOINT_FIRST.equals(request.getType())) {
            final CreateMultiPointRequest multiPointRequest = (CreateMultiPointRequest) request;
            return getMultiPointFirstCommand(multiPointRequest);
        } else if (CreateMultiPointRequest.REQ_MULTIPOINT_ADDITIONAL.equals(request.getType())) {
            final CreateMultiPointRequest multiPointRequest = (CreateMultiPointRequest) request;
            return getMultiPointAdditionalCommand(multiPointRequest);
        } else if (CreateMultiPointRequest.REQ_MULTIPOINT_LAST.equals(request.getType())) {
            final CreateMultiPointRequest multiPointRequest = (CreateMultiPointRequest) request;
            return buildMultiPointFinalCommand(multiPointRequest);
        } else {
            return null;
        }
    }

    /**
     * Returns the <i>host</i> for the appropriate <code>Requests</code>. Returns <code>null</code> otherwise.
     */
    @objid ("80eb6679-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public EditPart getTargetEditPart(final Request request) {
        if (CreateMultiPointRequest.REQ_MULTIPOINT_FIRST.equals(request.getType())) {
            return this.isOpaque ? getHost() : getTargetEditPartFirst((CreateMultiPointRequest) request);
        } else if (CreateMultiPointRequest.REQ_MULTIPOINT_ADDITIONAL.equals(request.getType())) {
            return this.isOpaque ? getHost() : getTargetEditPartAdditional((CreateMultiPointRequest) request);
        } else if (CreateMultiPointRequest.REQ_MULTIPOINT_LAST.equals(request.getType())) {
            return this.isOpaque ? getHost() : getTargetEditPartLast((CreateMultiPointRequest) request);
        }
        return null;
    }

    /**
     * calls {@link #showCreationFeedback(CreateMultiPointRequest)} when appropriate.
     */
    @objid ("80edc88f-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void showSourceFeedback(final Request request) {
        if (CreateMultiPointRequest.REQ_MULTIPOINT_LAST.equals(request.getType())) {
            showCreationFeedback((CreateMultiPointRequest) request);
        }
    }

    /**
     * Calls {@link #showTargetConnectionFeedback(CreateMultiPointRequest)} when appropriate.
     */
    @objid ("80edc897-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void showTargetFeedback(final Request request) {
        if (CreateMultiPointRequest.REQ_MULTIPOINT_FIRST.equals(request.getType())
                || CreateMultiPointRequest.REQ_MULTIPOINT_ADDITIONAL.equals(request.getType())
                || CreateMultiPointRequest.REQ_MULTIPOINT_LAST.equals(request.getType())) {
            showTargetConnectionFeedback((CreateMultiPointRequest) request);
        }
    }

    /**
     * Returns a connection to be used as feeback during creates.
     * @param req the operation being performed
     * @param source the object for which the feedback is created.
     * @return a connection to use as feedback
     */
    @objid ("80edc8a8-1dec-11e2-8cad-001ec947c8cc")
    protected Connection createDummyConnection(final CreateMultiPointRequest req, final EditPart source) {
        return new PolylineConnection();
    }

    /**
     * Erases connection feedback if necessary. Frees unused fields.
     * @param request the CreateMultiPointRequest
     */
    @objid ("80edc8b5-1dec-11e2-8cad-001ec947c8cc")
    protected void eraseCreationFeedback(final CreateMultiPointRequest request) {
        if (!this.connectionFeedbacks.isEmpty()) {
            for (final Connection connectionFeedback : this.connectionFeedbacks) {
                removeFeedback(connectionFeedback);
            }
            this.feedbackHelpers.clear();
            this.connectionFeedbacks.clear();
        }
    }

    /**
     * Override to erase target feedback. Does nothing by default.
     * @param request the CreateMultiPointRequest
     */
    @objid ("80edc8ba-1dec-11e2-8cad-001ec947c8cc")
    protected void eraseTargetConnectionFeedback(final CreateMultiPointRequest request) {
        // Additional feedback: outline the Node.
        final IFigure highlight = (IFigure) request.getExtendedData().get(HIGHLIGHTKEY);
        if (highlight != null) {
            final IFigure feedbackLayer = getFeedbackLayer();
            if (highlight.getParent() == feedbackLayer) {
                feedbackLayer.remove(highlight);
                if (feedbackLayer.getLayoutManager() != null) {
                    feedbackLayer.getLayoutManager().remove(highlight);
                }
                ((Request) request).getExtendedData().remove(HIGHLIGHTKEY);
            }
        }
    }

    /**
     * Utility method to get the metaclass to create.
     * @param request the creation request
     * @return The requested metamodel metaclass
     */
    @objid ("80edc8bf-1dec-11e2-8cad-001ec947c8cc")
    protected final Class<? extends MObject> getCreationMetaclass(final CreateMultiPointRequest request) {
        final ModelioLinkCreationContext ctx = ModelioLinkCreationContext.lookRequest(request);
        if (ctx != null) {
            final Class<? extends MObject> metaclass = ctx.getMetaclass().getJavaInterface();
            return metaclass;
        } else {
            return null;
        }
    }

    /**
     * Returns the ConnectionRouter for the creation feedback's connection.
     * @param request the create request
     * @param source the object for which the feedback is created.
     * @return a connection router
     */
    @objid ("80edc8c8-1dec-11e2-8cad-001ec947c8cc")
    protected ConnectionRouter getDummyConnectionRouter(final CreateMultiPointRequest request, final EditPart source) {
        return ((ConnectionLayer) getLayer(LayerConstants.CONNECTION_LAYER)).getConnectionRouter();
    }

    /**
     * Returns the FeedbackHelpers that are ready to use. The feedback helper must be configured with the connection that will be
     * used to display feedback, and that connection must be added to the appropriate layer in the diagram.
     * @param request the CreateMultiPointRequest
     * @return a list of FeedbackHelpers for all previous nodes
     */
    @objid ("80edc8d5-1dec-11e2-8cad-001ec947c8cc")
    protected List<FeedbackHelper> getFeedbackHelper(final CreateMultiPointRequest request) {
        if (this.feedbackHelpers.isEmpty()) {
            // for (EditPart sourceNode : request.getAcceptedEditParts()) {
            final EditPart sourceNode = getHost();
            final FeedbackHelper feedbackHelper = new FeedbackHelper();
            final Connection connectionFeedback = createDummyConnection(request, sourceNode);
            connectionFeedback.setConnectionRouter(getDummyConnectionRouter(request, sourceNode));
            connectionFeedback.setSourceAnchor(getSourceConnectionAnchor(request, sourceNode));
            feedbackHelper.setConnection(connectionFeedback);
            addFeedback(connectionFeedback);
            feedbackHelper.update(null, request.getLocation());
            this.connectionFeedbacks.add(connectionFeedback);
            this.feedbackHelpers.add(feedbackHelper);
            // }
        }
        return this.feedbackHelpers;
    }

    /**
     * Returns the Command that represents the step of creation for this additional actor. This Command will be passed to the target
     * node EditPart. The target node may do anything necessary to create a Command that represents the entire creation.
     * @see #getMultiPointFirstCommand(CreateMultiPointRequest)
     * @see #getMultiPointFinalCommand(CreateMultiPointRequest)
     * @param request the CreateMultiPointRequest
     * @return a Command representing the additional step of creation.
     */
    @objid ("80edc8e0-1dec-11e2-8cad-001ec947c8cc")
    protected abstract Command getMultiPointAdditionalCommand(final CreateMultiPointRequest request);

    /**
     * Returns the Command for the complete creation for this MultiPoint interaction.
     * @see #getMultiPointFirstCommand(CreateMultiPointRequest)
     * @see #getMultiPointAdditionalCommand(CreateMultiPointRequest)
     * @param request the CreateMultiPointRequest
     * @return a Command for the complete creation.
     */
    @objid ("80f02ae8-1dec-11e2-8cad-001ec947c8cc")
    protected abstract Command getMultiPointFinalCommand(final CreateMultiPointRequest request);

    /**
     * Returns the Command that represents the first step of creation. This Command will be passed to the target node EditPart. The
     * target node may do anything necessary to create a Command that represents the entire creation.
     * @see #getMultiPointAdditionalCommand(CreateMultiPointRequest)
     * @see #getMultiPointFinalCommand(CreateMultiPointRequest)
     * @param request the CreateMultiPointRequest
     * @return a Command representing the first step of creation.
     */
    @objid ("80f02aef-1dec-11e2-8cad-001ec947c8cc")
    protected abstract Command getMultiPointFirstCommand(final CreateMultiPointRequest request);

    /**
     * Called during the display of creation feedback to snap the feedback to the nearest source ConnectionAnchor.
     * @param request CreateMultiPointRequest
     * @param source the object for which the feedback is created.
     * @return <code>null</code> or the nearest source ConnectionAnchor
     */
    @objid ("80f02af6-1dec-11e2-8cad-001ec947c8cc")
    protected ConnectionAnchor getSourceConnectionAnchor(final CreateMultiPointRequest request, final EditPart source) {
        return source instanceof NodeEditPart ? ((NodeEditPart) source).getSourceConnectionAnchor(request) : null;
    }

    /**
     * Called during the display of creation feedback to snap the feedback to the nearest target ConnectionAnchor.
     * @param request CreateConnectionRequest
     * @return <code>null</code> or the nearest target ConnectionAnchor
     */
    @objid ("80f02b03-1dec-11e2-8cad-001ec947c8cc")
    protected ConnectionAnchor getTargetConnectionAnchor(final CreateMultiPointRequest request) {
        final EditPart target = request.getTargetEditPart();
        return target instanceof NodeEditPart ? ((NodeEditPart) target).getTargetConnectionAnchor(request) : null;
    }

    /**
     * <p>
     * Returns the target edit part for a {@link CreateMultiPointRequest} of type
     * {@link CreateMultiPointRequest#REQ_MULTIPOINT_ADDITIONAL}.
     * </p>
     * <p>
     * Default implementation returns host. Subclasses may override to adapt behaviour.
     * </p>
     * @param request the CreateMultiPointRequest
     * @return the target edit part for this request, or <code>null</code>.
     */
    @objid ("80f02b0c-1dec-11e2-8cad-001ec947c8cc")
    protected EditPart getTargetEditPartAdditional(final CreateMultiPointRequest request) {
        return getHost();
    }

    /**
     * <p>
     * Returns the target edit part for a {@link CreateMultiPointRequest} of type
     * {@link CreateMultiPointRequest#REQ_MULTIPOINT_FIRST}.
     * </p>
     * <p>
     * Default implementation returns host. Subclasses may override to adapt behaviour.
     * </p>
     * @param request the CreateMultiPointRequest
     * @return the target edit part for this request, or <code>null</code>.
     */
    @objid ("80f02b15-1dec-11e2-8cad-001ec947c8cc")
    protected EditPart getTargetEditPartFirst(final CreateMultiPointRequest request) {
        return getHost();
    }

    /**
     * <p>
     * Returns the target edit part for a {@link CreateMultiPointRequest} of type
     * {@link CreateMultiPointRequest#REQ_MULTIPOINT_LAST}.
     * </p>
     * <p>
     * Default implementation returns host. Subclasses may override to adapt behaviour.
     * </p>
     * @param request the CreateMultiPointRequest
     * @return the target edit part for this request, or <code>null</code>.
     */
    @objid ("80f02b1e-1dec-11e2-8cad-001ec947c8cc")
    protected EditPart getTargetEditPartLast(final CreateMultiPointRequest request) {
        return getHost();
    }

    /**
     * Utility method to test the metaclass of the creation request.
     * @param request the creation request
     * @param c a metaclass to test.
     * @return <code>true</code> if the request asks to create an element of type <code>c</code>, else <code>false</code>.
     */
    @objid ("80f02b27-1dec-11e2-8cad-001ec947c8cc")
    protected final boolean isCreationOf(final CreateMultiPointRequest request, final Class<?> c) {
        final Class<?> objType = getCreationType(request);
        return (objType!= null && c.isAssignableFrom(objType));
    }

    /**
     * Shows feedback during a creation.
     * @param request CreateConnectionRequest
     */
    @objid ("80f02b32-1dec-11e2-8cad-001ec947c8cc")
    protected void showCreationFeedback(final CreateMultiPointRequest request) {
        final Point p = new Point(request.getLocation());
        for (final FeedbackHelper helper : getFeedbackHelper(request)) {
            helper.update(getTargetConnectionAnchor(request), p);
        }
    }

    /**
     * Override to show target connection feedback. Does nothing by default.
     * @param request the CreateMultiPointRequest
     */
    @objid ("80f02b37-1dec-11e2-8cad-001ec947c8cc")
    protected void showTargetConnectionFeedback(final CreateMultiPointRequest request) {
        // Additional feedback: highlight the node.
        
        // compute highlight type
        final Command c = getHost().getCommand(request);
        FigureUtilities2.HighlightType hightlightType = FigureUtilities2.HighlightType.INFO;
        if (c == null) {
            hightlightType = FigureUtilities2.HighlightType.ERROR;
        } else if (c.canExecute()) {
            hightlightType = FigureUtilities2.HighlightType.SUCCESS;
        } else {
            hightlightType = FigureUtilities2.HighlightType.WARNING;
        }
        
        // create a highlight figure if it does not exist
        IFigure highlight = (IFigure) request.getExtendedData().get(HIGHLIGHTKEY);
        if (highlight == null) {
            // create a hightlight figure
            highlight = FigureUtilities2.createHighlightFigure(getFeedbackLayer(), getHostFigure(), hightlightType);
            // add the highlight figure to the feedback layer
            getFeedbackLayer().add(highlight);
            // register this additional feedback into the
            request.getExtendedData().put(HIGHLIGHTKEY, highlight);
        }
        // configure the highlight figure
        FigureUtilities2.updateHighlightType(highlight, hightlightType);
    }

    /**
     * Builds a compound command of all commands given along the interaction by the first and additional edit parts, and add the
     * command issued by the final edit part (== the host of this policy) at the end.
     * @param multiPointRequest @return
     */
    @objid ("80f28d41-1dec-11e2-8cad-001ec947c8cc")
    private Command buildMultiPointFinalCommand(final CreateMultiPointRequest multiPointRequest) {
        final CompoundCommand compound = new CompoundCommand();
        for (final Command command : multiPointRequest.getStartCommands()) {
            if (command != null && command.canExecute()) {
                compound.add(command);
            }
        }
        compound.add(getMultiPointFinalCommand(multiPointRequest));
        return compound.unwrap();
    }

    /**
     * Utility method to get the type of the object to create.
     * <p>
     * Returns a metamodel class or the GmDrawing class.
     * @param request the creation request
     * @return The requested metamodel class or the GmDrawing class.
     */
    @objid ("ed891901-8d4c-4c76-acd9-934186e0b858")
    protected final Class<?> getCreationType(final CreateMultiPointRequest request) {
        final Object newObjectType = request.getNewObjectType();
        if (newObjectType instanceof Class<?> && GmDrawing.class.isAssignableFrom((Class<?>) newObjectType) ) {
            return (Class<?>) newObjectType;
        }
        return getCreationMetaclass(request);
    }

}
