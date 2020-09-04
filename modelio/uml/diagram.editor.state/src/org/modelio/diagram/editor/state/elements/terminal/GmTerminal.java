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

package org.modelio.diagram.editor.state.elements.terminal;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.PositionConstants;
import org.modelio.diagram.elements.common.label.name.GmNameLabel;
import org.modelio.diagram.elements.common.portcontainer.GmPortContainer;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.behavior.stateMachineModel.TerminatePseudoState;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Specialization of the {@link GmPortContainer} class for {@link TerminatePseudoState}.
 * 
 * @author fpoyer
 */
@objid ("f59dec3a-55b6-11e2-877f-002564c97630")
public class GmTerminal extends GmPortContainer {
    @objid ("f59dec3e-55b6-11e2-877f-002564c97630")
    private TerminatePseudoState element;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("f59dec47-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("f59f72db-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("fce78eb9-5a5b-11e2-9e33-00137282c51b")
     static final GmTerminalStructuredStyleKeys STRUCTURED_KEYS = new GmTerminalStructuredStyleKeys();

    @objid ("fce9f111-5a5b-11e2-9e33-00137282c51b")
    private static final GmTerminalSimpleStyleKeys SIMPLE_KEYS = new GmTerminalSimpleStyleKeys();

    @objid ("fce9f113-5a5b-11e2-9e33-00137282c51b")
    private static final GmTerminalImageStyleKeys IMAGE_KEYS = new GmTerminalImageStyleKeys();

    @objid ("a431ee03-7e35-4282-a2ab-8d2267caabda")
    private static final GmTerminalUserImageStyleKeys USERIMAGE_KEYS = new GmTerminalUserImageStyleKeys();

    /**
     * Constructor.
     * 
     * @param diagram the diagram in which the element is unmasked.
     * @param el the unmasked element, can be <i>null</i>.
     * @param ref the unmasked element reference, must not be <i>null</i>..
     */
    @objid ("f59f72dd-55b6-11e2-877f-002564c97630")
    public GmTerminal(IGmDiagram diagram, TerminatePseudoState el, MRef ref) {
        super(diagram, ref);
        
        GmTerminalPrimaryNode mainNode = new GmTerminalPrimaryNode(diagram, ref);
        mainNode.setRoleInComposition(MAIN_NODE_ROLE);
        
        this.element = el;
        GmNameLabel label = new GmNameLabel(diagram, ref);
        label.setRoleInComposition(GmPortContainer.SATELLITE_ROLE);
        label.setLayoutData(Integer.valueOf(PositionConstants.EAST));
        
        this.addChild(mainNode);
        this.addChild(label);
    }

    @objid ("f59f72e9-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return false;
    }

    @objid ("f59f72f1-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        return false;
    }

    @objid ("f59f72f9-55b6-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
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

    @objid ("f59f7303-55b6-11e2-877f-002564c97630")
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

    /**
     * Empty constructor needed for deserialisation.
     */
    @objid ("f59f730c-55b6-11e2-877f-002564c97630")
    public GmTerminal() {
        // Nothing specific to do.
    }

    @objid ("f59f730f-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmTerminal.");
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

    @objid ("f59f7315-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRepresentedElement() {
        return this.element;
    }

    @objid ("f59f731c-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return getRepresentedElement();
    }

    @objid ("f5a0f97b-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmTerminal.", GmTerminal.MINOR_VERSION);
    }

    @objid ("f5a0f981-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.element = (TerminatePseudoState) resolveRef(this.getRepresentedRef());
    }

    @objid ("f5a0f986-55b6-11e2-877f-002564c97630")
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
    @objid ("f5a0f993-55b6-11e2-877f-002564c97630")
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
    @objid ("f5a0f99d-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSatellite(final GmNodeModel childNode) {
        return GmPortContainer.SATELLITE_ROLE.equals(childNode.getRoleInComposition());
    }

    @objid ("c1342a34-2030-43aa-8d91-8272f8431515")
    @Override
    public boolean isMainSatelliteLabel(GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return role.equals(SATELLITE_ROLE);
    }

}
