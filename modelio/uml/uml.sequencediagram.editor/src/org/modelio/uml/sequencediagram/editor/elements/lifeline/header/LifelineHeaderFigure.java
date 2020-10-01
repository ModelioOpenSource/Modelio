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

package org.modelio.uml.sequencediagram.editor.elements.lifeline.header;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.CompoundBorder;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.swt.graphics.Color;
import org.modelio.diagram.elements.common.header.WrappedHeaderFigure;
import org.modelio.diagram.styles.core.StyleKey.LinePattern;

/**
 * Specialized figure for the life line header: add a border that is the compound of a line border and a margin border, and handles the relative graphical properties.
 */
@objid ("d9483350-55b6-11e2-877f-002564c97630")
public class LifelineHeaderFigure extends WrappedHeaderFigure {
    @objid ("d9483355-55b6-11e2-877f-002564c97630")
    private static final int MARGIN = 2;

    @objid ("7bb41f84-f54f-40dc-9e3f-95b876af7fd1")
    private LineBorder lineBorder;

    /**
     * Creates a new rectangular figure.
     */
    @objid ("d9483357-55b6-11e2-877f-002564c97630")
    public LifelineHeaderFigure() {
        super();
        this.lineBorder = new LineBorder();
        this.setBorder(new CompoundBorder(this.lineBorder, new MarginBorder(MARGIN)));
    }

    @objid ("d949b9ba-55b6-11e2-877f-002564c97630")
    @Override
    public void setLineColor(final Color lineColor) {
        this.lineBorder.setColor(lineColor);
        super.setLineColor(lineColor);
    }

    @objid ("d949b9bf-55b6-11e2-877f-002564c97630")
    @Override
    public void setLinePattern(final LinePattern linePattern) {
        this.lineBorder.setStyle(linePattern.toSWTConstant());
        super.setLinePattern(linePattern);
    }

    @objid ("d949b9c6-55b6-11e2-877f-002564c97630")
    @Override
    public void setLineWidth(final int lineWidth) {
        this.lineBorder.setWidth(lineWidth);
        super.setLineWidth(lineWidth);
    }

}
