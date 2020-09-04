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

package org.modelio.diagram.editor.bpmn.elements.diagrams.processdesign;

import java.util.List;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.diagram.editor.bpmn.elements.bpmnlane.GmBpmnLane;
import org.modelio.diagram.editor.bpmn.elements.bpmnlane.hibridcontainer.GmBodyHybridContainer;
import org.modelio.diagram.editor.bpmn.elements.bpmnlanesetcontainer.GmBpmnLaneSetContainer;
import org.modelio.diagram.editor.bpmn.elements.bpmnmessage.GmBpmnMessage;
import org.modelio.diagram.editor.bpmn.elements.diagrams.BpmnDiagramSymbolViewModelProvider;
import org.modelio.diagram.editor.bpmn.elements.diagrams.GmBpmnDiagramStyleKeys;
import org.modelio.diagram.editor.bpmn.elements.participant.GmBpmnParticipantPortContainer;
import org.modelio.diagram.editor.bpmn.elements.workflow.GmWorkflow;
import org.modelio.diagram.editor.bpmn.elements.workflow.IWorkflowProvider;
import org.modelio.diagram.elements.common.abstractdiagram.GmAbstractDiagram;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.umlcommon.externdocument.GmExternDocument;
import org.modelio.diagram.elements.umlcommon.note.GmNote;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.diagram.styles.core.view.ISymbolViewModel;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnProcessDesignDiagram;
import org.modelio.metamodel.bpmn.events.BpmnBoundaryEvent;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnCollaboration;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLaneSet;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnParticipant;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.Document;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * This class represents the Gm of a BpmnProcessDesignDiagram.
 * <p>
 * The diagram might contain :
 * <ul>
 * <li>Its owner Process, unmasked as a GmBpmnProcessPortContainer instance</li>
 * <li>Participants, unmasked as GmBpmnParticipantPortContainer instances</li>
 * <li>A workflow, in a GmWorkflow</li>
 * </ul>
 * </p>
 * <p>
 * The diagram's workflow is either a direct child when no participant is part of the diagram or part of its Process. The diagram switches automatically from one mode to another if necessary.
 * </p>
 */
@objid ("9e4ad69a-3faf-4cb3-8687-9dac9d209e09")
public class GmBpmnProcessDesignDiagram extends GmAbstractDiagram implements IWorkflowProvider {
    @objid ("5f1c6e00-5846-4bd3-875e-aeae9bf065e6")
    private static final int MAJOR_VERSION = 0;

    /**
     * Current version of this Gm.
     */
    @objid ("cf9fd202-2aee-41aa-bed5-8f8378317c17")
    private static final int MINOR_VERSION = 1;

    /**
     * Role for the diagram's workflow.
     */
    @objid ("c9ad3ddd-3ee3-44c7-a870-1cd07c0022a8")
    public static final String ROLE_BODY = "diagram.body";

    @objid ("5c1b52bc-6416-41da-88fd-33a045b2608e")
    private BpmnProcessDesignDiagram obDiagram;

    @objid ("69b5447d-1a74-4f9f-a032-c68e7ccf4f3d")
    private static GmBpmnDiagramStyleKeys STYLEKEYS = new GmBpmnDiagramStyleKeys();

    @objid ("c1744b7a-2a26-4e78-95b1-6cc4374476ad")
    private GmWorkflow body;

    /**
     * Default constructor.
     * 
     * @param manager the manager needed make the link between the Ob and Gm models.
     * @param diagram the diagram itself.
     * @param diagramRef a reference to the diagram.
     */
    @objid ("386f28af-b740-4987-8f3a-9271e6174752")
    public GmBpmnProcessDesignDiagram(IModelManager manager, BpmnProcessDesignDiagram diagram, MRef diagramRef) {
        super(manager, diagramRef);
        this.obDiagram = diagram;
        
        // GmWorkflow creation
        createBody();
    }

    @objid ("f0e17603-713c-437c-b030-2ef396d222e9")
    @Override
    protected void reset(boolean hasPersistedData) {
        super.reset(hasPersistedData);
        
        if (!hasPersistedData) {
            createBody();
        }
    }

