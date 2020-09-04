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

package org.modelio.diagram.editor.state.elements.initialstate;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.EditPolicy;
import org.eclipse.swt.graphics.Color;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeRequestConstants;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeStartCreationEditPolicy;
import org.modelio.diagram.elements.core.figures.EllipseFigure;
import org.modelio.diagram.elements.core.link.DefaultCreateLinkEditPolicy;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.tools.multipoint.CreateMultiPointRequest;
import org.modelio.diagram.elements.umlcommon.constraint.ConstraintLinkEditPolicy;
import org.modelio.diagram.styles.core.IStyle;

/**
 * EditPart for a {@link GmInitialStatePrimaryNode} node.
 * 
 * @author fpoyer
 */
@objid ("f54b8679-55b6-11e2-877f-002564c97630")
public class InitialStateEditPart extends AbstractNodeEditPart {
    @objid ("f54b867d-55b6-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        // create the figure
        EllipseFigure fig = new InitialStateFigure();
        
        // set style independent properties
        fig.setPreferredSize(20, 20);
        fig.setMinimumSize(new Dimension(20, 20));
        
        // set style dependent properties
        refreshFromStyle(fig, getModelStyle());
        
        // return the figure
        return fig;
    }

    @objid ("f54b8682-55b6-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        installEditPolicy(EditPolicy.NODE_ROLE, new DefaultCreateLinkEditPolicy());
        installEditPolicy(LinkedNodeRequestConstants.REQ_LINKEDNODE_START,
                          new LinkedNodeStartCreationEditPolicy());
        installEditPolicy(CreateMultiPointRequest.REQ_MULTIPOINT_FIRST, new ConstraintLinkEditPolicy(false));
    }

    @objid ("f54b8685-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshVisuals() {
        GmInitialStatePrimaryNode initialModel = (GmInitialStatePrimaryNode) this.getModel();
        this.getFigure().getParent().setConstraint(this.getFigure(), initialModel.getLayoutData());
    }

    @objid ("f54b8688-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSelectable() {
        return false;
    }

    @objid ("f54b868d-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshFromStyle(IFigure aFigure, IStyle style) {
        if (aFigure instanceof EllipseFigure) {
            if (switchRepresentationMode()) {
                return;
            }
        }
        
        super.refreshFromStyle(aFigure, style);
    }

    /**
     * Ellipse figure where the line color and the fill color are the same.
     * 
     * @author cmarin
     */
    @objid ("f54b8694-55b6-11e2-877f-002564c97630")
    private static class InitialStateFigure extends EllipseFigure {
        @objid ("f54b8699-55b6-11e2-877f-002564c97630")
        public InitialStateFigure() {
        }

        @objid ("f54b869b-55b6-11e2-877f-002564c97630")
        @Override
        public void setFillColor(Color fillColor) {
            super.setFillColor(fillColor);
            
            setLineColor(fillColor);
        }

    }

}
