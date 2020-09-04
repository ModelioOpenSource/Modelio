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

package org.modelio.diagram.elements.common.simple;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.modelio.diagram.elements.core.figures.GradientFigure;
import org.modelio.diagram.elements.core.figures.PenOptions;

/**
 * Figure used by SimpleEditPart. Just a GradientFigure with a line border and a label centered inside it.
 */
@objid ("7f240543-1dec-11e2-8cad-001ec947c8cc")
public class SimpleFigure extends GradientFigure {
    @objid ("3d876b64-d356-4a3f-a89d-f83a38c1c638")
    private Label label;

    /**
     * Label firstChild;
     */
    @objid ("7f240548-1dec-11e2-8cad-001ec947c8cc")
    public SimpleFigure() {
        // init text and line pen support
        this.penOptions = new PenOptions();
        
        /* Assemble the figure here, ie create others figures and add them to 'this' 
         | This about keeping the children transparent possibly without drawn borders
         | in order to benefit from the background painting of the BoxFigure parent class
         */
        
        // Setup the layout manager
        this.setLayoutManager(new BorderLayout());
        
        // Set up a border
        this.updateBorder();
        
        //  child: the text area
        this.label = new Label() {
            @Override
            public Dimension getMinimumSize(int w, int h) {
                return getPreferredSize(w, h);
            }
        };
        this.label.setOpaque(false);
        this.label.setBorder(new MarginBorder(4));
        this.add(this.label, BorderLayout.CENTER);
        
        this.setOpaque(true);
    }

    @objid ("7f24054b-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setTextColor(Color textColor) {
        super.setTextColor(textColor);
        this.label.setForegroundColor(textColor);
    }

    @objid ("7f24054f-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setTextFont(Font textFont) {
        super.setTextFont(textFont);
        this.label.setFont(textFont);
    }

    /**
     * Sets the text of the label figure.
     * @param name new text of the label figure.
     */
    @objid ("7f240553-1dec-11e2-8cad-001ec947c8cc")
    public void setLabel(String name) {
        this.label.setText(name);
    }

    /**
     * Returns the label figure.
     * @return the label figure.
     */
    @objid ("7f240557-1dec-11e2-8cad-001ec947c8cc")
    public Label getLabelFigure() {
        return this.label;
    }

    @objid ("7f266768-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setLineColor(Color lineColor) {
        if (this.penOptions.lineColor != lineColor) {
            super.setLineColor(lineColor);
            this.updateBorder();
        }
    }

    @objid ("7f26676c-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setLineWidth(int lineWidth) {
        if (this.penOptions.lineWidth != lineWidth) {
            super.setLineWidth(lineWidth);
            this.updateBorder();
        }
    }

    @objid ("7f266770-1dec-11e2-8cad-001ec947c8cc")
    protected void updateBorder() {
        this.setBorder(new LineBorder(this.penOptions.lineColor, this.penOptions.lineWidth));
    }

}
