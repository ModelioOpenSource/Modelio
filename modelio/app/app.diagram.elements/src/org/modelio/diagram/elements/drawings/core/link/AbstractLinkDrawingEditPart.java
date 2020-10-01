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

package org.modelio.diagram.elements.drawings.core.link;

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
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gef.requests.LocationRequest;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.graphics.Color;
import org.modelio.diagram.elements.common.portcontainer.SatelliteDragTrackerProvider;
import org.modelio.diagram.elements.core.figures.IPenOptionsSupport;
import org.modelio.diagram.elements.core.figures.LinkFigure;
import org.modelio.diagram.elements.core.figures.RoundedLinkFigure;
import org.modelio.diagram.elements.core.figures.anchors.LinkAnchor;
import org.modelio.diagram.elements.core.figures.routers.RakeRouter;
import org.modelio.diagram.elements.core.link.ConnectionRouterRegistry;
import org.modelio.diagram.elements.core.link.GmLinkLayoutEditPolicy;
import org.modelio.diagram.elements.core.link.GmPath;
import org.modelio.diagram.elements.core.link.IAnchorModelProvider;
import org.modelio.diagram.elements.core.link.LinkEditPart;
import org.modelio.diagram.elements.core.link.SelectConnectionEditPartTracker;
import org.modelio.diagram.elements.core.link.extensions.GmFractionalConnectionLocator;
import org.modelio.diagram.elements.core.link.extensions.IGmLocator;
import org.modelio.diagram.elements.core.link.extensions.LocatorFactory;
import org.modelio.diagram.elements.core.link.ortho.OrthoBendpointEditPolicy;
import org.modelio.diagram.elements.core.link.path.ConnectionHelperFactory;
import org.modelio.diagram.elements.core.link.path.ConnectionPolicyUtils;
import org.modelio.diagram.elements.core.link.path.IConnectionHelper;
import org.modelio.diagram.elements.core.link.path.OrthoConnectionHelper;
import org.modelio.diagram.elements.core.link.rake.RakeLinkEditPolicy;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmLinkRake;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.model.IGmPath;
import org.modelio.diagram.elements.core.policies.DefaultBendpointEditPolicy;
import org.modelio.diagram.elements.core.policies.DefaultConnectionEndpointEditPolicy;
import org.modelio.diagram.elements.core.policies.DefaultDeleteLinkEditPolicy;
import org.modelio.diagram.elements.core.policies.DelegatingDirectEditionEditPolicy;
import org.modelio.diagram.elements.core.requests.NavigationRequest;
import org.modelio.diagram.elements.drawings.core.IGmDrawing;
import org.modelio.diagram.elements.drawings.core.IGmDrawingLayer;
import org.modelio.diagram.elements.drawings.core.IGmDrawingLink;
import org.modelio.diagram.elements.drawings.core.node.NodeDrawingEditPart;
import org.modelio.diagram.elements.drawings.layer.DrawingLayerEditPart;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId;
import org.modelio.diagram.styles.core.StyleKey.LinePattern;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.platform.core.navigate.IModelioNavigationService;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Basic edit part for {@link IGmDrawingLink}. A {@link LinkEditPart}: <br/>
 * <li>extends AbsbractConnectionEditPart => to have a link behavior</li><br/>
 * <li>implements NodeEditPart => to be usable as a source or a target of another link</li><br/>
 * <li>implements PropertyChangeListener => for update and refreshing from model or style change</li><br/>
 * <p>
 * Contrary to ordinary connection links, the drawing link figure is owned by the source node layer.
 */
@objid ("40645cc4-1d54-42eb-9c76-92a7a30a6218")
public class AbstractLinkDrawingEditPart extends AbstractConnectionEditPart implements PropertyChangeListener, IAnchorModelProvider {
    @objid ("ba1cc3b3-0fbc-4bc4-91dd-5b8f39c26698")
    private RoutingMode currentRoutingMode = new RoutingMode();

    /**
     * Rake router for all links.
     */
    @objid ("13aa7ef3-df25-47c2-a394-0ec9cc6deca6")
    protected static final RakeRouter rakeRouter = new RakeRouter();

    /**
     * C'tor.
     */
    @objid ("36009273-5cd8-4255-a116-76cff0c0faf1")
    public AbstractLinkDrawingEditPart() {
        super();
    }

