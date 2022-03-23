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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.uml2.uml.UMLFactory;
import org.modelio.metamodel.uml.behavior.activityModel.CallOperationAction;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.util.NotFoundException;

@objid ("e58f9067-c0b6-4ca8-9a7c-0f3fe5e745ca")
public class OCallOperationAction extends OActivityNode {
    @objid ("ba58b57f-7c62-45bd-9612-d37164e018f7")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return UMLFactory.eINSTANCE.createCallOperationAction();
    }

    @objid ("30495695-a181-4e16-8973-03f8e61766ab")
    public  OCallOperationAction(CallOperationAction element) {
        super(element);
    }

    @objid ("4dd4ac3a-f718-44fe-a104-571de6d214df")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
        setSynchronous( (org.eclipse.uml2.uml.CallOperationAction) ecoreElt);
        setOperation( (org.eclipse.uml2.uml.CallOperationAction) ecoreElt);
        
    }

    @objid ("1ba40d51-201a-4f1f-82b5-e65c843c1c26")
    private void setSynchronous(org.eclipse.uml2.uml.CallOperationAction action) {
        action.setIsSynchronous(getObjingElement().isIsSynchronous());
    }

    @objid ("0763b8bc-1583-4be6-aa26-d4f8b02828b9")
    private void setOperation(org.eclipse.uml2.uml.CallOperationAction ecoreElt) {
        Operation objingOperation = getObjingElement().getCalled();
        
        if (objingOperation != null) {
            org.eclipse.uml2.uml.Element ecoreOperation =  GenerationProperties.getInstance().getMappedElement(objingOperation);
        
            if (ecoreOperation != null) {
                if (ecoreOperation instanceof  org.eclipse.uml2.uml.Operation) {
                    ecoreElt.setOperation( (org.eclipse.uml2.uml.Operation) ecoreOperation);
                } else{
                    ecoreElt.destroy();
                    throw new NotFoundException("The  org.eclipse.uml2.uml.Operation \""
                            + objingOperation.getName()
                            + "\" has not been mapped correctly.");
                }
            }
        }
        
    }

    @objid ("e0907977-0d7c-434f-bb6a-54d78dc76250")
    @Override
    public CallOperationAction getObjingElement() {
        return (CallOperationAction) super.getObjingElement();
    }

}
