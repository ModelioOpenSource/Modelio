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

package org.modelio.diagram.elements.common.ghostlink;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionLocator;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.modelio.diagram.elements.core.figures.RoundedLinkFigure;
import org.modelio.diagram.elements.core.figures.routers.RakeRouter;
import org.modelio.diagram.elements.core.link.ConnectionRouterRegistry;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.link.GmLinkLayoutEditPolicy;
import org.modelio.diagram.elements.core.link.SelectConnectionEditPartTracker;
import org.modelio.diagram.elements.core.link.ortho.OrthoBendpointEditPolicy;
import org.modelio.diagram.elements.core.link.path.ConnectionHelperFactory;
import org.modelio.diagram.elements.core.link.path.ConnectionPolicyUtils;
import org.modelio.diagram.elements.core.link.path.IConnectionHelper;
import org.modelio.diagram.elements.core.link.rake.CreateRakeLinkEditPolicy;
import org.modelio.diagram.elements.core.link.rake.RakeLinkEditPolicy;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.model.IGmPath;
import org.modelio.diagram.elements.core.policies.DefaultBendpointEditPolicy;
import org.modelio.diagram.elements.core.policies.DefaultConnectionEndpointEditPolicy;
import org.modelio.diagram.elements.core.policies.DefaultDeleteLinkEditPolicy;
import org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId;

/**
 * Edit part for GmLinks.
 */
@objid ("7e4185bb-1dec-11e2-8cad-001ec947c8cc")
public class GhostLinkEditPart extends AbstractConnectionEditPart implements PropertyChangeListener {
    @objid ("7e43e804-1dec-11e2-8cad-001ec947c8cc")
    private static final int METACLASS_LABEL_INDEX = 0;

    @objid ("7e43e806-1dec-11e2-8cad-001ec947c8cc")
    private static final int NAME_LABEL_INDEX = GhostLinkEditPart.METACLASS_LABEL_INDEX + 1;

    @objid ("7e43e808-1dec-11e2-8cad-001ec947c8cc")
    private RoutingMode currentRoutingMode = new RoutingMode();

    /**
     * Rake router for all ghost links.
     */
    @objid ("7e43e809-1dec-11e2-8cad-001ec947c8cc")
    protected static RakeRouter rakeRouter = new RakeRouter();

    /**
     * Constructor for deserialization.
     */
    @objid ("7e43e80b-1dec-11e2-8cad-001ec947c8cc")
    public GhostLinkEditPart() {
        super();
    }

    @objid ("7e43e80e-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void activate() {
        super.activate();
        getLinkModel().addPropertyChangeListener(this);
    }

    @objid ("7e43e811-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Object getAdapter(Class adapter) {
        return null; // cannot do anything with a ghost
    }

    @objid ("7e43e817-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public DragTracker getDragTracker(Request req) {
        return new SelectConnectionEditPartTracker(this);
    }

    @objid ("7e43e821-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Link layout (bendpoints) update
        if (evt.getPropertyName().equals(IGmObject.PROPERTY_LAYOUTDATA)) {
            refreshSourceAnchor();
            refreshTargetAnchor();
            refreshVisuals();
        }
    }

    @objid ("7e43e825-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE, new DefaultConnectionEndpointEditPolicy());
        installEditPolicy(EditPolicy.NODE_ROLE, new CreateRakeLinkEditPolicy());
        installEditPolicy(EditPolicy.CONNECTION_ROLE, new DefaultDeleteLinkEditPolicy());
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new GmLinkLayoutEditPolicy());
        
