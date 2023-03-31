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
package org.modelio.diagram.elements.core.policies;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editpolicies.AbstractEditPolicy;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.OrderedLayoutEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.GroupRequest;
import org.modelio.diagram.elements.core.helpers.RequestHelper;
import org.modelio.diagram.elements.core.requests.RequestTypes;
import org.modelio.diagram.elements.plugin.DiagramElements;

/**
 * Decorator for your {@link OrderedLayoutEditPolicy} that asks connections to update their layout
 * when editing the container members.
 * <p>
 * <h2>Usage</h2>
 * This policy must be installed with {@link EditPolicy#LAYOUT_ROLE}.
 * Pass to the constructor the real {@link OrderedLayoutEditPolicy layout policy}
 * <p>
 * With this policy there is no need to use {@link LayoutNodeConnectionsEditPolicy} on the child edit parts.
 * @author cma
 * @see LayoutEditPolicy
 * @since 5.1.0
 */
@objid ("166f7c73-f97d-4e44-93f6-9c90bd4f44de")
public abstract class LayoutConnectionsAbstractLayoutEditPolicyDecorator extends AbstractEditPolicy {
    @objid ("de3ff6c7-608b-4f68-b6b0-757940a71610")
    private final EditPolicy decorated;

    /**
     * @param decorated the initial layout edit policy.
     */
    @objid ("6734377f-734d-4356-9f68-5636690f64bd")
    protected  LayoutConnectionsAbstractLayoutEditPolicyDecorator(LayoutEditPolicy decorated) {
        this.decorated = decorated;
    }

    @objid ("27e42272-a6e5-4184-a8f8-2785c381bf85")
    @Override
    public void activate() {
        this.decorated.activate();
    }

    @objid ("0923beda-a994-420c-b483-23a849cde5fa")
    @Override
    public void deactivate() {
        this.decorated.deactivate();
    }

    @objid ("8e21640d-321e-4413-9597-723720c24793")
    @Override
    public void eraseSourceFeedback(Request request) {
        this.decorated.eraseSourceFeedback(request);
    }

    @objid ("b972df30-6c2c-4e3b-8214-58dd2e9e04eb")
    @Override
    public void eraseTargetFeedback(Request request) {
        this.decorated.eraseTargetFeedback(request);
    }

    /**
     * Factors incoming requests into various specific methods.
     * @see org.eclipse.gef.EditPolicy#getCommand(Request)
     */
    @objid ("9e08bce9-6039-4d42-999d-0535156ac3cc")
    @Override
    public Command getCommand(Request request) {
        Command mainCommand = this.decorated.getCommand(request);
        Object type = request.getType();
        
        if (! (type instanceof String))
            return mainCommand;
        
        // Fast exit if null or not possible
        if (mainCommand==null) {
            // REQ_DELETING_CHILDREN is an allowed exception
            if (! type.equals(RequestTypes.REQ_DELETING_CHILDREN)) {
                return mainCommand;
            }
        } else if (! mainCommand.canExecute()) {
            return mainCommand;
        }
        
        CompoundCommand command = new CompoundCommand();
        command.add(mainCommand);
        
        switch ((String)type) {
        case REQ_DELETE_DEPENDANT:
            addDeleteDependantCommand(command, (GroupRequest) request);
            break;
        case REQ_ADD:
            addAddCommand(command, request);
            break;
        case REQ_ORPHAN_CHILDREN:
        case RequestTypes.REQ_DELETING_CHILDREN:
            addOrphanChildrenCommand(command, (GroupRequest) request);
            break;
        case REQ_MOVE_CHILDREN:
        case REQ_RESIZE_CHILDREN:
        case REQ_ALIGN_CHILDREN:
            addMoveChildrenCommand(command, (ChangeBoundsRequest) request);
            break;
        case REQ_CLONE:
            addCloneCommand(command, (ChangeBoundsRequest) request);
            break;
        case REQ_CREATE:
            addCreateCommand(command, (CreateRequest) request);
            break;
        default:
            if (DiagramElements.LOG.isDebugEnabled()) {
                DiagramElements.LOG.debug(new UnsupportedOperationException(String.format(
                        "Not decorated request type: %s",
                        RequestHelper.toString(request))));
            }
            break;
        }
        
        if (command.isEmpty())
            return null;
        return command.unwrap();
    }

    @objid ("453a472b-ecb8-4fef-847f-d4d29aec7df0")
    @Override
    public GraphicalEditPart getHost() {
        return (GraphicalEditPart) super.getHost();
    }

