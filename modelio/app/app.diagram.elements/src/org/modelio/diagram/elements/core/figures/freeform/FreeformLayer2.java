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

package org.modelio.diagram.elements.core.figures.freeform;

import java.util.Iterator;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.FreeformFigure;
import org.eclipse.draw2d.FreeformListener;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Layer;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * A Layer that can extend in all 4 directions.
 */
@objid ("3b468469-1ec2-4e99-ada4-45a4247fad96")
public class FreeformLayer2 extends Layer implements IFreeformFigure2 {
    @objid ("ad431853-886e-4123-bd49-6f84d8807142")
    private FreeformHelper2 helper = new FreeformHelper2(this);

    /**
     * @see IFigure#add(IFigure, Object, int)
     */
    @objid ("5c4a8e38-f858-4268-9a5e-46fc4d58750b")
    @Override
    public void add(IFigure child, Object constraint, int index) {
        super.add(child, constraint, index);
        this.helper.hookChild(child);
    }

    /**
     * @see FreeformFigure#addFreeformListener(FreeformListener)
     */
    @objid ("f8f6ef2f-8978-4aea-9153-8929afc8d9f5")
    @Override
    public void addFreeformListener(FreeformListener listener) {
        addListener(FreeformListener.class, listener);
    }

    /**
     * @see FreeformFigure#fireExtentChanged()
     */
    @objid ("eac2e3aa-439e-4c7f-acad-85865ebafc23")
    @Override
    public void fireExtentChanged() {
        Iterator<FreeformListener> iter = getListeners(FreeformListener.class);
        while (iter.hasNext()) {
            iter.next().notifyFreeformExtentChanged();
        }
    }

    /**
     * Overrides to do nothing.
     * @see Figure#fireMoved()
     */
    @objid ("8262c215-bb20-4322-8158-5fd5faf47dfe")
    @Override
    protected void fireMoved() {
    }

    /**
     * @see FreeformFigure#getFreeformExtent()
     */
    @objid ("9dab2fc0-763e-40ae-88a8-26ff70217190")
    @Override
    public Rectangle getFreeformExtent() {
        return this.helper.getFreeformExtent();
    }

    /**
     * @see Figure#primTranslate(int, int)
     */
    @objid ("12e97974-a2e4-4e17-a1fa-386b43850394")
    @Override
    public void primTranslate(int dx, int dy) {
        this.bounds.x += dx;
        this.bounds.y += dy;
    }

    /**
     * @see IFigure#remove(IFigure)
     */
    @objid ("9f0a2169-cf90-4dff-9a34-e9ebd2e89ffa")
    @Override
    public void remove(IFigure child) {
        this.helper.unhookChild(child);
        super.remove(child);
    }

    /**
     * @see FreeformFigure#removeFreeformListener(FreeformListener)
     */
    @objid ("26a2cf4b-7217-4854-ac3d-c4ac7b3e7c3c")
    @Override
    public void removeFreeformListener(FreeformListener listener) {
        removeListener(FreeformListener.class, listener);
    }

    /**
     * @see FreeformFigure#setFreeformBounds(Rectangle)
     */
    @objid ("181ac2bb-62d4-48dc-944d-2517daf6eb3d")
    @Override
    public void setFreeformBounds(Rectangle bounds) {
        this.helper.setFreeformBounds(bounds);
    }

    /**
     * Set a filter to use when computing {@link #getFreeformExtent()}.
     * <p>
     * Filtered out figures won't be used to compute the extent, that may result to the figures being cropped.
     * 
     * @param f an extent filter.
     */
    @objid ("736cc8cb-2880-4357-9d99-59f2a4282e70")
    @Override
    public final void setExtentFilter(IExtentFilter f) {
        this.helper.setExtentFilter(f);
    }

    /**
     * Get the filter used when computing {@link #getFreeformExtent()}.
     * <p>
     * Filtered out figures won't be used to compute the extent, that may result to the figures being cropped.
     * 
     * @return an extent filter.
     */
    @objid ("33bd19e6-aff7-4f03-b10a-cb078913f59e")
    @Override
    public final IExtentFilter getExtentFilter() {
        return this.helper.getExtentFilter();
    }

}
