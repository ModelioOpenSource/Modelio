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

package org.modelio.diagram.editor.communication.elements.communicationnode.v0;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.core.ui.swt.images.ElementImageService;
import org.modelio.diagram.editor.communication.elements.communicationnode.GmCommunicationNodeFlatHeader;
import org.modelio.diagram.editor.communication.elements.communicationnode.GmCommunicationNodeHeader;
import org.modelio.diagram.elements.common.header.GmModelElementHeader;
import org.modelio.diagram.elements.common.label.modelelement.GmModelElementLabel;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.node.IImageableNode;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationNode;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * This class represents the Gm of a CommunicationNode.
 */
@objid ("7a651f7c-55b6-11e2-877f-002564c97630")
public class _GmCommunicationNode extends GmCompositeNode implements IImageableNode {
    @objid ("7a651f82-55b6-11e2-877f-002564c97630")
    private CommunicationNode communicationNode;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("7a651f92-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("7a651f95-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("7a651f85-55b6-11e2-877f-002564c97630")
    private static final GmCommunicationNodeSimpleStyleKeys SIMPLEKEYS = new GmCommunicationNodeSimpleStyleKeys();

    @objid ("7a651f87-55b6-11e2-877f-002564c97630")
    private static final GmCommunicationNodeStructuredStyleKeys STRUCTKEYS = new GmCommunicationNodeStructuredStyleKeys();

    @objid ("7a651f89-55b6-11e2-877f-002564c97630")
    private static final GmCommunicationNodeImageStyleKeys IMAGEKEYS = new GmCommunicationNodeImageStyleKeys();

    /**
     * Header
     */
    @objid ("9ca08bf9-55c1-11e2-9337-002564c97630")
    private GmModelElementHeader header;

    @objid ("9ca08bfb-55c1-11e2-9337-002564c97630")
    private GmModelElementLabel imageModeHeader;

    @objid ("ca8a8556-2902-4e13-b51a-4175d77ff7c0")
    private static final GmCommunicationNodeUserImageStyleKeys USERIMAGE_KEYS = new GmCommunicationNodeUserImageStyleKeys();

    /**
     * Default constructor.
     * 
     * @param diagram the diagram in which this gm is unmasked.
     * @param theCommunicationNode the represented object node, may be null.
     * @param ref a reference to the represented object node.
     */
    @objid ("7a651f97-55b6-11e2-877f-002564c97630")
    public _GmCommunicationNode(IGmDiagram diagram, CommunicationNode theCommunicationNode, MRef ref) {
        super(diagram, ref);
        this.communicationNode = theCommunicationNode;
        this.header = new GmCommunicationNodeHeader(diagram, ref);
        this.header.setShowMetaclassIcon(true);
        super.addChild(this.header);
        this.imageModeHeader = new GmCommunicationNodeFlatHeader(diagram, ref);
        addChild(this.imageModeHeader);
    }

    /**
     * Empty constructor, needed for serialization.
     */
    @objid ("7a651fa3-55b6-11e2-877f-002564c97630")
    public _GmCommunicationNode() {
        // empty constructor for the serialization
    }

    @objid ("7a651fa6-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return true;
    }

    @objid ("7a651fae-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        return false;
    }

    @objid ("7a651fb6-55b6-11e2-877f-002564c97630")
    @Override
    public GmCompositeNode getCompositeFor(Class<? extends MObject> metaclass) {
        if (canCreate(metaclass)) {
            return this;
        }
        // else
        return null;
    }

    @objid ("7a66a61d-55b6-11e2-877f-002564c97630")
    @Override
    public ModelElement getRepresentedElement() {
        return this.communicationNode;
    }

    /**
     * Get the stereotype image to display.
     * 
     * @return the stereotype image to display. Must not be <i>null</i>.
     */
    @objid ("7a66a624-55b6-11e2-877f-002564c97630")
    @Override
    public Image getImage() {
        return ElementImageService.getImage(getRepresentedElement());
    }

    @objid ("7a66a62a-55b6-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        return (RepresentationMode) getDisplayedStyle().getProperty(_GmCommunicationNode.SIMPLEKEYS.getStyleKey(MetaKey.REPMODE));
    }

    @objid ("7a66a631-55b6-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        RepresentationMode mode = getRepresentationMode();
        switch (mode) {
        case SIMPLE:
            return _GmCommunicationNode.SIMPLEKEYS.getStyleKey(metakey);
        case STRUCTURED:
            return _GmCommunicationNode.STRUCTKEYS.getStyleKey(metakey);
        case IMAGE:
            return _GmCommunicationNode.IMAGEKEYS.getStyleKey(metakey);
        case USER_IMAGE:
            return _GmCommunicationNode.USERIMAGE_KEYS.getStyleKey(metakey);
        default:
            return null;
        }
    }

    @objid ("7a66a63a-55b6-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        RepresentationMode mode = getRepresentationMode();
        switch (mode) {
        case SIMPLE:
            return _GmCommunicationNode.SIMPLEKEYS.getStyleKeys();
        case STRUCTURED:
            return _GmCommunicationNode.STRUCTKEYS.getStyleKeys();
        case IMAGE:
            return _GmCommunicationNode.IMAGEKEYS.getStyleKeys();
        case USER_IMAGE:
            return _GmCommunicationNode.USERIMAGE_KEYS.getStyleKeys();
        default:
            return Collections.emptyList();
        }
    }

    @objid ("7a66a642-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = GmAbstractObject.readMinorVersion(in, "GmCommunicationNode.");
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

    @objid ("7a66a648-55b6-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        // forcing visual refresh in case Image changed
        firePropertyChange(IGmObject.PROPERTY_LAYOUTDATA, null, getLayoutData());
    }

    @objid ("7a66a64b-55b6-11e2-877f-002564c97630")
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

    @objid ("7a66a654-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return getRepresentedElement();
    }

    @objid ("7a66a65b-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        GmAbstractObject.writeMinorVersion(out, "GmCommunicationNode.", _GmCommunicationNode.MINOR_VERSION);
    }

    @objid ("7a682cbc-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.header = (GmModelElementHeader) this.getChildren().get(0);
        this.communicationNode = (CommunicationNode) resolveRef(getRepresentedRef());
        
        this.imageModeHeader = (GmModelElementLabel) this.getChildren().get(1);
    }

    @objid ("7a682cc1-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return _GmCommunicationNode.MAJOR_VERSION;
    }

    @objid ("7a682cc6-55b6-11e2-877f-002564c97630")
    public GmModelElementHeader getHeader() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.header;
    }

}
