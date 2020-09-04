/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.diagram.editor.bpmn.elements.bpmnlane;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.swt.graphics.Color;
import org.modelio.diagram.elements.core.figures.GradientFigure;
import org.modelio.diagram.styles.core.StyleKey.LinePattern;

/**
 * Dedicated figure for BPMN lanes and Participants.
 * <p>
 * Based on a {@link GradientFigure}, with an added {@link LineBorder}.
 */
@objid ("6115efda-55b6-11e2-877f-002564c97630")
public class BpmnLaneFigure extends GradientFigure {
    @objid ("6ddf37d6-24c1-4655-bf5b-c56519baf357")
    private LineBorder lineBorder;

    /**
     * C'tor. Just a basic {@link GradientFigure} with an additional line border.
     */
    @objid ("6115efdf-55b6-11e2-877f-002564c97630")
    public BpmnLaneFigure() {
        super();
        this.lineBorder = new LineBorder();
        setBorder(this.lineBorder);
    }

    @objid ("6115efe2-55b6-11e2-877f-002564c97630")
    @Override
    public void setLineColor(Color lineColor) {
        if (lineColor != this.penOptions.lineColor) {
            this.lineBorder.setColor(lineColor);
            super.setLineColor(lineColor);
        }
    }

    @objid ("6117763b-55b6-11e2-877f-002564c97630")
    @Override
    public void setLineWidth(int lineWidth) {
        if (lineWidth != this.penOptions.lineWidth) {
            this.lineBorder.setWidth(lineWidth);
            super.setLineWidth(lineWidth);
        }
    }

    @objid ("6117763f-55b6-11e2-877f-002564c97630")
    @Override
    public void setLinePattern(LinePattern linePattern) {
        if (linePattern != this.penOptions.linePattern) {
            this.lineBorder.setStyle(linePattern.toSWTConstant());
            super.setLinePattern(linePattern);
        }
    }

}
