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

package org.modelio.diagram.editor.bpmn.elements.bpmntransaction;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.PositionConstants;
import org.modelio.diagram.editor.bpmn.elements.style.GmBpmnSubProcessImageStyleKeys;
import org.modelio.diagram.editor.bpmn.elements.style.GmBpmnSubProcessSimpleStyleKeys;
import org.modelio.diagram.editor.bpmn.elements.style.GmBpmnSubProcessStructuredStyleKeys;
import org.modelio.diagram.editor.bpmn.elements.style.GmBpmnSubProcessUserImageStyleKeys;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.common.portcontainer.GmPortContainer;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.bpmn.activities.BpmnTransaction;
import org.modelio.metamodel.bpmn.events.BpmnBoundaryEvent;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Specialisation of the GmPortContainer class for BpmnTransaction .
 */
@objid ("61d93fbd-55b6-11e2-877f-002564c97630")
public class GmBpmnTransaction extends GmPortContainer {
    @objid ("61d93fcf-55b6-11e2-877f-002564c97630")
    private static final String IMAGE_LABEL_ROLE = "ImageLabel";

    @objid ("61d93fcd-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Current version of this Gm.
     */
    @objid ("61d93fca-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 1;

    @objid ("61d93fc7-55b6-11e2-877f-002564c97630")
    private BpmnTransaction element;

    @objid ("c45e3c32-59a6-11e2-ae45-002564c97630")
     static final GmBpmnSubProcessImageStyleKeys IMAGE_KEYS = new GmBpmnSubProcessImageStyleKeys();

    @objid ("c45e3c30-59a6-11e2-ae45-002564c97630")
     static final GmBpmnSubProcessSimpleStyleKeys SIMPLE_KEYS = new GmBpmnSubProcessSimpleStyleKeys();

    @objid ("c45e3c2e-59a6-11e2-ae45-002564c97630")
     static final GmBpmnSubProcessStructuredStyleKeys STRUCTURED_KEYS = new GmBpmnSubProcessStructuredStyleKeys();

    @objid ("5da40729-56e2-4bb0-b8e8-e793f02b1d56")
     static final GmBpmnSubProcessUserImageStyleKeys USERIMAGE_KEYS = new GmBpmnSubProcessUserImageStyleKeys();

    /**
     * Constructor.
     * @param diagram the diagram in which the BpmnReceiveTask is unmasked.
     * @param element the unmasked BpmnReceiveTask.
     * @param ref a reference to the unmasked callOperation.
     */
    @objid ("61d93fd1-55b6-11e2-877f-002564c97630")
    public GmBpmnTransaction(IGmDiagram diagram, BpmnTransaction element, MRef ref) {
        super(diagram, ref);
        this.element = element;
        
        GmBpmnTransactionPrimaryNode mainNode = new GmBpmnTransactionPrimaryNode(diagram, ref);
        mainNode.setRoleInComposition(GmPortContainer.MAIN_NODE_ROLE);
        
        GmDefaultModelElementLabel imageModeHeader = new GmDefaultModelElementLabel(diagram, ref);
        imageModeHeader.setRoleInComposition(GmBpmnTransaction.IMAGE_LABEL_ROLE);
        imageModeHeader.setLayoutData(Integer.valueOf(PositionConstants.SOUTH));
        
        super.addChild(mainNode);
        super.addChild(imageModeHeader);
    }

    /**
     * Empty constructor needed for deserialisation.
     */
    @objid ("61dac655-55b6-11e2-877f-002564c97630")
    public GmBpmnTransaction() {
        // Nothing specific to do.
    }

    @objid ("61d93fdd-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        if (BpmnBoundaryEvent.class.isAssignableFrom(type)) {
            return true;
        }
        return false;
    }

    @objid ("61dac63c-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        if (BpmnBoundaryEvent.class.isAssignableFrom(el.getClass())) {
            BpmnBoundaryEvent bevent = (BpmnBoundaryEvent) el;
            if (Objects.equals(bevent.getAttachedToRef(), this.element)) {
                return true;
            }
        }
        return false;
    }

    @objid ("61dac677-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmBpmnTransaction.MAJOR_VERSION;
    }

    @objid ("61dac665-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return this.element;
    }

