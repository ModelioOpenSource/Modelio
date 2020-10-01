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

package org.modelio.diagram.elements.common.portcontainer;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.diagram.elements.common.portcontainer.PortConstraint.Border;

/**
 * Port container layouting helper
 * 
 * @author cmarin
 * @since 3.4
 */
@objid ("d9503174-9601-4c65-b037-337e3abd51c2")
public class PortContainerLayoutHelper {
    @objid ("1aaa06ba-f3ed-438a-868d-ec2c20cd54e2")
    private Rectangle mainNodeConstraint;

    @objid ("f8ebe79f-8d97-427d-af6a-568bbeb238ef")
    private Rectangle portContainerBounds;

    /**
     * @param portContainerBounds the port container bounds
     * @param mainNodeConstraint the main node bounds relative to the container top left.
     */
    @objid ("4d0d6c77-b1bf-42a6-a70f-43a3775493e7")
    public PortContainerLayoutHelper(Rectangle portContainerBounds, Rectangle mainNodeConstraint) {
        this.portContainerBounds = portContainerBounds;
        this.mainNodeConstraint = mainNodeConstraint;
    }

    /**
     * @param portContainerFigure a port container figure
     */
    @objid ("934b4119-65eb-47db-a78f-eefb7db23856")
    public PortContainerLayoutHelper(PortContainerFigure portContainerFigure) {
        PortContainerLayout layoutManager = portContainerFigure.getPortContainerLayout();
        
        this.portContainerBounds = portContainerFigure.getBounds();
        this.mainNodeConstraint = (Rectangle) layoutManager.getConstraint(portContainerFigure.getMainNodeFigure());
        
        if (this.mainNodeConstraint == null) {
            // If no constraint defined for main node yet, use its
            // preferred size at location (0, 0).
            this.mainNodeConstraint = new Rectangle().setSize(portContainerFigure.getMainNodeFigure().getPreferredSize());
        }
    }

    /**
     * Complete the given rectangle with the child preferred size if width or height is not specified.
     * <p>
     * Warn: The given rectangle is directly modified ! The already specified width and height are passed as hints to get preferred size.
     * 
     * @param init the rectangle to complete
     * @param child the figure
     * @return the same rectangle, modified or not.
     */
    @objid ("0e275975-8883-4b87-9f78-cfddc2c4cccc")
    public static Rectangle completeWithPrefSize(final Rectangle init, final IFigure child) {
        Rectangle r = init;
        if (r.width == -1 || r.height == -1) {
            Dimension childPreferredSize = child.getPreferredSize(init.width, init.height);
            if (r.width == -1) {
                r.width = childPreferredSize.width;
            }
            if (r.height == -1) {
                r.height = childPreferredSize.height;
            }
        }
        return r;
    }

    /**
     * Returned a "fixed" copy of the requested bounds on the reference border.
     * 
     * @param child the child figure.
     * @param portConstraint the constraint.
     * @return the fixed bounds.
     */
    @objid ("722f176e-d443-44d2-bd8c-b68555b7ff5a")
    public Rectangle convertPortConstraint(IFigure child, PortConstraint portConstraint) {
        Rectangle requestBounds = portConstraint.getRequestedBounds();
        
        // If not yet defined, determine the reference border.
        if (Border.Undefined == portConstraint.getReferenceBorder()) {
            fixReferenceBorder(portConstraint);
        }
        
        // If using preferred size, get it.
        requestBounds = completeWithPrefSize(requestBounds, child);
        
        // Get the main node constraint, so that we can "stick" to it.
        // If not found, no way to fix, return requested bounds "as is".
        Rectangle lMainNodeConstraint = getMainNodeConstraint();
        if (lMainNodeConstraint == null) {
            return portConstraint.getRequestedBounds();
        }
        
        // Determine the "real" centre
        Point realCenter;
        Point requestedCenter;
        if (portConstraint.getRequestedCenter() != null) {
            requestedCenter = portConstraint.getRequestedCenter();
        } else {
            requestedCenter = requestBounds.getCenter();
        }
        
        switch (portConstraint.getReferenceBorder()) {
        case North: {
            realCenter = lMainNodeConstraint.getTop();
            realCenter.x = requestedCenter.x;
            break;
        }
        case South: {
            realCenter = lMainNodeConstraint.getBottom();
            realCenter.x = requestedCenter.x;
            break;
        }
        case East: {
            realCenter = lMainNodeConstraint.getRight();
            realCenter.y = requestedCenter.y;
            break;
        }
        case West: {
            realCenter = lMainNodeConstraint.getLeft();
            realCenter.y = requestedCenter.y;
            break;
        }
        case NorthEast:
            realCenter = lMainNodeConstraint.getTopRight();
            break;
        case NorthWest:
            realCenter = lMainNodeConstraint.getTopLeft();
            break;
        case SouthEast:
            realCenter = lMainNodeConstraint.getBottomRight();
            break;
        case SouthWest:
            realCenter = lMainNodeConstraint.getBottomLeft();
            break;
        case Undefined:
            realCenter = lMainNodeConstraint.getTopLeft();
            break;
        default: {
            // Could not defined the reference border, no way to fix, return
            // requested bounds "as is".
            return portConstraint.getRequestedBounds();
        }
        }
        
        // Make sure realCenter is inside the bounds.
        realCenter.x = Math.max(realCenter.x, lMainNodeConstraint.x);
        realCenter.x = Math.min(realCenter.x, lMainNodeConstraint.x + lMainNodeConstraint.width);
        realCenter.y = Math.max(realCenter.y, lMainNodeConstraint.y);
        realCenter.y = Math.min(realCenter.y, lMainNodeConstraint.y + lMainNodeConstraint.height);
        Rectangle fixedBounds = requestBounds.getTranslated(realCenter.x - requestedCenter.x,
                realCenter.y - requestedCenter.y);
        return fixedBounds;
    }

