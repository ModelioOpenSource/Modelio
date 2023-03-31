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
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformFigure;
import org.eclipse.draw2d.FreeformListener;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayeredPane;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;

/**
 * A LayeredPane that contains {@link org.eclipse.draw2d.FreeformLayer FreeformLayers}.
 * <p>
 * This is a copy of {@link org.eclipse.draw2d.FreeformLayeredPane} to use the fixed {@link FreeformHelper2}.
 * 
 * @since 3.7
 */
@objid ("bf752923-e97b-4757-9f9e-6b984ff1c404")
public class FreeformLayeredPane2 extends LayeredPane implements IFreeformFigure2 {
    @objid ("2ea3e916-ce3b-4f5a-afef-4752cf1a86f1")
    private FreeformHelper2 helper = new FreeformHelper2(this);

    /**
     * Constructs a new FreeformLayeredPane2.
     */
    @objid ("acd26497-419f-4a55-9e07-b3ad6b5af764")
    public  FreeformLayeredPane2() {
        setLayoutManager(null);
    }

    /**
     * @see IFigure#add(IFigure, Object, int)
     */
    @objid ("0bbc803a-4e21-42ca-970b-0244ceab0dde")
    @Override
    public void add(IFigure child, Object constraint, int index) {
        super.add(child, constraint, index);
        this.helper.hookChild(child);
        
    }

    /**
     * @see FreeformFigure#addFreeformListener(FreeformListener)
     */
    @objid ("1f59fee2-8925-4f51-acb0-633e2c651bb6")
    @Override
    public void addFreeformListener(FreeformListener listener) {
        addListener(FreeformListener.class, listener);
    }

    /**
     * @see FreeformFigure#fireExtentChanged()
     */
    @objid ("ca270f9d-3005-46aa-9d13-e5749b10955c")
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
    @objid ("df8a0559-707a-4084-a773-0748e97131f6")
    @Override
    protected void fireMoved() {
        
    }

    /**
     * Redefined to enable anti aliasing in whole diagram.
     */
    @objid ("d12f2e8e-07b2-45f9-b078-30d9de380d7b")
    @Override
    public void paint(Graphics graphics) {
        setupGc(graphics);
        
        super.paint(graphics);
        
    }

    /**
     * Use advaced graphics and anti aliasing in the whole diagram.
     * @param graphics the GEF GC
     */
    @objid ("42d73cf6-35f4-4263-893e-96819aa903a8")
    private void setupGc(Graphics graphics) {
        /*
        Dev notes:
        ----------
        This is the only place where anti aliasing may be activated for the whole diagram.
        Places where it didn't work:
        - ScalableFreeformLayeredPane2.paintclientArea(...) : GC settings are reset on next figure
        - redefine org.modelio.diagram.editor.widgets.draw2d.DeferredUpdateManagerWithWatchDog.paint(GC) : SWT GC settings are erased on first figure drawing,
          due to ill initialization of SWTGraphics from GC.
        - add a SWT paint listener in org.modelio.diagram.editor.AbstractDiagramEditor.createGraphicalViewer(Composite) : the listener is called AFTER painting
        */
        // 24/10/2022 : Disabled because it breaks MarqueeDragTracker : XOR mode does not work anymore
        if (false) {
            graphics.setAdvanced(true);
            if (graphics.getAdvanced()) {
                graphics.setInterpolation(SWT.HIGH); // seems to be interpolation for images painting
                graphics.setAntialias(SWT.ON);
                graphics.setTextAntialias(SWT.ON);
            }
        }
        
    }

    /**
     * Returns the FreeformHelper.
     * @return the FreeformHelper
     */
    @objid ("2e57e082-5840-4878-9898-b7d8bafdcb59")
    protected FreeformHelper2 getFreeformHelper() {
        return this.helper;
    }

    /**
     * @see FreeformFigure#getFreeformExtent()
     */
    @objid ("f16f8888-be01-4faf-a4ad-6b287e70b6a7")
    @Override
    public Rectangle getFreeformExtent() {
        return this.helper.getFreeformExtent();
    }

    /**
     * @see Figure#primTranslate(int, int)
     */
    @objid ("b93e276f-c7f3-4cd2-b047-3e52d1b46550")
    @Override
    protected void primTranslate(int dx, int dy) {
        this.bounds.x += dx;
        this.bounds.y += dy;
        
    }

    /**
     * @see IFigure#remove(IFigure)
     */
    @objid ("c04a44a2-eae8-4fa0-8aa4-2c288a46589f")
    @Override
    public void remove(IFigure child) {
        this.helper.unhookChild(child);
        super.remove(child);
        
    }

    /**
     * @see FreeformFigure#removeFreeformListener(FreeformListener)
     */
    @objid ("17ab4f67-f3be-4e55-bd06-291b6521a8dc")
    @Override
    public void removeFreeformListener(FreeformListener listener) {
        removeListener(FreeformListener.class, listener);
    }

    /**
     * @see FreeformFigure#setFreeformBounds(Rectangle)
     */
    @objid ("b4ce9921-f675-4235-a300-36b4f366ff4c")
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
    @objid ("0acc2c67-9206-4627-a459-eacc0f219de1")
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
    @objid ("7b234742-0ecf-4b04-abed-f238f9276631")
    @Override
    public final IExtentFilter getExtentFilter() {
        return this.helper.getExtentFilter();
    }

}
