/* 
 * Copyright 2013-2018 Modeliosoft
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
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.persistence.IPersistent;

/**
 * An anchor whose position is relative to the figure's top left corner.
 */
@objid ("7fddfcbd-1dec-11e2-8cad-001ec947c8cc")
public class GmNodeAnchor implements IPersistent {
    @objid ("7fddfcc1-1dec-11e2-8cad-001ec947c8cc")
    private static final int MAJOR_VERSION = 0;

    @objid ("f3b481ad-a10c-4b94-ab4b-879b10afb17d")
    private Dimension difference;

    /**
     * Read the element properties from a serialized form.
     * @param in a reader to build the graphic model from.
     */
    @objid ("7fddfcc3-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void read(IDiagramReader in) {
        this.difference = (Dimension) in.readProperty("difference");
    }

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
    @objid ("7fddfcc7-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void write(IDiagramWriter out) {
        out.writeProperty("difference", this.difference);
    }

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
    @objid ("7fe05ed3-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean isExternal(IDiagramWriter out) {
        return false;
    }

    /**
     * For deserialization.
     */
    @objid ("7fe05ed9-1dec-11e2-8cad-001ec947c8cc")
    public GmNodeAnchor() {
    }

    /**
     * Constructor
     * @param difference the difference to the figure's top left corner.
     */
    @objid ("7fe05edc-1dec-11e2-8cad-001ec947c8cc")
    public GmNodeAnchor(final Dimension difference) {
        this.difference = difference;
    }

    @objid ("7fe05ee3-1dec-11e2-8cad-001ec947c8cc")
    public Dimension getDifference() {
        return this.difference;
    }

    @objid ("7fe05ee9-1dec-11e2-8cad-001ec947c8cc")
    public void setDifference(final Dimension difference) {
        this.difference = difference;
    }

    @objid ("7fe05eef-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @objid ("6eda3d6e-e561-4327-a003-33370fdd15c3")
    @Override
    public String toString() {
        return getClass().getSimpleName()+"[difference="+getDifference()+"]";
    }

}
