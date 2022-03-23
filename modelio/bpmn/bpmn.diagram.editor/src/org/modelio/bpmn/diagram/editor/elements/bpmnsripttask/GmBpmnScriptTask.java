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
package org.modelio.bpmn.diagram.editor.elements.bpmnsripttask;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.PositionConstants;
import org.modelio.bpmn.diagram.editor.elements.common.style.GmBpmnTaskImageStyleKeys;
import org.modelio.bpmn.diagram.editor.elements.common.style.GmBpmnTaskSimpleStyleKeys;
import org.modelio.bpmn.diagram.editor.elements.common.style.GmBpmnTaskStructuredStyleKeys;
import org.modelio.bpmn.diagram.editor.elements.common.style.GmBpmnTaskUserImageStyleKeys;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.common.portcontainer.GmPortContainer;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.bpmn.activities.BpmnScriptTask;
import org.modelio.metamodel.bpmn.events.BpmnBoundaryEvent;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Specialisation of the GmPortContainer class for BpmnScriptTask.
 */
@objid ("61ae8643-55b6-11e2-877f-002564c97630")
public class GmBpmnScriptTask extends GmPortContainer {
    /**
     * Current version of this Gm.
     */
    @objid ("61ae8650-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 1;

    @objid ("61ae8653-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("61ae8655-55b6-11e2-877f-002564c97630")
    private static final String IMAGE_LABEL_ROLE = "ImageLabel";

    @objid ("61ae864d-55b6-11e2-877f-002564c97630")
    private BpmnScriptTask element;

    @objid ("c5d3f40e-59a6-11e2-ae45-002564c97630")
    static final GmBpmnTaskStructuredStyleKeys STRUCTURED_KEYS = new GmBpmnTaskStructuredStyleKeys();

    @objid ("c5d3f410-59a6-11e2-ae45-002564c97630")
    static final GmBpmnTaskSimpleStyleKeys SIMPLE_KEYS = new GmBpmnTaskSimpleStyleKeys();

    @objid ("c5d3f412-59a6-11e2-ae45-002564c97630")
    static final GmBpmnTaskImageStyleKeys IMAGE_KEYS = new GmBpmnTaskImageStyleKeys();

    @objid ("2cd598b3-924b-43a8-b3a4-c2b0954ac95a")
    static final GmBpmnTaskUserImageStyleKeys USERIMAGE_KEYS = new GmBpmnTaskUserImageStyleKeys();

    /**
     * Constructor.
     * @param diagram the diagram in which the BpmnScriptTask is unmasked.
     * @param element the unmasked BpmnScriptTask.
     * @param ref a reference to the unmasked callOperation.
     */
    @objid ("61ae8657-55b6-11e2-877f-002564c97630")
    public  GmBpmnScriptTask(IGmDiagram diagram, BpmnScriptTask element, MRef ref) {
        super(diagram, ref);
        this.element = element;
        
        GmBpmnScriptTaskPrimaryNode mainNode = new GmBpmnScriptTaskPrimaryNode(diagram, ref);
        mainNode.setRoleInComposition(GmPortContainer.MAIN_NODE_ROLE);
        
        GmDefaultModelElementLabel imageModeHeader = new GmDefaultModelElementLabel(diagram, ref);
        imageModeHeader.setRoleInComposition(GmBpmnScriptTask.IMAGE_LABEL_ROLE);
        imageModeHeader.setLayoutData(Integer.valueOf(PositionConstants.SOUTH));
        
        super.addChild(mainNode);
        super.addChild(imageModeHeader);
        
    }

    @objid ("61b00cbf-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        if (BpmnBoundaryEvent.class.isAssignableFrom(type)) {
            return true;
        }
        return false;
    }

    @objid ("61b00cc7-55b6-11e2-877f-002564c97630")
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

    @objid ("61b00ccf-55b6-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        switch (getRepresentationMode()) {
        case IMAGE:
            return GmBpmnScriptTask.IMAGE_KEYS.getStyleKey(metakey);
        case USER_IMAGE:
            return GmBpmnScriptTask.USERIMAGE_KEYS.getStyleKey(metakey);
        case SIMPLE:
            return GmBpmnScriptTask.SIMPLE_KEYS.getStyleKey(metakey);
        case STRUCTURED:
            return GmBpmnScriptTask.STRUCTURED_KEYS.getStyleKey(metakey);
        default:
            return null;
        }
        
    }

