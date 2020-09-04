/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.diagram.elements.factories.generic;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.modelio.diagram.elements.common.genericlink.GenericLinkEditPart;
import org.modelio.diagram.elements.common.genericlink.GmGenericLink;
import org.modelio.diagram.elements.common.genericnode.GenericNodeEditPart;
import org.modelio.diagram.elements.common.genericnode.GmGenericNode;
import org.modelio.diagram.elements.core.model.factory.IGmLinkFactory;
import org.modelio.diagram.elements.core.model.factory.IGmNodeFactory;

/**
 * Implementation of {@link IGmNodeFactory} and {@link IGmLinkFactory} returning edit parts for the generic GMs.
 */
@objid ("54cc871b-4723-4744-85be-9538a22d4e8e")
public class GenericEditPartFactory implements EditPartFactory {
    @objid ("7ae4c17b-6dff-48cb-8155-135ac6b5c485")
    @Override
    public EditPart createEditPart(EditPart context, Object model) {
        EditPart editPart;
        if (model.getClass() == GmGenericNode.class) {
            editPart = new GenericNodeEditPart();
            editPart.setModel(model);
            return editPart;
        }
        
        if (model.getClass() == GmGenericLink.class) {
            editPart = new GenericLinkEditPart();
            editPart.setModel(model);
            return editPart;
        }
        return null;
    }

}
