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

package org.modelio.diagram.editor.sequence.elements.combinedfragment.primarynode;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.resizablegroup.GmResizableGroup;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.metamodel.uml.behavior.interactionModel.CombinedFragment;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionOperand;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Container for InteractionOperands.
 * 
 * @author fpoyer
 */
@objid ("d8cc9aaf-55b6-11e2-877f-002564c97630")
public class GmInteractionOperandContainer extends GmResizableGroup {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("d8cc9ab3-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("d8cc9ab6-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Empty c'tor for deserialisation.
     */
    @objid ("d8cc9ab8-55b6-11e2-877f-002564c97630")
    public GmInteractionOperandContainer() {
        super();
    }

    /**
     * C'tor.
     * 
     * @param diagram diagram in which this gm is created.
     * @param relatedRef a ref to the represented CombinedFragment.
     */
    @objid ("d8cc9abb-55b6-11e2-877f-002564c97630")
    public GmInteractionOperandContainer(final IGmDiagram diagram, final MRef relatedRef) {
        super(diagram, relatedRef);
        super.setVertical(true);
    }

    @objid ("d8ce211d-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(final Class<? extends MObject> type) {
        return InteractionOperand.class.isAssignableFrom(type);
    }

    @objid ("d8ce2126-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(final MObject el) {
        return canCreate(el.getClass()) &&
                        ((InteractionOperand) el).getOwnerFragment().equals(getRelatedElement());
    }

    @objid ("d8ce212f-55b6-11e2-877f-002564c97630")
    @Override
    public GmCompositeNode getCompositeFor(final Class<? extends MObject> metaclass) {
        return InteractionOperand.class.isAssignableFrom(metaclass) ? this : null;
    }

    @objid ("d8ce213a-55b6-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        super.refreshFromObModel();
        // Read start line in ob model, refresh placement constraint accordingly
        CombinedFragment combinedFragment = (CombinedFragment) getRelatedElement();
        if (combinedFragment != null && combinedFragment.isValid()) {
            // Auto-unmask all InteractionOperands and reorder to follow the ob model.
            for (InteractionOperand operand : combinedFragment.getOperand()) {
                GmNodeModel childOperand = getChild(new MRef(operand));
                if (childOperand == null) {
                    getDiagram().unmask(this, operand, null);
                } else {
                    this.moveChild(childOperand, -1);
                }
            }
        }
    }

    @objid ("d8ce213d-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmInteractionOperandContainer.");
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

    @objid ("d8ce2143-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmInteractionOperandContainer.", GmInteractionOperandContainer.MINOR_VERSION);
    }

    @objid ("d8ce2149-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("d8ce214e-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
