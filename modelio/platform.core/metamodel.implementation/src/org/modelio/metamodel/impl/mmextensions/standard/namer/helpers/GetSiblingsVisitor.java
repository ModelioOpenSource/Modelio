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

package org.modelio.metamodel.impl.mmextensions.standard.namer.helpers;

import java.util.HashSet;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnProcessCollaborationDiagram;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnSubProcessDiagram;
import org.modelio.metamodel.bpmn.bpmnService.BpmnInterface;
import org.modelio.metamodel.bpmn.bpmnService.BpmnOperation;
import org.modelio.metamodel.bpmn.flows.BpmnMessage;
import org.modelio.metamodel.bpmn.flows.BpmnMessageFlow;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.objects.BpmnItemDefinition;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnCollaboration;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLane;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnLaneSet;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnParticipant;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.resources.BpmnResource;
import org.modelio.metamodel.bpmn.resources.BpmnResourceParameter;
import org.modelio.metamodel.bpmn.resources.BpmnResourceParameterBinding;
import org.modelio.metamodel.bpmn.resources.BpmnResourceRole;
import org.modelio.metamodel.bpmn.rootElements.BpmnArtifact;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowElement;
import org.modelio.metamodel.bpmn.rootElements.BpmnFlowNode;
import org.modelio.metamodel.bpmn.rootElements.BpmnSharedDefinitions;
import org.modelio.metamodel.bpmn.rootElements.BpmnSharedElement;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.mda.Project;
import org.modelio.metamodel.uml.behavior.activityModel.Activity;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityAction;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityNode;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityPartition;
import org.modelio.metamodel.uml.behavior.activityModel.Clause;
import org.modelio.metamodel.uml.behavior.activityModel.ExpansionNode;
import org.modelio.metamodel.uml.behavior.activityModel.ExpansionRegion;
import org.modelio.metamodel.uml.behavior.activityModel.InputPin;
import org.modelio.metamodel.uml.behavior.activityModel.InterruptibleActivityRegion;
import org.modelio.metamodel.uml.behavior.activityModel.OutputPin;
import org.modelio.metamodel.uml.behavior.activityModel.StructuredActivityNode;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.uml.behavior.commonBehaviors.BehaviorParameter;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Event;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationChannel;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationInteraction;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationMessage;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationNode;
import org.modelio.metamodel.uml.behavior.stateMachineModel.AbstractPseudoState;
import org.modelio.metamodel.uml.behavior.stateMachineModel.ConnectionPointReference;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Region;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateVertex;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Transition;
import org.modelio.metamodel.uml.behavior.usecaseModel.ExtensionPoint;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.AttributeLink;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.CollaborationUse;
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.metamodel.uml.statik.EnumerationLiteral;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.LinkEnd;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.metamodel.visitors.DefaultInfrastructureVisitor;
import org.modelio.metamodel.visitors.DefaultModelVisitor;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("0068d1de-030f-1035-9f91-001ec947cd2a")
public class GetSiblingsVisitor extends DefaultModelVisitor {
    @objid ("006d42f0-0311-1035-9f91-001ec947cd2a")
    private Set<String> results;

    @objid ("00690578-030f-1035-9f91-001ec947cd2a")
    public GetSiblingsVisitor() {
        this(new GetSiblingsInfraVisitor());
    }

    @objid ("006912f2-030f-1035-9f91-001ec947cd2a")
    public Set<String> getSiblings(Element element) {
        this.results = new HashSet<>(16);
        element.accept(this);
        this.results.remove(element.getName());
        return this.results;
    }

    @objid ("00697ec2-030f-1035-9f91-001ec947cd2a")
    @Override
    public Object visitActivityNode(ActivityNode theActivityNode) {
        Activity activity = theActivityNode.getOwner();
        if (activity != null) {
            for (Element e : activity.getOwnedNode()) {
                this.results.add(e.getName());
            }
            return null;
        }
        
        Clause clause = theActivityNode.getOwnerClause();
        if (clause != null) {
            for (Element e : clause.getBody()) {
                this.results.add(e.getName());
            }
            return null;
        }
        
        StructuredActivityNode node = theActivityNode.getOwnerNode();
        if (node != null) {
            for (Element e : node.getBody()) {
                this.results.add(e.getName());
            }
            return null;
        }
        
        ActivityPartition partition = theActivityNode.getOwnerPartition();
        if (partition != null) {
            for (Element e : partition.getContainedNode()) {
                this.results.add(e.getName());
            }
        }
        return null;
    }

