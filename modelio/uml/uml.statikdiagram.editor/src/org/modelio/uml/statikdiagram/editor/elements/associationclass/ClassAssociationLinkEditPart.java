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
package org.modelio.uml.statikdiagram.editor.elements.associationclass;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;
import org.modelio.diagram.elements.core.link.LinkEditPart;
import org.modelio.diagram.styles.core.IStyle;

/**
 * Inheritance link edit part.
 */
@objid ("33f3907a-55b7-11e2-877f-002564c97630")
public class ClassAssociationLinkEditPart extends LinkEditPart {
    @objid ("33f3907e-55b7-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        PolylineConnection connection = (PolylineConnection) super.createFigure();
        
        connection.setLineStyle(org.eclipse.swt.SWT.LINE_DASH);
        
        refreshFromStyle(connection, getModelStyle());
        return connection;
    }

    @objid ("33f39083-55b7-11e2-877f-002564c97630")
    @Override
    protected void refreshFromStyle(IFigure aFigure, IStyle style) {
        super.refreshFromStyle(aFigure, style);
    }

}
