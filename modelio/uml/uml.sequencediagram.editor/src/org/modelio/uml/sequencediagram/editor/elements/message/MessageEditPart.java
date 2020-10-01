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
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.ConnectionRouter;
import org.eclipse.draw2d.Cursors;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Locator;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy;
import org.eclipse.gef.requests.LocationRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeRequestConstants;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeStartCreationEditPolicy;
import org.modelio.diagram.elements.common.portcontainer.SatelliteDragTrackerProvider;
import org.modelio.diagram.elements.core.figures.IPenOptionsSupport;
import org.modelio.diagram.elements.core.figures.LinkFigure;
import org.modelio.diagram.elements.core.figures.RoundedLinkFigure;
import org.modelio.diagram.elements.core.figures.anchors.LinkAnchor;
import org.modelio.diagram.elements.core.figures.anchors.SatelliteAnchor;
import org.modelio.diagram.elements.core.link.ConnectionRouterRegistry;
import org.modelio.diagram.elements.core.link.DefaultCreateLinkEditPolicy;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.link.GmLinkLayoutEditPolicy;
import org.modelio.diagram.elements.core.link.anchors.GmSourceSatelliteAnchor;
import org.modelio.diagram.elements.core.link.extensions.GmFractionalConnectionLocator;
import org.modelio.diagram.elements.core.link.extensions.IGmLocator;
import org.modelio.diagram.elements.core.link.extensions.LocatorFactory;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.policies.DefaultDeleteLinkEditPolicy;
import org.modelio.diagram.elements.core.policies.DelegatingDirectEditionEditPolicy;
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;
import org.modelio.diagram.elements.core.tools.multipoint.CreateMultiPointRequest;
import org.modelio.diagram.elements.umlcommon.constraint.ConstraintLinkEditPolicy;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.IStyleProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId;
import org.modelio.diagram.styles.core.StyleKey.LinePattern;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.behavior.interactionModel.MessageEnd;
import org.modelio.metamodel.uml.behavior.interactionModel.MessageSort;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * EditPart (ie Controller in the GEF model) for Message in sequence diagram. Note: for lack of a better place, this editpart installs the move policy on itself (no good "parent" to do it).
 */
@objid ("d95f1694-55b6-11e2-877f-002564c97630")
public class MessageEditPart extends AbstractConnectionEditPart implements PropertyChangeListener, NodeEditPart {
    @objid ("d95f1696-55b6-11e2-877f-002564c97630")
    @Override
    public DragTracker getDragTracker(final Request req) {
        MessageDragTracker tracker = new MessageDragTracker(this);
        tracker.setCommandName(RequestConstants.REQ_MOVE);
        tracker.setDefaultCursor(Cursors.SIZENS);
        return tracker;
    }

