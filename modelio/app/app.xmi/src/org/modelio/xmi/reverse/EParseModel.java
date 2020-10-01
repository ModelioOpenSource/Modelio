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
import org.eclipse.uml2.uml.DurationObservation;
import org.eclipse.uml2.uml.ExceptionHandler;
import org.eclipse.uml2.uml.FinalNode;
import org.eclipse.uml2.uml.FinalState;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.InstanceValue;
import org.eclipse.uml2.uml.ObjectFlow;
import org.eclipse.uml2.uml.ObjectNode;
import org.eclipse.uml2.uml.Observation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.StringExpression;
import org.eclipse.uml2.uml.TimeObservation;
import org.eclipse.uml2.uml.util.UMLSwitch;

@objid ("a6d05e08-c07b-4091-952d-36ae01708143")
public class EParseModel extends UMLSwitch<Object> {
    @objid ("f0118f9e-f89d-4e92-9b5e-96f8300b9594")
    private XMIImportBehavior behavior;

    @objid ("a6546e08-19db-4d82-b00d-d225e82d89c6")
    private Map<Object, Object> visitorMap;

    @objid ("e14b41d9-cb7f-4a91-aa4b-fec920ff805c")
    public EParseModel(XMIImportBehavior behavior, Package ecoreRootModel) {
        this.behavior = behavior;
        this.visitorMap = new HashMap<>();
        this.visitorMap.put(ecoreRootModel, ecoreRootModel);
    }

    @objid ("9567c8a5-a176-49f9-8128-36ddf784ad30")
    @Override
    public Object caseAbstraction(org.eclipse.uml2.uml.Abstraction inputAbstraction) {
        Object theResult = this.visitorMap.get(inputAbstraction);
        if (theResult == null) {
            this.visitorMap.put(inputAbstraction, inputAbstraction);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("AbstractionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitAbstraction(inputAbstraction);
            theResult = super.caseAbstraction(inputAbstraction);
            this.doSwitch(inputAbstraction.getMapping());
        }
        return theResult;
    }

    @objid ("292b767d-d406-435d-a9d1-6f5592cf6462")
    @Override
    public Object caseAcceptCallAction(org.eclipse.uml2.uml.AcceptCallAction inputAcceptCallAction) {
        Object theResult = this.visitorMap.get(inputAcceptCallAction);
        if (theResult == null) {
            this.visitorMap.put(inputAcceptCallAction, inputAcceptCallAction);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("AcceptCallActionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitAcceptCallAction(inputAcceptCallAction);
            theResult = super.caseAcceptCallAction(inputAcceptCallAction);
            this.doSwitch(inputAcceptCallAction
                    .getReturnInformation());
        }
        return theResult;
    }

    @objid ("83180d4e-5c74-4346-859d-2f7ed2bf3dd0")
    @Override
    public Object caseAcceptEventAction(org.eclipse.uml2.uml.AcceptEventAction inputAcceptEventAction) {
        Object theResult = this.visitorMap.get(inputAcceptEventAction);
        if (theResult == null) {
            this.visitorMap.put(inputAcceptEventAction, inputAcceptEventAction);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("AcceptEventActionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitAcceptEventAction(inputAcceptEventAction);
            theResult = super.caseAcceptEventAction(inputAcceptEventAction);
            for (EObject result : inputAcceptEventAction.getResults()) {
                this.doSwitch(result);
            }
            for (EObject trigger : inputAcceptEventAction.getTriggers()) {
                this.doSwitch(trigger);
            }
        }
        return theResult;
    }

    @objid ("4d946267-b71b-4254-846d-5d8f5f292603")
    @Override
    public Object caseAction(org.eclipse.uml2.uml.Action inputAction) {
        Object theResult = super.caseAction(inputAction);
        for (EObject output : inputAction.getOutputs()) {
            this.doSwitch(output);
        }
        for (EObject input : inputAction.getInputs()) {
            this.doSwitch(input);
        }
        this.doSwitch(inputAction.getContext());
        for (EObject localPrecondition : inputAction.getLocalPreconditions()) {
            this.doSwitch(localPrecondition);
        }
        for (EObject localPostcondition : inputAction.getLocalPostconditions()) {
            this.doSwitch(localPostcondition);
        }
        return theResult;
    }

    @objid ("af75c301-80f3-4e74-95ee-7331898bccc1")
    @Override
    public Object caseActionExecutionSpecification(org.eclipse.uml2.uml.ActionExecutionSpecification inputActionExecutionSpecification) {
        Object theResult = this.visitorMap.get(inputActionExecutionSpecification);
        if (theResult == null) {
            this.visitorMap.put(inputActionExecutionSpecification,
                    inputActionExecutionSpecification);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ActionExecutionSpecificationImpl".equals(theResult
                        .getClass().getSimpleName()))) {
            this.behavior.visitActionExecutionSpecification(inputActionExecutionSpecification);
            theResult = super
            .caseActionExecutionSpecification(inputActionExecutionSpecification);
            this.doSwitch(inputActionExecutionSpecification
                    .getAction());
        }
        return theResult;
    }

    @objid ("eff0aa5f-1c21-4955-9da9-542426882ad5")
    @Override
    public Object caseActionInputPin(org.eclipse.uml2.uml.ActionInputPin inputActionInputPin) {
        Object theResult = this.visitorMap.get(inputActionInputPin);
        if (theResult == null) {
            this.visitorMap.put(inputActionInputPin, inputActionInputPin);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ActionInputPinImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitActionInputPin(inputActionInputPin);
            theResult = super.caseActionInputPin(inputActionInputPin);
            this.doSwitch(inputActionInputPin.getFromAction());
        }
        return theResult;
    }

    @objid ("adbff075-ff4a-4ed5-a4c2-84949406db26")
    @Override
    public Object caseActivity(org.eclipse.uml2.uml.Activity inputActivity) {
        Object theResult = this.visitorMap.get(inputActivity);
        if (theResult == null) {
            this.visitorMap.put(inputActivity, inputActivity);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ActivityImpl"
                        .equals(theResult.getClass().getSimpleName()))) {
            this.behavior.visitActivity(inputActivity);
            theResult = super.caseActivity(inputActivity);
            for (EObject structuredNode : inputActivity.getStructuredNodes()) {
                this.doSwitch(structuredNode);
            }
            for (EObject variable : inputActivity.getVariables()) {
                this.doSwitch(variable);
            }
            for (EObject node : inputActivity.getNodes()) {
                this.doSwitch(node);
            }
            for (EObject edge : inputActivity.getEdges()) {
                this.doSwitch(edge);
            }
            for (EObject partition : inputActivity.getPartitions()) {
                this.doSwitch(partition);
            }
            for (EObject group : inputActivity.getGroups()) {
                this.doSwitch(group);
            }
        }
        return theResult;
    }

    @objid ("eed20d0b-8402-46b2-9048-9a1343b9cebf")
    @Override
    public Object caseActivityEdge(org.eclipse.uml2.uml.ActivityEdge inputActivityEdge) {
        Object theResult = super.caseActivityEdge(inputActivityEdge);
        this.doSwitch(inputActivityEdge.getActivity());
        this.doSwitch(inputActivityEdge.getSource());
        this.doSwitch(inputActivityEdge.getTarget());
        for (EObject redefinedEdge : inputActivityEdge.getRedefinedEdges()) {
            this.doSwitch(redefinedEdge);
        }
        for (EObject inPartition : inputActivityEdge.getInPartitions()) {
            this.doSwitch(inPartition);
        }
        this.doSwitch(inputActivityEdge.getGuard());
        this.doSwitch(inputActivityEdge.getWeight());
        this.doSwitch(inputActivityEdge.getInterrupts());
        this.doSwitch(inputActivityEdge.getInStructuredNode());
        for (EObject inGroup : inputActivityEdge.getInGroups()) {
            this.doSwitch(inGroup);
        }
        return theResult;
    }

    @objid ("142755d1-6df0-4d12-b3fe-f91190f5df9a")
    @Override
    public Object caseActivityFinalNode(org.eclipse.uml2.uml.ActivityFinalNode inputActivityFinalNode) {
        Object theResult = this.visitorMap.get(inputActivityFinalNode);
        if (theResult == null) {
            this.visitorMap.put(inputActivityFinalNode, inputActivityFinalNode);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ActivityFinalNodeImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitActivityFinalNode(inputActivityFinalNode);
            theResult = super.caseActivityFinalNode(inputActivityFinalNode);
        }
        return theResult;
    }

    @objid ("7f2108a9-d3d3-4116-ac13-29b39379707c")
    @Override
    public Object caseActivityGroup(org.eclipse.uml2.uml.ActivityGroup inputActivityGroup) {
        Object theResult = super.caseActivityGroup(inputActivityGroup);
        for (EObject subgroup : inputActivityGroup.getSubgroups()) {
            this.doSwitch(subgroup);
        }
        this.doSwitch(inputActivityGroup.getSuperGroup());
        this.doSwitch(inputActivityGroup.getInActivity());
        for (EObject containedNode : inputActivityGroup.getContainedNodes()) {
            this.doSwitch(containedNode);
        }
        for (EObject containedEdge : inputActivityGroup.getContainedEdges()) {
            this.doSwitch(containedEdge);
        }
        return theResult;
    }

    @objid ("7c378761-7e89-424b-ace4-279812650334")
    @Override
    public Object caseActivityNode(org.eclipse.uml2.uml.ActivityNode inputActivityNode) {
        Object theResult = super.caseActivityNode(inputActivityNode);
        this.doSwitch(inputActivityNode.getInStructuredNode());
        this.doSwitch(inputActivityNode.getActivity());
        for (EObject outgoing : inputActivityNode.getOutgoings()) {
            this.doSwitch(outgoing);
        }
        for (EObject incoming : inputActivityNode.getIncomings()) {
            this.doSwitch(incoming);
        }
        for (EObject redefinedNode : inputActivityNode.getRedefinedNodes()) {
            this.doSwitch(redefinedNode);
        }
        for (EObject inPartition : inputActivityNode.getInPartitions()) {
            this.doSwitch(inPartition);
        }
        for (EObject inInterruptibleRegion : inputActivityNode
                .getInInterruptibleRegions()) {
            this.doSwitch(inInterruptibleRegion);
        }
        for (EObject inGroup : inputActivityNode.getInGroups()) {
            this.doSwitch(inGroup);
        }
        return theResult;
    }

    @objid ("b54dfc78-3c91-4f0c-a194-c11ce3fe79cd")
    @Override
    public Object caseActivityParameterNode(org.eclipse.uml2.uml.ActivityParameterNode inputActivityParameterNode) {
        Object theResult = this.visitorMap.get(inputActivityParameterNode);
        if (theResult == null) {
            this.visitorMap.put(inputActivityParameterNode,
                    inputActivityParameterNode);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ActivityParameterNodeImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitActivityParameterNode(inputActivityParameterNode);
            theResult = super
            .caseActivityParameterNode(inputActivityParameterNode);
            this.doSwitch(inputActivityParameterNode.getParameter());
        }
        return theResult;
    }

    @objid ("836d3190-da54-43b4-ac01-ec56eea6fe2a")
    @Override
    public Object caseActivityPartition(org.eclipse.uml2.uml.ActivityPartition inputActivityPartition) {
        Object theResult = this.visitorMap.get(inputActivityPartition);
        if (theResult == null) {
            this.visitorMap.put(inputActivityPartition, inputActivityPartition);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ActivityPartitionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitActivityPartition(inputActivityPartition);
            theResult = super.caseActivityPartition(inputActivityPartition);
            for (EObject edge : inputActivityPartition.getEdges()) {
                this.doSwitch(edge);
            }
            for (EObject node : inputActivityPartition.getNodes()) {
                this.doSwitch(node);
            }
            for (EObject subpartition : inputActivityPartition
                    .getSubpartitions()) {
                this.doSwitch(subpartition);
            }
            this.doSwitch(inputActivityPartition.getSuperPartition());
            this.doSwitch(inputActivityPartition.getRepresents());
        }
        return theResult;
    }

    @objid ("453d9f3f-599e-48dc-bd47-b56c3039bae1")
    @Override
    public Object caseActor(org.eclipse.uml2.uml.Actor inputActor) {
        Object theResult = this.visitorMap.get(inputActor);
        if (theResult == null) {
            this.visitorMap.put(inputActor, inputActor);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ActorImpl".equals(theResult.getClass().getSimpleName()))) {
            this.behavior.visitActor(inputActor);
            theResult = super.caseActor(inputActor);
        }
        return theResult;
    }

    @objid ("3c70d902-57b1-460a-b972-1569aeff0d4c")
    @Override
    public Object caseAddStructuralFeatureValueAction(org.eclipse.uml2.uml.AddStructuralFeatureValueAction inputAddStructuralFeatureValueAction) {
        Object theResult = this.visitorMap.get(inputAddStructuralFeatureValueAction);
        if (theResult == null) {
            this.visitorMap.put(inputAddStructuralFeatureValueAction,
                    inputAddStructuralFeatureValueAction);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("AddStructuralFeatureValueActionImpl".equals(theResult
                        .getClass().getSimpleName()))) {
            this.behavior
            .visitAddStructuralFeatureValueAction(inputAddStructuralFeatureValueAction);
            theResult = super
            .caseAddStructuralFeatureValueAction(inputAddStructuralFeatureValueAction);
            this.doSwitch(inputAddStructuralFeatureValueAction
                    .getInsertAt());
        }
        return theResult;
    }