    @objid ("582a4e88-f2c9-4016-8b91-ef8d5f2f1876")
    @Override
    public void activate() {
        super.activate();
        
        final IGmDrawingLink gmLink = getModel();
        gmLink.addPropertyChangeListener(this);
    }

    @objid ("2ea24a43-dbe4-4fb3-a57b-38420bf20083")
    @Override
    public Object createAnchorModel(final ConnectionAnchor anchor) {
        // TODO Temporary fake implementation: try to return the anchor of 'this' origin anchoring point
        return null;
    }

    @objid ("7d0e21c6-d3ba-465f-b484-a869d08be8ce")
    @Override
    public void deactivate() {
        super.deactivate();
        getModel().removePropertyChangeListener(this);
    }

    /**
     * Returns an object which is an instance of the given class associated with this object. Returns <code>null</code> if no such object can be found.
     * <p>
     * Extends {@link AbstractConnectionEditPart#getAdapter(Class)} to support {@link IGmObject}, {@link GmModel} and their subclasses.
     * @see IAdaptable#getAdapter(Class)
     * 
     * @param adapter the adapter class to look up
     * @return a object castable to the given class, or <code>null</code> if this object does not have an adapter for the given class
     */
    @objid ("722c797e-5ce4-42b4-94c9-71f1e5bb4d1e")
    @Override
    public Object getAdapter(Class adapter) {
        final IGmDrawingLink model = getModel();
        
        // Support IGmObject and its subclasses
        if (adapter.isInstance(model)) {
            return model;
        }
        
        // Supports MRef and MObject
        if (MRef.class.isAssignableFrom(adapter) ||
                MObject.class.isAssignableFrom(adapter)) {
        
            MRef ref = model.getHyperLink();
            if (ref != null) {
                if (adapter.isInstance(ref)) {
                    return ref;
                }
        
                MObject obj = model.getDiagram().getModelManager().getModelServices().findByRef(ref);
                if (obj != null && adapter.isInstance(obj)) {
                    return obj;
                }
            }
        }
        return super.getAdapter(adapter);
    }

    @objid ("9cf7c449-3d13-46a1-9157-8a8881d5f7bb")
    @Override
    public DragTracker getDragTracker(Request req) {
        return new SelectConnectionEditPartTracker(this);
    }

    @objid ("32224549-79fe-4e9c-bbc4-db15b6ddffee")
    @Override
    public IGmDrawingLink getModel() {
        return (IGmDrawingLink) super.getModel();
    }

    @objid ("cbf1d9be-8c95-45a1-a9f1-61f096c1dbd6")
    @Override
    public List<Object> getModelChildren() {
        final ArrayList<Object> ret = new ArrayList<>(8);
        final IGmDrawingLink link = getModel();
        
        ret.addAll(link.getVisibleExtensions());
        ret.addAll(super.getModelChildren());
        return ret;
    }

    @objid ("7b22a3fe-1f75-47ae-a6c4-5debbdf386a1")
    @Override
    public ConnectionAnchor getSourceConnectionAnchor(final ConnectionEditPart connection) {
        return new LinkAnchor(getConnectionFigure(), new GmFractionalConnectionLocator(0.5, 0, 0));
    }

    @objid ("42e66467-a631-47cd-8aff-9fae31c06cb2")
    @Override
    public ConnectionAnchor getSourceConnectionAnchor(final Request request) {
        return new LinkAnchor(getConnectionFigure(), new GmFractionalConnectionLocator(0.5, 0, 0));
    }

    @objid ("1b02a4a6-9d87-46b7-9cec-d501cbd582ed")
    @Override
    public ConnectionAnchor getTargetConnectionAnchor(final ConnectionEditPart connection) {
        return new LinkAnchor(getConnectionFigure(), new GmFractionalConnectionLocator(0.5, 0, 0));
    }

    @objid ("6f1179d9-7982-4ec5-82de-9fabad25243e")
    @Override
    public ConnectionAnchor getTargetConnectionAnchor(final Request request) {
        return new LinkAnchor(getConnectionFigure(), new GmFractionalConnectionLocator(0.5, 0, 0));
    }

