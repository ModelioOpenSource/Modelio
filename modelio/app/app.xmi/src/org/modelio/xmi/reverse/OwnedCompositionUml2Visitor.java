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

package org.modelio.xmi.reverse;

import java.util.HashMap;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.*;
import org.eclipse.uml2.uml.Abstraction;
import org.eclipse.uml2.uml.AcceptCallAction;
import org.eclipse.uml2.uml.AcceptEventAction;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.ActionExecutionSpecification;
import org.eclipse.uml2.uml.ActionInputPin;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ActivityFinalNode;
import org.eclipse.uml2.uml.ActivityGroup;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ActivityParameterNode;
import org.eclipse.uml2.uml.ActivityPartition;
import org.eclipse.uml2.uml.Actor;
import org.eclipse.uml2.uml.AddStructuralFeatureValueAction;
import org.eclipse.uml2.uml.AddVariableValueAction;
import org.eclipse.uml2.uml.AnyReceiveEvent;
import org.eclipse.uml2.uml.Artifact;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.AssociationClass;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.BehaviorExecutionSpecification;
import org.eclipse.uml2.uml.BehavioralFeature;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.BroadcastSignalAction;
import org.eclipse.uml2.uml.CallAction;
import org.eclipse.uml2.uml.CallBehaviorAction;
import org.eclipse.uml2.uml.CallEvent;
import org.eclipse.uml2.uml.CallOperationAction;
import org.eclipse.uml2.uml.CentralBufferNode;
import org.eclipse.uml2.uml.ChangeEvent;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.ClassifierTemplateParameter;
import org.eclipse.uml2.uml.Clause;
import org.eclipse.uml2.uml.ClearAssociationAction;
import org.eclipse.uml2.uml.ClearStructuralFeatureAction;
import org.eclipse.uml2.uml.ClearVariableAction;
import org.eclipse.uml2.uml.Collaboration;
import org.eclipse.uml2.uml.CollaborationUse;
import org.eclipse.uml2.uml.CombinedFragment;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.CommunicationPath;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.ComponentRealization;
import org.eclipse.uml2.uml.ConditionalNode;
import org.eclipse.uml2.uml.ConnectableElement;
import org.eclipse.uml2.uml.ConnectableElementTemplateParameter;
import org.eclipse.uml2.uml.ConnectionPointReference;
import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.ConnectorEnd;
import org.eclipse.uml2.uml.ConsiderIgnoreFragment;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.Continuation;
import org.eclipse.uml2.uml.ControlFlow;
import org.eclipse.uml2.uml.ControlNode;
import org.eclipse.uml2.uml.CreateLinkAction;
import org.eclipse.uml2.uml.CreateLinkObjectAction;
import org.eclipse.uml2.uml.CreateObjectAction;
import org.eclipse.uml2.uml.CreationEvent;
import org.eclipse.uml2.uml.DataStoreNode;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.DecisionNode;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.DeployedArtifact;
import org.eclipse.uml2.uml.Deployment;
import org.eclipse.uml2.uml.DeploymentSpecification;
import org.eclipse.uml2.uml.DeploymentTarget;
import org.eclipse.uml2.uml.DestroyLinkAction;
import org.eclipse.uml2.uml.DestroyObjectAction;
import org.eclipse.uml2.uml.DestructionEvent;
import org.eclipse.uml2.uml.Device;
import org.eclipse.uml2.uml.DirectedRelationship;
import org.eclipse.uml2.uml.Duration;
import org.eclipse.uml2.uml.DurationConstraint;
import org.eclipse.uml2.uml.DurationInterval;
import org.eclipse.uml2.uml.DurationObservation;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.ElementImport;
import org.eclipse.uml2.uml.EncapsulatedClassifier;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.Event;
import org.eclipse.uml2.uml.ExceptionHandler;
import org.eclipse.uml2.uml.ExecutableNode;
import org.eclipse.uml2.uml.ExecutionEnvironment;
import org.eclipse.uml2.uml.ExecutionEvent;
import org.eclipse.uml2.uml.ExecutionOccurrenceSpecification;
import org.eclipse.uml2.uml.ExecutionSpecification;
import org.eclipse.uml2.uml.ExpansionNode;
import org.eclipse.uml2.uml.ExpansionRegion;
import org.eclipse.uml2.uml.Expression;
import org.eclipse.uml2.uml.Extend;
import org.eclipse.uml2.uml.Extension;
import org.eclipse.uml2.uml.ExtensionEnd;
import org.eclipse.uml2.uml.ExtensionPoint;
import org.eclipse.uml2.uml.Feature;
import org.eclipse.uml2.uml.FinalNode;
import org.eclipse.uml2.uml.FinalState;
import org.eclipse.uml2.uml.FlowFinalNode;
import org.eclipse.uml2.uml.ForkNode;
import org.eclipse.uml2.uml.FunctionBehavior;
import org.eclipse.uml2.uml.Gate;
import org.eclipse.uml2.uml.GeneralOrdering;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.GeneralizationSet;
import org.eclipse.uml2.uml.Image;
import org.eclipse.uml2.uml.Include;
import org.eclipse.uml2.uml.InformationFlow;
import org.eclipse.uml2.uml.InformationItem;
import org.eclipse.uml2.uml.InitialNode;
import org.eclipse.uml2.uml.InputPin;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.InstanceValue;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.InteractionConstraint;
import org.eclipse.uml2.uml.InteractionFragment;
import org.eclipse.uml2.uml.InteractionOperand;
import org.eclipse.uml2.uml.InteractionUse;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.InterruptibleActivityRegion;
import org.eclipse.uml2.uml.Interval;
import org.eclipse.uml2.uml.IntervalConstraint;
import org.eclipse.uml2.uml.InvocationAction;
import org.eclipse.uml2.uml.JoinNode;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.LinkAction;
import org.eclipse.uml2.uml.LinkEndCreationData;
import org.eclipse.uml2.uml.LinkEndData;
import org.eclipse.uml2.uml.LinkEndDestructionData;
import org.eclipse.uml2.uml.LiteralBoolean;
import org.eclipse.uml2.uml.LiteralInteger;
import org.eclipse.uml2.uml.LiteralNull;
import org.eclipse.uml2.uml.LiteralSpecification;
import org.eclipse.uml2.uml.LiteralString;
import org.eclipse.uml2.uml.LiteralUnlimitedNatural;
import org.eclipse.uml2.uml.LoopNode;
import org.eclipse.uml2.uml.Manifestation;
import org.eclipse.uml2.uml.MergeNode;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.MessageEnd;
import org.eclipse.uml2.uml.MessageEvent;
import org.eclipse.uml2.uml.MessageOccurrenceSpecification;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.MultiplicityElement;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Node;
import org.eclipse.uml2.uml.ObjectFlow;
import org.eclipse.uml2.uml.ObjectNode;
import org.eclipse.uml2.uml.Observation;
import org.eclipse.uml2.uml.OccurrenceSpecification;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.OperationTemplateParameter;
import org.eclipse.uml2.uml.OutputPin;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageImport;
import org.eclipse.uml2.uml.PackageMerge;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterSet;
import org.eclipse.uml2.uml.ParameterableElement;
import org.eclipse.uml2.uml.PartDecomposition;
import org.eclipse.uml2.uml.Pin;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.ProfileApplication;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.ProtocolConformance;
import org.eclipse.uml2.uml.ProtocolStateMachine;
import org.eclipse.uml2.uml.ProtocolTransition;
import org.eclipse.uml2.uml.Pseudostate;
import org.eclipse.uml2.uml.QualifierValue;
import org.eclipse.uml2.uml.RaiseExceptionAction;
import org.eclipse.uml2.uml.ReadExtentAction;
import org.eclipse.uml2.uml.ReadIsClassifiedObjectAction;
import org.eclipse.uml2.uml.ReadLinkAction;
import org.eclipse.uml2.uml.ReadLinkObjectEndAction;
import org.eclipse.uml2.uml.ReadLinkObjectEndQualifierAction;
import org.eclipse.uml2.uml.ReadSelfAction;
import org.eclipse.uml2.uml.ReadStructuralFeatureAction;
import org.eclipse.uml2.uml.ReadVariableAction;
import org.eclipse.uml2.uml.Realization;
import org.eclipse.uml2.uml.ReceiveOperationEvent;
import org.eclipse.uml2.uml.ReceiveSignalEvent;
import org.eclipse.uml2.uml.Reception;
import org.eclipse.uml2.uml.ReclassifyObjectAction;
import org.eclipse.uml2.uml.RedefinableElement;
import org.eclipse.uml2.uml.RedefinableTemplateSignature;
import org.eclipse.uml2.uml.ReduceAction;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.Relationship;
import org.eclipse.uml2.uml.RemoveStructuralFeatureValueAction;
import org.eclipse.uml2.uml.RemoveVariableValueAction;
import org.eclipse.uml2.uml.ReplyAction;
import org.eclipse.uml2.uml.SendObjectAction;
import org.eclipse.uml2.uml.SendOperationEvent;
import org.eclipse.uml2.uml.SendSignalAction;
import org.eclipse.uml2.uml.SendSignalEvent;
import org.eclipse.uml2.uml.SequenceNode;
import org.eclipse.uml2.uml.Signal;
import org.eclipse.uml2.uml.SignalEvent;
import org.eclipse.uml2.uml.Slot;
import org.eclipse.uml2.uml.StartClassifierBehaviorAction;
import org.eclipse.uml2.uml.StartObjectBehaviorAction;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.StateInvariant;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.StringExpression;
import org.eclipse.uml2.uml.StructuralFeature;
import org.eclipse.uml2.uml.StructuralFeatureAction;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.eclipse.uml2.uml.StructuredClassifier;
import org.eclipse.uml2.uml.Substitution;
import org.eclipse.uml2.uml.TemplateBinding;
import org.eclipse.uml2.uml.TemplateParameter;
import org.eclipse.uml2.uml.TemplateParameterSubstitution;
import org.eclipse.uml2.uml.TemplateSignature;
import org.eclipse.uml2.uml.TemplateableElement;
import org.eclipse.uml2.uml.TestIdentityAction;
import org.eclipse.uml2.uml.TimeConstraint;
import org.eclipse.uml2.uml.TimeEvent;
import org.eclipse.uml2.uml.TimeExpression;
import org.eclipse.uml2.uml.TimeInterval;
import org.eclipse.uml2.uml.TimeObservation;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.Trigger;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.TypedElement;
import org.eclipse.uml2.uml.UnmarshallAction;
import org.eclipse.uml2.uml.Usage;
import org.eclipse.uml2.uml.UseCase;
import org.eclipse.uml2.uml.ValuePin;
import org.eclipse.uml2.uml.ValueSpecification;
import org.eclipse.uml2.uml.ValueSpecificationAction;
import org.eclipse.uml2.uml.Variable;
import org.eclipse.uml2.uml.VariableAction;
import org.eclipse.uml2.uml.Vertex;
import org.eclipse.uml2.uml.WriteLinkAction;
import org.eclipse.uml2.uml.WriteStructuralFeatureAction;
import org.eclipse.uml2.uml.WriteVariableAction;
import org.eclipse.uml2.uml.util.UMLSwitch;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.util.ModelUtils;

@objid ("7ce8b001-ab1e-4c42-bcf6-91c226038bbe")
public class OwnedCompositionUml2Visitor extends UMLSwitch<Object> {
    @objid ("907eb4bf-055c-486b-a82a-e2540461a3f3")
    private XMIImportBehavior behavior;

    @objid ("9a4cea09-0f7d-4ca4-b0b9-589bbb7115e4")
    private Map<Object, Object> visitorMap;

    @objid ("02368aee-ed5a-4398-a1ca-305c479ec841")
    public OwnedCompositionUml2Visitor(XMIImportBehavior behavior, Package ecoreRootModel) {
        this.behavior = behavior;
        this.visitorMap = new HashMap<>();
        
        if (ecoreRootModel instanceof Model)
            this.visitorMap.put(ecoreRootModel, ecoreRootModel);
    }

    @objid ("16405fe1-7b42-40af-a39d-663498d2e4f0")
    @Override
    public Object doSwitch(EObject inputElement) {
        if (inputElement != null)
            return super.doSwitch(inputElement);
        else
            return null;
    }

    @objid ("27e81b36-5445-4c1f-9841-a12b2fc68070")
    @Override
    public Object caseAbstraction(Abstraction inputAbstraction) {
        Object theResult = this.visitorMap.get(inputAbstraction);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("AbstractionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputAbstraction, inputAbstraction);
            this.behavior.visitAbstraction(inputAbstraction);
            this.doSwitch(inputAbstraction.getMapping());
        }
        return null;
    }

    @objid ("e7acdab6-c15b-4b65-a617-e67ac4fcc135")
    @Override
    public Object caseAcceptCallAction(AcceptCallAction inputAcceptCallAction) {
        Object theResult = this.visitorMap.get(inputAcceptCallAction);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("AcceptCallActionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputAcceptCallAction, inputAcceptCallAction);
            this.behavior.visitAcceptCallAction(inputAcceptCallAction);
            this.doSwitch( inputAcceptCallAction
                    .getReturnInformation());
        }
        return null;
    }

    @objid ("205c5d1f-2e84-48dc-b468-1ae14782f133")
    @Override
    public Object caseAcceptEventAction(AcceptEventAction inputAcceptEventAction) {
        Object theResult = this.visitorMap.get(inputAcceptEventAction);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("AcceptEventActionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputAcceptEventAction, inputAcceptEventAction);
            this.behavior.visitAcceptEventAction(inputAcceptEventAction);
            for (OutputPin result : inputAcceptEventAction.getResults()) {
                this.doSwitch(result);
            }
            for (Trigger trigger : inputAcceptEventAction.getTriggers()) {
                this.doSwitch(trigger);
            }
        }
        return null;
    }

    @objid ("eb43b090-fa9c-4998-a1d7-2a1e13624217")
    @Override
    public Object caseAction(Action inputAction) {
        for (OutputPin output : inputAction.getOutputs()) {
            this.doSwitch(output);
        }
        for (InputPin input : inputAction.getInputs()) {
            this.doSwitch(input);
        }
        for (Constraint localPrecondition : inputAction.getLocalPreconditions()) {
            this.doSwitch(localPrecondition);
        }
        for (Constraint localPostcondition : inputAction.getLocalPostconditions()) {
            this.doSwitch(localPostcondition);
        }
        return null;
    }

