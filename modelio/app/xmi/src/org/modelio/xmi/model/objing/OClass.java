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
import org.eclipse.uml2.uml.UMLFactory;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.util.AbstractObjingModelNavigation;
import org.modelio.xmi.util.NotFoundException;
import org.modelio.xmi.util.ObjingEAnnotation;

@objid ("95d9960c-8cf7-4440-b0bc-c5ce81f4ef9c")
public class OClass extends ONameSpace {
    @objid ("56236cf4-9fcf-4360-869f-d5352e0182d7")
    private boolean isIsClassAssociation = false;

    @objid ("ec3f9196-03b3-44d8-96c7-c7016bf03e90")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        if (!this.isIsClassAssociation){
            return createEcoreClass();
        }else
            return getOrCreateEcoreAssociationClass();
    }

    @objid ("ffca62b1-d024-49df-a933-bf6c3f348013")
    private org.eclipse.uml2.uml.Element createEcoreClass() {
        return  UMLFactory.eINSTANCE.createClass();
    }

    @objid ("e68fdbf9-a34e-43c7-b174-069dc278ddef")
    private org.eclipse.uml2.uml.Element getOrCreateEcoreAssociationClass() {
        return GenerationProperties.getInstance()
                                                .getMappedElement((getObjingElement()));
    }

    @objid ("1157bb84-fe52-46b4-a871-77dace7d1d03")
    public OClass(Class element) {
        super(element);
        this.isIsClassAssociation = (AbstractObjingModelNavigation.isIsClassAssociation(element));
    }

    @objid ("72ca974f-3f59-43d9-9f1f-2a68d94d9883")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        // The "isIsClassAssociation" test may be done on sub-classes, like
        // "Component" that inherits from "Class".
        this.isIsClassAssociation = (AbstractObjingModelNavigation.isIsClassAssociation((Class) getObjingElement()));
        
        if (!this.isIsClassAssociation)
            linkEcoreClass( (org.eclipse.uml2.uml.Class) ecoreElt);
    }

    @objid ("800bd8b2-4e87-4e9a-9fed-3728711677f0")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        super.setProperties(ecoreElt);
        
        if (!this.isIsClassAssociation)
            setClassProperties( (org.eclipse.uml2.uml.Class) ecoreElt);
        else
            setAssociationClassProperties((org.eclipse.uml2.uml.AssociationClass) ecoreElt);
    }

    @objid ("44d3b4a3-a270-4d43-bcf8-8aa78d36aeed")
    private void setPrimitiveEAnnotation(org.eclipse.uml2.uml.Class ecoreElt) {
        ObjingEAnnotation.setIsPrimitive(ecoreElt, ((Class)getObjingElement()).isIsElementary());
    }

    @objid ("063bbbd6-9257-403a-90bd-7a10b3dcb68d")
    private void setLeaf(org.eclipse.uml2.uml.Class ecoreElt) {
        ecoreElt.setIsLeaf(((Class)getObjingElement()).isIsLeaf());
    }

    @objid ("1a415649-28ef-44f6-8d57-5c5bae296e54")
    private void setMainEAnnotation(org.eclipse.uml2.uml.Class ecoreElt) {
        ObjingEAnnotation.setIsMain(ecoreElt, ((Class)getObjingElement()).isIsMain());
    }

    @objid ("579ea019-d13f-4033-a71f-954244a90742")
    private void setActive(org.eclipse.uml2.uml.Class ecoreElt) {
        ecoreElt.setIsActive(((Class)getObjingElement()).isIsActive());
    }

    @objid ("f9027fd8-4da7-4b3b-b50c-b57fe36f3876")
    private void linkEcoreClass(org.eclipse.uml2.uml.Class ecoreElt) {
        GenerationProperties genProp = GenerationProperties.getInstance();
        
        ModelTree objingOwner = ((Class) getObjingElement()).getOwner();
        
        if (objingOwner == null)
            objingOwner = ((Class)getObjingElement()).getOwnerTemplateParameter();
        
        org.eclipse.uml2.uml.Element ecoreOwner = genProp.getMappedElement(objingOwner);
        
        if (ecoreOwner != null) {
            if (ecoreOwner instanceof org.eclipse.uml2.uml.Package) {
                org.eclipse.uml2.uml.Package ownerIsPkg = (org.eclipse.uml2.uml.Package) ecoreOwner;
                ownerIsPkg.getPackagedElements().add(ecoreElt);
            } else if (ecoreOwner instanceof org.eclipse.uml2.uml.Component) {
                org.eclipse.uml2.uml.Component ownerIsCmpnt = (org.eclipse.uml2.uml.Component) ecoreOwner;
                ownerIsCmpnt.getPackagedElements().add(ecoreElt);
            } else if (ecoreOwner instanceof org.eclipse.uml2.uml.Interface) {
                org.eclipse.uml2.uml.Interface ownerIsItf = (org.eclipse.uml2.uml.Interface) ecoreOwner;
                ownerIsItf.getNestedClassifiers().add(ecoreElt);
            } else if (ecoreOwner instanceof org.eclipse.uml2.uml.Class) {
                org.eclipse.uml2.uml.Class ownerIsClass =  (org.eclipse.uml2.uml.Class) ecoreOwner;
                ownerIsClass.getNestedClassifiers().add(ecoreElt);
            } else if (ecoreOwner instanceof org.eclipse.uml2.uml.TemplateParameter) {
                org.eclipse.uml2.uml.TemplateParameter ownerIsTemplateParameter = (org.eclipse.uml2.uml.TemplateParameter) ecoreOwner;
                ecoreElt.setOwningTemplateParameter(ownerIsTemplateParameter);
            } else {
                ecoreElt.destroy();
                throw new NotFoundException("Owner Class ("
                        + ecoreOwner.getClass().getSimpleName() + ") Not Found");
            }
        }
    }

    @objid ("08f87693-1790-4d07-92a5-a13e6864bed1")
    private void setClassProperties(org.eclipse.uml2.uml.Class ecoreElt) {
        this.setLeaf(ecoreElt);
        
        if (GenerationProperties.getInstance().isRoundtripEnabled()){
            this.setPrimitiveEAnnotation(ecoreElt);
            this.setMainEAnnotation(ecoreElt);
            this.setRootEAnnotation(ecoreElt);
        }
        
        this.setActive(ecoreElt);
    }

    @objid ("367cd918-8f6d-4411-993b-ecee4e380078")
    private void setAssociationClassProperties(org.eclipse.uml2.uml.AssociationClass ecoreElt) {
        this.setLeaf(ecoreElt);
        if (GenerationProperties.getInstance().isRoundtripEnabled()){
            this.setPrimitiveEAnnotation(ecoreElt);
            this.setMainEAnnotation(ecoreElt);
            this.setRootEAnnotation(ecoreElt);
        }
        this.setActive(ecoreElt);
    }

    @objid ("6441a27b-5a11-42cb-913c-d830424d0674")
    private void setRootEAnnotation(org.eclipse.uml2.uml.Class ecoreElt) {
        ObjingEAnnotation.setIsRoot(ecoreElt, ((Class)getObjingElement()).isIsRoot());
    }

}
