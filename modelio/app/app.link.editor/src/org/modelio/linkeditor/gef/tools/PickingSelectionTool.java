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

package org.modelio.linkeditor.gef.tools;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.swt.graphics.Cursor;
import org.modelio.platform.core.picking.IPickingSession;
import org.modelio.platform.ui.gef.SharedCursors2;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Tool that handles the picking for the LinkViewer.
 * @author fpoyer
 */
@objid ("1bb9b8d9-5e33-11e2-b81d-002564c97630")
public class PickingSelectionTool extends PanSelectionTool {
    @objid ("d48b0f5a-5efd-11e2-a8be-00137282c51b")
    private IPickingSession session;

    @objid ("1bb9b8db-5e33-11e2-b81d-002564c97630")
    @Override
    protected Cursor calculateCursor() {
        // compute the proper picking cursor
        MObject hoveredElement = getHoveredElement();
        if (hoveredElement == null)
            return SharedCursors2.CURSOR_PICKING;
        else
            return this.session.hover(hoveredElement) ? SharedCursors2.CURSOR_PICKING_YES : SharedCursors2.CURSOR_PICKING_NO;
    }

    @objid ("1bb9b8df-5e33-11e2-b81d-002564c97630")
    @Override
    protected boolean handleButtonDown(int which) {
        // TODO this is raw implementation, might be better to send a 'picking" request to editpart and get a command to execute...
        // the command could indicate which cursor to use authorised picking versus forbidden picking...
        
        MObject hoveredElement = getHoveredElement();
        if (hoveredElement != null && this.session.hover(hoveredElement)) {
            this.session.pick(hoveredElement);
        }
        return super.handleButtonDown(which);
    }

    @objid ("1bb9b8e5-5e33-11e2-b81d-002564c97630")
    @Override
    protected boolean handleMove() {
        setCursor(calculateCursor());
        return super.handleMove();
    }

    @objid ("1bb9b8ea-5e33-11e2-b81d-002564c97630")
    private MObject getHoveredElement() {
        GraphicalEditPart target = (GraphicalEditPart) this.getTargetEditPart();
        if (target != null) {
            return target.getAdapter(MObject.class);
        }
        return null;
    }

    @objid ("d48b0f5d-5efd-11e2-a8be-00137282c51b")
    public PickingSelectionTool(IPickingSession session) {
        super(null);
        this.session = session;
    }

}
