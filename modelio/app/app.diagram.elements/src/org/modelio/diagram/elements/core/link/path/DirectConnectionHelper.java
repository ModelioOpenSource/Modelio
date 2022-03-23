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
package org.modelio.diagram.elements.core.link.path;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Point;
import org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId;

/**
 * Direct path.
 * <p>
 * The connection will go straight from the source to the destination.
 * 
 * @author cmarin
 */
@objid ("8046e419-1dec-11e2-8cad-001ec947c8cc")
public class DirectConnectionHelper implements IConnectionHelper {
    /**
     * Get all bend points without extremity points.
     * @return the bend points.
     */
    @objid ("8046e41e-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public List<Point> getModelBendPoints() {
        return Collections.emptyList();
    }

    @objid ("84804562-3b48-4724-84aa-916947fdac44")
    @Override
    public boolean isUsable() {
        return true;
    }

    @objid ("8046e428-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void updateFrom(final RawPathData req) {
        // Nothing to do
    }

    /**
     * Get the path routing mode.
     * @return the path routing mode.
     */
    @objid ("8046e42d-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public ConnectionRouterId getRoutingMode() {
        return ConnectionRouterId.DIRECT;
    }

    /**
     * constructor from serialized data
     */
    @objid ("8046e433-1dec-11e2-8cad-001ec947c8cc")
    public  DirectConnectionHelper() {
        
    }

    /**
     * Get the draw2d routing constraint to apply to the connection figure.
     * @return the draw2d routing constraint.
     */
    @objid ("8049464b-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Object getRoutingConstraint() {
        return null;
    }

}
