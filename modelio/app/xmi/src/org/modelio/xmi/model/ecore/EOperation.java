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

package org.modelio.xmi.model.ecore;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.MethodPassingMode;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.xmi.reverse.ReverseProperties;

@objid ("fef74c9e-d707-41ad-a875-96d1584e305f")
public class EOperation extends EBehavioralFeature {
    @objid ("f777b276-021a-4c9e-bd9c-7f6abbb6cff4")
    @Override
    public Element createObjingElt() {
        return ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createOperation();
    }

    @objid ("dfd8c873-1067-4802-9eba-3682a0f182be")
    public EOperation(org.eclipse.uml2.uml.Operation element) {
        super(element);
    }

    @objid ("cc634c68-1589-479e-a401-dde85298ec3c")
    @Override
    public void attach(Element objingElt) {
        ReverseProperties revProp = ReverseProperties.getInstance();
        org.eclipse.uml2.uml.Operation ecoreElement =  (org.eclipse.uml2.uml.Operation) getEcoreElement();
        org.eclipse.uml2.uml.Element ecoreOwner = ecoreElement.getOwner();
        Element objingOwner = (Element) revProp
                .getMappedElement(ecoreOwner);
        
        if (objingOwner instanceof Classifier){
            ((Operation) objingElt).setOwner((Classifier) objingOwner);       
        }else {
            objingElt.delete();
        }
    }

    @objid ("4cf462dc-71c7-4903-bff5-f5ea644df464")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);        
        setRedefines((Operation) objingElt);
        setPassingMode((Operation) objingElt);
    }

    @objid ("9ff7f12a-9d07-4d0c-a5e0-3ce950cc526d")
    private void setRedefines(Operation objingElt) {
        for  (org.eclipse.uml2.uml.Operation ecoreOperation : ( (org.eclipse.uml2.uml.Operation) getEcoreElement()).getRedefinedOperations()){
            Object objOperation = ReverseProperties.getInstance().getMappedElement(ecoreOperation);
            if (objOperation instanceof Operation){
                objingElt.setRedefines((Operation) objOperation);
            }
        }
    }

    @objid ("ba81ad7a-abc3-4a03-aefb-1fb14f53649d")
    private void setPassingMode(Operation objingElt) {
        if (( (org.eclipse.uml2.uml.Operation)getEcoreElement()).isQuery())
            objingElt.setPassing(MethodPassingMode.METHODIN);
        else
            objingElt.setPassing(MethodPassingMode.METHODOUT);
    }

}
