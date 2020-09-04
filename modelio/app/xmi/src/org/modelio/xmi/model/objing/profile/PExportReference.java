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

package org.modelio.xmi.model.objing.profile;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Property;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.MetaclassReference;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.xmi.generation.ProfileExportVisitorImpl;
import org.modelio.xmi.generation.TotalExportMap;
import org.modelio.xmi.util.AbstractObjingModelNavigation;
import org.modelio.xmi.util.ObjingEAnnotation;
import org.modelio.xmi.util.ProfileUtils;
import org.modelio.xmi.util.UMLMetamodel;

@objid ("c66ec558-979a-49aa-b710-ae416a00b8bb")
public class PExportReference implements IExportProfileElement {
    @objid ("05bd928f-3c22-4455-955d-34c33a562378")
    private MetaclassReference objingElt = null;

    @objid ("767ad16d-0cd7-48bb-8c30-ea9f7cb9a91e")
    private TotalExportMap totalMap = TotalExportMap.getInstance();

    @objid ("80dbd421-31cc-45d5-ad65-3d74eec45ce7")
    public PExportReference(MetaclassReference reference) {
        this.objingElt = reference;
    }

    @objid ("4084d237-9196-4973-89fc-ae79c5b64305")
    @Override
    public void accept(ProfileExportVisitorImpl visitor) {
        visitor.visit(this);
    }

    @objid ("d4bdfd74-1da5-4a0d-830a-aa7356e94dea")
    private org.eclipse.uml2.uml.Stereotype createEcoreStereotype() {
        org.eclipse.uml2.uml.Profile profile = (org.eclipse.uml2.uml.Profile) this.totalMap.get(this.objingElt.getOwnerProfile().getUuid().toString());
        
        org.eclipse.uml2.uml.Stereotype stereotype = ProfileUtils.createStereotype(this.objingElt, profile);
        
        ObjingEAnnotation.setIsReference(stereotype);
        return stereotype;
    }

    @objid ("ad4ae63b-42cb-4f82-bb4a-1e12c3f1e35c")
    private void setProperties(org.eclipse.uml2.uml.Stereotype stereotype) {
        setInheritance();
        ObjingEAnnotation.addObjingID(stereotype, this.objingElt.getUuid().toString());
    }

    @objid ("6833e3bc-e88b-4dd3-9e4c-4400193f6cb8")
    private void setInheritance() {
        org.eclipse.uml2.uml.Profile profile = (org.eclipse.uml2.uml.Profile) this.totalMap.get(this.objingElt.getOwnerProfile().getUuid().toString());
        
        org.eclipse.uml2.uml.Stereotype stereotypeEcore = (org.eclipse.uml2.uml.Stereotype) this.totalMap.get(this.objingElt.getUuid().toString());
        
        for (String newMetaclassName : ProfileUtils.getEcoreNameClass(this.objingElt.getReferencedClassName())){
        
            Class metaclassClass =  (org.eclipse.uml2.uml.Class) UMLMetamodel.getInstance().getUMLMetamodel().getOwnedType(newMetaclassName);
        
            boolean found = false;
            Class reference = metaclassClass;
        
            for (Object metaclass :  profile.getMetaclassReferences()){
                if (( (org.eclipse.uml2.uml.Class)((org.eclipse.uml2.uml.ElementImport)metaclass).getImportedElement()).getName().equals(metaclassClass.getName())){
                    reference =  (org.eclipse.uml2.uml.Class)((org.eclipse.uml2.uml.ElementImport)metaclass).getImportedElement();
                    found = true; 
                }
            }
        
            if (!found){ 
                profile.createMetaclassReference(reference);
                stereotypeEcore.createExtension(reference, false);
            }else{
        
                boolean typed = false ;
                for (Object extension : reference.getExtensions())
                    for (Object extensionEnd : ((org.eclipse.uml2.uml.Extension) extension).getOwnedEnds())
                        if (((Property) extensionEnd).getType().equals(stereotypeEcore))
                            typed = true;
        
                if (!typed)
                    stereotypeEcore.createExtension(reference, false);
            }    
        }
    }

    @objid ("192f0d0f-1092-4417-a22e-39633f21a95f")
    public List<PExportAttribut> getAttribute() {
        List<PExportAttribut> result = new ArrayList<>();
        
        for (TagType part : this.objingElt.getDefinedTagType()){
        
            PExportAttribut attribut = new PExportAttribut(part);
            result.add(attribut);
        
        }
        return result;
    }

    @objid ("ab296ee3-31da-45e7-9a38-81c4e2d80772")
    public List<PExportNoteType> getNoteTypes() {
        List<PExportNoteType> result = new ArrayList<>();
        for (NoteType part : this.objingElt.getDefinedNoteType()){
        
            PExportNoteType attribut = new PExportNoteType(part);
            result.add(attribut);
        
        }
        return result;
    }

    @objid ("b474ff00-1b21-4a65-a6a2-49527f8f16f0")
    public Element getElt() {
        return this.objingElt;
    }

    @objid ("21f3f4f9-9447-4ec2-9444-d1eb8ab9337a")
    public void visit() {
        org.eclipse.uml2.uml.Stereotype ecoreElt = (org.eclipse.uml2.uml.Stereotype) this.totalMap.get(this.objingElt.getUuid().toString());
        
        if ((ecoreElt == null) && AbstractObjingModelNavigation.mustBeExported(this.objingElt)){
            ecoreElt = createEcoreStereotype();
            this.totalMap.put(this.objingElt.getUuid().toString(), ecoreElt);
            setProperties(ecoreElt);
        }
    }

}
