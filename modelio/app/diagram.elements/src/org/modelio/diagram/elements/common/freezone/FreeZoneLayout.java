/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.diagram.elements.common.freezone;

import java.util.Iterator;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.AbstractLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.diagram.elements.plugin.DiagramElements;

/**
 * Specialisation of the XYLayout to add the fact that children position should be fixed to fit as much as possible within the client area of the parent figure.
 * 
 * @author fpoyer
 */
@objid ("7e37fc80-1dec-11e2-8cad-001ec947c8cc")
public class FreeZoneLayout extends XYLayout {
    @objid ("5b76e22e-48e8-4eb1-b781-d85408ddf6c3")
    private static final boolean TRUNCATE_NEGATIVE_PART = false;

    /**
     * Fix the child position so that it is inside the client area rectangle as much as possible
     * 
     * @param clientArea the area in which the child rectangle must fit.
     * @param childRectangle The rectangle to fix.
     */
    @objid ("7e37fc84-1dec-11e2-8cad-001ec947c8cc")
    protected void fitChildRectangleInClientArea(Rectangle clientArea, Rectangle childRectangle) {
        // Fix bottom right
        childRectangle.x = Math.min(childRectangle.right(), clientArea.right()) - childRectangle.width;
        childRectangle.y = Math.min(childRectangle.bottom(), clientArea.bottom()) - childRectangle.height;
        
        // Fix top left
        childRectangle.x = Math.max(childRectangle.x, clientArea.x);
        childRectangle.y = Math.max(childRectangle.y, clientArea.y);
    }

    @objid ("7e37fc8d-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void layout(IFigure parent) {
        Point offset = getOrigin(parent);
        Rectangle clientArea = parent.getClientArea();
        
        Iterator<IFigure> children = parent.getChildren().iterator();
        while (children.hasNext()) {
            IFigure f = children.next();
            Rectangle bounds = getConstraint(f);
            if (bounds == null) {
                continue;
            }
        
            // Modifies the constraint if not complete.
            // The constraint must be modified so that the layout is reproducible
            // with auto resize edit policies when the preferred size changes.
            if (bounds.width == -1 || bounds.height == -1) {
                Dimension childPreferredSize = f.getPreferredSize(bounds.width, bounds.height);
                if (bounds.width == -1) {
                    bounds.width = childPreferredSize.width;
                }
                if (bounds.height == -1) {
                    bounds.height = childPreferredSize.height;
                }
            }
        
            bounds = bounds.getCopy();
            bounds.translate(offset);
        
            fitChildRectangleInClientArea(clientArea, bounds);
        
            f.setBounds(bounds);
        }
    }

    /**
     * Calculates and returns the preferred size of the input figure. Since in XYLayout the location of the child should be preserved, the preferred size would be a region which would hold all the children of the input figure. If no constraint is set, that
     * child is ignored for calculation. If width and height are not positive, the preferred dimensions of the child are taken.
     * @see AbstractLayout#calculatePreferredSize(IFigure, int, int)
     * @since 2.0
     */
    @objid ("70f74d5d-6902-4d8e-b70f-b1c71a835f1e")
    @Override
    protected Dimension calculatePreferredSize(IFigure f, int wHint, int hHint) {
        Rectangle rect = new Rectangle();
        Iterator<IFigure> children = f.getChildren().iterator();
        while (children.hasNext()) {
            IFigure child = children.next();
            Rectangle r = getConstraint(child);
            if (r == null) {
                continue;
            }
        
            if (r.width == -1 || r.height == -1) {
                Dimension childPrefSize = child.getPreferredSize(r.width, r.height);
                r = r.getCopy();
                if (r.width == -1) {
                    r.width = childPrefSize.width;
                }
                if (r.height == -1) {
                    r.height = childPrefSize.height;
                }
            }
            rect.union(r);
        }
        
        truncateNegativePart(rect);
        
        Dimension d = rect.getSize();
        Insets insets = f.getInsets();
        return new Dimension(d.width + insets.getWidth(), d.height
                        + insets.getHeight()).union(getBorderPreferredSize(f));
    }

    @objid ("789ad43d-4d68-4e9d-8d84-999b1f2e5dcb")
    @Override
    public Rectangle getConstraint(IFigure figure) {
        Object constraint = super.getConstraint(figure);
        if (constraint instanceof Rectangle) {
            return (Rectangle) constraint;
        } else {
            // In a free zone layout, constraints should always be Rectangle, ignoring invalid constraint
            return null;
        }
    }

    @objid ("27c523ef-66d2-4076-9a10-32948f2b8447")
    @Override
    public void setConstraint(IFigure figure, Object newConstraint) {
        if (newConstraint == null || newConstraint instanceof Rectangle) {
            super.setConstraint(figure, newConstraint);
        } else {
            DiagramElements.LOG.warning(new IllegalArgumentException(String.format("In a free zone layout, constraints should always be Rectangle, ignoring [%s] constraint for %s in %s",
                    newConstraint, figure, figure.getParent())));
        }
    }

    /**
     * Truncates the rectangle of its negative part.
     * 
     * @param rect a Rectangle that on return will start on positive coordinates.
     */
    @objid ("994d820b-a532-4398-9e18-d27680f41399")
    protected void truncateNegativePart(Rectangle rect) {
        if (FreeZoneLayout.TRUNCATE_NEGATIVE_PART) {
            if (rect.x < 0) {
                rect.width += rect.x;
                rect.x = 0;
            }
            if (rect.y < 0) {
                rect.height += rect.y;
                rect.y = 0;
            }
        }
    }

}
