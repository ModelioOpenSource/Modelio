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
package org.modelio.diagram.elements.common.linktovoid;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.DropRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.link.GmPath;
import org.modelio.diagram.elements.core.link.anchors.GmSourceSatelliteAnchor;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.model.IGmModelRelated;
import org.modelio.diagram.elements.core.model.IGmNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Edit policy that allow a linked node to be unmasked inside the host.
 * 
 * @author cmarin
 */
@objid ("7ed2f527-1dec-11e2-8cad-001ec947c8cc")
public class LinkToVoidFinishCreationEditPolicy extends AbstractLinkToVoidCreationEditPolicy {
    @objid ("7ed2f529-1dec-11e2-8cad-001ec947c8cc")
    private static final Object HIGHLIGHTKEY = "target feedback";

    @objid ("7ed2f52b-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void eraseTargetConnectionFeedback(DropRequest request) {
        super.eraseTargetConnectionFeedback(request);
        
        // Additional feedback: outline the Node.
        final Request request2 = (Request) request;
        final RectangleFigure highlight = (RectangleFigure) request2.getExtendedData().get(HIGHLIGHTKEY);
        if (highlight != null) {
            final IFigure feedbackLayer = getFeedbackLayer();
            if (highlight.getParent() == feedbackLayer) {
                feedbackLayer.remove(highlight);
                request2.getExtendedData().remove(HIGHLIGHTKEY);
            }
        }
        
    }

    @objid ("7ed2f531-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getConnectionCompleteCommand(CreateConnectionRequest request) {
        final CreateLinkToVoidCommand startCommand = (CreateLinkToVoidCommand) request.getStartCommand();
        final IFigure srcFigure = ((AbstractGraphicalEditPart) request.getSourceEditPart()).getFigure();
        final Dimension dist = computeDistance(srcFigure, request.getLocation());
        
        startCommand.setDestinationLocation(dist);
        return startCommand;
    }

    @objid ("7ed2f53b-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
        return null;
    }

    @objid ("7ed2f545-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void showTargetConnectionFeedback(DropRequest request) {
        super.showTargetConnectionFeedback(request);
        
        final Request req = (Request) request;
        if (req.getType() != REQ_LINKTOVOID_END) {
            return;
        }
        
        // Additional feedback: highlight the node.
        RectangleFigure highlight = (RectangleFigure) req.getExtendedData().get(HIGHLIGHTKEY);
        
        if (highlight == null) {
            highlight = createFeedBackFigure(request);
        
            getFeedbackLayer().add(highlight);
            req.getExtendedData().put(HIGHLIGHTKEY, highlight);
        }
        
        final Point location = request.getLocation().getCopy();
        highlight.translateToRelative(location);
        highlight.setBounds(new Rectangle(location, new Dimension(10, 10)));
        
    }

    /**
     * Create the feed back figure.
     * <p>
     * Should create a ghost node to display where the node will be unmasked.
     * @param req The create linked node request
     */
    @objid ("7ed2f54b-1dec-11e2-8cad-001ec947c8cc")
    private RectangleFigure createFeedBackFigure(DropRequest req) {
        final RectangleFigure highlight = new RectangleFigure();
        highlight.setBorder(new LineBorder(ColorConstants.black, 1));
        highlight.setFill(false);
        return highlight;
    }

    /**
     * Returns the <i>host</i> for the appropriate <code>Requests</code>. Returns <code>null</code> otherwise.
     * @see org.eclipse.gef.EditPolicy#getTargetEditPart(Request)
     */
    @objid ("7ed2f555-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public EditPart getTargetEditPart(Request request) {
        if (REQ_LINKTOVOID_END.equals(request.getType()) ||
                REQ_LINKTOVOID_RECONNECT_TARGET.equals(request.getType())) {
            return getHost();
        }
        return null;
    }

    /**
     * Compute the distance between the given figure upper left corner and the given point.
     * @param srcFigure The source figure
     * @param absoluteLoc a location in absolute coordinates
     * @return The distance in coordinates relative to the source figure
     */
    @objid ("7ed2f560-1dec-11e2-8cad-001ec947c8cc")
    protected Dimension computeDistance(final IFigure srcFigure, final Point absoluteLoc) {
        final Point loc = srcFigure.getBounds().getLocation();
        srcFigure.translateToAbsolute(loc);
        
        final Dimension dist = absoluteLoc.getDifference(loc);
        
        srcFigure.translateToRelative(dist);
        return dist;
    }

