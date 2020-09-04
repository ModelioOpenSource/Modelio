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

package org.modelio.xmi.model.ecore;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.ObjectNodeOrderingKind;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.behavior.activityModel.Activity;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityParameterNode;
import org.modelio.metamodel.uml.behavior.commonBehaviors.BehaviorParameter;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.xmi.reverse.ReverseProperties;
import org.modelio.xmi.util.EcoreModelNavigation;

@objid ("eb0142f7-a3cf-4823-8b0f-5757b54b0d06")
public class EActivityParameterNode extends EActivityNode {
    @objid ("a82c3608-ace3-4484-9382-2e7da9c10016")
    @Override
    public Element createObjingElt() {
        return ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createActivityParameterNode();
    }

    @objid ("fadd7ee0-e83f-4fa9-9b21-9e60c856debe")
    public EActivityParameterNode(org.eclipse.uml2.uml.ActivityParameterNode element) {
        super(element);
    }

    @objid ("568fb676-7b79-4a4d-a443-2dd31a6a72ea")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        
        setControlType((ActivityParameterNode) objingElt);
        setOrdering((ActivityParameterNode) objingElt);
        setSelectionBehavior((ActivityParameterNode) objingElt);
        setUpperBound((ActivityParameterNode) objingElt);
        setType((ActivityParameterNode) objingElt);
        setRepresentedRealParameter((ActivityParameterNode) objingElt);
        setState((ActivityParameterNode) objingElt);
    }

    @objid ("b5173f45-9e48-44ad-b406-351d5dc0ce46")
    private void setControlType(ActivityParameterNode node) {
        node.setIsControlType(((org.eclipse.uml2.uml.ActivityParameterNode)getEcoreElement()).isControlType());
    }

    @objid ("21b62c67-943f-4499-8c27-c4983b95c21c")
    private void setOrdering(ActivityParameterNode node) {
        org.eclipse.uml2.uml.ActivityParameterNode ecoreElement    = ((org.eclipse.uml2.uml.ActivityParameterNode)getEcoreElement());
           switch (ecoreElement.getOrdering().getValue()) {
          
         
           case ObjectNodeOrderingKind.LIFO:
               node.setOrdering(org.modelio.metamodel.uml.behavior.activityModel.ObjectNodeOrderingKind.LIFO);
               break;
           case ObjectNodeOrderingKind.ORDERED:
               node.setOrdering(org.modelio.metamodel.uml.behavior.activityModel.ObjectNodeOrderingKind.ORDERED);
               break;
           case ObjectNodeOrderingKind.UNORDERED:
               node.setOrdering(org.modelio.metamodel.uml.behavior.activityModel.ObjectNodeOrderingKind.UNORDERED);
               break;
           default:
               node.setOrdering(org.modelio.metamodel.uml.behavior.activityModel.ObjectNodeOrderingKind.FIFO);
           }
    }

    @objid ("f8667b90-8f06-464f-83cc-99f38262a958")
    private void setSelectionBehavior(ActivityParameterNode node) {
        org.eclipse.uml2.uml. Behavior ecoreBehavior = ((org.eclipse.uml2.uml.ActivityParameterNode)getEcoreElement()).getSelection();
        if (ecoreBehavior instanceof org.eclipse.uml2.uml.OpaqueBehavior) {
            String objingBehavior = "";
            for (Object body : ((org.eclipse.uml2.uml.OpaqueBehavior) ecoreBehavior).getBodies()) {
                objingBehavior = objingBehavior.concat((String) body);
            }
            node.setSelectionBehavior(objingBehavior);
        } else if (ecoreBehavior != null) {
            String behaviorName = ecoreBehavior.getName();
            if (behaviorName != null)
                node.setSelectionBehavior(behaviorName);
        }
    }

    @objid ("e6343791-d0e5-4986-92a8-36bf76946b07")
    private void setUpperBound(ActivityParameterNode node) {
        org.eclipse.uml2.uml.ValueSpecification ecoreUpperBound = ((org.eclipse.uml2.uml.ActivityParameterNode)getEcoreElement()).getUpperBound();
                if (ecoreUpperBound != null) {
           String stringValue = ecoreUpperBound.stringValue();
           if (stringValue != null)
               node.setUpperBound(stringValue);
                }
    }

    @objid ("15f77a08-d749-4f20-b902-025225ccdb3f")
    private void setType(ActivityParameterNode node) {
        org.eclipse.uml2.uml.Type ecoreType = ((org.eclipse.uml2.uml.ActivityParameterNode)getEcoreElement()).getType();
        if (ecoreType != null) {
            node.setType((GeneralClass) ReverseProperties.getInstance().getMappedElement(ecoreType)); 
        }
    }

    @objid ("5dac0ad1-4f33-4597-8b2e-123190e1e303")
    private void setRepresentedRealParameter(ActivityParameterNode node) {
        org.eclipse.uml2.uml.Parameter ecoreParam = ((org.eclipse.uml2.uml.ActivityParameterNode)getEcoreElement()).getParameter();
        if (ecoreParam != null) {
            Object objingParam =  ReverseProperties.getInstance().getMappedElement(ecoreParam);
            if (objingParam instanceof Parameter) {
                 org.eclipse.uml2.uml.Activity ecoreActivity = EcoreModelNavigation
                .getOwnerActivity(((org.eclipse.uml2.uml.ActivityParameterNode)getEcoreElement()));
                if (ecoreActivity != null) {
                    Object objingActivity =  ReverseProperties.getInstance()
                    .getMappedElement(ecoreActivity);
                    if (objingActivity instanceof Activity) {
                        BehaviorParameter objingBehaviorParam = null;
                
                        if (objingParam instanceof BehaviorParameter)
                            objingBehaviorParam = (BehaviorParameter) objingParam;
                        else
                            objingBehaviorParam = ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createBehaviorParameter();
                
                        objingBehaviorParam.setOwner((Activity) objingActivity);
                
                        String name = ecoreParam.getName();
                        if (EcoreModelNavigation.isNotNull(name))
                            objingBehaviorParam.setName(name);
                        else 
                            objingBehaviorParam.setName("");
                
                        objingBehaviorParam
                        .setMapped((Parameter) objingParam);
                        node.setRepresentedRealParameter(objingBehaviorParam);
                
                        org.eclipse.uml2.uml.Type ecoreType = ecoreParam.getType();
                        if (ecoreType != null) {
                            Object objingType =  ReverseProperties.getInstance()
                            .getMappedElement(ecoreType);
                            if (objingType instanceof GeneralClass)
                                objingBehaviorParam
                                .setType((GeneralClass) objingType);
                        }
                    }
                }
            }
        }
    }

    @objid ("394b8d1d-0cc6-4571-b9a5-9fb4b9c753d2")
    private void setState(ActivityParameterNode node) {
        EList<?> ecoreStates = ((org.eclipse.uml2.uml.ActivityParameterNode)getEcoreElement()).getInStates();
        if (ecoreStates != null && ecoreStates.size() > 0) {
            org.eclipse.uml2.uml.State ecoreState = (org.eclipse.uml2.uml.State) ecoreStates.get(0);
            if (ecoreState != null) {
                Object objingState =  ReverseProperties.getInstance().getMappedElement(ecoreState);
                if (objingState instanceof State)
                    node.setInState((State) objingState);
            }
        }
    }

}
