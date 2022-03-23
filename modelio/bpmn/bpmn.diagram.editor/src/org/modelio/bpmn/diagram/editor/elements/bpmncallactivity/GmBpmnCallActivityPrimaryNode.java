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
package org.modelio.bpmn.diagram.editor.elements.bpmncallactivity;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.bpmn.diagram.editor.elements.bpmnnodefooter.GmBpmnNodeFooter;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
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
import org.modelio.metamodel.bpmn.activities.BpmnCallActivity;
import org.modelio.metamodel.bpmn.activities.BpmnMultiInstanceLoopCharacteristics;
import org.modelio.metamodel.bpmn.activities.BpmnStandardLoopCharacteristics;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Called;
import org.modelio.platform.model.ui.swt.images.ElementImageService;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * This class represents the Gm of a CallActivity.
 */
@objid ("609bddb3-55b6-11e2-877f-002564c97630")
public class GmBpmnCallActivityPrimaryNode extends GmNoStyleCompositeNode implements IImageableNode {
    @objid ("609bddbf-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Current version of this Gm.
     */
    @objid ("609bddbc-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 1;

    @objid ("c52d53ce-59a6-11e2-ae45-002564c97630")
    private GmBpmnNodeFooter footer;

    /**
     * Header
     */
    @objid ("609bddb9-55b6-11e2-877f-002564c97630")
    private GmBpmnCallActivityHeader header;

    /**
     * Default constructor.
     * @param diagram the diagram in which this gm is unmasked.
     * @param relatedRef ref
     */
    @objid ("609bddc1-55b6-11e2-877f-002564c97630")
    public  GmBpmnCallActivityPrimaryNode(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
        
        this.header = new GmBpmnCallActivityHeader(diagram, relatedRef, true);
        this.footer = new GmBpmnNodeFooter(diagram, relatedRef);
        this.header.setShowMetaclassIcon(false);
        
        super.addChild(this.header);
        super.addChild(this.footer);
        
    }

    /**
     * Empty constructor, needed for serialisation.
     */
    @objid ("609bddca-55b6-11e2-877f-002564c97630")
    public  GmBpmnCallActivityPrimaryNode() {
        // empty constructor for the serialization
    }

    @objid ("609bddcd-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return false;
    }

    @objid ("609bddd5-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        return false;
    }

    @objid ("609bdddd-55b6-11e2-877f-002564c97630")
    @Override
    public GmCompositeNode getCompositeFor(Class<? extends MObject> metaclass) {
        if (canCreate(metaclass)) {
            return this;
        }
        // else
        return null;
    }

    @objid ("609d643d-55b6-11e2-877f-002564c97630")
    @Override
    public Image getImage() {
        return ElementImageService.getImage(getRelatedElement());
    }

    @objid ("609d6476-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmBpmnCallActivityPrimaryNode.MAJOR_VERSION;
    }

    @objid ("609d644f-55b6-11e2-877f-002564c97630")
    @Override
    public BpmnCallActivity getRelatedElement() {
        return (BpmnCallActivity) super.getRelatedElement();
    }

    @objid ("609d6442-55b6-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        final StyleKey repModeKey = GmBpmnCallActivity.STRUCTURED_KEYS.getStyleKey(MetaKey.REPMODE);
        return getDisplayedStyle().getProperty(repModeKey);
    }

    @objid ("609d6462-55b6-11e2-877f-002564c97630")
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

    @objid ("609d6449-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmBpmnCallActivityPrimaryNode.");
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

    @objid ("609d6456-55b6-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        super.refreshFromObModel();
        String oldLabel = this.header.getMainLabel();
        this.header.refreshFromObModel();
        firePropertyChange(IGmObject.PROPERTY_LABEL, oldLabel, this.header.getMainLabel());
        
        BpmnCallActivity relatedElement = getRelatedElement();
        if (relatedElement != null) {
            if (relatedElement.isIsForCompensation()) {
                this.footer.setCompensation(true);
            } else {
                this.footer.setCompensation(false);
            }
        
            if (relatedElement.getLoopCharacteristics() instanceof BpmnStandardLoopCharacteristics) {
                this.footer.setLoop(true);
            } else {
                this.footer.setLoop(false);
            }
        
            if (relatedElement.getLoopCharacteristics() instanceof BpmnMultiInstanceLoopCharacteristics) {
                BpmnMultiInstanceLoopCharacteristics loop = (BpmnMultiInstanceLoopCharacteristics) relatedElement
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
        
            ModelElement called = Called.getTarget(relatedElement);
            if (called instanceof BpmnProcess) {
                this.footer.setEmptySubProcess(true);
            } else {
                this.footer.setEmptySubProcess(false);
            }
        
        }
        this.footer.refreshFromObModel();
        // forcing visual refresh in case Image changed
        firePropertyChange(IGmObject.PROPERTY_LAYOUTDATA, null, getLayoutData());
        
    }

    @objid ("609d646b-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmBpmnCallActivityPrimaryNode.", GmBpmnCallActivityPrimaryNode.MINOR_VERSION);
        
    }

    @objid ("609d6471-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.header = (GmBpmnCallActivityHeader) this.getChildren().get(0);
        this.footer = (GmBpmnNodeFooter) this.getChildren().get(1);
        
        GmDefaultModelElementLabel imageModeHeader = (GmDefaultModelElementLabel) this.getChildren().get(2);
        imageModeHeader.delete();
        
    }

    @objid ("609eead9-55b6-11e2-877f-002564c97630")
    private void read_1(final IDiagramReader in) {
        super.read(in);
        this.header = (GmBpmnCallActivityHeader) this.getChildren().get(0);
        this.footer = (GmBpmnNodeFooter) this.getChildren().get(1);
        
    }

}