    /**
     * Determine which border is closest of the center of the passed rectangle.
     * <p>
     * The rectangle must be relative to the {@link #getLayoutOrigin(IFigure)}.
     * 
     * @param rectConstraint the bounds to test.
     * @return the border closest to the center of the passed rectangle.
     */
    @objid ("7efb7d44-1dec-11e2-8cad-001ec947c8cc")
    public Border determineReferenceBorder(Rectangle rectConstraint) {
        // What the constraint want
        Point requestedCenter = rectConstraint.getCenter();
        return determineReferenceBorder(requestedCenter);
    }

    /**
     * Determine which border is closest of the center of the passed rectangle.
     * <p>
     * The rectangle must be in the port container figure coordinates.
     * 
     * @param bounds the bounds to test.
     * @return the border closest to the center of the passed rectangle.
     */
    @objid ("f9af0135-3ea7-4fe7-93b8-59749e4965df")
    public Border determineReferenceBorderFromBounds(Rectangle bounds) {
        Point requestedCenter = bounds.getCenter();
        // convert to layout relative
        requestedCenter.translate(-this.portContainerBounds.x, -this.portContainerBounds.y);
        return determineReferenceBorder(requestedCenter);
    }

    /**
     * Determine and set the port constraint reference border from the container bounds.
     * @param portConstraint
     */
    @objid ("4d377559-32d2-4428-8af1-5c8ac7e2d0af")
    public void fixReferenceBorder(final PortConstraint portConstraint) {
        if (portConstraint.getRequestedCenter() != null) {
            portConstraint.setReferenceBorder(determineReferenceBorder(portConstraint.getRequestedCenter()));
        } else {
            portConstraint.setReferenceBorder(determineReferenceBorder(portConstraint.getRequestedBounds()));
        }
    }

    /**
     * Returns the origin for the given figure.
     * 
     * @param parent the figure whose origin is requested
     * @return the origin
     */
    @objid ("9068b33f-1803-408d-ba66-751443d9ffe6")
    public static Point getLayoutOrigin(IFigure parent) {
        return parent.getClientArea().getLocation();
    }

    /**
     * Computes the bounds in coordinates relative to the {@link #portContainerBounds parent top left} that covers every children.
     * 
     * @param container the figure for which to compute preferred rectangle.
     * @param layoutManager the layout manager to use
     * @return the bounds in coordinates relative to the parent top left that covers every children.
     */
    @objid ("d4f9492d-3e41-4355-8542-14697965e45a")
    public Rectangle getPreferredRect(PortContainerFigure container, LayoutManager layoutManager) {
        Rectangle rect = null;
        
        for (Object childObj : container.getChildren()) {
            IFigure child = (IFigure) childObj;
            Object childConstraint = layoutManager.getConstraint(child);
        
            if (child.equals(container.getMainNodeFigure())) {
                childConstraint = getMainNodeConstraint();
                if (childConstraint == null) {
                    // If no constraint defined for main node yet, use its
                    // preferred size at location (0, 0).
                    childConstraint = new Rectangle().setSize(child.getPreferredSize());
                }
            }
        
            if (childConstraint != null) {
                Rectangle r = getRectFromConstraint(childConstraint, child);
                if (r == null) {
                    // skip
                } else if (rect == null) {
                    rect = r.getCopy();
                } else {
                    rect.union(r);
                }
            }
        }
        return rect;
    }