    @objid ("7ed55777-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getReconnectTargetCommand(final ReconnectRequest request) {
        final GmLink gmLink = (GmLink) request.getConnectionEditPart().getModel();
        
        final GraphicalEditPart srcEditPart = (GraphicalEditPart) request.getConnectionEditPart().getSource();
        final IFigure srcFigure = srcEditPart.getFigure();
        final Dimension dist = computeDistance(srcFigure, request.getLocation());
        
        final DisconnectLinkCommand cmd = new DisconnectLinkCommand(gmLink, gmLink.getDiagram());
        cmd.setAnchorLocation(dist);
        return cmd;
    }

    /**
     * Command that disconnect a link from its target.
     * <p>
     * The link will points to nothing in the model and to the GmDiagram itself in the diagram.
     * 
     * @author cmarin
     */
    @objid ("7ed55782-1dec-11e2-8cad-001ec947c8cc")
    public static class DisconnectLinkCommand extends Command {
        @objid ("7ed5578a-1dec-11e2-8cad-001ec947c8cc")
        private final IGmLink gmLink;

        @objid ("7ed5578c-1dec-11e2-8cad-001ec947c8cc")
        private final IGmNode newTargetNode;

        @objid ("73b47436-3d22-435e-b2cf-ffb043348cb7")
        private Dimension anchor;

        /**
         * Create the command.
         * @param gmLink The link to move.
         * @param newTarget The new target node, should be the diagram.
         */
        @objid ("7ed5578e-1dec-11e2-8cad-001ec947c8cc")
        public  DisconnectLinkCommand(final IGmLink gmLink, final IGmNode newTarget) {
            this.gmLink = gmLink;
            this.newTargetNode = newTarget;
            
        }

        @objid ("7ed55795-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public boolean canExecute() {
            if (!MTools.getAuthTool().canModify(this.gmLink.getDiagram().getRelatedElement())) {
                return false;
            }
            
            if (this.gmLink.getFrom() instanceof GmModel) {
                // The source and the link elements must be modifiable
                GmModel fromModel = (GmModel) this.gmLink.getFrom();
                return isModifableElement(fromModel) && isModifableElement(this.gmLink);
            } else {
                return isModifableElement(this.gmLink);
            }
            
        }

        @objid ("7ed55799-1dec-11e2-8cad-001ec947c8cc")
        private boolean isModifableElement(final IGmModelRelated model) {
            return MTools.getAuthTool().canModify(model.getRelatedElement());
        }

        @objid ("7ed5579f-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public void execute() {
            updateLinkTarget();
            
            if (this.anchor != null) {
                final GmPath newPath = new GmPath(this.gmLink.getPath());
                newPath.setTargetAnchor(new GmSourceSatelliteAnchor(this.anchor));
                this.gmLink.setLayoutData(newPath);
            }
            
        }

        /**
         * Set the exact source point of the link relative to the new source node location.
         * @param ray the source point of the link
         */
        @objid ("7ed557a2-1dec-11e2-8cad-001ec947c8cc")
        public void setAnchorLocation(final Dimension ray) {
            this.anchor = ray;
        }

        @objid ("7ed557a9-1dec-11e2-8cad-001ec947c8cc")
        protected void updateLinkTarget() {
            final MObject link = this.gmLink.getRelatedElement();
            final GmNodeModel oldTargetNode = (GmNodeModel) this.gmLink.getTo();
            
            final MObject oldDest = (oldTargetNode == null || oldTargetNode == this.newTargetNode) ? null
                    : oldTargetNode.getRelatedElement();
            
            if (oldDest != null) {
                // Disconnect the link
                link.getMClass().getMetamodel().getMExpert().setTarget(link, oldDest, null);
            }
            
            // Update gm model
            if (oldTargetNode != null) {
                oldTargetNode.removeEndingLink(this.gmLink);
            }
            
            if (this.newTargetNode != null) {
                this.newTargetNode.addEndingLink(this.gmLink);
            }
            
        }

    }

}
