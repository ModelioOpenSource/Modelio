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
import org.modelio.metamodel.uml.behavior.activityModel.ForkJoinNode;
import org.modelio.metamodel.uml.behavior.activityModel.StructuredActivityNode;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.util.AbstractObjingModelNavigation;
import org.modelio.xmi.util.NotFoundException;
import org.modelio.xmi.util.ObjingEAnnotation;

@objid ("c180f781-5794-4ff7-80fa-cf294c9d0b53")
public class OForkJoinNode extends OActivityNode {
    @objid ("49816fd2-3219-44dc-a54f-77caad185629")
    private boolean partialCreation = true;

    @objid ("bfb6165e-15d4-4ef6-a56b-d0aacb8817ee")
    private boolean isForkNode = false;

    @objid ("2fb9433a-a36f-446f-988e-625e9746fb27")
    private boolean isJoinNode = false;

    @objid ("86ecb341-ab9d-472b-b76c-6c0f63e88755")
    private org.eclipse.uml2.uml.ForkNode forkNode = null;

    @objid ("f124c2b4-9689-45e2-bfa1-dc5a1d090b70")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        this.partialCreation= false;
        
        // If the ForkJoin is not only Fork or Join, we create a special
        // structure:
        // a Join connected to a Fork by a flow.
        if (this.isJoinNode)
            return UMLFactory.eINSTANCE.createJoinNode();
        else if (this.isForkNode)
            return UMLFactory.eINSTANCE.createForkNode();
        else
            return UMLFactory.eINSTANCE.createJoinNode();
    }

    @objid ("eaefdaa8-32b5-4530-a5e5-ffac099480dc")
    public OForkJoinNode(ForkJoinNode element) {
        super(element);
        this.isJoinNode = (AbstractObjingModelNavigation.isJoinNode(element));
        this.isForkNode = (AbstractObjingModelNavigation.isForkNode(element));
    }

    @objid ("61ccf939-9d03-4dc9-84a5-ab9d5d90976d")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        if (!this.isJoinNode && !this.isForkNode) {
            if (!this.partialCreation) {
                createStructure((org.eclipse.uml2.uml.JoinNode) ecoreElt);
            } else {
                // The structure has already been created in the partial
                // creation
                // method.
                this.forkNode = getForkNode((org.eclipse.uml2.uml.JoinNode) ecoreElt);
            }
        }
        
        MObject objingOwner = getObjingElement().getCompositionOwner();
        org.eclipse.uml2.uml.Element ecoreOwner = GenerationProperties.getInstance().getMappedElement(objingOwner);
        
        if (ecoreOwner != null) {
            if (objingOwner instanceof StructuredActivityNode) {
                attachToStructuredActivityNode(ecoreElt, ecoreOwner);
                if (!this.isJoinNode && !this.isForkNode)
                    attachToStructuredActivityNode(this.forkNode, ecoreOwner);
            } else if (objingOwner instanceof ActivityPartition) {
                attachToActivityPartition(ecoreElt, ecoreOwner);
                if (!this.isJoinNode && !this.isForkNode)
                    attachToActivityPartition(this.forkNode, ecoreOwner);
            } else if (objingOwner instanceof Activity) {
                attachToActivity(ecoreElt, ecoreOwner);
                if (!this.isJoinNode && !this.isForkNode)
                    attachToActivity(this.forkNode, ecoreOwner);
            } else if (objingOwner instanceof Clause) {
                attachToClause(ecoreElt, ecoreOwner, (Clause) objingOwner);
                if (!this.isJoinNode && !this.isForkNode)
                    attachToClause(this.forkNode, ecoreOwner, (Clause) objingOwner);
            }
        }
    }

    @objid ("82f06cbb-b7fa-4d25-90ca-a02cbf78cae6")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
        
        if (this.isJoinNode) {
            setJoinSpec((org.eclipse.uml2.uml.JoinNode) ecoreElt);
            setCombineDuplicate((org.eclipse.uml2.uml.JoinNode) ecoreElt);
        }else{
            ObjingEAnnotation.setJoinSpec(ecoreElt, getObjingElement().getJoinSpec());
            ObjingEAnnotation.setIsCombineDuplicate(ecoreElt, getObjingElement().isIsCombineDuplicate());
        }
               
        if (!this.isJoinNode && !this.isForkNode) {
            setName(this.forkNode);
        }
    }

    @objid ("4dffc681-6df4-49d2-976e-1fe0d5d3cc30")
    private void createStructure(org.eclipse.uml2.uml.JoinNode joinNode) {
        this.forkNode = UMLFactory.eINSTANCE.createForkNode();
        
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
        
        ecoreFlow.setSource(joinNode);
        ecoreFlow.setTarget(this.forkNode);
    }

    @objid ("93b9ddb4-8b2e-48b1-89cc-f25c9c12a7f4")
    private org.eclipse.uml2.uml.ForkNode getForkNode(org.eclipse.uml2.uml.JoinNode joinNode) {
        EList<?> edgeList = joinNode.getOutgoings();
        if (edgeList != null && edgeList.size() > 0) {
             org.eclipse.uml2.uml.ActivityEdge flow = (org.eclipse.uml2.uml.ActivityEdge) edgeList.get(0);
             org.eclipse.uml2.uml.ActivityNode forknode = flow.getTarget();
            if (forknode instanceof org.eclipse.uml2.uml.ForkNode)
                return (org.eclipse.uml2.uml.ForkNode) forknode;
        }
        return null;
    }

    @objid ("8890a3da-c929-4fc1-93e2-0c746e3d5fe0")
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

    @objid ("e4dbb38f-1fbe-4814-97f1-bb304cd7c674")
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

    @objid ("2fc67986-6cb1-46af-af20-bdd936162799")
    private void attachToActivity(org.eclipse.uml2.uml.Element ecoreElt, org.eclipse.uml2.uml.Element ecoreOwner) {
        if (ecoreOwner instanceof  org.eclipse.uml2.uml.Activity) {
             org.eclipse.uml2.uml.Activity owner = (org.eclipse.uml2.uml.Activity) ecoreOwner;
            owner.getNodes().add((org.eclipse.uml2.uml.ActivityNode)ecoreElt);
        } else {
            ecoreElt.destroy();
            throw new NotFoundException("Owner Class ("
                    + ecoreOwner.getClass().getSimpleName() + ") Not Found");
        }
    }

    @objid ("ce794c6b-5782-4922-830b-440c91ac93f3")
    private void attachToClause(org.eclipse.uml2.uml.Element ecoreElt, org.eclipse.uml2.uml.Element ecoreOwner, Clause objingOwnerClause) {
        boolean isAttached = false;
        if (ecoreOwner instanceof org.eclipse.uml2.uml.Clause) {
            org.eclipse.uml2.uml.Clause owner =  (org.eclipse.uml2.uml.Clause) ecoreOwner;
        
            // A ForkJoinNode can't have any org.eclipse.uml2.uml.Pin in Modelio => use of the
            // "body" role:
            if (ecoreElt instanceof org.eclipse.uml2.uml.ExecutableNode)
                owner.getBodies().add( (org.eclipse.uml2.uml.ExecutableNode) ecoreElt);
        
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

    @objid ("c9538739-e0d4-4949-9173-56983457980a")
    private void setName(org.eclipse.uml2.uml.ControlNode node) {
        if (AbstractObjingModelNavigation.isNotNullOrEmpty(getObjingElement().getName()))
            node.setName(getObjingElement().getName());
    }

    @objid ("7921b9c4-ae5d-4270-b9cb-b161af49dcad")
    private void setJoinSpec(org.eclipse.uml2.uml.JoinNode node) {
        String value = getObjingElement().getJoinSpec();
        if ((value != null)&& (!value.equals(""))){
            org.eclipse.uml2.uml.LiteralString joinSpec = UMLFactory.eINSTANCE.createLiteralString();
            joinSpec.setValue(value);
            node.setJoinSpec(joinSpec);
        }
    }

    @objid ("33d23768-b0ab-4a29-a8bf-b3ec14b9fa10")
    private void setCombineDuplicate(org.eclipse.uml2.uml.JoinNode node) {
        node.setIsCombineDuplicate(getObjingElement().isIsCombineDuplicate());
    }

    @objid ("f967e49e-216c-4b61-b224-41122f420e41")
    @Override
    public ForkJoinNode getObjingElement() {
        return (ForkJoinNode) super.getObjingElement();
    }

}
