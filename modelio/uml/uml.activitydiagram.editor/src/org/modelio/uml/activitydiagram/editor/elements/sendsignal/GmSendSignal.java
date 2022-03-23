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
package org.modelio.uml.activitydiagram.editor.elements.sendsignal;

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
import org.modelio.diagram.styles.core.AbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.metamodel.uml.behavior.activityModel.InputPin;
import org.modelio.metamodel.uml.behavior.activityModel.OutputPin;
import org.modelio.metamodel.uml.behavior.activityModel.SendSignalAction;
import org.modelio.metamodel.uml.behavior.activityModel.ValuePin;
import org.modelio.uml.activitydiagram.editor.elements.pincontainer.GmPinContainer;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Specialization of the {@link GmPortContainer} class for {@link ISendSignalAction}.
 * 
 * @author fpoyer
 */
@objid ("2b42075a-55b6-11e2-877f-002564c97630")
public class GmSendSignal extends GmPinContainer {
    @objid ("2b42075c-55b6-11e2-877f-002564c97630")
    private SendSignalAction element;

    /**
     * Current version of this Gm.
     */
    @objid ("2b42076b-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 1;

    @objid ("2b42076e-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("2b420770-55b6-11e2-877f-002564c97630")
    private static final String IMAGE_LABEL_ROLE = "ImageLabel";

    @objid ("d098a4c9-55c0-11e2-9337-002564c97630")
    static final AbstractStyleKeyProvider STRUCTURED_KEYS = new GmSendSignalStructuredStyleKeys();

    @objid ("d098a4cb-55c0-11e2-9337-002564c97630")
    static final AbstractStyleKeyProvider SIMPLE_KEYS = new GmSendSignalSimpleStyleKeys();

    @objid ("d098a4cd-55c0-11e2-9337-002564c97630")
    static final AbstractStyleKeyProvider IMAGE_KEYS = new GmSendSignalImageStyleKeys();

    @objid ("a23f687c-5aa2-4a28-99eb-2e8a642c6a74")
    static final AbstractStyleKeyProvider USERIMAGE_KEYS = new GmSendSignalUserImageStyleKeys();

    /**
     * Constructor.
     * @param diagram the diagram in which the sendSignal is unmasked.
     * @param el the unmasked element, can be <i>null</i>.
     * @param ref the unmasked element reference, must not be <i>null</i>..
     */
    @objid ("2b420772-55b6-11e2-877f-002564c97630")
    public  GmSendSignal(IGmDiagram diagram, SendSignalAction el, MRef ref) {
        super(diagram, ref);
        this.element = el;
        
        GmSendSignalPrimaryNode mainNode = new GmSendSignalPrimaryNode(diagram, ref);
        mainNode.setRoleInComposition(GmPortContainer.MAIN_NODE_ROLE);
        
        GmDefaultModelElementLabel imageModeHeader = new GmDefaultModelElementLabel(diagram, ref);
        imageModeHeader.setRoleInComposition(GmSendSignal.IMAGE_LABEL_ROLE);
        imageModeHeader.setLayoutData(Integer.valueOf(PositionConstants.SOUTH));
        
        super.addChild(mainNode);
        super.addChild(imageModeHeader);
        
    }

    @objid ("2b42077e-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return (InputPin.class.isAssignableFrom(type) || ValuePin.class.isAssignableFrom(type) || OutputPin.class.isAssignableFrom(type));
    }

    @objid ("2b438dfa-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        return ((InputPin.class.isAssignableFrom(el.getClass()) ||
                        ValuePin.class.isAssignableFrom(el.getClass()) || OutputPin.class.isAssignableFrom(el.getClass())) && el.getCompositionOwner()
                                .equals(this.element));
        
    }

