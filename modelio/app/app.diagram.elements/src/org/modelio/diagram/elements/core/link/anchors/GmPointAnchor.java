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
package org.modelio.diagram.elements.core.link.anchors;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Dimension;
import org.modelio.diagram.elements.core.link.GmAbstractLinkAnchor;

/**
 * Represents an anchor located inside the destination node.
 * 
 * @author cmarin
 * @see org.modelio.diagram.elements.core.figures.anchors.NodeAnchor
 */
@objid ("7fe05ef6-1dec-11e2-8cad-001ec947c8cc")
public class GmPointAnchor extends GmAbstractLinkAnchor {
    @objid ("7fe05ef8-1dec-11e2-8cad-001ec947c8cc")
    private static final int MAJOR_VERSION = 0;

    /**
     * Creates a link anchor.
     * @param location The anchor reference point location relative to the anchored node location.
     */
    @objid ("7fe05efa-1dec-11e2-8cad-001ec947c8cc")
    public  GmPointAnchor(Dimension location) {
        super(location);
    }

    /**
     * Constructor for deserialization.
     */
    @objid ("7fe05f00-1dec-11e2-8cad-001ec947c8cc")
    public  GmPointAnchor() {
        
    }

    @objid ("7fe05f03-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
