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

package org.modelio.diagram.elements.drawings.layer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Layer;
import org.eclipse.draw2d.XYAnchor;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.SnapToHelper;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.SnapFeedbackPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.DropRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.modelio.diagram.elements.common.abstractdiagram.SnapEditPartAdapter;
import org.modelio.diagram.elements.core.figures.anchors.PointAnchor;
import org.modelio.diagram.elements.core.figures.freeform.FreeformLayer2;
import org.modelio.diagram.elements.core.figures.freeform.FreeformLayeredPane2;
import org.modelio.diagram.elements.core.link.IAnchorModelProvider;
import org.modelio.diagram.elements.core.link.anchors.GmPointAnchor;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.node.RectangleNodeAnchorProvider;
import org.modelio.diagram.elements.drawings.core.IGmDrawingLink;
import org.modelio.diagram.elements.drawings.core.IGmNodeDrawing;
import org.modelio.diagram.elements.drawings.core.link.CreateDrawingLinkEditPolicy;
import org.modelio.diagram.elements.drawings.core.policies.DrawingContainerEditLayoutPolicy;

/**
 * Edit part for {@link GmDrawingLayer}.
 * <p>
 * The layer figure is a layered pane containing a layer for nodes and another for connections.
 */
@objid ("ab58f615-a49d-4e74-bba0-f3ce7ddffef8")
public class DrawingLayerEditPart extends AbstractGraphicalEditPart implements PropertyChangeListener, IAnchorModelProvider {
    /**
     * A temporary point to avoid allocations of hundred of Points.
     * Used only by {@link #createAnchor(Point)}
     */
    @objid ("5098a520-305a-414c-b1d0-7710cea54f34")
    private static final Point tmpAnchor = new Point();

    @objid ("0ba7ff7f-b8a9-44e3-908e-98918984a920")
    private Layer nodesLayer;

    @objid ("6313009f-ef1c-4c3f-85c2-088db04a1f7f")
    private Layer connectionsLayer;

    /**
     * Default constructor.
     */
    @objid ("5baf5d42-e5cb-473e-9eb3-b34f5b9301de")
    public DrawingLayerEditPart() {
        super();
    }

    @objid ("baf2b973-8b1d-4f92-8455-d7a4283fef2d")
    @Override
    public boolean isSelectable() {
        // This edit part is not selectable.
        return false;
    }

    @objid ("bfc2c166-2af3-4237-8f91-2bb3f5db59bc")
    @Override
    public DragTracker getDragTracker(Request request) {
        // This edit part cannot be dragged.
        return null;
    }

    @objid ("1c557933-9b56-46db-bfab-ef12cf5e6e15")
    @Override
    protected IFigure createFigure() {
        // 1) Creates the nodes layer
        this.nodesLayer = new FreeformLayer2();
        this.nodesLayer.setLayoutManager(new FreeformLayout());
        
        // the figure must be kept transparent
        this.nodesLayer.setBackgroundColor(null);
        this.nodesLayer.setOpaque(false);
        
        // 2) Create the connections layer
        this.connectionsLayer = new FreeformLayer2();
        this.connectionsLayer.setLayoutManager(new FreeformLayout());
        
        // the figure must be kept transparent
        this.connectionsLayer.setBackgroundColor(null);
        this.connectionsLayer.setOpaque(false);
        
        // 3) Aggregate the layers
        FreeformLayeredPane2 pane = new FreeformLayeredPane2();
        pane.add(this.nodesLayer);
        pane.add(this.connectionsLayer);
        return pane;
    }

