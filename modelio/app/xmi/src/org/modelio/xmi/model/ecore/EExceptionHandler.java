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
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityAction;
import org.modelio.metamodel.uml.behavior.activityModel.ExceptionHandler;
import org.modelio.metamodel.uml.behavior.activityModel.InputPin;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.xmi.reverse.ReverseProperties;
import org.modelio.xmi.util.ObjingEAnnotation;

@objid ("70823320-f38e-4ad4-84e4-85fc56cdb6ca")
public class EExceptionHandler extends EElement {
    @objid ("3f226306-d002-4288-b782-91fd67dcfb3b")
    @Override
    public Element createObjingElt() {
        return ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createExceptionHandler();
    }

    @objid ("1f738a81-314f-49d3-9aa8-45d18d906e01")
    public EExceptionHandler(org.eclipse.uml2.uml.ExceptionHandler element) {
        super(element);
    }

    @objid ("0b59a287-8cd3-4d91-87dc-3b6fda27f52d")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        setName((ExceptionHandler)objingElt);
        setExceptionInput((ExceptionHandler)objingElt);
        setExceptionType((ExceptionHandler)objingElt);
        setProtectedNode((ExceptionHandler)objingElt);
    }

    @objid ("09dfc22f-ec8f-452f-9f43-45f3f8bc8b46")
    private void setExceptionInput(ExceptionHandler objingElt) {
        org.eclipse.uml2.uml.ObjectNode ecoreTarget = ((org.eclipse.uml2.uml.ExceptionHandler)getEcoreElement()).getExceptionInput();
        if (ecoreTarget != null) {
            Object objingTarget = ReverseProperties.getInstance().getMappedElement(ecoreTarget);
            if (objingTarget instanceof InputPin)
                 objingElt.setExceptionInput((InputPin) objingTarget);
        }
    }

    @objid ("199a0b05-c1e2-4f32-8a3b-a065f647896f")
    private void setExceptionType(ExceptionHandler objingElt) {
        for  (org.eclipse.uml2.uml.Classifier type : ((org.eclipse.uml2.uml.ExceptionHandler)getEcoreElement()).getExceptionTypes()){
            Object objType =  ReverseProperties.getInstance().getMappedElement(type);
            if (objType instanceof GeneralClass)
                objingElt.getExceptionType().add((GeneralClass) objType);
        }
    }

    @objid ("136bbe64-da41-4989-b5e7-0b1f985bb709")
    private void setProtectedNode(ExceptionHandler objingElt) {
        org.eclipse.uml2.uml.ExecutableNode ecoreSource = ((org.eclipse.uml2.uml.ExceptionHandler) getEcoreElement()).getProtectedNode();
        if (ecoreSource != null) {
            Object objingSource = ReverseProperties.getInstance().getMappedElement(ecoreSource);
            if ((objingSource != null) && (objingSource instanceof ActivityAction)){
                 objingElt.setProtectedNode((ActivityAction) objingSource);
            }
        }
    }

    @objid ("90ba9700-2994-4125-a0da-fe2091f65e67")
    private void setName(ExceptionHandler objingElt) {
        objingElt.setName(ObjingEAnnotation.getName(getEcoreElement()));
    }

}
