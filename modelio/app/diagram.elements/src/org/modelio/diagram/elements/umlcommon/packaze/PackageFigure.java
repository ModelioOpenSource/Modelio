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

package org.modelio.diagram.elements.umlcommon.packaze;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.AbstractHintLayout;
import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.CompoundBorder;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.TextUtilities;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.modelio.diagram.elements.core.figures.GradientFigure;
import org.modelio.diagram.elements.core.figures.IBrushOptionsSupport;
import org.modelio.diagram.elements.core.figures.IPenOptionsSupport;
import org.modelio.diagram.elements.core.figures.MinimumSizeLayout;
import org.modelio.diagram.elements.core.figures.PenOptions;
import org.modelio.diagram.elements.core.figures.borders.TLBRBorder;
import org.modelio.diagram.styles.core.StyleKey.LinePattern;

/**
 * Represents a package.
 * <p>
 * This figure is decomposed of an header area and a content area that each can receive one figure.
 * 
 * @author phv
 */
@objid ("8194ad2a-1dec-11e2-8cad-001ec947c8cc")
public class PackageFigure extends Figure implements IPenOptionsSupport, IBrushOptionsSupport {
    @objid ("8194ad2e-1dec-11e2-8cad-001ec947c8cc")
    private PenOptions penOptions;

    /**
     * area in which the header figure will be placed
     */
    @objid ("8194ad2f-1dec-11e2-8cad-001ec947c8cc")
    private GradientFigure headerArea;

    /**
     * area in which the content figure will be placed
     */
    @objid ("8194ad31-1dec-11e2-8cad-001ec947c8cc")
    private GradientFigure contentsArea;

    @objid ("d2304a1e-86a8-46f5-8f20-a671eb85f418")
    private Figure top;

    /**
     * Creates a package figure.
     */
    @objid ("8194ad3b-1dec-11e2-8cad-001ec947c8cc")
    public PackageFigure() {
        // init text and line pen support
        this.penOptions = new PenOptions();
        
        // The package figure is a container managing two areas the headerArea and the contentsArea
        // Dedicated figures can be set in each of theses areas.
        // The areas are transparent
        
        BorderLayout lm1 = new BorderLayout();
        lm1.setVerticalSpacing(-1);
        setLayoutManager(lm1);
        
        // The top figure contains the header area and a gradient figure. It receives a dedicated layout manager to maintain its width a a ratio of the package figure width
        this.top = new Figure();
        top.setLayoutManager(new HeaderAreaLayout());
        top.setOpaque(true);
        this.add(top, BorderLayout.TOP);
        
        // The package header area
        this.headerArea = new GradientFigure();
        this.headerArea.setOpaque(true);
        
        // Use a stack layout for label wrapping...
        final StackLayout layout = new StackLayout();
        this.headerArea.setLayoutManager(layout);
        top.add(this.headerArea);
        
        // The package contents area
        this.contentsArea = new GradientFigure();
        this.contentsArea.setLayoutManager(new BorderLayout());
        MinimumSizeLayout.apply(this.contentsArea, 100, 60);
        
        this.contentsArea.setOpaque(true);
        this.add(this.contentsArea, BorderLayout.CENTER);
        
        updateBorders();
        
        setOpaque(false);
    }

    @objid ("81970f87-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Color getFillColor() {
        return this.contentsArea.getFillColor();
    }

    @objid ("81970f93-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Color getLineColor() {
        return this.penOptions.lineColor;
    }

    @objid ("81970f98-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public LinePattern getLinePattern() {
        return this.penOptions.linePattern;
    }

    @objid ("81970f9d-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public int getLineWidth() {
        return this.penOptions.lineWidth;
    }

    @objid ("81970fa2-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Color getTextColor() {
        return this.penOptions.textColor;
    }

    @objid ("81970fa7-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Font getTextFont() {
        return this.penOptions.textFont;
    }

    @objid ("81970fac-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean getUseGradient() {
        return this.contentsArea.getUseGradient();
    }

    /**
     * Set the content figure that will be placed in the content area.
     * 
     * @param figure the content figure.
     */
    @objid ("81970fb1-1dec-11e2-8cad-001ec947c8cc")
    public void setContentsFigure(IFigure figure) {
        if (!this.contentsArea.getChildren().isEmpty()) {
            this.contentsArea.removeAll();
        }
        
        if (figure != null) {
            this.contentsArea.add(figure, BorderLayout.CENTER);
        }
    }

