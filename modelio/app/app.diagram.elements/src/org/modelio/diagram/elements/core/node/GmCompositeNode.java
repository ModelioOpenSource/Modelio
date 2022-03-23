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
package org.modelio.diagram.elements.core.node;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.model.IEditableText;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents a composite node.
 * <p>
 * Composite nodes can have children nodes. The child nodes are maintained in an internal list.
 */
@objid ("8089a5e2-1dec-11e2-8cad-001ec947c8cc")
public abstract class GmCompositeNode extends GmNodeModel {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("8089a5e8-1dec-11e2-8cad-001ec947c8cc")
    private static final int MINOR_VERSION = 0;

    @objid ("8089a5eb-1dec-11e2-8cad-001ec947c8cc")
    private static final int MAJOR_VERSION = 0;

    /**
     * Main node content that is moved as satellite when the node switch to simple or image representation mode.
     */
    @objid ("f842e2e8-fd55-469c-a251-25133d120df5")
    public static final String CONTENT_AS_SATELLITE_ROLE = "body content as satellite";

    /**
     * Internal list of children nodes.
     */
    @objid ("8089a5e4-1dec-11e2-8cad-001ec947c8cc")
    private List<GmNodeModel> children = new ArrayList<>();

    /**
     * Constructor for deserialization only.
     */
    @objid ("808c0824-1dec-11e2-8cad-001ec947c8cc")
    public  GmCompositeNode() {
        
    }

    /**
     * Create a composite node.
     * @param diagram The diagram.
     * @param relatedRef a reference to the element this GmModel is related to.
     */
    @objid ("808c0827-1dec-11e2-8cad-001ec947c8cc")
    public  GmCompositeNode(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
    }

    /**
     * Add a child to the children list and fires a {@link IGmObject#PROPERTY_CHILDREN} property change event.
     * @param child The node to add
     */
    @objid ("808c082c-1dec-11e2-8cad-001ec947c8cc")
    public void addChild(GmNodeModel child) {
        this.children.add(child);
        child.setParent(this);
        
        // set child style
        // child.getPersistedStyle().setCascadedStyle(this.getStyle());
        
        firePropertyChange(IGmObject.PROPERTY_CHILDREN, null, child);
        
    }

    /**
     * Tells whether this composite node support child nodes of the given java class.
     * <p>
     * The default implementation returns <tt>true</tt> in all cases. Subclasses may redefine this method to restrict the kind of
     * node that can be contained.
     * @param nodeClass the child node class
     * @return true if this node supports this kind of node, false in the other case.
     */
    @objid ("808c0830-1dec-11e2-8cad-001ec947c8cc")
    @SuppressWarnings ("static-method")
    public boolean canContain(Class<? extends GmNodeModel> nodeClass) {
        return true;
    }

    /**
     * Delete the element from the diagram.
     * <p>
     * Delete all its children before deleting itself.
     */
    @objid ("808c0839-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void delete() {
        // List children from the end to avoid reordering of the other GMs
        for (int i = this.children.size() - 1; i >= 0; i--) {
            GmNodeModel child = this.children.get(i);
            child.delete();
        
            // When several elements have been deleted consecutively, fix the next index
            if (i > this.children.size()) {
                i = this.children.size();
            }
        }
        
        assert (this.children.isEmpty()) : "All children should have been deleted:" + this.children;
        
        super.delete();
        
    }

    /**
     * Fires a {@link org.modelio.diagram.elements.core.model.IGmObject#PROPERTY_CHILDREN PROPERTY_CHILDREN} property change.
     * <p>
     * To be called when the result of {@link #isVisible()} on the given child node changes.
     * @param child The child node whose visibility changed.
     */
    @objid ("808c083d-1dec-11e2-8cad-001ec947c8cc")
    public void fireChildVisibilityChanged(GmNodeModel child) {
        firePropertyChange(IGmObject.PROPERTY_CHILDREN, null, child);
    }

    /**
     * Get the child element representing the given reference.
     * @param ref An element reference
     * @return the graphic model representing the given reference or <tt>null</tt> if none..
     */
    @objid ("808c0841-1dec-11e2-8cad-001ec947c8cc")
    public final GmNodeModel getChild(MRef ref) {
        for (GmNodeModel child : this.children) {
            if (child.getRepresentedRef().equals(ref)) {
                return child;
            }
        }
        return null;
    }

