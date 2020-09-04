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

package org.modelio.diagram.editor.statik.elements.connector;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.elements.instancelink.GmInstanceLink;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.statik.ConnectorEnd;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents a Connector.
 */
@objid ("34b3d347-55b7-11e2-877f-002564c97630")
public class GmConnectorLink extends GmInstanceLink {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("34b3d34b-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("34b3d34e-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("34b3d349-55b7-11e2-877f-002564c97630")
    private static final ConnectorLinkStructuredStyleKeys KEYS = new ConnectorLinkStructuredStyleKeys();

    /**
     * Constructor for deserialization.
     */
    @objid ("34b3d350-55b7-11e2-877f-002564c97630")
    public GmConnectorLink() {
        // Nothing to do.
    }

    /**
     * Creates a GmConnectorLink.
     * 
     * @param diagram The diagram containing the connector.
     * @param role The represented connector role, may be <code>null</code>.
     * @param roleRef The represented connector role reference. Must not be <code>null</code>.
     * @param connectorRef The represented connector reference. Must not be <code>null</code>.
     */
    @objid ("34b3d353-55b7-11e2-877f-002564c97630")
    public GmConnectorLink(IGmDiagram diagram, ConnectorEnd role, MRef roleRef, final MRef connectorRef) {
        super(diagram, role, roleRef, connectorRef);
    }

    @objid ("34b3d363-55b7-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        return KEYS.getStyleKey(metakey);
    }

    @objid ("34b3d36d-55b7-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        return KEYS.getStyleKeys();
    }

    @objid ("34b3d376-55b7-11e2-877f-002564c97630")
    @Override
    protected void readLink(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmConnectorLink.");
        switch (readVersion) {
            default: {
                assert (false) : "version number not covered!";
            }
            // Controlled fall through, reading as last known version:
            //$FALL-THROUGH$
            case 0: {
                super.readLink(in);
                break;
            }
        }
    }

    @objid ("34b559dd-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmConnectorLink.", GmConnectorLink.MINOR_VERSION);
    }

    @objid ("34b559e3-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
