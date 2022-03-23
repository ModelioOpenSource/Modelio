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

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.statik.ConnectorEnd;
import org.modelio.metamodel.uml.statik.NaryConnectorEnd;
import org.modelio.uml.statikdiagram.editor.elements.narylink.GmNLinkEndLink;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents an {@link ConnectorEnd} branch of n-ary Connector.
 * <p>
 * The source is the association node.<br>
 * The target the {@link ConnectorEnd#getLinked()} class.
 * <p>
 * The link style is a proxy on the association node style.
 * 
 * @author cmarin
 */
@objid ("35d5be23-55b7-11e2-877f-002564c97630")
public class GmNConnectorEndLink extends GmNLinkEndLink {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("35d5be27-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("35d5be2a-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("6126b6e8-5bd5-11e2-9e33-00137282c51b")
    static final NConnectorStructuredStyleKeys STRUCTURED_KEYS = new NConnectorStructuredStyleKeys();

    /**
     * Constructor for deserialization only.
     */
    @objid ("35d5be2c-55b7-11e2-877f-002564c97630")
    public  GmNConnectorEndLink() {
        // Nothing to do.
    }

    /**
     * Creates a GmConnectorEndLink.
     * @param diagram The diagram
     * @param role The represented Connector role, may be <code>null</code>
     * @param roleRef The represented Connector role reference, must not be <code>null</code>
     */
    @objid ("35d5be2f-55b7-11e2-877f-002564c97630")
    public  GmNConnectorEndLink(IGmDiagram diagram, NaryConnectorEnd role, MRef roleRef) {
        super(diagram, role, roleRef);
    }

    @objid ("35d5be3b-55b7-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        return STRUCTURED_KEYS.getStyleKey(metakey);
    }

    @objid ("35d7449a-55b7-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        return STRUCTURED_KEYS.getStyleKeys();
    }

    @objid ("35d744a3-55b7-11e2-877f-002564c97630")
    @Override
    protected void readLink(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmNConnectorEndLink.");
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

    @objid ("35d744a9-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmNConnectorEndLink.", GmNConnectorEndLink.MINOR_VERSION);
        
    }

    @objid ("35d744af-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
