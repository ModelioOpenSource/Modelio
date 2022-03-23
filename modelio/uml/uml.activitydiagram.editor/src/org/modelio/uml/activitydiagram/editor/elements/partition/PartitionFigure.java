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
package org.modelio.uml.activitydiagram.editor.elements.partition;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;
import org.modelio.diagram.elements.core.figures.GradientFigure;
import org.modelio.diagram.styles.core.StyleKey.LinePattern;

/**
 * Dedicated figure for Partition. Based on a GradientFigure, with an added LineBorder.
 * 
 * @author fpoyer
 */
@objid ("2b19e5e9-55b6-11e2-877f-002564c97630")
public class PartitionFigure extends GradientFigure {
    @objid ("2b1a0cfc-55b6-11e2-877f-002564c97630")
    private LineBorder lineBorder;

    /**
     * C'tor. Just a basic GradientFigure with an additional line border.
     */
    @objid ("2b1a0cfd-55b6-11e2-877f-002564c97630")
    public  PartitionFigure() {
        super();
        this.lineBorder = new LineBorder();
        setBorder(this.lineBorder);
        
    }

    @objid ("2b1a340a-55b6-11e2-877f-002564c97630")
    @Override
    public void setLineColor(Color lineColor) {
        if (lineColor != this.penOptions.lineColor) {
            this.lineBorder.setColor(lineColor);
            super.setLineColor(lineColor);
        }
        
    }

    @objid ("2b1a5b19-55b6-11e2-877f-002564c97630")
    @Override
    public void setLineWidth(int lineWidth) {
        if (lineWidth != this.penOptions.lineWidth) {
            this.lineBorder.setWidth(lineWidth);
            super.setLineWidth(lineWidth);
        }
        
    }

    @objid ("2b1a5b1d-55b6-11e2-877f-002564c97630")
    @Override
    public void setLinePattern(LinePattern linePattern) {
        if (linePattern != this.penOptions.linePattern) {
            this.lineBorder.setStyle(linePattern.toSWTConstant());
            super.setLinePattern(linePattern);
        }
        
    }

    @objid ("2b1a822c-55b6-11e2-877f-002564c97630")
    @Override
    public Rectangle getHandleBounds() {
        if (!this.getChildren().isEmpty())
            return ((IFigure) this.getChildren().get(0)).getBounds().getCopy();
        return super.getHandleBounds();
    }

}
