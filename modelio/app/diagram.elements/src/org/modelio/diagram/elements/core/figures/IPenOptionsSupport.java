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
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.modelio.diagram.styles.core.StyleKey.LinePattern;

/**
 * A figure implements this interface to indicate that it is using a pen defined by: <li>a line drawing Color <li>a line
 * thickness<li>a text color<li>a text font</li>
 */
@objid ("7fa4c440-1dec-11e2-8cad-001ec947c8cc")
public interface IPenOptionsSupport {
    /**
     * Set the line(s) color.
     * @param lineColor the line color.
     */
    @objid ("7fa4c442-1dec-11e2-8cad-001ec947c8cc")
    void setLineColor(Color lineColor);

    /**
     * Set the line(s) width.
     * @param lineWidth the line(s) width.
     */
    @objid ("7fa4c445-1dec-11e2-8cad-001ec947c8cc")
    void setLineWidth(int lineWidth);

    /**
     * Get the line color.
     * @return the line color.
     */
    @objid ("7fa4c448-1dec-11e2-8cad-001ec947c8cc")
    Color getLineColor();

    /**
     * Get the line width.
     * @return the line width.
     */
    @objid ("7fa4c44b-1dec-11e2-8cad-001ec947c8cc")
    int getLineWidth();

    /**
     * Set the text color.
     * @param textColor the text color.
     */
    @objid ("7fa4c44e-1dec-11e2-8cad-001ec947c8cc")
    void setTextColor(Color textColor);

    /**
     * Set the text font.
     * @param textFont the text font.
     */
    @objid ("7fa4c451-1dec-11e2-8cad-001ec947c8cc")
    void setTextFont(Font textFont);

    /**
     * Get the text color.
     * @return the text color.
     */
    @objid ("7fa4c454-1dec-11e2-8cad-001ec947c8cc")
    Color getTextColor();

    /**
     * Get the text font.
     * @return the text font.
     */
    @objid ("7fa72665-1dec-11e2-8cad-001ec947c8cc")
    Font getTextFont();

    /**
     * Sets the line pattern to the argument, which must be one of the constants
     * 
     * {@link LinePattern}
     * @param lineStyle the new style
     */
    @objid ("7fa72668-1dec-11e2-8cad-001ec947c8cc")
    void setLinePattern(LinePattern lineStyle);

    /**
     * Get the line pattern
     * @return lineStyle the line style See {@link LinePattern}
     */
    @objid ("7fa7266b-1dec-11e2-8cad-001ec947c8cc")
    LinePattern getLinePattern();

}
