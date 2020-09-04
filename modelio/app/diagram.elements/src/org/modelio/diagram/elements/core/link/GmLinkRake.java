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

package org.modelio.diagram.elements.core.link;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.model.IGmLinkRake;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.persistence.IPersistent;

/**
 * Represents a link rake on the source side.
 * <p>
 * The rake knows all links that are raked.
 * 
 * @author cmarin
 */
@objid ("80258328-1dec-11e2-8cad-001ec947c8cc")
public class GmLinkRake implements IPersistent, IGmLinkRake {
    @objid ("8025832b-1dec-11e2-8cad-001ec947c8cc")
    private static final int MAJOR_VERSION = 0;

    @objid ("8025832a-1dec-11e2-8cad-001ec947c8cc")
    private Object sharedAnchor;

    /**
     * Add a link to the rake.
     * 
     * @param gmLink the link to add
     */
    @objid ("8025832d-1dec-11e2-8cad-001ec947c8cc")
    public void addLink(final GmLink gmLink) {
        if (this.sharedAnchor == null)
            this.sharedAnchor = gmLink.getPath().getSourceAnchor();
        else if (this.sharedAnchor != gmLink.getPath().getSourceAnchor())
            throw new IllegalArgumentException(gmLink + " does not share the rake source.");
    }

    @objid ("80258332-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean isExternal(final IDiagramWriter out) {
        return false;
    }

    @objid ("80258339-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void read(final IDiagramReader in) {
        this.sharedAnchor = in.readProperty("sharedAnchor");
    }

    @objid ("8025833e-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void write(final IDiagramWriter out) {
        out.writeProperty("sharedAnchor", this.sharedAnchor);
    }

    /**
     * Constructor.
     */
    @objid ("80258343-1dec-11e2-8cad-001ec947c8cc")
    public GmLinkRake() {
    }

    /**
     * set the shared anchor.
     * 
     * @param anchor the shared anchor.
     */
    @objid ("80258346-1dec-11e2-8cad-001ec947c8cc")
    public void setSharedAnchor(final Object anchor) {
        this.sharedAnchor = anchor;
    }

    @objid ("8025834b-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Object getSharedAnchor() {
        return this.sharedAnchor;
    }

    @objid ("8027e562-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
