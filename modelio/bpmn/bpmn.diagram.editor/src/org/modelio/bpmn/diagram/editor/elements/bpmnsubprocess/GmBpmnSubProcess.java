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
package org.modelio.bpmn.diagram.editor.elements.bpmnsubprocess;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.PositionConstants;
import org.modelio.bpmn.diagram.editor.elements.common.style.GmBpmnSubProcessImageStyleKeys;
import org.modelio.bpmn.diagram.editor.elements.common.style.GmBpmnSubProcessSimpleStyleKeys;
import org.modelio.bpmn.diagram.editor.elements.common.style.GmBpmnSubProcessStructuredStyleKeys;
import org.modelio.bpmn.diagram.editor.elements.common.style.GmBpmnSubProcessUserImageStyleKeys;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.common.portcontainer.GmPortContainer;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.events.BpmnBoundaryEvent;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Specialisation of the GmPortContainer class for SubProcess.
 */
@objid ("61c6f025-55b6-11e2-877f-002564c97630")
public class GmBpmnSubProcess extends GmPortContainer {
    /**
     * Current version of this Gm.
     */
    @objid ("61c6f032-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 1;

    @objid ("61c6f035-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("61c6f037-55b6-11e2-877f-002564c97630")
    private static final String IMAGE_LABEL_ROLE = "ImageLabel";

    @objid ("61c6f02f-55b6-11e2-877f-002564c97630")
    private BpmnSubProcess element;

    @objid ("c5158610-59a6-11e2-ae45-002564c97630")
    static final GmBpmnSubProcessStructuredStyleKeys STRUCTURED_KEYS = new GmBpmnSubProcessStructuredStyleKeys();

    @objid ("c5158612-59a6-11e2-ae45-002564c97630")
    static final GmBpmnSubProcessSimpleStyleKeys SIMPLE_KEYS = new GmBpmnSubProcessSimpleStyleKeys();

    @objid ("c5158614-59a6-11e2-ae45-002564c97630")
    static final GmBpmnSubProcessImageStyleKeys IMAGE_KEYS = new GmBpmnSubProcessImageStyleKeys();

    @objid ("b3b6595b-c23a-439b-af55-f11b0c3c27c8")
    static final GmBpmnSubProcessUserImageStyleKeys USERIMAGE_KEYS = new GmBpmnSubProcessUserImageStyleKeys();

    /**
     * Constructor.
     * @param diagram the diagram in which the BpmnReceiveTask is unmasked.
     * @param element the unmasked BpmnReceiveTask.
     * @param ref a reference to the unmasked callOperation.
     */
    @objid ("61c6f039-55b6-11e2-877f-002564c97630")
    public  GmBpmnSubProcess(IGmDiagram diagram, BpmnSubProcess element, MRef ref) {
        super(diagram, ref);
        this.element = element;
        
        GmBpmnSubProcessPrimaryNode mainNode = new GmBpmnSubProcessPrimaryNode(diagram, ref);
        mainNode.setRoleInComposition(GmPortContainer.MAIN_NODE_ROLE);
        
        GmDefaultModelElementLabel imageModeHeader = new GmDefaultModelElementLabel(diagram, ref);
        imageModeHeader.setRoleInComposition(GmBpmnSubProcess.IMAGE_LABEL_ROLE);
        imageModeHeader.setLayoutData(Integer.valueOf(PositionConstants.SOUTH));
        
        super.addChild(mainNode);
        super.addChild(imageModeHeader);
        
    }

    @objid ("61c6f045-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        if (BpmnBoundaryEvent.class.isAssignableFrom(type)) {
            return true;
        }
        return false;
    }

    @objid ("61c6f04d-55b6-11e2-877f-002564c97630")
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

    @objid ("61c6f055-55b6-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        switch (getRepresentationMode()) {
        case IMAGE:
            return GmBpmnSubProcess.IMAGE_KEYS.getStyleKey(metakey);
        case USER_IMAGE:
            return GmBpmnSubProcess.USERIMAGE_KEYS.getStyleKey(metakey);
        case SIMPLE:
            return GmBpmnSubProcess.SIMPLE_KEYS.getStyleKey(metakey);
        case STRUCTURED:
            return GmBpmnSubProcess.STRUCTURED_KEYS.getStyleKey(metakey);
        default:
            return null;
        }
        
    }

