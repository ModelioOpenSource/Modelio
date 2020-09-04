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

package org.modelio.diagram.editor.statik.elements.naryconnector;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.PositionConstants;
import org.modelio.diagram.elements.common.header.GmDefaultModelElementHeader;
import org.modelio.diagram.elements.common.portcontainer.GmPortContainer;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.statik.Connector;
import org.modelio.metamodel.uml.statik.NaryConnector;
import org.modelio.metamodel.uml.statik.NaryLink;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Specialization of the {@link GmPortContainer} class for {@link Connector} diamond.
 * 
 * @author cmarin
 */
@objid ("35d744bc-55b7-11e2-877f-002564c97630")
public class GmNConnectorNode extends GmPortContainer {
    @objid ("35d744c2-55b7-11e2-877f-002564c97630")
    private NaryConnector element;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("35d744c5-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("35d744c8-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("61350504-5bd5-11e2-9e33-00137282c51b")
     static final NConnectorStructuredStyleKeys KEYS = GmNConnectorEndLink.STRUCTURED_KEYS;

    /**
     * Empty constructor needed for deserialization.
     */
    @objid ("35d744ca-55b7-11e2-877f-002564c97630")
    public GmNConnectorNode() {
        // Nothing specific to do.
    }

    /**
     * Initialize the n-ary association node.
     * 
     * @param diagram the diagram in which the n-ary association is unmasked.
     * @param el the unmasked n-ary association.
     * @param ref a reference to the unmasked class.
     */
    @objid ("35d744cd-55b7-11e2-877f-002564c97630")
    public GmNConnectorNode(IGmDiagram diagram, NaryConnector el, MRef ref) {
        super(diagram, ref);
        GmNConnectorPrimaryNode mainNode = new GmNConnectorPrimaryNode(diagram, el, ref);
        mainNode.setRoleInComposition(MAIN_NODE_ROLE);
        this.addChild(mainNode);
        
        this.element = el;
        
        final GmDefaultModelElementHeader label = new GmDefaultModelElementHeader(diagram, ref);
        label.setRoleInComposition(SATELLITE_ROLE);
        label.setLayoutData(Integer.valueOf(PositionConstants.SOUTH));
        
        addChild(label);
    }

    @objid ("35d744d9-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return false;
    }

    @objid ("35d8cb39-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        return false;
    }

    @objid ("35d8cb41-55b7-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmNConnectorNode.");
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

    @objid ("35d8cb47-55b7-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(final MetaKey metakey) {
        return KEYS.getStyleKey(metakey);
    }

    @objid ("35d8cb52-55b7-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        return KEYS.getStyleKeys();
    }

    @objid ("35d8cb5b-55b7-11e2-877f-002564c97630")
    @Override
    public NaryLink getRelatedElement() {
        return this.element;
    }

    @objid ("35d8cb62-55b7-11e2-877f-002564c97630")
    @Override
    public NaryLink getRepresentedElement() {
        return this.element;
    }

    @objid ("35d8cb69-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmNConnectorNode.", GmNConnectorNode.MINOR_VERSION);
    }

    @objid ("35d8cb6f-55b7-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.element = (NaryConnector) resolveRef(this.getRepresentedRef());
    }

    @objid ("35d8cb74-55b7-11e2-877f-002564c97630")
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
    @objid ("35da51dd-55b7-11e2-877f-002564c97630")
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
    @objid ("35da51e7-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isSatellite(final GmNodeModel childNode) {
        return GmPortContainer.SATELLITE_ROLE.equals(childNode.getRoleInComposition());
    }

    @objid ("527dce05-839a-4606-bded-c0a841a7c54c")
    @Override
    public boolean isMainSatelliteLabel(GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return role.equals(SATELLITE_ROLE);
    }

}
