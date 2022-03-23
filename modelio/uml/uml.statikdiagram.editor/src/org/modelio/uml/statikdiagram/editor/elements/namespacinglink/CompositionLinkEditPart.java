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
package org.modelio.uml.statikdiagram.editor.elements.namespacinglink;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.Locator;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gef.requests.LocationRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.eclipse.swt.graphics.Color;
import org.modelio.diagram.elements.common.portcontainer.SatelliteDragTrackerProvider;
import org.modelio.diagram.elements.core.figures.IBrushOptionsSupport;
import org.modelio.diagram.elements.core.figures.IPenOptionsSupport;
import org.modelio.diagram.elements.core.figures.LinkFigure;
import org.modelio.diagram.elements.core.figures.RoundedLinkFigure;
import org.modelio.diagram.elements.core.figures.anchors.LinkAnchor;
import org.modelio.diagram.elements.core.link.ConnectionRoutingServices;
import org.modelio.diagram.elements.core.link.ConnectionRoutingServices.IRouterDependentEditPolicyFactory;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.link.GmLinkLayoutEditPolicy;
import org.modelio.diagram.elements.core.link.GmPath;
import org.modelio.diagram.elements.core.link.IAnchorModelProvider;
import org.modelio.diagram.elements.core.link.RoutingMode;
import org.modelio.diagram.elements.core.link.SelectConnectionEditPartTracker;
import org.modelio.diagram.elements.core.link.extensions.GmFractionalConnectionLocator;
import org.modelio.diagram.elements.core.link.extensions.IGmLocator;
import org.modelio.diagram.elements.core.link.extensions.LocatorFactory;
import org.modelio.diagram.elements.core.link.path.ConnectionPolicyUtils;
import org.modelio.diagram.elements.core.link.path.IConnectionHelper;
import org.modelio.diagram.elements.core.link.path.IConnectionHelperFactory;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.policies.DefaultDeleteLinkEditPolicy;
import org.modelio.diagram.elements.core.policies.DelegatingDirectEditionEditPolicy;
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId;
import org.modelio.diagram.styles.core.StyleKey.LinePattern;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Edit part for the GmCompositionLink
 * 
 * @author fpoyer
 */
@objid ("35a670c0-55b7-11e2-877f-002564c97630")
public class CompositionLinkEditPart extends AbstractConnectionEditPart implements PropertyChangeListener, IAnchorModelProvider {
    @objid ("cf02989d-6fe4-4870-ad9e-e6640e5732fd")
    private RoutingMode currentRoutingMode = new RoutingMode();

    @objid ("35a670c5-55b7-11e2-877f-002564c97630")
    protected void refreshFromStyle(final IFigure aFigure, final IStyle style) {
        final GmLink gmModel = getModel();
        
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
        
        refreshRouterFromStyle((Connection) aFigure, style, gmModel);
        refreshDecorationFromStyle((LinkFigure) aFigure, style);
        
    }

    @objid ("35a7f73c-55b7-11e2-877f-002564c97630")
    protected void refreshDecorationFromStyle(final LinkFigure connection, final IStyle style) {
        GmCompositionLink model = (GmCompositionLink) getModel();
        // Source decoration
        RotatableDecoration decoration = connection.getSourceDecoration();
        if (decoration != null) {
            final IPenOptionsSupport pennable = (IPenOptionsSupport) decoration;
            pennable.setLineWidth(style.getInteger(model.getStyleKey(MetaKey.LINEWIDTH)));
            pennable.setLineColor(style.getColor(model.getStyleKey(MetaKey.LINECOLOR)));
            final IBrushOptionsSupport brushable = (IBrushOptionsSupport) decoration;
            brushable.setFillColor(style.getColor(model.getStyleKey(MetaKey.FILLCOLOR)));
        
        }
        
    }

