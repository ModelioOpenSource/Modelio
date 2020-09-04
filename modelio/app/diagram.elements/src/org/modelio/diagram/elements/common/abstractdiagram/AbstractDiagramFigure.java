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

package org.modelio.diagram.elements.common.abstractdiagram;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.ConnectionLayer;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformFigure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Layer;
import org.eclipse.draw2d.TreeSearch;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.modelio.diagram.elements.core.figures.freeform.FreeformLayer2;

/**
 * Figure to represent a diagram.
 * 
 * @author phv
 */
@objid ("7e05eb34-1dec-11e2-8cad-001ec947c8cc")
public class AbstractDiagramFigure extends FreeformLayer2 {
    @objid ("7e084d39-1dec-11e2-8cad-001ec947c8cc")
    private boolean showPageBoundaries = false;

    @objid ("65d83c49-1e83-11e2-8cad-001ec947c8cc")
    private Dimension pageBoundaries = new Dimension(800, 600);

    @objid ("65d83c4a-1e83-11e2-8cad-001ec947c8cc")
    private Rectangle workarea;

    /**
     * Constructor
     */
    @objid ("7e084d40-1dec-11e2-8cad-001ec947c8cc")
    public AbstractDiagramFigure() {
        super();
        
        setBounds(new Rectangle(new Point(0, 0), this.pageBoundaries));
        setLayoutManager(new AbstractDiagramLayout());
        
        // the abstract diagram figure must be kept transparent in order
        // for the grid and the background to be displayed
        setBackgroundColor(null);
        setOpaque(false);
    }

    @objid ("7e084d43-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Rectangle getFreeformExtent() {
        Rectangle r = super.getFreeformExtent();
        return r.union(this.workarea);
    }

    /**
     * Set page boundary dimensions.
     * 
     * @param size the page boundary dimensions.
     */
    @objid ("7e084d4a-1dec-11e2-8cad-001ec947c8cc")
    public void setPageBoundaries(Dimension size) {
        this.pageBoundaries = size;
        repaint();
    }

    /**
     * Set the working area bounds.
     * 
     * @param r The new work area bounds.
     */
    @objid ("7e084d50-1dec-11e2-8cad-001ec947c8cc")
    public void setWorkArea(Rectangle r) {
        if (r.equals(this.workarea)) {
            return;
        }
        
        this.workarea = r;
        fireExtentChanged();
    }

    /**
     * Toggle display of page boundaries.
     * 
     * @param onOff true to display page boundaries, false to hide them.
     */
    @objid ("7e084d56-1dec-11e2-8cad-001ec947c8cc")
    public void showPageBoundaries(boolean onOff) {
        if (this.showPageBoundaries != onOff) {
            this.showPageBoundaries = onOff;
            repaint();
        }
    }

    @objid ("7e084d5a-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void paintFigure(Graphics graphics) {
        super.paintFigure(graphics);
        if (this.showPageBoundaries && (this.pageBoundaries != null)) {
            graphics.setBackgroundColor(ColorConstants.blue);
            graphics.setLineStyle(SWT.LINE_DASH);
            Rectangle r = getBounds().getCopy();
        
            Rectangle min = computeMinimumBounds();
            r.setLocation(min.getLocation());
            r.translate(-5, -5);
        
            if (this.pageBoundaries.width > 0) {
                int i = r.x;
                while (i < r.width) {
                    graphics.drawLine(i, r.y, i, r.y + r.height);
                    i += this.pageBoundaries.width;
                }
            }
        
            if (this.pageBoundaries.height > 0) {
                int j = r.y;
                while (j < r.height) {
                    graphics.drawLine(r.x, j, r.x + r.width, j);
                    j += this.pageBoundaries.height;
                }
            }
        
        }
    }

    /**
     * Overridden to disable Layer implementation that refuse to return this figure if transparent .
     * @see Layer#findFigureAt(int, int, TreeSearch)
     */
    @objid ("a1e0f29a-dfea-412a-9229-7ae1e4df6392")
    @Override
    public IFigure findFigureAt(int x, int y, TreeSearch search) {
        if (!isEnabled()) {
            return null;
        }
        
        if (!containsPoint(x, y)) {
            return null;
        }
        if (search.prune(this)) {
            return null;
        }
        
        IFigure child = findDescendantAtExcluding(x, y, search);
        if (child != null) {
            return child;
        }
        if (search.accept(this)) {
            return this;
        }
        return null;
    }

    /**
     * @return <code>true</code> when page boundaries are displayed, <code>false</code> if they are hidden.
     */
    @objid ("c5d74632-64e4-4c68-a385-cdf424d95353")
    public boolean isShowPageBoundaries() {
        return this.showPageBoundaries;
    }

    /**
     * Computes the minimum bounds of a connection layer. The returned rectangle
     * is the smallest rectangle enclosing all the links.
     */
    @objid ("c655bfdf-b716-49b6-a5c6-33dd57ffd281")
    private Rectangle computeMinimumBounds(ConnectionLayer connectionLayer) {
        int xMin = Integer.MAX_VALUE;
        int xMax = Integer.MIN_VALUE;
        int yMin = Integer.MAX_VALUE;
        int yMax = Integer.MIN_VALUE;
        
        for (final Object o : connectionLayer.getChildren()) {
            final Rectangle b = ((Figure) o).getBounds();
        
            if (b.x < xMin) {
                xMin = b.x;
            }
            if (b.x + b.width > xMax) {
                xMax = b.x + b.width;
            }
        
            if (b.y < yMin) {
                yMin = b.y;
            }
            if (b.y + b.height > yMax) {
                yMax = b.y + b.height;
            }
        
        }
        return new Rectangle(xMin, yMin, xMax - xMin, yMax - yMin);
    }

    /**
     * Computes the smallest rectangle enclosing all the diagram nodes (note: the
     * computation does not take links into account which are laid in the
     * Connection layer)
     */
    @objid ("9cd6e3e8-0399-447c-99dd-018df6e690e3")
    private Rectangle computeMinimumBounds() {
        int xMin = Integer.MAX_VALUE;
        int xMax = Integer.MIN_VALUE;
        int yMin = Integer.MAX_VALUE;
        int yMax = Integer.MIN_VALUE;
        
        for (final Object fig : getChildren()) {
            final Rectangle b;
            if (fig instanceof FreeformFigure) {
                b = ((FreeformFigure) fig).getFreeformExtent();
            } else if (fig instanceof ConnectionLayer) {
                b = computeMinimumBounds((ConnectionLayer) fig);
            } else {
                b = ((Figure) fig).getBounds();
            }
        
            if (b.isEmpty()) {
                continue;
            }
        
            if (b.x < xMin) {
                xMin = b.x;
            }
            if (b.x + b.width > xMax) {
                xMax = b.x + b.width;
            }
        
            if (b.y < yMin) {
                yMin = b.y;
            }
            if (b.y + b.height > yMax) {
                yMax = b.y + b.height;
            }
        
        }
        return new Rectangle(xMin, yMin, xMax - xMin, yMax - yMin);
    }

    @objid ("c636851f-7fd3-42d6-aa9c-28df8965bbda")
    @Override
    public Rectangle getClientArea(Rectangle rect) {
        Rectangle clientArea = super.getClientArea(rect);
        if (this.showPageBoundaries) {
            clientArea.expand(5, 5);
        }
        return clientArea;
    }

}
