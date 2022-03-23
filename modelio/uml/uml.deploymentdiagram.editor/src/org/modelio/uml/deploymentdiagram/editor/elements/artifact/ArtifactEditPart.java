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
package org.modelio.uml.deploymentdiagram.editor.elements.artifact;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Border;
import org.eclipse.draw2d.CompoundBorder;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
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
import org.modelio.diagram.elements.core.policies.DefaultElementDropEditPolicy;
import org.modelio.diagram.elements.core.policies.DeferringCreateNodePolicy;
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;
import org.modelio.diagram.elements.core.tools.multipoint.CreateMultiPointRequest;
import org.modelio.diagram.elements.umlcommon.constraint.ConstraintLinkEditPolicy;
import org.modelio.diagram.styles.core.IStyle;

/**
 * {@link GmArtifactPrimaryNode} edit part.
 * <p>
 * Creates a {@link GradientFigure} with a toolbar layout, and add separation lines between child figures by setting line borders.
 */
@objid ("971135fb-55b6-11e2-877f-002564c97630")
public class ArtifactEditPart extends AbstractNodeEditPart {
    @objid ("971135ff-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSelectable() {
        return false;
    }

    @objid ("97113604-55b6-11e2-877f-002564c97630")
    @Override
    protected void addChildVisual(EditPart childEditPart, int index) {
        super.addChildVisual(childEditPart, index);
        
        updateSeparations(getFigure());
        updateFigureBorder(getFigure());
        
    }

    @objid ("97113609-55b6-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        
        installEditPolicy(ModelElementDropRequest.TYPE, new DefaultElementDropEditPolicy());
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new DeferringCreateNodePolicy());
        installEditPolicy(EditPolicy.NODE_ROLE, new DefaultCreateLinkEditPolicy());
        installEditPolicy(LinkedNodeRequestConstants.REQ_LINKEDNODE_START,
                new LinkedNodeStartCreationEditPolicy());
        installEditPolicy(CreateMultiPointRequest.REQ_MULTIPOINT_FIRST, new ConstraintLinkEditPolicy(false));
        
    }

    @objid ("9711360c-55b6-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        final GradientFigure artifactFigure = new GradientFigure();
        
        // Set style independent properties
        artifactFigure.setOpaque(true);
        
        final ToolbarLayoutWithGrab layout = new ToolbarLayoutWithGrab();
        layout.setHorizontal(false);
        layout.setStretchMinorAxis(true);
        
        artifactFigure.setLayoutManager(layout);
        
        MinimumSizeLayout.apply(artifactFigure, 150, 100);
        
        // Set style dependent properties
        refreshFromStyle(artifactFigure, getModelStyle());
        return artifactFigure;
    }

    @objid ("97113611-55b6-11e2-877f-002564c97630")
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

    @objid ("9712bc9e-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshVisuals() {
        IFigure classFigure = getFigure();
        GmArtifactPrimaryNode classModel = (GmArtifactPrimaryNode) getModel();
        
        classFigure.getParent().setConstraint(classFigure, classModel.getLayoutData());
        
    }

    @objid ("9712bca1-55b6-11e2-877f-002564c97630")
    @Override
    protected void removeChildVisual(EditPart childEditPart) {
        super.removeChildVisual(childEditPart);
        
        updateSeparations(getFigure());
        updateFigureBorder(getFigure());
        
    }

    /**
     * Update the separation lines between zones.
     * @param stateFig the composite figure to update.
     */
    @objid ("9712bca5-55b6-11e2-877f-002564c97630")
    protected void updateSeparations(GradientFigure stateFig) {
        // Update the zone separation lines
        final TLBRBorder zoneBorder = new TLBRBorder(stateFig.getLineColor(),
                stateFig.getLineWidth(),
                false,
                false,
                true,
                false);
        
        ChildFigureLineSeparator.updateSeparation(stateFig, zoneBorder);
        
    }

    @objid ("9712bca9-55b6-11e2-877f-002564c97630")
    private void updateFigureBorder(GradientFigure classFig) {
        final Border inner = new ZoomableLineBorder(classFig.getLineColor(), classFig.getLineWidth());
        final Border outer = new ShadowBorder(classFig.getLineColor(), classFig.getLineWidth());
        final CompoundBorder b = new CompoundBorder(outer, inner);
        
        classFig.setBorder(b);
        
    }

    @objid ("b7ee5cd4-9511-43e8-809e-906c7e62c1d0")
    @Override
    public GradientFigure getFigure() {
        return (GradientFigure) super.getFigure();
    }

}
