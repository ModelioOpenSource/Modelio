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
package org.modelio.uml.activitydiagram.editor.elements.objectflow;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.PolylineDecoration;
import org.modelio.diagram.elements.core.figures.decorations.DefaultPolylineDecoration;
import org.modelio.diagram.elements.core.link.LinkEditPart;
import org.modelio.uml.statikdiagram.editor.elements.informationflowgroup.DefaultCreateInfoFlowOnLinkEditPolicy;

/**
 * Edit part for {@link GmObjectFlow}.
 */
@objid ("2acdc1ca-55b6-11e2-877f-002564c97630")
public class ObjectFlowEditPart extends LinkEditPart {
    @objid ("2acdc1ce-55b6-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        PolylineConnection connection = (PolylineConnection) super.createFigure();
        
        // Navigability arrow toward target
        DefaultPolylineDecoration arrow = new DefaultPolylineDecoration();
        arrow.setTemplate(PolylineDecoration.TRIANGLE_TIP);
        arrow.setScale(5, 5);
        arrow.setOpaque(false);
        connection.setTargetDecoration(arrow);
        
        // Make sure the arrow has appropriate style
        refreshFromStyle(connection, getModelStyle());
        return connection;
    }

    @objid ("2acdc1d3-55b6-11e2-877f-002564c97630")
    @Override
    public void createEditPolicies() {
        super.createEditPolicies();
        
        installEditPolicy("CreateInfoFlows", new DefaultCreateInfoFlowOnLinkEditPolicy());
        
    }

}
