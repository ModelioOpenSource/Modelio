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

package org.modelio.diagram.editor.sequence.elements.interactionoperand.primarynode;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNoStyleCompositeNode;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.metamodel.uml.behavior.interactionModel.CombinedFragment;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionUse;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Primary node of InteractionOperand.
 * 
 * @author fpoyer
 */
@objid ("d909a3a0-55b6-11e2-877f-002564c97630")
public class GmInteractionOperandPrimaryNode extends GmNoStyleCompositeNode {
    /**
     * This property name is used to indicate to the edit part that a refresh from model indicated that a resize is needed.
     */
    @objid ("d909a3a4-55b6-11e2-877f-002564c97630")
     static final String PROPERTY_MODEL_UPDATE = "ModelUpdate";

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("d909a3a7-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("d909a3aa-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Empty c'tor for deserialisation.
     */
    @objid ("d909a3ac-55b6-11e2-877f-002564c97630")
    public GmInteractionOperandPrimaryNode() {
        super();
    }

    /**
     * C'tor.
     * @param diagram the diagram in which this Gm is created.
     * @param relatedRef a reference to the represented element.
     */
    @objid ("d909a3af-55b6-11e2-877f-002564c97630")
    public GmInteractionOperandPrimaryNode(final IGmDiagram diagram, final MRef relatedRef) {
        super(diagram, relatedRef);
        GmGuardLabel label = new GmGuardLabel(diagram, relatedRef);
        this.addChild(label);
    }

    @objid ("d909a3ba-55b6-11e2-877f-002564c97630")
    @Override
    public GmCompositeNode getCompositeFor(final Class<? extends MObject> metaclass) {
        if (CombinedFragment.class.isAssignableFrom(metaclass) ||
                InteractionUse.class.isAssignableFrom(metaclass)) {
            return this;
        } else {
            return null;
        }
    }

    @objid ("d90b2a20-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(final Class<? extends MObject> type) {
        return CombinedFragment.class.isAssignableFrom(type) || InteractionUse.class.isAssignableFrom(type);
    }

    @objid ("d90b2a29-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(final MObject el) {
        return canCreate(el.getClass()) && el.getCompositionOwner().equals(this.getRelatedElement());
    }

    @objid ("d90b2a32-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmInteractionOperandPrimaryNode.");
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

    @objid ("d90b2a38-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmInteractionOperandPrimaryNode.", GmInteractionOperandPrimaryNode.MINOR_VERSION);
    }

    @objid ("d90b2a3e-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("d90b2a43-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
