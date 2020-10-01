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

package org.modelio.bpmn.diagram.editor.elements.bpmnadhocsubprocess;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.bpmn.diagram.editor.elements.bpmnnodefooter.GmBpmnNodeFooter;
import org.modelio.bpmn.diagram.editor.elements.bpmnnodeheader.GmBpmnNodeHeader;
import org.modelio.bpmn.diagram.editor.elements.bpmnsubprocess.GmBpmnSubProcessPrimaryNode;
import org.modelio.bpmn.diagram.editor.elements.bpmnsubprocess.content.GmBpmnSubProcessContent;
import org.modelio.bpmn.diagram.editor.elements.style.GmBpmnSubProcessStructuredStyleKeys;
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
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.bpmn.activities.BpmnAdHocSubProcess;
import org.modelio.metamodel.bpmn.activities.BpmnMultiInstanceLoopCharacteristics;
import org.modelio.metamodel.bpmn.activities.BpmnStandardLoopCharacteristics;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnSubProcessDiagram;
import org.modelio.metamodel.bpmn.events.BpmnBoundaryEvent;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;
import org.modelio.platform.model.ui.swt.images.ElementImageService;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * This class represents the Gm of a SubProcess.
 */
@objid ("607edfe1-55b6-11e2-877f-002564c97630")
public class GmBpmnAdHocSubProcessPrimaryNode extends GmNoStyleCompositeNode implements IImageableNode {
    /**
     * Current version of this Gm.
     */
    @objid ("607edfeb-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 1;

    @objid ("607edfee-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Header
     */
    @objid ("c545218d-59a6-11e2-ae45-002564c97630")
    private GmBpmnNodeHeader header;

    @objid ("c545218f-59a6-11e2-ae45-002564c97630")
    private GmBpmnNodeFooter footer;

    @objid ("c5452190-59a6-11e2-ae45-002564c97630")
    private GmBpmnSubProcessContent body;

    /**
     * Default constructor.
     * 
     * @param diagram the diagram in which this gm is unmasked.
     * @param relatedRef ref
     */
    @objid ("607edff0-55b6-11e2-877f-002564c97630")
    public GmBpmnAdHocSubProcessPrimaryNode(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
        this.header = new GmBpmnNodeHeader(diagram, relatedRef, false);
        this.footer = new GmBpmnNodeFooter(diagram, relatedRef);
        
        this.header.setRoleInComposition(GmBpmnSubProcessPrimaryNode.ROLE_HEADER);
        this.footer.setRoleInComposition(GmBpmnSubProcessPrimaryNode.ROLE_FOOTER);
        
        super.addChild(this.header);
        
        this.body = createBody();
        if (this.body != null) {
            super.addChild(this.body);
        }
        
        super.addChild(this.footer);
        
        this.footer.setAdHoc(true);
    }

    @objid ("607edff9-55b6-11e2-877f-002564c97630")
    @Override
    public void addChild(GmNodeModel child) {
        if (child.getRelatedElement() instanceof BpmnBaseElement && ((BpmnBaseElement) child.getRelatedElement()).getCompositionOwner().equals(getRelatedElement())) {
            this.body.addChild(child);
        } else {
            super.addChild(child);
        }
    }

    /**
     * Empty constructor, needed for serialisation.
     */
    @objid ("607edfff-55b6-11e2-877f-002564c97630")
    public GmBpmnAdHocSubProcessPrimaryNode() {
        // empty constructor for the serialization
    }

    @objid ("607ee002-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return this.body != null && this.body.isVisible() && BpmnBaseElement.class.isAssignableFrom(type) && !BpmnBoundaryEvent.class.isAssignableFrom(type);
    }

    @objid ("60806660-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        return el instanceof BpmnBaseElement && ((BpmnBaseElement) el).getCompositionOwner().equals(getRelatedElement());
    }

    @objid ("60806668-55b6-11e2-877f-002564c97630")
    @Override
    public GmCompositeNode getCompositeFor(Class<? extends MObject> metaclass) {
        if (canCreate(metaclass)) {
            return this.body.getCompositeFor(metaclass);
        }
        return null;
    }

    @objid ("60806672-55b6-11e2-877f-002564c97630")
    @Override
    public Image getImage() {
        return ElementImageService.getImage(getRelatedElement());
    }

    @objid ("60806677-55b6-11e2-877f-002564c97630")
    @Override
    public BpmnAdHocSubProcess getRelatedElement() {
        return (BpmnAdHocSubProcess) super.getRelatedElement();
    }

    @objid ("6080667e-55b6-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        final StyleKey repModeKey = GmBpmnAdHocSubProcess.STRUCTURED_KEYS.getStyleKey(MetaKey.REPMODE);
        return getDisplayedStyle().getProperty(repModeKey);
    }

    @objid ("60806685-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmBpmnAdHocSubProcessPrimaryNode.");
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

    @objid ("6080668b-55b6-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        super.refreshFromObModel();
        
        if (this.body == null) {
            this.body = createBody();
            if (this.body != null) {
                super.addChild(this.body);
            }
        }
        
        String oldLabel = this.header.getMainLabel();
        this.header.refreshFromObModel();
        if (getRelatedElement() != null && getRelatedElement().isValid()) {
            firePropertyChange(IGmObject.PROPERTY_LABEL, oldLabel, this.header.getMainLabel());
            firePropertyChange(IGmObject.PROPERTY_LAYOUTDATA, null, getRelatedElement().isTriggeredByEvent());
        
            if (getRelatedElement().isIsForCompensation()) {
                this.footer.setCompensation(true);
            } else {
                this.footer.setCompensation(false);
            }
        
            if (getRelatedElement().getLoopCharacteristics() instanceof BpmnStandardLoopCharacteristics) {
                this.footer.setLoop(true);
            } else {
                this.footer.setLoop(false);
            }
        
            if (getRelatedElement().getLoopCharacteristics() instanceof BpmnMultiInstanceLoopCharacteristics) {
                BpmnMultiInstanceLoopCharacteristics loop = (BpmnMultiInstanceLoopCharacteristics) getRelatedElement().getLoopCharacteristics();
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
        firePropertyChange(IGmObject.PROPERTY_LAYOUTDATA, null, getLayoutData());
    }

    @objid ("6080668e-55b6-11e2-877f-002564c97630")
    @Override
    public void obElementAdded(MObject movedEl) {
        super.obElementAdded(movedEl);
        refreshFromObModel();
    }

    @objid ("60806694-55b6-11e2-877f-002564c97630")
    @Override
    public void obElementsUpdated() {
        super.obElementsUpdated();
        refreshFromObModel();
    }

    /**
     * @return the structured inner zone.
     */
    @objid ("60806697-55b6-11e2-877f-002564c97630")
    public GmCompositeNode getInnerZone() {
        return this.body;
    }

    @objid ("6081ecf9-55b6-11e2-877f-002564c97630")
    @Override
    public List<GmNodeModel> getVisibleChildren() {
        // Returned result depends on current representation mode:
        switch (getRepresentationMode()) {
        case USER_IMAGE:
        case IMAGE:
            return Collections.emptyList();
        default:
            return super.getVisibleChildren();
        }
    }

    @objid ("6081ed02-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmBpmnAdHocSubProcessPrimaryNode.", GmBpmnAdHocSubProcessPrimaryNode.MINOR_VERSION);
    }

    @objid ("6081ed08-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.header = (GmBpmnNodeHeader) getFirstChild(GmBpmnSubProcessPrimaryNode.ROLE_HEADER);
        this.footer = (GmBpmnNodeFooter) getFirstChild(GmBpmnSubProcessPrimaryNode.ROLE_FOOTER);
        
        GmDefaultModelElementLabel imageModeHeader = (GmDefaultModelElementLabel) this.getChildren().get(3);
        imageModeHeader.delete();
        
        this.body = (GmBpmnSubProcessContent) getFirstChild(GmBpmnSubProcessPrimaryNode.ROLE_BODY);
    }

    @objid ("6081ed0d-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmBpmnAdHocSubProcessPrimaryNode.MAJOR_VERSION;
    }

    @objid ("6081ed12-55b6-11e2-877f-002564c97630")
    private void read_1(final IDiagramReader in) {
        super.read(in);
        this.header = (GmBpmnNodeHeader) getFirstChild(GmBpmnSubProcessPrimaryNode.ROLE_HEADER);
        this.footer = (GmBpmnNodeFooter) getFirstChild(GmBpmnSubProcessPrimaryNode.ROLE_FOOTER);
        this.body = (GmBpmnSubProcessContent) getFirstChild(GmBpmnSubProcessPrimaryNode.ROLE_BODY);
    }

    @objid ("0013816c-caf8-4fd9-8f0d-85dd8dae017e")
    private GmBpmnSubProcessContent createBody() {
        BpmnSubProcess process = (BpmnSubProcess) resolveRef(getRepresentedRef());
        if (process != null) {
            List<BpmnSubProcessDiagram> processdiags = process.getProduct(BpmnSubProcessDiagram.class);
            if (!processdiags.isEmpty()) {
                BpmnSubProcessDiagram diag = processdiags.get(0);
                GmBpmnSubProcessContent content = new GmBpmnSubProcessContent(getDiagram(), diag, new MRef(diag));
                content.setRoleInComposition(GmBpmnSubProcessPrimaryNode.ROLE_BODY);
                return content;
            }
        }
        return null;
    }

    @objid ("b8fbb5cf-908d-44a5-a395-0f07a7c6aeee")
    @Override
    public void styleChanged(StyleKey property, Object newValue) {
        super.styleChanged(property, newValue);
        
        if (GmBpmnSubProcessStructuredStyleKeys.SHOWCONTENT.equals(property)) {
            fireChildVisibilityChanged(getFirstChild(GmBpmnSubProcessPrimaryNode.ROLE_BODY));
        }
    }

}
