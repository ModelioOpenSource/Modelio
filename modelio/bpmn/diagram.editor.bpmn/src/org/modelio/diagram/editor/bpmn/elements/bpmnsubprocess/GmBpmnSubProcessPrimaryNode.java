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

package org.modelio.diagram.editor.bpmn.elements.bpmnsubprocess;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.core.ui.swt.images.ElementImageService;
import org.modelio.diagram.editor.bpmn.elements.bpmnnodefooter.GmBpmnNodeFooter;
import org.modelio.diagram.editor.bpmn.elements.bpmnnodeheader.GmBpmnNodeHeader;
import org.modelio.diagram.editor.bpmn.elements.bpmnsubprocess.content.GmBpmnSubProcessContent;
import org.modelio.diagram.editor.bpmn.elements.style.GmBpmnSubProcessStructuredStyleKeys;
import org.modelio.diagram.editor.bpmn.elements.workflow.GmWorkflow;
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
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnSubProcessDiagram;
import org.modelio.metamodel.bpmn.events.BpmnBoundaryEvent;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLaneSet;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;
import org.modelio.vcore.smkernel.mapi.MExpert;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * This class represents the Gm of a SubProcess.
 */
@objid ("61c9fd79-55b6-11e2-877f-002564c97630")
public class GmBpmnSubProcessPrimaryNode extends GmNoStyleCompositeNode implements IImageableNode {
    /**
     * Current version of this Gm.
     */
    @objid ("61c9fd83-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 1;

    @objid ("61c9fd86-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("e130646c-1680-43e7-8cc3-b3684e19124f")
    public static final String ROLE_HEADER = "HEADER";

    @objid ("80330b1b-6589-49dc-9c6d-9547d447334a")
    public static final String ROLE_BODY = "BODY";

    @objid ("d51fdb71-86c2-4ff3-b385-3b20eac476f0")
    public static final String ROLE_FOOTER = "FOOTER";

    /**
     * Header
     */
    @objid ("61c9fd7f-55b6-11e2-877f-002564c97630")
    private GmBpmnNodeHeader header;

    @objid ("61c9fd81-55b6-11e2-877f-002564c97630")
    private GmBpmnNodeFooter footer;

    @objid ("61c9fd82-55b6-11e2-877f-002564c97630")
    private GmBpmnSubProcessContent body;

    /**
     * Default constructor.
     * 
     * @param diagram the diagram in which this gm is unmasked.
     * @param relatedRef ref
     */
    @objid ("61c9fd88-55b6-11e2-877f-002564c97630")
    public GmBpmnSubProcessPrimaryNode(IGmDiagram diagram, MRef relatedRef) {
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

    @objid ("61c9fd91-55b6-11e2-877f-002564c97630")
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
    @objid ("61c9fd97-55b6-11e2-877f-002564c97630")
    public GmBpmnSubProcessPrimaryNode() {
        // empty constructor for the serialization
    }

    @objid ("61c9fd9a-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return this.body != null && this.body.isVisible() && BpmnBaseElement.class.isAssignableFrom(type) && !BpmnBoundaryEvent.class.isAssignableFrom(type) && !BpmnLaneSet.class.isAssignableFrom(type);
    }

    @objid ("61cb83fd-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        MExpert expert = el.getMClass().getMetamodel().getMExpert();
        return el instanceof BpmnBaseElement && !expert.isLink(el.getMClass()) && ((BpmnBaseElement) el).getCompositionOwner().equals(getRelatedElement());
    }

    @objid ("61cb8405-55b6-11e2-877f-002564c97630")
    @Override
    public GmCompositeNode getCompositeFor(Class<? extends MObject> metaclass) {
        if (canCreate(metaclass)) {
            return this.body.getCompositeFor(metaclass);
        }
        return null;
    }

    @objid ("61cb840f-55b6-11e2-877f-002564c97630")
    @Override
    public Image getImage() {
        return ElementImageService.getImage(getRelatedElement());
    }

    @objid ("61cb8414-55b6-11e2-877f-002564c97630")
    @Override
    public BpmnSubProcess getRelatedElement() {
        return (BpmnSubProcess) super.getRelatedElement();
    }

    @objid ("61cb841b-55b6-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        final StyleKey repModeKey = GmBpmnSubProcess.STRUCTURED_KEYS.getStyleKey(MetaKey.REPMODE);
        return getDisplayedStyle().getProperty(repModeKey);
    }

    @objid ("61cb8422-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmBpmnSubProcessPrimaryNode.");
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

    @objid ("61cb8428-55b6-11e2-877f-002564c97630")
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
        firePropertyChange(IGmObject.PROPERTY_LABEL, oldLabel, this.header.getMainLabel());
        firePropertyChange(IGmObject.PROPERTY_LAYOUTDATA, null, this.header.getMainLabel());
        
        if (getRelatedElement() != null && getRelatedElement().isValid()) {
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

    @objid ("61cb842b-55b6-11e2-877f-002564c97630")
    @Override
    public void obElementAdded(MObject movedEl) {
        super.obElementAdded(movedEl);
        refreshFromObModel();
    }

    @objid ("61cb8431-55b6-11e2-877f-002564c97630")
    @Override
    public void obElementsUpdated() {
        super.obElementsUpdated();
        refreshFromObModel();
    }

    /**
     * @return the structured inner zone.
     */
    @objid ("61cb8434-55b6-11e2-877f-002564c97630")
    public GmCompositeNode getInnerZone() {
        return this.body;
    }

    @objid ("61cd0aa1-55b6-11e2-877f-002564c97630")
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

    @objid ("61cd0aaa-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmBpmnSubProcessPrimaryNode.", GmBpmnSubProcessPrimaryNode.MINOR_VERSION);
    }

    @objid ("61cd0ab0-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        
        this.header = (GmBpmnNodeHeader) getFirstChild(GmBpmnSubProcessPrimaryNode.ROLE_HEADER);
        this.footer = (GmBpmnNodeFooter) getFirstChild(GmBpmnSubProcessPrimaryNode.ROLE_FOOTER);
        
        GmDefaultModelElementLabel imageModeHeader = (GmDefaultModelElementLabel) this.getChildren().get(3);
        imageModeHeader.delete();
        
        this.body = (GmBpmnSubProcessContent) getFirstChild(GmBpmnSubProcessPrimaryNode.ROLE_BODY);
    }

    @objid ("61cd0ab5-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmBpmnSubProcessPrimaryNode.MAJOR_VERSION;
    }

    @objid ("61cd0aba-55b6-11e2-877f-002564c97630")
    private void read_1(final IDiagramReader in) {
        super.read(in);
        
        this.header = (GmBpmnNodeHeader) getFirstChild(GmBpmnSubProcessPrimaryNode.ROLE_HEADER);
        this.footer = (GmBpmnNodeFooter) getFirstChild(GmBpmnSubProcessPrimaryNode.ROLE_FOOTER);
        this.body = (GmBpmnSubProcessContent) getFirstChild(GmBpmnSubProcessPrimaryNode.ROLE_BODY);
    }

    @objid ("bf6c9c9b-f2b1-4ff4-ae6a-edf03f1961cf")
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

    @objid ("22a3ea76-603f-4273-9ca4-89dec47d1f97")
    public GmWorkflow getWorkflow() {
        if (this.body != null) {
            List<GmNodeModel> visibleChildren = this.body.getVisibleChildren();
            return (GmWorkflow) visibleChildren.get(visibleChildren.size() - 1);
        } else {
            return null;
        }
    }

    @objid ("cb31acb9-22c3-453e-bb1e-3dceede02800")
    @Override
    public void styleChanged(StyleKey property, Object newValue) {
        super.styleChanged(property, newValue);
        
        if (GmBpmnSubProcessStructuredStyleKeys.SHOWCONTENT.equals(property)) {
            fireChildVisibilityChanged(getFirstChild(GmBpmnSubProcessPrimaryNode.ROLE_BODY));
        }
    }

}
