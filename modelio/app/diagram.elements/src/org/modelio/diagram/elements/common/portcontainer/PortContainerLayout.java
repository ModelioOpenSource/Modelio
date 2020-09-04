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

package org.modelio.diagram.elements.common.portcontainer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.AbstractLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.diagram.elements.common.portcontainer.PortConstraint.Border;

/**
 * Specialisation of the {@link XYLayout} class that allows to ask for preferred bounds (rather than just preferred size).
 * 
 * @author fpoyer
 */
@objid ("7ef91ad1-1dec-11e2-8cad-001ec947c8cc")
public class PortContainerLayout extends AbstractLayout {
    @objid ("7ef91ae3-1dec-11e2-8cad-001ec947c8cc")
    private static final int MAINNODE_MINIMUM_SIZE = 8;

    /**
     * The layout constraints. The constraints may be either Rectangle for the main node or a pair <Rectangle, Border> for the ports.
     */
    @objid ("7ef91ad5-1dec-11e2-8cad-001ec947c8cc")
    private Map<IFigure, Object> constraints = new HashMap<>();

    /**
     * The cached minimum size.
     */
    @objid ("fb9cadec-c095-4f25-adec-f4864eedc1bd")
    private Dimension minimumSize;

    @objid ("cd7a6e8f-c55c-4a77-bd6e-081ec09e3358")
    private IFigure mainNodeFigure;

    @objid ("7ef91ae5-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Object getConstraint(IFigure figure) {
        return this.constraints.get(figure);
    }

    /**
     * Returns the current constraint for the mainNodeFigure.
     * @return the current constraint for the mainNodeFigure if found, <code>null</code> otherwise.
     */
    @objid ("7ef91aed-1dec-11e2-8cad-001ec947c8cc")
    public Rectangle getMainNodeConstraint() {
        // Look for the "bounds" of the main node.
        Rectangle mainNodeConstraint = null;
        if (this.mainNodeFigure != null) {
            mainNodeConstraint = (Rectangle) this.constraints.get(this.mainNodeFigure);
        }
        
        // If not found, return null.
        if (mainNodeConstraint == null) {
            return null;
        }
        
        // If found but using preferred size, get it.
        if (mainNodeConstraint.width == -1 || mainNodeConstraint.height == -1) {
            mainNodeConstraint = mainNodeConstraint.getCopy();
            Dimension mainNodePrefSize = this.mainNodeFigure.getPreferredSize(mainNodeConstraint.width,
                    mainNodeConstraint.height);
            if (mainNodeConstraint.width == -1) {
                mainNodeConstraint.width = mainNodePrefSize.width;
            }
            if (mainNodeConstraint.height == -1) {
                mainNodeConstraint.height = mainNodePrefSize.height;
            }
        }
        return mainNodeConstraint;
    }

    @objid ("7ef91af4-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Dimension getMinimumSize(final IFigure container, final int wHint, final int hHint) {
        if (this.minimumSize == null) {
            calculateMinimumSize(container);
        }
        return this.minimumSize;
    }

    @objid ("7efb7d26-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void invalidate() {
        this.minimumSize = null;
        super.invalidate();
    }

    /**
     * Implements the algorithm to layout the components of the given container figure. Each component is laid out using its own layout constraint specifying its size and position.
     */
    @objid ("7efb7d29-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void layout(IFigure parent) {
        Point offset = getOrigin(parent);
        Object mainNodeOriginalConstraint = null;
        IFigure child;
        for (Object childObj : parent.getChildren()) {
            child = (IFigure) childObj;
            Object childConstraint = this.constraints.get(child);
            if (child.equals(this.mainNodeFigure)) {
                if (childConstraint == null) {
                    // If no constraint defined for main node yet, use its
                    // parent size at location (0, 0).
                    childConstraint = new Rectangle(new Point(0, 0), parent.getSize());
                } else {
                    mainNodeOriginalConstraint = childConstraint;
                    childConstraint = fixMainNodeConstraint(parent, (Rectangle) childConstraint);
                }
                setConstraint(child, childConstraint);
            }
            if (childConstraint == null) {
                // no constraint for this child yet, do not move it and resize it to its preferred size (this is only a temporary state).
                child.setSize(child.getPreferredSize());
                continue;
            }
            Rectangle bounds = convertConstraintToRectangle(childConstraint, parent, child);
            bounds = bounds.getTranslated(offset);
            child.setBounds(bounds);
        }
        if (mainNodeOriginalConstraint != null) {
            setConstraint(this.mainNodeFigure, mainNodeOriginalConstraint);
        }
    }

