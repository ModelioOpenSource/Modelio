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

package org.modelio.diagram.editor.sequence.elements.interactionoperand;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.sequence.elements.interactionoperand.primarynode.GmInteractionOperandPrimaryNode;
import org.modelio.diagram.elements.common.portcontainer.GmPortContainer;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.behavior.interactionModel.Gate;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionOperand;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Main Gm for InteractionOperand.
 * 
 * @author fpoyer
 */
@objid ("d9038905-55b6-11e2-877f-002564c97630")
public class GmInteractionOperand extends GmPortContainer {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("d903890e-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("d9038911-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("d9038909-55b6-11e2-877f-002564c97630")
    private static final GmInteractionOperandStyleKeys KEYS = new GmInteractionOperandStyleKeys();

    @objid ("19aac6f3-835a-47b5-8b0b-41a98d530931")
    private InteractionOperand interactionOperand;

    /**
     * Empty c'tor for deserialisation.
     */
    @objid ("d9038913-55b6-11e2-877f-002564c97630")
    public GmInteractionOperand() {
        super();
    }

    /**
     * C'tor.
     * @param diagram the diagram in which this gm is created.
     * @param interactionOperand represented interaction operand.
     * @param relatedRef a reference to the represented interaction operand.
     */
    @objid ("d9038916-55b6-11e2-877f-002564c97630")
    public GmInteractionOperand(final IGmDiagram diagram, final InteractionOperand interactionOperand, final MRef relatedRef) {
        super(diagram, relatedRef);
        GmInteractionOperandPrimaryNode mainNode = new GmInteractionOperandPrimaryNode(diagram, relatedRef);
        mainNode.setRoleInComposition(GmPortContainer.MAIN_NODE_ROLE);
        addChild(mainNode);
        
        this.interactionOperand = interactionOperand;
    }

    @objid ("d9038925-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(final Class<? extends MObject> type) {
        return false;
    }

    @objid ("d903892e-55b6-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(final MetaKey metakey) {
        return GmInteractionOperand.KEYS.getStyleKey(metakey);
    }

    @objid ("d9038939-55b6-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        return GmInteractionOperand.KEYS.getStyleKeys();
    }

    @objid ("d9050f9a-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(final MObject el) {
        // Allow the unmasking of Gate on the owning combined fragment.
        return el instanceof Gate &&
                        ((Gate) el).getOwnerFragment() != null &&
                        ((Gate) el).getOwnerFragment().equals(this.getRelatedElement().getOwnerFragment());
    }

    @objid ("d9050fa3-55b6-11e2-877f-002564c97630")
    @Override
    public InteractionOperand getRelatedElement() {
        return this.interactionOperand;
    }

    @objid ("d9050faa-55b6-11e2-877f-002564c97630")
    @Override
    public InteractionOperand getRepresentedElement() {
        return this.interactionOperand;
    }

    @objid ("d9050fb1-55b6-11e2-877f-002564c97630")
    @Override
    public void read(final IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmInteractionOperand.");
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

    @objid ("d9050fb8-55b6-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        super.refreshFromObModel();
        if (this.interactionOperand != null && this.interactionOperand.isValid()) {
            int height = this.interactionOperand.getEndLineNumber() - this.interactionOperand.getLineNumber();
            firePropertyChange(IGmObject.PROPERTY_LAYOUTDATA, super.getLayoutData(), Integer.valueOf(height));
        }
    }

    @objid ("d9050fbb-55b6-11e2-877f-002564c97630")
    @Override
    public Object getLayoutData() {
        if (this.interactionOperand != null && this.interactionOperand.isValid()) {
            return this.interactionOperand.getEndLineNumber() - this.interactionOperand.getLineNumber();
        } else {
            return super.getLayoutData();
        }
    }

    @objid ("d9050fbf-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmInteractionOperand.", GmInteractionOperand.MINOR_VERSION);
    }

    @objid ("d9050fc5-55b6-11e2-877f-002564c97630")
    private void read_0(final IDiagramReader in) {
        super.read(in);
        this.interactionOperand = (InteractionOperand) resolveRef(getRepresentedRef());
    }

    @objid ("d9050fcb-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmInteractionOperand.MAJOR_VERSION;
    }

    /**
     * Is this node a Port, which position is defined relatively to the Main Node's bounds.
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Port.
     */
    @objid ("d9050fd8-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isPort(final GmNodeModel childNode) {
        return GmPortContainer.PORT_ROLE.equals(childNode.getRoleInComposition());
    }

    /**
     * Is this node a Satellite, which position is defined relatively to the Main Node's bounds.
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Satellite.
     */
    @objid ("d9069641-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSatellite(final GmNodeModel childNode) {
        return GmPortContainer.SATELLITE_ROLE.equals(childNode.getRoleInComposition());
    }

    @objid ("f7ece193-f8d0-462b-b947-f705fff9a78a")
    @Override
    public boolean isMainSatelliteLabel(GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return role.equals(GmPortContainer.SATELLITE_ROLE);
    }

}
