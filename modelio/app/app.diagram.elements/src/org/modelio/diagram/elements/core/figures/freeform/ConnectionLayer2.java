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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ConnectionLayer;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * Subclass {@link org.eclipse.draw2d.ConnectionLayer} to use {@link FreeformHelper2}.
 */
@objid ("c841b396-ab0f-4259-8802-c9779b65d5f1")
public class ConnectionLayer2 extends ConnectionLayer implements IFreeformFigure2 {
    @objid ("c6eae4af-42e0-40bc-b97a-e78b3ae9a888")
    private final FreeformHelper2 helper = new FreeformHelper2(this);

    @objid ("d9bd2a45-b0a7-404f-a478-e0c41b68a868")
    @Override
    public Rectangle getFreeformExtent() {
        return this.helper.getFreeformExtent();
    }

    @objid ("5881d3ba-9712-416e-8689-06e7a567a3b3")
    @Override
    public void remove(IFigure child) {
        this.helper.unhookChild(child);
        super.remove(child);
    }

    @objid ("74888230-207b-48ac-b1d4-35b151821029")
    @Override
    public void setFreeformBounds(Rectangle bounds) {
        this.helper.setFreeformBounds(bounds);
    }

    @objid ("eacfb3e4-bd2f-4cee-8148-5778d928c53b")
    @Override
    public void add(IFigure child, Object constraint, int index) {
        super.add(child, constraint, index);
        this.helper.hookChild(child);
    }

    /**
     * Set a filter to use when computing {@link #getFreeformExtent()}.
     * <p>
     * Filtered out figures won't be used to compute the extent, that may result to the figures being cropped.
     * 
     * @param f an extent filter.
     */
    @objid ("62fc0720-7ddb-4e4c-971d-e01773514098")
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
    @objid ("1692dec0-0030-4449-aed8-2d0113cb3e33")
    @Override
    public final IExtentFilter getExtentFilter() {
        return this.helper.getExtentFilter();
    }

}
