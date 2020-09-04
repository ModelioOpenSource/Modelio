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

package org.modelio.diagram.editor.bpmn.elements.bpmnlanesetcontainer;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.bpmn.elements.bpmnlane.GmBpmnLane;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNoStyleCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLaneSet;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * The node that contains lanes.
 * <p>
 * Used:
 * <ul>
 * <li>on the diagram background to hold top-level lanes.
 * <li>and in lanes to hold sub-lanes or elements.
 * </ul>
 */
@objid ("6143b668-55b6-11e2-877f-002564c97630")
public class GmBpmnLaneSetContainer extends GmNoStyleCompositeNode {
    /**
     * The orientation of this container. Constant used to describe role of subpartitions. * public static final String SUB_PARTITION = "Lane";
     */
    @objid ("6143b66c-55b6-11e2-877f-002564c97630")
    private BpmnLaneSet element;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("6143b670-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("6143b673-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("6143b675-55b6-11e2-877f-002564c97630")
    @Override
    public GmCompositeNode getCompositeFor(Class<? extends MObject> metaclass) {
        if (BpmnLaneSet.class.isAssignableFrom(metaclass) || BpmnLane.class.isAssignableFrom(metaclass)) {
            return this;
        }
        return null;
    }

    @objid ("6143b67f-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return BpmnLane.class.isAssignableFrom(type);
    }

    @objid ("6143b687-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        return (BpmnLane.class.isAssignableFrom(el.getClass()) && el.getCompositionOwner().equals(getRelatedElement()));
    }

    @objid ("6143b68f-55b6-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        return RepresentationMode.STRUCTURED;
    }

    @objid ("6143b696-55b6-11e2-877f-002564c97630")
    @Override
    public BpmnLaneSet getRepresentedElement() {
        return this.element;
    }

    /**
     * @param diagram the diagram in which this lane set container is used.
     * @param theLaneSet the element, may be null
     * @param relatedRef the related element reference, must not be null.
     */
    @objid ("6143b69d-55b6-11e2-877f-002564c97630")
    public GmBpmnLaneSetContainer(IGmDiagram diagram, BpmnLaneSet theLaneSet, MRef relatedRef) {
        super(diagram, relatedRef);
        this.element = theLaneSet;
    }

    /**
     * Empty constructor needed for serialization.
     */
    @objid ("61453cfc-55b6-11e2-877f-002564c97630")
    public GmBpmnLaneSetContainer() {
        // Nothing to do.
    }

    @objid ("61453cff-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = GmAbstractObject.readMinorVersion(in, "GmBpmnLaneSetContainer.");
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

    @objid ("61453d05-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        // Write version of this Gm if different of 0
        GmAbstractObject.writeMinorVersion(out, "GmBpmnLaneSetContainer.", GmBpmnLaneSetContainer.MINOR_VERSION);
    }

    /**
     * Get the contained {@link GmBpmnLane} nodes.
     * 
     * @return a list of {@link GmBpmnLane} nodes.
     */
    @objid ("61453d0b-55b6-11e2-877f-002564c97630")
    public List<GmBpmnLane> getLanes() {
        List<GmBpmnLane> partitions = new ArrayList<>();
        for (GmNodeModel p : getChildren()) {
            partitions.add((GmBpmnLane) p);
        }
        return partitions;
    }

    @objid ("61453d12-55b6-11e2-877f-002564c97630")
    @Override
    public void removeChild(GmNodeModel child) {
        super.removeChild(child);
    }

    @objid ("61453d18-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return this.element;
    }

    @objid ("61453d26-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.element = (BpmnLaneSet) resolveRef(getRepresentedRef());
    }

    @objid ("61453d2b-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmBpmnLaneSetContainer.MAJOR_VERSION;
    }

    @objid ("3b73d198-b28c-4768-99f5-466270709d8a")
    @Override
    public void refreshFromObModel() {
        super.refreshFromObModel();
        
        if (this.element == null || !this.element.isValid()) {
            return;
        }
        
        // If no children exist, delete self.
        List<BpmnLane> lanes = this.element.getLane();
        if (lanes.isEmpty()) {
            delete();
        } else {
            // Auto unmask lanes
            for (BpmnLane lane : lanes) {
                if (getChild(new MRef(lane)) == null) {
                    getDiagram().unmask(this, lane, -1);
                }
            }
        }
    }

    @objid ("11933942-5a5b-41c1-920a-3bcd82ac6373")
    @Override
    public void addChild(GmNodeModel child) {
        super.addChild(child);
    }

}
