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

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.Modelio;
import org.modelio.api.module.IPeerModule;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.generation.ProfileExportVisitorImpl;
import org.modelio.xmi.generation.TotalExportMap;
import org.modelio.xmi.util.AbstractObjingModelNavigation;
import org.modelio.xmi.util.ObjingEAnnotation;
import org.modelio.xmi.util.ProfileUtils;

@objid ("0614af7e-f3e0-4f37-b361-db99bfbefbe6")
public class PExportStereotype implements IExportProfileElement {
    @objid ("a2636ac6-dc1b-4d82-a2be-b482473c8307")
    private Stereotype objingElt = null;

    @objid ("8881baef-0c1c-45f4-9f91-948274a95444")
    private GenerationProperties genProp = GenerationProperties.getInstance();

    @objid ("0df0ce2e-aca9-4c2c-8428-7f9cdbf810df")
    private TotalExportMap totalMap = TotalExportMap.getInstance();

    @objid ("2a1b4ef6-6d8a-4f6a-9c98-97f7610c1709")
    private ProfileExportVisitorImpl _visitor = null;

    @objid ("f4dddec1-04c0-493c-b74e-daf6ebbc26b0")
    public PExportStereotype(Stereotype stereotype) {
        this.objingElt = stereotype;
    }

    @objid ("acdc20d6-dc58-4ea7-b3e7-a75eda3e5b95")
    @Override
    public void accept(ProfileExportVisitorImpl visitor) {
        this._visitor = visitor;
        visitor.visit(this);
    }

    @objid ("a84e6d62-d897-4f3c-8eca-0004e87a3ba9")
    private org.eclipse.uml2.uml.Stereotype createEcoreStereotype() {
        return ProfileUtils.createStereotype(this.objingElt);
    }

    @objid ("2bf58f66-fe8d-4929-8da6-241e4a0889ff")
    private void setProperties(org.eclipse.uml2.uml.Stereotype stereotype) {
        setInheritance(stereotype);
        
        ObjingEAnnotation.addObjingID(stereotype, this.objingElt.getUuid().toString());
        
        if (this.genProp.isRoundtripEnabled()){
            setHidden(stereotype);
            setLabel(stereotype);
            setBaseClass(stereotype);
        
        }
    }

    @objid ("9c4790b4-eb9e-460f-ac72-83abc816f37e")
    private void setLabel(org.eclipse.uml2.uml.Stereotype stereotype) {
        ObjingEAnnotation.setLabel(stereotype, this.objingElt.getLabelKey());
    }

    @objid ("e85c5c20-bd59-4148-8472-d534fbc1669b")
    private void setHidden(org.eclipse.uml2.uml.Stereotype stereotype) {
        ObjingEAnnotation.setIsHidden(stereotype, this.objingElt.isIsHidden());
    }

    @objid ("96b7f855-5fc6-4be0-9eb8-04683d15e71f")
    private void setIcon(org.eclipse.uml2.uml.Stereotype stereotype) {
        String moduleName =  this.objingElt.getOwner().getOwnerModule().getName();
        
        for (IPeerModule pModules : Modelio.getInstance().getModuleService().getAllPeerModules()){
            if (pModules.getName().equals(moduleName)){
                Path moduleResPath = pModules.getConfiguration().getModuleResourcesPath();
        
                String iconPath = this.objingElt.getIcon();
        
                String imagePath = this.objingElt.getImage();    
        
                if ((imagePath != null) && (!imagePath.equals("")))
                    ProfileUtils.setStereotypeImage( moduleResPath,  imagePath, stereotype);
                else if ((iconPath != null) && (!iconPath.equals("")))
                    ProfileUtils.setStereotypeImage( moduleResPath,  iconPath, stereotype);
            }
        }
    }

    @objid ("d247064c-3133-4a3a-8e4e-cbe2cdeb009f")
    private void setInheritance(org.eclipse.uml2.uml.Stereotype stereotype) {
        // inheritance link
        
        Stereotype parent = this.objingElt.getParent();
        
        if(( parent != null) && (ProfileUtils.isInScope(parent))){
            org.eclipse.uml2.uml.Stereotype parentEcore = (org.eclipse.uml2.uml.Stereotype) this.genProp.getMappedElement(parent);
        
            if (parentEcore == null)  {
                Profile obParentProfile = this.objingElt.getParent().getOwner();
                PExportProfile parentProfile = new PExportProfile(obParentProfile);
                this. _visitor.visit(parentProfile);
        
                org.eclipse.uml2.uml.Profile ecoreParentProfile = (org.eclipse.uml2.uml.Profile) this.genProp.getMappedElement(obParentProfile);
        
                org.eclipse.uml2.uml.Profile ecoreProfile = (org.eclipse.uml2.uml.Profile) this.genProp.getMappedElement(this.objingElt.getOwner());
                this.genProp.inverseProfiles(ecoreParentProfile, ecoreProfile);
        
                parentEcore = (org.eclipse.uml2.uml.Stereotype) this.genProp.getMappedElement(parent);
            }
        
            if (!stereotype.getSuperClasses().contains(parentEcore))
                stereotype.getSuperClasses().add(parentEcore);
        }
    }

    @objid ("7d1456a4-5d57-4231-a4c4-d7b101275b3b")
    public List<PExportAttribut> getAttribute() {
        List<PExportAttribut> result = new ArrayList<>();
        for (TagType part : this.objingElt.getDefinedTagType()){
        
            PExportAttribut attribut = new PExportAttribut(part);
            result.add(attribut);
        
        }
        return result;
    }

    @objid ("ef717d48-ba6d-4a84-8214-56c3871fdbc0")
    public Element getElt() {
        return this.objingElt;
    }

    @objid ("9e204695-da8d-4674-8aa4-07b8ed5261bc")
    public void visit() {
        org.eclipse.uml2.uml.Stereotype ecoreElt = (org.eclipse.uml2.uml.Stereotype) this.totalMap.get(this.objingElt.getUuid().toString());
        
        if ((ecoreElt == null) && AbstractObjingModelNavigation.mustBeExported(this.objingElt)){
            ecoreElt = createEcoreStereotype();
            this.totalMap.put(this.objingElt.getUuid().toString(), ecoreElt);
            setProperties(ecoreElt);
        }
    }

    @objid ("577b5cea-8ec0-4a04-af6a-257706de5c22")
    private void setBaseClass(org.eclipse.uml2.uml.Stereotype stereotype) {
        ObjingEAnnotation.addBaseClass(stereotype, this.objingElt.getBaseClassName());
    }

    @objid ("167ba41d-6c83-40d9-99b8-e094fd1b3a32")
    public List<PExportNoteType> getNoteTypes() {
        List<PExportNoteType> result = new ArrayList<>();
        
        for (NoteType part : this.objingElt.getDefinedNoteType()){
        
            PExportNoteType attribut = new PExportNoteType(part);
            result.add(attribut);
        
        }
        return result;
    }

}
