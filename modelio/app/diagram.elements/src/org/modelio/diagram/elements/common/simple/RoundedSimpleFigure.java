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
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.modelio.diagram.elements.core.figures.PenOptions;
import org.modelio.diagram.elements.core.figures.RoundedBoxFigure;

/**
 * {@link RoundedBoxFigure} with a {@link Label} in the center.
 */
@objid ("7f21a2e2-1dec-11e2-8cad-001ec947c8cc")
class RoundedSimpleFigure extends RoundedBoxFigure {
    @objid ("70aa9cd3-1ebd-4f85-a844-bde8be3aa533")
    private Label label;

    /**
     * Label firstChild;
     */
    @objid ("7f21a2e6-1dec-11e2-8cad-001ec947c8cc")
    public RoundedSimpleFigure() {
        // init text and line pen support
        this.penOptions = new PenOptions();
        
        /* Assemble the figure here, ie create others figures and add them to 'this' 
         | This about keeping the children transparent possibly without drawn borders
         | in order to benefit from the background painting of the BoxFigure parent class
         */
        
        // Setup the layout manager
        setLayoutManager(new BorderLayout());
        
        //  child: the text area
        this.label = new Label();
        this.label.setOpaque(false);
        this.label.setBorder(new MarginBorder(4));
        this.add(this.label, BorderLayout.CENTER);
    }

    @objid ("7f21a2e9-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setTextColor(Color textColor) {
        super.setTextColor(textColor);
        if (this.penOptions.textColor != textColor) {
            this.label.setForegroundColor(textColor);
        }
    }

    @objid ("7f21a2ed-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setTextFont(Font textFont) {
        super.setTextFont(textFont);
        if (this.penOptions.textFont != textFont) {
            this.label.setFont(textFont);
        }
    }

    @objid ("7f21a2f1-1dec-11e2-8cad-001ec947c8cc")
    public void setLabel(String name) {
        this.label.setText(name);
    }

    @objid ("7f21a2f4-1dec-11e2-8cad-001ec947c8cc")
    public Label getLabelFigure() {
        return this.label;
    }

}