    @objid ("0069b4c8-030f-1035-9f91-001ec947cd2a")
    @Override
    public Object visitActivityPartition(ActivityPartition theActivityPartition) {
        ActivityPartition superPartition = theActivityPartition.getSuperPartition();
        if (superPartition != null) {
            for (Element e : superPartition.getSubPartition()) {
                this.results.add(e.getName());
            }
            return null;
        }
        Activity activity = theActivityPartition.getInActivity();
        if (activity != null) {
            // Filtering because IInterruptibleActivityRegion is also an
            // IActivityGroup.
            for (Element e : activity.getOwnedGroup(ActivityPartition.class)) {
                this.results.add(e.getName());
            }
        }
        return null;
    }

    @objid ("0069eaba-030f-1035-9f91-001ec947cd2a")
    @Override
    public Object visitAttribute(Attribute theAttribute) {
        AssociationEnd owner = theAttribute.getQualified();
        Classifier owner2 = theAttribute.getOwner();
        if (owner != null) {
            for (Element e : owner.getQualifier()) {
                this.results.add(e.getName());
            }
        } else if (owner2 != null) {
            for (Element e : owner2.getOwnedAttribute()) {
                this.results.add(e.getName());
            }
        }
        return null;
    }

    @objid ("006a20e8-030f-1035-9f91-001ec947cd2a")
    @Override
    public Object visitAttributeLink(AttributeLink theElement) {
        Instance owner = theElement.getAttributed();
        if (owner != null) {
            for (Element e : owner.getSlot()) {
                this.results.add(e.getName());
            }
            for (Element e : owner.getOwnedEnd()) {
                this.results.add(e.getName());
            }
        }
        return null;
    }

    @objid ("006a5798-030f-1035-9f91-001ec947cd2a")
    @Override
    public Object visitBehaviorParameter(BehaviorParameter theBehaviorParameter) {
        Behavior theBehavior = theBehaviorParameter.getOwner();
        if (theBehavior != null) {
            for (Element e : theBehavior.getParameter()) {
                this.results.add(e.getName());
            }
        }
        return null;
    }

    @objid ("006a8e52-030f-1035-9f91-001ec947cd2a")
    @Override
    public Object visitBehavior(Behavior theBehavior) {
        NameSpace nsOwner = theBehavior.getOwner();
        Operation opOwner = theBehavior.getOwnerOperation();
        
        if (nsOwner != null) {
            for (Element e : nsOwner.getOwnedBehavior()) {
                this.results.add(e.getName());
            }
        } else if (opOwner != null) {
            for (Element e : opOwner.getOwnedBehavior()) {
                this.results.add(e.getName());
            }
        }
        return null;
    }

    @objid ("006ac552-030f-1035-9f91-001ec947cd2a")
    @Override
    public Object visitBindableInstance(BindableInstance theElement) {
        Classifier classifierOwner = theElement.getInternalOwner();
        Instance cluster = theElement.getCluster();
        
        if (classifierOwner != null) {
            for (Element e : classifierOwner.getInternalStructure()) {
                this.results.add(e.getName());
            }
        } else if (cluster != null) {
            for (Element e : cluster.getPart()) {
                this.results.add(e.getName());
            }
        } else {
            visitInstance(theElement);
        }
        return null;
    }

    @objid ("006afcde-030f-1035-9f91-001ec947cd2a")
    @Override
    public Object visitBpmnArtifact(final BpmnArtifact theElement) {
        BpmnSubProcess ownersubprocess = theElement.getSubProcess();
        if (ownersubprocess != null) {
            for (Element e : ownersubprocess.getArtifact()) {
                this.results.add(e.getName());
            }
        }
        
        BpmnProcess process = theElement.getProcess();
        if (process != null) {
            for (Element e : process.getArtifact()) {
                this.results.add(e.getName());
            }
        }
        
        BpmnCollaboration collaboration = theElement.getCollaboration();
        if (collaboration != null) {
            for (Element e : collaboration.getArtifact()) {
                this.results.add(e.getName());
            }
        }
        return null;
    }

