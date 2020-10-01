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
import java.util.Objects;
import java.util.stream.Collectors;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.GmReference;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.model.IGmNode;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.model.IGmReference;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents a node representing a model element in the diagram.
 * <p>
 * A node may be owned either by a GmComposite or a GmLink but not both.
 */
@objid ("809591de-1dec-11e2-8cad-001ec947c8cc")
public abstract class GmNodeModel extends GmModel implements IGmNode {
    @objid ("8097f3f4-1dec-11e2-8cad-001ec947c8cc")
    private static final int MAJOR_VERSION = 0;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("8097f3f1-1dec-11e2-8cad-001ec947c8cc")
    private static final int MINOR_VERSION = 0;

    /**
     * The role played by this node in its parent.
     * <p>
     * Used to distinguish particular children in a composite node. eg: used to make distinction between the inner
     * classes zone and the internal structure zone. Used also to separate Ports from other children nodes.
     */
    @objid ("92472f9d-1e83-11e2-8cad-001ec947c8cc")
    protected String roleInComposition = "";

    @objid ("8097f3ed-1dec-11e2-8cad-001ec947c8cc")
    private final transient List<GmReference<IGmLink>> endingLinks = new ArrayList<>();

    /**
     * The GmLink owning this node.
     * <p>
     * A node may be owned either by a {@link GmCompositeNode} or a {@link GmLink} but not both.
     */
    @objid ("8097f3e5-1dec-11e2-8cad-001ec947c8cc")
    private GmLink parentLink = null;

    @objid ("809591e0-1dec-11e2-8cad-001ec947c8cc")
    private GmCompositeNode parentNode = null;

    @objid ("8097f3e9-1dec-11e2-8cad-001ec947c8cc")
    private final transient List<GmReference<IGmLink>> startingLinks = new ArrayList<>();

    /**
     * Constructor for deserialization only.
     */
    @objid ("8097f3f6-1dec-11e2-8cad-001ec947c8cc")
    public GmNodeModel() {
        super();
    }

    /**
     * Creates the node model.
     * 
     * @param diagram The diagram owning this element.
     * @param relatedRef a reference to the element this GmModel is related to.
     */
    @objid ("8097f3f9-1dec-11e2-8cad-001ec947c8cc")
    public GmNodeModel(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
    }

    /**
     * Add a link going to this element.
     * 
     * @param link the ongoing link.
     */
    @objid ("8097f3fe-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void addEndingLink(IGmLink link) {
        this.endingLinks.add(new GmReference<>(this, link));
        link.setTo(this);
        firePropertyChange(IGmObject.PROPERTY_LINK_TARGET, null, link);
    }

    /**
     * Add a link starting from this node.
     * 
     * @param link The starting link.
     */
    @objid ("8097f403-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void addStartingLink(IGmLink link) {
        this.startingLinks.add(new GmReference<>(this, link));
        link.setFrom(this);
        firePropertyChange(IGmObject.PROPERTY_LINK_SOURCE, null, link);
    }

    /**
     * Delete the element from the diagram.
     * <p>
     * First remove all links, then detach itself from its parent.
     */
    @objid ("8097f408-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void delete() {
        for (IGmReference<IGmLink> l : new ArrayList<>(this.startingLinks)) {
            l.delete();
        }
        for (IGmReference<IGmLink> l : new ArrayList<>(this.endingLinks)) {
            l.delete();
        }
        
        final GmCompositeNode gmParent = getParentNode();
        if (gmParent != null) {
            gmParent.removeChild(this);
        }
        
        final GmLink gmLink = getParentLink();
        if (gmLink != null) {
            gmLink.removeExtension(this);
        }
        
        super.delete();
    }

    /**
     * Get the links going to this node.
     * 
     * @return the ongoing links.
     */
    @objid ("8097f40c-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public List<IGmLink> getEndingLinks() {
        return this.endingLinks
                        .stream()
                        .filter(r -> r.isReferencedModelValid())
                        .map(r -> r.getReferencedModel())
                        .collect(Collectors.toList());
    }

    @objid ("809a566e-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public int getMajorVersion() {
        return GmNodeModel.MAJOR_VERSION;
    }

