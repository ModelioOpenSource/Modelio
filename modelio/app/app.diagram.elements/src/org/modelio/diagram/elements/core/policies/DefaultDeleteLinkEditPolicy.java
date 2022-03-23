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
package org.modelio.diagram.elements.core.policies;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ConnectionEditPolicy;
import org.eclipse.gef.requests.GroupRequest;
import org.modelio.diagram.elements.core.commands.DeleteInDiagramCommand;
import org.modelio.diagram.elements.core.model.IGmObject;

/**
 * Edit policy to remove a diagram link from the diagram.
 * <p>
 * Default edit policy for the {@link EditPolicy#CONNECTION_ROLE} on links.
 * 
 * @see org.eclipse.gef.editpolicies.ConnectionEditPolicy
 * @author cmarin
 */
@objid ("80bbb733-1dec-11e2-8cad-001ec947c8cc")
public class DefaultDeleteLinkEditPolicy extends ConnectionEditPolicy {
    @objid ("80bbb737-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getDeleteCommand(GroupRequest request) {
        if (getHost().isSelectable()) {
            DeleteInDiagramCommand ret = new DeleteInDiagramCommand();
            ret.setNodetoDelete( (IGmObject) getHost().getModel());
            return ret;
        } else {
            return getHost().getParent().getCommand(request);
        }
        
    }

}
