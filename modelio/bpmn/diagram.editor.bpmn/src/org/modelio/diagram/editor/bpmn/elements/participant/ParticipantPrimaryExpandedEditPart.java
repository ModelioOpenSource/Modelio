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

package org.modelio.diagram.editor.bpmn.elements.participant;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.requests.ReconnectRequest;
import org.modelio.diagram.editor.bpmn.elements.bpmnlane.BpmnLaneFigure;
import org.modelio.diagram.editor.bpmn.elements.diagrams.processdesign.GmBpmnProcessDesignDiagram;
import org.modelio.diagram.editor.bpmn.elements.participant.content.GmBpmnParticipantContent;
import org.modelio.diagram.editor.bpmn.elements.policies.BpmnCreateLinkEditPolicy;
import org.modelio.diagram.editor.bpmn.plugin.DiagramEditorBpmn;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeRequestConstants;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeStartCreationEditPolicy;
import org.modelio.diagram.elements.core.figures.MinimumSizeLayout;
import org.modelio.diagram.elements.core.figures.borders.TLBRBorder;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.model.IGmLinkable;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.policies.AutoExpandEditPolicy;
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.metamodel.bpmn.flows.BpmnMessageFlow;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnParticipant;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * EditPart for {@link GmBpmnParticipantPrimaryNode} in structured mode.
 */
@objid ("8d7f701a-db38-408a-b1df-9118f769ff2e")
public class ParticipantPrimaryExpandedEditPart extends AbstractNodeEditPart {
    /**
     * Layout orientation cache, initialized when the figure is created.
     */
    @objid ("6ac887fb-f754-41a8-bbb6-cc115b939d91")
    private Boolean isHorizontalLayoutCache = null;

    @objid ("86316a9f-78c2-40b7-bf64-edc054e4587f")
    @Override
    public GmBpmnParticipantPrimaryNode getModel() {
        return (GmBpmnParticipantPrimaryNode) super.getModel();
    }

    @objid ("c5cdc88b-6a19-4c18-aba9-5dc86ca45de7")
    @Override
    public boolean isSelectable() {
        return false;
    }

    @objid ("8a4a7aec-126b-4e15-9c77-12ff36f87668")
    @Override
    protected void addChildVisual(EditPart childEditPart, int index) {
        IFigure childFig = ((GraphicalEditPart) childEditPart).getFigure();
        GmNodeModel childModel = (GmNodeModel) childEditPart.getModel();
        
        boolean isHorizontal = getModel().isHorizontalParticipantOrientation();
        switch (childModel.getRoleInComposition()) {
        // Positional reading: see GmBpmnParticipantPrimaryExpanded constructor for details of indices.
        case GmBpmnParticipantPrimaryNode.ROLE_HEAD:
            if (isHorizontal) {
                childFig.setBorder(new TLBRBorder(false, false, false, true));
                getContentPane().add(childFig, BorderLayout.LEFT);
            } else {
                childFig.setBorder(new TLBRBorder(false, false, true, false));
                getContentPane().add(childFig, BorderLayout.TOP);
            }
            // refresh style for the new border
            refreshFromStyle(getContentPane(), getModelStyle());
            break;
        case GmBpmnParticipantPrimaryNode.ROLE_BODY:
            // body: free zone: go in center and take all available space.
            getContentPane().add(childFig, BorderLayout.CENTER);
            break;
        case GmBpmnParticipantPrimaryNode.ROLE_FOOTER:
            if (isHorizontal) {
                // footer, on the right.
                getContentPane().add(childFig, BorderLayout.RIGHT);
            } else {
                // footer, on the bottom.
                getContentPane().add(childFig, BorderLayout.BOTTOM);
            }
            break;
        default:
            throw new IllegalArgumentException(String.format("Unexpected '%s' child at index %d.", childEditPart, index));
        
        }
    }