    @objid ("7efb7d30-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void remove(IFigure figure) {
        super.remove(figure);
        this.constraints.remove(figure);
    }

    /**
     * Sets the layout constraint of the given figure. The constraints can only be of type {@link Rectangle}.
     */
    @objid ("7efb7d36-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setConstraint(IFigure figure, Object newConstraint) {
        super.setConstraint(figure, newConstraint);
        if (newConstraint != null &&
                !(newConstraint instanceof Integer) &&
                !(newConstraint instanceof Border)) {
            this.constraints.put(figure, newConstraint);
        }
    }

    /**
     * Sets the main node figure.
     * @param mainNodeFigure the main node figure.
     */
    @objid ("7efb7d3e-1dec-11e2-8cad-001ec947c8cc")
    public void setMainNodeFigure(IFigure mainNodeFigure) {
        this.mainNodeFigure = mainNodeFigure;
    }

    /**
     * Calculates and returns the preferred size of the input figure. Since in XYLayout the location of the child should be preserved, the preferred size would be a region which would hold all the children of the input figure. If no constraint is set, that
     * child is ignored for calculation. If width and height are not positive, the preferred dimensions of the child are taken.
     * @see AbstractLayout#calculatePreferredSize(IFigure, int, int)
     * @since 2.0
     */
    @objid ("7efb7d57-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Dimension calculatePreferredSize(IFigure f, int wHint, int hHint) {
        Rectangle rect = null;
        Rectangle mainNodeConstraint = getMainNodeConstraint();
        for (Object childObj : f.getChildren()) {
            IFigure child = (IFigure) childObj;
            Object childConstraint = getConstraint(child);
            if (child.equals(this.mainNodeFigure)) {
                childConstraint = getMainNodePreferredSize(child);
                // Temporarily alter constraint for mainNode so that ports are correctly placed.
                this.constraints.put(this.mainNodeFigure, childConstraint);
            }
            if (childConstraint == null) {
                // no constraint for this child yet, ignore it
                // completely
                continue;
            }
        
            Rectangle r = convertConstraintToRectangle(childConstraint, f, child);
            if (rect == null) {
                rect = r.getCopy();
            } else {
                rect.union(r);
            }
        }
        if (rect == null) {
            // Should never happen, but better safe than sorry
            return new Dimension(0, 0);
        } else {
            Dimension d = rect.getSize();
            Insets insets = f.getInsets();
            // Restore mainNode constraint to its original value.
            this.constraints.put(this.mainNodeFigure, mainNodeConstraint);
            // Take insets and border into account.
            return new Dimension(d.width + insets.getWidth(), d.height + insets.getHeight()).union(getBorderPreferredSize(f));
        }
        // Rectangle rect = new Rectangle();
        // for (Object childObj : f.getChildren()) {
        // IFigure child = (IFigure) childObj;
        // Object childConstraint = this.constraints.get(child);
        // if (childConstraint == null)
        // if (child.equals(this.mainNodeFigure)) {
        // // If no constraint defined for main node yet, use its
        // // preferred size at location (0, 0).
        // childConstraint = new Rectangle(new Point(0, 0), child.getPreferredSize());
        // } else {
        // // else: no constraint for this child yet, ignore it
        // // completely
        // continue;
        // }
        // Rectangle r = convertConstraintToRectangle(childConstraint, f, child);
        // rect.union(r);
        // }
        // Dimension d = rect.getSize();
        // Insets insets = f.getInsets();
        // return new Dimension(d.width + insets.getWidth(), d.height + insets.getHeight()).union(getBorderPreferredSize(f));
    }

    /**
     * @return the main node figure if set, <code>null</code> otherwise.
     */
    @objid ("7efddf85-1dec-11e2-8cad-001ec947c8cc")
    protected IFigure getMainNodeFigure() {
        return this.mainNodeFigure;
    }

