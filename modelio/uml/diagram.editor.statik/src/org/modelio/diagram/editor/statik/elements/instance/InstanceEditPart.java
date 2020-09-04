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

package org.modelio.diagram.editor.statik.elements.instance;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Border;
import org.eclipse.draw2d.CompoundBorder;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.modelio.diagram.editor.statik.elements.naryconnector.AcceptNConnectorEditPolicy;
import org.modelio.diagram.editor.statik.elements.narylink.AcceptNLinkEditPolicy;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeRequestConstants;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeStartCreationEditPolicy;
import org.modelio.diagram.elements.core.figures.ChildFigureLineSeparator;
import org.modelio.diagram.elements.core.figures.GradientFigure;
import org.modelio.diagram.elements.core.figures.MinimumSizeLayout;
import org.modelio.diagram.elements.core.figures.ToolbarLayoutWithGrab;
import org.modelio.diagram.elements.core.figures.borders.ShadowBorder;
import org.modelio.diagram.elements.core.figures.borders.TLBRBorder;
import org.modelio.diagram.elements.core.figures.borders.ZoomableLineBorder;
import org.modelio.diagram.elements.core.link.DefaultCreateLinkEditPolicy;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;
import org.modelio.diagram.elements.umlcommon.constraint.ConstraintLinkEditPolicy;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.metamodel.uml.statik.BindableInstance;

/**
 * {@link GmInstancePrimaryNode} edit part.
 * <p>
 * Creates a {@link GradientFigure} with a toolbar layout, and add separation lines between child figures by setting line borders.
 */
@objid ("3541bb77-55b7-11e2-877f-002564c97630")
public class InstanceEditPart extends AbstractNodeEditPart {
    @objid ("3541bb7b-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isSelectable() {
        return false;
    }

    @objid ("3541bb80-55b7-11e2-877f-002564c97630")
    @Override
    protected void addChildVisual(EditPart childEditPart, int index) {
        super.addChildVisual(childEditPart, index);
        
        updateSeparations(getFigure());
        updateFigureBorder(getFigure());
    }

    @objid ("3541bb85-55b7-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        
        installEditPolicy(ModelElementDropRequest.TYPE, new InstanceElementDropEditPolicy());
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
    }

    @objid ("3541bb88-55b7-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        final GradientFigure fig = new GradientFigure();
        
        // Set style independent properties
        fig.setOpaque(true);
        
        final ToolbarLayoutWithGrab layout = new ToolbarLayoutWithGrab();
        layout.setHorizontal(false);
        layout.setStretchMinorAxis(true);
        
        fig.setLayoutManager(layout);
        
        MinimumSizeLayout.apply(fig, 90, 60);
        
        // Set style dependent properties
        refreshFromStyle(fig, getModelStyle());
        return fig;
    }

    @objid ("3541bb8d-55b7-11e2-877f-002564c97630")
    @Override
    protected void refreshFromStyle(IFigure aFigure, IStyle style) {
        if (aFigure instanceof GradientFigure) {
            if (!switchRepresentationMode()) {
                super.refreshFromStyle(aFigure, style);
        
                updateSeparations((GradientFigure) aFigure);
                updateFigureBorder((GradientFigure) aFigure);
            }
        }
    }

    @objid ("3541bb94-55b7-11e2-877f-002564c97630")
    @Override
    protected void refreshVisuals() {
        IFigure aFigure = getFigure();
        GmInstancePrimaryNode model = (GmInstancePrimaryNode) getModel();
        
        aFigure.getParent().setConstraint(aFigure, model.getLayoutData());
    }

    @objid ("3541bb97-55b7-11e2-877f-002564c97630")
    @Override
    protected void removeChildVisual(EditPart childEditPart) {
        super.removeChildVisual(childEditPart);
        
        updateSeparations(getFigure());
        updateFigureBorder(getFigure());
    }

    /**
     * Update the separation lines between zones.
     * 
     * @param aFigure the composite figure to update.
     */
    @objid ("354341f9-55b7-11e2-877f-002564c97630")
    protected void updateSeparations(GradientFigure aFigure) {
        // Update the zone separation lines
        final TLBRBorder zoneBorder = new TLBRBorder(aFigure.getLineColor(),
                aFigure.getLineWidth(),
                false,
                false,
                true,
                false);
        
        ChildFigureLineSeparator.updateSeparation(aFigure, zoneBorder);
    }

    @objid ("354341fd-55b7-11e2-877f-002564c97630")
    private void updateFigureBorder(GradientFigure aFigure) {
        final Border inner = new ZoomableLineBorder(aFigure.getLineColor(), aFigure.getLineWidth());
        final Border outer = new ShadowBorder(aFigure.getLineColor(), aFigure.getLineWidth());
        final CompoundBorder b = new CompoundBorder(outer, inner);
        
        aFigure.setBorder(b);
    }

    @objid ("d2481e42-087d-4250-b44a-8f3815c37898")
    @Override
    public GradientFigure getFigure() {
        return (GradientFigure) super.getFigure();
    }

}
