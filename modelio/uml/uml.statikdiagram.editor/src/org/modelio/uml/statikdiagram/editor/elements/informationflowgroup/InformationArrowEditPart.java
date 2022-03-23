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
package org.modelio.uml.statikdiagram.editor.elements.informationflowgroup;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.PointList;
import org.modelio.diagram.elements.core.figures.decorations.DefaultPolygonDecoration;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;

/**
 * Edit part for {@link GmInformationFlowArrow}.
 * <p>
 * Creates a filled arrow on the connection owning it. The arrow is filled with the line color.
 * 
 * @author cmarin
 */
@objid ("81734c4a-1dec-11e2-8cad-001ec947c8cc")
public class InformationArrowEditPart extends AbstractNodeEditPart {
    @objid ("64159fd8-1e83-11e2-8cad-001ec947c8cc")
    private static final PointList TRIANGLE_TIP = new PointList(new int[] { -1, 1, 0, 0, -1, -1 });

    /**
     * The arrow is not selectable.
     */
    @objid ("81734c50-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean isSelectable() {
        return false;
    }

    @objid ("81734c56-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected IFigure createFigure() {
        final DefaultPolygonDecoration deco = new DefaultPolygonDecoration();
        deco.setTemplate(TRIANGLE_TIP);
        deco.setScale(12, 10);
        deco.setOpaque(true);
        deco.setFill(true);
        
        refreshFromStyle(deco, getModelStyle());
        return deco;
    }

    @objid ("81734c5d-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void refreshFromStyle(final IFigure aFigure, final IStyle style) {
        super.refreshFromStyle(aFigure, style);
        
        final DefaultPolygonDecoration pen = (DefaultPolygonDecoration) aFigure;
        
        IGmObject gmModel = getModel();
        
        if (gmModel.getStyleKey(MetaKey.LINECOLOR) != null)
            pen.setFillColor(style.getColor(gmModel.getStyleKey(MetaKey.LINECOLOR)));
        
    }

}
