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

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.activities.BpmnCallActivity;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.activities.BpmnTask;
import org.modelio.metamodel.bpmn.bpmnService.BpmnEndPoint;
import org.modelio.metamodel.bpmn.bpmnService.BpmnInterface;
import org.modelio.metamodel.bpmn.events.BpmnBoundaryEvent;
import org.modelio.metamodel.bpmn.events.BpmnIntermediateCatchEvent;
import org.modelio.metamodel.bpmn.events.BpmnStartEvent;
import org.modelio.metamodel.bpmn.events.BpmnThrowEvent;
import org.modelio.metamodel.bpmn.flows.BpmnMessage;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.metamodel.bpmn.gateways.BpmnGateway;
import org.modelio.metamodel.bpmn.objects.BpmnItemAwareElement;
import org.modelio.metamodel.bpmn.objects.BpmnItemDefinition;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnCollaboration;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.bpmn.resources.BpmnResource;
import org.modelio.metamodel.bpmn.rootElements.BpmnArtifact;
import org.modelio.metamodel.bpmn.rootElements.BpmnSharedDefinitions;
import org.modelio.metamodel.uml.behavior.activityModel.AcceptCallEventAction;
import org.modelio.metamodel.uml.behavior.activityModel.AcceptChangeEventAction;
import org.modelio.metamodel.uml.behavior.activityModel.AcceptSignalAction;
import org.modelio.metamodel.uml.behavior.activityModel.AcceptTimeEventAction;
import org.modelio.metamodel.uml.behavior.activityModel.Activity;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityFinalNode;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityParameterNode;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityPartition;
import org.modelio.metamodel.uml.behavior.activityModel.CallBehaviorAction;
import org.modelio.metamodel.uml.behavior.activityModel.CallOperationAction;
import org.modelio.metamodel.uml.behavior.activityModel.CentralBufferNode;
import org.modelio.metamodel.uml.behavior.activityModel.ConditionalNode;
import org.modelio.metamodel.uml.behavior.activityModel.ControlFlow;
import org.modelio.metamodel.uml.behavior.activityModel.DataStoreNode;
import org.modelio.metamodel.uml.behavior.activityModel.DecisionMergeNode;
import org.modelio.metamodel.uml.behavior.activityModel.ExpansionNode;
import org.modelio.metamodel.uml.behavior.activityModel.ExpansionRegion;
import org.modelio.metamodel.uml.behavior.activityModel.FlowFinalNode;
import org.modelio.metamodel.uml.behavior.activityModel.ForkJoinNode;
import org.modelio.metamodel.uml.behavior.activityModel.InitialNode;
import org.modelio.metamodel.uml.behavior.activityModel.InputPin;
import org.modelio.metamodel.uml.behavior.activityModel.InstanceNode;
import org.modelio.metamodel.uml.behavior.activityModel.InterruptibleActivityRegion;
import org.modelio.metamodel.uml.behavior.activityModel.LoopNode;
import org.modelio.metamodel.uml.behavior.activityModel.MessageFlow;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectFlow;
import org.modelio.metamodel.uml.behavior.activityModel.OpaqueAction;
import org.modelio.metamodel.uml.behavior.activityModel.OutputPin;
import org.modelio.metamodel.uml.behavior.activityModel.SendSignalAction;
import org.modelio.metamodel.uml.behavior.activityModel.StructuredActivityNode;
import org.modelio.metamodel.uml.behavior.commonBehaviors.OpaqueBehavior;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationInteraction;
import org.modelio.metamodel.uml.behavior.interactionModel.Interaction;
import org.modelio.metamodel.uml.behavior.stateMachineModel.InternalTransition;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Transition;
import org.modelio.metamodel.uml.behavior.usecaseModel.Actor;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.informationFlow.InformationItem;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.Usage;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.ConnectorEnd;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.LinkEnd;
import org.modelio.metamodel.uml.statik.Node;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("bb773e2b-cd08-4570-83d5-2891ee8c54e6")
public class UmlMetaclassSorter implements Comparator<MObject> {
    /**
     * Defines the standard metaclass order, from top to bottom.
     */
    @objid ("b8bbad6f-5bf7-4808-84ec-47295516d0a9")
    private static final List<java.lang.Class<? extends MObject>> ORDERING = Arrays.asList(TemplateParameter.class, // 0
                Package.class, // 1
                Interface.class, // 2
                Class.class, // 3
                Actor.class, // 4
                UseCase.class, // 5
                Signal.class, // 6
                Node.class, // 7
                Component.class, // 8
                Artifact.class, // 9
                Collaboration.class, // 10
                DataType.class, // 11
                Enumeration.class, // 12
                InformationItem.class, // 13
                ActivityPartition.class, // 14
                InterruptibleActivityRegion.class, // 15
                LoopNode.class, // 16
                ConditionalNode.class, // 17
                StructuredActivityNode.class, // 18
                OpaqueAction.class, // 19
                AcceptSignalAction.class, // 20
                SendSignalAction.class, // 21
                AcceptCallEventAction.class, // 22
                AcceptTimeEventAction.class, // 23
                AcceptChangeEventAction.class, // 24
                CallOperationAction.class, // 25
                CallBehaviorAction.class, // 26
                ForkJoinNode.class, // 27
                DecisionMergeNode.class, // 28
                InitialNode.class, // 29
                FlowFinalNode.class, // 30
                ActivityFinalNode.class, // 31
                InstanceNode.class, // 32
                CentralBufferNode.class, // 33
                DataStoreNode.class, // 34
                ActivityParameterNode.class, // 35
                InputPin.class, // 36
                OutputPin.class, // 37
                ExpansionNode.class, // 38
                ExpansionRegion.class, // 39
                ObjectFlow.class, // 40
                MessageFlow.class, // 41
                ControlFlow.class, // 42
                BpmnProcess.class, // 43
                BpmnCollaboration.class, // 44
                BpmnMessage.class, // 45
                BpmnItemDefinition.class, // 46
                BpmnInterface.class, // 47
                BpmnEndPoint.class, // 48
                BpmnResource.class, // 49
                BpmnSubProcess.class, // 50
                BpmnCallActivity.class, // 51
                BpmnStartEvent.class, // 52
                BpmnTask.class, // 53
                BpmnGateway.class, // 54
                BpmnIntermediateCatchEvent.class, // 55
                BpmnBoundaryEvent.class, // 56
                BpmnThrowEvent.class, // 57
                BpmnItemAwareElement.class, // 58
                BpmnSequenceFlow.class, // 59
                BpmnArtifact.class, // 60
                Instance.class, // 61
                Port.class, // 62
                BindableInstance.class, // 63
                ConnectorEnd.class, // 64
                LinkEnd.class, // 65
                Usage.class, // 66
                Dependency.class, // 67
                Activity.class, // 68
                OpaqueBehavior.class, // 69
                Interaction.class, // 70
                CommunicationInteraction.class, // 71
                StateMachine.class, // 72
                BpmnSharedDefinitions.class, // 73
                Transition.class, // 74
                InternalTransition.class // 75
        );

    @objid ("a3d7604b-cdd5-4079-9aaf-a9a847adca87")
    @Override
    public int compare(MObject mo1, MObject mo2) {
        return Integer.compare(-UmlMetaclassSorter.ORDERING.indexOf(mo1.getMClass().getJavaInterface()),
                        -UmlMetaclassSorter.ORDERING.indexOf(mo2.getMClass().getJavaInterface()));
    }
static {
            // We do not want unlisted metaclasses to go first because of indexOf == -1, so we have to reverse the stored order
            Collections.reverse(UmlMetaclassSorter.ORDERING);
        }
    
}
