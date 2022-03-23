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
package org.modelio.uml.activitydiagram.editor.elements.datastore;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.diagram.elements.common.label.base.GmElementLabel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNoStyleCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.node.IImageableNode;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.platform.model.ui.swt.images.ElementImageService;
import org.modelio.uml.activitydiagram.editor.elements.activitynodeheader.GmActivityNodeHeader;
import org.modelio.uml.activitydiagram.editor.elements.datastore.v0._GmDataStore;
import org.modelio.uml.activitydiagram.editor.elements.objectnode.GmObjectNodeStateLabel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Primary Node for GmDataStore.
 * 
 * @author fpoyer
 */
@objid ("2a2c0395-55b6-11e2-877f-002564c97630")
public class GmDataStorePrimaryNode extends GmNoStyleCompositeNode implements IImageableNode {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("2a2c039b-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("2a2c039e-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Header
     */
    @objid ("2a2c03a0-55b6-11e2-877f-002564c97630")
    private GmActivityNodeHeader header;

    @objid ("d1c84b32-55c0-11e2-9337-002564c97630")
    private GmElementLabel objectNodeStateLabel;

    /**
     * Default constructor.
     * @param diagram the diagram in which this gm is unmasked.
     * @param ref a reference to the represented data store.
     */
    @objid ("2a2c03a5-55b6-11e2-877f-002564c97630")
    public  GmDataStorePrimaryNode(IGmDiagram diagram, MRef ref) {
        super(diagram, ref);
        this.header = new GmActivityNodeHeader(diagram, ref);
        this.header.setShowMetaclassIcon(true);
        super.addChild(this.header);
        this.objectNodeStateLabel = new GmObjectNodeStateLabel(diagram, ref);
        addChild(this.objectNodeStateLabel);
        
    }

    /**
     * Empty constructor, needed for serialisation.
     */
    @objid ("2a2c03ae-55b6-11e2-877f-002564c97630")
    public  GmDataStorePrimaryNode() {
        // empty constructor for the serialization
    }

    @objid ("2a2c03b1-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return true;
    }

    @objid ("2a2c03b9-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        return false;
    }

    @objid ("2a2d8a1d-55b6-11e2-877f-002564c97630")
    @Override
    public GmCompositeNode getCompositeFor(Class<? extends MObject> metaclass) {
        if (canCreate(metaclass))
            return this;
        // else
        return null;
    }

    /**
     * Get the stereotype image to display.
     * @return the stereotype image to display. Must not be <i>null</i>.
     */
    @objid ("2a2d8a27-55b6-11e2-877f-002564c97630")
    @Override
    public Image getImage() {
        return ElementImageService.getImage(getRelatedElement());
    }

    @objid ("2a2d8a2d-55b6-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        return (RepresentationMode) getDisplayedStyle().getProperty(GmDataStore.SIMPLEKEYS.getStyleKey(MetaKey.REPMODE));
    }

    @objid ("2a2d8a34-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmDataStorePrimaryNode.");
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

    @objid ("2a2d8a3a-55b6-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        super.refreshFromObModel();
        String oldLabel = this.header.getMainLabel();
        this.header.refreshFromObModel();
        firePropertyChange(PROPERTY_LABEL, oldLabel, this.header.getMainLabel());
        // forcing visual refresh in case Image changed
        firePropertyChange(PROPERTY_LAYOUTDATA, null, getLayoutData());
        
    }

    @objid ("2a2d8a3d-55b6-11e2-877f-002564c97630")
    @Override
    public List<GmNodeModel> getVisibleChildren() {
        // Returned result depends on current representation mode:
        List<GmNodeModel> ret;
        switch (this.getRepresentationMode()) {
        case USER_IMAGE:
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

    @objid ("2a2d8a46-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0.
        writeMinorVersion(out, "GmDataStorePrimaryNode.", Integer.valueOf(GmDataStorePrimaryNode.MINOR_VERSION));
        
    }

    @objid ("2a2d8a4c-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.header = (GmActivityNodeHeader) this.getChildren().get(0);
        this.objectNodeStateLabel = (GmElementLabel) this.getChildren().get(1);
        
    }

    @objid ("2a2d8a51-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    /**
     * Migration constructor.
     * @param oldVersionGm the instance to migrate from.
     */
    @objid ("2a2f10b9-55b6-11e2-877f-002564c97630")
     GmDataStorePrimaryNode(final _GmDataStore oldVersionGm) {
        super(oldVersionGm.getDiagram(), oldVersionGm.getRepresentedRef());
        this.header = oldVersionGm.getHeader();
        this.header.setShowMetaclassIcon(true);
        oldVersionGm.removeChild(this.header);
        super.addChild(this.header);
        
        this.objectNodeStateLabel = oldVersionGm.getObjectNodeStateLabel();
        oldVersionGm.removeChild(this.objectNodeStateLabel);
        addChild(this.objectNodeStateLabel);
        
    }

}
