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
package org.modelio.uml.sequencediagram.editor.elements.executionspecification;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.AbstractLayout;
import org.eclipse.draw2d.DelegatingLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Locator;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.uml.sequencediagram.editor.elements.sequencediagram.PlacementConstraint;
import org.modelio.uml.sequencediagram.editor.elements.sequencediagram.SequenceDiagramLayout;

/**
 * <p>
 * Layout Manager that is specific to the Execution. It is mostly based on a {@link DelegatingLayout} (because most children can determine their Y coordinates by themselves by reading the Ob model) with the exception of the first child that is the visible
 * part of the execution and that is placed "manually" (this last part is what makes it different from the {@link SequenceDiagramLayout}).
 * </p>
 * <p>
 * Constraints for each child figure are instances of {@link PlacementConstraint} which is an implementation of the {@link Locator} interface. Basically, being given a desired X coordinate in absolute coordinates and a desired width, a PlacementConstraint
 * should be able to determine the correct bounds by reading informations in the Ob model.
 * </p>
 * 
 * @author fpoyer
 */
@objid ("d8e99866-55b6-11e2-877f-002564c97630")
public class ExecutionSpecificationLayout extends AbstractLayout {
    @objid ("d8e99868-55b6-11e2-877f-002564c97630")
    private Map<IFigure, PlacementConstraint> constraints = new HashMap<>();

    @objid ("d8e9986c-55b6-11e2-877f-002564c97630")
    @Override
    public PlacementConstraint getConstraint(final IFigure child) {
        return this.constraints.get(child);
    }

    @objid ("d8e99873-55b6-11e2-877f-002564c97630")
    @Override
    public void layout(final IFigure parent) {
        // Treat first child specifically: it is the visible part of this execution
        IFigure visibleFigure = (IFigure) parent.getChildren().get(0);
        Rectangle bounds = parent.getBounds().getCopy();
        bounds.width = ExecutionSpecificationEditPart.EXECUTION_WIDTH;
        visibleFigure.setBounds(bounds);
        // Delegate other children placement.
        List<?> children = parent.getChildren();
        for (int i = 1; i < children.size(); i++) {
            IFigure child = (IFigure) children.get(i);
            PlacementConstraint locator = getConstraint(child);
            if (locator != null) {
                // Compute the X reference and give it to the placement constraints so they can update themselves correctly.
                locator.setX(bounds.right() + (ExecutionSpecificationEditPart.EXECUTION_WIDTH / 4));
                locator.relocate(child);
            }
        }
        
    }

    @objid ("d8e99878-55b6-11e2-877f-002564c97630")
    @Override
    public void remove(final IFigure child) {
        this.constraints.remove(child);
    }

    @objid ("d8e9987d-55b6-11e2-877f-002564c97630")
    @Override
    public void setConstraint(final IFigure figure, final Object constraint) {
        super.setConstraint(figure, constraint);
        if (constraint instanceof PlacementConstraint) {
            this.constraints.put(figure, (PlacementConstraint) constraint);
        }
        
    }

    @objid ("d8e99884-55b6-11e2-877f-002564c97630")
    @Override
    protected Dimension calculatePreferredSize(final IFigure parent, final int wHint, final int hHint) {
        Rectangle bounds = new Rectangle();
        bounds.setLocation(parent.getBounds().getLocation().getCopy());
        bounds.setSize(ExecutionSpecificationEditPart.EXECUTION_WIDTH, 0);
        int referenceX = bounds.right() + (ExecutionSpecificationEditPart.EXECUTION_WIDTH / 4);
        // Delegate children placement to their respective locators. Ignore first child, its the visibleFigure of this execution and have no required bounds.
        List<?> children = parent.getChildren();
        for (int i = 1; i < children.size(); i++) {
            IFigure child = (IFigure) children.get(i);
            PlacementConstraint locator = getConstraint(child);
            if (locator != null) {
                // Give the X reference to the placement constraints so they can update themselves correctly.
                locator.setX(referenceX);
                bounds.union(locator.getUpdatedBounds(child));
            }
        }
        return bounds.getSize().getCopy();
    }

}
