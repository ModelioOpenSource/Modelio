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
import org.eclipse.gef.EditPart;
import org.modelio.diagram.elements.core.node.GmNodeDragTracker;

/**
 * Override of the default {@link GmNodeDragTracker} class to take into account the fact that a satellite should never
 * be reparented.
 * 
 * @author fpoyer
 */
@objid ("7f02a465-1dec-11e2-8cad-001ec947c8cc")
public class SatelliteDragEditPartsTracker extends GmNodeDragTracker {
    /**
     * Default constructor.
     * @param sourceEditPart the edit part this DragTracker applies to.
     */
    @objid ("7f02a467-1dec-11e2-8cad-001ec947c8cc")
    public  SatelliteDragEditPartsTracker(EditPart sourceEditPart) {
        super(sourceEditPart);
    }

    @objid ("7f02a46d-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected boolean isMove() {
        return true;
    }

}
