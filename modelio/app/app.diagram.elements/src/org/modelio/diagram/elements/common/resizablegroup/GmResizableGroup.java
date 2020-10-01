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

package org.modelio.diagram.elements.common.resizablegroup;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNoStyleCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * <p>
 * Represent a group of resizable and re-orderable elements.
 * </p>
 * <p>
 * This class must be subclassed , {@link #canUnmask(MObject)} and {@link #canCreate(Class)} must be defined.
 * </p>
 * 
 * @author fpoyer
 */
@objid ("7f09cb45-1dec-11e2-8cad-001ec947c8cc")
public abstract class GmResizableGroup extends GmNoStyleCompositeNode {
    /**
     * The orientation of this container.
     */
    @objid ("7f09cb47-1dec-11e2-8cad-001ec947c8cc")
    private boolean vertical;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("7f09cb49-1dec-11e2-8cad-001ec947c8cc")
    private static final int MINOR_VERSION = 0;

    @objid ("7f09cb4c-1dec-11e2-8cad-001ec947c8cc")
    private static final int MAJOR_VERSION = 0;

    @objid ("7f09cb4e-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public GmCompositeNode getCompositeFor(Class<? extends MObject> metaclass) {
        if (canCreate(metaclass)) {
            return this;
        } else {
            return null;
        }
    }

    /**
     * @param diagram the diagram in which this partition container is used.
     * @param relatedRef a reference to the reprensented element.
     */
    @objid ("7f09cb55-1dec-11e2-8cad-001ec947c8cc")
    public GmResizableGroup(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
    }

    /**
     * Empty constructor needed for serialisation.
     */
    @objid ("7f09cb5a-1dec-11e2-8cad-001ec947c8cc")
    public GmResizableGroup() {
        // Nothing to do.
    }

    @objid ("7f09cb5d-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmResizableGroup.");
        switch (readVersion) {
            case 0: {
                read_0(in);
                break;
            }
            default: {
                assert (false) : "version number not covered!";
                // reading as last handled version: 0
                read_0(in);
                break;
            }
        }
    }

    @objid ("7f09cb61-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        out.writeProperty("isVertical", Boolean.valueOf(this.vertical));
        
        // Write version of this Gm if different of 0.
               writeMinorVersion(out, "GmResizableGroup.", Integer.valueOf(MINOR_VERSION));
    }

    /**
     * Returns whether this container is horizontal.
     * 
     * @return true if this container is horizontal, false otherwise.
     */
    @objid ("7f09cb65-1dec-11e2-8cad-001ec947c8cc")
    public boolean isVertical() {
        return this.vertical;
    }

    /**
     * Sets the orientation of this container.
     * 
     * @param value true if this container must be vertical, false otherwise.
     */
    @objid ("7f09cb6a-1dec-11e2-8cad-001ec947c8cc")
    public void setVertical(boolean value) {
        if (this.vertical != value) {
            this.vertical = value;
            firePropertyChange(IGmObject.PROPERTY_LAYOUTDATA, null, getLayoutData());
        }
    }

    @objid ("7f09cb6e-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected boolean isValidChild(final GmNodeModel node) {
        final MObject childEl = node.getRelatedElement();
        return childEl == null || (!childEl.isDeleted() && canUnmask(childEl));
    }

    @objid ("7f09cb75-1dec-11e2-8cad-001ec947c8cc")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.vertical = ((Boolean) in.readProperty("isVertical")).booleanValue();
    }

    @objid ("7f09cb78-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    /**
     * Returns whether this group allows movement of its children, like reordering them or moving them in and out of
     * this group.
     * 
     * Default implementation returns <code>true</code>.
     * 
     * @return <code>true</code> if movements are allowed.
     */
    @objid ("7f09cb7d-1dec-11e2-8cad-001ec947c8cc")
    public boolean allowsMove() {
        return true;
    }

    /**
     * Returns whether this group allows resizing of its children.
     * 
     * Default implementation returns <code>true</code>.
     * 
     * @return <code>true</code> if resize of children is allowed.
     */
    @objid ("7f09cb82-1dec-11e2-8cad-001ec947c8cc")
    public boolean allowsResize() {
        return true;
    }

}