    @objid ("21755bc5-f6a2-4644-b496-e0172ccd737b")
    @Override
    public Object caseAddVariableValueAction(org.eclipse.uml2.uml.AddVariableValueAction inputAddVariableValueAction) {
        Object theResult = this.visitorMap.get(inputAddVariableValueAction);
        if (theResult == null) {
            this.visitorMap.put(inputAddVariableValueAction,
                    inputAddVariableValueAction);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("AddVariableValueActionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitAddVariableValueAction(inputAddVariableValueAction);
            theResult = super
            .caseAddVariableValueAction(inputAddVariableValueAction);
            this.doSwitch(inputAddVariableValueAction.getInsertAt());
        }
        return theResult;
    }

    @objid ("22fe4209-4ab7-4d47-965e-e773bc2cf0ae")
    @Override
    public Object caseAnyReceiveEvent(org.eclipse.uml2.uml.AnyReceiveEvent inputAnyReceiveEvent) {
        Object theResult = this.visitorMap.get(inputAnyReceiveEvent);
        if (theResult == null) {
            this.visitorMap.put(inputAnyReceiveEvent, inputAnyReceiveEvent);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("AnyReceiveEventImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitAnyReceiveEvent(inputAnyReceiveEvent);
            theResult = super.caseAnyReceiveEvent(inputAnyReceiveEvent);
        }
        return theResult;
    }

    @objid ("fa0aff75-4314-49f2-ac8d-d9108525883e")
    @Override
    public Object caseArtifact(org.eclipse.uml2.uml.Artifact inputArtifact) {
        Object theResult = this.visitorMap.get(inputArtifact);
        if (theResult == null) {
            this.visitorMap.put(inputArtifact, inputArtifact);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ArtifactImpl"
                        .equals(theResult.getClass().getSimpleName()))) {
            this.behavior.visitArtifact(inputArtifact);
            theResult = super.caseArtifact(inputArtifact);
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
        return theResult;
    }

    @objid ("691b0a4a-e97a-44bc-a57a-fa43e2b29193")
    @Override
    public Object caseAssociation(org.eclipse.uml2.uml.Association inputAssociation) {
        Object theResult = this.visitorMap.get(inputAssociation);
        if (theResult == null) {
            this.visitorMap.put(inputAssociation, inputAssociation);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("AssociationImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitAssociation(inputAssociation);
            theResult = super.caseAssociation(inputAssociation);
            for (EObject ownedEnd : inputAssociation.getOwnedEnds()) {
                this.doSwitch(ownedEnd);
            }
            for (EObject endType : inputAssociation.getEndTypes()) {
                this.doSwitch(endType);
            }
            for (EObject memberEnd : inputAssociation.getMemberEnds()) {
                this.doSwitch(memberEnd);
            }
            for (EObject navigableOwnedEnd : inputAssociation
                    .getNavigableOwnedEnds()) {
                this.doSwitch(navigableOwnedEnd);
            }
        }
        return theResult;
    }

    @objid ("a33cf265-d83a-4421-9587-3cf397e72354")
    @Override
    public Object caseAssociationClass(org.eclipse.uml2.uml.AssociationClass inputAssociationClass) {
        Object theResult = this.visitorMap.get(inputAssociationClass);
        if (theResult == null) {
            this.visitorMap.put(inputAssociationClass, inputAssociationClass);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("AssociationClassImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitAssociationClass(inputAssociationClass);
            theResult = super.caseAssociationClass(inputAssociationClass);
        }
        return theResult;
    }

    @objid ("0bf9147d-c5f2-4e4d-bcf6-f222ee1e0bc3")
    @Override
    public Object caseBehavior(org.eclipse.uml2.uml.Behavior inputBehavior) {
        Object theResult = super.caseBehavior(inputBehavior);
        for (EObject redefinedBehavior : inputBehavior.getRedefinedBehaviors()) {
            this.doSwitch(redefinedBehavior);
        }
        this.doSwitch(inputBehavior.getSpecification());
        for (EObject ownedParameter : inputBehavior.getOwnedParameters()) {
            this.doSwitch(ownedParameter);
        }
        this.doSwitch(inputBehavior.getContext());
        for (EObject precondition : inputBehavior.getPreconditions()) {
            this.doSwitch(precondition);
        }
        for (EObject postcondition : inputBehavior.getPostconditions()) {
            this.doSwitch(postcondition);
        }
        for (EObject ownedParameterSet : inputBehavior.getOwnedParameterSets()) {
            this.doSwitch(ownedParameterSet);
        }
        return theResult;
    }

    @objid ("9fe8dc3f-e6c1-429f-9d12-9008d53ecc4f")
    @Override
    public Object caseBehaviorExecutionSpecification(org.eclipse.uml2.uml.BehaviorExecutionSpecification inputBehaviorExecutionSpecification) {
        Object theResult = this.visitorMap.get(inputBehaviorExecutionSpecification);
        if (theResult == null) {
            this.visitorMap.put(inputBehaviorExecutionSpecification,
                    inputBehaviorExecutionSpecification);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("BehaviorExecutionSpecificationImpl".equals(theResult
                        .getClass().getSimpleName()))) {
            this.behavior
            .visitBehaviorExecutionSpecification(inputBehaviorExecutionSpecification);
            theResult = super
            .caseBehaviorExecutionSpecification(inputBehaviorExecutionSpecification);
            this.doSwitch(inputBehaviorExecutionSpecification
                    .getBehavior());
        }
        return theResult;
    }

    @objid ("64a584dc-5c71-495c-994e-f8cc4c09531d")
    @Override
    public Object caseBehavioralFeature(org.eclipse.uml2.uml.BehavioralFeature inputBehavioralFeature) {
        Object theResult = super.caseBehavioralFeature(inputBehavioralFeature);
        for (EObject ownedParameter : inputBehavioralFeature
                .getOwnedParameters()) {
            this.doSwitch(ownedParameter);
        }
        for (EObject method : inputBehavioralFeature.getMethods()) {
            this.doSwitch(method);
        }
        for (EObject raisedException : inputBehavioralFeature
                .getRaisedExceptions()) {
            this.doSwitch(raisedException);
        }
        for (EObject ownedParameterSet : inputBehavioralFeature
                .getOwnedParameterSets()) {
            this.doSwitch(ownedParameterSet);
        }
        return theResult;
    }

    @objid ("5ac4d54b-0883-4dd8-beb5-63e4acb3a65a")
    @Override
    public Object caseBehavioredClassifier(org.eclipse.uml2.uml.BehavioredClassifier inputBehavioredClassifier) {
        Object theResult = super
        .caseBehavioredClassifier(inputBehavioredClassifier);
        for (EObject ownedBehavior : inputBehavioredClassifier
                .getOwnedBehaviors()) {
            this.doSwitch(ownedBehavior);
        }
        this.doSwitch(inputBehavioredClassifier
                .getClassifierBehavior());
        for (EObject interfaceRealization : inputBehavioredClassifier
                .getInterfaceRealizations()) {
            this.doSwitch(interfaceRealization);
        }
        //        for (EObject ownedTrigger : inputBehavioredClassifier.getOwnedTriggers()) {
        //            this.doSwitch(ownedTrigger);
        //        }
        return theResult;
    }

    @objid ("940dc886-5b19-4a5b-ab38-701750d2d1bc")
    @Override
    public Object caseBroadcastSignalAction(org.eclipse.uml2.uml.BroadcastSignalAction inputBroadcastSignalAction) {
        Object theResult = this.visitorMap.get(inputBroadcastSignalAction);
        if (theResult == null) {
            this.visitorMap.put(inputBroadcastSignalAction,
                    inputBroadcastSignalAction);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("BroadcastSignalActionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitBroadcastSignalAction(inputBroadcastSignalAction);
            theResult = super
            .caseBroadcastSignalAction(inputBroadcastSignalAction);
            this.doSwitch(inputBroadcastSignalAction.getSignal());
        }
        return theResult;
    }

    @objid ("35c5cfc9-d204-4ae9-aba1-e7fbbf56f0af")
    @Override
    public Object caseCallAction(org.eclipse.uml2.uml.CallAction inputCallAction) {
        Object theResult = super.caseCallAction(inputCallAction);
        for (EObject result : inputCallAction.getResults()) {
            this.doSwitch(result);
        }
        return theResult;
    }

    @objid ("6cf64002-efa4-47f9-a887-d55e46d27d10")
    @Override
    public Object caseCallBehaviorAction(org.eclipse.uml2.uml.CallBehaviorAction inputCallBehaviorAction) {
        Object theResult = this.visitorMap.get(inputCallBehaviorAction);
        if (theResult == null) {
            this.visitorMap.put(inputCallBehaviorAction, inputCallBehaviorAction);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("CallBehaviorActionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitCallBehaviorAction(inputCallBehaviorAction);
            theResult = super.caseCallBehaviorAction(inputCallBehaviorAction);
            this.doSwitch(inputCallBehaviorAction.getBehavior());
        }
        return theResult;
    }

    @objid ("4a6cd5f9-a7ab-4ca4-abb0-27269c788f50")
    @Override
    public Object caseCallEvent(org.eclipse.uml2.uml.CallEvent inputCallEvent) {
        Object theResult = this.visitorMap.get(inputCallEvent);
        if (theResult == null) {
            this.visitorMap.put(inputCallEvent, inputCallEvent);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("CallEventImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitCallEvent(inputCallEvent);
            theResult = super.caseCallEvent(inputCallEvent);
            this.doSwitch(inputCallEvent.getOperation());
        }
        return theResult;
    }

    @objid ("5a34fc3a-b268-49c9-95ab-b1fd2f56e611")
    @Override
    public Object caseCallOperationAction(org.eclipse.uml2.uml.CallOperationAction inputCallOperationAction) {
        Object theResult = this.visitorMap.get(inputCallOperationAction);
        if (theResult == null) {
            this.visitorMap.put(inputCallOperationAction, inputCallOperationAction);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("CallOperationActionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitCallOperationAction(inputCallOperationAction);
            theResult = super.caseCallOperationAction(inputCallOperationAction);
            this.doSwitch(inputCallOperationAction.getOperation());
            this.doSwitch(inputCallOperationAction.getTarget());
        }
        return theResult;
    }

    @objid ("5fe0fc80-6fc4-4cc2-af08-678289d14f5d")
    @Override
    public Object caseCentralBufferNode(org.eclipse.uml2.uml.CentralBufferNode inputCentralBufferNode) {
        Object theResult = this.visitorMap.get(inputCentralBufferNode);
        if (theResult == null) {
            this.visitorMap.put(inputCentralBufferNode, inputCentralBufferNode);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("CentralBufferNodeImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitCentralBufferNode(inputCentralBufferNode);
            theResult = super.caseCentralBufferNode(inputCentralBufferNode);
        }
        return theResult;
    }

    @objid ("aecdfde9-6dcc-4468-b2ec-1876f1aabb65")
    @Override
    public Object caseChangeEvent(org.eclipse.uml2.uml.ChangeEvent inputChangeEvent) {
        Object theResult = this.visitorMap.get(inputChangeEvent);
        if (theResult == null) {
            this.visitorMap.put(inputChangeEvent, inputChangeEvent);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ChangeEventImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitChangeEvent(inputChangeEvent);
            theResult = super.caseChangeEvent(inputChangeEvent);
            this.doSwitch(inputChangeEvent.getChangeExpression());
        }
        return theResult;
    }

    @objid ("fdefee9d-3d89-41f2-ac2a-7042a99df61e")
    @Override
    public Object caseClass(org.eclipse.uml2.uml.Class inputClass) {
        Object theResult = this.visitorMap.get(inputClass);
        if (theResult == null) {
            this.visitorMap.put(inputClass, inputClass);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ClassImpl".equals(theResult.getClass().getSimpleName()))) {
            this.behavior.visitClass(inputClass);
            theResult = super.caseClass(inputClass);
            for (EObject nestedClassifier : inputClass.getNestedClassifiers()) {
                this.doSwitch(nestedClassifier);
            }
            for (EObject ownedAttribute : inputClass.getOwnedAttributes()) {
                this.doSwitch(ownedAttribute);
            }
            for (EObject ownedOperation : inputClass.getOwnedOperations()) {
                this.doSwitch(ownedOperation);
            }
            for (EObject superClass : inputClass.getSuperClasses()) {
                this.doSwitch(superClass);
            }
            for (EObject ownedReception : inputClass.getOwnedReceptions()) {
                this.doSwitch(ownedReception);
            }
            for (EObject extension : inputClass.getExtensions()) {
                this.doSwitch(extension);
            }
        }
        return theResult;
    }

    @objid ("9225ccb6-c334-4f4e-9d23-ee3d8d9ae1dd")
    @Override
    public Object caseClassifier(org.eclipse.uml2.uml.Classifier inputClassifier) {
        Object theResult = super.caseClassifier(inputClassifier);
        for (EObject generalization : inputClassifier.getGeneralizations()) {
            this.doSwitch(generalization);
        }
        for (EObject feature : inputClassifier.getFeatures()) {
            this.doSwitch(feature);
        }
        for (EObject inheritedMember : inputClassifier.getInheritedMembers()) {
            this.doSwitch(inheritedMember);
        }
        for (EObject redefinedClassifier : inputClassifier
                .getRedefinedClassifiers()) {
            this.doSwitch(redefinedClassifier);
        }
        for (EObject general : inputClassifier.getGenerals()) {
            this.doSwitch(general);
        }
        for (EObject substitution : inputClassifier.getSubstitutions()) {
            this.doSwitch(substitution);
        }
        for (EObject attribute : inputClassifier.getAttributes()) {
            this.doSwitch(attribute);
        }
        this.doSwitch(inputClassifier.getRepresentation());
        for (EObject collaborationUse : inputClassifier.getCollaborationUses()) {
            this.doSwitch(collaborationUse);
        }
        for (EObject ownedUseCase : inputClassifier.getOwnedUseCases()) {
            this.doSwitch(ownedUseCase);
        }
        for (EObject useCase : inputClassifier.getUseCases()) {
            this.doSwitch(useCase);
        }
        for (EObject powertypeExtent : inputClassifier.getPowertypeExtents()) {
            this.doSwitch(powertypeExtent);
        }
        this.doSwitch(inputClassifier.getOwnedTemplateSignature());
        this.doSwitch(inputClassifier.getTemplateParameter());
        return theResult;
    }

    @objid ("8f3de447-2c92-49a1-9f8c-d610b99f1e9f")
    @Override
    public Object caseClassifierTemplateParameter(org.eclipse.uml2.uml.ClassifierTemplateParameter inputClassifierTemplateParameter) {
        Object theResult = this.visitorMap.get(inputClassifierTemplateParameter);
        if (theResult == null) {
            this.visitorMap.put(inputClassifierTemplateParameter,
                    inputClassifierTemplateParameter);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ClassifierTemplateParameterImpl".equals(theResult
                        .getClass().getSimpleName()))) {
            this.behavior
            .visitClassifierTemplateParameter(inputClassifierTemplateParameter);
            theResult = super
            .caseClassifierTemplateParameter(inputClassifierTemplateParameter);
            this.doSwitch(inputClassifierTemplateParameter
                    .getParameteredElement());
            this.doSwitch(inputClassifierTemplateParameter
                    .getDefault());
        
            for (EObject constrainingClassifier : inputClassifierTemplateParameter
                    .getConstrainingClassifiers()) {
                this.doSwitch(constrainingClassifier);
            }
        }
        return theResult;
    }

    @objid ("e9934b8a-4d28-4ffd-ad6d-6a2f2cdd9065")
    @Override
    public Object caseClause(org.eclipse.uml2.uml.Clause inputClause) {
        Object theResult = this.visitorMap.get(inputClause);
        if (theResult == null) {
            this.visitorMap.put(inputClause, inputClause);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ClauseImpl".equals(theResult.getClass().getSimpleName()))) {
            this.behavior.visitClause(inputClause);
            theResult = super.caseClause(inputClause);
            for (EObject test : inputClause.getTests()) {
                this.doSwitch(test);
            }
            for (EObject body : inputClause.getBodies()) {
                this.doSwitch(body);
            }
            for (EObject predecessorClause : inputClause.getPredecessorClauses()) {
                this.doSwitch(predecessorClause);
            }
            for (EObject successorClause : inputClause.getSuccessorClauses()) {
                this.doSwitch(successorClause);
            }
            this.doSwitch(inputClause.getDecider());
            for (EObject bodyOutput : inputClause.getBodyOutputs()) {
                this.doSwitch(bodyOutput);
            }
        }
        return theResult;
    }

    @objid ("cbd8c12f-70e8-4e9e-8b55-84d7801dc156")
    @Override
    public Object caseClearAssociationAction(org.eclipse.uml2.uml.ClearAssociationAction inputClearAssociationAction) {
        Object theResult = this.visitorMap.get(inputClearAssociationAction);
        if (theResult == null) {
            this.visitorMap.put(inputClearAssociationAction,
                    inputClearAssociationAction);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ClearAssociationActionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitClearAssociationAction(inputClearAssociationAction);
            theResult = super
            .caseClearAssociationAction(inputClearAssociationAction);
            this.doSwitch(inputClearAssociationAction.getObject());
            this.doSwitch(inputClearAssociationAction
                    .getAssociation());
        }
        return theResult;
    }

    @objid ("ee8ab5aa-a5a0-494a-94db-ad5e113d02d7")
    @Override
    public Object caseClearStructuralFeatureAction(org.eclipse.uml2.uml.ClearStructuralFeatureAction inputClearStructuralFeatureAction) {
        Object theResult = this.visitorMap.get(inputClearStructuralFeatureAction);
        if (theResult == null) {
            this.visitorMap.put(inputClearStructuralFeatureAction,
                    inputClearStructuralFeatureAction);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ClearStructuralFeatureActionImpl".equals(theResult
                        .getClass().getSimpleName()))) {
            this.behavior
            .visitClearStructuralFeatureAction(inputClearStructuralFeatureAction);
            theResult = super
            .caseClearStructuralFeatureAction(inputClearStructuralFeatureAction);
        }
        return theResult;
    }

    @objid ("df42f0cf-5ddb-4a1f-9887-f5be7c4eb605")
    @Override
    public Object caseClearVariableAction(org.eclipse.uml2.uml.ClearVariableAction inputClearVariableAction) {
        Object theResult = this.visitorMap.get(inputClearVariableAction);
        if (theResult == null) {
            this.visitorMap.put(inputClearVariableAction, inputClearVariableAction);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ClearVariableActionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitClearVariableAction(inputClearVariableAction);
            theResult = super.caseClearVariableAction(inputClearVariableAction);
        }
        return theResult;
    }

    @objid ("7017f6fb-09a0-46a1-ae6e-7abbe2fc2002")
    @Override
    public Object caseCollaboration(org.eclipse.uml2.uml.Collaboration inputCollaboration) {
        Object theResult = this.visitorMap.get(inputCollaboration);
        if (theResult == null) {
            this.visitorMap.put(inputCollaboration, inputCollaboration);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("CollaborationImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitCollaboration(inputCollaboration);
            theResult = super.caseCollaboration(inputCollaboration);
            for (EObject collaborationRole : inputCollaboration
                    .getCollaborationRoles()) {
                this.doSwitch(collaborationRole);
            }
        }
        return theResult;
    }

    @objid ("f13be56b-1922-44b2-bf59-f268f0bed03d")
    @Override
    public Object caseCollaborationUse(org.eclipse.uml2.uml.CollaborationUse inputCollaborationUse) {
        Object theResult = this.visitorMap.get(inputCollaborationUse);
        if (theResult == null) {
            this.visitorMap.put(inputCollaborationUse, inputCollaborationUse);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("CollaborationUseImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitCollaborationUse(inputCollaborationUse);
            theResult = super.caseCollaborationUse(inputCollaborationUse);
            this.doSwitch(inputCollaborationUse.getType());
            for (EObject roleBinding : inputCollaborationUse.getRoleBindings()) {
                this.doSwitch(roleBinding);
            }
        }
        return theResult;
    }

    @objid ("70b2b1e0-0cf8-4121-85bd-92642af186e0")
    @Override
    public Object caseCombinedFragment(org.eclipse.uml2.uml.CombinedFragment inputCombinedFragment) {
        Object theResult = this.visitorMap.get(inputCombinedFragment);
        if (theResult == null) {
            this.visitorMap.put(inputCombinedFragment, inputCombinedFragment);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("CombinedFragmentImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitCombinedFragment(inputCombinedFragment);
            theResult = super.caseCombinedFragment(inputCombinedFragment);
            for (EObject operand : inputCombinedFragment.getOperands()) {
                this.doSwitch(operand);
            }
            for (EObject cfragmentGate : inputCombinedFragment
                    .getCfragmentGates()) {
                this.doSwitch(cfragmentGate);
            }
        }
        return theResult;
    }

    @objid ("1e73a1a2-a361-4b33-bb1d-834894f18e03")
    @Override
    public Object caseComment(org.eclipse.uml2.uml.Comment inputComment) {
        Object theResult = this.visitorMap.get(inputComment);
        if (theResult == null) {
            this.visitorMap.put(inputComment, inputComment);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("CommentImpl".equals(theResult.getClass().getSimpleName()))) {
            this.behavior.visitComment(inputComment);
            theResult = super.caseComment(inputComment);
            for (EObject annotatedElement : inputComment.getAnnotatedElements()) {
                this.doSwitch(annotatedElement);
            }
        }
        return theResult;
    }

    @objid ("e9c04e92-d3e9-445e-963a-246e7c633a5b")
    @Override
    public Object caseCommunicationPath(org.eclipse.uml2.uml.CommunicationPath inputCommunicationPath) {
        Object theResult = this.visitorMap.get(inputCommunicationPath);
        if (theResult == null) {
            this.visitorMap.put(inputCommunicationPath, inputCommunicationPath);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("CommunicationPathImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitCommunicationPath(inputCommunicationPath);
            theResult = super.caseCommunicationPath(inputCommunicationPath);
        }
        return theResult;
    }

    @objid ("dc4e50e7-2bb4-4e68-904e-f9211530b305")
    @Override
    public Object caseComponent(org.eclipse.uml2.uml.Component inputComponent) {
        Object theResult = this.visitorMap.get(inputComponent);
        if (theResult == null) {
            this.visitorMap.put(inputComponent, inputComponent);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ComponentImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitComponent(inputComponent);
            theResult = super.caseComponent(inputComponent);
            for (EObject required : inputComponent.getRequireds()) {
                this.doSwitch(required);
            }
            for (EObject provided : inputComponent.getProvideds()) {
                this.doSwitch(provided);
            }
            for (EObject realization : inputComponent.getRealizations()) {
                this.doSwitch(realization);
            }
            for (EObject packagedElement : inputComponent.getPackagedElements()) {
                this.doSwitch(packagedElement);
            }
        }
        return theResult;
    }

    @objid ("d700efda-9d18-4b27-b603-3a7c3c702cd9")
    @Override
    public Object caseComponentRealization(org.eclipse.uml2.uml.ComponentRealization inputComponentRealization) {
        Object theResult = this.visitorMap.get(inputComponentRealization);
        if (theResult == null) {
            this.visitorMap
            .put(inputComponentRealization, inputComponentRealization);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ComponentRealizationImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitComponentRealization(inputComponentRealization);
            theResult = super
            .caseComponentRealization(inputComponentRealization);
            this.doSwitch(inputComponentRealization.getAbstraction());
        
            for (EObject realization : inputComponentRealization.getRealizingClassifiers()) {
                this.doSwitch(realization);
            }
        }
        return theResult;
    }

    @objid ("3fd04f14-3372-41d0-874f-23d65956359e")
    @Override
    public Object caseConditionalNode(org.eclipse.uml2.uml.ConditionalNode inputConditionalNode) {
        Object theResult = this.visitorMap.get(inputConditionalNode);
        if (theResult == null) {
            this.visitorMap.put(inputConditionalNode, inputConditionalNode);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ConditionalNodeImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitConditionalNode(inputConditionalNode);
            theResult = super.caseConditionalNode(inputConditionalNode);
            for (EObject clause : inputConditionalNode.getClauses()) {
                this.doSwitch(clause);
            }
            for (EObject result : inputConditionalNode.getResults()) {
                this.doSwitch(result);
            }
        }
        return theResult;
    }

    @objid ("0d52ff6a-a4f6-4789-aa40-fccc5f4cf7c3")
    @Override
    public Object caseConnectableElement(org.eclipse.uml2.uml.ConnectableElement inputConnectableElement) {
        Object theResult = super
        .caseConnectableElement(inputConnectableElement);
        for (EObject end : inputConnectableElement.getEnds()) {
            this.doSwitch(end);
        }
        this.doSwitch(inputConnectableElement.getTemplateParameter());
        return theResult;
    }

    @objid ("aa42f0e1-54d1-41fe-a816-2503589ba196")
    @Override
    public Object caseConnectableElementTemplateParameter(org.eclipse.uml2.uml.ConnectableElementTemplateParameter inputConnectableElementTemplateParameter) {
        Object theResult = this.visitorMap
        .get(inputConnectableElementTemplateParameter);
        if (theResult == null) {
            this.visitorMap.put(inputConnectableElementTemplateParameter,
                    inputConnectableElementTemplateParameter);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ConnectableElementTemplateParameterImpl".equals(theResult
                        .getClass().getSimpleName()))) {
            this.behavior
            .visitConnectableElementTemplateParameter(inputConnectableElementTemplateParameter);
            theResult = super
            .caseConnectableElementTemplateParameter(inputConnectableElementTemplateParameter);
            this.doSwitch(inputConnectableElementTemplateParameter
                    .getParameteredElement());
        }
        return theResult;
    }

    @objid ("e8e0a973-ef52-402b-a262-b18c78a39392")
    @Override
    public Object caseConnectionPointReference(org.eclipse.uml2.uml.ConnectionPointReference inputConnectionPointReference) {
        Object theResult = this.visitorMap.get(inputConnectionPointReference);
        if (theResult == null) {
            this.visitorMap.put(inputConnectionPointReference,
                    inputConnectionPointReference);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ConnectionPointReferenceImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior
            .visitConnectionPointReference(inputConnectionPointReference);
            theResult = super
            .caseConnectionPointReference(inputConnectionPointReference);
            for (EObject entry : inputConnectionPointReference.getEntries()) {
                this.doSwitch(entry);
            }
            this.doSwitch(inputConnectionPointReference.getState());
            for (EObject exit : inputConnectionPointReference.getExits()) {
                this.doSwitch(exit);
            }
        }
        return theResult;
    }

    @objid ("c8776c0a-ad90-49ec-9929-1886831c55b9")
    @Override
    public Object caseConnector(org.eclipse.uml2.uml.Connector inputConnector) {
        Object theResult = this.visitorMap.get(inputConnector);
        if (theResult == null) {
            this.visitorMap.put(inputConnector, inputConnector);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ConnectorImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitConnector(inputConnector);
            theResult = super.caseConnector(inputConnector);
            this.doSwitch(inputConnector.getType());
            for (EObject redefinedConnector : inputConnector
                    .getRedefinedConnectors()) {
                this.doSwitch(redefinedConnector);
            }
            for (EObject end : inputConnector.getEnds()) {
                this.doSwitch(end);
            }
            for (EObject contract : inputConnector.getContracts()) {
                this.doSwitch(contract);
            }
        }
        return theResult;
    }

    @objid ("46dea458-935f-4a25-9abf-395c77ba1618")
    @Override
    public Object caseConnectorEnd(org.eclipse.uml2.uml.ConnectorEnd inputConnectorEnd) {
        Object theResult = this.visitorMap.get(inputConnectorEnd);
        if (theResult == null) {
            this.visitorMap.put(inputConnectorEnd, inputConnectorEnd);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ConnectorEndImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitConnectorEnd(inputConnectorEnd);
            theResult = super.caseConnectorEnd(inputConnectorEnd);
            this.doSwitch(inputConnectorEnd.getDefiningEnd());
            this.doSwitch(inputConnectorEnd.getRole());
            this.doSwitch(inputConnectorEnd.getPartWithPort());
        }
        return theResult;
    }

    @objid ("cae8f9a4-1bc3-4649-8f20-726272098e67")
    @Override
    public Object caseConsiderIgnoreFragment(org.eclipse.uml2.uml.ConsiderIgnoreFragment inputConsiderIgnoreFragment) {
        Object theResult = this.visitorMap.get(inputConsiderIgnoreFragment);
        if (theResult == null) {
            this.visitorMap.put(inputConsiderIgnoreFragment,
                    inputConsiderIgnoreFragment);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ConsiderIgnoreFragmentImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitConsiderIgnoreFragment(inputConsiderIgnoreFragment);
            theResult = super
            .caseConsiderIgnoreFragment(inputConsiderIgnoreFragment);
            for (EObject message : inputConsiderIgnoreFragment.getMessages()) {
                this.doSwitch(message);
            }
        }
        return theResult;
    }

    @objid ("dd7e3e5a-a4fc-4e18-88aa-4fd4558daa76")
    @Override
    public Object caseConstraint(org.eclipse.uml2.uml.Constraint inputConstraint) {
        Object theResult = this.visitorMap.get(inputConstraint);
        if (theResult == null) {
            this.visitorMap.put(inputConstraint, inputConstraint);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ConstraintImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitConstraint(inputConstraint);
            theResult = super.caseConstraint(inputConstraint);
            for (EObject constrainedElement : inputConstraint
                    .getConstrainedElements()) {
                this.doSwitch(constrainedElement);
            }
            this.doSwitch(inputConstraint.getSpecification());
            this.doSwitch(inputConstraint.getContext());
        }
        return theResult;
    }

    @objid ("28a323fd-a9ed-4f2e-97a0-ae75942a8f64")
    @Override
    public Object caseContinuation(org.eclipse.uml2.uml.Continuation inputContinuation) {
        Object theResult = this.visitorMap.get(inputContinuation);
        if (theResult == null) {
            this.visitorMap.put(inputContinuation, inputContinuation);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ContinuationImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitContinuation(inputContinuation);
            theResult = super.caseContinuation(inputContinuation);
        }
        return theResult;
    }

    @objid ("8e8b6912-3c3f-45ec-b96c-a764986e2694")
    @Override
    public Object caseControlFlow(org.eclipse.uml2.uml.ControlFlow inputControlFlow) {
        Object theResult = this.visitorMap.get(inputControlFlow);
        if (theResult == null) {
            this.visitorMap.put(inputControlFlow, inputControlFlow);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ControlFlowImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitControlFlow(inputControlFlow);
            theResult = super.caseControlFlow(inputControlFlow);
        }
        return theResult;
    }

    @objid ("6f8499b6-67b4-4988-9e29-91ece530540c")
    @Override
    public Object caseControlNode(org.eclipse.uml2.uml.ControlNode inputControlNode) {
        return super.caseControlNode(inputControlNode);
    }

    @objid ("1dd0755e-8f02-4a9f-8163-8d0c77cccece")
    @Override
    public Object caseCreateLinkAction(org.eclipse.uml2.uml.CreateLinkAction inputCreateLinkAction) {
        Object theResult = this.visitorMap.get(inputCreateLinkAction);
        if (theResult == null) {
            this.visitorMap.put(inputCreateLinkAction, inputCreateLinkAction);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("CreateLinkActionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitCreateLinkAction(inputCreateLinkAction);
            theResult = super.caseCreateLinkAction(inputCreateLinkAction);
            for (EObject endData : inputCreateLinkAction.getEndData()) {
                this.doSwitch(endData);
            }
        }
        return theResult;
    }

    @objid ("ae10c2d7-f9f3-4af0-b251-062b36f64aea")
    @Override
    public Object caseCreateLinkObjectAction(org.eclipse.uml2.uml.CreateLinkObjectAction inputCreateLinkObjectAction) {
        Object theResult = this.visitorMap.get(inputCreateLinkObjectAction);
        if (theResult == null) {
            this.visitorMap.put(inputCreateLinkObjectAction,
                    inputCreateLinkObjectAction);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("CreateLinkObjectActionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitCreateLinkObjectAction(inputCreateLinkObjectAction);
            theResult = super
            .caseCreateLinkObjectAction(inputCreateLinkObjectAction);
            this.doSwitch(inputCreateLinkObjectAction.getResult());
        }
        return theResult;
    }

    @objid ("1b32fc9e-36a4-40f5-9684-5a56bcc40259")
    @Override
    public Object caseCreateObjectAction(org.eclipse.uml2.uml.CreateObjectAction inputCreateObjectAction) {
        Object theResult = this.visitorMap.get(inputCreateObjectAction);
        if (theResult == null) {
            this.visitorMap.put(inputCreateObjectAction, inputCreateObjectAction);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("CreateObjectActionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitCreateObjectAction(inputCreateObjectAction);
            theResult = super.caseCreateObjectAction(inputCreateObjectAction);
            this.doSwitch(inputCreateObjectAction.getClassifier());
            this.doSwitch(inputCreateObjectAction.getResult());
        }
        return theResult;
    }

    @objid ("62158ea7-b67e-4a3e-ab3c-c6acc905825d")
    @Override
    public Object caseCreationEvent(org.eclipse.uml2.uml.CreationEvent inputCreationEvent) {
        Object theResult = this.visitorMap.get(inputCreationEvent);
        if (theResult == null) {
            this.visitorMap.put(inputCreationEvent, inputCreationEvent);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("CreationEventImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitCreationEvent(inputCreationEvent);
            theResult = super.caseCreationEvent(inputCreationEvent);
        }
        return theResult;
    }

    @objid ("de404ac0-70b9-411d-ad8a-f454574fd4f0")
    @Override
    public Object caseDataStoreNode(org.eclipse.uml2.uml.DataStoreNode inputDataStoreNode) {
        Object theResult = this.visitorMap.get(inputDataStoreNode);
        if (theResult == null) {
            this.visitorMap.put(inputDataStoreNode, inputDataStoreNode);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("DataStoreNodeImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitDataStoreNode(inputDataStoreNode);
            theResult = super.caseDataStoreNode(inputDataStoreNode);
        }
        return theResult;
    }

    @objid ("e11e89e4-2da5-4005-a3b0-69aaadce51e9")
    @Override
    public Object caseDataType(org.eclipse.uml2.uml.DataType inputDataType) {
        Object theResult = this.visitorMap.get(inputDataType);
        if (theResult == null) {
            this.visitorMap.put(inputDataType, inputDataType);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("DataTypeImpl"
                        .equals(theResult.getClass().getSimpleName()))) {
            this.behavior.visitDataType(inputDataType);
            theResult = super.caseDataType(inputDataType);
            for (EObject ownedAttribute : inputDataType.getOwnedAttributes()) {
                this.doSwitch(ownedAttribute);
            }
            for (EObject ownedOperation : inputDataType.getOwnedOperations()) {
                this.doSwitch(ownedOperation);
            }
        }
        return theResult;
    }

    @objid ("637499f1-1e36-414b-b077-4312b1e8baa0")
    @Override
    public Object caseDecisionNode(org.eclipse.uml2.uml.DecisionNode inputDecisionNode) {
        Object theResult = this.visitorMap.get(inputDecisionNode);
        if (theResult == null) {
            this.visitorMap.put(inputDecisionNode, inputDecisionNode);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("DecisionNodeImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitDecisionNode(inputDecisionNode);
            theResult = super.caseDecisionNode(inputDecisionNode);
            this.doSwitch(inputDecisionNode.getDecisionInput());
        }
        return theResult;
    }

    @objid ("74c9f794-32e7-4065-8ea4-82a3cc3a2c28")
    @Override
    public Object caseDependency(org.eclipse.uml2.uml.Dependency inputDependency) {
        Object theResult = this.visitorMap.get(inputDependency);
        if (theResult == null) {
            this.visitorMap.put(inputDependency, inputDependency);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("DependencyImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitDependency(inputDependency);
            theResult = super.caseDependency(inputDependency);
            for (EObject supplier : inputDependency.getSuppliers()) {
                this.doSwitch(supplier);
            }
            for (EObject client : inputDependency.getClients()) {
                this.doSwitch(client);
            }
        }
        return theResult;
    }

    @objid ("c99313e5-5bbe-4d80-9989-32a899529ad5")
    @Override
    public Object caseDeployedArtifact(org.eclipse.uml2.uml.DeployedArtifact inputDeployedArtifact) {
        return super.caseDeployedArtifact(inputDeployedArtifact);
    }

    @objid ("1adfedae-eee0-4906-a431-e8926934fced")
    @Override
    public Object caseDeployment(org.eclipse.uml2.uml.Deployment inputDeployment) {
        Object theResult = this.visitorMap.get(inputDeployment);
        if (theResult == null) {
            this.visitorMap.put(inputDeployment, inputDeployment);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("DeploymentImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitDeployment(inputDeployment);
            theResult = super.caseDeployment(inputDeployment);
            for (EObject deployedArtifact : inputDeployment
                    .getDeployedArtifacts()) {
                this.doSwitch(deployedArtifact);
            }
            this.doSwitch(inputDeployment.getLocation());
            for (EObject configuration : inputDeployment.getConfigurations()) {
                this.doSwitch(configuration);
            }
        }
        return theResult;
    }

    @objid ("c53a154a-489d-445c-95cf-4dd570086c11")
    @Override
    public Object caseDeploymentSpecification(org.eclipse.uml2.uml.DeploymentSpecification inputDeploymentSpecification) {
        Object theResult = this.visitorMap.get(inputDeploymentSpecification);
        if (theResult == null) {
            this.visitorMap.put(inputDeploymentSpecification,
                    inputDeploymentSpecification);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("DeploymentSpecificationImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitDeploymentSpecification(inputDeploymentSpecification);
            theResult = super
            .caseDeploymentSpecification(inputDeploymentSpecification);
            this.doSwitch(inputDeploymentSpecification
                    .getDeployment());
        }
        return theResult;
    }

    @objid ("c0f7cd7e-129a-4cdc-90d5-61c1c905fad3")
    @Override
    public Object caseDeploymentTarget(org.eclipse.uml2.uml.DeploymentTarget inputDeploymentTarget) {
        Object theResult = super.caseDeploymentTarget(inputDeploymentTarget);
        for (EObject deployment : inputDeploymentTarget.getDeployments()) {
            this.doSwitch(deployment);
        }
        for (EObject deployedElement : inputDeploymentTarget
                .getDeployedElements()) {
            this.doSwitch(deployedElement);
        }
        return theResult;
    }

    @objid ("53ada190-af59-48e5-8ed5-413a6352cc08")
    @Override
    public Object caseDestroyLinkAction(org.eclipse.uml2.uml.DestroyLinkAction inputDestroyLinkAction) {
        Object theResult = this.visitorMap.get(inputDestroyLinkAction);
        if (theResult == null) {
            this.visitorMap.put(inputDestroyLinkAction, inputDestroyLinkAction);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("DestroyLinkActionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitDestroyLinkAction(inputDestroyLinkAction);
            theResult = super.caseDestroyLinkAction(inputDestroyLinkAction);
            for (EObject endData : inputDestroyLinkAction.getEndData()) {
                this.doSwitch(endData);
            }
        }
        return theResult;
    }

    @objid ("8e176cf8-9ad4-4aa7-b0c9-cd61cd9251d2")
    @Override
    public Object caseDestroyObjectAction(org.eclipse.uml2.uml.DestroyObjectAction inputDestroyObjectAction) {
        Object theResult = this.visitorMap.get(inputDestroyObjectAction);
        if (theResult == null) {
            this.visitorMap.put(inputDestroyObjectAction, inputDestroyObjectAction);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("DestroyObjectActionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitDestroyObjectAction(inputDestroyObjectAction);
            theResult = super.caseDestroyObjectAction(inputDestroyObjectAction);
            this.doSwitch(inputDestroyObjectAction.getTarget());
        }
        return theResult;
    }

    @objid ("26479f1d-4c52-4f31-81a9-fd557cd29bc4")
    @Override
    public Object caseDestructionEvent(org.eclipse.uml2.uml.DestructionEvent inputDestructionEvent) {
        Object theResult = this.visitorMap.get(inputDestructionEvent);
        if (theResult == null) {
            this.visitorMap.put(inputDestructionEvent, inputDestructionEvent);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("DestructionEventImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitDestructionEvent(inputDestructionEvent);
            theResult = super.caseDestructionEvent(inputDestructionEvent);
        }
        return theResult;
    }

    @objid ("6c732e52-b6c4-4fc4-91ee-ac2c8774414c")
    @Override
    public Object caseDevice(org.eclipse.uml2.uml.Device inputDevice) {
        Object theResult = this.visitorMap.get(inputDevice);
        if (theResult == null) {
            this.visitorMap.put(inputDevice, inputDevice);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("DeviceImpl".equals(theResult.getClass().getSimpleName()))) {
            this.behavior.visitDevice(inputDevice);
            theResult = super.caseDevice(inputDevice);
        }
        return theResult;
    }

    @objid ("ac649c37-9831-42a3-af7a-b32a42b7b82f")
    @Override
    public Object caseDirectedRelationship(org.eclipse.uml2.uml.DirectedRelationship inputDirectedRelationship) {
        Object theResult = super
        .caseDirectedRelationship(inputDirectedRelationship);
        for (EObject source : inputDirectedRelationship.getSources()) {
            this.doSwitch(source);
        }
        for (EObject target : inputDirectedRelationship.getTargets()) {
            this.doSwitch(target);
        }
        return theResult;
    }

    @objid ("e5e31a2e-75e3-4609-88fa-97cb3e601a80")
    @Override
    public Object caseDuration(org.eclipse.uml2.uml.Duration inputDuration) {
        Object theResult = this.visitorMap.get(inputDuration);
        if (theResult == null) {
            this.visitorMap.put(inputDuration, inputDuration);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("DurationImpl"
                        .equals(theResult.getClass().getSimpleName()))) {
            this.behavior.visitDuration(inputDuration);
            theResult = super.caseDuration(inputDuration);
            this.doSwitch(inputDuration.getExpr());
            for (EObject observation : inputDuration.getObservations()) {
                this.doSwitch(observation);
            }
        }
        return theResult;
    }

    @objid ("c686d8fc-fa66-4b13-891d-905ec0affb9a")
    @Override
    public Object caseDurationConstraint(org.eclipse.uml2.uml.DurationConstraint inputDurationConstraint) {
        Object theResult = this.visitorMap.get(inputDurationConstraint);
        if (theResult == null) {
            this.visitorMap.put(inputDurationConstraint, inputDurationConstraint);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("DurationConstraintImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitDurationConstraint(inputDurationConstraint);
            theResult = super.caseDurationConstraint(inputDurationConstraint);
            this.doSwitch(inputDurationConstraint.getSpecification());
        }
        return theResult;
    }

    @objid ("270bd71f-f8d2-4170-a99a-0acdc8eace1b")
    @Override
    public Object caseDurationInterval(org.eclipse.uml2.uml.DurationInterval inputDurationInterval) {
        Object theResult = this.visitorMap.get(inputDurationInterval);
        if (theResult == null) {
            this.visitorMap.put(inputDurationInterval, inputDurationInterval);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("DurationIntervalImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitDurationInterval(inputDurationInterval);
            theResult = super.caseDurationInterval(inputDurationInterval);
            this.doSwitch(inputDurationInterval.getMin());
            this.doSwitch(inputDurationInterval.getMax());
        }
        return theResult;
    }

    @objid ("9fd90446-33b9-4433-a5a8-b70a27feac0a")
    @Override
    public Object caseDurationObservation(final DurationObservation inputDurationIservation) {
        Object theResult = this.visitorMap.get(inputDurationIservation);
        if (theResult == null) {
            this.visitorMap.put(inputDurationIservation, inputDurationIservation);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("DurationIservationImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitDurationObservation(inputDurationIservation);
            theResult = super.caseDurationObservation(inputDurationIservation);
            for (EObject event : inputDurationIservation.getEvents()) {
                this.doSwitch(event);
            }
        }
        return theResult;
    }

    @objid ("7cd4caee-3e92-41f1-ab97-359c966b0aa9")
    @Override
    public Object caseElement(org.eclipse.uml2.uml.Element inputElement) {
        Object theResult = super.caseElement(inputElement);
        for (EObject ownedElement : inputElement.getOwnedElements()) {
            this.doSwitch(ownedElement);
        }
        this.doSwitch(inputElement.getOwner());
        for (EObject ownedComment : inputElement.getOwnedComments()) {
            this.doSwitch(ownedComment);
        }
        return theResult;
    }

    @objid ("62f3bb42-e977-4611-985d-19c7668b1e3f")
    @Override
    public Object caseElementImport(org.eclipse.uml2.uml.ElementImport inputElementImport) {
        Object theResult = this.visitorMap.get(inputElementImport);
        if (theResult == null) {
            this.visitorMap.put(inputElementImport, inputElementImport);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ElementImportImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitElementImport(inputElementImport);
            theResult = super.caseElementImport(inputElementImport);
            this.doSwitch(inputElementImport.getImportedElement());
            this.doSwitch(inputElementImport.getImportingNamespace());
        }
        return theResult;
    }

    @objid ("105023d8-8875-4832-a75d-484d76dcfde7")
    @Override
    public Object caseEncapsulatedClassifier(org.eclipse.uml2.uml.EncapsulatedClassifier inputEncapsulatedClassifier) {
        Object theResult = super
        .caseEncapsulatedClassifier(inputEncapsulatedClassifier);
        for (EObject ownedPort : inputEncapsulatedClassifier.getOwnedPorts()) {
            this.doSwitch(ownedPort);
        }
        return theResult;
    }

    @objid ("83731774-7515-4a57-af57-5913afd784f6")
    @Override
    public Object caseEnumeration(org.eclipse.uml2.uml.Enumeration inputEnumeration) {
        Object theResult = this.visitorMap.get(inputEnumeration);
        if (theResult == null) {
            this.visitorMap.put(inputEnumeration, inputEnumeration);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("EnumerationImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitEnumeration(inputEnumeration);
            theResult = super.caseEnumeration(inputEnumeration);
            for (EObject ownedLiteral : inputEnumeration.getOwnedLiterals()) {
                this.doSwitch(ownedLiteral);
            }
        }
        return theResult;
    }

    @objid ("38bf70e7-7332-4990-945b-41fa1a398461")
    @Override
    public Object caseEnumerationLiteral(org.eclipse.uml2.uml.EnumerationLiteral inputEnumerationLiteral) {
        Object theResult = this.visitorMap.get(inputEnumerationLiteral);
        if (theResult == null) {
            this.visitorMap.put(inputEnumerationLiteral, inputEnumerationLiteral);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("EnumerationLiteralImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitEnumerationLiteral(inputEnumerationLiteral);
            theResult = super.caseEnumerationLiteral(inputEnumerationLiteral);
            this.doSwitch(inputEnumerationLiteral.getEnumeration());
        }
        return theResult;
    }

    @objid ("ee551dd3-c8f7-4b42-9379-9ec801b5d6ce")
    @Override
    public Object caseEvent(org.eclipse.uml2.uml.Event inputEvent) {
        return super.caseEvent(inputEvent);
    }

    @objid ("e9139c46-80df-4145-a772-8750f45a8f9b")
    @Override
    public Object caseExceptionHandler(ExceptionHandler inputExceptionHandler) {
        Object theResult = this.visitorMap.get(inputExceptionHandler);
        if (theResult == null) {
            this.visitorMap.put(inputExceptionHandler, inputExceptionHandler);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ExceptionHandlerImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitExceptionHandler(inputExceptionHandler);
            theResult = super.caseExceptionHandler(inputExceptionHandler);
            this.doSwitch(inputExceptionHandler.getProtectedNode());
            this.doSwitch(inputExceptionHandler.getHandlerBody());
            this.doSwitch(inputExceptionHandler.getExceptionInput());
            for (EObject exceptionType : inputExceptionHandler
                    .getExceptionTypes()) {
                this.doSwitch(exceptionType);
            }
        }
        return theResult;
    }

    @objid ("18190a12-dea9-4b61-b307-7e90de9830d6")
    @Override
    public Object caseExecutableNode(org.eclipse.uml2.uml.ExecutableNode inputExecutableNode) {
        Object theResult = super.caseExecutableNode(inputExecutableNode);
        for (EObject handler : inputExecutableNode.getHandlers()) {
            this.doSwitch(handler);
        }
        return theResult;
    }

    @objid ("7ae23ae0-ab30-4694-a82f-23266dd8b1d6")
    @Override
    public Object caseExecutionEnvironment(org.eclipse.uml2.uml.ExecutionEnvironment inputExecutionEnvironment) {
        Object theResult = this.visitorMap.get(inputExecutionEnvironment);
        if (theResult == null) {
            this.visitorMap
            .put(inputExecutionEnvironment, inputExecutionEnvironment);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ExecutionEnvironmentImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitExecutionEnvironment(inputExecutionEnvironment);
            theResult = super
            .caseExecutionEnvironment(inputExecutionEnvironment);
        }
        return theResult;
    }

    @objid ("aa37748e-df2a-4779-aad3-1c171bf89c4e")
    @Override
    public Object caseExecutionEvent(org.eclipse.uml2.uml.ExecutionEvent inputExecutionEvent) {
        Object theResult = this.visitorMap.get(inputExecutionEvent);
        if (theResult == null) {
            this.visitorMap.put(inputExecutionEvent, inputExecutionEvent);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ExecutionEventImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitExecutionEvent(inputExecutionEvent);
            theResult = super.caseExecutionEvent(inputExecutionEvent);
        }
        return theResult;
    }

    @objid ("97c023d6-f010-400e-801f-f0b3e6db362b")
    @Override
    public Object caseExecutionOccurrenceSpecification(org.eclipse.uml2.uml.ExecutionOccurrenceSpecification inputExecutionOccurrenceSpecification) {
        Object theResult = this.visitorMap
        .get(inputExecutionOccurrenceSpecification);
        if (theResult == null) {
            this.visitorMap.put(inputExecutionOccurrenceSpecification,
                    inputExecutionOccurrenceSpecification);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ExecutionOccurrenceSpecificationImpl".equals(theResult
                        .getClass().getSimpleName()))) {
            this.behavior
            .visitExecutionOccurrenceSpecification(inputExecutionOccurrenceSpecification);
            theResult = super
            .caseExecutionOccurrenceSpecification(inputExecutionOccurrenceSpecification);
            this.doSwitch(inputExecutionOccurrenceSpecification
                    .getExecution());
        }
        return theResult;
    }

    @objid ("fd33b90a-b09c-453c-9035-f62ad1970be7")
    @Override
    public Object caseExecutionSpecification(org.eclipse.uml2.uml.ExecutionSpecification inputExecutionSpecification) {
        Object theResult = super
        .caseExecutionSpecification(inputExecutionSpecification);
        this.doSwitch(inputExecutionSpecification.getStart());
        this.doSwitch(inputExecutionSpecification.getFinish());
        return theResult;
    }

    @objid ("0fe8faf2-77ba-4c1d-ac1b-8c87e3bba2e2")
    @Override
    public Object caseExpansionNode(org.eclipse.uml2.uml.ExpansionNode inputExpansionNode) {
        Object theResult = this.visitorMap.get(inputExpansionNode);
        if (theResult == null) {
            this.visitorMap.put(inputExpansionNode, inputExpansionNode);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ExpansionNodeImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitExpansionNode(inputExpansionNode);
            theResult = super.caseExpansionNode(inputExpansionNode);
            this.doSwitch(inputExpansionNode.getRegionAsOutput());
            this.doSwitch(inputExpansionNode.getRegionAsInput());
        }
        return theResult;
    }

    @objid ("6010ba8f-3511-40bd-86b4-0e5934bad9aa")
    @Override
    public Object caseExpansionRegion(org.eclipse.uml2.uml.ExpansionRegion inputExpansionRegion) {
        Object theResult = this.visitorMap.get(inputExpansionRegion);
        if (theResult == null) {
            this.visitorMap.put(inputExpansionRegion, inputExpansionRegion);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ExpansionRegionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitExpansionRegion(inputExpansionRegion);
            theResult = super.caseExpansionRegion(inputExpansionRegion);
            for (EObject outputElement : inputExpansionRegion
                    .getOutputElements()) {
                this.doSwitch(outputElement);
            }
            for (EObject inputElement : inputExpansionRegion.getInputElements()) {
                this.doSwitch(inputElement);
            }
        }
        return theResult;
    }

    @objid ("6e0ee4fa-45c7-484a-9cbd-d00787588e20")
    @Override
    public Object caseExpression(org.eclipse.uml2.uml.Expression inputExpression) {
        Object theResult = this.visitorMap.get(inputExpression);
        if (theResult == null) {
            this.visitorMap.put(inputExpression, inputExpression);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ExpressionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitExpression(inputExpression);
            theResult = super.caseExpression(inputExpression);
            for (EObject operand : inputExpression.getOperands()) {
                this.doSwitch(operand);
            }
        }
        return theResult;
    }

    @objid ("d0aa227e-49b2-44d9-8ccc-18c78fc7fd93")
    @Override
    public Object caseExtend(org.eclipse.uml2.uml.Extend inputExtend) {
        Object theResult = this.visitorMap.get(inputExtend);
        if (theResult == null) {
            this.visitorMap.put(inputExtend, inputExtend);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ExtendImpl".equals(theResult.getClass().getSimpleName()))) {
            this.behavior.visitExtend(inputExtend);
            theResult = super.caseExtend(inputExtend);
            this.doSwitch(inputExtend.getExtendedCase());
            this.doSwitch(inputExtend.getExtension());
            this.doSwitch(inputExtend.getCondition());
            for (EObject extensionLocation : inputExtend.getExtensionLocations()) {
                this.doSwitch(extensionLocation);
            }
        }
        return theResult;
    }

    @objid ("164b9c3b-60e8-412b-b1be-9626e641d237")
    @Override
    public Object caseExtension(org.eclipse.uml2.uml.Extension inputExtension) {
        Object theResult = this.visitorMap.get(inputExtension);
        if (theResult == null) {
            this.visitorMap.put(inputExtension, inputExtension);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ExtensionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitExtension(inputExtension);
            theResult = super.caseExtension(inputExtension);
            this.doSwitch(inputExtension.getMetaclass());
            // this.doSwitch((EObject)inputExtension.getOwnedEnd());
            for (EObject ownedEnd : inputExtension.getOwnedEnds()) {
                this.doSwitch(ownedEnd);
            }
        }
        return theResult;
    }

    @objid ("e664c96c-7ee6-4ece-96cb-34a8006e1780")
    @Override
    public Object caseExtensionEnd(org.eclipse.uml2.uml.ExtensionEnd inputExtensionEnd) {
        Object theResult = this.visitorMap.get(inputExtensionEnd);
        if (theResult == null) {
            this.visitorMap.put(inputExtensionEnd, inputExtensionEnd);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ExtensionEndImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitExtensionEnd(inputExtensionEnd);
            theResult = super.caseExtensionEnd(inputExtensionEnd);
            this.doSwitch(inputExtensionEnd.getType());
        }
        return theResult;
    }

    @objid ("b1a82dc7-d816-4aa6-90b7-a17ce92f047b")
    @Override
    public Object caseExtensionPoint(org.eclipse.uml2.uml.ExtensionPoint inputExtensionPoint) {
        Object theResult = this.visitorMap.get(inputExtensionPoint);
        if (theResult == null) {
            this.visitorMap.put(inputExtensionPoint, inputExtensionPoint);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ExtensionPointImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitExtensionPoint(inputExtensionPoint);
            theResult = super.caseExtensionPoint(inputExtensionPoint);
            this.doSwitch(inputExtensionPoint.getUseCase());
        }
        return theResult;
    }

    @objid ("60bbcd17-894d-4fa7-abbd-68ff3a53ad8a")
    @Override
    public Object caseFeature(org.eclipse.uml2.uml.Feature inputFeature) {
        Object theResult = super.caseFeature(inputFeature);
        for (EObject featuringClassifier : inputFeature
                .getFeaturingClassifiers()) {
            this.doSwitch(featuringClassifier);
        }
        return theResult;
    }

    @objid ("72db2ce7-0448-4a46-9b9e-d2ded5f9fd20")
    @Override
    public Object caseFinalNode(FinalNode inputFinalNode) {
        return super.caseFinalNode(inputFinalNode);
    }

    @objid ("7e598e46-010c-4868-9883-8d09586e8285")
    @Override
    public Object caseFinalState(FinalState inputFinalState) {
        Object theResult = this.visitorMap.get(inputFinalState);
        if (theResult == null) {
            this.visitorMap.put(inputFinalState, inputFinalState);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("FinalStateImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitFinalState(inputFinalState);
            theResult = super.caseFinalState(inputFinalState);
        }
        return theResult;
    }

    @objid ("7f3b2625-ae45-4d15-8bb9-cf553fa5dc5a")
    @Override
    public Object caseFlowFinalNode(org.eclipse.uml2.uml.FlowFinalNode inputFlowFinalNode) {
        Object theResult = this.visitorMap.get(inputFlowFinalNode);
        if (theResult == null) {
            this.visitorMap.put(inputFlowFinalNode, inputFlowFinalNode);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("FlowFinalNodeImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitFlowFinalNode(inputFlowFinalNode);
            theResult = super.caseFlowFinalNode(inputFlowFinalNode);
        }
        return theResult;
    }

    @objid ("52daab22-bd75-4d1c-89b9-814f49f5d5d9")
    @Override
    public Object caseForkNode(org.eclipse.uml2.uml.ForkNode inputForkNode) {
        Object theResult = this.visitorMap.get(inputForkNode);
        if (theResult == null) {
            this.visitorMap.put(inputForkNode, inputForkNode);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ForkNodeImpl"
                        .equals(theResult.getClass().getSimpleName()))) {
            this.behavior.visitForkNode(inputForkNode);
            theResult = super.caseForkNode(inputForkNode);
        }
        return theResult;
    }

    @objid ("7fcf10b4-672a-4011-bfac-c5aae8774ab0")
    @Override
    public Object caseFunctionBehavior(org.eclipse.uml2.uml.FunctionBehavior inputFunctionBehavior) {
        Object theResult = this.visitorMap.get(inputFunctionBehavior);
        if (theResult == null) {
            this.visitorMap.put(inputFunctionBehavior, inputFunctionBehavior);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("FunctionBehaviorImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitFunctionBehavior(inputFunctionBehavior);
            theResult = super.caseFunctionBehavior(inputFunctionBehavior);
        }
        return theResult;
    }

    @objid ("74b9cbf6-3121-4524-9a5e-49f8d293c6a7")
    @Override
    public Object caseGate(org.eclipse.uml2.uml.Gate inputGate) {
        Object theResult = this.visitorMap.get(inputGate);
        if (theResult == null) {
            this.visitorMap.put(inputGate, inputGate);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("GateImpl".equals(theResult.getClass().getSimpleName()))) {
            this.behavior.visitGate(inputGate);
            theResult = super.caseGate(inputGate);
        }
        return theResult;
    }

    @objid ("1cd28aa7-a887-40ea-9fc3-b989cbe6f398")
    @Override
    public Object caseGeneralOrdering(org.eclipse.uml2.uml.GeneralOrdering inputGeneralOrdering) {
        Object theResult = this.visitorMap.get(inputGeneralOrdering);
        if (theResult == null) {
            this.visitorMap.put(inputGeneralOrdering, inputGeneralOrdering);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("GeneralOrderingImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitGeneralOrdering(inputGeneralOrdering);
            theResult = super.caseGeneralOrdering(inputGeneralOrdering);
            this.doSwitch(inputGeneralOrdering.getBefore());
            this.doSwitch(inputGeneralOrdering.getAfter());
        }
        return theResult;
    }

    @objid ("a0c63d2c-3c88-49a1-b00d-a0c5124eac9e")
    @Override
    public Object caseGeneralization(org.eclipse.uml2.uml.Generalization inputGeneralization) {
        Object theResult = this.visitorMap.get(inputGeneralization);
        if (theResult == null) {
            this.visitorMap.put(inputGeneralization, inputGeneralization);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("GeneralizationImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitGeneralization(inputGeneralization);
            theResult = super.caseGeneralization(inputGeneralization);
            this.doSwitch(inputGeneralization.getSpecific());
            this.doSwitch(inputGeneralization.getGeneral());
            for (EObject generalizationSet : inputGeneralization
                    .getGeneralizationSets()) {
                this.doSwitch(generalizationSet);
            }
        }
        return theResult;
    }

    @objid ("ca6bfd44-d79d-4248-a706-6eda83ccaf90")
    @Override
    public Object caseGeneralizationSet(org.eclipse.uml2.uml.GeneralizationSet inputGeneralizationSet) {
        Object theResult = this.visitorMap.get(inputGeneralizationSet);
        if (theResult == null) {
            this.visitorMap.put(inputGeneralizationSet, inputGeneralizationSet);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("GeneralizationSetImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitGeneralizationSet(inputGeneralizationSet);
            theResult = super.caseGeneralizationSet(inputGeneralizationSet);
            this.doSwitch(inputGeneralizationSet.getPowertype());
            for (EObject generalization : inputGeneralizationSet
                    .getGeneralizations()) {
                this.doSwitch(generalization);
            }
        }
        return theResult;
    }

    @objid ("8a91c38e-40ce-4f1f-ab60-87e68209fb70")
    @Override
    public Object caseImage(org.eclipse.uml2.uml.Image inputImage) {
        Object theResult = this.visitorMap.get(inputImage);
        if (theResult == null) {
            this.visitorMap.put(inputImage, inputImage);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ImageImpl".equals(theResult.getClass().getSimpleName()))) {
            this.behavior.visitImage(inputImage);
            theResult = super.caseImage(inputImage);
        }
        return theResult;
    }

    @objid ("184d7d30-30af-4651-a325-97600bb1993c")
    @Override
    public Object caseInclude(org.eclipse.uml2.uml.Include inputInclude) {
        Object theResult = this.visitorMap.get(inputInclude);
        if (theResult == null) {
            this.visitorMap.put(inputInclude, inputInclude);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("IncludeImpl".equals(theResult.getClass().getSimpleName()))) {
            this.behavior.visitInclude(inputInclude);
            theResult = super.caseInclude(inputInclude);
            this.doSwitch(inputInclude.getIncludingCase());
            this.doSwitch(inputInclude.getAddition());
        }
        return theResult;
    }

    @objid ("ee4a5239-0151-41d6-a9f7-601456a34d77")
    @Override
    public Object caseInformationFlow(org.eclipse.uml2.uml.InformationFlow inputInformationFlow) {
        Object theResult = this.visitorMap.get(inputInformationFlow);
        if (theResult == null) {
            this.visitorMap.put(inputInformationFlow, inputInformationFlow);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("InformationFlowImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitInformationFlow(inputInformationFlow);
            theResult = super.caseInformationFlow(inputInformationFlow);
            for (EObject realization : inputInformationFlow.getRealizations()) {
                this.doSwitch(realization);
            }
            for (EObject conveyed : inputInformationFlow.getConveyeds()) {
                this.doSwitch(conveyed);
            }
            for (EObject informationSource : inputInformationFlow
                    .getInformationSources()) {
                this.doSwitch(informationSource);
            }
            for (EObject informationTarget : inputInformationFlow
                    .getInformationTargets()) {
                this.doSwitch(informationTarget);
            }
            for (EObject realizingActivityEdge : inputInformationFlow
                    .getRealizingActivityEdges()) {
                this.doSwitch(realizingActivityEdge);
            }
            for (EObject realizingConnector : inputInformationFlow
                    .getRealizingConnectors()) {
                this.doSwitch(realizingConnector);
            }
            for (EObject realizingMessage : inputInformationFlow
                    .getRealizingMessages()) {
                this.doSwitch(realizingMessage);
            }
        }
        return theResult;
    }

    @objid ("84859ecb-f018-4c3f-bffc-e8d95d3b3e15")
    @Override
    public Object caseInformationItem(org.eclipse.uml2.uml.InformationItem inputInformationItem) {
        Object theResult = this.visitorMap.get(inputInformationItem);
        if (theResult == null) {
            this.visitorMap.put(inputInformationItem, inputInformationItem);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("InformationItemImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitInformationItem(inputInformationItem);
            theResult = super.caseInformationItem(inputInformationItem);
            for (EObject represented : inputInformationItem.getRepresenteds()) {
                this.doSwitch(represented);
            }
        }
        return theResult;
    }

    @objid ("e1541b2b-d18d-4a0b-927e-6732549fd7f5")
    @Override
    public Object caseInitialNode(org.eclipse.uml2.uml.InitialNode inputInitialNode) {
        Object theResult = this.visitorMap.get(inputInitialNode);
        if (theResult == null) {
            this.visitorMap.put(inputInitialNode, inputInitialNode);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("InitialNodeImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitInitialNode(inputInitialNode);
            theResult = super.caseInitialNode(inputInitialNode);
        }
        return theResult;
    }

    @objid ("a91c1954-bd53-4657-8da4-deeacb0bc2c7")
    @Override
    public Object caseInputPin(org.eclipse.uml2.uml.InputPin inputInputPin) {
        Object theResult = this.visitorMap.get(inputInputPin);
        if (theResult == null) {
            this.visitorMap.put(inputInputPin, inputInputPin);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("InputPinImpl"
                        .equals(theResult.getClass().getSimpleName()))) {
            this.behavior.visitInputPin(inputInputPin);
            theResult = super.caseInputPin(inputInputPin);
        }
        return theResult;
    }

    @objid ("8895f5cd-c795-4de8-bc38-7a363b7f0ccb")
    @Override
    public Object caseInstanceSpecification(InstanceSpecification inputInstanceSpecification) {
        Object theResult = this.visitorMap.get(inputInstanceSpecification);
        if (theResult == null) {
            this.visitorMap.put(inputInstanceSpecification,
                    inputInstanceSpecification);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("InstanceSpecificationImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitInstanceSpecification(inputInstanceSpecification);
            theResult = super
            .caseInstanceSpecification(inputInstanceSpecification);
            for (EObject classifier : inputInstanceSpecification
                    .getClassifiers()) {
                this.doSwitch(classifier);
            }
            for (EObject slot : inputInstanceSpecification.getSlots()) {
                this.doSwitch(slot);
            }
            this.doSwitch(inputInstanceSpecification
                    .getSpecification());
        }
        return theResult;
    }

    @objid ("c807cbe6-42a1-4814-bc60-4333e24fd4a5")
    @Override
    public Object caseInstanceValue(InstanceValue inputInstanceValue) {
        Object theResult = this.visitorMap.get(inputInstanceValue);
        if (theResult == null) {
            this.visitorMap.put(inputInstanceValue, inputInstanceValue);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("InstanceValueImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitInstanceValue(inputInstanceValue);
            theResult = super.caseInstanceValue(inputInstanceValue);
            this.doSwitch(inputInstanceValue.getInstance());
        }
        return theResult;
    }

    @objid ("320064eb-69d2-436f-8498-750740cfffe1")
    @Override
    public Object caseInteraction(org.eclipse.uml2.uml.Interaction inputInteraction) {
        Object theResult = this.visitorMap.get(inputInteraction);
        if (theResult == null) {
            this.visitorMap.put(inputInteraction, inputInteraction);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("InteractionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitInteraction(inputInteraction);
            theResult = super.caseInteraction(inputInteraction);
            for (EObject lifeline : inputInteraction.getLifelines()) {
                this.doSwitch(lifeline);
            }
            for (EObject message : inputInteraction.getMessages()) {
                this.doSwitch(message);
            }
            for (EObject fragment : inputInteraction.getFragments()) {
                this.doSwitch(fragment);
            }
            for (EObject action : inputInteraction.getActions()) {
                this.doSwitch(action);
            }
            for (EObject formalGate : inputInteraction.getFormalGates()) {
                this.doSwitch(formalGate);
            }
        }
        return theResult;
    }

    @objid ("c782f76a-2276-4641-9b76-4041fe59865d")
    @Override
    public Object caseInteractionConstraint(org.eclipse.uml2.uml.InteractionConstraint inputInteractionConstraint) {
        Object theResult = this.visitorMap.get(inputInteractionConstraint);
        if (theResult == null) {
            this.visitorMap.put(inputInteractionConstraint,
                    inputInteractionConstraint);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("InteractionConstraintImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitInteractionConstraint(inputInteractionConstraint);
            theResult = super
            .caseInteractionConstraint(inputInteractionConstraint);
            this.doSwitch(inputInteractionConstraint.getMinint());
            this.doSwitch(inputInteractionConstraint.getMaxint());
        }
        return theResult;
    }

    @objid ("ca5a6c06-460c-422c-ab36-b72ed919dcea")
    @Override
    public Object caseInteractionFragment(org.eclipse.uml2.uml.InteractionFragment inputInteractionFragment) {
        Object theResult = super
        .caseInteractionFragment(inputInteractionFragment);
        for (EObject covered : inputInteractionFragment.getCovereds()) {
            this.doSwitch(covered);
        }
        for (EObject generalOrdering : inputInteractionFragment
                .getGeneralOrderings()) {
            this.doSwitch(generalOrdering);
        }
        this.doSwitch(inputInteractionFragment
                .getEnclosingInteraction());
        this.doSwitch(inputInteractionFragment.getEnclosingOperand());
        return theResult;
    }

    @objid ("0fb4ad93-7ac4-4345-b117-92ea55238ed1")
    @Override
    public Object caseInteractionOperand(org.eclipse.uml2.uml.InteractionOperand inputInteractionOperand) {
        Object theResult = this.visitorMap.get(inputInteractionOperand);
        if (theResult == null) {
            this.visitorMap.put(inputInteractionOperand, inputInteractionOperand);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("InteractionOperandImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitInteractionOperand(inputInteractionOperand);
            theResult = super.caseInteractionOperand(inputInteractionOperand);
            this.doSwitch(inputInteractionOperand.getGuard());
            for (EObject fragment : inputInteractionOperand.getFragments()) {
                this.doSwitch(fragment);
            }
        }
        return theResult;
    }

    @objid ("ea411216-fce3-4d0f-b235-f6fe53f0e109")
    @Override
    public Object caseInteractionUse(org.eclipse.uml2.uml.InteractionUse inputInteractionUse) {
        Object theResult = this.visitorMap.get(inputInteractionUse);
        if (theResult == null) {
            this.visitorMap.put(inputInteractionUse, inputInteractionUse);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("InteractionUseImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitInteractionUse(inputInteractionUse);
            theResult = super.caseInteractionUse(inputInteractionUse);
            this.doSwitch(inputInteractionUse.getRefersTo());
            for (EObject actualGate : inputInteractionUse.getActualGates()) {
                this.doSwitch(actualGate);
            }
            for (EObject argument : inputInteractionUse.getArguments()) {
                this.doSwitch(argument);
            }
        }
        return theResult;
    }

    @objid ("69acfc7f-b61c-4f31-b03e-137837a4fb0a")
    @Override
    public Object caseInterface(org.eclipse.uml2.uml.Interface inputInterface) {
        Object theResult = this.visitorMap.get(inputInterface);
        if (theResult == null) {
            this.visitorMap.put(inputInterface, inputInterface);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("InterfaceImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitInterface(inputInterface);
            theResult = super.caseInterface(inputInterface);
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
            for (EObject redefinedInterface : inputInterface
                    .getRedefinedInterfaces()) {
                this.doSwitch(redefinedInterface);
            }
            for (EObject ownedReception : inputInterface.getOwnedReceptions()) {
                this.doSwitch(ownedReception);
            }
            this.doSwitch(inputInterface.getProtocol());
        }
        return theResult;
    }

    @objid ("f1f07138-b1d8-4672-9136-57c1a5596e4e")
    @Override
    public Object caseInterfaceRealization(org.eclipse.uml2.uml.InterfaceRealization inputInterfaceRealization) {
        Object theResult = this.visitorMap.get(inputInterfaceRealization);
        if (theResult == null) {
            this.visitorMap
            .put(inputInterfaceRealization, inputInterfaceRealization);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("InterfaceRealizationImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitInterfaceRealization(inputInterfaceRealization);
            theResult = super
            .caseInterfaceRealization(inputInterfaceRealization);
            this.doSwitch(inputInterfaceRealization.getContract());
            this.doSwitch(inputInterfaceRealization
                    .getImplementingClassifier());
        }
        return theResult;
    }

    @objid ("1765a5de-7ebb-40f9-a88f-a9b9088fb858")
    @Override
    public Object caseInterruptibleActivityRegion(org.eclipse.uml2.uml.InterruptibleActivityRegion inputInterruptibleActivityRegion) {
        Object theResult = this.visitorMap.get(inputInterruptibleActivityRegion);
        if (theResult == null) {
            this.visitorMap.put(inputInterruptibleActivityRegion,
                    inputInterruptibleActivityRegion);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("InterruptibleActivityRegionImpl".equals(theResult
                        .getClass().getSimpleName()))) {
            this.behavior
            .visitInterruptibleActivityRegion(inputInterruptibleActivityRegion);
            theResult = super
            .caseInterruptibleActivityRegion(inputInterruptibleActivityRegion);
            for (EObject interruptingEdge : inputInterruptibleActivityRegion
                    .getInterruptingEdges()) {
                this.doSwitch(interruptingEdge);
            }
            for (EObject node : inputInterruptibleActivityRegion.getNodes()) {
                this.doSwitch(node);
            }
        }
        return theResult;
    }

    @objid ("43fb3088-18b3-4162-be4c-fa938351a8f0")
    @Override
    public Object caseInterval(org.eclipse.uml2.uml.Interval inputInterval) {
        Object theResult = this.visitorMap.get(inputInterval);
        if (theResult == null) {
            this.visitorMap.put(inputInterval, inputInterval);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("IntervalImpl"
                        .equals(theResult.getClass().getSimpleName()))) {
            this.behavior.visitInterval(inputInterval);
            theResult = super.caseInterval(inputInterval);
            this.doSwitch(inputInterval.getMin());
            this.doSwitch(inputInterval.getMax());
        }
        return theResult;
    }

    @objid ("fc00f9ed-2bfb-480c-b852-ff5f0f340004")
    @Override
    public Object caseIntervalConstraint(org.eclipse.uml2.uml.IntervalConstraint inputIntervalConstraint) {
        Object theResult = this.visitorMap.get(inputIntervalConstraint);
        if (theResult == null) {
            this.visitorMap.put(inputIntervalConstraint, inputIntervalConstraint);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("IntervalConstraintImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitIntervalConstraint(inputIntervalConstraint);
            theResult = super.caseIntervalConstraint(inputIntervalConstraint);
            this.doSwitch(inputIntervalConstraint.getSpecification());
        }
        return theResult;
    }

    @objid ("c859ac2b-63cf-4833-b0e4-c6ea099c0de9")
    @Override
    public Object caseInvocationAction(org.eclipse.uml2.uml.InvocationAction inputInvocationAction) {
        Object theResult = super.caseInvocationAction(inputInvocationAction);
        for (EObject argument : inputInvocationAction.getArguments()) {
            this.doSwitch(argument);
        }
        this.doSwitch(inputInvocationAction.getOnPort());
        return theResult;
    }

    @objid ("1d05bc84-e981-42b0-b1d4-c34bf8d40815")
    @Override
    public Object caseJoinNode(org.eclipse.uml2.uml.JoinNode inputJoinNode) {
        Object theResult = this.visitorMap.get(inputJoinNode);
        if (theResult == null) {
            this.visitorMap.put(inputJoinNode, inputJoinNode);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("JoinNodeImpl"
                        .equals(theResult.getClass().getSimpleName()))) {
            this.behavior.visitJoinNode(inputJoinNode);
            theResult = super.caseJoinNode(inputJoinNode);
            this.doSwitch(inputJoinNode.getJoinSpec());
        }
        return theResult;
    }

    @objid ("246f801a-9100-4dd1-b17c-8d019ae4bb09")
    @Override
    public Object caseLifeline(org.eclipse.uml2.uml.Lifeline inputLifeline) {
        Object theResult = this.visitorMap.get(inputLifeline);
        if (theResult == null) {
            this.visitorMap.put(inputLifeline, inputLifeline);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("LifelineImpl"
                        .equals(theResult.getClass().getSimpleName()))) {
            this.behavior.visitLifeline(inputLifeline);
            theResult = super.caseLifeline(inputLifeline);
            for (EObject coveredBy : inputLifeline.getCoveredBys()) {
                this.doSwitch(coveredBy);
            }
            this.doSwitch(inputLifeline.getRepresents());
            this.doSwitch(inputLifeline.getInteraction());
            this.doSwitch(inputLifeline.getSelector());
            this.doSwitch(inputLifeline.getDecomposedAs());
        }
        return theResult;
    }

    @objid ("44c2feef-04cc-441e-9d4c-faefa8850dc3")
    @Override
    public Object caseLinkAction(org.eclipse.uml2.uml.LinkAction inputLinkAction) {
        Object theResult = super.caseLinkAction(inputLinkAction);
        for (EObject endData : inputLinkAction.getEndData()) {
            this.doSwitch(endData);
        }
        for (EObject inputValue : inputLinkAction.getInputValues()) {
            this.doSwitch(inputValue);
        }
        return theResult;
    }

    @objid ("558de564-7db3-4d0d-a6bc-89ca32efe0ce")
    @Override
    public Object caseLinkEndCreationData(org.eclipse.uml2.uml.LinkEndCreationData inputLinkEndCreationData) {
        Object theResult = this.visitorMap.get(inputLinkEndCreationData);
        if (theResult == null) {
            this.visitorMap.put(inputLinkEndCreationData, inputLinkEndCreationData);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("LinkEndCreationDataImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitLinkEndCreationData(inputLinkEndCreationData);
            theResult = super.caseLinkEndCreationData(inputLinkEndCreationData);
            this.doSwitch(inputLinkEndCreationData.getInsertAt());
        }
        return theResult;
    }

    @objid ("a92ca380-6add-4e9c-9242-3b6bef41e298")
    @Override
    public Object caseLinkEndData(org.eclipse.uml2.uml.LinkEndData inputLinkEndData) {
        Object theResult = this.visitorMap.get(inputLinkEndData);
        if (theResult == null) {
            this.visitorMap.put(inputLinkEndData, inputLinkEndData);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("LinkEndDataImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitLinkEndData(inputLinkEndData);
            theResult = super.caseLinkEndData(inputLinkEndData);
            this.doSwitch(inputLinkEndData.getValue());
            this.doSwitch(inputLinkEndData.getEnd());
            for (EObject qualifier : inputLinkEndData.getQualifiers()) {
                this.doSwitch(qualifier);
            }
        }
        return theResult;
    }

    @objid ("e9e0aa39-e41a-4aea-928c-cec8c9e93505")
    @Override
    public Object caseLinkEndDestructionData(org.eclipse.uml2.uml.LinkEndDestructionData inputLinkEndDestructionData) {
        Object theResult = this.visitorMap.get(inputLinkEndDestructionData);
        if (theResult == null) {
            this.visitorMap.put(inputLinkEndDestructionData,
                    inputLinkEndDestructionData);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("LinkEndDestructionDataImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitLinkEndDestructionData(inputLinkEndDestructionData);
            theResult = super
            .caseLinkEndDestructionData(inputLinkEndDestructionData);
            this.doSwitch(inputLinkEndDestructionData.getDestroyAt());
        }
        return theResult;
    }

    @objid ("08f472df-26be-4b97-a1c3-5cd14040b61c")
    @Override
    public Object caseLiteralBoolean(org.eclipse.uml2.uml.LiteralBoolean inputLiteralBoolean) {
        Object theResult = this.visitorMap.get(inputLiteralBoolean);
        if (theResult == null) {
            this.visitorMap.put(inputLiteralBoolean, inputLiteralBoolean);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("LiteralBooleanImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitLiteralBoolean(inputLiteralBoolean);
            theResult = super.caseLiteralBoolean(inputLiteralBoolean);
        }
        return theResult;
    }

    @objid ("104024d0-3dac-4c22-a1df-e21a9d1c4b2f")
    @Override
    public Object caseLiteralInteger(org.eclipse.uml2.uml.LiteralInteger inputLiteralInteger) {
        Object theResult = this.visitorMap.get(inputLiteralInteger);
        if (theResult == null) {
            this.visitorMap.put(inputLiteralInteger, inputLiteralInteger);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("LiteralIntegerImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitLiteralInteger(inputLiteralInteger);
            theResult = super.caseLiteralInteger(inputLiteralInteger);
        }
        return theResult;
    }

    @objid ("16d5c2b8-d521-4492-a630-e809180e9967")
    @Override
    public Object caseLiteralNull(org.eclipse.uml2.uml.LiteralNull inputLiteralNull) {
        Object theResult = this.visitorMap.get(inputLiteralNull);
        if (theResult == null) {
            this.visitorMap.put(inputLiteralNull, inputLiteralNull);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("LiteralNullImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitLiteralNull(inputLiteralNull);
            theResult = super.caseLiteralNull(inputLiteralNull);
        }
        return theResult;
    }

    @objid ("fd822718-ac50-4694-a834-d68c77af935b")
    @Override
    public Object caseLiteralSpecification(org.eclipse.uml2.uml.LiteralSpecification inputLiteralSpecification) {
        Object theResult = super
        .caseLiteralSpecification(inputLiteralSpecification);
        return theResult;
    }

    @objid ("6f258579-c260-4f6a-8db6-31df6c4cb606")
    @Override
    public Object caseLiteralString(org.eclipse.uml2.uml.LiteralString inputLiteralString) {
        Object theResult = this.visitorMap.get(inputLiteralString);
        if (theResult == null) {
            this.visitorMap.put(inputLiteralString, inputLiteralString);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("LiteralStringImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitLiteralString(inputLiteralString);
            theResult = super.caseLiteralString(inputLiteralString);
        }
        return theResult;
    }

    @objid ("177d510a-dced-42ea-bb0f-008bf8599957")
    @Override
    public Object caseLiteralUnlimitedNatural(org.eclipse.uml2.uml.LiteralUnlimitedNatural inputLiteralUnlimitedNatural) {
        Object theResult = this.visitorMap.get(inputLiteralUnlimitedNatural);
        if (theResult == null) {
            this.visitorMap.put(inputLiteralUnlimitedNatural,
                    inputLiteralUnlimitedNatural);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("LiteralUnlimitedNaturalImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitLiteralUnlimitedNatural(inputLiteralUnlimitedNatural);
            theResult = super
            .caseLiteralUnlimitedNatural(inputLiteralUnlimitedNatural);
        }
        return theResult;
    }

    @objid ("fac5f982-f83e-41fc-a648-ef517a41e6e2")
    @Override
    public Object caseLoopNode(org.eclipse.uml2.uml.LoopNode inputLoopNode) {
        Object theResult = this.visitorMap.get(inputLoopNode);
        if (theResult == null) {
            this.visitorMap.put(inputLoopNode, inputLoopNode);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("LoopNodeImpl"
                        .equals(theResult.getClass().getSimpleName()))) {
            this.behavior.visitLoopNode(inputLoopNode);
            theResult = super.caseLoopNode(inputLoopNode);
            for (EObject bodyPart : inputLoopNode.getBodyParts()) {
                this.doSwitch(bodyPart);
            }
            for (EObject setupPart : inputLoopNode.getSetupParts()) {
                this.doSwitch(setupPart);
            }
            this.doSwitch(inputLoopNode.getDecider());
            for (EObject test : inputLoopNode.getTests()) {
                this.doSwitch(test);
            }
            for (EObject result : inputLoopNode.getResults()) {
                this.doSwitch(result);
            }
            for (EObject loopVariable : inputLoopNode.getLoopVariables()) {
                this.doSwitch(loopVariable);
            }
            for (EObject bodyOutput : inputLoopNode.getBodyOutputs()) {
                this.doSwitch(bodyOutput);
            }
            for (EObject loopVariableInput : inputLoopNode
                    .getLoopVariableInputs()) {
                this.doSwitch(loopVariableInput);
            }
        }
        return theResult;
    }

    @objid ("2fdaecda-c233-4d1c-ab82-bf20e2f1c8f7")
    @Override
    public Object caseManifestation(org.eclipse.uml2.uml.Manifestation inputManifestation) {
        Object theResult = this.visitorMap.get(inputManifestation);
        if (theResult == null) {
            this.visitorMap.put(inputManifestation, inputManifestation);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ManifestationImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitManifestation(inputManifestation);
            theResult = super.caseManifestation(inputManifestation);
            this.doSwitch(inputManifestation.getUtilizedElement());
        }
        return theResult;
    }

    @objid ("16b1da18-e2ad-424d-a455-d9e628dc6176")
    @Override
    public Object caseMergeNode(org.eclipse.uml2.uml.MergeNode inputMergeNode) {
        Object theResult = this.visitorMap.get(inputMergeNode);
        if (theResult == null) {
            this.visitorMap.put(inputMergeNode, inputMergeNode);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("MergeNodeImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitMergeNode(inputMergeNode);
            theResult = super.caseMergeNode(inputMergeNode);
        }
        return theResult;
    }

    @objid ("d112c626-2dc5-482a-9287-ef9b2626c402")
    @Override
    public Object caseMessage(org.eclipse.uml2.uml.Message inputMessage) {
        Object theResult = this.visitorMap.get(inputMessage);
        if (theResult == null) {
            this.visitorMap.put(inputMessage, inputMessage);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("MessageImpl".equals(theResult.getClass().getSimpleName()))) {
            this.behavior.visitMessage(inputMessage);
            theResult = super.caseMessage(inputMessage);
            this.doSwitch(inputMessage.getReceiveEvent());
            this.doSwitch(inputMessage.getSendEvent());
            this.doSwitch(inputMessage.getConnector());
            this.doSwitch(inputMessage.getInteraction());
            for (EObject argument : inputMessage.getArguments()) {
                this.doSwitch(argument);
            }
            this.doSwitch(inputMessage.getSignature());
        }
        return theResult;
    }

    @objid ("2480d1a6-5399-4c82-b2fb-da3efe50c4a5")
    @Override
    public Object caseMessageEnd(org.eclipse.uml2.uml.MessageEnd inputMessageEnd) {
        Object theResult = super.caseMessageEnd(inputMessageEnd);
        this.doSwitch(inputMessageEnd.getMessage());
        return theResult;
    }

    @objid ("f678d741-e0ce-4e3e-b861-081e05cd80f2")
    @Override
    public Object caseMessageEvent(org.eclipse.uml2.uml.MessageEvent inputMessageEvent) {
        return super.caseMessageEvent(inputMessageEvent);
    }

    @objid ("982ffa97-9ac6-4635-95df-6d4353077b35")
    @Override
    public Object caseMessageOccurrenceSpecification(org.eclipse.uml2.uml.MessageOccurrenceSpecification inputMessageOccurrenceSpecification) {
        Object theResult = this.visitorMap.get(inputMessageOccurrenceSpecification);
        if (theResult == null) {
            this.visitorMap.put(inputMessageOccurrenceSpecification,
                    inputMessageOccurrenceSpecification);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("MessageOccurrenceSpecificationImpl".equals(theResult
                        .getClass().getSimpleName()))) {
            this.behavior
            .visitMessageOccurrenceSpecification(inputMessageOccurrenceSpecification);
            theResult = super
            .caseMessageOccurrenceSpecification(inputMessageOccurrenceSpecification);
        }
        return theResult;
    }

    @objid ("6a0d69c0-8e2e-4b6a-ae77-a044da1272a2")
    @Override
    public Object caseModel(org.eclipse.uml2.uml.Model inputModel) {
        Object theResult = this.visitorMap.get(inputModel);
        if (theResult == null) {
            this.visitorMap.put(inputModel, inputModel);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ModelImpl".equals(theResult.getClass().getSimpleName()))) {
            this.behavior.visitModel(inputModel);
            // ReverseProperties revProp = ReverseProperties.getInstance();
            // ModelImpl rootMdl = revProp.getEcoreModel();
            // if (rootMdl.getName().equals(inputModel.getName()))
            theResult = super.caseModel(inputModel);
        }
        return theResult;
    }

    @objid ("415f6f38-babb-421a-b8b7-4d4399c90bdf")
    @Override
    public Object caseMultiplicityElement(org.eclipse.uml2.uml.MultiplicityElement inputMultiplicityElement) {
        Object theResult = super
        .caseMultiplicityElement(inputMultiplicityElement);
        this.doSwitch(inputMultiplicityElement.getUpperValue());
        this.doSwitch(inputMultiplicityElement.getLowerValue());
        return theResult;
    }

    @objid ("53657a3a-6b43-472d-9b92-7a3d6b88c08f")
    @Override
    public Object caseNamedElement(org.eclipse.uml2.uml.NamedElement inputNamedElement) {
        Object theResult = super.caseNamedElement(inputNamedElement);
        for (EObject clientDependency : inputNamedElement
                .getClientDependencies()) {
            this.doSwitch(clientDependency);
        }
        this.doSwitch(inputNamedElement.getNamespace());
        this.doSwitch(inputNamedElement.getNameExpression());
        return theResult;
    }

    @objid ("952e7609-0379-4979-bf4c-32b55a7239ba")
    @Override
    public Object caseNamespace(org.eclipse.uml2.uml.Namespace inputNamespace) {
        Object theResult = super.caseNamespace(inputNamespace);
        for (EObject elementImport : inputNamespace.getElementImports()) {
            this.doSwitch(elementImport);
        }
        for (EObject packageImport : inputNamespace.getPackageImports()) {
            this.doSwitch(packageImport);
        }
        for (EObject ownedRule : inputNamespace.getOwnedRules()) {
            this.doSwitch(ownedRule);
        }
        for (EObject member : inputNamespace.getMembers()) {
            this.doSwitch(member);
        }
        for (EObject importedMember : inputNamespace.getImportedMembers()) {
            this.doSwitch(importedMember);
        }
        for (EObject ownedMember : inputNamespace.getOwnedMembers()) {
            this.doSwitch(ownedMember);
        }
        return theResult;
    }

    @objid ("25a7ba45-79db-40ef-9228-3d0e204ba2c2")
    @Override
    public Object caseNode(org.eclipse.uml2.uml.Node inputNode) {
        Object theResult = this.visitorMap.get(inputNode);
        if (theResult == null) {
            this.visitorMap.put(inputNode, inputNode);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("NodeImpl".equals(theResult.getClass().getSimpleName()))) {
            this.behavior.visitNode(inputNode);
            theResult = super.caseNode(inputNode);
            for (EObject nestedNode : inputNode.getNestedNodes()) {
                this.doSwitch(nestedNode);
            }
        }
        return theResult;
    }

    @objid ("79fcb74d-17fa-4884-9db7-141d400f1e5c")
    @Override
    public Object caseObjectFlow(ObjectFlow inputObjectFlow) {
        Object theResult = this.visitorMap.get(inputObjectFlow);
        if (theResult == null) {
            this.visitorMap.put(inputObjectFlow, inputObjectFlow);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ObjectFlowImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitObjectFlow(inputObjectFlow);
            theResult = super.caseObjectFlow(inputObjectFlow);
            this.doSwitch(inputObjectFlow.getTransformation());
            this.doSwitch(inputObjectFlow.getSelection());
        }
        return theResult;
    }

    @objid ("00e92a07-9366-4d73-bffa-1c4081610945")
    @Override
    public Object caseObjectNode(ObjectNode inputObjectNode) {
        Object theResult = super.caseObjectNode(inputObjectNode);
        this.doSwitch(inputObjectNode.getUpperBound());
        for (EObject inState : inputObjectNode.getInStates()) {
            this.doSwitch(inState);
        }
        this.doSwitch(inputObjectNode.getSelection());
        return theResult;
    }

    @objid ("b6b38257-ad75-47cb-a99a-5df9f9e91d40")
    @Override
    public Object caseObservation(final Observation inputIservation) {
        return super.caseObservation(inputIservation);
    }

    @objid ("83bfe7d1-b55b-4003-a48c-2694f2c0bf87")
    @Override
    public Object caseOccurrenceSpecification(org.eclipse.uml2.uml.OccurrenceSpecification inputOccurrenceSpecification) {
        Object theResult = this.visitorMap.get(inputOccurrenceSpecification);
        if (theResult == null) {
            this.visitorMap.put(inputOccurrenceSpecification,
                    inputOccurrenceSpecification);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("OccurrenceSpecificationImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitOccurrenceSpecification(inputOccurrenceSpecification);
            theResult = super
            .caseOccurrenceSpecification(inputOccurrenceSpecification);
            // this.doSwitch((EObject)inputOccurrenceSpecification.getCovered());
            for (EObject covered : inputOccurrenceSpecification.getCovereds()) {
                this.doSwitch(covered);
            }
            for (EObject toAfter : inputOccurrenceSpecification.getToAfters()) {
                this.doSwitch(toAfter);
            }
            for (EObject toBefore : inputOccurrenceSpecification.getToBefores()) {
                this.doSwitch(toBefore);
            }
        //            this.doSwitch(inputOccurrenceSpecification.getEvent());
        }
        return theResult;
    }

    @objid ("9184a6c9-ac67-4b60-a6dd-cf60bd1ea4bf")
    @Override
    public Object caseOpaqueAction(org.eclipse.uml2.uml.OpaqueAction inputOpaqueAction) {
        Object theResult = this.visitorMap.get(inputOpaqueAction);
        if (theResult == null) {
            this.visitorMap.put(inputOpaqueAction, inputOpaqueAction);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("OpaqueActionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitOpaqueAction(inputOpaqueAction);
            theResult = super.caseOpaqueAction(inputOpaqueAction);
            for (EObject inputValue : inputOpaqueAction.getInputValues()) {
                this.doSwitch(inputValue);
            }
            for (EObject outputValue : inputOpaqueAction.getOutputValues()) {
                this.doSwitch(outputValue);
            }
        }
        return theResult;
    }

    @objid ("64910ea2-4e3a-4867-a685-c9302b9001f0")
    @Override
    public Object caseOpaqueBehavior(org.eclipse.uml2.uml.OpaqueBehavior inputOpaqueBehavior) {
        Object theResult = this.visitorMap.get(inputOpaqueBehavior);
        if (theResult == null) {
            this.visitorMap.put(inputOpaqueBehavior, inputOpaqueBehavior);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("OpaqueBehaviorImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitOpaqueBehavior(inputOpaqueBehavior);
            theResult = super.caseOpaqueBehavior(inputOpaqueBehavior);
        }
        return theResult;
    }

    @objid ("f977cf2c-99cc-460e-aaa2-90a27c79b23d")
    @Override
    public Object caseOpaqueExpression(org.eclipse.uml2.uml.OpaqueExpression inputOpaqueExpression) {
        Object theResult = this.visitorMap.get(inputOpaqueExpression);
        if (theResult == null) {
            this.visitorMap.put(inputOpaqueExpression, inputOpaqueExpression);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("OpaqueExpressionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitOpaqueExpression(inputOpaqueExpression);
            theResult = super.caseOpaqueExpression(inputOpaqueExpression);
            this.doSwitch(inputOpaqueExpression.getResult());
            this.doSwitch(inputOpaqueExpression.getBehavior());
        }
        return theResult;
    }

    @objid ("33a7c4ea-21a8-4d47-8345-5926dc5422a4")
    @Override
    public Object caseOperation(org.eclipse.uml2.uml.Operation inputOperation) {
        Object theResult = this.visitorMap.get(inputOperation);
        if (theResult == null) {
            this.visitorMap.put(inputOperation, inputOperation);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("OperationImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitOperation(inputOperation);
            theResult = super.caseOperation(inputOperation);
            this.doSwitch(inputOperation.getClass_());
            for (EObject precondition : inputOperation.getPreconditions()) {
                this.doSwitch(precondition);
            }
            for (EObject postcondition : inputOperation.getPostconditions()) {
                this.doSwitch(postcondition);
            }
            for (EObject redefinedOperation : inputOperation
                    .getRedefinedOperations()) {
                this.doSwitch(redefinedOperation);
            }
            this.doSwitch(inputOperation.getDatatype());
            this.doSwitch(inputOperation.getBodyCondition());
            this.doSwitch(inputOperation.getType());
            for (EObject ownedParameter : inputOperation.getOwnedParameters()) {
                this.doSwitch(ownedParameter);
            }
            for (EObject raisedException : inputOperation.getRaisedExceptions()) {
                this.doSwitch(raisedException);
            }
            this.doSwitch(inputOperation.getInterface());
            this.doSwitch(inputOperation.getTemplateParameter());
        }
        return theResult;
    }

    @objid ("9ad60327-ac84-451a-a87e-2a767db1b463")
    @Override
    public Object caseOperationTemplateParameter(org.eclipse.uml2.uml.OperationTemplateParameter inputOperationTemplateParameter) {
        Object theResult = this.visitorMap.get(inputOperationTemplateParameter);
        if (theResult == null) {
            this.visitorMap.put(inputOperationTemplateParameter,
                    inputOperationTemplateParameter);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("OperationTemplateParameterImpl".equals(theResult
                        .getClass().getSimpleName()))) {
            this.behavior
            .visitOperationTemplateParameter(inputOperationTemplateParameter);
            theResult = super
            .caseOperationTemplateParameter(inputOperationTemplateParameter);
            this.doSwitch(inputOperationTemplateParameter
                    .getParameteredElement());
        }
        return theResult;
    }

    @objid ("c2ed6a4d-d71c-4e3b-b33c-7baa6088517f")
    @Override
    public Object caseOutputPin(org.eclipse.uml2.uml.OutputPin inputOutputPin) {
        Object theResult = this.visitorMap.get(inputOutputPin);
        if (theResult == null) {
            this.visitorMap.put(inputOutputPin, inputOutputPin);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("OutputPinImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitOutputPin(inputOutputPin);
            theResult = super.caseOutputPin(inputOutputPin);
        }
        return theResult;
    }

    @objid ("05f471a9-f8a2-47f5-bcbd-ef4d9cef1dae")
    @Override
    public Object casePackage(org.eclipse.uml2.uml.Package inputPackage) {
        Object theResult = this.visitorMap.get(inputPackage);
        if (theResult == null) {
            this.visitorMap.put(inputPackage, inputPackage);
        }
        
        if (inputPackage instanceof org.eclipse.uml2.uml.Model)
            return theResult;
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("PackageImpl".equals(theResult.getClass().getSimpleName()))) {
            this.behavior.visitPackage(inputPackage);
            theResult = super.casePackage(inputPackage);
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
            this.doSwitch(inputPackage.getNestingPackage());
            for (EObject profileApplication : inputPackage
                    .getProfileApplications()) {
                this.doSwitch(profileApplication);
            }
        }
        return theResult;
    }

    @objid ("0ebd3b34-a3ea-4de8-94ad-4833048ea02e")
    @Override
    public Object casePackageImport(org.eclipse.uml2.uml.PackageImport inputPackageImport) {
        Object theResult = this.visitorMap.get(inputPackageImport);
        if (theResult == null) {
            this.visitorMap.put(inputPackageImport, inputPackageImport);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("PackageImportImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitPackageImport(inputPackageImport);
            theResult = super.casePackageImport(inputPackageImport);
            this.doSwitch(inputPackageImport.getImportingNamespace());
            this.doSwitch(inputPackageImport.getImportedPackage());
        }
        return theResult;
    }

    @objid ("6a6bf618-d193-45fc-b4f1-4c86a6710797")
    @Override
    public Object casePackageMerge(org.eclipse.uml2.uml.PackageMerge inputPackageMerge) {
        Object theResult = this.visitorMap.get(inputPackageMerge);
        if (theResult == null) {
            this.visitorMap.put(inputPackageMerge, inputPackageMerge);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("PackageMergeImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitPackageMerge(inputPackageMerge);
            theResult = super.casePackageMerge(inputPackageMerge);
            this.doSwitch(inputPackageMerge.getReceivingPackage());
            this.doSwitch(inputPackageMerge.getMergedPackage());
        }
        return theResult;
    }

    @objid ("5eeaa49c-efc4-4efe-b5a9-b48955fd37be")
    @Override
    public Object casePackageableElement(org.eclipse.uml2.uml.PackageableElement inputPackageableElement) {
        Object theResult = super
        .casePackageableElement(inputPackageableElement);
        return theResult;
    }

    @objid ("cdef8f98-3c19-4dab-a1fe-8b2a11ca5dce")
    @Override
    public Object caseParameter(org.eclipse.uml2.uml.Parameter inputParameter) {
        Object theResult = this.visitorMap.get(inputParameter);
        if (theResult == null) {
            this.visitorMap.put(inputParameter, inputParameter);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ParameterImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitParameter(inputParameter);
            theResult = super.caseParameter(inputParameter);
            this.doSwitch(inputParameter.getDefaultValue());
            this.doSwitch(inputParameter.getOperation());
            for (EObject parameterSet : inputParameter.getParameterSets()) {
                this.doSwitch(parameterSet);
            }
        }
        return theResult;
    }

    @objid ("c66a24b3-08d4-43ce-a062-fdf16d76134f")
    @Override
    public Object caseParameterSet(org.eclipse.uml2.uml.ParameterSet inputParameterSet) {
        Object theResult = this.visitorMap.get(inputParameterSet);
        if (theResult == null) {
            this.visitorMap.put(inputParameterSet, inputParameterSet);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ParameterSetImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitParameterSet(inputParameterSet);
            theResult = super.caseParameterSet(inputParameterSet);
            for (EObject parameter : inputParameterSet.getParameters()) {
                this.doSwitch(parameter);
            }
            for (EObject condition : inputParameterSet.getConditions()) {
                this.doSwitch(condition);
            }
        }
        return theResult;
    }

    @objid ("92d0ef3a-d8d7-4d09-a7e7-cde9e03a4ce2")
    @Override
    public Object caseParameterableElement(org.eclipse.uml2.uml.ParameterableElement inputParameterableElement) {
        Object theResult = super
        .caseParameterableElement(inputParameterableElement);
        this.doSwitch(inputParameterableElement
                .getTemplateParameter());
        this.doSwitch(inputParameterableElement
                .getOwningTemplateParameter());
        return theResult;
    }

    @objid ("d85ffbac-35a6-4974-90e9-d8d7c051ba51")
    @Override
    public Object casePartDecomposition(org.eclipse.uml2.uml.PartDecomposition inputPartDecomposition) {
        Object theResult = this.visitorMap.get(inputPartDecomposition);
        if (theResult == null) {
            this.visitorMap.put(inputPartDecomposition, inputPartDecomposition);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("PartDecompositionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitPartDecomposition(inputPartDecomposition);
            theResult = super.casePartDecomposition(inputPartDecomposition);
        }
        return theResult;
    }

    @objid ("ae4e22fe-f930-41f1-81a6-16200473628e")
    @Override
    public Object casePin(org.eclipse.uml2.uml.Pin inputPin) {
        Object theResult = this.visitorMap.get(inputPin);
        if (theResult == null) {
            this.visitorMap.put(inputPin, inputPin);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("PinImpl".equals(theResult.getClass().getSimpleName()))) {
            this.behavior.visitPin(inputPin);
            theResult = super.casePin(inputPin);
        }
        return theResult;
    }

    @objid ("0f199e3f-b58e-4640-af03-ec495aab0420")
    @Override
    public Object casePort(org.eclipse.uml2.uml.Port inputPort) {
        Object theResult = this.visitorMap.get(inputPort);
        if (theResult == null) {
            this.visitorMap.put(inputPort, inputPort);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("PortImpl".equals(theResult.getClass().getSimpleName()))) {
            this.behavior.visitPort(inputPort);
            theResult = super.casePort(inputPort);
            for (EObject required : inputPort.getRequireds()) {
                this.doSwitch(required);
            }
            for (EObject redefinedPort : inputPort.getRedefinedPorts()) {
                this.doSwitch(redefinedPort);
            }
            for (EObject provided : inputPort.getProvideds()) {
                this.doSwitch(provided);
            }
            this.doSwitch(inputPort.getProtocol());
        }
        return theResult;
    }

    @objid ("7a98282c-17d8-4916-b531-dac280af7692")
    @Override
    public Object casePrimitiveType(org.eclipse.uml2.uml.PrimitiveType inputPrimitiveType) {
        Object theResult = this.visitorMap.get(inputPrimitiveType);
        if (theResult == null) {
            this.visitorMap.put(inputPrimitiveType, inputPrimitiveType);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("PrimitiveTypeImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitPrimitiveType(inputPrimitiveType);
            theResult = super.casePrimitiveType(inputPrimitiveType);
        }
        return theResult;
    }

    @objid ("3227f8b9-d078-4026-9e80-7761f541e34d")
    @Override
    public Object caseProfile(org.eclipse.uml2.uml.Profile inputProfile) {
        Object theResult = this.visitorMap.get(inputProfile);
        if (theResult == null) {
            this.visitorMap.put(inputProfile, inputProfile);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ProfileImpl".equals(theResult.getClass().getSimpleName()))) {
            this.behavior.visitProfile(inputProfile);
            theResult = super.caseProfile(inputProfile);
            for (EObject ownedStereotype : inputProfile.getOwnedStereotypes()) {
                this.doSwitch(ownedStereotype);
            }
            for (EObject metaclassReference : inputProfile
                    .getMetaclassReferences()) {
                this.doSwitch(metaclassReference);
            }
            for (EObject metamodelReference : inputProfile
                    .getMetamodelReferences()) {
                this.doSwitch(metamodelReference);
            }
        }
        return theResult;
    }

    @objid ("e1b868ea-f177-4e73-8d70-b1567fcfbd92")
    @Override
    public Object caseProfileApplication(org.eclipse.uml2.uml.ProfileApplication inputProfileApplication) {
        Object theResult = this.visitorMap.get(inputProfileApplication);
        if (theResult == null) {
            this.visitorMap.put(inputProfileApplication, inputProfileApplication);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ProfileApplicationImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitProfileApplication(inputProfileApplication);
            theResult = super.caseProfileApplication(inputProfileApplication);
            this
            .doSwitch(inputProfileApplication
                    .getAppliedProfile());
            this.doSwitch(inputProfileApplication
                    .getApplyingPackage());
        }
        return theResult;
    }

    @objid ("49f7bdea-0988-4fbe-ab9c-80d6cfebdb0e")
    @Override
    public Object caseProperty(Property inputProperty) {
        Object theResult = this.visitorMap.get(inputProperty);
        if (theResult == null) {
            this.visitorMap.put(inputProperty, inputProperty);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("PropertyImpl"
                        .equals(theResult.getClass().getSimpleName()))) {
            this.behavior.visitProperty(inputProperty);
            theResult = super.caseProperty(inputProperty);
            this.doSwitch(inputProperty.getClass_());
            for (EObject redefinedProperty : inputProperty
                    .getRedefinedProperties()) {
                this.doSwitch(redefinedProperty);
            }
            this.doSwitch(inputProperty.getOwningAssociation());
            this.doSwitch(inputProperty.getDatatype());
            this.doSwitch(inputProperty.getDefaultValue());
            this.doSwitch(inputProperty.getOpposite());
            for (EObject subsettedProperty : inputProperty
                    .getSubsettedProperties()) {
                this.doSwitch(subsettedProperty);
            }
            this.doSwitch(inputProperty.getAssociation());
            for (EObject qualifier : inputProperty.getQualifiers()) {
                this.doSwitch(qualifier);
            }
            this.doSwitch(inputProperty.getAssociationEnd());
        }
        return theResult;
    }

    @objid ("edd1bf8a-de44-4185-b7f7-92b801eb4118")
    @Override
    public Object caseProtocolConformance(org.eclipse.uml2.uml.ProtocolConformance inputProtocolConformance) {
        Object theResult = this.visitorMap.get(inputProtocolConformance);
        if (theResult == null) {
            this.visitorMap.put(inputProtocolConformance, inputProtocolConformance);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ProtocolConformanceImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitProtocolConformance(inputProtocolConformance);
            theResult = super.caseProtocolConformance(inputProtocolConformance);
            this.doSwitch(inputProtocolConformance
                    .getSpecificMachine());
            this.doSwitch(inputProtocolConformance
                    .getGeneralMachine());
        }
        return theResult;
    }

    @objid ("a983730c-2192-4269-87be-8989aed06001")
    @Override
    public Object caseProtocolStateMachine(org.eclipse.uml2.uml.ProtocolStateMachine inputProtocolStateMachine) {
        Object theResult = this.visitorMap.get(inputProtocolStateMachine);
        if (theResult == null) {
            this.visitorMap
            .put(inputProtocolStateMachine, inputProtocolStateMachine);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ProtocolStateMachineImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitProtocolStateMachine(inputProtocolStateMachine);
            theResult = super
            .caseProtocolStateMachine(inputProtocolStateMachine);
            for (EObject conformance : inputProtocolStateMachine
                    .getConformances()) {
                this.doSwitch(conformance);
            }
        }
        return theResult;
    }

    @objid ("ace397b0-81b2-4460-b3eb-fe6f2d2e4836")
    @Override
    public Object caseProtocolTransition(org.eclipse.uml2.uml.ProtocolTransition inputProtocolTransition) {
        Object theResult = this.visitorMap.get(inputProtocolTransition);
        if (theResult == null) {
            this.visitorMap.put(inputProtocolTransition, inputProtocolTransition);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ProtocolTransitionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitProtocolTransition(inputProtocolTransition);
            theResult = super.caseProtocolTransition(inputProtocolTransition);
            this.doSwitch(inputProtocolTransition.getPostCondition());
            for (EObject referred : inputProtocolTransition.getReferreds()) {
                this.doSwitch(referred);
            }
            this.doSwitch(inputProtocolTransition.getPreCondition());
        }
        return theResult;
    }

    @objid ("c5f7df74-98ca-4470-abf7-caa7ea1f6085")
    @Override
    public Object casePseudostate(org.eclipse.uml2.uml.Pseudostate inputPseudostate) {
        Object theResult = this.visitorMap.get(inputPseudostate);
        if (theResult == null) {
            this.visitorMap.put(inputPseudostate, inputPseudostate);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("PseudostateImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitPseudostate(inputPseudostate);
            theResult = super.casePseudostate(inputPseudostate);
            this.doSwitch(inputPseudostate.getStateMachine());
            this.doSwitch(inputPseudostate.getState());
        }
        return theResult;
    }

    @objid ("645e4a29-6beb-4543-a288-367aadeb2eca")
    @Override
    public Object caseQualifierValue(org.eclipse.uml2.uml.QualifierValue inputQualifierValue) {
        Object theResult = this.visitorMap.get(inputQualifierValue);
        if (theResult == null) {
            this.visitorMap.put(inputQualifierValue, inputQualifierValue);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("QualifierValueImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitQualifierValue(inputQualifierValue);
            theResult = super.caseQualifierValue(inputQualifierValue);
            this.doSwitch(inputQualifierValue.getQualifier());
            this.doSwitch(inputQualifierValue.getValue());
        }
        return theResult;
    }

    @objid ("dbe2a7ef-1858-4146-a5b9-949cce2e54e5")
    @Override
    public Object caseRaiseExceptionAction(org.eclipse.uml2.uml.RaiseExceptionAction inputRaiseExceptionAction) {
        Object theResult = this.visitorMap.get(inputRaiseExceptionAction);
        if (theResult == null) {
            this.visitorMap
            .put(inputRaiseExceptionAction, inputRaiseExceptionAction);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("RaiseExceptionActionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitRaiseExceptionAction(inputRaiseExceptionAction);
            theResult = super
            .caseRaiseExceptionAction(inputRaiseExceptionAction);
            this.doSwitch(inputRaiseExceptionAction.getException());
        }
        return theResult;
    }

    @objid ("38ab5bf9-fdca-4df9-a356-e17b0468ebd7")
    @Override
    public Object caseReadExtentAction(org.eclipse.uml2.uml.ReadExtentAction inputReadExtentAction) {
        Object theResult = this.visitorMap.get(inputReadExtentAction);
        if (theResult == null) {
            this.visitorMap.put(inputReadExtentAction, inputReadExtentAction);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ReadExtentActionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitReadExtentAction(inputReadExtentAction);
            theResult = super.caseReadExtentAction(inputReadExtentAction);
            this.doSwitch(inputReadExtentAction.getResult());
            this.doSwitch(inputReadExtentAction.getClassifier());
        }
        return theResult;
    }

    @objid ("0a5fe7ba-5b70-4f49-9229-182de32c758c")
    @Override
    public Object caseReadIsClassifiedObjectAction(org.eclipse.uml2.uml.ReadIsClassifiedObjectAction inputReadIsClassifiedObjectAction) {
        Object theResult = this.visitorMap.get(inputReadIsClassifiedObjectAction);
        if (theResult == null) {
            this.visitorMap.put(inputReadIsClassifiedObjectAction,
                    inputReadIsClassifiedObjectAction);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ReadIsClassifiedObjectActionImpl".equals(theResult
                        .getClass().getSimpleName()))) {
            this.behavior
            .visitReadIsClassifiedObjectAction(inputReadIsClassifiedObjectAction);
            theResult = super
            .caseReadIsClassifiedObjectAction(inputReadIsClassifiedObjectAction);
            this.doSwitch(inputReadIsClassifiedObjectAction
                    .getClassifier());
            this.doSwitch(inputReadIsClassifiedObjectAction
                    .getResult());
            this.doSwitch(inputReadIsClassifiedObjectAction
                    .getObject());
        }
        return theResult;
    }

    @objid ("cbd408fa-12c7-4505-b336-c52c6d71f187")
    @Override
    public Object caseReadLinkAction(org.eclipse.uml2.uml.ReadLinkAction inputReadLinkAction) {
        Object theResult = this.visitorMap.get(inputReadLinkAction);
        if (theResult == null) {
            this.visitorMap.put(inputReadLinkAction, inputReadLinkAction);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ReadLinkActionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitReadLinkAction(inputReadLinkAction);
            theResult = super.caseReadLinkAction(inputReadLinkAction);
            this.doSwitch(inputReadLinkAction.getResult());
        }
        return theResult;
    }

    @objid ("cfa7e3fa-91a1-4c32-9402-c1b552628720")
    @Override
    public Object caseReadLinkObjectEndAction(org.eclipse.uml2.uml.ReadLinkObjectEndAction inputReadLinkObjectEndAction) {
        Object theResult = this.visitorMap.get(inputReadLinkObjectEndAction);
        if (theResult == null) {
            this.visitorMap.put(inputReadLinkObjectEndAction,
                    inputReadLinkObjectEndAction);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ReadLinkObjectEndActionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitReadLinkObjectEndAction(inputReadLinkObjectEndAction);
            theResult = super
            .caseReadLinkObjectEndAction(inputReadLinkObjectEndAction);
            this.doSwitch(inputReadLinkObjectEndAction.getObject());
            this.doSwitch(inputReadLinkObjectEndAction.getEnd());
            this.doSwitch(inputReadLinkObjectEndAction.getResult());
        }
        return theResult;
    }

    @objid ("1f980b40-6398-4969-9b37-0d715e10ce15")
    @Override
    public Object caseReadLinkObjectEndQualifierAction(org.eclipse.uml2.uml.ReadLinkObjectEndQualifierAction inputReadLinkObjectEndQualifierAction) {
        Object theResult = this.visitorMap
        .get(inputReadLinkObjectEndQualifierAction);
        if (theResult == null) {
            this.visitorMap.put(inputReadLinkObjectEndQualifierAction,
                    inputReadLinkObjectEndQualifierAction);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ReadLinkObjectEndQualifierActionImpl".equals(theResult
                        .getClass().getSimpleName()))) {
            this.behavior
            .visitReadLinkObjectEndQualifierAction(inputReadLinkObjectEndQualifierAction);
            theResult = super
            .caseReadLinkObjectEndQualifierAction(inputReadLinkObjectEndQualifierAction);
            this.doSwitch(inputReadLinkObjectEndQualifierAction
                    .getObject());
            this.doSwitch(inputReadLinkObjectEndQualifierAction
                    .getResult());
            this.doSwitch(inputReadLinkObjectEndQualifierAction
                    .getQualifier());
        }
        return theResult;
    }

    @objid ("bd1df0c8-7f42-482f-af0f-86cc49042449")
    @Override
    public Object caseReadSelfAction(org.eclipse.uml2.uml.ReadSelfAction inputReadSelfAction) {
        Object theResult = this.visitorMap.get(inputReadSelfAction);
        if (theResult == null) {
            this.visitorMap.put(inputReadSelfAction, inputReadSelfAction);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ReadSelfActionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitReadSelfAction(inputReadSelfAction);
            theResult = super.caseReadSelfAction(inputReadSelfAction);
            this.doSwitch(inputReadSelfAction.getResult());
        }
        return theResult;
    }

    @objid ("7f883797-3f47-4782-ae5a-768069f9e2b3")
    @Override
    public Object caseReadStructuralFeatureAction(org.eclipse.uml2.uml.ReadStructuralFeatureAction inputReadStructuralFeatureAction) {
        Object theResult = this.visitorMap.get(inputReadStructuralFeatureAction);
        if (theResult == null) {
            this.visitorMap.put(inputReadStructuralFeatureAction,
                    inputReadStructuralFeatureAction);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ReadStructuralFeatureActionImpl".equals(theResult
                        .getClass().getSimpleName()))) {
            this.behavior
            .visitReadStructuralFeatureAction(inputReadStructuralFeatureAction);
            theResult = super
            .caseReadStructuralFeatureAction(inputReadStructuralFeatureAction);
            this.doSwitch(inputReadStructuralFeatureAction
                    .getResult());
        }
        return theResult;
    }

    @objid ("f83acdfd-9ebb-4295-8660-e120d7e2b908")
    @Override
    public Object caseReadVariableAction(org.eclipse.uml2.uml.ReadVariableAction inputReadVariableAction) {
        Object theResult = this.visitorMap.get(inputReadVariableAction);
        if (theResult == null) {
            this.visitorMap.put(inputReadVariableAction, inputReadVariableAction);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ReadVariableActionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitReadVariableAction(inputReadVariableAction);
            theResult = super.caseReadVariableAction(inputReadVariableAction);
            this.doSwitch(inputReadVariableAction.getResult());
        }
        return theResult;
    }

    @objid ("25cc75f9-424d-4864-aaf9-5300740c82b8")
    @Override
    public Object caseRealization(org.eclipse.uml2.uml.Realization inputRealization) {
        Object theResult = this.visitorMap.get(inputRealization);
        if (theResult == null) {
            this.visitorMap.put(inputRealization, inputRealization);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("RealizationImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitRealization(inputRealization);
            theResult = super.caseRealization(inputRealization);
        }
        return theResult;
    }

    @objid ("d6268e3a-943f-4073-a96c-f51f896adb4e")
    @Override
    public Object caseReceiveOperationEvent(org.eclipse.uml2.uml.ReceiveOperationEvent inputReceiveOperationEvent) {
        Object theResult = this.visitorMap.get(inputReceiveOperationEvent);
        if (theResult == null) {
            this.visitorMap.put(inputReceiveOperationEvent,
                    inputReceiveOperationEvent);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ReceiveOperationEventImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitReceiveOperationEvent(inputReceiveOperationEvent);
            theResult = super
            .caseReceiveOperationEvent(inputReceiveOperationEvent);
            this.doSwitch(inputReceiveOperationEvent.getOperation());
        }
        return theResult;
    }

    @objid ("209c67df-a3e4-4f42-9b13-3ca94eb8f7ad")
    @Override
    public Object caseReceiveSignalEvent(org.eclipse.uml2.uml.ReceiveSignalEvent inputReceiveSignalEvent) {
        Object theResult = this.visitorMap.get(inputReceiveSignalEvent);
        if (theResult == null) {
            this.visitorMap.put(inputReceiveSignalEvent, inputReceiveSignalEvent);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ReceiveSignalEventImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitReceiveSignalEvent(inputReceiveSignalEvent);
            theResult = super.caseReceiveSignalEvent(inputReceiveSignalEvent);
            this.doSwitch(inputReceiveSignalEvent.getSignal());
        }
        return theResult;
    }

    @objid ("7f860801-c1f1-4c72-a0ac-400e2a69d9dc")
    @Override
    public Object caseReception(org.eclipse.uml2.uml.Reception inputReception) {
        Object theResult = this.visitorMap.get(inputReception);
        if (theResult == null) {
            this.visitorMap.put(inputReception, inputReception);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ReceptionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitReception(inputReception);
            theResult = super.caseReception(inputReception);
            this.doSwitch(inputReception.getSignal());
        }
        return theResult;
    }

    @objid ("9fc136c4-1192-4106-9032-cd95b73d820a")
    @Override
    public Object caseReclassifyObjectAction(org.eclipse.uml2.uml.ReclassifyObjectAction inputReclassifyObjectAction) {
        Object theResult = this.visitorMap.get(inputReclassifyObjectAction);
        if (theResult == null) {
            this.visitorMap.put(inputReclassifyObjectAction,
                    inputReclassifyObjectAction);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ReclassifyObjectActionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitReclassifyObjectAction(inputReclassifyObjectAction);
            theResult = super
            .caseReclassifyObjectAction(inputReclassifyObjectAction);
            for (EObject oldClassifier : inputReclassifyObjectAction
                    .getOldClassifiers()) {
                this.doSwitch(oldClassifier);
            }
            for (EObject newClassifier : inputReclassifyObjectAction
                    .getNewClassifiers()) {
                this.doSwitch(newClassifier);
            }
            this.doSwitch(inputReclassifyObjectAction.getObject());
        }
        return theResult;
    }

    @objid ("aa3c5614-64b9-4241-93d4-ff6f6bf1e889")
    @Override
    public Object caseRedefinableElement(org.eclipse.uml2.uml.RedefinableElement inputRedefinableElement) {
        Object theResult = super
        .caseRedefinableElement(inputRedefinableElement);
        for (EObject redefinedElement : inputRedefinableElement
                .getRedefinedElements()) {
            this.doSwitch(redefinedElement);
        }
        for (EObject redefinitionContext : inputRedefinableElement
                .getRedefinitionContexts()) {
            this.doSwitch(redefinitionContext);
        }
        return theResult;
    }

    @objid ("4a36ac02-5c5f-4826-8b8b-48d46ba91434")
    @Override
    public Object caseRedefinableTemplateSignature(org.eclipse.uml2.uml.RedefinableTemplateSignature inputRedefinableTemplateSignature) {
        Object theResult = this.visitorMap.get(inputRedefinableTemplateSignature);
        if (theResult == null) {
            this.visitorMap.put(inputRedefinableTemplateSignature,
                    inputRedefinableTemplateSignature);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("RedefinableTemplateSignatureImpl".equals(theResult
                        .getClass().getSimpleName()))) {
            this.behavior
            .visitRedefinableTemplateSignature(inputRedefinableTemplateSignature);
            theResult = super
            .caseRedefinableTemplateSignature(inputRedefinableTemplateSignature);
            this.doSwitch(inputRedefinableTemplateSignature
                    .getClassifier());
            for (EObject extendedSignature : inputRedefinableTemplateSignature
                    .getExtendedSignatures()) {
                this.doSwitch(extendedSignature);
            }
            for (EObject inheritedParameter : inputRedefinableTemplateSignature
                    .getInheritedParameters()) {
                this.doSwitch(inheritedParameter);
            }
        }
        return theResult;
    }

    @objid ("91c58943-b5fa-4f87-b8da-ee265f9ab8c3")
    @Override
    public Object caseReduceAction(org.eclipse.uml2.uml.ReduceAction inputReduceAction) {
        Object theResult = this.visitorMap.get(inputReduceAction);
        if (theResult == null) {
            this.visitorMap.put(inputReduceAction, inputReduceAction);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ReduceActionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitReduceAction(inputReduceAction);
            theResult = super.caseReduceAction(inputReduceAction);
            this.doSwitch(inputReduceAction.getReducer());
            this.doSwitch(inputReduceAction.getResult());
            this.doSwitch(inputReduceAction.getCollection());
        }
        return theResult;
    }

    @objid ("c1540ca1-38b0-4260-9b8b-afe912779e40")
    @Override
    public Object caseRegion(org.eclipse.uml2.uml.Region inputRegion) {
        Object theResult = this.visitorMap.get(inputRegion);
        if (theResult == null) {
            this.visitorMap.put(inputRegion, inputRegion);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("RegionImpl".equals(theResult.getClass().getSimpleName()))) {
            this.behavior.visitRegion(inputRegion);
            theResult = super.caseRegion(inputRegion);
            for (EObject subvertex : inputRegion.getSubvertices()) {
                this.doSwitch(subvertex);
            }
            for (EObject transition : inputRegion.getTransitions()) {
                this.doSwitch(transition);
            }
            this.doSwitch(inputRegion.getStateMachine());
            this.doSwitch(inputRegion.getState());
            this.doSwitch(inputRegion.getExtendedRegion());
            // this.doSwitch((EObject)inputRegion.getRedefinitionContext());
            for (EObject redefinitionContext : inputRegion
                    .getRedefinitionContexts()) {
                this.doSwitch(redefinitionContext);
            }
        }
        return theResult;
    }

    @objid ("b4057f45-b611-430b-9b07-e3eaca9fbfe4")
    @Override
    public Object caseRelationship(org.eclipse.uml2.uml.Relationship inputRelationship) {
        Object theResult = super.caseRelationship(inputRelationship);
        for (EObject relatedElement : inputRelationship.getRelatedElements()) {
            this.doSwitch(relatedElement);
        }
        return theResult;
    }

    @objid ("c7708a40-b327-4844-91d7-e51cff1bb28f")
    @Override
    public Object caseRemoveStructuralFeatureValueAction(org.eclipse.uml2.uml.RemoveStructuralFeatureValueAction inputRemoveStructuralFeatureValueAction) {
        Object theResult = this.visitorMap
        .get(inputRemoveStructuralFeatureValueAction);
        if (theResult == null) {
            this.visitorMap.put(inputRemoveStructuralFeatureValueAction,
                    inputRemoveStructuralFeatureValueAction);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("RemoveStructuralFeatureValueActionImpl".equals(theResult
                        .getClass().getSimpleName()))) {
            this.behavior
            .visitRemoveStructuralFeatureValueAction(inputRemoveStructuralFeatureValueAction);
            theResult = super
            .caseRemoveStructuralFeatureValueAction(inputRemoveStructuralFeatureValueAction);
            this.doSwitch(inputRemoveStructuralFeatureValueAction
                    .getRemoveAt());
        }
        return theResult;
    }

    @objid ("55508d79-c2ed-4a77-a476-bfbf40999649")
    @Override
    public Object caseRemoveVariableValueAction(org.eclipse.uml2.uml.RemoveVariableValueAction inputRemoveVariableValueAction) {
        Object theResult = this.visitorMap.get(inputRemoveVariableValueAction);
        if (theResult == null) {
            this.visitorMap.put(inputRemoveVariableValueAction,
                    inputRemoveVariableValueAction);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("RemoveVariableValueActionImpl".equals(theResult
                        .getClass().getSimpleName()))) {
            this.behavior
            .visitRemoveVariableValueAction(inputRemoveVariableValueAction);
            theResult = super
            .caseRemoveVariableValueAction(inputRemoveVariableValueAction);
            this.doSwitch(inputRemoveVariableValueAction
                    .getRemoveAt());
        }
        return theResult;
    }

    @objid ("ceddf53d-82e7-4e47-87c9-6d3040aeb1a9")
    @Override
    public Object caseReplyAction(org.eclipse.uml2.uml.ReplyAction inputReplyAction) {
        Object theResult = this.visitorMap.get(inputReplyAction);
        if (theResult == null) {
            this.visitorMap.put(inputReplyAction, inputReplyAction);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ReplyActionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitReplyAction(inputReplyAction);
            theResult = super.caseReplyAction(inputReplyAction);
            this.doSwitch(inputReplyAction.getReplyToCall());
            this.doSwitch(inputReplyAction.getReturnInformation());
            for (EObject replyValue : inputReplyAction.getReplyValues()) {
                this.doSwitch(replyValue);
            }
        }
        return theResult;
    }

    @objid ("2989cd2b-31fc-4a9a-81f3-ca97fa8cd2a2")
    @Override
    public Object caseSendObjectAction(org.eclipse.uml2.uml.SendObjectAction inputSendObjectAction) {
        Object theResult = this.visitorMap.get(inputSendObjectAction);
        if (theResult == null) {
            this.visitorMap.put(inputSendObjectAction, inputSendObjectAction);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("SendObjectActionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitSendObjectAction(inputSendObjectAction);
            theResult = super.caseSendObjectAction(inputSendObjectAction);
            this.doSwitch(inputSendObjectAction.getTarget());
            this.doSwitch(inputSendObjectAction.getRequest());
        }
        return theResult;
    }

    @objid ("1bf80961-fb7b-42d8-8e4e-b8a5fc9c423e")
    @Override
    public Object caseSendOperationEvent(org.eclipse.uml2.uml.SendOperationEvent inputSendOperationEvent) {
        Object theResult = this.visitorMap.get(inputSendOperationEvent);
        if (theResult == null) {
            this.visitorMap.put(inputSendOperationEvent, inputSendOperationEvent);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("SendOperationEventImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitSendOperationEvent(inputSendOperationEvent);
            theResult = super.caseSendOperationEvent(inputSendOperationEvent);
            this.doSwitch(inputSendOperationEvent.getOperation());
        }
        return theResult;
    }

    @objid ("ad13892c-3a29-4c45-a445-9848bda9ae26")
    @Override
    public Object caseSendSignalAction(org.eclipse.uml2.uml.SendSignalAction inputSendSignalAction) {
        Object theResult = this.visitorMap.get(inputSendSignalAction);
        if (theResult == null) {
            this.visitorMap.put(inputSendSignalAction, inputSendSignalAction);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("SendSignalActionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitSendSignalAction(inputSendSignalAction);
            theResult = super.caseSendSignalAction(inputSendSignalAction);
            this.doSwitch(inputSendSignalAction.getTarget());
            this.doSwitch(inputSendSignalAction.getSignal());
        }
        return theResult;
    }

    @objid ("e7ce4221-1e9f-48fd-8bfd-dd5358d19096")
    @Override
    public Object caseSequenceNode(org.eclipse.uml2.uml.SequenceNode inputSequenceNode) {
        Object theResult = this.visitorMap.get(inputSequenceNode);
        if (theResult == null) {
            this.visitorMap.put(inputSequenceNode, inputSequenceNode);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("SequenceNodeImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitSequenceNode(inputSequenceNode);
            theResult = super.caseSequenceNode(inputSequenceNode);
            for (EObject executableNode : inputSequenceNode.getExecutableNodes()) {
                this.doSwitch(executableNode);
            }
        }
        return theResult;
    }

    @objid ("d4634613-4917-4c81-af62-45818097f99e")
    @Override
    public Object caseSignal(org.eclipse.uml2.uml.Signal inputSignal) {
        Object theResult = this.visitorMap.get(inputSignal);
        if (theResult == null) {
            this.visitorMap.put(inputSignal, inputSignal);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("SignalImpl".equals(theResult.getClass().getSimpleName()))) {
            this.behavior.visitSignal(inputSignal);
            theResult = super.caseSignal(inputSignal);
            for (EObject ownedAttribute : inputSignal.getOwnedAttributes()) {
                this.doSwitch(ownedAttribute);
            }
        }
        return theResult;
    }

    @objid ("2c5990dc-8d91-4ae2-be23-6684b2e4528b")
    @Override
    public Object caseSignalEvent(org.eclipse.uml2.uml.SignalEvent inputSignalEvent) {
        Object theResult = this.visitorMap.get(inputSignalEvent);
        if (theResult == null) {
            this.visitorMap.put(inputSignalEvent, inputSignalEvent);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("SignalEventImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitSignalEvent(inputSignalEvent);
            theResult = super.caseSignalEvent(inputSignalEvent);
            this.doSwitch(inputSignalEvent.getSignal());
        }
        return theResult;
    }

    @objid ("8ca75444-7d4a-4fed-9d84-8610c143937b")
    @Override
    public Object caseSlot(org.eclipse.uml2.uml.Slot inputSlot) {
        Object theResult = this.visitorMap.get(inputSlot);
        if (theResult == null) {
            this.visitorMap.put(inputSlot, inputSlot);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("SlotImpl".equals(theResult.getClass().getSimpleName()))) {
            this.behavior.visitSlot(inputSlot);
            theResult = super.caseSlot(inputSlot);
            this.doSwitch(inputSlot.getOwningInstance());
            this.doSwitch(inputSlot.getDefiningFeature());
            for (EObject value : inputSlot.getValues()) {
                this.doSwitch(value);
            }
        }
        return theResult;
    }

    @objid ("47f67a7d-b6e8-405e-917f-122b950556f8")
    @Override
    public Object caseStartClassifierBehaviorAction(org.eclipse.uml2.uml.StartClassifierBehaviorAction inputStartClassifierBehaviorAction) {
        Object theResult = this.visitorMap.get(inputStartClassifierBehaviorAction);
        if (theResult == null) {
            this.visitorMap.put(inputStartClassifierBehaviorAction,
                    inputStartClassifierBehaviorAction);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("StartClassifierBehaviorActionImpl".equals(theResult
                        .getClass().getSimpleName()))) {
            this.behavior
            .visitStartClassifierBehaviorAction(inputStartClassifierBehaviorAction);
            theResult = super
            .caseStartClassifierBehaviorAction(inputStartClassifierBehaviorAction);
            this.doSwitch(inputStartClassifierBehaviorAction
                    .getObject());
        }
        return theResult;
    }

    @objid ("f58f565d-1c12-4017-b822-f08c0b56a109")
    @Override
    public Object caseState(org.eclipse.uml2.uml.State inputState) {
        Object theResult = this.visitorMap.get(inputState);
        if (theResult == null) {
            this.visitorMap.put(inputState, inputState);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("StateImpl".equals(theResult.getClass().getSimpleName()))) {
            this.behavior.visitState(inputState);
            theResult = super.caseState(inputState);
            this.doSwitch(inputState.getSubmachine());
            for (EObject connection : inputState.getConnections()) {
                this.doSwitch(connection);
            }
            this.doSwitch(inputState.getRedefinedState());
            for (EObject region : inputState.getRegions()) {
                this.doSwitch(region);
            }
            // this.doSwitch((EObject)inputState.getRedefinitionContext());
            for (EObject redefinitionContext : inputState
                    .getRedefinitionContexts()) {
                this.doSwitch(redefinitionContext);
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
        return theResult;
    }

    @objid ("170e8d10-adde-415f-b7c2-ab3502559286")
    @Override
    public Object caseStateInvariant(org.eclipse.uml2.uml.StateInvariant inputStateInvariant) {
        Object theResult = this.visitorMap.get(inputStateInvariant);
        if (theResult == null) {
            this.visitorMap.put(inputStateInvariant, inputStateInvariant);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("StateInvariantImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitStateInvariant(inputStateInvariant);
            theResult = super.caseStateInvariant(inputStateInvariant);
            this.doSwitch(inputStateInvariant.getInvariant());
            // this.doSwitch((EObject)inputStateInvariant.getCovered());
            for (EObject covered : inputStateInvariant.getCovereds()) {
                this.doSwitch(covered);
            }
        }
        return theResult;
    }

    @objid ("029bc443-233a-44f6-9a90-9f0e4764efeb")
    @Override
    public Object caseStateMachine(org.eclipse.uml2.uml.StateMachine inputStateMachine) {
        Object theResult = this.visitorMap.get(inputStateMachine);
        if (theResult == null) {
            this.visitorMap.put(inputStateMachine, inputStateMachine);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("StateMachineImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitStateMachine(inputStateMachine);
            theResult = super.caseStateMachine(inputStateMachine);
            for (EObject region : inputStateMachine.getRegions()) {
                this.doSwitch(region);
            }
            for (EObject connectionPoint : inputStateMachine
                    .getConnectionPoints()) {
                this.doSwitch(connectionPoint);
            }
            for (EObject extendedStateMachine : inputStateMachine
                    .getExtendedStateMachines()) {
                this.doSwitch(extendedStateMachine);
            }
            for (EObject submachineState : inputStateMachine
                    .getSubmachineStates()) {
                this.doSwitch(submachineState);
            }
        }
        return theResult;
    }

    @objid ("523b8f1b-2810-41b8-8a65-08334547fd69")
    @Override
    public Object caseStereotype(org.eclipse.uml2.uml.Stereotype inputStereotype) {
        Object theResult = this.visitorMap.get(inputStereotype);
        if (theResult == null) {
            this.visitorMap.put(inputStereotype, inputStereotype);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("StereotypeImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitStereotype(inputStereotype);
            theResult = super.caseStereotype(inputStereotype);
            for (EObject icon : inputStereotype.getIcons()) {
                this.doSwitch(icon);
            }
        }
        return theResult;
    }

    @objid ("f725c931-40f6-4e3b-9f8a-64284f42ebe5")
    @Override
    public Object caseStringExpression(StringExpression inputStringExpression) {
        Object theResult = this.visitorMap.get(inputStringExpression);
        if (theResult == null) {
            this.visitorMap.put(inputStringExpression, inputStringExpression);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("StringExpressionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitStringExpression(inputStringExpression);
            theResult = super.caseStringExpression(inputStringExpression);
            for (EObject subExpression : inputStringExpression
                    .getSubExpressions()) {
                this.doSwitch(subExpression);
            }
            this
            .doSwitch(inputStringExpression
                    .getOwningExpression());
        }
        return theResult;
    }

    @objid ("75fbada4-08d7-48f6-bb44-ee07a8a1f139")
    @Override
    public Object caseStructuralFeature(org.eclipse.uml2.uml.StructuralFeature inputStructuralFeature) {
        return super.caseStructuralFeature(inputStructuralFeature);
    }

    @objid ("fff5c85c-43a2-4a91-b885-be04dda0153e")
    @Override
    public Object caseStructuralFeatureAction(org.eclipse.uml2.uml.StructuralFeatureAction inputStructuralFeatureAction) {
        Object theResult = super
        .caseStructuralFeatureAction(inputStructuralFeatureAction);
        this.doSwitch(inputStructuralFeatureAction
                .getStructuralFeature());
        this.doSwitch(inputStructuralFeatureAction.getObject());
        return theResult;
    }

    @objid ("3a31a582-7e57-425f-99fe-e7a7755bccf2")
    @Override
    public Object caseStructuredActivityNode(org.eclipse.uml2.uml.StructuredActivityNode inputStructuredActivityNode) {
        Object theResult = this.visitorMap.get(inputStructuredActivityNode);
        if (theResult == null) {
            this.visitorMap.put(inputStructuredActivityNode,
                    inputStructuredActivityNode);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("StructuredActivityNodeImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitStructuredActivityNode(inputStructuredActivityNode);
            theResult = super
            .caseStructuredActivityNode(inputStructuredActivityNode);
            for (EObject variable : inputStructuredActivityNode.getVariables()) {
                this.doSwitch(variable);
            }
            for (EObject node : inputStructuredActivityNode.getNodes()) {
                this.doSwitch(node);
            }
            this.doSwitch(inputStructuredActivityNode.getActivity());
            for (EObject edge : inputStructuredActivityNode.getEdges()) {
                this.doSwitch(edge);
            }
        }
        return theResult;
    }

    @objid ("916f202d-4ce1-40db-96d6-9bd62f01b906")
    @Override
    public Object caseStructuredClassifier(org.eclipse.uml2.uml.StructuredClassifier inputStructuredClassifier) {
        Object theResult = super
        .caseStructuredClassifier(inputStructuredClassifier);
        for (EObject ownedAttribute : inputStructuredClassifier
                .getOwnedAttributes()) {
            this.doSwitch(ownedAttribute);
        }
        for (EObject part : inputStructuredClassifier.getParts()) {
            this.doSwitch(part);
        }
        for (EObject role : inputStructuredClassifier.getRoles()) {
            this.doSwitch(role);
        }
        for (EObject ownedConnector : inputStructuredClassifier
                .getOwnedConnectors()) {
            this.doSwitch(ownedConnector);
        }
        return theResult;
    }

    @objid ("a20b63e5-d03c-4bde-a5d9-5d4f93486cb1")
    @Override
    public Object caseSubstitution(org.eclipse.uml2.uml.Substitution inputSubstitution) {
        Object theResult = this.visitorMap.get(inputSubstitution);
        if (theResult == null) {
            this.visitorMap.put(inputSubstitution, inputSubstitution);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("SubstitutionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitSubstitution(inputSubstitution);
            theResult = super.caseSubstitution(inputSubstitution);
            this.doSwitch(inputSubstitution.getContract());
            this.doSwitch(inputSubstitution
                    .getSubstitutingClassifier());
        }
        return theResult;
    }

    @objid ("a297c2ac-4d9f-4490-80c4-b0bad458e7ce")
    @Override
    public Object caseTemplateBinding(org.eclipse.uml2.uml.TemplateBinding inputTemplateBinding) {
        Object theResult = this.visitorMap.get(inputTemplateBinding);
        if (theResult == null) {
            this.visitorMap.put(inputTemplateBinding, inputTemplateBinding);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("TemplateBindingImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitTemplateBinding(inputTemplateBinding);
            theResult = super.caseTemplateBinding(inputTemplateBinding);
            this.doSwitch(inputTemplateBinding.getBoundElement());
            this.doSwitch(inputTemplateBinding.getSignature());
            for (EObject parameterSubstitution : inputTemplateBinding
                    .getParameterSubstitutions()) {
                this.doSwitch(parameterSubstitution);
            }
        }
        return theResult;
    }

    @objid ("4f13d430-ef5d-4f26-b848-d6971eddd39a")
    @Override
    public Object caseTemplateParameter(org.eclipse.uml2.uml.TemplateParameter inputTemplateParameter) {
        Object theResult = this.visitorMap.get(inputTemplateParameter);
        if (theResult == null) {
            this.visitorMap.put(inputTemplateParameter, inputTemplateParameter);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("TemplateParameterImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitTemplateParameter(inputTemplateParameter);
            theResult = super.caseTemplateParameter(inputTemplateParameter);
            this.doSwitch(inputTemplateParameter.getSignature());
            this.doSwitch(inputTemplateParameter
                    .getParameteredElement());
            this.doSwitch(inputTemplateParameter
                    .getOwnedParameteredElement());
            this.doSwitch(inputTemplateParameter.getDefault());
            this.doSwitch(inputTemplateParameter.getOwnedDefault());
        }
        return theResult;
    }

    @objid ("c5b50316-2942-4997-b2c0-cda65d9a3581")
    @Override
    public Object caseTemplateParameterSubstitution(org.eclipse.uml2.uml.TemplateParameterSubstitution inputTemplateParameterSubstitution) {
        Object theResult = this.visitorMap.get(inputTemplateParameterSubstitution);
        if (theResult == null) {
            this.visitorMap.put(inputTemplateParameterSubstitution,
                    inputTemplateParameterSubstitution);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("TemplateParameterSubstitutionImpl".equals(theResult
                        .getClass().getSimpleName()))) {
            this.behavior
            .visitTemplateParameterSubstitution(inputTemplateParameterSubstitution);
            theResult = super
            .caseTemplateParameterSubstitution(inputTemplateParameterSubstitution);
            this.doSwitch(inputTemplateParameterSubstitution
                    .getFormal());
            this.doSwitch(inputTemplateParameterSubstitution
                    .getTemplateBinding());
        
            this.doSwitch(inputTemplateParameterSubstitution.getActual());
        
            this.doSwitch( inputTemplateParameterSubstitution.getOwnedActual());
        
        }
        return theResult;
    }

    @objid ("24aa0814-fea1-44fa-b9e9-c6a5743f73c0")
    @Override
    public Object caseTemplateSignature(org.eclipse.uml2.uml.TemplateSignature inputTemplateSignature) {
        Object theResult = this.visitorMap.get(inputTemplateSignature);
        if (theResult == null) {
            this.visitorMap.put(inputTemplateSignature, inputTemplateSignature);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("TemplateSignatureImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitTemplateSignature(inputTemplateSignature);
            theResult = super.caseTemplateSignature(inputTemplateSignature);
            for (EObject parameter : inputTemplateSignature.getParameters()) {
                this.doSwitch(parameter);
            }
            for (EObject ownedParameter : inputTemplateSignature
                    .getOwnedParameters()) {
                this.doSwitch(ownedParameter);
            }
            this.doSwitch(inputTemplateSignature.getTemplate());
        }
        return theResult;
    }

    @objid ("ef2c961d-551a-404a-a551-ddbdae8c3b2e")
    @Override
    public Object caseTemplateableElement(org.eclipse.uml2.uml.TemplateableElement inputTemplateableElement) {
        Object theResult = super
        .caseTemplateableElement(inputTemplateableElement);
        for (EObject templateBinding : inputTemplateableElement
                .getTemplateBindings()) {
            this.doSwitch(templateBinding);
        }
        this.doSwitch(inputTemplateableElement
                .getOwnedTemplateSignature());
        return theResult;
    }

    @objid ("a1370792-8796-4fce-a391-ff11651714a7")
    @Override
    public Object caseTestIdentityAction(org.eclipse.uml2.uml.TestIdentityAction inputTestIdentityAction) {
        Object theResult = this.visitorMap.get(inputTestIdentityAction);
        if (theResult == null) {
            this.visitorMap.put(inputTestIdentityAction, inputTestIdentityAction);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("TestIdentityActionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitTestIdentityAction(inputTestIdentityAction);
            theResult = super.caseTestIdentityAction(inputTestIdentityAction);
            this.doSwitch(inputTestIdentityAction.getFirst());
            this.doSwitch(inputTestIdentityAction.getSecond());
            this.doSwitch(inputTestIdentityAction.getResult());
        }
        return theResult;
    }

    @objid ("84cd497f-a824-4893-8ed8-38958e643d85")
    @Override
    public Object caseTimeConstraint(org.eclipse.uml2.uml.TimeConstraint inputTimeConstraint) {
        Object theResult = this.visitorMap.get(inputTimeConstraint);
        if (theResult == null) {
            this.visitorMap.put(inputTimeConstraint, inputTimeConstraint);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("TimeConstraintImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitTimeConstraint(inputTimeConstraint);
            theResult = super.caseTimeConstraint(inputTimeConstraint);
            this.doSwitch(inputTimeConstraint.getSpecification());
        }
        return theResult;
    }

    @objid ("b84a04c8-f481-461f-ad09-f37466ffc635")
    @Override
    public Object caseTimeEvent(org.eclipse.uml2.uml.TimeEvent inputTimeEvent) {
        Object theResult = this.visitorMap.get(inputTimeEvent);
        if (theResult == null) {
            this.visitorMap.put(inputTimeEvent, inputTimeEvent);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("TimeEventImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitTimeEvent(inputTimeEvent);
            theResult = super.caseTimeEvent(inputTimeEvent);
            this.doSwitch(inputTimeEvent.getWhen());
        }
        return theResult;
    }

    @objid ("fb7eee8a-aef6-43da-9958-17662da67c1d")
    @Override
    public Object caseTimeExpression(org.eclipse.uml2.uml.TimeExpression inputTimeExpression) {
        Object theResult = this.visitorMap.get(inputTimeExpression);
        if (theResult == null) {
            this.visitorMap.put(inputTimeExpression, inputTimeExpression);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("TimeExpressionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitTimeExpression(inputTimeExpression);
            theResult = super.caseTimeExpression(inputTimeExpression);
            this.doSwitch(inputTimeExpression.getExpr());
            for (EObject observation : inputTimeExpression.getObservations()) {
                this.doSwitch(observation);
            }
        }
        return theResult;
    }

    @objid ("14feb5fa-a23d-43dc-ba06-796fa4e83d61")
    @Override
    public Object caseTimeInterval(org.eclipse.uml2.uml.TimeInterval inputTimeInterval) {
        Object theResult = this.visitorMap.get(inputTimeInterval);
        if (theResult == null) {
            this.visitorMap.put(inputTimeInterval, inputTimeInterval);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("TimeIntervalImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitTimeInterval(inputTimeInterval);
            theResult = super.caseTimeInterval(inputTimeInterval);
            this.doSwitch(inputTimeInterval.getMax());
            this.doSwitch(inputTimeInterval.getMin());
        }
        return theResult;
    }

    @objid ("c5302fcc-bf71-4bf7-b215-cf7ee4ca525a")
    @Override
    public Object caseTimeObservation(final TimeObservation inputTimeObservation) {
        Object theResult = this.visitorMap.get(inputTimeObservation);
        if (theResult == null) {
            this.visitorMap.put(inputTimeObservation, inputTimeObservation);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("TimeIservationImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitTimeObservation(inputTimeObservation);
            theResult = super.caseTimeObservation(inputTimeObservation);
            this.doSwitch(inputTimeObservation.getEvent());
        }
        return theResult;
    }

    @objid ("be55e389-828b-4ae8-a9d9-04e8c20359cf")
    @Override
    public Object caseTransition(org.eclipse.uml2.uml.Transition inputTransition) {
        Object theResult = this.visitorMap.get(inputTransition);
        if (theResult == null) {
            this.visitorMap.put(inputTransition, inputTransition);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("TransitionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitTransition(inputTransition);
            theResult = super.caseTransition(inputTransition);
            this.doSwitch(inputTransition.getContainer());
            this.doSwitch(inputTransition.getSource());
            this.doSwitch(inputTransition.getTarget());
            this.doSwitch(inputTransition.getRedefinedTransition());
            this.doSwitch(inputTransition.getGuard());
            // this.doSwitch((EObject)inputTransition.getRedefinitionContext());
            for (EObject redefinitionContext : inputTransition
                    .getRedefinitionContexts()) {
                this.doSwitch(redefinitionContext);
            }
            this.doSwitch(inputTransition.getEffect());
            for (EObject trigger : inputTransition.getTriggers()) {
                this.doSwitch(trigger);
            }
        }
        return theResult;
    }

    @objid ("781c02b9-9144-4bfc-a441-59c619058445")
    @Override
    public Object caseTrigger(org.eclipse.uml2.uml.Trigger inputTrigger) {
        Object theResult = this.visitorMap.get(inputTrigger);
        if (theResult == null) {
            this.visitorMap.put(inputTrigger, inputTrigger);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("TriggerImpl".equals(theResult.getClass().getSimpleName()))) {
            this.behavior.visitTrigger(inputTrigger);
            theResult = super.caseTrigger(inputTrigger);
            this.doSwitch(inputTrigger.getEvent());
            for (EObject port : inputTrigger.getPorts()) {
                this.doSwitch(port);
            }
        }
        return theResult;
    }

    @objid ("3b2665b7-e199-4e83-a4a5-de2f9c04f5a4")
    @Override
    public Object caseType(org.eclipse.uml2.uml.Type inputType) {
        Object theResult = super.caseType(inputType);
        this.doSwitch(inputType.getPackage());
        return theResult;
    }

    @objid ("b049297a-e2b6-48ad-9275-26696ae572e9")
    @Override
    public Object caseTypedElement(org.eclipse.uml2.uml.TypedElement inputTypedElement) {
        Object theResult = super.caseTypedElement(inputTypedElement);
        this.doSwitch(inputTypedElement.getType());
        return theResult;
    }

    @objid ("db3c461e-b3ef-4af2-ab66-87523eca6f5e")
    @Override
    public Object caseUnmarshallAction(org.eclipse.uml2.uml.UnmarshallAction inputUnmarshallAction) {
        Object theResult = this.visitorMap.get(inputUnmarshallAction);
        if (theResult == null) {
            this.visitorMap.put(inputUnmarshallAction, inputUnmarshallAction);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("UnmarshallActionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior.visitUnmarshallAction(inputUnmarshallAction);
            theResult = super.caseUnmarshallAction(inputUnmarshallAction);
            for (EObject result : inputUnmarshallAction.getResults()) {
                this.doSwitch(result);
            }
            this.doSwitch(inputUnmarshallAction.getUnmarshallType());
            this.doSwitch(inputUnmarshallAction.getObject());
        }
        return theResult;
    }

    @objid ("4873f541-2e82-45f7-845d-da2266a4791a")
    @Override
    public Object caseUsage(org.eclipse.uml2.uml.Usage inputUsage) {
        Object theResult = this.visitorMap.get(inputUsage);
        if (theResult == null) {
            this.visitorMap.put(inputUsage, inputUsage);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("UsageImpl".equals(theResult.getClass().getSimpleName()))) {
            this.behavior.visitUsage(inputUsage);
            theResult = super.caseUsage(inputUsage);
        }
        return theResult;
    }

    @objid ("83aa0fef-5518-4235-9f8b-591d3ef2c5c2")
    @Override
    public Object caseUseCase(org.eclipse.uml2.uml.UseCase inputUseCase) {
        Object theResult = this.visitorMap.get(inputUseCase);
        if (theResult == null) {
            this.visitorMap.put(inputUseCase, inputUseCase);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("UseCaseImpl".equals(theResult.getClass().getSimpleName()))) {
            this.behavior.visitUseCase(inputUseCase);
            theResult = super.caseUseCase(inputUseCase);
            for (EObject include : inputUseCase.getIncludes()) {
                this.doSwitch(include);
            }
            for (EObject extend : inputUseCase.getExtends()) {
                this.doSwitch(extend);
            }
            for (EObject extensionPoint : inputUseCase.getExtensionPoints()) {
                this.doSwitch(extensionPoint);
            }
            for (EObject subject : inputUseCase.getSubjects()) {
                this.doSwitch(subject);
            }
        }
        return theResult;
    }

    @objid ("3e660b11-7508-4f1b-99a3-868d4929bacc")
    @Override
    public Object caseValuePin(org.eclipse.uml2.uml.ValuePin inputValuePin) {
        Object theResult = this.visitorMap.get(inputValuePin);
        if (theResult == null) {
            this.visitorMap.put(inputValuePin, inputValuePin);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ValuePinImpl"
                        .equals(theResult.getClass().getSimpleName()))) {
            this.behavior.visitValuePin(inputValuePin);
            theResult = super.caseValuePin(inputValuePin);
            this.doSwitch(inputValuePin.getValue());
        }
        return theResult;
    }

    @objid ("800a2928-ee1e-4198-b86e-a1e0b02a6c32")
    @Override
    public Object caseValueSpecification(org.eclipse.uml2.uml.ValueSpecification inputValueSpecification) {
        return super.caseValueSpecification(inputValueSpecification);
    }

    @objid ("c13b29eb-3d3f-4d1d-981c-acfd8a4cffa8")
    @Override
    public Object caseValueSpecificationAction(org.eclipse.uml2.uml.ValueSpecificationAction inputValueSpecificationAction) {
        Object theResult = this.visitorMap.get(inputValueSpecificationAction);
        if (theResult == null) {
            this.visitorMap.put(inputValueSpecificationAction,
                    inputValueSpecificationAction);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("ValueSpecificationActionImpl".equals(theResult.getClass()
                        .getSimpleName()))) {
            this.behavior
            .visitValueSpecificationAction(inputValueSpecificationAction);
            theResult = super
            .caseValueSpecificationAction(inputValueSpecificationAction);
            this.doSwitch(inputValueSpecificationAction.getValue());
            this.doSwitch(inputValueSpecificationAction.getResult());
        }
        return theResult;
    }

    @objid ("8ba83915-94d5-4592-ad9d-d37ff667d293")
    @Override
    public Object caseVariable(org.eclipse.uml2.uml.Variable inputVariable) {
        Object theResult = this.visitorMap.get(inputVariable);
        if (theResult == null) {
            this.visitorMap.put(inputVariable, inputVariable);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("VariableImpl"
                        .equals(theResult.getClass().getSimpleName()))) {
            this.behavior.visitVariable(inputVariable);
            theResult = super.caseVariable(inputVariable);
            this.doSwitch(inputVariable.getScope());
            this.doSwitch(inputVariable.getActivityScope());
        }
        return theResult;
    }

    @objid ("8e864ee6-5538-4cad-81ea-0800b55aa0be")
    @Override
    public Object caseVariableAction(org.eclipse.uml2.uml.VariableAction inputVariableAction) {
        Object theResult = super.caseVariableAction(inputVariableAction);
        this.doSwitch(inputVariableAction.getVariable());
        return theResult;
    }

    @objid ("890a2c2e-ef2f-4985-8252-9e4d2aa63f3b")
    @Override
    public Object caseVertex(org.eclipse.uml2.uml.Vertex inputVertex) {
        Object theResult = super.caseVertex(inputVertex);
        this.doSwitch(inputVertex.getContainer());
        for (EObject outgoing : inputVertex.getOutgoings()) {
            this.doSwitch(outgoing);
        }
        for (EObject incoming : inputVertex.getIncomings()) {
            this.doSwitch(incoming);
        }
        return theResult;
    }

    @objid ("f9a71197-0b69-4fde-bab5-d7c540c9e888")
    @Override
    public Object caseWriteLinkAction(org.eclipse.uml2.uml.WriteLinkAction inputWriteLinkAction) {
        return super.caseWriteLinkAction(inputWriteLinkAction);
    }

    @objid ("471ecdbe-d183-4ee5-9180-5651eeefda19")
    @Override
    public Object caseWriteStructuralFeatureAction(org.eclipse.uml2.uml.WriteStructuralFeatureAction inputWriteStructuralFeatureAction) {
        Object theResult = super
        .caseWriteStructuralFeatureAction(inputWriteStructuralFeatureAction);
        this.doSwitch(inputWriteStructuralFeatureAction.getValue());
        return theResult;
    }

    @objid ("636b266a-4f04-44fe-951f-bcd6e8e0c98e")
    @Override
    public Object caseWriteVariableAction(org.eclipse.uml2.uml.WriteVariableAction inputWriteVariableAction) {
        Object theResult = super
        .caseWriteVariableAction(inputWriteVariableAction);
        this.doSwitch(inputWriteVariableAction.getValue());
        return theResult;
    }

    @objid ("bfb542ca-0b7d-4f5f-907f-9516efae4da5")
    @Override
    public Object doSwitch(EObject inputElement) {
        if (inputElement != null)
            return super.doSwitch(inputElement);
        else
            return null;
    }

    @objid ("52d6a4f4-2a29-49cd-a2c7-99807f4de4d8")
    @Override
    public Object caseStartObjectBehaviorAction(org.eclipse.uml2.uml.StartObjectBehaviorAction inputStartObjectBehaviorAction) {
        Object theResult = this.visitorMap.get(inputStartObjectBehaviorAction);
        if (theResult == null) {
            this.visitorMap.put(inputStartObjectBehaviorAction,
                    inputStartObjectBehaviorAction);
        }
        
        // In particular case of this concrete element is inherited by another
        // concrete element, it
        // shall be in the this.visitorMap. Also do call the treatment defined at the
        // current super level:
        if (theResult == null
                || !("StartObjectBehaviorActionImpl".equals(theResult
                        .getClass().getSimpleName()))) {
            this.behavior
            .visitStartObjectBehaviorAction(inputStartObjectBehaviorAction);
            theResult = super
            .caseStartObjectBehaviorAction(inputStartObjectBehaviorAction);
            this.doSwitch(inputStartObjectBehaviorAction
                    .getObject());
        }
        return theResult;
    }

}
