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
import org.eclipse.draw2d.geometry.Point;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.persistence.IPersistent;

/**
 * Represents a {@link org.eclipse.draw2d.XYAnchor}
 * 
 * @author cmarin
 */
@objid ("7fe2c160-1dec-11e2-8cad-001ec947c8cc")
public class GmXYAnchor implements IPersistent {
    @objid ("7fe2c165-1dec-11e2-8cad-001ec947c8cc")
    private static final int MAJOR_VERSION = 0;

    @objid ("3f30ac48-20f6-4227-bb41-8b01f37e319b")
    private Point ref;

    /**
     * Constructor
     * 
     * @param ref the position of the reference point in absolute coordinates.
     */
    @objid ("7fe2c167-1dec-11e2-8cad-001ec947c8cc")
    public GmXYAnchor(final Point ref) {
        this.ref = ref;
    }

    /**
     * For deserialization.
     */
    @objid ("7fe2c16e-1dec-11e2-8cad-001ec947c8cc")
    public GmXYAnchor() {
    }

    /**
     * Get the anchor location.
     * 
     * @return the position of the anchor in absolute coordinates.
     */
    @objid ("7fe2c171-1dec-11e2-8cad-001ec947c8cc")
    public Point getReferencePoint() {
        return this.ref;
    }

    @objid ("7fe2c178-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean isExternal(IDiagramWriter out) {
        return false;
    }

    @objid ("7fe2c17d-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void read(IDiagramReader in) {
        this.ref = (Point) in.readProperty("ref");
    }

    /**
     * Set the anchor location.
     * 
     * @param ref the position of the anchor in absolute coordinates.
     */
    @objid ("7fe2c180-1dec-11e2-8cad-001ec947c8cc")
    public void setReferencePoint(final Point ref) {
        this.ref = ref;
    }

    @objid ("7fe5238d-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void write(IDiagramWriter out) {
        out.writeProperty("ref", this.ref);
    }

    @objid ("7fe52390-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @objid ("158bbfe7-b0b5-4bc6-a49a-92663e3b37c3")
    @Override
    public String toString() {
        return getClass().getSimpleName()+"[reference="+getReferencePoint()+"]";
    }

}
