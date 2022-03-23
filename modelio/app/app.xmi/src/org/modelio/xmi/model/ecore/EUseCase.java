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
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.xmi.reverse.ReverseProperties;

@objid ("14e37e62-cc25-4b1d-a45b-6ab4b617a013")
public class EUseCase extends ENamedElement {
    @objid ("96ef088c-f885-48b0-9971-7d4aed597827")
    private org.eclipse.uml2.uml.UseCase ecoreElement = null;

    @objid ("81fd61bd-03a3-4d57-bf84-b54e880eec17")
    @Override
    public Element createObjingElt() {
        return  ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createUseCase();
    }

    @objid ("bc7d746d-be3f-4357-ac96-e231ca3fd29e")
    public  EUseCase(org.eclipse.uml2.uml.UseCase element) {
        super(element);
        this.ecoreElement = element;
        
    }

    @objid ("2c484edf-f20c-4323-9cdb-533f9b34cf2d")
    @Override
    public void attach(Element objingElt) {
        ReverseProperties revProp = ReverseProperties.getInstance();
        
        org.eclipse.uml2.uml.Element ecoreOwner = this.ecoreElement.getOwner();
        
        Object objingOwner = revProp.getMappedElement(ecoreOwner);
        
        if ( (objingOwner != null) 
                && (objingOwner instanceof ModelTree) 
                && !(objingOwner instanceof Profile)) {
            ((UseCase) objingElt).setOwner((ModelTree)objingOwner);
        }else{
            ((UseCase) objingElt).setOwner(revProp.getExternalPackage());
        }
        
    }

}
