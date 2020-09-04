/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.diagram.elements.core.figures.html.flyingsaucer.impl;

import java.awt.Rectangle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontMetrics;
import org.xhtmlrenderer.extend.FSGlyphVector;
import org.xhtmlrenderer.extend.FontContext;
import org.xhtmlrenderer.extend.OutputDevice;
import org.xhtmlrenderer.extend.TextRenderer;
import org.xhtmlrenderer.render.FSFont;
import org.xhtmlrenderer.render.FSFontMetrics;
import org.xhtmlrenderer.render.JustificationInfo;
import org.xhtmlrenderer.swt.SWTFSFont;
import org.xhtmlrenderer.util.Configuration;

/**
 * Render text with SWT.
 * 
 * @author cmarin
 */
@objid ("4cae9e8a-bbb4-45be-b249-37209f508b9a")
class GefFsTextRenderer implements TextRenderer {
    @objid ("f0cdba8d-e351-4abe-bc43-0614c5eaa8f2")
    private float _scale;

    @objid ("dee18d8c-62bb-45c3-a274-4a789e6f5ccc")
    private boolean _antialiasing;

    @objid ("3ba81c1c-c340-4af2-979f-26987e70fbd2")
    private boolean antialiasApplied;

    /**
     * C'tor
     */
    @objid ("5aee975d-9398-4dca-b5e4-f36590ec5b0c")
    public GefFsTextRenderer() {
        this._scale = Configuration.valueAsFloat("xr.text.scale", 1.0f);
        setSmoothingThreshold(Configuration.valueAsInt(
                "xr.text.aa-fontsize-threshhold", 0));
    }

    @objid ("9e14c8a2-1b8c-469f-a5d4-e9d7c3c7bba4")
    private void applySmoothing(Graphics gc) {
        if (!this.antialiasApplied) {
            gc.setTextAntialias(this._antialiasing ? SWT.ON : SWT.OFF);
            this.antialiasApplied = true;
        }
    }

    @objid ("59024741-f81e-4fd9-9644-d74a12f491f3")
    @Override
    public void setup(FontContext context) {
        // noop
    }

    @objid ("34bc72fa-2a8f-45e6-80f2-06c801cf218b")
    @Override
    public void drawString(OutputDevice outputDevice, String string, float x, float y) {
        Graphics gc = ((GefFsOutputDevice) outputDevice).getGC();
        applySmoothing(gc);
        
        FontMetrics metrics = gc.getFontMetrics();
        float y2 = y - (metrics.getAscent() + metrics.getLeading());
        gc.drawText(string, (int) x, (int) y2);
    }

    @objid ("ba1b4889-b631-4990-b1cf-07c622429992")
    @Override
    public FSFontMetrics getFSFontMetrics(FontContext context, FSFont font, String string) {
        return new GefFsFontMetricsAdapter((SWTFSFont) font);
    }

    @objid ("7a9f697a-b7e7-46a2-8452-14bf12260ce5")
    @Override
    public int getWidth(FontContext context, FSFont font, String string) {
        final Font swtFont = ((SWTFSFont) font).getSWTFont();
        Dimension extents = FigureUtilities.getTextExtents(string, swtFont);
        int width = extents.width;
        return width;
    }

    @objid ("e5205a7a-a3b8-4b60-94fb-461b277f41bd")
    @Override
    public float getFontScale() {
        return this._scale;
    }

    @objid ("00689a24-0fdf-4c45-b9b6-6ffa3975aa58")
    @Override
    public int getSmoothingLevel() {
        return 0;
    }

    @objid ("1636b8bc-ae5a-4c67-90ad-9d5bec89d0c6")
    @Override
    public void setFontScale(float scale) {
        this._scale = scale;
    }

    @objid ("4d60b4ff-3034-43ad-99ef-948823cdffe6")
    @Override
    public void setSmoothingLevel(int level) {
        /* no-op */
    }

    @objid ("95415234-4dd8-40d6-9757-bb85f34f409e")
    @Override
    public void setSmoothingThreshold(float fontsize) {
        this._antialiasing = (fontsize >= 0);
    }

    @objid ("7aadb577-3cf5-4513-a06d-fb1a74b9f785")
    @Override
    public void drawGlyphVector(OutputDevice outputDevice, FSGlyphVector vector, float x, float y) {
        throw new UnsupportedOperationException();
    }

    @objid ("4c63eac2-5ef3-4482-9272-9c48944a4fad")
    @Override
    public void drawString(OutputDevice outputDevice, String string, float x, float y, JustificationInfo info) {
        // dev note: Justification is not perfect but I cannot do better.
        // I think the library is not perfect because each justified text line ends with a space
        // and justification info does not ignore it.
        
        final Graphics gc = ((GefFsOutputDevice) outputDevice).getGC();
        applySmoothing(gc);
        
        final Dimension extent = new Dimension();
        final FontMetrics metrics = gc.getFontMetrics();
        final double y2 = y - (metrics.getAscent() + metrics.getLeading());
        
        double x2 = x;
        for (int i = 0, len = string.length(); i < len; ++i) {
            int c = string.codePointAt(i);
            String sc = string.substring(i, i + 1);
        
            // Draw the letter
            gc.drawText(sc, (int) Math.round(x2), (int) y2);
        
            // Compute next letter position
            FigureUtilities.getTextExtents(sc, gc.getFont(), extent);
        
            x2 += extent.width;
        
            // Add justification
            if (Character.getType(c) == Character.SPACE_SEPARATOR) {
                x2 += info.getSpaceAdjust();
            } else {
                x2 += info.getNonSpaceAdjust();
            }
        
        }
        
        // draw debug rectangle
        // gc.drawRectangle((int)x, (int)y, (int)(x2-x), extent.height);
    }

    @objid ("3d0a2f0a-c97c-41fa-9849-c3d5a37683eb")
    @Override
    public Rectangle getGlyphBounds(OutputDevice outputDevice, FSFont font, FSGlyphVector fsGlyphVector, int index, float x, float y) {
        throw new UnsupportedOperationException();
    }

    @objid ("2108d29b-119a-4a9f-aaa0-061343b140ff")
    @Override
    public float[] getGlyphPositions(OutputDevice outputDevice, FSFont font, FSGlyphVector fsGlyphVector) {
        throw new UnsupportedOperationException();
    }

    @objid ("5f7f4525-e35e-49de-822e-f673731f2b42")
    @Override
    public FSGlyphVector getGlyphVector(OutputDevice outputDevice, FSFont font, String string) {
        throw new UnsupportedOperationException();
    }

}