    /**
     * Overridden to add a specific behavior on {@link RequestConstants#REQ_DIRECT_EDIT DIRECT_EDIT} request: the request if forwarded to all children edit parts until one understand it, and then said child edit part is asked to perform the request.
     */
    @objid ("25b0259a-cfc8-4d7f-b9b8-081d9250895d")
    @Override
    public void performRequest(final Request req) {
        if (RequestConstants.REQ_DIRECT_EDIT.equals(req.getType())) {
            if (req instanceof LocationRequest) {
                // Give the request to the child where the request is located
                final Point reqLocation = ((LocationRequest) req).getLocation();
        
                for (GraphicalEditPart childEditPart : (List<GraphicalEditPart>) getChildren()) {
                    if (childEditPart.understandsRequest(req)
                            && containsAbsolutePoint(childEditPart, reqLocation)) {
                        childEditPart.performRequest(req);
                        return;
                    }
                }
            } else {
                // Give the request to the first child that understand it
                for (GraphicalEditPart childEditPart : (List<GraphicalEditPart>) getChildren()) {
                    if (childEditPart.understandsRequest(req)) {
                        childEditPart.performRequest(req);
                        return;
                    }
                }
            }
        } else if (NavigationRequest.TYPE == req.getType()) {
            final IGmDrawing gm = getModel();
            final MRef ref = gm.getHyperLink();
            if (ref != null) {
                final MObject relatedEl = gm.getDiagram().getModelManager().resolveRef(ref);
        
                if (relatedEl != null) {
                    IModelioNavigationService service = gm.getDiagram().getModelManager().getNavigationService();
                    service.fireNavigate(relatedEl);
        
                    getViewer().setSelection(new StructuredSelection(this));
                }
            }
        }
        super.performRequest(req);
    }

    @objid ("5f6d9a7e-22f8-4b8f-9025-ce740016e870")
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final String propName = evt.getPropertyName();
        
