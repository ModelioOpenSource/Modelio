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
package org.modelio.diagram.persistence;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Represent an element that is readable from an {@link IDiagramReader} and writable in an {@link IDiagramWriter}.
 * 
 * @author cmarin
 */
@objid ("cb7049b4-186f-11e2-92d2-001ec947c8cc")
public interface IPersistent {
    /**
     * Read the element properties from a serialized form.
     * @param in a reader to build the graphic model from.
     */
    @objid ("cb7049b6-186f-11e2-92d2-001ec947c8cc")
    void read(IDiagramReader in);

    /**
     * Save the persistent element in the given writer.
     * <p>
     * If the element is external to the writer, this method must only call
     * {@link IDiagramWriter#writeExtRef(IPersistent, String, String)}.
     * <p>
     * In the other case it can call any {@link IDiagramWriter IDiagramWriter.writeXxxx(...)} method except
     * <tt>writeExtRef(...)</tt>.
     * @param out a writer to save the model to.
     */
    @objid ("cb7049b9-186f-11e2-92d2-001ec947c8cc")
    void write(IDiagramWriter out);

    /**
     * Tells whether the element is stored outside of the given writer.
     * <p>
     * This method can use {@link IDiagramWriter#getRoot()} if needed to know whether it is internal or external.
     * <p>
     * If this is the case, {@link #write(IDiagramWriter)} will be called, which will have to call
     * {@link IDiagramWriter#writeExtRef(IPersistent, String, String)} only.
     * @param out the writer where the model is saved.
     * @return true if this element is external, false if the element is to be saved in this writer.
     */
    @objid ("cb7049bc-186f-11e2-92d2-001ec947c8cc")
    boolean isExternal(IDiagramWriter out);

    /**
     * Returns the current major version of this Graphic Model class.
     * @return the current major version of this Graphic Model class.
     */
    @objid ("cb7049c0-186f-11e2-92d2-001ec947c8cc")
    int getMajorVersion();
}