    /**
     * Get the GmModel owning this node.
     * <p>
     * The owner may be a {@link GmCompositeNode} or a {@link GmLink}.
     * 
     * @return the GmModel owning this node.
     */
    @objid ("8097f41e-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public final GmModel getParent() {
        if (this.parentNode != null) {
            return this.parentNode;
        } else {
            return this.parentLink;
        }
    }

    /**
     * Get the GmLink owning this node or <i>null</i> if the node is not owned by a GmLink.
     * 
     * @return the GmLink owning this node or <i>null</i> if the node is not owned by a GmLink.
     */
    @objid ("8097f414-1dec-11e2-8cad-001ec947c8cc")
    public final GmLink getParentLink() {
        return this.parentLink;
    }

    /**
     * Get the node owning this node.
     * 
     * @return the owner node.
     */
    @objid ("8097f419-1dec-11e2-8cad-001ec947c8cc")
    public final GmCompositeNode getParentNode() {
        return this.parentNode;
    }

    /**
     * Get the role played by this node in the parent node.
     * <p>
     * Never returns <tt>null</tt>.
     * 
     * @return the role played by this node in the parent node.
     */
    @objid ("8097f423-1dec-11e2-8cad-001ec947c8cc")
    public final String getRoleInComposition() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.roleInComposition;
    }

    @objid ("809a5664-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public List<IGmLink> getStartingLinks() {
        return this.startingLinks.stream()
                        .filter(r -> r.isReferencedModelValid())
                        .map(r -> r.getReferencedModel())
                        .collect(Collectors.toList());
    }

    /**
     * Same as {@link #getStyleKey(MetaKey)} but throws an exception if the style key is not found.
     * 
     * @param metakey The meta key of the style key to find.
     * @return The matching style key
     * @throws java.lang.IllegalStateException If no matching style key has been found.
     */
    @objid ("8097f428-1dec-11e2-8cad-001ec947c8cc")
    public final StyleKey getStyleKeyStrict(MetaKey metakey) throws IllegalStateException {
        final StyleKey ret = getStyleKey(metakey);
        
        if (ret == null) {
            if (getParent() == null) {
                throw new IllegalStateException("No style key for '" +
                        metakey.getKey() +
                        "' MetaKey on " +
                        toString());
            } else {
                throw new IllegalStateException("No style key for '" +
                        metakey.getKey() +
                        "' MetaKey on " +
                        toString() +
                        " in " +
                        getParent().toString());
            }
        }
        return ret;
    }

    /**
     * Tells whether a node is visible or not.
     * <p>
     * The default implementation returns true.
     * <p>
     * Subclasses may redefine this method. In this case they should redefine {@link #setVisible(boolean)} too.
     * 
     * @return true if the node and its parent are visible, false in the other cases.
     */
    @objid ("8097f42e-1dec-11e2-8cad-001ec947c8cc")
    public boolean isVisible() {
        if (getParentNode() != null) {
            return getParentNode().isVisible();
        } else {
            return true;
        }
    }

    @objid ("8097f432-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmNodeModel.");
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
     * Remove a link going to this node.
     * 
     * @param gmLink the link to remove.
     */
    @objid ("809a563f-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void removeEndingLink(IGmLink gmLink) {
        GmReference.removeFrom(this.endingLinks, gmLink);
        gmLink.setTo(null);
        firePropertyChange(IGmObject.PROPERTY_LINK_TARGET, gmLink, null);
    }

    /**
     * Remove a link starting from this node.
     * 
     * @param gmLink the link to remove.
     */
    @objid ("809a5644-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void removeStartingLink(IGmLink gmLink) {
        GmReference.removeFrom(this.startingLinks, gmLink);
        gmLink.setFrom(null);
        firePropertyChange(IGmObject.PROPERTY_LINK_SOURCE, gmLink, null);
    }

    /**
     * Set the link owning this node.
     * <p>
     * This node must not have a parent node. This method should be called only by
     * {@link GmLink#getExtension().add(String, GmNodeModel)}.
     * 
     * @param parentLink The GmLink owning this node.
     * @throws java.lang.IllegalStateException if the node has a parent node.
     */
    @objid ("809a5649-1dec-11e2-8cad-001ec947c8cc")
    public void setParentLink(GmLink parentLink) throws IllegalStateException {
        if (this.parentNode != null) {
            throw new IllegalStateException("The node has already " + this.parentNode + " as parent node");
        }
        
        this.parentLink = parentLink;
        updateDiagram();
    }

    /**
     * Set the GmCompositeNode owning this node.
     * 
     * @param parentNode The GmCompositeNode owning this node.
     */
    @objid ("809a5673-1dec-11e2-8cad-001ec947c8cc")
    public void setParentNode(final GmCompositeNode parentNode) {
        this.parentNode = parentNode;
        
        updateDiagram();
    }

    /**
     * Set the role played by this node in the parent node.
     * 
     * @param value the role played by this node in the parent node. Must not be <tt>null</tt>.
     */
    @objid ("809a564d-1dec-11e2-8cad-001ec947c8cc")
    public final void setRoleInComposition(String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.roleInComposition = value;
    }

    /**
     * Sets whether the node is visible or not and fire listeners.
     * <p>
     * Subclasses should redefine {@link #doSetVisible(boolean)} if {@link #isVisible()} is redefined.
     * 
     * @param visible whether the node must be visible
     */
    @objid ("809a5651-1dec-11e2-8cad-001ec947c8cc")
    public final void setVisible(boolean visible) {
        doSetVisible(visible);
        fireVisibilityChanged();
    }

    @objid ("809a5655-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        out.writeProperty("roleInComposition", this.roleInComposition);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmNodeModel.", GmNodeModel.MINOR_VERSION);
    }

    /**
     * Internally sets whether the node is visible or not.
     * <p>
     * This method is called by {@link #setVisible(boolean)} to set the internal flag.
     * <p>
     * The default implementation does nothing, nodes are always visible. Subclasses should redefine this method if
     * {@link #isVisible()} is redefined.
     * 
     * @param visible whether the node must be visible
     */
    @objid ("809a5659-1dec-11e2-8cad-001ec947c8cc")
    protected void doSetVisible(boolean visible) {
        // Nothing to do
    }

    /**
     * Fires a {@link IGmObject#PROPERTY_CHILDREN} event on the node parent.
     * <p>
     * To be called when the result of {@link #isVisible()} changes.
     */
    @objid ("809a565d-1dec-11e2-8cad-001ec947c8cc")
    protected final void fireVisibilityChanged() {
        final GmCompositeNode parent = getParentNode();
        if (parent != null) {
            parent.fireChildVisibilityChanged(this);
        }
        
        final GmLink link = getParentLink();
        if (link != null) {
            link.fireChildVisibilityChanged(this);
        }
    }

    /**
     * Set the node owning this node.
     * <p>
     * This node must not have a parent link.
     * 
     * @param parent The node owning this node
     * @throws java.lang.IllegalStateException if the node already has a parent link.
     */
    @objid ("809a5660-1dec-11e2-8cad-001ec947c8cc")
    protected void setParent(GmCompositeNode parent) throws IllegalStateException {
        if (this.parentLink != null) {
            throw new IllegalStateException("The node has already " + this.parentLink + " as parent link");
        }
        setParentNode(parent);
    }

    @objid ("809a566b-1dec-11e2-8cad-001ec947c8cc")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.roleInComposition = (String) in.readProperty("roleInComposition");
    }

    @objid ("0492b8ee-8fc0-4678-aef1-c8efb8265dd7")
    @Override
    public boolean updateDiagram() {
        GmModel newParentGm = getParent();
        
        IGmDiagram oldDiagram = getDiagram();
        if (newParentGm == null || oldDiagram == null || Objects.equals(newParentGm.getDiagram(), oldDiagram)) {
            return false;
        }
        
        IGmDiagram newDiagram = newParentGm.getDiagram();
        
        // Update the new child's diagram if necessary
        moveToDiagram(newDiagram);
        
        // Propagate to links
        this.startingLinks.forEach(r -> {
            r.setOwnerDiagram(newDiagram);
            if (r.isReferencedModelValid()) {
                r.getReferencedModel().updateDiagram();
            }
        });
        
        this.endingLinks.forEach(r -> {
            r.setOwnerDiagram(newDiagram);
            if (r.isReferencedModelValid()) {
                r.getReferencedModel().updateDiagram();
            }
        });
        return true;
    }

}
