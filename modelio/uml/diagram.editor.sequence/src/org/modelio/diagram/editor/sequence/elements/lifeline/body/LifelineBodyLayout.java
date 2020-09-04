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

package org.modelio.diagram.editor.sequence.elements.lifeline.body;

import java.util.ArrayDeque;
import java.util.Deque;
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
import org.modelio.diagram.editor.sequence.elements.executionoccurencespecification.ExecutionOccurenceSpecificationFigure;
import org.modelio.diagram.editor.sequence.elements.executionspecification.ExecutionSpecificationFigure;
import org.modelio.diagram.editor.sequence.elements.sequencediagram.PlacementConstraint;
import org.modelio.diagram.elements.core.figures.RoundedBoxFigure;

/**
 * <p>
 * Layout Manager that is specific to the Lifeline body. It is mostly based on a {@link DelegatingLayout} (because most children can determine their Y coordinates by themselves by reading the Ob model).
 * </p>
 * <p>
 * Constraints for each child figure are instances of {@link PlacementConstraint} which is an implementation of the {@link Locator} interface. Basically, being given a desired X coordinate in absolute coordinates and a desired width, a PlacementConstraint
 * should be able to determine the correct bounds by reading informations in the Ob model.
 * </p>
 * 
 * @author fpoyer
 */
@objid ("d932d66a-55b6-11e2-877f-002564c97630")
public class LifelineBodyLayout extends AbstractLayout {
    @objid ("d932d670-55b6-11e2-877f-002564c97630")
    private static final int NESTING_STEP = 7;

    @objid ("92aa7dd7-0324-45b6-9555-1dfd17bd5520")
    private Map<IFigure, PlacementConstraint> constraints = new HashMap<>();

    @objid ("d932d672-55b6-11e2-877f-002564c97630")
    @Override
    public PlacementConstraint getConstraint(final IFigure child) {
        return this.constraints.get(child);
    }

    @objid ("d932d679-55b6-11e2-877f-002564c97630")
    @Override
    public void layout(final IFigure parent) {
        // Two time layout: first time, let every child auto-determine its Y and Height values
        // Second time: based on those values, compute "nesting" of ExecutionSpecification and ExecutionOccurenceSpecification.
        List<?> children = parent.getChildren();
        for (int i = 0; i < children.size(); i++) {
            IFigure child = (IFigure) children.get(i);
            PlacementConstraint locator = getConstraint(child);
            if (locator != null) {
                // Give the locator a "default" X reference so it can update itself at the correct Y and with the correct height.
                locator.setX(parent.getBounds().getCenter().x);
                locator.relocate(child);
            }
        }
        
        for (int i = 0; i < children.size(); i++) {
            IFigure child = (IFigure) children.get(i);
            PlacementConstraint locator = getConstraint(child);
            if (locator != null) {
                // Compute the X reference and give it to the placement constraints so they can update themselves correctly.
                // TODO: uncomment when State Invariant is added.
                // if (child instanceof StateInvariantFigure) {
                // locator.setX(parent.getBounds().getCenter().x);
                // } else
                if (child instanceof ExecutionSpecificationFigure) {
                    locator.setX(parent.getBounds().getCenter().x +
                            computeNestingForExecutionSpecification(parent,
                                    (ExecutionSpecificationFigure) child));
                } else if (child instanceof ExecutionOccurenceSpecificationFigure) {
                    locator.setX(parent.getBounds().getCenter().x +
                            computeNestingForExecutionOccurenceSpecification(parent,
                                    (ExecutionOccurenceSpecificationFigure) child));
        
                } else if (child instanceof RoundedBoxFigure) {
                    locator.setX(parent.getBounds().getCenter().x);
        
                }
                locator.relocate(child);
            }
        }
    }

    @objid ("d932d67e-55b6-11e2-877f-002564c97630")
    @Override
    public void remove(final IFigure child) {
        this.constraints.remove(child);
    }

    @objid ("d932d683-55b6-11e2-877f-002564c97630")
    @Override
    public void setConstraint(final IFigure figure, final Object constraint) {
        super.setConstraint(figure, constraint);
        if (constraint instanceof PlacementConstraint) {
            this.constraints.put(figure, (PlacementConstraint) constraint);
        }
    }

