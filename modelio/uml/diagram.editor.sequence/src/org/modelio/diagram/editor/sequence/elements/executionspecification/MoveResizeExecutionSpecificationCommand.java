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

package org.modelio.diagram.editor.sequence.elements.executionspecification;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.commands.Command;
import org.modelio.metamodel.uml.behavior.interactionModel.ExecutionSpecification;

/**
 * Command used to actually move or resize an Execution in a sequence diagrams. Start and finish times are absolute, while layoutData is relative to owner's location.
 */
@objid ("d8efb2dc-55b6-11e2-877f-002564c97630")
public class MoveResizeExecutionSpecificationCommand extends Command {
    @objid ("d8efb2df-55b6-11e2-877f-002564c97630")
    private int finishTime;

    @objid ("d8efb2e0-55b6-11e2-877f-002564c97630")
    private int startTime;

    @objid ("d8efb2de-55b6-11e2-877f-002564c97630")
    private GmExecutionSpecification gmExecution;

    @objid ("d8efb2e1-55b6-11e2-877f-002564c97630")
    @Override
    public void execute() {
        // Modifying the Ob model, the rest will follow...
        ExecutionSpecification execution = this.gmExecution.getRelatedElement();
        execution.getStart().setLineNumber(this.startTime);
        execution.setLineNumber(this.startTime);
        execution.getFinish().setLineNumber(this.finishTime);
    }

    /**
     * Sets the new "time" of the ExecutionOccurenceSpecification that finishes the execution.
     * 
     * @param value the new "time" of the ExecutionOccurenceSpecification that finishes the execution.
     */
    @objid ("d8efb2e4-55b6-11e2-877f-002564c97630")
    public void setFinishTime(int value) {
        this.finishTime = value;
    }

    /**
     * Sets the modified execution.
     * 
     * @param value the modified execution.
     */
    @objid ("d8efb2e8-55b6-11e2-877f-002564c97630")
    public void setGmExecution(GmExecutionSpecification value) {
        this.gmExecution = value;
    }

    /**
     * Sets the new "time" of the ExecutionOccurenceSpecification that starts the execution.
     * 
     * @param value the new "time" of the ExecutionOccurenceSpecification that starts the execution.
     */
    @objid ("d8efb2ec-55b6-11e2-877f-002564c97630")
    public void setStartTime(int value) {
        this.startTime = value;
    }

}
