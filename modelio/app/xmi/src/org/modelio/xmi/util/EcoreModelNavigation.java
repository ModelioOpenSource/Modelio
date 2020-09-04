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

package org.modelio.xmi.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.ActionInputPin;
import org.eclipse.uml2.uml.ActivityPartition;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.CallAction;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.CombinedFragment;
import org.eclipse.uml2.uml.Expression;
import org.eclipse.uml2.uml.Gate;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.InstanceValue;
import org.eclipse.uml2.uml.LiteralSpecification;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.MultiplicityElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.PartDecomposition;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Slot;
import org.eclipse.uml2.uml.ValueSpecification;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.reverse.ReverseProperties;

@objid ("0b9802e6-55ba-4291-acd6-618a3eda5bf3")
public class EcoreModelNavigation {
    @objid ("37ce9e36-cd81-4898-8fc6-40d30d77056d")
    public static void attachAssocToNearestPkg(ModelTree currentElt, Association ecoreAssoc) {
        GenerationProperties genProp = GenerationProperties.getInstance();
        
        org.modelio.metamodel.uml.statik.Package nearestModelioPkg = AbstractObjingModelNavigation.getNearestPackage(currentElt);
        org.eclipse.uml2.uml.Namespace nearestEcorePkg = (org.eclipse.uml2.uml.Namespace) genProp.getMappedElement(nearestModelioPkg);
        
        if (nearestEcorePkg instanceof Package) {
            ((Package) nearestEcorePkg).getPackagedElements().add(ecoreAssoc);
        }
    }

    @objid ("bb270031-fc68-4345-8705-3cb5042b3757")
    public static org.eclipse.uml2.uml.InteractionFragment getEnclosing(org.eclipse.uml2.uml.InteractionFragment sequenceElt) {
        org.eclipse.uml2.uml.InteractionFragment enclosing = sequenceElt.getEnclosingInteraction();
        if (enclosing == null){
            enclosing = sequenceElt.getEnclosingOperand();
        }
        return enclosing;
    }

    @objid ("322c9669-d8bc-4fff-b6e2-7067d24d99ff")
    public static org.eclipse.uml2.uml.InteractionFragment getEnclosing(org.eclipse.uml2.uml.Message message) {
        org.eclipse.uml2.uml.InteractionFragment enclosing = null;
        org.eclipse.uml2.uml.MessageEnd event = null;
        event = message.getSendEvent();
        if (!(event instanceof org.eclipse.uml2.uml.MessageOccurrenceSpecification)){
            event = message.getReceiveEvent();
        }
        
        if (event instanceof org.eclipse.uml2.uml.MessageOccurrenceSpecification) {
            org.eclipse.uml2.uml.MessageOccurrenceSpecification end = (org.eclipse.uml2.uml.MessageOccurrenceSpecification) event;
            enclosing = end.getEnclosingInteraction();
            if (enclosing == null){
                enclosing = end.getEnclosingOperand();
            }
        }
        return enclosing;
    }

    @objid ("2931f726-d7bb-49d4-b3c1-2448a8f33673")
    public static org.eclipse.uml2.uml.BehaviorExecutionSpecification getFinalExecutionSpecification(org.eclipse.uml2.uml.MessageOccurrenceSpecification executionSpecification) {
        EList<?> covereds = executionSpecification.getCovereds();
        
        if (covereds.size() > 0){
            org.eclipse.uml2.uml.Lifeline lifeline = (org.eclipse.uml2.uml.Lifeline) covereds.get(0);
            EList<?> fragmentsList =  lifeline.getCoveredBys();
        
            for (Object fragment : fragmentsList){
                if (fragment instanceof org.eclipse.uml2.uml. BehaviorExecutionSpecification){
                    org.eclipse.uml2.uml. BehaviorExecutionSpecification behaviorExecutionSpecification = (org.eclipse.uml2.uml.BehaviorExecutionSpecification) fragment;
                    if (behaviorExecutionSpecification.getFinish().equals(executionSpecification)){
                        return behaviorExecutionSpecification;
                    }
                }
            }
        
        }
        return null;
    }

    @objid ("8d37f830-7378-4c05-bed4-ab754e3f9f47")
    public static Gate getFormalGate(org.eclipse.uml2.uml.Interaction referedInteraction, Gate actualGate) {
        Gate referedGate = null;
        if (referedInteraction != null) {
            String actualName = actualGate.getName();
            for (Object gate : referedInteraction.getFormalGates()) {            
                Gate formalGate = (org.eclipse.uml2.uml.Gate) gate;
                String formalName = formalGate.getName();
                if (actualName != null && formalName != null
                        && actualName.equals(formalName)) {
                    referedGate = formalGate;
                    break;
                }
            }
        }
        return referedGate;
    }

    @objid ("34be3ddb-ab46-430f-91e4-93b3a09336e9")
    public static Gate getFormalGate(org.eclipse.uml2.uml.Gate actualGate) {
        org.eclipse.uml2.uml.Element gateOwner = actualGate.getOwner();
        if (gateOwner instanceof org.eclipse.uml2.uml.InteractionUse) {
            org.eclipse.uml2.uml.Interaction refered = ((org.eclipse.uml2.uml.InteractionUse) gateOwner).getRefersTo();
            return getFormalGate(refered, actualGate);
        }
        return null;
    }

    @objid ("7795bf22-f4d0-4bdf-baae-95ad10ef3da0")
    public static org.eclipse.uml2.uml.InstanceSpecification getInstance(Slot slot) {
        InstanceSpecification result = null;
        if (slot.getValues().size() > 0) {
            ValueSpecification value = slot.getValues().get(0);
            if (value instanceof InstanceValue){
                return ((InstanceValue) value).getInstance();
            }      
        }
        return result;
    }

    @objid ("280aa4c6-f9f8-47ca-9261-9f8429f7e677")
    public static org.eclipse.uml2.uml.ActivityPartition getLeafPartition(List<ActivityPartition> ecorePartitions) {
        for (org.eclipse.uml2.uml.ActivityPartition currentPartition : ecorePartitions) {
            EList<?> subPartitions = currentPartition.getSubpartitions();
            if ((subPartitions == null) || (subPartitions.size() == 0)){
                return currentPartition;
            }
        }
        return null;
    }

    @objid ("32c1a675-c1d7-4833-979a-1f3249fbd5e2")
    public static List<Parameter> getMatchedParameters(org.eclipse.uml2.uml.Pin pin) {
        if (pin instanceof org.eclipse.uml2.uml.InputPin){
            return getMatchedParameters((org.eclipse.uml2.uml.InputPin) pin);
        }else{
            return getMatchedParameters((org.eclipse.uml2.uml.OutputPin) pin);
        }
    }

    @objid ("2dfe9914-1547-4ea4-a036-0ea77924411b")
    public static org.eclipse.uml2.uml.Interaction getMostEnclosingInteraction(org.eclipse.uml2.uml.Element ecoreIF) {
        org.eclipse.uml2.uml.Element owner = ecoreIF.getOwner();
        while (!(owner instanceof org.eclipse.uml2.uml.Interaction)){
            owner = owner.getOwner();
        }
        return (org.eclipse.uml2.uml.Interaction) owner;
    }

