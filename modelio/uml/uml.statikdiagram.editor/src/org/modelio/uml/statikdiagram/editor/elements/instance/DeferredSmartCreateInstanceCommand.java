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
import org.eclipse.gef.EditPart;
import org.eclipse.gef.requests.CreateRequest;
import org.modelio.diagram.elements.core.commands.DeferredCreateCommand;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.metamodel.uml.statik.BindableInstance;

/**
 * Deferred smart instance creation command.
 * <p>
 * The request must be a smart instance creation request.
 */
@objid ("352de53a-55b7-11e2-877f-002564c97630")
class DeferredSmartCreateInstanceCommand extends DeferredCreateCommand {
    /**
     * Create a deferred command.
     * @param req The creation request. The request must be a smart instance creation request.
     * @param sender The edit part sending the request
     */
    @objid ("352de53e-55b7-11e2-877f-002564c97630")
    public  DeferredSmartCreateInstanceCommand(final CreateRequest req, final EditPart sender) {
        super(req, sender);
    }

    @objid ("352de545-55b7-11e2-877f-002564c97630")
    @Override
    protected GmCompositeNode getTargetNode() throws IllegalArgumentException {
        return this.getCompositeNode().getCompositeFor(BindableInstance.class);
    }

}
