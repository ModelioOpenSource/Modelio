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
package org.modelio.diagram.elements.core.link;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IAdaptable;
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
import org.eclipse.gef.tools.SelectEditPartTracker;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.graphics.Color;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeRequestConstants;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeStartCreationEditPolicy;
import org.modelio.diagram.elements.common.portcontainer.SatelliteDragTrackerProvider;
import org.modelio.diagram.elements.core.figures.IPenOptionsSupport;
import org.modelio.diagram.elements.core.figures.LinkFigure;
import org.modelio.diagram.elements.core.figures.RoundedLinkFigure;
import org.modelio.diagram.elements.core.figures.routers.AutoOrthogonalRouter;
import org.modelio.diagram.elements.core.figures.routers.RakeRouter;
import org.modelio.diagram.elements.core.helpers.palapi.PaletteActionProvider;
import org.modelio.diagram.elements.core.link.ConnectionRoutingServices.IRouterDependentEditPolicyFactory;
import org.modelio.diagram.elements.core.link.anchors.INodeAnchorProvider;
import org.modelio.diagram.elements.core.link.anchors.RectangleNodeAnchorProvider;
import org.modelio.diagram.elements.core.link.anchors.SimpleLinkNodeAnchorProvider;
import org.modelio.diagram.elements.core.link.createhandle.UserChoiceCreateLinkEditPolicy;
import org.modelio.diagram.elements.core.link.extensions.IGmLocator;
import org.modelio.diagram.elements.core.link.extensions.LocatorFactory;
import org.modelio.diagram.elements.core.link.ortho.AutoOrthogonalRouterSynchronizeConstraintCommand;
import org.modelio.diagram.elements.core.link.path.ConnectionPolicyUtils;
import org.modelio.diagram.elements.core.link.path.IConnectionHelper;
import org.modelio.diagram.elements.core.link.path.IConnectionHelperFactory;
import org.modelio.diagram.elements.core.link.rake.CreateRakeLinkEditPolicy;
import org.modelio.diagram.elements.core.link.rake.RakeRefreshEditPolicy;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.policies.DefaultDeleteLinkEditPolicy;
import org.modelio.diagram.elements.core.policies.DefaultRefreshFromModelEditPolicy;
import org.modelio.diagram.elements.core.policies.DelegatingDirectEditionEditPolicy;
import org.modelio.diagram.elements.core.policies.LayoutConnectionConnectionsEditPolicy;
import org.modelio.diagram.elements.core.policies.LayoutNodeConnectionsEditPolicy;
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;
import org.modelio.diagram.elements.core.requests.NavigationRequest;
import org.modelio.diagram.elements.core.tools.multipoint.CreateMultiPointRequest;
import org.modelio.diagram.elements.umlcommon.constraint.ConstraintLinkEditPolicy;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId;
import org.modelio.diagram.styles.core.StyleKey.LinePattern;
import org.modelio.platform.core.activation.IActivationService;
import org.modelio.platform.core.navigate.IModelioNavigationService;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Base edit part implementation for {@link GmLink}.
 * <p>
 * A <code>LinkEditPart</code>: <br/>
 * <li>extends {@link AbstractConnectionEditPart} => to have a link behavior</li>
 * <li>implements {@link IAnchorModelProvider} => to be usable as a source or a target of other links</li>
 * <li>implements {@link PropertyChangeListener} => for update and refreshing from model or style change</li>
 */
@objid ("80199771-1dec-11e2-8cad-001ec947c8cc")
public class LinkEditPart extends AbstractConnectionEditPart implements PropertyChangeListener, IAnchorModelProvider {
    @objid ("80199775-1dec-11e2-8cad-001ec947c8cc")
    private RoutingMode currentRoutingMode = new RoutingMode();

    /**
     * Rake router for all links.
     */
    @objid ("80199776-1dec-11e2-8cad-001ec947c8cc")
    protected static final RakeRouter rakeRouter = new RakeRouter();

