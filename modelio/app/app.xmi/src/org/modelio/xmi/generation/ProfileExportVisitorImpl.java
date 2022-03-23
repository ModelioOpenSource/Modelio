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
package org.modelio.xmi.generation;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.xmi.gui.ProgressBarComposite;
import org.modelio.xmi.model.objing.profile.PExportAttribut;
import org.modelio.xmi.model.objing.profile.PExportGeneralization;
import org.modelio.xmi.model.objing.profile.PExportNoteType;
import org.modelio.xmi.model.objing.profile.PExportProfile;
import org.modelio.xmi.model.objing.profile.PExportReference;
import org.modelio.xmi.model.objing.profile.PExportStereotype;
import org.modelio.xmi.util.ProfileUtils;

@objid ("2fa25645-c7a2-4de7-bf38-e59b4bc11d2a")
public class ProfileExportVisitorImpl implements IProfileExportVisitor {
    @objid ("927bb4be-54a1-4191-b00d-68b238db8e9e")
    private ProgressBarComposite theProgressBar = null;

    @objid ("b1836d16-e25c-444d-b74e-ff4e54b8e64c")
    public  ProfileExportVisitorImpl(ProgressBarComposite progressBar) {
        this.theProgressBar = progressBar;
    }

    @objid ("39d10706-08ac-4bc8-a232-abb2db4cf813")
    public  ProfileExportVisitorImpl() {
        
    }

    @objid ("aa37cf99-81fb-469a-9b77-9d6c4a346bcf")
    @Override
    public void visit(PExportProfile profile) {
        profile.visit();
                
        for (PExportProfile subProfile : profile.getSubProfiles()){
            subProfile.accept(this);
            if (this.theProgressBar != null){
                this.theProgressBar.addElement();    
            }
        }
        
        for (PExportProfile subProfile : ProfileUtils.getSubProfiles(profile)){
            subProfile.accept(this);
            if (this.theProgressBar != null){
                this.theProgressBar.addElement();    
            }
        }
                
        for (PExportStereotype subStereotype : profile.getStereotypes()){
            
            subStereotype.accept(this);
            if (this.theProgressBar != null){
                this.theProgressBar.addElement();    
                this.theProgressBar.addValue();
            }
        }
                
                
        for (PExportReference subreference : profile.getReferences()){
            subreference.accept(this);
            if (this.theProgressBar != null){
                this.theProgressBar.addElement();    
            }
        }
        
    }

    @objid ("b38dbc96-032e-41ff-99bf-0ff253193464")
    @Override
    public void visit(PExportStereotype stereotype) {
        stereotype.visit();
                
        Stereotype elt = ((Stereotype)stereotype.getElt());
                
        PExportGeneralization generalization = new PExportGeneralization(
                elt.getBaseClassName(), elt);
        
        generalization.accept(this);
                
        for (PExportAttribut attribut : stereotype.getAttribute())
            attribut.accept(this);
        
        for (PExportNoteType noteType : stereotype.getNoteTypes())
            noteType.accept(this);
        
    }

    @objid ("4b2b6394-be4d-4c16-8c89-d6796d4c08cb")
    @Override
    public void visit(PExportAttribut attribut) {
        attribut.visit();
    }

    @objid ("7d658cad-443e-43cd-83cf-d1559390c712")
    @Override
    public void visit(PExportGeneralization generalization) {
        generalization.visit();
    }

    @objid ("9929c4eb-6d7c-4cc6-b0ff-5fc8f4a872cb")
    @Override
    public void visit(PExportReference reference) {
        reference.visit();
                
        for (PExportAttribut attribut : reference.getAttribute())
            attribut.accept(this);
        
        for (PExportNoteType noteType : reference.getNoteTypes())
            noteType.accept(this);
        
    }

    @objid ("308daab6-0f78-4cd3-aa2f-5f44cc5feff3")
    @Override
    public void visit(PExportNoteType noteType) {
        noteType.visit();
    }

}
