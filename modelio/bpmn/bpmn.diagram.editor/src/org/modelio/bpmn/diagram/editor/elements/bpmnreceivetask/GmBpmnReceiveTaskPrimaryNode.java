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
package org.modelio.bpmn.diagram.editor.elements.bpmnreceivetask;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.bpmn.diagram.editor.editor.BpmnSharedImages;
import org.modelio.bpmn.diagram.editor.elements.bpmnnodefooter.GmBpmnNodeFooter;
import org.modelio.bpmn.diagram.editor.elements.bpmnnodeheader.GmBpmnNodeHeader;
import org.modelio.bpmn.diagram.editor.plugin.DiagramEditorBpmn;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNoStyleCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.node.IImageableNode;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.metamodel.bpmn.activities.BpmnMultiInstanceLoopCharacteristics;
import org.modelio.metamodel.bpmn.activities.BpmnReceiveTask;
import org.modelio.metamodel.bpmn.activities.BpmnStandardLoopCharacteristics;
import org.modelio.platform.model.ui.swt.images.ElementImageService;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * This class represents the Gm of a BusinessRuleTask.
 */
@objid ("619001b7-55b6-11e2-877f-002564c97630")
public class GmBpmnReceiveTaskPrimaryNode extends GmNoStyleCompositeNode implements IImageableNode {
    @objid ("619001c3-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Current version of this Gm.
     */
    @objid ("619001c0-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 2;

    @objid ("619001bf-55b6-11e2-877f-002564c97630")
    private GmBpmnNodeFooter footer;

    /**
     * Header
     */
    @objid ("619001bd-55b6-11e2-877f-002564c97630")
    private GmBpmnNodeHeader header;

    /**
     * Default constructor.
     * @param diagram the diagram in which this gm is unmasked.
     * @param relatedRef ref
     */
    @objid ("619001c5-55b6-11e2-877f-002564c97630")
    public  GmBpmnReceiveTaskPrimaryNode(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
        this.header = new GmBpmnNodeHeader(diagram, relatedRef, false);
        this.footer = new GmBpmnNodeFooter(diagram, relatedRef);
        final GmBpmnReceiveTaskTypeLabel typeLabel = new GmBpmnReceiveTaskTypeLabel(diagram, relatedRef);
        
        super.addChild(this.header);
        super.addChild(typeLabel);
        super.addChild(this.footer);
        
        List<Image> images = new ArrayList<>();
        images.add(DiagramEditorBpmn.getImageRegistry().getImage(BpmnSharedImages.RECEIVETASKHEADER));
        this.header.addHeaderImage(images);
        
    }

    /**
     * Empty constructor, needed for serialisation.
     */
    @objid ("619001ce-55b6-11e2-877f-002564c97630")
    public  GmBpmnReceiveTaskPrimaryNode() {
        // empty constructor for the serialization
    }

    @objid ("619001d1-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return false;
    }

    @objid ("619001d9-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        return false;
    }

    @objid ("619001e1-55b6-11e2-877f-002564c97630")
    @Override
    public GmCompositeNode getCompositeFor(Class<? extends MObject> metaclass) {
        if (canCreate(metaclass))
            return this;
        // else
        return null;
    }

    @objid ("61918841-55b6-11e2-877f-002564c97630")
    @Override
    public Image getImage() {
        return ElementImageService.getImage(getRelatedElement());
    }

    @objid ("61930ed9-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @objid ("61918856-55b6-11e2-877f-002564c97630")
    @Override
    public BpmnReceiveTask getRelatedElement() {
        return (BpmnReceiveTask) super.getRelatedElement();
    }

    @objid ("61918846-55b6-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        final StyleKey repModeKey = GmBpmnReceiveTask.STRUCTURED_KEYS.getStyleKey(MetaKey.REPMODE);
        return getDisplayedStyle().getProperty(repModeKey);
    }

    @objid ("61918866-55b6-11e2-877f-002564c97630")
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

    @objid ("6191884d-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmBpmnReceiveTaskPrimaryNode.");
        switch (readVersion) {
        case 0: {
            read_0(in);
            break;
        }
        case 1: {
            read_1(in);
            break;
        }
        case 2: {
            read_2(in);
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

    @objid ("61918853-55b6-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        super.refreshFromObModel();
        String oldLabel = this.header.getMainLabel();
        this.header.refreshFromObModel();
        firePropertyChange(PROPERTY_LABEL, oldLabel, this.header.getMainLabel());
        
        List<Image> images = new ArrayList<>();
        images.add(DiagramEditorBpmn.getImageRegistry().getImage(BpmnSharedImages.RECEIVETASKHEADER));
        this.header.addHeaderImage(images);
        
        if (this.getRelatedElement() != null) {
            if (this.getRelatedElement().isIsForCompensation()) {
                this.footer.setCompensation(true);
            } else {
                this.footer.setCompensation(false);
            }
        
            if (this.getRelatedElement().getLoopCharacteristics() instanceof BpmnStandardLoopCharacteristics) {
                this.footer.setLoop(true);
            } else {
                this.footer.setLoop(false);
            }
        
            if (this.getRelatedElement().getLoopCharacteristics() instanceof BpmnMultiInstanceLoopCharacteristics) {
                BpmnMultiInstanceLoopCharacteristics loop = (BpmnMultiInstanceLoopCharacteristics) this.getRelatedElement()
                        .getLoopCharacteristics();
                if (loop.isIsSequencial()) {
                    this.footer.setParallel(false);
                    this.footer.setSequential(true);
                } else {
                    this.footer.setParallel(true);
                    this.footer.setSequential(false);
                }
            } else {
                this.footer.setParallel(false);
                this.footer.setSequential(false);
            }
        }
        
        this.footer.refreshFromObModel();
        // forcing visual refresh in case Image changed
        firePropertyChange(PROPERTY_LAYOUTDATA, null, getLayoutData());
        
    }

    @objid ("6191886f-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmBpmnReceiveTaskPrimaryNode.", MINOR_VERSION);
        
    }

    @objid ("61918875-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.header = (GmBpmnNodeHeader) this.getChildren().get(0);
        this.footer = (GmBpmnNodeFooter) this.getChildren().get(1);
        
        // V0 to V1 - delete image label
        GmDefaultModelElementLabel imageModeHeader = (GmDefaultModelElementLabel) this.getChildren().get(2);
        imageModeHeader.delete();
        
        // V1 to V2 - add type label
        final GmBpmnReceiveTaskTypeLabel typeLabel = new GmBpmnReceiveTaskTypeLabel(getDiagram(), getRepresentedRef());
        removeChild(this.footer);
        addChild(typeLabel);
        addChild(this.footer);
        
    }

    @objid ("61930ede-55b6-11e2-877f-002564c97630")
    private void read_1(final IDiagramReader in) {
        super.read(in);
        this.header = (GmBpmnNodeHeader) this.getChildren().get(0);
        this.footer = (GmBpmnNodeFooter) this.getChildren().get(1);
        
        // V1 to V2 - add type label
        final GmBpmnReceiveTaskTypeLabel typeLabel = new GmBpmnReceiveTaskTypeLabel(getDiagram(), getRepresentedRef());
        removeChild(this.footer);
        addChild(typeLabel);
        addChild(this.footer);
        
    }

    @objid ("6805d7fb-e3f6-4ec4-b6e1-c99b7ceffbf2")
    private void read_2(final IDiagramReader in) {
        super.read(in);
        this.header = (GmBpmnNodeHeader) this.getChildren().get(0);
        this.footer = (GmBpmnNodeFooter) this.getChildren().get(2);
        
    }

}
