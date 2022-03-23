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
package org.modelio.diagram.elements.core.link.linknode;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.XYAnchor;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.DropRequest;
import org.eclipse.jface.resource.ImageDescriptor;
import org.modelio.diagram.elements.core.commands.ILinkAndNodeCreationSupport;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.figures.FigureUtilities2;
import org.modelio.diagram.elements.core.link.ModelioLinkCreationContext;
import org.modelio.diagram.elements.core.link.createhandle.ICreationActionDescriptor;
import org.modelio.diagram.elements.core.link.createhandle.ICreationActionProvider;
import org.modelio.diagram.elements.core.link.createhandle.UserChoiceCreationCommand;
import org.modelio.diagram.elements.core.model.IGmLinkable;
import org.modelio.diagram.elements.core.requests.CreateLinkConstants;

/**
 * This abstract policy creates a link of a given kind and a target node for the link.
 * <p>
 * The type of the target node is chosen by the end user in a popup that appears when ending the link creation tool interaction
 * ( a right click).
 * <p>
 * Implementors have to provide:
 * <ul>
 * <li>the popup menu propositions as a {@link ICreationActionProvider} instance in {@link #getActionProvider()} .</li>
 * <li> {@link #isLinkSupported(ModelioLinkCreationContext)}
 * </ul>
 * 
 * 
 * The policy does not provide any control on what can be created and under which circumstances for the simple reason
 * that its effective behavior is provided by the {@link ICreationActionProvider} instances that are added to it. <br>
 * Implementors have to include the expected checks in the {@link ICreationActionProvider} implementation they provide.
 */
@objid ("96e8cbd1-74c5-44b4-a3eb-9840fae1ddd7")
public abstract class AbstractCreateLinkChooseNodeEditPolicy extends GraphicalEditPolicy {
    @objid ("8a738a48-edc8-4fe6-a910-9c907ddf244a")
    private IFigure highlight;

    @objid ("8014274a-ee22-45ab-86ef-5112222ffbb4")
    private final XYAnchor dummyAnchor = new XYAnchor(new Point(10, 10));

    /**
     * The role to use to register this edit policy.
     */
    @objid ("d25a89a3-88aa-4159-aea1-662c2a19ca9f")
    public static final Object ROLE = CreateLinkConstants.REQ_CONNECTION_CREATE_LINK_CHOOSENODE;

    /**
     * Get the action provider in charge of defining which node creation action are to be proposed to the end user.
     * @return the action provider to populate the node creation choice menu.
     */
    @objid ("717b5f30-80cf-439b-b613-727c1d5d6f4b")
    public abstract ICreationActionProvider getActionProvider();

    /**
     * Define whether or not a kind of link is handled by this policy.
     * @param linkCtx the link creation context , may be null
     * @return true if the policy handles the type of link defined in linkCtx. false otherwise. If false the policy won't provide any target edit part nor any command.
     */
    @objid ("e8d02767-622e-4ef0-bf36-7a16b84382f4")
    public abstract boolean isLinkSupported(ModelioLinkCreationContext linkCtx);

    /**
     * Calls {@link #getConnectionCreateLinkChooseNodeCommand(CreateConnectionRequest)} for {@link CreateLinkConstants#REQ_CONNECTION_CREATE_LINK_CHOOSENODE}
     * request.
     * <p>
     * @see  {@link #getConnectionCreateLinkChooseNodeCommand(CreateConnectionRequest)}
     */
    @objid ("c70c47e6-114c-46a9-9c73-841ba4ca95f0")
    @Override
    public Command getCommand(Request request) {
        if (CreateLinkConstants.REQ_CONNECTION_CREATE_LINK_CHOOSENODE.equals(request.getType())) {
            return getConnectionCreateLinkChooseNodeCommand((CreateConnectionRequest) request);
        }
        return null;
    }

    /**
     * Based on the exact type of request, delegate to a specialized private method.
     */
    @objid ("d90daeb1-1c3a-4f65-a86e-251f2a707e0b")
    @Override
    public EditPart getTargetEditPart(Request request) {
        // This policy only supports its specific behavior: propose a node creation as the target of the new link.
        if (CreateLinkConstants.REQ_CONNECTION_CREATE_LINK_CHOOSENODE.equals(request.getType())) {
            if (isLinkSupported(ModelioLinkCreationContext.lookRequest((CreateConnectionRequest)request))) {
                return getHost();
            }
        }
        return null;
    }

