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
package org.modelio.uml.activitydiagram.editor.elements.initial;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.EditPolicy;
import org.eclipse.swt.graphics.Color;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeRequestConstants;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeStartCreationEditPolicy;
import org.modelio.diagram.elements.core.figures.EllipseFigure;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.tools.multipoint.CreateMultiPointRequest;
import org.modelio.diagram.elements.umlcommon.constraint.ConstraintLinkEditPolicy;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.uml.activitydiagram.editor.elements.policies.CreateFlowEditPolicy;

/**
 * EditPart for an {@link GmInitialPrimaryNode}.
 * 
 * @author fpoyer
 */
@objid ("2aa48f03-55b6-11e2-877f-002564c97630")
public final class InitialEditPart extends AbstractNodeEditPart {
    @objid ("2aa48f07-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSelectable() {
        return false;
    }

    @objid ("2aa48f0c-55b6-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        installEditPolicy(EditPolicy.NODE_ROLE, new CreateFlowEditPolicy());
        installEditPolicy(LinkedNodeRequestConstants.REQ_LINKEDNODE_START,
                new LinkedNodeStartCreationEditPolicy());
        installEditPolicy(CreateMultiPointRequest.REQ_MULTIPOINT_FIRST, new ConstraintLinkEditPolicy(false));
        
    }

    @objid ("2aa48f0f-55b6-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        // create the figure
        final FilledEllipseFigure fig = new FilledEllipseFigure();
        
        // set style independent properties
        fig.setPreferredSize(20, 20);
        fig.setMinimumSize(new Dimension(20, 20));
        
        // set style dependent properties
        refreshFromStyle(fig, getModelStyle());
        
        // return the figure
        return fig;
    }

    @objid ("2aa48f14-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshFromStyle(IFigure aFigure, IStyle style) {
        if (aFigure instanceof EllipseFigure) {
            if (!switchRepresentationMode()) {
                super.refreshFromStyle(aFigure, style);
            }
        }
        
    }

    @objid ("2aa6157f-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshVisuals() {
        GmInitialPrimaryNode initialNodeModel = (GmInitialPrimaryNode) this.getModel();
        getFigure().getParent().setConstraint(getFigure(), initialNodeModel.getLayoutData());
        
    }

    /**
     * Ellipse figure where the line color and the fill color are the same.
     * 
     * @author cmarin
     */
    @objid ("2aa61582-55b6-11e2-877f-002564c97630")
    private static class FilledEllipseFigure extends EllipseFigure {
        /**
         * Public default constructor.
         */
        @objid ("2aa61587-55b6-11e2-877f-002564c97630")
        public  FilledEllipseFigure() {
            
        }

        @objid ("2aa6158a-55b6-11e2-877f-002564c97630")
        @Override
        public void setFillColor(Color fillColor) {
            super.setFillColor(fillColor);
            
            setLineColor(fillColor);
            
        }

    }

}
