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
package org.modelio.diagram.elements.common.group;

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
 * Represents a group of labels.
 * <p>
 * This class must be subclassed.
 * 
 * @author cmarin
 */
@objid ("7e523667-1dec-11e2-8cad-001ec947c8cc")
public abstract class GmGroup extends GmNoStyleCompositeNode {
    /**
     * Label that tells the group hides features.
     */
    @objid ("7e523669-1dec-11e2-8cad-001ec947c8cc")
    protected boolean hasHiddenFeatures = false;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("7e52366b-1dec-11e2-8cad-001ec947c8cc")
    private static final int MINOR_VERSION = 0;

    @objid ("7e52366e-1dec-11e2-8cad-001ec947c8cc")
    private static final int MAJOR_VERSION = 0;

    /**
     * Constructor for deserialization only.
     */
    @objid ("7e549878-1dec-11e2-8cad-001ec947c8cc")
    public  GmGroup() {
        
    }

    /**
     * Initialize a group.
     * @param diagram The diagram.
     * @param relatedRef a reference to the element this GmModel is related to. Must not be <i>null</i>.
     */
    @objid ("7e54987b-1dec-11e2-8cad-001ec947c8cc")
    public  GmGroup(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
    }

    @objid ("7e549880-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void addChild(GmNodeModel toAdd) {
        super.addChild(toAdd);
        
        updateHiddenFeatures();
        
    }

    /**
     * Tells whether this composite node support child nodes of the given java class.
     * <p>
     * Subclasses have to define allowed nodes.
     * <p>
     * Used by move and clone policies to know whether a graphic node can directly be moved to another one or if the
     * represented element must be unmasked to another kind of node.
     * <p>
     * The moved model element is assumed to be displayable under this node, so no check on the represented element
     * metaclass or ownership has to be made.
     */
    @objid ("7e549884-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public abstract boolean canContain(Class<? extends GmNodeModel> nodeClass);

    @objid ("7e54988b-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public final boolean canUnmask(MObject el) {
        // Cannot unmask anything else than an attribute
        if (!isValidElement(el)) {
            return false;
        }
        
        // Cannot unmask if the element is already displayed
        final GmNodeModel sameChild = getChild(new MRef(el));
        return (sameChild == null);
    }

    @objid ("7e549891-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public GmCompositeNode getCompositeFor(Class<? extends MObject> metaclass) {
        if (canCreate(metaclass)) {
            return this;
        } else {
            return null;
        }
        
    }

    /**
     * Tells whether the group has hidden features.
     * @return true if the group has hidden features.
     */
    @objid ("7e549898-1dec-11e2-8cad-001ec947c8cc")
    public boolean hasHiddenFeatures() {
        return this.hasHiddenFeatures;
    }

    /**
     * Tells whether the zone must be hidden if empty.
     * <p>
     * The default implementation returns false. It may be overridden to return true depending on a style key.
     * @return whether the zone must be hidden if empty.
     */
    @objid ("7e54989d-1dec-11e2-8cad-001ec947c8cc")
    public boolean hideIfEmpty() {
        return false;
    }

    @objid ("7e5498a2-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public abstract boolean isVisible();

    @objid ("7e5498a5-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void removeChild(GmNodeModel child) {
        super.removeChild(child);
        updateHiddenFeatures();
        
    }

    /**
     * Tells whether the last child node should be stretched to take the remaining available space.
     * <p>
     * The default implementation returns <i>false</i>.
     * <p>
     * Subclasses may redefine this method.
     * @return true to grab excess space, else false.
     */
    @objid ("7e5498a9-1dec-11e2-8cad-001ec947c8cc")
    public boolean stretchLastChild() {
        return false;
    }

    @objid ("7e56fad1-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected abstract void doSetVisible(boolean visible);

    @objid ("7e56fad4-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected final boolean isValidChild(GmNodeModel node) {
        final MObject el = node.getRelatedElement();
        
        if (el == null || !isValidElement(el)) {
            return false;
        }
        
        // Cannot unmask if the element is already displayed
        MRef ref = node.getRepresentedRef();
        for (GmNodeModel child : getChildren()) {
            if (child != node && child.getRepresentedRef().equals(ref) && child.getClass()==node.getClass()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Tells whether a GmNodeModel representing the given model element can be contained here.
     * <p>
     * Implementations should check:
     * <ul>
     * <li>that the element metaclass can be unmasked in this node,
     * <li>that the element is really owned by this node represented element,
     * <li>that the element verifies this node filters
     * </ul>
     * <p>
     * Implementations MUST NOT check whether the element is already unmasked.
     * @param el The element to unmask
     * @return <tt>true</tt> if it satisfies all conditions, else <tt>false</tt>.
     */
    @objid ("7e56fada-1dec-11e2-8cad-001ec947c8cc")
    protected abstract boolean isValidElement(MObject el);

    /**
     * Set whether the group hides some elements.
     * <p>
     * In this case adds a "..." label. Remove the label in the other case.
     * @param hasHiddenFeatures true if some elements are hidden else false.
     */
    @objid ("7e56fade-1dec-11e2-8cad-001ec947c8cc")
    protected void setHiddenFeature(boolean hasHiddenFeatures) {
        if (hasHiddenFeatures != this.hasHiddenFeatures) {
            this.hasHiddenFeatures = hasHiddenFeatures;
            firePropertyChange(IGmObject.PROPERTY_CHILDREN, null, hasHiddenFeatures);
        }
        
    }

    /**
     * Updates the {@link #hasHiddenFeatures} flag by calling {@link #setHiddenFeature(boolean)}.
     * <p>
     * Called by {@link #addChild(GmNodeModel)} and {@link #removeChild(GmNodeModel)}.
     */
    @objid ("7e56fae2-1dec-11e2-8cad-001ec947c8cc")
    protected abstract void updateHiddenFeatures();

    @objid ("7e56fae4-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void addChild(final GmNodeModel child, final int index) {
        super.addChild(child, index);
        updateHiddenFeatures();
        
    }

    @objid ("c0cac7e0-77a8-4e88-8b23-c9015a98e47e")
    @Override
    public void refreshFromObModel() {
        //TODO : move this in parent class
        firePropertyChange(PROP_REFRESH_FROM_OBMODEL, null, this);
        
    }

    @objid ("7e56faeb-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmGroup.");
        switch (readVersion) {
        case 0:
            read_0(in);
            break;
        
        default:
            assert (false) : readVersion + " version number not covered!";
            // reading as last handled version: 0
            read_0(in);
            break;
        
        }
        
    }

    @objid ("7e56faef-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmGroup.", GmGroup.MINOR_VERSION);
        
    }

    @objid ("7e56faf3-1dec-11e2-8cad-001ec947c8cc")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("7e56faf6-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public int getMajorVersion() {
        return GmGroup.MAJOR_VERSION;
    }

}
