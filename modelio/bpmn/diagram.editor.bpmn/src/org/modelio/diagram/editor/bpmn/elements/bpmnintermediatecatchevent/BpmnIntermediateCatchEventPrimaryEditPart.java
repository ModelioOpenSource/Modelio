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

package org.modelio.diagram.editor.bpmn.elements.bpmnintermediatecatchevent;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.SelectionEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.swt.graphics.Color;
import org.modelio.diagram.editor.bpmn.elements.policies.BpmnCreateLinkEditPolicy;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeRequestConstants;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeStartCreationEditPolicy;
import org.modelio.diagram.elements.core.figures.ColorizableImageFigure;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.policies.DefaultNodeResizableEditPolicy;
import org.modelio.diagram.elements.core.tools.multipoint.CreateMultiPointRequest;
import org.modelio.diagram.elements.umlcommon.constraint.ConstraintLinkEditPolicy;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;

/**
 * EditPart for an {@link GmBpmnIntermediateCatchEventPrimaryNode}.
 */
@objid ("9ed7de67-213f-4865-b7ce-64b384365bb0")
public final class BpmnIntermediateCatchEventPrimaryEditPart extends AbstractNodeEditPart {
    @objid ("620791e1-f118-4c11-9c69-b6be6f1757a2")
    @Override
    public boolean isSelectable() {
        return false;
    }

    @objid ("4f0d9b3b-d115-4943-bffc-21403764129c")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        installEditPolicy(EditPolicy.NODE_ROLE, new BpmnCreateLinkEditPolicy());
        installEditPolicy(LinkedNodeRequestConstants.REQ_LINKEDNODE_START, new LinkedNodeStartCreationEditPolicy());
        installEditPolicy(CreateMultiPointRequest.REQ_MULTIPOINT_FIRST, new ConstraintLinkEditPolicy(false));
    }

    @objid ("a68e0eb5-885c-4022-a309-1c4282e70259")
    @Override
    protected IFigure createFigure() {
        ColorizableImageFigure imageFigure = new ColorizableImageFigure(getModel().getEventImage());
        imageFigure.setPreferredSize(33, 33);
        imageFigure.setMinimumSize(new Dimension(33, 33));
        
        // set style dependent properties
        refreshFromStyle(imageFigure, getModelStyle());
        
        // return the figure
        return imageFigure;
    }

    @objid ("784d9c4e-e21e-4d23-88dc-0fc7d5953309")
    @Override
    protected void refreshFromStyle(IFigure aFigure, IStyle style) {
        if (!switchRepresentationMode()) {
            super.refreshFromStyle(aFigure, style);
        
            if (aFigure instanceof ColorizableImageFigure) {
                ColorizableImageFigure cFigure = (ColorizableImageFigure) aFigure;
                final GmModel gmModel = getModel();
                Color color = style.getColor(gmModel.getStyleKey(MetaKey.FILLCOLOR));
                cFigure.setColor(color);
            }
        }
    }

    @objid ("d89e165d-d9af-4406-9157-953714f3caad")
    @Override
    protected void refreshVisuals() {
        GmBpmnIntermediateCatchEventPrimaryNode initialNodeModel = getModel();
        getFigure().getParent().setConstraint(getFigure(), initialNodeModel.getLayoutData());
    }

    @objid ("174b3c2e-8ead-46ef-9e94-b7b1d461a6d4")
    @Override
    public SelectionEditPolicy getPreferredDragRolePolicy(final String requestType) {
        return new DefaultNodeResizableEditPolicy() {
                                    @Override
                                    protected Command getResizeCommand(ChangeBoundsRequest request) {
                                        ChangeBoundsRequest req = new ChangeBoundsRequest(RequestConstants.REQ_RESIZE_CHILDREN);
                                        req.setEditParts(getHost());
                        
                                        req.setMoveDelta(request.getMoveDelta());
                        
                                        int dimension = 0;
                                        int x = request.getSizeDelta().height;
                                        int y = request.getSizeDelta().width;
                        
                                        if (x >= 0 && y >= 0) {
                                            if (x > y) {
                                                dimension = x;
                                            } else {
                                                dimension = y;
                                            }
                                        } else {
                                            if (x < y) {
                                                dimension = x;
                                            } else {
                                                dimension = y;
                                            }
                                        }
                        
                                        req.setSizeDelta(new Dimension(dimension, dimension));
                                        req.setLocation(request.getLocation());
                                        req.setExtendedData(request.getExtendedData());
                                        req.setResizeDirection(request.getResizeDirection());
                                        return getHost().getParent().getCommand(req);
                                    }
                                };
    }

    @objid ("64300833-9981-4880-91eb-ae18f92a5108")
    @Override
    public GmBpmnIntermediateCatchEventPrimaryNode getModel() {
        return (GmBpmnIntermediateCatchEventPrimaryNode) super.getModel();
    }

}