    @objid ("7efddf8c-1dec-11e2-8cad-001ec947c8cc")
    private void calculateMinimumSize(final IFigure container) {
        Rectangle rect = new Rectangle();
        Rectangle mainNodeConstraint = getMainNodeConstraint();
        for (Object childObj : container.getChildren()) {
            IFigure child = (IFigure) childObj;
            Object childConstraint = getConstraint(child);
            if (child.equals(this.mainNodeFigure)) {
                childConstraint = getMainNodeMinimumSize(child);
                // Temporarily alter constraint for mainNode so that ports are correctly placed.
                this.constraints.put(this.mainNodeFigure, childConstraint);
            }
            if (childConstraint == null) {
                // no constraint for this child yet, ignore it
                // completely
                continue;
        
            }
            Rectangle r = convertConstraintToRectangle(childConstraint, container, child);
            rect.union(r);
        }
        Dimension d = rect.getSize();
        Insets insets = container.getInsets();
        // Restore mainNode constraint to its original value.
        this.constraints.put(this.mainNodeFigure, mainNodeConstraint);
        // Take insets and border into account.
        this.minimumSize = new Dimension(d.width + insets.getWidth(), d.height + insets.getHeight()).union(getBorderPreferredSize(container));
    }

    @objid ("7efddfb9-1dec-11e2-8cad-001ec947c8cc")
    private Object getMainNodeMinimumSize(final IFigure mainNode) {
        Rectangle mainNodeConstraint = getMainNodeConstraint();
        if (mainNodeConstraint == null) {
            // If no constraint defined for main node yet, use its
            // preferred size at location (0, 0).
            return new Rectangle(new Point(0, 0), mainNode.getMinimumSize());
        } else {
            // Temporarily modify the mainNode constraint
            return mainNodeConstraint.getCopy().setSize(mainNode.getMinimumSize());
        }
    }

    @objid ("7f0041cb-1dec-11e2-8cad-001ec947c8cc")
    private Object getMainNodePreferredSize(final IFigure mainNode) {
        Rectangle mainNodeConstraint = getMainNodeConstraint();
        if (mainNodeConstraint == null) {
            // If no constraint defined for main node yet, use its
            // preferred size at location (0, 0).
            return new Rectangle(new Point(0, 0), mainNode.getPreferredSize());
        } else {
            // Temporarily modify the mainNode constraint
            return mainNodeConstraint.getCopy().setSize(mainNode.getPreferredSize());
        }
    }

    /**
     * Returns the origin for the given figure.
     * @param parent the figure whose origin is requested
     * @return the origin
     */
    @objid ("7ef91b03-1dec-11e2-8cad-001ec947c8cc")
    public Point getOrigin(IFigure parent) {
        return parent.getClientArea().getLocation();
    }

    /**
     * Computes the bounds in coordinates relative to the parent that covers every children.
     * @param container the figure for which to compute bounds.
     * @return the bounds in coordinates relative to the parent that covers every children.
     */
    @objid ("80c076e4-2c71-4356-91e0-68b2fa936ba5")
    public Rectangle getPreferredBounds(IFigure container) {
        Rectangle rect = null;
        for (Object childObj : container.getChildren()) {
            IFigure child = (IFigure) childObj;
            Object childConstraint = getConstraint(child);
            if (childConstraint == null) {
                if (child.equals(this.mainNodeFigure)) {
                    // If no constraint defined for main node yet, use its
                    // preferred size at location (0, 0).
                    childConstraint = new Rectangle(new Point(0, 0), child.getPreferredSize());
                } else {
                    // else: no constraint for this child yet, ignore it
                    // completely
                    continue;
                }
        
            }
            Rectangle r = convertConstraintToRectangle(childConstraint, container, child);
            if (rect == null) {
                rect = r.getCopy();
            } else {
                rect.union(r);
            }
        }
        // Put in the coordinates system of the parent
        if (rect != null) {
            rect.translate(getOrigin(container));
        }
        return rect;
    }