    @objid ("d95f169d-55b6-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE, new ConnectionEndpointEditPolicy());
        installEditPolicy(EditPolicy.NODE_ROLE, new DefaultCreateLinkEditPolicy(true));
        installEditPolicy(EditPolicy.CONNECTION_ROLE, new DefaultDeleteLinkEditPolicy());
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new GmLinkLayoutEditPolicy());
        installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new DelegatingDirectEditionEditPolicy());
        
        installEditPolicy(LinkedNodeRequestConstants.REQ_LINKEDNODE_START,
                new LinkedNodeStartCreationEditPolicy());
        installEditPolicy(CreateMultiPointRequest.REQ_MULTIPOINT_FIRST, new ConstraintLinkEditPolicy(false));
        
        installEditPolicy("MessageTranslationRole", new MessageTranslationEditPolicy());
        installEditPolicy("CreateInfoFlow", new CreateInfoFlowEditPolicy());
        installEditPolicy(ModelElementDropRequest.class, new MessageElementDropEditPolicy());
    }

    @objid ("d95f16a0-55b6-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        final RoundedLinkFigure connection = new RoundedLinkFigure();
        
        // Navigability arrow toward target
        // Exact model depends on message type.
        MessageSort sort = getLinkModel().getSort();
        // Start by defining the arrow type.
        switch (sort) {
        case SYNCCALL: {
            PolygonDecoration arrow = new PolygonDecoration();
            arrow.setTemplate(PolygonDecoration.TRIANGLE_TIP);
            arrow.setScale(9, 4);
            arrow.setOpaque(false);
            connection.setTargetDecoration(arrow);
            break;
        }
        default: {
            PolylineDecoration arrow = new PolylineDecoration();
            arrow.setTemplate(PolylineDecoration.TRIANGLE_TIP);
            arrow.setScale(9, 4);
            arrow.setOpaque(false);
            arrow.setBackgroundColor(null);
            arrow.setFill(false);
            connection.setTargetDecoration(arrow);
            break;
        }
        }
        // Now the line pattern
        switch (sort) {
        case RETURNMESSAGE: {
            connection.setLineStyle(SWT.LINE_DASH);
            break;
        }
        default: {
            connection.setLineStyle(SWT.LINE_SOLID);
            break;
        }
        }
        refreshFromStyle(connection, getModelStyle());
        return connection;
    }

    @objid ("d95f16a5-55b6-11e2-877f-002564c97630")
    @Override
    public void activate() {
        super.activate();
        
        final GmAbstractObject gmLink = getLinkModel();
        gmLink.addPropertyChangeListener(this);
    }

    /**
     * Returns an object which is an instance of the given class associated with this object. Returns <code>null</code> if no such object can be found.
     * <p>
     * Extends {@link AbstractConnectionEditPart#getAdapter(Class)} to support {@link MObject}, {@link IGmObject}, {@link GmModel} and their subclasses.
     * @see IAdaptable#getAdapter(Class)
     * 
     * @param adapter the adapter class to look up
     * @return a object castable to the given class, or <code>null</code> if this object does not have an adapter for the given class
     */
    @objid ("d95f16a8-55b6-11e2-877f-002564c97630")
    @Override
    public Object getAdapter(final Class adapter) {
        final GmModel model = getLinkModel();
        
        // Support IGmObject, GmModel and its subclasses
        if (adapter.isInstance(model)) {
            return model;
        }
        
        // Support MObject & subclasses
        final MObject obElement = model.getRelatedElement();
        if (adapter.isInstance(obElement)) {
            return obElement;
        }
        return super.getAdapter(adapter);
    }

    @objid ("d95f16b0-55b6-11e2-877f-002564c97630")
    @Override
    public List<Object> getModelChildren() {
        final ArrayList<Object> ret = new ArrayList<>(8);
        final GmLink link = getLinkModel();
        
        ret.addAll(link.getVisibleExtensions());
        ret.addAll(super.getModelChildren());
        return ret;
    }

    @objid ("d9609d1e-55b6-11e2-877f-002564c97630")
    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        final String propName = evt.getPropertyName();
        
        if (propName.equals(IGmObject.PROPERTY_LAYOUTDATA)) {
            // Link layout (bendpoints) update
            refreshSourceAnchor();
            refreshTargetAnchor();
            refreshVisuals();
        } else if (propName.equals(IGmObject.PROPERTY_CHILDREN)) {
            refreshChildren();
        } else if (propName.equals(IGmObject.PROPERTY_STYLE)) {
            refreshFromStyle(getFigure(), (IStyle) evt.getNewValue());
            // Since many extensions' visibility is driven by a style key value, force refresh of children
            refreshChildren();
        } else if (propName.equals(IGmObject.PROPERTY_LINK_SOURCE)) {
            // Links were added/removed from the link
            refreshSourceConnections();
        } else if (propName.equals(IGmObject.PROPERTY_LINK_TARGET)) {
            // Links were added/removed from the link
            refreshTargetConnections();
        } else if (propName.equals(GmLink.PROP_SOURCE_EL)) {
            // If notified that the source changed.
            if (evt.getNewValue() instanceof MObject) {
                // Build a reconnection request, that will be passed to edit
                // parts that might accept it.
                ReconnectRequest request = new ReconnectRequest(RequestConstants.REQ_RECONNECT_SOURCE);
                request.setConnectionEditPart(this);
                request.setLocation(new Point(0, 0));
        
                swapEnd((MObject) evt.getNewValue(), request);
            }
        } else if (propName.equals(GmLink.PROP_TARGET_EL)) {
            // If notified that the target changed.
            if (evt.getNewValue() instanceof MObject) {
                // Build a reconnection request, that will be passed to edit
                // parts that might accept it.
                ReconnectRequest request = new ReconnectRequest(RequestConstants.REQ_RECONNECT_TARGET);
                request.setConnectionEditPart(this);
                request.setLocation(new Point(0, 0));
        
                swapEnd((MObject) evt.getNewValue(), request);
            }
        }
    }

    @objid ("d9609d23-55b6-11e2-877f-002564c97630")
    @Override
    protected void addChildVisual(final EditPart childEditPart, final int index) {
        final IFigure childFigure = ((GraphicalEditPart) childEditPart).getFigure();
        
        final PolylineConnection connection = (PolylineConnection) getFigure();
        
        ((AbstractNodeEditPart) childEditPart).setDragTrackerProvider(new SatelliteDragTrackerProvider(childEditPart));
        
        connection.add(childFigure, index);
        
        final GmLink gmlink = getLinkModel();
        final IGmObject childModel = (IGmObject) childEditPart.getModel();
        final Locator constraint = LocatorFactory.getInstance()
                .getLocator(connection,
                        gmlink.getLayoutContraint(childModel));
        
        this.figure.setConstraint(childFigure, constraint);
    }

    /**
     * Get the connection router registry.
     * 
     * @return the connection router registry.
     */
    @objid ("d9609d2a-55b6-11e2-877f-002564c97630")
    protected ConnectionRouterRegistry getConnectionRouterRegistry() {
        return (ConnectionRouterRegistry) getViewer().getProperty(ConnectionRouterRegistry.ID);
    }

    /**
     * @return the model style.
     */
    @objid ("d9609d31-55b6-11e2-877f-002564c97630")
    protected IStyle getModelStyle() {
        return getLinkModel().getDisplayedStyle();
    }

    /**
     * Overridden to return the same anchor when raked on the source side.
     */
    @objid ("d9609d38-55b6-11e2-877f-002564c97630")
    @Override
    protected ConnectionAnchor getSourceConnectionAnchor() {
        final GmLink gm = getLinkModel();
        if (gm.getPath().getSourceAnchor() instanceof GmSourceSatelliteAnchor) {
            GmSourceSatelliteAnchor sa = (GmSourceSatelliteAnchor) gm.getPath().getSourceAnchor();
            IFigure srcFig = ((GraphicalEditPart) getTarget()).getFigure();
            return new SatelliteAnchor(srcFig, sa.getLocation());
        } else {
            return super.getSourceConnectionAnchor();
        }
    }

    /**
     * Overridden to return the same anchor when raked on the target side.
     */
    @objid ("d9609d3d-55b6-11e2-877f-002564c97630")
    @Override
    protected ConnectionAnchor getTargetConnectionAnchor() {
        final GmLink gm = getLinkModel();
        if (gm.getPath().getTargetAnchor() instanceof GmSourceSatelliteAnchor) {
            GmSourceSatelliteAnchor sa = (GmSourceSatelliteAnchor) gm.getPath().getTargetAnchor();
            IFigure srcFig = ((GraphicalEditPart) getSource()).getFigure();
            return new SatelliteAnchor(srcFig, sa.getLocation());
        } else {
            return super.getTargetConnectionAnchor();
        }
    }

    /**
     * Refresh source and target decoration line color, width and pattern from the style
     * 
     * @param connection The figure to update, should be {@link #getFigure()}.
     * @param style The style to update from, usually {@link #getModelStyle()}
     */
    @objid ("d9609d42-55b6-11e2-877f-002564c97630")
    protected void refreshDecorationsPenOptionsFromStyle(final LinkFigure connection, final IStyle style) {
        IStyleProvider model = getLinkModel();
        
        // Get style values
        int lineWidth = 1;
        Color lineColor = null;
        
        if (model.getStyleKey(MetaKey.LINECOLOR) != null) {
            lineColor = style.getColor(model.getStyleKey(MetaKey.LINECOLOR));
        }
        if (model.getStyleKey(MetaKey.LINEWIDTH) != null) {
            lineWidth = style.getInteger(model.getStyleKey(MetaKey.LINEWIDTH));
        }
        
        // Target decoration
        RotatableDecoration decoration = connection.getTargetDecoration();
        if (decoration instanceof PolygonDecoration) {
            final PolygonDecoration pennable = (PolygonDecoration) decoration;
            pennable.setForegroundColor(lineColor);
            pennable.setLineWidth(lineWidth);
            pennable.setScale(9 * lineWidth, 4 * lineWidth);
        }
    }

    /**
     * Refresh the figure from the given style. This implementation updates pen and brush properties if applicable. StyleKey are looked up by MetaKey.
     * <p>
     * Often called in {@link #createFigure()} and after a style change.
     * 
     * @param aFigure The figure to update, should be {@link #getFigure()}.
     * @param style The style to update from, usually {@link #getModelStyle()}
     */
    @objid ("d9609d4d-55b6-11e2-877f-002564c97630")
    protected void refreshFromStyle(final IFigure aFigure, final IStyle style) {
        final IStyleProvider gmModel = getLinkModel();
        
        // Set pen properties where applicable
        if (aFigure instanceof IPenOptionsSupport) {
            IPenOptionsSupport pen = (IPenOptionsSupport) aFigure;
            if (gmModel.getStyleKey(MetaKey.FONT) != null) {
                pen.setTextFont(style.getFont(gmModel.getStyleKey(MetaKey.FONT)));
            }
            if (gmModel.getStyleKey(MetaKey.TEXTCOLOR) != null) {
                pen.setTextColor(style.getColor(gmModel.getStyleKey(MetaKey.TEXTCOLOR)));
            }
            if (gmModel.getStyleKey(MetaKey.LINECOLOR) != null) {
                pen.setLineColor(style.getColor(gmModel.getStyleKey(MetaKey.LINECOLOR)));
            }
            if (gmModel.getStyleKey(MetaKey.LINEWIDTH) != null) {
                pen.setLineWidth(style.getInteger(gmModel.getStyleKey(MetaKey.LINEWIDTH)));
            }
            if (gmModel.getStyleKey(MetaKey.LINEPATTERN) != null) {
                LinePattern pattern = style.getProperty(gmModel.getStyleKey(MetaKey.LINEPATTERN));
                pen.setLinePattern(pattern);
            }
        }
        
        if (aFigure instanceof LinkFigure) {
            // Refresh decorations
            refreshDecorationsPenOptionsFromStyle((LinkFigure) aFigure, style);
        
            // Refresh rounded line radius.
            if (aFigure instanceof RoundedLinkFigure) {
                final RoundedLinkFigure roundedLinkFigure = (RoundedLinkFigure) aFigure;
        
                // Line corner radius
                final StyleKey radiusStyleKey = gmModel.getStyleKey(MetaKey.LINERADIUS);
                if (radiusStyleKey != null) {
                    roundedLinkFigure.setRadius(style.getInteger(radiusStyleKey));
                }
        
                // Enable bridges on segment crossings
                final StyleKey bridgeStyleKey = gmModel.getStyleKey(MetaKey.DRAWLINEBRIDGES);
                if (bridgeStyleKey != null) {
                    roundedLinkFigure.setBridgesEnabled(style.getBoolean(bridgeStyleKey));
                }
            }
        }
    }

    @objid ("d9609d56-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshVisuals() {
        super.refreshVisuals();
        
        final GmLink gmLink = getLinkModel();
        final PolylineConnection conn = (PolylineConnection) getFigure();
        
        // Update the connection router & Refresh route
        updateConnectionRoute(conn);
        
        // Refresh children constraint
        for (Object c : getChildren()) {
            final GraphicalEditPart childPart = (GraphicalEditPart) c;
            final IGmObject childNode = (IGmObject) childPart.getModel();
            final IGmLocator gmLoc = gmLink.getLayoutContraint(childNode);
            final Locator loc = LocatorFactory.getInstance().getLocator(conn, gmLoc);
            conn.setConstraint(childPart.getFigure(), loc);
        }
    }

    @objid ("d96223b9-55b6-11e2-877f-002564c97630")
    private GmMessage getLinkModel() {
        return (GmMessage) getModel();
    }

    /**
     * Change the source or destination of the link given by the request.
     * <p>
     * Unmask the element if it is not displayed in the diagram. Updates the model, the graphic model and the view.
     * 
     * @param newEndElement the new source/destination
     * @param request The reconnect request.
     */
    @objid ("d96223bd-55b6-11e2-877f-002564c97630")
    protected final void swapEnd(final MObject newEndElement, final ReconnectRequest request) {
        // Search all gm representing the new target
        Collection<GmModel> models = getLinkModel().getDiagram().getAllGMRelatedTo(new MRef(newEndElement));
        // This boolean will be used to note that the searched End was found
        // unmasked at least once.
        boolean foundUnmaskedEnd = false;
        for (GmModel model : models) {
            // For each gm, search the corresponding edit part
            EditPart editPart = (EditPart) getViewer().getEditPartRegistry().get(model);
            if (editPart != null) {
                foundUnmaskedEnd = true;
                // See if this edit part accepts the reconnection request
                EditPart targetEditPart = editPart.getTargetEditPart(request);
                if (targetEditPart != null) {
                    request.setTargetEditPart(targetEditPart);
                    Command command = targetEditPart.getCommand(request);
                    if (command != null && command.canExecute()) {
                        // Found a potential new target!
                        command.execute();
                        return;
                    }
                }
            }
        }
        if (!foundUnmaskedEnd) {
            // The searched end was not found in the diagram (either there is no
            // Gm for it, or the Gm is "not visible" in that it doesn"t have an
            // EditPart).
            // => We will try to unmasked the searched end at roughly the same
            // place as the previous end.
            ModelElementDropRequest dropRequest = new ModelElementDropRequest();
            dropRequest.setDroppedElements(new MObject[] { newEndElement });
            Point dropPoint;
            if (request.getType().equals(RequestConstants.REQ_RECONNECT_SOURCE)) {
                dropPoint = ((GraphicalEditPart) getSource()).getFigure()
                        .getBounds()
                        .getLocation()
                        .getTranslated(20, 20);
            } else if (getTarget() != null) {
                dropPoint = ((GraphicalEditPart) getTarget()).getFigure()
                        .getBounds()
                        .getLocation()
                        .getTranslated(20, 20);
            } else {
                // Fallback, should not happen...
                dropPoint = new Point(0, 0);
            }
            dropRequest.setLocation(dropPoint);
            GmCompositeNode gmCompositeForUnmasking = getLinkModel()
                    .getDiagram()
                    .getCompositeFor(newEndElement.getClass());
            EditPart compositeEditPartForUnmasking = (EditPart) getViewer().getEditPartRegistry()
                    .get(gmCompositeForUnmasking);
            if (compositeEditPartForUnmasking == null) {
                return;
            }
            compositeEditPartForUnmasking = compositeEditPartForUnmasking.getTargetEditPart(dropRequest);
            Command command = compositeEditPartForUnmasking.getCommand(dropRequest);
            if (command == null || !command.canExecute()) {
                return;
            }
            command.execute();
        }
        // Now that (hopefully) we managed to unmasked the new end, let's try to
        // reroute ourselves to it!
        models = getLinkModel().getDiagram().getAllGMRelatedTo(new MRef(newEndElement));
        for (GmModel model : models) {
            // For each gm, search the corresponding edit part
            EditPart editPart = (EditPart) getViewer().getEditPartRegistry().get(model);
            if (editPart != null) {
                // See if this edit part accepts the reconnection request
                EditPart targetEditPart = editPart.getTargetEditPart(request);
                if (targetEditPart != null) {
                    request.setTargetEditPart(targetEditPart);
                    Command command = targetEditPart.getCommand(request);
                    if (command != null && command.canExecute()) {
                        // Found a potential new target!
                        command.execute();
                        return;
                    }
                }
            }
        }
    }

    /**
     * Update the connection router, the edit policies and the drag tracker from the model routing style.
     * 
     * @param cnx The connection figure
     */
    @objid ("d96223c6-55b6-11e2-877f-002564c97630")
    private void updateConnectionRoute(final Connection cnx) {
        // Refresh anchors
        refreshSourceAnchor();
        refreshTargetAnchor();
        
        // Change connection router
        MessageEnd sourceElement = (MessageEnd) ((GmMessage) getModel()).getFromElement();
        MessageEnd targetElement = (MessageEnd) ((GmMessage) getModel()).getToElement();
        if (sourceElement.getCovered().size() > 0 &&
                targetElement.getCovered().size() > 0 &&
                sourceElement.getCovered().get(0).equals(targetElement.getCovered().get(0))) {
            cnx.setConnectionRouter(RecursiveMessageRouter.getInstance());
        
        } else {
            final ConnectionRouter newRouter = getConnectionRouterRegistry().get(ConnectionRouterId.DIRECT);
            cnx.setConnectionRouter(newRouter);
        }
    }

    /**
     * Overridden to add a specific behavior on {@link RequestConstants#REQ_DIRECT_EDIT DIRECT_EDIT} request: the request if forwarded to all children edit parts until one understand it, and then said child edit part is asked to perform the request.
     */
    @objid ("d96223cb-55b6-11e2-877f-002564c97630")
    @Override
    public void performRequest(final Request req) {
        if (RequestConstants.REQ_DIRECT_EDIT.equals(req.getType())) {
            if (req instanceof LocationRequest) {
                // Give the request to the child where the request is located
                final Point reqLocation = ((LocationRequest) req).getLocation();
        
                for (Object childEditPartObj : getChildren()) {
        
                    final GraphicalEditPart childEditPart = (GraphicalEditPart) childEditPartObj;
                    if (childEditPart.understandsRequest(req) &&
                            containsAbsolutePoint(childEditPart, reqLocation)) {
                        childEditPart.performRequest(req);
                    }
        
                }
            } else {
                // Give the request to the first child that understand it
                for (Object childEditPartObj : getChildren()) {
                    final EditPart childEditPart = (EditPart) childEditPartObj;
                    if (childEditPart.understandsRequest(req)) {
                        childEditPart.performRequest(req);
                        return;
                    }
        
                }
            }
        }
        super.performRequest(req);
    }

    @objid ("d96223d1-55b6-11e2-877f-002564c97630")
    private boolean containsAbsolutePoint(final GraphicalEditPart editPart, final Point aPoint) {
        final IFigure fig = editPart.getFigure();
        final Point p = aPoint.getCopy();
        fig.translateToRelative(p);
        return fig.containsPoint(p);
    }

    /**
     * Returns the <code>ConnectionAnchor</code> for the specified <i>source</i> connection. This NodeEditPart is the {@link ConnectionEditPart#getSource() source} EditPart for the given connection.
     * <P>
     * The anchor may be a function of the connection's model, the node's model, a combination of both, or it may not depend on anything all.
     * 
     * @param connection the ConnectionEditPart
     * @return the ConnectionAnchor for the given ConnectionEditPart
     */
    @objid ("d96223d9-55b6-11e2-877f-002564c97630")
    @Override
    public ConnectionAnchor getSourceConnectionAnchor(final ConnectionEditPart connection) {
        // TODO Temporary fake implementation: try to return the anchor of 'this' origin anchoring point
        // final GmLink gmlink = (GmLink) getModel();
        return new LinkAnchor(getConnectionFigure(), new GmFractionalConnectionLocator(0.5, 0, 0));
    }

    /**
     * Returns the <i>source</i> <code>ConnectionAnchor</code> for the specified Request. The returned ConnectionAnchor is used only when displaying <i>feedback</i>. The Request is usually a {@link org.eclipse.gef.requests.LocationRequest}, which provides
     * the current mouse location.
     * 
     * @param request a Request describing the current interaction
     * @return the ConnectionAnchor to use during feedback
     */
    @objid ("d96223e1-55b6-11e2-877f-002564c97630")
    @Override
    public ConnectionAnchor getSourceConnectionAnchor(final Request request) {
        // TODO Temporary fake implementation: try to return the anchor of 'this' origin anchoring point
        // final GmLink gmlink = (GmLink) getModel();
        // ConnectionAnchor anchor = this.getConnectionFigure().getSourceAnchor();
        return new LinkAnchor(getConnectionFigure(), new GmFractionalConnectionLocator(0.5, 0, 0));
    }

    /**
     * Returns the <code>ConnectionAnchor</code> for the specified <i>target</i> connection. This NodeEditPart is the {@link ConnectionEditPart#getTarget() target} EditPart for the given connection.
     * <P>
     * The anchor may be a function of the connection's model, the node's model, a combination of both, or it may not depend on anything all.
     * 
     * @param connection the ConnectionEditPart
     * @return the ConnectionAnchor for the given ConnectionEditPart
     */
    @objid ("d963aa59-55b6-11e2-877f-002564c97630")
    @Override
    public ConnectionAnchor getTargetConnectionAnchor(final ConnectionEditPart connection) {
        // TODO Temporary fake implementation: try to return the anchor of 'this' origin anchoring point
        // final GmLink gmlink = (GmLink) getModel();
        return new LinkAnchor(getConnectionFigure(), new GmFractionalConnectionLocator(0.5, 0, 0));
    }

    /**
     * Returns the <i>target</i> <code>ConnectionAnchor</code> for the specified Request. The returned ConnectionAnchor is used only when displaying <i>feedback</i>. The Request is usually a {@link org.eclipse.gef.requests.LocationRequest}, which provides
     * the current mouse location.
     * 
     * @param request a Request describing the current interaction
     * @return the ConnectionAnchor to use during feedback
     */
    @objid ("d963aa61-55b6-11e2-877f-002564c97630")
    @Override
    public ConnectionAnchor getTargetConnectionAnchor(final Request request) {
        // TODO Temporary fake implementation: try to return the anchor of 'this' origin anchoring point
        // final GmLink gmlink = (GmLink) getModel();
        return new LinkAnchor(getConnectionFigure(), new GmFractionalConnectionLocator(0.5, 0, 0));
    }

    @objid ("d963aa69-55b6-11e2-877f-002564c97630")
    @Override
    protected List<?> getModelSourceConnections() {
        return getLinkModel().getStartingLinks();
    }

    @objid ("d963aa70-55b6-11e2-877f-002564c97630")
    @Override
    protected List<IGmLink> getModelTargetConnections() {
        return getLinkModel().getEndingLinks();
    }

    @objid ("d963aa77-55b6-11e2-877f-002564c97630")
    @Override
    public void deactivate() {
        super.deactivate();
        
        final GmAbstractObject gmLink = getLinkModel();
        gmLink.removePropertyChangeListener(this);
    }

}
