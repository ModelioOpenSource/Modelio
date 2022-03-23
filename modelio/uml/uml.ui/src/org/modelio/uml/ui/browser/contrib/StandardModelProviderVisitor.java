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
package org.modelio.uml.ui.browser.contrib;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.activities.BpmnActivity;
import org.modelio.metamodel.bpmn.activities.BpmnComplexBehaviorDefinition;
import org.modelio.metamodel.bpmn.activities.BpmnMultiInstanceLoopCharacteristics;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.bpmnService.BpmnInterface;
import org.modelio.metamodel.bpmn.events.BpmnBoundaryEvent;
import org.modelio.metamodel.bpmn.events.BpmnCatchEvent;
import org.modelio.metamodel.bpmn.events.BpmnEvent;
import org.modelio.metamodel.bpmn.events.BpmnThrowEvent;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.objects.BpmnItemAwareElement;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnCollaboration;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLaneSet;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnParticipant;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.resources.BpmnResource;
import org.modelio.metamodel.bpmn.resources.BpmnResourceRole;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowNode;
import org.modelio.metamodel.bpmn.rootElements.BpmnSharedDefinitions;
import org.modelio.metamodel.mda.Project;
import org.modelio.metamodel.uml.behavior.activityModel.Activity;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityAction;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityEdge;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityGroup;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityNode;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityPartition;
import org.modelio.metamodel.uml.behavior.activityModel.Clause;
import org.modelio.metamodel.uml.behavior.activityModel.ConditionalNode;
import org.modelio.metamodel.uml.behavior.activityModel.ExpansionRegion;
import org.modelio.metamodel.uml.behavior.activityModel.StructuredActivityNode;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationChannel;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationInteraction;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationNode;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Region;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateVertex;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Transition;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.ClassAssociation;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.metamodel.uml.statik.CollaborationUse;
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.LinkEnd;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.NaryAssociation;
import org.modelio.metamodel.uml.statik.NaryAssociationEnd;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.metamodel.uml.statik.TemplateBinding;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.metamodel.visitors.DefaultModelVisitor;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Visitor implementation covering UML and Bpmn content providing. Used by UmlContentProvider.
 * 
 * Visitor used to get tree node children.
 * <p>
 * <h3>Implementation note:</h3> If {@link #includeLinks} is <code>true</code>, the implementation should use {@link #addResults(List)} so that children tree nodes are linked to the container. <br/>
 * This should help the tree view not shrinking sometimes.
 * <p>
 * In the other case {@link #addResults(List)} should be called, to avoid creating adapters for nothing. The object returned by the 'visitXXXX " methods shall not be used. Element to return have to be added by calling 'addResults"
 */
@objid ("eb042f26-24cb-4956-8162-6673245953ef")
class StandardModelProviderVisitor extends DefaultModelVisitor {
    /**
     * Show 'link' container and links
     */
    @objid ("a485f495-2304-4526-950d-cd43d8e3569e")
    private boolean includeLinks;

    /**
     * Show UML model or not
     */
    @objid ("0cbdde48-935b-4280-9f13-6ee2523bbd7b")
    private boolean includeElements;

    /**
     * The computed children list
     */
    @objid ("368bf295-a371-4e4d-9222-2665cf4791dc")
    private List<Object> result;

    @objid ("00bdd2c9-7d44-403d-a1bb-7d270f607eba")
    private Comparator<MObject> standardSorter = new UmlMetaclassSorter();

    /**
     * Get the 'links' under parent, links are those elements intended to appear in the 'link' container in the browser
     * @param parent @return
     */
    @objid ("4fa114c7-d916-43c0-b6e0-7746dbf11b21")
    public List<MObject> getLinks(final MObject parent) {
        return getChildren(parent, false, true).stream().filter(o -> o instanceof MObject).map(o -> (MObject) o).collect(Collectors.toList());
    }

    /**
     * Get the 'elements' under parent.
     * @param parent @return
     */
    @objid ("4e9d9580-087e-490f-9ab0-ad8b8fe940f9")
    public List<Object> getElements(final MObject parent) {
        return getChildren(parent, true, false);
    }

    @objid ("f35d53e0-0bd9-4e2f-8846-417f5bd3d95c")
    @Override
    public Object visitActivity(Activity theActivity) {
        if (this.includeElements) {
            final List<ActivityGroup> ownedGroups = new ArrayList<>(theActivity.getOwnedGroup());
            Collections.sort(ownedGroups, this.standardSorter);
            addResults(ownedGroups);
        
            final List<ActivityNode> ownedNodes = new ArrayList<>(theActivity.getOwnedNode());
            Collections.sort(ownedNodes, this.standardSorter);
            addResults(ownedNodes);
        }
        super.visitActivity(theActivity);
        return null;
    }

    @objid ("51b2568b-352a-4abf-95d8-c0c31d59bcd0")
    @Override
    public Object visitActivityAction(ActivityAction theActivityAction) {
        if (this.includeElements) {
            // InputPin
            addResults(theActivityAction.getInput());
        
            // OutputPin
            addResults(theActivityAction.getOutput());
        
            // ExceptionHandler
            addResults(theActivityAction.getHandler());
        
        }
        super.visitActivityAction(theActivityAction);
        return null;
    }

    @objid ("65eeec5b-ef3c-431b-9fb4-4cadb694bbd8")
    @Override
    public Object visitActivityNode(ActivityNode theActivityNode) {
        if (this.includeElements) {
            List<ActivityEdge> flows = new ArrayList<>(theActivityNode.getOutgoing());
            Collections.sort(flows, this.standardSorter);
            addResults(flows);
        }
        super.visitActivityNode(theActivityNode);
        return null;
    }

    @objid ("431f4c56-2ecd-47bb-a5b6-903a76bf93b8")
    @Override
    public Object visitActivityPartition(ActivityPartition theActivityPartition) {
        if (this.includeElements) {
            // ControlFlow
            addResults(theActivityPartition.getSubPartition());
        
            List<ActivityNode> nodes = new ArrayList<>(theActivityPartition.getContainedNode());
            Collections.sort(nodes, this.standardSorter);
            addResults(nodes);
        
            // MessageFlow
            addResults(theActivityPartition.getOutgoing());
        }
        super.visitActivityPartition(theActivityPartition);
        return null;
    }

    @objid ("ac0ea471-c64e-419c-844e-e1d8e18af6be")
    @Override
    public Object visitArtifact(Artifact theArtifact) {
        if (this.includeElements) {
            // Manifestation
            addResults(theArtifact.getUtilized());
        }
        super.visitArtifact(theArtifact);
        return null;
    }

    @objid ("6ac23641-9ac3-4ac0-8392-f388b081c39b")
    @Override
    public Object visitAssociation(Association theAssociation) {
        if (this.includeElements) {
            addResult(theAssociation.getLinkToClass());
        }
        super.visitAssociation(theAssociation);
        return null;
    }

    @objid ("696e1f5b-f0e5-4c3b-b339-8d397c8ffb90")
    @Override
    public Object visitAssociationEnd(AssociationEnd theAssociationEnd) {
        if (this.includeElements) {
            addResults(theAssociationEnd.getQualifier());
        
            addResult(theAssociationEnd.getAssociation());
        }
        super.visitAssociationEnd(theAssociationEnd);
        return null;
    }

    @objid ("30517216-331f-4b1f-9eab-bbea93eccbff")
    @Override
    public Object visitBehavior(Behavior theBehavior) {
        if (this.includeElements) {
            // BehaviorParameter
            addResults(theBehavior.getParameter());
        
            // Event
            addResults(theBehavior.getEComponent());
        
            // Collaboration
            addResults(theBehavior.getOwnedCollaboration());
        }
        super.visitBehavior(theBehavior);
        return null;
    }

    @objid ("255f4a71-337a-4bc7-90ac-3b8dd7e44059")
    @Override
    public Object visitBpmnActivity(final BpmnActivity theBpmnActivity) {
        if (this.includeElements) {
            // BpmnDataInput
            addResults(theBpmnActivity.getInputSpecification());
        
            // BpmnDataOutput
            addResults(theBpmnActivity.getOutputSpecification());
        
            // BpmnBoundaryEvent
            addResults(theBpmnActivity.getBoundaryEventRef());
        }
        super.visitBpmnActivity(theBpmnActivity);
        return null;
    }

    @objid ("531c09a3-f690-4ac3-99f7-cd845d703bce")
    @Override
    public Object visitBpmnSharedDefinitions(BpmnSharedDefinitions theBpmnSharedDefinitions) {
        if (this.includeElements) {
            addResults(theBpmnSharedDefinitions.getRootElement());
        }
        super.visitBpmnSharedDefinitions(theBpmnSharedDefinitions);
        return null;
    }

    /**
     * Participant
     */
    @objid ("9d686799-cafd-4f0e-81bd-3917c9958677")
    @Override
    public Object visitBpmnParticipant(final BpmnParticipant theParticipant) {
        super.visitBpmnParticipant(theParticipant);
        return null;
    }

    @objid ("3e9bbaa2-b4a8-43fd-a94d-f7462114f1e8")
    @Override
    public Object visitBpmnCatchEvent(final BpmnCatchEvent theBpmnCatchEvent) {
        if (this.includeElements) {
            // BpmnDataOutput
            if (theBpmnCatchEvent.getDataOutput() != null) {
                addResult(theBpmnCatchEvent.getDataOutput());
            }
        }
        
        super.visitBpmnCatchEvent(theBpmnCatchEvent);
        return null;
    }

    /**
     * BpmnCollaboration
     * <ol>
     * <li>Participants</li>
     * <li>Message Flows</li>
     * <li>Messages</li>
     * </ol>
     */
    @objid ("9b5e9b2d-6151-4668-adc2-fb493e1d80c6")
    @Override
    public Object visitBpmnCollaboration(final BpmnCollaboration theBpmnCollaboration) {
        if (this.includeElements) {
            BpmnMessageFlowContainer flows = new BpmnMessageFlowContainer(theBpmnCollaboration, new ArrayList<>());
        
            // Participants
            for (BpmnParticipant p : theBpmnCollaboration.getParticipants()) {
                addResult(p);
            }
        
            // MessageFlows
            flows.getContents().addAll(theBpmnCollaboration.getMessageFlow());
        
            // Messages
            flows.getContents().addAll(theBpmnCollaboration.getMessages());
        
            if (!flows.getContents().isEmpty()) {
                addResult(flows);
            }
        
        }
        super.visitBpmnCollaboration(theBpmnCollaboration);
        return null;
    }

    @objid ("9cfda49e-2387-4478-acaa-a03c4c1b31fc")
    @Override
    public Object visitBpmnComplexBehaviorDefinition(final BpmnComplexBehaviorDefinition theBpmnComplexBehaviorDefinition) {
        if (this.includeElements) {
            // BpmnImplicitThrowEvent
            if (theBpmnComplexBehaviorDefinition.getEvent() != null) {
                addResult(theBpmnComplexBehaviorDefinition.getEvent());
            }
        }
        super.visitBpmnComplexBehaviorDefinition(theBpmnComplexBehaviorDefinition);
        return null;
    }

    @objid ("68bc7dc1-352c-4a8b-9ac1-4147802b0ed2")
    @Override
    public Object visitBpmnEvent(final BpmnEvent theBpmnEvent) {
        super.visitBpmnEvent(theBpmnEvent);
        return null;
    }

    @objid ("41e3dee2-ad17-448f-8a07-9bb33d70af78")
    @Override
    public Object visitBpmnFlowNode(final BpmnFlowNode theBpmnFlowNode) {
        if (this.includeElements) {
            // BpmnResourceRole
            addResults(theBpmnFlowNode.getResource());
        }
        super.visitBpmnFlowNode(theBpmnFlowNode);
        return null;
    }

    @objid ("ac848cda-76de-4aa2-be1b-8dda44b58ad0")
    @Override
    public Object visitBpmnInterface(final BpmnInterface theBpmnInterface) {
        if (this.includeElements) {
        
            // BpmnOperation
            addResults(theBpmnInterface.getOperation());
        }
        super.visitBpmnInterface(theBpmnInterface);
        return null;
    }

    @objid ("3d006c4f-8a5c-41e2-8730-c9486150fb72")
    @Override
    public Object visitBpmnItemAwareElement(final BpmnItemAwareElement theBpmnItemAwareElement) {
        if (this.includeElements) {
        
            // BpmnDataState
            if (theBpmnItemAwareElement.getDataState() != null) {
                addResult(theBpmnItemAwareElement.getDataState());
            }
        }
        super.visitBpmnItemAwareElement(theBpmnItemAwareElement);
        return null;
    }

    @objid ("9deb56c5-9948-451e-b5c0-9131c7060fd6")
    @Override
    public Object visitBpmnLane(final BpmnLane theBpmnLane) {
        if (this.includeElements) {
            // BpmnLaneSet
            if (theBpmnLane.getChildLaneSet() != null) {
                addResults(theBpmnLane.getChildLaneSet().getLane());
            }
        }
        
        super.visitBpmnLane(theBpmnLane);
        return null;
    }

    @objid ("a4f2a9f9-789c-488c-90c6-21f1e1d9c5b1")
    @Override
    public Object visitBpmnLaneSet(final BpmnLaneSet theBpmnLane) {
        if (this.includeElements) {
            // BpmnLane
            addResults(theBpmnLane.getLane());
        }
        super.visitBpmnLaneSet(theBpmnLane);
        return null;
    }

    @objid ("5c421890-aec1-4a8f-b7af-7e45e31b6485")
    @Override
    public Object visitBpmnMultiInstanceLoopCharacteristics(final BpmnMultiInstanceLoopCharacteristics theBpmnMultiInstanceLoopCharacteristics) {
        if (this.includeElements) {
            // BpmnDataInput
            if (theBpmnMultiInstanceLoopCharacteristics.getLoopDataInput() != null) {
                addResult(theBpmnMultiInstanceLoopCharacteristics.getLoopDataInput());
            }
        
            // BpmnDataOutput
            if (theBpmnMultiInstanceLoopCharacteristics.getLoopDataOutputRef() != null) {
                addResult(theBpmnMultiInstanceLoopCharacteristics.getLoopDataOutputRef());
            }
        
            // BpmnComplexBehaviorDefinition
            addResults(theBpmnMultiInstanceLoopCharacteristics.getComplexBehaviorDefinition());
        }
        super.visitBpmnMultiInstanceLoopCharacteristics(theBpmnMultiInstanceLoopCharacteristics);
        return null;
    }

    /**
     * Process
     * 
     * <ol>
     * <li>Tasks container containing the process activities</li>
     * <li>Events container containing the process events</li>
     * <li>Roles container containing the process lanes</li>
     * <li>Data container containing the process data objects</li>
     * </ul>
     */
    @objid ("a1f34a52-b84d-4f69-ad3e-1142534ecab7")
    @Override
    public Object visitBpmnProcess(final BpmnProcess theProcess) {
        if (this.includeElements) {
            // Collect tasks
            BpmnTasksContainer tasks = new BpmnTasksContainer(theProcess, theProcess.getFlowElement(BpmnActivity.class));
            if (!tasks.getContents().isEmpty()) {
                addResult(tasks);
            }
        
            // Collect events (any BpmnEvent excepted BPMNBoundaryEvents which are displayed under they owning activity)
            List<BpmnEvent> allEvents = theProcess.getFlowElement(BpmnEvent.class).stream()
                    .filter(e -> !(e instanceof BpmnBoundaryEvent))
                    .collect(Collectors.toList());
            if (!allEvents.isEmpty()) {
                BpmnEventsContainer events = new BpmnEventsContainer(theProcess, allEvents);
                addResult(events);
            }
        
            // Collect roles
            BpmnRolesContainer roles = new BpmnRolesContainer(theProcess, new ArrayList<>());
            if (theProcess.getLaneSet() != null) {
                roles.getContents().addAll(theProcess.getLaneSet().getLane());
            }
            if (!roles.getContents().isEmpty()) {
                addResult(roles);
            }
        
            // Collect data objects
            BpmnDataObjectsContainer dataobjects = new BpmnDataObjectsContainer(theProcess, theProcess.getFlowElement(BpmnItemAwareElement.class));
            if (!dataobjects.getContents().isEmpty()) {
                addResult(dataobjects);
            }
        
            // Gateways are part of the flow model and should NOT be shown
        
            // Collaborations
            addResult(theProcess.getDefinitionalCollaboration());
        }
        super.visitBpmnProcess(theProcess);
        return null;
    }

    @objid ("7366b71e-bf8c-45e2-b999-9430a50df690")
    @Override
    public Object visitBpmnResource(final BpmnResource theBpmnResource) {
        if (this.includeElements) {
        
            // BpmnResourceParameter
            addResults(theBpmnResource.getParameter());
        }
        super.visitBpmnResource(theBpmnResource);
        return null;
    }

    @objid ("4ce89e53-4591-4ed1-bda5-558421420399")
    @Override
    public Object visitBpmnResourceRole(final BpmnResourceRole theBpmnResourceRole) {
        if (this.includeElements) {
        
            // BpmnResourceParameterBinding
            addResults(theBpmnResourceRole.getResourceParameterBinding());
        }
        super.visitBpmnResourceRole(theBpmnResourceRole);
        return null;
    }

    @objid ("cb0dfd38-f198-4909-8ae5-76f541ad88cb")
    @Override
    public Object visitBpmnSequenceFlow(final BpmnSequenceFlow theBpmnSequenceFlow) {
        super.visitBpmnSequenceFlow(theBpmnSequenceFlow);
        return null;
    }

    @objid ("45433af8-2ca7-4dc3-b3c8-c80d84566111")
    @Override
    public Object visitBpmnSubProcess(final BpmnSubProcess theSubProcess) {
        if (this.includeElements) {
        
            // BpmnBoundaryEvent
            addResults(theSubProcess.getBoundaryEventRef());
        
            // Collect tasks
            BpmnTasksContainer tasks = new BpmnTasksContainer(theSubProcess, theSubProcess.getFlowElement(BpmnActivity.class));
            if (!tasks.getContents().isEmpty()) {
                addResult(tasks);
            }
        
            // Collect events (any BpmnEvent excepted BPMNBoundaryEvents which are displayed under they owning activity)
            List<BpmnEvent> allEvents = theSubProcess.getFlowElement(BpmnEvent.class).stream()
                    .filter(e -> !(e instanceof BpmnBoundaryEvent))
                    .collect(Collectors.toList());
            BpmnEventsContainer events = new BpmnEventsContainer(theSubProcess, allEvents);
            if (!events.getContents().isEmpty()) {
                addResult(events);
            }
        
            // Collect roles
            BpmnRolesContainer roles = new BpmnRolesContainer(theSubProcess, new ArrayList<>());
            if (theSubProcess.getLaneSet() != null) {
                roles.getContents().addAll(theSubProcess.getLaneSet().getLane());
            }
            if (!roles.getContents().isEmpty()) {
                addResult(roles);
            }
        
            // Collect data objects
            BpmnDataObjectsContainer dataobjects = new BpmnDataObjectsContainer(theSubProcess, theSubProcess.getFlowElement(BpmnItemAwareElement.class));
            if (!dataobjects.getContents().isEmpty()) {
                addResult(dataobjects);
            }
        
            // Gateways are part of the flow model and should NOT be shown
        }
        return null;
    }

    @objid ("35eb000a-6887-4871-ae60-4819e99a9057")
    @Override
    public Object visitBpmnThrowEvent(final BpmnThrowEvent theBpmnThrowEvent) {
        if (this.includeElements) {
            // BpmnDataInput
            if (theBpmnThrowEvent.getDataInput() != null) {
                addResult(theBpmnThrowEvent.getDataInput());
            }
        }
        super.visitBpmnThrowEvent(theBpmnThrowEvent);
        return null;
    }

    @objid ("9db2a597-1fa1-4d54-a0a3-343ecd1e496c")
    @Override
    public Object visitClassifier(Classifier theClassifier) {
        if (this.includeElements) {
        
            // Generalization
            addResults(theClassifier.getParent());
        
            // InterfaceRealization
            addResults(theClassifier.getRealized());
        
            // TemplateParameter
            addResults(theClassifier.getTemplate());
        
            // TemplateBinding
            addResults(theClassifier.getTemplateInstanciation());
        
            // Attribute
            addResults(theClassifier.getOwnedAttribute());
        
            // AssociationEnd
            for (final AssociationEnd end : theClassifier.getOwnedEnd()) {
                if (end.isNavigable() || (end.getOpposite() != null && !end.getOpposite().isNavigable())) {
                    addResult(end);
                }
            }
        
            // NaryAssociationEnd
            addResults(theClassifier.getOwnedNaryEnd());
        
            // Operation
            addResults(theClassifier.getOwnedOperation());
        
            List<BindableInstance> internalStructure = new ArrayList<>(theClassifier.getInternalStructure());
            Collections.sort(internalStructure, this.standardSorter);
            addResults(internalStructure);
        }
        
        if (this.includeLinks) {
            // Substitutions
            addResults(theClassifier.getSubstitued());
        
            // Component realizations
            addResults(theClassifier.getRealizedComponent());
        }
        super.visitClassifier(theClassifier);
        return null;
    }

    @objid ("6c930db5-7adf-4f04-9cee-1885117610a9")
    @Override
    public Object visitClause(Clause theClause) {
        if (this.includeElements) {
            List<ActivityNode> body = new ArrayList<>(theClause.getBody());
            Collections.sort(body, this.standardSorter);
            addResults(body);
        }
        super.visitClause(theClause);
        return null;
    }

    @objid ("54fae08e-63c2-4cf1-a92d-ea7b33cbf67f")
    @Override
    public Object visitCollaboration(Collaboration theCollaboration) {
        if (this.includeElements) {
            // Generalization
            addResults(theCollaboration.getParent());
        
            // InterfaceRealization
            addResults(theCollaboration.getRealized());
        
            // TemplateParameter
            addResults(theCollaboration.getTemplate());
        
            // TemplateBinding
            addResults(theCollaboration.getTemplateInstanciation());
        }
        
        super.visitCollaboration(theCollaboration);
        return null;
    }

    @objid ("79e34add-4c0b-4a55-8c5b-f4e742bf1f2f")
    @Override
    public Object visitCollaborationUse(CollaborationUse theCollaborationUse) {
        if (this.includeElements) {
            // Binding
            addResults(theCollaborationUse.getRoleBinding());
        }
        super.visitCollaborationUse(theCollaborationUse);
        return null;
    }

    @objid ("a7dc3640-0ce9-447a-a802-8f7a9a0cff69")
    @Override
    public Object visitCommunicationChannel(CommunicationChannel theCommunicationChannel) {
        if (this.includeElements) {
            // CommunicationMessages
            addResults(theCommunicationChannel.getStartToEndMessage());
            addResults(theCommunicationChannel.getEndToStartMessage());
        }
        super.visitCommunicationChannel(theCommunicationChannel);
        return null;
    }

    @objid ("0a4ccd18-09de-4398-814f-0864789d1edc")
    @Override
    public Object visitCommunicationInteraction(CommunicationInteraction theCommunicationInteraction) {
        if (this.includeElements) {
            // CommunicationNode
            addResults(theCommunicationInteraction.getOwned());
        }
        super.visitCommunicationInteraction(theCommunicationInteraction);
        return null;
    }

    @objid ("885de237-543d-409e-b5e3-25bb871a26f5")
    @Override
    public Object visitCommunicationNode(CommunicationNode theCommunicationNode) {
        if (this.includeElements) {
            // CommunicationChannel
            addResults(theCommunicationNode.getStarted());
        }
        super.visitCommunicationNode(theCommunicationNode);
        return null;
    }

    @objid ("ff682253-63b8-4f46-bd61-b1099192fda5")
    @Override
    public Object visitConditionalNode(ConditionalNode theConditionalNode) {
        if (this.includeElements) {
            // Clause
            addResults(theConditionalNode.getOwnedClause());
        }
        super.visitConditionalNode(theConditionalNode);
        return null;
    }

    @objid ("11c8fda4-1145-4536-b904-148e6d6d4f42")
    @Override
    public Object visitEnumeration(Enumeration theEnumeration) {
        if (this.includeElements) {
            addResults(theEnumeration.getValue());
        }
        super.visitEnumeration(theEnumeration);
        return null;
    }

    @objid ("09addec5-8aa5-4830-8973-318460e4b0b9")
    @Override
    public Object visitExpansionRegion(ExpansionRegion theExpansionRegion) {
        if (this.includeElements) {
            addResults(theExpansionRegion.getInputElement());
            addResults(theExpansionRegion.getOutputElement());
        }
        super.visitExpansionRegion(theExpansionRegion);
        return null;
    }

    @objid ("e1edcd5a-1dd6-4fce-90a3-105c327e6da3")
    @Override
    public Object visitInstance(Instance theInstance) {
        if (this.includeElements) {
            List<BindableInstance> part = new ArrayList<>(theInstance.getPart());
            Collections.sort(part, this.standardSorter);
            addResults(part);
        
            // AttributeLink
            addResults(theInstance.getSlot());
        
            List<LinkEnd> ownedEnd = new ArrayList<>(theInstance.getOwnedEnd());
            Collections.sort(ownedEnd, this.standardSorter);
            addResults(ownedEnd);
        
            // NaryLinkEnd
            addResults(theInstance.getOwnedNaryEnd());
        }
        super.visitInstance(theInstance);
        return null;
    }

    @objid ("7dea258e-da22-4c08-bf7f-cbc61fa1749d")
    @Override
    public Object visitLinkEnd(LinkEnd theLinkEnd) {
        if (this.includeElements) {
            addResult(theLinkEnd.getLink());
        }
        super.visitLinkEnd(theLinkEnd);
        return null;
    }

    @objid ("257e8d23-8661-4c50-b8cb-0a1cf61059f1")
    @Override
    public Object visitModelTree(ModelTree theModelTree) {
        if (this.includeElements) {
            List<ModelTree> res = new ArrayList<>(theModelTree.getOwnedElement());
            Collections.sort(res, this.standardSorter);
            addResults(res);
        }
        super.visitModelTree(theModelTree);
        return null;
    }

    @objid ("b2fcef71-279b-4125-8bfc-94eefe632424")
    @Override
    public Object visitNameSpace(NameSpace theNameSpace) {
        if (this.includeLinks) {
            // ElementImport
            addResults(theNameSpace.getOwnedImport());
        
            // PackageImport
            addResults(theNameSpace.getOwnedPackageImport());
        
            // InformationFlow
            addResults(theNameSpace.getOwnedInformationFlow());
        }
        
        if (this.includeElements) {
            // NOTE: Generalization, InterfaceRealization,
            // TemplateParameter, TemplateBinding are processed by
            // subclasses Collaboration, Classifier, Package in order to
            // control the ordering of elements in the browser.
        
            // Declared instances
            List<Instance> declared = new ArrayList<>(theNameSpace.getDeclared());
            Collections.sort(declared, this.standardSorter);
            addResults(declared);
        
            // CollaborationUse
            addResults(theNameSpace.getOwnedCollaborationUse());
        
            List<Behavior> behavior = new ArrayList<>(theNameSpace.getOwnedBehavior());
            Collections.sort(behavior, this.standardSorter);
            addResults(behavior);
        
            // DataFlow
            addResults(theNameSpace.getOwnedDataFlow());
        }
        super.visitNameSpace(theNameSpace);
        return null;
    }

    @objid ("880bd407-8ff7-408e-849b-43a0eb39f002")
    @Override
    public Object visitNaryAssociationEnd(NaryAssociationEnd theNaryAssociationEnd) {
        if (this.includeElements) {
            final NaryAssociation association = theNaryAssociationEnd.getNaryAssociation();
            if (association != null) {
                final ClassAssociation linkToClass = association.getLinkToClass();
                if (linkToClass != null) {
                    addResult(linkToClass);
                }
            }
        }
        super.visitNaryAssociationEnd(theNaryAssociationEnd);
        return null;
    }

    @objid ("0c997ffc-f17e-40b1-b3fc-c2618fe6d1d5")
    @Override
    public Object visitOperation(Operation theOperation) {
        if (this.includeLinks) {
            // ElementImport
            addResults(theOperation.getOwnedImport());
        
            // PackageImport
            addResults(theOperation.getOwnedPackageImport());
        }
        
        if (this.includeElements) {
            // ReturnParameter
            final Parameter returnParameter = theOperation.getReturn();
            if (returnParameter != null) {
                addResult(returnParameter);
            }
        
            // IOParameter
            addResults(theOperation.getIO());
        
            // RaisedException
            addResults(theOperation.getThrown());
        
            // TemplateParameter
            addResults(theOperation.getTemplate());
        
            // TemplateBinding
            addResults(theOperation.getTemplateInstanciation());
        
            // Collaboration
            addResults(theOperation.getExample());
        
            // CollaborationUse
            addResults(theOperation.getOwnedCollaborationUse());
        
            List<Behavior> behavior = new ArrayList<>(theOperation.getOwnedBehavior());
            Collections.sort(behavior, this.standardSorter);
            addResults(behavior);
        
        }
        super.visitOperation(theOperation);
        return null;
    }

    @objid ("c881b0e9-bbe0-4639-bc4a-5d2dd8d4e104")
    @Override
    public Object visitPackage(org.modelio.metamodel.uml.statik.Package thePackage) {
        if (this.includeLinks) {
            // Generalization
            addResults(thePackage.getParent());
        
            // InterfaceRealization
            addResults(thePackage.getRealized());
        
            // TemplateParameter
            addResults(thePackage.getTemplate());
        
            // TemplateBinding
            addResults(thePackage.getTemplateInstanciation());
        
            // PackageMerge
            addResults(thePackage.getMerge());
        }
        super.visitPackage(thePackage);
        return null;
    }

    @objid ("34caf95c-7668-41a0-bcb5-2b95b35ac88e")
    @Override
    public Object visitPort(Port thePort) {
        if (this.includeElements) {
            // ProvidedInterface
            addResults(thePort.getProvided());
        
            // RequiredInterface
            addResults(thePort.getRequired());
        }
        super.visitPort(thePort);
        return null;
    }

    @objid ("9a3385f8-6223-4f81-8e5c-7929bf0a8c86")
    @Override
    public Object visitProject(Project theProject) {
        if (this.includeElements) {
            for (org.modelio.metamodel.uml.statik.Package root : theProject.getModel()) {
                addResult(root);
            }
        }
        super.visitProject(theProject);
        return null;
    }

    @objid ("8cec60fe-90da-4fcd-8cea-61ab876138a3")
    @Override
    public Object visitRegion(Region theRegion) {
        if (this.includeElements) {
            addResults(theRegion.getSub());
        }
        super.visitRegion(theRegion);
        return null;
    }

    @objid ("6462240e-4263-4eee-a865-236c7590873a")
    @Override
    public Object visitState(State theState) {
        if (this.includeElements) {
            // Region
            addResults(theState.getOwnedRegion());
        
            // Entry and Exit Point
            addResults(theState.getEntryPoint());
            addResults(theState.getExitPoint());
        
            // InternalTransition
            addResults(theState.getInternal());
        
            // ConnectionPointReference
            addResults(theState.getConnection());
        }
        super.visitState(theState);
        return null;
    }

    @objid ("be3ed5fd-6a5d-4439-a678-0e545ff81311")
    @Override
    public Object visitStateMachine(StateMachine theStateMachine) {
        if (this.includeElements) {
        
            // Entry and Exit point are directly on the StateMachine
            addResults(theStateMachine.getEntryPoint());
            addResults(theStateMachine.getExitPoint());
        
            // the visible children of the StateMachine are the children of
            // its
            // Top State
            final Region theRegion = theStateMachine.getTop();
            addResults(theRegion.getSub());
        
        }
        super.visitStateMachine(theStateMachine);
        return null;
    }

    @objid ("fb7a0c64-121e-4168-b64a-dea053fcba47")
    @Override
    public Object visitStateVertex(StateVertex theStateVertex) {
        if (this.includeElements) {
            List<Transition> outGoing = new ArrayList<>(theStateVertex.getOutGoing());
            Collections.sort(outGoing, this.standardSorter);
            addResults(outGoing);
        }
        super.visitStateVertex(theStateVertex);
        return null;
    }

    @objid ("e7d841ad-1962-4d00-95ed-374dbaa1efa5")
    @Override
    public Object visitStructuredActivityNode(StructuredActivityNode theStructuredActivityNode) {
        if (this.includeElements) {
            List<ActivityNode> body = new ArrayList<>(theStructuredActivityNode.getBody());
            Collections.sort(body, this.standardSorter);
            addResults(body);
        }
        super.visitStructuredActivityNode(theStructuredActivityNode);
        return null;
    }

    @objid ("a260d767-dcda-4775-a16f-860148a82166")
    @Override
    public Object visitTemplateBinding(TemplateBinding theTemplateBinding) {
        if (this.includeElements) {
            // TemlplateParameterSubstitution
            addResults(theTemplateBinding.getParameterSubstitution());
        }
        super.visitTemplateBinding(theTemplateBinding);
        return null;
    }

    @objid ("d08771b7-b417-48ff-a0b4-816d1c334786")
    @Override
    public Object visitTemplateParameter(TemplateParameter theTemplateParameter) {
        if (this.includeElements) {
            // TemlplateParameterSubstitution
            final ModelElement modelElement = theTemplateParameter.getOwnedParameterElement();
            if (modelElement != null) {
                addResult(modelElement);
            }
        }
        super.visitTemplateParameter(theTemplateParameter);
        return null;
    }

    @objid ("2c25d3bc-d01a-4272-87f5-04cc2828f3dc")
    @Override
    public Object visitUseCase(UseCase theUseCase) {
        if (this.includeElements) {
            // ExtensionPoint
            addResults(theUseCase.getOwnedExtension());
        
            addResults(theUseCase.getUsed());
        }
        super.visitUseCase(theUseCase);
        return null;
    }

    /**
     * Avoid having a duplicated element in the result, but preserves order unlike a Set.
     * @param elt the element to add.
     */
    @objid ("9603c2d1-df56-4ed0-8090-7c9734bb87ec")
    private void addResult(Object elt) {
        if (elt != null && !this.result.contains(elt)) {
            this.result.add(elt);
        }
        
    }

    /**
     * Avoid having duplicated elements in the result, but preserves order unlike a Set.
     * @param objects the element to add.
     */
    @objid ("2d1c1b40-a3d9-442a-b7cc-6e5baec7a3b3")
    private void addResults(List<? extends MObject> objects) {
        for (Object elt : objects) {
            addResult(elt);
        }
        
    }

    /**
     * Get the children to display into the given element. The method configure the visitor (setting with elements/links options) and call the accept() method.
     * @param parent the element where children will be looked for
     * @return The children to display when expanding the tree node.
     */
    @objid ("eefeed65-9453-40c7-99b7-04d399082c30")
    private List<Object> getChildren(final MObject parent, boolean withElements, boolean withLinks) {
        this.includeElements = withElements;
        this.includeLinks = withLinks;
        
        // Clear the collecting list
        this.result = new ArrayList<>();
        
        // Start the 'visit'
        parent.accept(this);
        
        // Return results
        List<Object> localList = this.result;
        this.result = null; // Makes garbaging of the list more likely to happen
        return localList;
    }

}
