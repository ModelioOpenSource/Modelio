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

package org.modelio.diagram.editor.sequence.elements;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.persistence.IPersistent;

@objid ("d8fef546-55b6-11e2-877f-002564c97630")
public class GmMessageEndAnchor implements IPersistent {
    @objid ("d8fef549-55b6-11e2-877f-002564c97630")
    private int ref;

    @objid ("d8fef54a-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Constructor
     * 
     * @param ref the Y reference position in coordinates relative to the owner.
     */
    @objid ("d8fef54c-55b6-11e2-877f-002564c97630")
    public GmMessageEndAnchor(final int ref) {
        this.ref = ref;
    }

    /**
     * For deserialization.
     */
    @objid ("d8fef551-55b6-11e2-877f-002564c97630")
    public GmMessageEndAnchor() {
    }

    /**
     * Get the anchor Y location.
     * 
     * @return the Y position of the anchor in coordinates relative to the owner.
     */
    @objid ("d9007bba-55b6-11e2-877f-002564c97630")
    public int getReference() {
        return this.ref;
    }

    /**
     * Tells whether the element is stored outside of the given writer.
     * <p>
     * This method can use {@link IDiagramWriter#getRoot()} if needed to know whether it is internal or external.
     * <p>
     * If this is the case, {@link #write(IDiagramWriter)} will be called, which will have to call {@link IDiagramWriter#writeExtRef(IPersistent, String, String)} only.
     * 
     * @param out the writer where the model is saved.
     * @return true if this element is external, false if the element is to be saved in this writer.
     */
    @objid ("d9007bbf-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isExternal(IDiagramWriter out) {
        return false;
    }

    /**
     * Read the element properties from a serialized form.
     * 
     * @param in a reader to build the graphic model from.
     */
    @objid ("d9007bc7-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        this.ref = (Integer) in.readProperty("ref");
    }

    /**
     * Save the persistent element in the given writer.
     * <p>
     * If the element is external to the writer, this method must only call {@link IDiagramWriter#writeExtRef(IPersistent, String, String)}.
     * <p>
     * In the other case it can call any {@link IDiagramWriter IDiagramWriter.writeXxxx(...)} method except <tt>writeExtRef(...)</tt>.
     * 
     * @param out a writer to save the model to.
     */
    @objid ("d9007bcd-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        out.writeProperty("ref", this.ref);
    }

    @objid ("d9007bd3-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
