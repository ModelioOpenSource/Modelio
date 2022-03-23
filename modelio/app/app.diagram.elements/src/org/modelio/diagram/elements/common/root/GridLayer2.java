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
package org.modelio.diagram.elements.common.root;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.editparts.GridLayer;
import org.eclipse.swt.graphics.Color;

/**
 * Extended GridLayer that supports additionnal grid configuration options
 */
@objid ("65a88ee7-33f7-11e2-95fe-001ec947c8cc")
class GridLayer2 extends GridLayer {
    @objid ("65aaf111-33f7-11e2-95fe-001ec947c8cc")
    private int gridAlpha;

    /**
     * A viewer property indicating the grid's color. The value must be a {@link Color}.
     */
    @objid ("c3cd7607-3896-11e2-95fe-001ec947c8cc")
    public static final String PROPERTY_GRID_COLOR = "GridLayer2.GridColor"; // $NON-NLS-1$

    /**
     * A viewer property indicating the grid's alpha. The value must be a {@link Integer}.
     */
    @objid ("c3d6ff6e-3896-11e2-95fe-001ec947c8cc")
    public static final String PROPERTY_GRID_ALPHA = "GridLayer2.GridAlpha"; // $NON-NLS-1$

    @objid ("65aaf110-33f7-11e2-95fe-001ec947c8cc")
    private Color gridColor;

    @objid ("65aaf11a-33f7-11e2-95fe-001ec947c8cc")
    public  GridLayer2() {
        super();
        this.gridColor = ColorConstants.lightGray;
        this.gridAlpha = 255;
        
    }

    @objid ("65aaf11c-33f7-11e2-95fe-001ec947c8cc")
    @Override
    protected void paintGrid(Graphics graphics) {
        graphics.setAlpha(this.gridAlpha);
        graphics.setForegroundColor(this.gridColor);
        graphics.clipRect(getBounds().getCopy().shrink(8, 8)); //TODO improve design...
        
        
        //graphics.setForegroundColor(ColorConstants.black);
        
        super.paintGrid(graphics);
        
        // Draw a (0, 0) point indicator
        int size = 8;
        graphics.setAlpha(255);
        graphics.setLineWidth(2);
        
        graphics.drawLine(-size, 0, size, 0);
        graphics.drawLine(0, -size, 0, size);
        
    }

    @objid ("65aaf120-33f7-11e2-95fe-001ec947c8cc")
    @Override
    public void setOrigin(Point p) {
        super.setOrigin(p);
    }

    @objid ("65aaf124-33f7-11e2-95fe-001ec947c8cc")
    @Override
    public void setSpacing(Dimension spacing) {
        super.setSpacing(spacing);
    }

    @objid ("65aaf128-33f7-11e2-95fe-001ec947c8cc")
    public void setGridAlpha(int value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.gridAlpha = value;
        
    }

    @objid ("65aaf12b-33f7-11e2-95fe-001ec947c8cc")
    public void setGridColor(Color value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.gridColor = value;
        
    }

}
