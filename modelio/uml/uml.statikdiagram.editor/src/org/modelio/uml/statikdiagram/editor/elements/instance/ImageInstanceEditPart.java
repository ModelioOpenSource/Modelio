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

package org.modelio.uml.statikdiagram.editor.elements.instance;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.image.NonSelectableImageEditPart;
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;
import org.modelio.diagram.elements.umlcommon.constraint.ConstraintLinkEditPolicy;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.uml.statikdiagram.editor.elements.naryconnector.AcceptNConnectorEditPolicy;
import org.modelio.uml.statikdiagram.editor.elements.narylink.AcceptNLinkEditPolicy;

/**
 * EditPart for {@link GmInstancePrimaryNode} in image mode.
 * <p>
 * Accepts n-ary links.
 * 
 * @author cmarin
 */
@objid ("3541bb6c-55b7-11e2-877f-002564c97630")
public class ImageInstanceEditPart extends NonSelectableImageEditPart {
    @objid ("3541bb70-55b7-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        
        installEditPolicy("constraint", new ConstraintLinkEditPolicy(false));
        installEditPolicy("nary-link", new AcceptNLinkEditPolicy(true));
        
        GmInstancePrimaryNode model = (GmInstancePrimaryNode) getModel();
        if (model.getRelatedElement() instanceof BindableInstance) {
            installEditPolicy("nary-connector", new AcceptNConnectorEditPolicy(true));
        }
        
        installEditPolicy(ModelElementDropRequest.TYPE, new InstanceElementDropEditPolicy());
    }

}
