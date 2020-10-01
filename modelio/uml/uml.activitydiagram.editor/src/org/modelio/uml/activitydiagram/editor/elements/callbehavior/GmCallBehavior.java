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

package org.modelio.uml.activitydiagram.editor.elements.callbehavior;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.PositionConstants;
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
import org.modelio.metamodel.uml.behavior.activityModel.CallBehaviorAction;
import org.modelio.metamodel.uml.behavior.activityModel.InputPin;
import org.modelio.metamodel.uml.behavior.activityModel.OutputPin;
import org.modelio.metamodel.uml.behavior.activityModel.ValuePin;
import org.modelio.uml.activitydiagram.editor.elements.pincontainer.GmPinContainer;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Specialization of the {@link GmPinContainer} class for {@link CallBehaviorAction}.
 * 
 * @author fpoyer
 */
@objid ("29b80c2e-55b6-11e2-877f-002564c97630")
public class GmCallBehavior extends GmPinContainer {
    @objid ("29b80c30-55b6-11e2-877f-002564c97630")
    private CallBehaviorAction element;

    /**
     * Current version of this Gm.
     */
    @objid ("29b80c39-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 1;

    @objid ("29b9929b-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("29b9929d-55b6-11e2-877f-002564c97630")
    private static final String IMAGE_LABEL_ROLE = "ImageLabel";

    @objid ("29b80c35-55b6-11e2-877f-002564c97630")
     static final GmCallBehaviorSimpleStyleKeys SIMPLE_KEYS = new GmCallBehaviorSimpleStyleKeys();

    @objid ("2faece15-58a2-11e2-9574-002564c97630")
     static final GmCallBehaviorStructuredStyleKeys STRUCTURED_KEYS = new GmCallBehaviorStructuredStyleKeys();

    @objid ("2faece17-58a2-11e2-9574-002564c97630")
     static final GmCallBehaviorImageStyleKeys IMAGE_KEYS = new GmCallBehaviorImageStyleKeys();

    @objid ("26a4a9bd-cb63-44ee-8510-d78e06ee52e7")
     static final GmCallBehaviorUserImageStyleKeys USERIMAGE_KEYS = new GmCallBehaviorUserImageStyleKeys();

    /**
     * Constructor.
     * 
     * @param diagram the diagram in which the callBehavior is unmasked.
     * @param el the unmasked callBehavior.
     * @param ref a reference to the unmasked callBehavior.
     */
    @objid ("29b9929f-55b6-11e2-877f-002564c97630")
    public GmCallBehavior(IGmDiagram diagram, CallBehaviorAction el, MRef ref) {
        super(diagram, ref);
        this.element = el;
        
        GmCallBehaviorPrimaryNode mainNode = new GmCallBehaviorPrimaryNode(diagram, ref);
        mainNode.setRoleInComposition(GmPortContainer.MAIN_NODE_ROLE);
        
        GmBehaviorFlatLabel imageModeHeader = new GmBehaviorFlatLabel(diagram, ref);
        imageModeHeader.setRoleInComposition(GmCallBehavior.IMAGE_LABEL_ROLE);
        imageModeHeader.setLayoutData(Integer.valueOf(PositionConstants.SOUTH));
        
        super.addChild(mainNode);
        super.addChild(imageModeHeader);
    }

    /**
     * Empty constructor needed for deserialisation.
     */
    @objid ("29b992ab-55b6-11e2-877f-002564c97630")
    public GmCallBehavior() {
        // Nothing specific to do.
    }

    @objid ("29b992ae-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return (InputPin.class.isAssignableFrom(type) || ValuePin.class.isAssignableFrom(type) || OutputPin.class.isAssignableFrom(type));
    }

    @objid ("29b992b6-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        return ((InputPin.class.isAssignableFrom(el.getClass()) ||
                        ValuePin.class.isAssignableFrom(el.getClass()) || OutputPin.class.isAssignableFrom(el.getClass())) && el.getCompositionOwner()
                                .equals(this.element));
    }

    @objid ("29b992be-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return getRepresentedElement();
    }