    @objid ("81970fb7-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setFillColor(Color fillColor) {
        this.headerArea.setFillColor(fillColor);
        this.contentsArea.setFillColor(fillColor);
    }

    /**
     * Set the header figure.
     * <p>
     * The header figure will be placed in the header area.
     * 
     * @param figure the new header figure.
     */
    @objid ("81970fbb-1dec-11e2-8cad-001ec947c8cc")
    public void setHeaderFigure(IFigure figure) {
        if (!this.headerArea.getChildren().isEmpty()) {
            this.headerArea.removeAll();
        }
        
        if (figure != null) {
            this.headerArea.add(figure, BorderLayout.LEFT);
            figure.setBackgroundColor(ColorConstants.red);
        }
    }

    @objid ("81970fc1-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setLineColor(Color lineColor) {
        this.penOptions.lineColor = lineColor;
        updateBorders();
    }

    @objid ("81970fc5-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setLinePattern(LinePattern linePattern) {
        this.penOptions.linePattern = linePattern;
        updateBorders();
    }

    @objid ("81970fc9-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setLineWidth(int lineWidth) {
        this.penOptions.lineWidth = lineWidth;
        updateBorders();
    }

    @objid ("81970fcd-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setTextColor(Color textColor) {
        this.penOptions.textColor = textColor;
    }

    @objid ("81970fd1-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setTextFont(Font textFont) {
        this.penOptions.textFont = textFont;
    }

    @objid ("81970fd5-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setUseGradient(boolean useGradient) {
        this.contentsArea.setUseGradient(useGradient);
        this.headerArea.setUseGradient(useGradient);
    }

    @objid ("81970fd9-1dec-11e2-8cad-001ec947c8cc")
    private void updateBorders() {
        final LineBorder headerLineBorder = new TLBRBorder(this.penOptions.lineColor, this.penOptions.lineWidth, true, true, false, true);
        headerLineBorder.setStyle(getLinePattern().toSWTConstant());
        this.headerArea.setBorder(new CompoundBorder(headerLineBorder, new MarginBorder(0, 0, 0, 0)));
        // this.top.setBorder(new CompoundBorder(headerLineBorder, new MarginBorder(0, 0, 0, 0)));
        
        final LineBorder contentLineBorder = new LineBorder(this.penOptions.lineColor, this.penOptions.lineWidth);
        contentLineBorder.setStyle(getLinePattern().toSWTConstant());
        this.contentsArea.setBorder(new CompoundBorder(contentLineBorder, new MarginBorder(1)));
    }

    @objid ("81970fdb-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Dimension getMinimumSize(int wHint, int hHint) {
        Dimension minimumSize = super.getMinimumSize(wHint, hHint);
        
        if (minimumSize.height < 100) {
            minimumSize.height = 100;
        }
        
        if (minimumSize.width < 100) {
            minimumSize.width = 100;
        }
        return minimumSize;
    }

    @objid ("464d5504-fd46-4550-bbe1-91b4367fc76c")
    @Override
    public void setFillAlpha(int alpha) {
        // ignore
    }

    @objid ("c109747c-e462-4f15-926e-fe3f5b588c04")
    @Override
    public int getFillAlpha() {
        // always opaque
        return 255;
    }

    /**
     * Specific layout for container of header zone.
     */
    @objid ("819971e2-1dec-11e2-8cad-001ec947c8cc")
    private static final class HeaderAreaLayout extends AbstractHintLayout {
        /**
         * Header must occupy at least 1/{@value #MIN_LEN_RATIO} of available horizontal space
         */
        @objid ("1d41d07d-3f0e-401c-8e8f-5d9e3016b903")
        private static final int MIN_LEN_RATIO = 4;

        /**
         * Header can occupy up to 1/{@value #MAX_LEN_RATIO} of available horizontal space
         */
        @objid ("58a66fbe-898a-44f6-bb29-d7030441a35d")
        private static final float MAX_LEN_RATIO = 3.0f / 2;