    @objid ("61b00cd8-55b6-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        switch (getRepresentationMode()) {
        case IMAGE:
            return GmBpmnScriptTask.IMAGE_KEYS.getStyleKeys();
        case USER_IMAGE:
            return GmBpmnScriptTask.USERIMAGE_KEYS.getStyleKeys();
        case SIMPLE:
            return GmBpmnScriptTask.SIMPLE_KEYS.getStyleKeys();
        case STRUCTURED:
            return GmBpmnScriptTask.STRUCTURED_KEYS.getStyleKeys();
        default:
            return Collections.emptyList();
        }
        
    }

    /**
     * Empty constructor needed for deserialisation.
     */
    @objid ("61b00ce0-55b6-11e2-877f-002564c97630")
    public  GmBpmnScriptTask() {
        // Nothing specific to do.
    }

    @objid ("61b00ce3-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = GmAbstractObject.readMinorVersion(in, "GmBpmnScriptTask.");
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

    @objid ("61b00ce9-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRepresentedElement() {
        return this.element;
    }

    @objid ("61b00cf0-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return this.element;
    }

    @objid ("61b00cf7-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        GmAbstractObject.writeMinorVersion(out, "GmBpmnScriptTask.", GmBpmnScriptTask.MINOR_VERSION);
        
    }

    @objid ("61b1935a-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.element = (BpmnScriptTask) resolveRef(getRepresentedRef());
        
        GmDefaultModelElementLabel imageModeHeader = new GmDefaultModelElementLabel(getDiagram(), getRepresentedRef());
        imageModeHeader.setRoleInComposition(GmBpmnScriptTask.IMAGE_LABEL_ROLE);
        imageModeHeader.setLayoutData(Integer.valueOf(PositionConstants.SOUTH));
        
        super.addChild(imageModeHeader, 1);
        
    }

    @objid ("61b1935f-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmBpmnScriptTask.MAJOR_VERSION;
    }

    @objid ("61b19364-55b6-11e2-877f-002564c97630")
    private void read_1(final IDiagramReader in) {
        super.read(in);
        this.element = (BpmnScriptTask) resolveRef(getRepresentedRef());
        
    }

    @objid ("61b1936a-55b6-11e2-877f-002564c97630")
    @Override
    public List<GmNodeModel> getVisibleChildren() {
        // Returned result depends on current representation mode:
        List<GmNodeModel> ret = super.getVisibleChildren();
        if (getMainNode() != null) {
            switch (getMainNode().getRepresentationMode()) {
            case STRUCTURED:
            case SIMPLE: {
                GmNodeModel imageModeHeader = getFirstChild(GmBpmnScriptTask.IMAGE_LABEL_ROLE);
                ret.remove(imageModeHeader);
                break;
            }
            case USER_IMAGE:
            case IMAGE:
            default: {
                break;
            }
        
            }
        }
        return ret;
    }

    /**
     * Is this node a Satellite, which position is defined relatively to the Main Node's bounds.
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Satellite.
     */
    @objid ("61b19373-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSatellite(final GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return GmPortContainer.SATELLITE_ROLE.equals(role)
                        || GmBpmnScriptTask.IMAGE_LABEL_ROLE.equals(role);
        
    }

    /**
     * Is this node a Port, which position is defined relatively to the Main Node's bounds.
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Port.
     */
    @objid ("61b19385-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isPort(final GmNodeModel childNode) {
        return GmPortContainer.PORT_ROLE.equals(childNode.getRoleInComposition());
    }

    @objid ("7ce8e60b-027b-472f-baae-9e6d39fd79ab")
    @Override
    public boolean isMainSatelliteLabel(GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return role.equals(GmBpmnScriptTask.IMAGE_LABEL_ROLE);
    }

}
