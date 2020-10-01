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
import org.eclipse.swt.graphics.Color;

/**
 * A figure implements this interface to indicate that it using a brush defined by a filling Color and that it can draw
 * a color gradient if required.
 */
@objid ("7fa4c424-1dec-11e2-8cad-001ec947c8cc")
public interface IBrushOptionsSupport {
    /**
     * Set the fill color.
     * 
     * @param fillColor the fill color.
     */
    @objid ("7fa4c426-1dec-11e2-8cad-001ec947c8cc")
    void setFillColor(Color fillColor);

    /**
     * Set whether the background is filled with a gradient.
     * 
     * @param useGradient true to fill with a gradient, false to fill only with the fill color.
     */
    @objid ("7fa4c429-1dec-11e2-8cad-001ec947c8cc")
    void setUseGradient(boolean useGradient);

    /**
     * Get the fill color.
     * 
     * @return the fill color.
     */
    @objid ("7fa4c42c-1dec-11e2-8cad-001ec947c8cc")
    Color getFillColor();

    /**
     * Tells whether the background is filled with a gradient.
     * 
     * @return true if the background is filled with a gradient, false in the other case.
     */
    @objid ("7fa4c42f-1dec-11e2-8cad-001ec947c8cc")
    boolean getUseGradient();

    /**
     * Set the transparency level.
     * <p>
     * Values may range from 0 to 255. A value of 0 is completely transparent.
     * @see org.eclipse.draw2d.Graphics#setAlpha(int)
     * 
     * @param alpha an alpha value (0-255)
     */
    @objid ("9b37c0ed-781c-4dfd-902d-dffe6af53137")
    void setFillAlpha(int alpha);

    /**
     * Set the transparency level.
     * <p>
     * Values may range from 0 to 255. A value of 0 is completely transparent.
     * @see org.eclipse.draw2d.Graphics#setAlpha(int)
     * 
     * @return an alpha value (0-255)
     */
    @objid ("2315f1fa-1106-4272-995c-59835f1ce38d")
    int getFillAlpha();

}
