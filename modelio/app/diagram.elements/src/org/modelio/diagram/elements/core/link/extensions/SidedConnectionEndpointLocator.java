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

package org.modelio.diagram.elements.core.link.extensions;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.geometry.Transposer;

/**
 * Copy of {@link org.eclipse.draw2d.ConnectionEndpointLocator} with
 * <ul>
 * <li>{@link #isEnd()} accessible.
 * <li>handles fixed size
 * </ul>
 * <h3>history</h3>
 * <ul>
 * <li>Modelio < 3.4 : did inherit from {@link org.eclipse.draw2d.ConnectionEndpointLocator} to get access to {@link #isEnd()}
 * <li>Modelio 3.4 : is now a copy to handle size fixed in the locator model.
 * </ul>
 * 
 * @author cmarin
 */
@objid ("800dab9e-1dec-11e2-8cad-001ec947c8cc")
public class SidedConnectionEndpointLocator implements IResizableFigureLocator {
    @objid ("82fd793b-2aa3-42e5-ae73-cf03b41e013f")
    private boolean end;

    @objid ("bed89d64-1b65-4737-bfd6-d3694d426564")
    private int uDistance;

    @objid ("e877cfde-6c76-4489-9d84-d0192f423c23")
    private int vDistance;

    @objid ("239e8a94-bd5f-4d9b-9c34-9abf3383bde9")
    private int widthConstraint = -1;

    @objid ("20fb0a09-897e-434b-9d22-fa5334fdcc2a")
    private int heightConstraint = -1;

    @objid ("1d1524c7-445b-49f9-9e04-125924f925f3")
    private Connection connection;

    /**
     * Transposes the location if the connection point is along the top or
     * bottom of its owner figure.
     */
    @objid ("1306b54d-6cfd-4c47-8655-17c30adbf999")
    protected Transposer transposer = new Transposer();

    /**
     * Constructs a ConnectionEndpointLocator using the given {@link Connection}
     * . If <i>isEnd</i> is <code>true</code>, the location is relative to the
     * Connection's end (or target) point. If <i>isEnd</i> is <code>false</code>
     * , the location is relative to the Connection's start (or source) point.
     * @param c The Connection
     * @param isEnd <code>true</code> is location is relative to end point
     */
    @objid ("800daba4-1dec-11e2-8cad-001ec947c8cc")
    public SidedConnectionEndpointLocator(Connection c, boolean isEnd) {
        this.end = isEnd;
        this.connection = c;
        this.uDistance = 14;
        this.vDistance = 4;
    }

    /**
     * Return <i>true</i> if the figure is located at the target end, <i>false</i> if the figure is located at the source end.
     * @return <i>true</i> if the figure is located at the target end, <i>false</i> if the figure is located at the source end.
     */
    @objid ("800dabab-1dec-11e2-8cad-001ec947c8cc")
    public boolean isEnd() {
        return this.end;
    }

