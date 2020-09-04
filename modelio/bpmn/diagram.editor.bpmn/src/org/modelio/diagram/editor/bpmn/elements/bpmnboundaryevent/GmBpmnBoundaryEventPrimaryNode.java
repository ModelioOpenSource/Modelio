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

package org.modelio.diagram.editor.bpmn.elements.bpmnboundaryevent;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.core.ui.swt.images.ElementImageService;
import org.modelio.diagram.editor.bpmn.editor.BpmnSharedImages.BpmnImageRefHolder;
import org.modelio.diagram.editor.bpmn.plugin.DiagramEditorBpmn;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.model.IEditableText;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNoStyleSimpleNode;
import org.modelio.diagram.elements.core.node.IImageableNode;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.bpmn.events.BpmnBoundaryEvent;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents an {@link BpmnBoundaryEvent}.
 */
@objid ("608807a4-55b6-11e2-877f-002564c97630")
public final class GmBpmnBoundaryEventPrimaryNode extends GmNoStyleSimpleNode implements IImageableNode {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("608807ac-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("608807af-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Image Reference
     */
    @objid ("7216ae20-955d-4b3b-bd99-bf8c3ebf2a1b")
    private BpmnImageRefHolder imageHolder = new BpmnImageRefHolder();

    /**
     * Create a initial graphic node.
     * 
     * @param diagram The diagram
     * @param relatedRef The related element reference, may not be null.
     */
    @objid ("608807b1-55b6-11e2-877f-002564c97630")
    public GmBpmnBoundaryEventPrimaryNode(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
    }

    @objid ("608807ba-55b6-11e2-877f-002564c97630")
    @Override
    public BpmnBoundaryEvent getRelatedElement() {
        return (BpmnBoundaryEvent) super.getRelatedElement();
    }

    @objid ("608807c1-55b6-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        if (getRelatedElement() != null) {
            firePropertyChange(IGmObject.PROPERTY_LABEL, null, getRelatedElement().getName());
        }
        
        if (this.imageHolder.updateImageRef(getRelatedElement())) {
            GmCompositeNode gm_parent = getParentNode();
            if (gm_parent != null) {
                gm_parent.removeChild(this);
                gm_parent.addChild(this);
            }
        }
        // forcing visual refresh in case Image changed
        firePropertyChange(IGmObject.PROPERTY_LAYOUTDATA, null, getLayoutData());
    }

    @objid ("60898e1a-55b6-11e2-877f-002564c97630")
    @Override
    public void obElementAdded(MObject movedEl) {
        super.obElementAdded(movedEl);
        refreshFromObModel();
    }

    @objid ("60898e20-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return false;
    }

    @objid ("60898e28-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        return false;
    }

    /**
     * Get the parent model representation mode.
     * 
     * @return the parent representation mode or null if the node has still no parent.
     */
    @objid ("60898e30-55b6-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        final StyleKey repModeKey = GmBpmnBoundaryEvent.STRUCTURED_KEYS.getStyleKey(MetaKey.REPMODE);
        return getDisplayedStyle().getProperty(repModeKey);
    }

    @objid ("60898e38-55b6-11e2-877f-002564c97630")
    @Override
    public Image getImage() {
        return ElementImageService.getImage(getRelatedElement());
    }

    @objid ("60898e3d-55b6-11e2-877f-002564c97630")
    @Override
    public IEditableText getEditableText() {
        return new IEditableText() {
                
                            @Override
                            public String getText() {
                                return getRelatedElement().getName();
                            }
                
                            @Override
                            public void setText(String text) {
                                getRelatedElement().setName(text);
                            }
                
                        };
    }

    /**
     * Constructor for deserialization only.
     */
    @objid ("60898e44-55b6-11e2-877f-002564c97630")
    public GmBpmnBoundaryEventPrimaryNode() {
        // for the serialization
    }

    @objid ("60898e47-55b6-11e2-877f-002564c97630")
    @Override
    public void obElementsUpdated() {
        super.obElementsUpdated();
        refreshFromObModel();
    }

    @objid ("60898e4a-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = GmAbstractObject.readMinorVersion(in, "GmBpmnBoundaryEventPrimaryNode.");
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

    @objid ("60898e50-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        GmAbstractObject.writeMinorVersion(out, "GmBpmnBoundaryEventPrimaryNode.", GmBpmnBoundaryEventPrimaryNode.MINOR_VERSION);
    }

    @objid ("60898e56-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("608b14b9-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmBpmnBoundaryEventPrimaryNode.MAJOR_VERSION;
    }

    @objid ("4553c929-1fe4-4101-b438-4e97c055b182")
    public Image getEventImage() {
        Image image = DiagramEditorBpmn.getImageRegistry().getImage(this.imageHolder.getImageRef(getRelatedElement()));
        if (image == null) {
            image = DiagramEditorBpmn.getImageRegistry().getImage(BpmnBoundaryEvent.MNAME);
        }
        return image;
    }

}
