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

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Bendpoint;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.geometry.Point;
import org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId;

/**
 * Path for connections in oblique/bendpoint mode.
 * 
 * @author cmarin
 */
@objid ("80494675-1dec-11e2-8cad-001ec947c8cc")
public class ObliqueConnectionHelper implements IConnectionHelper {
    @objid ("48640c96-d892-420a-b162-5d0457ed8510")
    private final Connection connection;

    @objid ("67bd0702-1f19-4ccd-80ec-f2764126084a")
    private List<Point> bendPoints = new ArrayList<>();

    @objid ("8049467f-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public List<Point> getModelBendPoints() {
        return this.bendPoints;
    }

    @objid ("80494689-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void updateFrom(final RawPathData rawData) {
        this.bendPoints.clear();
        readRawPoints(rawData);
    }

    /**
     * Get the path routing mode.
     * 
     * @return the path routing mode.
     */
    @objid ("8049468e-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public ConnectionRouterId getRoutingMode() {
        return ConnectionRouterId.BENDPOINT;
    }

    /**
     * @param rawData the raw data provided by the tool (expressed in absolute coordinates)
     * @param connection the connection for which this helper is created.
     */
    @objid ("804ba8a6-1dec-11e2-8cad-001ec947c8cc")
    public ObliqueConnectionHelper(final RawPathData rawData, final Connection connection) {
        this.connection = connection;
        readRawPoints(rawData);
    }

    /**
     * constructor from a list of points (in coordinates relative to the connection).
     * 
     * @param points the list of point.
     * @param connection the connection for which this helper is created.
     */
    @objid ("804ba8af-1dec-11e2-8cad-001ec947c8cc")
    public ObliqueConnectionHelper(final List<Point> points, final Connection connection) {
        this.connection = connection;
        this.bendPoints = points;
    }

    @objid ("804ba8c2-1dec-11e2-8cad-001ec947c8cc")
    private void readRawPoints(final RawPathData rawData) {
        // Raw data is expressed in absolute coordinates
        // Translate to coordinates to the connection.
        for (Point absolutePoint : rawData.getPath()) {
            final Point p = new Point(absolutePoint);
            this.connection.translateToRelative(p);
            this.bendPoints.add(p);
        }
        
        this.bendPoints = BendPointUtils.draw2dPointsToModelConstraint(this.bendPoints);
    }

    @objid ("2a569448-22ae-4092-830d-62ce8fcc88b0")
    @Override
    public List<Bendpoint> getRoutingConstraint() {
        return BendPointUtils.toDraw2dConstraint(this.bendPoints);
    }

}
