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

package org.modelio.diagram.elements.common.linktovoid;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.DropRequest;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.figures.FigureUtilities2;
import org.modelio.diagram.elements.core.link.IAnchorModelProvider;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.node.GmNodeModel;

/**
 * Edit policy that allow to create a node linked to this node.
 * 
 * @author cmarin
 */
@objid ("7ed7b9dd-1dec-11e2-8cad-001ec947c8cc")
public class LinkToVoidStartCreationEditPolicy extends AbstractLinkToVoidCreationEditPolicy {
    @objid ("7ed7b9df-1dec-11e2-8cad-001ec947c8cc")
    private static final Object HIGHLIGHTKEY = "feedback source";

    @objid ("7ed7b9e1-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void eraseTargetConnectionFeedback(DropRequest request) {
        super.eraseTargetConnectionFeedback(request);
        // Additional feedback: outline the Node.
        final RectangleFigure highlight = (RectangleFigure) ((Request) request).getExtendedData()
                                                                               .get(HIGHLIGHTKEY);
        if (highlight != null) {
            final IFigure feedbackLayer = getFeedbackLayer();
            if (highlight.getParent() == feedbackLayer) {
                feedbackLayer.remove(highlight);
                ((Request) request).getExtendedData().remove(HIGHLIGHTKEY);
            }
        }
    }

    @objid ("7ed7b9e7-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getConnectionCompleteCommand(CreateConnectionRequest request) {
        return null;
    }

    @objid ("7ed7b9f1-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
        final ModelioCreationContext context = (ModelioCreationContext) request.getNewObject();
        final AbstractNodeEditPart nodeEditPart = (AbstractNodeEditPart) getHost();
        
        final CreateLinkToVoidCommand cmd = new CreateLinkToVoidCommand(context);
        
        final IFigure nodeFigure = nodeEditPart.getFigure();
        final Point location = request.getLocation().getCopy();
        nodeFigure.translateToRelative(location);
        
        cmd.setSourceNode((GmNodeModel) getHost().getModel());
        cmd.setSourceAnchor(getSourceAnchorModel(request));
        
        request.setStartCommand(cmd);
        return cmd;
    }

    @objid ("7ed7b9fb-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void showTargetConnectionFeedback(DropRequest request) {
        super.showTargetConnectionFeedback(request);
        
        // Additional feedback: highlight the node.
        if (((Request) request).getType() != REQ_LINKTOVOID_START) {
            return;
        }
        
        // compute highlight type
        final Command c = getCommand((Request) request);
        FigureUtilities2.HighlightType hightlightType = FigureUtilities2.HighlightType.INFO;
        if (c == null) {
            hightlightType = FigureUtilities2.HighlightType.ERROR;
        } else if (c.canExecute()) {
            hightlightType = FigureUtilities2.HighlightType.SUCCESS;
        } else {
            hightlightType = FigureUtilities2.HighlightType.WARNING;
        }
        
        // create a highlight figure if it does not exist
        IFigure highlight = (IFigure) ((Request) request).getExtendedData().get(HIGHLIGHTKEY);
        
        if (highlight == null) {
            // create a hightlight figure
            highlight = FigureUtilities2.createHighlightFigure(getFeedbackLayer(),
                                                               getHostFigure(),
                                                               hightlightType);
            // add the highlight figure to the feedback layer
            getFeedbackLayer().add(highlight);
            // register this additional feedback into the 
            ((Request) request).getExtendedData().put(HIGHLIGHTKEY, highlight);
        }
        // configure the highlight figure
        FigureUtilities2.updateHighlightType(highlight, hightlightType);
    }

    /**
     * Returns the <i>host</i> for the appropriate <code>Requests</code>. Returns <code>null</code> otherwise.
     * @see org.eclipse.gef.EditPolicy#getTargetEditPart(Request)
     */
    @objid ("7ed7ba01-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public EditPart getTargetEditPart(Request request) {
        if (REQ_LINKTOVOID_START.equals(request.getType()) ||
            REQ_LINKTOVOID_RECONNECT_SOURCE.equals(request.getType())) {
            return getHost();
        }
        return null;
    }

    @objid ("7ed7ba0c-1dec-11e2-8cad-001ec947c8cc")
    private Object getSourceAnchorModel(final CreateConnectionRequest request) {
        NodeEditPart sourceEditPart = (NodeEditPart) request.getTargetEditPart();
        ConnectionAnchor srcAnchor = sourceEditPart.getSourceConnectionAnchor(request);
        if (sourceEditPart instanceof IAnchorModelProvider) {
            return (((IAnchorModelProvider) sourceEditPart).createAnchorModel(srcAnchor));
        } else {
            return null; // TODO handle non IAnchorModelProvider
        }
    }

}