    /**
     * Returns an object which is an instance of the given class associated with this object.
     * <p>
     * Returns <code>null</code> if no such object can be found.
     * <p>
     * Extends {@link AbstractGraphicalEditPart#getAdapter(Class)} to support:
     * <ul>
     * <li>{@link IGmObject},{@link GmModel} and their subclasses.
     * <li>{@link SnapToHelper} : snaps to :
     * <ul>
     * <li>all free drawing bounds ,
     * <li>to all other moveable diagram nodes bounds with an external margin
     * so that drawing make a frame around them.
     * </ul>
     * </ul>
     * @see IAdaptable#getAdapter(Class)
     * @param adapter the adapter class to look up
     * @return a object castable to the given class, or <code>null</code> if this object does not have an adapter for
     * the given class
     */
    @objid ("712eabe6-8158-47aa-b3a8-23733b076e8a")
    @Override
    public Object getAdapter(Class adapter) {
        if (adapter == SnapToHelper.class) {
            SnapEditPartAdapter snapBuilder = new SnapEditPartAdapter(this);
            // Snap to all nodes of the diagram.
            // For this we look for all node containers.
            // We identify nodes container by whether or not they have a snap adapter.
            for (Object object : getViewer().getEditPartRegistry().values()) {
                GraphicalEditPart ep = (GraphicalEditPart) object;
                if (ep == this) {
                    // ignore ourself
                } else if (ep instanceof DrawingLayerEditPart) {
                    // snap to drawing bounds
                    snapBuilder.addContainer(ep, 0);
                } else if (ep.getAdapter(SnapToHelper.class) != null) {
                    // Snap to graphic nodes bounds with an external margin
                    // so that drawing make a frame around them.
                    snapBuilder.addContainer(ep, 5);
        
                    // What about a frame inside?
                    // snapBuilder.addContainer(ep, -5);
                }
            }
        
            return snapBuilder.getSnapToHelper();
        } else {
            final GmDrawingLayer model = getModel();
        
            // Support IGmObject, GmModel and its subclasses
            if (adapter.isInstance(model)) {
                return model;
            } else {
                return super.getAdapter(adapter);
            }
        }
    }

    @objid ("64211158-3d86-4edd-9904-742bdf98840e")
    @Override
    public void activate() {
        super.activate();
        getModel().addPropertyChangeListener(this);
    }

    @objid ("c89b9b62-2b32-4782-ad33-aa5c3be781e3")
    @Override
    public void deactivate() {
        super.deactivate();
        getModel().removePropertyChangeListener(this);
    }

