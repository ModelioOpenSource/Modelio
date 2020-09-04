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

package org.modelio.diagram.editor.bpmn.elements.workflow;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.bpmn.elements.bpmnlanesetcontainer.GmBpmnLaneSetContainer;
import org.modelio.diagram.editor.bpmn.elements.bpmnsubprocess.content.GmBpmnSubProcessContent;
import org.modelio.diagram.elements.common.freezone.GmBodyFreeZone;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.metamodel.bpmn.events.BpmnBoundaryEvent;
import org.modelio.metamodel.bpmn.flows.BpmnMessage;
import org.modelio.metamodel.bpmn.flows.BpmnMessageFlow;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.objects.BpmnDataAssociation;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLaneSet;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnParticipant;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.uml.infrastructure.AbstractResource;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents a BPMN workflow, in a diagram, a participant, a process or a subprocess.
 */
@objid ("3f9bfabd-eb95-4b96-a34b-a18a0f02874a")
public class GmWorkflow extends GmBodyFreeZone {
    @objid ("3faefdae-132d-4341-8bb9-eae1a9c246e5")
    private static final int MAJOR_VERSION = 0;

    @objid ("6f651238-5328-4373-8db1-098d0a217099")
    private static final String MINOR_PREFIX = "GmWorkflow.";

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("aa7a55da-b1d8-4b21-90c8-7232b6aa55ff")
    private static final int MINOR_VERSION = 0;

    /**
     * Constant used to describe role of all owned lanes.
     */
    @objid ("d31ebe70-e37f-4381-befc-d3555b64ee92")
    public static final String OWNED_LANE = "OwnedPool";

    /**
     * Constant used to describe role of all owned nodes.
     */
    @objid ("89487adc-b56e-4955-92c6-db6216aecf69")
    public static final String OWNED_NODE = "OwnedNode";

    @objid ("8f4259cd-9761-490f-a96e-b6407e0ab106")
    private transient Predicate<GmNodeModel> hideChildrenFilter;

    /**
     * A filter that removes notes and constraints from the workflow visible children. To be used with {@link #setHideChildrenFilter(Predicate)}.
     */
    @objid ("14c96ebe-48ec-4ae5-a1ce-582f9a2b1648")
    public static final Predicate<GmNodeModel> REMOVE_ANNOTATIONS = gm -> {
        Class<? extends MObject> type = gm.getRelatedMClass().getJavaInterface();
        return Note.class.isAssignableFrom(type)
                || AbstractResource.class.isAssignableFrom(type);
    };

    /**
     * @param diagram the diagram in which this workflow is used.
     * @param relatedRef ref
     */
    @objid ("61a9dd83-94a1-4a83-85c8-52008182a7b2")
    public GmWorkflow(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
    }

    /**
     * Empty constructor needed for serialisation.
     */
    @objid ("4a84789b-ca11-4c57-a99d-2e08917af467")
    public GmWorkflow() {
        // Nothing to do.
    }

    @objid ("d051d6ae-c2fc-41a4-ad20-887a548b6af5")
    @Override
    public void addChild(GmNodeModel child) {
        // Assign correct role to child: if not a sub partition, then its an inner node!
        if (child instanceof GmBpmnLaneSetContainer) {
            child.setRoleInComposition(GmWorkflow.OWNED_LANE);
            // Lane set container should be the first child
            super.addChild(child, 0);
        } else {
            child.setRoleInComposition(GmWorkflow.OWNED_NODE);
            super.addChild(child);
        }
    }

    @objid ("089a308b-5c8a-429d-9aa9-67de5c7797e5")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        if (Dependency.class.isAssignableFrom(type) ||
                AbstractDiagram.class.isAssignableFrom(type) ||
                Note.class.isAssignableFrom(type) ||
                AbstractResource.class.isAssignableFrom(type)) {
            return true;
        } else if (BpmnLane.class.isAssignableFrom(type) || BpmnLaneSet.class.isAssignableFrom(type)) {
            // If a lane set container exists, the workflow is not supposed to create lanes
            for (GmNodeModel child : getChildren()) {
                if (child.getRelatedElement() instanceof BpmnLaneSet) {
                    return false;
                }
            }
            return true;
        } else if (BpmnBoundaryEvent.class.isAssignableFrom(type) ||
                BpmnParticipant.class.isAssignableFrom(type) ||
                BpmnProcess.class.isAssignableFrom(type) ||
                BpmnMessageFlow.class.isAssignableFrom(type) ||
                BpmnMessage.class.isAssignableFrom(type)) {
            return false;
        }
        
