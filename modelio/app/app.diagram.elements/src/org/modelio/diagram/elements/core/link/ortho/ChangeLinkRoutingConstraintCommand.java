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

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Bendpoint;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.handles.HandleBounds;
import org.modelio.diagram.elements.core.link.GmPath;
import org.modelio.diagram.elements.core.link.IAnchorModelProvider;
import org.modelio.diagram.elements.core.link.path.BendPointUtils;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLinkObject;
import org.modelio.diagram.elements.core.model.IGmPath;
import org.modelio.vcore.model.api.MTools;

/**
 * A command that changes the routing constraint of the given connection.
 * 
 * @author fpoyer
 */
@objid ("80316eee-1dec-11e2-8cad-001ec947c8cc")
public class ChangeLinkRoutingConstraintCommand extends Command {
    @objid ("b6184cca-b43e-42a4-b9d2-98d7c7cb5d80")
    private List<Bendpoint> routingConstraint;

    @objid ("e1ca5238-941b-4c2f-92d7-ab0cbc0fdf5a")
    private Connection connection;

    @objid ("80316ef2-1dec-11e2-8cad-001ec947c8cc")
    private IGmPath path;

    @objid ("80316ef8-1dec-11e2-8cad-001ec947c8cc")
    private Object sourceAnchorModel;

    @objid ("80316ef9-1dec-11e2-8cad-001ec947c8cc")
    private Object targetAnchorModel;

    @objid ("80316efa-1dec-11e2-8cad-001ec947c8cc")
    private IGmLinkObject model;

    @objid ("188cd1ab-b6b9-4041-9394-3785483651be")
    private EditPartViewer viewer;

    /**
     * Creates a command that changes the routing constraint of the given connection.
     * 
     * @param path the new path
     * @param connectionEP the edit part of the connection to modify.
     */
    @objid ("80316efe-1dec-11e2-8cad-001ec947c8cc")
    @SuppressWarnings ("unchecked")
    public ChangeLinkRoutingConstraintCommand(final IGmPath path, final ConnectionEditPart connectionEP) {
        this.model = (IGmLinkObject) connectionEP.getModel();
        this.connection = (Connection) connectionEP.getFigure();
        this.path = new GmPath(path);
        this.viewer = connectionEP.getViewer();
        this.routingConstraint = (List<Bendpoint>) this.connection.getRoutingConstraint();
        this.sourceAnchorModel = ((IAnchorModelProvider) connectionEP.getSource()).createAnchorModel(this.connection.getSourceAnchor());
        this.targetAnchorModel = ((IAnchorModelProvider) connectionEP.getTarget()).createAnchorModel(this.connection.getTargetAnchor());
    }

    @objid ("80316f08-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void execute() {
        this.path.setSourceAnchor(this.sourceAnchorModel);
        this.path.setTargetAnchor(this.targetAnchorModel);
        
        Rectangle sourceBounds = getAnchorOwnerAbsoluteBounds(this.connection.getSourceAnchor()).expand(1, 1);
        this.connection.translateToRelative(sourceBounds);
        
        Rectangle targetBounds = getAnchorOwnerAbsoluteBounds(this.connection.getTargetAnchor()).expand(1, 1);
        this.connection.translateToRelative(targetBounds);
        
        ArrayList<Point> bendpoints = new ArrayList<>(this.routingConstraint.size());
        this.routingConstraint.forEach(bp -> bendpoints.add(bp.getLocation()));
        
        if (!sourceBounds.contains(targetBounds)) {
            while (!bendpoints.isEmpty() && sourceBounds.contains(bendpoints.get(0))) {
                bendpoints.remove(0);
            }
        } else if (!sourceBounds.equals(targetBounds)) {
            // Remove the first point unless the link is reflexive
            bendpoints.remove(0);
        }
        if (!targetBounds.contains(sourceBounds)) {
            for (int lastIdx = bendpoints.size() - 1; lastIdx >= 0 && targetBounds.contains(bendpoints.get(lastIdx)); lastIdx--) {
                bendpoints.remove(lastIdx);
            }
        } else if (!bendpoints.isEmpty()) {
            bendpoints.remove(0);
        }
        
        List<Point> modelPoints = BendPointUtils.draw2dPointsToModelConstraint(bendpoints);
        
        // DEBUG
        // System.err.println("final: " + points.size());
        this.path.setPathData(modelPoints);
        this.model.setLayoutData(this.path);
    }

    /**
     * Get the anchor owner (handle)bounds in absolute coordinates. If the anchor is not attached to a figure, returns a 1x1 sized rectangle located at the anchor reference point.
     * 
     * @param anchor The anchor.
     * @return The anchor owner bounds.
     */
    @objid ("80316f0b-1dec-11e2-8cad-001ec947c8cc")
    private Rectangle getAnchorOwnerAbsoluteBounds(final ConnectionAnchor anchor) {
        final IFigure f = anchor.getOwner();
        if (f == null) {
            Point p = anchor.getReferencePoint();
            return new Rectangle(p.x, p.y, 1, 1);
        } else {
            final Rectangle bounds = f instanceof HandleBounds ? ((HandleBounds) f).getHandleBounds()
                    .getCopy() : f.getBounds()
                            .getCopy();
            f.translateToAbsolute(bounds);
        
            return bounds;
        }
    }

    @objid ("6720891d-10c8-42e6-9a00-7c403cd02115")
    @Override
    public boolean canExecute() {
        final IGmDiagram diagram = this.model.getDiagram();
        return (MTools.getAuthTool().canModify(diagram.getRelatedElement()));
    }

}