    /**
     * Returns an integer representing the side of the passed Rectangle that a
     * point lies on. 1 == Top 2 == Right 3 == Bottom 4 == Left
     * @param loc The point that is to be located
     */
    @objid ("f5e0edee-7442-4318-b481-e8254e6edcba")
    private int calculateConnectionLocation(Point loc, Point topLeft, Point center) {
        double m1, m2 = 0;
        m1 = (double) (topLeft.y - center.y) / (double) (topLeft.x - center.x);
        
        if (loc.x - center.x != 0) {
            m2 = (double) (loc.y - center.y) / (double) (loc.x - center.x);
        }
        
        if (loc.x == center.x) {
            // Case where m2 is vertical
            if (loc.y < center.y) {
                return 3;
            } else {
                return 1;
            }
        } else if (Math.abs(m2) <= Math.abs(m1)) {
            // Connection start point along left or right side
            if (loc.x < center.x) {
                return 4;
            } else {
                return 2;
            }
        } else {
            // Connection start point along top or bottom
            if (loc.y < center.y) {
                return 3;
            } else {
                return 1;
            }
        }
    }

/*
     * This method is used to calculate the "quadrant" value of a connection
     * that does not have an owner on its starting point.
     * 1 == Top 2 == Right 3 == Bottom 4 == Left
     * @param startPoint The starting point of the connection.
     * @param endPoint The end point of the connection.
     */
    @objid ("f52360da-3d1f-4011-9966-5deb07276286")
    private int calculateConnectionLocation(Point startPoint, Point endPoint) {
        if (Math.abs(endPoint.x - startPoint.x) > Math.abs(endPoint.y
                - startPoint.y)) {
            if (endPoint.x > startPoint.x) {
                return 2;
            } else {
                return 4;
            }
        } else {
            if (endPoint.y > startPoint.y) {
                return 1;
            } else {
                return 3;
            }
        }
    }

/*
     * Calculates 'tan' which is used as a factor for y adjustment when placing
     * the connection label. 'tan' is capped at 1.0 in the positive direction
     * and -1.0 in the negative direction.
     * @param startPoint The starting point of the connection.
     * @param endPoint The end point of the connection.
     * @since 2.0
     */
    @objid ("c05e9db2-401c-469a-97d4-d2855896b036")
    private double calculateTan(Point startPoint, Point endPoint) {
        double tan = 0;
        if (endPoint.x == startPoint.x) {
            tan = 1.0;
        } else {
            tan = (double) (endPoint.y - startPoint.y)
                    / (double) (endPoint.x - startPoint.x);
        }
        if (tan > 1) {
            tan = 1.0;
        } else if (tan < -1) {
            tan = -1.0;
        }
        return tan;
    }

    @objid ("83214764-6614-4f16-bab6-657399cee56f")
    @SuppressWarnings("unused")
    private int calculateYShift(int figureWidth, int figureHeight) {
        int yShift = 0;
        if (this.vDistance < 0) {
            yShift = -figureHeight;
        } else if (this.vDistance == 0) {
            yShift = -figureHeight / 2;
        }
        return yShift;
    }

    @objid ("6887b3e4-e08a-40f2-a87e-c147ed2b7583")
    private Connection getConnection() {
        return this.connection;
    }

    @objid ("0a27e8fd-2234-4e55-a15e-dcc5cb5296c2")
    private IFigure getConnectionOwner() {
        IFigure connOwner;
        if (isEnd()) {
            ConnectionAnchor targetAnchor = this.connection.getTargetAnchor();
            connOwner = targetAnchor != null ? targetAnchor.getOwner() : null;
        } else {
            ConnectionAnchor sourceAnchor = this.connection.getSourceAnchor();
            connOwner = sourceAnchor != null ? sourceAnchor.getOwner() : null;
        }
        return connOwner;
    }

    /**
     * Returns the distance in pixels from the anchor's owner.
     * @return the offset distance from the endpoint figure
     */
    @objid ("e16a78be-1b24-4455-9a7e-44ca40649f6c")
    public int getUDistance() {
        return this.uDistance;
    }

    /**
     * Returns the distance in pixels from the connection
     * @return the offset from the connection itself
     */
    @objid ("4b9626a7-fdd0-4624-87fb-d26c471b6ae5")
    public int getVDistance() {
        return this.vDistance;
    }

