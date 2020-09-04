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

package org.modelio.diagram.editor.sequence.elements.interactionuse.primarynode;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.header.GmDefaultModelElementHeader;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNoStyleCompositeNode;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionUse;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Primary node for InteractionUse.
 * 
 * @author fpoyer
 */
@objid ("d9251af9-55b6-11e2-877f-002564c97630")
public class GmInteractionUsePrimaryNode extends GmNoStyleCompositeNode {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("d926a15a-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("d926a15d-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Empty c'tor for deserialisation.
     */
    @objid ("d926a15f-55b6-11e2-877f-002564c97630")
    public GmInteractionUsePrimaryNode() {
        super();
    }

    /**
     * C'tor.
     * 
     * @param diagram diagram in which this gm is created.
     * @param relatedRef a ref to the represented interaction use.
     */
    @objid ("d926a162-55b6-11e2-877f-002564c97630")
    public GmInteractionUsePrimaryNode(final IGmDiagram diagram, final MRef relatedRef) {
        super(diagram, relatedRef);
        GmOperatorLabel operatorLabel = new GmOperatorLabel(diagram, relatedRef);
        this.addChild(operatorLabel);
        GmDefaultModelElementHeader header = new GmInteractionUseHeader(diagram, relatedRef);
        this.addChild(header);
    }

    @objid ("d926a16d-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(final Class<? extends MObject> type) {
        return false;
    }

    @objid ("d926a176-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(final MObject el) {
        return false;
    }

    @objid ("d926a17f-55b6-11e2-877f-002564c97630")
    @Override
    public GmCompositeNode getCompositeFor(final Class<? extends MObject> metaclass) {
        return null;
    }

    @objid ("d926a18a-55b6-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        super.refreshFromObModel();
        // Read start and end line in ob model, refresh placement constraint accordingly
        InteractionUse interactionUse = (InteractionUse) getRelatedElement();
        if (interactionUse != null && interactionUse.isValid()) {
            firePropertyChange(IGmObject.PROPERTY_LAYOUTDATA, this.getLayoutData(), null);
        }
    }

    @objid ("d926a18d-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmInteractionUsePrimaryNode.");
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

    @objid ("d926a193-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmInteractionUsePrimaryNode.", GmInteractionUsePrimaryNode.MINOR_VERSION);
    }

    @objid ("d926a199-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("d92827f9-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
