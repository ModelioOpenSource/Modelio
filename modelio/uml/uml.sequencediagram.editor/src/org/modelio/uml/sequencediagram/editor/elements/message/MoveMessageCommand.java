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
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.metamodel.uml.behavior.interactionModel.ExecutionOccurenceSpecification;
import org.modelio.metamodel.uml.behavior.interactionModel.ExecutionSpecification;
import org.modelio.metamodel.uml.behavior.interactionModel.Message;
import org.modelio.metamodel.uml.behavior.interactionModel.MessageEnd;
import org.modelio.metamodel.uml.behavior.interactionModel.MessageSort;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.transactions.ITransaction;

/**
 * Command that actually does the work of moving a Message in a sequence diagram.
 */
@objid ("d969c4fe-55b6-11e2-877f-002564c97630")
public class MoveMessageCommand extends Command {
    @objid ("d969c503-55b6-11e2-877f-002564c97630")
    private int sourceTimeDelta;

    @objid ("d969c504-55b6-11e2-877f-002564c97630")
    private int targetTimeDelta;

    @objid ("5060ef49-55c2-11e2-9337-002564c97630")
    private IGmDiagram diagram;

    @objid ("bb72ac7f-5e9e-4b97-a21a-c8f23d2550f4")
    private Message message;

    @objid ("d969c508-55b6-11e2-877f-002564c97630")
    @Override
    public void execute() {
        final ICoreSession session = this.diagram.getModelManager().getModelingSession();
        try (ITransaction transaction = session.getTransactionSupport().createTransaction("Clone properties")) {
            MessageEnd sendEvent = this.message.getSendEvent();
            sendEvent.setLineNumber(sendEvent.getLineNumber() + this.sourceTimeDelta);
            if (sendEvent instanceof ExecutionOccurenceSpecification &&
                    ((ExecutionOccurenceSpecification) sendEvent).getStarted() != null) {
                ExecutionSpecification execution = ((ExecutionOccurenceSpecification) sendEvent).getStarted();
                execution.setLineNumber(execution.getLineNumber() + this.sourceTimeDelta);
                execution.getFinish().setLineNumber(execution.getFinish().getLineNumber() + this.sourceTimeDelta);
            }
            MessageEnd receiveEvent = this.message.getReceiveEvent();
            receiveEvent.setLineNumber(receiveEvent.getLineNumber() + this.targetTimeDelta);
            if (receiveEvent instanceof ExecutionOccurenceSpecification &&
                    ((ExecutionOccurenceSpecification) receiveEvent).getStarted() != null) {
                ExecutionSpecification execution = ((ExecutionOccurenceSpecification) receiveEvent).getStarted();
                execution.setLineNumber(execution.getLineNumber() + this.targetTimeDelta);
                execution.getFinish().setLineNumber(execution.getFinish().getLineNumber() + this.targetTimeDelta);
                if (execution.getFinish().getSentMessage() != null &&
                        execution.getFinish().getSentMessage().getSortOfMessage() == MessageSort.RETURNMESSAGE) {
                    MoveMessageCommand command = new MoveMessageCommand();
                    command.setDiagram(this.diagram);
                    command.setMessage(execution.getFinish().getSentMessage());
                    command.setSourceTimeDelta(0);
                    command.setTargetTimeDelta(this.targetTimeDelta);
                    command.execute();
                }
            }
            transaction.commit();
        }
    }

    @objid ("d969c50b-55b6-11e2-877f-002564c97630")
    public void setMessage(final Message message) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.message = message;
    }

    @objid ("d969c511-55b6-11e2-877f-002564c97630")
    public void setSourceTimeDelta(final int sourceTimeDelta) {
        this.sourceTimeDelta = sourceTimeDelta;
    }

    @objid ("d969c515-55b6-11e2-877f-002564c97630")
    public void setTargetTimeDelta(final int targetTimeDelta) {
        this.targetTimeDelta = targetTimeDelta;
    }

    @objid ("d969c519-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canExecute() {
        return MTools.getAuthTool().canModify(this.diagram.getRelatedElement()) && MTools.getAuthTool().canModify(this.message);
    }

    @objid ("d969c51e-55b6-11e2-877f-002564c97630")
    public void setDiagram(final IGmDiagram diagram) {
        this.diagram = diagram;
    }

}
