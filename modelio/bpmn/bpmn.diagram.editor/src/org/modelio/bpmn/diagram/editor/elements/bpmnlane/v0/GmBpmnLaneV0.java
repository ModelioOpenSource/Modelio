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
package org.modelio.bpmn.diagram.editor.elements.bpmnlane.v0;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.bpmn.diagram.editor.elements.bpmnlane.header.GmBpmnLaneHeader;
import org.modelio.bpmn.diagram.editor.elements.bpmnlane.hibridcontainer.GmBodyHybridContainer;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLaneSet;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnParticipant;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * This class represents the Gm of a Partition.
 */
@objid ("8047df30-c53f-43d2-b587-a8c75db8ca48")
public class GmBpmnLaneV0 extends GmCompositeNode {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("ee78e4ba-63cb-4979-a3e6-915367c20a5f")
    private static final int MINOR_VERSION = 0;

    @objid ("8e5600bc-6e4b-4968-9cb7-70fd891aa2bc")
    private static final int MAJOR_VERSION = 0;

    /**
     * For migration purpose, the element represented by this Gm might be a {@link BpmnLane} or a {@link BpmnParticipant}.
     */
    @objid ("0c7b3f5d-9ff2-4d25-8f79-635f899c6fbd")
    private BpmnBaseElement element; /* // BpmnLane or BpmnParticipant
     */

    @objid ("cd4b586f-172d-4eff-85cb-1125b279a122")
    private static GmBpmnLaneStructuredStyleKeysV0 STRUCTKEYS = new GmBpmnLaneStructuredStyleKeysV0();

    /**
     * Header
     */
    @objid ("6b16f7a1-4602-478b-8ed1-133c4f63b729")
    private GmBpmnLaneHeader header;

    /**
     * Free zone
     */
    @objid ("d1acc572-ab97-407c-8530-c1c9d7dab5a5")
    private GmBodyHybridContainer body;

    /**
     * Default constructor.
     * @param diagram the diagram in which this partition will be unmasked.
     * @param theLane the unmasked partition (can be null).
     * @param ref a reference to the unmasked partition (cannot be null).
     */
    @objid ("170d23e5-bdc8-4bcc-8b43-83cdaaa1c866")
    public  GmBpmnLaneV0(IGmDiagram diagram, BpmnLane theLane, MRef ref) {
        super(diagram, ref);
        this.element = theLane;
        this.header = new GmBpmnLaneHeader(diagram, ref);
        this.body = new GmBodyHybridContainer(diagram, ref);
        super.addChild(this.header);
        super.addChild(this.body);
        
    }

    @objid ("31bc6f96-e21a-4fa2-a3dd-57d2b87ab549")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return acceptMetaclass(type);
    }

    @objid ("b4ed214e-416c-4acd-a390-574d9e2991b4")
    @Override
    public boolean canUnmask(MObject el) {
        Class<? extends MObject> type = el.getClass();
        if (el instanceof BpmnLaneSet) {
            return el.getCompositionOwner().equals(this.element) && acceptMetaclass(type);
        } else if (el instanceof BpmnLane) {
            return el.getCompositionOwner().getCompositionOwner().equals(this.element) &&
                    acceptMetaclass(type);
        } else if (el instanceof BpmnFlowElement) {
            if (this.element instanceof BpmnLane) {
                return ((BpmnLane) this.element).getFlowElementRef().contains(el);
            } else {
                return ((BpmnParticipant) this.element).getProcess().getFlowElement().contains(el);
            }
        }
        return false;
    }

    @objid ("c3014074-a78a-48d5-86ea-cc64a78177c8")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        return STRUCTKEYS.getStyleKey(metakey);
    }

    @objid ("1566840c-fd66-4bec-98c6-aee4b7a21542")
    @Override
    public List<StyleKey> getStyleKeys() {
        return STRUCTKEYS.getStyleKeys();
    }

    @objid ("bc1fe9a1-4690-4434-9966-ef52dbab667e")
    @Override
    public RepresentationMode getRepresentationMode() {
        return RepresentationMode.STRUCTURED;
    }

    @objid ("f57602d8-9125-44aa-8e49-12979da6462c")
    @Override
    public GmCompositeNode getCompositeFor(Class<? extends MObject> metaclass) {
        if (acceptMetaclass(metaclass)) {
            return this.body;
        }
        // else
        return null;
    }

    /**
     * Empty constructor needed for serialisation.
     */
    @objid ("4c17bb08-59ec-4fef-bc36-df251f31b4cd")
    public  GmBpmnLaneV0() {
        // Nothing to do.
    }

    @objid ("8e0ec976-0d59-43ed-bd94-077b5867dc38")
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

    /**
     * @return The partition content area.
     */
    @objid ("6d9605c4-a383-4438-b5f8-6d535823b6a2")
    public GmCompositeNode getContentsArea() {
        return this.body;
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
    @objid ("915bf38b-e790-4de2-be10-0cf67154003b")
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

    @objid ("aa76daa2-c9cc-48b5-af17-bd17391e8345")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmBpmnLane.", MINOR_VERSION);
        
    }

    @objid ("844f1f05-98d9-42a4-99e4-0ad1cd69d75c")
    @Override
    public MObject getRepresentedElement() {
        return this.element;
    }

    @objid ("df718bae-97d2-40f8-aaa1-2d9cfc34269b")
    @Override
    public MObject getRelatedElement() {
        return this.element;
    }

    @objid ("06f7cb72-aafd-47af-9de3-8f49850a98ca")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.header = (GmBpmnLaneHeader) this.getChildren().get(0);
        this.body = (GmBodyHybridContainer) this.getChildren().get(1);
        this.element = (BpmnBaseElement) resolveRef(getRepresentedRef());
        
    }

    @objid ("a0216dad-2763-4075-b8e9-e5141e2c0e48")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @objid ("b754012d-03c1-4052-9070-7387429236b7")
    public GmBpmnLaneHeader getHeader() {
        return this.header;
    }

    @objid ("258ed1f2-53c8-4775-95c4-6c80c8703c75")
    public GmBodyHybridContainer getBody() {
        return this.body;
    }

}
