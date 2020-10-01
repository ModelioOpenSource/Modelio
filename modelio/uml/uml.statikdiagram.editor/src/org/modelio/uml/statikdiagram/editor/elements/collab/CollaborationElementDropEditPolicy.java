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

package org.modelio.uml.statikdiagram.editor.elements.collab;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.policies.DefaultElementDropEditPolicy;
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.uml.statikdiagram.editor.elements.classifier.SmartCreateCollabUseCommand;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Overrides the default drop handling policy so that in some case it searches for the parent of the dropped element
 */
@objid ("344d9774-55b7-11e2-877f-002564c97630")
public class CollaborationElementDropEditPolicy extends DefaultElementDropEditPolicy {
    @objid ("344d9778-55b7-11e2-877f-002564c97630")
    @Override
    protected EditPart getDropTargetEditPart(final ModelElementDropRequest request) {
        final GmCompositeNode gmModel = (GmCompositeNode) this.getHost().getModel();
        
        // If either of the dropped elements cannot be unmasked, return null.
        for (MObject droppedElement : request.getDroppedElements()) {
            if (!gmModel.canUnmask(droppedElement)) {
                // Gm doesn't know how to handle this element directly, look if
                // it is an element for which we can do something "smarter".
                if (!isSmartCollabUseNodeTarget(droppedElement, request)) {
                    // It is not a smart interaction
                    //
                    // The Gm cannot unmask this element directly, and we don't know
                    // what to do with it... return null
                    return null;
                }
            }
        }
        
        // All dropped elements understood: return host!
        return this.getHost();
    }

    @objid ("344d9782-55b7-11e2-877f-002564c97630")
    @Override
    protected Command getSmartDropCommand(final ModelElementDropRequest request) {
        CompoundCommand command = new CompoundCommand();
        
        Point dropLocation = request.getDropLocation();
        
        for (MObject toUnmask : request.getDroppedElements()) {
        
            if (isSmartCollabUseNodeTarget(toUnmask, request)) {
                command.add(getSmartCreateCollabUseCommand(dropLocation, toUnmask));
            } else if (toUnmask != null) {
                // Deal with 'non-smart' drops
                command.add(createSubCommand(request, dropLocation, toUnmask));
            }
        
            // Introduce some offset, so that all elements are not totally
            // on top of each other.
            dropLocation = dropLocation.getTranslated(20, 20);
        }
        return command;
    }

    @objid ("344d978c-55b7-11e2-877f-002564c97630")
    private Command getSmartCreateCollabUseCommand(final Point dropLocation, final MObject toUnmask) {
        final GmModel gmModel = (GmModel) getHost().getModel();
        final Collaboration owner = (Collaboration) gmModel.getRelatedElement();
        return new SmartCreateCollabUseCommand(dropLocation, toUnmask, getHost(), owner);
    }

    @objid ("344d9796-55b7-11e2-877f-002564c97630")
    private boolean isSmartCollabUseNodeTarget(final MObject element, final ModelElementDropRequest request) {
        if (!request.isSmart())
            return false;
        return (element instanceof Collaboration);
    }

    @objid ("344d97a2-55b7-11e2-877f-002564c97630")
    private Command createSubCommand(final ModelElementDropRequest request, final Point dropLocation, final MObject toUnmask) {
        ModelElementDropRequest subReq = new ModelElementDropRequest();
        subReq.setDroppedElements(new MObject[] { toUnmask });
        subReq.setExtendedData(request.getExtendedData());
        subReq.setLocation(dropLocation);
        subReq.setSmart(request.isSmart());
        return getDropCommand(subReq);
    }

}
