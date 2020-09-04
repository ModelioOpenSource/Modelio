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

package org.modelio.diagram.editor.activity.elements.conditional;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.modelio.diagram.editor.activity.elements.policies.CreateFlowEditPolicy;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeRequestConstants;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeStartCreationEditPolicy;
import org.modelio.diagram.elements.core.figures.MinimumSizeLayout;
import org.modelio.diagram.elements.core.figures.RoundedBoxFigure;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.policies.AutoExpandLayoutEditPolicy;
import org.modelio.diagram.elements.core.policies.DelegatingEditPolicy;
import org.modelio.diagram.elements.core.tools.multipoint.CreateMultiPointRequest;
import org.modelio.diagram.elements.umlcommon.constraint.ConstraintLinkEditPolicy;
import org.modelio.diagram.styles.core.IStyle;

/**
 * EditPart for an {@link GmConditionalPrimaryNode}.
 * 
 * @author fpoyer
 */
@objid ("2a0f059a-55b6-11e2-877f-002564c97630")
public class ConditionalEditPart extends AbstractNodeEditPart {
    /**
     * This edit part is not selectable, its parent satellite container is.
     */
    @objid ("2a0f059e-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSelectable() {
        return false;
    }

    @objid ("2a108c39-55b6-11e2-877f-002564c97630")
    @Override
    protected void addChildVisual(EditPart childEditPart, int index) {
        // First child: the header
        // All others: clause group.
        // See Gm constructor for detail
        final IFigure child = ((GraphicalEditPart) childEditPart).getFigure();
        
        final int nbChild = getFigure().getChildren().size();
        if (nbChild < 1) {
            getFigure().add(child, BorderLayout.TOP, index);
        } else {
            getFigure().add(child, BorderLayout.CENTER, index);
        }
    }

    @objid ("2a108c3e-55b6-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        
        installEditPolicy(EditPolicy.NODE_ROLE, new CreateFlowEditPolicy());
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new AutoExpandLayoutEditPolicy());
        installEditPolicy("delegate", new DelegatingEditPolicy());
        installEditPolicy(LinkedNodeRequestConstants.REQ_LINKEDNODE_START, new LinkedNodeStartCreationEditPolicy());
        installEditPolicy(CreateMultiPointRequest.REQ_MULTIPOINT_FIRST, new ConstraintLinkEditPolicy(false));
    }

    @objid ("2a108c41-55b6-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        // create the figure
        final RoundedBoxFigure fig = new RoundedBoxFigure();
        final BorderLayout figureLayout = new BorderLayout();
        fig.setLayoutManager(figureLayout);
        
        // set style independent properties
        MinimumSizeLayout.apply(fig, 120, 90);
        
        // set style dependent properties
        refreshFromStyle(fig, getModelStyle());
        
        // return the figure
        return fig;
    }

    /**
     * Refresh this EditPart's visuals.
     * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
     */
    @objid ("2a108c46-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshVisuals() {
        GmConditionalPrimaryNode conditionalModel = (GmConditionalPrimaryNode) getModel();
        getFigure().getParent().setConstraint(getFigure(), conditionalModel.getLayoutData());
    }

    @objid ("2a108c4a-55b6-11e2-877f-002564c97630")
    @Override
    protected void removeChildVisual(EditPart childEditPart) {
        // Let the child remove itself from its parent.
        IFigure child = ((GraphicalEditPart) childEditPart).getFigure();
        child.getParent().remove(child);
    }

    @objid ("2a108c4e-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshFromStyle(IFigure aFigure, IStyle style) {
        if (aFigure instanceof RoundedBoxFigure) {
            if (!switchRepresentationMode()) {
                super.refreshFromStyle(aFigure, style);
            }
        }
    }

}
