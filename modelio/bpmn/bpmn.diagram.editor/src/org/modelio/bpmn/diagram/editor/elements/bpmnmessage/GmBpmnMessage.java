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

package org.modelio.bpmn.diagram.editor.elements.bpmnmessage;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.PositionConstants;
import org.modelio.diagram.elements.common.portcontainer.GmPortContainer;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.bpmn.flows.BpmnMessage;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Graphical model for a {@link BpmnMessage}.
 */
@objid ("615c206a-55b6-11e2-877f-002564c97630")
public class GmBpmnMessage extends GmPortContainer {
    @objid ("615c206e-55b6-11e2-877f-002564c97630")
    private BpmnMessage message;

    /**
     * Current version of this Gm.
     */
    @objid ("615c2077-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 1;

    @objid ("615c207a-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("615c2071-55b6-11e2-877f-002564c97630")
     static final GmBpmnMessageStructuredStyleKeys STRUCTURED_KEYS = new GmBpmnMessageStructuredStyleKeys();

    @objid ("0870d5e2-dd82-4d7e-accf-fe36aa691514")
     static final GmBpmnMessageSimpleStyleKeys SIMPLE_KEYS = new GmBpmnMessageSimpleStyleKeys();

    @objid ("7af07589-763a-4a31-b321-8cc165859f4a")
     static final GmBpmnMessageImageStyleKeys IMAGE_KEYS = new GmBpmnMessageImageStyleKeys();

    @objid ("4bf86940-d2b4-4b62-9ab9-5eb4f7bc0235")
     static final GmBpmnMessageUserImageStyleKeys USERIMAGE_KEYS = new GmBpmnMessageUserImageStyleKeys();

    /**
     * Constructor to use only for deserialization.
     */
    @objid ("615c207c-55b6-11e2-877f-002564c97630")
    public GmBpmnMessage() {
    }

    @objid ("615c207f-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return false;
    }

    @objid ("615c2087-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        return false;
    }

    @objid ("615c208f-55b6-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        switch (getRepresentationMode()) {
        case IMAGE:
            return GmBpmnMessage.IMAGE_KEYS.getStyleKey(metakey);
        case USER_IMAGE:
            return GmBpmnMessage.USERIMAGE_KEYS.getStyleKey(metakey);
        case SIMPLE:
            return GmBpmnMessage.SIMPLE_KEYS.getStyleKey(metakey);
        case STRUCTURED:
            return GmBpmnMessage.STRUCTURED_KEYS.getStyleKey(metakey);
        default:
            return null;
        }
    }

    @objid ("615c2097-55b6-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        switch (getRepresentationMode()) {
        case IMAGE:
            return GmBpmnMessage.IMAGE_KEYS.getStyleKeys();
        case USER_IMAGE:
            return GmBpmnMessage.USERIMAGE_KEYS.getStyleKeys();
        case SIMPLE:
            return GmBpmnMessage.SIMPLE_KEYS.getStyleKeys();
        case STRUCTURED:
            return GmBpmnMessage.STRUCTURED_KEYS.getStyleKeys();
        default:
            return Collections.emptyList();
        }
    }

    /**
     * Creates a GmBpmnMessage.
     * 
     * @param diagram The diagram owning the node
     * @param message The represented note element
     * @param ref The represented note reference
     */
    @objid ("615c209f-55b6-11e2-877f-002564c97630")
    public GmBpmnMessage(final IGmDiagram diagram, final BpmnMessage message, final MRef ref) {
        super(diagram, ref);
        
        GmBpmnMessagePrimaryNode mainNode = new GmBpmnMessagePrimaryNode(diagram, ref);
        mainNode.setRoleInComposition(GmPortContainer.MAIN_NODE_ROLE);
        this.addChild(mainNode);
        
        this.message = message;
        GmBpmnMessageLabel label = new GmBpmnMessageLabel(diagram, ref);
        label.setRoleInComposition(GmPortContainer.SATELLITE_ROLE);
        label.setLayoutData(Integer.valueOf(PositionConstants.SOUTH));
        this.addChild(label);
    }

    @objid ("615da704-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmBpmnMessage.");
        switch (readVersion) {
        case 0: {
            read_0(in);
            break;
        }
        case 1: {
            read_1(in);
            break;
        }
        default: {
            assert (false) : "version number not covered!";
            // reading as last handled version: 1
            read_1(in);
            break;
        }
        }
    }

    @objid ("615da70a-55b6-11e2-877f-002564c97630")
    @Override
    public BpmnMessage getRepresentedElement() {
        return this.message;
    }

    @objid ("615da711-55b6-11e2-877f-002564c97630")
    @Override
    public BpmnMessage getRelatedElement() {
        return getRepresentedElement();
    }

    @objid ("615da726-55b6-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        if (this.message != null) {
            firePropertyChange(IGmObject.PROPERTY_LABEL, null, this.message.getName());
        }
    }

    @objid ("615da728-55b6-11e2-877f-002564c97630")
    @Override
    public GmCompositeNode getCompositeFor(final Class<? extends MObject> metaclass) {
        return this;
    }

    @objid ("615f2d9f-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmBpmnMessage.", GmBpmnMessage.MINOR_VERSION);
    }

    @objid ("615f2da5-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.message = (BpmnMessage) resolveRef(this.getRepresentedRef());
        
        for (StyleKey oldStyleKey : getPersistedStyle().getLocalKeys().toArray(new StyleKey[0])) {
            String newId = oldStyleKey.getId().replace("DATA_", "BPMNMESSAGE_");
            StyleKey newStyleKey = StyleKey.getInstance(newId);
            if (newStyleKey != null) {
                Object localValue = getPersistedStyle().getProperty(oldStyleKey);
                getPersistedStyle().setProperty(newStyleKey, localValue);
            }
            getPersistedStyle().removeProperty(oldStyleKey);
        }
    }

    @objid ("615f2daa-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmBpmnMessage.MAJOR_VERSION;
    }

    /**
     * Is this node a Port, which position is defined relatively to the Main Node's bounds.
     * 
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Port.
     */
    @objid ("615f2db7-55b6-11e2-877f-002564c97630")
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
    @objid ("615f2dc1-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSatellite(final GmNodeModel childNode) {
        return GmPortContainer.SATELLITE_ROLE.equals(childNode.getRoleInComposition());
    }

    @objid ("f1bce32f-2fce-4813-a6ac-9cfaa4a7ac13")
    @Override
    public boolean isMainSatelliteLabel(GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return role.equals(GmPortContainer.SATELLITE_ROLE);
    }

    @objid ("bd38799b-1bd3-4826-b7d6-f50a3bf8b21e")
    private void read_1(IDiagramReader in) {
        super.read(in);
        this.message = (BpmnMessage) resolveRef(this.getRepresentedRef());
    }

}
