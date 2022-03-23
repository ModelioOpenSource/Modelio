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
package org.modelio.uml.statikdiagram.editor.elements.naryconnector;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.metamodel.uml.statik.NaryConnector;
import org.modelio.uml.statikdiagram.editor.elements.narylink.GmNLinkPrimaryNode;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Main class of the central node for a n-ary Connector.
 * 
 * @author cmarin
 */
@objid ("35da51fd-55b7-11e2-877f-002564c97630")
public class GmNConnectorPrimaryNode extends GmNLinkPrimaryNode {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("35da51ff-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("35da5202-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Empty c'tor for deserialization.
     */
    @objid ("35da5204-55b7-11e2-877f-002564c97630")
    public  GmNConnectorPrimaryNode() {
        super();
    }

    /**
     * C'tor.
     * @param diagram the diagram in which this gm is created.
     * @param assoc the represented n-ary association. May be null.
     * @param relatedRef a reference to the represented n-ary association. Must Not be null.
     */
    @objid ("35da5207-55b7-11e2-877f-002564c97630")
    public  GmNConnectorPrimaryNode(final IGmDiagram diagram, final NaryConnector assoc, final MRef relatedRef) {
        super(diagram, assoc, relatedRef);
    }

    @objid ("35dbd87b-55b7-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        return getDisplayedStyle().getProperty(GmNConnectorNode.KEYS.getStyleKey(MetaKey.REPMODE));
    }

    @objid ("35dbd882-55b7-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmNConnectorPrimaryNode.");
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

    @objid ("35dbd888-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmNConnectorPrimaryNode.", GmNConnectorPrimaryNode.MINOR_VERSION);
        
    }

    @objid ("35dbd88e-55b7-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("35dbd893-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
