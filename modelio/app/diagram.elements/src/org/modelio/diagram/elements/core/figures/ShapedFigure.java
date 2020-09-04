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

package org.modelio.diagram.elements.core.figures;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * A shaped figure filled with a gradient.
 * 
 * @author phv
 */
@objid ("7fcd4c10-1dec-11e2-8cad-001ec947c8cc")
public class ShapedFigure extends GradientFigure implements IClonableFigure {
    @objid ("7fcd4c12-1dec-11e2-8cad-001ec947c8cc")
    protected IShaper shaper = null;

    /**
     * Default constructor.
     */
    @objid ("7fcd4c13-1dec-11e2-8cad-001ec947c8cc")
    public ShapedFigure() {
        super();
    }

    /**
     * Builds a ShapedFigure with a specific Shaper.
     * @param shaper A shaper.
     */
    @objid ("7fcd4c16-1dec-11e2-8cad-001ec947c8cc")
    public ShapedFigure(IShaper shaper) {
        super();
        this.shaper = shaper;
    }

    /**
     * Get the current shaper.
     * @return A shaper.
     */
    @objid ("7fcd4c1a-1dec-11e2-8cad-001ec947c8cc")
    public IShaper getShaper() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.shaper;
    }

    /**
     * Set the current shaper.
     * @param value The new shaper.
     */
    @objid ("7fcd4c1f-1dec-11e2-8cad-001ec947c8cc")
    public void setShaper(IShaper value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.shaper = value;
    }

    @objid ("7fcd4c23-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void paintFigure(Graphics graphics) {
        if (this.shaper != null) {
            graphics.clipPath(this.shaper.getShapePath(getBounds()));
        }
        super.paintFigure(graphics);
    }

    /**
     * Copy constructor.
     * @param orig the original figure
     */
    @objid ("3c379a6e-6344-4ea1-a8c8-c954fa4e5654")
    public ShapedFigure(ShapedFigure orig) {
        super(orig);
        this.shaper = orig.getShaper();
    }

    @objid ("b39b45b9-d868-4aa0-ac34-ef09367cef85")
    @Override
    public ShapedFigure getCopy() {
        return new ShapedFigure(this);
    }

    /**
     * Needs a re-definition to paint the gradient all over the figure bounds.
     * 
     * The shaper clippath and border will deal with the required cropping.
     */
    @objid ("130ff3c8-9d02-412b-9984-939c770acaab")
    @Override
    protected Rectangle getPaintRectangle() {
        return getBounds().getCopy();
    }

}
