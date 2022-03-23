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
package org.modelio.diagram.elements.core.figures.geometry;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Point;

/**
 * Indicates a direction.
 * 
 * @author cmarin
 */
@objid ("7f7e9e6a-1dec-11e2-8cad-001ec947c8cc")
public enum Direction {
    /**
     * Not defined
     */
    @objid ("7f7e9e6c-1dec-11e2-8cad-001ec947c8cc")
    NONE,
    /**
     * North
     */
    @objid ("7f7e9e6e-1dec-11e2-8cad-001ec947c8cc")
    NORTH,
    /**
     * East
     */
    @objid ("7f7e9e70-1dec-11e2-8cad-001ec947c8cc")
    EAST,
    /**
     * South
     */
    @objid ("7f7e9e72-1dec-11e2-8cad-001ec947c8cc")
    SOUTH,
    /**
     * West
     */
    @objid ("7f7e9e74-1dec-11e2-8cad-001ec947c8cc")
    WEST;

    @objid ("b390bc05-f99e-4f87-82bf-16272a291e33")
    private Direction opposite;

    @objid ("2ab84b3b-12f6-44e1-a0fa-1e7d62b8eb78")
    private Direction left;

    @objid ("4f6ed463-4eea-4a6f-a41e-7e11ee7b73ad")
    private Direction right;

    @objid ("98add04a-2b85-4670-b946-629efbc916ac")
    private Orientation orientation;

    /**
     * @return the opposite direction
     */
    @objid ("5c57a653-117f-425b-8b33-29c98642f7f7")
    public Direction opposite() {
        return this.opposite;
    }

    /**
     * @return the direction at the left of this direction
     */
    @objid ("c3b3d9c6-e8c7-4ada-a658-75676cfa6483")
    public Direction left() {
        return this.left;
    }

    /**
     * @return the direction at the right of this direction.
     */
    @objid ("44624114-e522-4c7d-ab34-bf88fdacaf5a")
    public Direction right() {
        return this.right;
    }

    /**
     * @return the {@link Orientation} of this direction
     */
    @objid ("8ca5cdca-450f-4f3b-a608-f8623bf7b91a")
    public Orientation orientation() {
        return this.orientation;
    }

    /**
     * Convert a {@link PositionConstants} constant to a Direction.
     * @param posConstant a {@link PositionConstants} constant.
     * @param defVal the value to return if the passed integer does not match any constant.
     * @return the matching direction
     */
    @objid ("951a8a5f-09aa-41e7-bed0-a60e63abf292")
    public static Direction fromPositionConstant(int posConstant, Direction defVal) {
        switch (posConstant) {
        case PositionConstants.EAST:
            return Direction.EAST;
        case PositionConstants.NORTH:
            return Direction.NORTH;
        case PositionConstants.SOUTH:
            return Direction.SOUTH;
        case PositionConstants.WEST:
            return Direction.WEST;
        case PositionConstants.NONE:
            return NONE;
        default:
            return defVal;
        }
        
    }

    /**
     * Get the exact direction from source to target if the segment is orthogonal.
     * <p>
     * Returns NONE if the segment is not orthogonal.
     * @param source the source point
     * @param target the target point
     * @return the direction from source to target or NONE.
     */
    @objid ("84546462-8487-4d54-8530-d6c88bfe5c84")
    public static Direction getOrtho(Point source, Point target) {
        if (source.y() == target.y()) {
            // horizontal
            if (source.x > target.x) {
                return Direction.WEST;
            } else {
                return Direction.EAST;
            }
        } else if (source.x() == target.x()) {
            // vertical
            if (source.y > target.y) {
                return Direction.NORTH;
            } else {
                return Direction.SOUTH;
            }
        }
        return Direction.NONE;
    }

    /**
     * Compute a best orthogonal direction from *source* to *target* .
     * <p>
     * Returns {@link #NONE} if the points are equals.
     * @param source a point
     * @param target a point
     * @return a direction from source to target
     */
    @objid ("b955b465-08ce-4694-a478-3d026368450e")
    public static Direction getMajor(Point source, Point target) {
        int dx = target.x() - source.x();
        int dy = target.y() - source.y();
        if (dx==0 && dy==0) {
            // points are equals
            return Direction.NONE;
        } else if (Math.abs(dx) > Math.abs(dy)) {
            if (dx > 0) {
                return Direction.EAST;
            } else {
                return Direction.WEST;
            }
        } else if (dy > 0) {
            return Direction.SOUTH;
        } else {
            return Direction.NORTH;
        }
        
    }

    /**
     * Compute a secondary orthogonal direction from *source* to *target* .
     * <p>
     * Returns {@link #NONE} if the segment formed by the two points is orthogonal.
     * @param source a point
     * @param target a point
     * @return a direction from source to target or NONE.
     */
    @objid ("2e8320e9-3f97-4e23-aae8-514b42ce2793")
    public static Direction getMinor(Point source, Point target) {
        int dx = target.x() - source.x();
        int dy = target.y() - source.y();
        if (Math.abs(dx) < Math.abs(dy)) {
            if (dx == 0) {
                return NONE;
            } else if (dx > 0) {
                return Direction.EAST;
            } else {
                return Direction.WEST;
            }
        } else if (dy == 0) {
            return Direction.NONE;
        } else if (dy > 0) {
            return Direction.SOUTH;
        } else {
            return Direction.NORTH;
        }
        
    }

