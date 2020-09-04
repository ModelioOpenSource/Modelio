/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.diagram.editor.bpmn.elements.bpmnmessageflow;

import java.util.List;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.core.link.ExtensionLocation;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.model.IGmLinkable;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.bpmn.flows.BpmnMessageFlow;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnCollaboration;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnParticipant;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Graphic model for {@link BpmnMessageFlow}.
 */
@objid ("616ce94c-55b6-11e2-877f-002564c97630")
public class GmBpmnMessageFlow extends GmLink {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("616ce955-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("616ce958-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("c52fb52e-59a6-11e2-ae45-002564c97630")
    private static final GmBpmnMessageFlowStyleKeys styleKeyProvider = new GmBpmnMessageFlowStyleKeys();

    @objid ("76308a53-2e9e-41b8-a566-e869d2a80003")
    private BpmnMessageFlow element;

    /**
     * Initialize a control flow graphic model.
     * 
     * @param diagram The owning diagram
     * @param element The reference flow, may be null
     * @param ref The referenced flow reference, may not be null
     */
    @objid ("616ce95a-55b6-11e2-877f-002564c97630")
    public GmBpmnMessageFlow(IGmDiagram diagram, BpmnMessageFlow element, MRef ref) {
        super(diagram, ref);
        this.element = element;
        
        GmDefaultModelElementLabel extension = new GmDefaultModelElementLabel(diagram, ref);
        extension.setShowLabel(true);
        addExtension(ExtensionLocation.MiddleSE, IGmLink.ROLE_MAIN_LABEL, extension);
    }

    /**
     * For deserialization only.
     */
    @objid ("616ce966-55b6-11e2-877f-002564c97630")
    public GmBpmnMessageFlow() {
        // Nothing to do.
    }

    @objid ("616ce969-55b6-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        return GmBpmnMessageFlow.styleKeyProvider.getStyleKey(metakey);
    }

    @objid ("616ce973-55b6-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        return GmBpmnMessageFlow.styleKeyProvider.getStyleKeys();
    }

    @objid ("616e6fd9-55b6-11e2-877f-002564c97630")
    @Override
    public BpmnBaseElement getFromElement() {
        return this.element.getSourceRef();
    }

    @objid ("616e6fe0-55b6-11e2-877f-002564c97630")
    @Override
    public BpmnBaseElement getToElement() {
        return this.element.getTargetRef();
    }

    @objid ("616e6fe7-55b6-11e2-877f-002564c97630")
    @Override
    public BpmnMessageFlow getRepresentedElement() {
        return this.element;
    }

    @objid ("616e6fee-55b6-11e2-877f-002564c97630")
    @Override
    public void readLink(IDiagramReader in) {
        super.readLink(in);
        this.element = (BpmnMessageFlow) resolveRef(getRepresentedRef());
    }

    @objid ("616e6ff4-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return this.element;
    }

    @objid ("616e6ffb-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        GmAbstractObject.writeMinorVersion(out, "GmBpmnMessageFlow.", GmBpmnMessageFlow.MINOR_VERSION);
    }

    @objid ("616e7001-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmBpmnMessageFlow.MAJOR_VERSION;
    }

    @objid ("7d63716f-1c1f-44ef-a8f7-56b2ac5b6136")
    @Override
    protected void read_GmLinkV0_roles() {
        read_GmLinkV0_roles_one_main_label();
    }

    @objid ("4327fa10-d521-46bd-a41f-f2aa518db7a0")
    @Override
    protected void refreshFromObModel() {
        MObject relatedElement = getRelatedElement();
        if (relatedElement != null && !relatedElement.isShell() && !relatedElement.isDeleted()) {
            BpmnBaseElement graphicSourceElement = getFromElement();
            final IGmLinkable gmFrom = getFrom();
            if (gmFrom == null) {
                // source changed: let edit part know and handle it.
                firePropertyChange(GmLink.PROP_SOURCE_EL, null, graphicSourceElement);
            } else if (gmFrom instanceof GmModel) {
                MObject modelSourceElement = ((GmModel) gmFrom).getRelatedElement();
        
                if (!endPointsMatch(modelSourceElement, graphicSourceElement)) {
                    // source changed: let edit part know and handle it.
                    firePropertyChange(GmLink.PROP_SOURCE_EL, modelSourceElement, graphicSourceElement);
                } else if (gmFrom instanceof GmNodeModel && !((GmNodeModel) gmFrom).isVisible()) {
                    // source not visible anymore, delete the link
                    delete();
                    return;
                }
            }
        
            MObject graphicTargetElement = getToElement();
            final IGmLinkable toGm = getTo();
            if (toGm == null) {
                // target changed: let edit part know and handle it.
                firePropertyChange(GmLink.PROP_TARGET_EL, null, graphicTargetElement);
            } else if (toGm instanceof GmModel) {
                MObject modelTargetElement = ((GmModel) toGm).getRelatedElement();
                if (!endPointsMatch(modelTargetElement, graphicTargetElement)) {
                    // target changed: let edit part know and handle it.
                    firePropertyChange(GmLink.PROP_TARGET_EL, modelTargetElement, graphicTargetElement);
                } else if (toGm instanceof GmNodeModel && !((GmNodeModel) toGm).isVisible()) {
                    // target not visible anymore, delete the link
                    delete();
                    return;
                }
            }
        
        }
    }

    @objid ("6dedfbf9-8f8f-499d-8883-fbbb46368a63")
    private boolean endPointsMatch(MObject modelEndPoint, MObject graphicEndPoint) {
        if (Objects.equals(modelEndPoint, graphicEndPoint)) {
            return true;
        } else if (modelEndPoint == null || graphicEndPoint == null) {
            return false;
        }
        
        MObject endPoint = findParticipantOrProcessFor((BpmnBaseElement) graphicEndPoint);
        return (Objects.equals(modelEndPoint, endPoint));
    }

    /**
     * Find a {@link BpmnParticipant} of {@link BpmnProcess} in the diagram owner that contains <i>element</i>.
     * 
     * @param bpmnElt a BPMN element
     * @return the best {@link BpmnParticipant} referencing the {@link BpmnProcess} that owns <i>element</i>, or the {@link BpmnProcess} itself.
     */
    @objid ("cffa88f0-3fb0-4229-ab8d-64efea4b6ee9")
    private BpmnBaseElement findParticipantOrProcessFor(BpmnBaseElement bpmnElt) {
        if (bpmnElt == null) {
            return null;
        }
        
        // element is either a BpmnParticipant, BpmnProcess or a BpmnFlowElement
        assert (bpmnElt instanceof BpmnParticipant || bpmnElt instanceof BpmnProcess || bpmnElt instanceof BpmnFlowElement) : String.valueOf(bpmnElt);
        
        if (bpmnElt instanceof BpmnProcess) {
            return bpmnElt;
        } else if (bpmnElt instanceof BpmnParticipant) {
            return bpmnElt;
        } else if (bpmnElt instanceof BpmnFlowElement) {
            BpmnFlowElement flowEl = (BpmnFlowElement) bpmnElt;
            BpmnProcess elementProcess = flowEl.getContainer();
        
            List<BpmnParticipant> candidates = elementProcess.getParticipant();
        
            // Look for an already displayed BpmnParticipant
            for (BpmnParticipant participant : candidates) {
                if (!getDiagram().getAllGMRepresenting(new MRef(participant)).isEmpty()) {
                    return participant;
                }
            }
        
            // Look for a BpmnParticipant in the same collaboration
            BpmnCollaboration diagCollab = (BpmnCollaboration) getDiagram().getRelatedElement().getOrigin();
        
            // Look first in collaboration participants
            for (BpmnParticipant participant : candidates) {
                if (Objects.equals(diagCollab, participant.getContainer())) {
                    return participant;
                }
            }
        }
        return null;
    }

}