    @objid ("29b992c5-55b6-11e2-877f-002564c97630")
    @Override
    public ActivityAction getRepresentedElement() {
        return this.element;
    }

    @objid ("29b992cc-55b6-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        StyleKey styleKey = GmCallBehavior.STRUCTURED_KEYS.getStyleKey(MetaKey.REPMODE);
        if (styleKey != null) {
            RepresentationMode mode = getDisplayedStyle().getProperty(styleKey);
            switch (mode) {
            case IMAGE:
                return GmCallBehavior.IMAGE_KEYS.getStyleKey(metakey);
            case USER_IMAGE:
                return GmCallBehavior.USERIMAGE_KEYS.getStyleKey(metakey);
            case SIMPLE:
                return GmCallBehavior.SIMPLE_KEYS.getStyleKey(metakey);
            case STRUCTURED:
                return GmCallBehavior.STRUCTURED_KEYS.getStyleKey(metakey);
            }
        }
        return null;
    }

    @objid ("29b992d6-55b6-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        StyleKey styleKey = GmCallBehavior.STRUCTURED_KEYS.getStyleKey(MetaKey.REPMODE);
        if (styleKey != null) {
            RepresentationMode mode = getDisplayedStyle().getProperty(styleKey);
            switch (mode) {
            case IMAGE:
                return GmCallBehavior.IMAGE_KEYS.getStyleKeys();
            case USER_IMAGE:
                return GmCallBehavior.USERIMAGE_KEYS.getStyleKeys();
            case SIMPLE:
                return GmCallBehavior.SIMPLE_KEYS.getStyleKeys();
            case STRUCTURED:
                return GmCallBehavior.STRUCTURED_KEYS.getStyleKeys();
            }
        }
        return Collections.emptyList();
    }

    @objid ("29bb193d-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = GmAbstractObject.readMinorVersion(in, "GmCallBehavior.");
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

    @objid ("29bb1943-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        GmAbstractObject.writeMinorVersion(out, "GmCallBehavior.", GmCallBehavior.MINOR_VERSION);
    }

    @objid ("29bb1949-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.element = (CallBehaviorAction) resolveRef(getRepresentedRef());
        
        GmBehaviorFlatLabel imageModeHeader = new GmBehaviorFlatLabel(getDiagram(), getRepresentedRef());
        imageModeHeader.setRoleInComposition(GmCallBehavior.IMAGE_LABEL_ROLE);
        imageModeHeader.setLayoutData(Integer.valueOf(PositionConstants.SOUTH));
        
        super.addChild(imageModeHeader, 1);
    }

    @objid ("29bb194e-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmCallBehavior.MAJOR_VERSION;
    }

    @objid ("29bb1953-55b6-11e2-877f-002564c97630")
    private void read_1(final IDiagramReader in) {
        super.read(in);
        this.element = (CallBehaviorAction) resolveRef(getRepresentedRef());
    }

    @objid ("29bb1959-55b6-11e2-877f-002564c97630")
    @Override
    public List<GmNodeModel> getVisibleChildren() {
        // Returned result depends on current representation mode:
        List<GmNodeModel> ret = super.getVisibleChildren();
        if (getMainNode() != null) {
            switch (getMainNode().getRepresentationMode()) {
            case STRUCTURED:
            case SIMPLE: {
                GmNodeModel imageModeHeader = getFirstChild(GmCallBehavior.IMAGE_LABEL_ROLE);
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
     * 
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Satellite.
     */
    @objid ("29bb1962-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSatellite(final GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return GmPortContainer.SATELLITE_ROLE.equals(role)
                        || GmCallBehavior.IMAGE_LABEL_ROLE.equals(role);
    }

    /**
     * Is this node a Port, which position is defined relatively to the Main Node's bounds.
     * 
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Port.
     */
    @objid ("29bb1974-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isPort(final GmNodeModel childNode) {
        return GmPortContainer.PORT_ROLE.equals(childNode.getRoleInComposition());
    }

    @objid ("bbdb06af-a61c-41af-9410-5a1bfde0d8d9")
    @Override
    public boolean isMainSatelliteLabel(GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return role.equals(GmCallBehavior.IMAGE_LABEL_ROLE);
    }

}