    @objid ("2a08cece-e674-4499-b56b-f4c0e903bf6b")
    public static org.eclipse.uml2.uml.Region getMostEnclosingRegion(org.eclipse.uml2.uml.Vertex vertex) {
        org.eclipse.uml2.uml.Region region = vertex.getContainer();
        if (region != null) {
            org.eclipse.uml2.uml.StateMachine ownerSM = region.getStateMachine();
            if (ownerSM != null){
                return region;
            }
        
            org.eclipse.uml2.uml.State ownerState = region.getState();
            if (ownerState != null){
                return getMostEnclosingRegion(ownerState);
            }
        }
        return null;
    }

    @objid ("7ac0f0be-6479-4d41-aebb-2f49e57b45eb")
    public static org.eclipse.uml2.uml.StateMachine getMostEnclosingStateMachine(org.eclipse.uml2.uml.Vertex vertex) {
        org.eclipse.uml2.uml.Region region = getMostEnclosingRegion(vertex);
        if (region != null){
            return region.getStateMachine();
        }
        return null;
    }

    @objid ("3ba1f660-ca32-4725-990b-13e360fca4ad")
    public static org.eclipse.uml2.uml.StateMachine getMostEnclosingStateMachine(org.eclipse.uml2.uml.Transition transition) {
        org.eclipse.uml2.uml.Region region = transition.getContainer();
        
        if (region != null) {
            org.eclipse.uml2.uml.StateMachine ownerSM = region.getStateMachine();
            if (ownerSM != null){
                return ownerSM;
            }
        
            org.eclipse.uml2.uml.State ownerState = region.getState();
            if (ownerState != null){
                return getMostEnclosingStateMachine(ownerState);
            }
        }
        return null;
    }

    @objid ("892c789a-60ff-4625-9606-458c8b9e9bcc")
    public static String getMultiplicityMax(final MultiplicityElement ecoreElt) {
        ValueSpecification upperValue = ecoreElt.getUpperValue();
        if (upperValue != null) {
            String multMax = upperValue.stringValue();
            if (multMax != null){
                return multMax;
            }else{
                return "0";
            }
        }else{
            return "1";
        }
    }

    @objid ("b1999565-dbdc-47d4-a52f-e2aaad5f3954")
    public static String getMultiplicityMin(final MultiplicityElement ecoreElt) {
        ValueSpecification lowerValue = ecoreElt.getLowerValue();
        if (lowerValue != null) {
            String multMin = lowerValue.stringValue();
            if (multMin != null){
                return multMin;
            }else{ 
                return "0";
            }
        }else {
            return "1";
        }
    }

    @objid ("3b970927-6b5e-4853-b452-1201691f5135")
    public static ModelTree getNearestDataTypeOwner(org.eclipse.uml2.uml.Element ecoreElt) {
        ReverseProperties revProp = ReverseProperties.getInstance();
        org.eclipse.uml2.uml.Element ecoreParent = ecoreElt;
        Element objParent = (Element) revProp.getMappedElement(ecoreParent);
        while (!((objParent instanceof org.modelio.metamodel.uml.statik.Package) 
                ||(objParent instanceof org.modelio.metamodel.uml.statik.Component)
                ||(objParent instanceof org.modelio.metamodel.uml.statik.Class)
                ||(objParent instanceof org.modelio.metamodel.uml.statik.Interface)
                ||(objParent instanceof org.modelio.metamodel.uml.behavior.commonBehaviors.Signal)) 
                && (ecoreParent.getOwner() != null)){
            ecoreParent = ecoreParent.getOwner();
            objParent = (Element) revProp.getMappedElement(ecoreParent);
        }
        
        if ((objParent instanceof org.modelio.metamodel.uml.statik.Package) 
                ||(objParent instanceof org.modelio.metamodel.uml.statik.Component)
                ||(objParent instanceof org.modelio.metamodel.uml.statik.Class)
                ||(objParent instanceof org.modelio.metamodel.uml.statik.Interface)
                ||(objParent instanceof org.modelio.metamodel.uml.behavior.commonBehaviors.Signal)){
            return (ModelTree) objParent;
        }
        return null;
    }

    @objid ("ef4655f1-52b7-4792-9c8f-8c8c68524229")
    public static ModelTree getNearestEnumerationOwner(org.eclipse.uml2.uml.Element ecoreElt) {
        ReverseProperties revProp = ReverseProperties.getInstance();
        org.eclipse.uml2.uml.Element ecoreParent = ecoreElt;
        Element objParent = (Element) revProp.getMappedElement(ecoreParent);
        
        while (!((objParent instanceof org.modelio.metamodel.uml.statik.Package) 
                ||(objParent instanceof org.modelio.metamodel.uml.statik.Component)
                ||(objParent instanceof org.modelio.metamodel.uml.statik.Class)
                ||(objParent instanceof org.modelio.metamodel.uml.statik.Interface)
                ||(objParent instanceof org.modelio.metamodel.uml.behavior.commonBehaviors.Signal)
                ||(objParent instanceof org.modelio.metamodel.uml.statik.TemplateParameter)) 
                && (ecoreParent.getOwner() != null)){
            ecoreParent = ecoreParent.getOwner();
            objParent = (Element) revProp.getMappedElement(ecoreParent);
        }
        
        if ((objParent instanceof org.modelio.metamodel.uml.statik.Package) 
                ||(objParent instanceof org.modelio.metamodel.uml.statik.Component)
                ||(objParent instanceof org.modelio.metamodel.uml.statik.Class)
                ||(objParent instanceof org.modelio.metamodel.uml.statik.Interface)
                ||(objParent instanceof org.modelio.metamodel.uml.behavior.commonBehaviors.Signal)
                ||(objParent instanceof org.modelio.metamodel.uml.statik.TemplateParameter)){
            return (ModelTree) objParent;
        }
        return null;
    }

    @objid ("b9ebe8ad-eb15-4ea2-9008-9198d74c66fc")
    public static NameSpace getNearestNameSpace(org.eclipse.uml2.uml.Element ecoreElt) {
        ReverseProperties revProp = ReverseProperties.getInstance();
        org.eclipse.uml2.uml.Element ecoreParent = ecoreElt;
        Element objParent = (Element) revProp.getMappedElement(ecoreParent);
        while (!(objParent instanceof NameSpace)  
                && (ecoreParent.getOwner() != null)){
            ecoreParent = ecoreParent.getOwner();
            objParent = (Element) revProp.getMappedElement(ecoreParent);
        }
        
        if (objParent instanceof NameSpace) {
            return (NameSpace) objParent;
        }
        return null;
    }

    @objid ("28ccabe6-03bf-4439-acb2-77a0551917b7")
    public static NameSpace getNearestOpaqueBehaviorOwner(org.eclipse.uml2.uml.Element ecoreElt) {
        ReverseProperties revProp = ReverseProperties.getInstance();
        org.eclipse.uml2.uml.Element ecoreParent = ecoreElt;
        Element objParent = (Element) revProp.getMappedElement(ecoreParent);
        
        while (!(objParent instanceof NameSpace ) 
                && (ecoreParent.getOwner() != null)){
            ecoreParent = ecoreParent.getOwner();
            objParent = (Element) revProp.getMappedElement(ecoreParent);
        }
        
        if (objParent instanceof NameSpace) {
            return (NameSpace) objParent;
        }
        return null;
    }

