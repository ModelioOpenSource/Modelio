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
package org.modelio.diagram.editor.tools;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.swt.graphics.Cursor;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.platform.core.picking.IPickingSession;
import org.modelio.platform.ui.gef.SharedCursors2;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("66aed165-33f7-11e2-95fe-001ec947c8cc")
public class PickingSelectionTool extends PanSelectionTool {
    @objid ("66aed166-33f7-11e2-95fe-001ec947c8cc")
    private IPickingSession session;

    @objid ("66aed167-33f7-11e2-95fe-001ec947c8cc")
    public  PickingSelectionTool(IPickingSession session) {
        this.session = session;
    }

    @objid ("66aed16a-33f7-11e2-95fe-001ec947c8cc")
    @Override
    protected Cursor calculateCursor() {
        // compute the proper picking cursor
        MObject hoveredElement = getHoveredElement();
        if (hoveredElement == null) {
            return SharedCursors2.CURSOR_PICKING;
        } else {
            return this.session.hover(hoveredElement) ? SharedCursors2.CURSOR_PICKING_YES : SharedCursors2.CURSOR_PICKING_NO;
        }
        
    }

    @objid ("66aed16e-33f7-11e2-95fe-001ec947c8cc")
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

    @objid ("66b5f872-33f7-11e2-95fe-001ec947c8cc")
    @Override
    protected boolean handleMove() {
        setCursor(calculateCursor());
        return super.handleMove();
    }

    @objid ("66b5f877-33f7-11e2-95fe-001ec947c8cc")
    private MObject getHoveredElement() {
        GraphicalEditPart target = (GraphicalEditPart) this.getTargetEditPart();
        if (target != null) {
            Object o = target.getModel();
            if (o instanceof GmModel) {
                // System.out.println(" " + ((GmModel) o).getElement());
                return ((GmModel) o).getRelatedElement();
            }
        }
        return null;
    }

}