    @objid ("006b4842-030f-1035-9f91-001ec947cd2a")
    @Override
    public Object visitBpmnFlowElement(BpmnFlowElement theModelTree) {
        BpmnSubProcess owner_sp = theModelTree.getSubProcess();
        BpmnProcess owner_pr = theModelTree.getContainer();
        if (owner_sp != null) {
            for (Element e : owner_sp.getFlowElement()) {
                this.results.add(e.getName());
            }
        }
        
        if (owner_pr != null) {
            for (Element e : owner_pr.getFlowElement()) {
                this.results.add(e.getName());
            }
        }
        return null;
    }

    @objid ("006b7ff6-030f-1035-9f91-001ec947cd2a")
    @Override
    public Object visitBpmnLane(final BpmnLane theBpmnLane) {
        BpmnLaneSet owner = theBpmnLane.getLaneSet();
        
        if (owner != null) {
            if (owner.getProcess() != null) {
                BpmnLaneSet laneset = owner.getProcess().getLaneSet();
                if (laneset != null) {
                    for (Element e : laneset.getLane()) {
                        this.results.add(e.getName());
                    }
                }
            } else {
                for (Element e : owner.getLane()) {
                    this.results.add(e.getName());
                }
            }
        
        }
        return null;
    }

    @objid ("006bc22c-030f-1035-9f91-001ec947cd2a")
    @Override
    public Object visitBpmnMessageFlow(final BpmnMessageFlow theBpmnMessageFlow) {
        BpmnCollaboration owner = theBpmnMessageFlow.getCollaboration();
        if (owner != null) {
            for (Element e : owner.getMessageFlow()) {
                this.results.add(e.getName());
            }
        }
        return null;
    }

    @objid ("006d608c-030f-1035-9f91-001ec947cd2a")
    @Override
    public Object visitBpmnOperation(final BpmnOperation theElement) {
        BpmnInterface owner = theElement.getBpmnInterfaceRef();
        if (owner != null) {
            for (Element e : owner.getOperation()) {
                this.results.add(e.getName());
            }
        }
        return null;
    }

    @objid ("006da40c-030f-1035-9f91-001ec947cd2a")
    @Override
    public Object visitBpmnProcessCollaborationDiagram(final BpmnProcessCollaborationDiagram theBpmnProcessCollaborationDiagram) {
        ModelElement owner = theBpmnProcessCollaborationDiagram.getOrigin();
        if (owner != null) {
            for (Element e : owner.getProduct()) {
                this.results.add(e.getName());
            }
        }
        return null;
    }

    @objid ("006de7aa-030f-1035-9f91-001ec947cd2a")
    @Override
    public Object visitBpmnResourceParameterBinding(final BpmnResourceParameterBinding theElement) {
        BpmnResourceRole owner = theElement.getResourceRole();
        if (owner != null) {
            for (Element e : owner.getResourceParameterBinding()) {
                this.results.add(e.getName());
            }
        }
        return null;
    }

    @objid ("006e2b5c-030f-1035-9f91-001ec947cd2a")
    @Override
    public Object visitBpmnResourceParameter(final BpmnResourceParameter theElement) {
        BpmnResource owner = theElement.getResource();
        if (owner != null) {
            for (Element e : owner.getParameter()) {
                this.results.add(e.getName());
            }
        }
        return null;
    }

    @objid ("006e6f0e-030f-1035-9f91-001ec947cd2a")
    @Override
    public Object visitBpmnResourceRole(final BpmnResourceRole theElement) {
        BpmnFlowNode ownernode = theElement.getAnnotated();
        if (ownernode != null) {
            for (Element e : ownernode.getResource()) {
                this.results.add(e.getName());
            }
        }
        
        BpmnProcess ownerprocess = theElement.getProcess();
        if (ownerprocess != null) {
            for (Element e : ownerprocess.getResource()) {
                this.results.add(e.getName());
            }
        }
        return null;
    }

    @objid ("006eb36a-030f-1035-9f91-001ec947cd2a")
    @Override
    public Object visitBpmnSharedElement(final BpmnSharedElement theBpmnRootElement) {
        BpmnSharedDefinitions owner = theBpmnRootElement.getOwner();
        if (owner != null) {
            for (Element e : owner.getRootElement()) {
                this.results.add(e.getName());
            }
        }
        return super.visitBpmnSharedElement(theBpmnRootElement);
    }

