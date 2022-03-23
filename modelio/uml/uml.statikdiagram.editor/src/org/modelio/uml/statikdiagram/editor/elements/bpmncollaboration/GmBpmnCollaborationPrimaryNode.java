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
package org.modelio.uml.statikdiagram.editor.elements.bpmncollaboration;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.diagram.elements.common.header.GmDefaultModelElementHeader;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNoStyleCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.node.IImageableNode;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.platform.model.ui.swt.images.ElementImageService;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * This class represents the Gm of a BpmnCollaboration.
 */
@objid ("76390ea4-49e6-41b7-99f2-be88b29fd313")
public class GmBpmnCollaborationPrimaryNode extends GmNoStyleCompositeNode implements IImageableNode {
    /**
     * Current version of this Gm.
     */
    @objid ("9dcfe5e8-b0aa-4db2-b552-8a2dc8a9c840")
    private static final int MINOR_VERSION = 1;

    @objid ("54834e3b-6e15-4725-b0ec-7d161f31f0a2")
    private static final int MAJOR_VERSION = 0;

    /**
     * Header
     */
    @objid ("952107fb-7797-4248-b62a-37187287fec0")
    private GmDefaultModelElementHeader header;

    /**
     * Default constructor.
     * @param diagram the diagram in which this gm is unmasked.
     * @param relatedRef a reference to the represented CallBehaviorAction
     */
    @objid ("98fe5fcb-fd99-4feb-9adc-a3849efa167f")
    public  GmBpmnCollaborationPrimaryNode(final IGmDiagram diagram, final MRef relatedRef) {
        super(diagram, relatedRef);
        this.header = new GmDefaultModelElementHeader(diagram, relatedRef);
        this.header.setShowMetaclassIcon(true);
        addChild(this.header);
        
    }

    /**
     * Empty constructor needed for the serialization.
     */
    @objid ("ab26e5b4-1277-4b90-abf2-a6a14ef2dc5f")
    public  GmBpmnCollaborationPrimaryNode() {
        // empty constructor for the serialization
    }

    @objid ("6aef422e-13d0-4c81-abce-415428325401")
    @Override
    public boolean canCreate(final Class<? extends MObject> type) {
        return false;
    }

    @objid ("aeb76d7e-afff-4f9c-b078-5cbe64e944ae")
    @Override
    public boolean canUnmask(final MObject el) {
        return false;
    }

    @objid ("1d41c521-f639-4c6e-adec-2dcc66fadcd6")
    @Override
    public GmCompositeNode getCompositeFor(final Class<? extends MObject> metaclass) {
        return this;
    }

    @objid ("bedf7c43-a5fa-4d93-8a9e-524ba44bd496")
    @Override
    public Image getImage() {
        return ElementImageService.getImage(getRelatedElement());
    }

    @objid ("3535b9e9-8e32-45be-8b0e-b02b36879e67")
    @Override
    public RepresentationMode getRepresentationMode() {
        final StyleKey repModeKey = GmBpmnCollaboration.STRUCTURED_KEYS.getStyleKey(MetaKey.REPMODE);
        return getDisplayedStyle().getProperty(repModeKey);
    }

    @objid ("002a845f-fb9e-475f-9f5c-e9a07253a14e")
    @Override
    public List<GmNodeModel> getVisibleChildren() {
        // Returned result depends on current representation mode:
        List<GmNodeModel> ret;
        switch (getRepresentationMode()) {
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

    @objid ("3e5f56b9-f422-4ceb-8e18-c16a6c0979e3")
    @Override
    public void read(final IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = GmAbstractObject.readMinorVersion(in, "GmBpmnCollaborationPrimaryNode.");
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

    @objid ("a90b4f5f-58dc-42a7-a7d0-2cdaf33865b4")
    @Override
    public void refreshFromObModel() {
        super.refreshFromObModel(); // forcing visual refresh in case Image changed
        firePropertyChange(IGmObject.PROPERTY_LAYOUTDATA, null, getLayoutData());
        
    }

    @objid ("3b26b1ec-4516-4461-857e-6d6f3c9ddeda")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        GmAbstractObject.writeMinorVersion(out, "GmBpmnCollaborationPrimaryNode.", GmBpmnCollaborationPrimaryNode.MINOR_VERSION);
        
    }

    @objid ("ecbd870c-72e8-4da1-a236-34db2817e665")
    private void read_0(final IDiagramReader in) {
        super.read(in);
        
        int i = 0;
        
        this.header = (GmDefaultModelElementHeader) this.getChildren().get(i++);
        GmNodeModel imageModeHeader = this.getChildren().get(i++);
        imageModeHeader.delete();
        
    }

    @objid ("919b1136-453f-438e-8d07-6d1a8dba0b9a")
    @Override
    public int getMajorVersion() {
        return GmBpmnCollaborationPrimaryNode.MAJOR_VERSION;
    }

    @objid ("24fa4484-f3c3-4952-808f-8edf2a342fbe")
    private void read_1(final IDiagramReader in) {
        super.read(in);
        
        this.header = (GmDefaultModelElementHeader) this.getChildren().get(0);
        
    }

}
