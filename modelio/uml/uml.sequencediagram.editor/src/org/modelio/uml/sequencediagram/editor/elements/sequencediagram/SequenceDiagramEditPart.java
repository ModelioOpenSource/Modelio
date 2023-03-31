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
package org.modelio.uml.sequencediagram.editor.elements.sequencediagram;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.modelio.diagram.elements.common.abstractdiagram.AbstractDiagramEditPart;
import org.modelio.diagram.elements.common.abstractdiagram.DiagramElementDropEditPolicy;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeFinishCreationEditPolicy;
import org.modelio.diagram.elements.common.linkednode.LinkedNodeRequestConstants;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;
import org.modelio.diagram.styles.core.IStyle;

/**
 * EditPart for Sequence diagram background.
 * 
 * @author fpoyer
 */
@objid ("d97c146d-55b6-11e2-877f-002564c97630")
public class SequenceDiagramEditPart extends AbstractDiagramEditPart {
    @objid ("d97c1471-55b6-11e2-877f-002564c97630")
    @Override
    protected void doAddChildVisual(final EditPart childEditPart, final int index) {
        GmModel model = (GmModel) childEditPart.getModel();
        Object layoutData = model.getLayoutData();
        if (layoutData == null) {
            // If no constraint defined, create a default one.
            layoutData = new Rectangle(0, 0, -1, -1);
            model.setLayoutData(layoutData);
        }
        if (layoutData instanceof Rectangle && childEditPart instanceof IPlacementConstraintProvider) {
            // On first time, fix the constraint.
            layoutData = ((IPlacementConstraintProvider) childEditPart).createPlacementConstraint(model,
                    ((Rectangle) layoutData).x,
                    ((Rectangle) layoutData).y,
                    ((Rectangle) layoutData).width,
                    ((Rectangle) layoutData).height);
            model.setLayoutData(layoutData);
        }
        IFigure child = ((GraphicalEditPart) childEditPart).getFigure();
        getContentPane().add(child, layoutData, index);
        
    }

    @objid ("d97c1478-55b6-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        // Policy to create notes
        installEditPolicy(LinkedNodeRequestConstants.REQ_LINKEDNODE_END,
                new LinkedNodeFinishCreationEditPolicy());
        
        // Layout policy specific to Sequen ce diagram.
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new SequenceDiagramLayoutPolicy());
        
        // Policy to create Lost, Found and Creation messages.
        // installEditPolicy(EditPolicy.NODE_ROLE, new CreateLinkEditPolicy());
        installEditPolicy(ModelElementDropRequest.TYPE, new DiagramElementDropEditPolicy());
        
    }

    @objid ("d97c147b-55b6-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        Figure diagramFigure = new SequenceDiagramFigure();
        
        // Set style independent properties
        diagramFigure.setLayoutManager(new SequenceDiagramLayout());
        
        // Set style dependent properties
        IStyle style = ((GmAbstractObject) this.getModel()).getDisplayedStyle();
        refreshFromStyle(diagramFigure, style);
        return diagramFigure;
    }

    @objid ("d97c1480-55b6-11e2-877f-002564c97630")
    @Override
    public void activate() {
        super.activate();
        ((IGmDiagram) this.getModel()).refreshAllFromObModel();
        
    }

    /**
     * Disable inherited{@link #createLayoutPolicyDecorator(EditPolicy)} for sequence diagrams.
     * @param layoutPolicy the layout edit policy. expected to be a {@link ConstrainedLayoutEditPolicy} by default implementation.
     * @return the created policy.
     * @since 5.1.0
     */
    @objid ("b841cf90-7827-4603-9c21-81acd6b84e49")
    protected EditPolicy createLayoutPolicyDecorator(EditPolicy layoutPolicy) {
        return layoutPolicy;
    }

}
