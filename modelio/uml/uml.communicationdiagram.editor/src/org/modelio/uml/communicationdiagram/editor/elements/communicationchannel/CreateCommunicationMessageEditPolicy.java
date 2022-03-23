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
package org.modelio.uml.communicationdiagram.editor.elements.communicationchannel;

import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartListener;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.DropRequest;
import org.modelio.diagram.elements.common.group.GmGroup;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.policies.DeferringCreateNodePolicy;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationMessage;
import org.modelio.uml.communicationdiagram.editor.elements.communicationmessage.GmCommunicationInvertedMessageGroup;
import org.modelio.uml.communicationdiagram.editor.elements.communicationmessage.GmCommunicationSentMessageGroup;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Allow creation of communication messages on the link.
 * <p>
 * Defers the creation to the {@link GmCommunicationSentMessageGroup}.
 */
@objid ("7a1eeec9-55b6-11e2-877f-002564c97630")
public class CreateCommunicationMessageEditPolicy extends DeferringCreateNodePolicy {
    /**
     * Redefined to return the {@link GmGroup} for the nearest association role from the mouse.
     * @param gmLink The association model
     * @param location The mouse location
     * @return The nearest {@link GmGroup} from the mouse.
     */
    @objid ("7a1eeecd-55b6-11e2-877f-002564c97630")
    protected GmCompositeNode getExtensionFor(final GmLink gmLink, final Point location) {
        Connection fig = (Connection) getHostFigure();
        
        PointList points = fig.getPoints();
        Point begin = points.getFirstPoint();
        Point last = points.getLastPoint();
        
        int d1 = Math.abs(begin.getDistance2(location));
        int d2 = Math.abs(last.getDistance2(location));
        
        GmCompositeNode gmTargetChild = null;
        if (d1 > d2) {
            // source side
            for (GmNodeModel n : gmLink.getExtensions()) {
                if (n instanceof GmCommunicationInvertedMessageGroup) {
                    gmTargetChild = (GmCompositeNode) n;
                    break;
                }
            }
        } else {
            // target side
            for (GmNodeModel n : gmLink.getExtensions()) {
                if (n instanceof GmCommunicationSentMessageGroup) {
                    gmTargetChild = (GmCompositeNode) n;
                    break;
                }
            }
        }
        return gmTargetChild;
    }

    @objid ("7a20755d-55b6-11e2-877f-002564c97630")
    @Override
    protected Command getAddCommand(final Request request) {
        if (getTargetEditPart(request) == null) {
            return null;
        }
        return new DeferredCommand(request, getHost());
    }

    @objid ("7a207564-55b6-11e2-877f-002564c97630")
    @Override
    protected Command getCloneCommand(final ChangeBoundsRequest request) {
        if (getTargetEditPart(request) == null) {
            return null;
        }
        return new DeferredCommand(request, getHost());
    }

    @objid ("7a20756b-55b6-11e2-877f-002564c97630")
    @Override
    protected Command getCreateCommand(final CreateRequest createReq) {
        if (getTargetEditPart(createReq) == null) {
            return null;
        }
        return new DeferredCommand(createReq, getHost());
    }

    @objid ("7a207572-55b6-11e2-877f-002564c97630")
    @Override
    protected EditPart getEditPartFor(final Class<? extends MObject> metaclass, final Point location) {
        if (metaclass != CommunicationMessage.class) {
            return null;
        }
        
        final GmLink gmLink = (GmLink) getHost().getModel();
        final GmCompositeNode gmTargetChild = getExtensionFor(gmLink, location);
        
        if (gmTargetChild == null) {
            return null;
        }
        
        if (!gmTargetChild.isVisible()) {
            return getHost();
        }
        
        final EditPart p = (EditPart) getHost().getRoot()
                .getViewer()
                .getEditPartRegistry()
                .get(gmTargetChild);
        return p;
    }

    /**
     * This edit policy must not decorate new children because {@link org.modelio.diagram.elements.core.link.GmLinkLayoutEditPolicy GmLinkLayoutEditPolicy} already does it.
     */
    @objid ("7a20757d-55b6-11e2-877f-002564c97630")
    @Override
    protected EditPartListener createListener() {
        return null;
    }

    /**
     * This edit policy must not decorate children because {@link org.modelio.diagram.elements.core.link.GmLinkLayoutEditPolicy GmLinkLayoutEditPolicy} already does it.
     */
    @objid ("7a207583-55b6-11e2-877f-002564c97630")
    @Override
    protected void decorateChildren() {
        return;
    }

    @objid ("7a207588-55b6-11e2-877f-002564c97630")
    class DeferredCommand extends Command {
        @objid ("7a20758d-55b6-11e2-877f-002564c97630")
        private Map<?, ?> editPartRegistry;

        @objid ("9cbf1079-55c1-11e2-9337-002564c97630")
        private GmLink gmLink;

        @objid ("3a2f4619-ac4a-4809-aee5-f65c2dd87145")
        private Request req;

        /**
         * Create a deferred command.
         * @param req The creation request.
         * @param sender The edit part sending the request
         */
        @objid ("7a207591-55b6-11e2-877f-002564c97630")
        public  DeferredCommand(final Request req, final EditPart sender) {
            this.req = req;
            this.gmLink = (GmLink) sender.getModel();
            this.editPartRegistry = sender.getViewer().getEditPartRegistry();
            
        }

        @objid ("7a207598-55b6-11e2-877f-002564c97630")
        @Override
        public boolean canExecute() {
            Command cmd = createCommand();
            return (cmd != null && cmd.canExecute());
        }

        @objid ("7a21fbfa-55b6-11e2-877f-002564c97630")
        @Override
        public void execute() {
            Command cmd = createCommand();
            if (cmd != null && cmd.canExecute()) {
                cmd.execute();
            }
            
        }

        @objid ("7a21fbfd-55b6-11e2-877f-002564c97630")
        private Command createCommand() {
            final GmCompositeNode gmTarget = getExtensionFor(this.gmLink,
                    ((DropRequest) this.req).getLocation());
            
            if (gmTarget == null) {
                return null;
            }
            
            if (!gmTarget.isVisible()) {
                gmTarget.setVisible(true);
            }
            
            final EditPart p = (EditPart) this.editPartRegistry.get(gmTarget);
            if (p == null) {
                return null;
            }
            
            final EditPart targetPart = p.getTargetEditPart(this.req);
            if (targetPart != null) {
                return targetPart.getCommand(this.req);
            } else {
                return null;
            }
            
        }

    }

}
