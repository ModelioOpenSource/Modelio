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

package org.modelio.diagram.editor.activity.elements.centralbuffer.v0;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.core.ui.swt.images.ElementImageService;
import org.modelio.diagram.editor.activity.elements.activitynodeheader.GmActivityNodeHeader;
import org.modelio.diagram.editor.activity.elements.centralbuffer.GmCentralBufferImageStyleKeys;
import org.modelio.diagram.editor.activity.elements.centralbuffer.GmCentralBufferSimpleStyleKeys;
import org.modelio.diagram.editor.activity.elements.centralbuffer.GmCentralBufferStructuredStyleKeys;
import org.modelio.diagram.editor.activity.elements.centralbuffer.GmCentralBufferUserImageStyleKeys;
import org.modelio.diagram.editor.activity.elements.objectnode.GmObjectNodeStateLabel;
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
import org.modelio.metamodel.uml.behavior.activityModel.CentralBufferNode;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * This class represents the Gm of a CentralBuffer.
 */
@objid ("29f08122-55b6-11e2-877f-002564c97630")
public class _GmCentralBuffer extends GmCompositeNode implements IImageableNode {
    @objid ("29f08128-55b6-11e2-877f-002564c97630")
    private CentralBufferNode element;

    /**
     * Current version of this Gm. Defaults to 1.
     */
    @objid ("29f08136-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 1;

    @objid ("29f08139-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("29f0812b-55b6-11e2-877f-002564c97630")
    private static GmCentralBufferSimpleStyleKeys SIMPLEKEYS = new GmCentralBufferSimpleStyleKeys();

    @objid ("29f0812c-55b6-11e2-877f-002564c97630")
    private static GmCentralBufferStructuredStyleKeys STRUCTKEYS = new GmCentralBufferStructuredStyleKeys();

    @objid ("29f0812d-55b6-11e2-877f-002564c97630")
    private static GmCentralBufferImageStyleKeys IMAGEKEYS = new GmCentralBufferImageStyleKeys();

    /**
     * Header
     */
    @objid ("29f0812e-55b6-11e2-877f-002564c97630")
    private GmActivityNodeHeader header;

    @objid ("d1a532eb-55c0-11e2-9337-002564c97630")
    private GmDefaultModelElementLabel imageModeHeader;

    @objid ("d1a532ec-55c0-11e2-9337-002564c97630")
    private GmElementLabel objectNodeStateLabel;

    @objid ("ae055c13-6be2-4b19-866f-80dec075590d")
    private static GmCentralBufferUserImageStyleKeys USERIMAGE_KEYS = new GmCentralBufferUserImageStyleKeys();

    /**
     * Default constructor.
     * 
     * @param diagram the diagram in which this gm is unmasked.
     * @param theCentralBuffer the represented central buffer, may be null.
     * @param ref a reference to the represented central buffer.
     */
    @objid ("29f0813b-55b6-11e2-877f-002564c97630")
    public _GmCentralBuffer(IGmDiagram diagram, CentralBufferNode theCentralBuffer, MRef ref) {
        super(diagram, ref);
        this.element = theCentralBuffer;
        this.header = new GmActivityNodeHeader(diagram, ref);
        this.header.setShowMetaclassIcon(true);
        super.addChild(this.header);
        this.imageModeHeader = new GmDefaultModelElementLabel(diagram, ref);
        addChild(this.imageModeHeader);
        this.objectNodeStateLabel = new GmObjectNodeStateLabel(diagram, ref);
        addChild(this.objectNodeStateLabel);
    }

    /**
     * Empty constructor, needed for serialisation.
     */
    @objid ("29f08147-55b6-11e2-877f-002564c97630")
    public _GmCentralBuffer() {
        // empty constructor for the serialization
    }

    @objid ("29f0814a-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return true;
    }

    @objid ("29f207b9-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        return false;
    }

    @objid ("29f207c1-55b6-11e2-877f-002564c97630")
    @Override
    public GmCompositeNode getCompositeFor(Class<? extends MObject> metaclass) {
        if (canCreate(metaclass)) {
            return this;
        }
        // else
        return null;
    }

    /**
     * Get the stereotype image to display.
     * 
     * @return the stereotype image to display. Must not be <i>null</i>.
     */
    @objid ("29f207cb-55b6-11e2-877f-002564c97630")
    @Override
    public Image getImage() {
        return ElementImageService.getImage(getRelatedElement());
    }

    @objid ("29f207d1-55b6-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        return (RepresentationMode) getDisplayedStyle().getProperty(SIMPLEKEYS.getStyleKey(MetaKey.REPMODE));
    }

    @objid ("29f207d8-55b6-11e2-877f-002564c97630")
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

    @objid ("29f207e2-55b6-11e2-877f-002564c97630")
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

    @objid ("29f207ea-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmCentralBuffer.");
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

    @objid ("29f207f0-55b6-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        super.refreshFromObModel();
        String oldLabel = this.header.getMainLabel();
        this.header.refreshFromObModel();
        firePropertyChange(PROPERTY_LABEL, oldLabel, this.header.getMainLabel());
        // forcing visual refresh in case Image changed
        firePropertyChange(PROPERTY_LAYOUTDATA, null, getLayoutData());
    }

    @objid ("29f207f3-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRepresentedElement() {
        return this.element;
    }

    @objid ("29f207fa-55b6-11e2-877f-002564c97630")
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

    @objid ("29f38e61-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return getRepresentedElement();
    }

    @objid ("29f38e68-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmCentralBuffer.", _GmCentralBuffer.MINOR_VERSION);
    }

    @objid ("29f38e6e-55b6-11e2-877f-002564c97630")
    private void read_1(IDiagramReader in) {
        super.read(in);
        this.header = (GmActivityNodeHeader) this.getChildren().get(0);
        this.element = (CentralBufferNode) resolveRef(getRepresentedRef());
        
        this.imageModeHeader = (GmDefaultModelElementLabel) this.getChildren().get(1);
        this.objectNodeStateLabel = (GmElementLabel) this.getChildren().get(2);
    }

    @objid ("29f38e73-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        // In version 0, objectNodeStateLabel did not exist,
        // so we have to create it instead of reading it.
        super.read(in);
        this.header = (GmActivityNodeHeader) this.getChildren().get(0);
        this.element = (CentralBufferNode) resolveRef(getRepresentedRef());
        
        this.imageModeHeader = (GmDefaultModelElementLabel) this.getChildren().get(1);
        this.objectNodeStateLabel = new GmObjectNodeStateLabel(getDiagram(), getRepresentedRef());
        addChild(this.objectNodeStateLabel);
    }

    @objid ("29f38e78-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @objid ("29f38e7d-55b6-11e2-877f-002564c97630")
    public GmActivityNodeHeader getHeader() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.header;
    }

    @objid ("29f38e81-55b6-11e2-877f-002564c97630")
    public GmElementLabel getObjectNodeStateLabel() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.objectNodeStateLabel;
    }

}
