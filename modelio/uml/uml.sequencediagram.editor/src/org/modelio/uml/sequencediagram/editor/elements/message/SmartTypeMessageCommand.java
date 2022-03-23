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
package org.modelio.uml.sequencediagram.editor.elements.message;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.commands.Command;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.behavior.interactionModel.Message;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.vcore.model.api.MTools;

/**
 * Command produced by the drop element edit policy of Message that can type a Message with an Operation or a Signal.
 * 
 * @author fpoyer
 */
@objid ("d96b4b99-55b6-11e2-877f-002564c97630")
public class SmartTypeMessageCommand extends Command {
    @objid ("d96b4ba4-55b6-11e2-877f-002564c97630")
    private String oldMessageName = "";

    @objid ("35274cf2-2e4d-4960-82e5-329c04468cfc")
    private Message messageToType;

    @objid ("95704dab-d401-405d-9a55-3ed15c568616")
    private Operation operation;

    @objid ("bdec36b8-7d2c-4888-a193-efcda3a0b44a")
    private Signal signal;

    /**
     * Constructor to type the message with a called operation
     * @param messageToType the message to type
     * @param operation the operation to call
     */
    @objid ("d96b4ba5-55b6-11e2-877f-002564c97630")
    public  SmartTypeMessageCommand(final Message messageToType, final Operation operation) {
        this.messageToType = messageToType;
        this.operation = operation;
        
    }

    /**
     * Constructor to type the message with a called operation
     * @param messageToType the message to type
     * @param signal the signal to send
     */
    @objid ("d96b4bb0-55b6-11e2-877f-002564c97630")
    public  SmartTypeMessageCommand(final Message messageToType, final Signal signal) {
        this.messageToType = messageToType;
        this.signal = signal;
        
    }

    @objid ("d96cd219-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canExecute() {
        return MTools.getAuthTool().canModify(this.messageToType) &&
                        (this.operation == null || MTools.getAuthTool().canModify(this.operation)) &&
                        (this.signal == null || MTools.getAuthTool().canModify(this.signal));
        
    }

    @objid ("d96cd21e-55b6-11e2-877f-002564c97630")
    @Override
    public void execute() {
        this.oldMessageName = this.messageToType.getName();
        if (this.operation != null) {
            this.messageToType.setInvoked(this.operation);
            this.messageToType.setName(this.operation.getName());
        } else if (this.signal != null) {
            this.messageToType.setSignalSignature(this.signal);
            this.messageToType.setName(this.signal.getName());
        }
        
    }

    @objid ("d96cd221-55b6-11e2-877f-002564c97630")
    @Override
    public void undo() {
        if (this.operation != null) {
            this.messageToType.setInvoked(null);
            this.messageToType.setName(this.oldMessageName);
        } else if (this.signal != null) {
            this.messageToType.setSignalSignature(null);
            this.messageToType.setName(this.oldMessageName);
        }
        
    }

    @objid ("d96cd224-55b6-11e2-877f-002564c97630")
    @Override
    public void redo() {
        if (this.operation != null) {
            this.messageToType.setInvoked(this.operation);
            this.messageToType.setName(this.operation.getName());
        } else if (this.signal != null) {
            this.messageToType.setSignalSignature(this.signal);
            this.messageToType.setName(this.signal.getName());
        }
        
    }

}
