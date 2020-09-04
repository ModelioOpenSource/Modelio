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

package org.modelio.diagram.editor.bpmn.elements.bpmndataobject.datastore;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.PositionConstants;
import org.modelio.diagram.editor.bpmn.elements.bpmndataobject.GmBpmnDataImageStyleKeys;
import org.modelio.diagram.editor.bpmn.elements.bpmndataobject.GmBpmnDataLabel;
import org.modelio.diagram.editor.bpmn.elements.bpmndataobject.GmBpmnDataObjectStyleKeys;
import org.modelio.diagram.editor.bpmn.elements.bpmndataobject.GmBpmnDataSimpleStyleKeys;
import org.modelio.diagram.editor.bpmn.elements.bpmndataobject.GmBpmnDataUserImageStyleKeys;
import org.modelio.diagram.elements.common.portcontainer.GmPortContainer;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.bpmn.objects.BpmnDataStore;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Graphical model for a {@link Note}.
 */
@objid ("60ce384c-55b6-11e2-877f-002564c97630")
public class GmBpmnDataStore extends GmPortContainer {
    @objid ("60ce3850-55b6-11e2-877f-002564c97630")
     BpmnDataStore theElement;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("60ce3859-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("60ce385c-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("c4d07e2f-59a6-11e2-ae45-002564c97630")
    public static final GmBpmnDataObjectStyleKeys STRUCTURED_KEYS = new GmBpmnDataObjectStyleKeys();

    @objid ("c4d07e31-59a6-11e2-ae45-002564c97630")
    public static final GmBpmnDataImageStyleKeys IMAGE_KEYS = new GmBpmnDataImageStyleKeys();

    @objid ("c4d07e33-59a6-11e2-ae45-002564c97630")
    public static final GmBpmnDataSimpleStyleKeys SIMPLE_KEYS = new GmBpmnDataSimpleStyleKeys();

    @objid ("263ebb98-446a-48e8-bbea-db457d1208b3")
    public static final GmBpmnDataUserImageStyleKeys USERIMAGE_KEYS = new GmBpmnDataUserImageStyleKeys();

    /**
     * Constructor to use only for deserialization.
     */
    @objid ("60ce385e-55b6-11e2-877f-002564c97630")
    public GmBpmnDataStore() {
    }

    /**
     * Creates a GmNote.
     * 
     * @param diagram The diagram owning the node
     * @param theElement The represented note element
     * @param ref The represented note reference
     */
    @objid ("60ce3861-55b6-11e2-877f-002564c97630")
    public GmBpmnDataStore(IGmDiagram diagram, BpmnDataStore theElement, MRef ref) {
        super(diagram, ref);
        
        GmBpmnDataStorePrimaryNode mainNode = new GmBpmnDataStorePrimaryNode(diagram, ref);
        mainNode.setRoleInComposition(MAIN_NODE_ROLE);
        this.addChild(mainNode);
        
        this.theElement = theElement;
        GmBpmnDataLabel label = new GmBpmnDataLabel(diagram, ref);
        label.setRoleInComposition(GmPortContainer.SATELLITE_ROLE);
        label.setLayoutData(Integer.valueOf(PositionConstants.SOUTH));
        this.addChild(label);
    }

    @objid ("60ce386d-55b6-11e2-877f-002564c97630")
    @Override
    public BpmnDataStore getRepresentedElement() {
        return this.theElement;
    }

    @objid ("60ce3874-55b6-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        return RepresentationMode.STRUCTURED;
    }

    @objid ("60ce387b-55b6-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        switch (getMainNodeRepresentationMode()) {
        case IMAGE:
            return IMAGE_KEYS.getStyleKey(metakey);
        case USER_IMAGE:
            return USERIMAGE_KEYS.getStyleKey(metakey);
        case SIMPLE:
            return SIMPLE_KEYS.getStyleKey(metakey);
        case STRUCTURED:
            return STRUCTURED_KEYS.getStyleKey(metakey);
        default:
            return null;
        }
    }

    @objid ("60ce3883-55b6-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        switch (getMainNodeRepresentationMode()) {
        case IMAGE:
            return IMAGE_KEYS.getStyleKeys();
        case USER_IMAGE:
            return USERIMAGE_KEYS.getStyleKeys();
        case SIMPLE:
            return SIMPLE_KEYS.getStyleKeys();
        case STRUCTURED:
            return STRUCTURED_KEYS.getStyleKeys();
        default:
            return Collections.emptyList();
        }
    }

    @objid ("60cfbee0-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmBpmnDataStore.");
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

    @objid ("60cfbee6-55b6-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        if (this.theElement != null) {
            firePropertyChange(PROPERTY_LABEL, null, this.theElement.getName());
        }
    }

    @objid ("60cfbee9-55b6-11e2-877f-002564c97630")
    @Override
    public GmCompositeNode getCompositeFor(Class<? extends MObject> metaclass) {
        return this;
    }

    @objid ("60cfbef3-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return false;
    }

    @objid ("60cfbefb-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        return false;
    }

    @objid ("60cfbf03-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return this.theElement;
    }

    @objid ("60cfbf19-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmBpmnDataStore.", MINOR_VERSION);
    }

    @objid ("60d14579-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.theElement = (BpmnDataStore) resolveRef(this.getRepresentedRef());
    }

    @objid ("60d1457e-55b6-11e2-877f-002564c97630")
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
    @objid ("60d1458b-55b6-11e2-877f-002564c97630")
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
    @objid ("60d14595-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSatellite(final GmNodeModel childNode) {
        return GmPortContainer.SATELLITE_ROLE.equals(childNode.getRoleInComposition());
    }

    @objid ("bbc10ef0-80d0-42e0-a561-34c67a66ec2b")
    @Override
    public boolean isMainSatelliteLabel(GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return role.equals(SATELLITE_ROLE);
    }

}
