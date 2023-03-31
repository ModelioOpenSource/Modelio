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
import org.modelio.diagram.elements.core.figures.geometry.Direction;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.persistence.IPersistent;

/**
 * Represents a anchor anchored on the border of the figure.
 */
@objid ("7fdb9a4b-1dec-11e2-8cad-001ec947c8cc")
public class GmBorderAnchor implements IPersistent {
    @objid ("7fddfc79-1dec-11e2-8cad-001ec947c8cc")
    private int border;

    @objid ("7fddfc7a-1dec-11e2-8cad-001ec947c8cc")
    private int offset;

    @objid ("7fddfc7b-1dec-11e2-8cad-001ec947c8cc")
    private static final int MAJOR_VERSION = 0;

    @objid ("7fddfc7d-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void read(IDiagramReader in) {
        this.border = (Integer) in.readProperty("border");
        this.offset = (Integer) in.readProperty("offset");
        
    }

    @objid ("7fddfc81-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void write(IDiagramWriter out) {
        out.writeProperty("border", this.border);
        out.writeProperty("offset", this.offset);
        
    }

    @objid ("7fddfc85-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean isExternal(IDiagramWriter out) {
        return false;
    }

    /**
     * Constructor.
     * @param border border.
     * @param offset offset
     */
    @objid ("7fddfc8b-1dec-11e2-8cad-001ec947c8cc")
    public  GmBorderAnchor(final int border, final int offset) {
        this.offset = offset;
        this.border = border;
        
    }

    @objid ("7fddfc92-1dec-11e2-8cad-001ec947c8cc")
    public  GmBorderAnchor() {
        
    }

    @objid ("7fddfc94-1dec-11e2-8cad-001ec947c8cc")
    public int getBorder() {
        return this.border;
    }

    @objid ("7fddfc98-1dec-11e2-8cad-001ec947c8cc")
    public void setBorder(final int border) {
        this.border = border;
    }

    @objid ("7fddfc9c-1dec-11e2-8cad-001ec947c8cc")
    public int getOffset() {
        return this.offset;
    }

    @objid ("7fddfca0-1dec-11e2-8cad-001ec947c8cc")
    public void setOffset(final int offset) {
        this.offset = offset;
    }

    @objid ("7fddfca4-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @objid ("639df528-82f9-4d13-b9e1-c543ffa6e1b8")
    @Override
    public String toString() {
        return String.format("%s [border=%s, offset=%s]",
                getClass().getSimpleName(),
                Direction.fromPositionConstant(getBorder(), null),
                getOffset());
        
    }

    @objid ("763d75b8-0e1b-4a92-9aeb-56f6560b82e6")
    @Override
    public boolean equals(Object obj) {
        // Automatically generated method.Please delete this comment before entering specific code.
        
        if (this == obj) return true;
        if (this.getClass() != obj.getClass()) return false;
        
        GmBorderAnchor other = (GmBorderAnchor)obj;
        if (this.border != other.border) return false;
        if (this.offset != other.offset) return false;
        return true;
    }

    @objid ("686d93f7-f945-4ece-9343-732ce510da2b")
    @Override
    public int hashCode() {
        // Automatically generated method.Please delete this comment before entering specific code.
        int result = super.hashCode();
        result = 31 * result + this.border;
        result = 31 * result + this.offset;
        return result;
    }

}
