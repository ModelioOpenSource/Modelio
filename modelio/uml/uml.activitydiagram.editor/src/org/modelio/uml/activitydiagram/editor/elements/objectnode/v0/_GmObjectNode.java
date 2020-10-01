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

package org.modelio.uml.activitydiagram.editor.elements.objectnode.v0;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.diagram.elements.common.label.base.GmElementLabel;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.node.IImageableNode;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectNode;
import org.modelio.platform.model.ui.swt.images.ElementImageService;
import org.modelio.uml.activitydiagram.editor.elements.objectnode.GmObjectNodeHeader;
import org.modelio.uml.activitydiagram.editor.elements.objectnode.GmObjectNodeImageStyleKeys;
import org.modelio.uml.activitydiagram.editor.elements.objectnode.GmObjectNodeSimpleStyleKeys;
import org.modelio.uml.activitydiagram.editor.elements.objectnode.GmObjectNodeStateLabel;
import org.modelio.uml.activitydiagram.editor.elements.objectnode.GmObjectNodeStructuredStyleKeys;
import org.modelio.uml.activitydiagram.editor.elements.objectnode.GmObjectNodeUserImageStyleKeys;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * This class represents the Gm of a ObjectNode.
 */
@objid ("2ae31e9a-55b6-11e2-877f-002564c97630")
public class _GmObjectNode extends GmCompositeNode implements IImageableNode {
    @objid ("2ae31ea0-55b6-11e2-877f-002564c97630")
    private ObjectNode element;

