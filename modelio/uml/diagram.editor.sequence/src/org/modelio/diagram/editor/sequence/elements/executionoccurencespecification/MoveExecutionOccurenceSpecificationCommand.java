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

package org.modelio.diagram.editor.sequence.elements.executionoccurencespecification;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.commands.Command;
import org.modelio.metamodel.uml.behavior.interactionModel.ExecutionOccurenceSpecification;
import org.modelio.vcore.model.api.MTools;

/**
 * Command used to actually move or resize an Execution in a sequence diagrams. Start and finish times are absolute, while layoutData is relative to owner's location.
 */
@objid ("d8e070a4-55b6-11e2-877f-002564c97630")
public class MoveExecutionOccurenceSpecificationCommand extends Command {
    @objid ("d8e070a7-55b6-11e2-877f-002564c97630")
    private int newTime;

    @objid ("d8e070a6-55b6-11e2-877f-002564c97630")
    private GmExecutionOccurenceSpecification gmExecutionOccurenceSpecification;

    @objid ("d8e070a8-55b6-11e2-877f-002564c97630")
    @Override
    public void execute() {
        // Modifying the Ob model, the rest will follow...
        ExecutionOccurenceSpecification executionOccurenceSpecification = (ExecutionOccurenceSpecification) this.gmExecutionOccurenceSpecification.getRelatedElement();
        executionOccurenceSpecification.setLineNumber(this.newTime);
    }

    /**
     * Sets the modified execution occurence specification.
     * 
     * @param value the modified execution occurence specification.
     */
    @objid ("d8e070ab-55b6-11e2-877f-002564c97630")
    public void setGmExecutionOccurenceSpecification(GmExecutionOccurenceSpecification value) {
        this.gmExecutionOccurenceSpecification = value;
    }

    /**
     * Sets the new "time" of the ExecutionOccurenceSpecification.
     * 
     * @param value the new "time" of the ExecutionOccurenceSpecification.
     */
    @objid ("d8e070af-55b6-11e2-877f-002564c97630")
    public void setNewTime(int value) {
        this.newTime = value;
    }

    @objid ("d8e070b3-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canExecute() {
        boolean isDiagramModifiable = MTools.getAuthTool().canModify(this.gmExecutionOccurenceSpecification.getDiagram().getRelatedElement());
        boolean isExecutionOccurrenceSpecificationModifiable = MTools.getAuthTool().canModify(this.gmExecutionOccurenceSpecification.getRelatedElement());
        return isDiagramModifiable && isExecutionOccurrenceSpecificationModifiable;
    }

}
