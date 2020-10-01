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

package org.modelio.uml.statikdiagram.editor.elements.packaze;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.modelio.diagram.elements.common.portcontainer.PortContainerEditPart;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.node.GmNodeModel;

/**
 * Specialization of the PortContainerEditPart to handle CompositionLinks
 * 
 * @author fpoyer
 */
@objid ("3629aa7f-55b7-11e2-877f-002564c97630")
public class PackagePortContainerEditPart extends PortContainerEditPart {
    @objid ("3629aa83-55b7-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        // We don't want the dashed line appearing when selecting one of the children, we already have the composition link for that!
        removeEditPolicy("satellite selection");
    }

    @objid ("3629aa86-55b7-11e2-877f-002564c97630")
    @Override
    protected void addChildVisual(final EditPart childEditPart, final int index) {
        super.addChildVisual(childEditPart, index);
        final GmNodeModel childModel = (GmNodeModel) childEditPart.getModel();
        if (GmPackage.BODY_CONTENT_AS_SATELLITE.equals(childModel.getRoleInComposition())) {
            ((AbstractNodeEditPart) childEditPart).setDragTrackerProvider(new ContentAsSatelliteDragTrackerProvider(childEditPart));
        }
    }

}