        MObject workflowOwner = getDiagram().getRelatedElement().getOrigin();
        if (workflowOwner instanceof BpmnProcess) {
            BpmnLaneSet laneSet = ((BpmnProcess) workflowOwner).getLaneSet();
            if (laneSet != null && !laneSet.getLane().isEmpty()) {
                // Forbid node creation in a workflow having lanes.
                return false;
            }
        }
        return (BpmnBaseElement.class.isAssignableFrom(type));
    }

    @objid ("b19d8674-63f2-4509-97a7-ca7a7cf43575")
    @Override
    public boolean canUnmask(MObject elt) {
        if (elt instanceof BpmnLaneSet) {
            return getDiagram().getRelatedElement().getOrigin().equals(((BpmnLaneSet) elt).getProcess());
        } else if (elt instanceof BpmnLane) {
            return canUnmask(((BpmnLane) elt).getLaneSet());
        }
        Class<? extends MObject> type = elt.getMClass().getJavaInterface();
        return canUnmask(type) && isInWorkflow(elt);
    }

    @objid ("fa46c357-4fe2-41f2-98b1-ea1136f6840e")
    @Override
    public GmCompositeNode getCompositeFor(Class<? extends MObject> type) {
        if (canUnmask(type)) {
            return this;
        }
        return null;
    }

    @objid ("cd7d6fec-301e-42f8-a508-003241b80c6e")
    @Override
    public int getMajorVersion() {
        return GmWorkflow.MAJOR_VERSION;
    }

    /**
     * @return <code>true</code> when the workflow is embedded in a different Gm, <code>false</code> when the workflow should work as a diagram's background.
     */
    @objid ("3886c419-58c0-45e8-9e16-f61774795fde")
    public boolean isEmbedded() {
        IGmDiagram diagram = getDiagram();
        return diagram != null && diagram.getDiagramOwner() != null;
    }

    @objid ("10574883-89b4-4099-96b1-0b39b3995f23")
    @Override
    public boolean isVisible() {
        GmCompositeNode parentNode = getParentNode();
        return parentNode != null && parentNode.isVisible();
    }

    @objid ("29541ff9-3e3b-4764-9076-5400a0a0c990")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = GmAbstractObject.readMinorVersion(in, GmWorkflow.MINOR_PREFIX);
        switch (readVersion) {
        case 0:
            read_0(in);
            break;
        default:
            assert (false) : "version number not covered!";
            // reading as last handled version: 0
            read_0(in);
            break;
        }
    }

    @objid ("22572272-8266-4d24-b298-13c1dedcdb53")
    @Override
    public void refreshFromObModel() {
        super.refreshFromObModel();
        
        MObject element = getRelatedElement();
        if (element == null || !element.isValid() || !(element instanceof AbstractDiagram)) {
            return;
        } else {
            element = ((AbstractDiagram) element).getOrigin();
        }
        
        // Ask the edit part to refresh the model.
        firePropertyChange(GmModel.PROP_REFRESH_FROM_OBMODEL, null, this);
    }

    @objid ("98b11b5d-3d0a-46df-8501-e83ade7c0bed")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        GmAbstractObject.writeMinorVersion(out, GmWorkflow.MINOR_PREFIX, GmWorkflow.MINOR_VERSION);
    }

    @objid ("22cc3fc8-fa63-46f6-81b3-afc911b0d8fa")
    @Override
    protected void doSetVisible(boolean visible) {
        if (visible && !isEmbedded()) {
            final GmCompositeNode grandParentNode = getParentNode().getParentNode();
            if (grandParentNode instanceof GmBpmnSubProcessContent) {
                grandParentNode.setVisible(visible);
                return;
            }
            super.doSetVisible(visible);
        }
    }

    @objid ("59299a81-0cfb-440b-b633-ed3bebdecb63")
    private boolean canUnmask(Class<? extends MObject> type) {
        if (Dependency.class.isAssignableFrom(type) ||
                AbstractDiagram.class.isAssignableFrom(type) ||
                Note.class.isAssignableFrom(type) ||
                AbstractResource.class.isAssignableFrom(type) ||
                BpmnLane.class.isAssignableFrom(type) ||
                BpmnLaneSet.class.isAssignableFrom(type) ||
                BpmnDataAssociation.class.isAssignableFrom(type) ||
                BpmnSequenceFlow.class.isAssignableFrom(type)) {
            return true;
        } else if (BpmnBoundaryEvent.class.isAssignableFrom(type) ||
                BpmnParticipant.class.isAssignableFrom(type) ||
                BpmnProcess.class.isAssignableFrom(type) ||
                BpmnMessageFlow.class.isAssignableFrom(type) ||
                BpmnMessage.class.isAssignableFrom(type)) {
            return false;
        } else if (getChildren(GmWorkflow.OWNED_LANE).isEmpty()) {
            return BpmnBaseElement.class.isAssignableFrom(type);
        } else {
            return false;
        }
    }

    /**
     * @return <code>true</code> if the element belongs to the current workflow.
     */
    @objid ("31edb795-e14f-402e-b7be-24d961a66781")
    private boolean isInWorkflow(final MObject elt) {
        if (elt == null) {
            return false;
        } else if (Objects.equals(getDiagram().getRelatedElement().getOrigin(), elt)) {
            return true;
        } else {
            return isInWorkflow(elt.getCompositionOwner());
        }
    }

    @objid ("a84da23b-c5e7-4258-a43c-c79951d9dbcb")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    /**
     * Tells whether this workflow is embedded and contains Lanes.
     * @return true if this workflow is embedded and contains Lanes.
     */
    @objid ("c213ebd5-42e5-4f4e-b4f9-33915c603b39")
    public boolean isEmbeddedWithLanes() {
        if (!isEmbedded()) {
            return false;
        }
        
        final List<?> modelChildren = getVisibleChildren();
        if (modelChildren.isEmpty()) {
            return false;
        }
        return modelChildren.stream().anyMatch(m -> (m instanceof GmBpmnLaneSetContainer));
    }

    /**
     * Valid child: a child that belongs to 'this' workflow
     * <p>
     * Note that 'belong' for BPMN, does not rely on composition owning in the model but is rather based on 'container' and 'subprocess' associations in the metamodel. Theoretically only one of the two association should be used (the unused one being
     * 'null'), the following core rely on this assumption.
     */
    @objid ("16b0d45e-f0c8-448c-b07a-386c837c3c42")
    @Override
    protected boolean isValidChild(GmNodeModel node) {
        ModelElement origin = getDiagram().getRelatedElement().getOrigin();
        
        MObject el = node.getRepresentedElement();
        if (el instanceof BpmnLaneSet) {
            return Objects.equals(getProcess((BpmnLaneSet) el), origin);
        } else if (el instanceof BpmnFlowElement) {
        
            BpmnFlowElement elt = (BpmnFlowElement) el;
        
            if (elt.getSubProcess() != null) {
                // 'elt' belongs to a subprocess that must be the same as 'this' workflow one.
                return elt.getSubProcess().equals(origin);
            } else if (elt.getContainer() != null) {
                // 'elt' belongs to a container (ie a process) that must be the same as 'this' workflow one.
                return elt.getContainer().equals(origin);
            } else {
                // Right now, not sure what to decide here...
                return true;
            }
        } else {
            // Right now, not sure what to decide here...
            return true;
        }
    }

    @objid ("7519aec5-d056-46f3-8e96-abbbd728ce63")
    private static MObject getProcess(BpmnLaneSet ls) {
        if (ls.getParentLane() != null) {
            return getProcess(ls.getParentLane().getLaneSet());
        } else if (ls.getProcess() != null) {
            return ls.getProcess();
        } else {
            return ls.getSubProcess();
        }
    }

    @objid ("24b34ebf-a18a-440c-b67f-ec1b331e8378")
    @Override
    public List<GmNodeModel> getVisibleChildren() {
        List<GmNodeModel> ret = super.getVisibleChildren();
        if (this.hideChildrenFilter != null) {
            ret.removeIf(this.hideChildrenFilter);
        }
        return ret;
    }

    @objid ("4d56dde5-544c-4205-8c40-59e734213285")
    public final Predicate<GmNodeModel> getHideChildrenFilter() {
        return this.hideChildrenFilter;
    }

    /**
     * Add a filter that removes graphic nodes from the visible children.
     * @see #REMOVE_ANNOTATIONS
     * @param filter a predicates that returns true to hide a child node. null to disable filtering.
     */
    @objid ("a1ef7fbb-6255-4d3f-b00f-1195eddef17f")
    public final void setHideChildrenFilter(Predicate<GmNodeModel> filter) {
        if (!Objects.equals(filter, this.hideChildrenFilter)) {
            this.hideChildrenFilter = filter;
            firePropertyChange(IGmObject.PROPERTY_CHILDREN, null, null);
        }
    }

}
