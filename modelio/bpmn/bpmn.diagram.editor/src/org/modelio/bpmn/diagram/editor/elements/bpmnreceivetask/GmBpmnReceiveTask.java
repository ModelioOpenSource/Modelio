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
package org.modelio.bpmn.diagram.editor.elements.bpmnreceivetask;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.PositionConstants;
import org.modelio.bpmn.diagram.editor.elements.common.style.GmBpmnTaskImageStyleKeys;
import org.modelio.bpmn.diagram.editor.elements.common.style.GmBpmnTaskSimpleStyleKeys;
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
import org.modelio.metamodel.bpmn.activities.BpmnReceiveTask;
import org.modelio.metamodel.bpmn.events.BpmnBoundaryEvent;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Specialisation of the GmPortContainer class for BpmnReceiveTask.
 */
@objid ("618cf462-55b6-11e2-877f-002564c97630")
public class GmBpmnReceiveTask extends GmPortContainer {
    /**
     * Current version of this Gm.
     */
    @objid ("618cf46f-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 1;

    @objid ("618cf472-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("618cf474-55b6-11e2-877f-002564c97630")
    private static final String IMAGE_LABEL_ROLE = "ImageLabel";

    @objid ("618cf46c-55b6-11e2-877f-002564c97630")
    private BpmnReceiveTask element;

    @objid ("c5b9c4ee-59a6-11e2-ae45-002564c97630")
    static final GmBpmnReceiveTaskStructuredStyleKeys STRUCTURED_KEYS = new GmBpmnReceiveTaskStructuredStyleKeys();

    @objid ("c5b9c4f0-59a6-11e2-ae45-002564c97630")
    static final GmBpmnTaskSimpleStyleKeys SIMPLE_KEYS = new GmBpmnTaskSimpleStyleKeys();

    @objid ("c5b9c4f2-59a6-11e2-ae45-002564c97630")
    static final GmBpmnTaskImageStyleKeys IMAGE_KEYS = new GmBpmnTaskImageStyleKeys();

    @objid ("5988ff29-3775-48c4-8605-26dafe0de030")
    static final GmBpmnTaskUserImageStyleKeys USERIMAGE_KEYS = new GmBpmnTaskUserImageStyleKeys();

    /**
     * Constructor.
     * @param diagram the diagram in which the BpmnReceiveTask is unmasked.
     * @param element the unmasked BpmnReceiveTask.
     * @param ref a reference to the unmasked callOperation.
     */
    @objid ("618cf476-55b6-11e2-877f-002564c97630")
    public  GmBpmnReceiveTask(IGmDiagram diagram, BpmnReceiveTask element, MRef ref) {
        super(diagram, ref);
        this.element = element;
        
        GmBpmnReceiveTaskPrimaryNode mainNode = new GmBpmnReceiveTaskPrimaryNode(diagram, ref);
        mainNode.setRoleInComposition(GmPortContainer.MAIN_NODE_ROLE);
        
        GmDefaultModelElementLabel imageModeHeader = new GmDefaultModelElementLabel(diagram, ref);
        imageModeHeader.setRoleInComposition(GmBpmnReceiveTask.IMAGE_LABEL_ROLE);
        imageModeHeader.setLayoutData(Integer.valueOf(PositionConstants.SOUTH));
        
        super.addChild(mainNode);
        super.addChild(imageModeHeader);
        
    }

    @objid ("618cf482-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        if (BpmnBoundaryEvent.class.isAssignableFrom(type)) {
            return true;
        }
        return false;
    }

    @objid ("618cf48a-55b6-11e2-877f-002564c97630")
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

    @objid ("618cf492-55b6-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        switch (getRepresentationMode()) {
        case IMAGE:
            return GmBpmnReceiveTask.IMAGE_KEYS.getStyleKey(metakey);
        case USER_IMAGE:
            return GmBpmnReceiveTask.USERIMAGE_KEYS.getStyleKey(metakey);
        case SIMPLE:
            return GmBpmnReceiveTask.SIMPLE_KEYS.getStyleKey(metakey);
        case STRUCTURED:
            return GmBpmnReceiveTask.STRUCTURED_KEYS.getStyleKey(metakey);
        default:
            return null;
        }
        
    }

    @objid ("618cf49b-55b6-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        switch (getRepresentationMode()) {
        case IMAGE:
            return GmBpmnReceiveTask.IMAGE_KEYS.getStyleKeys();
        case USER_IMAGE:
            return GmBpmnReceiveTask.USERIMAGE_KEYS.getStyleKeys();
        case SIMPLE:
            return GmBpmnReceiveTask.SIMPLE_KEYS.getStyleKeys();
        case STRUCTURED:
            return GmBpmnReceiveTask.STRUCTURED_KEYS.getStyleKeys();
        default:
            return Collections.emptyList();
        }
        
    }

    /**
     * Empty constructor needed for deserialisation.
     */
    @objid ("618e7af9-55b6-11e2-877f-002564c97630")
    public  GmBpmnReceiveTask() {
        // Nothing specific to do.
    }

    @objid ("618e7afc-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = GmAbstractObject.readMinorVersion(in, "GmBpmnReceiveTask.");
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

    @objid ("618e7b02-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRepresentedElement() {
        return this.element;
    }

    @objid ("618e7b09-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return this.element;
    }

    @objid ("618e7b10-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        GmAbstractObject.writeMinorVersion(out, "GmBpmnReceiveTask.", GmBpmnReceiveTask.MINOR_VERSION);
        
    }

    @objid ("618e7b16-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.element = (BpmnReceiveTask) resolveRef(getRepresentedRef());
        
        GmDefaultModelElementLabel imageModeHeader = new GmDefaultModelElementLabel(getDiagram(), getRepresentedRef());
        imageModeHeader.setRoleInComposition(GmBpmnReceiveTask.IMAGE_LABEL_ROLE);
        imageModeHeader.setLayoutData(Integer.valueOf(PositionConstants.SOUTH));
        
        super.addChild(imageModeHeader, 1);
        
    }

    @objid ("618e7b1b-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmBpmnReceiveTask.MAJOR_VERSION;
    }

    @objid ("618e7b20-55b6-11e2-877f-002564c97630")
    private void read_1(final IDiagramReader in) {
        super.read(in);
        this.element = (BpmnReceiveTask) resolveRef(getRepresentedRef());
        
    }

    @objid ("618e7b26-55b6-11e2-877f-002564c97630")
    @Override
    public List<GmNodeModel> getVisibleChildren() {
        // Returned result depends on current representation mode:
        List<GmNodeModel> ret = super.getVisibleChildren();
        if (getMainNode() != null) {
            switch (getMainNode().getRepresentationMode()) {
            case STRUCTURED:
            case SIMPLE: {
                GmNodeModel imageModeHeader = getFirstChild(GmBpmnReceiveTask.IMAGE_LABEL_ROLE);
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
    @objid ("618e7b2f-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSatellite(final GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return GmPortContainer.SATELLITE_ROLE.equals(role)
                        || GmBpmnReceiveTask.IMAGE_LABEL_ROLE.equals(role);
        
    }

    /**
     * Is this node a Port, which position is defined relatively to the Main Node's bounds.
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Port.
     */
    @objid ("6190019f-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isPort(final GmNodeModel childNode) {
        return GmPortContainer.PORT_ROLE.equals(childNode.getRoleInComposition());
    }

    @objid ("73663108-4c12-4f45-aaaa-ba502aa53b40")
    @Override
    public boolean isMainSatelliteLabel(GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return role.equals(GmBpmnReceiveTask.IMAGE_LABEL_ROLE);
    }

}
