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

import java.util.OptionalInt;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.link.CreateBendedConnectionRequest;
import org.modelio.diagram.elements.core.link.DefaultCreateLinkCommand;
import org.modelio.diagram.elements.core.link.ModelioLinkCreationContext;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.metamodel.uml.behavior.interactionModel.ExecutionSpecification;
import org.modelio.metamodel.uml.behavior.interactionModel.Gate;
import org.modelio.metamodel.uml.behavior.interactionModel.Lifeline;
import org.modelio.metamodel.uml.behavior.interactionModel.Message;
import org.modelio.uml.sequencediagram.editor.elements.sequencediagram.GmSequenceDiagram;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Command that creates a relationship element between the 2 node model org.modelio.uml.sequencediagram.editor.elements represented by the given EditPart.
 */
@objid ("d94fd452-55b6-11e2-877f-002564c97630")
public class CreateMessageCommand extends DefaultCreateLinkCommand {
    @objid ("d94fd456-55b6-11e2-877f-002564c97630")
    private int sourceTime;

    @objid ("d94fd457-55b6-11e2-877f-002564c97630")
    private int targetTime;

    @objid ("4fb6094a-55c2-11e2-9337-002564c97630")
    private CreateBendedConnectionRequest request;

    /**
     * C'tor.
     * @param context the creation context.
     */
    @objid ("d94fd45b-55b6-11e2-877f-002564c97630")
    public  CreateMessageCommand(ModelioLinkCreationContext context) {
        super(context);
    }

    @objid ("d94fd461-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canExecute() {
        if (!super.canExecute()) {
            return false;
        }
        
        if (this.context.getElementToUnmask() != null) {
            return true;
        } 
        
        // It is an actual creation (and not a simple unmasking).
        MClass toCreateMetaclass = this.context.getMetaclass();
        if (toCreateMetaclass.getJavaInterface() == Message.class) {
            if (this.targetNode != null) {
                MessageType type = getMessageType();
                final MObject targetEl = this.targetNode.getRelatedElement();
                if (targetEl instanceof Gate || targetEl instanceof ExecutionSpecification) {
                    // Creation and destruction messages targeting a gate and execution rectangles are forbidden
                    switch (type) {
                    case Creation:
                    case Destruction:
                        return false;
                    default:
                    }
                } else if (targetEl instanceof Lifeline) {
                    // Check creation and destruction message target 
                    Lifeline lf = (Lifeline) targetEl;
                    switch (type) {
                    case Creation:
                        // Cannot self create
                        if (this.sourceNode.equals(this.targetNode)) {
                            return false;
                        }
                        
                        // creation message must be received first
                        OptionalInt min = lf.getCoveredBy().stream().mapToInt(f -> f.getLineNumber()).min();
                        if (min.isPresent() && min.getAsInt() <= this.targetTime) {
                            return false;
                        }
                        break;
                    case Destruction:
                        // destruction message must be received last
                        OptionalInt max = lf.getCoveredBy().stream().mapToInt(f -> f.getLineNumber()).max();
                        if (max.isPresent() && max.getAsInt() >= this.targetTime) {
                            return false;
                        }
                        break;
                    default:
                    }
                }
            }
        }
        return true;
    }

    @objid ("d9515adb-55b6-11e2-877f-002564c97630")
    @Override
    public void execute() {
        MessageType type = getMessageType();
        
        // Create the link model element
        final IGmDiagram gmDiagram = this.sourceNode.getDiagram();
        CreateMessageHelper helper = new CreateMessageHelper((GmSequenceDiagram) gmDiagram);
        helper.setRequest(this.request);
        helper.createMessage(this.sourceNode, this.sourceTime, this.targetNode, this.targetTime, type);
        
    }

    @objid ("d9515ade-55b6-11e2-877f-002564c97630")
    public int getSourceTime() {
        // Automatically generated method. Please delete this comment before
        // entering specific code.
        return this.sourceTime;
    }

    @objid ("d9515ae2-55b6-11e2-877f-002564c97630")
    public int getTargetTime() {
        // Automatically generated method. Please delete this comment before
        // entering specific code.
        return this.targetTime;
    }

    @objid ("d9515ae6-55b6-11e2-877f-002564c97630")
    public void setSourceTime(int value) {
        // Automatically generated method. Please delete this comment before
        // entering specific code.
        this.sourceTime = value;
        
    }

    @objid ("d9515ae9-55b6-11e2-877f-002564c97630")
    public void setTargetTime(int value) {
        // Automatically generated method. Please delete this comment before
        // entering specific code.
        this.targetTime = value;
        
    }

    @objid ("d9515aec-55b6-11e2-877f-002564c97630")
    public void setRequest(CreateBendedConnectionRequest request) {
        this.request = request;
    }

    @objid ("d9515af1-55b6-11e2-877f-002564c97630")
    public CreateBendedConnectionRequest getRequest() {
        return this.request;
    }

    @objid ("a163a681-ce91-480c-b906-de67a120c782")
    protected MessageType getMessageType() {
        return MessageType.valueOf((String) this.context.getProperties().get("messageType"));
    }

}
