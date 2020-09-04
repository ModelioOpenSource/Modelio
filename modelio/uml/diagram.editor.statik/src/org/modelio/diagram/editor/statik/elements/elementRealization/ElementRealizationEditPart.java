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

package org.modelio.diagram.editor.statik.elements.elementRealization;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.modelio.diagram.elements.core.figures.LinkFigure;
import org.modelio.diagram.elements.core.figures.decorations.DefaultPolygonDecoration;
import org.modelio.diagram.elements.core.link.LinkEditPart;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;

/**
 * Interface realization link edit part.
 */
@objid ("590daac1-df67-4185-9d5d-0767c191975a")
public class ElementRealizationEditPart extends LinkEditPart {
    @objid ("a2e6df54-9384-4eb0-b3de-c85de7c3c679")
    @Override
    protected IFigure createFigure() {
        PolylineConnection connection = (PolylineConnection) super.createFigure();
        
        // Arrow toward target
        DefaultPolygonDecoration arrow = new DefaultPolygonDecoration();
        arrow.setTemplate(PolygonDecoration.TRIANGLE_TIP);
        arrow.setScale(8, 5);
        arrow.setOpaque(true);
        arrow.setFill(true);
        connection.setTargetDecoration(arrow);
        
        refreshFromStyle(connection, getModelStyle());
        return connection;
    }

    @objid ("cbbc98e5-e4e2-486e-9d81-f415c01d1248")
    @Override
    protected void refreshFromStyle(IFigure aFigure, IStyle style) {
        final LinkFigure genFigure = (LinkFigure) aFigure;
        
        super.refreshFromStyle(aFigure, style);
        
        final GmModel gmModel = getModel();
        
        if (genFigure.getTargetDecoration() != null && gmModel.getStyleKey(MetaKey.FILLCOLOR) != null)
            genFigure.getTargetDecoration()
                     .setBackgroundColor(style.getColor(gmModel.getStyleKey(MetaKey.FILLCOLOR)));
    }

}