    @objid ("0f659c8f-1f71-404b-8a6b-405d8f2bdf5b")
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
        case IGmObject.PROPERTY_LINK_SOURCE:
            refreshSourceConnections();
            break;
        case IGmObject.PROPERTY_LINK_TARGET:
            refreshTargetConnections();
            break;
        case IGmObject.PROPERTY_CHILDREN:
            refreshChildren();
            break;
        case IGmObject.PROPERTY_LAYOUTDATA:
            refreshVisuals();
            break;
        default:
            break;
        }
    }

    @objid ("553f28ac-7866-4277-acf5-c0512ef9cec7")
    @Override
    protected List<IGmNodeDrawing> getModelChildren() {
        GmDrawingLayer gmLayer = getModel();
        return gmLayer.getNodes();
    }

    @objid ("4bcede6d-48e1-40e0-b526-3622265146f5")
    @Override
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new DrawingContainerEditLayoutPolicy());
        installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new CreateDrawingLinkEditPolicy(true));
        
        // Snap to Geometry feedback
        installEditPolicy("Snap Feedback", new SnapFeedbackPolicy()); //$NON-NLS-1$
    }

    @objid ("cceeaaab-235c-4937-9602-2307787d9b61")
    @Override
    public IFigure getContentPane() {
        // Ensure the figure exists.
        getFigure();
        return this.nodesLayer;
    }

    @objid ("cdb63249-d546-4cb8-aa40-bef03ddf0269")
    @Override
    protected List<IGmDrawingLink> getModelSourceConnections() {
        return getModel().getStartingDrawingLinks();
    }

    @objid ("2e51a1e1-b5b7-4a65-aa35-243b0d4bcc54")
    @Override
    protected List<IGmDrawingLink> getModelTargetConnections() {
        return getModel().getEndingDrawingLinks();
    }

    @objid ("9efab6a3-4fdd-4fee-8b94-a7056ae2a70c")
    @Override
    public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection) {
        final ConnectionAnchor ret = RectangleNodeAnchorProvider.get().getSourceConnectionAnchor(this, connection);
        if (ret instanceof XYAnchor) {
            return ret;
        }
        return new XYAnchor(new Point(10, 10));
    }

    @objid ("85247630-8158-4403-8834-e739bcd8fb27")
    @Override
    public ConnectionAnchor getTargetConnectionAnchor(final ConnectionEditPart connectionPart) {
        final ConnectionAnchor ret = RectangleNodeAnchorProvider.get().getTargetConnectionAnchor(this, connectionPart);
        if (ret instanceof XYAnchor) {
            return ret;
        }
        return new XYAnchor(new Point(100, 10));
    }

    @objid ("61f3558c-8e54-4293-92c6-03592860ab40")
    @Override
    public ConnectionAnchor getSourceConnectionAnchor(Request request) {
        if (request instanceof CreateConnectionRequest) {
            Object object = ((CreateConnectionRequest) request).getExtendedData().get("srcAnchor");
            if (object instanceof ConnectionAnchor) {
                return (ConnectionAnchor) object;
            }
        }
        
        if (request instanceof ReconnectRequest) {
            final Point p = ((ReconnectRequest) request).getLocation();
            return createAnchor(p);
        } else if (request instanceof DropRequest) {
            final Point p = ((DropRequest) request).getLocation();
            return createAnchor(p);
        }
        throw new IllegalArgumentException(request + " not handled.");
    }

    /**
     * Create a XY anchor with the layer coordinates
     * @param absPoint an absolute point.
     * @return a XY anchor for the layer figure
     */
    @objid ("9b6697ed-ec2d-4796-8bc7-3a84073a3917")
    private ConnectionAnchor createAnchor(final Point absPoint) {
        DrawingLayerEditPart.tmpAnchor.setLocation(absPoint);
        getFigure().translateToRelative(DrawingLayerEditPart.tmpAnchor);
        return new PointAnchor(getFigure(), DrawingLayerEditPart.tmpAnchor);
    }

    @objid ("a9954a2f-850b-4239-8079-39a59bec86b0")
    @Override
    public ConnectionAnchor getTargetConnectionAnchor(Request request) {
        if (request instanceof ReconnectRequest) {
            final Point p = ((ReconnectRequest) request).getLocation();
        
            return createAnchor(p);
        } else if (request instanceof DropRequest) {
            final Point p = ((DropRequest) request).getLocation();
            return createAnchor(p);
        }
        throw new IllegalArgumentException(request + " not handled.");
    }

    @objid ("d2710091-a984-4f89-a109-1dbcd99b4dbe")
    @Override
    public Object createAnchorModel(ConnectionAnchor anchor) {
        if (anchor instanceof PointAnchor) {
            PointAnchor pa = (PointAnchor) anchor;
            final Point refPoint = pa.getRelativeReferencePoint();
        
            return new GmPointAnchor(new Dimension(refPoint.x, refPoint.y));
        }
        return RectangleNodeAnchorProvider.get().createAnchorModel(anchor);
    }

    /**
     * Add a connection to the layer.
     * <p>
     * The connection is added to the connections sub layer.
     * @param connectionFigure a connection figure
     */
    @objid ("688b848e-599b-44f5-b3d1-ae78d4059316")
    public void addConnection(IFigure connectionFigure) {
        // Ensure the figures exist.
        getFigure();
        
        this.connectionsLayer.add(connectionFigure);
    }

    @objid ("474c542a-729a-4f90-812c-fbb652021d1a")
    @Override
    public GmDrawingLayer getModel() {
        return (GmDrawingLayer) super.getModel();
    }

}