    /**
     * Get the child nodes that have the given role.
     * <p>
     * The returned list is a copy and may be freely modified.
     * <p>
     * @param role The role that returned children must have.
     * @return A list of the child nodes that have the given role. This list may be freely modified.
     */
    @objid ("808c0847-1dec-11e2-8cad-001ec947c8cc")
    public final List<GmNodeModel> getChildren(String role) {
        ArrayList<GmNodeModel> ret = new ArrayList<>(this.children.size());
        for (GmNodeModel c : this.children) {
            if (role.equals(c.getRoleInComposition())) {
                ret.add(c);
            }
        }
        return ret;
    }

    /**
     * <p>
     * Get the <strong>ALL<strong> child nodes.
     * </p>
     * <p>
     * The returned list is a copy and may be freely modified.
     * </p>
     * @return A copy of the child nodes list.
     */
    @objid ("808c084f-1dec-11e2-8cad-001ec947c8cc")
    public final List<GmNodeModel> getChildren() {
        return new ArrayList<>(this.children);
    }

    /**
     * Get the actual node in which an element of the given metaclass must be unmasked.
     * @param metaclass a metaclass
     * @return a composite node.
     */
    @objid ("808c0856-1dec-11e2-8cad-001ec947c8cc")
    public abstract GmCompositeNode getCompositeFor(Class<? extends MObject> metaclass);

    @objid ("808c085c-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public IEditableText getEditableText() {
        // Let's try to find a child that will return something useful
        for (GmNodeModel child : getChildren()) {
            if (child.getEditableText() != null) {
                return child.getEditableText();
            }
        }
        return super.getEditableText();
    }

    /**
     * Get the first child node that has the given role.
     * @param role The role that returned child must have.
     * @return The first found child that has the needed role or <tt>null</tt> if none found.
     */
    @objid ("808c0861-1dec-11e2-8cad-001ec947c8cc")
    public final GmNodeModel getFirstChild(String role) {
        for (GmNodeModel c : this.children) {
            if (role.equals(c.getRoleInComposition())) {
                return c;
            }
        }
        return null;
    }

    /**
     * <p>
     * Get the child nodes currently visible.
     * </p>
     * <p>
     * The returned list is a copy and may be freely modified.
     * </p>
     * <p>
     * Default implementation returns a list of all children for which the isVisible method returns <code>true</code>. This method
     * may be overridden to dynamically filter the children list, based on current representation mode for example.<br>
     * In this case you must ensure that {@link #styleChanged(StyleKey, Object)} fires a {@link IGmObject#PROPERTY_CHILDREN}
     * property change event in order for the EditParts to be informed of the change.<br>
     * </p>
     * @return A list of the visible child nodes.
     */
    @objid ("808c0867-1dec-11e2-8cad-001ec947c8cc")
    public List<GmNodeModel> getVisibleChildren() {
        List<GmNodeModel> ret = new ArrayList<>(this.children.size());
        for (GmNodeModel childNode : this.children) {
            if (childNode.isVisible()) {
                ret.add(childNode);
            }
        }
        return ret;
    }

    /**
     * Tells whether the node has children, visible or not.
     * @return true if the node has children, false in the other case.
     */
    @objid ("808c086e-1dec-11e2-8cad-001ec947c8cc")
    public final boolean hasChildren() {
        return !this.children.isEmpty();
    }

    /**
     * Move the given child element to the given position.
     * <p>
     * The element at the given position and all subsequent elements are shifted after the moved element.
     * @param child the child node to move
     * @param index the new index. If -1 the element is placed at the end.
     */
    @objid ("808c0873-1dec-11e2-8cad-001ec947c8cc")
    public final void moveChild(GmNodeModel child, int index) {
        int oldIndex = this.children.indexOf(child);
        
        if (oldIndex == -1) {
            throw new IllegalArgumentException("The element is not in the children list");
        }
        
        // If child already at asked position do nothing
        if (index == oldIndex) {
            return;
        }
        
        // If child already at asked last position do nothing
        if (index == -1 && oldIndex == this.children.size() - 1) {
            return;
        }
        
        this.children.remove(child);
        
        if (index == -1) {
            // Add to the end
            this.children.add(child);
        } else if (index > oldIndex) {
            // Child moved after old position,
            // Fix the index after removal.
            this.children.add(index - 1, child);
        } else {
            // Child moved before old position
            this.children.add(index, child);
        }
        
        firePropertyChange(IGmObject.PROPERTY_CHILDREN, null, child);
        
    }