    /**
     * Get the border on which the given port child figure is.
     * @param portFigure the child figure
     * @return the border
     */
    @objid ("3caaecf9-eba9-42aa-b77b-12ff96b44a16")
    public org.modelio.diagram.elements.common.portcontainer.PortConstraint.Border getReferenceBorder(IFigure portFigure) {
        Object c = getConstraint(portFigure);
        if (c == null) {
            return null;
        } else if (c instanceof PortConstraint.Border) {
            return (PortConstraint.Border) c;
        } else {
            return ((PortConstraint) c).getReferenceBorder();
        }
    }

    /**
     * Determine which border is closest of the centre of the passed rectangle.
     * @param container the reference container.
     * @param requestedBounds the bounds to test.
     * @return the border closest to the centre of the passed rectangle.
     */
    @objid ("494da9e8-326b-40b4-b7ac-156388d64b7c")
    Border determineReferenceBorder(IFigure container, Rectangle requestedBounds) {
        // What the constraint want
        Point requestedCenter = requestedBounds.getCenter();
        return determineReferenceBorder(container, requestedCenter);
    }

    @objid ("7887c33b-c8dd-4c69-9800-8d2411b7b602")
    void determineReferenceBorder(final IFigure container, final PortConstraint portConstraint) {
        if (portConstraint.getRequestedCenter() != null) {
            portConstraint.setReferenceBorder(determineReferenceBorder(container,
                    portConstraint.getRequestedCenter()
                            .getCopy()));
        } else {
            portConstraint.setReferenceBorder(determineReferenceBorder(container,
                    portConstraint.getRequestedBounds()));
        }
    }

    /**
     * Returned a "fixed" copy of the requested bounds on the reference border.
     * @param child the child figure.
     * @param portConstraint the constraint.
     * @return the fixed bounds.
     */
    @objid ("251b6453-5942-452d-a2d1-c22c1421da25")
    protected Rectangle getCorrectedRectangle(IFigure container, IFigure child, PortConstraint portConstraint) {
        Rectangle requestBounds = portConstraint.getRequestedBounds();
        
        // If not yet defined, determine the reference border.
        if (Border.Undefined == portConstraint.getReferenceBorder()) {
            determineReferenceBorder(container, portConstraint);
        }
        
        // If using preferred size, get it.
        if (requestBounds.width == -1 || requestBounds.height == -1) {
            requestBounds = requestBounds.getCopy();
            Dimension childPrefSize = child.getPreferredSize(requestBounds.width, requestBounds.height);
            if (requestBounds.width == -1) {
                requestBounds.width = childPrefSize.width;
            }
            if (requestBounds.height == -1) {
                requestBounds.height = childPrefSize.height;
            }
        }
        
        // Get the main node constraint, so that we can "stick" to it.
        // If not found, no way to fix, return requested bounds "as is".
        Rectangle mainNodeConstraint = getMainNodeConstraint();
        if (mainNodeConstraint == null) {
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
            realCenter = mainNodeConstraint.getTop();
            realCenter.x = requestedCenter.x;
            break;
        }
        case South: {
            realCenter = mainNodeConstraint.getBottom();
            realCenter.x = requestedCenter.x;
            break;
        }
        case East: {
            realCenter = mainNodeConstraint.getRight();
            realCenter.y = requestedCenter.y;
            break;
        }
        case West: {
            realCenter = mainNodeConstraint.getLeft();
            realCenter.y = requestedCenter.y;
            break;
        }
        case NorthEast:
            realCenter = mainNodeConstraint.getTopRight();
            break;
        case NorthWest:
            realCenter = mainNodeConstraint.getTopLeft();
            break;
        case SouthEast:
            realCenter = mainNodeConstraint.getBottomRight();
            break;
        case SouthWest:
            realCenter = mainNodeConstraint.getBottomLeft();
            break;
        case Undefined:
            realCenter = mainNodeConstraint.getTopLeft();
            break;
        default: {
            // Could not defined the reference border, no way to fix, return
            // requested bounds "as is".
            return portConstraint.getRequestedBounds();
        }
        }
        