    @objid ("f2357cf9-135e-4752-b157-80d9305bc8db")
    @Override
    public void showTargetFeedback(final Request request) {
        if (CreateLinkConstants.REQ_CONNECTION_CREATE_LINK_CHOOSENODE.equals(request.getType())) {
            showTargetConnectionFeedback((DropRequest) request);
            showSourceConnectionFeedback((CreateConnectionRequest) request);
        } else if (CreateLinkConstants.REQ_CONNECTION_ADD_BENDPOINT.equals(request.getType())) {
            showTargetConnectionFeedback((DropRequest) request);
            showSourceConnectionFeedback((CreateConnectionRequest) request);
        }
        
    }

    @objid ("34608ad9-3210-4657-a77f-e84af389e866")
    @Override
    public void eraseTargetFeedback(Request request) {
        if (CreateLinkConstants.REQ_CONNECTION_CREATE_LINK_CHOOSENODE.equals(request.getType())
                || CreateLinkConstants.REQ_CONNECTION_ADD_BENDPOINT.equals(request.getType())) {
            eraseTargetConnectionFeedback();
            eraseSourceConnectionFeedback((CreateConnectionRequest) request);
        }
        
    }

    @objid ("30143e94-eb1e-4113-89cd-2042ff0aaf89")
    protected void eraseTargetConnectionFeedback() {
        // Additional feedback: outline the Node.
        if (this.highlight != null) {
            final IFigure feedbackLayer = getFeedbackLayer();
            feedbackLayer.remove(this.highlight);
            this.highlight = null;
        }
        
    }

    /**
     * Computes the command for a {@link CreateLinkConstants#REQ_CONNECTION_CREATE_LINK_CHOOSENODE} request.
     * @param req a {@link CreateLinkConstants#REQ_CONNECTION_CREATE_LINK_CHOOSENODE} request.
     * @return the command or null.
     */
    @objid ("f35727ce-a658-4f7b-af4b-71d444aaf2e6")
    protected Command getConnectionCreateLinkChooseNodeCommand(CreateConnectionRequest req) {
        final ModelioLinkCreationContext context = ModelioLinkCreationContext.lookRequest(req);
        
        // Only handle link model element creation requests.
        if (context == null) {
            return null;
        }
        
        if (context.getElementToUnmask() == null) {
            ICreationActionProvider effectiveProvider = getEffectiveActionProvider(getActionProvider());
        
            UserChoiceCreationCommand cmd = new UserChoiceCreationCommand(getHost().getViewer(), effectiveProvider);
            cmd.update(req);
            return cmd;
        
        }
        return null;
    }

    @objid ("c7ee30fb-3af1-404e-900a-7a6220be1a81")
    protected void showTargetConnectionFeedback(DropRequest request) {
        // Additional feedback: highlight the node.
        // compute highlight type
        final Command c = getHost().getCommand((Request) request);
        FigureUtilities2.HighlightType hightlightType = FigureUtilities2.HighlightType.INFO;
        if (c == null) {
            hightlightType = FigureUtilities2.HighlightType.ERROR;
        } else if (c.canExecute()) {
            hightlightType = FigureUtilities2.HighlightType.SUCCESS;
        } else {
            hightlightType = FigureUtilities2.HighlightType.WARNING;
        }
        
        // create a highlight figure if it does not exist
        if (this.highlight == null) {
            // create a highlight figure
            this.highlight = FigureUtilities2.createHighlightFigure(getFeedbackLayer(), getHostFigure(), hightlightType);
            this.highlight.setOpaque(false);
            this.highlight.setBackgroundColor(null);
            // add the highlight figure to the feedback layer
            getFeedbackLayer().add(this.highlight);
        } else {
            // configure the highlight figure
            FigureUtilities2.updateHighlightType(this.highlight, hightlightType);
            this.highlight.setOpaque(false);
            this.highlight.setBackgroundColor(null);
        }
        
    }

