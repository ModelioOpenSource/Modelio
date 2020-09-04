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

package org.modelio.diagram.editor.communication.elements.communicationnode;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.core.ui.swt.images.ElementImageService;
import org.modelio.diagram.editor.communication.elements.communicationnode.v0._GmCommunicationNode;
import org.modelio.diagram.elements.common.header.GmModelElementHeader;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNoStyleCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.node.IImageableNode;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Primary Node for Communication Node.
 * 
 * @author fpoyer
 */
@objid ("7a5a7155-55b6-11e2-877f-002564c97630")
public class GmCommunicationNodePrimaryNode extends GmNoStyleCompositeNode implements IImageableNode {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("7a5bf7bc-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("7a5bf7bf-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Header
     */
    @objid ("9c923419-55c1-11e2-9337-002564c97630")
    private GmModelElementHeader header;

    /**
     * Default constructor.
     * 
     * @param diagram the diagram in which this gm is unmasked.
     * @param ref a reference to the represented object node.
     */
    @objid ("7a5bf7c1-55b6-11e2-877f-002564c97630")
    public GmCommunicationNodePrimaryNode(IGmDiagram diagram, MRef ref) {
        super(diagram, ref);
        this.header = new GmCommunicationNodeHeader(diagram, ref);
        this.header.setShowMetaclassIcon(true);
        super.addChild(this.header);
    }

    /**
     * Empty constructor, needed for serialization.
     */
    @objid ("7a5bf7ca-55b6-11e2-877f-002564c97630")
    public GmCommunicationNodePrimaryNode() {
        // empty constructor for the serialization
    }

    @objid ("7a5bf7cd-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return true;
    }

    @objid ("7a5bf7d5-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        return false;
    }

    @objid ("7a5bf7dd-55b6-11e2-877f-002564c97630")
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
    @objid ("7a5bf7e7-55b6-11e2-877f-002564c97630")
    @Override
    public Image getImage() {
        return ElementImageService.getImage(getRelatedElement());
    }

    @objid ("7a5bf7ed-55b6-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        return (RepresentationMode) getDisplayedStyle().getProperty(GmCommunicationNode.SIMPLEKEYS.getStyleKey(MetaKey.REPMODE));
    }

    @objid ("7a5bf7f4-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmCommunicationNodePrimaryNode.");
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

    @objid ("7a5d7e59-55b6-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        super.refreshFromObModel();
        // forcing visual refresh in case Image changed
        firePropertyChange(PROPERTY_LAYOUTDATA, null, getLayoutData());
    }

    @objid ("7a5d7e5c-55b6-11e2-877f-002564c97630")
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

    @objid ("7a5d7e65-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0.
        writeMinorVersion(out, "GmCommunicationNodePrimaryNode.", Integer.valueOf(GmCommunicationNodePrimaryNode.MINOR_VERSION));
    }

    @objid ("7a5d7e6b-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.header = (GmModelElementHeader) this.getChildren().get(0);
    }

    @objid ("7a5d7e70-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    /**
     * Migration constructor.
     * 
     * @param oldVersionGm the instance to migrate from.
     */
    @objid ("7a5d7e75-55b6-11e2-877f-002564c97630")
    GmCommunicationNodePrimaryNode(final _GmCommunicationNode oldVersionGm) {
        super(oldVersionGm.getDiagram(), oldVersionGm.getRepresentedRef());
        this.header = oldVersionGm.getHeader();
        this.header.setShowMetaclassIcon(true);
        oldVersionGm.removeChild(this.header);
        super.addChild(this.header);
    }

}
