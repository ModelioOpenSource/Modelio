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

package org.modelio.diagram.editor.communication.elements.communicationmessage;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.groupitem.GroupItemEditPart;
import org.modelio.diagram.elements.core.policies.HoverFeedbackEditPolicy;

/**
 * Edit part for {@link GmCommunicationMessageLabel}.
 * <p>
 * Extends {@link GroupItemEditPart}.
 */
@objid ("7a3d7358-55b6-11e2-877f-002564c97630")
public class CommunicationMessageLabelEditPart extends GroupItemEditPart {
    @objid ("7a3d735c-55b6-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        
        // Allow creation of information flows
        installEditPolicy("infoflows", new CreateInfoFlowOnMessageEditPolicy());
        
        // draw rectangle on hover
        installEditPolicy("hover", new HoverFeedbackEditPolicy());
    }

}
