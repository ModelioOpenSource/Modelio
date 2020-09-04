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

package org.modelio.xmi.model.objing;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.UMLFactory;
import org.modelio.metamodel.uml.infrastructure.Substitution;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.xmi.generation.GenerationProperties;

@objid ("4e2ee150-e354-46d9-a987-262224174476")
public class OSubstitution extends OModelElement {
    @objid ("c7e4aade-efc8-4f19-bbe2-8a27b4391c64")
    private Substitution objingElement;

    @objid ("4f33c20e-8df8-4b0b-b851-aa8274559513")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return UMLFactory.eINSTANCE.createSubstitution();
    }

    @objid ("645093ff-1a29-48d8-be34-6869b8bc1da7")
    public OSubstitution(Substitution param) {
        super(param);
        this.objingElement = param;
    }

    @objid ("828719dc-b4f1-40ef-9083-bd942457190d")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        GenerationProperties genProp = GenerationProperties.getInstance();
                
        Classifier objingSubstituting = this.objingElement
                .getSubstitutingClassifier();
        Classifier objingContract = this.objingElement.getContract();
                
        if (objingSubstituting != null && objingContract != null) {
            // Gets or creates the ecore "substituting" element:
            org.eclipse.uml2.uml.Classifier ecoreSubstituting =  (org.eclipse.uml2.uml.Classifier) genProp
                    .getMappedElement(objingSubstituting);
                
            // Gets or creates the ecore "contract" element:
            org.eclipse.uml2.uml.Classifier ecoreContract =  (org.eclipse.uml2.uml.Classifier) genProp
                    .getMappedElement(objingContract);
                
            if (ecoreSubstituting != null && ecoreContract != null) {
                org.eclipse.uml2.uml.Substitution ecoreSubstitution = (org.eclipse.uml2.uml.Substitution) ecoreElt;
                
                ecoreSubstitution.setSubstitutingClassifier(ecoreSubstituting);
                ecoreSubstitution.setContract(ecoreContract);
                
                Package ecorePkg = ecoreSubstituting.getNearestPackage();
                ecorePkg.getPackagedElements().add(ecoreSubstitution);
            }
        }
    }

    @objid ("a949dce9-8a87-4b3b-bff3-056ad7ef0d1e")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
    }

}
