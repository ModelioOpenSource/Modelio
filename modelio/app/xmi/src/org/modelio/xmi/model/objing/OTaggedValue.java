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
import org.eclipse.uml2.uml.Property;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.MetaclassReference;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.generation.TotalExportMap;

@objid ("4c2eb653-938e-4afa-9f5c-2672866cd73e")
public class OTaggedValue extends OElement implements IOElement {
    @objid ("b4857cf7-bc24-4237-a2a3-d6b70916a713")
    private TaggedValue objingElement;

    @objid ("60118e79-0aa2-4b81-8e6d-4e784407cfca")
    private org.eclipse.uml2.uml.PrimitiveType booleanType = GenerationProperties.getInstance().getEcoreUMLTypes().getBoolean();

    @objid ("666784c9-2b43-42b4-8c9f-01f10e5535ab")
    private org.eclipse.uml2.uml.PrimitiveType stringType = GenerationProperties.getInstance().getEcoreUMLTypes().getString();

    @objid ("9dd64097-b7dd-4c04-8fac-d78aca065aa7")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return setTaggedValues();
    }

    @objid ("4629fb84-44c0-4729-b931-a1816413145a")
    @Override
    public String getObjingID() {
        return this.objingElement.getUuid().toString();
    }

    @objid ("f76bc778-a826-4beb-a12d-e154730b03ef")
    @Override
    public Element getObjingElement() {
        return this.objingElement;
    }

    @objid ("b23c089b-1dbd-4e9c-9770-42d570d3deec")
    public OTaggedValue(TaggedValue element) {
        super(element);
        this.objingElement = element;
    }

    @objid ("9f1684ea-5320-4e84-90de-cb49b802acba")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        // TODO Auto-generated method stub
    }

    @objid ("34ffa6b5-25d1-4d3b-aa44-e2099391d104")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        // TODO Auto-generated method stub
    }

    @objid ("e5bb3260-7aca-4a61-938a-a63f4ed8b071")
    private Property setTaggedValues() {
        if( GenerationProperties.getInstance().isRoundtripEnabled()){
            return createValueEAnnotation();
        }else{
            return createValue();
        }
    }

    @objid ("d439fa99-1d1e-426f-bf26-1a99f1b1e759")
    private Property createValueEAnnotation() {
        ModelElement element = this.objingElement.getAnnoted();
                
        TagType tagType = this.objingElement.getDefinition(); 
                
        // r?cuperation du stereotype
                
        Stereotype obStereotype = tagType.getOwnerStereotype();
        org.eclipse.uml2.uml.Stereotype stereotype = null;
                
        if (obStereotype != null){
                
            Stereotype appliedSterotype = null;
            for (Stereotype extension : element.getExtension()){
                Stereotype temp = extension;
                while (temp != null ){
                    if (temp.equals(obStereotype)){
                        appliedSterotype = extension;
                        TotalExportMap totalCreationMap = TotalExportMap.getInstance();
                        stereotype = (org.eclipse.uml2.uml.Stereotype) totalCreationMap.get(appliedSterotype.getUuid().toString());
                    }
                
                    temp = temp.getParent();
                
                }
            }
                
        }else{
            MetaclassReference obReference = tagType.getOwnerReference();    
            TotalExportMap totalCreationMap = TotalExportMap.getInstance();
            stereotype = (org.eclipse.uml2.uml.Stereotype) totalCreationMap.get(obReference.getUuid().toString());
        }
                
        if (stereotype != null){
                
            String name = tagType.getName();
            if (tagType.getParamNumber().equals("0")){
                return stereotype.getOwnedAttribute(name, this.booleanType); 
            }else {
                return stereotype.getOwnedAttribute(name, this.stringType); 
            }
                
        }
        return null;
    }

    @objid ("11b90ed4-d2ab-4a8c-8193-cc8ad1556533")
    private Property createValue() {
        ModelElement element = this.objingElement.getAnnoted();
                
        TagType tagType = this.objingElement.getDefinition(); 
                
        // r?cuperation du stereotype
                
        Stereotype obStereotype = tagType.getOwnerStereotype();
        org.eclipse.uml2.uml.Stereotype stereotype = null;
                
        if (obStereotype != null){
                
            Stereotype appliedSterotype = null;
            for (Stereotype extension : element.getExtension()){
                Stereotype temp = extension;
                while (temp != null ){
                    if (temp.equals(obStereotype)){
                        appliedSterotype = extension;
                        TotalExportMap totalCreationMap = TotalExportMap.getInstance();
                        stereotype = (org.eclipse.uml2.uml.Stereotype) totalCreationMap.get(appliedSterotype.getUuid().toString());
                    }
                
                    temp = temp.getParent();
                
                }
            }
                
        }else{
            MetaclassReference obReference = tagType.getOwnerReference();    
            TotalExportMap totalCreationMap = TotalExportMap.getInstance();
            stereotype = (org.eclipse.uml2.uml.Stereotype) totalCreationMap.get(obReference.getUuid().toString());
        }
                
        if (stereotype != null){
                
            String name = tagType.getName(); 
            
            if (tagType.getParamNumber().equals("0")){
                return stereotype.getOwnedAttribute(name, this.booleanType); 
            }else {
                return stereotype.getOwnedAttribute(name, this.stringType); 
            }
        }
        return null;
    }

}
