/* 
 * Copyright 2013-2019 Modeliosoft
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

/*
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
package org.modelio.xmi.model.objing.profile;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.uml2.uml.Property;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.generation.ProfileExportVisitorImpl;
import org.modelio.xmi.generation.TotalExportMap;
import org.modelio.xmi.util.AbstractObjingModelNavigation;
import org.modelio.xmi.util.EcoreUMLTypes;
import org.modelio.xmi.util.ObjingEAnnotation;
import org.modelio.xmi.util.ProfileUtils;
import org.modelio.xmi.util.StringConverter;

@objid ("1d80e4b2-8012-46a7-8709-df0871645c61")
public class PExportAttribut implements IExportProfileElement {
    @objid ("85ad106f-2978-4536-8d32-231670114b7b")
    private TagType objingElt = null;

    @objid ("781fa152-4f0b-40c3-82b4-0dfc5f90a5c6")
    public PExportAttribut(TagType attribut) {
        this.objingElt = attribut;
    }

    @objid ("3bc4c9d3-d42e-40c6-867c-2d35b36112c9")
    @Override
    public void accept(ProfileExportVisitorImpl visitor) {
        visitor.visit(this);
    }

    @objid ("78171116-29ea-4dd4-a02f-29f57d16003c")
    public void visit() {
        org.eclipse.uml2.uml.Stereotype stereotype = null;
        
        if (this.objingElt.getOwnerStereotype() != null){
            stereotype = (org.eclipse.uml2.uml.Stereotype) TotalExportMap.getInstance().get(this.objingElt.getOwnerStereotype().getUuid().toString());
        }else{
            stereotype = (org.eclipse.uml2.uml.Stereotype) TotalExportMap.getInstance().get(this.objingElt.getOwnerReference().getUuid().toString());
        }
        
        
        Property attr = null;
        String name = ProfileUtils.getTagTypeName(this.objingElt);
        EcoreUMLTypes ecoreUMLTypes = GenerationProperties.getInstance().getEcoreUMLTypes();
        
        if (this.objingElt.getParamNumber().equals("0")){
            attr = stereotype.getOwnedAttribute(name,  ecoreUMLTypes.getBoolean());
            if (attr == null)
                attr = stereotype.createOwnedAttribute(name,  ecoreUMLTypes.getBoolean());
            attr.setLower(1);
            attr.setUpper(1);
        }else{
        
            attr = stereotype.getOwnedAttribute(name,  ecoreUMLTypes.getString());
            if (attr == null)
                attr = stereotype.createOwnedAttribute(name,  ecoreUMLTypes.getString());
        
            attr.setLower(0);
        
            String max = this.objingElt.getParamNumber();
        
            if (AbstractObjingModelNavigation.OBJING_UNLIMITED_VALUE.equals(max))
                attr.setUpper(org.eclipse.uml2.uml.LiteralUnlimitedNatural.UNLIMITED);
            else {
                Integer intMax = StringConverter.getInteger(max);
                if (intMax != null)
                    attr.setUpper(intMax);
            }
        
        }
        
        if (GenerationProperties.getInstance().isRoundtripEnabled()){
            setHidden(attr);
            setLabel(attr);
            setPartSignature(attr);
        }
        
        ObjingEAnnotation.addObjingID(attr, this.objingElt.getUuid().toString());
    }

    @objid ("ae65ef18-8569-4647-808d-03b304384f87")
    private void setLabel(Property attr) {
        ObjingEAnnotation.setLabel(attr, this.objingElt.getLabelKey());
    }

    @objid ("568ee1d2-d755-4f2e-aeef-e96f6e3040a0")
    private void setHidden(Property attr) {
        ObjingEAnnotation.setIsHidden(attr, this.objingElt.isIsHidden());
    }

    @objid ("5247818d-db77-4eb9-9fe9-d62edc4eb2df")
    private void setPartSignature(Property attr) {
        ObjingEAnnotation.setIsPartSignature(attr, this.objingElt.isBelongToPrototype());
    }

}
