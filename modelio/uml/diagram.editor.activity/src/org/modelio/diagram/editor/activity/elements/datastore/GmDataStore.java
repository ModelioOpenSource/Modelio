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

package org.modelio.diagram.editor.activity.elements.datastore;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.PositionConstants;
import org.modelio.diagram.editor.activity.elements.datastore.v0._GmDataStore;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.common.portcontainer.GmPortContainer;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.behavior.activityModel.DataStoreNode;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * This class represents the Gm of a DataStore.
 */
@objid ("2a25e910-55b6-11e2-877f-002564c97630")
public class GmDataStore extends GmPortContainer {
    @objid ("2a25e914-55b6-11e2-877f-002564c97630")
    private DataStoreNode element;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("2a25e91a-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("2a25e91d-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 1;

    @objid ("2a25e91f-55b6-11e2-877f-002564c97630")
    private static final String IMAGE_MODE_HEADER = "image mode header";

    @objid ("305c93e2-58a2-11e2-9574-002564c97630")
     static GmDataStoreSimpleStyleKeys SIMPLEKEYS = new GmDataStoreSimpleStyleKeys();

    @objid ("305c93e3-58a2-11e2-9574-002564c97630")
     static GmDataStoreStructuredStyleKeys STRUCTKEYS = new GmDataStoreStructuredStyleKeys();

    @objid ("305c93e4-58a2-11e2-9574-002564c97630")
     static GmDataStoreImageStyleKeys IMAGEKEYS = new GmDataStoreImageStyleKeys();

    @objid ("3c1c799a-d2f4-40f8-85c9-eb5c9dd12571")
     static GmDataStoreUserImageStyleKeys USERIMAGE_KEYS = new GmDataStoreUserImageStyleKeys();

    /**
     * Default constructor.
     * @param diagram the diagram in which this gm is unmasked.
     * @param theDataStore the represented data store, may be null.
     * @param ref a reference to the represented data store.
     */
    @objid ("2a25e921-55b6-11e2-877f-002564c97630")
    public GmDataStore(IGmDiagram diagram, DataStoreNode theDataStore, MRef ref) {
        super(diagram, ref);
        this.element = theDataStore;
        
        GmDataStorePrimaryNode primary = new GmDataStorePrimaryNode(diagram, ref);
        primary.setRoleInComposition(GmPortContainer.MAIN_NODE_ROLE);
        addChild(primary);
        GmDefaultModelElementLabel imageModeHeader = new GmDefaultModelElementLabel(diagram, ref);
        imageModeHeader.setRoleInComposition(IMAGE_MODE_HEADER);
        imageModeHeader.setLayoutData(PositionConstants.SOUTH);
        addChild(imageModeHeader);
    }

    /**
     * Empty constructor, needed for serialisation.
     */
    @objid ("2a25e92d-55b6-11e2-877f-002564c97630")
    public GmDataStore() {
        // empty constructor for the serialization
    }

    @objid ("2a25e930-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return false;
    }

    @objid ("2a276f9a-55b6-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        StyleKey ret = STRUCTKEYS.getStyleKey(metakey);
        if (ret != null) {
            return ret;
        }
        
        ret = SIMPLEKEYS.getStyleKey(metakey);
        if (ret != null) {
            return ret;
        }
        
        ret = IMAGEKEYS.getStyleKey(metakey);
        return ret;
    }

    @objid ("2a276fa4-55b6-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        if (getMainNode() != null) {
            RepresentationMode mode = getMainNode().getRepresentationMode();
            switch (mode) {
            case SIMPLE:
                return SIMPLEKEYS.getStyleKeys();
            case STRUCTURED:
                return STRUCTKEYS.getStyleKeys();
            case IMAGE:
                return IMAGEKEYS.getStyleKeys();
            case USER_IMAGE:
                return USERIMAGE_KEYS.getStyleKeys();
            default:
                return Collections.emptyList();
            }
        } else {
            return Collections.emptyList();
        }
    }

    @objid ("2a276fac-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmDataStore.");
        switch (readVersion) {
        case 0: {
            read_0(in);
            break;
        }
        default: {
            assert (false) : "version number not covered!";
            // reading as last handled version: 1
            read_0(in);
            break;
        }
        }
    }

    @objid ("2a276fb2-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRepresentedElement() {
        return this.element;
    }

    @objid ("2a276fb9-55b6-11e2-877f-002564c97630")
    @Override
    public List<GmNodeModel> getVisibleChildren() {
        // Returned result depends on current representation mode:
        List<GmNodeModel> ret = super.getVisibleChildren();
        if (getMainNode() != null) {
            switch (getMainNode().getRepresentationMode()) {
            case STRUCTURED:
            case SIMPLE:
                GmNodeModel imageModeHeader = getFirstChild(IMAGE_MODE_HEADER);
                ret.remove(imageModeHeader);
                break;
            case IMAGE:
            case USER_IMAGE:
            default:
                break;
        
            }
        }
        return ret;
    }

    @objid ("2a276fc2-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return this.element;
    }

    @objid ("2a276fc9-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmDataStore.", GmDataStore.MINOR_VERSION);
    }

    @objid ("2a276fcf-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.element = (DataStoreNode) resolveRef(getRepresentedRef());
    }

    @objid ("2a276fd4-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    /**
     * Is this node a Satellite, which position is defined relatively to the Main Node's bounds.
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Satellite.
     */
    @objid ("2a28f63a-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSatellite(final GmNodeModel childNode) {
        return IMAGE_MODE_HEADER.equals(childNode.getRoleInComposition());
    }

    /**
     * Is this node a Port, which position is defined relatively to the Main Node's bounds.
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Port.
     */
    @objid ("2a28f644-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isPort(final GmNodeModel childNode) {
        return false;
    }

    @objid ("2a28f64e-55b6-11e2-877f-002564c97630")
    @Override
    public void addStartingLink(final IGmLink link) {
        if (getMainNode() != null) {
            getMainNode().addStartingLink(link);
        } else {
            super.addStartingLink(link);
        }
    }

    @objid ("2a28f655-55b6-11e2-877f-002564c97630")
    @Override
    public void addEndingLink(final IGmLink link) {
        if (getMainNode() != null) {
            getMainNode().addEndingLink(link);
        } else {
            super.addEndingLink(link);
        }
    }

    /**
     * Migration constructor from major version 0, should only be called by migrator.
     * @param oldVersionGm the instance to migrate from.
     */
    @objid ("2a28f65c-55b6-11e2-877f-002564c97630")
    GmDataStore(final _GmDataStore oldVersionGm) {
        super(oldVersionGm.getDiagram(), oldVersionGm.getRepresentedRef());
        this.element = (DataStoreNode) oldVersionGm.getRelatedElement();
        
        GmDataStorePrimaryNode primary = new GmDataStorePrimaryNode(oldVersionGm);
        primary.setRoleInComposition(GmPortContainer.MAIN_NODE_ROLE);
        addChild(primary);
        
        GmDefaultModelElementLabel imageModeHeader = new GmDefaultModelElementLabel(oldVersionGm.getDiagram(), oldVersionGm.getRepresentedRef());
        imageModeHeader.setRoleInComposition(IMAGE_MODE_HEADER);
        imageModeHeader.setLayoutData(PositionConstants.SOUTH);
        addChild(imageModeHeader);
    }

    @objid ("3a282ce3-a77e-4ed8-bf6a-38e04352200e")
    @Override
    public boolean isMainSatelliteLabel(GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return role.equals(IMAGE_MODE_HEADER);
    }

}
