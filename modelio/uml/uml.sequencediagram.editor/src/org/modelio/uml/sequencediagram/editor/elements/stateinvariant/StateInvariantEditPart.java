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
package org.modelio.uml.sequencediagram.editor.elements.stateinvariant;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeRequestConstants;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeStartCreationEditPolicy;
import org.modelio.diagram.elements.core.figures.RoundedBoxFigure;
import org.modelio.diagram.elements.core.link.DefaultCreateLinkEditPolicy;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.tools.multipoint.CreateMultiPointRequest;
import org.modelio.diagram.elements.umlcommon.constraint.ConstraintLinkEditPolicy;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.metamodel.uml.behavior.interactionModel.StateInvariant;
import org.modelio.uml.sequencediagram.editor.elements.sequencediagram.GmSequenceDiagram;
import org.modelio.uml.sequencediagram.editor.elements.sequencediagram.IPlacementConstraintProvider;
import org.modelio.uml.sequencediagram.editor.elements.sequencediagram.PlacementConstraint;

/**
 * EditPart (ie Controller in the GEF model) for StateInvariant in Sequence Diagram.
 */
@objid ("d99a9919-55b6-11e2-877f-002564c97630")
public class StateInvariantEditPart extends AbstractNodeEditPart implements IPlacementConstraintProvider {
    /**
     * Default height to use for a StateInvariant specification if none is given by user.
     */
    @objid ("d99c1f7c-55b6-11e2-877f-002564c97630")
    public static final int DEFAULT_STATEINVARIANT_HEIGHT = 50;

    /**
     * Default width of a StateInvariant.
     */
    @objid ("d99c1f7f-55b6-11e2-877f-002564c97630")
    public static final int DEFAULT_STATEINVARIANT_WIDTH = 50;

    /**
     * Creates and returns a PlacementConstraint for the given model.
     * @param model the graphic model for which a constraint is to be created.
     * @param x the desired X coordinate in coordinates relative to the parent figure.
     * @param y the desired Y coordinates relative to the parent figure.
     * @param width the desired width of the figure.
     * @param height the desired height of the figure.
     * @return a new PlacementConstraint.
     */
    @objid ("d99c1f82-55b6-11e2-877f-002564c97630")
    @Override
    public PlacementConstraint createPlacementConstraint(final GmModel model, final int x, final int y, final int width, final int height) {
        return new StateInvariantPlacementConstraint((StateInvariant) model.getRelatedElement(),
                                x,
                                y,
                                width,
                                height,
                                (GmSequenceDiagram) model.getDiagram());
        
    }

    @objid ("d99c1f94-55b6-11e2-877f-002564c97630")
    @Override
    protected void addChildVisual(final EditPart childEditPart, final int index) {
        IFigure child = ((GraphicalEditPart) childEditPart).getFigure();
        getContentPane().add(child, BorderLayout.CENTER, index);
        
    }

    @objid ("d99c1f9b-55b6-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        installEditPolicy(EditPolicy.NODE_ROLE, new DefaultCreateLinkEditPolicy(false));
        installEditPolicy(LinkedNodeRequestConstants.REQ_LINKEDNODE_START,
                new LinkedNodeStartCreationEditPolicy());
        installEditPolicy(CreateMultiPointRequest.REQ_MULTIPOINT_FIRST, new ConstraintLinkEditPolicy(false));
        
    }

    @objid ("d99c1f9e-55b6-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        // Create the figure
        RoundedBoxFigure fig = new RoundedBoxFigure();
        
        // Set style independent properties
        fig.setOpaque(true);
        fig.setLayoutManager(new BorderLayout());
        
        // Set style dependent properties
        IStyle style = ((GmAbstractObject) this.getModel()).getDisplayedStyle();
        refreshFromStyle(fig, style);
        return fig;
    }

    @objid ("d99c1fa3-55b6-11e2-877f-002564c97630")
    @Override
    protected void refreshVisuals() {
        GmAbstractObject model = getModel();
        getFigure().getParent().setConstraint(getFigure(), model.getLayoutData());
        
    }

}
