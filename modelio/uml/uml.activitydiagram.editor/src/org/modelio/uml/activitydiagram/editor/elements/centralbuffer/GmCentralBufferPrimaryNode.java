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
import org.modelio.uml.activitydiagram.editor.elements.centralbuffer.v0._GmCentralBuffer;
import org.modelio.uml.activitydiagram.editor.elements.objectnode.GmObjectNodeStateLabel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * PrimaryNode for GmCentralBuffer.
 * 
 * @author fpoyer
 */
@objid ("29e75972-55b6-11e2-877f-002564c97630")
public class GmCentralBufferPrimaryNode extends GmNoStyleCompositeNode implements IImageableNode {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("29e75978-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("29e7597b-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Header
     */
    @objid ("29e7597d-55b6-11e2-877f-002564c97630")
    private GmActivityNodeHeader header;

    @objid ("d1a09ee9-55c0-11e2-9337-002564c97630")
    private GmElementLabel objectNodeStateLabel;

    /**
     * Default constructor.
     * 
     * @param diagram the diagram in which this gm is unmasked.
     * @param ref a reference to the represented central buffer.
     */
    @objid ("29e75982-55b6-11e2-877f-002564c97630")
    public GmCentralBufferPrimaryNode(IGmDiagram diagram, MRef ref) {
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
    @objid ("29e7598b-55b6-11e2-877f-002564c97630")
    public GmCentralBufferPrimaryNode() {
        // empty constructor for the serialization
    }

    @objid ("29e7598e-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return true;
    }

    @objid ("29e75996-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        return false;
    }

    @objid ("29e8dffe-55b6-11e2-877f-002564c97630")
    @Override
    public GmCompositeNode getCompositeFor(Class<? extends MObject> metaclass) {
        if (canCreate(metaclass))
            return this;
        // else
        return null;
    }

    /**
     * Get the stereotype image to display.
     * 
     * @return the stereotype image to display. Must not be <i>null</i>.
     */
    @objid ("29e8e008-55b6-11e2-877f-002564c97630")
    @Override
    public Image getImage() {
        return ElementImageService.getImage(getRelatedElement());
    }

    @objid ("29e8e00e-55b6-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        return (RepresentationMode) getDisplayedStyle().getProperty(GmCentralBuffer.SIMPLEKEYS.getStyleKey(MetaKey.REPMODE));
    }

    @objid ("29e8e015-55b6-11e2-877f-002564c97630")
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

    @objid ("29e8e01b-55b6-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        super.refreshFromObModel();
        String oldLabel = this.header.getMainLabel();
        this.header.refreshFromObModel();
        firePropertyChange(PROPERTY_LABEL, oldLabel, this.header.getMainLabel());
        // forcing visual refresh in case Image changed
        firePropertyChange(PROPERTY_LAYOUTDATA, null, getLayoutData());
    }

    @objid ("29e8e01e-55b6-11e2-877f-002564c97630")
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

    @objid ("29e8e027-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmCentralBuffer.", GmCentralBufferPrimaryNode.MINOR_VERSION);
    }

    @objid ("29e8e02d-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.header = (GmActivityNodeHeader) this.getChildren().get(0);
        this.objectNodeStateLabel = (GmElementLabel) this.getChildren().get(1);
    }

    @objid ("29e8e032-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    /**
     * Migration constructor.
     * 
     * @param oldVersionGm the instance to migrate from.
     */
    @objid ("29ea6699-55b6-11e2-877f-002564c97630")
    GmCentralBufferPrimaryNode(final _GmCentralBuffer oldVersionGm) {
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