    @objid ("550f7afc-eb02-484c-a506-49f4c3c90782")
    @Override
    protected void afterSwitchRepresentationMode() {
        BpmnParticipant thisParticipant = getModel().getRepresentedElement();
        List<ConnectionEditPart> srcConnections = new ArrayList<ConnectionEditPart>(getSourceConnections());
        List<ConnectionEditPart> tgtConnections = new ArrayList<ConnectionEditPart>(getTargetConnections());
        
        final IGmDiagram diagram = getModel().getDiagram();
        final Map<?, EditPart> editPartRegistry = getViewer().getEditPartRegistry();
        
        for (ConnectionEditPart connEp : srcConnections) {
            if (connEp.getModel() instanceof IGmLink) {
                final IGmLink gmLink = (IGmLink) connEp.getModel();
                MObject linkEl = gmLink.getRelatedElement();
                if (linkEl instanceof BpmnMessageFlow) {
                    BpmnMessageFlow f = (BpmnMessageFlow) linkEl;
                    if (!Objects.equals(f.getSourceRef(), thisParticipant)) {
                        for (GmModel fromCandidate : diagram.getAllGMRepresenting(new MRef(f.getSourceRef()))) {
                            AbstractNodeEditPart newSourceEp = (AbstractNodeEditPart) editPartRegistry.get(fromCandidate);
                            if (newSourceEp != null) {
                                reconnectSourceTo(gmLink, newSourceEp);
                            } else {
                                DiagramEditorBpmn.LOG.debug(new NullPointerException(String.format("%s has no edit part for source of %s.", fromCandidate, gmLink)));
                            }
                        }
                    }
                }
            }
        }
        
        for (ConnectionEditPart connEp : tgtConnections) {
            if (connEp.getModel() instanceof IGmLink) {
                final IGmLink gmLink = (IGmLink) connEp.getModel();
                MObject linkEl = gmLink.getRelatedElement();
                if (linkEl instanceof BpmnMessageFlow) {
                    BpmnMessageFlow f = (BpmnMessageFlow) linkEl;
                    if (!Objects.equals(f.getTargetRef(), thisParticipant)) {
                        for (GmModel toCandidate : diagram.getAllGMRepresenting(new MRef(f.getTargetRef()))) {
                            AbstractNodeEditPart newDestEp = (AbstractNodeEditPart) editPartRegistry.get(toCandidate);
                            if (newDestEp != null) {
                                reconnectTargetTo(gmLink, newDestEp);
                            } else {
                                DiagramEditorBpmn.LOG.debug(new NullPointerException(String.format("%s has no edit part for target of %s.", toCandidate, gmLink)));
                            }
                        }
                    }
                }
            }
        }
        
        super.afterSwitchRepresentationMode();
    }

    /**
     * Attach all message flow links toward inner nodes to itself before switching.
     */
    @objid ("6df53e36-4545-4e9c-825c-043ff50575a8")
    @Override
    protected void beforeSwitchRepresentationMode() {
        GmBpmnParticipantPrimaryNode m = getModel();
        
        m.refreshOrientation();
        
        GmBpmnParticipantContent body = m.getBody();
        if (body == null) {
            // Extern participant
            return;
        }
        GmBpmnProcessDesignDiagram embeddedDiag = body.getViewedDiagramModel(true);
        if (embeddedDiag != null) {
            Collection<GmModel> embeddedNodes = embeddedDiag.getAllModels();
        
            for (GmModel diagModel : m.getDiagram().getAllModels()) {
                if (diagModel instanceof IGmLink) {
                    IGmLink l = (IGmLink) diagModel;
                    IGmLinkable source = l.getFrom();
                    IGmLinkable target = l.getTo();
        
                    boolean srcEmbedded = embeddedNodes.contains(source);
                    boolean targetEmbedded = embeddedNodes.contains(target);
                    if (srcEmbedded ^ targetEmbedded) {
                        if (srcEmbedded) {
                            reconnectSourceTo(l, this);
                        }
        
                        if (targetEmbedded) {
                            reconnectTargetTo(l, this);
                        }
                    }
                }
            }
        }
    }

    @objid ("84dedea0-c634-48ca-9885-0b8b4c24acaa")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        
        installEditPolicy(AutoExpandEditPolicy.ROLE, new AutoExpandEditPolicy());
        
