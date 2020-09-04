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

package org.modelio.diagram.editor.statik.elements.statemachine;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.PositionConstants;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.common.portcontainer.GmPortContainer;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Specialization of the {@link GmPortContainer} class for {@link StateMachine}.
 */
@objid ("36a542fa-55b7-11e2-877f-002564c97630")
public class GmStateMachine extends GmPortContainer {
    @objid ("36a542fe-55b7-11e2-877f-002564c97630")
    private StateMachine element;

    /**
     * Current version of this Gm.
     */
    @objid ("36a54307-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 1;

    @objid ("36a5430a-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("36a5430c-55b7-11e2-877f-002564c97630")
    private static final String IMAGE_LABEL_ROLE = "ImageLabel";

    @objid ("36a54301-55b7-11e2-877f-002564c97630")
     static final GmStateMachineStructuredStyleKeys STRUCTURED_KEYS = new GmStateMachineStructuredStyleKeys();

    @objid ("64587d7e-5bd5-11e2-9e33-00137282c51b")
     static final GmStateMachineSimpleStyleKeys SIMPLE_KEYS = new GmStateMachineSimpleStyleKeys();

    @objid ("64587d80-5bd5-11e2-9e33-00137282c51b")
     static final GmStateMachineImageStyleKeys IMAGE_KEYS = new GmStateMachineImageStyleKeys();

    @objid ("40b86459-72b0-499b-9b37-f766a0dcb241")
     static final GmStateMachineUserImageStyleKeys USERIMAGE_KEYS = new GmStateMachineUserImageStyleKeys();

    /**
     * Constructor.
     * @param diagram the diagram in which the state machine is unmasked.
     * @param el the unmasked state machine.
     * @param ref a reference to the unmasked state machine.
     */
    @objid ("36a5430e-55b7-11e2-877f-002564c97630")
    public GmStateMachine(final IGmDiagram diagram, final StateMachine el, final MRef ref) {
        super(diagram, ref);
        this.element = el;
        
        GmStateMachinePrimaryNode mainNode = new GmStateMachinePrimaryNode(diagram, ref);
        mainNode.setRoleInComposition(GmPortContainer.MAIN_NODE_ROLE);
        
        GmDefaultModelElementLabel imageModeHeader = new GmDefaultModelElementLabel(diagram, ref);
        imageModeHeader.setRoleInComposition(GmStateMachine.IMAGE_LABEL_ROLE);
        imageModeHeader.setLayoutData(Integer.valueOf(PositionConstants.SOUTH));
        
        super.addChild(mainNode);
        super.addChild(imageModeHeader);
    }

    /**
     * Empty constructor needed for deserialisation.
     */
    @objid ("36a5431d-55b7-11e2-877f-002564c97630")
    public GmStateMachine() {
        // Nothing specific to do.
    }

    @objid ("36a54320-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(final Class<? extends MObject> type) {
        return false;
    }

    @objid ("36a6c99e-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(final MObject el) {
        return false;
    }

    @objid ("36a6c9a7-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return getRepresentedElement();
    }

    @objid ("36a6c9ae-55b7-11e2-877f-002564c97630")
    @Override
    public StateMachine getRepresentedElement() {
        return this.element;
    }

    @objid ("36a6c9b5-55b7-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(final MetaKey metakey) {
        StyleKey styleKey = GmStateMachine.STRUCTURED_KEYS.getStyleKey(MetaKey.REPMODE);
        if (styleKey != null) {
            RepresentationMode mode = getDisplayedStyle().getProperty(styleKey);
            switch (mode) {
            case IMAGE:
                return GmStateMachine.IMAGE_KEYS.getStyleKey(metakey);
            case USER_IMAGE:
                return GmStateMachine.USERIMAGE_KEYS.getStyleKey(metakey);
            case SIMPLE:
                return GmStateMachine.SIMPLE_KEYS.getStyleKey(metakey);
            case STRUCTURED:
                return GmStateMachine.STRUCTURED_KEYS.getStyleKey(metakey);
            }
        }
        return null;
    }

    @objid ("36a6c9c0-55b7-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        StyleKey styleKey = GmStateMachine.STRUCTURED_KEYS.getStyleKey(MetaKey.REPMODE);
        if (styleKey != null) {
            RepresentationMode mode = getDisplayedStyle().getProperty(styleKey);
            switch (mode) {
            case IMAGE:
                return GmStateMachine.IMAGE_KEYS.getStyleKeys();
            case USER_IMAGE:
                return GmStateMachine.USERIMAGE_KEYS.getStyleKeys();
            case SIMPLE:
                return GmStateMachine.SIMPLE_KEYS.getStyleKeys();
            case STRUCTURED:
                return GmStateMachine.STRUCTURED_KEYS.getStyleKeys();
            }
        }
        return Collections.emptyList();
    }

    @objid ("36a6c9c9-55b7-11e2-877f-002564c97630")
    @Override
    public void read(final IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = GmAbstractObject.readMinorVersion(in, "GmStateMachine.");
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

    @objid ("36a6c9d0-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        GmAbstractObject.writeMinorVersion(out, "GmStateMachine.", GmStateMachine.MINOR_VERSION);
    }

    @objid ("36a8503a-55b7-11e2-877f-002564c97630")
    private void read_0(final IDiagramReader in) {
        super.read(in);
        this.element = (StateMachine) resolveRef(getRepresentedRef());
        
        GmDefaultModelElementLabel imageModeHeader = new GmDefaultModelElementLabel(getDiagram(), getRepresentedRef());
        imageModeHeader.setRoleInComposition(GmStateMachine.IMAGE_LABEL_ROLE);
        imageModeHeader.setLayoutData(Integer.valueOf(PositionConstants.SOUTH));
        
        super.addChild(imageModeHeader, 1);
    }

    @objid ("36a85040-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmStateMachine.MAJOR_VERSION;
    }

    @objid ("36a85045-55b7-11e2-877f-002564c97630")
    private void read_1(final IDiagramReader in) {
        super.read(in);
        this.element = (StateMachine) resolveRef(getRepresentedRef());
    }

    @objid ("36a8504b-55b7-11e2-877f-002564c97630")
    @Override
    public List<GmNodeModel> getVisibleChildren() {
        // Returned result depends on current representation mode:
        List<GmNodeModel> ret = super.getVisibleChildren();
        if (getMainNode() != null) {
            switch (getMainNode().getRepresentationMode()) {
            case STRUCTURED:
            case SIMPLE: {
                GmNodeModel imageModeHeader = getFirstChild(GmStateMachine.IMAGE_LABEL_ROLE);
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
    @objid ("36a85054-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isSatellite(final GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return GmPortContainer.SATELLITE_ROLE.equals(role)
                                        || GmStateMachine.IMAGE_LABEL_ROLE.equals(role);
    }

    /**
     * Is this node a Port, which position is defined relatively to the Main Node's bounds.
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Port.
     */
    @objid ("36a85066-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isPort(final GmNodeModel childNode) {
        return GmPortContainer.PORT_ROLE.equals(childNode.getRoleInComposition());
    }

    @objid ("35aeaf4a-2268-4bb3-8085-90a93b4593d7")
    @Override
    public boolean isMainSatelliteLabel(GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return role.equals(GmStateMachine.IMAGE_LABEL_ROLE);
    }

}