    /**
     * Convert a port layout constraint to bounds the figure should take. The bounds are relative to the container origin.
     * 
     * @param childConstraint the constraint : Rectangle, Integer or PortConstraint.
     * @param child the child figure
     * @return the new child figure coords.
     */
    @objid ("6e8d0f67-3359-4b5f-ba3f-a086dff6b012")
    public Rectangle getRectFromConstraint(final Object childConstraint, final IFigure child) {
        Rectangle r;
        if (childConstraint instanceof Rectangle) {
            r = (Rectangle) childConstraint;
        
            r = getCompletedWithPrefSize(r, child);
        } else if (childConstraint instanceof Integer) {
            r = convertIntConstraint(child, (Integer) childConstraint);
        } else if (childConstraint instanceof PortConstraint) {
            r = convertPortConstraint(child, (PortConstraint) childConstraint);
        } else {
            // Create a default constraint to avoid exceptions
            r = new Rectangle(0, 0, -1, -1);
            r = getCompletedWithPrefSize(r, child);
        }
        return r;
    }

    /**
     * Computes the initial location of a satellite based on the bounds of the main node, a placement constraint expressed as a value from {@link PositionConstants} and the preferred size of the satellite.
     * 
     * @param mainNodeBounds the bounds of the main node.
     * @param placement a placement constraint expressed as a value from {@link PositionConstants}. Can be either {@link PositionConstants#SOUTH_EAST}, {@link PositionConstants#SOUTH}, {@link PositionConstants#SOUTH_WEST}, {@link PositionConstants#WEST},
     * {@link PositionConstants#NORTH_WEST}, {@link PositionConstants#NORTH}, {@link PositionConstants#NORTH_EAST} or {@link PositionConstants#EAST} which is the default.
     * @param childPrefSize the preferred size of the satellite
     * @return the initial location of the satellite.
     */
    @objid ("10c646e2-a5b8-443b-b1e4-9b4a53d759e3")
    private static Point computeSatelliteInitialLocation(Rectangle mainNodeBounds, int placement, Dimension childPrefSize) {
        switch (placement) {
        case PositionConstants.SOUTH_EAST:
            return mainNodeBounds.getBottomRight();
        
        case PositionConstants.SOUTH:
            return mainNodeBounds.getBottom().translate(-childPrefSize.width / 2, 0);
        
        case PositionConstants.SOUTH_WEST:
            return mainNodeBounds.getBottomLeft().translate(-childPrefSize.width, 0);
        
        case PositionConstants.WEST:
            return mainNodeBounds.getLeft().translate(-childPrefSize.width,
                    -childPrefSize.height / 2);
        case PositionConstants.NORTH_WEST:
            return mainNodeBounds.getTopLeft().translate(-childPrefSize.width,
                    -childPrefSize.height);
        
        case PositionConstants.NORTH:
            return mainNodeBounds.getTop().translate(-childPrefSize.width / 2,
                    -childPrefSize.height);
        
        case PositionConstants.NORTH_EAST:
            return mainNodeBounds.getTopRight().translate(0, -childPrefSize.height);
        
        case PositionConstants.EAST:
        default:
            return mainNodeBounds.getRight().translate(0, -childPrefSize.height / 2);
        }
    }

    /**
     * Converts an Integer (interpreted as a value from {@link PositionConstants} to a Rectangle.
     * 
     * @param child the child figure
     * @param constraint its layout constraint
     * @return the new rectangle constraint.
     */
    @objid ("157ba1f7-da86-4f57-af67-9754e531b4bc")
    public Rectangle convertIntConstraint(IFigure child, Integer constraint) {
        // Let's suppose that it is a placement constraint from
        // PositionConstants, and we'll place the child around the main
        // node's figure.
        
        // Get the main node constraint, so that we can "stick" to it.
        // If not found, no way to fix, return requested bounds "as is".
        Rectangle mainNodeBounds = getMainNodeConstraint();
        if (mainNodeBounds == null) {
            mainNodeBounds = new Rectangle(0, 0, 0, 0);
        }
        
        // 2 - define a constraint around the main node bounds (default to EAST).
        Dimension childPreferredSize = child.getPreferredSize();
        Point loc = computeSatelliteInitialLocation(mainNodeBounds, constraint, childPreferredSize);
        Rectangle newConstraint = new Rectangle(loc, childPreferredSize);
        return newConstraint;
    }

