/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.diagram.editor.state.elements.statediagram;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.modelio.diagram.elements.common.abstractdiagram.DiagramElementDropEditPolicy;
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;
import org.modelio.metamodel.uml.behavior.stateMachineModel.InternalTransition;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Extension of {@link DiagramElementDropEditPolicy} handling InternalTransition as nodes instead of links.
 */
@objid ("d192e5e2-f75c-4ebc-bf2d-e24448ac9b25")
class StateDiagramElementDropEditPolicy extends DiagramElementDropEditPolicy {
    @objid ("7271cf3c-4ca8-4fce-91b3-d2a972b9235a")
    @Override
    protected Command getSmartDropCommand(ModelElementDropRequest request) {
        final CompoundCommand command = new CompoundCommand();
        
        Point dropLocation = request.getDropLocation();
        
        for (final MObject toUnmask : request.getDroppedElements()) {
            if (toUnmask instanceof InternalTransition) {
                // Unmask InternalTransition as nodes instead of links
                command.add(createDropCommandForElement(dropLocation, toUnmask));
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

    /**
     * Create a standard unmask request
     */
    @objid ("53abbed5-2649-49d6-b68f-e6cb512834e9")
    private Command createSubCommand(final ModelElementDropRequest request, final Point dropLocation, final MObject toUnmask) {
        final ModelElementDropRequest subReq = new ModelElementDropRequest();
        subReq.setDroppedElements(new MObject[] { toUnmask });
        subReq.setExtendedData(request.getExtendedData());
        subReq.setLocation(dropLocation);
        subReq.setSmart(request.isSmart());
        return getDropCommand(subReq);
    }

}
