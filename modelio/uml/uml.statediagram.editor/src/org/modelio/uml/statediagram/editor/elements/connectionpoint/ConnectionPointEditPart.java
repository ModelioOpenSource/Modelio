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
package org.modelio.uml.statediagram.editor.elements.connectionpoint;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.AccessibleAnchorProvider;
import org.eclipse.gef.EditPolicy;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeRequestConstants;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeStartCreationEditPolicy;
import org.modelio.diagram.elements.common.portbordered.IPortEditPart;
import org.modelio.diagram.elements.core.link.DefaultCreateLinkEditPolicy;
import org.modelio.diagram.elements.core.link.anchors.fixed.AbstractFixedNodeAnchorProvider;
import org.modelio.diagram.elements.core.link.anchors.fixed.EllipseFixedNodeAnchorProvider;
import org.modelio.diagram.elements.core.link.anchors.fixed.IFixedConnectionAnchorFactory;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.policies.AnchorsFeedbackEditPolicy;
import org.modelio.diagram.elements.core.tools.multipoint.CreateMultiPointRequest;
import org.modelio.diagram.elements.umlcommon.constraint.ConstraintLinkEditPolicy;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.metamodel.uml.behavior.stateMachineModel.ConnectionPointReference;
import org.modelio.uml.statediagram.editor.elements.connectionpoint.ConnectionPointFigure.ReferencedConnectionPoint;

/**
 * EditPart for a connection point reference in structured representation mode.
 * 
 * @author fpoyer
 */
@objid ("f4f920ba-55b6-11e2-877f-002564c97630")
public class ConnectionPointEditPart extends AbstractNodeEditPart implements IPortEditPart {
    @objid ("3b27aa18-ff2f-47b3-b7af-1f7386dff6ff")
    private AbstractFixedNodeAnchorProvider nodeAnchorProvider;

    @objid ("f4f920c0-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSelectable() {
        return false;
    }

    /**
     * Creates the Figure to be used as this part's main visuals
     */
    @objid ("f4f920c5-55b6-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        // create the figure
        ConnectionPointFigure fig = new ConnectionPointFigure();
        
        // set style independent properties
        fig.setSize(20, 20);
        
        // set style dependent properties
        refreshFromStyle(fig, getModelStyle());
        
        // return the figure
        return fig;
    }

    @objid ("f4f920cb-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshFromStyle(IFigure aFigure, IStyle style) {
        if (aFigure instanceof ConnectionPointFigure && !switchRepresentationMode()) {
            super.refreshFromStyle(aFigure, style);
        }
        
    }

    @objid ("f4f920d2-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshVisuals() {
        GmModel model = this.getModel();
        ConnectionPointFigure fig = (ConnectionPointFigure) this.getFigure();
        fig.getParent().setConstraint(fig, model.getLayoutData());
        // refresh the figure corresponding to the referenced element
        refreshFigureOfReference((ConnectionPointReference) model.getRelatedElement(), fig);
        
    }

    @objid ("f4f920d5-55b6-11e2-877f-002564c97630")
    private void refreshFigureOfReference(ConnectionPointReference connectionPoint, ConnectionPointFigure fig) {
        if (connectionPoint.getEntry() != null) {
            fig.setReference(ReferencedConnectionPoint.ENTRYREF);
        } else if (connectionPoint.getExit() != null) {
            fig.setReference(ReferencedConnectionPoint.EXITREF);
        } else {
            fig.setReference(ReferencedConnectionPoint.NOREF);
        }
        
    }

    @objid ("f4faa759-55b6-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        
        // Link creation & modification edit policy
        installEditPolicy(EditPolicy.NODE_ROLE, new DefaultCreateLinkEditPolicy());
        
        // Note creation
        installEditPolicy(LinkedNodeRequestConstants.REQ_LINKEDNODE_START,
                new LinkedNodeStartCreationEditPolicy());
        
        // Constraint creation
        installEditPolicy(CreateMultiPointRequest.REQ_MULTIPOINT_FIRST, new ConstraintLinkEditPolicy(false));
        
        installEditPolicy(AnchorsFeedbackEditPolicy.class, new AnchorsFeedbackEditPolicy(this.nodeAnchorProvider));
        
    }

    @objid ("dbcaf0ac-96d0-45fb-9cdb-ba869bea6ed5")
    @Override
    protected AbstractFixedNodeAnchorProvider getNodeAnchorProvider() {
        if (this.nodeAnchorProvider == null) {
            this.nodeAnchorProvider = createAnchorProvider(this.figure);
        }
        return this.nodeAnchorProvider;
    }

    @objid ("c56e2181-db1b-4c69-99cc-ed550797004c")
    @Override
    protected void setFigure(IFigure figure) {
        super.setFigure(figure);
        getNodeAnchorProvider().onFigureMoved(figure);
        
    }

    /**
     * Create the {@link AbstractFixedNodeAnchorProvider} for this edit part.
     * @param figure the edit part figure.
     * @return the created anchor provider.
     */
    @objid ("beb08347-4505-4532-a7f4-d1b5434563a9")
    protected AbstractFixedNodeAnchorProvider createAnchorProvider(IFigure figure) {
        return new EllipseFixedNodeAnchorProvider();
    }

    /**
     * Adapt to {@link AccessibleAnchorProvider} or {@link IFixedConnectionAnchorFactory}.
     */
    @objid ("4e98e6dc-cacd-4edf-89de-9bae32f56557")
    @Override
    public Object getAdapter(Class adapter) {
        if (AccessibleAnchorProvider.class.isAssignableFrom(adapter)) {
            return this.nodeAnchorProvider.getAccessibleAnchorProvider(getFigure());
        } else if (IFixedConnectionAnchorFactory.class.isAssignableFrom(adapter)) {
            return this.nodeAnchorProvider;
        }
        return super.getAdapter(adapter);
    }

}
