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

package org.modelio.diagram.editor.statik.elements.instanceheader;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.modelio.diagram.elements.common.header.ModelElementHeaderEditPart;
import org.modelio.diagram.elements.common.header.WrappedHeaderFigure;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Instance;

/**
 * Edit part for {@link GmInstanceHeader}.
 */
@objid ("35495ca3-55b7-11e2-877f-002564c97630")
public class InstanceHeaderEditPart extends ModelElementHeaderEditPart {
    @objid ("35495ca7-55b7-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        WrappedHeaderFigure fig = (WrappedHeaderFigure) super.createFigure();
        
        // Style independent properties
        // ----------------------------
        
        // underline InstanceSpecification : instances that are not parts or Ports.
        Instance instance = ((GmInstanceHeader) getModel()).getRelatedElement();
        if (!(instance instanceof BindableInstance))
            fig.setUnderline(true);
        return fig;
    }

}
