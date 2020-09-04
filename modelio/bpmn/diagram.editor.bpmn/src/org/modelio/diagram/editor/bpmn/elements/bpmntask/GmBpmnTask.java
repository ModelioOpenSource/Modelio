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

package org.modelio.diagram.editor.bpmn.elements.bpmntask;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.PositionConstants;
import org.modelio.diagram.editor.bpmn.elements.style.GmBpmnTaskImageStyleKeys;
import org.modelio.diagram.editor.bpmn.elements.style.GmBpmnTaskSimpleStyleKeys;
import org.modelio.diagram.editor.bpmn.elements.style.GmBpmnTaskStructuredStyleKeys;
import org.modelio.diagram.editor.bpmn.elements.style.GmBpmnTaskUserImageStyleKeys;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.common.portcontainer.GmPortContainer;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.bpmn.activities.BpmnTask;
import org.modelio.metamodel.bpmn.events.BpmnBoundaryEvent;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Specialisation of the GmPortContainer class for BpmnTask.
 */
@objid ("61d017f5-55b6-11e2-877f-002564c97630")
public class GmBpmnTask extends GmPortContainer {
    @objid ("61d017ff-55b6-11e2-877f-002564c97630")
    private BpmnTask element;

    /**
     * Current version of this Gm.
     */
    @objid ("61d01802-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 1;

    @objid ("61d01805-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("61d01807-55b6-11e2-877f-002564c97630")
    private static final String IMAGE_LABEL_ROLE = "ImageLabel";

    @objid ("c5e23c4e-59a6-11e2-ae45-002564c97630")
     static final GmBpmnTaskStructuredStyleKeys STRUCTURED_KEYS = new GmBpmnTaskStructuredStyleKeys();

    @objid ("c5e49dae-59a6-11e2-ae45-002564c97630")
     static final GmBpmnTaskSimpleStyleKeys SIMPLE_KEYS = new GmBpmnTaskSimpleStyleKeys();

    @objid ("c5e49db0-59a6-11e2-ae45-002564c97630")
     static final GmBpmnTaskImageStyleKeys IMAGE_KEYS = new GmBpmnTaskImageStyleKeys();

    @objid ("ecb77ef5-1a32-4c7e-ac32-ad621cab8c9e")
     static final GmBpmnTaskUserImageStyleKeys USERIMAGE_KEYS = new GmBpmnTaskUserImageStyleKeys();

    /**
     * Constructor.
     * 
     * @param diagram the diagram in which the BpmnTask is unmasked.
     * @param element the unmasked BpmnTask.
     * @param ref a reference to the unmasked callOperation.
     */
    @objid ("61d01809-55b6-11e2-877f-002564c97630")
    public GmBpmnTask(IGmDiagram diagram, BpmnTask element, MRef ref) {
        super(diagram, ref);
        this.element = element;
        
        GmBpmnTaskPrimaryNode mainNode = new GmBpmnTaskPrimaryNode(diagram, ref);
        mainNode.setRoleInComposition(MAIN_NODE_ROLE);
        
        GmDefaultModelElementLabel imageModeHeader = new GmDefaultModelElementLabel(diagram, ref);
        imageModeHeader.setRoleInComposition(IMAGE_LABEL_ROLE);
        imageModeHeader.setLayoutData(Integer.valueOf(PositionConstants.SOUTH));
        
        super.addChild(mainNode);
        super.addChild(imageModeHeader);
    }

    @objid ("61d01815-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        if (BpmnBoundaryEvent.class.isAssignableFrom(type)) {
            return true;
        }
        return false;
    }

    @objid ("61d0181d-55b6-11e2-877f-002564c97630")
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

    @objid ("61d19e80-55b6-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        switch (getRepresentationMode()) {
        case IMAGE:
            return IMAGE_KEYS.getStyleKey(metakey);
        case USER_IMAGE:
            return USERIMAGE_KEYS.getStyleKey(metakey);
        case SIMPLE:
            return SIMPLE_KEYS.getStyleKey(metakey);
        case STRUCTURED:
            return STRUCTURED_KEYS.getStyleKey(metakey);
        default:
            return null;
        }
    }

    @objid ("61d19e89-55b6-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        switch (getRepresentationMode()) {
        case IMAGE:
            return IMAGE_KEYS.getStyleKeys();
        case USER_IMAGE:
            return USERIMAGE_KEYS.getStyleKeys();
        case SIMPLE:
            return SIMPLE_KEYS.getStyleKeys();
        case STRUCTURED:
            return STRUCTURED_KEYS.getStyleKeys();
        default:
            return Collections.emptyList();
        }
    }

    /**
     * Empty constructor needed for deserialisation.
     */
    @objid ("61d19e91-55b6-11e2-877f-002564c97630")
    public GmBpmnTask() {
        // Nothing specific to do.
    }

    @objid ("61d19e94-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmBpmnTask.");
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

    @objid ("61d19e9a-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRepresentedElement() {
        return this.element;
    }

    @objid ("61d19ea1-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return this.element;
    }

    @objid ("61d19ea8-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmBpmnTask.", MINOR_VERSION);
    }

    @objid ("61d19eae-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.element = (BpmnTask) resolveRef(getRepresentedRef());
        
        GmDefaultModelElementLabel imageModeHeader = new GmDefaultModelElementLabel(getDiagram(), getRepresentedRef());
        imageModeHeader.setRoleInComposition(IMAGE_LABEL_ROLE);
        imageModeHeader.setLayoutData(Integer.valueOf(PositionConstants.SOUTH));
        
        super.addChild(imageModeHeader, 1);
    }

    @objid ("61d19eb3-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @objid ("61d19eb8-55b6-11e2-877f-002564c97630")
    private void read_1(final IDiagramReader in) {
        super.read(in);
        this.element = (BpmnTask) resolveRef(getRepresentedRef());
    }

    @objid ("61d3251c-55b6-11e2-877f-002564c97630")
    @Override
    public List<GmNodeModel> getVisibleChildren() {
        List<GmNodeModel> ret = super.getVisibleChildren();
        
        // Returned result depends on current representation mode of the primary node
        GmNodeModel firstChild = getMainNode();
        
        if (firstChild == null) {
            // Remove the header used for image mode.
            ret.remove(getFirstChild(IMAGE_LABEL_ROLE));
        } else {
            switch (firstChild.getRepresentationMode()) {
            case IMAGE:
            case USER_IMAGE:
                // keep image mode header
                break;
            default:
                // Remove the header used for image mode.
                ret.remove(getFirstChild(IMAGE_LABEL_ROLE));
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
    @objid ("61d32525-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSatellite(final GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return GmPortContainer.SATELLITE_ROLE.equals(role)
                                || IMAGE_LABEL_ROLE.equals(role);
    }

    /**
     * Is this node a Port, which position is defined relatively to the Main Node's bounds.
     * 
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Port.
     */
    @objid ("61d32537-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isPort(final GmNodeModel childNode) {
        return GmPortContainer.PORT_ROLE.equals(childNode.getRoleInComposition());
    }

    @objid ("086f43f9-a44c-4295-8858-7bc434338d02")
    @Override
    public boolean isMainSatelliteLabel(GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return role.equals(IMAGE_LABEL_ROLE);
    }

}
