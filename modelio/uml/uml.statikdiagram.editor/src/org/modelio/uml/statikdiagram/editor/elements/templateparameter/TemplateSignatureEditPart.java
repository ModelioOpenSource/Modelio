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
package org.modelio.uml.statikdiagram.editor.elements.templateparameter;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.swt.graphics.Color;
import org.modelio.diagram.elements.common.header.FlatHeaderFigure;
import org.modelio.diagram.elements.common.label.modelelement.ModelElementLabelEditPart;
import org.modelio.diagram.elements.core.figures.borders.ZoomableLineBorder;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.FillMode;

/**
 * Edit part for {@link GmTemplateSignature}.
 */
@objid ("36e9ed53-55b7-11e2-877f-002564c97630")
public class TemplateSignatureEditPart extends ModelElementLabelEditPart {
    @objid ("36e9ed57-55b7-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        TemplateSignatureFigure fig = new TemplateSignatureFigure();
        
        // Style independent
        fig.setOpaque(true);
        
        // Style dependant
        refreshFromStyle(fig, getModelStyle());
        return fig;
    }

    @objid ("36e9ed5c-55b7-11e2-877f-002564c97630")
    private void updateFigureBorder(final TemplateSignatureFigure aFigure) {
        final ZoomableLineBorder inner = new ZoomableLineBorder(aFigure.getLineColor(), aFigure.getLineWidth());
        
        // inner.setWidth(1);
        inner.setStyle(Graphics.LINE_DASH);
        
        aFigure.setBorder(inner);
        
    }

    @objid ("36e9ed60-55b7-11e2-877f-002564c97630")
    @Override
    protected void refreshFromStyle(final IFigure fig, final IStyle style) {
        super.refreshFromStyle(fig, style);
        
        final TemplateSignatureFigure theFig = (TemplateSignatureFigure) fig;
        final GmTemplateSignature gmModel = (GmTemplateSignature) getModel();
        
        if (gmModel.getStyleKey(MetaKey.LINECOLOR) != null) {
            theFig.setLineColor(style.getColor(gmModel.getStyleKey(MetaKey.LINECOLOR)));
        }
        if (gmModel.getStyleKey(MetaKey.LINEWIDTH) != null) {
            theFig.setLineWidth(style.getInteger(gmModel.getStyleKey(MetaKey.LINEWIDTH)));
        }
        
        if (gmModel.getStyleKey(MetaKey.FILLCOLOR) != null) {
            theFig.setFillColor(style.getColor(gmModel.getStyleKey(MetaKey.FILLCOLOR)));
        }
        
        if (gmModel.getStyleKey(MetaKey.FILLMODE) != null) {
            switch ((FillMode) style.getProperty(gmModel.getStyleKey(MetaKey.FILLMODE))) {
            case GRADIENT:
                theFig.setUseGradient(true);
                break;
            case SOLID:
                theFig.setUseGradient(false);
                break;
            case TRANSPARENT:
                theFig.setFillColor(null);
                break;
            }
        }
        
        updateFigureBorder((TemplateSignatureFigure) fig);
        
    }

    @objid ("36eb73c0-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isSelectable() {
        return false;
    }

    /**
     * Figure for {@link GmTemplateSignature}.
     * <p>
     * It is a model element header with a dashed border.
     */
    @objid ("36eb73c5-55b7-11e2-877f-002564c97630")
    public static class TemplateSignatureFigure extends FlatHeaderFigure {
        @objid ("36eb73ca-55b7-11e2-877f-002564c97630")
        @Override
        public void setLineColor(final Color lineColor) {
            if (this.penOptions.lineColor != lineColor) {
                this.penOptions.lineColor = lineColor;
                this.repaint();
            }
            
        }

        @objid ("36eb73cf-55b7-11e2-877f-002564c97630")
        @Override
        public void setLineWidth(final int lineWidth) {
            if (this.penOptions.lineWidth != lineWidth) {
                this.penOptions.lineWidth = lineWidth;
                this.repaint();
            }
            
        }

        @objid ("36eb73d4-55b7-11e2-877f-002564c97630")
        @Override
        public int getLineWidth() {
            return this.penOptions.lineWidth;
        }

        @objid ("36eb73d9-55b7-11e2-877f-002564c97630")
        @Override
        public Color getLineColor() {
            return this.penOptions.lineColor;
        }

    }

}
