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

package org.modelio.diagram.editor.sequence.elements.message;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.modelio.diagram.elements.core.policies.DefaultElementDropEditPolicy;
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.behavior.interactionModel.Message;
import org.modelio.metamodel.uml.behavior.interactionModel.MessageSort;
import org.modelio.metamodel.uml.statik.Operation;

/**
 * Specialisation of the default drop request handling policy to add some smart interactions.
 * 
 * @author fpoyer
 */
@objid ("d963aaa0-55b6-11e2-877f-002564c97630")
public class MessageElementDropEditPolicy extends DefaultElementDropEditPolicy {
    /**
     * Unmasking of an operation on a Message is allowed. It provoke a smart interaction typing the message by the operation.
     */
    @objid ("d963aaa4-55b6-11e2-877f-002564c97630")
    @Override
    protected EditPart getDropTargetEditPart(final ModelElementDropRequest request) {
        Boolean isSmart = request.isSmart();
        isSmart = isSmart && request.getDroppedElements().length == 1;
        isSmart = isSmart &&
                (request.getDroppedElements()[0] instanceof Operation || request.getDroppedElements()[0] instanceof Signal);
        isSmart = isSmart &&
                ((GmMessage) getHost().getModel()).getRelatedElement().getSortOfMessage() != MessageSort.RETURNMESSAGE;
        
        if (isSmart) {
            return getHost();
        } else {
            return super.getDropTargetEditPart(request);
        }
    }

    @objid ("d963aaad-55b6-11e2-877f-002564c97630")
    @Override
    protected Command getSmartDropCommand(final ModelElementDropRequest request) {
        Message message = ((GmMessage) getHost().getModel()).getRelatedElement();
        if (request.getDroppedElements().length == 1 &&
                message.getSortOfMessage() != MessageSort.RETURNMESSAGE) {
            if (request.getDroppedElements()[0] instanceof Operation) {
                return new SmartTypeMessageCommand(message, (Operation) request.getDroppedElements()[0]);
            } else if (request.getDroppedElements()[0] instanceof Signal) {
                return new SmartTypeMessageCommand(message, (Signal) request.getDroppedElements()[0]);
            }
        }
        return super.getSmartDropCommand(request);
    }

}
