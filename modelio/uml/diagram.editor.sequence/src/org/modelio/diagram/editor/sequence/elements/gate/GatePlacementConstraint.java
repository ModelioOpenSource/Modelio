/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.diagram.editor.sequence.elements.gate;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.sequence.elements.sequencediagram.GmSequenceDiagram;
import org.modelio.diagram.editor.sequence.elements.sequencediagram.PlacementConstraint;

/**
 * Placement constraint for a Gate on the diagram background.
 * 
 * @author fpoyer
 */
@objid ("d8f5cd72-55b6-11e2-877f-002564c97630")
public class GatePlacementConstraint extends PlacementConstraint {
    @objid ("d8f5cd74-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Empty c'tor for deserialisation.
     */
    @objid ("d8f5cd76-55b6-11e2-877f-002564c97630")
    public GatePlacementConstraint() {
        super();
    }

    /**
     * Main c'tor.
     * 
     * @param x the requested X coordinate.
     * @param y the requested y coordinate.
     * @param width the requested width.
     * @param height the requested height.
     * @param diagram the diagram in which this constraint is used.
     */
    @objid ("d8f5cd79-55b6-11e2-877f-002564c97630")
    public GatePlacementConstraint(final int x, final int y, final int width, final int height, final GmSequenceDiagram diagram) {
        super(x, y, width, height, diagram);
    }

    @objid ("d8f5cd86-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
