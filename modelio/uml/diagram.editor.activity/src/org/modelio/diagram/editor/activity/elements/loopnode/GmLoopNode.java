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

package org.modelio.diagram.editor.activity.elements.loopnode;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.PositionConstants;
import org.modelio.diagram.editor.activity.elements.pincontainer.GmPinContainer;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.common.portcontainer.GmPortContainer;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.AbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityAction;
import org.modelio.metamodel.uml.behavior.activityModel.InputPin;
import org.modelio.metamodel.uml.behavior.activityModel.LoopNode;
import org.modelio.metamodel.uml.behavior.activityModel.OutputPin;
import org.modelio.metamodel.uml.behavior.activityModel.ValuePin;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Specialization of the {@link GmPortContainer} class for {@link ILoopNode}.
 * 
 * @author fpoyer
 */
@objid ("2ab864fa-55b6-11e2-877f-002564c97630")
public class GmLoopNode extends GmPinContainer {
    @objid ("2ab864fc-55b6-11e2-877f-002564c97630")
    private LoopNode element;

    /**
     * Current version of this Gm.
     */
    @objid ("2ab9eb9a-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 1;

    @objid ("2ab9eb9d-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("2ab9eb9f-55b6-11e2-877f-002564c97630")
    private static final String IMAGE_LABEL_ROLE = "ImageLabel";

    @objid ("d227340a-55c0-11e2-9337-002564c97630")
     static final AbstractStyleKeyProvider STRUCTURED_KEYS = new GmLoopNodeStructuredStyleKeys();

    @objid ("d227340c-55c0-11e2-9337-002564c97630")
     static final AbstractStyleKeyProvider SIMPLE_KEYS = new GmLoopNodeSimpleStyleKeys();

    @objid ("d227340e-55c0-11e2-9337-002564c97630")
     static final AbstractStyleKeyProvider IMAGE_KEYS = new GmLoopNodeImageStyleKeys();

    @objid ("5c8b0029-0883-4d80-81d5-a270bc0820cc")
     static final AbstractStyleKeyProvider USERIMAGE_KEYS = new GmLoopNodeUserImageStyleKeys();

    /**
     * Constructor.
     * @param diagram the diagram in which the loopNode is unmasked.
     * @param el the unmasked loopNode.
     * @param ref a reference to the unmasked loopNode.
     */
    @objid ("2ab9eba1-55b6-11e2-877f-002564c97630")
    public GmLoopNode(IGmDiagram diagram, LoopNode el, MRef ref) {
        super(diagram, ref);
        this.element = el;
        
        GmLoopNodePrimaryNode mainNode = new GmLoopNodePrimaryNode(diagram, ref);
        mainNode.setRoleInComposition(GmPortContainer.MAIN_NODE_ROLE);
        
        GmDefaultModelElementLabel imageModeHeader = new GmDefaultModelElementLabel(diagram, ref);
        imageModeHeader.setRoleInComposition(GmLoopNode.IMAGE_LABEL_ROLE);
        imageModeHeader.setLayoutData(Integer.valueOf(PositionConstants.SOUTH));
        
        super.addChild(mainNode);
        super.addChild(imageModeHeader);
    }

    /**
     * Empty constructor needed for deserialisation.
     */
    @objid ("2ab9ebad-55b6-11e2-877f-002564c97630")
    public GmLoopNode() {
        // Nothing specific to do.
    }

    @objid ("2ab9ebb0-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return (InputPin.class.isAssignableFrom(type) || ValuePin.class.isAssignableFrom(type) || OutputPin.class.isAssignableFrom(type));
    }

    @objid ("2ab9ebb8-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        return ((InputPin.class.isAssignableFrom(el.getClass()) ||
                        ValuePin.class.isAssignableFrom(el.getClass()) || OutputPin.class.isAssignableFrom(el.getClass())) && el.getCompositionOwner()
                                .equals(this.element));
    }

