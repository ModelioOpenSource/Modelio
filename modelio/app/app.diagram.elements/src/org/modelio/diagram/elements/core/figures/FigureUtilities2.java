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
package org.modelio.diagram.elements.core.figures;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.AbsoluteBendpoint;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.modelio.diagram.elements.core.link.MPoint;
import org.modelio.diagram.elements.plugin.DiagramElements;
import org.modelio.platform.ui.CoreFontRegistry;

/**
 * Utility class to make:
 * <ul>
 * <li>ghost shapes.
 * <li>highlight figures for link or box
 * </ul>
 * 
 * @author phv
 */
@objid ("7f79d9d6-1dec-11e2-8cad-001ec947c8cc")
public class FigureUtilities2 extends FigureUtilities {
    @objid ("7f79d9da-1dec-11e2-8cad-001ec947c8cc")
    private static Color ghostLineColor = ColorConstants.darkGray;

    @objid ("7f79d9e9-1dec-11e2-8cad-001ec947c8cc")
    public static IFigure createHighlightFigure(final IFigure feedbackLayer, final IFigure refFigure, final HighlightType type) {
        IFigure highLightFigure = null;
        
        if (refFigure instanceof PolylineConnection) {
            highLightFigure = createHighlightLink(feedbackLayer, (PolylineConnection) refFigure, type);
        } else {
            highLightFigure = createHighlightBox(feedbackLayer, refFigure, type);
        }
        
        // decorate depending on type
        updateHighlightType(highLightFigure, type);
        return highLightFigure;
    }

    /**
     * Get the same font as the given one but smaller.
     * @param baseFont the base font
     * @return the smaller font
     */
    @objid ("5fa20ee9-c8fb-4c28-b0b5-35579e3da0d7")
    public static Font getSmallerFont(Font baseFont) {
        FontData[] fontData = baseFont.getFontData();
        for (FontData data : fontData) {
            data.setHeight(getSmallerFontHeight(data.getHeight()));
        }
        return CoreFontRegistry.getFont(fontData);
    }

    /**
     * Produces a ghosting effect on the shape <i>s</i>.
     * @param s the shape
     * @param refFigure unused
     * @return the ghosted shape
     */
    @objid ("7f79d9dc-1dec-11e2-8cad-001ec947c8cc")
    public static Shape makeGhostShape(Shape s, IFigure refFigure) {
        Color penColor = ghostLineColor;
        if (refFigure != null && refFigure instanceof IPenOptionsSupport) {
            // penColor = ((IPenOptionsSupport) refFigure).getLineColor();
        }
        s.setForegroundColor(penColor);
        
        // s.setBackgroundColor(ghostFillColor);
        
        // s.setFillXOR(true);
        s.setFill(false);
        
        s.setOutlineXOR(false);
        s.setLineStyle(SWT.LINE_DOT);
        s.setLineWidth(2);
        return s;
    }

    @objid ("0651cbb6-549d-405f-a46a-1baf799903a1")
    private static void updatePenOptions(final IPenOptionsSupport highlightFigure, Color color) {
        highlightFigure.setLineColor(color);
        highlightFigure.setLineWidth(2);
        
    }

    @objid ("362f7db9-1db0-4c3b-ba70-c79056ba7cfd")
    private static void updateBrushOptions(final IFigure highlightFigure, Color color, int alpha) {
        if (highlightFigure instanceof IBrushOptionsSupport) {
            IBrushOptionsSupport b = (IBrushOptionsSupport) highlightFigure;
            b.setFillAlpha(alpha);
            b.setFillColor(color);
        }
        
    }

