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

package org.modelio.diagram.elements.common.portcontainer;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FigureListener;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutListener;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.handles.HandleBounds;
import org.modelio.diagram.elements.core.figures.ChainedLayout;
import org.modelio.diagram.elements.core.figures.LayoutListenerSupport;

/**
 * Specialised figure for port containment.
 * 
 * @author fpoyer
 */
@objid ("7ef6b8a6-1dec-11e2-8cad-001ec947c8cc")
public class PortContainerFigure extends Figure implements HandleBounds {
    @objid ("cd985cd2-5e2c-4385-a0e0-9b54bbbec1e9")
    private IFigure mainNodeFigure;

    /**
     * Returns the Rectangle around which handles are to be placed. The Rectangle should be in the same coordinate
     * system as the figure itself.
     * 
     * @return The rectangle used for handles
     */
    @objid ("7ef6b8af-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Rectangle getHandleBounds() {
        if (this.mainNodeFigure != null) {
            if (this.mainNodeFigure instanceof HandleBounds) {
                HandleBounds mainNodeHandleBounds = (HandleBounds) this.mainNodeFigure;
                return mainNodeHandleBounds.getHandleBounds();
            } else {
                return this.mainNodeFigure.getBounds();
            }
        
        } else {
            return getBounds();
        }
    }

    /**
     * Sets the main node figure.
     * 
     * @param mainNodeFigure the main node figure.
     */
    @objid ("7ef91ab9-1dec-11e2-8cad-001ec947c8cc")
    public void setMainNodeFigure(IFigure mainNodeFigure) {
        this.mainNodeFigure = mainNodeFigure;
        
        if (this.mainNodeFigure != null) {
            // Fire own listeners when main node moves
            // to force own HandleBounds users to update:
            // getHandleBounds() depend on main node.
            this.mainNodeFigure.addFigureListener(new FigureListener() {
        
                @SuppressWarnings("synthetic-access")
                @Override
                public void figureMoved(IFigure source) {
                    fireFigureMoved();
                }
            });
        }
    }

    @objid ("7ef91abf-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean containsPoint(int x, int y) {
        for (Object childObj : getChildren()) {
            if (((IFigure) childObj).containsPoint(x, y)) {
                return true;
            }
        }
        return false;
    }

    @objid ("7ef91ac6-1dec-11e2-8cad-001ec947c8cc")
    protected IFigure getMainNodeFigure() {
        return this.mainNodeFigure;
    }

    @objid ("f43ec8da-a66c-418a-98ee-532969448c6a")
    @Override
    public void addLayoutListener(LayoutListener listener) {
        LayoutListenerSupport.addLayoutListener(this, listener);
    }

    @objid ("e3aa8aee-a04d-40a2-915e-f772be9e03e4")
    @Override
    public void removeLayoutListener(LayoutListener listener) {
        LayoutListenerSupport.removeListener(this, listener);
    }

    @objid ("74c91b35-b5f8-4783-97c2-718ad3614a66")
    @Override
    public void setLayoutManager(LayoutManager manager) {
        super.setLayoutManager(LayoutListenerSupport.newLayoutManager(this, manager));
    }

    /**
     * @return the {@link PortContainerLayout} layout manager.
     */
    @objid ("b818e324-ffbf-40a5-9110-6ccbea68ebb3")
    public PortContainerLayout getPortContainerLayout() {
        return (PortContainerLayout) ChainedLayout.getRootLayout(this);
    }

}
