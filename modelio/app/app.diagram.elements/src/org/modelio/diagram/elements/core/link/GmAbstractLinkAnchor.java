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
package org.modelio.diagram.elements.core.link;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.persistence.IPersistent;

/**
 * Represents the link anchor on the source and destination node.
 * 
 * @author cmarin
 * @see org.modelio.diagram.elements.core.figures.anchors.NodeAnchor
 */
@objid ("800dabb3-1dec-11e2-8cad-001ec947c8cc")
public abstract class GmAbstractLinkAnchor implements IPersistent {
    @objid ("800dabbd-1dec-11e2-8cad-001ec947c8cc")
    private static final int MAJOR_VERSION = 0;

    /**
     * Links that use this anchor.
     */
    @objid ("800dabb9-1dec-11e2-8cad-001ec947c8cc")
    private Collection<IGmAnchorListener> links = new ArrayList<>(1);

    /**
     * The anchor reference point location relative to the node location.
     */
    @objid ("676402a5-1e83-11e2-8cad-001ec947c8cc")
    private Dimension location;

    /**
     * Creates a link anchor.
     * @param location The anchor reference point location relative to the anchored node location.
     */
    @objid ("800dabbf-1dec-11e2-8cad-001ec947c8cc")
    protected  GmAbstractLinkAnchor(Dimension location) {
        this.location = location;
    }

    /**
     * Constructor for deserialization.
     */
    @objid ("800dabc5-1dec-11e2-8cad-001ec947c8cc")
    protected  GmAbstractLinkAnchor() {
        
    }

    /**
     * Get the anchor reference point location.
     * @return the anchor reference point location.
     */
    @objid ("800dabc8-1dec-11e2-8cad-001ec947c8cc")
    public Dimension getLocation() {
        return this.location;
    }

    @objid ("80100ddf-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean isExternal(IDiagramWriter out) {
        return false;
    }

    @objid ("80100de5-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void read(IDiagramReader in) {
        Object o = in.readProperty("location");
        if (o instanceof Point) {
            // TODO: Temporary ascendant compatibility, to remove after 01/12/2010.
            final Point readPoint = (Point) o;
            this.location = new Dimension(readPoint.x, readPoint.y);
        } else {
            this.location = (Dimension) o;
        }
        
    }

    /**
     * Set the anchor reference point location relative to the node.
     * @param location the anchor reference point location.
     */
    @objid ("80100de9-1dec-11e2-8cad-001ec947c8cc")
    public void setLocation(Dimension location) {
        this.location = location;
        
        for (IGmAnchorListener l : this.links) {
            l.fireAnchorMoved(this);
        }
        
    }

    @objid ("80100def-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void write(IDiagramWriter out) {
        out.writeProperty("location", this.location);
    }

    /**
     * Called by the link on deserialization
     * @param l a link
     */
    @objid ("80100df3-1dec-11e2-8cad-001ec947c8cc")
    public void addLink(IGmAnchorListener l) {
        this.links.add(l);
    }

    /**
     * @param l the listener (link) to remove
     */
    @objid ("80100df6-1dec-11e2-8cad-001ec947c8cc")
    public void removeLink(IGmAnchorListener l) {
        this.links.remove(l);
    }

    @objid ("80100df9-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @objid ("925ad1a3-13ac-4df4-8699-bb734ea9c192")
    @Override
    public String toString() {
        return getClass().getSimpleName()+"[location="+getLocation()+", "+this.links.size()+" listener(s) ]";
    }

    @objid ("285cf665-9f2e-4a2f-8fbf-9e2c8973dc94")
    @Override
    public int hashCode() {
        return Objects.hash(getClass(), this.location);
    }

    @objid ("2f658747-1994-456c-b875-4b7a739a8029")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        GmAbstractLinkAnchor other = (GmAbstractLinkAnchor) obj;
        return Objects.equals(this.location, other.location);
    }

}