    @objid ("3206ba9d-ec0c-438f-8f24-d865d2b13df2")
    @Override
    public boolean canCreate(Class<? extends MObject> metaclass) {
        if (!isLocal()) {
            // Never accept to create elements in a referenced process
            return false;
        } else if (Dependency.class.isAssignableFrom(metaclass) ||
                AbstractDiagram.class.isAssignableFrom(metaclass) ||
                Note.class.isAssignableFrom(metaclass) ||
                Document.class.isAssignableFrom(metaclass)) {
            return true;
        } else if (BpmnLane.class.isAssignableFrom(metaclass)) {
            return true;
        } else if (BpmnBoundaryEvent.class.isAssignableFrom(metaclass)) {
            return false;
        } else {
            if (((BpmnProcess) this.obDiagram.getOrigin()).getLaneSet() == null) {
                return BpmnBaseElement.class.isAssignableFrom(metaclass);
            } else {
                return false;
            }
        }
    }

    @objid ("60903926-7eee-421b-a4ea-aacfcde28df1")
    @Override
    public boolean doCanUnmask(MObject el) {
        return false;
    }

    @objid ("72d32a00-924d-45a7-af7a-84ce3e95351e")
    @Override
    public GmCompositeNode getCompositeFor(Class<? extends MObject> metaclass) {
        if (this.body != null) {
            return this.body.getCompositeFor(metaclass);
        }
        return null;
    }

    @objid ("ff28acb9-ba08-4c0d-9938-c2f95c111deb")
    @Override
    public void delete() {
        this.body = null;
        super.delete();
    }

    @objid ("c2cd375b-34eb-4067-8896-38da3f654a67")
    @Override
    public void addChild(GmNodeModel child) {
        if (child instanceof GmWorkflow) {
            this.body = (GmWorkflow) child;
            child.setRoleInComposition(GmBpmnProcessDesignDiagram.ROLE_BODY);
        } else if (child instanceof GmNote || child instanceof GmExternDocument) {
            // Notes & rich notes should be in the workflow
            MObject owner = child.getRelatedElement().getCompositionOwner();
            if (!(owner instanceof BpmnProcess) && !(owner instanceof BpmnParticipant)) {
                getWorkflow().addChild(child);
                return;
            }
        }
        super.addChild(child);
    }

    @objid ("bbdd4cfc-f28e-45e5-9f2d-36e49452aa44")
    @Override
    public void removeChild(GmNodeModel child) {
        if (child == this.body) {
            this.body = null;
        }
        super.removeChild(child);
    }

    @objid ("030ddc94-d9f8-4d06-83e7-f3ec20624f8a")
    @Override
    public BpmnProcessDesignDiagram getRelatedElement() {
        return this.obDiagram;
    }

    @objid ("25de3c83-6975-42eb-875e-dd92801d56c6")
    @Override
    public RepresentationMode getRepresentationMode() {
        return RepresentationMode.STRUCTURED;
    }

    @objid ("79373a40-5b97-4b91-8a81-9c2dbc542c8e")
    @Override
    public BpmnProcessDesignDiagram getRepresentedElement() {
        return this.obDiagram;
    }

