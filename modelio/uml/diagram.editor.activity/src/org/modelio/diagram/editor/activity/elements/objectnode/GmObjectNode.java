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

package org.modelio.diagram.editor.activity.elements.objectnode;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.PositionConstants;
import org.modelio.diagram.editor.activity.elements.objectnode.v0._GmObjectNode;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.common.portcontainer.GmPortContainer;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectNode;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * This class represents the Gm of a ObjectNode.
 */
@objid ("2acf485a-55b6-11e2-877f-002564c97630")
public class GmObjectNode extends GmPortContainer {
    @objid ("2acf485e-55b6-11e2-877f-002564c97630")
    private ObjectNode element;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("2acf4864-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("2acf4867-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 1;

    @objid ("2acf4869-55b6-11e2-877f-002564c97630")
    private static final String IMAGE_MODE_HEADER = "image mode header";

    @objid ("2dbae93a-58a2-11e2-9574-002564c97630")
     static GmObjectNodeSimpleStyleKeys SIMPLEKEYS = new GmObjectNodeSimpleStyleKeys();

    @objid ("2dbae93b-58a2-11e2-9574-002564c97630")
    private static GmObjectNodeStructuredStyleKeys STRUCTKEYS = new GmObjectNodeStructuredStyleKeys();

    @objid ("2dbae93c-58a2-11e2-9574-002564c97630")
    private static GmObjectNodeImageStyleKeys IMAGEKEYS = new GmObjectNodeImageStyleKeys();

    @objid ("7c923377-6423-4eae-982f-370dd794748e")
    private static GmObjectNodeUserImageStyleKeys USERIMAGE_KEYS = new GmObjectNodeUserImageStyleKeys();

    /**
     * Default constructor.
     * @param diagram the diagram in which this gm is unmasked.
     * @param theObjectNode the represented object node, may be null.
     * @param ref a reference to the represented object node.
     */
    @objid ("2acf486b-55b6-11e2-877f-002564c97630")
    public GmObjectNode(IGmDiagram diagram, ObjectNode theObjectNode, MRef ref) {
        super(diagram, ref);
        this.element = theObjectNode;
        
        GmObjectNodePrimaryNode primary = new GmObjectNodePrimaryNode(diagram, ref);
        primary.setRoleInComposition(GmPortContainer.MAIN_NODE_ROLE);
        addChild(primary);
        
        GmDefaultModelElementLabel imageModeHeader = new GmDefaultModelElementLabel(diagram, ref);
        imageModeHeader.setRoleInComposition(IMAGE_MODE_HEADER);
        imageModeHeader.setLayoutData(PositionConstants.SOUTH);
        addChild(imageModeHeader);
    }

    /**
     * Empty constructor, needed for serialization.
     */
    @objid ("2ad0cefc-55b6-11e2-877f-002564c97630")
    public GmObjectNode() {
        // empty constructor for the serialization
    }

    @objid ("2ad0ceff-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return false;
    }

    @objid ("2ad0cf07-55b6-11e2-877f-002564c97630")
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
        
        ret = USERIMAGE_KEYS.getStyleKey(metakey);
        if (ret != null) {
            return ret;
        }
        
        ret = IMAGEKEYS.getStyleKey(metakey);
        return ret;
    }

    @objid ("2ad0cf11-55b6-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        if (getMainNode() != null) {
            switch (getMainNode().getRepresentationMode()) {
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

    @objid ("2ad0cf19-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmObjectNode.");
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

    @objid ("2ad0cf1f-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRepresentedElement() {
        return this.element;
    }

    @objid ("2ad0cf26-55b6-11e2-877f-002564c97630")
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

    @objid ("2ad0cf2f-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return this.element;
    }

    @objid ("2ad0cf36-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmObjectNode.", GmObjectNode.MINOR_VERSION);
    }

    @objid ("2ad0cf3c-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.element = (ObjectNode) resolveRef(getRepresentedRef());
    }

    @objid ("2ad25599-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    /**
     * Is this node a Satellite, which position is defined relatively to the Main Node's bounds.
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Satellite.
     */
    @objid ("2ad255a4-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSatellite(final GmNodeModel childNode) {
        return IMAGE_MODE_HEADER.equals(childNode.getRoleInComposition());
    }

    /**
     * Is this node a Port, which position is defined relatively to the Main Node's bounds.
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Port.
     */
    @objid ("2ad255ae-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isPort(final GmNodeModel childNode) {
        return false;
    }

    @objid ("2ad255b8-55b6-11e2-877f-002564c97630")
    @Override
    public void addStartingLink(final IGmLink link) {
        if (getMainNode() != null) {
            getMainNode().addStartingLink(link);
        } else {
            super.addStartingLink(link);
        }
    }

    @objid ("2ad255bf-55b6-11e2-877f-002564c97630")
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
    @objid ("2ad255c6-55b6-11e2-877f-002564c97630")
    GmObjectNode(final _GmObjectNode oldVersionGm) {
        super(oldVersionGm.getDiagram(), oldVersionGm.getRepresentedRef());
        this.element = (ObjectNode) oldVersionGm.getRelatedElement();
        
        GmObjectNodePrimaryNode primary = new GmObjectNodePrimaryNode(oldVersionGm);
        primary.setRoleInComposition(GmPortContainer.MAIN_NODE_ROLE);
        addChild(primary);
        
        GmDefaultModelElementLabel imageModeHeader = new GmDefaultModelElementLabel(oldVersionGm.getDiagram(), oldVersionGm.getRepresentedRef());
        imageModeHeader.setRoleInComposition(IMAGE_MODE_HEADER);
        imageModeHeader.setLayoutData(PositionConstants.SOUTH);
        addChild(imageModeHeader);
    }

    @objid ("54f3c7f7-c0ba-4b76-83a2-0ff2ea9dc961")
    @Override
    public boolean isMainSatelliteLabel(GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return role.equals(IMAGE_MODE_HEADER);
    }

}
