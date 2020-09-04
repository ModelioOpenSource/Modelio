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

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.FigureListener;
import org.eclipse.draw2d.FreeformFigure;
import org.eclipse.draw2d.FreeformListener;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * Copy of org.eclipse.draw2d.FreeformHelper with {@link #getFreeformExtent()} fixed to ignore empty rectangles.
 * <p>
 * This helper also may have a filter to ignore some figures, set with {@link #setExtentFilter(IExtentFilter)}.
 * @author cma
 * @since 3.7
 */
@objid ("d7b47927-2727-4dbf-8d11-62670455f447")
public class FreeformHelper2 implements FreeformListener {
    @objid ("f4828d9a-2240-43c3-8f4f-1af9d808aa29")
    private final FreeformFigure host;

    @objid ("96a83e4c-e2a3-4aeb-84ef-a9da10450ebd")
    private Rectangle freeformExtent;

    @objid ("ac42e5ed-a435-44da-a40b-97b9ecd0ec37")
    private final FigureListener figureListener = source -> invalidate();

    @objid ("36798b33-0df9-4f1d-8c8a-ac7e3b3e0628")
    private IExtentFilter extentFilter = IExtentFilter.NONE;

    @objid ("5a3782b9-4f94-4234-8fbc-6bce48023672")
    public FreeformHelper2(FreeformFigure host) {
        this.host = host;
    }

    @objid ("5915a921-8626-4e0e-be38-115e628b28db")
    public Rectangle getFreeformExtent() {
        if (this.freeformExtent != null) {
            return this.freeformExtent;
        }
        
        Rectangle r;
        List<IFigure> children = this.host.getChildren();
        for (IFigure child : children) {
            if (this.extentFilter.test(child)) {
                if (child instanceof FreeformFigure) {
                    r = ((FreeformFigure) child).getFreeformExtent();
                } else {
                    r = child.getBounds();
                }
                if (this.freeformExtent == null || this.freeformExtent.isEmpty()) {
                    this.freeformExtent = r.getCopy();
                } else {
                    this.freeformExtent.union(r);
                }
            }
        }
        
        Insets insets = this.host.getInsets();
        if (this.freeformExtent == null) {
            this.freeformExtent = new Rectangle(0, 0, insets.getWidth(),
                    insets.getHeight());
        } else {
            this.host.translateToParent(this.freeformExtent);
            this.freeformExtent.expand(insets);
        }
        return this.freeformExtent;
    }

    @objid ("ed8d85c1-13ad-4c9b-9c2a-ecc1f55fce22")
    public void hookChild(IFigure child) {
        invalidate();
        if (child instanceof FreeformFigure) {
            ((FreeformFigure) child).addFreeformListener(this);
        } else {
            child.addFigureListener(this.figureListener);
        }
    }

    @objid ("c49d8ed6-80b3-4acb-a98d-2c1869841f57")
    public void invalidate() {
        this.freeformExtent = null;
        this.host.fireExtentChanged();
        if (this.host.getParent() != null) {
            this.host.getParent().revalidate();
        } else {
            this.host.revalidate();
        }
    }

    @objid ("fb395bdf-548e-489c-9ec6-38832bedf977")
    @Override
    public void notifyFreeformExtentChanged() {
        // A childs freeform extent has changed, therefore this extent must be
        // recalculated
        invalidate();
    }

    @objid ("edb9b13b-b44c-4fdf-9e46-e4919d187a90")
    public void setFreeformBounds(Rectangle bounds) {
        this.host.setBounds(bounds);
        
        Rectangle childrenBounds = bounds.getCopy();
        this.host.translateFromParent(childrenBounds);
        
        List<IFigure> children = this.host.getChildren();
        for (IFigure child : children) {
            if (child instanceof FreeformFigure) {
                ((FreeformFigure) child).setFreeformBounds(childrenBounds);
            }
        }
    }

    @objid ("702d5999-05d6-490f-9519-7191bc448f14")
    public void unhookChild(IFigure child) {
        invalidate();
        if (child instanceof FreeformFigure) {
            ((FreeformFigure) child).removeFreeformListener(this);
        } else {
            child.removeFigureListener(this.figureListener);
        }
    }

    @objid ("2c761fa4-486a-46d4-bf38-ec290b500878")
    public IExtentFilter getExtentFilter() {
        return this.extentFilter;
    }

    @objid ("f1f184ad-c21a-4934-b603-bb1d842028a5")
    public void setExtentFilter(IExtentFilter extentFilter) {
        if (extentFilter == null) {
            extentFilter = IExtentFilter.NONE;
        }
        if (extentFilter == this.extentFilter) {
            return;
        }
        
        this.extentFilter = extentFilter;
        invalidate();
    }

}