    @objid ("91e0fc1f-22b5-43df-95a5-168cb9ada6d6")
    @Override
    public Object caseActionExecutionSpecification(ActionExecutionSpecification inputActionExecutionSpecification) {
        Object theResult = this.visitorMap.get(inputActionExecutionSpecification);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ActionExecutionSpecificationImpl".equals(theResult
                        .getClass().getSimpleName()))) {
            this.visitorMap.put(inputActionExecutionSpecification,
                    inputActionExecutionSpecification);
            this.behavior
            .visitActionExecutionSpecification(inputActionExecutionSpecification);
        }
        return null;
    }

    @objid ("2b08096a-2495-4207-b0eb-049ffe00bdc2")
    @Override
    public Object caseActionInputPin(ActionInputPin inputActionInputPin) {
        Object theResult = this.visitorMap.get(inputActionInputPin);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ActionInputPinImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputActionInputPin, inputActionInputPin);
            this.behavior.visitActionInputPin(inputActionInputPin);
            this.doSwitch( inputActionInputPin.getFromAction());
        }
        return null;
    }

    @objid ("6986e125-cac3-4062-9d07-e927b0fe7ac2")
    @Override
    public Object caseActivity(Activity inputActivity) {
        Object theResult = this.visitorMap.get(inputActivity);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ActivityImpl"
                        .equals(theResult.getClass().getSimpleName()))) {
            this.visitorMap.put(inputActivity, inputActivity);
            this.behavior.visitActivity(inputActivity);
            for (StructuredActivityNode structuredNode : inputActivity.getStructuredNodes()) {
                this.doSwitch(structuredNode);
            }
            for (Variable variable : inputActivity.getVariables()) {
                this.doSwitch(variable);
            }
            for (EObject node : inputActivity.getNodes()) {
                this.doSwitch(node);
            }
            for (EObject edge : inputActivity.getEdges()) {
                this.doSwitch(edge);
            }
            for (EObject group : inputActivity.getGroups()) {
                this.doSwitch(group);
            }
        }
        return null;
    }

    @objid ("aa56b88d-5d32-4852-a8a6-f8ae8cf88c61")
    @Override
    public Object caseActivityEdge(ActivityEdge inputActivityEdge) {
        this.doSwitch(inputActivityEdge.getGuard());
        this.doSwitch(inputActivityEdge.getWeight());
        return null;
    }

    @objid ("d6b22be7-9d3b-4616-ae10-4c9f1a7796dc")
    @Override
    public Object caseActivityFinalNode(ActivityFinalNode inputActivityFinalNode) {
        Object theResult = this.visitorMap.get(inputActivityFinalNode);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ActivityFinalNodeImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputActivityFinalNode, inputActivityFinalNode);
            this.behavior.visitActivityFinalNode(inputActivityFinalNode);
        }
        return null;
    }

    @objid ("f7cfb9b6-757f-4212-82fd-9f9501c353b0")
    @Override
    public Object caseActivityGroup(ActivityGroup inputActivityGroup) {
        for (EObject subgroup : inputActivityGroup.getSubgroups()) {
            this.doSwitch(subgroup);
        }
        return null;
    }

    @objid ("05270fba-161b-4a84-a773-568e25ef3d16")
    @Override
    public Object caseActivityNode(ActivityNode inputActivityNode) {
        return null;
    }

    @objid ("9142d730-50c5-45d0-a08c-526e84027a52")
    @Override
    public Object caseActivityParameterNode(ActivityParameterNode inputActivityParameterNode) {
        Object theResult = this.visitorMap.get(inputActivityParameterNode);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ActivityParameterNodeImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputActivityParameterNode,
                    inputActivityParameterNode);
            this.behavior.visitActivityParameterNode(inputActivityParameterNode);
        }
        return null;
    }

    @objid ("8280f93c-7b3a-4c59-a498-51fab501ed2b")
    @Override
    public Object caseActivityPartition(ActivityPartition inputActivityPartition) {
        Object theResult = this.visitorMap.get(inputActivityPartition);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ActivityPartitionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputActivityPartition, inputActivityPartition);
            this.behavior.visitActivityPartition(inputActivityPartition);
            for (EObject subpartition : inputActivityPartition
                    .getSubpartitions()) {
                this.doSwitch(subpartition);
            }
        }
        return null;
    }

    @objid ("c2d9e6e5-6f0e-4e9c-abda-d925023415e1")
    @Override
    public Object caseActor(Actor inputActor) {
        Object theResult = this.visitorMap.get(inputActor);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ActorImpl".equals(theResult.getClass().getSimpleName()))) {
            this.visitorMap.put(inputActor, inputActor);
            this.behavior.visitActor(inputActor);
        }
        return null;
    }

    @objid ("2dc7d226-70f8-4212-b79b-0ae913751918")
    @Override
    public Object caseAddStructuralFeatureValueAction(AddStructuralFeatureValueAction inputAddStructuralFeatureValueAction) {
        Object theResult = this.visitorMap.get(inputAddStructuralFeatureValueAction);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("AddStructuralFeatureValueActionImpl".equals(theResult
                        .getClass().getSimpleName()))) {
            this.visitorMap.put(inputAddStructuralFeatureValueAction,
                    inputAddStructuralFeatureValueAction);
            this.behavior
            .visitAddStructuralFeatureValueAction(inputAddStructuralFeatureValueAction);
            this.doSwitch( inputAddStructuralFeatureValueAction
                    .getInsertAt());
        }
        return null;
    }

    @objid ("0f548bb5-d573-4bd6-81af-802e630c62ca")
    @Override
    public Object caseAddVariableValueAction(AddVariableValueAction inputAddVariableValueAction) {
        Object theResult = this.visitorMap.get(inputAddVariableValueAction);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("AddVariableValueActionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputAddVariableValueAction,
                    inputAddVariableValueAction);
            this.behavior.visitAddVariableValueAction(inputAddVariableValueAction);
            this.doSwitch( inputAddVariableValueAction.getInsertAt());
        }
        return null;
    }

    @objid ("d6d5d0ca-f886-406e-a98c-7b90d1ca53f1")
    @Override
    public Object caseAnyReceiveEvent(AnyReceiveEvent inputAnyReceiveEvent) {
        Object theResult = this.visitorMap.get(inputAnyReceiveEvent);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("AnyReceiveEventImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputAnyReceiveEvent, inputAnyReceiveEvent);
            this.behavior.visitAnyReceiveEvent(inputAnyReceiveEvent);
        }
        return null;
    }

    @objid ("91784f20-ae3d-4cdc-bca0-bc07b930f5d8")
    @Override
    public Object caseArtifact(Artifact inputArtifact) {
        Object theResult = this.visitorMap.get(inputArtifact);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ArtifactImpl"
                        .equals(theResult.getClass().getSimpleName()))) {
            this.visitorMap.put(inputArtifact, inputArtifact);
            this.behavior.visitArtifact(inputArtifact);
            for (EObject nestedArtifact : inputArtifact.getNestedArtifacts()) {
                this.doSwitch(nestedArtifact);
            }
            for (EObject manifestation : inputArtifact.getManifestations()) {
                this.doSwitch(manifestation);
            }
            for (EObject ownedOperation : inputArtifact.getOwnedOperations()) {
                this.doSwitch(ownedOperation);
            }
            for (EObject ownedAttribute : inputArtifact.getOwnedAttributes()) {
                this.doSwitch(ownedAttribute);
            }
        }
        return null;
    }

    @objid ("43ff2733-8687-4118-836b-b6040e49a523")
    @Override
    public Object caseAssociation(Association inputAssociation) {
        Object theResult = this.visitorMap.get(inputAssociation);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        
        if (theResult == null
                || !("AssociationImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
        
            this.visitorMap.put(inputAssociation, inputAssociation);
            this.behavior.visitAssociation(inputAssociation);
            for (EObject ownedEnd : inputAssociation.getMemberEnds()) {
                this.doSwitch(ownedEnd);
            }
        
        }
        return null;
    }

    @objid ("d3826c71-5647-426c-b4f2-abdc0c67e2cd")
    @Override
    public Object caseAssociationClass(AssociationClass inputAssociationClass) {
        Object theResult = this.visitorMap.get(inputAssociationClass);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("AssociationClassImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputAssociationClass, inputAssociationClass);
            this.behavior.visitAssociationClass(inputAssociationClass);
            for (EObject ownedEnd : inputAssociationClass.getMemberEnds()) {
                this.doSwitch(ownedEnd);
            }
        }
        return null;
    }

    @objid ("4428550f-f29e-42db-ae41-650692bde390")
    @Override
    public Object caseBehavior(Behavior inputBehavior) {
        for (EObject ownedParameter : inputBehavior.getOwnedParameters()) {
            this.doSwitch(ownedParameter);
        }
        for (EObject precondition : inputBehavior.getPreconditions()) {
            this.doSwitch(precondition);
        }
        for (EObject postcondition : inputBehavior.getPostconditions()) {
            this.doSwitch(postcondition);
        }
        for (EObject ownedParameterSet : inputBehavior.getOwnedParameterSets()) {
            this.doSwitch(ownedParameterSet);
        }
        return null;
    }

    @objid ("d2434408-6c08-4ee1-99a8-9c1fd8f67b43")
    @Override
    public Object caseBehavioralFeature(BehavioralFeature inputBehavioralFeature) {
        for (EObject ownedParameter : inputBehavioralFeature
                .getOwnedParameters()) {
            this.doSwitch(ownedParameter);
        }
        for (EObject ownedParameterSet : inputBehavioralFeature
                .getOwnedParameterSets()) {
            this.doSwitch(ownedParameterSet);
        }
        return null;
    }

    @objid ("4b5fdf25-eb15-4d6d-ad6c-46ffabbd8e57")
    @Override
    public Object caseBehavioredClassifier(BehavioredClassifier inputBehavioredClassifier) {
        for (EObject ownedBehavior : inputBehavioredClassifier
                .getOwnedBehaviors()) {
            this.doSwitch(ownedBehavior);
        }
        for (EObject interfaceRealization : inputBehavioredClassifier
                .getInterfaceRealizations()) {
            this.doSwitch(interfaceRealization);
        }
        for (EObject ownedTrigger : inputBehavioredClassifier.getOwnedTriggers()) {
            this.doSwitch(ownedTrigger);
        }
        return null;
    }

    @objid ("524ccfad-3d27-4077-8e8b-6bc12f0f4615")
    @Override
    public Object caseBehaviorExecutionSpecification(BehaviorExecutionSpecification inputBehaviorExecutionSpecification) {
        Object theResult = this.visitorMap.get(inputBehaviorExecutionSpecification);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("BehaviorExecutionSpecificationImpl".equals(theResult
                        .getClass().getSimpleName()))) {
            this.visitorMap.put(inputBehaviorExecutionSpecification,
                    inputBehaviorExecutionSpecification);
            this.behavior
            .visitBehaviorExecutionSpecification(inputBehaviorExecutionSpecification);
        }
        return null;
    }

    @objid ("0070859e-5c2d-486f-9a74-d2b92a6e9a3a")
    @Override
    public Object caseBroadcastSignalAction(BroadcastSignalAction inputBroadcastSignalAction) {
        Object theResult = this.visitorMap.get(inputBroadcastSignalAction);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("BroadcastSignalActionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputBroadcastSignalAction,
                    inputBroadcastSignalAction);
            this.behavior.visitBroadcastSignalAction(inputBroadcastSignalAction);
        }
        return null;
    }

    @objid ("1b3d3fd0-eb48-4ae6-8fc8-b3bdef686e2d")
    @Override
    public Object caseCallAction(CallAction inputCallAction) {
        for (EObject result : inputCallAction.getResults()) {
            this.doSwitch(result);
        }
        return null;
    }

    @objid ("1fb16e81-4c01-41b2-b718-380de6d5719d")
    @Override
    public Object caseCallBehaviorAction(CallBehaviorAction inputCallBehaviorAction) {
        Object theResult = this.visitorMap.get(inputCallBehaviorAction);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("CallBehaviorActionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputCallBehaviorAction, inputCallBehaviorAction);
            this.behavior.visitCallBehaviorAction(inputCallBehaviorAction);
        }
        return null;
    }

    @objid ("cdf287dd-656d-42b8-b4dc-14410b107ddd")
    @Override
    public Object caseCallEvent(CallEvent inputCallEvent) {
        Object theResult = this.visitorMap.get(inputCallEvent);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("CallEventImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputCallEvent, inputCallEvent);
            this.behavior.visitCallEvent(inputCallEvent);
        }
        return null;
    }

    @objid ("e553c33c-1966-4b87-8809-f2f3593fbdad")
    @Override
    public Object caseCallOperationAction(CallOperationAction inputCallOperationAction) {
        Object theResult = this.visitorMap.get(inputCallOperationAction);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("CallOperationActionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputCallOperationAction, inputCallOperationAction);
            this.behavior.visitCallOperationAction(inputCallOperationAction);
            this.doSwitch(inputCallOperationAction.getTarget());
        }
        return null;
    }

    @objid ("f7196433-4fe5-4d2c-8afc-e4a7c1814179")
    @Override
    public Object caseCentralBufferNode(CentralBufferNode inputCentralBufferNode) {
        Object theResult = this.visitorMap.get(inputCentralBufferNode);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("CentralBufferNodeImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputCentralBufferNode, inputCentralBufferNode);
            this.behavior.visitCentralBufferNode(inputCentralBufferNode);
        }
        return null;
    }

    @objid ("3058e201-ef46-4fbc-818d-b07633355681")
    @Override
    public Object caseChangeEvent(ChangeEvent inputChangeEvent) {
        Object theResult = this.visitorMap.get(inputChangeEvent);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ChangeEventImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputChangeEvent, inputChangeEvent);
            this.behavior.visitChangeEvent(inputChangeEvent);
            this.doSwitch(inputChangeEvent.getChangeExpression());
        }
        return null;
    }

    @objid ("40e6b8ac-393c-468a-894c-b01fd6e0dc3d")
    @Override
    public Object caseClass(Class inputClass) {
        Object theResult = this.visitorMap.get(inputClass);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ClassImpl".equals(theResult.getClass().getSimpleName()))) {
            this.visitorMap.put(inputClass, inputClass);
            this.behavior.visitClass(inputClass);
            for (EObject nestedClassifier : inputClass.getNestedClassifiers()) {
                this.doSwitch(nestedClassifier);
            }
            for (EObject ownedAttribute : inputClass.getOwnedAttributes()) {
                this.doSwitch(ownedAttribute);
            }
            for (EObject ownedOperation : inputClass.getOwnedOperations()) {
                this.doSwitch(ownedOperation);
            }
            for (EObject ownedReception : inputClass.getOwnedReceptions()) {
                this.doSwitch(ownedReception);
            }
        }
        return null;
    }

    @objid ("7959fa87-87e8-4258-bd73-d9ebf296cb07")
    @Override
    public Object caseClassifier(Classifier inputClassifier) {
        for (EObject generalization : inputClassifier.getGeneralizations()) {
            this.doSwitch(generalization);
        }
        for (EObject substitution : inputClassifier.getSubstitutions()) {
            this.doSwitch(substitution);
        }
        for (EObject collaborationUse : inputClassifier.getCollaborationUses()) {
            this.doSwitch(collaborationUse);
        }
        for (EObject ownedUseCase : inputClassifier.getOwnedUseCases()) {
            this.doSwitch(ownedUseCase);
        }
        this.doSwitch(inputClassifier.getOwnedTemplateSignature());
        return null;
    }

    @objid ("6a853c40-e71a-418e-aa38-99c75ebe4438")
    @Override
    public Object caseClassifierTemplateParameter(ClassifierTemplateParameter inputClassifierTemplateParameter) {
        Object theResult = this.visitorMap.get(inputClassifierTemplateParameter);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ClassifierTemplateParameterImpl".equals(theResult
                        .getClass().getSimpleName()))) {
            this.visitorMap.put(inputClassifierTemplateParameter,
                    inputClassifierTemplateParameter);
            this.behavior
            .visitClassifierTemplateParameter(inputClassifierTemplateParameter);
        }
        return null;
    }

    @objid ("779121c9-ec07-4cf3-9ab3-94649352627a")
    @Override
    public Object caseClause(Clause inputClause) {
        Object theResult = this.visitorMap.get(inputClause);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ClauseImpl".equals(theResult.getClass().getSimpleName()))) {
            this.visitorMap.put(inputClause, inputClause);
            this.behavior.visitClause(inputClause);
        }
        return null;
    }

    @objid ("75fe39b9-a022-408e-ab67-924ca4942193")
    @Override
    public Object caseClearAssociationAction(ClearAssociationAction inputClearAssociationAction) {
        Object theResult = this.visitorMap.get(inputClearAssociationAction);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ClearAssociationActionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputClearAssociationAction,
                    inputClearAssociationAction);
            this.behavior.visitClearAssociationAction(inputClearAssociationAction);
            this.doSwitch( inputClearAssociationAction.getObject());
        }
        return null;
    }

    @objid ("a0cb1a7b-1bdb-49e2-ab7f-6f0f005834ea")
    @Override
    public Object caseClearStructuralFeatureAction(ClearStructuralFeatureAction inputClearStructuralFeatureAction) {
        Object theResult = this.visitorMap.get(inputClearStructuralFeatureAction);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ClearStructuralFeatureActionImpl".equals(theResult
                        .getClass().getSimpleName()))) {
            this.visitorMap.put(inputClearStructuralFeatureAction,
                    inputClearStructuralFeatureAction);
            this.behavior
            .visitClearStructuralFeatureAction(inputClearStructuralFeatureAction);
        }
        return null;
    }

    @objid ("62229e75-ccf5-408a-bba1-25150f617585")
    @Override
    public Object caseClearVariableAction(ClearVariableAction inputClearVariableAction) {
        Object theResult = this.visitorMap.get(inputClearVariableAction);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ClearVariableActionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputClearVariableAction, inputClearVariableAction);
            this.behavior.visitClearVariableAction(inputClearVariableAction);
        }
        return null;
    }

    @objid ("a13523bb-aa97-4ad7-827f-b0cf90752358")
    @Override
    public Object caseCollaboration(Collaboration inputCollaboration) {
        Object theResult = this.visitorMap.get(inputCollaboration);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("CollaborationImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputCollaboration, inputCollaboration);
            this.behavior.visitCollaboration(inputCollaboration);
        }
        return null;
    }

    @objid ("058bb3f3-4339-4a1e-ab00-5dac3d83a8f3")
    @Override
    public Object caseCollaborationUse(CollaborationUse inputCollaborationUse) {
        Object theResult = this.visitorMap.get(inputCollaborationUse);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("CollaborationUseImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputCollaborationUse, inputCollaborationUse);
            this.behavior.visitCollaborationUse(inputCollaborationUse);
            for (EObject roleBinding : inputCollaborationUse.getRoleBindings()) {
                this.doSwitch(roleBinding);
            }
        }
        return null;
    }

    @objid ("72d258a2-6506-45d4-87b9-6afb7fdf4fd2")
    @Override
    public Object caseCombinedFragment(CombinedFragment inputCombinedFragment) {
        Object theResult = this.visitorMap.get(inputCombinedFragment);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("CombinedFragmentImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputCombinedFragment, inputCombinedFragment);
            this.behavior.visitCombinedFragment(inputCombinedFragment);
        
            for (org.eclipse.uml2.uml.InteractionOperand operand : inputCombinedFragment.getOperands()) {
                ModelUtils.setLineNumber(operand);
                this.doSwitch(operand);
                ModelUtils.setEndLineNumber(operand);
        
                for (Gate cfragmentGate : inputCombinedFragment.getCfragmentGates()) {
                    this.doSwitch(cfragmentGate);
                    ModelUtils.setLineNumber(cfragmentGate);
                }
            }
        }
        return null;
    }

    @objid ("19227dae-a30a-4604-a410-6e6c95854640")
    @Override
    public Object caseComment(Comment inputComment) {
        Object theResult = this.visitorMap.get(inputComment);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("CommentImpl".equals(theResult.getClass().getSimpleName()))) {
            this.visitorMap.put(inputComment, inputComment);
            this.behavior.visitComment(inputComment);
        }
        return null;
    }

    @objid ("6b22db4b-00fb-4cdf-9a62-926461935d51")
    @Override
    public Object caseCommunicationPath(CommunicationPath inputCommunicationPath) {
        Object theResult = this.visitorMap.get(inputCommunicationPath);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("CommunicationPathImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputCommunicationPath, inputCommunicationPath);
            this.behavior.visitCommunicationPath(inputCommunicationPath);
        }
        return null;
    }

    @objid ("fe50e510-3d48-415c-9010-7c17101e933b")
    @Override
    public Object caseComponent(Component inputComponent) {
        Object theResult = this.visitorMap.get(inputComponent);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ComponentImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputComponent, inputComponent);
            this.behavior.visitComponent(inputComponent);
        
            for (EObject realization : inputComponent.getRealizations()) {
                this.doSwitch(realization);
            }
            for (EObject packagedElement : inputComponent.getPackagedElements()) {
                this.doSwitch(packagedElement);
            }
        }
        return null;
    }

    @objid ("e3f2bdec-72be-4539-b507-e913225f735b")
    @Override
    public Object caseComponentRealization(ComponentRealization inputComponentRealization) {
        Object theResult = this.visitorMap.get(inputComponentRealization);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ComponentRealizationImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap
            .put(inputComponentRealization, inputComponentRealization);
            this.behavior.visitComponentRealization(inputComponentRealization);
        }
        return null;
    }

    @objid ("d047d6f0-48a7-4b9a-a467-3019277ec2e5")
    @Override
    public Object caseConditionalNode(ConditionalNode inputConditionalNode) {
        Object theResult = this.visitorMap.get(inputConditionalNode);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ConditionalNodeImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputConditionalNode, inputConditionalNode);
            this.behavior.visitConditionalNode(inputConditionalNode);
            for (EObject clause : inputConditionalNode.getClauses()) {
                this.doSwitch(clause);
            }
            for (EObject result : inputConditionalNode.getResults()) {
                this.doSwitch(result);
            }
        }
        return null;
    }

    @objid ("abbfc782-57fc-4db4-8ffb-a67299ab5d96")
    @Override
    public Object caseConnectableElement(ConnectableElement inputConnectableElement) {
        return null;
    }

    @objid ("ae2abc1b-326c-463d-97d8-fb274760dc18")
    @Override
    public Object caseConnectableElementTemplateParameter(ConnectableElementTemplateParameter inputConnectableElementTemplateParameter) {
        Object theResult = this.visitorMap
                .get(inputConnectableElementTemplateParameter);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ConnectableElementTemplateParameterImpl".equals(theResult
                        .getClass().getSimpleName()))) {
            this.visitorMap.put(inputConnectableElementTemplateParameter,
                    inputConnectableElementTemplateParameter);
            this.behavior
            .visitConnectableElementTemplateParameter(inputConnectableElementTemplateParameter);
        }
        return null;
    }

    @objid ("9997d82b-2a3d-4028-be96-417d761855ac")
    @Override
    public Object caseConnectionPointReference(ConnectionPointReference inputConnectionPointReference) {
        Object theResult = this.visitorMap.get(inputConnectionPointReference);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ConnectionPointReferenceImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputConnectionPointReference,
                    inputConnectionPointReference);
            this.behavior
            .visitConnectionPointReference(inputConnectionPointReference);
        }
        return null;
    }

    @objid ("1fed9173-5569-4cb5-badf-193140277fb9")
    @Override
    public Object caseConnector(Connector inputConnector) {
        Object theResult = this.visitorMap.get(inputConnector);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ConnectorImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputConnector, inputConnector);
            this.behavior.visitConnector(inputConnector);
            for (EObject end : inputConnector.getEnds()) {
                this.doSwitch(end);
            }
        }
        return null;
    }

    @objid ("ad251001-4483-4f68-8bd9-0935ea70e25b")
    @Override
    public Object caseConnectorEnd(ConnectorEnd inputConnectorEnd) {
        Object theResult = this.visitorMap.get(inputConnectorEnd);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ConnectorEndImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputConnectorEnd, inputConnectorEnd);
            this.behavior.visitConnectorEnd(inputConnectorEnd);
        }
        return null;
    }

    @objid ("70fd3148-0319-42a1-aaf2-5a54e2d7158c")
    @Override
    public Object caseConsiderIgnoreFragment(ConsiderIgnoreFragment inputConsiderIgnoreFragment) {
        Object theResult = this.visitorMap.get(inputConsiderIgnoreFragment);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ConsiderIgnoreFragmentImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputConsiderIgnoreFragment,
                    inputConsiderIgnoreFragment);
            this.behavior.visitConsiderIgnoreFragment(inputConsiderIgnoreFragment);
        }
        return null;
    }

    @objid ("b36e32e2-1c99-44bb-9663-a2c561db0d8a")
    @Override
    public Object caseConstraint(Constraint inputConstraint) {
        Object theResult = this.visitorMap.get(inputConstraint);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ConstraintImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputConstraint, inputConstraint);
            this.behavior.visitConstraint(inputConstraint);
            this.doSwitch( inputConstraint.getSpecification());
        }
        return null;
    }

    @objid ("2231f66b-1c3f-4fb0-89ba-aea27db187df")
    @Override
    public Object caseContinuation(Continuation inputContinuation) {
        Object theResult = this.visitorMap.get(inputContinuation);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ContinuationImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputContinuation, inputContinuation);
            this.behavior.visitContinuation(inputContinuation);
        }
        return null;
    }

    @objid ("63caf0c3-9be6-47bc-9e0d-9b5d3ca39e1b")
    @Override
    public Object caseControlFlow(ControlFlow inputControlFlow) {
        Object theResult = this.visitorMap.get(inputControlFlow);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ControlFlowImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputControlFlow, inputControlFlow);
            this.behavior.visitControlFlow(inputControlFlow);
        }
        return null;
    }

    @objid ("0ed3d274-562e-48e5-9783-b6909788f514")
    @Override
    public Object caseControlNode(ControlNode inputControlNode) {
        return null;
    }

    @objid ("858a0128-a7e0-4477-a8ef-aad479f4b013")
    @Override
    public Object caseCreateLinkAction(CreateLinkAction inputCreateLinkAction) {
        Object theResult = this.visitorMap.get(inputCreateLinkAction);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("CreateLinkActionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputCreateLinkAction, inputCreateLinkAction);
            this.behavior.visitCreateLinkAction(inputCreateLinkAction);
            for (EObject endData : inputCreateLinkAction.getEndData()) {
                this.doSwitch(endData);
            }
        }
        return null;
    }

    @objid ("e2a64c90-c658-42ce-9568-87bb2364eca1")
    @Override
    public Object caseCreateLinkObjectAction(CreateLinkObjectAction inputCreateLinkObjectAction) {
        Object theResult = this.visitorMap.get(inputCreateLinkObjectAction);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("CreateLinkObjectActionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputCreateLinkObjectAction,
                    inputCreateLinkObjectAction);
            this.behavior.visitCreateLinkObjectAction(inputCreateLinkObjectAction);
            this.doSwitch(inputCreateLinkObjectAction.getResult());
        }
        return null;
    }

    @objid ("b3ccb6a8-1b92-47cc-b50a-0b99fdd2dd23")
    @Override
    public Object caseCreateObjectAction(CreateObjectAction inputCreateObjectAction) {
        Object theResult = this.visitorMap.get(inputCreateObjectAction);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("CreateObjectActionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputCreateObjectAction, inputCreateObjectAction);
            this.behavior.visitCreateObjectAction(inputCreateObjectAction);
            this.doSwitch(inputCreateObjectAction.getResult());
        }
        return null;
    }

    @objid ("083dfc75-7b36-4157-81ab-aabce39726ca")
    @Override
    public Object caseCreationEvent(CreationEvent inputCreationEvent) {
        Object theResult = this.visitorMap.get(inputCreationEvent);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("CreationEventImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputCreationEvent, inputCreationEvent);
            this.behavior.visitCreationEvent(inputCreationEvent);
        }
        return null;
    }

    @objid ("e0c234de-e53b-4c4f-94d6-6b3903aec705")
    @Override
    public Object caseDataStoreNode(DataStoreNode inputDataStoreNode) {
        Object theResult = this.visitorMap.get(inputDataStoreNode);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("DataStoreNodeImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputDataStoreNode, inputDataStoreNode);
            this.behavior.visitDataStoreNode(inputDataStoreNode);
        }
        return null;
    }

    @objid ("74d07a62-3957-40e6-8289-b98b9cc6a977")
    @Override
    public Object caseDataType(DataType inputDataType) {
        Object theResult = this.visitorMap.get(inputDataType);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("DataTypeImpl"
                        .equals(theResult.getClass().getSimpleName()))) {
            this.visitorMap.put(inputDataType, inputDataType);
            this.behavior.visitDataType(inputDataType);
            for (EObject ownedAttribute : inputDataType.getOwnedAttributes()) {
                this.doSwitch(ownedAttribute);
            }
            for (EObject ownedOperation : inputDataType.getOwnedOperations()) {
                this.doSwitch(ownedOperation);
            }
        }
        return null;
    }

    @objid ("abf9f675-9d11-48e2-b1bb-078b16f793d7")
    @Override
    public Object caseDecisionNode(DecisionNode inputDecisionNode) {
        Object theResult = this.visitorMap.get(inputDecisionNode);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("DecisionNodeImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputDecisionNode, inputDecisionNode);
            this.behavior.visitDecisionNode(inputDecisionNode);
        }
        return null;
    }

    @objid ("85b52a8a-4461-4abf-a34e-17272d0bb045")
    @Override
    public Object caseDependency(Dependency inputDependency) {
        Object theResult = this.visitorMap.get(inputDependency);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("DependencyImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputDependency, inputDependency);
            this.behavior.visitDependency(inputDependency);
        }
        return null;
    }

    @objid ("64f5c7b1-d148-404b-85df-5d4b6669301e")
    @Override
    public Object caseDeployedArtifact(DeployedArtifact inputDeployedArtifact) {
        return null;
    }

    @objid ("932a47a4-730d-476e-9967-a2e26889c265")
    @Override
    public Object caseDeployment(Deployment inputDeployment) {
        Object theResult = this.visitorMap.get(inputDeployment);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("DeploymentImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputDeployment, inputDeployment);
            this.behavior.visitDeployment(inputDeployment);
            for (EObject configuration : inputDeployment.getConfigurations()) {
                this.doSwitch(configuration);
            }
        }
        return null;
    }

    @objid ("76cbb93a-215a-430d-92d4-6a14607f7300")
    @Override
    public Object caseDeploymentSpecification(DeploymentSpecification inputDeploymentSpecification) {
        Object theResult = this.visitorMap.get(inputDeploymentSpecification);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("DeploymentSpecificationImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputDeploymentSpecification,
                    inputDeploymentSpecification);
            this.behavior.visitDeploymentSpecification(inputDeploymentSpecification);
        }
        return null;
    }

    @objid ("276bdf89-bd49-48d0-bf1e-07d570046e7d")
    @Override
    public Object caseDeploymentTarget(DeploymentTarget inputDeploymentTarget) {
        for (EObject deployment : inputDeploymentTarget.getDeployments()) {
            this.doSwitch(deployment);
        }
        return null;
    }

    @objid ("a5c24fa4-cc1b-472a-bfa4-c20fc7ccf6dd")
    @Override
    public Object caseDestroyLinkAction(DestroyLinkAction inputDestroyLinkAction) {
        Object theResult = this.visitorMap.get(inputDestroyLinkAction);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("DestroyLinkActionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputDestroyLinkAction, inputDestroyLinkAction);
            this.behavior.visitDestroyLinkAction(inputDestroyLinkAction);
            for (EObject endData : inputDestroyLinkAction.getEndData()) {
                this.doSwitch(endData);
            }
        }
        return null;
    }

    @objid ("c852d9e5-b327-4c6a-929f-3f4d22f311ca")
    @Override
    public Object caseDestroyObjectAction(DestroyObjectAction inputDestroyObjectAction) {
        Object theResult = this.visitorMap.get(inputDestroyObjectAction);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("DestroyObjectActionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputDestroyObjectAction, inputDestroyObjectAction);
            this.behavior.visitDestroyObjectAction(inputDestroyObjectAction);
            this.doSwitch(inputDestroyObjectAction.getTarget());
        }
        return null;
    }

    @objid ("9a72b7f7-15cd-4eee-b02d-1e00406fc6f7")
    @Override
    public Object caseDestructionEvent(DestructionEvent inputDestructionEvent) {
        Object theResult = this.visitorMap.get(inputDestructionEvent);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("DestructionEventImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputDestructionEvent, inputDestructionEvent);
            this.behavior.visitDestructionEvent(inputDestructionEvent);
        }
        return null;
    }

    @objid ("2bc7ac5f-6e59-4cda-9c98-2d830f581a88")
    @Override
    public Object caseDevice(Device inputDevice) {
        Object theResult = this.visitorMap.get(inputDevice);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("DeviceImpl".equals(theResult.getClass().getSimpleName()))) {
            this.visitorMap.put(inputDevice, inputDevice);
            this.behavior.visitDevice(inputDevice);
        }
        return null;
    }

    @objid ("8aa18ba5-9543-4f5b-a9cb-a89e1cc607e4")
    @Override
    public Object caseDirectedRelationship(DirectedRelationship inputDirectedRelationship) {
        return null;
    }

    @objid ("03959060-77cb-4410-91c5-c7af799bd774")
    @Override
    public Object caseDuration(Duration inputDuration) {
        Object theResult = this.visitorMap.get(inputDuration);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("DurationImpl"
                        .equals(theResult.getClass().getSimpleName()))) {
            this.visitorMap.put(inputDuration, inputDuration);
            this.behavior.visitDuration(inputDuration);
        }
        return null;
    }

    @objid ("bbb265b0-8379-4bd7-99d0-e5a1de8b06c4")
    @Override
    public Object caseDurationConstraint(DurationConstraint inputDurationConstraint) {
        Object theResult = this.visitorMap.get(inputDurationConstraint);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("DurationConstraintImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputDurationConstraint, inputDurationConstraint);
            this.behavior.visitDurationConstraint(inputDurationConstraint);
            this.doSwitch(inputDurationConstraint.getSpecification());
        }
        return null;
    }

    @objid ("18317a87-ef4f-424a-895e-e852234447ce")
    @Override
    public Object caseDurationInterval(DurationInterval inputDurationInterval) {
        Object theResult = this.visitorMap.get(inputDurationInterval);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("DurationIntervalImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputDurationInterval, inputDurationInterval);
            this.behavior.visitDurationInterval(inputDurationInterval);
        }
        return null;
    }

    @objid ("d538d2d0-4199-470d-a580-64197b7567ff")
    @Override
    public Object caseDurationObservation(final DurationObservation inputDurationIservation) {
        Object theResult = this.visitorMap.get(inputDurationIservation);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("DurationIservationImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputDurationIservation, inputDurationIservation);
            this.behavior.visitDurationObservation(inputDurationIservation);
        }
        return null;
    }

    @objid ("5e9ee055-4723-4337-9a8f-20d0f79ff296")
    @Override
    public Object caseElement(Element inputElement) {
        for (EObject ownedElement : inputElement.getOwnedElements()) {
            this.doSwitch(ownedElement);
        }
        for (EObject ownedComment : inputElement.getOwnedComments()) {
            this.doSwitch(ownedComment);
        }
        return null;
    }

    @objid ("8bce1e42-297b-4ff6-a2c0-fc3472cef63a")
    @Override
    public Object caseElementImport(ElementImport inputElementImport) {
        Object theResult = this.visitorMap.get(inputElementImport);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ElementImportImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputElementImport, inputElementImport);
            this.behavior.visitElementImport(inputElementImport);
        }
        return null;
    }

    @objid ("be1b5263-335b-4af8-8b82-e9965977eb67")
    @Override
    public Object caseEncapsulatedClassifier(EncapsulatedClassifier inputEncapsulatedClassifier) {
        for (EObject ownedPort : inputEncapsulatedClassifier.getOwnedPorts()) {
            this.doSwitch(ownedPort);
        }
        return null;
    }

    @objid ("b0e24f2d-f4db-4a6c-8428-a208eff5c8bc")
    @Override
    public Object caseEnumeration(Enumeration inputEnumeration) {
        Object theResult = this.visitorMap.get(inputEnumeration);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("EnumerationImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputEnumeration, inputEnumeration);
            this.behavior.visitEnumeration(inputEnumeration);
            for (EObject ownedLiteral : inputEnumeration.getOwnedLiterals()) {
                this.doSwitch(ownedLiteral);
            }
        }
        return null;
    }

    @objid ("c9a8ba7f-4d9f-4429-b9d0-d3dcd7e9de77")
    @Override
    public Object caseEnumerationLiteral(EnumerationLiteral inputEnumerationLiteral) {
        Object theResult = this.visitorMap.get(inputEnumerationLiteral);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("EnumerationLiteralImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputEnumerationLiteral, inputEnumerationLiteral);
            this.behavior.visitEnumerationLiteral(inputEnumerationLiteral);
        }
        return null;
    }

    @objid ("2b431795-25eb-4263-8d59-7e70d56e81bc")
    @Override
    public Object caseEvent(Event inputEvent) {
        return null;
    }

    @objid ("2e084c84-2786-4877-b4f7-ecc123b57a74")
    @Override
    public Object caseExceptionHandler(ExceptionHandler inputExceptionHandler) {
        Object theResult = this.visitorMap.get(inputExceptionHandler);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ExceptionHandlerImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputExceptionHandler, inputExceptionHandler);
            this.behavior.visitExceptionHandler(inputExceptionHandler);
        }
        return null;
    }

    @objid ("fd22c060-669f-46af-a4e2-5d81343b704b")
    @Override
    public Object caseExecutableNode(ExecutableNode inputExecutableNode) {
        for (EObject handler : inputExecutableNode.getHandlers()) {
            this.doSwitch(handler);
        }
        return null;
    }

    @objid ("12616057-b0fb-4732-8fb3-483c6531b405")
    @Override
    public Object caseExecutionEnvironment(ExecutionEnvironment inputExecutionEnvironment) {
        Object theResult = this.visitorMap.get(inputExecutionEnvironment);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ExecutionEnvironmentImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap
            .put(inputExecutionEnvironment, inputExecutionEnvironment);
            this.behavior.visitExecutionEnvironment(inputExecutionEnvironment);
        }
        return null;
    }

    @objid ("007a9134-b9ae-4b80-baa6-ae6827fcac63")
    @Override
    public Object caseExecutionEvent(ExecutionEvent inputExecutionEvent) {
        Object theResult = this.visitorMap.get(inputExecutionEvent);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ExecutionEventImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputExecutionEvent, inputExecutionEvent);
            this.behavior.visitExecutionEvent(inputExecutionEvent);
        }
        return null;
    }

    @objid ("f38da0b2-f9e5-43ba-b629-dc85dded7caa")
    @Override
    public Object caseExecutionOccurrenceSpecification(ExecutionOccurrenceSpecification inputExecutionOccurrenceSpecification) {
        Object theResult = this.visitorMap
                .get(inputExecutionOccurrenceSpecification);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ExecutionOccurrenceSpecificationImpl".equals(theResult
                        .getClass().getSimpleName()))) {
            this.visitorMap.put(inputExecutionOccurrenceSpecification,
                    inputExecutionOccurrenceSpecification);
            this.behavior
            .visitExecutionOccurrenceSpecification(inputExecutionOccurrenceSpecification);
        }
        return null;
    }

    @objid ("88bd4357-5f92-46c8-bd4b-7de912b891da")
    @Override
    public Object caseExecutionSpecification(ExecutionSpecification inputExecutionSpecification) {
        return null;
    }

    @objid ("1abd6556-7598-440b-a69c-12fa409e9be2")
    @Override
    public Object caseExpansionNode(ExpansionNode inputExpansionNode) {
        Object theResult = this.visitorMap.get(inputExpansionNode);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ExpansionNodeImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputExpansionNode, inputExpansionNode);
            this.behavior.visitExpansionNode(inputExpansionNode);
        }
        return null;
    }

    @objid ("11327808-8d91-4782-987a-e71d6f8e463e")
    @Override
    public Object caseExpansionRegion(ExpansionRegion inputExpansionRegion) {
        Object theResult = this.visitorMap.get(inputExpansionRegion);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ExpansionRegionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputExpansionRegion, inputExpansionRegion);
            this.behavior.visitExpansionRegion(inputExpansionRegion);
        }
        return null;
    }

    @objid ("6fc8eca4-4208-4694-9807-a13da9c0d691")
    @Override
    public Object caseExpression(Expression inputExpression) {
        Object theResult = this.visitorMap.get(inputExpression);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ExpressionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputExpression, inputExpression);
            this.behavior.visitExpression(inputExpression);
            for (EObject operand : inputExpression.getOperands()) {
                this.doSwitch(operand);
            }
        }
        return null;
    }

    @objid ("3e30d78d-7184-4444-9d57-d343dd54c827")
    @Override
    public Object caseExtend(Extend inputExtend) {
        Object theResult = this.visitorMap.get(inputExtend);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ExtendImpl".equals(theResult.getClass().getSimpleName()))) {
            this.visitorMap.put(inputExtend, inputExtend);
            this.behavior.visitExtend(inputExtend);
            this.doSwitch(inputExtend.getCondition());
        }
        return null;
    }

    @objid ("c2316afe-5ac0-45a5-865c-a4bfa5e70d96")
    @Override
    public Object caseExtension(Extension inputExtension) {
        Object theResult = this.visitorMap.get(inputExtension);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ExtensionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputExtension, inputExtension);
            this.behavior.visitExtension(inputExtension);
            for (EObject ownedEnd : inputExtension.getOwnedEnds()) {
                this.doSwitch(ownedEnd);
            }
        }
        return null;
    }

    @objid ("a7b8de6e-09fb-4729-9f0d-ce14c2445ef9")
    @Override
    public Object caseExtensionEnd(ExtensionEnd inputExtensionEnd) {
        Object theResult = this.visitorMap.get(inputExtensionEnd);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ExtensionEndImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputExtensionEnd, inputExtensionEnd);
            this.behavior.visitExtensionEnd(inputExtensionEnd);
        }
        return null;
    }

    @objid ("f385b4ae-7b73-4c5c-acb9-1d9650e29532")
    @Override
    public Object caseExtensionPoint(ExtensionPoint inputExtensionPoint) {
        Object theResult = this.visitorMap.get(inputExtensionPoint);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ExtensionPointImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputExtensionPoint, inputExtensionPoint);
            this.behavior.visitExtensionPoint(inputExtensionPoint);
        }
        return null;
    }

    @objid ("773ea02f-b946-49b6-8ecd-dd3506ae0b66")
    @Override
    public Object caseFeature(Feature inputFeature) {
        return null;
    }

    @objid ("3d0e36d9-61a7-42a0-9d4a-eb1853b44e68")
    @Override
    public Object caseFinalNode(FinalNode inputFinalNode) {
        return null;
    }

    @objid ("a35fb956-5ede-45c8-93f8-13156175ff9e")
    @Override
    public Object caseFinalState(FinalState inputFinalState) {
        Object theResult = this.visitorMap.get(inputFinalState);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("FinalStateImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputFinalState, inputFinalState);
            this.behavior.visitFinalState(inputFinalState);
        }
        return null;
    }

    @objid ("b21065c9-9087-4858-8481-31ab4b820b0f")
    @Override
    public Object caseFlowFinalNode(FlowFinalNode inputFlowFinalNode) {
        Object theResult = this.visitorMap.get(inputFlowFinalNode);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("FlowFinalNodeImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputFlowFinalNode, inputFlowFinalNode);
            this.behavior.visitFlowFinalNode(inputFlowFinalNode);
        }
        return null;
    }

    @objid ("7e1704dc-ce27-4dd1-9303-39be841fedfb")
    @Override
    public Object caseForkNode(ForkNode inputForkNode) {
        Object theResult = this.visitorMap.get(inputForkNode);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ForkNodeImpl"
                        .equals(theResult.getClass().getSimpleName()))) {
            this.visitorMap.put(inputForkNode, inputForkNode);
            this.behavior.visitForkNode(inputForkNode);
        }
        return null;
    }

    @objid ("9fcaba33-9c57-4edd-bdfa-e71c509083b3")
    @Override
    public Object caseFunctionBehavior(FunctionBehavior inputFunctionBehavior) {
        Object theResult = this.visitorMap.get(inputFunctionBehavior);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("FunctionBehaviorImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputFunctionBehavior, inputFunctionBehavior);
            this.behavior.visitFunctionBehavior(inputFunctionBehavior);
        }
        return null;
    }

    @objid ("84982b8b-cb97-4fc2-a85a-56f9343ecfe4")
    @Override
    public Object caseGate(Gate inputGate) {
        Object theResult = this.visitorMap.get(inputGate);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("GateImpl".equals(theResult.getClass().getSimpleName()))) {
            this.visitorMap.put(inputGate, inputGate);
            this.behavior.visitGate(inputGate);
        }
        return null;
    }

    @objid ("19e575e1-3a12-487f-a627-3719b2953d53")
    @Override
    public Object caseGeneralization(Generalization inputGeneralization) {
        Object theResult = this.visitorMap.get(inputGeneralization);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("GeneralizationImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputGeneralization, inputGeneralization);
            this.behavior.visitGeneralization(inputGeneralization);
        }
        return null;
    }

    @objid ("15a9a038-02d0-41bf-8cda-3255f739dd2b")
    @Override
    public Object caseGeneralizationSet(GeneralizationSet inputGeneralizationSet) {
        Object theResult = this.visitorMap.get(inputGeneralizationSet);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("GeneralizationSetImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputGeneralizationSet, inputGeneralizationSet);
            this.behavior.visitGeneralizationSet(inputGeneralizationSet);
        }
        return null;
    }

    @objid ("096461e2-ded5-4f05-bdff-b052468b86f7")
    @Override
    public Object caseGeneralOrdering(GeneralOrdering inputGeneralOrdering) {
        Object theResult = this.visitorMap.get(inputGeneralOrdering);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("GeneralOrderingImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputGeneralOrdering, inputGeneralOrdering);
            this.behavior.visitGeneralOrdering(inputGeneralOrdering);
        }
        return null;
    }

    @objid ("5da16dcb-be5b-4eec-ab13-c0751cd4df71")
    @Override
    public Object caseImage(Image inputImage) {
        Object theResult = this.visitorMap.get(inputImage);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ImageImpl".equals(theResult.getClass().getSimpleName()))) {
            this.visitorMap.put(inputImage, inputImage);
            this.behavior.visitImage(inputImage);
        }
        return null;
    }

    @objid ("e291580f-46ec-44ed-adb9-666079889e61")
    @Override
    public Object caseInclude(Include inputInclude) {
        Object theResult = this.visitorMap.get(inputInclude);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("IncludeImpl".equals(theResult.getClass().getSimpleName()))) {
            this.visitorMap.put(inputInclude, inputInclude);
            this.behavior.visitInclude(inputInclude);
        }
        return null;
    }

    @objid ("42712be8-369e-4807-b43d-e863d0a4b96b")
    @Override
    public Object caseInformationFlow(InformationFlow inputInformationFlow) {
        Object theResult = this.visitorMap.get(inputInformationFlow);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("InformationFlowImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputInformationFlow, inputInformationFlow);
            this.behavior.visitInformationFlow(inputInformationFlow);
        }
        return null;
    }

    @objid ("e3b15733-0cd2-4960-bb01-f8a0d8d767ae")
    @Override
    public Object caseInformationItem(InformationItem inputInformationItem) {
        Object theResult = this.visitorMap.get(inputInformationItem);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("InformationItemImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputInformationItem, inputInformationItem);
            this.behavior.visitInformationItem(inputInformationItem);
        }
        return null;
    }

    @objid ("1a2fc0f1-4217-42eb-bd99-f6b255c639df")
    @Override
    public Object caseInitialNode(InitialNode inputInitialNode) {
        Object theResult = this.visitorMap.get(inputInitialNode);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("InitialNodeImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputInitialNode, inputInitialNode);
            this.behavior.visitInitialNode(inputInitialNode);
        }
        return null;
    }

    @objid ("2be5883e-df90-45b7-be4c-3216bd4059f5")
    @Override
    public Object caseInputPin(InputPin inputInputPin) {
        Object theResult = this.visitorMap.get(inputInputPin);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("InputPinImpl"
                        .equals(theResult.getClass().getSimpleName()))) {
            this.visitorMap.put(inputInputPin, inputInputPin);
            this.behavior.visitInputPin(inputInputPin);
        }
        return null;
    }

    @objid ("c7504cfa-112e-45f5-a5b7-6cb40a93dac8")
    @Override
    public Object caseInstanceSpecification(InstanceSpecification inputInstanceSpecification) {
        Object theResult = this.visitorMap.get(inputInstanceSpecification);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("InstanceSpecificationImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputInstanceSpecification,
                    inputInstanceSpecification);
            this.behavior.visitInstanceSpecification(inputInstanceSpecification);
            for (EObject slot : inputInstanceSpecification.getSlots()) {
                this.doSwitch(slot);
            }
            this.doSwitch(inputInstanceSpecification
                    .getSpecification());
        }
        return null;
    }

    @objid ("a2fa9c75-7ba1-4574-ba7c-afe9881eba08")
    @Override
    public Object caseInstanceValue(InstanceValue inputInstanceValue) {
        Object theResult = this.visitorMap.get(inputInstanceValue);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("InstanceValueImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputInstanceValue, inputInstanceValue);
            this.behavior.visitInstanceValue(inputInstanceValue);
        }
        return null;
    }

    @objid ("fc3b8471-3f16-4912-9c5d-3fe8c8a4060e")
    @Override
    public Object caseInteraction(Interaction inputInteraction) {
        Object theResult = this.visitorMap.get(inputInteraction);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        
        if (theResult == null
                || !("InteractionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
        
            this.visitorMap.put(inputInteraction, inputInteraction);
            this.behavior.visitInteraction(inputInteraction);
            for (EObject lifeline : inputInteraction.getLifelines()) {
                this.doSwitch(lifeline);
            }
        
            //Gates
            for (Gate formalGate : inputInteraction.getFormalGates()) {
                ModelUtils.setLineNumber(formalGate);
                this.doSwitch(formalGate);
            }
        
            //Interaction fragment
            for (InteractionFragment fragment : inputInteraction.getFragments()) {
                ModelUtils.setLineNumber(fragment);
                this.doSwitch(fragment);
                ModelUtils.setEndLineNumber(fragment);
            }
        
            //Messages
            for (Message message : inputInteraction.getMessages()) {
                this.doSwitch(message);
            }
        
            for (EObject action : inputInteraction.getActions()) {
                this.doSwitch(action);
            }
        
        }
        return null;
    }

    @objid ("ac562f2f-31f4-46f9-a47f-d3dcd96d1d15")
    @Override
    public Object caseInteractionConstraint(InteractionConstraint inputInteractionConstraint) {
        Object theResult = this.visitorMap.get(inputInteractionConstraint);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("InteractionConstraintImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputInteractionConstraint,
                    inputInteractionConstraint);
            this.behavior.visitInteractionConstraint(inputInteractionConstraint);
            this.doSwitch(inputInteractionConstraint.getMinint());
            this.doSwitch(inputInteractionConstraint.getMaxint());
        }
        return null;
    }

    @objid ("b506a490-e72b-4a64-87b4-e5b81f3db81d")
    @Override
    public Object caseInteractionFragment(InteractionFragment inputInteractionFragment) {
        for (EObject generalOrdering : inputInteractionFragment
                .getGeneralOrderings()) {
            this.doSwitch(generalOrdering);
        }
        return null;
    }

    @objid ("9ef4ecad-4b18-46e2-838d-5a134b79fe02")
    @Override
    public Object caseInteractionOperand(InteractionOperand inputInteractionOperand) {
        Object theResult = this.visitorMap.get(inputInteractionOperand);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("InteractionOperandImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
        
            this.visitorMap.put(inputInteractionOperand, inputInteractionOperand);
            this.behavior.visitInteractionOperand(inputInteractionOperand);
        
            this.doSwitch(inputInteractionOperand.getGuard());
        
            for (InteractionFragment fragment : inputInteractionOperand.getFragments()) {
                ModelUtils.setLineNumber(fragment);
                this.doSwitch(fragment);
                ModelUtils.setEndLineNumber(fragment);
            }
        }
        return null;
    }

    @objid ("9cd6b4b5-76bb-4d4b-9377-71ea05c0c63a")
    @Override
    public Object caseInteractionUse(InteractionUse inputInteractionUse) {
        Object theResult = this.visitorMap.get(inputInteractionUse);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("InteractionUseImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputInteractionUse, inputInteractionUse);
            this.behavior.visitInteractionUse(inputInteractionUse);
        
            for (Gate actualGate : inputInteractionUse.getActualGates()) {
                ModelUtils.setLineNumber(actualGate);
                this.doSwitch(actualGate);
            }
        
            for (EObject argument : inputInteractionUse.getArguments()) {
                this.doSwitch(argument);
            }
        }
        return null;
    }

    @objid ("ea28ea21-aa11-4aca-a004-eb63e332bf66")
    @Override
    public Object caseInterface(Interface inputInterface) {
        Object theResult = this.visitorMap.get(inputInterface);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("InterfaceImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputInterface, inputInterface);
            this.behavior.visitInterface(inputInterface);
            for (EObject ownedAttribute : inputInterface.getOwnedAttributes()) {
                this.doSwitch(ownedAttribute);
            }
            for (EObject ownedOperation : inputInterface.getOwnedOperations()) {
                this.doSwitch(ownedOperation);
            }
            for (EObject nestedClassifier : inputInterface
                    .getNestedClassifiers()) {
                this.doSwitch(nestedClassifier);
            }
            for (EObject ownedReception : inputInterface.getOwnedReceptions()) {
                this.doSwitch(ownedReception);
            }
            this.doSwitch(inputInterface.getProtocol());
        }
        return null;
    }

    @objid ("ef1c006a-9165-4570-bb01-a05b2333c9e8")
    @Override
    public Object caseInterfaceRealization(InterfaceRealization inputInterfaceRealization) {
        Object theResult = this.visitorMap.get(inputInterfaceRealization);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("InterfaceRealizationImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap
            .put(inputInterfaceRealization, inputInterfaceRealization);
            this.behavior.visitInterfaceRealization(inputInterfaceRealization);
        }
        return null;
    }

    @objid ("fa4127c0-054c-4ab1-a0e6-94293f3a0e1d")
    @Override
    public Object caseInterruptibleActivityRegion(InterruptibleActivityRegion inputInterruptibleActivityRegion) {
        Object theResult = this.visitorMap.get(inputInterruptibleActivityRegion);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("InterruptibleActivityRegionImpl".equals(theResult
                        .getClass().getSimpleName()))) {
            this.visitorMap.put(inputInterruptibleActivityRegion,
                    inputInterruptibleActivityRegion);
            this.behavior
            .visitInterruptibleActivityRegion(inputInterruptibleActivityRegion);
        }
        return null;
    }

    @objid ("69bec4bb-d208-45e5-8da1-24cdd4a3509b")
    @Override
    public Object caseInterval(Interval inputInterval) {
        Object theResult = this.visitorMap.get(inputInterval);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("IntervalImpl"
                        .equals(theResult.getClass().getSimpleName()))) {
            this.visitorMap.put(inputInterval, inputInterval);
            this.behavior.visitInterval(inputInterval);
        }
        return null;
    }

    @objid ("4545e0d8-f55a-4c9e-80e2-602154764448")
    @Override
    public Object caseIntervalConstraint(IntervalConstraint inputIntervalConstraint) {
        Object theResult = this.visitorMap.get(inputIntervalConstraint);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("IntervalConstraintImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputIntervalConstraint, inputIntervalConstraint);
            this.behavior.visitIntervalConstraint(inputIntervalConstraint);
            this.doSwitch(inputIntervalConstraint.getSpecification());
        }
        return null;
    }

    @objid ("2de3a0b2-912d-44ef-bf81-a39f2adbd0af")
    @Override
    public Object caseInvocationAction(InvocationAction inputInvocationAction) {
        for (EObject argument : inputInvocationAction.getArguments()) {
            this.doSwitch(argument);
        }
        return null;
    }

    @objid ("ee0072c5-8aca-4ecd-ad7a-2a488093e0c3")
    @Override
    public Object caseJoinNode(JoinNode inputJoinNode) {
        Object theResult = this.visitorMap.get(inputJoinNode);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("JoinNodeImpl"
                        .equals(theResult.getClass().getSimpleName()))) {
            this.visitorMap.put(inputJoinNode, inputJoinNode);
            this.behavior.visitJoinNode(inputJoinNode);
            this.doSwitch(inputJoinNode.getJoinSpec());
        }
        return null;
    }

    @objid ("67a990cf-1ba7-418e-9776-a14f715b9366")
    @Override
    public Object caseLifeline(Lifeline inputLifeline) {
        Object theResult = this.visitorMap.get(inputLifeline);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("LifelineImpl"
                        .equals(theResult.getClass().getSimpleName()))) {
            this.visitorMap.put(inputLifeline, inputLifeline);
            this.behavior.visitLifeline(inputLifeline);
            this.doSwitch(inputLifeline.getSelector());
        }
        return null;
    }

    @objid ("0c8032ab-958b-4eed-8c46-e257b8b1effc")
    @Override
    public Object caseLinkAction(LinkAction inputLinkAction) {
        for (EObject endData : inputLinkAction.getEndData()) {
            this.doSwitch(endData);
        }
        for (EObject inputValue : inputLinkAction.getInputValues()) {
            this.doSwitch(inputValue);
        }
        return null;
    }

    @objid ("960f6d40-2d6f-4e58-8e8d-42ceeaf24613")
    @Override
    public Object caseLinkEndCreationData(LinkEndCreationData inputLinkEndCreationData) {
        Object theResult = this.visitorMap.get(inputLinkEndCreationData);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("LinkEndCreationDataImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputLinkEndCreationData, inputLinkEndCreationData);
            this.behavior.visitLinkEndCreationData(inputLinkEndCreationData);
        }
        return null;
    }

    @objid ("b1798235-bc9d-4c5c-8e70-7858d9464e32")
    @Override
    public Object caseLinkEndData(LinkEndData inputLinkEndData) {
        Object theResult = this.visitorMap.get(inputLinkEndData);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("LinkEndDataImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputLinkEndData, inputLinkEndData);
            this.behavior.visitLinkEndData(inputLinkEndData);
            for (EObject qualifier : inputLinkEndData.getQualifiers()) {
                this.doSwitch(qualifier);
            }
        }
        return null;
    }

    @objid ("287bca2d-5a4c-460a-acd6-d50c9c80ea36")
    @Override
    public Object caseLinkEndDestructionData(LinkEndDestructionData inputLinkEndDestructionData) {
        Object theResult = this.visitorMap.get(inputLinkEndDestructionData);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("LinkEndDestructionDataImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputLinkEndDestructionData,
                    inputLinkEndDestructionData);
            this.behavior.visitLinkEndDestructionData(inputLinkEndDestructionData);
        }
        return null;
    }

    @objid ("f0acbda2-8fa1-489e-9656-49128fbe7522")
    @Override
    public Object caseLiteralBoolean(LiteralBoolean inputLiteralBoolean) {
        Object theResult = this.visitorMap.get(inputLiteralBoolean);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("LiteralBooleanImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputLiteralBoolean, inputLiteralBoolean);
            this.behavior.visitLiteralBoolean(inputLiteralBoolean);
        }
        return null;
    }

    @objid ("e1856304-7c79-4917-b538-fcc7dcdea630")
    @Override
    public Object caseLiteralInteger(LiteralInteger inputLiteralInteger) {
        Object theResult = this.visitorMap.get(inputLiteralInteger);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("LiteralIntegerImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputLiteralInteger, inputLiteralInteger);
            this.behavior.visitLiteralInteger(inputLiteralInteger);
        }
        return null;
    }

    @objid ("897072f2-c6a8-4052-8233-004ec5f0317d")
    @Override
    public Object caseLiteralNull(LiteralNull inputLiteralNull) {
        Object theResult = this.visitorMap.get(inputLiteralNull);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("LiteralNullImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputLiteralNull, inputLiteralNull);
            this.behavior.visitLiteralNull(inputLiteralNull);
        }
        return null;
    }

    @objid ("f44703d0-3e98-408a-9824-04a0bcf0239d")
    @Override
    public Object caseLiteralSpecification(LiteralSpecification inputLiteralSpecification) {
        return null;
    }

    @objid ("95e730bd-c0c8-43f8-90ce-c069d69b231b")
    @Override
    public Object caseLiteralString(LiteralString inputLiteralString) {
        Object theResult = this.visitorMap.get(inputLiteralString);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("LiteralStringImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputLiteralString, inputLiteralString);
            this.behavior.visitLiteralString(inputLiteralString);
        }
        return null;
    }

    @objid ("045883f7-e87b-4757-a4c0-51fcdf6f8e91")
    @Override
    public Object caseLiteralUnlimitedNatural(LiteralUnlimitedNatural inputLiteralUnlimitedNatural) {
        Object theResult = this.visitorMap.get(inputLiteralUnlimitedNatural);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("LiteralUnlimitedNaturalImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputLiteralUnlimitedNatural,
                    inputLiteralUnlimitedNatural);
            this.behavior.visitLiteralUnlimitedNatural(inputLiteralUnlimitedNatural);
        }
        return null;
    }

    @objid ("a908d807-8f19-4753-830d-9af7e3083cb1")
    @Override
    public Object caseLoopNode(LoopNode inputLoopNode) {
        Object theResult = this.visitorMap.get(inputLoopNode);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("LoopNodeImpl"
                        .equals(theResult.getClass().getSimpleName()))) {
            this.visitorMap.put(inputLoopNode, inputLoopNode);
            this.behavior.visitLoopNode(inputLoopNode);
            for (EObject result : inputLoopNode.getResults()) {
                this.doSwitch(result);
            }
            for (EObject loopVariableInput : inputLoopNode
                    .getLoopVariableInputs()) {
                this.doSwitch(loopVariableInput);
            }
        }
        return null;
    }

    @objid ("e9e4534f-a221-43ef-a77a-8ce0c9043f2f")
    @Override
    public Object caseManifestation(Manifestation inputManifestation) {
        Object theResult = this.visitorMap.get(inputManifestation);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ManifestationImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputManifestation, inputManifestation);
            this.behavior.visitManifestation(inputManifestation);
        }
        return null;
    }

    @objid ("2fb29d84-3ae6-4299-a82b-c20e27601714")
    @Override
    public Object caseMergeNode(MergeNode inputMergeNode) {
        Object theResult = this.visitorMap.get(inputMergeNode);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("MergeNodeImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputMergeNode, inputMergeNode);
            this.behavior.visitMergeNode(inputMergeNode);
        }
        return null;
    }

    @objid ("86b4c55d-e4ed-4a40-8deb-8e697820d040")
    @Override
    public Object caseMessage(Message inputMessage) {
        Object theResult = this.visitorMap.get(inputMessage);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("MessageImpl".equals(theResult.getClass().getSimpleName()))) {
            this.visitorMap.put(inputMessage, inputMessage);
            this.behavior.visitMessage(inputMessage);
            for (EObject argument : inputMessage.getArguments()) {
                this.doSwitch(argument);
            }
        }
        return null;
    }

    @objid ("84bd8b01-df55-4975-89a5-973146e63a0f")
    @Override
    public Object caseMessageEnd(MessageEnd inputMessageEnd) {
        return null;
    }

    @objid ("4329f04f-674b-40a1-85cb-ceb111137646")
    @Override
    public Object caseMessageEvent(MessageEvent inputMessageEvent) {
        return null;
    }

    @objid ("33496b27-6e16-4bfd-8923-d99d61eae114")
    @Override
    public Object caseMessageOccurrenceSpecification(MessageOccurrenceSpecification inputMessageOccurrenceSpecification) {
        Object theResult = this.visitorMap.get(inputMessageOccurrenceSpecification);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("MessageOccurrenceSpecificationImpl".equals(theResult
                        .getClass().getSimpleName()))) {
            this.visitorMap.put(inputMessageOccurrenceSpecification,
                    inputMessageOccurrenceSpecification);
            this.behavior
            .visitMessageOccurrenceSpecification(inputMessageOccurrenceSpecification);
        }
        return null;
    }

    @objid ("d3a30932-1ba9-440b-bccf-13aad073bc6f")
    @Override
    public Object caseModel(Model inputModel) {
        Object theResult = this.visitorMap.get(inputModel);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ModelImpl".equals(theResult.getClass().getSimpleName()))) {
            this.visitorMap.put(inputModel, inputModel);
            this.behavior.visitModel(inputModel);
        }
        return null;
    }

    @objid ("c6435716-82ad-4d55-b098-812274d7ed5d")
    @Override
    public Object caseMultiplicityElement(MultiplicityElement inputMultiplicityElement) {
        this.doSwitch(inputMultiplicityElement.getUpperValue());
        this.doSwitch(inputMultiplicityElement.getLowerValue());
        return null;
    }

    @objid ("d354e9e8-396d-436d-8a31-c92ae08f8d80")
    @Override
    public Object caseNamedElement(NamedElement inputNamedElement) {
        this.doSwitch(inputNamedElement.getNameExpression());
        return null;
    }

    @objid ("f5e1677b-de6c-4284-88b5-89e4b9e6200f")
    @Override
    public Object caseNamespace(Namespace inputNamespace) {
        for (EObject elementImport : inputNamespace.getElementImports()) {
            this.doSwitch(elementImport);
        }
        for (EObject packageImport : inputNamespace.getPackageImports()) {
            this.doSwitch(packageImport);
        }
        for (EObject ownedRule : inputNamespace.getOwnedRules()) {
            this.doSwitch(ownedRule);
        }
        for (EObject ownedMember : inputNamespace.getOwnedMembers()) {
            this.doSwitch(ownedMember);
        }
        return null;
    }

    @objid ("d08ebd19-3339-47d7-a9a5-823f6621fff0")
    @Override
    public Object caseNode(Node inputNode) {
        Object theResult = this.visitorMap.get(inputNode);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("NodeImpl".equals(theResult.getClass().getSimpleName()))) {
            this.visitorMap.put(inputNode, inputNode);
            this.behavior.visitNode(inputNode);
            for (EObject nestedNode : inputNode.getNestedNodes()) {
                this.doSwitch(nestedNode);
            }
        }
        return null;
    }

    @objid ("7afc3ec4-f7e7-4929-a9aa-c5dfd193fe9d")
    @Override
    public Object caseObjectFlow(ObjectFlow inputObjectFlow) {
        Object theResult = this.visitorMap.get(inputObjectFlow);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ObjectFlowImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputObjectFlow, inputObjectFlow);
            this.behavior.visitObjectFlow(inputObjectFlow);
        }
        return null;
    }

    @objid ("0f307b19-e9c3-48cf-9c7a-d8b34bfc6fce")
    @Override
    public Object caseObjectNode(ObjectNode inputObjectNode) {
        this.doSwitch(inputObjectNode.getUpperBound());
        return null;
    }

    @objid ("8b1bdeb2-7ac8-49a8-b5d7-20abce706b2f")
    @Override
    public Object caseObservation(final Observation inputIservation) {
        return null;
    }

    @objid ("4b4f7ed0-6854-4e4b-9ae2-818e0fa6fca0")
    @Override
    public Object caseOccurrenceSpecification(OccurrenceSpecification inputOccurrenceSpecification) {
        Object theResult = this.visitorMap.get(inputOccurrenceSpecification);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("OccurrenceSpecificationImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputOccurrenceSpecification,
                    inputOccurrenceSpecification);
            this.behavior.visitOccurrenceSpecification(inputOccurrenceSpecification);
        }
        return null;
    }

    @objid ("6766a3c4-78bc-45a8-8514-e54d21d571f1")
    @Override
    public Object caseOpaqueAction(OpaqueAction inputOpaqueAction) {
        Object theResult = this.visitorMap.get(inputOpaqueAction);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("OpaqueActionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputOpaqueAction, inputOpaqueAction);
            this.behavior.visitOpaqueAction(inputOpaqueAction);
        
            for (EObject inputValue : inputOpaqueAction.getInputValues()) {
                this.doSwitch(inputValue);
            }
            for (EObject outputValue : inputOpaqueAction.getOutputValues()) {
                this.doSwitch(outputValue);
            }
        }
        return null;
    }

    @objid ("bd896445-3f24-4fa8-a437-774fd0d7e984")
    @Override
    public Object caseOpaqueBehavior(OpaqueBehavior inputOpaqueBehavior) {
        Object theResult = this.visitorMap.get(inputOpaqueBehavior);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("OpaqueBehaviorImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputOpaqueBehavior, inputOpaqueBehavior);
            this.behavior.visitOpaqueBehavior(inputOpaqueBehavior);
        }
        return null;
    }

    @objid ("ee80f362-9a17-4340-b4fb-8cb2c506f340")
    @Override
    public Object caseOpaqueExpression(OpaqueExpression inputOpaqueExpression) {
        Object theResult = this.visitorMap.get(inputOpaqueExpression);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("OpaqueExpressionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputOpaqueExpression, inputOpaqueExpression);
            this.behavior.visitOpaqueExpression(inputOpaqueExpression);
        }
        return null;
    }

    @objid ("9b72c8a7-2341-4f01-8d50-547f40db032f")
    @Override
    public Object caseOperation(Operation inputOperation) {
        Object theResult = this.visitorMap.get(inputOperation);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("OperationImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputOperation, inputOperation);
            this.behavior.visitOperation(inputOperation);
            for (EObject precondition : inputOperation.getPreconditions()) {
                this.doSwitch(precondition);
            }
            for (EObject postcondition : inputOperation.getPostconditions()) {
                this.doSwitch(postcondition);
            }
            this.doSwitch(inputOperation.getBodyCondition());
            for (EObject ownedParameter : inputOperation.getOwnedParameters()) {
                this.doSwitch(ownedParameter);
            }
        }
        return null;
    }

    @objid ("ea2c61e9-e67f-4eee-b75e-15015698e0db")
    @Override
    public Object caseOperationTemplateParameter(OperationTemplateParameter inputOperationTemplateParameter) {
        Object theResult = this.visitorMap.get(inputOperationTemplateParameter);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("OperationTemplateParameterImpl".equals(theResult
                        .getClass().getSimpleName()))) {
            this.visitorMap.put(inputOperationTemplateParameter,
                    inputOperationTemplateParameter);
            this.behavior
            .visitOperationTemplateParameter(inputOperationTemplateParameter);
        }
        return null;
    }

    @objid ("6ec9d1b9-e655-4b33-b083-2823f00aedd8")
    @Override
    public Object caseOutputPin(OutputPin inputOutputPin) {
        Object theResult = this.visitorMap.get(inputOutputPin);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("OutputPinImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputOutputPin, inputOutputPin);
            this.behavior.visitOutputPin(inputOutputPin);
        }
        return null;
    }

    @objid ("b35497f2-06c2-4da8-8774-7c6e1ea00a9f")
    @Override
    public Object casePackage(Package inputPackage) {
        Object theResult = this.visitorMap.get(inputPackage);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("PackageImpl".equals(theResult.getClass().getSimpleName()))) {
            this.visitorMap.put(inputPackage, inputPackage);
            // [CVI]: ignore visit of relations defined at "Package" level for
            // an element of type "Model"
            if (inputPackage instanceof Model)
                return null;
        
            this.behavior.visitPackage(inputPackage);
            for (EObject packageMerge : inputPackage.getPackageMerges()) {
                this.doSwitch(packageMerge);
            }
            for (EObject packagedElement : inputPackage.getPackagedElements()) {
                this.doSwitch(packagedElement);
            }
            for (EObject ownedType : inputPackage.getOwnedTypes()) {
                this.doSwitch(ownedType);
            }
            for (EObject nestedPackage : inputPackage.getNestedPackages()) {
                this.doSwitch(nestedPackage);
            }
            for (EObject profileApplication : inputPackage
                    .getProfileApplications()) {
                this.doSwitch(profileApplication);
            }
        }
        return null;
    }

    @objid ("24718b5d-b72e-4848-b006-03233018b4d6")
    @Override
    public Object casePackageableElement(PackageableElement inputPackageableElement) {
        return null;
    }

    @objid ("073a1846-1522-45c0-aa23-b6dc686b8115")
    @Override
    public Object casePackageImport(PackageImport inputPackageImport) {
        Object theResult = this.visitorMap.get(inputPackageImport);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("PackageImportImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputPackageImport, inputPackageImport);
            this.behavior.visitPackageImport(inputPackageImport);
        
            //            Package importedPack = inputPackageImport.getImportedPackage();
            //            if (!TotalImportMap.getInstance().containsKey(importedPack))
            //                this.doSwitch(importedPack);
        }
        return null;
    }

    @objid ("e5b274e9-91d7-4470-9844-6bc4091ea326")
    @Override
    public Object casePackageMerge(PackageMerge inputPackageMerge) {
        Object theResult = this.visitorMap.get(inputPackageMerge);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("PackageMergeImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputPackageMerge, inputPackageMerge);
            this.behavior.visitPackageMerge(inputPackageMerge);
        }
        return null;
    }

    @objid ("c0c7e45e-a059-4bcb-93f5-5e53a9530e14")
    @Override
    public Object caseParameter(Parameter inputParameter) {
        Object theResult = this.visitorMap.get(inputParameter);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ParameterImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputParameter, inputParameter);
            this.behavior.visitParameter(inputParameter);
            this.doSwitch(inputParameter.getDefaultValue());
        }
        return null;
    }

    @objid ("80275f73-273a-4ecc-902c-738f43d4f93f")
    @Override
    public Object caseParameterableElement(ParameterableElement inputParameterableElement) {
        return null;
    }

    @objid ("29b3e34c-229f-4990-b64c-52e2203ebf22")
    @Override
    public Object caseParameterSet(ParameterSet inputParameterSet) {
        Object theResult = this.visitorMap.get(inputParameterSet);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ParameterSetImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputParameterSet, inputParameterSet);
            this.behavior.visitParameterSet(inputParameterSet);
            for (EObject condition : inputParameterSet.getConditions()) {
                this.doSwitch(condition);
            }
        }
        return null;
    }

    @objid ("4902fce2-ff9c-4123-9dac-ff05ffc91837")
    @Override
    public Object casePartDecomposition(PartDecomposition inputPartDecomposition) {
        Object theResult = this.visitorMap.get(inputPartDecomposition);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("PartDecompositionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputPartDecomposition, inputPartDecomposition);
            this.behavior.visitPartDecomposition(inputPartDecomposition);
        }
        return null;
    }

    @objid ("ff897010-ec26-4584-b52e-2b63ede6ac03")
    @Override
    public Object casePin(Pin inputPin) {
        Object theResult = this.visitorMap.get(inputPin);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("PinImpl".equals(theResult.getClass().getSimpleName()))) {
            this.visitorMap.put(inputPin, inputPin);
            this.behavior.visitPin(inputPin);
        }
        return null;
    }

    @objid ("366f8ee3-37e3-4dd0-ba4a-ac3f41b65517")
    @Override
    public Object casePort(Port inputPort) {
        Object theResult = this.visitorMap.get(inputPort);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("PortImpl".equals(theResult.getClass().getSimpleName()))) {
            this.visitorMap.put(inputPort, inputPort);
            this.behavior.visitPort(inputPort);
        }
        return null;
    }

    @objid ("b671cd3b-de97-4115-8749-748cc0b103e3")
    @Override
    public Object casePrimitiveType(PrimitiveType inputPrimitiveType) {
        Object theResult = this.visitorMap.get(inputPrimitiveType);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("PrimitiveTypeImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputPrimitiveType, inputPrimitiveType);
            this.behavior.visitPrimitiveType(inputPrimitiveType);
        
            for (EObject ownedTemplateBinding : inputPrimitiveType.getTemplateBindings()) {
                this.doSwitch(ownedTemplateBinding);
            }
        }
        return null;
    }

    @objid ("3b68f7b9-bb15-4f38-991c-0d872fee2c36")
    @Override
    public Object caseProfile(Profile inputProfile) {
        Object theResult = this.visitorMap.get(inputProfile);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ProfileImpl".equals(theResult.getClass().getSimpleName()))) {
            this.visitorMap.put(inputProfile, inputProfile);
            this.behavior.visitProfile(inputProfile);
            for (EObject ownedStereotype : inputProfile.getOwnedStereotypes()) {
                this.doSwitch(ownedStereotype);
            }
        
        }
        return null;
    }

    @objid ("b1e8055a-6395-4beb-98e1-0861700e055a")
    @Override
    public Object caseProfileApplication(ProfileApplication inputProfileApplication) {
        Object theResult = this.visitorMap.get(inputProfileApplication);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ProfileApplicationImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputProfileApplication, inputProfileApplication);
            this.behavior.visitProfileApplication(inputProfileApplication);
            try{
                this.doSwitch( inputProfileApplication.getAppliedProfile());
            }catch(Exception e ){
                Xmi.LOG.error(Xmi.PLUGIN_ID, e);
            }
        
        }
        return null;
    }

    @objid ("299b8218-9208-47ed-b572-2d1a691189cc")
    @Override
    public Object caseProperty(Property inputProperty) {
        Object theResult = this.visitorMap.get(inputProperty);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("PropertyImpl"
                        .equals(theResult.getClass().getSimpleName()))) {
            this.visitorMap.put(inputProperty, inputProperty);
            this.behavior.visitProperty(inputProperty);
            this.doSwitch(inputProperty.getDefaultValue());
            for (EObject qualifier : inputProperty.getQualifiers()) {
                this.doSwitch(qualifier);
            }
        }
        return null;
    }

    @objid ("4a1fa45f-272b-46f0-8e03-12bbee7c0cee")
    @Override
    public Object caseProtocolConformance(ProtocolConformance inputProtocolConformance) {
        Object theResult = this.visitorMap.get(inputProtocolConformance);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ProtocolConformanceImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputProtocolConformance, inputProtocolConformance);
            this.behavior.visitProtocolConformance(inputProtocolConformance);
        }
        return null;
    }

    @objid ("636179f0-2b8f-414a-99c2-1899ce099e6d")
    @Override
    public Object caseProtocolStateMachine(ProtocolStateMachine inputProtocolStateMachine) {
        Object theResult = this.visitorMap.get(inputProtocolStateMachine);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ProtocolStateMachineImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap
            .put(inputProtocolStateMachine, inputProtocolStateMachine);
            this.behavior.visitProtocolStateMachine(inputProtocolStateMachine);
            for (EObject conformance : inputProtocolStateMachine
                    .getConformances()) {
                this.doSwitch(conformance);
            }
        }
        return null;
    }

    @objid ("d3c86c41-1fd8-40ab-b634-2f880a965be3")
    @Override
    public Object caseProtocolTransition(ProtocolTransition inputProtocolTransition) {
        Object theResult = this.visitorMap.get(inputProtocolTransition);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ProtocolTransitionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputProtocolTransition, inputProtocolTransition);
            this.behavior.visitProtocolTransition(inputProtocolTransition);
            this.doSwitch(inputProtocolTransition.getPostCondition());
            this.doSwitch(inputProtocolTransition.getPreCondition());
        }
        return null;
    }

    @objid ("0eb1b2ab-e02f-445c-a21c-6728ae0d817d")
    @Override
    public Object casePseudostate(Pseudostate inputPseudostate) {
        Object theResult = this.visitorMap.get(inputPseudostate);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("PseudostateImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputPseudostate, inputPseudostate);
            this.behavior.visitPseudostate(inputPseudostate);
        }
        return null;
    }

    @objid ("a001ee0d-a00d-49fa-9181-5f805fac742c")
    @Override
    public Object caseQualifierValue(QualifierValue inputQualifierValue) {
        Object theResult = this.visitorMap.get(inputQualifierValue);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("QualifierValueImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputQualifierValue, inputQualifierValue);
            this.behavior.visitQualifierValue(inputQualifierValue);
        }
        return null;
    }

    @objid ("ef47926b-95f2-4c2e-8411-7e47f0bb0c17")
    @Override
    public Object caseRaiseExceptionAction(RaiseExceptionAction inputRaiseExceptionAction) {
        Object theResult = this.visitorMap.get(inputRaiseExceptionAction);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("RaiseExceptionActionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap
            .put(inputRaiseExceptionAction, inputRaiseExceptionAction);
            this.behavior.visitRaiseExceptionAction(inputRaiseExceptionAction);
            this.doSwitch(inputRaiseExceptionAction.getException());
        }
        return null;
    }

    @objid ("6afe2c04-8310-41d5-872d-4f9703d14f1c")
    @Override
    public Object caseReadExtentAction(ReadExtentAction inputReadExtentAction) {
        Object theResult = this.visitorMap.get(inputReadExtentAction);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ReadExtentActionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputReadExtentAction, inputReadExtentAction);
            this.behavior.visitReadExtentAction(inputReadExtentAction);
            this.doSwitch(inputReadExtentAction.getResult());
        }
        return null;
    }

    @objid ("ec940ecc-ad5d-4590-995f-1290f0137697")
    @Override
    public Object caseReadIsClassifiedObjectAction(ReadIsClassifiedObjectAction inputReadIsClassifiedObjectAction) {
        Object theResult = this.visitorMap.get(inputReadIsClassifiedObjectAction);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ReadIsClassifiedObjectActionImpl".equals(theResult
                        .getClass().getSimpleName()))) {
            this.visitorMap.put(inputReadIsClassifiedObjectAction,
                    inputReadIsClassifiedObjectAction);
            this.behavior
            .visitReadIsClassifiedObjectAction(inputReadIsClassifiedObjectAction);
            this.doSwitch(inputReadIsClassifiedObjectAction
                    .getResult());
            this.doSwitch(inputReadIsClassifiedObjectAction
                    .getObject());
        }
        return null;
    }

    @objid ("2e9256fc-441f-4ee8-9ea5-b3ef578f8a42")
    @Override
    public Object caseReadLinkAction(ReadLinkAction inputReadLinkAction) {
        Object theResult = this.visitorMap.get(inputReadLinkAction);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ReadLinkActionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputReadLinkAction, inputReadLinkAction);
            this.behavior.visitReadLinkAction(inputReadLinkAction);
            this.doSwitch(inputReadLinkAction.getResult());
        }
        return null;
    }

    @objid ("e31fa33b-de68-4792-b5c3-f6b81019ba47")
    @Override
    public Object caseReadLinkObjectEndAction(ReadLinkObjectEndAction inputReadLinkObjectEndAction) {
        Object theResult = this.visitorMap.get(inputReadLinkObjectEndAction);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ReadLinkObjectEndActionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputReadLinkObjectEndAction,
                    inputReadLinkObjectEndAction);
            this.behavior.visitReadLinkObjectEndAction(inputReadLinkObjectEndAction);
            this.doSwitch(inputReadLinkObjectEndAction.getObject());
            this.doSwitch(inputReadLinkObjectEndAction.getResult());
        }
        return null;
    }

    @objid ("2b556956-d346-46a1-bf94-94175c6f38df")
    @Override
    public Object caseReadLinkObjectEndQualifierAction(ReadLinkObjectEndQualifierAction inputReadLinkObjectEndQualifierAction) {
        Object theResult = this.visitorMap
                .get(inputReadLinkObjectEndQualifierAction);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ReadLinkObjectEndQualifierActionImpl".equals(theResult
                        .getClass().getSimpleName()))) {
            this.visitorMap.put(inputReadLinkObjectEndQualifierAction,
                    inputReadLinkObjectEndQualifierAction);
            this.behavior
            .visitReadLinkObjectEndQualifierAction(inputReadLinkObjectEndQualifierAction);
            this.doSwitch(inputReadLinkObjectEndQualifierAction
                    .getObject());
            this.doSwitch(inputReadLinkObjectEndQualifierAction
                    .getResult());
        }
        return null;
    }

    @objid ("3f807ec1-f4fc-4eff-a69e-dffec05f04a2")
    @Override
    public Object caseReadSelfAction(ReadSelfAction inputReadSelfAction) {
        Object theResult = this.visitorMap.get(inputReadSelfAction);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ReadSelfActionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputReadSelfAction, inputReadSelfAction);
            this.behavior.visitReadSelfAction(inputReadSelfAction);
            this.doSwitch(inputReadSelfAction.getResult());
        }
        return null;
    }

    @objid ("2d0090aa-fada-4e56-9b73-c06957cfdf61")
    @Override
    public Object caseReadStructuralFeatureAction(ReadStructuralFeatureAction inputReadStructuralFeatureAction) {
        Object theResult = this.visitorMap.get(inputReadStructuralFeatureAction);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ReadStructuralFeatureActionImpl".equals(theResult
                        .getClass().getSimpleName()))) {
            this.visitorMap.put(inputReadStructuralFeatureAction,
                    inputReadStructuralFeatureAction);
            this.behavior
            .visitReadStructuralFeatureAction(inputReadStructuralFeatureAction);
            this.doSwitch(inputReadStructuralFeatureAction
                    .getResult());
        }
        return null;
    }

    @objid ("524ba8d9-33d3-4127-901f-e3c8488a3c1c")
    @Override
    public Object caseReadVariableAction(ReadVariableAction inputReadVariableAction) {
        Object theResult = this.visitorMap.get(inputReadVariableAction);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ReadVariableActionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputReadVariableAction, inputReadVariableAction);
            this.behavior.visitReadVariableAction(inputReadVariableAction);
            this.doSwitch(inputReadVariableAction.getResult());
        }
        return null;
    }

    @objid ("1fd0cdb9-1bd9-4b1d-a7fb-7ccd72577cc9")
    @Override
    public Object caseRealization(Realization inputRealization) {
        Object theResult = this.visitorMap.get(inputRealization);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("RealizationImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputRealization, inputRealization);
            this.behavior.visitRealization(inputRealization);
        }
        return null;
    }

    @objid ("e6c8a0a0-0f04-45a3-ae0a-a22d41bc5ad2")
    @Override
    public Object caseReceiveOperationEvent(ReceiveOperationEvent inputReceiveOperationEvent) {
        Object theResult = this.visitorMap.get(inputReceiveOperationEvent);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ReceiveOperationEventImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputReceiveOperationEvent,
                    inputReceiveOperationEvent);
            this.behavior.visitReceiveOperationEvent(inputReceiveOperationEvent);
        }
        return null;
    }

    @objid ("49c92e68-c2cf-425b-80bc-5bddf739aa10")
    @Override
    public Object caseReceiveSignalEvent(ReceiveSignalEvent inputReceiveSignalEvent) {
        Object theResult = this.visitorMap.get(inputReceiveSignalEvent);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ReceiveSignalEventImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputReceiveSignalEvent, inputReceiveSignalEvent);
            this.behavior.visitReceiveSignalEvent(inputReceiveSignalEvent);
        }
        return null;
    }

    @objid ("44fcd601-2194-45b3-a098-a1a7b41c85b8")
    @Override
    public Object caseReception(Reception inputReception) {
        Object theResult = this.visitorMap.get(inputReception);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ReceptionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputReception, inputReception);
            this.behavior.visitReception(inputReception);
        }
        return null;
    }

    @objid ("2c24fb67-b1a9-4b9a-8651-f7d26d9434be")
    @Override
    public Object caseReclassifyObjectAction(ReclassifyObjectAction inputReclassifyObjectAction) {
        Object theResult = this.visitorMap.get(inputReclassifyObjectAction);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ReclassifyObjectActionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputReclassifyObjectAction,
                    inputReclassifyObjectAction);
            this.behavior.visitReclassifyObjectAction(inputReclassifyObjectAction);
            this.doSwitch(inputReclassifyObjectAction.getObject());
        }
        return null;
    }

    @objid ("2cd8bd69-de07-4d2e-9527-7219b15441de")
    @Override
    public Object caseRedefinableElement(RedefinableElement inputRedefinableElement) {
        return null;
    }

    @objid ("8c637e54-584c-4b32-813a-4e5633c8371e")
    @Override
    public Object caseRedefinableTemplateSignature(RedefinableTemplateSignature inputRedefinableTemplateSignature) {
        Object theResult = this.visitorMap.get(inputRedefinableTemplateSignature);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("RedefinableTemplateSignatureImpl".equals(theResult
                        .getClass().getSimpleName()))) {
            this.visitorMap.put(inputRedefinableTemplateSignature,
                    inputRedefinableTemplateSignature);
            this.behavior
            .visitRedefinableTemplateSignature(inputRedefinableTemplateSignature);
        }
        return null;
    }

    @objid ("5cc0ad85-e9f8-4b74-9372-3e8e250f4f78")
    @Override
    public Object caseReduceAction(ReduceAction inputReduceAction) {
        Object theResult = this.visitorMap.get(inputReduceAction);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ReduceActionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputReduceAction, inputReduceAction);
            this.behavior.visitReduceAction(inputReduceAction);
            this.doSwitch(inputReduceAction.getResult());
            this.doSwitch(inputReduceAction.getCollection());
        }
        return null;
    }

    @objid ("d55fca34-a71f-479e-b8ec-aeca03d5a8a7")
    @Override
    public Object caseRegion(Region inputRegion) {
        Object theResult = this.visitorMap.get(inputRegion);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("RegionImpl".equals(theResult.getClass().getSimpleName()))) {
            this.visitorMap.put(inputRegion, inputRegion);
            this.behavior.visitRegion(inputRegion);
            for (EObject subvertex : inputRegion.getSubvertices()) {
                this.doSwitch(subvertex);
            }
            for (EObject transition : inputRegion.getTransitions()) {
                this.doSwitch(transition);
            }
        }
        return null;
    }

    @objid ("b3c642aa-658e-4ab7-8af7-7ee1d743020f")
    @Override
    public Object caseRelationship(Relationship inputRelationship) {
        return null;
    }

    @objid ("e1156e6c-eea7-4c63-99a3-943468019af6")
    @Override
    public Object caseRemoveStructuralFeatureValueAction(RemoveStructuralFeatureValueAction inputRemoveStructuralFeatureValueAction) {
        Object theResult = this.visitorMap
                .get(inputRemoveStructuralFeatureValueAction);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("RemoveStructuralFeatureValueActionImpl".equals(theResult
                        .getClass().getSimpleName()))) {
            this.visitorMap.put(inputRemoveStructuralFeatureValueAction,
                    inputRemoveStructuralFeatureValueAction);
            this.behavior
            .visitRemoveStructuralFeatureValueAction(inputRemoveStructuralFeatureValueAction);
            this.doSwitch(inputRemoveStructuralFeatureValueAction
                    .getRemoveAt());
        }
        return null;
    }

    @objid ("0650fb76-9711-4f8f-bf5c-79971fcd03a4")
    @Override
    public Object caseRemoveVariableValueAction(RemoveVariableValueAction inputRemoveVariableValueAction) {
        Object theResult = this.visitorMap.get(inputRemoveVariableValueAction);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("RemoveVariableValueActionImpl".equals(theResult
                        .getClass().getSimpleName()))) {
            this.visitorMap.put(inputRemoveVariableValueAction,
                    inputRemoveVariableValueAction);
            this.behavior
            .visitRemoveVariableValueAction(inputRemoveVariableValueAction);
            this.doSwitch(inputRemoveVariableValueAction
                    .getRemoveAt());
        }
        return null;
    }

    @objid ("0d114447-0924-4f99-8094-666e32df2cd5")
    @Override
    public Object caseReplyAction(ReplyAction inputReplyAction) {
        Object theResult = this.visitorMap.get(inputReplyAction);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ReplyActionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputReplyAction, inputReplyAction);
            this.behavior.visitReplyAction(inputReplyAction);
            this.doSwitch(inputReplyAction.getReturnInformation());
            for (EObject replyValue : inputReplyAction.getReplyValues()) {
                this.doSwitch(replyValue);
            }
        }
        return null;
    }

    @objid ("10d1a7ff-93f2-478c-83d2-bae9b5362514")
    @Override
    public Object caseSendObjectAction(SendObjectAction inputSendObjectAction) {
        Object theResult = this.visitorMap.get(inputSendObjectAction);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("SendObjectActionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputSendObjectAction, inputSendObjectAction);
            this.behavior.visitSendObjectAction(inputSendObjectAction);
            this.doSwitch(inputSendObjectAction.getTarget());
            this.doSwitch(inputSendObjectAction.getRequest());
        }
        return null;
    }

    @objid ("15cf4d09-ec5b-45c4-9438-7cf6268f8965")
    @Override
    public Object caseSendOperationEvent(SendOperationEvent inputSendOperationEvent) {
        Object theResult = this.visitorMap.get(inputSendOperationEvent);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("SendOperationEventImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputSendOperationEvent, inputSendOperationEvent);
            this.behavior.visitSendOperationEvent(inputSendOperationEvent);
        }
        return null;
    }

    @objid ("9817ef78-510a-4f1e-84b9-334e7756f3fc")
    @Override
    public Object caseSendSignalAction(SendSignalAction inputSendSignalAction) {
        Object theResult = this.visitorMap.get(inputSendSignalAction);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("SendSignalActionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputSendSignalAction, inputSendSignalAction);
            this.behavior.visitSendSignalAction(inputSendSignalAction);
            this.doSwitch(inputSendSignalAction.getTarget());
        }
        return null;
    }

    @objid ("c013d518-6103-40d4-b81d-476a06eb07d1")
    @Override
    public Object caseSendSignalEvent(SendSignalEvent inputSendSignalEvent) {
        Object theResult = this.visitorMap.get(inputSendSignalEvent);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("SendSignalEventImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputSendSignalEvent, inputSendSignalEvent);
            this.behavior.visitSendSignalEvent(inputSendSignalEvent);
        }
        return null;
    }

    @objid ("ac9cb89d-bc70-49ef-8e1a-4a047ef04ae5")
    @Override
    public Object caseSequenceNode(SequenceNode inputSequenceNode) {
        Object theResult = this.visitorMap.get(inputSequenceNode);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("SequenceNodeImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputSequenceNode, inputSequenceNode);
            this.behavior.visitSequenceNode(inputSequenceNode);
            for (EObject executableNode : inputSequenceNode.getExecutableNodes()) {
                this.doSwitch(executableNode);
            }
        }
        return null;
    }

    @objid ("64b633aa-83c7-48fb-90b6-f75d42cf67ab")
    @Override
    public Object caseSignal(Signal inputSignal) {
        Object theResult = this.visitorMap.get(inputSignal);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("SignalImpl".equals(theResult.getClass().getSimpleName()))) {
            this.visitorMap.put(inputSignal, inputSignal);
            this.behavior.visitSignal(inputSignal);
            for (EObject ownedAttribute : inputSignal.getOwnedAttributes()) {
                this.doSwitch(ownedAttribute);
            }
        }
        return null;
    }

    @objid ("0ef9b40b-0db9-4d72-83e6-d7de98741b45")
    @Override
    public Object caseSignalEvent(SignalEvent inputSignalEvent) {
        Object theResult = this.visitorMap.get(inputSignalEvent);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("SignalEventImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputSignalEvent, inputSignalEvent);
            this.behavior.visitSignalEvent(inputSignalEvent);
        }
        return null;
    }

    @objid ("025aa44b-867a-4a57-a2c8-33596de0a15d")
    @Override
    public Object caseSlot(Slot inputSlot) {
        Object theResult = this.visitorMap.get(inputSlot);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("SlotImpl".equals(theResult.getClass().getSimpleName()))) {
            this.visitorMap.put(inputSlot, inputSlot);
            this.behavior.visitSlot(inputSlot);
            for (EObject value : inputSlot.getValues()) {
                this.doSwitch(value);
            }
        }
        return null;
    }

    @objid ("f264b72a-caea-4bb4-a0ca-07bd959c0e56")
    @Override
    public Object caseStartClassifierBehaviorAction(StartClassifierBehaviorAction inputStartClassifierBehaviorAction) {
        Object theResult = this.visitorMap.get(inputStartClassifierBehaviorAction);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("StartClassifierBehaviorActionImpl".equals(theResult
                        .getClass().getSimpleName()))) {
            this.visitorMap.put(inputStartClassifierBehaviorAction,
                    inputStartClassifierBehaviorAction);
            this.behavior
            .visitStartClassifierBehaviorAction(inputStartClassifierBehaviorAction);
            this.doSwitch(inputStartClassifierBehaviorAction
                    .getObject());
        }
        return null;
    }

    @objid ("d975ade3-06f9-4389-82b1-8f5b169b1a8d")
    @Override
    public Object caseState(State inputState) {
        Object theResult = this.visitorMap.get(inputState);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("StateImpl".equals(theResult.getClass().getSimpleName()))) {
            this.visitorMap.put(inputState, inputState);
            this.behavior.visitState(inputState);
        
            for (EObject connection : inputState.getConnections()) {
                this.doSwitch(connection);
            }
        
            for (EObject region : inputState.getRegions()) {
                this.doSwitch(region);
            }
        
            this.doSwitch(inputState.getStateInvariant());
        
            this.doSwitch(inputState.getEntry());
            this.doSwitch(inputState.getExit());
            this.doSwitch(inputState.getDoActivity());
        
            for (EObject connectionPoint : inputState.getConnectionPoints()) {
                this.doSwitch(connectionPoint);
            }
            for (EObject deferrableTrigger : inputState.getDeferrableTriggers()) {
                this.doSwitch(deferrableTrigger);
            }
        }
        return null;
    }

    @objid ("7bdebcf3-dd84-4f71-b370-38f72e22474e")
    @Override
    public Object caseStateInvariant(StateInvariant inputStateInvariant) {
        Object theResult = this.visitorMap.get(inputStateInvariant);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("StateInvariantImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputStateInvariant, inputStateInvariant);
            this.behavior.visitStateInvariant(inputStateInvariant);
            this.doSwitch(inputStateInvariant.getInvariant());
        }
        return null;
    }

    @objid ("9496b94a-b9d8-4f9e-ac7f-c7faf430e046")
    @Override
    public Object caseStateMachine(StateMachine inputStateMachine) {
        Object theResult = this.visitorMap.get(inputStateMachine);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("StateMachineImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputStateMachine, inputStateMachine);
            this.behavior.visitStateMachine(inputStateMachine);
            for (EObject region : inputStateMachine.getRegions()) {
                this.doSwitch(region);
            }
            for (EObject connectionPoint : inputStateMachine
                    .getConnectionPoints()) {
                this.doSwitch(connectionPoint);
            }
        }
        return null;
    }

    @objid ("18561e18-d375-4d57-924e-f1983c5c5a20")
    @Override
    public Object caseStereotype(Stereotype inputStereotype) {
        Object theResult = this.visitorMap.get(inputStereotype);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("StereotypeImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputStereotype, inputStereotype);
            this.behavior.visitStereotype(inputStereotype);
            for (EObject icon : inputStereotype.getIcons()) {
                this.doSwitch(icon);
            }
        }
        return null;
    }

    @objid ("cf7b669b-1916-4415-9498-dbdf32c73c1b")
    @Override
    public Object caseStringExpression(StringExpression inputStringExpression) {
        Object theResult = this.visitorMap.get(inputStringExpression);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("StringExpressionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputStringExpression, inputStringExpression);
            this.behavior.visitStringExpression(inputStringExpression);
            for (EObject subExpression : inputStringExpression
                    .getSubExpressions()) {
                this.doSwitch(subExpression);
            }
        }
        return null;
    }

    @objid ("50444a03-45cb-4de2-bd5b-03f73aba65f1")
    @Override
    public Object caseStructuralFeature(StructuralFeature inputStructuralFeature) {
        return null;
    }

    @objid ("674313d8-5727-4d75-923a-4424281a072a")
    @Override
    public Object caseStructuralFeatureAction(StructuralFeatureAction inputStructuralFeatureAction) {
        this.doSwitch(inputStructuralFeatureAction.getObject());
        return null;
    }

    @objid ("15be7b98-46f9-4378-8ec4-256fdc883082")
    @Override
    public Object caseStructuredActivityNode(StructuredActivityNode inputStructuredActivityNode) {
        Object theResult = this.visitorMap.get(inputStructuredActivityNode);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("StructuredActivityNodeImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputStructuredActivityNode,
                    inputStructuredActivityNode);
            this.behavior.visitStructuredActivityNode(inputStructuredActivityNode);
            for (EObject variable : inputStructuredActivityNode.getVariables()) {
                this.doSwitch(variable);
            }
            for (EObject node : inputStructuredActivityNode.getNodes()) {
                this.doSwitch(node);
            }
            for (EObject edge : inputStructuredActivityNode.getEdges()) {
                this.doSwitch(edge);
            }
        }
        return null;
    }

    @objid ("7ec72331-17e2-40da-bb9f-b813e76439ae")
    @Override
    public Object caseStructuredClassifier(StructuredClassifier inputStructuredClassifier) {
        for (EObject ownedAttribute : inputStructuredClassifier
                .getOwnedAttributes()) {
            this.doSwitch(ownedAttribute);
        }
        for (EObject ownedConnector : inputStructuredClassifier
                .getOwnedConnectors()) {
            this.doSwitch(ownedConnector);
        }
        return null;
    }

    @objid ("2a2205d0-90ac-46a5-b6cd-1c7e70d596b1")
    @Override
    public Object caseSubstitution(Substitution inputSubstitution) {
        Object theResult = this.visitorMap.get(inputSubstitution);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("SubstitutionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputSubstitution, inputSubstitution);
            this.behavior.visitSubstitution(inputSubstitution);
        }
        return null;
    }

    @objid ("3c96c51c-2877-4ba4-af8e-93edda663b4e")
    @Override
    public Object caseTemplateableElement(TemplateableElement inputTemplateableElement) {
        for (EObject templateBinding : inputTemplateableElement
                .getTemplateBindings()) {
            this.doSwitch(templateBinding);
        }
        this.doSwitch(inputTemplateableElement
                .getOwnedTemplateSignature());
        return null;
    }

    @objid ("ceb0829a-89af-4b9c-8596-832c56e59e73")
    @Override
    public Object caseTemplateBinding(TemplateBinding inputTemplateBinding) {
        Object theResult = this.visitorMap.get(inputTemplateBinding);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("TemplateBindingImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputTemplateBinding, inputTemplateBinding);
            this.behavior.visitTemplateBinding(inputTemplateBinding);
            for (EObject parameterSubstitution : inputTemplateBinding
                    .getParameterSubstitutions()) {
                this.doSwitch(parameterSubstitution);
            }
        }
        return null;
    }

    @objid ("1d7b913e-2ef2-450e-9a75-65a52c33a1fc")
    @Override
    public Object caseTemplateParameter(TemplateParameter inputTemplateParameter) {
        Object theResult = this.visitorMap.get(inputTemplateParameter);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("TemplateParameterImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputTemplateParameter, inputTemplateParameter);
            this.behavior.visitTemplateParameter(inputTemplateParameter);
            this.doSwitch(inputTemplateParameter
                    .getOwnedParameteredElement());
            this.doSwitch(inputTemplateParameter.getOwnedDefault());
        }
        return null;
    }

    @objid ("1b120d72-31dc-4f03-941b-6fe26e075d3f")
    @Override
    public Object caseTemplateParameterSubstitution(TemplateParameterSubstitution inputTemplateParameterSubstitution) {
        Object theResult = this.visitorMap.get(inputTemplateParameterSubstitution);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("TemplateParameterSubstitutionImpl".equals(theResult
                        .getClass().getSimpleName()))) {
            this.visitorMap.put(inputTemplateParameterSubstitution,
                    inputTemplateParameterSubstitution);
            this.behavior
            .visitTemplateParameterSubstitution(inputTemplateParameterSubstitution);
        
            this.doSwitch(inputTemplateParameterSubstitution.getOwnedActual());
        
        }
        return null;
    }

    @objid ("25fc0622-a8c2-445d-9cc7-68dc15c033ac")
    @Override
    public Object caseTemplateSignature(TemplateSignature inputTemplateSignature) {
        Object theResult = this.visitorMap.get(inputTemplateSignature);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("TemplateSignatureImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputTemplateSignature, inputTemplateSignature);
            this.behavior.visitTemplateSignature(inputTemplateSignature);
            for (EObject ownedParameter : inputTemplateSignature
                    .getOwnedParameters()) {
                this.doSwitch(ownedParameter);
            }
        }
        return null;
    }

    @objid ("11f260b2-52b7-4fbb-8417-44d20f5220ea")
    @Override
    public Object caseTestIdentityAction(TestIdentityAction inputTestIdentityAction) {
        Object theResult = this.visitorMap.get(inputTestIdentityAction);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("TestIdentityActionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputTestIdentityAction, inputTestIdentityAction);
            this.behavior.visitTestIdentityAction(inputTestIdentityAction);
            this.doSwitch(inputTestIdentityAction.getFirst());
            this.doSwitch(inputTestIdentityAction.getSecond());
            this.doSwitch(inputTestIdentityAction.getResult());
        }
        return null;
    }

    @objid ("813f80ff-75d9-4555-8e6e-b00603cbf952")
    @Override
    public Object caseTimeConstraint(TimeConstraint inputTimeConstraint) {
        Object theResult = this.visitorMap.get(inputTimeConstraint);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("TimeConstraintImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputTimeConstraint, inputTimeConstraint);
            this.behavior.visitTimeConstraint(inputTimeConstraint);
            this.doSwitch(inputTimeConstraint.getSpecification());
        }
        return null;
    }

    @objid ("9fd34e72-7f2f-47a2-aeb1-3639e1f9d879")
    @Override
    public Object caseTimeEvent(TimeEvent inputTimeEvent) {
        Object theResult = this.visitorMap.get(inputTimeEvent);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("TimeEventImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputTimeEvent, inputTimeEvent);
            this.behavior.visitTimeEvent(inputTimeEvent);
            this.doSwitch(inputTimeEvent.getWhen());
        }
        return null;
    }

    @objid ("82365276-7b4f-4985-b1f4-ab619fd95c04")
    @Override
    public Object caseTimeExpression(TimeExpression inputTimeExpression) {
        Object theResult = this.visitorMap.get(inputTimeExpression);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("TimeExpressionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputTimeExpression, inputTimeExpression);
            this.behavior.visitTimeExpression(inputTimeExpression);
        }
        return null;
    }

    @objid ("ed7bf911-69eb-41a7-b181-bba95dd4ba87")
    @Override
    public Object caseTimeInterval(TimeInterval inputTimeInterval) {
        Object theResult = this.visitorMap.get(inputTimeInterval);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("TimeIntervalImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputTimeInterval, inputTimeInterval);
            this.behavior.visitTimeInterval(inputTimeInterval);
        }
        return null;
    }

    @objid ("1653b07e-04f4-4755-ac73-1967b74aac7a")
    @Override
    public Object caseTimeObservation(final TimeObservation inputTimeObservation) {
        Object theResult = this.visitorMap.get(inputTimeObservation);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("TimeIservationImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputTimeObservation, inputTimeObservation);
            this.behavior.visitTimeObservation(inputTimeObservation);
        }
        return null;
    }

    @objid ("b9c76b29-e655-4900-973f-04aacb06506e")
    @Override
    public Object caseTransition(Transition inputTransition) {
        Object theResult = this.visitorMap.get(inputTransition);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("TransitionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputTransition, inputTransition);
            this.behavior.visitTransition(inputTransition);
            this.doSwitch(inputTransition.getGuard());
            this.doSwitch(inputTransition.getEffect());
            for (EObject trigger : inputTransition.getTriggers()) {
                this.doSwitch(trigger);
            }
        }
        return null;
    }

    @objid ("080819cb-f0ca-4780-9ddb-9e6ce30c7bcf")
    @Override
    public Object caseTrigger(Trigger inputTrigger) {
        Object theResult = this.visitorMap.get(inputTrigger);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("TriggerImpl".equals(theResult.getClass().getSimpleName()))) {
            this.visitorMap.put(inputTrigger, inputTrigger);
            this.behavior.visitTrigger(inputTrigger);
        }
        return null;
    }

    @objid ("aa5ef681-4dc5-424b-a8a2-f8d6498fbf6e")
    @Override
    public Object caseType(Type inputType) {
        return null;
    }

    @objid ("7b3d329d-f860-4a21-bec9-9a3f19d319f0")
    @Override
    public Object caseTypedElement(TypedElement inputTypedElement) {
        //        Type type = inputTypedElement.getType();
        //        if (!TotalImportMap.getInstance().containsKey(type))
        //            this.doSwitch(type);
        return null;
    }

    @objid ("b87e74e3-2abc-46b3-b836-bfaba77f2225")
    @Override
    public Object caseUnmarshallAction(UnmarshallAction inputUnmarshallAction) {
        Object theResult = this.visitorMap.get(inputUnmarshallAction);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("UnmarshallActionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputUnmarshallAction, inputUnmarshallAction);
            this.behavior.visitUnmarshallAction(inputUnmarshallAction);
            for (EObject result : inputUnmarshallAction.getResults()) {
                this.doSwitch(result);
            }
            this.doSwitch(inputUnmarshallAction.getObject());
        }
        return null;
    }

    @objid ("2b25e949-5e21-47ff-b7d7-e8654786a1e4")
    @Override
    public Object caseUsage(Usage inputUsage) {
        Object theResult = this.visitorMap.get(inputUsage);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("UsageImpl".equals(theResult.getClass().getSimpleName()))) {
            this.visitorMap.put(inputUsage, inputUsage);
            this.behavior.visitUsage(inputUsage);
        }
        return null;
    }

    @objid ("36bfa8f7-ad27-46e4-8d52-971a0beeec28")
    @Override
    public Object caseUseCase(UseCase inputUseCase) {
        Object theResult = this.visitorMap.get(inputUseCase);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("UseCaseImpl".equals(theResult.getClass().getSimpleName()))) {
            this.visitorMap.put(inputUseCase, inputUseCase);
            this.behavior.visitUseCase(inputUseCase);
            for (EObject include : inputUseCase.getIncludes()) {
                this.doSwitch(include);
            }
            for (EObject extend : inputUseCase.getExtends()) {
                this.doSwitch(extend);
            }
            for (ExtensionPoint extensionPoint : inputUseCase.getExtensionPoints()) {
                this.doSwitch(extensionPoint);
            }
        }
        return null;
    }

    @objid ("d29127ee-e7b3-49da-8d76-c7cdd00015a5")
    @Override
    public Object caseValuePin(ValuePin inputValuePin) {
        Object theResult = this.visitorMap.get(inputValuePin);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ValuePinImpl"
                        .equals(theResult.getClass().getSimpleName()))) {
            this.visitorMap.put(inputValuePin, inputValuePin);
            this.behavior.visitValuePin(inputValuePin);
            this.doSwitch(inputValuePin.getValue());
        }
        return null;
    }

    @objid ("f2abba71-4934-4a28-b3e0-efa15060925b")
    @Override
    public Object caseValueSpecification(ValueSpecification inputValueSpecification) {
        return null;
    }

    @objid ("62db7254-9599-4bfc-872a-fadbb64f64f0")
    @Override
    public Object caseValueSpecificationAction(ValueSpecificationAction inputValueSpecificationAction) {
        Object theResult = this.visitorMap.get(inputValueSpecificationAction);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ValueSpecificationActionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.visitorMap.put(inputValueSpecificationAction,
                    inputValueSpecificationAction);
            this.behavior
            .visitValueSpecificationAction(inputValueSpecificationAction);
            this.doSwitch(inputValueSpecificationAction.getValue());
            this.doSwitch(inputValueSpecificationAction.getResult());
        }
        return null;
    }

    @objid ("81baa223-0986-4804-9e96-b15b460d1084")
    @Override
    public Object caseVariable(Variable inputVariable) {
        Object theResult = this.visitorMap.get(inputVariable);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("VariableImpl"
                        .equals(theResult.getClass().getSimpleName()))) {
            this.visitorMap.put(inputVariable, inputVariable);
            this.behavior.visitVariable(inputVariable);
        }
        return null;
    }

    @objid ("fa070d71-9021-4c69-a4fe-f604ffeb8b5c")
    @Override
    public Object caseVariableAction(VariableAction inputVariableAction) {
        return null;
    }

    @objid ("1e3b31d9-63cc-43ca-b0e7-cdbef9bdce66")
    @Override
    public Object caseVertex(Vertex inputVertex) {
        return null;
    }

    @objid ("f674d839-b646-4cab-a7c8-2c1c2b847bc7")
    @Override
    public Object caseWriteLinkAction(WriteLinkAction inputWriteLinkAction) {
        return null;
    }

    @objid ("46dabffc-933f-4f02-8f6b-c830c68f7cd0")
    @Override
    public Object caseWriteStructuralFeatureAction(WriteStructuralFeatureAction inputWriteStructuralFeatureAction) {
        this.doSwitch(inputWriteStructuralFeatureAction.getValue());
        return null;
    }

    @objid ("63ae7f6e-2342-4bb7-a05d-caaeaf0b09db")
    @Override
    public Object caseWriteVariableAction(WriteVariableAction inputWriteVariableAction) {
        this.doSwitch( inputWriteVariableAction.getValue());
        return null;
    }

    @objid ("9eeabfb8-88d8-46e3-9fab-85cf9b2ae5aa")
    public XMIImportBehavior getBehavior() {
        return this.behavior;
    }

    @objid ("b5281801-30cc-4ed4-88d6-2ba48b2cff6a")
    public Map<Object, Object> getvisitorMap() {
        return this.visitorMap;
    }

    @objid ("2b3e31e0-cc75-4f42-aceb-7493b3b06091")
    @Override
    public Object caseStartObjectBehaviorAction(StartObjectBehaviorAction inputStartObjectBehaviorAction) {
        Object theResult = this.visitorMap.get(inputStartObjectBehaviorAction);
        // If this concrete element is inherited by another concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("StartObjectBehaviorActionImpl".equals(theResult
                        .getClass().getSimpleName()))) {
            this.visitorMap.put(inputStartObjectBehaviorAction,
                    inputStartObjectBehaviorAction);
            this.behavior
            .visitStartObjectBehaviorAction(inputStartObjectBehaviorAction);
            this.doSwitch(inputStartObjectBehaviorAction
                    .getObject());
        }
        return null;
    }

}
