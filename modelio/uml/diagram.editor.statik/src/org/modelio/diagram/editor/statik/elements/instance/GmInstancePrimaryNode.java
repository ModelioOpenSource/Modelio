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

package org.modelio.diagram.editor.statik.elements.instance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.core.ui.swt.images.ElementImageService;
import org.modelio.diagram.editor.statik.elements.instanceheader.GmInstanceHeader;
import org.modelio.diagram.editor.statik.elements.instanceinternalstructure.GmInstanceInternalStructure;
import org.modelio.diagram.editor.statik.elements.slot.GmSlotGroup;
import org.modelio.diagram.elements.common.freezone.GmFreeZone;
import org.modelio.diagram.elements.common.group.GmGroup;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNoStyleCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.node.IImageableNode;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.metamodel.uml.statik.AttributeLink;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents an {@link Instance}.
 * <p>
 * The GmClass is composed of many zones and groups that are shown depending on the style properties.
 */
@objid ("353a1a4d-55b7-11e2-877f-002564c97630")
public class GmInstancePrimaryNode extends GmNoStyleCompositeNode implements IImageableNode {
    /**
     * Current version of this Gm.
     */
    @objid ("353a1a5c-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 1;

    @objid ("353a1a5f-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("a68cdf15-55c2-11e2-9337-002564c97630")
    private GmDefaultModelElementLabel imageModeHeader;

    /**
     * Instance header
     */
    @objid ("5ddf7b28-5bd5-11e2-9e33-00137282c51b")
    private GmInstanceHeader header;

    /**
     * Slot group
     */
    @objid ("5ddf7b2a-5bd5-11e2-9e33-00137282c51b")
    private GmSlotGroup slotGroup;

    /**
     * Internal structure
     */
    @objid ("5de1dd83-5bd5-11e2-9e33-00137282c51b")
    private GmInstanceInternalStructure internalStructure;

    /**
     * Constructor for deserialization only.
     */
    @objid ("353a1a61-55b7-11e2-877f-002564c97630")
    public GmInstancePrimaryNode() {
        // Nothing to do.
    }

    /**
     * Creates a GmInstancePrimaryNode.
     * @param diagram The owner diagram.
     * @param relatedRef a reference to the element this GmModel is related to, must not be null.
     */
    @objid ("353a1a64-55b7-11e2-877f-002564c97630")
    public GmInstancePrimaryNode(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
        
        this.header = new GmInstanceHeader(diagram, relatedRef);
        this.header.setRoleInComposition(Constants.HEADER);
        
        this.slotGroup = new GmSlotGroup(diagram, relatedRef);
        this.slotGroup.setRoleInComposition(Constants.SLOTGROUP);
        
        this.internalStructure = new GmInstanceInternalStructure(diagram, relatedRef);
        this.internalStructure.setRoleInComposition(Constants.INTERNALSTRUCTURE);
        
        super.addChild(this.header);
        super.addChild(this.slotGroup);
        super.addChild(this.internalStructure);
        
        styleChanged(getDisplayedStyle());
        this.imageModeHeader = new GmDefaultModelElementLabel(diagram, relatedRef);
        addChild(this.imageModeHeader);
    }

    @objid ("353a1a6d-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return false;
    }

    @objid ("353a1a75-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        return false;
    }

    /**
     * Get the group where <tt>GmAttributes</tt> are unmasked.
     * @return the attributes group.
     */
    @objid ("353a1a7d-55b7-11e2-877f-002564c97630")
    public GmCompositeNode getAttributesGroup() {
        return this.slotGroup;
    }

    @objid ("353ba0da-55b7-11e2-877f-002564c97630")
    @Override
    public GmCompositeNode getCompositeFor(Class<? extends MObject> metaclass) {
        GmCompositeNode ret = null;
        
        if (BindableInstance.class.isAssignableFrom(metaclass) && !(Port.class.isAssignableFrom(metaclass))) {
            // Instances are unmasked in the internal structure zone or group
            ret = getInternalStructure().getCompositeFor(metaclass);
        } else if (AttributeLink.class.isAssignableFrom(metaclass)) {
            // Attributes are unmasked in the attributes group
            ret = getAttributesGroup();
        }
        return ret;
    }

    @objid ("353ba0e4-55b7-11e2-877f-002564c97630")
    @Override
    public Image getImage() {
        NameSpace base = getRelatedElement().getBase();
        if (base != null) {
            return ElementImageService.getImage(base);
        }
        return ElementImageService.getImage(getRelatedElement());
    }

    /**
     * Get the internal structure.
     * @return the internal structure.
     */
    @objid ("353ba0e9-55b7-11e2-877f-002564c97630")
    public GmInstanceInternalStructure getInternalStructure() {
        return this.internalStructure;
    }

    @objid ("353ba0ee-55b7-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        return getDisplayedStyle().getProperty(GmInstance.STRUCTURED_KEYS.getStyleKey(MetaKey.REPMODE));
    }

    @objid ("353ba0f5-55b7-11e2-877f-002564c97630")
    @Override
    public Instance getRelatedElement() {
        return (Instance) super.getRelatedElement();
    }

    @objid ("353ba0fc-55b7-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmInstancePrimaryNode.");
        switch (readVersion) {
            case 0: {
                read_0(in);
                break;
            }
            case 1: {
                read_1(in);
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

    @objid ("353ba102-55b7-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        super.refreshFromObModel();
        String oldLabel = this.header.getMainLabel();
        this.header.refreshFromObModel();
        firePropertyChange(PROPERTY_LABEL, oldLabel, this.header.getMainLabel());
        // forcing visual refresh in case Image changed
        firePropertyChange(PROPERTY_LAYOUTDATA, null, getLayoutData());
    }

    @objid ("353ba105-55b7-11e2-877f-002564c97630")
    @Override
    public List<GmNodeModel> getVisibleChildren() {
        // Returned result depends on current representation mode:
        List<GmNodeModel> ret;
        switch (this.getRepresentationMode()) {
            case IMAGE: {
                ret = new ArrayList<>(1);
                //ret.add(this.imageModeHeader);
                break;
            }
            case SIMPLE:
                return Arrays.asList((GmNodeModel)this.header);
            case STRUCTURED:
            default: {
                ret = super.getVisibleChildren();
                // Remove the header used for image mode.
                ret.remove(this.imageModeHeader);
                break;
            }
        }
        return ret;
    }

    @objid ("353ba10e-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmInstancePrimaryNode.", GmInstancePrimaryNode.MINOR_VERSION);
    }

    @objid ("353ba114-55b7-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        
        this.header = (GmInstanceHeader) getFirstChild(Constants.HEADER);
        this.slotGroup = (GmSlotGroup) getFirstChild(Constants.SLOTGROUP);
        GmGroup internalStructureGroup = (GmGroup) getFirstChild(Constants.INTERNALGROUP);
        GmFreeZone internalStructureZone = (GmFreeZone) getFirstChild(Constants.INTERNALZONE);
        this.imageModeHeader = (GmDefaultModelElementLabel) this.getChildren().get(4);
        
        // Migrate internal structure group/zone
        removeChild(internalStructureGroup);
        removeChild(internalStructureZone);
        
        this.internalStructure = new GmInstanceInternalStructure(getDiagram(), getRepresentedRef(), internalStructureZone, internalStructureGroup);
        this.internalStructure.setRoleInComposition(Constants.INTERNALSTRUCTURE);
        addChild(this.internalStructure, 2);
    }

    @objid ("353ba119-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @objid ("353d277c-55b7-11e2-877f-002564c97630")
    private void read_1(IDiagramReader in) {
        super.read(in);
        
        this.header = (GmInstanceHeader) getFirstChild(Constants.HEADER);
        this.slotGroup = (GmSlotGroup) getFirstChild(Constants.SLOTGROUP);
        this.internalStructure = (GmInstanceInternalStructure) getFirstChild(Constants.INTERNALSTRUCTURE);
        
        this.imageModeHeader = (GmDefaultModelElementLabel) this.getChildren().get(3);
    }

    /**
     * Constants to retrieve children node on deserialization.
     * 
     * @author cmarin
     */
    @objid ("353d2781-55b7-11e2-877f-002564c97630")
    private interface Constants {
        @objid ("353d2783-55b7-11e2-877f-002564c97630")
        public static final String HEADER = "header";

        @objid ("353d2785-55b7-11e2-877f-002564c97630")
        public static final String SLOTGROUP = "slots";

        @objid ("353d2787-55b7-11e2-877f-002564c97630")
        @Deprecated
        public static final String INTERNALZONE = "internalZone";

        @objid ("353d278a-55b7-11e2-877f-002564c97630")
        @Deprecated
        public static final String INTERNALGROUP = "internalGroup";

        @objid ("353d278d-55b7-11e2-877f-002564c97630")
        public static final String INTERNALSTRUCTURE = "InternalStructure";

    }

}