    @objid ("61c6f05e-55b6-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        switch (getRepresentationMode()) {
        case IMAGE:
            return GmBpmnSubProcess.IMAGE_KEYS.getStyleKeys();
        case USER_IMAGE:
            return GmBpmnSubProcess.USERIMAGE_KEYS.getStyleKeys();
        case SIMPLE:
            return GmBpmnSubProcess.SIMPLE_KEYS.getStyleKeys();
        case STRUCTURED:
            return GmBpmnSubProcess.STRUCTURED_KEYS.getStyleKeys();
        default:
            return Collections.emptyList();
        }
        
    }

    /**
     * Empty constructor needed for deserialisation.
     */
    @objid ("61c876ba-55b6-11e2-877f-002564c97630")
    public  GmBpmnSubProcess() {
        // Nothing specific to do.
    }

    @objid ("61c876bd-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmBpmnSubProcess.");
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

    @objid ("61c876c3-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRepresentedElement() {
        return this.element;
    }

    @objid ("61c876ca-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return this.element;
    }

    @objid ("61c876d1-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmBpmnSubProcess.", GmBpmnSubProcess.MINOR_VERSION);
        
    }

    @objid ("61c876d7-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.element = (BpmnSubProcess) resolveRef(getRepresentedRef());
        
        GmDefaultModelElementLabel imageModeHeader = new GmDefaultModelElementLabel(getDiagram(), getRepresentedRef());
        imageModeHeader.setRoleInComposition(GmBpmnSubProcess.IMAGE_LABEL_ROLE);
        imageModeHeader.setLayoutData(Integer.valueOf(PositionConstants.SOUTH));
        
        super.addChild(imageModeHeader, 1);
        
    }

    @objid ("61c876dc-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmBpmnSubProcess.MAJOR_VERSION;
    }

    @objid ("61c876e1-55b6-11e2-877f-002564c97630")
    private void read_1(final IDiagramReader in) {
        super.read(in);
        this.element = (BpmnSubProcess) resolveRef(getRepresentedRef());
        
    }

    @objid ("61c876e7-55b6-11e2-877f-002564c97630")
    @Override
    public List<GmNodeModel> getVisibleChildren() {
        List<GmNodeModel> ret = super.getVisibleChildren();
        
        // Returned result depends on current representation mode of the primary node
        GmNodeModel firstChild = getMainNode();
        
        if (firstChild == null) {
            // Remove the header used for image mode.
            ret.remove(getFirstChild(GmBpmnSubProcess.IMAGE_LABEL_ROLE));
        } else {
            switch (firstChild.getRepresentationMode()) {
            case IMAGE:
            case USER_IMAGE:
                break;
            default:
                // Remove the header used for image mode.
                ret.remove(getFirstChild(GmBpmnSubProcess.IMAGE_LABEL_ROLE));
            }
        }
        return ret;
    }

    /**
     * Is this node a Satellite, which position is defined relatively to the Main Node's bounds.
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Satellite.
     */
    @objid ("61c876f0-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSatellite(final GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return GmPortContainer.SATELLITE_ROLE.equals(role)
                        || GmBpmnSubProcess.IMAGE_LABEL_ROLE.equals(role);
        
    }

    /**
     * Is this node a Port, which position is defined relatively to the Main Node's bounds.
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Port.
     */
    @objid ("61c9fd61-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isPort(final GmNodeModel childNode) {
        return GmPortContainer.PORT_ROLE.equals(childNode.getRoleInComposition());
    }

    @objid ("6c8d85b6-ec95-462e-9e6b-8d5baeb315dd")
    @Override
    public boolean isMainSatelliteLabel(GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return role.equals(GmBpmnSubProcess.IMAGE_LABEL_ROLE);
    }

}
