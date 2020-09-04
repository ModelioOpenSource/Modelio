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
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.geometry.Dimension;

/**
 * {@link LayoutManager} that delegates to another layout manager.
 * <p>
 * This class is made to be extended. Then each method may be redefined to
 * make additional behavior.
 * 
 * @author cmarin
 * @since 3.4
 */
@objid ("a41ebac0-c55e-449c-b024-88d8f8390e65")
public abstract class ChainedLayout implements LayoutManager {
    @objid ("c5fb447f-3c76-42a4-b6e5-a310bcf68f8e")
    private LayoutManager chained;

    /**
     * @param chained the layout to delegate to.
     */
    @objid ("86245b0b-5d80-42db-95ca-2c08a42bde22")
    public ChainedLayout(LayoutManager chained) {
        this.chained = chained;
    }

    @objid ("081ec4e1-f49c-4337-9363-c075b027dd66")
    @Override
    public Object getConstraint(IFigure child) {
        if (this.chained != null) {
            return this.chained.getConstraint(child);
        } else {
            return null;
        }
    }

    @objid ("f13f6bc1-fc61-4e27-86a9-0b78510e928a")
    @Override
    public Dimension getMinimumSize(IFigure container, int wHint, int hHint) {
        if (this.chained != null) {
            return this.chained.getMinimumSize(container, wHint, hHint);
        } else {
            return null;
        }
    }

    @objid ("39161151-a349-4c8b-abc1-157350e95578")
    @Override
    public Dimension getPreferredSize(IFigure container, int wHint, int hHint) {
        if (this.chained != null) {
            return this.chained.getPreferredSize(container, wHint, hHint);
        } else {
            return null;
        }
    }

    @objid ("1e4bed0b-5941-4d49-8fee-225ae01919a0")
    @Override
    public void invalidate() {
        if (this.chained != null) {
            this.chained.invalidate();
        }
    }

    @objid ("3663112a-3cc0-4cd1-9153-d1230770c3fb")
    @Override
    public void layout(IFigure container) {
        if (this.chained != null) {
            this.chained.layout(container);
        }
    }

    @objid ("dad8baf6-7a3a-4705-b899-12039bce8270")
    @Override
    public void remove(IFigure child) {
        if (this.chained != null) {
            this.chained.remove(child);
        }
    }

    @objid ("eef2a8d7-77b0-4c88-90b0-b5158ab098ff")
    @Override
    public void setConstraint(IFigure child, Object constraint) {
        if (this.chained != null) {
            this.chained.setConstraint(child, constraint);
        }
    }

    /**
     * @return the layout to which operations are delegated.
     */
    @objid ("2d570851-4a34-46a8-8ad8-a66fa34008f5")
    public LayoutManager getChained() {
        return this.chained;
    }

    @objid ("e6dbf4d6-ad13-4020-a8e1-3bbac7ec858e")
    @Override
    public String toString() {
        return getClass().getSimpleName()+" [chained=" + getChained() + "]";
    }

    /**
     * Get the root layout manager by iterating the layout managers chain.
     * <p>
     * Works even if the layout manager is not a <code>ChainedLayout</code>
     * or no layout manager is set (returns <i>null</i>).
     * @param fig a figure
     * @return the root layout manager.
     */
    @objid ("d3954135-a173-4406-9fff-8a8ec9f19ba1")
    public static LayoutManager getRootLayout(IFigure fig) {
        return getRootLayout(fig.getLayoutManager());
    }

    /**
     * Changes the chained layout.
     * @param chained the new layout.
     */
    @objid ("0d857713-4d64-4c34-9788-cdc2e6c00de9")
    public void setChained(LayoutManager chained) {
        if (this.chained instanceof ChainedLayout) {
            ChainedLayout c = (ChainedLayout) this.chained;
            c.setChained(chained);
        } else {
            this.chained = chained;
        }
    }

    /**
     * Get the root layout manager by iterating the layout managers chain.
     * <p>
     * Works even if the layout manager is not a <code>ChainedLayout</code>
     * or layout manager is null (returns <i>null</i>).
     * @param alayout a layout manager.
     * @return the root layout manager.
     */
    @objid ("e463fa2b-75f2-4f22-af80-327a3886a6c1")
    public static LayoutManager getRootLayout(LayoutManager alayout) {
        LayoutManager layout = alayout;
        
        while (layout instanceof ChainedLayout) {
            layout = ((ChainedLayout)layout).getChained();
        }
        return layout;
    }

    @objid ("1e88ff62-de48-47b9-aae1-aa344911e2fc")
    public void setRootLayout(LayoutManager newRoot) {
        ChainedLayout last = this;
        for (LayoutManager l = last; l instanceof ChainedLayout; l = last.getChained()) {
            last = ((ChainedLayout) l);
        }
            
        last.setChained(newRoot);
    }

    /**
     * Find a layout of the given class in the layout chain of the figure
     * @param f a  figure
     * @param cls the layout class to find
     * @return the found layouter or null.
     */
    @objid ("c757f3a6-a287-494c-896d-ee0cb472c2ea")
    @SuppressWarnings ("unchecked")
    public static <T extends LayoutManager> T findLayout(IFigure f, Class<T> cls) {
        for (LayoutManager l = f.getLayoutManager(); l instanceof ChainedLayout; l = ((ChainedLayout) l).getChained()) {
            if (cls.isInstance(l)) {
                return (T) l;
            }
        }
        return null;
    }

    /**
     * Remove the given layout from the chain.
     * @param f a figure
     * @param cls the layout class to remove.
     */
    @objid ("5c9cf139-7f41-4210-b668-1bbd7afbe480")
    @SuppressWarnings ("unchecked")
    public static <T extends ChainedLayout> T removeLayout(IFigure f, Class<T> cls) {
        LayoutManager l = f.getLayoutManager();
        ChainedLayout prev = null;
        
        while ( l instanceof ChainedLayout) {
            ChainedLayout c = (ChainedLayout) l;
            if (cls.isInstance(c)) {
                if (prev == null) {
                    f.setLayoutManager(c.getChained());
                } else {
                    prev.setChained(c.getChained());
                }
                return (T) c;
            }
            prev = c;
            l = c.getChained();
        }
        return null;
    }

}
