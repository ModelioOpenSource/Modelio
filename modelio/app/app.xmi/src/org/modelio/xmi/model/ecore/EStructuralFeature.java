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

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Property;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.KindOfAccess;
import org.modelio.metamodel.uml.statik.StructuralFeature;
import org.modelio.xmi.reverse.ReverseProperties;
import org.modelio.xmi.util.EcoreModelNavigation;
import org.modelio.xmi.util.ObjingEAnnotation;

@objid ("d40383e5-59ff-4114-acd2-b2baeebb2fc6")
public class EStructuralFeature extends EFeature {
    @objid ("09ac247c-c030-4f55-ab93-8b4daa1297a7")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        
        boolean roundTripEnable = ReverseProperties.getInstance().isRoundtripEnabled();
        
        if (objingElt instanceof StructuralFeature){
        
            StructuralFeature structFeature = (StructuralFeature) objingElt;
        
            setMultiplicity(structFeature);
            setOrdered(structFeature);
            setUnique(structFeature);
            setAccessMode(structFeature);
            setDerived(structFeature);
        
            if (roundTripEnable) {
                setChangeableEAnnotation(structFeature);
            }
        }else  if (objingElt instanceof Instance){
            setInstance((Instance) objingElt);
        }
        
    }

    @objid ("006d711b-a6cc-4e8b-a941-bd82cd4ad5ad")
    public  EStructuralFeature(org.eclipse.uml2.uml.StructuralFeature element) {
        super(element);
    }

    @objid ("3f3f3b68-84e4-4344-9fa5-66087e848a1d")
    private void setMultiplicity(StructuralFeature objFeature) {
        String multMin = EcoreModelNavigation.getMultiplicityMin(getEcoreElement());
        String multMax = EcoreModelNavigation.getMultiplicityMax(getEcoreElement());
        
        if (!("".equals(multMin))){
            objFeature.setMultiplicityMin(multMin);
        }
        
        if (!("".equals(multMax))){
            objFeature.setMultiplicityMax(multMax);
        }
        
        testMutiplicity(objFeature);
        
    }

    @objid ("c6a2c8ba-10ca-4e74-be01-73e67d6ba77d")
    private void setMultiMax(StructuralFeature objFeature) {
        String multMax = EcoreModelNavigation.getMultiplicityMax((getEcoreElement()));
        
        if ("".equals(multMax)) {
            objFeature.setMultiplicityMax("undefined");
        }else{
            objFeature.setMultiplicityMax(multMax);
        }
        
    }

    @objid ("e512230e-acf1-48d4-a16e-f4887f90ebe6")
    private void setMultiMin(Instance objingElt) {
        String value =  ObjingEAnnotation.getMultiMin(this.ecoreElt);
        if ((value != null) && (!value.equals(""))){
            objingElt.setMultiplicityMin(value);
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

    @objid ("0dbf9884-4428-4703-9bd1-456893366a80")
    private void setMultiplicity(Instance objingElt) {
        String multMin = EcoreModelNavigation.getMultiplicityMin(getEcoreElement());
        String multMax = EcoreModelNavigation.getMultiplicityMax(getEcoreElement());
        
        if (!("".equals(multMin))){
            objingElt.setMultiplicityMin(multMin);
        }
        
        if (!("".equals(multMax))){
            objingElt.setMultiplicityMax(multMax);
        }
        
    }

    @objid ("893ae47c-5673-4106-9aae-421ca50d69de")
    private void setMultiMax(Instance objFeature) {
        String multMax = EcoreModelNavigation.getMultiplicityMax((getEcoreElement()));
        
        if ("".equals(multMax)) {
            objFeature.setMultiplicityMax("undefined");
        }else{
            objFeature.setMultiplicityMax(multMax);
        }
        
    }

    @objid ("96dd7007-0312-406a-a6f5-d82f128a3364")
    private void setMultiMin(StructuralFeature objingElt) {
        String value =  ObjingEAnnotation.getMultiMin(this.ecoreElt);
        if ((value != null) && (!value.equals(""))){
            objingElt.setMultiplicityMin(value);
        }
        
    }

    @objid ("6357e793-83f2-469e-9555-ecf37b4d55f5")
    public void setInstance(Instance objingElt) {
        //        setBase(objingElt);
        //        setRepresentedFeature(objingElt);
                setMultiplicity(objingElt);
        
                if (ReverseProperties.getInstance().isRoundtripEnabled()){
                    setIsConstant(objingElt);
                    setValue(objingElt);
                    setMultiMax(objingElt);
                    setMultiMin(objingElt);
                }
        
    }

    @objid ("6c2f0ca3-ac51-4afd-831e-b50785bcf615")
    private void setIsConstant(Instance objingElt) {
        objingElt.setIsConstant(ObjingEAnnotation.isConstant(this.ecoreElt));
    }

    @objid ("f28b1d0d-bb75-42c5-8019-94c954b3b9db")
    private void setValue(Instance objingElt) {
        String value =  ObjingEAnnotation.getValue(this.ecoreElt);
        if ((value != null) && (!value.equals(""))){
            objingElt.setValue(value);
        }
        
    }

    @objid ("96c21ba0-7649-43f8-ba34-b40c18012e02")
    @Override
    public org.eclipse.uml2.uml.StructuralFeature getEcoreElement() {
        return (org.eclipse.uml2.uml.StructuralFeature) super.getEcoreElement();
    }

    @objid ("ec70b3a9-18a9-46f5-9c01-2456a2a0d852")
    private void setRepresentedFeature(Instance objingElt) {
        if (objingElt instanceof BindableInstance){
        
            EList<?> listProperty =  ((Property) getEcoreElement()).getRedefinedProperties();
        
            if ((listProperty != null) && (listProperty.size() > 0) ){
                Property represented = (Property) listProperty.get(0);
                Object base = ReverseProperties.getInstance().getMappedElement(represented);
        
                if ((base instanceof UmlModelElement) && (!base.equals(objingElt)))
                    ((BindableInstance) objingElt).setRepresentedFeature((UmlModelElement) base);
                else if (base instanceof List<?> ){
                    ((BindableInstance) objingElt).setRepresentedFeature(((List<? extends UmlModelElement>) base).get(0));
                }
            }
        }
        
    }

    @objid ("3109cfb4-24b9-4c7f-a81c-91eaee46505e")
    private void setChangeableEAnnotation(StructuralFeature attribute) {
        String accessMode = ObjingEAnnotation.getAccessMode(getEcoreElement());
        
        if (ObjingEAnnotation.READ_VALUE.equals(accessMode)) {
            attribute.setChangeable(KindOfAccess.READ);
        } else if (ObjingEAnnotation.WRITE_VALUE.equals(accessMode)) {
            attribute.setChangeable(KindOfAccess.WRITE);
        } else if (ObjingEAnnotation.READ_WRITE_VALUE.equals(accessMode)) {
            attribute.setChangeable(KindOfAccess.READWRITE);
        } else if (ObjingEAnnotation.ACCESS_NONE_VALUE.equals(accessMode)) {
            attribute.setChangeable(KindOfAccess.ACCESNONE);
        }
        
    }

    @objid ("a8c3a982-d7e2-4ee7-8e34-07483c655cc1")
    private void setDerived(StructuralFeature structFeature) {
        structFeature.setIsDerived(((Property) getEcoreElement()).isDerived());
    }

    @objid ("e7963e8e-cf52-4bf9-9093-83ec443b9a0c")
    private void setAccessMode(StructuralFeature objingElt) {
        if (((Property) getEcoreElement()).isReadOnly()){
            objingElt.setChangeable(KindOfAccess.READ);
        }
        
    }

    @objid ("aee238a8-196c-43eb-961f-23b631430f81")
    private void setUnique(StructuralFeature attribute) {
        attribute.setIsUnique(((Property) getEcoreElement()).isUnique());
    }

    @objid ("78eeb275-bc76-4309-a83b-c72e3d1a2f62")
    private void setOrdered(StructuralFeature attribute) {
        attribute.setIsOrdered(((Property) getEcoreElement()).isOrdered());
    }

}
