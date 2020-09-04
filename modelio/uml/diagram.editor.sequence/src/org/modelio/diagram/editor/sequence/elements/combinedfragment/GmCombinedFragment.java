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

package org.modelio.diagram.editor.sequence.elements.combinedfragment;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.sequence.elements.combinedfragment.primarynode.GmCombinedFragmentPrimaryNode;
import org.modelio.diagram.elements.common.portcontainer.GmPortContainer;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.behavior.interactionModel.CombinedFragment;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Gm for CombinedFragment. Specialisation of GmPortContainer.
 * 
 * @author fpoyer
 */
@objid ("d8c4f966-55b6-11e2-877f-002564c97630")
public class GmCombinedFragment extends GmPortContainer {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("d8c4f96f-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("d8c4f972-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("e259125b-6277-4413-a308-eb0d057abd02")
    private static final GmCombinedFragmentStyleKeys KEYS = new GmCombinedFragmentStyleKeys();

    @objid ("551d022c-bc91-4d48-8521-ddaef0316d43")
    private CombinedFragment combinedFragment;

    /**
     * Empty c'tor for deserialisation.
     */
    @objid ("d8c4f974-55b6-11e2-877f-002564c97630")
    public GmCombinedFragment() {
        super();
    }

    /**
     * C'tor.
     * @param diagram diagram in which this gm is created.
     * @param combinedFragment the represented CombinedFragment
     * @param relatedRef a reference to the represented element.
     */
    @objid ("d8c4f977-55b6-11e2-877f-002564c97630")
    public GmCombinedFragment(final IGmDiagram diagram, final CombinedFragment combinedFragment, final MRef relatedRef) {
        super(diagram, relatedRef);
        
        GmCombinedFragmentPrimaryNode mainNode = new GmCombinedFragmentPrimaryNode(diagram, relatedRef);
        mainNode.setRoleInComposition(MAIN_NODE_ROLE);
        addChild(mainNode);
        
        this.combinedFragment = combinedFragment;
    }

    @objid ("d8c4f986-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(final Class<? extends MObject> type) {
        return false; // Gate.class.isAssignableFrom(type);
    }

    @objid ("d8c4f98f-55b6-11e2-877f-002564c97630")
    @Override
    public CombinedFragment getRelatedElement() {
        return this.combinedFragment;
    }

    @objid ("d8c4f996-55b6-11e2-877f-002564c97630")
    @Override
    public CombinedFragment getRepresentedElement() {
        return this.combinedFragment;
    }

    @objid ("d8c4f99d-55b6-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(final MetaKey metakey) {
        return KEYS.getStyleKey(metakey);
    }

    @objid ("d8c67ffc-55b6-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        return KEYS.getStyleKeys();
    }

    @objid ("d8c68005-55b6-11e2-877f-002564c97630")
    @Override
    public void read(final IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmCombinedFragment.");
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

    @objid ("d8c6800c-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmCombinedFragment.", GmCombinedFragment.MINOR_VERSION);
    }

    @objid ("d8c68012-55b6-11e2-877f-002564c97630")
    private void read_0(final IDiagramReader in) {
        super.read(in);
        this.combinedFragment = (CombinedFragment) resolveRef(getRepresentedRef());
    }

    @objid ("d8c68018-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    /**
     * Is this node a Port, which position is defined relatively to the Main Node's bounds.
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Port.
     */
    @objid ("d8c68025-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isPort(final GmNodeModel childNode) {
        return GmPortContainer.PORT_ROLE.equals(childNode.getRoleInComposition());
    }

    /**
     * Is this node a Satellite, which position is defined relatively to the Main Node's bounds.
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Satellite.
     */
    @objid ("d8c6802f-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSatellite(final GmNodeModel childNode) {
        return GmPortContainer.SATELLITE_ROLE.equals(childNode.getRoleInComposition());
    }

    @objid ("60cc1d55-811f-4246-b76c-a54844719242")
    @Override
    public boolean isMainSatelliteLabel(GmNodeModel childNode) {
        return false;
    }

}
