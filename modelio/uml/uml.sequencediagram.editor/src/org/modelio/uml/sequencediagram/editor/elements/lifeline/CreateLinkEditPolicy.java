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
package org.modelio.uml.sequencediagram.editor.elements.lifeline;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.modelio.diagram.elements.core.link.CreateBendedConnectionRequest;
import org.modelio.diagram.elements.core.link.DefaultCreateLinkEditPolicy;
import org.modelio.diagram.elements.core.link.ModelioLinkCreationContext;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.metamodel.uml.behavior.interactionModel.Message;
import org.modelio.uml.sequencediagram.editor.elements.message.CreateMessageCommand;

/**
 * Edit policy that allow creating links from/to Lifeline.
 */
@objid ("d9376a67-55b6-11e2-877f-002564c97630")
public class CreateLinkEditPolicy extends DefaultCreateLinkEditPolicy {
    @objid ("88b02951-b0fd-4c71-8e29-afd7841a4697")
    private static final Point TMP = new Point();

    @objid ("d9376a6b-55b6-11e2-877f-002564c97630")
    @Override
    protected Command getConnectionCompleteCommand(CreateConnectionRequest req) {
        Command completeCommand = super.getConnectionCompleteCommand(req);
        if (completeCommand instanceof CreateMessageCommand) {
            CreateMessageCommand startCommand = (CreateMessageCommand) completeCommand;
            int sourceTime = startCommand.getSourceTime();
            TMP.setLocation(req.getLocation());
            getHostFigure().translateToRelative(TMP);
        
            // Do not allow a message to get back in time.
            if (TMP.y < sourceTime) {
                startCommand.setTargetTime(sourceTime);
            } else {
                startCommand.setTargetTime(TMP.y);
            }
        }
        return completeCommand;
    }

    @objid ("d9376a71-55b6-11e2-877f-002564c97630")
    @Override
    protected Command getConnectionCreateCommand(CreateConnectionRequest req) {
        ModelioLinkCreationContext context = ModelioLinkCreationContext.lookRequest(req);
        if (context != null) {
            if (Message.class == context.getJavaClass()) {
                CreateMessageCommand cmd = new CreateMessageCommand(context);
        
                cmd.setSource((GmNodeModel) getHost().getModel());
                req.setStartCommand(cmd);
                
                TMP.setLocation(req.getLocation());
                getHostFigure().translateToRelative(TMP);
                cmd.setSourceTime(TMP.y);
        
                if (req instanceof CreateBendedConnectionRequest) {
                    cmd.setRequest((CreateBendedConnectionRequest) req);
                }
                return cmd;
            }
        }
        return super.getConnectionCreateCommand(req);
    }

    @objid ("d9376a77-55b6-11e2-877f-002564c97630")
    @Override
    protected Command getReconnectSourceCommand(ReconnectRequest req) {
        if (((GmModel) req.getConnectionEditPart().getModel()).getRelatedElement() instanceof Message) {
            return null;
        } else {
            return super.getReconnectSourceCommand(req);
        }
        
    }

    @objid ("d9376a7c-55b6-11e2-877f-002564c97630")
    @Override
    protected Command getReconnectTargetCommand(ReconnectRequest req) {
        if (((GmModel) req.getConnectionEditPart().getModel()).getRelatedElement() instanceof Message) {
            return null;
        } else {
            return super.getReconnectTargetCommand(req);
        }
        
    }

    @objid ("d9376a81-55b6-11e2-877f-002564c97630")
    @Override
    protected void showCreationFeedback(CreateConnectionRequest request) {
        if (request instanceof CreateBendedConnectionRequest) {
            final ModelioLinkCreationContext context = ModelioLinkCreationContext.lookRequest(request);
            if (context != null) {
                // Copy and hack the request to make sure the "destination" Y is never
                // less than the source. Copy is needed cause we don't want to modify
                // the original request (to avoid some nasty side effects)
        
                CreateBendedConnectionRequest request_origin = (CreateBendedConnectionRequest) request;
                CreateBendedConnectionRequest request_copy = createCopy(context, request_origin);
                Command startCommand = request_origin.getStartCommand();
        
                if (startCommand instanceof CreateMessageCommand) {
                    // Fix the "Y" coordinate.
                    TMP.x = 0;
                    TMP.y = ((CreateMessageCommand) startCommand).getSourceTime();
                    ((GraphicalEditPart) request.getSourceEditPart()).getFigure().translateToAbsolute(TMP);
                    request_copy.getLocation().y = Math.max(TMP.y, request_origin.getLocation().y);
                }
                super.showCreationFeedback(request_copy);
            } else {
                super.showCreationFeedback(request);
            }
        } else {
            super.showCreationFeedback(request);
        }
        
    }

    @objid ("f4e10d72-eb70-4d60-9c19-513546e4bd9f")
    protected CreateBendedConnectionRequest createCopy(final ModelioLinkCreationContext context, CreateBendedConnectionRequest request_origin) {
        CreateBendedConnectionRequest rcopy = new CreateBendedConnectionRequest();
        rcopy.setFactory(context);
        rcopy.setLocation(request_origin.getLocation().getCopy());
        rcopy.setSize(null);
        rcopy.setSourceEditPart(request_origin.getSourceEditPart());
        rcopy.setTargetEditPart(request_origin.getTargetEditPart());
        rcopy.setType(request_origin.getType());
        rcopy.getData().setRoutingMode(request_origin.getData().getRoutingMode());
        rcopy.getData().setSrcPoint(request_origin.getData().getSrcPoint());
        rcopy.getData().setLastPoint(request_origin.getData().getLastPoint());
        Command startCommand = request_origin.getStartCommand();
        rcopy.setStartCommand(startCommand);
        return rcopy;
    }

}
