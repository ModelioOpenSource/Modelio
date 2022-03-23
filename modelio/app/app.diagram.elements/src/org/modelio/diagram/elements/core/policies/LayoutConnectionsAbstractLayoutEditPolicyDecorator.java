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
@objid ("9872cbf9-dd7f-4d7c-8797-7488a1a25329")
public abstract class LayoutConnectionsAbstractLayoutEditPolicyDecorator extends AbstractEditPolicy {
    @objid ("2524f0fa-b618-4933-a7b8-021959371d21")
    private final EditPolicy decorated;

    /**
     * @param decorated the initial layout edit policy.
     */
    @objid ("5dc2b416-8162-4371-b18d-16578d994d81")
    protected  LayoutConnectionsAbstractLayoutEditPolicyDecorator(LayoutEditPolicy decorated) {
        this.decorated = decorated;
    }

    @objid ("8439d3d3-3e4c-418c-b61c-28305d8458c7")
    @Override
    public void activate() {
        this.decorated.activate();
    }

    @objid ("709c5ec1-4f21-42ee-bd6c-271e6bcf92fd")
    @Override
    public void deactivate() {
        this.decorated.deactivate();
    }

    @objid ("8e1133ad-0b91-499c-a1f6-dc92f1e30131")
    @Override
    public void eraseSourceFeedback(Request request) {
        this.decorated.eraseSourceFeedback(request);
    }

    @objid ("8e02152d-0592-406f-82ab-d008a048d62e")
    @Override
    public void eraseTargetFeedback(Request request) {
        this.decorated.eraseTargetFeedback(request);
    }

    /**
     * Factors incoming requests into various specific methods.
     * @see org.eclipse.gef.EditPolicy#getCommand(Request)
     */
    @objid ("1a477fcd-b2d9-4d82-82a8-a7f8c69f3a52")
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

    @objid ("d769b2db-ce84-4250-b6e3-bac62f6e76e0")
    @Override
    public GraphicalEditPart getHost() {
        return (GraphicalEditPart) super.getHost();
    }

    /**
     * Forwards call to the decorated policy.
     * @see org.eclipse.gef.EditPolicy#getTargetEditPart(Request)
     */
    @objid ("b7d387f3-40ed-42cb-b3be-4064eef0105c")
    @Override
    public EditPart getTargetEditPart(Request request) {
        return this.decorated.getTargetEditPart(request);
    }

    @objid ("5bbecab5-ad86-42c2-9c70-a0394fc76c1a")
    @Override
    public void setHost(EditPart host) {
        super.setHost(host);
        this.decorated.setHost(host);
        
    }

    @objid ("c8d7b712-0e3e-4529-960a-28b7f8d5fb74")
    @Override
    public void showSourceFeedback(Request request) {
        this.decorated.showSourceFeedback(request);
    }

    /**
     * Factors feedback requests into two more specific methods.
     * @see org.eclipse.gef.EditPolicy#showTargetFeedback(Request)
     */
    @objid ("5908815b-ec78-4214-bb99-ae4824c9344f")
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

    @objid ("476abe3f-9eca-4139-bc9e-94c8885bbf3f")
    @Override
    public boolean understandsRequest(Request req) {
        return this.decorated.understandsRequest(req);
    }

    /**
     * Add <code>Commands</code> to perform a add.
     * @param command the compound command where to add new commands
     * @param request the CreateRequest
     */
    @objid ("d2de53dd-b9ab-4e83-b6ce-5ea8bf29c5ed")
    protected abstract void addAddCommand(CompoundCommand command, Request request);

    /**
     * Add <code>Commands</code> to perform a clone.
     * @param command the compound command where to add new commands
     * @param request the clone ChangeBoundsRequest
     */
    @objid ("787e8909-1d76-4df5-88fb-fe74b829b223")
    protected abstract void addCloneCommand(CompoundCommand command, ChangeBoundsRequest request);

    /**
     * Add <code>Commands</code> to perform a create.
     * @param command the compound command where to add new commands
     * @param request the CreateRequest
     */
    @objid ("4fc0c9c5-6e11-4563-b303-731803c67ad0")
    protected abstract void addCreateCommand(CompoundCommand command, CreateRequest request);

    /**
     * Add <code>Commands</code> to delete a child.
     * <p>
     * This method does not get called unless the child forwards an additional request to the
     * container editpart.
     * @param command the compound command where to add new commands
     * @param request the Request
     */
    @objid ("01dca2bd-b7e1-4b37-aa68-07ad948d1d07")
    protected abstract void addDeleteDependantCommand(CompoundCommand command, GroupRequest request);

    /**
     * Add <code>Commands</code> to move and/or resize a group of children.
     * @param command the compound command where to add new commands
     * @param request the ChangeBoundsRequest
     */
    @objid ("abdd6274-0cdb-4603-be04-82c6c4fe81ab")
    protected abstract void addMoveChildrenCommand(CompoundCommand command, ChangeBoundsRequest request);

    /**
     * Add the <code>Command</code> to orphan a group of children.
     * @param command the compound command where to add new commands
     * @param request the Request
     */
    @objid ("e4926393-f9c2-45d9-9564-5444bc64ff53")
    protected abstract void addOrphanChildrenCommand(CompoundCommand command, GroupRequest request);

    /**
     * Returns the host's {@link GraphicalEditPart#getContentPane() contentPane}.
     * <p>
     * The contentPane is the Figure which parents the childrens' figures. It
     * is also the figure which has the LayoutManager that corresponds to this
     * EditPolicy. All operations should be interpreted with respect to this figure.
     * @return the Figure that owns the corresponding <code>LayoutManager</code>
     */
    @objid ("8b3ee674-b5b2-4ca9-9edd-2741275a1c5a")
    protected IFigure getLayoutContainer() {
        return getHost().getContentPane();
    }

}
