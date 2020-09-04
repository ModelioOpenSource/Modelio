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

package org.modelio.diagram.elements.common.linkednode;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.tools.ConnectionCreationTool;

/**
 * The default creation tool to create a node connected to another. With this tool, the user must click and release the
 * left mouse button on the source edit part and then click and release the left mouse button on a target edit part that
 * should represent void space. By default, this tool will remain active after connections are created. The user must
 * select a different tool to deactivate this tool.
 */
@objid ("7eb8bb7f-1dec-11e2-8cad-001ec947c8cc")
public class LinkedNodeCreationTool extends ConnectionCreationTool implements LinkedNodeRequestConstants {
    @objid ("7eb8bb83-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected String getCommandName() {
        if (isInState(STATE_CONNECTION_STARTED | STATE_ACCESSIBLE_DRAG_IN_PROGRESS))
            return REQ_LINKEDNODE_END;
        else
            return REQ_LINKEDNODE_START;
    }

}
