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

package org.modelio.diagram.editor.communication.elements.factories;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.modelio.diagram.editor.communication.elements.communicationchannel.CommunicationChannelEditPart;
import org.modelio.diagram.editor.communication.elements.communicationchannel.GmCommunicationChannel;
import org.modelio.diagram.editor.communication.elements.communicationdiagram.CommunicationDiagramEditPart;
import org.modelio.diagram.editor.communication.elements.communicationdiagram.GmCommunicationDiagram;
import org.modelio.diagram.editor.communication.elements.communicationmessage.CommunicationMessageArrowEditPart;
import org.modelio.diagram.editor.communication.elements.communicationmessage.CommunicationMessageGroupEditPart;
import org.modelio.diagram.editor.communication.elements.communicationmessage.CommunicationMessageLabelEditPart;
import org.modelio.diagram.editor.communication.elements.communicationmessage.GmCommunicationInvertedMessageArrow;
import org.modelio.diagram.editor.communication.elements.communicationmessage.GmCommunicationInvertedMessageGroup;
import org.modelio.diagram.editor.communication.elements.communicationmessage.GmCommunicationMessageLabel;
import org.modelio.diagram.editor.communication.elements.communicationmessage.GmCommunicationSentMessageArrow;
import org.modelio.diagram.editor.communication.elements.communicationmessage.GmCommunicationSentMessageGroup;
import org.modelio.diagram.editor.communication.elements.communicationnode.CommunicationNodeEditPart;
import org.modelio.diagram.editor.communication.elements.communicationnode.GmCommunicationNode;
import org.modelio.diagram.editor.communication.elements.communicationnode.GmCommunicationNodePrimaryNode;
import org.modelio.diagram.elements.common.portcontainer.PortContainerEditPart;
import org.modelio.diagram.elements.core.node.GmNodeModel;

/**
 * The State diagram EditPart factory.
 * <p>
 * This factory only processes the state specific edit parts. It is intended to be used only as a cascaded factory in order to dynamically enriching the Modelio standard factory so that this latter ends by being able to process the complete UML. The
 * StateDiagramEditPartFactory does not provide edit part for simple and image mode.
 */
@objid ("7a2fb7ca-55b6-11e2-877f-002564c97630")
public final class CommunicationEditPartFactory implements EditPartFactory {
    /**
     * the default factory to use when structured mode is requested.
     */
    @objid ("7a2fb7cc-55b6-11e2-877f-002564c97630")
    private static final StructuredModeEditPartFactory structuredModeEditPartFactory = new StructuredModeEditPartFactory();

    @objid ("7a2fb7d7-55b6-11e2-877f-002564c97630")
    @Override
    public EditPart createEditPart(EditPart context, Object model) {
        EditPart editPart;
        if (model instanceof GmNodeModel) {
            // For node models, delegates according the representation model.
            GmNodeModel node = (GmNodeModel) model;
            switch (node.getRepresentationMode()) {
            case STRUCTURED:
                editPart = CommunicationEditPartFactory.structuredModeEditPartFactory.createEditPart(context, model);
                break;
            default:
                editPart = null; // generically supported by standard factory
            }
        
            return editPart;
        } else {
            // Link models are always in structured mode.
            editPart = CommunicationEditPartFactory.structuredModeEditPartFactory.createEditPart(context, model);
            return editPart;
        }
    }

    /**
     * EditPart factory for node models in standard structured mode.
     * <p>
     * This is the default mode so the default factory.
     */
    @objid ("7a2fb7dd-55b6-11e2-877f-002564c97630")
    private static final class StructuredModeEditPartFactory implements EditPartFactory {
        @objid ("7a2fb7df-55b6-11e2-877f-002564c97630")
        @Override
        public EditPart createEditPart(EditPart context, Object model) {
            EditPart editPart = null;
            
            if (model.getClass() == GmCommunicationDiagram.class) {
                editPart = new CommunicationDiagramEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmCommunicationChannel.class) {
                editPart = new CommunicationChannelEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmCommunicationNode.class) {
                editPart = new PortContainerEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmCommunicationNodePrimaryNode.class) {
                editPart = new CommunicationNodeEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmCommunicationSentMessageGroup.class) {
                editPart = new CommunicationMessageGroupEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmCommunicationInvertedMessageGroup.class) {
                editPart = new CommunicationMessageGroupEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmCommunicationMessageLabel.class) {
                editPart = new CommunicationMessageLabelEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmCommunicationSentMessageArrow.class) {
                editPart = new CommunicationMessageArrowEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            if (model.getClass() == GmCommunicationInvertedMessageArrow.class) {
                editPart = new CommunicationMessageArrowEditPart();
                editPart.setModel(model);
                return editPart;
            }
            
            // not found
            return null;
        }

    }

}
