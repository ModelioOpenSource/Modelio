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

package org.modelio.diagram.editor.bpmn.elements.bpmnadhocsubprocess;

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
import org.modelio.metamodel.bpmn.activities.BpmnAdHocSubProcess;
import org.modelio.metamodel.bpmn.events.BpmnBoundaryEvent;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Specialisation of the GmPortContainer class for AdHocSubProcess.
 */
@objid ("607bd290-55b6-11e2-877f-002564c97630")
public class GmBpmnAdHocSubProcess extends GmPortContainer {
    @objid ("607bd29a-55b6-11e2-877f-002564c97630")
    private BpmnAdHocSubProcess element;

    /**
     * Current version of this Gm.
     */
    @objid ("607bd29d-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 1;

    @objid ("607bd2a0-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("607bd2a2-55b6-11e2-877f-002564c97630")
    private static final String IMAGE_LABEL_ROLE = "ImageLabel";

    @objid ("c53b9c0d-59a6-11e2-ae45-002564c97630")
     static final GmBpmnSubProcessStructuredStyleKeys STRUCTURED_KEYS = new GmBpmnSubProcessStructuredStyleKeys();

    @objid ("c53b9c0f-59a6-11e2-ae45-002564c97630")
     static final GmBpmnSubProcessSimpleStyleKeys SIMPLE_KEYS = new GmBpmnSubProcessSimpleStyleKeys();

    @objid ("c53b9c11-59a6-11e2-ae45-002564c97630")
     static final GmBpmnSubProcessImageStyleKeys IMAGE_KEYS = new GmBpmnSubProcessImageStyleKeys();

    @objid ("14c69647-08b4-4bdd-99ac-825056898698")
     static final GmBpmnSubProcessUserImageStyleKeys USERIMAGE_KEYS = new GmBpmnSubProcessUserImageStyleKeys();

    /**
     * Constructor.
     * 
     * @param diagram the diagram in which the BpmnReceiveTask is unmasked.
     * @param element the unmasked BpmnReceiveTask.
     * @param ref a reference to the unmasked callOperation.
     */
    @objid ("607bd2a4-55b6-11e2-877f-002564c97630")
    public GmBpmnAdHocSubProcess(IGmDiagram diagram, BpmnAdHocSubProcess element, MRef ref) {
        super(diagram, ref);
        this.element = element;
        
        GmBpmnAdHocSubProcessPrimaryNode mainNode = new GmBpmnAdHocSubProcessPrimaryNode(diagram, ref);
        mainNode.setRoleInComposition(GmPortContainer.MAIN_NODE_ROLE);
        
        GmDefaultModelElementLabel imageModeHeader = new GmDefaultModelElementLabel(diagram, ref);
        imageModeHeader.setRoleInComposition(GmBpmnAdHocSubProcess.IMAGE_LABEL_ROLE);
        imageModeHeader.setLayoutData(Integer.valueOf(PositionConstants.SOUTH));
        
        super.addChild(mainNode);
        super.addChild(imageModeHeader);
    }

    @objid ("607bd2b0-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        if (BpmnBoundaryEvent.class.isAssignableFrom(type)) {
            return true;
        }
        return false;
    }

    @objid ("607bd2b8-55b6-11e2-877f-002564c97630")
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

    @objid ("607bd2c0-55b6-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        switch (getRepresentationMode()) {
        case IMAGE:
            return GmBpmnAdHocSubProcess.IMAGE_KEYS.getStyleKey(metakey);
        case USER_IMAGE:
            return GmBpmnAdHocSubProcess.USERIMAGE_KEYS.getStyleKey(metakey);
        case SIMPLE:
            return GmBpmnAdHocSubProcess.SIMPLE_KEYS.getStyleKey(metakey);
        case STRUCTURED:
            return GmBpmnAdHocSubProcess.STRUCTURED_KEYS.getStyleKey(metakey);
        default:
            return null;
        }
    }