    @objid ("26a5246d-a95f-48c1-a70c-503a5bc9e3b9")
    public static ModelTree getNearestStateMachineOwner(org.eclipse.uml2.uml.Element ecoreElt) {
        ReverseProperties revProp = ReverseProperties.getInstance();
        org.eclipse.uml2.uml.Element ecoreParent = ecoreElt;
        Element objParent = (Element) revProp.getMappedElement(ecoreParent);
        while (!((objParent instanceof NameSpace) 
                ||(objParent instanceof Operation)) 
                && (ecoreParent.getOwner() != null)){
            ecoreParent = ecoreParent.getOwner();
            objParent = (Element) revProp.getMappedElement(ecoreParent);
        }
        
        if ((objParent instanceof NameSpace) 
                ||(objParent instanceof Operation)){
            return (ModelTree) objParent;
        }
        return null;
    }

    @objid ("cf417ea7-562f-44ce-8624-90364de2af81")
    public static org.eclipse.uml2.uml.InteractionFragment getNextFragmentInInteraction(org.eclipse.uml2.uml.InteractionFragment ecoreElement) {
        org.eclipse.uml2.uml.Interaction interaction = ecoreElement.getEnclosingInteraction();
        if (interaction != null) {
            EList<?> fragments = interaction.getFragments();
            if (fragments != null && fragments.size() > 0) {
                int index = fragments.indexOf(ecoreElement);
                if (index < (fragments.size() -1)) {
                    org.eclipse.uml2.uml.InteractionFragment previous = (org.eclipse.uml2.uml.InteractionFragment) fragments
                            .get(index + 1);
                    if (previous instanceof org.eclipse.uml2.uml.ExecutionSpecification){
                        previous = getPreviousFragmentInInteraction(previous);
                    } else if (previous instanceof PartDecomposition){
                        previous = getPreviousFragmentInInteraction(previous);
                    }
                    return previous;
                }
            }
        }
        return null;
    }

    @objid ("c125f4d3-10df-4dd4-8fb5-08a3c0c2cb2d")
    public static org.eclipse.uml2.uml.InteractionFragment getNextFragmentInInteraction(org.eclipse.uml2.uml.Message ecoreElement) {
        org.eclipse.uml2.uml.MessageOccurrenceSpecification end = null;
        org.eclipse.uml2.uml.MessageEnd msgEnd = null;
        msgEnd = ecoreElement.getSendEvent();
        if (!(msgEnd instanceof org.eclipse.uml2.uml.MessageOccurrenceSpecification)){
            msgEnd = ecoreElement.getReceiveEvent();
        }
        
        if (msgEnd instanceof org.eclipse.uml2.uml.MessageOccurrenceSpecification){
            end = (org.eclipse.uml2.uml.MessageOccurrenceSpecification) msgEnd;
        }
        
        if (end != null){
            org.eclipse.uml2.uml.Interaction interaction = end.getEnclosingInteraction();
            if (interaction != null) {
                EList<?> fragments = interaction.getFragments();
                if (fragments != null && fragments.size() > 0) {
                    int index = fragments.indexOf(end);
                    if (index < (fragments.size() -1)) {
                        org.eclipse.uml2.uml.InteractionFragment previous = (org.eclipse.uml2.uml.InteractionFragment) fragments
                                .get(index + 1);
                        if (previous instanceof org.eclipse.uml2.uml.ExecutionSpecification){
                            previous = getPreviousFragmentInInteraction(previous);
                        }else if (previous instanceof PartDecomposition){
                            previous = getPreviousFragmentInInteraction(previous);
                        }
                        return previous;
                    }
                }
            }
        }
        return null;
    }

    @objid ("a7d3768d-31e6-4560-9e9b-ddb6376d1038")
    public static org.eclipse.uml2.uml.InteractionFragment getNextFragmentInOperand(org.eclipse.uml2.uml.InteractionFragment ecoreElement) {
        org.eclipse.uml2.uml.InteractionOperand operand = ecoreElement.getEnclosingOperand();
        if (operand != null) {
            EList<?> fragments = operand.getFragments();
            if (fragments != null && fragments.size() > 0) {
                int index = fragments.indexOf(ecoreElement);
                if (index < (fragments.size() -1)) {
                    org.eclipse.uml2.uml.InteractionFragment previous = (org.eclipse.uml2.uml.InteractionFragment) fragments
                            .get(index - 1);
                    if (previous instanceof org.eclipse.uml2.uml.ExecutionSpecification){
                        previous = getPreviousFragmentInInteraction(previous);
                    }else if (previous instanceof PartDecomposition){
                        previous = getPreviousFragmentInInteraction(previous);
                    }
                    return previous;
                }
            }
        }
        return null;
    }

    @objid ("944142ea-2ce9-4979-a845-953f5a83fa49")
    public static org.eclipse.uml2.uml.InteractionFragment getNextFragmentInOperand(org.eclipse.uml2.uml.Message ecoreElement) {
        org.eclipse.uml2.uml.MessageOccurrenceSpecification end = null;
        org.eclipse.uml2.uml.MessageEnd msgEnd = null;
        msgEnd = ecoreElement.getSendEvent();
        if (!(msgEnd instanceof org.eclipse.uml2.uml.MessageOccurrenceSpecification)){
            msgEnd = ecoreElement.getReceiveEvent();
        }
        
        if (msgEnd instanceof org.eclipse.uml2.uml.MessageOccurrenceSpecification){
            end = (org.eclipse.uml2.uml.MessageOccurrenceSpecification) msgEnd;
        }
        
        if (end != null){
            org.eclipse.uml2.uml.InteractionOperand operand = end.getEnclosingOperand();
            if (operand != null) {
                EList<?> fragments = operand.getFragments();
                if (fragments != null && fragments.size() > 0) {
                    int index = fragments.indexOf(end);
                    if (index < (fragments.size() -1)) {
                        org.eclipse.uml2.uml.InteractionFragment previous = (org.eclipse.uml2.uml.InteractionFragment) fragments
                                .get(index - 1);
                        if (previous instanceof org.eclipse.uml2.uml.ExecutionSpecification){
                            previous = getPreviousFragmentInInteraction(previous);
                        }else if (previous instanceof PartDecomposition){
                            previous = getPreviousFragmentInInteraction(previous);
                        }
                        return previous;
                    }
                }
            }
        }
        return null;
    }

    @objid ("654d8744-2a75-4d25-a638-16934be0c9f9")
    public static List<Property> getOppositeAssociationEnds(Property p1) throws AssociationNotFoundException {
        org.eclipse.uml2.uml.Association assoc = p1.getAssociation();
        
        if (assoc != null) {
            List<Property> oppositeEnds = new ArrayList<>();
            for (Object ecoreEnd : assoc.getMemberEnds()) {
                if (!ecoreEnd.equals(p1)){
                    oppositeEnds.add((Property) ecoreEnd);
                }
            }
            return oppositeEnds;
        } else {
            throw new AssociationNotFoundException("The Property \""
                    + p1.getName() + "\" is not connected to an org.eclipse.uml2.uml.Association.");
        }
    }

