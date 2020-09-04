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

package org.modelio.diagram.editor.sequence.elements.lifeline;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.modelio.diagram.editor.sequence.elements.sequencediagram.GmSequenceDiagram;
import org.modelio.diagram.editor.sequence.elements.sequencediagram.IPlacementConstraintProvider;
import org.modelio.diagram.editor.sequence.elements.sequencediagram.PlacementConstraint;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.policies.AutoExpandLayoutEditPolicy;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.metamodel.uml.behavior.interactionModel.Lifeline;

/**
 * EditPart of a Lifeline in a sequence diagram.
 */
@objid ("d94b4077-55b6-11e2-877f-002564c97630")
public class LifelineEditPart extends AbstractNodeEditPart implements IPlacementConstraintProvider {
    /**
     * Creates and returns a PlacementConstraint for the given model.
     * 
     * @param model the graphic model for which a constraint is to be created.
     * @param x the desired X coordinate in coordinates relative to the parent figure.
     * @param y the desired Y coordinate in coordinates relative to the parent figure.
     * @param width the desired width of the figure.
     * @param height the desired height of the figure.
     * @return a new PlacementConstraint.
     */
    @objid ("d94b407b-55b6-11e2-877f-002564c97630")
    @Override
    public PlacementConstraint createPlacementConstraint(final GmModel model, final int x, final int y, final int width, final int height) {
        return new LifelinePlacementConstraint((Lifeline) model.getRelatedElement(),
                                x,
                                y,
                                width,
                                height,
                                (GmSequenceDiagram) model.getDiagram());
    }

    @objid ("d94b408d-55b6-11e2-877f-002564c97630")
    @Override
    protected void addChildVisual(EditPart childEditPart, int index) {
        IFigure child = ((GraphicalEditPart) childEditPart).getFigure();
        GmNodeModel childModel = (GmNodeModel) childEditPart.getModel();
        if (childModel.getLayoutData() == null &&
                GmLifeline.HEADER_ROLE.equals(childModel.getRoleInComposition())) {
            // Header
            childModel.setLayoutData(BorderLayout.TOP);
        } else if (childModel.getLayoutData() == null &&
                GmLifeline.BODY_ROLE.equals(childModel.getRoleInComposition())) {
            // Body
            childModel.setLayoutData(BorderLayout.CENTER);
        }
        getContentPane().add(child, childModel.getLayoutData(), index);
    }

    @objid ("d94b4092-55b6-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        LifelineFigure lifelineFigure = new LifelineFigure();
        
        // Set style independent properties
        lifelineFigure.setLayoutManager(new BorderLayout());
        
        // Set style dependent properties
        IStyle style = ((GmAbstractObject) getModel()).getDisplayedStyle();
        refreshFromStyle(lifelineFigure, style);
        return lifelineFigure;
    }

    @objid ("d94cc6f9-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshVisuals() {
        GmLifeline lifelineModel = (GmLifeline) getModel();
        getFigure().getParent().setConstraint(getFigure(), lifelineModel.getLayoutData());
    }

    /**
     * Override needed because dumbass BorderLayout can't return a meaningful value when getConstraint is called... :(
     */
    @objid ("d94cc6fc-55b6-11e2-877f-002564c97630")
    @Override
    protected void reorderChild(final EditPart child, final int index) {
        IFigure childFigure = ((GraphicalEditPart) child).getFigure();
        super.reorderChild(child, index);
        setLayoutConstraint(child, childFigure, ((GmAbstractObject) child.getModel()).getLayoutData());
    }

    @objid ("3f75c074-6fc1-41a5-9692-f14509f56479")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new AutoExpandLayoutEditPolicy());
    }

}
