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

package org.modelio.uml.sequencediagram.editor.elements.combinedfragment.primarynode;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNoStyleCompositeNode;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.metamodel.uml.behavior.interactionModel.CombinedFragment;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionOperand;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Primary node for CombinedFragment.
 * 
 * @author fpoyer
 */
@objid ("d8cb13f5-55b6-11e2-877f-002564c97630")
public class GmCombinedFragmentPrimaryNode extends GmNoStyleCompositeNode {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("d8cb13f9-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("d8cb13fc-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Empty c'tor for deserialisation.
     */
    @objid ("d8cb13fe-55b6-11e2-877f-002564c97630")
    public GmCombinedFragmentPrimaryNode() {
        super();
    }

    /**
     * C'tor.
     * 
     * @param diagram diagram in which this gm is created.
     * @param relatedRef a ref to the represented CombinedFragment.
     */
    @objid ("d8cb1401-55b6-11e2-877f-002564c97630")
    public GmCombinedFragmentPrimaryNode(final IGmDiagram diagram, final MRef relatedRef) {
        super(diagram, relatedRef);
        GmOperatorLabel label = new GmOperatorLabel(diagram, relatedRef);
        this.addChild(label);
        GmInteractionOperandContainer container = new GmInteractionOperandContainer(diagram, relatedRef);
        this.addChild(container);
    }

    @objid ("d8cb140c-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(final Class<? extends MObject> type) {
        return false;
    }

    @objid ("d8cb1415-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(final MObject el) {
        return false;
    }

    @objid ("d8cc9a80-55b6-11e2-877f-002564c97630")
    @Override
    public GmCompositeNode getCompositeFor(final Class<? extends MObject> metaclass) {
        return (GmCompositeNode) (InteractionOperand.class.isAssignableFrom(metaclass) ? this.getChildren()
                        .get(1) : null);
    }

    @objid ("d8cc9a8b-55b6-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        super.refreshFromObModel();
        CombinedFragment combinedFragment = (CombinedFragment) getRelatedElement();
        if (combinedFragment != null && combinedFragment.isValid()) {
            firePropertyChange(IGmObject.PROPERTY_LAYOUTDATA, this.getLayoutData(), null);
        }
    }

    @objid ("d8cc9a8e-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmCombinedFragmentPrimaryNode.");
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

    @objid ("d8cc9a94-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmCombinedFragmentPrimaryNode.", GmCombinedFragmentPrimaryNode.MINOR_VERSION);
    }

    @objid ("d8cc9a9a-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("d8cc9a9f-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