    @objid ("61dac65e-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRepresentedElement() {
        return this.element;
    }

    @objid ("61dac644-55b6-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        switch (getRepresentationMode()) {
        case IMAGE:
            return GmBpmnTransaction.IMAGE_KEYS.getStyleKey(metakey);
        case USER_IMAGE:
            return GmBpmnTransaction.USERIMAGE_KEYS.getStyleKey(metakey);
        case SIMPLE:
            return GmBpmnTransaction.SIMPLE_KEYS.getStyleKey(metakey);
        case STRUCTURED:
            return GmBpmnTransaction.STRUCTURED_KEYS.getStyleKey(metakey);
        default:
            return null;
        }
    }

    @objid ("61dac64d-55b6-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        switch (getRepresentationMode()) {
        case IMAGE:
            return GmBpmnTransaction.IMAGE_KEYS.getStyleKeys();
        case USER_IMAGE:
            return GmBpmnTransaction.USERIMAGE_KEYS.getStyleKeys();
        case SIMPLE:
            return GmBpmnTransaction.SIMPLE_KEYS.getStyleKeys();
        case STRUCTURED:
            return GmBpmnTransaction.STRUCTURED_KEYS.getStyleKeys();
        default:
            return Collections.emptyList();
        }
    }

    @objid ("61dc4cde-55b6-11e2-877f-002564c97630")
    @Override
    public List<GmNodeModel> getVisibleChildren() {
        List<GmNodeModel> ret = super.getVisibleChildren();
        
        // Returned result depends on current representation mode of the primary node
        GmNodeModel firstChild = getMainNode();
        
        if (firstChild == null) {
            // Remove the header used for image mode.
            ret.remove(getFirstChild(GmBpmnTransaction.IMAGE_LABEL_ROLE));
        } else {
            switch (firstChild.getRepresentationMode()) {
            case IMAGE:
            case USER_IMAGE:
                break;
            default:
                // Remove the header used for image mode.
                ret.remove(getFirstChild(GmBpmnTransaction.IMAGE_LABEL_ROLE));
            }
        }
        return ret;
    }

    @objid ("bbfc59b3-eb0c-44c4-87d3-fe90d29ae3a6")
    @Override
    public boolean isMainSatelliteLabel(GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return role.equals(GmBpmnTransaction.IMAGE_LABEL_ROLE);
    }

    /**
     * Is this node a Port, which position is defined relatively to the Main Node's bounds.
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Port.
     */
    @objid ("61dc4cf9-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isPort(final GmNodeModel childNode) {
        return GmPortContainer.PORT_ROLE.equals(childNode.getRoleInComposition());
    }

    /**
     * Is this node a Satellite, which position is defined relatively to the Main Node's bounds.
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Satellite.
     */
    @objid ("61dc4ce7-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSatellite(final GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return GmPortContainer.SATELLITE_ROLE.equals(role)
                        || GmBpmnTransaction.IMAGE_LABEL_ROLE.equals(role);
    }

    @objid ("61dac658-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmBpmnTransaction.");
        switch (readVersion) {
        case 0: {
            read_0(in);
            break;
        }
        case 1: {
            read_1(in);
            break;
        }
        default: {
            assert (false) : "version number not covered!";
            // reading as last handled version: 1
            read_1(in);
            break;
        }
        }
    }

    @objid ("61dac66c-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmBpmnTransaction.", GmBpmnTransaction.MINOR_VERSION);
    }

    @objid ("61dac672-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.element = (BpmnTransaction) resolveRef(getRepresentedRef());
        
        GmDefaultModelElementLabel imageModeHeader = new GmDefaultModelElementLabel(getDiagram(), getRepresentedRef());
        imageModeHeader.setRoleInComposition(GmBpmnTransaction.IMAGE_LABEL_ROLE);
        imageModeHeader.setLayoutData(Integer.valueOf(PositionConstants.SOUTH));
        
        super.addChild(imageModeHeader, 1);
    }

    @objid ("61dac67c-55b6-11e2-877f-002564c97630")
    private void read_1(final IDiagramReader in) {
        super.read(in);
        this.element = (BpmnTransaction) resolveRef(getRepresentedRef());
    }

}
