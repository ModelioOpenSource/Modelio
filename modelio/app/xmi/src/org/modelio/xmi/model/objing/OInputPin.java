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

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.UMLFactory;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityAction;
import org.modelio.metamodel.uml.behavior.activityModel.InputPin;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.IModelerModuleStereotypes;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.util.AbstractObjingModelNavigation;
import org.modelio.xmi.util.AttachInputPinToOwnerVisitor;

@objid ("7c4d5f92-4131-483d-8df6-7855ff25d3e3")
public class OInputPin extends OPin {
    @objid ("8724d0f4-6bc6-4842-a8b5-9319ecacc600")
    private org.eclipse.uml2.uml.Element ecoreOwnerElt = null;

    @objid ("d0d4a840-50e5-4046-b069-66cae40988e7")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        if (((InputPin)getObjingElement()).isStereotyped(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2ACTIONINPUTPIN))
            return UMLFactory.eINSTANCE.createActionInputPin();
        else{
            return UMLFactory.eINSTANCE.createInputPin();
        }
    }

    @objid ("a9533fc9-7f6a-436d-bdcd-27b555baad2b")
    private boolean allPinsAreMapped() {
        ActivityAction objingOwner = ((InputPin)getObjingElement()).getInputing();
        List<InputPin> objingInputPinList = objingOwner.getInput();
        
        if (this.ecoreOwnerElt instanceof  org.eclipse.uml2.uml.Action) {
             org.eclipse.uml2.uml.Action ecoreOwner =  (org.eclipse.uml2.uml.Action) this.ecoreOwnerElt;
            EList<?> ecoreInputPinList = ecoreOwner.getInputs();
        
            if (ecoreInputPinList != null
                    && objingInputPinList.size() == ecoreInputPinList.size())
                return true;
        }
        return false;
    }

    @objid ("d0e44cfb-4992-4adc-8f4f-84152f072b52")
    private List<InputPin> getSortedInputPinList() {
        ActivityAction objingOwner = ((InputPin)getObjingElement()).getInputing();
        
        List<Parameter> objingParamList = AbstractObjingModelNavigation
                .getRelatedParameters(objingOwner);
        
        List<InputPin> objingInputPinList = new ArrayList<>(
                objingOwner.getInput());
        
        List<InputPin> objingPinSortedList = new ArrayList<>();
        
        for (Parameter param : objingParamList) {
            InputPin matchingPin = getMatchingPin(objingInputPinList, param);
            if (matchingPin != null) {
                objingInputPinList.remove(matchingPin);
                objingPinSortedList.add(matchingPin);
            }
        }
        return objingPinSortedList;
    }

    @objid ("1360b06b-8d80-4d89-962a-0e29ef0bfcbe")
    public OInputPin(InputPin element) {
        super(element);
        this.ecoreOwnerElt = GenerationProperties.getInstance().getMappedElement(element.getInputing());
    }

    @objid ("b076ce85-3bfa-445f-897a-0c0e41df20e0")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        MObject objingOwner = getObjingElement().getCompositionOwner();
        org.eclipse.uml2.uml.Element ecoreOwner = GenerationProperties.getInstance().getMappedElement(objingOwner);
        
        if ((ecoreElt instanceof org.eclipse.uml2.uml.ExpansionNode)
                && (ecoreOwner instanceof org.eclipse.uml2.uml.StructuredActivityNode)){       
                ((org.eclipse.uml2.uml.StructuredActivityNode) ecoreOwner ).getNodes().add((org.eclipse.uml2.uml.ExpansionNode)ecoreElt);       
        }else{
        
            if (ecoreOwner != null) {
                if (objingOwner instanceof ActivityAction)
                    attachToActivityAction(ecoreElt, ecoreOwner);
                else
                    super.attach(ecoreElt);
            }
        }
    }

    @objid ("e641653c-f001-4ff9-9545-cc04aa62014a")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
        
        setMatched();
        
        if (ecoreElt instanceof  org.eclipse.uml2.uml.ActionInputPin)
            setAction( (org.eclipse.uml2.uml.ActionInputPin) ecoreElt);
    }

    @objid ("754c8fdb-cd5d-4f2e-a928-3007fd0c57c3")
    private void attachToActivityAction(org.eclipse.uml2.uml.Element ecoreElt, org.eclipse.uml2.uml.Element ecoreOwner) {
        AttachInputPinToOwnerVisitor attachInputPin = new AttachInputPinToOwnerVisitor();
        attachInputPin.attachInputPin((org.eclipse.uml2.uml.InputPin) ecoreElt, ecoreOwner, ((InputPin)getObjingElement()));
    }

    @objid ("6953abda-95cc-4b3f-ad24-7136ad30549d")
    private void setMatched() {
        if ((allPinsAreMapped())
                && (this.ecoreOwnerElt instanceof  org.eclipse.uml2.uml.CallOperationAction)){
            try{
                 org.eclipse.uml2.uml.CallOperationAction action =  (org.eclipse.uml2.uml.CallOperationAction) this.ecoreOwnerElt;
                List<InputPin> objingPinSortedList = getSortedInputPinList();
                EList<org.eclipse.uml2.uml.InputPin> ecoreInputPinList = action.getArguments();
        
                for (InputPin objingPin : objingPinSortedList) {
                    org.eclipse.uml2.uml.Element ecorePin = GenerationProperties.getInstance().getMappedElement(objingPin);
                    if (ecorePin instanceof org.eclipse.uml2.uml.InputPin) {
                        if (ecoreInputPinList.contains(ecorePin))
                            ecoreInputPinList.remove(ecorePin);
                        ecoreInputPinList.add((org.eclipse.uml2.uml.InputPin)ecorePin);
                    }
                }
            }catch(Exception e){
                Xmi.LOG.error(e);
            }
        }
    }

    @objid ("73635a7f-ba89-4eba-a732-97963adb608a")
    private InputPin getMatchingPin(List<InputPin> objingInputPinList, Parameter param) {
        for (InputPin pin : objingInputPinList) {
            if (param.equals(pin.getMatched()))
                return pin;
        }
        return null;
    }

    @objid ("9423be51-edc9-4438-8359-c102984b5070")
    private void setAction(org.eclipse.uml2.uml.ActionInputPin ecoreElt) {
        org.eclipse.uml2.uml.Element element = ecoreElt.getOwner();
        if (element instanceof  org.eclipse.uml2.uml.Action){
            try{
                ecoreElt.setFromAction( (org.eclipse.uml2.uml.Action) element);
            }catch(Exception e){
                Xmi.LOG.error(Xmi.PLUGIN_ID, e);
            }
        }
    }

}
