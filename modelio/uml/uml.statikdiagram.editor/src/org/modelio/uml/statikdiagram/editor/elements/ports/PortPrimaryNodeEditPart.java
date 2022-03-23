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
package org.modelio.uml.statikdiagram.editor.elements.ports;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeRequestConstants;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeStartCreationEditPolicy;
import org.modelio.diagram.elements.common.linktovoid.LinkToVoidConstants;
import org.modelio.diagram.elements.common.linktovoid.LinkToVoidStartCreationEditPolicy;
import org.modelio.diagram.elements.core.helpers.palapi.PaletteActionProvider;
import org.modelio.diagram.elements.core.link.DefaultCreateLinkEditPolicy;
import org.modelio.diagram.elements.core.link.createhandle.UserChoiceCreateLinkEditPolicy;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.umlcommon.constraint.ConstraintLinkEditPolicy;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.metamodel.uml.statik.PortOrientation;
import org.modelio.uml.statikdiagram.editor.elements.naryconnector.AcceptNConnectorEditPolicy;

/**
 * EditPart for {@link GmPortPrimaryNode}.
 * 
 * @author cmarin
 */
@objid ("364cc2f4-55b7-11e2-877f-002564c97630")
public class PortPrimaryNodeEditPart extends AbstractNodeEditPart {
    /**
     * Constructor.
     */
    @objid ("364cc2f8-55b7-11e2-877f-002564c97630")
    public  PortPrimaryNodeEditPart() {
        // Nothing specific to do.
    }

    @objid ("364e495a-55b7-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        PortFigure fig = new PortFigure();
        
        // set style independent properties
        fig.setOpaque(true);
        fig.setSize(13, 13);
        fig.setMinimumSize(new Dimension(13, 13));
        
        // set style dependent properties
        refreshFromStyle(fig, getModelStyle());
        
        // return the figure
        return fig;
    }

    @objid ("364e495f-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isSelectable() {
        return false;
    }

    @objid ("364e4964-55b7-11e2-877f-002564c97630")
    @Override
    protected void refreshVisuals() {
        PortFigure fig = (PortFigure) getFigure();
        GmPortPrimaryNode model = (GmPortPrimaryNode) getModel();
        fig.getParent().setConstraint(fig, model.getLayoutData());
        // read model to determine if port is IN, OUT, INOUT or NONE
        Port portElement = (Port) model.getRelatedElement();
        if (portElement != null) {
            fig.setPortDirection(portElement.getDirection());
        } else {
            // Defaults to NONE.
            fig.setPortDirection(PortOrientation.NONE);
        }
        // read model to set border figure is sticking to.
        fig.setPosition(model.getPosition());
        
    }

    @objid ("364e4967-55b7-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        
        // Do not show the smart link creation handle in ports which are too small to display them properly.
        installEditPolicy(UserChoiceCreateLinkEditPolicy.class, new UserChoiceCreateLinkEditPolicy(new PaletteActionProvider(this, PaletteActionProvider.IS_LINK_TOOL), false));
        
        installEditPolicy(EditPolicy.NODE_ROLE, new DefaultCreateLinkEditPolicy());
        
        installEditPolicy(LinkedNodeRequestConstants.REQ_LINKEDNODE_START, new LinkedNodeStartCreationEditPolicy());
        installEditPolicy(LinkToVoidConstants.REQ_LINKTOVOID_START, new LinkToVoidStartCreationEditPolicy());
        installEditPolicy("constraint", new ConstraintLinkEditPolicy(false));
        installEditPolicy("nary-connector", new AcceptNConnectorEditPolicy(true));
        
    }

    @objid ("364e496a-55b7-11e2-877f-002564c97630")
    @Override
    protected void refreshFromStyle(final IFigure aFigure, final IStyle style) {
        if (!switchRepresentationMode()) {
            super.refreshFromStyle(aFigure, style);
        }
        
    }

    @objid ("364e4973-55b7-11e2-877f-002564c97630")
    @Override
    public ConnectionAnchor getSourceConnectionAnchor(final ConnectionEditPart connection) {
        return new ChopboxAnchor(getFigure());
    }

    @objid ("364e497a-55b7-11e2-877f-002564c97630")
    @Override
    public ConnectionAnchor getSourceConnectionAnchor(final Request request) {
        return new ChopboxAnchor(getFigure());
    }

    @objid ("364e4981-55b7-11e2-877f-002564c97630")
    @Override
    public ConnectionAnchor getTargetConnectionAnchor(final ConnectionEditPart connectionPart) {
        return new ChopboxAnchor(getFigure());
    }

    @objid ("364e4988-55b7-11e2-877f-002564c97630")
    @Override
    public ConnectionAnchor getTargetConnectionAnchor(final Request request) {
        return new ChopboxAnchor(getFigure());
    }

}
