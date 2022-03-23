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
package org.modelio.uml.statikdiagram.editor.elements.bindinglink;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.swt.SWT;
import org.modelio.diagram.elements.core.link.LinkEditPart;

/**
 * Edit part for {@link GmBindingLink}.
 * <p>
 * A binding link is always dashed.
 * 
 * @author cmarin
 */
@objid ("34108e5a-55b7-11e2-877f-002564c97630")
public class BindingLinkEditPart extends LinkEditPart {
    @objid ("34108e5e-55b7-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        final PolylineConnection connection = (PolylineConnection) super.createFigure();
        
        // Set style independent properties
        connection.setLineStyle(SWT.LINE_DASH);
        
        // Set style dependent properties
        // Already done by super.createFigure()
        return connection;
    }

}
