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

package org.modelio.diagram.elements.factories.drawing;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.modelio.diagram.elements.drawings.core.GmDrawing;
import org.modelio.diagram.elements.drawings.ellipse.EllipseDrawingEditPart;
import org.modelio.diagram.elements.drawings.ellipse.GmEllipseDrawing;
import org.modelio.diagram.elements.drawings.layer.DrawingLayerEditPart;
import org.modelio.diagram.elements.drawings.layer.GmDrawingLayer;
import org.modelio.diagram.elements.drawings.line.GmLineDrawing;
import org.modelio.diagram.elements.drawings.line.LineDrawingEditPart;
import org.modelio.diagram.elements.drawings.rectangle.GmRectangleDrawing;
import org.modelio.diagram.elements.drawings.rectangle.RectangleDrawingEditPart;
import org.modelio.diagram.elements.drawings.text.GmTextDrawing;
import org.modelio.diagram.elements.drawings.text.TextDrawingEditPart;

/**
 * Implementation of {@link EditPartFactory} returning edit parts for drawings.
 */
@objid ("52afb5ef-6391-4c37-848c-6d51d55d81fb")
public class DrawingEditPartFactory implements EditPartFactory {
    @objid ("4810a011-785d-42de-bd94-05775f78d0a1")
    @Override
    public EditPart createEditPart(EditPart context, Object model) {
        if (model instanceof GmRectangleDrawing) {
            final RectangleDrawingEditPart editPart = new RectangleDrawingEditPart();
            editPart.setModel(model);
            return editPart;
        }
        
        if (model instanceof GmEllipseDrawing) {
            final EllipseDrawingEditPart editPart = new EllipseDrawingEditPart();
            editPart.setModel(model);
            return editPart;
        }
        
        if (model instanceof GmDrawingLayer) {
            DrawingLayerEditPart p = new DrawingLayerEditPart();
            p.setModel(model);
            return p;
        }
        
        if (model instanceof GmTextDrawing) {
            TextDrawingEditPart p = new TextDrawingEditPart();
            p.setModel(model);
            return p;
        }
        
        if (model instanceof GmLineDrawing) {
            LineDrawingEditPart p = new LineDrawingEditPart();
            p.setModel(model);
            return p;
        }
        
        if (model instanceof GmDrawing || model instanceof GmDrawingLayer) {
            throw new IllegalArgumentException(model + " drawing is not supported.");
        }
        return null;
    }

}
