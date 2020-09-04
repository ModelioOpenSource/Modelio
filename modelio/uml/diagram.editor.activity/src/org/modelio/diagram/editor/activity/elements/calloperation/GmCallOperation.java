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

package org.modelio.diagram.editor.activity.elements.calloperation;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.PositionConstants;
import org.modelio.diagram.editor.activity.elements.pincontainer.GmPinContainer;
import org.modelio.diagram.elements.common.portcontainer.GmPortContainer;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityAction;
import org.modelio.metamodel.uml.behavior.activityModel.CallOperationAction;
import org.modelio.metamodel.uml.behavior.activityModel.InputPin;
import org.modelio.metamodel.uml.behavior.activityModel.OutputPin;
import org.modelio.metamodel.uml.behavior.activityModel.ValuePin;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Specialisation of the GmPortContainer class for CallOperation.
 * 
 * @author fpoyer
 */
@objid ("29d1fcc3-55b6-11e2-877f-002564c97630")
public class GmCallOperation extends GmPinContainer {
    @objid ("29d1fcc5-55b6-11e2-877f-002564c97630")
    private CallOperationAction element;

    /**
     * Current version of this Gm.
     */
    @objid ("29d3833a-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 1;

    @objid ("29d3833d-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("29d3833f-55b6-11e2-877f-002564c97630")
    private static final String IMAGE_LABEL_ROLE = "ImageLabel";

    @objid ("2fd9a72e-58a2-11e2-9574-002564c97630")
     static final GmCallOperationStructuredStyleKeys STRUCTURED_KEYS = new GmCallOperationStructuredStyleKeys();

    @objid ("2fd9a730-58a2-11e2-9574-002564c97630")
     static final GmCallOperationSimpleStyleKeys SIMPLE_KEYS = new GmCallOperationSimpleStyleKeys();

    @objid ("2fd9a732-58a2-11e2-9574-002564c97630")
     static final GmCallOperationImageStyleKeys IMAGE_KEYS = new GmCallOperationImageStyleKeys();

    @objid ("47aed15a-e048-4b55-b2ef-14aa985f9008")
     static final GmCallOperationUserImageStyleKeys USERIMAGE_KEYS = new GmCallOperationUserImageStyleKeys();

    /**
     * Constructor.
     * @param diagram the diagram in which the callOperation is unmasked.
     * @param el the unmasked callOperation.
     * @param ref a reference to the unmasked callOperation.
     */
    @objid ("29d38341-55b6-11e2-877f-002564c97630")
    public GmCallOperation(IGmDiagram diagram, CallOperationAction el, MRef ref) {
        super(diagram, ref);
        this.element = el;
        
        GmCallOperationPrimaryNode mainNode = new GmCallOperationPrimaryNode(diagram, ref);
        mainNode.setRoleInComposition(GmPortContainer.MAIN_NODE_ROLE);
        
        GmCallOperationFlatLabel imageModeHeader = new GmCallOperationFlatLabel(diagram, ref);
        imageModeHeader.setRoleInComposition(GmCallOperation.IMAGE_LABEL_ROLE);
        imageModeHeader.setLayoutData(Integer.valueOf(PositionConstants.SOUTH));
        
        super.addChild(mainNode);
        super.addChild(imageModeHeader);
    }

    @objid ("29d3834d-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return (InputPin.class.isAssignableFrom(type) || ValuePin.class.isAssignableFrom(type) || OutputPin.class.isAssignableFrom(type));
    }

    @objid ("29d38355-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        return ((InputPin.class.isAssignableFrom(el.getClass()) ||
                        ValuePin.class.isAssignableFrom(el.getClass()) || OutputPin.class.isAssignableFrom(el.getClass())) && el.getCompositionOwner()
                                .equals(this.element));
    }

