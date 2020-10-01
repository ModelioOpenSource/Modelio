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

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.behavior.activityModel.CallAction;
import org.modelio.metamodel.uml.behavior.activityModel.OutputPin;
import org.modelio.metamodel.uml.behavior.activityModel.Pin;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.xmi.reverse.ReverseProperties;
import org.modelio.xmi.util.EcoreModelNavigation;
import org.modelio.xmi.util.ObjingEAnnotation;

@objid ("0f95f041-a539-44ae-bbb1-1f094dfcca61")
public class EPin extends EActivityNode {
    @objid ("ad8bad16-99ce-42fa-82ef-6594b7bc6c9a")
    @Override
    public Element createObjingElt() {
        return null;
    }

    @objid ("4caadcec-592b-4985-84be-3d3255e174e9")
    public EPin(org.eclipse.uml2.uml.Pin element) {
        super(element);
    }

    @objid ("56eae77e-9f03-4e87-97b9-a30781ea4875")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        
        // Properties defined on org.eclipse.uml2.uml.Pin
        setControl((Pin) objingElt);
        setExpansionEAnnotation((Pin) objingElt);
        setMatched((Pin) objingElt);
        setUpperBound((Pin) objingElt);
    }

    @objid ("b70e5753-cb8e-4b59-b784-0dca3152d67c")
    private void setUpperBound(final Pin pin) {
        org.eclipse.uml2.uml.Pin ecoreElement = (org.eclipse.uml2.uml.Pin) this.getEcoreElement();
         org.eclipse.uml2.uml.ValueSpecification ecoreUpperBound = ecoreElement.getUpperBound();
        if (ecoreUpperBound != null) {
            String stringValue = ecoreUpperBound.stringValue();
            if (stringValue != null)
                pin.setUpperBound(stringValue);
        }else{
            ecoreUpperBound = ecoreElement.getUpperValue();
            if (ecoreUpperBound != null) {
                String stringValue = ecoreUpperBound.stringValue();
                if (stringValue != null)
                    pin.setUpperBound(stringValue);
            }
        }
    }

    @objid ("f9ddac42-6148-469f-a104-deae36b68e3e")
    private void setControl(Pin pin) {
        pin.setIsControl(((org.eclipse.uml2.uml.Pin)getEcoreElement()).isControl());
    }

    @objid ("9a053a93-c7d0-44e3-a6c2-04cf9918136a")
    private void setExpansionEAnnotation(Pin pin) {
        pin.setIsExpansion(ObjingEAnnotation.isExpansion((org.eclipse.uml2.uml.Pin)getEcoreElement()));
    }

    @objid ("f7bf62a2-88b5-4eb0-afdf-caae5008cb02")
    private void setMatched(Pin pin) {
        org.eclipse.uml2.uml.Pin ecoreElement = (org.eclipse.uml2.uml.Pin) getEcoreElement();
        org.eclipse.uml2.uml.Element ecoreOwnerElt = ecoreElement.getOwner();
        Object obOwner = ReverseProperties.getInstance().getMappedElement(ecoreOwnerElt);
        
        if ((ecoreOwnerElt instanceof org.eclipse.uml2.uml.CallAction) && (obOwner instanceof CallAction)){
        
            List<org.eclipse.uml2.uml.Parameter> ecoreParamList = EcoreModelNavigation
                    .getMatchedParameters(ecoreElement);
        
            List<? extends org.eclipse.uml2.uml.Pin> pinList = null;
            
            if (pin instanceof OutputPin)
                pinList = ((org.eclipse.uml2.uml.CallAction) ecoreOwnerElt).getOutputs();
            else
                pinList = ((org.eclipse.uml2.uml.CallAction) ecoreOwnerElt).getInputs();
            
            for (org.eclipse.uml2.uml.Parameter ecoreParam : ecoreParamList) {
        
                if (((ecoreElement.getType() != null)
                        && (ecoreParam.getType() != null)
                        && (ecoreElement.getType().equals(ecoreParam.getType())))
                        && (EcoreModelNavigation.getMultiplicityMax(ecoreElement).equals(EcoreModelNavigation.getMultiplicityMax(ecoreParam)))
                        && (EcoreModelNavigation.getMultiplicityMin(ecoreElement).equals(EcoreModelNavigation.getMultiplicityMin(ecoreParam)))
                        && (ecoreParamList.indexOf(ecoreParam) == pinList.indexOf(ecoreElement))
                        ){
        
                    Object obParam = ReverseProperties.getInstance()
                            .getMappedElement(ecoreParam);
        
                    if (obParam instanceof Parameter) {
                        pin.setMatched((Parameter) obParam);
                    }
                }
            }
        }
    }

}
