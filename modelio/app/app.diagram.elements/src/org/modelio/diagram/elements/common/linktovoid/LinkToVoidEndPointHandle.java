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

package org.modelio.diagram.elements.common.linktovoid;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ConnectionLocator;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.handles.ConnectionEndpointHandle;
import org.eclipse.gef.tools.ConnectionEndpointTracker;
import org.modelio.diagram.elements.core.figures.LinkFigure;

/**
 * A handle used at the start or end of the {@link LinkToVoidEditPart}.
 */
@objid ("7ed092e2-1dec-11e2-8cad-001ec947c8cc")
public class LinkToVoidEndPointHandle extends ConnectionEndpointHandle {
    @objid ("27016e0a-200d-4796-8055-81a89afd441e")
    private static final Rectangle DRAW_RECT = new Rectangle();

    /**
     * Creates a new LinkToVoidEndPointHandle, sets its owner to <code>owner</code> , and sets its locator to a
     * {@link ConnectionLocator}.
     * 
     * @param owner the ConnectionEditPart owner
     * @param endPoint one of {@link ConnectionLocator#SOURCE} or {@link ConnectionLocator#TARGET}.
     */
    @objid ("7ed092ea-1dec-11e2-8cad-001ec947c8cc")
    public LinkToVoidEndPointHandle(final ConnectionEditPart owner, final int endPoint) {
        super(owner, endPoint);
        
        initPreferredSize();
    }

    /**
     * Let the handle contain the connection end point decoration if any.
     */
    @objid ("7ed092f3-1dec-11e2-8cad-001ec947c8cc")
    private void initPreferredSize() {
        // Set the handle size
        
        final LinkFigure conn = (LinkFigure) getConnection();
        final RotatableDecoration decoration;
        if (getEndPoint() == ConnectionLocator.SOURCE) {
            decoration = conn.getSourceDecoration();
        } else {
            decoration = conn.getTargetDecoration();
        }
        
        if (decoration != null) {
            final Dimension decSize = decoration.getSize();
        
            decSize.expand(4, 4);
            conn.translateToAbsolute(decSize);
        
            decSize.union(getPreferredSize());
            setPreferredSize(decSize);
        }
    }

    @objid ("7ed092f6-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected DragTracker createDragTracker() {
        if (isFixed())
            return null;
        
        ConnectionEndpointTracker tracker;
        tracker = new ConnectionEndpointTracker((ConnectionEditPart) getOwner());
        
        if (getEndPoint() == ConnectionLocator.SOURCE) {
            tracker.setCommandName(LinkToVoidConstants.REQ_LINKTOVOID_RECONNECT_SOURCE);
        } else {
            tracker.setCommandName(LinkToVoidConstants.REQ_LINKTOVOID_RECONNECT_TARGET);
        }
        
        tracker.setDefaultCursor(getCursor());
        return tracker;
    }

    /**
     * Returns the alpha for the outside of the handle.
     * 
     * @return the alpha for the border
     */
    @objid ("7ed092fd-1dec-11e2-8cad-001ec947c8cc")
    protected int getBorderAlpha() {
        return (isPrimary()) ? 100 : 255;
    }

    /**
     * Returns the alpha for the inside of the handle.
     * 
     * @return the alpha for the inside
     */
    @objid ("7ed09302-1dec-11e2-8cad-001ec947c8cc")
    protected int getFillAlpha() {
        return (isPrimary()) ? 255 : 100;
    }

    @objid ("7ed09307-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void paintFigure(final Graphics g) {
        DRAW_RECT.setBounds(getBounds());
        
        //DRAW_RECT.shrink(1, 1);
        DRAW_RECT.resize(-1, -1);
        g.setForegroundColor(getBorderColor());
        g.setAlpha(getBorderAlpha());
        g.drawRectangle(DRAW_RECT);
        
        DRAW_RECT.shrink(1, 1);
        g.setForegroundColor(getFillColor());
        g.setAlpha(getFillAlpha());
        g.drawRectangle(DRAW_RECT);
        
        DRAW_RECT.shrink(1, 1);
        g.setForegroundColor(getFillColor());
        g.setAlpha(getFillAlpha());
        g.drawRectangle(DRAW_RECT);
    }

}
