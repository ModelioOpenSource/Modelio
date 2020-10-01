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

package org.modelio.uml.statikdiagram.editor.elements.ports;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.PositionConstants;
import org.modelio.diagram.elements.common.portcontainer.PortConstraint.Border;
import org.modelio.diagram.elements.common.portcontainer.PortConstraint;
import org.modelio.diagram.elements.common.portcontainer.PortContainerEditPart;
import org.modelio.diagram.elements.core.model.GmAbstractObject;

/**
 * Specialised EditPart that handles orientation based on the border that this port is stuck on.
 * 
 * @author fpoyer
 */
@objid ("364b3c28-55b7-11e2-877f-002564c97630")
public class PortEditPart extends PortContainerEditPart {
    @objid ("364b3c2c-55b7-11e2-877f-002564c97630")
    @Override
    protected void refreshVisuals() {
        super.refreshVisuals();
        refreshOrientation();
    }

    @objid ("364b3c2f-55b7-11e2-877f-002564c97630")
    private void refreshOrientation() {
        Object layoutData = ((GmAbstractObject) getModel()).getLayoutData();
        if (layoutData instanceof PortConstraint) {
            PortConstraint constraint = (PortConstraint) layoutData;
            Border border = constraint.getReferenceBorder();
            GmPortPrimaryNode mainNodeModel = (GmPortPrimaryNode) ((GmPort) getModel()).getMainNode();
            if (mainNodeModel != null) {
                updateMainNodeOrientation(mainNodeModel, border);
            }
        }
    }

    @objid ("364b3c31-55b7-11e2-877f-002564c97630")
    private void updateMainNodeOrientation(final GmPortPrimaryNode mainNodeModel, final Border border) {
        // update orientation of main node.
        switch (border) {
            case North:
                mainNodeModel.setPosition(PositionConstants.NORTH);
                break;
            case NorthEast:
                mainNodeModel.setPosition(PositionConstants.NORTH_EAST);
                break;
            case East:
                mainNodeModel.setPosition(PositionConstants.EAST);
                break;
            case SouthEast:
                mainNodeModel.setPosition(PositionConstants.SOUTH_EAST);
                break;
            case South:
                mainNodeModel.setPosition(PositionConstants.SOUTH);
                break;
            case SouthWest:
                mainNodeModel.setPosition(PositionConstants.SOUTH_WEST);
                break;
            case West:
                mainNodeModel.setPosition(PositionConstants.WEST);
                break;
            case NorthWest:
                mainNodeModel.setPosition(PositionConstants.NORTH_WEST);
                break;
            case Undefined:
                mainNodeModel.setPosition(PositionConstants.NONE);
                break;
        }
    }

}
