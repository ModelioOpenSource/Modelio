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

package org.modelio.bpmn.diagram.editor.elements.bpmnsequenceflow;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.requests.LocationRequest;
import org.modelio.bpmn.diagram.editor.elements.bpmnsequenceflow.insert.InsertInFlowEditPolicy;
import org.modelio.bpmn.diagram.editor.elements.bpmnsequenceflow.throwcatch.InsertThrowCatchEditPolicy;
import org.modelio.bpmn.diagram.editor.elements.policies.BpmnCreateLinkEditPolicy;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeRequestConstants;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeStartCreationEditPolicy;
import org.modelio.diagram.elements.core.figures.decorations.DefaultPolygonDecoration;
import org.modelio.diagram.elements.core.helpers.palapi.PaletteActionProvider;
import org.modelio.diagram.elements.core.link.LinkEditPart;
import org.modelio.diagram.elements.core.link.createhandle.UserChoiceCreateLinkEditPolicy;
import org.modelio.diagram.elements.core.policies.DefaultConnectionEndpointEditPolicy;
import org.modelio.diagram.elements.core.policies.DefaultDeleteLinkEditPolicy;
import org.modelio.diagram.elements.core.policies.DelegatingDirectEditionEditPolicy;
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;
import org.modelio.diagram.elements.core.tools.multipoint.CreateMultiPointRequest;
import org.modelio.diagram.elements.umlcommon.constraint.ConstraintLinkEditPolicy;
import org.modelio.metamodel.bpmn.events.BpmnBoundaryEvent;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.gateways.BpmnGateway;

/**
 * Edit part for {@link GmBpmnSequenceFlow}.
 */
@objid ("619c369a-55b6-11e2-877f-002564c97630")
public class BpmnSequenceFlowEditPart extends LinkEditPart {
    @objid ("619dbd3b-55b6-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        PolylineConnection connection = (PolylineConnection) super.createFigure();
        connection.setTargetDecoration(getArrowDecoration());
        
        // Make sure the arrow has appropriate style
        refreshFromStyle(connection, getModelStyle());
        return connection;
    }

    @objid ("619dbd40-55b6-11e2-877f-002564c97630")
    private DefaultPolygonDecoration getArrowDecoration() {
        DefaultPolygonDecoration decoration = new DefaultPolygonDecoration();
        decoration.setTemplate(PolygonDecoration.TRIANGLE_TIP);
        decoration.setScale(8, 5);
        decoration.setOpaque(true);
        decoration.setFill(true);
        return decoration;
    }

    @objid ("619dbd46-55b6-11e2-877f-002564c97630")
    private DefaultPolygonDecoration getDiamondDecoration() {
        DefaultPolygonDecoration decoration = new DefaultPolygonDecoration();
        PointList points = new PointList(new int[] { -1, 1, 0, 0, -1, -1, -2, 0, -1, 1 });
        decoration.setTemplate(points);
        decoration.setScale(8, 5);
        decoration.setOpaque(true);
        decoration.setFill(true);
        decoration.setBackgroundColor(ColorConstants.white);
        return decoration;
    }

    @objid ("619dbd4c-55b6-11e2-877f-002564c97630")
    private DefaultPolygonDecoration getCrossDecoration() {
        DefaultPolygonDecoration decoration = new DefaultPolygonDecoration();
        PointList points = new PointList(new int[] { -5, -5, -20, 5 });
        decoration.setTemplate(points);
        decoration.setScale(1, 1);
        decoration.setOpaque(true);
        return decoration;
    }

    @objid ("619dbd52-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshVisuals() {
        super.refreshVisuals();
        
        GmBpmnSequenceFlow gmmodel = getModel();
        BpmnSequenceFlow flow = gmmodel.getRepresentedElement();
        PolylineConnection pfigure = (PolylineConnection) getFigure();
        
        if (flow.getDefaultFrom() != null ||
                flow.getDefaultOfExclusive() != null ||
                flow.getDefaultOfInclusive() != null ||
                flow.getDefaultOfComplex() != null) {
            pfigure.setSourceDecoration(getCrossDecoration());
        } else {
            if (!flow.getConditionExpression().equals("")) {
                if (!(flow.getSourceRef() instanceof BpmnGateway || flow.getSourceRef() instanceof BpmnBoundaryEvent)) {
                    pfigure.setSourceDecoration(getDiamondDecoration());
                } else {
                    pfigure.setSourceDecoration(null);
                }
            } else {
                pfigure.setSourceDecoration(null);
            }
        }
    }

    @objid ("619dbd55-55b6-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        // Do not show the smart link creation handle on links.
        installEditPolicy(UserChoiceCreateLinkEditPolicy.class, new UserChoiceCreateLinkEditPolicy(new PaletteActionProvider(this, PaletteActionProvider.IS_LINK_TOOL), false));
        
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new SequenceFlowLinkLayoutEditPolicy());
        installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE, new DefaultConnectionEndpointEditPolicy());
        installEditPolicy(EditPolicy.NODE_ROLE, new BpmnCreateLinkEditPolicy(true));
        installEditPolicy(EditPolicy.CONNECTION_ROLE, new DefaultDeleteLinkEditPolicy());
        installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new DelegatingDirectEditionEditPolicy());
        installEditPolicy(LinkedNodeRequestConstants.REQ_LINKEDNODE_START, new LinkedNodeStartCreationEditPolicy());
        installEditPolicy(CreateMultiPointRequest.REQ_MULTIPOINT_FIRST, new ConstraintLinkEditPolicy(false));
        installEditPolicy(InsertInFlowEditPolicy.class.getSimpleName(), new InsertInFlowEditPolicy());
        installEditPolicy(InsertThrowCatchEditPolicy.class.getSimpleName(), new InsertThrowCatchEditPolicy());
        installEditPolicy(ModelElementDropRequest.class, new BpmnSequenceFlowElementDropEditPolicy());
    }

    @objid ("7b17cd7a-7660-41b5-b4a7-af73f01cb7d5")
    @Override
    public void performRequest(final Request req) {
        if (RequestConstants.REQ_DIRECT_EDIT.equals(req.getType())) {
            if (!(req instanceof LocationRequest)) {
                // Give the request to the guard
                Object guardEditPart = getViewer().getEditPartRegistry().get((getModel().getFirstExtension(GmBpmnSequenceFlow.ROLE_GUARD)));
                if (guardEditPart != null) {
                    // Make sure it understands the request
                    final EditPart childEditPart = (EditPart) guardEditPart;
                    if (childEditPart.understandsRequest(req)) {
                        childEditPart.performRequest(req);
                        return;
                    }
        
                }
            }
        }
        super.performRequest(req);
    }

    @objid ("187cb644-237e-483b-939c-209eefc71109")
    @Override
    public GmBpmnSequenceFlow getModel() {
        return (GmBpmnSequenceFlow) super.getModel();
    }

}
