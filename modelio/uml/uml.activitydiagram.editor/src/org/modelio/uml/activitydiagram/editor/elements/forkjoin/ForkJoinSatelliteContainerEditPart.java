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
package org.modelio.uml.activitydiagram.editor.elements.forkjoin;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.editpolicies.SelectionEditPolicy;
import org.modelio.diagram.elements.common.portcontainer.AutoSizeEditPolicy;
import org.modelio.diagram.elements.common.portcontainer.GmPortContainer;
import org.modelio.diagram.elements.common.portcontainer.PortContainerEditPart;
import org.modelio.diagram.elements.core.node.GmNodeModel;

/**
 * Edit part for {@link GmForkJoin}.
 * 
 * @author fpo
 */
@objid ("2a7ff009-55b6-11e2-877f-002564c97630")
public class ForkJoinSatelliteContainerEditPart extends PortContainerEditPart {
    @objid ("2a7ff00d-55b6-11e2-877f-002564c97630")
    @Override
    public SelectionEditPolicy getPreferredDragRolePolicy(String requestType) {
        if (requestType.equals(RequestConstants.REQ_RESIZE)) {
            GmPortContainer node = (GmPortContainer) getModel();
            GmNodeModel fork = node.getMainNode();
            ForkOrientation orientation = (ForkOrientation) fork.getDisplayedStyle().getProperty(GmForkJoinStructuredStyleKeys.ORIENTATION);
            AutoSizeEditPolicy resizablePolicy = new AutoSizeEditPolicy();
            if (orientation == ForkOrientation.VERTICAL) {
                resizablePolicy.setResizeDirections(PositionConstants.NORTH_SOUTH);
            } else {
                resizablePolicy.setResizeDirections(PositionConstants.EAST_WEST);
            }
            return resizablePolicy;
        }
        return super.getPreferredDragRolePolicy(requestType);
    }

}