    /**
     * Relocates the given IFigure at either the source or target end of the
     * Connection, based on the <code>boolean</code> given in the constructor
     * {@link #SidedConnectionEndpointLocator(Connection, boolean)}.
     * @param figure The figure to relocate
     */
    @objid ("5630b531-5aaa-45b5-a4db-be3211ceb8b1")
    @Override
    public void relocate(IFigure figure) {
        Connection conn = getConnection();
        Point startPoint = Point.SINGLETON;
        Point endPoint = new Point();
        
        int startPointPosition = 0;
        int endPointPosition = 1;
        if (isEnd()) {
            startPointPosition = conn.getPoints().size() - 1;
            endPointPosition = startPointPosition - 1;
        }
        
        conn.getPoints().getPoint(startPoint, startPointPosition);
        conn.getPoints().getPoint(endPoint, endPointPosition);
        
        IFigure connOwner = getConnectionOwner();
        
        int quadrant;
        if (connOwner != null) {
            Rectangle connOwnerBounds = connOwner.getBounds();
            Point connOwnerCenter = connOwnerBounds.getCenter();
            Point connOwnerTL = connOwnerBounds.getTopLeft();
            quadrant = calculateConnectionLocation(startPoint, connOwnerTL,
                    connOwnerCenter);
        } else {
            quadrant = calculateConnectionLocation(startPoint, endPoint);
        }
        
        int cos = 1;
        this.transposer.setEnabled(false);
        
        /*
         * Label placement calculations are done as if the connection point is
         * along the left or right side of the figure. If the connection point
         * is along the top or bottom, values are transposed.
         */
        if (quadrant == 1 || quadrant == 3) {
            this.transposer.setEnabled(true);
        }
        
        if (quadrant == 3 || quadrant == 4) {
            cos = -1;
        }
        
        Dimension figureSize = this.transposer.t(computeFigureSize(figure));
        
        startPoint = this.transposer.t(startPoint);
        endPoint = this.transposer.t(endPoint);
        
        double tan = calculateTan(startPoint, endPoint);
        
        int figureWidth = figureSize.width;
        int figureHeight = figureSize.height;
        int yShift = calculateYShift(figureWidth, figureHeight);
        
        Point figurePoint = new Point(
                startPoint.x + (this.uDistance * cos) + figureWidth * ((cos - 1) / 2),
                (int) (startPoint.y + cos * this.uDistance * tan + this.vDistance + yShift));
        
        Rectangle figureBounds = new Rectangle();
        figureBounds.setSize(this.transposer.t(figureSize));
        figureBounds.setLocation(this.transposer.t(figurePoint));
        figure.setBounds(figureBounds);
    }

    /**
     * Calculate the figure size from this locator
     * @param target the figure to relocate.
     * @return the figure size to set.
     */
    @objid ("efe6a76d-e7f7-44f9-805f-eb15cb63a061")
    protected Dimension computeFigureSize(IFigure figure) {
        return figure.getPreferredSize(getWidthConstraint(), getHeightConstraint());
    }

    /**
     * Sets the distance in pixels from the Connection's owner.
     * @param distance Number of pixels to place the ConnectionEndpointLocator from
     * its owner.
     * @since 2.0
     */
    @objid ("a79f65a5-3d19-4f46-ac02-17fd4747a718")
    public void setUDistance(int distance) {
        this.uDistance = distance;
    }

    /**
     * Sets the distance in pixels from the Connection.
     * @param distance Number of pixels to place the ConnectionEndpointLocator from
     * its Connection.
     * @since 2.0
     */
    @objid ("df35ef10-aaa2-4e68-8250-508c3c1a6fe3")
    public void setVDistance(int distance) {
        this.vDistance = distance;
    }

    /**
     * Get the width constraint.
     * <p>
     * -1 means no constraint.
     * @return the width constraint.
     */
    @objid ("fd56bd88-b2a9-4809-a228-633443663d21")
    @Override
    public int getWidthConstraint() {
        return this.widthConstraint;
    }

    /**
     * Set the width constraint.
     * <p>
     * -1 means no constraint.
     * @param fixedWidth the width constraint.
     */
    @objid ("fc6b2254-5db2-42c7-89a4-c960f682f376")
    @Override
    public void setWidthConstraint(int fixedWidth) {
        this.widthConstraint = fixedWidth;
    }

    /**
     * Get the height constraint.
     * <p>
     * -1 means no constraint.
     * @return the height constraint.
     */
    @objid ("bdfe5240-7580-4f2e-9dc5-223d47615e76")
    @Override
    public int getHeightConstraint() {
        return this.heightConstraint;
    }

    /**
     * Set the height constraint.
     * <p>
     * -1 means no constraint.
     * @param fixedHeight the height constraint.
     */
    @objid ("280cc6a2-a9ed-4766-9f21-0d94b8363a2d")
    @Override
    public void setHeightConstraint(int fixedHeight) {
        this.heightConstraint = fixedHeight;
    }

}
