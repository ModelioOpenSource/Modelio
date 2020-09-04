/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.diagram.editor.bpmn.elements.policies;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.helpers.palapi.PaletteActionProvider;
import org.modelio.diagram.elements.core.link.CreateBendedConnectionRequest;
import org.modelio.diagram.elements.core.link.ModelioLinkCreationContext;
import org.modelio.diagram.elements.core.link.createhandle.ICreationActionProvider;
import org.modelio.diagram.elements.core.link.createhandle.UserChoiceCreationCommand;
import org.modelio.diagram.elements.core.link.createhandle.UserChoiceLinkCreationFactory;
import org.modelio.diagram.elements.core.link.linknode.AbstractCreateLinkChooseNodeEditPolicy;
import org.modelio.diagram.elements.core.link.path.RawPathData;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.requests.CreateLinkConstants;
import org.modelio.metamodel.bpmn.events.BpmnEvent;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.objects.BpmnDataAssociation;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Create Link+Node policy for BPMN diagrams, supporting {@link BpmnSequenceFlow} and {@link BpmnDataAssociation} links.
 * 
 * The proposed node to be created are a selection of most common cases.
 * 
 * Moreover the policy supports {@link UserChoiceLinkCreationFactory} request.
 * In this case A node is created from the user chosen menu and a MessageFlow is created.
 */
@objid ("758c1ae2-0f9c-4262-9b8d-a1556c8403ff")
public class BpmnCreateLinkChooseNodeEditPolicy extends AbstractCreateLinkChooseNodeEditPolicy {
    @objid ("f946e952-e9ae-459c-ac4b-402682719d2e")
    private ICreationActionProvider actionProvider;

    /**
     * key of request copy in the original request data.
     */
    @objid ("a9d4d439-934c-4078-86b5-8173491a02a6")
    private static final Object CreateLinkChooseNodeRequest = "CreateLinkChooseNodeRequest";

    @objid ("fa51629b-defb-417f-81f5-663235b9260b")
    public BpmnCreateLinkChooseNodeEditPolicy() {
        super();
    }

    @objid ("2335898f-aebf-4d6e-b1a8-131e01670c19")
    @Override
    public ICreationActionProvider getActionProvider() {
        if (this.actionProvider == null) {
            this.actionProvider = new PaletteActionProvider(getHost(),
                    PaletteActionProvider.IS_NODE_TOOL.and(BpmnCreateLinkChooseNodeEditPolicy::accept));
        
        }
        return this.actionProvider;
    }

    /**
     * This policy implementation only accepts {@link BpmnSequenceFlow} and {@link BpmnDataAssociation} links.
     */
    @objid ("b460b0f5-46ef-4327-93ec-721341a8f9da")
    @Override
    public boolean isLinkSupported(ModelioLinkCreationContext linkCtx) {
        return linkCtx != null
                        && (BpmnSequenceFlow.class.isAssignableFrom(linkCtx.getJavaClass()) ||
                                BpmnDataAssociation.class.isAssignableFrom(linkCtx.getJavaClass()));
    }

    /**
     * Only node tools from the palette. For BpmnEvent only those without type.
     * @param entry a palette entry
     * @return true only for node tools without typed BpmnEvent ones.
     */
    @objid ("99896bce-85f2-4b75-8f51-fd5b79641ab6")
    private static boolean accept(PaletteEntry entry) {
        ModelioCreationContext nodeCtx = PaletteActionProvider.getNodeCreationContext(entry);
        Class<? extends MObject> mc = nodeCtx.getJavaClass();
        
        if (BpmnEvent.class.isAssignableFrom(mc)) {
            return "NONE".equals(nodeCtx.getProperties().getOrDefault("type", "NONE"));
        }
        return true;
    }

