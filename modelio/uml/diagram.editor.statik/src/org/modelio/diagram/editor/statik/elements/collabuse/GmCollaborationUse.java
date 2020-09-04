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

package org.modelio.diagram.editor.statik.elements.collabuse;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.PositionConstants;
import org.modelio.diagram.editor.statik.elements.collabuse.v0._GmCollaborationUse;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.common.portcontainer.GmPortContainer;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.statik.CollaborationUse;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * This class represents the Gm of a CollaborationUse.
 */
@objid ("34723680-55b7-11e2-877f-002564c97630")
public final class GmCollaborationUse extends GmPortContainer {
    @objid ("34723684-55b7-11e2-877f-002564c97630")
    private CollaborationUse collabUse;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("3472368d-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("34723690-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 1;

    @objid ("34723692-55b7-11e2-877f-002564c97630")
    private static final String IMAGE_MODE_HEADER = "image mode header";

    @objid ("34723687-55b7-11e2-877f-002564c97630")
     static final CollaborationUseSimpleStyleKeys SIMPLE_KEYS = new CollaborationUseSimpleStyleKeys();

    @objid ("34723689-55b7-11e2-877f-002564c97630")
    private static final CollaborationUseStructuredStyleKeys STRUCTURED_KEYS = new CollaborationUseStructuredStyleKeys();

    @objid ("3472368b-55b7-11e2-877f-002564c97630")
    private static final CollaborationUseImageStyleKeys IMAGE_KEYS = new CollaborationUseImageStyleKeys();

    @objid ("ba84e256-1ab0-4bd9-93bb-9e60f71b571b")
    private static final CollaborationUseUserImageStyleKeys USERIMAGE_KEYS = new CollaborationUseUserImageStyleKeys();

    /**
     * Default constructor.
     * 
     * @param diagram the diagram in which this gm is unmasked.
     * @param theCollabCase the represented object node, may be null.
     * @param ref a reference to the represented object node.
     */
    @objid ("34723694-55b7-11e2-877f-002564c97630")
    public GmCollaborationUse(IGmDiagram diagram, final CollaborationUse theCollabCase, MRef ref) {
        super(diagram, ref);
        this.collabUse = theCollabCase;
        
        GmCollaborationUsePrimaryNode primary = new GmCollaborationUsePrimaryNode(diagram, ref);
        primary.setRoleInComposition(GmPortContainer.MAIN_NODE_ROLE);
        addChild(primary);
        
        GmDefaultModelElementLabel header = new GmDefaultModelElementLabel(diagram, ref);
        header.setRoleInComposition(IMAGE_MODE_HEADER);
        header.setLayoutData(PositionConstants.SOUTH);
        
        super.addChild(header);
    }

    /**
     * Empty constructor, needed for serialization.
     */
    @objid ("3473bd05-55b7-11e2-877f-002564c97630")
    public GmCollaborationUse() {
        // empty constructor for the serialization
    }

    @objid ("3473bd08-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return false;
    }

    @objid ("3473bd10-55b7-11e2-877f-002564c97630")
    @Override
    public CollaborationUse getRelatedElement() {
        return this.collabUse;
    }

    @objid ("3473bd17-55b7-11e2-877f-002564c97630")
    @Override
    public CollaborationUse getRepresentedElement() {
        return this.collabUse;
    }

    @objid ("3473bd1e-55b7-11e2-877f-002564c97630")
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

    @objid ("3473bd28-55b7-11e2-877f-002564c97630")
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

    @objid ("3473bd30-55b7-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmCollaborationUse.");
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

    @objid ("3473bd36-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0.
        writeMinorVersion(out, "GmCollaborationUse.", Integer.valueOf(GmCollaborationUse.MINOR_VERSION));
    }

    @objid ("3475439c-55b7-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        
        this.collabUse = (CollaborationUse) resolveRef(getRepresentedRef());
    }

    @objid ("347543a1-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    /**
     * Is this node a Satellite, which position is defined relatively to the Main Node's bounds.
     * 
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Satellite.
     */
    @objid ("347543ac-55b7-11e2-877f-002564c97630")
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
    @objid ("347543b6-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isPort(final GmNodeModel childNode) {
        return false;
    }

    @objid ("347543c0-55b7-11e2-877f-002564c97630")
    @Override
    public void addStartingLink(final IGmLink link) {
        if (getMainNode() != null) {
            getMainNode().addStartingLink(link);
        } else {
            super.addStartingLink(link);
        }
    }

    @objid ("347543c7-55b7-11e2-877f-002564c97630")
    @Override
    public void addEndingLink(final IGmLink link) {
        if (getMainNode() != null) {
            getMainNode().addEndingLink(link);
        } else {
            super.addEndingLink(link);
        }
    }

    @objid ("347543ce-55b7-11e2-877f-002564c97630")
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

    /**
     * Migration constructor from major version 0, should only be called by migrator.
     * 
     * @param oldVersionGm the instance to migrate from.
     */
    @objid ("3476ca3e-55b7-11e2-877f-002564c97630")
    GmCollaborationUse(final _GmCollaborationUse oldVersionGm) {
        super(oldVersionGm.getDiagram(), oldVersionGm.getRepresentedRef());
        this.collabUse = oldVersionGm.getRelatedElement();
        
        GmCollaborationUsePrimaryNode primary = new GmCollaborationUsePrimaryNode(oldVersionGm);
        primary.setRoleInComposition(GmPortContainer.MAIN_NODE_ROLE);
        addChild(primary);
        
        GmDefaultModelElementLabel header = new GmDefaultModelElementLabel(oldVersionGm.getDiagram(), oldVersionGm.getRepresentedRef());
        header.setRoleInComposition(IMAGE_MODE_HEADER);
        header.setLayoutData(PositionConstants.SOUTH);
        
        super.addChild(header);
    }

    @objid ("f78a362a-e3cf-49e3-9fa8-38b6cfc2e801")
    @Override
    public boolean isMainSatelliteLabel(GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return role.equals(IMAGE_MODE_HEADER);
    }

}
