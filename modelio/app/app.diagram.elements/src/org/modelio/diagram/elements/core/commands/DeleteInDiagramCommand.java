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
import org.eclipse.gef.commands.Command;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmObject;

/**
 * Delete a graphic element, without modifying the represented model element.
 * 
 * @author fpoyer
 */
@objid ("7f3e3f16-1dec-11e2-8cad-001ec947c8cc")
public class DeleteInDiagramCommand extends Command {
    /**
     * Diagram element to delete;
     */
    @objid ("7f3e3f1a-1dec-11e2-8cad-001ec947c8cc")
    private IGmObject toDelete;

    @objid ("7f3e3f1c-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void execute() {
        this.toDelete.delete();
    }

    /**
     * Set the graphic element to delete.
     * @param el the graphic element to delete.
     * @return this instance
     */
    @objid ("7f3e3f1f-1dec-11e2-8cad-001ec947c8cc")
    public DeleteInDiagramCommand setNodetoDelete(IGmObject el) {
        this.toDelete = el;
        return this;
    }

    /**
     * The command is executable if the diagram is modifiable.
     */
    @objid ("7f3e3f23-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean canExecute() {
        IGmDiagram gmDiagram = this.toDelete.getDiagram();
        return gmDiagram == null || gmDiagram.getRelatedElement() == null || gmDiagram.isUserEditable();
    }

}