    @objid ("5d7bf35d-bbf4-4bff-92d3-0e5f2036a89d")
    public static int getOrdering(final org.eclipse.uml2.uml.Pin pin) {
        int result = 0;
        CallAction pinOwner = (CallAction) pin.getOwner();
        for (org.eclipse.uml2.uml.Element ownedElt : pinOwner.getOwnedElements()){
            if (ownedElt.equals(pin)){
                return result;
            }else if (ownedElt instanceof org.eclipse.uml2.uml.Pin){
                result++;
            }
        }
        return result;
    }

    @objid ("1c407869-353d-4850-b8c7-7709db5a5d85")
    public static int getOrdering(final org.eclipse.uml2.uml.Parameter parameter) {
        int result = 0;
        org.eclipse.uml2.uml.Element paramOwner =  parameter.getOwner();
        for (org.eclipse.uml2.uml.Element ownedElt : paramOwner.getOwnedElements()){
            if (ownedElt.equals(parameter)){
                return result;
            }else if (ownedElt instanceof org.eclipse.uml2.uml.Parameter){
                result++;
            }
        }
        return result;
    }

    @objid ("98e32dea-7b14-4eb5-9a97-bc821e1d854a")
    public static org.eclipse.uml2.uml.Activity getOwnerActivity(org.eclipse.uml2.uml.ActivityNode node) {
        org.eclipse.uml2.uml.Element owner = node.getOwner();
        
        while (!(owner instanceof  org.eclipse.uml2.uml.Activity)){
            owner = owner.getOwner();
        }
        return (org.eclipse.uml2.uml.Activity) owner;
    }

    @objid ("3574aa6f-131b-4054-84d4-2832c2e8106a")
    public static org.eclipse.uml2.uml.Clause getOwnerClause(org.eclipse.uml2.uml.ActivityNode node) {
        org.eclipse.uml2.uml.StructuredActivityNode structuredNode = node.getInStructuredNode();
        
        if (structuredNode instanceof org.eclipse.uml2.uml.ConditionalNode) {
            for (Object clause : ((org.eclipse.uml2.uml.ConditionalNode) structuredNode)
                    .getClauses()) {
                for (Object innerNode : ( (org.eclipse.uml2.uml.Clause) clause).getTests()) {
                    if (innerNode.equals(node)){
                        return  (org.eclipse.uml2.uml.Clause) clause;
                    }
                }
        
                for (Object innerNode : ( (org.eclipse.uml2.uml.Clause) clause).getBodies()) {
                    if (innerNode.equals(node)){
                        return  (org.eclipse.uml2.uml.Clause) clause;
                    }
                }
            }
        }
        return null;
    }

    @objid ("0584a6db-0d1f-46a5-a6de-6e3567c85584")
    public static List<ActivityPartition> getOwnerPartitions(org.eclipse.uml2.uml.ActivityNode node) {
        List<ActivityPartition> partitionList = new ArrayList<>();
        for (Object partition : node.getInPartitions()) {
            partitionList.add((org.eclipse.uml2.uml.ActivityPartition) partition);
        }
        return partitionList;
    }

    @objid ("09a1af72-0c47-4782-b3ed-af39939c7528")
    public static org.eclipse.uml2.uml.InteractionFragment getPreviousFragmentInInteraction(org.eclipse.uml2.uml.InteractionFragment ecoreElement) {
        org.eclipse.uml2.uml.Interaction interaction = ecoreElement.getEnclosingInteraction();
        if (interaction != null) {
            EList<?> fragments = interaction.getFragments();
            if (fragments != null && fragments.size() > 0) {
                int index = fragments.indexOf(ecoreElement);
                if (index > 0) {
                    org.eclipse.uml2.uml.InteractionFragment previous = (org.eclipse.uml2.uml.InteractionFragment) fragments
                            .get(index - 1);
                    if (previous instanceof org.eclipse.uml2.uml.ExecutionSpecification){
                        return getPreviousFragmentInInteraction(previous);
                    }else if (previous instanceof PartDecomposition){
                        return getPreviousFragmentInInteraction(previous);
                    }
                    return previous;
                }
            }
        }
        return null;
    }

    @objid ("02d26dec-860f-43bc-998d-60e75d73ac3c")
    public static org.eclipse.uml2.uml.InteractionFragment getPreviousFragmentInInteraction(org.eclipse.uml2.uml.Message ecoreElement) {
        org.eclipse.uml2.uml.MessageOccurrenceSpecification end = null;
        org.eclipse.uml2.uml.MessageEnd msgEnd = null;
        msgEnd = ecoreElement.getSendEvent();
        if (!(msgEnd instanceof org.eclipse.uml2.uml.MessageOccurrenceSpecification)){
            msgEnd = ecoreElement.getReceiveEvent();
        }
        
        if (msgEnd instanceof org.eclipse.uml2.uml.MessageOccurrenceSpecification){
            end = (org.eclipse.uml2.uml.MessageOccurrenceSpecification) msgEnd;
        }
        
        if (end != null){
            org.eclipse.uml2.uml.Interaction interaction = end.getEnclosingInteraction();
            if (interaction != null) {
                EList<?> fragments = interaction.getFragments();
                if (fragments != null && fragments.size() > 0) {
                    int index = fragments.indexOf(end);
                    if (index > 0) {
                        org.eclipse.uml2.uml.InteractionFragment previous = (org.eclipse.uml2.uml.InteractionFragment) fragments
                                .get(index - 1);
                        if (previous instanceof org.eclipse.uml2.uml.ExecutionSpecification){
                            previous = getPreviousFragmentInInteraction(previous);
                        }else if (previous instanceof PartDecomposition){
                            previous = getPreviousFragmentInInteraction(previous);
                        }
                        return previous;
                    }
                }
            }
        }
        return null;
    }

    @objid ("fe4a252f-b6f9-4b6e-a872-ae9f3fa55b58")
    public static org.eclipse.uml2.uml.InteractionFragment getPreviousFragmentInOperand(org.eclipse.uml2.uml.InteractionFragment ecoreElement) {
        org.eclipse.uml2.uml.InteractionOperand operand = ecoreElement.getEnclosingOperand();
        if (operand != null) {
            EList<?> fragments = operand.getFragments();
            if (fragments != null && fragments.size() > 0) {
                int index = fragments.indexOf(ecoreElement);
                if (index > 0) {
                    org.eclipse.uml2.uml.InteractionFragment previous = (org.eclipse.uml2.uml.InteractionFragment) fragments
                            .get(index - 1);
                    if (previous instanceof org.eclipse.uml2.uml.ExecutionSpecification){
                        return getPreviousFragmentInInteraction(previous);
                    }else if (previous instanceof PartDecomposition){
                        return  getPreviousFragmentInInteraction(previous);
                    }
        
                }else{
        
                    org.eclipse.uml2.uml.Element owner = operand.getOwner();
                    if (owner instanceof CombinedFragment){
                        CombinedFragment comb =  (org.eclipse.uml2.uml.CombinedFragment) owner;
                        owner = owner.getOwner();
                        if (owner instanceof org.eclipse.uml2.uml.InteractionOperand){
                            getPreviousFragmentInOperand(comb);
                        }else if (owner instanceof org.eclipse.uml2.uml.Interaction){
                            getPreviousFragmentInInteraction(comb);
                        }
                    }
        
                }
            }
        
        }
        return null;
    }

