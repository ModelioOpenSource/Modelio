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

package org.modelio.diagram.editor.statik.elements.narylink;

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
import org.modelio.metamodel.uml.statik.NaryLink;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Specialization of the {@link GmPortContainer} class for {@link NaryLink} diamond.
 * 
 * @author fpoyer
 */
@objid ("35e80d79-55b7-11e2-877f-002564c97630")
public class GmNLinkNode extends GmPortContainer {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("35e80d82-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("35e80d85-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("616bdb1c-5bd5-11e2-9e33-00137282c51b")
     static final NLinkStructuredStyleKeys KEYS = GmNLinkEndLink.STRUCTURED_KEYS;

    @objid ("4d353ef7-ad44-41bd-8e0e-10a6c8a0d4ac")
    private NaryLink element;

    /**
     * Empty constructor needed for deserialization.
     */
    @objid ("35e80d87-55b7-11e2-877f-002564c97630")
    public GmNLinkNode() {
        // Nothing specific to do.
    }

    /**
     * Initialize the n-ary link node.
     * 
     * @param diagram the diagram in which the n-ary link is unmasked.
     * @param el the unmasked n-ary link.
     * @param ref a reference to the unmasked class.
     */
    @objid ("35e80d8a-55b7-11e2-877f-002564c97630")
    public GmNLinkNode(IGmDiagram diagram, NaryLink el, MRef ref) {
        super(diagram, ref);
        GmNLinkPrimaryNode mainNode = new GmNLinkPrimaryNode(diagram, el, ref);
        mainNode.setRoleInComposition(MAIN_NODE_ROLE);
        addChild(mainNode);
        
        this.element = el;
        
        final GmDefaultModelElementHeader label = new GmDefaultModelElementHeader(diagram, ref);
        label.setRoleInComposition(SATELLITE_ROLE);
        label.setLayoutData(Integer.valueOf(PositionConstants.SOUTH));
        
        addChild(label);
    }

    @objid ("35e80d96-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return false;
    }

    @objid ("35e80d9e-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        return false;
    }

    @objid ("35e80da6-55b7-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmNLinkNode.");
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

    @objid ("35e80dac-55b7-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(final MetaKey metakey) {
        return KEYS.getStyleKey(metakey);
    }

    @objid ("35e80db7-55b7-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        return KEYS.getStyleKeys();
    }

    @objid ("35e9941f-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return this.element;
    }

    @objid ("35e99426-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getRepresentedElement() {
        return this.element;
    }

    @objid ("35e9942d-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmNLinkNode.", GmNLinkNode.MINOR_VERSION);
    }

    @objid ("35e99433-55b7-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.element = (NaryLink) resolveRef(this.getRepresentedRef());
    }

    @objid ("35e99438-55b7-11e2-877f-002564c97630")
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
    @objid ("35e99445-55b7-11e2-877f-002564c97630")
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
    @objid ("35e9944f-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isSatellite(final GmNodeModel childNode) {
        return GmPortContainer.SATELLITE_ROLE.equals(childNode.getRoleInComposition());
    }

    @objid ("4c481e60-8996-4225-9fdf-081dab62c282")
    @Override
    public boolean isMainSatelliteLabel(GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return role.equals(SATELLITE_ROLE);
    }

}
