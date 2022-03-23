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
package org.modelio.xmi.model.objing.profile;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.uml2.uml.Property;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.generation.ProfileExportVisitorImpl;
import org.modelio.xmi.generation.TotalExportMap;
import org.modelio.xmi.util.EcoreUMLTypes;
import org.modelio.xmi.util.ObjingEAnnotation;

@objid ("338905f6-6268-46db-b414-98f455073a11")
public class PExportNoteType implements IExportProfileElement {
    @objid ("111e2778-8212-4a64-b8bd-b3908ce30bb2")
    private NoteType objingElt = null;

    @objid ("f887459d-6ff8-45c9-ba01-1ab89c276b0a")
    public  PExportNoteType(NoteType attribut) {
        this.objingElt = attribut;
    }

    @objid ("759a0f66-9542-4dff-843f-dc31ee4fc062")
    @Override
    public void accept(ProfileExportVisitorImpl visitor) {
        visitor.visit(this);
    }

    @objid ("f71e0720-4fd4-4daf-ad69-2fdc5e995425")
    public void visit() {
        org.eclipse.uml2.uml.Stereotype stereotype = null;
        
        if (this.objingElt.getOwnerStereotype() != null){
            stereotype = (org.eclipse.uml2.uml.Stereotype)TotalExportMap.getInstance().get(this.objingElt.getOwnerStereotype().getUuid().toString());
        }else{
            stereotype = (org.eclipse.uml2.uml.Stereotype)TotalExportMap.getInstance().get(this.objingElt.getOwnerReference().getUuid().toString());
        }
        
        String name = this.objingElt.getName();
        EcoreUMLTypes ecoreUMLTypes = GenerationProperties.getInstance().getEcoreUMLTypes();
        
        Property attr  = stereotype.getOwnedAttribute(name,  ecoreUMLTypes.getString());
        if (attr == null)
            attr = stereotype.createOwnedAttribute(name,  ecoreUMLTypes.getString());
        
        attr.setLower(1);
        attr.setUpper(1);
        
        ObjingEAnnotation.setIsNoteType(attr);
        
        if (GenerationProperties.getInstance().isRoundtripEnabled()){
            setHidden(attr);
            setLabel(attr);
        
        }
        ObjingEAnnotation.addObjingID(attr, this.objingElt.getUuid().toString());
        
    }

    @objid ("df5f957c-8570-4a3f-9b37-be31af8f6466")
    private void setLabel(Property attr) {
        ObjingEAnnotation.setLabel(attr, this.objingElt.getLabelKey());
    }

    @objid ("5007b5f1-996c-4639-8566-01f368706de2")
    private void setHidden(Property attr) {
        ObjingEAnnotation.setIsHidden(attr, this.objingElt.isIsHidden());
    }

}