        if (propName.equals(IGmObject.PROPERTY_LAYOUTDATA)) {
            // Link layout (bendpoints) update
            refreshRakeModeOnSource();
            refreshRakeModeOnTarget();
        
            refreshSourceAnchor();
            refreshTargetAnchor();
            refreshVisuals();
        
        } else if (propName.equals(IGmObject.PROPERTY_CHILDREN)) {
            refreshChildren();
        } else if (propName.equals(IGmObject.PROPERTY_STYLE)) {
            refreshFromStyle(getFigure(), (IStyle) evt.getNewValue());
            // Since many extensions' visibility is driven by a style key value, force refresh of children
            refreshChildren();
        } else if (propName.equals(IGmDrawingLink.PROP_SOURCE_GM)) {
            refreshRakeModeOnSource();
        } else if (propName.equals(IGmDrawingLink.PROP_TARGET_GM)) {
            refreshRakeModeOnTarget();
        } else if (propName.equals(IGmObject.PROPERTY_LINK_SOURCE)) {
            // Links were added/removed from the link
            refreshSourceConnections();
        } else if (propName.equals(IGmObject.PROPERTY_LINK_TARGET)) {
            // Links were added/removed from the link
            refreshTargetConnections();
        }
    }

    @objid ("d76dc255-2f03-4906-9e01-4f0de32bbcf4")
    @Override
    public void setSource(EditPart editPart) {
        // Override to force removal of the figure from the old layer
        // and add it to the new layer
        final EditPart oldSource = getSource();
        if (oldSource == editPart) {
            return;
        }
        
        setParent(null);
        super.setSource(editPart);
    }

    /**
     * {@inheritDoc} Add the figure to the drawing layer instead of the connection layer.
     * <p>
     * Set the collections of all diagram connections on the {@link RoundedLinkFigure}. This collection is used to find intersections to draw bridges.
     * @since 3.7
     * @see RoundedLinkFigure#setAllDiagramConnections(Collection)
     */
    @objid ("048dff5f-d978-4460-b83d-45e5903c70ba")
    @Override
    protected void activateFigure() {
        IGmDrawingLayer gmlayer = getModel().getFrom().getLayer();
        DrawingLayerEditPart layerPart = (DrawingLayerEditPart) getViewer().getEditPartRegistry().get(gmlayer);
        IFigure fig = getFigure();
        
        layerPart.addConnection(fig);
        
        if (fig instanceof RoundedLinkFigure) {
            // Set the collections of all diagram connections.
            // This collection is used to find intersections to draw bridges.
            Collection<Connection> allDiagramConnections = ConnectionPolicyUtils.getAllDiagramConnectionsCollector(this);
            ((RoundedLinkFigure) fig).setAllDiagramConnections(allDiagramConnections);
        }
        
        // don't call super : it would add the connection to the global connection layer
    }

    @objid ("21703216-94dd-40be-a666-24219db6af1e")
    @Override
    protected void addChildVisual(EditPart childEditPart, int index) {
        final IFigure childFigure = ((GraphicalEditPart) childEditPart).getFigure();
        
        final PolylineConnection connection = (PolylineConnection) getFigure();
        
        ((NodeDrawingEditPart) childEditPart).setDragTrackerProvider(new SatelliteDragTrackerProvider(childEditPart));
        
        connection.add(childFigure, index);
        
        final IGmDrawingLink gmlink = getModel();
        final IGmObject childModel = (IGmObject) childEditPart.getModel();
        final Locator constraint = LocatorFactory.getInstance()
                .getLocator(connection,
                        gmlink.getLayoutContraint(childModel));
        
        this.figure.setConstraint(childFigure, constraint);
    }

    @objid ("038bfd60-64cc-410b-87e3-7ea3fd939510")
    @Override
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE, new DefaultConnectionEndpointEditPolicy());
        // installEditPolicy(EditPolicy.NODE_ROLE, new DefaultCreateLinkEditPolicy(true));
        installEditPolicy(EditPolicy.CONNECTION_ROLE, new DefaultDeleteLinkEditPolicy());
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new GmLinkLayoutEditPolicy());
        installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new DelegatingDirectEditionEditPolicy());
        // installEditPolicy("rake", new CreateRakeLinkEditPolicy());
        
        if (getRoutingMode().routingStyle != null) {
            updateBendPointEditPolicies(getRoutingMode());
        }
        
        /*
         * installEditPolicy(LinkedNodeRequestConstants.REQ_LINKEDNODE_START, new LinkedNodeStartCreationEditPolicy()); installEditPolicy(CreateMultiPointRequest.REQ_MULTIPOINT_FIRST, new ConstraintLinkEditPolicy(false));
         */
    }

    @objid ("6256add2-ba8c-4209-bffb-ab67e6cd5bb4")
    @Override
    protected IFigure createFigure() {
        final IFigure connection = new RoundedLinkFigure();
        
        refreshFromStyle(connection, getModelStyle());
        return connection;
    }

    /**
     * Deactivates the Figure representing this, by removing it from the layer connection layer, and resetting the source and target connections to <code>null</code>.
     */
    @objid ("dd00b59f-845e-459d-ac0f-e38c9f393c1a")
    @Override
    protected void deactivateFigure() {
        getFigure().getParent().remove(getFigure());
        getConnectionFigure().setSourceAnchor(null);
        getConnectionFigure().setTargetAnchor(null);
        // don't call super : it would look for the connection in the global connection layer
    }

    /**
     * Get the connection router registry.
     * 
     * @return the connection router registry.
     */
    @objid ("fefb94b4-3d9f-4593-ad40-e49dfa0fcdd4")
    protected ConnectionRouterRegistry getConnectionRouterRegistry() {
        return (ConnectionRouterRegistry) getViewer().getProperty(ConnectionRouterRegistry.ID);
    }

    @objid ("ef303fb9-d3bf-41aa-b578-56fca661349b")
    @Override
    protected List<IGmDrawingLink> getModelSourceConnections() {
        return getModel().getStartingDrawingLinks();
    }

    /**
     * @return the model style.
     */
    @objid ("81138d86-7993-4d75-b4e0-ce3899626820")
    protected IStyle getModelStyle() {
        return getModel().getDisplayedStyle();
    }

    @objid ("1fc9ac1b-543f-48b1-8b2a-3036fefb9ef4")
    @Override
    protected List<IGmDrawingLink> getModelTargetConnections() {
        return getModel().getEndingDrawingLinks();
    }

    /**
     * Get the current connection routing mode.
     * 
     * @return the connection routing mode.
     */
    @objid ("05c5f312-acfa-4d3b-8ca3-9766f29a62f8")
    protected RoutingMode getRoutingMode() {
        return this.currentRoutingMode;
    }

    /**
     * Refresh source and target decoration line color, width and pattern from the style
     * 
     * @param connection The figure to update, should be {@link #getFigure()}.
     * @param style The style to update from, usually {@link #getModelStyle()}
     */
    @objid ("40be6efb-f4bf-4baa-a26b-9e664206bff4")
    protected void refreshDecorationsPenOptionsFromStyle(LinkFigure connection, IStyle style) {
        IGmDrawingLink model = getModel();
        
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
     * 
     * @param aFigure The figure to update, should be {@link #getFigure()}.
     * @param style The style to update from, usually {@link #getModelStyle()}
     */
    @objid ("1cf2cae6-437c-4548-be8b-2e63256b6652")
    protected void refreshFromStyle(IFigure aFigure, IStyle style) {
        final IGmDrawingLink gmModel = getModel();
        
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

    @objid ("635573f5-18e0-4785-9745-3d07b18470f5")
    @Override
    protected void refreshVisuals() {
        super.refreshVisuals();
        
        final IGmDrawingLink gmLink = getModel();
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
    @objid ("6e090c7c-bd4e-4e31-bb52-c3a2756b94e9")
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

    @objid ("cfbe822f-b878-497e-bc30-1d3c7fecc7d9")
    private static boolean containsAbsolutePoint(final GraphicalEditPart editPart, final Point aPoint) {
        final IFigure fig = editPart.getFigure();
        final Point p = aPoint.getCopy();
        fig.translateToRelative(p);
        return fig.containsPoint(p);
    }

    @objid ("63e9b486-5abb-4da6-82d9-9325c3b6f07e")
    private void refreshRakeModeOnSource() {
        final IGmDrawingLink gm = getModel();
        final IGmPath oldPath = gm.getPath();
        final IGmLinkRake sourceRake = oldPath.getSourceRake();
        if (sourceRake != null && sourceRake.getSharedAnchor() != oldPath.getSourceAnchor()) {
        
            OrthoConnectionHelper connectionPath = new OrthoConnectionHelper(getConnectionFigure(), this);
            Object pathData = connectionPath.getModelPathData();
        
            GmPath path = new GmPath(oldPath);
            path.setRouterKind(ConnectionRouterId.ORTHOGONAL);
            path.setSourceRake(null);
            path.setTargetRake(null);
            path.setPathData(pathData);
            gm.setLayoutData(path);
        }
    }

    @objid ("e16e91dc-2865-4bb8-897f-59fcbf939f7f")
    private void refreshRakeModeOnTarget() {
        final IGmDrawingLink gm = getModel();
        final IGmPath oldPath = gm.getPath();
        final IGmLinkRake targetRake = oldPath.getTargetRake();
        if (targetRake != null && targetRake.getSharedAnchor() != oldPath.getTargetAnchor()) {
        
            OrthoConnectionHelper connectionPath = new OrthoConnectionHelper(getConnectionFigure(), this);
            Object pathData = connectionPath.getModelPathData();
        
            GmPath path = new GmPath(oldPath);
            path.setRouterKind(ConnectionRouterId.ORTHOGONAL);
            path.setSourceRake(null);
            path.setTargetRake(null);
            path.setPathData(pathData);
            gm.setLayoutData(path);
        }
    }

    @objid ("b47c49c5-8e98-44ad-9d97-b5558442c5b2")
    private void refreshRouterFromStyle(final Connection connectionFigure, final IStyle style, final IGmDrawingLink gmLink) {
        // Update the connection router if changed from the style.
        final StyleKey routerStyleKey = gmLink.getStyleKey(MetaKey.CONNECTIONROUTER);
        if (routerStyleKey != null) {
            final ConnectionRouterId styleRouter = style.getProperty(routerStyleKey);
            final ConnectionRouterId oldRouter = gmLink.getPath().getRouterKind();
            if (styleRouter != oldRouter) {
                final GmPath newPath = new GmPath(gmLink.getPath());
                newPath.setRouterKind(styleRouter);
        
                IConnectionHelper oldHelper = ConnectionHelperFactory.createFromSerializedData(oldRouter,
                        this,
                        connectionFigure);
                IConnectionHelper newHelper = ConnectionHelperFactory.convert(oldHelper,
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

    /**
     * Add an edit policy to edit bend points if the router handles bend point editing.
     */
    @objid ("e0671ac2-0836-4a2e-b43a-bc6bb89b4c64")
    private void updateBendPointEditPolicies(final RoutingMode mode) {
        removeEditPolicy(EditPolicy.CONNECTION_BENDPOINTS_ROLE);
        
        if (mode.rake) {
            installEditPolicy(EditPolicy.CONNECTION_BENDPOINTS_ROLE, new RakeLinkEditPolicy());
        } else {
            switch (mode.routingStyle) {
            case DIRECT:
                // installEditPolicy(EditPolicy.CONNECTION_BENDPOINTS_ROLE, new NonResizableEditPolicy());
                break;
            case BENDPOINT:
                installEditPolicy(EditPolicy.CONNECTION_BENDPOINTS_ROLE, new DefaultBendpointEditPolicy());
                break;
            case ORTHOGONAL:
                installEditPolicy(EditPolicy.CONNECTION_BENDPOINTS_ROLE, new OrthoBendpointEditPolicy());
                break;
            default:
                throw new IllegalStateException(getRoutingMode() + " routing mode not supported");
            }
        }
    }

    /**
     * Update the connection router, the edit policies and the drag tracker from the model routing style.
     * 
     * @param cnx The connection figure
     */
    @objid ("7143a829-3140-4d23-abbb-633f281df213")
    private void updateConnectionRoute(final Connection cnx) {
        // Refresh anchors
        refreshSourceAnchor();
        refreshTargetAnchor();
        
        final IGmDrawingLink gmLink = getModel();
        final RoutingMode newRoutingMode = new RoutingMode(gmLink.getPath());
        final RoutingMode oldRoutingMode = getRoutingMode();
        // Change connection router if the rake mode changes or there is no rake and the style changes
        if (oldRoutingMode.rake != newRoutingMode.rake ||
                (!newRoutingMode.rake && oldRoutingMode.routingStyle != newRoutingMode.routingStyle)) {
            // Set the connection router
            if (newRoutingMode.rake) {
                cnx.setConnectionRouter(AbstractLinkDrawingEditPart.rakeRouter);
            } else {
                cnx.setConnectionRouter(getConnectionRouterRegistry().get(newRoutingMode.routingStyle));
            }
        
            // Set the new constraint
            IConnectionHelper helper = ConnectionHelperFactory.createFromSerializedData(newRoutingMode.routingStyle,
                    this,
                    cnx);
            cnx.setRoutingConstraint(helper.getRoutingConstraint());
        
            // Update edit policy
            updateBendPointEditPolicies(newRoutingMode);
        
            this.currentRoutingMode = newRoutingMode;
        
        } else {
            IConnectionHelper helper = ConnectionHelperFactory.createFromSerializedData(newRoutingMode.routingStyle,
                    this,
                    cnx);
            cnx.setRoutingConstraint(helper.getRoutingConstraint());
        }
    }

    /**
     * Represents the routing mode of the link.
     * 
     * @author cmarin
     */
    @objid ("89cf34c2-4d55-4b57-80e2-c5e3c2b2dfe4")
    private static class RoutingMode {
        @objid ("fb04bd98-07de-4095-bde3-5ddd1a2d39a8")
        public boolean rake = false;

        @objid ("48db7ddc-d528-4e3a-8dcf-c678182d9089")
        public ConnectionRouterId routingStyle = null;

        @objid ("65b88af6-e951-4db0-a77a-d6467798a905")
        public RoutingMode() {
        }

        @objid ("361c8dd2-8e8f-445f-a67c-c619238592f9")
        public RoutingMode(final IGmPath path) {
            this.routingStyle = path.getRouterKind();
            this.rake = path.getSourceRake() != null || path.getTargetRake() != null;
        }

        @objid ("33cfab26-0205-46a3-ae76-50a1af0accf1")
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + (this.rake ? 1231 : 1237);
            result = prime * result + ((this.routingStyle == null) ? 0 : this.routingStyle.hashCode());
            return result;
        }

        @objid ("33ef1c0f-5163-406b-a2ef-8c23c1204025")
        @Override
        public boolean equals(final Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            RoutingMode other = (RoutingMode) obj;
            if (this.rake != other.rake) {
                return false;
            }
            if (this.routingStyle == null) {
                if (other.routingStyle != null) {
                    return false;
                }
            } else if (this.routingStyle != other.routingStyle) {
                return false;
            }
            return true;
        }

    }

}
