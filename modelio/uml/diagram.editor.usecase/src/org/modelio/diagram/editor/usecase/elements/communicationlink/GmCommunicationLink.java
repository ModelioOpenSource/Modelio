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

package org.modelio.diagram.editor.usecase.elements.communicationlink;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.elements.association.GmAssocStructuredStyleKeys;
import org.modelio.diagram.editor.statik.elements.association.GmAssociation;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.vcore.smkernel.mapi.MRef;

@objid ("5e459c1a-55b7-11e2-877f-002564c97630")
public class GmCommunicationLink extends GmAssociation {
    @objid ("5e459c1c-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("5e459c1f-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("5e459c21-55b7-11e2-877f-002564c97630")
    public GmCommunicationLink() {
        super();
    }

    @objid ("5e459c24-55b7-11e2-877f-002564c97630")
    public GmCommunicationLink(IGmDiagram diagram, AssociationEnd role, MRef roleRef, MRef associationRef) {
        super(diagram, role, roleRef, associationRef);
        
        ArrayList<GmNodeModel> extensions = new ArrayList<>(getExtensions());
        for (GmNodeModel extension : extensions) {
            removeExtension(extension);
        }
        
        getDisplayedStyle().setProperty(GmAssocStructuredStyleKeys.SHOWNAVIGABILITY, false);
    }

    @objid ("5e459c33-55b7-11e2-877f-002564c97630")
    @Override
    protected void readLink(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmCommunicationLink.");
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

    @objid ("5e459c39-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmCommunicationLink.", GmCommunicationLink.MINOR_VERSION);
    }

    @objid ("5e459c3f-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
