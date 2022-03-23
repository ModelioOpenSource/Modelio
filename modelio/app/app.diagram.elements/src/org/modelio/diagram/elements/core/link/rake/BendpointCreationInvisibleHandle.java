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
package org.modelio.diagram.elements.core.link.rake;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Graphics;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.SharedCursors;
import org.eclipse.gef.handles.BendpointCreationHandle;

/**
 * An invisible bendpoint creation handle used to move a line segment.
 * 
 * @author sshaw
 */
@objid ("8055320e-1dec-11e2-8cad-001ec947c8cc")
public class BendpointCreationInvisibleHandle extends BendpointCreationHandle {
    /**
     * Creates a new BendpointCreationHandle, sets its owner to <code>owner</code> and its index to <code>index</code>,
     * and sets its locator to a new {@link org.eclipse.draw2d.MidpointLocator}.
     * @param owner a connection edit part
     * @param index the index of the segment to make moveable.
     */
    @objid ("80553212-1dec-11e2-8cad-001ec947c8cc")
    public  BendpointCreationInvisibleHandle(ConnectionEditPart owner, int index) {
        super(owner, index);
        setCursor(SharedCursors.CURSOR_TREE_ADD);
        // TODO: look at this
        //setCursor(Cursors.CURSOR_SEG_ADD);
        
    }

    /**
     * Draws the handle with fill color and outline color dependent on the primary selection status of the owner edit
     * part.
     * @param g The graphics used to paint the figure.
     */
    @objid ("80579467-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void paintFigure(Graphics g) {
        // do nothing - this handle is invisible
    }

}
