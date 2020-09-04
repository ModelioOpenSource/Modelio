/* 
 * Copyright 2013-2019 Modeliosoft
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
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.DropRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.modelio.api.module.mda.IMdaExpert;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.figures.FigureUtilities2;
import org.modelio.diagram.elements.core.helpers.AnchorModelHelper;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.umlcommon.diagramview.CreateDiagramViewCommand;
import org.modelio.diagram.elements.umlcommon.externdocument.CreateExternDocumentCommand;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.Document;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MExpert;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Edit policy that allow to create a node linked to this node.
 * 
 * @author cmarin
 */
@objid ("7ebfe270-1dec-11e2-8cad-001ec947c8cc")
public class LinkedNodeStartCreationEditPolicy extends AbstractLinkedNodeCreationEditPolicy {
    @objid ("7ebfe272-1dec-11e2-8cad-001ec947c8cc")
    private static final Object HIGHLIGHTKEY = "feedback source";

    /**
     * Returns the <i>host</i> for the appropriate <code>Requests</code>. Returns <code>null</code> otherwise.
     * @see org.eclipse.gef.EditPolicy#getTargetEditPart(Request)
     */
    @objid ("7ebfe274-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public EditPart getTargetEditPart(Request request) {
        if (LinkedNodeRequestConstants.REQ_LINKEDNODE_START.equals(request.getType())) {
            ModelioCreationContext context = ModelioCreationContext.lookRequest((CreateConnectionRequest) request);
            if (context == null) {
                return null;
            }
        
            Stereotype linkStereotype = context.getStereotype();
            MClass linkMetaclass = context.getMetaclass();
            String depName = context.getDependencyName();
            GmModel gmModel = (GmModel) getHost().getModel();
            MObject sourceElement = gmModel.getRelatedElement();
            IMdaExpert mdaExpert = gmModel.getDiagram().getModelManager().getMdaExpert();
        
            // If source element cannot be found, or if creation expert doesn't allow AND this instance is not "opaque" (see javadoc
            // on private attribute isOpaque for details), return null.
            if (sourceElement == null) {
                return null;
            }
        
            MExpert expert = linkMetaclass.getMetamodel().getMExpert();
        
            if (expert.canCompose(sourceElement, linkMetaclass, depName)) {
                return getHost();
            }
        
            if (mdaExpert.canSource(linkStereotype, linkMetaclass, sourceElement.getMClass())) {
                return getHost();
            }
        
            return null;
        } else if (RequestConstants.REQ_RECONNECT_SOURCE.equals(request.getType())) {
            ReconnectRequest r = (ReconnectRequest) request;
            if (r.getConnectionEditPart().getModel() instanceof IGmNodeLink) {
                return getHost();
            }
        }
        return null;
    }

    @objid ("7ebfe27f-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void eraseTargetConnectionFeedback(DropRequest request) {
        super.eraseTargetConnectionFeedback(request);
        
        // Additional feedback: outline the Node.
        final IFigure highlight = (IFigure) ((Request) request).getExtendedData().get(LinkedNodeStartCreationEditPolicy.HIGHLIGHTKEY);
        if (highlight != null) {
            final IFigure feedbackLayer = getFeedbackLayer();
            if (highlight.getParent() == feedbackLayer) {
                feedbackLayer.remove(highlight);
                ((Request) request).getExtendedData().remove(LinkedNodeStartCreationEditPolicy.HIGHLIGHTKEY);
            }
        }
    }

    @objid ("7ebfe285-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getConnectionCompleteCommand(CreateConnectionRequest request) {
        return null;
    }

    @objid ("7ebfe28f-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
        ModelioCreationContext context = ModelioCreationContext.fromRequest(request);
        
        CreateLinkedNodeCommand cmd = null;
        if (Document.class == context.getJavaClass()) {
            cmd = new CreateExternDocumentCommand(context);
        } else if (Dependency.class == context.getJavaClass() && context.getStereotype() != null && context.getStereotype().getName().equals("related_diagram")) {
            cmd = new CreateDiagramViewCommand(context);
        } else {
            cmd = new CreateLinkedNodeCommand(context);
        }
        
        cmd.setSource(getHost());
        
        // Getting a hold on the model of both anchors
        cmd.setSourceAnchor(AnchorModelHelper.getSourceAnchorModel(request.getTargetEditPart(), request));
        request.setStartCommand(cmd);
        return cmd;
    }

    /**
     * Highlight the node under the mouse when starting a linked node creation.
     */
    @objid ("7ebfe299-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void showTargetConnectionFeedback(final DropRequest dropRequest) {
        final Request request = (Request) dropRequest;
        
        // Only linked node creation start is supported here
        if (request.getType() != LinkedNodeRequestConstants.REQ_LINKEDNODE_START) {
            return;
        }
        
        // Additional feedback: highlight the node.
        // compute highlight type
        Command c = getCommand(request);
        FigureUtilities2.HighlightType hightlightType = FigureUtilities2.HighlightType.INFO;
        if (c == null) {
            hightlightType = FigureUtilities2.HighlightType.ERROR;
        } else if (c.canExecute()) {
            hightlightType = FigureUtilities2.HighlightType.SUCCESS;
        } else {
            hightlightType = FigureUtilities2.HighlightType.WARNING;
        }
        
        // create a highlight figure if it does not exist
        IFigure highlight = (IFigure) request.getExtendedData().get(LinkedNodeStartCreationEditPolicy.HIGHLIGHTKEY);
        if (highlight == null) {
            // create a hightlight figure
            highlight = FigureUtilities2.createHighlightFigure(getFeedbackLayer(), getHostFigure(), hightlightType);
            // add the highlight figure to the feedback layer
            getFeedbackLayer().add(highlight);
        
            // register this additional feedback into the
            request.getExtendedData().put(LinkedNodeStartCreationEditPolicy.HIGHLIGHTKEY, highlight);
        }
        
        // configure the highlight figure
        FigureUtilities2.updateHighlightType(highlight, hightlightType);
    }

    @objid ("7ebfe2a1-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected boolean isHandled(final Request request) {
        if (LinkedNodeRequestConstants.REQ_LINKEDNODE_START.equals(request.getType())) {
            return true;
        }
        if (RequestConstants.REQ_RECONNECT_SOURCE.equals(request.getType())) {
            ReconnectRequest r = (ReconnectRequest) request;
            return r.getConnectionEditPart().getModel() instanceof IGmNodeLink;
        }
        return false;
    }

    @objid ("7ec244a8-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void eraseCreationFeedback(final CreateConnectionRequest request) {
        super.eraseCreationFeedback(request);
        
        eraseTargetConnectionFeedback(request);
    }

}
