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

package org.modelio.diagram.editor.sequence.elements.interactionuse.primarynode;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.header.GmDefaultModelElementHeader;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionUse;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Displays the represented element name as main label.
 * <p>
 * Has no own style, depends on its parent element.
 */
@objid ("d9251ac3-55b6-11e2-877f-002564c97630")
public class GmInteractionUseHeader extends GmDefaultModelElementHeader {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("d9251ac7-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("d9251aca-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * For deserialization only.
     */
    @objid ("d9251acc-55b6-11e2-877f-002564c97630")
    public GmInteractionUseHeader() {
        // Empty c'tor for deserialization only.
    }

    /**
     * Initializes a model element header.
     * @param diagram the owning diagram.
     * @param relatedRef the element reference.
     */
    @objid ("d9251acf-55b6-11e2-877f-002564c97630")
    public GmInteractionUseHeader(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
        this.setStackedStereotypes(true);
    }

    @objid ("d9251ad8-55b6-11e2-877f-002564c97630")
    @Override
    protected String computeMainLabel() {
        InteractionUse interactionUse = (InteractionUse) getRelatedElement();
        if (interactionUse != null && interactionUse.isValid() && interactionUse.getRefersTo() != null) {
            return interactionUse.getRefersTo().getName();
        } else {
            return super.computeMainLabel();
        }
    }

    @objid ("d9251adc-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmInteractionUseHeader.");
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

    @objid ("d9251ae2-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmInteractionUseHeader.", GmInteractionUseHeader.MINOR_VERSION);
    }

    @objid ("d9251ae8-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("d9251aed-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
