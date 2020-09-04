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

package org.modelio.xmi.model.objing;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.UMLFactory;
import org.modelio.metamodel.uml.behavior.activityModel.Activity;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityEdge;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityNode;
import org.modelio.metamodel.uml.behavior.activityModel.DecisionMergeNode;
import org.modelio.metamodel.uml.behavior.activityModel.ForkJoinNode;
import org.modelio.metamodel.uml.behavior.activityModel.InterruptibleActivityRegion;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.util.AbstractObjingModelNavigation;

@objid ("b0e9e7eb-6719-4187-8206-69317f63105d")
public class OActivityEdge extends OModelElement {
    @objid ("aa1476f7-d428-4424-b614-31d708a0adc5")
    public OActivityEdge(ActivityEdge element) {
        super(element);
    }

    @objid ("28ed7f65-6470-4788-b509-320a5936d74f")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
        setSource((org.eclipse.uml2.uml.ActivityEdge) ecoreElt);
        setTarget((org.eclipse.uml2.uml.ActivityEdge) ecoreElt);        
        setGuard((org.eclipse.uml2.uml.ActivityEdge) ecoreElt);
        setWeight((org.eclipse.uml2.uml.ActivityEdge) ecoreElt);
        setInterrupts((org.eclipse.uml2.uml.ActivityEdge) ecoreElt);
    }

    @objid ("dcd86028-9545-4b63-936a-05531564bd32")
    private void setSource(org.eclipse.uml2.uml.ActivityEdge flow) {
        ActivityNode objingSource = ((ActivityEdge)getObjingElement()).getSource();
        org.eclipse.uml2.uml.Element ecoreSource = GenerationProperties.getInstance().getMappedElement(objingSource);
        
        if (ecoreSource instanceof  org.eclipse.uml2.uml.ActivityNode) {
            // Particular case of a MergeNode:
            // report to the mapper of DecisionMergeNode for more information.
            // In case of a DecisionMergeNode that is a Merge AND a Decision,
            // a special structure is constructed. In this case, when exporting
            // a flow, its source can't be theMergeNode, it must be the
            // org.eclipse.uml2.uml.DecisionNode.
            if (ecoreSource instanceof org.eclipse.uml2.uml.MergeNode
                    && !AbstractObjingModelNavigation
                    .isMergeNode((DecisionMergeNode) objingSource)
                    && !AbstractObjingModelNavigation
                    .isDecisionNode((DecisionMergeNode) objingSource)) {
                EList<?> activityEdgeList = ((org.eclipse.uml2.uml.MergeNode) ecoreSource)
                .getOutgoings();
                if (activityEdgeList != null && activityEdgeList.size() > 0) {
                     org.eclipse.uml2.uml.ActivityEdge linkingFlow = (org.eclipse.uml2.uml.ActivityEdge) activityEdgeList
                    .get(0);
                     org.eclipse.uml2.uml.ActivityNode source = linkingFlow.getTarget();
                    flow.setSource(source);
                }
            } else if (ecoreSource instanceof org.eclipse.uml2.uml.JoinNode
                    && !AbstractObjingModelNavigation
                    .isJoinNode((ForkJoinNode) objingSource)
                    && !AbstractObjingModelNavigation
                    .isForkNode((ForkJoinNode) objingSource)) {
                // Same principle for ForkJoinNode elements:
                    EList<?> activityEdgeList = ((org.eclipse.uml2.uml.JoinNode) ecoreSource)
                    .getOutgoings();
                if (activityEdgeList != null && activityEdgeList.size() > 0) {
                     org.eclipse.uml2.uml.ActivityEdge linkingFlow = (org.eclipse.uml2.uml.ActivityEdge) activityEdgeList
                    .get(0);
                     org.eclipse.uml2.uml.ActivityNode source = linkingFlow.getTarget();
                    flow.setSource(source);
                }
            } else {
                flow.setSource((org.eclipse.uml2.uml.ActivityNode) ecoreSource);
            }
        }
    }

    @objid ("bf6d6c9b-fba4-4c65-b4ea-3b693419489f")
    private void setGuard(org.eclipse.uml2.uml.ActivityEdge flow) {
        String guard = ((ActivityEdge)getObjingElement()).getGuard();
        if (guard.length() > 0) {
             org.eclipse.uml2.uml.OpaqueExpression ecoreGuard = UMLFactory.eINSTANCE
            .createOpaqueExpression();
            ecoreGuard.getBodies().add(guard);
            flow.setGuard(ecoreGuard);
        }
    }

    @objid ("486b7034-595f-4656-a678-77e8ea1932ae")
    private void setTarget(org.eclipse.uml2.uml.ActivityEdge flow) {
        ActivityNode objingTarget = ((ActivityEdge)getObjingElement()).getTarget();
        
        if (objingTarget != null) {
            org.eclipse.uml2.uml.Element ecoreTarget = GenerationProperties.getInstance().getMappedElement(objingTarget);
        
            if (ecoreTarget instanceof  org.eclipse.uml2.uml.ActivityNode) {
                // In case of a DecisionMergeNode as a target, if the
                // mapping is normal, there is no particular treatment.
                // If the mapping is complex, the following structure is
                // created: the DecisionMergeNode is mapped as a Merge
                // connected to a Decision by a flow.
                // the "getMappedElement" method returns the MergeNode.
                // As a consequence, there is no particular treatment
                // to do (the target of a flow must be the MergeNode).
                flow.setTarget((org.eclipse.uml2.uml.ActivityNode) ecoreTarget);
            }
        }
    }

    @objid ("2ae193c6-a7fe-460b-a848-184f2f93dd4f")
    private void setWeight(org.eclipse.uml2.uml.ActivityEdge flow) {
        String weight = ((ActivityEdge)getObjingElement()).getWeight();
        if (weight.length() > 0) {
            try{
                int weightInt = Integer.valueOf(weight);
                org.eclipse.uml2.uml.LiteralInteger ecoreWeight = UMLFactory.eINSTANCE
                .createLiteralInteger();
                ecoreWeight.setValue(weightInt);
                flow.setWeight(ecoreWeight);
            }catch(Exception e){
                org.eclipse.uml2.uml.LiteralString ecoreWeight = UMLFactory.eINSTANCE
                .createLiteralString();
                ecoreWeight.setValue(weight);
                flow.setWeight(ecoreWeight);
            }
        }
    }

    @objid ("657d3ee0-9856-4b5f-8a76-b9e8b008ae69")
    private void setInterrupts(org.eclipse.uml2.uml.ActivityEdge flow) {
        InterruptibleActivityRegion objingRegion = ((ActivityEdge) getObjingElement())
                .getInterrupts();
        
        if (objingRegion != null) {
            org.eclipse.uml2.uml.Element ecoreRegion = GenerationProperties.getInstance().getMappedElement(objingRegion);
            if (ecoreRegion instanceof org.eclipse.uml2.uml.InterruptibleActivityRegion)
                flow.setInterrupts((org.eclipse.uml2.uml.InterruptibleActivityRegion) ecoreRegion);
        }
    }

    @objid ("07740bfe-2ed4-4383-9d5c-9707704b124f")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        MObject actOwner = getObjingElement().getCompositionOwner();
        
        while((actOwner != null) && !(actOwner instanceof Activity)){
            actOwner = actOwner.getCompositionOwner();
        }
        
        if (actOwner == null)
            ecoreElt.destroy();
        else{
            Object owner = GenerationProperties.getInstance().getMappedElement(actOwner);
            if ((owner != null) 
                    && (owner instanceof  org.eclipse.uml2.uml.Activity) 
                    && (ecoreElt instanceof  org.eclipse.uml2.uml.ActivityEdge)) {
                ((org.eclipse.uml2.uml.Activity) owner).getEdges().add((org.eclipse.uml2.uml.ActivityEdge) ecoreElt);
            }
            else{
                ecoreElt.destroy();
            }
        }
    }

}
