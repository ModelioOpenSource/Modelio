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

package org.modelio.diagram.editor.bpmn.elements.bpmnendevent;

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
import org.modelio.metamodel.bpmn.events.BpmnEndEvent;
import org.modelio.metamodel.bpmn.gateways.BpmnComplexGateway;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents an {@link BpmnComplexGateway}.
 */
@objid ("60e20e75-55b6-11e2-877f-002564c97630")
public final class GmBpmnEndEventPrimaryNode extends GmNoStyleSimpleNode implements IImageableNode {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("60e20e7d-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("60e20e80-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Image Service
     */
    @objid ("5369722e-4666-4f4c-a0a5-90d421dfb1b1")
    private BpmnImageRefHolder imageHolder = new BpmnImageRefHolder();

    /**
     * Create a initial graphic node.
     * 
     * @param diagram The diagram
     * @param relatedRef The related element reference, may not be null.
     */
    @objid ("60e20e82-55b6-11e2-877f-002564c97630")
    public GmBpmnEndEventPrimaryNode(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
    }

    @objid ("60e20e8b-55b6-11e2-877f-002564c97630")
    @Override
    public BpmnEndEvent getRelatedElement() {
        return (BpmnEndEvent) super.getRelatedElement();
    }

    @objid ("60e20e92-55b6-11e2-877f-002564c97630")
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

    @objid ("60e20e95-55b6-11e2-877f-002564c97630")
    @Override
    public void obElementAdded(MObject movedEl) {
        refreshFromObModel();
    }

    @objid ("60e394f9-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return true;
    }

    @objid ("60e39501-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        return false;
    }

    /**
     * Get the parent model representation mode.
     * 
     * @return the parent representation mode or null if the node has still no parent.
     */
    @objid ("60e39509-55b6-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        final StyleKey repModeKey = GmBpmnEndEvent.STRUCTURED_KEYS.getStyleKey(MetaKey.REPMODE);
        return getDisplayedStyle().getProperty(repModeKey);
    }

    @objid ("60e39511-55b6-11e2-877f-002564c97630")
    @Override
    public Image getImage() {
        return ElementImageService.getImage(getRelatedElement());
    }

    @objid ("60e39516-55b6-11e2-877f-002564c97630")
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
    @objid ("60e3951d-55b6-11e2-877f-002564c97630")
    public GmBpmnEndEventPrimaryNode() {
        // for the serialization
    }

    @objid ("60e39520-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = GmAbstractObject.readMinorVersion(in, "GmBpmnEndEventPrimaryNode.");
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

    @objid ("60e39526-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        GmAbstractObject.writeMinorVersion(out, "GmBpmnEndEventPrimaryNode.", GmBpmnEndEventPrimaryNode.MINOR_VERSION);
    }

    @objid ("60e3952c-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("60e39531-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmBpmnEndEventPrimaryNode.MAJOR_VERSION;
    }

    @objid ("956f1acb-1f85-44a5-ae99-cd5109e5e92d")
    public Image getEventImage() {
        Image image = DiagramEditorBpmn.getImageRegistry().getImage(this.imageHolder.getImageRef(getRelatedElement()));
        if (image == null) {
            image = DiagramEditorBpmn.getImageRegistry().getImage(BpmnEndEvent.MNAME);
        }
        return image;
    }

}
