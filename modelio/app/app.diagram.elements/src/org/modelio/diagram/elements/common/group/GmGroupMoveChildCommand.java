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
package org.modelio.diagram.elements.common.group;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.commands.Command;

/**
 * Command to reorder child elements in a {@link GmGroup}
 * 
 * @author cmarin
 */
@objid ("7e595d47-1dec-11e2-8cad-001ec947c8cc")
public class GmGroupMoveChildCommand extends Command {
    /**
     * Set the child to be moved.
     * @param model the child to be moved.
     */
    @objid ("7e595d4b-1dec-11e2-8cad-001ec947c8cc")
    public void setChild(Object model) {
        // Nothing to do
    }

    /**
     * set the element after which the child must be moved.
     * @param model the element after which the child must be moved.
     */
    @objid ("7e595d4f-1dec-11e2-8cad-001ec947c8cc")
    public void setAfter(Object model) {
        // Nothing to do
    }

}
