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

package org.modelio.diagram.editor.sequence.elements.sequencediagram;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Locator;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.persistence.IPersistent;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Base class for the objects used to define to bounds of a child.
 */
@objid ("d979072b-55b6-11e2-877f-002564c97630")
public abstract class PlacementConstraint implements IPersistent, Locator {
    @objid ("d979072f-55b6-11e2-877f-002564c97630")
    private int height;

    @objid ("d9790730-55b6-11e2-877f-002564c97630")
    private int width;

    @objid ("d9790731-55b6-11e2-877f-002564c97630")
    private int x;

    @objid ("d9790732-55b6-11e2-877f-002564c97630")
    private int y;

    @objid ("d9790734-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("d9790733-55b6-11e2-877f-002564c97630")
    private GmSequenceDiagram diagram;

    /**
     * C'tor.
     * 
     * @param x the x reference in coordinates relative to its parent.
     * @param y the Y reference in coordinates relative to its parent.
     * @param width the desired width in pixels.
     * @param height the desired height in pixels.
     * @param diagram the diagram in which this constraint is used.
     */
    @objid ("d9790736-55b6-11e2-877f-002564c97630")
    public PlacementConstraint(final int x, final int y, final int width, final int height, final GmSequenceDiagram diagram) {
        super();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.diagram = diagram;
    }

    /**
     * Empty constructor for deserialisation. Do not use!
     */
    @objid ("d9790743-55b6-11e2-877f-002564c97630")
    public PlacementConstraint() {
    }

    /**
     * Computes and return the bounds that would be used if the relocate method was called.
     * 
     * @param target the figure to relocate.
     * @return the bounds that would be given to the figure.
     */
    @objid ("d9790746-55b6-11e2-877f-002564c97630")
    public Rectangle getUpdatedBounds(final IFigure target) {
        int tmpwidth = getWidth();
        int tmpheight = getHeight();
        if (tmpwidth == -1 || tmpheight == -1) {
            Dimension preferredSize = target.getPreferredSize(tmpwidth, tmpheight);
            if (tmpwidth == -1) {
                tmpwidth = preferredSize.width;
            }
            if (tmpheight == -1) {
                tmpheight = preferredSize.height;
            }
        }
        return new Rectangle(getX(), getY(), tmpwidth, tmpheight);
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
    @objid ("d979074d-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isExternal(IDiagramWriter out) {
        return false;
    }

    /**
     * Read the element properties from a serialized form.
     * 
     * @param in a reader to build the graphic model from.
     */
    @objid ("d97a8dc0-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        this.x = ((Integer) in.readProperty("x")).intValue();
        this.y = ((Integer) in.readProperty("y")).intValue();
        this.width = ((Integer) in.readProperty("width")).intValue();
        this.height = ((Integer) in.readProperty("height")).intValue();
        this.diagram = (GmSequenceDiagram) in.getRoot();
    }

    @objid ("d97a8dc7-55b6-11e2-877f-002564c97630")
    @Override
    public void relocate(final IFigure target) {
        target.setBounds(getUpdatedBounds(target));
    }

    /**
     * Sets the desired height for this placement constraint.
     * 
     * @param height the desired height.
     */
    @objid ("d97a8dcc-55b6-11e2-877f-002564c97630")
    public void setHeight(final int height) {
        this.height = height;
    }

    /**
     * Sets the desired width for this placement constraint.
     * 
     * @param width the desired width.
     */
    @objid ("d97a8dd1-55b6-11e2-877f-002564c97630")
    public void setWidth(final int width) {
        this.width = width;
    }

    /**
     * Sets the X reference.
     * 
     * @param x the new reference.
     */
    @objid ("d97a8dd6-55b6-11e2-877f-002564c97630")
    public void setX(final int x) {
        this.x = x;
    }

    /**
     * Sets the Y reference.
     * 
     * @param y the new reference.
     */
    @objid ("d97a8ddb-55b6-11e2-877f-002564c97630")
    public void setY(final int y) {
        this.y = y;
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
    @objid ("d97a8de0-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        out.writeProperty("x", Integer.valueOf(this.x));
        out.writeProperty("y", Integer.valueOf(this.y));
        out.writeProperty("width", Integer.valueOf(this.width));
        out.writeProperty("height", Integer.valueOf(this.height));
    }

    @objid ("d97a8de7-55b6-11e2-877f-002564c97630")
    protected int getHeight() {
        return this.height;
    }

    @objid ("d97a8deb-55b6-11e2-877f-002564c97630")
    protected int getWidth() {
        return this.width;
    }

    @objid ("d97a8def-55b6-11e2-877f-002564c97630")
    protected int getX() {
        return this.x;
    }

    @objid ("d97a8df3-55b6-11e2-877f-002564c97630")
    protected int getY() {
        return this.y;
    }

    /**
     * This method is meant to be used in read methods implementation to resolve the MRef.
     * @param <E> the type of element to return.
     * 
     * @param ref the reference to resolve.
     * @return the found MObject or <code>null</code> if the element is not present in the project.
     */
    @objid ("d97a8df7-55b6-11e2-877f-002564c97630")
    protected <E extends MObject> E resolveRef(final MRef ref) {
        return this.diagram.getModelManager().resolveRef(ref);
    }

    @objid ("d97c1463-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
