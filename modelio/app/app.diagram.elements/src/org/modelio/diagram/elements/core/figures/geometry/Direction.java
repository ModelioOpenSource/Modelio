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

    @objid ("b679d224-c575-4e1e-828a-a6151487d762")
    private Direction opposite;

    @objid ("2bda1ef9-d018-4a6e-b4bc-af531d1633fc")
    private Direction left;

    @objid ("929f7596-0ecb-43ef-946b-0fed5e27ac05")
    private Direction right;

    @objid ("5097b248-8c8a-4aa3-a659-487123c106f5")
    private Orientation orientation;

    /**
     * @return the opposite direction
     */
    @objid ("ae8a7edb-5ff5-44d5-9a8e-942316bf81b8")
    public Direction opposite() {
        return this.opposite;
    }

    /**
     * @return the direction at the left of this direction
     */
    @objid ("14caa42e-d5b0-4c99-b59b-ae5749709cd7")
    public Direction left() {
        return this.left;
    }

    /**
     * @return the direction at the right of this direction.
     */
    @objid ("ebe086e9-3ac9-4610-a7f2-0be39707862f")
    public Direction right() {
        return this.right;
    }

    /**
     * @return the {@link Orientation} of this direction
     */
    @objid ("ca06a5f8-5d4f-4061-a2d3-914c93fdfc6d")
    public Orientation orientation() {
        return this.orientation;
    }

    /**
     * Convert a {@link PositionConstants} constant to a Direction.
     * @param posConstant a {@link PositionConstants} constant.
     * @param defVal the value to return if the passed integer does not match any constant.
     * @return the matching direction
     */
    @objid ("6841a6a4-9b3a-4114-a7c4-bbdfc9f603e3")
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
    @objid ("e4d46140-5a28-4bf7-b86d-192229816af3")
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
    @objid ("016cd9db-fce9-4e18-9135-62ad2621f77e")
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
    @objid ("5232153f-8095-4d3d-a19a-96e6ca79fe84")
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
    @objid ("b4112c60-83e9-436a-8d1c-b9eeed7eb8c9")
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
    @objid ("81e1af1b-19f5-4805-a15f-fb7adbcd2929")
    public static class Pair {
        @objid ("b3bd8039-928b-4847-8206-a9bda609db37")
        private Direction major;

        @objid ("c573598c-89d6-485d-9e12-6bfaca64bf98")
        private Direction minor;

        /**
         * Shared instance for short computations.
         */
        @objid ("b73c905a-9b18-4903-b957-006fa6cbd7dd")
        public static final Pair SHARED = new Pair();

        /**
         * Constructor to reuse same instance.
         * @param source a point
         * @param target a point
         * @return this instance to chain call
         */
        @objid ("00020d84-a480-4e35-b30c-f8a19af4e879")
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
        @objid ("3b9935c8-8705-4602-821e-e2b6b5591f9a")
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
        @objid ("3ed8cd24-bf77-4506-acd8-f6acd39b921c")
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
        @objid ("6620a44e-2031-465c-a9b0-ba1a437c26dd")
        public Direction major() {
            return this.major;
        }

        /**
         * @return the minor direction
         */
        @objid ("e2f5ab3c-1795-486f-bd70-f7824d82d090")
        public Direction minor() {
            return this.minor;
        }

        /**
         * @return true both points were equal.
         */
        @objid ("c87b5250-83a0-4c7d-af8f-6e7f8adc87ee")
        public boolean isOverlap() {
            return this.major == NONE;
        }

        /**
         * @return true if the direction is orthogonal.
         */
        @objid ("3bddd6be-2477-4929-9a44-46b2e19fe819")
        public boolean isOrthogonal() {
            return ! isOverlap() && minor() == NONE;
        }

        /**
         * Return the direction parallel to the given pair major orientation.
         * @param other a direction pair
         * @return the direction parallel to the given pair major orientation.
         */
        @objid ("9ab41b77-338e-40b3-8f8e-1750f3e417c7")
        public Direction parallelOf(Pair other) {
            return parallelOf(other.major().orientation());
        }

        /**
         * Return the direction perpendicular to the given pair major orientation.
         * @param other a direction pair
         * @return the direction perpendicular to the given pair major orientation.
         */
        @objid ("d52216fa-7f89-4af8-8111-82a1ca3e8899")
        public Direction perpendicularOf(Pair other) {
            return perpendicularOf(other.major().orientation());
        }

    }

}
