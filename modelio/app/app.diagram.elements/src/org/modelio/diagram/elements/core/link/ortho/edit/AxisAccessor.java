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
package org.modelio.diagram.elements.core.link.ortho.edit;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.diagram.elements.core.figures.geometry.Direction;
import org.modelio.diagram.elements.core.figures.geometry.GeomUtils;
import org.modelio.diagram.elements.core.figures.geometry.Orientation;
import org.modelio.diagram.elements.core.figures.routers.AutoOrthoConstants;
import org.modelio.diagram.elements.plugin.DiagramElements;

/**
 * Service to access X or Y coordinates of geometry objects depending on the orthogonal orientation.
 * @since 5.0.2
 */
@objid ("1ab39e87-1d9d-4021-9209-e651f2601081")
public class AxisAccessor {
    @objid ("ab72582e-3239-4d0a-a768-48be37f76e04")
    private static final int MIN_DIST = AutoOrthoConstants.MIN_DIST;

    @objid ("ea8d0ab2-4dc6-46a5-a854-e8ad4779a7d7")
    private static final int SNAP_DIST = AutoOrthoConstants.SNAP_DIST;

    /**
     * true to access x coordinate, false for y coordinate.
     */
    @objid ("c8912133-2e41-42f2-85dd-0d614bf1fed2")
    private final boolean xcoord;

    /**
     * Accessor for X coordinates.
     */
    @objid ("966cf9db-d102-4f14-bc4c-5096e5157982")
    public static final AxisAccessor X = new AxisAccessor(true);

    /**
     * Accessor for Y coordinates.
     */
    @objid ("e199ad23-9919-4524-8f40-e79a930c32bf")
    public static final AxisAccessor Y = new AxisAccessor(false);

    /**
     * Horizontal segments accessors
     */
    @objid ("eb1029b7-4857-4267-88ce-be8598773b3b")
    public static final OrientedAccessors H = new OrientedAccessors(Orientation.HORIZONTAL, X, Y);

    /**
     * Vertical segments accessors
     */
    @objid ("7932a1c3-7701-48fc-acd0-0fa87df24641")
    public static final OrientedAccessors V = new OrientedAccessors(Orientation.VERTICAL, Y, X);

    @objid ("16d9a24d-b85a-4a3e-af5e-346b57b92734")
    private static final PrecisionRectangle TMPRECT = new PrecisionRectangle();

    /**
     * Bounce the given point outside the given rectangle if it is inside
     * @param editedLoc the point to bounce back
     * @param rectToBounce the rectangle to not cross
     * @return true if the point was bounced, false if nothing was done.
     */
    @objid ("2525ac3f-be11-4339-aa3b-d733368582a7")
    public boolean bounceFromRect(Point editedLoc, Rectangle rectToBounce) {
        Rectangle r = TMPRECT.setBounds(rectToBounce).expand(MIN_DIST , MIN_DIST );
        if (! isCoordContained(editedLoc, r))
            return false;
        if (this.xcoord) {
            if (editedLoc.x() < r.x() + r.preciseWidth() / 2.0) {
                editedLoc.setX(r.x());
            } else {
                editedLoc.setX(r.right());
            }
        } else {
            if (editedLoc.y() < r.y() + r.preciseHeight() / 2.0) {
                editedLoc.setY(r.y());
            } else {
                editedLoc.setY(r.bottom());
            }
        }
        return true;
    }

    /**
     * Test coordinates on both points for equality.
     * @param p1 a point
     * @param p2 another point
     * @return true if their coordinate on the axis are equals.
     */
    @objid ("50d5406d-817e-4f89-aa69-a8f60d882621")
    public boolean coordEquals(Point p1, Point p2) {
        if (this.xcoord) {
            return p1.x() == p2.x();
        } else {
            return p1.y() == p2.y();
        }
        
    }

    /**
     * Enforce minimum distance between 2 points that are supposed to be on the same horizontal or vertical line.
     * @param editedLocation the edited new location
     * @param startLocation the initial location to guess in which direction the point will be bounced back if both point equals
     * @param other the location from which a min distance must be kept
     * @return true if editedLocation was bounced back, else false
     */
    @objid ("3f260fdd-5214-475d-9cd8-a595cc762532")
    public boolean enforceMinDistance(Point editedLocation, Point startLocation, Point other) {
        int d = getCoord(other) - getCoord(editedLocation);
        
        if (d > -MIN_DIST && d < MIN_DIST) {
            // enforce minimal distance
            if (d == 0) {
                // point exactly on other, bounce toward startLocation
                int moveDelta = getCoord(editedLocation) - getCoord(startLocation);
                d = moveDelta;
            }
            if (d >= 0) {
                setCoord(editedLocation, getCoord(other) - MIN_DIST);
            } else {
                setCoord(editedLocation, getCoord(other) + MIN_DIST);
            }
            return true;
        }
        return false;
    }

    @objid ("72a7678c-1cad-48a0-b2ea-d7b80bb8ad4b")
    public static final OrientedAccessors forOrientation(Orientation o) {
        if (o==Orientation.HORIZONTAL)
            return H;
        if (o==Orientation.VERTICAL)
            return V;
        
        throw new IllegalArgumentException(String.valueOf(o));
        
    }