    @objid ("35a7f746-55b7-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        PolylineConnection fig = new RoundedLinkFigure();
        CircleDeco dec = new CircleDeco();
        dec.setOpaque(true);
        fig.setSourceDecoration(dec);
        refreshFromStyle(fig, getModelStyle());
        return fig;
    }

    @objid ("35a7f74b-55b7-11e2-877f-002564c97630")
    @Override
    public void activate() {
        super.activate();
        
        final GmLink gmLink = getModel();
        gmLink.addPropertyChangeListener(this);
        
    }

    @objid ("35a7f74e-55b7-11e2-877f-002564c97630")
    @Override
    public Object createAnchorModel(final ConnectionAnchor anchor) {
        return null;
    }

    @objid ("35a7f755-55b7-11e2-877f-002564c97630")
    @Override
    public Object getAdapter(final Class adapter) {
        final Object model = getModel();
        
        // Support IGmObject, GmModel and its subclasses
        if (adapter.isInstance(model)) {
            return model;
        }
        
        // Support MObject & subclasses
        if (model instanceof GmModel) {
            final GmModel gmModel = (GmModel) model;
            final MObject obElement = gmModel.getRelatedElement();
            if (adapter.isInstance(obElement)) {
                return obElement;
            }
        }
        return super.getAdapter(adapter);
    }

    @objid ("35a7f75c-55b7-11e2-877f-002564c97630")
    @Override
    public DragTracker getDragTracker(final Request req) {
        return new SelectConnectionEditPartTracker(this);
    }

    @objid ("35a7f763-55b7-11e2-877f-002564c97630")
    @Override
    public List<Object> getModelChildren() {
        final ArrayList<Object> ret = new ArrayList<>(8);
        final GmLink link = getModel();
        
        ret.addAll(link.getVisibleExtensions());
        ret.addAll(super.getModelChildren());
        return ret;
    }

    @objid ("35a7f76a-55b7-11e2-877f-002564c97630")
    @Override
    public ConnectionAnchor getSourceConnectionAnchor(final ConnectionEditPart connection) {
        return new LinkAnchor(getConnectionFigure(), new GmFractionalConnectionLocator(0.5, 0, 0));
    }

    @objid ("35a7f771-55b7-11e2-877f-002564c97630")
    @Override
    public ConnectionAnchor getSourceConnectionAnchor(final Request request) {
        return new LinkAnchor(getConnectionFigure(), new GmFractionalConnectionLocator(0.5, 0, 0));
    }

    @objid ("35a97dda-55b7-11e2-877f-002564c97630")
    @Override
    public ConnectionAnchor getTargetConnectionAnchor(final ConnectionEditPart connection) {
        return new LinkAnchor(getConnectionFigure(), new GmFractionalConnectionLocator(0.5, 0, 0));
    }

    @objid ("35a97de1-55b7-11e2-877f-002564c97630")
    @Override
    public ConnectionAnchor getTargetConnectionAnchor(final Request request) {
        return new LinkAnchor(getConnectionFigure(), new GmFractionalConnectionLocator(0.5, 0, 0));
    }

    @objid ("35a97de8-55b7-11e2-877f-002564c97630")
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

