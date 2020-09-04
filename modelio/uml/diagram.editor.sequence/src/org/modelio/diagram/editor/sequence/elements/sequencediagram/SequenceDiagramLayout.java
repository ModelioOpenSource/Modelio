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

package org.modelio.diagram.editor.sequence.elements.sequencediagram;

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

/**
 * <p>
 * Layout Manager that is specific to the Sequence diagram. It is mostly based on a {@link DelegatingLayout} (because most children can determine their Y coordinates by themselves by reading the Ob model).
 * </p>
 * <p>
 * Constraints for each child figure are instances of {@link PlacementConstraint} which is an implementation of the {@link Locator} interface. Basically, being given a desired X coordinate in absolute coordinates and a desired width, a PlacementConstraint
 * should be able to determine the correct bounds by reading informations in the Ob model.
 * </p>
 * 
 * @author fpoyer
 */
@objid ("d97c1495-55b6-11e2-877f-002564c97630")
public class SequenceDiagramLayout extends AbstractLayout {
    @objid ("d97c1497-55b6-11e2-877f-002564c97630")
    private Map<IFigure, PlacementConstraint> selfLocatingConstraints = new HashMap<>();

    @objid ("ea5c478c-5b08-4384-bc74-9ada7d3fc85a")
    private Map<IFigure, Rectangle> simpleConstraints = new HashMap<>();

    @objid ("d97d9afc-55b6-11e2-877f-002564c97630")
    @Override
    public Object getConstraint(final IFigure child) {
        return this.selfLocatingConstraints.get(child) != null ? this.selfLocatingConstraints.get(child)
                        : this.simpleConstraints.get(child);
    }

    @objid ("d97d9b03-55b6-11e2-877f-002564c97630")
    @Override
    public void layout(final IFigure parent) {
        List<?> children = parent.getChildren();
        for (int i = 0; i < children.size(); i++) {
            IFigure child = (IFigure) children.get(i);
            PlacementConstraint locator = getLocator(child);
            if (locator != null) {
                // TODO fix the X coordinate to prevent overlapping... use a X mapper?
                locator.relocate(child);
            } else {
                Rectangle simpleConstraint = getSimpleConstraint(child);
                if (simpleConstraint != null) {
                    if (simpleConstraint.width == -1 || simpleConstraint.height == -1) {
                        simpleConstraint = simpleConstraint.getCopy();
                        Dimension childPreferredSize = child.getPreferredSize(simpleConstraint.width,
                                simpleConstraint.height);
                        if (simpleConstraint.width == -1) {
                            simpleConstraint.width = childPreferredSize.width;
                        }
                        if (simpleConstraint.height == -1) {
                            simpleConstraint.height = childPreferredSize.height;
                        }
                    }
                    child.setBounds(simpleConstraint);
                }
            }
        }
    }

    @objid ("d97d9b08-55b6-11e2-877f-002564c97630")
    @Override
    public void remove(final IFigure child) {
        this.selfLocatingConstraints.remove(child);
        this.simpleConstraints.remove(child);
    }

    @objid ("d97d9b0d-55b6-11e2-877f-002564c97630")
    @Override
    public void setConstraint(final IFigure figure, final Object constraint) {
        super.setConstraint(figure, constraint);
        if (constraint instanceof PlacementConstraint) {
            this.selfLocatingConstraints.put(figure, (PlacementConstraint) constraint);
        } else if (constraint instanceof Rectangle) {
            this.simpleConstraints.put(figure, (Rectangle) constraint);
        }
    }

    @objid ("d97d9b14-55b6-11e2-877f-002564c97630")
    @Override
    protected Dimension calculatePreferredSize(final IFigure parent, final int wHint, final int hHint) {
        // FIXME: absolutely not correct! Use the locators!
        List<?> children = parent.getChildren();
        Dimension d = new Dimension();
        for (int i = 0; i < children.size(); i++) {
            IFigure child = (IFigure) children.get(i);
            d.union(child.getPreferredSize());
        }
        return d;
    }

    @objid ("d97d9b1f-55b6-11e2-877f-002564c97630")
    private PlacementConstraint getLocator(final IFigure child) {
        return this.selfLocatingConstraints.get(child);
    }

    @objid ("d97d9b25-55b6-11e2-877f-002564c97630")
    private Rectangle getSimpleConstraint(final IFigure child) {
        return this.simpleConstraints.get(child);
    }

}