    /**
     * Accessors for the given point relative to the given rectangle.
     * @param p a point
     * @param r a rectangle
     * @return the matching accessors
     */
    @objid ("05b01b59-1d45-4ef8-a805-2855311039a3")
    public static final OrientedAccessors forPointAndRectangle(Point p, Rectangle r) {
        return forOrientation(GeomUtils.getDirection(p, r).orientation());
    }

    /**
     * Get the accessor for a segment.
     * <p>
     * This method logs a warning if the segment is not orthogonal.
     * @param p1 the first point
     * @param p2 the second point
     * @return the matching accessors
     */
    @objid ("1f684f6f-121e-4eed-b9d2-c0c293b45d83")
    public static final OrientedAccessors forSegment(Point p1, Point p2) {
        if (Math.abs(p1.x() - p2.x()) < 2)
            return V;
        if (Math.abs(p1.y() - p2.y()) < 2)
            return H;
        
        DiagramElements.LOG.warning(new IllegalArgumentException(String.format("Non orthogonal segment: %s - %s", p1, p2)));
        if (Direction.getMajor(p1, p2).orientation() == Orientation.VERTICAL) {
            return V;
        } else {
            return H;
        }
        
    }

    /**
     * Get the accessor for an orthogonal segment.
     * <p>
     * Returns null if the segment is not orthogonal.
     * @param p1 the first point
     * @param p2 the second point
     * @return the matching accessors
     */
    @objid ("3523cedf-dac7-425a-b36b-062d8d1fe9f5")
    public static final OrientedAccessors forSegmentOrNull(Point p1, Point p2) {
        if (p1.x() == p2.x())
            return V;
        if (p1.y() == p2.y())
            return H;
        return null;
    }

    /**
     * @param p a point
     * @return X or Y coordinate
     */
    @objid ("e766de01-29c4-4e51-9a0c-0d7a99688f0c")
    public int getCoord(Point p) {
        if (this.xcoord) {
            return p.x();
        } else {
            return p.y();
        }
        
    }

    /**
     * Get the point half way between p1 and p2, using only X or Y coordinates.
     * @param p1 a point
     * @param p2 another point
     * @return the point half way between p1 and p2.
     */
    @objid ("e7042fdb-8d70-4782-bc20-40fadcb7a56a")
    public int getMiddleCoord(Point p1, Point p2) {
        if (this.xcoord) {
            return p1.x() + (p2.x() - p1.x()) / 2;
        } else {
            return p1.y() + (p2.y() - p1.y()) / 2;
        }
        
    }

    @objid ("b6b05d4f-1d71-4915-be96-2311a7d2e655")
    public boolean isCoordContained(Point p, Rectangle r) {
        int a = getCoord(p);
        int c1 = this.xcoord ? r.x() : r.y();
        int c2 = this.xcoord ? r.right() : r.bottom();
        return a > c1 && a < c2;
    }

    /**
     * @return the perpendicular axis accessor
     */
    @objid ("e09c27f9-d8d0-49de-8ce2-48be816e2dd5")
    public AxisAccessor perpendicular() {
        if (this.xcoord)
            return Y;
        else
            return X;
        
    }

    /**
     * Align the edited point with the value.
     * @param edited the edited point
     * @param val the point to align to
     * @return the edited point for convenience
     */
    @objid ("f2baa92b-cd96-47ec-8a5d-602e7682eaff")
    public Point setCoord(Point edited, Point val) {
        if (this.xcoord) {
            edited.setX(val.x());
        } else {
            edited.setY(val.y());
        }
        return edited;
    }

    @objid ("3566129f-b891-46c1-9051-76af2cdbe509")
    public Point setCoord(Point edited, int val) {
        if (this.xcoord) {
            edited.setX(val);
        } else {
            edited.setY(val);
        }
        return edited;
    }

    @objid ("e30f92ac-03a3-4d29-98b1-776b3e1c2d9e")
    public boolean snapToPoint(Point edited, Point other) {
        int d = getCoord(other) - getCoord(edited);
        if (Math.abs(d) < SNAP_DIST) {
            setCoord(edited, other);
            return true;
        }
        return false;
    }

    @objid ("1e42cece-16f0-4902-b6f7-93c63e26d63f")
    private  AxisAccessor(final boolean xcoord) {
        this.xcoord = xcoord;
    }

    /**
     * Holder for accessors along and across an orthogonal line/segment.
     * @since 5.0.2
     */
    @objid ("0bcf4079-ffb8-4fe8-96c3-ce560288e910")
    public static class OrientedAccessors {
        /**
         * The segment orientation
         */
        @objid ("54f1b11d-dca5-406a-81e7-2d494f738076")
        public final Orientation orientation;

        /**
         * Accessor to edit coordinate along the segment
         */
        @objid ("35300ec8-a773-42b5-a298-78a623d94d41")
        public final AxisAccessor along;

        /**
         * Accessor to edit coordinate across the segment
         */
        @objid ("3e1e91ef-d254-4048-8bf7-a915f5791e84")
        public final AxisAccessor across;

        @objid ("bdb8d3fa-6acb-4c6b-8340-92fd44bafd40")
        protected  OrientedAccessors(Orientation orientation, AxisAccessor along, AxisAccessor accross) {
            this.orientation = orientation;
            this.along = along;
            this.across = accross;
            
        }

    }

}
