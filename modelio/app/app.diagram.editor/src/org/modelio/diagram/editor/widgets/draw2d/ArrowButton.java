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
package org.modelio.diagram.editor.widgets.draw2d;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Button;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.Triangle;
import org.eclipse.draw2d.geometry.Dimension;

/**
 * Draw2d arrow button paint on a gradient background.
 * 
 * @author cma, from GEF {@link org.eclipse.gef.ui.palette.FlyoutPaletteComposite}.
 */
@objid ("4b78258d-d2fb-4cd6-8246-69ff58c6e11e")
public class ArrowButton extends Button {
    @objid ("3793d6df-3ff5-45ba-aef7-aa69b599c760")
    private final Triangle triangle;

    @objid ("d3e691de-6e41-4517-9a9b-daca44db4021")
    static final Dimension ARROW_SIZE = new Dimension(6, 11);

    /**
     * Creates a new instance
     * @param direction the direction the arrow should face (PositionConstants.RIGHT or PositionConstants.LEFT)
     */
    @objid ("21b0b8fd-80cf-4456-8f32-f4cb0e0a125d")
    public  ArrowButton(int direction) {
        super();
        setDirection(direction);
        
        this.triangle = new Triangle();
        this.triangle.setOutline(true);
        this.triangle.setBackgroundColor(ColorConstants.listBackground);
        this.triangle.setForegroundColor(ColorConstants.buttonDarkest);
        setContents(this.triangle);
        
    }

    /**
     * Sets the direction the orientable figure will face.
     * <p>
     * Possible values are {@link PositionConstants#NORTH}, {@link PositionConstants#SOUTH}, {@link PositionConstants#EAST} and {@link PositionConstants#WEST}.
     * @param direction The direction
     */
    @objid ("d1ab6812-ca1d-4e9c-9b86-29ea1fd5524a")
    public final void setDirection(int direction) {
        if (this.triangle != null) {
            this.triangle.setDirection(direction);
        }
        
    }

    @objid ("06fbea74-c1cf-4c17-9cce-2f1c0c478134")
    @Override
    protected void layout() {
        super.layout();
        /*
         * Rectangle clientArea = getBounds();
         * 
         * this.triangle.setBounds(new Rectangle( clientArea.getCenter().getTranslated( -ARROW_SIZE.width / 2, -ARROW_SIZE.height / 2), ARROW_SIZE));
         */
        
    }

    @objid ("182b8acc-d950-4195-bd2f-451b45c48752")
    @Override
    protected void paintFigure(Graphics graphics) {
        super.paintFigure(graphics);
        
        /*
         * // paint the gradient graphics.pushState(); Rectangle r = Rectangle.SINGLETON; r.setBounds(getBounds()); graphics.setForegroundColor(ColorConstants.listBackground); graphics.setBackgroundColor(ColorConstants.button); graphics.fillGradient(r,
         * true); graphics.popState();
         * 
         * // draw bottom border graphics.setForegroundColor(ColorConstants.buttonDarker); graphics.drawLine( r.getBottomLeft().getTranslated(0, -1), r.getBottomRight().getTranslated(0, -1));
         */
        
    }

}
