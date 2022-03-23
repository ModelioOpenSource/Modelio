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
package org.modelio.uml.deploymentdiagram.editor.elements.node;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeRequestConstants;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeStartCreationEditPolicy;
import org.modelio.diagram.elements.core.figures.ChildFigureLineSeparator;
import org.modelio.diagram.elements.core.figures.GradientFigure;
import org.modelio.diagram.elements.core.figures.MinimumSizeLayout;
import org.modelio.diagram.elements.core.figures.ToolbarLayoutWithGrab;
import org.modelio.diagram.elements.core.figures.borders.TLBRBorder;
import org.modelio.diagram.elements.core.figures.box.Box3DFigure;
import org.modelio.diagram.elements.core.link.DefaultCreateLinkEditPolicy;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.policies.DeferringCreateNodePolicy;
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;
import org.modelio.diagram.elements.core.tools.multipoint.CreateMultiPointRequest;
import org.modelio.diagram.elements.umlcommon.constraint.ConstraintLinkEditPolicy;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.uml.statikdiagram.editor.elements.classifier.ClassifierElementDropEditPolicy;

/**
 * {@link GmNodePrimaryNode} edit part.
 * <p>
 * Creates a {@link GradientFigure} with a toolbar layout, and add separation lines between child figures by setting line borders.
 */
@objid ("97469e08-55b6-11e2-877f-002564c97630")
public class NodeEditPart extends AbstractNodeEditPart {
    @objid ("97469e0c-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSelectable() {
        return false;
    }

    @objid ("97469e11-55b6-11e2-877f-002564c97630")
    @Override
    protected void addChildVisual(EditPart childEditPart, int index) {
        super.addChildVisual(childEditPart, index);
        
        updateSeparations(getFigure());
        
    }

    @objid ("97469e16-55b6-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        
        installEditPolicy(ModelElementDropRequest.TYPE, new ClassifierElementDropEditPolicy());
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new DeferringCreateNodePolicy());
        installEditPolicy(EditPolicy.NODE_ROLE, new DefaultCreateLinkEditPolicy());
        installEditPolicy(LinkedNodeRequestConstants.REQ_LINKEDNODE_START,
                new LinkedNodeStartCreationEditPolicy());
        installEditPolicy(CreateMultiPointRequest.REQ_MULTIPOINT_FIRST, new ConstraintLinkEditPolicy(false));
        
    }

    @objid ("9748247b-55b6-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        final Box3DFigure nodeFigure = new Box3DFigure();
        
        // Set style independent properties
        nodeFigure.setOpaque(true);
        
        final ToolbarLayoutWithGrab layout = new ToolbarLayoutWithGrab();
        layout.setHorizontal(false);
        layout.setStretchMinorAxis(true);
        
        nodeFigure.setLayoutManager(layout);
        
        MinimumSizeLayout.apply(nodeFigure, 150, 100);
        
        // set style dependent properties
        refreshFromStyle(nodeFigure, getModelStyle());
        
        // return the figure
        return nodeFigure;
    }

    @objid ("97482480-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshFromStyle(IFigure aFigure, IStyle style) {
        if (aFigure instanceof GradientFigure) {
            if (!switchRepresentationMode()) {
                super.refreshFromStyle(aFigure, style);
        
                updateSeparations(aFigure);
            }
        }
        
    }

    @objid ("97482487-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshVisuals() {
        IFigure classFigure = getFigure();
        GmNodePrimaryNode classModel = (GmNodePrimaryNode) getModel();
        
        classFigure.getParent().setConstraint(classFigure, classModel.getLayoutData());
        
    }

    @objid ("9748248a-55b6-11e2-877f-002564c97630")
    @Override
    protected void removeChildVisual(EditPart childEditPart) {
        super.removeChildVisual(childEditPart);
        
        updateSeparations(getFigure());
        
    }

    /**
     * Update the separation lines between zones.
     * @param aFigure the composite figure to update.
     */
    @objid ("9748248e-55b6-11e2-877f-002564c97630")
    protected void updateSeparations(IFigure aFigure) {
        final GradientFigure stateFig = (GradientFigure) aFigure;
        // Update the zone separation lines
        final TLBRBorder zoneBorder = new TLBRBorder(stateFig.getLineColor(),
                stateFig.getLineWidth(),
                false,
                false,
                true,
                false);
        
        ChildFigureLineSeparator.updateSeparation(stateFig, zoneBorder);
        
    }

}