    @objid ("006ef7bc-030f-1035-9f91-001ec947cd2a")
    @Override
    public Object visitBpmnSequenceFlow(final BpmnSequenceFlow theBpmnSequenceFlow) {
        if (theBpmnSequenceFlow.getContainer() != null) {
            BpmnProcess owner = theBpmnSequenceFlow.getContainer();
            for (Element e : owner.getFlowElement()) {
                this.results.add(e.getName());
            }
        
        } else if (theBpmnSequenceFlow.getSubProcess() != null) {
            BpmnSubProcess owner = theBpmnSequenceFlow.getSubProcess();
            for (Element e : owner.getFlowElement()) {
                this.results.add(e.getName());
            }
        }
        return null;
    }

    @objid ("006f3c86-030f-1035-9f91-001ec947cd2a")
    @Override
    public Object visitBpmnSubProcessDiagram(final BpmnSubProcessDiagram theBpmnSubProcessDiagram) {
        ModelElement owner = theBpmnSubProcessDiagram.getOrigin();
        if (owner != null) {
            for (Element e : owner.getProduct()) {
                this.results.add(e.getName());
            }
        }
        return null;
    }

    @objid ("006f8146-030f-1035-9f91-001ec947cd2a")
    @Override
    public Object visitCollaborationUse(CollaborationUse theCollaborationUse) {
        Operation opOwner = theCollaborationUse.getORepresented();
        NameSpace nOwner = theCollaborationUse.getNRepresented();
        
        if (opOwner != null) {
            for (Element e : opOwner.getOwnedCollaborationUse()) {
                this.results.add(e.getName());
            }
        } else if (nOwner != null) {
            for (Element e : nOwner.getOwnedCollaborationUse()) {
                this.results.add(e.getName());
            }
        }
        return null;
    }

    @objid ("006fbbca-030f-1035-9f91-001ec947cd2a")
    @Override
    public Object visitCommunicationMessage(CommunicationMessage theCommunicationMessage) {
        CommunicationChannel channel = theCommunicationMessage.getChannel();
        if (channel != null) {
            for (Element e : channel.getStartToEndMessage()) {
                this.results.add(e.getName());
            }
        }
        
        channel = theCommunicationMessage.getInvertedChannel();
        if (channel != null) {
            for (Element e : channel.getEndToStartMessage()) {
                this.results.add(e.getName());
            }
        
        }
        return null;
    }

    @objid ("006ff6da-030f-1035-9f91-001ec947cd2a")
    @Override
    public Object visitCommunicationNode(CommunicationNode theCommunicationNode) {
        CommunicationInteraction owner = theCommunicationNode.getOwner();
        
        if (owner != null) {
            for (Element e : owner.getOwned()) {
                this.results.add(e.getName());
            }
        }
        return null;
    }

    @objid ("00706ef8-030f-1035-9f91-001ec947cd2a")
    @Override
    public Object visitConnectionPointReference(ConnectionPointReference theConnectionPointReference) {
        State connectionPointReferenceOwner = theConnectionPointReference.getOwnerState();
        
        if (connectionPointReferenceOwner != null) {
            for (Element e : connectionPointReferenceOwner.getConnection()) {
                this.results.add(e.getName());
            }
        }
        return null;
    }

    @objid ("00712212-030f-1035-9f91-001ec947cd2a")
    @Override
    public Object visitEnumerationLiteral(EnumerationLiteral theEnumerationLiteral) {
        Enumeration enumeration = theEnumerationLiteral.getValuated();
        if (enumeration != null) {
            for (Element e : enumeration.getValue()) {
                this.results.add(e.getName());
            }
        }
        return null;
    }

    @objid ("00715e08-030f-1035-9f91-001ec947cd2a")
    @Override
    public Object visitEvent(Event theEvent) {
        StateMachine eventOwner = (StateMachine) theEvent.getComposed();
        
        if (eventOwner != null) {
            for (Element e : eventOwner.getEComponent()) {
                this.results.add(e.getName());
            }
        }
        return null;
    }

    @objid ("00719a94-030f-1035-9f91-001ec947cd2a")
    @Override
    public Object visitExpansionNode(ExpansionNode theExpansionNode) {
        ExpansionRegion region = theExpansionNode.getRegionAsInput();
        if (region == null) {
            region = theExpansionNode.getRegionAsOutput();
        }
        
        if (region != null) {
            for (Element e : region.getInputElement()) {
                this.results.add(e.getName());
            }
            for (Element e : region.getOutputElement()) {
                this.results.add(e.getName());
            }
        }
        return null;
    }