    /**
     * Current version of this Gm. Defaults to 1.
     */
    @objid ("2ae31eae-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 1;

    @objid ("2ae31eb1-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("d0a7e709-55c0-11e2-9337-002564c97630")
    private GmDefaultModelElementLabel imageModeHeader;

    @objid ("d0a7e70a-55c0-11e2-9337-002564c97630")
    private GmElementLabel objectNodeStateLabel;

    @objid ("311fc63b-58a2-11e2-9574-002564c97630")
    private static GmObjectNodeSimpleStyleKeys SIMPLEKEYS = new GmObjectNodeSimpleStyleKeys();

    @objid ("311fc63c-58a2-11e2-9574-002564c97630")
    private static GmObjectNodeStructuredStyleKeys STRUCTKEYS = new GmObjectNodeStructuredStyleKeys();

    @objid ("311fc63d-58a2-11e2-9574-002564c97630")
    private static GmObjectNodeImageStyleKeys IMAGEKEYS = new GmObjectNodeImageStyleKeys();

    /**
     * Header
     */
    @objid ("311fc63e-58a2-11e2-9574-002564c97630")
    private GmObjectNodeHeader header;

    @objid ("27b9774b-463c-456a-a473-60c979b7f152")
    private static GmObjectNodeUserImageStyleKeys USERIMAGE_KEYS = new GmObjectNodeUserImageStyleKeys();

    /**
     * Default constructor.
     * 
     * @param diagram the diagram in which this gm is unmasked.
     * @param theObjectNode the represented object node, may be null.
     * @param ref a reference to the represented object node.
     */
    @objid ("2ae31eb3-55b6-11e2-877f-002564c97630")
    public _GmObjectNode(IGmDiagram diagram, ObjectNode theObjectNode, MRef ref) {
        super(diagram, ref);
        this.element = theObjectNode;
        this.header = new GmObjectNodeHeader(diagram, ref);
        this.header.setShowMetaclassIcon(true);
        super.addChild(this.header);
        this.imageModeHeader = new GmDefaultModelElementLabel(diagram, ref);
        addChild(this.imageModeHeader);
        this.objectNodeStateLabel = new GmObjectNodeStateLabel(diagram, ref);
        addChild(this.objectNodeStateLabel);
    }

    /**
     * Empty constructor, needed for serialization.
     */
    @objid ("2ae4a51c-55b6-11e2-877f-002564c97630")
    public _GmObjectNode() {
        // empty constructor for the serialization
    }

    @objid ("2ae4a51f-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return true;
    }

    @objid ("2ae4a527-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        return false;
    }

    @objid ("2ae4a52f-55b6-11e2-877f-002564c97630")
    @Override
    public GmCompositeNode getCompositeFor(Class<? extends MObject> metaclass) {
        if (canCreate(metaclass)) {
            return this;
        }
        // else
        return null;
    }

    @objid ("2ae4a539-55b6-11e2-877f-002564c97630")
    @Override
    public Image getImage() {
        return ElementImageService.getImage(getRelatedElement());
    }

    @objid ("2ae4a53e-55b6-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        return (RepresentationMode) getDisplayedStyle().getProperty(SIMPLEKEYS.getStyleKey(MetaKey.REPMODE));
    }

    @objid ("2ae4a545-55b6-11e2-877f-002564c97630")
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

    @objid ("2ae4a54f-55b6-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        RepresentationMode mode = getRepresentationMode();
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
    }

    @objid ("2ae4a557-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmObjectNode.");
        switch (readVersion) {
        case 1: {
            read_1(in);
            break;
        }
        case 0: {
            read_0(in);
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

    @objid ("2ae4a55d-55b6-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        super.refreshFromObModel();
        String oldLabel = this.header.getMainLabel();
        this.header.refreshFromObModel();
        firePropertyChange(PROPERTY_LABEL, oldLabel, this.header.getMainLabel());
        // forcing visual refresh in case Image changed
        firePropertyChange(PROPERTY_LAYOUTDATA, null, getLayoutData());
    }

    @objid ("2ae62bbb-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRepresentedElement() {
        return this.element;
    }

    @objid ("2ae62bc2-55b6-11e2-877f-002564c97630")
    @Override
    public List<GmNodeModel> getVisibleChildren() {
        // Returned result depends on current representation mode:
        List<GmNodeModel> ret;
        switch (getRepresentationMode()) {
        case IMAGE: {
            ret = new ArrayList<>(1);
            ret.add(this.imageModeHeader);
            break;
        }
        default: {
            ret = super.getVisibleChildren();
            // Remove the header used for image mode.
            ret.remove(this.imageModeHeader);
            break;
        }
        }
        return ret;
    }

    @objid ("2ae62bcb-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return getRepresentedElement();
    }

    @objid ("2ae62bd2-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmObjectNode.", _GmObjectNode.MINOR_VERSION);
    }

    @objid ("2ae62bd8-55b6-11e2-877f-002564c97630")
    private void read_1(IDiagramReader in) {
        super.read(in);
        this.header = (GmObjectNodeHeader) this.getChildren().get(0);
        this.element = (ObjectNode) resolveRef(getRepresentedRef());
        
        this.imageModeHeader = (GmDefaultModelElementLabel) this.getChildren().get(1);
        this.objectNodeStateLabel = (GmElementLabel) this.getChildren().get(2);
    }

    @objid ("2ae62bdd-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        // In version 0, objectNodeStateLabel did not exist,
        // so we have to create it instead of reading it.
        super.read(in);
        this.header = (GmObjectNodeHeader) this.getChildren().get(0);
        this.element = (ObjectNode) resolveRef(getRepresentedRef());
        
        this.imageModeHeader = (GmDefaultModelElementLabel) this.getChildren().get(1);
        this.objectNodeStateLabel = new GmObjectNodeStateLabel(getDiagram(), getRepresentedRef());
        addChild(this.objectNodeStateLabel);
    }

    @objid ("2ae62be2-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @objid ("2ae62be7-55b6-11e2-877f-002564c97630")
    public GmObjectNodeHeader getHeader() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.header;
    }

    @objid ("2ae62beb-55b6-11e2-877f-002564c97630")
    public GmElementLabel getObjectNodeStateLabel() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.objectNodeStateLabel;
    }

}
