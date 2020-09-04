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
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editpolicies.GraphicalEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.swt.SWT;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Special drop policy that can handle the drop of a sub partition (with potentially inbetween ancestors).
 */
@objid ("6140a944-55b6-11e2-877f-002564c97630")
public class BpmnLaneSetDropEditPolicy extends GraphicalEditPolicy {
    @objid ("012901e6-600d-4f08-adea-ddec18cfa730")
    private RectangleFigure highlight;

    /**
     * <p>
     * If all dropped elements are partitions returns host if all dropped elements are sub partitions of host (with potentially several not yet unmasked inbetween ancestors).
     * </p>
     * <p>
     * Mixed case: don't know how to handle, returns <code>null</code>.
     * </p>
     * 
     * *
     * @param request the drop request
     * @return the host if all dropped elements are sub partitions, <code>null</code> otherwise.
     */
    @objid ("6140a947-55b6-11e2-877f-002564c97630")
    private EditPart getDropTargetEditPart(ModelElementDropRequest request) {
        GmModel hostModel = (GmModel) getHost().getModel();
        IGmDiagram diagram = hostModel.getDiagram();
        MObject hostElement = hostModel.getRelatedElement();
        for (MObject droppedElement : request.getDroppedElements()) {
            if (!(droppedElement instanceof BpmnLane))
                // Eliminating mixed cases right now.
                return null;
            // Also eliminating if dropped partition is not a sub partition
            // of host. We check that host is somewhere in the composition
            // stack of dropped partition.
            BpmnLane droppedPartition = (BpmnLane) droppedElement;
            BpmnLane ancestorPartition = droppedPartition.getLaneSet().getParentLane();
            while (ancestorPartition != null) {
                // If ancestor IS in composition stack, OK, check next
                // dropped element.
                if (ancestorPartition.equals(hostElement))
                    break;
                // else
                // While we're at it, if there is a "closer" ancestor
                // already unmasked, do not handle.
                if (!diagram.getAllGMRepresenting(new MRef(ancestorPartition)).isEmpty())
                    return null;
                // ancestorPartition = ancestorPartition.getpA;
            }
            if (ancestorPartition == null)
                // Dropped partition is not a sub partition of host, we
                // cannot
                // handle this request
                return null;
        
        }
        // Only partitions that are sub partitions of host and have no
        // closer unmasked ancestor: return host!
        return getHost();
    }

    @objid ("6140a94f-55b6-11e2-877f-002564c97630")
    private Command getDropCommand(ModelElementDropRequest request) {
        // Only partitions that are sub partitions of host and have no
        // closer unmasked ancestor: build an unmask command for each dropped
        // partition!
        CompoundCommand command = new CompoundCommand();
        Point dropLocation = request.getDropLocation();
        for (MObject toUnmask : request.getDroppedElements()) {
            final CreateRequest req = new CreateRequest();
            req.setLocation(dropLocation);
            req.setSize(new Dimension(-1, -1));
            ModelioCreationContext gmCreationContext = new ModelioCreationContext(toUnmask);
            // gmCreationContext.setProperty("kind", PartitionToolKind.INNER.toString());
            req.setFactory(gmCreationContext);
            command.add(getHost().getCommand(req));
        
            // Introduce some offset, so that all elements are not totally on
            // top of each other.
            dropLocation = dropLocation.getTranslated(20, 20);
        
        }
        return command.unwrap();
    }

    @objid ("61422fba-55b6-11e2-877f-002564c97630")
    @Override
    public EditPart getTargetEditPart(Request request) {
        if (request.getType().equals(ModelElementDropRequest.TYPE))
            return getDropTargetEditPart((ModelElementDropRequest) request);
        // else
        return null;
    }

    @objid ("61422fc0-55b6-11e2-877f-002564c97630")
    @Override
    public Command getCommand(Request request) {
        if (request.getType().equals(ModelElementDropRequest.TYPE)) {
            return getDropCommand((ModelElementDropRequest) request);
        }
        return null;
    }

    @objid ("61422fc6-55b6-11e2-877f-002564c97630")
    @Override
    public void showTargetFeedback(Request request) {
        if (request.getType().equals(ModelElementDropRequest.TYPE)) {
            if (this.highlight == null) {
                this.highlight = new RectangleFigure();
                this.highlight.setLineWidth(2);
                this.highlight.setForegroundColor(this.getHostFigure().getForegroundColor());
                this.highlight.setLineStyle(SWT.LINE_DASH);
                this.highlight.setBounds(getHostFigure().getBounds().getCopy().expand(0, 0));
                this.highlight.setFill(false);
                addFeedback(this.highlight);
        
            }
        }
        super.showTargetFeedback(request);
    }

    @objid ("61422fca-55b6-11e2-877f-002564c97630")
    @Override
    public void eraseTargetFeedback(Request request) {
        if (request.getType().equals(ModelElementDropRequest.TYPE)) {
            if (this.highlight != null) {
                removeFeedback(this.highlight);
                this.highlight = null;
            }
        }
        
        super.eraseTargetFeedback(request);
    }

}
