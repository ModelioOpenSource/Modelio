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

package org.modelio.diagram.editor.bpmn.elements.bpmnmessage;

import java.beans.PropertyChangeEvent;
import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.SelectionEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.modelio.diagram.editor.bpmn.editor.BpmnSharedImages;
import org.modelio.diagram.editor.bpmn.elements.bpmndataobject.BpmnDataFigure;
import org.modelio.diagram.editor.bpmn.elements.policies.BpmnCreateLinkEditPolicy;
import org.modelio.diagram.editor.bpmn.plugin.DiagramEditorBpmn;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeEndReconnectEditPolicy;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeRequestConstants;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeStartCreationEditPolicy;
import org.modelio.diagram.elements.core.figures.MinimumSizeLayout;
import org.modelio.diagram.elements.core.link.DefaultCreateLinkCommand;
import org.modelio.diagram.elements.core.link.ModelioLinkCreationContext;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmLinkable;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.policies.DefaultNodeResizableEditPolicy;
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.metamodel.bpmn.flows.BpmnMessageFlow;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Edit part for {@link GmBpmnMessagePrimaryNode}.
 */
@objid ("6159131a-55b6-11e2-877f-002564c97630")
public class BpmnMessageEditPart extends AbstractNodeEditPart {
    @objid ("6159131e-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSelectable() {
        return false;
    }

    /**
     * @see AbstractNodeEditPart#propertyChange(java.beans.PropertyChangeEvent)
     */
    @objid ("615a99ba-55b6-11e2-877f-002564c97630")
    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(IGmObject.PROPERTY_LABEL)) {
            refreshVisuals();
        } else {
            super.propertyChange(evt);
        }
        
        if (evt.getPropertyName().equals(IGmObject.PROPERTY_LINK_TARGET)) {
            // This property change event may be used to signal that some links are missing.
            Object newValue = evt.getNewValue();
            if (newValue instanceof BpmnMessageFlow) {
                createMissingLinkForElement((BpmnMessageFlow) newValue);
            }
        }
    }

    /**
     * @see BpmnMessageEditPart#createEditPolicies()
     */
    @objid ("615a99c0-55b6-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        
        installEditPolicy(EditPolicy.NODE_ROLE, new BpmnCreateLinkEditPolicy());
        installEditPolicy("linkedNode", new LinkedNodeEndReconnectEditPolicy());
        installEditPolicy(ModelElementDropRequest.TYPE, new BpmnMessageElementDropEditPolicy());
        
        installEditPolicy(LinkedNodeRequestConstants.REQ_LINKEDNODE_START, new LinkedNodeStartCreationEditPolicy());
    }

    /**
     * Creates the Figure to be used as this part's visuals
     * @see BpmnMessageEditPart#createFigure()
     */
    @objid ("615a99c4-55b6-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        // create the figure
        BpmnDataFigure fig = new BpmnDataFigure();
        // figure1.setLayoutManager(new BorderLayout());
        // set style independent properties
        fig.setOpaque(true);
        fig.setCenterIcon(DiagramEditorBpmn.getImageRegistry().getImage(BpmnSharedImages.MESSAGE));
        
        MinimumSizeLayout.apply(fig, 40, 55);
        
        // set style dependent properties
        refreshFromStyle(fig, getModelStyle());
        
        // return the figure
        return fig;
    }

    @objid ("615a99ca-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshVisuals() {
        final BpmnDataFigure fig = (BpmnDataFigure) getFigure();
        final GmBpmnMessagePrimaryNode gm = (GmBpmnMessagePrimaryNode) getModel();
        fig.getParent().setConstraint(fig, gm.getLayoutData());
        fig.setTopIcons(gm.getRepresentedIcon());
    }

    @objid ("615a99cd-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshFromStyle(final IFigure aFigure, final IStyle style) {
        if (aFigure instanceof BpmnDataFigure) {
            if (!switchRepresentationMode()) {
                super.refreshFromStyle(aFigure, style);
            }
        }
    }

    @objid ("615a99d6-55b6-11e2-877f-002564c97630")
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

    @objid ("615a99dd-55b6-11e2-877f-002564c97630")
    private void createMissingLinkForElement(final BpmnMessageFlow constrainedElement) {
        IGmLinkable sourceModel = getModel();
        GmBpmnMessageLink link = new GmBpmnMessageLink(sourceModel.getDiagram(),
                new MRef(sourceModel.getRelatedElement()));
        
        sourceModel.addStartingLink(link);
        CreateConnectionRequest request = new CreateConnectionRequest();
        request.setType(RequestConstants.REQ_CONNECTION_END);
        request.setSourceEditPart(this);
        request.setLocation(new Point(0, 0));
        ModelioLinkCreationContext context = new ModelioLinkCreationContext(sourceModel.getRelatedElement());
        request.setFactory(context);
        DefaultCreateLinkCommand startCommand = new DefaultCreateLinkCommand(context);
        startCommand.setSource(sourceModel);
        request.setStartCommand(startCommand);
        
        // Search all gm representing the new target
        Collection<GmModel> constrainedElementModels = sourceModel.getDiagram().getAllGMRelatedTo(new MRef(constrainedElement));
        // This boolean will be used to note that the searched End was found
        // unmasked at least once.
        for (GmModel constrainedElementModel : constrainedElementModels) {
            // For each gm, search the corresponding edit part
            EditPart editPart = (EditPart) this.getViewer()
                    .getEditPartRegistry()
                    .get(constrainedElementModel);
            if (editPart != null) {
                // See if this edit part accepts the reconnection request
                EditPart targetEditPart = editPart.getTargetEditPart(request);
                if (targetEditPart != null) {
                    // found a valid target: add the link to it!
                    IGmLinkable targetModel = (IGmLinkable) targetEditPart.getModel();
                    targetModel.addEndingLink(link);
                    break;
                }
            }
        }
    }

    @objid ("615a99e3-55b6-11e2-877f-002564c97630")
    @Override
    protected void addChildVisual(final EditPart childEditPart, final int index) {
        IFigure child = ((GraphicalEditPart) childEditPart).getFigure();
        if (index == 0) {
            this.getFigure().add(child, BorderLayout.CENTER, index);
        }
    }

}
