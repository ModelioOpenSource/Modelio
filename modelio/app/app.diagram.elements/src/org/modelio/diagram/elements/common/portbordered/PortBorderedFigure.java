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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.TreeSearch;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.diagram.elements.core.figures.GradientFigure;

@objid ("7edc7eb0-1dec-11e2-8cad-001ec947c8cc")
public class PortBorderedFigure extends GradientFigure {
    @objid ("cd5d7142-dfb5-4960-b3a5-e765dabc45a0")
    private IFigure primaryFigure = null;

    @objid ("7edc7eb1-1dec-11e2-8cad-001ec947c8cc")
    private PortContainerFigure portContainer;

    @objid ("5e013735-263a-4cb3-bae4-0b7788c3de27")
    private Rectangle overallBounds = null;

    @objid ("7edc7eb8-1dec-11e2-8cad-001ec947c8cc")
    public PortBorderedFigure(IFigure primaryFigure) {
        super();
        this.primaryFigure = primaryFigure;
        
        // debug
        // setOpaque(true);
        // setBackgroundColor(ColorConstants.lightGreen);
        // setLayoutManager(new FlowLayout());
        //
        
        // Create the port container
        this.portContainer = new PortContainerFigure();
        
        // Add children
        add(this.primaryFigure);
        add(this.portContainer);
        
        setBounds(primaryFigure.getBounds().getCopy());
    }

    @objid ("7edc7ebd-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void layout() {
        if (!this.getBounds().equals(this.primaryFigure.getBounds())) {
            this.primaryFigure.setBounds(this.getBounds().getCopy());
            this.portContainer.invalidateTree();
            erase();
        }
    }

    @objid ("7edc7ec0-1dec-11e2-8cad-001ec947c8cc")
    public void addPortFigure(IFigure figure) {
        this.portContainer.add(figure);
    }

    @objid ("7edee0db-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Dimension getMinimumSize(int wHint, int hHint) {
        return this.primaryFigure.getMinimumSize(wHint, hHint);
    }

    @objid ("7edee0e4-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Dimension getPreferredSize(int wHint, int hHint) {
        return this.primaryFigure.getPreferredSize(wHint, hHint);
    }

    @objid ("7edee0ed-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void repaint() {
        super.repaint();
        this.portContainer.repaint();
    }

    @objid ("7edee0f0-1dec-11e2-8cad-001ec947c8cc")
    public void removePortFigure(IFigure figure) {
        this.portContainer.remove(figure);
    }

    @objid ("7edee0f5-1dec-11e2-8cad-001ec947c8cc")
    public void addToPrimaryFigure(IFigure figure, int index) {
        this.primaryFigure.add(figure, index);
    }

    @objid ("7edee0fb-1dec-11e2-8cad-001ec947c8cc")
    public void removeFromPrimaryFigure(IFigure figure) {
        this.primaryFigure.remove(figure);
    }

    @objid ("7edee100-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Rectangle getClientArea(Rectangle rect) {
        if (this.primaryFigure != null) {
            return this.primaryFigure.getClientArea(rect);
        } else
            return super.getClientArea(rect);
    }

    @objid ("7edee109-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean containsPoint(int x, int y) {
        if (this.portContainer.containsPoint(x, y)) {
            return true;
        } else
            return super.containsPoint(x, y);
    }

    @objid ("7edee10f-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void primTranslate(int dx, int dy) {
        super.primTranslate(dx, dy);
        erase();
    }

    @objid ("7edee114-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void erase() {
        super.erase();
        this.portContainer.erase();
    }

    @objid ("7edee117-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public IFigure findFigureAt(int x, int y, TreeSearch search) {
        if (search.prune(this))
            return null;
        IFigure result = this.portContainer.findFigureAt(x, y, search);
        if (result != null) {
            return result;
        }
        return this.primaryFigure.findFigureAt(x, y, search);
    }

    @objid ("7edee123-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public IFigure findMouseEventTargetAt(int x, int y) {
        IFigure borderItemFigure = this.portContainer.findMouseEventTargetAt(x, y);
        if (borderItemFigure != null)
            return borderItemFigure;
        return super.findMouseEventTargetAt(x, y);
    }

    @objid ("7edee12c-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean intersects(Rectangle rect) {
        if (getOverallBounds().intersects(rect))
            return true;
        return super.intersects(rect);
    }

    @objid ("7ee14335-1dec-11e2-8cad-001ec947c8cc")
    public Rectangle getOverallBounds() {
        if (this.overallBounds == null) {
            Rectangle rect = getBounds().getCopy();
            if (this.portContainer != null) {
                rect = rect.union(this.portContainer.getOverallBounds());
            }
            this.overallBounds = rect;
        }
        return this.overallBounds;
    }

    @objid ("7ee1433b-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void invalidate() {
        this.overallBounds = null;
        super.invalidate();
    }

    @objid ("7ee1433e-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void validate() {
        this.overallBounds = null;
        super.validate();
    }

    @objid ("7ee14341-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void fireFigureMoved() {
        super.fireFigureMoved();
        this.overallBounds = null;
    }

    @objid ("7ee14344-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Rectangle getHandleBounds() {
        return super.getHandleBounds();
    }

    @objid ("7ee1434b-1dec-11e2-8cad-001ec947c8cc")
    public IFigure getPrimaryFigure() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.primaryFigure;
    }

}
