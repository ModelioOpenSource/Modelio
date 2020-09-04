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

package org.modelio.diagram.elements.core.link.anchors;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Dimension;
import org.modelio.diagram.elements.core.link.GmAbstractLinkAnchor;

/**
 * Anchor that locate a figure at the given distance from the connection source node.
 * 
 * @author cmarin
 */
@objid ("7fe2c14b-1dec-11e2-8cad-001ec947c8cc")
public class GmSourceSatelliteAnchor extends GmAbstractLinkAnchor {
    @objid ("7fe2c14d-1dec-11e2-8cad-001ec947c8cc")
    private static final int MAJOR_VERSION = 0;

    /**
     * Creates the anchor
     * 
     * @param destinationLocation distance from the connection source.
     */
    @objid ("7fe2c14f-1dec-11e2-8cad-001ec947c8cc")
    public GmSourceSatelliteAnchor(final Dimension destinationLocation) {
        super(destinationLocation);
    }

    /**
     * Constructor for deserialization only.
     */
    @objid ("7fe2c156-1dec-11e2-8cad-001ec947c8cc")
    public GmSourceSatelliteAnchor() {
        super();
    }

    @objid ("7fe2c159-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