        // Make sure realCenter is inside the bounds.
        realCenter.x = Math.max(realCenter.x, mainNodeConstraint.x);
        realCenter.x = Math.min(realCenter.x, mainNodeConstraint.x + mainNodeConstraint.width);
        realCenter.y = Math.max(realCenter.y, mainNodeConstraint.y);
        realCenter.y = Math.min(realCenter.y, mainNodeConstraint.y + mainNodeConstraint.height);
        Rectangle fixedBounds = requestBounds.getTranslated(realCenter.x - requestedCenter.x,
                realCenter.y - requestedCenter.y);
        return fixedBounds;
    }

    @objid ("22136653-0bf9-4661-b2ae-c7fce3208a1e")
    private Rectangle convertConstraintToRectangle(final Object childConstraint, final IFigure container, final IFigure child) {
        Rectangle r;
        if (childConstraint instanceof Rectangle) {
            r = (Rectangle) childConstraint;
        } else {
            r = getCorrectedRectangle(container, child, (PortConstraint) childConstraint);
        }
        
        if (r.width == -1 || r.height == -1) {
            Dimension childPreferredSize = child.getPreferredSize(r.width, r.height);
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
     * Determine which border is closest of the passed point.
     * @param container the reference container
     * @param requestedCenter the point to test.
     * @return the border closest to the centre of the passed rectangle.
     */
    @objid ("622b486a-76b9-4d45-abe9-df7a8272cbb1")
    private Border determineReferenceBorder(IFigure container, Point requestedCenter) {
        Rectangle mainNodeConstraint = getMainNodeConstraint();
        // If not found, no way to fix, return requested bounds "as is".
        if (mainNodeConstraint == null) {
            return Border.Undefined;
        }
        
        double height = mainNodeConstraint.height;
        double width = mainNodeConstraint.width;
        double ratio = height / width;
        
        requestedCenter.translate(mainNodeConstraint.getCenter().getNegated());
        // Determine in which NESW "quadrant" the requested centre point is.
        double x = requestedCenter.x;
        double y = requestedCenter.y;
        if ((x * ratio) >= y) {
            // NE quadrant
            if ((x * ratio) >= (-y)) {
                // E quadrant
                return Border.East;
            }
            // else
            // N quadrant
            return Border.North;
        }
        // else
        // SW quadrant
        if ((x * ratio) >= (-y)) {
            // S quadrant
            return Border.South;
        }
        // else
        // W quadrant
        return Border.West;
    }

    @objid ("5c50afca-b2e9-4175-841d-bf6295ac33ed")
    private Object fixMainNodeConstraint(final IFigure container, final Rectangle mainNodeConstraint) {
        Rectangle fixedRect = mainNodeConstraint.getCopy();
        /*
         * Dimension parentSize = container.getSize(); Dimension parentPrefSize = getPreferredBounds(container).getSize(); if (parentSize.width < parentPrefSize.width) { // Parent's wish is not granted: we should fix the // mainNodeSize fixedRect =
         * fixedRect.getResized(parentSize.width - parentPrefSize.width, 0); if (fixedRect.width < MAINNODE_MINIMUM_SIZE) { fixedRect.width = MAINNODE_MINIMUM_SIZE; } } if (parentSize.height < parentPrefSize.height) { // Parent's wish is not granted: we
         * should fix the // mainNodeSize fixedRect = fixedRect.getResized(0, parentSize.height - parentPrefSize.height); if (fixedRect.height < MAINNODE_MINIMUM_SIZE) { fixedRect.height = MAINNODE_MINIMUM_SIZE; } }
         */
        return fixedRect;
    }

    /**
     * Copy constructor that copies layout constraints for the given container figure.
     * @param hostFigure a port container figure
     * @return the lyaout manager copy.
     */
    @objid ("f47af48a-5814-4f48-9842-1d5fa183984d")
    public static PortContainerLayout copy(PortContainerFigure hostFigure) {
        PortContainerLayout ret = new PortContainerLayout();
        ret.setMainNodeFigure(hostFigure.getMainNodeFigure());
        
        LayoutManager hostLm = hostFigure.getLayoutManager();
        for (Iterator<IFigure> iterator = hostFigure.getChildren().iterator(); iterator.hasNext();) {
            IFigure fig = iterator.next();
            ret.setConstraint(fig, hostLm.getConstraint(fig));
        }
        return ret;
    }

}
