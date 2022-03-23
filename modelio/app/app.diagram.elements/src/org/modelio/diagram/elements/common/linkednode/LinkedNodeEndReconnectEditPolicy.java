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
package org.modelio.diagram.elements.common.linkednode;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.DropRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.modelio.diagram.elements.core.helpers.AnchorModelHelper;
import org.modelio.diagram.elements.core.link.DefaultReconnectTargetCommand;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.model.IGmLinkable;
import org.modelio.diagram.elements.core.requests.RequestProperty;

/**
 * Edit policy that allow moving the node link end.
 * <p>
 * To be put on node link edit parts.
 * 
 * @author cmarin
 */
@objid ("7ebb1d95-1dec-11e2-8cad-001ec947c8cc")
public class LinkedNodeEndReconnectEditPolicy extends AbstractLinkedNodeCreationEditPolicy {
    @objid ("7ebb1d97-1dec-11e2-8cad-001ec947c8cc")
    private static final Object HIGHLIGHTKEY = "target feedback";

    @objid ("7ebb1d99-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void eraseTargetConnectionFeedback(DropRequest request) {
        super.eraseTargetConnectionFeedback(request);
        
        // Additional feedback: outline the Node.
        final Request request2 = (Request) request;
        RectangleFigure highlight = (RectangleFigure) request2.getExtendedData().get(HIGHLIGHTKEY);
        if (highlight != null) {
            final IFigure feedbackLayer = getFeedbackLayer();
            if (highlight.getParent() == feedbackLayer) {
                feedbackLayer.remove(highlight);
                request2.getExtendedData().remove(HIGHLIGHTKEY);
            }
        }
        
    }

    @objid ("7ebb1d9f-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getConnectionCompleteCommand(CreateConnectionRequest request) {
        return null;
    }

    @objid ("7ebb1da9-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
        return null;
    }

    @objid ("7ebb1db3-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void showTargetConnectionFeedback(DropRequest request) {
        super.showTargetConnectionFeedback(request);
        /*
         * if (((Request) request).getType() != REQ_RECONNECT_TARGET) return;
         * 
         * final ReconnectRequest req = (ReconnectRequest) request;
         * 
         * // Additional feedback: highlight the node.
         * 
         */
        
    }

    @objid ("7ebb1db9-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public EditPart getTargetEditPart(Request request) {
        if (isHandled(request) || LinkedNodeRequestConstants.REQ_LINKEDNODE_END.equals(request.getType())) {
            return getHost();
        }
        return null;
    }

    @objid ("7ebb1dc3-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getReconnectTargetCommand(final ReconnectRequest request) {
        // Forbid changing the target of the link
        if (request.getTarget() != request.getConnectionEditPart().getTarget()) {
            return null;
        }
        
        GmLink gmLink = (GmLink) request.getConnectionEditPart().getModel();
        IGmLinkable newTarget = (IGmLinkable) getHost().getModel();
        
        DefaultReconnectTargetCommand cmd = new DefaultReconnectTargetCommand(gmLink, newTarget, RequestProperty.PROP_SKIP_MODELCHANGE.get(request));
        cmd.setAnchorModel(AnchorModelHelper.getTargetAnchorModel(request));
        return cmd;
    }

    @objid ("7ebb1dce-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected boolean isHandled(final Request request) {
        if (REQ_RECONNECT_TARGET.equals(request.getType())) {
            ReconnectRequest r = (ReconnectRequest) request;
            return r.getConnectionEditPart().getModel() instanceof IGmNodeLink;
        }
        return false;
    }

}
