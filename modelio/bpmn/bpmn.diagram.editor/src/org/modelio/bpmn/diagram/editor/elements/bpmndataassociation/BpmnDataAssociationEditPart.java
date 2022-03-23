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
package org.modelio.bpmn.diagram.editor.elements.bpmndataassociation;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.geometry.PointList;
import org.modelio.diagram.elements.core.figures.decorations.DefaultPolylineDecoration;
import org.modelio.diagram.elements.core.link.LinkEditPart;

/**
 * Edit part for {@link GmBpmnDataAssociation}.
 */
@objid ("60a8129a-55b6-11e2-877f-002564c97630")
public class BpmnDataAssociationEditPart extends LinkEditPart {
    @objid ("60a8129e-55b6-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        PolylineConnection connection = (PolylineConnection) super.createFigure();
        if (!((GmBpmnDataAssociation) getModel()).isLinkToSequenceFlow())
            connection.setTargetDecoration(getArrowDecoration());
        
        // Make sure the arrow has appropriate style
        refreshFromStyle(connection, getModelStyle());
        return connection;
    }

    @objid ("60a812a3-55b6-11e2-877f-002564c97630")
    private DefaultPolylineDecoration getArrowDecoration() {
        DefaultPolylineDecoration decoration = new DefaultPolylineDecoration();
        PointList points = new PointList(new int[] { -1, -1, 0, 0, -1, 1 });
        decoration.setTemplate(points);
        decoration.setScale(5, 5);
        decoration.setOpaque(true);
        return decoration;
    }

    @objid ("60a812a9-55b6-11e2-877f-002564c97630")
    @Override
    protected void registerVisuals() {
        super.registerVisuals();
        if (!((GmBpmnDataAssociation) getModel()).isLinkToSequenceFlow()) {
            ((PolylineConnection) getFigure()).setTargetDecoration(getArrowDecoration());
        } else {
            ((PolylineConnection) getFigure()).setTargetDecoration(null);
        }
        
    }

}
