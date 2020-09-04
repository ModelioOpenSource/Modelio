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

package org.modelio.diagram.elements.core.figures;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.text.TextFlow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.TextLayout;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.swt.widgets.Display;

/**
 * TextFlow that can be underlined or stroked through.
 * 
 * @author cmarin
 */
@objid ("7e7134af-1dec-11e2-8cad-001ec947c8cc")
public class TextFlow2 extends TextFlow {
    @objid ("7e7134b4-1dec-11e2-8cad-001ec947c8cc")
    private boolean underline = false;

    @objid ("7e7134b5-1dec-11e2-8cad-001ec947c8cc")
    private boolean strikeThrough = false;

    @objid ("7e7134b3-1dec-11e2-8cad-001ec947c8cc")
    private static TextLayout layout = null;

    /**
     * Provides a TextLayout that can be used by the Draw2d text package for Bidi. This TextLayout should not be
     * disposed by clients. The provided TextLayout's orientation will be LTR.
     * @return an SWT TextLayout that can be used for Bidi
     */
    @objid ("7e7134b6-1dec-11e2-8cad-001ec947c8cc")
    static TextLayout getTextLayout() {
        if (layout == null)
            layout = new TextLayout(Display.getDefault());
        layout.setOrientation(SWT.LEFT_TO_RIGHT);
        return layout;
    }

    @objid ("7e7134bb-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void paintText(final Graphics g, final String draw, final int x, final int y, final int bidiLevel) {
        //super.paintText(g, draw, x, y, bidiLevel);
        TextLayout tl = getTextLayout();
        try {
            if (isMirrored())
                tl.setOrientation(SWT.RIGHT_TO_LEFT);
        } catch (org.eclipse.swt.SWTException e) {
            // the diagram is probably already disposed...
            return;
        }
        tl.setFont(g.getFont());
        tl.setText(draw);
        
        TextStyle ts = new TextStyle(g.getFont(), g.getForegroundColor(), null);
        ts.underline = this.underline;
        ts.strikeout = this.strikeThrough;
        ts.underlineColor = ts.foreground;
        
        
        tl.setStyle(ts, 0, draw.length());
        g.drawTextLayout(tl, x, y);
    }

    /**
     * Set whether the label is underlined.
     * @param val true to underline the label
     */
    @objid ("7e7134ca-1dec-11e2-8cad-001ec947c8cc")
    public void setUnderline(final boolean val) {
        if (this.underline != val) {
            this.underline = val;
            repaint();
        }
    }

    /**
     * Set whether the label is underlined.
     * @param strikeThrough true to strike the label
     */
    @objid ("7e7134cf-1dec-11e2-8cad-001ec947c8cc")
    public void setStrikeThrough(final boolean strikeThrough) {
        if (this.strikeThrough != strikeThrough) {
            this.strikeThrough = strikeThrough;
            repaint();
        }
    }

}
