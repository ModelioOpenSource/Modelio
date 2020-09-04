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

package org.modelio.diagram.editor.activity.elements.expansionnode;

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
import org.modelio.metamodel.uml.behavior.activityModel.ExpansionNode;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Specialisation of the GmPortContainer class for expansion node.
 * 
 * @author fpoyer
 */
@objid ("2a522940-55b6-11e2-877f-002564c97630")
public class GmExpansionNode extends GmPortContainer {
    @objid ("2a52294a-55b6-11e2-877f-002564c97630")
    private ExpansionNode element;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("2a52294d-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("2a522950-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("30a8c081-58a2-11e2-9574-002564c97630")
    private static final GmExpansionNodeImageStyleKeys IMAGE_KEYS = new GmExpansionNodeImageStyleKeys();

    @objid ("30a8c083-58a2-11e2-9574-002564c97630")
    private static final GmExpansionNodeSimpleStyleKeys SIMPLE_KEYS = new GmExpansionNodeSimpleStyleKeys();

    @objid ("30a8c085-58a2-11e2-9574-002564c97630")
     static final GmExpansionNodeStructuredStyleKeys STRUCTURED_KEYS = new GmExpansionNodeStructuredStyleKeys();

    @objid ("f28dba26-7305-4da3-8f8d-ff853b58ba0b")
    private static final GmExpansionNodeUserImageStyleKeys USERIMAGE_KEYS = new GmExpansionNodeUserImageStyleKeys();

    /**
     * Constructor.
     * 
     * @param diagram the diagram in which the expansionNode is unmasked.
     * @param el the unmasked expansionNode.
     * @param ref a reference to the unmasked expansionNode.
     */
    @objid ("2a522952-55b6-11e2-877f-002564c97630")
    public GmExpansionNode(IGmDiagram diagram, ExpansionNode el, MRef ref) {
        super(diagram, ref);
        
        GmExpansionNodePrimaryNode mainNode = new GmExpansionNodePrimaryNode(diagram, el, ref);
        mainNode.setRoleInComposition(MAIN_NODE_ROLE);
        this.addChild(mainNode);
        
        this.element = el;
        GmNameLabel label = new GmNameLabel(diagram, ref);
        label.setRoleInComposition(GmPortContainer.SATELLITE_ROLE);
        label.setLayoutData(Integer.valueOf(PositionConstants.EAST));
        this.addChild(label);
    }

    /**
     * Empty constructor needed for deserialisation.
     */
    @objid ("2a53afc4-55b6-11e2-877f-002564c97630")
    public GmExpansionNode() {
        // Nothing specific to do.
    }

    @objid ("2a53afc7-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return false;
    }

    @objid ("2a53afcf-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        return false;
    }

    @objid ("2a53afd7-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRepresentedElement() {
        return this.element;
    }

    @objid ("2a53afde-55b6-11e2-877f-002564c97630")
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

    @objid ("2a53afe8-55b6-11e2-877f-002564c97630")
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

    @objid ("2a53aff1-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmExpansionNode.");
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

    @objid ("2a553659-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return getRepresentedElement();
    }

    @objid ("2a553660-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmExpansionNode.", GmExpansionNode.MINOR_VERSION);
    }

    @objid ("2a553666-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.element = (ExpansionNode) resolveRef(getRepresentedRef());
    }

    @objid ("2a55366b-55b6-11e2-877f-002564c97630")
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
    @objid ("2a553678-55b6-11e2-877f-002564c97630")
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
    @objid ("2a553682-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSatellite(final GmNodeModel childNode) {
        return GmPortContainer.SATELLITE_ROLE.equals(childNode.getRoleInComposition());
    }

    @objid ("7d9bef14-76cd-46f8-a840-91afe93fbc34")
    @Override
    public boolean isMainSatelliteLabel(GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return role.equals(SATELLITE_ROLE);
    }

}