    @objid ("29d3835d-55b6-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        StyleKey styleKey = GmCallOperation.STRUCTURED_KEYS.getStyleKey(MetaKey.REPMODE);
        if (styleKey != null) {
            RepresentationMode mode = getDisplayedStyle().getProperty(styleKey);
            switch (mode) {
            case IMAGE:
                return GmCallOperation.IMAGE_KEYS.getStyleKey(metakey);
            case USER_IMAGE:
                return GmCallOperation.USERIMAGE_KEYS.getStyleKey(metakey);
            case SIMPLE:
                return GmCallOperation.SIMPLE_KEYS.getStyleKey(metakey);
            case STRUCTURED:
                return GmCallOperation.STRUCTURED_KEYS.getStyleKey(metakey);
            }
        }
        return null;
    }

    @objid ("29d38367-55b6-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        StyleKey styleKey = GmCallOperation.STRUCTURED_KEYS.getStyleKey(MetaKey.REPMODE);
        if (styleKey != null) {
            RepresentationMode mode = getDisplayedStyle().getProperty(styleKey);
            switch (mode) {
            case IMAGE:
                return GmCallOperation.IMAGE_KEYS.getStyleKeys();
            case USER_IMAGE:
                return GmCallOperation.USERIMAGE_KEYS.getStyleKeys();
            case SIMPLE:
                return GmCallOperation.SIMPLE_KEYS.getStyleKeys();
            case STRUCTURED:
                return GmCallOperation.STRUCTURED_KEYS.getStyleKeys();
            }
        }
        return Collections.emptyList();
    }

    /**
     * Empty constructor needed for deserialization.
     */
    @objid ("29d38370-55b6-11e2-877f-002564c97630")
    public GmCallOperation() {
        // Nothing specific to do.
    }

    @objid ("29d38373-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = GmAbstractObject.readMinorVersion(in, "GmCallOperation.");
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

    @objid ("29d509dc-55b6-11e2-877f-002564c97630")
    @Override
    public ActivityAction getRepresentedElement() {
        return this.element;
    }

    @objid ("29d509e3-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return getRepresentedElement();
    }

    @objid ("29d509ea-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        GmAbstractObject.writeMinorVersion(out, "GmCallOperation.", GmCallOperation.MINOR_VERSION);
    }

    @objid ("29d509f0-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.element = (CallOperationAction) resolveRef(getRepresentedRef());
        
        GmCallOperationFlatLabel imageModeHeader = new GmCallOperationFlatLabel(getDiagram(), getRepresentedRef());
        imageModeHeader.setRoleInComposition(GmCallOperation.IMAGE_LABEL_ROLE);
        imageModeHeader.setLayoutData(Integer.valueOf(PositionConstants.SOUTH));
        
        super.addChild(imageModeHeader, 1);
    }

    @objid ("29d509f5-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmCallOperation.MAJOR_VERSION;
    }

    @objid ("29d509fa-55b6-11e2-877f-002564c97630")
    private void read_1(final IDiagramReader in) {
        super.read(in);
        this.element = (CallOperationAction) resolveRef(getRepresentedRef());
    }

    @objid ("29d50a00-55b6-11e2-877f-002564c97630")
    @Override
    public List<GmNodeModel> getVisibleChildren() {
        // Returned result depends on current representation mode:
        List<GmNodeModel> ret = super.getVisibleChildren();
        if (getMainNode() != null) {
            switch (getMainNode().getRepresentationMode()) {
            case STRUCTURED:
            case SIMPLE: {
                GmNodeModel imageModeHeader = getFirstChild(GmCallOperation.IMAGE_LABEL_ROLE);
                ret.remove(imageModeHeader);
                break;
            }
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
    @objid ("29d50a09-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSatellite(final GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return GmPortContainer.SATELLITE_ROLE.equals(role)
                        || GmCallOperation.IMAGE_LABEL_ROLE.equals(role);
    }

    /**
     * Is this node a Port, which position is defined relatively to the Main Node's bounds.
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Port.
     */
    @objid ("29d69085-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isPort(final GmNodeModel childNode) {
        return GmPortContainer.PORT_ROLE.equals(childNode.getRoleInComposition());
    }

    @objid ("2d47bbd3-7e91-4536-b950-6a28938cb68b")
    @Override
    public boolean isMainSatelliteLabel(GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return role.equals(GmCallOperation.IMAGE_LABEL_ROLE);
    }

}
