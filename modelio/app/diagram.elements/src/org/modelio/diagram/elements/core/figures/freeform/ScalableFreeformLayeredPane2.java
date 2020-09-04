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

package org.modelio.diagram.elements.core.figures.freeform;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ScalableFreeformLayeredPane;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * Same as {@link ScalableFreeformLayeredPane} but don't use a ScaledGraphics
 * to paint the area, this class sucks at zooming texts.
 * 
 * @author cmarin
 */
@objid ("e693d18f-7c7a-470a-b770-d77888ee9e1e")
public class ScalableFreeformLayeredPane2 extends ScalableFreeformLayeredPane implements IFreeformFigure2 {
    @objid ("31b8c246-445e-491b-8819-5fafba391239")
    private final FreeformHelper2 helper = new FreeformHelper2(this);

    @objid ("f17e9bdc-a044-46c4-9bdb-702ee870fbe8")
    @Override
    protected void paintClientArea(Graphics graphics) {
        if (getChildren().isEmpty()) {
            return;
        } else if (getScale() == 1.0) {
            super.paintClientArea(graphics);
        } else {
            boolean optimizeClip = getBorder() == null || getBorder().isOpaque();
            if (!optimizeClip) {
                graphics.clipRect(getBounds().getShrinked(getInsets()));
            }
            
            graphics.scale(getScale());
        
            graphics.pushState();
            paintChildren(graphics);
            graphics.popState();
            graphics.restoreState();
        }
    }

    @objid ("f9303804-3537-4421-bd80-79786e1f70e3")
    @Override
    public void add(IFigure child, Object constraint, int index) {
        super.add(child, constraint, index);
        this.helper.hookChild(child);
    }

    @objid ("0a0d548a-5fc7-4fd1-ba29-b625c54a1b03")
    @Override
    public void setScale(double newZoom) {
        if (getScale() == newZoom) {
            return;
        }
        super.setScale(newZoom);
        this.helper.invalidate();
    }

    @objid ("d976854c-4016-423a-a710-9a5ffb2a2fe5")
    @Override
    public Rectangle getFreeformExtent() {
        return this.helper.getFreeformExtent();
    }

    @objid ("a3ae2846-44a4-47be-9187-3c8ce2a1e38d")
    @Override
    public void remove(IFigure child) {
        this.helper.unhookChild(child);
        super.remove(child);
    }

    @objid ("e9098abf-3382-46d8-ae15-f4fb0adfe552")
    @Override
    public void setFreeformBounds(Rectangle bounds) {
        this.helper.setFreeformBounds(bounds);
    }

    /**
     * Set a filter to use when computing {@link #getFreeformExtent()}.
     * <p>
     * Filtered out figures won't be used to compute the extent, that may result to the figures being cropped.
     * @param f an extent filter.
     */
    @objid ("af2d5f82-a64f-42e2-a5a4-4cbaccab3951")
    @Override
    public final void setExtentFilter(IExtentFilter f) {
        this.helper.setExtentFilter(f);
    }

    /**
     * Get the filter used when computing {@link #getFreeformExtent()}.
     * <p>
     * Filtered out figures won't be used to compute the extent, that may result to the figures being cropped.
     * @return an extent filter.
     */
    @objid ("259cec09-5f5c-4f1d-b5e9-5f0778adf22f")
    @Override
    public final IExtentFilter getExtentFilter() {
        return this.helper.getExtentFilter();
    }

}
