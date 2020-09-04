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

package org.modelio.diagram.elements.core.figures;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.geometry.Dimension;

/**
 * Layout manager to put on another to ensure the container has a minimum size
 * without using {@link IFigure#setMinimumSize(org.eclipse.draw2d.geometry.Dimension)}.
 * <p>
 * All behavior is delegated to the initial layout manager but the returned {@link #getPreferredSize(IFigure, int, int)}
 * and {@link #getMinimumSize(IFigure, int, int)} are guaranteed to be greater or equal to the minimum size set on this layout.
 * <p>
 * <h2>Quick usage:</h2>
 * <pre><code>
 * IFigure fig = ...
 * fig.setLayoutManager(new XYLayout()); // apply your layout manager
 * MinimumSizeLayout.apply(fig, minw, minh); // set minimum size
 * </code></pre>
 * 
 * @author cmarin
 * @since 3.4
 * @see #apply(IFigure, int, int)
 */
@objid ("1912e0de-f92e-4706-aae3-5d31ada14808")
public class MinimumSizeLayout extends ChainedLayout {
    @objid ("ebac45f4-c972-4152-898e-69126f7560f4")
    private final Dimension minSize = new Dimension();

    /**
     * @param chained the initial layout
     */
    @objid ("90d2fe46-f85f-40f0-b8d4-a7c957ea4ad1")
    public MinimumSizeLayout(LayoutManager chained) {
        super(chained);
    }

    /**
     * Set the minimum size.
     * 
     * @param minSize the minimum size. The passed dimension is copied.
     */
    @objid ("ea1ec0bf-e1e8-4765-887f-0ef827386ef5")
    public void setMinimumSize(Dimension minSize) {
        this.minSize.setSize(minSize);
    }

    /**
     * Set the minimum size.
     * 
     * @param minw the minimum width
     * @param minh the minimum height
     */
    @objid ("8b88bb15-a24b-4663-a80b-7495516d2523")
    public void setMinimumSize(int minw, int minh) {
        this.minSize.setSize(minw, minh);
    }

    /**
     * Apply a minimum size to the given figure.
     * <p>
     * The figure layout manager must already have been set.
     * 
     * @param fig the figure to layout
     * @param minw the minimum width
     * @param minh the minimum height
     */
    @objid ("c7f9419f-de25-4adf-b5c9-c016df6cdf51")
    public static void apply(IFigure fig, int minw, int minh) {
        LayoutManager l = fig.getLayoutManager();
        if (l==null) {
            throw new IllegalArgumentException("No LayoutManager set on "+fig);
        }
        
        MinimumSizeLayout minLayout = new MinimumSizeLayout(l);
        minLayout.setMinimumSize(minw, minh);
        
        fig.setLayoutManager(minLayout);
    }

    @objid ("f5f9266f-f7b8-4df5-9378-fdc65a78c1ca")
    @Override
    public Dimension getMinimumSize(IFigure container, int wHint, int hHint) {
        Dimension ret = super.getMinimumSize(container, wHint, hHint);
        
        if (ret.contains(this.minSize)) {
            return ret;
        } else {
            return ret.getUnioned(this.minSize);
        }
    }

    @objid ("75ce8981-8565-4847-bf13-1c06e7711293")
    @Override
    public Dimension getPreferredSize(IFigure container, int wHint, int hHint) {
        Dimension ret = super.getPreferredSize(container, wHint, hHint);
        
        if (ret.contains(this.minSize)) {
            return ret;
        } else {
            return ret.getUnioned(this.minSize);
        }
    }

    @objid ("afc303f3-36b5-4f46-9240-b9f4a3a58f87")
    @Override
    public String toString() {
        return getClass().getSimpleName()+" [minSize=" + this.minSize +", chained="+ getChained()+ "]";
    }

}
