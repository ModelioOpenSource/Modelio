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
package org.modelio.diagram.elements.core.link.extensions;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.geometry.Transposer;

/**
 * Factory of {@link SidedConnectionEndpointLocator}.
 */
@objid ("800b492b-1dec-11e2-8cad-001ec947c8cc")
class SidedConnectionEndpointLocatorFactory {
    @objid ("800b4932-1dec-11e2-8cad-001ec947c8cc")
    public  SidedConnectionEndpointLocatorFactory() {
        
    }

    /**
     * Compute a new uDistance and vDistance depending on the move delta and the edge object figure.
     * @param conn The connection figure
     * @param extension The extension figure to move
     * @param moveDelta the current move delta
     * @param mouseLocation the mouse location
     * @return The locator for the new position.
     */
    @objid ("800b4934-1dec-11e2-8cad-001ec947c8cc")
    public SidedConnectionEndpointLocator getLocator(final Connection conn, final IFigure extension, final Dimension moveDelta, final Point mouseLocation) {
        Point mousePosition = mouseLocation.getCopy();
        conn.translateToRelative(mousePosition);
        conn.translateFromParent(mousePosition);
        
        Dimension delta = moveDelta.getCopy();
        conn.translateToRelative(delta);
        conn.translateFromParent(delta);
        
        Point startPoint = Point.SINGLETON;
        Point endPoint = new Point();
        
        final SidedConnectionEndpointLocator currentLocator = (SidedConnectionEndpointLocator) conn.getLayoutManager()
                .getConstraint(extension);
        final boolean isTargetSide = currentLocator.isEnd();
        
        final PointList connPoints = conn.getPoints();
        int startPointPosition = 0;
        int endPointPosition = 1;
        if (isTargetSide) {
            startPointPosition = connPoints.size() - 1;
            endPointPosition = startPointPosition - 1;
        }
        
        connPoints.getPoint(startPoint, startPointPosition);
        connPoints.getPoint(endPoint, endPointPosition);
        
        final IFigure connOwner = getConnectionOwner(conn, isTargetSide);
        
        int quadrant;
        if (connOwner != null) {
            final Rectangle connOwnerBounds = connOwner.getBounds();
            final Point connOwnerCenter = connOwnerBounds.getCenter();
            final Point connOwnerTL = connOwnerBounds.getTopLeft();
            quadrant = calculateConnectionLocation(startPoint, connOwnerTL, connOwnerCenter);
        } else {
            quadrant = calculateConnectionLocation(startPoint, endPoint);
        }
        
        
        Transposer transposer = new Transposer();
        /*
         * Label placement calculations are done as if the connection point is along the left or right side of the figure. If
         * the connection point is along the top or bottom, values are transposed.
         */
        if (quadrant == SidedConnectionEndpointLocator.QUANDRANT_TOP || quadrant == SidedConnectionEndpointLocator.QUANDRANT_BOTTOM) {
            transposer.setEnabled(true);
        }
        
        int cos ;
        if (quadrant == SidedConnectionEndpointLocator.QUANDRANT_RIGHT || quadrant == SidedConnectionEndpointLocator.QUANDRANT_TOP) {
            cos = 1;
        } else {
            cos = -1;
        }
        
        
        final Dimension origFigureSize = extension.getPreferredSize();
        final Dimension figureSize = transposer.t(origFigureSize);
        
        startPoint = transposer.t(startPoint);
        endPoint = transposer.t(endPoint);
        delta = transposer.t(delta);
        mousePosition = transposer.t(mousePosition);
        
        
        final double tan = calculateTan(startPoint, endPoint);
        
        Point initialLocation = getLocation(currentLocator.getUDistance(), currentLocator.getVDistance(), startPoint,
                figureSize, cos, tan);
        Point location = new Point(initialLocation.x + delta.width, initialLocation.y + delta.height);
        
        int[] uvDistance = getUVDistance(location, startPoint, figureSize, cos, tan);
        int uDistance = uvDistance[0];
        int vDistance = uvDistance[1];
        
        if (false) {
            int[] mouseUVDistance = getUVDistance(mousePosition, startPoint, new Dimension(0, 0), cos, tan);
            int mouseV = mouseUVDistance[1];
        
            if ((mouseV >= 0 && vDistance <= 0) || (mouseV <= 0 && vDistance >= 0)) {
                vDistance = 0;
            } else
                if ((mouseV > 0 && currentLocator.getVDistance() < 0) ||
                        (mouseV < 0 && currentLocator.getVDistance() > 0)) {
                    int sign = currentLocator.getVDistance() > 0 ? 1 : -1;
                    location.y += figureSize.height * sign;
                    uvDistance = getUVDistance(location, startPoint, figureSize, cos, tan);
                    vDistance = uvDistance[1];
                    if ((mouseV >= 0 && vDistance <= 0) || (mouseV <= 0 && vDistance >= 0)) {
                        vDistance = 0;
                    }
                } else if (currentLocator.getVDistance() == 0) {
                    if (Math.abs(delta.height) < figureSize.height / 2) {
                        vDistance = 100;
                    } else {
                        int sign = vDistance < 0 ? 1 : -1;
                        location.y += figureSize.height / 2 * sign;
                        uvDistance = getUVDistance(location, startPoint, figureSize, cos, tan);
                        vDistance = uvDistance[1];
                    }
                }
        }
        
         SidedConnectionEndpointLocator ret = new SidedConnectionEndpointLocator(conn, isTargetSide);
         ret.setUDistance(uDistance);
         ret.setVDistance(vDistance);
        return ret;
    }