    @objid ("0071d716-030f-1035-9f91-001ec947cd2a")
    @Override
    public Object visitExtensionPoint(ExtensionPoint theExtensionPoint) {
        UseCase theUseCase = theExtensionPoint.getOwner();
        if (theUseCase != null) {
            for (Element e : theUseCase.getOwnedExtension()) {
                this.results.add(e.getName());
            }
        }
        return null;
    }

    @objid ("007213e8-030f-1035-9f91-001ec947cd2a")
    @Override
    public Object visitInputPin(InputPin theInputPin) {
        ActivityAction owner = theInputPin.getInputing();
        
        if (owner != null) {
            for (Element e : owner.getInput()) {
                this.results.add(e.getName());
            }
        }
        return null;
    }

    @objid ("00725150-030f-1035-9f91-001ec947cd2a")
    @Override
    public Object visitInstance(Instance theInstance) {
        NameSpace owner = theInstance.getOwner();
        for (Element e : owner.getDeclared()) {
            this.results.add(e.getName());
        }
        return null;
    }

    @objid ("00728ec2-030f-1035-9f91-001ec947cd2a")
    @Override
    public Object visitInterruptibleActivityRegion(InterruptibleActivityRegion theInterruptibleActivityRegion) {
        Activity activity = theInterruptibleActivityRegion.getInActivity();
        if (activity != null) {
            // Filtering because IActivityPartition is also an
            // IActivityGroup.
            for (Element e : activity.getOwnedGroup(InterruptibleActivityRegion.class)) {
                this.results.add(e.getName());
            }
        }
        return null;
    }

    @objid ("0072ccca-030f-1035-9f91-001ec947cd2a")
    @Override
    public Object visitLinkEnd(LinkEnd theElement) {
        Instance owner = theElement.getTarget();
        if (owner != null) {
            for (Element e : owner.getSlot()) {
                this.results.add(e.getName());
            }
            for (Element e : owner.getOwnedEnd()) {
                this.results.add(e.getName());
            }
        }
        return null;
    }

    @objid ("00730b36-030f-1035-9f91-001ec947cd2a")
    @Override
    public Object visitModelTree(ModelTree theModelTree) {
        ModelTree owner = theModelTree.getOwner();
        if (owner != null) {
            for (Element e : owner.getOwnedElement()) {
                this.results.add(e.getName());
            }
        }
        return null;
    }

    @objid ("0073d37c-030f-1035-9f91-001ec947cd2a")
    @Override
    public Object visitOperation(Operation theOperation) {
        Classifier owner = theOperation.getOwner();
        if (owner != null) {
            for (Element e : owner.getOwnedOperation()) {
                if (theOperation.isStereotyped("ModelerModule", "create")) {
                    continue;
                } else if (theOperation.isStereotyped("ModelerModule", "destroy")) {
                    continue;
                }
                this.results.add(e.getName());
            }
        }
        return null;
    }

    @objid ("0074135a-030f-1035-9f91-001ec947cd2a")
    @Override
    public Object visitOutputPin(OutputPin theOutputPin) {
        ActivityAction owner = theOutputPin.getOutputing();
        
        if (owner != null) {
            for (Element e : owner.getOutput()) {
                this.results.add(e.getName());
            }
        }
        return null;
    }

    @objid ("0074532e-030f-1035-9f91-001ec947cd2a")
    @Override
    public Object visitParameter(Parameter theParameter) {
        Operation owner = theParameter.getComposed();
        if (owner != null) {
            for (Element e : owner.getIO()) {
                this.results.add(e.getName());
            }
            // result.add (owner.getReturn());
        }
        return null;
    }

    @objid ("0075d7e4-030f-1035-9f91-001ec947cd2a")
    @Override
    public Object visitRegion(Region theRegion) {
        State regionOwner = theRegion.getParent();
        
        if (regionOwner != null) {
            for (Element e : regionOwner.getOwnedRegion()) {
                this.results.add(e.getName());
            }
        }
        return null;
    }

    @objid ("00765dcc-030f-1035-9f91-001ec947cd2a")
    @Override
    public Object visitState(State theState) {
        Region stateOwner = theState.getParent();
        
        if (stateOwner != null) {
            for (Element e : stateOwner.getSub()) {
                this.results.add(e.getName());
            }
        }
        return null;
    }