    /**
     * Forwards call to the decorated policy.
     * @see org.eclipse.gef.EditPolicy#getTargetEditPart(Request)
     */
    @objid ("06ca0967-fc46-4397-965f-6aa7971d5321")
    @Override
    public EditPart getTargetEditPart(Request request) {
        return this.decorated.getTargetEditPart(request);
    }

    @objid ("25af0780-6c51-4219-b064-d59ec63fd3fb")
    @Override
    public void setHost(EditPart host) {
        super.setHost(host);
        this.decorated.setHost(host);
        
    }

    @objid ("766e202c-8408-4d0d-9517-d5dedf4d0a89")
    @Override
    public void showSourceFeedback(Request request) {
        this.decorated.showSourceFeedback(request);
    }

    /**
     * Factors feedback requests into two more specific methods.
     * @see org.eclipse.gef.EditPolicy#showTargetFeedback(Request)
     */
    @objid ("35e01809-a8d4-41c2-a45b-def8cf177e47")
    @Override
    public void showTargetFeedback(Request request) {
        this.decorated.showTargetFeedback(request);
        // to be uncommented and implemented if some decorated policies decide
        // to modify their figure or children one to show feedback.
        /*
        if (REQ_ADD.equals(request.getType())
                || REQ_CLONE.equals(request.getType())
                || REQ_MOVE.equals(request.getType())
                || REQ_RESIZE_CHILDREN.equals(request.getType())
                || REQ_CREATE.equals(request.getType()))
            showLayoutTargetFeedback(request);
        
        if (REQ_CREATE.equals(request.getType())) {
            CreateRequest createReq = (CreateRequest) request;
            if (createReq.getSize() != null) {
                showSizeOnDropFeedback(createReq);
            }
        }
        */
        
    }

    @objid ("abd02dc4-8c8e-43ad-b0cc-a52eea62c3bb")
    @Override
    public boolean understandsRequest(Request req) {
        return this.decorated.understandsRequest(req);
    }

    /**
     * Add <code>Commands</code> to perform a add.
     * @param command the compound command where to add new commands
     * @param request the CreateRequest
     */
    @objid ("6a2d29bf-7b4d-4fb1-aa19-38e3fe53b489")
    protected abstract void addAddCommand(CompoundCommand command, Request request);

    /**
     * Add <code>Commands</code> to perform a clone.
     * @param command the compound command where to add new commands
     * @param request the clone ChangeBoundsRequest
     */
    @objid ("f85f4153-651f-4462-bae5-b801e98b06ce")
    protected abstract void addCloneCommand(CompoundCommand command, ChangeBoundsRequest request);

    /**
     * Add <code>Commands</code> to perform a create.
     * @param command the compound command where to add new commands
     * @param request the CreateRequest
     */
    @objid ("26f3c523-ad6f-4568-8abd-88a351d6a368")
    protected abstract void addCreateCommand(CompoundCommand command, CreateRequest request);

    /**
     * Add <code>Commands</code> to delete a child.
     * <p>
     * This method does not get called unless the child forwards an additional request to the
     * container editpart.
     * @param command the compound command where to add new commands
     * @param request the Request
     */
    @objid ("af868612-26f3-4f9e-bc4e-aa2d24538ec0")
    protected abstract void addDeleteDependantCommand(CompoundCommand command, GroupRequest request);

    /**
     * Add <code>Commands</code> to move and/or resize a group of children.
     * @param command the compound command where to add new commands
     * @param request the ChangeBoundsRequest
     */
    @objid ("f0d375a5-4953-4fc7-9c37-948b0aec1b04")
    protected abstract void addMoveChildrenCommand(CompoundCommand command, ChangeBoundsRequest request);

    /**
     * Add the <code>Command</code> to orphan a group of children.
     * @param command the compound command where to add new commands
     * @param request the Request
     */
    @objid ("00fa9d7a-24d8-4a86-b57f-fea5b24f9caa")
    protected abstract void addOrphanChildrenCommand(CompoundCommand command, GroupRequest request);

    /**
     * Returns the host's {@link GraphicalEditPart#getContentPane() contentPane}.
     * <p>
     * The contentPane is the Figure which parents the childrens' figures. It
     * is also the figure which has the LayoutManager that corresponds to this
     * EditPolicy. All operations should be interpreted with respect to this figure.
     * @return the Figure that owns the corresponding <code>LayoutManager</code>
     */
    @objid ("917149f6-8e18-4616-98c4-6eebaf485119")
    protected IFigure getLayoutContainer() {
        return getHost().getContentPane();
    }

}
