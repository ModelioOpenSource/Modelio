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
package org.modelio.diagram.elements.drawings.line;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.swt.graphics.Color;
import org.modelio.diagram.elements.core.figures.LinkFigure;
import org.modelio.diagram.elements.core.figures.decorations.DefaultPolygonDecoration;
import org.modelio.diagram.elements.core.figures.decorations.DefaultPolylineDecoration;
import org.modelio.diagram.elements.drawings.core.link.AbstractLinkDrawingEditPart;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.FillMode;
import org.modelio.diagram.styles.core.StyleKey.LinePattern;

/**
 * Edit part for {@link GmLineDrawing}.
 */
@objid ("799a55ca-9914-4f1b-a9f6-05b72cb3145d")
public class LineDrawingEditPart extends AbstractLinkDrawingEditPart {
    @objid ("ca94ed68-2ca6-48c3-90ad-71db2b4151e2")
    private static final PointList TRIANGLE_TIP = new PointList(new int[] { -1, 1, 0, 0, -1, -1 });

    @objid ("26bb4019-fae5-4405-b107-312f5816e95f")
    private static final PointList LOSANGE_TIP = new PointList(new int[] { -1, 1, 0, 0, -1, -1, -2, 0 });

    @objid ("28d9d627-62b9-4f6d-a081-69880315f3d6")
    @Override
    protected void refreshFromStyle(IFigure aFigure, IStyle style) {
        super.refreshFromStyle(aFigure, style);
        
        LinkFigure connection = (LinkFigure) aFigure;
        GmLineDrawing model = getModel();
        
        createDecorations(connection, model);
        
    }

    @objid ("be84e7d5-3536-4f89-a39d-a4d62be95257")
    private RotatableDecoration createDecoration(LineDecoration toAggregation, int scalex, int scaley, FillMode fill, Color fillColor, Color lineColor, LinePattern linePattern, Integer lineWidth) {
        switch (toAggregation) {
        case NONE:
            return null;
        case ARROW: {
            DefaultPolylineDecoration deco = new DefaultPolylineDecoration();
            deco.setTemplate(TRIANGLE_TIP);
            deco.setScale(scalex, scaley);
            deco.setOpaque(true);
            deco.setFill(fill != FillMode.TRANSPARENT);
            deco.setLineColor(lineColor);
            deco.setLineWidth(lineWidth);
            deco.setLinePattern(linePattern);
            deco.setBackgroundColor(fillColor);
            return deco;
        }
        case TRIANGLE: {
            DefaultPolygonDecoration deco = new DefaultPolygonDecoration();
            deco.setTemplate(TRIANGLE_TIP);
            deco.setScale(scalex, scaley);
            deco.setOpaque(true);
            deco.setFill(fill != FillMode.TRANSPARENT);
            deco.setUseGradient(fill == FillMode.GRADIENT);
            deco.setLineColor(lineColor);
            deco.setLineWidth(lineWidth);
            deco.setLinePattern(linePattern);
            deco.setFillColor(fillColor);
            return deco;
        }
        case LOSANGE:
            DefaultPolygonDecoration deco = new DefaultPolygonDecoration();
            deco.setTemplate(LOSANGE_TIP);
            deco.setScale(scalex, scaley);
            deco.setFill(fill != FillMode.TRANSPARENT);
            deco.setUseGradient(fill == FillMode.GRADIENT);
            deco.setLineColor(lineColor);
            deco.setLineWidth(lineWidth);
            deco.setLinePattern(linePattern);
            deco.setFillColor(fillColor);
            return deco;
        default:
            break;
        }
        return null;
    }

    @objid ("4fcadb77-9a5c-4248-87c4-db36bdf5d020")
    @Override
    public GmLineDrawing getModel() {
        return (GmLineDrawing) super.getModel();
    }

    @objid ("9f0aa048-5d25-4732-b8a6-e025b2ed6014")
    @Override
    protected void refreshVisuals() {
        super.refreshVisuals();
    }

    @objid ("cba7732f-7d8b-41ff-9a1f-c3c47e716855")
    private void createDecorations(LinkFigure connection, GmLineDrawing model) {
        RotatableDecoration deco;
        
        LineDecoration kind;
        int scalex;
        int scaley;
        FillMode fillmode;
        Integer lineWidth = null;
        Color fillColor;
        Color lineColor;
        LinePattern linePattern;
        
        final IStyle style = model.getDisplayedStyle();
        
        kind = style.getProperty(GmLineStyleKeys.SourceDeco.KIND);
        scalex = style.getProperty(GmLineStyleKeys.SourceDeco.SCALEX);
        scaley = style.getProperty(GmLineStyleKeys.SourceDeco.SCALEY);
        fillmode = style.getProperty(GmLineStyleKeys.SourceDeco.FILLMODE);
        fillColor = style.getProperty(GmLineStyleKeys.SourceDeco.FILLCOLOR);
        lineColor = style.getProperty(GmLineStyleKeys.SourceDeco.LINECOLOR);
        linePattern = style.getProperty(GmLineStyleKeys.SourceDeco.LINEPATTERN);
        
        if (fillColor==null && model.getStyleKey(MetaKey.FILLCOLOR) != null)
            fillColor = style.getColor(model.getStyleKey(MetaKey.FILLCOLOR));
        if (lineColor==null && model.getStyleKey(MetaKey.LINECOLOR) != null)
            lineColor = style.getColor(model.getStyleKey(MetaKey.LINECOLOR));
        if (linePattern==null && model.getStyleKey(MetaKey.LINEPATTERN) != null)
            linePattern = style.getProperty(model.getStyleKey(MetaKey.LINEPATTERN));
        if (model.getStyleKey(MetaKey.LINEWIDTH) != null) 
            lineWidth = style.getInteger(model.getStyleKey(MetaKey.LINEWIDTH));
        
        
        deco = createDecoration(kind, scalex,scaley,fillmode, fillColor, lineColor, linePattern, lineWidth);
        connection.setSourceDecoration(deco);
        
        kind = style.getProperty(GmLineStyleKeys.TargetDeco.KIND);
        scalex = style.getProperty(GmLineStyleKeys.TargetDeco.SCALEX);
        scaley = style.getProperty(GmLineStyleKeys.TargetDeco.SCALEY);
        fillmode = style.getProperty(GmLineStyleKeys.TargetDeco.FILLMODE);
        fillColor = style.getProperty(GmLineStyleKeys.TargetDeco.FILLCOLOR);
        lineColor = style.getProperty(GmLineStyleKeys.TargetDeco.LINECOLOR);
        linePattern = style.getProperty(GmLineStyleKeys.TargetDeco.LINEPATTERN);
        
        if (fillColor==null && model.getStyleKey(MetaKey.FILLCOLOR) != null)
            fillColor = style.getColor(model.getStyleKey(MetaKey.FILLCOLOR));
        if (lineColor==null && model.getStyleKey(MetaKey.LINECOLOR) != null)
            lineColor = style.getColor(model.getStyleKey(MetaKey.LINECOLOR));
        if (linePattern==null && model.getStyleKey(MetaKey.LINEPATTERN) != null)
            linePattern = style.getProperty(model.getStyleKey(MetaKey.LINEPATTERN));
        if (lineWidth==null && model.getStyleKey(MetaKey.LINEWIDTH) != null) 
            lineWidth = style.getInteger(model.getStyleKey(MetaKey.LINEWIDTH));
        
        deco = createDecoration(kind, scalex,scaley,fillmode, fillColor, lineColor, linePattern, lineWidth);
        connection.setTargetDecoration(deco);
        
    }

}