        @objid ("819971e6-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public void layout(final IFigure topFigure) {
            if (!topFigure.getChildren().isEmpty()) {
                IFigure headerAreaFigure = (IFigure) topFigure.getChildren().get(0);
            
                Rectangle headerAreaBounds = topFigure.getBounds().getShrinked(topFigure.getInsets());
            
                // Header can occupy up to 1/MAX_LEN_RATIO of available horizontal space
                // but no less than 1/MIN_LEN_RATIO, and all of vertical space.
                int topAreaWidth = headerAreaBounds.width;
                int minWidth = topAreaWidth / HeaderAreaLayout.MIN_LEN_RATIO;
                int maxWidth = (int) (topAreaWidth / HeaderAreaLayout.MAX_LEN_RATIO);
            
                // first ask for preferred size
                // -1 is needed because PageFlow takes whole available width hint
                Dimension headerPrefSize = headerAreaFigure.getPreferredSize(-1, headerAreaBounds.height);
            
                if (headerPrefSize.width() > maxWidth) {
                    // Ask for minimum size
                    headerPrefSize = headerAreaFigure.getMinimumSize(maxWidth, headerAreaBounds.height);
                }
            
                // Make size satisfy minimum and maximum
                headerAreaBounds.width = Math.min(headerPrefSize.width, maxWidth);
                headerAreaBounds.width = Math.max(headerAreaBounds.width, minWidth);
                headerAreaBounds.height = Math.min(headerAreaBounds.height, headerPrefSize.height);
            
                headerAreaFigure.setBounds(headerAreaBounds);
            }
        }

        @objid ("819971ed-1dec-11e2-8cad-001ec947c8cc")
        @Override
        protected Dimension calculatePreferredSize(final IFigure topFigure, final int wHint, final int hHint) {
            if (!topFigure.getChildren().isEmpty()) {
                IFigure headerAreaFigure = (IFigure) topFigure.getChildren().get(0);
                Dimension twotextLinesSize = getTwoLinesTextSize(topFigure);
                Dimension borderPrefSize = getBorderPreferredSize(topFigure);
                Insets topInsets = topFigure.getInsets();
            
                // Header can occupy up to 1/MAX_LEN_RATIO of available horizontal space and all of vertical space.
                int maxAreaWidth = wHint > 0 ? (int) ((wHint - topInsets.getWidth()) / HeaderAreaLayout.MAX_LEN_RATIO) : -1;
                int maxAreaHeight = hHint > 0 ? hHint - topInsets.getHeight() : -1;
                if (hHint > 0 && maxAreaHeight < 1) {
                    maxAreaHeight = twotextLinesSize.height;
                }
            
                // Compute base preferred size
                // -1 is needed because PageFlow takes whole available width hint
                Dimension prefSize = headerAreaFigure.getPreferredSize(-1, maxAreaHeight).getCopy();
            
                if (maxAreaWidth != -1 && prefSize.width() > maxAreaWidth) {
                    prefSize = headerAreaFigure.getMinimumSize(maxAreaWidth, maxAreaHeight).getCopy();
                }
            
                // prefSize.height = Math.min(prefSize.height, twotextLinesSize.height);
                prefSize.expand(topInsets.getWidth(), topInsets.getHeight());
                prefSize.union(borderPrefSize);
            
                return prefSize;
            
            } else {
                return new Dimension(0, 0);
            }
        }

        /**
         * Compute the size of 3 lines of text, to get the maximum height of a package header.
         * @param headerAreaFigure the package header area figure to get the font from
         * @return
         */
        @objid ("0e4cffea-5680-48a5-865f-4a0637fe087c")
        protected final Dimension getTwoLinesTextSize(IFigure topFigure) {
            Font f;
            IFigure pf = topFigure.getParent();
            if (pf instanceof PackageFigure) {
                f = ((PackageFigure) pf).getTextFont();
            } else {
                f = topFigure.getFont();
            }
            
            // ok this is 3 lines...
            return TextUtilities.INSTANCE.getTextExtents("Lj1\nLj2\nL", f);
        }

        /**
         * Needed empty constructor.
         */
        @objid ("54349f11-32ea-404d-968f-516fc8b755fe")
        public HeaderAreaLayout() {
            super();
        }

    }

}