    @objid ("af72c430-6c99-418f-83b4-2f8cc03dd517")
    @Override
    public EditPart getTargetEditPart(Request request) {
        // This policy only supports its specific behavior: propose a node creation as the target of the new link.
        if (CreateLinkConstants.REQ_CONNECTION_CREATE_LINK_CHOOSENODE.equals(request.getType())) {
            if (isChooseLinkRequest(request)) {
                return getHost();
            }
            if (isLinkSupported(ModelioLinkCreationContext.lookRequest((CreateConnectionRequest) request))) {
                return getHost();
            }
        }
        return null;
    }

    /**
     * Tells whether the request is a choose link and choose node request.
     * <p>
     * It is a request having a {@link UserChoiceLinkCreationFactory} creation factory.
     * @param request a {@link CreateLinkConstants#REQ_CONNECTION_CREATE_LINK_CHOOSENODE} request
     * @return whether it is a "user choice" create link request.
     */
    @objid ("eb859d0a-4a7f-497e-b8c3-c18bea1584e9")
    protected static boolean isChooseLinkRequest(Request request) {
        return ((CreateConnectionRequest) request).getNewObjectType() == UserChoiceLinkCreationFactory.class;
    }

    @objid ("9350c898-6cb4-45ff-8b34-aba486347d4e")
    @Override
    protected Command getConnectionCreateLinkChooseNodeCommand(CreateConnectionRequest req) {
        if (CreateLinkConstants.REQ_CONNECTION_CREATE_LINK_CHOOSENODE.equals(req.getType()) && isChooseLinkRequest(req)) {
            ICreationActionProvider effectiveProvider = getEffectiveActionProvider(getActionProvider());
            UserChoiceCreationCommand cmd = new UserChoiceCreationCommand(getHost().getViewer(), effectiveProvider);
        
            CreateConnectionRequest copy = getCreateLinkChooseNodeRequest((CreateBendedConnectionRequest) req);
            cmd.update(copy);
            return cmd;
        
        } else {
            return super.getConnectionCreateLinkChooseNodeCommand(req);
        }
    }

    /**
     * Create an updated clone of the given request with a {@link ModelioLinkCreationContext} factory for {@link BpmnSequenceFlow}.
     * <p>
     * The clone is cached in the request extended data.
     * @param req a request
     * @return the cloned request.
     */
    @objid ("1380792a-38e1-4cb2-9a4b-89293ee4fb26")
    protected CreateBendedConnectionRequest getCreateLinkChooseNodeRequest(CreateBendedConnectionRequest req) {
        CreateBendedConnectionRequest copy = (CreateBendedConnectionRequest) req.getExtendedData().get(BpmnCreateLinkChooseNodeEditPolicy.CreateLinkChooseNodeRequest);
        
        GmModel gm = (GmModel) getHost().getModel();
        if (copy == null) {
            copy = new CreateBendedConnectionRequest();
            copy.setFactory(new ModelioLinkCreationContext(gm.getDiagram().getModelManager().getMetamodel().getMClass(BpmnSequenceFlow.class), null));
        }
        copy.setType(req.getType());
        copy.setExtendedData(req.getExtendedData());
        copy.setLocation(req.getLocation());
        copy.setSize(req.getSize());
        copy.setSnapToEnabled(req.isSnapToEnabled());
        copy.setSourceEditPart(req.getSourceEditPart());
        copy.setTargetEditPart(req.getTargetEditPart());
        
        RawPathData dataCopy = copy.getData();
        RawPathData reqData = req.getData();
        dataCopy.setSrcPoint(reqData.getSrcPoint());
        dataCopy.setLastPoint(reqData.getLastPoint());
        dataCopy.setRoutingMode(reqData.getRoutingMode());
        dataCopy.getPath().clear();
        dataCopy.getPath().addAll(reqData.getPath());
        
        if (copy.getStartCommand() == null && copy.getSourceEditPart() != null) {
            // Ask for a Message flow creation start command to the source edit part
            copy.setType(RequestConstants.REQ_CONNECTION_START);
            copy.setLocation(reqData.getSrcPoint());
            copy.setStartCommand(copy.getSourceEditPart().getCommand(copy));
        
            copy.setType(req.getType());
            copy.setLocation(req.getLocation());
        }
        return copy;
    }

}