        installEditPolicy(EditPolicy.NODE_ROLE, new BpmnCreateLinkEditPolicy(false));
        installEditPolicy(LinkedNodeRequestConstants.REQ_LINKEDNODE_START, new LinkedNodeStartCreationEditPolicy());
        // Override the default DROP policy
        installEditPolicy(ModelElementDropRequest.TYPE, null);
    }

    @objid ("312a4dc4-328d-4378-94bf-2a56bae3e5ee")
    @Override
    protected IFigure createFigure() {
        BpmnLaneFigure fig = null;
        
        fig = new BpmnLaneFigure();
        fig.setLayoutManager(new BorderLayout());
        MinimumSizeLayout.apply(fig, 80, 40);
        
        // set style independent properties
        fig.setOpaque(true);
        
        // Init orientation
        this.isHorizontalLayoutCache = getModel().isHorizontalParticipantOrientation();
        
        // set style dependent properties
        refreshFromStyle(fig, getModelStyle());
        
        // return the figure
        return fig;
    }

    @objid ("aa723650-dc30-4d7f-b8a3-faa600c55a68")
    @Override
    protected void refreshFromStyle(IFigure aFigure, IStyle style) {
        if (aFigure instanceof BpmnLaneFigure) {
            if (!switchRepresentationMode()) {
                super.refreshFromStyle(aFigure, style);
        
                final GmModel gmModel = getModel();
                if (aFigure.getChildren().size() > 0) {
                    IFigure headerFigure = (IFigure) aFigure.getChildren().get(0);
                    ((TLBRBorder) headerFigure.getBorder()).setColor(style.getColor(gmModel.getStyleKey(MetaKey.LINECOLOR)));
                }
            }
        } else {
            super.refreshFromStyle(aFigure, style);
        }
    }

    /**
     * Refresh this EditPart's visuals.
     * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
     */
    @objid ("2375f512-3e00-4aec-b268-934420ddd921")
    @Override
    protected void refreshVisuals() {
        GmBpmnParticipantPrimaryNode partitionModel = getModel();
        getFigure().getParent().setConstraint(getFigure(), partitionModel.getLayoutData());
    }

    @objid ("8d0d1b17-f569-40e3-a9c4-d6e730d9c746")
    private void reconnectSourceTo(IGmLink gmLink, AbstractNodeEditPart newSourceEp) {
        ReconnectRequest req = new ReconnectRequest(RequestConstants.REQ_RECONNECT_SOURCE);
        ConnectionEditPart connEp = (ConnectionEditPart) getViewer().getEditPartRegistry().get(gmLink);
        Connection connFig = (Connection) connEp.getFigure();
        
        req.setConnectionEditPart(connEp);
        final Point firstPoint = connFig.getPoints().getFirstPoint();
        connFig.translateToAbsolute(firstPoint);
        req.setLocation(firstPoint);
        req.setTargetEditPart(newSourceEp);
        
        ConnectionAnchor newAnchor = newSourceEp.getSourceConnectionAnchor(req);
        Object newAnchorModel = newSourceEp.createAnchorModel(newAnchor);
        gmLink.getPath().setSourceAnchor(newAnchorModel);
        gmLink.getFrom().removeStartingLink(gmLink);
        newSourceEp.getModel().addStartingLink(gmLink);
    }

    @objid ("7628abeb-2af3-4e05-8905-2e5b443f0614")
    private void reconnectTargetTo(IGmLink gmLink, AbstractNodeEditPart newTargetEp) {
        ReconnectRequest req = new ReconnectRequest(RequestConstants.REQ_RECONNECT_TARGET);
        ConnectionEditPart connEp = (ConnectionEditPart) getViewer().getEditPartRegistry().get(gmLink);
        Connection connFig = (Connection) connEp.getFigure();
        
        req.setConnectionEditPart(connEp);
        final Point firstPoint = connFig.getPoints().getLastPoint();
        connFig.translateToAbsolute(firstPoint);
        req.setLocation(firstPoint);
        req.setTargetEditPart(newTargetEp);
        
        ConnectionAnchor newAnchor = newTargetEp.getTargetConnectionAnchor(req);
        Object newAnchorModel = createAnchorModel(newAnchor);
        gmLink.getPath().setTargetAnchor(newAnchorModel);
        gmLink.getTo().removeEndingLink(gmLink);
        
        newTargetEp.getModel().addEndingLink(gmLink);
    }

    @objid ("bdb135b6-dd84-4f41-897c-f389631a38ac")
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        super.propertyChange(evt);
        
        if (evt.getPropertyName().equals(GmBpmnParticipantPrimaryNode.CHECK_ORIENTATION)) {
            switchRepresentationMode();
        }
    }

    /**
     * @return <code>true</code> when an orientation change occurs, to make the edit part create its figure again.
     */
    @objid ("0625b4c4-7b7a-4985-831b-72badbdd7497")
    @Override
    protected boolean needsRepresentationModeSwitch() {
        final EditPart parentEditPart = getParent();
        if (parentEditPart == null || !isActive()) {
            return false;
        }
        
        if (this.isHorizontalLayoutCache != null && this.isHorizontalLayoutCache != getModel().isHorizontalParticipantOrientation()) {
            // update cache
            this.isHorizontalLayoutCache = getModel().isHorizontalParticipantOrientation();
        
            // return true to make the edit part create its figure again
            return true;
        } else {
            return super.needsRepresentationModeSwitch();
        }
    }

    @objid ("076e8fe7-cce2-4a81-b401-7ea43f489492")
    @Override
    protected void autoSizeNode(EditPart newEditPart) {
        // Orientation is changing, do not apply FitToMinSize
    }

}