    @objid ("808c0878-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = GmAbstractObject.readMinorVersion(in, "GmCompositeNode.");
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

    /**
     * Refresh completely the GM element from the Ob model.
     * <p>
     * The default implementation deletes all child nodes that are not valid anymore. Validation is done by calling
     * {@link #isValidChild(GmNodeModel)}.
     * <p>
     * This method may be redefined. In this case the inherited method must be called in order to clean obsolete children.
     */
    @objid ("808e6a7e-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void refreshFromObModel() {
        // Only try to refresh if correctly placed in Gm hierarchy and not a ghost.
        if (getRelatedElement() != null) {
            for (GmNodeModel gm : getChildren()) {
                if (!isValidChild(gm)) {
                    gm.delete();
                }
            }
        }
        
    }

    /**
     * Remove a child from the children list and fires a {@link IGmObject#PROPERTY_CHILDREN} property change event.
     * @param child The node to remove
     */
    @objid ("808e6a82-1dec-11e2-8cad-001ec947c8cc")
    public void removeChild(GmNodeModel child) {
        if (this.children.remove(child)) {
            firePropertyChange(IGmObject.PROPERTY_CHILDREN, child, null);
        
            child.setParent(null);
        } else {
            assert (false) : child + " is not owned by " + this;
        }
        
    }

    @objid ("808e6a86-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        out.writeProperty("Children", getChildren());
        
        // Write version of this Gm if different of 0.
        GmAbstractObject.writeMinorVersion(out, "GmCompositeNode.", GmCompositeNode.MINOR_VERSION);
        
    }

    /**
     * Tells whether the given node is a valid child for this node.
     * <p>
     * The given child may already or not be a child node of this node.
     * <p>
     * Implementations should check:
     * <ul>
     * <li>that the child node represented element can be unmasked in this node,
     * <li>that the child node represented element is really owned by this node represented element,
     * <li>that there is not already another child node representing the same element.
     * <li>that the child node verifies this node filters
     * </ul>
     * <p>
     * The default implementation returns true in all cases.
     * @param node A node
     * @return true if the node can be a child of this node, false in the other case.
     */
    @objid ("808e6a8a-1dec-11e2-8cad-001ec947c8cc")
    @SuppressWarnings ("static-method")
    protected boolean isValidChild(GmNodeModel node) {
        return true;
    }

    /**
     * Add a child to the children list at the given index and fires a {@link IGmObject#PROPERTY_CHILDREN} property change event.
     * @param child The node to add
     * @param index the index where the child will be added.
     */
    @objid ("808e6a91-1dec-11e2-8cad-001ec947c8cc")
    public void addChild(final GmNodeModel child, final int index) {
        this.children.add(index, child);
        child.setParent(this);
        
        // set child style
        firePropertyChange(IGmObject.PROPERTY_CHILDREN, null, child);
        
    }

    /**
     * Returns the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the
     * element.
     * @param child a node model.
     * @return the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the
     * element.
     */
    @objid ("808e6a98-1dec-11e2-8cad-001ec947c8cc")
    public final int getChildIndex(final GmNodeModel child) {
        return this.children.indexOf(child);
    }

    @objid ("808e6a9f-1dec-11e2-8cad-001ec947c8cc")
    private void read_0(IDiagramReader in) {
        super.read(in);
        
        // Reset existing children
        for (GmNodeModel c : new ArrayList<>(this.children)) {
            c.delete();
            this.children.remove(c);
        }
        
        final List<Object> listProperty = in.readListProperty("Children");
        for (Object c : listProperty) {
            final GmNodeModel childNode = (GmNodeModel) c;
            this.children.add(childNode);
            childNode.setParent(this);
        }
        firePropertyChange(IGmObject.PROPERTY_CHILDREN, null, this.children);
        
    }

    @objid ("808e6aa2-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public int getMajorVersion() {
        return GmCompositeNode.MAJOR_VERSION;
    }

    @objid ("39b29bb8-3854-4bfa-9595-143698022a78")
    @Override
    public boolean updateDiagram() {
        if (!super.updateDiagram()) {
            return false;
        } 
        
        // Propagate to children
        for (GmNodeModel c : getChildren()) {
            c.updateDiagram();
        }
        return true;
    }

}
