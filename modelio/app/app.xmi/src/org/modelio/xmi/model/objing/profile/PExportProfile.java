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

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.MetaclassReference;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.xmi.generation.GenerationProperties;
import org.modelio.xmi.generation.ProfileExportVisitorImpl;
import org.modelio.xmi.generation.TotalExportMap;
import org.modelio.xmi.util.AbstractObjingModelNavigation;
import org.modelio.xmi.util.ObjingEAnnotation;
import org.modelio.xmi.util.ProfileUtils;

@objid ("963e791a-3cb9-487d-b502-083363ee09f5")
public class PExportProfile implements IExportProfileElement {
    @objid ("fab5222d-ac46-4a3f-be7f-fb2cd9822cad")
    private Profile objingElt = null;

    @objid ("cb32bac3-4af2-44ac-9ec2-2afa476c7c98")
    private TotalExportMap totalMap = TotalExportMap.getInstance();

    @objid ("7fa1f8b4-9c22-4a93-a054-30137618799b")
    public  PExportProfile(Profile profile) {
        this.objingElt = profile;
    }

    @objid ("3f245b4b-8fa5-4b49-b9a7-9bbce5cea563")
    @Override
    public void accept(ProfileExportVisitorImpl visitor) {
        visitor.visit(this);
    }

    @objid ("cb94e6c5-541a-4ddb-88aa-685ef8d92bb6")
    public void visit() {
        org.eclipse.uml2.uml.Profile ecoreElt = (org.eclipse.uml2.uml.Profile) GenerationProperties.getInstance().getMappedElement(this.objingElt);
        
        if (ecoreElt == null){
            ecoreElt = ProfileUtils.createEcoreProfile(this.objingElt);  
            this.totalMap.put(this.objingElt.getUuid().toString(), ecoreElt);
        }
        
        setProperties(ecoreElt);
        attach(ecoreElt);
        
    }

    @objid ("e7b43230-e473-4251-b7ad-25eb6affd2c1")
    private void setProperties(org.eclipse.uml2.uml.Profile ecoreElt) {
        ObjingEAnnotation.setModule(ecoreElt, this.objingElt.getOwnerModule().getUuid().toString());
        ObjingEAnnotation.addObjingID(ecoreElt, this.objingElt.getUuid().toString());
        String name = ProfileUtils.getName(this.objingElt);
        ecoreElt.setName(name);
        
    }

    @objid ("d24f3069-05b5-42b5-8877-a31f8ea480a1")
    public List<PExportProfile> getSubProfiles() {
        List<PExportProfile> subProfiles = new ArrayList<>();
        
        /** FIXME owned profiles ?
        for (ModelTree sub : this.objingElt.getOwnedElement()){
            if (sub instanceof Profile){
                PExportProfile temp = new PExportProfile((Profile) sub);
                subProfiles.add(temp);
            }
        }*/
        return subProfiles;
    }

    @objid ("d94ce0d1-e386-4159-b928-73e334cdbacc")
    public List<PExportStereotype> getStereotypes() {
        List<PExportStereotype> result = new ArrayList<>();
        for (Stereotype sub : this.objingElt.getDefinedStereotype()){
            if (AbstractObjingModelNavigation.mustBeExported(sub)){
                PExportStereotype temp = new PExportStereotype(sub);
                result.add(temp);
            }
        
        }
        return result;
    }

    @objid ("b0dc3bf6-04b8-48f6-a2a9-6ea94fec262f")
    public List<PExportReference> getReferences() {
        List<PExportReference> result = new ArrayList<>();
        for (MetaclassReference sub : this.objingElt.getOwnedReference()){
            PExportReference temp = new PExportReference(sub);
            result.add(temp);
        
        }
        return result;
    }

    @objid ("f805688b-afe5-42f0-b56b-29d31282b57a")
    public Profile getObjElt() {
        return this.objingElt;
    }

    @objid ("acb194f8-9696-4c0d-b13d-eaa07204046e")
    private void attach(final org.eclipse.uml2.uml.Profile ecoreElt) {
        Profile owner = ProfileUtils.getProfileOwner(this.objingElt);
        org.eclipse.uml2.uml.Profile ecoreOwner = null;
        if (owner != null){
            PExportProfile export = new PExportProfile(owner);
            export.visit();
            ecoreOwner = (org.eclipse.uml2.uml.Profile) GenerationProperties.getInstance().getMappedElement(owner);
        
            if  ((ecoreOwner != null) && (!(ecoreOwner.equals(ecoreElt))))
                ecoreOwner.getPackagedElements().add(ecoreElt);
        }
        
    }

}
