/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.diagram.editor.sequence.elements.interactionuse.gate;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.PositionConstants;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.common.portcontainer.GmPortContainer;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.behavior.interactionModel.Gate;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Gm for a gate that is on an interaction use (not on the diagram background)
 * 
 * @author fpoyer
 */
@objid ("d9145209-55b6-11e2-877f-002564c97630")
public class GmGateOnInteractionUse extends GmPortContainer {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("d9145216-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("d9145219-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("d9145214-55b6-11e2-877f-002564c97630")
     static final GmGateOnInteractionUseImageStyleKeys IMAGE_KEYS = new GmGateOnInteractionUseImageStyleKeys();

    @objid ("6db47b54-677f-4759-94c3-98fba785c1f3")
     static final GmGateOnInteractionUseStructuredStyleKeys STRUCTURED_KEYS = new GmGateOnInteractionUseStructuredStyleKeys();

    @objid ("c4d358c5-3dd2-47f6-b6bf-7fe86c64cd3e")
     static final GmGateOnInteractionUseSimpleStyleKeys SIMPLE_KEYS = new GmGateOnInteractionUseSimpleStyleKeys();

    @objid ("79653e79-a6cd-48d5-9da8-d00df7ebcca2")
    private Gate gate;

    @objid ("600b1e0d-1217-4faf-b8c3-ce9fcab7e490")
     static final GmGateOnInteractionUseUserImageStyleKeys USERIMAGE_KEYS = new GmGateOnInteractionUseUserImageStyleKeys();

    /**
     * Empty c'tor for deserialisation.
     */
    @objid ("d914521b-55b6-11e2-877f-002564c97630")
    public GmGateOnInteractionUse() {
        super();
    }

    /**
     * C'tor.
     * 
     * @param diagram the diagram in which this Gm is created.
     * @param gate the gate to represent.
     * @param relatedRef a ref to the gate.
     */
    @objid ("d915d87b-55b6-11e2-877f-002564c97630")
    public GmGateOnInteractionUse(final IGmDiagram diagram, final Gate gate, final MRef relatedRef) {
        super(diagram, relatedRef);
        GmGateOnInteractionUsePrimaryNode mainNode = new GmGateOnInteractionUsePrimaryNode(diagram, relatedRef);
        mainNode.setRoleInComposition(MAIN_NODE_ROLE);
        addChild(mainNode);
        
        this.gate = gate;
        GmDefaultModelElementLabel header = new GmDefaultModelElementLabel(diagram, relatedRef);
        header.setRoleInComposition(GmPortContainer.SATELLITE_ROLE);
        header.setLayoutData(PositionConstants.SOUTH);
        addChild(header);
    }

    @objid ("d915d88a-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(final Class<? extends MObject> type) {
        return false;
    }

    @objid ("d915d893-55b6-11e2-877f-002564c97630")
    @Override
    public Gate getRelatedElement() {
        return this.gate;
    }

    @objid ("d915d89a-55b6-11e2-877f-002564c97630")
    @Override
    public Gate getRepresentedElement() {
        return this.gate;
    }

    @objid ("d915d8a1-55b6-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(final MetaKey metakey) {
        StyleKey styleKey = STRUCTURED_KEYS.getStyleKey(MetaKey.REPMODE);
        if (styleKey != null) {
            RepresentationMode mode = getDisplayedStyle().getProperty(styleKey);
            switch (mode) {
            case IMAGE:
                return IMAGE_KEYS.getStyleKey(metakey);
            case USER_IMAGE:
                return USERIMAGE_KEYS.getStyleKey(metakey);
            case SIMPLE:
                return SIMPLE_KEYS.getStyleKey(metakey);
            case STRUCTURED:
                return STRUCTURED_KEYS.getStyleKey(metakey);
            }
        }
        return null;
    }

    @objid ("d915d8ac-55b6-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        StyleKey styleKey = STRUCTURED_KEYS.getStyleKey(MetaKey.REPMODE);
        if (styleKey != null) {
            RepresentationMode mode = getDisplayedStyle().getProperty(styleKey);
            switch (mode) {
            case IMAGE:
                return IMAGE_KEYS.getStyleKeys();
            case USER_IMAGE:
                return USERIMAGE_KEYS.getStyleKeys();
            case SIMPLE:
                return SIMPLE_KEYS.getStyleKeys();
            case STRUCTURED:
                return STRUCTURED_KEYS.getStyleKeys();
            }
        }
        return Collections.emptyList();
    }

    @objid ("d915d8b5-55b6-11e2-877f-002564c97630")
    @Override
    public void read(final IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmGateOnInteractionUse.");
        switch (readVersion) {
        case 0: {
            read_0(in);
            break;
        }
        default: {
            assert (false) : "version number not covered!";
            // reading as last handled version: 0
            read_0(in);
            break;
        }
        }
    }

    @objid ("d9175f19-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmGateOnInteractionUse.", GmGateOnInteractionUse.MINOR_VERSION);
    }

    @objid ("d9175f1f-55b6-11e2-877f-002564c97630")
    private void read_0(final IDiagramReader in) {
        super.read(in);
        this.gate = (Gate) resolveRef(getRepresentedRef());
    }

    @objid ("d9175f25-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    /**
     * Is this node a Port, which position is defined relatively to the Main Node's bounds.
     * 
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Port.
     */
    @objid ("d9175f32-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isPort(final GmNodeModel childNode) {
        return GmPortContainer.PORT_ROLE.equals(childNode.getRoleInComposition());
    }

    /**
     * Is this node a Satellite, which position is defined relatively to the Main Node's bounds.
     * 
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Satellite.
     */
    @objid ("d9175f3c-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSatellite(final GmNodeModel childNode) {
        return GmPortContainer.SATELLITE_ROLE.equals(childNode.getRoleInComposition());
    }

    @objid ("3064037b-0b81-4270-923e-08d2c4a9cd41")
    @Override
    public boolean isMainSatelliteLabel(GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return role.equals(SATELLITE_ROLE);
    }

}
