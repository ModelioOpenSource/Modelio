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
package org.modelio.uml.statikdiagram.editor.elements.requiredinterface;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.EllipseAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.SelectionEditPolicy;
import org.modelio.diagram.elements.common.abstractdiagram.AbstractDiagramEditPart;
import org.modelio.diagram.elements.common.linktovoid.LinkToVoidConstants;
import org.modelio.diagram.elements.core.figures.EllipseFigure;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.policies.SmallNodeNonResizeableEditPolicy;
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.NaryConnector;
import org.modelio.metamodel.uml.statik.NaryLinkEnd;
import org.modelio.uml.statikdiagram.editor.elements.providedinterface.GmProvidedInterfaceLink;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Edit part for {@link GmLollipopConnection}.
 * <p>
 * Represented as a simple ellipse in the diagram.
 * 
 * @author cmarin
 */
@objid ("367a89a9-55b7-11e2-877f-002564c97630")
public class LollipopConnectionEditPart extends AbstractNodeEditPart {
    @objid ("367a89ad-55b7-11e2-877f-002564c97630")
    private static final int LOLLIPOP_DIAM = GmProvidedInterfaceLink.LOLLIPOP_DIAM;

    @objid ("367a89af-55b7-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        final EllipseFigure fig = new EllipseFigure();
        fig.setPreferredSize(LOLLIPOP_DIAM, LOLLIPOP_DIAM);
        fig.setSize(LOLLIPOP_DIAM, LOLLIPOP_DIAM);
        
        refreshFromStyle(fig, getModelStyle());
        return fig;
    }

    @objid ("367a89b4-55b7-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        
        installEditPolicy(LinkToVoidConstants.REQ_LINKTOVOID_END, new LollipopConnectionLinksEditPolicy());
        
    }

    @objid ("367a89b7-55b7-11e2-877f-002564c97630")
    @Override
    public ConnectionAnchor getTargetConnectionAnchor(final ConnectionEditPart connectionPart) {
        return new EllipseAnchor(getFigure());
    }

    @objid ("367c1019-55b7-11e2-877f-002564c97630")
    @Override
    public ConnectionAnchor getTargetConnectionAnchor(final Request request) {
        return new EllipseAnchor(getFigure());
    }

    @objid ("367c1020-55b7-11e2-877f-002564c97630")
    @Override
    protected void refreshVisuals() {
        final GmLollipopConnection gmNode = (GmLollipopConnection) this.getModel();
        this.getFigure().getParent().setConstraint(this.getFigure(), gmNode.getLayoutData());
        
    }

    @objid ("367c1023-55b7-11e2-877f-002564c97630")
    @Override
    public SelectionEditPolicy getPreferredDragRolePolicy(final String requestType) {
        return new SmallNodeNonResizeableEditPolicy();
    }

    @objid ("7c2c6909-6b8b-4216-baa3-91a45074055b")
    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(GmLollipopConnection.PROP_REFRESH_BRANCHES)) {
            // Refresh the branches, look for missing branches
            GmNodeModel gmModel = getModel();
            final List<ModelElement> missingBranches = getMissingBranches(gmModel);
            if (! missingBranches.isEmpty()) {
                Point dropLocation = getFigure().getBounds().getBottomRight();
                getFigure().translateToAbsolute(dropLocation);
        
                final Map<?, ?> editPartRegistry = getViewer().getEditPartRegistry();
                final AbstractDiagramEditPart diagramEditPart = (AbstractDiagramEditPart) editPartRegistry.get(gmModel.getDiagram());
                
                ModelElementDropRequest request = new ModelElementDropRequest();
                request.setDroppedElements(missingBranches.toArray(new MObject[0]));
                request.setLocation(dropLocation);
        
                final Command command = diagramEditPart.getCommand(request);
                if (command != null) {
                    command.execute();
        
                    // 'propertyChange' is called from a model change handler, we must manually trigger a refresh for these newly unmasked elements
                    for (ModelElement modelElement : missingBranches) {
                        for (GmModel gm : gmModel.getDiagram().getAllGMRepresenting(new MRef(modelElement))) {
                            gm.obElementsUpdated();
                        }
                    }
                }
            }
        }
        
        // In any case apply the super routine.
        super.propertyChange(evt);
        
    }

    @objid ("82e84bbd-dfbb-41b6-a2dd-4cfa71a2d52f")
    private List<ModelElement> getMissingBranches(final GmNodeModel gmModel) {
        NaryConnector element = (NaryConnector) gmModel.getRelatedElement();
        if (element == null || !element.isValid()) {
            return Collections.emptyList();
        } else {
            List<ModelElement> newbranches = new ArrayList<>();
            // Get all consumers/providers
            for (NaryLinkEnd end : element.getNaryLinkEnd()) {
                if (end.getConsumer() != null) {
                    newbranches.add(end.getConsumer());
                }
        
                if (end.getProvider() != null) {
                    newbranches.add(end.getProvider());
                }
            }
        
            // Remove branches already unmasked
            for (IGmLink l : gmModel.getEndingLinks()) {
                newbranches.remove(l.getRelatedElement());
            }
        
            return newbranches;
        }
        
    }

}
