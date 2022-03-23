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
package org.modelio.diagram.elements.common.portbordered;

import java.util.Iterator;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.DelegatingLayout;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FigureListener;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Locator;
import org.eclipse.draw2d.TreeSearch;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

@objid ("7ee14356-1dec-11e2-8cad-001ec947c8cc")
class PortContainerFigure extends Figure {
    @objid ("9d2b48ae-975d-4710-b410-c554cfb87578")
    FigureListener portListener = null;

    @objid ("66423199-f162-46e9-845f-ab4e651ca12f")
    Rectangle overallBounds = null;

    @objid ("7ee1435f-1dec-11e2-8cad-001ec947c8cc")
    public  PortContainerFigure() {
        super();
        setLayoutManager(new DelegatingLayout());
        
        this.portListener = new FigureListener() {
        
            @Override
            public void figureMoved(IFigure fig) {
        
                fig.getParent().revalidate();
            }
        
        };
        
    }

    @objid ("7ee14361-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void add(IFigure figure, Object constraint, int index) {
        figure.addFigureListener(this.portListener);
        
        Locator l = new Locator() {
        
            @Override
            public void relocate(IFigure port) {
        
                Dimension portSize = port.getPreferredSize();
        
                Rectangle parentBounds = getParent().getBounds();
        
                int pw = parentBounds.width;
                int ph = parentBounds.height;
                int px = parentBounds.x;
                int py = parentBounds.y;
        
                int newX;
                int newY;
        
                Rectangle candidateBounds = null;
                int yOffset = 0;
                do {
                    // Position all ports on the East side
                    newX = px + pw - portSize.width / 2;
                    newY = py + ph / 2 + yOffset;
        
                    candidateBounds = new Rectangle(new Point(newX, newY), portSize);
        
                    yOffset += portSize.height + portSize.width / 2;
        
                } while (getOverridenPort(candidateBounds, port) != null);
        
                port.setBounds(candidateBounds);
                port.repaint();
        
                // System.out.println("relocate " + port.toString() + portSize +
                // parentBounds + candidateBounds);
            }
        
            public IFigure getOverridenPort(Rectangle candidateBounds, IFigure port) {
        
                List<?> borderItems = port.getParent().getChildren();
        
                // Only check those border items that would have already been
                // relocated.
                int currentIndex = borderItems.indexOf(port);
                for (int i = 0; i < currentIndex; i++) {
                    IFigure figure = (IFigure) borderItems.get(i);
                    if (figure.isVisible()) {
                        Rectangle rect = figure.getBounds().getCopy();
                        if (rect.intersects(candidateBounds)) {
                            return figure;
                        }
                    }
                }
                return null;
            }
        
        };
        
        super.add(figure, l, index);
        // helper.hookChild(figure);
        
    }

    @objid ("7ee14369-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void remove(IFigure figure) {
        figure.removeFigureListener(this.portListener);
        super.remove(figure);
        
    }

    @objid ("7ee1436f-1dec-11e2-8cad-001ec947c8cc")
    public Rectangle getOverallBounds() {
        if (this.overallBounds == null) {
            this.overallBounds = getParent().getBounds().getCopy();
            // enlarge for children (ports)
            Iterator<?> iterator = getChildren().iterator();
            while (iterator.hasNext()) {
                Figure childFigure = (Figure) iterator.next();
                Rectangle childBounds = childFigure.getBounds();
                this.overallBounds.union(childBounds);
            }
        }
        return this.overallBounds;
    }

    @objid ("7ee14375-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void repaint() {
        if (getChildren().isEmpty()) {
            super.repaint();
        } else {
            if (getParent() == null || !isVisible())
                return;
            Rectangle rectBounds = getOverallBounds();
            getParent().getParent().repaint(rectBounds);
            // if (getViewport() != null) {
            // getViewport().repaint(rectBounds);
            // }
        }
        
    }

    @objid ("7ee14378-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean containsPoint(int x, int y) {
        // if (useLocalCoordinates()) {
        // x = x - getBounds().x - getInsets().left;
        // y = y - getBounds().y - getInsets().top;
        // }
        
        for (int i = getChildren().size(); i > 0;) {
            i--;
            IFigure fig = (IFigure) getChildren().get(i);
            if (fig.containsPoint(x, y)) {
                return true;
            }
        }
        return false;
    }

    @objid ("7ee1437f-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void paintClientArea(Graphics graphics) {
        if (getChildren().isEmpty() || !isVisible())
            return;
        
        //TODO: disabled by CMA : Enlarging clipping is BAD, please find another way.
        // // We have to enlarge the clip area to overall bounds before painting
        // Rectangle clip = getOverallBounds();
        // graphics.setClip(clip);
        
        graphics.pushState();
        paintChildren(graphics);
        graphics.popState();
        graphics.restoreState();
        
    }

    @objid ("7ee14385-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean intersects(Rectangle rect) {
        Rectangle rectangle = getOverallBounds();
        return rectangle.intersects(rect);
    }

    @objid ("7ee3a593-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected IFigure findDescendantAtExcluding(int x, int y, TreeSearch search) {
        Point pt = new Point(x, y);
        this.translateFromParent(pt);
        
        IFigure fig;
        for (int i = getChildren().size(); i > 0;) {
            i--;
            fig = (IFigure) getChildren().get(i);
            if (fig.isVisible()) {
                fig = fig.findFigureAt(pt.x, pt.y, search);
                if (fig != null)
                    return fig;
            }
        }
        // No descendants were found
        return null;
    }

    @objid ("7ee3a59f-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public IFigure findFigureAt(int x, int y, TreeSearch search) {
        if (search.prune(this))
            return null;
        IFigure child = findDescendantAtExcluding(x, y, search);
        if (child != null)
            return child;
        return null;
    }

    @objid ("7ee3a5ab-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public IFigure findMouseEventTargetAt(int x, int y) {
        IFigure f = findMouseEventTargetInDescendantsAt(x, y);
        if (f != null)
            return f;
        if (isMouseEventTarget())
            return this;
        return null;
    }

    @objid ("7ee3a5b4-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected IFigure findMouseEventTargetInDescendantsAt(int x, int y) {
        Point pt = new Point(x, y);
        this.translateFromParent(pt);
        
        IFigure fig;
        for (int i = getChildren().size(); i > 0;) {
            i--;
            fig = (IFigure) getChildren().get(i);
            if (fig.isVisible() && fig.isEnabled()) {
                if (fig.containsPoint(pt.x, pt.y)) {
                    fig = fig.findMouseEventTargetAt(pt.x, pt.y);
                    return fig;
                }
            }
        }
        return null;
    }

    @objid ("7ee3a5bd-1dec-11e2-8cad-001ec947c8cc")
    public IFigure getMainFigure() {
        return ((PortBorderedFigure) getParent()).getPrimaryFigure();
    }

    @objid ("7ee3a5c3-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void erase() {
        if (getChildren().isEmpty()) {
            super.erase();
        } else {
            if (getParent() == null || !isVisible())
                return;
            repaint();
        }
        
    }

    @objid ("7ee3a5c6-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void invalidate() {
        this.overallBounds = null;
        super.invalidate();
        // ??? updateLayerExtents(); ???
        
    }

    @objid ("7ee3a5c9-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void validate() {
        this.overallBounds = null;
        super.validate();
        
    }

}
