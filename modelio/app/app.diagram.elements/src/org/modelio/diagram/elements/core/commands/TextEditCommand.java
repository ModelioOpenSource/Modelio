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
package org.modelio.diagram.elements.core.commands;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.model.IEditableText;

/**
 * Command that changes an element name, signature or other attribute.
 */
@objid ("7f4a2ad4-1dec-11e2-8cad-001ec947c8cc")
public class TextEditCommand extends org.eclipse.gef.commands.Command {
    @objid ("8f025634-1e83-11e2-8cad-001ec947c8cc")
    String newName;

    @objid ("7f4a2ad9-1dec-11e2-8cad-001ec947c8cc")
    IEditableText modelToRename;

    /**
     * Creates a rename command.
     * @param modelToRename The editable element.
     * @param newName The new name.
     */
    @objid ("7f4a2ada-1dec-11e2-8cad-001ec947c8cc")
    public  TextEditCommand(IEditableText modelToRename, String newName) {
        this.modelToRename = modelToRename;
        this.newName = newName;
        
    }

    @objid ("7f4a2adf-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void execute() {
        this.modelToRename.setText(this.newName);
    }

}