    @objid ("f871b801-7b89-4a64-9bea-ae10b55d6c67")
    public static org.eclipse.uml2.uml.InteractionFragment getPreviousFragmentInOperand(org.eclipse.uml2.uml.Message ecoreElement) {
        org.eclipse.uml2.uml.MessageOccurrenceSpecification end = null;
        org.eclipse.uml2.uml.MessageEnd msgEnd = null;
        msgEnd = ecoreElement.getSendEvent();
        if (!(msgEnd instanceof org.eclipse.uml2.uml.MessageOccurrenceSpecification)){
            msgEnd = ecoreElement.getReceiveEvent();
        }
        
        if (msgEnd instanceof org.eclipse.uml2.uml.MessageOccurrenceSpecification){
            end = (org.eclipse.uml2.uml.MessageOccurrenceSpecification) msgEnd;
        }
        
        if (end != null){
            org.eclipse.uml2.uml.InteractionOperand operand = end.getEnclosingOperand();
            if (operand != null) {
                EList<?> fragments = operand.getFragments();
                if (fragments != null && fragments.size() > 0) {
                    int index = fragments.indexOf(end);
                    if (index > 0) {
                        org.eclipse.uml2.uml.InteractionFragment previous = (org.eclipse.uml2.uml.InteractionFragment) fragments
                                .get(index - 1);
                        if (previous instanceof org.eclipse.uml2.uml.ExecutionSpecification){
                            previous = getPreviousFragmentInInteraction(previous);
                        }else if (previous instanceof PartDecomposition){
                            previous = getPreviousFragmentInInteraction(previous);
                        }
                        return previous;
                    }
                }
            }
        }
        return null;
    }

    @objid ("98c9a6a6-be05-4c79-b6af-9d3c89c5a2c0")
    public static org.eclipse.uml2.uml.InteractionOperand getPreviousOperand(org.eclipse.uml2.uml.InteractionOperand ecoreElement) {
        org.eclipse.uml2.uml.Element ecoreOwner = ecoreElement.getOwner();
        if (ecoreOwner instanceof CombinedFragment) {
            CombinedFragment ecoreCF =  (org.eclipse.uml2.uml.CombinedFragment) ecoreOwner;
            EList<?> ecoreOpList = ecoreCF.getOperands();
            if (ecoreOpList != null && ecoreOpList.size() > 0) {
                int index = ecoreOpList.indexOf(ecoreElement);
                if (index > 0){
                    return (org.eclipse.uml2.uml.InteractionOperand) ecoreOpList.get(index - 1);
                }
            }
        }
        return null;
    }

    @objid ("61664e22-ec6d-4f42-b35c-34ad16ca92f2")
    public static Gate getReferedGate(org.eclipse.uml2.uml.Gate ecoreGate) {
        Gate referedGate = null;
        
        if (!isFormalGate(ecoreGate)) {
            org.eclipse.uml2.uml.Element owner = ecoreGate.getOwner();
            if (owner instanceof org.eclipse.uml2.uml.InteractionUse){
                referedGate = getFormalGate(ecoreGate);
            }
        }
        return referedGate;
    }

    @objid ("b3451b4d-5fc8-49a9-aaae-72ac3683b051")
    public static org.eclipse.uml2.uml.Message getReplyMessage(org.eclipse.uml2.uml.Message message) {
        boolean isSynchronous = false;
        org.eclipse.uml2.uml.MessageSort msgSort = message.getMessageSort();
        if (msgSort.getValue() == org.eclipse.uml2.uml.MessageSort.SYNCH_CALL){
            isSynchronous = true;
        }
        
        if (isSynchronous) {
            org.eclipse.uml2.uml.MessageEnd receiveEnd = message.getReceiveEvent();
            if (receiveEnd != null){
        
                org.eclipse.uml2.uml.Lifeline coveredLL = null;
        
                if (receiveEnd instanceof org.eclipse.uml2.uml.MessageOccurrenceSpecification){
                    coveredLL = ((org.eclipse.uml2.uml.MessageOccurrenceSpecification)receiveEnd).getCovereds().get(0);
                } else if (receiveEnd instanceof Gate){
                    coveredLL = ((org.eclipse.uml2.uml.InteractionUse)((org.eclipse.uml2.uml.Gate)receiveEnd).getOwner()).getCovereds().get(0);
                }
        
                if (coveredLL != null){
                    EList<?> coveringFragments = coveredLL.getCoveredBys();
                    int index = coveringFragments.indexOf(receiveEnd);
                    int fragmentsNumber = coveringFragments.size();
                    int indexOfEOS = index + 1;
                    if (indexOfEOS < coveringFragments.size()){ 
                        org.eclipse.uml2.uml.InteractionFragment next = (org.eclipse.uml2.uml.InteractionFragment) coveringFragments
                                .get(indexOfEOS);
                        while (indexOfEOS < fragmentsNumber - 1
                                && !(next instanceof org.eclipse.uml2.uml.ExecutionSpecification)) {
                            next = (org.eclipse.uml2.uml.InteractionFragment) coveringFragments
                                    .get(++indexOfEOS);
                        }
        
                        if (next instanceof org.eclipse.uml2.uml.ExecutionSpecification) {
                            org.eclipse.uml2.uml.OccurrenceSpecification finishEnd = ((org.eclipse.uml2.uml.ExecutionSpecification) next)
                                    .getFinish();
                            if (finishEnd instanceof org.eclipse.uml2.uml.MessageOccurrenceSpecification) {
                                return ((org.eclipse.uml2.uml.MessageOccurrenceSpecification) finishEnd)
                                        .getMessage();
                            }
                        }
                    }
                }
        
                return null;
        
            }
        }
        return null;
    }

    @objid ("91d15183-4e31-4fec-a250-79cba888df00")
    public static Package getRoot(org.eclipse.uml2.uml.Package ecorePackage) {
        org.eclipse.uml2.uml.Element root = ecorePackage;
        while (!(root instanceof Model) && (root.getOwner() != null)){
            root = root.getOwner();
        }
        
        if (root instanceof Package){
            return (Package) root;
        }else {
            return ecorePackage;
        }
    }

    @objid ("0957512f-21aa-4bbe-987c-008897f0289c")
    public static String getValue(ValueSpecification valueSpec) {
        StringBuffer result = new StringBuffer();
        
        if (valueSpec instanceof Expression){       
            result.append( ((org.eclipse.uml2.uml.Expression) valueSpec).getSymbol());       
        }else if (valueSpec instanceof  org.eclipse.uml2.uml.OpaqueExpression){       
            for (String body : ((org.eclipse.uml2.uml.OpaqueExpression) valueSpec).getBodies()){
                result.append(body);
            }       
        }else if (valueSpec instanceof LiteralSpecification){
            result.append(((org.eclipse.uml2.uml.LiteralSpecification) valueSpec).stringValue());
        }
        return result.toString();
    }

    @objid ("8049a940-c812-4b09-a35e-bca49b2aa8cc")
    public static boolean hasChangeEvent(org.eclipse.uml2.uml.AcceptEventAction action) {
        for (Object trigger : action.getTriggers()) {
            org.eclipse.uml2.uml.Event event = ( (org.eclipse.uml2.uml.Trigger) trigger).getEvent();
            if (event instanceof  org.eclipse.uml2.uml.ChangeEvent){
                return true;
            }
        }
        return false;
    }

