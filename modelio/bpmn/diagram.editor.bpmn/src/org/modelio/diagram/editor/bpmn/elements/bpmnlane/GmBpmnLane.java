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

package org.modelio.diagram.editor.bpmn.elements.bpmnlane;

import java.util.List;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.bpmn.elements.bpmnlane.header.GmBpmnLaneHeader;
import org.modelio.diagram.editor.bpmn.elements.bpmnlane.hibridcontainer.GmBodyHybridContainer;
import org.modelio.diagram.editor.bpmn.elements.bpmnlane.v0.GmBpmnLaneV0;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnCollaboration;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLaneSet;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * This class represents the Gm of a Partition.
 */
@objid ("6118fd10-55b6-11e2-877f-002564c97630")
public class GmBpmnLane extends GmCompositeNode {
    /**
     * Current major version of this Gm. See {@link GmBpmnLaneMigrator}.
     */
    @objid ("6118fd20-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 1;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("6118fd1d-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    /**
     * The represented lane.
     */
    @objid ("6118fd19-55b6-11e2-877f-002564c97630")
    private BpmnLane element; // BpmnLane or BpmnParticipant

    @objid ("c471472d-59a6-11e2-ae45-002564c97630")
    private static GmBpmnLaneStructuredStyleKeys STRUCTKEYS = new GmBpmnLaneStructuredStyleKeys();

    /**
     * Free zone
     */
    @objid ("c4714730-59a6-11e2-ae45-002564c97630")
    private GmBodyHybridContainer body;

    /**
     * Header
     */
    @objid ("c471472e-59a6-11e2-ae45-002564c97630")
    private GmBpmnLaneHeader header;

    /**
     * Default constructor.
     * @param diagram the diagram in which this partition will be unmasked.
     * @param theLane the unmasked partition (can be null).
     * @param ref a reference to the unmasked partition (cannot be null).
     */
    @objid ("611a837a-55b6-11e2-877f-002564c97630")
    public GmBpmnLane(IGmDiagram diagram, BpmnLane theLane, MRef ref) {
        super(diagram, ref);
        this.element = theLane;
        this.header = new GmBpmnLaneHeader(diagram, ref);
        this.body = new GmBodyHybridContainer(diagram, ref);
        super.addChild(this.header);
        super.addChild(this.body);
    }

    /**
     * Empty constructor needed for serialisation.
     */
    @objid ("611a83ba-55b6-11e2-877f-002564c97630")
    public GmBpmnLane() {
        // Nothing to do.
    }

    @objid ("611a8386-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return acceptMetaclass(type);
    }

    @objid ("611a838e-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        Class<? extends MObject> type = el.getClass();
        if (el instanceof BpmnLaneSet) {
            return el.getCompositionOwner().equals(this.element) && acceptMetaclass(type);
        } else if (el instanceof BpmnLane) {
            return el.getCompositionOwner().getCompositionOwner().equals(this.element) && acceptMetaclass(type);
        } else if (el instanceof BpmnFlowElement) {
            if (this.element.getFlowElementRef().contains(el)) {
                return true;
            } else {
                return getDiagram().isUserEditable() && Objects.equals(findParentCollaboration(el), findParentCollaboration(this.element));
            }
        }
        return false;
    }

    @objid ("611a83b0-55b6-11e2-877f-002564c97630")
    @Override
    public GmCompositeNode getCompositeFor(Class<? extends MObject> metaclass) {
        if (acceptMetaclass(metaclass)) {
            return this.body;
        }
        // else
        return null;
    }

    /**
     * @return The partition content area.
     */
    @objid ("611c0a1c-55b6-11e2-877f-002564c97630")
    public GmCompositeNode getContentsArea() {
        return this.body;
    }

    @objid ("611c0a44-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmBpmnLane.MAJOR_VERSION;
    }

    @objid ("611c0a38-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return this.element;
    }

    @objid ("611a83a9-55b6-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        return RepresentationMode.STRUCTURED;
    }

    @objid ("611c0a31-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRepresentedElement() {
        return this.element;
    }

    @objid ("611a8396-55b6-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        return GmBpmnLane.STRUCTKEYS.getStyleKey(metakey);
    }

    @objid ("611a83a0-55b6-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        return GmBpmnLane.STRUCTKEYS.getStyleKeys();
    }

    @objid ("611a83bd-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmBpmnLane.");
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

    @objid ("611c0a2b-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmBpmnLane.", GmBpmnLane.MINOR_VERSION);
    }

    /**
     * Tells whether elements of the given metaclass can be unmasked inside this graphic node.
     * <p>
     * This method should return true only if it is consistent to display the given metaclass elements inside this graphic element.
     * <p>
     * <b>eg:</b> IAttributes can be displayed in a GmClass .
     * @param type The metaclass to unmask.
     * @return true only if it is consistent to display elements of the given metaclass inside this graphic element, false in the other cases.
     */
    @objid ("611c0a23-55b6-11e2-877f-002564c97630")
    private boolean acceptMetaclass(Class<? extends MObject> type) {
        boolean accept = false;
        // If there are no subpartitions yet, we can accept inner nodes and
        // edges.
        if (this.body.getChildren(GmBodyHybridContainer.SUB_LANE).isEmpty()) {
            accept = BpmnFlowElement.class.isAssignableFrom(type);
        }
        
        // We can always accept sub partitions: if things come to the worst, all
        // inner nodes and edges will be transfered to the first subpartition.
        accept = accept || BpmnLaneSet.class.isAssignableFrom(type);
        accept = accept || BpmnLane.class.isAssignableFrom(type);
        return accept;
    }

    @objid ("611c0a3f-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.header = (GmBpmnLaneHeader) this.getChildren().get(0);
        this.body = (GmBodyHybridContainer) this.getChildren().get(1);
        this.element = (BpmnLane) resolveRef(getRepresentedRef());
    }

    @objid ("4906081c-b4b0-4292-85e3-a9950913bda5")
    GmBpmnLane(final GmBpmnLaneV0 oldVersionGm) {
        super(oldVersionGm.getDiagram(), oldVersionGm.getRepresentedRef());
        this.element = (BpmnLane) oldVersionGm.getRepresentedElement();
        
        this.header = oldVersionGm.getHeader();
        
        this.body = oldVersionGm.getBody();
        
        oldVersionGm.removeChild(this.header);
        super.addChild(this.header);
        oldVersionGm.removeChild(this.body);
        super.addChild(this.body);
    }

    @objid ("71c08734-6644-4aa7-b083-ef8eb9fd0a46")
    @Override
    public void addChild(GmNodeModel child) {
        this.body.addChild(child);
    }

    /**
     * @return the lane body container.
     */
    @objid ("21a9490f-1755-47ee-a543-d9a1badf935e")
    public GmBodyHybridContainer getBody() {
        return this.body;
    }

    @objid ("926ca3c9-5ae3-4e4b-a818-93e8a2e3befe")
    private BpmnCollaboration findParentCollaboration(MObject elt) {
        MObject owner = elt.getCompositionOwner();
        if (owner == null) {
            return null;
        } else if (owner instanceof BpmnCollaboration) {
            return (BpmnCollaboration) owner;
        } else {
            return findParentCollaboration(owner);
        }
    }

}
