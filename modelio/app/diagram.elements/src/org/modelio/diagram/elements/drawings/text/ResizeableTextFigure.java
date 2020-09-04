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

package org.modelio.diagram.elements.drawings.text;

import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.text.FlowPage;
import org.eclipse.draw2d.text.TextFlow;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.modelio.diagram.elements.core.figures.GradientFigure;
import org.modelio.diagram.elements.drawings.core.HAlign;
import org.modelio.ui.CoreFontRegistry;

/**
 * Figure for {@link GmTextDrawing}.
 * <p>
 * Draws a multi-line text flow.
 * The text alignment, font and color can be specified.
 * <p>
 * The font size is automatically adjusted to fit the figure bounds.
 */
@objid ("22e46ffb-08b9-42c5-88db-9ff9a21a191a")
public class ResizeableTextFigure extends GradientFigure {
    @objid ("ccbbecce-0179-4c35-91e9-2ab6f7e54770")
    private FlowPage contents;

    @objid ("186e7da2-a7ad-4dec-bdac-3b53100a7742")
    private TextFlow contentsText;

    @objid ("77ed7e9c-9d56-436f-b7f0-a72cb1eb6737")
    private Font baseFont;

    @objid ("9d1f7f15-7eaa-48cf-b4d1-24b3ea63d87d")
    private Dimension textSize = null;

    /**
     * Creates the figure.
     */
    @objid ("4c27796d-619c-4934-842f-0f709e487fb1")
    public ResizeableTextFigure() {
        this.contents = new FlowPage();
        this.contentsText = new TextFlow();
        this.contents.add(this.contentsText);
        //this.contents.setBorder(new MarginBorder(2));
        this.contents.setOpaque(false);
        this.contents.setHorizontalAligment(PositionConstants.LEFT);
        
        add(this.contents);
        setLayoutManager(new FlowLayout());
        setOpaque(false);
    }

    /**
     * Set the note text.
     * 
     * @param contents the note text.
     */
    @objid ("cc26174a-7bfe-4fff-8a49-47f6698d2527")
    public void setContents(String contents) {
        this.contentsText.setText(contents);
        updateFont();
    }

    /**
     * Set the text horizontal alignment.
     * 
     * @param align the text horizontal alignment.
     */
    @objid ("54893cf2-49ea-45ab-9b6d-953aae5ba5e0")
    public void setHorizontalAligment(HAlign align) {
        int ialign;
        switch (align) {
        case Center:
            ialign = PositionConstants.CENTER;
            break;
        case Left:
            ialign = PositionConstants.LEFT;
            break;
        case Right:
            ialign = PositionConstants.RIGHT;
            break;
        default:
            ialign = PositionConstants.CENTER;
            break;
        }
            
        if (this.contents.getHorizontalAligment() != ialign) {
            this.contents.setHorizontalAligment(ialign);
        }
    }

    @objid ("5440fd49-7e09-48f9-9316-0f0f31a4653b")
    @Override
    public void setTextColor(Color textColor) {
        this.contents.setForegroundColor(textColor);
        super.setTextColor(textColor);
    }

    @objid ("7b2c0ae8-b5c0-4066-ab78-f7adb92c606a")
    @Override
    public void setTextFont(Font textFont) {
        if (! Objects.equals(this.baseFont, textFont)) {
            this.baseFont = textFont;
        
            updateFont();
        }
    }

    @objid ("cdc8b51c-c88e-419c-b0fb-c75c812b7b73")
    @Override
    public void validate() {
        if (this.textSize == null)
            updateFont();
        
        super.validate();
    }

    @objid ("22e1e2b5-4a68-428c-944e-cfc50690aec5")
    private void updateFont() {
        Font finalFont = computeFont(getClientArea());
        this.contents.setFont(finalFont);
        super.setTextFont(finalFont);
    }

    @objid ("fe051950-cf3a-435c-bd82-c68355baf29d")
    @Override
    public void setBounds(Rectangle rect) {
        final Rectangle oldBounds = getBounds();
        if (oldBounds == null || oldBounds.width != rect.width || oldBounds.height != rect.height) {
            setPreferredSize(rect.getSize());
            super.setBounds(rect);
            updateFont();
        } else {
            super.setBounds(rect);
        }
    }

    @objid ("08384d44-dc09-4fac-8b8d-dc4721f6da9a")
    private Font computeFont(Rectangle rect) {
        if (rect.isEmpty())
            return this.baseFont;
        
        final double maxTextW = rect.width - 2;
        final double maxTextH = rect.height - 2;
        final String text = this.contentsText.getText();
        final Dimension extents = FigureUtilities.getTextExtents(text, this.baseFont);
        
        final double zoomH = maxTextH / extents.height;
        final double zoomW = maxTextW / extents.width;
        
        double neededZoom = Math.max(zoomH, zoomW);
        
        if (extents.width * neededZoom > maxTextW)
            neededZoom = zoomW;
        else if (extents.height * neededZoom > maxTextH)
            neededZoom = zoomH;
        
        this.textSize = extents.scale(neededZoom);
        
        final FontData[] fontData = this.baseFont.getFontData();
        for (FontData d : fontData) {
            d.setHeight((int) (d.getHeight() * neededZoom));
            //d.setStyle(SWT.BOLD);
        }
        return CoreFontRegistry.getFont(fontData);
    }

}
