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
package org.modelio.uml.communicationdiagram.editor.elements.communicationmessage;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPolicy;
import org.modelio.diagram.elements.common.group.GroupEditPart;
import org.modelio.diagram.elements.core.policies.HoverFeedbackEditPolicy;

/**
 * Edit policy for {@link GmCommunicationSentMessageGroup} and {@link GmCommunicationInvertedMessageGroup}.
 */
@objid ("7a3d7349-55b6-11e2-877f-002564c97630")
public class CommunicationMessageGroupEditPart extends GroupEditPart {
    /**
     * The group is moveable.
     */
    @objid ("7a3d734d-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSelectable() {
        return true;
    }

    @objid ("7a3d7353-55b6-11e2-877f-002564c97630")
    @Override
    protected final void createEditPolicies() {
        super.createEditPolicies();
        
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new CommunicationMessageGroupCreatePolicy());
        installEditPolicy("hover", new HoverFeedbackEditPolicy());
        
    }

}
