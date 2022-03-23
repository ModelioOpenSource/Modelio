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
package org.modelio.uml.activitydiagram.editor.elements.centralbuffer;

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
import org.modelio.metamodel.uml.behavior.activityModel.CentralBufferNode;
import org.modelio.uml.activitydiagram.editor.elements.centralbuffer.v0._GmCentralBuffer;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * This class represents the Gm of a CentralBuffer.
 */
@objid ("29e13f05-55b6-11e2-877f-002564c97630")
public class GmCentralBuffer extends GmPortContainer {
    @objid ("29e13f09-55b6-11e2-877f-002564c97630")
    private CentralBufferNode element;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("29e13f0f-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("29e13f12-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 1;

    @objid ("29e2c57a-55b6-11e2-877f-002564c97630")
    private static final String IMAGE_MODE_HEADER = "image mode header";

    @objid ("2ff637eb-58a2-11e2-9574-002564c97630")
    static GmCentralBufferSimpleStyleKeys SIMPLEKEYS = new GmCentralBufferSimpleStyleKeys();

    @objid ("2ff637ec-58a2-11e2-9574-002564c97630")
    private static GmCentralBufferStructuredStyleKeys STRUCTKEYS = new GmCentralBufferStructuredStyleKeys();

    @objid ("2ff637ed-58a2-11e2-9574-002564c97630")
    private static GmCentralBufferImageStyleKeys IMAGEKEYS = new GmCentralBufferImageStyleKeys();

    @objid ("8758fa71-cc69-4bc1-9dc8-f2fcf669c087")
    private static GmCentralBufferUserImageStyleKeys USERIMAGE_KEYS = new GmCentralBufferUserImageStyleKeys();

    /**
     * Default constructor.
     * @param diagram the diagram in which this gm is unmasked.
     * @param theCentralBuffer the represented central buffer, may be null.
     * @param ref a reference to the represented central buffer.
     */
    @objid ("29e2c57c-55b6-11e2-877f-002564c97630")
    public  GmCentralBuffer(IGmDiagram diagram, CentralBufferNode theCentralBuffer, MRef ref) {
        super(diagram, ref);
        this.element = theCentralBuffer;
        
        GmCentralBufferPrimaryNode primary = new GmCentralBufferPrimaryNode(diagram, ref);
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
    @objid ("29e2c588-55b6-11e2-877f-002564c97630")
    public  GmCentralBuffer() {
        // empty constructor for the serialization
    }

    @objid ("29e2c58b-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return false;
    }

    @objid ("29e2c593-55b6-11e2-877f-002564c97630")
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

    @objid ("29e2c59d-55b6-11e2-877f-002564c97630")
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

    @objid ("29e2c5a5-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmCentralBuffer.");
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

    @objid ("29e2c5ab-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRepresentedElement() {
        return this.element;
    }

    @objid ("29e2c5b2-55b6-11e2-877f-002564c97630")
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
            case USER_IMAGE:
            case IMAGE:
            default:
                break;
        
            }
        }
        return ret;
    }

    @objid ("29e44c1d-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return this.element;
    }

    @objid ("29e44c24-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmCentralBuffer.", GmCentralBuffer.MINOR_VERSION);
        
    }

    @objid ("29e44c2a-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.element = (CentralBufferNode) resolveRef(getRepresentedRef());
        
    }

    @objid ("29e44c2f-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    /**
     * Migration constructor from major version 0, should only be called by migrator.
     * @param oldVersionGm the instance to migrate from.
     */
    @objid ("29e44c34-55b6-11e2-877f-002564c97630")
     GmCentralBuffer(final _GmCentralBuffer oldVersionGm) {
        super(oldVersionGm.getDiagram(), oldVersionGm.getRepresentedRef());
        this.element = (CentralBufferNode) oldVersionGm.getRelatedElement();
        
        GmCentralBufferPrimaryNode primary = new GmCentralBufferPrimaryNode(oldVersionGm);
        primary.setRoleInComposition(GmPortContainer.MAIN_NODE_ROLE);
        addChild(primary);
        
        GmDefaultModelElementLabel imageModeHeader = new GmDefaultModelElementLabel(oldVersionGm.getDiagram(),
                oldVersionGm.getRepresentedRef());
        imageModeHeader.setRoleInComposition(IMAGE_MODE_HEADER);
        imageModeHeader.setLayoutData(PositionConstants.SOUTH);
        addChild(imageModeHeader);
        
    }

    @objid ("29e44c3e-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSatellite(final GmNodeModel child) {
        return IMAGE_MODE_HEADER.equals(child.getRoleInComposition());
    }

    @objid ("29e44c47-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isPort(final GmNodeModel child) {
        return false;
    }

    @objid ("29e44c50-55b6-11e2-877f-002564c97630")
    @Override
    public void addStartingLink(final IGmLink link) {
        if (getMainNode() != null) {
            getMainNode().addStartingLink(link);
        } else {
            super.addStartingLink(link);
        }
        
    }

    @objid ("29e5d2be-55b6-11e2-877f-002564c97630")
    @Override
    public void addEndingLink(final IGmLink link) {
        if (getMainNode() != null) {
            getMainNode().addEndingLink(link);
        } else {
            super.addEndingLink(link);
        }
        
    }

    @objid ("36419428-dcd4-42e8-be56-63891c974d89")
    @Override
    public boolean isMainSatelliteLabel(GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return role.equals(IMAGE_MODE_HEADER);
    }

}
