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
import org.eclipse.uml2.uml.Property;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.StructuralFeature;
import org.modelio.xmi.reverse.ReverseProperties;
import org.modelio.xmi.util.EcoreModelNavigation;
import org.modelio.xmi.util.ObjingEAnnotation;

@objid ("d40383e5-59ff-4114-acd2-b2baeebb2fc6")
public class EStructuralFeature extends EFeature {
    @objid ("9346978a-b7b5-40f4-8ce2-dbe4e6f81013")
    private org.eclipse.uml2.uml.StructuralFeature ecoreFeatureElt = null;

    @objid ("09ac247c-c030-4f55-ab93-8b4daa1297a7")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        if (objingElt instanceof StructuralFeature){
            setMultiplicity((StructuralFeature) objingElt);
           
        }
    }

    @objid ("006d711b-a6cc-4e8b-a941-bd82cd4ad5ad")
    public EStructuralFeature(org.eclipse.uml2.uml.StructuralFeature element) {
        super(element);
        this.ecoreFeatureElt = element;
    }

    @objid ("3f3f3b68-84e4-4344-9fa5-66087e848a1d")
    private void setMultiplicity(StructuralFeature objFeature) {
        setMultiMin(objFeature);
        setMultiMax(objFeature);
        testMutiplicity(objFeature);
    }

    @objid ("c6a2c8ba-10ca-4e74-be01-73e67d6ba77d")
    private void setMultiMax(StructuralFeature objFeature) {
        if (ReverseProperties.getInstance().isRoundtripEnabled()){
            objFeature.setMultiplicityMax(ObjingEAnnotation.getMultiMax(this.ecoreFeatureElt));
        }else{
            String multMax = EcoreModelNavigation.getMultiplicityMax(((Property) getEcoreElement()));
          
            if ("".equals(multMax)) {
                objFeature.setMultiplicityMax("undefined");
            }else{
                objFeature.setMultiplicityMax(multMax);
            }
              
        }
    }

    @objid ("e512230e-acf1-48d4-a16e-f4887f90ebe6")
    private void setMultiMin(StructuralFeature objFeature) {
        if (ReverseProperties.getInstance().isRoundtripEnabled()){
            objFeature.setMultiplicityMin(ObjingEAnnotation.getMultiMin(this.ecoreFeatureElt));
        }else{
            String multMin = EcoreModelNavigation.getMultiplicityMin(((Property) getEcoreElement()));
          
            if ("".equals(multMin)) {
                objFeature.setMultiplicityMin("undefined");
            }else{
                objFeature.setMultiplicityMin(multMin);
            }
              
        }
    }

    @objid ("ffed22d9-2a5a-45ad-858a-ff4f82dddec8")
    private void testMutiplicity(StructuralFeature feature) {
        String multMin = feature.getMultiplicityMin();
        String multMax = feature.getMultiplicityMax();
        
        if (multMin.equals("*")){
            if (multMax.equals("*")){
                feature.setMultiplicityMin("undefined");
            }else{
                feature.setMultiplicityMin(multMax);
                feature.setMultiplicityMax("*");
            }
        }
    }

}
