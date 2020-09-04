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

package org.modelio.diagram.editor.statik.elements.collabuse;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeRequestConstants;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeStartCreationEditPolicy;
import org.modelio.diagram.elements.core.figures.ChildFigureLineSeparator;
import org.modelio.diagram.elements.core.figures.EllipseFigure;
import org.modelio.diagram.elements.core.figures.MinimumSizeLayout;
import org.modelio.diagram.elements.core.figures.ToolbarLayoutWithGrab;
import org.modelio.diagram.elements.core.figures.borders.TLBRBorder;
import org.modelio.diagram.elements.core.link.DefaultCreateLinkEditPolicy;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.policies.DeferringCreateNodePolicy;
import org.modelio.diagram.elements.core.tools.multipoint.CreateMultiPointRequest;
import org.modelio.diagram.elements.umlcommon.constraint.ConstraintLinkEditPolicy;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.StyleKey.LinePattern;

/**
 * EditPart for a {@link GmCollaborationUsePrimaryNode}.
 * 
 * @author cma
 */
@objid ("346da27a-55b7-11e2-877f-002564c97630")
public class CollaborationUseEditPart extends AbstractNodeEditPart {
    @objid ("346da27e-55b7-11e2-877f-002564c97630")
    @Override
    protected void addChildVisual(EditPart childEditPart, int index) {
        super.addChildVisual(childEditPart, index);
        
        updateSeparations(getFigure());
    }

    /**
     * @see AbstractNodeEditPart#createEditPolicies()
     */
    @objid ("346da283-55b7-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        installEditPolicy(EditPolicy.NODE_ROLE, new DefaultCreateLinkEditPolicy());
        installEditPolicy(LinkedNodeRequestConstants.REQ_LINKEDNODE_START,
                          new LinkedNodeStartCreationEditPolicy());
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new DeferringCreateNodePolicy());
        installEditPolicy(CreateMultiPointRequest.REQ_MULTIPOINT_FIRST, new ConstraintLinkEditPolicy(false));
    }

    /**
     * Creates the Figure to be used as this part's main visuals
     * @see AbstractNodeEditPart#createFigure()
     */
    @objid ("346da287-55b7-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        // UseCaseFigure
        final EllipseFigure classFigure = new EllipseFigure();
        
        // Set style independent properties
        final ToolbarLayoutWithGrab layout = new ToolbarLayoutWithGrab();
        layout.setHorizontal(false);
        layout.setStretchMinorAxis(true);
        
        classFigure.setLayoutManager(layout);
        
        classFigure.setOpaque(true);
        classFigure.setLinePattern(LinePattern.LINE_DASH);
        
        // Set default size
        MinimumSizeLayout.apply(classFigure, 200, 75);
        
        // Set style dependent properties
        refreshFromStyle(classFigure, getModelStyle());
        return classFigure;
    }

    /**
     * Refresh the figure from the given style.
     * <p>
     * Often called in {@link #createFigure()} and after a style change.
     * 
     * @param aFigure The figure to update, should be {@link #getFigure()}.
     * @param style The style to update from, usually {@link #getModelStyle()}
     */
    @objid ("346da28d-55b7-11e2-877f-002564c97630")
    @Override
    protected void refreshFromStyle(IFigure aFigure, IStyle style) {
        if (!switchRepresentationMode()) {
            super.refreshFromStyle(aFigure, style);
        }
    }

    /**
     * Refresh this EditPart's visuals.
     * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
     */
    @objid ("346da295-55b7-11e2-877f-002564c97630")
    @Override
    protected void refreshVisuals() {
        final GmAbstractObject model = getModel();
        getFigure().getParent().setConstraint(getFigure(), model.getLayoutData());
    }

    @objid ("346da299-55b7-11e2-877f-002564c97630")
    @Override
    protected void removeChildVisual(EditPart childEditPart) {
        super.removeChildVisual(childEditPart);
        
        updateSeparations(getFigure());
    }

    /**
     * Update the separation lines between zones.
     * 
     * @param aFigure the composite figure to update.
     */
    @objid ("346da29d-55b7-11e2-877f-002564c97630")
    protected void updateSeparations(IFigure aFigure) {
        final EllipseFigure fig = (EllipseFigure) aFigure;
        // Update the zone separation lines
        final TLBRBorder zoneBorder = new TLBRBorder(fig.getLineColor(),
                                                     fig.getLineWidth(),
                                                     false,
                                                     false,
                                                     true,
                                                     false);
        
        zoneBorder.setStyle(Graphics.LINE_DASH);
        ChildFigureLineSeparator.updateSeparation(fig, zoneBorder);
    }

    @objid ("346da2a1-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isSelectable() {
        return false;
    }

}
