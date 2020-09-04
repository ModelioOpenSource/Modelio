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

package org.modelio.diagram.elements.core.link.path;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.XYAnchor;
import org.eclipse.draw2d.geometry.Point;
import org.modelio.diagram.elements.core.figures.routers.RakeConstraint;
import org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId;

/**
 * Path for connections in rake mode.
 * 
 * @author cmarin
 */
@objid ("80506d70-1dec-11e2-8cad-001ec947c8cc")
public final class RakeConnectionHelper implements IConnectionHelper {
    @objid ("42ed70ac-b239-42ab-926a-b8a98b95eedc")
    private final Connection connection;

    @objid ("80506d75-1dec-11e2-8cad-001ec947c8cc")
    private RakeConstraint constraint;

    @objid ("80506d76-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public List<Point> getModelBendPoints() {
        if (this.constraint == null) {
            return Collections.emptyList();
        }
        
        List<Point> ret = new ArrayList<>(2);
        
        XYAnchor anchor = this.constraint.getSourceRakeAnchor();
        if (anchor != null) {
            ret.add(anchor.getReferencePoint());
        }
        
        anchor = this.constraint.getTargetRakeAnchor();
        if (anchor != null) {
            ret.add(anchor.getReferencePoint());
        }
        return ret;
    }

    /**
     * Updates this path from the given connection creation raw data.
     * @param req The connection creation raw data.
     */
    @objid ("80506d80-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public final void updateFrom(final RawPathData req) {
        ConnectionAnchor targetAnchor1 = this.connection.getTargetAnchor();
        IFigure targetFig = targetAnchor1.getOwner();
        
        if (targetFig instanceof Connection) {
            Connection targetConn = (Connection) targetFig;
            if (targetConn.getRoutingConstraint() instanceof RakeConstraint) {
                // connecting to an existing rake
                this.constraint = (RakeConstraint) targetConn.getRoutingConstraint();
            } else {
                // connecting to a normal connection
                Point rakeLocation = targetAnchor1.getLocation(req.getLastPoint());
        
                this.constraint = new RakeConstraint();
                this.constraint.setTargetRakeAnchor(new XYAnchor(rakeLocation));
        
                // Change the connection target to the other connection target
                this.connection.setTargetAnchor(targetConn.getTargetAnchor());
            }
        } else {
            // TODO to be done
        }
    }

    /**
     * Get the path routing mode.
     * @return the path routing mode.
     */
    @objid ("80506d86-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public ConnectionRouterId getRoutingMode() {
        return ConnectionRouterId.ORTHOGONAL;
    }

    /**
     * constructor from serialized data
     * @param serializedData serialized data.
     * @param connection the routed connection
     */
    @objid ("80506d8c-1dec-11e2-8cad-001ec947c8cc")
    public RakeConnectionHelper(final RakeConstraint serializedData, final Connection connection) {
        this.constraint = serializedData;
        this.connection = connection;
    }

    /**
     * Creates a rake helper from a raw path data.
     * @param req a raw path data.
     * @param connection the routed connection
     */
    @objid ("80506d95-1dec-11e2-8cad-001ec947c8cc")
    public RakeConnectionHelper(final RawPathData req, final Connection connection) {
        this.connection = connection;
        updateFrom(req);
    }

    @objid ("80506d9e-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Object getRoutingConstraint() {
        // TODO convert from model to draw2d
        return this.constraint;
    }

    @objid ("b9044736-d480-4437-a834-8e6d92d60912")
    @Override
    public Object getModelPathData() {
        // TODO Auto-generated method stub
        return this.constraint;
    }

}
