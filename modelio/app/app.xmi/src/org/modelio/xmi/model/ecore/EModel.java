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
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.xmi.reverse.ReverseProperties;

@objid ("84d1ae7d-765b-4454-9973-8c9f1d8cbb0d")
public class EModel extends EPackage {
    @objid ("1a0312d0-acf4-4b0b-9908-6dd65a84d662")
    private boolean isRoot = false;

    @objid ("cb7f716d-7cda-495a-a000-ef37faff8d1e")
    private org.eclipse.uml2.uml.Model ecoreElement;

    @objid ("27a141ce-342e-46db-86f8-b1dc784310b9")
    @Override
    public Element createObjingElt() {
        for (org.eclipse.uml2.uml.Package ecoremodel : ReverseProperties.getInstance().getEcoreModels()){
            if (this.ecoreElement.equals(ecoremodel)){
                this.isRoot = true;
                return ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createPackage();
            }
        }
        return ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createPackage();
    }

    @objid ("645fad64-be3f-4838-b299-af84e690d859")
    public  EModel(final org.eclipse.uml2.uml.Model element) {
        super(element);
        this.ecoreElement = element;
        
    }

    @objid ("c143572b-d3d7-47f9-bed8-358eb9ac3dab")
    @Override
    public void attach(final Element objingElt) {
        if (!this.isRoot){
            Package objingPkg = (Package) objingElt;
            ReverseProperties revProp = ReverseProperties.getInstance();
            org.eclipse.uml2.uml.Element ecoreOwner = this.ecoreElement.getOwner();
        
            if (ecoreOwner != null){
                Element objingOwner = (Element) revProp
                        .getMappedElement(ecoreOwner);
        
                if (ecoreOwner instanceof org.eclipse.uml2.uml.Model) {            
                    objingPkg.setOwner((Package) objingOwner);
                }else if (ecoreOwner instanceof org.eclipse.uml2.uml.Package) {             
                    objingPkg.setOwner((Package) objingOwner);
                }else if (ecoreOwner instanceof org.eclipse.uml2.uml.Component){
                    objingPkg.setOwner((Component) objingOwner);
                }
            }else{
                objingPkg.setOwner(ReverseProperties.getInstance().getExternalPackage());
            }
        
        }
        
    }

}