    /**
     * Creates a {@link LinkEditPart}.
     */
    @objid ("80199778-1dec-11e2-8cad-001ec947c8cc")
    public  LinkEditPart() {
        super();
    }

    @objid ("8019977b-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void activate() {
        super.activate();
        
        final GmLink gmLink = getModel();
        gmLink.addPropertyChangeListener(this);
        
    }

    @objid ("8019977e-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Object createAnchorModel(final ConnectionAnchor anchor) {
        return getNodeAnchorProvider().createAnchorModel(anchor);
    }

    @objid ("801bf99d-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void deactivate() {
        super.deactivate();
        
        getModel().removePropertyChangeListener(this);
        
    }

    /**
     * Returns an object which is an instance of the given class associated with this object. Returns <code>null</code> if no such object can be found.
     * <p>
     * Extends {@link AbstractConnectionEditPart#getAdapter(Class)} to support {@link MObject}, {@link IGmObject}, {@link GmModel} and their subclasses.
     * @see IAdaptable#getAdapter(Class)
     * @param adapter the adapter class to look up
     * @return a object castable to the given class, or <code>null</code> if this object does not have an adapter for the given class
     */
    @objid ("801bf9a0-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Object getAdapter(Class adapter) {
        final GmLink gmModel = getModel();
        
        // Support IGmObject, GmModel and its subclasses
        if (adapter.isInstance(gmModel)) {
            return gmModel;
        }
        
        // Support MObject & subclasses
        if (gmModel != null) {
            final MObject obElement = gmModel.getRelatedElement();
            if (adapter.isInstance(obElement)) {
                return obElement;
            }
        }
        
        // Support indirectly IFixedConnectionAnchorFactory
        INodeAnchorProvider anchProv = getNodeAnchorProvider();
        if (adapter.isInstance(anchProv)) {
            return getNodeAnchorProvider();
        }
        return super.getAdapter(adapter);
    }

    @objid ("801bf9a7-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public DragTracker getDragTracker(Request req) {
        if (isModelUserEditable()) {
            return new SelectConnectionEditPartTracker(this);
        } else {
            return new SelectEditPartTracker(this);
        }
        
    }

    @objid ("801bf9b1-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public List<Object> getModelChildren() {
        final ArrayList<Object> ret = new ArrayList<>(8);
        final GmLink link = getModel();
        
        ret.addAll(link.getVisibleExtensions());
        ret.addAll(super.getModelChildren());
        return ret;
    }

    @objid ("801bf9b8-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public ConnectionAnchor getSourceConnectionAnchor(final ConnectionEditPart connection) {
        return getNodeAnchorProvider().getSourceConnectionAnchor(this, connection);
    }

    @objid ("801bf9c3-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public ConnectionAnchor getSourceConnectionAnchor(final Request request) {
        return getNodeAnchorProvider().getSourceConnectionAnchor(this, request);
    }

    @objid ("801bf9ce-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public ConnectionAnchor getTargetConnectionAnchor(final ConnectionEditPart connection) {
        return getNodeAnchorProvider().getTargetConnectionAnchor(this, connection);
    }

    @objid ("801bf9d9-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public ConnectionAnchor getTargetConnectionAnchor(final Request request) {
        return getNodeAnchorProvider().getTargetConnectionAnchor(this, request);
    }

    /**
     * Get the service used to produce connection anchors from ConnectionEditPart of Request.
     * <p>
     * Default implementation returns {@link RectangleNodeAnchorProvider}. May be redefined to use another service.
     * @return the service used to produce connection anchors from ConnectionEditPart of Request.
     * @since 5.1.0
     */
    @objid ("32f539be-e370-43d8-8249-75f44d0c5261")
    protected INodeAnchorProvider getNodeAnchorProvider() {
        return SimpleLinkNodeAnchorProvider.INSTANCE;
    }

