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

package org.modelio.diagram.editor.sequence.elements.interactionuse;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.sequence.elements.sequencediagram.GmSequenceDiagram;
import org.modelio.diagram.editor.sequence.elements.sequencediagram.PlacementConstraint;

/**
 * Placement constraint for an InteractionUse in a Sequence diagram.
 * 
 * @author fpoyer
 */
@objid ("d9220d98-55b6-11e2-877f-002564c97630")
public class InteractionUsePlacementConstraint extends PlacementConstraint {
    @objid ("d9220d9a-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Empty c'tor for deserialisation.
     */
    @objid ("d9220d9c-55b6-11e2-877f-002564c97630")
    public InteractionUsePlacementConstraint() {
        super();
    }

    /**
     * C'tor.
     * 
     * @param x x coordinate.
     * @param y y coordinate.
     * @param width width.
     * @param height height
     * @param diagram diagram in which this happens.
     */
    @objid ("d9220d9f-55b6-11e2-877f-002564c97630")
    public InteractionUsePlacementConstraint(final int x, final int y, final int width, final int height, final GmSequenceDiagram diagram) {
        super(x, y, width, height, diagram);
    }

    /**
     * Make getWidth visible so that primary node can use it.
     */
    @objid ("d9220dac-55b6-11e2-877f-002564c97630")
    @Override
    public int getWidth() {
        return super.getWidth();
    }

    /**
     * Make getX visible so that primary node can use it.
     */
    @objid ("d9220db2-55b6-11e2-877f-002564c97630")
    @Override
    public int getX() {
        return super.getX();
    }

    /**
     * Make getY visible so that primary node can use it.
     */
    @objid ("d9239419-55b6-11e2-877f-002564c97630")
    @Override
    public int getY() {
        return super.getY();
    }

    @objid ("d923941f-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