    /**
     * Compute a secondary orthogonal direction from *source* to *target* .
     * <p>
     * Returns {@link #NONE} if the segment formed by the two points is orthogonal.
     * @param source a point
     * @param target a point
     * @return a direction from source to target or NONE.
     */
    @objid ("9731e0c3-1927-400b-b453-fef557cc82e4")
    public static Pair getPair(Point source, Point target) {
        return new Pair().init(source, target);
    }
static {
                        NONE.opposite = NONE;
                        NONE.left = NONE;
                        NONE.right = NONE;
                        NONE.orientation = Orientation.NONE;
    
                        NORTH.opposite = SOUTH;
                        NORTH.left = WEST;
                        NORTH.right = EAST;
                        NORTH.orientation = Orientation.VERTICAL;
    
                        EAST.opposite = WEST;
                        EAST.left = NORTH;
                        EAST.right = SOUTH;
                        EAST.orientation = Orientation.HORIZONTAL;
    
                        SOUTH.opposite = NORTH;
                        SOUTH.left = EAST;
                        SOUTH.right = WEST;
                        SOUTH.orientation = Orientation.VERTICAL;
    
                        WEST.opposite = EAST;
                        WEST.left = SOUTH;
                        WEST.right = NORTH;
                        WEST.orientation = Orientation.HORIZONTAL;
                    }
    
    /**
     * Holder for both the {@link Direction#getMajor(Point, Point)} and {@link Direction#getMinor(Point, Point)}.
     * <p>
     * {@link #major} and {@link #minor} are expected to be perpendicular or {@link Direction#NONE}.
     */
    @objid ("c2ab764e-d220-44e2-95e8-14150f5583a0")
    public static class Pair {
        @objid ("0b20eab8-acf2-4cb6-8df2-6bd676b9d304")
        private Direction major;

        @objid ("263b08a1-7126-4a2a-8494-a84f8d95939a")
        private Direction minor;

        /**
         * Shared instance for short computations.
         */
        @objid ("54b8f70f-eaac-4943-aebe-a3b9298c8ee6")
        public static final Pair SHARED = new Pair();

        /**
         * Constructor to reuse same instance.
         * @param source a point
         * @param target a point
         * @return this instance to chain call
         */
        @objid ("a0789459-ba9f-499d-9064-74d7993ccff5")
        public Pair init(Point source, Point target) {
            int dx = target.x() - source.x();
            int dy = target.y() - source.y();
            Direction dirx;
            Direction diry;
            
            if (dx == 0) {
                dirx =  NONE;
            } else if (dx > 0) {
                dirx = Direction.EAST;
            } else {
                dirx = Direction.WEST;
            }
            
            if (dy == 0) {
                diry = Direction.NONE;
            } else if (dy > 0) {
                diry = Direction.SOUTH;
            } else {
                diry = Direction.NORTH;
            }
            
            if (Math.abs(dx) < Math.abs(dy)) {
                this.major = diry;
                this.minor = dirx;
            } else {
                this.major = dirx;
                this.minor = diry;
            }
            return this;
        }

        /**
         * Return the direction perpendicular to the given orientation.
         * @param orientation an orientation
         * @return the direction perpendicular to the given orientation.
         */
        @objid ("e64f9f62-8a88-4c05-8aac-2b46185dcc2b")
        public Direction perpendicularOf(Orientation orientation) {
            if (this.major==NONE)
                return this.major;
            
            if (this.major.orientation() == orientation.getPerpendicular())
                return this.major;
            else
                return this.minor;
            
        }

        /**
         * Return the direction parallel to the given orientation.
         * @param orientation an orientation
         * @return the direction parallel to the given orientation.
         */
        @objid ("30e366fb-e9d1-47b9-9dfc-722da01d516b")
        public Direction parallelOf(Orientation orientation) {
            if (this.major==NONE)
                return this.major;
            
            if (this.major.orientation() == orientation)
                return this.major;
            else
                return this.minor;
            
        }

        /**
         * @return the major direction
         */
        @objid ("378ceeb4-0f11-4935-ae11-e5d549540acd")
        public Direction major() {
            return this.major;
        }

        /**
         * @return the minor direction
         */
        @objid ("38d9f1fc-39f3-4f23-b657-f3b90479aedd")
        public Direction minor() {
            return this.minor;
        }

        /**
         * @return true both points were equal.
         */
        @objid ("1013fbbc-8358-447e-ac4f-f885663e695f")
        public boolean isOverlap() {
            return this.major == NONE;
        }

        /**
         * @return true if the direction is orthogonal.
         */
        @objid ("1d1c4884-8262-4af9-8615-8723135c0bd8")
        public boolean isOrthogonal() {
            return ! isOverlap() && minor() == NONE;
        }

        /**
         * Return the direction parallel to the given pair major orientation.
         * @param other a direction pair
         * @return the direction parallel to the given pair major orientation.
         */
        @objid ("4b795850-408d-40b4-bf58-aab34f65a2fe")
        public Direction parallelOf(Pair other) {
            return parallelOf(other.major().orientation());
        }

        /**
         * Return the direction perpendicular to the given pair major orientation.
         * @param other a direction pair
         * @return the direction perpendicular to the given pair major orientation.
         */
        @objid ("326eeb9e-ef5a-427d-9cb5-31ddffcc965a")
        public Direction perpendicularOf(Pair other) {
            return perpendicularOf(other.major().orientation());
        }

    }

}
