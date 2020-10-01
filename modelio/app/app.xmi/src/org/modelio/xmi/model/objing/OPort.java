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

package org.modelio.xmi.model.objing;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.UMLFactory;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.util.AbstractObjingModelNavigation;
import org.modelio.xmi.util.NotFoundException;
import org.modelio.xmi.util.ObjingEAnnotation;

@objid ("0134d964-11a6-486d-aadb-c1cb066669df")
public class OPort extends OElement implements IOElement {
    @objid ("6477a75f-830a-49b5-b128-4184d791814f")
    private GenerationProperties genProp = GenerationProperties.getInstance();

    @objid ("43014f2c-ee75-4c65-a1ff-52355f6a5a6e")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        MObject objOwner = getObjingElement().getCompositionOwner();
        
        if (objOwner instanceof BindableInstance){
            boolean exist = false;
            ModelElement representedFeature = getObjingElement().getRepresentedFeature();
            NameSpace base = ((Instance) objOwner).getBase();
            exist = ((base!= null) && (representedFeature != null)
                    && (representedFeature instanceof Port)
                    && (representedFeature.getCompositionOwner().equals(base)));
        
            if (exist){
                return this.genProp.getMappedElement(representedFeature);
            }else{
                if ((representedFeature != null) && (representedFeature instanceof Port)
                        && !(representedFeature.getCompositionOwner() instanceof Instance)){
                    return this.genProp.getMappedElement(representedFeature);
                }else
                    return  null;
            }
        }else if (objOwner instanceof Instance)
            return  UMLFactory.eINSTANCE.createSlot();
        else if ( objOwner instanceof Classifier)
            return  UMLFactory.eINSTANCE.createPort();
        return null;
    }

    @objid ("4066af51-2b58-4921-834b-c004732f1a23")
    public OPort(Port element) {
        super(element);
    }

    @objid ("632cea98-f62b-4620-8f2a-f0d770ebdc4d")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        /*  org.eclipse.uml2.uml.Type of possible owners of a org.eclipse.uml2.uml.Port in Modelio: // - Classifier
         * (from BindableInstance) // - Instance (from '' ) // - NameSpace
         * (from Instance) // - TemplateParameter (from ModelElement) //
         * org.eclipse.uml2.uml.Type of possible owners of a org.eclipse.uml2.uml.Slot in Uml2: // - InstanceSpecification
         * (from slot) // - org.eclipse.uml2.uml.Element (from org.eclipse.uml2.uml.Element) // - EAnnotation (from
          EObject)
         */
        
        MObject objingOwner = getObjingElement().getCompositionOwner();
        
        if (objingOwner instanceof BindableInstance) {
            attachPortToBindableInstance((BindableInstance)objingOwner, ecoreElt);
        }else if (objingOwner instanceof Instance){
            attachPortToInstance((Instance)objingOwner, (org.eclipse.uml2.uml.Slot) ecoreElt);
        }else{
            attachPortToClassifier(objingOwner, (org.eclipse.uml2.uml.Port) ecoreElt);
        }
    }

    @objid ("9351cc2d-465b-4356-af19-71ea7e0986d6")
    private void attachPortToClassifier(MObject objingOwner, org.eclipse.uml2.uml.Port ecoreElt) {
        org.eclipse.uml2.uml.Element ecoreOwner =  this.genProp.getMappedElement(objingOwner);
        
        if (objingOwner instanceof NameSpace ){
            if ( ecoreOwner instanceof  Class) {
        
                if (!( (org.eclipse.uml2.uml.Class) ecoreOwner).getOwnedPorts().contains(ecoreElt))
                    ( (org.eclipse.uml2.uml.Class) ecoreOwner).getOwnedPorts().add(ecoreElt);
        
            }else if (ecoreOwner instanceof  org.eclipse.uml2.uml.Signal){
        
                if (!( (org.eclipse.uml2.uml.Signal) ecoreOwner).getOwnedAttributes().contains(ecoreElt))
                    ( (org.eclipse.uml2.uml.Signal) ecoreOwner).getOwnedAttributes().add(ecoreElt);
        
            }else    if (ecoreOwner instanceof Component){
        
                if (!((org.eclipse.uml2.uml.Component) ecoreOwner).getOwnedAttributes().contains(ecoreElt))
                    ((org.eclipse.uml2.uml.Component) ecoreOwner).getOwnedAttributes().add(ecoreElt);
        
            }else    if (ecoreOwner instanceof  org.eclipse.uml2.uml.Collaboration){
        
                if (!( (org.eclipse.uml2.uml.Collaboration) ecoreOwner).getOwnedAttributes().contains(ecoreElt))
                    ( (org.eclipse.uml2.uml.Collaboration) ecoreOwner).getOwnedAttributes().add(ecoreElt);
        
        
            }else {
                ecoreElt.destroy();
                throw new NotFoundException( "No possible mapping for " +
                        ecoreElt.getClass().getSimpleName() + " contained in " +
                        ecoreOwner.getClass().getSimpleName());
            }
        }else{
            ecoreElt.destroy();
        }
    }

    @objid ("2b4d3bdc-78c7-4b62-ba71-2551f58540ec")
    private void attachPortToInstance(Instance objingOwner, org.eclipse.uml2.uml.Slot ecoreElt) {
        InstanceSpecification ecoreOwner = (InstanceSpecification) this.genProp.getMappedElement(objingOwner);
        ecoreOwner.getSlots().add(ecoreElt);
    }

    @objid ("b35de0bf-cbdf-41b5-b971-bba2bc4c5bc7")
    private void attachPortToBindableInstance(BindableInstance objingOwner, org.eclipse.uml2.uml.Element ecoreElt) {
        NameSpace base = objingOwner.getBase();
        
        if ((base != null) && (ecoreElt instanceof org.eclipse.uml2.uml.Port)){
            attachPortToClassifier(base, (org.eclipse.uml2.uml.Port)ecoreElt);
        }else{
            MObject objOwner = AbstractObjingModelNavigation.getBindableInstanceOwner(getObjingElement());
            if ( objOwner instanceof Instance)
                attachPortToInstance((Instance) objOwner, (org.eclipse.uml2.uml.Slot) ecoreElt);
            else{
                attachPortToClassifier(objOwner, (org.eclipse.uml2.uml.Port) ecoreElt);
            }
        }
    }

    @objid ("1bfc6796-66ec-44a2-9505-947670f0e6a9")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        Boolean roundTrip = GenerationProperties.getInstance().isRoundtripEnabled();
        Port objElt = getObjingElement();
        
        if (ecoreElt instanceof org.eclipse.uml2.uml.Port){
            ((org.eclipse.uml2.uml.Port) ecoreElt).setIsBehavior(objElt.isIsBehavior());
            ((org.eclipse.uml2.uml.Port) ecoreElt).setIsService(objElt.isIsService());
        }else if (ecoreElt instanceof org.eclipse.uml2.uml.Slot){
            setDefiningFeature((org.eclipse.uml2.uml.Slot) ecoreElt);
        
            if (roundTrip) {
                ObjingEAnnotation.setIsBehavior(ecoreElt, objElt.isIsBehavior());
                ObjingEAnnotation.setIsService(ecoreElt, objElt.isIsService());
            }
        }
        
        if (roundTrip) {
            ObjingEAnnotation.setIsPort(ecoreElt);
            ObjingEAnnotation.setOwner(ecoreElt, String.valueOf(objElt.getCompositionOwner().getUuid().toString()));
            ObjingEAnnotation.setPortDirection(ecoreElt, objElt.getDirection().getLiteral());
        }
    }

    @objid ("d42e4b1d-66d8-4182-9035-3988627a22a0")
    private void setDefiningFeature(org.eclipse.uml2.uml.Slot slot) {
        Element represented = getObjingElement().getRepresentedFeature();
        if (represented != null){
        
            Object temp = this.genProp.getMappedElement(represented);
            if (temp instanceof org.eclipse.uml2.uml.StructuralFeature){
                slot.setDefiningFeature((org.eclipse.uml2.uml.StructuralFeature) temp);
            }
        }
    }

    @objid ("78a33388-78df-4e3a-b14f-000c137a9e20")
    @Override
    public Port getObjingElement() {
        return (Port) super.getObjingElement();
    }

}
