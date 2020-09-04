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

package org.modelio.diagram.editor.sequence.elements.stateinvariant;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.commands.Command;
import org.modelio.metamodel.uml.behavior.interactionModel.StateInvariant;

/**
 * Command used to actually move or resize a StateInvariant in a sequence diagrams. Start and finish times are absolute, while layoutData is relative to owner's location.
 */
@objid ("d999127c-55b6-11e2-877f-002564c97630")
public class MoveResizeStateInvariantCommand extends Command {
    @objid ("d99a98db-55b6-11e2-877f-002564c97630")
    private int finishTime;

    @objid ("d99a98dc-55b6-11e2-877f-002564c97630")
    private int startTime;

    @objid ("058c2549-1298-4994-b209-38ae24e9c1a9")
    private GmStateInvariant gmStateInvariant;

    @objid ("b4cb687d-920f-477a-a901-b3661ee745ae")
    private Object newLayoutData;

    @objid ("d99a98de-55b6-11e2-877f-002564c97630")
    @Override
    public void execute() {
        // Modifying the Ob model, the rest will follow...
        StateInvariant stateInvariant = this.gmStateInvariant.getRelatedElement();
        stateInvariant.setLineNumber(this.startTime);
        stateInvariant.setEndLineNumber(this.finishTime);
        this.gmStateInvariant.setLayoutData(this.newLayoutData);
    }

    /**
     * Sets the new "end time" of the StateInvariant.
     * @param value the new "end time" of the StateInvariant.
     */
    @objid ("d99a98e1-55b6-11e2-877f-002564c97630")
    public void setFinishTime(int value) {
        this.finishTime = value;
    }

    /**
     * Sets the modified StateInvariant.
     * @param value the modified StateInvariant.
     */
    @objid ("d99a98e5-55b6-11e2-877f-002564c97630")
    public void setGmStateInvariant(GmStateInvariant value) {
        this.gmStateInvariant = value;
    }

    /**
     * Sets the new "time" of the StateInvariant.
     * @param value the new "time" of the StateInvariant.
     */
    @objid ("d99a98e9-55b6-11e2-877f-002564c97630")
    public void setStartTime(int value) {
        this.startTime = value;
    }

    @objid ("d99a98ed-55b6-11e2-877f-002564c97630")
    public void setNewLayoutData(final Object newLayoutData) {
        this.newLayoutData = newLayoutData;
    }

}