    @objid ("2b438e02-55b6-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        StyleKey styleKey = GmSendSignal.STRUCTURED_KEYS.getStyleKey(MetaKey.REPMODE);
        if (styleKey != null) {
            RepresentationMode mode = getDisplayedStyle().getProperty(styleKey);
            switch (mode) {
            case IMAGE:
                return GmSendSignal.IMAGE_KEYS.getStyleKey(metakey);
            case USER_IMAGE:
                return GmSendSignal.USERIMAGE_KEYS.getStyleKey(metakey);
            case SIMPLE:
                return GmSendSignal.SIMPLE_KEYS.getStyleKey(metakey);
            case STRUCTURED:
                return GmSendSignal.STRUCTURED_KEYS.getStyleKey(metakey);
            }
        }
        return null;
    }

    @objid ("2b438e0c-55b6-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        StyleKey styleKey = GmSendSignal.STRUCTURED_KEYS.getStyleKey(MetaKey.REPMODE);
        if (styleKey != null) {
            RepresentationMode mode = getDisplayedStyle().getProperty(styleKey);
            switch (mode) {
            case IMAGE:
                return GmSendSignal.IMAGE_KEYS.getStyleKeys();
            case USER_IMAGE:
                return GmSendSignal.USERIMAGE_KEYS.getStyleKeys();
            case SIMPLE:
                return GmSendSignal.SIMPLE_KEYS.getStyleKeys();
            case STRUCTURED:
                return GmSendSignal.STRUCTURED_KEYS.getStyleKeys();
            }
        }
        return Collections.emptyList();
    }

    /**
     * Empty constructor needed for deserialisation.
     */
    @objid ("2b438e15-55b6-11e2-877f-002564c97630")
    public  GmSendSignal() {
        // Nothing specific to do.
    }

    @objid ("2b438e18-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = GmAbstractObject.readMinorVersion(in, "GmSendSignal.");
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

    @objid ("2b438e1e-55b6-11e2-877f-002564c97630")
    @Override
    public SendSignalAction getRepresentedElement() {
        return this.element;
    }

    @objid ("2b438e25-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return getRepresentedElement();
    }

    @objid ("2b438e2c-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        GmAbstractObject.writeMinorVersion(out, "GmSendSignal.", GmSendSignal.MINOR_VERSION);
        
    }

    @objid ("2b438e32-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.element = (SendSignalAction) resolveRef(getRepresentedRef());
        
        GmDefaultModelElementLabel imageModeHeader = new GmDefaultModelElementLabel(getDiagram(), getRepresentedRef());
        imageModeHeader.setRoleInComposition(GmSendSignal.IMAGE_LABEL_ROLE);
        imageModeHeader.setLayoutData(Integer.valueOf(PositionConstants.SOUTH));
        
        super.addChild(imageModeHeader, 1);
        
    }

    @objid ("2b438e37-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmSendSignal.MAJOR_VERSION;
    }

    @objid ("2b45149a-55b6-11e2-877f-002564c97630")
    private void read_1(final IDiagramReader in) {
        super.read(in);
        this.element = (SendSignalAction) resolveRef(getRepresentedRef());
        
    }

    @objid ("2b4514a0-55b6-11e2-877f-002564c97630")
    @Override
    public List<GmNodeModel> getVisibleChildren() {
        // Returned result depends on current representation mode:
        List<GmNodeModel> ret = super.getVisibleChildren();
        if (getMainNode() != null) {
            switch (getMainNode().getRepresentationMode()) {
            case STRUCTURED:
            case SIMPLE: {
                GmNodeModel imageModeHeader = getFirstChild(GmSendSignal.IMAGE_LABEL_ROLE);
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
    @objid ("2b4514a9-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSatellite(final GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return GmPortContainer.SATELLITE_ROLE.equals(role)
                        || GmSendSignal.IMAGE_LABEL_ROLE.equals(role);
        
    }

    /**
     * Is this node a Port, which position is defined relatively to the Main Node's bounds.
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Port.
     */
    @objid ("2b4514bb-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isPort(final GmNodeModel childNode) {
        return GmPortContainer.PORT_ROLE.equals(childNode.getRoleInComposition());
    }

    @objid ("5f841943-a4ce-4ca2-910c-33ed0359460d")
    @Override
    public boolean isMainSatelliteLabel(GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return role.equals(GmSendSignal.IMAGE_LABEL_ROLE);
    }

}
