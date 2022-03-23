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
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.module.modelermodule.api.xmi.standard.operation.UML2RedefinableTemplateSignature;
import org.modelio.xmi.reverse.ReverseProperties;
import org.modelio.xmi.util.ObjingEAnnotation;

@objid ("dab4929e-436e-4325-aa65-5066ee60e1c0")
public class ERedefinableTemplateSignature extends ENamedElement {
    @objid ("c731b691-2862-4047-a1f6-d136d13dfee1")
    private org.eclipse.uml2.uml.RedefinableTemplateSignature ecoreElement = null;

    @objid ("b616ce28-1afe-49f4-ab44-0ddbd5944465")
    @Override
    public Element createObjingElt() {
        org.eclipse.uml2.uml.Element ecoreOwner = this.ecoreElement.getOwner();
        Object objingOwner =  ReverseProperties.getInstance().getMappedElement(ecoreOwner);
        
        if ((objingOwner instanceof Classifier)
                && !(objingOwner instanceof Enumeration)
                && (!ObjingEAnnotation.isDeleted(this.ecoreElement))){
            return UML2RedefinableTemplateSignature.create().getElement();
        }else {
            return null;
        }
        
    }

    @objid ("db7b17f4-809d-4f4d-a8b1-2fbd629ba607")
    public  ERedefinableTemplateSignature(org.eclipse.uml2.uml.RedefinableTemplateSignature element) {
        super(element);
        this.ecoreElement = element;
        
    }

    @objid ("f95841d6-98e0-429f-a7ac-4971fec114aa")
    @Override
    public void attach(Element objingElt) {
        if (objingElt != null) {
            ReverseProperties revProp = ReverseProperties.getInstance();
        
            org.eclipse.uml2.uml.Element ecoreOwner = this.ecoreElement.getOwner();
            Element objingOwner = (Element) revProp
                    .getMappedElement(ecoreOwner);
        
            if (objingOwner instanceof Classifier)
                ((Operation) objingElt).setOwner((Classifier) objingOwner);
            else {
                objingElt.delete();
            }
        }
        
    }

}
