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

package org.modelio.uml.activitydiagram.editor.elements.action;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.modelio.diagram.elements.common.text.ElementTextEditPart;
import org.modelio.diagram.styles.core.IStyle;

/**
 * Edit part for {@link GmActionBody}.
 * <p>
 * Applies a specific font to the figure.
 */
@objid ("297f96da-55b6-11e2-877f-002564c97630")
public class ActionBodyEditPart extends ElementTextEditPart {
    /**
     * Overridden to use specific font.
     */
    @objid ("297f96de-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshFromStyle(IFigure aFigure, IStyle style) {
        super.refreshFromStyle(aFigure, style);
        aFigure.setFont(style.getFont(GmActionStructuredStyleKeys.BODYFONT));
    }

}