    /**
     * Returns an integer representing the side of the passed Rectangle that a point lies on. 1 == Top 2 == Right 3 == Bottom 4
     * == Left
     * @param loc The point that is to be located
     */
    @objid ("800b4949-1dec-11e2-8cad-001ec947c8cc")
    private static int calculateConnectionLocation(Point loc, Point topLeft, Point center) {
        return SidedConnectionEndpointLocator.calculateConnectionLocation(loc, topLeft, center);
    }

    /**
     * This method is used to calculate the "quadrant" value of a connection that does not have an owner on its starting point.
     * 
     * 1 == Top 2 == Right 3 == Bottom 4 == Left
     * @param startPoint The starting point of the connection.
     * @param endPoint The end point of the connection.
     */
    @objid ("800b4956-1dec-11e2-8cad-001ec947c8cc")
    private static int calculateConnectionLocation(Point startPoint, Point endPoint) {
        return SidedConnectionEndpointLocator.calculateConnectionLocation(startPoint, endPoint);
    }

    /**
     * Calculates 'tan' which is used as a factor for y adjustment when placing the connection label. 'tan' is capped at 1.0 in
     * the positive direction and -1.0 in the negative direction.
     * @param startPoint The starting point of the connection.
     * @param endPoint The end point of the connection.
     * 
     * @since 2.0
     */
    @objid ("800b4960-1dec-11e2-8cad-001ec947c8cc")
    private static double calculateTan(Point startPoint, Point endPoint) {
        return SidedConnectionEndpointLocator.calculateTan(startPoint, endPoint);
    }

    @objid ("800b496b-1dec-11e2-8cad-001ec947c8cc")
    private static Point getLocation(int uDistance, int vDistance, Point startPoint, Dimension figureSize, int cos, double tan) {
        int x = (cos * uDistance) + startPoint.x + (figureSize.width * (cos - 1) / 2);
        int y = (int) (vDistance + startPoint.y + (cos * uDistance * tan));
        return new Point(x, y);
    }

    @objid ("800b497b-1dec-11e2-8cad-001ec947c8cc")
    private static int[] getUVDistance(Point point, Point startPoint, Dimension figureSize, int cos, double tan) {
        int uDistance = (point.x - startPoint.x - (figureSize.width * (cos - 1) / 2)) / cos;
        int vDistance = (int) (point.y - startPoint.y - (cos * uDistance * tan));
        return new int[] { uDistance, vDistance };
    }

    /**
     * Get the node figure at the given side of the connection
     * @param conn The connection figure
     * @param isEnd <i>false</i> for the source side, <i>true</i> for the target side.
     * @return The node figure.
     */
    @objid ("800dab8a-1dec-11e2-8cad-001ec947c8cc")
    private static IFigure getConnectionOwner(Connection conn, boolean isEnd) {
        if (isEnd) {
            return conn.getTargetAnchor().getOwner();
        } else {
            return conn.getSourceAnchor().getOwner();
        }
        
    }

}
