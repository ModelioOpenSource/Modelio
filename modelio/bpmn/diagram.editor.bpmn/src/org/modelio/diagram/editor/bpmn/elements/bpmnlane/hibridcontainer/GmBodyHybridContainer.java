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

package org.modelio.diagram.editor.bpmn.elements.bpmnlane.hibridcontainer;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.bpmn.elements.bpmnlanesetcontainer.GmBpmnLaneSetContainer;
import org.modelio.diagram.elements.common.freezone.GmFreeZone;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Hybrid container that can behave either like a sub lane container OR like a free zone (NOT both at the same time).
 */
@objid ("6135faff-55b6-11e2-877f-002564c97630")
public class GmBodyHybridContainer extends GmFreeZone {
    /**
     * Constant used to describe role of all inner nodes.
     */
    @objid ("6135fb03-55b6-11e2-877f-002564c97630")
    public static final String OWNED_NODE = "OwnedNode";

    @objid ("6135fb06-55b6-11e2-877f-002564c97630")
    public static final String SUB_LANE = "Lane";

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("6137815a-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("6137815d-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * @param diagram the diagram in which this lane container is used.
     * @param relatedRef ref
     */
    @objid ("6137815f-55b6-11e2-877f-002564c97630")
    public GmBodyHybridContainer(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
    }

    /**
     * Empty constructor needed for serialisation.
     */
    @objid ("61378168-55b6-11e2-877f-002564c97630")
    public GmBodyHybridContainer() {
        // Nothing to do.
    }

    /**
     * Overridden to delegate to parent (so that Inner nodes can also be accepted).
     */
    @objid ("6137816b-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        // Parent node should know.
        return getParent().canCreate(type);
    }

    /**
     * Overridden to delegate to parent (so that Inner nodes can also be accepted).
     */
    @objid ("61378174-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        // Parent node should know.
        return getParent().canUnmask(el);
    }

    @objid ("6137817d-55b6-11e2-877f-002564c97630")
    @Override
    protected boolean isValidChild(GmNodeModel node) {
        final MObject childEl = node.getRelatedElement();
        return childEl == null || (childEl.isValid() && canUnmask(childEl));
    }

    @objid ("61378185-55b6-11e2-877f-002564c97630")
    @Override
    public GmCompositeNode getCompositeFor(Class<? extends MObject> metaclass) {
        if (canCreate(metaclass)) {
            return this;
        }
        // else
        return null;
    }

    /**
     * Redefined to set its own style cascading from the new parent node style.
     */
    @objid ("6137818f-55b6-11e2-877f-002564c97630")
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

    @objid ("61378196-55b6-11e2-877f-002564c97630")
    @Override
    public void addChild(GmNodeModel child) {
        // Assign correct role to child: if not a sub lane, then its an inner node!
        if (child instanceof GmBpmnLaneSetContainer) {
            child.setRoleInComposition(GmBodyHybridContainer.SUB_LANE);
        } else {
            child.setRoleInComposition(GmBodyHybridContainer.OWNED_NODE);
        }
        super.addChild(child);
    }

    @objid ("613907fe-55b6-11e2-877f-002564c97630")
    @Override
    protected void doSetVisible(boolean visible) {
        if (visible) {
            StyleKey key = getStyleKey(MetaKey.REPMODE);
            if (key != null) {
                getParent().getDisplayedStyle().setProperty(key, RepresentationMode.STRUCTURED);
            }
        }
    }

    @objid ("61390802-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isVisible() {
        return true;
    }

    @objid ("61390807-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = GmAbstractObject.readMinorVersion(in, "GmBodyHybridContainer.");
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

    @objid ("6139080d-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        GmAbstractObject.writeMinorVersion(out, "GmBodyHybridContainer.", GmBodyHybridContainer.MINOR_VERSION);
    }

    @objid ("61390813-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("61390818-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmBodyHybridContainer.MAJOR_VERSION;
    }

}
