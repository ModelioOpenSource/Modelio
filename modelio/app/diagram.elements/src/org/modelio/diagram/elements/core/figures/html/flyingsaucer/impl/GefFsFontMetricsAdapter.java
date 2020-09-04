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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.swt.graphics.FontMetrics;
import org.xhtmlrenderer.render.FSFontMetrics;
import org.xhtmlrenderer.swt.SWTFSFont;

/**
 * Adapt SWT's font metrics to Flying Saucer's ones.
 * 
 * @author cmarin
 */
@objid ("ef2249fa-3d53-4911-828a-8f3589b03add")
class GefFsFontMetricsAdapter implements FSFontMetrics {
    @objid ("4cc9656b-50bc-405f-86ad-c4f47ae2de1d")
    private final FontMetrics _fm;

    /**
     * @param font the font to measure
     */
    @objid ("46e303d5-d4ed-47d5-97b7-2f1b302632df")
    public GefFsFontMetricsAdapter(SWTFSFont font) {
        this._fm = FigureUtilities.getFontMetrics(font.getSWTFont());
    }

    @objid ("f2b0ee63-f7bf-4e72-aa43-811f8eb59f20")
    @Override
    public float getAscent() {
        return this._fm.getAscent() + this._fm.getLeading();
    }

    @objid ("0da37161-28d4-41e3-8f82-85f43b0be1ad")
    @Override
    public float getDescent() {
        return this._fm.getDescent();
    }

// FIXME better metrics!
    @objid ("7996e716-6a84-4acd-8f8a-d1e826463962")
    @Override
    public float getStrikethroughOffset() {
        /*
         * Strike-through offset should be half an ex. We approximate an ex here
         * as half an em.
         */
        return -getAscent() / 4;
    }

    @objid ("c180d880-52aa-42f9-adf3-a2ba6a85a86c")
    @Override
    public float getStrikethroughThickness() {
        return Math.max(1, ((float) this._fm.getHeight()) / 20);
    }

    @objid ("e119ab73-8741-442a-ae86-36fafd1eca4c")
    @Override
    public float getUnderlineOffset() {
        return 1;
    }

    @objid ("bf229ec2-2cd4-492b-9f59-5b1c4bdb3939")
    @Override
    public float getUnderlineThickness() {
        return Math.max(1, ((float) this._fm.getHeight()) / 20);
    }

}
