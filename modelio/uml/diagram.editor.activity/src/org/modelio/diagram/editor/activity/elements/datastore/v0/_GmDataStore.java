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

package org.modelio.diagram.editor.activity.elements.datastore.v0;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.core.ui.swt.images.ElementImageService;
import org.modelio.diagram.editor.activity.elements.activitynodeheader.GmActivityNodeHeader;
import org.modelio.diagram.editor.activity.elements.datastore.GmDataStoreImageStyleKeys;
import org.modelio.diagram.editor.activity.elements.datastore.GmDataStoreSimpleStyleKeys;
import org.modelio.diagram.editor.activity.elements.datastore.GmDataStoreStructuredStyleKeys;
import org.modelio.diagram.editor.activity.elements.datastore.GmDataStoreUserImageStyleKeys;
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
import org.modelio.metamodel.uml.behavior.activityModel.DataStoreNode;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * This class represents the Gm of a DataStore.
 */
@objid ("2a352b4a-55b6-11e2-877f-002564c97630")
public class _GmDataStore extends GmCompositeNode implements IImageableNode {
    @objid ("2a352b50-55b6-11e2-877f-002564c97630")
    private DataStoreNode element;

    /**
     * Current version of this Gm. Defaults to 1.
     */
    @objid ("2a352b5e-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 1;

    @objid ("2a352b61-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("2a352b53-55b6-11e2-877f-002564c97630")
    private static GmDataStoreSimpleStyleKeys SIMPLEKEYS = new GmDataStoreSimpleStyleKeys();

    @objid ("2a352b54-55b6-11e2-877f-002564c97630")
    private static GmDataStoreStructuredStyleKeys STRUCTKEYS = new GmDataStoreStructuredStyleKeys();

    @objid ("2a352b55-55b6-11e2-877f-002564c97630")
    private static GmDataStoreImageStyleKeys IMAGEKEYS = new GmDataStoreImageStyleKeys();

    /**
     * Header
     */
    @objid ("2a352b56-55b6-11e2-877f-002564c97630")
    private GmActivityNodeHeader header;

    @objid ("d1ce65a9-55c0-11e2-9337-002564c97630")
    private GmDefaultModelElementLabel imageModeHeader;

    @objid ("d1ce65aa-55c0-11e2-9337-002564c97630")
    private GmElementLabel objectNodeStateLabel;

    @objid ("b37cba50-b48e-4969-91bd-0700190e4006")
    private static GmDataStoreUserImageStyleKeys USERIMAGE_KEYS = new GmDataStoreUserImageStyleKeys();

    /**
     * Default constructor.
     * 
     * @param diagram the diagram in which this gm is unmasked.
     * @param theDataStore the represented data store, may be null.
     * @param ref a reference to the represented data store.
     */
    @objid ("2a352b63-55b6-11e2-877f-002564c97630")
    public _GmDataStore(IGmDiagram diagram, DataStoreNode theDataStore, MRef ref) {
        super(diagram, ref);
        this.element = theDataStore;
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
    @objid ("2a352b6f-55b6-11e2-877f-002564c97630")
    public _GmDataStore() {
        // empty constructor for the serialization
    }

    @objid ("2a352b72-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return true;
    }

    @objid ("2a36b1de-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        return false;
    }

    @objid ("2a36b1e6-55b6-11e2-877f-002564c97630")
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
    @objid ("2a36b1f0-55b6-11e2-877f-002564c97630")
    @Override
    public Image getImage() {
        return ElementImageService.getImage(getRelatedElement());
    }

    @objid ("2a36b1f6-55b6-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        return (RepresentationMode) getDisplayedStyle().getProperty(SIMPLEKEYS.getStyleKey(MetaKey.REPMODE));
    }

    @objid ("2a36b1fd-55b6-11e2-877f-002564c97630")
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

    @objid ("2a36b207-55b6-11e2-877f-002564c97630")
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

    @objid ("2a36b20f-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmDataStore.");
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

    @objid ("2a36b215-55b6-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        super.refreshFromObModel();
        String oldLabel = this.header.getMainLabel();
        this.header.refreshFromObModel();
        firePropertyChange(PROPERTY_LABEL, oldLabel, this.header.getMainLabel());
        // forcing visual refresh in case Image changed
        firePropertyChange(PROPERTY_LAYOUTDATA, null, getLayoutData());
    }

    @objid ("2a36b218-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRepresentedElement() {
        return this.element;
    }

    @objid ("2a38387b-55b6-11e2-877f-002564c97630")
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

    @objid ("2a383884-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return getRepresentedElement();
    }

    @objid ("2a38388b-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmDataStore.", _GmDataStore.MINOR_VERSION);
    }

    @objid ("2a383891-55b6-11e2-877f-002564c97630")
    private void read_1(IDiagramReader in) {
        super.read(in);
        this.header = (GmActivityNodeHeader) this.getChildren().get(0);
        this.element = (DataStoreNode) resolveRef(getRepresentedRef());
        
        this.imageModeHeader = (GmDefaultModelElementLabel) this.getChildren().get(1);
        this.objectNodeStateLabel = (GmElementLabel) this.getChildren().get(2);
    }

    @objid ("2a383896-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        // In version 0, objectNodeStateLabel did not exist,
        // so we have to create it instead of reading it.
        super.read(in);
        this.header = (GmActivityNodeHeader) this.getChildren().get(0);
        this.element = (DataStoreNode) resolveRef(getRepresentedRef());
        
        this.imageModeHeader = (GmDefaultModelElementLabel) this.getChildren().get(1);
        this.objectNodeStateLabel = new GmObjectNodeStateLabel(getDiagram(), getRepresentedRef());
        addChild(this.objectNodeStateLabel);
    }

    @objid ("2a38389b-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @objid ("2a3838a0-55b6-11e2-877f-002564c97630")
    public GmActivityNodeHeader getHeader() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.header;
    }

    @objid ("2a3838a4-55b6-11e2-877f-002564c97630")
    public GmElementLabel getObjectNodeStateLabel() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.objectNodeStateLabel;
    }

}