    @objid ("9b37620e-4a5d-4123-b435-c7d4594b3397")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        return GmBpmnProcessDesignDiagram.STYLEKEYS.getStyleKey(metakey);
    }

    @objid ("95004f2d-fd4f-4236-845c-dacf17704132")
    @Override
    public List<StyleKey> getStyleKeys() {
        return GmBpmnProcessDesignDiagram.STYLEKEYS.getStyleKeys();
    }

    @objid ("f628971d-3ca5-44db-a279-7326451768fd")
    @Override
    public ISymbolViewModel getSymbolViewModel() {
        return BpmnDiagramSymbolViewModelProvider.create(getPersistedStyle(), this);
    }

    @objid ("e1a45377-fe26-40c0-aef1-119a7bee2c39")
    @Override
    public GmWorkflow getWorkflow() {
        return this.body;
    }

    @objid ("4de41218-4dce-4541-a80d-2e190c07be13")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = GmAbstractObject.readMinorVersion(in, "GmBpmnProcessDesignDiagram.");
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
            assert false : "version number not covered!";
            // reading as last handled version: 1
            read_1(in);
            break;
        }
        }
    }

    @objid ("e36c6de1-c2b4-4d1f-b496-bacebc567973")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.obDiagram = (BpmnProcessDesignDiagram) resolveRef(getRepresentedRef());
        
        this.body = null;
        
        // Look for process and body
        List<GmNodeModel> oldChildren = getChildren();
        for (GmNodeModel oldChild : oldChildren) {
            if (oldChild instanceof GmWorkflow) {
                this.body = (GmWorkflow) oldChild;
                break;
            }
        }
        
        if (this.body == null) {
            this.body = new GmWorkflow(this, getRepresentedRef());
            this.body.setRoleInComposition(GmBpmnProcessDesignDiagram.ROLE_BODY);
            super.addChild(this.body);
        }
        
        // Move existing children into the workflow
        List<GmNodeModel> alreadyInProcess = this.body.getChildren();
        for (GmNodeModel oldChild : oldChildren) {
            if (oldChild instanceof GmBpmnParticipantPortContainer || oldChild instanceof GmBpmnMessage) {
                // Nothing to do
            } else if (oldChild instanceof GmBpmnLane) {
                if (oldChild.getRelatedElement().isShell()) {
                    // Replace shell GmBpmnLane, moving its contents to the parent itself.
                    GmBodyHybridContainer oldBody = ((GmBpmnLane) oldChild).getBody();
                    List<GmNodeModel> ownedNodes = oldBody.getChildren(GmBodyHybridContainer.OWNED_NODE);
                    if (!ownedNodes.isEmpty()) {
                        // There were no lanes in the old Gm
                        for (GmNodeModel ownedNode : ownedNodes) {
                            GmCompositeNode compositeFor = this.body.getCompositeFor(ownedNode.getRepresentedElement().getClass());
                            if (compositeFor != null) {
                                oldBody.removeChild(ownedNode);
                                compositeFor.addChild(ownedNode);
                            } else {
                                // There is probably no diagram for this process, delete the gm
                                ownedNode.delete();
                            }
                        }
                    }
        
                    List<GmNodeModel> ownedLaneSetContainer = oldBody.getChildren(GmBodyHybridContainer.SUB_LANE);
                    // Process lanes in the old Gm
                    for (GmNodeModel ownedNode : ownedLaneSetContainer) {
                        MObject representedElement = ownedNode.getRepresentedElement();
                        GmCompositeNode compositeFor = representedElement != null ? this.body.getCompositeFor(representedElement.getClass()) : null;
                        if (compositeFor != null) {
                            // Delete soon-to-be-replaced automatically unmasked container
                            for (GmNodeModel node : compositeFor.getChildren(GmWorkflow.OWNED_LANE)) {
                                node.delete();
                            }
        
                            // Old layout data was an Integer, replace it with a proper Rectangle
                            Rectangle layoutData = (oldChild.getLayoutData() instanceof Rectangle ? (Rectangle) oldChild.getLayoutData() : new Rectangle()).getCopy();
                            // Take removed header into consideration
                            layoutData.translate(25, 0);
                            ownedNode.setLayoutData(new Rectangle(layoutData));
        
                            // Add migrated container
                            oldBody.removeChild(ownedNode);
                            compositeFor.addChild(ownedNode);
                        } else {
                            // There is probably no diagram for this process, delete the gm
                            ownedNode.delete();
                        }
                    }
                    oldChild.delete();
                } else {
                    // Move lane to the proper LaneSetContainer
                    BpmnLane lane = (BpmnLane) oldChild.getRelatedElement();
                    BpmnLaneSet laneSet = lane.getLaneSet();
        
                    // Unmask lane set
                    final GmBpmnLaneSetContainer gmLaneSet = new GmBpmnLaneSetContainer(this, laneSet, new MRef(laneSet));
                    gmLaneSet.setLayoutData(oldChild.getLayoutData());
                    alreadyInProcess.add(gmLaneSet);
                    this.body.addChild(gmLaneSet);
        
                    // Reparent the lane
                    alreadyInProcess.add(oldChild);
                    super.removeChild(oldChild);
                    gmLaneSet.addChild(oldChild);
                }
            } else if (!oldChild.getRepresentedRef().equals(getRepresentedRef())) {
                alreadyInProcess.add(oldChild);
                super.removeChild(oldChild);
                this.body.addChild(oldChild);
            }
        }
        
        // Moved sequence flows to their corresponding embedded diagrams
        for (IGmDiagram newDiagram : getEmbeddedDiagrams()) {
            ModelElement origin = newDiagram.getRelatedElement().getOrigin();
            if (origin instanceof BpmnProcess) {
                for (BpmnSequenceFlow sequenceFlow : ((BpmnProcess) origin).getFlowElement(BpmnSequenceFlow.class)) {
                    for (GmModel relatedGm : getAllGMRelatedTo(new MRef(sequenceFlow))) {
                        removeGraphicModel(relatedGm);
                        newDiagram.addGraphicModel(relatedGm);
                    }
                }
            }
        }
    }

    @objid ("58e09c96-4238-468a-906c-55616e9bd44c")
    private void read_1(IDiagramReader in) {
        super.read(in);
        
        this.obDiagram = (BpmnProcessDesignDiagram) resolveRef(getRepresentedRef());
        this.body = (GmWorkflow) getFirstChild(GmBpmnProcessDesignDiagram.ROLE_BODY);
    }

    @objid ("967fc67b-e6e4-4f19-bb70-1146d39351f2")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        GmAbstractObject.writeMinorVersion(out, "GmBpmnProcessDesignDiagram.", GmBpmnProcessDesignDiagram.MINOR_VERSION);
    }

    @objid ("672055c4-a5f7-47e4-a68c-901b8d940230")
    @Override
    public int getMajorVersion() {
        return GmBpmnProcessDesignDiagram.MAJOR_VERSION;
    }

    @objid ("627e17a5-8c40-4c6e-9a78-7aa5c2d20e37")
    private void createBody() {
        this.body = new GmWorkflow(this, getRepresentedRef());
        this.body.setRoleInComposition(GmBpmnProcessDesignDiagram.ROLE_BODY);
        addChild(this.body);
    }

    /**
     * @return whether lanes should be displayed horizontally or vertically.
     */
    @objid ("d1d2eb43-23b9-43e8-90b0-7c0436f16629")
    public boolean isHorizontalLaneOrientation() {
        return getDisplayedStyle().getProperty(GmBpmnDiagramStyleKeys.HORIZONTAL_LANES);
    }

    /**
     * A process diagram is editable if the diagram model object is modifiable and the diagram is "local".
     */
    @objid ("9830223e-4849-49bb-980f-684ddfb3c37a")
    @Override
    public boolean isUserEditable() {
        MObject relatedElement = getRelatedElement();
        return relatedElement != null
                && !(relatedElement.isShell() || relatedElement.isDeleted())
                && relatedElement.getStatus().isModifiable()
                && isLocal();
    }

    /**
     * A process is local if the displayed diagram is not embedded or is displayed in a collaboration that is a composition children of the process itself.
     * 
     * @return <code>true</code> if the process is local.
     */
    @objid ("a6b58f61-5a02-4520-9218-596fbcf94e70")
    public boolean isLocal() {
        if (getDiagramOwner() == null) {
            return true;
        }
        Behavior process = getOwnerBehavior(getRelatedElement());
        Behavior collab = getOwnerBehavior(IGmDiagram.getRoot(this).getRelatedElement());
        if (collab instanceof BpmnCollaboration) {
            return Objects.equals(collab.getCompositionOwner(), process);
        } else {
            return Objects.equals(collab, process);
        }
    }

    /**
     * @param elt a {@link MObject}.
     * @return the first {@link Behavior} in the upward model composition tree. Might be <code>null</code> for non-BPMN elements.
     */
    @objid ("3a92dd7d-0967-4a64-9d16-75590c070ebe")
    private Behavior getOwnerBehavior(final MObject elt) {
        if (elt == null) {
            return null;
        } else if (elt instanceof Behavior) {
            return (Behavior) elt;
        } else {
            return getOwnerBehavior(elt.getCompositionOwner());
        }
    }

    @objid ("d0a410ce-e229-41f6-9b2a-00b1a9643118")
    @Override
    public String getFactoryIdentifier() {
        return BpmnProcessDesignDiagram.MNAME;
    }

    @objid ("6704459e-444b-49cc-8313-26879ea65909")
    @Override
    public boolean canUnmaskGenericElements() {
        return true;
    }

}
