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
package org.modelio.diagram.elements.umlcommon.namespaceuse;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.PolylineDecoration;
import org.modelio.diagram.elements.core.figures.decorations.DefaultPolylineDecoration;
import org.modelio.diagram.elements.core.link.LinkEditPart;

/**
 * Edit part for {@link GmNamespaceUse}.
 */
@objid ("817f37ff-1dec-11e2-8cad-001ec947c8cc")
public class NamespaceUseEditPart extends LinkEditPart {
    @objid ("817f3801-1dec-11e2-8cad-001ec947c8cc")
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

}
