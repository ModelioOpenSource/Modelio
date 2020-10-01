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

package org.modelio.xmi.model.objing;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.OutputPin;
import org.eclipse.uml2.uml.UMLFactory;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityAction;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.util.AbstractObjingModelNavigation;
import org.modelio.xmi.util.AttachOutputPinToOwnerVisitor;

@objid ("da4a48c7-e260-42d7-927a-28f672fbb87f")
public class OOutputPin extends OPin {
    @objid ("c00c33eb-1dba-4859-9e98-7fe7f91fc323")
    private org.eclipse.uml2.uml.Element ecoreOwnerElt = null;

    @objid ("3ee5a3e1-4e39-4458-b379-508ecd0d352b")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return UMLFactory.eINSTANCE.createOutputPin();
    }

    @objid ("9e96894a-c1db-4e0a-8035-a8108009efa0")
    private boolean allPinsAreMapped() {
        ActivityAction objingOwner = ((org.modelio.metamodel.uml.behavior.activityModel.OutputPin)getObjingElement()).getOutputing();
        List<org.modelio.metamodel.uml.behavior.activityModel.OutputPin> objingOutputPinList = objingOwner.getOutput();
        
        if (this.ecoreOwnerElt instanceof  org.eclipse.uml2.uml.Action) {
             org.eclipse.uml2.uml.Action ecoreOwner =  (org.eclipse.uml2.uml.Action) this.ecoreOwnerElt;
            EList<?> ecoreOutputPinList = ecoreOwner.getOutputs();
        
            if (ecoreOutputPinList != null
                    && objingOutputPinList.size() == ecoreOutputPinList.size())
                return true;
        }
        return false;
    }

    @objid ("df18f0a6-572d-42ad-9917-d678487e1f8f")
    private List<org.modelio.metamodel.uml.behavior.activityModel.OutputPin> getSortedOutputPinList() {
        ActivityAction objingOwner = ((org.modelio.metamodel.uml.behavior.activityModel.OutputPin)getObjingElement()).getOutputing();
        
        List<Parameter> objingParamList = AbstractObjingModelNavigation
                .getRelatedParameters(objingOwner);
        
        List<org.modelio.metamodel.uml.behavior.activityModel.OutputPin> objingOutputPinList = new ArrayList<>(
                objingOwner.getOutput());
        
        List<org.modelio.metamodel.uml.behavior.activityModel.OutputPin> objingPinSortedList = new ArrayList<>();
        
        for (Parameter param : objingParamList) {
            org.modelio.metamodel.uml.behavior.activityModel.OutputPin matchingPin = getMatchingPin(objingOutputPinList, param);
            if (matchingPin != null) {
                objingOutputPinList.remove(matchingPin);
                objingPinSortedList.add(matchingPin);
            }
        }
        return objingPinSortedList;
    }

    @objid ("e20df4cd-28e6-446a-a22b-771c5cd58150")
    public OOutputPin(org.modelio.metamodel.uml.behavior.activityModel.OutputPin element) {
        super(element);
        this.ecoreOwnerElt = GenerationProperties.getInstance().getMappedElement(element.getOutputing());
    }

    @objid ("2bbf0320-25a9-451a-86a5-c7e2e3a44625")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        MObject objingOwner = (((org.modelio.metamodel.uml.behavior.activityModel.OutputPin) getObjingElement()).getCompositionOwner());
        org.eclipse.uml2.uml.Element ecoreOwner = GenerationProperties.getInstance().getMappedElement(objingOwner);
        
        if (ecoreOwner != null) {
            if (objingOwner instanceof ActivityAction)
                attachToActivityAction(ecoreElt, ecoreOwner);
            else
                super.attach(ecoreElt);
        }
    }

    @objid ("943a2e71-944d-4535-8233-5eb06b7cd75c")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
        if (ecoreElt instanceof org.eclipse.uml2.uml.ExpansionNode){
            setOutputRegion((org.eclipse.uml2.uml.ExpansionNode) ecoreElt);
        }
        
        setMatched();
    }

    @objid ("41255ced-7c50-4b96-b99c-4b53bf146cc2")
    private void attachToActivityAction(org.eclipse.uml2.uml.Element ecoreElt, org.eclipse.uml2.uml.Element ecoreOwner) {
        AttachOutputPinToOwnerVisitor attachOutputPin = new AttachOutputPinToOwnerVisitor();
        attachOutputPin.attachOutputPin((org.eclipse.uml2.uml.OutputPin) ecoreElt, (org.modelio.metamodel.uml.behavior.activityModel.OutputPin) getObjingElement(), ecoreOwner);
    }

    @objid ("5e6c9130-fac5-4156-a47a-81dd7191ef38")
    private void setMatched() {
        if ((allPinsAreMapped()) && (this.ecoreOwnerElt instanceof  org.eclipse.uml2.uml.CallOperationAction)){
            List<org.modelio.metamodel.uml.behavior.activityModel.OutputPin> objingPinSortedList = getSortedOutputPinList();
            List<OutputPin> ecoreOutputPinList = ( (org.eclipse.uml2.uml.CallOperationAction) this.ecoreOwnerElt).getResults();
        
            for (org.modelio.metamodel.uml.behavior.activityModel.OutputPin objingPin : objingPinSortedList) {
                org.eclipse.uml2.uml.Element ecorePin = GenerationProperties.getInstance().getMappedElement(objingPin);
                if (ecorePin instanceof org.eclipse.uml2.uml.OutputPin) {
                    if (ecoreOutputPinList.contains(ecorePin))
                        ecoreOutputPinList.remove(ecorePin);
        
                    ecoreOutputPinList.add((org.eclipse.uml2.uml.OutputPin)ecorePin);
                }
            }
        }
    }

    @objid ("8a256732-c907-4f4f-9362-c9e326fb5b19")
    private org.modelio.metamodel.uml.behavior.activityModel.OutputPin getMatchingPin(List<org.modelio.metamodel.uml.behavior.activityModel.OutputPin> objingOutputPinList, Parameter param) {
        for (org.modelio.metamodel.uml.behavior.activityModel.OutputPin pin : objingOutputPinList) {
            if (param.equals(pin.getMatched()))
                return pin;
        }
        return null;
    }

    @objid ("47d40115-df56-4c9a-85b8-64c4cf202101")
    private void setOutputRegion(org.eclipse.uml2.uml.ExpansionNode ecoreElt) {
        if (ecoreElt.getOwner() instanceof org.eclipse.uml2.uml.ExpansionRegion)
            ecoreElt.setRegionAsOutput((org.eclipse.uml2.uml.ExpansionRegion)ecoreElt.getOwner());
    }

}
