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
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.xmi.reverse.ReverseProperties;
import org.modelio.xmi.util.ObjingEAnnotation;

@objid ("9581dee9-a6d9-484e-9b6f-c8f3f08a8f23")
public class ESignal extends ENamedElement {
    @objid ("2609b749-59db-44ad-9bb1-b3f378ed40aa")
    private org.eclipse.uml2.uml.Signal ecoreElement;

    @objid ("3f92d9cb-1b61-4d0d-852b-cf94aa1fd2c6")
    @Override
    public Element createObjingElt() {
        return ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createSignal();
    }

    @objid ("c7ebad8d-b052-4684-9aa6-0a5e32aecd70")
    public ESignal(org.eclipse.uml2.uml.Signal element) {
        super(element);
        this.ecoreElement = element;
    }

    @objid ("c68b91f2-f9e9-4193-a21c-2572c9385a33")
    @Override
    public void attach(Element objingElt) {
        ReverseProperties revProp = ReverseProperties.getInstance();
        
        org.eclipse.uml2.uml.Element ecoreOwner = this.ecoreElement.getOwner();
        
        Object objingOwner = revProp.getMappedElement(ecoreOwner);
        
        if ((objingOwner != null) 
                && (objingOwner instanceof ModelTree) 
                && !((objingOwner instanceof Profile))) {
        
            ((Signal) objingElt).setOwner((ModelTree)objingOwner);
        
        }else{
            ((Signal) objingElt).setOwner(revProp.getExternalPackage());
        }
    }

    @objid ("e5c0c207-1627-48a4-828a-356d99282647")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        
        setBase((Signal) objingElt);
        
        if (ReverseProperties.getInstance().isRoundtripEnabled()) {          
            setIsEventEAnnotation((Signal) objingElt);
            setIsExceptionEAnnotation((Signal) objingElt);
        }
    }

    @objid ("b3f9979a-48e9-4744-8725-5b37060efaa1")
    private void setBase(Signal objingElt) {
        if (this.ecoreElement.getRedefinedClassifiers().size() > 0) { 
            Object objingType = ReverseProperties.getInstance().getMappedElement(this.ecoreElement.getRedefinedClassifiers().get(0));
            if ((objingType != null) && (objingType instanceof GeneralClass))
                objingElt.setBase((GeneralClass) objingType);      
        }
    }

    @objid ("e04c5d34-eb07-47b8-96a9-5efe52b4bff0")
    private void setIsEventEAnnotation(Signal objingElt) {
        objingElt.setIsEvent(ObjingEAnnotation.isEvent((this.ecoreElement)));
    }

    @objid ("cb5ceebf-6cb4-48eb-998c-d83232e885a5")
    private void setIsExceptionEAnnotation(Signal objingElt) {
        objingElt.setIsException(ObjingEAnnotation.isException((this.ecoreElement)));
    }

}
