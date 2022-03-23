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
package org.modelio.bpmn.diagram.editor.elements.bpmnmanualtask;

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
import org.modelio.metamodel.bpmn.activities.BpmnManualTask;
import org.modelio.metamodel.bpmn.events.BpmnBoundaryEvent;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Specialisation of the GmPortContainer class for BpmnManualTask.
 */
@objid ("61517223-55b6-11e2-877f-002564c97630")
public class GmBpmnManualTask extends GmPortContainer {
    /**
     * Current version of this Gm.
     */
    @objid ("61517230-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 1;

    @objid ("61517233-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("6152f89a-55b6-11e2-877f-002564c97630")
    private static final String IMAGE_LABEL_ROLE = "ImageLabel";

    @objid ("6151722d-55b6-11e2-877f-002564c97630")
    private BpmnManualTask element;

    @objid ("c59871ae-59a6-11e2-ae45-002564c97630")
    static final GmBpmnTaskStructuredStyleKeys STRUCTURED_KEYS = new GmBpmnTaskStructuredStyleKeys();

    @objid ("c59871b0-59a6-11e2-ae45-002564c97630")
    static final GmBpmnTaskSimpleStyleKeys SIMPLE_KEYS = new GmBpmnTaskSimpleStyleKeys();

    @objid ("c59871b2-59a6-11e2-ae45-002564c97630")
    static final GmBpmnTaskImageStyleKeys IMAGE_KEYS = new GmBpmnTaskImageStyleKeys();

    @objid ("820c9298-643d-4347-af6c-3e8dcb6539e0")
    static final GmBpmnTaskUserImageStyleKeys USERIMAGE_KEYS = new GmBpmnTaskUserImageStyleKeys();

    /**
     * Constructor.
     * @param diagram the diagram in which the BpmnManualTask is unmasked.
     * @param element the unmasked BpmnManualTask.
     * @param ref a reference to the unmasked callOperation.
     */
    @objid ("6152f89c-55b6-11e2-877f-002564c97630")
    public  GmBpmnManualTask(IGmDiagram diagram, BpmnManualTask element, MRef ref) {
        super(diagram, ref);
        this.element = element;
        
        GmBpmnManualTaskPrimaryNode mainNode = new GmBpmnManualTaskPrimaryNode(diagram, ref);
        mainNode.setRoleInComposition(GmPortContainer.MAIN_NODE_ROLE);
        
        GmDefaultModelElementLabel imageModeHeader = new GmDefaultModelElementLabel(diagram, ref);
        imageModeHeader.setRoleInComposition(GmBpmnManualTask.IMAGE_LABEL_ROLE);
        imageModeHeader.setLayoutData(Integer.valueOf(PositionConstants.SOUTH));
        
        super.addChild(mainNode);
        super.addChild(imageModeHeader);
        
    }

    @objid ("6152f8a8-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        if (BpmnBoundaryEvent.class.isAssignableFrom(type)) {
            return true;
        }
        return false;
    }

    @objid ("6152f8b0-55b6-11e2-877f-002564c97630")
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

    @objid ("6152f8b8-55b6-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        switch (getRepresentationMode()) {
        case IMAGE:
            return GmBpmnManualTask.IMAGE_KEYS.getStyleKey(metakey);
        case USER_IMAGE:
            return GmBpmnManualTask.USERIMAGE_KEYS.getStyleKey(metakey);
        case SIMPLE:
            return GmBpmnManualTask.SIMPLE_KEYS.getStyleKey(metakey);
        case STRUCTURED:
            return GmBpmnManualTask.STRUCTURED_KEYS.getStyleKey(metakey);
        default:
            return null;
        }
        
    }

    @objid ("6152f8c1-55b6-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        switch (getRepresentationMode()) {
        case IMAGE:
            return GmBpmnManualTask.IMAGE_KEYS.getStyleKeys();
        case USER_IMAGE:
            return GmBpmnManualTask.USERIMAGE_KEYS.getStyleKeys();
        case SIMPLE:
            return GmBpmnManualTask.SIMPLE_KEYS.getStyleKeys();
        case STRUCTURED:
            return GmBpmnManualTask.STRUCTURED_KEYS.getStyleKeys();
        default:
            return Collections.emptyList();
        }
        
    }

    /**
     * Empty constructor needed for deserialisation.
     */
    @objid ("6152f8c9-55b6-11e2-877f-002564c97630")
    public  GmBpmnManualTask() {
        // Nothing specific to do.
    }

    @objid ("6152f8cc-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = GmAbstractObject.readMinorVersion(in, "GmBpmnManualTask.");
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

    @objid ("6152f8d2-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRepresentedElement() {
        return this.element;
    }

    @objid ("6152f8d9-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return this.element;
    }

    @objid ("61547f39-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        GmAbstractObject.writeMinorVersion(out, "GmBpmnManualTask.", GmBpmnManualTask.MINOR_VERSION);
        
    }

    @objid ("61547f3f-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.element = (BpmnManualTask) resolveRef(getRepresentedRef());
        
        GmDefaultModelElementLabel imageModeHeader = new GmDefaultModelElementLabel(getDiagram(), getRepresentedRef());
        imageModeHeader.setRoleInComposition(GmBpmnManualTask.IMAGE_LABEL_ROLE);
        imageModeHeader.setLayoutData(Integer.valueOf(PositionConstants.SOUTH));
        
        super.addChild(imageModeHeader, 1);
        
    }

    @objid ("61547f44-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmBpmnManualTask.MAJOR_VERSION;
    }

    @objid ("61547f49-55b6-11e2-877f-002564c97630")
    private void read_1(final IDiagramReader in) {
        super.read(in);
        this.element = (BpmnManualTask) resolveRef(getRepresentedRef());
        
    }

    @objid ("61547f4f-55b6-11e2-877f-002564c97630")
    @Override
    public List<GmNodeModel> getVisibleChildren() {
        // Returned result depends on current representation mode:
        List<GmNodeModel> ret = super.getVisibleChildren();
        if (getMainNode() != null) {
            switch (getMainNode().getRepresentationMode()) {
            case STRUCTURED:
            case SIMPLE: {
                GmNodeModel imageModeHeader = getFirstChild(GmBpmnManualTask.IMAGE_LABEL_ROLE);
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
    @objid ("61547f58-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSatellite(final GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return GmPortContainer.SATELLITE_ROLE.equals(role)
                        || GmBpmnManualTask.IMAGE_LABEL_ROLE.equals(role);
        
    }

    /**
     * Is this node a Port, which position is defined relatively to the Main Node's bounds.
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Port.
     */
    @objid ("61547f6a-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isPort(final GmNodeModel childNode) {
        return GmPortContainer.PORT_ROLE.equals(childNode.getRoleInComposition());
    }

    @objid ("cc4db019-cca5-4159-8ebd-cdac86026009")
    @Override
    public boolean isMainSatelliteLabel(GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return role.equals(GmBpmnManualTask.IMAGE_LABEL_ROLE);
    }

}
