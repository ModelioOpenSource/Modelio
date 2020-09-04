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

package org.modelio.diagram.editor.usecase.elements.system;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.modelio.diagram.editor.usecase.elements.usecase.GmUseCase;
import org.modelio.diagram.elements.common.freezone.DefaultFreeZoneLayoutEditPolicy;

@objid ("5e54de77-55b7-11e2-877f-002564c97630")
class SystemFreeZoneLayoutEditPolicy extends DefaultFreeZoneLayoutEditPolicy {
    @objid ("5e54de7a-55b7-11e2-877f-002564c97630")
    @Override
    protected Command createChangeConstraintCommand(final ChangeBoundsRequest request, final EditPart child, final Object constraint) {
        CompoundCommand command = new CompoundCommand();
        command.add(super.createChangeConstraintCommand(request, child, constraint));
        
        if (child.getModel() instanceof GmUseCase) {
            command.add(new SystemResizeCommand((GraphicalEditPart) getHost(),
                                                (GraphicalEditPart) child,
                                                request));
        }
        return command.unwrap();
    }

}