    @objid ("2ab9ebc0-55b6-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        StyleKey styleKey = GmLoopNode.STRUCTURED_KEYS.getStyleKey(MetaKey.REPMODE);
        if (styleKey != null) {
            RepresentationMode mode = getDisplayedStyle().getProperty(styleKey);
            switch (mode) {
            case IMAGE:
                return GmLoopNode.IMAGE_KEYS.getStyleKey(metakey);
            case USER_IMAGE:
                return GmLoopNode.USERIMAGE_KEYS.getStyleKey(metakey);
            case SIMPLE:
                return GmLoopNode.SIMPLE_KEYS.getStyleKey(metakey);
            case STRUCTURED:
                return GmLoopNode.STRUCTURED_KEYS.getStyleKey(metakey);
            }
        }
        return null;
    }

    @objid ("2ab9ebca-55b6-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        StyleKey styleKey = GmLoopNode.STRUCTURED_KEYS.getStyleKey(MetaKey.REPMODE);
        if (styleKey != null) {
            RepresentationMode mode = getDisplayedStyle().getProperty(styleKey);
            switch (mode) {
            case IMAGE:
                return GmLoopNode.IMAGE_KEYS.getStyleKeys();
            case USER_IMAGE:
                return GmLoopNode.USERIMAGE_KEYS.getStyleKeys();
            case SIMPLE:
                return GmLoopNode.SIMPLE_KEYS.getStyleKeys();
            case STRUCTURED:
                return GmLoopNode.STRUCTURED_KEYS.getStyleKeys();
            }
        }
        return Collections.emptyList();
    }

    @objid ("2ab9ebd3-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = GmAbstractObject.readMinorVersion(in, "GmLoopNode.");
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

    @objid ("2abb723c-55b6-11e2-877f-002564c97630")
    @Override
    public ActivityAction getRepresentedElement() {
        return this.element;
    }

    @objid ("2abb7243-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return getRepresentedElement();
    }

    @objid ("2abb724a-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        GmAbstractObject.writeMinorVersion(out, "GmLoopNode.", GmLoopNode.MINOR_VERSION);
    }

    @objid ("2abb7250-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.element = (LoopNode) resolveRef(getRepresentedRef());
        
        GmDefaultModelElementLabel imageModeHeader = new GmDefaultModelElementLabel(getDiagram(), getRepresentedRef());
        imageModeHeader.setRoleInComposition(GmLoopNode.IMAGE_LABEL_ROLE);
        imageModeHeader.setLayoutData(Integer.valueOf(PositionConstants.SOUTH));
        
        super.addChild(imageModeHeader, 1);
    }

    @objid ("2abb7255-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmLoopNode.MAJOR_VERSION;
    }

    @objid ("2abb725a-55b6-11e2-877f-002564c97630")
    private void read_1(final IDiagramReader in) {
        super.read(in);
        this.element = (LoopNode) resolveRef(getRepresentedRef());
    }

    @objid ("2abb7260-55b6-11e2-877f-002564c97630")
    @Override
    public List<GmNodeModel> getVisibleChildren() {
        // Returned result depends on current representation mode:
        List<GmNodeModel> ret = super.getVisibleChildren();
        if (getMainNode() != null) {
            switch (getMainNode().getRepresentationMode()) {
            case STRUCTURED:
            case SIMPLE: {
                GmNodeModel imageModeHeader = getFirstChild(GmLoopNode.IMAGE_LABEL_ROLE);
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
    @objid ("2abb7269-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSatellite(final GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return GmPortContainer.SATELLITE_ROLE.equals(role)
                        || GmLoopNode.IMAGE_LABEL_ROLE.equals(role);
    }

    /**
     * Is this node a Port, which position is defined relatively to the Main Node's bounds.
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Port.
     */
    @objid ("2abcf8db-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isPort(final GmNodeModel childNode) {
        return GmPortContainer.PORT_ROLE.equals(childNode.getRoleInComposition());
    }

    @objid ("a2e52ae8-b9d4-4e44-b849-bc2434ed8cc6")
    @Override
    public boolean isMainSatelliteLabel(GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return role.equals(GmLoopNode.IMAGE_LABEL_ROLE);
    }

}
