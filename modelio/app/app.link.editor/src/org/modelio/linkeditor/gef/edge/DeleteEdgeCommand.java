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
package org.modelio.linkeditor.gef.edge;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.graph.Edge;
import org.eclipse.gef.commands.Command;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Command that deletes the model element represented by the edge.
 */
@objid ("1b9ac6f3-5e33-11e2-b81d-002564c97630")
public class DeleteEdgeCommand extends Command {
    @objid ("056caf93-6c49-4c58-8fc4-cb300dfddca3")
    private final Edge edge;

    /**
     * Creates a command that will delete the given Edge when executed.
     * @param edge the edge to delete.
     */
    @objid ("1b9ac6fa-5e33-11e2-b81d-002564c97630")
    public  DeleteEdgeCommand(final Edge edge) {
        this.edge = edge;
    }

    @objid ("1b9ac701-5e33-11e2-b81d-002564c97630")
    @Override
    public boolean canExecute() {
        if (this.edge.data instanceof MObject) {
            MObject link = (MObject) this.edge.data;
        
            boolean linkCanBeDeleted = link.getStatus().isModifiable();
        
            MObject source = link.getMClass().getMetamodel().getMExpert().getSource(link);
            boolean sourceCanBeModified = source != null && source.getStatus().isModifiable();
            return linkCanBeDeleted && sourceCanBeModified;
        } else {
            return false;
        }
        
    }

    @objid ("1b9ac705-5e33-11e2-b81d-002564c97630")
    @Override
    public void execute() {
        if (this.edge.data instanceof MObject) {
            ((MObject) this.edge.data).delete();
        }
        
    }

}
