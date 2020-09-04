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

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.UMLFactory;
import org.modelio.metamodel.uml.behavior.activityModel.Activity;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityEdge;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityPartition;
import org.modelio.metamodel.uml.behavior.activityModel.Clause;
import org.modelio.metamodel.uml.behavior.activityModel.ConditionalNode;
import org.modelio.metamodel.uml.behavior.activityModel.ControlFlow;
import org.modelio.metamodel.uml.behavior.activityModel.DecisionMergeNode;
import org.modelio.metamodel.uml.behavior.activityModel.StructuredActivityNode;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.util.AbstractObjingModelNavigation;
import org.modelio.xmi.util.NotFoundException;
import org.modelio.xmi.util.ObjingEAnnotation;

@objid ("acaf94e3-ee33-4df8-b690-b7e2cd9afdf4")
public class ODecisionMergeNode extends OModelElement {
    @objid ("9a68bad2-1ec7-4c32-ac94-cd7f7f3fe89f")
    private boolean partialCreation = true;

    @objid ("666882de-ae7b-46d8-8ee2-ffa0973431db")
    private boolean isMergeNode = false;

    @objid ("3f9bec85-3610-4cf9-b5f3-a78054ab6cbc")
    private boolean isDecisionNode = false;

    @objid ("45ba275e-3e23-48ae-80f6-a9f2263a0d76")
    private org.eclipse.uml2.uml.DecisionNode decisionNode;

    @objid ("e255e29b-e515-4ade-a3b6-b595c0cfe597")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        this.partialCreation = false;
        
