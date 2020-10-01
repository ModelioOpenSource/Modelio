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

package org.modelio.uml.statikdiagram.editor.elements.collab;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.PositionConstants;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.common.portcontainer.GmPortContainer;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.uml.statikdiagram.editor.elements.collab.v0._GmCollaboration;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * This class represents the Gm of a {@link Collaboration}.
 */
@objid ("3456bf4c-55b7-11e2-877f-002564c97630")
public class GmCollaboration extends GmPortContainer {
    @objid ("3456bf50-55b7-11e2-877f-002564c97630")
    private Collaboration collaboration;

    /**
     * Current version of this Gm.
     */
    @objid ("345845bb-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("345845be-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 1;

    @objid ("345845c0-55b7-11e2-877f-002564c97630")
    private static final String IMAGE_MODE_HEADER = "ImageHeader";

    @objid ("3456bf53-55b7-11e2-877f-002564c97630")
     static final CollaborationSimpleStyleKeys SIMPLE_KEYS = new CollaborationSimpleStyleKeys();

    @objid ("3456bf55-55b7-11e2-877f-002564c97630")
    private static final CollaborationStructuredStyleKeys STRUCTURED_KEYS = new CollaborationStructuredStyleKeys();

    @objid ("345845b9-55b7-11e2-877f-002564c97630")
    private static final CollaborationImageStyleKeys IMAGE_KEYS = new CollaborationImageStyleKeys();

    @objid ("59948c17-0479-474a-9228-8afe52702825")
    private static final CollaborationUserImageStyleKeys USERIMAGE_KEYS = new CollaborationUserImageStyleKeys();

    /**
     * Default constructor.
     * 
     * @param diagram the diagram in which this gm is unmasked.
     * @param theCollaboration the represented object node, may be null.
     * @param ref a reference to the represented object node.
     */
    @objid ("345845c2-55b7-11e2-877f-002564c97630")
    public GmCollaboration(IGmDiagram diagram, final Collaboration theCollaboration, MRef ref) {
        super(diagram, ref);
        this.collaboration = theCollaboration;
        
        GmCollaborationPrimaryNode primary = new GmCollaborationPrimaryNode(diagram, ref);
        primary.setRoleInComposition(GmPortContainer.MAIN_NODE_ROLE);
        addChild(primary);
        
        GmDefaultModelElementLabel imageModeHeader = new GmDefaultModelElementLabel(diagram, ref);
        imageModeHeader.setRoleInComposition(IMAGE_MODE_HEADER);
        imageModeHeader.setLayoutData(PositionConstants.SOUTH);
        super.addChild(imageModeHeader);
    }

    /**
     * Empty constructor, needed for serialization.
     */
    @objid ("345845cf-55b7-11e2-877f-002564c97630")
    public GmCollaboration() {
        // empty constructor for the serialization
    }

    @objid ("345845d2-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return false;
    }

    @objid ("345845da-55b7-11e2-877f-002564c97630")
    @Override
    public Collaboration getRepresentedElement() {
        return this.collaboration;
    }

    @objid ("345845e1-55b7-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        StyleKey ret = STRUCTURED_KEYS.getStyleKey(metakey);
        if (ret != null) {
            return ret;
        }
        
        ret = SIMPLE_KEYS.getStyleKey(metakey);
        if (ret != null) {
            return ret;
        }
        
        ret = IMAGE_KEYS.getStyleKey(metakey);
        return ret;
    }

    @objid ("345845eb-55b7-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        if (getMainNode() != null) {
            switch (getMainNode().getRepresentationMode()) {
                case SIMPLE:
                    return SIMPLE_KEYS.getStyleKeys();
                case STRUCTURED:
                    return STRUCTURED_KEYS.getStyleKeys();
                case IMAGE:
                    return IMAGE_KEYS.getStyleKeys();
                default:
                    return Collections.emptyList();
            }
        } else {
            return Collections.emptyList();
        }
    }

    @objid ("345845f3-55b7-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmCollaboration.");
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

    @objid ("345845f9-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return getRepresentedElement();
    }

    @objid ("3459cc5d-55b7-11e2-877f-002564c97630")
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
                default:
                    break;
        
            }
        }
        return ret;
    }

    @objid ("3459cc66-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmCollaboration.", GmCollaboration.MINOR_VERSION);
    }

    @objid ("3459cc6c-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @objid ("3459cc71-55b7-11e2-877f-002564c97630")
    private void read_0(final IDiagramReader in) {
        super.read(in);
        
        this.collaboration = (Collaboration) resolveRef(getRepresentedRef());
    }

    /**
     * Is this node a Satellite, which position is defined relatively to the Main Node's bounds.
     * 
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Satellite.
     */
    @objid ("3459cc7d-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isSatellite(final GmNodeModel childNode) {
        return IMAGE_MODE_HEADER.equals(childNode.getRoleInComposition());
    }

    /**
     * Is this node a Port, which position is defined relatively to the Main Node's bounds.
     * 
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Port.
     */
    @objid ("3459cc87-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isPort(final GmNodeModel childNode) {
        return false;
    }

    @objid ("3459cc91-55b7-11e2-877f-002564c97630")
    @Override
    public void addStartingLink(final IGmLink link) {
        if (getMainNode() != null) {
            getMainNode().addStartingLink(link);
        } else {
            super.addStartingLink(link);
        }
    }

    @objid ("345b52f9-55b7-11e2-877f-002564c97630")
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
     * 
     * @param oldVersionGm the instance to migrate from.
     */
    @objid ("345b5300-55b7-11e2-877f-002564c97630")
    GmCollaboration(final _GmCollaboration oldVersionGm) {
        super(oldVersionGm.getDiagram(), oldVersionGm.getRepresentedRef());
        this.collaboration = oldVersionGm.getRepresentedElement();
        
        GmCollaborationPrimaryNode primary = new GmCollaborationPrimaryNode(oldVersionGm);
        primary.setRoleInComposition(GmPortContainer.MAIN_NODE_ROLE);
        addChild(primary);
        
        GmDefaultModelElementLabel imageModeHeader = new GmDefaultModelElementLabel(oldVersionGm.getDiagram(),
                                                                      oldVersionGm.getRepresentedRef());
        imageModeHeader.setRoleInComposition(IMAGE_MODE_HEADER);
        imageModeHeader.setLayoutData(PositionConstants.SOUTH);
        super.addChild(imageModeHeader);
    }

    @objid ("d7c88feb-fd95-4eca-984a-cdc03a2f89bb")
    @Override
    public boolean isMainSatelliteLabel(GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return role.equals(IMAGE_MODE_HEADER);
    }

}
