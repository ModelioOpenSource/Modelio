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

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Connection;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.commands.Command;
import org.modelio.diagram.elements.core.figures.routers.AnchorBounds;
import org.modelio.diagram.elements.core.figures.routers.ConnectionState;
import org.modelio.diagram.elements.core.link.GmPath;
import org.modelio.diagram.elements.core.link.IAnchorModelProvider;
import org.modelio.diagram.elements.core.link.MPoint;
import org.modelio.diagram.elements.core.link.path.BendPointUtils;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLinkObject;
import org.modelio.diagram.elements.core.model.IGmPath;
import org.modelio.diagram.elements.plugin.DiagramElements;
import org.modelio.vcore.model.api.MTools;

/**
 * Command that updates the {@link IGmPath} of an {@link IGmLinkObject} from a {@link Connection}.
 */
@objid ("80316eee-1dec-11e2-8cad-001ec947c8cc")
public class ChangeLinkRoutingConstraintCommand extends Command {
    @objid ("efdc32b6-fca1-4792-b270-0a85f4b15e2a")
    private boolean isForced;

    @objid ("e1ca5238-941b-4c2f-92d7-ab0cbc0fdf5a")
    protected final Connection connection;

    @objid ("26751bbb-f131-4357-b679-4ec130d96b4a")
    protected final IGmPath initialPath;

    @objid ("80316efa-1dec-11e2-8cad-001ec947c8cc")
    protected final IGmLinkObject model;

    @objid ("ce9f496b-fd7f-4eb9-b0db-3b37a90a5ef1")
    protected final ConnectionState connectionState;

    @objid ("9816f321-3e59-4de9-bd6d-a145a2d4ce3b")
    private ConnectionEditPart connectionEP;

    /**
     * Creates a command that changes the routing constraint of the given connection.
     * @param connectionEP the edit part of the connection to modify.
     * @param connectionState the connection state to save in the model
     * @param isForced if false, check if a previous command already modified the connection route before changing the constraint.
     */
    @objid ("80316efe-1dec-11e2-8cad-001ec947c8cc")
    public  ChangeLinkRoutingConstraintCommand(final ConnectionEditPart connectionEP, ConnectionState connectionState, boolean isForced) {
        this.connectionEP = connectionEP;
        this.model = (IGmLinkObject) connectionEP.getModel();
        this.connection = (Connection) connectionEP.getFigure();
        this.connectionState = connectionState;
        this.initialPath = this.model.getPath();
        this.isForced = isForced;
        
    }

    /**
     * Creates a command that changes the routing constraint of the given connection. (isForced is set to <code>false</code>)
     * @param connectionEP the edit part of the connection to modify.
     * @param connectionState the connection state to save in the model
     */
    @objid ("d9d465e4-5964-4d94-8885-a33294b251a7")
    public  ChangeLinkRoutingConstraintCommand(final ConnectionEditPart connectionEP, ConnectionState connectionState) {
        this(connectionEP, connectionState, false);
    }

    /**
     * Creates a command that synchronize the routing constraint of the model from the Connection figure routing constraint.
     * @param connectionEP the edit part of the connection to modify.
     */
    @objid ("4bc36366-1ca1-4c1f-834c-b57b2086fb83")
    public  ChangeLinkRoutingConstraintCommand(final ConnectionEditPart connectionEP) {
        this(connectionEP, new ConnectionState().init((Connection) connectionEP.getFigure()));
    }

    @objid ("80316f08-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void execute() {
        if (!this.isForced && !this.model.getLayoutData().equals(this.initialPath)) {
            // A previous command already modified the connection route.
            if (DiagramElements.LOG.isDebugEnabled())
                DiagramElements.LOG.debug(new Throwable(String.format("Layout already changed:%n  - from %s%n  - to %s", this.initialPath, this.model.getLayoutData())));
            // Don't overwrite its work and abort.
            return;
        }
        
        IAnchorModelProvider sourceEp = (IAnchorModelProvider) this.connectionEP.getSource();
        IAnchorModelProvider targetEp = (IAnchorModelProvider) this.connectionEP.getTarget();
        
        if (sourceEp != null && targetEp != null) {
            try {
                GmPath newPath = new GmPath(this.initialPath);
        
                Object sourceAnchorModel = sourceEp.createAnchorModel(this.connectionState.getSourceAnchor());
                newPath.setSourceAnchor(sourceAnchorModel);
        
                Object targetAnchorModel = targetEp.createAnchorModel(this.connectionState.getTargetAnchor());
                newPath.setTargetAnchor(targetAnchorModel);
        
                List<MPoint> modelPoints = getNewBendPoints();
        
                newPath.setPathData(modelPoints);
                this.model.setLayoutData(newPath);
            } catch (@SuppressWarnings ("unused") IllegalArgumentException e) {
                // Invalid anchor found, just do nothing.
            }
        }
        
    }

    /**
     * Compute the new bend points from the command parameters.
     * @see #connectionState
     * @see #connection
     * @return the new draw2d bend points
     */
    @objid ("528e6f9c-189b-45c3-98c7-383281eb28a5")
    protected List<MPoint> getNewBendPoints() {
        List<MPoint> points = BendPointUtils.copyConstraint(this.connectionState.getMPoints());
        if (!points.isEmpty()) {
            AnchorBounds.SHARED.fromAnchors(this.connectionState.getSourceAnchor(), this.connectionState.getTargetAnchor())
                    .expand(1)
                    .toRelative(this.connection)
                    .trimContainedPoints(points, MPoint::getLocation, true);
        }
        return points;
    }

    @objid ("6720891d-10c8-42e6-9a00-7c403cd02115")
    @Override
    public boolean canExecute() {
        final IGmDiagram diagram = this.model.getDiagram();
        return MTools.getAuthTool().canModify(diagram.getRelatedElement());
    }

}
