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

package org.modelio.diagram.editor.statik.elements.instance;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Border;
import org.eclipse.draw2d.CompoundBorder;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.modelio.diagram.editor.statik.elements.naryconnector.AcceptNConnectorEditPolicy;
import org.modelio.diagram.editor.statik.elements.narylink.AcceptNLinkEditPolicy;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeRequestConstants;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeStartCreationEditPolicy;
import org.modelio.diagram.elements.core.figures.GradientFigure;
import org.modelio.diagram.elements.core.figures.MinimumSizeLayout;
import org.modelio.diagram.elements.core.figures.ToolbarLayoutWithGrab;
import org.modelio.diagram.elements.core.figures.borders.ShadowBorder;
import org.modelio.diagram.elements.core.figures.borders.ZoomableLineBorder;
import org.modelio.diagram.elements.core.link.DefaultCreateLinkEditPolicy;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;
import org.modelio.diagram.elements.umlcommon.constraint.ConstraintLinkEditPolicy;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.metamodel.uml.statik.BindableInstance;

/**
 * {@link GmInstancePrimaryNode} edit part for simple mode.
 */
@objid ("35434211-55b7-11e2-877f-002564c97630")
public class InstanceSimpleEditPart extends AbstractNodeEditPart {
    @objid ("35434215-55b7-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new InstanceSmartCreateNodeEditPolicy());
        installEditPolicy(EditPolicy.NODE_ROLE, new DefaultCreateLinkEditPolicy());
        installEditPolicy(LinkedNodeRequestConstants.REQ_LINKEDNODE_START,
                new LinkedNodeStartCreationEditPolicy());
        installEditPolicy("constraint", new ConstraintLinkEditPolicy(false));
        installEditPolicy("nary-link", new AcceptNLinkEditPolicy(true));
        
        GmInstancePrimaryNode model = (GmInstancePrimaryNode) getModel();
        if (model.getRelatedElement() instanceof BindableInstance) {
            installEditPolicy("nary-connector", new AcceptNConnectorEditPolicy(true));
        }
        
        installEditPolicy(ModelElementDropRequest.TYPE, new InstanceElementDropEditPolicy());
    }

    @objid ("35434218-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isSelectable() {
        return false;
    }

    @objid ("3543421d-55b7-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        // Create the figure
        final GradientFigure fig = new GradientFigure();
        
        // Set style independent properties
        fig.setOpaque(true);
        
        final ToolbarLayoutWithGrab layout = new ToolbarLayoutWithGrab();
        layout.setHorizontal(false);
        layout.setStretchMinorAxis(true);
        
        fig.setLayoutManager(layout);
        
        MinimumSizeLayout.apply(fig, 100, 50);
        
        // Set style dependent properties
        refreshFromStyle(fig, getModelStyle());
        return fig;
    }

    @objid ("35434222-55b7-11e2-877f-002564c97630")
    private void updateFigureBorder(GradientFigure aFigure) {
        final Border inner = new ZoomableLineBorder(aFigure.getLineColor(), aFigure.getLineWidth());
        final Border outer = new ShadowBorder(aFigure.getLineColor(), aFigure.getLineWidth());
        final CompoundBorder b = new CompoundBorder(outer, inner);
        
        aFigure.setBorder(b);
    }

    @objid ("35434225-55b7-11e2-877f-002564c97630")
    @Override
    protected void refreshFromStyle(IFigure aFigure, IStyle style) {
        if (!switchRepresentationMode()) {
            super.refreshFromStyle(aFigure, style);
            updateFigureBorder((GradientFigure) aFigure);
        }
    }

    @objid ("3543422c-55b7-11e2-877f-002564c97630")
    @Override
    protected void refreshVisuals() {
        final GradientFigure fig = getFigure();
        final GmNodeModel gm = getModel();
        
        fig.getParent().setConstraint(fig, gm.getLayoutData());
    }

    @objid ("72b82d94-52fc-410d-b518-b5fb1d064182")
    @Override
    public GradientFigure getFigure() {
        return (GradientFigure) super.getFigure();
    }

}
