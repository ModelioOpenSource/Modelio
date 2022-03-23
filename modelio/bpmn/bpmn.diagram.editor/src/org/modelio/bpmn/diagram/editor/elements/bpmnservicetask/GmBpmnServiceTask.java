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
package org.modelio.bpmn.diagram.editor.elements.bpmnservicetask;

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
import org.modelio.metamodel.bpmn.activities.BpmnServiceTask;
import org.modelio.metamodel.bpmn.events.BpmnBoundaryEvent;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Specialisation of the GmPortContainer class for BpmnServiceTask.
 */
@objid ("61a55e83-55b6-11e2-877f-002564c97630")
public class GmBpmnServiceTask extends GmPortContainer {
    /**
     * Current version of this Gm.
     */
    @objid ("61a55e90-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 1;

    @objid ("61a6e4fb-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("61a6e4fd-55b6-11e2-877f-002564c97630")
    private static final String IMAGE_LABEL_ROLE = "ImageLabel";

    @objid ("61a55e8d-55b6-11e2-877f-002564c97630")
    private BpmnServiceTask element;

    @objid ("c50019ad-59a6-11e2-ae45-002564c97630")
    static final GmBpmnServiceTaskStructuredStyleKeys STRUCTURED_KEYS = new GmBpmnServiceTaskStructuredStyleKeys();

    @objid ("c50019af-59a6-11e2-ae45-002564c97630")
    static final GmBpmnTaskSimpleStyleKeys SIMPLE_KEYS = new GmBpmnTaskSimpleStyleKeys();

    @objid ("c50019b1-59a6-11e2-ae45-002564c97630")
    static final GmBpmnTaskImageStyleKeys IMAGE_KEYS = new GmBpmnTaskImageStyleKeys();

    @objid ("96116005-dfc3-4a27-99e5-36427b1d344f")
    static final GmBpmnTaskUserImageStyleKeys USERIMAGE_KEYS = new GmBpmnTaskUserImageStyleKeys();

    /**
     * Constructor.
     * @param diagram the diagram in which the BpmnServiceTask is unmasked.
     * @param element the unmasked BpmnServiceTask.
     * @param ref a reference to the unmasked callOperation.
     */
    @objid ("61a6e4ff-55b6-11e2-877f-002564c97630")
    public  GmBpmnServiceTask(IGmDiagram diagram, BpmnServiceTask element, MRef ref) {
        super(diagram, ref);
        this.element = element;
        
        GmBpmnServiceTaskPrimaryNode mainNode = new GmBpmnServiceTaskPrimaryNode(diagram, ref);
        mainNode.setRoleInComposition(GmPortContainer.MAIN_NODE_ROLE);
        
        GmDefaultModelElementLabel imageModeHeader = new GmDefaultModelElementLabel(diagram, ref);
        imageModeHeader.setRoleInComposition(GmBpmnServiceTask.IMAGE_LABEL_ROLE);
        imageModeHeader.setLayoutData(Integer.valueOf(PositionConstants.SOUTH));
        
        super.addChild(mainNode);
        super.addChild(imageModeHeader);
        
    }

    @objid ("61a6e50b-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        if (BpmnBoundaryEvent.class.isAssignableFrom(type)) {
            return true;
        }
        return false;
    }

    @objid ("61a6e513-55b6-11e2-877f-002564c97630")
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

    @objid ("61a6e51b-55b6-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        switch (getRepresentationMode()) {
        case IMAGE:
            return GmBpmnServiceTask.IMAGE_KEYS.getStyleKey(metakey);
        case USER_IMAGE:
            return GmBpmnServiceTask.USERIMAGE_KEYS.getStyleKey(metakey);
        case SIMPLE:
            return GmBpmnServiceTask.SIMPLE_KEYS.getStyleKey(metakey);
        case STRUCTURED:
            return GmBpmnServiceTask.STRUCTURED_KEYS.getStyleKey(metakey);
        default:
            return null;
        }
        
    }

    @objid ("61a6e524-55b6-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        switch (getRepresentationMode()) {
        case IMAGE:
            return GmBpmnServiceTask.IMAGE_KEYS.getStyleKeys();
        case USER_IMAGE:
            return GmBpmnServiceTask.USERIMAGE_KEYS.getStyleKeys();
        case SIMPLE:
            return GmBpmnServiceTask.SIMPLE_KEYS.getStyleKeys();
        case STRUCTURED:
            return GmBpmnServiceTask.STRUCTURED_KEYS.getStyleKeys();
        default:
            return Collections.emptyList();
        }
        
    }

    /**
     * Empty constructor needed for deserialisation.
     */
    @objid ("61a6e52c-55b6-11e2-877f-002564c97630")
    public  GmBpmnServiceTask() {
        // Nothing specific to do.
    }

    @objid ("61a6e52f-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = GmAbstractObject.readMinorVersion(in, "GmBpmnServiceTask.");
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

    @objid ("61a6e535-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRepresentedElement() {
        return this.element;
    }

    @objid ("61a86b99-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return this.element;
    }

    @objid ("61a86ba0-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        GmAbstractObject.writeMinorVersion(out, "GmBpmnServiceTask.", GmBpmnServiceTask.MINOR_VERSION);
        
    }

    @objid ("61a86ba6-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.element = (BpmnServiceTask) resolveRef(getRepresentedRef());
        
        GmDefaultModelElementLabel imageModeHeader = new GmDefaultModelElementLabel(getDiagram(), getRepresentedRef());
        imageModeHeader.setRoleInComposition(GmBpmnServiceTask.IMAGE_LABEL_ROLE);
        imageModeHeader.setLayoutData(Integer.valueOf(PositionConstants.SOUTH));
        
        super.addChild(imageModeHeader, 1);
        
    }

    @objid ("61a86bab-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmBpmnServiceTask.MAJOR_VERSION;
    }

    @objid ("61a86bb0-55b6-11e2-877f-002564c97630")
    private void read_1(final IDiagramReader in) {
        super.read(in);
        this.element = (BpmnServiceTask) resolveRef(getRepresentedRef());
        
    }

    @objid ("61a86bb6-55b6-11e2-877f-002564c97630")
    @Override
    public List<GmNodeModel> getVisibleChildren() {
        // Returned result depends on current representation mode:
        List<GmNodeModel> ret = super.getVisibleChildren();
        if (getMainNode() != null) {
            switch (getMainNode().getRepresentationMode()) {
            case STRUCTURED:
            case SIMPLE: {
                GmNodeModel imageModeHeader = getFirstChild(GmBpmnServiceTask.IMAGE_LABEL_ROLE);
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
    @objid ("61a86bbf-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSatellite(final GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return GmPortContainer.SATELLITE_ROLE.equals(role)
                        || GmBpmnServiceTask.IMAGE_LABEL_ROLE.equals(role);
        
    }

    /**
     * Is this node a Port, which position is defined relatively to the Main Node's bounds.
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Port.
     */
    @objid ("61a86bd1-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isPort(final GmNodeModel childNode) {
        return GmPortContainer.PORT_ROLE.equals(childNode.getRoleInComposition());
    }

    @objid ("027753d2-f46b-434f-807e-77d449ef0c70")
    @Override
    public boolean isMainSatelliteLabel(GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return role.equals(GmBpmnServiceTask.IMAGE_LABEL_ROLE);
    }

}
