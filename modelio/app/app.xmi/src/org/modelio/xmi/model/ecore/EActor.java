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
import org.modelio.metamodel.uml.behavior.usecaseModel.Actor;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.xmi.reverse.ReverseProperties;

@objid ("8ccba823-2898-4eca-8a92-2a5597c16cb0")
public class EActor extends ENamedElement {
    @objid ("6b9d65f1-355b-4fe7-b33f-1faaaa45c413")
    @Override
    public Element createObjingElt() {
        return ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createActor();
    }

    @objid ("b762512f-5638-482b-a895-30045906c52d")
    public  EActor(org.eclipse.uml2.uml.Actor element) {
        super(element);
    }

    @objid ("c99a7630-c03a-4ff8-bf79-99bb26e745ce")
    @Override
    public void attach(Element objingElt) {
        ReverseProperties revProp = ReverseProperties.getInstance();
        
        org.eclipse.uml2.uml.Element ecoreOwner = getEcoreElement().getOwner();
        
        Object objingOwner =  revProp.getMappedElement(ecoreOwner);
        
        if ((objingOwner != null)  
                && (objingOwner instanceof ModelTree)
                && !(objingOwner instanceof Profile)) {
             ((Actor) objingElt).setOwner((ModelTree)objingOwner);
        }else{
             ((Actor) objingElt).setOwner(revProp.getExternalPackage());
        }
        
    }

}
