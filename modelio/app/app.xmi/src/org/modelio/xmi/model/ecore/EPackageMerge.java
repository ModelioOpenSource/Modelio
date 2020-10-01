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
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.uml.statik.PackageMerge;
import org.modelio.xmi.reverse.ReverseProperties;
import org.modelio.xmi.util.ObjingEAnnotation;

/**
 * This class is in charge of the Ecore org.eclipse.uml2.uml.PackageMerge import
 * @author ebrosse
 */
@objid ("f135ef20-329c-439b-8aa2-03be1500fb55")
public class EPackageMerge extends EElement {
    @objid ("34099d1f-fcf5-47c2-96b2-637f28555c82")
    private org.eclipse.uml2.uml.PackageMerge ecoreElement = null;

    @objid ("2e65987b-3f91-4379-baac-af493974154f")
    @Override
    public Element createObjingElt() {
        return  ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createPackageMerge();
    }

    @objid ("691ad382-3c3a-47b4-ae58-3c72a77e3c5b")
    public EPackageMerge(org.eclipse.uml2.uml.PackageMerge element) {
        super(element);
        this.ecoreElement = element;
    }

    @objid ("c70db464-db13-4995-9196-0b55a7b7175b")
    @Override
    public void attach(Element objingElt) {
        ReverseProperties revProp = ReverseProperties.getInstance();
        
        //  take the ecore Imported and Importing
        org.eclipse.uml2.uml.Package ecoreMergedPackage = this.ecoreElement.getMergedPackage();
        org.eclipse.uml2.uml.Package ecoreReceivingPackage = this.ecoreElement.getReceivingPackage();
        
        Package objingMergedPackage = (Package) revProp
                .getMappedElement(ecoreMergedPackage);
        Package objingReceivingPackage = (Package) revProp
                .getMappedElement(ecoreReceivingPackage);
        
        if (objingMergedPackage != null && objingReceivingPackage != null) {
            PackageMerge objingPMImport = (PackageMerge) objingElt;
            objingPMImport.setMergedPackage(objingMergedPackage);
            objingPMImport.setReceivingPackage(objingReceivingPackage);
        }
    }

    @objid ("868c76d5-fd0d-49eb-ac5e-5de3c65e054b")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        setName((PackageMerge) objingElt);
    }

    @objid ("30062852-0ed4-45d4-9ddf-9af841b36e35")
    private void setName(final PackageMerge objingElt) {
        objingElt.setName(ObjingEAnnotation.getName(this.ecoreElement));
    }

}
