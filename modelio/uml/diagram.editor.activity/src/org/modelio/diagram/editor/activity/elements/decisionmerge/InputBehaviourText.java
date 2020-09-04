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

package org.modelio.diagram.editor.activity.elements.decisionmerge;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.PointList;
import org.modelio.diagram.elements.common.text.MultilineTextFigure;
import org.modelio.diagram.elements.core.figures.borders.ShapedBorder;
import org.modelio.diagram.elements.umlcommon.note.NoteFigure;

/**
 * @author fpoyer
 */
@objid ("2a477ac8-55b6-11e2-877f-002564c97630")
public class InputBehaviourText extends MultilineTextFigure {
    @objid ("2a477ace-55b6-11e2-877f-002564c97630")
    private static final int FOLDSIZE = 12;

    @objid ("2a477acc-55b6-11e2-877f-002564c97630")
    private static final PointList foldTemplate = new PointList(new int[] { 0, 0, 0,
            InputBehaviourText.FOLDSIZE, InputBehaviourText.FOLDSIZE, InputBehaviourText.FOLDSIZE });

    /**
     * @param text the text to display.
     */
    @objid ("2a477ad0-55b6-11e2-877f-002564c97630")
    public InputBehaviourText(String text) {
        super(text);
        setBorder(new ShapedBorder(this.getLineColor(), getLineWidth(), new NoteFigure.NoteShaper()));
    }

    @objid ("2a477ad4-55b6-11e2-877f-002564c97630")
    @Override
    protected void paintFigure(Graphics graphics) {
        super.paintFigure(graphics);
    }

}