    @objid ("7f7c3c1b-1dec-11e2-8cad-001ec947c8cc")
    public static void updateHighlightType(final IFigure highlightFigure, final HighlightType type) {
        if (highlightFigure instanceof RectangleFigure) {
            RectangleFigure boxFigure = (RectangleFigure) highlightFigure;
            switch (type) {
            case SUCCESS:
                boxFigure.setBorder(new LineBorder(ColorConstants.lightGreen, 2));
                boxFigure.setBackgroundColor(ColorConstants.lightGreen);
                boxFigure.setAlpha(50);
                break;
            case ERROR:
                boxFigure.setBorder(new LineBorder(ColorConstants.red, 2));
                boxFigure.setBackgroundColor(ColorConstants.red);
                boxFigure.setAlpha(20);
                break;
            case WARNING:
                boxFigure.setBorder(new LineBorder(ColorConstants.orange, 2));
                boxFigure.setBackgroundColor(ColorConstants.orange);
                boxFigure.setAlpha(120);
                break;
            case INFO:
                boxFigure.setBorder(new LineBorder(ColorConstants.blue, 2));
                boxFigure.setBackgroundColor(ColorConstants.lightBlue);
                boxFigure.setAlpha(50);
                break;
            }
        } else if (highlightFigure instanceof PolylineConnection || highlightFigure instanceof Shape) {
            Shape shapeFigure = (Shape) highlightFigure;
            switch (type) {
            case SUCCESS:
                shapeFigure.setForegroundColor(ColorConstants.green);
                break;
            case ERROR:
                shapeFigure.setForegroundColor(ColorConstants.red);
                break;
            case WARNING:
                shapeFigure.setForegroundColor(ColorConstants.orange);
                break;
            case INFO:
                shapeFigure.setForegroundColor(ColorConstants.blue);
                break;
            }
        
        } else if (highlightFigure instanceof IPenOptionsSupport) {
            IPenOptionsSupport f = (IPenOptionsSupport)highlightFigure;
            switch (type) {
            case SUCCESS:
                updatePenOptions(f, ColorConstants.lightGreen);
                updateBrushOptions(highlightFigure, ColorConstants.lightGreen,50);
                break;
            case ERROR:
                updatePenOptions(f, ColorConstants.red);
                updateBrushOptions(highlightFigure, ColorConstants.red, 20);
                break;
            case WARNING:
                updatePenOptions(f, ColorConstants.orange);
                updateBrushOptions(highlightFigure, ColorConstants.orange, 120);
                break;
            case INFO:
                updatePenOptions(f, ColorConstants.blue);
                updateBrushOptions(highlightFigure, ColorConstants.lightBlue, 50);
                break;
            }
        } else {
            DiagramElements.LOG.warning("updateHighlightType() ignoring unsupported highlight figure type " +
                    highlightFigure.getClass().getSimpleName());
        }
        
    }

    @objid ("7f7c3c23-1dec-11e2-8cad-001ec947c8cc")
    private static IFigure createHighlightBox(final IFigure feedbackLayer, final IFigure refFigure, final HighlightType type) {
        RectangleFigure highlightFigure = new RectangleFigure();
        final Rectangle bounds = refFigure.getBounds().getCopy();
        refFigure.translateToAbsolute(bounds);
        feedbackLayer.translateToRelative(bounds);
        bounds.expand(1, 1);
        highlightFigure.setBounds(bounds);
        highlightFigure.setFill(true);
        return highlightFigure;
    }

    @objid ("7f79d9f9-1dec-11e2-8cad-001ec947c8cc")
    private static IFigure createHighlightLink(final IFigure feedbackLayer, final PolylineConnection refConnection, final HighlightType type) {
        PolylineConnection highlightFigure = new PolylineConnection();
        highlightFigure.removeAllPoints();
        highlightFigure.setSourceAnchor(refConnection.getSourceAnchor());
        highlightFigure.setTargetAnchor(refConnection.getTargetAnchor());
        highlightFigure.setConnectionRouter(refConnection.getConnectionRouter());
        
        if (refConnection.getRoutingConstraint() instanceof ArrayList<?>) {
            ArrayList<AbsoluteBendpoint> newRoutingConstraint = new ArrayList<>();
            for (Object o : (ArrayList<?>) refConnection.getRoutingConstraint()) {
                if (o instanceof AbsoluteBendpoint) {
                    AbsoluteBendpoint point = new MPoint((AbsoluteBendpoint) o, true);
                    refConnection.translateToAbsolute(point);
                    feedbackLayer.translateToRelative(point);
                    newRoutingConstraint.add(point);
                }
            }
            highlightFigure.setRoutingConstraint(newRoutingConstraint);
        
        } else {
            highlightFigure.setRoutingConstraint(refConnection.getRoutingConstraint());
        }
        
        highlightFigure.setLineWidth(refConnection.getLineWidth() + 2);
        highlightFigure.setLineStyle(refConnection.getLineStyle());
        return highlightFigure;
    }

    @objid ("c1c215e4-c0f3-4a65-b4af-83a69bd74c56")
    private static int getSmallerFontHeight(int height) {
        switch (height) {
        case 8:
            return 7;
        
        case 9:
            return 7;
        
        case 10:
            return 8;
        
        case 11:
            return 8;
        
        case 12:
            return 9;
        
        case 13:
            return 10;
        
        case 14:
            return 10;
        
        default:
            if (height < 8) {
                return height;
            } else {
                return height * 10 / 14;
            }
        }
        
    }

    @objid ("7f7c3c33-1dec-11e2-8cad-001ec947c8cc")
    public enum HighlightType {
        @objid ("7f7c3c34-1dec-11e2-8cad-001ec947c8cc")
        ERROR,
        @objid ("7f7c3c35-1dec-11e2-8cad-001ec947c8cc")
        WARNING,
        @objid ("7f7c3c36-1dec-11e2-8cad-001ec947c8cc")
        SUCCESS,
        @objid ("7f7c3c37-1dec-11e2-8cad-001ec947c8cc")
        INFO;

    }

}
