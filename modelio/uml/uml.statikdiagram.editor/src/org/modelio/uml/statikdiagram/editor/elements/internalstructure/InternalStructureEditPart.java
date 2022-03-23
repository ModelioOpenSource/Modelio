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
package org.modelio.uml.statikdiagram.editor.elements.internalstructure;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.modelio.diagram.elements.core.figures.RectangularFigure;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.policies.AutoExpandLayoutEditPolicy;

/**
 * {@link GmInternalStructure} edit part.
 */
@objid ("35942168-55b7-11e2-877f-002564c97630")
public class InternalStructureEditPart extends AbstractNodeEditPart {
    @objid ("3594216f-55b7-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        RectangularFigure f = new RectangularFigure();
        f.setDrawTop(true);
        f.setDrawLeft(false);
        f.setDrawBottom(false);
        f.setDrawRight(false);
        f.setOpaque(false);
        f.setLayoutManager(new BorderLayout());
        refreshFromStyle(f, getModelStyle());
        return f;
    }

    @objid ("3595a7bc-55b7-11e2-877f-002564c97630")
    @Override
    protected void addChildVisual(final EditPart childEditPart, final int index) {
        IFigure child = ((GraphicalEditPart) childEditPart).getFigure();
        getContentPane().add(child, BorderLayout.CENTER, index);
        
    }

    @objid ("3595a7c3-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isSelectable() {
        // Already selected, return true
        if (getViewer().getSelectedEditParts().contains(this)) {
            return true;
        }
        
        // Allow selection only if the composition parent was already selected
        EditPart parent = getParent();
        while (parent != null) {
            if (parent.isSelectable()) {
                return (parent.getSelected() != EditPart.SELECTED_NONE);
            }
            parent = parent.getParent();
        }
        return false;
    }

    @objid ("3595a7c8-55b7-11e2-877f-002564c97630")
    @Override
    protected void refreshVisuals() {
        IGmObject model = getModel();
        if (model.getLayoutData() != null) {
            getFigure().getParent().setConstraint(getFigure(), model.getLayoutData());
        }
        
    }

    @objid ("9dd05912-c68e-4c47-95a7-daf1b03a1d59")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new AutoExpandLayoutEditPolicy());
        
    }

}