    /**
     * Action provider that : <ul>
     * <li> filters the given one selecting only node creation actions
     * <li> and chains them with a link creation action
     * </ul>
     * @param actionProvider a creation actions provider
     * @return the final provider.
     */
    @objid ("2e2af8f2-c06d-4dd7-a0fe-3f6d1be567f1")
    protected ICreationActionProvider getEffectiveActionProvider(ICreationActionProvider actionProvider) {
        return req -> actionProvider.getPaletteActions(req)
                        .filter(action1 -> action1.getCommand() instanceof ILinkAndNodeCreationSupport)
                        .map(action2 -> new NodeAndLinkCreationActionDescriptor(action2, req));
    }

    /**
     * Asks the source edit part to show feedback of a normal REQ_CONNECTION_END request.
     * @param request a {@link CreateLinkConstants#REQ_CONNECTION_CREATE_LINK_CHOOSENODE} request
     */
    @objid ("1968b934-f4a4-40d1-a028-8c5d0c6c6a76")
    private void showSourceConnectionFeedback(CreateConnectionRequest request) {
        Object curType = request.getType();
        request.setType(REQ_CONNECTION_END);
        request.getSourceEditPart().showSourceFeedback(request);
        request.setType(curType);
        
    }

    /**
     * Asks the source edit part to remove feedback of a normal REQ_CONNECTION_END request.
     * @param request a {@link CreateLinkConstants#REQ_CONNECTION_CREATE_LINK_CHOOSENODE} request
     */
    @objid ("2e9438fb-b9c3-485b-a91b-c6e46c66bdcb")
    private void eraseSourceConnectionFeedback(CreateConnectionRequest request) {
        Object curType = request.getType();
        request.setType(REQ_CONNECTION_END);
        request.getSourceEditPart().eraseSourceFeedback(request);
        request.setType(curType);
        
    }

    /**
     * Creation action descriptor that chains another descriptor command with a {@link CreateLinkAndNodeCommand}.
     */
    @objid ("bdedbe6c-1eeb-4077-b8b4-bb087a6a3d69")
    private static final class NodeAndLinkCreationActionDescriptor implements ICreationActionDescriptor {
        @objid ("abfcc783-d9a8-4fa5-8732-a697df9769b6")
        private final ICreationActionDescriptor createNodeAction;

        @objid ("9f205ab3-e665-4b7c-a056-6aed4d68ec7a")
        private final CreateConnectionRequest createConnReq;

        @objid ("08fa6fc6-70c5-460f-8342-4279e4999d1b")
        public  NodeAndLinkCreationActionDescriptor(ICreationActionDescriptor createNodeAction, CreateConnectionRequest createConnReq) {
            this.createNodeAction = createNodeAction;
            this.createConnReq = createConnReq;
            
        }

        @objid ("0cd315b8-e069-4df0-b646-acd0f856daa2")
        @Override
        public CreateRequest getRequest() {
            return this.createConnReq;
        }

        @objid ("97d1ba94-9988-4bd2-8d3f-a7eac8e0b271")
        @Override
        public String getLabel() {
            return this.createNodeAction.getLabel();
        }

        @objid ("39b7b611-1f80-4f47-b57f-00b3bfe15a35")
        @Override
        public ImageDescriptor getIcon() {
            return this.createNodeAction.getIcon();
        }

        @objid ("530d8740-8ec6-4c37-952f-99044fb4597d")
        @Override
        public Command getCommand() {
            IGmLinkable sourceNode = ((IGmLinkable) this.createConnReq.getSourceEditPart().getModel());
            
            ModelioCreationContext nodeCtx = ModelioCreationContext.lookRequest(this.createNodeAction.getRequest());
            return new CreateLinkAndNodeCommand(nodeCtx, this.createNodeAction.getCommand(), this.createConnReq, sourceNode);
        }

        @objid ("5f2f19e7-a1d7-4b59-bee4-045b6299865d")
        @Override
        public void execute(EditPartViewer viewer) {
            viewer.getEditDomain().getCommandStack().execute(getCommand());
        }

    }

}
