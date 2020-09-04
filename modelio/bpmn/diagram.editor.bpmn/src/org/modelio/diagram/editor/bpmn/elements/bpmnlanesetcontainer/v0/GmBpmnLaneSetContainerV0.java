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

package org.modelio.diagram.editor.bpmn.elements.bpmnlanesetcontainer.v0;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.bpmn.elements.bpmnlane.GmBpmnLane;
import org.modelio.diagram.editor.bpmn.elements.bpmnlane.v0.GmBpmnLaneV0;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNoStyleCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLaneSet;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * The node (doesn't have anything corresponding in the ObModel) that contains partitions.
 * <p>
 * Used:
 * <ul>
 * <li>on the diagram background to hold top-level partitions
 * <li>and in partitions to hold sub-partitions.
 * </ul>
 */
@objid ("756a07a8-de0b-42ff-9988-f351c1a83d2d")
public class GmBpmnLaneSetContainerV0 extends GmNoStyleCompositeNode {
    @objid ("444cc50e-a16a-4046-94a4-457e44158f70")
    private static final int MAJOR_VERSION = 0;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("4fb31e68-848f-4fea-bdd8-2dc075f1332d")
    private static final int MINOR_VERSION = 0;

    /**
     * The orientation of this container. Constant used to describe role of subpartitions. * public static final String SUB_PARTITION = "Lane";
     */
    @objid ("dac4bc30-ffef-420b-b542-449f6d44432c")
    private BpmnLaneSet element;

    /**
     * @param diagram the diagram in which this partition container is used.
     * @param theLaneSet the element, may be null
     * @param relatedRef the related element reference, must not be null.
     */
    @objid ("b9952089-9e70-4585-a93c-3174d9c07d2a")
    public GmBpmnLaneSetContainerV0(IGmDiagram diagram, BpmnLaneSet theLaneSet, MRef relatedRef) {
        super(diagram, relatedRef);
        this.element = theLaneSet;
    }

    /**
     * Empty constructor needed for serialization.
     */
    @objid ("9bb182c3-107e-446a-83ef-be0529e19b25")
    public GmBpmnLaneSetContainerV0() {
        // Nothing to do.
    }

    @objid ("bb38f263-f9e7-4125-a410-e15066729e53")
    @Override
    public void addChild(final GmNodeModel child) {
        if (child instanceof GmBpmnLane) {
        
            GmBpmnLane lane = (GmBpmnLane) child;
        
            for (GmModel ownedNode : getChildren()) {
                if (ownedNode.getRelatedElement() instanceof BpmnFlowElement) {
                    // GM side
                    removeChild((GmNodeModel) ownedNode);
                    lane.getCompositeFor(ownedNode.getRelatedElement().getClass()).addChild((GmNodeModel) ownedNode);
        
                    // OB Side
                    BpmnFlowElement flowElement = (BpmnFlowElement) ownedNode.getRelatedElement();
        
                    for (BpmnLane elane : new ArrayList<>(flowElement.getLane())) {
                        flowElement.getLane().remove(elane);
                    }
        
                    flowElement.getLane().add((BpmnLane) lane.getRelatedElement());
                }
            }
        
        }
        
        super.addChild(child);
    }

    @objid ("88cbe977-7d50-4278-ac2c-34df464f34fb")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return BpmnLane.class.isAssignableFrom(type);
    }

    @objid ("58d93436-b756-4bc7-a051-889ef10baa5e")
    @Override
    public boolean canUnmask(MObject el) {
        return (BpmnLane.class.isAssignableFrom(el.getClass()) && el.getCompositionOwner()
                                        .equals(getRelatedElement()));
    }

    @objid ("6871a0bc-d677-48ef-9375-3f7d162c1e8c")
    @Override
    public GmCompositeNode getCompositeFor(Class<? extends MObject> metaclass) {
        if (BpmnLaneSet.class.isAssignableFrom(metaclass) || BpmnLane.class.isAssignableFrom(metaclass)) {
            return this;
        }
        return null;
    }

    @objid ("5647ffbc-85a8-44d7-b247-420b9a0fceff")
    @Override
    public int getMajorVersion() {
        return GmBpmnLaneSetContainerV0.MAJOR_VERSION;
    }

    /**
     * Get the contained {@link GmBpmnLaneV0} nodes.
     * 
     * @return a list of {@link GmBpmnLaneV0} nodes.
     */
    @objid ("108c69cc-4785-46eb-ade3-dd25926f7eae")
    public List<GmBpmnLaneV0> getPartitions() {
        List<GmBpmnLaneV0> partitions = new ArrayList<>();
        for (GmNodeModel p : getChildren()) {
            partitions.add((GmBpmnLaneV0) p);
        }
        return partitions;
    }

    @objid ("75b709eb-08e6-44b5-9b0a-4a7674374418")
    @Override
    public MObject getRelatedElement() {
        return this.element;
    }

    @objid ("ccdd88fc-1797-4f95-a220-e8e9cafb0b2a")
    @Override
    public RepresentationMode getRepresentationMode() {
        return RepresentationMode.STRUCTURED;
    }

    @objid ("f70cb847-e837-487c-b54d-c2ab1bd75e98")
    @Override
    public BpmnLaneSet getRepresentedElement() {
        return this.element;
    }

    @objid ("ce5fd1b5-ea0c-42e2-b2b8-a3f221a2b0da")
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

    @objid ("1013469f-e48d-49cd-8b44-568000c07469")
    @Override
    public void removeChild(GmNodeModel child) {
        super.removeChild(child);
        // If removed child was the last, delete self.
        if (!hasChildren() && child instanceof GmBpmnLane) {
            delete();
        }
    }

    @objid ("0552dfa0-a730-45d8-b504-b1a5354d1e69")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        // Write version of this Gm if different of 0
        GmAbstractObject.writeMinorVersion(out, "GmBpmnLaneSetContainer.", GmBpmnLaneSetContainerV0.MINOR_VERSION);
    }

    @objid ("aa84ba66-e7bf-499a-b561-a7c6f00d50de")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.element = (BpmnLaneSet) resolveRef(getRepresentedRef());
    }

}