    @objid ("0b0a1a2f-0054-44c0-ae94-bc50e87fbd42")
    public static boolean hasReceiveOperationEvent(org.eclipse.uml2.uml.AcceptEventAction action) {
        for (Object trigger : action.getTriggers()) {
            org.eclipse.uml2.uml.Event event = ( (org.eclipse.uml2.uml.Trigger) trigger).getEvent();
            if (event instanceof  org.eclipse.uml2.uml.ReceiveOperationEvent){
                return true;
            }
        }
        return false;
    }

    @objid ("d90cf40a-4b08-40e3-a73c-1cff905f6288")
    public static boolean hasSignalEvent(org.eclipse.uml2.uml.AcceptEventAction action) {
        for (Object trigger : action.getTriggers()) {
            org.eclipse.uml2.uml.Event event = ( (org.eclipse.uml2.uml.Trigger) trigger).getEvent();
            if (event instanceof  org.eclipse.uml2.uml.SignalEvent){
                return true;
            }
        }
        return false;
    }

    @objid ("489fefe5-778c-4f33-9d3d-8f5a6e13a2d5")
    public static boolean hasTimeEvent(org.eclipse.uml2.uml.AcceptEventAction action) {
        for (Object trigger : action.getTriggers()) {
            org.eclipse.uml2.uml.Event event = ( (org.eclipse.uml2.uml.Trigger) trigger).getEvent();
            if (event instanceof  org.eclipse.uml2.uml.TimeEvent){
                return true;
            }
        }
        return false;
    }

    @objid ("62240a76-23ef-46f0-aab1-8a33fa2f4f98")
    public static int getValidEndNumber(final org.eclipse.uml2.uml.Association ecoreElement) {
        int endNumber = 0;
        
        for (Property memberEnd : ecoreElement.getMemberEnds()) {
            if (isValid(memberEnd)){
                endNumber++;
            }     
        }
        return endNumber;
    }

    @objid ("e100a04b-1e46-4fb6-8b35-5e2cccf8c30b")
    public static boolean hasUniqueRootRegion(org.eclipse.uml2.uml.StateMachine stateMachine) {
        EList<?> rootRegionList = stateMachine.getRegions();
        return (rootRegionList.size() == 1);
    }

    @objid ("8d974ab3-6a3c-4cb5-aa97-aa791f0432c4")
    public static boolean isAssocEnd(final Property ecoreElement) {
        if (!((ecoreElement.getOwner() instanceof org.eclipse.uml2.uml.Stereotype)
                ||(ecoreElement.getType() instanceof org.eclipse.uml2.uml.Stereotype))){
            return ((ecoreElement.getAssociation() != null) && (isValid(ecoreElement)));
        }
        return false;
    }

    @objid ("eb43a600-2eb8-4a08-a196-638ce07d203e")
    public static boolean isAssocInstance(final InstanceSpecification ecoreLink) {
        EList<Classifier> types = ecoreLink.getClassifiers();
        
        if (types.size() > 0){
        
            for  (org.eclipse.uml2.uml.Classifier currentType : types){
                if (!(currentType instanceof org.eclipse.uml2.uml.Association)
                        || (!isValid((org.eclipse.uml2.uml.Association) currentType))){
                    // One of types is not a valid Association
                    return false;
                }
            }
            //All types are valid Association 
            //must have at least two slots i.e. two ends 
            return ecoreLink.getSlots().size() >= 2;
        }
        //No Type
        return false;
    }

    @objid ("7f4f3eb1-dad4-4b85-8911-2912dc6bcb82")
    public static boolean isChildOfRootRegion(org.eclipse.uml2.uml.Vertex state) {
        org.eclipse.uml2.uml.Region rootRegion = getMostEnclosingRegion(state);
        org.eclipse.uml2.uml.Region container = state.getContainer();
        return ((container != null) && (container.equals(rootRegion)));
    }

    @objid ("025716aa-67de-4e46-bd57-ac74dacfaf9b")
    public static boolean isCondition(org.eclipse.uml2.uml.Constraint constraint) {
        for (Object constrainedElt : constraint.getConstrainedElements()) {
            if (constrainedElt instanceof org.eclipse.uml2.uml.ProtocolTransition) {
                org.eclipse.uml2.uml.ProtocolTransition trans = (org.eclipse.uml2.uml.ProtocolTransition) constrainedElt;
                return (trans.getGuard().equals(constraint)
                        || trans.getPostCondition().equals(constraint)
                        || trans.getPreCondition().equals(constraint));
            }else  if (constrainedElt instanceof org.eclipse.uml2.uml.Transition) {
                org.eclipse.uml2.uml.Transition trans =  (org.eclipse.uml2.uml.Transition) constrainedElt;
                return trans.getGuard().equals(constraint);
            }
        
        }
        return false;
    }

    @objid ("6c80340f-dd14-4eeb-9de1-7acfb5a96acf")
    public static boolean isConnector(final InstanceSpecification ecoreLink) {
        List<Element> objEnds = EcoreModelNavigation.getSlots(ecoreLink);
        
        if (objEnds.size() >= 2){
            for (Element objEnd : objEnds){
                if (!(objEnd instanceof Instance) || !(objEnd instanceof BindableInstance)){
                    return false;
                }
            }
        }else{
            return false;
        }
        return true;
    }

