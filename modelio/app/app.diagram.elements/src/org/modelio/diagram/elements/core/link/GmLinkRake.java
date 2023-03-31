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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.model.IGmLinkRake;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.persistence.IPersistent;

/**
 * Implementation of {@link IGmLinkRake}.
 * 
 * @author cmarin
 */
@objid ("80258328-1dec-11e2-8cad-001ec947c8cc")
public class GmLinkRake implements IPersistent, IGmLinkRake {
    @objid ("8025832b-1dec-11e2-8cad-001ec947c8cc")
    private static final int MAJOR_VERSION = 0;

    @objid ("8025832a-1dec-11e2-8cad-001ec947c8cc")
    private Object sharedAnchor;

    @objid ("c50b59df-48fb-4ac6-be26-998d52bba0b3")
    private final List<PropertyChangeListener> listeners = new CopyOnWriteArrayList<>();

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

    @objid ("aafffd81-db23-4ad8-b873-81f7d0a25f3a")
    @Override
    public void addListener(PropertyChangeListener listener) {
        this.listeners.add(listener);
    }

    @objid ("4791d2cb-0eef-4b93-b82a-3a8afa5f7744")
    @Override
    public void removeListener(PropertyChangeListener listener) {
        this.listeners.remove(listener);
    }

    @objid ("515b8e58-0e18-4607-a914-6502fd970f05")
    protected void fireListeners(Object oldAnchor) {
        PropertyChangeEvent evt = new PropertyChangeEvent(this, PROP, oldAnchor, getSharedAnchor());
        for (PropertyChangeListener l : this.listeners) {
            l.propertyChange(evt);
        }
        
    }

    /**
     * set the shared anchor.
     * @param anchor the shared anchor.
     */
    @objid ("80258346-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setSharedAnchor(final Object anchor) {
        Object oldAnchor = this.sharedAnchor;
        this.sharedAnchor = anchor;
        fireListeners(oldAnchor);
        
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

    @objid ("4cf90695-1b77-46df-8072-ee71f42b41bd")
    @Override
    public int hashCode() {
        return Objects.hash(this.sharedAnchor);
    }

    @objid ("e7653f34-a0a3-4c0b-8e89-03efd4166067")
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
        GmLinkRake other = (GmLinkRake) obj;
        return Objects.equals(this.sharedAnchor, other.sharedAnchor);
    }

    @objid ("a3b17950-8b9b-4cc0-9984-0c42ebfa0c0a")
    @Override
    public String toString() {
        return String.format("%s [sharedAnchor=%s, %d listeners]", getClass().getSimpleName(), this.sharedAnchor, this.listeners.size());
    }

}
