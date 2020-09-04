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
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.modelio.diagram.styles.core.StyleKey.LinePattern;

/**
 * This class is used a simple convenient public data structure of a pen properties.
 */
@objid ("7fa988da-1dec-11e2-8cad-001ec947c8cc")
public class PenOptions {
    /**
     * Line width
     */
    @objid ("7fa988dc-1dec-11e2-8cad-001ec947c8cc")
    public int lineWidth;

    /**
     * Line style
     */
    @objid ("8164a83a-1e83-11e2-8cad-001ec947c8cc")
    public LinePattern linePattern;

    /**
     * Line color
     */
    @objid ("7fa988de-1dec-11e2-8cad-001ec947c8cc")
    public Color lineColor;

    /**
     * Text color
     */
    @objid ("7fa988e0-1dec-11e2-8cad-001ec947c8cc")
    public Color textColor;

    /**
     * Text font
     */
    @objid ("7fa988e2-1dec-11e2-8cad-001ec947c8cc")
    public Font textFont;

    /**
     * Initialize a pen options.
     */
    @objid ("7fa988e6-1dec-11e2-8cad-001ec947c8cc")
    public PenOptions() {
        this.lineColor = ColorConstants.black;
        this.lineWidth = 1;
        this.linePattern = LinePattern.LINE_SOLID;
        this.textColor = ColorConstants.black;
        this.textFont = null;
    }

    /**
     * Copy constructor.
     * @param other the original
     */
    @objid ("c7e70a28-24aa-4fb6-bf86-cdbcad34b2ba")
    public PenOptions(PenOptions other) {
        this.lineColor = other.lineColor;
        this.lineWidth = other.lineWidth;
        this.linePattern = other.linePattern;
        this.textColor = other.textColor;
        this.textFont = other.textFont;
    }

}
