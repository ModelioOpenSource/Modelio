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
package org.modelio.diagram.elements.core.figures;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.graphics.Color;

/**
 * This class is used a simple convenient public data structure of a brush properties.
 */
@objid ("7f692942-1dec-11e2-8cad-001ec947c8cc")
public class BrushOptions {
    /**
     * Fill with a gradient
     */
    @objid ("7f692946-1dec-11e2-8cad-001ec947c8cc")
    public boolean useGradient;

    /**
     * Transparency level.
     * <p>
     * 0 = transparent, 255 = opaque.
     */
    @objid ("782cb1af-9974-40a9-b8a8-12bf84440323")
    public int alpha = 255;

    /**
     * Fill color
     */
    @objid ("7f692944-1dec-11e2-8cad-001ec947c8cc")
    public Color fillColor;

    /**
     * Initialize brush options.
     */
    @objid ("7f692948-1dec-11e2-8cad-001ec947c8cc")
    public  BrushOptions() {
        this.fillColor = ColorConstants.white;
        this.useGradient = false;
        
    }

    /**
     * Copy constructor.
     * @param other the original
     */
    @objid ("72bc9d53-5a6f-47c3-b4e2-c5a33ddd33b6")
    public  BrushOptions(BrushOptions other) {
        this.alpha = other.alpha;
        this.fillColor = other.fillColor;
        this.useGradient = other.useGradient;
        
    }

}