    /**
     * Overridden to add a specific behavior on {@link RequestConstants#REQ_DIRECT_EDIT DIRECT_EDIT} request: the request if forwarded to all children edit parts until one understand it, and then said child edit part is asked to perform the request.
     */
    @objid ("801bf9e4-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void performRequest(final Request req) {
        if (NavigationRequest.TYPE == req.getType()) {
            final GmLink gm = getModel();
            final MObject relatedEl = gm.getRelatedElement();
        
            IModelioNavigationService service = gm.getDiagram().getModelManager().getNavigationService();
            service.fireNavigate(relatedEl);
        
            getViewer().setSelection(new StructuredSelection(this));
        } else if (RequestConstants.REQ_OPEN.equals(req.getType())) {
            final GmLink gm = getModel();
            final MObject relatedEl = gm.getRelatedElement();
        
            IActivationService service = gm.getDiagram().getModelManager().getActivationService();
            service.activateMObject(relatedEl);
        } else if (RequestConstants.REQ_DIRECT_EDIT.equals(req.getType())) {
            performDirectEditRequest(req);
        }
        super.performRequest(req);
        
    }

    /**
     * Perform a direct edition request.
     * <p>
     * The request may be a {@link LocationRequest} with a mouse location or a simple {@link Request} .
     * @param req a {@link RequestConstants#REQ_DIRECT_EDIT} request
     */
    @objid ("0118d8f7-2862-47f9-b133-d5183f67410b")
    protected void performDirectEditRequest(final Request req) {
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

    @objid ("801e5bfa-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final String propName = evt.getPropertyName();
        
        if (propName.equals(IGmObject.PROPERTY_LAYOUTDATA)) {
            // Link layout (bendpoints) update
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

    @objid ("801e5bfe-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void addChildVisual(EditPart childEditPart, int index) {
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

    @objid ("801e5c05-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void createEditPolicies() {
        // Do not show the smart link creation handle on links.
        installEditPolicy(UserChoiceCreateLinkEditPolicy.class, new UserChoiceCreateLinkEditPolicy(new PaletteActionProvider(this, PaletteActionProvider.IS_LINK_TOOL), false));
        
        // to create links on links
        installEditPolicy(EditPolicy.NODE_ROLE, new DefaultCreateLinkEditPolicy(true));
        
        // To delete links
        installEditPolicy(EditPolicy.CONNECTION_ROLE, new DefaultDeleteLinkEditPolicy());
        
        // to layout extension labels
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new GmLinkLayoutEditPolicy());
        
        // to allow renaming
        installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new DelegatingDirectEditionEditPolicy());
        
        // to allow using this link as link target to create a rake
        installEditPolicy("rake", new CreateRakeLinkEditPolicy());
        installEditPolicy(RakeRefreshEditPolicy.ROLE, new RakeRefreshEditPolicy());
        
        // setup routing mode dependent edit policies
        if (getRoutingMode().routingStyle != null) {
            updateRouterDependentEditPolicies(getRoutingMode());
        }
        
        installEditPolicy(LinkedNodeRequestConstants.REQ_LINKEDNODE_START, new LinkedNodeStartCreationEditPolicy());
        installEditPolicy(CreateMultiPointRequest.REQ_MULTIPOINT_FIRST, new ConstraintLinkEditPolicy(false));
        
        // Additional policies that request links on the link to update their feedback then layout.
        installEditPolicy(LayoutConnectionConnectionsEditPolicy.ROLE, new LayoutConnectionConnectionsEditPolicy(this));
        installEditPolicy(LayoutNodeConnectionsEditPolicy.ROLE, new LayoutNodeConnectionsEditPolicy(this));
        
        // Edit policy that refreshes the graphic model from model changes.
        // Deletes the graphic model when the model element is deleted.
        installEditPolicy(DefaultRefreshFromModelEditPolicy.ROLE, new DefaultRefreshFromModelEditPolicy());
        
    }

    @objid ("801e5c08-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected IFigure createFigure() {
        final IFigure connection = new RoundedLinkFigure();
        
        refreshFromStyle(connection, getModelStyle());
        return connection;
    }

    /**
     * Convenience method to get the {@link GmLink} model.
     * @return the link model.
     */
    @objid ("801e5c14-1dec-11e2-8cad-001ec947c8cc")
    protected final GmLink getLinkModel() {
        return getModel();
    }

    @objid ("801e5c19-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected List<IGmLink> getModelSourceConnections() {
        return getModel().getStartingLinks();
    }

    /**
     * @return the model style.
     */
    @objid ("801e5c1f-1dec-11e2-8cad-001ec947c8cc")
    protected IStyle getModelStyle() {
        return getModel().getDisplayedStyle();
    }

    @objid ("801e5c24-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected List<IGmLink> getModelTargetConnections() {
        return getModel().getEndingLinks();
    }

    /**
     * Get the current connection routing mode.
     * @return the connection routing mode.
     */
    @objid ("801e5c2a-1dec-11e2-8cad-001ec947c8cc")
    protected final RoutingMode getRoutingMode() {
        return this.currentRoutingMode;
    }

    /**
     * Refresh source and target decoration line color, width and pattern from the style
     * @param connection The figure to update, should be {@link #getFigure()}.
     * @param style The style to update from, usually {@link #getModelStyle()}
     */
    @objid ("801e5c2f-1dec-11e2-8cad-001ec947c8cc")
    protected void refreshDecorationsPenOptionsFromStyle(LinkFigure connection, IStyle style) {
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

    /**
     * Refresh the figure from the given style. This implementation updates pen and brush properties if applicable. StyleKey are looked up by MetaKey.
     * <p>
     * Often called in {@link #createFigure()} and after a style change.
     * @param aFigure The figure to update, should be {@link #getFigure()}.
     * @param style The style to update from, usually {@link #getModelStyle()}
     */
    @objid ("801e5c34-1dec-11e2-8cad-001ec947c8cc")
    protected void refreshFromStyle(IFigure aFigure, IStyle style) {
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
        
    }

    @objid ("801e5c3b-1dec-11e2-8cad-001ec947c8cc")
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

    /**
     * This method is redefined to fix the constraint saving in the case where the child figure does not directly belong to the edit part figure.
     * @see org.eclipse.gef.editparts.AbstractEditPart#reorderChild(EditPart, int)
     */
    @objid ("801e5c3e-1dec-11e2-8cad-001ec947c8cc")
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

    /**
     * Change the source or destination of the link given by the request.
     * <p>
     * Unmask the element if it is not displayed in the diagram. Updates the model, the graphic model and the view.
     * @param newEndElement the new source/destination
     * @param request The reconnect request.
     */
    @objid ("801e5c48-1dec-11e2-8cad-001ec947c8cc")
    protected final void swapEnd(MObject newEndElement, ReconnectRequest request) {
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

    @objid ("8020be57-1dec-11e2-8cad-001ec947c8cc")
    private boolean containsAbsolutePoint(final GraphicalEditPart editPart, final Point aPoint) {
        final IFigure fig = editPart.getFigure();
        final Point p = aPoint.getCopy();
        fig.translateToRelative(p);
        return fig.containsPoint(p);
    }

    @objid ("8020be67-1dec-11e2-8cad-001ec947c8cc")
    private void refreshRouterFromStyle(final Connection connectionFigure, final IStyle style, final GmLink gmLink) {
        // Update the connection router if changed from the style.
        final StyleKey routerStyleKey = gmLink.getStyleKey(MetaKey.CONNECTIONROUTER);
        if (routerStyleKey != null) {
            final ConnectionRouterId styleRouter = style.getProperty(routerStyleKey);
            final ConnectionRouterId oldRouter = gmLink.getPath().getRouterKind();
            if (styleRouter != oldRouter) {
                final GmPath newPath = new GmPath(gmLink.getPath());
                newPath.setRouterKind(styleRouter);
        
                IConnectionHelperFactory helperFactory = ConnectionPolicyUtils.getRoutingServices(this).getConnectionHelperFactory();
        
                IConnectionHelper oldHelper = helperFactory.createFromSerializedData(
                        oldRouter,
                        this,
                        connectionFigure);
        
                IConnectionHelper newHelper = helperFactory.convert(
                        oldHelper,
                        styleRouter,
                        connectionFigure);
        
                newPath.setPathData(newHelper.getModelPathData());
        
                if (newHelper.getRoutingMode() != ConnectionRouterId.ORTHOGONAL) {
                    newPath.setSourceRake(null);
                    newPath.setTargetRake(null);
                }
        
                gmLink.setLayoutData(newPath);
            }
        }
        
    }

    @objid ("8020be71-1dec-11e2-8cad-001ec947c8cc")
    private void unmaskEndElement(final MObject newEndElement, final ReconnectRequest request) {
        // The searched end was not found in the diagram (either there is no
        // Gm for it, or the Gm is "not visible" in that it doesn"t have an
        // EditPart).
        // => We will try to unmasked the searched end at roughly the same
        // place as the previous end.
        ModelElementDropRequest dropRequest = new ModelElementDropRequest();
        dropRequest.setDroppedElements(new MObject[] { newEndElement });
        
        getFigure().getUpdateManager().performUpdate();
        
        Point dropPoint;
        if (request.getType().equals(RequestConstants.REQ_RECONNECT_SOURCE)) {
            final GraphicalEditPart connSourceEp = (GraphicalEditPart) getSource();
            if (connSourceEp == null) {
                dropPoint = getConnectionFigure().getSourceAnchor().getReferencePoint();
            } else {
                IFigure sourceFigure = connSourceEp.getFigure();
                dropPoint = sourceFigure.getBounds().getLocation().getTranslated(20, 20);
                sourceFigure.translateToAbsolute(dropPoint);
            }
        } else {
            final GraphicalEditPart connTargetEp = (GraphicalEditPart) getTarget();
            if (connTargetEp == null) {
                dropPoint = getConnectionFigure().getTargetAnchor().getReferencePoint();
            } else {
                IFigure targetFigure = connTargetEp.getFigure();
                dropPoint = targetFigure.getBounds().getLocation().getTranslated(20, 20);
                targetFigure.translateToAbsolute(dropPoint);
            }
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

    /**
     * Add an edit policy to edit bend points if the router handles bend point editing.
     * @param mode the new routing mode
     */
    @objid ("8020be79-1dec-11e2-8cad-001ec947c8cc")
    protected void updateRouterDependentEditPolicies(final RoutingMode mode) {
        IRouterDependentEditPolicyFactory bendPointEditPoliciesFactory = ConnectionPolicyUtils.getRoutingServices(this).getEditPoliciesFactory();
        
        // Note : installEditPolicy(...) removes cleanly the existing policy if any
        installEditPolicy(EditPolicy.CONNECTION_BENDPOINTS_ROLE, bendPointEditPoliciesFactory.createBendPointsPolicy(mode));
        installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE, bendPointEditPoliciesFactory.createEndPointsPolicy(mode));
        
    }

    /**
     * Update the connection router, the edit policies and the drag tracker from the model routing style.
     * @param cnx The connection figure
     */
    @objid ("8020be7e-1dec-11e2-8cad-001ec947c8cc")
    private void updateConnectionRoute(final Connection cnx) {
        // Refresh anchors
        refreshSourceAnchor();
        refreshTargetAnchor();
        
        final IGmLink gmLink = getLinkModel();
        final RoutingMode newRoutingMode = new RoutingMode(gmLink.getPath());
        final RoutingMode oldRoutingMode = getRoutingMode();
        final ConnectionRoutingServices routingServices = ConnectionPolicyUtils.getRoutingServices(this);
        
        // Change connection router if the rake mode changes or there is no rake and the style changes
        if (oldRoutingMode.rake != newRoutingMode.rake || !newRoutingMode.rake && oldRoutingMode.routingStyle != newRoutingMode.routingStyle) {
            // Set the connection router
            boolean isAutoRouter = false;
            if (newRoutingMode.rake) {
                cnx.setConnectionRouter(LinkEditPart.rakeRouter);
            } else {
                cnx.setConnectionRouter(routingServices.getDisplayRouter(newRoutingMode.routingStyle));
                isAutoRouter = routingServices.getEditionRouter(newRoutingMode.routingStyle) instanceof AutoOrthogonalRouter;
            }
        
            // Set the new constraint
            IConnectionHelper helper = routingServices.getConnectionHelperFactory().createFromSerializedData(newRoutingMode.routingStyle, this, cnx);
            cnx.setRoutingConstraint(helper.getRoutingConstraint());
        
            // Update edit policies
            updateRouterDependentEditPolicies(newRoutingMode);
        
            this.currentRoutingMode = newRoutingMode;
        
            if (isAutoRouter && oldRoutingMode.routingStyle != null) {
                new AutoOrthogonalRouterSynchronizeConstraintCommand(this).execute();
            }
        } else {
            IConnectionHelper helper = routingServices.getConnectionHelperFactory().createFromSerializedData(newRoutingMode.routingStyle, this, cnx);
            cnx.setRoutingConstraint(helper.getRoutingConstraint());
        }
        
    }

    @objid ("33fb250d-fc22-4302-92f3-61cfd3b86e44")
    @Override
    public GmLink getModel() {
        return (GmLink) super.getModel();
    }

    /**
     * Redefined because 'parent' is not reliable.
     * <p>
     * Instead look for the real parent from the model.
     */
    @objid ("83b1e7c2-2f64-42da-bbe9-391192256a81")
    @Override
    public void setParent(EditPart parent) {
        if (parent == null) {
            super.setParent(parent);
        } else {
            EditPart realParent = findRealParent(parent);
            super.setParent(realParent);
        }
        
    }

    /**
     * Find the root edit part this ConnectionEditPart should be owned by.
     * <p>
     * For most link it is anEditPart.getRoot(), except for cross diagram connections for which the parent must be the root edit part of the connection diagram.
     * @param anEditPart the source or target of the connection
     * @return the root edit part this ConnectionEditPart should be owned by.
     */
    @objid ("b16665dc-fd0c-44af-aa4b-f5a4cbec38f8")
    protected EditPart findRealParent(EditPart anEditPart) {
        IGmDiagram diagram = getModel().getDiagram();
        
        EditPart diagramEp = (EditPart) anEditPart.getViewer().getEditPartRegistry().get(diagram);
        if (diagramEp != null) {
            return diagramEp.getRoot();
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Redefined to set the collections of all diagram connections on the {@link RoundedLinkFigure}. This collection is used to find intersections to draw bridges.
     * @since 3.7
     * @see RoundedLinkFigure#setAllDiagramConnections(Collection)
     */
    @objid ("7e925375-b007-41de-b7b4-867cfbe9b008")
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

    @objid ("2eec3c63-a105-4a7b-87cc-aa520413a705")
    protected boolean isModelUserEditable() {
        IGmObject m = getModel();
        return m.isUserEditable();
    }

    @objid ("86e5184a-df60-461a-9c33-ee8d5eff69e7")
    public List<EditPolicy> getInstalledPolicies() {
        List<EditPolicy> policies = new ArrayList<>();
        
        EditPolicyIterator it = getEditPolicyIterator();
        
        while (it.hasNext()) {
            policies.add(it.next());
        }
        return policies;
    }

}