    @objid ("607d591c-55b6-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        switch (getRepresentationMode()) {
        case IMAGE:
            return GmBpmnAdHocSubProcess.IMAGE_KEYS.getStyleKeys();
        case USER_IMAGE:
            return GmBpmnAdHocSubProcess.USERIMAGE_KEYS.getStyleKeys();
        case SIMPLE:
            return GmBpmnAdHocSubProcess.SIMPLE_KEYS.getStyleKeys();
        case STRUCTURED:
            return GmBpmnAdHocSubProcess.STRUCTURED_KEYS.getStyleKeys();
        default:
            return Collections.emptyList();
        }
    }

    /**
     * Empty constructor needed for deserialisation.
     */
    @objid ("607d5924-55b6-11e2-877f-002564c97630")
    public GmBpmnAdHocSubProcess() {
        // Nothing specific to do.
    }

    @objid ("607d5927-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmBpmnAdHocSubProcess.");
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

    @objid ("607d592d-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRepresentedElement() {
        return this.element;
    }

    @objid ("607d5934-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return this.element;
    }

    @objid ("607d593b-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmBpmnAdHocSubProcess.", GmBpmnAdHocSubProcess.MINOR_VERSION);
    }

    @objid ("607d5941-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.element = (BpmnAdHocSubProcess) resolveRef(getRepresentedRef());
        
        GmDefaultModelElementLabel imageModeHeader = new GmDefaultModelElementLabel(getDiagram(), getRepresentedRef());
        imageModeHeader.setRoleInComposition(GmBpmnAdHocSubProcess.IMAGE_LABEL_ROLE);
        imageModeHeader.setLayoutData(Integer.valueOf(PositionConstants.SOUTH));
        
        super.addChild(imageModeHeader, 1);
    }

    @objid ("607d5946-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmBpmnAdHocSubProcess.MAJOR_VERSION;
    }

    @objid ("607d594b-55b6-11e2-877f-002564c97630")
    private void read_1(final IDiagramReader in) {
        super.read(in);
        this.element = (BpmnAdHocSubProcess) resolveRef(getRepresentedRef());
    }

    @objid ("607d5951-55b6-11e2-877f-002564c97630")
    @Override
    public List<GmNodeModel> getVisibleChildren() {
        List<GmNodeModel> ret = super.getVisibleChildren();
        
        // Returned result depends on current representation mode of the primary node
        GmNodeModel firstChild = getMainNode();
        
        if (firstChild == null) {
            // Remove the header used for image mode.
            ret.remove(getFirstChild(GmBpmnAdHocSubProcess.IMAGE_LABEL_ROLE));
        } else {
            switch (firstChild.getRepresentationMode()) {
            case IMAGE:
            case USER_IMAGE:
                break;
            default:
                // Remove the header used for image mode.
                ret.remove(getFirstChild(GmBpmnAdHocSubProcess.IMAGE_LABEL_ROLE));
            }
        }
        return ret;
    }

    /**
     * Is this node a Satellite, which position is defined relatively to the Main Node's bounds.
     * 
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Satellite.
     */
    @objid ("607d595a-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSatellite(final GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return GmPortContainer.SATELLITE_ROLE.equals(role)
                        || GmBpmnAdHocSubProcess.IMAGE_LABEL_ROLE.equals(role);
    }

    /**
     * Is this node a Port, which position is defined relatively to the Main Node's bounds.
     * 
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Port.
     */
    @objid ("607edfc9-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isPort(final GmNodeModel childNode) {
        return GmPortContainer.PORT_ROLE.equals(childNode.getRoleInComposition());
    }

    @objid ("a06f620a-306b-4e43-a5b9-115dff35e64f")
    @Override
    public boolean isMainSatelliteLabel(GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return role.equals(GmBpmnAdHocSubProcess.IMAGE_LABEL_ROLE);
    }

}
