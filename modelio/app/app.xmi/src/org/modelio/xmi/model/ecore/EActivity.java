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
package org.modelio.xmi.model.ecore;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.behavior.activityModel.Activity;
import org.modelio.metamodel.uml.behavior.stateMachineModel.InternalTransition;
import org.modelio.metamodel.uml.behavior.stateMachineModel.State;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Transition;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.xmi.reverse.ReverseProperties;
import org.modelio.xmi.util.EcoreModelNavigation;

@objid ("62d4e77a-9768-44c5-a5e1-eca104234a4a")
public class EActivity extends ENamedElement {
    @objid ("201b88d4-3186-41dc-9c68-5336d3709e7d")
    @Override
    public Element createObjingElt() {
        return ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createActivity();
    }

    @objid ("c7d725e7-6d2d-4ac8-ae6d-44f1b1a145b5")
    public  EActivity(org.eclipse.uml2.uml.Activity element) {
        super(element);
    }

    @objid ("7e57e89b-7bde-4483-a346-ee3babd15acc")
    @Override
    public void attach(Element objingElt) {
        ReverseProperties revProp = ReverseProperties.getInstance();
        org.eclipse.uml2.uml.Activity ecoreElement = (org.eclipse.uml2.uml.Activity) getEcoreElement();
        org.eclipse.uml2.uml.Element ecoreOwner = getEcoreElement().getOwner();
        Activity objingAct = (Activity) objingElt;
        NameSpace objingOwner = null; 
        
        if (ecoreOwner != null) {
            Element tempOwner = (Element) revProp.getMappedElement(ecoreOwner);
        
            if (tempOwner != null) {
                if (tempOwner instanceof NameSpace){
                    objingOwner = (NameSpace) tempOwner;
                }else if (tempOwner instanceof State){
        
                    objingOwner = EcoreModelNavigation.getNearestNameSpace(ecoreOwner);
        
                    org.eclipse.uml2.uml. Behavior ent = ((org.eclipse.uml2.uml.State)ecoreOwner).getEntry();
                    org.eclipse.uml2.uml. Behavior exit = ((org.eclipse.uml2.uml.State)ecoreOwner).getExit();
                    org.eclipse.uml2.uml. Behavior doActivity = ((org.eclipse.uml2.uml.State)ecoreOwner).getDoActivity();
        
                    IStandardModelFactory factory = ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class);
                    if ((ent != null) && (ent.equals(ecoreElement))){
                        InternalTransition transition = factory.createInternalTransition();
                        transition.setSComposed((State)tempOwner);
                        transition.setBehaviorEffect(objingAct);
                        transition.setReceivedEvents("Entry");
                    }else if ((exit != null) && (exit.equals(ecoreElement))){    
                        InternalTransition transition = factory.createInternalTransition();
                        transition.setSComposed((State)tempOwner);
                        transition.setBehaviorEffect(objingAct);
                        transition.setReceivedEvents("Exit");
                    }else if ((doActivity != null) && (doActivity.equals(ecoreElement))){
                        InternalTransition transition = factory.createInternalTransition();
                        transition.setSComposed((State) tempOwner);
                        transition.setBehaviorEffect(objingAct);
                        transition.setReceivedEvents("Do");
                    }
                    
                }else if (tempOwner instanceof Transition){
                    ((Transition)tempOwner).setBehaviorEffect(objingAct);
                    objingOwner = EcoreModelNavigation.getNearestNameSpace(ecoreOwner);
                }
            }
        
        }
        
        if (objingOwner != null){
            objingAct.setOwner(objingOwner);
        }else {
            objingAct.setOwner(ReverseProperties.getInstance().getExternalPackage());
        }
        
    }

    @objid ("442a4fde-9f46-44d5-95a8-3285c0cfc33c")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        setReadOnly((Activity) objingElt);
        setSingleExecution((Activity) objingElt);
        setReentrant((Activity) objingElt);
        
    }

    @objid ("63111aa0-264d-403b-b4ca-bfdd6426557a")
    private void setReadOnly(Activity activity) {
        activity.setIsReadOnly(((org.eclipse.uml2.uml.Activity)getEcoreElement()).isReadOnly());
    }

    @objid ("e4a227eb-2757-48d4-925c-763b5d248f84")
    private void setSingleExecution(Activity activity) {
        activity.setIsSingleExecution(((org.eclipse.uml2.uml.Activity)getEcoreElement()).isSingleExecution());
    }

    @objid ("79bc0d97-e272-4daa-a850-cb96ccb877a8")
    private void setReentrant(Activity activity) {
        activity.setIsReentrant(((org.eclipse.uml2.uml.Activity)getEcoreElement()).isReentrant());
    }

}