    @objid ("35a97ded-55b7-11e2-877f-002564c97630")
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
        } else if (propName.equals(IGmObject.PROPERTY_LINK_SOURCE)) {
            // Links were added/removed from the link
            refreshSourceConnections();
        } else if (propName.equals(IGmObject.PROPERTY_LINK_TARGET)) {
            // Links were added/removed from the link
            refreshTargetConnections();
        }
        
    }

    @objid ("35a97df2-55b7-11e2-877f-002564c97630")
    @Override
    protected void addChildVisual(final EditPart childEditPart, final int index) {
        final IFigure childFigure = ((GraphicalEditPart) childEditPart).getFigure();
        
        final PolylineConnection connection = (PolylineConnection) getFigure();
        
        ((AbstractNodeEditPart) childEditPart).setDragTrackerProvider(new SatelliteDragTrackerProvider(childEditPart));
        
        connection.add(childFigure, index);
        
        final GmLink gmlink = getModel();
        final IGmObject childModel = (IGmObject) childEditPart.getModel();
        final Locator constraint = LocatorFactory.getInstance()
                .getLocator(connection,
                        gmlink.getLayoutContraint(childModel));
        
        this.figure.setConstraint(childFigure, constraint);
        
    }

    @objid ("35a97df9-55b7-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.CONNECTION_ROLE, new DefaultDeleteLinkEditPolicy());
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new GmLinkLayoutEditPolicy());
        installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new DelegatingDirectEditionEditPolicy());
        
        if (getRoutingMode().routingStyle != null) {
            updateRouterDependentEditPolicies(getRoutingMode());
        }
        
    }

    @objid ("35a97e02-55b7-11e2-877f-002564c97630")
    protected final GmLink getLinkModel() {
        return getModel();
    }

    @objid ("35a97e08-55b7-11e2-877f-002564c97630")
    @Override
    protected List<IGmLink> getModelSourceConnections() {
        return getModel().getStartingLinks();
    }

    @objid ("35a97e0e-55b7-11e2-877f-002564c97630")
    protected IStyle getModelStyle() {
        return getModel().getDisplayedStyle();
    }

    @objid ("35ab047d-55b7-11e2-877f-002564c97630")
    @Override
    protected List<IGmLink> getModelTargetConnections() {
        return getModel().getEndingLinks();
    }

    @objid ("35ab0483-55b7-11e2-877f-002564c97630")
    protected RoutingMode getRoutingMode() {
        return this.currentRoutingMode;
    }

    @objid ("35ab0487-55b7-11e2-877f-002564c97630")
    protected void refreshDecorationsPenOptionsFromStyle(final LinkFigure connection, final IStyle style) {
        GmLink model = getModel();
        
        // Get style values
        int lineWidth = 1;
        LinePattern lineStyle = LinePattern.LINE_SOLID;
        Color lineColor = null;
        
        if (model.getStyleKey(MetaKey.LINECOLOR) != null) {
            lineColor = style.getColor(model.getStyleKey(MetaKey.LINECOLOR));
        }
        if (model.getStyleKey(MetaKey.LINEWIDTH) != null) {
            lineWidth = style.getInteger(model.getStyleKey(MetaKey.LINEWIDTH));
        }
        if (model.getStyleKey(MetaKey.LINEPATTERN) != null) {
            lineStyle = style.getProperty(model.getStyleKey(MetaKey.LINEPATTERN));
        }
        
        // Source decoration
        RotatableDecoration decoration = connection.getSourceDecoration();
        if (decoration instanceof IPenOptionsSupport) {
            final IPenOptionsSupport pennable = (IPenOptionsSupport) decoration;
            pennable.setLineColor(lineColor);
            pennable.setLinePattern(lineStyle);
            pennable.setLineWidth(lineWidth);
        }
        
        // Target decoration
        decoration = connection.getTargetDecoration();
        if (decoration instanceof IPenOptionsSupport) {
            final IPenOptionsSupport pennable = (IPenOptionsSupport) decoration;
            pennable.setLineColor(lineColor);
            pennable.setLinePattern(lineStyle);
            pennable.setLineWidth(lineWidth);
        }
        
    }

    @objid ("35ab0491-55b7-11e2-877f-002564c97630")
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
            if (loc != null) {
                conn.setConstraint(childPart.getFigure(), loc);
            }
        }
        
    }

    @objid ("35ab0494-55b7-11e2-877f-002564c97630")
    @Override
    protected void reorderChild(final EditPart child, final int index) {
        // Save the constraint of the child so that it does not
        // get lost during the remove and re-add.
        IFigure childFigure = ((GraphicalEditPart) child).getFigure();
        LayoutManager layout = childFigure.getParent().getLayoutManager();
        Object constraint = null;
        if (layout != null) {
            constraint = layout.getConstraint(childFigure);
        }
        
        // super.reorderChild(child, index);
        // Copy of AbstractEditPart#reorderChild(EditPart, int)
        removeChildVisual(child);
        List<Object> lchildren = getChildren();
        lchildren.remove(child);
        lchildren.add(index, child);
        addChildVisual(child, index);
        
        if (constraint != null) {
            setLayoutConstraint(child, childFigure, constraint);
        }
        
    }

    @objid ("35ab049b-55b7-11e2-877f-002564c97630")
    protected final void swapEnd(final MObject newEndElement, final ReconnectRequest request) {
        // Search all gm representing the new target
        Collection<GmModel> models = getModel().getDiagram()
                .getAllGMRelatedTo(new MRef(newEndElement));
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
            unmaskEndElement(newEndElement, request);
        }
        // Now that (hopefully) we managed to unmasked the new end, let's try to
        // reroute ourselves to it!
        models = getModel().getDiagram().getAllGMRelatedTo(new MRef(newEndElement));
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

    @objid ("35ab04a3-55b7-11e2-877f-002564c97630")
    private boolean containsAbsolutePoint(final GraphicalEditPart editPart, final Point aPoint) {
        final IFigure fig = editPart.getFigure();
        final Point p = aPoint.getCopy();
        fig.translateToRelative(p);
        return fig.containsPoint(p);
    }

    @objid ("35ab04ab-55b7-11e2-877f-002564c97630")
    private void refreshRouterFromStyle(final Connection connectionFigure, final IStyle style, final GmLink gmLink) {
        // Update the connection router if changed from the style.
        final StyleKey routerStyleKey = gmLink.getStyleKey(MetaKey.CONNECTIONROUTER);
        if (routerStyleKey != null) {
            final ConnectionRouterId styleRouter = style.getProperty(routerStyleKey);
            final ConnectionRouterId oldRouter = gmLink.getPath().getRouterKind();
            if (styleRouter != oldRouter) {
                final GmPath newPath = new GmPath(gmLink.getPath());
                newPath.setRouterKind(styleRouter);
        
                IConnectionHelperFactory connectionHelperFactory = ConnectionPolicyUtils.getRoutingServices(this).getConnectionHelperFactory();
                IConnectionHelper oldHelper = connectionHelperFactory.createFromSerializedData(oldRouter,
                        this,
                        connectionFigure);
                IConnectionHelper newHelper = connectionHelperFactory.convert(oldHelper,
                        styleRouter,
                        connectionFigure);
        
                newPath.setPathData(newHelper.getModelPathData());
        
                gmLink.setLayoutData(newPath);
            }
        }
        
    }

    /**
     * Update edit policies that depend on the connection routing mode.
     * @param mode the new routing mode
     */
    @objid ("35ac8b1b-55b7-11e2-877f-002564c97630")
    private void updateRouterDependentEditPolicies(final RoutingMode mode) {
        IRouterDependentEditPolicyFactory editPoliciesFactory = ConnectionPolicyUtils.getRoutingServices(this).getEditPoliciesFactory();
        
        // Note : installEditPolicy(...) removes cleanly the existing policy if any
        installEditPolicy(EditPolicy.CONNECTION_BENDPOINTS_ROLE, editPoliciesFactory.createBendPointsPolicy(mode));
        installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE, editPoliciesFactory.createEndPointsPolicy(mode));
        
    }

    @objid ("35ac8b1f-55b7-11e2-877f-002564c97630")
    private void updateConnectionRoute(final Connection cnx) {
        // Refresh anchors
        refreshSourceAnchor();
        refreshTargetAnchor();
        
        final IGmLink gmLink = getLinkModel();
        final RoutingMode newRoutingMode = new RoutingMode(gmLink.getPath());
        final RoutingMode oldRoutingMode = getRoutingMode();
        
        // Change connection router if the rake mode changes or there is no rake and the style changes
        ConnectionRoutingServices routingServices = ConnectionPolicyUtils.getRoutingServices(this);
        if (oldRoutingMode.routingStyle != newRoutingMode.routingStyle) {
            // Set the connection router
            cnx.setConnectionRouter(routingServices.getDisplayRouter(newRoutingMode.routingStyle));
        
            // Set the new constraint
            IConnectionHelper helper = routingServices.getConnectionHelperFactory().createFromSerializedData(
                    newRoutingMode.routingStyle,
                    this,
                    cnx);
            cnx.setRoutingConstraint(helper.getRoutingConstraint());
        
            // Update edit policy
            updateRouterDependentEditPolicies(newRoutingMode);
        
            this.currentRoutingMode = newRoutingMode;
        
        } else {
            IConnectionHelper helper = routingServices.getConnectionHelperFactory().createFromSerializedData(
                    newRoutingMode.routingStyle,
                    this,
                    cnx);
            cnx.setRoutingConstraint(helper.getRoutingConstraint());
        }
        
    }

    @objid ("35ac8b23-55b7-11e2-877f-002564c97630")
    private void unmaskEndElement(final MObject newEndElement, final ReconnectRequest request) {
        // The searched end was not found in the diagram (either there is no
        // Gm for it, or the Gm is "not visible" in that it doesn"t have an
        // EditPart).
        // => We will try to unmasked the searched end at roughly the same
        // place as the previous end.
        ModelElementDropRequest dropRequest = new ModelElementDropRequest();
        dropRequest.setDroppedElements(new MObject[] { newEndElement });
        Point dropPoint;
        if (request.getType().equals(RequestConstants.REQ_RECONNECT_SOURCE)) {
            ((GraphicalEditPart) getRoot().getContents()).getFigure().invalidateTree();
            ((GraphicalEditPart) getRoot().getContents()).getFigure().validate();
            IFigure sourceFigure = ((GraphicalEditPart) getSource()).getFigure();
            dropPoint = sourceFigure.getBounds().getLocation().getTranslated(20, 20);
            sourceFigure.translateToAbsolute(dropPoint);
        } else {
            ((GraphicalEditPart) getRoot().getContents()).getFigure().invalidateTree();
            ((GraphicalEditPart) getRoot().getContents()).getFigure().validate();
            IFigure targetFigure = ((GraphicalEditPart) getTarget()).getFigure();
            dropPoint = targetFigure.getBounds().getLocation().getTranslated(20, 20);
            targetFigure.translateToAbsolute(dropPoint);
        }
        dropRequest.setLocation(dropPoint);
        GmCompositeNode gmCompositeForUnmasking = getModel().getDiagram()
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

    @objid ("27144bde-8793-4eca-a4e7-8eb70755f313")
    @Override
    public GmLink getModel() {
        return (GmLink) super.getModel();
    }

    @objid ("e964442b-e157-4dfb-9077-935e0333cdd2")
    @Override
    public void deactivate() {
        final GmLink gmLink = getModel();
        gmLink.removePropertyChangeListener(this);
        
        super.deactivate();
        
    }

    /**
     * {@inheritDoc}
     * <p>
     * Redefined to set the collections of all diagram connections on the {@link RoundedLinkFigure}. This collection is used to find intersections to draw bridges.
     * @since 3.7
     * @see RoundedLinkFigure#setAllDiagramConnections(Collection)
     */
    @objid ("160e7879-fc42-4565-b5e8-f5d0d76e9b69")
    @Override
    protected void activateFigure() {
        super.activateFigure();
        
        IFigure fig = getFigure();
        if (fig instanceof RoundedLinkFigure) {
            // Set the collections of all diagram connections.
            // This collection is used to find intersections to draw bridges.
            Collection<Connection> allDiagramConnections = ConnectionPolicyUtils.getAllDiagramConnectionsCollector(this);
            ((RoundedLinkFigure) fig).setAllDiagramConnections(allDiagramConnections);
        }
        
    }

}
