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

import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.DropRequest;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.plugin.DiagramElements;

/**
 * Edit policy that allow a linked node to be unmasked inside the host.
 * 
 * @author cmarin
 */
@objid ("7ebb1de0-1dec-11e2-8cad-001ec947c8cc")
public class LinkedNodeFinishCreationEditPolicy extends AbstractLinkedNodeCreationEditPolicy {
    /**
     * Creation context property name in the plugins.xml file.
     * <p>
     * This property gives the class name of an edit policy that show the feedback figure for the node to be created.
     * <p>
     * Can be used to show a note figure for note creation, ...
     */
    @objid ("90b1dfd6-1e83-11e2-8cad-001ec947c8cc")
    private static final String FINISHPOLICYKEY = "finish_feedback_policy";

    @objid ("7ebd7fef-1dec-11e2-8cad-001ec947c8cc")
    private static final Object HIGHLIGHTKEY = "target feedback";

    @objid ("7ebd7ff4-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void eraseTargetConnectionFeedback(final DropRequest dropRequest) {
        super.eraseTargetConnectionFeedback(dropRequest);
        
        // Only creation finish is handled
        final Request request = (Request) dropRequest;
        if (!LinkedNodeRequestConstants.REQ_LINKEDNODE_END.equals(request.getType())) {
            return;
        }
        
        // Additional feedback: outline the Node.
        GraphicalEditPolicy delegatePolicy = getDelegateFeedbackPolicy((CreateConnectionRequest) dropRequest, false);
        if (delegatePolicy != null) {
            delegatePolicy.showTargetFeedback((Request) dropRequest);
        } else {
            RectangleFigure highlight = (RectangleFigure) request.getExtendedData().get(HIGHLIGHTKEY);
            if (highlight != null) {
                final IFigure feedbackLayer = getFeedbackLayer();
                if (highlight.getParent() == feedbackLayer) {
                    feedbackLayer.remove(highlight);
                    request.getExtendedData().remove(HIGHLIGHTKEY);
                }
            }
        }
    }

    @objid ("7ebd7ffb-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getConnectionCompleteCommand(CreateConnectionRequest request) {
        AbstractNodeEditPart nodeEditPart = (AbstractNodeEditPart) getHost();
        CreateLinkedNodeCommand startCommand = (CreateLinkedNodeCommand) request.getStartCommand();
        
        startCommand.setDestinationNode((GmCompositeNode) nodeEditPart.getModel());
        Point p = new Point(request.getLocation());
        getHostFigure().translateToRelative(p);
        startCommand.setNodeLocation(p);
        if (request.getSize() != null) {
            Dimension d = new Dimension(request.getSize());
            getHostFigure().translateToRelative(d);
            startCommand.setNodeSize(d);
        } else {
            startCommand.setNodeSize(null);
        }
        return startCommand;
    }

    @objid ("7ebd8005-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
        return null;
    }

    @objid ("7ebd800f-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void showTargetConnectionFeedback(DropRequest request) {
        super.showTargetConnectionFeedback(request);
        
        final CreateConnectionRequest req = (CreateConnectionRequest) request;
        if (LinkedNodeRequestConstants.REQ_LINKEDNODE_END != req.getType()) {
            return;
        }
        
        if (! isUserEditable()) {
            return;
        }
        
        final IFigure feedbackLayer = getFeedbackLayer();
        
        GraphicalEditPolicy feedbackPolicy = getDelegateFeedbackPolicy(req, true);
        if (feedbackPolicy != null) {
            feedbackPolicy.showTargetFeedback(req);
        } else {
            // Additional feedback: highlight the node.
            RectangleFigure highlight = (RectangleFigure) req.getExtendedData().get(HIGHLIGHTKEY);
        
            if (highlight == null) {
                highlight = createFeedBackFigure(req);
        
                feedbackLayer.add(highlight);
                ((Request) request).getExtendedData().put(HIGHLIGHTKEY, highlight);
            }
        
            Point location = req.getLocation().getCopy();
            feedbackLayer.translateToRelative(location);
            highlight.setBounds(new Rectangle(location, new Dimension(50, 50)));
        }
    }

    /**
     * Create the feed back figure.
     * <p>
     * Should create a ghost node to display where the node will be unmasked.
     * 
     * @param req The create linked node request
     */
    @objid ("7ebd8015-1dec-11e2-8cad-001ec947c8cc")
    private RectangleFigure createFeedBackFigure(CreateConnectionRequest req) {
        RectangleFigure highlight = new RectangleFigure();
        highlight.setBorder(new LineBorder(ColorConstants.black, 1));
        highlight.setFill(false);
        return highlight;
    }

    /**
     * Returns the <i>host</i> for the appropriate <code>Requests</code>. Returns <code>null</code> otherwise.
     * @see org.eclipse.gef.EditPolicy#getTargetEditPart(Request)
     */
    @objid ("7ebd801f-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public EditPart getTargetEditPart(Request request) {
        if (REQ_LINKEDNODE_END.equals(request.getType()) && isUserEditable()) {
            CreateConnectionRequest r = (CreateConnectionRequest) request;
            IGmDiagram srcdiag = ((IGmObject) r.getSourceEditPart().getModel()).getDiagram();
            IGmDiagram targetdiag =((IGmObject) getHost().getModel()).getDiagram();
            if (srcdiag == targetdiag) {
                return getHost();
            }
        }
        return null;
    }

    /**
     * Get a delegate edit policy used to show target feedback on link creation.
     * <p>
     * Not used yet. Could be used to instantiate an edit policy specified in the plugin.xml that display a specific feedback figure
     * on link creation.
     * <p>
     * Then a note figure could be displayed when creating a linked note.
     * 
     * @param req the connection creation request
     * @param createMissing if <code>true</code>, instantiate the edit policy if not already done.
     * @return the found feedback policy or <code>null</code> if none found.
     */
    @objid ("7ebd802a-1dec-11e2-8cad-001ec947c8cc")
    @SuppressWarnings("unchecked")
    protected GraphicalEditPolicy getDelegateFeedbackPolicy(final CreateConnectionRequest req, final boolean createMissing) {
        GraphicalEditPolicy ret = null;
        
        // Look for the cached policy in the map
        final Map<Object, Object> data = req.getExtendedData();
        if (data.containsKey(FINISHPOLICYKEY)) {
            return (GraphicalEditPolicy) data.get(FINISHPOLICYKEY);
        }
        
        // Instantiate the edit policy if class name is specified
        if (createMissing) {
            final ModelioCreationContext context = (ModelioCreationContext) req.getNewObject();
            final String policyClassName = (String) context.getProperties().get(FINISHPOLICYKEY);
            if (policyClassName != null) {
                try {
                    Class<? extends GraphicalEditPolicy> c;
                    c = (Class<? extends GraphicalEditPolicy>) Class.forName(policyClassName);
                    ret = c.newInstance();
                    ret.setHost(getHost());
                    ret.activate();
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                    DiagramElements.LOG.error(e);
                }
            }
        
            data.put(FINISHPOLICYKEY, ret);
        }
        return ret;
    }

    @objid ("7ebfe24d-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected boolean isHandled(final Request req) {
        return (REQ_LINKEDNODE_END.equals(req.getType()));
    }

}