    /**
     * Determine which border is closest of the passed point.
     * @param container the reference container
     * 
     * @param requestedCenter the point to test.
     * @return the border closest to the center of the passed rectangle.
     */
    @objid ("3e9c30cc-bae9-4ce8-abff-13d39a7d2e51")
    private Border determineReferenceBorder(Point requestedCenter) {
        double height = this.portContainerBounds.height;
        double width = this.portContainerBounds.width;
        double ratio = height / width;
        Rectangle lMainNodeConstraint = getMainNodeConstraint();
        // If not found, no way to fix, return requested bounds "as is".
        if (lMainNodeConstraint == null) {
            return Border.Undefined;
        }
        
        Point mainNodeCenter = lMainNodeConstraint.getCenter();
        
        // Determine in which NESW "quadrant" the requested centre point is.
        double x = requestedCenter.x - mainNodeCenter.x;
        double y = requestedCenter.y - mainNodeCenter.y;
        if ((x * ratio) >= y) {
            // NE quadrant
            if ((x * ratio) >= (-y)) {
                // E quadrant
                return Border.East;
            } else {
                // N quadrant
                return Border.North;
            }
        }
        // SW quadrant
        if ((x * ratio) >= (-y)) {
            // S quadrant
            return Border.South;
        } else {
            // W quadrant
            return Border.West;
        }
    }

    @objid ("c51f01b3-4622-41bb-b17c-207e950a7b0e")
    private Rectangle getMainNodeConstraint() {
        return this.mainNodeConstraint;
    }

    /**
     * Convert a {@link Border} constraint for a child figure to a {@link PortConstraint} with default values.
     * 
     * @param child a child figure to layout as port
     * @param border a border
     * @return a default port constraint.
     */
    @objid ("bc2fc483-4dad-49cb-845f-c5bf84e8a33d")
    public static PortConstraint convertToPortConstraint(IFigure child, Border border) {
        Rectangle requestBounds = new Rectangle().setSize(child.getPreferredSize());
        
        PortConstraint pc = new PortConstraint();
        pc.setReferenceBorder(border);
        pc.setRequestedBounds(requestBounds);
        return pc;
    }

    /**
     * Complete the given rectangle with the child preferred size if width or height is not specified.
     * <p>
     * If the rectangle is already complete it is returned as is. In the other case a completed copy is returned. The already specified width and height are then passed as hints to get preferred size.
     * 
     * @param init the rectangle to complete
     * @param child the figure
     * @return the same rectangle or a completed copy.
     */
    @objid ("f8ae225d-670f-4394-8788-c3f86256842d")
    public static Rectangle getCompletedWithPrefSize(final Rectangle init, final IFigure child) {
        Rectangle r = init;
        if (r.width == -1 || r.height == -1) {
            Dimension childPreferredSize = child.getPreferredSize(init.width, init.height);
            r = r.getCopy();
            if (r.width == -1) {
                r.width = childPreferredSize.width;
            }
            if (r.height == -1) {
                r.height = childPreferredSize.height;
            }
        }
        return r;
    }

    /**
     * Get the border on which a figure with the given constraint is.
     * 
     * @param constraint the {@link PortConstraint} or {@link PortConstraint.Border}.
     * @return the {@link PortConstraint.Border}
     * @throws java.lang.ClassCastException if the constraint is not a port or border.
     */
    @objid ("f87cccd3-6b7c-441d-a866-c241b1dab3d4")
    public static org.modelio.diagram.elements.common.portcontainer.PortConstraint.Border getReferenceBorder(Object constraint) throws ClassCastException {
        if (constraint == null) {
            return null;
        } else if (constraint instanceof PortConstraint.Border) {
            return (PortConstraint.Border) constraint;
        } else {
            return ((PortConstraint) constraint).getReferenceBorder();
        }
    }

}