        if (getRoutingMode().routingStyle != null) {
            updateBendPointEditPolicies(getRoutingMode());
        }
    }

    @objid ("7e464a5d-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected IFigure createFigure() {
        final RoundedLinkFigure connection = new RoundedLinkFigure();
        
        connection.setForegroundColor(ColorConstants.lightGray);
        
        // Metaclass label
        Label metaclassLabel = new Label();
        metaclassLabel.setOpaque(false);
        ConnectionLocator metaclassLabelLocator = new ConnectionLocator(connection, ConnectionLocator.MIDDLE);
        metaclassLabelLocator.setGap(5);
        metaclassLabelLocator.setRelativePosition(PositionConstants.NORTH);
        
        connection.add(metaclassLabel, metaclassLabelLocator, GhostLinkEditPart.METACLASS_LABEL_INDEX);
        
        // Name label
        Label nameLabel = new Label();
        nameLabel.setOpaque(false);
        ConnectionLocator nameLabelLocator = new ConnectionLocator(connection, ConnectionLocator.MIDDLE);
        nameLabelLocator.setGap(5);
        nameLabelLocator.setRelativePosition(PositionConstants.SOUTH);
        
        connection.add(nameLabel, nameLabelLocator, GhostLinkEditPart.NAME_LABEL_INDEX);
        return connection;
    }

    /**
     * Get the connection router registry.
     * 
     * @return the connection router registry.
     */
    @objid ("7e464a64-1dec-11e2-8cad-001ec947c8cc")
    protected ConnectionRouterRegistry getConnectionRouterRegistry() {
        return (ConnectionRouterRegistry) getViewer().getProperty(ConnectionRouterRegistry.ID);
    }

    /**
     * Get the connection routing mode.
     * 
     * @return the connection routing mode.
     */
    @objid ("7e464a69-1dec-11e2-8cad-001ec947c8cc")
    protected RoutingMode getRoutingMode() {
        return this.currentRoutingMode;
    }

    @objid ("7e464a6e-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void refreshVisuals() {
        super.refreshVisuals();
        final GmLink gmLink = getLinkModel();
        final PolylineConnection conn = (PolylineConnection) getFigure();
        
        // Update the connection router & Refresh route
        updateConnectionRoute(conn);
        
        // Refresh labels
        Label metaclasslabel = (Label) conn.getChildren().get(GhostLinkEditPart.METACLASS_LABEL_INDEX);
        metaclasslabel.setText("<<" + gmLink.getGhostMetaclass() + ">>\n");
        
        Label nameLabel = (Label) conn.getChildren().get(GhostLinkEditPart.NAME_LABEL_INDEX);
        nameLabel.setText(gmLink.getGhostLabel());
    }

    @objid ("7e464a71-1dec-11e2-8cad-001ec947c8cc")
    private GmLink getLinkModel() {
        return (GmLink) getModel();
    }

    /**
     * Add an edit policy to edit bend points if the router handles bend point editing.
     * 
     * @param newRoutingMode the new routing mode
     */
    @objid ("7e464a75-1dec-11e2-8cad-001ec947c8cc")
    private void updateBendPointEditPolicies(RoutingMode newRoutingMode) {
        removeEditPolicy(EditPolicy.CONNECTION_BENDPOINTS_ROLE);
        
        if (newRoutingMode.rake) {
            installEditPolicy(EditPolicy.CONNECTION_BENDPOINTS_ROLE, new RakeLinkEditPolicy());
        } else {
            switch (newRoutingMode.routingStyle) {
            case DIRECT:
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
    @objid ("7e464a79-1dec-11e2-8cad-001ec947c8cc")
    private void updateConnectionRoute(final Connection cnx) {
        // Refresh anchors
        refreshSourceAnchor();
        refreshTargetAnchor();
        
        final IGmLink gmLink = getLinkModel();
        final RoutingMode newRoutingMode = new RoutingMode(gmLink.getPath());
        final RoutingMode oldRoutingMode = getRoutingMode();
        
        // Change connection router if the rake mode changes or there is no rake and the style changes
        if (oldRoutingMode.rake != newRoutingMode.rake ||
                (!newRoutingMode.rake && oldRoutingMode.routingStyle != newRoutingMode.routingStyle)) {
            // Set the connection router
            if (newRoutingMode.rake) {
                cnx.setConnectionRouter(GhostLinkEditPart.rakeRouter);
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
     * {@inheritDoc}
     * <p>
     * Redefined to set the collections of all diagram connections on the {@link RoundedLinkFigure}. This collection is used to find intersections to draw bridges.
     * @since 3.7
     * @see RoundedLinkFigure#setAllDiagramConnections(Collection)
     */
    @objid ("82647b24-3846-457d-9ebd-b74058f69851")
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

    /**
     * Represents the routing mode of the link.
     * 
     * @author cmarin
     */
    @objid ("7e464a80-1dec-11e2-8cad-001ec947c8cc")
    private static class RoutingMode {
        @objid ("7e464a83-1dec-11e2-8cad-001ec947c8cc")
        public boolean rake = false;

        @objid ("8f1306a9-1e83-11e2-8cad-001ec947c8cc")
        public ConnectionRouterId routingStyle = null;

        @objid ("7e464a85-1dec-11e2-8cad-001ec947c8cc")
        public RoutingMode() {
        }

        @objid ("7e464a87-1dec-11e2-8cad-001ec947c8cc")
        public RoutingMode(final IGmPath path) {
            this.routingStyle = path.getRouterKind();
            this.rake = path.getSourceRake() != null || path.getTargetRake() != null;
        }

        @objid ("7e464a8b-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + (this.rake ? 1231 : 1237);
            result = prime * result + ((this.routingStyle == null) ? 0 : this.routingStyle.hashCode());
            return result;
        }

        @objid ("7e464a90-1dec-11e2-8cad-001ec947c8cc")
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