        // If the DecisionMerge is not only Decision or Merge, we create a
        // special structure:
        // a Merge connected to a Decision by a flow.
        if (this.isMergeNode)
            return UMLFactory.eINSTANCE.createMergeNode();
        else if (this.isDecisionNode)
            return UMLFactory.eINSTANCE.createDecisionNode();
        else
            return UMLFactory.eINSTANCE.createMergeNode();
    }

    @objid ("7474c69b-7c57-474a-979f-c005090a1ff3")
    public ODecisionMergeNode(DecisionMergeNode element) {
        super(element);
        if (AbstractObjingModelNavigation.isMergeNode(element))
            this.isMergeNode = true;
        else if (AbstractObjingModelNavigation.isDecisionNode(element))
            this.isDecisionNode = true;
    }

    @objid ("2f6e38f0-95c6-4fc4-9316-220a8b5c7e67")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        if (!this.isMergeNode && !this.isDecisionNode) {
            if (!this.partialCreation) {
                createStructure((org.eclipse.uml2.uml.MergeNode) ecoreElt);
            } else {
                // The structure has already been created in the partial
                // creation
                // method.
                this.decisionNode = getDecisionNode((org.eclipse.uml2.uml.MergeNode) ecoreElt);
            }
        }
        
        MObject objingOwner = getObjingElement().getCompositionOwner();
        org.eclipse.uml2.uml.Element ecoreOwner = GenerationProperties.getInstance().getMappedElement(objingOwner);
        
        if (ecoreOwner != null) {
            if (objingOwner instanceof StructuredActivityNode) {
                attachToStructuredActivityNode(ecoreElt, ecoreOwner);
                if (!this.isMergeNode && !this.isDecisionNode)
                    attachToStructuredActivityNode(this.decisionNode, ecoreOwner);
            } else if (objingOwner instanceof ActivityPartition) {
                attachToActivityPartition(ecoreElt, ecoreOwner);
                if (!this.isMergeNode && !this.isDecisionNode)
                    attachToActivityPartition(this.decisionNode, ecoreOwner);
            } else if (objingOwner instanceof Activity) {
                attachToActivity(ecoreElt, ecoreOwner);
                if (!this.isMergeNode && !this.isDecisionNode)
                    attachToActivity(this.decisionNode, ecoreOwner);
            } else if (objingOwner instanceof Clause) {
                attachToClause(ecoreElt, ecoreOwner, (Clause) objingOwner);
                if (!this.isMergeNode && !this.isDecisionNode)
                    attachToClause(this.decisionNode, ecoreOwner,
                                   (Clause) objingOwner);
            }
        }
    }

    @objid ("13965c1a-e244-47bf-b62e-fd4255421b3e")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
        
        if (this.isDecisionNode)
            setDecisionInput((org.eclipse.uml2.uml.DecisionNode) ecoreElt);
        
        if (!this.isMergeNode && !this.isDecisionNode) {
            super.setProperties(this.decisionNode);
            setDecisionInput(this.decisionNode);
        }
    }

    @objid ("eb201c09-41ee-4fd5-9b11-cb677db3bd05")
    private void createStructure(org.eclipse.uml2.uml.MergeNode mergeNode) {
        this.decisionNode = UMLFactory.eINSTANCE.createDecisionNode();
        
        ActivityEdge typeOfEdge = null;
        List<ActivityEdge> objingInc = getObjingElement().getIncoming();
        List<ActivityEdge> objingOut = getObjingElement().getOutgoing();
        
        if (objingInc.size() > 0) {
            typeOfEdge = objingInc.get(0);
        } else if (objingOut.size() > 0) {
            typeOfEdge = objingOut.get(0);
        }
        
         org.eclipse.uml2.uml.ActivityEdge ecoreFlow = null;
        if (typeOfEdge instanceof ControlFlow)
            ecoreFlow = UMLFactory.eINSTANCE.createControlFlow();
        else
            ecoreFlow = UMLFactory.eINSTANCE.createObjectFlow();
        
        Activity enclosingActivity = (Activity) AbstractObjingModelNavigation
                .getEnclosingElement(getObjingElement(), getObjingElement().getMClass().getMetamodel().getMClass(Activity.class));
        
        if (enclosingActivity != null) {
            org.eclipse.uml2.uml.Element ecoreActivity = GenerationProperties.getInstance()
                    .getMappedElement(enclosingActivity);
            if (ecoreActivity instanceof  org.eclipse.uml2.uml.Activity) {
                 org.eclipse.uml2.uml.Activity owner = (org.eclipse.uml2.uml.Activity) ecoreActivity;
                owner.getEdges().add(ecoreFlow);
            }
        }
        
        ecoreFlow.setSource(mergeNode);
        ecoreFlow.setTarget(this.decisionNode);
    }

    @objid ("76b17668-8895-47f9-bdfb-702363e0456f")
    private org.eclipse.uml2.uml.DecisionNode getDecisionNode(org.eclipse.uml2.uml.MergeNode mergeNode) {
        EList<?> edgeList = mergeNode.getOutgoings();
        if (edgeList != null && edgeList.size() > 0) {
             org.eclipse.uml2.uml.ActivityEdge flow = (org.eclipse.uml2.uml.ActivityEdge) edgeList.get(0);
             org.eclipse.uml2.uml.ActivityNode decisionnode = flow.getTarget();
            if (decisionnode instanceof org.eclipse.uml2.uml.DecisionNode)
                return (org.eclipse.uml2.uml.DecisionNode) decisionnode;
        }
        return null;
    }

    @objid ("1ca5caf5-f92b-42ad-a4ac-9ed1b38c7e13")
    private void attachToStructuredActivityNode(org.eclipse.uml2.uml.Element ecoreElt, org.eclipse.uml2.uml.Element ecoreOwner) {
        if (ecoreOwner instanceof org.eclipse.uml2.uml.StructuredActivityNode) {
            org.eclipse.uml2.uml.StructuredActivityNode owner = (org.eclipse.uml2.uml.StructuredActivityNode) ecoreOwner;
            owner.getNodes().add((org.eclipse.uml2.uml.ActivityNode)ecoreElt);
        } else {
            ecoreElt.destroy();
            throw new NotFoundException("Owner Class ("
                    + ecoreOwner.getClass().getSimpleName() + ") Not Found");
        }
    }

    @objid ("a46e3966-3705-4d9f-958b-9a985c5af311")
    private void attachToActivityPartition(org.eclipse.uml2.uml.Element ecoreElt, org.eclipse.uml2.uml.Element ecoreOwner) {
        if (ecoreOwner instanceof  org.eclipse.uml2.uml.ActivityPartition) {
             org.eclipse.uml2.uml.ActivityPartition owner = (org.eclipse.uml2.uml.ActivityPartition) ecoreOwner;
            owner.getNodes().add((org.eclipse.uml2.uml.ActivityNode)ecoreElt);
        
            // Setting composition relation
            Activity enclosingActivity = (Activity) AbstractObjingModelNavigation
                    .getEnclosingElement(getObjingElement(), getObjingElement().getMClass().getMetamodel().getMClass(Activity.class));
            if (enclosingActivity != null) {
                org.eclipse.uml2.uml.Element ecoreActivity = GenerationProperties.getInstance()
                        .getMappedElement(enclosingActivity);
                if (ecoreActivity != null)
                    attachToActivity(ecoreElt, ecoreActivity);
                else{
                    ecoreElt.destroy();
                    throw new NotFoundException("Owner Class not found.");
                }
            }
        } else {
            ecoreElt.destroy();
            throw new NotFoundException("Owner Class ("
                    + ecoreOwner.getClass().getSimpleName() + ") Not Found");
        }
    }

    @objid ("59a6ae32-b6e5-4460-9f46-3c95f83ee55c")
    private void attachToActivity(org.eclipse.uml2.uml.Element ecoreElt, org.eclipse.uml2.uml.Element ecoreOwner) {
        if (ecoreOwner instanceof  org.eclipse.uml2.uml.Activity) {
            ((org.eclipse.uml2.uml.Activity) ecoreOwner).getNodes().add((org.eclipse.uml2.uml.ActivityNode)ecoreElt);
        } else {
            ecoreElt.destroy();
            throw new NotFoundException("Owner Class ("
                    + ecoreOwner.getClass().getSimpleName() + ") Not Found");
        }
    }

    @objid ("90931037-bd8c-4325-abbd-22140aaf9f71")
    private void attachToClause(org.eclipse.uml2.uml.Element ecoreElt, org.eclipse.uml2.uml.Element ecoreOwner, Clause objingOwnerClause) {
        boolean isAttached = false;
        if (ecoreOwner instanceof org.eclipse.uml2.uml.Clause) {
            org.eclipse.uml2.uml.Clause owner =  (org.eclipse.uml2.uml.Clause) ecoreOwner;
        
            // A DecisionMergeNode can't have any org.eclipse.uml2.uml.Pin in Ijing => use of the
            // "body" role:
            if (ecoreElt instanceof org.eclipse.uml2.uml.ExecutableNode)
                owner.getBodies().add( (org.eclipse.uml2.uml.ExecutableNode)ecoreElt);
        
            // Setting composition relation (in org.eclipse.uml2.uml.ConditionalNode):
            ConditionalNode objingConditional = objingOwnerClause.getOwner();
        
            if (objingConditional != null) {
                org.eclipse.uml2.uml.Element ecoreConditional = GenerationProperties.getInstance()
                        .getMappedElement(objingConditional);
                if (ecoreConditional instanceof org.eclipse.uml2.uml.StructuredActivityNode) {
                    ((org.eclipse.uml2.uml.StructuredActivityNode) ecoreConditional).getNodes().add(
                                                                               (org.eclipse.uml2.uml.ActivityNode)ecoreElt);
                    isAttached = true;
                }
            }
        }
        
        if (!isAttached) {
            // Setting composition relation (in  org.eclipse.uml2.uml.Activity):
            Activity enclosingActivity = (Activity) AbstractObjingModelNavigation
                    .getEnclosingElement(getObjingElement(), getObjingElement().getMClass().getMetamodel().getMClass(Activity.class));
            if (enclosingActivity != null) {
                org.eclipse.uml2.uml.Element ecoreActivity = GenerationProperties.getInstance()
                        .getMappedElement(enclosingActivity);
                if (ecoreActivity != null)
                    attachToActivity(ecoreElt, ecoreActivity);
                else{
                    ecoreElt.destroy();
                    throw new NotFoundException("Owner Class not found.");
                }
            }
        }
    }

    @objid ("47bc0b40-7bfe-451a-a88d-d7a87d9a5140")
    private void setDecisionInput(org.eclipse.uml2.uml.DecisionNode node) {
        String decisionInput = getObjingElement().getDecisionInputBehavior();
        if (decisionInput.length() > 0) {
            org.eclipse.uml2.uml.OpaqueBehavior behavior = UMLFactory.eINSTANCE.createOpaqueBehavior();
            ObjingEAnnotation.setIsDeleted(behavior);
            behavior.getBodies().add(decisionInput);
        
            // Setting composition relation
            Activity enclosingActivity = (Activity) AbstractObjingModelNavigation
                    .getEnclosingElement(getObjingElement(), getObjingElement().getMClass().getMetamodel().getMClass(Activity.class));
        
            Package objingPkg = AbstractObjingModelNavigation
                    .getNearestPackage(enclosingActivity);
        
            if (objingPkg != null) {
                org.eclipse.uml2.uml.Package ecorePkg = (org.eclipse.uml2.uml.Package) GenerationProperties.getInstance()
                        .getMappedElement(objingPkg);
                if (ecorePkg != null)
                    ecorePkg.getPackagedElements().add(behavior);
                else{
                    node.destroy();
                    throw new NotFoundException("Owner Class not found.");
                }
            }
        
            node.setDecisionInput(behavior);
        }
    }

    @objid ("de4d4ede-448c-40fb-9b4e-8e1a30ef77af")
    @Override
    public DecisionMergeNode getObjingElement() {
        return (DecisionMergeNode) super.getObjingElement();
    }

}