    @objid ("d932d68a-55b6-11e2-877f-002564c97630")
    @Override
    protected Dimension calculatePreferredSize(final IFigure parent, final int wHint, final int hHint) {
        Rectangle bounds = Rectangle.SINGLETON;
        bounds.setLocation(parent.getBounds().getLocation().getCopy());
        bounds.setSize(0, 0);
        int referenceX = bounds.right(); // + (ExecutionEditPart.EXECUTION_WIDTH / 4);
        // Delegate children placement to their respective locators. Ignore first child, its the visibleFigure of this execution and have no required bounds.
        List<?> children = parent.getChildren();
        for (int i = 0; i < children.size(); i++) {
            IFigure child = (IFigure) children.get(i);
            PlacementConstraint locator = getConstraint(child);
            if (locator != null) {
                // Give the X reference to the placement constraints so they can update themselves correctly.
                if (child instanceof ExecutionSpecificationFigure) {
                    locator.setX(referenceX +
                            computeNestingForExecutionSpecification(parent,
                                    (ExecutionSpecificationFigure) child));
                } else if (child instanceof ExecutionOccurenceSpecificationFigure) {
                    locator.setX(referenceX +
                            computeNestingForExecutionOccurenceSpecification(parent,
                                    (ExecutionOccurenceSpecificationFigure) child));
        
                } else {
                    locator.setX(referenceX);
                }
                bounds.union(locator.getUpdatedBounds(child));
            }
        }
        return bounds.getSize();
    }

    @objid ("d932d695-55b6-11e2-877f-002564c97630")
    private int computeNestingForExecutionSpecification(final IFigure container, final ExecutionSpecificationFigure reference) {
        // Computes the nesting to be used for this child, based on other children at the same y position.
        // 1 - State Invariant do not nest
        // 2 - ExecutionSpecification only care about other ExecutionSpecifications.
        // 3 - ExecutionOccurenceSpecifications use the same nesting as the ExecutionOccurenceSpecification at their center.
        int yReference = reference.getBounds().y;
        
        List<?> children = container.getChildren();
        Deque<IFigure> currentNesting = new ArrayDeque<>();
        for (int i = 0; i < children.size(); i++) {
            IFigure currentChild = (IFigure) children.get(i);
            if (currentChild instanceof ExecutionSpecificationFigure) {
                int currentTop = currentChild.getBounds().y;
                if (yReference <= currentTop) {
                    break;
                } else {
                    while (currentNesting.peek() != null &&
                            currentNesting.peek().getBounds().bottom() <= currentTop) {
                        currentNesting.pop();
                    }
                    currentNesting.push(currentChild);
                }
            } else {
                // Figure are ordered (ExecutionSpecificationFigure comes first, then StateInvariantFigure, then ExecutionOccurenceSpecificationFigure) so we can bail out right now.
                break;
            }
        }
        while (currentNesting.peek() != null && currentNesting.peek().getBounds().bottom() <= yReference) {
            currentNesting.pop();
        }
        return currentNesting.size() * LifelineBodyLayout.NESTING_STEP;
    }

    @objid ("d9345cfc-55b6-11e2-877f-002564c97630")
    private int computeNestingForExecutionOccurenceSpecification(final IFigure container, final ExecutionOccurenceSpecificationFigure reference) {
        int yReference = reference.getBounds().getCenter().y;
        List<?> children = container.getChildren();
        Deque<IFigure> currentNesting = new ArrayDeque<>();
        for (int i = 0; i < children.size(); i++) {
            IFigure currentChild = (IFigure) children.get(i);
            if (currentChild instanceof ExecutionSpecificationFigure) {
                if (currentChild.getBounds().y == yReference ||
                        currentChild.getBounds().bottom() == yReference) {
                    // If the reference shares its center y with the start or end of an ExecutionSpecification use the nesting for said execution specification.
                    return computeNestingForExecutionSpecification(container,
                            (ExecutionSpecificationFigure) currentChild);
                } else {
                    // Else use the regular algorithm.
                    int currentTop = currentChild.getBounds().y;
                    if (yReference <= currentTop) {
                        break;
                    } else {
                        while (currentNesting.peek() != null &&
                                currentNesting.peek().getBounds().bottom() <= currentTop) {
                            currentNesting.pop();
                        }
                        currentNesting.push(currentChild);
                    }
                }
            } else {
                // Figure are ordered (ExecutionSpecificationFigure comes first, then StateInvariantFigure, then ExecutionOccurenceSpecificationFigure) so we can bail out right now.
                break;
            }
        }
        while (currentNesting.peek() != null && currentNesting.peek().getBounds().bottom() <= yReference) {
            currentNesting.pop();
        }
        return currentNesting.isEmpty() ? 0 : (currentNesting.size() - 1) * LifelineBodyLayout.NESTING_STEP;
    }

}
