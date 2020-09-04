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

package org.modelio.diagram.editor.sequence.elements.interactionuse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.sequence.elements.interactionuse.primarynode.GmInteractionUsePrimaryNode;
import org.modelio.diagram.elements.common.portcontainer.GmPortContainer;
import org.modelio.diagram.elements.common.portcontainer.PortConstraint.Border;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.behavior.interactionModel.Gate;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionFragment;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionUse;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Gm for InteractionUse. Specialisation of GmPortContainer.
 * 
 * @author fpoyer
 */
@objid ("d91bf327-55b6-11e2-877f-002564c97630")
public class GmInteractionUse extends GmPortContainer {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("d91bf330-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("d91bf333-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("d91bf32b-55b6-11e2-877f-002564c97630")
    private static final GmInteractionUseStyleKeys KEYS = new GmInteractionUseStyleKeys();

    @objid ("778ca479-589c-4439-aa76-c2086322c75a")
    private InteractionUse interactionUse;

    /**
     * Empty c'tor for deserialisation.
     */
    @objid ("d91bf335-55b6-11e2-877f-002564c97630")
    public GmInteractionUse() {
        super();
    }

    /**
     * C'tor.
     * 
     * @param diagram diagram in which this gm is created.
     * @param interactionUse the represented interactionuse
     * @param relatedRef a reference to the represented element.
     */
    @objid ("d91d799a-55b6-11e2-877f-002564c97630")
    public GmInteractionUse(final IGmDiagram diagram, final InteractionUse interactionUse, final MRef relatedRef) {
        super(diagram, relatedRef);
        
        GmInteractionUsePrimaryNode mainNode = new GmInteractionUsePrimaryNode(diagram, relatedRef);
        mainNode.setRoleInComposition(MAIN_NODE_ROLE);
        addChild(mainNode);
        
        this.interactionUse = interactionUse;
    }

    @objid ("d91d79a9-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(final Class<? extends MObject> type) {
        return Gate.class.isAssignableFrom(type);
    }

    @objid ("d91d79b2-55b6-11e2-877f-002564c97630")
    @Override
    public InteractionUse getRelatedElement() {
        return this.interactionUse;
    }

    @objid ("d91d79b9-55b6-11e2-877f-002564c97630")
    @Override
    public InteractionUse getRepresentedElement() {
        return this.interactionUse;
    }

    @objid ("d91d79c0-55b6-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(final MetaKey metakey) {
        return KEYS.getStyleKey(metakey);
    }

    @objid ("d91d79cb-55b6-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        return KEYS.getStyleKeys();
    }

    @objid ("d91d79d4-55b6-11e2-877f-002564c97630")
    @Override
    public void read(final IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmInteractionUse.");
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

    @objid ("d91d79db-55b6-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        // Eliminate children that no longer belong here.
        super.refreshFromObModel();
        // Unmask Gates
        InteractionUse relatedMObject = getRelatedElement();
        if (relatedMObject != null && relatedMObject.isValid()) {
            boolean forceUpdate = false;
            int index = 1;
            // Unmask and order all the ExecutionSpecification (nesting is only a graphical effect ;))
            // Get all execution specification on given lifeline and sort them by start line number.
            List<Gate> gates = new ArrayList<>(this.getRelatedElement().getActualGate().size());
            gates.addAll(this.getRelatedElement().getActualGate());
            Collections.sort(gates, new Comparator<Gate>() {
                @Override
                public int compare(Gate o1, Gate o2) {
                    if (o1.getLineNumber() < o2.getLineNumber()) {
                        return -1;
                    } else if (o1.getLineNumber() == o2.getLineNumber()) {
                        return 0;
                    } else {// if (o1.getLineNumber() > o2.getLineNumber()) {
                        return 1;
                    }
                }
            });
            // Now unmask if needed and check order.
            for (Gate gate : gates) {
                forceUpdate = forceUpdate || unmaskAndOrderChild(gate, index);
                ++index;
            }
        
            if (forceUpdate) {
                getDiagram().refreshAllFromObModel();
            }
        }
    }

    @objid ("d91f003b-55b6-11e2-877f-002564c97630")
    private boolean unmaskAndOrderChild(final InteractionFragment fragment, final int index) {
        boolean updateNeeded = false;
        GmNodeModel child = getChild(new MRef(fragment));
        if (child == null) {
            child = getDiagram().unmask(this, fragment, Border.West);
            updateNeeded = true;
        }
        if (child != null && getChildren().indexOf(child) != index) {
            this.moveChild(child, index);
            updateNeeded = true;
        }
        return updateNeeded;
    }

    @objid ("d91f0045-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmInteractionUse.", GmInteractionUse.MINOR_VERSION);
    }

    @objid ("d91f004b-55b6-11e2-877f-002564c97630")
    private void read_0(final IDiagramReader in) {
        super.read(in);
        this.interactionUse = (InteractionUse) resolveRef(getRepresentedRef());
    }

    @objid ("d91f0051-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    /**
     * Is this node a Port, which position is defined relatively to the Main Node's bounds.
     * 
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Port.
     */
    @objid ("d91f005e-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isPort(final GmNodeModel childNode) {
        return GmPortContainer.PORT_ROLE.equals(childNode.getRoleInComposition());
    }

    /**
     * Is this node a Satellite, which position is defined relatively to the Main Node's bounds.
     * 
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Satellite.
     */
    @objid ("d91f0068-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSatellite(final GmNodeModel childNode) {
        return GmPortContainer.SATELLITE_ROLE.equals(childNode.getRoleInComposition());
    }

    @objid ("36f87c20-f02f-456d-b0b8-1f3916dc5e29")
    @Override
    public boolean isMainSatelliteLabel(GmNodeModel childNode) {
        return false;
    }

}