    @objid ("c9deac00-6ff1-4a89-88ff-8afa67a3037e")
    public static boolean isConnectorEnd(Property ecoreElement) {
        org.eclipse.uml2.uml.Element owner = ecoreElement.getOwner();
        if (owner instanceof org.eclipse.uml2.uml.StructuredClassifier){
            for (org.eclipse.uml2.uml.Connector connector : ((org.eclipse.uml2.uml.StructuredClassifier) owner).getOwnedConnectors()){
                for (org.eclipse.uml2.uml.ConnectorEnd end : connector.getEnds()){
                    if ((end.getPartWithPort() != null ) 
                            && (end.getPartWithPort().equals(ecoreElement))){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @objid ("732634f2-a0f7-4f68-96f8-2e075478f312")
    public static boolean isEffectsBehavior(org.eclipse.uml2.uml.Behavior behavior) {
        return (behavior.getOwner() instanceof  org.eclipse.uml2.uml.Transition);
    }

    @objid ("b6146dfd-44aa-4ed9-a1f7-1f46ffa3cf89")
    public static boolean isFormalGate(org.eclipse.uml2.uml.Gate ecoreGate) {
        org.eclipse.uml2.uml.Interaction enclosing = getMostEnclosingInteraction(ecoreGate);
        
        for (Object formal : enclosing.getFormalGates()) {
            return (formal.equals(ecoreGate));
        }
        return false;
    }

    @objid ("f1bd371e-03d2-4f90-a838-7e290c5d2b4c")
    public static boolean isFoundMessage(org.eclipse.uml2.uml.Message message) {
        org.eclipse.uml2.uml.MessageEnd sendEnd = message.getSendEvent();
        
        if (sendEnd == null){
            return true;
        }else if (sendEnd instanceof org.eclipse.uml2.uml.MessageOccurrenceSpecification) {
            org.eclipse.uml2.uml.MessageOccurrenceSpecification end = (org.eclipse.uml2.uml.MessageOccurrenceSpecification) sendEnd;
            EList<?> covereds = end.getCovereds();
            return (covereds == null || covereds.size() == 0);
        }
        return false;
    }

    @objid ("f99d68db-41b4-41c6-8507-127c5902b3bb")
    public static boolean isInAction(final org.eclipse.uml2.uml.ActivityNode ecoreElement) {
        return (ecoreElement.getOwner() instanceof  org.eclipse.uml2.uml.Action);
    }

    @objid ("de1f4e6c-6958-470a-a1a1-f4bf03077caa")
    public static boolean isInActivity(org.eclipse.uml2.uml.ActivityNode node) {
        org.eclipse.uml2.uml.Activity activity = node.getActivity();
        return (activity != null);
    }

    @objid ("e7a5fd8b-9e1b-42b3-bcd4-a301b767ee00")
    public static boolean isInClause(org.eclipse.uml2.uml.ActivityNode node) {
        org.eclipse.uml2.uml.StructuredActivityNode structuredNode = node.getInStructuredNode();
        
        if (structuredNode instanceof org.eclipse.uml2.uml.ConditionalNode) {
            for (Object clause : ((org.eclipse.uml2.uml.ConditionalNode) structuredNode)
                    .getClauses()) {
                for (Object innerNode : ( (org.eclipse.uml2.uml.Clause) clause).getTests()) {
                    if (innerNode.equals(node)){
                        return true;
                    }
                }
        
                for (Object innerNode : ( (org.eclipse.uml2.uml.Clause) clause).getBodies()) {
                    if (innerNode.equals(node)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @objid ("7e81d63d-98cb-48f4-8738-4861ef2d93c9")
    public static boolean isInPartition(org.eclipse.uml2.uml.ActivityNode node) {
        EList<?> partitionList = node.getInPartitions();
        return (partitionList != null && partitionList.size() > 0);
    }

    @objid ("4947fbcd-057b-441d-b187-e508e5c5f3b8")
    public static boolean isInStructuredActivityNode(org.eclipse.uml2.uml.ActivityNode node) {
        org.eclipse.uml2.uml.StructuredActivityNode structuredNode = node.getInStructuredNode();
        return (structuredNode != null);
    }

    @objid ("34040ebe-107f-4018-a579-840dd8681c8d")
    public static boolean isInternalTransition(org.eclipse.uml2.uml.Transition transition) {
        return (org.eclipse.uml2.uml.TransitionKind.INTERNAL_LITERAL.equals(transition.getKind()));
    }

    @objid ("c8ccbe4e-1060-429e-ada1-e20fb8101742")
    public static boolean isLostMessage(org.eclipse.uml2.uml.Message message) {
        org.eclipse.uml2.uml.MessageEnd receiveEnd = message.getReceiveEvent();
        
        if (receiveEnd == null){
            return true;
        }else if (receiveEnd instanceof org.eclipse.uml2.uml.MessageOccurrenceSpecification) {
            org.eclipse.uml2.uml.MessageOccurrenceSpecification end = (org.eclipse.uml2.uml.MessageOccurrenceSpecification) receiveEnd;
            EList<?> covereds = end.getCovereds();
            return (covereds == null || covereds.size() == 0);
        }
        return false;
    }

    @objid ("3f481720-8394-4cc4-b1b9-05901d2a5f4b")
    public static boolean isMergedBehavior(org.eclipse.uml2.uml.Behavior behavior) {
        for (Object clientDependency : behavior.getClientDependencies()) {
            for (Object ecoreSupplier : ((org.eclipse.uml2.uml.Dependency) clientDependency)
                    .getSuppliers()) {
                if (ecoreSupplier instanceof  org.eclipse.uml2.uml.Signal){
                    return true;
                }
            }
        }
        return false;
    }

    @objid ("2d5e5cbd-2919-45e7-a767-19513f9a0eef")
    public static boolean isMetaclass(final Class ecoreClass) {
        boolean isMetaclass = false;
        
        for (org.eclipse.uml2.uml.Stereotype ster : ecoreClass.getAppliedStereotypes()){
            if (ster.getName().equals("Metaclass")){
                isMetaclass = true;
                break;
            }
        }
        
        if (!isMetaclass){
            return false;
        }
        
        org.eclipse.uml2.uml.Element owner = ecoreClass.getOwner();
        return ((owner instanceof Model ) &&
                        ((Model)owner).getName().equals(UMLMetamodel.getInstance().getUMLMetamodel().getName()));
    }

    @objid ("ce8493b2-fe4f-47af-a50c-81c5aa4775ae")
    public static boolean isModelChild(org.eclipse.uml2.uml.Element element) {
        List <org.eclipse.uml2.uml.Element> listRoot = new ArrayList <>();
        Set<org.eclipse.uml2.uml.Package> roots = ReverseProperties.getInstance().getEcoreModels();
        listRoot.addAll(roots);
        
        for(org.eclipse.uml2.uml.Package root : roots){
            for (Object packageImport : root.getPackageImports()){
                listRoot.add(((org.eclipse.uml2.uml.PackageImport) packageImport).getImportedPackage());
            }
        }
        
        List<Package> listPackages = topOwners(element);
        
        for (Object pack : listRoot){
            if (listPackages.contains(pack)){
                return true;
            }
        }
        return false;
    }

    @objid ("eefce16a-1998-4edf-8be0-21c151d862a5")
    public static boolean isNotNull(final String text) {
        return (text != null);
    }

    @objid ("3d9852bc-ff7e-4381-9c3f-fc91adb996e8")
    public static boolean isPart(final Property ecoreElement) {
        if (!(ecoreElement.getOwner() instanceof org.eclipse.uml2.uml.Stereotype)){
        
            if ((ecoreElement.getEnds() != null) && (ecoreElement.getEnds().size() > 0))
                return true;
        
            return ((ecoreElement.isComposite()  && ((ecoreElement.getAssociation() == null))
                    && (!(ecoreElement.getOwner() instanceof org.eclipse.uml2.uml.Interface))) ||
                    (ecoreElement.getOwner() instanceof org.eclipse.uml2.uml.Interaction || 
                            ((ecoreElement.getOwner() != null) && (ecoreElement.getOwner().getOwner() instanceof org.eclipse.uml2.uml.Interaction))));
        
        }
        return false;
    }

    @objid ("7591f9f2-7bb4-4fdf-a766-07eed1e72922")
    public static boolean isPort(final Property ecoreElement) {
        return (ecoreElement instanceof org.eclipse.uml2.uml.Port);
    }

    @objid ("70b85fdd-ef8a-40d3-a601-10c2e664cded")
    private static boolean isValid(final org.eclipse.uml2.uml.Association ecoreElement) {
        return (getValidEndNumber(ecoreElement) >= 2);
    }

    @objid ("db4fa252-10ac-48e5-9427-53ad2584c0ce")
    public static List<Package> topOwners(final org.eclipse.uml2.uml.Element element) {
        List<Package> listPackages = new ArrayList<>();
        
        if (element instanceof Package){
            listPackages.add((Package) element);
        }
        
        org.eclipse.uml2.uml.Element temp = element;
        org.eclipse.uml2.uml.Element owner = temp.getOwner();
        
        while (owner != null) {
        
            if (owner instanceof Package){
                listPackages.add((Package) owner);
            }
        
            temp = owner;
            owner = temp.getOwner();        
        }
        return listPackages;
    }

    @objid ("54f169af-da2a-4415-a9ea-f5fb4d7c7b3b")
    public static void unsupportedOwnedWithIj(final Element objingOwner, final ModelElement objingElement) {
        String eltName = objingElement.getName();
        String eltClassName = objingElement.getClass().getSimpleName()
                .substring(2);
        String ownerClassName = objingOwner.getClass().getSimpleName()
                .substring(2);
        String message = Xmi.I18N.getMessage("logFile.warning.unsupportedOwnerImport",
                eltName, eltClassName, ownerClassName);
        
        ReverseProperties.getInstance().addError(message);
    }

    @objid ("1c41c9f1-871b-4598-a90f-95bd1d995669")
    private static List<Parameter> getMatchedParameters(org.eclipse.uml2.uml.InputPin pin) {
        CallAction pinOwner = (CallAction) pin.getOwner();
        List<Parameter> paramList = new ArrayList<>();
        
        if (pinOwner instanceof  org.eclipse.uml2.uml.CallOperationAction) {
            org.eclipse.uml2.uml.Operation operation = ( (org.eclipse.uml2.uml.CallOperationAction) pinOwner)
                    .getOperation();
            if (operation != null) {
                for (org.eclipse.uml2.uml.Parameter param : operation.getOwnedParameters()) {
                    if (param.getDirection().equals(org.eclipse.uml2.uml.ParameterDirectionKind.IN_LITERAL) 
                            || param.getDirection().equals(org.eclipse.uml2.uml.ParameterDirectionKind.INOUT_LITERAL) ){
                        paramList.add( param);
                    }
                }
            }
        } else if (pinOwner instanceof  org.eclipse.uml2.uml.CallBehaviorAction) {
            org.eclipse.uml2.uml. Behavior behavior = ( (org.eclipse.uml2.uml.CallBehaviorAction) pinOwner).getBehavior();
            if (behavior != null) {
                for (org.eclipse.uml2.uml.Parameter param : behavior.getOwnedParameters()) {
                    if (param.getDirection().equals(org.eclipse.uml2.uml.ParameterDirectionKind.IN_LITERAL) 
                            || param.getDirection().equals(org.eclipse.uml2.uml.ParameterDirectionKind.INOUT_LITERAL) ){
                        paramList.add( param);
                    }
                }
            }
        }
        return paramList;
    }

    @objid ("52d2f815-336a-49f3-88d3-2aafe3b71d1f")
    private static List<Parameter> getMatchedParameters(final org.eclipse.uml2.uml.OutputPin pin) {
        CallAction pinOwner = (CallAction) pin.getOwner();
        List<Parameter> paramList = new ArrayList<>();
        
        if (pinOwner instanceof  org.eclipse.uml2.uml.CallOperationAction) {
            org.eclipse.uml2.uml.Operation operation = ( (org.eclipse.uml2.uml.CallOperationAction) pinOwner)
                    .getOperation();
            if (operation != null) {
                for (org.eclipse.uml2.uml.Parameter param : operation.getOwnedParameters()) {
                    if (!(param.getDirection().equals(org.eclipse.uml2.uml.ParameterDirectionKind.IN_LITERAL))){
                        paramList.add(param);
                    }
                }
            }
        } else if (pinOwner instanceof  org.eclipse.uml2.uml.CallBehaviorAction) {
            org.eclipse.uml2.uml. Behavior behavior = ( (org.eclipse.uml2.uml.CallBehaviorAction) pinOwner).getBehavior();
            if (behavior != null) {
                for (org.eclipse.uml2.uml.Parameter param : behavior.getOwnedParameters()) {
                    if (!(param.getDirection().equals(org.eclipse.uml2.uml.ParameterDirectionKind.IN_LITERAL))){
                        paramList.add( param);
                    }
                }
            }
        }
        return paramList;
    }

    @objid ("3a761685-9343-4481-ac78-8b1517056517")
    private static List<Element> getSlots(final InstanceSpecification ecoreLink) {
        List<Element> result = new  ArrayList<>();
        EList<Slot> slots = ecoreLink.getSlots();
        ReverseProperties revProp = ReverseProperties.getInstance();
        for (Slot slot: slots ){
            for  (ValueSpecification value : slot.getValues()){
                if ((value instanceof InstanceValue) && (((InstanceValue) value).getInstance() != null)){
                    result.add((Element)revProp.getMappedElement(((InstanceValue ) value).getInstance()));
                }
            }
        }
        return result;
    }

    @objid ("90d801a0-d7e3-4b8a-8b77-76fefb7a095b")
    private static boolean isValidPropertyOwner(final org.eclipse.uml2.uml.Type ecoreType) {
        if (ecoreType != null) {
            Object objingOwner = ReverseProperties.getInstance().getMappedElement(ecoreType);
            return ((objingOwner != null) 
                    && ((objingOwner instanceof org.modelio.metamodel.uml.statik.Classifier) && 
                            ((objingOwner instanceof org.modelio.metamodel.uml.statik.Class) || (objingOwner instanceof org.modelio.metamodel.uml.statik.Interface) || (objingOwner instanceof org.modelio.metamodel.uml.statik.Component)
                                    || (objingOwner instanceof org.modelio.metamodel.uml.behavior.usecaseModel.Actor) || (objingOwner instanceof org.modelio.metamodel.uml.statik.DataType) || (objingOwner instanceof org.modelio.metamodel.uml.statik.Node) 
                                    || (objingOwner instanceof org.modelio.metamodel.uml.behavior.commonBehaviors.Signal)|| (objingOwner instanceof org.modelio.metamodel.uml.behavior.usecaseModel.UseCase))));
        }
        return false;
    }

    @objid ("79a6a3a4-8a93-463b-8291-9606cc380218")
    private static boolean isValid(final Property ecoreElement) {
        List<Property> oppositeEnds;
        try {
            oppositeEnds = EcoreModelNavigation
                    .getOppositeAssociationEnds(ecoreElement);
        
            int nbEnds = oppositeEnds.size();
        
            if (nbEnds == 1) {
                // Case of a binary org.eclipse.uml2.uml.Association
                return isValidPropertyOwner(oppositeEnds.get(0).getType());
            } else if (nbEnds > 1) {// (in Ecore, an association may have only
                // one member end (which does
                // not have any sense ...)
                // Case of a N ary association
                return isValidPropertyOwner( ecoreElement.getType());
            }
        } catch (AssociationNotFoundException e) {
            Xmi.LOG.error(Xmi.PLUGIN_ID, e);
        }
        return false;
    }

    @objid ("29a646fa-9373-40b8-9ca6-528ae949f414")
    public static boolean isInSequenceNode(org.eclipse.uml2.uml.ActivityNode node) {
        org.eclipse.uml2.uml.StructuredActivityNode structuredNode = node.getInStructuredNode();
        return (structuredNode != null);
    }

    @objid ("02dd7cd8-de34-4bc6-a510-f4ee844e2fef")
    public static boolean isInActionInputPin(org.eclipse.uml2.uml.ActivityNode ecoreElement) {
        return (ecoreElement.getOwner() instanceof ActionInputPin);
    }

}
