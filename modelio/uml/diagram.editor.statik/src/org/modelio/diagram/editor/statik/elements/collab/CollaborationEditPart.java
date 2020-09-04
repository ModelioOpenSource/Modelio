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

package org.modelio.diagram.editor.statik.elements.collab;

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
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;
import org.modelio.diagram.elements.core.tools.multipoint.CreateMultiPointRequest;
import org.modelio.diagram.elements.umlcommon.constraint.ConstraintLinkEditPolicy;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.StyleKey.LinePattern;

/**
 * EditPart for a {@link GmCollaborationPrimaryNode}.
 * 
 * @author cma
 */
@objid ("344c10ba-55b7-11e2-877f-002564c97630")
public class CollaborationEditPart extends AbstractNodeEditPart {
    @objid ("344c10be-55b7-11e2-877f-002564c97630")
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

    @objid ("344c10c3-55b7-11e2-877f-002564c97630")
    @Override
    protected void refreshFromStyle(IFigure aFigure, IStyle style) {
        if (!switchRepresentationMode()) {
            super.refreshFromStyle(aFigure, style);
        }
    }

    @objid ("344c10ca-55b7-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        installEditPolicy(EditPolicy.NODE_ROLE, new DefaultCreateLinkEditPolicy());
        installEditPolicy(LinkedNodeRequestConstants.REQ_LINKEDNODE_START,
                          new LinkedNodeStartCreationEditPolicy());
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new DeferringCreateNodePolicy());
        installEditPolicy(CreateMultiPointRequest.REQ_MULTIPOINT_FIRST, new ConstraintLinkEditPolicy(false));
        installEditPolicy(ModelElementDropRequest.TYPE, new CollaborationElementDropEditPolicy());
    }

    @objid ("344c10cd-55b7-11e2-877f-002564c97630")
    @Override
    protected void refreshVisuals() {
        final GmAbstractObject model = getModel();
        getFigure().getParent().setConstraint(getFigure(), model.getLayoutData());
    }

    @objid ("344c10d0-55b7-11e2-877f-002564c97630")
    @Override
    protected void addChildVisual(EditPart childEditPart, int index) {
        super.addChildVisual(childEditPart, index);
        
        updateSeparations(getFigure());
    }

    @objid ("344c10d5-55b7-11e2-877f-002564c97630")
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
    @objid ("344d975b-55b7-11e2-877f-002564c97630")
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

    @objid ("344d975f-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isSelectable() {
        return false;
    }

}
