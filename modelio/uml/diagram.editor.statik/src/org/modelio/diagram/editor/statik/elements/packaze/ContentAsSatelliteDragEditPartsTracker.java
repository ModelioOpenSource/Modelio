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

package org.modelio.diagram.editor.statik.elements.packaze;

import java.util.HashSet;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.modelio.diagram.editor.statik.elements.namespacinglink.GmCompositionLink;
import org.modelio.diagram.elements.common.portcontainer.GmPortContainer;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.model.IGmLinkable;
import org.modelio.diagram.elements.core.node.GmNodeDragTracker;
import org.modelio.diagram.elements.core.node.GmNodeModel;

@objid ("3615d43a-55b7-11e2-877f-002564c97630")
public class ContentAsSatelliteDragEditPartsTracker extends GmNodeDragTracker {
    /**
     * Default constructor.
     * 
     * @param sourceEditPart the edit part this DragTracker applies to.
     */
    @objid ("3615d43d-55b7-11e2-877f-002564c97630")
    public ContentAsSatelliteDragEditPartsTracker(final EditPart sourceEditPart) {
        super(sourceEditPart);
    }

    @objid ("3615d442-55b7-11e2-877f-002564c97630")
    @Override
    protected boolean isMove() {
        EditPart part = getSourceEditPart();
        Set<EditPart> simpleModeAncestors = new HashSet<>();
        GmNodeModel mainNodeModel = (GmNodeModel) part.getModel();
        computeSimpleModeAncestors(simpleModeAncestors, mainNodeModel);
        while (part != getTargetEditPart() && part != null) {
            if (part.getParent() == getTargetEditPart() &&
                (part.getSelected() != EditPart.SELECTED_NONE || simpleModeAncestors.contains(part))) {
                return true;
            }
            part = part.getParent();
        }
        return false;
    }

    @objid ("3615d447-55b7-11e2-877f-002564c97630")
    private void computeSimpleModeAncestors(Set<EditPart> simpleModeAncestors, IGmLinkable model) {
        IGmLinkable mainNodeModel = model;
        if (mainNodeModel instanceof GmPortContainer) {
            mainNodeModel = ((GmPortContainer) mainNodeModel).getMainNode();
        }
        for (IGmLink link : mainNodeModel.getEndingLinks()) {
            if (link instanceof GmCompositionLink) {
                IGmLinkable from = link.getFrom();
                GraphicalEditPart fromEditPart = (GraphicalEditPart) getCurrentViewer().getEditPartRegistry()
                                                                                       .get(from);
                if (fromEditPart != null) {
                    simpleModeAncestors.add(fromEditPart);
                    simpleModeAncestors.add(fromEditPart.getParent());
                    computeSimpleModeAncestors(simpleModeAncestors, from);
                }
            }
        }
    }

}
