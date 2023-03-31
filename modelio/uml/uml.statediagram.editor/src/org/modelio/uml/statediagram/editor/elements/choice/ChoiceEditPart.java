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
package org.modelio.uml.statediagram.editor.elements.choice;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Dimension;
import org.modelio.diagram.elements.core.link.anchors.fixed2.core.IFixedNodeAnchorProvider;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.uml.statediagram.editor.elements.common.state.AbstractStateEditPart;
import org.modelio.uml.statediagram.editor.elements.figures.DiamondFigure;

/**
 * EditPart for an Choice Node.
 * 
 * @author fpoyer
 */
@objid ("f4eff8fa-55b6-11e2-877f-002564c97630")
public class ChoiceEditPart extends AbstractStateEditPart {
    @objid ("e07a9c93-c073-4eb7-bb3b-7a2e93cecbcf")
    Label labelFigure;

    @objid ("74b360e8-be39-4537-a6a4-0311545f29d3")
    private IFixedNodeAnchorProvider nodeAnchorProvider;

    @objid ("f4eff8ff-55b6-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        // create the figure
        DiamondFigure fig = new DiamondFigure();
        
        // set style independent properties
        fig.setPreferredSize(20, 30);
        fig.setMinimumSize(new Dimension(20, 30));
        
        // set style dependent properties
        refreshFromStyle(fig, getModelStyle());
        
        // return the figure
        return fig;
    }

    @objid ("f4eff907-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshVisuals() {
        GmChoicePrimaryNode choiceModel = (GmChoicePrimaryNode) this.getModel();
        this.getFigure().getParent().setConstraint(this.getFigure(), choiceModel.getLayoutData());
        
    }

    @objid ("f4eff90a-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSelectable() {
        return false;
    }

    @objid ("f4eff90f-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshFromStyle(IFigure aFigure, IStyle style) {
        if (aFigure instanceof DiamondFigure) {
            if (switchRepresentationMode()) {
                return;
            }
        }
        
        super.refreshFromStyle(aFigure, style);
        
    }

}
