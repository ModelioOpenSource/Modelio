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

package org.modelio.uml.activitydiagram.editor.elements.decisionmerge;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.modelio.diagram.elements.common.portcontainer.PortContainerEditPart;
import org.modelio.diagram.elements.common.portcontainer.PortContainerLayout;

/**
 * Edit part for {@link GmDecisionMerge}.
 */
@objid ("2a39bf1a-55b6-11e2-877f-002564c97630")
public class DecisionMergeEditPart extends PortContainerEditPart {
    @objid ("2a39bf1e-55b6-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        IFigure fig = new DecisionMergePortContainerFigure();
        // Style independent properties.
        fig.setLayoutManager(new PortContainerLayout());
        // DEBUG
        // fig.setOpaque(true);
        // fig.setBackgroundColor(ColorConstants.red);
        // TODO: this container should NOT have a min size, min size should be
        // computed based on min size of children!
        fig.setMinimumSize(new Dimension(10, 10));
        // Style dependent properties.
        refreshFromStyle(fig, getModelStyle());
        return fig;
    }

    @objid ("2a39bf23-55b6-11e2-877f-002564c97630")
    @Override
    protected void addChildVisual(EditPart childEditPart, int index) {
        super.addChildVisual(childEditPart, index);
        if (childEditPart.getModel() instanceof GmDecisionMergePrimaryNode) {
            DecisionMergePortContainerFigure fig = (DecisionMergePortContainerFigure) getFigure();
            fig.setMainNodeFigure(((AbstractGraphicalEditPart) childEditPart).getFigure());
        } else if (childEditPart.getModel() instanceof GmInputBehaviourText) {
            DecisionMergePortContainerFigure fig = (DecisionMergePortContainerFigure) getFigure();
            fig.setInputBehaviourFigure(((AbstractGraphicalEditPart) childEditPart).getFigure());
        }
    }

    @objid ("2a39bf28-55b6-11e2-877f-002564c97630")
    @Override
    protected void removeChildVisual(EditPart childEditPart) {
        super.removeChildVisual(childEditPart);
        if (childEditPart.getModel() instanceof GmDecisionMergePrimaryNode) {
            DecisionMergePortContainerFigure fig = (DecisionMergePortContainerFigure) getFigure();
            fig.setMainNodeFigure(null);
        } else if (childEditPart.getModel() instanceof GmInputBehaviourText) {
            DecisionMergePortContainerFigure fig = (DecisionMergePortContainerFigure) getFigure();
            fig.setInputBehaviourFigure(null);
        
        }
    }

}
