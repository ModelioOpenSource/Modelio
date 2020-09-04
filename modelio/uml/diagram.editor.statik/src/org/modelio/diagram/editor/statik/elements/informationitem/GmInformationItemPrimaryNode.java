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

package org.modelio.diagram.editor.statik.elements.informationitem;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.core.ui.swt.images.ElementImageService;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNoStyleCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.node.IImageableNode;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.metamodel.uml.informationFlow.InformationItem;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents a {@link InformationItem}.
 * <p>
 * The GmDataType is composed of many zones and groups that are shown depending on the style properties.
 */
@objid ("351888a7-55b7-11e2-877f-002564c97630")
public class GmInformationItemPrimaryNode extends GmNoStyleCompositeNode implements IImageableNode {
    /**
     * Current version of this Gm.
     */
    @objid ("351888af-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 1;

    @objid ("351888b2-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Classifier header
     */
    @objid ("351888ad-55b7-11e2-877f-002564c97630")
    private GmInformationItemHeader header;

    /**
     * Constructor for deserialization only.
     */
    @objid ("351888b4-55b7-11e2-877f-002564c97630")
    public GmInformationItemPrimaryNode() {
        // Nothing to do.
    }

    /**
     * Creates a GmClass.
     * 
     * @param diagram The owner diagram.
     * @param relatedRef a reference to the element this GmModel is related to, must not be null.
     */
    @objid ("351888b7-55b7-11e2-877f-002564c97630")
    public GmInformationItemPrimaryNode(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
        
        this.header = new GmInformationItemHeader(diagram, relatedRef);
        this.header.setShowMetaclassIcon(true);
        
        super.addChild(this.header);
        
        styleChanged(getDisplayedStyle());
    }

    @objid ("351888c0-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return false;
    }

    @objid ("351a0f1a-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        return false;
    }

    @objid ("351a0f22-55b7-11e2-877f-002564c97630")
    @Override
    public GmCompositeNode getCompositeFor(Class<? extends MObject> metaclass) {
        return null;
    }

    @objid ("351a0f2c-55b7-11e2-877f-002564c97630")
    @Override
    public Image getImage() {
        return ElementImageService.getImage(getRelatedElement());
    }

    @objid ("351a0f31-55b7-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        return getDisplayedStyle().getProperty(GmInformationItem.STRUCTURED_KEYS.getStyleKey(MetaKey.REPMODE));
    }

    @objid ("351a0f38-55b7-11e2-877f-002564c97630")
    @Override
    public List<GmNodeModel> getVisibleChildren() {
        // Returned result depends on current representation mode:
        List<GmNodeModel> ret;
        switch (this.getRepresentationMode()) {
            case IMAGE: {
                ret = Collections.emptyList();
                break;
            }
            default: {
                ret = super.getVisibleChildren();
                break;
            }
        }
        return ret;
    }

    @objid ("351a0f41-55b7-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmInformationItemPrimaryNode.");
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

    @objid ("351a0f47-55b7-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        super.refreshFromObModel();
        
        final String oldLabel = this.header.getMainLabel();
        this.header.refreshFromObModel();
        
        firePropertyChange(PROPERTY_LABEL, oldLabel, this.header.getMainLabel());
        // forcing visual refresh in case Image changed
        firePropertyChange(PROPERTY_LAYOUTDATA, null, getLayoutData());
    }

    @objid ("351a0f4a-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmInformationItemPrimaryNode.", GmInformationItemPrimaryNode.MINOR_VERSION);
    }

    @objid ("351a0f50-55b7-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        
        final List<GmNodeModel> children = getChildren();
        
        this.header = (GmInformationItemHeader) children.get(0);
        
        GmNodeModel imageModeHeader = this.getChildren().get(1);
        imageModeHeader.delete();
    }

    @objid ("351a0f55-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @objid ("351a0f5a-55b7-11e2-877f-002564c97630")
    private void read_1(final IDiagramReader in) {
        super.read(in);
        
        final List<GmNodeModel> children = getChildren();
        
        this.header = (GmInformationItemHeader) children.get(0);
    }

}
