/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.diagram.editor.bpmn.elements.bpmntransaction;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.core.ui.swt.images.ElementImageService;
import org.modelio.diagram.editor.bpmn.elements.bpmnnodefooter.GmBpmnNodeFooter;
import org.modelio.diagram.editor.bpmn.elements.bpmnnodeheader.GmBpmnNodeHeader;
import org.modelio.diagram.editor.bpmn.elements.bpmnsubprocess.GmBpmnSubProcessPrimaryNode;
import org.modelio.diagram.editor.bpmn.elements.bpmnsubprocess.content.GmBpmnSubProcessContent;
import org.modelio.diagram.editor.bpmn.elements.style.GmBpmnSubProcessStructuredStyleKeys;
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
import org.modelio.metamodel.bpmn.activities.BpmnMultiInstanceLoopCharacteristics;
import org.modelio.metamodel.bpmn.activities.BpmnStandardLoopCharacteristics;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.activities.BpmnTransaction;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnSubProcessDiagram;
import org.modelio.metamodel.bpmn.events.BpmnBoundaryEvent;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * This class represents the Gm of a SubProcess.
 */
@objid ("61dc4d11-55b6-11e2-877f-002564c97630")
public class GmBpmnTransactionPrimaryNode extends GmNoStyleCompositeNode implements IImageableNode {
    /**
     * Current version of this Gm.
     */
    @objid ("61dc4d1b-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 1;

    @objid ("61dc4d1e-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Header
     */
    @objid ("61dc4d17-55b6-11e2-877f-002564c97630")
    private GmBpmnNodeHeader header;

    @objid ("61dc4d19-55b6-11e2-877f-002564c97630")
    private GmBpmnNodeFooter footer;

    @objid ("61dc4d1a-55b6-11e2-877f-002564c97630")
    private GmBpmnSubProcessContent body;

    /**
     * Default constructor.
     * @param diagram the diagram in which this gm is unmasked.
     * @param relatedRef ref
     */
    @objid ("61dc4d20-55b6-11e2-877f-002564c97630")
    public GmBpmnTransactionPrimaryNode(IGmDiagram diagram, MRef relatedRef) {
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
    }

    @objid ("61ddd380-55b6-11e2-877f-002564c97630")
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
    @objid ("61ddd386-55b6-11e2-877f-002564c97630")
    public GmBpmnTransactionPrimaryNode() {
        // empty constructor for the serialization
    }

    @objid ("61ddd389-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return this.body != null && this.body.isVisible() && BpmnBaseElement.class.isAssignableFrom(type) && !BpmnBoundaryEvent.class.isAssignableFrom(type);
    }

    @objid ("61ddd391-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        return el instanceof BpmnBaseElement && ((BpmnBaseElement) el).getCompositionOwner().equals(getRelatedElement());
    }

    @objid ("61ddd399-55b6-11e2-877f-002564c97630")
    @Override
    public GmCompositeNode getCompositeFor(Class<? extends MObject> metaclass) {
        if (canCreate(metaclass)) {
            return this.body.getCompositeFor(metaclass);
        }
        return null;
    }

    @objid ("61ddd3a3-55b6-11e2-877f-002564c97630")
    @Override
    public Image getImage() {
        return ElementImageService.getImage(getRelatedElement());
    }

    @objid ("61ddd3a8-55b6-11e2-877f-002564c97630")
    @Override
    public BpmnTransaction getRelatedElement() {
        return (BpmnTransaction) super.getRelatedElement();
    }

    @objid ("61ddd3af-55b6-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        final StyleKey repModeKey = GmBpmnTransaction.STRUCTURED_KEYS.getStyleKey(MetaKey.REPMODE);
        return getDisplayedStyle().getProperty(repModeKey);
    }

    @objid ("61ddd3b6-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmBpmnTransactionPrimaryNode.");
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

    @objid ("61df5a1c-55b6-11e2-877f-002564c97630")
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

    @objid ("61df5a1f-55b6-11e2-877f-002564c97630")
    @Override
    public void obElementAdded(MObject movedEl) {
        super.obElementAdded(movedEl);
        refreshFromObModel();
    }

    @objid ("61df5a25-55b6-11e2-877f-002564c97630")
    @Override
    public void obElementsUpdated() {
        super.obElementsUpdated();
        refreshFromObModel();
    }

    /**
     * @return the structured inner zone.
     */
    @objid ("61df5a28-55b6-11e2-877f-002564c97630")
    public GmCompositeNode getInnerZone() {
        return this.body;
    }

    @objid ("61df5a2f-55b6-11e2-877f-002564c97630")
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

    @objid ("61df5a38-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmBpmnTransactionPrimaryNode.", GmBpmnTransactionPrimaryNode.MINOR_VERSION);
    }

    @objid ("61df5a3e-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.header = (GmBpmnNodeHeader) getFirstChild(GmBpmnSubProcessPrimaryNode.ROLE_HEADER);
        this.footer = (GmBpmnNodeFooter) getFirstChild(GmBpmnSubProcessPrimaryNode.ROLE_FOOTER);
        
        GmDefaultModelElementLabel imageModeHeader = (GmDefaultModelElementLabel) this.getChildren().get(3);
        imageModeHeader.delete();
        
        this.body = (GmBpmnSubProcessContent) getFirstChild(GmBpmnSubProcessPrimaryNode.ROLE_BODY);
    }

    @objid ("61df5a43-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmBpmnTransactionPrimaryNode.MAJOR_VERSION;
    }

    @objid ("61df5a48-55b6-11e2-877f-002564c97630")
    private void read_1(final IDiagramReader in) {
        super.read(in);
        this.header = (GmBpmnNodeHeader) getFirstChild(GmBpmnSubProcessPrimaryNode.ROLE_HEADER);
        this.footer = (GmBpmnNodeFooter) getFirstChild(GmBpmnSubProcessPrimaryNode.ROLE_FOOTER);
        this.body = (GmBpmnSubProcessContent) getFirstChild(GmBpmnSubProcessPrimaryNode.ROLE_BODY);
    }

    @objid ("2695db02-0130-49e3-b37c-7d233d9d5fc5")
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

    @objid ("5bc5bea1-f0ca-4d8a-8a32-a44db4dc6f52")
    @Override
    public void styleChanged(StyleKey property, Object newValue) {
        super.styleChanged(property, newValue);
        
        if (GmBpmnSubProcessStructuredStyleKeys.SHOWCONTENT.equals(property)) {
            fireChildVisibilityChanged(getFirstChild(GmBpmnSubProcessPrimaryNode.ROLE_BODY));
        }
    }

}
