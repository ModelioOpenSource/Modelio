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
package org.modelio.audit.view.statusbar;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;

/**
 * ProgressBar2 This SWT implementation of a ProgressBar displays a text showing the current value of the progress bar. It also correctly resizes according to its layout data constraints.
 * 
 * By default the displayed text value is a percentage computed as selection/(maximum-minimum) * 100. To display another value, redefine the {@link ProgressBar2#getSelectionLabel()} method.
 */
@objid ("aff5d12f-08c1-4207-9fe6-fb07b60adcee")
public class ProgressBar2 extends Composite {
    @objid ("16d5ecb0-30ed-470e-ab9b-6b54c589cca5")
    private int minimum = 0;

    @objid ("ad190999-02b9-44c4-a9aa-6bbb848cc857")
    private int maximum = 100;

    @objid ("7cc5b01d-ee1f-4e27-addb-4de0f9dca940")
    private int selection = 0;

    @objid ("481d7ced-9da7-4fdd-83e1-a651229e3575")
    private Color textColor;

    @objid ("ee231947-41c9-4716-b17e-fc930ea6ad15")
    public  ProgressBar2(Composite parent, int style) {
        super(parent, style);
        
        // Set a default size
        setSize(100, getFont().getFontData()[0].getHeight() + 8);
        
        addDisposeListener((e) -> {
            if (ProgressBar2.this.textColor != null) {
                ProgressBar2.this.textColor.dispose();
            }
        });
        
        addPaintListener(new PaintListener() {
            @Override
            public void paintControl(PaintEvent e) {
                ProgressBar2 pb = (ProgressBar2) e.widget;
                Color fgColor = pb.getForeground();
                Color txtColor = pb.getTextColor(pb.getBackground(), fgColor);
        
                // Draw whole background
                e.gc.fillRectangle(e.x, e.y, e.width, e.height);
        
                // Draw scale indicator
                int normalizedSelection = Math.max(pb.minimum, Math.min(pb.maximum, pb.selection));
                float ratio = (float) normalizedSelection / pb.maximum;
                e.gc.setBackground(fgColor);
                e.gc.fillRectangle(e.x, e.y, (int) (e.width * ratio), e.height);
        
                e.gc.setForeground(txtColor);
        
                String s = getSelectionLabel();
                FontMetrics fontMetrics = e.gc.getFontMetrics();
                int sWidth = (int) (fontMetrics.getAverageCharacterWidth() * s.length());
                int sHeight = fontMetrics.getHeight();
                e.gc.drawText(s, e.x + e.width / 2 - sWidth / 2, e.y + 1+ e.height / 2 - sHeight / 2, true);
        
            }
        });
        
    }

    /**
     * Redefine to change the displayed value. By default, display a percentage.
     * @return
     */
    @objid ("6010995e-25b1-4c07-bb02-921cb659971f")
    public String getSelectionLabel() {
        return String.format("%.1f %%", (this.selection * 1.0 / (this.maximum - this.minimum) * 100));
    }

    @objid ("258d0164-4d07-41c7-8052-18ab7f979a9f")
    public void setSelection(int value) {
        this.selection = value;
        this.redraw();
        
    }

    @objid ("1cbaf69d-a844-4d88-a3b7-952db8e47a10")
    public void setMaximum(int maximum) {
        this.maximum = maximum;
    }

    @objid ("27b2a77c-e012-4bb1-bbc4-1130b8cf404c")
    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }

    @objid ("ccdcf741-1ae3-42e3-a3d3-7a30e82d0b02")
    public int getSelection() {
        return this.selection;
    }

    @objid ("d204a35d-e677-4529-80da-e61a4cc98f5d")
    public int getMaximum() {
        return this.maximum;
    }

    @objid ("ec85253d-dd24-4115-9548-8ab42b19e47e")
    public int getMinimum() {
        return this.minimum;
    }

    @objid ("842327b4-e1c5-4932-ad54-4f956600d415")
    protected Color getTextColor(Color bg, Color fg) {
        // String to draw.
        if (this.textColor == null) {
        //            Color fgColor = getForeground();
        //            if (fgColor.getRGB().getHSB()[2] > 0.5) {
        //                this.textColor = new Color(this.getDisplay(), new RGB(fgColor.getRed() / 3, fgColor.getGreen() / 3, fgColor.getBlue() / 3));
        //            } else {
        //                this.textColor = new Color(this.getDisplay(), new RGB(fgColor.getRed() * 3, fgColor.getGreen() * 3, fgColor.getBlue() * 3));
        //            }
            this.textColor = new Color(this.getDisplay(), new RGB(
                    (fg.getRed() + bg.getRed()) / 2,
                    (fg.getGreen() + bg.getGreen()) / 2,
                    (fg.getBlue() + bg.getBlue()) / 2 ));
        
        }
        return this.textColor;
    }

}
