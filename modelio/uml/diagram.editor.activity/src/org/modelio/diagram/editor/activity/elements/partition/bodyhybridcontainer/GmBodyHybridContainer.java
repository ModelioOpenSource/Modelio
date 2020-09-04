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

package org.modelio.diagram.editor.activity.elements.partition.bodyhybridcontainer;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.activity.elements.partition.GmPartition;
import org.modelio.diagram.editor.activity.elements.partitioncontainer.GmPartitionContainer;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityNode;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityPartition;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Hybrid container that can behave either like a sub partition container OR like a free zone (NOT both at the same time).
 */
@objid ("2afb8879-55b6-11e2-877f-002564c97630")
public class GmBodyHybridContainer extends GmPartitionContainer {
    /**
     * Constant used to describe role of all inner nodes.
     */
    @objid ("2afb887b-55b6-11e2-877f-002564c97630")
    public static final String OWNED_NODE = "OwnedNode";

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("2afb887e-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("2afb8881-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Initialize the container.
     * @param diagram the diagram in which this partition container is used.
     * @param relatedRef represented element reference, must not be null.
     */
    @objid ("2afb8883-55b6-11e2-877f-002564c97630")
    public GmBodyHybridContainer(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
    }

    /**
     * Empty constructor needed for serialisation.
     */
    @objid ("2afb888c-55b6-11e2-877f-002564c97630")
    public GmBodyHybridContainer() {
        // Nothing to do.
    }

    /**
     * Overridden to delegate to parent (so that Inner nodes can also be accepted).
     */
    @objid ("2afb888f-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        // Parent node should know.
        return getParent().canCreate(type);
    }

    /**
     * Overridden to delegate to parent (so that Inner nodes can also be accepted).
     */
    @objid ("2afd0f19-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        // Parent node should know.
        return getParent().canUnmask(el);
    }

    @objid ("2afd0f22-55b6-11e2-877f-002564c97630")
    @Override
    protected boolean isValidChild(GmNodeModel node) {
        final MObject childEl = node.getRelatedElement();
        return childEl == null || (childEl.isValid() && canUnmask(childEl));
    }

    @objid ("2afd0f2a-55b6-11e2-877f-002564c97630")
    @Override
    public GmCompositeNode getCompositeFor(Class<? extends MObject> metaclass) {
        if (canCreate(metaclass))
            return this;
        // else
        return null;
    }

    /**
     * Redefined to set its own style cascading from the new parent node style.
     */
    @objid ("2afd0f34-55b6-11e2-877f-002564c97630")
    @Override
    protected void setParent(GmCompositeNode parent) {
        GmModel oldParent = getParent();
        
        // Call inherited
        super.setParent(parent);
        
        // Modify the style
        if (parent != null && !parent.equals(oldParent)) {
            getPersistedStyle().setCascadedStyle(parent.getPersistedStyle());
        }
    }

    @objid ("2afd0f3b-55b6-11e2-877f-002564c97630")
    @Override
    public void addChild(GmNodeModel child) {
        // Assign correct role to child: if not a sub partition, then its an inner node!
        if (child instanceof GmPartition) {
            // If we have inner nodes, transfer them to the sub partition.
            List<GmNodeModel> ownedNodes = getChildren(OWNED_NODE);
            if (!ownedNodes.isEmpty()) {
                for (GmNodeModel ownedNode : ownedNodes) {
                    // GM side
                    removeChild(ownedNode);
                    ((GmPartition) child).getCompositeFor(ownedNode.getRelatedElement().getClass())
                            .addChild(ownedNode);
                    // Ob side
                    ((ActivityPartition) this.getRelatedElement()).getContainedNode().remove(ownedNode.getRelatedElement());
                    ((ActivityPartition) child.getRelatedElement()).getContainedNode().add((ActivityNode) ownedNode.getRelatedElement());
                }
            }
        } else {
            child.setRoleInComposition(OWNED_NODE);
        }
        super.addChild(child);
    }

    @objid ("2afd0f41-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmBodyHybridContainer.");
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

    @objid ("2afd0f47-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmBodyHybridContainer.", GmBodyHybridContainer.MINOR_VERSION);
    }

    @objid ("2afd0f4d-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("2afd0f52-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
