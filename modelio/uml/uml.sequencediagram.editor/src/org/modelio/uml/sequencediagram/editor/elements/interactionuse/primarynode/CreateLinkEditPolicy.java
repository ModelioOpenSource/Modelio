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

package org.modelio.uml.sequencediagram.editor.elements.interactionuse.primarynode;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.gef.requests.ReconnectRequest;
import org.modelio.diagram.elements.core.link.CreateBendedConnectionRequest;
import org.modelio.diagram.elements.core.link.DefaultCreateLinkEditPolicy;
import org.modelio.diagram.elements.core.link.ModelioLinkCreationContext;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.metamodel.uml.behavior.interactionModel.Message;
import org.modelio.uml.sequencediagram.editor.elements.message.CreateMessageCommand;

/**
 * Edit policy that allow creating Links to/from an InteractionUse
 */
@objid ("5b48ccf5-1979-442a-81b8-7b0fc0ab4d7d")
public class CreateLinkEditPolicy extends DefaultCreateLinkEditPolicy {
    @objid ("8a281fad-3c4b-4251-b587-ad75601606b6")
    @Override
    protected Command getConnectionCompleteCommand(CreateConnectionRequest req) {
        ModelioLinkCreationContext context = ModelioLinkCreationContext.lookRequest(req);
        if (context != null) {
            if (Message.class == context.getJavaClass()) {
        
                CreateMessageCommand startCommand = (CreateMessageCommand) super.getConnectionCompleteCommand(req);
                int sourceTime = startCommand.getSourceTime();
                Point tmp = Point.SINGLETON;
                tmp.setLocation(req.getLocation().x, req.getLocation().y);
                getHostFigure().translateToRelative(tmp);
                int targetTime = tmp.y;
                // Do not allow a message to get back in time.
                if (targetTime < sourceTime) {
                    startCommand.setTargetTime(sourceTime);
                } else {
                    startCommand.setTargetTime(targetTime);
                }
                return startCommand;
            }
        }
        return super.getConnectionCompleteCommand(req);
    }

    @objid ("5bc4f5ea-f36f-4606-9fc4-61b877c2fbb9")
    @Override
    protected Command getConnectionCreateCommand(CreateConnectionRequest req) {
        ModelioLinkCreationContext context = ModelioLinkCreationContext.lookRequest(req);
        if (context != null) {
            if (Message.class == context.getJavaClass()) {
        
                CreateMessageCommand cmd = new CreateMessageCommand(context);
        
                cmd.setSource((GmNodeModel) getHost().getModel());
                req.setStartCommand(cmd);
                Point tmp = Point.SINGLETON;
                tmp.setLocation(req.getLocation().x, req.getLocation().y);
                getHostFigure().translateToRelative(tmp);
                cmd.setSourceTime(tmp.y);
        
                if (req instanceof CreateBendedConnectionRequest) {
                    cmd.setRequest((CreateBendedConnectionRequest) req);
                }
                return cmd;
            }
        }
        return super.getConnectionCreateCommand(req);
    }

    @objid ("22b375c5-7179-4e32-b8ff-e1181422e414")
    @Override
    protected Command getReconnectSourceCommand(ReconnectRequest req) {
        if (((GmModel) req.getConnectionEditPart().getModel()).getRelatedElement() instanceof Message) {
            return null;
        } else {
            return super.getReconnectSourceCommand(req);
        }
    }

    @objid ("467257ef-3e6b-4015-84d4-9a9494e272c9")
    @Override
    protected Command getReconnectTargetCommand(ReconnectRequest req) {
        if (((GmModel) req.getConnectionEditPart().getModel()).getRelatedElement() instanceof Message) {
            return null;
        } else {
            return super.getReconnectTargetCommand(req);
        }
    }

    @objid ("94d5eac9-b1ce-4a18-96ed-66dafddcd0f8")
    @Override
    protected void showCreationFeedback(CreateConnectionRequest request) {
        // Copy and hack the request to make sure the "destination" Y is never
        // less than the source. Copy is needed cause we don't want to modify
        // the original request (to avoid some nasty side effects)
        if (request instanceof CreateBendedConnectionRequest) {
            CreateBendedConnectionRequest request_origin = (CreateBendedConnectionRequest) request;
            CreateBendedConnectionRequest request_copy = new CreateBendedConnectionRequest();
            request_copy.setFactory((CreationFactory) request_origin.getNewObject()); // < hack: we know that we stored the factory as the new object.
            request_copy.setLocation(request_origin.getLocation().getCopy());
            request_copy.setSize(null);
            request_copy.setSourceEditPart(request_origin.getSourceEditPart());
            request_copy.setTargetEditPart(request_origin.getTargetEditPart());
            request_copy.setType(request_origin.getType());
            request_copy.getData().setRoutingMode(request_origin.getData().getRoutingMode());
            request_copy.getData().setSrcPoint(request_origin.getData().getSrcPoint());
            request_copy.getData().setLastPoint(request_origin.getData().getLastPoint());
        
            Command startCommand = request_origin.getStartCommand();
            request_copy.setStartCommand(startCommand);
            if (startCommand instanceof CreateMessageCommand) {
                // Fix the "Y" coordinate.
                Point tmp = Point.SINGLETON;
                tmp.y = ((CreateMessageCommand) startCommand).getSourceTime();
                ((GraphicalEditPart) request.getSourceEditPart()).getFigure().translateToAbsolute(tmp);
                request_copy.getLocation().y = Math.max(tmp.y, request_origin.getLocation().y);
            }
            super.showCreationFeedback(request_copy);
        } else {
            super.showCreationFeedback(request);
        }
    }

}