    @objid ("007725cc-030f-1035-9f91-001ec947cd2a")
    @Override
    public Object visitTemplateParameter(TemplateParameter theTemplateParameter) {
        final NameSpace nowner = theTemplateParameter.getParameterized();
        if (nowner != null) {
            for (Element e : nowner.getTemplate()) {
                this.results.add(e.getName());
            }
        }
        
        final Operation oowner = theTemplateParameter.getParameterizedOperation();
        if (oowner != null) {
            for (Element e : oowner.getTemplate()) {
                this.results.add(e.getName());
            }
        }
        return null;
    }

    @objid ("00776898-030f-1035-9f91-001ec947cd2a")
    @Override
    public Object visitTransition(Transition theTransition) {
        StateVertex owner = theTransition.getSource();
        
        if (owner != null) {
            // Add all transitions in the owning state
            for (Element e : owner.getOutGoing()) {
                this.results.add(e.getName());
            }
        
            for (MObject e : owner.getCompositionChildren()) {
                if (e instanceof StateVertex) {
                    for (Element e2 : ((StateVertex) e).getOutGoing()) {
                        this.results.add(e2.getName());
                    }
                }
        
            }
        }
        return null;
    }

    @objid ("0077ac36-030f-1035-9f91-001ec947cd2a")
    @Override
    public Object visitAbstractPseudoState(AbstractPseudoState theUML2PseudoState) {
        Region uml2PseudoStateOwner = theUML2PseudoState.getParent();
        
        if (uml2PseudoStateOwner != null) {
            for (Element e : uml2PseudoStateOwner.getSub()) {
                this.results.add(e.getName());
            }
        }
        return null;
    }

    @objid ("00783dae-030f-1035-9f91-001ec947cd2a")
    @Override
    public Object visitBpmnItemDefinition(final BpmnItemDefinition theItemDefinition) {
        final BpmnSharedDefinitions owner = theItemDefinition.getOwner();
        if (owner != null) {
            for (Element e : owner.getRootElement(BpmnItemDefinition.class)) {
                this.results.add(e.getName());
            }
        
        }
        return null;
    }

    @objid ("8dd4c191-257e-4401-bf9d-5a0817fa1732")
    @Override
    public Object visitBpmnParticipant(final BpmnParticipant theBpmnParticipant) {
        BpmnCollaboration owner = theBpmnParticipant.getContainer();
        if (owner != null) {
            for (Element e : owner.getParticipants()) {
                this.results.add(e.getName());
            }
        }
        return null;
    }

    @objid ("ae7e2da9-b8f0-470c-8fad-d669be515072")
    @Override
    public Object visitBpmnMessage(final BpmnMessage theBpmnMessage) {
        BpmnCollaboration owner = theBpmnMessage.getCollaboration();
        if (owner != null) {
            for (Element e : owner.getMessages()) {
                this.results.add(e.getName());
            }
        }
        return super.visitBpmnMessage(theBpmnMessage);
    }

    @objid ("c9e982cd-1dcf-4560-bc90-465cb0e161bc")
    private GetSiblingsVisitor(GetSiblingsInfraVisitor infraVisitor) {
        super(infraVisitor);
        infraVisitor.gsv = this;
        this.results = null;
    }

    @objid ("a899ebec-f107-4ba3-bfb8-4c94baa1ab78")
    @Override
    public Object visitPackage(Package thePackage) {
        Project project = thePackage.getRepresented();
        if (project != null) {
            for (Element e : project.getModel()) {
                this.results.add(e.getName());
            }
        }
        super.visitPackage(thePackage);
        return null;
    }

    @objid ("7e4b0ef5-6d72-4027-b225-5f5428b20294")
    private static class GetSiblingsInfraVisitor extends DefaultInfrastructureVisitor {
        @objid ("469a2bba-5245-4275-8fce-c3354d77ac67")
        private GetSiblingsVisitor gsv;

        @objid ("00694952-030f-1035-9f91-001ec947cd2a")
        @Override
        public Object visitAbstractDiagram(AbstractDiagram theAbstractDiagram) {
            ModelElement owner = theAbstractDiagram.getOrigin();
            if (owner != null) {
                for (Element e : owner.getProduct()) {
                    this.gsv.results.add(e.getName());
                }
            }
            return null;
        }

    }

}
