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

package org.modelio.uml.sequencediagram.editor.elements.gate;

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
@objid ("d8f75420-55b6-11e2-877f-002564c97630")
public class GmGate extends GmPortContainer {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("d8f7542d-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("d8f75430-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("30b05e77-0e17-4cbc-9d43-2e8201198d60")
     static final GmGateStructuredStyleKeys STRUCTURED_KEYS = new GmGateStructuredStyleKeys();

    @objid ("0e057a55-6b9c-4116-ab49-cf1e7b5f6108")
     static final GmGateImageStyleKeys IMAGE_KEYS = new GmGateImageStyleKeys();

    @objid ("84abecfb-5b76-4269-92d6-998ddf83b793")
     static final GmGateSimpleStyleKeys SIMPLE_KEYS = new GmGateSimpleStyleKeys();

    @objid ("9979e159-dc9d-49ce-81f5-2456b42ac4d9")
    private Gate gate;

    @objid ("c968c519-c25f-4ad5-8cf6-e5e8165be06a")
     static final GmGateUserImageStyleKeys USERIMAGE_KEYS = new GmGateUserImageStyleKeys();

    /**
     * Empty c'tor for deserialisation.
     */
    @objid ("d8f75432-55b6-11e2-877f-002564c97630")
    public GmGate() {
        super();
    }

    /**
     * C'tor.
     * 
     * @param diagram the diagram in which this Gm is created.
     * @param gate the gate to represent.
     * @param relatedRef a ref to the gate.
     */
    @objid ("d8f75435-55b6-11e2-877f-002564c97630")
    public GmGate(final IGmDiagram diagram, final Gate gate, final MRef relatedRef) {
        super(diagram, relatedRef);
        
        GmGatePrimaryNode mainNode = new GmGatePrimaryNode(diagram, relatedRef);
        mainNode.setRoleInComposition(MAIN_NODE_ROLE);
        addChild(mainNode);
        
        this.gate = gate;
        
        GmDefaultModelElementLabel header = new GmDefaultModelElementLabel(diagram, relatedRef);
        header.setRoleInComposition(GmPortContainer.SATELLITE_ROLE);
        header.setLayoutData(PositionConstants.SOUTH);
        addChild(header);
    }

    @objid ("d8f8daa5-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(final Class<? extends MObject> type) {
        return false;
    }

    @objid ("d8f8daae-55b6-11e2-877f-002564c97630")
    @Override
    public Gate getRelatedElement() {
        return this.gate;
    }

    @objid ("d8f8dab5-55b6-11e2-877f-002564c97630")
    @Override
    public Gate getRepresentedElement() {
        return this.gate;
    }

    @objid ("d8f8dabc-55b6-11e2-877f-002564c97630")
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

    @objid ("d8f8dac7-55b6-11e2-877f-002564c97630")
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

    @objid ("d8f8dad0-55b6-11e2-877f-002564c97630")
    @Override
    public void read(final IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmGate.");
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

    @objid ("d8f8dad7-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmGate.", GmGate.MINOR_VERSION);
    }

    @objid ("d8f8dadd-55b6-11e2-877f-002564c97630")
    private void read_0(final IDiagramReader in) {
        super.read(in);
        this.gate = (Gate) resolveRef(getRepresentedRef());
    }

    @objid ("d8fa613e-55b6-11e2-877f-002564c97630")
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
    @objid ("d8fa614b-55b6-11e2-877f-002564c97630")
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
    @objid ("d8fa6155-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSatellite(final GmNodeModel childNode) {
        return GmPortContainer.SATELLITE_ROLE.equals(childNode.getRoleInComposition());
    }

    @objid ("ea0ef1ac-90ec-4f0e-86d9-7af2794c9ba6")
    @Override
    public boolean isMainSatelliteLabel(GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return role.equals(SATELLITE_ROLE);
    }

}
